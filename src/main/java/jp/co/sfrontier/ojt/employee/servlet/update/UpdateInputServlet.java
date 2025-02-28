package jp.co.sfrontier.ojt.employee.servlet.update;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.sfrontier.ojt.employee.db.entity.EmployeeEntity;
import jp.co.sfrontier.ojt.employee.service.update.UpdateService;

/**
 * 更新機能の入力画面を表示するサーブレットクラス
 */
@WebServlet("/update/input")
public class UpdateInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String ref = request.getHeader("REFERER");
		String urlRegex = ".*/list$";
		HttpSession session = request.getSession();

		//一覧表示画面から遷移した場合
		if (ref.matches(urlRegex)) {
			String employeeNoStr = request.getParameter("employeeNoForUpdate");

			// データベースから社員番号をもとに社員情報を取得する
			UpdateService service = new UpdateService();
			EmployeeEntity employee = service.getEmployeeByNo(employeeNoStr);

			String empNoStr = String.valueOf(employee.getEmployeeNo());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date test = employee.getBirthday();
			String birthdayStr = dateFormat.format(employee.getBirthday());
			String hireDateStr = dateFormat.format(employee.getHireDate());
			
			// 取得した社員情報をセッションに保存する
			session.setAttribute("updateEmployeeNo", empNoStr);
			session.setAttribute("updateLastName", employee.getLastName());
			session.setAttribute("updateFirstName", employee.getFirstName());
			session.setAttribute("updateAlphabetLastName", employee.getAlphabetLastName());
			session.setAttribute("updateAlphabetFirstName", employee.getAlphabetFirstName());
			session.setAttribute("updateBirthday", birthdayStr);
			session.setAttribute("updateHireDate", hireDateStr);
			session.setAttribute("updateDepartment", employee.getDepartment());

			// 取得した社員情報をリクエストスコープに保存
			request.setAttribute("employee", employee);
		}

		//更新機能の入力画面を表示する
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/update/UpdateInput.jsp");
		dispatcher.forward(request, response);
	}
}
