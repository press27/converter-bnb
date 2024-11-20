package eu.iba.auto_test.converterbnb.dao.repository.sql;

import eu.iba.auto_test.converterbnb.dao.model.AttachmentDocument;
import eu.iba.auto_test.converterbnb.dao.repository.mapper.AttachmentTaskRowMapper;
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

// like Лист ознакомления
public class AttachmentTaskSqlFunction extends SqlFunction<AttachmentDocument> {

    private static final String SQL ="SELECT attachment.XRecID as id, attachment.EDocID as docCardId, taskAttach.TaskID as taskId, " +
            "attachment.Author as authorId, p.NameAn as authorFullName, p.Dop as authorLoginAD, attachment.ModifyDate as uploadDate, " +
            "document.Name as name, attachment.TypeVersionData as contentType, attachment.Size as size, attachment.VersionData as data, attachment.CRC as crc " +
            "FROM SBTaskAttach taskAttach " +
            "LEFT JOIN SBEDoc document ON document.XRecID = taskAttach.AttachID " +
            "LEFT JOIN SBEDocVer attachment ON document.XRecID = attachment.EDocID " +
            "LEFT JOIN MBAnalit p ON attachment.Author = p.Analit and p.Vid = 3119 " +
            "WHERE taskAttach.AttachType = (:attachType) AND taskAttach.TaskID = (:taskId) ";

    private final AttachmentTaskRowMapper rowMapper;

    public AttachmentTaskSqlFunction(DataSource ds, Map<String, Object> mapParam) {
        super(ds, SQL);
        this.rowMapper = new AttachmentTaskRowMapper();
        changeSql(mapParam);
    }

    private void changeSql(Map<String, Object> paramMap) throws DataAccessException {
        StringBuilder sb = new StringBuilder();
        sb.append(SQL);

        if (paramMap.containsKey("name")){
            sb.append("AND document.Name LIKE (:name) ");
        }

        super.setSql(sb.toString());
        declareParameterCustom(paramMap);
    }

    private void declareParameterCustom(Map<String, Object> paramMap) throws InvalidDataAccessApiUsageException {
        declareParameter(new SqlParameter("attachType", Types.VARCHAR));
        declareParameter(new SqlParameter("taskId", Types.BIGINT));

        if (paramMap.containsKey("name")){
            declareParameter(new SqlParameter("name", Types.VARCHAR));
        }

    }

    @Override
    protected AttachmentDocument mapRow(@NotNull ResultSet rs, int rowNum) throws SQLException {
        return rowMapper.mapRow(rs, rowNum);
    }

}
