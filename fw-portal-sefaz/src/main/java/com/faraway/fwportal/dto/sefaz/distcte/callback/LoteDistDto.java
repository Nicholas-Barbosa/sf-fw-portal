package com.faraway.fwportal.dto.sefaz.distcte.callback;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "loteDistDFeInt")
public class LoteDistDto {

	@JacksonXmlProperty(localName = "docZip")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<DocZipDto> doc;

	public List<DocZipDto> getDoc() {
		return doc;
	}

	public static LoteDistDto toDeserialize(String response) throws JsonMappingException, JsonProcessingException {
		XmlMapper xmlMapper = new XmlMapper();
		LoteDistDto value = xmlMapper.readValue(response, LoteDistDto.class);
		return value;
	}

}
