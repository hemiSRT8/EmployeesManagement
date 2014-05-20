function deleteConfirmation(param) {
    if (confirm("Are you sure ?\nThis action is unchangeable")) {
       param.submit();
    }
}

function editDepartmentValidation (result) {
    if (result) {
        alert('Department was edited successfully');
        window.location.href = "viewAllDepartments.html";
    } else {
        alert ('An error was occurred , please try again');
        window.location.href = "viewAllDepartments.html";
    }
}

function deleteEmployeeValidation (result) {
    if (result) {
        alert('Employee was deleted successfully');
        window.location.href = "index.html";
    } else {
        alert ('An error was occurred , please try again');
        window.location.href = "index.html";
    }
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

function editFormValidation (result) {
    if (result) {
        alert('Employee was edited successfully');
        window.location.href = "index.html";
    } else {
        alert ('An error was occurred , please try again');
        window.location.href = "index.html";
    }
}

function editFormDateOfBirthValidation (form) {
     if (form.dateOfBirth.value == "") {
        return true;
     }

     if (!(/^(19|20)\d\d[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])$/.test(form.dateOfBirth.value))) {
        alert('Date of birth format must be :\nIn format : YYYY-MM-DD\nBetween : 1900-01-01 and 2099-12-31\nTry again !');
        form.dateOfBirth.focus();
        return false;
     }
}

function departmentAddValidation(result) {
    if (result) {
        alert ('Department was added successfully');
        window.location.href = "viewAllDepartments.html";
    }
    else {
        alert ('An error was occurred , please try again');
        window.location.href = "viewAllDepartments.html";
    }
}

function addEmployeesToDepartmentValidation (result) {
    if (result) {
        alert ('Employee(s) was(were) added to departments successfully');
        window.location.href = "addEmployeesToDepartment.html";
    }
    else {
        alert ('An error was occurred , please try again');
        window.location.href = "addEmployeesToDepartment.html";
    }
}


