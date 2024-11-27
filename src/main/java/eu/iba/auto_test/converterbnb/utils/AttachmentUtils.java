package eu.iba.auto_test.converterbnb.utils;

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

}
