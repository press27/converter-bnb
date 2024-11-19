package eu.iba.auto_test.converterbnb.dao.repository.mapper;

import eu.iba.auto_test.converterbnb.dao.model.AttachmentDocument;
import eu.iba.auto_test.converterbnb.dao.model.Author;
import eu.iba.auto_test.converterbnb.dao.model.UserType;
import eu.iba.auto_test.converterbnb.utils.UserUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class AttachmentDocumentRowMapper implements RowMapper<AttachmentDocument> {

    @Override
    public AttachmentDocument mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            AttachmentDocument model = new AttachmentDocument();
            Long id = rs.getObject("id", Long.class);
            if(id != null){
                model.setId(id);
            }
            model.setDocRkkId(rs.getLong("docRkkId"));

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

            Timestamp uploadDate = rs.getTimestamp("uploadDate");
            if(uploadDate != null){
                model.setUploadDate(uploadDate.toInstant());
            }
            model.setName(rs.getString("name"));
            model.setContentType(rs.getString("contentType"));
            model.setSize(rs.getLong("size"));
            model.setData(rs.getString("data"));
            model.setCrc(rs.getString("crc"));
            return model;
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }
}
