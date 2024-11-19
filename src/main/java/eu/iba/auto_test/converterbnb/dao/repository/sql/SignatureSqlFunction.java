package eu.iba.auto_test.converterbnb.dao.repository.sql;

import eu.iba.auto_test.converterbnb.dao.model.Signature;
import eu.iba.auto_test.converterbnb.dao.repository.mapper.SignatureRowMapper;
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

public class SignatureSqlFunction extends SqlFunction<Signature> {

    private static final String SQL ="SELECT signature.XRecID as id, signature.EDocID as docCardId, signature.Author as authorId, " +
            "p.NameAn as authorFullName, p.Dop as authorLoginAD, signature.ReplaceableUser as employeeId, " +
            "repUser.NameAn as employeeFullName, repUser.Dop as employeeLoginAD, signature.SignDate as signDate, signature.Sign as data, " +
            "signature.SignatureType as signatureType, signature.Comment as comment " +
            "FROM SBEDocSignature signature " +
            "LEFT JOIN MBAnalit p ON signature.Author = p.Analit and p.Vid = 3119 " +
            "LEFT JOIN MBAnalit repUser ON signature.ReplaceableUser = repUser.Analit and repUser.Vid = 3119 " +
            "WHERE signature.EDocID = (:docCardId) ";

    private final SignatureRowMapper rowMapper;

    public SignatureSqlFunction(DataSource ds, Map<String, Object> mapParam) {
        super(ds, SQL);
        this.rowMapper = new SignatureRowMapper();
        changeSql(mapParam);
    }

    private void changeSql(Map<String, Object> paramMap) throws DataAccessException {
        StringBuilder sb = new StringBuilder();
        sb.append(SQL);
        super.setSql(sb.toString());
        declareParameterCustom(paramMap);
    }

    private void declareParameterCustom(Map<String, Object> paramMap) throws InvalidDataAccessApiUsageException {
        declareParameter(new SqlParameter("docCardId", Types.OTHER));
    }

    @Override
    protected Signature mapRow(@NotNull ResultSet rs, int rowNum) throws SQLException {
        return rowMapper.mapRow(rs, rowNum);
    }

}
