package lk.ijse.culinary.tm;

import io.github.palexdev.materialfx.controls.MFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseTm {

    private String courseID;
    private String courseName;
    private String courseDuration;
    private double courseFee;
private MFXButton remove;

}