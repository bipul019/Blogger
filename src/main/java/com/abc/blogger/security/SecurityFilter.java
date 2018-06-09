package com.abc.blogger.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecurityFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain 	chain)
			throws IOException, ServletException {
		
		HttpServletRequest httprequest=(HttpServletRequest)request;
		HttpServletResponse httpresponse=(HttpServletResponse)response;
		
		if(httprequest.getMethod().equalsIgnoreCase("options")||httprequest.getServletPath().equalsIgnoreCase("/signup")){
			chain.doFilter(request, response);
			return;
		}
		
		if(httprequest.getServletPath().equalsIgnoreCase("/signin")&&httprequest.isRequestedSessionIdValid()){
			httprequest.getSession().invalidate();
			return;
		}
		
		if(!httprequest.getServletPath().equalsIgnoreCase("/signin")&&!httprequest.isRequestedSessionIdValid()){
			httpresponse.sendError(401);
			return;
		}
		else{
			chain.doFilter(request, response);
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	

}
