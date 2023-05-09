package myshop.backend.controller;


import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import myshop.backend.domain.Cate;
import myshop.backend.domain.Event;
import myshop.backend.domain.Product;
import myshop.backend.domain.Product_files;
import myshop.backend.dto.ProductsPagingDto;
import myshop.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("products")
public class RestfulProductController {

    @Autowired
    private final CateService cateService;

    @Autowired
    private final EventService eventService;

    @Autowired
    private final FileService fileService;

    @Autowired
    private final ProductService productService;

    @Autowired
    private final ProductFilesService productFilesService;

    @GetMapping("list")
    public Map<String, Object> listProductsWithPaging(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize
            ) {
        int totalCount = productService.countProductsId();
        ProductsPagingDto productsPagingDto = new ProductsPagingDto(pageNum, pageSize, totalCount);

        List<Product> productList = productService.listWithPaging(productsPagingDto);
        Map<String, Object> result = new HashMap<>();
        result.put("productList",productList);
        result.put("pageDto", productsPagingDto);
        return result;
    }
    @PostMapping("/insert")
    public long insert(@RequestBody Product products) {
        long pid = productService.insert(products);
        return pid;
    }
    @PostMapping("{pid}/fileUp")
    public ResponseEntity<String> insertImageFile(@PathVariable("pid") long pid,
                                                  @RequestParam("files") List<MultipartFile> files) {
        try {
            for (MultipartFile file : files) {
                productFilesService.insert(file, pid);
            }
            return ResponseEntity.ok("이미지 파일 저장 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미지 파일 저장 실패");
        }
    }
    @GetMapping("getfirstfile")
    public ResponseEntity<byte[]> getProductFiles(@RequestParam long pid){
        List<Product_files> productFiles = productFilesService.listProductsFilesByPid(pid);
        if(productFiles.size() ==0){
            return ResponseEntity.notFound().build();
        }

        // 첫 번째 파일을 가져온다.
        Product_files file = productFiles.get(0);
        // 이미지 파일을 읽어들인다.
        byte[] data = null;

        try (InputStream is = new FileInputStream(file.getSavedpath())) {
            data = is.readAllBytes();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        // 이미지 파일을 반환한다.
        HttpHeaders headers = new HttpHeaders();
        //headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(data.length);
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

    @GetMapping("imagefiles")
    public Map<String, Object> getProductFilesList(@RequestParam long pid){
        List<Product_files> list = productFilesService.listProductsFilesByPid(pid);
        Map<String, Object> map = new HashMap<>();
        if(list.size()==0){
            map.put("null", "저장된 파일이 없습니다.");
        } else {
            map.put("fileList", list);
        }
        return map;
    }
    @PostMapping("deletefile")
    public void deleteProductFile (@RequestBody Product_files productFiles){
        productFilesService.deleteByFnameAndPid(productFiles);
    }
    @PostMapping("update")
    public void update(@RequestBody Product product){
        productService.update(product);
    }
    @PostMapping("update/{pid}/fileup")
    public ResponseEntity<String> updateImageFile(@PathVariable("pid") long pid,
                                                  @RequestParam("files") List<MultipartFile> files) {
        List<Product_files> list = productFilesService.listProductsFilesByPid(pid);
        if(list.size()==0){
            try {
                for (MultipartFile file : files) {
                    productFilesService.insert(file, pid);
                }
                return ResponseEntity.ok("이미지 파일 저장 성공");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미지 파일 저장 실패");
            }
        } else {
            try {
                productFilesService.update(files, pid);
                return ResponseEntity.ok("이미지 파일 수정 성공");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미지 파일 저장 실패");
            }
        }
    }

    // 상품 내용보기 + 수정 위해 Product 가져오기
    @GetMapping("product")
    public Product getProductByPid(@RequestParam long pid){
        return productService.getProductByPid(pid);
    }


    //검색
    @GetMapping("search")
    public Map<String, Object> searchlistProductsWithPaging(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false, value = "title") String titleKw,
            @RequestParam(required = false, value = "category") String categoryKw,
            @RequestParam(required = false, value = "status") String statusKw,
            @RequestParam(required = false, value = "event") String eventKw
    ) {

        if(titleKw != null){
            int totalCount = productService.countProductIdByTitleContaining(titleKw);
            ProductsPagingDto productsPagingDto = new ProductsPagingDto(pageNum, pageSize, totalCount);
            List<Product> productList = productService.listWithPagingByTitle(productsPagingDto, titleKw);
            pln("search productList: "+ productList);
            Map<String, Object> result = new HashMap<>();
            result.put("productList",productList);
            result.put("pageDto", productsPagingDto);
            return result;
        }
        if(categoryKw != null){
            int totalCount = productService.countProductIdByCategoryContaining(categoryKw);
            ProductsPagingDto productsPagingDto = new ProductsPagingDto(pageNum, pageSize, totalCount);
            List<Product> productList = productService.listWithPagingByCategory(productsPagingDto, categoryKw);
            Map<String, Object> result = new HashMap<>();
            result.put("productList",productList);
            result.put("pageDto", productsPagingDto);
            return result;
        }
        if(statusKw != null){
            int totalCount = productService.countProductIdByStatusContaining(statusKw);
            ProductsPagingDto productsPagingDto = new ProductsPagingDto(pageNum, pageSize, totalCount);
            List<Product> productList = productService.listWithPagingByStatus(productsPagingDto, statusKw);
            Map<String, Object> result = new HashMap<>();
            result.put("productList",productList);
            result.put("pageDto", productsPagingDto);
            return result;
        }
        if(eventKw != null){
            int totalCount = productService.countProductIdByEventContaining(eventKw);
            ProductsPagingDto productsPagingDto = new ProductsPagingDto(pageNum, pageSize, totalCount);
            List<Product> productList = productService.listWithPagingByEvent(productsPagingDto, eventKw);
            Map<String, Object> result = new HashMap<>();
            result.put("productList",productList);
            result.put("pageDto", productsPagingDto);
            return result;
        }
        return null;
    }
    // 카테고리 리스트
    @GetMapping("getcatelist")
    public List<Cate> getCateList(){
        return cateService.listAll();
    }


    // 이벤트 리스트
    @GetMapping("geteventlist")
    public List<Event> getEventList(){
        return eventService.listAll();
    }

    @PostMapping(value = "/upload_files", produces = "application/json; charset=utf8")
    public String uploadCKEditorFilesInPath(@RequestParam("file")
                                            MultipartFile file) throws IOException {
        return fileService.saveFileInPath(file);
    }

    //상품 삭제
    @GetMapping("delete")
    public void deleteProduct(@RequestParam long pid){
        productService.deleteById(pid);
    }

    void pln(String str){
        System.out.println(str);
    }

}
