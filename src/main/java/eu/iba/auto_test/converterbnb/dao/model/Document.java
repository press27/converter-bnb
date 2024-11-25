package eu.iba.auto_test.converterbnb.dao.model;

import java.time.Instant;
import java.util.*;

// РКК
public class Document {

    // Id ркк XRecID
    private Long id;

    // Тип документа
    private DocumentCategoryConstants documentCategoryConstants;

    // Вид документа
    private DocumentType documentType;

    // Вид обращения
    private CitizenType citizenType;

    // Заголовок документа
    private String shortSummary;

    // Автор РКК
    private Author author;

    // Корреспондент
    private CorrespondentDocument correspondent;

    // Адресат
    private AddresseeDocument addressee;

    // Исх. № корреспондента
    private String outRegNumber;

    // Дата документа корреспондента
    private Instant outRegDate;

    // Регистрационный номер
    private String regNumber;

    // Дата регистрации
    private Instant regDate;

    // Кем подписан (для внутреннего и исходящего документа)
    private Employee whoSigned;

    // Кем подписан (для входящего документа)
    private String inDocSigners;

    // Способ доставки для входящего документа
    private String deliveryMethod;

    // Признак, является заявитель физическим или юридическим лицом
    private DeclarantType declarantType;

    // Дата поступления обращения
    private Instant receiptDate;

    // Адрес в обращениях
    private String fullAddress;

    // ФИО заявителя (Физ. лицо)
    private String fioApplicant;

    // Организация заявителя (Юр. лицо)
    private OrganizationDocument organization;

    // Номенклатуры в которые списано ркк (предварительно у них (БНБ) только одна)
    private NomenclatureAffairDocument nomenclatureAffairDocument;

    // Признак коллективного обращения
    private Boolean collective = false;

    // Признак анонимного обращения
    private Boolean anonymous = false;

    // Вложения документа
    private Set<AttachmentDocument> attachmentDocuments = new HashSet<>();

    // История ркк
    private List<History> histories = new ArrayList<>(); // состоит из истории ркк и документов

    // Поручения
    private List<Task> tasks = new ArrayList<>();

    // Ознакомление
    private List<Introduction> introductions = new ArrayList<>();

    // Список пользователей для предоставления доступа к документу
    private Set<Employee> employeesAccess = new HashSet<>();

    // Ссылки на другие документ(РКК) 3176???


    public Document() {
    }

    public Long getId() {
        return id;
    }

    public Document setId(Long id) {
        this.id = id;
        return this;
    }

    public DocumentCategoryConstants getDocumentCategoryConstants() {
        return documentCategoryConstants;
    }

    public Document setDocumentCategoryConstants(DocumentCategoryConstants documentCategoryConstants) {
        this.documentCategoryConstants = documentCategoryConstants;
        return this;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public Document setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
        return this;
    }

    public CitizenType getCitizenType() {
        return citizenType;
    }

    public Document setCitizenType(CitizenType citizenType) {
        this.citizenType = citizenType;
        return this;
    }

    public String getShortSummary() {
        return shortSummary;
    }

    public Document setShortSummary(String shortSummary) {
        this.shortSummary = shortSummary;
        return this;
    }

    public Author getAuthor() {
        return author;
    }

    public Document setAuthor(Author author) {
        this.author = author;
        return this;
    }

    public CorrespondentDocument getCorrespondent() {
        return correspondent;
    }

    public Document setCorrespondent(CorrespondentDocument correspondent) {
        this.correspondent = correspondent;
        return this;
    }

    public AddresseeDocument getAddressee() {
        return addressee;
    }

    public Document setAddressee(AddresseeDocument addressee) {
        this.addressee = addressee;
        return this;
    }

    public String getOutRegNumber() {
        return outRegNumber;
    }

    public Document setOutRegNumber(String outRegNumber) {
        this.outRegNumber = outRegNumber;
        return this;
    }

    public Instant getOutRegDate() {
        return outRegDate;
    }

    public Document setOutRegDate(Instant outRegDate) {
        this.outRegDate = outRegDate;
        return this;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public Document setRegNumber(String regNumber) {
        this.regNumber = regNumber;
        return this;
    }

    public Instant getRegDate() {
        return regDate;
    }

    public Document setRegDate(Instant regDate) {
        this.regDate = regDate;
        return this;
    }

    public DeclarantType getDeclarantType() {
        return declarantType;
    }

    public Document setDeclarantType(DeclarantType declarantType) {
        this.declarantType = declarantType;
        return this;
    }

    public String getFioApplicant() {
        return fioApplicant;
    }

    public Document setFioApplicant(String fioApplicant) {
        this.fioApplicant = fioApplicant;
        return this;
    }

    public OrganizationDocument getOrganization() {
        return organization;
    }

    public Document setOrganization(OrganizationDocument organization) {
        this.organization = organization;
        return this;
    }

    public NomenclatureAffairDocument getNomenclatureAffairDocument() {
        return nomenclatureAffairDocument;
    }

    public Document setNomenclatureAffairDocument(NomenclatureAffairDocument nomenclatureAffairDocument) {
        this.nomenclatureAffairDocument = nomenclatureAffairDocument;
        return this;
    }

    public Set<AttachmentDocument> getAttachmentDocuments() {
        return attachmentDocuments;
    }

    public Document setAttachmentDocuments(Set<AttachmentDocument> attachmentDocuments) {
        this.attachmentDocuments = attachmentDocuments;
        return this;
    }

    public List<History> getHistories() {
        return histories;
    }

    public Document setHistories(List<History> histories) {
        this.histories = histories;
        return this;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Document setTasks(List<Task> tasks) {
        this.tasks = tasks;
        return this;
    }

    public List<Introduction> getIntroductions() {
        return introductions;
    }

    public Document setIntroductions(List<Introduction> introductions) {
        this.introductions = introductions;
        return this;
    }

    public Employee getWhoSigned() {
        return whoSigned;
    }

    public Document setWhoSigned(Employee whoSigned) {
        this.whoSigned = whoSigned;
        return this;
    }

    public String getInDocSigners() {
        return inDocSigners;
    }

    public Document setInDocSigners(String inDocSigners) {
        this.inDocSigners = inDocSigners;
        return this;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public Document setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
        return this;
    }

    public Instant getReceiptDate() {
        return receiptDate;
    }

    public Document setReceiptDate(Instant receiptDate) {
        this.receiptDate = receiptDate;
        return this;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public Document setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
        return this;
    }

    public Set<Employee> getEmployeesAccess() {
        return employeesAccess;
    }

    public Document setEmployeesAccess(Set<Employee> employeesAccess) {
        this.employeesAccess = employeesAccess;
        return this;
    }

    public Boolean getCollective() {
        return collective;
    }

    public Document setCollective(Boolean collective) {
        this.collective = collective;
        return this;
    }

    public Boolean getAnonymous() {
        return anonymous;
    }

    public Document setAnonymous(Boolean anonymous) {
        this.anonymous = anonymous;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return Objects.equals(id, document.id) && documentCategoryConstants == document.documentCategoryConstants && Objects.equals(documentType, document.documentType) && Objects.equals(citizenType, document.citizenType) && Objects.equals(shortSummary, document.shortSummary) && Objects.equals(author, document.author) && Objects.equals(correspondent, document.correspondent) && Objects.equals(addressee, document.addressee) && Objects.equals(outRegNumber, document.outRegNumber) && Objects.equals(outRegDate, document.outRegDate) && Objects.equals(regNumber, document.regNumber) && Objects.equals(regDate, document.regDate) && Objects.equals(whoSigned, document.whoSigned) && Objects.equals(inDocSigners, document.inDocSigners) && Objects.equals(deliveryMethod, document.deliveryMethod) && declarantType == document.declarantType && Objects.equals(receiptDate, document.receiptDate) && Objects.equals(fullAddress, document.fullAddress) && Objects.equals(fioApplicant, document.fioApplicant) && Objects.equals(organization, document.organization) && Objects.equals(nomenclatureAffairDocument, document.nomenclatureAffairDocument) && Objects.equals(collective, document.collective) && Objects.equals(anonymous, document.anonymous) && Objects.equals(attachmentDocuments, document.attachmentDocuments) && Objects.equals(histories, document.histories) && Objects.equals(tasks, document.tasks) && Objects.equals(introductions, document.introductions) && Objects.equals(employeesAccess, document.employeesAccess);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, documentCategoryConstants, documentType, citizenType, shortSummary, author, correspondent, addressee, outRegNumber, outRegDate, regNumber, regDate, whoSigned, inDocSigners, deliveryMethod, declarantType, receiptDate, fullAddress, fioApplicant, organization, nomenclatureAffairDocument, collective, anonymous, attachmentDocuments, histories, tasks, introductions, employeesAccess);
    }
}
