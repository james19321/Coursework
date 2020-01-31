function pageLoad() {
}

function login(event) {

    event.preventDefault();

    const form = document.getElementById("loginForm");
    const formData = new FormData(form);

    fetch("/users/login", { method: 'POST', body: formData })
        .then(response => response.json())
.then(responseData => {

        if (responseData.hasOwnProperty('error')) {
        alert(responseData.error);
    } else {
        Cookies.set("username", responseData.username);
        Cookies.set("token", responseData.token);

        // window.location.href = '/client/index.html';
    }
}
).catch((error) => {
        console.error('Error:', error);
});
}

function logout() {

    fetch("/users/logout", {method: 'POST'}
    ).then(response => response.json()
).then(responseData => {
        if (responseData.hasOwnProperty('error')) {

        alert(responseData.error);

    } else {
        console.log("hello world");
        Cookies.remove("username");
        Cookies.remove("token");

        // window.location.href = '/client/index.html';

    }
});

}