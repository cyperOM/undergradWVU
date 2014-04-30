//Sets.java
//cs111 sample layered implementation of Sets using LinkedList

import java.util.LinkedList;
import java.util.Iterator;

public class Sets<T>
{
    private LinkedList<T> set;

    public Sets()
    {
	set = new LinkedList<T>();
    }

    public Sets(Sets s1)
    {
	set = new LinkedList<T>(s1.set);
    }

    public void insert(T value)
    {
	if (!member(value))
	    set.add(value);
    }


    public boolean member(T value)
    {   Iterator<T> itr;
	boolean in = false;
 
	itr = set.listIterator();
	while (!in && itr.hasNext())
	    {if (value.equals(itr.next()))
		in = true;
	    }
	return in;
    }


    public Sets unions (Sets<T> s1)
    {
	Iterator<T> itr;
	Sets<T> unionset = new Sets<T>(s1);
	T addit;

	itr= set.listIterator();
	while ( itr.hasNext())
	    {   addit = itr.next();
		if (!unionset.member(addit))
		unionset.set.add(addit);
	    }
	return unionset;
    }

    public Sets intersection (Sets<T> s1)
    {   Iterator<T> itr;
	Sets<T> intersectionset = new Sets<T>();
	T addit;
	itr= s1.set.listIterator();

	while (itr.hasNext())
	    {   addit = itr.next();
		if (member(addit))
		    intersectionset.set.add(addit);
	    }
	return intersectionset;
    }

    
    public Sets difference (Sets<T> s1)
    {
	Iterator<T> itr;
	Sets<T> differenceset = new Sets<T>();
	T addit;

	itr= s1.set.listIterator();
	while (itr.hasNext())
	    {   addit = itr.next();
		if (!member(addit))
		    differenceset.set.add(addit);
	    }
	itr= set.listIterator();
	while (itr.hasNext())
	    {   addit = itr.next();
		if (!s1.member(addit))
		    differenceset.set.add(addit);
	    }
	return differenceset;
    }

    public void displayContents()
    {   Iterator<T> itr;

	itr = set.listIterator();
	while (itr.hasNext())
	    {System.out.println(itr.next());
	    }
    }
}
