package beans;

public class User {
	private String uuid;
	private String username;
	private String password;
	private int personnalBest;
	
	public User(String uuid, String username, String password, int personnalBest) {
		this.uuid = uuid;
		this.username = username;
		this.password = password;
		this.personnalBest = personnalBest;
	}
	
	public String toString() {
		return this.uuid + " " + this.username + " " + this.password + " " + this.personnalBest;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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

	public int getPersonnalBest() {
		return personnalBest;
	}

	public void setPersonnalBest(int personnalBest) {
		this.personnalBest = personnalBest;
	}
}
