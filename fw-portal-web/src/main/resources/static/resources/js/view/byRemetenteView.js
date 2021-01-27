function find(){
    //	$("#fetchModal").modal('show');
            
              
            var cnpj= document.getElementById("inputCnpj").value; 
            view.makeRequest(cnpj);
    }
    
    function closeModal(){
    console.log("closing modal...");
    $("#fetchModal").modal('hide');
    }
    
    
    
    class ByRemetenteView{
    
        
        makeRequest(cnpj,currentPage,size){
             var client = new XMLHttpRequest();
          client.onload = function() {
                // in case of network errors this might not give reliable results
             
                var response = JSON.parse(client.responseText);
                var view = new ByRemetenteView();
                view.changeCssClassOfDivAndAddMessage(client.status,response);
                
              }
              client.open("GET", "https://farawaybr.com/conhecimentos/rest/findByRemetente/"+cnpj);
              client.send();
        }
    
    
        changeCssClassOfDivAndAddMessage(status,response){
                  
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
            
        }
        
       generateTags(responseJson){

        var tabela = document.querySelector("#tabela-conhecimentos");
        var content = responseJson.content;
       
    //    console.log("content " +content.length);
    //     for(var i=0;i<content.length;i++){
    //         var conhecimentoTr = this.montaTr(content[i]);
    //                tabela.appendChild(conhecimentoTr);
    //     }
        for(var i=0; i<content.length;i++){
               var conhecimento = this.createConhecimentoObject(content[i]);
               // this.generateTable(tabela,conhecimento);
               var conhecimentoTr = this.montaTr(conhecimento);
               tabela.appendChild(conhecimentoTr);
            }
     }

     createConhecimentoObject(objectResponse){
       
            var numero = objectResponse.numero;
            var serie = objectResponse.serie;
            var chave = objectResponse.chaveCte;
            var emissao = objectResponse.emissao;
            var total = objectResponse.total
            var taxas = objectResponse.componente;
            var imposto = objectResponse.imposto;
            var emitente = objectResponse.emitente;
            var remetente = objectResponse.remetente;
            var destinatario = objectResponse.destinatario;
            var origem = objectResponse.origem;
            var destino = objectResponse.destino;
            var carga = objectResponse.infoCarga;
            var notas = objectResponse.notas;

            return new ConhecimentoDto(numero,serie,chave,emissao,total,taxas,imposto,emitente,
                remetente,destinatario,origem,destino,carga,notas);
         
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