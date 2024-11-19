//package eu.iba.auto_test.converterbnb.controller;
//
//import eu.iba.auto_test.converterbnb.controller.data.DocumentData;
//import eu.iba.auto_test.converterbnb.dao.model.Document;
//import eu.iba.auto_test.converterbnb.dao.model.DocumentCategoryConstants;
//import eu.iba.auto_test.converterbnb.dao.services.DocumentServiceDao;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping(value = "api/bnb/document")
//public class DocumentController {
//
//    private final DocumentServiceDao documentServiceDao;
//
//    @Autowired
//    public DocumentController(DocumentServiceDao documentServiceDao) {
//        this.documentServiceDao = documentServiceDao;
//    }
//
//    @GetMapping(value = "/{docCategory}", produces = "application/json")
//    @Transactional(readOnly = true)
//    public List<Document> getCollection(@PathVariable("docCategory") DocumentCategoryConstants docCategory,
//                                        @RequestParam(value = "ids", required = false) List<Long> ids) {
//        DocumentData documentData = new DocumentData();
//        documentData.setRkkIds(ids);
//        return documentServiceDao.findAll(documentData, docCategory);
//    }
//
//}
