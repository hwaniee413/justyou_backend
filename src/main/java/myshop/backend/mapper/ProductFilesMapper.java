package myshop.backend.mapper;


import myshop.backend.domain.Product_files;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductFilesMapper {
    List<Product_files> selectProductFilesByPid(long pid);

    void insert(Product_files productsFiles);
    void update(Product_files productsFiles);
    void delete(long pid);

    void deleteByFnameAndPid(Product_files productFiles);
}
