head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.04.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TradingTimeCheckDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引時間チェック区分 定数定義インタフェイス(WEB3TradingTimeCheckDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/28 栄イ (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 取引時間チェック区分 定数定義インタフェイス<BR>
 *<BR>
 * @@author 栄イ (中訊)
 * @@version 1.0
 */
public interface WEB3TradingTimeCheckDivDef
{
    /**
     * 0：取引時間をチェックする
     */
    public final static String TRADING_TIME_CHECK  = "0";

    /**
     * 1：取引時間をチェックしない
     */
    public final static String TRADING_TIME_NOT_CHECK  = "1";
}
@
