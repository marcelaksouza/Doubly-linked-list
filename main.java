

public class main {

	public static void main(String[] args) {
		
		//initiating the controller with a new node list and a new db
		Controller controller = new Controller(new NodesList(), new Db());
		//welcome message from the controller 
		controller.welcome();
		//show what is the next in the queue
		controller.printThehead();
		//show the main menu
		controller.mainMenuView();
	}
}







