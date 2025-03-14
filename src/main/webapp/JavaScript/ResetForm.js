/**
 * クリアボタン押下で検索フォームをクリアするJavaScript
 */
function resetForm(){
	document.searchEmployee.employeeNo.value = "";
    document.searchEmployee.lastName.value = "";
    document.searchEmployee.firstName.value = "";
    document.searchEmployee.alphabetLastName.value = "";
    document.searchEmployee.alphabetFirstName.value = "";
    document.searchEmployee.birthdayFrom.value = "";
    document.searchEmployee.birthdayTo.value = "";
    document.searchEmployee.hireDateFrom.value = "";
    document.searchEmployee.hireDateTo.value = "";
    document.searchEmployee.department.value = "";
}