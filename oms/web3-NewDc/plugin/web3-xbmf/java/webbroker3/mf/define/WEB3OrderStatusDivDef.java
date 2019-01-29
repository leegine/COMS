head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OrderStatusDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3OrderStatusDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 王蘭芬(中訊)　@新規作成
*/
package webbroker3.mf.define;

/**
 * 注文状態区分　@定数定義インタフェイス
 *                                                                     
 * @@author 王蘭芬
 * @@version 1.0
 */
public interface WEB3OrderStatusDivDef
{
    /**
     * 1 : 注文中
     */
    public static final String ORDERING = "1";

    /**
     * 6 : 注文受付エラー
     */
    public static final String ORDER_ERROR = "6";

    /**
     * 12 : 注文取消中
     */
    public static final String ORDER_CANCELING = "12";
    
    /**
     * 14 : 注文取消済
     */
    public static final String ORDER_CANCELED = "14";

    /**
     * 15 : 注文取消失敗　@ 
     */
    public static final String ORDER_CANCEL_FAILED = "15";

    /**
     * 52 : 約定中　@  
     */
    public static final String EXECUTED_IN_PROCESS = "52";

    /**
     * 53 : 約定済
     */
    public static final String EXECUTED_COMPLETE = "53";

    /**
     * 54 : 約定済 銀行振込
     */
    public static final String EXECUTED_COMPLETE_BANK_TRANSFER = "54";

    /**
     * 55 : XXXへ乗換中    
     */
    public static final String SWITCHING = "55";
    
	/**
	 * 56 : 取消不可    
	 */
	public static final String CANCEL_DISABLE = "56";
}
@
