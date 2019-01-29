head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.49.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPEquityBuyTradingPowerCheckTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 「取引余力チェック方法@-現物株式買付注文時」の定義インターフェース(WEB3TPEquityBuyTradingPowerCheckTypeDef.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/03/15 nakazato(DIR-ST) 新規作成
 */
package webbroker3.tradingpower.define;

/**
 * （「取引余力チェック方法@-現物株式買付注文時」の定義インターフェース）
 */
public interface WEB3TPEquityBuyTradingPowerCheckTypeDef
{

    /**
     * (今回現物買注文について、「銘柄の代用掛目」を使用して、取引余力チェックを実施) <BR>
     */
    public final static String DEFAULT = "0";

    /**
     * (今回現物買注文について、「銘柄の代用掛目」と「余力計算代用掛目」の小さい方を使用して、取引余力チェックを実施) <BR>
     */
    public final static String BRANCH_SUBRATE = "1";
}
@
