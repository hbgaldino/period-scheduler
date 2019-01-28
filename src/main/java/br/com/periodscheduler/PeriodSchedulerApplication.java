package br.com.periodscheduler;

import br.com.periodscheduler.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("br.com.periodscheduler.entity")
@Slf4j
public class PeriodSchedulerApplication implements CommandLineRunner {

    @Autowired
    ScheduleService scheduleService;

    public static void main(String[] args) {
        SpringApplication.run(PeriodSchedulerApplication.class, args);
    }

    @Override
    public void run(String... args) {
        scheduleService.loadAllPendingTasks();
    }
}

