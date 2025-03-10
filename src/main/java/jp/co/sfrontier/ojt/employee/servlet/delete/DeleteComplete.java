package jp.co.sfrontier.ojt.employee.servlet.delete;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.sfrontier.ojt.employee.service.delete.DeleteService;

/**
 * 社員情報の削除処理を完了させるサーブレットクラス
 */
@WebServlet("/delete/complete")
public class DeleteComplete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//削除対象の社員番号を取得する
		int employeeNo = Integer.parseInt(request.getParameter("employeeNo"));

		//DeleteServiceを使って社員情報を削除する
		DeleteService service = new DeleteService();
		boolean isSuccess = service.deleteEmployee(employeeNo);

		if (isSuccess) {
			//削除に成功したとき、削除機能の完了画面を表示する
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/delete/DeleteComplete.jsp");
			dispatcher.forward(request, response);
		} else {
			//削除に失敗したとき、確認画面に値をセットし、エラーメッセージを表示する
			request.setAttribute("errorMessage", "エラーが発生したため登録できませんでした。もう一度お試しください。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/delete/DeleteConfirm.jsp");
			dispatcher.forward(request, response);
		}
	}
}
