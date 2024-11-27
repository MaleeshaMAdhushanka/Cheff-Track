// Payment.java
package lk.ijse.culinary.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
@Data

@NoArgsConstructor
@Entity
public class Payment {
    @Id
    private String payment_ID;
    private LocalDate paymentDate;
    private double payamount;
    private String status;
    private double upfrontAmount;
    private double balanceAmount;
    private String courseID;
    private String studentEmail;

    @ManyToOne
    @JoinColumn(name = "studentCourse_ID")
    private StudentCourse studentCourse;

    // Constructor
    public Payment(String payment_ID, LocalDate paymentDate, double payamount, String status, double upfrontAmount, double balanceAmount, String courseID, String studentEmail, StudentCourse studentCourse) {
        this.payment_ID = payment_ID;
        this.paymentDate = paymentDate;
        this.payamount = payamount;
        this.status = status;
        this.upfrontAmount = upfrontAmount;
        this.balanceAmount = balanceAmount;
        this.courseID = courseID;
        this.studentEmail = studentEmail;
        this.studentCourse = studentCourse;
    }

    // Getters and setters
    public String getPaymentID() {
        return payment_ID;
    }

    public void setPaymentID(String payment_ID) {
        this.payment_ID = payment_ID;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getPayamount() {
        return payamount;
    }

    public void setPayamount(double payamount) {
        this.payamount = payamount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getUpfrontAmount() {
        return upfrontAmount;
    }

    public void setUpfrontAmount(double upfrontAmount) {
        this.upfrontAmount = upfrontAmount;
    }

    public double getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public StudentCourse getStudentCourse() {
        return studentCourse;
    }

    public void setStudentCourse(StudentCourse studentCourse) {
        this.studentCourse = studentCourse;
    }
}