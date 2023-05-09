package myshop.backend.service;

import myshop.backend.domain.Emp;

import java.util.List;

public interface EmpService {
    List<Emp> list();

    List<Emp> listByEmpRank(String emprank);
    List<Emp> listByDeptNameAndEmpRank(String deptname, String emprank);

    Long getTotalCount();
    Long getTotalCountByDept(String deptname);

    Emp getEmpInfoByEmpnumber(long empnumber);

    List<Emp> getEmpInfoByEmpname(String empname);

    Boolean getEmpInfoByEmail(String email);

    void insert(Emp emp);

    void updateMobile(String mobile, long empno);
    void updatePassword(String password, long empno);
}
