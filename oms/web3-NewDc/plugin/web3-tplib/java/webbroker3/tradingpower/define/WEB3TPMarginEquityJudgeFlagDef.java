head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.49.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPMarginEquityJudgeFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用現物判定フラグの定義インターフェース(WEB3TPMarginEquityJudgeFlagDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/24 張騰宇 (中訊) 新規作成 仕様変更モデル113
*/
package webbroker3.tradingpower.define;

/**
 * 信用現物判定フラグ　@定数定義インタフェイス
 *
 * @@author 張騰宇
 * @@version 1.0
 */
public interface WEB3TPMarginEquityJudgeFlagDef
{
    /**
     * 0：現物顧客
     */
    public final static String EQUITY_ACCOUNT = "0";

    /**
     * 1：信用顧客
     */
    public final static String MARGIN_ACCOUNT = "1";
}
@
