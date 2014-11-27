package com.vino.lecture.action;

import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.vino.lecture.service.ExcelService;

public class FileDownloadAction extends ActionSupport {

	/**
	 * 
	 */
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
	 * ����һ��inputstream��ʵ��
	 * ��������Ӧ��result���inputName����ֵΪtargetFile
	 * @return
	 */
	public InputStream getTargetFile(){
		
		// ServletContext. getResourceAsStream(String path)��Ĭ�ϴ�WebAPP��Ŀ¼��ȡ��Դ��
		//Tomcat��path�Ƿ��ԡ�/'��ͷ����ν��
		//��Ȼ��;��������ʵ���йء� 
		return ServletActionContext.getServletContext().getResourceAsStream(inputPath);
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	}
	public String createExcel() throws Exception {
		// TODO Auto-generated method stub
		excelService.createReserveInfoExcel();
		excelService.importExcel();
		return SUCCESS;
	}


}
