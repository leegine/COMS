head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoTradedOrderNotifyDecisionInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累投売買注文通知確定インタセプタ(WEB3RuitoTradedOrderNotifyDecisionInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 韋念瓊 (中訊) 新規作成
*/
package webbroker3.xbruito;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BalanceAddFlagDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * 累投売買注文通知確定インタセプタ。<BR>
 */
public class WEB3RuitoTradedOrderNotifyDecisionInterceptor
    extends WEB3RuitoDefaultRuitoOrderDecisionInterceptor
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        WEB3RuitoTradedOrderNotifyDecisionInterceptor.class);

    /**
     * 受渡日<BR>
     */
    private Timestamp deliveryDate;

    /**
     * 受注日時<BR>
     */
    private Timestamp acceptedDate;

    /**
     * 識別コード<BR>
     */
    private String requestNumber;

    /**
     * 1：銀行振込み、2：証券口座入金<BR>
     */
    private String paymentMethod;

    /**
     * 0:その他　@1:MMF　@2:中期国債ファ@ンド　@3:MRF<BR>
     */
    private RuitoTypeEnum ruitoTypeEnum;

    /**
     * 2:全部指定　@3:金額指定　@4:口数指定<BR>
     */
    private String ruitoSellDiv;

    /**
     * 0：営業店　@1：インターネット　@2：コールセンタ　@3：モバイル<BR>
     */
    private String orderChannel;

    /**
     * デフォルトコンストラクタ<BR>
     * @@roseuid 408F3D2A03DC
     */

    public WEB3RuitoTradedOrderNotifyDecisionInterceptor()
    {

    }

    /**
     * （mutateのオーバーライド）<BR>
     * <BR>
     * 引数で与えられた累投注文単位Paramsに値を設定し、<BR>
     *      累投注文単位Paramsを返す。<BR>
     * <BR>
     * １）　@発注日の設定を行う<BR>
     * 　@－累投注文単位Params.setBizDate()をコールし、<BR>
     *        発注日の設定を行う。<BR>
     * 　@　@[setBizDateに渡すパラメタ]<BR>
     * 　@　@　@発注日： this.get受注日時()の戻り値を<BR>
     *              yyyyMMdd形式に変換した文字列<BR>
     * <BR>
     * ２）　@扱者コード(SONAR)の設定を行う。<BR>
     * 　@－累投注文単位Params.getAccountId()に<BR>
     *         対応する顧客オブジェクトを取得する。<BR>
     * 　@－累投注文単位Params.setSonarTraderCode()をコールし、<BR>
     *        扱者コード(SONAR)の設定を行う。<BR>
     * 　@　@［setSonarTraderCodeに渡すパラメタ］<BR>
     * 　@　@　@扱者コード(SONAR)： 
     * 顧客.getDataSourceObject().getSonarTraderCode()の戻り値<BR>
     * <BR>
     * ３）　@残高計上フラグの設定を行う。<BR>
     * 　@－累投注文単位Params.set残高計上フラグ()をコールし、<BR>
     *           残高計上フラグの設定を行う。<BR>
     * 　@　@[set残高計上フラグに渡すパラメタ]<BR>
     * 　@　@　@残高計上フラグ： ”0：残高計上未済”<BR>
     * <BR>
     * ４）　@累投注文単位Paramsに、以下の設定を行う。<BR>
     * <BR>
     * 　@　@累投注文単位Params.setOrderRootDiv(WEB3OrderRootDivDef.HOST)<BR>
     * 　@　@累投注文単位Params.setErrorReasonCode(null)<BR>
     * 　@　@累投注文単位Params.setMrfOrderRequestNumber(null)<BR>
     * 　@　@累投注文単位Params.setReturnMethod(null)<BR>
     * <BR>
     * ５）　@当オブジェクトに設定されている値を累投注文単位Paramsに設定する。<BR>
     * <BR>
     * 　@　@累投注文単位Params.seDeliveryDate(this.get受渡日())<BR>
     * 　@　@累投注文単位Params.setReceivedDateTime(this.get受注日時())<BR>
     * 　@　@累投注文単位Params.setOrderRequestNumber(this.get識別コード())<BR>
     * 　@　@累投注文単位Params.setPaymentMethod(this.get受渡方法@())<BR>
     * 　@　@累投注文単位Params.setRuitoType(this.get累投タイプ())<BR>
     * 　@　@累投注文単位Params.setGpSellDiv(this.get累投解約区分())<BR>
     * 　@　@累投注文単位Params.setOrderChanel(this.get注文チャネル())<BR>
     * <BR>
     * ６）　@引数で与えられた累投注文単位Paramsを返す。<BR>
     * @@param l_persistenceType - データの更新または挿入中に呼ぶかどうか指定<BR>
     * @@param l_persistenceContext - 
     * 呼び出し時のコンテキストを指定。例えば現物注文中など<BR>
     * @@param l_unitParams - 永続化前の累投注文単位Params<BR>
     * @@return RuitoOrderUnitParams
     * @@roseuid 408F3D0D0274
     */
    public RuitoOrderUnitParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_persistenceContext,
        RuitoOrderUnitParams l_unitParams)
    {
        String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType l_persistenceType,"
                + "OrderManagerPersistenceContext l_persistenceContext, "
                + "RuitoOrderUnitParams l_unitParams)";
        log.entering(STR_METHOD_NAME);
        try
        {
            if (l_unitParams == null)
            {
                log.debug(" パラメータ値がNULL ");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "パラメータ値がNULL");
            }
            //1)発注日の設定を行う            
            String l_strBizDate = WEB3DateUtility.formatDate(this.getAcceptedDate(), "yyyyMMdd");
            log.debug("発注日 = " + l_strBizDate);
            l_unitParams.setBizDate(l_strBizDate);
           
            //2)扱者コード(SONAR)の設定を行う。
            long l_lngAccountId = l_unitParams.getAccountId();
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
            AccountManager l_accMgr = l_finApp.getAccountManager();
            
            MainAccount l_acc = l_accMgr.getMainAccount(l_lngAccountId);
            MainAccountRow l_mainAccountRow = (MainAccountRow)
                l_acc.getDataSourceObject();
                
            String l_strSonarTraderCode = l_mainAccountRow.getSonarTraderCode();
            log.debug("扱者コード = " + l_strSonarTraderCode);
            l_unitParams.setSonarTraderCode(l_strSonarTraderCode);

            //3)残高計上フラグの設定を行う。
            l_unitParams.setBalanceAddFlag(
                WEB3BalanceAddFlagDef.BALANCE_OUTSTANDING);

            //4)累投注文単位Paramsに、以下の設定を行う。
            l_unitParams.setOrderRootDiv(WEB3OrderRootDivDef.HOST);
            l_unitParams.setErrorReasonCode(null);
            l_unitParams.setMrfOrderRequestNumber(null);
            l_unitParams.setReturnMethod(null);

            //5)当オブジェクトに設定されている値を累投注文単位Paramsに設定する。            
            l_unitParams.setDeliveryDate(this.getDeliveryDate());
            l_unitParams.setReceivedDateTime(this.getAcceptedDate());
            l_unitParams.setOrderRequestNumber(this.getRequestNumber());
            l_unitParams.setPaymentMethod(this.getPaymentMethod());
            l_unitParams.setRuitoType(this.getRuitoTypeEnum());
            l_unitParams.setGpSellDiv(this.getRuitoSellDiv());
            l_unitParams.setOrderChanel(this.getOrderChannel());
        }        
        catch (NotFoundException l_ex)
        {
               log.error("該当する顧客オブジェクトがありません"); 
               throw new WEB3BaseRuntimeException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                   l_ex.getMessage(),
                   l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        //6)引数で与えられた累投注文単位Paramsを返す。
        return l_unitParams;
    }

    /**
     * （mutateのオーバーライド）<BR>
     * <BR>
     * 引数で与えられた累投注文履歴Paramsに値を設定し、<BR>
     *     累投注文履歴Paramsを返す。<BR>
     * <BR>
     * １）　@注文エラー理由コードの設定を行う。<BR>
     * 　@－累投注文履歴Params.set注文エラー理由コード()をコールし、<BR>
     *         注文エラー理由コードの設定を行う。<BR>
     * 　@　@［set注文エラー理由コードに渡すパラメタ］<BR>
     * 　@　@　@注文エラー理由コード： null<BR>
     * <BR>
     * ２）　@引数で与えられた累投注文履歴Paramsを返す。<BR>
     * @@param l_persistenceType - データの更新または挿入中に呼ぶかどうか指定<BR>
     * @@param l_persistenceContext - <BR>
     *      呼び出し時のコンテキストを指定。例えば現物注文中など<BR>
     * @@param l_unitParams - 永続化前の累投注文履歴Params<BR>
     * @@return RuitoOrderActionParams
     * @@roseuid 4091A0E20257
     */
    public RuitoOrderActionParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_persistenceContext,
        RuitoOrderActionParams l_unitParams)
    {
        //1)注文エラー理由コードの設定を行う。        
        l_unitParams.setErrorReasonCode(null);
        //2)引数で与えられた累投注文履歴Paramsを返す。
        return l_unitParams;
    }

    /**
     * 受渡日の設定を行う。<BR>
     * @@param l_deliveryDate - 受渡日<BR>
     * @@roseuid 408F3D5A0023
     */
    public void setDeliveryDate(Timestamp l_deliveryDate)
    {
        deliveryDate = l_deliveryDate;
    }

    /**
     * this.受渡日を返す。<BR>
     * @@return Timestamp
     * @@roseuid 408F3D6802A3
     */
    public Timestamp getDeliveryDate()
    {
        return deliveryDate;
    }

    /**
     * 受注日時の設定を行う。<BR>
     * @@param l_acceptedDate - 受注日時<BR>
     * @@roseuid 408F3D770274
     */
    public void setAcceptedDate(Timestamp l_acceptedDate)
    {
        acceptedDate = l_acceptedDate;
    }

    /**
     * this.受注日時を返す。<BR>
     * @@return Timestamp
     * @@roseuid 408F3D8C011D
     */
    public Timestamp getAcceptedDate()
    {
        return acceptedDate;
    }

    /**
     * 累投注文単位の識別コードを設定する。<BR>
     * @@param l_strRequestNumber - 累投注文単位の識別コード<BR>
     * @@roseuid 4091A0E200D0
     */
    public void setRequestNumber(String l_strRequestNumber)
    {
        requestNumber = l_strRequestNumber;
    }

    /**
     * this.識別コードを返す。<BR>
     * @@return String
     * @@roseuid 4091A0E200FF
     */
    public String getRequestNumber()
    {
        return requestNumber;
    }

    /**
     * 受渡方法@の設定を行う。<BR>
     * @@param l_strPaymentMethod - 受渡方法@<BR>
     * @@roseuid 4091A0E2015D
     */
    public void setPaymentMethod(String l_strPaymentMethod)
    {
        paymentMethod = l_strPaymentMethod;
    }

    /**
     * this.受渡方法@を返す。<BR>
     * @@return String
     * @@roseuid 4091A0E201DA
     */
    public String getPaymentMethod()
    {
        return paymentMethod;
    }

    /**
     * 累投タイプの設定を行う。<BR>
     * @@param l_ruitoTypeEnum - 累投タイプ<BR>
     * @@roseuid 4091A0E2017C
     */
    public void setRuitoTypeEnum(RuitoTypeEnum l_ruitoTypeEnum)
    {
        ruitoTypeEnum = l_ruitoTypeEnum;
    }

    /**
     * this.累投タイプを返す。<BR>
     * @@return RuitoTypeEnum
     * @@roseuid 4091A0E201EA
     */
    public RuitoTypeEnum getRuitoTypeEnum()
    {
        return ruitoTypeEnum;
    }

    /**
     * 累投解約区分の設定を行う。<BR>
     * @@param l_strRuitoSellDiv - 累投解約区分<BR>
     * @@roseuid 4091A0E2018C
     */
    public void setRuitoSellDiv(String l_strRuitoSellDiv)
    {
        ruitoSellDiv = l_strRuitoSellDiv;
    }

    /**
     * this.累投解約区分を返す。<BR>
     * @@return String
     * @@roseuid 4091A0E20209
     */
    public String getRuitoSellDiv()
    {
        return ruitoSellDiv;
    }

    /**
     * 注文チャネルの設定を行う。<BR>
     * @@param l_strOrderChannel - 注文チャネル<BR>
     * @@roseuid 4091A0E20276
     */
    public void setOrderChannel(String l_strOrderChannel)
    {
        orderChannel = l_strOrderChannel;
    }

    /**
     * this.注文チャネルを返す。<BR>
     * @@return String
     * @@roseuid 4091A0E20286
     */
    public String getOrderChannel()
    {
        return orderChannel;
    }

}
@
