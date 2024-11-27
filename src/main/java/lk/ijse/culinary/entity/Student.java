package lk.ijse.culinary.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "student_id", length = 30)
    private String studentId;


    @Column(name = "student_name")
    private String name;

    @Column(name = "student_address")
    private String address;


    @Column(name = "student_contact")
    private String contact;

    @Column(name = "student_course")
    private String course;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "program")
    private List<Student_Program> studentPrograms = new ArrayList<>();

    public Student() {}
    public Student(String studentId, String name, String address, String contact, String course) {
        this.studentId = studentId;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.course = course;

    }
}
