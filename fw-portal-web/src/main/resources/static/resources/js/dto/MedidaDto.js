class MedidaDto{

    _tipoMedida;
    _quantidade;

    constructor(tipoMedida,quantidade){
        this._tipoMedida = tipoMedida;
        this._quantidade = quantidade;
    }

    getTipoMedida(){
        return this._tipoMedida;
    }

    getQuantidade(){
        return this._quantidade;
    }
}