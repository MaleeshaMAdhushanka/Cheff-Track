package lk.ijse.culinary.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "student_course")
public class StudentCourse {
    @Id
    @Column(name = "student_course_ID")
    private Long studentCourseID;

    @Column(name = "registration_date")
    private Date registrationDate;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "studentCourse", cascade = CascadeType.ALL)
    private List<Payment> payments;

    public StudentCourse(String courseId, Date date, Student student, Course course) {
        this.registrationDate = date;
        this.student = student;
        this.course = course;
    }

    public StudentCourse(long l, Date date, Student student, Course course) {
        this.studentCourseID = l;
        this.registrationDate = date;
        this.student = student;
        this.course = course;
    }

    public String getCourseName() {
        return course.getCourseName();
    }

    public String getCourseID() {
        return course.getCourseID();
    }
}