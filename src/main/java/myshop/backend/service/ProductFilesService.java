package myshop.backend.service;


import myshop.backend.domain.Product_files;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface ProductFilesService {
        List<Product_files> listProductsFilesByPid(long pid);

        void insert(MultipartFile files, long pid);

        void update(List<MultipartFile> files, long pid);

        void deleteByFnameAndPid(Product_files productFiles);
}
