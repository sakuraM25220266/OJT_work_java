package jp.co.sfrontier.ojt.employee.servlet.delete;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/delete/confirm")
public class DeleteConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		//削除機能の確認画面を表示する
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/delete/DeleteConfirm.jsp");
		dispatcher.forward(request, response);
	}

}
