package eu.iba.auto_test.converterbnb.dao.repository.mapper;

import eu.iba.auto_test.converterbnb.dao.model.*;
import eu.iba.auto_test.converterbnb.dao.model.additional.UpdateDocumentType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class UpdateCitizenTypeMapper implements RowMapper<UpdateDocumentType> {
    @Override
    public UpdateDocumentType mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            UpdateDocumentType model = new UpdateDocumentType();
            Long id = rs.getObject("id", Long.class);
            if(id != null){
                model.setId(id);
            }
            model.setDocumentCategoryConstants(DocumentCategoryConstants.APPEAL);

            Long citizenTypeId = rs.getObject("citizenTypeId", Long.class);
            if(citizenTypeId != null && citizenTypeId > 0){
                CitizenType citizenType = new CitizenType();
                citizenType.setCitizenTypeId(citizenTypeId);
                citizenType.setCitizenType(rs.getString("citizenType"));
                model.setCitizenType(citizenType);
            }

            Timestamp regDate = rs.getTimestamp("regDate");
            if(regDate != null){
                model.setRegDate(regDate.toInstant());
            }

            String regNumber = rs.getString("regNumber");
            if(regDate != null && (regNumber == null || regNumber.isEmpty())){
                model.setRegDate(null);
            } else {
                model.setRegNumber(regNumber);
            }
            return model;
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }
}
