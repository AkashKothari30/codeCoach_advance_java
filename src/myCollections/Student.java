package myCollections;

public class Student {
    private String name;
    private int rollno;

    public Student(String name,int rollno){
        this.name = name;
        this.rollno =rollno;
    }
    public String toString(){
        return "Student( name=' " + name + " ',  rollno ="  + rollno +")";

    }
}
