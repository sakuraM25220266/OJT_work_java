package jp.co.sfrontier.ojt.employee.db.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sfrontier.ojt.employee.db.entity.EmployeeEntity;
import jp.co.sfrontier.ojt.employee.db.entity.SearchConditionEntity;

/**
 * 社員情報の参照、新規登録、更新、削除のSQL文を実行するクラス。
 */
public class EmployeeDao {

	/**
	 * 社員情報を検索するメソッド
	 * @return employees
	 */
	public List<EmployeeEntity> getEmployeeInfo(SearchConditionEntity searchCondition) {

		String sql = "SELECT * FROM employees";

		// 検索条件を格納するためのリスト
		List<String> conditions = new ArrayList<>();

		// 各条件が入力されている場合はリストに追加する
		if (searchCondition.getEmployeeNo() != -1) {
			conditions.add("employee_no LIKE ?");
		}

		if (searchCondition.getLastName() != null && !searchCondition.getLastName().isEmpty()) {
			conditions.add("last_name LIKE ?");
		}

		if (searchCondition.getFirstName() != null && !searchCondition.getFirstName().isEmpty()) {
			conditions.add("first_name LIKE ?");
		}

		if (searchCondition.getAlphabetLastName() != null && !searchCondition.getAlphabetLastName().isEmpty()) {
			conditions.add("last_name_roman LIKE ?");
		}

		if (searchCondition.getAlphabetFirstName() != null && !searchCondition.getAlphabetFirstName().isEmpty()) {
			conditions.add("first_name_roman LIKE ?");
		}

		if (searchCondition.getBirthdayFrom() != null && searchCondition.getBirthdayTo() != null) {
			conditions.add("birthday BETWEEN ? AND ?");
		} else if (searchCondition.getBirthdayFrom() != null) {
			conditions.add("birthday >= ?");
		} else if (searchCondition.getBirthdayTo() != null) {
			conditions.add("birthday <= ?");
		}

		if (searchCondition.getHireDateFrom() != null && searchCondition.getHireDateTo() != null) {
			conditions.add("hired_on BETWEEN ? AND ?");
		} else if (searchCondition.getHireDateFrom() != null) {
			conditions.add("hired_on >= ?");
		} else if (searchCondition.getHireDateTo() != null) {
			conditions.add("hired_on <= ?");
		}

		if (searchCondition.getDepartment() != null && !searchCondition.getDepartment().isEmpty()) {
			conditions.add("department LIKE ?");
		}

		// リストに検索条件があればWHERE句を追加し、検索条件が複数あればANDで条件を結合する
		if (!conditions.isEmpty()) {
			String condition = String.join(" AND ", conditions);
			sql += " WHERE " + condition;
		}

		List<EmployeeEntity> employees = new ArrayList<>();

		try {
			// データベース接続
			DatabaseConnector dbConnector = new DatabaseConnector();
			Connection con = dbConnector.getConnection();

			// SQL実行準備
			PreparedStatement stmt = con.prepareStatement(sql);

			//どの位置にパラメータの値をセットするかを管理するための変数
			int pos = 1;

			// 検索条件がある場合、対応する値を順番に設定する
			if (searchCondition.getEmployeeNo() != -1) {
				stmt.setString(pos++, "%" + searchCondition.getEmployeeNo() + "%");
			}

			if (searchCondition.getLastName() != null && !searchCondition.getLastName().isEmpty()) {
				stmt.setString(pos++, "%" + searchCondition.getLastName() + "%");
			}

			if (searchCondition.getFirstName() != null && !searchCondition.getFirstName().isEmpty()) {
				stmt.setString(pos++, "%" + searchCondition.getFirstName() + "%");
			}

			if (searchCondition.getAlphabetLastName() != null && !searchCondition.getAlphabetLastName().isEmpty()) {
				stmt.setString(pos++, "%" + searchCondition.getAlphabetLastName() + "%");
			}

			if (searchCondition.getAlphabetFirstName() != null && !searchCondition.getAlphabetFirstName().isEmpty()) {
				stmt.setString(pos++, "%" + searchCondition.getAlphabetFirstName() + "%");
			}

			if (searchCondition.getBirthdayFrom() != null && searchCondition.getBirthdayTo() != null) {
				stmt.setDate(pos++, searchCondition.getBirthdayFrom());
				stmt.setDate(pos++, searchCondition.getBirthdayTo());
			} else if (searchCondition.getBirthdayFrom() != null) {
				stmt.setDate(pos++, searchCondition.getBirthdayFrom());
			} else if (searchCondition.getBirthdayTo() != null) {
				stmt.setDate(pos++, searchCondition.getBirthdayTo());
			}

			if (searchCondition.getHireDateFrom() != null && searchCondition.getHireDateTo() != null) {
				stmt.setDate(pos++, searchCondition.getHireDateFrom());
				stmt.setDate(pos++, searchCondition.getHireDateTo());
			} else if (searchCondition.getHireDateFrom() != null) {
				stmt.setDate(pos++, searchCondition.getHireDateFrom());
			} else if (searchCondition.getHireDateTo() != null) {
				stmt.setDate(pos++, searchCondition.getHireDateTo());
			}

			if (searchCondition.getDepartment() != null && !searchCondition.getDepartment().isEmpty()) {
				stmt.setString(pos++, "%" + searchCondition.getDepartment() + "%");
			}

			// 実行結果を取得する
			ResultSet rs = stmt.executeQuery();

			// データがなくなるまで(rs.next()がfalseになるまで)繰り返す
			while (rs.next()) {
				int employeeNo = rs.getInt("employee_no");
				String lastName = rs.getString("last_name");
				String firstName = rs.getString("first_name");
				String alphabetLastName = rs.getString("last_name_roman");
				String alphabetFirstName = rs.getString("first_name_roman");
				Date birthday = rs.getDate("birthday");
				Date hireDate = rs.getDate("hired_on");
				String department = rs.getString("department");

				//EmployeeEntityのインスタンスを生成し、取り出したデータをリストに追加する
				EmployeeEntity employee = new EmployeeEntity(employeeNo, lastName, firstName, alphabetLastName,
						alphabetFirstName, birthday, hireDate, department);
				employees.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}

	/**
	 * 社員番号の重複チェックを行うメソッド
	 * @param employeeNo
	 * @return isExist
	 * @throws SQLException
	 */
	public boolean isEmployeeNoExists(int employeeNo) throws SQLException {
		boolean isExist = false;

		// SQL文の作成
		String sql = "SELECT COUNT(*) FROM employees WHERE employee_no = ?";
		// データベース接続
		DatabaseConnector dbConnector = new DatabaseConnector();
		Connection con = dbConnector.getConnection();

		// SQL実行準備
		PreparedStatement stmt = con.prepareStatement(sql);
		//入力された社員番号の値をセットする
		stmt.setInt(1, employeeNo);
		// 実行結果取得
		ResultSet rs = stmt.executeQuery();
		//DBに社員番号が存在したらtrue、存在しなければfalseを返す
		if (rs.next()) {
			int count = rs.getInt(1);
			if (count > 0) {
				isExist = true;
			}
		}
		return isExist;
	}

	/**
	 * 社員番号から社員情報を検索するメソッド<br>
	 * 社員番号のみ指定したSerchConditionEntityオブジェクトを生成し、getEmployeeInfoメソッドに検索処理を委譲する
	 * @param employeeNoStr
	 * @return 一致する社員情報があればemployees、なければnull
	 */
	public EmployeeEntity getEmployeeByNo(String employeeNoStr) {
		int employeeNo = Integer.parseInt(employeeNoStr);
		SearchConditionEntity searchCondition = new SearchConditionEntity(employeeNo, "", "", "", "", null, null, null,
				null, "");

		//getEmployeeInfoメソッドに処理を委譲する
		List<EmployeeEntity> employees = getEmployeeInfo(searchCondition);

		if (employees.size() > 0) {
			return employees.get(0);
		}
		return null;
	}

	/**
	 * 社員情報の新規登録を行う
	 * @param employee
	 * @return isInserted
	 */
	public boolean insertEmployee(EmployeeEntity employee) {
		// SQL文の作成
		String sql = "INSERT INTO employees (employee_no, last_name, first_name, last_name_roman, first_name_roman, birthday, hired_on, department) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		//インサート成功・失敗フラグ
		boolean isInserted = false;

		try {
			// データベース接続
			DatabaseConnector dbConnector = new DatabaseConnector();
			Connection con = dbConnector.getConnection();
			// SQL実行準備
			PreparedStatement stmt = con.prepareStatement(sql);

			//sqlのプレースホルダに値をセットする
			stmt.setInt(1, employee.getEmployeeNo());
			stmt.setString(2, employee.getLastName());
			stmt.setString(3, employee.getFirstName());
			stmt.setString(4, employee.getAlphabetLastName());
			stmt.setString(5, employee.getAlphabetFirstName());
			stmt.setDate(6, employee.getBirthday());
			stmt.setDate(7, employee.getHireDate());
			stmt.setString(8, employee.getDepartment());

			// SQL実行
			stmt.executeUpdate();
			isInserted = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isInserted;
	}

	/**
	 * 社員情報の更新を行う
	 * @param employee
	 * @return isUpdated
	 */
	public boolean updateEmployee(EmployeeEntity employee) {
		// SQL文の作成
		String sql = "UPDATE employees SET last_name = ?, first_name = ?, last_name_roman = ?, first_name_roman = ?, birthday = ?, hired_on = ?, department = ? WHERE employee_no = ?";

		//アップデート成功・失敗フラグ
		boolean isUpdated = false;

		try {
			// データベース接続
			DatabaseConnector dbConnector = new DatabaseConnector();
			Connection con = dbConnector.getConnection();
			// SQL実行準備
			PreparedStatement stmt = con.prepareStatement(sql);

			//sqlのプレースホルダに値をセットする
			stmt.setString(1, employee.getLastName());
			stmt.setString(2, employee.getFirstName());
			stmt.setString(3, employee.getAlphabetLastName());
			stmt.setString(4, employee.getAlphabetFirstName());
			stmt.setDate(5, employee.getBirthday());
			stmt.setDate(6, employee.getHireDate());
			stmt.setString(7, employee.getDepartment());
			stmt.setInt(8, employee.getEmployeeNo());

			// SQL実行
			stmt.executeUpdate();
			isUpdated = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isUpdated;
	}

	/**
	 * 社員情報の削除を行う
	 * @param employeeNo
	 * @return isDeleted
	 */
	public boolean deleteEmployee(int employeeNo) {
		// SQL文の作成
		String sql = "DELETE FROM employees WHERE employee_no = ?";

		//デリート成功・失敗フラグ
		boolean isDeleted = false;

		try {
			// データベース接続
			DatabaseConnector dbConnector = new DatabaseConnector();
			Connection con = dbConnector.getConnection();
			// SQL実行準備
			PreparedStatement stmt = con.prepareStatement(sql);

			//sqlのプレースホルダに値をセットする
			stmt.setInt(1, employeeNo);

			// SQL実行
			stmt.executeUpdate();
			isDeleted = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isDeleted;
	}
}
