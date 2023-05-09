package myshop.backend.service;

import myshop.backend.domain.Emp;
import myshop.backend.mapper.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService{
    @Autowired
    private EmpMapper empMapper;

    private PasswordEncoder passwordEncoder;
    @Override
    public List<Emp> list() {
        return empMapper.selectEmpWithDept();
    }

    @Override
    public List<Emp> listByEmpRank(String emprank) {
        return empMapper.selectEmpWithDeptByEmpRank(emprank);
    }

    @Override
    public List<Emp> listByDeptNameAndEmpRank(String deptname, String emprank) {
        return empMapper.selectEmpWithDeptByDeptnameAndEmprank(deptname, emprank);
    }

    @Override
    public Long getTotalCount() {
        return empMapper.getTotalCount();
    }

    @Override
    public Long getTotalCountByDept(String deptname) {
        return empMapper.getTotalCountByDept(deptname);
    }

    @Override
    public Emp getEmpInfoByEmpnumber(long empnumber) {
        return empMapper.getEmpInfoByEmpnumber(empnumber);
    }
    @Override
    public List<Emp> getEmpInfoByEmpname(String empname) {
        return empMapper.getEmpInfoByEmpname(empname);
    }

    @Override
    public Boolean getEmpInfoByEmail(String email) {
        Emp emp = empMapper.getEmpInfoByEmail(email);
        if(emp != null){
            return true;  // 널이 아니므로 중복이 참
        } else {
            return false; // 널이면 중복이 거짓
        }
    }

    @Override
    public void insert(Emp emp) {
        empMapper.insert(emp);
    }

    @Override
    public void updateMobile(String mobile, long empno) {
        Emp emp = empMapper.getEmpInfoByEmpno(empno);
        emp.setMobile(mobile);
        empMapper.updateMobile(emp);
    }

    @Override
    public void updatePassword(String encodedPwd, long empno) {
        Emp emp = empMapper.getEmpInfoByEmpno(empno);
        emp.setPassword(encodedPwd);
        empMapper.updatePassword(emp);
    }
}
