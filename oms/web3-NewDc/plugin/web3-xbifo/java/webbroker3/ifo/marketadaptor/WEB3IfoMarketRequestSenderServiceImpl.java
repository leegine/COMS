head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoMarketRequestSenderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP市場リクエスト送信サービス(WEB3IfoMarketRequestSenderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 李強(中訊)  新規建注文送信       新規作成
Revesion History : 2004/06/16 李強(中訊)  返済注文送信         新規作成
Revesion History : 2004/06/16 李強(中訊)  取消注文送信         新規作成
Revesion History : 2004/06/16 李強(中訊)  訂正確定             新規作成
Revesion History : 2004/06/16 李強(中訊)  取消確定             新規作成
Revesion History : 2004/06/17 李強(中訊)  新規建訂正注文送信    新規作成
Revesion History : 2004/06/17 李強(中訊)  返済訂正注文送信      新規作成
Revesion History : 2006/07/06 徐宏偉 (中訊) 【先物オプション】仕様変更モデル511
Revesion History : 2007/01/25 徐大方 (中訊) 仕様変更588,595,597,598,602,603,607
Revesion History : 2007/03/27 徐大方 (中訊) 障害対応U02998
Revesion History : 2007/03/27 徐大方 (中訊) ＤＢ更新仕様165
Revesion History : 2007/03/28 徐大方 (中訊) 障害対応U02999
Revesion History : 2007/06/08 孟亜南 (中訊) 仕様変更モデルNo.657 No.664 No.698
*/
package webbroker3.ifo.marketadaptor;

import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProductManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSendResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TooLateException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotInstalledException;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoMarketRequestSenderService;
import com.fitechlabs.xtrade.plugin.tc.xbifo.market.messages.IfoChangeOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.xbifo.market.messages.IfoChangeSettleContractOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.xbifo.market.messages.IfoOpenContractOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.xbifo.market.messages.IfoSettleContractOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoProductImpl;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoOrderImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.DefaultMarketRequestSendResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.CancelOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.MarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3AllOrderChangeDivDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3FrontOrderTradeCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3HostStatusDef;
import webbroker3.common.define.WEB3OrderDateDivDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.common.define.WEB3SonarExecutionConditionDef;
import webbroker3.common.define.WEB3CancelDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.mqgateway.WEB3MQGatewayService;
import webbroker3.mqgateway.WEB3MQMessageSpec;
import webbroker3.mqgateway.WEB3MQSendResult;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderSwitching;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.ifo.data.HostFotypeOrderAllParams;
import webbroker3.ifo.data.HostFotypeOrderAllRow;
import webbroker3.ifo.define.WEB3IfoProductTypeDef;
import webbroker3.ifo.service.delegate.WEB3IfoFrontOrderService;
import webbroker3.ifo.WEB3IfoAcceptedUpdateInterceptor;
import webbroker3.ifo.WEB3IfoChangeConfirmUpdateInterceptor;
import webbroker3.ifo.WEB3IfoCancelConfirmUpdateInterceptor;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;

/**
 * (先物OP市場リクエスト送信サービス )<BR>
 *
 * @@author 李強
 * @@version 1.0
 */
public class WEB3IfoMarketRequestSenderServiceImpl implements IfoMarketRequestSenderService
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoMarketRequestSenderServiceImpl.class);

    /**
     * (返済注文送信)<BR>
     * <BR>
     * （send(IfoSettleContractOrderMarketRequestMessage)の実装）<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物OP市場ﾘｸｴｽﾄ）返済注文送信」参照。<BR>
     * @@param l_request - 返済注文リクエストメッセージ<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSendResult
     * @@roseuid 40611D9201E3
     */
    public MarketRequestSendResult send(
        IfoSettleContractOrderMarketRequestMessage l_request) //cannot throw ToolateException
    {
        final String METHOD_NAME = "send(IfoSettleContractOrderMarketRequestMessage)";
        log.entering(METHOD_NAME);

        try
        {
            if (l_request == null)
            {
                log.error("parameter is null type");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + METHOD_NAME);
            }

            String l_strInstitutionCode = null;//証券会社コード

            Order l_order = null;
            IfoOrderUnitRow l_ifoOrderUnitRow = null;
            boolean l_isTriggerIssue = false;
            String l_strOrderConditionType = null;
            long l_lngMsgTokenId = 0;
            long l_lngOrderId = l_request.getOrderId();

            IfoProductImpl l_ifoProductImpl = null;
            WEB3MQMessageSpec l_web3MQMessageSpec = null;
            WEB3MQGatewayService l_web3MQGatewayService;
            WEB3MQSendResult l_web3MQSendResult;
            ProcessingResult l_processingResult;

            TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.IFO);
            IfoProductManager l_ifoProductManager =
                (IfoProductManager) l_tradingModule.getProductManager();

            //注文取得
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();
            l_order = (IfoOrderImpl) l_orderMgr.getOrder(l_lngOrderId);

            OrderUnit[] l_orderUnits = l_order.getOrderUnits();
            OrderUnit l_orderUnit = l_orderUnits[0];

            l_ifoOrderUnitRow =
                (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();
           
            //(*1) 注文通知（注文単位.注文経路区分 == ”HOST”）の場合のみ処理実施。
            if (WEB3OrderRootDivDef.HOST.equals(l_ifoOrderUnitRow.getOrderRootDiv()))
            {
                //インタセプタを生成する。
                WEB3IfoAcceptedUpdateInterceptor l_interceptor =
                    new WEB3IfoAcceptedUpdateInterceptor(WEB3ErrorReasonCodeDef.NORMAL, null);
                
                //インタセプタをOrderManagerにセットする。
                l_orderMgr.setThreadLocalPersistenceEventInterceptor(l_interceptor);
                
                //受付結果（受付成功）オブジェクトを生成する。
                DefaultNewOrderAcceptedMarketResponseMessage l_newAcceptedResponseMessage =
                    new DefaultNewOrderAcceptedMarketResponseMessage(l_lngOrderId);
                    
                MarketAdapter l_markertAdapter = l_tradingModule.getMarketAdapter();
                
                IfoMarketResponseReceiverCallbackService l_callBackService =
                (IfoMarketResponseReceiverCallbackService) l_markertAdapter.getMarketResponseReceiverCallbackService();
                
                //受付完了を注文に更新する。
                l_processingResult = l_callBackService.process(l_newAcceptedResponseMessage);
                
                if (l_processingResult.isFailedResult())
                {
                    return DefaultMarketRequestSendResult.newFailedResultInstance(
                        l_processingResult);
                }
                return DefaultMarketRequestSendResult.newSuccessResultInstance(
                            l_lngMsgTokenId);
            }

            //notifyルールエンジンサーバ()
            l_orderMgr.notifyRLS(
                (IfoOrderUnit)l_orderUnit,
                OrderManagerPersistenceContext.NEW_SETTLE_CONTRACT_ORDER);

            //発注条件を取得
            l_strOrderConditionType = l_ifoOrderUnitRow.getOrderConditionType();

            //(*1) 逆指値注文の場合は処理終了
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType))
            {
                log.debug("逆指値注文の場合は処理終了");
                log.exiting(METHOD_NAME);
                return DefaultMarketRequestSendResult.newSuccessResultInstance(
                            l_lngMsgTokenId);
            }

            //insert返済注文キュー()
            l_orderMgr.insertSettleContractHostOrder(l_lngOrderId);
            
            //銘柄オブジェクトを取得する
            l_ifoProductImpl = (IfoProductImpl) l_ifoProductManager.getProduct(
                                                    l_ifoOrderUnitRow.getProductId());

            //isトリガ発行()をコール
            l_isTriggerIssue =
                WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(
                    l_strOrderConditionType);

            l_strInstitutionCode = l_ifoProductImpl.getInstitution().getInstitutionCode();
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_finOjbectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            WEB3GentradeMarket l_market =
                (WEB3GentradeMarket)l_finOjbectManager.getMarket(l_ifoOrderUnitRow.getMarketId());
            String l_strMarketCode = l_market.getMarketCode();

            //WEB3IfoOrderService
            WEB3IfoFrontOrderService l_orderService =
                (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);

            HostFotypeOrderAllParams l_orderAllParams =
                l_orderService.getHostFotypeOrderAll((IfoOrderUnit)l_orderUnit);

            //isMQトリガ発行経路()
            //MQトリガ発行要否を取得する。
            //[isMQトリガ発行経路()に指定する引数]
            //証券会社コード：　@注文単位.証券会社IDに該当する証券会社コード
            //銘柄タイプ：　@注文単位.銘柄タイプ
            //市場コード：　@注文単位.市場IDに該当する市場コード
            //発注経路区分：　@注文単位.発注経路区分
            //フロント発注システム区分：　@先物OP注文取引キュー.フロント発注システム区分
            boolean l_blnIsSubmitMQTriggerEnable = WEB3GentradeOrderSwitching.isSubmitMQTriggerEnable(
                l_strInstitutionCode,
                l_ifoOrderUnitRow.getProductType(),
                l_strMarketCode,
                l_ifoOrderUnitRow.getSubmitOrderRouteDiv(),
                l_orderAllParams.getFrontOrderSystemCode());
            //トリガー発行時間帯&&MQトリガ発行経路の場合のみ処理実施
            if (l_isTriggerIssue && l_blnIsSubmitMQTriggerEnable)
            {
                String l_strMQDataCode =
                    l_orderService.getOrderMQDataCode((IfoOrderUnit)l_orderUnit);
                if (l_strMQDataCode == null)
                {
                    log.exiting(METHOD_NAME);
                    return DefaultMarketRequestSendResult.newSuccessResultInstance(
                        l_lngMsgTokenId);
                }
                //WEB3MQMessageSpec(証券会社コード : String, データコード : String)
                //WEB3MQMessageSpecを生成する。
                //[コンストラクタに指定する引数]
                //証券会社コード：
                //　@注文単位.証券会社IDに該当する証券会社コード
                //データコード：
                //　@先物OP発注サービス.get発注時MQデータコード()の戻り値
                l_web3MQMessageSpec =
                    new WEB3MQMessageSpec(
                        l_strInstitutionCode,
                        l_strMQDataCode);

                //MQサービスを取得する
                l_web3MQGatewayService =
                    (WEB3MQGatewayService) Services.getService(
                        WEB3MQGatewayService.class);

                //send(MQメッセージ内容 : WEB3MQMessageSpec)
                l_web3MQSendResult =
                    l_web3MQGatewayService.send(l_web3MQMessageSpec);

                if (l_web3MQSendResult.isSuccessResult())
                {
                    log.exiting(METHOD_NAME);
                    return DefaultMarketRequestSendResult.newSuccessResultInstance(l_lngMsgTokenId);
                }
                else
                {
                    l_processingResult =
                        ProcessingResult.newFailedResultInstance(
                            l_web3MQSendResult.getErrorInfo());

                    log.exiting(METHOD_NAME);
                    return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
                }
            }
        }
        catch (NotFoundException l_nfe)
        {
            WEB3SystemLayerException l_sysException = new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);

            log.error("__an unexpected error__",l_sysException);
            ProcessingResult l_processingResult = ProcessingResult.newFailedResultInstance(
                l_sysException.getErrorInfo());

            return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
        }
        catch (WEB3BaseException l_wbe)
        {

            log.error("__an unexpected error__",l_wbe);
            ProcessingResult l_processingResult = ProcessingResult.newFailedResultInstance(
                    l_wbe.getErrorInfo());

            return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
        }

        log.exiting(METHOD_NAME);
        return DefaultMarketRequestSendResult.newSuccessResultInstance(0L);
    }

    /**
     * (新規建注文送信)<BR>
     * <BR>
     * （send(IfoOpenContractOrderMarketRequestMessage)の実装）<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物OP市場ﾘｸｴｽﾄ）新規建注文送信」参照。<BR>
     * @@param l_request - (新規建注文リクエストメッセージ)<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSendResult
     * @@roseuid 40611E250389
     */
    public MarketRequestSendResult send(
        IfoOpenContractOrderMarketRequestMessage l_request) //cannot throw ToolateException
    {
        final String METHOD_NAME = "send(IfoOpenContractOrderMarketRequestMessage)";
        log.entering(METHOD_NAME);

        try
        {
            if (l_request == null)
            {
                log.error("parameter is null type");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + METHOD_NAME);
            }

            String l_strInstitutionCode = null;//証券会社コード

            IfoOrderImpl l_order = null;
            IfoOrderUnitRow l_ifoOrderUnitRow = null;
            boolean l_isTriggerIssue = false;
            String l_strOrderConditionType = null;
            long l_lngMsgTokenId = 0;
            long l_lngOrderId = l_request.getOrderId();

            WEB3MQMessageSpec l_web3MQMessageSpec = null;
            WEB3MQGatewayService l_web3MQGatewayService;
            WEB3MQSendResult l_web3MQSendResult;
            ProcessingResult l_processingResult;

            TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.IFO);
            IfoProductManager l_ifoProductManager =
                (IfoProductManager) l_tradingModule.getProductManager();

            IfoProductImpl l_ifoProductImpl = null;

            //注文取得
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();
            l_order = (IfoOrderImpl) l_orderMgr.getOrder(l_lngOrderId);

            OrderUnit[] l_orderUnits = l_order.getOrderUnits();
            OrderUnit l_orderUnit = l_orderUnits[0];

            l_ifoOrderUnitRow =
                (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();

            //(*1) 注文通知（注文単位.注文経路区分 == ”HOST”）の場合のみ処理実施。
            if (WEB3OrderRootDivDef.HOST.equals(l_ifoOrderUnitRow.getOrderRootDiv()))
            {
                //インタセプタを生成する。
                WEB3IfoAcceptedUpdateInterceptor l_interceptor =
                    new WEB3IfoAcceptedUpdateInterceptor(WEB3ErrorReasonCodeDef.NORMAL, null);
                
                //インタセプタをOrderManagerにセットする。
                l_orderMgr.setThreadLocalPersistenceEventInterceptor(l_interceptor);
                
                //受付結果（受付成功）オブジェクトを生成する。
                DefaultNewOrderAcceptedMarketResponseMessage l_newResponseMessage =
                    new DefaultNewOrderAcceptedMarketResponseMessage(l_lngOrderId);
                    
                MarketAdapter l_markertAdapter = l_tradingModule.getMarketAdapter();
                
                IfoMarketResponseReceiverCallbackService l_callBackService =
                (IfoMarketResponseReceiverCallbackService) l_markertAdapter.getMarketResponseReceiverCallbackService();
                
                //受付完了を注文に更新する。
                l_processingResult = l_callBackService.process(l_newResponseMessage);
                
                if (l_processingResult.isFailedResult())
                {
                    return DefaultMarketRequestSendResult.newFailedResultInstance(
                        l_processingResult);
                }
                return DefaultMarketRequestSendResult.newSuccessResultInstance(
                            l_lngMsgTokenId);
            }

            //notifyルールエンジンサーバ()
            l_orderMgr.notifyRLS(
                (IfoOrderUnit)l_orderUnit,
                OrderManagerPersistenceContext.NEW_OPEN_CONTRACT_ORDER);

            //発注条件を取得
            //(*1) 逆指値注文（注文単位.発注条件 == ”逆指値”）の場合は処理終了
            l_strOrderConditionType = l_ifoOrderUnitRow.getOrderConditionType();

            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType))
            {
                log.debug("逆指値注文の場合は処理終了");
                log.exiting(METHOD_NAME);
                return DefaultMarketRequestSendResult.newSuccessResultInstance(
                            l_lngMsgTokenId);
            }

            //insert新規建注文キュー()
            l_orderMgr.insertOpenContractHostOrder(l_lngOrderId);

            l_ifoProductImpl = (IfoProductImpl) l_ifoProductManager.getProduct(
                                    l_ifoOrderUnitRow.getProductId());

            //isトリガ発行()をコール
            l_isTriggerIssue =
                WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(
                    l_strOrderConditionType);

            l_strInstitutionCode = l_ifoProductImpl.getInstitution().getInstitutionCode();
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_finOjbectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            WEB3GentradeMarket l_market =
                (WEB3GentradeMarket)l_finOjbectManager.getMarket(l_ifoOrderUnitRow.getMarketId());
            String l_strMarketCode = l_market.getMarketCode();

            //WEB3IfoOrderService
            WEB3IfoFrontOrderService l_orderService =
                (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);

            HostFotypeOrderAllParams l_orderAllParams =
                l_orderService.getHostFotypeOrderAll((IfoOrderUnit)l_orderUnit);

            //isMQトリガ発行経路()
            //MQトリガ発行要否を取得する。
            //[isMQトリガ発行経路()に指定する引数]
            //証券会社コード：　@注文単位.証券会社IDに該当する証券会社コード
            //銘柄タイプ：　@注文単位.銘柄タイプ
            //市場コード：　@注文単位.市場IDに該当する市場コード
            //発注経路区分：　@注文単位.発注経路区分
            //フロント発注システム区分：　@先物OP注文取引キュー.フロント発注システム区分
            boolean l_blnIsSubmitMQTriggerEnable = WEB3GentradeOrderSwitching.isSubmitMQTriggerEnable(
                l_strInstitutionCode,
                l_ifoOrderUnitRow.getProductType(),
                l_strMarketCode,
                l_ifoOrderUnitRow.getSubmitOrderRouteDiv(),
                l_orderAllParams.getFrontOrderSystemCode());
            //トリガー発行時間帯&&MQトリガ発行経路の場合のみ処理実施
            if (l_isTriggerIssue && l_blnIsSubmitMQTriggerEnable)
            {
                String l_strMQDataCode =
                    l_orderService.getOrderMQDataCode((IfoOrderUnit)l_orderUnit);
                if (l_strMQDataCode == null)
                {
                    log.exiting(METHOD_NAME);
                    return DefaultMarketRequestSendResult.newSuccessResultInstance(
                        l_lngMsgTokenId);
                }
                //WEB3MQMessageSpec(証券会社コード : String, データコード : String)
                //WEB3MQMessageSpecを生成する。
                //[コンストラクタに指定する引数]
                //証券会社コード：
                //　@注文単位.証券会社IDに該当する証券会社コード
                //データコード：
                //　@先物OP発注サービス.get発注時MQデータコード()の戻り値
                l_web3MQMessageSpec =
                    new WEB3MQMessageSpec(
                        l_strInstitutionCode,
                        l_strMQDataCode);

                //MQサービスを取得する
                l_web3MQGatewayService =
                    (WEB3MQGatewayService) Services.getService(
                        WEB3MQGatewayService.class);

                //send(MQメッセージ内容 : WEB3MQMessageSpec)
                l_web3MQSendResult =
                    l_web3MQGatewayService.send(l_web3MQMessageSpec);

                if (l_web3MQSendResult.isSuccessResult())
                {
                    log.exiting(METHOD_NAME);
                    return DefaultMarketRequestSendResult.newSuccessResultInstance(l_lngMsgTokenId);
                }
                else
                {
                    l_processingResult =
                        ProcessingResult.newFailedResultInstance(
                            l_web3MQSendResult.getErrorInfo());

                    log.exiting(METHOD_NAME);
                    return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
                }
            }
        }
        catch (NotFoundException l_nfe)
        {
            WEB3SystemLayerException l_sysException = new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);

            log.error("__an unexpected error__",l_sysException);
            ProcessingResult l_processingResult = ProcessingResult.newFailedResultInstance(
                l_sysException.getErrorInfo());

            return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
        }
        catch (WEB3BaseException l_wbe)
        {
            log.error("__an unexpected error__",l_wbe);
            ProcessingResult l_processingResult = ProcessingResult.newFailedResultInstance(
                    l_wbe.getErrorInfo());

            return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
        }

        log.exiting(METHOD_NAME);
        return DefaultMarketRequestSendResult.newSuccessResultInstance(0L);
    }

    /**
     * (返済訂正注文送信)<BR>
     * <BR>
     * （send(IfoChangeSettleContractOrderMarketRequestMessage,
     * <BR>boolean) の実装） <BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物OP市場ﾘｸｴｽﾄ）返済訂正注文送信」参照。<BR>
     * @@param l_request - 返済注文訂正リクエストメッセージ<BR>
     * @@param l_blnIsMarketNotSendMessage - (is市場未送信)<BR>
     * 原注文が市場未送信の場合はtrue、原注文が市場送信済の場合<BR>
     * はfalseを指定する。<BR>
     * falseの場合、SONARへ変更を通知する。<BR>
     *
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSendResult
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.TooLateException
     * @@roseuid 40611EBB01E3
     */
    public MarketRequestSendResult send(
        IfoChangeSettleContractOrderMarketRequestMessage l_request,
        boolean l_blnIsMarketNotSendMessage)
        throws TooLateException
    {
        final String METHOD_NAME = "send(IfoChangeSettleContractOrderMarketRequestMessage,boolean)";
        log.entering(METHOD_NAME);

        try
        {
            if (l_request == null)
            {
                log.error("parameter is null type");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + METHOD_NAME);
            }

            String ORDER_REQUEST_CODE       = WEB3HostRequestCodeDef.OPTION_ORDER_CHANGE_CANCEL;// リクエストデータコード
            String l_strStatus = WEB3StatusDef.NOT_DEAL;//処理区分 "0：未処理"
            String l_strInstitutionCode     = null;//証券会社コード
            String l_strBranchCode          = null;//部店コード
            String l_strTraderCode          = null;//扱者コード
            String l_strOrderRequestNumber  = null;//識別コード
            double l_dblSellOrderQuantity  = 0;//売付数量
            double l_dblBuyOrderQuantity   = 0;//買付数量
            double l_dblSellOrderQuantity1  = 0;//売付数量
            double l_dblBuyOrderQuantity1   = 0;//買付数量
            double l_dblLimitPrice         = 0;//指値
            String l_strExecutionCondition  = null;//執行条件
            String l_strConfirmedExecutionCondition  = null;//執行条件
            String l_strOrderConditionType  = null;//発注条件
            Timestamp l_tsCreateDateTime    = null;//受注日時
            String l_strAccountCode         = null;//顧客コード
            String l_strOrderDateDiv        = null;//受注日区分
            String l_strProductCode         = null;//銘柄コード
            double l_dblQuantity           = 0;//訂正数量
            String l_strCancelDiv           = null;//取消区分
            String l_strFuturesOptionDiv    = null;
            int l_intOrderActionSerialNo = 0;//注文履歴番号
            String  l_strOrder_action_serial_no =null;
            String l_strFutureOptionProductType = null;
            boolean l_blnIsInsert = false;
           
            Order l_order = null;
            IfoOrderUnitRow l_ifoOrderUnitRow = null;
            boolean l_isTriggerIssue = false;
            long l_lngMsgTokenId = 0;
            long l_lngOrderId = l_request.getOrderId();

            WEB3MQMessageSpec l_web3MQMessageSpec = null;
            WEB3MQGatewayService l_web3MQGatewayService;
            WEB3MQSendResult l_web3MQSendResult;
            ProcessingResult l_processingResult;

            AccountManager l_accountManager =GtlUtils.getAccountManager();
            TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.IFO);
            IfoProductManager l_ifoProductManager =
                (IfoProductManager) l_tradingModule.getProductManager();

            IfoProductImpl l_ifoProductImpl = null;

            //注文取得
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();
            l_order = (IfoOrderImpl) l_orderMgr.getOrder(l_lngOrderId);

            OrderUnit[] l_orderUnits = l_order.getOrderUnits();
            OrderUnit l_orderUnit = l_orderUnits[0];

            l_ifoOrderUnitRow =
                (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();

            //notifyルールエンジンサーバ()
            if (l_blnIsMarketNotSendMessage)
            {
                l_orderMgr.notifyRLS(
                    (IfoOrderUnit)l_orderUnit,
                    OrderManagerPersistenceContext.MODIFY_ORDER_CONFIRMED_BY_MKT);
            }
            else
            {
                l_orderMgr.notifyRLS(
                    (IfoOrderUnit)l_orderUnit,
                    OrderManagerPersistenceContext.MODIFY_ORDER_ACCEPTED);
            }

            //対象データを取得する----------------------------[START]
            //先物オプション区分を取得
            l_strFuturesOptionDiv = l_ifoOrderUnitRow.getFutureOptionDiv();
            
            //throw NotFoundException
            //銘柄を取得する
            l_ifoProductImpl = (IfoProductImpl) l_ifoProductManager.getProduct(
                                    l_ifoOrderUnitRow.getProductId());
            IfoProductRow l_ifoProductRow = (IfoProductRow)l_ifoProductImpl.getDataSourceObject();

            //部店を取得する
            Branch l_banch =
                   l_accountManager.getBranch(l_ifoOrderUnitRow.getBranchId());
            //証券会社コードを取得する
            l_strInstitutionCode =
                       l_banch.getInstitution().getInstitutionCode();
            //部店コードを取得する
            l_strBranchCode = l_banch.getBranchCode();
            //顧客コードを取得する
            l_strAccountCode =
                l_accountManager.getMainAccount(
                    l_ifoOrderUnitRow.getAccountId()).getAccountCode();
            //扱者コードを取得する
            l_strTraderCode = l_ifoOrderUnitRow.getSonarTraderCode();
            //識別コードを取得する
            l_strOrderRequestNumber =
                    l_ifoOrderUnitRow.getOrderRequestNumber();
            //銘柄コードを取得する
            l_strProductCode = l_ifoProductImpl.getProductCode();
            
            //買付/売付数量を取得する
            //訂正数量を取得する
            l_dblQuantity = l_ifoOrderUnitRow.getQuantity();
            //買付/売付数量を取得する
            OrderTypeEnum l_orderType = l_orderUnit.getOrderType();
            if (OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE.equals(l_orderType) || 
                OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE.equals(l_orderType))
                //608：OP買建返済注文（売返済）/604：先物買建返済注文（売返済）
            {   //売付の場合
                l_dblSellOrderQuantity = l_ifoOrderUnitRow.getConfirmedQuantity();
                l_dblSellOrderQuantity1 = l_ifoOrderUnitRow.getQuantity();
            }
            else if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE.equals(l_orderType) ||
                OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE.equals(l_orderType))
                //607：OP買建返済注文（買返済）/603：先物買建返済注文（買返済）
            {
               //買付の場合
               l_dblBuyOrderQuantity = l_ifoOrderUnitRow.getConfirmedQuantity();
               l_dblBuyOrderQuantity1 = l_ifoOrderUnitRow.getQuantity();
            }

            //指値を取得する
            l_dblLimitPrice = l_ifoOrderUnitRow.getLimitPrice();
            //執行条件を取得する
            IfoOrderExecutionConditionType l_ifoExecutionConditionType =
                    l_ifoOrderUnitRow.getExecutionConditionType();
            if (IfoOrderExecutionConditionType.AT_MARKET_OPEN.equals(
                    l_ifoExecutionConditionType))
            {
                //寄付
                l_strExecutionCondition =
                    WEB3SonarExecutionConditionDef.AT_MARKET_OPEN;
            }
            else if (IfoOrderExecutionConditionType.AT_MARKET_CLOSE.equals(
                l_ifoExecutionConditionType))
            {
                //引け
                l_strExecutionCondition =
                    WEB3SonarExecutionConditionDef.AT_MARKET_CLOSE;
            }
            else if (IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.equals(
                    l_ifoExecutionConditionType))
            {
                //出来ずば引成(不成)
                l_strExecutionCondition =
                    WEB3SonarExecutionConditionDef.NO_EXECUTED_MARKET_ORDER;
            }
            else if (!l_ifoOrderUnitRow.getFirstOrderUnitIdIsNull())
            {
                // 出来るまで注文(注文単位.初回注文の注文単位ID≠null)の場合、２：出合。
                l_strExecutionCondition = WEB3SonarExecutionConditionDef.COME_TO_TERMS;
            }
            else
            {
                //無条件
                l_strExecutionCondition =
                    WEB3SonarExecutionConditionDef.UNCONDITIONDNESS;
            }

            l_ifoExecutionConditionType =
                l_ifoOrderUnitRow.getConfirmedExecConditionType();
            if (IfoOrderExecutionConditionType.AT_MARKET_OPEN.equals(
                l_ifoExecutionConditionType))
            {
                //寄付
                l_strConfirmedExecutionCondition =
                    WEB3SonarExecutionConditionDef.AT_MARKET_OPEN;
            }
            else if (IfoOrderExecutionConditionType.AT_MARKET_CLOSE.equals(
                l_ifoExecutionConditionType))
            {
                //引け
                l_strConfirmedExecutionCondition =
                    WEB3SonarExecutionConditionDef.AT_MARKET_CLOSE;
            }
            else if (IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.equals(
                l_ifoExecutionConditionType))
            {
                //出来ずば引成(不成)
                l_strConfirmedExecutionCondition =
                    WEB3SonarExecutionConditionDef.NO_EXECUTED_MARKET_ORDER;
            }
            else if (!l_ifoOrderUnitRow.getFirstOrderUnitIdIsNull())
            {
                // 出来るまで注文(注文単位.初回注文の注文単位ID≠null)の場合、２：出合。
                l_strConfirmedExecutionCondition = WEB3SonarExecutionConditionDef.COME_TO_TERMS;
            }
            else
            {
                //無条件
                l_strConfirmedExecutionCondition =
                    WEB3SonarExecutionConditionDef.UNCONDITIONDNESS;
            }
            //発注条件を取得する
            l_strOrderConditionType = l_ifoOrderUnitRow.getOrderConditionType();

            //受注日時に現在時刻を設定する
            l_tsCreateDateTime = GtlUtils.getSystemTimestamp();
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            String l_strCreateDate = l_format.format(l_tsCreateDateTime);

            //受注日区分を取得する
            l_strOrderDateDiv = WEB3OrderDateDivDef.YESTERDAY;
            if (l_ifoOrderUnitRow.getBizDate().compareTo(l_strCreateDate) == 0)
            {
                l_strOrderDateDiv = WEB3OrderDateDivDef.TODAY;

            }

            //注文履歴最終通番を取得する
            l_intOrderActionSerialNo =
                    l_ifoOrderUnitRow.getLastOrderActionSerialNo();
            l_strOrder_action_serial_no = String.valueOf(l_intOrderActionSerialNo);
            
            //先物オプション商品を取得する
            if (IfoDerivativeTypeEnum.FUTURES.equals(l_ifoProductImpl.getDerivativeType()))
            {
                l_strFutureOptionProductType = WEB3IfoProductTypeDef.FUTURES;
            }
            else if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_ifoProductImpl.getDerivativeType()))
            {
                l_strFutureOptionProductType = WEB3IfoProductTypeDef.PUT_OPTIONS;
            }
            else if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_ifoProductImpl.getDerivativeType()))
            {
                l_strFutureOptionProductType = WEB3IfoProductTypeDef.CALL_OPTIONS;
            }

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_finOjbectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            WEB3GentradeMarket l_market =
                (WEB3GentradeMarket)l_finOjbectManager.getMarket(l_ifoOrderUnitRow.getMarketId());
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            WEB3IfoFrontOrderService l_orderService =
                (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);

            //データコードを取得する
            if (l_strFuturesOptionDiv.equals(WEB3FuturesOptionDivDef.FUTURES))
            {
                ORDER_REQUEST_CODE = WEB3HostRequestCodeDef.FUTURES_ORDER_CHANGE_CANCEL;
            }

            //対象データを取得する----------------------------[END]

            //(*1) 市場未送信注文の場合のみ処理実施
            if (l_blnIsMarketNotSendMessage)
            {
                //逆指値注文の場合、処理終了 
                if(WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType) 
                    && !WEB3RequestTypeDef.QUOTE_SERVER.equals(l_ifoOrderUnitRow.getRequestType()))
                {
                    log.debug("逆指値注文の場合は処理終了");
                    log.exiting(METHOD_NAME);
                    return DefaultMarketRequestSendResult.newSuccessResultInstance(l_lngMsgTokenId);
                }
                
                //証券会社、部店、識別コード、社内処理項目、処理区分で先OP返済訂正_先OP注文取引キューテーブル検索
                //throw DataFindException, DataNetworkException, DataQueryException
                try
                {
                    l_queryProcessor = Processors.getDefaultProcessor();
                    int l_intIndex = l_orderService.getIndexOfOrderRevInCorpCode();
                    int l_intFigure = l_orderService.getFigureOfOrderRev();
                    StringBuffer l_sbWhereOrder = new StringBuffer();
                    l_sbWhereOrder.append(" institution_code = ? "); 
                    l_sbWhereOrder.append(" and branch_code = ? ");
                    l_sbWhereOrder.append(" and order_request_number = ? ");
                    l_sbWhereOrder.append(" and substr(corp_code," + l_intIndex + "," + l_intFigure + ")=? ");
                    l_sbWhereOrder.append(" and status = ? ");
            
                    Object[] l_objWhereOrder = { 
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strOrderRequestNumber,
                        l_ifoOrderUnitRow.getOrderRev(),
                        WEB3HostStatusDef.NOT_STARTED_PROCESS};

                    List l_lisSearchResult =
                        l_queryProcessor.doFindAllQuery(
                            HostFotypeOrderAllRow.TYPE,
                            l_sbWhereOrder.toString(),
                            null,
                            "FOR UPDATE",
                            l_objWhereOrder);

                    int l_intUpdateCnt = 0;
                    if (l_lisSearchResult.isEmpty() || l_lisSearchResult.size() == 0)
                    {
                        return DefaultMarketRequestSendResult
                            .newFailedResultInstance(
                                ProcessingResult.newFailedResultInstance(
                                    WEB3ErrorCatalog.BUSINESS_ERROR_00010));
                    }
                    else
                    {
                        l_intUpdateCnt = l_lisSearchResult.size();
                    }
                    log.debug("updated rows count:" + l_intUpdateCnt + "  rows");

                    Map l_mapChanges = new HashMap();
                    l_mapChanges.put("order_action_serial_no", l_strOrder_action_serial_no);
                    l_mapChanges.put("submit_order_route_div", l_ifoOrderUnitRow.getSubmitOrderRouteDiv());
                    l_mapChanges.put("buy_order_quantity", new Double(l_dblBuyOrderQuantity1));
                    l_mapChanges.put("sell_order_quantity", new Double(l_dblSellOrderQuantity1));
                    l_mapChanges.put("limit_price", new Double(l_dblLimitPrice));
                    l_mapChanges.put("execution_condition", l_strExecutionCondition);
                    l_mapChanges.put("last_updated_timestamp", l_tsCreateDateTime);

                    //一致する行の内容を訂正しDBに更新する。
                    if (l_intUpdateCnt > 0)
                    {
                        l_queryProcessor.doUpdateAllQuery(
                            HostFotypeOrderAllRow.TYPE,
                            l_sbWhereOrder.toString(),
                            l_objWhereOrder,
                            l_mapChanges);
                    }

                    log.exiting(METHOD_NAME);
                    return DefaultMarketRequestSendResult.newSuccessResultInstance(l_lngMsgTokenId);
                }
                catch (DataException l_de)
                {
                    WEB3SystemLayerException l_sysException = new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + METHOD_NAME,
                        l_de.getMessage(),
                        l_de);

                    log.error("__an unexpected error__",l_sysException);
                    l_processingResult = ProcessingResult.newFailedResultInstance(
                        l_sysException.getErrorInfo());

                    return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
                }
            }

            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();

            // is内容通知済注文
            boolean l_blnIsNotifyEndOrder = l_orderManager.isNotifyEndOrder(l_orderUnit);

            // is市場開局時間帯
            boolean l_blnIsOpenTimeZone = WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone();
            
            // is取引所休憩時間帯
            boolean l_blnIsTradeCloseTimeZone =
                WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone();

            //トリガー発行あり
            boolean l_blnIsSubmitMarketTrigger =
                WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(l_strOrderConditionType);

            //（*2）（休憩時間帯）　@or　@（市場通知要の訂正注文　@&&　@トリガー発行あり）の場合
            if (l_blnIsTradeCloseTimeZone || (!l_blnIsNotifyEndOrder && l_blnIsSubmitMarketTrigger))
            {
                HostFotypeOrderAllParams l_hostFotypeOrderAllParams = null;

                //休憩時間帯の場合
                if (l_blnIsTradeCloseTimeZone)
                {
                    //get先物OP注文取引キュー(IfoOrderUnit)
                    //先物OP注文取引キューを取得する。
                    //[先物OP発注サービス.get先物OP注文取引キュー()に指定する引数]
                    //注文単位：　@注文単位
                    l_hostFotypeOrderAllParams =
                        l_orderService.getHostFotypeOrderAll((IfoOrderUnit)l_orderUnit);

                    //キューデータ存在しない場合
                    if (l_hostFotypeOrderAllParams == null)
                    {
                        //市場通知不要の場合、訂正確定(注文単位:OrderUnit)を行う
                        if (l_orderManager.isNotifyEndOrder(l_orderUnit))
                        {
                            //訂正確定
                            this.updateOrderModified(l_orderUnit);

                            //処理を終了する
                            log.exiting(METHOD_NAME);
                            return DefaultMarketRequestSendResult.newSuccessResultInstance(
                                l_lngMsgTokenId);
                        }

                        //市場通知要の場合、キューデータinsert
                        else
                        {
                            l_blnIsInsert = true;
                        }
                    }
                    //キューデータ存在する場合、キューデータupdate
                    else
                    {
                        try
                        {
                            QueryProcessor l_processor = Processors.getDefaultProcessor();

                            //受注日時
                            l_hostFotypeOrderAllParams.setReceivedDateTime(l_tsCreateDateTime);
                            //set注文履歴番号
                            l_hostFotypeOrderAllParams.setOrderActionSerialNo(l_intOrderActionSerialNo);
                            //set発注経路区分
                            l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv(l_ifoOrderUnitRow.getSubmitOrderRouteDiv());
                            //訂正数量
                            l_hostFotypeOrderAllParams.setChangeQuantity(l_dblQuantity);
                            //set指値
                            l_hostFotypeOrderAllParams.setChangeLimitPrice(l_dblLimitPrice);
                            //set執行条件
                            l_hostFotypeOrderAllParams.setChangeExecutionCondition(l_strExecutionCondition);
                            //set更新日付
                            l_hostFotypeOrderAllParams.setLastUpdatedTimestamp(l_tsCreateDateTime);

                            l_processor.doUpdateQuery(l_hostFotypeOrderAllParams);
                        }
                        catch (DataException l_ex)
                        {
                            WEB3SystemLayerException l_sysException =
                                new WEB3SystemLayerException(
                                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                                    this.getClass().getName() + "." + METHOD_NAME,
                                    l_ex.getMessage(),
                                    l_ex);
    
                            log.error("__an unexpected error__",l_sysException);
                            l_processingResult = ProcessingResult.newFailedResultInstance(
                                l_sysException.getErrorInfo());
    
                            return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
                        }
                    }
                }
                else
                {
                    l_blnIsInsert = true;
                }
                if (l_blnIsInsert)
                {
                    l_hostFotypeOrderAllParams =
                        new HostFotypeOrderAllParams();

                    //データコード
                    l_hostFotypeOrderAllParams.setRequestCode(ORDER_REQUEST_CODE);
                    //口座ＩＤ
                    l_hostFotypeOrderAllParams.setAccountId(l_orderUnit.getAccountId());
                    //証券会社コード
                    l_hostFotypeOrderAllParams.setInstitutionCode(l_strInstitutionCode);
                    //部店コード
                    l_hostFotypeOrderAllParams.setBranchCode(l_strBranchCode);
                    //顧客コード
                    l_hostFotypeOrderAllParams.setAccountCode(l_strAccountCode);
                    //扱者コード
                    l_hostFotypeOrderAllParams.setTraderCode(l_strTraderCode);
                    //受注日区分
                    l_hostFotypeOrderAllParams.setReceivedDateTimeDiv(l_strOrderDateDiv);
                    //識別コード
                    l_hostFotypeOrderAllParams.setOrderRequestNumber(l_strOrderRequestNumber);
                    //市場コード（SONAR）
                    l_hostFotypeOrderAllParams.setSonarMarketCode(l_ifoOrderUnitRow.getSonarMarketCode());
                    //銘柄コード
                    l_hostFotypeOrderAllParams.setProductCode(l_strProductCode);
                    //受注日時
                    l_hostFotypeOrderAllParams.setReceivedDateTime(l_tsCreateDateTime);
                    //注文履歴番号
                    l_hostFotypeOrderAllParams.setOrderActionSerialNo(l_intOrderActionSerialNo);
                    //発注経路区分
                    l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv(
                        l_ifoOrderUnitRow.getSubmitOrderRouteDiv());
                    //原資産銘柄コード
                    l_hostFotypeOrderAllParams.setTargetProductCode(
                        l_ifoProductImpl.getUnderlyingProductCode());
                    //限月（年）
                    l_hostFotypeOrderAllParams.setDeliveryMonthYyyy(
                        l_ifoProductImpl.getMonthOfDelivery().substring(0, 4));
                    //限月（月）
                    l_hostFotypeOrderAllParams.setDeliveryMonthMm(
                        l_ifoProductImpl.getMonthOfDelivery().substring(4, 6));
                    //先物オプション商品
                    l_hostFotypeOrderAllParams.setFutureOptionProductType(l_strFutureOptionProductType);
                    //行使価格
                    l_hostFotypeOrderAllParams.setStrikePrice(l_ifoProductImpl.getStrikePrice());
                    //分割
                    l_hostFotypeOrderAllParams.setSplitType(l_ifoProductRow.getSplitType());
                    //売付数量
                    l_hostFotypeOrderAllParams.setSellOrderQuantity(l_dblSellOrderQuantity);
                    //買付数量
                    l_hostFotypeOrderAllParams.setBuyOrderQuantity(l_dblBuyOrderQuantity);
                    //指値
                    l_hostFotypeOrderAllParams.setLimitPrice(l_ifoOrderUnitRow.getConfirmedPrice());
                    //執行条件
                    l_hostFotypeOrderAllParams.setExecutionCondition(l_strConfirmedExecutionCondition);
                    //逆指値基準値
                    l_hostFotypeOrderAllParams.setStopOrderPrice(null);
                    //（W指値）訂正指値
                    l_hostFotypeOrderAllParams.setWLimitPrice(null);
                    //取引区分
                    l_hostFotypeOrderAllParams.setTransactionType(null);
                    //伝票№
                    l_hostFotypeOrderAllParams.setTicketNumber(null);
                    //建玉チェック
                    l_hostFotypeOrderAllParams.setContractCheck(null);
                    //注文チャネル
                    l_hostFotypeOrderAllParams.setOrderChanel(null);
                    //手数料№
                    l_hostFotypeOrderAllParams.setCommisionNumber(null);
                    //手数料№枝番
                    l_hostFotypeOrderAllParams.setCommisionBranchNumber(null);
                    //手数料商品コード
                    l_hostFotypeOrderAllParams.setCommisionProductCode(null);
                    //訂正数量
                    l_hostFotypeOrderAllParams.setChangeQuantity(l_dblQuantity);
                    //訂正指値
                    l_hostFotypeOrderAllParams.setChangeLimitPrice(l_dblLimitPrice);
                    //訂正執行条件
                    l_hostFotypeOrderAllParams.setChangeExecutionCondition(l_strExecutionCondition);
                    //訂正逆指値基準値
                    l_hostFotypeOrderAllParams.setChangeStopOrderPrice(null);
                    //訂正（W指値）訂正指値
                    l_hostFotypeOrderAllParams.setChangeWLimitPrice(null);
                    //取消区分
                    l_strCancelDiv = WEB3CancelDivDef.EXCEPT_CANCEL;
                    l_hostFotypeOrderAllParams.setCancelDiv(l_strCancelDiv);
                    //フロント発注取引所区分コード
                    l_hostFotypeOrderAllParams.setFrontOrderExchangeCode(
                        l_orderService.getFrontOrderExchangeCode(l_market.getMarketCode()));
                    //フロント発注システム区分
                    l_hostFotypeOrderAllParams.setFrontOrderSystemCode(
                        l_orderService.getFrontOrderSystemCode(l_market.getMarketCode()));
                    //フロント発注取引区分コード
                    l_hostFotypeOrderAllParams.setFrontOrderTradeCode(
                        WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE);
                    //自己委託区分
                    l_hostFotypeOrderAllParams.setTradeauditCode(null);
                    //社内処理項目
                    l_hostFotypeOrderAllParams.setCorpCode(
                        l_orderService.getCorpCode((IfoOrderUnit)l_orderUnit));
                    //（被訂正）社内処理項目
                    l_hostFotypeOrderAllParams.setOrgCorpCode(
                        l_orderService.getOrgCorpCode((IfoOrderUnit)l_orderUnit));
                    //仮想サーバNo.（JSOES）
                    l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes(null);
                    //市場発注No.
                    l_hostFotypeOrderAllParams.setMarketOrderNumber(null);
                    //AMG送信時刻
                    l_hostFotypeOrderAllParams.setAmgSendTime(null);
                    //AMG入力保証受信時刻
                    l_hostFotypeOrderAllParams.setAmgAckTime(null);
                    //市場入力保証受信時刻
                    l_hostFotypeOrderAllParams.setMarketAckTime(null);
                    //全訂正処理区分
                    l_hostFotypeOrderAllParams.setAllOrderChangeDiv(
                        WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE);
                    //処理区分
                    l_hostFotypeOrderAllParams.setStatus(l_strStatus);
                    try
                    {
                        QueryProcessor l_processor = Processors.getDefaultProcessor();

                        l_processor.doInsertQuery(l_hostFotypeOrderAllParams);
                    }
                    catch (DataException l_ex)
                    {
                        WEB3SystemLayerException l_sysException =
                            new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                                this.getClass().getName() + "." + METHOD_NAME,
                                l_ex.getMessage(),
                                l_ex);

                        log.error("__an unexpected error__",l_sysException);
                        l_processingResult = ProcessingResult.newFailedResultInstance(
                            l_sysException.getErrorInfo());

                        return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
                    }
                }

                //isトリガ発行()をコール
                l_isTriggerIssue =
                    WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(
                        null);

                String l_strMarketCode = l_market.getMarketCode();

                //isMQトリガ発行経路()
                //MQトリガ発行要否を取得する。
                //[isMQトリガ発行経路()に指定する引数]
                //証券会社コード：　@注文単位.証券会社IDに該当する証券会社コード
                //銘柄タイプ：　@注文単位.銘柄タイプ
                //市場コード：　@注文単位.市場IDに該当する市場コード
                //発注経路区分：　@注文単位.発注経路区分
                //フロント発注システム区分：　@先物OP注文取引キュー.フロント発注システム区分
                boolean l_blnIsSubmitMQTriggerEnable = WEB3GentradeOrderSwitching.isSubmitMQTriggerEnable(
                    l_strInstitutionCode,
                    l_ifoOrderUnitRow.getProductType(),
                    l_strMarketCode,
                    l_ifoOrderUnitRow.getSubmitOrderRouteDiv(),
                    l_hostFotypeOrderAllParams.getFrontOrderSystemCode());
                //トリガー発行時間帯&&MQトリガ発行経路の場合のみ処理実施
                if (l_isTriggerIssue && l_blnIsSubmitMQTriggerEnable)
                {
                    String l_strMQDataCode =
                        l_orderService.getChangeCancelMQDataCode(
                            (IfoOrderUnit)l_orderUnit);
                    if (l_strMQDataCode == null)
                    {
                        log.exiting(METHOD_NAME);
                        return DefaultMarketRequestSendResult.newSuccessResultInstance(
                            l_lngMsgTokenId);
                    }
                    //WEB3MQMessageSpec(証券会社コード : String, データコード : String)
                    //WEB3MQMessageSpecを生成する。
                    //[コンストラクタに指定する引数]
                    //証券会社コード：
                    //　@注文単位.証券会社IDに該当する証券会社コード
                    //データコード：
                    //　@先物OP発注サービス.get訂正取消時MQデータコード()の戻り値
                    l_web3MQMessageSpec =
                        new WEB3MQMessageSpec(
                            l_strInstitutionCode,
                            l_strMQDataCode);

                    //MQサービスを取得する
                    l_web3MQGatewayService =
                        (WEB3MQGatewayService) Services.getService(
                            WEB3MQGatewayService.class);

                    //send(MQメッセージ内容 : WEB3MQMessageSpec)
                    l_web3MQSendResult =
                        l_web3MQGatewayService.send(l_web3MQMessageSpec);

                    if (l_web3MQSendResult.isSuccessResult())
                    {
                        log.exiting(METHOD_NAME);
                        return DefaultMarketRequestSendResult.newSuccessResultInstance(l_lngMsgTokenId);
                    }
                    else
                    {
                        l_processingResult =
                            ProcessingResult.newFailedResultInstance(
                                l_web3MQSendResult.getErrorInfo());

                        log.exiting(METHOD_NAME);
                        return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
                    }
                }
            }
            else if (l_orderManager.isNotifyEndOrder(l_orderUnit)
                || !l_blnIsOpenTimeZone
                || (!l_blnIsMarketNotSendMessage
                    && WEB3GentradeTradingTimeManagement.isEveningSessionTimeZone()
                    && WEB3StringTypeUtility.isEmpty(l_ifoOrderUnitRow.getSessionType())))
            {
                //訂正確定
                this.updateOrderModified(l_orderUnit);
            }
        }
        catch (WEB3SystemLayerException l_sle)
        {
            WEB3SystemLayerException l_sysException = new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + METHOD_NAME,
                l_sle.getMessage(),
                l_sle);
            log.error("__an unexpected error__",l_sysException);
            ProcessingResult l_processingResult = ProcessingResult.newFailedResultInstance(
                l_sysException.getErrorInfo());

            return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
        }
        catch (NotFoundException l_nfe)
        {
            WEB3SystemLayerException l_sysException = new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);

            log.error("__an unexpected error__",l_sysException);
            ProcessingResult l_processingResult = ProcessingResult.newFailedResultInstance(
                l_sysException.getErrorInfo());

            return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
        }
        catch (DataException l_de)
        {
            WEB3SystemLayerException l_sysException = new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + METHOD_NAME,
                l_de.getMessage(),
                l_de);

            log.error("__an unexpected error__",l_sysException);
            ProcessingResult l_processingResult = ProcessingResult.newFailedResultInstance(
                l_sysException.getErrorInfo());

            return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
        }
        catch (WEB3BaseException l_wbe)
        {
            log.error("__an unexpected error__",l_wbe);
            ProcessingResult l_processingResult = ProcessingResult.newFailedResultInstance(
                    l_wbe.getErrorInfo());

            return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
        }
        log.exiting(METHOD_NAME);
        return DefaultMarketRequestSendResult.newSuccessResultInstance(0L);

    }

    /**
     * (新規建訂正注文送信)<BR>
     * <BR>
     * （send(IfoChangeOrderMarketRequestMessage, boolean)の実装）<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物OP市場ﾘｸｴｽﾄ）新規建訂正注文送信」参照。<BR>
     * @@param l_request - 新規建注文訂正リクエストメッセージ<BR>
     * @@param l_blnIsMarketNotSendMessage - (is市場未送信)<BR>
     * 原注文が市場未送信の場合はtrue、原注文が市場送信済の場合<BR>はfalseを指定する。<
     * BR>
     * falseの場合、SONARへ変更を通知する。<BR>
     *
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSendResult
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.TooLateException
     * @@roseuid 4061218303D7
     */
    public MarketRequestSendResult send(
        IfoChangeOrderMarketRequestMessage l_request,
        boolean l_blnIsMarketNotSendMessage)
        throws TooLateException
    {
        final String METHOD_NAME = "send(IfoChangeOrderMarketRequestMessage,boolean)";
        log.entering(METHOD_NAME);

        try
        {
            if (l_request == null)
            {
                log.error("parameter is null type");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + METHOD_NAME);
            }

            String ORDER_REQUEST_CODE       = WEB3HostRequestCodeDef.OPTION_ORDER_CHANGE_CANCEL;// リクエストデータコード
            String l_strStatus = WEB3StatusDef.NOT_DEAL;//処理区分 "0：未処理"
            String l_strInstitutionCode     = null;//証券会社コード
            String l_strBranchCode          = null;//部店コード
            String l_strTraderCode          = null;//扱者コード
            String l_strOrderRequestNumber  = null;//識別コード
            double l_dblSellOrderQuantity  = 0;//売付数量
            double l_dblBuyOrderQuantity   = 0;//買付数量
            double l_dblSellOrderQuantity1  = 0;//売付数量
            double l_dblBuyOrderQuantity1   = 0;//買付数量
            double l_dblLimitPrice         = 0;//指値
            String l_strExecutionCondition  = null;//執行条件
            String l_strConfirmedExecutionCondition  = null;//執行条件
            String l_strOrderConditionType = null;//発注条件
            Timestamp l_tsCreateDateTime    = null;//受注日時
            String l_strAccountCode         = null;//顧客コード
            String l_strOrderDateDiv        = null;//受注日区分
            String l_strProductCode         = null;//銘柄コード
            double l_dblQuantity           = 0;//訂正数量
            String l_strCancelDiv           = null;//取消区分
            String l_strFuturesOptionDiv    = null;
            int l_intOrderActionSerialNo = 0;//注文履歴番号
            String  l_strOrderActionSerialNo =null;
            String l_strFutureOptionProductType = null;
            boolean l_blnIsInsert = false;

            Order l_order = null;
            IfoOrderUnitRow l_ifoOrderUnitRow = null;
            boolean l_isTriggerIssue = false;
            long l_lngMsgTokenId = 0;
            long l_lngOrderId = l_request.getOrderId();

            WEB3MQMessageSpec l_web3MQMessageSpec = null;
            WEB3MQGatewayService l_web3MQGatewayService;
            WEB3MQSendResult l_web3MQSendResult;
            ProcessingResult l_processingResult;

            AccountManager l_accountManager =GtlUtils.getAccountManager();
            TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.IFO);
            IfoProductManager l_ifoProductManager =
                (IfoProductManager) l_tradingModule.getProductManager();

            IfoProductImpl l_ifoProductImpl = null;

            //注文取得
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();
            l_order = (IfoOrderImpl) l_orderMgr.getOrder(l_lngOrderId);

            OrderUnit[] l_orderUnits = l_order.getOrderUnits();
            OrderUnit l_orderUnit = l_orderUnits[0];

            l_ifoOrderUnitRow =
                (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();

            //notifyルールエンジンサーバ()
            if (l_blnIsMarketNotSendMessage)
            {
                l_orderMgr.notifyRLS(
                    (IfoOrderUnit)l_orderUnit,
                    OrderManagerPersistenceContext.MODIFY_ORDER_CONFIRMED_BY_MKT);
            }
            else
            {
                l_orderMgr.notifyRLS(
                    (IfoOrderUnit)l_orderUnit,
                    OrderManagerPersistenceContext.MODIFY_ORDER_ACCEPTED);
            }

            //対象データを取得する----------------------------[START]
            //先物オプション区分を取得
            l_strFuturesOptionDiv = l_ifoOrderUnitRow.getFutureOptionDiv();
            
            //throw NotFoundException
            //銘柄を取得する
            l_ifoProductImpl = (IfoProductImpl) l_ifoProductManager.getProduct(
                                l_ifoOrderUnitRow.getProductId());
            IfoProductRow l_ifoProductRow = (IfoProductRow)l_ifoProductImpl.getDataSourceObject();

            //部店を取得する
            Branch l_banch =
                   l_accountManager.getBranch(l_ifoOrderUnitRow.getBranchId());

            //証券会社コードを取得する
            l_strInstitutionCode =
                       l_banch.getInstitution().getInstitutionCode();

            //部店コードを取得する
            l_strBranchCode = l_banch.getBranchCode();

            //顧客コードを取得する
            l_strAccountCode =
                l_accountManager.getMainAccount(
                    l_ifoOrderUnitRow.getAccountId()).getAccountCode();

            //扱者コードを取得する
            l_strTraderCode = l_ifoOrderUnitRow.getSonarTraderCode();
            
            //識別コードを取得する
            l_strOrderRequestNumber =
                    l_ifoOrderUnitRow.getOrderRequestNumber();
            
            //銘柄コードを取得する
            l_strProductCode = l_ifoProductImpl.getProductCode();
            
            //買付/売付数量をセット
            //訂正数量をセット
            l_dblQuantity = l_ifoOrderUnitRow.getQuantity();
            //買付/売付数量を取得する
            OrderTypeEnum l_orderType = l_orderUnit.getOrderType();
            if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_orderType) ||
                OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.equals(l_orderType))
                //605：OP新規買建注文/601：先物新規買建注文
            {   //買付の場合
                l_dblBuyOrderQuantity = l_ifoOrderUnitRow.getConfirmedQuantity();
                l_dblBuyOrderQuantity1 = l_ifoOrderUnitRow.getQuantity();
            }
            else if (OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.equals(l_orderType) ||
                OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.equals(l_orderType))
                //606：OP新規売建注文/602：先物新規売建注文
            {
                //売付の場合
                l_dblSellOrderQuantity = l_ifoOrderUnitRow.getConfirmedQuantity();
                l_dblSellOrderQuantity1 = l_ifoOrderUnitRow.getQuantity();
            }

            //指値を取得
            l_dblLimitPrice = l_ifoOrderUnitRow.getLimitPrice();
            //執行条件を設定
            IfoOrderExecutionConditionType l_ifoExecutionConditionType =
                l_ifoOrderUnitRow.getExecutionConditionType();

            if (IfoOrderExecutionConditionType.AT_MARKET_OPEN.equals(l_ifoExecutionConditionType))
            {
                //寄付
                l_strExecutionCondition = WEB3SonarExecutionConditionDef.AT_MARKET_OPEN;
            }
            else if (IfoOrderExecutionConditionType.AT_MARKET_CLOSE.equals(l_ifoExecutionConditionType))
            {
                //引け
                l_strExecutionCondition = WEB3SonarExecutionConditionDef.AT_MARKET_CLOSE;
            }
            else if (IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.equals(l_ifoExecutionConditionType))
            {
                //出来ずば引成(不成)
                l_strExecutionCondition = WEB3SonarExecutionConditionDef.NO_EXECUTED_MARKET_ORDER;
            }
            else if (!l_ifoOrderUnitRow.getFirstOrderUnitIdIsNull())
            {
                // 出来るまで注文(注文単位.初回注文の注文単位ID≠null)の場合、２：出合。
                l_strExecutionCondition = WEB3SonarExecutionConditionDef.COME_TO_TERMS;
            }
            else
            {
                //無条件
                l_strExecutionCondition = WEB3SonarExecutionConditionDef.UNCONDITIONDNESS;
            }

            l_ifoExecutionConditionType =
                l_ifoOrderUnitRow.getConfirmedExecConditionType();
            if (IfoOrderExecutionConditionType.AT_MARKET_OPEN.equals(l_ifoExecutionConditionType))
            {
                //寄付
                l_strConfirmedExecutionCondition = WEB3SonarExecutionConditionDef.AT_MARKET_OPEN;
            }
            else if (IfoOrderExecutionConditionType.AT_MARKET_CLOSE.equals(l_ifoExecutionConditionType))
            {
                //引け
                l_strConfirmedExecutionCondition = WEB3SonarExecutionConditionDef.AT_MARKET_CLOSE;
            }
            else if (IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.equals(l_ifoExecutionConditionType))
            {
                //出来ずば引成(不成)
                l_strConfirmedExecutionCondition = WEB3SonarExecutionConditionDef.NO_EXECUTED_MARKET_ORDER;
            }
            else if (!l_ifoOrderUnitRow.getFirstOrderUnitIdIsNull())
            {
                // 出来るまで注文(注文単位.初回注文の注文単位ID≠null)の場合、２：出合。
                l_strConfirmedExecutionCondition = WEB3SonarExecutionConditionDef.COME_TO_TERMS;
            }
            else
            {
                //無条件
                l_strConfirmedExecutionCondition = WEB3SonarExecutionConditionDef.UNCONDITIONDNESS;
            }
            //発注条件を取得する
            l_strOrderConditionType = l_ifoOrderUnitRow.getOrderConditionType();

			//受注日時に現在時刻を設定する
			l_tsCreateDateTime = GtlUtils.getSystemTimestamp();
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            String l_strCreateDate = l_format.format(l_tsCreateDateTime);

            //受注日区分を取得する
            l_strOrderDateDiv = WEB3OrderDateDivDef.YESTERDAY;
            if (l_ifoOrderUnitRow.getBizDate().compareTo(l_strCreateDate) == 0)
            {
                l_strOrderDateDiv = WEB3OrderDateDivDef.TODAY;
            }

            //注文履歴最終通番を取得する
            l_intOrderActionSerialNo =
                    l_ifoOrderUnitRow.getLastOrderActionSerialNo();
            l_strOrderActionSerialNo = String.valueOf(l_intOrderActionSerialNo);
            
            //データコードを取得する
            if (l_strFuturesOptionDiv.equals(WEB3FuturesOptionDivDef.FUTURES))
            {
                ORDER_REQUEST_CODE = WEB3HostRequestCodeDef.FUTURES_ORDER_CHANGE_CANCEL;
            }

            //先物オプション商品を取得する
            if (IfoDerivativeTypeEnum.FUTURES.equals(l_ifoProductImpl.getDerivativeType()))
            {
                l_strFutureOptionProductType = WEB3IfoProductTypeDef.FUTURES;
            }
            else if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_ifoProductImpl.getDerivativeType()))
            {
                l_strFutureOptionProductType = WEB3IfoProductTypeDef.PUT_OPTIONS;
            }
            else if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_ifoProductImpl.getDerivativeType()))
            {
                l_strFutureOptionProductType = WEB3IfoProductTypeDef.CALL_OPTIONS;
            }

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_finOjbectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            WEB3GentradeMarket l_market =
                (WEB3GentradeMarket)l_finOjbectManager.getMarket(l_ifoOrderUnitRow.getMarketId());
            WEB3IfoFrontOrderService l_orderService =
                (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);
            //対象データを取得する----------------------------[END]

            //(*1) 市場未送信注文の場合のみ処理実施
            if (l_blnIsMarketNotSendMessage)
            {
                log.debug("市場未送信注文の場合のみ処理実施");
                
				//（注文単位.発注条件 == ”逆指値”　@かつ　@注文単位.リクエストタイプ!=”時価サーバ”）の場合は処理終了。
				if(WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType)
                    && !WEB3RequestTypeDef.QUOTE_SERVER.equals(l_ifoOrderUnitRow.getRequestType()))
				{
					log.debug("逆指値注文の場合は処理終了");
					log.exiting(METHOD_NAME);
					return DefaultMarketRequestSendResult.newSuccessResultInstance(l_lngMsgTokenId);
				}

                //証券会社、部店、識別コード、処理区分で先物OP注文取引キューテーブル検索
                try
                {   //throw DataFindException, DataNetworkException, DataQueryException
                    QueryProcessor l_processor = Processors.getDefaultProcessor();
                    int l_intIndex = l_orderService.getIndexOfOrderRevInCorpCode();
                    int l_intFigure = l_orderService.getFigureOfOrderRev();
                    StringBuffer l_sbWhereOrder = new StringBuffer();
                    l_sbWhereOrder.append(" institution_code = ? "); 
                    l_sbWhereOrder.append(" and branch_code = ? ");
                    l_sbWhereOrder.append(" and order_request_number = ? ");
                    l_sbWhereOrder.append(" and substr(corp_code," + l_intIndex + "," + l_intFigure + ")=? ");
                    l_sbWhereOrder.append(" and status = ? ");
            
                    Object[] l_objWhereOrder = { 
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strOrderRequestNumber,
                        l_ifoOrderUnitRow.getOrderRev(),
                        WEB3HostStatusDef.NOT_STARTED_PROCESS};

                    List l_lisSearchResult =
                        l_processor.doFindAllQuery(
                            HostFotypeOrderAllRow.TYPE,
                            l_sbWhereOrder.toString(),
                            null,
                            "FOR UPDATE",
                            l_objWhereOrder);
      
                    int l_intUpdateCnt = 0;
					if (l_lisSearchResult.isEmpty() || l_lisSearchResult.size() == 0)
					{
						return DefaultMarketRequestSendResult
							.newFailedResultInstance(
								ProcessingResult.newFailedResultInstance(
									WEB3ErrorCatalog.BUSINESS_ERROR_00010));
					}
                    else
                    {
                        l_intUpdateCnt = l_lisSearchResult.size();
                    }

                    Map l_mapChanges = new HashMap();
                    l_mapChanges.put("order_action_serial_no", l_strOrderActionSerialNo);
                    l_mapChanges.put("submit_order_route_div", l_ifoOrderUnitRow.getSubmitOrderRouteDiv());
                    l_mapChanges.put("buy_order_quantity", new Double(l_dblBuyOrderQuantity1));
                    l_mapChanges.put("sell_order_quantity", new Double(l_dblSellOrderQuantity1));
                    l_mapChanges.put("limit_price", new Double(l_dblLimitPrice));
                    l_mapChanges.put("execution_condition", l_strExecutionCondition);
                    l_mapChanges.put("last_updated_timestamp", l_tsCreateDateTime);
                    //一致する行の内容を訂正しDBに更新する。
                    if (l_intUpdateCnt > 0)
                    {
                        l_processor.doUpdateAllQuery(
                            HostFotypeOrderAllRow.TYPE,
                            l_sbWhereOrder.toString(),
                            l_objWhereOrder,
                            l_mapChanges);
                    }

                    log.exiting(METHOD_NAME);
                    return DefaultMarketRequestSendResult.newSuccessResultInstance(l_lngMsgTokenId);
                }
                catch (DataException l_de)
                {
                    WEB3SystemLayerException l_sysException = new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + METHOD_NAME,
                        l_de.getMessage(),
                        l_de);

                    log.error("__an unexpected error__",l_sysException);
                    l_processingResult = ProcessingResult.newFailedResultInstance(
                        l_sysException.getErrorInfo());

                    return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
                }
            }

            //(*2) 内容通知済注文の場合
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();
            //市場開局時間帯であるかを判定する。
            boolean l_blnIsOpenTimeZone = WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone();
            //トリガー発行あり
            boolean l_blnIsSubmitMarketTrigger =
                WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(l_strOrderConditionType);
            //is取引所休憩時間帯()
            boolean l_blnIsTradeCloseTimeZone = WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone();

            //（休憩時間帯）or（市場通知要の訂正注文 && トリガー発行あり）の場合
            if (l_blnIsTradeCloseTimeZone
                || (!l_orderManager.isNotifyEndOrder(l_orderUnit)
                        && l_blnIsSubmitMarketTrigger))
            {
                HostFotypeOrderAllParams l_hostFotypeOrderAllParams = null;
                //休憩時間帯の場合
                if (l_blnIsTradeCloseTimeZone)
                {
                    //get先物OP注文取引キュー(IfoOrderUnit)
                    //先物OP注文取引キューを取得する。
                    //[先物OP発注サービス.get先物OP注文取引キュー()に指定する引数]
                    //注文単位：　@注文単位
                    l_hostFotypeOrderAllParams =
                        l_orderService.getHostFotypeOrderAll((IfoOrderUnit)l_orderUnit);

                    //キューデータ存在しない場合
                    if (l_hostFotypeOrderAllParams == null)
                    {
                        //市場通知不要の場合、訂正確定(注文単位:OrderUnit)を行う
                        if (l_orderManager.isNotifyEndOrder(l_orderUnit))
                        {
                            //訂正確定
                            this.updateOrderModified(l_orderUnit);

                            //処理を終了する
                            log.exiting(METHOD_NAME);
                            return DefaultMarketRequestSendResult.newSuccessResultInstance(
                                l_lngMsgTokenId);
                        }
                        //市場通知要の場合、キューデータinsert
                        else
                        {
                            l_blnIsInsert = true;
                        }
                    }
                    //キューデータ存在する場合、キューデータupdate
                    else
                    {
                        try
                        {
                            QueryProcessor l_processor = Processors.getDefaultProcessor();

                            //受注日時
                            l_hostFotypeOrderAllParams.setReceivedDateTime(l_tsCreateDateTime);
                            //set注文履歴番号
                            l_hostFotypeOrderAllParams.setOrderActionSerialNo(l_intOrderActionSerialNo);
                            //set発注経路区分
                            l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv(l_ifoOrderUnitRow.getSubmitOrderRouteDiv());
                            //訂正数量
                            l_hostFotypeOrderAllParams.setChangeQuantity(l_dblQuantity);
                            //set指値
                            l_hostFotypeOrderAllParams.setChangeLimitPrice(l_dblLimitPrice);
                            //set執行条件
                            l_hostFotypeOrderAllParams.setChangeExecutionCondition(l_strExecutionCondition);
                            //set更新日付
                            l_hostFotypeOrderAllParams.setLastUpdatedTimestamp(l_tsCreateDateTime);
                            l_processor.doUpdateQuery(l_hostFotypeOrderAllParams);
                        }
                        catch (DataException l_ex)
                        {
                            WEB3SystemLayerException l_sysException =
                                new WEB3SystemLayerException(
                                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                                    this.getClass().getName() + "." + METHOD_NAME,
                                    l_ex.getMessage(),
                                    l_ex);

                            log.error("__an unexpected error__",l_sysException);
                            l_processingResult = ProcessingResult.newFailedResultInstance(
                                l_sysException.getErrorInfo());

                            return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
                        }
                    }
                }
                else
                {
                    l_blnIsInsert = true;
                }
                if (l_blnIsInsert)
                {
                    l_hostFotypeOrderAllParams =
                        new HostFotypeOrderAllParams();

                    //データコード
                    l_hostFotypeOrderAllParams.setRequestCode(ORDER_REQUEST_CODE);
                    //口座ＩＤ
                    l_hostFotypeOrderAllParams.setAccountId(l_orderUnit.getAccountId());
                    //証券会社コード
                    l_hostFotypeOrderAllParams.setInstitutionCode(l_strInstitutionCode);
                    //部店コード
                    l_hostFotypeOrderAllParams.setBranchCode(l_strBranchCode);
                    //顧客コード
                    l_hostFotypeOrderAllParams.setAccountCode(l_strAccountCode);
                    //扱者コード
                    l_hostFotypeOrderAllParams.setTraderCode(l_strTraderCode);
                    //受注日区分
                    l_hostFotypeOrderAllParams.setReceivedDateTimeDiv(l_strOrderDateDiv);
                    //識別コード
                    l_hostFotypeOrderAllParams.setOrderRequestNumber(l_strOrderRequestNumber);
                    //市場コード（SONAR）
                    l_hostFotypeOrderAllParams.setSonarMarketCode(l_ifoOrderUnitRow.getSonarMarketCode());
                    //銘柄コード
                    l_hostFotypeOrderAllParams.setProductCode(l_strProductCode);
                    //受注日時
                    l_hostFotypeOrderAllParams.setReceivedDateTime(l_tsCreateDateTime);
                    //注文履歴番号
                    l_hostFotypeOrderAllParams.setOrderActionSerialNo(l_intOrderActionSerialNo);
                    //発注経路区分
                    l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv(l_ifoOrderUnitRow.getSubmitOrderRouteDiv());
                    //原資産銘柄コード
                    l_hostFotypeOrderAllParams.setTargetProductCode(l_ifoProductImpl.getUnderlyingProductCode());
                    //限月（年）
                    l_hostFotypeOrderAllParams.setDeliveryMonthYyyy(
                        l_ifoProductImpl.getMonthOfDelivery().substring(0, 4));
                    //限月（月）
                    l_hostFotypeOrderAllParams.setDeliveryMonthMm(
                        l_ifoProductImpl.getMonthOfDelivery().substring(4, 6));
                    //先物オプション商品
                    l_hostFotypeOrderAllParams.setFutureOptionProductType(l_strFutureOptionProductType);
                    //行使価格
                    l_hostFotypeOrderAllParams.setStrikePrice(l_ifoProductImpl.getStrikePrice());
                    //分割
                    l_hostFotypeOrderAllParams.setSplitType(l_ifoProductRow.getSplitType());
                    //売付数量
                    l_hostFotypeOrderAllParams.setSellOrderQuantity(l_dblSellOrderQuantity);
                    //買付数量
                    l_hostFotypeOrderAllParams.setBuyOrderQuantity(l_dblBuyOrderQuantity);
                    //指値
                    l_hostFotypeOrderAllParams.setLimitPrice(l_ifoOrderUnitRow.getConfirmedPrice());
                    //執行条件
                    l_hostFotypeOrderAllParams.setExecutionCondition(l_strConfirmedExecutionCondition);
                    //逆指値基準値
                    l_hostFotypeOrderAllParams.setStopOrderPrice(null);
                    //（W指値）訂正指値
                    l_hostFotypeOrderAllParams.setWLimitPrice(null);
                    //取引区分
                    l_hostFotypeOrderAllParams.setTransactionType(null);
                    //伝票№
                    l_hostFotypeOrderAllParams.setTicketNumber(null);
                    //建玉チェック
                    l_hostFotypeOrderAllParams.setContractCheck(null);
                    //注文チャネル
                    l_hostFotypeOrderAllParams.setOrderChanel(null);
                    //手数料№
                    l_hostFotypeOrderAllParams.setCommisionNumber(null);
                    //手数料№枝番
                    l_hostFotypeOrderAllParams.setCommisionBranchNumber(null);
                    //手数料商品コード
                    l_hostFotypeOrderAllParams.setCommisionProductCode(null);
                    //訂正数量
                    l_hostFotypeOrderAllParams.setChangeQuantity(l_dblQuantity);
                    //訂正指値
                    l_hostFotypeOrderAllParams.setChangeLimitPrice(l_dblLimitPrice);
                    //訂正執行条件
                    l_hostFotypeOrderAllParams.setChangeExecutionCondition(l_strExecutionCondition);
                    //訂正逆指値基準値
                    l_hostFotypeOrderAllParams.setChangeStopOrderPrice(null);
                    //訂正（W指値）訂正指値
                    l_hostFotypeOrderAllParams.setChangeWLimitPrice(null);
                    //取消区分
                    l_strCancelDiv = WEB3CancelDivDef.EXCEPT_CANCEL;
                    l_hostFotypeOrderAllParams.setCancelDiv(l_strCancelDiv);
                    //フロント発注取引所区分コード
                    l_hostFotypeOrderAllParams.setFrontOrderExchangeCode(
                        l_orderService.getFrontOrderExchangeCode(l_market.getMarketCode()));
                    //フロント発注システム区分
                    l_hostFotypeOrderAllParams.setFrontOrderSystemCode(
                        l_orderService.getFrontOrderSystemCode(l_market.getMarketCode()));
                    //フロント発注取引区分コード
                    l_hostFotypeOrderAllParams.setFrontOrderTradeCode(
                        WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE);
                    //自己委託区分
                    l_hostFotypeOrderAllParams.setTradeauditCode(null);
                    //社内処理項目
                    l_hostFotypeOrderAllParams.setCorpCode(l_orderService.getCorpCode((IfoOrderUnit)l_orderUnit));
                    //（被訂正）社内処理項目
                    l_hostFotypeOrderAllParams.setOrgCorpCode(
                        l_orderService.getOrgCorpCode((IfoOrderUnit)l_orderUnit));
                    //仮想サーバNo.（JSOES）
                    l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes(null);
                    //市場発注No.
                    l_hostFotypeOrderAllParams.setMarketOrderNumber(null);
                    //AMG送信時刻
                    l_hostFotypeOrderAllParams.setAmgSendTime(null);
                    //AMG入力保証受信時刻
                    l_hostFotypeOrderAllParams.setAmgAckTime(null);
                    //市場入力保証受信時刻
                    l_hostFotypeOrderAllParams.setMarketAckTime(null);
                    //全訂正処理区分
                    l_hostFotypeOrderAllParams.setAllOrderChangeDiv(
                        WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE);
                    //処理区分
                    l_hostFotypeOrderAllParams.setStatus(l_strStatus);
                    try
                    {
                        QueryProcessor l_processor = Processors.getDefaultProcessor();

                        l_processor.doInsertQuery(l_hostFotypeOrderAllParams);
                    }
                    catch (DataException l_ex)
                    {
                        WEB3SystemLayerException l_sysException =
                            new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                                this.getClass().getName() + "." + METHOD_NAME,
                                l_ex.getMessage(),
                                l_ex);

                        log.error("__an unexpected error__",l_sysException);
                        l_processingResult = ProcessingResult.newFailedResultInstance(
                            l_sysException.getErrorInfo());

                        return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
                    }
                }
                //isトリガ発行()をコール
                l_isTriggerIssue =
                    WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(
                        null);

                String l_strMarketCode = l_market.getMarketCode();

                //isMQトリガ発行経路()
                //MQトリガ発行要否を取得する。
                //[isMQトリガ発行経路()に指定する引数]
                //証券会社コード：　@注文単位.証券会社IDに該当する証券会社コード
                //銘柄タイプ：　@注文単位.銘柄タイプ
                //市場コード：　@注文単位.市場IDに該当する市場コード
                //発注経路区分：　@注文単位.発注経路区分
                //フロント発注システム区分：　@先物OP注文取引キュー.フロント発注システム区分
                boolean l_blnIsSubmitMQTriggerEnable = WEB3GentradeOrderSwitching.isSubmitMQTriggerEnable(
                    l_strInstitutionCode,
                    l_ifoOrderUnitRow.getProductType(),
                    l_strMarketCode,
                    l_ifoOrderUnitRow.getSubmitOrderRouteDiv(),
                    l_hostFotypeOrderAllParams.getFrontOrderSystemCode());
                //トリガー発行時間帯&&MQトリガ発行経路の場合のみ処理実施
                if (l_isTriggerIssue && l_blnIsSubmitMQTriggerEnable)
                {
                    String l_strMQDataCode =
                        l_orderService.getChangeCancelMQDataCode(
                            (IfoOrderUnit)l_orderUnit);
                    if (l_strMQDataCode == null)
                    {
                        log.exiting(METHOD_NAME);
                        return DefaultMarketRequestSendResult.newSuccessResultInstance(
                            l_lngMsgTokenId);
                    }
                    //WEB3MQMessageSpec(証券会社コード : String, データコード : String)
                    //WEB3MQMessageSpecを生成する。
                    //[コンストラクタに指定する引数]
                    //証券会社コード：
                    //　@注文単位.証券会社IDに該当する証券会社コード
                    //データコード：
                    //　@先物OP発注サービス.get訂正取消時MQデータコード()の戻り値
                    l_web3MQMessageSpec =
                        new WEB3MQMessageSpec(
                            l_strInstitutionCode,
                            l_strMQDataCode);

                    //MQサービスを取得する
                    l_web3MQGatewayService =
                        (WEB3MQGatewayService) Services.getService(
                            WEB3MQGatewayService.class);

                    //send(MQメッセージ内容 : WEB3MQMessageSpec)
                    l_web3MQSendResult =
                        l_web3MQGatewayService.send(l_web3MQMessageSpec);

                    if (l_web3MQSendResult.isSuccessResult())
                    {
                        log.exiting(METHOD_NAME);
                        return DefaultMarketRequestSendResult.newSuccessResultInstance(l_lngMsgTokenId);
                    }
                    else
                    {
                        l_processingResult =
                            ProcessingResult.newFailedResultInstance(
                                l_web3MQSendResult.getErrorInfo());

                        log.exiting(METHOD_NAME);
                        return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
                    }
                }
            }
            else if (l_orderManager.isNotifyEndOrder(l_orderUnit)
                || !l_blnIsOpenTimeZone
                || (!l_blnIsMarketNotSendMessage
                    && WEB3GentradeTradingTimeManagement.isEveningSessionTimeZone()
                    && WEB3StringTypeUtility.isEmpty(l_ifoOrderUnitRow.getSessionType())))
            {
                //訂正確定
                this.updateOrderModified(l_orderUnit);
            }
        }
		catch (WEB3SystemLayerException l_sle)
		{
			WEB3SystemLayerException l_sysException = new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + METHOD_NAME,
				l_sle.getMessage(),
				l_sle);
			log.error("__an unexpected error__",l_sysException);
			ProcessingResult l_processingResult = ProcessingResult.newFailedResultInstance(
				l_sysException.getErrorInfo());

			return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
		}
        catch (NotFoundException l_nfe)
        {
            WEB3SystemLayerException l_sysException = new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);

            log.error("__an unexpected error__",l_sysException);
            ProcessingResult l_processingResult = ProcessingResult.newFailedResultInstance(
                l_sysException.getErrorInfo());

            return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
        }
        catch (WEB3BaseException l_wbe)
        {
            log.error("__an unexpected error__",l_wbe);
            ProcessingResult l_processingResult = ProcessingResult.newFailedResultInstance(
                    l_wbe.getErrorInfo());

            return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
        }
        log.exiting(METHOD_NAME);
        return DefaultMarketRequestSendResult.newSuccessResultInstance(0L);

    }

    /**
     * (取消注文送信)<BR>
     * <BR>
     * （send(CancelOrderMarketRequestMessage, boolean)の実装）<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物OP市場ﾘｸｴｽﾄ）取消注文送信」参照。<BR>
     * @@param l_request - 注文取消リクエストメッセージ<BR>
     *
     * @@param l_blnIsMarketNotSendMessage - (is市場未送信)<BR>
     * 原注文が市場未送信の場合はtrue、原注文が市場送信済の場合<BR>はfalseを指定する。<
     * BR>
     * falseの場合、SONARへ取消を通知する。<BR>
     *
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSendResult
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.TooLateException
     * @@roseuid 4061223901C4
     */
    public MarketRequestSendResult send(
        CancelOrderMarketRequestMessage l_request,
        boolean l_blnIsMarketNotSendMessage)
        throws TooLateException
    {
        final String METHOD_NAME = "send(CancelOrderMarketRequestMessage,boolean)";
        log.entering(METHOD_NAME);

        try
        {
            if (l_request == null)
            {
                log.error("parameter is null type");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + METHOD_NAME);
            }

            String ORDER_REQUEST_CODE       = WEB3HostRequestCodeDef.OPTION_ORDER_CHANGE_CANCEL;//データコード
            String l_strStatus = WEB3StatusDef.NOT_DEAL;//処理区分 "0：未処理"
            String l_strInstitutionCode     = null;//証券会社コード
            String l_strBranchCode          = null;//部店コード
            String l_strAccountCode         = null;//顧客コード
            String l_strTraderCode          = null;//扱者コード
            String l_strOrderDateDiv        = null;//受注日区分
            String l_strOrderRequestNumber  = null;//識別コード
            String l_strProductCode         = null;//銘柄コード
            Timestamp l_tsCreateDateTime    = null;//受注日時
            String l_strCancelDiv           = null;//取消区分
            String l_strFuturesOptionDiv    = null;//先物／オプション区分
            String l_strOrderConditionType = null;//発注条件
            Order l_order = null;
            IfoOrderUnitRow l_ifoOrderUnitRow = null;
            int l_intOrderActionSerialNo = 0;//注文履歴番号
            String l_strFutureOptionProductType = null;
            double l_dblSellOrderQuantity  = 0;//売付数量
            double l_dblBuyOrderQuantity   = 0;//買付数量
            String l_strConfirmedExecutionCondition  = null;//執行条件

            boolean l_isTriggerIssue = false;
            long l_lngMsgTokenId = 0;
            long l_lngOrderId = l_request.getOrderId();

            WEB3MQMessageSpec l_web3MQMessageSpec = null;
            WEB3MQGatewayService l_web3MQGatewayService;
            WEB3MQSendResult l_web3MQSendResult;
            ProcessingResult l_processingResult;

            AccountManager l_accountManager =GtlUtils.getAccountManager();
            TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.IFO);
            IfoProductManager l_ifoProductManager =
                (IfoProductManager) l_tradingModule.getProductManager();

            IfoProductImpl l_ifoProductImpl = null;

            //注文取得
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();
            l_order = (IfoOrderImpl) l_orderMgr.getOrder(l_lngOrderId);

            OrderUnit[] l_orderUnits = l_order.getOrderUnits();
            OrderUnit l_orderUnit = l_orderUnits[0];

            l_ifoOrderUnitRow =
                (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();

            //notifyルールエンジンサーバ()
            //1.5 (*)notifyルールエンジンサーバ()にて業務エラーがスローされた場合
            try
            {
	            if (l_blnIsMarketNotSendMessage)
	            {
	                l_orderMgr.notifyRLS(
	                    (IfoOrderUnit)l_orderUnit,
	                    OrderManagerPersistenceContext.CANCEL_ORDER_CONFIRMED_BY_MKT);
	            }
	            else
	            {
	                l_orderMgr.notifyRLS(
	                    (IfoOrderUnit)l_orderUnit,
	                    OrderManagerPersistenceContext.CANCEL_ORDER_ACCEPTED);
	            }
            }
            catch (WEB3BusinessLayerException l_ex)
            {
				log.debug("error in  l_orderMgr.notifyRLS", l_ex);
            }

            //対象データを取得する----------------------------[START]
            //先物オプション区分を取得
            l_strFuturesOptionDiv = l_ifoOrderUnitRow.getFutureOptionDiv();
            
            //throw NotFoundException
            //銘柄を取得する
            l_ifoProductImpl = (IfoProductImpl) l_ifoProductManager
                              .getProduct(l_ifoOrderUnitRow.getProductId());
            IfoProductRow l_ifoProductRow = (IfoProductRow)l_ifoProductImpl.getDataSourceObject();

            //部店を取得する
            Branch l_banch =
                   l_accountManager.getBranch(l_ifoOrderUnitRow.getBranchId());
            //証券会社コードを取得する
            l_strInstitutionCode = l_banch.getInstitution().getInstitutionCode();

            //部店コードを取得する
            l_strBranchCode = l_banch.getBranchCode();
            //識別コードを取得する
            l_strOrderRequestNumber = l_ifoOrderUnitRow.getOrderRequestNumber();
            //顧客コードを取得する
            l_strAccountCode =
                l_accountManager.getMainAccount(
                    l_ifoOrderUnitRow.getAccountId()).getAccountCode();
            //扱者コードを取得する
            l_strTraderCode = l_ifoOrderUnitRow.getSonarTraderCode();
            
			//発注条件を取得する
            l_strOrderConditionType = l_ifoOrderUnitRow.getOrderConditionType();

			//受注日時に現在時刻を設定する
			l_tsCreateDateTime = GtlUtils.getSystemTimestamp();
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            String l_strCreateDate = l_format.format(l_tsCreateDateTime);
                        
            //受注日区分を取得する
            l_strOrderDateDiv = WEB3OrderDateDivDef.YESTERDAY;
            if (l_ifoOrderUnitRow.getBizDate().compareTo(l_strCreateDate) == 0)
            {
                l_strOrderDateDiv = WEB3OrderDateDivDef.TODAY;
            }
            
            //銘柄コードを取得する
            l_strProductCode = l_ifoProductImpl.getProductCode();

            //注文履歴最終通番を取得する
            l_intOrderActionSerialNo =
                    l_ifoOrderUnitRow.getLastOrderActionSerialNo();

            //データコードを取得する
            if (l_strFuturesOptionDiv.equals(WEB3FuturesOptionDivDef.FUTURES))
            {
                ORDER_REQUEST_CODE = WEB3HostRequestCodeDef.FUTURES_ORDER_CHANGE_CANCEL;
            }

            //先物オプション商品を取得する
            if (IfoDerivativeTypeEnum.FUTURES.equals(l_ifoProductImpl.getDerivativeType()))
            {
                l_strFutureOptionProductType = WEB3IfoProductTypeDef.FUTURES;
            }
            else if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_ifoProductImpl.getDerivativeType()))
            {
                l_strFutureOptionProductType = WEB3IfoProductTypeDef.PUT_OPTIONS;
            }
            else if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_ifoProductImpl.getDerivativeType()))
            {
                l_strFutureOptionProductType = WEB3IfoProductTypeDef.CALL_OPTIONS;
            }

            IfoOrderExecutionConditionType l_ifoExecutionConditionType =
                l_ifoOrderUnitRow.getConfirmedExecConditionType();
            if (IfoOrderExecutionConditionType.AT_MARKET_OPEN.equals(l_ifoExecutionConditionType))
            {
                //寄付
                l_strConfirmedExecutionCondition = WEB3SonarExecutionConditionDef.AT_MARKET_OPEN;
            }
            else if (IfoOrderExecutionConditionType.AT_MARKET_CLOSE.equals(l_ifoExecutionConditionType))
            {
                //引け
                l_strConfirmedExecutionCondition = WEB3SonarExecutionConditionDef.AT_MARKET_CLOSE;
            }
            else if (IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.equals(l_ifoExecutionConditionType))
            {
                //出来ずば引成(不成)
                l_strConfirmedExecutionCondition = WEB3SonarExecutionConditionDef.NO_EXECUTED_MARKET_ORDER;
            }
            else if (!l_ifoOrderUnitRow.getFirstOrderUnitIdIsNull())
            {
                // 出来るまで注文(注文単位.初回注文の注文単位ID≠null)の場合、２：出合。
                l_strConfirmedExecutionCondition = WEB3SonarExecutionConditionDef.COME_TO_TERMS;
            }
            else
            {
                //無条件
                l_strConfirmedExecutionCondition = WEB3SonarExecutionConditionDef.UNCONDITIONDNESS;
            }

            //買付/売付数量を取得する
            SideEnum l_sideEnum = l_orderUnit.getSide();
            if (SideEnum.BUY.equals(l_sideEnum))
            //注文単位.getSide()=="買い"の場合
            {
                //注文単位.市場から確認済みの数量
                l_dblBuyOrderQuantity = l_ifoOrderUnitRow.getConfirmedQuantity();
            }
            else if (SideEnum.SELL.equals(l_sideEnum))
            //注文単位.getSide()=="売り"の場合
            {
                //注文単位.市場から確認済みの数量
                l_dblSellOrderQuantity = l_ifoOrderUnitRow.getConfirmedQuantity();
            }

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_finOjbectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            WEB3GentradeMarket l_market =
                (WEB3GentradeMarket)l_finOjbectManager.getMarket(l_ifoOrderUnitRow.getMarketId());
            WEB3IfoFrontOrderService l_orderService =
                (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);
            //対象データを取得する----------------------------[END]

            //(*1) 市場未送信注文の場合のみ処理実施
            if (l_blnIsMarketNotSendMessage)
            {
                log.debug("市場未送信注文の場合のみ処理実施" );

                //（注文単位.発注条件 == ”逆指値”　@かつ　@注文単位.リクエストタイプ!=”時価サーバ”）の場合は処理終了。
                if(WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType)
                    && !WEB3RequestTypeDef.QUOTE_SERVER.equals(l_ifoOrderUnitRow.getRequestType()))
                {
                    log.debug("逆指値注文の場合は処理終了");
                    log.exiting(METHOD_NAME);
                    return DefaultMarketRequestSendResult.newSuccessResultInstance(l_lngMsgTokenId);
                }

                //証券会社、部店、識別コード、処理区分でOP取消_先物OP注文取引キューテーブル検索
                try
                {   //throw DataFindException, DataNetworkException, DataQueryException
                    QueryProcessor l_processor = Processors.getDefaultProcessor();
                    int l_intIndex = l_orderService.getIndexOfOrderRevInCorpCode();
                    int l_intFigure = l_orderService.getFigureOfOrderRev();
                    StringBuffer l_sbWhere = new StringBuffer();
                    l_sbWhere.append(" institution_code = ? ");
                    l_sbWhere.append(" and branch_code = ? ");
                    l_sbWhere.append(" and order_request_number = ? ");
                    l_sbWhere.append(" and substr(corp_code," + l_intIndex + "," + l_intFigure + ")=? ");
                    l_sbWhere.append(" and status = ? ");

                    Object[] l_objParams =
                    {   l_strInstitutionCode,
                        l_strBranchCode,
                        l_strOrderRequestNumber,
                        l_ifoOrderUnitRow.getOrderRev(),
                        WEB3HostStatusDef.NOT_STARTED_PROCESS};

                    //対象データ削除
                    int l_intDeleteCnt = l_processor.doDeleteAllQuery(
                        HostFotypeOrderAllRow.TYPE,
                        l_sbWhere.toString(),
                        l_objParams);
                        
                    if (l_intDeleteCnt == 0)
                    {
						return DefaultMarketRequestSendResult
							.newFailedResultInstance(
								ProcessingResult.newFailedResultInstance(
									WEB3ErrorCatalog.BUSINESS_ERROR_00010));
                    }

                }
                catch (DataException l_de)
                {
                    WEB3SystemLayerException l_sysException = new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + METHOD_NAME,
                        l_de.getMessage(),
                        l_de);

                    log.error("__an unexpected error__",l_sysException);
                    l_processingResult = ProcessingResult.newFailedResultInstance(
                        l_sysException.getErrorInfo());

                    return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
                }

                //処理を終了する
                log.exiting(METHOD_NAME);
                return DefaultMarketRequestSendResult.newSuccessResultInstance(
                    l_lngMsgTokenId);

            }
            //市場開局時間帯であるかを判定する。
            boolean l_blnIsOpenTimeZone = WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone();
            //is取引所休憩時間帯()
            boolean l_blnIsTradeCloseTimeZone =
                WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone();

            //トリガー発行あり
            boolean l_blnIsSubmitMarketTrigger =
                WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(l_strOrderConditionType);

            //（*2）（休憩時間帯）　@or　@（トリガー発行あり）の場合
            if (l_blnIsTradeCloseTimeZone || l_blnIsSubmitMarketTrigger)
            {
                HostFotypeOrderAllParams l_hostFotypeOrderAllParams = null;
                //休憩時間帯（is取引所休憩時間帯==true）の場合
                if (l_blnIsTradeCloseTimeZone)
                {
                    //get先物OP注文取引キュー(IfoOrderUnit)
                    //先物OP注文取引キューを取得する。
                    //[先物OP発注サービス.get先物OP注文取引キュー()に指定する引数]
                    //注文単位：　@注文単位
                    l_hostFotypeOrderAllParams =
                        l_orderService.getHostFotypeOrderAll((IfoOrderUnit)l_orderUnit);
                }

                //キューデータ存在しない場合、insert
                if (l_hostFotypeOrderAllParams == null)
                {
                    l_hostFotypeOrderAllParams =
                        new HostFotypeOrderAllParams();

                    //データコード
                    l_hostFotypeOrderAllParams.setRequestCode(ORDER_REQUEST_CODE);
                    //口座ＩＤ
                    l_hostFotypeOrderAllParams.setAccountId(l_orderUnit.getAccountId());
                    //証券会社コード
                    l_hostFotypeOrderAllParams.setInstitutionCode(l_strInstitutionCode);
                    //部店コード
                    l_hostFotypeOrderAllParams.setBranchCode(l_strBranchCode);
                    //顧客コード
                    l_hostFotypeOrderAllParams.setAccountCode(l_strAccountCode);
                    //扱者コード
                    l_hostFotypeOrderAllParams.setTraderCode(l_strTraderCode);
                    //受注日区分
                    l_hostFotypeOrderAllParams.setReceivedDateTimeDiv(l_strOrderDateDiv);
                    //識別コード
                    l_hostFotypeOrderAllParams.setOrderRequestNumber(l_strOrderRequestNumber);
                    //市場コード（SONAR）
                    l_hostFotypeOrderAllParams.setSonarMarketCode(l_ifoOrderUnitRow.getSonarMarketCode());
                    //銘柄コード
                    l_hostFotypeOrderAllParams.setProductCode(l_strProductCode);
                    //受注日時
                    l_hostFotypeOrderAllParams.setReceivedDateTime(l_tsCreateDateTime);
                    //注文履歴番号
                    l_hostFotypeOrderAllParams.setOrderActionSerialNo(l_intOrderActionSerialNo);
                    //発注経路区分
                    l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv(l_ifoOrderUnitRow.getSubmitOrderRouteDiv());
                    //原資産銘柄コード
                    l_hostFotypeOrderAllParams.setTargetProductCode(l_ifoProductImpl.getUnderlyingProductCode());
                    //限月（年）
                    l_hostFotypeOrderAllParams.setDeliveryMonthYyyy(
                        l_ifoProductImpl.getMonthOfDelivery().substring(0, 4));
                    //限月（月）
                    l_hostFotypeOrderAllParams.setDeliveryMonthMm(
                        l_ifoProductImpl.getMonthOfDelivery().substring(4, 6));
                    //先物オプション商品
                    l_hostFotypeOrderAllParams.setFutureOptionProductType(l_strFutureOptionProductType);
                    //行使価格
                    l_hostFotypeOrderAllParams.setStrikePrice(l_ifoProductImpl.getStrikePrice());
                    //分割
                    l_hostFotypeOrderAllParams.setSplitType(l_ifoProductRow.getSplitType());
                    //売付数量
                    l_hostFotypeOrderAllParams.setSellOrderQuantity(l_dblSellOrderQuantity);
                    //買付数量
                    l_hostFotypeOrderAllParams.setBuyOrderQuantity(l_dblBuyOrderQuantity);
                    //指値
                    l_hostFotypeOrderAllParams.setLimitPrice(l_ifoOrderUnitRow.getConfirmedPrice());
                    //執行条件
                    l_hostFotypeOrderAllParams.setExecutionCondition(l_strConfirmedExecutionCondition);
                    //逆指値基準値
                    l_hostFotypeOrderAllParams.setStopOrderPrice(null);
                    //（W指値）訂正指値
                    l_hostFotypeOrderAllParams.setWLimitPrice(null);
                    //取引区分
                    l_hostFotypeOrderAllParams.setTransactionType(null);
                    //伝票№
                    l_hostFotypeOrderAllParams.setTicketNumber(null);
                    //建玉チェック
                    l_hostFotypeOrderAllParams.setContractCheck(null);
                    //注文チャネル
                    l_hostFotypeOrderAllParams.setOrderChanel(null);
                    //手数料№
                    l_hostFotypeOrderAllParams.setCommisionNumber(null);
                    //手数料№枝番
                    l_hostFotypeOrderAllParams.setCommisionBranchNumber(null);
                    //手数料商品コード
                    l_hostFotypeOrderAllParams.setCommisionProductCode(null);
                    //訂正数量
                    l_hostFotypeOrderAllParams.setChangeQuantity(null);
                    //訂正指値
                    l_hostFotypeOrderAllParams.setChangeLimitPrice(null);
                    //訂正執行条件
                    l_hostFotypeOrderAllParams.setChangeExecutionCondition(null);
                    //訂正逆指値基準値
                    l_hostFotypeOrderAllParams.setChangeStopOrderPrice(null);
                    //訂正（W指値）訂正指値
                    l_hostFotypeOrderAllParams.setChangeWLimitPrice(null);
                    //取消区分
                    l_strCancelDiv = WEB3CancelDivDef.CANCEL;
                    l_hostFotypeOrderAllParams.setCancelDiv(l_strCancelDiv);
                    //フロント発注取引所区分コード
                    l_hostFotypeOrderAllParams.setFrontOrderExchangeCode(
                        l_orderService.getFrontOrderExchangeCode(l_market.getMarketCode()));
                    //フロント発注システム区分
                    l_hostFotypeOrderAllParams.setFrontOrderSystemCode(
                        l_orderService.getFrontOrderSystemCode(l_market.getMarketCode()));
                    //フロント発注取引区分コード
                    l_hostFotypeOrderAllParams.setFrontOrderTradeCode(
                        WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE);
                    //自己委託区分
                    l_hostFotypeOrderAllParams.setTradeauditCode(null);
                    //社内処理項目
                    l_hostFotypeOrderAllParams.setCorpCode(
                        l_orderService.getOrgCorpCode((IfoOrderUnit)l_orderUnit));
                    //（被訂正）社内処理項目
                    l_hostFotypeOrderAllParams.setOrgCorpCode(
                        l_orderService.getOrgCorpCode((IfoOrderUnit)l_orderUnit));
                    //仮想サーバNo.（JSOES）
                    l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes(null);
                    //市場発注No.
                    l_hostFotypeOrderAllParams.setMarketOrderNumber(null);
                    //AMG送信時刻
                    l_hostFotypeOrderAllParams.setAmgSendTime(null);
                    //AMG入力保証受信時刻
                    l_hostFotypeOrderAllParams.setAmgAckTime(null);
                    //市場入力保証受信時刻
                    l_hostFotypeOrderAllParams.setMarketAckTime(null);
                    //全訂正処理区分
                    l_hostFotypeOrderAllParams.setAllOrderChangeDiv(
                        WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE);
                    //処理区分
                    l_hostFotypeOrderAllParams.setStatus(l_strStatus);
                    try
                    {
                        QueryProcessor l_processor = Processors.getDefaultProcessor();

                        l_processor.doInsertQuery(l_hostFotypeOrderAllParams);
                    }
                    catch (DataException l_ex)
                    {
                        WEB3SystemLayerException l_sysException =
                            new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                                this.getClass().getName() + "." + METHOD_NAME,
                                l_ex.getMessage(),
                                l_ex);

                        log.error("__an unexpected error__",l_sysException);
                        l_processingResult = ProcessingResult.newFailedResultInstance(
                            l_sysException.getErrorInfo());

                        return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
                    }
                }
                //キューデータ存在する場合、キューデータupdate
                else
                {
                    try
                    {
                        QueryProcessor l_processor = Processors.getDefaultProcessor();

                        //受注日時
                        l_hostFotypeOrderAllParams.setReceivedDateTime(l_tsCreateDateTime);
                        //set注文履歴番号
                        l_hostFotypeOrderAllParams.setOrderActionSerialNo(l_intOrderActionSerialNo);
                        //set発注経路区分
                        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv(
                            l_ifoOrderUnitRow.getSubmitOrderRouteDiv());
                        //取引区分
                        l_hostFotypeOrderAllParams.setTransactionType(null);
                        //伝票№
                        l_hostFotypeOrderAllParams.setTicketNumber(null);
                        //建玉チェック
                        l_hostFotypeOrderAllParams.setContractCheck(null);
                        //注文チャネル
                        l_hostFotypeOrderAllParams.setOrderChanel(null);
                        //手数料№
                        l_hostFotypeOrderAllParams.setCommisionNumber(null);
                        //手数料№枝番
                        l_hostFotypeOrderAllParams.setCommisionBranchNumber(null);
                        //手数料商品コード
                        l_hostFotypeOrderAllParams.setCommisionProductCode(null);
                        //訂正数量
                        l_hostFotypeOrderAllParams.setChangeQuantity(null);
                        //訂正指値
                        l_hostFotypeOrderAllParams.setChangeLimitPrice(null);
                        //訂正執行条件
                        l_hostFotypeOrderAllParams.setChangeExecutionCondition(null);
                        //訂正逆指値基準値
                        l_hostFotypeOrderAllParams.setChangeStopOrderPrice(null);
                        //訂正（W指値）訂正指値
                        l_hostFotypeOrderAllParams.setChangeWLimitPrice(null);
                        //取消区分
                        l_hostFotypeOrderAllParams.setCancelDiv(WEB3CancelDivDef.CANCEL);
                        //社内処理項目
                        l_hostFotypeOrderAllParams.setCorpCode(
                            l_orderService.getOrgCorpCode((IfoOrderUnit)l_orderUnit));
                        //（被訂正）社内処理項目
                        l_hostFotypeOrderAllParams.setOrgCorpCode(
                            l_orderService.getOrgCorpCode((IfoOrderUnit)l_orderUnit));
                        //更新日付
                        l_hostFotypeOrderAllParams.setLastUpdatedTimestamp(l_tsCreateDateTime);
                        l_processor.doUpdateQuery(l_hostFotypeOrderAllParams);
                    }
                    catch (DataException l_ex)
                    {
                        WEB3SystemLayerException l_sysException =
                            new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                                this.getClass().getName() + "." + METHOD_NAME,
                                l_ex.getMessage(),
                                l_ex);

                        log.error("__an unexpected error__",l_sysException);
                        l_processingResult = ProcessingResult.newFailedResultInstance(
                            l_sysException.getErrorInfo());

                        return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
                    }
                }
                //isトリガ発行()をコール
                l_isTriggerIssue =
                    WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(
                        null);

                String l_strMarketCode = l_market.getMarketCode();

                //isMQトリガ発行経路()
                //MQトリガ発行要否を取得する。
                //[isMQトリガ発行経路()に指定する引数]
                //証券会社コード：　@注文単位.証券会社IDに該当する証券会社コード
                //銘柄タイプ：　@注文単位.銘柄タイプ
                //市場コード：　@注文単位.市場IDに該当する市場コード
                //発注経路区分：　@注文単位.発注経路区分
                //フロント発注システム区分：　@先物OP注文取引キュー.フロント発注システム区分
                boolean l_blnIsSubmitMQTriggerEnable = WEB3GentradeOrderSwitching.isSubmitMQTriggerEnable(
                    l_strInstitutionCode,
                    l_ifoOrderUnitRow.getProductType(),
                    l_strMarketCode,
                    l_ifoOrderUnitRow.getSubmitOrderRouteDiv(),
                    l_hostFotypeOrderAllParams.getFrontOrderSystemCode());
                //トリガー発行時間帯&&MQトリガ発行経路の場合のみ処理実施
                if (l_isTriggerIssue && l_blnIsSubmitMQTriggerEnable)
                {
                    String l_strMQDataCode =
                        l_orderService.getChangeCancelMQDataCode(
                            (IfoOrderUnit)l_orderUnit);
                    if (l_strMQDataCode == null)
                    {
                        log.exiting(METHOD_NAME);
                        return DefaultMarketRequestSendResult.newSuccessResultInstance(
                            l_lngMsgTokenId);
                    }
                    //WEB3MQMessageSpec(証券会社コード : String, データコード : String)
                    //WEB3MQMessageSpecを生成する。
                    //[コンストラクタに指定する引数]
                    //証券会社コード：
                    //　@注文単位.証券会社IDに該当する証券会社コード
                    //データコード：
                    //　@先物OP発注サービス.get訂正取消時MQデータコード()の戻り値
                    l_web3MQMessageSpec =
                        new WEB3MQMessageSpec(
                            l_strInstitutionCode,
                            l_strMQDataCode);

                    //MQサービスを取得する
                    l_web3MQGatewayService =
                        (WEB3MQGatewayService) Services.getService(
                            WEB3MQGatewayService.class);

                    //send(MQメッセージ内容 : WEB3MQMessageSpec)
                    l_web3MQSendResult =
                        l_web3MQGatewayService.send(l_web3MQMessageSpec);

                    if (l_web3MQSendResult.isSuccessResult())
                    {
                        log.exiting(METHOD_NAME);
                        return DefaultMarketRequestSendResult.newSuccessResultInstance(l_lngMsgTokenId);
                    }
                    else
                    {
                        l_processingResult =
                            ProcessingResult.newFailedResultInstance(
                                l_web3MQSendResult.getErrorInfo());

                        log.exiting(METHOD_NAME);
                        return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
                    }
                }
            }
            //市場送信済注文で市場閉局時間帯（is市場未送信 == false && is市場開局時間帯() == false)の場合、
            //または、市場送信済注文で夕場時間帯で日中登録注文
            //（is市場未送信 == false && 取引時間管理.is夕場時間帯() == true && 注文単位.立会区分 == null）の場合、
            //取消を確定させて処理を終了する。
            if ((!l_blnIsMarketNotSendMessage
                && !l_blnIsOpenTimeZone)
                || (!l_blnIsMarketNotSendMessage
                    && WEB3GentradeTradingTimeManagement.isEveningSessionTimeZone()
                    && WEB3StringTypeUtility.isEmpty(l_ifoOrderUnitRow.getSessionType())))
            {
                //取消確定
                this.updateOrderCancelled(l_orderUnit);
            }
        }
		catch (WEB3SystemLayerException l_sle)
		{
			WEB3SystemLayerException l_sysException = new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + METHOD_NAME,
			    l_sle.getMessage(),
                l_sle);
			log.error("__an unexpected error__",l_sysException);
			ProcessingResult l_processingResult = ProcessingResult.newFailedResultInstance(
				l_sysException.getErrorInfo());

            return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
		}
        catch (NotFoundException l_nfe)
        {
            WEB3SystemLayerException l_sysException = new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);

            log.error("__an unexpected error__",l_sysException);
            ProcessingResult l_processingResult = ProcessingResult.newFailedResultInstance(
                l_sysException.getErrorInfo());

            return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
        }
        catch (WEB3BaseException l_wbe)
        {
            log.error("__an unexpected error__",l_wbe);
            ProcessingResult l_processingResult = ProcessingResult.newFailedResultInstance(
                    l_wbe.getErrorInfo());

            return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
        }
        log.exiting(METHOD_NAME);
        return DefaultMarketRequestSendResult.newSuccessResultInstance(0L);

   }


    /**
     * (訂正確定)<BR>
     * <BR>
     * 訂正を確定させる。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物OP市場ﾘｸｴｽﾄ）訂正確定」参照。<BR>
     * @@param l_orderUnit - 注文単位オブジェクト<BR>
     * @@roseuid 40A463D90009
     */
    protected void updateOrderModified(OrderUnit l_orderUnit)
    throws WEB3BaseException
    {
        final String METHOD_NAME = "validateChangeOrder(OrderUnit)";
        log.entering(METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.error("parameter is null type");
            throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + METHOD_NAME);
        }

        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);//throw NotInstalledException

            WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();


            //先物OP訂正確定更新インタセプタ
            WEB3IfoChangeConfirmUpdateInterceptor l_interceptor =
                             new WEB3IfoChangeConfirmUpdateInterceptor();
            //OP注文マネージャ
            l_orderManager.setThreadLocalPersistenceEventInterceptor(
                             l_interceptor);

            long l_lngOrderId = l_orderUnit.getOrderId();

            DefaultChangeOrderAcceptedMarketResponseMessage l_changeResponseMessage =
                new DefaultChangeOrderAcceptedMarketResponseMessage(l_lngOrderId);

            MarketAdapter l_markertAdapter = l_tradingModule.getMarketAdapter();

            IfoMarketResponseReceiverCallbackService l_callBackService =
            (IfoMarketResponseReceiverCallbackService) l_markertAdapter.getMarketResponseReceiverCallbackService();

            ProcessingResult l_processingResult = l_callBackService.process(l_changeResponseMessage);

            if (l_processingResult.isFailedResult())
            {
                throw new WEB3SystemLayerException(l_processingResult.getErrorInfo(),
                    this.getClass().getName() + "." + METHOD_NAME);
            }
        }
        catch (NotInstalledException l_nie)
        {
            log.error(l_nie.getMessage(),l_nie);
            log.exiting(METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + METHOD_NAME,
                l_nie.toString(),
                l_nie);
        }
    }

    /**
     * (取消確定)<BR>
     * <BR>
     * 取消を確定させる。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物OP市場ﾘｸｴｽﾄ）取消確定」参照。<BR>
     * @@param l_orderUnit - 注文単位オブジェクト<BR>
     * @@roseuid 40A81D830072
     */
    protected void updateOrderCancelled(OrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String METHOD_NAME = "validateCancelOrder(OrderUnit)";
        log.entering(METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.error("parameter is null type");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + METHOD_NAME);
        }

        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);

            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();


            MarketAdapter l_marketAdapter = l_tradingModule.getMarketAdapter();
            //先物OP取消確定更新インタセプタ
            WEB3IfoCancelConfirmUpdateInterceptor l_interceptor =
                       new WEB3IfoCancelConfirmUpdateInterceptor();
            //OP注文マネージャ
            l_orderManager.setThreadLocalPersistenceEventInterceptor(
                       l_interceptor);

            long l_lngOrderId = l_orderUnit.getOrderId();

            DefaultCancelOrderAcceptedMarketResponseMessage l_cancelResponseMessage =
                new DefaultCancelOrderAcceptedMarketResponseMessage(l_lngOrderId);


            IfoMarketResponseReceiverCallbackService l_callBackService =
                (IfoMarketResponseReceiverCallbackService) l_marketAdapter.getMarketResponseReceiverCallbackService();

            ProcessingResult l_processingResult = l_callBackService.process(l_cancelResponseMessage);
            
            if (l_processingResult.isFailedResult())
            {
                throw new WEB3SystemLayerException(l_processingResult.getErrorInfo(),
                    this.getClass().getName() + "." + METHOD_NAME);
            }
        }
        catch (NotInstalledException l_nie)
        {
            log.error(l_nie.getMessage(),l_nie);
            log.exiting(METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + METHOD_NAME,
                l_nie.toString(),
                l_nie);
        }
    }

    /**
      * @@param arg0
      * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSendResult
      * @@roseuid 40C0D45800DA
      */
    public MarketRequestSendResult send(MarketRequestMessage arg0)
    {
        return null;
    }
}
@
