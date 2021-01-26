

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
         
         
         var view = new ByRemetenteView();
         view.changeCssClassOfDivAndAddMessage(client.status);
		  }
		  client.open("GET", "http://localhost:8080/conhecimentos/rest/findByRemetente/"+cnpj);
		  client.send();
	}


    changeCssClassOfDivAndAddMessage(status){
      		
		   const cssWarning = 'alert-warning';
           const cssSucess ='alert-success';
       	   const cssDanger = 'alert-danger';
           var msg = document.getElementById("divAlert");
           msg.hidden =false; 
        
        if(status==200){
            msg.textContent="Conhecimentos encontrados.";
            
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
}
var view = new ByRemetenteView();