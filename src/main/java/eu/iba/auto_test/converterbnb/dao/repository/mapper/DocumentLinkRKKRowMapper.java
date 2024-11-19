package eu.iba.auto_test.converterbnb.dao.repository.mapper;

import eu.iba.auto_test.converterbnb.dao.model.DocumentLink;
import eu.iba.auto_test.converterbnb.dao.model.DocumentLinkType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DocumentLinkRKKRowMapper implements RowMapper<DocumentLink> {

    @Override
    public DocumentLink mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        Long docRkkLinkId = rs.getObject("docRkkLinkId", Long.class);
        if(docRkkLinkId != null) {
            DocumentLink documentLink = new DocumentLink();
            documentLink.setId(id);
            documentLink.setDocRkkId(id);
            documentLink.setDocRkkLinkId(docRkkLinkId);
            documentLink.setDocumentLinkType(DocumentLinkType.LINK_DOC);
            return documentLink;
        } else {
            return null;
        }
    }
}
