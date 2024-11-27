package eu.iba.auto_test.converterbnb.utils;

import eu.iba.auto_test.converterbnb.dao.model.DocumentCategoryConstants;

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

}
