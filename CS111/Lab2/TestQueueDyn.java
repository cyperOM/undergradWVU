//TestQueueDyn.java
//CS111 test rpogrma to test dynamic queue implementation

public class TestQueueDyn
{

    public static void main(String [] args) throws QueueEmptyException
    {
	QueueDyn <Integer> q = new QueueDyn<Integer>();

	System.out.println("there are " + q.numElements() + " elements in the queue q");

	for (int c = 5; c > 0; c--)
	    q.enqueue(c);
 
	System.out.println ("there are " + q.numElements() +" elements in the queue q");
  
	QueueDyn <Integer> q1 = new QueueDyn<Integer>(q);

	System.out.println("there are " + q1.numElements() + " elements in the queue q1");

	System.out.println ("the contents of q1 are: "); 
	while (!q1.empty())
	    {
		System.out.println(q1.dequeue());
	    }

	System.out.println ("there are " + q1.numElements()+ " elements in the queue q1");
    }
}
