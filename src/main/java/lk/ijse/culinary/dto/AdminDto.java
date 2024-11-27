package lk.ijse.culinary.dto;

import lk.ijse.culinary.entity.Admin;

public class AdminDto {

    private String username;

    private String password;

    private String imgUrl;


    public AdminDto() {
    }

    public AdminDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AdminDto(String username, String password, String imgUrl) {
        this.username = username;
        this.password = password;
        this.imgUrl = imgUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Admin getEntity(){
        return new Admin(this.username,this.password,this.imgUrl);
    }


}

