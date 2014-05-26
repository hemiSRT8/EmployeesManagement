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

function addEmployeeResult (result) {
    if (result) {
        alert ('Employee was added successfully');
        window.location.href = "index.html";
    }
    else {
        alert ('An error was occurred , please try again');
        window.location.href = "index.html";
    }
}

