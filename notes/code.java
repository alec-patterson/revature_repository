/**
* The Problem:
*
* We have a list of tasks. Each task can depend on other tasks. 
* For example if task A depends on task B then B should run before A.
* 
* Implement the "getTaskWithDependencies" method such that it returns a list of task names in the correct order.
* 
* For example if I want to execute task "application A", the method should return a list with "storage,mongo,application A".
*
* List of Tasks:
*
*   - name: application A
*     dependsOn:
*       - mongo
*
*   - name: storage
*
*   - name: mongo
*     dependsOn:
*       - storage
*
*   - name: memcache
*
*   - name: application B
*     dependsOn:
*       - memcache
*
* The Java program is expected to be executed succesfully.
*/


import java.io.*;
import java.util.*;
import org.junit.*;
import org.junit.runner.*;

public class Solution { 
 private static List<String> getTaskWithDependencies(List<Task> tasks, String dependsOn) {
   // TODO: please implement logic here
  Task found = null;
  List<String> list = new ArrayList<>();
  
  // Finding task based on dependsOn
  for(Task t: tasks) {
    if(t.getName().equals(dependsOn)) {
      found = t;
      break;
    }
  }
   
  /* 
   * if the task depends on anything loop through all dependencies
   * and get their dependencies
   */
  if(found.getDependsOn() != null) {
    for(String item: found.getDependsOn()) {
      List<String> temp = getTaskWithDependencies(tasks, item);
      if(temp != null)
        list.addAll(temp);
    }
  }
  
  // adds the current task into list and returns list
  list.add(found.getName());
  return list;
 }
  
 public static void main(String[] args) {
   JUnitCore.main("Solution");        
 }

 @Test
 public void testGetTaskDependenciesForApplicationA() {
   Assert.assertEquals(
     Arrays.asList(
       "storage",
       "mongo",
       "application A"
     ),
     getTaskWithDependencies(TaskList.getTasks(), "application A")
   );
 }
}

/**
* Definition of a Task
*/
class Task {
 private final String name;
 private final List<String> dependsOn;

 Task(String name) {
   this(name, new ArrayList<>());
 }

 Task(String name, List<String> dependsOn) {
   this.name = name;
   this.dependsOn = dependsOn;
 }

 public String getName() { return this.name; }
 public List<String> getDependsOn() { return this.dependsOn; }
}

class TaskList {
 public static List<Task> getTasks() {
   List<Task> tasks = new ArrayList<>();

   tasks.add(new Task("application A", Arrays.asList("mongo")));
   tasks.add(new Task("storage"));
   tasks.add(new Task("mongo", Arrays.asList("storage")));
   tasks.add(new Task("memcache"));
   tasks.add(new Task("application B", Arrays.asList("memcache")));

   return tasks;
 }
}