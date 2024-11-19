package eu.iba.auto_test.converterbnb.dao.model;

import java.time.Instant;
import java.util.Objects;

public class NomenclatureAffairDocument {

    // Id записи XRecID
    private Long nomenclatureAffairId;

    // Наименование номенклатуры дела
    private String nomenclatureAffairName;

    // Дата списания в дело (у обращений почему то этого поля нет)
    private Instant nomenclatureAffairDate;

    public NomenclatureAffairDocument() {
    }

    public Long getNomenclatureAffairId() {
        return nomenclatureAffairId;
    }

    public NomenclatureAffairDocument setNomenclatureAffairId(Long nomenclatureAffairId) {
        this.nomenclatureAffairId = nomenclatureAffairId;
        return this;
    }

    public String getNomenclatureAffairName() {
        return nomenclatureAffairName;
    }

    public NomenclatureAffairDocument setNomenclatureAffairName(String nomenclatureAffairName) {
        this.nomenclatureAffairName = nomenclatureAffairName;
        return this;
    }

    public Instant getNomenclatureAffairDate() {
        return nomenclatureAffairDate;
    }

    public NomenclatureAffairDocument setNomenclatureAffairDate(Instant nomenclatureAffairDate) {
        this.nomenclatureAffairDate = nomenclatureAffairDate;
        return this;
    }
}
