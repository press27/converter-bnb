package eu.iba.auto_test.converterbnb.controller.data;

public class HistoryData {

    // Id для идентифицирования справочников (3174 - ркк (вх, исх, внтур), 3329 - (обрщ.), 3342 - поручение)
    private Long referenceTypeId;

    // Id ркк, поручения
    private Long srcRecId;

    public HistoryData() {
    }

    public Long getReferenceTypeId() {
        return referenceTypeId;
    }

    public HistoryData setReferenceTypeId(Long referenceTypeId) {
        this.referenceTypeId = referenceTypeId;
        return this;
    }

    public Long getSrcRecId() {
        return srcRecId;
    }

    public HistoryData setSrcRecId(Long srcRecId) {
        this.srcRecId = srcRecId;
        return this;
    }
}
