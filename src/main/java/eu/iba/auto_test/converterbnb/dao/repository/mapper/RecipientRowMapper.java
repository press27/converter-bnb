package eu.iba.auto_test.converterbnb.dao.repository.mapper;

import eu.iba.auto_test.converterbnb.dao.model.Recipient;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RecipientRowMapper implements RowMapper<Recipient> {

    @Override
    public Recipient mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            Recipient model = new Recipient();
            model.setId(rs.getLong("id"));
            model.setRecipientName(rs.getString("recipientName"));
            return model;
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }
}
