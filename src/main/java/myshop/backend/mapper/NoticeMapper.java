package myshop.backend.mapper;


import myshop.backend.domain.Notice;
import myshop.backend.dto.NoticePagingDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface NoticeMapper {
    List<Notice> selectNoticeWithAdmin(NoticePagingDto noticePagingDto);

    List<Notice> selectNoticeWithAdminMaxFive();

    Notice selectByIdWithAdmin(long nid);

    int countNoticeId();

    void insertSelectKey(Notice notice);
    void updateById(Notice notice);
    void deleteById(long nid);
}
