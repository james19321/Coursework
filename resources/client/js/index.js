function pageLoad(){
  let now = new Date();

  document.getElementById("testDiv").innerHTML = '<div style="text-align:center;">'
      + '<h1>Welcome to my API powered website!</h1>'
      + '<img src="/client/img/website logo.png"  alt="Logo"/>'
      + '<div style="font-style: italic;">'
      + 'Generated at ' + now.toLocaleTimeString()
      + '</div>'
      + '</div>';

}
