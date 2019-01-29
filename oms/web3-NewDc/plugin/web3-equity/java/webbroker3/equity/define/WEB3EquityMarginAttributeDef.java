head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityMarginAttributeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 株式銘柄情報制度信用銘柄　@表示名定義インタフェイス (WEB3EquityMarginAttributeDef.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/12/22 SRA坂上 作成
*/
package webbroker3.equity.define;

/**
 * 株式銘柄情報制度信用銘柄　@表示名定義インタフェイス
 *                                                                     
 * @@author SRA坂上
 * @@version 1.0
 */
public interface WEB3EquityMarginAttributeDef {
    /**
     * 1(貸借銘柄)
     */
    public final static String MARGIN_ATTRIBUTE_PRODUCT_LOAN = "1";
    
    /**
     * 2(信用制度銘柄)
     */
    public final static String MARGIN_ATTRIBUTE_PRODUCT_NOLOAN = "2";
    
    /**
     * 3(その他)
     */
    public final static String MARGIN_ATTRIBUTE_PRODUCT_OTHER = "3";
            
}
@
