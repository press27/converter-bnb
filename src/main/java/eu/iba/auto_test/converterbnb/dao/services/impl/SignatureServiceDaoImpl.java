package eu.iba.auto_test.converterbnb.dao.services.impl;

import eu.iba.auto_test.converterbnb.controller.data.SignatureData;
import eu.iba.auto_test.converterbnb.dao.model.Signature;
import eu.iba.auto_test.converterbnb.dao.repository.sql.SignatureSqlFunction;
import eu.iba.auto_test.converterbnb.dao.services.SignatureServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SignatureServiceDaoImpl implements SignatureServiceDao {

    private final DataSource ds;

    @Autowired
    public SignatureServiceDaoImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public List<Signature> findAll(SignatureData data) {
        Map<String, Object> param = createParamSql(data);
        return new SignatureSqlFunction(ds, param).executeByNamedParam(param);
    }

    private Map<String, Object> createParamSql(SignatureData data) {
        Map<String, Object> param = new HashMap<>();
        param.put("docCardId", data.getDocCardId());
        return param;
    }
}
