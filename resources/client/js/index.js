function pageLoad(){
  const username = Cookies.get("username");
  if(typeof username == "string"){
      document.getElementById("logoutButton").removeAttribute("hidden");
      document.getElementById("loginMessage").setAttribute("hidden", true);
  }else if(typeof username == "undefined"){
    document.getElementById("logoutButton").setAttribute("hidden",true);
  }


}

function getCookie(cname) {
  var name = cname + "=";
  var decodedCookie = decodeURIComponent(document.cookie);
  var ca = decodedCookie.split(';');
  for(var i = 0; i <ca.length; i++) {
    var c = ca[i];
    while (c.charAt(0) == ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}
