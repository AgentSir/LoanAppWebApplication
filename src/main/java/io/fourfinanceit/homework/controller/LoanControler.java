/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.fourfinanceit.homework.controller;

import io.fourfinanceit.homework.enums.LoanApplicationStatusEnum;
import io.fourfinanceit.homework.model.Client;
import io.fourfinanceit.homework.repository.LoanApplicationRepository;
import io.fourfinanceit.homework.model.LoanApplication;
import io.fourfinanceit.homework.repository.ClientRepository;
import io.fourfinanceit.homework.util.IpAddressDefiner;
import io.fourfinanceit.homework.util.RiskDefiner;
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
public class LoanControler {
       
    @Autowired
    private LoanApplicationRepository loanApplicationRepository;
    
    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private RiskDefiner riskDefiner;
    
    @Autowired
    private IpAddressDefiner ipAddressDefiner;
    
    @RequestMapping(
            value = "/loan",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<LoanApplication>> getLoanApplications() {

        Iterable<LoanApplication> loanApplications = loanApplicationRepository.findAll();

        return new ResponseEntity<Iterable<LoanApplication>>(loanApplications, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/loan/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoanApplication> getLoanApplication(@PathVariable("id") Long id) {

        LoanApplication loanApplication = loanApplicationRepository.findOne(id);
        if (loanApplication == null) {
            return new ResponseEntity<LoanApplication>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<LoanApplication>(loanApplication, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/loan",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoanApplication> createLoanApplication(@RequestBody LoanApplication loanApplication, HttpServletRequest request) {
        
        loanApplication.setIpAddress(ipAddressDefiner.getIpAddress(request));
        
        LoanApplication savedLoanApplication = loanApplicationRepository.save(loanApplication);
        if (savedLoanApplication.getStatus() == LoanApplicationStatusEnum.ACTIVE.getStatus()) {
            return new ResponseEntity<LoanApplication>(savedLoanApplication, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<LoanApplication>(savedLoanApplication, HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(
            value = "/loan/{id}",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoanApplication> deleteGreeting(
            @PathVariable("id") Long id, @RequestBody LoanApplication loanApplication) {
        
        if (loanApplicationRepository.findOne(id) != null) {
            
            loanApplicationRepository.delete(id);
            return new ResponseEntity<LoanApplication>(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<LoanApplication>(HttpStatus.NO_CONTENT);
        }
        
    }

}
