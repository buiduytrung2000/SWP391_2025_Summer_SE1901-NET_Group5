const user = /*[[${session.user}]]*/;

if(user != null) {
    document.getElementById('avatar').removeAttribute('disabled');
} else {
    document.getElementById('avatar').setAttribute('disabled', true);
}
