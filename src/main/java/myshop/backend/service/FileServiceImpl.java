package myshop.backend.service;

import com.google.gson.JsonObject;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;


@Service
public class FileServiceImpl implements FileService{

    @Override
    public Long saveFile(MultipartFile file) throws IOException {
        return null;
    }

    @Override
    public String saveFileInPath(MultipartFile file) throws IOException {
        JsonObject jsonObject = new JsonObject();

        String fileRoot = "C:\\ckeditor_image\\";
        String originalFileName = file.getOriginalFilename();	//오리지날 파일명
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
        String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명

        File targetFile = new File(fileRoot + savedFileName);
        try {
            InputStream fileStream = file.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
            jsonObject.addProperty("url", "ckeditor/"+savedFileName); // contextroot + resources + 저장할 내부 폴더명
            jsonObject.addProperty("fname", savedFileName); // 저장된 파일이름
            jsonObject.addProperty("responseCode", "success");

        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
            jsonObject.addProperty("responseCode", "error");
            e.printStackTrace();
        }
        String a = jsonObject.toString();
        System.out.println("imgUp: " + a);
        return a;
    }

    @Override
    public String getFileInPath(String fname) {
        JsonObject jsonObject = new JsonObject();

        String fileRoot = "C:\\ckeditor_image\\";
        File targetFile = new File(fileRoot + fname);

        return null;
    }
    void pln(String str){
        System.out.println(str);
    }
}
