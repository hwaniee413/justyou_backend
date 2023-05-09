package myshop.backend.service;

import myshop.backend.domain.Product;
import myshop.backend.dto.ProductsPagingDto;

import java.util.List;

public interface ProductService {

    List<Product> listWithPaging(ProductsPagingDto productsPagingDto);

    List<Product> listWithPagingByTitle(ProductsPagingDto productsPagingDto, String kw);
    List<Product> listWithPagingByCategory(ProductsPagingDto productsPagingDto, String kw);
    List<Product> listWithPagingByStatus(ProductsPagingDto productsPagingDto, String kw);
    List<Product> listWithPagingByEvent(ProductsPagingDto productsPagingDto, String kw);

    Product getProductByPid(long pid);

    int countProductsId();

    int countProductIdByTitleContaining(String kw);
    int countProductIdByCategoryContaining(String kw);

    int countProductIdByStatusContaining(String kw);
    int countProductIdByEventContaining(String kw);

    long insert(Product product);
    void update(Product product);
    void deleteById(long id);
}
