package myshop.backend.service;


import myshop.backend.domain.Product;
import myshop.backend.dto.ProductsPagingDto;
import myshop.backend.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> listWithPaging(ProductsPagingDto productsPagingDto) {
        return productMapper.selectAllWithPaging(productsPagingDto);
    }

    @Override
    public List<Product> listWithPagingByTitle(ProductsPagingDto productsPagingDto, String kw) {
        return productMapper.selectAllWithPagingByTitle(productsPagingDto, kw);
    }

    @Override
    public List<Product> listWithPagingByCategory(ProductsPagingDto productsPagingDto, String kw) {
        return productMapper.selectAllWithPagingByCategory(productsPagingDto, kw);
    }

    @Override
    public List<Product> listWithPagingByStatus(ProductsPagingDto productsPagingDto, String kw) {
        return productMapper.selectAllWithPagingByStatus(productsPagingDto, kw);
    }

    @Override
    public List<Product> listWithPagingByEvent(ProductsPagingDto productsPagingDto, String kw) {
        return productMapper.selectAllWithPagingByEvent(productsPagingDto, kw);
    }

    @Override
    public Product getProductByPid(long pid) {
        return productMapper.selectProductByPid(pid);
    }

    @Override
    public int countProductsId() {
        return productMapper.countProductsId();
    }

    @Override
    public int countProductIdByTitleContaining(String kw) {
        return productMapper.countProductIdByTitleContaining(kw);
    }

    @Override
    public int countProductIdByCategoryContaining(String kw) {
        return productMapper.countProductIdByCategoryContaining(kw);
    }

    @Override
    public int countProductIdByStatusContaining(String kw) {
        return productMapper.countProductIdByStatusContaining(kw);
    }

    @Override
    public int countProductIdByEventContaining(String kw) {
        return productMapper.countProductIdByEventContaining(kw);
    }

    @Override
    public long insert(Product product) {
        System.out.println("insert - products: " + product);
        productMapper.insert(product);
        return product.getPid();
    }

    @Override
    public void update(Product products) {
        productMapper.updateById(products);
    }
    @Override
    public void deleteById(long id) {
        productMapper.deleteById(id);
    }
}
