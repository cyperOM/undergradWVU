package javagame; 
  
import java.applet.Applet; 
import java.awt.Color; 
import java.awt.Frame; 
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent; 
import java.awt.event.KeyListener; 
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;

  
public class Main extends Applet implements Runnable, KeyListener{ 
  
	private Player player;
	private Image image, character, portal, tree, rock, treeWinter, treeFall, treeSummer, treeSpring;
	private Graphics second;
	private URL base;
	private int foxRight, foxLeft, season = 0; 
	public static DrawGame game;
	int highScoreValue;
	
	public static DrawGame getGame(){
		return game;
	}
	
    @Override
    public void init(){ 
    	HighscoreMenu.readFile(System.getProperty("user.dir") + "/highscore.txt");
    	new MainMenu();
        setSize(400,400); 
        setBackground(Color.GREEN); 
        setFocusable(true); 
        addKeyListener(this); 
        Frame frame = (Frame)this.getParent().getParent(); 
        frame.setTitle("Seasons"); 
        try {
			game = new DrawGame(1);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
        try {
        	base = getDocumentBase();
        } catch(Exception e){
        	System.out.println("There was an exception!");
        }
        character = getImage(base,"foxRight.png");
        portal = getImage(base, "slowportalgif.gif");
        tree = getImage(base, "tree.png");
        rock = getImage(base, "rock.png");
        getImage(base, "snow.png");
    } 
      
    @Override
    public void start(){ 
    	player = new Player();
    	
        Thread thread = new Thread(this); 
        thread.start(); 
    } 
  
    @Override
    public void stop(){ 
          
    } 
      
    @Override
    public void destroy(){ 
          
    } 
  
    @Override
    public void run() { 
        while(true){ 
        	player.update();
            repaint(); 
            try{ 
                Thread.sleep(17); 
            } catch (InterruptedException e){ 
                e.printStackTrace(); 
            } 
        } 
          
    } 
    
    public void update(Graphics g){
    	if(image==null){
    		image = createImage(this.getWidth(), this.getHeight());
    		second = image.getGraphics();
    		
    	}
    	second.setColor(getBackground());
    	second.fillRect(0, 0, getWidth(), getHeight());
    	second.setColor(getForeground());
    	paint(second);
    	
    	if (season == 0){ // spring
   	       setBackground(Color.GREEN); 
   	    tree = getImage(base,"treeSpring.png");
    	}
    	
    	if (season == 1){ // summer
    	       setBackground(Color.GREEN.darker()); 
    	       tree = getImage(base,"treeSummer.png");
    	}
    	
    	if (season == 2){ // fall
 	       setBackground(Color.ORANGE.darker()); 
 	      tree = getImage(base, "treeFall.png");
    	}
    	
    	if (season == 3){ // winter
    		setBackground(Color.WHITE); 
    		tree = getImage(base, "treeWinter.png");
    	}
    	
    	g.drawImage(image, 0, 0, this);
    }
    public void drawBorder(Graphics g){
    	g.drawImage(tree, 0, 0, this);
    	g.drawImage(tree, 0, 50, this);
    	g.drawImage(tree, 0, 100, this);
    	g.drawImage(tree, 0, 150, this);
    	g.drawImage(tree, 0, 200, this);
    	g.drawImage(tree, 0, 250, this);
    	g.drawImage(tree, 0, 300, this);
    	g.drawImage(tree, 0, 350, this);
    	g.drawImage(tree, 350, 0, this);
    	g.drawImage(tree, 300, 0, this);
    	g.drawImage(tree, 250, 0, this);
    	g.drawImage(tree, 200, 0, this);
    	g.drawImage(tree, 150, 0, this);
    	g.drawImage(tree, 100, 0, this);
    	g.drawImage(tree, 50, 0, this);
    	g.drawImage(tree, 350, 350, this);
    	g.drawImage(tree, 300, 350, this);
    	g.drawImage(tree, 250, 350, this);
    	g.drawImage(tree, 200, 350, this);
    	g.drawImage(tree, 150, 350, this);
    	g.drawImage(tree, 100, 350, this);
    	g.drawImage(tree, 50, 350, this);
    	g.drawImage(tree, 350, 50, this);
    	g.drawImage(tree, 350, 100, this);
    	g.drawImage(tree, 350, 150, this);
    	g.drawImage(tree, 350, 200, this);
    	g.drawImage(tree, 350, 250, this);
    	g.drawImage(tree, 350, 300, this);
    }
    
    public void paint(Graphics g){
    	drawBorder(g);
    	int inc = 50;
    	
    	for(int i = 0; i < 6;  i++){
    		for(int k = 0; k < 6; k++){
    			int pos = game.getArrayPos(k, i);
    			switch(pos){
    				case(1):
    					g.drawImage(character, (50*i)+inc, (50*k)+inc, this);
    					break;
    				case(2):
    					g.drawImage(portal, (50*i)+inc, (50*k)+inc, this);
    					break;
    				case(3):
    					g.drawImage(rock, (50*i)+inc, (50*k)+inc, this);
    					break;
    				case(4):
    					g.drawImage(tree, (50*i)+inc, (50*k)+inc, this);
    					break;
    			}
    		}
    	}
    	
    	if(foxRight == 1){
    		 character = getImage(base,"foxRight.png");
    	}
    	if (foxLeft == 1){
    		 character = getImage(base,"foxLeft.png");
    	}
    	
    }
  
    @Override
    public void keyPressed(KeyEvent e) { 
        switch(e.getKeyCode()){ 
        case KeyEvent.VK_UP: 
            System.out.println("Move Up");
            player.moveUp();
            break; 
        case KeyEvent.VK_DOWN: 
            System.out.println("Move Down"); 
            player.moveDown();
            break; 
        case KeyEvent.VK_LEFT: 
            System.out.println("Move Left"); 
            foxLeft = 1; 
            foxRight = 0;
            player.moveLeft();
            break; 
        case KeyEvent.VK_RIGHT: 
            System.out.println("Move Right"); 
            foxRight = 1;
            foxLeft = 0;
            player.moveRight();
            break; 
        case KeyEvent.VK_S:
        	System.out.println("Season Changed");
        	if(season == 3){
        		season = 0;
        	}
        	else {
        		season += 1;
        	}
        	break;
        case KeyEvent.VK_R:
            System.out.println("Level Reset"); 
            try { 
            	highScoreValue = HighscoreMenu.getHighScore();
            	highScoreValue = highScoreValue - 25;
            	HighscoreMenu.setHighScore(highScoreValue);
            	System.out.println(highScoreValue);
                game = new DrawGame(game.getLevel()); 
            } catch (FileNotFoundException e1) { 
                // TODO Auto-generated catch block 
                e1.printStackTrace(); 
            } 
        	break;
        case KeyEvent.VK_E:
        	exitGame();
        	break;
        case KeyEvent.VK_M:
        	save();
        	new MainMenu();
        	break;
        } 
          
    } 
  
    @Override
    public void keyReleased(KeyEvent e) { 
        switch(e.getKeyCode()){ 
        case KeyEvent.VK_UP: 
            System.out.println("Stop UP"); 
            break; 
        case KeyEvent.VK_DOWN: 
            System.out.println("Stop Down"); 
            break; 
        case KeyEvent.VK_LEFT: 
            System.out.println("Stop Left");
            break; 
        case KeyEvent.VK_RIGHT: 
            System.out.println("Stop Right"); 
            break; 
        } 
          
    } 
  
    @Override
    public void keyTyped(KeyEvent arg0) { 
        // TODO Auto-generated method stub 
          
    } 
    
    public static void loadGame(){
    	String info;
    	String[] infoArray = new String[2];
    	int lvl = 1;
    	FileReader fileReader;
		try {
			fileReader = new FileReader(System.getProperty("user.dir") + "/SeasonsSave.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			info = bufferedReader.readLine();
			infoArray = info.split(" ");
			lvl = Integer.parseInt(infoArray[0]);
			HighscoreMenu.setHighScore(Integer.parseInt(infoArray[1]));
			game = new DrawGame(lvl);
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void save(){
    	try {
			PrintWriter writer = new PrintWriter(System.getProperty("user.dir") + "/SeasonsSave.txt", "UTF-8");
			writer.print(game.getLevel() + " " + HighscoreMenu.getHighScore());
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	public void exitGame(){
		save();
		HighscoreMenu.writeFile();
		System.exit(0);
	}
}
      
