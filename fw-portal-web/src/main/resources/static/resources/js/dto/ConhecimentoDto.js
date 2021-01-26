class ConhecimentoDto {

    _numero;
    _serie;
    _chave;
    _emissao;
    _total;
    _taxas;
    
    constructor(numero,serie,chave,emissao,total,taxas){
        this._numero = numero;
        this._serie = serie;
        this._chave = chave;
        this._emissao = emissao;
        this._total =total;
        this._taxas = taxas;
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
}