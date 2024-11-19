package eu.iba.auto_test.converterbnb.dao.services;

import eu.iba.auto_test.converterbnb.controller.data.IntroductionData;
import eu.iba.auto_test.converterbnb.dao.model.Introduction;

import java.util.List;

public interface IntroductionServiceDao {

    List<Introduction> findAll(IntroductionData data);

}
