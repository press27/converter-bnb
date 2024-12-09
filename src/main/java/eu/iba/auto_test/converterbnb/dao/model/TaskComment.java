package eu.iba.auto_test.converterbnb.dao.model;

import java.time.Instant;
import java.util.Objects;

public class TaskComment {

    // ID записи
    private Long id;

    // ID задачи
    private Long taskId;

    // ID подзадачи
    private Long jobId;

    // ID родительской задачи
    private Long parentTaskId;

    // Дата создания
    private Instant createDate;

    // Автор
    private Employee author;

    // Родительская тема
    private String parentTopic;

    // Тема
    private String topic;

    // Комментарий
    private String comment;

    // Статус
    private String status;

    public TaskComment() {
    }

    public Long getId() {
        return id;
    }

    public TaskComment setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getTaskId() {
        return taskId;
    }

    public TaskComment setTaskId(Long taskId) {
        this.taskId = taskId;
        return this;
    }

    public Long getJobId() {
        return jobId;
    }

    public TaskComment setJobId(Long jobId) {
        this.jobId = jobId;
        return this;
    }

    public Long getParentTaskId() {
        return parentTaskId;
    }

    public TaskComment setParentTaskId(Long parentTaskId) {
        this.parentTaskId = parentTaskId;
        return this;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public TaskComment setCreateDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public Employee getAuthor() {
        return author;
    }

    public TaskComment setAuthor(Employee author) {
        this.author = author;
        return this;
    }

    public String getParentTopic() {
        return parentTopic;
    }

    public TaskComment setParentTopic(String parentTopic) {
        this.parentTopic = parentTopic;
        return this;
    }

    public String getTopic() {
        return topic;
    }

    public TaskComment setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public TaskComment setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public TaskComment setStatus(String status) {
        this.status = status;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskComment that = (TaskComment) o;
        return Objects.equals(id, that.id) && Objects.equals(taskId, that.taskId) && Objects.equals(jobId, that.jobId) && Objects.equals(parentTaskId, that.parentTaskId) && Objects.equals(createDate, that.createDate) && Objects.equals(author, that.author) && Objects.equals(parentTopic, that.parentTopic) && Objects.equals(topic, that.topic) && Objects.equals(comment, that.comment) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taskId, jobId, parentTaskId, createDate, author, parentTopic, topic, comment, status);
    }
}
