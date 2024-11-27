package lk.ijse.culinary.tm;

import io.github.palexdev.materialfx.controls.MFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentTm {
    private String id;
    private String name;
    private String address;
    private String dob;
    private String email;
    private String contact;
    private String course;
    private MFXButton remove;


}
