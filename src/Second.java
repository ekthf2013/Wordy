import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.awt.*;

public class Second extends JFrame{
    int example[];
    int answer1;
    int answerIndex;
    int cnt = 0;
    private Clip clip;
    Container c = getContentPane();
    
    ImageIcon image = new ImageIcon("./Button_image/sound.png");
    
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
    
    JButton sound = new JButton(image); 
    JButton next = new RoundedButton("다음 단계로");
    
    String eng;
    
    Vector<Word> v;
    JButton[] answer;
        
    JLabel Q1;
    JLabel CNT;
    JLabel step=new JLabel("2단계");
    JLabel question=new JLabel("그림에 맞는 영단어를 고르시오");
    JLabel Field=new JLabel("");
    
    public Second() {
		setTitle("영어 단어 암기 프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Font f = new Font("맑은 고딕",Font.PLAIN,30);
        Font f2=new Font("SanSerif",Font.BOLD,30);
        Font f3 = new Font("맑은 고딕",Font.BOLD,15);
        Font f4 = new Font("맑은 고딕",Font.BOLD,14);
        Font f5=new Font("SanSerif",Font.BOLD,17);
        v = new Vector<Word>();
        v.add(new Word("bell", "종"));
        v.add(new Word("cat", "고양이"));
        v.add(new Word("dog", "개"));
        v.add(new Word("hat", "모자"));
        v.add(new Word("key", "열쇠"));
        v.add(new Word("map", "지도"));
        v.add(new Word("pig", "돼지"));
        v.add(new Word("rabbit", "토끼"));
        v.add(new Word("sun", "태양"));
        v.add(new Word("water", "물"));
                       
        c.setLayout(null);
        step.setBounds(20,10,90,30);
        step.setFont(f2);
        c.add(step);
        question.setBounds(10,60,200,50);
        c.add(question);
        makeexam();
        makebutton();
        print_panel();
        for(int i = 0; i < 4; i++)
            answer[i].addActionListener(new myactionlistener());
        //버튼 위치
        answer[0].setLocation(280,300);
        answer[0].setSize(100,30);
        answer[1].setLocation(420,300);
        answer[1].setSize(100,30);
        answer[2].setLocation(280,350);
        answer[2].setSize(100,30);
        answer[3].setLocation(420,350);
        answer[3].setSize(100,30);
        
        //다음단계 버튼
        next.setLocation(350,430);
        next.setSize(100,20);
        next.addActionListener(new myactionlistener());
        next.setFont(f4);
        c.add(next);
        
        //소리버튼
        sound.setLocation(600,30);
        sound.setSize(50,50);
        sound.addActionListener(new myactionlistener());
		sound.setBorderPainted(false);
        c.add(sound);
        
        Field.setBounds(360,380,200,50);
        Field.setFont(f3);
        Field.setForeground(Color.red);
        c.add(Field);
        
        answer[0].setFont(f5);
        answer[1].setFont(f5);
        answer[2].setFont(f5);
        answer[3].setFont(f5);
        for(int i = 0; i < 4; i++)
            c.add(answer[i]);        
        
		c.setBackground(Color.WHITE);
		
        setSize(800,550);
        setVisible(true);   
        
        next.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			new Third();
    			setVisible(false);
    		}
    	});
    }
    private int makeExample(int ex[], int answerIndex) {//보기 랜덤
        int n[] = {-1, -1, -1, -1}; // 
        int index;
        for(int i=0; i<n.length; i++) {
            do {
                index = (int)(Math.random()*v.size());   //랜덤 index(단어)
            } while(index == answerIndex || exists(n, index)); 
            n[i] = index;
        }
        for(int i=0; i<n.length; i++) ex[i] = n[i];
        return (int)(Math.random()*n.length); 
    }
    private boolean exists(int n[], int index) {
        for(int i=0; i<n.length; i++) {
            if(n[i] == index)
                return true;
        }
        return false;
    }
    private void makeexam() {
        this.Q1 = new JLabel(eng);
        this.answerIndex = (int)(Math.random()*v.size()); 
        this.eng = v.get(answerIndex).getEnglish();
        this.example = new int[4];
        this.answer1 = makeExample(example,answerIndex);
        example[answer1] = answerIndex; 
        print_img();
    }
    
    private void makebutton() {
        this.answer= new JButton[4];

        answer[0] = new RoundedButton(v.get(example[0]).getEnglish());
        answer[1] = new RoundedButton(v.get(example[1]).getEnglish());
        answer[2] = new RoundedButton(v.get(example[2]).getEnglish());
        answer[3] = new RoundedButton(v.get(example[3]).getEnglish());
    }
    
    public void print_img() {
        if(eng == "bell") {
        	c.removeAll();
        	c.revalidate();
        	c.repaint();
            Q1.setIcon(bell);
         }
         else if(eng =="cat") {
         	c.removeAll();
         	c.revalidate();
        	c.repaint();
            Q1.setIcon(cat);
         }
         else if(eng =="dog") {
          	c.removeAll();
          	c.revalidate();
         	c.repaint();
            Q1.setIcon(dog);
         }
         else if(eng =="hat") {
          	c.removeAll();
          	c.revalidate();
         	c.repaint();
            Q1.setIcon(hat);
         }
         else if(eng =="key") {
          	c.removeAll();
          	c.revalidate();
         	c.repaint();
            Q1.setIcon(key);
         }
         else if(eng =="map") {
          	c.removeAll();
          	c.revalidate();
         	c.repaint();
            Q1.setIcon(map);
         }
         else if(eng =="pig") {
          	c.removeAll();
          	c.revalidate();
         	c.repaint();
            Q1.setIcon(pig);
         }
         else if(eng =="rabbit") {
          	c.removeAll();
          	c.revalidate();
         	c.repaint();
            Q1.setIcon(rabbit);
         }
         else if(eng =="sun") {
          	c.removeAll();
          	c.revalidate();
         	c.repaint();
            Q1.setIcon(sun);
         }
         else if(eng =="water") {
          	c.removeAll();
          	c.revalidate();
         	c.repaint();
            Q1.setIcon(water);
         }
        Q1.setLocation(300,80);
        Q1.setSize(200,200);
        c.add(Q1);
    }
    
    public void print_panel() {
        c.add(step);
        c.add(question);
        c.add(sound);
        c.add(next);
        c.add(Field);
        Q1.getText();
        for(int i = 0; i < 4; i++)
            c.add(answer[i]); 
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
   
    class myactionlistener implements ActionListener{
       public void actionPerformed(ActionEvent e) {
       JButton b=(JButton)e.getSource();
       if(b.getText().equals(v.get(example[answer1]).getEnglish())) {
          Field.setText(null);
          playSound("./sound/correct.wav", false);
          makeexam();
          print_panel();
          Q1.setText(eng);
          for(int j = 0; j<4; j++) {
               answer[j].setText(v.get(example[j]).getEnglish());}
          }
       else if (b == sound || b == next) {
          Field.setText(null);
       }
       else {
    	   playSound("./sound/wrong.wav", false);
    	   Field.setText("틀렸습니다.");
       }
       
       //소리버튼 눌렀을 때 발음 출력
        JButton s = (JButton)e.getSource();
        if(s == sound) {
           if(Q1.getText() == "rabbit") 
               playSound("./sound/rabbit.wav", false);
           if(Q1.getText() == "bell") 
               playSound("./sound/bell.wav", false);
           if(Q1.getText() == "cat") 
               playSound("./sound/cat.wav", false);
           if(Q1.getText() == "dog") 
               playSound("./sound/dog.wav", false);
           if(Q1.getText() == "hat") 
               playSound("./sound/hat.wav", false);
           if(Q1.getText() == "key") 
               playSound("./sound/key.wav", false);
           if(Q1.getText() == "map") 
               playSound("./sound/map.wav", false);
           if(Q1.getText() == "pig") 
               playSound("./sound/pig.wav", false);
           if(Q1.getText() == "sun") 
               playSound("./sound/sun.wav", false);
           if(Q1.getText() == "water") 
               playSound("./sound/water.wav", false);
           }
       }
    }
    public static void main(String[] args) {
         new Second();    
    }
}



