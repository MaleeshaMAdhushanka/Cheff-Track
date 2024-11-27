package lk.ijse.culinary.dto;

import lk.ijse.culinary.entity.Student;
import lk.ijse.culinary.entity.Student_Program;

import java.sql.Date;

public class PaymentDto {
    private String paymentId;
    private Student_Program studentProgramId;
    private Student studentId;
    private Double payment;
    private Date paymentDate;
    private String status;
    private double upfront_amount;
    private double balance_amount;
}
