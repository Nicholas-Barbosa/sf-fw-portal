class ImpostoDto{
    _baseCalculo;
    _porcentagemIcms;
    _valorIcms;

    constructor(baseCalculo,porcentagemIcms,valorIcms){
        this._baseCalculo = baseCalculo;
        this._porcentagemIcms = porcentagemIcms;
        this._valorIcms = valorIcms;
    }

    getBaseCalculo(){
        return this._baseCalculo;
    }

    getPorcentagemIcms(){
        return this._porcentagemIcms;
    }
    getValorIcms(){
        return this._valorIcms;
    }
}