/**
 * 
 */
package com.lsy.user.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import org.json.JSONObject;

import com.lsy.userinfo_actionform.UserActionForm;
import com.lsy.vo.User;
import com.sly.user.business.UserManager;
import com.lsy.filter.EncodingFilter;
import com.lsy.model.UserInfo;
import com.lsy.servlet.Messages;

/**
 * �û���¼��֤
 */
public class LoginAction extends DispatchAction {

	/*
	 * ���ݿ��ѯ����֤��¼�ʺţ�����
	 */

	private String json;

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		UserActionForm uaf = (UserActionForm) form;
		//System.out.println("000000000����Action�������");
		User user = null;
		String qq = uaf.getQq();
		String password = uaf.getPassword();
		// json = new
		// String(request.getParameter("json").getBytes("ISO8859-1"),"UTF-8");
		// JSONObject jsonObj = new JSONObject(json); //��JSON��ʽ���ַ��������JSON����
		// String qq = jsonObj.getString("qq"); //��ȡJSON�����е�qq���Ե�ֵ
		// String password = jsonObj.getString("password");
		// //��ȡJSON�����е�password���Ե�ֵ

		//System.out.println("qq=" + qq);
		UserInfo userinfo = UserInfo.getInstance(); // ���UserInfo��Ķ���
		Vector vector = userinfo.getList();
		boolean flag = true; // ����Ƿ��¼�ı���
		// �ж��û��Ƿ��¼
		//System.out.println("vector=" + vector);
		for (int i = 0; i < vector.size(); i++) {
			user = (User) vector.elementAt(i);
			
			if (qq.equals(user.getQq())) {

				PrintWriter out;
				try {
					out = response.getWriter();
					out
							.println("<script language='javascript'>alert('���û��ѵ�¼');window.location.href='index.jsp';</script>");
				} catch (IOException e) {
					e.printStackTrace();
				}
				flag = false;
				
				break;
			}
		}

		if (flag) {
			user = UserManager.getInstance().userLogin(qq, password);
		}

		if (user != null) {

			// ��¼�ɹ���ת������ҳ��
			//System.out.println("User=" + user.getNickname() + " "+ user.getQq() + " " + user.getPhoto());
			// ���û���Ϣ���浽session��
			session.setAttribute("user_info", user);
			 // ����һ��HashMap������������ÿ���û���session id
			EncodingFilter.put(user.getQq(), session);
			response.sendRedirect("Messages?action=loginRoom");
			// return mapping.findForward("success");
			// response.sendRedirect("/chatjsp/MyJsp.jsp");
			// //request.getRequestDispatcher("../Messages?action=loginRoom").forward(request,
			// response);
			// System.out.println("ooooû���ҵ�·��ooo");
			// //response.sendRedirect("Messages?action=loginRoom");

		} else {
			// �����id����Ҫ�ص�login.jspҳ��
			PrintWriter out;
			try {
				out = response.getWriter();
				out
						.println("<script language='javascript'>alert('�˺Ż�������������µ�¼');window.location.href='index.jsp';</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
			// json = "{ msg:'��¼ʧ��,�˺Ż��������!'}";

		}

		// response.setCharacterEncoding("UTF-8");
		// response.getWriter().write(json);
		return null;
	}

}
