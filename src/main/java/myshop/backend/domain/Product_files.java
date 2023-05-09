package myshop.backend.domain;


import lombok.Data;

@Data
public class Product_files {
    private long fid;
    private String fname;
    private String ofname;
    private String savedpath;
    private long pid;
    private Product product;
}
