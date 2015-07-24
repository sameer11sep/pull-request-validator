package com.xebialabs;

import com.atlassian.jira.issue.Issue;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.InvalidInputException;
import com.opensymphony.workflow.Validator;
import com.opensymphony.workflow.WorkflowException;
import org.eclipse.egit.github.core.PullRequest;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.service.PullRequestService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * The Validator class for checking if PRs are associated with the JIRA Id.
 */
public class PullRequestTaskValidator implements Validator {

    private final static String ACCESS_TOKEN = "GITHUB_OAUTH_ACCESS_TOKEN";

    @Override
    public void validate(Map transientVars, Map args, PropertySet propertySet) throws InvalidInputException, WorkflowException {
        RepositoryId repositoryId = new RepositoryId("sameer11sep", "Refactoring");
        Issue issue = (Issue) transientVars.get("issue");
        final Integer fixVersions = issue.getFixVersions().size();
        int associatedPRs=0;
        if (!issue.isSubTask()) {
            associatedPRs = getAssociatedPRs(repositoryId, issue, associatedPRs);
            if(associatedPRs == 0){
                throw new WorkflowException("There is no Pull Request Associated with this Task, please create a pull request before moving it to Test");
            }else if(associatedPRs < fixVersions){
                throw new WorkflowException("There should be at least one Pull Request for each Fix Version");
            }
        }
    }

    private int getAssociatedPRs(RepositoryId repositoryId, Issue issue, int associatedPRs) {
        PullRequestService service = new PullRequestService();
        service.getClient().setOAuth2Token(System.getenv(ACCESS_TOKEN));
        try {
            final List<PullRequest> pullRequests = service.getPullRequests(repositoryId, "open");
            for (PullRequest pullRequest:pullRequests){
                if(pullRequest.getTitle().toLowerCase().contains(issue.getKey().toLowerCase())){
                    associatedPRs++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return associatedPRs;
    }
}
