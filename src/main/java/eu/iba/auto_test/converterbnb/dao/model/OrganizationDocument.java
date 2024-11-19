package eu.iba.auto_test.converterbnb.dao.model;

public class OrganizationDocument {

    // Id организации
    private Long organizationId;

    // Организация заявителя (Юр. лицо)
    private String organizationName;

    public OrganizationDocument() {
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public OrganizationDocument setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public OrganizationDocument setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
        return this;
    }
}
