package eu.iba.auto_test.converterbnb.dao.repository.sql;

import eu.iba.auto_test.converterbnb.dao.model.History;
import eu.iba.auto_test.converterbnb.dao.repository.mapper.HistoryRowMapper;
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

public class HistorySqlFunction extends SqlFunction<History> {

    private static final String SQL ="SELECT DISTINCT protokol.SrcRecID as generalId, protokol.DateAct as dateAction, p.Analit as authorId, p.NameAn as authorFullName, " +
            "p.Dop as authorLoginAD, protokol.Action as action, protokol.Detail as detail, protokol.ReferenceTypeID as referenceTypeId " +
            "FROM XProtokol protokol " +
            "LEFT JOIN MBAnalit p ON protokol.UserID = p.Dop and p.Vid = 3119 " +
            "WHERE " +
            "protokol.ReferenceTypeID = (:referenceTypeId) " +
            "AND protokol.SrcRecID = (:srcRecId) ";

    private final HistoryRowMapper rowMapper;

    public HistorySqlFunction(DataSource ds, Map<String, Object> mapParam) {
        super(ds, SQL);
        this.rowMapper = new HistoryRowMapper();
        changeSql(mapParam);
    }

    private void changeSql(Map<String, Object> paramMap) throws DataAccessException {
        StringBuilder sb = new StringBuilder();
        sb.append(SQL);
        super.setSql(sb.toString());
        declareParameterCustom(paramMap);
    }

    private void declareParameterCustom(Map<String, Object> paramMap) throws InvalidDataAccessApiUsageException {
        declareParameter(new SqlParameter("referenceTypeId", Types.OTHER));
        declareParameter(new SqlParameter("srcRecId", Types.OTHER));
    }

    @Override
    protected History mapRow(@NotNull ResultSet rs, int rowNum) throws SQLException {
        return rowMapper.mapRow(rs, rowNum);
    }

}
