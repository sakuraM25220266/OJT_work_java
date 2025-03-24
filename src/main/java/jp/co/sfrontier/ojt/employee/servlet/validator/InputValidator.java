package jp.co.sfrontier.ojt.employee.servlet.validator;

/**
 * 登録機能と更新機能の入力画面のバリデーションチェックの処理を行うクラス
 */
public class InputValidator {

	/**
	 * 社員番号のバリデーションチェックを行うメソッド
	 * @param employeeNoStr
	 * @return エラーがある場合はエラーメッセージ、ない場合はnull
	 */
	public String validateEmployeeNo(String employeeNoStr) {
		if (employeeNoStr == null || employeeNoStr.isEmpty()) {
			return "必須項目が未入力です。";
		}
		if (employeeNoStr.matches("^0{1,4}$")) {
			return "社員番号0番は登録できません。";
		}
		if (!employeeNoStr.matches("^[0-9]{1,4}$")) {
			return "4文字以内の半角数値で入力してください。";
		}
		//バリデーションチェックOKの時
		return null;
	}

	/**
	 * 姓(漢字)のバリデーションチェックを行うメソッド
	 * @param lastName
	 * @return エラーがある場合はエラーメッセージ、ない場合はnull
	 */
	public String validateLastName(String lastName) {
		if (lastName == null || lastName.isEmpty()) {
			return "必須項目が未入力です。";
		}
		if (lastName.length() > 10) {
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
		if (firstName == null || firstName.isEmpty()) {
			return "必須項目が未入力です。";
		}
		if (firstName.length() > 10) {
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
		if (alphabetLastName == null || alphabetLastName.isEmpty()) {
			return "必須項目が未入力です。";
		}
		if (!alphabetLastName.matches("^[A-Za-z]{1,20}$")) {
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
		if (alphabetFirstName == null || alphabetFirstName.isEmpty()) {
			return "必須項目が未入力です。";
		}
		if (!alphabetFirstName.matches("^[A-Za-z]{1,20}$")) {
			return "20文字以内の半角英字で入力してください。";
		}
		return null;
	}

	/**
	 * 部署のバリデーションチェックを行うメソッド
	 * @param department
	 * @return エラーがある場合はエラーメッセージ、ない場合はnull
	 */
	public String validateDepartment(String department) {
		if (department.length() > 20) {
			return "20文字以内で入力してください。";
		}
		return null;
	}
}
