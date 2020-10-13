package com.syncpower.system.util;

import com.syncpower.system.beans.CurrentDeviceConsumption;
import com.syncpower.system.beans.DeviceConfig;
import com.syncpower.system.config.ModClientProps;
import com.syncpower.system.service.DeviceService;

import de.re.easymodbus.exceptions.ModbusException;
import de.re.easymodbus.modbusclient.ModbusClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;

@Component
public class ReadInputRegistersData extends BaseProducer {

	@Autowired
	DeviceService deviceService;

	/*@Autowired
	private ModClientProps modClientProps;*/

	@Override
	protected void batch() {
		System.out.println("Reading input register data task started.");

		/*	modClientProps.getDeviceIds().forEach(id->{
        		try {
					readInputRegisterValues(id);
				} catch (IOException | ModbusException e) {
					e.printStackTrace();
				}
        	});*/

		deviceService.getDeviceConfigs().forEach(device->{
			//System.out.println(device.getDeviceNo() +" IPADDESS: "+ device.getIpAddress());
			try {
				readInputRegisterValues(device);
			} catch (IOException | ModbusException e) {
				e.printStackTrace();
			}
		});

	}

	private void readInputRegisterValues(DeviceConfig deviceConfig) throws IOException, ModbusException {

		//ModbusClient modClient = new ModbusClient(modClientProps.getIpAddress(), modClientProps.getPort());
		ModbusClient modClient = new ModbusClient(deviceConfig.getIpAddress(), Integer.parseInt(deviceConfig.getPort()));

		try{
			modClient.Connect();
			modClient.setUnitIdentifier((byte)(long)deviceConfig.getRecordId());
			System.out.println("modClient getUnitIdentifier: "+modClient.getUnitIdentifier());
			int[] inputRegisters = modClient.ReadInputRegisters(0, 2);
			float value =  ModbusClient.ConvertRegistersToFloat(inputRegisters);
			BigDecimal preciseVal = new BigDecimal(Float.toString(value));
			System.out.println("Input Register value: "+ preciseVal);
			persistIRValuesToDb(preciseVal, deviceConfig.getDeviceNo());
		}catch(Exception  ex){
			System.out.println("Connection is not established");
			ex.printStackTrace();
		}
	}

	private void persistIRValuesToDb(BigDecimal preciseVal, String deviceName){
		MathContext mcPrecision = new MathContext(4);
		BigDecimal previousTotal =  deviceService.getPreviousEnergyConsumed("D1");
		BigDecimal currentTotal = previousTotal.add(preciseVal);
		CurrentDeviceConsumption meterConsumption = new CurrentDeviceConsumption();
		meterConsumption.setDeviceNo(deviceName);
		meterConsumption.setCurrentReading(preciseVal.round(mcPrecision));
		meterConsumption.setTotalEnergyConsumed(currentTotal.round(mcPrecision));
		meterConsumption.setCreatedBy("SYSTEM");
		meterConsumption.setUpdatedBy("SYSTEM");
		meterConsumption.setCreatedDate(new Date());
		meterConsumption.setUpdatedDate(new Date());

		deviceService.saveOrUpdate(meterConsumption);
	}

}
