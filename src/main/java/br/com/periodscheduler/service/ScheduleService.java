package br.com.periodscheduler.service;

import br.com.periodscheduler.entity.Task;
import br.com.periodscheduler.exception.TaskNotFoundException;
import br.com.periodscheduler.repository.TaskRepository;
import br.com.periodscheduler.vo.ScheduledTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ScheduleService {

    Map<ScheduledTask, ScheduledFuture> scheduleMap = new HashMap<>();

    @Autowired
    TaskRepository taskRepository;

    public void scheduleTask(Task task) {

        //TODO: resolve issues due timezone
        long delay = task.getScheduledTime().getTime() - System.currentTimeMillis();

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());

        ScheduledFuture scheduledFuture = scheduler.schedule(() -> {
            log.info("running scheduled job for taskId: " + task.getId());

            task.setActive(false);
            task.setLogMessage("Task executed successfully");
            taskRepository.save(task);
            scheduleMap.remove(new ScheduledTask(task.getId(), null));


        }, delay, TimeUnit.MILLISECONDS);

        scheduleMap.put(new ScheduledTask(task.getId(), task.getScheduledTime()), scheduledFuture);
    }

    public void cancelTask(Integer taskId) throws TaskNotFoundException {
        log.info("cancelling task for taskId: " + taskId);
        ScheduledTask scheduledTask = new ScheduledTask(taskId, null);

        ScheduledFuture scheduledFuture = scheduleMap.get(scheduledTask);

        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
            scheduleMap.remove(scheduledTask);
            taskRepository.deleteById(taskId);
            log.info("task successfully removed from scheduler");
        } else {
            log.error("task: " + taskId + " not found");
            throw new TaskNotFoundException("task: " + taskId + " not found");
        }
    }

    public List<ScheduledTask> getScheduledTasks() {
        return scheduleMap.keySet().stream().collect(Collectors.toList());
    }

}
