package jp.co.sfrontier.ojt.employee.servlet.register;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.sfrontier.ojt.employee.db.dao.EmployeeDao;
import jp.co.sfrontier.ojt.employee.servlet.validator.InputValidator;

/**
 * 社員方法の登録確認を行うサーブレットクラス
 */
@WebServlet("/register/confirm")
public class RegisterConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");

		//入力画面で入力された値を取得する
		String empNoStr = request.getParameter("empNo");
		String lastName = request.getParameter("lastName");
		String firstName = request.getParameter("firstName");
		String alphabetLastName = request.getParameter("alphabetLastName");
		String alphabetFirstName = request.getParameter("alphabetFirstName");
		String birthdayStr = request.getParameter("birthday");
		String hireDateStr = request.getParameter("hireDate");
		String department = request.getParameter("department");

		// セッションに保存
		HttpSession session = request.getSession();
		session.setAttribute("empNo", empNoStr);
		session.setAttribute("lastName", lastName);
		session.setAttribute("firstName", firstName);
		session.setAttribute("alphabetLastName", alphabetLastName);
		session.setAttribute("alphabetFirstName", alphabetFirstName);
		session.setAttribute("birthday", birthdayStr);
		session.setAttribute("hireDate", hireDateStr);
		session.setAttribute("department", department);

		// バリデーション処理
		InputValidator validator = new InputValidator();
		String empNoError = validator.validateEmpNo(empNoStr);
		int empNo = 0;
		if (empNoError == null) {
			empNo = Integer.parseInt(request.getParameter("empNo"));
			EmployeeDao employeeDao = new EmployeeDao();
			try {
				if (employeeDao.isEmpNoExists(empNo)) {
					empNoError = "すでに登録されている社員番号です。";
				}
			} catch (SQLException e) {
				throw new ServletException(e);
			}
		}

		String lastNameError = validator.validateLastName(lastName);
		String firstNameError = validator.validateFirstName(firstName);
		String alphabetLastNameError = validator.validateAlphabetLastName(alphabetLastName);
		String alphabetFirstNameError = validator.validateAlphabetFirstName(alphabetFirstName);
		//JSPの「input type="date"」の仕様上、バリデーションエラーを発生させることができないため、生年月日と入社年月日のバリデーションチェックは行わない。
		String departmentError = validator.validateDepartment(department);

		boolean hasError = false;
		// エラーメッセージがあればリクエストにセット
		if (empNoError != null) {
			request.setAttribute("empNoError", empNoError);
			hasError = true;
		}
		if (lastNameError != null) {
			request.setAttribute("lastNameError", lastNameError);
			hasError = true;
		}
		if (firstNameError != null) {
			request.setAttribute("firstNameError", firstNameError);
			hasError = true;
		}
		if (alphabetLastNameError != null) {
			request.setAttribute("alphabetLastNameError", alphabetLastNameError);
			hasError = true;
		}
		if (alphabetFirstNameError != null) {
			request.setAttribute("alphabetFirstNameError", alphabetFirstNameError);
			hasError = true;
		}
		if (departmentError != null) {
			request.setAttribute("departmentError", departmentError);
			hasError = true;
		}

		// エラーがあった場合は入力画面に戻す
		if (hasError) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/register/RegisterInput.jsp");
			dispatcher.forward(request, response);
			return;
		}

		// エラーがなければ確認画面に渡す値をセットし、確認画面に遷移
		request.setAttribute("empNo", empNoStr);
		request.setAttribute("lastName", lastName);
		request.setAttribute("firstName", firstName);
		request.setAttribute("alphabetLastName", alphabetLastName);
		request.setAttribute("alphabetFirstName", alphabetFirstName);
		request.setAttribute("birthday", birthdayStr);
		request.setAttribute("hireDate", hireDateStr);
		request.setAttribute("department", department);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/register/RegisterConfirm.jsp");
		dispatcher.forward(request, response);
	}
}
