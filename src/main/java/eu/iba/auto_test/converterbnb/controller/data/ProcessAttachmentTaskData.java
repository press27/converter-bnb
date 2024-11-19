package eu.iba.auto_test.converterbnb.controller.data;

import java.util.Set;

public class ProcessAttachmentTaskData {

    Set<Long> docCardIds;

    public ProcessAttachmentTaskData() {
    }

    public Set<Long> getDocCardIds() {
        return docCardIds;
    }

    public ProcessAttachmentTaskData setDocCardIds(Set<Long> docCardIds) {
        this.docCardIds = docCardIds;
        return this;
    }
}
