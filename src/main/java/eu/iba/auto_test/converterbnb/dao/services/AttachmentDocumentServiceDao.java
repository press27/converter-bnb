package eu.iba.auto_test.converterbnb.dao.services;

import eu.iba.auto_test.converterbnb.controller.data.AttachmentData;
import eu.iba.auto_test.converterbnb.dao.model.AttachmentDocument;

import java.util.Set;

public interface AttachmentDocumentServiceDao {

    Set<AttachmentDocument> findAll(AttachmentData data);

    Set<AttachmentDocument> findAllInTask(AttachmentData data);

}
