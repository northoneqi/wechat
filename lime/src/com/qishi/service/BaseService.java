package com.qishi.service;




public  interface BaseService<M  extends java.io.Serializable> {

	 	public M save(M model);

	    public void saveOrUpdate(M model);
	    
	    public void update(M model);
	    
	    public void merge(M model);
 
	    public void flush();
	    
	    public void clear();
	

}
