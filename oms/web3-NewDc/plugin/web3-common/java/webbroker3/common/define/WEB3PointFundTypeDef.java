head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.38.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PointFundTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 商品タイプ(WEB3PointFundTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/21 郭英(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * 商品タイプ
 *
 * @@author 郭英
 * @@version 1.0
 */
public interface WEB3PointFundTypeDef
{
    /**
     * 10:  株式 
     */
    public final static String STOCK = "10";

    /**
     * 20:  国内投信　@
     */
    public final static String DOMESTIC_MUTUAL_FUND = "20";
    
    /**
     * 21:  外国投信
     */
    public final static String FOREIGN_MUTUAL_FUND = "21";

    /**
     * 22:  GP　@　@
     */
    public final static String GP = "22";
    
    /**
     * 23:  MRF
     */
    public final static String MRF = "23";

    /**
     * 30:  債券
     */
    public final static String BOND = "30";
    
    /**
     * 40:  外国株式　@ 
     */
    public final static String FOREIGN_EQUITY = "40";

    /**
     * 50:  株式先物
     */
    public final static String EQUITY_FUTURE = "50";

    /**
     * 51:  株式オプション
     */
    public final static String EQUITY_OPTION = "51";
    
    /**
     * 52:  債券先物　@
     */
    public final static String BOND_FUTURE = "52";
    
    /**
     * 53:  債券先物オプション　@
     */
    public final static String BOND_FUTURE_OPTION = "53";
    
    /**
     * 54:  選択権付債券　@
     */
    public final static String OPTIONAL_BOND = "54";
    
    /**
     * 60:  外国債券 　@
     */
    public final static String FOREIGN__BOND = "60";
    
    /**
     * 70:  金地金　@
     */
    public final static String GOLD_BAR = "70";
    
    /**
     * 71:  金GP　@
     */
    public final static String GOLD_GP = "71";
    
    /**
     * 80:  特殊株式　@
     */
    public final static String SPECIAL_STOCK = "80";
    
    /**
     * 91:  CD　@
     */
    public final static String CD = "91";
    
    /**
     * 92:  CP　@
     */
    public final static String CP = "92";
    
    /**
     * 93:  BA 　@
     */
    public final static String BA = "93";
}
@
