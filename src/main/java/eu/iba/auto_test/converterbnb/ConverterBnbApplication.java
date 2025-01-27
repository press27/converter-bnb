package eu.iba.auto_test.converterbnb;

import eu.iba.auto_test.converterbnb.dao.model.AttachmentDocument;
import eu.iba.auto_test.converterbnb.dao.model.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

import java.util.*;

@SpringBootApplication
@EnableRetry
public class ConverterBnbApplication {

//    private final static Long MAX_SIZE =  100000000L;

    public static void main(String[] args) {

//        List<Document> documents = new ArrayList<>();
//        Document doc1 = new Document();
//        Document doc2 = new Document();
//        Document doc3 = new Document();
//        Document doc4 = new Document();
//        Document doc5 = new Document();
//
//        documents.add(doc1);
//        documents.add(doc2);
//        documents.add(doc3);
//        documents.add(doc4);
//        documents.add(doc5);
        
//        for (int i = 0; i < 5; i++) {
//            Document doc = getDocument(i);
//            documents.add(doc);
//        }
//
//        List<List<Document>> collection = splitIntoCollectionsBySize(documents);
//        System.out.println("save");
//
        SpringApplication.run(ConverterBnbApplication.class, args);
    }
//
//    private static Document getDocument(int i) {
//        Document doc = new Document();
//        Set<AttachmentDocument> attachmentDocuments = new HashSet<>();
//        for (int j = 0; j < 3; j++) {
//            if (i != 3) {
//                AttachmentDocument attachment = new AttachmentDocument();
//                Random random = new Random();
//                attachment.setSize(random.nextLong(2000000L, 6000000L));
//                attachmentDocuments.add(attachment);
//            } else if(j == 2 || j == 1) {
//                AttachmentDocument attachment = new AttachmentDocument();
//                Random random = new Random();
//                attachment.setSize(random.nextLong(40000000L, 50000000L));
//                attachmentDocuments.add(attachment);
//            }
//        }
//        doc.setAttachmentDocuments(attachmentDocuments);
//        return doc;
//    }
//
//    public static List<List<Document>> splitIntoCollectionsBySize(List<Document> documents){
//        List<List<Document>> collection = new ArrayList<>();
//        Long overallSize = 0L;
//        List<Document> listDoc = new ArrayList<>();
//        for(Document document: documents){
//            if(document.getAttachmentDocuments() != null){
//                overallSize = overallSize + calculateSizeAttachments(document.getAttachmentDocuments());
//                if(overallSize > MAX_SIZE){
//                    if(!listDoc.isEmpty()) {
//                        List<Document> docs = new ArrayList<>(listDoc);
//                        collection.add(docs);
//                        listDoc.clear();
//                    }
//                    overallSize = calculateSizeAttachments(document.getAttachmentDocuments());
//                }
//                listDoc.add(document);
//            } else {
//                listDoc.add(document);
//            }
//        }
//
//        if(!listDoc.isEmpty()){
//            List<Document> docs = new ArrayList<>(listDoc);
//            collection.add(docs);
//            listDoc.clear();
//        }
//
//        return collection;
//    }
//
//
//    public static Long calculateSizeAttachments(Set<AttachmentDocument> attachmentDocuments){
//        Long size = 0L;
//        for (AttachmentDocument attachmentDocument: attachmentDocuments){
//            size = size + attachmentDocument.getSize();
//        }
//        return size;
//    }
}
