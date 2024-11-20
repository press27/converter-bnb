package eu.iba.auto_test.converterbnb.dao.repository.mapper;

import eu.iba.auto_test.converterbnb.dao.model.DocumentLink;
import eu.iba.auto_test.converterbnb.dao.model.DocumentLinkType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DocumentLinkRowMapper implements RowMapper<DocumentLink> {

    @Override
    public DocumentLink mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        Long docRkkId = rs.getObject("docRkkId", Long.class);
        Long docRkkLinkId = rs.getObject("docRkkLinkId", Long.class);
        //Long documentLinkTypeId = rs.getObject("documentLinkTypeId", Long.class);
        if(docRkkId != null && docRkkLinkId != null) {
            DocumentLink documentLink = new DocumentLink();
            documentLink.setId(id);
            documentLink.setDocRkkId(docRkkId);
            documentLink.setDocRkkLinkId(docRkkLinkId);
            documentLink.setDocumentLinkType(DocumentLinkType.LINK_DOC);
            return documentLink;
        } else {
            return null;
        }
    }

//    private DocumentLinkType convertLinkType(Long linkType) {
//        if(linkType == 100691L){
//            return DocumentLinkType.RECEIVED_IN_RESPONSE_TO; // Ответное
//        }
//        if(linkType == 100700L){
//            return DocumentLinkType.CREATE_IN_RESPONSE_TO; // В ответ на
//        }
//        if(linkType == 101095L){
//            return DocumentLinkType.LINK_DOC;
//        }
//        if(linkType == 101096L){
//            return DocumentLinkType.LINK_DOC;
//        }
//        return null;
//    }
}
