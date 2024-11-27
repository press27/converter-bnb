package eu.iba.auto_test.converterbnb;

import eu.iba.auto_test.converterbnb.dao.model.Task;
import eu.iba.auto_test.converterbnb.utils.TaskUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ConverterBnbApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConverterBnbApplication.class, args);

//        List<Task> tasks = new ArrayList<>();
//        for (int i = 1; i < 5; i++) {
//            String index = String.valueOf(i);
//            tasks.add(sTask(index));
//            for (int j = 10; j < 13; j++) {
//                String indexChild = index + j;
//                tasks.add(scTask(indexChild, index));
//                for (int k = 200; k < 203; k++) {
//                    String indexChild2 = indexChild + k;
//                    tasks.add(scTask(indexChild2, indexChild));
//                }
//            }
//        }
//        List<Task> list = TaskUtils.getTreeTasks(tasks);
//        System.out.println("kek");
//    }
//
//    private static Task sTask(String id){
//        Task task = new Task();
//        task.setId(Long.parseLong(id));
//        return task;
//    }
//
//    private static Task scTask(String id, String parentId){
//        Task task = new Task();
//        task.setId(Long.parseLong(id));
//        task.setParentId(Long.parseLong(parentId));
//        return task;
    }
}
