package eu.iba.auto_test.converterbnb.dao.repository.mapper;

import eu.iba.auto_test.converterbnb.dao.model.Employee;
import eu.iba.auto_test.converterbnb.dao.model.History;
import eu.iba.auto_test.converterbnb.utils.HistoryUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class HistoryDocumentRowMapper implements RowMapper<History> {
    @Override
    public History mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            History model = new History();
            model.setId(rs.getLong("id"));
            model.setDocCardId(rs.getLong("docCardId"));
            model.setRkkId(rs.getLong("rkkId"));
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
                builder.append(HistoryUtils.textFormattingGeneralHistory(detail));
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
            case "6" -> "Пусто"; //TODO как узнаю заменить
            case "7" -> "Пусто"; //TODO как узнаю заменить
            case "A" -> "Изменение прав доступа";
            case "B" -> "Изменение видимости версии";
            case "C" -> "Создание";
            case "D" -> "Удаление";
            case "E" -> "Изменение";
            case "F" -> "Шифрование сертификатом";
            case "G" -> "Подписание";
            case "H" -> "Удаление версии";
            case "I" -> "Импорт без разблокировки";
            case "J" -> "Изменение стадии жизненного цикла";
            case "K" -> "Изменение карточки";
            case "L" -> "Шифрование паролем и сертификатом";
            case "M" -> "Изменение состояния версии";
            case "N" -> "Отключение шифрования";
            case "O" -> "Изменение пароля";
            case "P" -> "Шифрование паролем";
            case "Q" -> "Восстановление из локальной копии";
            case "R" -> "Просмотр";
            case "S" -> "Отправка по почте";
            case "T" -> "Изменение хранилища";
            case "U" -> "Разблокировка";
            case "V" -> "Создание версии";
            case "W" -> "Изменение вида документа";
            case "X" -> "Экспорт с блокировкой";
            case "Y" -> "Изменение типа карточки";
            case "Z" -> "Копирование";
            default -> "";
        };
    }

}
