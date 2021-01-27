function find(){
            var cnpj= document.getElementById("inputCnpj").value;
            if(verifyCurrentAndLastCnpj(cnpj)){
                addStyleForDivMsg("Este CNPJ foi usado como parametro para a busca anterior.",'alert-danger');
                 
            }else{ 
                deleteAllRowsFromTable();

                view.makeRequest(cnpj);
             
            }
            
}
function verifyCurrentAndLastCnpj(cnpj){
    return cnpj == ByRemetenteView._lastCnpj;
}

function deleteAllRowsFromTable(){
    var parent = document.getElementById("tabela-conhecimentos");
    while(parent.hasChildNodes()) {
        parent.removeChild(parent.firstChild);
    }
}

function addStyleForDivMsg(msgToPrint,alert){
    var msg = document.getElementById("divAlert");
    if(msg.classList.contains('alert-warning'))
    msg.classList.remove('alert-warning');
    
    if(msg.classList.contains('alert-danger'))
    msg.classList.remove('alert-danger');

    msg.hidden =false; 

    msg.textContent=msgToPrint;
    msg.classList.add(alert);
}



    function closeModal(){
    console.log("closing modal...");
    $("#fetchModal").modal('hide');
    }
    
    
  

    class ByRemetenteView{
    
        static _lastCnpj;
        

        makeRequest(cnpj,currentPage,size){
             var client = new XMLHttpRequest();
             client.onload = function() {
                // in case of network errors this might not give reliable results
             
            var response = JSON.parse(client.responseText);
            var view = new ByRemetenteView();
            view.changeCssClassOfDivAndAddMessage(client.status,response,cnpj);
              
              }
              client.open("GET", "https://farawaybr.com/conhecimentos/rest/findByRemetente/"+cnpj);
              client.send();
        }
    
    
        changeCssClassOfDivAndAddMessage(status,response,cnpj){
                  
            const cssWarning = 'alert-warning';
            const cssSucess ='alert-success';
            const cssDanger = 'alert-danger';
            
            if(status==200){
                addStyleForDivMsg("Conhecimentos encontrados.",cssSucess );
                this.generateTags(response);
                
              
               
            }else{
                if(status=404){
                    addStyleForDivMsg("Nenhum conhecimento encontrado.",cssWarning );
                }else{
                    addStyleForDivMsg("Erro interno no servidor!",cssDanger );
                    
                }
                
            }
            ByRemetenteView._lastCnpj=cnpj;
        }
        
       generateTags(responseJson){

        var tabela = document.querySelector("#tabela-conhecimentos");
        var content = responseJson.content;
        var objectBuilder = new ConhecimentoBuilderService();
        
    //    console.log("content " +content.length);
    //     for(var i=0;i<content.length;i++){
    //         var conhecimentoTr = this.montaTr(content[i]);
    //                tabela.appendChild(conhecimentoTr);
    //     }
        for(var i=0; i<content.length;i++){
               // this.generateTable(tabela,conhecimento);
               var conhecimento = objectBuilder.createConhecimentoObject(content[i]);
              
               var conhecimentoTr = this.montaTr(conhecimento);
               tabela.appendChild(conhecimentoTr);
            }
     }

   
     montaTr(conhecimento) {
        var conhecimentoTr = document.createElement("tr");
        conhecimentoTr.classList.add("conhecimento");
    
        conhecimentoTr.appendChild(this.montaTd(conhecimento.getChave()));
        conhecimentoTr.appendChild(this.montaTd(conhecimento.getNumero()));
        conhecimentoTr.appendChild(this.montaTd(conhecimento.getSerie()));
        conhecimentoTr.appendChild(this.montaTd(conhecimento.getEmissao()));
        conhecimentoTr.appendChild(this.montaTd(conhecimento.getTotal()));

        var emitente = conhecimento.getEmitente();
        conhecimentoTr.appendChild(this.montaTd(emitente.getNome()));
        

        var remetente = conhecimento.getRemetente();
        conhecimentoTr.appendChild(this.montaTd(remetente.getNome()));
      

        var destinatario = conhecimento.getDestinatario();
        conhecimentoTr.appendChild(this.montaTd(destinatario.getNome()));
       
        //   conhecimentoTr.appendChild(this.montaTd(conhecimento.numero));
        // conhecimentoTr.appendChild(this.montaTd(conhecimento.serie));
        // conhecimentoTr.appendChild(this.montaTd(conhecimento.chave));
        // conhecimentoTr.appendChild(this.montaTd(conhecimento.emissao));
        return conhecimentoTr;
    }
    montaTd(dado) {
        var td = document.createElement("td");
  
        td.textContent = dado;
        
        return td;
    }
    }
    var view = new ByRemetenteView();