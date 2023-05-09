package myshop.backend.controller;


import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import lombok.RequiredArgsConstructor;
import myshop.backend.domain.Notice;
import myshop.backend.dto.NoticePagingDto;
import myshop.backend.service.FileService;
import myshop.backend.service.NoticeService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("notice")
@CrossOrigin(origins = "*", maxAge = 3600) //#매우 중요!
@RestController
public class RestfulNoticeController {

    @Autowired
    private final NoticeService noticeService;

    @Autowired
    private final FileService fileService;

    @GetMapping("")
    public Map<String, Object> noticeList(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize
            ) {
        int totalCount = noticeService.countNoticeId();
        pln("noticeList in - 1");
        NoticePagingDto noticePagingDto = new NoticePagingDto(pageNum, pageSize, totalCount);
        pln("noticeList in - 2");
        List<Notice> noticeList = noticeService.listWithPaging(noticePagingDto);
        pln("noticeList: " + noticeList);
        Map<String, Object> result = new HashMap<>();
        result.put("noticeList",noticeList);
        result.put("pageDto", noticePagingDto);
        return result;
    }

    @GetMapping("/forfooter")
    public List<Notice> noticeListForFooter() {
        pln("noticeListForFooter in?");
        List<Notice> noticeList = noticeService.listMaxFive();
        return noticeList;
    }
    @PostMapping("/create")
    public void insert(@RequestBody Notice notice) {
        pln("insert in? "+ notice);
        noticeService.insert(notice);
    }

    @GetMapping("{nid}")
    public Notice getNoticeArticle(@PathVariable long nid){
        return noticeService.getNoticeArticle(nid);
    }
    @PostMapping("/update/{nid}")
    public void update(@PathVariable long nid, @RequestBody Notice notice) {
        pln("update notice: " + notice);

        noticeService.update(notice);
    }
    @DeleteMapping("/del/{nid}")
    public void delete(@PathVariable long nid){
        pln("delete nid: " + nid);
        noticeService.delete(nid);
    }

    //공지사항 게시판 CkEditor 이미지 업로드 처리
    @PostMapping(value = "/upload_files", produces = "application/json; charset=utf8")
    public String uploadCKEditorFilesInPath(@RequestParam("file")
            MultipartFile file) throws IOException {
        pln("upload_files in?");
        pln("file: " + file);
        return fileService.saveFileInPath(file);
    }


    void pln(String str){
        System.out.println(str);
    }
}
