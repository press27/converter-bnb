package eu.iba.auto_test.converterbnb.dao.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.iba.auto_test.converterbnb.controller.data.*;
import eu.iba.auto_test.converterbnb.dao.model.*;
import eu.iba.auto_test.converterbnb.dao.repository.sql.document_one.DocumentAppealOneSqlFunction;
import eu.iba.auto_test.converterbnb.dao.repository.sql.document_one.DocumentIncomingOneSqlFunction;
import eu.iba.auto_test.converterbnb.dao.repository.sql.document_one.DocumentInternOneSqlFunction;
import eu.iba.auto_test.converterbnb.dao.repository.sql.document_one.DocumentOutgoingOneSqlFunction;
import eu.iba.auto_test.converterbnb.dao.services.*;
import eu.iba.auto_test.converterbnb.utils.AttachmentUtils;
import eu.iba.auto_test.converterbnb.utils.DocumentUtils;
import eu.iba.auto_test.converterbnb.utils.SignatureUtils;
import eu.iba.auto_test.converterbnb.utils.TaskUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DocumentServiceDaoV2Impl implements DocumentServiceDaoV2 {

    public static final Logger log = LoggerFactory.getLogger(DocumentServiceDaoImpl.class);

    private final DataSource ds;
    private final AttachmentDocumentServiceDao attachmentDocumentServiceDao;
    private final HistoryServiceDao historyServiceDao;
    private final IntroductionServiceDao introductionServiceDao;
    private final TaskDocumentServiceDao taskDocumentServiceDao;
    private final TaskExecutorsServiceDao taskExecutorsServiceDao;
    private final GeneralInfoServiceDao generalInfoServiceDao;
    private final SignatureServiceDao signatureServiceDao;
    private final TaskCommentServiceDao taskCommentServiceDao;
    private final UploadService uploadService;
    private final static Long MAX_SIZE =  10000000L;

    public DocumentServiceDaoV2Impl(DataSource ds, AttachmentDocumentServiceDao attachmentDocumentServiceDao, HistoryServiceDao historyServiceDao, IntroductionServiceDao introductionServiceDao, TaskDocumentServiceDao taskDocumentServiceDao, TaskExecutorsServiceDao taskExecutorsServiceDao, GeneralInfoServiceDao generalInfoServiceDao, SignatureServiceDao signatureServiceDao, TaskCommentServiceDao taskCommentServiceDao, UploadService uploadService) {
        this.ds = ds;
        this.attachmentDocumentServiceDao = attachmentDocumentServiceDao;
        this.historyServiceDao = historyServiceDao;
        this.introductionServiceDao = introductionServiceDao;
        this.taskDocumentServiceDao = taskDocumentServiceDao;
        this.taskExecutorsServiceDao = taskExecutorsServiceDao;
        this.generalInfoServiceDao = generalInfoServiceDao;
        this.signatureServiceDao = signatureServiceDao;
        this.taskCommentServiceDao = taskCommentServiceDao;
        this.uploadService = uploadService;
    }

    @Override
    public void saveOneByTypeAndId(DocumentCategoryConstants documentCategoryConstants, Long rkkId) {
        Document document = getDoc(rkkId, documentCategoryConstants);
        if (document.getId() != null) {
            try {
                AttachmentData attachmentData = new AttachmentData();
                attachmentData.setRkkId(document.getId());
                Set<AttachmentDocument> attachmentDocuments = AttachmentUtils.deleteAttachmentWithNullData(attachmentDocumentServiceDao.findAll(attachmentData));

                HistoryData historyData = new HistoryData();
                if (documentCategoryConstants == DocumentCategoryConstants.APPEAL) {
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

                if (documentCategoryConstants != DocumentCategoryConstants.APPEAL) { // в обращениях нет ознакомления
                    if (document.getIntroductionIds() != null && !document.getIntroductionIds().isEmpty()) {
                        List<Introduction> introductions = new ArrayList<>();
                        for (Long introductionId : document.getIntroductionIds()) {
                            IntroductionData introductionData = new IntroductionData();
                            introductionData.setRkkId(document.getId());
                            introductionData.setIntroductionId(introductionId);
                            List<Introduction> introductionsIter = introductionServiceDao.findAll(introductionData);
                            introductions.addAll(introductionsIter);
                            Set<Long> taskIntroductionIds = new HashSet<>();
                            for (Introduction introduction : introductionsIter) {
                                if (introduction.getTaskId() != null) {
                                    taskIntroductionIds.add(introduction.getTaskId());
                                }
                            }
                            for (Long taskId : taskIntroductionIds) {
                                AttachmentData attachmentIntroductionData = new AttachmentData();
                                attachmentIntroductionData.setAttachType(AttachType.E);
                                attachmentIntroductionData.setTaskId(taskId);
                                attachmentIntroductionData.setName("Лист ознакомления");
                                Set<AttachmentDocument> introductionSheets = AttachmentUtils.deleteAttachmentWithNullData(attachmentDocumentServiceDao.findAllInTask(attachmentIntroductionData));
                                attachmentDocuments.addAll(introductionSheets); // добавляю листы ознакомления(вложение) в общий список
                            }
                        }
                        document.setIntroductions(introductions); // сохраняю ознакомителей в док.
                    }
                }

                TaskData taskData = new TaskData();
                taskData.setRkkId(document.getId());
                List<Task> tasks = taskDocumentServiceDao.findAll(taskData); // поручения
                Set<Instant> instantSet = new HashSet<>(); // даты создания поручений
                long indexNano = 1;
                if (tasks != null && !tasks.isEmpty()) {
                    for (Task task : tasks) {
                        if (instantSet.contains(task.getCreateDate())) {
                            task.setCreateDate(task.getCreateDate().plusNanos(indexNano));
                            instantSet.add(task.getCreateDate());
                            indexNano++;
                        } else {
                            instantSet.add(task.getCreateDate());
                        }
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

                generalInfoServiceDao.findAllEmployeeAndAttachmentReview(document, taskData);
                generalInfoServiceDao.findAllEmployeeAndAttachmentRegistration(document, taskData);
                ProcessAttachmentTaskData processAttachmentTaskData = getData(document.getAttachmentDocuments());
                if (!processAttachmentTaskData.getDocCardIds().isEmpty()) {
                    generalInfoServiceDao.findAllEmployeeAndAttachmentByProcess(document, processAttachmentTaskData);
                }
                generalInfoServiceDao.findAllAttachmentByTask(document, document.getId());

                for (AttachmentDocument attachmentDocument : attachmentDocuments) {
                    attachmentDocument.setData(AttachmentUtils.hexToBase64(attachmentDocument.getData()));
                    SignatureData signatureData = new SignatureData();
                    signatureData.setDocCardId(attachmentDocument.getDocCardId());
                    List<Signature> signatures = SignatureUtils.deleteSignatureWithNullData(signatureServiceDao.findAll(signatureData));
                    if (!signatures.isEmpty()) {
                        attachmentDocument.setSignatures(signatures);
                    }
                }

                document.setAttachmentDocuments(attachmentDocuments); // сохраняю вложения в док.

                List<TaskComment> comments = taskCommentServiceDao.findAllByRkkId(document.getId());
                if (comments != null && !comments.isEmpty()) {
                    document.setTaskComments(comments);
                    Set<Employee> employees = new HashSet<>();
                    for (TaskComment taskComment : comments) {
                        employees.add(taskComment.getAuthor());
                    }
                    document.getEmployeesAccess().addAll(employees);
                }

                saveJson(document.getId().toString(), document);
                log.info("saved document id: {}", rkkId);
                uploadService.uploadDocument(document);
            } catch (Exception e) {
                saveToFileErrorDocIds(document.getId().toString());
                Long size = AttachmentUtils.calculateSizeAllAttachmentsByDocument(document);
                log.error("Process document with id: {}, size attachments: {} {}", document.getId(), size, e.getMessage(), e);
            }
        }
    }

    @Override
    public void saveOneByTypeAndIds(DocumentCategoryConstants documentCategoryConstants, List<Long> ids) {
        int count = 0;
        List<Document> documents = getDocs(ids, documentCategoryConstants);
        documents = DocumentUtils.getUniqueDocuments(documents);
        List<List<Document>> collection = splitIntoCollectionsBySize(documents);
        for (List<Document> documentList: collection) {
            for (Document document : documentList) {
                if (document.getId() != null) {
                    try {
                        AttachmentData attachmentData = new AttachmentData();
                        attachmentData.setRkkId(document.getId());
                        Set<AttachmentDocument> attachmentDocuments = AttachmentUtils.deleteAttachmentWithNullData(attachmentDocumentServiceDao.findAll(attachmentData));

                        HistoryData historyData = new HistoryData();
                        if (documentCategoryConstants == DocumentCategoryConstants.APPEAL) {
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

                        if (documentCategoryConstants != DocumentCategoryConstants.APPEAL) { // в обращениях нет ознакомления
                            if (document.getIntroductionIds() != null && !document.getIntroductionIds().isEmpty()) {
                                List<Introduction> introductions = new ArrayList<>();
                                for (Long introductionId : document.getIntroductionIds()) {
                                    IntroductionData introductionData = new IntroductionData();
                                    introductionData.setRkkId(document.getId());
                                    introductionData.setIntroductionId(introductionId);
                                    List<Introduction> introductionsIter = introductionServiceDao.findAll(introductionData);
                                    introductions.addAll(introductionsIter);
                                    Set<Long> taskIntroductionIds = new HashSet<>();
                                    for (Introduction introduction : introductionsIter) {
                                        if (introduction.getTaskId() != null) {
                                            taskIntroductionIds.add(introduction.getTaskId());
                                        }
                                    }
                                    for (Long taskId : taskIntroductionIds) {
                                        AttachmentData attachmentIntroductionData = new AttachmentData();
                                        attachmentIntroductionData.setAttachType(AttachType.E);
                                        attachmentIntroductionData.setTaskId(taskId);
                                        attachmentIntroductionData.setName("Лист ознакомления");
                                        Set<AttachmentDocument> introductionSheets = AttachmentUtils.deleteAttachmentWithNullData(attachmentDocumentServiceDao.findAllInTask(attachmentIntroductionData));
                                        attachmentDocuments.addAll(introductionSheets); // добавляю листы ознакомления(вложение) в общий список
                                    }
                                }
                                document.setIntroductions(introductions); // сохраняю ознакомителей в док.
                            }
                        }

                        TaskData taskData = new TaskData();
                        taskData.setRkkId(document.getId());
                        List<Task> tasks = taskDocumentServiceDao.findAll(taskData); // поручения
                        Set<Instant> instantSet = new HashSet<>(); // даты создания поручений
                        long indexNano = 1;
                        if (tasks != null && !tasks.isEmpty()) {
                            for (Task task : tasks) {
                                if (instantSet.contains(task.getCreateDate())) {
                                    task.setCreateDate(task.getCreateDate().plusNanos(indexNano));
                                    instantSet.add(task.getCreateDate());
                                    indexNano++;
                                } else {
                                    instantSet.add(task.getCreateDate());
                                }
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

                        generalInfoServiceDao.findAllEmployeeAndAttachmentReview(document, taskData);
                        generalInfoServiceDao.findAllEmployeeAndAttachmentRegistration(document, taskData);
                        ProcessAttachmentTaskData processAttachmentTaskData = getData(document.getAttachmentDocuments());
                        if (!processAttachmentTaskData.getDocCardIds().isEmpty()) {
                            generalInfoServiceDao.findAllEmployeeAndAttachmentByProcess(document, processAttachmentTaskData);
                        }
                        generalInfoServiceDao.findAllAttachmentByTask(document, document.getId());

                        for (AttachmentDocument attachmentDocument : attachmentDocuments) {
                            attachmentDocument.setData(AttachmentUtils.hexToBase64(attachmentDocument.getData()));
                            SignatureData signatureData = new SignatureData();
                            signatureData.setDocCardId(attachmentDocument.getDocCardId());
                            List<Signature> signatures = SignatureUtils.deleteSignatureWithNullData(signatureServiceDao.findAll(signatureData));
                            if (!signatures.isEmpty()) {
                                attachmentDocument.setSignatures(signatures);
                            }
                        }

                        document.setAttachmentDocuments(attachmentDocuments); // сохраняю вложения в док.

                        List<TaskComment> comments = taskCommentServiceDao.findAllByRkkId(document.getId());
                        if (comments != null && !comments.isEmpty()) {
                            document.setTaskComments(comments);
                            Set<Employee> employees = new HashSet<>();
                            for (TaskComment taskComment : comments) {
                                employees.add(taskComment.getAuthor());
                            }
                            document.getEmployeesAccess().addAll(employees);
                        }

                        saveJson(document.getId().toString(), document);
                        count++;
                    } catch (Exception e) {
                        saveToFileErrorDocIds(document.getId().toString());
                        Long size = AttachmentUtils.calculateSizeAllAttachmentsByDocument(document);
                        log.error("Process document with id: {} , size attachments: {} {}", document.getId().toString(), size, e.getMessage(), e);
                    }
                }
            }
            log.info("saved documents count: {}", count);
            log.info("type: {}", documentCategoryConstants);

            String strIds = documentList.stream().map(Document::getId).map(String::valueOf).collect(Collectors.joining(", "));
            Long size = AttachmentUtils.calculateSizeAllAttachmentsByDocuments(documentList);
            try {
                uploadService.uploadListDocument(documentList);
            } catch (Exception e) {
                saveToFileErrorDocIds(strIds);
                log.error("Process documents with ids: {} , size attachments: {} {}", strIds, size, e.getMessage(), e);
            }
        }
    }

    private List<List<Document>> splitIntoCollectionsBySize(List<Document> documents){
        List<List<Document>> collection = new ArrayList<>();
        if(!documents.isEmpty()) {
            Long overallSize = 0L;
            List<Document> listDoc = new ArrayList<>();
            for (Document document : documents) {
                if (document.getAttachmentDocuments() != null) {
                    overallSize = overallSize + calculateSizeAttachments(document.getAttachmentDocuments());
                    if (overallSize > MAX_SIZE) {
                        if (!listDoc.isEmpty()) {
                            List<Document> docs = new ArrayList<>(listDoc);
                            collection.add(docs);
                            listDoc.clear();
                        }
                        overallSize = calculateSizeAttachments(document.getAttachmentDocuments());
                    }
                    listDoc.add(document);
                } else {
                    listDoc.add(document);
                }
            }
            List<Document> docs = new ArrayList<>(listDoc);
            collection.add(docs);
            listDoc.clear();
        }
        return collection;
    }


    private Long calculateSizeAttachments(Set<AttachmentDocument> attachmentDocuments){
        Long size = 0L;
        for (AttachmentDocument attachmentDocument: attachmentDocuments){
            size = size + attachmentDocument.getSize();
        }
        return size;
    }

    private Document getDoc(Long rkkId, DocumentCategoryConstants documentCategoryConstants){
        Map<String, Object> param = createParamSql(rkkId);
        Document document = new Document();
        switch (documentCategoryConstants) {
            case INTERN -> document = new DocumentInternOneSqlFunction(ds, param).findObjectByNamedParam(param);
            case INCOMING -> document = new DocumentIncomingOneSqlFunction(ds, param).findObjectByNamedParam(param);
            case OUTGOING -> document = new DocumentOutgoingOneSqlFunction(ds, param).findObjectByNamedParam(param);
            case APPEAL -> document = new DocumentAppealOneSqlFunction(ds, param).findObjectByNamedParam(param);
        }
        return document;
    }

    private List<Document> getDocs(List<Long> rkkIds, DocumentCategoryConstants documentCategoryConstants){
        Map<String, Object> param = createParamSqlList(rkkIds);
        List<Document> documents = new ArrayList<>();
        switch (documentCategoryConstants) {
            case INTERN -> documents = new DocumentInternOneSqlFunction(ds, param).executeByNamedParam(param);
            case INCOMING -> documents = new DocumentIncomingOneSqlFunction(ds, param).executeByNamedParam(param);
            case OUTGOING -> documents = new DocumentOutgoingOneSqlFunction(ds, param).executeByNamedParam(param);
            case APPEAL -> documents = new DocumentAppealOneSqlFunction(ds, param).executeByNamedParam(param);
        }
        return documents;
    }

    private Map<String, Object> createParamSql(Long rkkId) {
        Map<String, Object> param = new HashMap<>();
        param.put("rkkId", rkkId);
        return param;
    }

    private Map<String, Object> createParamSqlList(List<Long> rkkIds) {
        Map<String, Object> param = new HashMap<>();
        if (rkkIds != null && !rkkIds.isEmpty()){
            param.put("rkkIds", rkkIds);
        }
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

    //TODO после всех тестов убрать со всех мест где вызывается
    public static void saveJson(String file_prefix, Object request) {
        try {
            Path filePath = Paths.get("c:\\json\\" + file_prefix + ".json");
            if (!Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }
            ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
            Files.write(filePath, mapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request), java.nio.file.StandardOpenOption.APPEND, java.nio.file.StandardOpenOption.CREATE);
        } catch (Exception ex){
            log.error(ex.getMessage(),ex);
        }
    }

    public static void saveToFileErrorDocIds(String ids) {
        try {
            Path filePath = Paths.get("c:\\error-doc\\" + "error-file" + ".txt");
            if (!Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }
            String info = ids + ",";
            Files.write(filePath, info.getBytes(), java.nio.file.StandardOpenOption.APPEND, java.nio.file.StandardOpenOption.CREATE);
        } catch (Exception ex) {
            log.error(ex.getMessage(),ex);
        }
    }

}
