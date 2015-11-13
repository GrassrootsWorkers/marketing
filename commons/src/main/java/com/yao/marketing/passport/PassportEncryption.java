package com.yao.marketing.passport;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.codec.binary.Base64;

import com.yao.marketing.string.StringUtil;


public class PassportEncryption {
	/**
	 * ���ַ�������MD5����
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
	 * �ַ�������
	 * @param sourceStr Դ�ַ���
	 * @param salt ���������
	 * @return
	 */
	public static String encryPtion(String sourceStr, String salt) {
		String destStr = md5(salt.trim().toLowerCase() + sourceStr);
		return destStr;
	}
	
	/**
	 * �ַ�������
	 * @param map ��Ҫ���ܵļ�ֵ��
	 * @param key ���ܵ���Կ
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
	 * MD5ͨ��KEY ��value����
	 * @param key
	 * @param data
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
    public static byte[] getHmacMd5Bytes(byte[] key,byte[] data) throws NoSuchAlgorithmException{
       /* HmacMd5 calculation formula: H(K XOR opad, H(K XOR ipad, text))
       * HmacMd5 ���㹫ʽ��H(K XOR opad, H(K XOR ipad, text))
       * H����hash�㷨��������ʹ��MD5�㷨��K������Կ��text����Ҫ���ܵ�����
       * ipadΪ0x36��opadΪ0x5C��
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
       * �����Կ���ȣ�����64�ֽڣ���ʹ�ù�ϣ�㷨��������ժҪ����Ϊ��������Կ��
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
       * �����Կ���Ȳ���64�ֽڣ���ʹ��0x00���뵽64�ֽڡ�
       */
       if(actualKey.length < length)
       {
        for(int i = actualKey.length; i < keyArr.length; i++)
         keyArr[i] = 0x00;
       }
      
       /*calc K XOR ipad
       * ʹ����Կ��ipad����������㡣
       */
       byte[] kIpadXorResult = new byte[length];
       for(int i = 0; i < length; i++)
       {
        kIpadXorResult[i] = (byte) (keyArr[i] ^ ipad[i]);
       }
      
       /*append "text" to the end of "K XOR ipad"
       * ������������׷�ӵ�K XOR ipad���������档
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
       * ʹ�ù�ϣ�㷨������������ժҪ��
       */
       byte[] firstHashResult = md5(firstAppendResult);
      
       /*calc K XOR opad
       * ʹ����Կ��opad����������㡣
       */
       byte[] kOpadXorResult = new byte[length];
       for(int i = 0; i < length; i++)
       {
        kOpadXorResult[i] = (byte) (keyArr[i] ^ opad[i]);
       }
      
       /*append "H(K XOR ipad, text)" to the end of "K XOR opad"
       * ��H(K XOR ipad, text)���׷�ӵ�K XOR opad�������
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
       * ����������ݽ��й�ϣ���㡣
       */
       byte[] hmacMd5Bytes = md5(secondAppendResult);

       return hmacMd5Bytes;

    }
    /**
     * ͨ���û�token,userid,username�õ�Security��md5,base64��
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
