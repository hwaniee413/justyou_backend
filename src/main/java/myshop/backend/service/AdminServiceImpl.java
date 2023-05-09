package myshop.backend.service;

import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import myshop.backend.domain.Admin;
import myshop.backend.domain.Admincode;
import myshop.backend.mapper.AdminCodeMapper;
import myshop.backend.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private final AdminMapper adminMapper;

    @Override
    public Admin getAdminById(long admin_num) {
        return adminMapper.getAdminbyId(admin_num);
    }

    @Override
    public Admin getAdminByEmailId(String admin_id) {
        Admin admin = adminMapper.getAdminByEmailId(admin_id);
        return admin;
    }

    @Override
    public Admin getAdminByPassword(String admin_pwd) {
        Admin admin = adminMapper.getAdminByPassword(admin_pwd);
        return admin;
    }


    @Override
    public void signUp(Admin admin) {
        adminMapper.signUp(admin);
    }
    void pln(String str){
        System.out.println(str);
    }
}
