package lk.ijse.culinary.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "program")
public class Program {
    @Id
    @Column(name = "program_id")
    private int programId;

    @Column(name = "program_name")
    private String programName;


    @Column(name = "duration")
    private String duration;

    @Column(name = "program_fee")
    private double program_fee;
}
