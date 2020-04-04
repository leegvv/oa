package net.arver.oa.rest;

import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.ProcessInstanceMeta;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Pageable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProcessInstanceController {

    private Logger logger = LoggerFactory.getLogger(ProcessInstanceController.class);

    @Autowired
    private ProcessRuntime processRuntime;

    @GetMapping("/process-instances")
    public List<ProcessInstance> getProcessInstances() {
        final List<ProcessInstance> processInstances =
            processRuntime.processInstances(Pageable.of(0, 10)).getContent();
        return processInstances;
    }

    @GetMapping("process-instance-meta")
    public ProcessInstanceMeta getProcessInstanceMeta(@RequestParam("processInstanceId") final String processInstanceId) {
        final ProcessInstanceMeta processInstanceMeta = processRuntime.processInstanceMeta(processInstanceId);
        return processInstanceMeta;
    }
}
