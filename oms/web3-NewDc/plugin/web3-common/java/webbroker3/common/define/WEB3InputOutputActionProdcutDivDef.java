head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.59.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3InputOutputActionProdcutDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出庫履歴テーブルの商品区分インタフェイス(WEB3InputOutputActionProdcutDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/19 凌建平(中訊) 作成
*/
package webbroker3.common.define;

/**
 * 入出庫履歴テーブルの商品区分インタフェイス
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3InputOutputActionProdcutDivDef 
{

    /**
     * 10（日本株）
     */
    public static final String JAPAN_STOCK = "10";

    /**
     * 20（国内投信）
     */
    public static final String MUTUAL_FUND_DOMESTIC = "20";
    
    /**
     * 21（外国投信）
     */
    public static final String MUTUAL_FUND_FOREIGN = "21";

    /**
     * 22（GP）
     */
    public static final String GP = "22";

    /**
     * 30（債券）
     */
    public static final String BOND = "30";

    /**
     * 40（外国株式）
     */
    public static final String FOREIGN_EQUITY = "40";

    /**
     * 60（外国債券）
     */
    public static final String FOREIGN__BOND = "60";

    /**
     * 70（金地金）
     */
    public static final String GOLD_BAR = "70";

    /**
     * 80（特殊株式）
     */
    public static final String SPRCIAL_EQUITY = "80";

    /**
     * 91（CD）
     */
    public static final String CD = "91";

    /**
     * 92（CP）
     */
    public static final String CP = "92";
}
@
