package com.vino.lecture.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vino.lecture.domain.Admin;
import com.vino.lecture.domain.PageBean;


@Service("adminService")
public class AdminService extends BaseService {
	private List<Object> condition;
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
		public Admin login(String username,String password){
			String hql="from Admin a where a.username=? and a.password=?";
			condition=new ArrayList<Object>();
			condition.add(username);
			condition.add(password);
			 
			return (Admin)adminDao.query(hql, condition);	
			}
		
		

		@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
		public String addAdmin(Admin admin) throws RuntimeException{
			
			String hql="from Admin u where u.username=?";
			condition=new ArrayList<Object>();
			condition.add(admin.getUsername());
			try{
			if(adminDao.query(hql, condition)==null){
			adminDao.add(admin);
			return "success";
			}
			else
				return "repeat";
				
			} catch (Exception e) {
				//重新抛出运行时异常以便事务管理处理,在action中处理该异常
				e.printStackTrace();
				throw new RuntimeException();
				
			}
			
		}
		@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
		public void deleteAdmin(long id) throws RuntimeException{
			try{
			adminDao.delete(Admin.class,id);
			}catch(Exception e){
				throw new RuntimeException();
			}
		}
		
		@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
		public void deleteAdmins(List<Integer> ids) throws RuntimeException{
			try{
			for(Integer id :ids)
				if(id!=null)
					adminDao.delete(Admin.class,id);
			}catch(Exception e){
				throw new RuntimeException();
			}
		}
		
		
		@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
		public void updateAdmin(Admin admin) throws RuntimeException{
			String hql="update Admin u set u.password=? where u.id=?";
			condition=new ArrayList<Object>();
			condition.add(admin.getPassword());
			
			condition.add(admin.getId());
			try{
				adminDao.updateWithCondition(hql, condition);
				
			}catch(Exception e){
				throw new RuntimeException();
			}
		}
		@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
		public List<Admin> queryAllAdmin() {
			String hql="from User";	
			return adminDao.query(hql);	
		}
		@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
		public PageBean<Admin> pageQueryAdmin(int pageNo,int pageRecord) {		
			return adminDao.queryPage(pageNo, pageRecord,"Admin");		
		}
		

		
		
}
