package lk.ijse.culinary.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Payment {


    @Id
    @Column(name = "pay_id")
    private String pay_id;

    @Column(name = "pay_date")
    private String pay_date;

    @Column(name = "pay_amount")
    private String pay_amount;

    @Column(name = "status")
    private  String status;

    @Column(name = "upfront_amount")
    private double upfront_amount;

    @Column(name = "balance_amount")
    private double balance_amount;

    @ManyToOne
    @JoinColumn(name = "student_program_id")
    private Student_Program studentProgram;

}
