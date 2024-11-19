package eu.iba.auto_test.converterbnb.dao.model;

import java.util.Objects;

public class Correspondent {

    // Id записи XRecID
    private Long id;

    //Наименование
    private String name;

    //Наименование полное?
    private String fullName;

    //УНП
    private String unp;

    //Юридический адрес
    private String address;

    public Correspondent() {
    }

    public Long getId() {
        return id;
    }

    public Correspondent setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Correspondent setName(String name) {
        this.name = name;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public Correspondent setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getUnp() {
        return unp;
    }

    public Correspondent setUnp(String unp) {
        this.unp = unp;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Correspondent setAddress(String address) {
        this.address = address;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Correspondent that = (Correspondent) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(fullName, that.fullName) && Objects.equals(unp, that.unp) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, fullName, unp, address);
    }
}
