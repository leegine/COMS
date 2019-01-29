head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.24.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AttentionInfoDivCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注意情報区分コード定数定義インタフェイス(WEB3AttentionInfoDivCodeDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/01/04 趙林鵬(中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 注意情報区分コード定数定義インタフェイス<BR>
 * (注意情報履歴テーブルの注意情報区分コードの參照)<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public interface WEB3AttentionInfoDivCodeDef
{
    /**
     * A001：売買停止（注文受付可）
     */
    public final static String TRADE_STOP_ORDER_ACCEPT_ENABLE = "A001";

    /**
     * A002：売買停止（注文受付不可）
     */
    public final static String TRADE_STOP_ORDER_ACCEPT_DISABLE = "A002";

    /**
     * A003：売買停止（注文受付可）の取消
     */
    public final static String TRADE_STOP_CANCEL_ORDER_ACCEPT_ENABLE = "A003";

    /**
     * A004：売買停止（注文受付不可）の取消
     */
    public final static String TRADE_STOP_CANCEL_ORDER_ACCEPT_DISABLE = "A004";

    /**
     * A005：売買停止（注文受付可）の解除
     */
    public final static String TRADE_STOP_RELEASE_ORDER_ACCEPT_ENABLE = "A005";

    /**
     * A006：売買停止（注文受付不可）の解除
     */
    public final static String TRADE_STOP_RELEASE_ORDER_ACCEPT_DISABLE = "A006";

    /**
     * A007：売買停止（注文受付可）の解除の取消
     */
    public final static String TRADE_STOP_RELEASE_CANCEL_ORDER_ACCEPT_ENABLE = "A007";

    /**
     * A008：売買停止（注文受付不可）の解除の取消
     */
    public final static String TRADE_STOP_RELEASE_CANCEL_ORDER_ACCEPT_DISABLE = "A008";

    /**
     * A011：売買中断
     */
    public final static String TRADE_INTERRUPT = "A011";

    /**
     * A012：売買中断の取消
     */
    public final static String TRADE_INTERRUPT_CANCEL = "A012";

    /**
     * A013：売買中断の解除
     */
    public final static String TRADE_INTERRUPT_RELEASE = "A013";

    /**
     * A014：売買中断の解除の取消
     */
    public final static String TRADE_INTERRUPT_RELEASE_CANCEL = "A014";

    /**
     * A031：新規上場銘柄の初値が付いた場合
     */
    public final static String OPEN_LISTING_PRODUCT_GIVEN_FIRST_VALUE = "A031";

    /**
     * A081：フリーフォーマット
     */
    public final static String FREE_FORMAT = "A081";
}@
