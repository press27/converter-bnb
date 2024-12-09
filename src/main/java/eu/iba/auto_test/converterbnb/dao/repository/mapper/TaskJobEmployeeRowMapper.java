package eu.iba.auto_test.converterbnb.dao.repository.mapper;

import eu.iba.auto_test.converterbnb.dao.model.Employee;
import eu.iba.auto_test.converterbnb.dao.repository.sql.model.TaskJobGeneral;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskJobEmployeeRowMapper implements RowMapper<TaskJobGeneral> {

    @Override
    public TaskJobGeneral mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            TaskJobGeneral model = new TaskJobGeneral();
            model.setId(rs.getLong("id"));
            model.setTaskId(rs.getLong("taskId"));

            Long employeeId = rs.getObject("employeeId", Long.class);
            if(employeeId != null && employeeId > 0) {
                Employee employee = new Employee();
                employee.setEmployeeId(employeeId);
                employee.setEmployeeFullName(rs.getString("employeeFullName"));
                employee.setEmployeeLoginAD(rs.getString("employeeLoginAD"));
                model.setEmployee(employee);
            }

            return model;
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

}
