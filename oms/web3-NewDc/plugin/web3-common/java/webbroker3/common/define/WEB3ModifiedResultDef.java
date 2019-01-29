head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.29.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ModifiedResultDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3ModifiedResultDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 訂正結果コード　@定数定義インタフェイス
 *                                                                      
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3ModifiedResultDef
{
    /**
     * 01 : 全部取消　@　@　@　@　@　@　@　@　@　@　@  取消完了
     */
    public static final String ALL_CANCEL = "01";

    /**
     * 02 : 全数訂正(内出来なし)　@　@　@　@　@  訂正完了 
     */
    public static final String ALL_CHANGED_NO_EXECUTED = "02";

    /**
     * 03 : 全数訂正(内出来あり)　@　@　@　@　@  訂正完了
     */
    public static final String ALL_CHANGED_PARTIALLY_EXECUTED = "03";

    /**
     * 04 : 一部取消　@　@　@　@　@　@　@　@　@　@　@  取消完了
     */
    public static final String PARTIALLY_CANCEL = "04";

    /**
     * 05 : 一部訂正　@　@　@　@　@　@　@　@　@　@　@  訂正完了
     */
    public static final String PARTIALLY_CHANGED = "05";

    /**
     * 06 : 一部取消不能(内出来なし)       取消完了
     */
    public static final String PARTIALLY_NOT_CANCEL_NO_EXECUTED = "06";

    /**
     * 07 :  一部取消不能(内出来あり)       取消完了
     */
    public static final String PARTIALLY_NOT_CANCEL_EXECUTED = "07";

    /**
     * 08 : 一部訂正不能(内出来なし)       訂正完了
     */
    public static final String PARTIALLY_NOT_CHANGED_NO_EXECUTED = "08";

    /**
     * 09 : 一部訂正不能(内出来あり)       訂正完了
     */
    public static final String PARTIALLY_NOT_CHANGED_PARTIALLY_EXECUTED = "09";

    /**
     * 10 : 全数約定済み                  訂正失敗、取消失敗
     */
    public static final String ALL_EXECUTED = "10";

    /**
     * 11 : 始値決定済み                  訂正失敗
     */
    public static final String OPENING_RATE_DECIDED = "11";

    /**
     * 20 : 訂正伝票破棄                  訂正失敗、取消失敗
     */
    public static final String CHANGE_SLIP_DESTORIED = "20";

    /**
     * 21 : 間に合わず                    訂正失敗、取消失敗
     */
    public static final String MAKESHIFT = "21";

}
@
