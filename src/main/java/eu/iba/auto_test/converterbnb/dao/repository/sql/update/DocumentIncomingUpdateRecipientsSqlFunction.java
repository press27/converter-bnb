package eu.iba.auto_test.converterbnb.dao.repository.sql.update;

import eu.iba.auto_test.converterbnb.dao.model.Recipient;
import eu.iba.auto_test.converterbnb.dao.repository.mapper.RecipientRowMapper;
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

public class DocumentIncomingUpdateRecipientsSqlFunction extends SqlFunction<Recipient> {

    private static final String SQL ="SELECT DISTINCT rkk.XRecID as id, rkk.LongString4 as recipientName " +
            "FROM MBAnalit rkk " +
            "WHERE rkk.PriznDok = 'В' AND rkk.Vid = 3174 " +
            "AND rkk.DataTime IS NOT NULL " +
            "AND rkk.LongString4 IS NOT NULL " +
            "AND rkk.XRecID > :nextId ORDER BY rkk.XRecID ";

    private final RecipientRowMapper rowMapper;

    public DocumentIncomingUpdateRecipientsSqlFunction(DataSource ds, Map<String, Object> mapParam) {
        super(ds, SQL);
        this.rowMapper = new RecipientRowMapper();
        changeSql(mapParam);
    }

    private void changeSql(Map<String, Object> paramMap) throws DataAccessException {
        StringBuilder sb = new StringBuilder();
        sb.append(SQL);

//        if (paramMap.containsKey("rkkIds")){
//            sb.append("AND rkk.XRecID IN (:rkkIds) ");
//        }

        super.setSql(sb.toString());
        declareParameterCustom(paramMap);
    }

    private void declareParameterCustom(Map<String, Object> paramMap) throws InvalidDataAccessApiUsageException {
//        if (paramMap.containsKey("rkkIds")){
//            declareParameter(new SqlParameter("rkkIds", Types.OTHER));
//        }
        declareParameter(new SqlParameter("nextId", Types.BIGINT));
    }

    @Override
    protected Recipient mapRow(@NotNull ResultSet rs, int rowNum) throws SQLException {
        return rowMapper.mapRow(rs, rowNum);
    }

}
