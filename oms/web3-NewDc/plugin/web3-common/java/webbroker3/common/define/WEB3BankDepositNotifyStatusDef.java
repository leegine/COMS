head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.34.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3BankDepositNotifyStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 処理区分定数定義インタフェイス(WEB3BankDepositNotifyStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/06/20 キョウ再平　@(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 銀行入金通知テーブルの処理区分定数定義インタフェイス
 *
 * @@author キョウ再平(中訊)
 * @@version 1.0
 */

public interface WEB3BankDepositNotifyStatusDef
{
    /**
     * 9:エラー
     */
    public final static String ERROR = "9";
}
@
