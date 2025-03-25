package jp.co.sfrontier.ojt.employee.servlet.validator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * InputValidatorクラスのメソッドの単体テストを行うテストクラス
 */
class InputValidatorTest {

	InputValidator validator = new InputValidator();

	/**
	 * 社員番号のバリデーションチェックメソッドの正常系テストケース
	 */
	@Test
	void testValidateEmployeeNo_correct() {
		String expected = null;
		String actual = validator.validateEmployeeNo("1234");
		assertEquals(expected, actual);
	}

	/**
	 * 社員番号のバリデーションチェックメソッドの異常系テストケース(値がnullの場合)
	 */
	@Test
	void testValidateEmployeeNo_null() {
		String expected = "必須項目が未入力です。";
		String actual = validator.validateEmployeeNo(null);
		assertEquals(expected, actual);
	}

	/**
	 * 社員番号のバリデーションチェックメソッドの異常系テストケース(値が空文字の場合)
	 */
	@Test
	void testValidateEmployeeNo_empty() {
		String expected = "必須項目が未入力です。";
		String actual = validator.validateEmployeeNo("");
		assertEquals(expected, actual);
	}

	/**
	 * 社員番号のバリデーションチェックメソッドの異常系テストケース(値が0の場合)
	 */
	@Test
	void testValidateEmployeeNo_zero() {
		String expected = "社員番号0番は登録できません。";
		String actual = validator.validateEmployeeNo("0");
		assertEquals(expected, actual);
	}

	/**
	 * 社員番号のバリデーションチェックメソッドの異常系テストケース(値が5文字の場合)
	 */
	@Test
	void testValidateEmployeeNo_5characters() {
		String expected = "4文字以内の半角数値で入力してください。";
		String actual = validator.validateEmployeeNo("12345");
		assertEquals(expected, actual);
	}

	/**
	 * 社員番号のバリデーションチェックメソッドの異常系テストケース(値が英字の場合)
	 */
	@Test
	void testValidateEmployeeNo_alphabet() {
		String expected = "4文字以内の半角数値で入力してください。";
		String actual = validator.validateEmployeeNo("abc");
		assertEquals(expected, actual);
	}

	/**
	 * 社員番号のバリデーションチェックメソッドの異常系テストケース(値が数字と英字が混在している場合)
	 */
	@Test
	void testValidateEmployeeNo_numberAndAlphabet() {
		String expected = "4文字以内の半角数値で入力してください。";
		String actual = validator.validateEmployeeNo("12ab");
		assertEquals(expected, actual);
	}

	/**
	 * 社員番号のバリデーションチェックメソッドの異常系テストケース(値が全角数字の場合)
	 */
	@Test
	void testValidateEmployeeNo_fullWidth() {
		String expected = "4文字以内の半角数値で入力してください。";
		String actual = validator.validateEmployeeNo("１２３４");
		assertEquals(expected, actual);
	}

	/**
	 * 姓(漢字)のバリデーションチェックメソッドの正常系テストケース
	 */
	@Test
	void testValidateLastName_correct() {
		String expected = null;
		String actual = validator.validateLastName("渡辺");
		assertEquals(expected, actual);
	}

	/**
	 * 姓(漢字)のバリデーションチェックメソッドの異常系テストケース(値がnullの場合)
	 */
	@Test
	void testValidateLastName_null() {
		String expected = "必須項目が未入力です。";
		String actual = validator.validateLastName(null);
		assertEquals(expected, actual);
	}

	/**
	 * 姓(漢字)のバリデーションチェックメソッドの異常系テストケース(値が空文字の場合)
	 */
	@Test
	void testValidateLastName_empty() {
		String expected = "必須項目が未入力です。";
		String actual = validator.validateLastName("");
		assertEquals(expected, actual);
	}

	/**
	 * 姓(漢字)のバリデーションチェックメソッドの正常系テストケース(値が10文字の場合)
	 */
	@Test
	void testValidateLastName_10characters() {
		String expected = null;
		String actual = validator.validateLastName("ああああああああああ");
		assertEquals(expected, actual);
	}
	
	/**
	 * 姓(漢字)のバリデーションチェックメソッドの異常系テストケース(値が11文字の場合)
	 */
	@Test
	void testValidateLastName_11characters() {
		String expected = "10文字以内で入力してください。";
		String actual = validator.validateLastName("あああああああああああ");
		assertEquals(expected, actual);
	}

	/**
	 * 名(漢字)バリデーションチェックメソッドの正常系テストケース
	 */
	@Test
	void testValidateFirstName_correct() {
		String expected = null;
		String actual = validator.validateFirstName("花子");
		assertEquals(expected, actual);
	}

	/**
	 * 名(漢字)のバリデーションチェックメソッドの異常系テストケース(値がnullの場合)
	 */
	@Test
	void testValidateFirstName_null() {
		String expected = "必須項目が未入力です。";
		String actual = validator.validateFirstName(null);
		assertEquals(expected, actual);
	}

	/**
	 * 名(漢字)のバリデーションチェックメソッドの異常系テストケース(値が空文字の場合)
	 */
	@Test
	void testValidateFirstName_empty() {
		String expected = "必須項目が未入力です。";
		String actual = validator.validateFirstName("");
		assertEquals(expected, actual);
	}

	/**
	 * 名(漢字)のバリデーションチェックメソッドの正常系テストケース(値が10文字の場合)
	 */
	@Test
	void testValidateFirstName_10characters() {
		String expected = null;
		String actual = validator.validateFirstName("あああああああああああ");
		assertEquals(expected, actual);
	}
	
	/**
	 * 名(漢字)のバリデーションチェックメソッドの異常系テストケース(値が11文字の場合)
	 */
	@Test
	void testValidateFirstName_11characters() {
		String expected = "10文字以内で入力してください。";
		String actual = validator.validateFirstName("あああああああああああ");
		assertEquals(expected, actual);
	}

	/**
	 * 姓(ローマ字)のバリデーションチェックメソッドの正常系テストケース
	 */
	@Test
	void testValidateAlphabetLastName_correct() {
		String expected = null;
		String actual = validator.validateAlphabetLastName("Watanabe");
		assertEquals(expected, actual);
	}

	/**
	 * 姓(ローマ字)のバリデーションチェックメソッドの異常系テストケース(値がnullの場合)
	 */
	@Test
	void testValidateAlphabetLastName_null() {
		String expected = "必須項目が未入力です。";
		String actual = validator.validateAlphabetLastName(null);
		assertEquals(expected, actual);
	}

	/**
	 * 姓(ローマ字)のバリデーションチェックメソッドの異常系テストケース(値が空文字の場合)
	 */
	@Test
	void testValidateAlphabetLastName_empty() {
		String expected = "必須項目が未入力です。";
		String actual = validator.validateAlphabetLastName("");
		assertEquals(expected, actual);
	}

	/**
	 * 姓(ローマ字)のバリデーションチェックメソッドの異常系テストケース(値が数字の場合)
	 */
	@Test
	void testValidateAlphabetLastName_number() {
		String expected = "20文字以内の半角英字で入力してください。";
		String actual = validator.validateAlphabetLastName("1234");
		assertEquals(expected, actual);
	}

	/**
	 * 姓(ローマ字)のバリデーションチェックメソッドの異常系テストケース(値が日本語の場合)
	 */
	@Test
	void testValidateAlphabetLastName_japanese() {
		String expected = "20文字以内の半角英字で入力してください。";
		String actual = validator.validateAlphabetLastName("渡辺");
		assertEquals(expected, actual);
	}

	/**
	 * 姓(ローマ字)のバリデーションチェックメソッドの異常系テストケース(値が全角英字の場合)
	 */
	@Test
	void testValidateAlphabetLastName_fullWidth() {
		String expected = "20文字以内の半角英字で入力してください。";
		String actual = validator.validateAlphabetLastName("Ｗａｔａｎａｂｅ");
		assertEquals(expected, actual);
	}

	/**
	 * 姓(ローマ字)のバリデーションチェックメソッドの正常系テストケース(値が20文字の場合)
	 */
	@Test
	void testValidateAlphabetLastName_20characters() {
		String expected = "20文字以内の半角英字で入力してください。";
		String actual = validator.validateAlphabetLastName("aaaaaaaaaaaaaaaaaaaa");
		assertEquals(expected, actual);
	}
	
	/**
	 * 姓(ローマ字)のバリデーションチェックメソッドの異常系テストケース(値が21文字の場合)
	 */
	@Test
	void testValidateAlphabetLastName_21characters() {
		String expected = "20文字以内の半角英字で入力してください。";
		String actual = validator.validateAlphabetLastName("aaaaaaaaaaaaaaaaaaaaa");
		assertEquals(expected, actual);
	}

	/**
	 * 名(ローマ字)のバリデーションチェックメソッドの正常系テストケース
	 */
	@Test
	void testValidateAlphabetFirstName_correct() {
		String expected = null;
		String actual = validator.validateAlphabetFirstName("Hanako");
		assertEquals(expected, actual);
	}

	/**
	 * 名(ローマ字)のバリデーションチェックメソッドの異常系テストケース(値がnullの場合)
	 */
	@Test
	void testValidateAlphabetFirstName_null() {
		String expected = "必須項目が未入力です。";
		String actual = validator.validateAlphabetFirstName(null);
		assertEquals(expected, actual);
	}

	/**
	 * 名(ローマ字)のバリデーションチェックメソッドの異常系テストケース(値が空文字の場合)
	 */
	@Test
	void testValidateAlphabetFirstName_empty() {
		String expected = "必須項目が未入力です。";
		String actual = validator.validateAlphabetFirstName("");
		assertEquals(expected, actual);
	}

	/**
	 * 名(ローマ字)のバリデーションチェックメソッドの異常系テストケース(値が数字の場合)
	 */
	@Test
	void testValidateAlphabetFirstName_number() {
		String expected = "20文字以内の半角英字で入力してください。";
		String actual = validator.validateAlphabetFirstName("1234");
		assertEquals(expected, actual);
	}
	
	/**
	 * 名(ローマ字)のバリデーションチェックメソッドの異常系テストケース(値が日本語の場合)
	 */
	@Test
	void testValidateAlphabetFirstName_japanese() {
		String expected = "20文字以内の半角英字で入力してください。";
		String actual = validator.validateAlphabetFirstName("花子");
		assertEquals(expected, actual);
	}

	/**
	 * 名(ローマ字)のバリデーションチェックメソッドの異常系テストケース(値が全角英字の場合)
	 */
	@Test
	void testValidateAlphabetFirstName_fullWidth() {
		String expected = "20文字以内の半角英字で入力してください。";
		String actual = validator.validateAlphabetFirstName("Ｈａｎａｋｏ");
		assertEquals(expected, actual);
	}

	/**
	 * 名(ローマ字)のバリデーションチェックメソッドの正常系テストケース(値が20文字の場合)
	 */
	@Test
	void testValidateAlphabetFirstName_20characters() {
		String expected = null;
		String actual = validator.validateAlphabetFirstName("aaaaaaaaaaaaaaaaaaaa");
		assertEquals(expected, actual);
	}
	
	/**
	 * 名(ローマ字)のバリデーションチェックメソッドの異常系テストケース(値が21文字の場合)
	 */
	@Test
	void testValidateAlphabetFirstName_21characters() {
		String expected = "20文字以内の半角英字で入力してください。";
		String actual = validator.validateAlphabetFirstName("aaaaaaaaaaaaaaaaaaaaa");
		assertEquals(expected, actual);
	}

	/**
	 * 部署のバリデーションチェックメソッドの正常系テストケース
	 */
	@Test
	void testValidateDepartment_correct() {
		String expected = null;
		String actual = validator.validateDepartment("システムサービス1部");
		assertEquals(expected, actual);
	}

	/**
	 * 部署のバリデーションチェックメソッドの正常系テストケース(値が20文字の場合)
	 */
	@Test
	void testValidateDepartment_20characters() {
		String expected = "20文字以内で入力してください。";
		String actual = validator.validateDepartment("ああああああああああああああああああああ");
		assertEquals(expected, actual);
	}
	
	/**
	 * 部署のバリデーションチェックメソッドの異常系テストケース(値が21文字の場合)
	 */
	@Test
	void testValidateDepartment_21characters() {
		String expected = "20文字以内で入力してください。";
		String actual = validator.validateDepartment("あああああああああああああああああああああ");
		assertEquals(expected, actual);
	}

}
