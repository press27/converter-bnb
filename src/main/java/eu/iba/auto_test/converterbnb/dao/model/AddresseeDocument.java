package eu.iba.auto_test.converterbnb.dao.model;

import java.time.Instant;

public class AddresseeDocument {

    // Id записи XRecID
    private Long addresseeId;

    // Наименование адресата
    private String addresseeName;

    // Полное Наименование адресата
    private String addresseeFullName;

    // Кому отправить
    private String toPeople;

    // Способ доставки
    private String deliveryMethod;

    // Дата отправки
    private Instant dateSend;

    public AddresseeDocument() {
    }

    public Long getAddresseeId() {
        return addresseeId;
    }

    public AddresseeDocument setAddresseeId(Long addresseeId) {
        this.addresseeId = addresseeId;
        return this;
    }

    public String getAddresseeName() {
        return addresseeName;
    }

    public AddresseeDocument setAddresseeName(String addresseeName) {
        this.addresseeName = addresseeName;
        return this;
    }

    public String getAddresseeFullName() {
        return addresseeFullName;
    }

    public AddresseeDocument setAddresseeFullName(String addresseeFullName) {
        this.addresseeFullName = addresseeFullName;
        return this;
    }

    public String getToPeople() {
        return toPeople;
    }

    public AddresseeDocument setToPeople(String toPeople) {
        this.toPeople = toPeople;
        return this;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public AddresseeDocument setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
        return this;
    }

    public Instant getDateSend() {
        return dateSend;
    }

    public AddresseeDocument setDateSend(Instant dateSend) {
        this.dateSend = dateSend;
        return this;
    }
}
