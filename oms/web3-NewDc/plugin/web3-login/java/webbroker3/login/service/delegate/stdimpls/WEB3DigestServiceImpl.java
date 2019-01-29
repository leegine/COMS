head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.24.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3DigestServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3DigestServiceImplクラス(WEB3DigestServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/6/07 孫(FLJ) 新規作成
                   2006/7/07 孫(FLJ) 生成されたGUIDを大文字にする（getKey3()メソッド）
*/
package webbroker3.login.service.delegate.stdimpls;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

import webbroker3.login.service.delegate.WEB3DigestKey;
import webbroker3.login.service.delegate.WEB3DigestService;
import webbroker3.util.WEB3LogUtility;


import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

/**
 *  このクラスはWEB3に必要なセキュリティーキーを生成、認証などを行うサービスである。
 * 
 *  @@author      孫(FLJ)
 *  @@version     1.0
 */
public class WEB3DigestServiceImpl implements WEB3DigestService
{
    /** ログ出力オブジェクト */
    private static final WEB3LogUtility m_log = WEB3LogUtility.getInstance(WEB3DigestServiceImpl.class);
    
    /** ランダム数の生成器。 */    
    private static final SecureRandom numberGenerator = new SecureRandom();
    
    /** 16進数の表示数字 */
    private final static String[] hexDigits = {
            "0", "1", "2", "3", "4", "5", "6", "7",
            "8", "9", "a", "b", "c", "d", "e", "f"};
    
    //メソッド名の定数
    private final static String M_GET_RANDOM_KEY = "getRandomKey()";    
    private final static String M_IS_VALID_KEY = "isValidKey(WEB3DigestKey key)"; 
    private final static String M_GET_KEY_1 = "getKey1()"; 
    private final static String M_GET_KEY_3 = "getKey3()"; 
    private final static String M_GET_KEY_4 = "getKey4(final WEB3DigestKey key)"; 

    
    /**
     * Web3に必要なキーを作成、取得
     * 
     * @@return キー
     */
    public WEB3DigestKey getRandomKey()
    {
        m_log.entering(M_GET_RANDOM_KEY);
        
        WEB3DigestKey result = new WEB3DigestKey();
        
        result.setKey1(getKey1());
        result.setKey3(getKey3());
        result.setKey4(getKey4(result));
        
        m_log.exiting(M_GET_RANDOM_KEY);
        return result;
    }
    
    /**
     * 有効なキーであるかをチャックする。
     * 下記二つ条件を満たすキーは有効キーであると判断される。
     * １．キーの生成時刻と現在時刻と比べて五分間以内であること。
     * ２．キーのSHA1コードは現在計算されたSHA1コードと同じであること。
     * 
     * @@param l_key チャックされるキーオブジェクト
     * @@return 有効であるかどうか。有効の場合:true,無効は:false
     */
    public boolean isValidKey(WEB3DigestKey l_key)
    {
        m_log.entering(M_IS_VALID_KEY);
        
        boolean result = false;
        
        if(l_key != null && l_key.getKey1() != null && l_key.getKey3() != null)
        {
            //フォーマットを指定
            SimpleDateFormat l_dateFormat = GtlUtils.getThreadSafeSimpleDateFormat(WEB3DigestKey.TIME_FORMAT);
            
            //今の時刻を取得
            String l_nowString = l_dateFormat.format(new Date(System.currentTimeMillis()));
            
            //キーの生成時刻と現在時刻と比べて五分間以内であるかを判断する。
            long l_interval = Math.abs(Long.parseLong(l_nowString) - Long.parseLong(l_key.getKey1()) );
            if( l_interval > 500)
            {
                return result;
            }
            
            //キーのSHA1コードは現在計算されたSHA1コードと同じであるかを判断する。
            String key4 = getKey4(l_key);//web3キーオブジェクトのキー1,2,3によって、新しいキー4(SHA1コード)を生成
            result = key4.equals(l_key.getKey4());//新しいキー4とweb3キーオブジェクトの持っている古いキーを比較
        }
        
        m_log.exiting(M_IS_VALID_KEY);
        return result;
    }
    
    /**
     * キー1(西暦日時分秒)を生成
     * 
     * @@return キー1
     */
    private String getKey1()
    {
        m_log.entering(M_GET_KEY_1);
        
        //フォーマットを指定
        SimpleDateFormat format = GtlUtils.getThreadSafeSimpleDateFormat(WEB3DigestKey.TIME_FORMAT); 

        //今の時刻を取得
        Date date = new Date(System.currentTimeMillis());
        
        m_log.debug("Created Key1(date):"+date);
        m_log.exiting(M_GET_KEY_1);
        return format.format(date);
    }
    
    /**
     * キー3(GUID)を生成
     * 
     * @@return キー3
     */    
    private String getKey3()
    {
        m_log.entering(M_GET_KEY_3);
        String result = null;
        
        byte[] randomBytes = new byte[16];
        numberGenerator.nextBytes(randomBytes);
        randomBytes[6]  &= 0x0f;  /* clear version        */
        randomBytes[6]  |= 0x40;  /* set to version 4     */
        randomBytes[8]  &= 0x3f;  /* clear variant        */
        randomBytes[8]  |= 0x80;  /* set to IETF variant  */

        long msb = 0;
        long lsb = 0;
        for (int i=0; i<8; i++)
        {
            msb = (msb << 8) | (randomBytes[i] & 0xff);
        }
        for (int i=8; i<16; i++){
            lsb = (lsb << 8) | (randomBytes[i] & 0xff);           
        }
        
        result = (longToHexString(msb >> 32, 8) +
                  longToHexString(msb >> 16, 4) +
                  longToHexString(msb, 4)       +
                  longToHexString(lsb >> 48, 4) +
                  longToHexString(lsb, 12));
        
        //2006/07/07 孫　@修正 生成されたGUIDを大文字にする
        result = result.toUpperCase();
        
        m_log.debug("Created Key3(GUID):"+result);
        m_log.exiting(M_GET_KEY_3);
        return result;
    }
    
    /**
     * キー1,2,3によって、キー4(SHA1コード)を生成
     * 
     * @@param key
     *            キー1,2,3を持っているオブジェクト
     * @@return キー1 生成中エラーが発生する時に長さ0の文字列を返す
     */
    private String getKey4(final WEB3DigestKey l_key)
    {
        m_log.entering(M_GET_KEY_4);
        StringBuffer result = new StringBuffer();
        
        MessageDigest md;
        try
        {
            md = MessageDigest.getInstance("SHA1");

            md.update(l_key.getKey1().getBytes());
            md.update(l_key.getKey2().getBytes());
            md.update(l_key.getKey3().getBytes());
            byte[] byteTemp = md.digest();
            for (int i = 0; i < byteTemp.length; i++) {
                result.append(byteToHexString(byteTemp[i]));
            }
        }
        catch (NoSuchAlgorithmException e)
        {
            m_log.error("Error while Generating SHA1 Key." + e);
        }

        m_log.debug("Created Key4(SHA1):"+result.toString());    
        m_log.exiting(M_GET_KEY_4);
        return result.toString();
    }
    
    /**
     * 数字から16進数の文字列を返す
     */
    private String longToHexString(long val, int digits)
    {
        long hi = 1L << (digits * 4);
        return Long.toHexString(hi | (val & (hi - 1))).substring(1);
    }
    
    /**
     * バイトを16進数の文字列に変更
     */
    private String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
          n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
      }

}
@
