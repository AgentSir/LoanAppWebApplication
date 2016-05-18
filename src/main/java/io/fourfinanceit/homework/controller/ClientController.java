/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.fourfinanceit.homework.controller;

import io.fourfinanceit.homework.enums.LoanApplicationStatusEnum;
import io.fourfinanceit.homework.model.Client;
import io.fourfinanceit.homework.model.LoanApplication;
import io.fourfinanceit.homework.repository.ClientRepository;
import io.fourfinanceit.homework.util.IpAddressDefiner;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sergey_putilov
 */
@RestController
public class ClientController {
    
    @Autowired
    private ClientRepository clientRepository;
        
    @Autowired
    private IpAddressDefiner ipAddressDefiner;
    
    @RequestMapping(
            value = "/client",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Client>> getClients() {

        Iterable<Client> clients = clientRepository.findAll();

        return new ResponseEntity<Iterable<Client>>(clients, HttpStatus.OK);
    }
    
    @RequestMapping(
            value = "/client/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> getClient(@PathVariable("id") Long id) {

        Client client = clientRepository.findOne(id);
        
        if (client == null) {
            return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Client>(client, HttpStatus.OK);
    }
    
    @RequestMapping(
            value = "/client",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoanApplication> createClient(@RequestBody Client client, HttpServletRequest request) {
        
        LoanApplication loanApplication = null;
        
        while (client.getLoanApplications().iterator().hasNext()) {
           loanApplication = client.getLoanApplications().iterator().next();
           loanApplication.setIpAddress(ipAddressDefiner.getIpAddress(request));
        }
        
        Client savedClient = clientRepository.save(client);
        
        while (savedClient.getLoanApplications().iterator().hasNext()) {
            if (!savedClient.getLoanApplications().iterator().hasNext()) {
                loanApplication = savedClient.getLoanApplications().iterator().next();
            }
        }
        
        if (loanApplication.getStatus() == LoanApplicationStatusEnum.ACTIVE.getStatus()) {
            return new ResponseEntity<LoanApplication>(loanApplication, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<LoanApplication>(loanApplication, HttpStatus.FORBIDDEN);
        }
        
    }
    
    @RequestMapping(
            value = "/client/{id}",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> deleteGreeting(
            @PathVariable("id") Long id, @RequestBody Client client) {
        
        if (clientRepository.findOne(id) != null) {
            
            clientRepository.delete(id);
            return new ResponseEntity<Client>(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<Client>(HttpStatus.NO_CONTENT);
        }
        
    }
    
}
