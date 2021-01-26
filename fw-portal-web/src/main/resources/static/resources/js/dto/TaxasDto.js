class TaxasDto{
   
	#taxa;

	
    #valor;
    
    constructor(taxa,valor){
        this.#taxa = taxa;
        this.#valor = valor;
       
    }

    getTaxa(){
        return this.#taxa;
    }

    getValor(){
        return this.#valor;
        
    }
}