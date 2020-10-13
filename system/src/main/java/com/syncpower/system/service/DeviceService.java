package com.syncpower.system.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syncpower.system.beans.CurrentDeviceConsumption;
import com.syncpower.system.beans.DeviceConfig;
import com.syncpower.system.beans.DeviceStatus;
import com.syncpower.system.repository.CurrentDeviceRepository;
import com.syncpower.system.repository.DeviceConfigRepository;
import com.syncpower.system.repository.DeviceStatusRepository;
import com.syncpower.system.util.CalculationUtil;

@Service
public class DeviceService {

	@Autowired
	CurrentDeviceRepository currentDeviceRepository;

	@Autowired
	CalculationUtil calculateUtil;

	@Autowired
	DeviceStatusRepository deviceStatusRepository;

	@Autowired
	DeviceConfigRepository deviceConfigRepository;

	public List<DeviceStatus> getAllDevicesStatus() {
		
		return deviceStatusRepository.findAll();
	}

	public DeviceStatus findByDeviceNo(String deviceNo) {
		return deviceStatusRepository.retriveByDeviceNo(deviceNo);

	}

	public void saveOrUpdate(DeviceStatus mtrStatus) {
		deviceStatusRepository.save(mtrStatus);
	}

	public List<CurrentDeviceConsumption> getAllDeviceReadings() {
		List<CurrentDeviceConsumption> consumptionList = currentDeviceRepository.findAll();
		//List<MeterStatus> statusList =  statusService.getAllMeterStatus();
		//currentMeterRepository.findAll().forEach(currentMeterRead -> consumList.add(currentMeterRead));
		return calculateUtil.getCurrentMaxRecords(consumptionList);
	}

	public CurrentDeviceConsumption retriveByDeviceNo(String deviceNo) {
		List<CurrentDeviceConsumption> readingList = currentDeviceRepository.retriveByDeviceNo(deviceNo);
		return readingList.get(0);
	}

	public void saveOrUpdate(CurrentDeviceConsumption meterReading) {
		currentDeviceRepository.save(meterReading);
	}

	public BigDecimal getPreviousEnergyConsumed(String deviceNo){
		BigDecimal previousEnergy = new BigDecimal(0.0000);
		List<CurrentDeviceConsumption> previousReadings = currentDeviceRepository.retriveByDeviceNo(deviceNo);
		if(!previousReadings.isEmpty() && previousReadings.size()>0){
			previousEnergy = previousReadings.get(0).getTotalEnergyConsumed();
			return previousEnergy;
		}
		return previousEnergy;
	}

	public List<DeviceConfig> getDeviceConfigs() {
		return deviceConfigRepository.findAll();
	}
	
	public DeviceConfig saveOrUpdate(DeviceConfig config) {
		deviceConfigRepository.save(config);
		return config;
	}

}
