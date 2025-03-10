package jp.co.sfrontier.ojt.employee.service.list;

import java.util.List;

import jp.co.sfrontier.ojt.employee.db.dao.EmployeeDao;
import jp.co.sfrontier.ojt.employee.db.entity.EmployeeEntity;
import jp.co.sfrontier.ojt.employee.db.entity.SearchConditionEntity;

/**
 * 社員情報の一覧表示・検索を行うサービスクラス
 */
public class ListService {

	EmployeeDao employeeDao = new EmployeeDao();

	/**
	 * 検索条件を指定し社員情報を取得するメソッド
	 * @return employees(社員情報リスト)
	 */
	public List<EmployeeEntity> searchEmployees(SearchConditionEntity searchCondition) {
		//EmployeeDaoクラスの検索処理を呼び出す
		return employeeDao.getEmployeeInfo(searchCondition);
	}

	/**
	 * 社員番号に一致する社員情報を取得するメソッド
	 * @param employeeNoStr
	 * @return employee(社員番号に一致する1件の社員情報)
	 */
	public EmployeeEntity getEmployeeByNo(String employeeNoStr) {
		return employeeDao.getEmployeeByNo(employeeNoStr);
	}
}
