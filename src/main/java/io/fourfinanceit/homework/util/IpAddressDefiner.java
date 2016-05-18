/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.fourfinanceit.homework.util;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

/**
 *
 * @author sergey_putilov
 */
@Component
public class IpAddressDefiner {
    
    public String getIpAddress(HttpServletRequest request) {
        
        final String ipAddress = request.getHeader("X-FORWARDED-FOR");
        
        if(ipAddress == null) {
            return request.getRemoteAddr();
        }

        return ipAddress;
    }
    
}
