package eu.iba.auto_test.converterbnb.dao.repository.sql;

import eu.iba.auto_test.converterbnb.dao.model.History;
import eu.iba.auto_test.converterbnb.dao.repository.mapper.HistoryTaskRowMapper;
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

public class HistoryTaskSqlFunction extends SqlFunction<History> {

    private static final String SQL ="SELECT taskProtocol.XRecID as id, taskProtocol.TaskID as taskId, taskProtocol.JobID as jobId, " +
            "taskProtocol.ActionDate as dateAction, taskProtocol.UserID as authorId, p.NameAn as authorFullName, p.Dop as authorLoginAD, " +
            "taskProtocol.ActionType as action, taskProtocol.Detail as detail, taskProtocol.DetailInfo as detailInfo " +
            "FROM SBTaskProtocol taskProtocol " +
            "LEFT JOIN MBAnalit p ON taskProtocol.UserID = p.Analit and p.Vid = 3119 " +
            "WHERE " +
            "taskProtocol.TaskID = (:taskId) ";

    private final HistoryTaskRowMapper rowMapper;

    public HistoryTaskSqlFunction(DataSource ds, Map<String, Object> mapParam) {
        super(ds, SQL);
        this.rowMapper = new HistoryTaskRowMapper();
        changeSql(mapParam);
    }

    private void changeSql(Map<String, Object> paramMap) throws DataAccessException {
        StringBuilder sb = new StringBuilder();
        sb.append(SQL);
        super.setSql(sb.toString());
        declareParameterCustom(paramMap);
    }

    private void declareParameterCustom(Map<String, Object> paramMap) throws InvalidDataAccessApiUsageException {
        declareParameter(new SqlParameter("taskId", Types.BIGINT));
    }

    @Override
    protected History mapRow(@NotNull ResultSet rs, int rowNum) throws SQLException {
        return rowMapper.mapRow(rs, rowNum);
    }

}
