package eu.iba.auto_test.converterbnb.dao.model;

public class CorrespondentDocument {

    // Id записи XRecID
    private Long correspondentId;

    // Наименование корреспондента
    private String correspondentName;

    // Полное Наименование корреспондента
    private String correspondentFullName;

    // УНП
    private String correspondentUnp;

    // СМДО код
    private String correspondentSmdoCode;

    public CorrespondentDocument() {
    }

    public Long getCorrespondentId() {
        return correspondentId;
    }

    public CorrespondentDocument setCorrespondentId(Long correspondentId) {
        this.correspondentId = correspondentId;
        return this;
    }

    public String getCorrespondentName() {
        return correspondentName;
    }

    public CorrespondentDocument setCorrespondentName(String correspondentName) {
        this.correspondentName = correspondentName;
        return this;
    }

    public String getCorrespondentFullName() {
        return correspondentFullName;
    }

    public CorrespondentDocument setCorrespondentFullName(String correspondentFullName) {
        this.correspondentFullName = correspondentFullName;
        return this;
    }

    public String getCorrespondentUnp() {
        return correspondentUnp;
    }

    public CorrespondentDocument setCorrespondentUnp(String correspondentUnp) {
        this.correspondentUnp = correspondentUnp;
        return this;
    }

    public String getCorrespondentSmdoCode() {
        return correspondentSmdoCode;
    }

    public CorrespondentDocument setCorrespondentSmdoCode(String correspondentSmdoCode) {
        this.correspondentSmdoCode = correspondentSmdoCode;
        return this;
    }
}
