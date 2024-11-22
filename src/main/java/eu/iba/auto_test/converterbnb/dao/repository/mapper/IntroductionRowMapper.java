package eu.iba.auto_test.converterbnb.dao.repository.mapper;

import eu.iba.auto_test.converterbnb.dao.model.Author;
import eu.iba.auto_test.converterbnb.dao.model.Employee;
import eu.iba.auto_test.converterbnb.dao.model.Introduction;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class IntroductionRowMapper implements RowMapper<Introduction> {
    @Override
    public Introduction mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            Introduction model = new Introduction();
            model.setId(rs.getLong("id"));
            model.setTaskId(rs.getLong("taskId"));
            Timestamp createDate = rs.getTimestamp("createDate");
            if(createDate != null){
                model.setCreateDate(createDate.toInstant());
            }

            Long authorId = rs.getObject("authorId", Long.class);
            if(authorId != null && authorId > 0) {
                Author author = new Author();
                author.setAuthorId(authorId);
                author.setAuthorFullName(rs.getString("authorFullName"));
                author.setAuthorLoginAD(rs.getString("authorLoginAD"));
                model.setAuthor(author);
            }

            Timestamp introductionDate = rs.getTimestamp("introductionDate");
            if(introductionDate != null){
                model.setIntroductionDate(introductionDate.toInstant());
            }

            Long employeeId = rs.getObject("employeeId", Long.class);
            if(employeeId != null && employeeId > 0) {
                Employee executor = new Employee();
                executor.setEmployeeId(employeeId);
                executor.setEmployeeFullName(rs.getString("employeeFullName"));
                executor.setEmployeeLoginAD(rs.getString("employeeLoginAD"));
                model.setIntroductionStampAuthor(executor);
            }

            model.setComment(rs.getString("comment"));
            return model;
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

//    private IntroductionStatus convertStatus(String status) {
//        return switch (status) {
//            case "D" -> IntroductionStatus.COMPLETED;
//            case "B" -> IntroductionStatus.DISCONTINUED;
//            default -> IntroductionStatus.IN_WORK;
//        };
//    }
}
