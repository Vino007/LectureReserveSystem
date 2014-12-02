package com.vino.lecture.action;

import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.vino.lecture.service.ExcelService;

public class FileDownloadAction extends ActionSupport {

	/**
	 * 
	 */
	private long lectureId;
	public long getLectureId() {
		return lectureId;
	}
	public void setLectureId(long lectureId) {
		this.lectureId = lectureId;
	}
	private static final long serialVersionUID = 1L;
	private ExcelService excelService;
	public ExcelService getExcelService() {
		return excelService;
	}
	public void setExcelService(ExcelService excelService) {
		this.excelService = excelService;
	}
	private String inputPath;
	public String getInputPath() {
		return inputPath;
	}
	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}
	/**
	 * 返回一个inputstream的实例
	 * 给方法对应在result里的inputName属性值为targetFile
	 * @return
	 */
	public InputStream getTargetFile(){
		
		// ServletContext. getResourceAsStream(String path)：默认从WebAPP根目录下取资源，
		//Tomcat下path是否以’/'开头无所谓，
		//当然这和具体的容器实现有关。 
		return ServletActionContext.getServletContext().getResourceAsStream(inputPath);
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	}
	public String exportReserveListExcel() throws Exception {
		// TODO Auto-generated method stub
		excelService.createReserveInfoExcel(lectureId);
	
		return SUCCESS;
	}
	public String exportAttenceListExcel() throws Exception {
		// TODO Auto-generated method stub
		excelService.createAttenceListExcel(lectureId);
		//excelService.importExcel();
		return SUCCESS;
	}

}
