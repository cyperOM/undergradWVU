package javagame;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DrawGame {     
	 static int [][] intArray = new int [6][6];
	 private int playerRow;
	 private int playerColumn;
	 private static int level;
	 private boolean onPortal = false;
	 int highscoreValue;
  
	 public DrawGame(int lvl) throws FileNotFoundException{
		 	 String fileName = null;
		 	 level = lvl;
		 	 switch(level){
		 	 	case 1:
		 	 		fileName = "level1.txt";
		 	 		break;
		 	 	case 2:
		 	 		fileName = "level2.txt";
		 	 		break;
		 	 	case 3:
		 	 		fileName = "level3.txt";
		 	 		break;
		 	 	case 4:
		 	 		fileName = "level4.txt";
		 	 		break;
		 	 	case 5:
		 	 		fileName = "level5.txt";
		 	 		break;
		 	 	default:
		 	 		System.out.println("Going to write...");
		 	 		HighscoreMenu.writeFile();
		 	 		Main.game = new DrawGame(1);
		 	 		new MainMenu();
		 	 		break;
		 	 }
		 	 String line = null;
			 String [] lineArray = new String [6];
			 int i = 0, j = 0, c = 0, r = 0;
			 try {
		            FileReader fileReader = new FileReader(fileName);
		            BufferedReader bufferedReader = new BufferedReader(fileReader);

		            while((line = bufferedReader.readLine()) != null) {
		            	if((r % 2.0) == 0){
		            		lineArray[i] = line;
		            		i++;
		            	}	
		            	r++;
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
		 
		for(int row = 0; row < 6; row++){
			for (int column = 0; column < 6; column++){
				//Floor
				if (lineArray[row].charAt(column) == '0'){
					intArray[row][column] = 0;
				}
				//Player
				else if (lineArray[row].charAt(column) == '1'){
					intArray[row][column] = 1;
					playerRow = row;
					playerColumn = column;
					
				}
				//Exit
				else if (lineArray[row].charAt(column) == '2'){
					intArray[row][column] = 2;
				}
				//Block
				else if (lineArray[row].charAt(column) == '3'){
					intArray[row][column] = 3;
				}
				//Tree
				else if (lineArray[row].charAt(column) == '4'){
					intArray[row][column] = 4;
				}					
			}
			System.out.println();
					}
		 		} 
	public int getArrayPos(int posX, int posY){
		return intArray[posX][posY];
	}
	
	public void right(){
		if(playerColumn > 5)
			return;
		else if(intArray[playerRow][playerColumn + 1] == 4)
			return;
		else if(intArray[playerRow][playerColumn + 1] == 3){
			if((playerColumn + 2) > 5)
				return;
			else if(intArray[playerRow][playerColumn + 2] == 4)
				return;
			else{
				if(intArray[playerRow][playerColumn + 2] == 2){
					nextLevel();
				} else {
					intArray[playerRow][playerColumn] = 0;
					intArray[playerRow][playerColumn + 1] = 1;
					intArray[playerRow][playerColumn + 2] = 3;
					playerColumn++;
					return;
				}
			}
		} else {
			if(onPortal){
				intArray[playerRow][playerColumn] = 2; 
				intArray[playerRow][playerColumn + 1] = 1; 
				playerColumn++;
				onPortal = false;
				return;
			} else {
				if(intArray[playerRow][playerColumn + 1] == 2){
					onPortal = true;
				}
				intArray[playerRow][playerColumn] = 0;
				intArray[playerRow][playerColumn + 1] = 1;
				playerColumn++;
				return;
			}
		}
	}
	
	public void left(){
		if(playerColumn < 0)
			return;
		else if(intArray[playerRow][playerColumn - 1] == 4)
			return;
		else if(intArray[playerRow][playerColumn - 1] == 3){
			if((playerColumn - 2) < 0)
				return;
			else if(intArray[playerRow][playerColumn - 2] == 4)
				return;
			else{
				if(intArray[playerRow][playerColumn - 2] == 2){
					nextLevel();
				} else {
					intArray[playerRow][playerColumn] = 0;
					intArray[playerRow][playerColumn - 1] = 1;
					intArray[playerRow][playerColumn - 2] = 3;
					playerColumn--;
					return;
				}
			}
		} else {
			if(onPortal){
				intArray[playerRow][playerColumn] = 2; 
				intArray[playerRow][playerColumn - 1] = 1; 
				playerColumn--;
				onPortal = false;
				return;
			} else {
				if(intArray[playerRow][playerColumn - 1] == 2){
					onPortal = true;
				}
				intArray[playerRow][playerColumn] = 0;
				intArray[playerRow][playerColumn - 1] = 1;
				playerColumn--;
				return;
			}
		}
	}
	
	public void down(){
		if(playerRow > 5)
			return;
		else if(intArray[playerRow + 1][playerColumn] == 4)
			return;
		else if(intArray[playerRow + 1][playerColumn] == 3){
			if((playerRow + 2) > 5)
				return;
			else if(intArray[playerRow + 2][playerColumn] == 4)
				return;
			else{
				if(intArray[playerRow + 2][playerColumn] == 2){
					nextLevel();
				} else {
					intArray[playerRow][playerColumn] = 0;
					intArray[playerRow+ 1][playerColumn] = 1;
					intArray[playerRow + 2][playerColumn] = 3;
					playerRow++;
					return;
				}
			}
		} else {
			if(onPortal){
				intArray[playerRow][playerColumn] = 2; 
				intArray[playerRow + 1][playerColumn] = 1; 
				playerRow++;
				onPortal = false;
				return;
			} else {
				if(intArray[playerRow + 1][playerColumn] == 2){
					onPortal = true;
				}
				intArray[playerRow][playerColumn] = 0;
				intArray[playerRow + 1][playerColumn] = 1;
				playerRow++;
				return;
			}
		}
	}
	
	public void up(){ 
		if(playerColumn <0); 
		else if(intArray[playerRow - 1][playerColumn] == 4) ;
		else if(intArray[playerRow - 1][playerColumn] == 3){ 
			if((playerRow - 2) <0) ; 
			else if(intArray[playerRow-2][playerColumn] == 4) ; 
			else{
				if(intArray[playerRow - 2][playerColumn] == 2){
					nextLevel();
				} else {
					intArray[playerRow][playerColumn] = 0; 
					intArray[playerRow-1][playerColumn] = 1; 
					intArray[playerRow-2][playerColumn] = 3; 
					playerRow--; 
				}
			} 
		} 
		else {
			if(onPortal){
				intArray[playerRow][playerColumn] = 2; 
				intArray[playerRow - 1][playerColumn] = 1; 
				playerRow--;
				onPortal = false;
				return;
			} else {
				if(intArray[playerRow - 1][playerColumn] == 2){
					onPortal = true;
				}
				intArray[playerRow][playerColumn] = 0; 
				intArray[playerRow - 1][playerColumn] = 1; 
				playerRow--;
				return;
			}
		} 
	}
	
	private void nextLevel() { 
        level++; 
        
        highscoreValue = HighscoreMenu.getHighScore();
        highscoreValue = highscoreValue + 100;
        HighscoreMenu.setHighScore(highscoreValue);
        System.out.println(highscoreValue);
        try { 
            Main.game = new DrawGame(level); 
        } catch (FileNotFoundException e) { 
            // TODO Auto-generated catch block 
            e.printStackTrace(); 
        } 
    }

	public int getLevel(){
		return level;
	}
}
