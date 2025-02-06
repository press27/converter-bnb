package eu.iba.auto_test.converterbnb.dao.repository.sql;

import eu.iba.auto_test.converterbnb.dao.model.NomenclatureAffair;
import eu.iba.auto_test.converterbnb.dao.repository.mapper.NomenclatureAffairRowMapper;
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

public class NomenclatureAffairSqlFunction extends SqlFunction<NomenclatureAffair> {

    private static final String SQL ="SELECT DISTINCT TOP(20) na.XRecID as id, na.Podr as departmentId, d.NameAn as departmentName, " +
            "na.Soder as nameAffair, na.FileStoragePeriod as storagePeriodId, sp.NameAn as storagePeriodName, " +
            "na.Recv as indexAffair, na.DatOpen as collectingStart, na.DatClose as collectingEnd, na.Prim as note " +
            "FROM MBAnalit na " +
            "LEFT JOIN MBAnalit d on na.Podr = d.XRecID and d.Vid = 446 " +
            "LEFT JOIN MBAnalit sp on na.FileStoragePeriod = sp.XRecID and sp.Vid = 3309 " +
            "WHERE na.Vid = 3162 AND na.DatOpen < '2025-01-01 00:00:00.000' " +
            "AND na.XRecID > :nextId ORDER BY na.XRecID ";

    private final NomenclatureAffairRowMapper rowMapper;

    public NomenclatureAffairSqlFunction(DataSource ds,  Map<String, Object> paramMap) {
        super(ds, SQL);
        this.rowMapper = new NomenclatureAffairRowMapper();
        changeSql(paramMap);
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
    protected NomenclatureAffair mapRow(@NotNull ResultSet rs, int rowNum) throws SQLException {
        return rowMapper.mapRow(rs, rowNum);
    }


}
