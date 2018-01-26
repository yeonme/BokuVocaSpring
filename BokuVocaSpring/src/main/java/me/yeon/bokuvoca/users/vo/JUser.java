package me.yeon.bokuvoca.users.vo;

public class JUser {
	private String username;
	private String email;
	private String password; //본 필드는 조회시 채워지지 않는다
	private String registered;
	private String lastlogin;
	private String photo;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRegistered() {
		return registered;
	}

	public void setRegistered(String registered) {
		this.registered = registered;
	}

	public String getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(String lastlogin) {
		this.lastlogin = lastlogin;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "JUser [username=" + username + ", email=" + email + ", password=" + password + ", registered=" + registered
				+ ", lastlogin=" + lastlogin + ", photo=" + photo + "]";
	}

	public JUser(){
		
	}
	
	public JUser(String username, String email, String secure, String registered, String lastlogin, String photo) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.registered = registered;
		this.lastlogin = lastlogin;
		this.photo = photo;
	}
}
