package lk.ijse.culinary.bo.custom;

import lk.ijse.culinary.bo.SuperBO;
import lk.ijse.culinary.dto.AdminDto;

import java.util.List;

public interface AdminBO extends SuperBO {
    boolean saveAdmin(AdminDto dto);
    boolean updateAdmin(AdminDto dto);
    boolean deleteAdmin(String id);
    List<AdminDto> getAllAdmin();
    boolean isAdminExist(AdminDto dto);
}
