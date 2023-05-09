package myshop.backend.domain;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Product {
    private long pid;
    private String title;
    private String description;
    private long retail;
    private long sale;

    private String category;
    private String status;
    private String event;
    private long quantity;

    private LocalDateTime rdate;
    private LocalDateTime udate;

    private LocalDateTime start_at;
    private LocalDateTime end_at;

    private Product_files product_files;

}
