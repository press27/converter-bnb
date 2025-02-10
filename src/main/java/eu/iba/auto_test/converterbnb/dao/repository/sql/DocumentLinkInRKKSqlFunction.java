package eu.iba.auto_test.converterbnb.dao.repository.sql;

import eu.iba.auto_test.converterbnb.dao.model.DocumentLink;
import eu.iba.auto_test.converterbnb.dao.repository.mapper.DocumentLinkRKKRowMapper;
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

public class DocumentLinkInRKKSqlFunction extends SqlFunction<DocumentLink> {

    private static final String SQL ="SELECT DISTINCT TOP(10) link.XRecID as id, link.RKK as docRkkLinkId " +
            "FROM MBAnalit link " +
            "WHERE link.RKK IS NOT NULL " +
            "AND link.XRecID is not null " +
            "AND link.XRecID > :nextId ORDER BY link.XRecID ";

    private final DocumentLinkRKKRowMapper rowMapper;

    public DocumentLinkInRKKSqlFunction(DataSource ds, Map<String, Object> mapParam) {
        super(ds, SQL);
        this.rowMapper = new DocumentLinkRKKRowMapper();
        changeSql(mapParam);
    }

    private void changeSql(Map<String, Object> paramMap) throws DataAccessException {
        StringBuilder sb = new StringBuilder();
        sb.append(SQL);
        super.setSql(sb.toString());
        declareParameterCustom(paramMap);
    }

    private void declareParameterCustom(Map<String, Object> paramMap) throws InvalidDataAccessApiUsageException {
        declareParameter(new SqlParameter("nextId", Types.BIGINT));
    }

    @Override
    protected DocumentLink mapRow(@NotNull ResultSet rs, int rowNum) throws SQLException {
        return rowMapper.mapRow(rs, rowNum);
    }

}
