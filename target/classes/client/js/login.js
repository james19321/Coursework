function login(event) {
    //prevent dfault stops the page reloading when event "sumbit" happens which allows my code to run instead of the broser dealing with the event
    event.preventDefault();

    //This code creates a javascript object of the login form with username and password
    const form = document.getElementById("loginForm");
    const formData = new FormData(form);

    //This is an api call with path /users/login which passes the data from the login form
    fetch("/users/login", { method: 'POST', body: formData })
        .then(response => response.json())
.then(responseData => {
 
    //this is checking wether the API has returned an error and if so showing it in browser
    if (responseData.hasOwnProperty('error')) {
        alert(responseData.error);
    } 
    //this code sets the cookies username and token to the ones which we recieve from the Java API
    else {
        Cookies.set("username", responseData.username);
        Cookies.set("token", responseData.token);
        
        //this code sends us to the page with address localhost:8081/client/index.html
        window.location.href = '/client/index.html';
    }
}
).catch((error) => {
        console.error('Error:', error);
});
}

function logout(event) {

    event.preventDefault();
    fetch("/users/logout", {method: 'POST'}
    ).then(response => response.json()
).then(responseData => {
        if (responseData.hasOwnProperty('error')) {

        alert(responseData.error);

    } else {
        console.log("hello world");
        Cookies.remove("username");
        Cookies.remove("token");

        window.location.href = '/client/index.html';

    }
});

}