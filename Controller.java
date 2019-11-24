import java.util.ArrayList;
import java.util.Scanner;

//to do
//blank validation
//string validation
//int validation
//priority validation

public class Controller {
	//global variables
	Db db;
	NodesList nodelist;
	ArrayList<Person> peopleList; 
	
	//constructors
	public Controller(NodesList nodelist,Db db) {
		this.db = db;
		this.nodelist = nodelist;
		//start the node list
		getAllAddtoList();
	}
	
	public void welcome() {
		//welcome message to the system
		System.out.println("--Welcome to imigration system.--");
	};
	
	public void mainMenuView(){
		//Main menu view
		System.out.println("--Menu--");
        System.out.println("1. Call the next on the queue ");
        System.out.println("2. View the queue"); 
        System.out.println("3. Add person to the queue"); 
        System.out.println("4. Check a person position on the queue by id"); 
        System.out.println("5. Update a person details");
        System.out.println("6. Delete options"); //falta conectar db only on delete many from tail
        System.out.println("7. exit");
        System.out.println("Enter a option");
        //call the switch controller for this view
        mainMenuSwitch();
	};
	
	public void mainMenuSwitch(){
        switch(Integer.parseInt(readUserInput())){
        case 1:{
        	// Call the next on the queue
        	// only if the length of the list is bigger then 0 
        	// delete the head and print the new head
        	// back to menu
        	if(nodelist.getLength() > 0) {
        		db.deletePerson(nodelist.getHead().getPerson().getId());
        		nodelist.deleteFromHead();
        	}
    		printThehead();
    		exitOrMenuView();
        }break;
        case 2:{
        	//View the queue
        	//only if the length of the list is bigger then 0 
        	//call the node list method who prints all nodes position
        	//else say the queue is empty
        	//back to menu
        	System.out.println("");
        	if(nodelist.getLength() > 0) {
        		nodelist.position();
        	}
        	else {
        		System.out.println("Queue is empty");
        	}
        	System.out.println("");
        	exitOrMenuView();
        	}break;
        case 3:{
            //Add person to the queue
        	//call the add new person form
        	//back to the menu
        	addNewPersonForm();
        	exitOrMenuView();
        }break;
        case 4:{
        	//Check a person position on the queue by id
        	//call the view in the queue method
        	//back to the menu
        	viewInTheQueue();
        	exitOrMenuView();
        }break; 
        case 5:{
        	//update menu
        	//call the update menu
        	updateMenuView();
        }break;
        case 6:{
        	//delete menu
        	//call the delete manu
        	deleteMenuView();
        }break;
        case 7: {
        	//exit
        	System.out.println("Good Bye");
        	System.exit(0);
        }break;
        default:
            System.out.println("Please type a valid option");
            mainMenuView();
        }
	};
	
	public void updateMenuView() {
		System.out.println("--Update Menu--");
        System.out.println("1. Update First name");
        System.out.println("2. Update Last name");
        System.out.println("3. Update passport");
        System.out.println("4. Update priority");
        System.out.println("5. Main menu");
        System.out.println("6. exit");
        System.out.println("Enter a option");
        updateMenuSwitch();
	}
	
	public void updateMenuSwitch() {
		switch(Integer.parseInt(readUserInput())){
        //Update First name
		case 1: {
			updateFname();
			exitOrMenuView();
		}break;
        //Update Last name
		case 2:{
			updateLname();
			exitOrMenuView();
		}break;
		//Update passport
        case 3:{
        	updatePassport();
        	exitOrMenuView();
        }break; 
        //Update priority
        case 4:{
        	updatePriority();
        	getAllAddtoList();
        	exitOrMenuView();
        }break;
        //Main menu
        case 5:{
        	mainMenuView();
        }break;
        //exit
        case 6:{
        		System.out.println("Good Bye");
        		System.exit(0);
        }break;
        default:
            System.out.println("Please type a valid option");
        }
	}
	
	public void deleteMenuView() {
		System.out.println("--Delete Menu--");
		System.out.println("1. Delete from the top of the list"); //done
        System.out.println("2. Delete from the queue by the id"); //Parei aqui
        System.out.println("3. Delete from the end of the queue"); //done
        System.out.println("4. Main menu"); //done
        System.out.println("5. exit"); //done
        System.out.println("Enter a option");
        deleteMenuScitch();
	};
	
	public void deleteMenuScitch() {
		switch(Integer.parseInt(readUserInput())){
        //delete from the top of the list
		case 1:{
			if(nodelist.getLength() > 0) {
        		db.deletePerson(nodelist.deleteFromHead().getPerson().getId());
        	}
    		printThehead();
    		exitOrMenuView();
		}break; 
		//delete from the queue by the id
        case 2:{
        	deleteFromMidleForm();
        	exitOrMenuView();
        }break; 
        //delete from the end of the queue
        case 3:{
        	deleteFromTailForm();
        	exitOrMenuView();
        }break;  
        //main menu
        case 4:{ 
        	mainMenuView();
        	exitOrMenuView();
        }break;
        case 5: {
        	System.out.println("Good Bye");
        	System.exit(0);
        } break;
        default:
            System.out.println("Please type a valid option");
        }
	};
	
	public void deleteFromTailForm() {
		System.out.println("How many should be deleted from the end of the list?");
        int qtd = Integer.parseInt(readUserInput());
        if(qtd<=nodelist.getLength()) {
        nodelist.deleteManyFromTail(qtd);}
        else {
        	nodelist.deleteAll();
        	db.deleteAll();
        }
	}
	
	public void deleteFromMidleForm() {
		System.out.println("what the id you want to delete from the queue");
        int id = Integer.parseInt(readUserInput());
        db.deletePerson(nodelist.deleteFromMidle(id).getPerson().getId());
        System.out.println("depois do metodo delete from midle");
	}
	
	public void viewInTheQueue() {
		System.out.println("Enter person Id");
		int id = Integer.parseInt(readUserInput());
		if (nodelist.returnAposition(id) == 0 ) {
			System.out.println("Id "+id+" is not in the list ");
		}
		else {
			System.out.println("Id "+id+" is at position "+nodelist.returnAposition(id));
		}
	}
	
	public Node updateFname() {
		System.out.println("Enter the Id");
        int id = Integer.parseInt(readUserInput());
        System.out.println("First name should be update to:");
        String fname = readUserInput();
		Node curr =nodelist.getHead();
        while (curr.getPerson().getId() != id) {
        	curr = curr.getNextNode();
        	if(curr.getPerson().getId() == id) {
            	curr.getPerson().setFirstName(db.updateFname(id, fname));
            	break;
            }	
        }
        return curr;
	}

	public Node updateLname() {
		System.out.println("Enter the Id");
        int id = Integer.parseInt(readUserInput());
        System.out.println("Last name should be update to:");
        String lname = readUserInput();
		Node curr =nodelist.getHead();
        while (curr.getPerson().getId() != id) {
        	curr = curr.getNextNode();
        	if(curr.getPerson().getId() == id) {
            	curr.getPerson().setLastName(db.updateLname(id, lname));
            	break;
            }	
        }
        return curr;
	}
		
	public Node updatePassport() {
		System.out.println("Enter the Id");
        int id = Integer.parseInt(readUserInput());
        System.out.println("Passport should be update to:");
        String passport = readUserInput();
		Node curr =nodelist.getHead();
        while (curr.getPerson().getId() != id) {
        	curr = curr.getNextNode();
        	if(curr.getPerson().getId() == id) {
            	curr.getPerson().setPassport(db.updatePassport(id, passport));
            	break;
            }	
        }
        return curr;
	}

	public Node updatePriority() {
		System.out.println("Enter the Id");
        int id = Integer.parseInt(readUserInput());
        System.out.println("Priority should be update to:");
        String priority = readUserInput().toUpperCase();
		Node curr =nodelist.getHead();
        while (curr.getPerson().getId() != id) {
        	curr = curr.getNextNode();
        }
        if(curr.getPerson().getId() == id) {
        	 System.out.println("got here dentro do if");
        	curr.getPerson().setPriority(Priority.valueOf(db.updatePriority(id, priority)));
        }
        return curr;
	}

	public void addNewPersonForm() {
		System.out.println("First name:");
        String fname = readUserInput();
        System.out.println("Last name:");
        String lname = readUserInput();
        System.out.println("Passport:");
        String passport = readUserInput();
        System.out.println("Priority:");
        Priority priority = Priority.valueOf(readUserInput().toUpperCase());
        nodelist.addNewNode(db.addPerson(new Person(fname,lname,passport,priority)));
        System.out.println(nodelist);
	}
	
	public void printThehead() {
		if(nodelist.getLength() > 0) {
			System.out.println("");
			System.out.println(
					"Next on the queue is : ID "
					+ nodelist.getHead().getPerson().getId() 
					+" "+nodelist.getHead().getPerson().getFirstName() 
					+" "+nodelist.getHead().getPerson().getLastName() 
					+" Priority "
					+ nodelist.getHead().getPerson().getPriority());
			System.out.println("");
		}
		else {
			System.out.println("");
			System.out.println("queue is empty");
			System.out.println("");
			}
	};
	
	public void getAllAddtoList() {
		//this method initiating the nodelist with all the people from the database
		//if the list is empty then connect to database and save the people into a array list
		//then from each element add to the node list
		if(nodelist.isEmpty()) {
			peopleList = db.getAllpeople();
			peopleList.forEach((c) -> {
				nodelist.addNewNode(c);
			});
		}
		//if the list isnt empty then clear delete all people from the nodelist and from array list
		//then call this method again to restart the node list
		else{
			peopleList.clear();
			nodelist.deleteAll();
			getAllAddtoList();
		}
	}
	
	public void exitOrMenuView(){
        System.out.println("What do you like to do next?");
        System.out.println("1. Go to main menu");
        System.out.println("2. Update person details menu");
        System.out.println("3. Delete person from the queue menu");
        System.out.println("4. Exit program");
        exitOrMenuSwitch();
	}
	
	public void exitOrMenuSwitch() {
		 switch(Integer.parseInt(readUserInput())){
	     //go to main menu
		 case 1:{ 
			 mainMenuView(); 
		 }break;
		 //update details menu
		 case 2:{
			 updateMenuView();
		 }break;
		 //delete from the queue menu
		 case 3:{
			 deleteMenuView();
		 }break;
		 //exit
		 case 4:{
			 System.out.println("Good Bye");
	         System.exit(0);
		 }break;
		 default: {
			 System.out.println("Enter a valid option");
	         exitOrMenuSwitch();
		 }
        }
    }

	public String readUserInput(){
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        String input = sc.next();  // Read user input
        return input; //return the user imput
    };
}
