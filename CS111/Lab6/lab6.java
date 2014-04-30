public class lab6{
    public static void main(String[] args){
	
	Person henry = new Person("Henry", "Stern" , 7);
	Person steve = new Person();
	Person steve2;
	
	steve.setFirst("George");
	steve.setLast("Mason");
	steve.setAge(37);
	steve2 = (Person)steve.clone();

	henry.printPerson();
	steve.printPerson();
	steve2.printPerson();
	System.out.println("Comparison of is Henry older than Steve yields " + henry.compareTo(steve));
	System.out.println(henry);
	if (steve.equals(steve2))
	    System.out.println("Equals works!");
	    
    }
}
	