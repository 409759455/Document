import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.util.*;
import java.io.File;


public class ClassList {
	
	class Customer implements Serializable
	{
		String custName;
		String custPassword;
	}

//	��װע����Ϣ
	class Register_Customer extends Object implements java.io.Serializable
	{
	     String custName;
	     String custPassword;
	     String age;
	     String sex;
	     String email;
	}

//	���ڷ�������������û�����Ϣ  
	class Message implements Serializable
	{
	  	Vector userOnLine;
	  	Vector chat;
	}
//	������Ϣ���л�
	class Chat implements Serializable
	{
		String  chatUser;
		String  chatMessage;
		String  chatToUser;
		File    file1;
		boolean filee;
		boolean whisper;
	}  
//	�˳���Ϣ���л�
/*	class Exit1 implements Serializable
	{
	    String exitname;	
	}*/

}

