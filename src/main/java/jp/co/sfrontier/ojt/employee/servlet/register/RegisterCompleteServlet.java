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
 * Servlet implementation class RegisterCompleteServlet
 */
@WebServlet("/register/complete")
public class RegisterCompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//入力画面で入力された値を取得する
		int empNo = Integer.parseInt(request.getParameter("emp_no"));
		String lastName = request.getParameter("last_name");
		String firstName = request.getParameter("first_name");
		String alphabetLastName = request.getParameter("alphabet_last_name");
		String alphabetFirstName = request.getParameter("alphabet_first_name");
		Date birthday = null;
		String birthdayStr = request.getParameter("birthday");
		if(birthdayStr != "") {
			birthday = Date.valueOf(birthdayStr);
		}
		Date hireDate = null;
		String hireDateStr = request.getParameter("hire_date");
		if(hireDateStr != "") {
			hireDate = Date.valueOf(request.getParameter("hire_date"));
		}
		String department = request.getParameter("department");
		
		//RegisterServiceを使って社員情報をDBに登録
		
		RegisterService service = new RegisterService();
		EmployeeEntity employee = new EmployeeEntity(empNo, lastName, firstName, alphabetLastName, alphabetFirstName, birthday, hireDate, department);
		boolean isSuccess = service.registerNewEmployee(employee);
		
		if (isSuccess) {
			//DB登録に成功したとき、登録機能の完了画面を表示する
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/register/RegisterComplete.jsp");
			dispatcher.forward(request, response);
		}else {
			//DB登録に失敗したとき、確認画面にエラーメッセージを表示する
			request.setAttribute("errorMessage", "エラーが発生したため登録できませんでした。もう一度お試しください。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/RegisterConfirm.jsp");
            dispatcher.forward(request, response);
		}
	}
	
	 // POSTリクエストに対応
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // POSTリクエストはGETメソッドと同じ処理を行う場合
        doGet(request, response);
    }

}
