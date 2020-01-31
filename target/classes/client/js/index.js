function pageLoad(){
  const username = Cookies.get("username");
  if(typeof username == "string"){
      document.getElementById("loginStatus").removeAttribute("hidden");
  }else if(typeof username == "undefined"){
    document.getElementById("loginStatus").setAttribute("hidden",true);
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
