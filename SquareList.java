//TODO: Complete java docs and code in missing spots.
//Missing spots are marked by < YOUR_CODE_HERE >.
//Do NOT edit any other parts of the code.

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Comparator;

/**
 *  A list of squares within a single window.
 *  
 *  <p>Adapterion of Nifty Assignment (http://nifty.stanford.edu/) by
 *  Mike Clancy in 2001. Original code by Mike Clancy. Updated Fall
 *  2022 by K. Raven Russell.</p>
 */
public class SquareList 
{
	//You'll need some instance variables probably...
	//< YOUR_CODE_HERE >
	/**
	 *  The head of the linked list reference to the very first node.
	 */
	private Node<Square> head;  	// first node
	/**
	 *  The tail of the linked list reference to the last node.
	 */
	private Node<Square> tail;  	// last node
	/**
	 *  The size of the linked list.
	 */
	private int size;		// how many nodes
	/**
	 *  Initialize an empty list of squares.
	 */
	public SquareList() 
	{
		//Any initialization code you need.
		
		//O(1)
		
		//< YOUR_CODE_HERE >
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	/**
	 *  Returns the head of the list of squares.
	 *  @return the head of linked list
	 */
	public Node<Square> getHead() 
	{
		//Returns the head of the list of squares.
		
		//O(1)
		
		//We will use this method to test your
		//linked list implementaiton of this
		//class, so whether or not you are using
		//the generic linked list class or bare
		//nodes, you must still be able to return
		//the appropriate head of the list.
		
		//< YOUR_CODE_HERE >
		
		return this.head; //dummy return, replace this!
	}
	
	/**
	 *  Returns the tail of the list of squares.
	 *  @return the tail of linked list
	 */
	public Node<Square> getTail() 
	{
		//Returns the tail of the list of squares.
		
		//O(1)
		
		//We will use this method to test your
		//linked list implementaiton of this
		//class, so whether or not you are using
		//the generic linked list class or bare
		//nodes, you must still be able to return
		//the appropriate tail of the list.
		
		//< YOUR_CODE_HERE >
		
		return this.tail; //dummy return, replace this!
	}
	
	/**
	 *  Gets the number of squares in the list.
	 *  @return the size of linked list
	 */
	public int numSquares() 
	{
		//Gets the number of squares in the list.
		
		//O(1)
		
		//< YOUR_CODE_HERE >
		
		return this.size;
	}
	
	/**
	 *  This method add a square to the list. Newly added squares.
	 *  @param sq is a new square.
	 */
	public void add(Square sq) 
	{
		//Add a square to the list. Newly added squares
		//should be at the back end of the list.
		
		//O(1)
		
		//throw IllegalArgumentException for invalid sqares
		
		//< YOUR_CODE_HERE >
		if(sq == null)
			throw new IllegalArgumentException("Invalid square.");
		
		Node<Square> newNode = new Node<Square>(sq);
		
		if(head == null)
		{
			head = tail = newNode;
			head.prev = null;
			tail.next = null;
			size++;
		}
		else
		{
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
			tail.next = null;
			size++;
		}
	}
	
	/**
	 *  This method remove a node from the linked list.
	 *  @param x coordinate.
	 *  @param y coordinate.
	 *  @param deleteNode is the node needed to deleted.
	 *  @return the head of the stack .
	 */
	private  Node<Square> deleteANode(Node<Square> deleteNode, int x, int y)
	{
		
		if(head == null || deleteNode == null)
			return null;
		
		if(head.data.contains(x, y) == true)
		{
			head = head.next;
		}
		if(tail.data.contains(x, y) == true)
		{
			tail = tail.prev;
		}
		
		if(deleteNode.next != null)
		{
			deleteNode.next.prev = deleteNode.prev;
		}
		if(deleteNode.prev != null)
		{
			deleteNode.prev.next = deleteNode.next;
		}
		deleteNode = null;
		size--;
		return head;

	}
	
	
	/**
	 *  This method Deletes all squares from the list that contain the position (x,y).
	 *  @param x is the x coordinate.
	 *  @param y is the y coordinate.
	 *  @return true if any squares get deleted and returns false otherwise.
	 */
	public boolean handleClick (int x, int y) 
	{
		//Deletes all squares from the list that contain the 
		//position (x,y). Returns true if any squares get
		//deleted and returns false otherwise.
		
		//Returns true if any squares were deleted.
		
		//O(n) where n is the size of the list of squares
		
		//< YOUR_CODE_HERE >
		Node<Square> current = head;
		Node<Square> next;
		boolean found = false;
		
		//when head node contain the position
		
		if(head == null)
			return false;
		while(current != null)
		{
			if(current.data.contains(x, y) == true)
			{
				next = current.next;
				head = deleteANode(current, x, y);
				found = true;
				current = next;
			}
			else
				current = current.next;
		}
		if(found == true)
			return true;
		return false;
	}

	/**
	 *  Gets an iterator for the list of squares.
	 *  Squares are returned in the order added.
	 *  
	 *  @return the iterator requested
	 */
	public Iterator<Square> elements() 
	{
		//Note that this method uses your linked list!
		//so if the iterator doesn't work, that's on you...
		
		return new Iterator<>() 
		{
			/**
			 *  The current node pointed to by the
			 *  iterator (containing the next value
			 *  to be returned).
			 */
			private Node<Square> current = getHead();
			
			/**
			 * {@inheritDoc}
			 */
			@Override
			public Square next() 
			{
				if(!hasNext()) 
				{
					throw new NoSuchElementException();
				}
				Square ret = current.data;
				current = current.next;
				return ret;
			}
			
			/**
			 * {@inheritDoc}
			 */
			@Override
			public boolean hasNext() 
			{
				return (current != null);
			}
		};
	}
	/**
	 *  This method sorts the squares in the window by their creation time (lower ids were created first).
	 */
	public void sortCreation() 
	{
		//Sorts the squares in the window by their creation time
		//(lower ids were created first). This should use your
		//ThreeTenLinkedList.sort() method you write in Part 5,
		//so don't do this until you have (a) read part 5,
		//(b) looked at the example in WindowStack, and (c) are 
		//sure you understand comparators.
		
		//O(n^2)
		
		//< YOUR_CODE_HERE >
		
		
		
		//create a way to compare windows by area
		Comparator<Square> comp = new Comparator<>() 
		{
			public int compare(Square s1, Square s2) 
			{
				return s1.id() - s2.id();
			}
		};
		
		//create a pair of nodes to pass into the sort function
		ThreeTenLinkedList.NodePair<Square> pair = new ThreeTenLinkedList.NodePair<>(this.head, this.tail);
		
		//call the sort function with the comparator
		ThreeTenLinkedList.NodePair<Square> ret = ThreeTenLinkedList.sort(pair, comp);
		
		//make the returned list the head and tail of this list
		//this is for PART 5 of the project... don't try to do this
		//before you complete ThreeTenLinkedList.sort()!
		//< YOUR_CODE_HERE >

		if(ThreeTenLinkedList.isSorted(pair, comp) == true)
		{
			this.head = ret.head;
			this.tail = ret.tail;
		}
		
	
		
		
	}
	/**
	 *  This method sorts the squares in the window by their location in the window.
	 */
	public void sortLoc() 
	{
		//Sorts the squares in the window by their location
		//in the window. Same rules as sorting the windows
		//in WindowStack. This should use your
		//ThreeTenLinkedList.sort() method you write in Part 5,
		//so don't do this until you have (a) read part 5,
		//(b) looked at the example in WindowStack, and (c) are 
		//sure you understand comparators
		
		//O(n^2)
		
		//< YOUR_CODE_HERE >
		
		
		
		
		//create a way to compare windows by area
		Comparator<Square> comp = new Comparator<>() 
		{
			public int compare(Square s1, Square s2) 
			{
				int i = s1.getUpperLeftX() - s2.getUpperLeftX();
				if(i != 0)
					return i;
				return Integer.compare(s1.getUpperLeftY(), s2.getUpperLeftY());
			}
		};
		
		//create a pair of nodes to pass into the sort function
		ThreeTenLinkedList.NodePair<Square> pair = new ThreeTenLinkedList.NodePair<>(this.head , this.tail);
		
		//call the sort function with the comparator
		ThreeTenLinkedList.NodePair<Square> ret = ThreeTenLinkedList.sort(pair, comp);
		
		//make the returned list the head and tail of this list
		//this is for PART 5 of the project... don't try to do this
		//before you complete ThreeTenLinkedList.sort()!
		//< YOUR_CODE_HERE >

		if(ThreeTenLinkedList.isSorted(pair, comp) == true)
		{
			this.head = ret.head;
			this.tail = ret.tail;
		}

	}

}
