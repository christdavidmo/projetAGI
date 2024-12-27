

function toggleMenu(){
    const menu = document.getElementById("menu");
    const menu_burger = document.getElementById("menu-burger");
    const overlay = document.getElementById("menu-overlay");

    menu.classList.toggle("active");
    menu_burger.classList.toggle("active");
    overlay.classList.toggle("active");
}


function CloseMenu(){
    const menu = document.getElementById("menu");
    const menu_burger = document.getElementById("menu-burger");
    const overlay = document.getElementById("menu-overlay");

    menu.classList.remove("active");
    menu_burger.classList.remove("active");
    overlay.classList.remove("active");

}