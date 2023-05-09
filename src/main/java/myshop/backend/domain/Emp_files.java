package myshop.backend.domain;


import lombok.Data;

@Data
public class Emp_files {
    private long fid;
    private String fname;
    private String ofname;
    private String savedpath;
    private long empno;

    private Emp emp;
}
