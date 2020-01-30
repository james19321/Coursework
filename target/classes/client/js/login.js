function pageLoad(token){
    if(token==null){
        document.getElementById('loginStatus').innerHTML;
    }
}

function getCookie(cname){
    var name= cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for (var i=0;i<ca.length;i++){
        var c =ca[i];
        while(c.charAt(0) == ''){
            c=c.substring(1);
        }
        if(c.indexOf(name) == 0){
            return c.substring(name.length,c.length);
        }
    }
    return "";
}

function checkCookie() {
    var cookie = getCookie("token");
    if(username != ""){
        alert("Welcome back");
    }else{
        document.getElementById('loginStatus').innerHTML = 'Not Logged In';
    }
}

checkCookie('token');

const username=document.getElementById('username');
const password=document.getElementById('password');
const form=document.getElementById('form');