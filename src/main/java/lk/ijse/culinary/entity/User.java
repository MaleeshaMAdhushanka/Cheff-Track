package lk.ijse.culinary.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Users")
public class User {
   @Id
   @Column(name =  "user_id")
   private String userid;

   @Column(name = "username")
   private String username;

   @Column(name = "password")
    private String password;

   @Column(name =  "jobrole")
    private String jobrole;
}
