head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFundNewOrderApplyConfirmInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信新規注文確定インタセプタ（募集）(WEB3MutualFundNewOrderApplyConfirmInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/26 韋念瓊 (中訊) 新規作成
                   2006/09/12 趙林鵬 (中訊) 仕様変更・モデル488、490
                   2006/10/11 張騰宇 (中訊) モデル504
*/

package webbroker3.mf;

import java.sql.Timestamp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * 投信新規注文確定インタセプタ（募集）<BR>
 *
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3MutualFundNewOrderApplyConfirmInterceptor
    extends WEB3MutualFundNewOrderConfirmInterceptor
{

    /**
     *  ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MutualFundNewOrderApplyConfirmInterceptor.class);

    /**
     * 注文種別<BR>
     */
    protected OrderTypeEnum orderType = OrderTypeEnum.MF_RECRUIT;

    /**
     * 入金日<BR>
     */
    protected Timestamp paymentDate;

    /**
     * (投信新規注文確定インタセプタ（募集）)<BR>
     * コンストラクタ<BR>
     * @@roseuid 40AD8E0E03B4
     */
    public WEB3MutualFundNewOrderApplyConfirmInterceptor()
    {
    }

    /**
     * (更新値設定)<BR>
     * （mutateの実装） <BR>
     * <BR>
     * 募集注文処理の中で、投信注文単位データの作成・更新時に呼ばれる。 <BR>
     * 引数で与えられた投信注文単位Paramsに値を設定し、投信注文単位Paramsを返す。 <BR>
     * <BR>
     * １）　@super.mutate()メソッドをコールする。 <BR>
     * <BR>
     * ２）　@投信注文単位Paramsに以下の設定を行う。 <BR>
     * <BR>
     * 　@　@投信注文単位Params.set注文種別(this.注文種別)  <BR>
     *     投信注文単位Params.set入金日(this.入金日) <BR>
     * <BR>
     * 3）　@引数で与えられた投信注文単位Paramsを返す。 <BR>
     * <BR>
     * @@param l_orderManagerPersistenceType - (永続化タイプ)<BR>
     * データの更新または挿入中に呼ぶかどうか指定<BR>
     * @@param l_orderManagerPersistenceContext - (永続化コンテキスト)<BR>
     * 呼び出し時のコンテキストを指定。例えば現物注文中など。<BR>
     * @@param l_mutualFundOrderUnitParams - (投信注文単位Params)<BR>
     * 永続化前の投信注文単位Params<BR>
     * @@return MutualFundOrderActionParams
     * @@roseuid 40AD8E93025C
     */
    public MutualFundOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        MutualFundOrderUnitParams l_mutualFundOrderUnitParams)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType l_orderManagerPersistenceType," +
            "OrderManagerPersistenceContext l_orderManagerPersistenceContext, " +
            "MutualFundOrderUnitParams l_mutualFundOrderUnitParams))";
        log.entering(STR_METHOD_NAME);

        if  (l_mutualFundOrderUnitParams == null)
        {
            log.debug(" パラメータNull出来ない。With " +
                "(永続化前の投信注文単位Params)l_mutualFundOrderActionParams" +
                l_mutualFundOrderUnitParams);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータはNullである");
        }
        //１）　@super.mutate()メソッドをコールする。
        super.mutate(
            l_orderManagerPersistenceType,
            l_orderManagerPersistenceContext,
            l_mutualFundOrderUnitParams);

        //２）　@投信注文単位Paramsに以下の設定を行う。
        //投信注文単位Params.set注文種別(this.注文種別)
        l_mutualFundOrderUnitParams.setOrderType(this.orderType);

        //投信注文単位Params.set入金日(this.入金日)
        l_mutualFundOrderUnitParams.setPaymentDate(this.paymentDate);

        //3）　@引数で与えられた投信注文単位Paramsを返す。
        log.exiting(STR_METHOD_NAME);
        return l_mutualFundOrderUnitParams;
    }

    /**
     * (set入金日)<BR>
     * 入金日の設定を行う。<BR>
     * @@param l_paymentDate - 入金日<BR>
     * @@roseuid 40AD92050133
     */
    public void setPaymentDate(Timestamp l_paymentDate)
    {
        this.paymentDate = l_paymentDate;
    }

    /**
     * (get入金日)<BR>
     * this.入金日を返す。<BR>
     * @@return Timestamp
     * @@roseuid 40AD91EC00E5
     */
    public Timestamp getPaymentDate()
    {
        return paymentDate;
    }

}
@
