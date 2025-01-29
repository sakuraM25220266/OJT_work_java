package jp.co.sfrontier.ojt.employee.servlet.register;

import java.io.IOException;
import java.sql.Date;
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
 * Servlet implementation class RegisterConfirmServlet
 */
@WebServlet("/register/confirm")
public class RegisterConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");

		//入力画面で入力された値を取得する
		String empNoStr = request.getParameter("emp_no");
		String lastName = request.getParameter("last_name");
		String firstName = request.getParameter("first_name");
		String alphabetLastName = request.getParameter("alphabet_last_name");
		String alphabetFirstName = request.getParameter("alphabet_first_name");
		Date birthday = null;
		String birthdayStr = request.getParameter("birthday");
		if (birthdayStr != "") {
			birthday = Date.valueOf(birthdayStr);
		}
		Date hireDate = null;
		String hireDateStr = request.getParameter("hire_date");
		if (hireDateStr != "") {
			hireDate = Date.valueOf(request.getParameter("hire_date"));
		}
		String department = request.getParameter("department");

		// セッションに保存
		HttpSession session = request.getSession();
		session.setAttribute("empNo", empNoStr);
		session.setAttribute("lastName", lastName);
		session.setAttribute("firstName", firstName);
		session.setAttribute("alphabetLastName", alphabetLastName);
		session.setAttribute("alphabetFirstName", alphabetFirstName);
		session.setAttribute("birthday", birthday);
		session.setAttribute("hireDate", hireDate);
		session.setAttribute("department", department);

		// バリデーション処理
		InputValidator validator = new InputValidator();
		String empNoError = validator.validateEmpNo(empNoStr);
		int empNo = 0;
		if (empNoError == null) {
			empNo = Integer.parseInt(request.getParameter("emp_no"));
			EmployeeDao employeeDao = new EmployeeDao();
			try {
				if (employeeDao.isEmpNoExists(empNo)) {
					empNoError = "すでに登録されている社員番号です。";
				}
			} catch (SQLException e) {
				throw new ServletException(e);
			}
		}

		String nameError = validator.validateName(lastName, firstName);
		String alphabetNameError = validator.validateAlphabetName(alphabetLastName, alphabetFirstName);
		String birthdayError = validator.validateDate(birthday);
		String hireDateError = validator.validateDate(hireDate);
		String departmentError = validator.validateDepartment(department);

		boolean hasError = false;
		// エラーメッセージがあればリクエストにセット
		if (empNoError != null) {
			request.setAttribute("empNoError", empNoError);
			hasError = true;
		}
		if (nameError != null) {
			request.setAttribute("nameError", nameError);
			hasError = true;
		}
		if (alphabetNameError != null) {
			request.setAttribute("alphabetNameError", alphabetNameError);
			hasError = true;
		}
		if (birthdayError != null) {
			request.setAttribute("dateError", birthdayError);
			hasError = true;
		}
		if (hireDateError != null) {
			request.setAttribute("hireDateError", hireDateError);
			hasError = true;
		}
		if (departmentError != null) {
			request.setAttribute("departmentError", departmentError);
			hasError = true;
		}

		request.setAttribute("empNo", empNo);
		request.setAttribute("lastName", lastName);
		request.setAttribute("firstName", firstName);
		request.setAttribute("alphabetLastName", alphabetLastName);
		request.setAttribute("alphabetFirstName", alphabetFirstName);

		// birthday が null の場合、空文字をセット
		if (birthday == null) {
			request.setAttribute("birthday", "");
		} else {
			request.setAttribute("birthday", birthday);
		}

		// hireDate が null の場合、空文字をセット
		if (hireDate == null) {
			request.setAttribute("hireDate", "");
		} else {
			request.setAttribute("hireDate", hireDate);
		}
		request.setAttribute("department", department);

		// エラーがあった場合は入力画面に戻す
		if (hasError) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/register/RegisterInput.jsp");
			dispatcher.forward(request, response);
			return;
		}

		// 確認画面に遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/register/RegisterConfirm.jsp");
		dispatcher.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		//登録機能の確認画面を表示する
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/register/RegisterConfirm.jsp");
		dispatcher.forward(request, response);
	}
}
