package jp.co.sfrontier.ojt.employee.servlet.validator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * InputValidatorクラスのメソッドの単体テストを行うテストクラス
 */
class InputValidatorTest {

	InputValidator validator = new InputValidator();

	/**
	 * 社員番号のバリデーションチェックメソッドのテストケース
	 */
	@Test
	void testValidateEmployeeNo_correct() {
		String expected = null;
		String actual = validator.validateEmployeeNo("1234");
		assertEquals(expected, actual);
	}

	@Test
	void testValidateEmployeeNo_null() {
		String expected = "必須項目が未入力です。";
		String actual = validator.validateEmployeeNo(null);
		assertEquals(expected, actual);
	}

	@Test
	void testValidateEmployeeNo_empty() {
		String expected = "必須項目が未入力です。";
		String actual = validator.validateEmployeeNo("");
		assertEquals(expected, actual);
	}

	@Test
	void testValidateEmployeeNo_zero() {
		String expected = "社員番号0番は登録できません。";
		String actual = validator.validateEmployeeNo("0");
		assertEquals(expected, actual);
	}

	@Test
	void testValidateEmployeeNo_long() {
		String expected = "4文字以内の半角数値で入力してください。";
		String actual = validator.validateEmployeeNo("12345");
		assertEquals(expected, actual);
	}

	@Test
	void testValidateEmployeeNo_alphabet() {
		String expected = "4文字以内の半角数値で入力してください。";
		String actual = validator.validateEmployeeNo("abc");
		assertEquals(expected, actual);
	}

	@Test
	void testValidateEmployeeNo_mix() {
		String expected = "4文字以内の半角数値で入力してください。";
		String actual = validator.validateEmployeeNo("12ab");
		assertEquals(expected, actual);
	}

	@Test
	void testValidateEmployeeNo_fullWidth() {
		String expected = "4文字以内の半角数値で入力してください。";
		String actual = validator.validateEmployeeNo("１２３４");
		assertEquals(expected, actual);
	}

	/**
	 * 姓(漢字)のバリデーションチェックメソッドのテストケース
	 */
	@Test
	void testValidateLastName_correct() {
		String expected = null;
		String actual = validator.validateLastName("渡辺");
		assertEquals(expected, actual);
	}

	@Test
	void testValidateLastName_null() {
		String expected = "必須項目が未入力です。";
		String actual = validator.validateLastName(null);
		assertEquals(expected, actual);
	}

	@Test
	void testValidateLastName_empty() {
		String expected = "必須項目が未入力です。";
		String actual = validator.validateLastName("");
		assertEquals(expected, actual);
	}

	@Test
	void testValidateLastName_long() {
		String expected = "10文字以内で入力してください。";
		String actual = validator.validateLastName("あああああああああああ");
		assertEquals(expected, actual);
	}

	/**
	 * 名(漢字)のバリデーションチェックメソッドのテストケース
	 */
	@Test
	void testValidateFirstName_correct() {
		String expected = null;
		String actual = validator.validateFirstName("花子");
		assertEquals(expected, actual);
	}

	@Test
	void testValidateFirstName_null() {
		String expected = "必須項目が未入力です。";
		String actual = validator.validateFirstName(null);
		assertEquals(expected, actual);
	}

	@Test
	void testValidateFirstName_empty() {
		String expected = "必須項目が未入力です。";
		String actual = validator.validateFirstName("");
		assertEquals(expected, actual);
	}

	@Test
	void testValidateFirstName_long() {
		String expected = "10文字以内で入力してください。";
		String actual = validator.validateFirstName("あああああああああああ");
		assertEquals(expected, actual);
	}

	/**
	 * 姓(ローマ字)のバリデーションチェックメソッドのテストケース
	 */
	@Test
	void testValidateAlphabetLastName_correct() {
		String expected = null;
		String actual = validator.validateAlphabetLastName("Watanabe");
		assertEquals(expected, actual);
	}

	@Test
	void testValidateAlphabetLastName_null() {
		String expected = "必須項目が未入力です。";
		String actual = validator.validateAlphabetLastName(null);
		assertEquals(expected, actual);
	}

	@Test
	void testValidateAlphabetLastName_empty() {
		String expected = "必須項目が未入力です。";
		String actual = validator.validateAlphabetLastName("");
		assertEquals(expected, actual);
	}

	@Test
	void testValidateAlphabetLastName_number() {
		String expected = "20文字以内の半角英字で入力してください。";
		String actual = validator.validateAlphabetLastName("1234");
		assertEquals(expected, actual);
	}

	@Test
	void testValidateAlphabetLastName_japanese() {
		String expected = "20文字以内の半角英字で入力してください。";
		String actual = validator.validateAlphabetLastName("渡辺");
		assertEquals(expected, actual);
	}

	@Test
	void testValidateAlphabetLastName_fullWidth() {
		String expected = "20文字以内の半角英字で入力してください。";
		String actual = validator.validateAlphabetLastName("Ｗａｔａｎａｂｅ");
		assertEquals(expected, actual);
	}

	@Test
	void testValidateAlphabetLastName_long() {
		String expected = "20文字以内の半角英字で入力してください。";
		String actual = validator.validateAlphabetLastName("aaaaaaaaaaaaaaaaaaaaa");
		assertEquals(expected, actual);
	}

	/**
	 * 名(ローマ字)のバリデーションチェックメソッドのテストケース
	 */
	@Test
	void testValidateAlphabetFirstName_correct() {
		String expected = null;
		String actual = validator.validateAlphabetFirstName("Hanako");
		assertEquals(expected, actual);
	}

	@Test
	void testValidateAlphabetFirstName_null() {
		String expected = "必須項目が未入力です。";
		String actual = validator.validateAlphabetFirstName(null);
		assertEquals(expected, actual);
	}

	@Test
	void testValidateAlphabetFirstName_empty() {
		String expected = "必須項目が未入力です。";
		String actual = validator.validateAlphabetFirstName("");
		assertEquals(expected, actual);
	}

	@Test
	void testValidateAlphabetFirstName_number() {
		String expected = "20文字以内の半角英字で入力してください。";
		String actual = validator.validateAlphabetFirstName("1234");
		assertEquals(expected, actual);
	}

	@Test
	void testValidateAlphabetFirstName_japanese() {
		String expected = "20文字以内の半角英字で入力してください。";
		String actual = validator.validateAlphabetFirstName("花子");
		assertEquals(expected, actual);
	}

	@Test
	void testValidateAlphabetFirstName_fullWidth() {
		String expected = "20文字以内の半角英字で入力してください。";
		String actual = validator.validateAlphabetFirstName("Ｈａｎａｋｏ");
		assertEquals(expected, actual);
	}

	@Test
	void testValidateAlphabetFirstName_long() {
		String expected = "20文字以内の半角英字で入力してください。";
		String actual = validator.validateAlphabetFirstName("aaaaaaaaaaaaaaaaaaaaa");
		assertEquals(expected, actual);
	}

	/**
	 * 部署のバリデーションチェックメソッドのテストケース
	 */
	@Test
	void testValidateDepartment_correct() {
		String expected = null;
		String actual = validator.validateDepartment("システムサービス1部");
		assertEquals(expected, actual);
	}

	@Test
	void testValidateDepartment_long() {
		String expected = "20文字以内で入力してください。";
		String actual = validator.validateDepartment("あああああああああああああああああああああ");
		assertEquals(expected, actual);
	}

}
