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

/**
 * 社員情報の一覧表示と検索を担当するサーブレットクラス<br>
 * 一覧表示画面に初回アクセスした時と、全件表示状態で他画面に遷移し一覧表示画面に戻って来た時は、社員情報を全件表示する。<br>
 * 検索フォームで社員情報の絞り込みを行い、他画面に遷移し一覧表示画面に戻って来た時は、絞り込まれた状態の社員情報を表示する。<br>
 * したがって、一覧表示画面を表示する際はセッションから検索条件を取得することで、新しく入力された検索条件またはひとつ前に検索したときの検索条件で社員情報の検索を行うようにしている。
 */
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

			//一覧表示画面以外から遷移した場合は、1つ前の検索条件で検索する
			///completeは登録・更新・削除の完了画面、/inputは登録・更新の入力画面、/delete/confirmは削除の確認画面
			String ref = request.getHeader("REFERER");
			String urlRegex = ".*/(complete|input|delete/confirm)$";
			if (ref != null && ref.matches(urlRegex)) {

				//セッションから復元した検索条件をリクエストにセットする
				request.setAttribute("employeeNo", session.getAttribute("searchEmployeeNo"));
				request.setAttribute("lastName", session.getAttribute("searchLastName"));
				request.setAttribute("firstName", session.getAttribute("searchFirstName"));
				request.setAttribute("alphabetLastName", session.getAttribute("searchAlphabetLastName"));
				request.setAttribute("alphabetFirstName", session.getAttribute("searchAlphabetFirstName"));
				request.setAttribute("birthdayFrom", session.getAttribute("searchBirthdayFrom"));
				request.setAttribute("birthdayTo", session.getAttribute("searchBirthdayTo"));
				request.setAttribute("hireDateFrom", session.getAttribute("searchHireDateFrom"));
				request.setAttribute("hireDateTo", session.getAttribute("searchHireDateTo"));
				request.setAttribute("department", session.getAttribute("searchDepartment"));

			} else {
				//一覧表示画面に新しくアクセスした場合と検索フォームで検索した場合は、初期値または入力された検索条件で検索する
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

				//エラーがなければセッションを更新する(エラーがあればセッションは1つ前の検索条件のまま)
				if (!hasError) {
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
				}

				//入力された検索条件をリクエストにセットする
				request.setAttribute("employeeNo", employeeNoStr);
				request.setAttribute("lastName", lastName);
				request.setAttribute("firstName", firstName);
				request.setAttribute("alphabetLastName", alphabetLastName);
				request.setAttribute("alphabetFirstName", alphabetFirstName);
				request.setAttribute("birthdayFrom", birthdayFromStr);
				request.setAttribute("birthdayTo", birthdayToStr);
				request.setAttribute("hireDateFrom", hireDateFromStr);
				request.setAttribute("hireDateTo", hireDateToStr);
				request.setAttribute("department", department);
			}
			//社員情報を取得する
			List<EmployeeEntity> employees = getEmployeesFromSession(session);

			// 社員情報リストをリクエストスコープにセットする
			request.setAttribute("employees", employees);

			//一覧表示画面を表示する
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/List.jsp");
			dispatcher.forward(request, response);

		} catch (IOException | ServletException | RuntimeException e) {
			e.printStackTrace();
		}
	}

	/**
	 * セッションから検索条件を取得し、その検索条件に合った社員情報を取得する
	 * @param session
	 * @return employees
	 */
	private List<EmployeeEntity> getEmployeesFromSession(HttpSession session) {
		//セッションから検索条件を取得する		
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

		//employeeNoの型変換を行う
		int employeeNo = -1;
		if (employeeNoStr != null && !employeeNoStr.isEmpty()) {
			employeeNo = Integer.parseInt(employeeNoStr);
		}

		//birthdayの型変換を行う
		Date birthdayFrom = null;
		Date birthdayTo = null;
		if (birthdayFromStr != null && !birthdayFromStr.isEmpty()) {
			birthdayFrom = Date.valueOf(birthdayFromStr);
		}
		if (birthdayToStr != null && !birthdayToStr.isEmpty()) {
			birthdayTo = Date.valueOf(birthdayToStr);
		}

		//hireDateの型変換を行う
		Date hireDateFrom = null;
		Date hireDateTo = null;
		if (hireDateFromStr != null && !hireDateFromStr.isEmpty()) {
			hireDateFrom = Date.valueOf(hireDateFromStr);
		}
		if (hireDateToStr != null && !hireDateToStr.isEmpty()) {
			hireDateTo = Date.valueOf(hireDateToStr);
		}

		// SearchConditionEntityを使って検索条件をセット
		SearchConditionEntity searchCondition = new SearchConditionEntity(employeeNo, lastName,
				firstName, alphabetLastName, alphabetFirstName, birthdayFrom, birthdayTo, hireDateFrom,
				hireDateTo, department);

		// 社員情報を取得する
		List<EmployeeEntity> employees = listService.searchEmployees(searchCondition);
		return employees;
	}
}
