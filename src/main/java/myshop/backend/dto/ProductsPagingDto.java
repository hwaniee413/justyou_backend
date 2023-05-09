package myshop.backend.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProductsPagingDto {

    // 현재 페이지
    private int pageNum;

    // 한 페이지당 게시글 수
    private int pageSize;
    // 전체 게시글 수
    private int totalCount;

    // 전체 페이지수
    private int totalPageCount;

    //MyBatis ROWNUM 조건에 들어갈 변수
    private int startRow;
    private int endRow;

    public ProductsPagingDto(int pageNum, int pageSize, int totalCount){
        this.pageNum=pageNum;
        this.pageSize=pageSize;
        this.totalCount=totalCount;
        this.totalPageCount= calTotalcount();
    }

    private int calTotalcount() {
        int totalPageCount = totalCount/pageSize;
        if(totalCount%pageSize !=0) totalPageCount++;
        return totalPageCount;
    }
}
