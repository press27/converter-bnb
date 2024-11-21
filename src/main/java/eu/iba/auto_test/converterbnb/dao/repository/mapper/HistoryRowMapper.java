package eu.iba.auto_test.converterbnb.dao.repository.mapper;

import eu.iba.auto_test.converterbnb.dao.model.Author;
import eu.iba.auto_test.converterbnb.dao.model.History;
import eu.iba.auto_test.converterbnb.dao.model.UserType;
import eu.iba.auto_test.converterbnb.utils.UserUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class HistoryRowMapper implements RowMapper<History> {

    @Override
    public History mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            History model = new History();
            Long generalId = rs.getObject("generalId", Long.class);
            Long referenceTypeId = rs.getObject("referenceTypeId", Long.class); // 3174 - ркк (внутр, вх, исх), 3329 - ркк (обр.), 3342 - поручения
            if(referenceTypeId != null && (referenceTypeId == 3174 || referenceTypeId == 3329)){
                model.setRkkId(generalId);
            } else if(referenceTypeId != null && referenceTypeId == 3342){
                model.setTaskId(generalId);
            }

            Timestamp dateAction = rs.getTimestamp("dateAction");
            if(dateAction != null){
                model.setDateAction(dateAction.toInstant());
            }

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

            String action = convertAction(rs.getString("action"));
            model.setAction(action);
            String detail = rs.getString("detail");
            model.setDetail(detail);
            if(detail != null && !detail.isEmpty()){
                StringBuilder builder = new StringBuilder();
                builder.append(action).append(":");
                builder.append("\n");
                builder.append(" ").append(detail);
                model.setText(builder.toString());
            }
            return model;
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    private String convertAction(String action) {
        return switch (action) {
            case "C" -> "Создание";
            case "И" -> "Изменение";
            case "У" -> "Удаление";
            default -> "";
        };
    }

}