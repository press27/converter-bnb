package eu.iba.auto_test.converterbnb.dao.repository.sql;

import eu.iba.auto_test.converterbnb.dao.model.AttachmentDocument;
import eu.iba.auto_test.converterbnb.dao.repository.mapper.AttachmentDocumentRowMapper;
import jakarta.validation.constraints.NotNull;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlFunction;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;

public class AttachmentDocumentSqlFunction extends SqlFunction<AttachmentDocument> {

    private static final String SQL ="SELECT attachment.XRecID as id, attachment.EDocID as docCardId, attachment.Author as authorId, " +
            "p.NameAn as authorFullName, p.Dop as authorLoginAD, attachment.ModifyDate as uploadDate, document.Name as name, " +
            "attachment.TypeVersionData as contentType, attachment.Size as size, attachment.VersionData as data, attachment.CRC as crc " +
            "FROM SBEDocVer attachment " +
            "LEFT JOIN SBEDoc document ON document.XRecID = attachment.EDocID " +
            "LEFT JOIN MBAnalit p ON attachment.Author = p.Analit and p.Vid = 3119 " +
            "WHERE attachment.EDocID IN (SELECT DestID from SBLinks link WHERE SourceID = (:rkkId) and SourceType = 'R' and DestType = 'E') ";

    private final AttachmentDocumentRowMapper rowMapper;

    public AttachmentDocumentSqlFunction(DataSource ds, Map<String, Object> mapParam) {
        super(ds, SQL);
        this.rowMapper = new AttachmentDocumentRowMapper();
        changeSql(mapParam);
    }

    private void changeSql(Map<String, Object> paramMap) throws DataAccessException {
        StringBuilder sb = new StringBuilder();
        sb.append(SQL);
        super.setSql(sb.toString());
        declareParameterCustom(paramMap);
    }

    private void declareParameterCustom(Map<String, Object> paramMap) throws InvalidDataAccessApiUsageException {
        declareParameter(new SqlParameter("rkkId", Types.BIGINT));
    }

    @Override
    protected AttachmentDocument mapRow(@NotNull ResultSet rs, int rowNum) throws SQLException {
        return rowMapper.mapRow(rs, rowNum);
    }

}
