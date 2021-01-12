package com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.meta.fluxo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class DestinatarioLayout extends EmpresaLayout implements Cloneable {

	@JacksonXmlProperty(localName = "enderDest")
	private EnderecoLayout endereco;

	public DestinatarioLayout() {
		// TODO Auto-generated constructor stub
	}

	public DestinatarioLayout(EnderecoLayout endereco, String nome, String cnpj) {
		super();
		this.endereco = endereco;
		super.setCnpj(cnpj);
		super.setNome(nome);
	}

	public EnderecoLayout getEndereco() {
		return endereco;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return new DestinatarioLayout(this.endereco, super.getNome(), super.getCnpj());
	}
}
