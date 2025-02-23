package eu.iba.auto_test.converterbnb.dao.repository.sql;

import eu.iba.auto_test.converterbnb.dao.repository.mapper.TaskParentRowMapper;
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

public class ProcessAttachmentTaskParentSqlFunction extends SqlFunction<TaskGeneral> {

    private static final String SQL ="SELECT DISTINCT task.XRecID as id, task.Author as authorId, p.NameAn as authorFullName, p.Dop as authorLoginAD " +
            "FROM SBTask task " +
            "LEFT JOIN SBTaskAttach taskAttach ON task.XRecID = taskAttach.TaskID AND AttachType = 'E' " +
            "LEFT JOIN MBAnalit p ON task.Author = p.Analit and p.Vid = 3119 " +
            "WHERE taskAttach.AttachID IN (:docCardIds) AND (task.State = 'D' OR task.State = 'B') ";

    private final TaskParentRowMapper rowMapper;

    public ProcessAttachmentTaskParentSqlFunction(DataSource ds, Map<String, Object> mapParam) {
        super(ds, SQL);
        this.rowMapper = new TaskParentRowMapper();
        changeSql(mapParam);
    }

    private void changeSql(Map<String, Object> paramMap) throws DataAccessException {
        StringBuilder sb = new StringBuilder();
        sb.append(SQL);
        super.setSql(sb.toString());
        declareParameterCustom(paramMap);
    }

    private void declareParameterCustom(Map<String, Object> paramMap) throws InvalidDataAccessApiUsageException {
        declareParameter(new SqlParameter("docCardIds", Types.BIGINT));
    }

    @Override
    protected TaskGeneral mapRow(@NotNull ResultSet rs, int rowNum) throws SQLException {
        return rowMapper.mapRow(rs, rowNum);
    }

}
