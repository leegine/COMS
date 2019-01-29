head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.49.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAdddepositCheckDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 「追証余力チェック方法@-XXX時」の定数定義インターフェース(WEB3TPAdddepositCheckDef.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/07/27 nakazato(DIR-ST) 新規作成
 */
package webbroker3.tradingpower.define;


/**
 * (「追証余力チェック方法@-XXX時」の定数定義インターフェース)
 */
public interface WEB3TPAdddepositCheckDef
{
    /**
     * (チェックなし)<BR>
     */
    public final static String DEFAULT = "0";

    /**
     * (T+発注日～T+受渡日-1（受渡日前日）の追証余力が0未満でないかチェックする)<BR>
     */
    public final static String FROM_BIZ_DATE_UNTIL_PRE_DELIVERY_DATE = "1";

    /**
     * (T+発注日（発注日）の追証余力が0未満でないかチェックする)<BR>
     */
    public final static String ON_BIZ_DATE = "2";
}
@
