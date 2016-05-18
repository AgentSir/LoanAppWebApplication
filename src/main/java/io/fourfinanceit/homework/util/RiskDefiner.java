/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.fourfinanceit.homework.util;

import io.fourfinanceit.homework.enums.LoanApplicationStatusEnum;
import io.fourfinanceit.homework.repository.LoanApplicationRepository;
import io.fourfinanceit.homework.model.LoanApplication;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 *
 * @author sergey_putilov
 */
@Component
@ComponentScan
public class RiskDefiner {
    
    @Value("${loan.application.maxApplicationsPerDay}")
    private Integer maxApplicationsPerDay;
    
    @Value("${loan.application.maxAmount}")
    private BigDecimal maxAmount;
    
    @Value("${loan.application.riskHourFrom}")
    private Integer riskHourFrom;
    
    @Value("${loan.application.riskHourTill}")
    private Integer riskHourTill;

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;
    
    public boolean isAllowToLoanApplication(LoanApplication loanApplication){
        
        if (isMaxAmount(loanApplication)) {
            if(isRiskTime()) {
                return false;
            }
        } else {
            if (isLowedCountloans(loanApplication)) {
                return false;
            }
        }
        
        return true;
    }
    
    private boolean isMaxAmount(LoanApplication loanApplication) {
        
        return maxAmount.equals(loanApplication.getAmount());
    }
    
    private boolean isRiskTime () {

        Calendar cal = Calendar.getInstance(); 
        int dayHour = cal.get(Calendar.HOUR_OF_DAY);

        if (dayHour >= riskHourFrom && dayHour < riskHourTill) {
            return true;
        } else {
            return false;
        }
    }
    
    private boolean isLowedCountloans (LoanApplication loanApplication) {
        
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, -24);
        Date date = cal.getTime();
        String ipAddress = loanApplication.getIpAddress();
        List<LoanApplication> loanApplications = loanApplicationRepository.findByIpAddressAndStatusAndInsertDateGreaterThan(ipAddress, LoanApplicationStatusEnum.ACTIVE.getStatus(), cal.getTime());
        
        if (loanApplications.size() >= maxApplicationsPerDay) {
            return false;
        } else {
            return true;
        }
    }
    
}
