package com.xebialabs;

import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.project.version.Version;
import com.opensymphony.workflow.WorkflowException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PullRequestTaskValidatorTest {

    @Rule
    public ExpectedException exception=ExpectedException.none();

    @Test
    public void shouldPullAllPRs() throws WorkflowException {
        Issue issue= mock(Issue.class);
        when(issue.isSubTask()).thenReturn(false);
        when(issue.getKey()).thenReturn("DEPL-3");
        Map map=new HashMap();
        map.put("issue",issue);
        PullRequestTaskValidator validator=new PullRequestTaskValidator();
        validator.validate(map,null,null);
    }

    @Test
    public void shouldThrowExceptionIfPRIsNotAvailable() throws WorkflowException {
        exception.expect(WorkflowException.class);
        exception.expectMessage("There is no Pull Request Associated with this Task");
        Issue issue= mock(Issue.class);
        when(issue.isSubTask()).thenReturn(false);
        when(issue.getKey()).thenReturn("DEPL-1");
        Map map=new HashMap();
        map.put("issue",issue);
        PullRequestTaskValidator validator=new PullRequestTaskValidator();
        validator.validate(map,null,null);
    }

    @Test
    public void shouldThrowExceptionIfPRForEachFixVersionIsNotAvailable() throws WorkflowException {
        exception.expect(WorkflowException.class);
        exception.expectMessage("There should be at least one Pull Request for each Fix Version");
        Issue issue= mock(Issue.class);
        when(issue.isSubTask()).thenReturn(false);
        Collection<Version> fixVersions=new ArrayList<Version>();
        fixVersions.add(mock(Version.class));
        fixVersions.add(mock(Version.class));
        when(issue.getFixVersions()).thenReturn(fixVersions);
        when(issue.getKey()).thenReturn("DEPL-3");
        Map map=new HashMap();
        map.put("issue",issue);
        PullRequestTaskValidator validator=new PullRequestTaskValidator();
        validator.validate(map,null,null);
    }



}