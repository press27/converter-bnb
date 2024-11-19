package eu.iba.auto_test.converterbnb.dao.repository.sql;

import eu.iba.auto_test.converterbnb.dao.repository.mapper.TaskChildRowMapper;
import eu.iba.auto_test.converterbnb.dao.repository.sql.model.TaskGeneral;
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

public class ReviewTaskChildSqlFunction extends SqlFunction<TaskGeneral> {

    private static final String SQL ="SELECT DISTINCT task.XRecID as id, task.LeaderTaskID as parentLeaderId, " +
            "task.Author as authorId, p.NameAn as authorFullName, p.Dop as authorLoginAD " +
            "FROM SBTask task " +
            "LEFT JOIN MBAnalit p ON task.Author = p.Analit and p.Vid = 3119 " +
            "WHERE task.LeaderTaskID = (:taskId) AND (task.State = 'D' OR task.State = 'B')";

    private final TaskChildRowMapper rowMapper;

    public ReviewTaskChildSqlFunction(DataSource ds, Map<String, Object> mapParam) {
        super(ds, SQL);
        this.rowMapper = new TaskChildRowMapper();
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
    protected TaskGeneral mapRow(@NotNull ResultSet rs, int rowNum) throws SQLException {
        return rowMapper.mapRow(rs, rowNum);
    }

}
