package keywords;

import java.net.URL;
import java.util.List;

import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yao.marketing.interfaces.weixin.keywords.IKeywordsManager;
import com.yao.marketing.interfaces.weixin.user.IWeixinUserBindedManager;
import com.yao.model.weixin.keywords.Keywords;
import com.yao.model.weixin.receive.ReceivedMessage;
import com.yao.model.weixin.send.SendedMessage;
import com.yao.model.weixin.user.WeixinUserBind;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-service.xml"})
public class KeywordTest {
		@Autowired
		IKeywordsManager keywordsManager;
		@Autowired
		IWeixinUserBindedManager weixinbindedManager;
		@Autowired
		SqlSessionFactoryBean sqlSessionFactory;

		@Ignore("test()Î´Íê³É")
		@Test
		public void test() {
				System.out.println(sqlSessionFactory);
				System.out.println(keywordsManager);
				Keywords keywords = new Keywords();
				keywords.setContent("success");
				keywords.setShopId(1);
				keywords.setType(Keywords.all_match);
				List<Keywords> keywordList = keywordsManager.queryKeywords(keywords);

				System.out.println(keywordList);

		/*
		 * List<ShopTemplate> shoptemplateList =
		 * keywordsManager.queryTemplate(1, 1);
		 * System.out.println(shoptemplateList.size());
		 */

		}

		@Test
		public void testBindedUser() {
				WeixinUserBind weixinUser = new WeixinUserBind();
				weixinUser.setOpenId("o6_bmjrPTlm6_2sgVt7hMZOPfL2M");
				// weixinbindedManager.intsert(weixinUser);
				weixinbindedManager.insert(weixinUser);
				System.out.println(weixinbindedManager.validateIfBinded("o6_bmjrPTlm6_2sgVt7hMZOPfL2M"));
		}

		@Test
		public void sendCoupon() throws Exception {

				ReceivedMessage msg = new ReceivedMessage();
				msg.setContent("ÓÅ»ÝÈ¯");
				msg.setFromUserName("a90efc35df1842a391b35ed37b568e33");
				msg.setToUserName("²âÊÔÕËºÅ");
				msg.setMsgType("text");
		/*msg.setMsgType("event");
		msg.setEvent("CLICK");
		msg.setEventKey("clwg88mh");*/
				msg.setShopId(1000);
				msg.setVenderId("2011102716210000");
				SendedMessage msg1 = keywordsManager.getReplayContent(msg, "1000");
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>" + msg1.getContent());

		}

		@Test
		public void testGetPath() {
				URL resource = getClass().getResource("/");
				System.out.println(resource.getPath());
		}
}
