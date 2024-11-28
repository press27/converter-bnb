package eu.iba.auto_test.converterbnb.dao.repository.mapper;

import eu.iba.auto_test.converterbnb.dao.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

public class DocumentAppealRowMapper implements RowMapper<Document> {

    public static final Logger log = LoggerFactory.getLogger(DocumentAppealRowMapper.class);

    @Override
    public Document mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            Document model = new Document();
            Long id = rs.getObject("id", Long.class);
            if(id != null){
                model.setId(id);
            }
            model.setDocumentCategoryConstants(DocumentCategoryConstants.APPEAL);

            model.setShortSummary(rs.getString("shortSummary"));

            Long citizenTypeId = rs.getObject("citizenTypeId", Long.class);
            if(citizenTypeId != null && citizenTypeId > 0){
                CitizenType citizenType = new CitizenType();
                citizenType.setCitizenTypeId(citizenTypeId);
                citizenType.setCitizenType(rs.getString("citizenType"));
                model.setCitizenType(citizenType);
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
            model.setFioApplicant(rs.getString("fioApplicant"));

            Long organizationId = rs.getObject("organizationId", Long.class);
            if(organizationId != null && organizationId > 0){
                OrganizationDocument organizationDocument = new OrganizationDocument();
                organizationDocument.setOrganizationId(organizationId);
                organizationDocument.setOrganizationName(rs.getString("organizationName"));
                model.setOrganization(organizationDocument);
            }

            Long citizenNomenclatureAffairId = rs.getObject("citizenNomenclatureAffairId", Long.class);
            if(citizenNomenclatureAffairId != null && citizenNomenclatureAffairId > 0){
                NomenclatureAffairDocument nomenclatureAffairDocument = new NomenclatureAffairDocument();
                nomenclatureAffairDocument.setNomenclatureAffairId(citizenNomenclatureAffairId);
                nomenclatureAffairDocument.setNomenclatureAffairName(rs.getString("citizenNomenclatureAffairName"));
                //nomenclatureAffairDocument.setNomenclatureAffairDate(null);
                model.setNomenclatureAffairDocument(nomenclatureAffairDocument);
            }

            Timestamp receiptDate = rs.getTimestamp("receiptDate");
            if(receiptDate != null){
                model.setReceiptDate(receiptDate.toInstant());
            }
            model.setFullAddress(rs.getString("fullAddress"));
            model.setCollective(convertTypeAppeal(rs.getString("collective")));
            model.setAnonymous(convertTypeAppeal(rs.getString("anonymous")));
            model.setDeclarantType(convertDeclarantType(rs.getString("declarantType")));
            return model;
        } catch (Exception e) {
            throw new SQLException(e);
        }

    }

    private DeclarantType convertDeclarantType(String type) {
        if(type != null && !type.isEmpty()){
            if(type.equals("O")){
                return DeclarantType.ENTITY;
            }
            if(type.equals("C")){
                return DeclarantType.INDIVIDUAL;
            }
        }
        return null;
    }

    private Boolean convertTypeAppeal(String type) {
        try {
            if (type != null && !type.isEmpty()) {
                if (Objects.equals(type, "Д")) {
                    return Boolean.TRUE;
                }
            }
            return Boolean.FALSE;
        } catch (Exception ex){
            log.error(ex.getMessage(),ex);
            return Boolean.FALSE;
        }
    }
}
