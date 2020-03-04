/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function muestraLogin() {
    document.getElementById("popup").classList.remove('oculto');
    window.setTimeout(function () {
        document.getElementById("login").classList.add('escalar');
        document.getElementById("fondoPopUp").classList.add('fondoVisiblePopUp');
    }, 100);

}

function salirPopUp() {
    document.getElementById("login").classList.remove('escalar');
    document.getElementById("fondoPopUp").classList.remove('fondoVisiblePopUp');
    window.setTimeout(function () {
        document.getElementById("popup").classList.add('oculto');

    }, 500);

}