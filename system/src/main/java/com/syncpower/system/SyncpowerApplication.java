package com.syncpower.system;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.syncpower.system.beans.DeviceConfig;
import com.syncpower.system.service.DeviceService;

@SpringBootApplication
@EnableScheduling
public class SyncpowerApplication implements CommandLineRunner{
	
	@Autowired
	DeviceService deviceService;

	public static void main(String[] args) {
		SpringApplication.run(SyncpowerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		DeviceConfig config1 = new DeviceConfig(1L, "D1", "Ethernet", "502", "192.168.0.102", "SYSTEM", "SYSTEM", new Date(), new Date());
		DeviceConfig config2 = new DeviceConfig(2L, "D2", "Ethernet", "502", "192.168.0.102", "SYSTEM", "SYSTEM", new Date(), new Date());
		DeviceConfig config3 = new DeviceConfig(3L, "D3", "Ethernet", "502", "192.168.0.102", "SYSTEM", "SYSTEM", new Date(), new Date());
		DeviceConfig config4 = new DeviceConfig(4L, "D4", "Ethernet", "502", "192.168.0.102", "SYSTEM", "SYSTEM", new Date(), new Date());
		DeviceConfig config5 = new DeviceConfig(5L, "D5", "Ethernet", "502", "192.168.0.102", "SYSTEM", "SYSTEM", new Date(), new Date());
		DeviceConfig config6 = new DeviceConfig(6L, "D6", "Ethernet", "502", "192.168.0.102", "SYSTEM", "SYSTEM", new Date(), new Date());
		DeviceConfig config7 = new DeviceConfig(7L, "D7", "Ethernet", "502", "192.168.0.102", "SYSTEM", "SYSTEM", new Date(), new Date());
		DeviceConfig config8 = new DeviceConfig(8L, "D8", "Ethernet", "502", "192.168.0.102", "SYSTEM", "SYSTEM", new Date(), new Date());
		
		deviceService.saveOrUpdate(config1);
		deviceService.saveOrUpdate(config2);
		deviceService.saveOrUpdate(config3);
		deviceService.saveOrUpdate(config4);
		deviceService.saveOrUpdate(config5);
		deviceService.saveOrUpdate(config6);
		deviceService.saveOrUpdate(config7);
		deviceService.saveOrUpdate(config8);
		
	}

}
