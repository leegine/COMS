head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.50.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3OrderPriceDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文単価区分(WEB3OrderPriceDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/20 張宝楠 (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 注文単価区分　@定数定義インタフェイス
 *                                                                      
 * @@author 張宝楠 (中訊)
 * @@version 1.0
 */
public interface WEB3OrderPriceDivDef
{

    /**
     * 成行
     */
    public static final String MARKET_PRICE = "0";

    /**
     * 指値
     */
    public static final String LIMIT_PRICE = "1";
    
}
@
