package com.vino.lecture.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.junit.Test;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.vino.lecture.domain.ReserveInfo;
import com.vino.lecture.domain.User;
@Service
public class ExcelService extends BaseService{
	/*
	 * 查询数据库中的reserveInfo表中某讲座的预约名单
	 * 生成excel
	 * 由download导出下载
	 */
	List<Object> condition;
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public void createReserveInfoExcel(long id){
		String hql="from ReserveInfo r where r.lectureId=?";
		condition=new ArrayList<Object>();
		condition.add(id);
		List<ReserveInfo> reserveInfos=reserveDao.queryWithCondition(hql, condition);
		//查询讲座名
		String title=(String) lectureDao.query("select l.title from LectureInfo l where l.id=?", condition);
		String[] heads={"学号","姓名"};		
		try {
			createAttenceOrReserveExcel(new FileOutputStream( ServletActionContext.getServletContext().getRealPath("/WEB-INF/download/export.xls")),
					title, heads, reserveInfos);
		System.out.println(ServletActionContext.getServletContext().getContextPath());
		
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("文件路径错误");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public void createAttenceListExcel(long id){
		String hql="from ReserveInfo r where r.lectureId=? and r.attence=1";
		condition=new ArrayList<Object>();
		condition.add(id);
		List<ReserveInfo> reserveInfos=reserveDao.queryWithCondition(hql, condition);
		//查询讲座名
		String title=(String) lectureDao.query("select l.title from LectureInfo l where l.id=?", condition);
		String[] heads={"学号","姓名"};		
		try {
			createAttenceOrReserveExcel(new FileOutputStream( ServletActionContext.getServletContext().getRealPath("/WEB-INF/download/export.xls")),
					title, heads, reserveInfos);
		System.out.println(ServletActionContext.getServletContext().getContextPath());
		
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("文件路径错误");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 导入考勤excel
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void importAttenceExcel(String filename,long id){
		try {
			readAttenceExcel(new FileInputStream(ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload/"+filename)),id);
		} catch (BiffException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 导入用户excel
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void importUserExcel(String filename){
		try {
			readUserExcel(new FileInputStream(ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload/"+filename)));
		} catch (BiffException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建预约列表或者考勤表的excel的底层，两者的信息是一样的
	 * @param os
	 * @param title
	 * @param heads
	 * @param list
	 * @throws IOException
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	public void createAttenceOrReserveExcel(OutputStream os,String title,String[] heads,List<ReserveInfo> list) throws IOException,
			RowsExceededException, WriteException {
		// 创建工作区
		WritableWorkbook workbook = Workbook.createWorkbook(os);
		// 创建新的一页，sheet只能在工作簿中使用
		WritableSheet sheet = workbook.createSheet("test sheet1", 0);

		// 创建单元格即具体要显示的内容，new Label(0,0,"用户") 第一个参数是column 第二个参数是row
		// 第三个参数是content，第四个参数是可选项,为Label添加字体样式

		// 通过sheet的addCell方法添加Label，注意一个cell/label只能使用一次addCell

		// 第一个参数为列，第二个为行，第三个文本内容
			
		sheet.addCell(new Label(0,0,title));//添加讲座标题
		
		//添加字段名
		for(int i=0;i<heads.length;i++){
			sheet.addCell(new Label(i,1,heads[i]));
		}
		//添加字段内容
		for(int i=0;i<list.size();i++){
			sheet.addCell(new Label(0, i+2, list.get(i).getUsername()));
			sheet.addCell(new Label(1, i+2, list.get(i).getName()));
			sheet.addCell(new Label(2, i+2, list.get(i).getGrade()));
			sheet.addCell(new Label(3, i+2, list.get(i).getMajor()));
		
		}
		// 将内容写到输出流中，然后关闭工作区，最后关闭输出流
		workbook.write();
		workbook.close();
		os.close();
	}
		
	//导入学生名单 学号+姓名
	//第三行开始为 有效数据
	
	public void readAttenceExcel(InputStream is,long id) throws BiffException, IOException{
		Workbook workbook=Workbook.getWorkbook(is);
		if(workbook.getNumberOfSheets()>0){
			Sheet sheet=workbook.getSheet(0);
			Cell[] usernameCells=sheet.getColumn(0);
			Cell[] nameCells=sheet.getColumn(1);
			for(int i=2;i<nameCells.length;i++){
				String hql="update ReserveInfo r set r.attence=1 where r.username=? and r.name=? and lectureId=?";
				List<Object> condition=new ArrayList<Object>();
				condition.add(usernameCells[i].getContents());
				condition.add(nameCells[i].getContents());
				condition.add(id);
				reserveDao.updateWithCondition(hql, condition);			
				System.out.println(nameCells[i].getContents());
				System.out.println(id);
			}
			workbook.close();
			is.close();
				
		}
	}
	
	/**
	 * excel格式：
	 * 第一行为 字段名：学号，姓名，性别，年级，学院
	 * 第二行为 实际值
	 * @param is
	 * @throws BiffException
	 * @throws IOException
	 */
	public void readUserExcel(InputStream is) throws BiffException, IOException{
		Workbook workbook=Workbook.getWorkbook(is);
		if(workbook.getNumberOfSheets()>0){
			Sheet sheet=workbook.getSheet(0);
			Cell[] usernameCells=sheet.getColumn(0);
			Cell[] nameCells=sheet.getColumn(1);
			Cell[] genderCells=sheet.getColumn(2);
			Cell[] gradeCells=sheet.getColumn(3);
			Cell[] majorCells=sheet.getColumn(4);
			
			for(int i=1;i<nameCells.length;i++){
				User user=new User();
				user.setUsername(usernameCells[i].getContents());
				user.setName(nameCells[i].getContents());
				user.setGender(genderCells[i].getContents());
				user.setGrade(gradeCells[i].getContents());
				user.setMajor(majorCells[i].getContents());
				userDao.add(user);
				System.out.println(nameCells[i].getContents());
			
			}
			workbook.close();
			is.close();
				
		}
	}


}
