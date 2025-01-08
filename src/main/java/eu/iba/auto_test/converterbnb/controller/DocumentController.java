package eu.iba.auto_test.converterbnb.controller;

import eu.iba.auto_test.converterbnb.controller.filter.DocumentFilter;
import eu.iba.auto_test.converterbnb.dao.model.DocumentCategoryConstants;
import eu.iba.auto_test.converterbnb.dao.services.DocumentServiceDao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/bnb/document")
public class DocumentController {

    private final DocumentServiceDao documentServiceDao;

    @Autowired
    public DocumentController(DocumentServiceDao documentServiceDao) {
        this.documentServiceDao = documentServiceDao;
    }

    @GetMapping(value = "/save-all-by-one", produces = "application/json")
    @Transactional(readOnly = true)
    public ResponseEntity<?> saveAllByOne() {
        documentServiceDao.saveAllByOne();
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/save-all-by-list", produces = "application/json")
    @Transactional(readOnly = true)
    public ResponseEntity<?> saveAllByList() {
        documentServiceDao.saveAllByList();
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/save-range-by-one-test-v1", produces = "application/json")
    @Transactional(readOnly = true)
    public ResponseEntity<?> saveRangeByOneTestV1() {
        documentServiceDao.saveRangeByOneTestV1();
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/save-range-by-list-test-v1", produces = "application/json")
    @Transactional(readOnly = true)
    public ResponseEntity<?> saveRangeByListTestV1() {
        documentServiceDao.saveRangeByListTestV1();
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/save-all-by-type/{categoryConstants}", produces = "application/json")
    @Transactional(readOnly = true)
    public ResponseEntity<?> saveAllByType(@PathVariable("categoryConstants") DocumentCategoryConstants categoryConstants) {
        documentServiceDao.saveAllByType(categoryConstants);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/save-all-by-type/{categoryConstants}/next-id/{nextId}", produces = "application/json")
    @Transactional(readOnly = true)
    public ResponseEntity<?> saveAllByTypeAndNextId(@PathVariable("categoryConstants") DocumentCategoryConstants categoryConstants,
                                                    @PathVariable("nextId") Long nextId) {
        documentServiceDao.saveAllByTypeAndNextId(categoryConstants, nextId);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/save-all-by-filter", produces = "application/json")
    @Transactional(readOnly = true)
    public ResponseEntity<?> saveAllByFilter(@Valid DocumentFilter filter) {
        documentServiceDao.saveAllByTypeAndNextId(filter.getDocumentCategoryConstants(), filter.getNextId());
        return ResponseEntity.ok().build();
    }
//
//    @GetMapping(value = "/{rkkId}", produces = "application/json")
//    @Transactional(readOnly = true)
//    public ResponseEntity<?> saveOne(@PathVariable Long rkkId) {
//        documentServiceDao.saveOne(rkkId);
//        return ResponseEntity.ok().build();
//    }

}
