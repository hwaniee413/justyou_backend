package myshop.backend.service;

import myshop.backend.domain.Admin;
import myshop.backend.domain.Admincode;

public interface AdminService {

    Admin getAdminById(long admin_num);
    Admin getAdminByEmailId(String admin_id);
    Admin getAdminByPassword(String admin_pwd);

    void signUp(Admin admin);
}
