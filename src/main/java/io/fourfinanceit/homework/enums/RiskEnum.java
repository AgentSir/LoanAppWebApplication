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
public enum RiskEnum {
    
    UNDEFINED(0),
    NORMAL(1),
    TOO_HIGH(2);

    private Integer risk;
    
    RiskEnum(final Integer risk) {
        this.risk = risk;
    }

    /**
     * @return the risk
     */
    public Integer getRisk() {
        return risk;
    }

    /**
     * @param risk the risk to set
     */
    public void setRisk(final Integer risk) {
        this.risk = risk;
    }
    
}
