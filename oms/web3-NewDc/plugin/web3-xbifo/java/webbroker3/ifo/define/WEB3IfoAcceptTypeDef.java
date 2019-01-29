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
filename	WEB3IfoAcceptTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 受付区分　@定数定義インタフェイス (WEB3IfoAcceptTypeDef.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/11/26 凌建平(中訊) 作成
*/
package webbroker3.ifo.define;

/**
 * 受付区分　@定数定義インタフェイス
 *                                                                     
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3IfoAcceptTypeDef {
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
