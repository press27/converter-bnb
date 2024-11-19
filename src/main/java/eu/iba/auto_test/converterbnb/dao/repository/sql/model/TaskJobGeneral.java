package eu.iba.auto_test.converterbnb.dao.repository.sql.model;

import eu.iba.auto_test.converterbnb.dao.model.Employee;

import java.util.Objects;

public class TaskJobGeneral {

    private Long id;

    private Long taskId;

    private Employee employee;

    public TaskJobGeneral() {
    }

    public Long getId() {
        return id;
    }

    public TaskJobGeneral setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getTaskId() {
        return taskId;
    }

    public TaskJobGeneral setTaskId(Long taskId) {
        this.taskId = taskId;
        return this;
    }

    public Employee getEmployee() {
        return employee;
    }

    public TaskJobGeneral setEmployee(Employee employee) {
        this.employee = employee;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskJobGeneral that = (TaskJobGeneral) o;
        return Objects.equals(id, that.id) && Objects.equals(taskId, that.taskId) && Objects.equals(employee, that.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taskId, employee);
    }
}
