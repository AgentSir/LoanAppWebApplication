/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.fourfinanceit.homework.service;

import io.fourfinanceit.homework.model.Client;

/**
 *
 * @author sergey_putilov
 */
public interface ClientService {
    
    public Client getClientById(Long id);
    public Iterable<Client> getAllClients();
    public Client createOrUpdate(Client client);
    
}
