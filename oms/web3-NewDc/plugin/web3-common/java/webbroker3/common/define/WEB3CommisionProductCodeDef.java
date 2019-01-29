head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.44.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3CommisionProductCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 手数料商品コード定義クラス(WEB3CommisionProductCodeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/09 仲川 里織(SRA) 新規作成
Revesion History : 2006/06/30 凌建平(中訊) 仕様変更・ＤＢレイアウトNo.389を対応
*/
package webbroker3.common.define;

/**
 * 手数料商品コード定数を定義する。
 *
 * @@author 仲川 里織(SRA)
 * @@version 1.0
 */
public interface WEB3CommisionProductCodeDef
{

    /**
     * 10：上場株式
     */
    public final static String LISTING_STOCK = "10";
    
    /**
     * 11：JASDAQ
     */
    public final static String JASDAQ = "11";

    /**
     * 12：ミニ株式
     */
    public final static String MINI_STOCK = "12";
    
    /**
     * 13：IPO
     */
    public final static String IPO = "13";

    /**
     * 20：投資信託
     */
    public final static String MUTUAL_FUND = "20";
    
    /**
     *  30：累積投資
     */
    public final static String RUITO = "30";

    /**
     * 30：債権
     */
    public final static String CREDIT = "30";

    /**
     * 31：債権店頭
     */
    public final static String CREDIT_STORE = "31";

    /**
     * 40：外国株式
     */
    public final static String FOREIGN_EQITY = "40";
    
    /**
     * 50：株価指数先物
     */
    public final static String INDEX_FUTURES = "50";
    
    /**
     * 51：株価指数OP
     */
    public final static String INDEX_OP = "51";

}
@
