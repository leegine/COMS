head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.45.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TradeHistoryProductCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3TradeHistoryProductCodeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/28  範慧琴(sinocom)　@新規作成
                   2006/04/25 凌建平(中訊) 仕様変更・モデル048 
*/
package webbroker3.tradehistory.define;

/**
 *商品コード 定数定義インタフェイス
 *                                                                     
 * @@author 範慧琴
 * @@version 1.0
 */
public interface WEB3TradeHistoryProductCodeDef
{

    /**
     * 10：国内株式
     */
    public static final String DOMESTIC_STOCK = "10";

    /**
     * 40：外国株式
     */
    public static final String  FOREIGN_STOCK = "40";
    
    /**
     * 11：信用
     */
    public static final String  MARGIN = "11";

    /**
     * 10：現物
     */
    public static final String  EQUITY = "10";    
    
    /**
     *30：国内債券
     */
    public static final String  DOMESTIC_BOND = "30";    
    
    /**
     * 99：金銭
     */
    public static final String  MONEY = "99";
}@
