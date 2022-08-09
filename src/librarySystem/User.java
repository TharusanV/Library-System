package librarySystem;

public class User {

	private int id;
	private String firstName;
	private String lastName;
	private String password;
	
	public User(int usersID, String usersFirstName, String usersLastName, String usersPassword) {
		this.id = usersID;
		this.firstName = usersFirstName;
		this.lastName = usersLastName;
		this.password = usersPassword;
	}
	
	public void setID(int changedUserID){
		this.id = changedUserID;
	}
	
	public void setFirstName(String changedUserFirstName){
		this.firstName = changedUserFirstName;
	}
	
	public void setLastName(String changedUserLastName){
		this.lastName = changedUserLastName;
	}
	
	public void setPassword(String changedUserPassword){
		this.password = changedUserPassword;
	}	
	
	public int getID(){
		return this.id;
	}
	
	public String getFirstName(){
		return this.firstName;
	}
	
	public String getLastName(){
		return this.lastName;
	}
	
	public String getPassword(){
		return this.password;
	}
}
