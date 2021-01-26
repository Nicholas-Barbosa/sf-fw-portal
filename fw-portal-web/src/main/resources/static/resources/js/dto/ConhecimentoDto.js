class ConhecimentoDto {

    _numero;
    _serie;
    _chave;
    _emissao;
    _total;
    _taxas=[];
    _imposto;

    constructor(numero,serie,chave,emissao,total,taxas,imposto){
        this._numero = numero;
        this._serie = serie;
        this._chave = chave;
        this._emissao = emissao;
        this._total =total;
        this._taxas = taxas.map(function(object){
            var nomeTaxa = object.nome;
            var valor = object.valor;
            return new TaxaDto(nomeTaxa,valor);
        });
      
        this.createImposto(imposto);
        
    }

   
    createImposto(imposto){
        
        this._imposto = new ImpostoDto(imposto.baseCalculo,imposto.porcentagemIcms,imposto.valorIcms);
    }
    getNumero() {
        return this._numero;
    }
    
    getSerie() {
        return this._serie;
    }
    
    getChave() {
        return this._chave;
    }
    getEmissao(){
        return this._emissao;
    }
    getTotal(){
        return this._total;
    }

    getTaxas(){
        return this._taxas;
    }

    getImposto(){
        return this._imposto;
    }
}