/**
 * 
 */
package jp.co.sfrontier.ojt.employee.db.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sfrontier.ojt.employee.db.entity.EmployeeEntity;

/**社員情報の参照、新規登録、更新、削除のSQL文を実行するクラス。
 * 
 */
public class EmployeeDao {

	//社員情報の参照を行う
	public List<EmployeeEntity> getEmployeeInfo() {
		// SQL文の作成
		String sql = "SELECT * FROM employees";
		List<EmployeeEntity> employees = new ArrayList<>();

		try {
			// データベース接続
			DatabaseConnector dbConnector = new DatabaseConnector();
			Connection con = dbConnector.getConnection();
			// SQL実行準備
			PreparedStatement stmt = con.prepareStatement(sql);
			// 実行結果取得
			ResultSet rs = stmt.executeQuery();

			// データがなくなるまで(rs.next()がfalseになるまで)繰り返す
			while (rs.next()) {
				int employeeNo = rs.getInt("employee_no");
				String lastName = rs.getString("last_name");
				String firstName = rs.getString("first_name");
				String alphabetLastName = rs.getString("last_name_roman");
				String alphabetFirstName = rs.getString("first_name_roman");
				Date birthday = Date.valueOf(rs.getString("birthday"));
				Date hireDate = Date.valueOf(rs.getString("hired_on"));
				String department = rs.getString("department");

				//EmployeeEntityのインスタンスを生成し、取り出したデータをリストに追加
				EmployeeEntity employee = new EmployeeEntity(employeeNo, lastName, firstName, alphabetLastName,
						alphabetFirstName, birthday, hireDate, department);
				employees.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}

	//社員番号の重複チェックを行う
	public boolean isEmpNoExists(int empNo) throws SQLException {
		boolean isExist = false;

		// SQL文の作成
		String sql = "SELECT COUNT(*) FROM employees WHERE employee_no = ?";
		// データベース接続
		DatabaseConnector dbConnector = new DatabaseConnector();
		Connection con = dbConnector.getConnection();
		
		// SQL実行準備
		PreparedStatement stmt = con.prepareStatement(sql);
		//入力された社員番号の値をセットする
		stmt.setInt(1, empNo);
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

	//社員情報の新規登録を行う
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
			stmt.setDate(7, employee.getHiredOn());
			stmt.setString(8, employee.getDepartment());

			// SQL実行
			int i = stmt.executeUpdate();
			System.out.println(i + "行が挿入されました。");
			isInserted = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isInserted;
	}
}
