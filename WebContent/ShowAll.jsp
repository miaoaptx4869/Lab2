<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<body>
	<h1>作者的信息</h1>
	 <table width="85%" border="1" align="center">
		<tr bgcolor="#949494">
			<th>AuthorID</th>
			<th>Name</th>
			<th>Age</th>
			<th>Country</th>
		</tr>
		<%
			ArrayList<Map<String, String>> list2 = (ArrayList<Map<String, String>>) session.getAttribute("authorlist");
			if (list2.size() > 0) {
				out.print("<tr align=\"center\"><td>" + list2.get(0).get("AuthorID") + "</td>\n");
				out.print("<td>" + list2.get(0).get("Name") + "</td>\n");
				out.print("<td>" + list2.get(0).get("Age") + "</td>\n");
				out.print("<td>" + list2.get(0).get("country") + "</td></tr>\n");
			} else {
				out.print("<tr><td>" + "false" + "</td></tr>\n");
			}
		%>

	</table>
	</table>
	<h1>作品的详细信息</h1>
	<table width="85%" border="1" align="center">
		<tr bgcolor="#949494">
			<th>ISBN</th>
			<th>Title</th>
			<th>AuthorID</th>
			<th>Publisher</th>
			<th>PublishDate</th>
			<th>Price</th>
		</tr>
		<%
			ArrayList<Map<String, String>> list = (ArrayList<Map<String, String>>) session.getAttribute("booklist");
			if (list.size() > 0) {
				out.print("<tr align=\"center\"><td>" + list.get(0).get("ISBN") + "</td>\n");
				out.print("<td>" + list.get(0).get("Title") + "</td>\n");
				out.print("<td>" + list.get(0).get("AuthorID") + "</td>\n");
				out.print("<td>" + list.get(0).get("Publisher") + "</td>\n");
				out.print("<td>" + list.get(0).get("PublishDate") + "</td>\n");
				out.print("<td>" + list.get(0).get("Price") + "</td></tr>\n");
			} else {
				out.print("<tr><td>" + "false" + "</td></tr>\n");
			}
		%>

	</table>
	</br>
	<a href=home><button type="button">返回主页</button></a>
</body>
</body>
</html>