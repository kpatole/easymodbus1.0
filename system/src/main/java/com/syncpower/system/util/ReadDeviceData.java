package com.syncpower.system.util;

import com.syncpower.system.beans.CurrentMeterConsumption;
import com.syncpower.system.service.CurrentMeterService;
import de.re.easymodbus.exceptions.ModbusException;
import de.re.easymodbus.modbusclient.ModbusClient;
import de.re.easymodbus.server.ModbusServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.Date;

@Component
public class ReadDeviceData extends BaseProducer {

    @Autowired
    CurrentMeterService meterService;

    @Override
    protected void batch() {
        System.out.println("Base Producer");
        try{
            connectToDevice();
        }catch(IOException e){
            e.printStackTrace();
        }catch(ModbusException modEx){
            modEx.printStackTrace();
        }
    }

    private void connectToDevice() throws IOException, ModbusException {
        ModbusServer modServer = new ModbusServer();
        //boolean[] coils = modServer.coils();
        ModbusClient modClient = new ModbusClient();
        modClient.Connect("192.168.0.101", 502);
        if(modClient.isConnected()){
            System.out.println("modClient: "+modClient);
            //byte[] data = modClient.receiveData();
            int[] inputRegisters = modClient.ReadInputRegisters(0, 10);
            boolean[] coilRegisters = modClient.ReadCoils(0,10);
            System.out.println(coilRegisters);
            float value =  modClient.ConvertRegistersToFloat(inputRegisters);
            BigDecimal preciseVal = new BigDecimal(Float.toString(value));
            System.out.println("Input Register value: "+ preciseVal);
            persistToDb(preciseVal);
        }else{
            System.out.println("Connection is not established");
        }
    }

    private void persistToDb(BigDecimal preciseVal){
        MathContext mcPrecision = new MathContext(4);
        BigDecimal previousTotal =  meterService.getPreviousEnergyConsumed("D1");
        BigDecimal currentTotal = previousTotal.add(preciseVal);
        CurrentMeterConsumption meterConsumption = new CurrentMeterConsumption();
        meterConsumption.setMeterNo("D1");
        meterConsumption.setCurrentReading(preciseVal.round(mcPrecision));
        meterConsumption.setTotalEnergyConsumed(currentTotal.round(mcPrecision));
        meterConsumption.setCreatedBy("SYSTEM");
        meterConsumption.setUpdatedBy("SYSTEM");
        meterConsumption.setCreatedDate(new Date());
        meterConsumption.setUpdatedDate(new Date());

        meterService.saveOrUpdate(meterConsumption);
    }

}
