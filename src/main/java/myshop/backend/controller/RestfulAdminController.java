package myshop.backend.controller;


import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import myshop.backend.domain.Admin;
import myshop.backend.domain.Admincode;
import myshop.backend.service.AdminCodeService;
import myshop.backend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("admin")
@CrossOrigin(origins = "*", maxAge = 3600) //#매우 중요!
@RestController
public class RestfulAdminController {

    @Autowired
    private final AdminService adminService;



    @GetMapping("/{id}")
    public Admin getAdmin(@PathVariable long id){
        pln("getAdmin in? admin_num: " + id);
        return adminService.getAdminById(id);
    }


    void pln(String str){
        System.out.println(str);
    }
}
