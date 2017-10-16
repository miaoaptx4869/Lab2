package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class connect {
	private Connection connection = null;

	public connect() {
		String driver = "com.mysql.jdbc.Driver";
		String username = System.getenv("ACCESSKEY");
		String password = System.getenv("SECRETKEY");
		//System.getenv("MYSQL_HOST_S"); 为从库，只读
		String dbUrl = String.format("jdbc:mysql://%s:%s/%s", System.getenv("MYSQL_HOST"), System.getenv("MYSQL_PORT"), System.getenv("MYSQL_DB"));
		try {
		    Class.forName(driver).newInstance();
		    connection = DriverManager.getConnection(dbUrl, username, password);
		    // ...
		} catch (Exception e) {
		    // ...
		}
	}


	public int update(String sql) {
		int lineNum = 0;
		try {
			PreparedStatement preStmt = connection.prepareStatement(sql);
			lineNum = preStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lineNum;
	}

	public ArrayList<Map<String, String>> select(String sql, String tableName) {
		ArrayList<Map<String, String>> result = new ArrayList<>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			String[] frame = getFrame(tableName);
			while (rs.next()) {
				Map<String, String> tmp = new HashMap<>();
				for (String key : frame) {
					if (key == "#")
						break;
					tmp.put(key, rs.getString(key));
				}
				result.add(tmp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int delete(String sql) {
		int lineNum = 0;
		try {
			Statement stmt = connection.createStatement();
			lineNum = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lineNum;
	}

	// 获取当前表的关键字，并以字符串数组的形式返回：如“username”，“id“等
	private String[] getFrame(String tableName) {
		String[] result = new String[10];
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("show columns from " + tableName);
			int i = 0;
			while (rs.next()) {
				result[i++] = rs.getString(1);
			}
			result[i] = "#";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	private void close() {
		try {
			if (connection != null) {
				connection.close();
			}
		}catch(Exception e) {
			
		}
	}

}