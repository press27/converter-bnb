package eu.iba.auto_test.converterbnb.controller;

import eu.iba.auto_test.converterbnb.dao.services.DocumentServiceDao;
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
//
//    @GetMapping(value = "/{rkkId}", produces = "application/json")
//    @Transactional(readOnly = true)
//    public ResponseEntity<?> saveOne(@PathVariable Long rkkId) {
//        documentServiceDao.saveOne(rkkId);
//        return ResponseEntity.ok().build();
//    }

}
