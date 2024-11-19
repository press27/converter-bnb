package eu.iba.auto_test.converterbnb.controller.data;

import eu.iba.auto_test.converterbnb.dao.model.AttachType;

public class AttachmentData {

    // id ркк
    private Long rkkId;

    //Тип вложения
    private AttachType attachType;

    // Id задачи
    private Long taskId;

    // Наименование вложения
    private String name;

    public AttachmentData() {
    }

    public AttachmentData(Long taskId, AttachType attachType) {
        this.taskId = taskId;
        this.attachType = attachType;
    }

    public Long getRkkId() {
        return rkkId;
    }

    public AttachmentData setRkkId(Long rkkId) {
        this.rkkId = rkkId;
        return this;
    }

    public AttachType getAttachType() {
        return attachType;
    }

    public AttachmentData setAttachType(AttachType attachType) {
        this.attachType = attachType;
        return this;
    }

    public Long getTaskId() {
        return taskId;
    }

    public AttachmentData setTaskId(Long taskId) {
        this.taskId = taskId;
        return this;
    }

    public String getName() {
        return name;
    }

    public AttachmentData setName(String name) {
        this.name = name;
        return this;
    }
}
