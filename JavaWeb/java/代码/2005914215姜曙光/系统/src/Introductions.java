
import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.*;

public class Introductions extends JDialog               //������ϢDialog����
{
	//����������������
    JPanel panel1 = new JPanel();
    
    //�������ֱ�ǩ����
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JLabel jLabel3 = new JLabel();
    JLabel jLabel4 = new JLabel();
    JLabel jLabel5 = new JLabel();
    JLabel jLabel6 = new JLabel();
    JLabel jLabel7 = new JLabel();
    
    //���ò��ֹ�����
    GridLayout gridLayout1 = new GridLayout();

    //���캯������
    public Introductions()                                             
    {
    	//���ø��๹�췽��
         super(new Frame(), "����������Ҽ��", false);
         setSize(400,400);
         
        //���ð�����Ϣ�����е���ʾ��Ϣ

        panel1.setLayout(gridLayout1);
        gridLayout1.setColumns(1);
        gridLayout1.setRows(7);
        jLabel1.setText("�����������1.0�棬лл֧��");
        jLabel2.setText("��������û��б���ʾ�������ҵ������û��б�");
        jLabel3.setText("�����û��б��±ߵı�ǩ������ʾ��������������ķ������û���");
        jLabel4.setText("�ұߵĴ���ı�����ʾ�������¼���û��������⿴��������Ա��");
        jLabel5.setText("���Լ���������Ϣ��");
        jLabel6.setText("�±ߵĵ����ı��������û�Ԥ���͵����ݣ�");
        jLabel7.setText("�����Ҹ������ļ����͵Ĺ��ܡ�");
       

        panel1.add(jLabel1, null);
        panel1.add(jLabel2);
        panel1.add(jLabel3);
        panel1.add(jLabel4);
        panel1.add(jLabel5);
        panel1.add(jLabel6);
        panel1.add(jLabel7);
        getContentPane().add(panel1);

    }
}

