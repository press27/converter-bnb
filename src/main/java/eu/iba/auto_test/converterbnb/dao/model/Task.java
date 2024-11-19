package eu.iba.auto_test.converterbnb.dao.model;

import java.time.Instant;
import java.util.List;
import java.util.Set;

// Поручение
public class Task {

    // Id поручения
    private Long id;

    // Id ркк
    private Long rkkId;

    // Тип поручения Directum
    private TaskTypeDirectum typeDirectum;

    // Тип поручения
    private TaskType type;

    // Номер поручения
    private String taskNumber;

    // Дата создания поручения
    private Instant createDate;

    // Текст поручения
    private String taskText;

    // Планируемая дата исполнения поручения
    private Instant planedDateEnd;

    // Реальная дата исполнения
    private Instant realDateEnd;

    // Статус поручения Directum
    private TaskStatusDirectum taskStatusDirectum;

    // Статус поручения
    private TaskStatus taskStatus;

    // Автор поручения
    private Author author;

    // Id родительского поручения
    private Long parentId;

    // Список исполнителей
    private Set<TaskExecutors> taskExecutors;

    // История поручений
    private List<History> taskHistory;


    public Long getId() {
        return id;
    }

    public Task setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getRkkId() {
        return rkkId;
    }

    public Task setRkkId(Long rkkId) {
        this.rkkId = rkkId;
        return this;
    }

    public TaskTypeDirectum getTypeDirectum() {
        return typeDirectum;
    }

    public Task setTypeDirectum(TaskTypeDirectum typeDirectum) {
        this.typeDirectum = typeDirectum;
        return this;
    }

    public TaskType getType() {
        return type;
    }

    public Task setType(TaskType type) {
        this.type = type;
        return this;
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    public Task setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
        return this;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public Task setCreateDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public String getTaskText() {
        return taskText;
    }

    public Task setTaskText(String taskText) {
        this.taskText = taskText;
        return this;
    }

    public Instant getPlanedDateEnd() {
        return planedDateEnd;
    }

    public Task setPlanedDateEnd(Instant planedDateEnd) {
        this.planedDateEnd = planedDateEnd;
        return this;
    }

    public Instant getRealDateEnd() {
        return realDateEnd;
    }

    public Task setRealDateEnd(Instant realDateEnd) {
        this.realDateEnd = realDateEnd;
        return this;
    }

    public TaskStatusDirectum getTaskStatusDirectum() {
        return taskStatusDirectum;
    }

    public Task setTaskStatusDirectum(TaskStatusDirectum taskStatusDirectum) {
        this.taskStatusDirectum = taskStatusDirectum;
        return this;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public Task setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
        return this;
    }

    public Author getAuthor() {
        return author;
    }

    public Task setAuthor(Author author) {
        this.author = author;
        return this;
    }

    public Long getParentId() {
        return parentId;
    }

    public Task setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    public Set<TaskExecutors> getTaskExecutors() {
        return taskExecutors;
    }

    public Task setTaskExecutors(Set<TaskExecutors> taskExecutors) {
        this.taskExecutors = taskExecutors;
        return this;
    }

    public List<History> getTaskHistory() {
        return taskHistory;
    }

    public Task setTaskHistory(List<History> taskHistory) {
        this.taskHistory = taskHistory;
        return this;
    }
}
