package edu.stanford.bmir.protege.web.client.form;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import edu.stanford.bmir.protege.web.client.editor.ValueEditor;
import edu.stanford.bmir.protege.web.client.editor.ValueListEditor;
import edu.stanford.bmir.protege.web.client.editor.ValueListFlexEditorImpl;
import edu.stanford.bmir.protege.web.client.primitive.PrimitiveDataEditor;
import edu.stanford.bmir.protege.web.client.primitive.PrimitiveDataEditorView;
import edu.stanford.bmir.protege.web.shared.DataFactory;
import edu.stanford.bmir.protege.web.shared.DirtyChangedEvent;
import edu.stanford.bmir.protege.web.shared.DirtyChangedHandler;
import edu.stanford.bmir.protege.web.shared.PrimitiveType;
import edu.stanford.bmir.protege.web.shared.entity.OWLLiteralData;
import edu.stanford.bmir.protege.web.shared.entity.OWLPrimitiveData;
import edu.stanford.bmir.protege.web.shared.lang.LanguageMap;
import org.semanticweb.owlapi.model.OWLLiteral;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2019-11-16
 */
public class LanguageMapEditor extends Composite implements ValueEditor<LanguageMap> {

    private static LanguageMapEditorUiBinder ourUiBinder = GWT.create(LanguageMapEditorUiBinder.class);

    @UiField(provided = true)
    ValueListFlexEditorImpl<LanguageMapEntry> delegate;

    @Inject
    public LanguageMapEditor(Provider<LanguageMapEntryPresenter> presenterProvider) {
        delegate = new ValueListFlexEditorImpl<>(presenterProvider::get);
        delegate.setEnabled(true);
        delegate.setNewRowMode(ValueListEditor.NewRowMode.MANUAL);
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @Override
    public HandlerRegistration addDirtyChangedHandler(DirtyChangedHandler handler) {
        return addHandler(handler, DirtyChangedEvent.TYPE);
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Optional<LanguageMap>> handler) {
        return addHandler(handler, ValueChangeEvent.getType());
    }

    @Override
    public void clearValue() {
        delegate.clearValue();
    }

    @Override
    public Optional<LanguageMap> getValue() {
        Map<String, String> map = delegate.getValue()
                .orElse(Collections.emptyList())
                .stream()
                .collect(Collectors.toMap(
                        LanguageMapEntry::getLangTag,
                        LanguageMapEntry::getValue
                ));
        return Optional.of(LanguageMap.get(map));
    }

    @Override
    public void setValue(LanguageMap object) {
        List<LanguageMapEntry> entries = object.asMap()
                                               .entrySet()
                                               .stream()
                                               .map(entry -> {
                                                   String langTag = entry.getKey();
                                                   String value = entry.getValue();
                                                   return LanguageMapEntry.get(langTag, value);
                                               })
                                               .collect(toList());
        delegate.setValue(entries);
    }

    @Override
    public boolean isDirty() {
        return delegate.isDirty();
    }

    @Override
    public boolean isWellFormed() {
        return delegate.isWellFormed();
    }

    interface LanguageMapEditorUiBinder extends UiBinder<HTMLPanel, LanguageMapEditor> {

    }
}