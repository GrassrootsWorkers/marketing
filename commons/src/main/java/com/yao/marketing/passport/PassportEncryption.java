package com.yao.marketing.passport;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.codec.binary.Base64;

import com.yao.marketing.string.StringUtil;


public class PassportEncryption {
	/**
	 * 对字符串进行MD5加密
	 */
	private final static String md5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			byte[] t = new byte[strTemp.length * 2];
			for (int i = 0; i < t.length; i++) {
				if (i % 2 == 0) {
					t[i] = strTemp[i / 2];
				} else {
					t[i] = 0;
				}
			}

			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(t);
			byte[] md = mdTemp.digest();

			int j = md.length;
			char str[] = new char[j * 3];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];				
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];	
				
				str[k++] = '-';
			}
			
			return new String(str, 0, str.length-1).toUpperCase();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 字符串加密
	 * @param sourceStr 源字符串
	 * @param salt 加密随机串
	 * @return
	 */
	public static String encryPtion(String sourceStr, String salt) {
		String destStr = md5(salt.trim().toLowerCase() + sourceStr);
		return destStr;
	}
	
	/**
	 * 字符串加密
	 * @param map 需要加密的键值对
	 * @param key 加密的密钥
	 * @return 
	 */
	public static String encryPtion(Map<Object, Object> map, String secret) {
		if (map == null ||map.size() <=0 ) {
			return "";
		}
		
		TreeMap<Object, Object> tm = new TreeMap<Object, Object>();
		tm.putAll(map);
		StringBuffer buffer = new StringBuffer();
		for (Object key : tm.keySet()) {
			Object value = tm.get(key);
			if (value != null && StringUtil.areNotEmpty(value.toString()))			
				buffer.append(tm.get(key));
		}
		
		return encryPtion(buffer.toString(), secret);
	}
	private static byte[] md5(byte[] str) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str);
        return md.digest();
    }
	/**
	 * MD5通过KEY ，value加密
	 * @param key
	 * @param data
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
    public static byte[] getHmacMd5Bytes(byte[] key,byte[] data) throws NoSuchAlgorithmException{
       /* HmacMd5 calculation formula: H(K XOR opad, H(K XOR ipad, text))
       * HmacMd5 计算公式：H(K XOR opad, H(K XOR ipad, text))
       * H代表hash算法，本类中使用MD5算法，K代表密钥，text代表要加密的数据
       * ipad为0x36，opad为0x5C。
       */
       int length = 64;
       byte[] ipad = new byte[length];
       byte[] opad = new byte[length];
       for(int i = 0; i < 64; i++)
       {
        ipad[i] = 0x36;
        opad[i] = 0x5C;
       }
       byte[] actualKey = key;      //Actual key.
       byte[] keyArr = new byte[length];   //Key bytes of 64 bytes length
       /*If key's length is longer than 64,then use hash function to digest it and use the result as actual key.
       * 如果密钥长度，大于64字节，就使用哈希算法，计算其摘要，作为真正的密钥。
       */
       if(key.length>length)
       {
        actualKey = md5(key);
       }
       for(int i = 0; i < actualKey.length; i++)
       {
        keyArr[i] = actualKey[i];
       }

       /*append zeros to K
       * 如果密钥长度不足64字节，就使用0x00补齐到64字节。
       */
       if(actualKey.length < length)
       {
        for(int i = actualKey.length; i < keyArr.length; i++)
         keyArr[i] = 0x00;
       }
      
       /*calc K XOR ipad
       * 使用密钥和ipad进行异或运算。
       */
       byte[] kIpadXorResult = new byte[length];
       for(int i = 0; i < length; i++)
       {
        kIpadXorResult[i] = (byte) (keyArr[i] ^ ipad[i]);
       }
      
       /*append "text" to the end of "K XOR ipad"
       * 将待加密数据追加到K XOR ipad计算结果后面。
       */
       byte[] firstAppendResult = new byte[kIpadXorResult.length+data.length];
       for(int i=0;i<kIpadXorResult.length;i++)
       {
        firstAppendResult[i] = kIpadXorResult[i];
       }
       for(int i=0;i<data.length;i++)
       {
        firstAppendResult[i+keyArr.length] = data[i];
       }
      
       /*calc H(K XOR ipad, text)
       * 使用哈希算法计算上面结果的摘要。
       */
       byte[] firstHashResult = md5(firstAppendResult);
      
       /*calc K XOR opad
       * 使用密钥和opad进行异或运算。
       */
       byte[] kOpadXorResult = new byte[length];
       for(int i = 0; i < length; i++)
       {
        kOpadXorResult[i] = (byte) (keyArr[i] ^ opad[i]);
       }
      
       /*append "H(K XOR ipad, text)" to the end of "K XOR opad"
       * 将H(K XOR ipad, text)结果追加到K XOR opad结果后面
       */ 
       byte[] secondAppendResult = new byte[kOpadXorResult.length+firstHashResult.length];
       for(int i=0;i<kOpadXorResult.length;i++)
       {
        secondAppendResult[i] = kOpadXorResult[i];
       }
       for(int i=0;i<firstHashResult.length;i++)
       {
        secondAppendResult[i+keyArr.length] = firstHashResult[i];
       }
      
       /*H(K XOR opad, H(K XOR ipad, text))
       * 对上面的数据进行哈希运算。
       */
       byte[] hmacMd5Bytes = md5(secondAppendResult);

       return hmacMd5Bytes;

    }
    /**
     * 通过用户token,userid,username得到Security（md5,base64）
     * @param key
     * @param value
     * @return
     */
    public static String getSecurity(String token,String userid,String username){
        String value = userid+username;
        byte[] result = null;
        try {
            result = getHmacMd5Bytes(token.getBytes(),value.getBytes("UTF-8"));
            byte[] resultstr = Base64.encodeBase64(result);
            return new String(resultstr);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        return null;
    }
	public static void main(String[] args) {
		String s = "123456";
		System.out.println(encryPtion(s, "9kQwtN2Swhw=".trim().toLowerCase()));
	}
}
