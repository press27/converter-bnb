package eu.iba.auto_test.converterbnb.dao.model;

import java.time.Instant;
import java.util.Objects;

public class Signature {

    // Id записи XRecID
    private Long id;

    // Id карточки документа
    private Long docCardId;

    // Id rkk
    private Long rkkId;

    // Автор подписи электронного документа
    private Author author;

    // Дата подписания
    private Instant signDate;

    // Пользователь, от имени которого поставлена подпись
    private Employee replaceableUser;

    // ЭЦП подпись
    private String data;

    //Тип подписи:
    //«В» – Визирующая
    //«У» – Утверждающая
    //«Н» – Нет
    private String signatureType;

    // Примечание к подписи
    private String comment;

    public Signature() {
    }

    public Long getId() {
        return id;
    }

    public Signature setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getDocCardId() {
        return docCardId;
    }

    public Signature setDocCardId(Long docCardId) {
        this.docCardId = docCardId;
        return this;
    }

    public Long getRkkId() {
        return rkkId;
    }

    public Signature setRkkId(Long rkkId) {
        this.rkkId = rkkId;
        return this;
    }

    public String getData() {
        return data;
    }

    public Signature setData(String data) {
        this.data = data;
        return this;
    }

    public Author getAuthor() {
        return author;
    }

    public Signature setAuthor(Author author) {
        this.author = author;
        return this;
    }

    public Instant getSignDate() {
        return signDate;
    }

    public Signature setSignDate(Instant signDate) {
        this.signDate = signDate;
        return this;
    }

    public Employee getReplaceableUser() {
        return replaceableUser;
    }

    public Signature setReplaceableUser(Employee replaceableUser) {
        this.replaceableUser = replaceableUser;
        return this;
    }

    public String getSignatureType() {
        return signatureType;
    }

    public Signature setSignatureType(String signatureType) {
        this.signatureType = signatureType;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public Signature setComment(String comment) {
        this.comment = comment;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Signature signature = (Signature) o;
        return Objects.equals(id, signature.id) && Objects.equals(docCardId, signature.docCardId) && Objects.equals(rkkId, signature.rkkId) && Objects.equals(author, signature.author) && Objects.equals(signDate, signature.signDate) && Objects.equals(replaceableUser, signature.replaceableUser) && Objects.equals(data, signature.data) && Objects.equals(signatureType, signature.signatureType) && Objects.equals(comment, signature.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, docCardId, rkkId, author, signDate, replaceableUser, data, signatureType, comment);
    }
}
