package eu.iba.auto_test.converterbnb.controller;

import eu.iba.auto_test.converterbnb.controller.filter.DocumentFilter;
import eu.iba.auto_test.converterbnb.dao.model.DocumentCategoryConstants;
import eu.iba.auto_test.converterbnb.dao.services.DocumentServiceDao;
import eu.iba.auto_test.converterbnb.dao.services.DocumentServiceDaoV2;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
@RequestMapping(value = "api/bnb/document")
public class DocumentController {

    private final DocumentServiceDao documentServiceDao;
    private final DocumentServiceDaoV2 documentServiceDaoV2;
    private final ConcurrentHashMap<String, AtomicBoolean> locks = new ConcurrentHashMap<>();

    @Autowired
    public DocumentController(DocumentServiceDao documentServiceDao, DocumentServiceDaoV2 documentServiceDaoV2) {
        this.documentServiceDao = documentServiceDao;
        this.documentServiceDaoV2 = documentServiceDaoV2;
    }

    private boolean acquireLock(String key) {
        return locks.computeIfAbsent(key, k -> new AtomicBoolean(false)).compareAndSet(false, true);
    }

    private void releaseLock(String key) {
        locks.getOrDefault(key, new AtomicBoolean(false)).set(false);
    }

    @GetMapping(value = "/save-all-by-one", produces = "application/json")
    public ResponseEntity<?> saveAllByOne() {
        String key = "save-all-by-one";
        if (!acquireLock(key)) {
            return ResponseEntity.status(429).body("Operation is already in progress");
        }
        try {
            documentServiceDao.saveAllByOne();
            return ResponseEntity.ok().build();
        } finally {
            releaseLock(key);
        }
    }

    @GetMapping(value = "/save-all-by-list", produces = "application/json")
    public ResponseEntity<?> saveAllByList() {
        String key = "save-all-by-list";
        if (!acquireLock(key)) {
            return ResponseEntity.status(429).body("Operation is already in progress");
        }
        try {
            documentServiceDao.saveAllByList();
            return ResponseEntity.ok().build();
        } finally {
            releaseLock(key);
        }
    }

    @GetMapping(value = "/save-range-by-one-test-v1", produces = "application/json")
    public ResponseEntity<?> saveRangeByOneTestV1() {
        String key = "save-range-by-one-test-v1";
        if (!acquireLock(key)) {
            return ResponseEntity.status(429).body("Operation is already in progress");
        }
        try {
            documentServiceDao.saveRangeByOneTestV1();
            return ResponseEntity.ok().build();
        } finally {
            releaseLock(key);
        }
    }

    @GetMapping(value = "/save-range-by-list-test-v1", produces = "application/json")
    public ResponseEntity<?> saveRangeByListTestV1() {
        String key = "save-range-by-list-test-v1";
        if (!acquireLock(key)) {
            return ResponseEntity.status(429).body("Operation is already in progress");
        }
        try {
            documentServiceDao.saveRangeByListTestV1();
            return ResponseEntity.ok().build();
        } finally {
            releaseLock(key);
        }
    }

    @GetMapping(value = "/save-list-appeal-by-one", produces = "application/json")
    public ResponseEntity<?> saveListAppealByOne() {
        String key = "save-list-appeal-by-one";
        if (!acquireLock(key)) {
            return ResponseEntity.status(429).body("Operation is already in progress");
        }
        try {
            documentServiceDao.saveListByOneByType(DocumentCategoryConstants.APPEAL);
            return ResponseEntity.ok().build();
        } finally {
            releaseLock(key);
        }
    }

    @GetMapping(value = "/save-list-incoming-by-one", produces = "application/json")
    public ResponseEntity<?> saveListIncomingByOne() {
        String key = "save-list-incoming-by-one";
        if (!acquireLock(key)) {
            return ResponseEntity.status(429).body("Operation is already in progress");
        }
        try {
            documentServiceDao.saveListByOneByType(DocumentCategoryConstants.INCOMING);
            return ResponseEntity.ok().build();
        } finally {
            releaseLock(key);
        }
    }

    @GetMapping(value = "/save-list-intern-by-one", produces = "application/json")
    public ResponseEntity<?> saveListInternByOne() {
        String key = "save-list-intern-by-one";
        if (!acquireLock(key)) {
            return ResponseEntity.status(429).body("Operation is already in progress");
        }
        try {
            documentServiceDao.saveListByOneByType(DocumentCategoryConstants.INTERN);
            return ResponseEntity.ok().build();
        } finally {
            releaseLock(key);
        }
    }

    @GetMapping(value = "/save-list-outgoing-by-one", produces = "application/json")
    public ResponseEntity<?> saveListOutgoingByOne() {
        String key = "save-list-outgoing-by-one";
        if (!acquireLock(key)) {
            return ResponseEntity.status(429).body("Operation is already in progress");
        }
        try {
            documentServiceDao.saveListByOneByType(DocumentCategoryConstants.OUTGOING);
            return ResponseEntity.ok().build();
        } finally {
            releaseLock(key);
        }
    }

    @GetMapping(value = "/save-list-appeal-by-one/{nextId}", produces = "application/json")
    public ResponseEntity<?> saveListAppealByOneAndNextId(@PathVariable("nextId") Long nextId) {
        String key = "save-list-appeal-by-one/"+nextId;
        if (!acquireLock(key)) {
            return ResponseEntity.status(429).body("Operation is already in progress");
        }
        try {
            documentServiceDao.saveListByOneByTypeAndNextId(DocumentCategoryConstants.APPEAL, nextId);
            return ResponseEntity.ok().build();
        } finally {
            releaseLock(key);
        }
    }

    @GetMapping(value = "/save-list-incoming-by-one/{nextId}", produces = "application/json")
    public ResponseEntity<?> saveListIncomingByOneAndNextId(@PathVariable("nextId") Long nextId) {
        String key = "save-list-incoming-by-one/"+nextId;
        if (!acquireLock(key)) {
            return ResponseEntity.status(429).body("Operation is already in progress");
        }
        try {
            documentServiceDao.saveListByOneByTypeAndNextId(DocumentCategoryConstants.INCOMING, nextId);
            return ResponseEntity.ok().build();
        } finally {
            releaseLock(key);
        }
    }

    @GetMapping(value = "/save-list-intern-by-one/{nextId}", produces = "application/json")
    public ResponseEntity<?> saveListInternByOneAndNextId(@PathVariable("nextId") Long nextId) {
        String key = "save-list-intern-by-one/"+nextId;
        if (!acquireLock(key)) {
            return ResponseEntity.status(429).body("Operation is already in progress");
        }
        try {
            documentServiceDao.saveListByOneByTypeAndNextId(DocumentCategoryConstants.INTERN, nextId);
            return ResponseEntity.ok().build();
        } finally {
            releaseLock(key);
        }
    }

    @GetMapping(value = "/save-list-outgoing-by-one/{nextId}", produces = "application/json")
    public ResponseEntity<?> saveListOutgoingByOneAndNextId(@PathVariable("nextId") Long nextId) {
        String key = "save-list-outgoing-by-one/"+nextId;
        if (!acquireLock(key)) {
            return ResponseEntity.status(429).body("Operation is already in progress");
        }
        try {
            documentServiceDao.saveListByOneByTypeAndNextId(DocumentCategoryConstants.OUTGOING, nextId);
            return ResponseEntity.ok().build();
        } finally {
            releaseLock(key);
        }
    }

    @GetMapping(value = "/save-all-by-type/{categoryConstants}", produces = "application/json")
    public ResponseEntity<?> saveAllByType(@PathVariable("categoryConstants") DocumentCategoryConstants categoryConstants) {
        String key = "save-all-by-type/"+categoryConstants;
        if (!acquireLock(key)) {
            return ResponseEntity.status(429).body("Operation is already in progress");
        }
        try {
            documentServiceDao.saveAllByType(categoryConstants);
            return ResponseEntity.ok().build();
        } finally {
            releaseLock(key);
        }
    }

    @GetMapping(value = "/save-all-by-type/{categoryConstants}/next-id/{nextId}", produces = "application/json")
    public ResponseEntity<?> saveAllByTypeAndNextId(@PathVariable("categoryConstants") DocumentCategoryConstants categoryConstants,
                                                    @PathVariable("nextId") Long nextId) {
        String key = "save-all-by-type/"+categoryConstants+"/next-id/"+nextId;
        if (!acquireLock(key)) {
            return ResponseEntity.status(429).body("Operation is already in progress");
        }
        try {
            documentServiceDao.saveAllByTypeAndNextId(categoryConstants, nextId);
            return ResponseEntity.ok().build();
        } finally {
            releaseLock(key);
        }
    }

    @GetMapping(value = "/save-all-by-filter", produces = "application/json")
    public ResponseEntity<?> saveAllByFilter(@Valid DocumentFilter filter) {
        String key = "save-all-by-filter";
        if (!acquireLock(key)) {
            return ResponseEntity.status(429).body("Operation is already in progress");
        }
        try {
            documentServiceDao.saveAllByTypeAndNextId(filter.getDocumentCategoryConstants(), filter.getNextId());
            return ResponseEntity.ok().build();
        } finally {
            releaseLock(key);
        }
    }

    @GetMapping(value = "/save-one-by-type/{categoryConstants}/rkk-id/{rkkId}", produces = "application/json")
    public ResponseEntity<?> saveOneByTypeAndRkkId(@PathVariable("categoryConstants") DocumentCategoryConstants categoryConstants,
                                                   @PathVariable("rkkId") Long rkkId) {
        String key = "save-one-by-type/"+categoryConstants+"/rkk-id/"+rkkId;
        if (!acquireLock(key)) {
            return ResponseEntity.status(429).body("Operation is already in progress");
        }
        try {
            documentServiceDaoV2.saveOneByTypeAndId(categoryConstants, rkkId);
            return ResponseEntity.ok().build();
        } finally {
            releaseLock(key);
        }
    }

    @GetMapping(value = "/save-one-by-type/{categoryConstants}/rkk-ids/{rkkIds}", produces = "application/json")
    public ResponseEntity<?> saveOneByTypeAndRkkId(@PathVariable("categoryConstants") DocumentCategoryConstants categoryConstants,
                                                   @PathVariable("rkkIds") List<Long> rkkIds) {
        String key = "save-one-by-type/"+categoryConstants+"/rkk-ids/"+rkkIds;
        if (!acquireLock(key)) {
            return ResponseEntity.status(429).body("Operation is already in progress");
        }
        try {
            documentServiceDaoV2.saveOneByTypeAndIds(categoryConstants, rkkIds);
            return ResponseEntity.ok().build();
        } finally {
            releaseLock(key);
        }
    }

    @GetMapping(value = "/save-comment-incoming-recipient", produces = "application/json")
    public ResponseEntity<?> saveCommentIncomingRecipient() {
        String key = "save-comment-incoming-recipient";
        if (!acquireLock(key)) {
            return ResponseEntity.status(429).body("Operation is already in progress");
        }
        try {
            documentServiceDao.saveDocumentCommentRecipient();
            return ResponseEntity.ok().build();
        } finally {
            releaseLock(key);
        }
    }

}
