package eu.iba.auto_test.converterbnb.dao.repository.sql;

import eu.iba.auto_test.converterbnb.dao.model.Correspondent;
import eu.iba.auto_test.converterbnb.dao.repository.mapper.CorrespondentRowMapper;
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

public class CorrespondentSqlFunction extends SqlFunction<Correspondent> {

    private static final String SQL ="SELECT DISTINCT TOP(10) c.XRecID as id, c.NameAn as nameCorrespondent, c.LongString5 as fullNameCorrespondent, " +
            "c.INN as unp, c.Soder2 as address " +
            "FROM MBAnalit c " +
            "WHERE c.Vid = 266 AND c.NOVSmdoCode IS NULL AND c.XRecID > :nextId ORDER BY c.XRecID ";

    private final CorrespondentRowMapper rowMapper;

    public CorrespondentSqlFunction(DataSource ds, Map<String, Object> paramMap) {
        super(ds, SQL);
        this.rowMapper = new CorrespondentRowMapper();
        changeSql(paramMap);
    }

    private void changeSql(Map<String, Object> paramMap) throws DataAccessException {
        StringBuilder sb = new StringBuilder();
        sb.append(SQL);
        super.setSql(sb.toString());
        declareParameterCustom(paramMap);
    }

    private void declareParameterCustom(Map<String, Object> paramMap) throws InvalidDataAccessApiUsageException {
        declareParameter(new SqlParameter("nextId", Types.OTHER));
    }

    @Override
    protected Correspondent mapRow(@NotNull ResultSet rs, int rowNum) throws SQLException {
        return rowMapper.mapRow(rs, rowNum);
    }

}
