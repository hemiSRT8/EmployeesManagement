 function ValidateForm(frm) {
    if (frm.firstName.value == "") {alert('First Name is required.');frm.firstName.focus();return false;}
    if (frm.lastName.value == "") {alert('Last Name is required.');frm.lastName.focus();return false;}

    if (frm.dateOfBirth.value === "") {

        alert('Date Of Birth is required.');
        frm.dateOfBirth.focus();
        return false;
    }

    if (!(/^(\d\d(?:\d\d)?)-(\d\d?)-(\d\d?)$/.test(frm.dateOfBirth.value))) {
		alert('Date Of Birth should be in yyyy-MM-dd format');
        frm.dateOfBirth.focus();
        return false;
    }
	if (frm.wage.value == "") {
		alert('wage is required.');
		frm.wage.focus();
		return false;
	}
    return true;
}