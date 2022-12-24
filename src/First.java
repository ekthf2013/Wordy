
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.font.ImageGraphicAttribute;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.lang.Thread;

public class First extends JFrame{
    private Clip clip;
    Container c = getContentPane();
    
    String eng;
    String kor;

	JProgressBar jb;
	JPanel bar;
    JLabel Img;
    JLabel E;
    JLabel K;
    JLabel step=new JLabel("1단계");
    JLabel question=new JLabel("단어를 학습하시오.");
    JButton btn = new RoundedButton("다음");
    JButton next = new RoundedButton("다음 단계로");
	
    ImageIcon bell = new ImageIcon("./images/bell.png");
    ImageIcon cat = new ImageIcon("./images/cat.png");
    ImageIcon dog = new ImageIcon("./images/dog.png");
    ImageIcon hat = new ImageIcon("./images/hat.png");
    ImageIcon key = new ImageIcon("./images/key.png");
    ImageIcon map = new ImageIcon("./images/map.png");
    ImageIcon pig = new ImageIcon("./images/pig.png");
    ImageIcon rabbit = new ImageIcon("./images/rabbit.png");
    ImageIcon sun = new ImageIcon("./images/sun.png");
    ImageIcon water = new ImageIcon("./images/water.png");
	
    public First() {
		setTitle("영어 단어 암기 프로그램");
        Font f = new Font("맑은 고딕",Font.BOLD,30);
        Font f2 = new Font("SanSerif",Font.BOLD,30);
        Font f3 = new Font("맑은 고딕",Font.BOLD,14);
		bar = new JPanel();
        eng = "bell";
        kor = "종";
        
        c.setLayout(null);
        step.setBounds(20,10,90,30);
        step.setFont(f2);
        c.add(step);
        question.setBounds(10,50,200,50);
        c.add(question);
        
        jb = new JProgressBar(0 ,10);
		jb.setValue(1);
		jb.setStringPainted(true);
		jb.setBorderPainted(false);
		bar.setLocation(220, 30);
		bar.add(jb);
        
        this.E = new JLabel(eng);
        E.setLocation(300, 300);
        E.setSize(100, 50);
        E.setFont(f2);
        c.add(E);
        
        this.K = new JLabel(kor);
        K.setLocation(450, 300);
        K.setSize(100,50);
        K.setFont(f);
        c.add(K);
        
        this.Img = new JLabel(); 
        Img.setIcon(bell);
        Img.setLocation(300,80);
        Img.setSize(200,200);
        c.add(Img);
       
        playSound("./sound/bell.wav", false);

        //다음 버튼
        btn.setLocation(350, 380);
        btn.setSize(100,20);
        btn.setFont(f3);
        c.add(btn);
        //다음 단계 버튼
        next.setLocation(350,430);
        next.setSize(100,20);
        next.setFont(f3);
        c.add(next);
        bar.setSize(800,550);
        bar.setVisible(true);
        c.add(bar);
		bar.setBackground(Color.WHITE);
		c.setBackground(Color.WHITE);
        
        setSize(800,550);
        setVisible(true);
        
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               new Second();
               setVisible(false);
            }
         });
        btn.addActionListener(event -> {
			c.removeAll();
        	c.revalidate();
        	c.repaint();
			print_panel();
        	});
    }
    public void print_panel() {
        c.add(step);
        c.add(question);
        c.add(btn);
        c.add(next);
        if(eng == "bell") {
        	eng = "cat";
        	kor = "고양이";
            Img.setIcon(cat);
    		jb.setValue(2);
            playSound("./sound/cat.wav", false);
         }
         else if(eng =="cat") {
        	 eng = "dog";
        	 kor = "개";
             Img.setIcon(dog);
     		jb.setValue(3);
             playSound("./sound/dog.wav", false);
         }
         else if(eng =="dog") {
        	 eng = "hat";
        	 kor = "모자";
             Img.setIcon(hat);
      		jb.setValue(4);
             playSound("./sound/hat.wav", false);
         }
         else if(eng =="hat") {
        	 eng = "key";
        	 kor = "열쇠";
             Img.setIcon(key);
      		jb.setValue(5);
             playSound("./sound/key.wav", false);
         }
         else if(eng =="key") {
        	 eng = "map";
        	 kor = "지도";
             Img.setIcon(map);
      		jb.setValue(6);
             playSound("./sound/map.wav", false);
         }
         else if(eng =="map") {
        	 eng = "pig";
        	 kor = "돼지";
             Img.setIcon(pig);
      		jb.setValue(7);
             playSound("./sound/pig.wav", false);
         }
         else if(eng =="pig") {
        	 eng = "rabbit";
        	 kor = "토끼";
             Img.setIcon(rabbit);
      		jb.setValue(8);
             playSound("./sound/rabbit.wav", false);
         }
         else if(eng =="rabbit") {
        	 eng = "sun";
        	 kor = "태양";
             Img.setIcon(sun);
      		jb.setValue(9);
             playSound("./sound/sun.wav", false);
         }
         else if(eng =="sun") {
        	 eng = "water";
        	 kor = "물";
             Img.setIcon(water);
      		jb.setValue(10);
             playSound("./sound/water.wav", false);
        	 btn.setText("홈으로");
             btn.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent e) {
                    new Start_menu();
                    setVisible(false);
                 }
              });
         }
    	E.setText(eng);
    	K.setText(kor);
    	c.add(E);
    	c.add(K);
    	c.add(Img);
        c.add(bar);
    }
  //소리출력하는 함수
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
       new First();    
    }
 
}


