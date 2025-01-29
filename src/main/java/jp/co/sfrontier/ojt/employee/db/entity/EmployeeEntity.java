package jp.co.sfrontier.ojt.employee.db.entity;

import java.sql.Date;

/**
 * DBのテーブルを表すデータクラス
 */
public class EmployeeEntity {
	private int employeeNo;
	private String lastName;
	private String firstName;
	private String alphabetLastName;
	private String alphabetFirstName;
	private Date birthday;
	private Date hiredOn;
	private String department;
	
	//コンストラクタ
	public EmployeeEntity(int employeeNo, String lastName, String firstName, String alphabetLastName,
			String alphabetFirstName, Date birthday, Date hiredOn, String department) {
		this.employeeNo = employeeNo;
		this.lastName = lastName;
		this.firstName = firstName;
		this.alphabetLastName = alphabetLastName;
		this.alphabetFirstName = alphabetFirstName;
		this.birthday = birthday;
		this.hiredOn = hiredOn;
		this.department = department;
	}

	//セッター・ゲッター
	public int getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(int employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getAlphabetLastName() {
		return alphabetLastName;
	}

	public void setAlphabetLastName(String alphabetLastName) {
		this.alphabetLastName = alphabetLastName;
	}

	public String getAlphabetFirstName() {
		return alphabetFirstName;
	}

	public void setAlphabetFirstName(String alphabetFirstName) {
		this.alphabetFirstName = alphabetFirstName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getHiredOn() {
		return hiredOn;
	}

	public void setHiredOn(Date hiredOn) {
		this.hiredOn = hiredOn;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

}
