/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.fourfinanceit.homework.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 *
 * @author sergey_putilov
 */
@Entity
@Table(name = "loan_application")
public class LoanApplication {
    
    private Long loanApplicationId;
    private Long clientId;
    private String IpAddress;
    private BigDecimal amount;
    private Date term;
    private Date insertDate;
    private Integer status;
    
    @Override
    public boolean equals(Object o) {
        
        if (null == o) return false;
        if (!(o instanceof LoanApplication)) return false;
        if (this == o) return true;

        LoanApplication input = (LoanApplication) o;
        return new EqualsBuilder().append(this.getLoanApplicationId(), input.getLoanApplicationId()).isEquals();

    }

    @Override
    public int hashCode() {
        if (getLoanApplicationId() != null && getLoanApplicationId() > 0) {
            return new HashCodeBuilder(11, 37).append(getLoanApplicationId()).toHashCode();
        }
        else {
            return super.hashCode();
        }
    }
    
    /**
     * @return the applicationId
     */
    @Id
    @GeneratedValue
    @Column(name = "loan_application_id")
    public Long getLoanApplicationId() {
        return loanApplicationId;
    }
    
    /**
     * @param applicationId the applicationId to set
     */
    public void setLoanApplicationId(Long applicationId) {
        this.loanApplicationId = applicationId;
    }
    
    /**
     * @return the clientId
     */
    @Column(name = "client_id")
    public Long getClientId() {
        return clientId;
    }
    
    /**
     * @param clientId the clientId to set
     */
    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
    
    /**
     * @return the IpAddress
     */
    @Column(name = "ip_address")
    public String getIpAddress() {
        return IpAddress;
    }
    
    /**
     * @param IpAddress the IpAddress to set
     */
    public void setIpAddress(String IpAddress) {
        this.IpAddress = IpAddress;
    }
        
    /**
     * @return insertDate 
     */
    @Column(name = "loan_application_date", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getInsertDate() {
        return insertDate;
    }
    
    /**
     * @param insertDate the insertDate to set
     */
    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }
    
    /**
     * @return the amount
     */
    @Column(name = "amount")
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
                
    /**
     * @return the term
     */
    @Column(name = "term", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)    
    public Date getTerm() {
        return term;
    }
    
    /**
     * @param term the term to set
     */
    public void setTerm(Date term) {
        this.term = term;
    }
    
    /**
     * @return the status
     */
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
    
}
