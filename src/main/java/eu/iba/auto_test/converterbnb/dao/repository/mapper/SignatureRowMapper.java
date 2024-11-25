package eu.iba.auto_test.converterbnb.dao.repository.mapper;

import eu.iba.auto_test.converterbnb.dao.model.Author;
import eu.iba.auto_test.converterbnb.dao.model.Employee;
import eu.iba.auto_test.converterbnb.dao.model.Signature;
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
            //model.setRkkId(rs.getLong("rkkId"));

            Long authorId = rs.getObject("authorId", Long.class);
            if(authorId != null && authorId > 0) {
                Author author = new Author();
                author.setAuthorId(authorId);
                author.setAuthorFullName(rs.getString("authorFullName"));
                author.setAuthorLoginAD(rs.getString("authorLoginAD"));
                model.setAuthor(author);
            }

            Timestamp signDate = rs.getTimestamp("signDate");
            if(signDate != null){
                model.setSignDate(signDate.toInstant());
            }

            Long employeeId = rs.getObject("employeeId", Long.class);
            if(employeeId != null && employeeId > 0) {
                Employee employee = new Employee();
                employee.setEmployeeId(employeeId);
                employee.setEmployeeFullName(rs.getString("employeeFullName"));
                employee.setEmployeeLoginAD(rs.getString("employeeLoginAD"));
                model.setReplaceableUser(employee);
            }

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
