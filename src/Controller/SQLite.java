package Controller;

import Model.User;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SQLite {
    
    String driverURL = "jdbc:sqlite:" + "database.db";
    
    public void createNewDatabase() {
        try (Connection conn = DriverManager.getConnection(driverURL)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("Database database.db created.");
				
				Controller.Logger.log ("database creation", "database was created");
            }
        } catch (Exception ex) {
			ex.printStackTrace ();
			Controller.Logger.log ("database access error", "forced exit due to failure to connect to database @database create");
			System.exit (0);
		}
    }
    
    public void createUserTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (\n"
            + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
            + " username TEXT NOT NULL,\n"
            + " password TEXT NOT NULL,\n"
            + " role INTEGER DEFAULT 2\n"
            + ");";

        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table users in database.db created.");
			Controller.Logger.log ("user table creation", "user table was created");
        } catch (Exception ex) {
			ex.printStackTrace ();
			Controller.Logger.log ("database access error", "forced exit due to failure to connect to database @user table creation");
			System.exit (0);
		}
    }
    
    public void dropUserTable() {
        String sql = "DROP TABLE IF EXISTS users;";
		
        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
			
            System.out.println("Table users in database.db dropped.");
			Controller.Logger.log ("drop table", "users table dropped");
        } catch (Exception ex) {
			ex.printStackTrace ();
			Controller.Logger.log ("database access error", "forced exit due to failure to connect to database @drop user table");
			System.exit (0);
		}
    }
    
    public ArrayList<User> getUsers(){
        String sql = "SELECT id, username, password, role FROM users";
        ArrayList<User> users = new ArrayList<User>();
        
        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            
            while (rs.next()) {
                users.add(new User(rs.getInt("id"),
                                   rs.getString("username"),
                                   rs.getString("password"),
                                   rs.getInt("role")));
            
            }
			
			Controller.Logger.log ("data get", "all users were queried");
        } catch (Exception ex) {
			ex.printStackTrace ();
			Controller.Logger.log ("database access error", "forced exit due to failure to connect to database @get users");
			System.exit (0);
		}
        return users;
    }
	
	public User getUser (String username, String password) {
		StringBuilder sb = new StringBuilder ();
		sb.append (" select id, username, password, role ")
			.append (" from users ")
			.append (" where username = '")
				.append (username)
					.append ("' and ")
			.append (" password = '")
				.append (PasswordEncryption.hash (password))
				.append ("'");
				
		String sql = sb.toString ();
		
		 try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            
			User user = null;
            while (rs.next()) {
				user = new User(rs.getInt("id"),
                                   rs.getString("username"),
                                   rs.getString("password"),
                                   rs.getInt("role"));
            }
			
			Controller.Logger.log ("data get", "login request was queried");
			return user;
        } catch (Exception ex) {
			ex.printStackTrace ();
			Controller.Logger.log ("database access error", "forced exit due to failure to connect to database @login");
			System.exit (0);
		}
		
		return null;
	}
	
	public boolean userExists (String user) {
		StringBuilder sb = new StringBuilder ();
		sb.append ("select username ")
			.append (" from users ")
			.append (" where username = '").append (user).append ("'");
		
		String sql = sb.toString ();
		
		try (Connection conn = DriverManager.getConnection(driverURL);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next ()) { return true; }
			
			Controller.Logger.log ("data get", "username" + user + " availability checked");
		} catch (Exception ex) {
			ex.printStackTrace ();
			Controller.Logger.log ("database access error", "forced exit due to failure to connect to database @register");
			System.exit (0);
		}
		
		return false;
	}
    
    public void addUser(String username, String password) {
		String hashedPassword = PasswordEncryption.hash (password);
		
        String sql = "INSERT INTO users(username,password) VALUES('" + username + "','" + hashedPassword + "')";
        
        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()){
            stmt.execute(sql);
            
			Controller.Logger.log ("user add", "user " + username + " added");
//  For this activity, we would not be using prepared statements first.
//      String sql = "INSERT INTO users(username,password) VALUES(?,?)";
//      PreparedStatement pstmt = conn.prepareStatement(sql)) {
//      pstmt.setString(1, username);
//      pstmt.setString(2, password);
//      pstmt.executeUpdate();
        } catch (Exception ex) {
			ex.printStackTrace ();
			Controller.Logger.log ("database access error", "forced exit due to failure to connect to database @account creation");
			System.exit (0);
		}
    }
    
    public void addUser(String username, String password, int role) {
		String hashedPassword = PasswordEncryption.hash (password);
		
        String sql = "INSERT INTO users(username,password,role) VALUES('" + username + "','" + hashedPassword + "','" + role + "')";
        
        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()){
            stmt.execute(sql);
			Controller.Logger.log ("user add", "user " + username + " added");
        } catch (Exception ex) {
			ex.printStackTrace ();
			Controller.Logger.log ("database access error", "forced exit due to failure to connect to database @account creation");
			System.exit (0);
		}
    }
    
    public void removeUser(String username) {
        String sql = "DELETE FROM users WHERE username='" + username + "');";

        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("User " + username + " has been deleted.");
			
			Controller.Logger.log ("user add", "user " + username + " added");
        } catch (Exception ex) {
			ex.printStackTrace ();
			Controller.Logger.log ("database access error", "forced exit due to failure to connect to database @remove user");
			System.exit (0);
		}
    }
    
}
