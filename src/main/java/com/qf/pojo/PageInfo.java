package com.qf.pojo;

import lombok.Data;

import java.util.List;

/**
 * Administrator 2019/7/16 0016 11:29
 */
@Data
public class PageInfo<T> {
    //已知
    private Integer page;
    //已知
    private Integer size;
    //已知
    private Long total;

    private Integer pages;
    //total % size == 0? total/size:total/size+1;
    private Integer offset;
    private List<T> list;

    public PageInfo(Integer page, Integer size, Long total) {
        this.page = page<=0?1:page;
        this.size = size<=0?5:size;
        this.total = total<0?0:total;
        this.pages=(int)(this.total % this.size == 0? this.total/this.size:this.total/this.size+1);
        this.offset=(this.page-1)*this.size;
    }
}
