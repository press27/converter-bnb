package eu.iba.auto_test.converterbnb.dao.model;

import java.time.Instant;
import java.util.Objects;

public class NomenclatureAffair {

    // Id записи XRecID
    private Long id;

    // Id Подразделение
    private Long departmentId;

    // Наименование подразделение
    private String departmentName;

    // Заголовок дела
    private String nameAffair;

    // Id Срока хранения
    private Long storagePeriodId;

    // Наименование Срока хранения
    private String storagePeriodName;

    // Индекс дела
    private String index;

    // Дата начала комплектования
    private Instant collectingStart;

    // Дата окончания комплектования
    private Instant collectingEnd;

    // Примечание
    private String note;

    public NomenclatureAffair() {
    }

    public Long getId() {
        return id;
    }

    public NomenclatureAffair setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public NomenclatureAffair setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public NomenclatureAffair setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
        return this;
    }

    public String getNameAffair() {
        return nameAffair;
    }

    public NomenclatureAffair setNameAffair(String nameAffair) {
        this.nameAffair = nameAffair;
        return this;
    }

    public Long getStoragePeriodId() {
        return storagePeriodId;
    }

    public NomenclatureAffair setStoragePeriodId(Long storagePeriodId) {
        this.storagePeriodId = storagePeriodId;
        return this;
    }

    public String getStoragePeriodName() {
        return storagePeriodName;
    }

    public NomenclatureAffair setStoragePeriodName(String storagePeriodName) {
        this.storagePeriodName = storagePeriodName;
        return this;
    }

    public String getIndex() {
        return index;
    }

    public NomenclatureAffair setIndex(String index) {
        this.index = index;
        return this;
    }

    public Instant getCollectingStart() {
        return collectingStart;
    }

    public NomenclatureAffair setCollectingStart(Instant collectingStart) {
        this.collectingStart = collectingStart;
        return this;
    }

    public Instant getCollectingEnd() {
        return collectingEnd;
    }

    public NomenclatureAffair setCollectingEnd(Instant collectingEnd) {
        this.collectingEnd = collectingEnd;
        return this;
    }

    public String getNote() {
        return note;
    }

    public NomenclatureAffair setNote(String note) {
        this.note = note;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NomenclatureAffair that = (NomenclatureAffair) o;
        return Objects.equals(id, that.id) && Objects.equals(departmentId, that.departmentId) && Objects.equals(departmentName, that.departmentName) && Objects.equals(nameAffair, that.nameAffair) && Objects.equals(storagePeriodId, that.storagePeriodId) && Objects.equals(storagePeriodName, that.storagePeriodName) && Objects.equals(index, that.index) && Objects.equals(collectingStart, that.collectingStart) && Objects.equals(collectingEnd, that.collectingEnd) && Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departmentId, departmentName, nameAffair, storagePeriodId, storagePeriodName, index, collectingStart, collectingEnd, note);
    }
}
