package net.arver.oa.rest;

import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.runtime.TaskAdminRuntime;
import org.activiti.api.task.runtime.TaskRuntime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class TaskManagementController {

    private Logger logger = LoggerFactory.getLogger(TaskManagementController.class);

    @Autowired
    private TaskRuntime taskRuntime;

    @Autowired
    private TaskAdminRuntime taskAdminRuntime;

    @GetMapping("/my-tasks")
    public List<Task> getMyTasks() {
        final Page<Task> tasks = taskRuntime.tasks(Pageable.of(0, 10));
        logger.info("> My Available Tasks:" + tasks.getTotalItems());

        for (final Task task : tasks.getContent()) {
            logger.info("\t > My User Task:" + task);
        }

        return tasks.getContent();
    }

    @GetMapping("/all-tasks")
    public List<Task> getAllTasks() {
        final Page<Task> tasks = taskAdminRuntime.tasks(Pageable.of(0, 10));
        logger.info("> All Available Tasks:" + tasks.getTotalItems());

        for (final Task task : tasks.getContent()) {
            logger.info("\t User Task: " + task);
        }
        return tasks.getContent();
    }

    @GetMapping("/complete-task")
    public String completeTask(@RequestParam(value = "taskId") final String taskId) {
        taskRuntime.complete(TaskPayloadBuilder.complete().withTaskId(taskId).build());
        return "Completed Task:" + taskId;
    }

}
