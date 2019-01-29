head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.50.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3CommodityCodeTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3CommodityCodeTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/02 jia-yuanchun(sinocom)　@新規作成
Revesion History : 2006/10/18 栄イ(中訊) 取引履歴仕様変更管理台帳・ＤＢレイアウトNo020
*/
package webbroker3.common.define;

/**
 * 商品  (商品コード＋分類)　@定数定義インタフェイス
 *                                                                      
 * @@author jia-yuanchun
 * @@version 1.0
 */
public interface WEB3CommodityCodeTypeDef
{
    /**
     * 2:日本投信
     */
    public static final String JAPANESE_MUTUAL_FUND = "2";

    /**
     * 10:日株売買
     */
    public static final String JAPANESE_STOCK_TRADE = "10";

    /**
     * 11:ミニ株売買
     */
    public static final String MINI_STOCK_TRADE = "11";

    /**
     * 12:ミニ株権利
     */
    public static final String MINI_STOCK_CLAIM = "12";

    /**
     * 15:信用取引
     */
    public static final String MARGIN = "15";

    /**
     * 20:投信取引
     */
    public static final String MUTUAL_FUND_TRADE = "20";

    /**
     * 21:投信取引
     */
    public static final String MUTUAL_FUND_TRADING = "21";

    /**
     * 30:日債売買
     */
    public static final String JAPANESE_BOND_TRADE = "30";

    /**
     * 40:外株売買
     */
    public static final String FOREIGN_STOCK_TRADE = "40";

    /**
     * 42:外株権利
     */
    public static final String FOREIGN_STOCK_CLAIM = "42";
}
@
