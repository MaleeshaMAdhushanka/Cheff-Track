package lk.ijse.culinary.dto;

import lk.ijse.culinary.entity.Course;
import lk.ijse.culinary.entity.Student;
import lombok.*;

import java.sql.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class StudentCourseDto {
    private String studentCourseID;
    private Date registrationDate;
    private Student student;
    private Course course;
}
