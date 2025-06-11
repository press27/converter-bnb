package eu.iba.auto_test.converterbnb.controller;

import eu.iba.auto_test.converterbnb.dao.model.DocumentCategoryConstants;
import eu.iba.auto_test.converterbnb.dao.services.UpdateCitizenTypeServiceDao;
import eu.iba.auto_test.converterbnb.dao.services.UpdateDocumentTypeGeneralServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
@RequestMapping(value = "api/bnb/update-document-type")
public class UpdateDocumentTypeController {

    private final UpdateDocumentTypeGeneralServiceDao updateDocumentTypeGeneralServiceDao;
    private final UpdateCitizenTypeServiceDao updateCitizenTypeServiceDao;
    private final ConcurrentHashMap<String, AtomicBoolean> locks = new ConcurrentHashMap<>();

    @Autowired
    public UpdateDocumentTypeController(UpdateDocumentTypeGeneralServiceDao updateDocumentTypeGeneralServiceDao, UpdateCitizenTypeServiceDao updateCitizenTypeServiceDao) {
        this.updateDocumentTypeGeneralServiceDao = updateDocumentTypeGeneralServiceDao;
        this.updateCitizenTypeServiceDao = updateCitizenTypeServiceDao;
    }

    private boolean acquireLock(String key) {
        return locks.computeIfAbsent(key, k -> new AtomicBoolean(false)).compareAndSet(false, true);
    }

    private void releaseLock(String key) {
        locks.getOrDefault(key, new AtomicBoolean(false)).set(false);
    }

    @GetMapping(value = "/save-all-by-type/{categoryConstants}/count/{count}", produces = "application/json")
    public ResponseEntity<?> saveAllByType(@PathVariable("categoryConstants") DocumentCategoryConstants categoryConstants,
                                           @PathVariable("count") Integer count) {
        String key = "save-all-by-type/" + categoryConstants + "/count/" + count;
        if (!acquireLock(key)) {
            return ResponseEntity.status(429).body("Operation is already in progress");
        }
        try {
            updateDocumentTypeGeneralServiceDao.saveAllByType(categoryConstants, count);
            return ResponseEntity.ok().build();
        } finally {
            releaseLock(key);
        }
    }

    @GetMapping(value = "/save-all-citizen/count/{count}", produces = "application/json")
    public ResponseEntity<?> saveAllCitizen(@PathVariable("count") Integer count) {
        String key = "save-all-citizen/count/" + count;
        if (!acquireLock(key)) {
            return ResponseEntity.status(429).body("Operation is already in progress");
        }
        try {
            updateCitizenTypeServiceDao.saveAll(count);
            return ResponseEntity.ok().build();
        } finally {
            releaseLock(key);
        }
    }

}
