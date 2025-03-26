<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String employeeNo = (String) request.getAttribute("deleteEmployeeNo");
	if (employeeNo == null) {
		employeeNo = "";
	}

	String lastName = (String) request.getAttribute("deleteLastName");
	if (lastName == null) {
		lastName = "";
	}

	String firstName = (String) request.getAttribute("deleteFirstName");
	if (firstName == null) {
		firstName = "";
	}

	String alphabetLastName = (String) request.getAttribute("deleteAlphabetLastName");
	if (alphabetLastName == null) {
		alphabetLastName = "";
	}

	String alphabetFirstName = (String) request.getAttribute("deleteAlphabetFirstName");
	if (alphabetFirstName == null) {
		alphabetFirstName = "";
	}

	String birthday = (String) request.getAttribute("deleteBirthday");
	if (birthday == null) {
		birthday = "";
	}

	String hireDate = (String) request.getAttribute("deleteHireDate");
	if (hireDate == null) {
		hireDate = "";
	}

	String department = (String) request.getAttribute("deleteDepartment");
	if (department == null) {
		department = "";
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除確認画面</title>
<script src="<%=request.getContextPath()%>/JavaScript/ButtonAction.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/employeeapp.css">

</head>
<body>
	<%
	String errorMessage = (String) request.getAttribute("errorMessage");
	if (errorMessage != null && !errorMessage.isEmpty()) {
	%>
	<span class="error-message"><%=errorMessage%></span>
	<%
	}
	%>

	<p>下記内容を削除してもよろしければ、「削除する」ボタンをクリックしてください。</p>
	<form method="post" action="<%=request.getContextPath()%>/delete/complete">
		<table>
			<tr>
				<td>社員番号</td>
				<td><%=employeeNo%></td>
				<input type="hidden" name="employeeNo" value="<%=employeeNo%>">
			</tr>
			<tr>
				<td>氏名（漢字）</td>
				<td><%=lastName%> <%=firstName%></td>
				<input type="hidden" name="lastName" value="<%=lastName%>">
				<input type="hidden" name="firstName" value="<%=firstName%>">
			</tr>
			<tr>
				<td>氏名（ローマ字）</td>
				<td><%=alphabetLastName%> <%=alphabetFirstName%></td>
				<input type="hidden" name="alphabetLastName" value="<%=alphabetLastName%>">
				<input type="hidden" name="alphabetFirstName" value="<%=alphabetFirstName%>">
			</tr>
			<tr>
				<td>生年月日</td>
				<td><%=birthday%></td>
				<input type="hidden" name="birthday" value="<%=birthday%>">
			</tr>
			<tr>
				<td>入社年月日</td>
				<td><%=hireDate%></td>
				<input type="hidden" name="hireDate" value="<%=hireDate%>">
			</tr>
			<tr>
				<td>部署</td>
				<td><%=department%></td>
				<input type="hidden" name="department" value="<%=department%>">
			</tr>
		</table>
		<div>
			<button type="button" onclick="location.href='<%=request.getContextPath()%>/list'">キャンセル</button>
			<button type="submit" id="submitButton" onsubmit="disableSubmitButton()">削除する</button>
		</div>
	</form>
</body>
</html>