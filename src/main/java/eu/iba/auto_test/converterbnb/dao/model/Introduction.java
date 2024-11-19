package eu.iba.auto_test.converterbnb.dao.model;

import java.time.Instant;
import java.util.Objects;

// Ознакомление
public class Introduction {

    // Идентификатор листа ознакомления
    // Id job
    private Long id;

    // Id task
    private Long taskId;

    // Дата создания ознакомления
    private Instant createDate;

    // Автор создания ознакомления
    private Author author;

    // Дата ознакомления
    private Instant introductionDate;

    // Ознакамливающийся
    // Сотрудник который проставил отметку об ознакомлении
    private Employee introductionStampAuthor;

    // Комментарий при постановке на ознакомление
    private String comment;

    public Introduction() {
    }

    public Long getId() {
        return id;
    }

    public Introduction setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getTaskId() {
        return taskId;
    }

    public Introduction setTaskId(Long taskId) {
        this.taskId = taskId;
        return this;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public Introduction setCreateDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public Author getAuthor() {
        return author;
    }

    public Introduction setAuthor(Author author) {
        this.author = author;
        return this;
    }

    public Instant getIntroductionDate() {
        return introductionDate;
    }

    public Introduction setIntroductionDate(Instant introductionDate) {
        this.introductionDate = introductionDate;
        return this;
    }

    public Employee getIntroductionStampAuthor() {
        return introductionStampAuthor;
    }

    public Introduction setIntroductionStampAuthor(Employee introductionStampAuthor) {
        this.introductionStampAuthor = introductionStampAuthor;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public Introduction setComment(String comment) {
        this.comment = comment;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Introduction that = (Introduction) o;
        return Objects.equals(id, that.id) && Objects.equals(taskId, that.taskId) && Objects.equals(createDate, that.createDate) && Objects.equals(author, that.author) && Objects.equals(introductionDate, that.introductionDate) && Objects.equals(introductionStampAuthor, that.introductionStampAuthor) && Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taskId, createDate, author, introductionDate, introductionStampAuthor, comment);
    }
}
