package javagame;

import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Graphics; 
import java.awt.GridLayout; 
import java.awt.Image; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton; 
import javax.swing.JFrame; 
  
  
public class LevelSelectMenu extends JFrame implements ActionListener{       
    Image dbImage; 
    Graphics dbg; 
    static boolean gameStart = true; 
    JFrame frame = new JFrame(); 
    JButton[] layoutButtons = new JButton[10]; 
    JButton clicked = null;
    JButton level1, level2, level3, level4, level5, back;
    int screenWidth = 500; 
    int screenHeight = 500; 
    int rows = 1; 
    int columns = 3; 
           
    Dimension screenSize = new Dimension (screenWidth, screenHeight); 
      
    public LevelSelectMenu(){ 
        frame.setTitle("Seasons"); 
        frame.setLayout(new GridLayout (3, 2, 90, 45)); 
        frame.setSize(screenSize); 
        frame.setResizable(false); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setVisible(true); 
        frame.setBackground(Color.WHITE); 
        level1 = new JButton("Level 1"); 
        level2 = new JButton("Level 2"); 
        level3 = new JButton("Level 3"); 
        level4 = new JButton("Level 4"); 
        level5 = new JButton("Level 5"); 
        back = new JButton("Back");

        layoutButtons[0] = level1; 
        layoutButtons[1] = level2; 
        layoutButtons[2] = level3; 
        layoutButtons[3] = level4;
        layoutButtons[4] = level5;
        layoutButtons[5] = back;
        
        for (int i = 0; i < 6; i++){ 
            frame.add(layoutButtons[i]); 
            layoutButtons[i].setEnabled(true);
            layoutButtons[i].addActionListener(this);
        } 
} 
  
      
    public static void start() { 
    new LevelSelectMenu(); 
          
    }


	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object src = arg0.getSource();
		if(src == level1){
			System.out.println("level1");
			  try {
				Main.game = new DrawGame(1);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			frame.dispose();
		}
		else if(src == level2){
			System.out.println("level2");
			try {
				Main.game = new DrawGame(2);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			frame.dispose();
		}
		else if(src == level3){
			System.out.println("level3");
			try {
				Main.game = new DrawGame(3);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			frame.dispose();
		}
		else if(src == level4){
			System.out.println("level4");
			try {
				Main.game = new DrawGame(4);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			frame.dispose();
		}
		else if(src == level5){
			System.out.println("level5");
			try {
				Main.game = new DrawGame(5);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			frame.dispose();
		}
		else if(src == back){
			System.out.println("back");
			new MainMenu();
			frame.dispose();
		} 
	}


	private void DrawGame(String string) {
		// TODO Auto-generated method stub
		
	}
		
	}  
      