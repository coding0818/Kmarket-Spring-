package kr.co.kmarket.vo;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyPagingVO {
    private String uid;
    private int pageSize = 5;
    private int cntPerPage = 10; //페이지당 글개수

    // 현재페이지, 시작페이지, 끝페이지, 게시글 총개수, 마지막페이지, SQL쿼리에 쓸 start, end
    private int nowPage, startPage, endPage, total, lastPage, start, end;

    public MyPagingVO(int total, int nowPage, String uid){
        setNowPage(nowPage);
        setCntPerPage(cntPerPage);
        setTotal(total);
        setUid(uid);
        calcLastPage(getTotal(), getCntPerPage());
        calcStartEndPage(getNowPage(), pageSize);
        calcStartEnd(getNowPage(), getCntPerPage());
    }

    // 제일 마지막 페이지 계산
    public void calcLastPage(int total, int cntPerPage){
        if (total % cntPerPage > 0) {
            setLastPage((int)Math.ceil((double)total / (double)cntPerPage));
        }else{
            setLastPage(total / cntPerPage);
        }
    }

    // 시작, 끝 페이지 계산
    public void calcStartEndPage(int nowPage, int pageSize){
        setEndPage((int)Math.ceil((double)nowPage / (double)pageSize) * pageSize);
        if (getLastPage() < getEndPage()){
            setEndPage(getLastPage());
        }
        
    }

    // DB 쿼리에서 사용할 start, end 값 계산
    public void calcStartEnd(int nowPage, int cntPerPage){
        setEnd(nowPage * cntPerPage);
        setStart(getEnd() - cntPerPage + 1);
    }
}
