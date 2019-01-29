head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.50.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPEqtypeSwapMarginCostUndeliveredContractLossDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 「建玉諸経費(現引現渡)の未受渡決済損計上区分」の定数定義インターフェース(WEB3TPEqtypeSwapMarginCostUndeliveredContractLossDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/01 崔遠鵬(中訊) 新規作成モデルNo.254-256、モデルNo.260
*/
package webbroker3.tradingpower.define;

/**
 * (「建玉諸経費(現引現渡)の未受渡決済損計上区分」の定数定義インターフェース)
 */
public interface WEB3TPEqtypeSwapMarginCostUndeliveredContractLossDivDef
{
    /**
     * 建玉諸経費(現引現渡)を未受渡決済損に計上しない<BR>
     */
    public final static String DEFAULT = "0";

    /**
     * 建玉諸経費(現引現渡)を未受渡決済損に計上する
     */
    public final static String EXECUTE = "1";
}
@
