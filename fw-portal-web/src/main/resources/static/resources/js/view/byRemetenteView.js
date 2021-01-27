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
              client.open("GET", "http://localhost:8080/conhecimentos/rest/findByRemetente/"+cnpj);
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
            var content = responseJson.content;
            for(var i=0; i<content.length;i++){
                var conhecimento = this.createConhecimentoObject(content[i]);
                var taxa = conhecimento.getTaxas()[0];
               
               console.log("chave "+ conhecimento.getChave()+" emissao "+ conhecimento.getEmissao() +" taxa[0] " +taxa.getValor());
              console.log("imposto " +conhecimento.getImposto());
              console.log("emitente " + conhecimento.getEmitente().getNome());
              console.log("remetente " + conhecimento.getRemetente().getNome());
              console.log("destinatario " + conhecimento.getDestinatario().getNome());
              console.log("origem " + conhecimento.getOrigem().getNome());
              console.log("destino " + conhecimento.getDestino().getNome());
              console.log("produto predominante " +conhecimento.getCarga().getProdutoPredominante());
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
    }
    var view = new ByRemetenteView();