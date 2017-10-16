import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.net.*;
import java.io.*;
import java.util.*;
import java.io.File;

class SendFile implements Serializable {
	String chatUser;
	String chatToUser;
	File file1;
	boolean whisper;
}


class Exit1 implements Serializable {
	String exitname;
}


public class ChatRoom extends Thread implements ActionListener
{

	static JFrame frmChat;
	JPanel pnlChat, pnlChoice;
	File file;
	JButton btnCls, btnExit, btnSend, btnSave, btnSendFile,
			btnBrowse;
	JLabel lblChatIag, lblUserList, lblUserMessage, lblSendMessage,
			lblChatUser, lblnumber, lbluser, lblpicture, lblname, lblTochat;
	JLabel lblUserTotal, lblCount, lblBack, lblFile;
	JTextField txtMessage, txtFile,txtTouser;
	java.awt.List lstUserList;
	TextArea taUserMessage; // ��Ϣ�ı���
    JComboBox cmbUser; // �б�ѡ��
	JRadioButton  chPrivateChat,chPublicChat,PublicChat;
	ButtonGroup a,b;
	String strServerIp, strLoginName; // strLoginName��Ϊ�����ڵ��û�
	Thread thread;
	JMenuBar mbChat;         // �˵���
	JMenu mnuSystem, mnuHelp;// �˵�
	JMenuItem mnuiCls, mnuiSave, mnuiClock, mnuiExit, mnuiContent,mnuiAbout;
	Introduction dailog11;
	Introductions dailogs;
	// ���ڽ��������ڶ�λ
	Dimension scrnsize;
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	byte butter[];      //�����ı��ļ�����

	// ���췽��
	public ChatRoom(String name, String ip, String picturec) {
		strServerIp = ip;
		strLoginName = name;

		frmChat = new JFrame("�����������" + "[�û�:" + name + "]");
		pnlChat = new JPanel();
		dailog11 =new Introduction();
		dailogs=new Introductions();
		
		//����ر�ʱĬ��ִ�еĲ���
		frmChat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChat.getContentPane().add(pnlChat);

		Font fntDisp1 = new Font("����", Font.PLAIN, 12);
		// Font fntDisp2=new Font("����",Font.PLAIN,11);

		mbChat = new JMenuBar();
		mnuSystem = new JMenu("ϵͳ(S)");
		mnuSystem.setMnemonic(KeyEvent.VK_S);
		mnuSystem.setFont(fntDisp1);

		mnuHelp = new JMenu("����(H)");
		mnuHelp.setMnemonic(KeyEvent.VK_H);
		mnuHelp.setFont(fntDisp1);

		mbChat.add(mnuSystem);
		mbChat.add(mnuHelp);

		// ϵͳ�˵�
		mnuiCls = new JMenuItem("�����Ļ");
		mnuiCls.setFont(fntDisp1);
		mnuiSave = new JMenuItem("���������¼");
		mnuiSave.setFont(fntDisp1);
		// mnuiClock=new JMenuItem("�鿴ʱ��");
		// mnuiClock.setFont(fntDisp1);
		mnuiExit = new JMenuItem("�˳�ϵͳ");
		mnuiExit.setFont(fntDisp1);
		mnuSystem.add(mnuiCls);
		mnuSystem.add(mnuiSave);
		// mnuSystem.add(mnuiClock);
		mnuSystem.add(mnuiExit);
		mnuiCls.addActionListener(this);
		mnuiSave.addActionListener(this);
		mnuiExit.addActionListener(this);

		// �����˵�
		mnuiContent = new JMenuItem("Ŀ¼");
		mnuiContent.setFont(fntDisp1);
		mnuiAbout = new JMenuItem("���������ϵͳ���");
		mnuiAbout.setFont(fntDisp1);
		mnuHelp.add(mnuiContent);
		mnuHelp.add(mnuiAbout);
		mnuiContent.addActionListener(this);
		mnuiAbout.addActionListener(this);
		

		// ////////////////////// �˵���/////////////////
		frmChat.setJMenuBar(mbChat);
		String list[] = { "������" };
		btnSendFile=new JButton("�����ļ�");
		btnCls = new JButton("����");
		btnExit = new JButton("�˳�");
		btnSend = new JButton("����(N)");
		btnSave = new JButton("����");
		btnBrowse = new JButton("���...");
		lblUserList = new JLabel("�������û��б�");

		lblname = new JLabel("�û�:��" + strLoginName + "��");
		// lblpicture=new JLabel();

		lblUserMessage = new JLabel("��������Ϣ��");
		lblSendMessage = new JLabel("��������:");
		lblChatUser = new JLabel("�����ں�:");

		lblUserTotal = new JLabel("��������:");
		lblCount = new JLabel("0");

		lblFile = new JLabel("�ı��ļ�:");
		txtFile = new JTextField(20);
		lstUserList = new java.awt.List();
		txtMessage = new JTextField(170);
		cmbUser = new JComboBox(list);
		lblTochat = new JLabel(" ������");
		txtTouser = new JTextField("������");
		PublicChat=new JRadioButton("Ⱥ��",true);
		chPrivateChat = new JRadioButton("˽��");
		chPublicChat = new JRadioButton("����");
		a=new ButtonGroup();
		a.add(chPublicChat);
		a.add(PublicChat);
		a.add(chPrivateChat);
		
	
		taUserMessage = new TextArea("", 300, 200,
				TextArea.SCROLLBARS_VERTICAL_ONLY);// ֻ�����¹���
		taUserMessage.setEditable(false); // ����д��

		pnlChat.setLayout(null);           //�ֶ�����
		pnlChat.setBackground(new Color(255, 130, 203));
		btnSendFile.setBounds(470, 465, 100, 25);
		btnBrowse.setBounds(400, 465, 80, 25);
		btnSend.setBounds(500, 410, 80, 25);

		lblFile.setBounds(210, 440, 80, 25);
		lblUserList.setBounds(5, 50, 120, 40);
		lblUserTotal.setBounds(130, 50, 60, 40);
		lblCount.setBounds(190, 0, 110, 40);
		lblUserMessage.setBounds(225, 100, 180, 40);
		lblChatUser.setBounds(160, 340, 110, 40);
		lblSendMessage.setBounds(160, 380, 60, 40);

		// �û�ͼ�����ʾ
		lblname.setBounds(430, 50, 100, 40);

		lblUserTotal.setBounds(25, 390, 80, 40);// �����û���������ʾ
		lblCount.setBounds(110, 390, 100, 40);

		txtFile.setBounds(275, 440, 300, 25);
		lstUserList.setBounds(25, 90, 120, 305);
		taUserMessage.setBounds(165, 90, 400, 255);
		txtMessage.setBounds(220, 385, 360, 25);
		// cmbUser.setBounds(220,350,80,25);
		lblTochat.setBounds(220, 350, 80, 25);
		txtTouser.setBounds(220, 350, 80, 25);
		chPrivateChat.setBounds(335, 352, 60, 20);
		chPublicChat.setBounds(400, 352, 60, 20);
		PublicChat.setBounds(465, 352, 60, 20);

		btnSendFile.setFont(fntDisp1);
		btnBrowse.setFont(fntDisp1);
		btnCls.setFont(fntDisp1);
		btnExit.setFont(fntDisp1);
		btnSend.setFont(fntDisp1);
		btnSave.setFont(fntDisp1);
		lblFile.setFont(fntDisp1);
		lblUserList.setFont(fntDisp1);
		lblUserMessage.setFont(fntDisp1);
		lblChatUser.setFont(fntDisp1);
		lblSendMessage.setFont(fntDisp1);
		// lblUserTotal.setFont(fntDisp1);
		// lblCount.setFont(fntDisp1);
		txtFile.setFont(fntDisp1);
		cmbUser.setFont(fntDisp1);

		chPrivateChat.setFont(fntDisp1);
		chPublicChat.setFont(fntDisp1);
		taUserMessage.setFont(new Font("����", Font.PLAIN, 12));

		lblUserList.setForeground(Color.YELLOW);
		lblUserMessage.setForeground(Color.YELLOW);
		lblSendMessage.setForeground(Color.red);
		lblChatUser.setForeground(Color.red);
		lblSendMessage.setForeground(Color.black);
		lblUserTotal.setForeground(Color.red);
		lblCount.setForeground(Color.red);
		cmbUser.setForeground(Color.black);
		chPrivateChat.setForeground(Color.black);
		chPublicChat.setForeground(Color.black);
		lstUserList.setBackground(Color.white);
		taUserMessage.setBackground(Color.white);
		btnBrowse.setBackground(Color.PINK);
		lblTochat.setBackground(Color.PINK);
		lblTochat.setForeground(Color.GREEN);
		btnSendFile.setBackground(Color.PINK);
		btnCls.setBackground(Color.ORANGE);
		btnExit.setBackground(Color.ORANGE);
		btnSend.setBackground(Color.PINK);
		btnSave.setBackground(Color.ORANGE);
//		cmbmusic.setForeground(Color.RED);
//		cmbmusic.setBackground(Color.yellow);
		chPrivateChat.setOpaque(false);   
		chPublicChat.setOpaque(false);
		PublicChat.setOpaque(false);

		pnlChat.add(lblFile);
		pnlChat.add(txtFile);
		pnlChat.add(btnSendFile);
		pnlChat.add(btnBrowse);
		pnlChat.add(lblname);
		pnlChat.add(btnSend);
		// pnlChat.add(btnSave);
		// pnlChat.add(lblUserList);
		pnlChat.add(lblUserMessage);
		pnlChat.add(lblSendMessage);
		pnlChat.add(lblChatUser);
		pnlChat.add(lblUserTotal);
		pnlChat.add(lblCount);
		pnlChat.add(lstUserList);
		pnlChat.add(taUserMessage);
		pnlChat.add(txtMessage);
		pnlChat.add(txtTouser);
		pnlChat.add(chPrivateChat);
		pnlChat.add(chPublicChat);
		pnlChat.add(PublicChat);

		// /////////////���ӹ������/////////////////
		pnlChoice = new JPanel(new GridLayout(1, 5)); // GridLayout(14,1) ����
		// 14�� 1��
		pnlChoice.setBackground(new Color(255, 130, 203));
		pnlChoice.setFont(new Font("����", 0, 12));
		pnlChoice.setBorder(BorderFactory.createCompoundBorder(BorderFactory
				.createTitledBorder(""), BorderFactory.createEmptyBorder(1, 1,
				1, 1)));

		pnlChoice.setBounds(25, 475, 350, 35);
		pnlChat.add(pnlChoice);
		// pnlChoice.add(cmbmusic);
		pnlChoice.add(btnSave);
		pnlChoice.add(btnCls);
		pnlChoice.add(btnExit);

		frmChat.addWindowListener(new Windowclose());

		btnSendFile.addActionListener(this);
		btnBrowse.addActionListener(this);
		btnCls.addActionListener(this);
		btnExit.addActionListener(this);
		btnSend.addActionListener(this);
		btnSave.addActionListener(this);
		lstUserList.addActionListener(this);
		txtMessage.addActionListener(this);
		PublicChat.addActionListener(this);
		chPrivateChat.addActionListener(this);
		chPublicChat.addActionListener(this);
//		cmbmusic.addActionListener(this);

		// ��������ҳ����Ϣˢ���߳�
		Thread thread = new Thread(this);
		thread.start();

		frmChat.setSize(605, 560);// 600,440
		frmChat.setVisible(true);
		frmChat.setResizable(false);

		// �����ڶ�λ����Ļ����
		scrnsize = toolkit.getScreenSize();
		frmChat.setLocation(scrnsize.width / 2 - frmChat.getWidth() / 2,
				scrnsize.height / 2 - frmChat.getHeight() / 2);

		// Image img=toolkit.getImage("chat3.jpg");
		// frmChat.setIconImage(img);

		// �û�ͷ��
		Icon log;
		log = new ImageIcon("images\\"+"touxiang"+picturec+".jpg");
		lblpicture = new JLabel(log);
		lblpicture.setBounds(525, 50, 40, 40);
		pnlChat.add(lblpicture);

		Icon logo1 = new ImageIcon("images\\chat12.jpg");
		lblChatIag = new JLabel(logo1);
		lblChatIag.setBounds(0, -5, 600, 520);
		pnlChat.add(lblChatIag);

	} // ���췽������

	public void run() {
		int intMessageCounter = 0; // �����¼������(�����������ݺ��������)
		int intUserTotal = 0; // ��½���û���
		boolean isFirstLogin = true; // �ж��Ƿ�յ�½
		boolean isFound; // �ж��Ƿ��ҵ��û�
		Vector user_exit = new Vector();

		try {
			while (true) {
				Socket toServer;
				toServer = new Socket(strServerIp, 8000);
				// ����Ϣ����������
				Message messobj = new Message();
				ObjectOutputStream streamtoserver = new ObjectOutputStream(
						toServer.getOutputStream());
				streamtoserver.writeObject((Message) messobj);
				// �����Է���������Ϣ
				ObjectInputStream streamfromserver = new ObjectInputStream(
						toServer.getInputStream());
				messobj = (Message) streamfromserver.readObject();

				// //////ˢ��������Ϣ�б�//////////
				if (isFirstLogin) // ����յ�½
				{
					intMessageCounter = messobj.chat.size(); // ���θ��û���½ǰ����������
					isFirstLogin = false;
				}

				for (int i = intMessageCounter; i < messobj.chat.size(); i++) {

					if (messobj.chat.elementAt(i).getClass().getName().equals(
							"Chat"))// ������ܵ������������¼

					{
						Chat temp = (Chat) messobj.chat.elementAt(i);
						String temp_message;

						if (temp.chatUser.equals(strLoginName)) {
							if (temp.chatToUser.equals(strLoginName)) {
								temp_message = "ϵͳ��ʾ�����벻Ҫ�������" + "\n" + "\n";
							} else {
								if (!temp.whisper) // �������Ļ�
								{
									temp_message = "���㡿�ԡ�" + temp.chatToUser
											+ "��˵��" + "\n  " + temp.chatMessage
											+ "\n" + "\n";
								} else {
									temp_message = "���㡿���Ķԡ�" + temp.chatToUser
											+ "��˵��" + "\n  " + temp.chatMessage
											+ "\n" + "\n";
								}
							}
						}
						else {
							if (temp.chatToUser.equals(strLoginName)) {
								if (!temp.whisper) // �������Ļ�
								{
									temp_message = "��" + temp.chatUser
											+ "���ԡ��㡿˵��" + "\n  "
											+ temp.chatMessage + "\n" + "\n";
								} else {
									temp_message = "��" + temp.chatUser
											+ "�����Ķԡ��㡿˵��" + "\n  "
											+ temp.chatMessage + "\n" + "\n";
								}
							} else {
								if (!temp.chatUser.equals(temp.chatToUser)) // �Է�û����������
								{
									if (!temp.whisper) // �������Ļ�
									{
										temp_message = "��" + temp.chatUser
												+ "���ԡ�" + temp.chatToUser
												+ "��˵��" + "\n  "
												+ temp.chatMessage + "\n"
												+ "\n";
									} else {
										temp_message = "";
									}
								} else {
									temp_message = "";
								}
							}
						}
						taUserMessage.append(temp_message);

					}
					
					
					
					

					// ////////�ӷ��ļ��Ĵ���////////
					if (messobj.chat.elementAt(i).getClass().getName().equals(
							"SendFile")) 
					{   
						 
						 
						SendFile Femp = (SendFile) messobj.chat.elementAt(i);
						butter = new byte[2500];
						int b;
						if((Femp.chatToUser.equals(strLoginName)||(!Femp.whisper))&&!(Femp.file1.getName().equals("aaa.txt")))
						{
							
							//new SaveFile(Femp.file1);
							try
					     {
					         butter = new byte[2500];                                 //����һ��2500���ֽڴ�С���ֽ�����
					         FileInputStream readfile=new FileInputStream(Femp.file1);//�����ļ�������,������ָ��֮ǰ�Ѿ������õ��ļ�����file
					       if(!Femp.chatUser.equals(strLoginName)) {
					         while((b=readfile.read(butter,0,2500))!=-1)              //ʹ��read()��������ȡ�ļ����ݣ��������ļ�βʱѭ������
					   	    {
					           String str=new String(butter,0,2500);//�����ַ���str,����ת�����洢�ֽ�����������
                               taUserMessage.append("");
                               taUserMessage.append(Femp.chatUser+"���㴫�����ļ�:"+'\n'+"\n"); 
					           taUserMessage.append(str); 
					           taUserMessage.append("\n"+"\n");
					           //���ı����м����ȡ�����ļ�����
					   	    }
					         }
					         readfile.close();   
					              //�ر��ļ�������
                                  // ������ļ������ڴ����ļ�������ɵķ�����Ϣ
					         if(Femp.whisper||((!Femp.whisper)&&Femp.chatUser.equals(strLoginName)))
					         {
					            SendFile Filesend = new SendFile();
								Filesend.file1 = new File("aaa.txt");
								Filesend.chatUser  =  Femp.chatUser;
								Filesend.chatToUser = Femp.chatUser;
								Filesend.whisper = Femp.whisper;
								

								try {
									Socket tooServer = new Socket(strServerIp, 8000);
									ObjectOutputStream outObj = new ObjectOutputStream(tooServer
											.getOutputStream());
									outObj.writeObject(Filesend);
									outObj.close();
									toServer.close();

								} catch (Exception e) {
									System.out.println(e);
								}
					      }
					     }
					      catch(IOException e)
					      {System.out.println("File read error");}
					     
				
						}
						
						
						
						if (Femp.chatToUser.equals(strLoginName)&&Femp.chatUser.equals(strLoginName)&&Femp.file1.getName().equals("aaa.txt"))
						{
							taUserMessage.append("�ļ�������ϣ�" + "\n" + "\n");
							
						}

					}

					// ////////// ����ϵͳ��Ϣ//////////////////
					if (messobj.chat.elementAt(i).getClass().getName().equals(
							"SystenMessage")) {
						SystenMessage Sysme = (SystenMessage) messobj.chat
								.elementAt(i);
						
						taUserMessage.append("  ϵͳ��Ϣ��" + "\n" + Sysme.message
								+ "\n" + "\n");
					}
					intMessageCounter++;

				} // for����

				
				
				// //////ˢ�������û�//////////
				lstUserList.removeAll();
//				cmbUser.removeAllItems();
//				cmbUser.addItem("������");
				for (int i = 0; i < messobj.userOnLine.size(); i++) { // boolean
					// b;
					// int[] c;

					String User = (String) messobj.userOnLine.elementAt(i);
					lstUserList.add(User);
					// for(int j=0;j<cmbUser.getItemCount();j++)
					// {if

					// }
//					cmbUser.addItem(User);
				}
				Integer a = new Integer(messobj.userOnLine.size()); // ��ʾ��������
				lblCount.setText(a.toString());

				// ��ʾ�û����������ҵ���Ϣ
				if (messobj.userOnLine.size() > intUserTotal) {
					String tempstr = messobj.userOnLine.elementAt(
							messobj.userOnLine.size() - 1).toString();
					if (!tempstr.equals(strLoginName)) {
						taUserMessage.append("��" + tempstr + "������" + "\n");
					}
				}

				// ��ʾ�û��뿪�����ҵ���Ϣ
				if (messobj.userOnLine.size() < intUserTotal) {
					for (int b = 0; b < user_exit.size(); b++) {
						isFound = false;
						for (int c = 0; c < messobj.userOnLine.size(); c++) {
							if (user_exit.elementAt(b).equals(
									messobj.userOnLine.elementAt(c))) {
								isFound = true;
								break;
							}
						}
						if (!isFound) // û�з��ָ��û�
						{
							if (!user_exit.elementAt(b).equals(strLoginName)) {
								messobj.userOnLine.remove(user_exit
										.elementAt(b));
								taUserMessage
										.append("��" + user_exit.elementAt(b)
												+ "������" + "\n");
							}
						}
					}
				}
				user_exit = messobj.userOnLine;
				intUserTotal = messobj.userOnLine.size();
				streamtoserver.close();
				streamfromserver.close();
				toServer.close();
				this.thread.sleep(1000);
			}

		} catch (Exception e) {
			JOptionPane jop = new JOptionPane();
			jop.showMessageDialog(null, "�������ӷ�������");
			System.out.println(e);
		}

	} // run()����

	// /////////������ť��Ӧ//////////////
	public void actionPerformed(ActionEvent ae) {
		Object source = (Object) ae.getSource();
		if (source.equals(btnCls)) // ���ֲ�����
		{
			new Music();
			
			
		}
		if (source.equals(mnuiContent))//Ŀ¼
		{
			dailog11.setVisible(true);
		}
		if (source.equals(mnuiAbout))// �����Ҽ��
		{
			dailogs.setVisible(true);
		}

		if (source.equals(mnuiExit)) // �˳��������
		{
			exit();
		}
		if (source.equals(mnuiSave)) // ���������¼
		{
			saveMessage();
		}

		if (source.equals(btnExit)) // �˳��������
		{
			exit();
			
		}
		if (source.equals(btnSend)) // ����������Ϣ
		{
			sendMessage();
		}
		if (source.equals(btnSave)) // ���������¼
		{
			saveMessage();
		}
		if (source.equals(lstUserList)) // ˫���б��
		{
			changeUser();

		}
		if (source.equals(PublicChat)) // Ⱥ��
		{
			 txtTouser.setText("������");
			 btnSend.setEnabled(true);
			 btnSendFile.setEnabled(true);
			  
			}
		if (source.equals(chPrivateChat)) // ˽��
			
		{	if(txtTouser.getText().equals("������"))
			{
			 txtTouser.setText("");
			 btnSend.setEnabled(false);
			 btnSendFile.setEnabled(false);
			} 
			}
		
       if (source.equals(chPublicChat)) // ˽��
			
		{	if(txtTouser.getText().equals("������"))
			{
			 txtTouser.setText("");
			 btnSend.setEnabled(false);
			 btnSendFile.setEnabled(false);
			} 
			}
			
		
		if (source.equals(mnuiCls)) // �����˵���
		{
			clearMessage();
		}

		if (source.equals(btnBrowse)) // �ļ��Ĵ򿪰�ť
		{
			JFileChooserDemo jFramefile = new JFileChooserDemo();
     		file = jFramefile.fileChooser.getSelectedFile();
			txtFile.setText(file.getName());

		}
		if (source.equals(btnSendFile)) // �ļ��ķ��Ͱ�ť
		{
			sendFile();
		}

	} // actionPerformed()����

	// /////////�������ڹر���Ӧ//////////////
	class Windowclose extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			exit();
		}
	}

	// "����"��ť
	public void clearMessage() {
		taUserMessage.setText("");
	}

	// "�˳�"��ť
	public void exit() {
		Exit1 exit = new Exit1();
		exit.exitname = strLoginName;
		// �����˳���Ϣ
		try {
			Socket toServer = new Socket(strServerIp, 8000);
			// �������������Ϣ
			ObjectOutputStream outObj = new ObjectOutputStream(toServer
					.getOutputStream());
			outObj.writeObject(exit);

			outObj.close();
			toServer.close();
			frmChat.dispose();
		} catch (Exception e) {
		}

	} // exit()����

	// "����"��ť
	public void sendMessage() {
		Chat chatobj = new Chat();
		chatobj.chatUser = strLoginName;
		chatobj.chatMessage = txtMessage.getText();
		chatobj.chatToUser = txtTouser.getText();
		chatobj.whisper = chPrivateChat.isSelected() ? true : false;

		// �������������Ϣ
		try {
			Socket toServer = new Socket(strServerIp, 8000);
			ObjectOutputStream outObj = new ObjectOutputStream(toServer
					.getOutputStream());
			outObj.writeObject(chatobj);
			txtMessage.setText(""); // ����ı���
			outObj.close();
			toServer.close();
		} catch (Exception e) {
		}
	} // sendMessage()����

	// "����"��ť
	public void saveMessage() {
		try {
			FileOutputStream fileoutput = new FileOutputStream("message.txt",
					true);
			String temp = taUserMessage.getText();
			System.out.println(temp);
			fileoutput.write(temp.getBytes());
			fileoutput.close();
			// JOptionPane jop = new JOptionPane();
			// jop.showMessageDialog(null,"�����¼�ѱ��棡")��

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	// ����ѡ�û���ӵ�cmbUser��
	public void changeUser() {
		// boolean key = true;
		String selected = lstUserList.getSelectedItem();

		/*
		 * for(int i = 0; i < cmbUser.getItemCount(); i++) {
		 * if(selected.equals(cmbUser.getItemAt(i))) { key = false; break; } }
		 * if(key == true) { cmbUser.insertItemAt(selected,0); }
		 * cmbUser.setSelectedItem(selected);
		 */

		txtTouser.setText(selected);
		chPrivateChat.setSelected(true);
		btnSend.setEnabled(true);
		btnSendFile.setEnabled(true);

	} // changeUser()����

	public void sendFile() {

		if(txtTouser.getText().equals(strLoginName))
		{
			taUserMessage.append("\n"+"�����Լ����Լ������ļ�"+"\n");
		}
		else{
		
		SendFile Filesend = new SendFile();

		Filesend.file1 = file;
		Filesend.chatUser = strLoginName;
		Filesend.chatToUser = txtTouser.getText();
		Filesend.whisper = chPrivateChat.isSelected() ? true : false;

		try {
			Socket toServer = new Socket(strServerIp, 8000);
			ObjectOutputStream outObj = new ObjectOutputStream(toServer
					.getOutputStream());
			outObj.writeObject(Filesend);
			outObj.close();
			toServer.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		}
		taUserMessage.append("���ڷ����ļ���"+file.getName()+"��....." + "\n" + "\n");

	}

	public static void main(String args[]) {
		new ChatRoom("�û�", "127.0.0.1", "1");
	}

}