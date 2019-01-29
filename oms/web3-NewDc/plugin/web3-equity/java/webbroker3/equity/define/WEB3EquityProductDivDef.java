head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityProductDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 商品区分　@定数定義インタフェイス (WEB3EquityAcceptTypeDef.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/07/27 関博(中訊) 作成
*/
package webbroker3.equity.define;


/**
 * 商品区分　@定数定義インタフェイス
 *                                                                     
 * @@author 関博
 * @@version 1.0
 */
public interface WEB3EquityProductDivDef
{
    /**
     * 商品区分:
     * 0： 現物株式、信用取引 すべて
     */
    public final static String EQUITY_MARGIN = "0";
    
    /**
     * 商品区分:
     * 1： 現物株式
     */
    public final static String EQUITY = "1";
    
    /**
     * 商品区分:
     * 2： 信用取引
     */
    public final static String MARGIN = "2";

}
@
