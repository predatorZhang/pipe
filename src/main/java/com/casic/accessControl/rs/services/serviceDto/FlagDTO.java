package com.casic.accessControl.rs.services.serviceDto;

import java.math.BigDecimal;

/**
 * Created by test203 on 2015/10/27.
 */
public class FlagDTO {
    private BigDecimal markerCount;
    private String Street;

    public BigDecimal getMarkerCount() {
        return markerCount;
    }

    public void setMarkerCount(BigDecimal markerCount) {
        this.markerCount = markerCount;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }
}
