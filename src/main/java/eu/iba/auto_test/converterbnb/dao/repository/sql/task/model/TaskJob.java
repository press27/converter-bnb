package eu.iba.auto_test.converterbnb.dao.repository.sql.task.model;

import eu.iba.auto_test.converterbnb.dao.model.Employee;

import java.time.Instant;
import java.util.Objects;

public class TaskJob {

    // ID подзадачи
    private Long id;

    // ID родительской подзадачи
    private Long parentTaskJobId;

    // ID задачи
    private Long taskId;

    // Дата начала
    private Instant dateStart;

    // Дата конца
    private Instant dateEnd;

    // Конечный срок
    private Instant finalDate;

    // Автор
    private Employee author;

    // Родитеская Тема
    private String parentTopic;

    // Тема
    private String topic;

    // Статус
    private String status;

    public Long getId() {
        return id;
    }

    public TaskJob setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getParentTaskJobId() {
        return parentTaskJobId;
    }

    public TaskJob setParentTaskJobId(Long parentTaskJobId) {
        this.parentTaskJobId = parentTaskJobId;
        return this;
    }

    public Long getTaskId() {
        return taskId;
    }

    public TaskJob setTaskId(Long taskId) {
        this.taskId = taskId;
        return this;
    }

    public Instant getDateStart() {
        return dateStart;
    }

    public TaskJob setDateStart(Instant dateStart) {
        this.dateStart = dateStart;
        return this;
    }

    public Instant getDateEnd() {
        return dateEnd;
    }

    public TaskJob setDateEnd(Instant dateEnd) {
        this.dateEnd = dateEnd;
        return this;
    }

    public Instant getFinalDate() {
        return finalDate;
    }

    public TaskJob setFinalDate(Instant finalDate) {
        this.finalDate = finalDate;
        return this;
    }

    public Employee getAuthor() {
        return author;
    }

    public TaskJob setAuthor(Employee author) {
        this.author = author;
        return this;
    }

    public String getParentTopic() {
        return parentTopic;
    }

    public TaskJob setParentTopic(String parentTopic) {
        this.parentTopic = parentTopic;
        return this;
    }

    public String getTopic() {
        return topic;
    }

    public TaskJob setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public TaskJob setStatus(String status) {
        this.status = status;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskJob taskJob = (TaskJob) o;
        return Objects.equals(id, taskJob.id) && Objects.equals(parentTaskJobId, taskJob.parentTaskJobId) && Objects.equals(taskId, taskJob.taskId) && Objects.equals(dateStart, taskJob.dateStart) && Objects.equals(dateEnd, taskJob.dateEnd) && Objects.equals(finalDate, taskJob.finalDate) && Objects.equals(author, taskJob.author) && Objects.equals(parentTopic, taskJob.parentTopic) && Objects.equals(topic, taskJob.topic) && Objects.equals(status, taskJob.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parentTaskJobId, taskId, dateStart, dateEnd, finalDate, author, parentTopic, topic, status);
    }
}
