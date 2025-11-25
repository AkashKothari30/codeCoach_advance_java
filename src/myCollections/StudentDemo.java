package myCollections;
import java .util.ArrayList;
public class StudentDemo {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<Student>();
        students.add(new Student("Akash kothari", 01));
        students.add(new Student("Aman naudiyal", 02));
        students.add(new Student("ujjawal sharma", 03));
        students.add(new Student("ankit", 33));
        students.add(new Student("vikash ", 23));

        for(Student stu : students){
            System.out.println(stu);
        }
        System.out.println("size of Students :"+ students.size());
    }
}
