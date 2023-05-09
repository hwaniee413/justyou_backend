package myshop.backend.service;

import lombok.RequiredArgsConstructor;
import myshop.backend.domain.Dept;

import myshop.backend.mapper.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DeptServiceImpl implements DeptService{
    @Autowired
    private DeptMapper deptMapper;
    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }
}
