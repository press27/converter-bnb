package eu.iba.auto_test.converterbnb.dao.services;

import eu.iba.auto_test.converterbnb.controller.data.ProcessAttachmentTaskData;
import eu.iba.auto_test.converterbnb.controller.data.TaskData;
import eu.iba.auto_test.converterbnb.dao.model.Document;

public interface GeneralInfoServiceDao {

    void findAllEmployeeReview(Document document, TaskData data);

    void findAllEmployeeRegistration(Document document, TaskData data);

    void findAllEmployeeByProcess(Document document, ProcessAttachmentTaskData data);

}
