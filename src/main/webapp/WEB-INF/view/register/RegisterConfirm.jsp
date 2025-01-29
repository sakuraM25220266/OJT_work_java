<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録確認画面</title>
</head>
<body>
	<%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null && !errorMessage.isEmpty()) {
    %>
        <span class="error-message" style="color: red; font-weight: bold;">
            <%= errorMessage %>
        </span>
    <% 
        }
    %>
	
	<p>下記内容でよろしければ、「登録する」ボタンをクリックしてください。</p>
	<form action="<%= request.getContextPath() %>/register/complete" method="post">
        <table>
            <tr>
                <td>社員番号</td>
                <td><%= request.getAttribute("empNo") %></td>
                <input type="hidden" name="emp_no" value="<%= request.getAttribute("empNo") %>">
            </tr>
            <tr>
                <td>氏名（漢字）</td>
                <td><%= request.getAttribute("lastName") %> <%= request.getAttribute("firstName") %></td>
                <input type="hidden" name="last_name" value="<%= request.getAttribute("lastName") %>">
                <input type="hidden" name="first_name" value="<%= request.getAttribute("firstName") %>">
            </tr>
            <tr>
                <td>氏名（ローマ字）</td>
                <td><%= request.getAttribute("alphabetLastName") %> <%= request.getAttribute("alphabetFirstName") %></td>
                <input type="hidden" name="alphabet_last_name" value="<%= request.getAttribute("alphabetLastName") %>">
                <input type="hidden" name="alphabet_first_name" value="<%= request.getAttribute("alphabetFirstName") %>">
            </tr>
            <tr>
                <td>生年月日</td>
                <td><%= request.getAttribute("birthday") %></td>
                <input type="hidden" name="birthday" value="<%= request.getAttribute("birthday") %>">
            </tr>
            <tr>
                <td>入社年月日</td>
                <td><%= request.getAttribute("hireDate") %></td>
                <input type="hidden" name="hire_date" value="<%= request.getAttribute("hireDate") %>">
            </tr>
            <tr>
                <td>部署</td>
                <td><%= request.getAttribute("department") %></td>
                <input type="hidden" name="department" value="<%= request.getAttribute("department") %>">
            </tr>
        </table>
        <div>
            
            <button type="button" onclick="location.href='<%= request.getContextPath() %>/register/input'">内容を修正する</button>
            <button type="submit">登録する</button>
            
        </div>
    </form>
</body>
</html>