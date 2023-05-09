package myshop.backend.mapper;


import myshop.backend.domain.Emp_files;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EmpFileMapper {
    Emp_files selectEmpFilesByEmpno(long empno);

    void insert(Emp_files empFiles);

    void update(Emp_files empFiles);
}
