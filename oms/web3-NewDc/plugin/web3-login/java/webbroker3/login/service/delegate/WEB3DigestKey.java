head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.22.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3DigestKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3DigestKeyクラス(WEB3DigestKey.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/6/07 孫(FLJ) 新規作成
*/
package webbroker3.login.service.delegate;

/**
 *  WEB3に必要なセキュリティーキーを保存する。
 * 
 *  @@author      孫(FLJ)
 *  @@version     1.0
 */
public class WEB3DigestKey implements java.io.Serializable
{
    
    public final static String TIME_FORMAT = "yyyyMMddHHmmss";
    
    /**キー1(西暦日時分秒):  フォーマット：YYYYMMDDHHNNSS　@*/
    private String key1 = null;
    
    /**キー2(固定文字列):  HimawariWEBBROKER3　@*/
    private final static String key2 = "HimawariWEBBROKER3";
    
    /**キー3(GUID):  唯一の標識の文字列　@　@　@32桁大文字の英数字　@*/
    private String key3 = null;
    
    /**キー4(SHA1コード):  40桁の文字・数字列　@*/
    private String key4 = null;
    

    /**
     * @@return キー1(西暦日時分秒)を戻します。
     */
    public String getKey1()
    {
        return key1;
    }
    /**
     * @@param key1 キー1(西暦日時分秒)を設定します。
     */
    public void setKey1(String l_key1)
    {
        this.key1 = l_key1;
    }
    /**
     * @@return key2 キー2(固定文字列)を戻します。
     */
    public String getKey2()
    {
        return key2;
    }

    /**
     * @@return キー3(GUID) を戻します。
     */
    public String getKey3()
    {
        return key3;
    }
    /**
     * @@param key3 キー3(GUID) を設定。
     */
    public void setKey3(String l_key3)
    {
        this.key3 = l_key3;
    }
    /**
     * @@return キー4(SHA1コード)を戻します。
     */
    public String getKey4()
    {
        return key4;
    }
    /**
     * @@param key4 キー4(SHA1コード)を設定。
     */
    public void setKey4(String l_key4)
    {
        this.key4 = l_key4;
    }

    
}
@
