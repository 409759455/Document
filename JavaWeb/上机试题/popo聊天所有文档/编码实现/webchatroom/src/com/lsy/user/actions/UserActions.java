/**
 * 
 */
package com.lsy.user.actions;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


import com.lsy.userinfo_actionform.UserActionForm;
import com.lsy.vo.User;
import com.sly.user.business.UserManager;

/**
 * @author Administrator
 * 
 */
public class UserActions extends DispatchAction {

	/*
	 * ͨ��QQ�˺Ų��Һ��ѵ���Ϣ
	 */
	public ActionForward FindByQq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("GBK");
		HttpSession session = request.getSession();
		UserActionForm uaf = (UserActionForm) form;// ��ȡ��½�˺�
		String qq = uaf.getQq();

		User user_info = (User) session.getAttribute("user_info");
		String my_qq = user_info.getQq();// �õ��Լ����˺�

		//System.out.println("12122qqqqq=" + qq);
		//System.out.println("my_qq=" + my_qq);

		User user = UserManager.getInstance().findById(qq);// ���غ�����Ϣ
		// ��������Ϣ���浽request��
		request.setAttribute("user_info", user);
		if (qq.equals(my_qq)) {
			return mapping.findForward("updata_success");
		} else {
			return mapping.findForward("findByQq_success");
		}
	}
	
}
	
