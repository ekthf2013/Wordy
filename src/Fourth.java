
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
import java.time.LocalDateTime;

public class Fourth extends JFrame{
    int example[];
    int answer1;
    int answerIndex;
    int cnt = 0;
    String kor;
    Word eng;
    Vector<Word> v;
    private Clip clip;   
    JLabel Q1;
    JLabel CNT;
    JTextField engField=new JTextField(20);
    JButton chkBtn=new RoundedButton("확인");
    JButton homeBtn=new RoundedButton("홈으로");
    JLabel Field=new JLabel("");
    JLabel step=new JLabel("4단계");
    JLabel question=new JLabel("뜻에 맞는 영단어를 적으시오");
    public Fourth() {
		setTitle("영어 단어 암기 프로그램");
        Font f = new Font("맑은 고딕",Font.BOLD,30);
        Font f2 = new Font("SanSerif",Font.BOLD,30);
        Font f3 = new Font("맑은 고딕",Font.BOLD,15);
        Font f4 = new Font("맑은 고딕",Font.PLAIN,20);
        
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
        
        chkBtn.setSize(80,30);
        chkBtn.setLocation(360,400);
        chkBtn.setFont(f3);
        chkBtn.addActionListener(new ButtonListener());
        c.add(chkBtn);
        
        homeBtn.setSize(150,30);
        homeBtn.setLocation(600,450);
        homeBtn.setFont(f3);
        homeBtn.addActionListener(new ButtonListener());
        c.add(homeBtn);
        
        question.setBounds(10,50,200,50);
        c.add(question);//문제
        engField.setBounds(300,280,200,50);
        engField.setFont(f4);
        engField.setHorizontalAlignment(JTextField.CENTER);
        c.add(engField);//답 입력하는 곳
        
        Field.setBounds(360,340,200,50);
        Field.setFont(f3);
        Field.setForeground(Color.red);
        c.add(Field);
        makeexam();
        
        //영어 뜻
        this.Q1 = new JLabel(kor);
        Q1.setLocation(370,170);
        Q1.setSize(200,50);
        Q1.setFont(f);
        c.add(Q1);
    
        JLabel timerLabel = new JLabel();
      	timerLabel.setBounds(700,10,100,100);
    	timerLabel.setFont(new Font("Gothic", Font.ITALIC, 30));
    	c.add(timerLabel);
    	TimerThread th = new TimerThread(timerLabel);
    	th.start();
    	
		c.setBackground(Color.WHITE);
		
        setSize(800,550);//전체 사이즈
        setVisible(true); 
        
        homeBtn.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			new Start_menu();
    			setVisible(false);
    		}
    	});
    }


    private void makeexam() {
        this.answerIndex = (int)(Math.random()*v.size()); //랜덤 번호
        this.kor = v.get(answerIndex).getKorean();//랜덤번호 인덱스에 있는 뜻 받아옴
        this.eng=v.get(answerIndex);
    }
   
    class ButtonListener implements ActionListener{
    	public void actionPerformed(ActionEvent e) {
    	//JButton b=(JButton)e.getSource();	
    	if(e.getSource()==chkBtn) {//확인 버튼 눌렀을 때
    		String temp=engField.getText();//
    		temp=temp.trim();//trim()은 공백제거
    		if(eng.getEnglish().equals(temp)&&engField.getText()!=null) {
    			//입력한 문자열이 null이 아니고 같은 문자열일 때
    			Field.setText(null);
    			playSound("./sound/correct.wav", false);
    			engField.setText("정답");
    			engField.setText(null);//입력부분 null
        		makeexam();//랜덤으로 문제 결정하는 함수 불러옴
        		Q1.setText(kor);//Q1부분에 뜻 출력
    		}
    		else {
    			playSound("./sound/wrong.wav", false);
    			Field.setText("틀렸습니다.");   			
    			
    		}
    	}
    	}
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
         new Fourth();
        
    }
 
}



