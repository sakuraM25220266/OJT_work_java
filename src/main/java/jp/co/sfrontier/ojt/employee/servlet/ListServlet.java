package jp.co.sfrontier.ojt.employee.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.sfrontier.ojt.employee.db.entity.EmployeeEntity;
import jp.co.sfrontier.ojt.employee.db.entity.SearchConditionEntity;
import jp.co.sfrontier.ojt.employee.service.list.ListService;
import jp.co.sfrontier.ojt.employee.servlet.validator.SearchValidator;

@WebServlet("/list")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ListService listService = new ListService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		try {
			request.setCharacterEncoding("UTF-8");

			//各機能の完了画面から一覧表示画面に遷移したときは、1つ前の検索結果を表示させる
			String ref = request.getHeader("REFERER");
			String urlRegex = ".*/(complete|input|delete/confirm)$";
			if (ref != null && ref.matches(urlRegex)) {

				// セッションから検索条件を取得する
				String employeeNoStr = (String) session.getAttribute("searchEmployeeNo");
				String lastName = (String) session.getAttribute("searchLastName");
				String firstName = (String) session.getAttribute("searchFirstName");
				String alphabetLastName = (String) session.getAttribute("searchAlphabetLastName");
				String alphabetFirstName = (String) session.getAttribute("searchAlphabetFirstName");
				String birthdayFromStr = (String) session.getAttribute("searchBirthdayFrom");
				String birthdayToStr = (String) session.getAttribute("searchBirthdayTo");
				String hireDateFromStr = (String) session.getAttribute("searchHireDateFrom");
				String hireDateToStr = (String) session.getAttribute("searchHireDateTo");
				String department = (String) session.getAttribute("searchDepartment");
				
				//型変換を行う
				int employeeNo = -1;
				if (employeeNoStr != null && !employeeNoStr.isEmpty()) {
					employeeNo = Integer.parseInt(employeeNoStr);
				}
				
				Date birthdayFrom = null;
				if (birthdayFromStr != null && !birthdayFromStr.isEmpty()) {
					birthdayFrom = Date.valueOf(birthdayFromStr);
				}

				Date birthdayTo = null;
				if (birthdayToStr != null && !birthdayToStr.isEmpty()) {
					birthdayTo = Date.valueOf(birthdayToStr);
				}

				Date hireDateFrom = null;
				if (hireDateFromStr != null && !hireDateFromStr.isEmpty()) {
					hireDateFrom = Date.valueOf(hireDateFromStr);
				}

				Date hireDateTo = null;
				if (hireDateToStr != null && !hireDateToStr.isEmpty()) {
					hireDateTo = Date.valueOf(hireDateToStr);
				}

				// 検索条件を使って検索を実行
				SearchConditionEntity searchCondition = new SearchConditionEntity(employeeNo, lastName, firstName,
						alphabetLastName, alphabetFirstName, birthdayFrom, birthdayTo, hireDateFrom, hireDateTo,
						department);

				// 社員情報を取得する
				List<EmployeeEntity> employees = listService.searchEmployees(searchCondition);
				request.setAttribute("employees", employees);

				// 一覧表示画面を表示
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/List.jsp");
				dispatcher.forward(request, response);
				return;
			}

			//入力画面で入力された値を取得する
			String employeeNoStr = request.getParameter("employeeNo");
			String lastName = request.getParameter("lastName");
			String firstName = request.getParameter("firstName");
			String alphabetLastName = request.getParameter("alphabetLastName");
			String alphabetFirstName = request.getParameter("alphabetFirstName");
			String birthdayFromStr = request.getParameter("birthdayFrom");
			String birthdayToStr = request.getParameter("birthdayTo");
			String hireDateFromStr = request.getParameter("hireDateFrom");
			String hireDateToStr = request.getParameter("hireDateTo");
			String department = request.getParameter("department");

			// セッションに保存する
			session.setAttribute("searchEmployeeNo", employeeNoStr);
			session.setAttribute("searchLastName", lastName);
			session.setAttribute("searchFirstName", firstName);
			session.setAttribute("searchAlphabetLastName", alphabetLastName);
			session.setAttribute("searchAlphabetFirstName", alphabetFirstName);
			session.setAttribute("searchBirthdayFrom", birthdayFromStr);
			session.setAttribute("searchBirthdayTo", birthdayToStr);
			session.setAttribute("searchHireDateFrom", hireDateFromStr);
			session.setAttribute("searchHireDateTo", hireDateToStr);
			session.setAttribute("searchDepartment", department);

			//バリデーションチェックを行う
			SearchValidator validator = new SearchValidator();
			String employeeNoError = validator.validateEmployeeNo(employeeNoStr);
			String lastNameError = validator.validateLastName(lastName);
			String firstNameError = validator.validateFirstName(firstName);
			String alphabetLastNameError = validator.validateAlphabetLastName(alphabetLastName);
			String alphabetFirstNameError = validator.validateAlphabetFirstName(alphabetFirstName);

			//birthdayの型を変換し、バリデーションチェックを行う
			Date birthdayFrom = null;
			Date birthdayTo = null;
			if (birthdayFromStr != null && !birthdayFromStr.isEmpty()) {
				birthdayFrom = Date.valueOf(birthdayFromStr);
			}
			if (birthdayToStr != null && !birthdayToStr.isEmpty()) {
				birthdayTo = Date.valueOf(birthdayToStr);
			}
			String birthdayError = validator.validateDate(birthdayFrom, birthdayTo);

			//hireDateの型を変換し、バリデーションチェックを行う
			Date hireDateFrom = null;
			Date hireDateTo = null;
			if (hireDateFromStr != null && !hireDateFromStr.isEmpty()) {
				hireDateFrom = Date.valueOf(hireDateFromStr);
			}
			if (hireDateToStr != null && !hireDateToStr.isEmpty()) {
				hireDateTo = Date.valueOf(hireDateToStr);
			}
			String hireDateError = validator.validateDate(hireDateFrom, hireDateTo);

			String departmentError = validator.validateDepartment(department);

			boolean hasError = false;
			// エラーメッセージがあればリクエストにセット
			if (employeeNoError != null) {
				request.setAttribute("employeeNoError", employeeNoError);
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
			if (birthdayError != null) {
				request.setAttribute("birthdayError", birthdayError);
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

			//エラーが発生した場合は、前回の検索結果（employees）をセッションから取得し、リクエストにセットする
			if (hasError) {
				List<EmployeeEntity> employees = (List<EmployeeEntity>) session.getAttribute("employees");
				request.setAttribute("employees", employees);

				//エラーがあった場合は一覧表示画面に戻す
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/List.jsp");
				dispatcher.forward(request, response);
				return;
			}

			//employeeNoの型変換を行う
			int employeeNo = -1;
			if (employeeNoStr != null && !employeeNoStr.isEmpty()) {
				employeeNo = Integer.parseInt(employeeNoStr);
			}

			// SearchConditionEntityを使って検索条件をセット
			SearchConditionEntity searchCondition = new SearchConditionEntity(employeeNo, lastName, firstName,
					alphabetLastName, alphabetFirstName, birthdayFrom, birthdayTo, hireDateFrom, hireDateTo,
					department);

			// 社員情報を取得する
			List<EmployeeEntity> employees = listService.searchEmployees(searchCondition);

			// 社員情報リストをセッションとリクエストスコープにセット
			session.setAttribute("employees", employees);
			request.setAttribute("employees", employees);

			//一覧表示画面を表示する
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/List.jsp");
			dispatcher.forward(request, response);

		} catch (IOException | ServletException | RuntimeException e) {
			e.printStackTrace();
		}
	}
}
