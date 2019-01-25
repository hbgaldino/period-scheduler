package br.com.periodscheduler;

import br.com.periodscheduler.repository.TaskRepository;
import br.com.periodscheduler.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.sql.DataSource;

@SpringBootApplication
@EntityScan("br.com.periodscheduler.entity")
@Slf4j
public class PeriodSchedulerApplication implements CommandLineRunner {

    @Autowired
    DataSource dataSource;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskRepository taskRepository;

    public static void main(String[] args) {
        SpringApplication.run(PeriodSchedulerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("TESTE    ");
        userRepository.findAll().forEach(user -> log.info(user.toString()));
        taskRepository.findAll().forEach(task -> log.info(task.toString()));
    }
}

