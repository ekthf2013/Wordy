import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

class Sub extends JFrame{
	JTable table;
	JScrollPane sPane;
	Container c;
	Vector<String> columnName = null;   // String을 원소로 갖는 갖는 리스트
	Vector<Vector<String>> data = null; // String 여러개를 갖는 백터들을 원소로 갖는 리스트
	int cnt = 1;
	JButton home = new RoundedButton("홈으로");
	Sub(){
		setTitle("영어 단어 암기 프로그램");
        Font f = new Font("맑은 고딕",Font.BOLD,15);
		columnName = new Vector<String>();
		data = new Vector<Vector<String>>();
		getFile(); // 파일에서 한줄씩 읽어와 columnName과 data 객체를 완성시킴
		compose(); // 위에서 만들어진 JTable을 생성하고 Scrollbar panel에 올리고, 작업 영역에 올리는 메소드
		setSize(800,550);
		setVisible(true);
		home.setFont(f);
		home.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			new Start_menu();
    			setVisible(false);
    		}
    	});
	}
	
	private void compose() { // JTable을 생성하고 Scrollbar panel에 올리고, 작업 영역에 올리는 메소드
		table = new JTable(data, columnName);
		table.setSize(30,30);
		//table.setBackground(Color.yellow);
		table.setRowHeight(45);
		sPane = new JScrollPane(table);
		c = getContentPane();
		c.add(sPane);	
		c.add(home, BorderLayout.SOUTH);

	}

	public void getFile() {
		
		File file = new File("./Note/note.txt");
		FileReader fr;
		try {
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			String line;
			
			while((line = br.readLine()) != null) { // 1줄씩 읽어들임
				tableMake(line);
			}
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void tableMake(String line) {
		String[] tokens = line.split("/");
		if (cnt++ == 1) { // 파일내용중 첫번째 줄은 column 명으로 사용
			for(int i = 0; i < tokens.length; i++) {
				columnName.add(tokens[i]);
			}
		}else {
			Vector<String> one = new Vector<String>();
			for(int i = 0; i < tokens.length; i++) {
				one.add(tokens[i]);
			}
			data.add(one);
		}
	}
}
public class Note {
	public static void main(String[] args) {
		new Sub();
	}
}


