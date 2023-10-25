package com.database;

import java.io.Serializable;

public class MemberDTO implements Serializable{
	private String username;
	private String password;
	private String nickname;
	
	public MemberDTO()
	{
		
	}
	public MemberDTO(String username, String password,String nickname)
	{
		this.username = username;
		this.password = password;
		this.nickname = nickname;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		System.out.println("setUsername 오류");

			this.username = username;

	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {

			this.password = password;

	}
	
	

}