package com.casic.accessControl.rs.services.serviceDto;

import java.math.BigDecimal;

/**
 * Created by test203 on 2015/10/27.
 */
public class HealthPipeDTO {
    private BigDecimal pipeCount;
    private String PipeType;
    private String analyseDate;
    private String rank;

    public HealthPipeDTO() {

    }

    public HealthPipeDTO(BigDecimal pipeCount, String pipeType, String analyseDate, String rank) {
        this.pipeCount = pipeCount;
        PipeType = pipeType;
        this.analyseDate = analyseDate;
        this.rank = rank;
    }


    public static String convertRank(String rank) {
        if("严重疾病".equals(rank))
            rank = "A";
        if("疾病".equals(rank))
            rank = "B";
        if("亚健康".equals(rank))
            rank = "C";
        if("较健康".equals(rank))
            rank = "D";
        if("健康".equals(rank))
            rank = "E";
        return rank;
    }

    public BigDecimal getPipeCount() {
        return pipeCount;
    }

    public void setPipeCount(BigDecimal pipeCount) {
        this.pipeCount = pipeCount;
    }

    public String getPipeType() {
        return PipeType;
    }

    public void setPipeType(String pipeType) {
        this.PipeType = pipeType;
    }

    public String getAnalyseDate() {
        return analyseDate;
    }

    public void setAnalyseDate(String analyseDate) {
        this.analyseDate = analyseDate;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
