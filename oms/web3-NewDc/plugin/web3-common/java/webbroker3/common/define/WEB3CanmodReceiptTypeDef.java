head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.01.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3CanmodReceiptTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3CanmodReceiptTypeDef)
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
public interface WEB3CanmodReceiptTypeDef
{
    /**
     * 1 : 訂正完了
     */
    public static final String CHANGED = "1";

    /**
     * 2 : 訂正失敗
     */
    public static final String CHANGE_FAILED = "2";

    /**
     * 3 : 取消完了
     */
    public static final String CANCEL = "3";

    /**
     * 4 : 取消失敗
     */
    public static final String CANCEL_FAILED = "4";

    /**
     * 5 : エラー
     */
    public static final String ERROR = "5";

}
@
