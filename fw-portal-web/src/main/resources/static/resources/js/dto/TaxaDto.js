class TaxaDto{
   
	_taxa;
    _valor;
    
    constructor(taxa,valor){
        this._taxa = taxa;
        this._valor = valor;
       
    }

    getTaxa(){
        return this._taxa;
    }

    getValor(){
        return this._valor;
        
    }
}