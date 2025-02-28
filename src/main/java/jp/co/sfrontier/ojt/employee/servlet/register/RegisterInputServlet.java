package jp.co.sfrontier.ojt.employee.servlet.register;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * 新規社員登録機能の入力画面を表示するサーブレットクラス
 */
@WebServlet("/register/input")
public class RegisterInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String ref = request.getHeader("REFERER");
		String urlRegex = ".*/register/confirm$";
		if(!ref.matches(urlRegex)) {
			///register/confirm以外のURLからアクセスされたとき、セッションの入力情報を削除する
			HttpSession session = request.getSession();
	        session.removeAttribute("registerEmployeeNo");
	        session.removeAttribute("registerLastName");
	        session.removeAttribute("registerFirstName");
	        session.removeAttribute("registerAlphabetLastName");
	        session.removeAttribute("registerAlphabetFirstName");
	        session.removeAttribute("registerBirthday");
	        session.removeAttribute("registerHireDate");
	        session.removeAttribute("registerDepartment");
		}
        
		//登録機能の入力画面を表示する
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/register/RegisterInput.jsp");
		dispatcher.forward(request, response);
	}
}
