/**
 * 
 */
function disableSubmitButton(){
	var submitButton = document.getElementById("submitButton");
	submitButton.disabled = true;
	
	var form = submitButton.form;
    form.submit();
}