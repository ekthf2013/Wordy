import javax.swing.*;

import java.util.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

 
public class Third extends JFrame{
    int example[];
    int answer1;
    int answerIndex;
    int cnt = 0;
    private Clip clip;
    String eng;
    
    Vector<Word> v;
    JButton[] answer;
        
    JLabel Q1;
    JLabel CNT;
    JLabel step=new JLabel("3단계");
    JLabel question=new JLabel("주어진 영단어의 뜻을 고르시오");
    JLabel Field=new JLabel("");
    ImageIcon image = new ImageIcon("./Button_image/sound.png");
    JButton sound = new JButton(image); 
    JButton next = new RoundedButton("다음 단계로");

    public Third() {
        Font f = new Font("맑은 고딕",Font.BOLD,30);
        Font f2=new Font("SanSerif",Font.BOLD,30);
        Font f3 = new Font("맑은 고딕",Font.BOLD,14);
        Font f4 = new Font("맑은 고딕",Font.BOLD,17);
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
                       
        Container c = getContentPane();
        c.setLayout(null);
        step.setBounds(20,10,90,30);
        step.setFont(f2);
        c.add(step);
        question.setBounds(10,60,200,50);
        c.add(question);
        
        makeexam();
        makebutton();
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
        
        next.setLocation(350,430);
        next.setSize(100,20);
        next.setFont(f3);
        next.addActionListener(new myactionlistener());
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
              
        //영어 단어
        this.Q1 = new JLabel(eng);
        Q1.setLocation(370,150);
        Q1.setSize(200,50);
        Q1.setFont(f);
        c.add(Q1);
        
        answer[0].setFont(f4);
        answer[1].setFont(f4);
        answer[2].setFont(f4);
        answer[3].setFont(f4);
        
        for(int i = 0; i < 4; i++)
            c.add(answer[i]);
        
		c.setBackground(Color.WHITE);
		
        setSize(800,550);
        setVisible(true);

        next.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			new Fourth();
    			setVisible(false);
    		}
    	});
                
    }
    private int makeExample(int ex[], int answerIndex) {//보기 랜덤
        int n[] = {-1, -1, -1, -1};
        int index;
        for(int i=0; i<n.length; i++) {
            do {
                index = (int)(Math.random()*v.size());	//랜덤 index(단어)
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
        this.answerIndex = (int)(Math.random()*v.size()); 
        this.eng = v.get(answerIndex).getEnglish();
        this.example = new int[4];
        this.answer1 = makeExample(example,answerIndex);
        example[answer1] = answerIndex;  
    }
    
    private void makebutton() {
        this.answer= new JButton[4];
        answer[0] = new RoundedButton(v.get(example[0]).getKorean());
        answer[1] = new RoundedButton(v.get(example[1]).getKorean());
        answer[2] = new RoundedButton(v.get(example[2]).getKorean());
        answer[3] = new RoundedButton(v.get(example[3]).getKorean());
        
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
    class myactionlistener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
        JButton b=(JButton)e.getSource();
        if(b.getText().equals(v.get(example[answer1]).getKorean())) {
           Field.setText(null);
           playSound("./sound/correct.wav", false);
           makeexam();
           Q1.setText(eng);
           for(int j = 0; j<4; j++) {
                answer[j].setText(v.get(example[j]).getKorean());}
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
         new Third();
        
    }
 
}



