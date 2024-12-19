

document.addEventListener("DOMContentLoaded", function (){
    const menuB = document.getElementById("burger-menu");
    const menuN = document.getElementById("nav-menu");

    menuB.addEventListener('click',function (){
        menuN.classList.toggle('show');
    })
});

document.addEventListener('click',function (event){
    const menuB = document.getElementById("burger-menu");
    const menuN = document.getElementById("nav-menu");

    if ( !menuB.contains(event.target) && ! menuN.contains(event.target)) {
        menuN.classList.remove('show');
    }
})