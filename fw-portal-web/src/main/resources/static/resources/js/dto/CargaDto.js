class CargaDto{
    _valor;
    _produtoPredominante;
    _medidas=[];

    constructor(valor,produtoPredominante,medidas){
        this._valor = valor;
        this._produtoPredominante = produtoPredominante;
        this._medidas = medidas.map(function(object){
            return new MedidaDto(object.tipoMedida,object.quantidade);
        });
    }

    getValor(){
        return this._valor;
    }

    getProdutoPredominante(){
        return this._produtoPredominante;
    }
    getMedidas(){
        return this._medidas;
    }

}