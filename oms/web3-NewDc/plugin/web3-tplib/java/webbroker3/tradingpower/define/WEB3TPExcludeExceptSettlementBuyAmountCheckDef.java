head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.49.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPExcludeExceptSettlementBuyAmountCheckDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 「会社部店別余力計算条件」の定数定義クラス（WEB3TPExcludeExceptSettlementBuyAmountCheckDef.java）
Author Name      : Daiwa Institute of Research
Revision History : 2007/10/18 崔遠鵬 (中訊) 新規作成　@仕様変更モデル212
*/
package webbroker3.tradingpower.define;

/**
 * 「差金決済相当外買付代金非考慮の差金決済チェック」の定数定義クラス<BR>
 * 
 * @@author 崔遠鵬
 * @@version 1.0
 */
public interface WEB3TPExcludeExceptSettlementBuyAmountCheckDef
{
    /**
     * 0：実施しない
     */
    public final static String DEFAULT = "0";

    /**
     * 1：実施する
     */
    public final static String EXECUTE = "1";
}
@
