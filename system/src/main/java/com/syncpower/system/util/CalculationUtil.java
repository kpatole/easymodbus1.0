package com.syncpower.system.util;

import com.syncpower.system.beans.CurrentMeterConsumption;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

@Component
public class CalculationUtil {

    public BigDecimal calculateTotalEnergy(List<CurrentMeterConsumption> readingList){
        final BigDecimal[] totalConsumed = new BigDecimal[1];
        totalConsumed[0] = new BigDecimal(0.0000);
        MathContext mcPrecision = new MathContext(4);
        readingList.stream().forEach(meter -> {
            totalConsumed[0] = totalConsumed[0].add(meter.getCurrentReading());
        });
        System.out.println("Total Energy Consumed: "+totalConsumed[0].round(mcPrecision));
        return totalConsumed[0].round(mcPrecision);
    }

}
