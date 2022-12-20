//TODO: Add JavaDocs ONLY. No other Editing.
/**
 *  The is the Node class that store generic object type.
 *	@param <T> type.
 */
class Node<T> 
{
	/**
	 *  The is the data attribute of the node.
     */
	public T data;
	/**
	 *  The is the reference to the next node.
     */
	public Node<T> next;
	/**
	 *  The is the reference to the previous node.
     */
	public Node<T> prev;
	/**
	 *  A empty default constructor.
     */
	public Node() 
	{
		
	}
	/**
	 *  A constructor with a designed data.
	 *	@param data is the data will be assigned.
     */
	public Node(T data) 
	{
		this.data = data;
	}
}