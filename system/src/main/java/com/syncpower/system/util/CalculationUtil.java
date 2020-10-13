package com.syncpower.system.util;

import com.syncpower.system.beans.CurrentDeviceConsumption;

import org.hibernate.transform.ToListResultTransformer;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CalculationUtil {

    public BigDecimal calculateTotalEnergy(List<CurrentDeviceConsumption> readingList){
        final BigDecimal[] totalConsumed = new BigDecimal[1];
        totalConsumed[0] = new BigDecimal(0.0000);
        MathContext mcPrecision = new MathContext(4);
        readingList.stream().forEach(meter -> {
            totalConsumed[0] = totalConsumed[0].add(meter.getCurrentReading());
        });
        System.out.println("Total Energy Consumed: "+totalConsumed[0].round(mcPrecision));
        return totalConsumed[0].round(mcPrecision);
    }
    
    public List<CurrentDeviceConsumption> getCurrentMaxRecords(List<CurrentDeviceConsumption> wholeList){
    	
    	Map<String, List<CurrentDeviceConsumption>> grupingByMeterName = 
    			wholeList.stream().collect(Collectors.groupingBy(CurrentDeviceConsumption::getDeviceNo, 
    					Collectors.mapping((CurrentDeviceConsumption p) -> p, Collectors.toList())));
    	
    	Comparator<CurrentDeviceConsumption> totalConsumedComp = Comparator.comparing(CurrentDeviceConsumption::getTotalEnergyConsumed);
    	Map<String, CurrentDeviceConsumption> records = new HashMap<String, CurrentDeviceConsumption>();
    	grupingByMeterName.forEach((key, valueList)->{
    		
    		Optional<CurrentDeviceConsumption> maxConsumMeterOptional = valueList.stream().max(totalConsumedComp);
    		if(maxConsumMeterOptional.isPresent()) {
    			records.put(key, maxConsumMeterOptional.get());
    		}
    	});
    	List<CurrentDeviceConsumption> finalRecords = new ArrayList<CurrentDeviceConsumption>(records.values());
    	return finalRecords;
    	
    }

}
