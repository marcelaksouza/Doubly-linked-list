import java.util.NoSuchElementException;

//to do
//delete from the middle of the list

//create a node list with a head attribute
public class NodesList {
	
	private Node head;
	private Node tail;
	private int length;
	
	//getters and setters
	public Node getHead() {
		return this.head;
	};

	protected void setHead(Node head) {
		this.head = head;
	};
	
	//function returns a boolean if the node parameter is the head 
	public boolean isHead(Node node) {
		return this.head == node;
	};
	
	public Node getTail() {
		return tail;
	}

	public void setTail(Node tail) {
		this.tail = tail;
	};
	
	public boolean isTail(Node node) {
		return this.tail == node;
	};
	
	public int getLength() {
		return this.length;
	}
	
	public boolean isEmpty() {
		return this.head == null;
	};
	
	public void addAtHead(Person person) {
		//insertAtHead is adding the data(Person) to the head	
		//is person isn't null
		if (person != null ) {
		//Create new node called newNode. newNode uses a person as parameter to create a new node
		Node newNode = new Node(person);
		//newNode need to point to the Head of the list
		newNode.setNextNode(this.head);
		//if the head of the list isn't null 
		if (this.head != null) {
			//then point the head previous node as the newNode
			this.head.setPrevNode(newNode);
			
			}
		//if the head of the list is null point head of the list as this new node
		this.head = newNode;
		
		//if tail of the list is null
		if(tail == null) { 
			//then add to tail of this list the new node
			this.tail = newNode;
			}
		//add one to the length
        this.length++;
		}
		
		//else print to enter a valid person
		else {
			System.out.println("Person can't be empty");
		}
	};
	
	public void addAtTail(Person person) {
        Node newNode = new Node(person);
        if(tail != null) {
        	this.tail.setNextNode(newNode) ;
        	}
        this.tail = newNode;
        if(head == null) { 
        	this.head = newNode;
        	}
        this.length++;
    }

	public void addNewNode (Person person) {
		if(isEmpty()) {
			addAtHead(person);
			return;
		}
			Node currentNode = this.head;
			Node newNode = new Node(person);
			//while head node is not null 	
			//while next node is not null and the next node priority is less than the head node priority
			while (currentNode!=null) {
				if(person.getPriority().ordinal()>currentNode.getPerson().getPriority().ordinal()) {
					newNode.setNextNode(currentNode);
					if(currentNode.getPrevNode()!=null) {
						currentNode.getPrevNode().setNextNode(newNode);
						newNode.setPrevNode(currentNode.getPrevNode());
					}
					currentNode.setPrevNode(newNode);
					if(this.head==currentNode)
						this.head=newNode;
					break;
				}
				//get the next node of the nextNode
				//else 
				currentNode = currentNode.getNextNode();
				}
			if(currentNode==null) {
				newNode.setPrevNode(this.tail);
				this.tail.setNextNode(newNode);
				this.tail = newNode;
			}
			this.length++;
			return;
			
	}
	
	public void position() {
		if (head != null) {
		int i = 1;
		Node curr = this.head;
		while (curr != null) {
			System.out.println("Position "+i+" "+curr.getPerson());
			i ++;
			curr = curr.getNextNode();
			}
		}
	}
	
	public int returnAposition(int id) {
		int i = 1;
		if (head != null) {
			Node curr = this.head;
			while (curr.getPerson().getId() != id) {
				i ++;
				if(curr.getNextNode() == null) {
					return 0;
				}
				curr = curr.getNextNode();
				}
			
			return i;
			}
		return 0;
		}

	public Node deleteFromHead() {
		try {
		if(isEmpty()){
		System.out.println("Node list is empty");
		return null; 
		}
			Node first = this.head;
				if(head.getNextNode() == null){
					this.head = null;//line not tested
					this.tail = null;
				}else{
					  // previous of next node (new first) becomes null
					  this.head.getNextNode().setPrevNode(null); 
					 }
				head = head.getNextNode();
				length--;
		return first;
		}catch(NoSuchElementException e) {
			System.out.println(e);
		}
		return null;
	};
	
	public Node deleteManyFromTail(int qtd) {
		if(isEmpty()){
			System.out.println("Node list is empty");
			return null; 
			}
				Node last = this.tail;
				int i = 0;
				while(i < qtd && tail != null ) {
					if(head.getNextNode() == null){
					this.head = null;
					}else{
						  // next of previous node (new last) becomes null
						  this.tail.getPrevNode().setNextNode(null);
						 }
					tail = tail.getPrevNode();
					length--;
					i++;
				}
			return last;
	};
	
	public void deleteAll() {
		deleteManyFromTail(getLength());
	}
	
	public Node deleteFromMidle(int id) {
		Node curr = this.head;
		if(curr != null) {
			while (curr.getPerson().getId() != id) {
			if (curr.getNextNode() != null) {
				curr= curr.getNextNode();
				}
			}
			if (curr.getPerson().getId() == id) {
				if(isHead(curr)) {
					deleteFromHead();
					return curr;
				}
				else if(isTail(curr)) {
					deleteManyFromTail(1);
					return curr;
				}
				else {
					curr.getNextNode().setPrevNode(curr.getPrevNode());
			        curr.getPrevNode().setNextNode(curr.getNextNode());
			        return curr;
				}
			}
		}
		return curr;
	}
		
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Node n = this.head;
		while (n != null) {
			sb.append("");
			sb.append(n);
			sb.append("\n");
			n = n.getNextNode();
		}
		return sb.toString();
	}
};

