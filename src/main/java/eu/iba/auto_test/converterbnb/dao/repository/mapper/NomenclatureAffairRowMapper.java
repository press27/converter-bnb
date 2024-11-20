package eu.iba.auto_test.converterbnb.dao.repository.mapper;

import eu.iba.auto_test.converterbnb.dao.model.NomenclatureAffair;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

public class NomenclatureAffairRowMapper implements RowMapper<NomenclatureAffair> {

    @Override
    public NomenclatureAffair mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            NomenclatureAffair model = new NomenclatureAffair();
            model.setId(rs.getLong("id"));
            model.setDepartmentId(rs.getLong("departmentId"));
            model.setDepartmentName(rs.getString("departmentName"));
            model.setNameAffair(rs.getString("nameAffair"));
            model.setStoragePeriodId(rs.getLong("storagePeriodId"));
            model.setStoragePeriodName(rs.getString("storagePeriodName"));
            String indexAffair = rs.getString("indexAffair");
            if(indexAffair != null && !indexAffair.isEmpty()) {
                model.setIndex(indexAffair.trim());
            }
            Timestamp collectingStart = rs.getTimestamp("collectingStart");
            if(collectingStart != null){
                model.setCollectingStart(collectingStart.toInstant());
            }
            Timestamp collectingEnd = rs.getTimestamp("collectingEnd");
            if(collectingEnd != null){
                model.setCollectingEnd(collectingEnd.toInstant());
            }
            model.setNote(rs.getString("note"));
            return model;
        } catch (Exception e){
            throw new SQLException(e);
        }
    }

}
