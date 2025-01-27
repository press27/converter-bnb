package eu.iba.auto_test.converterbnb.utils;

import eu.iba.auto_test.converterbnb.dao.model.AttachmentDocument;
import eu.iba.auto_test.converterbnb.dao.model.Document;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AttachmentUtils {

    public static String bytesToHex(byte[] bytes) {
        if (bytes == null || bytes.length == 0){
            return null;
        }
        StringBuilder hexString = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static byte[] hexToByte(String hex) {
        if (hex.startsWith("0x")) {
            hex = hex.substring(2);
        }
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < hex.length(); i += 2) {
            bytes[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4) + Character.digit(hex.charAt(i+1), 16));
        }
        return bytes;
    }

    public static Set<AttachmentDocument> deleteAttachmentWithNullData(Set<AttachmentDocument> attachmentDocumentSet){
        Set<AttachmentDocument> attachmentDocuments = new HashSet<>();
        for (AttachmentDocument attachmentDocument: attachmentDocumentSet){
            if(attachmentDocument.getData() != null){
                attachmentDocuments.add(attachmentDocument);
            }
        }
        return attachmentDocuments;
    }

    public static Long calculateSizeAllAttachmentsByDocuments(List<Document> documents){
        Long size = 0L;
        for (Document document: documents){
            if(document.getAttachmentDocuments() != null) {
                for (AttachmentDocument attachmentDocument : document.getAttachmentDocuments()) {
                    size = size + attachmentDocument.getSize();
                }
            }
        }
        return size;
    }
}
