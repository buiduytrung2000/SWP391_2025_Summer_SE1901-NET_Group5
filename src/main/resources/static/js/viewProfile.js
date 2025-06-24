function editProfile() {
    document.getElementById('editForm').style.display = "block";
}
function hidePage2() {
    document.getElementById('profilemain').classList.remove('blurred');
    document.getElementById('overlay').style.display = 'none';
    document.getElementById('editcustomer').style.display = 'none';
}
function closeEditProfile() {
    document.getElementById('editForm').style.display = "none";
}
