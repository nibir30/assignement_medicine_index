package com.nibir.medicine_index.config.Scheduler;//package com.nibir.medicine_index.config.Scheduler;
//
//import com.cnsbd.idraWebApi.service.ElementService;
//import com.cnsbd.idraWebApi.service.LookUpService;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalTime;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//@Configuration
//public class SchedulerConfig {
//
//    @Autowired
//    private ElementService elementService;
//
//    @Autowired
//    LookUpService lookUpService;
//
//    @PostConstruct
//    private void scheduler() {
//        try {
//            ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
//            long initialDelay = calculateInitialDelay();
//            long periodSeconds = calculatePeriodInSeconds();
//
//            Runnable serviceTask = this::serviceTask;
//
//            scheduledExecutorService.scheduleAtFixedRate(serviceTask, initialDelay, periodSeconds, TimeUnit.SECONDS);
//
////            scheduledExecutorService.shutdown();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void serviceTask() {
//        try {
//            elementService.publishElement();
//
//            elementService.expireElement();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private long calculateInitialDelay() {
//        long initialDelay = 0;
//        try {
//            LocalTime now = LocalTime.now();
//
////            LocalTime targetTime = LocalTime.of(15, 53);
//            LocalTime targetTime = LocalTime.MIDNIGHT;
//            initialDelay = targetTime.toSecondOfDay() - now.toSecondOfDay();
//
//            if (initialDelay < 0) {
//                initialDelay += TimeUnit.DAYS.toSeconds(1);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return initialDelay;
//    }
//
//    private long calculatePeriodInSeconds() {
//        long periodInSeconds = 0;
//        try {
//            periodInSeconds = TimeUnit.DAYS.toSeconds(1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return periodInSeconds;
//    }
//}
