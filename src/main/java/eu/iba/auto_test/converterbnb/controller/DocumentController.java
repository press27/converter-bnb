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

    @GetMapping(produces = "application/json")
    @Transactional(readOnly = true)
    public ResponseEntity<?> saveAll() {
        documentServiceDao.saveAll();
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/v2", produces = "application/json")
    @Transactional(readOnly = true)
    public ResponseEntity<?> saveAllV2() {
        documentServiceDao.saveAllV2();
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/v3", produces = "application/json")
    @Transactional(readOnly = true)
    public ResponseEntity<?> saveAllV3() {
        documentServiceDao.saveAllV3();
        return ResponseEntity.ok().build();
    }

}
