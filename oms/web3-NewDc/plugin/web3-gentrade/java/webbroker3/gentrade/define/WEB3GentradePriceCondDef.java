head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradePriceCondDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 値段条件コード定義インタフェイス(WEB3GentradePriceCondDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 孟東 (中訊) 新規作成
*/
package webbroker3.gentrade.define;

/**
 * 値段条件コード定義インタフェイス
 *
 * @@author 孟東
 * @@version 1.0
 */
public interface WEB3GentradePriceCondDef
{
    /**
     * 0：　@DEFAULT(条件指定なし)
     */
    public static final String DEFAULT = "0";
    
    /**
     * 1：　@現在値指値注文
     */
    public static final String CURRENT_PRICE_ORDER = "1";

    /**
     * 3：　@優先指値注文
     */
    public static final String EASE_CURRENT_PRICE_ORDER = "3";

    /**
     * 5：　@成行残数指値注文
     */
    public static final String MARKET_PRICE_REST_LIMIT_PRICE = "5";

    /**
     * 7：　@成行残数取消注文
     */
    public static final String MARKET_PRICE_REST_CANCEL = "7";
}
@
