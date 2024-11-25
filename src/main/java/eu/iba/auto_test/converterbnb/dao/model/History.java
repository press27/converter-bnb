package eu.iba.auto_test.converterbnb.dao.model;

import java.time.Instant;
import java.util.Objects;

// История для РРК, Поручений, Задач, Заданий
public class History {

    // Id записи XRecID
    private Long id;

    // Id rkk
    private Long rkkId;

    // Id карточки документа
    private Long docCardId;

    // Id задачи
    private Long taskId;

    // Id задания
    private Long jobId;

    // Действие
    private String action;

    // Дата действия
    private Instant dateAction;

    // Автор действия
    private Employee author;

    // Подробности. Значения основных полей (ИД записи, Имя, Заголовок)
    private String detail;

    // Доп. информация
    private String detailInfo;

    // Полный текст истории для сохраенния в нашей системе
    private String text;

    // Историяя поручения или ркк в зависимости от этого referenceTypeId
    // 3174 - ркк (внутр, вх, исх), 3329 - ркк (обр.), 3342 - поручения
    //private Long referenceTypeId;

    public History() {
    }

    public Long getId() {
        return id;
    }

    public History setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getRkkId() {
        return rkkId;
    }

    public History setRkkId(Long rkkId) {
        this.rkkId = rkkId;
        return this;
    }

    public Long getDocCardId() {
        return docCardId;
    }

    public History setDocCardId(Long docCardId) {
        this.docCardId = docCardId;
        return this;
    }

    public Long getTaskId() {
        return taskId;
    }

    public History setTaskId(Long taskId) {
        this.taskId = taskId;
        return this;
    }

    public Long getJobId() {
        return jobId;
    }

    public History setJobId(Long jobId) {
        this.jobId = jobId;
        return this;
    }

    public String getAction() {
        return action;
    }

    public History setAction(String action) {
        this.action = action;
        return this;
    }

    public Instant getDateAction() {
        return dateAction;
    }

    public History setDateAction(Instant dateAction) {
        this.dateAction = dateAction;
        return this;
    }

    public Employee getAuthor() {
        return author;
    }

    public History setAuthor(Employee author) {
        this.author = author;
        return this;
    }

    public String getDetail() {
        return detail;
    }

    public History setDetail(String detail) {
        this.detail = detail;
        return this;
    }

    public String getDetailInfo() {
        return detailInfo;
    }

    public History setDetailInfo(String detailInfo) {
        this.detailInfo = detailInfo;
        return this;
    }

    public String getText() {
        return text;
    }

    public History setText(String text) {
        this.text = text;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        History history = (History) o;
        return Objects.equals(id, history.id) && Objects.equals(rkkId, history.rkkId) && Objects.equals(docCardId, history.docCardId) && Objects.equals(taskId, history.taskId) && Objects.equals(jobId, history.jobId) && Objects.equals(action, history.action) && Objects.equals(dateAction, history.dateAction) && Objects.equals(author, history.author) && Objects.equals(detail, history.detail) && Objects.equals(detailInfo, history.detailInfo) && Objects.equals(text, history.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rkkId, docCardId, taskId, jobId, action, dateAction, author, detail, detailInfo, text);
    }
}
