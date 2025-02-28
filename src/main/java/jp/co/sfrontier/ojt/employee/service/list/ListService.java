package jp.co.sfrontier.ojt.employee.service.list;

import java.util.List;

import jp.co.sfrontier.ojt.employee.db.dao.EmployeeDao;
import jp.co.sfrontier.ojt.employee.db.entity.EmployeeEntity;
import jp.co.sfrontier.ojt.employee.db.entity.SearchConditionEntity;

/**
 * 社員情報の一覧表示・検索を行うサービスクラス
 */
public class ListService {

    /**
     * 社員情報を取得するメソッド
     * @return EmployeeInfoオブジェクト
     */
    public List<EmployeeEntity> searchEmployees(SearchConditionEntity searchCondition) {
    	
		EmployeeDao employeeDao = new EmployeeDao();
		
      //EmployeeDaoクラスの検索処理を呼び出す
    	return employeeDao.getEmployeeInfo(searchCondition);
    }
}
