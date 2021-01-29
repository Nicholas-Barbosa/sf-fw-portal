    function find(){
            var cnpj= document.getElementById("inputCnpj").value;
            console.log("cnpj lenght " +cnpj.length)
           if(cnpj.length>0){
            if(verifyCurrentAndLastCnpj(cnpj)){
                view.modifyStyleAndAddForDivMsg("Este CNPJ foi usado como parametro para a busca anterior.",'alert-danger');
                 
            }else{ 
                view.removeAllEntriesFromTableConhecimentos();
                
                view.findConhecimentoByRemetente(cnpj);
             
            }
        }else
            view.modifyStyleAndAddForDivMsg("Campo CNPJ invalido!",'alert-danger');
           
}
function verifyCurrentAndLastCnpj(cnpj){
    return cnpj == ByRemetenteView._lastCnpj;
}


function closeModal(){
    console.log("closing modal...");
    $("#fetchModal").modal('hide');
}
    


  class ByRemetenteView{
    
        static _lastCnpj;
        _divMsg = document.getElementById("divAlert");
        _btnFindConhecimento = document.getElementById("btnFindConhecimentoByRemetente");
        _tableConhecimentos = document.getElementById("tabela-conhecimentos");;

        getDivMsg(){
            return this._divMsg;
        }

        getBtnFindConhecimento(){
            return this._btnFindConhecimento;
        }

        removeAllEntriesFromTableConhecimentos(){
            while(this._tableConhecimentos.hasChildNodes()) {
                this._tableConhecimentos.removeChild(this._tableConhecimentos.firstChild);
            }
        }
        modifyStyleAndAddForDivMsg(msgToPrint,style){
            var lenght =  this._divMsg.classList.length;
            var itemToRemoveFromStyle =  this._divMsg.classList.item(lenght-1);
            this._divMsg.classList.replace(itemToRemoveFromStyle,style);
            this._divMsg.hidden =false; 
        
            this._divMsg.textContent=msgToPrint;
           
        }

        modifyBtnFindConhecimento(msg,style){
           
            var lenght = this._btnFindConhecimento.classList.length;
            var itemToRemoveFromStyle = this._btnFindConhecimento.classList.item(lenght-1);
            this._btnFindConhecimento.classList.replace(itemToRemoveFromStyle,style);
           
            this._btnFindConhecimento.textContent  =msg;
        }

        findConhecimentoByRemetente(cnpj,currentPage,size){
            this.modifyBtnFindConhecimento("Procurando...",'btn-outline-info');
            this.modifyStyleAndAddForDivMsg("Procurando por conhecimentos na API FarAway...",'alert-info');

            if(currentPage == undefined){
                currentPage=0;
            }
            if(size==undefined){
                size = 12;
            }
            console.log(currentPage)
             var client = new XMLHttpRequest();
             client.onload = function() {
                // in case of network errors this might not give reliable results
                try{
                    var response = JSON.parse(client.responseText);
                    console.log("current page of API " +response.number);
                    var view = new ByRemetenteView();
                    view.convertToObjectAndReturnMsg(client.status,response,cnpj);
                }catch(e){
                    if(e instanceof Error){
                        this.modifyStyleAndAddForDivMsg(e+ " client js!",'alert-danger');
                    }
                }
                
              
              }
              client.open("GET", "https://farawaybr.com/conhecimentos/rest/findByRemetente/"+cnpj+'?page='+currentPage+'&size='+size);
              client.send();
          
           
            }
        
    
    
        convertToObjectAndReturnMsg(status,response,cnpj){
           
            const cssWarning = 'alert-warning';
            const cssSucess ='alert-success';
            const cssDanger = 'alert-danger';
            
            if(status==200){
                this.modifyStyleAndAddForDivMsg("Conhecimentos encontrados.",cssSucess );
                this.generateTags(response);
            }else{
                if(status=404){
                    this.modifyStyleAndAddForDivMsg("Nenhum conhecimento encontrado.",cssWarning );
                }else{
                    this.modifyStyleAndAddForDivMsg("Erro interno no servidor!",cssDanger );
                    
                }
                
            }
            ByRemetenteView._lastCnpj=cnpj;
            this.modifyBtnFindConhecimento("Procurar",'btn-outline-success');
        }
        
       generateTags(responseJson){

        var tabela = document.querySelector("#tabela-conhecimentos");
        var content = responseJson.content;
        var objectBuilder = new ConhecimentoBuilderService();

        for(var i=0; i<content.length;i++){
             
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