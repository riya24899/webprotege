package edu.stanford.bmir.protege.web.server.dispatch.validators;

import javax.inject.Inject;
import edu.stanford.bmir.protege.web.server.dispatch.RequestContext;
import edu.stanford.bmir.protege.web.server.dispatch.RequestValidationResult;
import edu.stanford.bmir.protege.web.server.dispatch.RequestValidator;
import edu.stanford.bmir.protege.web.server.owlapi.OWLAPIProjectDocumentStore;
import edu.stanford.bmir.protege.web.shared.HasProjectId;
import edu.stanford.bmir.protege.web.shared.dispatch.Action;
import edu.stanford.bmir.protege.web.shared.dispatch.Result;
import edu.stanford.bmir.protege.web.shared.project.ProjectId;

/**
 * Author: Matthew Horridge<br>
 * Stanford University<br>
 * Bio-Medical Informatics Research Group<br>
 * Date: 20/02/2013
 */
public class ProjectExistsValidator implements RequestValidator {

    private final ProjectId projectId;

    @Inject
    public ProjectExistsValidator(ProjectId projectId) {
        this.projectId = projectId;
    }

    @Override
    public RequestValidationResult validateAction() {
        return RequestValidationResult.getValid();
//        ProjectId projectId = action.getProjectId();
//        OWLAPIProjectDocumentStore ds = OWLAPIProjectDocumentStore.getProjectDocumentStore(projectId);
//        if(ds.exists()) {
//            return RequestValidationResult.getValid();
//        }
//        else {
//            return RequestValidationResult.getInvalid("Project does not exist");
//        }
    }
}
