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
}
