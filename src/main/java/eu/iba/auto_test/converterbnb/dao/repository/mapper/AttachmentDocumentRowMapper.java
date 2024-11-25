package eu.iba.auto_test.converterbnb.dao.repository.mapper;

import eu.iba.auto_test.converterbnb.dao.model.AttachmentDocument;
import eu.iba.auto_test.converterbnb.dao.model.Employee;
import eu.iba.auto_test.converterbnb.utils.AttachmentUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.StreamUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class AttachmentDocumentRowMapper implements RowMapper<AttachmentDocument> {

    public static final Logger log = LoggerFactory.getLogger(AttachmentDocumentRowMapper.class);

    @Override
    public AttachmentDocument mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            AttachmentDocument model = new AttachmentDocument();
            Long id = rs.getObject("id", Long.class);
            if(id != null){
                model.setId(id);
            }
            model.setDocCardId(rs.getLong("docCardId"));

            Long authorId = rs.getObject("authorId", Long.class);
            if(authorId != null && authorId > 0) {
                Employee author = new Employee();
                author.setEmployeeId(authorId);
                author.setEmployeeFullName(rs.getString("authorFullName"));
                author.setEmployeeLoginAD(rs.getString("authorLoginAD"));
                model.setAuthor(author);
            }

            Timestamp uploadDate = rs.getTimestamp("uploadDate");
            if(uploadDate != null){
                model.setUploadDate(uploadDate.toInstant());
            }
            model.setName(rs.getString("name"));
            model.setContentType(rs.getString("contentType"));
            model.setSize(rs.getLong("size"));
            model.setData(getSafeHex(rs,"data"));
            model.setCrc(rs.getString("crc"));
            return model;
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    private String getSafeHex(ResultSet rs, String column) {
        try {
            return AttachmentUtils.bytesToHex(rs.getBytes(column));
        } catch (Exception ex){
            log.error(ex.getMessage(),ex);
            try {
                return AttachmentUtils.bytesToHex(StreamUtils.copyToByteArray(rs.getBlob(column).getBinaryStream()));
            } catch (Exception ex2){
                log.error(ex2.getMessage(),ex2);
            }
            return null;
        }
    }

}
