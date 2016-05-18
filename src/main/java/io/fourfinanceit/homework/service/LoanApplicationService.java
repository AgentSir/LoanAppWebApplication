/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.fourfinanceit.homework.service;

import io.fourfinanceit.homework.model.LoanApplication;

/**
 *
 * @author sergey_putilov
 */
public interface LoanApplicationService {
    
    public LoanApplication getLoanApplicationById(Long id);
    public Iterable<LoanApplication> listAllLoans();
    public LoanApplication create(LoanApplication loanApplication);
    public LoanApplication update(LoanApplication loanApplication);
    
}
