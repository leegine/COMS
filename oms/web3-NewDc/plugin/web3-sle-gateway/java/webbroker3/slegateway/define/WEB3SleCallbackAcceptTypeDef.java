head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.59.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleCallbackAcceptTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 受付区分定義インタフェイス(WEB3SleCallbackAcceptTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/13 李 (FTL) 新規作成
*/
package webbroker3.slegateway.define;

/**
 * 受付区分定義インタフェイス
 *
 * @@author 李 (FTL)
 * @@version 1.0
 */
public interface WEB3SleCallbackAcceptTypeDef
{
    /**
     * 01：注文受付済
     */
    public static final String ORDER_ACCEPT_COMPLETE = "01";
    
    /**
     * 02：注文受付エラー
     */
    public static final String ORDER_ACCEPT_ERROR = "09";

    /**
     * 03：注文受付済取消
     */
    public static final String ORDER_ACCEPT_COMPLETE_CANCEL = "03";

    /**
     * 11：訂正済
     */
    public static final String CHANGED = "11";

    /**
     * 12：訂正エラー
     */
    public static final String CHANGE_ERROR = "19";

    /**
     * 21：取消済
     */
    public static final String CANCEL = "21";

    /**
     * 22：取消エラー
     */
    public static final String CANCEL_ERROR = "29";

    /**
     * 31：出来ず
     */
    public static final String NOT_EXECUTED = "31";
}
@
