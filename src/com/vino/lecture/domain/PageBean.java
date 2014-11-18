package com.vino.lecture.domain;

import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class PageBean<T> {

//	private int totalPage;//总页数
	private int totalRecord;//总记录
	private int pageNo;//当前页数
	private int pageRecord; //每页记录数
	private List<T> beanList;//当前页的记录
	/**
	 * 初始化page设定
	 */
	public PageBean(){
		
		pageNo=1;
		pageRecord=5;
	}
	public int getTotalPage() {
		int totalPage=totalRecord/pageRecord;
		return totalRecord%pageRecord==0?totalPage:totalPage+1;
	}
	/*public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}*/
	public int getTotalRecord() {
		return totalRecord;
	}
	public List<T> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageRecord() {
		return pageRecord;
	}
	public void setPageRecord(int pageRecord) {
		this.pageRecord = pageRecord;
	}
	
}
