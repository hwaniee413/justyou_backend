package myshop.backend.controller;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myshop.backend.domain.Admin;
import myshop.backend.domain.Admincode;
import myshop.backend.domain.Emp;
import myshop.backend.service.AdminCodeService;
import myshop.backend.service.AdminService;
import myshop.backend.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import static javax.crypto.Cipher.SECRET_KEY;



@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600) //#매우 중요!
@RequestMapping("accounts")
@RestController
public class RestfulAccountsController {

    // JWT 토큰 변환 시 사용되는 키
    private static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private static final String secretString = Encoders.BASE64.encode(key.getEncoded());

    // 회원가입시 비밀번호 암호화
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final AdminService adminService;
    @Autowired
    private final AdminCodeService adminCodeService;

    @Autowired
    private final EmpService empService;
    @PostMapping("login.do")
    public String login(@RequestBody Map<String, String> loginRequest){
        System.out.println("empnumber " + loginRequest.get("empnumber"));
        System.out.println("password " + loginRequest.get("password"));
        JsonObject jsonObject = new JsonObject();
        String json = null;
        long empNumber = Long.parseLong(loginRequest.get("empnumber"));
        Emp emp = empService.getEmpInfoByEmpnumber(empNumber);
        System.out.println("emp: " + emp);
        if (emp == null ){
            jsonObject.addProperty("error", "입력하신 사원번호가 존재하지 않습니다.");
            json = jsonObject.toString();
            return json;
        }
        String rowPwd = loginRequest.get("password");
        String encodedPwd = emp.getPassword();
        boolean flag = passwordEncoder.matches(rowPwd, encodedPwd);
        //boolean flag = encodedPwd.equals(rowPwd);
        System.out.println("비번 일치? " + flag);
        if (!flag){
            jsonObject.addProperty("error", "패스워드가 일치하지 않습니다.");
            json = jsonObject.toString();
            return json;
        }

        // TODO: 로그인 처리 로직 구현
        String token1 = Jwts.builder()
                .setSubject(String.valueOf(emp.getEmpnumber())) //사원번호
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 1시간
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
        String token2 = Jwts.builder()
                .setSubject(emp.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 1시간
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
        String token3 = Jwts.builder()
                .setSubject(emp.getDept().getDeptname())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 1시간
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        // admin 객체를 JSON 형태로 변환


        jsonObject.addProperty("token1", token1);
        jsonObject.addProperty("token2", token2);
        jsonObject.addProperty("token3", token3);
        json = jsonObject.toString();
        return json;
    }

    // 토큰 복호화를 위한 키 반환
    @GetMapping("getJwtSecretKey")
    public String getJwtSecretKey(){
        JsonObject jsonObject = new JsonObject();
        pln("getJwtSecretKey in");
        jsonObject.addProperty("secretKey", secretString);
        String json = jsonObject.toString();
        return json;
    }

    // 관리자 계정 생성을 위한 관리자 코드 가져오기
    @GetMapping("/admincode/{id}")
    public Admincode getAdminCode(@PathVariable long id){
        pln("getAdminCode in");
        return adminCodeService.getAdminCode(id);
    }

    // 아이디 중복 확인
    @GetMapping("dplicheck")
    public String dplicheck(@RequestParam String admin_id){
        pln("admin_id: " + admin_id);
        JsonObject jsonObject = new JsonObject();
        Admin admin = adminService.getAdminByEmailId(admin_id);
        if (admin == null) {
            jsonObject.addProperty("admin_id", "null");

        } else {
            jsonObject.addProperty("admin_id", admin.getAdmin_name());
        }

        String a = jsonObject.toString();
        System.out.println("getAdminByEmailId json: " + a);
        return a;
    }
    @PostMapping("register/signup")
    public String register (@RequestBody Admin admin){
        pln("register admin: " + admin);
        String encodedPassword = passwordEncoder.encode(admin.getAdmin_pwd());
        admin.setAdmin_pwd(encodedPassword);
        adminService.signUp(admin);
        return "success";
    }
    void pln(String str){
        System.out.println(str);
    }
}
