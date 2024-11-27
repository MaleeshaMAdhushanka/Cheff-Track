package lk.ijse.culinary.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
@ToString
@Getter
@Setter
@Entity
@Table(name = "course")
public class Course {
    @Id
    @Column(name = "course_ID",length = 10)
    private String courseID;


    @Column(name = "course_Name")
    private String courseName;

    @Column(name = "course_Duration")
    private String courseDuration;
    @Column(name = "course_Fee")
    private Double courseFee;


    public Course(String courseID, String courseName, double paymentFee) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseFee = paymentFee;
    }

    public Course(String courseID) {
        this.courseID = courseID;
    }
}
