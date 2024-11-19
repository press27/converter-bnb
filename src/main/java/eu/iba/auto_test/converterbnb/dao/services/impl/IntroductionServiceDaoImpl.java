package eu.iba.auto_test.converterbnb.dao.services.impl;

import eu.iba.auto_test.converterbnb.controller.data.IntroductionData;
import eu.iba.auto_test.converterbnb.dao.model.Introduction;
import eu.iba.auto_test.converterbnb.dao.repository.sql.IntroductionSqlFunction;
import eu.iba.auto_test.converterbnb.dao.services.IntroductionServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IntroductionServiceDaoImpl implements IntroductionServiceDao {

    private final DataSource ds;

    @Autowired
    public IntroductionServiceDaoImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public List<Introduction> findAll(IntroductionData data) {
        Map<String, Object> param = createParamSql(data);
        return new IntroductionSqlFunction(ds, param).executeByNamedParam(param);
    }

    private Map<String, Object> createParamSql(IntroductionData data) {
        Map<String, Object> param = new HashMap<>();
        param.put("rkkId", data.getRkkId());
        return param;
    }
}
