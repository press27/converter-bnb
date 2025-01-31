package eu.iba.auto_test.converterbnb.controller;

import eu.iba.auto_test.converterbnb.dao.model.DocumentLink;
import eu.iba.auto_test.converterbnb.dao.services.DocumentLinkServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "api/bnb/document-link")
public class DocumentLinkController {

    private final DocumentLinkServiceDao documentLinkServiceDao;

    @Autowired
    public DocumentLinkController(DocumentLinkServiceDao documentLinkServiceDao) {
        this.documentLinkServiceDao = documentLinkServiceDao;
    }

    @GetMapping(produces = "application/json")
    public synchronized ResponseEntity<?> getCollection() {
        documentLinkServiceDao.saveAll();
        return ResponseEntity.ok().build();
    }

}
