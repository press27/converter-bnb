package eu.iba.auto_test.converterbnb.dao.repository.mapper;

import eu.iba.auto_test.converterbnb.dao.model.Employee;
import eu.iba.auto_test.converterbnb.dao.model.TaskComment;
import eu.iba.auto_test.converterbnb.utils.AttachmentUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.StreamUtils;

import java.nio.charset.Charset;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TaskCommentRowMapper implements RowMapper<TaskComment> {

    public static final Logger log = LoggerFactory.getLogger(TaskCommentRowMapper.class);

    @Override
    public TaskComment mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            TaskComment model = new TaskComment();
            model.setId(rs.getLong("id"));
            model.setTaskId(rs.getLong("taskId"));
            Long jobId = rs.getObject("jobId", Long.class);
            if (jobId != null && jobId > 0) {
                model.setJobId(jobId);
            }
            Timestamp createDate = rs.getTimestamp("createDate");
            if (createDate != null) {
                model.setCreateDate(createDate.toInstant());
            }

            Long authorId = rs.getObject("authorId", Long.class);
            if (authorId != null && authorId > 0) {
                Employee author = new Employee();
                author.setEmployeeId(authorId);
                author.setEmployeeFullName(rs.getString("authorFullName"));
                author.setEmployeeLoginAD(rs.getString("authorLoginAD"));
                model.setAuthor(author);
            }
            model.setComment(getText(getSafeHex(rs,"comment")));
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

    private String getText(String text){
        if(text != null) {
            return new String(AttachmentUtils.hexToByte(text), Charset.forName("windows-1251"));
        }
        return null;
    }
}
