package eu.iba.auto_test.converterbnb.controller.data;

public class SignatureData {

    // Id вложения (карточки)
    private Long docCardId;

    public SignatureData() {
    }

    public Long getDocCardId() {
        return docCardId;
    }

    public SignatureData setDocCardId(Long docCardId) {
        this.docCardId = docCardId;
        return this;
    }
}
