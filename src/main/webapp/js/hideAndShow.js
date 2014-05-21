function hiddenEditField_onclick (divId) {
    document.getElementById(divId).style.display='';
}

function addDepartment_onclick () {
    document.getElementById('addDepartment').style.display='';
}

function addEmployeesToDepartment_onclick () {
     window.location.href = "addEmployeesToDepartment.html";
}

function hideAddDepartmentBlock () {
    document.getElementById('addDepartment').style.display='none';
}

function hideEditOperationBlock (divId) {
    document.getElementById(divId).style.display='none';
}

function cancelAddEmployeesToDepartmentOperation () {
    window.location.href = "viewAllDepartments.html";
}