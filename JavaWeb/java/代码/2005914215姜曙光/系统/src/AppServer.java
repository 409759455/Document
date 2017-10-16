import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.util.*;

 class SystenMessage implements Serializable
{  String message;
   
}

//////////*����������*//////////
public class AppServer extends Thread implements ActionListener
{
	ServerSocket serverSocket;
	static ServerFrame sFrame=new ServerFrame();
	static Vector u=new Vector(1,1);
	static Vector v=new Vector(1,1);

	public AppServer()
	{
		

	    sFrame.btnSaveLog.addActionListener(this);
	    sFrame.btnStop.addActionListener(this);
	    sFrame.btnSend.addActionListener(this);
	
	 	
	 	try
	 	{
			serverSocket = new ServerSocket(8000);
			//��ȡ����������������IP��ַ
			InetAddress address = InetAddress.getLocalHost();      
//   			sFrame.txtServerName.setText(address.getHostName());
   			sFrame.txtIP.setText(address.getHostAddress());
   			sFrame.txtPort.setText("8000");
		}
		catch(IOException e)
		{
			fail(e,"������������");
		}
		  sFrame.txtStatus.setText("������...");
		  this.start();    //�����߳�
	}//���캯������
	
	
	   public static void fail(Exception e,String str)
	      {
		     System.out.println(str+" ��"+e);
	      } 
	
	
	//////////*�����ͻ�������*//////////
	public void run()
	{
		try 
		{
			while(true)
			{
				//���������ܿͻ�������
				Socket client = serverSocket.accept();
			    new Connection(client,u,v);   //֧�ֶ��߳�
			}
		}
		catch(IOException e)
		{
			fail(e,"���ܼ�����");
		}
    }
    
    
    //*****************����������***************/
    public static void main(String args[])
    {
    	new AppServer();
    }
    
    
  public void actionPerformed(ActionEvent ae)
 {
	  
	  Object source=(Object)ae.getSource();
	  
	                ////////////������־/////////////
	  if(source.equals(sFrame.btnSaveLog))
	  {
		  
		    	try
		    	{
		    		FileOutputStream  fileoutput=new FileOutputStream("Log.txt",true);
		    	    String temp=sFrame.taLog.getText()+"\n";
		    	    System.out.println(temp);
		    	    fileoutput.write(temp.getBytes());
		    	    fileoutput.close();
		        }
		        catch(Exception e)
		        {
		        	System.out.println(e);
		        }
		        
		    }  
	 
	            //////////////�˳�����/////////////////// 
	  if(source.equals(sFrame.btnStop))
	  {
		  sFrame.dispose();
		  
	  }
	  
	  
	             ///////////����ϵͳ��Ϣ////////////////
	  if(source.equals(sFrame.btnSend))
	  { SystenMessage systenchat =new SystenMessage();
	    systenchat.message=sFrame.txtNotice.getText();
	    
	    v.addElement(systenchat);
	    sFrame.taMessage.append("\n"+"ϵͳ��Ϣ��"+"\n   "+sFrame.txtNotice.getText()+"\n");
	    sFrame.txtNotice.setText("");
		  
	  }

 	}
 
}



//////////*�����߳�*//////////
class Connection extends Thread
{
	protected Socket netClient;
	
	Vector userOnline;
	Vector userChat;                                                                                     
	
	protected ObjectInputStream fromClient;  //�ӿͻ���������
	protected PrintStream toClient;          //�����ͻ���
	static Vector  vList = new Vector();     //�����û��б�
	
	Object obj;
	
	
	
	public Connection(Socket client,Vector u,Vector c)
	{
		netClient = client; //��ͻ������ӵ��׽���
		userOnline=u;       // �����û�������
		userChat=c;         //�����¼������
		
		try
		{
			//����˫��ͨ��
			                                   
			fromClient = new ObjectInputStream(netClient.getInputStream());//�����ͻ�����
			toClient = new PrintStream(netClient.getOutputStream());       //������д���ͻ�
		}
		catch(IOException e)
	 	{
			try
			{
				netClient.close();
			}
			catch(IOException e1)
			{
				System.out.println("���ܽ�����"+e1);
				return;
			}			
		}
		this.start();
	}
	
	public void run()
	{
	 try
	{       //obj��Object��Ķ���
	obj = (Object)fromClient.readObject();
			if(obj.getClass().getName().equals("Customer"))          //��¼
			{
			    serverLogin();	
			}
			if(obj.getClass().getName().equals("Register_Customer")) //ע��
			{
			    serverRegiste();	
			}
		    if(obj.getClass().getName().equals("Message"))           //������Ϣ����
		    {
		        serverMessage();
		    }
		    if(obj.getClass().getName().equals("Chat"))              //������Ϣ����
		    {
		        serverChat();
		    }
		    
		    if(obj.getClass().getName().equals("SendFile"))              //������Ϣ����
		    {
		        serverFile();
		    }
		    if(obj.getClass().getName().equals("Exit1"))             //�˳�����
		    {
		        serverExit();	
		    }	
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
		catch(ClassNotFoundException e1)
		{
			System.out.println("������������"+e1);
		}
		finally
		{
			try
			{
				netClient.close();
			}
			catch(IOException e)
			{
				System.out.println(e);
			}
		}
	}
	
	
	
	
	
	
	
	
	
	/**********��¼����**********/
	public void serverLogin()
	{
	    
	    try
	    {
	    Customer clientMessage2 = (Customer)obj;
	    			    	
	            //���ļ�
            FileInputStream file3 =new FileInputStream("user.txt");
		    ObjectInputStream objInput1 = new ObjectInputStream(file3);
		    vList=(Vector)objInput1.readObject(); 
			    	
		    int find=0;  //�����жϱ�־
//		    System.out.println(find);
		    for(int i=0;i<vList.size();i++)
		    {     
		       Register_Customer reg=(Register_Customer)vList.elementAt(i);
		          
		       if ( reg.custName.equals(clientMessage2.custName) )
		       {
		           find=1; 
		           if( !reg.custPassword.equals(clientMessage2.custPassword) )
		           {
		      	        toClient.println("���벻��ȷ");
		      	        
		      	        break;
		           }
		           else
		           {
		      	        //�ж��Ƿ��Ѿ���¼
		      	        int login_flag=0;
		      	        for(int a=0;a<userOnline.size();a++)
		      	        {
		      	            if(	clientMessage2.custName.equals(userOnline.elementAt(a)))
		      	            {
		      	            	login_flag=1;
		      	            	break;
		      	            }
		      	        }
		      	        
		      	        if (login_flag==0)
		      	        {
		      	            userOnline.addElement(clientMessage2.custName);//���øյ�½���û�����ӵ��û��б���
		      	            toClient.println(reg.picture+"Loginsucces");//+reg.picture);
		      	            // toClient.println(reg.picture);
		      	             
		      	            Date t=new Date();
		      	            System.out.println("�û�"+clientMessage2.custName+"��¼�ɹ���"+
		      	                               "��¼ʱ��:"+t.toString()+"\n");
		      	           AppServer.sFrame.lstUser.add(clientMessage2.custName);
		      	           
		      	           AppServer.sFrame.taLog.append("�û�"+clientMessage2.custName+"��¼�ɹ���"+"��¼ʱ��:"+t.toString()+"\n");
		      	          
		      	           String  userNumber=String.valueOf(userOnline.size());
		      	         
		      	           
		      	           AppServer.sFrame.txtNumber.setText(userNumber);
		      	            
		       	            break;
		       	        }
		       	        else
		       	        {
		       	            toClient.println("���û��ѵ�¼");
		       	        }
		           } 
		       }
		       else
		       {
		           continue;	
		       }    
		    }
		    if (find == 0)
		    {
		  	    toClient.println("û������û�������ע��");
	        }
	        
	        file3.close();
		    objInput1.close();
		    fromClient.close();	
	    }
	    catch(ClassNotFoundException e)
  		{
  			System.out.println(e);
  		}
  		catch(IOException e)
  		{
  			System.out.println(e);
  		}
	}
	
	
	
	
	
	
	
	
	
	
	//ע��ֻ�����û��������������֤����Registerȥ����
	
	/**********ע�ᴦ��**********/
     public void serverRegiste()
    {
     try
     {
       	int flag=0;  //�Ƿ������жϱ�־
		Register_Customer clientMessage =(Register_Customer)obj;
       	File fList=new File("user.txt");
      	if(fList.length()!= 0)//�ж��Ƿ��ǵ�һ��ע���û�
      	{
        	ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(fList));
			vList=(Vector)objInput.readObject(); 
			//�ж��Ƿ�������
			for(int i=0;i<vList.size();i++)
			{
	 			Register_Customer reg=(Register_Customer)vList.elementAt(i);
     			if(reg.custName.equals(clientMessage.custName))
         		{
          			toClient.println("ע�����ظ�,������ѡ��");
           			flag=1;
             		break;
          		}
			}
       }
      if (flag==0)
      {
	
    //�����ע���û�
	vList.addElement(clientMessage);
	
	//�������е���д���ļ�
	FileOutputStream file = new FileOutputStream(fList);
	ObjectOutputStream objout = new ObjectOutputStream(file);
	objout.writeObject(vList);
	
	//����ע��ɹ���Ϣ
		        toClient.println(clientMessage.custName+"ע��ɹ�");
		        Date t=new Date();
		        
		        AppServer.sFrame.taLog.append("�û�"+clientMessage.custName+"ע��ɹ�!;ע��ʱ��:"+t.toString()+"\n");
		        
		        System.out.println("�û�"+clientMessage.custName+"ע��ɹ�, "+
		                           "ע��ʱ��:"+t.toString()+"\n");
		       
		        
				    
		        file.close();
		        objout.close();
		        fromClient.close();
		    }
	    }
	    catch(ClassNotFoundException e)
  		{
  			System.out.println(e);
  		}
  		catch(IOException e)
  		{
  			System.out.println(e);
  		}
    }
    
   
     
     
     
     
     
     
     
     
     /**********������Ϣ����**********/
    public void serverMessage()
    {
        try
        {
        	Message mess=new Message();
            mess.userOnLine=userOnline;
            mess.chat=userChat;
        
            ObjectOutputStream outputstream=new ObjectOutputStream(netClient.getOutputStream());
            outputstream.writeObject((Message)mess);
            
            netClient.close();
            outputstream.close();
        }    
        catch(IOException e)
  	  	{
  	  	}
        
    }
    
    
    
    
    
    
    
    
    /**********������Ϣ����**********/
	public void serverChat()
  	{
  		//�����յ��Ķ���ֵ����������Ϣ�����л�����
  		Chat cObj = new Chat();
  		cObj = (Chat)obj;
  		
  	 	String UserMessage;
  		if(!cObj.whisper)
  			{
  			UserMessage="��"+cObj.chatUser+"���ԡ�"+cObj.chatToUser+"��˵��"+cObj.chatMessage+"\n";
  			}
  		else{
  			UserMessage="��"+cObj.chatUser+"�����Ķԡ�"+cObj.chatToUser+"��˵��"+cObj.chatMessage+"\n";
  		}
  		
  		AppServer.sFrame.taMessage.append(UserMessage);
  		
  		
  		//��������Ϣ�����л�������ӵ�����������Ϣ��ʸ����
  		userChat.addElement((Chat)cObj);
  		return;
  	}	   
  	
	
	//�����ļ�
  	
     public	void serverFile()
     
   {  
//    	�����յ��Ķ���ֵ����������Ϣ�����л�����
   		SendFile cObj = new SendFile();
   		cObj = (SendFile)obj;
   		String UserMessage;
   		
   		if(!(cObj.file1.getName().equals("aaa.txt")))
   		{
   		if(!cObj.whisper)
   			{
   			UserMessage="��"+cObj.chatUser+"���ԡ�"+cObj.chatToUser+"���������ļ���"+cObj.file1+"\n";
   			}
   		else{
   			UserMessage="��"+cObj.chatUser+"�����Ķԡ�"+cObj.chatToUser+"�����ķ������ļ���"+cObj.file1+"\n";
   		}
   		
   		AppServer.sFrame.taMessage.append(UserMessage);
   		}
   		
   		//��������Ϣ�����л�������ӵ�����������Ϣ��ʸ����
   		userChat.addElement((SendFile)cObj);
   		return; 
         }
	
	
	
	
	
	
	/**********�û��˳�����**********/
  	public void serverExit()
  	{
  		Exit1 exit1=new Exit1();
  		exit1=(Exit1)obj;
  		  		
  		userOnline.removeElement(exit1.exitname);  //�����û�ɾ��
  		
  		
  		Date t=new Date();
  		
  		System.out.println("�û�"+exit1.exitname+"�Ѿ��˳�, "+
  		                   "�˳�ʱ��:"+t.toString()+"\n");
  		AppServer.sFrame.taLog.append("�û�"+exit1.exitname+"�Ѿ��˳�, "+"�˳�ʱ��:"+t.toString()+"\n");
  		AppServer.sFrame.lstUser.remove(exit1.exitname);
        String  userNumber=String.valueOf(userOnline.size());
        AppServer.sFrame.txtNumber.setText(userNumber);
  		
  	}
}                     //Connection����