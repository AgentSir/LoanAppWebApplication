/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.fourfinanceit.homework.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 *
 * @author sergey_putilov
 */
@Entity
@Table(name = "client")
public class Client {
    
    private Long clientId;
    private String firstName;
    private String lastName;
    private Set<LoanApplication> loanApplications;
    
    @Override
    public boolean equals(Object o) {
        
        if (null == o) return false;
        if (!(o instanceof Client)) return false;
        if (this == o) return true;

        Client input = (Client) o;
        return new EqualsBuilder().append(this.getClientId(), input.getClientId()).isEquals();

    }

    @Override
    public int hashCode() {
        if (getClientId() != null && getClientId() > 0) {
            return new HashCodeBuilder(11, 37).append(getClientId()).toHashCode();
        }
        else {
            return super.hashCode();
        }
    }
    
    /**
     * @return the clientId
     */
    @Id
    @GeneratedValue
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
     * @return the firstName
     */
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }
       
    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
        
    /**
     * @return the lastName
     */
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
     
    /**
     * @return the loanApplications
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = {javax.persistence.CascadeType.ALL})
    @JoinColumn(name = "client_id")
    @JsonManagedReference
    public Set<LoanApplication> getLoanApplications() {
        return loanApplications;
    }

    /**
     * @param loanApplications the loanApplications to set
     */
    public void setLoanApplications(Set<LoanApplication> loanApplications) {
        this.loanApplications = loanApplications;
    }
    
}
