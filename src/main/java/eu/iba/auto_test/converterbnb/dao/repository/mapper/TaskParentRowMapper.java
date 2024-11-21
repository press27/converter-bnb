package eu.iba.auto_test.converterbnb.dao.repository.mapper;

import eu.iba.auto_test.converterbnb.dao.model.Author;
import eu.iba.auto_test.converterbnb.dao.model.UserType;
import eu.iba.auto_test.converterbnb.dao.repository.sql.model.TaskGeneral;
import eu.iba.auto_test.converterbnb.utils.UserUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskParentRowMapper implements RowMapper<TaskGeneral> {
    @Override
    public TaskGeneral mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            TaskGeneral model = new TaskGeneral();
            model.setId(rs.getLong("id"));

            Author author = new Author();
            Long authorId = rs.getObject("authorId", Long.class);
            if(authorId != null) {
                author.setAuthorId(authorId);
                author.setAuthorFullName(rs.getString("authorFullName"));
                author.setAuthorLoginAD(rs.getString("authorLoginAD"));
                author.setUserType(UserUtils.getUserType(authorId));
            } else {
                author.setUserType(UserType.SYSTEM);
            }
            model.setAuthor(author);

            return model;
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }
}