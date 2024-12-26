package eu.iba.auto_test.converterbnb.utils;

import eu.iba.auto_test.converterbnb.dao.model.Signature;

import java.util.ArrayList;
import java.util.List;

public class SignatureUtils {

    public static List<Signature> deleteSignatureWithNullData(List<Signature> signatures){
        List<Signature> signatureList = new ArrayList<>();
        for (Signature signature: signatures){
            if(signature.getData() != null){
                signatureList.add(signature);
            }
        }
        return signatureList;
    }

}
