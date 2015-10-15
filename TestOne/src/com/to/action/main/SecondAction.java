package com.to.action.main;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author IronMan
 *
 * 2015年2月14日
 */
public class SecondAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	public String create() {
		System.out.println("SecondAction--create");
		return "success";
	}
	public String delete() {
		System.out.println("SecondAction--delete");
		return "success";
	}
	
}
