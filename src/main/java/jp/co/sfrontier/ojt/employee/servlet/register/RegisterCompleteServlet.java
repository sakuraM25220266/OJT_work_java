package jp.co.sfrontier.ojt.employee.servlet.register;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.sfrontier.ojt.employee.db.entity.EmployeeEntity;
import jp.co.sfrontier.ojt.employee.service.register.RegisterService;

/**
 * 社員情報の登録処理を完了させるサーブレットクラス
 */
@WebServlet("/register/complete")
public class RegisterCompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		//入力画面で入力された値を取得する
		int empNo = Integer.parseInt(request.getParameter("empNo"));
		String lastName = request.getParameter("lastName");
		String firstName = request.getParameter("firstName");
		String alphabetLastName = request.getParameter("alphabetLastName");
		String alphabetFirstName = request.getParameter("alphabetFirstName");
		Date birthday = null;
		String birthdayStr = request.getParameter("birthday");
		if (!birthdayStr.isEmpty()) {
			birthday = Date.valueOf(birthdayStr);
		}
		Date hireDate = null;
		String hireDateStr = request.getParameter("hireDate");
		if (!hireDateStr.isEmpty()) {
			hireDate = Date.valueOf(request.getParameter("hireDate"));
		}
		String department = request.getParameter("department");

		//RegisterServiceを使って社員情報をDBに登録する
		RegisterService service = new RegisterService();
		EmployeeEntity employee = new EmployeeEntity(empNo, lastName, firstName, alphabetLastName, alphabetFirstName,birthday, hireDate, department);
		boolean isSuccess = service.registerEmployee(employee);

		if (isSuccess) {
			//DB登録に成功したとき、登録機能の完了画面を表示する
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/register/RegisterComplete.jsp");
			dispatcher.forward(request, response);
		} else {
			//DB登録に失敗したとき、確認画面に値をセットし、エラーメッセージを表示する
			request.setAttribute("empNo", empNo);
		    request.setAttribute("lastName", lastName);
		    request.setAttribute("firstName", firstName);
		    request.setAttribute("alphabetLastName", alphabetLastName);
		    request.setAttribute("alphabetFirstName", alphabetFirstName);
		    request.setAttribute("birthday", birthdayStr);
		    request.setAttribute("hireDate", hireDateStr);
		    request.setAttribute("department", department);
			request.setAttribute("errorMessage", "エラーが発生したため登録できませんでした。もう一度お試しください。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/register/RegisterConfirm.jsp");
			dispatcher.forward(request, response);
		}
	}
}
