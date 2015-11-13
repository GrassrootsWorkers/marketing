package xml;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;
import com.yao.model.convert.WeixinDataConvert;
import com.yao.model.weixin.constant.EventType;
import com.yao.model.weixin.receive.ReceivedMessage;
import com.yao.model.weixin.send.Articles;
import com.yao.model.weixin.send.SendedMessage;
import com.yao.model.weixin.send.Voice;

public class TestXml {
	@Test
	public void testXml() {
		String str="<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>12345678</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[/template/weixin_text.vm]]></Content></xml>";
		/*ReceivedMessage msg = new ReceivedMessage();
		WeixinDataConvert<ReceivedMessage> convert = new WeixinDataConvert<ReceivedMessage>();
		msg.setContent("111");*/
		SendedMessage send = new SendedMessage();
		WeixinDataConvert<SendedMessage> convertJson = new WeixinDataConvert<SendedMessage>();
		//System.out.println(convertJson.ConvertObjectToJson(send));
		/*msg.setContent("≤‚ ‘");
		String str = convert.ConvertObjectToXml(msg);
		System.out.println(str);*/
		/*ReceivedMessage msg1 = new ReceivedMessage();
		convert.ConvertXmlToObject(str, msg1);
		System.out.println(msg1.getContent());*/
		
		/*XStream xstream = new XStream();
		xstream.processAnnotations(ReceivedMessage.class);
		
		ReceivedMessage s = (ReceivedMessage)xstream.fromXML(str);
		System.out.println(s.getToUserName());*/
		send = convertJson.ConvertXmlToObject(str, send);
		System.out.println(send.getToUserName());
		/*WeixinDataConvert<SendedMessage> convert1 = new WeixinDataConvert<SendedMessage>();
		
		SendedMessage send = new SendedMessage();
		send.setAccessToken("1111");
		List<Articles> news = new ArrayList<Articles>();
		Articles ar = new Articles();
		ar.setDescription("11111");
		news.add(ar);
		ar = new Articles();
		ar.setDescription("11111");
		news.add(ar);
		send.setNews(news);
		Voice v = new Voice();
		v.setMediaId("23232");
		send.setVoice(v);
		System.out.println(convert1.ConvertObjectToXml(send));*/
		
	}
	
	@Test
	public void testEum() {
		System.out.println(EventType.SCAN.toString().equals("SCAN"));
	}
	@Test
	public void createTemplate() {
		SendedMessage send = new SendedMessage();
		send.setContent("/template/weixin_text.vm");
	}
}
