package eu.iba.auto_test.converterbnb.dao.services;

import eu.iba.auto_test.converterbnb.controller.data.HistoryDocumentData;
import eu.iba.auto_test.converterbnb.controller.data.HistoryData;
import eu.iba.auto_test.converterbnb.controller.data.HistoryTaskData;
import eu.iba.auto_test.converterbnb.dao.model.History;

import java.util.List;

public interface HistoryServiceDao {

    List<History> findHistory(HistoryData data);

    List<History> findHistoryDocument(HistoryDocumentData data);

    List<History> findHistoryTask(HistoryTaskData data);

}
