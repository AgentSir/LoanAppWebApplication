/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.fourfinanceit.homework.enums;

/**
 *
 * @author sergey_putilov
 */
public enum LoanApplicationStatusEnum {

    UNDEFINED(0),
    ACTIVE(1),
    EXPIRED(2),
    REJECTED(3);

    private Integer status;
    
    LoanApplicationStatusEnum (final Integer status) {
        this.status = status;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(final Integer status) {
        this.status = status;
    }
    
}
