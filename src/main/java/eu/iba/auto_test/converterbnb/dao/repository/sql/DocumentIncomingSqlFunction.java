package eu.iba.auto_test.converterbnb.dao.repository.sql;

import eu.iba.auto_test.converterbnb.dao.model.Document;
import eu.iba.auto_test.converterbnb.dao.repository.mapper.DocumentIncomingRowMapper;
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

//входящий
public class DocumentIncomingSqlFunction extends SqlFunction<Document> {

    private static final String SQL ="SELECT DISTINCT TOP(10) rkk.XRecID as id, rkk.PriznDok as documentCategoryConstants, " +
            "rkk.Org as correspondentId, correspondent.NameAn as correspondentName, correspondent.LongString5 as correspondentFullName, " +
            "correspondent.INN as correspondentUnp, correspondent.NOVSmdoCode as correspondentSmdoCode, rkk.Dop2 as outRegNumber, rkk.Date2 as outRegDate, " +
            "rkk.Soder as shortSummary, rkk.DocumentKind as documentTypeId, dk.NameAn as documentType, rkk.Polzovatel as authorId, " +
            "author.NameAn as authorFullName, author.Dop as authorLoginAD, rkk.Dop as regNumber, rkk.DataTime as regDate, " +
            "rkk.Delo as nomenclatureAffairId, na.NameAn as nomenclatureAffairName, rkk.Data7 as nomenclatureAffairDate, " +
            "rkk.Stroka3 as inDocSigners, delivery.NameAn as deliveryMethod " +
            "FROM MBAnalit rkk " +
            "LEFT JOIN MBAnalit correspondent ON rkk.Org = correspondent.Analit and correspondent.Vid = 266 " +
            "LEFT JOIN MBAnalit dk ON rkk.DocumentKind = dk.Analit and dk.Vid = 3153 " +
            "LEFT JOIN MBAnalit author ON rkk.Polzovatel = author.Analit and author.Vid = 3119 " +
            "LEFT JOIN MBAnalit na ON rkk.Delo = na.Analit and na.Vid = 3162 " +
            "LEFT JOIN MBAnalit delivery ON rkk.SposobDost = delivery.Analit and delivery.Vid = 3195 " +
            "WHERE rkk.PriznDok = 'В' AND rkk.Vid = 3174 " +
            "AND rkk.XRecID > :nextId ORDER BY rkk.XRecID ";

    private final DocumentIncomingRowMapper rowMapper;

    public DocumentIncomingSqlFunction(DataSource ds, Map<String, Object> mapParam) {
        super(ds, SQL);
        this.rowMapper = new DocumentIncomingRowMapper();
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
        declareParameter(new SqlParameter("nextId", Types.OTHER));
    }

    @Override
    protected Document mapRow(@NotNull ResultSet rs, int rowNum) throws SQLException {
        return rowMapper.mapRow(rs, rowNum);
    }

}
