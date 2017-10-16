<%@ page language="java" import="java.net.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>查找结果</title>

</head>
<body>
	<h1>该作者的所有作品</h1>
	<table width="80%" border="1" align="center">
		<tr bgcolor="#949494">
			<th>ISBN</th>
			<th>Title</th>
			<th>删除</th>

		</tr>
		<%
			ArrayList<Map<String, String>> list = (ArrayList<Map<String, String>>) session.getAttribute("result");
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); ++i) {
					out.println("<tr align=\"center\" ><td>" + list.get(i).get("ISBN") + "</td>");
					out.println("<td><a href=seeinfo?ISBN=" + URLEncoder.encode(list.get(i).get("ISBN"), "UTF-8") + ">"
							+ list.get(i).get("Title") + "</td>");
					out.println("<td align=\"center\" ><a href=toedit?ISBN="
							+ URLEncoder.encode(list.get(i).get("ISBN"), "UTF-8"));
					out.println("<td align=\"center\" ><a href=deleteBook?ISBN="
							+ URLEncoder.encode(list.get(i).get("ISBN"), "UTF-8") + ">"
							+ "<button type=\"button\">删除这本书</button></a></td></tr>");
				}
			} else {
				out.print("<tr><td>" + "false" + "</td></tr>\n");
			}
		%>

	</table>
	<br />

	
		 <a href=home><button type="button">返回主页</button></a>


</body>
</html>