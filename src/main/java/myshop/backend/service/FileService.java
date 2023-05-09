package myshop.backend.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    Long saveFile(MultipartFile file) throws IOException;

    String saveFileInPath(MultipartFile multipartFile) throws  IOException;

    String getFileInPath(String fname);
}
