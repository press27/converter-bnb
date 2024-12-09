package eu.iba.auto_test.converterbnb.dao.repository.sql;

import eu.iba.auto_test.converterbnb.dao.model.TaskComment;
import eu.iba.auto_test.converterbnb.dao.repository.mapper.TaskCommentRowMapper;
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

public class TaskCommentSqlFunction extends SqlFunction<TaskComment> {

    private static final String SQL = "SELECT taskText.XRecID as id, taskText.TaskID as taskId, taskText.JobID as jobId, " +
            "taskText.EditDate as createDate, taskText.Author as authorId, p.NameAn as authorFullName, " +
            "p.Dop as authorLoginAD, taskText.Text as comment " +
            "FROM SBTaskText taskText " +
            "LEFT JOIN MBAnalit p ON taskText.Author = p.Analit and p.Vid = 3119 " +
            "WHERE taskText.TaskID = (:taskId) ";

    private final TaskCommentRowMapper rowMapper;

    public TaskCommentSqlFunction(DataSource ds, Map<String, Object> mapParam) {
        super(ds, SQL);
        this.rowMapper = new TaskCommentRowMapper();
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
    protected TaskComment mapRow(@NotNull ResultSet rs, int rowNum) throws SQLException {
        return rowMapper.mapRow(rs, rowNum);
    }

}
