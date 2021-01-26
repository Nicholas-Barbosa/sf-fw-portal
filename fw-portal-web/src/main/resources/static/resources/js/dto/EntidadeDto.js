class EntidadeDto{

    _cnpj;
    _nome;

    constructor(cnpj,nome){
        this._cnpj= cnpj;
        this._nome = nome;
    }

    getCnpj(){
        return this._cnpj;
    }
    getNome(){
        return this._nome;
    }
}