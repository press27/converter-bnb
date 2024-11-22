package eu.iba.auto_test.converterbnb.dao.repository.mapper;

import eu.iba.auto_test.converterbnb.dao.model.AttachmentDocument;
import eu.iba.auto_test.converterbnb.dao.model.Author;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class AttachmentTaskRowMapper implements RowMapper<AttachmentDocument> {
    @Override
    public AttachmentDocument mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            AttachmentDocument model = new AttachmentDocument();
            model.setId(rs.getLong("id"));
            model.setDocCardId(rs.getLong("docCardId"));
            model.setTaskId(rs.getLong("taskId"));

            Long authorId = rs.getObject("authorId", Long.class);
            if(authorId != null && authorId > 0) {
                Author author = new Author();
                author.setAuthorId(authorId);
                author.setAuthorFullName(rs.getString("authorFullName"));
                author.setAuthorLoginAD(rs.getString("authorLoginAD"));
                model.setAuthor(author);
            }

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
