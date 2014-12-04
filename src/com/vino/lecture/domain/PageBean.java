package com.vino.lecture.domain;

import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class PageBean<T> {

//	private int totalPage;//��ҳ��
	private int totalRecord;//�ܼ�¼
	private int pageNo;//��ǰҳ��
	private int pageRecord; //ÿҳ��¼��
	private List<T> beanList;//��ǰҳ�ļ�¼
//	private int firstPage;
//	private int lastPage;
	/**
	 * ��ʼ��page�趨
	 */
	public PageBean(){
		
		pageNo=1;
		pageRecord=5;
	}
	public int getFirstPage() {
	//	int totalPage=getTotalPage();
		
		return 1+10*(pageNo/10);
	/*	if(pageNo+10>totalPage)  //��һ�ַ�ҳ��ʾ��ʽ
			return totalPage-9;
	
		return 1;*/
	}
	
	public int getLastPage() {
		int totalPage=getTotalPage();
		if(getFirstPage()+9>totalPage)
			return totalPage;
		return getFirstPage()+9;
		
		/*if(getFirstPage()+10>totalPage) //��һ�ַ�ҳ��ʾ��ʽ
			return totalPage;
		
		return 10;*/
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
