package eu.iba.auto_test.converterbnb.dao.repository.sql;

import eu.iba.auto_test.converterbnb.dao.model.Introduction;
import eu.iba.auto_test.converterbnb.dao.repository.mapper.IntroductionRowMapper;
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

// В обращениях нет ознакомления +
// Написать скрипт который забирает Вложение (Лист ознакомления) +
public class IntroductionSqlFunction extends SqlFunction<Introduction> {

    private static final String SQL ="SELECT DISTINCT introduction.XRecID as id, task.XRecID as taskId, introduction.StartDate as createDate, " +
            "task.Author as authorId, p.NameAn as authorFullName, p.Dop as authorLoginAD, introduction.EndDate as introductionDate, " +
            "introduction.Executor as employeeId, executor.NameAn as employeeFullName, executor.Dop as employeeLoginAD, " +
            "introduction.Subject as comment " +
            "FROM SBTaskJob introduction " +
            "LEFT JOIN SBTask task ON introduction.TaskID = task.XRecID " +
            "LEFT JOIN MBAnalit executor ON introduction.Executor = executor.Analit and executor.Vid = 3119 " +
            "LEFT JOIN MBAnalit p ON task.Author = p.Analit and p.Vid = 3119 " +
            "LEFT JOIN MBAnalit rkk ON task.XRecID = (:introductionId) " +
            "WHERE introduction.State = 'D' AND rkk.XRecID = (:rkkId) ";

    private final IntroductionRowMapper rowMapper;

    public IntroductionSqlFunction(DataSource ds, Map<String, Object> mapParam) {
        super(ds, SQL);
        this.rowMapper = new IntroductionRowMapper();
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
        declareParameter(new SqlParameter("introductionId", Types.BIGINT));
    }

    @Override
    protected Introduction mapRow(@NotNull ResultSet rs, int rowNum) throws SQLException {
        return rowMapper.mapRow(rs, rowNum);
    }

}
