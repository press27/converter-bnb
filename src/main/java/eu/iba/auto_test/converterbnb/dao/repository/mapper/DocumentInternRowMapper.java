package eu.iba.auto_test.converterbnb.dao.repository.mapper;

import eu.iba.auto_test.converterbnb.dao.model.*;
import eu.iba.auto_test.converterbnb.utils.DocumentUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DocumentInternRowMapper implements RowMapper<Document> {

    @Override
    public Document mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            Document model = new Document();
            model.setId(rs.getLong("id"));
            model.setDocumentCategoryConstants(DocumentUtils.convertDocumentCategoryConstants(rs.getString("documentCategoryConstants")));

            model.setShortSummary(rs.getString("shortSummary"));

            Long documentTypeId = rs.getObject("documentTypeId", Long.class);
            if(documentTypeId != null && documentTypeId > 0) {
                DocumentType documentType = new DocumentType();
                documentType.setDocumentTypeId(documentTypeId);
                documentType.setDocumentType(rs.getString("documentType"));
                model.setDocumentType(documentType);
            }

            Long authorId = rs.getObject("authorId", Long.class);
            if(authorId != null && authorId > 0) {
                Employee author = new Employee();
                author.setEmployeeId(authorId);
                author.setEmployeeFullName(rs.getString("authorFullName"));
                author.setEmployeeLoginAD(rs.getString("authorLoginAD"));
                model.setAuthor(author);
            }

            model.setRegNumber(rs.getString("regNumber"));
            Timestamp regDate = rs.getTimestamp("regDate");
            if(regDate != null){
                model.setRegDate(regDate.toInstant());
            }

            Long nomenclatureAffairDocumentId = rs.getObject("nomenclatureAffairId", Long.class);
            if(nomenclatureAffairDocumentId != null && nomenclatureAffairDocumentId > 0) {
                NomenclatureAffairDocument nomenclatureAffairDocument = new NomenclatureAffairDocument();
                nomenclatureAffairDocument.setNomenclatureAffairId(nomenclatureAffairDocumentId);
                nomenclatureAffairDocument.setNomenclatureAffairName(rs.getString("nomenclatureAffairName"));
                Timestamp nomenclatureAffairDate = rs.getTimestamp("nomenclatureAffairDate");
                if (nomenclatureAffairDate != null) {
                    nomenclatureAffairDocument.setNomenclatureAffairDate(nomenclatureAffairDate.toInstant());
                }
                model.setNomenclatureAffairDocument(nomenclatureAffairDocument);
            }

            Long employeeId = rs.getObject("employeeId", Long.class);
            if(employeeId != null && employeeId > 0) {
                Employee employee = new Employee();
                employee.setEmployeeId(employeeId);
                employee.setEmployeeFullName(rs.getString("employeeFullName"));
                employee.setEmployeeLoginAD(rs.getString("employeeLoginAD"));
                model.setWhoSigned(employee);
            }
            return model;
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

}
