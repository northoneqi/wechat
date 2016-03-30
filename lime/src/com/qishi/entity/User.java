package com.qishi.entity;

import java.io.Serializable; 

import javax.persistence.Basic; 
import javax.persistence.Column; 
import javax.persistence.Entity; 
import javax.persistence.GeneratedValue; 
import javax.persistence.GenerationType; 
import javax.persistence.Id; 
import javax.persistence.Table; 

import org.springframework.beans.factory.annotation.Qualifier;

@Entity 

@Table(name="tuser") 

public class User implements Serializable{ 

private static final long serialVersionUID = 1L; 

private String id; 

private String name; 

private String psw; 

@Column(name="name") 
public String getName() { 
return name; 
} 
@Id 

@Basic(optional=false) 

@Column(name="id") 

@GeneratedValue(strategy=GenerationType.IDENTITY) 
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public void setName(String name) { 

this.name = name; 

} 

@Column(name="psw") 

public String getPsw() { 

return psw; 

} 

public void setPsw(String psw) { 

this.psw = psw; 

} 


} 
