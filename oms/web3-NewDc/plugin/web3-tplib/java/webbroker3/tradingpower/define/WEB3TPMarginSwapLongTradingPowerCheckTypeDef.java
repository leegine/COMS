head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.52.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPMarginSwapLongTradingPowerCheckTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 「取引余力チェック方法@-信用現引注文時」の定義インターフェース(WEB3TPMarginSwapLongTradingPowerCheckTypeDef.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/08/16 nakazato(DIR-ST) 新規作成
 */
package webbroker3.tradingpower.define;

/**
 * （「取引余力チェック方法@-信用現引注文時」の定義インターフェース）
 */
public interface WEB3TPMarginSwapLongTradingPowerCheckTypeDef
{

    /**
     * (注文銘柄の受渡日以降の保証金余力をチェックする。) <BR>
     */
    public final static String DEFAULT = "0";

    /**
     * (注文銘柄の受渡日以降の追証余力をチェックする。) <BR>
     */
    public final static String ADDDEPOSIT = "1";
}@
