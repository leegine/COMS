head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.50.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPCashoutTodayDepositTransferDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 「出金に伴う当日保証金振替実施区分」の定数定義インターフェース(WEB3TPCashoutTodayDepositTransferDivDef.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2009/12/15 張騰宇 新規作成　@モデル413
 */
package webbroker3.tradingpower.define;

/**
 * (「出金に伴う当日保証金振替実施区分」の定数定義インターフェース)
 * @@author 張騰宇
 * @@version 1.0
 */
public interface WEB3TPCashoutTodayDepositTransferDivDef
{
    /**
     * 翌日出金について、当日(T+0)で保証金から預り金への振替を行わない。
     */
    public final static String DEFAULT = "0";

    /**
     * 翌日出金について、当日(T+0)で保証金から預り金への振替を行う。
     */
    public final static String EXECUTE = "1";
}
@
