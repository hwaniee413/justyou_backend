package myshop.backend.service;

import myshop.backend.domain.Emp_files;
import myshop.backend.mapper.EmpFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


@Service
public class EmpFilesSeviceImpl implements EmpFilesService{

    @Value("${file.dir}")
    private String fileDir;
    @Autowired
    private EmpFileMapper empFileMapper;

    @Override
    public Emp_files getEmpFileByEmpno(long empno) {
        return empFileMapper.selectEmpFilesByEmpno(empno);
    }

    @Override
    public void insert(MultipartFile file, long empno) throws IOException {
        String ofname = file.getOriginalFilename(); // 원래 파일 이름 추출
        String uuid = UUID.randomUUID().toString(); // 파일 이름으로 쓸 uuid 생성
        String extension = ofname.substring(ofname.lastIndexOf(".")); // 확장자 추출(ex : .png)
        String fname = uuid + extension; // uuid와 확장자 결합
        String savedPath = fileDir + fname; // 파일을 불러올 때 사용할 파일 경로

        Emp_files empFiles = new Emp_files();
        empFiles.setFname(fname);
        empFiles.setOfname(ofname);
        empFiles.setSavedpath(savedPath);
        empFiles.setEmpno(empno);
        file.transferTo(new File(savedPath)); // 실제로 로컬에 uuid를 파일명으로 저장
        empFileMapper.insert(empFiles);
    }

    @Override
    public void update(MultipartFile file, long empno) throws IOException {
        String ofname = file.getOriginalFilename(); // 원래 파일 이름 추출
        String uuid = UUID.randomUUID().toString(); // 파일 이름으로 쓸 uuid 생성
        String fname = ofname.substring(ofname.lastIndexOf(".")); // 확장자 추출(ex : .png)
        String savedName = uuid + fname; // uuid와 확장자 결합
        String savedPath = fileDir + savedName; // 파일을 불러올 때 사용할 파일 경로

        Emp_files empFiles = empFileMapper.selectEmpFilesByEmpno(empno);
        empFiles.setFname(fname);
        empFiles.setOfname(ofname);
        empFiles.setSavedpath(savedPath);
        file.transferTo(new File(savedPath)); // 실제로 로컬에 uuid를 파일명으로 저장
        empFileMapper.update(empFiles);

    }

}
