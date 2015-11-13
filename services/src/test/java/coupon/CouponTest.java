package coupon;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yao.marketing.interfaces.weixin.coupon.IWeixinUserBindCouponManager;
import com.yao.model.weixin.coupon.WeixinCouponInfo;
import com.yao.model.weixin.coupon.WeixinUserCoupon;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-service.xml" })
public class CouponTest {
	@Autowired
	IWeixinUserBindCouponManager weixinCouponManager;
	
	@Test
	public void testInsertCouponInfo() {
		WeixinCouponInfo info = new WeixinCouponInfo();
		 info.setBatchCode("BCBOH");
		 info.setShopId(1000);
		 info.setFreezeQuantity(100);
		 info.setInputer("admin");
		 info.setShowName("Ж╕вп");
		 info.setStatus(1);
		 info.setUsedCount(1);
		 weixinCouponManager.save(info);
	}
	
	@Test
	public void testBindCoupon() {
		WeixinUserCoupon coupon = new WeixinUserCoupon();
		coupon.setBatchCode("BCBOH");
		coupon.setOpenId("11111");
		coupon.setSendTime(5);
		weixinCouponManager.bindCouponToUser(coupon);
	}
	@Test
	public void queryUserBindedCoupon(){
		weixinCouponManager.queryUnBindCouponByOpenId("1","11");
	}
	@Test
	public void testRemoveEntry(){
		Map<String,Integer> unbindedCoupon =new HashMap<String,Integer>();
		unbindedCoupon.put("1", 0);
		unbindedCoupon.put("11", 3);
		unbindedCoupon.put("12", 0);
		unbindedCoupon.put("13", 2);
		
	}
}
