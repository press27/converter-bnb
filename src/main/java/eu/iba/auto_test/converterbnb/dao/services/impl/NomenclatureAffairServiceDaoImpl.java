package eu.iba.auto_test.converterbnb.dao.services.impl;

import eu.iba.auto_test.converterbnb.dao.model.NomenclatureAffair;
import eu.iba.auto_test.converterbnb.dao.repository.sql.NomenclatureAffairSqlFunction;
import eu.iba.auto_test.converterbnb.dao.services.NomenclatureAffairServiceDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NomenclatureAffairServiceDaoImpl implements NomenclatureAffairServiceDao {

    private final DataSource ds;
    private final UploadService uploadService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public NomenclatureAffairServiceDaoImpl(DataSource ds, UploadService uploadService) {
        this.ds = ds;
        this.uploadService = uploadService;
    }

    @Override
    public void saveAll() {
        Long nextId = 0L;
        Map<String, Object> param = createParamSql(nextId);
        List<NomenclatureAffair> nomenclatureAffairs = new NomenclatureAffairSqlFunction(ds, param).executeByNamedParam(param);
        while (!nomenclatureAffairs.isEmpty()){
            for (NomenclatureAffair nomenclatureAffair : nomenclatureAffairs) {
                try {
                    uploadService.uploadNomenclatureAffair(nomenclatureAffair);
                } catch (Exception e) {
                    logger.error("Process nomenclature affair with id: {} {}", nomenclatureAffair.getId(), e.getMessage(), e);
                }
                nextId = nomenclatureAffair.getId();
            }
            param = createParamSql(nextId);
            nomenclatureAffairs = new NomenclatureAffairSqlFunction(ds, param).executeByNamedParam(param);
        }
    }

    private Map<String, Object> createParamSql(Long nextId) {
        Map<String, Object> param = new HashMap<>();
        param.put("nextId", nextId);
        return param;
    }
}
