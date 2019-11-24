
import java.sql.*;
import java.util.ArrayList;

public class Db {
	 Connection conn = null;
	 Statement stmt = null;
	 
	 public Db() {
	 }

	public void dbConnect() {
		//this method makes the connection with the db
		 try {
	        // Load the database driver
	        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	        String dbServer = "jdbc:mysql://localhost:3306/db";
	        String user = "root";
	        String password = "";

	        // Get a connection to the database
	        conn = DriverManager.getConnection(dbServer, user, password);
	        if (conn != null) {
        }
        // Get a statement from the connection
        stmt = conn.createStatement();
    } catch (SQLException se) {
        System.out.println("SQL Exception:");

        // Loop through the SQL Exceptions
        while (se != null) {
            System.out.println("State  : " + se.getSQLState());
            System.out.println("Message: " + se.getMessage());
            System.out.println("Error  : " + se.getErrorCode());
            se = se.getNextException();
        }
    } catch (Exception e) {
        System.out.println(e);
    	}
	}

	public Person addPerson(Person person) {
		//add new person on people table usind Person as the parameter
		dbConnect();
		System.out.println("Add a person to the database");
		try {
        //Query to insert into the people Table
	        String query = "INSERT INTO people (fname, lname, passport, priority)" + " VALUES (?,?,?,?)";
	        //prepared statement is associating the position with the variables
	        PreparedStatement preparedStmt = conn.prepareStatement(query);
	        preparedStmt.setString(1, person.getFirstName());
	        preparedStmt.setString (2, person.getLastName());
	        preparedStmt.setString (3, person.getPassport());
	        preparedStmt.setString (4, person.getPriority().name());
	        
        //preparedStmt.executeUpdate() return the number of the rows inserted, so I am checking if its more than 0 then move on
        if (preparedStmt.executeUpdate() > 0) {
            System.out.println("A new person was inserted successfully!");
            //second query to retrieve the last inserted value
            String query1 = "SELECT id, arrivalDate FROM people where id = LAST_INSERT_ID();";
            //execute second query and save the result set into the variable rs
            ResultSet rs = stmt.executeQuery(query1);
            while (rs.next()) {
            	person.setId(rs.getInt("id")); 
            	person.setDate(rs.getString("arrivalDate"));
            }
            //closing statement and connection 
            stmt.close();
            conn.close();
        }
       
    } catch (Exception e) {
        System.out.print(e);
    }
	return person;
}

	public Person getOne(int id){
		dbConnect();
		//System.out.println("get one function");
		try {
		String query = "SELECT id, fname, lname, passport, priority, arrivalDate FROM people where id =" + id +";";
	    //execute second query and save the result set into the variable rs
	    ResultSet rs = stmt.executeQuery(query);
	    
	    while (rs.next() != false) {
	    	//saving into a map the values of the database
	    	Person person = new Person(rs.getInt("id"), 
					   rs.getString("fname"), 
					   rs.getString("lname"), 
					   rs.getString("passport"), 
					   Priority.valueOf(rs.getString("priority")), 
					   rs.getString("arrivalDate")
					   );
	    	//return map
	        return person; 
	    }
	    //closing statement and connection 
	    stmt.close();
	    conn.close();
	
		} catch (Exception e) {
			System.out.print(e);
	}
	return null;
	}
	
	public String updateFname(int id, String firstName) {
		dbConnect();
		System.out.println("update name function");
		try {
	        //query to update person first name
	        String query = "UPDATE people SET fname=? WHERE id=?";
	        PreparedStatement preparedStmt = conn.prepareStatement(query);
	        preparedStmt.setString(1, firstName);
	        preparedStmt.setInt (2, id);
	        
	        if (preparedStmt.executeUpdate() > 0) {
	            System.out.println(firstName + "'s name, was updated suceffuly");
	            return firstName;
	        }
	        stmt.close();
	        conn.close();
	    } catch (Exception e) {
	        System.out.print(e);
	    }
		return null;
	}
	
	public String updateLname(int id, String lastName) {
		dbConnect();
		//System.out.println("update name function");
		try {
	        //query to update person first name
	        String query = "UPDATE people SET lname=? WHERE id=?";
	        PreparedStatement preparedStmt = conn.prepareStatement(query);
	        preparedStmt.setString(1, lastName);
	        preparedStmt.setInt (2, id);
	        
	        if (preparedStmt.executeUpdate() > 0) {
	            System.out.println(lastName + "'s last name, was updated suceffuly");
	        }
	        stmt.close();
	        conn.close();
	    } catch (Exception e) {
	        System.out.print(e);
	    }
		return lastName;
	}
	
	public String updatePassport(int id, String passport) {
		dbConnect();
		//System.out.println("update name function");
		try {
	        //query to update person first name
	        String query = "UPDATE people SET passport=? WHERE id=?";
	        PreparedStatement preparedStmt = conn.prepareStatement(query);
	        preparedStmt.setString(1, passport);
	        preparedStmt.setInt (2, id);
	        
	        if (preparedStmt.executeUpdate() > 0) {
	            System.out.println("Passport number "+passport + " was updated suceffuly");
	        }
	        stmt.close();
	        conn.close();
	    } catch (Exception e) {
	        System.out.print(e);
	    }
		return passport;
	}
	
	public String updatePriority(int id, String priority) {
		dbConnect();
		//System.out.println("update name function");
		try {
	        //query to update person first name
	        String query = "UPDATE people SET priority=? WHERE id=?";
	        PreparedStatement preparedStmt = conn.prepareStatement(query);
	        preparedStmt.setString(1, priority);
	        preparedStmt.setInt (2, id);
	        
	        if (preparedStmt.executeUpdate() > 0) {
	            System.out.println("Priority was of id "+ id +" updated suceffuly");
	        }
	        stmt.close();
	        conn.close();
	        return priority;
	    } catch (Exception e) {
	        System.out.print(e);
	    }
		return null;
	}
	
	public int deletePerson(int id) {
		dbConnect();
		try {
	        //query to update person priority
	        String query = "DELETE FROM people WHERE id=?";
	        PreparedStatement preparedStmt = conn.prepareStatement(query);
	        preparedStmt.setInt(1, id);
	        
	        int rowsInserted = preparedStmt.executeUpdate();
	        if (rowsInserted > 0) {
	            System.out.println("A person was deleted successfully!");
	            return id;
	        }
	        stmt.close();
	        conn.close();
	    } catch (Exception e) {
	        System.out.print(e);
	    }
		return 0;
		
	}
	
	public void deleteAll() {
		dbConnect();
		try {
	        //query to update person priority
	        String query = "TRUNCATE TABLE  people;";
	        PreparedStatement preparedStmt = conn.prepareStatement(query);
	        
	        int rowsInserted = preparedStmt.executeUpdate();
	        if (rowsInserted > 0) {
	            System.out.println("A person was deleted successfully!");
	            return;
	        }
	        stmt.close();
	        conn.close();
	    } catch (Exception e) {
	        System.out.print(e);
	    }
		return;
	}
	
	public void getAllDisplayFromDatabase() {
		dbConnect();
		//System.out.println("get one function");
		try {
		String query = "SELECT * from people";
	    //execute second query and save the result set into the variable rs
	    ResultSet rs = stmt.executeQuery(query);
	    ResultSetMetaData rsmd = rs.getMetaData();
	    int columnsNumber = rsmd.getColumnCount();
	    while (rs.next()) {
	        for (int i = 1; i <= columnsNumber; i++) {
	            String columnValue = rs.getString(i);
	            System.out.printf("%-1S %-15S", rsmd.getColumnName(i) , columnValue );
	        }
	        System.out.println("");
	    }
	    //closing statement and connection 
	    stmt.close();
	    conn.close();
		} catch (Exception e) {
			System.out.print(e);
	}
	}
	 
	public ArrayList<Person> getAllpeople() {
	dbConnect();
	try {
    String query = "SELECT * FROM people";
    ResultSet rs = stmt.executeQuery(query);
    ArrayList<Person> peopleList = new ArrayList<>();
    while (rs.next()) {
    	Person person = new Person(rs.getInt("id"), 
    							   rs.getString("fname"), 
    							   rs.getString("lname"), 
    							   rs.getString("passport"), 
    							   Priority.valueOf(rs.getString("priority")), 
    							   rs.getString("arrivalDate")
    							   );
    	peopleList.add(person);
    }
	    stmt.close();
	    conn.close();
	    return peopleList;
    } catch (Exception e) {
        System.out.print(e);
    }
	return null; 
  }
}
   

	        

