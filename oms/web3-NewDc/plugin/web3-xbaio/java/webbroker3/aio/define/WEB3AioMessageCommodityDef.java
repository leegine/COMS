head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.49.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioMessageCommodityDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AioMessageCommodityDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/24 王蘭芬 (中訊) 新規作成
*/
package webbroker3.aio.define;

/**
 * 証券振替一覧リクエストの商品区分　@定数定義インタフェイス
 *                                                                     
 * @@author 王蘭芬(中訊)
 * @@version 1.0
 */
public interface WEB3AioMessageCommodityDef 
{
    /**
     *  1：株式
     */
    public static final String EQUITY = "1";
    
    /**
     *  2：債券
     */
    public static final String BOND = "2";
    
    /**
     *  3：投信
     */
    public static final String MUTUAL_FUND = "3";
}
@
