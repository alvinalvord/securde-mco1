package Controller;


import Model.User;
import View.Frame;
import java.util.ArrayList;



public class Main {
    
    public SQLite sqlite;
	private User user;
	public int userLock;
	public static final int userLimit = 5;
	
	public void setUser (User user) {
		if (user == null) 
			userLock ++;
		else {
			this.user = user; userLock = 0;
		}
	}
	
	public User getUser () {
		return user;
	}
    
    public static void main(String[] args) {
        new Main().init();
    }
    
    public void init(){
        // Initialize a driver object
        sqlite = new SQLite();
		
		user = null;
		userLock = 0;

        // Create a database
        sqlite.createNewDatabase();
        
        // Drop users table if needed
        sqlite.dropUserTable();
        
        // Create users table if not exist
        sqlite.createUserTable();
        
        // Add users
        sqlite.addUser("admin", "Qwerty.1234" , 5);
        sqlite.addUser("manager", "Qwerty.1234", 4);
        sqlite.addUser("staff", "Qwerty.1234", 3);
        sqlite.addUser("client1", "Qwerty.1234", 2);
        sqlite.addUser("client2", "Qwerty.1234", 2);
        
        // Get users
        ArrayList<User> users = sqlite.getUsers();
        for(int nCtr = 0; nCtr < users.size(); nCtr++){
            System.out.println("===== User " + users.get(nCtr).getId() + " =====");
            System.out.println(" Username: " + users.get(nCtr).getUsername());
            System.out.println(" Password: " + users.get(nCtr).getPassword());
            System.out.println(" Role: " + users.get(nCtr).getRole());
        }
        
        // Initialize User Interface
        Frame frame = new Frame();
        frame.init(this);
    }
    
}
