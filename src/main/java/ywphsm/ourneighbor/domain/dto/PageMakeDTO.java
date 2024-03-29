package ywphsm.ourneighbor.domain.dto;

import lombok.Data;

@Data
public class PageMakeDTO {

    /* 시작 페이지 */
    private int startPage;

    /* 끝 페이지 */
    private int endPage;

    /* 이전 페이지, 다음 페이지 존재유무 */
    private boolean prev, next;

    /* 전체 게시물 수 */
    private long total;

    //현재 페이지
    private int pageNum;

    /* 생성자 */
    public PageMakeDTO(int pageNum, int amount, long total) {

        this.total = total;


        /* 마지막 페이지 */
        pageNum++;
        this.pageNum = pageNum;
        this.endPage = (int) (Math.ceil(pageNum / 10.0)) * 10;
        /* 시작 페이지 */
        this.startPage = this.endPage - 9;

        /* 전체 마지막 페이지 */
        int realEnd = (int) (Math.ceil(total * 1.0 / amount));

        /* 전체 마지막 페이지(realend)가 화면에 보이는 마지막페이지(endPage)보다 작은 경우, 보이는 페이지(endPage) 값 조정 */
        if (realEnd < this.endPage) {
            this.endPage = realEnd;
        }

        /* 시작 페이지(startPage)값이 1보다 큰 경우 true */
        this.prev = this.startPage > 1;

        /* 마지막 페이지(endPage)값이 1보다 큰 경우 true */
        this.next = this.endPage < realEnd;

    }
}
