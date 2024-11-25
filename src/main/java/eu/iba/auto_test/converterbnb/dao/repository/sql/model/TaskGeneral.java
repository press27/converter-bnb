package eu.iba.auto_test.converterbnb.dao.repository.sql.model;

import eu.iba.auto_test.converterbnb.dao.model.Employee;

import java.util.Objects;
import java.util.Set;

public class TaskGeneral {

    private Long id;

    private Long parentLeaderId;

    private Employee author;

    Set<TaskGeneral> taskChildList;

    public TaskGeneral() {
    }

    public Long getId() {
        return id;
    }

    public TaskGeneral setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getParentLeaderId() {
        return parentLeaderId;
    }

    public TaskGeneral setParentLeaderId(Long parentLeaderId) {
        this.parentLeaderId = parentLeaderId;
        return this;
    }

    public Employee getAuthor() {
        return author;
    }

    public TaskGeneral setAuthor(Employee author) {
        this.author = author;
        return this;
    }

    public Set<TaskGeneral> getTaskChildList() {
        return taskChildList;
    }

    public TaskGeneral setTaskChildList(Set<TaskGeneral> taskChildList) {
        this.taskChildList = taskChildList;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskGeneral that = (TaskGeneral) o;
        return Objects.equals(id, that.id) && Objects.equals(parentLeaderId, that.parentLeaderId) && Objects.equals(author, that.author) && Objects.equals(taskChildList, that.taskChildList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parentLeaderId, author, taskChildList);
    }
}
