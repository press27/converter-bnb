package eu.iba.auto_test.converterbnb.dao.repository.sql.task;

import eu.iba.auto_test.converterbnb.dao.repository.sql.task.mapper.TaskJobRowMapper;
import eu.iba.auto_test.converterbnb.dao.repository.sql.task.model.TaskJob;
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

public class TaskJobSqlFunction extends SqlFunction<TaskJob> {

    private static final String SQL = "SELECT taskJob.XRecID as id, taskJob.TaskID as taskId, taskJob.StartDate as dateStart, taskJob.EndDate as dateEnd, " +
            "taskJob.FinalDate as finalDate, taskJob.Executor as authorId, p.NameAn as authorFullName, p.Dop as authorLoginAD, " +
            "taskJob.Subject as topic, taskJob.State as status " +
            "FROM SBTaskJob taskJob " +
            "LEFT JOIN MBAnalit p ON taskJob.Executor = p.Analit and p.Vid = 3119 " +
            "WHERE taskJob.TaskID = (:taskId) ";

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
        declareParameter(new SqlParameter("taskId", Types.BIGINT));
    }

    @Override
    protected TaskJob mapRow(@NotNull ResultSet rs, int rowNum) throws SQLException {
        return rowMapper.mapRow(rs, rowNum);
    }

}
