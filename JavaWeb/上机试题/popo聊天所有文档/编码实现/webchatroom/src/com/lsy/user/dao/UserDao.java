/**
 * 
 */
package com.lsy.user.dao;

import java.sql.Connection;


import com.lsy.vo.User;




/**
 * @author ����Ԩ
 *
 */
public interface UserDao {


	//�û�ע��
	public boolean userRge(Connection conn, User user)throws Exception;
	//��¼��֤
	public  User userLogin(Connection conn,String qq, String password)throws Exception; 
	//ͨ��QQ�˺Ų�ѯ
	public User  findById(Connection conn, String qq)throws Exception;
	//�����û���Ϣ
	public boolean updataUser(Connection conn,User stu)throws Exception;
	
}
