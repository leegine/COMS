head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.50.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPOrixSecuredLoanLockDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 金額ロックフラグの定義インターフェース(WEB3TPAccountBalanceStatusDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/24 孟亞南 (中訊) 新規作成
*/
package webbroker3.tradingpower.define;

/**
 * 金額ロックフラグ　@定数定義インタフェイス
 *
 * @@author 孟亞南
 * @@version 1.0
 */
public interface WEB3TPOrixSecuredLoanLockDef
{
    /**
     * 金額ロックフラグ == 1
     */
    public final static String ORIX_SECURED_LOAN = "1";
}
@
