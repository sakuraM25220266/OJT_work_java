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
import jp.co.sfrontier.ojt.employee.service.list.ListService;

/**
 * 更新機能の入力画面を表示するサーブレットクラス
 */
@WebServlet("/update/input")
public class UpdateInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		//一覧表示画面から更新を行う社員番号を取得する
		String employeeNoForUpdate = request.getParameter("employeeNoForUpdate");

		// データベースから社員番号をもとに社員情報を取得する
		ListService service = new ListService();
		EmployeeEntity employee = service.getEmployeeByNo(employeeNoForUpdate);

		// 取得した社員情報をセッションに保存する
		//employeeNoをString型に変換する
		int employeeNo = employee.getEmployeeNo();
		String employeeNoStr = String.valueOf(employeeNo);
		session.setAttribute("updateEmployeeNo", employeeNoStr);

		//birthdayとhireDateをString型に変換する
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date birthday = employee.getBirthday();
		if (birthday != null) {
			String birthdayStr = dateFormat.format(birthday);
			session.setAttribute("updateBirthday", birthdayStr);
		} else {
			session.setAttribute("updateBirthday", birthday);
		}

		Date hireDate = employee.getHireDate();
		if (hireDate != null) {
			String hireDateStr = dateFormat.format(hireDate);
			session.setAttribute("updateHireDate", hireDateStr);
		} else {
			session.setAttribute("updateHireDate", hireDate);
		}

		session.setAttribute("updateLastName", employee.getLastName());
		session.setAttribute("updateFirstName", employee.getFirstName());
		session.setAttribute("updateAlphabetLastName", employee.getAlphabetLastName());
		session.setAttribute("updateAlphabetFirstName", employee.getAlphabetFirstName());
		session.setAttribute("updateDepartment", employee.getDepartment());

		//更新機能の入力画面を表示する
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/update/UpdateInput.jsp");
		dispatcher.forward(request, response);
	}
}
