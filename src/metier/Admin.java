package metier;

public class Admin {
	private String username;
	private String password;
	
	public Admin() {
		super();
	}
	public Admin(String username, String pas) {
		super();
		this.setUsername(username);
		this.setPassword(pas);
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
