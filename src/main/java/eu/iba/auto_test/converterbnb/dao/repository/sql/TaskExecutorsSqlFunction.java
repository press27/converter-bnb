package eu.iba.auto_test.converterbnb.dao.repository.sql;

import eu.iba.auto_test.converterbnb.dao.model.TaskExecutors;
import eu.iba.auto_test.converterbnb.dao.repository.mapper.TaskExecutorsRowMapper;
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

// Исполнители поручения
//TODO LEFT JOIN executor.Vid = 3119 возможно не тот справочник
public class TaskExecutorsSqlFunction extends SqlFunction<TaskExecutors> {

    private static final String SQL ="SELECT DISTINCT taskExecutor.XRecID as id, taskExecutor.Analit as taskId, " +
            "task.HighLvl as rkkId, task.MainClaim as rkkCitizenId, task.DateOper as createDate, taskExecutor.YesNoT as responsible, " +
            "taskExecutor.DataT as executedDate, taskExecutor.DopT as stampText, taskExecutor.PerformerT as employeeId, " +
            "executor.NameAn as employeeFullName, executor.Dop as employeeLoginAD, taskExecutor.AssignmentStatusT as taskExecutorsStatusDirectum, " +
            "taskExecutor.NumStr as number, task.MainRRCAssignment as parentTaskId " +
            "FROM MBAnValR taskExecutor " +
            "LEFT JOIN MBAnalit executor ON taskExecutor.PerformerT = executor.Analit and executor.Vid = 3119 " +
            "LEFT JOIN MBAnalit task ON taskExecutor.Analit = task.Analit and task.Vid = 3342 " +
            "WHERE " +
            "taskExecutor.Analit = (:taskId) ";

    private final TaskExecutorsRowMapper rowMapper;

    public TaskExecutorsSqlFunction(DataSource ds, Map<String, Object> mapParam) {
        super(ds, SQL);
        this.rowMapper = new TaskExecutorsRowMapper();
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
    protected TaskExecutors mapRow(@NotNull ResultSet rs, int rowNum) throws SQLException {
        return rowMapper.mapRow(rs, rowNum);
    }

}
