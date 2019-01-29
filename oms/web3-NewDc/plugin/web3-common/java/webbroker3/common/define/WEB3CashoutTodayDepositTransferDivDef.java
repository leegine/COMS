head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.08.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3CashoutTodayDepositTransferDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金に伴う当日保証金振替実施区分定数定義インタフェイス(WEB3CashoutTodayDepositTransferDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/12/15 趙林鵬(中訊) 新規作成 ＤＢレイアウト700
*/

package webbroker3.common.define;

/**
 * 出金に伴う当日保証金振替実施区分定数定義インタフェイス<BR>
 * (部店用プリファ@レンステーブルのプリファ@レンスの値の參照)<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public interface WEB3CashoutTodayDepositTransferDivDef
{
    /**
     * 0：翌日出金について、当日(T+0)で保証金から預り金への振替を行わない。
     */
    public final static String DEFAULT = "0";

    /**
     * 1：翌日出金について、当日(T+0)で保証金から預り金への振替を行う。
     */
    public final static String EXECUTE = "1";
}@
