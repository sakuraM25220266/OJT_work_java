package jp.co.sfrontier.ojt.employee.servlet.update;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.sfrontier.ojt.employee.db.entity.EmployeeEntity;
import jp.co.sfrontier.ojt.employee.service.update.UpdateService;

/**
 * 社員情報の更新処理を完了させるサーブレットクラス
 */
@WebServlet("/update/complete")
public class UpdateCompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		//入力画面で入力された値を取得する
		int employeeNo = Integer.parseInt(request.getParameter("employeeNo"));
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

		//UpdateServiceを使って社員情報を更新する
		UpdateService service = new UpdateService();
		EmployeeEntity employee = new EmployeeEntity(employeeNo, lastName, firstName, alphabetLastName,
				alphabetFirstName, birthday, hireDate, department);
		boolean isSuccess = service.updateEmployee(employee);

		if (isSuccess) {
			//DB登録に成功したとき、登録機能の完了画面を表示する
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/update/UpdateComplete.jsp");
			dispatcher.forward(request, response);
		} else {
			//DB登録に失敗したとき、確認画面に値をセットし、エラーメッセージを表示する
			request.setAttribute("employeeNo", employeeNo);
			request.setAttribute("lastName", lastName);
			request.setAttribute("firstName", firstName);
			request.setAttribute("alphabetLastName", alphabetLastName);
			request.setAttribute("alphabetFirstName", alphabetFirstName);
			request.setAttribute("birthday", birthdayStr);
			request.setAttribute("hireDate", hireDateStr);
			request.setAttribute("department", department);
			request.setAttribute("errorMessage", "エラーが発生したため登録できませんでした。もう一度お試しください。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/update/UpdateConfirm.jsp");
			dispatcher.forward(request, response);
		}
	}

}
