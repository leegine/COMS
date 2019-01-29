head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.52.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3CommissionTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3CommissionTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 手数料タイプ　@定数定義インタフェイス
 *                                                                     
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3CommissionTypeDef
{
    /**
     * 0 : 定率手数料
     */
    public static final String FIXED_RATE_COMMISSIONFEE = "0";

    /**
     * 3 : 約定代金合計
     */
    public static final String EXECUTED_TURNOVER_COUNT = "3";

    /**
     * 4 : 約定回数、約定代金合計
     */
    public static final String EXECUTED_TIMES = "4";

    /**
     * 5 : 一日定額制
     */
    public static final String FIXED_AMOUNT = "5";

}
@
