package Models;
// Generated Aug 13, 2019 9:20:47 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Classes generated by hbm2java
 */
public class Classes  implements java.io.Serializable {


     private int id;
     private String name;
     private Set students = new HashSet(0);
     private Set schedules = new HashSet(0);

    public Classes() {
    }

	
    public Classes(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Classes(int id, String name, Set students, Set schedules) {
       this.id = id;
       this.name = name;
       this.students = students;
       this.schedules = schedules;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public Set getStudents() {
        return this.students;
    }
    
    public void setStudents(Set students) {
        this.students = students;
    }
    public Set getSchedules() {
        return this.schedules;
    }
    
    public void setSchedules(Set schedules) {
        this.schedules = schedules;
    }




}

