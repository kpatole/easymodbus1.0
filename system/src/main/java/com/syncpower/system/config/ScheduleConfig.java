package com.syncpower.system.config;

import com.syncpower.system.service.CurrentMeterService;
import com.syncpower.system.util.ReadDeviceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronTrigger;

import java.time.Instant;

@Configuration
public class ScheduleConfig {
    @Autowired
    private ReadDeviceData readDeviceData;

    /*@Bean
    public TaskScheduler runReadingTask(){
        TaskScheduler scheduler = new TaskScheduler();
        ReadDeviceData readDeviceData = new ReadDeviceData();
        CronTrigger cronTrigger = new CronTrigger("0/3 * * * * *");
        scheduler.scheduleWithFixedDelay( new ReadDeviceData(), cronTri
        System.out.println("Running scheduler!");gger);
        return scheduler;
    }*/

    @Scheduled(fixedRate = 10000)
    public void scheduleTaskWithFixedRate() {
        //logger.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()) );
        readDeviceData.run();
        //readDeviceData.run();
    }
}
