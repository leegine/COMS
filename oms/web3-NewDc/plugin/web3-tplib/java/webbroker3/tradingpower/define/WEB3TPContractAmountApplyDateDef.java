head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.51.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPContractAmountApplyDateDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 「当日建玉代金計上開始日」の定数定義インターフェース(WEB3TPContractAmountApplyDateDef.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/07/15 nakazato(DIR-ST) 新規作成
 */
package webbroker3.tradingpower.define;


/**
 * (「当日建玉代金計上開始日」の定数定義インターフェース)
 */
public interface WEB3TPContractAmountApplyDateDef
{
    /**
     * (T+1以降より反映)<BR>
     */
    public final static String DEFAULT = "0";

    /**
     * (発注日以降より反映)<BR>
     */
    public final static String FROM_BIZ_DATE = "1";

    /**
     * (T+2以降より反映)<BR>
     */
    public final static String FROM_T2 = "2";
}
@
