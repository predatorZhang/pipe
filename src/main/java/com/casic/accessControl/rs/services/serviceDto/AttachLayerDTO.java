package com.casic.accessControl.rs.services.serviceDto;

import java.math.BigDecimal;

/**
 * Created by test203 on 2015/10/27.
 */
public class AttachLayerDTO {
    private double length;
    private String PipeType;

    public AttachLayerDTO() {

    }

    public AttachLayerDTO(double length, String pipeType) {
        this.length = length;
        this.PipeType = pipeType;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public String getPipeType() {
        return PipeType;
    }

    public void setPipeType(String pipeType) {
        this.PipeType = pipeType;
    }

}
