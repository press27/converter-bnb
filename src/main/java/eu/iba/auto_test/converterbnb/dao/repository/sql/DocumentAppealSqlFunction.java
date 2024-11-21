package eu.iba.auto_test.converterbnb.dao.repository.sql;

import eu.iba.auto_test.converterbnb.dao.model.Document;
import eu.iba.auto_test.converterbnb.dao.repository.mapper.DocumentAppealRowMapper;
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

//Обращения
public class DocumentAppealSqlFunction extends SqlFunction<Document> {

    private static final String SQL ="SELECT DISTINCT TOP(10) rkk.XRecID as id, rkk.Soder as shortSummary, " +
            "rkk.ClaimType as citizenTypeId, ct.NameAn as citizenType, rkk.Polzovatel as authorId, " +
            "author.NameAn as authorFullName, author.Dop as authorLoginAD, rkk.Dop as regNumber, rkk.DataTime as regDate, " +
            "rkk.Soder2 as fioApplicant, rkk.Org as organizationId, citizenOrg.NameAn as organizationName, " +
            "rkk.ClaimFile as citizenNomenclatureAffairId, citizenNa.NameAn as citizenNomenclatureAffairName, " +
            "rkk.Date2 as receiptDate, rkk.Dop4 as fullAddress " +
            "FROM MBAnalit rkk " +
            "LEFT JOIN MBAnalit ct ON rkk.ClaimType = ct.Analit and ct.Vid = 3336 " +
            "LEFT JOIN MBAnalit author ON rkk.Polzovatel = author.Analit and author.Vid = 3119 " +
            "LEFT JOIN MBAnalit citizenOrg ON rkk.Org = citizenOrg.Analit and citizenOrg.Vid = 266 " +
            "LEFT JOIN MBAnalit citizenNa ON rkk.ClaimFile = citizenNa.Analit and citizenNa.Vid = 3162 " +
            "WHERE rkk.Vid = 3329 " +
            "AND rkk.XRecID > :nextId ORDER BY rkk.XRecID ";

    private final DocumentAppealRowMapper rowMapper;

    public DocumentAppealSqlFunction(DataSource ds, Map<String, Object> mapParam) {
        super(ds, SQL);
        this.rowMapper = new DocumentAppealRowMapper();
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
    protected Document mapRow(@NotNull ResultSet rs, int rowNum) throws SQLException {
        return rowMapper.mapRow(rs, rowNum);
    }

}