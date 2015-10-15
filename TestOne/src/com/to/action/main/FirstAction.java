package com.to.action.main;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author IronMan
 *
 * 2015年2月13日
 */
public class FirstAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String i;
	@Override
	public String execute() throws Exception {
		System.out.println("execute---"+ i);
		return "success";
	}
	
	public String add() {
		System.out.println("add");
		return "success";
	}

	public String getI() {
		return i;
	}

	public void setI(String i) {
		this.i = i;
	}
	

}
