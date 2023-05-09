package myshop.backend.service;

import myshop.backend.domain.Emp_files;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface EmpFilesService {
    Emp_files getEmpFileByEmpno(long empno);

    void insert(MultipartFile files, long empno) throws IOException;
    void update(MultipartFile file, long empno) throws IOException;
}
