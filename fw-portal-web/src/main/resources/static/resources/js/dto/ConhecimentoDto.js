class ConhecimentoDto {

    _numero;
    _serie;
    _chave;
    _emissao;
    _total;
    _taxas=[];
    _imposto;
    _emitente;
    _remetente;
    _destinatario;

    constructor(numero,serie,chave,emissao,total,taxas,imposto,emitente,remetente,destinatario){
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
      
        this.createImpostoObject(imposto);
        
            this.createEntidadeObject(emitente,1);
            this.createEntidadeObject(remetente,2);
            this.createEntidadeObject(destinatario,3);
         }

   
    createImpostoObject(imposto){
        this._imposto = new ImpostoDto(imposto.baseCalculo,imposto.porcentagemIcms,imposto.valorIcms);
    }

    createEntidadeObject(entidade,op){
        if(op==1){
            this._emitente  = new EntidadeDto(entidade.CNPJ,entidade.nome);
        }else if(op==2)
            this._remetente = new EntidadeDto(entidade.CNPJ,entidade.nome);
        else
            this._destinatario =  new EntidadeDto(entidade.CNPJ,entidade.nome);
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

    getEmitente(){
        return this._emitente;
    }

    getRemetente(){
        return this._remetente;
    }

    getDestinatario(){
        return this._destinatario;
    }
}