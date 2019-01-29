head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.46.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ApplicationCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3ApplicationCodeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/02 jia-yuanchun(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 適用コード　@定数定義インタフェイス
 *                                                                      
 * @@author jia-yuanchun
 * @@version 1.0
 */
public interface WEB3ApplicationCodeDef
{
    /**
     * 10:信用決済
     */
    public static final String MARGIN_SETTLE = "10";

    /**
     * 11:確定配当
     */
    public static final String CONFIRM_DIVIDEND = "11";

    /**
     * 12:預かり配当
     */
    public static final String DEPOSIT_DIVIDEND = "12";

    /**
     * 13:権利受払金
     */
    public static final String CLAIM_PAYMENT = "13";

    /**
     * 20:ミニ株売買
     */
    public static final String MINI_STOCK_TRADE = "20";

    /**
     * 21:ミニ端株売却
     */
    public static final String MINI_STOCK_SALE = "21";

    /**
     * 22:ミニ有償増資
     */
    public static final String MINI_STOCK_ONEROUS_INCREASE = "22";

    /**
     * 31:外株権利売却
     */
    public static final String FOREIGN_STOCK_CLAIM_SALE = "31";

    /**
     * 00:以外
     */
    public static final String OTHER = "00";
}
@
