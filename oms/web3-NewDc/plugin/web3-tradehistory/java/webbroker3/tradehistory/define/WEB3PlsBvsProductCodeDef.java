head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.45.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3PlsBvsProductCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 商品コード(WEB3PlsBvsProductCodeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/05 賈元春(sinocom) 新規作成
*/
package webbroker3.tradehistory.define;

/**
 * 商品コード 定数定義インタフェイス
 * @@author 賈元春
 * @@version 1.0
 */
public interface WEB3PlsBvsProductCodeDef
{

    /**
     * 10:　@株式
     */
    public final static String EQUITY = "10";

    /**
     * 11:　@信用
     */
    public final static String MARGIN = "11";
    
    /**
     * 15:　@ミニ株
     */
    public final static String MINISTOCK = "15";    

    /**
     * 20:　@投信
     */
    public final static String MUTUAL = "20";

    /**
     * 21:　@外投
     */
    public final static String FOREIGN_MUTUAL = "21";

    /**
     * 22:　@累投
     */
    public final static String RUITO = "22";

    /**
     * 23:　@MRF
     */
    public final static String MRF = "23";

    /**
     * 30:　@債券
     */
    public final static String BOND = "30";

    /**
     * 40:　@外株
     */
    public final static String FOREIGN_STOCK = "40";

    /**
     * 50:　@株先
     */
    public final static String STOCK_FUTURES = "50";

    /**
     * 51： 株指数OP
     */
    public final static String STOCK_INDEX_OP = "51";

    /**
     * 52:　@債先
     */
    public final static String BOND_FUTURES = "52";

    /**
     * 53:　@債先OP
     */
    public final static String BOND_FUTURES_OP = "53";

    /**
     * 54:　@店OP
     */
    public final static String BRANCH_OP = "54";

    /**
     * 55:　@外先
     */
    public final static String FOREIGN_FUTURES = "55";

    /**
     * 56:　@外先OP
     */
    public final static String FOREIGN_FUTURES_OP = "56";

    /**
     * 57:　@株OP
     */
    public final static String STOCK_OP = "57";

    /**
     * 60:　@外債
     */
    public final static String FOREIGN_BOND = "60";

    /**
     * 70:　@金
     */
    public final static String CASH = "70";

    /**
     * 71:　@金GP
     */
    public final static String CASH_GP = "71";

    /**
     * 80:　@特殊
     */
    public final static String SPECIAL= "80";    
    
    /**
     * 91:　@CD
     */
    public final static String CD = "91";

    /**
     * 92:　@CP
     */
    public final static String CP = "92";

    /**
     * 93:　@BA
     */
    public final static String BA = "93";

    /**
     * 99:　@金銭
     */
    public final static String MONEY = "99";               
}
@
