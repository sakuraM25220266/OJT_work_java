package jp.co.sfrontier.ojt.employee.servlet.update;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.sfrontier.ojt.employee.servlet.validator.InputValidator;

/**
 * 社員情報の更新確認を行うサーブレットクラス
 */
@WebServlet("/update/confirm")
public class UpdateConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//入力画面で入力された値を取得する
		String employeeNoStr = request.getParameter("employeeNo");
		String lastName = request.getParameter("lastName");
		String firstName = request.getParameter("firstName");
		String alphabetLastName = request.getParameter("alphabetLastName");
		String alphabetFirstName = request.getParameter("alphabetFirstName");
		String birthdayStr = request.getParameter("birthday");
		String hireDateStr = request.getParameter("hireDate");
		String department = request.getParameter("department");

		// セッションに保存
		HttpSession session = request.getSession();
		session.setAttribute("updateEmployeeNo", employeeNoStr);
		session.setAttribute("updateLastName", lastName);
		session.setAttribute("updateFirstName", firstName);
		session.setAttribute("updateAlphabetLastName", alphabetLastName);
		session.setAttribute("updateAlphabetFirstName", alphabetFirstName);
		session.setAttribute("updateBirthday", birthdayStr);
		session.setAttribute("updateHireDate", hireDateStr);
		session.setAttribute("updateDepartment", department);

		// バリデーション処理
		InputValidator validator = new InputValidator();
		String lastNameError = validator.validateLastName(lastName);
		String firstNameError = validator.validateFirstName(firstName);
		String alphabetLastNameError = validator.validateAlphabetLastName(alphabetLastName);
		String alphabetFirstNameError = validator.validateAlphabetFirstName(alphabetFirstName);
		//JSPの「input type="date"」の仕様上、バリデーションエラーを発生させることができないため、生年月日と入社年月日のバリデーションチェックは行わない。
		String departmentError = validator.validateDepartment(department);

		boolean hasError = false;
		// エラーメッセージがあればリクエストにセット
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/update/UpdateInput.jsp");
			dispatcher.forward(request, response);
			return;
		}

		// エラーがなければ確認画面に渡す値をセットし、確認画面に遷移
		request.setAttribute("employeeNo", employeeNoStr);
		request.setAttribute("lastName", lastName);
		request.setAttribute("firstName", firstName);
		request.setAttribute("alphabetLastName", alphabetLastName);
		request.setAttribute("alphabetFirstName", alphabetFirstName);
		request.setAttribute("birthday", birthdayStr);
		request.setAttribute("hireDate", hireDateStr);
		request.setAttribute("department", department);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/update/UpdateConfirm.jsp");
		dispatcher.forward(request, response);
	}
}