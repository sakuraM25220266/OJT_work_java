package jp.co.sfrontier.ojt.employee.db.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import jp.co.sfrontier.ojt.employee.db.entity.EmployeeEntity;
import jp.co.sfrontier.ojt.employee.db.entity.SearchConditionEntity;

/**
 * EmployeeDaoクラスのメソッドの単体テストを行うテストクラス<br>
 * テスト実行前に、src/main/test/sql配下のTestData.sqlを手動で実行し、テスト用DB(company_test)にテストデータを挿入する<br>
 * テスト実行後は、テーブル内のテストデータを手動で削除する
 */
class EmployeeDaoTest {

	EmployeeDao employeeDao = new EmployeeDao();

	/**
	 * 社員情報の検索メソッドの正常系テストケース<br>
	 * 検索条件を指定し、検索結果が0件であることを確認する
	 */
	@Test
	void testGetEmployeeInfo_noResult() {
		SearchConditionEntity searchCondition = new SearchConditionEntity(600, "", "", "", "", null, null, null, null,
				"");
		List<EmployeeEntity> employees = employeeDao.getEmployeeInfo(searchCondition);
		int expected = 0;
		int actual = employees.size();
		assertEquals(expected, actual);
	}

	/**
	 * 社員情報の検索メソッドの正常系テストケース<br>
	 * 検索条件を指定し、検索結果が1件であることを確認する
	 */
	@Test
	void testGetEmployeeInfo_oneResult() {
		SearchConditionEntity searchCondition = new SearchConditionEntity(601, "", "", "", "", null, null, null, null,
				"");
		List<EmployeeEntity> employees = employeeDao.getEmployeeInfo(searchCondition);
		int expected = 1;
		int actual = employees.size();
		assertEquals(expected, actual);
	}

	/**
	 * 社員情報の検索メソッドの正常系テストケース<br>
	 * 検索条件を指定し、検索結果が複数件であることを確認する
	 */
	@Test
	void testGetEmployeeInfo_multipleResult() {
		SearchConditionEntity searchCondition = new SearchConditionEntity(60, "", "", "", "", null, null, null, null,
				"");
		List<EmployeeEntity> employees = employeeDao.getEmployeeInfo(searchCondition);
		int expected = 5;
		int actual = employees.size();
		assertEquals(expected, actual);
	}

	/**
	 * 社員情報の検索メソッドの正常系テストケース<br>
	 * 検索条件に社員番号を指定し、結果が1件かつ想定通りの社員情報であることを確認する
	 */
	@Test
	void testGetEmployeeInfo_employeeNo() {
		SearchConditionEntity searchCondition = new SearchConditionEntity(602, "", "", "", "", null, null, null, null,
				"");

		List<EmployeeEntity> employees = employeeDao.getEmployeeInfo(searchCondition);
		int expected = 1;
		int actual = employees.size();
		assertEquals(expected, actual);

		EmployeeEntity employee = employees.get(0);
		assertEquals(602, employee.getEmployeeNo());
		assertEquals("佐々木", employee.getLastName());
		assertEquals("優子", employee.getFirstName());
		assertEquals("Sasaki", employee.getAlphabetLastName());
		assertEquals("Yuko", employee.getAlphabetFirstName());
		assertEquals(Date.valueOf("2000-05-10"), employee.getBirthday());
		assertEquals(Date.valueOf("2023-04-01"), employee.getHireDate());
		assertEquals("システムサービス2部", employee.getDepartment());
	}

	/**
	 * 社員情報の検索メソッドの正常系テストケース<br>
	 * 検索条件に姓(漢字)を指定し、結果が1件かつ想定通りの社員情報であることを確認する
	 */
	@Test
	void testGetEmployeeInfo_lastName() {
		SearchConditionEntity searchCondition = new SearchConditionEntity(-1, "上田", "", "", "", null, null, null, null,
				"");

		List<EmployeeEntity> employees = employeeDao.getEmployeeInfo(searchCondition);
		int expected = 1;
		int actual = employees.size();
		assertEquals(expected, actual);

		EmployeeEntity employee = employees.get(0);
		assertEquals(601, employee.getEmployeeNo());
		assertEquals("上田", employee.getLastName());
		assertEquals("雄二", employee.getFirstName());
		assertEquals("Ueda", employee.getAlphabetLastName());
		assertEquals("Yuji", employee.getAlphabetFirstName());
		assertEquals(Date.valueOf("1980-03-19"), employee.getBirthday());
		assertEquals(Date.valueOf("2020-09-01"), employee.getHireDate());
		assertEquals("システムサービス1部", employee.getDepartment());
	}

	/**
	 * 社員情報の検索メソッドの正常系テストケース<br>
	 * 検索条件に名(漢字)を指定し、結果が1件かつ想定通りの社員情報であることを確認する
	 */
	@Test
	void testGetEmployeeInfo_firstName() {
		SearchConditionEntity searchCondition = new SearchConditionEntity(-1, "", "花", "", "", null, null, null, null,
				"");

		List<EmployeeEntity> employees = employeeDao.getEmployeeInfo(searchCondition);
		int expected = 1;
		int actual = employees.size();
		assertEquals(expected, actual);

		EmployeeEntity employee = employees.get(0);
		assertEquals(603, employee.getEmployeeNo());
		assertEquals("田中", employee.getLastName());
		assertEquals("花", employee.getFirstName());
		assertEquals("Tanaka", employee.getAlphabetLastName());
		assertEquals("Hana", employee.getAlphabetFirstName());
		assertEquals(Date.valueOf("1990-07-22"), employee.getBirthday());
		assertEquals(Date.valueOf("2015-10-15"), employee.getHireDate());
		assertEquals("システムサービス3部", employee.getDepartment());
	}

	/**
	 * 社員情報の検索メソッドの正常系テストケース<br>
	 * 検索条件に姓(ローマ字)を指定し、結果が1件かつ想定通りの社員情報であることを確認する
	 */
	@Test
	void testGetEmployeeInfo_alphabetLastName() {
		SearchConditionEntity searchCondition = new SearchConditionEntity(-1, "", "", "Ueda", "", null, null, null,
				null, "");

		List<EmployeeEntity> employees = employeeDao.getEmployeeInfo(searchCondition);
		int expected = 1;
		int actual = employees.size();
		assertEquals(expected, actual);

		EmployeeEntity employee = employees.get(0);
		assertEquals(601, employee.getEmployeeNo());
		assertEquals("上田", employee.getLastName());
		assertEquals("雄二", employee.getFirstName());
		assertEquals("Ueda", employee.getAlphabetLastName());
		assertEquals("Yuji", employee.getAlphabetFirstName());
		assertEquals(Date.valueOf("1980-03-19"), employee.getBirthday());
		assertEquals(Date.valueOf("2020-09-01"), employee.getHireDate());
		assertEquals("システムサービス1部", employee.getDepartment());
	}

	/**
	 * 社員情報の検索メソッドの正常系テストケース<br>
	 * 検索条件に名(ローマ字)を指定し、結果が1件かつ想定通りの社員情報であることを確認する
	 */
	@Test
	void testGetEmployeeInfo_alphabetFirstName() {
		SearchConditionEntity searchCondition = new SearchConditionEntity(-1, "", "", "", "Hana", null, null, null,
				null, "");

		List<EmployeeEntity> employees = employeeDao.getEmployeeInfo(searchCondition);
		int expected = 1;
		int actual = employees.size();
		assertEquals(expected, actual);

		EmployeeEntity employee = employees.get(0);
		assertEquals(603, employee.getEmployeeNo());
		assertEquals("田中", employee.getLastName());
		assertEquals("花", employee.getFirstName());
		assertEquals("Tanaka", employee.getAlphabetLastName());
		assertEquals("Hana", employee.getAlphabetFirstName());
		assertEquals(Date.valueOf("1990-07-22"), employee.getBirthday());
		assertEquals(Date.valueOf("2015-10-15"), employee.getHireDate());
		assertEquals("システムサービス3部", employee.getDepartment());
	}

	/**
	 * 社員情報の検索メソッドの正常系テストケース<br>
	 * 検索条件に生年月日(期間開始)を指定し、結果が1件かつ想定通りの社員情報であることを確認する
	 */
	@Test
	void testGetEmployeeInfo_birthdayFrom() {
		SearchConditionEntity searchCondition = new SearchConditionEntity(-1, "", "", "", "",
				Date.valueOf("2000-05-10"), null, null, null, "");

		List<EmployeeEntity> employees = employeeDao.getEmployeeInfo(searchCondition);
		int expected = 1;
		int actual = employees.size();
		assertEquals(expected, actual);

		EmployeeEntity employee = employees.get(0);
		assertEquals(602, employee.getEmployeeNo());
		assertEquals("佐々木", employee.getLastName());
		assertEquals("優子", employee.getFirstName());
		assertEquals("Sasaki", employee.getAlphabetLastName());
		assertEquals("Yuko", employee.getAlphabetFirstName());
		assertEquals(Date.valueOf("2000-05-10"), employee.getBirthday());
		assertEquals(Date.valueOf("2023-04-01"), employee.getHireDate());
		assertEquals("システムサービス2部", employee.getDepartment());
	}

	/**
	 * 社員情報の検索メソッドの正常系テストケース<br>
	 * 検索条件に生年月日(期間終了)を指定し、結果が1件かつ想定通りの社員情報であることを確認する
	 */
	@Test
	void testGetEmployeeInfo_birthdayTo() {
		SearchConditionEntity searchCondition = new SearchConditionEntity(-1, "", "", "", "",
				null, Date.valueOf("1980-03-19"), null, null, "");

		List<EmployeeEntity> employees = employeeDao.getEmployeeInfo(searchCondition);
		int expected = 1;
		int actual = employees.size();
		assertEquals(expected, actual);

		EmployeeEntity employee = employees.get(0);
		assertEquals(601, employee.getEmployeeNo());
		assertEquals("上田", employee.getLastName());
		assertEquals("雄二", employee.getFirstName());
		assertEquals("Ueda", employee.getAlphabetLastName());
		assertEquals("Yuji", employee.getAlphabetFirstName());
		assertEquals(Date.valueOf("1980-03-19"), employee.getBirthday());
		assertEquals(Date.valueOf("2020-09-01"), employee.getHireDate());
		assertEquals("システムサービス1部", employee.getDepartment());
	}

	/**
	 * 社員情報の検索メソッドの正常系テストケース<br>
	 * 検索条件に入社年月日(期間開始)を指定し、結果が1件かつ想定通りの社員情報であることを確認する
	 */
	@Test
	void testGetEmployeeInfo_hireDateFrom() {
		SearchConditionEntity searchCondition = new SearchConditionEntity(-1, "", "", "", "",
				null, null, Date.valueOf("2023-04-01"), null, "");

		List<EmployeeEntity> employees = employeeDao.getEmployeeInfo(searchCondition);
		int expected = 1;
		int actual = employees.size();
		assertEquals(expected, actual);

		EmployeeEntity employee = employees.get(0);
		assertEquals(602, employee.getEmployeeNo());
		assertEquals("佐々木", employee.getLastName());
		assertEquals("優子", employee.getFirstName());
		assertEquals("Sasaki", employee.getAlphabetLastName());
		assertEquals("Yuko", employee.getAlphabetFirstName());
		assertEquals(Date.valueOf("2000-05-10"), employee.getBirthday());
		assertEquals(Date.valueOf("2023-04-01"), employee.getHireDate());
		assertEquals("システムサービス2部", employee.getDepartment());
	}

	/**
	 * 社員情報の検索メソッドの正常系テストケース<br>
	 * 検索条件に入社年月日(期間終了)を指定し、結果が1件かつ想定通りの社員情報であることを確認する
	 */
	@Test
	void testGetEmployeeInfo_hireDateTo() {
		SearchConditionEntity searchCondition = new SearchConditionEntity(-1, "", "", "", "",
				null, null, null, Date.valueOf("2015-10-15"), "");

		List<EmployeeEntity> employees = employeeDao.getEmployeeInfo(searchCondition);
		int expected = 1;
		int actual = employees.size();
		assertEquals(expected, actual);

		EmployeeEntity employee = employees.get(0);
		assertEquals(603, employee.getEmployeeNo());
		assertEquals("田中", employee.getLastName());
		assertEquals("花", employee.getFirstName());
		assertEquals("Tanaka", employee.getAlphabetLastName());
		assertEquals("Hana", employee.getAlphabetFirstName());
		assertEquals(Date.valueOf("1990-07-22"), employee.getBirthday());
		assertEquals(Date.valueOf("2015-10-15"), employee.getHireDate());
		assertEquals("システムサービス3部", employee.getDepartment());
	}

	/**
	 * 社員情報の検索メソッドの正常系テストケース<br>
	 * 検索条件に部署を指定し、結果が1件かつ想定通りの社員情報であることを確認する
	 */
	@Test
	void testGetEmployeeInfo_department() {
		SearchConditionEntity searchCondition = new SearchConditionEntity(-1, "", "", "", "",
				null, null, null, null, "システムサービス3部");

		List<EmployeeEntity> employees = employeeDao.getEmployeeInfo(searchCondition);
		int expected = 1;
		int actual = employees.size();
		assertEquals(expected, actual);

		EmployeeEntity employee = employees.get(0);
		assertEquals(603, employee.getEmployeeNo());
		assertEquals("田中", employee.getLastName());
		assertEquals("花", employee.getFirstName());
		assertEquals("Tanaka", employee.getAlphabetLastName());
		assertEquals("Hana", employee.getAlphabetFirstName());
		assertEquals(Date.valueOf("1990-07-22"), employee.getBirthday());
		assertEquals(Date.valueOf("2015-10-15"), employee.getHireDate());
		assertEquals("システムサービス3部", employee.getDepartment());
	}

	/**
	 * 社員情報の検索メソッドの正常系テストケース<br>
	 * 検索条件を全項目指定し、結果が1件かつ想定通りの社員情報であることを確認する
	 */
	@Test
	void testGetEmployeeInfo_allConditions() {
		SearchConditionEntity searchCondition = new SearchConditionEntity(601, "上田", "雄二", "Ueda", "Yuji",
				Date.valueOf("1980-03-19"), null, Date.valueOf("2020-09-01"), null, "システムサービス1部");

		List<EmployeeEntity> employees = employeeDao.getEmployeeInfo(searchCondition);
		EmployeeEntity employee = employees.get(0);
		assertEquals(601, employee.getEmployeeNo());
		assertEquals("上田", employee.getLastName());
		assertEquals("雄二", employee.getFirstName());
		assertEquals("Ueda", employee.getAlphabetLastName());
		assertEquals("Yuji", employee.getAlphabetFirstName());
		assertEquals(Date.valueOf("1980-03-19"), employee.getBirthday());
		assertEquals(Date.valueOf("2020-09-01"), employee.getHireDate());
		assertEquals("システムサービス1部", employee.getDepartment());
	}

	/**
	 * 社員番号の重複チェックメソッドの正常系テストケース(社員番号が存在する場合)
	 * @throws SQLException 
	 */
	@Test
	void testIsEmployeeNoExists_employeeExists() throws SQLException {
		int employeeNo = 601;
		boolean expected = true;
		boolean actual = employeeDao.isEmployeeNoExists(employeeNo);
		assertEquals(expected, actual);
	}

	/**
	 * 社員番号の重複チェックメソッドの正常系テストケース(社員番号が存在しない場合)
	 * @throws SQLException 
	 */
	@Test
	void testIsEmployeeNoExists_employeeNotExists() throws SQLException {
		int employeeNo = 600;
		boolean expected = false;
		boolean actual = employeeDao.isEmployeeNoExists(employeeNo);
		assertEquals(expected, actual);
	}

	/**
	 * 社員番号から社員情報を検索するメソッドの正常系テストケース(社員番号が一致する社員情報が存在する場合)
	 */
	@Test
	void testGetEmployeeByNo_employeeExists() {
		String employeeNoStr = "601";
		EmployeeEntity employee = employeeDao.getEmployeeByNo(employeeNoStr);
		assertEquals(601, employee.getEmployeeNo());
		assertEquals("上田", employee.getLastName());
		assertEquals("雄二", employee.getFirstName());
		assertEquals("Ueda", employee.getAlphabetLastName());
		assertEquals("Yuji", employee.getAlphabetFirstName());
		assertEquals(Date.valueOf("1980-03-19"), employee.getBirthday());
		assertEquals(Date.valueOf("2020-09-01"), employee.getHireDate());
		assertEquals("システムサービス1部", employee.getDepartment());
	}

	/**
	 * 社員番号から社員情報を検索するメソッドの正常系テストケース(社員番号が一致する社員情報が存在しない場合)
	 */
	@Test
	void testGetEmployeeByNo_employeeNotExists() {
		String employeeNoStr = "600";
		String expected = null;
		EmployeeEntity actual = employeeDao.getEmployeeByNo(employeeNoStr);
		assertEquals(expected, actual);
	}

	/**
	 * 社員情報の新規登録メソッドの正常系テストケース
	 */
	@Test
	void testInsertEmployee() {
		EmployeeEntity employee = new EmployeeEntity(604, "佐藤", "太郎", "Sato", "Taro", Date.valueOf("2000-01-01"),
				Date.valueOf("2023-04-01"), "システムサービス1部");
		boolean expected = true;
		boolean actual = employeeDao.insertEmployee(employee);
		assertEquals(expected, actual);

		String employeeNoStr = "604";
		EmployeeEntity insertedEmployee = employeeDao.getEmployeeByNo(employeeNoStr);
		assertEquals(604, insertedEmployee.getEmployeeNo());
		assertEquals("佐藤", insertedEmployee.getLastName());
		assertEquals("太郎", insertedEmployee.getFirstName());
		assertEquals("Sato", insertedEmployee.getAlphabetLastName());
		assertEquals("Taro", insertedEmployee.getAlphabetFirstName());
		assertEquals(Date.valueOf("2000-01-01"), insertedEmployee.getBirthday());
		assertEquals(Date.valueOf("2023-04-01"), insertedEmployee.getHireDate());
		assertEquals("システムサービス1部", insertedEmployee.getDepartment());
	}

	/**
	 * 社員情報の更新メソッドの正常系テストケース
	 */
	@Test
	void testUpdateEmployee() {
		EmployeeEntity employee = new EmployeeEntity(605, "山田", "健太", "Yamada", "Kenta", Date.valueOf("2000-01-01"),
				Date.valueOf("2023-04-01"), "総務部");
		boolean expected = true;
		boolean actual = employeeDao.updateEmployee(employee);
		assertEquals(expected, actual);

		String employeeNoStr = "605";
		EmployeeEntity updatedEmployee = employeeDao.getEmployeeByNo(employeeNoStr);
		assertEquals(605, updatedEmployee.getEmployeeNo());
		assertEquals("山田", updatedEmployee.getLastName());
		assertEquals("健太", updatedEmployee.getFirstName());
		assertEquals("Yamada", updatedEmployee.getAlphabetLastName());
		assertEquals("Kenta", updatedEmployee.getAlphabetFirstName());
		assertEquals(Date.valueOf("2000-01-01"), updatedEmployee.getBirthday());
		assertEquals(Date.valueOf("2023-04-01"), updatedEmployee.getHireDate());
		assertEquals("総務部", updatedEmployee.getDepartment());
	}

	/**
	 * 社員情報の削除メソッドの正常系テストケース
	 */
	@Test
	void testDeleteEmployee() {
		int employeeNo = 606;
		boolean expected = true;
		boolean actual = employeeDao.deleteEmployee(employeeNo);
		assertEquals(expected, actual);

		String employeeNoStr = "606";
		String expected1 = null;
		EmployeeEntity actual1 = employeeDao.getEmployeeByNo(employeeNoStr);
		assertEquals(actual1, expected1);
	}
}
