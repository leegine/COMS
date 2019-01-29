head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoMarketRequestSubmitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累投市場リクエスト送信サービスクラス(WEB3RuitoMarketRequestSubmitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/15  李志強 (中訊) 新規作成
*/
package webbroker3.xbruito.marketadaptor;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.DefaultMarketRequestSendResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSendResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TooLateException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.CancelOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.MarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoMarketRequestSenderService;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoOrder;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoProductManager;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.market.messages.RuitoNewOrderMarketRequestMessage;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3OrderDivTypeDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3SettleDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3TransferRouteDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.mqgateway.WEB3MQGatewayService;
import webbroker3.mqgateway.WEB3MQMessageSpec;
import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.WEB3RuitoAcceptedDecisionInterceptor;
import webbroker3.xbruito.WEB3RuitoOrderManager;
import webbroker3.xbruito.WEB3RuitoProduct;
import webbroker3.xbruito.WEB3RuitoProductManager;
import webbroker3.xbruito.data.HostMrfOrderCancelParams;
import webbroker3.xbruito.data.HostMrfOrderParams;
import webbroker3.xbruito.data.HostRuitoOrderCancelParams;
import webbroker3.xbruito.data.HostRuitoOrderParams;
import webbroker3.xbruito.data.HostRuitoSellParams;
import webbroker3.xbruito.data.HostSellCancelParams;

/**
 * 累投市場リクエスト送信サービスクラス。<BR>
 */
public class WEB3RuitoMarketRequestSubmitServiceImpl
    implements RuitoMarketRequestSenderService
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3RuitoMarketRequestSubmitServiceImpl.class);

    /**
     * 市場送信を行う場合は true を、そうでない場合は false を設定する。<BR>
     */
    private boolean isMarketSubmit;

    /**
     * （sendの実装）<BR>
     * <BR>
     * 指定の取消注文市場リクエストメッセージを市場へ送信する。<BR>
     * <BR>
     * １）　@注文取得<BR>
     * 　@－取消注文市場リクエストメッセージ.getOrderId() にて注文IDを<BR>
     *          取得する。<BR>
     * 　@－拡張累投注文マネージャ.getOrder()をコールして、<BR>
     *   累投注文オブジェクトを取得する。<BR>
     * 　@　@［getOrderに渡すパラメタ］<BR>
     * 　@　@　@注文ID： 取得した注文ID<BR>
     * 　@－取得した累投注文オブジェクト.getOrderUnits()をコールし、<BR>
     *         累投注文単位オブジェクトの配列を取得する。<BR>
     *     <BR>
     * ２）　@取消注文市場リクエストメッセージ送信<BR>
     * ２－１）　@累投注文単位.getDataSourceObject().getFundType()の<BR>
     *            戻り値が<BR>
     * RuitoTypeEnum.MRFで、引数.is市場未送信の値がfalseの場合<BR>
     * 　@取消注文市場リクエストメッセージ送信<BR>
     * （MRF解約：市場送信あり）()を コールする。<BR>
     * 　@［取消注文市場リクエストメッセージ送信<BR>
     * （MRF解約：市場送信あり）に渡すパラメタ］<BR>
     * 　@　@累投注文単位： <BR>
     *     取得した累投注文単位オブジェクトの配列[0]<BR>
     * 　@　@補助口座： <BR>
     *    取消注文市場リクエストメッセージ.getSubAccount()の戻り値<BR>
     * 　@（シーケンス図「（累投市場リクエスト）取消注文送信<BR>
     *        （MRF解約：市場送信あり）」を参照）<BR>
     * <BR>
     * ２－２）　@累投注文単位.getDataSourceObject().getFundType()の<BR>
     *           戻り値が<BR>
     * RuitoTypeEnum.中期国債ファ@ンドまたはRuitoTypeEnum.MMFで、<BR>
     * かつ累投注文単位.getOrderType()の戻り値が<BR>
     * OrderTypeEnum.累投買注文で<BR>
     * 、かつ引数.is市場未送信の値がfalseの場合<BR>
     * 　@取消注文市場リクエストメッセージ送信（買付：市場送信あり）()を<BR>
     *         コールする。<BR>
     * 　@［取消注文市場リクエストメッセージ送信（買付：市場送信あり）に<BR>
     *          渡すパラメタ］<BR>
     * 　@　@累投注文単位： 取得した累投注文単位オブジェクトの配列[0]<BR>
     * 　@　@補助口座： 取消注文市場リクエストメッセージ.getSubAccount()の<BR>
     *          戻り値<BR>
     * 　@（シーケンス図「（累投市場リクエスト）取消注文送信<BR>
     *    （買付：市場送信あり）」を参照）<BR>
     * <BR>
     * ２－３）　@累投注文単位.getDataSourceObject().getFundType()<BR>
     *            の戻り値が<BR>
     * RuitoTypeEnum.中期国債ファ@ンドまたはRuitoTypeEnum.MMFで、<BR>
     * かつ累投注文単位.getOrderType()の戻り値が<BR>
     * OrderTypeEnum.累投売注文で、<BR>
     * かつ引数.is市場未送信の値がfalseの場合<BR>
     * 取消注文市場リクエストメッセージ送信（解約：市場送信あり）()を<BR>
     * コールする。<BR>
     * ［取消注文市場リクエストメッセージ送信（解約：市場送信あり）に<BR>
     * 渡すパラメタ］<BR>
     * 　@　@累投注文単位： 取得した累投注文単位オブジェクトの配列[0]<BR>
     * 　@　@補助口座： <BR>
     *    取消注文市場リクエストメッセージ.getSubAccount()の戻り値<BR>
     * 　@（シーケンス図「（累投市場リクエスト）取消注文送信<BR>
     *    （解約：市場送信あり）」 を参照）<BR>
     * <BR>
     * ２－４）　@累投注文単位.getDataSourceObject().getFundType()<BR>
     *            の戻り値が<BR>
     * RuitoTypeEnum.MRFで、引数.is市場未送信の値がtrueの場合<BR>
     * 　@取消注文市場リクエストメッセージ送信<BR>
     *  （MRF解約：市場送信なし）()をコールする。<BR>
     * 　@［取消注文市場リクエストメッセージ送信<BR>
     *    （MRF解約：市場送信なし）に渡すパラメタ］<BR>
     * 　@　@累投注文単位： 取得した累投注文単位オブジェクトの配列[0]<BR>
     * 　@　@補助口座： <BR>
     *    取消注文市場リクエストメッセージ.getSubAccount()の戻り値<BR>
     * 　@（シーケンス図「（累投市場リクエスト）取消注文送信<BR>
     *    （MRF解約：市場送信なし）」を参照）<BR>
     * <BR>
     * ２－５）　@累投注文単位.getDataSourceObject().getFundType()<BR>
     *            の戻り値が<BR>
     * RuitoTypeEnum.中期国債ファ@ンドまたはRuitoTypeEnum.MMFで、<BR>
     * かつ累投注文単位.getOrderType()の戻り値が<BR>
     * OrderTypeEnum.累投買注文で、<BR>
     * かつ引数.is市場未送信の値がtrueの場合<BR>
     * 　@取消注文市場リクエストメッセージ送信（買付：市場送信なし）()<BR>
     *   をコールする。<BR>
     * 　@［取消注文市場リクエストメッセージ送信（買付：市場送信なし）に<BR>
     *     渡すパラメタ］<BR>
     * 　@　@累投注文単位： 取得した累投注文単位オブジェクトの配列[0]<BR>
     * 　@　@補助口座： <BR>
     *     取消注文市場リクエストメッセージ.getSubAccount()の戻り値<BR>
     * 　@（シーケンス図「（累投市場リクエスト）取消注文送信<BR>
     *    （買付：市場送信なし）」を参照）<BR>
     * <BR>
     * ２－６）　@累投注文単位.getDataSourceObject().getFundType()<BR>
     *            の戻り値が<BR>
     * RuitoTypeEnum.中期国債ファ@ンドまたはRuitoTypeEnum.MMFで、<BR>
     * かつ累投注文単位.getOrderType()の戻り値が<BR>
     *                     OrderTypeEnum.累投売注文で、<BR>
     * かつ引数.is市場未送信の値がtrueの場合<BR>
     * 　@取消注文市場リクエストメッセージ送信<BR>
     *   （解約：市場送信なし）()をコールする。<BR>
     * 　@［取消注文市場リクエストメッセージ送信<BR>
     *    （解約：市場送信なし）に渡すパラメタ］<BR>
     * 　@　@累投注文単位： 取得した累投注文単位オブジェクトの配列[0]<BR>
     * 　@　@補助口座： <BR>
     *     取消注文市場リクエストメッセージ.getSubAccount()の戻り値<BR>
     * 　@（シーケンス図「（累投市場リクエスト）取消注文送信<BR>
     *    （解約：市場送信なし）」 を参照）<BR>
     * <BR>
     * ２－７）　@新規注文市場リクエストメッセージ送信の<BR>
     *                         各メソッドが正常終了した場合、<BR>
     * 　@DefaultMarketRequestSendResult.newSuccessResultInstance()<BR>
     *          の戻り値を返す。<BR>
     * 　@［newSuccessResultInstanceに渡すパラメタ］<BR>
     * 　@　@メッセージトークンID： 0<BR>
     * <BR>
     * ２－８）　@新規注文市場リクエストメッセージ送信の各メソッドが<BR>
     *              例外をスローした場合<BR>
     * 　@－ProcessingResult.newFailedResultInstance()をコールして<BR>
     *         ProcessingResultオブジェクトを生成する。<BR>
     * 　@　@［newFailedResultInstanceに渡すパラメタ］<BR>
     * 　@　@　@エラー情報： 例外オブジェクト.getErrorInfo()の戻り値<BR>
     * 　@－DefaultMarketRequestSendResult.newFailedResultInstance()<BR>
     *            の戻り値を返す。<BR>
     * 　@　@［newFailedResultInstanceに渡すパラメタ］<BR>
     * 　@　@　@オペレーション結果： 生成したProcessingResultオブジェクト<BR>
     * @@param l_request - 取消注文市場リクエストメッセージ<BR>
     * @@param isMarketNotSubmit -
     * 注文が市場に未送信で送信サービスにローカル取消をするよう通知する場合は<BR>
     * true を指定する。<BR>
     * falseに指定の場合は市場へ取消メッセージを送信する。<BR>
     *
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSendResult
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.TooLateException
     * @@roseuid 406D22920166
     */
    public MarketRequestSendResult send(
        CancelOrderMarketRequestMessage l_request,
        boolean isMarketNotSubmit)
        throws TooLateException
    {
        final String STR_METHOD_NAME =
            "send(CancelOrderMarketRequestMessage l_request,"
                + "boolean isMarketNotSubmit)";
        log.entering(STR_METHOD_NAME);

        try
        {
            if (l_request == null)
            {
                log.error("__ParameterError__");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            //取消注文市場リクエストメッセージ.getOrderId() にて注文IDを取得する
            long l_lngOrderId = l_request.getOrderId();
            log.debug(
                "[cancelOrderMarketRequestMessageSubmit] l_lngOrderId = "
                    + l_lngOrderId);

            WEB3RuitoOrderManager l_ruitoOrderManager = null;
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            l_ruitoOrderManager =
                (WEB3RuitoOrderManager) l_finApp
                    .getTradingModule(ProductTypeEnum.RUITO)
                    .getOrderManager();
            RuitoOrder l_ruitoOrder = null;
            try
            {
                //累投注文オブジェクト
                l_ruitoOrder =
                    (RuitoOrder) l_ruitoOrderManager.getOrder(l_lngOrderId);
            }
            catch (NotFoundException l_ex)
            {
                log.error("__NotFoundException__", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            //累投注文単位オブジェクトの配列
            OrderUnit[] l_orderUnits = l_ruitoOrder.getOrderUnits();
            //取消注文市場リクエストメッセージ送信
            RuitoOrderUnitRow l_ruitoOrderUnitRow =
                (RuitoOrderUnitRow) l_orderUnits[0].getDataSourceObject();

            RuitoOrderUnit l_ruitoOrderUnit = (RuitoOrderUnit) l_orderUnits[0];
            int l_intRuitoType = l_ruitoOrderUnitRow.getRuitoType().intValue();

            log.debug("l_intRuitoType = " + l_intRuitoType);
            log.debug("l_intOrderType = " + l_ruitoOrderUnit.getOrderType());

            try
            {
                if (l_intRuitoType == RuitoTypeEnum.MRF.intValue()
                    && !isMarketNotSubmit)
                {
                    cancelOrderMarketRequestMessageSubmitMRFSellHasMarketSubmit(
                        l_ruitoOrderUnit,
                        l_request.getSubAccount());
                }
                else if (
                    (l_intRuitoType == RuitoTypeEnum.MMF.intValue()
                        || l_intRuitoType
                            == RuitoTypeEnum.CHUUKOKU_FUND.intValue())
                        && OrderTypeEnum.RUITO_BUY.equals(
                            l_ruitoOrderUnit.getOrderType())
                        && !isMarketNotSubmit)
                {
                    cancelOrderMarketRequestMessageSubmitBuyHasMarketSubmit(
                        l_ruitoOrderUnit,
                        l_request.getSubAccount());
                }
                else if (
                    (l_intRuitoType == RuitoTypeEnum.MMF.intValue()
                        || l_intRuitoType
                            == RuitoTypeEnum.CHUUKOKU_FUND.intValue())
                        && OrderTypeEnum.RUITO_SELL.equals(
                            l_ruitoOrderUnit.getOrderType())
                        && !isMarketNotSubmit)
                {
                    cancelOrderMarketRequestMessageSubmitSellHasMarketSubmit(
                        l_ruitoOrderUnit,
                        l_request.getSubAccount());
                }
                else if (
                    l_intRuitoType == RuitoTypeEnum.MRF.intValue()
                        && isMarketNotSubmit)
                {
                    cancelOrderMarketRequestMessageSubmitMRFSellNotMarketSubmit(
                        l_ruitoOrderUnit,
                        l_request.getSubAccount());
                }
                else if (
                    (l_intRuitoType == RuitoTypeEnum.MMF.intValue()
                        || l_intRuitoType
                            == RuitoTypeEnum.CHUUKOKU_FUND.intValue())
                        && OrderTypeEnum.RUITO_BUY.equals(
                            l_ruitoOrderUnit.getOrderType())
                        && isMarketNotSubmit)
                {
                    cancelOrderMarketRequestMessageSubmitBuyNotMarketSubmit(
                        l_ruitoOrderUnit,
                        l_request.getSubAccount());
                }
                else if (
                    (l_intRuitoType == RuitoTypeEnum.MMF.intValue()
                        || l_intRuitoType
                            == RuitoTypeEnum.CHUUKOKU_FUND.intValue())
                        && OrderTypeEnum.RUITO_SELL.equals(
                            l_ruitoOrderUnit.getOrderType())
                        && isMarketNotSubmit)
                {
                    cancelOrderMarketRequestMessageSubmitSellNotMarketSubmit(
                        l_ruitoOrderUnit,
                        l_request.getSubAccount());
                }
            }
            catch (WEB3BaseException l_ex)
            {
                ProcessingResult l_processingResult =
                    ProcessingResult.newFailedResultInstance(
                        l_ex.getErrorInfo());

                log.exiting(STR_METHOD_NAME);
                return DefaultMarketRequestSendResult.newFailedResultInstance(
                    l_processingResult);
            }
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new TooLateException(l_ex.getMessage());
        }

        log.exiting(STR_METHOD_NAME);
        return DefaultMarketRequestSendResult.newSuccessResultInstance(0);
    }

    /**
     * （send(RuitoNewOrderMarketRequestMessage)の実装）<BR>
     * <BR>
     * 指定の累投注文マーケットリクエストメッセージを市場へ送信する。<BR>
     * <BR>
     * １）　@this.is市場送信()の値が false の場合、<BR>
     * DefaultMarketRequestSendResult.newSuccessResultInstance(0)<BR>
     * の戻り値を返す。<BR>
     * <BR>
     * ２）　@注文取得<BR>
     * 　@－累投新規注文市場リクエストメッセージ.getOrderUnitId() にて<BR>
     *          注文単位IDを取得する。<BR>
     * 　@－拡張累投注文マネージャ.getOrderUnit()をコールして、<BR>
     *       累投注文単位オブジェクトを取得する。<BR>
     * 　@　@［getOrderUnitに渡すパラメタ］<BR>
     * 　@　@　@注文単位ID： 取得した注文単位ID<BR>
     * <BR>
     * ３）　@新規注文市場リクエストメッセージ送信<BR>
     * ３－１）　@取得した累投注文単位.getDataSourceObject().<BR>
     *            get注文経路区分()の戻り値が<BR>
     * 　@WEB3OrderRootDivDef.HOSTと等しくない場合<BR>
     *   （＝WebⅢ入力注文）<BR>
     * <BR>
     * 　@－累投注文単位.getDataSourceObject().getFundType()<BR>
     *      の戻り値が<BR>
     * 　@　@RuitoTypeEnum.MRFの場合<BR>
     * 　@　@　@新規注文市場リクエストメッセージ送信（MRF解約）()<BR>
     *       をコールする。<BR>
     * 　@　@　@［新規注文市場リクエストメッセージ送信<BR>
     *         （MRF解約）に渡すパラメタ］<BR>
     * 　@　@　@　@累投注文単位： 取得した累投注文単位オブジェクト<BR>
     * 　@　@　@　@補助口座： <BR>
     *          累投新規注文市場リクエストメッセージ.getSubAccount()<BR>
     *           の戻り値<BR>
     * 　@　@　@（シーケンス図「（累投市場リクエスト）新規注文送信<BR>
     *         （MRF解約）」を参照）<BR>
     * <BR>
     * 　@－累投注文単位..getDataSourceObject().getFundType()<BR>
     *      の戻り値が<BR>
     *         RuitoTypeEnum.中期国債ファ@ンド<BR>
     *     またはRuitoTypeEnum.MMFで、<BR>
     *    かつ累投注文単位.getOrderType()の戻り値が<BR>
     * OrderTypeEnum.累投買注文の場合<BR>
     * 　@　@　@新規注文市場リクエストメッセージ送信（買付）()をコールする。<BR>
     * 　@　@　@［新規注文市場リクエストメッセージ送信（買付）に渡すパラメタ］<BR>
     * 　@　@　@　@累投注文単位： 取得した累投注文単位オブジェクト<BR>
     * 　@　@　@　@補助口座： <BR>
     *         累投新規注文市場リクエストメッセージ.getSubAccount()<BR>
     *         の戻り値<BR>
     * 　@　@　@（シーケンス図「（累投市場リクエスト）新規注文送信<BR>
     *         （買付）」を参照）<BR>
     * <BR>
     * 　@－累投注文単位..getDataSourceObject().getFundType()<BR>
     *       の戻り値がRuitoTypeEnum.中期国債ファ@ンド<BR>
     *      またはRuitoTypeEnum.MMFで、<BR>
     *      かつ累投注文単位.getOrderType()の戻り値が<BR>
     *          OrderTypeEnum.累投売注文の場合<BR>
     * 　@　@　@新規注文市場リクエストメッセージ送信（解約）()をコールする。<BR>
     * 　@　@　@［新規注文市場リクエストメッセージ送信（解約）に渡すパラメタ］<BR>
     * 　@　@　@　@累投注文単位： 取得した累投注文単位オブジェクト<BR>
     * 　@　@　@　@補助口座： <BR>
     *         累投新規注文市場リクエストメッセージ.getSubAccount()<BR>
     *         の戻り値<BR>
     * 　@　@　@（シーケンス図「（累投市場リクエスト）新規注文送信<BR>
     *        （解約）」を参照）<BR>
     * <BR>
     * ３－２）　@取得した累投注文単位.getDataSourceObject().<BR>
     *             get注文経路区分()の戻り値が<BR>
     * 　@WEB3OrderRootDivDef.HOSTと等しい場合（＝SONAR入力注文）<BR>
     * 　@　@新規注文市場リクエストメッセージ送信（売買注文通知）()<BR>
     *      をコールする。<BR>
     * 　@　@［新規注文市場リクエストメッセージ送信<BR>
     *       （売買注文通知）に渡すパラメタ］<BR>
     * 　@　@　@累投注文単位： 取得した累投注文単位オブジェクト<BR>
     * 　@　@　@補助口座： <BR>
     *        累投新規注文市場リクエストメッセージ.getSubAccount()<BR>
     *        の戻り値<BR>
     * 　@　@（シーケンス図「（累投市場ﾘｸｴｽﾄ）新規注文送信<BR>
     *      （売買注文通知）」を参照）<BR>
     * <BR>
     * ３－３）　@新規注文市場リクエストメッセージ送信の各メソッドが<BR>
     *           正常終了した場合、<BR>
     * 　@DefaultMarketRequestSendResult.newSuccessResultInstance()の<BR>
     *              戻り値を返す。<BR>
     * 　@［newSuccessResultInstanceに渡すパラメタ］<BR>
     * 　@　@メッセージトークンID： 0<BR>
     * <BR>
     * ３－４）　@新規注文市場リクエストメッセージ送信の各メソッドが<BR>
     *          例外をスローした場合<BR>
     * 　@－ProcessingResult.newFailedResultInstance()をコールして<BR>
     *          ProcessingResultオブジェクトを生成する。<BR>
     * 　@　@［newFailedResultInstanceに渡すパラメタ］<BR>
     * 　@　@　@エラー情報： 例外オブジェクト.getErrorInfo()の戻り値<BR>
     * 　@－DefaultMarketRequestSendResult.newFailedResultInstance()<BR>
     *               の戻り値を返す。<BR>
     * 　@　@［newFailedResultInstanceに渡すパラメタ］<BR>
     * 　@　@　@オペレーション結果： 生成したProcessingResultオブジェクト<BR>
     * @@param l_request - 累投新規注文市場リクエストメッセージ<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSendResult
     * @@roseuid 406D22920176
     */
    public MarketRequestSendResult send(RuitoNewOrderMarketRequestMessage l_request)
    {
        final String STR_METHOD_NAME =
            "newOrderMarketRequestMessageSumbit(RuitoNewOrderMarketRequestMessage l_request)";

        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.error("__ParameterError__");

            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            log.exiting(STR_METHOD_NAME);
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }

        if (!isMarketSubmit())
        {
            log.debug("isMarketSubmit == false");
            log.exiting(STR_METHOD_NAME);
            return DefaultMarketRequestSendResult.newSuccessResultInstance(0);
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3RuitoOrderManager l_web3RuitoOrderManager =
            (WEB3RuitoOrderManager) l_finApp
                .getTradingModule(ProductTypeEnum.RUITO)
                .getOrderManager();

        RuitoOrderUnit l_ruitoOrderUnit = null;
        try
        {
            l_ruitoOrderUnit =
                (RuitoOrderUnit) l_web3RuitoOrderManager.getOrderUnit(
                    l_request.getOrderUnitId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006);

            log.exiting(STR_METHOD_NAME);
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }

        RuitoOrderUnitRow l_ruitoOrderUnitRow =
            (RuitoOrderUnitRow) l_ruitoOrderUnit.getDataSourceObject();

        log.debug("orderRootDiv = " + l_ruitoOrderUnitRow.getOrderRootDiv());

        try
        {
            if (!WEB3OrderRootDivDef
                .HOST
                .equals(l_ruitoOrderUnitRow.getOrderRootDiv()))
            {
                int l_intRuitoType = l_ruitoOrderUnitRow.getRuitoType().intValue();

                log.debug("ruitoType = " + l_intRuitoType);
                log.debug("orderType = " + l_ruitoOrderUnitRow.getOrderType());

                if (l_intRuitoType == RuitoTypeEnum.MRF.intValue())
                {
                    newOrderMarketRequestMessageSubmitMRFSell(
                        l_ruitoOrderUnit,
                        l_request.getSubAccount());
                }
                else if (
                    (l_intRuitoType == RuitoTypeEnum.MMF.intValue()
                        || l_intRuitoType
                            == RuitoTypeEnum.CHUUKOKU_FUND.intValue())
                        && OrderTypeEnum.RUITO_BUY.equals(
                            l_ruitoOrderUnitRow.getOrderType()))
                {
                    newOrderMarketRequestMessageSubmitBuy(
                        l_ruitoOrderUnit,
                        l_request.getSubAccount());
                }
                else if (
                    (l_intRuitoType == RuitoTypeEnum.MMF.intValue()
                        || l_intRuitoType
                            == RuitoTypeEnum.CHUUKOKU_FUND.intValue())
                        && OrderTypeEnum.RUITO_SELL.equals(
                            l_ruitoOrderUnitRow.getOrderType()))
                {
                    newOrderMarketRequestMessageSubmitSell(
                        l_ruitoOrderUnit,
                        l_request.getSubAccount());
                }
            }
            else
            {
                newOrderMarketRequestMessageSubmitTradeOrderNotify(l_ruitoOrderUnit);
            }
        }
        catch (WEB3BaseException l_ex)
        {
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }

        log.exiting(STR_METHOD_NAME);
        return DefaultMarketRequestSendResult.newSuccessResultInstance(0);

    }

    /**
     * キューテーブルへの書き込み、市場への送信を行うか行わないかを<BR>
     * 指定する。<BR>
     * 行う場合は true を、行わない場合は false を指定する。<BR>
     * @@param isMarketSubmit - キューテーブルへの書き込み、市場への送信を行う場合は
     * true を、<BR>
     * そうでない場合は false を指定する。<BR>
     *
     * @@return boolean
     * @@roseuid 406D320100E9
     */
    public boolean setMarketSubmit(boolean l_isMarketSubmit)
    {
        isMarketSubmit = l_isMarketSubmit;
        log.debug("l_isMarketSubmit = " + l_isMarketSubmit);
        return isMarketSubmit;
    }

    /**
     * キューテーブルへの書き込みと市場への送信を行う場合は true を、<BR>
     * そうでない場合は false を返す。<BR>
     * @@return boolean
     * @@roseuid 406D33E1008B
     */
    public boolean isMarketSubmit()
    {
        log.debug("isMarketSubmit = " + isMarketSubmit);
        return isMarketSubmit;
    }
    /**
     * 新規注文市場リクエストメッセージ送信（MRF解約）<BR>
     * <BR>
     * MRF解約注文の市場リクエストメッセージを送信する。<BR>
     * <BR>
     * １）　@キューデータインサート<BR>
     * 　@引数.累投注文単位オブジェクトの内容をもとに、<BR>
     *   MRF注文キューテーブルに注文データを挿入する。<BR>
     * 　@（DB更新仕様「MRF解約_MRF注文キューテーブル.xls」参照）<BR>
     * <BR>
     * ２）　@トリガ発行<BR>
     * 　@－トリガを発行するかのチェック<BR>
     * 　@　@取引時間管理.isトリガ発行()をコールし、<BR>
     *      戻り値がtrueであればトリガを発行する。<BR>
     *      戻り値がfalseであれば以降の処理を行わない。<BR>
     * 　@　@　@［isトリガ発行に渡すパラメタ］<BR>
     * 　@　@　@　@発注条件：<BR>
     * 　@　@　@　@　@”0 ： DEFAULT”<BR>
     * <BR>
     * 　@－WEB3MQMessageSpecの生成<BR>
     * 　@　@WEB3MQMessageSpecを生成する。<BR>
     * 　@　@[コンストラクタに渡すパラメタ]<BR><BR>
     * 　@　@ 証券会社コード： 引数.補助口座.getInstitution().getInstitutionCode()<BR>
     *        の戻り値<BR>
     * 　@　@　@データコード： ”GI831”<BR>
     * <BR>
     * 　@－トリガを発行する。<BR>
     * 　@　@WEB3MQGatewayServiceImpl.send()をコールし、<BR>
     *      トリガを発行する。<BR>
     * 　@　@［sendに渡すパラメタ］<BR>
     * 　@　@　@MQに送信するメッセージのスペック： 生成した<BR>
     *           WEB3MQMessageSpecオブジェクト<BR>
     * @@param l_ruitoOrderUnit - 累投注文単位<BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 407C98E1022B
     */
    public void newOrderMarketRequestMessageSubmitMRFSell(
        RuitoOrderUnit l_ruitoOrderUnit,
        SubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "newOrderMarketRequestMessageSubmitMRFSell(RuitoOrderUnit l_ruitoOrderUnit,"
                + " SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_ruitoOrderUnit == null || l_subAccount == null)
        {
            log.error("__ParameterError__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        HostMrfOrderParams l_hostMrfOrderParams = new HostMrfOrderParams();

        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        String l_strAccountCode = null;
        String l_strOrderRequestNumber = null;
        String l_strTraderCode = null;
        String l_strPoductCode = null;
        String l_strReturnMethod = null;        
        long l_lngSellOrderAmount = 0;
        Timestamp l_tsReceivedDateTime = null;

        RuitoOrderUnitRow l_ruitoOderUnitRow =
            (RuitoOrderUnitRow) l_ruitoOrderUnit.getDataSourceObject();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.RUITO);
        WEB3RuitoProductManager l_ruitoProductManager =
            (WEB3RuitoProductManager) l_tradingModule.getProductManager();

        try
        {
            //部店を取得する
            Branch l_banch =
                l_accountManager.getBranch(l_ruitoOderUnitRow.getBranchId());
            l_strBranchCode = l_banch.getBranchCode();

            log.debug("l_strBranchCode = " + l_strBranchCode);

            //証券会社コードを取得する
            l_strInstitutionCode =
                l_banch.getInstitution().getInstitutionCode();

            log.debug("l_strInstitutionCode = " + l_strInstitutionCode);

            //顧客コードを取得する
            l_strAccountCode =
                l_accountManager
                    .getMainAccount(l_ruitoOderUnitRow.getAccountId())
                    .getAccountCode();

            log.debug("l_strAccountCode = " + l_strAccountCode);

            //扱者コードを取得する
            l_strTraderCode = l_ruitoOderUnitRow.getSonarTraderCode();

            log.debug("l_strTraderCode = " + l_strTraderCode);

            //識別コードを取得する
            l_strOrderRequestNumber =
                l_ruitoOderUnitRow.getOrderRequestNumber();

            log.debug("l_strOrderRequestNumber = " + l_strOrderRequestNumber);

            //銘柄コードを取得する
            long productId = l_ruitoOderUnitRow.getProductId();
            log.debug("productId = " + productId);
            RuitoProduct l_product =
                (RuitoProduct) l_ruitoProductManager.getProduct(productId);

            RuitoProductRow l_ruitoProductRow =
                (RuitoProductRow) l_product.getDataSourceObject();
            l_strPoductCode = l_ruitoProductRow.getMrfFundCode();
            log.debug("l_strPoductCode = " + l_strPoductCode);

            //売付金額
            l_lngSellOrderAmount = (long)l_ruitoOderUnitRow.getQuantity();
            log.debug("l_lngSellOrderAmount = " + l_lngSellOrderAmount);

            //返還方法@を取得する
            l_strReturnMethod = l_ruitoOderUnitRow.getReturnMethod();
            log.debug("l_strReturnMethod = " + l_strReturnMethod);

            //受注日時
            l_tsReceivedDateTime = l_ruitoOderUnitRow.getReceivedDateTime();
            log.debug("l_tsReceivedDateTime = " + l_tsReceivedDateTime);

            //データコード
            l_hostMrfOrderParams.setRequestCode(
                WEB3HostRequestCodeDef.RUITO_REQUEST_MESSAGE_SEND_MRF_SELL);

            //証券会社コード
            l_hostMrfOrderParams.setInstitutionCode(l_strInstitutionCode);
            //部店コード
            l_hostMrfOrderParams.setBranchCode(l_strBranchCode);
            //顧客コード
            l_hostMrfOrderParams.setAccountCode(l_strAccountCode);
            //扱者コード
            l_hostMrfOrderParams.setTraderCode(l_strTraderCode);
            //識別コード
            l_hostMrfOrderParams.setOrderRequestNumber(l_strOrderRequestNumber);
            //銘柄
            l_hostMrfOrderParams.setMrfFundCode(l_strPoductCode);
            //返還区分
            l_hostMrfOrderParams.setReturnDiv(" ");
            //課税区分
            l_hostMrfOrderParams.setTaxDiv(" ");
            //売付金額
            l_hostMrfOrderParams.setSellOrderAmount(l_lngSellOrderAmount);
            //返還方法@
            l_hostMrfOrderParams.setReturnMethod(l_strReturnMethod);
            //受注日時
            l_hostMrfOrderParams.setOrderDate(l_tsReceivedDateTime);
            //処理区分
            l_hostMrfOrderParams.setStatus(WEB3StatusDef.NOT_DEAL);

            Processors.getDefaultProcessor().doInsertQuery(
                l_hostMrfOrderParams);
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundExcetion__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (WEB3GentradeTradingTimeManagement
            .isSubmitMarketTrigger(WEB3OrderingConditionDef.DEFAULT))
        {
            WEB3MQMessageSpec l_web3MQMessageSpec =
                new WEB3MQMessageSpec(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    WEB3HostRequestCodeDef.RUITO_REQUEST_MESSAGE_SEND_MRF_SELL + "T");
            log.debug("InstitutionCode =" + l_subAccount.getInstitution().getInstitutionCode());
            log.debug("DataCode =" + WEB3HostRequestCodeDef.RUITO_REQUEST_MESSAGE_SEND_MRF_SELL + "T");
            WEB3MQGatewayService l_web3MQGatewayService =
                (WEB3MQGatewayService) Services.getService(
                    WEB3MQGatewayService.class);

            l_web3MQGatewayService.send(l_web3MQMessageSpec);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 新規注文市場リクエストメッセージ送信（買付）<BR>
     * <BR>
     * 中期国債ファ@ンド・MMF買付注文の市場リクエストメッセージを<BR>
     * 送信する。<BR>
     * <BR>
     * １）　@キューデータインサート<BR>
     * 　@引数.累投注文単位オブジェクトの内容をもとに、<BR>
     *    累投注文キューテーブル<BR>
     * 　@に注文データを挿入する。<BR>
     * 　@（DB更新仕様「累積投資買付_累投注文キューテーブル.xls」参照）<BR>
     * <BR>
     * ２）　@トリガ発行<BR>
     * 　@－トリガを発行するかのチェック<BR>
     * 　@　@取引時間管理.isトリガ発行()をコールし、<BR>
     *     戻り値がtrueであればトリガを<BR>
     * 　@　@発行する。戻り値がfalseであれば以降の処理を行わない。<BR>
     * 　@　@　@［isトリガ発行に渡すパラメタ］<BR>
     * 　@　@　@　@発注条件：<BR>
     * 　@　@　@　@　@”0 ： DEFAULT”<BR>
     * <BR>
     * 　@－WEB3MQMessageSpecの生成<BR>
     * 　@　@WEB3MQMessageSpecを生成する。<BR>
     * 　@　@[コンストラクタに渡すパラメタ]<BR>
     * 　@　@ 証券会社コード： 引数.補助口座.getInstitution().getInstitutionCode()<BR>
     *           の戻り値<BR>
     * 　@　@　@データコード： ”DI801”<BR>
     * <BR>
     * 　@－トリガを発行する。<BR>
     * 　@　@WEB3MQGatewayServiceImpl.send()をコールし、<BR>
     *     トリガを発行する。<BR>
     * 　@　@［sendに渡すパラメタ］<BR>
     * 　@　@　@MQに送信するメッセージのスペック： <BR>
     *        生成したWEB3MQMessageSpecオブジェクト<BR>
     * @@param l_ruitoOrderUnit - 累投注文単位<BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 407C98F502E4
     */
    public void newOrderMarketRequestMessageSubmitBuy(
        RuitoOrderUnit l_ruitoOrderUnit,
        SubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "newOrderMarketRequestMessageSubmitBuy(RuitoOrderUnit l_ruitoOrderUnit,"
                + " SubAccount l_subAccount)";

        log.entering(STR_METHOD_NAME);
        if (l_ruitoOrderUnit == null || l_subAccount == null)
        {
            log.error("__ParameterError__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        HostRuitoOrderParams l_hostRuitoOrderParams =
            new HostRuitoOrderParams();

        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        String l_strAccountCode = null;
        String l_strOrderRequestNumber = null;
        String l_strTraderCode = null;
        String l_strReturnMethod = null;
        String l_strTaxType = null;
        String l_strCourse = null;
        String l_strPlan = null;
        //int l_intPayAmount = 0;
        long l_lngPayAmount = 0;
        Timestamp l_tsReceivedDateTime = null;

        RuitoOrderUnitRow l_ruitoOderUnitRow =
            (RuitoOrderUnitRow) l_ruitoOrderUnit.getDataSourceObject();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.RUITO);
        RuitoProductManager l_ruitoProductManager =
            (RuitoProductManager) l_tradingModule.getProductManager();

        try
        {
            //部店を取得する
            Branch l_banch =
                l_accountManager.getBranch(l_ruitoOderUnitRow.getBranchId());
            l_strBranchCode = l_banch.getBranchCode();
            log.debug("l_strBranchCode = " + l_strBranchCode);

            //証券会社コードを取得する
            l_strInstitutionCode =
                l_banch.getInstitution().getInstitutionCode();
            log.debug("l_strInstitutionCode = " + l_strInstitutionCode);

            //顧客コードを取得する
            l_strAccountCode =
                l_accountManager
                    .getMainAccount(l_ruitoOderUnitRow.getAccountId())
                    .getAccountCode();
            log.debug("l_strAccountCode = " + l_strAccountCode);

            //扱者コードを取得する
            l_strTraderCode = l_ruitoOderUnitRow.getSonarTraderCode();
            log.debug("l_strTraderCode = " + l_strTraderCode);

            //識別コードを取得する
            l_strOrderRequestNumber =
                l_ruitoOderUnitRow.getOrderRequestNumber();
            log.debug("l_strOrderRequestNumber = " + l_strOrderRequestNumber);

            //コース
            WEB3RuitoProduct l_ruitoProduct =
                (WEB3RuitoProduct) l_ruitoProductManager.getProduct(
                    l_ruitoOderUnitRow.getProductId());
            l_strCourse = l_ruitoProduct.getCourse();
            log.debug("l_strCourse = " + l_strCourse);

            //プラン
            l_strPlan = l_ruitoProduct.getPlan();
            log.debug("l_strPlan = " + l_strPlan);

            //入金額
            l_lngPayAmount = (long) l_ruitoOderUnitRow.getQuantity();
            log.debug("l_intPayAmount = " + l_lngPayAmount);

            //返還方法@を取得する
            l_strReturnMethod = l_ruitoOderUnitRow.getReturnMethod();
            log.debug("l_strReturnMethod = " + l_strReturnMethod);

            //受注日時
            l_tsReceivedDateTime = l_ruitoOderUnitRow.getReceivedDateTime();
            log.debug("l_tsReceivedDateTime = " + l_tsReceivedDateTime);

            //データコード
            l_hostRuitoOrderParams.setRequestCode(
                WEB3HostRequestCodeDef.RUITO_REQUEST_MESSAGE_SEND_BUY);
            //証券会社コード
            l_hostRuitoOrderParams.setInstitutionCode(l_strInstitutionCode);
            //部店コード
            l_hostRuitoOrderParams.setBranchCode(l_strBranchCode);
            //顧客コード
            l_hostRuitoOrderParams.setAccountCode(l_strAccountCode);
            //扱者コード
            l_hostRuitoOrderParams.setTraderCode(l_strTraderCode);
            //識別コード
            l_hostRuitoOrderParams.setOrderRequestNumber(
                l_strOrderRequestNumber);
            //コース
            l_hostRuitoOrderParams.setCourse(l_strCourse);
            //プラン
            l_hostRuitoOrderParams.setPlan(l_strPlan);
            //入金額
            l_hostRuitoOrderParams.setPayAmount(l_lngPayAmount);
            //決済区分
            l_hostRuitoOrderParams.setSettleDiv(WEB3SettleDivDef.TRANSFER);
            //入金区分
            l_hostRuitoOrderParams.setPayDiv(" ");
            //買付銘柄
            l_hostRuitoOrderParams.setProduct("  ");
            //受渡経路
            l_hostRuitoOrderParams.setTransferRoute(
                WEB3TransferRouteDef.CUSTOM_ACCOUNT);
            //課税区分
            l_hostRuitoOrderParams.setTaxDiv(" ");
            //乗換区分
            l_hostRuitoOrderParams.setConvDiv(" ");
            //相手
            l_hostRuitoOrderParams.setPartner("  ");
            //入金月日
            l_hostRuitoOrderParams.setPayDate(null);
            //受注日時
            l_hostRuitoOrderParams.setOrderDate(l_tsReceivedDateTime);
            //処理区分
            l_hostRuitoOrderParams.setStatus(WEB3StatusDef.NOT_DEAL);

            Processors.getDefaultProcessor().doInsertQuery(
                l_hostRuitoOrderParams);
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundExcetion__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (WEB3GentradeTradingTimeManagement
            .isSubmitMarketTrigger(WEB3OrderingConditionDef.DEFAULT))
        {
            WEB3MQMessageSpec l_web3MQMessageSpec =
                new WEB3MQMessageSpec(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    WEB3HostRequestCodeDef.RUITO_REQUEST_MESSAGE_SEND_BUY + "T",
					l_strCourse);
			log.debug("InstitutionCode =" + l_subAccount.getInstitution().getInstitutionCode());
			log.debug("DataCode =" + WEB3HostRequestCodeDef.RUITO_REQUEST_MESSAGE_SEND_BUY + "T");
			log.debug("UserData =" + l_strCourse);
            WEB3MQGatewayService l_web3MQGatewayService =
                (WEB3MQGatewayService) Services.getService(
                    WEB3MQGatewayService.class);

            l_web3MQGatewayService.send(l_web3MQMessageSpec);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 新規注文市場リクエストメッセージ送信（解約）<BR>
     * <BR>
     * 中期国債ファ@ンド・MMF解約注文の市場リクエストメッセージを<BR>
     * 送信する。<BR>
     * <BR>
     * １）　@キューデータインサート<BR>
     * 　@引数.累投注文単位オブジェクトの内容をもとに、<BR>
     *   累投解約キューテーブ<BR>
     * 　@に注文データを挿入する。<BR>
     * 　@（DB更新仕様「累積投資解約_累投解約キューテーブル.xls」参照）<BR>
     * <BR>
     * ２）　@トリガ発行<BR>
     * 　@－トリガを発行するかのチェック<BR>
     * 　@　@取引時間管理.isトリガ発行()をコールし、<BR>
     *      戻り値がtrueであればトリガを<BR>
     * 　@　@発行する。戻り値がfalseであれば以降の処理を行わない。<BR>
     * 　@　@　@［isトリガ発行に渡すパラメタ］<BR>
     * 　@　@　@　@発注条件：<BR>
     * 　@　@　@　@　@”0 ： DEFAULT”<BR>
     * <BR>
     * 　@－WEB3MQMessageSpecの生成<BR>
     * 　@　@WEB3MQMessageSpecを生成する。<BR>
     * 　@　@[コンストラクタに渡すパラメタ]<BR>
     * 　@　@ 証券会社コード： 引数.補助口座.getInstitution().getInstitutionCode()<BR>
     *            の戻り値<BR>
     * 　@　@　@データコード： ”DI803”<BR>
     * <BR>
     * 　@－トリガを発行する。<BR>
     * 　@　@WEB3MQGatewayServiceImpl.send()をコールし、<BR>
     *     トリガを発行する。<BR>
     * 　@　@［sendに渡すパラメタ］<BR>
     * 　@　@　@MQに送信するメッセージのスペック： <BR>
     *        生成したWEB3MQMessageSpecオブジェクト<BR>
     * @@param l_ruitoOrderUnit - 累投注文単位<BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 407C99080042
     */
    public void newOrderMarketRequestMessageSubmitSell(
        RuitoOrderUnit l_ruitoOrderUnit,
        SubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "newOrderMarketRequestMessageSubmitSell(RuitoOrderUnit l_ruitoOrderUnit,"
                + " SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_ruitoOrderUnit == null || l_subAccount == null)
        {
            log.error("__ParameterError__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        HostRuitoSellParams l_hostRuitoSellParams = new HostRuitoSellParams();

        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        String l_strAccountCode = null;
        String l_strOrderRequestNumber = null;
        String l_strTraderCode = null;
        String l_strReturnMethod = null;
        String l_strTaxType = null;
        String l_strCourse = null;
        String l_strPlan = null;
        String l_strReturnDiv = null;
        long l_lngAmount = 0;
        long l_lngQuantity = 0;
        Timestamp l_tsReceivedDateTime = null;

        RuitoOrderUnitRow l_ruitoOderUnitRow =
            (RuitoOrderUnitRow) l_ruitoOrderUnit.getDataSourceObject();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.RUITO);
        RuitoProductManager l_ruitoProductManager =
            (RuitoProductManager) l_tradingModule.getProductManager();

        try
        {
            //部店を取得する
            Branch l_banch =
                l_accountManager.getBranch(l_ruitoOderUnitRow.getBranchId());
            l_strBranchCode = l_banch.getBranchCode();
            log.debug("l_strBranchCode = " + l_strBranchCode);

            //証券会社コードを取得する
            l_strInstitutionCode =
                l_banch.getInstitution().getInstitutionCode();
            log.debug("l_strInstitutionCode = " + l_strInstitutionCode);

            //顧客コードを取得する
            l_strAccountCode =
                l_accountManager
                    .getMainAccount(l_ruitoOderUnitRow.getAccountId())
                    .getAccountCode();
            log.debug("l_ruitoOderUnitRow.getOrderId() = " + l_ruitoOderUnitRow.getOrderId());
            log.debug("l_strAccountCode = " + l_strAccountCode);

            //扱者コードを取得する
            l_strTraderCode = l_ruitoOderUnitRow.getSonarTraderCode();
            log.debug("l_strTraderCode = " + l_strTraderCode);

            //識別コードを取得する
            l_strOrderRequestNumber =
                l_ruitoOderUnitRow.getOrderRequestNumber();
            log.debug("l_strOrderRequestNumber = " + l_strOrderRequestNumber);

            //コースを取得する
            WEB3RuitoProduct l_ruitoProduct =
                (WEB3RuitoProduct) l_ruitoProductManager.getProduct(
                    l_ruitoOderUnitRow.getProductId());
            l_strCourse = l_ruitoProduct.getCourse();
            log.debug("l_strCourse = " + l_strCourse);

            //プランを取得する
            l_strPlan = l_ruitoProduct.getPlan();
            log.debug("l_strPlan = " + l_strPlan);

            //返還区分を取得する
            //累投注文単位.累投解約区分==”2：全部指定”の場合は 1：全部解約(MAXASにて'2'に変換)
            //それ以外の場合は 3：金額指定解約(一部解約)

            if (WEB3SellDivDef
                .ALL_DESIGNATE
                .equals(l_ruitoOderUnitRow.getGpSellDiv()))
            {
                l_strReturnDiv = WEB3OrderDivTypeDef.ALL_CANCEL;
            }
            else
            {
                l_strReturnDiv = WEB3OrderDivTypeDef.PARTIALLY_CANCEL;
            }
            log.debug("l_strReturnDiv = " + l_strReturnDiv);

            //手取金額を取得する
            //累投注文単位.累投解約区分が"4:口数指定"の場合累投注文単位.注文数量
            //それ以外の場合は0
            if (WEB3SellDivDef
                .MONEY_DESIGNATE
                .equals(l_ruitoOderUnitRow.getGpSellDiv()))
            {
                l_lngAmount = (long) l_ruitoOderUnitRow.getQuantity();
            }
            else
            {
                l_lngAmount = 0;
            }
            log.debug("l_lngAmount = " + l_lngAmount);

            //数量を取得する
            //累投注文単位.累投解約区分が"3:金額指定"の場合累投注文単位.注文数量
            //それ以外の場合は0
            if (WEB3SellDivDef.COUNT_DESIGNATE.equals(
                l_ruitoOderUnitRow.getGpSellDiv()))
            {
                l_lngQuantity = (long) l_ruitoOderUnitRow.getQuantity();
            }
            else
            {
                l_lngQuantity = 0;
            }
            log.debug("l_lngQuantity = " + l_lngQuantity);

            //受注日時を取得する
            l_tsReceivedDateTime = l_ruitoOderUnitRow.getReceivedDateTime();
            log.debug("l_tsReceivedDateTime = " + l_tsReceivedDateTime);

            //データコード
            l_hostRuitoSellParams.setRequestCode(
                WEB3HostRequestCodeDef.RUITO_REQUEST_MESSAGE_SEND_SELL);
            //証券会社コード
            l_hostRuitoSellParams.setInstitutionCode(l_strInstitutionCode);
            //部店コード
            l_hostRuitoSellParams.setBranchCode(l_strBranchCode);
            //顧客コード
            l_hostRuitoSellParams.setAccountCode(l_strAccountCode);
            //扱者コード
            l_hostRuitoSellParams.setTraderCode(l_strTraderCode);
            //識別コード
            l_hostRuitoSellParams.setOrderRequestNumber(
                l_strOrderRequestNumber);
            //コース
            l_hostRuitoSellParams.setCourse(l_strCourse);
            //プラン
            l_hostRuitoSellParams.setPlan(l_strPlan);
            //融資区分
            l_hostRuitoSellParams.setLoanDiv(" ");
            //銘柄・回号
            l_hostRuitoSellParams.setProductIssueCode("         ");
            //課税区分
            l_hostRuitoSellParams.setTaxDiv(" ");
            //返還区分
            l_hostRuitoSellParams.setReturnDiv(l_strReturnDiv);
            //手取金額
            l_hostRuitoSellParams.setAmount(l_lngAmount);
            //数量
            l_hostRuitoSellParams.setQuantity(l_lngQuantity);
            //担保売却
            l_hostRuitoSellParams.setMortgageSell(" ");
            //受渡日
            l_hostRuitoSellParams.setDeliveryDate(null);
            //受注日時
            l_hostRuitoSellParams.setOrderDate(l_tsReceivedDateTime);
            //処理区分
            l_hostRuitoSellParams.setStatus(WEB3StatusDef.NOT_DEAL);

            Processors.getDefaultProcessor().doInsertQuery(
                l_hostRuitoSellParams);
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundExcetion__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (WEB3GentradeTradingTimeManagement
            .isSubmitMarketTrigger(WEB3OrderingConditionDef.DEFAULT))
        {
            WEB3MQMessageSpec l_web3MQMessageSpec =
                new WEB3MQMessageSpec(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    WEB3HostRequestCodeDef.RUITO_REQUEST_MESSAGE_SEND_SELL + "T",
					l_strCourse);
			log.debug("InstitutionCode =" + l_subAccount.getInstitution().getInstitutionCode());
			log.debug("DataCode =" + WEB3HostRequestCodeDef.RUITO_REQUEST_MESSAGE_SEND_SELL + "T");
			log.debug("UserData =" + l_strCourse);
            WEB3MQGatewayService l_web3MQGatewayService =
                (WEB3MQGatewayService) Services.getService(
                    WEB3MQGatewayService.class);

            l_web3MQGatewayService.send(l_web3MQMessageSpec);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 新規注文市場リクエストメッセージ送信（売買注文通知）<BR>
     * <BR>
     * MRF解約注文の市場リクエストメッセージを送信する。<BR>
     * <BR>
     * １）　@累投市場応答コールバックサービスオブジェクトの取得<BR>
     * 　@RuitoMarketResponseReceiverCallbackService( )<BR>
     * 　@をコールし、累投市場応答コールバックサービスオブジェクト<BR>
     *   を取得する。<BR>
     * <BR>
     * ２）　@DefaultNewOrderAcceptedMarketResponseMessage<BR>
     * 　@オブジェクトを生成する。<BR>
     * 　@［コンストラクタに渡すパラメタ］<BR>
     * 　@　@注文ID： 引数.累投注文内容.getOrderId()の戻り値<BR>
     * <BR>
     * ３）　@累投受付確定インタセプタオブジェクトを生成する。<BR>
     * <BR>
     * ４）　@累投受付確定インタセプタ.set注文エラー理由コード()をコールし、<BR>
     * 注文エラーコードの<BR>
     * 　@設定を行う。<BR>
     * 　@[set注文エラー理由コードに渡すパラメタ]<BR>
     * 　@　@注文エラー理由コード： null<BR>
     * <BR>
     * ５）　@拡張累投注文マネージャ<BR>
     * .setThreadLocalPersistenceEventInterceptor()<BR>
     * をコールし、インタセプタの設定を行う。<BR>
     * 　@[setThreadLocalPersistenceEventInterceptorに渡すパラメタ]<BR>
     * 　@　@累投受付確定インタセプタ： 生成した累投受付確定インタセプタ<BR>
     * <BR>
     * ６）　@RuitoMarketResponseReceiverCallbackService.process()<BR>
     *       をコールする。<BR>
     * 　@［processに渡すパラメタ］<BR>
     * 　@　@新規注文受付済市場応答メッセージ： <BR>
     * 　@　@　@生成した<BR>
     * DefaultNewOrderAcceptedMarketResponseMessage<BR>
     *            オブジェクト<BR>
     * @@param l_ruitoOrderUnit - 累投注文単位<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 407C991A015A
     */
    public void newOrderMarketRequestMessageSubmitTradeOrderNotify(RuitoOrderUnit l_ruitoOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "newOrderMarketRequestMessageSubmitTradeOrderNotify("
                + "RuitoOrderUnit l_ruitoOrderUnit)";

        log.entering(STR_METHOD_NAME);
        if (l_ruitoOrderUnit == null)
        {
            log.error("__ParameterError__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        MarketAdapter l_marketAdaptor =
            l_finApp.getTradingModule(ProductTypeEnum.RUITO).getMarketAdapter();
        RuitoMarketResponseReceiverCallbackService l_callBackService =
            (RuitoMarketResponseReceiverCallbackService)l_marketAdaptor.getMarketResponseReceiverCallbackService();

        DefaultNewOrderAcceptedMarketResponseMessage l_defaultNewOrderAcceptedMarketResponseMessage =
            new DefaultNewOrderAcceptedMarketResponseMessage(
                l_ruitoOrderUnit.getOrderId());

        WEB3RuitoAcceptedDecisionInterceptor l_web3RuitoAcceptedDecisionInterceptor =
            new WEB3RuitoAcceptedDecisionInterceptor();
        l_web3RuitoAcceptedDecisionInterceptor.setOrderErrorReasonCode(null);

        WEB3RuitoOrderManager l_web3RuitoOrderManager =
            (WEB3RuitoOrderManager) l_finApp
                .getTradingModule(ProductTypeEnum.RUITO)
                .getOrderManager();

        l_web3RuitoOrderManager.setThreadLocalPersistenceEventInterceptor(
            l_web3RuitoAcceptedDecisionInterceptor);
        l_callBackService.process(
            l_defaultNewOrderAcceptedMarketResponseMessage);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 取消注文市場リクエストメッセージ送信（MRF解約：市場送信あり）<BR>
     * <BR>
     * MRF注文取消の市場リクエストメッセージを送信する。<BR>
     * <BR>
     * １）　@キューデータインサート<BR>
     * 　@引数.累投注文単位オブジェクトの内容をもとに、<BR>
     *          MRF注文取消キューテーブルに注文データを挿入する。<BR>
     * 　@（DB更新仕様「MRF取消_MRF注文取消キューテーブル.xls」参照）<BR>
     * <BR>
     * ２）　@トリガ発行<BR>
     * 　@－トリガを発行するかのチェック<BR>
     * 　@　@取引時間管理.isトリガ発行()をコールし、<BR>
     *     戻り値がtrueであればトリガを<BR>
     * 　@　@発行する。戻り値がfalseであれば以降の処理を行わない。<BR>
     * 　@　@　@［isトリガ発行に渡すパラメタ］<BR>
     * 　@　@　@　@発注条件：<BR>
     * 　@　@　@　@　@”0 ： DEFAULT”<BR>
     * <BR>
     * 　@－WEB3MQMessageSpecの生成<BR>
     * 　@　@WEB3MQMessageSpecを生成する。<BR>
     * 　@　@[コンストラクタに渡すパラメタ]<BR>
     * 　@　@ 証券会社コード： 引数.補助口座.getInstitution().getInstitutionCode()<BR>
     *           の戻り値<BR>
     * 　@　@　@データコード： ”GI831”<BR>
     * <BR>
     * 　@－トリガを発行する。<BR>
     * 　@　@WEB3MQGatewayServiceImpl.send()をコールし、トリガを発行する。<BR>
     * 　@　@［sendに渡すパラメタ］<BR>
     * 　@　@　@MQに送信するメッセージのスペック： <BR>
     *      生成したWEB3MQMessageSpecオブジェクト<BR>
     * @@param l_ruitoOrderUnit - 累投注文単位<BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4083A7FC01C8
     */
    public void cancelOrderMarketRequestMessageSubmitMRFSellHasMarketSubmit(
        RuitoOrderUnit l_ruitoOrderUnit,
        SubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "cancelOrderMarketRequestMessageSubmitMRFSellHasMarketSubmit("
                + "RuitoOrderUnit l_ruitoOrderUnit,"
                + " SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_ruitoOrderUnit == null || l_subAccount == null)
        {
            log.error("__ParameterError__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        HostMrfOrderCancelParams l_hostMrfOrderCancelParams =
            new HostMrfOrderCancelParams();

        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        String l_strAccountCode = null;
        String l_strOrderRequestNumber = null;
        String l_strTraderCode = null;
        String l_strPoductCode = null;
        String l_strReturnMethod = null;
        String l_strTaxType = null;
        int l_intSellOrderAmount = 0;
        Timestamp l_tsReceivedDateTime = null;

        RuitoOrderUnitRow l_ruitoOderUnitRow =
            (RuitoOrderUnitRow) l_ruitoOrderUnit.getDataSourceObject();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.RUITO);
        RuitoProductManager l_ruitoProductManager =
            (RuitoProductManager) l_tradingModule.getProductManager();

        try
        {
            //部店を取得する
            Branch l_banch =
                l_accountManager.getBranch(l_ruitoOderUnitRow.getBranchId());
            l_strBranchCode = l_banch.getBranchCode();
            log.debug("l_strBranchCode = " + l_strBranchCode);

            //証券会社コードを取得する
            l_strInstitutionCode =
                l_banch.getInstitution().getInstitutionCode();
            log.debug("l_strInstitutionCode = " + l_strInstitutionCode);

            //顧客コードを取得する
            l_strAccountCode =
                l_accountManager
                    .getMainAccount(l_ruitoOderUnitRow.getAccountId())
                    .getAccountCode();
            log.debug("l_strAccountCode = " + l_strAccountCode);

            //識別コードを取得する
            l_strOrderRequestNumber =
                l_ruitoOderUnitRow.getOrderRequestNumber();
            log.debug("l_strOrderRequestNumber = " + l_strOrderRequestNumber);

            //銘柄コードを取得する
            long productId = l_ruitoOderUnitRow.getProductId();
            log.debug("productId = " + productId);
            RuitoProduct l_product =
                (RuitoProduct) l_ruitoProductManager.getProduct(productId);

            RuitoProductRow l_ruitoProductRow =
                (RuitoProductRow) l_product.getDataSourceObject();
            l_strPoductCode = l_ruitoProductRow.getMrfFundCode();
            log.debug("l_strPoductCode = " + l_strPoductCode);

            //受注日時を取得する
            l_tsReceivedDateTime = l_ruitoOderUnitRow.getReceivedDateTime();
            log.debug("l_tsReceivedDateTime = " + l_tsReceivedDateTime);

            //データコード
            l_hostMrfOrderCancelParams.setRequestCode(
                WEB3HostRequestCodeDef
                    .RUITO_REQUEST_MESSAGE_SEND_MRF_ORDER_CANCEL);
            //証券会社コード
            l_hostMrfOrderCancelParams.setInstitutionCode(l_strInstitutionCode);
            //部店コード
            l_hostMrfOrderCancelParams.setBranchCode(l_strBranchCode);
            //顧客コード
            l_hostMrfOrderCancelParams.setAccountCode(l_strAccountCode);
            //扱者コード
            l_hostMrfOrderCancelParams.setTraderCode(l_ruitoOderUnitRow.getSonarTraderCode());
            //識別コード
            l_hostMrfOrderCancelParams.setOrderRequestNumber(
                l_strOrderRequestNumber);
            //銘柄
            l_hostMrfOrderCancelParams.setMrfFundCode(l_strPoductCode);
            //取消区分
            l_hostMrfOrderCancelParams.setCancelDiv("-");
            //受注日時
            l_hostMrfOrderCancelParams.setOrderDate(l_tsReceivedDateTime);
            //処理区分
            l_hostMrfOrderCancelParams.setStatus(WEB3StatusDef.NOT_DEAL);

            Processors.getDefaultProcessor().doInsertQuery(
                l_hostMrfOrderCancelParams);
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundExcetion__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (WEB3GentradeTradingTimeManagement
            .isSubmitMarketTrigger(WEB3OrderingConditionDef.DEFAULT))
        {
            WEB3MQMessageSpec l_web3MQMessageSpec =
                new WEB3MQMessageSpec(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    WEB3HostRequestCodeDef.RUITO_REQUEST_MESSAGE_SEND_MRF_ORDER_CANCEL + "T");
			log.debug("InstitutionCode =" + l_subAccount.getInstitution().getInstitutionCode());
			log.debug("DataCode =" + WEB3HostRequestCodeDef.RUITO_REQUEST_MESSAGE_SEND_MRF_ORDER_CANCEL + "T");
            WEB3MQGatewayService l_web3MQGatewayService =
                (WEB3MQGatewayService) Services.getService(
                    WEB3MQGatewayService.class);

            l_web3MQGatewayService.send(l_web3MQMessageSpec);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 取消注文市場リクエストメッセージ送信（買付：市場送信あり）<BR>
     * <BR>
     * 中期国債ファ@ンド・MMF買付注文取消の市場リクエストメッセージ<BR>
     * を送信する。<BR>
     * <BR>
     * １）　@キューデータインサート<BR>
     * 　@引数.累投注文単位オブジェクトの内容をもとに、<BR>
     *   累投注文取消キューテーブル<BR>
     * 　@に注文データを挿入する。<BR>
     * 　@（DB更新仕様<BR>
     *     「累積投資取消_累投注文取消キューテーブル.xls」参照）<BR>
     * <BR>
     * ２）　@トリガ発行<BR>
     * 　@－トリガを発行するかのチェック<BR>
     * 　@　@取引時間管理.isトリガ発行()をコールし、<BR>
     *      戻り値がtrueであればトリガを<BR>
     * 　@　@発行する。戻り値がfalseであれば以降の処理を行わない。<BR>
     * 　@　@　@［isトリガ発行に渡すパラメタ］<BR>
     * 　@　@　@　@発注条件：<BR>
     * 　@　@　@　@　@”0 ： DEFAULT”<BR>
     * <BR>
     * 　@－WEB3MQMessageSpecの生成<BR>
     * 　@　@WEB3MQMessageSpecを生成する。<BR>
     * 　@　@[コンストラクタに渡すパラメタ]<BR>
     * 　@　@　@証券会社コード： <BR>
     *       引数.補助口座.getInstitution().getInstitutionCode()<BR>
     *         の戻り値<BR>
     * 　@　@　@データコード： ”DI801”<BR>
     * <BR>
     * 　@－トリガを発行する。<BR>
     * 　@　@WEB3MQGatewayServiceImpl.send()をコールし、<BR>
     *     トリガを発行する。<BR>
     * 　@　@［sendに渡すパラメタ］<BR>
     * 　@　@　@MQに送信するメッセージのスペック： <BR>
     *        生成したWEB3MQMessageSpecオブジェクト<BR>
     * @@param l_ruitoOrderUnit - 累投注文単位<BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4083A84401A9
     */
    public void cancelOrderMarketRequestMessageSubmitBuyHasMarketSubmit(
        RuitoOrderUnit l_ruitoOrderUnit,
        SubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "cancelOrderMarketRequestMessageSubmitMRFSellHasMarketSubmit("
                + "RuitoOrderUnit l_ruitoOrderUnit,"
                + " SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_ruitoOrderUnit == null || l_subAccount == null)
        {
            log.error("__ParameterError__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        HostRuitoOrderCancelParams l_hostRuitoOrderCancelParams =
            new HostRuitoOrderCancelParams();

        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        String l_strAccountCode = null;
        String l_strOrderRequestNumber = null;
        String l_strTraderCode = null;
        String l_strCourse = null;
        String l_strPlan = null;
        Timestamp l_tsReceivedDateTime = null;

        RuitoOrderUnitRow l_ruitoOderUnitRow =
            (RuitoOrderUnitRow) l_ruitoOrderUnit.getDataSourceObject();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.RUITO);
        RuitoProductManager l_ruitoProductManager =
            (RuitoProductManager) l_tradingModule.getProductManager();

        try
        {
            //部店を取得する
            Branch l_banch =
                l_accountManager.getBranch(l_ruitoOderUnitRow.getBranchId());
            l_strBranchCode = l_banch.getBranchCode();
            log.debug("l_strBranchCode = " + l_strBranchCode);

            //証券会社コードを取得する
            l_strInstitutionCode =
                l_banch.getInstitution().getInstitutionCode();
            log.debug("l_strInstitutionCode = " + l_strInstitutionCode);

            //顧客コードを取得する
            l_strAccountCode =
                l_accountManager
                    .getMainAccount(l_ruitoOderUnitRow.getAccountId())
                    .getAccountCode();
            log.debug("l_strAccountCode = " + l_strAccountCode);

            //扱者コードを取得する
            l_strTraderCode = l_ruitoOderUnitRow.getSonarTraderCode();
            log.debug("l_strTraderCode = " + l_strTraderCode);

            //識別コードを取得する
            l_strOrderRequestNumber =
                l_ruitoOderUnitRow.getOrderRequestNumber();
            log.debug("l_strOrderRequestNumber = " + l_strOrderRequestNumber);

            //コースを取得する
            WEB3RuitoProduct l_ruitoProduct =
                (WEB3RuitoProduct) l_ruitoProductManager.getProduct(
                    l_ruitoOderUnitRow.getProductId());
            l_strCourse = l_ruitoProduct.getCourse();
            log.debug("l_strCourse = " + l_strCourse);

            //プランを取得する
            l_strPlan = l_ruitoProduct.getPlan();
            log.debug("l_strPlan = " + l_strPlan);

            //受注日時を取得する
            l_tsReceivedDateTime = l_ruitoOderUnitRow.getReceivedDateTime();
            log.debug("l_tsReceivedDateTime = " + l_tsReceivedDateTime);

            //データコード
            l_hostRuitoOrderCancelParams.setRequestCode(
                WEB3HostRequestCodeDef.RUITO_REQUEST_MESSAGE_SEND_ORDER_CANCEL);
            //証券会社コード
            l_hostRuitoOrderCancelParams.setInstitutionCode(
                l_strInstitutionCode);
            //部店コード
            l_hostRuitoOrderCancelParams.setBranchCode(l_strBranchCode);
            //顧客コード
            l_hostRuitoOrderCancelParams.setAccountCode(l_strAccountCode);
            //扱者コード
            l_hostRuitoOrderCancelParams.setTraderCode(l_strTraderCode);
            //識別コード
            l_hostRuitoOrderCancelParams.setOrderRequestNumber(
                l_strOrderRequestNumber);
            //コース
            l_hostRuitoOrderCancelParams.setCourse(l_strCourse);
            //プラン
            l_hostRuitoOrderCancelParams.setPlan(l_strPlan);
            //取消区分
            l_hostRuitoOrderCancelParams.setCancelDiv("-");
            //受注日時
            l_hostRuitoOrderCancelParams.setOrderDate(l_tsReceivedDateTime);
            //処理区分
            l_hostRuitoOrderCancelParams.setStatus(WEB3StatusDef.NOT_DEAL);

            Processors.getDefaultProcessor().doInsertQuery(
                l_hostRuitoOrderCancelParams);
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundExcetion__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (WEB3GentradeTradingTimeManagement
            .isSubmitMarketTrigger(WEB3OrderingConditionDef.DEFAULT))
        {
            WEB3MQMessageSpec l_web3MQMessageSpec =
                new WEB3MQMessageSpec(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    WEB3HostRequestCodeDef.RUITO_REQUEST_MESSAGE_SEND_ORDER_CANCEL + "T",
					l_strCourse);
			log.debug("InstitutionCode =" + l_subAccount.getInstitution().getInstitutionCode());
			log.debug("DataCode =" + WEB3HostRequestCodeDef.RUITO_REQUEST_MESSAGE_SEND_ORDER_CANCEL + "T");
			log.debug("UserData =" + l_strCourse);
            WEB3MQGatewayService l_web3MQGatewayService =
                (WEB3MQGatewayService) Services.getService(
                    WEB3MQGatewayService.class);

            l_web3MQGatewayService.send(l_web3MQMessageSpec);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 取消注文市場リクエストメッセージ送信（解約：市場送信あり）<BR>
     * <BR>
     * 中期国債ファ@ンド・MMF解約注文の市場リクエストメッセージを送信する。<BR>
     * <BR>
     * １）　@キューデータインサート<BR>
     * 　@引数.累投注文単位オブジェクトの内容をもとに、<BR>
     *   累投解約取消キューテーブ<BR>
     * 　@に注文データを挿入する。<BR>
     * 　@（DB更新仕様<BR>
     *     「累積投資取消_累投解約取消キューテーブル.xls」参照）<BR>
     * <BR>
     * ２）　@トリガ発行<BR>
     * 　@－トリガを発行するかのチェック<BR>
     * 　@　@取引時間管理.isトリガ発行()をコールし、<BR>
     *     戻り値がtrueであればトリガを<BR>
     * 　@　@発行する。戻り値がfalseであれば以降の処理を行わない。<BR>
     * 　@　@　@［isトリガ発行に渡すパラメタ］<BR>
     * 　@　@　@　@発注条件：<BR>
     * 　@　@　@　@　@”0 ： DEFAULT”<BR>
     * <BR>
     * 　@－WEB3MQMessageSpecの生成<BR>
     * 　@　@WEB3MQMessageSpecを生成する。<BR>
     * 　@　@[コンストラクタに渡すパラメタ]<BR>
     * 　@　@　@証券会社コード： <BR>
     *      引数.補助口座.getInstitution().getInstitutionCode()<BR>
     *           の戻り値<BR>
     * 　@　@　@データコード： ”DI803”<BR>
     * <BR>
     * 　@－トリガを発行する。<BR>
     * 　@　@WEB3MQGatewayServiceImpl.send()をコールし、<BR>
     *      トリガを発行する。<BR>
     * 　@　@［sendに渡すパラメタ］<BR>
     * 　@　@　@MQに送信するメッセージのスペック： <BR>
     *        生成したWEB3MQMessageSpecオブジェクト<BR>
     * @@param l_ruitoOrderUnit - 累投注文単位<BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4083A87A00CE
     */
    public void cancelOrderMarketRequestMessageSubmitSellHasMarketSubmit(
        RuitoOrderUnit l_ruitoOrderUnit,
        SubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "cancelOrderMarketRequestMessageSubmitMRFSellHasMarketSubmit("
                + "RuitoOrderUnit l_ruitoOrderUnit,"
                + " SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_ruitoOrderUnit == null || l_subAccount == null)
        {
            log.error("__ParameterError__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        HostSellCancelParams l_hostSellCancelParams =
            new HostSellCancelParams();

        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        String l_strAccountCode = null;
        String l_strOrderRequestNumber = null;
        String l_strTraderCode = null;
        String l_strCourse = null;
        String l_strPlan = null;
        String l_strReturnMethod = null;
        String l_strTaxType = null;
        Timestamp l_tsReceivedDateTime = null;

        RuitoOrderUnitRow l_ruitoOderUnitRow =
            (RuitoOrderUnitRow) l_ruitoOrderUnit.getDataSourceObject();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.RUITO);
        RuitoProductManager l_ruitoProductManager =
            (RuitoProductManager) l_tradingModule.getProductManager();

        try
        {
            //部店を取得する
            Branch l_banch =
                l_accountManager.getBranch(l_ruitoOderUnitRow.getBranchId());
            l_strBranchCode = l_banch.getBranchCode();
            log.debug("l_strBranchCode = " + l_strBranchCode);

            //証券会社コードを取得する
            l_strInstitutionCode =
                l_banch.getInstitution().getInstitutionCode();
            log.debug("l_strInstitutionCode = " + l_strInstitutionCode);

            //顧客コードを取得する
            l_strAccountCode =
                l_accountManager
                    .getMainAccount(l_ruitoOderUnitRow.getAccountId())
                    .getAccountCode();
            log.debug("l_strAccountCode = " + l_strAccountCode);

            //扱者コードを取得する
            l_strTraderCode = l_ruitoOderUnitRow.getSonarTraderCode();
            log.debug("l_strTraderCode = " + l_strTraderCode);

            //識別コードを取得する
            l_strOrderRequestNumber =
                l_ruitoOderUnitRow.getOrderRequestNumber();
            log.debug("l_strOrderRequestNumber = " + l_strOrderRequestNumber);

            //コースを取得する
            WEB3RuitoProduct l_ruitoProduct =
                (WEB3RuitoProduct) l_ruitoProductManager.getProduct(
                    l_ruitoOderUnitRow.getProductId());
            l_strCourse = l_ruitoProduct.getCourse();
            log.debug("l_strCourse = " + l_strCourse);

            //プランを取得する
            l_strPlan = l_ruitoProduct.getPlan();
            log.debug("l_strPlan = " + l_strPlan);

            //受注日時を取得する
            l_tsReceivedDateTime = l_ruitoOderUnitRow.getReceivedDateTime();
            log.debug("l_tsReceivedDateTime = " + l_tsReceivedDateTime);

            //データコード
            l_hostSellCancelParams.setRequestCode(
                WEB3HostRequestCodeDef.RUITO_REQUEST_MESSAGE_SEND_SELL_CANCEL);
            //証券会社コード
            l_hostSellCancelParams.setInstitutionCode(l_strInstitutionCode);
            //部店コード
            l_hostSellCancelParams.setBranchCode(l_strBranchCode);
            //顧客コード
            l_hostSellCancelParams.setAccountCode(l_strAccountCode);
            //扱者コード
            l_hostSellCancelParams.setTraderCode(l_strTraderCode);
            //識別コード
            l_hostSellCancelParams.setOrderRequestNumber(
                l_strOrderRequestNumber);
            //コース
            l_hostSellCancelParams.setCourse(l_strCourse);
            //プラン
            l_hostSellCancelParams.setPlan(l_strPlan);
            //融資区分
            l_hostSellCancelParams.setLoanDiv(null);
            //銘柄・回号
            l_hostSellCancelParams.setProductIssueCode(null);
            //取消区分
            l_hostSellCancelParams.setCancelDiv("-");
            //受注日時
            l_hostSellCancelParams.setOrderDate(l_tsReceivedDateTime);
            //処理区分
            l_hostSellCancelParams.setStatus(WEB3StatusDef.NOT_DEAL);

            Processors.getDefaultProcessor().doInsertQuery(
                l_hostSellCancelParams);
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundExcetion__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (WEB3GentradeTradingTimeManagement
            .isSubmitMarketTrigger(WEB3OrderingConditionDef.DEFAULT))
        {
            WEB3MQMessageSpec l_web3MQMessageSpec =
                new WEB3MQMessageSpec(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    WEB3HostRequestCodeDef.RUITO_REQUEST_MESSAGE_SEND_SELL_CANCEL + "T",
					l_strCourse);
			log.debug("InstitutionCode =" + l_subAccount.getInstitution().getInstitutionCode());
			log.debug("DataCode =" + WEB3HostRequestCodeDef.RUITO_REQUEST_MESSAGE_SEND_SELL_CANCEL + "T");
			log.debug("UserData =" + l_strCourse);
            WEB3MQGatewayService l_web3MQGatewayService =
                (WEB3MQGatewayService) Services.getService(
                    WEB3MQGatewayService.class);

            l_web3MQGatewayService.send(l_web3MQMessageSpec);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 取消注文市場リクエストメッセージ送信（MRF解約：市場送信なし）<BR>
     * <BR>
     * １）　@キューデータ削除<BR>
     * 　@引数.累投注文単位オブジェクトの識別コードに対応する<BR>
     *               キューデータが存在する場合、<BR>
     * 　@MRF注文キューテーブルのデータを削除する。<BR>
     * 　@［削除条件］<BR>
     * 　@　@証券会社コード = <BR>
     * 引数.補助口座.getInstitution().getInstitutionCode()の戻り値 AND<BR>
     * 　@　@部店コード = <BR>
     * 引数.補助口座.getMainAccount().getBranch().getBranchCode()<BR>
     *          の戻り値 AND<BR>
     * 　@　@顧客コード = 引数.補助口座.getMainAccount().getAccountCode()<BR>
     *          の戻り値 AND<BR>
     * 　@　@識別コード = 引数.累投注文単位.getDataSourceObject().get識別コード()<BR>
     * @@param l_ruitoOrderUnit - 累投注文単位<BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4083A89D0217
     */
    public void cancelOrderMarketRequestMessageSubmitMRFSellNotMarketSubmit(
        RuitoOrderUnit l_ruitoOrderUnit,
        SubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "cancelOrderMarketRequestMessageSubmitMRFSellNotMarketSubmit("
                + "RuitoOrderUnit l_ruitoOrderUnit, SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_ruitoOrderUnit == null || l_subAccount == null)
        {
            log.error("__ParameterError__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //引数.累投注文単位オブジェクトの識別コードに対応するキューデータが存在する場合は
        //MRF注文キューテーブルのデータを削除する
        try
        {
            // MRF注文キューテーブルより、以下の条件に一致する行を削除する。
            // 証券会社コード = 引数.補助口座.getInstitution().getInstitutionCode()の戻り値
            // 部店コード = 引数.補助口座.getMainAccount().getBranch().getBranchCode()の戻り値
            // 顧客コード = 引数.補助口座.getMainAccount().getAccountCode()の戻り値
            // 識別コード = 引数.累投注文単位.getDataSourceObject().get識別コード()
            QueryProcessor processor = Processors.getDefaultProcessor();
            String l_strWhere =
                " institution_code=? and branch_code=? and account_code=? and order_request_number=? ";
            String[] l_strBindValues = new String[4];
            l_strBindValues[0] =
                l_subAccount.getInstitution().getInstitutionCode();
            log.debug("InstitutionCode = " + l_strBindValues[0]);
            l_strBindValues[1] =
                l_subAccount.getMainAccount().getBranch().getBranchCode();
            log.debug("BranchCode = " + l_strBindValues[1]);
            l_strBindValues[2] = l_subAccount.getMainAccount().getAccountCode();
            log.debug("AccountCode = " + l_strBindValues[2]);
            RuitoOrderUnitRow l_ruitoOrderUnitRow =
                (RuitoOrderUnitRow) l_ruitoOrderUnit.getDataSourceObject();
            l_strBindValues[3] = l_ruitoOrderUnitRow.getOrderRequestNumber();
            log.debug("OrderRequestNumber = " + l_strBindValues[3]);

            //対象データ削除
            processor.doDeleteAllQuery(
                HostMrfOrderParams.TYPE,
                l_strWhere,
                l_strBindValues);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 取消注文市場リクエストメッセージ送信（買付：市場送信なし）<BR>
     * <BR>
     * １）　@キューデータ削除<BR>
     * 　@引数.累投注文単位オブジェクトの識別コードに<BR>
     * 対応するキューデータが存在する場合は、<BR>
     * 　@累投注文キューテーブルのデータを削除する。<BR>
     * 　@［削除条件］<BR>
     * 　@　@証券会社コード = <BR>
     *      引数.補助口座.getInstitution().getInstitutionCode()の戻り値 AND<BR>
     * 　@　@部店コード = <BR>
     *     引数.補助口座.getMainAccount().getBranch().getBranchCode()<BR>
     *     の戻り値 AND<BR>
     * 　@　@顧客コード = <BR>
     *    引数.補助口座.getMainAccount().getAccountCode()の戻り値 AND<BR>
     * 　@　@識別コード = <BR>
     *    引数.累投注文単位.getDataSourceObject().get識別コード()<BR>
     * @@param l_ruitoOrderUnit - 累投注文単位<BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4083A89D0226
     */
    public void cancelOrderMarketRequestMessageSubmitBuyNotMarketSubmit(
        RuitoOrderUnit l_ruitoOrderUnit,
        SubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "cancelOrderMarketRequestMessageSubmitBuyNotMarketSubmit("
                + "RuitoOrderUnit l_ruitoOrderUnit, SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_ruitoOrderUnit == null || l_subAccount == null)
        {
            log.error("__ParameterError__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //引数.累投注文単位オブジェクトの識別コードに対応するキューデータが存在する場合は
        //MRF注文キューテーブルのデータを削除する
        try
        {
            // MRF注文キューテーブルより、以下の条件に一致する行を削除する。
            // 証券会社コード = 引数.補助口座.getInstitution().getInstitutionCode()の戻り値
            // 部店コード = 引数.補助口座.getMainAccount().getBranch().getBranchCode()の戻り値
            // 顧客コード = 引数.補助口座.getMainAccount().getAccountCode()の戻り値
            // 識別コード = 引数.累投注文単位.getDataSourceObject().get識別コード()
            QueryProcessor processor = Processors.getDefaultProcessor();
            String l_strWhere =
                " institution_code=? and branch_code=? and account_code=? and order_request_number=? ";
            String[] l_strBindValues = new String[4];
            l_strBindValues[0] =
                l_subAccount.getInstitution().getInstitutionCode();
            log.debug("InstitutionCode = " + l_strBindValues[0]);
            l_strBindValues[1] =
                l_subAccount.getMainAccount().getBranch().getBranchCode();
            log.debug("BranchCode = " + l_strBindValues[1]);
            l_strBindValues[2] = l_subAccount.getMainAccount().getAccountCode();
            log.debug("AccountCode = " + l_strBindValues[2]);
            RuitoOrderUnitRow l_ruitoOrderUnitRow =
                (RuitoOrderUnitRow) l_ruitoOrderUnit.getDataSourceObject();
            l_strBindValues[3] = l_ruitoOrderUnitRow.getOrderRequestNumber();
            log.debug("OrderRequestNumber = " + l_strBindValues[3]);

            //対象データ削除
            processor.doDeleteAllQuery(
                HostRuitoOrderParams.TYPE,
                l_strWhere,
                l_strBindValues);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 取消注文市場リクエストメッセージ送信（解約：市場送信なし）<BR>
     * <BR>
     * １）　@キューデータ削除<BR>
     * 　@引数.累投注文単位オブジェクトの識別コードに<BR>
     *            対応するキューデータが存在する場合は、<BR>
     * 　@累投解約キューテーブルのデータを削除する。<BR>
     * 　@［削除条件］<BR>
     * 　@　@証券会社コード = <BR>
     *     引数.補助口座.getInstitution().getInstitutionCode()<BR>
     *          の戻り値 AND<BR>
     * 　@　@部店コード = <BR>
     *    引数.補助口座.getMainAccount().getBranch().getBranchCode()<BR>
     *           の戻り値 AND<BR>
     * 　@　@顧客コード = <BR>
     *    引数.補助口座.getMainAccount().getAccountCode()<BR>
     *           の戻り値 AND<BR>
     * 　@　@識別コード = <BR>
     *    引数.累投注文単位.getDataSourceObject().get識別コード()<BR>
     * @@param l_ruitoOrderUnit - 累投注文単位<BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4083A89D0236
     */
    public void cancelOrderMarketRequestMessageSubmitSellNotMarketSubmit(
        RuitoOrderUnit l_ruitoOrderUnit,
        SubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "cancelOrderMarketRequestMessageSubmitSellNotMarketSubmit("
                + "RuitoOrderUnit l_ruitoOrderUnit, SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_ruitoOrderUnit == null || l_subAccount == null)
        {
            log.error("__ParameterError__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //引数.累投注文単位オブジェクトの識別コードに対応するキューデータが存在する場合は
        //MRF注文キューテーブルのデータを削除する
        try
        {
            // MRF注文キューテーブルより、以下の条件に一致する行を削除する。
            // 証券会社コード = 引数.補助口座.getInstitution().getInstitutionCode()の戻り値
            // 部店コード = 引数.補助口座.getMainAccount().getBranch().getBranchCode()の戻り値
            // 顧客コード = 引数.補助口座.getMainAccount().getAccountCode()の戻り値
            // 識別コード = 引数.累投注文単位.getDataSourceObject().get識別コード()
            QueryProcessor processor = Processors.getDefaultProcessor();
            String l_strWhere =
                " institution_code=? and branch_code=? and account_code=? and order_request_number=? ";
            String[] l_strBindValues = new String[4];
            l_strBindValues[0] =
                l_subAccount.getInstitution().getInstitutionCode();
            log.debug("InstitutionCode = " + l_strBindValues[0]);
            l_strBindValues[1] =
                l_subAccount.getMainAccount().getBranch().getBranchCode();
            log.debug("BranchCode = " + l_strBindValues[1]);
            l_strBindValues[2] = l_subAccount.getMainAccount().getAccountCode();
            log.debug("AccountCode = " + l_strBindValues[2]);
            RuitoOrderUnitRow l_ruitoOrderUnitRow =
                (RuitoOrderUnitRow) l_ruitoOrderUnit.getDataSourceObject();
            l_strBindValues[3] = l_ruitoOrderUnitRow.getOrderRequestNumber();
            log.debug("OrderRequestNumber = " + l_strBindValues[3]);

            //対象データ削除
            processor.doDeleteAllQuery(
                HostRuitoSellParams.TYPE,
                l_strWhere,
                l_strBindValues);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /* (非 Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSenderService#send(com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestMessage)
     */
    public MarketRequestSendResult send(MarketRequestMessage arg0)
    {
        return null;
    }

}
@
