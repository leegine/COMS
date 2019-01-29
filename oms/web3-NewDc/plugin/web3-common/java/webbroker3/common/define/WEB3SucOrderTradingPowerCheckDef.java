head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.26.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SucOrderTradingPowerCheckDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連続注文余力チェック実施定数定義インタフェイス(WEB3SucOrderTradingPowerCheckDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/17 凌建平(中訊)　@新規作成
*/
package webbroker3.common.define;

/**
 * 連続注文余力チェック実施定数定義インタフェイス
 * 
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3SucOrderTradingPowerCheckDef
{
    /**
     * 0：チェックなし
     */
    public static final String DEFAULT = "0";

    /**
     * 1：余力チェック要
     */
    public static final String CHECK_TRADING_POWER = "1";
}
@
