package myshop.backend.mapper;


import myshop.backend.domain.Product;
import myshop.backend.dto.ProductsPagingDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductMapper {
    List<Product> selectAllWithPaging(ProductsPagingDto productsPagingDto);

    List<Product> selectAllWithPagingByTitle(@Param("pagingDto") ProductsPagingDto productsPagingDto, @Param("kw") String kw);
    List<Product> selectAllWithPagingByCategory(@Param("pagingDto") ProductsPagingDto productsPagingDto, @Param("kw") String kw);
    List<Product> selectAllWithPagingByStatus(@Param("pagingDto") ProductsPagingDto productsPagingDto, @Param("kw") String kw);
    List<Product> selectAllWithPagingByEvent(@Param("pagingDto") ProductsPagingDto productsPagingDto, @Param("kw") String kw);
    Product selectProductByPid(long pid);
    int countProductsId();

    int countProductIdByTitleContaining(String kw);
    int countProductIdByCategoryContaining(String kw);
    int countProductIdByStatusContaining(String kw);
    int countProductIdByEventContaining(String kw);

    long insert(Product products);
    void updateById(Product products);
    void deleteById(long id);
}
