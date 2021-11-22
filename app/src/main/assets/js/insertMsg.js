let form = document.getElementById("insert_message");
let content = document.getElementById("content");
let Email = Ponte.getUserEmail();
let Password = Ponte.getUserPassword();

Ponte.seachUser(Email, Password);

let interval;

let userName;

interval = setInterval(() => {
  userName = Ponte.getUserName();

  clearInterval(interval);
}, );

form.addEventListener("submit", (event) => {
  Ponte.insereMsg(content.value, userName);
  content.textContent = ""
});
