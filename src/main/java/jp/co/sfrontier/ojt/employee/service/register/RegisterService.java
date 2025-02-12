/**
 * 
 */
package jp.co.sfrontier.ojt.employee.service.register;

import jp.co.sfrontier.ojt.employee.db.dao.EmployeeDao;
import jp.co.sfrontier.ojt.employee.db.entity.EmployeeEntity;

/**
 * 社員情報の新規登録処理を行うサービスクラス
 */
public class RegisterService {

	/**
	 * 社員情報の登録処理を行うメソッド
	 * @param employee
	 * @return employeeDaoクラスのinsertEmployeeメソッドで登録ができたかどうかを表すフラグisInserted
	 */
	public boolean registerEmployee(EmployeeEntity employee) {
		EmployeeDao employeeDao = new EmployeeDao();

		//EmployeeDaoクラスの登録処理を呼び出す
		return employeeDao.insertEmployee(employee);
	}
}
