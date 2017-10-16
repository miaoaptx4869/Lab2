package com.structs.trio;

import database.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpSession;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import org.apache.struts2.ServletActionContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class HelloStrutsAction {
	private String ISBN;
	private ArrayList<String> list = null;

	public ArrayList<String> getList() {
		return this.list;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}
    
	//得到全部信息
	public String seeInfo() {
		ServletRequest Srequest = ServletActionContext.getRequest();
		HttpServletRequest Sreq = (HttpServletRequest) Srequest;
		HttpSession session = Sreq.getSession();
		// find book
		String sql1 = "select * from Book where ISBN=" + "'" + ISBN + "'";
		connect newc = new connect();
		ArrayList<Map<String, String>> result1 = newc.select(sql1, "Book");
        // put it into session
		session.setAttribute("booklist", result1);
		// find author
		String sql2 = "select * from Author where AuthorID=" + result1.get(0).get("AuthorID");
		ArrayList<Map<String, String>> result2 = newc.select(sql2, "Author");
		// put it into session
		session.setAttribute("authorlist", result2);
		return "SUCCESS";
	}

	//删除图书
	public String deleteBook() {
		String sql = "delete from Book where ISBN=" + "'" + ISBN + "'";
		connect newc = new connect();
		int result = newc.delete(sql);
		if (result == 0) {
			return "FALSE";
		} else {
			return "SUCCESS";
		}
	}

	
	
}