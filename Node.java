//node class
public class Node {
	//each node has the data(person), the next node and the previous node
	private Person person;
	private Node nextNode;
	private Node prevNode;

	//node constructor needs a person
	public Node(Person person) {
		if (person == null) {
			System.out.print("Please insert a valid person");
		}
		else {
		this.person = person;
		}
	}
	
	//getter for person
	public Person getPerson() {
		return person;
	}
	
	public void setPerson(Person person) {
		this.person = person;
	}
	
	//getter for next node
	public Node getNextNode() {
		return nextNode;
	}
	
	//setter for next node
	public void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
	}
	
	//getter for previous node
	public Node getPrevNode() {
		return prevNode;
	}
	
	//setter for previous node
	public void setPrevNode(Node prevNode) {
		this.prevNode = prevNode;
	}
	
	@Override
	public String toString() {
		return this.person.toString();
	}
}
