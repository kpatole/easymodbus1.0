package com.syncpower.system.util;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.syncpower.system.beans.DeviceStatus;
import com.syncpower.system.config.ModClientProps;
import com.syncpower.system.service.DeviceService;

import de.re.easymodbus.exceptions.ModbusException;
import de.re.easymodbus.modbusclient.ModbusClient;

@Component
public class ReadCoilStatus extends BaseProducer {
	
    @Autowired
    private DeviceService statusService;
    
    @Autowired
    private ModClientProps modClientProps;

	@Override
	protected void batch() {
		System.out.println("Reading coil status task started.");
        	modClientProps.getDeviceIds().forEach(id->{
        		try {
					readCoilValues(id);
				} catch (IOException | ModbusException e) {
					e.printStackTrace();
				}
        	});
	}
	
	
    public void readCoilValues(int slaveId) throws IOException, ModbusException {
    	
    	ModbusClient modClient = new ModbusClient(modClientProps.getIpAddress(), modClientProps.getPort());
    	try {

	        modClient.Connect();
	        modClient.setUnitIdentifier((byte)slaveId);
	        
            System.out.println("modClient: "+modClient.getUnitIdentifier());
            boolean coilPoll = modClient.ReadCoils(0,1)[0];
            System.out.println("device "+slaveId+" is on: true  or off: false  =>  "+coilPoll);
            modClient.Disconnect();
            persistCoilValuesToDb(slaveId, coilPoll);
    	}catch(Exception  ex){
            System.out.println("Connection is not established");
            ex.printStackTrace();
        }
    }
    
    
    private void persistCoilValuesToDb(int slaveId, boolean coilPoll){
    	DeviceStatus mtrStatus = statusService.findByDeviceNo("D"+slaveId);
    	if(null != mtrStatus) {
    		mtrStatus.setActive(coilPoll);
    		mtrStatus.setCreatedBy("SYSTEM");
    		mtrStatus.setUpdatedBy("SYSTEM");
    		mtrStatus.setCreatedDate(new Date());
    		mtrStatus.setUpdatedDate(new Date());
    		statusService.saveOrUpdate(mtrStatus);
    		
    	}else {
    		DeviceStatus mtrStatusNew = new DeviceStatus();
    		mtrStatusNew.setDeviceNo("D"+slaveId);
    		mtrStatusNew.setActive(false);
    		mtrStatusNew.setCreatedBy("SYSTEM");
    		mtrStatusNew.setUpdatedBy("SYSTEM");
    		mtrStatusNew.setCreatedDate(new Date());
    		mtrStatusNew.setUpdatedDate(new Date());
    		statusService.saveOrUpdate(mtrStatusNew);
    	}
    	System.out.println("Value has been stored/updated");
    }

}
