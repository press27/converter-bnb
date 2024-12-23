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
            model.setName(rs.getString("nameCorrespondent"));
            model.setFullName(rs.getString("fullNameCorrespondent"));
            String unp = rs.getString("unp");
            if(unp != null && !unp.isEmpty()){
                model.setUnp(unp.trim());
            }
            model.setAddress(rs.getString("address"));
            model.setSmdoCode(rs.getString("smdoCode"));
            return model;
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }
}
