<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true" %>
<%@ page import="java.sql.Date"%>
<%
   
   String empNo = (String) session.getAttribute("empNo");
   if (empNo == null) { 
     empNo = ""; 
   }
   
   String lastName = (String) session.getAttribute("lastName");
   if (lastName == null) { 
     lastName = ""; 
   }
   
   String firstName = (String) session.getAttribute("firstName");
   if (firstName == null) { 
	 firstName = ""; 
   }
   
   String alphabetLastName = (String) session.getAttribute("alphabetLastName");
   if (alphabetLastName == null) { 
	 alphabetLastName = ""; 
   }
   
   String alphabetFirstName = (String) session.getAttribute("alphabetFirstName");
   if (alphabetFirstName == null) { 
	 alphabetFirstName = ""; 
   }
   
   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
   Date birthday = (Date) session.getAttribute("birthday");
   String birthdayStr = "";
   if (birthday == null) { 
	 birthdayStr = ""; 
   }else{
	   birthdayStr = dateFormat.format(birthday);
   }
   
   Date hireDate = (Date) session.getAttribute("hireDate");
   String hireDateStr = "";
   if (hireDate == null) { 
	  hireDateStr = ""; 
   }else{
	   hireDateStr = dateFormat.format(hireDate);
   }
   
   String department = (String) session.getAttribute("department");
   if (department == null) { 
	 department = ""; 
   }
   
   session.invalidate();
%>

<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<head>
<title>新規登録入力画面</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/test.css">

</head>
<body>
	<p>新規登録する社員情報を入力し、「内容確認に進む」ボタンをクリックしてください。</p>

	<form action="<%= request.getContextPath() %>/register/confirm" method="post">
	<table>
		<tr>
			<td>社員番号<br>※必須</td>
			<td>
				<input type="text" name="emp_no" value="<%= empNo %>">
			
                <% 
                    String empNoError = (String) request.getAttribute("empNoError");
                    if (empNoError != null) {
                %>
                    <span class="error-message"><%= empNoError %></span>
                <% 
                    }
                %>
			</td>
		</tr>
		<tr>
			<td>氏名(漢字)<br>※必須</td>
			<td><input type="text" name="last_name" value="<%= lastName %>" >
                <% 
                    String nameError = (String) request.getAttribute("nameError");
                    if (nameError != null) {
                %>
                    <span class="error-message"><%= nameError %></span> 
                <% 
                    }
                %>
            </td>
            <td><input type="text" name="first_name" value="<%= firstName %>" ></td>
			
		</tr>
		<tr>
			<td>氏名(ローマ字)<br>※必須</td>
			<td><input type="text" name="alphabet_last_name" value="<%= alphabetLastName %>" >
                <% 
                    String alphabetNameError = (String) request.getAttribute("alphabetNameError");
                    if (alphabetNameError != null) {
                %>
                    <span class="error-message"><%= alphabetNameError %></span>
                <% 
                    }
                %>
            </td>
            <td><input type="text" name="alphabet_first_name" value="<%= alphabetFirstName %>" ></td>
		</tr>
		<tr>
			<td>生年月日</td>
			<td><input type="date" name="birthday" value="<%= birthdayStr %>" min="1900-01-01" max="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>">
                <% 
                    String dateError = (String) request.getAttribute("dateError");
                    if (dateError != null) {
                %>
                    <span class="error-message"><%= dateError %></span>
                <% 
                    }
                %>
            </td>
		</tr>
		<tr>
			<td>入社年月日</td>
			<td><input type="date" name="hire_date" value="<%= hireDateStr %>" min="1900-01-01" max="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>">
                <% 
                    String hireDateError = (String) request.getAttribute("hireDateError");
                    if (hireDateError != null) {
                %>
                    <span class="error-message"><%= hireDateError %></span>
                <% 
                    }
                %>
            </td>
		</tr>
		<tr>
			<td>部署</td>
			<td><input type="text" name="department" value="<%= department %>">
                <% 
                    String departmentError = (String) request.getAttribute("departmentError");
                    if (departmentError != null) {
                %>
                    <span class="error-message"><%= departmentError %></span>
                <% 
                    }
                %>
            </td>
		</tr>
	</table>
	<div>
		<button type="button" onclick="location.href='<%= request.getContextPath() %>/list'">キャンセル</button>
	    <button type="submit">内容確認に進む</button>
	</div>
	
    </form>
</body>
</html>