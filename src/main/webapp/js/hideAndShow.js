function show1_onclick() {
    document.getElementById('departmentsFullInfoContainer').style.display='';
    document.getElementById('departmentsOnlyNamesContainer').style.display='none';
    document.getElementById('container').style.height='450px';
}

function show2_onclick() {
    document.getElementById('departmentsOnlyNamesContainer').style.display='';
    document.getElementById('departmentsFullInfoContainer').style.display='none';
    document.getElementById('container').style.height='450px';
}

function editField_onclick (divId) {
    document.getElementById('deleteButton').style.display='none';
    document.getElementById('editButton').style.display='none';
    document.getElementById(divId).style.display='';
}

