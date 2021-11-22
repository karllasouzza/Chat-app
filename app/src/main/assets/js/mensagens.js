let container = document.getElementById("msg");

let message = Ponte.seachMessages(" ", " ");
console.log("Linha 3 - " + message);

let resultM = "";
resultM = new String();
resultM = Ponte.getMessages();

exec();

function exec() {
  userName = Ponte.getUserName();

  console.log("Linha 8 - " + resultM);
  if (resultM[0] != "" && resultM != undefined) {
    let preresultFinal = resultM.split("|");

    console.log("Linha 11 - " + preresultFinal);

    for (let index = 0; index < preresultFinal.length; index++) {
      let v = "";
      v = new String();
      if (/\S/.test(preresultFinal[index])) {
        v = preresultFinal[index];

        console.log("Linha 18 - " + v);

        let resultFinal = v.split("--S--");

        console.log("Linha 23 - " + resultFinal[0]);

        let Name_formated = resultFinal[2];

        console.log("Linha 26 - " + Name_formated);


          container.innerHTML += `
          <div class=" ${resultFinal[2] != userName ? 'li' : 'li_me'} ">
          <div>
          <figure class="img ${letter_to_image(Name_formated)}"></figure>
          <figcaption>${Name_formated}</figcaption>
          </div>
          <span>${resultFinal[1]}</span>
          </div>`;


        let heightPage = document.body.scrollHeight;
        window.scrollTo(0, heightPage);
      }
    }
  }
}

setInterval(() => {
  Ponte.seachMessages(" ", " ");

  if (resultM != Ponte.getMessages()) {
    resultM = Ponte.getMessages();
    container.innerHTML = "";
    exec();
  }
}, 6000);

function letter_to_image(first_letter) {
  if (first_letter[0].toUpperCase() === "A") {
    return "image_a";
  } else if (first_letter[0].toUpperCase() === "B") {
    return "image_b";
  } else if (first_letter[0].toUpperCase() === "C") {
    return "image_c";
  } else if (first_letter[0].toUpperCase() === "D") {
    return "image_d";
  } else if (first_letter[0].toUpperCase() === "E") {
    return "image_e";
  } else if (first_letter[0].toUpperCase() === "F") {
    return "image_f";
  } else if (first_letter[0].toUpperCase() === "G") {
    return "image_g";
  } else if (first_letter[0].toUpperCase() === "H") {
    return "image_h";
  } else if (first_letter[0].toUpperCase() === "I") {
    return "image_i";
  } else if (first_letter[0].toUpperCase() === "J") {
    return "image_j";
  } else if (first_letter[0].toUpperCase() === "K") {
    return "image_k";
  } else if (first_letter[0].toUpperCase() === "L") {
    return "image_l";
  } else if (first_letter[0].toUpperCase() === "M") {
    return "image_m";
  } else if (first_letter[0].toUpperCase() === "N") {
    return "image_n";
  } else if (first_letter[0].toUpperCase() === "O") {
    return "image_o";
  } else if (first_letter[0].toUpperCase() === "P") {
    return "image_p";
  } else if (first_letter[0].toUpperCase() === "Q") {
    return "image_q";
  } else if (first_letter[0].toUpperCase() === "R") {
    return "image_r";
  } else if (first_letter[0].toUpperCase() === "S") {
    return "image_s";
  } else if (first_letter[0].toUpperCase() === "T") {
    return "image_t";
  } else if (first_letter[0].toUpperCase() === "U") {
    return "image_u";
  } else if (first_letter[0].toUpperCase() === "V") {
    return "image_v";
  } else if (first_letter[0].toUpperCase() === "W") {
    return "image_w";
  } else if (first_letter[0].toUpperCase() === "X") {
    return "image_x";
  } else if (first_letter[0].toUpperCase() === "Y") {
    return "image_y";
  } else if (first_letter[0].toUpperCase() === "Z") {
    return "image_z";
  } else {
    return "image_special";
  }
}
