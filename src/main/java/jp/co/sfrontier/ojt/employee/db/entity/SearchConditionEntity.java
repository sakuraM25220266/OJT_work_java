package jp.co.sfrontier.ojt.employee.db.entity;

import java.sql.Date;

public class SearchConditionEntity {
	private int employeeNo;
	private String lastName;
	private String firstName;
	private String alphabetLastName;
	private String alphabetFirstName;
	private Date birthdayFrom;
	private Date birthdayTo;
	private Date hireDateFrom;
	private Date hireDateTo;
	private String department;

	//コンストラクタ
	public SearchConditionEntity(int employeeNo, String lastName, String firstName, String alphabetLastName,
			String alphabetFirstName, Date birthdayFrom, Date birthdayTo, Date hireDateFrom, Date hireDateTo,
			String department) {
		this.employeeNo = employeeNo;
		this.lastName = lastName;
		this.firstName = firstName;
		this.alphabetLastName = alphabetLastName;
		this.alphabetFirstName = alphabetFirstName;
		this.birthdayFrom = birthdayFrom;
		this.birthdayTo = birthdayTo;
		this.hireDateFrom = hireDateFrom;
		this.hireDateTo = hireDateTo;
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

	public Date getBirthdayFrom() {
		return birthdayFrom;
	}

	public void setBirthdayFrom(Date birthdayFrom) {
		this.birthdayFrom = birthdayFrom;
	}

	public Date getBirthdayTo() {
		return birthdayTo;
	}

	public void setBirthdayTo(Date birthdayTo) {
		this.birthdayTo = birthdayTo;
	}

	public Date getHireDateFrom() {
		return hireDateFrom;
	}

	public void setHireDateFrom(Date hireDateFrom) {
		this.hireDateFrom = hireDateFrom;
	}

	public Date getHireDateTo() {
		return hireDateTo;
	}

	public void setHireDateTo(Date hireDateTo) {
		this.hireDateTo = hireDateTo;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
}
