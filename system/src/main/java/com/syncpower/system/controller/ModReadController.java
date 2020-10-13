package com.syncpower.system.controller;

import com.syncpower.system.beans.CurrentDeviceConsumption;
import com.syncpower.system.beans.DeviceConfig;
import com.syncpower.system.beans.DeviceStatus;
import com.syncpower.system.service.DeviceService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/reading")
public class ModReadController{

	@Autowired
	DeviceService deviceService;


	@GetMapping(value = "/get/all",
			produces = { MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<CurrentDeviceConsumption>> getAllMeterReadings(){

		List<CurrentDeviceConsumption> deviceReadingData =  deviceService.getAllDeviceReadings();
		if(null != deviceReadingData){
			return ResponseEntity.ok(deviceReadingData);
		}else{
			return ResponseEntity.notFound().build();
		}
	}


	@GetMapping(value = "/get/{deviceName}",
			produces = { MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CurrentDeviceConsumption> getReadingByDevice(@PathVariable("deviceName") String deviceName){

		CurrentDeviceConsumption deviceConsumptionData =  deviceService.retriveByDeviceNo(deviceName);
		if(null != deviceConsumptionData){
			return ResponseEntity.ok(deviceConsumptionData);
		}else{
			return ResponseEntity.notFound().build();
		}
	}


	//@PathVariable("deviceName") String deviceName
	@GetMapping(value = "/get/statusList",
			produces = { MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<DeviceStatus>> getAllDevicesStatus(){

		List<DeviceStatus> statusList =  deviceService.getAllDevicesStatus();
		if(null != statusList && !statusList.isEmpty()){
			return ResponseEntity.ok(statusList);
		}else{
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(value = "/config",
			produces = { MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<DeviceConfig>> getDeviceConfigs(){

		List<DeviceConfig> configList =  deviceService.getDeviceConfigs();
		if(null != configList && !configList.isEmpty()){
			return ResponseEntity.ok(configList);
		}else{
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping(value = "/config/save",
			produces = { MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<DeviceConfig> saveConfig(@RequestBody DeviceConfig config){

		//List<DeviceConfig> configList =  deviceService.getDeviceConfigs();
		if(null != config){
			return ResponseEntity.ok(deviceService.saveOrUpdate(config));
		}else{
			return ResponseEntity.badRequest().build();
		}
	}

}
