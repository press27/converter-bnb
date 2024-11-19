package eu.iba.auto_test.converterbnb.dao.model;

public enum TaskStatus {

    ON_EXECUTION("ON_EXECUTION"),           //На исполнении
    EXECUTED("EXECUTED");                   //Исполнено

    private final String taskStatus;

    TaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

}
