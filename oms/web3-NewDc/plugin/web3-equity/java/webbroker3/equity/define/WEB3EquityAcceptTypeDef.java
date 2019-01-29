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
filename	WEB3EquityAcceptTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 受付区分　@定数定義インタフェイス (WEB3EquityAcceptTypeDef.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/07/27 戴義波(中訊) 作成
*/
package webbroker3.equity.define;

/**
 * 受付区分　@定数定義インタフェイス
 *                                                                     
 * @@author 戴義波
 * @@version 1.0
 */
public interface WEB3EquityAcceptTypeDef {
    /**
     * 0:受付未済
     */
    public final static String EXEC_TYPE_NAN = "0";
    
    /**
     * 1:受付済
     */
    public final static String EXEC_TYPE_NOT_NAN = "1";
    
    /**
     * 2:受付エラー
     */
    public final static String EXEC_TYPE_ERROR = "2";

    /**
     * 4:発注待ち
     */
    public final static String EXEC_TYPE_WAITING = "4";
    
    /**
     * 5:発注遅延
     */
    public final static String EXEC_TYPE_DELAY = "5";
    
    /**
     * 6:翌営業日注文
     */
    public final static String EXEC_TYPE_NEXT_ORDER = "6";
    
    /**
     * 7:発注未処理
     */
    public final static String EXEC_TYPE_UNORDER = "7";
    
}
@
