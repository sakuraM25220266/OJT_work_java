<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新確認画面</title>
<script src="<%=request.getContextPath()%>/JavaScript/ButtonAction.js"></script>
</head>
<body>
	<%
	String errorMessage = (String) request.getAttribute("errorMessage");
	if (errorMessage != null && !errorMessage.isEmpty()) {
	%>
	<span class="errorMessage" style="color: red; font-weight: bold;">
		<%=errorMessage%>
	</span>
	<%
	}
	%>

	<p>下記内容でよろしければ、「登録する」ボタンをクリックしてください。</p>
	<form method="post" action="?">
		<table>
			<tr>
				<td>社員番号</td>
				<td><%=request.getAttribute("employeeNo")%></td>
				<input type="hidden" name="employeeNo" value="<%=request.getAttribute("employeeNo")%>">
			</tr>
			<tr>
				<td>氏名（漢字）</td>
				<td><%=request.getAttribute("lastName")%> <%=request.getAttribute("firstName")%></td>
				<input type="hidden" name="lastName" value="<%=request.getAttribute("lastName")%>">
				<input type="hidden" name="firstName" value="<%=request.getAttribute("firstName")%>">
			</tr>
			<tr>
				<td>氏名（ローマ字）</td>
				<td><%=request.getAttribute("alphabetLastName")%> <%=request.getAttribute("alphabetFirstName")%></td>
				<input type="hidden" name="alphabetLastName" value="<%=request.getAttribute("alphabetLastName")%>">
				<input type="hidden" name="alphabetFirstName" value="<%=request.getAttribute("alphabetFirstName")%>">
			</tr>
			<tr>
				<td>生年月日</td>
				<td><%=request.getAttribute("birthday")%></td>
				<input type="hidden" name="birthday" value="<%=request.getAttribute("birthday")%>">
			</tr>
			<tr>
				<td>入社年月日</td>
				<td><%=request.getAttribute("hireDate")%></td>
				<input type="hidden" name="hireDate" value="<%=request.getAttribute("hireDate")%>">
			</tr>
			<tr>
				<td>部署</td>
				<td><%=request.getAttribute("department")%></td>
				<input type="hidden" name="department" value="<%=request.getAttribute("department")%>">
			</tr>
		</table>
		<div>
			<button type="submit" formaction="<%=request.getContextPath()%>/update/input">内容を修正する</button>
			<button type="submit" id="submitButton" onsubmit="disableSubmitButton()" formaction="<%=request.getContextPath()%>/update/complete">更新する</button>
		</div>
	</form>
</body>
</html>