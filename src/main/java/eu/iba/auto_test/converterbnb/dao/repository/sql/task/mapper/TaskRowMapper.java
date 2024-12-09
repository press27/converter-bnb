package eu.iba.auto_test.converterbnb.dao.repository.sql.task.mapper;

import eu.iba.auto_test.converterbnb.dao.model.Employee;
import eu.iba.auto_test.converterbnb.dao.repository.sql.task.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TaskRowMapper implements RowMapper<Task> {

    public static final Logger log = LoggerFactory.getLogger(TaskRowMapper.class);

    @Override
    public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            Task model = new Task();
            model.setId(rs.getLong("id"));
            Timestamp dateStart = rs.getTimestamp("dateStart");
            if(dateStart != null){
                model.setDateStart(dateStart.toInstant());
            }
            Timestamp dateEnd = rs.getTimestamp("dateEnd");
            if(dateEnd != null){
                model.setDateEnd(dateEnd.toInstant());
            }
            Timestamp finalDate = rs.getTimestamp("finalDate");
            if(finalDate != null){
                model.setFinalDate(finalDate.toInstant());
            }

            Long authorId = rs.getObject("authorId", Long.class);
            if(authorId != null && authorId > 0) {
                Employee author = new Employee();
                author.setEmployeeId(authorId);
                author.setEmployeeFullName(rs.getString("authorFullName"));
                author.setEmployeeLoginAD(rs.getString("authorLoginAD"));
                model.setAuthor(author);
            }
            model.setTopic(rs.getString("topic"));
            model.setStatus(convertStatus(rs.getString("status")));
            return model;
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

        private String convertStatus(String status) {
        if(status != null) {
            return switch (status) {
                case "B" -> "Прекращена";
                case "C" -> "На контроле";
                case "D" -> "Выполнена";
                case "I" -> "Инициализация";
                case "O" -> "Возобновлена";
                default -> "В работе";
            };
        } else {
            return "Неизвестный статус";
        }
    }

}
