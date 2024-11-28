package lk.ijse.culinary.dto;



public class UserDto {

    private String email;
    private String name;
    private String address;

    private String password;

    private String imgUrl;


    public UserDto() {
    }

    public UserDto(String email, String name, String address, String password, String imgUrl) {
        this.email = email;
        this.name = name;
        this.address = address;
        this.password = password;
        this.imgUrl = imgUrl;
    }


    public UserDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserDto(String email, String name, String address, String password) {
        this.email = email;
        this.name = name;
        this.address = address;
        this.password = password;
    }

    public UserDto(String text, String encryptedPassword, String imgUrl) {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}

