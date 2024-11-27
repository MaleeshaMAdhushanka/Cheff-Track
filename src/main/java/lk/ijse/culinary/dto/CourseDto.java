package lk.ijse.culinary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {
    private String courseID;
    private String courseName;
    private String courseDuration;
    private double courseFee;


}