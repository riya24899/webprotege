package edu.stanford.bmir.protege.web.server.change.matcher;

import javax.inject.Inject;
import com.google.inject.TypeLiteral;
import edu.stanford.bmir.protege.web.server.owlapi.OWLObjectStringFormatter;
import org.semanticweb.owlapi.io.OWLObjectRenderer;
import org.semanticweb.owlapi.model.OWLPropertyDomainAxiom;

import java.util.Optional;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 16/03/16
 */
public class PropertyDomainAxiomChangeMatcher extends AbstractAxiomMatcher<OWLPropertyDomainAxiom<?>> {

    private final OWLObjectStringFormatter formatter;

    @Inject
    public PropertyDomainAxiomChangeMatcher(OWLObjectStringFormatter formatter) {
        super(new TypeLiteral<OWLPropertyDomainAxiom<?>>(){});
        this.formatter = formatter;
    }

    @Override
    protected Optional<String> getDescriptionForAddAxiomChange(OWLPropertyDomainAxiom<?> axiom) {
        return formatter.format("Added %s to the domain of %s", axiom.getDomain(), axiom.getProperty());
    }

    @Override
    protected Optional<String> getDescriptionForRemoveAxiomChange(OWLPropertyDomainAxiom<?> axiom) {
        return formatter.format("Removed %s from the domain of %s", axiom.getDomain(), axiom.getProperty());
    }
}
