package jp.co.sfrontier.ojt.employee.servlet.register;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterInputServlet
 */
@WebServlet("/register/input")
public class RegisterInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		//登録機能の入力画面を表示する
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/register/RegisterInput.jsp");
		dispatcher.forward(request, response);
	}
}
