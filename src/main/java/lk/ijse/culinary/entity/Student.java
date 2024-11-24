package lk.ijse.culinary.entity;


import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @Column(name = "student_id")
    private String studentId;


    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;


    @Column(name = "contact")
    private String contact;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private  User user;

}
