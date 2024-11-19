package eu.iba.auto_test.converterbnb.dao.repository.mapper;

import eu.iba.auto_test.converterbnb.dao.model.Employee;
import eu.iba.auto_test.converterbnb.dao.model.UserType;
import eu.iba.auto_test.converterbnb.dao.repository.sql.model.TaskJobGeneral;
import eu.iba.auto_test.converterbnb.utils.UserUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskJobRowMapper implements RowMapper<TaskJobGeneral> {

    @Override
    public TaskJobGeneral mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            TaskJobGeneral model = new TaskJobGeneral();
            model.setId(rs.getLong("id"));
            model.setTaskId(rs.getLong("taskId"));

            Employee employee = new Employee();
            Long employeeId = rs.getObject("employeeId", Long.class);
            if(employeeId != null) {
                employee.setEmployeeId(employeeId);
                employee.setEmployeeFullName(rs.getString("employeeFullName"));
                employee.setEmployeeLoginAD(rs.getString("employeeLoginAD"));
                employee.setUserType(UserUtils.getUserType(employeeId));
            } else {
                employee.setUserType(UserType.SYSTEM);
            }
            model.setEmployee(employee);

            return model;
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

}
