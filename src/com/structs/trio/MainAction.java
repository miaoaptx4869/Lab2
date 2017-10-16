package com.structs.trio;

import database.*;
import java.sql.*;
import java.util.Map;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import org.apache.struts2.ServletActionContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class MainAction {
	private String name;
	private ArrayList<String> list = null;
	
	public ArrayList<String> getList() {
		return this.list;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//返回主页
	public String returnHome() {
		return "SUCCESS";
	}
    
	//按照作者名查询图书
	public String toSearch() {
		String sql1 = "select * from Author where Name=\"" + this.name + "\"";
		connect newc = new connect();
		ArrayList<Map<String, String>> result1 = newc.select(sql1, "Author");
		if (result1.size() == 0) {
			return "FALSE";
		}
		String id = result1.get(0).get("AuthorID");
		String sql2 = "select * from Book where AuthorID=" + id;
		ArrayList<Map<String, String>> result2 = newc.select(sql2, "Book");
		if (result2.size() == 0) {
			return "FALSE";
		}
		ServletRequest Srequest = ServletActionContext.getRequest();
		HttpServletRequest Sreq = (HttpServletRequest) Srequest;
		HttpSession session = Sreq.getSession();
		session.setAttribute("result", result2);

		return "SUCCESS";

	}

	
}