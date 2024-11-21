package eu.iba.auto_test.converterbnb.utils;

import eu.iba.auto_test.converterbnb.dao.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TaskUtils {

    public static List<Task> getTreeTasks(List<Task> allTask){
        if(!allTask.isEmpty()) {
            List<Task> treeTasksList = new ArrayList<>();
            for (Task task : allTask) {
                if (task.getParentId() == null){
                    treeTasksList.add(task);
                    searchChildTask(task, allTask);
                }
            }
            return treeTasksList;
        }
        return null;
    }

    private static void searchChildTask(Task task, List<Task> allTask){
        for (Task taskChild: allTask) {
            if(Objects.equals(taskChild.getParentId(), task.getId())){
                task.getChildTasks().add(taskChild);
                searchChildTask(taskChild, allTask);
            }
        }
    }

}
