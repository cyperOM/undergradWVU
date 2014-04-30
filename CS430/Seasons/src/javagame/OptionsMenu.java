package javagame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class OptionsMenu extends JFrame implements ActionListener{       
    Image dbImage; 
    Graphics dbg; 
    static boolean gameStart = true; 
    JFrame frame = new JFrame(); 
    JButton[] layoutButtons = new JButton[10]; 
    JButton soundOff, soundOn, back; 
    int screenWidth = 500; 
    int screenHeight = 500; 
    int rows = 1; 
    int columns = 3; 
           
    Dimension screenSize = new Dimension (screenWidth, screenHeight); 
      
    public OptionsMenu(){ 
        frame.setTitle("Seasons"); 
        frame.setLayout(new GridLayout (3, 2, 90, 45)); 
        frame.setSize(screenSize); 
        frame.setResizable(false); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setVisible(true); 
        frame.setBackground(Color.WHITE); 
        soundOn = new JButton("Sound On"); 
        soundOff = new JButton("Sound Off"); 
        back = new JButton("Back");
        layoutButtons[0] = soundOff; 
        layoutButtons[1] = soundOn; 
        layoutButtons[2] = back; 
        
        for (int i = 0; i < 3; i++){ 
            frame.add(layoutButtons[i]); 
            layoutButtons[i].setEnabled(true);
            layoutButtons[i].addActionListener(this);
        } 
        
} 

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object src = arg0.getSource();
		if(src == soundOn)
			System.out.println("Sound On");
		else if(src == soundOff)
			System.out.println("Sound Off");
		else if(src == back){
			new MainMenu();
			frame.dispose();
		}
	}  
      
}
