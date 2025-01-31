package eu.iba.auto_test.converterbnb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
@EnableRetry
public class ConverterBnbApplication {

//    private final static Long MAX_SIZE =  10000000L;

    public static void main(String[] args) {

//        List<Document> documents = new ArrayList<>();
//        Document doc1 = new Document();
//        doc1.setId(1L);
//        Document doc2 = new Document();
//        doc2.setId(2L);
//        Document doc3 = new Document();
//        doc3.setId(3L);
//        Document doc4 = new Document();
//        doc4.setId(1L);
//        Document doc5 = new Document();
//        doc5.setId(5L);
//        Document doc6 = new Document();
//        doc6.setId(1L);
//
//        documents.add(doc1);
//        documents.add(doc2);
//        documents.add(doc3);
//        documents.add(doc4);
//        documents.add(doc5);
//        documents.add(doc6);
//
//        documents = DocumentUtils.getUniqueDocuments(documents);
//        System.out.println("save");
        
//        for (int i = 0; i < 5; i++) {
//            Document doc = getDocument(i);
//            documents.add(doc);
//        }
//
//        List<List<Document>> collection = splitIntoCollectionsBySize(documents);
//        System.out.println("save");

        /////////////////////////////
//        Long id = 0L;
//        for (int i = 0; i < 5; i++) {
//            id = id + 1;
//            try {
//                Path filePath = Paths.get("c:\\error-doc\\" + "error-file" + ".txt");
//                if (!Files.exists(filePath.getParent())) {
//                    Files.createDirectories(filePath.getParent());
//                }
//                String str = Files.readString(filePath);
//                Files.writeString(filePath, str + id);
//                //ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
//                //Files.write(filePath, mapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request), java.nio.file.StandardOpenOption.APPEND, java.nio.file.StandardOpenOption.CREATE);
//            } catch (Exception ex) {
//                //log.error(ex.getMessage(),ex);
//            }
//        }


        SpringApplication.run(ConverterBnbApplication.class, args);
    }

//    private static Document getDocument(int i) {
//        Document doc = new Document();
//        Set<AttachmentDocument> attachmentDocuments = new HashSet<>();
//        for (int j = 0; j < 3; j++) {
//            if (i != 3) {
//                AttachmentDocument attachment = new AttachmentDocument();
//                Random random = new Random();
//                attachment.setSize(random.nextLong(1000000L, 5000000L));
//                attachmentDocuments.add(attachment);
//            }
////            else if(j == 2 || j == 1) {
////                AttachmentDocument attachment = new AttachmentDocument();
////                Random random = new Random();
////                attachment.setSize(random.nextLong(40000000L, 50000000L));
////                attachmentDocuments.add(attachment);
////            }
//        }
//        doc.setAttachmentDocuments(attachmentDocuments);
//        return doc;
//    }

//    public static List<List<Document>> splitIntoCollectionsBySize(List<Document> documents){
//        List<List<Document>> collection = new ArrayList<>();
//        if(!documents.isEmpty()) {
//            Long overallSize = 0L;
//            List<Document> listDoc = new ArrayList<>();
//            for (Document document : documents) {
//                if (document.getAttachmentDocuments() != null) {
//                    overallSize = overallSize + calculateSizeAttachments(document.getAttachmentDocuments());
//                    if (overallSize > MAX_SIZE) {
//                        if (!listDoc.isEmpty()) {
//                            List<Document> docs = new ArrayList<>(listDoc);
//                            collection.add(docs);
//                            listDoc.clear();
//                        }
//                        overallSize = calculateSizeAttachments(document.getAttachmentDocuments());
//                    }
//                    listDoc.add(document);
//                } else {
//                    listDoc.add(document);
//                }
//            }
//            List<Document> docs = new ArrayList<>(listDoc);
//            collection.add(docs);
//            listDoc.clear();
//        }
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
