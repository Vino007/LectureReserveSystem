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
@Service
public class ExcelService extends BaseService{
	/*
	 * ��ѯ���ݿ��е�reserveInfo����ĳ������ԤԼ����
	 * ����excel
	 * ��download��������
	 */
	List<Object> condition;
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public void createReserveInfoExcel(long id){
		String hql="from ReserveInfo r where r.lectureId=?";
		condition=new ArrayList<Object>();
		condition.add(id);
		List<ReserveInfo> reserveInfos=reserveDao.queryWithCondition(hql, condition);
		//��ѯ������
		String title=(String) lectureDao.query("select l.title from LectureInfo l where l.id=?", condition);
		String[] heads={"ѧ��","����"};		
		try {
			createExcel(new FileOutputStream( ServletActionContext.getServletContext().getRealPath("/WEB-INF/download/export.xls")),
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
			System.out.println("�ļ�·������");
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
		//��ѯ������
		String title=(String) lectureDao.query("select l.title from LectureInfo l where l.id=?", condition);
		String[] heads={"ѧ��","����"};		
		try {
			createExcel(new FileOutputStream( ServletActionContext.getServletContext().getRealPath("/WEB-INF/download/export.xls")),
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
			System.out.println("�ļ�·������");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * ����excel
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void importExcel(String filename,long id){
		try {
			readExcel(new FileInputStream(ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload/"+filename)),id);
		} catch (BiffException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createExcel(OutputStream os,String title,String[] heads,List<ReserveInfo> list) throws IOException,
			RowsExceededException, WriteException {
		// ����������
		WritableWorkbook workbook = Workbook.createWorkbook(os);
		// �����µ�һҳ��sheetֻ���ڹ�������ʹ��
		WritableSheet sheet = workbook.createSheet("test sheet1", 0);

		// ������Ԫ�񼴾���Ҫ��ʾ�����ݣ�new Label(0,0,"�û�") ��һ��������column �ڶ���������row
		// ������������content�����ĸ������ǿ�ѡ��,ΪLabel���������ʽ

		// ͨ��sheet��addCell�������Label��ע��һ��cell/labelֻ��ʹ��һ��addCell

		// ��һ������Ϊ�У��ڶ���Ϊ�У��������ı�����
			
		sheet.addCell(new Label(0,0,title));//��ӽ�������
		
		//����ֶ���
		for(int i=0;i<heads.length;i++){
			sheet.addCell(new Label(i,1,heads[i]));
		}
		//����ֶ�����
		for(int i=0;i<list.size();i++){
			sheet.addCell(new Label(0, i+2, list.get(i).getUsername()));
			sheet.addCell(new Label(1, i+2, list.get(i).getName()));
		}
		// ������д��������У�Ȼ��رչ����������ر������
		workbook.write();
		workbook.close();
		os.close();
	}
	
	
	
	
	//����ѧ������ ѧ��+����
	//�����п�ʼΪ ��Ч����
	
	public void readExcel(InputStream is,long id) throws BiffException, IOException{
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
	public void importDB(){
		
	}

}
