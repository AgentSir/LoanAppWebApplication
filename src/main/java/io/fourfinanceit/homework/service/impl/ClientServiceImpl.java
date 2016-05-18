/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.fourfinanceit.homework.service.impl;

import io.fourfinanceit.homework.enums.LoanApplicationStatusEnum;
import io.fourfinanceit.homework.repository.ClientRepository;
import io.fourfinanceit.homework.model.Client;
import io.fourfinanceit.homework.service.ClientService;
import io.fourfinanceit.homework.util.RiskDefiner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sergey_putilov
 */
@Service
public class ClientServiceImpl implements ClientService {
    
    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private RiskDefiner riskDefiner;

    public Client getClientById(Long id) {
        Client client = clientRepository.findOne(id);
        return client;
    }
    
    public Iterable<Client> getAllClients() {
        Iterable<Client> clients = clientRepository.findAll();
        return clients;
    }
    
    public Client createOrUpdate(Client client) {
        
        Client clientPersisted = getClientById(client.getClientId());
        
        if (clientPersisted.getClientId() != null) {
            
            clientPersisted.getLoanApplications().add(client.getLoanApplications().iterator().next());
            
            while (clientPersisted.getLoanApplications().iterator().hasNext()) {
                
                if (!clientPersisted.getLoanApplications().iterator().hasNext()) {
                    
                    if (riskDefiner.isAllowToLoanApplication(clientPersisted.getLoanApplications().iterator().next())) {
                        clientPersisted.getLoanApplications().iterator().next().setStatus(LoanApplicationStatusEnum.ACTIVE.getStatus());
                    } else {
                        clientPersisted.getLoanApplications().iterator().next().setStatus(LoanApplicationStatusEnum.REJECTED.getStatus());
                    }
                    
                    Client updatedClient = clientRepository.save(clientPersisted);
                    return updatedClient;

                }
            }   
        } 
        
        if (riskDefiner.isAllowToLoanApplication(client.getLoanApplications().iterator().next())) {
            client.getLoanApplications().iterator().next().setStatus(LoanApplicationStatusEnum.ACTIVE.getStatus());
        } else {
            client.getLoanApplications().iterator().next().setStatus(LoanApplicationStatusEnum.REJECTED.getStatus());
        }
        
        Client savedClient = clientRepository.save(client);
        return savedClient;
    }
    
}
