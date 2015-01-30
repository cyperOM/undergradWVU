
/*
 * Name: Samantha Brooks
 * Computer Login-ID (WVU ID): sbrooks9
 * WVU Student ID Number: 701118894
 * Programming Assignment Number: 2
 * Date: 12/1/2014
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class access {
	static String start = "000";
	static String view = "canBeViewed";
	static String loggedIn = null, root = null;
	static PrintWriter friendsWriter;
	static PrintWriter listsWriter;
	static PrintWriter picturesWriter;
	static PrintWriter auditWriter;
	static File friendsFile = new File("friends.txt");
	static File listsFile = new File("lists.txt");
	static File picturesFile = new File("pictures.txt");
	static File auditFile = new File("audit.txt");
	static String[] listArray = new String[500];
	static String[] pictureArray = new String[100];
	static String[] friendArray = new String[100];
	static int listN = 0, friendN = 0, picCounter = 0;

	public static void main(String args[]) throws IOException {
		int tokenNum = 3; 
		Scanner input = new Scanner(System.in);
		String commandLine;
		System.out.println("Please enter the name of a file: ");
		String fileName = input.nextLine();
		System.out.println();
		System.out.println("Audit:");
		FileReader testFile = new FileReader(fileName);
		BufferedReader buffRead = new BufferedReader(testFile);

		while((commandLine = buffRead.readLine()) != null){
			String[] tokens = commandLine.split(" ", tokenNum);
			switch (tokens[0]){

			case "friendadd": 
				if(tokens[1].length() < 31 && tokens.length < 3 && !tokens[1].contains("/") && !tokens[1].contains(".") && !tokens[1].contains("\\") && !tokens[1].contains(" ")){
					friendadd(tokens[1]);
				}
				else if(tokens[1].length() >= 30){
					System.out.println("Error friendadd: The friend name is too long");
					auditWriter.println("Error friendadd: The friend name is too long");
				}
				else if((tokens[1].contains("/") || tokens[1].contains(".") || tokens[1].contains("\\") || tokens[1].contains(" ")) || tokens.length > 2){
					System.out.println("Error friendadd: The friend name contains invalid characters");
					auditWriter.println("Error friendadd: The friend name contains invalid characters");
				}
				break;

			case "viewby": 
				viewby(tokens[1]);
				break;

			case "logout": 
				logout();
				break;

			case "listadd": 
				if(tokens[1].length() < 31 && tokens.length < 3 && !tokens[1].contains("/") && !tokens[1].contains(".") && !tokens[1].contains("\\") && !tokens[1].contains(" ")){
					listadd(tokens[1]);
				}
				else if(tokens[1].length() >= 30){
					System.out.println("Error listadd: The list name is too long");
					auditWriter.println("Error listadd: The list name is too long");
				}
				else if((tokens[1].contains("/") || tokens[1].contains(".") || tokens[1].contains("\\") || tokens[1].contains(" ")) || tokens.length > 2){
					System.out.println("Error listadd: The list name contains invalid characters");
					auditWriter.println("Error listadd: The list name contains invalid characters");
				}
				break;

			case "friendlist": 
				friendlist(tokens[1], tokens[2]);
				break;

			case "postpicture": 
				if(tokens[1].length() < 31 && tokens.length < 3 && !tokens[1].contains("/") && !tokens[1].contains("\\") && !tokens[1].contains(" ")){
					postpicture(tokens[1]);
				}
				else if(tokens[1].length() >= 30){
					System.out.println("Error postpicture: The picture name is too long");
					auditWriter.println("Error postpicture: The picture name is too long");
				}
				else if((tokens[1].contains("/") || tokens[1].contains(".") || tokens[1].contains("\\") || tokens[1].contains(" ")) || tokens.length > 2){
					System.out.println("Error postpicture: The picture name contains invalid characters");
					auditWriter.println("Error postpicture: The picture name contains invalid characters");
				}
				break;

			case "chlst": 
				chlst(tokens[1], tokens[2]);
				break;

			case "chmod": 
				chmod(tokens[1], tokens[2]);
				break; 

			case "chown": 
				chown(tokens[1], tokens[2]);
				break;

			case "readcomments": 
				readcomments(tokens[1]);
				break;

			case "writecomments": 
				writecomments(tokens[1], tokens[2]);
				break;
			case "end": 
				end();
				break;
			}

		}
	}


	/*
	 * This method creates a friend and writes the friends name to a file: friends.txt
	 * @Input Param: a string that specifies the name of a friend
	 */

	public static void friendadd(String friendName)
			throws FileNotFoundException {
		if (start == "000") {
			friendsWriter = new PrintWriter(friendsFile);
			listsWriter = new PrintWriter(listsFile);
			picturesWriter = new PrintWriter(picturesFile);
			auditWriter = new PrintWriter(auditFile);
			start = "111";
			loggedIn = friendName;
			root = friendName;
			friendArray[friendN] = friendName;
			friendN++;
		}
		if (searchFileExactly(friendsFile, friendName) == "111" && loggedIn.equals(root)) {
			friendsWriter.println(friendName);
			System.out.println("Friend " + friendName + " added");
			auditWriter.println("Friend " + friendName + " added");
			friendArray[friendN] = friendName;
			friendN++;
		} 
		else if(!loggedIn.equals(root)){
			System.out.println("Error: only root may issue friendadd command");
			auditWriter.println("Error: only root may issue friendadd command");
		}
		else {
			System.out.println("Error: Friend " + friendName + " already exists");
			auditWriter.println("Error: Friend " + friendName + " already exists");
		}
		auditWriter.flush();
		friendsWriter.flush();
	}


	/*
	 * This method lets friends view your profile. 
	 * @Input Param: a string that specifies the name of a friend
	 * @Return: a string that acts as a flag to determine if a profile can be viewed
	 */

	public static String viewby(String friendName) throws FileNotFoundException {
		if (searchFileExactly(friendsFile, friendName) == "000" && (loggedIn == null || loggedIn.equals(friendName))) {
			System.out.println("Friend " + friendName + " views the profile");
			auditWriter.println("Friend " + friendName + " views the profile");
			auditWriter.flush();
			loggedIn = friendName;
			return view = "beingViewed";
		}
		else if((searchFileExactly(friendsFile, friendName) == "111")){
			System.out.println("Viewby failed: invalid friend name");
			auditWriter.println("Viewby failed: invalid friend name");
			auditWriter.flush();
			return view = "beingViewed";	
		}
		else {
			System.out.println("Login failed: simultaneous login not permitted");
			auditWriter.println("Login failed: simultaneous login not permitted");
			auditWriter.flush();
			return view = "canBeViewed";
		}
	}

	/*
	 * This method logs out the user and makes the profile unviewable.  
	 * @Return: a string that acts as a flag to indicate if something is veiwable or not
	 */

	public static void logout() {
		if(loggedIn != null){
			System.out.println("Friend " + loggedIn + " has logged out");
			auditWriter.println("Friend " + loggedIn + " has logged out");
			auditWriter.flush();
			loggedIn = null;
		//	return view = "canBeViewed";
		}
		else {
			System.out.println("Error: No one is logged in");
			auditWriter.println("Error: No one is logged in");
			auditWriter.flush();
		//	return view = "canBeViewed";
		}
	}

	/*
	 * This method creates a list and writes it to a file: lists.txt
	 * @Input Param: a string that specifies the name of a list
	 */

	public static void listadd(String listName) throws FileNotFoundException {
		if (searchFileClosely(listsFile, listName) == "111" && listName != "nil" && loggedIn.equals(root) && loggedIn != null) {
			listsWriter.append(listName+"\n");
			listArray[listN] = listName;
			System.out.println("List " + listName + " added");
			auditWriter.println("List " + listName + " added");
			listN++;
		}
		else if(!loggedIn.equals(root) && loggedIn != null){
			System.out.println("Error: only root may issue listadd command");
			auditWriter.println("Error: only root may issue listadd command");
		}
		else {
			System.out.println("Error: List " +  listName + " already exists");
			auditWriter.println("Error: List " +  listName + " already exists");
		}
		listsWriter.flush();
		auditWriter.flush();
	}


	/*
	 * This method adds friends to a specified friend list and writes it to a file: list.txt   
	 * @Input Param: two strings that specify the name of a friend and list
	 */

	public static void friendlist(String friendName, String listName)
			throws IOException {
		String friendFound = "no";
		for(int z = 0; z < friendN; z++){
			if(friendArray[z].equals(friendName)){
				friendFound = "yes";
			}
		}
		if(loggedIn == null){
			System.out.println("Error: You need to log in");
			auditWriter.println("Error: You need to log in");
			auditWriter.flush();
			return;
		}

		if (searchFileClosely(listsFile, listName) == "000" ) {	
			for (int i = 0; i < listN; i++) {
				if (listArray[i].contains(listName) && friendFound == "yes" && !listArray[i].contains(friendName)) {
					System.out.println("Friend " + friendName + " added to list " + listName);
					auditWriter.println("Friend " + friendName + " added to list " + listName);
					StringBuilder appendString = new StringBuilder();
					appendString.append(listArray[i] + " " + friendName);
					String newList = appendString.toString();
					fileRewriter(listsFile, newList, listArray[i].toString());
					listArray[i] = newList;
					auditWriter.flush();
					return;
				}
				else if (listArray[i].contains(friendName)){
					System.out.println("Error friendlist: " + friendName +" are already on " + listName);
					auditWriter.println("Error friendlist: " + friendName +" are already on " + listName);
					auditWriter.flush();
					return;
				}
				else if(friendFound == "no"){
					System.out.println("Error friendlist: Friend " + friendName + " does not exist");
					auditWriter.println("Error friendlist: Friend " + friendName + " does not exist");
					auditWriter.flush();
					return;
				}
			}
		}
		System.out.println("Error friendlist: list " + listName + " does not exist");
		auditWriter.println("Error friendlist: list " + listName + " does not exist");
		auditWriter.flush();
		return;

	} 


	/*
	 * This method stores all the information of a picture in one string  
	 * @Input Param: four strings that specify the name of a picture, the picture owner, 
	 * the list, and a set of permissions
	 * @Return: A string that contains all of the pictures information 
	 */
	public static String picture(String pictureName, String owner, String list, String permissions){
		StringBuilder picture = new StringBuilder();
		picture.append(pictureName);
		picture.append(",");
		picture.append(owner);
		picture.append(",");
		picture.append(list);
		picture.append(",");
		picture.append(permissions);
		String pic = picture.toString();
		return pic;	
	}

	/*
	 * This method posts a picture and creates a file for it
	 * @Input Param: a string that specifies the name of a picture
	 */

	public static void postpicture(String pictureName) throws IOException {
		if(loggedIn == null){
			System.out.println("Error: You need to log in");
			auditWriter.println("Error: You need to log in");
			auditWriter.flush();
			return;
		}
		if(searchFileExactly(picturesFile, pictureName) == "111"){
			picturesWriter.println(pictureName);
			picturesWriter.println(picture(pictureName, loggedIn, "nil", "rw -- --"));
			pictureArray[picCounter] = picture(pictureName, loggedIn, "nil", "rw -- --");
			System.out.println("Picture " + pictureName + " with owner " + loggedIn + " and default permissions created");
			auditWriter.println("Picture " + pictureName + " with owner " + loggedIn + " and default permissions created");
			picCounter++;
			File newFile = new File(pictureName);
			FileWriter newFileWriter = new FileWriter(newFile);
			newFileWriter.flush();
		}
		else {
			System.out.println("Error: file " + pictureName + " already exist");
			auditWriter.println("Error: file " + pictureName + " already exist");
		}
		auditWriter.flush();
		picturesWriter.flush();
	}

	/*
	 * This method changes the list assigned to a picture
	 * @Input Param: two strings, one for the picture name and one for the list name
	 */

	public static void chlst(String pictureName, String listName) throws IOException {
		if(loggedIn == null){
			System.out.println("Error: You need to log in");
			auditWriter.println("Error: You need to log in");
			auditWriter.flush();
			return;
		}
		for(int i = 0; i < picCounter; i++){
			if(pictureArray[i].contains(pictureName+"") && view == "beingViewed"){
				for(int z = 0; z < listN; z++){{
					if(listArray[z].contains(loggedIn) || loggedIn.equals(root)){
						String[] split = pictureArray[i].split(",");
						StringBuilder appendString = new StringBuilder();
						appendString.append(split[0]).append(",");
						appendString.append(split[1]).append(",");
						appendString.append(listName).append(",");
						appendString.append(split[3]);
						String newEntry = appendString.toString();
						pictureArray[i] = newEntry;
						fileRewriter(picturesFile, newEntry, pictureArray[i]);
						System.out.println("List for " + pictureName + " set to " + listName +  " by " + loggedIn);
						auditWriter.println("List for " + pictureName + " set to " + listName +  " by " + loggedIn);
						auditWriter.flush();
						return;
					}

				}
				}
			}
		}
		System.out.println("Error with chlst: Friend " + loggedIn + " is not a member of list " + listName);
		auditWriter.println("Error with chlst: Friend " + loggedIn + " is not a member of list " + listName);
		auditWriter.flush();
	}

	/*
	 * This method changes the permission assigned to a picture
	 * @Input Param: two strings, one for the file name and one for the list name
	 */

	public static void chmod(String fileName, String permission) throws IOException {
		if(loggedIn == null){
			System.out.println("Error: You need to log in");
			auditWriter.println("Error: You need to log in");
			auditWriter.flush();
			return;
		}

		for(int i = 0; i < picCounter; i++){
			if(pictureArray[i].contains(fileName) && (loggedIn == root || pictureArray[i].contains(loggedIn))){
				String[] split = pictureArray[i].split(",");
				StringBuilder appendString = new StringBuilder();
				appendString.append(split[0]).append(",");
				appendString.append(split[1]).append(",");
				appendString.append(split[2]).append(",");
				appendString.append(permission);
				String newEntry = appendString.toString();
				fileRewriter(picturesFile, newEntry, pictureArray[i]);
				pictureArray[i] = newEntry;
				System.out.println("Permissions for " +fileName+ " set to " + permission + " by " + loggedIn);
				auditWriter.println("Permissions for " +fileName+ " set to " + permission + " by " + loggedIn);		
				auditWriter.flush();
				return;

			}
			else if(pictureArray[i].contains(fileName) && !pictureArray[i].contains(loggedIn)){
				System.out.println("Permissions for " +fileName+ " can not be set by " + loggedIn);
				auditWriter.println("Permissions for " +fileName+ " can not be set by " + loggedIn);
				auditWriter.flush();
				return;
			}

		}
		System.out.println("Error with chmod: file " + fileName + " not found");
		auditWriter.println("Error with chmod: file " + fileName + " not found");
		auditWriter.flush();
	}

	/*
	 * This method changes the owner assigned to a picture
	 * @Input Param: two strings, one for the picture name and one for the friend name
	 */

	public static void chown(String pictureName, String friendName) throws IOException {
		if(loggedIn == null){
			System.out.println("Error: You need to log in");
			auditWriter.println("Error: You need to log in");
			auditWriter.flush();
			return;
		}
		if(picCounter == 0){
			System.out.println("Error with chown: no pictures exist");
			auditWriter.println("Error with chown:  no pictures exist");
			auditWriter.flush();
			return;	
		}

		for(int i = 0; i < picCounter; i++){
			if(pictureArray[i].contains(pictureName+",") && (loggedIn.equals(root)|| pictureArray[i].contains(loggedIn+","))){
				String[] split = pictureArray[i].split(",");
				StringBuilder putTogether = new StringBuilder();
				putTogether.append(split[0]).append(",").append(friendName).append(",").append(split[2]).append(",").append(split[3]);
				fileRewriter(picturesFile, putTogether.toString(), pictureArray[i].toString());
				pictureArray[i] = putTogether.toString();
				System.out.println("Owner of " + split[0] + " changed to " + friendName);
				auditWriter.println("Owner of " + split[0] + " hanged to " + friendName);
				picturesWriter.flush();
				auditWriter.flush();
				return;
			}
			else if(pictureArray[i].contains(pictureName+",") && !loggedIn.equals(root)){
				System.out.println("Error with chown: you do not have permission to change the owner");
				auditWriter.println("Error with chown: you do not have permission to change the owner");
				auditWriter.flush();
				return;
			}

		}
		System.out.println("Error with chown: file " + pictureName + " does not exist");
		auditWriter.println("Error with chown: file " + pictureName + " does not exist");
		picturesWriter.flush();
		auditWriter.flush();
	}

	/*
	 * This method allows the user logged in to read the file specified if they have the 
	 * correct permissions
	 * @Input Param: a string that contains the picture name
	 */

	public static void readcomments(String pictureName) throws IOException {
		if(loggedIn == null){
			System.out.println("Error: You need to log in");
			auditWriter.println("Error: You need to log in");
			auditWriter.flush();
			return;
		}

		BufferedReader reader = new BufferedReader(new FileReader(pictureName));
		String readLine = "";

		for(int i = 0; i < picCounter; i++){
			String[] split = pictureArray[i].split(",");
			String[] permissionSplit = split[3].split(" ");
			String  errorMessage = "no";

			if(pictureArray[i].contains(pictureName)){
				for (int z = 0; z < listN; z++){
					if (listArray[z].contains(split[2]) && listArray[z].contains(loggedIn)){
						errorMessage = "print";
					}
				}

				if(pictureArray[i].contains(loggedIn+",") && pictureArray[i].contains(pictureName) && (permissionSplit[0].equals("rw") || permissionSplit[0].equals("r-") || permissionSplit[0].equals("r"))){
					System.out.println("Friend " + loggedIn + " reads " + pictureName + " as: ");
					auditWriter.println("Friend " + loggedIn + " reads " + pictureName + " as: ");
					while((readLine = reader.readLine()) != null)
					{
						System.out.println(readLine);
						auditWriter.println(readLine);
					}
					reader.close();
					auditWriter.flush();
					break;
				}

				else if(!pictureArray[i].contains(loggedIn) && pictureArray[i].contains(split[2]) && errorMessage == "print" && (permissionSplit[1].equals("rw") || permissionSplit[1].equals("r-")|| permissionSplit[1].equals("r"))){
					System.out.println("Friend " + loggedIn + " reads " + pictureName + " as: ");
					auditWriter.println("Friend " + loggedIn + " reads " + pictureName + " as: ");
					while((readLine = reader.readLine()) != null)
					{
						System.out.println(readLine);
						auditWriter.println(readLine);
					}
					reader.close();
					auditWriter.flush();
					break;
				}

				else if(!pictureArray[i].contains(loggedIn) && (permissionSplit[2].equals("rw") || permissionSplit[2].equals("r-") || permissionSplit[2].equals("r"))){
					System.out.println("Friend " + loggedIn + " reads " + pictureName + " as: ");
					auditWriter.println("Friend " + loggedIn + " reads " + pictureName + " as: ");
					while((readLine = reader.readLine()) != null)
					{
						System.out.println(readLine);
						auditWriter.println(readLine);
					}
					reader.close();
					auditWriter.flush();
					break;
				}

				else {
					System.out.println("Friend "+ loggedIn +  " denied read access to " + pictureName);
					auditWriter.println("Friend "+ loggedIn +  " denied read access to " + pictureName);
					auditWriter.flush();
					break;
				}
			}
		}
	}

	/*
	 * This method allows the user logged in to write to the file specified if they have the 
	 * correct permissions
	 * @Input Param: two strings, one that contains the picture name and one that contains the text to be written
	 */

	public static void writecomments(String pictureName, String text) throws IOException {
		if(loggedIn == null){
			System.out.println("Error: You need to log in");
			auditWriter.println("Error: You need to log in");
			auditWriter.flush();
			return;
		}
		

		for(int i = 0; i < picCounter; i++){
			if(pictureArray[i].contains(pictureName+",")){
				String[] split = pictureArray[i].split(",");
				String[] permissionSplit = split[3].split(" ");
				boolean contains = false;
				for (int z = 0; z < listN; z++){
					if (listArray[z].contains(split[2]) && listArray[z].contains(loggedIn)){
						contains = true;
					}
				}

				if(pictureArray[i].contains(loggedIn+",") && (permissionSplit[0].equals("rw") || permissionSplit[0].equals("-w") || permissionSplit[0].equals("w"))){
					File picture = new File(pictureName);
					FileWriter newWriter = new FileWriter(picture, true);
					newWriter.append(text + "\n");
					newWriter.flush();
					System.out.println("Friend " + loggedIn + " wrote to " + pictureName + ": "+ text );
					auditWriter.println("Friend " + loggedIn + " wrote to " + pictureName + ": "+ text );
					auditWriter.flush();
					return;
				}
				else if(!pictureArray[i].contains(loggedIn) && contains == true && pictureArray[i].contains(split[2]) && (permissionSplit[1].equals("rw") || permissionSplit[1].equals("-w")|| permissionSplit[1].equals("w"))){
					File picture = new File(pictureName);
					FileWriter newWriter = new FileWriter(picture, true);
					newWriter.append(text + "\n");
					System.out.println("Friend " + loggedIn + " wrote to " + pictureName + ": "+ text );
					newWriter.flush();
					auditWriter.println("Friend " + loggedIn + " wrote to " + pictureName + ": "+ text );
					auditWriter.flush();
					return;
				}

				else if(!pictureArray[i].contains(loggedIn) && (permissionSplit[2].equals("rw") || permissionSplit[2].equals("-w")|| permissionSplit[2].equals("w"))){
					File picture = new File(pictureName);
					FileWriter newWriter = new FileWriter(picture, true);
					newWriter.append(text + "\n");
					System.out.println("Friend " + loggedIn + " wrote to " + pictureName + ": "+ text );
					auditWriter.println("Friend " + loggedIn + " wrote to " + pictureName + ": "+ text );
					auditWriter.flush();
					newWriter.flush();
					return;
				}
			}
		}
		System.out.println("Friend "+ loggedIn +  " denied write access to " + pictureName);	
		auditWriter.println("Friend "+ loggedIn +  " denied write access to " + pictureName);
		auditWriter.flush();

	}

	/*
	 * This method ends the program and closes all the file writers
	 */

	public static void end() {
		friendsWriter.close();
		listsWriter.close();
		picturesWriter.close();
		auditWriter.close();
	}


	/*
	 * This method searches a specified file for the exact string entered 
	 * @Input Param: a File to specify which file to search and a string to 
	 * represent what needs to be searcher
	 * @Return: A string that acts as a flag to indicates if a searched string was found
	 */

	public static String searchFileExactly(File fileName, String searchedVariable) throws FileNotFoundException {
		Scanner scanner = new Scanner(fileName);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (line.equals(searchedVariable)) {
				return "000";
			}
		}
		return "111";
	}


	/*
	 * This method searches a specified file for close to the string specified 
	 * @Input Param: a File to specify which file to search and a string to 
	 * represent what needs to be searcher
	 * @Return: A string that acts as a flag to indicates if a searched string was found
	 */

	public static String searchFileClosely(File fileName, String searchedVariable) throws FileNotFoundException {
		Scanner scanner = new Scanner(fileName);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (line.contains(searchedVariable)) {
				return "000";
			}
		}
		return "111";
	}

	/*
	 * This method searches a specified file for the exact string specified 
	 * @Input Param: a File to specify which file to rewrite and two strings. One string
	 * is the string to replace and the other string is what to overwrite with
	 */

	public static void fileRewriter(File fileName, String newText, String oldText) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String readLine, oldInfo= "";
		while((readLine = reader.readLine()) != null)
		{
			oldInfo += readLine + "\r\n";
		}
		reader.close();
		String newtext = oldInfo.replaceAll(oldText, newText);	   
		FileWriter writer = new FileWriter(fileName);
		writer.write(newtext);
		writer.close();
	}
}

