package eu.iba.auto_test.converterbnb.dao.repository.mapper;

import eu.iba.auto_test.converterbnb.dao.model.DocumentType;
import eu.iba.auto_test.converterbnb.dao.model.additional.UpdateDocumentType;
import eu.iba.auto_test.converterbnb.utils.DocumentUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class UpdateDocumentTypeGeneralMapper implements RowMapper<UpdateDocumentType> {

    @Override
    public UpdateDocumentType mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            UpdateDocumentType model = new UpdateDocumentType();
            model.setId(rs.getLong("id"));
            model.setDocumentCategoryConstants(DocumentUtils.convertDocumentCategoryConstants(rs.getString("documentCategoryConstants")));

            Long documentTypeId = rs.getObject("documentTypeId", Long.class);
            if(documentTypeId != null && documentTypeId > 0) {
                DocumentType documentType = new DocumentType();
                documentType.setDocumentTypeId(documentTypeId);
                documentType.setDocumentType(rs.getString("documentType"));
                model.setDocumentType(documentType);
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
