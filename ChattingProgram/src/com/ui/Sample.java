package com.ui;


import java.time.LocalDateTime;
import java.util.Vector;

import com.database.MemberDTO;

public class Sample {
	
	public static Vector<String> getmemberheader()
	{
		Vector<String> memberheader = new Vector<>();
		memberheader.add("ID");
		memberheader.add("PassWord");
		memberheader.add("NickName");

		return memberheader;
	}
	/**
	 * id
	 * pw
	 * 	setNickName(nickName);
		setName(name);
		setGender(gender);
		setZipcode(zipcode);
		setAddress(address);
	 */
	public static Vector<MemberDTO> getMembers(){
		Vector<MemberDTO> members = new Vector<>();
		members.add(new MemberDTO("qlwb11", "12345", "Ohsaam"));
		members.add(new MemberDTO("qlwb11", "12345","Ohsaam"));
		return members;
		
	
	
	}
	
	
	
	}


	
	
	

