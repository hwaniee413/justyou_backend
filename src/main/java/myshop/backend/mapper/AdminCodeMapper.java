package myshop.backend.mapper;


import myshop.backend.domain.Admin;
import myshop.backend.domain.Admincode;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdminCodeMapper {

    Admincode getAdminCode(long id);
}
