head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqChangeCancelDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 訂正取消区分ラベル定義インタフェイス(WEB3FeqChangeCancelDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/11 孟東 (中訊) 新規作成
*/
package webbroker3.feq.define;

/**
 * 訂正取消区分ラベル定義インタフェイス
 *
 * @@author 孟東
 * @@version 1.0
 */
public interface WEB3FeqChangeCancelDivDef
{
    /**
     * 0：初期値
     */
    public static final String INITIAL_VALUE = "0";
    
    /**
     * 1：取消中
     */
    public static final String CANCELING = "1";

    /**
     * 2：一部取消完了
     */
    public static final String PART_CANCELED = "3";

    /**
     * 3：全部取消完了
     */
    public static final String CANCELED = "3";

    /**
     * 4：取消失敗
     */
    public static final String CANCEL_ERROR = "4";

    /**
     * 5：訂正中
     */
    public static final String CHANGING = "5";

    /**
     * 6：一部訂正完了
     */
    public static final String PARTIALLY_CHANGED = "6";

    /**
     * 7：全部訂正完了
     */
    public static final String CHANGED = "7";

    /**
     * 8：訂正失敗
     */
    public static final String CHANGE_ERROR = "8";

    /**
     * 9：訂正後新規注文
     */
    public static final String CHANGED_NEW_ORDER = "9";
}
@
