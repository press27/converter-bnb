package eu.iba.auto_test.converterbnb.dao.repository.sql;

import eu.iba.auto_test.converterbnb.dao.model.Task;
import eu.iba.auto_test.converterbnb.dao.repository.mapper.TaskDocumentRowMapper;
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

// Поручения по ркк
public class TaskDocumentSqlFunction extends SqlFunction<Task> {

    private static final String SQL ="SELECT DISTINCT task.XRecID as id, task.HighLvl as rkkId, task.MainClaim as rkkCitizenId, " +
            "task.RRCAssignmentType as taskType, task.Recv4 as taskNumber, task.DateOper as createDate, task.Prim as taskText, " +
            "task.Date2 as planedDateEnd, task.Date3 as realDateEnd, task.AssignmentStatus as taskStatus, task.Polzovatel as authorId, " +
            "p.NameAn as authorFullName, p.Dop as authorLoginAD, task.MainRRCAssignment as parentId " +
            "FROM MBAnalit task " +
            "LEFT JOIN MBAnalit p ON task.Polzovatel = p.Analit and p.Vid = 3119 " +
            "WHERE " +
            "task.Vid = 3342 " +
            "AND (task.HighLvl = (:rkkId) or task.MainClaim = (:rkkId)) " +
            "AND task.XRecID NOT IN ( " +
            "   SELECT DISTINCT excludeTask.IntNumber2 " + //Нужно что бы убрать поручения старой версии (у них поручения могут пересоздаваться как новая версия)
            "   FROM MBAnalit excludeTask " +
            "   WHERE excludeTask.Vid = 3342 AND (excludeTask.HighLvl = (:rkkId) OR excludeTask.MainClaim = (:rkkId)) AND excludeTask.IntNumber2 IS NOT NULL) ";

    private final TaskDocumentRowMapper rowMapper;

    public TaskDocumentSqlFunction(DataSource ds, Map<String, Object> mapParam) {
        super(ds, SQL);
        this.rowMapper = new TaskDocumentRowMapper();
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
