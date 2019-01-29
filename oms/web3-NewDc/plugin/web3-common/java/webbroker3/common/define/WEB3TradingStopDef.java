head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.46.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TradingStopDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引停止区分 定数定義インタフェイス(WEB3TradingStopDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/21 栄イ (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 取引停止区分 定数定義インタフェイス<BR>
 *<BR>
 * @@author 栄イ (中訊)
 * @@version 1.0
 */
public interface WEB3TradingStopDef
{
    /**
     * 0:取引可能
     */
    public final static String TRADING_ENABLE  = "0";

    /**
     * 1:取引停止
     */
    public final static String TRADING_STOP  = "1";
}
@
