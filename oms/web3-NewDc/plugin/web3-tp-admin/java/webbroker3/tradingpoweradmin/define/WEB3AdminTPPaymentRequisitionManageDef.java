head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.36.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisitionManageDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金請求管理インターフェース(WEB3AdminTPIfoAccountOpenDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/03/22 宮本 篤東(SCS) 新規作成
*/
package webbroker3.tradingpoweradmin.define;

/**
 * WEB3AdminTPPaymentRequisitionManageDefインターフェース。
 * 入金請求管理各取引停止区分,摘要を定義する。
 * @@author 宮本 篤東(SCS)
 * @@version 1.0
 *
 */
public interface WEB3AdminTPPaymentRequisitionManageDef
{
    /**
     * 0：停止無し
     */
    public static final String DEFAULT = "0";

    /**
     * 1：20%割れ
     */
    public static final String break20 = "1";

    /**
     * 2：30%割れ
     */
    public static final String break30 = "2";

    /**
     * 3：指定なし
     */
    public static final String notSpecify = "3";

    /**
     * 0：摘要日数すべて
     */
    public static final String allSpecifyDay = "0";

    /**
     * 3：20%割れ指定最大日数
     */
    public static final String max20SpecifyDay = "3";

    /**
     * 8：30%割れ指定最大日数
     */
    public static final String max30SpecifyDay = "8";

}
@
