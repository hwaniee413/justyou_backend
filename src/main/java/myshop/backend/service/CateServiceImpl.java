package myshop.backend.service;

import myshop.backend.domain.Cate;
import myshop.backend.mapper.CateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CateServiceImpl implements CateService{
    @Autowired
    private CateMapper cateMapper;

    @Override
    public List<Cate> listAll() {
        return cateMapper.selectAll();
    }
}
