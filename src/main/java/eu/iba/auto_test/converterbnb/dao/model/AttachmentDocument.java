package eu.iba.auto_test.converterbnb.dao.model;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

public class AttachmentDocument {

    // Id записи XRecID
    private Long id;

    // Id карточки документа
    private Long docCardId;

//    // Id ркк
//    private Long docRkkId;

    // Id Задачи
    private Long taskId;

    // Автор
    private Employee author;

    // Дата загрузки/дата создания
    private Instant uploadDate;

    // Наименование электронного документа
    private String name;

    // Тип файла (расширение)
    private String contentType;

    // Размер файла
    private Long size;

    // Данные файла
    private String data;

    // CRC
    private String crc;

    private List<Signature> signatures;

    public AttachmentDocument() {
    }

    public Long getId() {
        return id;
    }

    public AttachmentDocument setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getDocCardId() {
        return docCardId;
    }

    public AttachmentDocument setDocCardId(Long docCardId) {
        this.docCardId = docCardId;
        return this;
    }

    public Long getTaskId() {
        return taskId;
    }

    public AttachmentDocument setTaskId(Long taskId) {
        this.taskId = taskId;
        return this;
    }


    public Employee getAuthor() {
        return author;
    }

    public AttachmentDocument setAuthor(Employee author) {
        this.author = author;
        return this;
    }

    public Instant getUploadDate() {
        return uploadDate;
    }

    public AttachmentDocument setUploadDate(Instant uploadDate) {
        this.uploadDate = uploadDate;
        return this;
    }

    public String getName() {
        return name;
    }

    public AttachmentDocument setName(String name) {
        this.name = name;
        return this;
    }

    public Long getSize() {
        return size;
    }

    public AttachmentDocument setSize(Long size) {
        this.size = size;
        return this;
    }

    public String getContentType() {
        return contentType;
    }

    public AttachmentDocument setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public String getData() {
        return data;
    }

    public AttachmentDocument setData(String data) {
        this.data = data;
        return this;
    }

    public String getCrc() {
        return crc;
    }

    public AttachmentDocument setCrc(String crc) {
        this.crc = crc;
        return this;
    }

    public List<Signature> getSignatures() {
        return signatures;
    }

    public AttachmentDocument setSignatures(List<Signature> signatures) {
        this.signatures = signatures;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttachmentDocument that = (AttachmentDocument) o;
        return Objects.equals(id, that.id) && Objects.equals(docCardId, that.docCardId) && Objects.equals(taskId, that.taskId) && Objects.equals(author, that.author) && Objects.equals(uploadDate, that.uploadDate) && Objects.equals(name, that.name) && Objects.equals(contentType, that.contentType) && Objects.equals(size, that.size) && Objects.equals(data, that.data) && Objects.equals(crc, that.crc) && Objects.equals(signatures, that.signatures);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, docCardId, taskId, author, uploadDate, name, contentType, size, data, crc, signatures);
    }
}
