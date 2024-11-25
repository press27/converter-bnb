package eu.iba.auto_test.converterbnb.dao.services.impl;

import eu.iba.auto_test.converterbnb.controller.data.*;
import eu.iba.auto_test.converterbnb.dao.model.*;
import eu.iba.auto_test.converterbnb.dao.repository.sql.*;
import eu.iba.auto_test.converterbnb.dao.services.*;
import eu.iba.auto_test.converterbnb.utils.TaskUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.*;

import static eu.iba.auto_test.converterbnb.dao.model.DocumentCategoryConstants.*;
import static eu.iba.auto_test.converterbnb.dao.model.DocumentCategoryConstants.APPEAL;

@Service
public class DocumentServiceDaoImpl implements DocumentServiceDao {

    private final DataSource ds;
    private final AttachmentDocumentServiceDao attachmentDocumentServiceDao;
    private final HistoryServiceDao historyServiceDao;
    private final IntroductionServiceDao introductionServiceDao;
    private final TaskServiceDao taskServiceDao;
    private final TaskExecutorsServiceDao taskExecutorsServiceDao;
    private final GeneralInfoServiceDao generalInfoServiceDao;
    private final SignatureServiceDao signatureServiceDao;
    private final UploadService uploadService;

    @Autowired
    public DocumentServiceDaoImpl(DataSource ds, AttachmentDocumentServiceDao attachmentDocumentServiceDao, HistoryServiceDao historyServiceDao, IntroductionServiceDao introductionServiceDao, TaskServiceDao taskServiceDao, TaskExecutorsServiceDao taskExecutorsServiceDao, GeneralInfoServiceDao generalInfoServiceDao, SignatureServiceDao signatureServiceDao, UploadService uploadService) {
        this.ds = ds;
        this.attachmentDocumentServiceDao = attachmentDocumentServiceDao;
        this.historyServiceDao = historyServiceDao;
        this.introductionServiceDao = introductionServiceDao;
        this.taskServiceDao = taskServiceDao;
        this.taskExecutorsServiceDao = taskExecutorsServiceDao;
        this.generalInfoServiceDao = generalInfoServiceDao;
        this.signatureServiceDao = signatureServiceDao;
        this.uploadService = uploadService;
    }

    @Override
    public void saveAll() {
        List<DocumentCategoryConstants> documentCategoryConstants = new ArrayList<>(Arrays.asList(INTERN, INCOMING, OUTGOING, APPEAL));
        for(DocumentCategoryConstants documentCategoryConstant: documentCategoryConstants) {
            Long nextId = 0L;
            List<Document> documents = getDocs(nextId, documentCategoryConstant);
            while (!documents.isEmpty()) {
                for (Document document : documents) {
                    if (document.getId() != null) {
                        AttachmentData attachmentData = new AttachmentData();
                        attachmentData.setRkkId(document.getId());
                        Set<AttachmentDocument> attachmentDocuments = attachmentDocumentServiceDao.findAll(attachmentData);

                        HistoryData historyData = new HistoryData();
                        if (documentCategoryConstant == DocumentCategoryConstants.APPEAL) {
                            historyData.setReferenceTypeId(3329L);
                        } else {
                            historyData.setReferenceTypeId(3174L);
                        }
                        historyData.setSrcRecId(document.getId());
                        List<History> histories = historyServiceDao.findHistory(historyData);
                        HistoryDocumentData historyDocumentData = new HistoryDocumentData();
                        historyDocumentData.setRkkId(document.getId());
                        List<History> historiesDocument = historyServiceDao.findHistoryDocument(historyDocumentData);
                        histories.addAll(historiesDocument);
                        histories.sort(Comparator.comparing(History::getDateAction));
                        document.setHistories(histories); // сохраняю историю в док.

                        if (documentCategoryConstant != DocumentCategoryConstants.APPEAL) { // в обращениях нет ознакомления
                            IntroductionData introductionData = new IntroductionData();
                            introductionData.setRkkId(document.getId());
                            List<Introduction> introductions = introductionServiceDao.findAll(introductionData);
                            document.setIntroductions(introductions); // сохраняю ознакомителей в док.
                            Set<Long> taskIntroductionIds = new HashSet<>();
                            for (Introduction introduction : introductions) {
                                if (introduction.getTaskId() != null) {
                                    taskIntroductionIds.add(introduction.getTaskId());
                                }
                            }
                            for (Long taskId : taskIntroductionIds) {
                                AttachmentData attachmentIntroductionData = new AttachmentData();
                                attachmentIntroductionData.setAttachType(AttachType.E);
                                attachmentIntroductionData.setTaskId(taskId);
                                attachmentIntroductionData.setName("Лист ознакомления");
                                Set<AttachmentDocument> introductionSheets = attachmentDocumentServiceDao.findAllInTask(attachmentIntroductionData);
                                attachmentDocuments.addAll(introductionSheets); // добавляю листы ознакомления(вложение) в общий список
                            }
                        }

                        TaskData taskData = new TaskData();
                        taskData.setRkkId(document.getId());
                        List<Task> tasks = taskServiceDao.findAll(taskData); // поручения
                        if(tasks != null) {
                            for (Task task : tasks) {
                                TaskExecutorsData taskExecutorsData = new TaskExecutorsData();
                                taskExecutorsData.setTaskId(task.getId());
                                Set<TaskExecutors> taskExecutors = taskExecutorsServiceDao.findAll(taskExecutorsData); // исполнители поручения
                                task.setTaskExecutors(taskExecutors);

                                HistoryData historyData1 = new HistoryData();
                                historyData1.setReferenceTypeId(3342L);
                                historyData1.setSrcRecId(task.getId());
                                List<History> historyTask1 = historyServiceDao.findHistory(historyData1); // история поручения

                                HistoryTaskData historyTaskData = new HistoryTaskData();
                                historyTaskData.setTaskId(task.getId());
                                List<History> historyTask2 = historyServiceDao.findHistoryTask(historyTaskData); // история поручения
                                historyTask1.addAll(historyTask2);
                                historyTask1.sort(Comparator.comparing(History::getDateAction));
                                task.setTaskHistory(historyTask1); // сохраняю историю в поручение
                            }
                            List<Task> treeTask = TaskUtils.getTreeTasks(tasks); // сохраняю поручения в виде дерева
                            document.setTasks(treeTask); // сохраняю поручения в док.
                        }

                        generalInfoServiceDao.findAllEmployeeReview(document, taskData);
                        generalInfoServiceDao.findAllEmployeeRegistration(document, taskData);
                        ProcessAttachmentTaskData processAttachmentTaskData = getData(document.getAttachmentDocuments());
                        if (!processAttachmentTaskData.getDocCardIds().isEmpty()) {
                            generalInfoServiceDao.findAllEmployeeByProcess(document, processAttachmentTaskData);
                        }

                        for (AttachmentDocument attachmentDocument : attachmentDocuments) {
                            SignatureData signatureData = new SignatureData();
                            signatureData.setDocCardId(attachmentDocument.getDocCardId());
                            List<Signature> signatures = signatureServiceDao.findAll(signatureData);
                            if (!signatures.isEmpty()) {
                                attachmentDocument.setSignatures(signatures);
                            }
                        }

                        document.setAttachmentDocuments(attachmentDocuments); // сохраняю вложения в док.
                        uploadService.uploadDocument(document);
                        nextId = document.getId();
                    }
                }
                documents = getDocs(nextId, documentCategoryConstant);
            }
        }
    }

    @Override
    public void saveAllV2() {
        List<DocumentCategoryConstants> documentCategoryConstants = new ArrayList<>(Arrays.asList(INTERN, INCOMING, OUTGOING, APPEAL));
        for(DocumentCategoryConstants documentCategoryConstant: documentCategoryConstants) {
            int index = 10;
            int count = 0;
            Long nextId = 0L;
            List<Document> documents = getDocs(nextId, documentCategoryConstant);
            while (!documents.isEmpty()) {
                for (Document document : documents) {
                    if (document.getId() != null) {
//                        AttachmentData attachmentData = new AttachmentData();
//                        attachmentData.setRkkId(document.getId());
//                        Set<AttachmentDocument> attachmentDocuments = attachmentDocumentServiceDao.findAll(attachmentData);

                        HistoryData historyData = new HistoryData();
                        if (documentCategoryConstant == DocumentCategoryConstants.APPEAL) {
                            historyData.setReferenceTypeId(3329L);
                        } else {
                            historyData.setReferenceTypeId(3174L);
                        }
                        historyData.setSrcRecId(document.getId());
                        List<History> histories = historyServiceDao.findHistory(historyData);
                        HistoryDocumentData historyDocumentData = new HistoryDocumentData();
                        historyDocumentData.setRkkId(document.getId());
                        List<History> historiesDocument = historyServiceDao.findHistoryDocument(historyDocumentData);
                        histories.addAll(historiesDocument);
                        histories.sort(Comparator.comparing(History::getDateAction));
                        document.setHistories(histories); // сохраняю историю в док.

                        if (documentCategoryConstant != DocumentCategoryConstants.APPEAL) { // в обращениях нет ознакомления
                            IntroductionData introductionData = new IntroductionData();
                            introductionData.setRkkId(document.getId());
                            List<Introduction> introductions = introductionServiceDao.findAll(introductionData);
                            document.setIntroductions(introductions); // сохраняю ознакомителей в док.
                            Set<Long> taskIntroductionIds = new HashSet<>();
                            for (Introduction introduction : introductions) {
                                if (introduction.getTaskId() != null) {
                                    taskIntroductionIds.add(introduction.getTaskId());
                                }
                            }
//                            for (Long taskId : taskIntroductionIds) {
//                                AttachmentData attachmentIntroductionData = new AttachmentData();
//                                attachmentIntroductionData.setAttachType(AttachType.E);
//                                attachmentIntroductionData.setTaskId(taskId);
//                                attachmentIntroductionData.setName("Лист ознакомления");
//                                Set<AttachmentDocument> introductionSheets = attachmentDocumentServiceDao.findAllInTask(attachmentIntroductionData);
//                                attachmentDocuments.addAll(introductionSheets); // добавляю листы ознакомления(вложение) в общий список
//                            }
                        }

                        TaskData taskData = new TaskData();
                        taskData.setRkkId(document.getId());
                        List<Task> tasks = taskServiceDao.findAll(taskData); // поручения
                        if(tasks != null) {
                            for (Task task : tasks) {
                                TaskExecutorsData taskExecutorsData = new TaskExecutorsData();
                                taskExecutorsData.setTaskId(task.getId());
                                Set<TaskExecutors> taskExecutors = taskExecutorsServiceDao.findAll(taskExecutorsData); // исполнители поручения
                                task.setTaskExecutors(taskExecutors);

                                HistoryData historyData1 = new HistoryData();
                                historyData1.setReferenceTypeId(3342L);
                                historyData1.setSrcRecId(task.getId());
                                List<History> historyTask1 = historyServiceDao.findHistory(historyData1); // история поручения

                                HistoryTaskData historyTaskData = new HistoryTaskData();
                                historyTaskData.setTaskId(task.getId());
                                List<History> historyTask2 = historyServiceDao.findHistoryTask(historyTaskData); // история поручения
                                historyTask1.addAll(historyTask2);
                                historyTask1.sort(Comparator.comparing(History::getDateAction));
                                task.setTaskHistory(historyTask1); // сохраняю историю в поручение
                            }
                            List<Task> treeTask = TaskUtils.getTreeTasks(tasks); // сохраняю поручения в виде дерева
                            document.setTasks(treeTask); // сохраняю поручения в док.
                        }

//                        generalInfoServiceDao.findAllEmployeeReview(document, taskData);
//                        generalInfoServiceDao.findAllEmployeeRegistration(document, taskData);
//                        ProcessAttachmentTaskData processAttachmentTaskData = getData(document.getAttachmentDocuments());
//                        if (!processAttachmentTaskData.getDocCardIds().isEmpty()) {
//                            generalInfoServiceDao.findAllEmployeeByProcess(document, processAttachmentTaskData);
//                        }

//                        for (AttachmentDocument attachmentDocument : attachmentDocuments) {
//                            SignatureData signatureData = new SignatureData();
//                            signatureData.setDocCardId(attachmentDocument.getDocCardId());
//                            List<Signature> signatures = signatureServiceDao.findAll(signatureData);
//                            if (!signatures.isEmpty()) {
//                                attachmentDocument.setSignatures(signatures);
//                            }
//                        }

//                        document.setAttachmentDocuments(attachmentDocuments); // сохраняю вложения в док.
                        uploadService.uploadDocument(document);
                        nextId = document.getId();
                    }
                }
                count++;
                documents = getDocs(nextId, documentCategoryConstant);
                if(count == index){
                    documents = new ArrayList<>();
                }
            }
        }
    }

    private List<Document> getDocs(Long nextId, DocumentCategoryConstants documentCategoryConstants){
        Map<String, Object> param = createParamSql(nextId);
        List<Document> documents = new ArrayList<>();
        switch (documentCategoryConstants) {
            case INTERN -> documents = new DocumentInternSqlFunction(ds, param).executeByNamedParam(param);
            case INCOMING -> documents = new DocumentIncomingSqlFunction(ds, param).executeByNamedParam(param);
            case OUTGOING -> documents = new DocumentOutgoingSqlFunction(ds, param).executeByNamedParam(param);
            case APPEAL -> documents = new DocumentAppealSqlFunction(ds, param).executeByNamedParam(param);
        }
        return documents;
    }

    private Map<String, Object> createParamSql(Long nextId) {
        Map<String, Object> param = new HashMap<>();
//        if (data.getRkkIds() != null){
//            param.put("rkkIds", data.getRkkIds());
//        }
        param.put("nextId", nextId);
        return param;
    }

    private ProcessAttachmentTaskData getData(Set<AttachmentDocument> attachmentDocuments){
        ProcessAttachmentTaskData data = new ProcessAttachmentTaskData();
        Set<Long> docCardIds = new HashSet<>();
        for (AttachmentDocument attachmentDocument: attachmentDocuments) {
            docCardIds.add(attachmentDocument.getDocCardId());
        }
        data.setDocCardIds(docCardIds);
        return data;
    }
}
