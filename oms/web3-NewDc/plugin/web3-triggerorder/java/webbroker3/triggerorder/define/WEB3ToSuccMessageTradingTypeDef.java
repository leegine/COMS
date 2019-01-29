head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.38.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMessageTradingTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : メッセージ用取引区分定数定義インタフェイス(WEB3ToSuccMessageTradingTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/13  郭英(sinocom)　@新規作成
*/
package webbroker3.triggerorder.define;

/**
 * 株式予約注文単位Implのメッセージ用取引区分定数定義インタフェイス
 *                                                                     
 * @@author 郭英
 * @@version 1.1
 */
public interface WEB3ToSuccMessageTradingTypeDef
{
    /**
     * 1 : 現物買注文
     */
    public static final String BUY_ORDER = "1";

    /**
     * 2 : 現物売注文
     */
    public static final String SELL_ORDER = "2";
    
    /**
     * 3 : 新規買建注文
     */
    public static final String OPEN_LONG_MARGIN = "3";

    /**
     * 4 : 新規売建注文
     */
    public static final String OPEN_SHORT_MARGIN = "4";
    
    /**
     * 5 : 買建返済注文（売返済）
     */
    public static final String CLOSE_LONG_MARGIN = "5";
    
    /**
     * 6 : 売建返済注文（買返済）
     */
    public static final String CLOSE_SHORT_MARGIN = "6";
    
    /**
     * 7 : 現引注文
     */
    public static final String  SWAP_MARGIN_LONG= "7";
    
    /**
     * 8 : 現渡注文
     */ 
    public static final String SWAP_MARGIN_SHORT = "8";
}
@
