package eu.iba.auto_test.converterbnb.dao.repository.mapper;

import eu.iba.auto_test.converterbnb.dao.model.*;
import eu.iba.auto_test.converterbnb.utils.DocumentUtils;
import eu.iba.auto_test.converterbnb.utils.UserUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DocumentOutgoingRowMapper implements RowMapper<Document> {

    @Override
    public Document mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            Document model = new Document();
            model.setId(rs.getLong("id"));
            model.setDocumentCategoryConstants(DocumentUtils.convertDocumentCategoryConstants(rs.getString("documentCategoryConstants")));

            AddresseeDocument addresseeDocument = new AddresseeDocument();
            addresseeDocument.setAddresseeId(rs.getLong("addresseeId"));
            addresseeDocument.setAddresseeName(rs.getString("addresseeName"));
            addresseeDocument.setAddresseeFullName(rs.getString("addresseeFullName"));
            addresseeDocument.setToPeople(rs.getString("toPeople"));
            addresseeDocument.setDeliveryMethod(rs.getString("deliveryMethod"));
            Timestamp dateSend = rs.getTimestamp("dateSend");
            if(dateSend != null){
                addresseeDocument.setDateSend(dateSend.toInstant());
            }
            model.setAddressee(addresseeDocument);

            model.setShortSummary(rs.getString("shortSummary"));

            DocumentType documentType = new DocumentType();
            documentType.setDocumentTypeId(rs.getLong("documentTypeId"));
            documentType.setDocumentType(rs.getString("documentType"));
            model.setDocumentType(documentType);

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

            model.setRegNumber(rs.getString("regNumber"));
            Timestamp regDate = rs.getTimestamp("regDate");
            if(regDate != null){
                model.setRegDate(regDate.toInstant());
            }

            NomenclatureAffairDocument nomenclatureAffairDocument = new NomenclatureAffairDocument();
            nomenclatureAffairDocument.setNomenclatureAffairId(rs.getLong("nomenclatureAffairId"));
            nomenclatureAffairDocument.setNomenclatureAffairName(rs.getString("nomenclatureAffairName"));
            Timestamp nomenclatureAffairDate = rs.getTimestamp("nomenclatureAffairDate");
            if(nomenclatureAffairDate != null){
                nomenclatureAffairDocument.setNomenclatureAffairDate(nomenclatureAffairDate.toInstant());
            }
            model.setNomenclatureAffairDocument(nomenclatureAffairDocument);

            Employee employee = new Employee();
            Long employeeId = rs.getObject("employeeId", Long.class);
            if(employeeId != null) {
                employee.setEmployeeId(employeeId);
                employee.setEmployeeFullName(rs.getString("employeeFullName"));
                employee.setEmployeeLoginAD(rs.getString("employeeLoginAD"));
                employee.setUserType(UserUtils.getUserType(employeeId));
            } else {
                employee.setUserType(UserType.SYSTEM);
            }
            model.setWhoSigned(employee);
            return model;
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

}
