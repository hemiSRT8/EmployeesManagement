function deleteConfirmation(param) {
    if (confirm("Are you sure ?\nThis action is unchangeable")) {
       param.submit();
    }
}

function editDepartmentValidation(result) {
    if (result) {
        alert('Department was edited successfully');
    } else {
        alert ('An error was occurred , please try again');
    }
    window.location.href = "viewAllDepartments.html";
}

function deleteEmployeeValidation (result) {
    if (result) {
        alert('Employee was deleted successfully');
    } else {
        alert ('An error was occurred , please try again');
    }
    window.location.href = "index.html";
}

function addDepartmentValidation(form) {
    if (form.departmentName.value == "") {
        alert('Department name is required.');
        form.departmentName.focus();
        return false;
    }
    return true;
}

function deleteDepartmentValidation(result) {
    if (result) {
        alert('Department was deleted successfully');
        window.location.href = "viewAllDepartments.html";
    } else {
        alert ('An error was occurred , please try again');
        window.location.href = "index.html";
    }
}

function editFormValidation(result) {
    if (result) {
        alert('Employee was edited successfully');
    } else {
        alert ('An error was occurred , please try again');
    }
    window.location.href = "index.html";
}

function editFormDateOfBirthValidation (form) {
     if (form.dateOfBirth.value == "") {
        return true;
     }
     if (!(/^(19|20)\d\d[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])$/.test(form.dateOfBirth.value))) {
        alert('Date of birth format must be:\nIn format: YYYY-MM-DD\nBetween : 1900-01-01 and 2099-12-31\nTry again!');
        form.dateOfBirth.focus();
        return false;
     }
}

function departmentAddValidation(result) {
    if (result) {
        alert ('Department was added successfully');
    }
    else {
        alert ('An error was occurred , please try again');
    }
    window.location.href = "viewAllDepartments.html";
}

function addEmployeesToDepartmentValidation (result) {
    if (result) {
        alert ('Employee(s) was(were) added to departments successfully');
    }
    else {
        alert ('An error was occurred , please try again');
    }
    window.location.href = "addEmployeesToDepartment.html";
}

 function validateForm(frm) {
    if (frm.firstName.value == "") {
        alert('First Name is required.');
        frm.firstName.focus();
        return false;
    }
    if (frm.lastName.value == "") {
        alert('Last Name is required.');
            frm.lastName.focus();
            return false;
    }
    if(frm.firstName.value.length > 46) {
     alert('First Name length should be less than 46.');
            frm.firstName.focus();
            return false;

    }

    if(frm.lastName.value.length > 46) {
          alert('Last Name length should be less than 46.');
                frm.firstName.focus();
                return false;

    }

    if (frm.dateOfBirth.value == "") {
        alert('Date Of Birth is required.');
        frm.dateOfBirth.focus();
        return false;
    }

    if (!(/^(19|20)\d\d[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])$/.test(frm.dateOfBirth.value))) {
		alert('Date Of Birth should be in yyyy-MM-dd format.\nBetween : 1900-01-01 and 2099-12-31');
        frm.dateOfBirth.focus();
        return false;
    }
    return true;
}

function isEmployeeAdded(addResult) {
    if(addResult) {
        alert("Employee was added.");

    } else {
        alert("Error is occurred.\nTry again..");
    }
     window.location.href = "addEmployeeMenu.html";
}

