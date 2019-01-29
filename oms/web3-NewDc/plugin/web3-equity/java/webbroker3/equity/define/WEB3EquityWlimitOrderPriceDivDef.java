head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityWlimitOrderPriceDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3EquityWlimitOrderPriceDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/21 li-yunfeng(sinocom)　@新規作成
*/
package webbroker3.equity.define;

/**
 * 指値用注文単価区分　@定数定義インタフェイス
 *                                                                     
 * @@author li-yunfeng
 * @@version 1.0
 */
public interface WEB3EquityWlimitOrderPriceDivDef
{
    /**
     * W指値用注文単価区分（成行）<BR>
     */
    public final static String WLIMIT_ORDER_PRICE_DIV_MARKET_PRICE = "0";

    /**
     * W指値用注文単価区分（指値）<BR>
     */
    public final static String WLIMIT_ORDER_PRICE_DIV_LIMIT_PRICE = "1";
}
@
