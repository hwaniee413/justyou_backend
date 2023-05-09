package myshop.backend.domain;


import lombok.Data;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Data
public class Emp {
    private long empno;
    private String name;
    private String mobile;
    private String emprank;
    private LocalDateTime rdate;
    private long sal;
    private long comm;
    private long deptno;
    private long empnumber;
    private long vacation_total;
    private long vacation_used;
    private long annual_total;
    private long annual_used;
    private Dept dept;

    private String password;

    private String email;


}
