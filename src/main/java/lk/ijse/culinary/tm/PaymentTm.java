package lk.ijse.culinary.tm;

import io.github.palexdev.materialfx.controls.MFXButton;
import lk.ijse.culinary.entity.StudentCourse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentTm {
    private String paymentID;
    private Date paymentDate;
    private Double payamount;
    private String status;
    private double balanceAmount;
    private double upfrontAmount;
    private String courseID;
    private String studentEmail;
    private StudentCourse studentCourse;
    private MFXButton remove;

    public PaymentTm(String paymentID, double upfrontAmount, double balanceAmount, String courseID, String studentEmail, String status, MFXButton removeButton) {
        this.paymentID = paymentID;
        this.upfrontAmount = upfrontAmount;
        this.balanceAmount = balanceAmount;
        this.courseID = courseID;
        this.studentEmail = studentEmail;
        this.status = status;
        this.remove = removeButton;
    }

    public char[] getStudentName() {
        return null;
    }

    public boolean getCourseName() {
        return false;
    }

    public char[] getPayment() {
        return null;
    }

}
