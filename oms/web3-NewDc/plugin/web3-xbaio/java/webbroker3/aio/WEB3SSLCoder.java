head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.30.08.31.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6304d92ea6f5d00;
filename	WEB3SSLCoder.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        : WEB3SSLCoder.java
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2011/03/30 車進(中訊) 新規作成
*/
package webbroker3.aio;

import java.security.MessageDigest;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * WEB3SSLCoder
 * @@version 1.0
 */  
public abstract class WEB3SSLCoder
{   
   public static final String KEY_SHA = "SHA";   
   public static final String KEY_MD5 = "MD5";   
 
   /**
    * <pre>
    * HmacMD5
    * HmacSHA1
    * HmacSHA256
    * HmacSHA384
    * HmacSHA512
    * </pre>
    */
    public static final String KEY_MAC = "HmacMD5";

   /**
    * BASE64　@Decrypt
    * 
    * @@param key
    * @@return byte[]
    * @@throws Exception
    */
    public static byte[] decryptBASE64(String key) throws Exception
    {   
        return (new BASE64Decoder()).decodeBuffer(key);
    }   

   /**
    * BASE64　@Encrypt
    * @@param key
    * @@return String
    * @@throws Exception
    */
    public static String encryptBASE64(byte[] key) throws Exception
    {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

   /**
    * MD5　@Decrypt
    * @@param data
    * @@return byte[]
    * @@throws Exception
    */
    public static byte[] encryptMD5(byte[] data) throws Exception
    {
        MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
        md5.update(data);
        return md5.digest();
    }   

   /**
    * SHA　@Encrypt
    * @@param data
    * @@return byte[]
    * @@throws Exception
    */
    public static byte[] encryptSHA(byte[] data) throws Exception
    {
        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
        sha.update(data);
        return sha.digest();
    }

   /**
    * init HMAC Key
    * @@return String
    * @@throws Exception
    */
    public static String initMacKey() throws Exception
    {
       KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);
       SecretKey secretKey = keyGenerator.generateKey();
       return encryptBASE64(secretKey.getEncoded());
    }

    /**
     * HMAC　@Encrypt
     * @@param data
     * @@param key
     * @@return byte[]
     * @@throws Exception
     */
     public static byte[] encryptHMAC(byte[] data, String key) throws Exception
     {
         SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
         Mac mac = Mac.getInstance(secretKey.getAlgorithm());
         mac.init(secretKey);
         return mac.doFinal(data);
     }
}  
@
