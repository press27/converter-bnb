package eu.iba.auto_test.converterbnb.dao.repository.mapper;

import eu.iba.auto_test.converterbnb.dao.model.Employee;
import eu.iba.auto_test.converterbnb.dao.model.History;
import eu.iba.auto_test.converterbnb.utils.HistoryUtils;
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

            Long authorId = rs.getObject("authorId", Long.class);
            if(authorId != null && authorId > 0) {
                Employee author = new Employee();
                author.setEmployeeId(authorId);
                author.setEmployeeFullName(rs.getString("authorFullName"));
                author.setEmployeeLoginAD(rs.getString("authorLoginAD"));
                model.setAuthor(author);
            }

            String action = convertAction(rs.getString("action"));
            model.setAction(action);
            StringBuilder builder = new StringBuilder();
            if(!action.isEmpty()){
                builder.append(action);
            }
            String detail = rs.getString("detail");
            model.setDetail(detail);
            if(detail != null && !detail.isEmpty()){
                builder.append(":");
                builder.append("\n");
                builder.append(" ").append(HistoryUtils.textFormattingGeneralHistory(detail));
                model.setText(builder.toString());
            }
            return model;
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    private String convertAction(String action) {
        return switch (action) {
            case "С" -> "Создание";
            case "И" -> "Изменение";
            case "У" -> "Удаление";
            default -> "";
        };
    }

}
