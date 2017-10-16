import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.sound.midi.*;



import java.io.File;
public class MultiMidiDemo extends JFrame 
{
	private String[] midiFile={"betsy.mid","camptown.mid"};
	private MultiMidiPlayer midiPanel;

    public MultiMidiDemo() 
    {
        super("��������ֲ�����");
        setSize(300, 500);
       
        //��ȡ���ݴ���
        Container container = getContentPane();
        
        midiPanel = new MultiMidiPlayer(midiFile);
        container.add(midiPanel);
        
        setVisible(true);
        this.addWindowListener(new Windowcloseee());
//   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
      }
    
    
   private class Windowcloseee extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			exit11() ;
		}
	}
   
   
	public void exit11() 
	{   
		this.dispose();
		
	}
	
	
	
    public static void main(String[] args)
    {
        MultiMidiDemo application = new MultiMidiDemo();
    }
}




class MultiMidiPlayer extends JPanel implements Runnable, ActionListener 
{
	Thread runner;
	private JPanel buttonPanel;              //��Ű�ť����
    private JButton play, stop;              //����, ������ť 
    private JLabel label,labelMusic;                    //��ʾ��Ϣ
    private Sequence currentSound;           //����
    Sequencer player;                //������
    private String[] songFile;               //�����ļ�
    private int songToPlay;
    private JTextField musicList;
    java.awt.List MusicList;

    public MultiMidiPlayer(String[] songs) {
    	super();
    	this.setLayout(null);
        songFile = songs;
        songToPlay=0;
        label = new JLabel("���ڲ��ţ�");
          
        Icon iconmusic = new ImageIcon("images\\music.jpg");
		labelMusic = new JLabel(iconmusic);
		labelMusic.setBounds(0, 0, 300, 500);
		
		musicList=new JTextField();
		MusicList = new java.awt.List();
		MusicList.add(songs[0]);
		MusicList.add(songs[1]);
//		MusicList.add("ҹ���˴���");
//		MusicList.add("�������");
		MusicList.setBackground(Color.GREEN);
		MusicList.setBounds(20, 70, 200, 280);
        
        
        //������ť
        play = new JButton("Play");
        stop = new JButton("Stop");
        stop.setEnabled(false);
        
        play.setBounds(50, 400, 60, 30);
		stop.setBounds(150, 400, 60, 30);
		label.setBounds(20, 360, 80, 30);
		musicList.setBounds(100, 360, 180, 30);
        //ע�������
        play.addActionListener(this);
        stop.addActionListener(this);
        MusicList.addActionListener(this);
        
        
        
        buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        
        
        buttonPanel.add(play);
        buttonPanel.add(stop);
        buttonPanel.add(label);
        buttonPanel.add(musicList);
        buttonPanel.add(labelMusic);
        buttonPanel.add(MusicList);
        
        
        
        //��buttonPanel���������ݴ���
        buttonPanel.setBounds(0, 0, 300, 500);
        this.add(buttonPanel);
        
        
        
        if (songFile.length == 0) //��������б���Ϊ�գ����Ű�ť����Ϊ���ܹ���
        {
            play.setEnabled(false);
            runner = null;
            
        }
     }
    
    
    
    
     //����ť�¼�
     public void actionPerformed(ActionEvent event) 
     {
    	 if (event.getSource() == play)
    		 play();
         if(event.getSource()==stop)
             stop();
         else
        	 play();
         if(event.getSource()==MusicList)
         
        	 play();
     }
     
     
     
     //��������
     public void play() 
     {
         if (runner == null) 
         {
            runner = new Thread(this);
            runner.start();
            play.setEnabled(false);
            stop.setEnabled(true);
         }
     }
     
     
     
     //ֹͣ����
     public void stop() 
     {
        if (runner != null) 
        {
            runner = null;
            stop.setEnabled(false);
            play.setEnabled(true);
        }
     }


     public void run() 
     {
        try 
        {
            player = MidiSystem.getSequencer();      //��ȡ������
        } 
        catch (Exception exception) 
        {
            musicList.setText(exception.toString());
        }
        
        
        
        while (Thread.currentThread() == runner) 
        {
            for (songToPlay = 0; songToPlay < songFile.length; songToPlay++) 
            {
                if (songFile[songToPlay] != null) 
                {
                    try 
                    {
                        File song = new File(songFile[songToPlay]);
                        //��ȡ����
                        currentSound = MidiSystem.getSequence(song);
                        player.open();
                        //����������������
                        player.setSequence(currentSound);
                        //��ʼ����
                        player.start();
                        musicList.setText( song.getName());
                        while (player.isRunning() && runner != null) 
                        {
                            try 
                            {
                                Thread.sleep(100);
                            } 
                            catch (InterruptedException exception){}
                        }
                        musicList.setText("");
                        player.close();
                    } 
                    catch (Exception exception) 
                    {
                        label.setText(exception.toString());
                        break;
                    }
                }
            }
        }
    }
}
