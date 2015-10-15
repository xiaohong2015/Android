package com.to.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

/**
 * @author IronMan
 *
 * 2015年2月14日
 */
public class EncodingFilter implements Filter {
	
	protected FilterConfig fc;

	@Override
	public void destroy() {
		this.fc= null;
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		
		HttpServletRequest  r= (HttpServletRequest ) arg0;
		HttpServletResponse o= (HttpServletResponse) arg1;
		
		if(r.getMethod().equalsIgnoreCase("POST")) {
			// post
			r.setCharacterEncoding("UTF-8");
		} else {
			Enumeration<String> pn= arg0.getParameterNames();
			while(pn.hasMoreElements()) {
				String n= pn.nextElement();
				String v[]= arg0.getParameterValues(n);
				for(int i=0; i<v.length; i++) {
					// get
					v[i]= new String(v[i].getBytes("iso-8859-1"), "UTF-8");
				}
				arg0.setAttribute(n, v);
			}
		}

		o.setCharacterEncoding("UTF-8");
		o.setContentType("text/html; charset=UTF-8");
		arg2.doFilter(r, o);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		this.fc= arg0;
	}
}
