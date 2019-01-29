head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoTradeTypeDef.java;


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
package webbroker3.ifo.define;

/**
 * 取引区分
 *                                                                     
 * @@author Wu Yan Fei
 * @@version 1.1
 */
public interface WEB3IfoTradeTypeDef
{
    /**
     *3 : 新規買建注文
     */
    public static final String OPEN_LONG_CONTRACT = "3";

    /**
     *4 : 新規売建注文
     */
    public static final String OPEN_SHORT_CONTRACT = "4";
    
    /**
     *5 : 買建返済注文（売返済）
     */
    public static final String CLOSE_SELL_CONTRACT = "5";
    
    /**
     *6 : 売建返済注文（買返済）
     */
    public static final String CLOSE_BUY_CONTRACT = "6";
}
@
