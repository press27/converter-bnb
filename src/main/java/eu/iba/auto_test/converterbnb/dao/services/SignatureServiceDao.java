package eu.iba.auto_test.converterbnb.dao.services;

import eu.iba.auto_test.converterbnb.controller.data.SignatureData;
import eu.iba.auto_test.converterbnb.dao.model.Signature;

import java.util.List;

public interface SignatureServiceDao {

    List<Signature> findAll(SignatureData data);

}
