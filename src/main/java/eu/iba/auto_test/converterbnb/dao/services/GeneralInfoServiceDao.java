package eu.iba.auto_test.converterbnb.dao.services;

import eu.iba.auto_test.converterbnb.controller.data.ProcessAttachmentTaskData;
import eu.iba.auto_test.converterbnb.controller.data.TaskData;
import eu.iba.auto_test.converterbnb.dao.model.Document;

public interface GeneralInfoServiceDao {

    void findAllEmployeeAndAttachmentReview(Document document, TaskData data);

    void findAllEmployeeAndAttachmentRegistration(Document document, TaskData data);

    void findAllEmployeeAndAttachmentByProcess(Document document, ProcessAttachmentTaskData data);

    void findAllAttachmentByTask(Document document, Long rkkId);

}
