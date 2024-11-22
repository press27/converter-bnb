//package eu.iba.auto_test.converterbnb.utils;
//
//import eu.iba.auto_test.converterbnb.dao.model.UserType;
//
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.Set;
//
////44880 - Владелец БД
////103576 - Administrator
////104330 - Системный пользователь
////156861 - DIRECTUM Service
////183560 - Test
////201053 - temp_novacom
////201056 - temp_novacom1
////202665 - Test1
////328114 - Test2
////459152 - Служебный ОВК
////459154 - Служебный ОБ
////459155 - Служебный УРиПБИ
////459156 - Служебный ЮУ
////459157 - Служебный УОУ
////459445 - Служебный КУ
//public class UserUtils {
//
//    private static final Set<Long> idsExclude = new HashSet<>(Arrays.asList(44880L,103576L,104330L,156861L,183560L,201053L,201056L,202665L,328114L,459152L,459154L,459155L,459156L,459157L,459445L));
//
//    public static UserType getUserType(Long id){
//        if(idsExclude.contains(id)){
//            return UserType.SYSTEM;
//        } else {
//            return UserType.USER;
//        }
//    }
//
//}
