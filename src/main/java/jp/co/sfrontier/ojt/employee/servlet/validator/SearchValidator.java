package jp.co.sfrontier.ojt.employee.servlet.validator;

import java.sql.Date;

/**
 * 検索フォームのバリデーションチェックの処理を行うクラス
 */
public class SearchValidator {

	/**
	 * 社員番号のバリデーションチェックを行うメソッド
	 * @param employeeNoStr
	 * @return エラーがある場合はエラーメッセージ、ない場合はnull
	 */
	public String validateEmployeeNo(String employeeNoStr) {
		if (employeeNoStr != null && !employeeNoStr.isEmpty() && !employeeNoStr.matches("^[0-9]{1,4}$")) {
			return "4文字以内の半角数値で入力してください。";
		}
		return null;
	}

	/**
	 * 姓(漢字)のバリデーションチェックを行うメソッド
	 * @param lastName
	 * @return エラーがある場合はエラーメッセージ、ない場合はnull
	 */
	public String validateLastName(String lastName) {
		if (lastName != null && !lastName.isEmpty() && lastName.length() > 10) {
			return "10文字以内で入力してください。";
		}
		return null;
	}

	/**
	 * 名(漢字)のバリデーションチェックを行うメソッド
	 * @param firstName
	 * @return エラーがある場合はエラーメッセージ、ない場合はnull
	 */
	public String validateFirstName(String firstName) {
		if (firstName != null && !firstName.isEmpty() && firstName.length() > 10) {
			return "10文字以内で入力してください。";
		}
		return null;
	}

	/**
	 * 姓(ローマ字)のバリデーションチェックを行うメソッド
	 * @param alphabetLastName
	 * @return エラーがある場合はエラーメッセージ、ない場合はnull
	 */
	public String validateAlphabetLastName(String alphabetLastName) {
		if (alphabetLastName != null && !alphabetLastName.isEmpty() && !alphabetLastName.matches("^[A-Za-z]{1,20}$")) {
			return "20文字以内の半角英字で入力してください。";
		}
		return null;
	}

	/**
	 * 名(ローマ字)のバリデーションチェックを行うメソッド
	 * @param alphabetFirstName
	 * @return エラーがある場合はエラーメッセージ、ない場合はnull
	 */
	public String validateAlphabetFirstName(String alphabetFirstName) {
		if (alphabetFirstName != null && !alphabetFirstName.isEmpty() && !alphabetFirstName.matches("^[A-Za-z]{1,20}$")) {
			return "20文字以内の半角英字で入力してください。";
		}
		return null;
	}

	/**
	 * 年月日の範囲のバリデーションチェックを行うメソッド
	 * @param from
	 * @param to
	 * @return エラーがある場合はエラーメッセージ、ない場合はnull
	 */
	public String validateDate(Date from, Date to) {
		if (from != null && to != null && from.after(to)) {
			return "期間検索の開始日が終了日よりも後の日付になっています。";
		}
		return null;
	}

	/**
	 * 部署のバリデーションチェックを行うメソッド
	 * @param department
	 * @return エラーがある場合はエラーメッセージ、ない場合はnull
	 */
	public String validateDepartment(String department) {
		if (department != null && !department.isEmpty() && department.length() > 20) {
			return "20文字以内で入力してください。";
		}
		return null;
	}
}
