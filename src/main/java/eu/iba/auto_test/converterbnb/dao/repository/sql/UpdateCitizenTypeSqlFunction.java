package eu.iba.auto_test.converterbnb.dao.repository.sql;

import eu.iba.auto_test.converterbnb.dao.model.additional.UpdateDocumentType;
import eu.iba.auto_test.converterbnb.dao.repository.mapper.UpdateCitizenTypeMapper;
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

public class UpdateCitizenTypeSqlFunction extends SqlFunction<UpdateDocumentType> {

    private static final String SQL ="SELECT DISTINCT TOP(:count) rkk.XRecID as id, rkk.ClaimType as citizenTypeId, " +
            "ct.NameAn as citizenType, rkk.Dop as regNumber, rkk.DataTime as regDate " +
            "FROM MBAnalit rkk " +
            "LEFT JOIN MBAnalit ct ON rkk.ClaimType = ct.Analit and ct.Vid = 3336 " +
            "WHERE rkk.Vid = 3329 " +
            "AND rkk.ClaimKind IS NOT NULL " +
            "AND rkk.DataTime IS NOT NULL " +
            "AND rkk.XRecID > :nextId ORDER BY rkk.XRecID ";

    private final UpdateCitizenTypeMapper rowMapper;

    public UpdateCitizenTypeSqlFunction(DataSource ds, Map<String, Object> mapParam) {
        super(ds, SQL);
        this.rowMapper = new UpdateCitizenTypeMapper();
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
        declareParameter(new SqlParameter("count", Types.BIGINT));
    }

    @Override
    protected UpdateDocumentType mapRow(@NotNull ResultSet rs, int rowNum) throws SQLException {
        return rowMapper.mapRow(rs, rowNum);
    }

}
