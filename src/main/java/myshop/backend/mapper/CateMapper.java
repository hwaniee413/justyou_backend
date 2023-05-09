package myshop.backend.mapper;


import myshop.backend.domain.Cate;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CateMapper {
    List<Cate> selectAll();
}
