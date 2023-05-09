package myshop.backend.controller;


import lombok.RequiredArgsConstructor;
import myshop.backend.domain.Dept;
import myshop.backend.domain.Emp;
import myshop.backend.domain.Emp_files;
import myshop.backend.domain.Schedule;
import myshop.backend.service.DeptService;
import myshop.backend.service.EmpFilesService;
import myshop.backend.service.EmpService;
import myshop.backend.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("emp")
public class RestfulEmpController {
    @Autowired
    private final EmpService empService;

    @Autowired
    private final EmpFilesService empFilesService;

    @Autowired
    private final ScheduleService scheduleService;

    private final PasswordEncoder passwordEncoder;

    @GetMapping("list")
    public List<Emp> list(){
        List<Emp> list = empService.list();
        return list;
    }
    @GetMapping("emp")
    public List<Emp> listByEmpRank(@RequestParam String emprank) {
        List<Emp> list = empService.listByEmpRank(emprank);
        return list;
    }
    @GetMapping("dept")
    public List<Emp> listByDeptNameAndEmpRank(@RequestParam String deptname, @RequestParam String emprank){
        List<Emp> list = empService.listByDeptNameAndEmpRank(deptname, emprank);
        return list;
    }
    @GetMapping("dept/{deptname}/totalcount")
    public long getTotalCountByDept(@PathVariable String deptname){
        long totalcount = empService.getTotalCountByDept(deptname);
        return totalcount;
    }
    @GetMapping("totalcount")
    public long getTotalCount(){

        long totalcount = empService.getTotalCount();

        return totalcount;
    }
    @GetMapping("schedule")
    public List<Schedule> getScheduleListByDeptname(@RequestParam String deptname){
        pln("getScheduleListByDeptname deptname: "+deptname);
        List<Schedule> list = scheduleService.listByDeptname(deptname);
        pln("getScheduleListByDeptname list: "+list);
        return list;
    }
    @GetMapping("search_empnumber")
    public Emp getEmpInfoByEmpnumber(
            @RequestParam(required = false) String empnumber) {

        pln("empnumber: " + empnumber);
        if (empnumber!=null){
            long empNumber = Long.parseLong(empnumber);
            if(empNumber!=0){
                return empService.getEmpInfoByEmpnumber(empNumber);
            }
        }

        return null;
    }
    @GetMapping("search_name")
    public List<Emp> getEmpInfoByEmpname(@RequestParam(required = false) String empname){
        if(empname!=null){
            return empService.getEmpInfoByEmpname(empname);
        }
        return null;
    }
    @PostMapping("insert")
    public void insert(@RequestBody Emp emp){
        pln("emp insert in?");
        pln("emp: " + emp);
        emp.setPassword(passwordEncoder.encode(emp.getPassword()));
        empService.insert(emp);
    }
    @PostMapping("{empnumber}/fileUp")
    public void insertImageFile(@PathVariable("empnumber") long empnumber ,
                                @RequestParam("file") MultipartFile file) throws IOException {
        pln("emp insertImageFile in?");
        pln("empnumber: " + empnumber);
        pln("file: " + file);
        Emp emp = empService.getEmpInfoByEmpnumber(empnumber);;
        long empno = emp.getEmpno();
        empFilesService.insert(file, empno);
        pln("file success?");
    }
    @GetMapping("getProfilefile")
    public ResponseEntity<byte[]> getProfilefile(@RequestParam long empno){
        pln("getProfilefile in? empno: " + empno);
        Emp_files file = empFilesService.getEmpFileByEmpno(empno);
        pln("getProfilefile in? file: " + file);
        // 이미지 파일이 존재하지 않을 경우
        if(file == null){
            return ResponseEntity.notFound().build();
        }
        // 이미지 파일을 읽어들인다.
        byte[] data = null;
        try (InputStream is = new FileInputStream(file.getSavedpath())) {
            data = is.readAllBytes();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        // 이미지 파일을 반환한다.
        HttpHeaders headers = new HttpHeaders();
        //headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(data.length);
        return new ResponseEntity<>(data, headers, HttpStatus.OK);

    }
    @PostMapping("updatefile")
    public void updateImageFile(@RequestParam("file") MultipartFile file,
                                @RequestParam("empno") long empno) throws IOException{
        pln("updateImageFile file: " + file );
        pln("updateImageFile empno: " + empno );
        empFilesService.update(file, empno);
        pln("file update success?");
    }
    @PostMapping("updatemobile")
    public void updateMobile(@RequestParam("empno") long empno,
                             @RequestParam("mobile") String mobile) {
        pln("updateMobile empno: " + empno);
        pln("updateMobile mobile: " + mobile);
        empService.updateMobile(mobile, empno);
    }
    @PostMapping("updatepassword")
    public void updatePassword(@RequestParam("empno") long empno,
                             @RequestParam("password") String password) {
        pln("updateMobile empno: " + empno);
        pln("updateMobile mobile: " + password);
        String encodedPwd = passwordEncoder.encode(password);
        empService.updatePassword(encodedPwd, empno);
    }

    @GetMapping("dplicheck")
    public Boolean dplicheck(@RequestParam("email") String email){
        pln("dplicheck email: " + email);

        return empService.getEmpInfoByEmail(email);
    }

    void pln(String str){
        System.out.println(str);
    }
}
