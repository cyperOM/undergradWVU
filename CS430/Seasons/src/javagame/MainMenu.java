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
  
  
public class MainMenu extends JFrame implements ActionListener{       
    Image dbImage; 
    Graphics dbg; 
    static boolean gameStart = true; 
    JFrame frame = new JFrame(); 
    JButton[] layoutButtons = new JButton[10]; 
    JButton clicked = null;
    JButton newGame, continueGame, levelSelect, options, highScores, exit; 
    int screenWidth = 500; 
    int screenHeight = 500; 
    int rows = 1; 
    int columns = 3; 
           
    Dimension screenSize = new Dimension (screenWidth, screenHeight); 
    
    public MainMenu(){ 
        frame.setTitle("Seasons"); 
        frame.setLayout(new GridLayout (3, 2, 90, 45)); 
        frame.setSize(screenSize); 
        frame.setResizable(false); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setVisible(true); 
        frame.setBackground(Color.WHITE); 
        continueGame = new JButton("Continue"); 
        newGame = new JButton("New Game"); 
        levelSelect = new JButton("Level Select");
        options = new JButton("Options");
        highScores = new JButton("High Scores");
        exit = new JButton("Exit");
        layoutButtons[0] = continueGame; 
        layoutButtons[1] = newGame; 
        layoutButtons[2] = levelSelect; 
        layoutButtons[3] = options;
        layoutButtons[4] = highScores;
        layoutButtons[5] = exit;
        for (int i = 0; i < 6; i++){ 
            frame.add(layoutButtons[i]); 
            layoutButtons[i].setEnabled(true);
            layoutButtons[i].addActionListener(this);
        } 
} 
  
      
    public static void start() { 
    new MainMenu(); 
          
    }


	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object src = arg0.getSource();
		if(src == continueGame){
			System.out.println("ContinueGame");
			Main.loadGame();
			frame.dispose();
		}
		else if(src == newGame){
			System.out.println("newGame");
			try {
				HighscoreMenu.setHighScore(0);
				System.out.println(HighscoreMenu.getHighScore());
				Main.game = new DrawGame(1);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			frame.dispose();
		}
		else if(src == levelSelect){
			System.out.println("levelSelect");
		new LevelSelectMenu();
		frame.dispose();
		}
		else if(src == options){
			System.out.println("Options");
		new OptionsMenu();
		frame.dispose();
		} 
		else if(src == highScores){
			System.out.println("highScores");
			new HighscoreMenu();
		frame.dispose();
		} 
		else if(src == exit){
			System.out.println("Exit");
			System.exit(0);
		} 
	}
		
	}  
      
