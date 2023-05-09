package myshop.backend.mapper;


import myshop.backend.domain.Schedule;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ScheduleMapper {

    List<Schedule> selectWithDeptByDeptname(String deptname);
}
