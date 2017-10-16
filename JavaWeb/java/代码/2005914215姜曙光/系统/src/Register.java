import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.*;


public class Register extends JFrame  implements ActionListener
{
	JPanel  pnlRegister,pnlLay;
	JLabel  lblUserName,lblGender,lblAge,lblPicture,lblPicture1;
	JLabel  lblPassword,lblConfirmPass,lblEmail,logoPosition;
	JTextField  txtUserName,txtAge,txtEmail;
	JPasswordField  pwdUserPassword,pwdConfirmPass;
	JRadioButton  rbtnMale,rbtnFemale;
	ButtonGroup  btngGender;
    JButton  btnOk,btnCancel,btnClear;
    JComboBox  cmbpicture; //ͼ���ѡ��
    Icon logoa;
    
	String  strServerIp;
    //���ڽ��������ڶ�λ
	Dimension scrnsize;
    Toolkit toolkit=Toolkit.getDefaultToolkit();
    //���췽��
	public Register(String ip)
	{
		super("�����������ע��");
		strServerIp=ip;
		pnlRegister=new JPanel();
		this.getContentPane().add(pnlRegister);
	    lblPicture=new JLabel(" ͷ  ��:");
		lblUserName=new JLabel("�� �� ��:");
		lblGender=new JLabel("��    ��:");
		lblAge=new JLabel("��    ��:");
		lblPassword=new JLabel("��   ��:");
		lblConfirmPass=new JLabel("ȷ������:");
		lblEmail=new JLabel("�����ʼ�:");
		
		String list[]={"1","2","3","4","5"};
        cmbpicture= new JComboBox(list);
		
		
		txtUserName=new JTextField(30);
		txtEmail=new JTextField(30);
		txtAge=new JTextField(10);
		pwdUserPassword=new JPasswordField(30);
		pwdConfirmPass=new JPasswordField(30);
		rbtnMale=new JRadioButton("��",true);
		rbtnFemale=new JRadioButton("Ů");
	    btngGender=new ButtonGroup();
	    btnOk=new JButton("ȷ��");
	    btnOk.setMnemonic('O');
	    btnOk.setToolTipText("����ע����Ϣ");
		btnCancel=new JButton("����");
		btnCancel.setMnemonic('B');
		btnCancel.setToolTipText("���ص�¼����");
		btnClear=new JButton("���");
		btnClear.setMnemonic('L');
		btnClear.setToolTipText("���ע����Ϣ");
		
		
	
		
		/*  �ò��ֲ����ֶ�����           *
		 *��setBounds�������λ��        *
		 *  setFont�������塢���͡��ֺ�  *
		 *��setForeground�������ֵ���ɫ  *
		 *  setBackground���ñ���ɫ      *
		 *  setOpaque����������Ϊ͸��    */
		pnlRegister.setLayout(null);    //������ֶ�����
		pnlRegister.setBackground(new Color(255,130,203));
     
		
		lblPicture.setBounds(30, 250, 50, 30);
		lblUserName.setBounds(30,80,100,30);
		txtUserName.setBounds(110,85,120,20);
		lblPassword.setBounds(30,105,100,30);
		pwdUserPassword.setBounds(110,110,120,20);
		lblConfirmPass.setBounds(30,130,100,30);
		pwdConfirmPass.setBounds(110,135,120,20);
		lblGender.setBounds(30,155,100,30);
		rbtnMale.setBounds(110,160,60,20);
		rbtnFemale.setBounds(190,160,60,20);
		lblAge.setBounds(30,180,100,30);
		txtAge.setBounds(110,185,120,20);
		lblEmail.setBounds(30,205,100,30);
		txtEmail.setBounds(110,210,120,20);
		cmbpicture.setBounds(80,260,80,20);
		//lblPicture1.setBounds(170, 230, 40, 40);

	   // btnOk.setBounds(250,130,80,25);	
	   // btnCancel.setBounds(250,170,80,25);
	   // btnClear.setBounds(250,210,80,25);
	
		Font fontstr=new Font("����",Font.PLAIN,12);	
		lblPicture.setFont(fontstr);
		lblUserName.setFont(fontstr);
	    lblGender.setFont(fontstr);
		lblPassword.setFont(fontstr);
		lblConfirmPass.setFont(fontstr); 
		lblAge.setFont(fontstr);
		lblEmail.setFont(fontstr);
        rbtnMale.setFont(fontstr);
		rbtnFemale.setFont(fontstr);
		txtUserName.setFont(fontstr);
		txtEmail.setFont(fontstr);	
		btnOk.setFont(fontstr);
		btnCancel.setFont(fontstr);
		btnClear.setFont(fontstr);
						
		lblUserName.setForeground(Color.blue);
		lblPicture.setForeground(Color.blue);
		lblGender.setForeground(Color.blue);
		lblPassword.setForeground(Color.blue);
		lblAge.setForeground(Color.blue);
		lblConfirmPass .setForeground(Color.blue);
		lblEmail.setForeground(Color.blue);
		rbtnMale.setForeground(Color.blue);
		rbtnFemale.setForeground(Color.blue);
		rbtnMale.setBackground(Color.white);
		rbtnFemale.setBackground(Color.white);
		btnOk.setBackground(Color.gray);	
	    btnCancel.setBackground(Color.gray);
	    btnClear.setBackground(Color.gray);
		rbtnMale.setOpaque(false);   
		rbtnFemale.setOpaque(false);
		
//		int picture=Integer.parseInt(String.valueOf(cmbpicture.getSelectedItem()));
//		

		 
		logoa = new ImageIcon("images\\"+"touxiang"+String.valueOf(cmbpicture.getSelectedItem())+".jpg");

		lblPicture1 = new JLabel();
		lblPicture1.setIcon(logoa);
	 	lblPicture1.setBounds(170, 250, 40, 40);
		pnlRegister.add(lblPicture1);
	
		
		
		pnlRegister.add(lblUserName);
		pnlRegister.add(lblPicture);
		pnlRegister.add(lblPicture1);
		pnlRegister.add(lblGender);
		pnlRegister.add(lblPassword);
		pnlRegister.add(lblConfirmPass);
		pnlRegister.add(lblEmail);
		pnlRegister.add(lblAge);
		pnlRegister.add(txtAge);
		pnlRegister.add(txtUserName);
		pnlRegister.add(txtEmail);
		pnlRegister.add(pwdUserPassword);
		pnlRegister.add(pwdConfirmPass);
		//pnlRegister.add(btnOk);
		//pnlRegister.add(btnCancel);
		//pnlRegister.add(btnClear);
		pnlRegister.add(rbtnMale);
		pnlRegister.add(rbtnFemale);
		pnlRegister.add(cmbpicture);
		btngGender.add(rbtnMale);
	    btngGender.add(rbtnFemale);
	    
	 
	    
	    
		// ���ܴ���
		pnlLay=new JPanel(new GridLayout(1,3));    // GridLayout(14,1)  ���� 14�� 1��
		pnlLay.setBackground(new Color(255,130,203));
		pnlLay.setFont(new Font("����",0,12));
		pnlLay.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(""),BorderFactory.createEmptyBorder(1,1,1,1)));
	    
		pnlLay.setBounds(20,320,240,30);
		pnlLay.add(btnOk);
		pnlLay.add(btnCancel);
		pnlLay.add(btnClear);
		pnlRegister.add(pnlLay);
		
		this.setSize(270,380);
		this.setVisible(true);
		this.setResizable(false);
		
		
		
		
//		���ñ���ͼƬ
	    Icon logo = new ImageIcon("images\\registerlogo1.jpg");
	 	logoPosition = new JLabel(logo);
		logoPosition.setBounds(0, 0, 270,80);
		pnlRegister.add(logoPosition);
	    
		
		
		//�����ڶ�λ����Ļ����
    	scrnsize=toolkit.getScreenSize();
    	this.setLocation(scrnsize.width/2-this.getWidth()/2,
    	                 scrnsize.height/2-this.getHeight()/2);
//		Image img=toolkit.getImage("images\\appico.jpg");
//        this.setIconImage(img);
		//������ťע�����
		btnOk    .addActionListener(this);
		btnCancel.addActionListener(this);
		btnClear   .addActionListener(this);
		cmbpicture.addActionListener(this);
	}  //���췽������
	
	//��ť������Ӧ
	public void actionPerformed(ActionEvent ae)
	{
		Object source=new Object();
	    source=ae.getSource();
	    if (source.equals(btnOk))      //"ȷ��"��ť
	    {
	        register();
	    }
	    if (source.equals(btnCancel))  //"����"��ť
	    {
	    	new Login();
	    	this.dispose();
	    }
	    if (source.equals(btnClear))     //"���"��ť
	    {
	        txtUserName.setText("");
	        pwdUserPassword.setText("");
	        pwdConfirmPass.setText("");
	        txtAge.setText("");
	        txtEmail.setText("");	
	    }
	    if (source.equals(cmbpicture))
	    {
	    	logoa = new ImageIcon("images\\"+"touxiang"+String.valueOf(cmbpicture.getSelectedItem())+".jpg");
	    	lblPicture1.setIcon(logoa);
	    }
	}  //actionPerformed()����
	
	
	
	
	//////////"ȷ��"��ť�¼���Ӧ//////////
	public void register()
	{
		//���ܿͻ�����ϸ����
        Register_Customer data=new Register_Customer();
	    data.custName     = txtUserName.getText();
		data.custPassword = pwdUserPassword.getText();
		data.age          = txtAge.getText();
		data.sex          = rbtnMale.isSelected()?"��":"Ů";
		data.email        = txtEmail.getText();
		data.picture      = String.valueOf(cmbpicture.getSelectedItem());
		
		
		
		//��֤�û����Ƿ�Ϊ��
		if(data.custName.length()==0)
		{
		    JOptionPane.showMessageDialog(null,"�û�������Ϊ��");	
            return;	
		}
		//��֤�����Ƿ�Ϊ��
		if(data.custPassword.length()==0)
		{
		    JOptionPane.showMessageDialog(null,"���벻��Ϊ��");	
            return;	
		}
		
		//��֤�����һ����
		if(!data.custPassword.equals(pwdConfirmPass.getText()))
		{
		    JOptionPane.showMessageDialog(null,"�����������벻һ�£�����������");	
            return;
		}
		
		//��֤�����Ƿ�Ϊ��
		if(data.age.length()==0)
		{
		    JOptionPane.showMessageDialog(null,"���䲻��Ϊ��");	
            return;	
		}
		//��֤����ĺϷ���
		int age=Integer.parseInt(txtAge.getText());
		if (age<=0||age>100){
			JOptionPane.showMessageDialog(null,"�������벻�Ϸ�");
			return;
		}
		//��֤Email����ȷ��
		int Found_flag=0;    //�жϱ�־
		for (int i=0;i<data.email.length();i++)
		{
		    if(data.email.charAt(i)=='@')
		    {
		        Found_flag++;	
		    }	
		}
		if(Found_flag!=1)
		{
		    JOptionPane.showMessageDialog(null,"���������ʽ����ȷ������������");	
            return;	
		}
		
		try
		{
		    //���ӵ�������
		    Socket toServer;
  		    toServer = new Socket(strServerIp,8000);
		    ObjectOutputStream streamToServer=new ObjectOutputStream (toServer.getOutputStream());					
		    //д�ͻ���ϸ���ϵ�������socket
		    streamToServer.writeObject((Register_Customer)data);
            //�����Է�����socket�ĵ�½״̬
            BufferedReader fromServer=new BufferedReader(new InputStreamReader(toServer.getInputStream()));
            String status=fromServer.readLine();
            //��ʾ�ɹ���Ϣ
            JOptionPane op=new JOptionPane();
            op.showMessageDialog(null,status);
            
            if(status.equals(data.custName+"ע��ɹ�"))
            {
                txtUserName.setText("");
                pwdUserPassword.setText("");
                pwdConfirmPass.setText("");
                txtAge.setText("");
                txtEmail.setText("");
                cmbpicture.setSelectedItem("1");
               
            }
            
            //�ر�������
		    streamToServer.close();
            fromServer.close();
         }
		 catch(InvalidClassException e1)
		 {
		    JOptionPane.showMessageDialog(null,"�����!");
		 }
		 catch(NotSerializableException e2)
		 {
			JOptionPane.showMessageDialog(null,"����δ���л�!");
		 }
		 catch(IOException e3)
		 {
		 	JOptionPane.showMessageDialog(null,"����д�뵽ָ��������!");
		 }
		
	}  //����register()����
	public static void main(String args[])
	{
		new Register("127.0.0.1");
	}

}  //class Register ����
