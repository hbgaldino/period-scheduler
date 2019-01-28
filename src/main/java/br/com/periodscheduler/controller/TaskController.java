package br.com.periodscheduler.controller;

import br.com.periodscheduler.entity.Task;
import br.com.periodscheduler.exception.TaskNotFoundException;
import br.com.periodscheduler.repository.TaskRepository;
import br.com.periodscheduler.service.ScheduleService;
import br.com.periodscheduler.vo.ScheduledTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/task")
@Slf4j
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    ScheduleService scheduleService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Task> create(@RequestBody Task task) {
        scheduleService.scheduleTask(taskRepository.save(task));
        return ResponseEntity.accepted().body(task);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ScheduledTask>> fetch() {
        return ResponseEntity.ok(scheduleService.getScheduledTasks());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) throws TaskNotFoundException {
        log.info("Deleting!!! " + id);
        scheduleService.cancelTask(id);
        return ResponseEntity.ok().build();
    }
}
