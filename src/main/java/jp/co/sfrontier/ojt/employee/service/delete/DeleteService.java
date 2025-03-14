package jp.co.sfrontier.ojt.employee.service.delete;

import jp.co.sfrontier.ojt.employee.db.dao.EmployeeDao;

/**
 * 社員情報の削除処理を行うサービスクラス
 */
public class DeleteService {

	EmployeeDao employeeDao = new EmployeeDao();
	
	/**
	 * 社員情報の削除を行う
	 * @param employeeNo
	 * @return isDeleted
	 */
	public boolean deleteEmployee(int employeeNo) {
		return employeeDao.deleteEmployee(employeeNo);
	}
}