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
filename	WEB3IfoCanmodReceiptTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 注文訂正・取消区分インタフェイス (WEB3IfoCanmodReceiptTypeDef.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/01/19 凌建平(中訊) 作成
*/

package webbroker3.ifo.define;

/**
 * 注文訂正・取消区分インタフェイス
 *                                                                     
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3IfoCanmodReceiptTypeDef 
{
    /**
     * 1：訂正完了
     */
    public static final String CHANGED_COMPLETE = "1";

    /**
     * 2：訂正失敗
     */
    public static final String CHANGED_FAILED = "2";

    /**
     * 3：取消完了
     */ 
    public static final String CANCELED_COMPLETE = "3";

    /**
     * 4：取消失敗
     */
    public static final String CANCELED_FAILED = "4";

    /**
     * 5：エラー
     */
    public static final String ERROR_NOTIFY = "5";   
}
@
