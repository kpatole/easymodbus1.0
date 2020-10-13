package com.syncpower.system.config;

import com.syncpower.system.beans.DeviceConfig;
import com.syncpower.system.util.ReadCoilStatus;
import com.syncpower.system.util.ReadInputRegistersData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class ScheduleConfig {
    @Autowired
    private ReadInputRegistersData readDeviceData;
   
    @Autowired
    private ReadCoilStatus readCoilStatus;

    /*@Bean
    public TaskScheduler runReadingTask(){
        TaskScheduler scheduler = new TaskScheduler();
        ReadDeviceData readDeviceData = new ReadDeviceData();
        CronTrigger cronTrigger = new CronTrigger("0/3 * * * * *");
        scheduler.scheduleWithFixedDelay( new ReadDeviceData(), cronTri
        System.out.println("Running scheduler!");gger);
        return scheduler;
    }*/
    
//    @Scheduled(fixedRate = 12000)
//    public void scheduleReadCoilTask() {
//        //logger.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()) );
//    	readCoilStatus.run();
//        //readDeviceData.run();
//    }

    @Scheduled(fixedRate = 10000)
    public void scheduleReadIRValuesTask() {
        //logger.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()) );
        readDeviceData.run();
    }
}
