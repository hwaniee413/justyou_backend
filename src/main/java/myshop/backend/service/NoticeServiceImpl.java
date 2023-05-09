package myshop.backend.service;

import myshop.backend.domain.Notice;
import myshop.backend.dto.NoticePagingDto;
import myshop.backend.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;


    @Override
    public List<Notice> listWithPaging(NoticePagingDto noticePagingDto) {

        pln("#listWithPaging: " + noticePagingDto);
        return noticeMapper.selectNoticeWithAdmin(noticePagingDto);
    }


    @Override
    public List<Notice> listMaxFive() {
        return noticeMapper.selectNoticeWithAdminMaxFive();
    }

    @Override
    public int countNoticeId() {
        return noticeMapper.countNoticeId();
    }

    @Override
    public Notice getNoticeArticle(long nid) {
        return noticeMapper.selectByIdWithAdmin(nid);
    }

    @Override
    public void insert(Notice notice) {
        noticeMapper.insertSelectKey(notice);
    }

    @Override
    public void update(Notice notice) {
        noticeMapper.updateById(notice);
    }
    @Override
    public void delete(long nid){
        noticeMapper.deleteById(nid);
    }

    void pln(String str){
        System.out.println(str);
    }


}
