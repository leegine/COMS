head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.49.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioOrderAcceptedDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AioOrderAcceptedDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 韋念瓊 (中訊) 新規作成
*/
package webbroker3.aio.define;

/**
 * 出金申込問合せ一覧リクエストの注文受付区分と
 * 出金申込問合せ明細クラスの注文受付区分　@定数定義インタフェイス
 *                                                                     
 * @@author 韋念瓊
 * @@version 1.0
 */
public interface WEB3AioOrderAcceptedDivDef
{      
    
    /**
     * 0 : 受付未済
     */
    public static final String NOT_ACCEPTED = "0";

    /**
     * 1 : 受付済
     */
    public static final String ACCEPTED = "1";    
    
    /**
     * 2 : 受付エラー
     */
    public static final String ACCEPT_ERROR = "2";
    
    /**
     * 3 : 全て
     */
    public static final String ALL = "3"; 
    
    /**
     * 4 : 受付中
     */
    public static final String ACCEPTING = "4"; 
    
    
}
@
