package eu.iba.auto_test.converterbnb.dao.repository.mapper;

import eu.iba.auto_test.converterbnb.dao.model.Correspondent;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CorrespondentRowMapper implements RowMapper<Correspondent> {

    @Override
    public Correspondent mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            Correspondent model = new Correspondent();
            model.setId(rs.getLong("id"));
            model.setName(rs.getString("nameCorrespondent").trim());
            model.setFullName(rs.getString("fullNameCorrespondent").trim());
            model.setUnp(rs.getString("unp").trim());
            model.setAddress(rs.getString("address").trim());
            return model;
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }
}
