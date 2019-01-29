head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoMRFOrderAcceptUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累投MRF注文受付１件サービス実装クラス(WEB3RuitoMRFOrderAcceptUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 韋念瓊 (中訊) 新規作成
*/
package webbroker3.xbruito.service.delegate.stdimpls;

import webbroker3.xbruito.service.delegate.WEB3RuitoMRFOrderAcceptUnitService;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.market.messages.DefaultRuitoNewOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSendResult;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderRejectedMarketResponseMessage;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.xbruito.WEB3RuitoAcceptedDecisionInterceptor;
import webbroker3.xbruito.marketadaptor.WEB3RuitoMarketRequestSubmitServiceImpl;
import webbroker3.common.WEB3BaseException;
import webbroker3.xbruito.WEB3RuitoOrderManager;
import webbroker3.xbruito.WEB3RuitoProductManager;
import webbroker3.xbruito.WEB3RuitoProduct;
import webbroker3.xbruito.WEB3RuitoTradedProduct;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * 累投MRF注文受付１件サービス実装クラス<BR>
 * <BR>
 * 注文１件ごとの受付処理を実施する。<BR>
 */
public class WEB3RuitoMRFOrderAcceptUnitServiceImpl
    implements WEB3RuitoMRFOrderAcceptUnitService
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoMRFOrderAcceptUnitServiceImpl.class);

    /**
     * 累投MRF注文受付失敗処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（累投MRF注文受付）notify注文受付失敗」参照。<BR>
     * <BR>
     * １）　@拡張累投注文マネージャ.<BR>
     *    setThreadLocalPersistenceEventInterceptor()<BR>
     * 　@をコールし、インタセプタを設定する。<BR>
     * 　@[setThreadLocalPersistenceEventInterceptorに<BR>
     *   渡すパラメタ]<BR>
     * 　@インタセプタ： 引数.累投受付確定インタセプタ<BR>
     * <BR>
     * ２） RuitoMarketResponseReceiverCallbackService<BR>
     *   を取得する。<BR>
     * <BR>
     * ３）　@DefaultNewOrderRejectedMarketResponseMessage<BR>
     *   オブジェクトを生成する。<BR>
     * 　@[コンストラクタに渡すパラメタ]<BR>
     * 　@注文ID： 引数.累投注文単位.getOrderId()の戻り値<BR>
     * <BR>
     * ４）　@RuitoMarketResponseReceiverCallbackService.process()<BR>
     *    メソッドをコールする。<BR>
     * 　@[processに渡すパラメタ]<BR>
     * 　@取消注文拒否市場応答メッセージ：<BR>
     * 　@生成した<BR>
     *   DefaultNewOrderRejectedMarketResponseMessage<BR>
     *   オブジェクト<BR>
     * <BR>
     * ５）　@process()メソッドの戻り値.isFailedResult()の<BR>
     *   値がtrueの場合は、例外をスローする。<BR>
     *   累投MRF注文受付エラー:<BR>
     *     classpath:WEB3BusinessLayerException<BR>
     *     tag:BUSINESS_ERROR_00238<BR>
     *     code:252<BR>
     * <BR>
     * ６）　@引数.累投注文単位.getDataSourceObject().<BR>
     *    getMRF注文識別コード()の戻り値と<BR>
     * 　@等しい識別コードを持つ累投注文単位オブジェクトを取得する。<BR>
     * 　@[get注文単位に渡すパラメタ]<BR>
     * 　@　@補助口座ID： 引数.累投注文単位.getSubAccountId()の戻り値<BR>
     * 　@　@識別コード： 取得したMRF注文識別コード<BR>
     * <BR>
     * ７）　@拡張累投注文マネージャ.setThreadLocalPersistenceEventInterceptor()<BR>
     * 　@をコールし、インタセプタを設定する。<BR>
     * 　@[setThreadLocalPersistenceEventInterceptorに渡すパラメタ]<BR>
     * 　@　@インタセプタ： 引数.累投受付確定インタセプタ<BR>
     * <BR>
     * ８）　@DefaultNewOrderRejectedMarketResponseMessage<BR>
     *   オブジェクトを生成する。<BR>
     * 　@[コンストラクタに渡すパラメタ]<BR>
     * 　@　@注文ID： 取得した累投注文単位.getOrderId()の戻り値<BR>
     * <BR>
     * ９）　@RuitoMarketResponseReceiverCallbackService.process()<BR>
     *   メソッドをコールする。<BR>
     * 　@[processに渡すパラメタ]<BR>
     * 　@　@取消注文拒否市場応答メッセージ：<BR>
     * 　@　@生成した<BR>
     *   DefaultNewOrderRejectedMarketResponseMessageオブジェクト<BR>
     * <BR>
     * １０）　@process()メソッドの戻り値.isFailedResult()の値がfalseの場合は、<BR>
     *   例外をスローする。<BR>
     *   累投MRF注文受付エラー:<BR>
     *     classpath:WEB3BusinessLayerException<BR>
     *     tag:BUSINESS_ERROR_00238<BR>
     *     code:252<BR>
     * @@param l_ruitoOrderUnit - 累投注文単位<BR>
     * @@param l_ruitoAcceptDecisionInterceptor - 累投受付確定インタセプタ<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 408760DE006C
     */
    public void notifyOrderAcceptFail(
        RuitoOrderUnit l_ruitoOrderUnit,
        WEB3RuitoAcceptedDecisionInterceptor l_ruitoAcceptDecisionInterceptor)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "notifyOrderAcceptFail(RuitoOrderUnit l_ruitoOrderUnit, " +
            "WEB3RuitoAcceptedDecisionInterceptor l_ruitoAcceptDecisionInterceptor)";
        log.entering(STR_METHOD_NAME);

        if (l_ruitoOrderUnit == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tm = l_finApp.getTradingModule(ProductTypeEnum.RUITO);

            WEB3RuitoOrderManager l_ruitoOrderMgr =
                (WEB3RuitoOrderManager) l_tm.getOrderManager();
            MarketAdapter l_marketAdaptor = l_tm.getMarketAdapter();


            //１）拡張累投注文マネージャ.setThreadLocalPersistenceEventInterceptor()
            //をコールし、インタセプタを設定する。
            l_ruitoOrderMgr.setThreadLocalPersistenceEventInterceptor(
                l_ruitoAcceptDecisionInterceptor);

            //２） RuitoMarketResponseReceiverCallbackServiceを取得する。
            RuitoMarketResponseReceiverCallbackService l_service =
               (RuitoMarketResponseReceiverCallbackService)l_marketAdaptor
               .getMarketResponseReceiverCallbackService();

            //３）DefaultNewOrderRejectedMarketResponseMessageオブジェクトを生成する。
            DefaultNewOrderRejectedMarketResponseMessage
                l_defaultNewOrderRejectedMarketResponseMessage =
                new DefaultNewOrderRejectedMarketResponseMessage(
                    l_ruitoOrderUnit.getOrderId());

            //４）RuitoMarketResponseReceiverCallbackService.process()メソッドをコールする。
            ProcessingResult l_processingResult =
                    l_service.process(
                    l_defaultNewOrderRejectedMarketResponseMessage);
            //５）process()メソッドの戻り値.isFailedResult()の値がtrueの場合
            if (l_processingResult.isFailedResult())
            {
                log.debug("累投MRF注文受付エラー");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00238,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "process()メソッドの戻り値.isFailedResult()の値がtrueの場合");
            }

            RuitoOrderUnitParams l_ruitoOrderUnitParams =
                ((RuitoOrderUnitParams) l_ruitoOrderUnit.getDataSourceObject());
            //６）getMRF注文識別コード()
            String l_lngMRFOrderRequestNumber =
                l_ruitoOrderUnitParams.getMrfOrderRequestNumber();
            log.debug("RuitoOrderUnit.MRF注文識別コード = " + l_lngMRFOrderRequestNumber);

            //識別コードを持つ累投注文単位オブジェクトを取得する。
            RuitoOrderUnit l_ruitoOrderUnitMRF =
                l_ruitoOrderMgr.getRuitoOrderUnit(
                l_ruitoOrderUnit.getAccountId(),
                l_ruitoOrderUnit.getSubAccountId(),
                l_lngMRFOrderRequestNumber);

            //７）拡張累投注文マネージャ.setThreadLocalPersistenceEventInterceptor()
            //をコールし、インタセプタを設定する。
            l_ruitoOrderMgr.setThreadLocalPersistenceEventInterceptor(
                l_ruitoAcceptDecisionInterceptor);

            //８）DefaultNewOrderRejectedMarketResponseMessageオブジェクトを生成する。
            DefaultNewOrderRejectedMarketResponseMessage
                l_marketResponseMessage =
                new DefaultNewOrderRejectedMarketResponseMessage(
                    l_ruitoOrderUnitMRF.getOrderId());
            log.debug("ruitoOrderUnit.getOrderId() = " + l_ruitoOrderUnitMRF.getOrderId());

            //９）RuitoMarketResponseReceiverCallbackService.process()メソッドをコールする。
            l_processingResult = l_service.process(l_marketResponseMessage);

            //１０）process()メソッドの戻り値.isFailedResult()の値がfalseの場合
            boolean l_blnProcessResult = l_processingResult.isFailedResult();
            log.debug("process()メソッドの戻り値 = " + l_blnProcessResult);
            if (l_blnProcessResult)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00238,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        catch (NotFoundException l_ex)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 累投MRF注文受付完了処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（累投MRF注文受付）notify注文受付完了」参照。<BR>
     * <BR>
     * １）拡張累投注文マネージャ.setThreadLocalPersistenceEventInterceptor()<BR>
     * 　@をコールし、インタセプタを設定する。<BR>
     * 　@[setThreadLocalPersistenceEventInterceptorに渡すパラメタ]<BR>
     * 　@　@インタセプタ： 引数.累投受付確定インタセプタ<BR>
     * <BR>
     * ２）　@RuitoMarketResponseReceiverCallbackServiceを取得する。<BR>
     *
     * ３）DefaultNewOrderAcceptedMarketResponseMessage<BR>
     *     オブジェクトを生成する。<BR>
     * 　@[コンストラクタに渡すパラメタ]<BR>
     * 　@　@注文ID： 引数.累投注文単位.getOrderId()の戻り値<BR>
     * <BR>
     * ４）　@RuitoMarketResponseReceiverCallbackService.process()<BR>
     *    メソッドをコールする。<BR>
     *  　@[processに渡すパラメタ]<BR>
     * 　@ 取消注文受付済市場応答メッセージ：<BR>
     * 　@ 生成した<BR>
     *    DefaultNewOrderAcceptedMarketResponseMessage<BR>
     *    オブジェクト<BR>
     * <BR>
     * ５）　@process()メソッドの戻り値.isFailedResult()の値が<BR>
     *    trueの場合は、例外をスローする。<BR>
     *    累投MRF注文受付エラー:<BR>
     *     classpath:WEB3BusinessLayerException<BR>
     *     tag:BUSINESS_ERROR_00238<BR>
     *     code:252<BR>
     * <BR>
     * ６）引数.累投注文単位.getDataSourceObject().getMRF注文識別コード()<BR>
     *    の戻り値に対応する<BR>
     *  　@累投注文単位オブジェクトを取得する。<BR>
     * <BR>
     * 　@－拡張累投注文マネージャ.get注文単位()をコールし、<BR>
     *    取得したMRF注文識別コードと等しい<BR>
     * 　@ 識別コードを持つ累投注文単位オブジェクトを取得する。<BR>
     * 　@ [get注文単位に渡すパラメタ]<BR>
     * 　@ 補助口座ID： 引数.累投注文単位.getSubAccountId()の戻り値<BR>
     * 　@　@　@識別コード： <BR>
     *     引数.累投注文単位.getDataSourceObject().getMRF注文識別コード()<BR>
     *     の戻り値<BR>
     * <BR>
     * ７）市場アダプタより、累投市場リクエスト送信サービスを取得する。<BR>
     *
     * ８）　@累投市場リクエスト送信サービスに、<BR>
     *     市場送信処理を実施するという設定を行う。<BR>
     * 　@　@累投市場リクエスト送信サービス.set市場送信可否()をコールする。<BR>
     * 　@　@[.set市場送信可否に渡すパラメタ]<BR>
     * 　@　@is市場送信： true<BR>
     * <BR>
     * ９）　@拡張アカウントマネージャ.getSubAccount()をコールし、<BR>
     *     補助口座オブジェクトを取得する<BR>
     * 　@　@[getSubAccountに渡すパラメタ]<BR>
     * 　@　@口座ID： <BR>
     *      取得した累投注文単位オブジェクト.getAccountId()の戻り値<BR>
     * 　@　@補助口座ID： <BR>
     *     取得した累投注文単位オブジェクト.getSubAccountId()の戻り値<BR>
     * <BR>
     * １０）　@拡張累投銘柄オブジェクトを取得する。<BR>
     *     拡張累投銘柄マネージャ.get累投銘柄()をコールし、<BR>
     *     拡張累投銘柄オブジェクトを取得する。<BR>
     *   　@[get累投銘柄に渡すパラメタ]<BR>
     * 　@　@銘柄ID： <BR>
     *     取得した<BR>
     *     累投注文単位オブジェクト.getDataSourceObject().getProductId()<BR>
     *     の戻り値<BR>
     * <BR>
     * １１）　@拡張累投銘柄マネージャ.get累投取引銘柄()をコールし、<BR>
     *     拡張累投取引銘柄を取得する。<BR>
     *   　@[get累投取引銘柄に渡すパラメタ]<BR>
     * 　@　@証券会社：<BR>
     *      取得した補助口座オブジェクト.getInstitution()の戻り値<BR>
     * 　@　@銘柄コード： <BR>
     *     取得した拡張累投銘柄オブジェクト.getProductCode()<BR>
     *     の戻り値<BR>
     * <BR>
     * １２）　@DefaultRuitoNewOrderMarketRequestMessage<BR>
     *     オブジェクトを生成する。<BR>
     *   　@[コンストラクタに渡すパラメタ]<BR>
     * 　@　@補助口座： 取得した補助口座オブジェクト<BR>
     * 　@　@取引銘柄： 取得した拡張累投取引銘柄オブジェクト<BR>
     * 　@　@累投注文単位Row：<BR>
     *     取得した累投注文単位.getDataSourceObject()の戻り値<BR>
     * <BR>
     * １３）　@累投市場リクエスト送信サービス.<BR>
     *     新規注文市場リクエストメッセージ送信()メソッドをコールする。<BR>
     *   　@[新規注文市場リクエストメッセージ送信に渡すパラメタ]<BR>
     * 　@　@累投新規注文市場リクエストメッセージ： <BR>
     * 　@　@生成した<BR>
     *     DefaultRuitoNewOrderMarketRequestMessageオブジェクト<BR>
     * <BR>
     * １４）累投市場リクエスト送信サービス.<BR>
     *      新規注文市場リクエストメッセージ送信()<BR>
     *     の戻り値判定<BR>
     * 　@　@新規注文市場リクエストメッセージ送信()の<BR>
     *     戻り値.getProcessingResult().isSuccessfulResult()==false<BR>
     * 　@　@の場合、例外をスローする。<BR>
     *     新規注文市場メッセージ送信エラー:<BR>
     *     classpath:WEB3BusinessLayerException<BR>
     *     tag:BUSINESS_ERROR_00237<BR>
     * @@param l_ruitoOrderUnit - 累投注文単位<BR>
     * @@param l_ruitoAcceptDecisionInterceptor - 累投受付確定インタセプタ<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 408760DE008C
     */
    public void notifyOrderAcceptComplete(
        RuitoOrderUnit l_ruitoOrderUnit,
        WEB3RuitoAcceptedDecisionInterceptor l_ruitoAcceptDecisionInterceptor)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "notifyOrderAcceptComplete(RuitoOrderUnit l_ruitoOrderUnit, " +
            "WEB3RuitoAcceptedDecisionInterceptor l_ruitoAcceptDecisionInterceptor)";
        log.entering(STR_METHOD_NAME);

        if (l_ruitoOrderUnit == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }

        try
        {
            //１）拡張累投注文マネージャ.setThreadLocalPersistenceEventInterceptor()
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tm = l_finApp.getTradingModule(ProductTypeEnum.RUITO);

            WEB3RuitoOrderManager l_ruitoOrderMgr =
                (WEB3RuitoOrderManager) l_tm.getOrderManager();
            MarketAdapter l_marketAdaptor = l_tm.getMarketAdapter();

            l_ruitoOrderMgr.setThreadLocalPersistenceEventInterceptor(
                l_ruitoAcceptDecisionInterceptor);

            //２）　@RuitoMarketResponseReceiverCallbackServiceを取得する。
            RuitoMarketResponseReceiverCallbackService l_service =
               (RuitoMarketResponseReceiverCallbackService)l_marketAdaptor
               .getMarketResponseReceiverCallbackService();

            log.debug("l_ruitoOrderUnit.getOrderId()=" + l_ruitoOrderUnit.getOrderId());
            //３）DefaultNewOrderAcceptedMarketResponseMessageブジェクトを生成する。
            DefaultNewOrderAcceptedMarketResponseMessage l_responseMessage =
                new DefaultNewOrderAcceptedMarketResponseMessage(
                l_ruitoOrderUnit.getOrderId());

            log.debug("=========test========");
            //４）RuitoMarketResponseReceiverCallbackService.process()メソッドをコールする。
            ProcessingResult l_processingResult =
                l_service.process(l_responseMessage);

            //５）process()メソッドの戻り値.isFailedResult()の値がtrueの場合
            boolean l_blnProcessResult = l_processingResult.isFailedResult();
            log.debug("process()メソッドの戻り値 = " + l_blnProcessResult);
            if (l_blnProcessResult)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00238,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            //６）引数.累投注文単位.getDataSourceObject().getMRF注文識別コード()
            RuitoOrderUnitParams l_ruitoOrderUnitParams =
                ((RuitoOrderUnitParams) l_ruitoOrderUnit.getDataSourceObject());

            //getMRF注文識別コード()
            String l_lngMRFOrderRequestNumber =
                l_ruitoOrderUnitParams.getMrfOrderRequestNumber();

            log.debug("l_ruitoOrderMgr.getRuitoOrderUnit()");
            log.debug("AccountId = " + l_ruitoOrderUnit.getAccountId());
            log.debug("SubAccountId = " + l_ruitoOrderUnit.getSubAccountId());
            log.debug("RuitoOrderUnit.MRF注文識別コード = " + l_lngMRFOrderRequestNumber);

            RuitoOrderUnit l_ruitoOrderUnitMRF =
            l_ruitoOrderMgr.getRuitoOrderUnit(
                l_ruitoOrderUnit.getAccountId(),
                l_ruitoOrderUnit.getSubAccountId(),
                l_lngMRFOrderRequestNumber);        //NotFoundException

            l_ruitoOrderMgr.setThreadLocalPersistenceEventInterceptor(
                l_ruitoAcceptDecisionInterceptor);

            //７）市場アダプタより、累投市場リクエスト送信サービスを取得する。
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.RUITO);
            MarketAdapter l_marketAdapter = l_tradingModule.getMarketAdapter();
            WEB3RuitoMarketRequestSubmitServiceImpl l_marketRequestSubmitService =
                (WEB3RuitoMarketRequestSubmitServiceImpl)
                l_marketAdapter.getMarketRequestSenderServce();

            //８）累投市場リクエスト送信サービスに、市場送信処理を実施するという設定を行う。
            l_marketRequestSubmitService.setMarketSubmit(true);

            //９）拡張アカウントマネージャ.getSubAccount()をコールし
            WEB3GentradeAccountManager l_accMgr =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();

            //NotFoundException
            SubAccount l_subAccount =
                    l_accMgr.getSubAccount(
                    l_ruitoOrderUnitMRF.getAccountId(),
                    l_ruitoOrderUnitMRF.getSubAccountId());

            //１０）拡張累投銘柄オブジェクトを取得する。
            WEB3RuitoProductManager l_ruitoProductManager =
                (WEB3RuitoProductManager) l_finApp
                    .getTradingModule(ProductTypeEnum.RUITO)
                    .getProductManager();

            RuitoOrderUnitRow l_ruitoOrderUnitRow =
                (RuitoOrderUnitRow) l_ruitoOrderUnitMRF.getDataSourceObject();

            log.debug("l_ruitoProductManager.getRuitoProduct()");
            log.debug("l_ruitoOrderUnitRow.getProductId()=" + l_ruitoOrderUnitRow.getProductId());
            WEB3RuitoProduct l_ruitoProduct =
                (WEB3RuitoProduct) l_ruitoProductManager.getRuitoProduct(
                l_ruitoOrderUnitRow.getProductId());

            //１１）拡張累投銘柄マネージャ.get累投取引銘柄()をコールし、
            //拡張累投取引銘柄を取得する。
            log.debug("InstitutionCode = " + l_subAccount.getInstitution().getInstitutionCode());
            log.debug("ProductCode = " + l_ruitoProduct.getProductCode());
            log.debug("l_ruitoProductManager.getRuitoTradedProduct() begin");
            WEB3RuitoTradedProduct l_ruitoTradedProduct =
                    (WEB3RuitoTradedProduct) l_ruitoProductManager
                    .getRuitoTradedProduct(
                    l_subAccount.getInstitution(),
                    l_ruitoProduct.getProductCode());
            log.debug("l_ruitoProductManager.getRuitoTradedProduct() end");
            //１２）DefaultRuitoNewOrderMarketRequestMessageオブジェクトを生成する。
            DefaultRuitoNewOrderMarketRequestMessage l_marketRequestMessage =
                new DefaultRuitoNewOrderMarketRequestMessage(
                    l_subAccount,
                    l_ruitoTradedProduct,
                    l_ruitoOrderUnitRow);

            //１３）累投市場リクエスト送信サービス.新規注文市場リクエスト
            //メッセージ送信()メソッドをコールする。
            MarketRequestSendResult l_marketRequestSendResult =
                l_marketRequestSubmitService.send(l_marketRequestMessage);

            //１４）累投市場リクエスト送信サービス.新規注文市場リクエスト
            //メッセージ送信()の戻り値判定
            boolean l_blnSendResult = l_marketRequestSendResult
                    .getProcessingResult().isSuccessfulResult();

            if (!l_blnSendResult)
            {
                log.debug("__新規注文市場メッセージ送信エラー__");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00237,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "新規注文市場リクエストメッセージ送信()の戻り値." +
                    "getProcessingResult().isSuccessfulResult()==falseの場合");
            }
        }
        catch(NotFoundException l_ex)
        {
            log.error("__NotFoundException__");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
