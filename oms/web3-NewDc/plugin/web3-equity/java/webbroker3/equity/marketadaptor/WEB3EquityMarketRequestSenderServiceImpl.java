head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityMarketRequestSenderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 市場リクエスト送信サービス(WEB3EquityMarketRequestSenderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/06 本郷　@千草(SRA) 新規作成
Revesion History : 2006/07/06 肖志偉 (中訊) 仕様変更モデル949
Revesion History : 2006/11/01 趙林鵬 (中訊) モデル No.1023
Revesion History : 2007/04/25 謝旋(中訊) モデル1138
Revesion History : 2007/12/17 金傑(中訊) モデル1219、1230、1244
Revesion History : 2007/12/26 金傑(中訊) モデル1273
*/

package webbroker3.equity.marketadaptor;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeMarketRequestSenderService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProductManager;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.EqTypeChangeOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.EqTypeChangeSettleContractOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.EqTypeChangeSwapContractOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.EqTypeNewCashBasedOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.EqTypeOpenContractOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.EqTypeSettleContractOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.EqTypeSwapContractOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeProductImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.DefaultMarketRequestSendResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSendResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TooLateException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.CancelOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.MarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.MarketResponseMessage;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AllOrderChangeDivDef;
import webbroker3.common.define.WEB3CancelDivDef;
import webbroker3.common.define.WEB3CapitalGainTaxTypeDef;
import webbroker3.common.define.WEB3CheckTypeDef;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3FrontOrderStatusDef;
import webbroker3.common.define.WEB3FrontOrderTradeCodeDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3HostStatusDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderDateDivDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.common.define.WEB3ResidentDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3TaxTypeDef;
import webbroker3.equity.WEB3EquityCancelOrderConfirmInterceptor;
import webbroker3.equity.WEB3EquityChangeConfirmInterceptor;
import webbroker3.equity.WEB3EquityOrderAcceptPersistenceInterceptor;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3MarginSwapMarginAcceptInterceptor;
import webbroker3.equity.data.HostEqtypeOrderAllParams;
import webbroker3.equity.data.HostEqtypeSwapParams;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeOrderSwitching;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.mqgateway.WEB3MQGatewayService;
import webbroker3.mqgateway.WEB3MQMessageSpec;
import webbroker3.mqgateway.WEB3MQSendResult;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * （市場リクエスト送信サービス）。<BR>
 * <BR>
 * SONARへ発注リクエストを行うサービス。<BR>
 * （EqTypeMarketRequestSenderServiceの実装）
 * @@version 1.0
 */
public class WEB3EquityMarketRequestSenderServiceImpl
    implements EqTypeMarketRequestSenderService
{

    /**
     * （ログ出力ユーティリティ）。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityMarketRequestSenderServiceImpl.class);

    /**
     * (データコード付加文字)。
     */
    private static final String DATA_CODE = "T";
    
    /**
     * （新規注文送信）。<BR>
     * <BR>
     * 新規注文の送信を行う。<BR>
     * （send(EqTypeNewCashBasedOrderMarketRequestMessage)のオーバーライド）<BR>
     * <BR>
     * 株式ミニ投資買付注文サービス、株式ミニ投資売付注文サービス、<BR>
     * 株式注文サービス、注文繰越サービス、注文通知サービスより<BR>
     * コールされる。<BR>
     * -------------------------------------------------------------------<BR>
     * ○ミニ株の場合<BR>
     * 　@－処理なし。<BR>
     * <BR>
     * ○ミニ株以外、かつWebⅢ入力注文の場合（注文経路区分≠HOST）<BR>
     * 　@－【株式注文キューテーブル】に対し、発注データをInsert等する。<BR>
     * 　@－MQトリガを発行する。<BR>
     * <BR>
     * ○ミニ株以外、かつSONAR入力注文の場合（注文経路区分＝HOST）<BR>
     * 　@－「新規注文受付OK」の処理を行い、注文ステータスを"発注済"にする。<BR>
     * -------------------------------------------------------------------<BR>
     * <BR>
     * １）　@注文取得<BR>
     * 　@注文リクエストメッセージ.getOrderId() にて注文IDを取得する。<BR>
     * 　@注文マネージャにて、注文IDに該当する注文オブジェクトを取得する。<BR>
     * 　@注文オブジェクト.getOrderUnits( )で<BR>
     * 　@取得した注文単位オブジェクトの0番目の要素について、以下の処理を行う。<BR>
     * <BR>
     * １－１）　@ミニ株の場合<BR>
     * 　@ミニ株の場合(*1)、以降の処理を行わず終了する（return）。<BR>
     * 　@　@(*1) ミニ株の判定<BR>
     * 　@　@注文単位.注文種別 = OrderTypeEnum.MINI_STOCK_SELL（株式ミニ投資売注文） Or<BR>
     * 　@　@注文単位.注文種別 = OrderTypeEnum.MINI_STOCK_BUY（株式ミニ投資買注文）<BR>
     * <BR>
     * １－２）　@ミニ株でない場合<BR>
     * 　@取得した注文単位．注文経路区分≠"HOST"の場合（＝WebⅢ入力注文）の処理は<BR>
     * シーケンス図「（現物株式市場ﾘｸｴｽﾄ）新規注文送信」を参照。<BR>
     * 　@取得した注文単位．注文経路区分＝"HOST"の場合（＝SONAR入力注文）の処理は<BR>
     * シーケンス図「（現物株式市場ﾘｸｴｽﾄ）新規注文送信（注文通知）」を参照。
     * @@param l_requestMessage （株式注文リクエストメッセージ）<BR>
     * 株式注文リクエストメッセージ
     * @@return MarketRequestSendResult
     */
    public MarketRequestSendResult send(EqTypeNewCashBasedOrderMarketRequestMessage l_requestMessage)
    {
        final String STR_METHOD_NAME =
            "send(EqTypeNewCashBasedOrderMarketRequestMessage)";
        log.entering(STR_METHOD_NAME);
        long l_lngMsgTokenId = 0;

        try
        {
            long l_lngOrderId = l_requestMessage.getOrderId();
    
            String l_strInstitutionCode = null;
            boolean l_isTriggerIssue = false;
            String l_strOrderConditionType = null;
            String l_strOrderRootDiv = null;
    
            EqtypeOrderUnitRow l_eqtypeOderUnitRow = null;
    
            WEB3MQMessageSpec l_web3MQMessageSpec = null;
            WEB3MQGatewayService l_web3MQGatewayService;
            WEB3MQSendResult l_web3MQSendResult;
    
            DefaultNewOrderAcceptedMarketResponseMessage l_newOrderResponseMessage =
                null;
    
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
    
            AccountManager l_accountManager = l_finApp.getAccountManager();
    
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

            //拡張注文マネージャを取得
            WEB3EquityOrderManager l_equityOrderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

            if (l_requestMessage == null)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //注文取得
            OrderUnit[] l_orderUnits = l_equityOrderManager.getOrderUnits(l_lngOrderId);

            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];

            l_eqtypeOderUnitRow =
                (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

            // １－１）　@ミニ株の場合 <BR>                                                       
            // 　@　@注文単位.注文種別 = OrderTypeEnum.MINI_STOCK_SELL（株式ミニ投資売注文）Or
            // 　@　@注文単位.注文種別 = OrderTypeEnum.MINI_STOCK_BUY（株式ミニ投資買注文)
            if(l_orderUnit.getOrderType().equals(OrderTypeEnum.MINI_STOCK_SELL) 
            || l_orderUnit.getOrderType().equals(OrderTypeEnum.MINI_STOCK_BUY))
            {
                
                log.debug("ミニ株の場合");
                return DefaultMarketRequestSendResult.newSuccessResultInstance(l_lngMsgTokenId);
                   
            }
            //１－２）　@ミニ株でない場合 
            else
            {
                //注文経路区分
                l_strOrderRootDiv = l_eqtypeOderUnitRow.getOrderRootDiv();

                log.debug(
                    "注文経路区分" + WEB3OrderRootDivDef.HOST + "?=" + l_strOrderRootDiv);
                if (!WEB3OrderRootDivDef.HOST.equals(l_strOrderRootDiv))
                {
                    log.debug("注文経路区分≠'HOST'の場合（＝WebⅢ入力注文）");
                    //取得した注文単位．注文経路区分≠"HOST"の場合（＝WebⅢ入力注文）

                    l_equityOrderManager.notifyRLS(
                        l_orderUnit,
                        OrderManagerPersistenceContext.NEW_CASH_BASED_ORDER);

                    if (WEB3OrderingConditionDef
                        .STOP_LIMIT_PRICE
                        .equals(l_eqtypeOderUnitRow.getOrderConditionType()))
                    {
                        log.debug("注文単位.発注条件＝”逆指値”の場合は、注文キューに登録しない。");
                        return DefaultMarketRequestSendResult.newSuccessResultInstance(
                            l_lngMsgTokenId);
                    }

                    //部店を取得する
                    Branch l_banch =
                        l_accountManager.getBranch(
                            l_eqtypeOderUnitRow.getBranchId());
                    //証券会社コードを取得する
                    l_strInstitutionCode =
                        l_banch.getInstitution().getInstitutionCode();
                    
                    l_equityOrderManager.insertEquityHostOrder(l_lngOrderId);

                    // 発注経路区分
                    String l_strSubmitOrderRouteDiv =
                        l_eqtypeOderUnitRow.getSubmitOrderRouteDiv();
                    
                    // 市場オブジェクトから市場コードを取る
                    WEB3EquityFrontOrderService l_frontOrderService =
                        (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
                    WEB3GentradeFinObjectManager l_finObjectManager =
                        (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                    Market l_market =
                        l_finObjectManager.getMarket(l_eqtypeOderUnitRow.getMarketId());
                    String l_strMarketCode = l_market.getMarketCode();

                    // getMarket()の戻り値.getMarketCode() == "PTS市場"の場合
                    if (WEB3MarketCodeDef.JNX_PTS.equals(l_strMarketCode))
                    {
                        log.exiting(STR_METHOD_NAME);
                        return DefaultMarketRequestSendResult.newSuccessResultInstance(l_lngMsgTokenId);
                    }

                    // フロント発注システム区分
                    EqTypeTradedProduct l_tradedProduct =
                        (EqTypeTradedProduct)l_orderUnit.getTradedProduct();
                    EqtypeTradedProductRow l_tradedProductRow =
                        (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
                    String l_strFrontOrderSystemCode =
                        l_frontOrderService.getFrontOrderSystemCode(
                            l_strMarketCode,
                            l_tradedProductRow.getOpenOtcDiv());

                    //発注条件を取得
                    l_strOrderConditionType =
                        l_eqtypeOderUnitRow.getOrderConditionType();

                    l_isTriggerIssue =
                        WEB3GentradeTradingTimeManagement
                            .isSubmitMarketTrigger(
                            l_strOrderConditionType);

                    boolean l_isSubmitMqTrigger =
                        WEB3GentradeOrderSwitching.isSubmitMQTriggerEnable(
                            l_strInstitutionCode,
                            l_eqtypeOderUnitRow.getProductType(),
                            l_strMarketCode,
                            l_strSubmitOrderRouteDiv,
                            l_strFrontOrderSystemCode);
                    
                    if (l_isTriggerIssue && l_isSubmitMqTrigger)
                    {
                        String l_strMQDataCode =
                            l_frontOrderService.getOrderMQDataCode(l_strSubmitOrderRouteDiv);
                        if (l_strMQDataCode == null)
                        {
                            log.debug("新規注文送信 成功（発注停止中）！！！");
                            return DefaultMarketRequestSendResult
                                .newSuccessResultInstance(
                                l_lngMsgTokenId);
                        }

                        l_web3MQGatewayService =
                            (WEB3MQGatewayService)Services.getService(
                                WEB3MQGatewayService.class);

                        l_web3MQMessageSpec =
                            new WEB3MQMessageSpec(
                                l_strInstitutionCode,
                                l_strMQDataCode);

                        l_web3MQSendResult =
                            l_web3MQGatewayService.send(l_web3MQMessageSpec);

                        if (l_web3MQSendResult.isSuccessResult())
                        {
                            log.debug("新規注文送信 成功！！！");
                            return DefaultMarketRequestSendResult
                                .newSuccessResultInstance(
                                l_lngMsgTokenId);
                        }
                        else
                        {
                            log.debug("新規注文送信 失敗 ！！！");
                            ProcessingResult l_processingResult =
                                ProcessingResult.newFailedResultInstance(
                                    l_web3MQSendResult.getErrorInfo());
                            return DefaultMarketRequestSendResult
                                .newFailedResultInstance(
                                l_processingResult);
                        }
                    }
                }
                else
                {
                    log.debug("注文経路区分＝'HOST'の場合（＝SONAR入力注文）");
                    //取得した注文単位.注文経路区分＝"HOST"の場合（＝SONAR入力注文）
                    WEB3EquityOrderAcceptPersistenceInterceptor l_orderAccepterInterceptor =
                        new WEB3EquityOrderAcceptPersistenceInterceptor(null);
                    l_equityOrderManager.setThreadLocalPersistenceEventInterceptor(l_orderAccepterInterceptor);
                    EqTypeMarketResponseReceiverCallbackService l_callBackService =
                        (EqTypeMarketResponseReceiverCallbackService)l_tradingModule
                            .getMarketAdapter()
                            .getMarketResponseReceiverCallbackService();
                    l_newOrderResponseMessage =
                        new DefaultNewOrderAcceptedMarketResponseMessage(l_lngOrderId);
                    ProcessingResult l_processingResult =
                        l_callBackService.process(
                            (MarketResponseMessage)l_newOrderResponseMessage);
                    if (l_processingResult.isFailedResult())
                    {
                        return DefaultMarketRequestSendResult
                            .newFailedResultInstance(
                            l_processingResult);
                    }
                    return DefaultMarketRequestSendResult.newSuccessResultInstance(
                        l_lngMsgTokenId);
                }
            }
        }
        catch (NotFoundException nfe)
        {
            WEB3SystemLayerException l_sysException =
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    nfe.getMessage(),
                    nfe);
            log.error("__an unexpected error__", l_sysException);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(
                    l_sysException.getErrorInfo());
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);

        }
        catch (WEB3BaseException wbe)
        {
            log.error("__an unexpected error__", wbe);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(wbe.getErrorInfo());
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }

        log.exiting(STR_METHOD_NAME);
        return DefaultMarketRequestSendResult.newSuccessResultInstance(
            l_lngMsgTokenId);
    }

    /**
     * （取消注文送信）。<BR>
     * <BR>
     * 注文取消の送信を行う。<BR>
     * （send(CancelOrderMarketRequestMessage, boolean)のオーバーライド）<BR>
     * <BR>
     * 注文取消サービス、信用取引取消サービスより<BR>
     * コールされる。<BR>
     * -------------------------------------------------------------------<BR>
     *   －取消対象注文がミニ株注文の場合は、何もせずにreturnする。<BR>
     * <BR>
     * 　@－取消対象注文が市場未送信の場合<BR>
     * 　@　@　@・現引現渡注文ならば、【現引現渡キューテーブル】から対象データをDeleteする。<BR>
     * 　@　@　@・現引現渡注文以外ならば、【株式注文キューテーブル】から対象データをDeleteする。<BR>
     * <BR>
     * 　@－取消対象注文が市場送信済の場合<BR>
     * 　@　@　@・市場開局時間帯(ホスト送信時間帯)の場合、対象キューテーブル(*)に取消データをInsertし、<BR>
     * 　@　@　@　@MQトリガを発行する。<BR>
     * 　@　@　@　@(*)対象キューテーブル<BR>
     * 　@　@　@　@　@現引現渡注文ならば、【現引現渡キューテーブル】<BR>
     * 　@　@　@　@　@現引現渡注文以外ならば、【株式注文訂正取消キューテーブル】<BR>
     * 　@　@　@・市場閉局時間帯(ホスト送信時間帯外)の場合、取消を確定させる。<BR>
     * -------------------------------------------------------------------<BR>
     * <BR>
     * １）　@注文取得<BR>
     * 　@株式注文取消リクエストメッセージ.getOrderId() にて注文IDを取得する。<BR>
     * 　@注文マネージャにて、注文IDに該当する注文オブジェクトを取得する。<BR>
     * 　@注文オブジェクト.getOrderUnits( )で<BR>
     * 　@取得した注文単位オブジェクトの0番目の要素について、ミニ株以外の場合は以下の処理を行う。<BR>
     * <BR>
     * 以下、注文が市場未送信か送信済かにより、処理が分かれる。<BR>
     * <BR>
     * 注文が市場未送信の場合（引数.is市場未送信 == true）は、<BR>
     * シーケンス図「（現物株式_信用取引市場ﾘｸｴｽﾄ）取消注文送信（市場未送信注文）」を参照。<BR>
     * 注文が市場送信済の場合（引数.is市場未送信 == false）は、<BR>
     * シーケンス図「（現物株式_信用取引市場ﾘｸｴｽﾄ）取消注文送信（市場送信済注文）」を参照。
     * @@param l_requestMessage （株式注文取消リクエストメッセージ）<BR>
     * 株式注文取消リクエストメッセージ
     * @@param l_isUnSend （is市場未送信）<BR>
     * 原注文が市場未送信の場合はtrue、<BR>
     * 原注文が市場送信済の場合はfalseを指定する。<BR>
     * falseの場合、SONARへ取消を通知する。<BR>
     * @@return MarketRequestSendResult
     * @@throws TooLateException
     */
    public MarketRequestSendResult send(
        CancelOrderMarketRequestMessage l_requestMessage,
        boolean l_isUnSend)
        throws TooLateException
    {
        final String STR_METHOD_NAME =
            "send(CancelOrderMarketRequestMessage,boolean)";
        log.entering(STR_METHOD_NAME);

        long l_lngMsgTokenId = 0L;
        boolean l_isTriggerIssue = false;

        try
        {
            long l_lngOrderId = l_requestMessage.getOrderId();
        
            if (l_requestMessage == null)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            AccountManager l_accountManager = l_finApp.getAccountManager();

            //注文取得
            Order l_order = new EqTypeOrderImpl(l_lngOrderId);
            OrderUnit[] l_orderUnits = l_order.getOrderUnits();
            OrderUnit l_orderUnit = l_orderUnits[0];
            EqtypeOrderUnitRow l_eqtypeOderUnitRow =
                (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
                
            // ミニ株の場合 <BR>                                                       
            // 　@　@注文単位.注文種別 = OrderTypeEnum.MINI_STOCK_SELL（株式ミニ投資売注文）Or
            // 　@　@注文単位.注文種別 = OrderTypeEnum.MINI_STOCK_BUY（株式ミニ投資買注文)
            if(l_orderUnit.getOrderType().equals(OrderTypeEnum.MINI_STOCK_SELL) 
            || l_orderUnit.getOrderType().equals(OrderTypeEnum.MINI_STOCK_BUY))
            {
                log.debug("ミニ株の場合");
                return DefaultMarketRequestSendResult.newSuccessResultInstance(l_lngMsgTokenId);
            }

            //顧客コードを取得する
            MainAccount l_mainAccount = l_mainAccount = l_accountManager
                .getMainAccount(l_eqtypeOderUnitRow.getAccountId());
            MainAccountRow l_mainAccountRow = (MainAccountRow) l_mainAccount.getDataSourceObject();
            String l_strAccountCode = l_mainAccount.getAccountCode();
            
            EqTypeProduct l_eqtypeProduct = (EqTypeProduct)l_tradingModule.getProductManager().getProduct(l_eqtypeOderUnitRow.getProductId());
            String l_strPoductCode = l_eqtypeProduct.getProductCode();
            //識別コードを取得する
            String l_strOrderRequestNumber =
                l_eqtypeOderUnitRow.getOrderRequestNumber();
            //部店を取得する
            Branch l_banch =
                l_accountManager.getBranch(l_eqtypeOderUnitRow.getBranchId());
            //証券会社コードを取得する
            String l_strInstitutionCode =
                l_banch.getInstitution().getInstitutionCode();
            //部店コードを取得する
            String l_strBranchCode = l_banch.getBranchCode();

            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

            // 市場コードを取得
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market =
                l_finObjectManager.getMarket(l_eqtypeOderUnitRow.getMarketId());
            String l_strMarketCode = l_market.getMarketCode();

            if (l_isUnSend)
            {
                try 
                {
                    l_orderManager.notifyRLS(
                        (EqTypeOrderUnit)l_orderUnit,
                        OrderManagerPersistenceContext.CANCEL_ORDER_CONFIRMED_BY_MKT);
                }
                catch (WEB3BusinessLayerException l_ble)
                {
                    log.debug("error in  l_orderMgr.notifyRLS", l_ble);
                }

                if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_eqtypeOderUnitRow.getOrderConditionType())
                    && !(WEB3RequestTypeDef.QUOTE_SERVER.equals(l_eqtypeOderUnitRow.getRequestType())))
                {
                    return DefaultMarketRequestSendResult.newSuccessResultInstance(
                        l_lngMsgTokenId);
                }

                QueryProcessor processor = Processors.getDefaultProcessor();
                log.debug("==> 現引現渡注文の場合のみ、実施する。");
                if(OrderCategEnum.SWAP_MARGIN.equals(l_eqtypeOderUnitRow.getOrderCateg()))
                {
                    String l_strWhere =" request_code=? and institution_code=? and branch_code=? and order_request_number=? and status=?";
                    String[] l_strBindValues = new String[5];
                    l_strBindValues[0] = WEB3HostRequestCodeDef.EQUITY_SPOT_ORDER;
                    l_strBindValues[1] = l_strInstitutionCode;
                    l_strBindValues[2] = l_strBranchCode;
                    l_strBindValues[3] = l_strOrderRequestNumber;
                    l_strBindValues[4] = WEB3HostStatusDef.NOT_STARTED_PROCESS;

                    //対象データ削除
                    int l_intResults = processor.doDeleteAllQuery(
                                                HostEqtypeSwapParams.TYPE,
                                                l_strWhere,
                                                l_strBindValues);
                    if (l_intResults == 0)
                    {
                        return DefaultMarketRequestSendResult
                            .newFailedResultInstance(
                                ProcessingResult.newFailedResultInstance(
                                    WEB3ErrorCatalog.BUSINESS_ERROR_00010));
                    }
                }
                else
                {
                    WEB3EquityFrontOrderService l_frontOrderService =
                        (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
                    int l_intIndex = l_frontOrderService.getIndexOfOrderRevInCorpCode();
                    int l_intFigure = l_frontOrderService.getFigureOfOrderRev();
                    String l_strWhere =" request_code=? and institution_code=? and branch_code=? and order_request_number=? and substr(corp_code," + l_intIndex + "," + l_intFigure + ")=? and status=?";
                    String[] l_strBindValues = new String[6];
                    l_strBindValues[0] = WEB3HostRequestCodeDef.EQUITY_ORDER;
                    l_strBindValues[1] = l_strInstitutionCode;
                    l_strBindValues[2] = l_strBranchCode;
                    l_strBindValues[3] = l_strOrderRequestNumber;
                    l_strBindValues[4] = l_eqtypeOderUnitRow.getOrderRev();
                    l_strBindValues[5] = WEB3FrontOrderStatusDef.NOT_DEAL;

                    //対象データ削除
                    int l_intResults = processor.doDeleteAllQuery(
                            HostEqtypeOrderAllParams.TYPE,
                            l_strWhere,
                            l_strBindValues);
                    if (l_intResults == 0)
                    {
                        return DefaultMarketRequestSendResult
                            .newFailedResultInstance(
                                ProcessingResult.newFailedResultInstance(
                                    WEB3ErrorCatalog.BUSINESS_ERROR_00010));
                    }
                }
            }
            else
            {
                log.debug("==> 市場送信済（is市場未送信 == false）の場合の処理");

                // getMarket()の戻り値.市場コード == "PTS市場"の場合
                if (WEB3MarketCodeDef.JNX_PTS.equals(l_strMarketCode))
                {
                    // 株式注文取引キューテーブルへ登録する。
                    // 登録内容は、DB更新仕様
                    //「(PTS)株式注文取消_株式注文取引キューテーブル.xls」 参照。

                    WEB3EquityFrontOrderService l_frontOrderService =
                        (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);

                    HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams = new HostEqtypeOrderAllParams();

                    // データコード
                    l_hostEqtypeOrderAllParams.setRequestCode(
                        WEB3HostRequestCodeDef.EQUITY_ORDER_CHANGE);
                    // 口座ID
                    l_hostEqtypeOrderAllParams.setAccountId(
                        l_eqtypeOderUnitRow.getAccountId());
                    // 証券会社コード
                    l_hostEqtypeOrderAllParams.setInstitutionCode(
                        l_strInstitutionCode);
                    // 部店コード
                    l_hostEqtypeOrderAllParams.setBranchCode(
                        l_strBranchCode);
                    // 口座コード
                    l_hostEqtypeOrderAllParams.setAccountCode(
                        l_strAccountCode);
                    // 扱者コード（SONAR）
                    l_hostEqtypeOrderAllParams.setSonarTraderCode(
                        l_eqtypeOderUnitRow.getSonarTraderCode());
                    // 識別コード
                    l_hostEqtypeOrderAllParams.setOrderRequestNumber(
                        l_strOrderRequestNumber);
                    // 注文履歴番号
                    l_hostEqtypeOrderAllParams.setOrderActionSerialNo(
                        l_eqtypeOderUnitRow.getLastOrderActionSerialNo());
                    // 銘柄コード
                    l_hostEqtypeOrderAllParams.setProductCode(
                        l_strPoductCode);
                    // 受注日時
                    l_hostEqtypeOrderAllParams.setReceivedDateTime(
                        GtlUtils.getTradingSystem().getSystemTimestamp());
                    // 発注経路区分
                    l_hostEqtypeOrderAllParams.setSubmitOrderRouteDiv(
                        l_eqtypeOderUnitRow.getSubmitOrderRouteDiv());
                    if (SideEnum.SELL.equals(l_orderUnit.getSide()))
                    {
                        // 売付数量
                        l_hostEqtypeOrderAllParams.setSellOrderQuantity(
                            l_eqtypeOderUnitRow.getConfirmedQuantity());
                        // 買付数量
                        l_hostEqtypeOrderAllParams.setBuyOrderQuantity(0D);
                    }
                    else
                    {
                        // 売付数量
                        l_hostEqtypeOrderAllParams.setSellOrderQuantity(0D);
                        // 買付数量
                        l_hostEqtypeOrderAllParams.setBuyOrderQuantity(
                            l_eqtypeOderUnitRow.getConfirmedQuantity());
                    }
                    // 指値
                    l_hostEqtypeOrderAllParams.setLimitPrice(
                        l_eqtypeOderUnitRow.getConfirmedPrice());
                    // 執行条件（SONAR）
                    String l_strConfirmedExecConditionType = null;
                    if (l_orderManager.isCarriedOrderUnit((EqTypeOrderUnit)l_orderUnit))
                    {
                        l_strConfirmedExecConditionType =
                            WEB3ExecutionConditionDef.COME_TO_TERMS;
                    }
                    else
                    {
                        l_strConfirmedExecConditionType =
                        l_orderManager.getExecutionConditionTypeSonar(
                                l_eqtypeOderUnitRow.getConfirmedExecConditionType());
                    }
                    l_hostEqtypeOrderAllParams.setExecutionCondition(
                        l_strConfirmedExecConditionType);
                    // 値段条件（SONAR）
                    l_hostEqtypeOrderAllParams.setPriceConditionType(
                        l_orderManager.getPriceConditionTypeSonar(
                            l_eqtypeOderUnitRow.getConfirmedPriceConditionType()));
                    // 取消区分
                    l_hostEqtypeOrderAllParams.setCancelDiv(WEB3CancelDivDef.CANCEL);
                    // 取消時間
                    String l_strLastUpdatedDate =
                        WEB3DateUtility.formatDate(
                            l_eqtypeOderUnitRow.getLastUpdatedTimestamp(), WEB3GentradeTimeDef.TIME_FORMAT_HM);
                    l_hostEqtypeOrderAllParams.setCancelOrderTime(l_strLastUpdatedDate);
                    // 取消区分（前日／当日）
                    Timestamp l_tsCurTime = GtlUtils.getTradingSystem().getSystemTimestamp();

                    String l_strReceivedDateTime = WEB3DateUtility.formatDate(
                        l_tsCurTime,
                        WEB3GentradeTimeDef.DATE_FORMAT_YMD);
                    String l_strCancelOrderDateDiv = null;

                    if (WEB3Toolkit.isEquals(l_strReceivedDateTime, l_eqtypeOderUnitRow.getBizDate()))
                    {
                        l_strCancelOrderDateDiv = WEB3OrderDateDivDef.TODAY;
                    }
                    else
                    {
                        l_strCancelOrderDateDiv = WEB3OrderDateDivDef.YESTERDAY;
                    }

                    l_hostEqtypeOrderAllParams.setCancelOrderDateDiv(l_strCancelOrderDateDiv);
                    // フロント発注取引所区分コード
                    l_hostEqtypeOrderAllParams.setFrontOrderExchangeCode(
                        l_frontOrderService.getFrontOrderExchangeCode(l_strMarketCode));
                    // フロント発注システム区分
                    EqTypeOrderUnit l_eqtypeOrderUnit = (EqTypeOrderUnit)l_orderUnit;
                    EqTypeTradedProduct l_tradedProduct =
                        (EqTypeTradedProduct)l_eqtypeOrderUnit.getTradedProduct();
                    EqtypeTradedProductRow l_tradedProductRow =
                        (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
                    String l_strFrontOrderSystemCode =
                        l_frontOrderService.getFrontOrderSystemCode(
                            l_strMarketCode,
                            l_tradedProductRow.getOpenOtcDiv());
                    l_hostEqtypeOrderAllParams.setFrontOrderSystemCode(
                        l_strFrontOrderSystemCode);
                    // フロント発注取引区分コード
                    l_hostEqtypeOrderAllParams.setFrontOrderTradeCode(
                        WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE);
                    // 社内処理項目
                    l_hostEqtypeOrderAllParams.setCorpCode(
                        l_frontOrderService.getOrgCorpCode(l_eqtypeOrderUnit));
                    // （被訂正）社内処理項目
                    l_hostEqtypeOrderAllParams.setOrgCorpCode(
                        l_frontOrderService.getOrgCorpCode(l_eqtypeOrderUnit));
                    // 全訂正処理区分
                    l_hostEqtypeOrderAllParams.setAllOrderChangeDiv(
                        WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE);
                    // 処理区分（ステータス）
                    l_hostEqtypeOrderAllParams.setStatus(
                        WEB3FrontOrderStatusDef.NOT_DEAL);

                    // データ挿入する
                    QueryProcessor l_processor = Processors.getDefaultProcessor();
                    l_processor.doInsertQuery(l_hostEqtypeOrderAllParams);

                    log.exiting(STR_METHOD_NAME);
                    return DefaultMarketRequestSendResult.newSuccessResultInstance(l_lngMsgTokenId);
                }

                try 
                {
                    l_orderManager.notifyRLS(
                        (EqTypeOrderUnit)l_orderUnit,
                        OrderManagerPersistenceContext.CANCEL_ORDER_ACCEPTED);
                }
                catch (WEB3BusinessLayerException l_ble)
                {
                    log.error("notifyルールエンジンサーバ()にて業務エラーがスローされた場合");
                }
                
                ProcessingResult l_processingResult;

                double l_quantity = l_orderUnit.getQuantity();
                String l_strTraderCode = l_eqtypeOderUnitRow.getSonarTraderCode();
				Timestamp l_tsCurTime = GtlUtils.getTradingSystem().getSystemTimestamp();
                String l_strReceivedDateTime = WEB3DateUtility.formatDate(l_tsCurTime,"yyyyMMdd");
                String l_strCancelOrderDateDiv;
                if (l_strReceivedDateTime.equals(l_eqtypeOderUnitRow.getBizDate()))
                {
                    l_strCancelOrderDateDiv = WEB3OrderDateDivDef.TODAY;
                }
                else
                {
                    l_strCancelOrderDateDiv = WEB3OrderDateDivDef.YESTERDAY;
                }      
     
                String l_strLastUpdatedDate =
                    WEB3DateUtility.formatDate(
                        l_eqtypeOderUnitRow.getLastUpdatedTimestamp(), "HHmm");

                if (WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone())
                {
                    log.debug("==> 市場開局時間帯の場合");
                    
                    //市場開局時間帯の場合
                    if (OrderCategEnum.SWAP_MARGIN.equals(l_orderUnit.getOrderCateg()))
                    {
                        //現引現渡注文の場合
                        HostEqtypeSwapParams l_hostEqtypeSwapParams =
                            new HostEqtypeSwapParams();

                        //setデータコード
                        l_hostEqtypeSwapParams.setRequestCode(
                            WEB3HostRequestCodeDef.EQUITY_SPOT_ORDER_CHANGE_CANCEL);                            
                        //顧客ID
                        l_hostEqtypeSwapParams.setAccountId(l_eqtypeOderUnitRow.getAccountId());
                        //set証券会社コード
                        l_hostEqtypeSwapParams.setInstitutionCode(
                            l_strInstitutionCode);
                        //set部店コード
                        l_hostEqtypeSwapParams.setBranchCode(
                            l_strBranchCode);
                        //set顧客コード
                        l_hostEqtypeSwapParams.setAccountCode(
                            l_strAccountCode);
                        //set扱者コード
                        l_hostEqtypeSwapParams.setSonarTraderCode(
                            l_strTraderCode);
                        //set銘柄コード
                        l_hostEqtypeSwapParams.setProductCode(
                            l_strPoductCode);
                        //set現渡数量
                        if (OrderTypeEnum.SWAP_MARGIN_SHORT.equals(l_orderUnit.getOrderType()))
                        {
                            l_hostEqtypeSwapParams.setSellOrderQuantity(l_quantity);
                        }
                        else
                        {
                            l_hostEqtypeSwapParams.setSellOrderQuantity(0);
                        }
                        //set現引数量
                        if (OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_orderUnit.getOrderType()))
                        {
                            l_hostEqtypeSwapParams.setBuyOrderQuantity(l_quantity);
                        }
                        else
                        {
                            l_hostEqtypeSwapParams.setBuyOrderQuantity(0);
                        }
                        //set弁済区分（SONAR）
                        l_hostEqtypeSwapParams.setSonarRepaymentType(l_eqtypeOderUnitRow.getSonarRepaymentType());
                        //set市場コード（SONAR）
                        l_hostEqtypeSwapParams.setSonarMarketCode(l_eqtypeOderUnitRow.getSonarMarketCode());
                        //set伝票№
                        l_hostEqtypeSwapParams.setTicketNumber(l_eqtypeOderUnitRow.getVoucherNo());
                        //set譲渡益税区分
                        // 現引注文の場合：　@0：なし
                        // 現渡注文の場合：
						// 　@個人客（顧客.口座タイプ==("個人アカウント", "共用アカウント")）でかつ、
						//　@　@　@　@居住者、特別非居住者の場合：　@1：申告
						//　@　@　@　@非居住者の場合：　@　@　@　@　@　@　@0：なし
						// 　@法@人客（顧客.口座タイプ=="法@人アカウント"）の場合：　@0：なし
						if (OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_orderUnit.getOrderType()))
						{
							l_hostEqtypeSwapParams.setCapitalGainTaxType(
								WEB3CapitalGainTaxTypeDef.NOTHING);
						}
						else if (
							MainAccountTypeEnum.INDIVIDUAL_ACCOUNT.equals(l_mainAccountRow.getAccountType()) ||
							MainAccountTypeEnum.JOINT_OWNERSHIP.equals(l_mainAccountRow.getAccountType()))
						{
							if (WEB3ResidentDef.RESIDENT.equals(l_mainAccountRow.getResident()) ||
								WEB3ResidentDef.SPE_NON_RESIDENT.equals(l_mainAccountRow.getResident()))
							{
								l_hostEqtypeSwapParams.setCapitalGainTaxType(
									WEB3CapitalGainTaxTypeDef.REPORT);
							}
							else if (WEB3ResidentDef.NON_RESIDENT.equals(l_mainAccountRow.getResident()))
							{
								l_hostEqtypeSwapParams.setCapitalGainTaxType(
									WEB3CapitalGainTaxTypeDef.NOTHING);
							}
						}
                        else if(
                            MainAccountTypeEnum.CORPORATE_ACCOUNT.equals(l_mainAccountRow.getAccountType()))
                        {
                            l_hostEqtypeSwapParams.setCapitalGainTaxType(
                                WEB3CapitalGainTaxTypeDef.NOTHING);
                        }
                        
                        //set強制
                        l_hostEqtypeSwapParams.setCheckType(WEB3CheckTypeDef.PRE_CHECK);
                        //set取消
                        l_hostEqtypeSwapParams.setCancelDiv(WEB3CancelDivDef.CANCEL);
                        //set識別コード
                        l_hostEqtypeSwapParams.setOrderRequestNumber(l_strOrderRequestNumber);
                        //set注文履歴番号 ： 株式注文単位.注文履歴最終通番
                        l_hostEqtypeSwapParams.setOrderActionSerialNo(
                            l_eqtypeOderUnitRow.getLastOrderActionSerialNo());
                        //set受注日時
                        l_hostEqtypeSwapParams.setCreateDatetime(l_tsCurTime);
                        //set税区分（特定口座区分）
                        if (TaxTypeEnum.NORMAL.equals(l_eqtypeOderUnitRow.getTaxType()))
                        {
                            l_hostEqtypeSwapParams.setTaxType(WEB3TaxTypeDef.NORMAL);
                        }
                        else
                        {
                            l_hostEqtypeSwapParams.setTaxType(WEB3TaxTypeDef.SPECIAL);
                        }
                        //set税区分（現引現渡現物特定口座区分）
                        if (TaxTypeEnum.NORMAL.equals(l_eqtypeOderUnitRow.getSwapTaxType()))
                        {
                            l_hostEqtypeSwapParams.setSwapTaxType(WEB3TaxTypeDef.NORMAL);
                        }
                        else
                        {
                            l_hostEqtypeSwapParams.setSwapTaxType(WEB3TaxTypeDef.SPECIAL);
                        }
                        //set処理区分(0：未処理)
                        l_hostEqtypeSwapParams.setStatus(WEB3StatusDef.NOT_DEAL);

                        //データ挿入する
                        QueryProcessor processor = Processors.getDefaultProcessor();
                        processor.doInsertQuery(l_hostEqtypeSwapParams);
                        
                        l_isTriggerIssue =
                            WEB3GentradeTradingTimeManagement
                                .isSubmitMarketTrigger(
                                null);
                    
                        if (l_isTriggerIssue)
                        {
                            log.debug("==> isトリガ発行()をコール==true");
                            WEB3MQMessageSpec l_web3MQMessageSpec =
                                new WEB3MQMessageSpec(
                                    l_strInstitutionCode,
                                    WEB3HostRequestCodeDef.EQUITY_SPOT_ORDER_CHANGE_CANCEL + DATA_CODE);
                             WEB3MQGatewayService l_web3MQGatewayService =
                                 (WEB3MQGatewayService)Services.getService(
                                     WEB3MQGatewayService.class);
                             WEB3MQSendResult l_web3MQSendResult =
                                 l_web3MQGatewayService.send(l_web3MQMessageSpec); 
                    
                            if (l_web3MQSendResult.isSuccessResult())
                            {
                                log.debug("==> 取消注文送信 成功！！！");
                                return DefaultMarketRequestSendResult
                                        .newSuccessResultInstance(l_lngMsgTokenId);
                            }
                            else
                            {
                                log.debug("==> 取消注文送信 失敗 ！！！");
                                l_processingResult =
                                    ProcessingResult.newFailedResultInstance(l_web3MQSendResult.getErrorInfo());
                                    return DefaultMarketRequestSendResult
                                        .newFailedResultInstance(
                                        l_processingResult);
                            }
                        }
                    }
                    else
                    {
                        //現引現渡注文以外の場合
                        WEB3EquityFrontOrderService l_frontOrderService =
                            (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
                        HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams = null;
                        if (WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone())
                        {
                            l_hostEqtypeOrderAllParams =
                                l_frontOrderService.getHostEqtypeOrderAll(
                                    (EqTypeOrderUnit)l_orderUnit);
                        }
                        
                        if (l_hostEqtypeOrderAllParams == null)
                        {
                            l_hostEqtypeOrderAllParams = new HostEqtypeOrderAllParams();
                            
                            // データコード
                            l_hostEqtypeOrderAllParams.setRequestCode(
                                WEB3HostRequestCodeDef.EQUITY_ORDER_CHANGE);
                            // 口座ID
                            l_hostEqtypeOrderAllParams.setAccountId(
                                l_eqtypeOderUnitRow.getAccountId());
                            // 証券会社コード
                            l_hostEqtypeOrderAllParams.setInstitutionCode(
                                l_strInstitutionCode);
                            // 部店コード
                            l_hostEqtypeOrderAllParams.setBranchCode(
                                l_strBranchCode);
                            // 口座コード
                            l_hostEqtypeOrderAllParams.setAccountCode(
                                l_strAccountCode);
                            // 扱者コード（SONAR）
                            l_hostEqtypeOrderAllParams.setSonarTraderCode(
                                l_strTraderCode);
                            // 識別コード
                            l_hostEqtypeOrderAllParams.setOrderRequestNumber(
                                l_strOrderRequestNumber);
                            // 注文履歴番号
                            l_hostEqtypeOrderAllParams.setOrderActionSerialNo(
                                l_eqtypeOderUnitRow.getLastOrderActionSerialNo());
                            // 銘柄コード
                            l_hostEqtypeOrderAllParams.setProductCode(
                                l_strPoductCode);
                            // 受注日時
                            l_hostEqtypeOrderAllParams.setReceivedDateTime(l_tsCurTime);
                            // 発注経路区分
                            l_hostEqtypeOrderAllParams.setSubmitOrderRouteDiv(
                                l_eqtypeOderUnitRow.getSubmitOrderRouteDiv());
                            if (SideEnum.SELL.equals(l_orderUnit.getSide()))
                            {
                                // 売付数量
                                l_hostEqtypeOrderAllParams.setSellOrderQuantity(
                                    l_eqtypeOderUnitRow.getConfirmedQuantity());
                                // 買付数量
                                l_hostEqtypeOrderAllParams.setBuyOrderQuantity(0D);
                            }
                            else
                            {
                                // 売付数量
                                l_hostEqtypeOrderAllParams.setSellOrderQuantity(0D);
                                // 買付数量
                                l_hostEqtypeOrderAllParams.setBuyOrderQuantity(
                                    l_eqtypeOderUnitRow.getConfirmedQuantity());
                            }
                            // 指値
                            l_hostEqtypeOrderAllParams.setLimitPrice(
                                l_eqtypeOderUnitRow.getConfirmedPrice());
                            // 執行条件（SONAR）
                            String l_strConfirmedExecConditionType = null;
                            if (l_orderManager.isCarriedOrderUnit((EqTypeOrderUnit)l_orderUnit))
                            {
                                l_strConfirmedExecConditionType =
                                    WEB3ExecutionConditionDef.COME_TO_TERMS;
                            }
                            else
                            {
                                l_strConfirmedExecConditionType = 
                                l_orderManager.getExecutionConditionTypeSonar(
                                        l_eqtypeOderUnitRow.getConfirmedExecConditionType());
                            }
                            l_hostEqtypeOrderAllParams.setExecutionCondition(
                                l_strConfirmedExecConditionType);
                            // 値段条件（SONAR）
                            l_hostEqtypeOrderAllParams.setPriceConditionType(
                            	l_orderManager.getPriceConditionTypeSonar(
                                	l_eqtypeOderUnitRow.getConfirmedPriceConditionType()));
                            // 取消区分
                            l_hostEqtypeOrderAllParams.setCancelDiv(WEB3CancelDivDef.CANCEL);
                            // 取消時間                      
                            l_hostEqtypeOrderAllParams.setCancelOrderTime(l_strLastUpdatedDate);
                            // 取消区分（前日／当日）
                            l_hostEqtypeOrderAllParams.setCancelOrderDateDiv(l_strCancelOrderDateDiv);
                            // フロント発注取引所区分コード
                            l_hostEqtypeOrderAllParams.setFrontOrderExchangeCode(
                                l_frontOrderService.getFrontOrderExchangeCode(l_strMarketCode));
                            // フロント発注システム区分
                            EqTypeOrderUnit l_eqtypeOrderUnit = (EqTypeOrderUnit)l_orderUnit;
                            EqTypeTradedProduct l_tradedProduct =
                                (EqTypeTradedProduct)l_eqtypeOrderUnit.getTradedProduct();
                            EqtypeTradedProductRow l_tradedProductRow =
                                (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
                            String l_strFrontOrderSystemCode =
                                l_frontOrderService.getFrontOrderSystemCode(
                                    l_strMarketCode,
                                    l_tradedProductRow.getOpenOtcDiv());
                            l_hostEqtypeOrderAllParams.setFrontOrderSystemCode(
                                l_strFrontOrderSystemCode);
                            // フロント発注取引区分コード
                            l_hostEqtypeOrderAllParams.setFrontOrderTradeCode(
                                WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE);
                            // 社内処理項目
                            l_hostEqtypeOrderAllParams.setCorpCode(
                                l_frontOrderService.getOrgCorpCode(l_eqtypeOrderUnit));
                            // （被訂正）社内処理項目
                            l_hostEqtypeOrderAllParams.setOrgCorpCode(
                                l_frontOrderService.getOrgCorpCode(l_eqtypeOrderUnit));
                            // 全訂正処理区分
                            l_hostEqtypeOrderAllParams.setAllOrderChangeDiv(
                                WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE);
                            // 処理区分（ステータス）
                            l_hostEqtypeOrderAllParams.setStatus(
                                WEB3FrontOrderStatusDef.NOT_DEAL);
    
                            // データ挿入する
                            QueryProcessor processor = Processors.getDefaultProcessor();
                            processor.doInsertQuery(l_hostEqtypeOrderAllParams);
                        }
                        else
                        {
                            // 注文履歴番号
                            l_hostEqtypeOrderAllParams.setOrderActionSerialNo(
                                l_eqtypeOderUnitRow.getLastOrderActionSerialNo());
                            // 受注日時
                            l_hostEqtypeOrderAllParams.setReceivedDateTime(l_tsCurTime);
                            // 発注経路区分
                            l_hostEqtypeOrderAllParams.setSubmitOrderRouteDiv(
                                l_eqtypeOderUnitRow.getSubmitOrderRouteDiv());
                            // 取引コード（SONAR）
                            l_hostEqtypeOrderAllParams.setSonarTradedCode(null);
                            // 弁済区分（SONAR）
                            l_hostEqtypeOrderAllParams.setSonarRepaymentType(null);
                            // 市場コード（SONAR）
                            l_hostEqtypeOrderAllParams.setSonarMarketCode(null);
                            // 伝票№
                            l_hostEqtypeOrderAllParams.setTicketNumber(null);
                            // 受注日区分
                            l_hostEqtypeOrderAllParams.setReceivedDateTimeDiv(null);
                            // 税区分（特定口座区分）
                            l_hostEqtypeOrderAllParams.setTaxType(null);
                            // 譲渡益税区分
                            l_hostEqtypeOrderAllParams.setCapitalGainTaxType(null);
                            // 強制
                            l_hostEqtypeOrderAllParams.setCheckType(null);
                            // 注文チャネル
                            l_hostEqtypeOrderAllParams.setOrderChanel(null);
                            // ファ@クター
                            l_hostEqtypeOrderAllParams.setFactor(null);
                            // 手数料№
                            l_hostEqtypeOrderAllParams.setCommisionNumber(null);
                            // 手数料№枝番
                            l_hostEqtypeOrderAllParams.setCommisionBranchNumber(null);
                            // 手数料商品コード
                            l_hostEqtypeOrderAllParams.setCommisionProductCode(null);
                            // 空売フラグ
                            l_hostEqtypeOrderAllParams.setShortSellOrderFlag(null);
                            // 訂正数量
                            l_hostEqtypeOrderAllParams.setChangeQuantity(null);
                            // 訂正指値
                            l_hostEqtypeOrderAllParams.setChangeLimitPrice(null);
                            // 訂正執行条件（SONAR）
                            l_hostEqtypeOrderAllParams.setChangeExecutionCondition(null);
                            // 訂正値段条件（SONAR）
                            l_hostEqtypeOrderAllParams.setChangePriceConditionType(null);
                            // 訂正時間
                            l_hostEqtypeOrderAllParams.setChangeOrderTime(null);
                            // 訂正区分（前日／当日）
                            l_hostEqtypeOrderAllParams.setChangeOrderDateDiv(null);
                            // 取消区分
                            l_hostEqtypeOrderAllParams.setCancelDiv(WEB3CancelDivDef.CANCEL);
                            // 取消時間                      
                            l_hostEqtypeOrderAllParams.setCancelOrderTime(l_strLastUpdatedDate);
                            // 取消区分（前日／当日）
                            l_hostEqtypeOrderAllParams.setCancelOrderDateDiv(l_strCancelOrderDateDiv);
                            // 社内処理項目
                            l_hostEqtypeOrderAllParams.setCorpCode(
                                l_frontOrderService.getOrgCorpCode((EqTypeOrderUnit)l_orderUnit));
                            // （被訂正）社内処理項目
                            l_hostEqtypeOrderAllParams.setOrgCorpCode(
                                l_frontOrderService.getOrgCorpCode((EqTypeOrderUnit)l_orderUnit));
                            // 更新日付
                            l_hostEqtypeOrderAllParams.setLastUpdatedTimestamp(l_tsCurTime);
                            // データを更新する
                            QueryProcessor processor = Processors.getDefaultProcessor();
                            processor.doUpdateQuery(l_hostEqtypeOrderAllParams);
                        }
                        
                        //isトリガ発行()をコール
                        l_isTriggerIssue =
                            WEB3GentradeTradingTimeManagement
                                .isSubmitMarketTrigger(
                                null);

                        boolean l_isSubmitMqTrigger =
                            WEB3GentradeOrderSwitching.isSubmitMQTriggerEnable(
                                l_strInstitutionCode,
                                l_eqtypeOderUnitRow.getProductType(),
                                l_strMarketCode,
                                l_eqtypeOderUnitRow.getSubmitOrderRouteDiv(),
                                l_hostEqtypeOrderAllParams.getFrontOrderSystemCode());
                        
                        if (l_isTriggerIssue && l_isSubmitMqTrigger)
                        {
                            String l_strMQDataCode =
                                l_frontOrderService.getChangeCancelMQDataCode(
                                    l_eqtypeOderUnitRow.getSubmitOrderRouteDiv());
                            if (l_strMQDataCode == null)
                            {
                                log.debug("新規注文送信 成功（発注停止中）！！！");
                                return DefaultMarketRequestSendResult
                                    .newSuccessResultInstance(
                                    l_lngMsgTokenId);
                            }
                            WEB3MQMessageSpec l_web3MQMessageSpec =
                                new WEB3MQMessageSpec(
                                    l_strInstitutionCode,
                                    l_strMQDataCode);
                            WEB3MQGatewayService l_web3MQGatewayService =
                                (WEB3MQGatewayService)Services.getService(
                                    WEB3MQGatewayService.class);
                            WEB3MQSendResult l_web3MQSendResult =
                                l_web3MQGatewayService.send(l_web3MQMessageSpec); 
                        
                            if (l_web3MQSendResult.isSuccessResult())
                            {
                                log.debug("==> 取消注文送信 成功！！！");
                                return DefaultMarketRequestSendResult
                                        .newSuccessResultInstance(l_lngMsgTokenId);
                            }
                            else
                            {
                                log.debug("==> 取消注文送信 失敗 ！！！");
                                l_processingResult =
                                    ProcessingResult.newFailedResultInstance(l_web3MQSendResult.getErrorInfo());
                                    return DefaultMarketRequestSendResult
                                        .newFailedResultInstance(
                                        l_processingResult);
                            }
                        }
                    }
                }
                else
                {
                    log.debug("==> 市場閉局時間帯の場合");
                        //市場閉局時間帯の場合
                    WEB3EquityCancelOrderConfirmInterceptor l_cancelConfirmInterceptor =
                        new WEB3EquityCancelOrderConfirmInterceptor();
                    l_orderManager.setThreadLocalPersistenceEventInterceptor(
                        l_cancelConfirmInterceptor);
                    MarketAdapter l_marketAdapter = l_tradingModule.getMarketAdapter();
                    l_marketAdapter.getMarketResponseReceiverCallbackService();
                    DefaultCancelOrderAcceptedMarketResponseMessage l_cancelResponseMessage =
                        new DefaultCancelOrderAcceptedMarketResponseMessage(l_lngOrderId);
                    EqTypeMarketResponseReceiverCallbackService l_callBackService =
                        (EqTypeMarketResponseReceiverCallbackService)l_tradingModule
                            .getMarketAdapter()
                            .getMarketResponseReceiverCallbackService();
                    l_processingResult = l_callBackService.process(
                        (MarketResponseMessage)l_cancelResponseMessage);
                    if (l_processingResult.isFailedResult())
                    {
                        return DefaultMarketRequestSendResult
                            .newFailedResultInstance(
                            l_processingResult);
                    }    
                }
            }
        }
        catch (WEB3BaseException l_wbe)
        {
            log.error("__an unexpected error__", l_wbe);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(l_wbe.getErrorInfo());
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }
        catch (DataFindException e)
        {
            log.error(
                "__an unexpected error__",
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    e.toString(),
                    e));
            throw new TooLateException(e.getMessage());
        }
        catch (DataQueryException e)
        {
            log.error(
                "__an unexpected error__",
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    e.toString(),
                    e));
            throw new TooLateException(e.getMessage());
        }
        catch (DataNetworkException e)
        {
            log.error(
                "__an unexpected error__",
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    e.toString(),
                    e));
            throw new TooLateException(e.getMessage());
        }
        catch (NotFoundException nfe)
        {
            log.error(
                "__an unexpected error__",
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    nfe.toString(),
                    nfe));
            throw new TooLateException(nfe.getMessage());
        }

        log.exiting(STR_METHOD_NAME);
        return DefaultMarketRequestSendResult.newSuccessResultInstance(
            l_lngMsgTokenId);
    }

    /**
     * （訂正注文送信）。<BR>
     * <BR>
     * 現物の訂正注文、新規建訂正注文の送信を行う。<BR>
     * （send(EqTypeChangeOrderMarketRequestMessage, boolean)のオーバーライド）<BR>
     * <BR>
     * Web3からの注文訂正（現物、新規建）をSONARへ送信する。<BR>
     * 注文訂正サービス、信用取引訂正新規建サービスからコールされる。<BR>
     * -------------------------------------------------------------------<BR>
     * ○訂正内容が、市場に通知する必要がある内容の場合<BR>
     * 　@－訂正元注文が市場未送信の場合、【株式注文キューテーブル】の対象データをUpdateする。<BR>
     * 　@－訂正元注文が市場送信済の場合<BR>
     * 　@　@　@・市場開局時間帯（ホスト送信時間帯)の場合、【株式注文訂正取消キューテーブル】に対し<BR>
     * 　@　@　@　@行をInsertし、トリガを発行する時間帯の場合のみMQトリガを発行する。<BR>
     * 　@　@　@・市場閉局時間帯(ホスト送信時間帯外)の場合、訂正を確定させる。<BR>
     * <BR>
     * ○訂正内容が、市場に通知不要である場合<BR>
     * 　@－訂正を確定させる。<BR>
     * -------------------------------------------------------------------<BR>
     * <BR>
     * １）　@注文取得<BR>
     * 　@株式注文訂正リクエストメッセージ.getOrderId() にて注文IDを取得する。<BR>
     * 　@注文マネージャにて、注文IDに該当する注文オブジェクトを取得する。<BR>
     * 　@注文オブジェクト.getOrderUnits( )で<BR>
     * 　@取得した注文単位オブジェクトの0番目の要素について、以下の処理を行う。<BR>
     * <BR>
     * 以下、注文が市場未送信か送信済かにより、処理が分かれる。<BR>
     * <BR>
     * 注文が市場未送信の場合（引数.is市場未送信 == true）は、<BR>
     * シーケンス図「（現物株式_信用取引市場ﾘｸｴｽﾄ）訂正注文送信（市場未送信注文）」を参照。<BR>
     * 注文が市場送信済の場合（引数.is市場未送信 == false）は、<BR>
     * シーケンス図「（現物株式_信用取引市場ﾘｸｴｽﾄ）訂正注文送信（市場送信済注文）」を参照。
     * @@param l_requestMessage （株式注文訂正リクエストメッセージ）<BR>
     * 株式注文訂正リクエストメッセージ
     * @@param l_isUnSend （is市場未送信）<BR>
     * 原注文が市場未送信の場合はtrue、<BR>
     * 原注文が市場送信済の場合はfalseを指定する。<BR> 
     * falseの場合、SONARへ訂正を通知する。
     * @@return MarketRequestSendResult<BR>
     * @@throws TooLateException
     */
    public MarketRequestSendResult send(
        EqTypeChangeOrderMarketRequestMessage l_requestMessage,
        boolean l_isUnSend)
        throws TooLateException
    {
        final String STR_METHOD_NAME =
            "send(EqTypeChangeOrderMarketRequestMessage,boolean)";
        log.entering(STR_METHOD_NAME);

        String ORDER_REQUEST_CODE = WEB3HostRequestCodeDef.EQUITY_ORDER;
        String CHANGE_CANCEL_REQUEST_CODE =
            WEB3HostRequestCodeDef.EQUITY_ORDER_CHANGE;

        boolean l_isTriggerIssue = false;
        long l_lngMsgTokenId = 0;
        ProcessingResult l_processingResult;
        WEB3MQMessageSpec l_web3MQMessageSpec = null;
        WEB3MQGatewayService l_web3MQGatewayService;
        WEB3MQSendResult l_web3MQSendResult;
        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        String l_strAccountCode = null;
        String l_strExecutionCondition = null;
        String l_strPriceConditionType = null;
        String l_strOrderRequestNumber = null;
        String l_strTraderCode = null;
        String l_strPoductCode = null;
        String l_strLastUpdatedDate = null;
        String l_strChangeOrderDateDiv = null;
        Timestamp l_tsReceivedDateTime = null;
        double l_dblOrderQuantity = 0;
        double l_dblLimitPrice = 0;
        String l_strOrderConditionType = null;

        WEB3EquityChangeConfirmInterceptor l_changeConfirmInterceptor = null;
        try
        {
            if (l_requestMessage == null)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                            this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            long l_lngOrderId = l_requestMessage.getOrderId();
            OrderUnit[] l_orderUnits = null;
            OrderUnit l_orderUnit = null;
            EqtypeOrderUnitRow l_eqtypeOderUnitRow = null;
    
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accountManager = l_finApp.getAccountManager();
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            EqTypeProductManager l_eqTypeProductManager =
                (EqTypeProductManager)l_tradingModule.getProductManager();
            EqTypeMarketResponseReceiverCallbackService l_callBackService =
                (EqTypeMarketResponseReceiverCallbackService)l_tradingModule
                    .getMarketAdapter()
                    .getMarketResponseReceiverCallbackService();
    
            WEB3EquityOrderManager l_orderMgr =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

            //注文取得
            Order l_order = null;
            l_order = new EqTypeOrderImpl(l_lngOrderId);
            l_orderUnits = l_order.getOrderUnits();
            l_orderUnit = l_orderUnits[0];
            l_eqtypeOderUnitRow =
                (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            // 市場コードを取得
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market =
                l_finObjectManager.getMarket(l_eqtypeOderUnitRow.getMarketId());
            String l_strMarketCode = l_market.getMarketCode();

            //発注条件を取得
            l_strOrderConditionType =
                l_eqtypeOderUnitRow.getOrderConditionType();

            //識別コードを取得する
            l_strOrderRequestNumber =
                l_eqtypeOderUnitRow.getOrderRequestNumber();
            //部店を取得する
            Branch l_banch =
                l_accountManager.getBranch(l_eqtypeOderUnitRow.getBranchId());
            //証券会社コードを取得する
            l_strInstitutionCode =
                l_banch.getInstitution().getInstitutionCode();
            //部店コードを取得する
            l_strBranchCode = l_banch.getBranchCode();
            //顧客コードを取得する
            l_strAccountCode =
                l_accountManager
                    .getMainAccount(l_eqtypeOderUnitRow.getAccountId())
                    .getAccountCode();
            //扱者コードを取得する
            l_strTraderCode = l_eqtypeOderUnitRow.getSonarTraderCode();

            //銘柄コードを取得する
            l_strPoductCode =
                ((EqTypeProductImpl)l_eqTypeProductManager
                    .getProduct(l_eqtypeOderUnitRow.getProductId()))
                    .getProductCode();
            //訂正数量を取得する
            l_dblOrderQuantity = l_eqtypeOderUnitRow.getQuantity();
            //訂正指値を取得する
            l_dblLimitPrice = l_eqtypeOderUnitRow.getLimitPrice();

            //執行条件（SONAR）
            if (l_orderMgr.isCarriedOrderUnit((EqTypeOrderUnit)l_orderUnit))
            {
                l_strExecutionCondition =
                    WEB3ExecutionConditionDef.COME_TO_TERMS;
            }
            else
            {
                l_strExecutionCondition = 
                    l_orderMgr.getExecutionConditionTypeSonar(
                        l_eqtypeOderUnitRow.getExecutionConditionType());
            }

            //値段条件（SONAR）
            l_strPriceConditionType = l_orderMgr.getPriceConditionTypeSonar(
                l_eqtypeOderUnitRow.getPriceConditionType());
            
            Timestamp l_tsLastUpdateDateTime = GtlUtils.getSystemTimestamp();
            //受注日時
            l_tsReceivedDateTime = l_tsLastUpdateDateTime;

            //訂正時間
            l_strLastUpdatedDate =
                WEB3DateUtility.formatDate(l_tsReceivedDateTime, "HHmm");

            //訂正区分（前日／当日）
            String l_strDate =
                WEB3DateUtility.formatDate(l_tsReceivedDateTime, "yyyyMMdd");
            if (l_eqtypeOderUnitRow.getBizDate().equals(l_strDate))
            {
                l_strChangeOrderDateDiv = WEB3OrderDateDivDef.TODAY;
            }
            else
            {
                l_strChangeOrderDateDiv = WEB3OrderDateDivDef.YESTERDAY;
            }

            if (l_isUnSend)
            {
                l_orderMgr.notifyRLS(
                    (EqTypeOrderUnit)l_orderUnit,
                    OrderManagerPersistenceContext.MODIFY_ORDER_CONFIRMED_BY_MKT);

                if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType)
                    && !(WEB3RequestTypeDef.QUOTE_SERVER.equals(l_eqtypeOderUnitRow.getRequestType())))
                {
                    return DefaultMarketRequestSendResult.newSuccessResultInstance(l_lngMsgTokenId);
                }

                log.debug("==> 市場未送信（is市場未送信 == true）の場合の処理");

                QueryProcessor processor = Processors.getDefaultProcessor();
                WEB3EquityFrontOrderService l_frontOrderService =
                    (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
                int l_intIndex = l_frontOrderService.getIndexOfOrderRevInCorpCode();
                int l_intFigure = l_frontOrderService.getFigureOfOrderRev();
                String l_strWhere =
                    " request_code=? and institution_code=? and branch_code=? and order_request_number=? and substr(corp_code," + l_intIndex + "," + l_intFigure + ")=? and status=?";
                String[] l_strBindValues = new String[6];
                l_strBindValues[0] = ORDER_REQUEST_CODE;
                l_strBindValues[1] = l_strInstitutionCode;
                l_strBindValues[2] = l_strBranchCode;
                l_strBindValues[3] = l_strOrderRequestNumber;
                l_strBindValues[4] = l_eqtypeOderUnitRow.getOrderRev();
                l_strBindValues[5] = WEB3FrontOrderStatusDef.NOT_DEAL;

                //訂正内容を更新する
                Map l_mapChanges = new HashMap();
                
				//set注文履歴番号 ： 株式注文単位.注文履歴最終通番
				l_mapChanges.put(
                    "order_action_serial_no",
				    new Integer(l_eqtypeOderUnitRow.getLastOrderActionSerialNo()));
                
                if (OrderCategEnum.ASSET.equals(l_eqtypeOderUnitRow.getOrderCateg()))
                {
                    //現物株式の場合
                    if (SideEnum.SELL.equals(l_orderUnit.getSide()))
                    {
                        //set買付数量
                        l_mapChanges.put("buy_order_quantity", new Double("0"));
                        //set売付数量
                        l_mapChanges.put("sell_order_quantity", new Double(l_dblOrderQuantity));
                    }
                    else
                    {
                        //set買付数量
                        l_mapChanges.put("buy_order_quantity", new Double(l_dblOrderQuantity));
                        //set売付数量
                        l_mapChanges.put("sell_order_quantity", new Double("0"));
                    }
                }
                else
                {
                    //信用取引の場合
                    if (OrderTypeEnum.MARGIN_LONG.equals(l_orderUnit.getOrderType()))
                    {
                        //set買付数量
                        l_mapChanges.put("buy_order_quantity", new Double(l_dblOrderQuantity));
                        //set売付数量
                        l_mapChanges.put("sell_order_quantity", new Double("0"));
                    }
                    else if (OrderTypeEnum.MARGIN_SHORT.equals(l_orderUnit.getOrderType()))
                    {
                        //set買付数量
                        l_mapChanges.put("buy_order_quantity", new Double("0"));
                        //set売付数量
                        l_mapChanges.put("sell_order_quantity", new Double(l_dblOrderQuantity));
                    }
                    //set空売フラグ
                    l_mapChanges.put("short_sell_order_flag",l_eqtypeOderUnitRow.getShortSellOrderFlag());
                }

                //set指値
                l_mapChanges.put(
                    "limit_price",
                    new Double(l_dblLimitPrice));
                //set執行条件（SONAR）
                l_mapChanges.put(
                    "execution_condition",
                    l_strExecutionCondition);
                //set値段条件（SONAR）
                l_mapChanges.put(
                    "price_condition_type",
                    l_strPriceConditionType);
                //set発注経路区分
                l_mapChanges.put(
                    "submit_order_route_div",
                    l_eqtypeOderUnitRow.getSubmitOrderRouteDiv());
                //set更新日付
                l_mapChanges.put(
                    "last_updated_timestamp",
                    l_tsLastUpdateDateTime);
                int l_intResults = processor.doUpdateAllQuery(
                    (RowType)HostEqtypeOrderAllParams.TYPE,
                    l_strWhere,
                    l_strBindValues,
                    l_mapChanges);
                if (l_intResults == 0)
                {
                    return DefaultMarketRequestSendResult
                        .newFailedResultInstance(
                            ProcessingResult.newFailedResultInstance(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00010));
                }
            }
            else
            {
                log.debug("==> 市場送信済（is市場未送信 == false）の場合の処理");

                // getMarket()の戻り値.市場コード == "PTS市場"の場合
                if (WEB3MarketCodeDef.JNX_PTS.equals(l_strMarketCode))
                {
                    WEB3EquityFrontOrderService l_frontOrderService =
                        (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);

                    // 株式注文取引キューテーブルへ登録する。
                    //登録内容は、DB更新仕様
                    //「(PTS)株式注文訂正_株式注文取引キューテーブル.xls」 参照。

                    HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams = new HostEqtypeOrderAllParams();

                    // データコード
                    l_hostEqtypeOrderAllParams.setRequestCode(
                        CHANGE_CANCEL_REQUEST_CODE);
                    // 口座ID
                    l_hostEqtypeOrderAllParams.setAccountId(l_eqtypeOderUnitRow.getAccountId());
                    // 証券会社コード
                    l_hostEqtypeOrderAllParams.setInstitutionCode(
                        l_strInstitutionCode);
                    // 部店コード
                    l_hostEqtypeOrderAllParams.setBranchCode(
                        l_strBranchCode);
                    // 口座コード
                    l_hostEqtypeOrderAllParams.setAccountCode(
                        l_strAccountCode);
                    // 扱者コード（SONAR）
                    l_hostEqtypeOrderAllParams.setSonarTraderCode(
                        l_strTraderCode);
                    // 識別コード
                    l_hostEqtypeOrderAllParams.setOrderRequestNumber(
                        l_strOrderRequestNumber);
                    // 注文履歴番号
                    l_hostEqtypeOrderAllParams.setOrderActionSerialNo(
                        l_eqtypeOderUnitRow.getLastOrderActionSerialNo());
                    // 銘柄コード
                    l_hostEqtypeOrderAllParams.setProductCode(
                        l_strPoductCode);
                    // 受注日時
                    l_hostEqtypeOrderAllParams.setReceivedDateTime(
                        l_tsReceivedDateTime);
                    // 発注経路区分
                    l_hostEqtypeOrderAllParams.setSubmitOrderRouteDiv(
                        l_eqtypeOderUnitRow.getSubmitOrderRouteDiv());
                    if (SideEnum.SELL.equals(l_orderUnit.getSide()))
                    {
                        // 売付数量
                        l_hostEqtypeOrderAllParams.setSellOrderQuantity(
                            l_eqtypeOderUnitRow.getConfirmedQuantity());
                        // 買付数量
                        l_hostEqtypeOrderAllParams.setBuyOrderQuantity(0D);
                    }
                    else
                    {
                        // 売付数量
                        l_hostEqtypeOrderAllParams.setSellOrderQuantity(0D);
                        // 買付数量
                        l_hostEqtypeOrderAllParams.setBuyOrderQuantity(
                            l_eqtypeOderUnitRow.getConfirmedQuantity());
                    }
                    // 指値
                    l_hostEqtypeOrderAllParams.setLimitPrice(
                        l_eqtypeOderUnitRow.getConfirmedPrice());
                    // 執行条件（SONAR）
                    String l_strConfirmedExecConditionType = null;
                    if (l_orderMgr.isCarriedOrderUnit((EqTypeOrderUnit)l_orderUnit))
                    {
                        l_strConfirmedExecConditionType =
                            WEB3ExecutionConditionDef.COME_TO_TERMS;
                    }
                    else
                    {
                        l_strConfirmedExecConditionType =
                            l_orderMgr.getExecutionConditionTypeSonar(
                                l_eqtypeOderUnitRow.getConfirmedExecConditionType());
                    }
                    l_hostEqtypeOrderAllParams.setExecutionCondition(
                        l_strConfirmedExecConditionType);
                    // 値段条件（SONAR）
                    l_hostEqtypeOrderAllParams.setPriceConditionType(
                        l_orderMgr.getPriceConditionTypeSonar(
                            l_eqtypeOderUnitRow.getConfirmedPriceConditionType()));
                    // 訂正数量
                    l_hostEqtypeOrderAllParams.setChangeQuantity(
                        l_dblOrderQuantity);
                    // 訂正指値
                    l_hostEqtypeOrderAllParams.setChangeLimitPrice(
                        l_dblLimitPrice);
                    // 訂正執行条件（SONAR）
                    l_hostEqtypeOrderAllParams.setChangeExecutionCondition(
                        l_strExecutionCondition);
                    // 訂正値段条件（SONAR）
                    l_hostEqtypeOrderAllParams.setChangePriceConditionType(
                        l_strPriceConditionType);
                    // 訂正時間
                    l_hostEqtypeOrderAllParams.setChangeOrderTime(
                        l_strLastUpdatedDate);
                    // 訂正区分（前日／当日）
                    l_hostEqtypeOrderAllParams.setChangeOrderDateDiv(
                        l_strChangeOrderDateDiv);
                    // 取消区分
                    l_hostEqtypeOrderAllParams.setCancelDiv(
                        WEB3CancelDivDef.EXCEPT_CANCEL);
                    // フロント発注取引所区分コード
                    l_hostEqtypeOrderAllParams.setFrontOrderExchangeCode(
                        l_frontOrderService.getFrontOrderExchangeCode(l_strMarketCode));
                    // フロント発注システム区分
                    EqTypeOrderUnit l_eqtypeOrderUnit = (EqTypeOrderUnit)l_orderUnit;
                    EqTypeTradedProduct l_tradedProduct =
                        (EqTypeTradedProduct)l_eqtypeOrderUnit.getTradedProduct();
                    EqtypeTradedProductRow l_tradedProductRow =
                        (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
                    String l_strFrontOrderSystemCode =
                        l_frontOrderService.getFrontOrderSystemCode(
                            l_strMarketCode,
                            l_tradedProductRow.getOpenOtcDiv());
                    l_hostEqtypeOrderAllParams.setFrontOrderSystemCode(
                        l_strFrontOrderSystemCode);
                    // フロント発注取引区分コード
                    l_hostEqtypeOrderAllParams.setFrontOrderTradeCode(
                        WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE);
                    // 社内処理項目
                    l_hostEqtypeOrderAllParams.setCorpCode(
                        l_frontOrderService.getCorpCode(l_eqtypeOrderUnit));
                    // （被訂正）社内処理項目
                    l_hostEqtypeOrderAllParams.setOrgCorpCode(
                        l_frontOrderService.getOrgCorpCode(l_eqtypeOrderUnit));
                    // 全訂正処理区分
                    l_hostEqtypeOrderAllParams.setAllOrderChangeDiv(
                        WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE);
                    // 処理区分
                    l_hostEqtypeOrderAllParams.setStatus(
                        WEB3FrontOrderStatusDef.NOT_DEAL);

                    //データ挿入する
                    QueryProcessor l_processor = Processors.getDefaultProcessor();
                    l_processor.doInsertQuery(
                        l_hostEqtypeOrderAllParams);
                    log.exiting(STR_METHOD_NAME);
                    return DefaultMarketRequestSendResult.newSuccessResultInstance(l_lngMsgTokenId);
                }

                boolean l_blnQueueInsUpdFlg = true;

                l_orderMgr.notifyRLS(
                    (EqTypeOrderUnit)l_orderUnit,
                    OrderManagerPersistenceContext.MODIFY_ORDER_ACCEPTED);

                // is訂正市場通知
                boolean l_blnIsChangeMarketNotify =
                    l_orderMgr.isChangeMarketNotify(l_orderUnit);
                // is市場開局時間帯
                boolean l_blnIsTradeOpenTimeZone =
                    WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone();
                // is取引所休憩時間帯
                boolean l_blnIsTradeRestTimeZone =
                    WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone();
                    
                if (l_blnIsTradeRestTimeZone ||
                    (l_blnIsChangeMarketNotify && l_blnIsTradeOpenTimeZone))
                {
                    log.debug("is訂正市場通知==" + l_blnIsChangeMarketNotify);
                    log.debug("is市場開局時間帯==" + l_blnIsTradeOpenTimeZone);
                    log.debug("is取引所休憩時間帯==" + l_blnIsTradeRestTimeZone);
                    
                    WEB3EquityFrontOrderService l_frontOrderService =
                        (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
                    HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams = null;
                    if (l_blnIsTradeRestTimeZone)
                    {
                        l_hostEqtypeOrderAllParams =
                            l_frontOrderService.getHostEqtypeOrderAll(
                                (EqTypeOrderUnit)l_orderUnit);
                    }
                    
                    if (l_hostEqtypeOrderAllParams == null)
                    {
                        //is訂正市場通知==false(訂正通知不要の場合)はキューに登録・更新を行わない
                        if(!l_blnIsChangeMarketNotify)
                        {
                            log.debug("訂正通知不要のため、キューに登録・更新を行わない（訂正を完了する）");
                            l_blnQueueInsUpdFlg = false;
                        }
                        else
                        {
                            l_hostEqtypeOrderAllParams = new HostEqtypeOrderAllParams();

                            // データコード
                            l_hostEqtypeOrderAllParams.setRequestCode(
                                CHANGE_CANCEL_REQUEST_CODE);
                            // 口座ID
                            l_hostEqtypeOrderAllParams.setAccountId(l_eqtypeOderUnitRow.getAccountId());
                            // 証券会社コード
                            l_hostEqtypeOrderAllParams.setInstitutionCode(
                                l_strInstitutionCode);
                            // 部店コード
                            l_hostEqtypeOrderAllParams.setBranchCode(
                                l_strBranchCode);
                            // 口座コード
                            l_hostEqtypeOrderAllParams.setAccountCode(
                                l_strAccountCode);
                            // 扱者コード（SONAR）
                            l_hostEqtypeOrderAllParams.setSonarTraderCode(
                                l_strTraderCode);
                            // 識別コード
                            l_hostEqtypeOrderAllParams.setOrderRequestNumber(
                                l_strOrderRequestNumber);
                            // 注文履歴番号
                            l_hostEqtypeOrderAllParams.setOrderActionSerialNo(
                                l_eqtypeOderUnitRow.getLastOrderActionSerialNo());
                            // 銘柄コード
                            l_hostEqtypeOrderAllParams.setProductCode(
                                l_strPoductCode);
                            // 受注日時
                            l_hostEqtypeOrderAllParams.setReceivedDateTime(
                                l_tsReceivedDateTime);
                            // 発注経路区分
                            l_hostEqtypeOrderAllParams.setSubmitOrderRouteDiv(
                                l_eqtypeOderUnitRow.getSubmitOrderRouteDiv());
                            if (SideEnum.SELL.equals(l_orderUnit.getSide()))
                            {
                                // 売付数量
                                l_hostEqtypeOrderAllParams.setSellOrderQuantity(
                                    l_eqtypeOderUnitRow.getConfirmedQuantity());
                                // 買付数量
                                l_hostEqtypeOrderAllParams.setBuyOrderQuantity(0D);
                            }
                            else
                            {
                                // 売付数量
                                l_hostEqtypeOrderAllParams.setSellOrderQuantity(0D);
                                // 買付数量
                                l_hostEqtypeOrderAllParams.setBuyOrderQuantity(
                                    l_eqtypeOderUnitRow.getConfirmedQuantity());
                            }
                            // 指値
                            l_hostEqtypeOrderAllParams.setLimitPrice(
                                l_eqtypeOderUnitRow.getConfirmedPrice());
                            // 執行条件（SONAR）
                            String l_strConfirmedExecConditionType = null;
                            if (l_orderMgr.isCarriedOrderUnit((EqTypeOrderUnit)l_orderUnit))
                            {
                                l_strConfirmedExecConditionType =
                                    WEB3ExecutionConditionDef.COME_TO_TERMS;
                            }
                            else
                            {
                                l_strConfirmedExecConditionType = 
                                    l_orderMgr.getExecutionConditionTypeSonar(
                                        l_eqtypeOderUnitRow.getConfirmedExecConditionType());
                            }
                            l_hostEqtypeOrderAllParams.setExecutionCondition(
                                l_strConfirmedExecConditionType);
                            // 値段条件（SONAR）
                            l_hostEqtypeOrderAllParams.setPriceConditionType(
                                l_orderMgr.getPriceConditionTypeSonar(
                                    l_eqtypeOderUnitRow.getConfirmedPriceConditionType()));
                            // 訂正数量
                            l_hostEqtypeOrderAllParams.setChangeQuantity(
                                l_dblOrderQuantity);
                            // 訂正指値
                            l_hostEqtypeOrderAllParams.setChangeLimitPrice(
                                l_dblLimitPrice);
                            // 訂正執行条件（SONAR）
                            l_hostEqtypeOrderAllParams.setChangeExecutionCondition(
                                l_strExecutionCondition);
                            // 訂正値段条件（SONAR）
                            l_hostEqtypeOrderAllParams.setChangePriceConditionType(
                                l_strPriceConditionType);
                            // 訂正時間
                            l_hostEqtypeOrderAllParams.setChangeOrderTime(
                                l_strLastUpdatedDate);
                            // 訂正区分（前日／当日）
                            l_hostEqtypeOrderAllParams.setChangeOrderDateDiv(
                                l_strChangeOrderDateDiv);
                            // 取消区分
                            l_hostEqtypeOrderAllParams.setCancelDiv(
                                WEB3CancelDivDef.EXCEPT_CANCEL);
                            // フロント発注取引所区分コード
                            l_hostEqtypeOrderAllParams.setFrontOrderExchangeCode(
                                l_frontOrderService.getFrontOrderExchangeCode(l_strMarketCode));
                            // フロント発注システム区分
                            EqTypeOrderUnit l_eqtypeOrderUnit = (EqTypeOrderUnit)l_orderUnit;
                            EqTypeTradedProduct l_tradedProduct =
                                (EqTypeTradedProduct)l_eqtypeOrderUnit.getTradedProduct();
                            EqtypeTradedProductRow l_tradedProductRow =
                                (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
                            String l_strFrontOrderSystemCode =
                                l_frontOrderService.getFrontOrderSystemCode(
                                    l_strMarketCode,
                                    l_tradedProductRow.getOpenOtcDiv());
                            l_hostEqtypeOrderAllParams.setFrontOrderSystemCode(
                                l_strFrontOrderSystemCode);
                            // フロント発注取引区分コード
                            l_hostEqtypeOrderAllParams.setFrontOrderTradeCode(
                                WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE);
                            // 社内処理項目
                            l_hostEqtypeOrderAllParams.setCorpCode(
                                l_frontOrderService.getCorpCode(l_eqtypeOrderUnit));
                            // （被訂正）社内処理項目
                            l_hostEqtypeOrderAllParams.setOrgCorpCode(
                                l_frontOrderService.getOrgCorpCode(l_eqtypeOrderUnit));
                            // 全訂正処理区分
                            l_hostEqtypeOrderAllParams.setAllOrderChangeDiv(
                                WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE);
                            // 処理区分
                            l_hostEqtypeOrderAllParams.setStatus(
                                WEB3FrontOrderStatusDef.NOT_DEAL);
                        
                            //データ挿入する
                            QueryProcessor processor = Processors.getDefaultProcessor();
                            processor.doInsertQuery(
                                l_hostEqtypeOrderAllParams);
                        }
                    }
                    else
                    {
                        // 注文履歴番号
                        l_hostEqtypeOrderAllParams.setOrderActionSerialNo(
                            l_eqtypeOderUnitRow.getLastOrderActionSerialNo());
                        // 受注日時
                        l_hostEqtypeOrderAllParams.setReceivedDateTime(
                            l_tsReceivedDateTime);
                        // 発注経路区分
                        l_hostEqtypeOrderAllParams.setSubmitOrderRouteDiv(
                            l_eqtypeOderUnitRow.getSubmitOrderRouteDiv());
                        // 訂正数量
                        l_hostEqtypeOrderAllParams.setChangeQuantity(
                            l_dblOrderQuantity);
                        // 訂正指値
                        l_hostEqtypeOrderAllParams.setChangeLimitPrice(
                            l_dblLimitPrice);
                        // 訂正執行条件（SONAR）
                        l_hostEqtypeOrderAllParams.setChangeExecutionCondition(
                            l_strExecutionCondition);
                        // 訂正値段条件（SONAR）
                        l_hostEqtypeOrderAllParams.setChangePriceConditionType(
                            l_strPriceConditionType);
                        // 訂正時間
                        l_hostEqtypeOrderAllParams.setChangeOrderTime(
                            l_strLastUpdatedDate);
                        // 訂正区分（前日／当日）
                        l_hostEqtypeOrderAllParams.setChangeOrderDateDiv(
                            l_strChangeOrderDateDiv);
                        //更新日付
                        l_hostEqtypeOrderAllParams.setLastUpdatedTimestamp(
                            l_tsLastUpdateDateTime);
                        // データ更新する
                        QueryProcessor processor = Processors.getDefaultProcessor();
                        processor.doUpdateQuery(
                            l_hostEqtypeOrderAllParams);
                    }
                    
                    if (l_blnQueueInsUpdFlg == true)
                    {
                        //isトリガ発行()をコール
                        l_isTriggerIssue =
                            WEB3GentradeTradingTimeManagement
                                .isSubmitMarketTrigger(
                                null);

                        boolean l_isSubmitMqTrigger =
                            WEB3GentradeOrderSwitching.isSubmitMQTriggerEnable(
                                l_strInstitutionCode,
                                l_eqtypeOderUnitRow.getProductType(),
                                l_strMarketCode,
                                l_eqtypeOderUnitRow.getSubmitOrderRouteDiv(),
                                l_hostEqtypeOrderAllParams.getFrontOrderSystemCode());
                        
                        if (l_isTriggerIssue && l_isSubmitMqTrigger)
                        {
                            String l_strMQDataCode =
                                l_frontOrderService.getChangeCancelMQDataCode(l_eqtypeOderUnitRow.getSubmitOrderRouteDiv());
                            if (l_strMQDataCode == null)
                            {
                                log.debug("新規注文送信 成功（発注停止中）！！！");
                                return DefaultMarketRequestSendResult
                                    .newSuccessResultInstance(
                                    l_lngMsgTokenId);
                            }
                        
                            l_web3MQGatewayService =
                                (WEB3MQGatewayService)Services.getService(
                                    WEB3MQGatewayService.class);

                            l_web3MQMessageSpec =
                                new WEB3MQMessageSpec(
                                    l_strInstitutionCode,
                                    l_strMQDataCode);

                            l_web3MQSendResult =
                                l_web3MQGatewayService.send(
                                    l_web3MQMessageSpec);

                            if (l_web3MQSendResult.isSuccessResult())
                            {
                                log.debug("==> 訂正注文送信 成功！！！");
                                return DefaultMarketRequestSendResult
                                    .newSuccessResultInstance(
                                    l_lngMsgTokenId);
                            }
                            else
                            {
                                log.debug("==> 訂正注文送信 失敗 ！！！");
                                l_processingResult =
                                    ProcessingResult.newFailedResultInstance(
                                        l_web3MQSendResult.getErrorInfo());
                                return DefaultMarketRequestSendResult
                                    .newFailedResultInstance(
                                    l_processingResult);
                            }
                        }
                    }
                }
                else
                {
                    l_blnQueueInsUpdFlg = false;
                }

                if (l_blnQueueInsUpdFlg == false)
                {
                    l_changeConfirmInterceptor = 
                        new WEB3EquityChangeConfirmInterceptor();                                                                                                                                                                                                                                          
                    l_orderMgr.setThreadLocalPersistenceEventInterceptor(
                        l_changeConfirmInterceptor);                                                                                                                        
                    l_processingResult = l_callBackService.process(
                        new DefaultChangeOrderAcceptedMarketResponseMessage(l_lngOrderId));    
                    if (l_processingResult.isFailedResult())
                    {
                        return DefaultMarketRequestSendResult.newFailedResultInstance(
                            l_processingResult);
                    }                                                                                                                    
                }
            }
        }
        catch (DataNetworkException dne)
        {
            log.error(
                "__an unexpected error__",
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    dne.toString(),
                    dne));
            throw new TooLateException(dne.getMessage());
        }
        catch (DataQueryException dqe)
        {
            log.error(
                "__an unexpected error__",
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    dqe.toString(),
                    dqe));
            throw new TooLateException(dqe.getMessage());
        }
        catch (NotFoundException nfe)
        {
            log.error(
                "__an unexpected error__",
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    nfe.toString(),
                    nfe));
            throw new TooLateException(nfe.getMessage());
        }
        catch (WEB3SystemLayerException wse)
        {
            log.error(
                "__an unexpected error__",
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    wse.toString(),
                    wse));
            throw new TooLateException(wse.getMessage());
        }
        catch (WEB3BaseException l_wbe)
        {
            log.error("__an unexpected error__", l_wbe);
            l_processingResult =
                ProcessingResult.newFailedResultInstance(l_wbe.getErrorInfo());
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }

        log.exiting(STR_METHOD_NAME);
        return DefaultMarketRequestSendResult.newSuccessResultInstance(
            l_lngMsgTokenId);
    }

    /**
     * （新規建注文送信）。<BR>
     * <BR>
     * 新規建注文の送信を行う。<BR>
     * （send(EqTypeOpenContractOrderMarketRequestMessage)のオーバーライド）<BR>
     * <BR>
     * 信用取引新規建サービス、注文繰越サービス、信用取引注文通知サービスより<BR>
     * コールされる。<BR>
     * -------------------------------------------------------------------<BR>
     * ○WebⅢ入力注文の場合（注文経路区分≠HOST）<BR>
     * 　@－【株式注文キューテーブル】に対し、発注データをInsert等する。<BR>
     * 　@－MQトリガを発行する。<BR>
     * <BR>
     * ○SONAR入力注文の場合（注文経路区分＝HOST）<BR>
     * 　@－「新規建注文受付OK」の処理を行い、注文ステータスを"発注済"にする。<BR>
     * -------------------------------------------------------------------<BR>
     * <BR>
     * 処理内容は、<BR>
     * シーケンス図「（信用取引市場ﾘｸｴｽﾄ）新規建注文送信」を参照。<BR>
     * <BR>
     * @@param l_requestMessage （信用新規建注文市場リクエストメッセージ）<BR>
     * 信用新規建注文市場リクエストメッセージ
     * @@return MarketRequestSendResult
     * @@roseuid 414544E701F1
     */
    public MarketRequestSendResult send(EqTypeOpenContractOrderMarketRequestMessage l_requestMessage)
    {
        final String STR_METHOD_NAME =
            "send(EqTypeOpenContractOrderMarketRequestMessage)";
        log.entering(STR_METHOD_NAME);
        long l_lngMsgTokenId = 0;
        try
        {
            long l_orderId = l_requestMessage.getOrderId();
            //3.getOrderUnits(注文ID : long)
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            //注文マネージャにて、注文IDに該当する注文オブジェクトを取得する。<BR>
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            MarketAdapter l_marketAdapter = l_tradingModule.getMarketAdapter();
            AccountManager l_accountManager = l_finApp.getAccountManager();

            //注文オブジェクト.getOrderUnits( )で<BR>
            OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_orderId);
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
            EqtypeOrderUnitRow l_orderUnitRow =
                (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            Branch l_branch =
                l_accountManager.getBranch(l_orderUnitRow.getBranchId());
        
            //4.注文単位.注文経路区分　@＝　@"HOST"
            if (WEB3OrderRootDivDef.HOST.equals(l_orderUnitRow.getOrderRootDiv()))
            {
                //5.株式注文受付インタセプタ
                WEB3EquityOrderAcceptPersistenceInterceptor l_orderAccepterInterceptor =
                    new WEB3EquityOrderAcceptPersistenceInterceptor(null);
                //6.setThreadLocalPersistenceEventInterceptor
                l_orderManager.setThreadLocalPersistenceEventInterceptor(
                    l_orderAccepterInterceptor);
                //7.getMarketResponseReceiverCallbackService( )
                MarketResponseReceiverCallbackService l_marketRequestSenderService =
                    l_marketAdapter.getMarketResponseReceiverCallbackService();
                //8.DefaultNewOrderAcceptedMarketResponseMessage
                DefaultNewOrderAcceptedMarketResponseMessage l_responseMessage =
                    new DefaultNewOrderAcceptedMarketResponseMessage(l_orderId);
                //
                ProcessingResult l_processingResult = l_marketRequestSenderService.process(l_responseMessage);
                
                if (l_processingResult.isFailedResult())
                {
                    return DefaultMarketRequestSendResult
                            .newFailedResultInstance(
                            l_processingResult);
                }
                return DefaultMarketRequestSendResult.newSuccessResultInstance(
                    l_lngMsgTokenId);
            }
            
            l_orderManager.notifyRLS(
                l_orderUnit,
                OrderManagerPersistenceContext.NEW_OPEN_CONTRACT_ORDER);
            
            //12注文単位.発注条件＝”逆指値”
            if (WEB3OrderingConditionDef
                .STOP_LIMIT_PRICE
                .equals(l_orderUnitRow.getOrderConditionType()))
            {
                return DefaultMarketRequestSendResult.newSuccessResultInstance(
                    l_lngMsgTokenId);
            }

            l_orderManager.insertMarginOpenHostOrder(l_orderId);
            
            // 発注経路区分
            String l_strSubmitOrderRouteDiv =
                l_orderUnitRow.getSubmitOrderRouteDiv();
            // フロント発注取引所区分コード
            WEB3EquityFrontOrderService l_frontOrderService =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market =
                l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
            String l_strMarketCode = l_market.getMarketCode();
            // フロント発注システム区分
            EqTypeTradedProduct l_tradedProduct =
                (EqTypeTradedProduct)l_orderUnit.getTradedProduct();
            EqtypeTradedProductRow l_tradedProductRow =
                (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
            String l_strFrontOrderSystemCode =
                l_frontOrderService.getFrontOrderSystemCode(
                    l_strMarketCode,
                    l_tradedProductRow.getOpenOtcDiv());
            
            boolean l_isTriggerIssue =
                WEB3GentradeTradingTimeManagement
                    .isSubmitMarketTrigger(l_orderUnitRow.getOrderConditionType());
            boolean l_isSubmitMqTrigger =
                WEB3GentradeOrderSwitching.isSubmitMQTriggerEnable(
                    l_branch.getInstitution().getInstitutionCode(),
                    l_orderUnitRow.getProductType(),
                    l_strMarketCode,
                    l_strSubmitOrderRouteDiv,
                    l_strFrontOrderSystemCode);
            
            if (l_isTriggerIssue && l_isSubmitMqTrigger)
            {
                String l_strMQDataCode =
                    l_frontOrderService.getOrderMQDataCode(l_strSubmitOrderRouteDiv);
                if (l_strMQDataCode == null)
                {
                    log.debug("新規注文送信 成功（発注停止中）！！！");
                    return DefaultMarketRequestSendResult
                        .newSuccessResultInstance(
                        l_lngMsgTokenId);
                }
                WEB3MQMessageSpec l_mqMessageSpec =
                    new WEB3MQMessageSpec(
                        l_branch.getInstitution().getInstitutionCode(),
                        l_strMQDataCode);
                //18.send(WEB3MQMessageSpec)(
                WEB3MQGatewayService l_mqGatewayService =
                    (WEB3MQGatewayService)Services.getService(
                        WEB3MQGatewayService.class);
                WEB3MQSendResult l_web3MQSendResult= l_mqGatewayService.send(l_mqMessageSpec);
                ProcessingResult l_processingResult = null;
                if (l_web3MQSendResult.isSuccessResult())
                {
                    log.debug("==> 新規建注文送信 成功！！！");
                    return DefaultMarketRequestSendResult
                        .newSuccessResultInstance(l_lngMsgTokenId);
                }
                else
                {
                    log.debug("==> 新規建注文送信 失敗 ！！！");
                    l_processingResult =ProcessingResult.newFailedResultInstance(
                                                       l_web3MQSendResult.getErrorInfo());
                                               return DefaultMarketRequestSendResult
                                                   .newFailedResultInstance(
                                                   l_processingResult);
                }
            }
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME,l_nfe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_wbe)
        {
            log.error("__an unexpected error__", l_wbe);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(l_wbe.getErrorInfo());
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }
        log.exiting(STR_METHOD_NAME);
        return DefaultMarketRequestSendResult.newSuccessResultInstance(
                   l_lngMsgTokenId);
    }

    /**
     * （返済注文送信）。<BR>
     * <BR>
     * 返済注文の送信を行う。<BR>
     * （send(EqTypeSettleContractOrderMarketRequestMessage)のオーバーライド）<BR>
     * <BR>
     * 信用取引返済サービス、注文繰越サービス、信用取引注文通知サービスより
     * コールされる。<BR>
     * -------------------------------------------------------------------<BR>
     * ○WebⅢ入力注文の場合（注文経路区分≠HOST）<BR>
     * 　@－【株式注文キューテーブル】に対し、発注データをInsert等する。<BR>
     * 　@－MQトリガを発行する。<BR>
     * <BR>
     * ○SONAR入力注文の場合（注文経路区分＝HOST）<BR>
     * 　@－「返済注文受付OK」の処理を行い、注文ステータスを"発注済"にする。<BR>
     * -------------------------------------------------------------------<BR>
     * <BR>
     * 処理内容は、<BR>
     * シーケンス図「（信用取引市場ﾘｸｴｽﾄ）返済注文送信」を参照。
     * @@param l_requestMessage （信用返済注文市場リクエストメッセージ）<BR>
     * 信用返済注文市場リクエストメッセージ
     * @@return MarketRequestSendResult
     */
    public MarketRequestSendResult send(EqTypeSettleContractOrderMarketRequestMessage l_requestMessage)
    {
        final String STR_METHOD_NAME =
            "send(EqTypeNewCashBasedOrderMarketRequestMessage)";
        log.entering(STR_METHOD_NAME);
        long l_lngMsgTokenId = 0;
        try
        {
            //2.getOrderId( )(
            long l_orderId = l_requestMessage.getOrderId();
            //3.getOrderUnits(注文ID : long)
            //注文マネージャにて、注文IDに該当する注文オブジェクトを取得する。<BR>
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            MarketAdapter l_marketAdapter = l_tradingModule.getMarketAdapter();
            //注文オブジェクト.getOrderUnits( )で<BR>
            OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_orderId);
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];

            EqtypeOrderUnitRow l_orderUnitRow =
                (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

            if (l_orderManager.isApproveForcedSettleOrder(l_orderUnit))
            {
                return DefaultMarketRequestSendResult.newSuccessResultInstance(
                    l_lngMsgTokenId);
            }

            if (WEB3OrderRootDivDef.HOST.equals(l_orderUnitRow.getOrderRootDiv()))
            {
                //5.株式注文受付インタセプタ
                WEB3EquityOrderAcceptPersistenceInterceptor l_orderAccepterInterceptor =
                    new WEB3EquityOrderAcceptPersistenceInterceptor(null);
                //6.setThreadLocalPersistenceEventInterceptor
                l_orderManager.setThreadLocalPersistenceEventInterceptor(
                    l_orderAccepterInterceptor);
                //7.getMarketResponseReceiverCallbackService( )
                MarketResponseReceiverCallbackService l_marketRequestSenderService =
                    l_marketAdapter.getMarketResponseReceiverCallbackService();
                //8.DefaultNewOrderAcceptedMarketResponseMessage
                DefaultNewOrderAcceptedMarketResponseMessage l_responseMessage =
                    new DefaultNewOrderAcceptedMarketResponseMessage(l_orderId);
                //
                ProcessingResult l_processingResult = l_marketRequestSenderService.process(l_responseMessage);
                if (l_processingResult.isFailedResult())
                {
                    return DefaultMarketRequestSendResult
                            .newFailedResultInstance(
                            l_processingResult);
                }
                
                return DefaultMarketRequestSendResult.newSuccessResultInstance(
                    l_lngMsgTokenId);
            
            }
            
            l_orderManager.notifyRLS(
                l_orderUnit,
                OrderManagerPersistenceContext.NEW_SETTLE_CONTRACT_ORDER);
            
            //12注文単位.発注条件＝”逆指値”
            if (WEB3OrderingConditionDef
                .STOP_LIMIT_PRICE
                .equals(l_orderUnitRow.getOrderConditionType()))
            {
                return DefaultMarketRequestSendResult.newSuccessResultInstance(
                    l_lngMsgTokenId);
            }
            
            l_orderManager.insertMarginCloseHostOrder(l_orderId);

            AccountManager l_accountManager = l_finApp.getAccountManager();
            Branch l_branch =
                l_accountManager.getBranch(l_orderUnitRow.getBranchId());

            // 発注経路区分
            String l_strSubmitOrderRouteDiv =
                l_orderUnitRow.getSubmitOrderRouteDiv();
            // フロント発注取引所区分コード
            WEB3EquityFrontOrderService l_frontOrderService =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market =
                l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
            String l_strMarketCode = l_market.getMarketCode();
            // フロント発注システム区分
            EqTypeTradedProduct l_tradedProduct =
                (EqTypeTradedProduct)l_orderUnit.getTradedProduct();
            EqtypeTradedProductRow l_tradedProductRow =
                (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
            String l_strFrontOrderSystemCode =
                l_frontOrderService.getFrontOrderSystemCode(
                    l_strMarketCode,
                    l_tradedProductRow.getOpenOtcDiv());
            
            //15. isトリガ発行(String)(
            boolean l_isTriggerIssue =
                WEB3GentradeTradingTimeManagement
                    .isSubmitMarketTrigger(l_orderUnitRow.getOrderConditionType());
            boolean l_isSubmitMqTrigger =
                WEB3GentradeOrderSwitching.isSubmitMQTriggerEnable(
                    l_branch.getInstitution().getInstitutionCode(),
                    l_orderUnitRow.getProductType(),
                    l_strMarketCode,
                    l_strSubmitOrderRouteDiv,
                    l_strFrontOrderSystemCode);
            
            if (l_isTriggerIssue && l_isSubmitMqTrigger)
            {
                String l_strMQDataCode =
                    l_frontOrderService.getOrderMQDataCode(l_strSubmitOrderRouteDiv);
                if (l_strMQDataCode == null)
                {
                    log.debug("新規注文送信 成功（発注停止中）！！！");
                    return DefaultMarketRequestSendResult
                        .newSuccessResultInstance(
                        l_lngMsgTokenId);
                }
                //16.WEB3MQMessageSpec(String, String)(
                WEB3MQMessageSpec l_mqMessageSpec =
                    new WEB3MQMessageSpec(
                        l_branch.getInstitution().getInstitutionCode(),
                        l_strMQDataCode);
                //18.send(WEB3MQMessageSpec)(
                WEB3MQGatewayService l_mqGatewayService =
                    (WEB3MQGatewayService)Services.getService(
                        WEB3MQGatewayService.class);
                WEB3MQSendResult l_web3MQSendResult = l_mqGatewayService.send(l_mqMessageSpec);
                log.exiting(STR_METHOD_NAME);
                ProcessingResult l_processingResult = null;
                if (l_web3MQSendResult.isSuccessResult())
                {
                    log.debug("==> 返済注文送信 成功！！！");
                    return DefaultMarketRequestSendResult
                        .newSuccessResultInstance(l_lngMsgTokenId);
                }
                else
                {
                    log.debug("==> 返済注文送信 失敗 ！！！");
                    l_processingResult =ProcessingResult.newFailedResultInstance(
                    l_web3MQSendResult.getErrorInfo());
                    return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
                }
            }           
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME,l_nfe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_wbe)
        {
            log.error("__an unexpected error__", l_wbe);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(l_wbe.getErrorInfo());
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }
        return DefaultMarketRequestSendResult.newSuccessResultInstance(
                  l_lngMsgTokenId);
    }

    /**
     * （現引現渡注文送信）。<BR>
     * <BR>
     * 現引現渡注文の送信を行う。<BR>
     * （send(EqTypeSwapContractOrderMarketRequestMessage)のオーバーライド）<BR>
     * <BR>
     * 信用取引現引現渡サービスからコールされる。<BR>
     * -------------------------------------------------------------------<BR>
     * 　@－【現引現渡キューテーブル】に対し、発注データをInsertする。<BR>
     * 　@－MQトリガを発行する。<BR>
     * -------------------------------------------------------------------<BR>
     * <BR>
     * 処理内容は、<BR>
     * シーケンス図「（信用取引市場ﾘｸｴｽﾄ）現引現渡注文送信」を参照。
     * @@param l_requestMessage （信用現引現渡注文市場リクエストメッセージ）<BR>
     * 信用現引現渡注文市場リクエストメッセージ
     * @@return MarketRequestSendResult
     * @@roseuid 414544E70255
     */
    public MarketRequestSendResult send(EqTypeSwapContractOrderMarketRequestMessage l_requestMessage)
    {
        final String STR_METHOD_NAME =
            "send(EqTypeNewCashBasedOrderMarketRequestMessage)";
        log.entering(STR_METHOD_NAME);
        //2.getOrderId( )(
        long l_orderId = l_requestMessage.getOrderId();
        //3.getOrderUnits(注文ID : long)
        //注文マネージャにて、注文IDに該当する注文オブジェクトを取得する。<BR>
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        MarketAdapter l_marketAdapter = l_tradingModule.getMarketAdapter();
        //注文オブジェクト.getOrderUnits( )で<BR>
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_orderId);
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];

        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        long l_lngMsgTokenId = 0;
        HostEqtypeSwapParams l_params = new HostEqtypeSwapParams();
        AccountManager l_accountManager = l_finApp.getAccountManager();
        ProductManager l_productManager = l_tradingModule.getProductManager();
        try
        {
            //3.注文単位.注文経路区分　@＝　@"HOST"
            if (WEB3OrderRootDivDef.HOST.equals(l_orderUnitRow.getOrderRootDiv()))
            {
                //3.1.信用現引現渡注文受付インタセプタ
                WEB3MarginSwapMarginAcceptInterceptor l_swapMarginAccepterInterceptor =
                    new WEB3MarginSwapMarginAcceptInterceptor(null);
                //3.2.setThreadLocalPersistenceEventInterceptor
                l_orderManager.setThreadLocalPersistenceEventInterceptor(
                    l_swapMarginAccepterInterceptor);
                //3.3.getMarketResponseReceiverCallbackService( )
                MarketResponseReceiverCallbackService l_marketRequestSenderService =
                    l_marketAdapter.getMarketResponseReceiverCallbackService();
                //3.4.DefaultNewOrderAcceptedMarketResponseMessage
                DefaultNewOrderAcceptedMarketResponseMessage l_responseMessage =
                    new DefaultNewOrderAcceptedMarketResponseMessage(l_orderId);
                //3.5.process
                ProcessingResult l_processingResult = l_marketRequestSenderService.process(l_responseMessage);
                
                if (l_processingResult.isFailedResult())
                {
                    return DefaultMarketRequestSendResult
                            .newFailedResultInstance(
                            l_processingResult);
                }
                return DefaultMarketRequestSendResult.newSuccessResultInstance(
                    l_lngMsgTokenId);
            }
            
            Branch l_branch =
                l_accountManager.getBranch(l_orderUnitRow.getBranchId());
            Institution l_institution = l_branch.getInstitution();
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();

            //(*1) キューテーブルに行を挿入する。
            //挿入する行の内容は、DB更新仕様
            //「信用現引現渡_現引現渡キューテーブル.xls」参照。
            //1   データコード  request_code   ”AI805”             
            l_params.setRequestCode(WEB3HostRequestCodeDef.EQUITY_SPOT_ORDER);
            //口座ID
            l_params.setAccountId(l_orderUnitRow.getAccountId());
            //2   証券会社コード  institution_code     株式注文単位.部店ＩＤに該当する部店.証券会社コード  
            l_params.setInstitutionCode(l_institution.getInstitutionCode());
            //3   部店コード                                                  
            l_params.setBranchCode(l_branch.getBranchCode());
            //4   顧客コード  
            MainAccount l_mainAccount = l_accountManager.getMainAccount(l_orderUnit.getAccountId());
            MainAccountRow l_mainAccountRow = (MainAccountRow) l_mainAccount.getDataSourceObject();
            l_params.setAccountCode(l_mainAccount.getAccountCode());
            //5   扱者コード（SONAR）   
            l_params.setSonarTraderCode(l_orderUnitRow.getSonarTraderCode());
            
            //6   銘柄コード 
            WEB3EquityProduct l_product =
                (WEB3EquityProduct)l_productManager.getProduct(
                    l_orderUnitRow.getProductId());
            l_params.setProductCode(l_product.getProductCode());
            //7   現渡数量   
            if (OrderTypeEnum
                .SWAP_MARGIN_SHORT
                .equals(l_orderUnitRow.getOrderType()))
            {
                l_params.setSellOrderQuantity(l_orderUnitRow.getQuantity());
            }
            else
            {
                l_params.setSellOrderQuantity(0);
            }
            //8   現引数量  
            if (OrderTypeEnum
                .SWAP_MARGIN_LONG
                .equals(l_orderUnitRow.getOrderType()))
            {
                l_params.setBuyOrderQuantity(l_orderUnitRow.getQuantity());
            }
            else
            {
                l_params.setBuyOrderQuantity(0);
            }
            //9   弁済区分（SONAR）
            l_params.setSonarRepaymentType(
                l_orderUnitRow.getSonarRepaymentType());
            //10  市場コード（SONAR）
            l_params.setSonarMarketCode(l_orderUnitRow.getSonarMarketCode());
            //11  伝票№ 
            l_params.setTicketNumber(l_orderUnitRow.getVoucherNo());
            //12  譲渡益税区分
            // 現引注文の場合：　@0：なし
            // 現渡注文の場合：
			// 　@個人客（顧客.口座タイプ==("個人アカウント", "共用アカウント")）でかつ、
			//　@　@　@　@居住者、特別非居住者の場合：　@1：申告
			//　@　@　@　@非居住者の場合：　@　@　@　@　@　@　@0：なし
			// 　@法@人客（顧客.口座タイプ=="法@人アカウント"）の場合：　@0：なし
			if (OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_orderUnit.getOrderType()))
			{
				l_params.setCapitalGainTaxType(
					WEB3CapitalGainTaxTypeDef.NOTHING);
			}
			else if (
				MainAccountTypeEnum.INDIVIDUAL_ACCOUNT.equals(l_mainAccountRow.getAccountType()) ||
				MainAccountTypeEnum.JOINT_OWNERSHIP.equals(l_mainAccountRow.getAccountType()))
			{
				if (WEB3ResidentDef.RESIDENT.equals(l_mainAccountRow.getResident()) ||
					WEB3ResidentDef.SPE_NON_RESIDENT.equals(l_mainAccountRow.getResident()))
				{
					l_params.setCapitalGainTaxType(
						WEB3CapitalGainTaxTypeDef.REPORT);
				}
				else if (WEB3ResidentDef.NON_RESIDENT.equals(l_mainAccountRow.getResident()))
				{
					l_params.setCapitalGainTaxType(
						WEB3CapitalGainTaxTypeDef.NOTHING);
				}
			}
            else if(
                MainAccountTypeEnum.CORPORATE_ACCOUNT.equals(l_mainAccountRow.getAccountType()))
            {
                l_params.setCapitalGainTaxType(
                    WEB3CapitalGainTaxTypeDef.NOTHING);
            }
            
            //13  強制 
            l_params.setCheckType(WEB3CheckTypeDef.PRE_CHECK);
            //14  取消  
            l_params.setCancelDiv(WEB3CancelDivDef.EXCEPT_CANCEL);
            //15  識別コード 
            l_params.setOrderRequestNumber(
                l_orderUnitRow.getOrderRequestNumber());
            //注文履歴番号 ： 株式注文単位.注文履歴最終通番
            l_params.setOrderActionSerialNo(l_orderUnitRow.getLastOrderActionSerialNo());
            //16  受注日時
            Timestamp l_tsCurTime =
                GtlUtils.getTradingSystem().getSystemTimestamp();
            l_params.setCreateDatetime(l_tsCurTime);
            //17  税区分（特定口座区分） 
            if (TaxTypeEnum.NORMAL.equals(l_orderUnitRow.getTaxType()))
            {
                l_params.setTaxType(WEB3TaxTypeDef.NORMAL);
            }
            else
            {
                l_params.setTaxType(WEB3TaxTypeDef.SPECIAL);
            }

            //18  税区分（現引現渡現物特定口座区分）  
            if (TaxTypeEnum.NORMAL.equals(l_orderUnitRow.getSwapTaxType()))
            {
                l_params.setSwapTaxType(WEB3TaxTypeDef.NORMAL);
            }
            else
            {
                l_params.setSwapTaxType(WEB3TaxTypeDef.SPECIAL);
            }

            //19  処理区分 status    
            l_params.setStatus(WEB3StatusDef.NOT_DEAL);
            l_queryProcesser.doInsertQuery(l_params);
            //7.isトリガ発行(String)(    
            if (WEB3GentradeTradingTimeManagement
                .isSubmitMarketTrigger(l_orderUnitRow.getOrderConditionType()))
            {
                //8.WEB3MQMessageSpec(String, String)(
                WEB3MQMessageSpec l_mqMessageSpec =
                    new WEB3MQMessageSpec(
                        l_branch.getInstitution().getInstitutionCode(),
                        WEB3HostRequestCodeDef.EQUITY_SPOT_ORDER + DATA_CODE);
                //9.send(WEB3MQMessageSpec)(
                WEB3MQGatewayService l_mqGatewayService =
                    (WEB3MQGatewayService)Services.getService(
                        WEB3MQGatewayService.class);
                WEB3MQSendResult l_web3MQSendResult = l_mqGatewayService.send(l_mqMessageSpec);
                ProcessingResult l_processingResult = null;
                if (l_web3MQSendResult.isSuccessResult())
                {
                    log.debug("==> 現引現渡注文送信 成功！！！");
                    return DefaultMarketRequestSendResult.newSuccessResultInstance(l_lngMsgTokenId);
                }
                else
                {
                    log.debug("==> 現引現渡注文送信 失敗 ！！！");
                        l_processingResult =ProcessingResult.newFailedResultInstance(
                            l_web3MQSendResult.getErrorInfo());
                            return DefaultMarketRequestSendResult
                            .newFailedResultInstance(l_processingResult);
                }
            }           
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME,l_dfe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME,l_nfe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME,l_dne);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME,l_dqe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_wsle)
        {
            log.error(STR_METHOD_NAME,l_wsle);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return DefaultMarketRequestSendResult.newSuccessResultInstance(
                          l_lngMsgTokenId);
    }

    /**
     * （返済訂正注文送信）。<BR>
     * <BR>
     * 返済訂正注文の送信を行う。<BR>
     * （send(EqTypeChangeSettleContractOrderMarketRequestMessage, boolean)のオーバーライド）<BR>
     * <BR>
     * Web3からの返済注文訂正をSONARへ送信する。<BR>
     * 信用取引訂正返済サービスからコールされる。<BR>
     * -------------------------------------------------------------------<BR>
     * ○訂正内容が、市場に通知する必要がある内容の場合<BR>
     * 　@－訂正元注文が市場未送信の場合、【株式注文キューテーブル】の対象データをUpdateする。<BR>
     * 　@－訂正元注文が市場送信済の場合<BR>
     * 　@　@　@・市場開局時間帯（ホスト送信時間帯)の場合、【株式注文訂正取消キューテーブル】に対し<BR>
     * 　@　@　@　@行をInsertし、トリガを発行する時間帯の場合のみMQトリガを発行する。<BR>
     * 　@　@　@・市場閉局時間帯(ホスト送信時間帯外)の場合、訂正を確定させる。<BR>
     * <BR>
     * ○訂正内容が、市場に通知不要である場合<BR>
     * 　@－訂正を確定させる。<BR>
     * -------------------------------------------------------------------<BR>
     * <BR>
     * １）　@注文取得<BR>
     * 　@信用返済注文訂正リクエストメッセージ.getOrderId( ) にて注文IDを取得する。<BR>
     * 　@注文マネージャにて、注文IDに該当する注文オブジェクトを取得する。<BR>
     * 　@注文オブジェクト.getOrderUnits( )で<BR>
     * 　@取得した注文単位オブジェクトの0番目の要素について、以下の処理を行う。<BR>
     * <BR>
     * 以下、注文が市場未送信か送信済かにより、処理が分かれる。<BR>
     * <BR>
     * 注文が市場未送信の場合（引数.is市場未送信 == true）は、<BR>
     * シーケンス図「（信用取引市場ﾘｸｴｽﾄ）返済訂正注文送信（市場未送信注文）」を参照。<BR>
     * 注文が市場送信済の場合（引数.is市場未送信 == false）は、<BR>
     * シーケンス図「（信用取引市場ﾘｸｴｽﾄ）返済訂正注文送信（市場送信済注文）」を参照。
     * @@param l_marginCloseMarginOrderChangeRequestMessage （信用返済注文訂正リクエストメッセージ）<BR>
     * 信用返済注文訂正リクエストメッセージ
     * @@param l_isMarketNoRequestMessage （is市場未送信）<BR>
     * 訂正元注文が市場未送信の場合はtrue、<BR>
     * 訂正元が市場送信済の場合はfalseを指定する。<BR>
     * falseの場合、SONARへ訂正を通知する。
     * @@return MarketRequestSendResult
     * @@roseuid 414544E70291
     */
    public MarketRequestSendResult send(
        EqTypeChangeSettleContractOrderMarketRequestMessage l_requestMessage,
        boolean l_isUnSend)
    {
        final String STR_METHOD_NAME =
            "EqTypeChangeSettleContractOrderMarketRequestMessage l_requestMessage, boolean l_isUnSend)";
        log.entering(STR_METHOD_NAME);

        // １）　@注文取得
        long l_orderId = l_requestMessage.getOrderId();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        //注文マネージャにて、注文IDに該当する注文オブジェクトを取得する。<BR>
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp
                .getTradingModule(ProductTypeEnum.EQUITY)
                .getOrderManager();
        //注文オブジェクト.getOrderUnits( )で<BR>
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_orderId);
        //取得した注文単位オブジェクトの0番目の要素について、以下の処理を行う。
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        AccountManager l_accountManager = l_finApp.getAccountManager();
        long l_lngMsgTokenId = 0;
          
        try
        {
            //識別コードを取得する
            String l_strOrderRequestNumber =
                l_orderUnitRow.getOrderRequestNumber();
            //部店を取得する
            Branch l_banch =
                l_accountManager.getBranch(l_orderUnitRow.getBranchId());
            //証券会社コードを取得する
            String l_strInstitutionCode =
                l_banch.getInstitution().getInstitutionCode();
            //部店コードを取得する
            String l_strBranchCode = l_banch.getBranchCode();
            //顧客コードを取得する
            String l_strAccountCode =
                l_accountManager
                    .getMainAccount(l_orderUnitRow.getAccountId())
                    .getAccountCode();
            //扱者コードを取得する
            String l_strTraderCode = l_orderUnitRow.getSonarTraderCode();

            //銘柄コードを取得する
            String l_strPoductCode =
                ((EqTypeProduct)l_orderUnit.getProduct()).getProductCode();
            //訂正数量を取得する
            double l_dblOrderQuantity = l_orderUnitRow.getQuantity();
            //訂正指値を取得する
            double l_dblLimitPrice = l_orderUnitRow.getLimitPrice();

            //執行条件
            String l_strExecutionCondition = null;
            if (l_orderManager.isCarriedOrderUnit((EqTypeOrderUnit)l_orderUnit))
            {
                l_strExecutionCondition =
                    WEB3ExecutionConditionDef.COME_TO_TERMS;
            }
            else
            {
                l_strExecutionCondition = 
                    l_orderManager.getExecutionConditionTypeSonar(
                        l_orderUnitRow.getExecutionConditionType());
            }

            //値段条件
            String l_strPriceConditionType = l_orderManager.getPriceConditionTypeSonar(
                l_orderUnitRow.getPriceConditionType());
            
            Timestamp l_tsLastUpdateDateTime = GtlUtils.getSystemTimestamp();
            //受注日時
            Timestamp l_tsReceivedDateTime = l_tsLastUpdateDateTime;
            //訂正時間
            String l_strLastUpdatedDate =
                WEB3DateUtility.formatDate(l_tsReceivedDateTime, "HHmm");

            //訂正区分（前日／当日）
            String l_strDate =
                WEB3DateUtility.formatDate(l_tsReceivedDateTime, "yyyyMMdd");
            String l_strChangeOrderDateDiv;
            if (l_orderUnitRow.getBizDate().equals(l_strDate))
            {
                l_strChangeOrderDateDiv = WEB3OrderDateDivDef.TODAY;
            }
            else
            {
                l_strChangeOrderDateDiv = WEB3OrderDateDivDef.YESTERDAY;
            }
            
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            //注文が市場未送信の場合（引数.is市場未送信 == true）は
            if (l_isUnSend)
            {
                l_orderManager.notifyRLS(
                    l_orderUnit,
                    OrderManagerPersistenceContext.MODIFY_ORDER_CONFIRMED_BY_MKT);

                if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType())
                    && !(WEB3RequestTypeDef.QUOTE_SERVER.equals(l_orderUnitRow.getRequestType())))
                {
                    return DefaultMarketRequestSendResult.newSuccessResultInstance(l_lngMsgTokenId);
                }

                log.debug("==> 市場未送信（is市場未送信 == true）の場合の処理");
                //市場未送信（is市場未送信 == true）の場合の処理
                //株式注文キューテーブルの対象データ訂正
                // 株式注文キューテーブルより、以下の条件に一致する行を
                //Select for updateにて検索し、訂正内容を更新する
                //株式注文キューテーブル.データコード = ”株式取引注文”
                //株式注文キューテーブル.証券会社コード = 注文単位.証券会社IDに該当する証券会社コード
                //株式注文キューテーブル.部店コード = 注文単位.部店IDに該当する部店コード
                //株式注文キューテーブル.識別コード = 注文単位.識別コード
                WEB3EquityFrontOrderService l_frontOrderService =
                    (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
                int l_intIndex = l_frontOrderService.getIndexOfOrderRevInCorpCode();
                int l_intFigure = l_frontOrderService.getFigureOfOrderRev();
                String l_strWhere =
                    " request_code=? and institution_code=? and branch_code=? and order_request_number=? and substr(corp_code," + l_intIndex + "," + l_intFigure + ")=? and status=?";
                String[] l_strBindValues = new String[6];
                l_strBindValues[0] = WEB3HostRequestCodeDef.EQUITY_ORDER;
                l_strBindValues[1] = l_strInstitutionCode;
                l_strBindValues[2] = l_strBranchCode;
                l_strBindValues[3] = l_strOrderRequestNumber;
                l_strBindValues[4] = l_orderUnitRow.getOrderRev();
                l_strBindValues[5] = WEB3FrontOrderStatusDef.NOT_DEAL;

                //訂正内容を更新する
                Map l_mapChanges = new HashMap();
                
				//set注文履歴番号 ： 株式注文単位.注文履歴最終通番
				l_mapChanges.put(
				    "order_action_serial_no",
				    new Integer(l_orderUnitRow.getLastOrderActionSerialNo()));
                
                if (OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_orderUnit.getOrderType()))
                {
					//set買付数量
					l_mapChanges.put("buy_order_quantity", new Double("0"));
					//set売付数量
					l_mapChanges.put("sell_order_quantity", new Double(l_dblOrderQuantity));
                }
                else if (OrderTypeEnum.CLOSE_MARGIN_SHORT.equals(l_orderUnit.getOrderType()))
                {
					//set買付数量
					l_mapChanges.put("buy_order_quantity", new Double(l_dblOrderQuantity));
					 //set売付数量
					l_mapChanges.put("sell_order_quantity", new Double("0"));
                }

                //set指値
                l_mapChanges.put(
                    "limit_price",
                    new Double(l_dblLimitPrice));
                //set執行条件（SONAR）
                l_mapChanges.put(
                    "execution_condition",
                    l_strExecutionCondition);
                //set値段条件（SONAR）
                l_mapChanges.put(
                    "price_condition_type",
                    l_strPriceConditionType);
                //set発注経路区分
                l_mapChanges.put(
                    "submit_order_route_div",
                    l_orderUnitRow.getSubmitOrderRouteDiv());
                //set更新日付
                l_mapChanges.put(
                    "last_updated_timestamp",
                    l_tsLastUpdateDateTime);
                int l_intResults = l_queryProcesser.doUpdateAllQuery(
                    (RowType)HostEqtypeOrderAllParams.TYPE,
                    l_strWhere,
                    l_strBindValues,
                    l_mapChanges);
                if (l_intResults == 0)
                {
                    return DefaultMarketRequestSendResult
                        .newFailedResultInstance(
                            ProcessingResult.newFailedResultInstance(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00010));
                }
            }
            else
            {
                boolean l_blnQueueInsUpdFlg = true;

                l_orderManager.notifyRLS(
                    l_orderUnit,
                    OrderManagerPersistenceContext.MODIFY_ORDER_ACCEPTED);
                
                //6. is訂正市場通知要(注文単位)(
                boolean l_isChangeMarketNotify =
                    l_orderManager.isChangeMarketNotify(l_orderUnit);
                //7. is市場開局時間帯()
                boolean l_isTradeOpenTimeZone =
                    WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone();
                // is取引所休憩時間帯
                boolean l_isTradeRestTimeZone =
                    WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone();

                if (l_isTradeRestTimeZone ||
                    (l_isChangeMarketNotify && l_isTradeOpenTimeZone))
                {
                    log.debug("is訂正市場通知==" + l_isChangeMarketNotify);
                    log.debug("is市場開局時間帯==" + l_isTradeOpenTimeZone);
                    log.debug("is取引所休憩時間帯==" + l_isTradeRestTimeZone);
                    
                    WEB3EquityFrontOrderService l_frontOrderService =
                        (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
                    HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams = null;
                    if (l_isTradeRestTimeZone)
                    {
                        l_hostEqtypeOrderAllParams =
                            l_frontOrderService.getHostEqtypeOrderAll(
                                (EqTypeOrderUnit)l_orderUnit);
                    }
                    
                    if (l_hostEqtypeOrderAllParams == null)
                    {
                        //is訂正市場通知==false(訂正通知不要の場合)はキューに登録・更新を行わない
                        if(!l_isChangeMarketNotify)
                        {
                            log.debug("訂正通知不要のため、キューに登録・更新を行わない（訂正を完了する）");
                            l_blnQueueInsUpdFlg = false;
                        }
                        else
                        {
                            l_hostEqtypeOrderAllParams = new HostEqtypeOrderAllParams();
                        
                            // データコード
                            l_hostEqtypeOrderAllParams.setRequestCode(
                                WEB3HostRequestCodeDef.EQUITY_ORDER_CHANGE);     
                            // 口座ID
                            l_hostEqtypeOrderAllParams.setAccountId(l_orderUnitRow.getAccountId());
                            // 証券会社コード
                            l_hostEqtypeOrderAllParams.setInstitutionCode(
                                l_strInstitutionCode);
                            // 部店コード
                            l_hostEqtypeOrderAllParams.setBranchCode(
                                l_strBranchCode);
                            // 口座コード
                            l_hostEqtypeOrderAllParams.setAccountCode(
                                l_strAccountCode);
                            // 扱者コード（SONAR）
                            l_hostEqtypeOrderAllParams.setSonarTraderCode(
                                l_strTraderCode);
                            // 識別コード
                            l_hostEqtypeOrderAllParams.setOrderRequestNumber(
                                l_strOrderRequestNumber);
                            // 注文履歴番号
                            l_hostEqtypeOrderAllParams.setOrderActionSerialNo(
                                l_orderUnitRow.getLastOrderActionSerialNo());
                            // 銘柄コード
                            l_hostEqtypeOrderAllParams.setProductCode(
                                l_strPoductCode);
                            // 受注日時
                            l_hostEqtypeOrderAllParams.setReceivedDateTime(
                                l_tsReceivedDateTime);
                            // 発注経路区分
                            l_hostEqtypeOrderAllParams.setSubmitOrderRouteDiv(
                                l_orderUnitRow.getSubmitOrderRouteDiv());
                            if (SideEnum.SELL.equals(l_orderUnit.getSide()))
                            {
                                // 売付数量
                                l_hostEqtypeOrderAllParams.setSellOrderQuantity(
                                    l_orderUnitRow.getConfirmedQuantity());
                                // 買付数量
                                l_hostEqtypeOrderAllParams.setBuyOrderQuantity(0D);
                            }
                            else
                            {
                                // 売付数量
                                l_hostEqtypeOrderAllParams.setSellOrderQuantity(0D);
                                // 買付数量
                                l_hostEqtypeOrderAllParams.setBuyOrderQuantity(
                                    l_orderUnitRow.getConfirmedQuantity());
                            }
                            // 指値
                            l_hostEqtypeOrderAllParams.setLimitPrice(
                                l_orderUnitRow.getConfirmedPrice());
                            // 執行条件（SONAR）
                            String l_strConfirmedExecConditionType = null;
                            if (l_orderManager.isCarriedOrderUnit((EqTypeOrderUnit)l_orderUnit))
                            {
                                l_strConfirmedExecConditionType =
                                    WEB3ExecutionConditionDef.COME_TO_TERMS;
                            }
                            else
                            {
                                l_strConfirmedExecConditionType = 
                                    l_orderManager.getExecutionConditionTypeSonar(
                                        l_orderUnitRow.getConfirmedExecConditionType());
                            }
                            l_hostEqtypeOrderAllParams.setExecutionCondition(
                                l_strConfirmedExecConditionType);
                            // 値段条件（SONAR）
                            l_hostEqtypeOrderAllParams.setPriceConditionType(
                                l_orderManager.getPriceConditionTypeSonar(
                                    l_orderUnitRow.getConfirmedPriceConditionType()));
                            // 訂正数量
                            l_hostEqtypeOrderAllParams.setChangeQuantity(
                                l_dblOrderQuantity);
                            // 訂正指値
                            l_hostEqtypeOrderAllParams.setChangeLimitPrice(
                                l_dblLimitPrice);
                            // 訂正執行条件（SONAR）
                            l_hostEqtypeOrderAllParams.setChangeExecutionCondition(
                                l_strExecutionCondition);
                            // 訂正値段条件（SONAR）
                            l_hostEqtypeOrderAllParams.setChangePriceConditionType(
                                l_strPriceConditionType);
                            // 訂正時間
                            l_hostEqtypeOrderAllParams.setChangeOrderTime(
                                l_strLastUpdatedDate);
                            // 訂正区分（前日／当日）
                            l_hostEqtypeOrderAllParams.setChangeOrderDateDiv(
                                l_strChangeOrderDateDiv);
                            // 取消区分
                            l_hostEqtypeOrderAllParams.setCancelDiv(
                                WEB3CancelDivDef.EXCEPT_CANCEL);
                            // フロント発注取引所区分コード
                            WEB3GentradeFinObjectManager l_finObjectManager =
                                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                            Market l_market =
                                l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
                            String l_strMarketCode = l_market.getMarketCode();
                            l_hostEqtypeOrderAllParams.setFrontOrderExchangeCode(
                                l_frontOrderService.getFrontOrderExchangeCode(l_strMarketCode));
                            // フロント発注システム区分
                            EqTypeOrderUnit l_eqtypeOrderUnit = (EqTypeOrderUnit)l_orderUnit;
                            EqTypeTradedProduct l_tradedProduct =
                                (EqTypeTradedProduct)l_eqtypeOrderUnit.getTradedProduct();
                            EqtypeTradedProductRow l_tradedProductRow =
                                (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
                            String l_strFrontOrderSystemCode =
                                l_frontOrderService.getFrontOrderSystemCode(
                                    l_strMarketCode,
                                    l_tradedProductRow.getOpenOtcDiv());
                            l_hostEqtypeOrderAllParams.setFrontOrderSystemCode(
                                l_strFrontOrderSystemCode);
                            // フロント発注取引区分コード
                            l_hostEqtypeOrderAllParams.setFrontOrderTradeCode(
                                WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE);
                            // 社内処理項目
                            l_hostEqtypeOrderAllParams.setCorpCode(
                                l_frontOrderService.getCorpCode(l_eqtypeOrderUnit));
                            // （被訂正）社内処理項目
                            l_hostEqtypeOrderAllParams.setOrgCorpCode(
                                l_frontOrderService.getOrgCorpCode(l_eqtypeOrderUnit));
                            // 全訂正処理区分
                            l_hostEqtypeOrderAllParams.setAllOrderChangeDiv(
                                WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE);
                            // 処理区分
                            l_hostEqtypeOrderAllParams.setStatus(
                                WEB3FrontOrderStatusDef.NOT_DEAL);
                        
                            l_queryProcesser.doInsertQuery(l_hostEqtypeOrderAllParams);
                        }
                    }
                    else
                    {
                        // 注文履歴番号
                        l_hostEqtypeOrderAllParams.setOrderActionSerialNo(
                            l_orderUnitRow.getLastOrderActionSerialNo());
                        // 受注日時
                        l_hostEqtypeOrderAllParams.setReceivedDateTime(
                            l_tsReceivedDateTime);
                        // 発注経路区分
                        l_hostEqtypeOrderAllParams.setSubmitOrderRouteDiv(
                            l_orderUnitRow.getSubmitOrderRouteDiv());
                        // 訂正数量
                        l_hostEqtypeOrderAllParams.setChangeQuantity(
                            l_dblOrderQuantity);
                        // 訂正指値
                        l_hostEqtypeOrderAllParams.setChangeLimitPrice(
                            l_dblLimitPrice);
                        // 訂正執行条件（SONAR）
                        l_hostEqtypeOrderAllParams.setChangeExecutionCondition(
                            l_strExecutionCondition);
                        // 訂正値段条件（SONAR）
                        l_hostEqtypeOrderAllParams.setChangePriceConditionType(
                            l_strPriceConditionType);
                        // 訂正時間
                        l_hostEqtypeOrderAllParams.setChangeOrderTime(
                            l_strLastUpdatedDate);
                        // 訂正区分（前日／当日）
                        l_hostEqtypeOrderAllParams.setChangeOrderDateDiv(
                            l_strChangeOrderDateDiv);
                        //set更新日付
                        l_hostEqtypeOrderAllParams.setLastUpdatedTimestamp(
                            l_tsLastUpdateDateTime);
                        // データ更新する
                        l_queryProcesser.doUpdateQuery(
                            l_hostEqtypeOrderAllParams);
                    }
                    
                    if (l_blnQueueInsUpdFlg == true)
                    {
                        boolean l_isTriggerIssue =
                            WEB3GentradeTradingTimeManagement
                                .isSubmitMarketTrigger(null);
                        WEB3GentradeFinObjectManager l_finObjectManager =
                            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                        Market l_market =
                            l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
                        boolean l_isSubmitMqTrigger =
                            WEB3GentradeOrderSwitching.isSubmitMQTriggerEnable(
                                l_strInstitutionCode,
                                l_orderUnitRow.getProductType(),
                                l_market.getMarketCode(),
                                l_orderUnitRow.getSubmitOrderRouteDiv(),
                                l_hostEqtypeOrderAllParams.getFrontOrderSystemCode());
                    
                        if (l_isTriggerIssue && l_isSubmitMqTrigger)
                        {
                            String l_strMQDataCode =
                                l_frontOrderService.getChangeCancelMQDataCode(
                                    l_orderUnitRow.getSubmitOrderRouteDiv());
                            if (l_strMQDataCode == null)
                            {
                                log.debug("新規注文送信 成功（発注停止中）！！！");
                                return DefaultMarketRequestSendResult
                                    .newSuccessResultInstance(
                                    l_lngMsgTokenId);
                            }
                            //12.WEB3MQMessageSpec(String, String)(
                            WEB3MQMessageSpec l_mqMessageSpec =
                                new WEB3MQMessageSpec(
                                l_strInstitutionCode,
                                l_strMQDataCode);
                            //13.send(WEB3MQMessageSpec)(
                            WEB3MQGatewayService l_mqGatewayService =
                                (WEB3MQGatewayService)Services.getService(
                                    WEB3MQGatewayService.class);
                            WEB3MQSendResult l_web3MQSendResult = l_mqGatewayService.send(l_mqMessageSpec);
                            ProcessingResult l_processingResult = null;
                            if (l_web3MQSendResult.isSuccessResult())
                            {
                                log.debug("==> 返済訂正注文送信 成功！！！");
                                return DefaultMarketRequestSendResult
                                    .newSuccessResultInstance(l_lngMsgTokenId);
                            }
                            else
                            {
                                log.debug("==> 返済訂正注文送信 失敗 ！！！");
                                l_processingResult =ProcessingResult.newFailedResultInstance(
                                            l_web3MQSendResult.getErrorInfo());
                                return DefaultMarketRequestSendResult
                                            .newFailedResultInstance(l_processingResult);
                            }
                        }
                    }
                }
                else
                {
                    l_blnQueueInsUpdFlg = false;
                }

                if (l_blnQueueInsUpdFlg == false)
                {
                    //15.株式注文訂正確定インタセプタ( )(
                    WEB3EquityChangeConfirmInterceptor l_changeConfirmInterceptor =
                        new WEB3EquityChangeConfirmInterceptor();
           
                    //16. setThreadLocalPersistenceEventInterceptor(株式注文訂正確定インタセプタ : EqTypeOrderManagerPersistenceEventInterceptor)
                    l_orderManager.setThreadLocalPersistenceEventInterceptor(
                        l_changeConfirmInterceptor);
                    //17.getMarketResponseReceiverCallbackService( )
                    MarketAdapter l_marketAdapter =
                        l_finApp
                            .getTradingModule(ProductTypeEnum.EQUITY)
                            .getMarketAdapter();
                    EqTypeMarketResponseReceiverCallbackService l_marketResponseReciverCallbackService =
                        (EqTypeMarketResponseReceiverCallbackService)l_marketAdapter
                            .getMarketResponseReceiverCallbackService();
                    //18.DefaultChangeOrderAcceptedMarketResponseMessage
                    DefaultChangeOrderAcceptedMarketResponseMessage l_acceptedMarketResponseMessage =
                        new DefaultChangeOrderAcceptedMarketResponseMessage(
                            l_orderUnitRow.getOrderId());
                    //19.process
                    ProcessingResult l_processingResult = l_marketResponseReciverCallbackService.process(
                        l_acceptedMarketResponseMessage);
                
                    if (l_processingResult.isFailedResult())
                    {
                        return DefaultMarketRequestSendResult
                             .newFailedResultInstance(
                             l_processingResult);
                    }
                }
            }
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME,l_dfe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME,l_nfe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME,l_dne);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME,l_dqe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_wbe)
        {
            log.error("__an unexpected error__", l_wbe);
            ProcessingResult l_processingResult =
                ProcessingResult.newFailedResultInstance(l_wbe.getErrorInfo());
            return DefaultMarketRequestSendResult.newFailedResultInstance(
                l_processingResult);
        }
        log.exiting(STR_METHOD_NAME);

        return DefaultMarketRequestSendResult.newSuccessResultInstance(
                        l_lngMsgTokenId);

    }

    /**
     * (non-Javadoc)
     * @@see
     * com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeMarketRequestSenderService#send(com
     * .fitechlabs.xtrade.plugin.tc.eqtype.market.messages.EqTypeChangeSwapContractOrde
     * rMarketRequestMessage, boolean)
     * @@param arg0
     * @@param arg1
     * @@return MarketRequestSendResult
     * @@throws TooLateException
     * @@roseuid 40349AC3016F
     */
    public MarketRequestSendResult send(
        EqTypeChangeSwapContractOrderMarketRequestMessage arg0,
        boolean arg1)
        throws TooLateException
    {
        return null;
    }

    /**
     * (non-Javadoc)
     * @@see
     * com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSenderService#send(com.fit
     * echlabs.xtrade.plugin.tc.gentrade.MarketRequestMessage)
     * @@param arg0
     * @@return MarketRequestSendResult
     * @@roseuid 40349AC302B7
     */
    public MarketRequestSendResult send(MarketRequestMessage arg0)
    {
        return null;
    }
}
@
