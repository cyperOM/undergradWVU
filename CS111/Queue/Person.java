public class Person implements Cloneable, Comparable{

    //  class TooOldException extends Exception {

	/*	public TooOldException (){super();}
	public TooOldException(String in){super(in);}

    }

    class capErrorException extends Exception {
	public capErrorException () {super ();}
	public capErrorException(String in) {super(in);	}
    }
	*/



    private int age;
    private String first;
    private String last;
    
    public Person(){
	age = 0;
	first = "John";
	last = "Doe";
    }
    

    public Person(String f, String l, int a){
	age = a;
	first = f;
	last = l;

    }

    // Getters
    public int age(){
	return age;
    }
    public String first(){
	return first;
    }
    public String last(){
	return last;
    }
    public String name(){
	return (first + " " + last);
    }


    // Setters
    public void setAge(int a){
	age = a;
    }
    public void setFirst(String f){
	first = f;
    }
    public void setLast(String l){
	last = l;
    }

    
    public void printPerson(){
	System.out.printf("%-10s %-12s %-3d\n",first,last,age);
    }

    public String toString(){
	return (first + " " +last+ " is " + age + " years old");
    }
    
    public int compareTo(Object p){
	if (((Person)p).age < age)
	    return 1;
	else if (((Person)p).age == age)
	    return 0;
	else
	    return -1;
    }
    public Object clone(){
	Person clone = new Person(first,last,age);
	return clone;
    }	    
    public boolean equals (Object p){
	return (p instanceof Person && ((Person)p).age == age && ((Person)p).first.equals(first) && ((Person)p).last.equals(last));
    }


    
}
