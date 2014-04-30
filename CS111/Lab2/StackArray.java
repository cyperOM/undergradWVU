//StackArray.java
//CS111 Implementation of a stack interface using an array


import java.util.EmptyStackException;

public class StackArray<T>
{
    private Object[] stack;
    private int top;
    private static final int maxSize=10;

    public static class FullStackException extends Exception
    {
	public FullStackException(){}
    }
	
    public StackArray()
    {
	stack = (T[]) new Object [maxSize];
	top = 0;
    }

    public StackArray(int numElements)
    {
	stack = (T[])new Object[numElements];
	top = 0;
    }

    public T pop() throws EmptyStackException 
    {   T val;
	if (empty())
	    throw new EmptyStackException();

	top--;
	val = (T)stack[top];
	return val;
    }


    public void push (T value) throws FullStackException
    {
	if (!full())
	    {  stack[top]=value;
	        top++;
	    }
	else throw new FullStackException();
    }

    public T peek() throws EmptyStackException
    {
	if (empty())
	    throw new EmptyStackException();

	return (T)stack[top -1];
    }


    public boolean empty()
    { boolean empt = false;
      if (top == 0)
	empt = true;
      return empt;
    }

    public boolean full ()
    { boolean ful=false;
      if (top == stack.length)
	  ful = true;
      return ful;
    }

    public int size()
    {
	return top;
    }


    public int getMaxSize()
    { return stack.length;
    } 


    public void clear()
    {
	top =0;
    }
}
