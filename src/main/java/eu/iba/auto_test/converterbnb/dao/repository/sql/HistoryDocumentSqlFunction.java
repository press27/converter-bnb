package eu.iba.auto_test.converterbnb.dao.repository.sql;

import eu.iba.auto_test.converterbnb.dao.model.History;
import eu.iba.auto_test.converterbnb.dao.repository.mapper.HistoryDocumentRowMapper;
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

public class HistoryDocumentSqlFunction extends SqlFunction<History> {

    private static final String SQL ="SELECT DISTINCT docProtocol.XRecID as id, docProtocol.EDocID as docCardId, links.SourceID as rkkId, docProtocol.ActionDate as dateAction, " +
            "docProtocol.UserID as authorId, p.NameAn as authorFullName, p.Dop as authorLoginAD, docProtocol.ActionType as action, docProtocol.Detail as detail " +
            "FROM SBEDocProtocol docProtocol " +
            "LEFT JOIN SBLinks links ON docProtocol.EDocID = links.DestID and SourceType = 'R' and DestType = 'E' " +
            "LEFT JOIN MBAnalit p ON docProtocol.UserID = p.Analit and p.Vid = 3119 " +
            "WHERE " +
            "links.SourceID = (:rkkId)";

    private final HistoryDocumentRowMapper rowMapper;

    public HistoryDocumentSqlFunction(DataSource ds, Map<String, Object> mapParam) {
        super(ds, SQL);
        this.rowMapper = new HistoryDocumentRowMapper();
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
    protected History mapRow(@NotNull ResultSet rs, int rowNum) throws SQLException {
        return rowMapper.mapRow(rs, rowNum);
    }

}
