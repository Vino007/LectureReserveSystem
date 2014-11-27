package com.vino.lecture.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 上传文件
 * @author Joker
 *
 */
@Controller
public class FileUploadAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private File file;
	private String fileFileName;
	private String fileContentType;
	private String description;
	private String uploadDir;
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String path=ServletActionContext.getServletContext().getRealPath(uploadDir);
		System.out.println(path);
		String newFileName=null;
		long now=new Date().getTime();
		File dir=new File(path);
		if(!dir.exists())
			dir.mkdirs();
		int index=fileFileName.lastIndexOf(".");
		if(index!=-1)
		newFileName=now+fileFileName.substring(index);
		else
			newFileName=Long.toString(now);
		BufferedOutputStream bos=null;
		BufferedInputStream bis=null;
		System.out.println(newFileName);
		try {
			bis = new BufferedInputStream(new FileInputStream(file));
			bos = new BufferedOutputStream(new FileOutputStream(new File(dir,
					newFileName)));
			byte[] buf = new byte[4096];
			int len = -1;
			while ((len = bis.read(buf)) != -1) {
				bos.write(buf, 0, len);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(bis!=null)
				try {
					bis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(bos!=null)
				try {
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		}
	
		
			
		return SUCCESS;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUploadDir() {
		return uploadDir;
	}
	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}
}
