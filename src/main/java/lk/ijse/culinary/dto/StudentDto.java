package lk.ijse.culinary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDto {
    private String id;
    private String name;
    private String address;
    private String dob;
    private String email;
    private String contact;
    private String course;

}
