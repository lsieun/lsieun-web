// Used to toggle the menu on small screens when clicking on the menu button
function click_menu_icon() {
    var x = document.getElementById("nav_mini_bar");
    if (x.className.indexOf("w3-show") == -1) {
        x.className += " w3-show";
    } else {
        x.className = x.className.replace(" w3-show", "");
    }
}