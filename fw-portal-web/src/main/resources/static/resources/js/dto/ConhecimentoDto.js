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
    _origem;
    _destino;
    _carga;
    _notas=[];

    constructor(numero,serie,chave,emissao,total,taxas,imposto,emitente,remetente,
        destinatario,origem,destino,carga,notas){
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
        this._emitente = this.createEntidadeObject(emitente);
        this._remetente =  this.createEntidadeObject(remetente);
        this._destinatario =  this.createEntidadeObject(destinatario);
        this._origem = this.creatCidadeObject(origem);
        this._destino = this.creatCidadeObject(destino);
        this._carga = new CargaDto(carga.valor,carga.produtoPredominante,carga.medidas);
        this._notas = notas.map(function(object){
              return new NotaDto(object.numero,object.serie,object.chave);      
        })
        
     }

   
    createImpostoObject(imposto){
        this._imposto = new ImpostoDto(imposto.baseCalculo,imposto.porcentagemIcms,imposto.valorIcms);
    }

    createEntidadeObject(entidade){
            return new EntidadeDto(entidade.CNPJ,entidade.nome);
       
    }

    creatCidadeObject(cidade){
        return new CidadeDto(cidade.nome,cidade.uf,cidade.codigo);
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

    getOrigem(){
        return this._origem;
    }

    getDestino(){
        return this._destino;
    }

    getCarga(){
        return this._carga;
    }

    getNotas(){
        return this._notas;
    }
}