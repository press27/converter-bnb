package eu.iba.auto_test.converterbnb.dao.repository.sql;

import eu.iba.auto_test.converterbnb.dao.repository.mapper.TaskJobRowMapper;
import eu.iba.auto_test.converterbnb.dao.repository.sql.model.TaskJobGeneral;
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

public class TaskJobSqlFunction extends SqlFunction<TaskJobGeneral> {

    private static final String SQL ="SELECT DISTINCT taskJob.XRecID as id, taskJob.TaskID as taskId, " +
            "taskJob.Executor as employeeId, p.NameAn as employeeFullName, p.Dop as employeeLoginAD " +
            "FROM SBTaskJob taskJob " +
            "LEFT JOIN MBAnalit p ON taskJob.Executor = p.Analit AND p.Vid = 3119 " +
            "WHERE (taskJob.State = 'D' OR taskJob.State = 'B') AND taskJob.TaskID = (:taskId) ";

    private final TaskJobRowMapper rowMapper;

    public TaskJobSqlFunction(DataSource ds, Map<String, Object> mapParam) {
        super(ds, SQL);
        this.rowMapper = new TaskJobRowMapper();
        changeSql(mapParam);
    }

    private void changeSql(Map<String, Object> paramMap) throws DataAccessException {
        StringBuilder sb = new StringBuilder();
        sb.append(SQL);
        super.setSql(sb.toString());
        declareParameterCustom(paramMap);
    }

    private void declareParameterCustom(Map<String, Object> paramMap) throws InvalidDataAccessApiUsageException {
        declareParameter(new SqlParameter("taskId", Types.OTHER));
    }

    @Override
    protected TaskJobGeneral mapRow(@NotNull ResultSet rs, int rowNum) throws SQLException {
        return rowMapper.mapRow(rs, rowNum);
    }

}
