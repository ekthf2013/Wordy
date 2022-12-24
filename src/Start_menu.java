import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.awt.*;

public class Start_menu extends JFrame{
    private Clip clip;
    
    JPanel main_panel;
    
	JLabel title = new JLabel();
	JButton ok = new RoundedButton("시작");
	JButton memo = new RoundedButton("모든 단어 확인");
	JButton exit = new RoundedButton("종료");
	
	ImageIcon icon = new ImageIcon("./Button_image/logo.png");
	
	JLabel logo = new JLabel(icon);
	public Start_menu() {
        Font f = new Font("맑은 고딕",Font.BOLD,20);
    	playSound("./sound/backgroundmusic.wav", true);
		setTitle("영어 단어 암기 프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		main_panel = new JPanel();
		main_panel.setBackground(Color.WHITE);
		
		main_panel.setLayout(null);
		logo.setIcon(icon);
		logo.setLocation(150, 80);
		logo.setSize(500,350);
		main_panel.add(logo);
		
		ok.setLocation(200, 350);
		ok.setSize(100,30);
		ok.setFont(f);
		main_panel.add(ok);
		ok.addActionListener(event ->{
			playSound("./sound/pop.wav", false);
		});
		
		memo.setLocation(305, 350);
		memo.setSize(150, 30);
		memo.setFont(f);
		main_panel.add(memo);
		memo.addActionListener(event ->{
			playSound("./sound/pop.wav", false);
		});
		
		exit.setLocation(460, 350);
		exit.setSize(100,30);
		exit.setFont(f);
		main_panel.add(exit);
		
		main_panel.setSize(800,550);
		main_panel.setVisible(true);
		main_panel.setFocusable(true);
		main_panel.requestFocus();
		
		setSize(800,550);		
		setVisible(true);
		//c.setBackground(Color.white);
		c.add(main_panel);
		ok.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	             new First();
	             setVisible(false);
	          }
	       });
		memo.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	             new Sub();
	             setVisible(false);
	          }
	       });
		exit.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
		             System.exit(0);
		          }
		       });
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	
    public void playSound(String pathName, boolean isloop) {
    	try {
    		clip = AudioSystem.getClip();
    		File audioFile = new File(pathName);
    		AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
    		clip.open(audioStream);
    		clip.start();
    		if(isloop)
    			clip.loop(Clip.LOOP_CONTINUOUSLY); //무한반복
    	} catch(LineUnavailableException e) {
    		e.printStackTrace();
    	}
    	catch(UnsupportedAudioFileException e) {
    		e.printStackTrace();
    	}
    	catch(IOException e) {
    		e.printStackTrace();
    	}

    }
	public static void main(String[] args) {
		new Start_menu();
	}

}






