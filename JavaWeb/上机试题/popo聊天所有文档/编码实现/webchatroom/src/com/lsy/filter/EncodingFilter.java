package com.lsy.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;





public class EncodingFilter implements Filter {
	//�������������ʹ���һ��ȫ�ֵĶ���usermap�������ڱ�����û���session
	private static Map<String , HttpSession> usermap = new HashMap<String, HttpSession>();

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			
		} catch (Exception e) {
		}
		
		chain.doFilter(request, response);

	} 
	
	
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
	//���û�����֮��Ӧ��session���浽usermap��
	public static void put(String key,HttpSession value)
	{
		usermap.put(key, value);
	}
	//ͨ���û����˺Ż�ø��û���session
	public static HttpSession getValue(String key)
	{
		return usermap.get(key);
	}
	
	public void destroy() {

	}

}
