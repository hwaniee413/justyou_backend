package myshop.backend.mapper;


import myshop.backend.domain.Event;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EventMapper {
    List<Event> selectAll();
}
