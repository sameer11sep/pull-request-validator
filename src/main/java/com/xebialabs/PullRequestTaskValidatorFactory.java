package com.xebialabs;

import com.atlassian.jira.plugin.workflow.AbstractWorkflowPluginFactory;
import com.atlassian.jira.plugin.workflow.WorkflowPluginValidatorFactory;
import com.opensymphony.workflow.FactoryException;
import com.opensymphony.workflow.loader.AbstractDescriptor;
import com.opensymphony.workflow.loader.AbstractWorkflowFactory;
import com.opensymphony.workflow.loader.WorkflowDescriptor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sameerarora on 7/24/15.
 */
public class PullRequestTaskValidatorFactory extends AbstractWorkflowPluginFactory implements WorkflowPluginValidatorFactory {

    @Override
    protected void getVelocityParamsForInput(Map<String, Object> map) {

    }

    @Override
    protected void getVelocityParamsForEdit(Map<String, Object> map, AbstractDescriptor abstractDescriptor) {

    }

    @Override
    protected void getVelocityParamsForView(Map<String, Object> map, AbstractDescriptor abstractDescriptor) {

    }

    @Override
    public Map<String, ?> getDescriptorParams(Map<String, Object> map) {
        return new HashMap<String, Object>();
    }
}
