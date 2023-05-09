package myshop.backend.mapper;

import myshop.backend.domain.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface AdminMapper {
    Admin getAdminbyId(long admin_num);

    Admin getAdminByEmailId(String admin_id);

    Admin getAdminByPassword(String admin_pwd);

    void signUp(Admin admin);
}
