package eu.iba.auto_test.converterbnb.controller;

import eu.iba.auto_test.converterbnb.controller.filter.DocumentFilter;
import eu.iba.auto_test.converterbnb.dao.model.DocumentCategoryConstants;
import eu.iba.auto_test.converterbnb.dao.services.DocumentServiceDao;
import eu.iba.auto_test.converterbnb.dao.services.DocumentServiceDaoV2;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/bnb/document")
public class DocumentController {

    private final DocumentServiceDao documentServiceDao;
    private final DocumentServiceDaoV2 documentServiceDaoV2;

    @Autowired
    public DocumentController(DocumentServiceDao documentServiceDao, DocumentServiceDaoV2 documentServiceDaoV2) {
        this.documentServiceDao = documentServiceDao;
        this.documentServiceDaoV2 = documentServiceDaoV2;
    }

    @GetMapping(value = "/save-all-by-one", produces = "application/json")
    public synchronized ResponseEntity<?> saveAllByOne() {
        documentServiceDao.saveAllByOne();
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/save-all-by-list", produces = "application/json")
    public synchronized ResponseEntity<?> saveAllByList() {
        documentServiceDao.saveAllByList();
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/save-range-by-one-test-v1", produces = "application/json")
    public synchronized ResponseEntity<?> saveRangeByOneTestV1() {
        documentServiceDao.saveRangeByOneTestV1();
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/save-range-by-list-test-v1", produces = "application/json")
    public synchronized ResponseEntity<?> saveRangeByListTestV1() {
        documentServiceDao.saveRangeByListTestV1();
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/save-list-appeal-by-one", produces = "application/json")
    public synchronized ResponseEntity<?> saveListAppealByOne() {
        documentServiceDao.saveListByOneByType(DocumentCategoryConstants.APPEAL);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/save-list-incoming-by-one", produces = "application/json")
    public synchronized ResponseEntity<?> saveListIncomingByOne() {
        documentServiceDao.saveListByOneByType(DocumentCategoryConstants.INCOMING);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/save-list-intern-by-one", produces = "application/json")
    public synchronized ResponseEntity<?> saveListInternByOne() {
        documentServiceDao.saveListByOneByType(DocumentCategoryConstants.INTERN);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/save-list-outgoing-by-one", produces = "application/json")
    public synchronized ResponseEntity<?> saveListOutgoingByOne() {
        documentServiceDao.saveListByOneByType(DocumentCategoryConstants.OUTGOING);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/save-all-by-type/{categoryConstants}", produces = "application/json")
    public synchronized ResponseEntity<?> saveAllByType(@PathVariable("categoryConstants") DocumentCategoryConstants categoryConstants) {
        documentServiceDao.saveAllByType(categoryConstants);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/save-all-by-type/{categoryConstants}/next-id/{nextId}", produces = "application/json")
    public synchronized ResponseEntity<?> saveAllByTypeAndNextId(@PathVariable("categoryConstants") DocumentCategoryConstants categoryConstants,
                                                    @PathVariable("nextId") Long nextId) {
        documentServiceDao.saveAllByTypeAndNextId(categoryConstants, nextId);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/save-all-by-filter", produces = "application/json")
    public synchronized ResponseEntity<?> saveAllByFilter(@Valid DocumentFilter filter) {
        documentServiceDao.saveAllByTypeAndNextId(filter.getDocumentCategoryConstants(), filter.getNextId());
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/save-one-by-type/{categoryConstants}/rkk-id/{rkkId}", produces = "application/json")
    public synchronized ResponseEntity<?> saveOneByTypeAndRkkId(@PathVariable("categoryConstants") DocumentCategoryConstants categoryConstants,
                                                    @PathVariable("rkkId") Long rkkId) {
        documentServiceDaoV2.saveOneByTypeAndId(categoryConstants, rkkId);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/save-one-by-type/{categoryConstants}/rkk-ids/{rkkIds}", produces = "application/json")
    public synchronized ResponseEntity<?> saveOneByTypeAndRkkId(@PathVariable("categoryConstants") DocumentCategoryConstants categoryConstants,
                                                   @PathVariable("rkkIds") List<Long> rkkIds) {
        documentServiceDaoV2.saveOneByTypeAndIds(categoryConstants, rkkIds);
        return ResponseEntity.ok().build();
    }

}
