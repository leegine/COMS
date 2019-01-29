head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.49.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TradedDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@                                                                    /**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累投売買区分  定数定義クラス(WEB3GpSellDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/03 韋念瓊 (sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * 累投売買区分  定数を定義する。
 *
 * @@author 韋念瓊 (sinocom)
 * @@version 1.0
 */
public interface WEB3TradedDivDef
{

    /**
     * 1：買付　@
     */
    public final static String BUY = "1";

    /**
     * 2：全部解約
     */
    public final static String ALL_SELL = "2";

    /**
     * 3：一部解約
     */
    public final static String PARTIALLY_SELL = "3";

}
@
