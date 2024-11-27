package lk.ijse.culinary.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "student_id", length = 30)
    private String id;
    @Column(name = "student_name")
    private String name;
    @Column(name = "student_address")
    private String address;
    @Column(name = "student_dob")
    private String dob;
    @Column(name = "student_email")
    private String email;
    @Column(name = "student_contact")
    private String contact;
    @Column(name = "student_course")
    private String course;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "course")
    private List<StudentCourse> studentCourses = new ArrayList<>();

    public Student(String id, String name, String address, String dob, String email, String contact, String course) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.dob = dob;
        this.email = email;
        this.contact = contact;
        this.course = course;
    }

    public Student(String name) {
        this.name = name;
    }

    public Student(Student studentEmail, Student studentName) {
        this.email = studentEmail.getEmail();
        this.name = studentName.getName();
    }

    public Student(String studentEmail, String name) {
        this.email = studentEmail;
        this.name = name;
    }
}