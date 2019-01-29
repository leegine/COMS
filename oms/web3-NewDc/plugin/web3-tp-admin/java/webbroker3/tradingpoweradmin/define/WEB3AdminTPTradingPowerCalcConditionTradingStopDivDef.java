head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.37.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPTradingPowerCalcConditionTradingStopDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : 大和総研 証券ソリューションシステム第二部
File Name        : 顧客余力条件余力取引停止区分定義インターフェース(WEB3AdminTPTradingPowerCalcConditionTradingStopDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2005/01/24 堀野 和美(FLJ) 新規作成
*/
package webbroker3.tradingpoweradmin.define;

/**
 * WEB3AdminTPTradingPowerCalcConditionTradingStopDivインターフェース。
 * 顧客余力条件余力取引停止区分を定義する。
 * @@author 堀野 和美(FLJ)
 * @@version 1.0
 *
 */
public interface WEB3AdminTPTradingPowerCalcConditionTradingStopDivDef {

    /**
     * 1: 取引可能
     */
    public static final String ENABLE = "0";

    /**
     * 1: 取引停止
     */
    public static final String STOP = "1";

}
@
