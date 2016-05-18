/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.fourfinanceit.homework.service.impl;

import io.fourfinanceit.homework.enums.LoanApplicationStatusEnum;
import io.fourfinanceit.homework.repository.LoanApplicationRepository;
import io.fourfinanceit.homework.model.LoanApplication;
import io.fourfinanceit.homework.util.RiskDefiner;
import io.fourfinanceit.homework.service.LoanApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sergey_putilov
 */
@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {
    
    @Autowired
    private LoanApplicationRepository loanApplicationRepository;
    
    @Autowired
    private RiskDefiner riskDefiner;
    
    public LoanApplication getLoanApplicationById(Long id) {
        LoanApplication loanApplication = loanApplicationRepository.findOne(id);
        return loanApplication;
    }

    public Iterable<LoanApplication> listAllLoans() {
        Iterable<LoanApplication> loanApplications = loanApplicationRepository.findAll();
        return loanApplications;
    }
    
    public LoanApplication create(LoanApplication loanApplication) {
        
        if (loanApplication.getClientId() != null) {
            return null;
        } 
        
        if (riskDefiner.isAllowToLoanApplication(loanApplication)) {
            loanApplication.setStatus(LoanApplicationStatusEnum.ACTIVE.getStatus());
        } else {
            loanApplication.setStatus(LoanApplicationStatusEnum.REJECTED.getStatus());
        }
        
        LoanApplication savedLoanApplication = loanApplicationRepository.save(loanApplication);
        return savedLoanApplication;
        
    }
    
    public LoanApplication update(LoanApplication loanApplication) {
        
        LoanApplication loanApplicationPersisted = getLoanApplicationById(loanApplication.getLoanApplicationId());
        
        if (loanApplicationPersisted == null) {
            return null;
        }
        
        LoanApplication updatedLoanApplication = loanApplicationRepository.save(loanApplication);
        return updatedLoanApplication;
        
    }
    
}
