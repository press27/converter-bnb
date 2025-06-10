package eu.iba.auto_test.converterbnb.dao.model;

import java.util.Objects;

public class Recipient {

    // Id rkk XRecID
    private Long id;

    // Адресаты (1 и больше)
    private String recipientName;

    public Recipient() {
    }

    public Long getId() {
        return id;
    }

    public Recipient setId(Long id) {
        this.id = id;
        return this;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public Recipient setRecipientName(String recipientName) {
        this.recipientName = recipientName;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipient recipient = (Recipient) o;
        return Objects.equals(id, recipient.id) && Objects.equals(recipientName, recipient.recipientName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, recipientName);
    }
}
