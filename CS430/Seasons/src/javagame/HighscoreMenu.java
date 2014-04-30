package javagame;

import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Graphics; 
import java.awt.GridLayout; 
import java.awt.Image; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import javax.swing.JButton; 
import javax.swing.JFrame; 
import javax.swing.JTextArea;

public class HighscoreMenu extends JFrame implements ActionListener{       
  Image dbImage; 
  Graphics dbg; 
  static boolean gameStart = true; 
  JFrame frame = new JFrame(); 
  JButton[] layoutButtons = new JButton[10]; 
  JButton clicked = null;
  JButton back;
  int screenWidth = 500; 
  int screenHeight = 500; 
  int buttonHeight = 125;
  int rows = 1; 
  int columns = 3;  
  static String [] highscoreArray = new String [10];
  static int highScoreValue;

 JTextArea label1 = new JTextArea("test");  
  Dimension screenSize = new Dimension (screenWidth, screenHeight); 
    
  public HighscoreMenu(){ 
      frame.setTitle("Seasons"); 
      frame.setLayout(new GridLayout (2, 1, 0, 0)); 
      frame.setSize(screenSize); 
      frame.setResizable(false); 
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
      frame.setVisible(true); 
      frame.setBackground(Color.WHITE);  
      back = new JButton("Back");
      readFile(System.getProperty("user.dir") + "/highscore.txt");
      label1.setText("HighScores:" + "\n1: " + highscoreArray[0] + "\n2: " + highscoreArray[1] + "\n3: " + highscoreArray[2] + "\n4: " + highscoreArray[3] + "\n5: " + highscoreArray[4] + "\n6: " + highscoreArray[5] + "\n7: " + highscoreArray[6] + "\n8: " + highscoreArray[7] + "\n9: " + highscoreArray[8] + "\n10: " + highscoreArray[9]);
      label1.setBackground(Color.LIGHT_GRAY);
      frame.add(label1);
      frame.add(back); 
      back.setEnabled(true);
      back.addActionListener(this);
 
} 
  

    
  public static void start() { 
  new HighscoreMenu(); 
        
  }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object src = arg0.getSource();
		if(src == back){
			new MainMenu();
			frame.dispose();		
		} 
	}
	
	public static void writeFile() {
		System.out.println("Writing...");
		int highScore = getHighScore();
		int[] intArray = new int[10];
		for (int i = 0; i < 10; i++) {
			highscoreArray[i].trim();
			System.out.println(highscoreArray[i]);
			intArray[i] = Integer.parseInt(highscoreArray[i]);
		}
		Arrays.sort(intArray);
		if (intArray[0] < highScore) {
			intArray[0] = highScore;
		}
		Arrays.sort(intArray);
		try {
			PrintWriter writer = new PrintWriter(System.getProperty("user.dir") + "/highscore.txt", "UTF-8");
			for(int i = 9; i >= 0; i--) {
				writer.println(intArray[i]);
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String readFile(String fileName) {
		
		for(int i = 0; i < 10; i++) {
			highscoreArray[i] = "0";
		}
		
		String line = null;
		int i = 0; 
		try {
		       FileReader fileReader = new FileReader(fileName);
		       BufferedReader bufferedReader = new BufferedReader(fileReader);

		       while((line = bufferedReader.readLine()) != null) {
		           
		               highscoreArray[i] = line;
		               i++;
		       }
		       bufferedReader.close(); 
		}
		   catch(FileNotFoundException ex) {
		       System.out.println(
		           "Unable to open file '" + fileName + "'");              
		   }
		   catch(IOException ex1) {
		       System.out.println("Error reading file '" + fileName + "'");                    
		   }
		return line;

		
	}  
    
	public static void setHighScore(int number){ 
        highScoreValue = number; 
    } 
    
    public static int getHighScore(){ 
        return highScoreValue; 
    }
} 