package myshop.backend.domain;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Schedule {
    private long id;
    private String title;
    private String content;
    private String status;
    private String empname;
    private LocalDateTime start_at;
    private LocalDateTime deadline;
    private LocalDateTime end_at;
    private long deptno;
    private Dept dept;

}
