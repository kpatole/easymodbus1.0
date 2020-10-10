package com.syncpower.system.service;

import com.syncpower.system.beans.CurrentMeterConsumption;
import com.syncpower.system.repository.CurrentMeterRepository;
import com.syncpower.system.util.CalculationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class CurrentMeterService {

    @Autowired
    CurrentMeterRepository currentMeterRepository;

    @Autowired
    CalculationUtil calculateUtil;

    public List<CurrentMeterConsumption> getAllReadings() {
        List<CurrentMeterConsumption> consumList = new ArrayList<>();
        currentMeterRepository.findAll().forEach(currentMeterRead -> consumList.add(currentMeterRead));
        return consumList;
    }

    public CurrentMeterConsumption retriveByMeterNo(String meterNo) {
        List<CurrentMeterConsumption> readingList = currentMeterRepository.retriveByMeterNo(meterNo);
        return readingList.get(0);
    }

    public void saveOrUpdate(CurrentMeterConsumption meterReading) {
        currentMeterRepository.save(meterReading);
    }

    public BigDecimal getPreviousEnergyConsumed(String meterNo){
        BigDecimal previousEnergy = new BigDecimal(0.0000);
        List<CurrentMeterConsumption> previousReadings = currentMeterRepository.retriveByMeterNo(meterNo);
        if(!previousReadings.isEmpty() && previousReadings.size()>0){
            previousEnergy = previousReadings.get(0).getTotalEnergyConsumed();
            return previousEnergy;
        }
        return previousEnergy;
    }

//    public void delete(int id) {
//        currentMeterRepository.deleteById(id);
//    }
}
