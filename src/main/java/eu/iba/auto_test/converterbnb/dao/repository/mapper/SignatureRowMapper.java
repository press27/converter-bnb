package eu.iba.auto_test.converterbnb.dao.repository.mapper;

import eu.iba.auto_test.converterbnb.dao.model.Author;
import eu.iba.auto_test.converterbnb.dao.model.Employee;
import eu.iba.auto_test.converterbnb.dao.model.Signature;
import eu.iba.auto_test.converterbnb.dao.model.UserType;
import eu.iba.auto_test.converterbnb.utils.UserUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class SignatureRowMapper implements RowMapper<Signature> {

    @Override
    public Signature mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            Signature model = new Signature();
            model.setId(rs.getLong("id"));
            model.setDocCardId(rs.getLong("docCardId"));
            model.setRkkId(rs.getLong("rkkId"));

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

            Timestamp signDate = rs.getTimestamp("signDate");
            if(signDate != null){
                model.setSignDate(signDate.toInstant());
            }

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
            model.setReplaceableUser(employee);

            model.setData(rs.getString("data"));
            model.setSignatureType(convertSignatureType(rs.getString("signatureType")));
            model.setComment(rs.getString("comment"));
            return model;
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    private String convertSignatureType(String signatureType) {
        return switch (signatureType) {
            case "В" -> "Визирующая";
            case "У" -> "Утверждающая";
            case "Н" -> "Нет";
            default -> "";
        };
    }

}
