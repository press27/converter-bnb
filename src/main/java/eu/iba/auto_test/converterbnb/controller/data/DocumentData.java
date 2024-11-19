package eu.iba.auto_test.converterbnb.controller.data;

import java.util.List;

public class DocumentData {

    // Список ids ркк
    private List<Long> rkkIds;

    public DocumentData() {
    }

    public List<Long> getRkkIds() {
        return rkkIds;
    }

    public DocumentData setRkkIds(List<Long> rkkIds) {
        this.rkkIds = rkkIds;
        return this;
    }
}
