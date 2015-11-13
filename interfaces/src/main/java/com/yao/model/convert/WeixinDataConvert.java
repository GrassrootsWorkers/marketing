package com.yao.model.convert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.xstream.XStream;
import com.yao.marketing.interfaces.convert.IDataConvert;

public class WeixinDataConvert<T> implements IDataConvert<T> {

	@Override
	public String ConvertObjectToXml(T o) {
		XStream xstream = XstreamUtils.getXstream(o.getClass(), false);
		xstream.autodetectAnnotations(true);
		String xmlStr = xstream.toXML(o);
		return xmlStr;
	}

	@Override
	public String ConvertObjectToJson(T o) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.getSerializationConfig();
			return mapper.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public T ConvertXmlToObject(String xmlStr, T o) {
		XStream xstream = XstreamUtils.getXstream(o.getClass(), false);
		xstream.processAnnotations(o.getClass());
		xstream.autodetectAnnotations(true);
		xstream.fromXML(xmlStr, o);
		return o;
	}

	@Override
	public T ConvertJsonToObject(String jsonStr, T o) {
		return null;
	}
	
}
