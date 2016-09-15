package com.trinasolar.oauth.mgr.domain;

/**
 * Created by zhm on 16-9-15.
 */
public class Page {
    private int pageno;
    private int pagesize;
    private int total;
    private int totalPages;

    public Page(int pageno, int pagesize, int total) {
        this.pageno = pageno;
        this.pagesize = pagesize;
        this.total = total;
        this.totalPages = (total + pagesize -1) / pagesize;
    }

    public int getPageno() {
        return pageno;
    }

    public void setPageno(int pageno) {
        this.pageno = pageno;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
