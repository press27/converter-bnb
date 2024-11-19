package eu.iba.auto_test.converterbnb.dao.model;

// Вид обращения
public class CitizenType {

    // Id вида обращения
    private Long citizenTypeId;

    // Наименование вида обращения
    private String citizenType;

    public CitizenType() {
    }

    public Long getCitizenTypeId() {
        return citizenTypeId;
    }

    public CitizenType setCitizenTypeId(Long citizenTypeId) {
        this.citizenTypeId = citizenTypeId;
        return this;
    }

    public String getCitizenType() {
        return citizenType;
    }

    public CitizenType setCitizenType(String citizenType) {
        this.citizenType = citizenType;
        return this;
    }
}
