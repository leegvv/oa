package net.arver.oa.connector;

import org.activiti.api.process.model.IntegrationContext;
import org.activiti.api.process.runtime.connector.Connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("serviceTask1Impl")
public class ServiceTaskConnector implements Connector {

    private Logger logger = LoggerFactory.getLogger(ServiceTaskConnector.class);

    @Override
    public IntegrationContext apply(final IntegrationContext integrationContext) {
        logger.info("Some service task logic... [processInstanceId=" + integrationContext.getProcessInstanceId() + "]");
        return integrationContext;
    }
}
