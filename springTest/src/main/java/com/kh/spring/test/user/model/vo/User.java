package com.kh.spring.test.user.model.vo;

// 3_4. User 클래스 만들기
// 3_5. UserDao 만들기
public class User {
	private String id;
	private String name;
	private String password;
	
	public User() {}

	public User(String id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + "]";
	}
}
