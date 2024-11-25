package eu.iba.auto_test.converterbnb.dao.repository.mapper;

import eu.iba.auto_test.converterbnb.dao.model.Employee;
import eu.iba.auto_test.converterbnb.dao.repository.sql.model.TaskGeneral;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskParentRowMapper implements RowMapper<TaskGeneral> {
    @Override
    public TaskGeneral mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            TaskGeneral model = new TaskGeneral();
            model.setId(rs.getLong("id"));

            Long authorId = rs.getObject("authorId", Long.class);
            if(authorId != null && authorId > 0) {
                Employee author = new Employee();
                author.setEmployeeId(authorId);
                author.setEmployeeFullName(rs.getString("authorFullName"));
                author.setEmployeeLoginAD(rs.getString("authorLoginAD"));
                model.setAuthor(author);
            }

            return model;
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }
}
