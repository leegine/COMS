head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.03.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AccountInfoAcceptStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 受付結果区分  定数定義インタフェイス(WEB3AccountInfoAcceptStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/13　@謝旋 (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 携帯番号.勤務先情報変更申込の受付結果区分 定数定義インタフェイス
 *
 * @@author 謝旋 (中訊)
 * @@version 1.0
 */
public class WEB3AccountInfoAcceptStatusDef
{
    /**
     *  0：受付未済
     */
    public static final String NOT_ACCEPT = "0";

    /**
     *  1：受付完了
     */
    public static final String ACCEPT_COMPLETE = "1";

    /**
     *  9：エラー
     */
    public static final String ERROR = "9";

}
@
