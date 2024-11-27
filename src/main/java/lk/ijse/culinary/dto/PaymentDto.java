package lk.ijse.culinary.dto;

import lk.ijse.culinary.entity.Course;
import lk.ijse.culinary.entity.Student;
import lk.ijse.culinary.entity.StudentCourse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentDto {
    private String paymentID;
    private Date paymentDate;
    private Double payamount;
    private String status;
    private double balanceAmount;
    private double upfrontAmount;
    private String courseID;
    private String studentEmail;
    private StudentCourse studentcourse;

    public PaymentDto(String paymentId, String studentEmail, String name, String courseId, String courseName, double paymentFee) {
        this.paymentID = paymentId;
        this.studentEmail = studentEmail;
        this.courseID = courseId;
        this.studentcourse = new StudentCourse(courseId, new Date(System.currentTimeMillis()), new Student(studentEmail, name), new Course(courseId, courseName, paymentFee));
    }

    public PaymentDto(String paymentID, String studentEmail, String courseID, String studentCourseID, double payAmount, LocalDate payDate, String status, double upfrontAmount, double balanceAmount) {
        this.paymentID = paymentID;
        this.studentEmail = studentEmail;
        this.courseID = courseID;
        this.studentcourse = new StudentCourse(Long.parseLong(studentCourseID), new Date(System.currentTimeMillis()), new Student(studentEmail), new Course(courseID));
        this.payamount = payAmount;
        this.paymentDate = Date.valueOf(payDate);
        this.status = status;
        this.upfrontAmount = upfrontAmount;
        this.balanceAmount = balanceAmount;
    }

    public PaymentDto(String paymentId, LocalDate paymentDate, double upfrontAmount, double balanceAmount, String status, String studentEmail, String courseId) {
        this.paymentID = paymentId;
        this.paymentDate = Date.valueOf(paymentDate);
        this.upfrontAmount = upfrontAmount;
        this.balanceAmount = balanceAmount;
        this.status = status;
        this.studentEmail = studentEmail;
        this.courseID = courseId;
    }

    public PaymentDto(String paymentId, LocalDate paymentDate, double upfrontAmount, double balanceAmount, String status, String studentEmail, String courseId, double payAmount) {
        this.paymentID = paymentId;
        this.paymentDate = Date.valueOf(paymentDate);
        this.upfrontAmount = upfrontAmount;
        this.balanceAmount = balanceAmount;
        this.status = status;
        this.studentEmail = studentEmail;
        this.courseID = courseId;
        this.payamount = payAmount;
    }

    public String getStudentName() {
        return studentcourse.getStudent().getName();
    }
}
