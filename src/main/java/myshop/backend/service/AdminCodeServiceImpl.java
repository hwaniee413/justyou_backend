package myshop.backend.service;


import myshop.backend.domain.Admincode;
import myshop.backend.mapper.AdminCodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminCodeServiceImpl implements AdminCodeService{
    @Autowired
    AdminCodeMapper adminCodeMapper;

    @Override
    public Admincode getAdminCode(long id) {
        Admincode admincode = adminCodeMapper.getAdminCode(id);
        return admincode;
    }

    void pln(String str){
        System.out.println(str);
    }
}
