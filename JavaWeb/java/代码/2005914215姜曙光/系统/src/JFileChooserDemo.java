
import javax.swing.*;
import java.awt.*;



public class JFileChooserDemo extends JFrame 
{

	JFileChooser fileChooser;
	
    
    public JFileChooserDemo()
    { 
    	
        
        try
		{    //�������
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e){}
		
		
        
        
        fileChooser = new JFileChooser("e:");
        fileChooser.setApproveButtonText("ȷ��");
        fileChooser.setDialogTitle("���ļ�");
      
        fileChooser.showOpenDialog(this);
      //  fileChooser.setfile
//         d = new FileDialog(this,
//                "What file do you want to save?", FileDialog.SAVE);
//              d.setFile("fdjfi");
//              //d.setDirectory(".");
//              d.show();
        
      }
    
    
	
  
    public static void main(String[] args)
    {
  	   new JFileChooserDemo();
  	   
    }
  
  
	
	    
	   
  	    
  	
}







