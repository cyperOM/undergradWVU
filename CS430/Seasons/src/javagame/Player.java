package javagame; 
 
public class Player { 
   
    public void update() { 
    	
    } 
  
    public void moveUp() { 
    	 Main.getGame().up();
    } 
  
    public void moveDown() { 
    	Main.getGame().down();
    } 
  
    public void moveLeft() { 
    	Main.getGame().left();
    } 
  
    public void moveRight() {
    	Main.getGame().right();
    }
} 