package lk.ijse.culinary.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "payment")
public class Payment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long pay_id;

    @Column(name = "payment")
    private double payment;

    @Column(name = "payment_date")
    private Date pay_date;


    @Column(name = "status")
    private  String status;

    @Column(name = "upfront_amount")
    private double upfront_amount;

    @Column(name = "balance_amount")
    private double balance_amount;

    @ManyToOne
    @JoinColumn(name = "student_program_id")
    private Student_Program studentProgram;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

}
