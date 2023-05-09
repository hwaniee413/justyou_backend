package myshop.backend.service;

import myshop.backend.domain.Notice;
import myshop.backend.dto.NoticePagingDto;

import java.util.List;

public interface NoticeService {
    List<Notice> listWithPaging(NoticePagingDto noticePagingDto);

    List<Notice> listMaxFive();

    int countNoticeId();

    Notice getNoticeArticle(long nid);
    void insert(Notice notice);
    void update(Notice notice);
    void delete(long nid);
}
