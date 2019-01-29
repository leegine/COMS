head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderCarryOverUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文繰越一件サービスImpl(WEB3EquityOrderCarryOverUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/02 鄒政 (中訊) 新規作成
Revesion History : 2006/11/20 唐性峰　@(中訊)モデルNo.1029,No.1030,No.1070
Revesion History : 2007/06/04 何文敏 (中訊) 仕様変更モデルNo.1160
*/
package webbroker3.equity.service.delegate.stdimpls;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeClosingContractSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeContractOpenOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeContractSettleOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityEstimatedContractPrice;
import webbroker3.equity.WEB3EquityEstimatedDeliveryPrice;
import webbroker3.equity.WEB3EquityNewCashBasedOrderSpec;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityOrderManagerPersistenceEventInterceptor;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityRealizedProfitAndLossPrice;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3EquityTypeOrderManagerReusableValidations;
import webbroker3.equity.WEB3MarginCloseMarginUpdateInterceptor;
import webbroker3.equity.WEB3MarginNewOrderValidationResult;
import webbroker3.equity.WEB3MarginOpenContractOrderSpec;
import webbroker3.equity.WEB3MarginOpenMarginUpdateInterceptor;
import webbroker3.equity.WEB3MarginSettleContractOrderSpec;
import webbroker3.equity.service.delegate.WEB3EquityOrderCarryOverUnitService;
import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;

import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;

import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;

/**
 * （注文繰越一件サービスImpl）。
 * @@version 1.0
 */
public class WEB3EquityOrderCarryOverUnitServiceImpl
    implements WEB3EquityOrderCarryOverUnitService
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityOrderCarryOverUnitServiceImpl.class);

    /**
     * @@roseuid 40B2A3100101
     */
    public WEB3EquityOrderCarryOverUnitServiceImpl()
    {

    }

    /**
     * (insert繰越注文)<BR>
     * <BR>
     * 引数に指定された注文単位オブジェクトから、
     *  繰越の新規注文データ（現物株式 or 新規建 or 返済）を作成する。
     *  シーケンス図「（注文繰越）顧客単位繰越実行」の
     *  注文繰越一件サービスImpl.insert繰越注文(注文単位)参照
     * @@param l_orderUnit - 注文単位<BR>
     * 繰越対象の注文単位オブジェクト。<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 4137CF8A0219
     */
    public boolean insertCarryOverOrder(OrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "insertCarryOverOrder(OrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        boolean l_blnOk = true;
        
        //注文単位.注文カテゴリによりいずれかをコールする
        try
        {
            if (OrderCategEnum.ASSET.equals(l_orderUnit.getOrderCateg()))
            {
                insertEquityCarryOverOrder((EqTypeOrderUnit)l_orderUnit);
            }
            else if (OrderCategEnum.OPEN_MARGIN.equals(l_orderUnit.getOrderCateg()))
            {
                insertOpenMarginCarryOverOrder(
                    (EqTypeContractOpenOrderUnit)l_orderUnit);
            }
            else if (OrderCategEnum.CLOSE_MARGIN.equals(l_orderUnit.getOrderCateg()))
            {
                insertCloseMarginCarryOverOrder(
                    (EqTypeContractSettleOrderUnit)l_orderUnit);
            }
        }
        catch (WEB3BaseException l_exp)
        {
            log.debug(STR_METHOD_NAME, l_exp);
            log.debug("error order_unit_id = [" + l_orderUnit.getOrderUnitId() + "]");
            ErrorInfo l_errorInfo = l_exp.getErrorInfo();
            if (l_errorInfo.getErrorTag().startsWith("SYSTEM_ERROR"))
            {
                throw new WEB3SystemLayerException(
                    l_errorInfo,
                    l_exp.getErrorMethod(),
                    l_exp.getMessage(),
                    l_exp.getException());
            }
            
            // get注文エラー理由コード
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_traModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_traModule.getOrderManager();
            String l_strErrorReasonCode = l_orderManager.getErrorReasonCode(l_errorInfo.getErrorCode());
            log.debug("l_strErrorReasonCode = " + l_strErrorReasonCode);
            
            log.debug("update繰越元注文の実行");
            this.updateOriginalOrder(
                (EqTypeOrderUnit)l_orderUnit,
                l_strErrorReasonCode);
            
            l_blnOk = false;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_blnOk;
    }

    /**
     * (insert現物繰越注文)<BR>
     * 現物株式の売買注文の繰越処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（注文繰越一件サービス）現物繰越注文作成」参照。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 繰越対象の注文単位オブジェクト。
     * @@roseuid 4104BB210299
     */
    public void insertEquityCarryOverOrder(EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "insertEquityCarryOverOrder(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            TradingModule l_traModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_traModule.getOrderManager();
            
            EqtypeOrderUnitRow l_orderUnitRow =
                (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            
            //1.1. 注文単位.取引者ID≠nullの場合のみ
            WEB3GentradeTrader l_trader = null;
            if (!l_orderUnitRow.getTraderIdIsNull())
            {
                l_trader = (WEB3GentradeTrader)l_finObjectManager.getTrader(l_orderUnitRow.getTraderId());
            }
            
            //1.2. create注文内容()
            WEB3GentradeBranch l_branch =
                (WEB3GentradeBranch)l_accountManager.getBranch(l_orderUnitRow.getBranchId());
                    
            Market l_market =
                l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
                
            EqTypeProduct l_product =
                (EqTypeProduct)l_traModule.getProductManager().getProduct(
                    l_orderUnitRow.getProductId());
            EqtypeProductRow l_productRow =
                (EqtypeProductRow)l_product.getDataSourceObject();

            boolean l_isSellOrder;
            if (OrderTypeEnum.EQUITY_BUY.equals(l_orderUnitRow.getOrderType()))
            {
                l_isSellOrder = false;
            }
            else
            {
                l_isSellOrder = true;
            }
            
            OrderUnit l_firstOrderUnit = l_orderManager.getFirstOrderUnit(l_orderUnit);
            WEB3EquityNewCashBasedOrderSpec l_newCashBasedOrderSpec =
                WEB3EquityNewCashBasedOrderSpec.createOrderSpec(
                    l_branch.getInstitution().getInstitutionCode(),
                    l_trader,
                    l_market.getMarketCode(),
                    l_productRow.getProductCode(),
                    l_orderUnitRow.getQuantity() - l_orderUnitRow.getExecutedQuantity(),
                    l_orderUnitRow.getLimitPrice(),
                    l_orderUnitRow.getExecutionConditionType(),
                    l_orderUnitRow.getTaxType(),
                    l_orderUnitRow.getExpirationDate(),
                    l_isSellOrder,
                    l_orderUnitRow.getOrderChanel(),
                    l_orderUnitRow.getPriceConditionType(),
                    l_orderUnitRow.getOrderConditionType(),
                    l_orderUnitRow.getOrderCondOperator(),
                    l_orderUnitRow.getStopOrderPrice(),
                    l_orderUnitRow.getWLimitPrice(),
                    new Long(l_firstOrderUnit.getOrderUnitId()),
                    l_orderUnitRow.getWLimitExecCondType());
                    
            //1.3. set手数料商品コード()
            l_newCashBasedOrderSpec.setCommissionProductCode(
                WEB3CommisionProductCodeDef.LISTING_STOCK);
                
            //1.4. create手数料()
            WEB3GentradeCommission l_commission =
                l_newCashBasedOrderSpec.createCommission(
                    l_branch,
                    l_orderUnitRow.getSonarTradedCode());
                    
            //1.5. validate現物株式注文()
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                    l_orderUnitRow.getAccountId(),
                    l_orderUnitRow.getSubAccountId());
            EqTypeNewOrderValidationResult l_orderValidationResult = 
                l_orderManager.validateNewCashBasedOrder(
                l_subAccount,
                l_newCashBasedOrderSpec);
            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                throw new WEB3BaseException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //1.6. getTradedProduct( )
            WEB3EquityTradedProduct l_tradedProduct = null;
            if (l_orderUnit.getTradedProduct() != null)
            {
                l_tradedProduct =
                    (WEB3EquityTradedProduct)l_orderUnit.getTradedProduct();
            }

            //calc概算受渡代金()
            //引数は以下の通りに設定する。
            //手数料 : 作成した手数料オブジェクト
            //指値 : 株式注文内容.getLimitPrice()
            //（W指値）訂正指値 : 株式注文内容.get（W指値）訂正指値()
            //逆指値基準値 : 株式注文内容.get逆指値基準値()
            //執行条件 : 株式注文内容.get執行条件()
            //（W指値）執行条件 : 株式注文内容.get（W指値）執行条件()
            //値段条件 : 株式注文内容.get値段条件()
            //発注条件 : 株式注文内容.get発注条件()
            //確認時取得時価 : null
            //isストップ注文有効 : 拡張株式注文マネージャ.isストップ注文有効(注文単位)
            //補助口座 : 取得した補助口座
            //取引銘柄 : 注文単位.getTradedProduct()
            //株数 : 株式注文内容.getQuantity( )（未出来分の数量）
            //is売注文 : 株式注文内容.isSellOrder( )
            //約定数量 : 0
            //合計約定金額 : 0
            //isSkip金額チェック : false（スキップしない）
            WEB3EquityEstimatedDeliveryPrice l_deliveryPrice =
                l_orderManager.calcEstimateDeliveryAmount(
                    l_commission,
                    l_newCashBasedOrderSpec.getLimitPrice(),
                    l_newCashBasedOrderSpec.getWLimitPriceChange(),
                    l_newCashBasedOrderSpec.getStopLimitPriceBasePrice(),
                    l_newCashBasedOrderSpec.getExecConditionType(),
                    l_newCashBasedOrderSpec.getWlimitExecCondType(),
                    l_newCashBasedOrderSpec.getPriceConditionType(),
                    l_newCashBasedOrderSpec.getOrderCond(),
                    null,
                    l_orderManager.isStopOrderValid(l_orderUnit),
                    l_subAccount,
                    l_tradedProduct,
                    l_newCashBasedOrderSpec.getQuantity(),
                    l_newCashBasedOrderSpec.isSellOrder(),
                    0.0D,
                    0.0D,
                    false);

            // set注文単価()
            //概算受渡代金計算結果.get計算単価()の戻り値をセットする。
            l_newCashBasedOrderSpec.setOrderUnitPrice(l_deliveryPrice.getCalcUnitPrice());
            log.debug("注文単価：[" + l_deliveryPrice.getCalcUnitPrice() + "]");

            //1.10. set概算受渡代金()
            l_newCashBasedOrderSpec.setEstimateDeliveryAmount(
                l_deliveryPrice.getEstimateDeliveryAmount());
            log.debug("概算受渡代金：[" + l_deliveryPrice.getEstimateDeliveryAmount() + "]");
                
            //1.11. 株式注文インタセプタ()
            WEB3EquityOrderManagerPersistenceEventInterceptor l_interceptor =
                new WEB3EquityOrderManagerPersistenceEventInterceptor();
                
            //1.12. set株式注文内容(株式注文内容)
            l_interceptor.setEquityOrderSpec(l_newCashBasedOrderSpec);
            
            //1.13. set繰越元注文単位()
            l_interceptor.setCarryoverOrderUnit(l_orderUnit);
            
            //1.14. validate取引余力()
            WEB3TPTradingPowerService l_tradingpowerService =
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            Object[] l_orderSpecIntercepter =
            {
                l_interceptor
            };
            Object[] l_orderSpec =
            {
                l_newCashBasedOrderSpec
            };
            
            WEB3TPTradingPowerResult l_tpResult = null;
            l_tpResult = l_tradingpowerService.validateTradingPower(
                l_subAccount,
                l_orderSpecIntercepter,
                l_orderSpec,
                l_orderUnit.getOrderType(),
                false);

            // throw余力エラー詳細情報
            // 余力エラーの情報からエラーコードを決定し、throwする。
            l_orderManager.throwTpErrorInfo(l_tpResult, l_orderUnitRow.getOrderType());

            //1.15. setThreadLocalPersistenceEventInterceptor()
            l_orderManager.setThreadLocalPersistenceEventInterceptor(
                l_interceptor);
            
            //1.16. createNewOrderId( )
            long l_newOrderId = l_orderManager.createNewOrderId();
            
            //1.17. submitNewCashBasedOrder()
            MainAccount l_mainAccount = l_subAccount.getMainAccount();
            WEB3Crypt l_crypt = new WEB3Crypt();
            EqTypeOrderSubmissionResult l_orderSubmissionResult =
                l_orderManager.submitNewCashBasedOrder(
                    l_subAccount,
                    l_newCashBasedOrderSpec,
                    l_newOrderId,
                    l_crypt.decrypt(l_mainAccount.getTradingPassword()),
                    true);
                    
            if (l_orderSubmissionResult.getProcessingResult().isFailedResult())
            {
                ErrorInfo l_errorInfo = l_orderSubmissionResult.getProcessingResult().getErrorInfo();
                throw new WEB3BaseException(
                    l_errorInfo,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_errorInfo.getErrorMessage());
            }
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
    }

    /**
     * (insert新規建繰越注文)<BR>
     * 新規建注文の繰越処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（注文繰越一件サービス）新規建繰越注文作成」参照。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 繰越対象の注文単位オブジェクト（新規建）。
     * @@roseuid 4110BAA50045
     */
    public void insertOpenMarginCarryOverOrder(EqTypeContractOpenOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "insertOpenMarginCarryOverOrder(EqTypeContractOpenOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            TradingModule l_traModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_traModule.getOrderManager();
            
            EqtypeOrderUnitRow l_orderUnitRow =
                (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            
            //1.1. 注文単位.取引者ID≠nullの場合のみ
            WEB3GentradeTrader l_trader = null;
            if (!l_orderUnitRow.getTraderIdIsNull())
            {
                l_trader = (WEB3GentradeTrader)l_finObjectManager.getTrader(l_orderUnitRow.getTraderId());
            }
            
            //1.2. create新規建注文内容()
            Market l_market =
                l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
            EqTypeProduct l_product =
                (EqTypeProduct)l_traModule.getProductManager().getProduct(
                    l_orderUnitRow.getProductId());
                    
            EqtypeProductRow l_productRow =
                (EqtypeProductRow)l_product.getDataSourceObject();

            boolean l_isLong;
            if (SideEnum.BUY.equals(l_orderUnit.getSide()))
            {
                l_isLong = true;
            }
            else
            {
                l_isLong = false;
            }
            
            OrderUnit l_firstOrderUnit = l_orderManager.getFirstOrderUnit(l_orderUnit);
            WEB3MarginOpenContractOrderSpec l_marginOpenContractOrderSpec =
                WEB3MarginOpenContractOrderSpec.createOpenMarginOrderSpec(
                    l_trader,
                    l_isLong,
                    l_productRow.getProductCode(),
                    l_market.getMarketCode(),
                    (l_orderUnitRow.getQuantity()
                        - l_orderUnitRow.getExecutedQuantity()),
                    l_orderUnitRow.getLimitPrice(),
                    l_orderUnitRow.getExecutionConditionType(),
                    l_orderUnitRow.getExpirationDate(),
                    l_orderUnitRow.getTaxType(),
                    l_orderUnitRow.getPriceConditionType(),
                    l_orderUnitRow.getOrderConditionType(),
                    l_orderUnitRow.getOrderCondOperator(),
                    l_orderUnitRow.getStopOrderPrice(),
                    l_orderUnitRow.getWLimitPrice(),
                    l_orderUnitRow.getRepaymentType(),
                    l_orderUnitRow.getRepaymentNum(),
                    new Long(l_firstOrderUnit.getOrderUnitId()),
                    l_orderUnitRow.getWLimitExecCondType());
            
            //1.3. create手数料()
            WEB3EquityBizLogicProvider l_bizLogicProvider =
                (WEB3EquityBizLogicProvider)l_traModule.getBizLogicProvider();
            WEB3GentradeCommission l_commission = l_bizLogicProvider.createCommission(l_orderUnitRow.getOrderId());
            
            //1.6. get発注日()
            Date l_datBizDate =
                WEB3GentradeTradingTimeManagement.getOrderBizDate();
                
            //1.7. set発注日()
            l_commission.setOrderBizDate(new Timestamp(l_datBizDate.getTime()));
            
            //1.10. getSubAccount()
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                    l_orderUnitRow.getAccountId(),
                    l_orderUnitRow.getSubAccountId());

            //calc注文時建代金()
            //[calc注文時建代金( )：引数設定仕様]
            //手数料 : 作成した手数料オブジェクト
            //指値 : 新規建注文内容.getLimitPrice()
            //（W指値）訂正指値 : 新規建注文内容.get（W指値）訂正指値()
            //逆指値基準値 : 新規建注文内容.get逆指値基準値()
            //執行条件 : 新規建注文内容.get執行条件()
            //（W指値）執行条件 : 新規建注文内容.get（W指値）執行条件()
            //値段条件 : 新規建注文内容.get値段条件()
            //発注条件 : 新規建注文内容.get発注条件()
            //確認時取得時価 : null
            //isストップ注文有効 : 拡張株式注文マネージャ.isストップ注文有効(注文単位)
            //is売建 : 新規建注文内容.isShortOrder( )
            //補助口座 : 取得した補助口座オブジェクト
            //取引銘柄 : 取得した取引銘柄オブジェクト
            //株数 : 信用新規建注文内容.getQuantity( )
            //約定数量 : 0
            //合計約定金額 : 0
            //isSkip金額チェック : false（スキップしない）固定
            WEB3EquityEstimatedContractPrice l_contractAmountAtOrder =
                l_orderManager.calcContractAmountAtOrder(
                    l_commission,
                    l_marginOpenContractOrderSpec.getLimitPrice(),
                    l_marginOpenContractOrderSpec.getWLimitPrice(),
                    l_marginOpenContractOrderSpec.getStopOrderPrice(),
                    l_marginOpenContractOrderSpec.getExecConditionType(),
                    l_marginOpenContractOrderSpec.getWlimitExecCondType(),
                    l_marginOpenContractOrderSpec.getPriceConditionType(),
                    l_marginOpenContractOrderSpec.getOrderConditionType(),
                    null,
                    l_orderManager.isStopOrderValid(l_orderUnit),
                    l_marginOpenContractOrderSpec.isShortOrder(),
                    l_subAccount,
                    (WEB3EquityTradedProduct)l_orderUnit.getTradedProduct(),
                    l_marginOpenContractOrderSpec.getQuantity(),
                    0.0D,
                    0.0D,
                    false);
            log.debug("注文時建代金：[" + l_contractAmountAtOrder + "]");

            //set計算単価()
            //calc注文時建代金()の戻り値.get計算単価()の戻り値をセットする。
            l_marginOpenContractOrderSpec.setCalcUnitPrice(
                l_contractAmountAtOrder.getCalcUnitPrice());

            //1.15. set建代金()
            //引数は以下の通りに設定する。
            //建代金：　@calc注文時建代金( ).get概算建代金( )の戻り値をセット
            l_marginOpenContractOrderSpec.setContractAmount(
                l_contractAmountAtOrder.getEstimatedContractPrice());

            //1.16. validate新規建注文()
            WEB3MarginNewOrderValidationResult l_orderValidationResult = 
                (WEB3MarginNewOrderValidationResult)l_orderManager.validateOpenContractOrder(
                    l_subAccount,
                    l_marginOpenContractOrderSpec);
            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                throw new WEB3BaseException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);                        
            }
            
            //1.17. calc委託手数料()
            l_bizLogicProvider.calcCommission(l_commission, l_subAccount);
            
            //1.18. get空売り規制対象フラグ()
            boolean l_strShortSellOrderFlag = l_orderValidationResult.getShortSellingRestraint();
            
            //1.19. 信用新規建更新インタセプタ()
            WEB3MarginOpenMarginUpdateInterceptor l_openUpdateInterceptor =
                new WEB3MarginOpenMarginUpdateInterceptor(
                    l_marginOpenContractOrderSpec,
                    l_commission,
                    l_orderUnitRow.getOrderChanel(),
                    l_orderUnitRow.getOrderRootDiv(),
                    l_strShortSellOrderFlag);
                    
            //1.20. set繰越元注文単位()
            l_openUpdateInterceptor.setCarryoverOrderUnit(l_orderUnit);
            
            //1.21. validate取引余力()
            WEB3TPTradingPowerService l_tradingpowerService =
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            Object[] l_orderSpecIntercepter =
            {
                l_openUpdateInterceptor
            };
            Object[] l_orderSpec =
            {
                l_marginOpenContractOrderSpec
            };
            
            WEB3TPTradingPowerResult l_tpResult = null;
            l_tpResult = l_tradingpowerService.validateTradingPower(
                l_subAccount,
                l_orderSpecIntercepter,
                l_orderSpec,
                l_orderUnit.getOrderType(),
                false);

            // throw余力エラー詳細情報
            // 余力エラーの情報からエラーコードを決定し、throwする。
            l_orderManager.throwTpErrorInfo(l_tpResult, l_orderUnitRow.getOrderType());

            //1.22. setThreadLocalPersistenceEventInterceptor()
            l_orderManager.setThreadLocalPersistenceEventInterceptor(
                l_openUpdateInterceptor);
            
            //1.23. createNewOrderId( )
            long l_newOrderId = l_orderManager.createNewOrderId();
            
            //1.24. submitOpenContractOrder()
            MainAccount l_mainAccount = l_subAccount.getMainAccount();
            WEB3Crypt l_crypt = new WEB3Crypt();
            EqTypeOrderSubmissionResult l_orderSubmissionResult =
                l_orderManager.submitOpenContractOrder(
                    l_subAccount,
                    l_marginOpenContractOrderSpec,
                    l_newOrderId,
                    l_crypt.decrypt(l_mainAccount.getTradingPassword()),
                    true);
            if (l_orderSubmissionResult.getProcessingResult().isFailedResult())
            {
                ErrorInfo l_errorInfo = l_orderSubmissionResult.getProcessingResult().getErrorInfo();
                throw new WEB3BaseException(
                    l_errorInfo,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_errorInfo.getErrorMessage());
            }
        }
        catch (NotFoundException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (insert返済繰越注文)<BR>
     * 返済注文の繰越処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（注文繰越一件サービス）返済繰越注文作成」参照。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 繰越対象の注文単位オブジェクト（返済）。
     * @@roseuid 4105BE8C0290
     */
    public void insertCloseMarginCarryOverOrder(EqTypeContractSettleOrderUnit l_orderUnit)
        throws WEB3BaseException
    {

        final String STR_METHOD_NAME =
            "insertCloseMarginCarryOverOrder(EqTypeContractSettleOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            TradingModule l_traModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_traModule.getOrderManager();
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_traModule.getPositionManager();
            EqtypeOrderUnitRow l_orderUnitRow =
                (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            
            //1.1. getContractsToClose()
            EqTypeClosingContractSpec[] l_specs = l_orderUnit.getContractsToClose();
            
            //1.2. ArrayList()
            ArrayList l_list = new ArrayList();
            
            //1.3. getContractsToClose( )の戻り値（＝建株返済指定情報）要素数(index)分、Loop
            for (int i = 0; i < l_specs.length; i++)
            {
                //1.3.1. 建株残高チェック
                //（１）返済数量を算出する。
                EqtypeClosingContractSpecRow l_specRow =
                    (EqtypeClosingContractSpecRow)l_specs[i].getDataSourceObject();
                double l_returnQuantity =
                    l_specRow.getQuantity() - l_specRow.getExecutedQuantity();
                log.debug("返済数量：[" + l_returnQuantity + "]");
                if (l_returnQuantity == 0D)
                {
                    continue;
                }
                
                //（２）返済可能建株残高を算出する。
                WEB3EquityContract l_contract =
                    (WEB3EquityContract)l_positionManager.getContract(
                        l_specs[i].getContractId());
                double l_posiblePosition =
                    l_contract.getQuantity()
                        - l_contract.getLockedQuantity();
                log.debug("返済可能建株残高：[" + l_posiblePosition + "]");
                
                //（３）返済数量＞返済可能建株残高の場合、「建株残高不足エラー」の例外をthrowする。
                if (l_returnQuantity > l_posiblePosition)
                {
                    throw new WEB3BaseException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00808,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        WEB3EquityTypeOrderManagerReusableValidations.ORDER_ERROR_TAG
                        + WEB3ErrorReasonCodeDef.TRADE_STOP_PRODUCT_ERROR);
                }

                // validate決済期日超過(建株)
                // 建株の決済期日チェックを行う。
                WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResValidations =
                    (WEB3EquityTypeOrderManagerReusableValidations)
                        WEB3EquityTypeOrderManagerReusableValidations.getInstance();
                l_orderMgrResValidations.validateCloseDateExcess(l_contract);

                //1.3.2. EqTypeSettleContractOrderEntry()
                EqTypeSettleContractOrderEntry l_contractOrderEntry =
                    new EqTypeSettleContractOrderEntry(
                        l_specs[i].getContractId(),
                        l_returnQuantity);
                        
                //1.3.3. add()
                l_list.add(l_contractOrderEntry);
            }
            
            //1.4. toArray()            
            EqTypeSettleContractOrderEntry[] l_entry =
                new EqTypeSettleContractOrderEntry[l_list.size()];
            l_list.toArray(l_entry);
            
            //1.5. 注文単位.取引者ID≠nullの場合のみ
            WEB3GentradeTrader l_trader = null;
            if (!l_orderUnitRow.getTraderIdIsNull())
            {
                l_trader = (WEB3GentradeTrader)l_finObjectManager.getTrader(l_orderUnitRow.getTraderId());
            }
            
            //1.6. create返済注文内容()
            OrderUnit l_firstOrderUnit = l_orderManager.getFirstOrderUnit(l_orderUnit);
            WEB3MarginSettleContractOrderSpec l_settleContractOrderSpec =
                WEB3MarginSettleContractOrderSpec.createCloseMarginOrderSpec(
                    l_trader,
                    l_entry,
                    l_orderUnitRow.getLimitPrice(),
                    l_orderUnitRow.getExecutionConditionType(),
                    l_orderUnitRow.getExpirationDate(),
                    l_orderUnitRow.getTaxType(),
                    l_orderUnitRow.getPriceConditionType(),
                    l_orderUnitRow.getOrderConditionType(),
                    l_orderUnitRow.getOrderCondOperator(),
                    l_orderUnitRow.getStopOrderPrice(),
                    l_orderUnitRow.getWLimitPrice(),
                    l_orderUnitRow.getClosingOrderType(),
                    new Long(l_firstOrderUnit.getOrderUnitId()),
                    l_orderUnitRow.getWLimitExecCondType());
                    
            //1.7. getSubAccount()
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                    l_orderUnit.getAccountId(),
                    l_orderUnit.getSubAccountId());
                        
            //1.8. validate返済注文()
            EqTypeNewOrderValidationResult l_orderValidationResult = 
                l_orderManager.validateSettleContractOrder(
                    l_subAccount,
                    l_settleContractOrderSpec);
            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                throw new WEB3BaseException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            //1.9. create手数料()
            WEB3EquityBizLogicProvider l_bizLogicProvider =
                (WEB3EquityBizLogicProvider)l_traModule.getBizLogicProvider();
            WEB3GentradeCommission l_commission =
                l_bizLogicProvider.createCommission(
                    l_orderUnitRow.getOrderId());
                    
            //1.10. isLimitOrder()
            boolean l_isLimitOrder = l_settleContractOrderSpec.isLimitOrder();
            
            //1.11. setIs指値()
            l_commission.setIsLimitPrice(l_isLimitOrder);
            
            //1.12. get発注日()
            Date l_datBizDate =
                WEB3GentradeTradingTimeManagement.getOrderBizDate();
                
            //1.13. set発注日()
            l_commission.setOrderBizDate(new Timestamp(l_datBizDate.getTime()));
            
            //1.14. get建株()
            WEB3EquityContract l_equityContract =
                (WEB3EquityContract)l_positionManager.getContract(
                    l_entry[0].getContractId());
                    
            //1.15. getTradedProduct()
            WEB3EquityProductManager l_productManager =
                (WEB3EquityProductManager)l_traModule.getProductManager();
            WEB3EquityTradedProduct l_tradedProduct = null;
            EqtypeContractRow l_contractRow =
                (EqtypeContractRow)l_equityContract.getDataSourceObject();
            try
            {
                l_tradedProduct =
                    (WEB3EquityTradedProduct)l_productManager.getTradedProduct(
                        l_contractRow.getProductId(),
                        l_contractRow.getMarketId());
            }
            catch (Exception l_exp)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00638,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_exp.getMessage(),
                    l_exp);
            }
            
            //1.16. calc概算決済損益代金()
            WEB3EquityRealizedProfitAndLossPrice l_profitAndLossPrice =
                l_orderManager.calcEstimatedRealizedProfitAndLossAmount(
                    l_commission,
                    l_orderUnitRow.getLimitPrice(),
                    l_subAccount,
                    l_tradedProduct,
                    l_entry,
                    l_settleContractOrderSpec.getTotalQuantity(),
                    null,
                    0D,
                    0D,
                    false);
            log.debug("概算決済損益代金：[" + l_profitAndLossPrice + "]");
            
            //1.17. 信用返済更新インタセプタ()
            WEB3MarginCloseMarginUpdateInterceptor l_interceptor =
                new WEB3MarginCloseMarginUpdateInterceptor(
                    l_settleContractOrderSpec,
                    l_commission,
                    l_profitAndLossPrice,
                    l_contractRow.getRepaymentType(),
                    l_contractRow.getRepaymentNum(),
                    l_orderUnitRow.getOrderChanel(),
                    l_orderUnitRow.getOrderRootDiv());

            //1.18. set繰越元注文単位()
            l_interceptor.setCarryoverOrderUnit(l_orderUnit);
            
            //1.19. setThreadLocalPersistenceEventInterceptor()
            l_orderManager.setThreadLocalPersistenceEventInterceptor(
                l_interceptor);
            
            //1.20. createNewOrderId()
            long l_newOrderId = l_orderManager.createNewOrderId();
            
            //1.21. submitSettleContractOrder()
            MainAccount l_mainAccount = l_subAccount.getMainAccount();
            WEB3Crypt l_crypt = new WEB3Crypt();
            EqTypeOrderSubmissionResult l_orderSubmissionResult =
                l_orderManager.submitSettleContractOrder(
                    l_subAccount,
                    l_settleContractOrderSpec,
                    l_newOrderId,
                    l_crypt.decrypt(l_mainAccount.getTradingPassword()),
                    true);
            if (l_orderSubmissionResult.getProcessingResult().isFailedResult())
            {
                ErrorInfo l_errorInfo = l_orderSubmissionResult.getProcessingResult().getErrorInfo();
                throw new WEB3BaseException(
                        l_errorInfo,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_errorInfo.getErrorMessage());
            }
            
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
    }

    /**
     * (update繰越元注文)<BR>
     * 発注審査でエラーとなった繰越元注文の注文エラー理由コードなどを更新する。<BR>
     * <BR>
     * １）　@繰越元注文の注文エラー理由コード をupdateする。<BR>
     * <BR>
     * １－１）　@以下の条件に該当する繰越元注文の注文単位レコードをupdateする。<BR>
     * 　@　@<条件><BR>
     * 　@　@　@注文単位テーブル.注文単位ID = パラメータ.注文単位.注文単位ID<BR>
     * <BR>
     * 　@　@<更新内容><BR>
     * 　@　@　@注文単位レコード.注文エラー理由コード = <BR>
     * パラメータ.注文エラー理由コード<BR>
     * 　@　@　@注文単位レコード.更新日付 = 現在日時<BR>
     * <BR>
     * １－２）　@以下の条件に該当する繰越元注文の注文履歴の、<BR>
     * 　@　@　@　@　@最終履歴レコードの注文エラー理由コード をupdateする。<BR>
     * <BR>
     * 　@　@<条件><BR>
     * 　@　@履歴テーブル.注文単位ID　@＝　@<BR>
     * パラメータ.注文単位.注文単位ID　@かつ<BR>
     * 　@　@履歴テーブル.注文履歴番号　@＝　@<BR>
     * パラメータ.注文単位.注文履歴最終通番<BR>
     * <BR>
     * 　@　@<更新内容><BR>
     * 　@　@履歴レコード.注文エラー理由コード　@＝　@<BR>
     * パラメータ.注文エラー理由コード<BR>
     * 　@　@履歴レコード.更新日付　@＝　@現在日時<BR>
     * <BR>
     * １－３）　@以下の条件に該当する、<BR>
     * 繰越元注文の注文（ヘッダ）の更新日時をupdateする。<BR>
     * <BR>
     * 　@　@<条件><BR>
     * 　@　@注文（ヘッダ）テーブル.注文ID　@＝　@パラメータ.注文単位.注文ID<BR>
     * <BR>
     * 　@　@<更新内容><BR>
     * 　@　@注文（ヘッダ）レコード.更新日付　@＝　@現在日時<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 繰越元の注文単位
     * @@param l_strOrderErrReasonCode - (注文エラー理由コード)<BR>
     * 注文エラー理由
     * @@roseuid 4121D908036F
     */
    public void updateOriginalOrder(
        EqTypeOrderUnit l_orderUnit,
        String l_strOrderErrReasonCode) 
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "updateCarryOverOrder(OrderUnit, String)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            EqtypeOrderUnitRow l_orderUnitRow =
                EqtypeOrderUnitDao.findRowByOrderUnitId(
                    l_orderUnit.getOrderUnitId());
                    
            EqtypeOrderUnitParams l_orderUnitParams =
                new EqtypeOrderUnitParams(l_orderUnitRow);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //注文単位レコード.注文エラー理由コード =パラメータ.注文エラー理由コード<BR>
            //注文単位レコード.更新日付 = 現在日時
            l_orderUnitParams.setErrorReasonCode(l_strOrderErrReasonCode);            
            l_orderUnitParams.setLastUpdatedTimestamp(
                l_finApp.getTradingSystem().getSystemTimestamp());
            l_queryProcessor.doUpdateQuery(l_orderUnitParams);

            //履歴テーブル.注文単位ID　@＝　@パラメータ.注文単位.注文単位ID　@かつ
            //履歴テーブル.注文履歴番号　@＝　@パラメータ.注文単位.注文履歴最終通番

            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" order_unit_id = ? ");
            l_sbWhere.append(" and order_action_serial_no = ? ");

            Object[] l_objWhere =
            {
                new Long(l_orderUnitRow.getOrderUnitId()),
                new Integer(l_orderUnitRow.getLastOrderActionSerialNo())
            };
                    
            List l_lisResults =
                l_queryProcessor.doFindAllQuery(
                    EqtypeOrderActionRow.TYPE,
                    l_sbWhere.toString(),
                    null,
                    null,
                    l_objWhere);
                    
            EqtypeOrderActionParams l_orderActionParams =
                new EqtypeOrderActionParams((EqtypeOrderActionRow)l_lisResults.get(0));
            //履歴レコード.注文エラー理由コード　@＝　@パラメータ.注文エラー理由コード
            //履歴レコード.更新日付　@＝　@現在日時
            l_orderActionParams.setErrorReasonCode(l_strOrderErrReasonCode);
            l_orderActionParams.setLastUpdatedTimestamp(
                l_finApp.getTradingSystem().getSystemTimestamp());
            l_queryProcessor.doUpdateQuery(l_orderActionParams);
            
            //注文（ヘッダ）テーブル.注文ID　@＝　@パラメータ.注文単位.注文ID
            EqtypeOrderRow l_orderRow =
                EqtypeOrderDao.findRowByPk(l_orderUnitRow.getOrderId());
                
            //注文（ヘッダ）レコード.更新日付　@＝　@現在日時
            EqtypeOrderParams l_orderParams = new EqtypeOrderParams(l_orderRow);
            l_orderParams.setLastUpdatedTimestamp(
                l_finApp.getTradingSystem().getSystemTimestamp());
                
            l_queryProcessor.doUpdateQuery(l_orderParams);
        }
        catch (DataException l_de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }
    }
}
@
