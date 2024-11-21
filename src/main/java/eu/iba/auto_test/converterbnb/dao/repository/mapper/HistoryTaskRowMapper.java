package eu.iba.auto_test.converterbnb.dao.repository.mapper;

import eu.iba.auto_test.converterbnb.dao.model.Author;
import eu.iba.auto_test.converterbnb.dao.model.History;
import eu.iba.auto_test.converterbnb.dao.model.UserType;
import eu.iba.auto_test.converterbnb.utils.HistoryUtils;
import eu.iba.auto_test.converterbnb.utils.UserUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class HistoryTaskRowMapper implements RowMapper<History> {
    @Override
    public History mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            History model = new History();
            model.setId(rs.getLong("id"));
            model.setTaskId(rs.getLong("taskId"));
            model.setJobId(rs.getLong("jobId"));
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
                builder.append(HistoryUtils.textFormattingGeneralHistory(detail));
                model.setText(builder.toString());
            }
            String detailInfo = rs.getString("detailInfo");
            model.setDetailInfo(detailInfo);
            if(detailInfo != null && !detailInfo.isEmpty()){
                StringBuilder builder = new StringBuilder();
                builder.append(action).append(":");
                builder.append("\n");
                builder.append(HistoryUtils.textFormattingTaskHistory(detailInfo));
                model.setText(builder.toString());
            }
            return model;
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    private String convertAction(String action) {
        return switch (action) {
            case "0" -> "Просмотр теневой копии";
            case "1" -> "Экспорт без блокировки";
            case "2" -> "Блокировка";
            case "3" -> "Импорт с разблокировкой";
            case "A" -> "Принятие";
            case "B" -> "Прекращение";
            case "C" -> "Создание";
            case "D" -> "Удаление";
            case "E" -> "Изменение";
            case "F" -> "Шифрование сертификатом";
            case "G" -> "Подписание";
            case "H" -> "Удаление версии";
            case "I" -> "Импорт без разблокировки";
            case "J" -> "Изменение стадии жизненного цикла";
            case "K" -> "Изменение задачи/задания";
            case "L" -> "Шифрование паролем и сертификатом";
            case "M" -> "Изменение состояния версии";
            case "N" -> "Отключение шифрования";
            case "O" -> "Изменение пароля";
            case "P" -> "Шифрование паролем";
            case "Q" -> "Восстановление из локальной копии";
            case "R" -> "Просмотр";
            case "S" -> "Старт";
            case "T" -> "Изменение хранилища";
            case "U" -> "Пометка как не прочитанного";
            case "V" -> "Пометка как прочитанного";
            case "W" -> "Изменение вида документа";
            case "X" -> "Выполнение";
            case "Y" -> "Изменение типа задачи/задания";
            case "Z" -> "Копирование";
            default -> "";
        };
    }

}