package eu.iba.auto_test.converterbnb.dao.repository.mapper;

import eu.iba.auto_test.converterbnb.dao.model.*;
import eu.iba.auto_test.converterbnb.utils.DocumentUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

public class DocumentIncomingRowMapper implements RowMapper<Document> {

//    2018: 01-1-30 - 166357
//    2019: 01-1-30 - 198985
//    2020: 01-1-10 - 216182
//    2021: 52-1-10 - 244582
//    2022: 32-1-10 - 284103
//    2023: 32-1-08 - 342703
//    2024: 32-1-08 - 452916

    private final static ZoneId ZONE_MINSK = ZoneId.of("Europe/Moscow");

    @Override
    public Document mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            Document model = new Document();
            model.setId(rs.getLong("id"));
            model.setDocumentCategoryConstants(DocumentUtils.convertDocumentCategoryConstants(rs.getString("documentCategoryConstants")));

            Long correspondentId = rs.getObject("correspondentId", Long.class);
            if(correspondentId != null && correspondentId > 0) {
                CorrespondentDocument correspondentDocument = new CorrespondentDocument();
                correspondentDocument.setCorrespondentId(correspondentId);
                correspondentDocument.setCorrespondentName(rs.getString("correspondentName"));
                correspondentDocument.setCorrespondentFullName(rs.getString("correspondentFullName"));
                correspondentDocument.setCorrespondentUnp(rs.getString("correspondentUnp"));
                correspondentDocument.setCorrespondentSmdoCode(rs.getString("correspondentSmdoCode"));
                model.setCorrespondent(correspondentDocument);
            }

            model.setOutRegNumber(rs.getString("outRegNumber"));
            Timestamp outRegDate = rs.getTimestamp("outRegDate");
            if (outRegDate != null) {
                model.setOutRegDate(outRegDate.toInstant());
            }
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

            Timestamp regDate = rs.getTimestamp("regDate");
            if(regDate != null){
                model.setRegDate(regDate.toInstant());
                model.setCreateDate(regDate.toInstant());
            }

            String regNumber = rs.getString("regNumber");
            if(regDate != null && (regNumber == null || regNumber.isEmpty())){
                model.setSkipRegistration(Boolean.TRUE);
                model.setRegDate(null);
            } else {
                model.setRegNumber(regNumber);
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
            } else {
                if(regDate != null){
                    saveNomenclatureAffair(model, regDate);
                }
            }

            model.setInDocSigners(rs.getString("inDocSigners"));
            model.setDeliveryMethod(rs.getString("deliveryMethod"));

            String ids = rs.getString("introductionIds");
            if(ids != null && !ids.isEmpty()) {
                List<Long> introductionIds = model.getIntroductionIds();
                String[] stringIds = ids.split(",");
                for (String stringId : stringIds) {
                    if(stringId != null && !stringId.isEmpty()) {
                        Long longId = Long.parseLong(stringId.trim());
                        introductionIds.add(longId);
                    }
                }
                Collections.sort(introductionIds);
                model.setIntroductionIds(introductionIds);
            }
            return model;
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    private void saveNomenclatureAffair(Document model, Timestamp regDate) {
        Instant iRegDate = regDate.toInstant();
        ZonedDateTime zRegDate = iRegDate.atZone(ZONE_MINSK);
        int year = zRegDate.getYear();
        NomenclatureAffairDocument nomenclatureAffairDocument = new NomenclatureAffairDocument();
        switch (year) {
            case 2018 -> nomenclatureAffairDocument.setNomenclatureAffairId(166357L);
            case 2019 -> nomenclatureAffairDocument.setNomenclatureAffairId(198985L);
            case 2020 -> nomenclatureAffairDocument.setNomenclatureAffairId(216182L);
            case 2021 -> nomenclatureAffairDocument.setNomenclatureAffairId(244582L);
            case 2022 -> nomenclatureAffairDocument.setNomenclatureAffairId(284103L);
            case 2023 -> nomenclatureAffairDocument.setNomenclatureAffairId(342703L);
            case 2024 -> nomenclatureAffairDocument.setNomenclatureAffairId(452916L);
        }
        model.setNomenclatureAffairDocument(nomenclatureAffairDocument);
    }

}
