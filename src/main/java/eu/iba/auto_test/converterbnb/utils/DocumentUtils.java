package eu.iba.auto_test.converterbnb.utils;

import eu.iba.auto_test.converterbnb.dao.model.Document;
import eu.iba.auto_test.converterbnb.dao.model.DocumentCategoryConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentUtils {

    public static DocumentCategoryConstants convertDocumentCategoryConstants(String documentCategoryConstants){
        if(documentCategoryConstants != null && !documentCategoryConstants.isEmpty()) {
            return switch (documentCategoryConstants) {
                case "В" -> DocumentCategoryConstants.INCOMING;
                case "И" -> DocumentCategoryConstants.OUTGOING;
                case "У" -> DocumentCategoryConstants.INTERN;
                case "О" -> DocumentCategoryConstants.APPEAL;
                default -> null;
            };
        }
        return null;
    }

    public static List<Document> getUniqueDocuments(List<Document> documents){
        Map<Long, Document> documentMap = new HashMap<>();
        for (Document document: documents){
            documentMap.put(document.getId(), document);
        }

        List<Document> uniqueDocuments = new ArrayList<>();
        for(Map.Entry<Long, Document> item : documentMap.entrySet()){
            uniqueDocuments.add(item.getValue());
        }
        return uniqueDocuments;
    }

}
