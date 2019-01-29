head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.50.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPRealCalcFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 「時価計算フラグ」の定数定義クラス(WEB3TPRealCalcFlagDef.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/02/02 kikuchi(SCS) 新規作成
 */
package webbroker3.tradingpower.define;

/**
 * 「時価計算フラグ」の定数定義クラス<br>
 *
 * @@author kikuchi
 * @@version 1.0
 */
public interface WEB3TPRealCalcFlagDef
{

    /**
     * 前日終値評価で再計算
     */
    public static final String CLOSING_PRICE = "0";

    /**
     * リアル時価評価で再計算
     */
    public static final String CURRENT_MARKET = "1";

}
@
