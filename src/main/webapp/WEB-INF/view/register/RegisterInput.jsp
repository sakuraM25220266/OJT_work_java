<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true" %>
<%@ page import="java.sql.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%
   String employeeNo = (String) session.getAttribute("registerEmployeeNo");
   if (employeeNo == null) { 
     employeeNo = ""; 
   }
   
   String lastName = (String) session.getAttribute("registerLastName");
   if (lastName == null) { 
     lastName = ""; 
   }
   
   String firstName = (String) session.getAttribute("registerFirstName");
   if (firstName == null) { 
	 firstName = ""; 
   }
   
   String alphabetLastName = (String) session.getAttribute("registerAlphabetLastName");
   if (alphabetLastName == null) { 
	 alphabetLastName = ""; 
   }
   
   String alphabetFirstName = (String) session.getAttribute("registerAlphabetFirstName");
   if (alphabetFirstName == null) { 
	 alphabetFirstName = ""; 
   }
   
   String birthday = (String) session.getAttribute("registerBirthday");
   if (birthday == null) { 
	   birthday = ""; 
   }
   
   String hireDate = (String) session.getAttribute("registerHireDate");
   if (hireDate == null) { 
	   hireDate = ""; 
   }
   
   String department = (String) session.getAttribute("registerDepartment");
   if (department == null) { 
	 department = ""; 
   }
%>

<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<head>
<title>新規登録入力画面</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/employeeapp.css">

</head>
<body>
	<p>新規登録する社員情報を入力し、「内容確認に進む」ボタンをクリックしてください。</p>

	<form action="<%= request.getContextPath() %>/register/confirm" method="post">
	<table class="form-table">
		<tr>
			<td>社員番号<br>※必須</td>
			<td>
				<input type="text" name="employeeNo" value="<%= employeeNo %>">
                <% 
                    String employeeNoError = (String) request.getAttribute("employeeNoError");
                    if (employeeNoError != null) {
                %>
                    <br>
                    <span class="error-message"><%= employeeNoError %></span>
                <% 
                    }
                %>
			</td>
		</tr>
		<tr>
			<td>氏名(漢字)<br>※必須</td>
			<td><input type="text" name="lastName" value="<%= lastName %>" >
                <% 
                    String lastNameError = (String) request.getAttribute("lastNameError");
                    if (lastNameError != null) {
                %>
                    <br>
                    <span class="error-message"><%= lastNameError %></span> 
                <% 
                    }
                %>
            </td>
            <td><input type="text" name="firstName" value="<%= firstName %>" >
            	<% 
                    String firstNameError = (String) request.getAttribute("firstNameError");
                    if (firstNameError != null) {
                %>
                    <br>
                    <span class="error-message"><%= firstNameError %></span> 
                <% 
                    }
                %>
            </td>
			
		</tr>
		<tr>
			<td>氏名(ローマ字)<br>※必須</td>
			<td><input type="text" name="alphabetLastName" value="<%= alphabetLastName %>" >
                <% 
                    String alphabetLastNameError = (String) request.getAttribute("alphabetLastNameError");
                    if (alphabetLastNameError != null) {
                %>
                    <br>
                    <span class="error-message"><%= alphabetLastNameError %></span>
                <% 
                    }
                %>
            </td>
            <td><input type="text" name="alphabetFirstName" value="<%= alphabetFirstName %>" >
            	<% 
                    String alphabetFirstNameError = (String) request.getAttribute("alphabetFirstNameError");
                    if (alphabetFirstNameError != null) {
                %>
                    <br>
                    <span class="error-message"><%= alphabetFirstNameError %></span>
                <% 
                    }
                %>
            </td>
		</tr>
		<tr>
			<td>生年月日</td>
			<td><input type="date" name="birthday" value="<%= birthday %>" min="1900-01-01" max="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>">
            </td>
		</tr>
		<tr>
			<td>入社年月日</td>
			<td><input type="date" name="hireDate" value="<%= hireDate %>" min="1900-01-01" max="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>">
            </td>
		</tr>
		<tr>
			<td>部署</td>
			<td><input type="text" name="department" value="<%= department %>">
                <% 
                    String departmentError = (String) request.getAttribute("departmentError");
                    if (departmentError != null) {
                %>
                    <br>
                    <span class="error-message"><%= departmentError %></span>
                <% 
                    }
                %>
            </td>
		</tr>
	</table>
	<div>
		<button type="button" class="paging-button" onclick="location.href='<%= request.getContextPath() %>/list'">キャンセル</button>
	    <button type="submit" class="paging-button">内容確認に進む</button>
	</div>
	
    </form>
</body>
</html>