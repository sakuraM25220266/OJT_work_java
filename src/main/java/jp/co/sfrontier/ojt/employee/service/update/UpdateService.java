package jp.co.sfrontier.ojt.employee.service.update;

import jp.co.sfrontier.ojt.employee.db.dao.EmployeeDao;
import jp.co.sfrontier.ojt.employee.db.entity.EmployeeEntity;

/**
 * 社員情報の更新処理を行うサービスクラス
 */
public class UpdateService {

	EmployeeDao employeeDao = new EmployeeDao();

	/**
	 * 社員情報の更新を行う
	 * @param employee
	 * @return isUpdated
	 */
	public boolean updateEmployee(EmployeeEntity employee) {
		return employeeDao.updateEmployee(employee);
	}
}
