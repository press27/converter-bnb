//package eu.iba.auto_test.converterbnb.controller;
//
//import eu.iba.auto_test.converterbnb.dao.model.Correspondent;
//import eu.iba.auto_test.converterbnb.dao.services.CorrespondentServiceDao;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping(value = "api/bnb/correspondent")
//public class CorrespondentController {
//
//    private final CorrespondentServiceDao correspondentServiceDao;
//
//    @Autowired
//    public CorrespondentController(CorrespondentServiceDao correspondentServiceDao) {
//        this.correspondentServiceDao = correspondentServiceDao;
//    }
//
//    @GetMapping(produces = "application/json")
//    @Transactional(readOnly = true)
//    public List<Correspondent> getCollection() {
//        return correspondentServiceDao.findAll();
//    }
//
//}
