package lk.ijse.culinary.entity;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "student_program")
public class Student_Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_program_id")
    private Long student_program_id;

    @Column(name = "resgistration_date")
    private Date registration_date;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    @OneToMany(mappedBy = "student_program", cascade = CascadeType.ALL)
    private List<Payment> payments;

}
