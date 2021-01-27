class ConhecimentoBuilderService{

    constructor(){
        
    }
    createConhecimentoObject(objectToConvert){
       
        var numero = objectToConvert.numero;
        var serie = objectToConvert.serie;
        var chave = objectToConvert.chaveCte;
        var emissao = objectToConvert.emissao;
        var total = objectToConvert.total
        var taxas = objectToConvert.componente;
        var imposto = objectToConvert.imposto;
        var emitente = objectToConvert.emitente;
        var remetente = objectToConvert.remetente;
        var destinatario = objectToConvert.destinatario;
        var origem = objectToConvert.origem;
        var destino = objectToConvert.destino;
        var carga = objectToConvert.infoCarga;
        var notas = objectToConvert.notas;

        return new ConhecimentoDto(numero,serie,chave,emissao,total,taxas,imposto,emitente,
            remetente,destinatario,origem,destino,carga,notas);
     
 }

}