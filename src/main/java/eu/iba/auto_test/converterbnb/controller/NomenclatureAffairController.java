package eu.iba.auto_test.converterbnb.controller;

import eu.iba.auto_test.converterbnb.dao.services.NomenclatureAffairServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/bnb/nomenclature-affair")
public class NomenclatureAffairController {

    private final NomenclatureAffairServiceDao nomenclatureAffairServiceDao;

    @Autowired
    public NomenclatureAffairController(NomenclatureAffairServiceDao nomenclatureAffairServiceDao) {
        this.nomenclatureAffairServiceDao = nomenclatureAffairServiceDao;
    }

    @GetMapping(produces = "application/json")
    @Transactional(readOnly = true)
    public ResponseEntity<?> getCollection() {
        nomenclatureAffairServiceDao.saveAll();
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/update", produces = "application/json")
    @Transactional(readOnly = true)
    public ResponseEntity<?> getCollectionUpdate() {
        nomenclatureAffairServiceDao.update();
        return ResponseEntity.ok().build();
    }

}
