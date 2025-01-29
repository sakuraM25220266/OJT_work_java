/**
 * 
 */
package jp.co.sfrontier.ojt.employee.service.register;

import jp.co.sfrontier.ojt.employee.db.dao.EmployeeDao;
import jp.co.sfrontier.ojt.employee.db.entity.EmployeeEntity;

/**社員情報の新規登録処理を行うクラス。
 * 
 */
public class RegisterService {
	
	//データベースへの接続情報をプロパティファイルから取得
	public boolean registerNewEmployee (EmployeeEntity employee) {
		EmployeeDao employeeDao = new EmployeeDao();
		
		//EmployeeDaoクラスの登録処理を呼び出す
		return employeeDao.insertEmployee(employee);
	}
}
