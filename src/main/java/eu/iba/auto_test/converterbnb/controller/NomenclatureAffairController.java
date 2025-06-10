package eu.iba.auto_test.converterbnb.controller;

import eu.iba.auto_test.converterbnb.dao.services.NomenclatureAffairServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
@RequestMapping(value = "api/bnb/nomenclature-affair")
public class NomenclatureAffairController {

    private final NomenclatureAffairServiceDao nomenclatureAffairServiceDao;
    private final ConcurrentHashMap<String, AtomicBoolean> locks = new ConcurrentHashMap<>();

    @Autowired
    public NomenclatureAffairController(NomenclatureAffairServiceDao nomenclatureAffairServiceDao) {
        this.nomenclatureAffairServiceDao = nomenclatureAffairServiceDao;
    }

    @GetMapping(value = "/save-all", produces = "application/json")
    public ResponseEntity<?> getCollection() {
        String key = "save-all";
        if (!acquireLock(key)) {
            return ResponseEntity.status(429).body("Operation is already in progress");
        }
        try {
            nomenclatureAffairServiceDao.saveAll();
            return ResponseEntity.ok().build();
        } finally {
            releaseLock(key);
        }
    }

    @GetMapping(value = "/update", produces = "application/json")
    public ResponseEntity<?> getCollectionUpdate() {
        String key = "update";
        if (!acquireLock(key)) {
            return ResponseEntity.status(429).body("Operation is already in progress");
        }
        try {
            nomenclatureAffairServiceDao.update();
            return ResponseEntity.ok().build();
        } finally {
            releaseLock(key);
        }
    }

    private boolean acquireLock(String key) {
        return locks.computeIfAbsent(key, k -> new AtomicBoolean(false)).compareAndSet(false, true);
    }

    private void releaseLock(String key) {
        locks.getOrDefault(key, new AtomicBoolean(false)).set(false);
    }

}
