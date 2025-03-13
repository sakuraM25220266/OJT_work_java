package jp.co.sfrontier.ojt.employee.servlet.delete;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.sfrontier.ojt.employee.db.entity.EmployeeEntity;
import jp.co.sfrontier.ojt.employee.service.list.ListService;

/**
 * 社員情報の削除確認を行うサーブレットクラス
 */
@WebServlet("/delete/confirm")
public class DeleteConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		//一覧表示画面から削除を行う社員番号を取得する
		String employeeNoFromList = request.getParameter("employeeNo");

		// データベースから社員番号をもとに社員情報を取得する
		ListService service = new ListService();
		EmployeeEntity employee = service.getEmployeeByNo(employeeNoFromList);

		//取得した社員情報をリクエストにセットする
		//employeeNoをString型に変換する
		int employeeNo = employee.getEmployeeNo();
		String employeeNoStr = String.valueOf(employeeNo);
		request.setAttribute("deleteEmployeeNo", employeeNoStr);

		//birthdayとhireDateをString型に変換する
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date birthday = employee.getBirthday();
		if (birthday != null) {
			String birthdayStr = dateFormat.format(birthday);
			request.setAttribute("deleteBirthday", birthdayStr);
		} else {
			request.setAttribute("deleteBirthday", birthday);
		}

		Date hireDate = employee.getHireDate();
		if (hireDate != null) {
			String hireDateStr = dateFormat.format(hireDate);
			request.setAttribute("deleteHireDate", hireDateStr);
		} else {
			request.setAttribute("deleteHireDate", hireDate);
		}

		request.setAttribute("deleteLastName", employee.getLastName());
		request.setAttribute("deleteFirstName", employee.getFirstName());
		request.setAttribute("deleteAlphabetLastName", employee.getAlphabetLastName());
		request.setAttribute("deleteAlphabetFirstName", employee.getAlphabetFirstName());
		request.setAttribute("deleteDepartment", employee.getDepartment());

		//削除機能の確認画面を表示する
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/delete/DeleteConfirm.jsp");
		dispatcher.forward(request, response);
	}

}
