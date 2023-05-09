package myshop.backend.controller;


import lombok.RequiredArgsConstructor;
import myshop.backend.domain.Dept;
import myshop.backend.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("dept")
public class RestfulDeptController {

    @Autowired
    private  final DeptService deptService;
    @GetMapping("list")
    public List<Dept> list(){
        List<Dept> list =deptService.list();
        return list;
    }
    void pln(String str){
        System.out.println(str);
    }
}
