package eu.iba.auto_test.converterbnb.controller.data;

public class HistoryTaskData {

    // Id поручения
    private Long taskId;

    public HistoryTaskData() {
    }

    public Long getTaskId() {
        return taskId;
    }

    public HistoryTaskData setTaskId(Long taskId) {
        this.taskId = taskId;
        return this;
    }
}
