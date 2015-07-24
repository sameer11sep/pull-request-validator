package com.xebialabs;

import com.atlassian.jira.plugin.workflow.AbstractWorkflowPluginFactory;
import com.atlassian.jira.plugin.workflow.WorkflowPluginValidatorFactory;
import com.opensymphony.workflow.loader.AbstractDescriptor;

import java.util.HashMap;
import java.util.Map;

/**
 * Validator Factory
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
