<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除完了画面</title>
</head>
<body>
	<p>社員情報の削除が完了しました。</p>
	<button onclick="window.location.href='<%= request.getContextPath() %>/list'">一覧表示画面に戻る</button>
</body>
</html>