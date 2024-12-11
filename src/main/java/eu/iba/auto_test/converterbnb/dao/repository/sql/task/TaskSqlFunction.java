package eu.iba.auto_test.converterbnb.dao.repository.sql.task;

import eu.iba.auto_test.converterbnb.dao.repository.sql.task.mapper.TaskRowMapper;
import eu.iba.auto_test.converterbnb.dao.repository.sql.task.model.Task;
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

public class TaskSqlFunction extends SqlFunction<Task> {

    private static final String SQL = "SELECT task.XRecID as id, task.StartDate as dateStart, task.EndDate as dateEnd, " +
            "task.FinalDate as finalDate, task.Author as authorId, p.NameAn as authorFullName, p.Dop as authorLoginAD, " +
            "task.Subject as topic, task.State as status " +
            "FROM SBTask task " +
            "LEFT JOIN MBAnalit p ON task.Author = p.Analit AND p.Vid = 3119 " +
            "LEFT JOIN SBTaskAttach taskAttach ON task.XRecID = taskAttach.TaskID " +
            "LEFT JOIN SBEDoc document ON taskAttach.AttachID = document.XRecID " +
            "WHERE document.XRecID IN (SELECT DestID from SBLinks link WHERE SourceID = (:rkkId) and SourceType = 'R' and DestType = 'E') ";

    private final TaskRowMapper rowMapper;

    public TaskSqlFunction(DataSource ds, Map<String, Object> mapParam) {
        super(ds, SQL);
        this.rowMapper = new TaskRowMapper();
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
    protected Task mapRow(@NotNull ResultSet rs, int rowNum) throws SQLException {
        return rowMapper.mapRow(rs, rowNum);
    }

}
