package eu.iba.auto_test.converterbnb.dao.model;

import java.time.Instant;

// Исполнитель поручения
public class TaskExecutors {

    // Id пользователя (исполнителя)
    private Long id;

    // Id поручения
    private Long taskId;

    // Id rkk
    private Long rkkId;

    // Дата создания
    private Instant createDate;

    // Признак ответственного исполнителя
    private Boolean responsible;

    // Дата исполнения
    private Instant executedDate;

    // Текст исполнения
    private String stampText;

    // Исполнитель поручения
    private Employee executor;

    // Номер исполнителя (Порядковый номер в таблице исполнения в Directum)
    private Long number;

    // Статус исполнения Directum
    TaskExecutorsStatusDirectum taskExecutorsStatusDirectum;

    // parentTaskId
    private Long parentTaskId;

    // Хранит ссылки на другие документы БД приложения СЭД
    // private List<TaskDocumentLink> taskDocumentLinks;

    // Файлы исполнения
    //private List<AttachmentTask> attachments;


    public Long getId() {
        return id;
    }

    public TaskExecutors setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getRkkId() {
        return rkkId;
    }

    public TaskExecutors setRkkId(Long rkkId) {
        this.rkkId = rkkId;
        return this;
    }

    public Long getTaskId() {
        return taskId;
    }

    public TaskExecutors setTaskId(Long taskId) {
        this.taskId = taskId;
        return this;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public TaskExecutors setCreateDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public Boolean getResponsible() {
        return responsible;
    }

    public TaskExecutors setResponsible(Boolean responsible) {
        this.responsible = responsible;
        return this;
    }

    public Instant getExecutedDate() {
        return executedDate;
    }

    public TaskExecutors setExecutedDate(Instant executedDate) {
        this.executedDate = executedDate;
        return this;
    }

    public String getStampText() {
        return stampText;
    }

    public TaskExecutors setStampText(String stampText) {
        this.stampText = stampText;
        return this;
    }

    public Employee getExecutor() {
        return executor;
    }

    public TaskExecutors setExecutor(Employee executor) {
        this.executor = executor;
        return this;
    }

    public TaskExecutorsStatusDirectum getTaskExecutorsStatusDirectum() {
        return taskExecutorsStatusDirectum;
    }

    public TaskExecutors setTaskExecutorsStatusDirectum(TaskExecutorsStatusDirectum taskExecutorsStatusDirectum) {
        this.taskExecutorsStatusDirectum = taskExecutorsStatusDirectum;
        return this;
    }

    public Long getNumber() {
        return number;
    }

    public TaskExecutors setNumber(Long number) {
        this.number = number;
        return this;
    }

    public Long getParentTaskId() {
        return parentTaskId;
    }

    public TaskExecutors setParentTaskId(Long parentTaskId) {
        this.parentTaskId = parentTaskId;
        return this;
    }
}
