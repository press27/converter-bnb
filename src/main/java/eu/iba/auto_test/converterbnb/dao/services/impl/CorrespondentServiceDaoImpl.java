package eu.iba.auto_test.converterbnb.dao.services.impl;

import eu.iba.auto_test.converterbnb.dao.model.Correspondent;
import eu.iba.auto_test.converterbnb.dao.repository.sql.CorrespondentSqlFunction;
import eu.iba.auto_test.converterbnb.dao.services.CorrespondentServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CorrespondentServiceDaoImpl implements CorrespondentServiceDao {

    private final DataSource ds;
    private final UploadService uploadService;

    @Autowired
    public CorrespondentServiceDaoImpl(DataSource ds, UploadService uploadService) {
        this.ds = ds;
        this.uploadService = uploadService;
    }

    @Override
    public void saveAll() {
        Long nextId = 0L;
        Map<String, Object> param = createParamSql(nextId);
        List<Correspondent> correspondents = new CorrespondentSqlFunction(ds, param).executeByNamedParam(param);
        while (!correspondents.isEmpty()){
            for (Correspondent correspondent : correspondents) {
                uploadService.uploadCorrespondent(correspondent);
                nextId = correspondent.getId();
            }
            param = createParamSql(nextId);
            correspondents = new CorrespondentSqlFunction(ds, param).executeByNamedParam(param);
        }
    }

    private Map<String, Object> createParamSql(Long nextId) {
        Map<String, Object> param = new HashMap<>();
        param.put("nextId", nextId);
        return param;
    }
}
