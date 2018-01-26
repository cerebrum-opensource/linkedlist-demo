/**
 * This code is used to create a simply linked list containing integer data and other basic functions
 * Using Generic type to pass Integer type values 
 * @author rpsingh
 *
 */
public class SinglyLinkedListImpl<T> {

	private Node<T> nodeHead;
	private Node<T> nodeTail;

	public Node<T> getNodeHead() {
		return nodeHead;
	}

	public void setNodeHead(Node<T> nodeHead) {
		this.nodeHead = nodeHead;
	}

	public Node<T> getNodeTail() {
		return nodeTail;
	}

	public void setNodeTail(Node<T> nodeTail) {
		this.nodeTail = nodeTail;
	}

	private void addNode(T element){

		Node<T> node = new Node<>();
		node.setValue(element);
		System.out.println("Adding: "+element);
		// at first instance when node is empty or is first node 
		//since there is only one element, both head and tail are same 
		if(nodeHead == null){
			nodeHead = node;
			nodeTail = node;
		} else {
			// if any node already exists, set current tail next link to new node
			nodeTail.setNextNodeLink(node);
			//set tail as newly created node
			nodeTail = node;
		}
	}

	/**
	 * Method to add a node in list after a given node
	 * @param element
	 * @param after
	 */
	private void addNodeAfter(T element, T after){
		Node<T> tmp = nodeHead;
		Node<T> refNode = null;
		/**
		 * Traverse till given element
		 */
		while(true){
			if(tmp == null){
				break;
			}
			if(tmp.compareTo(after) == 0){
				//found the target node, add after this node
				refNode = tmp;
				break;
			}
			tmp = tmp.getNextNodeLink();
		}
		if(refNode != null){
			//add element after the target node
			Node<T> nd = new Node<T>();
			nd.setValue(element);
			nd.setNextNodeLink(tmp.getNextNodeLink());
			if(tmp == nodeTail){
				nodeTail = nd;
			}
			tmp.setNextNodeLink(nd);

		} else {
			System.out.println("Unable to find the given element...");
		}
	}

	/**
	 * Delete the very first node from list
	 */
	private void deleteFirstNode(){
		if(nodeHead == null){
			System.out.println("Node doesn't exist");
			return;
		}
		Node<T> tmp = nodeHead;
		nodeHead = tmp.getNextNodeLink();
		if(nodeHead == null){
			nodeTail = null;
		}
		System.out.println("Deleted First Node: "+tmp.getValue());
	}

	/**
	 * Delete the node after given element of list
	 * @param after
	 */
	private void deleteNodeAfter(T after){
		Node<T> tmp = nodeHead;
		Node<T> refNode = null;
		/**
		 * Traverse till given element
		 */
		while(true){
			if(tmp == null){
				break;
			}
			if(tmp.compareTo(after) == 0){
				//found the target node, add after this node
				refNode = tmp;
				break;
			}
			tmp = tmp.getNextNodeLink();
		}
		if(refNode != null){
			tmp = refNode.getNextNodeLink();
			refNode.setNextNodeLink(tmp.getNextNodeLink());
			if(refNode.getNextNodeLink() == null){
				nodeTail = refNode;
			}
			System.out.println("Deleted: "+tmp.getValue());
		} else {
			System.out.println("Unable to find the given element...");
		}
	}

	
	/**
	 * Method to delete all nodes having value greater than supplied
	 * @param n
	 */
	private void delete(int n) {
		Node<T> prev = null, next;
		for (Node<T> current = nodeHead; current != null; current = next) {
			next = current.getNextNodeLink();
			if ((int)current.getValue() > n) {
				if (prev != null) {
					prev.setNextNodeLink(next);
				} else {
					nodeHead = next;
				}
			} else {
				prev = current;
			}
		}
	}


	/**
	 * Method to delete last node from list using traversal till node that don't have next 
	 * reference link and delete that node reference from previous one to replace as last node
	 */
	private void deleteLastNode(){
		System.out.println("Deleting last node from list");
		if(nodeHead == null)
			System.out.println("Nothing found to delete");
		else{
			Node<T> currentNode = null;
			Node<T> nextNode = nodeHead;
			while(nextNode.getNextNodeLink() != null)
			{
				currentNode = nextNode;
				nextNode = nextNode.getNextNodeLink();
			}
			if(currentNode != null)
			{
				currentNode.setNextNodeLink(null);
				nodeTail = currentNode;
			}
		}
	}

	/**
	 * Method to traverse/list all nodes of linked list
	 */
	private void traverseNodes(){
		try{
			System.out.println("Listing all nodes");
			Node<T> tmp = nodeHead;
			while(true){
				if(tmp == null){
					System.out.println();
					break;
				}

				System.out.print(tmp.getValue());
				tmp = tmp.getNextNodeLink();
				if(tmp != null)
					System.out.print(" -> ");
			}
		}
		catch(Exception e){e.printStackTrace();}
	}

	public static void main(String a[]){
		SinglyLinkedListImpl<Integer> sl = new SinglyLinkedListImpl<>();
		// Adding random nodes 
		sl.addNode(3);
		sl.addNode(32);
		sl.addNode(54);
		sl.addNode(89);
		sl.addNode(53);
		//Traversal of all nodes added so far
		sl.traverseNodes();
		// adding a node 76 after node 54 
		sl.addNodeAfter(76, 54);
		//Traversal of all nodes
		sl.traverseNodes();
		//Deleting first node of list
		sl.deleteFirstNode();
		//Traversal of all nodes
		sl.traverseNodes();
		//Deleting node after specific node //76
		sl.deleteNodeAfter(76);
		//Traversal of all nodes
		sl.traverseNodes();
		//Delete last node from list and remove reference from earlier node
		sl.deleteLastNode();
		//Traversal of all nodes
		sl.traverseNodes();
		// Adding random nodes
		sl.addNode(90);
		sl.addNode(91);
		sl.addNode(92);
		sl.addNode(35);
		//Traversal of all nodes
		sl.traverseNodes();
		// delete all nodes having value greater than 70
		sl.delete(70);
		//Traversal of all nodes
		sl.traverseNodes();
	}
}

class Node<T> implements Comparable<T> {

	private T value;
	private Node<T> nextNodeLink;

	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
	public Node<T> getNextNodeLink() {
		return nextNodeLink;
	}
	public void setNextNodeLink(Node<T> link) {
		this.nextNodeLink = link;
	}
	@Override
	public int compareTo(T arg) {
		if(arg == this.value){
			return 0;
		} else {
			return 1;
		}
	}
}