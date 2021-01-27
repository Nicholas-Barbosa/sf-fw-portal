


function montaTr(paciente) {
    var pacienteTr = document.createElement("tr");
    pacienteTr.classList.add("conhecimento");

    pacienteTr.appendChild(montaTd(paciente.nome));
    pacienteTr.appendChild(montaTd(paciente.peso));
    pacienteTr.appendChild(montaTd(paciente.altura));
    pacienteTr.appendChild(montaTd(paciente.gordura));
    pacienteTr.appendChild(montaTd(paciente.imc));

    return pacienteTr;
}

function montaTd(dado) {
    var td = document.createElement("td");
    td.textContent = dado;

    return td;
}  
var paciente ={
    nome:"Nicholas",
    peso:"80",
    altura:"1.77",
    gordura:"20",
    imc:"12"
}

var pacienteTr = montaTr(paciente);
var tabela = document.querySelector("#tabela-conhecimentos");
tabela.appendChild(pacienteTr);

var pacienteTr = montaTr(paciente);
tabela.appendChild(pacienteTr);