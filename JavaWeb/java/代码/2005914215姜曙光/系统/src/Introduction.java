import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.*;

public class Introduction extends JDialog               //������ϢDialog����
{
	//����������������
    JPanel panel1 = new JPanel();
    
    //�������ֱ�ǩ����
    JLabel jLabel3 = new JLabel();
    JLabel jLabel4 = new JLabel();
    JLabel jLabel5 = new JLabel();
    JLabel jLabel6 = new JLabel();
    JLabel jLabel7 = new JLabel();
    
    //���ò��ֹ�����
    GridLayout gridLayout1 = new GridLayout();

    //���캯������
    public Introduction()                                             
    {
    	//���ø��๹�췽��
         super(new Frame(), "�����������Ŀ¼", false);
         setSize(400,400);
         
        //���ð�����Ϣ�����е���ʾ��Ϣ

        panel1.setLayout(gridLayout1);
        gridLayout1.setColumns(1);
        gridLayout1.setRows(7);
        jLabel3.setText("1. ����");
        jLabel4.setText("2. ˽�� ");
        jLabel5.setText("3. Ⱥ��");
        jLabel6.setText("4. �����ļ�");
        jLabel7.setText("6. ��������");
        
       

        panel1.add(jLabel3);
        panel1.add(jLabel4);
        panel1.add(jLabel5);
        panel1.add(jLabel6);
        panel1.add(jLabel7);
        getContentPane().add(panel1);

    }
}

