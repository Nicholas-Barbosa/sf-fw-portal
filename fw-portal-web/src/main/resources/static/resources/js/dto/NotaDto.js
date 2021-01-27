class NotaDto{
    _numero;
    _serie;
    _chave;

    constructor(numero,serie,chave){
        this._numero = numero;
        this._serie = serie;
        this._chave = chave;
    }

    getNumero(){
        return this._numero;
    }

    getSerie(){
        return this._serie;
    }
    getChave(){
        return this._chave;
    }
    
}