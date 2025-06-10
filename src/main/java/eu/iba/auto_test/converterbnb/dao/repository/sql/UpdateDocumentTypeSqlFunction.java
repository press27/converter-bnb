package eu.iba.auto_test.converterbnb.dao.repository.sql;

import eu.iba.auto_test.converterbnb.dao.model.additional.UpdateDocumentType;
import eu.iba.auto_test.converterbnb.dao.repository.mapper.UpdateDocumentTypeMapper;
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

public class UpdateDocumentTypeSqlFunction extends SqlFunction<UpdateDocumentType> {

    private static final String SQL ="SELECT DISTINCT TOP(20) rkk.XRecID as id, rkk.PriznDok as documentCategoryConstants, " +
            "rkk.DocumentKind as documentTypeId, dk.NameAn as documentType, rkk.Dop as regNumber, rkk.DataTime as regDate " +
            "FROM MBAnalit rkk " +
            "LEFT JOIN MBAnalit dk ON rkk.DocumentKind = dk.Analit and dk.Vid = 3153 " +
            "WHERE rkk.Vid = 3174 " +
            "AND rkk.DataTime IS NOT NULL " +
            "AND rkk.XRecID > :nextId ORDER BY rkk.XRecID ";

    private final UpdateDocumentTypeMapper rowMapper;

    public UpdateDocumentTypeSqlFunction(DataSource ds, Map<String, Object> mapParam) {
        super(ds, SQL);
        this.rowMapper = new UpdateDocumentTypeMapper();
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
    protected UpdateDocumentType mapRow(@NotNull ResultSet rs, int rowNum) throws SQLException {
        return rowMapper.mapRow(rs, rowNum);
    }


}
