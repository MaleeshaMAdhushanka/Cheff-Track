package lk.ijse.culinary.tm;

public class StudentCourseTm {
    private String studentCourseId;
    private String registerDate;
    private String studentId;
    private String courseId;

    public StudentCourseTm() {
    }

    public StudentCourseTm(String studentCourseId, String registerDate, String studentId, String courseId) {
        this.studentCourseId = studentCourseId;
        this.registerDate = registerDate;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public String getStudentCourseId() {
        return studentCourseId;
    }

    public void setStudentCourseId(String studentCourseId) {
        this.studentCourseId = studentCourseId;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "StudentCourseTm{" +
                "studentCourseId='" + studentCourseId + '\'' +
                ", registerDate='" + registerDate + '\'' +
                ", studentId='" + studentId + '\'' +
                ", courseId='" + courseId + '\'' +
                '}';
    }
}
