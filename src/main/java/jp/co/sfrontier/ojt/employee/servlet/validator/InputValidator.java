package jp.co.sfrontier.ojt.employee.servlet.validator;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeParseException;

/**
 * バリデーションチェックの処理を行うクラス。
 */
public class InputValidator {

	//社員番号のバリデーションメソッド
	public String validateEmpNo(String empNoStr) {
		if (empNoStr == null || empNoStr.equals("")) {
			return "必須項目が未入力です。";
		}
		if (empNoStr.matches("^0{1,4}$")) {
			return "社員番号0番は登録できません。";
		}
		if (!empNoStr.matches("^[0-9]{1,4}$")) {
			return "4文字以内の半角数値で入力してください。";
		}
		//バリデーションチェックOKの時
		return null;
	}

	//氏名のバリデーションメソッド
	public String validateName(String lastName, String firstName) {
		if (lastName == null || lastName.equals("") || firstName == null || firstName.equals("")) {
			return "必須項目が未入力です。";
		}
		if (lastName.length() > 10 || firstName.length() > 10) {
			return "10文字以内で入力してください。";
		}
		return null;
	}

	//氏名(ローマ字)のバリデーションメソッド
	public String validateAlphabetName(String alphabetLastName, String alphabetFirstName) {
		if (alphabetLastName == null || alphabetLastName.equals("") || alphabetFirstName == null
				|| alphabetFirstName.equals("")) {
			return "必須項目が未入力です。";
		}
		if (!alphabetLastName.matches("^[A-Za-z]{1,20}$") || !alphabetFirstName.matches("^[A-Za-z]{1,20}$")) {
			return "20文字以内のアルファベットで入力してください。";
		}
		return null;
	}

	//年月日のバリデーションメソッド
	public String validateDate(Date date) {
		// 日付がnullの場合はバリデーションを行わない
		if (date == null) {
			return null;
		}
		try {
			//Date型をLocalDate型に変換する
			LocalDate parsedDate = date.toLocalDate();

			// 1900年1月1日から現在の日付までの範囲をチェック
			LocalDate minDate = LocalDate.of(1900, Month.JANUARY, 1);
			LocalDate currentDate = LocalDate.now();

			// 範囲外の日付の場合
			if (parsedDate.isBefore(minDate) || parsedDate.isAfter(currentDate)) {
				return "存在しない年月日です。選択しなおしてください。";
			}
		} catch (DateTimeParseException e) {
			// 無効な日付形式、または存在しない日付の場合
			return "存在しない年月日です。選択しなおしてください。";
		}

		return null;
	}

	//部署のバリデーションメソッド
	public String validateDepartment(String department) {
		if (department.length() > 20) {
			return "20文字以内で入力してください。";
		}
		return null;
	}
}
