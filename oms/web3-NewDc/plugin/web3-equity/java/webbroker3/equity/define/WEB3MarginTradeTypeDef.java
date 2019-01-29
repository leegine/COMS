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
filename	WEB3MarginTradeTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3MarginSwapAccountTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/20  WuYanFei(sinocom)　@新規作成
*/
package webbroker3.equity.define;

/**
 * 取引区分
 *                                                                     
 * @@author Wu Yan Fei
 * @@version 1.1
 */
public interface WEB3MarginTradeTypeDef
{
    /**
     *3 : 新規買建注文
     */
    public static final String OPEN_LONG_MARGIN = "3";

    /**
     *4 : 新規売建注文
     */
    public static final String OPEN_SHORT_MARGIN = "4";
    
    /**
     *5 : 買建返済注文（売返済）
     */
    public static final String CLOSE_SELL_MARGIN = "5";
    
    /**
     *6 : 売建返済注文（買返済）
     */
    public static final String CLOSE_BUY_MARGIN = "6";
    
    /**
     *7 : 現引注文
     */
    public static final String  GENBIKI_ORDER= "7";
    
    /**
     *8 : 現渡注文
     */ 
    public static final String GENWADASI_ORDER = "8";
}
@
