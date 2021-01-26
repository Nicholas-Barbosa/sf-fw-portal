class CidadeDto{

    _nome;
    _uf;
    _codigo;

    constructor(nome,uf,codigo){
        this._nome = nome;
        this._uf = uf;
        this._codigo = codigo;
    }

    getNome(){
        return this._nome;
    }
    getUf(){
        return this._uf;
    }
    getCodigo(){
        return this._codigo;
    }
}