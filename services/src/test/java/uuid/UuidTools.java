package uuid;

import java.util.UUID;

import org.junit.Test;

public class UuidTools {
	public String getUuid(){
		String s = UUID.randomUUID().toString(); 
		return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
	}
	@Test
	public void printlnUuid() {
		System.out.println(getUuid() );
	}
}
