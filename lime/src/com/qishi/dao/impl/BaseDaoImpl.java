package com.qishi.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.qishi.dao.BaseDao;
import com.qishi.util.GenericsUtils;

public class BaseDaoImpl<M extends java.io.Serializable> implements BaseDao<M> {
	
	private final Class<M> entityClass;
    private final String Hql_From;
    @SuppressWarnings("unchecked")
	 public BaseDaoImpl() {
		 	//
	        this.entityClass = GenericsUtils.getSuperClassGenricType(getClass(), 0);
	        Hql_From = "from " + this.entityClass.getSimpleName();
	       
	 }

	 @Autowired
	 @Qualifier("sessionFactory")
		private SessionFactory sessionFactory; 
		
		public Session getSession() {
	        return sessionFactory.getCurrentSession();
	    }


	


		public List<M> findAllbyorder(String lieming, String shunxi) {
			// TODO Auto-generated method stub
			String hql = Hql_From+" order by "+lieming+" "+shunxi;
			System.out.println(hql);
			return  getSession().createQuery(hql).list();
			
		}

		public List<M> findbypage(int page, int pagesize) {
			String hql = Hql_From;
			Query   query   =   getSession().createQuery(hql); 
			query.setFirstResult((page-1)*pagesize); 		
			query.setMaxResults(pagesize); 		
			List<M> list= (List<M>)query.list();
			return list;
		}

		public List<M> findbyleiming(String ziduan, String liezhi) {
			String hql = Hql_From+" where "+ziduan+" = '"+liezhi+"'";
			return getSession().createQuery(hql).list();
		}
		

		public int findAllcount() {
			// TODO Auto-generated method stub
			String hql = Hql_From ;
			List list = getSession().createQuery(hql).list();
			int a =list.size(); 
			return a ;
		}





		public List<M> findAll() {
			// TODO Auto-generated method stub
			return getSession().createQuery(Hql_From).list(); 
		}





		@Override
		public void merge(M model) {
			// TODO Auto-generated method stub
			 getSession().merge(model);
		}





		@Override
		public void flush() {
			// TODO Auto-generated method stub
			 getSession().flush();
		}





		@Override
		public void clear() {
			// TODO Auto-generated method stub
			 getSession().clear();
		}





		@Override
		public void update(M model) {
			getSession().update(model);
			
		}





		@Override
		public void delete(M model) {
			getSession().delete(model);
			
		}





		@Override
		public void save(M model) {
			getSession().save(model);
			
		}





		@Override
		public void saveOrUpdate(M model) {
			getSession().saveOrUpdate(model);
			
		}





		


}
