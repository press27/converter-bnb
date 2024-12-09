package eu.iba.auto_test.converterbnb.dao.repository.sql.task.model;

import eu.iba.auto_test.converterbnb.dao.model.Employee;

import java.time.Instant;
import java.util.Objects;

public class Task {

    // ID задачи
    private Long id;

    // ID родительской задачи
    private Long parentTaskId;

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

    public Task() {
    }

    public Long getId() {
        return id;
    }

    public Task setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getParentTaskId() {
        return parentTaskId;
    }

    public Task setParentTaskId(Long parentTaskId) {
        this.parentTaskId = parentTaskId;
        return this;
    }

    public Instant getDateStart() {
        return dateStart;
    }

    public Task setDateStart(Instant dateStart) {
        this.dateStart = dateStart;
        return this;
    }

    public Instant getDateEnd() {
        return dateEnd;
    }

    public Task setDateEnd(Instant dateEnd) {
        this.dateEnd = dateEnd;
        return this;
    }

    public Instant getFinalDate() {
        return finalDate;
    }

    public Task setFinalDate(Instant finalDate) {
        this.finalDate = finalDate;
        return this;
    }

    public Employee getAuthor() {
        return author;
    }

    public Task setAuthor(Employee author) {
        this.author = author;
        return this;
    }

    public String getParentTopic() {
        return parentTopic;
    }

    public Task setParentTopic(String parentTopic) {
        this.parentTopic = parentTopic;
        return this;
    }

    public String getTopic() {
        return topic;
    }

    public Task setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Task setStatus(String status) {
        this.status = status;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) && Objects.equals(parentTaskId, task.parentTaskId) && Objects.equals(dateStart, task.dateStart) && Objects.equals(dateEnd, task.dateEnd) && Objects.equals(finalDate, task.finalDate) && Objects.equals(author, task.author) && Objects.equals(parentTopic, task.parentTopic) && Objects.equals(topic, task.topic) && Objects.equals(status, task.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parentTaskId, dateStart, dateEnd, finalDate, author, parentTopic, topic, status);
    }
}
