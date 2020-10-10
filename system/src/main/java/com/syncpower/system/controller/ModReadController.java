package com.syncpower.system.controller;

import com.syncpower.system.beans.CurrentMeterConsumption;
import com.syncpower.system.service.CurrentMeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/reading")
public class ModReadController{

    @Autowired
    CurrentMeterService meterService;

    @GetMapping(value = "/get/{deviceName}",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<CurrentMeterConsumption> getReadingByDevice(@PathVariable("deviceName") String deviceName){

        CurrentMeterConsumption meterConsumptionData =  meterService.retriveByMeterNo(deviceName);
        if(null != meterConsumptionData){
            return ResponseEntity.ok(meterConsumptionData);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
