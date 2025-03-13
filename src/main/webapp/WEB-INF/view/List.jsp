<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="jp.co.sfrontier.ojt.employee.db.entity.EmployeeEntity" %>
<%
	String employeeNo = (String) request.getAttribute("employeeNo");
	if (employeeNo == null) {
		employeeNo = "";
	}
	
	String lastName = (String) request.getAttribute("lastName");
	if (lastName == null) {
		lastName = "";
	}
	
	String firstName = (String) request.getAttribute("firstName");
	if (firstName == null) {
		firstName = "";
	}
	
	String alphabetLastName = (String) request.getAttribute("alphabetLastName");
	if (alphabetLastName == null) {
		alphabetLastName = "";
	}
	
	String alphabetFirstName = (String) request.getAttribute("alphabetFirstName");
	if (alphabetFirstName == null) {
		alphabetFirstName = "";
	}
	
	String birthdayFrom = (String) request.getAttribute("birthdayFrom");
	if (birthdayFrom == null) {
		birthdayFrom = "";
	}
	
	String birthdayTo = (String) request.getAttribute("birthdayTo");
	if (birthdayTo == null) {
		birthdayTo = "";
	}
	
	String hireDateFrom = (String) request.getAttribute("hireDateFrom");
	if (hireDateFrom == null) {
		hireDateFrom = "";
	}
	
	String hireDateTo = (String) request.getAttribute("hireDateTo");
	if (hireDateTo == null) {
		hireDateTo = "";
	}
	
	String department = (String) request.getAttribute("department");
	if (department == null) {
		department = "";
		
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>一覧表示画面</title>
<script src="<%=request.getContextPath()%>/JavaScript/ResetForm.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/employeeapp.css">

</head>
<body>

<!-- 検索フォーム -->
<div class="search-form">
<form action="<%=request.getContextPath()%>/list" method="get" name="searchEmployee">
    <label for="employeeNo">社員番号</label>
    <input type="text" id="employeeNo" name="employeeNo" value="<%= employeeNo %>">
    <%
        String employeeNoError = (String) request.getAttribute("employeeNoError");
        if (employeeNoError != null) {
    %>
        <br>
        <span class="error-message"><%=employeeNoError%></span>
    <%
        }
    %>

    <br>
    <label for="lastName">姓 (漢字)</label>
    <input type="text" id="lastName" name="lastName" value="<%= lastName %>">
    <label for="firstName">名 (漢字)</label>
    <input type="text" id="firstName" name="firstName" value="<%= firstName %>">
    <%
        String lastNameError = (String) request.getAttribute("lastNameError");
        if (lastNameError != null) {
    %>
        <br>
        <span class="error-message"><%=lastNameError%></span>
    <%
        }

        String firstNameError = (String) request.getAttribute("firstNameError");
        if (firstNameError != null) {
    %>
        <span class="error-message"><%=firstNameError%></span>
    <%
        }
    %>

    <br>
    <label for="alphabetLastName">姓 (ローマ字)</label>
    <input type="text" id="alphabetLastName" name="alphabetLastName" value="<%= alphabetLastName %>">
    <label for="alphabetFirstName">名 (ローマ字)</label>
    <input type="text" id="alphabetFirstName" name="alphabetFirstName" value="<%= alphabetFirstName %>">
    <%
        String alphabetLastNameError = (String) request.getAttribute("alphabetLastNameError");
        if (alphabetLastNameError != null) {
    %>
        <br>
        <span class="error-message"><%=alphabetLastNameError%></span>
    <%
        }

        String alphabetFirstNameError = (String) request.getAttribute("alphabetFirstNameError");
        if (alphabetFirstNameError != null) {
    %>
        <span class="error-message"><%=alphabetFirstNameError%></span>
    <%
        }
    %>

    <br>
    <label for="birthdayFrom">生年月日</label>
    <input type="date" id="birthdayFrom" name="birthdayFrom" value="<%= birthdayFrom %>" min="1900-01-01" max="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>">
    <span>～</span>
    <input type="date" id="birthdayTo" name="birthdayTo" value="<%= birthdayTo %>" min="1900-01-01" max="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>">
    <%
        String birthdayError = (String) request.getAttribute("birthdayError");
        if (birthdayError != null) {
    %>
        <br>
        <span class="error-message"><%=birthdayError%></span>
    <%
        }
    %>

    <br>
    <label for="hireDateFrom">入社年月日</label>
    <input type="date" id="hireDateFrom" name="hireDateFrom" value="<%= hireDateFrom %>" min="1900-01-01" max="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>">
    <span>～</span>
    <input type="date" id="hireDateTo" name="hireDateTo" value="<%= birthdayTo %>" min="1900-01-01" max="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>">
    <%
        String hireDateError = (String) request.getAttribute("hireDateError");
        if (hireDateError != null) {
    %>
        <br>
        <span class="error-message"><%=hireDateError%></span>
    <%
        }
    %>

    <br>
    <label for="department">部署</label>
    <input type="text" id="department" name="department" value="<%= department %>">
    <%
        String departmentError = (String) request.getAttribute("departmentError");
        if (departmentError != null) {
    %>
        <br>
        <span class="error-message"><%=departmentError%></span>
    <%
        }
    %>

    <button type="submit">検索</button>
    <button type="button" onclick="resetForm()">クリア</button>
</form>
</div>

<br>
<!-- 新規登録ボタン -->
<a href="<%=request.getContextPath()%>/register/input"><button class="register-button">新規登録</button></a>

<!-- 社員情報テーブル -->
<div class="scroll-container">
    <table>
        <thead>
            <tr>
                <th>社員番号</th>
                <th>氏名 (漢字)</th>
                <th>氏名 (ローマ字)</th>
                <th>生年月日</th>
                <th>入社年月日</th>
                <th>部署</th>
                <th>更新</th>
                <th>削除</th>
            </tr>
        </thead>
        <tbody>
            <%
                // リクエストスコープから社員情報リストを取得
                List<EmployeeEntity> employees = (List<EmployeeEntity>) request.getAttribute("employees");
                if (employees != null) {
                    // 社員情報リストをループして表示
                    for (EmployeeEntity employee : employees) {
            %>
            <tr>
                <td><%=employee.getEmployeeNo()%></td>
                <td><%=employee.getLastName() + " " + employee.getFirstName()%></td>
                <td><%=employee.getAlphabetLastName() + " " + employee.getAlphabetFirstName()%></td>
                <td>
                    <%
                        if (employee.getBirthday() != null) {
                    %>
                        <%=employee.getBirthday()%>
                    <%
                        } else {
                    %>
                        <%=""%>
                    <%
                        }
                    %>
                </td>

                <td>
                    <%
                        if (employee.getHireDate() != null) {
                    %>
                        <%=employee.getHireDate()%>
                    <%
                        } else {
                    %>
                        <%=""%>
                    <%
                        }
                    %>
                </td>
                <td><%=employee.getDepartment()%></td>
                <td>
                	<form action="<%=request.getContextPath()%>/update/input" method="post">
                		<input type="hidden" name="employeeNo" value="<%=employee.getEmployeeNo() %>">
                		<button class="update-delete">更新</button>
                	</form>
                </td>
                <td>
                	<form action="<%=request.getContextPath()%>/delete/confirm" method="post">
                		<input type="hidden" name="employeeNo" value="<%=employee.getEmployeeNo() %>">
                		<button class="update-delete">削除</button>
                	</form>
                </td>
            </tr>
            <%
                }
            }
            %>
        </tbody>
    </table>
</div>

</body>
</html>
