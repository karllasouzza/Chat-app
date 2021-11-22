let Name = document.getElementById("name");
let Email = document.getElementById("email");
let Password = document.getElementById("password");

let form = document.querySelector("form");

form.addEventListener("submit", function (event) {
  event.preventDefault();
  
  Ponte.insereUsuario(Name.value, Email.value, Password.value);
  location.href = "./login.html";
});
