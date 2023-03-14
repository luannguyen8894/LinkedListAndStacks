//TODO: Complete java docs and code in missing spots.
//Missing spots are marked by < YOUR_CODE_HERE >.
//Do NOT edit any other parts of the code.

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.awt.Color;
import java.util.Comparator;

/**
 *  A stack of windows within the window.
 *  
 *  <p>Adapterion of Nifty Assignment (http://nifty.stanford.edu/) by
 *  Mike Clancy in 2001. Original code by Mike Clancy. Updated Fall
 *  2022 by K. Raven Russell.</p>
 */
public class WindowStack {
	//You'll need some instance variables probably...
	//< YOUR_CODE_HERE >
	/**
	 *  The head of the stack or the head of the linked list.
	 */
	private Node<Window> head;  	// first node
	/**
	 *  The tail of the stack or the bottom of the linked list.
	 */
	private Node<Window> tail;  	// last node
	/**
	 *  The size of the stack.
	 */
	private int size = 0;		// how many nodes
	/**
	 *  Initialize an empty stack of Window.
	 */
	public WindowStack() 
	{
		//Any initialization code you need.

		//O(1)

		//< YOUR_CODE_HERE >

		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	/**
	 *  This method returns the head (top) of the stack of windows.  
	 *  @return head attribute.
	 */
	public Node<Window> getHead() 
	{
		//Returns the head (top) of the stack of windows.

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
	 *  This method returns the tail (bottom) of the stack of windows.  
	 *  @return tail attribute.
	 */
	public Node<Window> getTail() {
		//Returns the tail (bottom) of the stack of windows.

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
	 *  This method returns the number of windows in the stack.  
	 *  @return size attribute.
	 */
	public int numWindows() 
	{
		//Gets the number of windows in the stack.

		//O(1)

		return this.size;
	}

	/**
	 *  This method add a window at the top of the stack.
	 *  @param r is the new window will be added.
	 */
	public void add(Window r) 
	{
		//Add a window at the top of the stack.

		//O(1)

		//throw IllegalArgumentException for invalid windows

		//Note: the "top" of the stack should
		//be the head of your linked list.

		//< YOUR_CODE_HERE >
		if(r == null)
			throw new IllegalArgumentException("Invalid windows");
		Node<Window> newNode = new Node<Window>(r);

		if(head == null)
		{
			head = tail = newNode;
			head.prev = null;
			tail.next = null;
			size++;
		}
		else
		{
			head.prev = newNode;
			newNode.next = head;
			head = newNode;
			head.prev = null;
			size++;
		}
		deselectedAll();
		makeSureTopIsSelected();
	}

	/**
	 *  This method remove a node from a stack.
	 *  @param x coordinate.
	 *  @param y coordinate.
	 *  @param deleteNode is the node needed to deleted.
	 *  @return the head of the stack .
	 */
	private Node<Window> deleteANode(Node<Window> deleteNode, int x, int y)
	{
		if(head == null || deleteNode == null)
			return null;

		if(head.data.contains(x, y) == true)
		{
			head = head.next;
		}
		//this one, tail has same coordinate also delete
		if(tail.data.contains(x, y) == true && tail == deleteNode)
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
		size--;
		deselectedAll();
		makeSureTopIsSelected();
		return  head;

	}

	/**
	 *  This method remove a node from a stack (except the top) for left click.
	 *  @param x coordinate.
	 *  @param y coordinate.
	 *  @param deleteNode is the node needed to deleted.
	 *  @return the head of the stack .
	 */
	private Node<Window> removeNodeForLeftClick(Node<Window> deleteNode, int x, int y)
	{
		////hereeeee
		if(tail.data.contains(x, y) == true && tail.data.getSelected() == true)
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
		size--;

		return  head;
	}

	/**
	 *  This method checks whether the top of the stack being clicked at.
	 *  @param x coordinate.
	 *  @param y coordinate.
	 *  @return true if it contains x,y. Otherwise false
	 */
	private boolean isTopWindow(int x, int y)
	{
		if(head.data.contains(x, y) == true)
			return true;
		return false;

	}

	/**
	 *  This method set isSelected of all the node in the stack to false.
	 */
	private void deselectedAll()
	{
		Node<Window> temp = head;
		while(temp != null)
		{
			temp.data.setSelected(false);

			temp = temp.next;
		}
	}

	/**
	 *  This method set the isSelected of the head node to be true.
	 */
	private void makeSureTopIsSelected()
	{
		if(head != null)
			head.data.setSelected(true);
	}

	/**
	 *  This method handles the left click and right click at the coordinate (x,y).
	 *  @param x coordinate.
	 *  @param y coordinate.
	 *  @param leftClick is the left click if it's true, otherwise it's a right click.
	 *  @return true if the click was handled, false if no window was found.
	 */
	public boolean handleClick (int x, int y, boolean leftClick) 
	{
		//The mouse has been clicked at position (x,y).
		//Left clicks are move windows to the top of the
		//stack or pass the click on to the window at the
		//top. Right clicks remove windows.

		//Returns true if the click was handled, false
		//if no window was found.

		//O(n) where n is the number of windows in the stack


		//Details:

		//Find the top-most window on the stack (if any)
		//that contains the given coordinate.

		//For a left click:

		//If the window is not at the top of the stack,
		//move it to the top of the stack without
		//disturbing the order of the other windows.
		//Mark this window as the "selected" window (and
		//ensure the previous selected window is no longer
		//selected).

		//If the window is at the top of the stack already,
		//ask the window to handle a click-event (using the
		//Window's handleClick() method).

		//If none of the windows on the stack were clicked
		//on, just return.

		//For a right click:

		//Remove the window from the stack completely. The
		//window at the top of the stack should be the 
		//selected window.


		//Hint #1: This would be a great time to use helper
		//methods! If you just write one giant method...
		//it'll be much harder to debug...

		//Hint #2: Make sure to use the methods you wrote
		//in the Window class. Don't write those again!


		//< YOUR_CODE_HERE >
		boolean isHandled = false;
		if(leftClick == true)
		{
			//click at top
			if(head != null && isTopWindow(x, y) == true)
			{
				head.data.handleClick(x, y);
				head.data.setSelected(true);
				isHandled = true;
			}
			//click somewhere else
			else
			{
				if(head != null)
				{
					deselectedAll();
					Node<Window> clickWindow = head;
					while(clickWindow != null)
					{
						//found the top most contains x,y
						if(clickWindow.data.contains(x, y) == true)
						{
							//temp = new Node<Window>(clickWindow.data);
							isHandled = true;
							clickWindow.data.setSelected(true);
							break;
						}
						clickWindow = clickWindow.next;

					}
					//somewhere else is windows
					if(isHandled == true)
					{
						head = removeNodeForLeftClick(clickWindow, x, y);
						add(clickWindow.data);
					}
				}



			}

		}
		//right click
		else
		{
			makeSureTopIsSelected();
			if(head != null)
			{

				Node<Window> clickWindow = head;
				while(clickWindow != null)
				{
					if(clickWindow.data.contains(x, y) == true)
					{
						isHandled = true;

						break;
					}
					clickWindow = clickWindow.next;
				}

				if(isHandled == true)
				{
					head = deleteANode(clickWindow, x, y);
				}
			}

		}

		return isHandled; //dummy return, replace this!
	}

	/**
	 *  Gets an iterator for the stack of windows.
	 *  Windows are returned from bottom to top.
	 *  
	 *  @return the iterator requested
	 */
	public Iterator<Window> windows() {
		//Note that this method uses your linked list!
		//so if the iterator doesn't work, that's on you...

		return new Iterator<>() {
			/**
			 *  The current node pointed to by the
			 *  iterator (containing the next value
			 *  to be returned).
			 */
			private Node<Window> current = getTail();

			/**
			 * {@inheritDoc}
			 */
			@Override
			public Window next() {
				if(!hasNext()) {
					throw new NoSuchElementException();
				}
				Window ret = current.data;
				current = current.prev;
				return ret;
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public boolean hasNext() {
				return (current != null);
			}
		};
	}

	/**
	 *  This method sorts the windows in the stack by their area (length x width).
	 */
	public void sortSize() 
	{
		//Sorts the windows in the stack by their area (length x width).
		//MOST of this is done for you, but you still need to assign
		//the returned head and tail back.

		//unselect the top window
		this.getHead().data.setSelected(false);

		//create a way to compare windows by area
		Comparator<Window> comp = new Comparator<>() 
		{
			public int compare(Window w1, Window w2) 
			{
				return (w1.getWidth()*w1.getHeight())-(w2.getWidth()*w2.getHeight());
			}
		};

		//create a pair of nodes to pass into the sort function
		ThreeTenLinkedList.NodePair<Window> pair = new ThreeTenLinkedList.NodePair<>(getHead(), getTail());

		//call the sort function with the comparator
		ThreeTenLinkedList.NodePair<Window> ret = ThreeTenLinkedList.sort(pair, comp);

		//make the returned list the head and tail of this list
		//this is for PART 5 of the project... don't try to do this
		//before you complete ThreeTenLinkedList.sort()!
		//< YOUR_CODE_HERE >

		if(ThreeTenLinkedList.isSorted(pair, comp) == true)
		{
			this.head = ret.head;
			this.tail = ret.tail;
		}



		this.getHead().data.setSelected(true);
	}


	/**
	 *  This method sorts the windows in the stack by their upper left corner location.
	 */
	public void sortLoc() 
	{
		//Sorts the windows in the stack by their upper left
		//corner loction. Left things (bigger-X) are on top
		//of right things (smaller-X). Tie-breaker: lower
		//things (bigger-Y) top of  higher things (smaller-Y).

		//This should use your ThreeTenLinkedList.sort() method you
		//write in Part 5, so don't do this until you have (a) read
		//part 5, (b) looked at the example in sortSize() above, and
		//(c) are sure you understand comparators.

		//O(n^2)

		//< YOUR_CODE_HERE >

		this.getHead().data.setSelected(false);

		//create a way to compare windows by area
		Comparator<Window> comp = new Comparator<>() 
		{
			public int compare(Window w1, Window w2) 
			{


				int i = w2.getUpperLeftX() - w1.getUpperLeftX();
				if(i != 0)
					return i;
				return Integer.compare(w2.getUpperLeftY(), w1.getUpperLeftY());


			}
		};

		//create a pair of nodes to pass into the sort function
		ThreeTenLinkedList.NodePair<Window> pair = new ThreeTenLinkedList.NodePair<>(getHead(), getTail());

		//call the sort function with the comparator
		ThreeTenLinkedList.NodePair<Window> ret = ThreeTenLinkedList.sort(pair, comp);

		//make the returned list the head and tail of this list
		//this is for PART 5 of the project... don't try to do this
		//before you complete ThreeTenLinkedList.sort()!
		//< YOUR_CODE_HERE >

		if(ThreeTenLinkedList.isSorted(pair, comp) == true)
		{
			this.head = ret.head;
			this.tail = ret.tail;
		}

		this.getHead().data.setSelected(true);




	}
}

