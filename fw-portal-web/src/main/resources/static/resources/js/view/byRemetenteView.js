function find(){
    
            
           
            var cnpj= document.getElementById("inputCnpj").value;
            if(cnpj==ByRemetenteView._lastCnpj){
                 var msg = document.getElementById("divAlert");
                 msg.textContent="Este CNPJ foi usado como parametro para a busca anterior. Use outro.";
                 msg.classList.add('alert-danger');
                 
            }else{ 
                var parent = document.getElementById("tabela-conhecimentos");
                while(parent.hasChildNodes()) {
                    parent.removeChild(parent.firstChild);
                }
                view.makeRequest(cnpj);
             
            }

            
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
               var msg = document.getElementById("divAlert");
               msg.hidden =false; 
            
            if(status==200){
                msg.textContent="Conhecimentos encontrados.";
                this.generateTags(response);
                  if(msg.classList.contains(cssWarning))
                        msg.classList.remove(cssWarning);
                        
                  if(msg.classList.contains(cssDanger))
                        msg.classList.remove(cssDanger);
                msg.classList.add(cssSucess);
               
            }else{
                if(status=404){
                    msg.textContent="Nenhum conhecimento encotrado.";
                    msg.classList.add(cssWarning);
                }else{
                    msg.textContent="Erro interno no servidor!";
                    msg.classList.add(cssDanger);
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
        conhecimentoTr.appendChild(this.montaTd(conhecimento.getEmitente().getNome()));
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