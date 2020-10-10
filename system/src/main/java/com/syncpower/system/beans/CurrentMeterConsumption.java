package com.syncpower.system.beans;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
public class CurrentMeterConsumption {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY )
    private int recordId;
    private  String meterNo;
    private  BigDecimal currentReading;
    private  BigDecimal totalEnergyConsumed;
    private  String createdBy;
    private  String updatedBy;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    public String getMeterNo() {
        return meterNo;
    }

    public void setMeterNo(String meterNo) {
        this.meterNo = meterNo;
    }

    public BigDecimal getCurrentReading() {
        return currentReading;
    }

    public void setCurrentReading(BigDecimal currentReading) {
        this.currentReading = currentReading;
    }

    public BigDecimal getTotalEnergyConsumed() {
        return totalEnergyConsumed;
    }

    public void setTotalEnergyConsumed(BigDecimal totalEnergyConsumed) {
        this.totalEnergyConsumed = totalEnergyConsumed;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date created_date) {
        this.createdDate = created_date;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updated_date) {
        this.updatedDate = updated_date;
    }
}
