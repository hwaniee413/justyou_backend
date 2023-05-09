package myshop.backend.mapper;

import myshop.backend.domain.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EmpMapper {
    List<Emp> selectEmpWithDept();

    List<Emp> selectEmpWithDeptByEmpRank(String emprank);
    List<Emp> selectEmpWithDeptByDeptnameAndEmprank(@Param("deptname") String deptname, @Param("emprank") String emprank);
    Emp getEmpInfoByEmpnumber(long empnumber);
    List<Emp> getEmpInfoByEmpname(String empname);

    Emp getEmpInfoByEmpno(long empno);

    Emp getEmpInfoByEmail(String email);

    Long getTotalCount();
    Long getTotalCountByDept(String deptname);

    void insert(Emp emp);

    void updateMobile(Emp emp);
    void updatePassword(Emp emp);


}
