package eu.iba.auto_test.converterbnb.dao.repository.mapper;

import eu.iba.auto_test.converterbnb.dao.model.additional.UpdateDocumentType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateDocumentTypeMapper implements RowMapper<UpdateDocumentType> {

    @Override
    public UpdateDocumentType mapRow(ResultSet rs, int rowNum) throws SQLException {
        return null;
    }
}
