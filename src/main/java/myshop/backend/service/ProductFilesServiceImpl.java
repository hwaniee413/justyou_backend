package myshop.backend.service;

import myshop.backend.domain.Product_files;
import myshop.backend.mapper.ProductFilesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


@Service
public class ProductFilesServiceImpl implements ProductFilesService {

    @Value("${file.dir}")
    private String fileDir;
    @Autowired
    private ProductFilesMapper productFilesMapper;
    @Override
    public List<Product_files> listProductsFilesByPid(long pid) {
        return productFilesMapper.selectProductFilesByPid(pid);
    }

    @Override
    public void insert(MultipartFile files, long pid) {
        try {
            String ofname = files.getOriginalFilename(); // 원래 파일 이름 추출
            String uuid = UUID.randomUUID().toString(); // 파일 이름으로 쓸 uuid 생성
            String extension = ofname.substring(ofname.lastIndexOf(".")); // 확장자 추출(ex : .png)
            String fname = uuid + extension; // uuid와 확장자 결합
            String savedPath = fileDir + fname; // 파일을 불러올 때 사용할 파일 경로

            Product_files productFiles = new Product_files();
            productFiles.setPid(pid);
            productFiles.setFname(fname);
            productFiles.setOfname(ofname);
            productFiles.setSavedpath(savedPath);
            files.transferTo(new File(savedPath));
            productFilesMapper.insert(productFiles);
            System.out.println("product file save ok?");
        }catch(IOException ioException){
            System.out.println("Products_files_insert_ioexception: " + ioException);
        }
    }

    @Override
    public void update(List<MultipartFile> files, long pid) {
        try {
            for (Product_files productFiles : productFilesMapper.selectProductFilesByPid(pid)) {
                productFilesMapper.delete(productFiles.getFid()); // 기존 파일 정보 삭제
            }

            for (MultipartFile file : files) {
                String ofname = file.getOriginalFilename(); // 원래 파일 이름 추출
                String uuid = UUID.randomUUID().toString(); // 파일 이름으로 쓸 uuid 생성
                String extension = ofname.substring(ofname.lastIndexOf(".")); // 확장자 추출(ex : .png)
                String fname = uuid + extension; // uuid와 확장자 결합
                String savedPath = fileDir + fname; // 파일을 불러올 때 사용할 파일 경로

                Product_files productFiles = new Product_files();
                productFiles.setPid(pid);
                productFiles.setFname(fname);
                productFiles.setOfname(ofname);
                productFiles.setSavedpath(savedPath);
                file.transferTo(new File(savedPath));
                productFilesMapper.insert(productFiles); // 새로운 파일 정보 입력
            }
            System.out.println("product file update ok?");
        }catch(IOException ioException){
            System.out.println("Products_files_update_ioexception: " + ioException);
        }
    }

    @Override
    public void deleteByFnameAndPid(Product_files productFiles) {
        productFilesMapper.deleteByFnameAndPid(productFiles);
    }
}
