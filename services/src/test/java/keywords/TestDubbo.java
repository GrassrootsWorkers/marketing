package keywords;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yao.coupon.business.interfaces.ICouponRemoteManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:dubbo-consumer.xml" })
public class TestDubbo {
	@Autowired
	private ICouponRemoteManager couponRemoteManager;
	@Test
	public void testDubboBean(){
		System.out.println(couponRemoteManager);
	}
}
