head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.48.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioTelegramReturnCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AioTelegramReturnCodeDeff.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/14 王蘭芬 (中訊) 新規作成
*/
package webbroker3.aio.define;

/**
 * マルチバンク電文処理サービスImplのReturnCode　@定数定義インタフェイス
 *                                                                     
 * @@author 王蘭芬
 * @@version 1.0
 */
public interface WEB3AioTelegramReturnCodeDef
{
    /**
     * "OK":OK  
     */
    public static final String OK = "OK";
    
    /**
     * "NG" NG 
     */
    public static final String NG = "NG";

    /**
     * "ERROR" ERROR 
     */
    public static final String ERROR = "ERROR";
    
    /**
     * "FAIL" FAIL 
     */
    public static final String FAIL = "FAIL";

    /**
     * "COMPLETE" COMPLETE 
     */
    public static final String COMPLETE = "COMPLETE";
}

@
