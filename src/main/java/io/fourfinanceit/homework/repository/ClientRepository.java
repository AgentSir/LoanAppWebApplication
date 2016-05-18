/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.fourfinanceit.homework.repository;

import io.fourfinanceit.homework.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sergey_putilov
 */
@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

}
