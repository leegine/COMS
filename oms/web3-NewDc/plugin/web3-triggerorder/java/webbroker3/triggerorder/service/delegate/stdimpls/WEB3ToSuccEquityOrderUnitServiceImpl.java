head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.51.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEquityOrderUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連続注文株式発注一件サービスImpl(WEB3ToSuccEquityOrderUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/17 凌建平(中訊) 新規作成
Revesion History : 2008/05/05 安陽(中訊) 仕様変更モデルNo.314
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Contract;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityEstimatedDeliveryPrice;
import webbroker3.equity.WEB3EquityNewCashBasedOrderSpec;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityOrderManagerPersistenceEventInterceptor;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityRealizedProfitAndLossPrice;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3MarginCloseMarginUpdateInterceptor;
import webbroker3.equity.WEB3MarginNewOrderValidationResult;
import webbroker3.equity.WEB3MarginOpenContractOrderSpec;
import webbroker3.equity.WEB3MarginOpenMarginUpdateInterceptor;
import webbroker3.equity.WEB3MarginSettleContractOrderSpec;
import webbroker3.equity.WEB3MarginSwapContractOrderSpec;
import webbroker3.equity.WEB3MarginSwapMarginUpdateInterceptor;
import webbroker3.gentrade.WEB3GentradeBranchMarketRepayDealtCond;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondRow;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvEqClosingContractSpecRow;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationEqTypeOrderUpdateService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityOrderUnitService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (連続注文株式発注一件サービスImpl)<BR>
 * 連続注文株式発注一件サービス実装クラス。<BR>
 * <BR>
 * @@author 凌建平 <BR>
 * @@version 1.0<BR>
 */
public class WEB3ToSuccEquityOrderUnitServiceImpl implements WEB3ToSuccEquityOrderUnitService 
{
    
    /**
     * ログオブジェクト
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
    	WEB3ToSuccEquityOrderUnitServiceImpl.class);

    /**
     * @@roseuid 4349DEE70222
     */
    public WEB3ToSuccEquityOrderUnitServiceImpl() 
    {
     
    }
    
    /**
     * (submit現物株式注文)<BR>
     * 現物株式注文を発注する。<BR>
     * シーケンス図「（連続注文発注一件サービス）submit現物株式注文」を参照。<BR>
     * @@param l_rsvEqOrderUnit - (株式予約注文単位)<BR>
     * 株式予約注文単位オブジェクト。<BR>
     * @@exception WEB3BaseException<BR>
     * @@roseuid 432175DD01A0
     */
    public void submitEquityOrder(WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitEquityOrder(WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        //株式予約注文単位
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow = null;

        //エラーコード
        String l_strErrorCode = null;

        //株式予約注文更新サービス
        WEB3ToSuccReservationEqTypeOrderUpdateService l_orderUpdateService =
            (WEB3ToSuccReservationEqTypeOrderUpdateService)Services.getService(
                WEB3ToSuccReservationEqTypeOrderUpdateService.class);

        try
		{
        	//get発注日(確認時発注日 : Date)
            //引数は以下の通りにセットする。 
            //  確認時発注日：　@引数の株式予約注文単位.発注日
        	l_rsvEqOrderUnitRow = (RsvEqOrderUnitRow)l_rsvEqOrderUnit.getDataSourceObject();
        	WEB3GentradeTradingTimeManagement.getOrderBizDate(
    			WEB3DateUtility.getDate(l_rsvEqOrderUnitRow.getBizDate(), "yyyyMMdd"));
        	
        	//分岐フロー：　@予約注文単位.取引者ID≠nullの場合のみ
        	WEB3GentradeTrader l_trader = null;
        	if (!l_rsvEqOrderUnitRow.getTraderIdIsNull())
        	{
            	//getTrader(取引者ID : long)
                //引数は以下の通りにセットする。 
                //  取引者ID：　@予約注文単位.取引者ID
                FinObjectManager l_finMng = GtlUtils.getFinObjectManager();
                l_trader = (WEB3GentradeTrader)l_finMng.getTrader(l_rsvEqOrderUnitRow.getTraderId());
        	}

        	//get予約注文執行単価( )(株式予約注文単位Impl::get予約注文執行単価)
            double l_dblRsvOrderExecPrice = l_rsvEqOrderUnit.getRsvOrderExecPrice();
        	
            //create注文内容(証券会社コード : String, 扱者 : 扱者, 市場コード : String, 銘柄コード : String, 
            //		株数 : double, 指値 : double, 執行条件 : EqTypeExecutionConditionType, 税区分 : TaxTypeEnum, 
            //		注文失効日 : Timestamp, is売注文 : boolean, 注文チャネル : String, 値段条件 : String, 
            //		発注条件 : String, 発注条件演算子 : String, 逆指値基準値 : double, （W指値）訂正指値 : double, 
            //		初回注文の注文単位ID : Long)
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class); 
            //部店を取得
            Branch l_branch = l_finApp.getAccountManager().getBranch(l_rsvEqOrderUnit.getBranchId());
            //市場を取得
            Market l_market = l_finApp.getFinObjectManager().getMarket(l_rsvEqOrderUnitRow.getMarketId());
            //銘柄を取得
            TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityProductManager l_productManager = (WEB3EquityProductManager)l_tradingModule.getProductManager();
            Product l_product = l_productManager.getProduct(l_rsvEqOrderUnitRow.getProductId());
            EqtypeProductRow l_productRow = (EqtypeProductRow)l_product.getDataSourceObject();

            Long l_lngFirstOrderUnitId = null;
            if (!l_rsvEqOrderUnitRow.getFirstOrderUnitIdIsNull())
            {
                l_lngFirstOrderUnitId = new Long(l_rsvEqOrderUnitRow.getFirstOrderUnitId());
            }
            else
            {
                l_lngFirstOrderUnitId = null;
            }

            WEB3EquityNewCashBasedOrderSpec l_orderSpec =
                WEB3EquityNewCashBasedOrderSpec.createOrderSpec(
            		l_branch.getInstitution().getInstitutionCode(),
            		l_trader,
            		l_market.getMarketCode(),
                    l_productRow.getProductCode(),
            		l_rsvEqOrderUnit.getQuantity(),
            		l_dblRsvOrderExecPrice,
            		EqTypeExecutionConditionType.NONE,
    				l_rsvEqOrderUnit.getTaxType(),
    				l_rsvEqOrderUnit.getExpirationTimestamp(),
    				l_rsvEqOrderUnit.isSellOrder(),
    				l_rsvEqOrderUnitRow.getOrderChanel(),
                    WEB3PriceConditionDef.DEFAULT,
    				WEB3OrderingConditionDef.DEFAULT,
                    null,
                    0,
                    0,
                    l_lngFirstOrderUnitId);

            //set手数料商品コード(手数料商品コード : String)
            //引数は以下の通りに設定する。  
            //  手数料商品コード.上場株式（固定でセット） 
            l_orderSpec.setCommissionProductCode(WEB3CommisionProductCodeDef.LISTING_STOCK);
            
            //create手数料(部店 : 部店, 取引コード（SONAR） : String)
            //引数は以下の通りに設定する。  
            //  部店：　@予約注文単位.部店ID の部店オブジェクト  
            //  取引コード（SONAR）：　@"普通株式" 
            WEB3GentradeCommission l_commission = l_orderSpec.createCommission(
                l_branch, 
                WEB3TransactionTypeSONARDef.MARKET_TRADING);
            
            //validate現物株式注文(補助口座 : SubAccount, 株式注文内容 : EqTypeNewCashBasedOrderSpec)
            //  引数は以下の通りにセットする。 
            //      補助口座：　@引数の予約注文単位.口座ID、補助口座IDに該当する補助口座オブジェクト  
            //      株式注文内容：　@create注文内容( )で作成した株式注文内容オブジェクト 
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            SubAccount l_subAccount = l_finApp.getAccountManager().getSubAccount(
                l_rsvEqOrderUnit.getAccountId(),
                l_rsvEqOrderUnit.getSubAccountId());
            EqTypeNewOrderValidationResult l_orderValidationResult =
                l_orderManager.validateNewCashBasedOrder(
                    l_subAccount, 
                    l_orderSpec);

            //{validate現物株式注文()}が失敗の場合。
            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("{validate現物株式注文()}が失敗の場合。");
                
                throw new WEB3BaseException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //get取引銘柄( )(株式予約注文単位Impl::get取引銘柄)
            TradedProduct l_tradedProduct = l_rsvEqOrderUnit.getTradedProduct();
            
            //is成行注文( )(株式予約注文単位Impl::is成行注文)
            boolean l_blnIsMarketOrder = l_rsvEqOrderUnit.isMarketOrder();
            
            //分岐フロー：　@成行注文の場合
            double l_dblCalcCurrentPrice = 0;
            if (l_blnIsMarketOrder)
            {
                //calc時価(手数料商品コード : String, 取引銘柄 : 取引銘柄, 補助口座 : 補助口座, isSTOP高考慮 : boolean)
                //引数は以下の通りにセットする。 
                //  手数料商品コード：　@手数料.get手数料商品コード( )  
                //  取引銘柄：　@予約注文単位.get取引銘柄()  
                //  補助口座：　@予約注文単位.口座ID、補助口座IDに該当する補助口座オブジェクト  
                //  isSTOP高考慮：　@株式注文内容.isBuyOrder( )==true（買い注文）の場合、true。  
                //　@　@　@　@　@            以外、false。
                boolean l_blnIsStop = false;
                if (l_orderSpec.isBuyOrder())
                {
                    l_blnIsStop = true;
                }

                l_dblCalcCurrentPrice = l_orderManager.calcCurrentPrice(
                    l_commission.getCommissionProductCode(),
                    (WEB3EquityTradedProduct)l_tradedProduct,
                    (WEB3GentradeSubAccount)l_subAccount,
                    l_blnIsStop);
            }

            //set注文単価(注文単価 : double)
            //引数は以下の通りにセットする。 
            //成行注文の場合 
            //  注文単価：　@EQTYPEの拡張株式注文マネージャ.calc時価()の戻り値 
            //指値注文の場合 
            //  注文単価：　@株式予約注文単位.get予約注文執行単価()の戻り値
            if (l_blnIsMarketOrder)
            {
                l_orderSpec.setOrderUnitPrice(l_dblCalcCurrentPrice);
            }
            else
            {
                l_orderSpec.setOrderUnitPrice(l_dblRsvOrderExecPrice);
            }

            //calc概算受渡代金(手数料 : 手数料, 計算単価 : double, 補助口座 : SubAccount, 
            //  取引銘柄 : 取引銘柄, 株数 : double, is売注文 : boolean, 約定数量 : double, 
            //  合計約定金額 : double, isSkip金額チェック : boolean, is拘束考慮 : boolean)
            //引数は以下の通りにセットする。 
            //  手数料：　@株式注文内容.get手数料( )  
            //  計算単価：　@株式注文内容.get注文単価( )  
            //  補助口座：　@引数の予約注文単位.口座ID、補助口座IDに該当する補助口座オブジェクト  
            //  取引銘柄：　@取得した取引銘柄オブジェクト  
            //  株数：　@株式注文内容.getQuantity( ) 
            //  is売注文：　@株式注文内容.isSellOrder( )  
            //  約定数量：　@0  
            //  合計約定金額：　@0  
            //  isSkip金額チェック：　@false（スキップしない）  
            //  is拘束考慮：　@true（考慮する）
            WEB3EquityEstimatedDeliveryPrice l_estimateDeliveryAmount = l_orderManager.calcEstimateDeliveryAmount(
                l_orderSpec.getCommission(),
                l_orderSpec.getOrderUnitPrice(),
                l_subAccount,
                (WEB3EquityTradedProduct)l_tradedProduct,
                l_orderSpec.getQuantity(),
                l_orderSpec.isSellOrder(),
                0,
                0,
                false,
                true);

            //set概算受渡代金(概算金額 : double)
            //引数は以下の通りに設定する。  
            //概算金額：　@calc概算受渡代金戻り値オブジェクト.get概算受渡代金( )
            l_orderSpec.setEstimateDeliveryAmount(l_estimateDeliveryAmount.getEstimateDeliveryAmount());

            //株式注文インタセプタ()
            WEB3EquityOrderManagerPersistenceEventInterceptor l_interceptor = 
                new WEB3EquityOrderManagerPersistenceEventInterceptor();

            //set株式注文内容(株式注文内容 : 株式注文内容)
            //引数は以下の通りにセットする。 
            //  株式注文内容：　@生成した株式注文内容オブジェクト
            l_interceptor.setEquityOrderSpec(l_orderSpec);

            //set注文経路区分(注文経路区分 : String)
            //引数は以下の通りにセットする。 
            //  注文経路区分：　@引数の予約注文単位の同項目 
            l_interceptor.setOrderRootDiv(l_rsvEqOrderUnitRow.getOrderRootDiv());
            
            //set受注日時(受注日時 : Date)
            //引数は以下の通りにセットする。 
            //  受注日時：　@引数の予約注文単位の同項目
            l_interceptor.setReceivedDateTime(l_rsvEqOrderUnitRow.getReceivedDateTime());

            //validate取引余力(補助口座 : 補助口座, 注文内容インタセプタ : Object[], 注文内容 : Object[], 
            //  注文種別 : OrderTypeEnum, 余力更新フラグ : boolean)
            //引数は以下の通りに設定する。  
            //  補助口座：　@引数の予約注文単位.口座ID、補助口座IDに該当する補助口座オブジェクト  
            //  注文内容インタセプタ：　@生成した株式注文インタセプタ  
            //  注文内容：　@発注審査に使用した株式注文内容オブジェクト  
            //  注文種別：　@予約注文単位.get注文種別()
            //  余力更新フラグ：　@true（完了時）
            WEB3TPTradingPowerService l_tradingpowerService =
                (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            WEB3TPTradingPowerResult l_tradingPowerResult = l_tradingpowerService.validateTradingPower(
                (WEB3GentradeSubAccount)l_subAccount,
                new Object[]{l_interceptor},
                new Object[]{l_orderSpec},
                l_rsvEqOrderUnit.getOrderType(),
                true);

            if (!l_tradingPowerResult.isResultFlg())
            {
                log.debug("取引余力チェックエラー。");
                l_orderManager.throwTpErrorInfo(l_tradingPowerResult, l_rsvEqOrderUnit.getOrderType());
            }

            //setThreadLocalPersistenceEventInterceptor(株式注文インタセプタ : EqTypeOrderManagerPersistenceEventInterceptor)
            l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);

            //submitNewCashBasedOrder(arg0 : SubAccount, arg1 : EqTypeNewCashBasedOrderSpec, arg2 : long, 
            //  arg3 : 論理ビュー::java::lang::String, arg4 : boolean)
            MainAccount l_mainAccount = l_subAccount.getMainAccount();
            WEB3Crypt l_crypt = new WEB3Crypt();
            EqTypeOrderSubmissionResult l_submissionResult = l_orderManager.submitNewCashBasedOrder(
                l_subAccount,
                l_orderSpec,
                l_rsvEqOrderUnitRow.getOrderId(),
                l_crypt.decrypt(l_mainAccount.getTradingPassword()),
                true);

            //実行結果に応じ、予約注文単位をupdateする。
            if (l_submissionResult.getProcessingResult().isSuccessfulResult())
            {
                //1.21.1 set発注済To予約注文単位(ProductTypeEnum, long)(連続注文マネージャImpl::set発注済To予約注文単位)
                //引数は以下の通りにセットする。 
                //  銘柄タイプ：　@予約注文単位.銘柄タイプをセット
                //  注文ID：　@引数の予約注文単位.注文ID
                WEB3ToSuccOrderManagerImpl l_orderManagerImpl = 
                    (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
                l_orderManagerImpl.setOrderedToOrderUnit(
                    l_rsvEqOrderUnitRow.getProductType(),
                    l_rsvEqOrderUnitRow.getOrderId());
            }
            //実行結果エラー。
            else
            {
                log.debug("{submitNewCashBasedOrder()}がエラー。");

                throw new WEB3BaseException(
                    l_submissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
		}
		catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            if (l_ex instanceof WEB3BaseException)
            {
                l_strErrorCode = ((WEB3BaseException)l_ex).getErrorInfo().getErrorCode();
            }
            else if (l_ex instanceof WEB3BaseRuntimeException)
            {
                l_strErrorCode = ((WEB3BaseRuntimeException)l_ex).getErrorInfo().getErrorCode();
            }
            else if (l_ex instanceof NotFoundException)
            {
                l_strErrorCode = WEB3ErrorCatalog.SYSTEM_ERROR_80005.getErrorCode();
            }
            else
            {
                l_strErrorCode = WEB3ErrorCatalog.SYSTEM_ERROR_80002.getErrorCode();
            }

            //invalidate予約注文単位(株式予約注文単位行 : 株式予約注文単位Row, 発注エラーコード : String)
            //引数は以下の通りにセットする。 
            //株式予約注文単位行：　@引数の予約注文単位の行オブジェクト 
            //発生エラーコード：　@発生した例外オブジェクトのErrorInfo.error_code  
            //　@　@　@　@　@　@            （エラーの原因の特定が可能なBusinessError／SystemErrorのエラーコード）
            l_orderUpdateService.invalidateOrderUnit(
                l_rsvEqOrderUnitRow,
                l_strErrorCode);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (submit信用新規建注文)<BR>
     * 信用新規建注文を発注する。<BR>
     * シーケンス図「（連続注文株式発注一件サービス）<BR>
     * submit信用新規建注文」を参照。<BR>
     * <BR>
     * @@param l_rsvEqOrderUnit - (株式予約注文単位)<BR>
     * 株式予約注文単位オブジェクト。<BR>
     * @@exception WEB3BaseException<BR>
     * @@roseuid 433298A400C5
     */
    public void submitMarginOpenContractOrder(WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitMarginOpenContractOrder(WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        //株式予約注文単位
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow = null;

        //エラーコード
        String l_strErrorCode = null;

        //株式予約注文更新サービス
        WEB3ToSuccReservationEqTypeOrderUpdateService l_orderUpdateService =
            (WEB3ToSuccReservationEqTypeOrderUpdateService)Services.getService(
                WEB3ToSuccReservationEqTypeOrderUpdateService.class);

        try
        {
            //get発注日(確認時発注日 : Date)
            //引数は以下の通りにセットする。
            //確認時発注日：　@引数の株式予約注文単位.発注日
            l_rsvEqOrderUnitRow = (RsvEqOrderUnitRow)l_rsvEqOrderUnit.getDataSourceObject();
            Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(
                WEB3DateUtility.getDate(l_rsvEqOrderUnitRow.getBizDate(), "yyyyMMdd"));

            //分岐フロー：　@予約注文単位.取引者ID≠nullの場合のみ
            WEB3GentradeTrader l_trader = null;
            if (!l_rsvEqOrderUnitRow.getTraderIdIsNull())
            {
                //getTrader(取引者ID : long)
                //引数は以下の通りにセットする
                //  取引者ID：　@予約注文単位.取引者
                FinObjectManager l_finMng = GtlUtils.getFinObjectManager();
                l_trader = (WEB3GentradeTrader)l_finMng.getTrader(l_rsvEqOrderUnit.getTraderId());
            }

            //is買注文()
            boolean l_blnIsBuyOrder = l_rsvEqOrderUnit.isBuyOrder();
            
            //get銘柄( )(株式予約注文単位Impl::get銘柄)
            //アイテムの定義
            //  株式銘柄オブジェクトを取得する。
            Product l_product = l_rsvEqOrderUnit.getProduct();

            //get市場( )(株式予約注文単位Impl::get市場)
            //アイテムの定義
            //  市場オブジェクトを取得する。
            WEB3GentradeMarket l_market = l_rsvEqOrderUnit.getMarket();

            //get予約注文執行単価( )(株式予約注文単位Impl::get予約注文執行単価)
            //アイテムの定義
            //  予約注文の執行単価を取得する。
            double l_dblRsvOrderExecPrice = l_rsvEqOrderUnit.getRsvOrderExecPrice();

            //[create新規建注文内容( )：引数設定仕様]
            //扱者：　@予約注文単位.取引者ID==nullの場合は、null
            //        予約注文単位.取引者ID≠nullの場合は、取引者IDに該当する扱者オブジェクト
            //is買建：　@予約注文単位.is買注文()の戻り値
            //銘柄コード：　@予約注文単位.get銘柄().銘柄コード
            //市場コード：　@予約注文単位.get市場().市場コード
            //数量：　@予約注文単位.注文数量
            //指値：　@予約注文単位.get予約注文執行単価()の戻り値
            //執行条件：　@"条件なし"
            //注文失効日：　@予約注文単位.注文失効日
            //税区分：　@予約注文単位.税区分
            //値段条件：　@"条件なし"
            //発注条件：　@"条件なし"
            //発注条件演算子：　@null
            //逆指値基準値：　@0
            //（W指値）訂正指値：　@0
            //弁済区分：　@予約注文単位.弁済区分
            //弁済期限値：　@予約注文単位.弁済期限値
            //初回注文の注文単位ID：　@予約注文単位.初回注文の注文単位ID
            EqtypeProductRow l_productRow = (EqtypeProductRow)l_product.getDataSourceObject();
            //弁済区分
            String l_strRepaymentType = l_rsvEqOrderUnitRow.getRepaymentType();
            //弁済期限値
            int l_intRepaymentNum = l_rsvEqOrderUnitRow.getRepaymentNum();
            //新規建注文内容
            Long firstOrderUnitId = null;
            if (!l_rsvEqOrderUnitRow.getFirstOrderUnitIdIsNull())
            {
                firstOrderUnitId = new Long(l_rsvEqOrderUnitRow.getFirstOrderUnitId());
            }
            WEB3MarginOpenContractOrderSpec l_openContractOrderSpec = 
                WEB3MarginOpenContractOrderSpec.createOpenMarginOrderSpec(
                    l_trader, 
                    l_blnIsBuyOrder, 
                    l_productRow.getProductCode(), 
                    l_market.getMarketCode(), 
                    l_rsvEqOrderUnit.getQuantity(), 
                    l_dblRsvOrderExecPrice,
                    EqTypeExecutionConditionType.NONE,
                    l_rsvEqOrderUnitRow.getExpirationDate(), 
                    l_rsvEqOrderUnit.getTaxType(), 
                    WEB3PriceConditionDef.DEFAULT,
                    WEB3OrderingConditionDef.DEFAULT,
                    null, 
                    0, 
                    0, 
                    l_strRepaymentType, 
                    l_intRepaymentNum, 
                    firstOrderUnitId);

            //set発注日(発注日 : Date)
            //引数は以下の通りにセットする。 
            //  発注日：　@取引時間管理.get発注日(Date)の戻り値
            l_openContractOrderSpec.setBizDate(l_datOrderBizDate);

            //create手数料(補助口座 : 補助口座, 市場コード : String, 発注日 : Date, 
            //  注文チャネル : String, 信用取引区分 : String, 弁済期限値 : double, 注文カテゴリ : OrderCategEnum)
            //引数は以下の通りに設定する。  
            //  補助口座：　@予約注文単位の口座ID、補助口座IDに該当する補助口座オブジェクト 
            //  市場コード：　@予約注文単位.get市場().市場コード  
            //  発注日：　@予約注文単位.発注日 
            //  注文チャネル：　@予約注文単位.初回注文の注文チャネル 
            //  信用取引区分：　@予約注文単位.弁済区分  
            //  弁済期限値：　@予約注文単位.弁済期限値 
            //  注文カテゴリ：　@OrderCategEnum.”新規建注文”（OPEN_MARGIN）
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class); 
            WEB3EquityBizLogicProvider l_bizLogicProvider =
                (WEB3EquityBizLogicProvider)l_finApp.getTradingModule(
                    ProductTypeEnum.EQUITY).getBizLogicProvider();
                    
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            //補助口座：　@予約注文単位の口座ID、補助口座IDに該当する補助口座オブジェクト
            WEB3GentradeSubAccount l_subAccount = 
                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                    l_rsvEqOrderUnit.getAccountId(),
                    l_rsvEqOrderUnit.getSubAccountId());
            //手数料
            WEB3GentradeCommission l_commission = l_bizLogicProvider.createCommission(
                l_subAccount, 
                l_market.getMarketCode(), 
                l_datOrderBizDate, 
                l_rsvEqOrderUnitRow.getOrderChanel(), 
                l_strRepaymentType, 
                l_intRepaymentNum, 
                OrderCategEnum.OPEN_MARGIN);
                
            //isLimitOrder()
            boolean l_blnIsLimitOrder = l_openContractOrderSpec.isLimitOrder();
            
            //setIs指値(is指値 : boolean)
            //引数は以下の通りにセットする。 
            //is指値：　@信用新規建注文内容.isLimitOrder()の戻り値
            l_commission.setIsLimitPrice(l_blnIsLimitOrder);
            
            //isMarketOrder()
            boolean l_blnIsMarketOrder = l_openContractOrderSpec.isMarketOrder();
            
            //get取引銘柄()(株式予約注文単位Impl::get取引銘柄)
            WEB3EquityTradedProduct l_tradedProduct = (WEB3EquityTradedProduct)l_rsvEqOrderUnit.getTradedProduct();
            
            //get手数料商品コード()
            String l_strCommissionProductCode = l_commission.getCommissionProductCode();
            
            //[calc新規建計算単価( )：引数設定仕様]
            //  is新規建：　@true固定
            //  is買建：　@予約注文単位.is買注文()の戻り値
            //  is成行：　@信用新規建注文内容.isMarketOrder()の戻り値
            //  指値：　@予約注文単位.get予約注文執行単価()の戻り値
            //  補助口座：　@予約注文単位.口座ID、補助口座IDに該当する補助口座オブジェクト
            //  取引銘柄：　@予約注文単位.get取引銘柄()
            //  手数料商品コード：　@手数料.get手数料商品コード()
            double l_dblOpenMarginCalcUnitPrice =
                l_orderManager.calcOpenMarginCalcUnitPrice(
                    true,
                    l_blnIsBuyOrder,
                    l_blnIsMarketOrder,
                    l_dblRsvOrderExecPrice,
                    l_subAccount,
                    l_tradedProduct,
                    l_strCommissionProductCode);

            //set計算単価(計算単価 : double)
            //引数は以下の通りにセットする。 
            //計算単価：　@拡張株式注文マネージャ.calc新規建計算単価()の戻り値
            l_openContractOrderSpec.setCalcUnitPrice(l_dblOpenMarginCalcUnitPrice);

            //[calc注文時建代金( )：引数設定仕様]
            //  手数料：　@作成した手数料オブジェクト
            //  計算単価：　@信用新規建注文内容.get計算単価()
            //  補助口座：　@取得した補助口座オブジェクト
            //  取引銘柄：　@取得した取引銘柄オブジェクト
            //  株数：　@　@信用新規建注文内容.getQuantity()
            //  約定数量：　@0
            //  合計約定金額：　@0
            //  isSkip金額チェック：　@false（スキップしない）固定
            double l_dblContractAmountAtOrder = l_orderManager.calcContractAmountAtOrder(
                l_commission,
                l_openContractOrderSpec.getCalcUnitPrice(),
                l_subAccount,
                l_tradedProduct,
                l_openContractOrderSpec.getQuantity(),
                0,
                0,
                false);

            //set建代金(建代金 : double)
            //引数は以下の通りにセットする。 
            //  建代金：　@拡張株式注文マネージャ.calc注文時建代金()の戻り値
            l_openContractOrderSpec.setContractAmount(l_dblContractAmountAtOrder);
            
            //validate新規建注文(補助口座 : SubAccount, 信用新規建注文内容 : EqTypeOpenContractOrderSpec)
            //引数は以下の通りに設定する。  
            //  補助口座：　@予約注文単位.口座ID、補助口座IDに該当する補助口座オブジェクト 
            //  信用新規建注文内容：　@作成した信用新規建注文内容オブジェクト
            WEB3MarginNewOrderValidationResult l_orderValidationResult =
                (WEB3MarginNewOrderValidationResult)l_orderManager.validateOpenContractOrder(
                    l_subAccount,
                    l_openContractOrderSpec);

            //{validate新規建注文()}が失敗の場合。
            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("{validate新規建注文()}が失敗の場合。");
                
                throw new WEB3BaseException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //calc委託手数料(手数料 : 手数料, 補助口座 : SubAccount)
            //引数は以下の通りにセットする。 
            //  手数料：　@作成した手数料オブジェクト 
            //  補助口座：　@予約注文単位.口座ID、補助口座IDに該当する補助口座オブジェクト
            l_bizLogicProvider.calcCommission(
                l_commission,
                l_subAccount);
                
            //get空売り規制対象フラグ()
            boolean l_blnShortSellingRestraint = l_orderValidationResult.getShortSellingRestraint();

            //信用新規建更新インタセプタ(信用新規建注文内容 : 信用新規建注文内容, 手数料 : 手数料, 
            //  初回注文の注文チャネル : String, 注文経路区分 : String, 空売り規制対象フラグ : boolean)
            //引数は以下の通りに設定する。  
            //  信用新規建注文内容：　@作成した信用新規建注文内容オブジェクト  
            //  手数料：　@作成した手数料オブジェクト  
            //  初回注文の注文チャネル：　@予約注文単位.初回注文の注文チャネル 
            //  注文経路区分：　@予約注文単位.注文経路区分  
            //  空売り規制対象フラグ：　@validate新規建注文( )の戻り値の  
            //   信用新規建新規注文発注審査結果オブジェクト.get空売り規制対象フラグ( )
            WEB3MarginOpenMarginUpdateInterceptor l_openMarginUpdateInterceptor =
                new WEB3MarginOpenMarginUpdateInterceptor(
                    l_openContractOrderSpec,
                    l_commission,
                    l_rsvEqOrderUnitRow.getOrderChanel(),
                    l_rsvEqOrderUnitRow.getOrderRootDiv(),
                    l_blnShortSellingRestraint);
                
            //set受注日時(受注日時 : Date)
            //引数は以下の通りにセットする。 
            //  受注日時：　@予約注文単位.受注日時
            l_openMarginUpdateInterceptor.setReceivedDateTime(l_rsvEqOrderUnitRow.getReceivedDateTime());

            //validate取引余力(補助口座 : 補助口座, 注文内容インタセプタ : Object[], 注文内容 : Object[], 
            //  注文種別 : OrderTypeEnum, 余力更新フラグ : boolean)
            //引数は以下の通りに設定する。  
            //  補助口座：　@予約注文単位.口座ID、補助口座IDに該当する補助口座オブジェクト  
            //  注文内容インタセプタ：　@生成した信用新規建更新インタセプタ  
            //  注文内容：　@発注審査に使用した信用新規建注文内容オブジェクト  
            //  注文種別：　@予約注文単位.get注文種別() 
            //  余力更新フラグ：　@true（完了時）
            WEB3TPTradingPowerService l_tradingpowerService =
                (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            Object[] l_objOpenMarginUpdateInterceptor = new Object[]{l_openMarginUpdateInterceptor};
            Object[] l_objOpenContractOrderSpec = new Object[]{l_openContractOrderSpec};
            WEB3TPTradingPowerResult l_tpResult = l_tradingpowerService.validateTradingPower(
                l_subAccount,
                l_objOpenMarginUpdateInterceptor,
                l_objOpenContractOrderSpec,
                l_rsvEqOrderUnit.getOrderType(),
                true);

            //is判定フラグ()
            boolean l_blnIsResultFlg = l_tpResult.isResultFlg();

            //分岐フロー：　@取引余力結果.is判定フラグ( )==trueの場合のみ
            EqTypeOrderSubmissionResult l_orderSubmissionResult = null;
            if (l_blnIsResultFlg)
            {
                //setThreadLocalPersistenceEventInterceptor(信用新規建更新インタセプタ : 
                //  EqTypeOrderManagerPersistenceEventInterceptor)
                l_orderManager.setThreadLocalPersistenceEventInterceptor(l_openMarginUpdateInterceptor);

                //[submitOpenContractOrder( )：引数設定仕様]
                //  arg0（補助口座）：　@取得した補助口座オブジェクト
                //  arg1（信用新規建注文内容）：　@create新規建注文内容()の戻り値
                //  arg2（注文ＩＤ）：　@予約注文単位.get注文ID()
                //  arg3（取引パスワード）：　@顧客.getTradingPassword()の戻り値をdecryptした値
                //  arg4（isSkip発注審査）：　@true
                MainAccount l_mainAccount = l_subAccount.getMainAccount();
                WEB3Crypt l_crypt = new WEB3Crypt();
                l_orderSubmissionResult =
                    l_orderManager.submitOpenContractOrder(
                        l_subAccount,
                        l_openContractOrderSpec,
                        l_rsvEqOrderUnit.getOrderId(),
                        l_crypt.decrypt(l_mainAccount.getTradingPassword()),
                        true);
            }
            else
            {
                log.debug("取引余力チェックエラー。");
                l_orderManager.throwTpErrorInfo(l_tpResult, l_rsvEqOrderUnit.getOrderType());
            }

            //実行結果に応じ、予約注文単位をupdateする
            if (l_orderSubmissionResult.getProcessingResult().isSuccessfulResult())
            {
                //set発注済To予約注文単位(ProductTypeEnum, long)(連続注文マネージャImpl::
                //  set発注済To予約注文単位)
                //引数は以下の通りにセットする。 
                //  銘柄タイプ：　@引数の予約注文単位.銘柄タイプ 
                //  注文ID：　@引数の予約注文単位.get注文ID()
                WEB3ToSuccOrderManagerImpl l_orderManagerImpl = 
                    (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
                l_orderManagerImpl.setOrderedToOrderUnit(
                    l_rsvEqOrderUnit.getProductType(),
                    l_rsvEqOrderUnit.getOrderId());
            }
            else
            {
                log.debug("{submitOpenContractOrder()}がエラー場合。");

                throw new WEB3BaseException(
                    l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            if (l_ex instanceof WEB3BaseException)
            {
                l_strErrorCode = ((WEB3BaseException)l_ex).getErrorInfo().getErrorCode();
            }
            else if (l_ex instanceof WEB3BaseRuntimeException)
            {
                l_strErrorCode = ((WEB3BaseRuntimeException)l_ex).getErrorInfo().getErrorCode();
            }
            else if (l_ex instanceof NotFoundException)
            {
                l_strErrorCode = WEB3ErrorCatalog.SYSTEM_ERROR_80005.getErrorCode();
            }
            else
            {
                l_strErrorCode = WEB3ErrorCatalog.SYSTEM_ERROR_80002.getErrorCode();
            }

            //invalidate予約注文単位(株式予約注文単位行 : 株式予約注文単位Row, 発注エラーコード : String)
            //引数は以下の通りにセットする。 
            //株式予約注文単位行：　@引数の予約注文単位の行オブジェクト 
            //発生エラーコード：　@発生した例外オブジェクトのErrorInfo.error_code  
            //　@　@　@　@　@　@            （エラーの原因の特定が可能なBusinessError／SystemErrorのエラーコード）
            l_orderUpdateService.invalidateOrderUnit(
                l_rsvEqOrderUnitRow,
                l_strErrorCode);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (submit信用返済注文)<BR>
     * 信用返済注文を発注する。<BR>
     * シーケンス図「（連続注文株式発注一件サービス）submit信用返済注文」を参照。<BR>
     * <BR>
     * @@param l_rsvEqOrderUnit - (株式予約注文単位)<BR>
     * 株式予約注文単位オブジェクト。<BR>
     * @@exception WEB3BaseException<BR>
     * @@roseuid 434622340223
     */
    public void submitMarginSettleContractOrder(WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitMarginSettleContractOrder(WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);
        
        //株式予約注文単位
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow = null;
        
        //エラーコード
        String l_strErrorCode = null;

        //株式予約注文更新サービス
        WEB3ToSuccReservationEqTypeOrderUpdateService l_orderUpdateService =
            (WEB3ToSuccReservationEqTypeOrderUpdateService)Services.getService(
                WEB3ToSuccReservationEqTypeOrderUpdateService.class);

        try
        {
            //get発注日(確認時発注日 : Date)
            //引数は以下の通りにセットする。
            //確認時発注日：　@引数の株式予約注文単位.発注日
            l_rsvEqOrderUnitRow = (RsvEqOrderUnitRow)l_rsvEqOrderUnit.getDataSourceObject();
            Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(
                WEB3DateUtility.getDate(l_rsvEqOrderUnitRow.getBizDate(), "yyyyMMdd"));

            //get株式予約建株返済指定情報一覧( )(株式予約注文単位Impl::get株式予約建株返済指定情報一覧)
            RsvEqClosingContractSpecRow[] l_arrClosingContractSpec = l_rsvEqOrderUnit.getContractsToClose();
            if (l_arrClosingContractSpec == null)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02339,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            //create決済指定エントリ(株式予約建株返済指定情報行[])(連続注文株式発注一件サービスImpl::create決済指定エントリ)
            //引数は以下の通りにセットする。 
            //  予約返済指定情報一覧：　@株式予約注文単位.get株式予約建株返済指定情報一覧()の戻り値
            EqTypeSettleContractOrderEntry[] l_arrSettleContractOrderEntry = 
                this.createSettleContractEntries(l_arrClosingContractSpec);
            
            //分岐フロー：　@予約注文単位.取引者ID≠nullの場合のみ
            WEB3GentradeTrader l_trader = null;
            if (!l_rsvEqOrderUnitRow.getTraderIdIsNull())
            {
                //getTrader(取引者ID : long)
                //引数は以下の通りにセットする
                //  取引者ID：　@予約注文単位.取引者
                FinObjectManager l_finMng = GtlUtils.getFinObjectManager();
                l_trader = (WEB3GentradeTrader)l_finMng.getTrader(l_rsvEqOrderUnit.getTraderId());
            }
            
            //get予約注文執行単価( )(株式予約注文単位Impl::get予約注文執行単価)
            double l_dblRsvOrderExecPrice = l_rsvEqOrderUnit.getRsvOrderExecPrice();

            //[create返済注文内容( )：引数設定仕様]
            //扱者：　@予約注文単位.取引者ID==nullの場合は、null
            //          予約注文単位.取引者ID≠nullの場合は、取引者IDに該当する扱者オブジェクト
            //決済建株エントリ：　@create決済指定エントリ()の戻り値
            //指値：　@予約注文単位.get予約注文執行単価()の戻り値
            //執行条件：　@"条件なし"
            //注文失効日：　@予約注文単位.注文失効日
            //税区分：　@予約注文単位.税区分
            //値段条件：　@"条件なし"
            //発注条件：　@"条件なし"
            //発注条件演算子：　@null
            //逆指値基準値：　@0
            //（W指値）訂正指値：　@0
            //決済順序区分：　@予約注文単位.決済順序区分
            //初回注文の注文単位ID：　@予約注文単位.初回注文の注文単位ID
            Long l_firstOrderUnitId = null;
            if (!l_rsvEqOrderUnitRow.getFirstOrderUnitIdIsNull())
            {
                l_firstOrderUnitId = new Long(l_rsvEqOrderUnitRow.getFirstOrderUnitId());
            }
            WEB3MarginSettleContractOrderSpec l_closeMarginOrderSpec =
                WEB3MarginSettleContractOrderSpec.createCloseMarginOrderSpec(
                    l_trader,
                    l_arrSettleContractOrderEntry,
                    l_dblRsvOrderExecPrice,
                    EqTypeExecutionConditionType.NONE,
                    l_rsvEqOrderUnitRow.getExpirationDate(), 
                    l_rsvEqOrderUnit.getTaxType(), 
                    WEB3PriceConditionDef.DEFAULT,
                    WEB3OrderingConditionDef.DEFAULT,
                    null,
                    0,
                    0,
                    l_rsvEqOrderUnitRow.getClosingOrderType(),
                    l_firstOrderUnitId);
                    
            //validate返済注文(補助口座 : SubAccount, 信用返済注文内容 : EqTypeSettleContractOrderSpec)
            //引数は以下の通りに設定する。  
            //  補助口座：　@予約注文単位.口座ID、補助口座IDに該当する補助口座オブジェクト  
            //  信用返済注文内容：　@作成した信用返済注文内容オブジェクト
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class); 
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_finApp.getTradingModule(
                    ProductTypeEnum.EQUITY).getOrderManager();
            //補助口座：　@予約注文単位の口座ID、補助口座IDに該当する補助口座オブジェクト
            WEB3GentradeSubAccount l_subAccount = 
                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                    l_rsvEqOrderUnit.getAccountId(),
                    l_rsvEqOrderUnit.getSubAccountId());

            EqTypeNewOrderValidationResult l_orderValidationResult = 
                l_orderManager.validateSettleContractOrder(
                    l_subAccount,
                    l_closeMarginOrderSpec);

            //{validate返済注文()}が失敗の場合。
            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("{validate返済注文()}が失敗の場合。");
                
                throw new WEB3BaseException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //get市場( )(株式予約注文単位Impl::get市場)
            WEB3GentradeMarket l_market = l_rsvEqOrderUnit.getMarket();

            //create手数料(補助口座 : 補助口座, 市場コード : String, 発注日 : Date, 注文チャネル : String, 
            //  信用取引区分 : String, 弁済期限値 : double, 注文カテゴリ : OrderCategEnum)
            //引数は以下の通りに設定する。  
            //  補助口座：　@予約注文単位の口座ID、補助口座IDに該当する補助口座オブジェクト 
            //  市場コード：　@予約注文単位.get市場().市場コード  
            //  発注日：　@予約注文単位.発注日 
            //  注文チャネル：　@予約注文単位.初回注文の注文チャネル 
            //  信用取引区分：　@予約注文単位.弁済区分  
            //  弁済期限値：　@予約注文単位.弁済期限値 
            //  注文カテゴリ：　@OrderCategEnum.”返済注文”（CLOSE_MARGIN）
            WEB3EquityBizLogicProvider l_bizLogicProvider =
                (WEB3EquityBizLogicProvider)l_finApp.getTradingModule(
                    ProductTypeEnum.EQUITY).getBizLogicProvider();
            //手数料を取得
            WEB3GentradeCommission l_commission = l_bizLogicProvider.createCommission(
                l_subAccount, 
                l_market.getMarketCode(), 
                l_datOrderBizDate, 
                l_rsvEqOrderUnitRow.getOrderChanel(), 
                l_rsvEqOrderUnitRow.getRepaymentType(), 
                l_rsvEqOrderUnitRow.getRepaymentNum(), 
                OrderCategEnum.CLOSE_MARGIN);

            //isLimitOrder()
            boolean l_blnIsLimitOrder = l_closeMarginOrderSpec.isLimitOrder();

            //setIs指値(is指値 : boolean)
            //引数は以下の通りにセットする。 
            //  is指値：　@信用返済注文内容.isLimitOrder()の戻り値
            l_commission.setIsLimitPrice(l_blnIsLimitOrder);

            //get取引銘柄()(株式予約注文単位Impl::get取引銘柄)
            WEB3EquityTradedProduct l_tradedProduct =
                (WEB3EquityTradedProduct)l_rsvEqOrderUnit.getTradedProduct();

            //[calc概算決済損益代金( )：引数設定仕様]
            //  手数料：　@作成した手数料オブジェクト
            //  指値：　@予約注文単位.get予約注文執行単価()の戻り値
            //  補助口座：　@予約注文単位.口座ID、補助口座IDに該当する補助口座オブジェクト
            //  取引銘柄：　@取得した取引銘柄オブジェクト
            //  決済建株エントリ：　@create決済指定エントリ()の戻り値
            //  数量：　@返済注文内容.getTotalQuantity()
            //  注文単位：　@null（固定）
            //  今回約定数量：　@0
            //  今回約定単価：　@0
            //  isSkip金額チェック：　@false（スキップしない）
            WEB3EquityRealizedProfitAndLossPrice l_profitAndLossPrice = 
                l_orderManager.calcEstimatedRealizedProfitAndLossAmount(
                    l_commission,
                    l_dblRsvOrderExecPrice,
                    l_subAccount,
                    l_tradedProduct,
                    l_arrSettleContractOrderEntry,
                    l_closeMarginOrderSpec.getTotalQuantity(),
                    null,
                    0,
                    0,
                    false);

            //[信用返済更新インタセプタ( )：引数設定仕様]
            //  信用返済注文内容：　@create返済注文内容()の戻り値
            //  手数料：　@作成した手数料オブジェクト
            //  概算決済損益代金計算結果：　@calc概算決済損益代金()の戻り値
            //  弁済区分：　@予約注文単位.弁済区分
            //  弁済期限値：　@予約注文単位.弁済期限値
            //  初回注文の注文チャネル：　@予約注文単位.初回注文の注文チャネル
            //  注文経路区分：　@予約注文単位.注文経路区分
            WEB3MarginCloseMarginUpdateInterceptor l_closeMarginUpdateInterceptor =
                new WEB3MarginCloseMarginUpdateInterceptor(
                    l_closeMarginOrderSpec,
                    l_commission,
                    l_profitAndLossPrice,
                    l_rsvEqOrderUnitRow.getRepaymentType(), 
                    l_rsvEqOrderUnitRow.getRepaymentNum(), 
                    l_rsvEqOrderUnitRow.getOrderChanel(), 
                    l_rsvEqOrderUnitRow.getOrderRootDiv());

            //set受注日時(受注日時 : Date)
            //引数は以下の通りにセットする。 
            //  受注日時：　@予約注文単位.受注日時
            l_closeMarginUpdateInterceptor.setReceivedDateTime(l_rsvEqOrderUnitRow.getReceivedDateTime());

            //setThreadLocalPersistenceEventInterceptor(信用返済更新インタセプタ : 
            //  EqTypeOrderManagerPersistenceEventInterceptor)
            l_orderManager.setThreadLocalPersistenceEventInterceptor(l_closeMarginUpdateInterceptor);

            //[submitSettleContractOrder( )：引数設定仕様]
            //  arg0（補助口座）：　@取得した補助口座オブジェクト
            //  arg1（返済注文内容）：　@create返済注文内容()の戻り値
            //  arg2（注文ＩＤ）：　@予約注文単位.get注文ID()
            //  arg3（取引パスワード）：　@顧客.getTradingPassword()の戻り値をdecryptした値
            //  arg4（isSkip発注審査）：　@true
            MainAccount l_mainAccount = l_subAccount.getMainAccount();
            WEB3Crypt l_crypt = new WEB3Crypt();

            EqTypeOrderSubmissionResult l_orderSubmissionResult =
                l_orderManager.submitSettleContractOrder(
                    l_subAccount,
                    l_closeMarginOrderSpec,
                    l_rsvEqOrderUnit.getOrderId(),
                    l_crypt.decrypt(l_mainAccount.getTradingPassword()),
                    true);

            //余力再計算(補助口座 : 補助口座)
            //引数は以下の通りにセットする。 
            //  補助口座：　@予約注文単位.口座ID、補助口座IDに該当する補助口座オブジェクト
            WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
                (WEB3TPTradingPowerReCalcService) Services.getService(
                    WEB3TPTradingPowerReCalcService.class);
            l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);

            //実行結果に応じ、予約注文単位をupdateする
            if (l_orderSubmissionResult.getProcessingResult().isSuccessfulResult())
            {
                //set発注済To予約注文単位(ProductTypeEnum, long)(連続注文マネージャImpl::set発注済To予約注文単位)
                //引数は以下の通りにセットする。 
                //  銘柄タイプ：　@引数の予約注文単位.銘柄タイプ 
                //  注文ID：　@引数の予約注文単位.注文ID
                WEB3ToSuccOrderManagerImpl l_orderManagerImpl = 
                    (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
                l_orderManagerImpl.setOrderedToOrderUnit(
                    l_rsvEqOrderUnit.getProductType(),
                    l_rsvEqOrderUnit.getOrderId());
            }
            else
            {
                log.debug("{submitSettleContractOrder()}がエラー場合。");

                throw new WEB3BaseException(
                    l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            if (l_ex instanceof WEB3BaseException)
            {
                l_strErrorCode = ((WEB3BaseException)l_ex).getErrorInfo().getErrorCode();
            }
            else if (l_ex instanceof WEB3BaseRuntimeException)
            {
                l_strErrorCode = ((WEB3BaseRuntimeException)l_ex).getErrorInfo().getErrorCode();
            }
            else if (l_ex instanceof NotFoundException)
            {
                l_strErrorCode = WEB3ErrorCatalog.SYSTEM_ERROR_80005.getErrorCode();
            }
            else
            {
                l_strErrorCode = WEB3ErrorCatalog.SYSTEM_ERROR_80002.getErrorCode();
            }

            //invalidate予約注文単位(株式予約注文単位行 : 株式予約注文単位Row, 発注エラーコード : String)
            //引数は以下の通りにセットする。 
            //株式予約注文単位行：　@引数の予約注文単位の行オブジェクト 
            //発生エラーコード：　@発生した例外オブジェクトのErrorInfo.error_code  
            //　@　@　@　@　@　@            （エラーの原因の特定が可能なBusinessError／SystemErrorのエラーコード）
            l_orderUpdateService.invalidateOrderUnit(
                l_rsvEqOrderUnitRow,
                l_strErrorCode);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (submit信用現引現渡注文)<BR>
     * 信用現引現渡注文を発注する。<BR>
     * シーケンス図「（連続注文株式発注一件サービス）<BR>
     * submit信用現引現渡注文」を参照。<BR>
     * <BR>
     * @@param l_rsvEqOrderUnit - (株式予約注文単位)<BR>
     * 株式予約注文単位オブジェクト。<BR>
     * @@exception WEB3BaseException<BR>
     * @@roseuid 4346224A02FD
     */
    public void submitMarginSwapContractOrder(WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitMarginSwapContractOrder(WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);
        
        //株式予約注文単位
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow = null;
        
        //エラーコード
        String l_strErrorCode = null;

        //株式予約注文更新サービス
        WEB3ToSuccReservationEqTypeOrderUpdateService l_orderUpdateService =
            (WEB3ToSuccReservationEqTypeOrderUpdateService)Services.getService(
                WEB3ToSuccReservationEqTypeOrderUpdateService.class);

        try
        {
            //get発注日(確認時発注日 : Date)
            //引数は以下の通りにセットする。
            //確認時発注日：　@引数の株式予約注文単位.発注日
            l_rsvEqOrderUnitRow = (RsvEqOrderUnitRow)l_rsvEqOrderUnit.getDataSourceObject();
            WEB3GentradeTradingTimeManagement.getOrderBizDate(
                WEB3DateUtility.getDate(l_rsvEqOrderUnitRow.getBizDate(), "yyyyMMdd"));

            //get株式予約建株返済指定情報一覧( )(株式予約注文単位Impl::get株式予約建株返済指定情報一覧)
            RsvEqClosingContractSpecRow[] l_arrClosingContractSpec = l_rsvEqOrderUnit.getContractsToClose();
            if (l_arrClosingContractSpec == null)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02339,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            //create決済指定エントリ(株式予約建株返済指定情報行[])(連続注文株式発注一件サービスImpl::create決済指定エントリ)
            //引数は以下の通りにセットする。 
            //  予約返済指定情報一覧：　@株式予約注文単位.get株式予約建株返済指定情報一覧()の戻り値
            EqTypeSettleContractOrderEntry[] l_arrSettleContractOrderEntry = 
                this.createSettleContractEntries(l_arrClosingContractSpec);
            
            //分岐フロー：　@予約注文単位.取引者ID≠nullの場合のみ
            WEB3GentradeTrader l_trader = null;
            if (!l_rsvEqOrderUnitRow.getTraderIdIsNull())
            {
                //getTrader(取引者ID : long)
                //引数は以下の通りにセットする
                //  取引者ID：　@予約注文単位.取引者
                FinObjectManager l_finMng = GtlUtils.getFinObjectManager();
                l_trader = (WEB3GentradeTrader)l_finMng.getTrader(l_rsvEqOrderUnit.getTraderId());
            }
            
            //[create現引現渡注文内容( )：引数設定仕様]
            //  扱者：　@予約注文単位.取引者ID==nullの場合は、null
            //          予約注文単位.取引者ID≠nullの場合は、取引者IDに該当する扱者オブジェクト
            //  決済建株エントリ：　@create決済指定エントリ()の戻り値
            //  決済順序区分：　@予約注文単位.決済順序区分
            //  税区分：　@予約注文単位.税区分
            //  税区分（現引現渡）：　@予約注文単位.税区分（現引現渡）
            WEB3MarginSwapContractOrderSpec l_swapMarginOrderSpec =
                WEB3MarginSwapContractOrderSpec.createSwapMarginOrderSpec(
                    l_trader,
                    l_arrSettleContractOrderEntry,
                    l_rsvEqOrderUnitRow.getClosingOrderType(),
                    l_rsvEqOrderUnitRow.getTaxType(),
                    l_rsvEqOrderUnitRow.getSwapTaxType());
            
            //validate現引現渡注文(補助口座 : SubAccount, 信用現引現渡注文内容 : EqTypeSwapContractOrderSpec)
            //引数は以下の通りにセットする。 
            //  補助口座：　@予約注文単位.口座ID、補助口座IDに該当する補助口座オブジェクト 
            //  信用現引現渡注文内容：　@作成した現引現渡注文内容オブジェクト
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class); 
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_finApp.getTradingModule(
                    ProductTypeEnum.EQUITY).getOrderManager();
            //補助口座：　@予約注文単位の口座ID、補助口座IDに該当する補助口座オブジェクト
            WEB3GentradeSubAccount l_subAccount = 
                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                    l_rsvEqOrderUnit.getAccountId(),
                    l_rsvEqOrderUnit.getSubAccountId());

            EqTypeNewOrderValidationResult l_orderValidationResult = 
                l_orderManager.validateSwapContractOrder(
                    l_subAccount,
                    l_swapMarginOrderSpec);

            //{validate現引現渡注文()}が失敗の場合。
            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("{validate現引現渡注文()}が失敗の場合。");
                
                throw new WEB3BaseException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //calc概算受渡代金（現引現渡）(決済建株エントリ : EqtypeSettleContractOrderEntry[], 
            //  数量 : double, 注文単位 : 注文単位)
            //引数は以下の通りにセットする。 
            //  決済建株エントリ：　@create決済指定エントリ()の戻り値 
            //  数量：　@作成した信用現引現渡注文内容.getTotalQuantity( )  
            //  注文単位：　@null（固定）：新規注文の登録なのでnull 
            double l_dblTotalQuantity = l_swapMarginOrderSpec.getTotalQuantity();
            double l_dblEstimatedSwapPrice = l_orderManager.calcEstimatedSwapPrice(
                l_arrSettleContractOrderEntry,
                l_dblTotalQuantity,
                null);

            //get建株(建株ID : long)
            //引数は以下の通りにセットする。 
            //  建株ID：　@create決済指定エントリ()の戻り値[0].建株ID
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_finApp.getTradingModule(
                    ProductTypeEnum.EQUITY).getPositionManager();
            if (l_arrSettleContractOrderEntry == null || l_arrSettleContractOrderEntry.length == 0)
            {
                log.debug("データ不整合エラー。");            
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME); 
            }
            WEB3EquityContract l_equityContract = (WEB3EquityContract)l_positionManager.getContract(
                l_arrSettleContractOrderEntry[0].getContractId());

            //isShort()
            boolean l_blnIsShort = l_equityContract.isShort();

            //分岐フロー：建株.isShort()==true（＝現渡注文）の場合のみ
            //譲渡損益
            double l_dblCapitaGain = 0;
            
            //譲渡益税
            double l_dblCapitalGainTax = 0;

            if (l_blnIsShort)
            {
                //calc譲渡損益(金額 : double, 売数量 : double, 銘柄ID : long, 補助口座 : SubAccount, 税区分 : TaxTypeEnum)
                //引数は以下の通りに設定する。 
                //  金額：　@calc概算受渡代金（現引現渡）() 
                //  売数量：　@信用現引現渡注文内容.getTotalQuantity() 
                //  銘柄ID：　@予約注文単位.銘柄ID 
                //  補助口座：　@予約注文単位.口座ID、補助口座IDに該当する補助口座オブジェクト 
                //  税区分：　@信用現引現渡注文内容.get税区分（現引現渡）()
                WEB3EquityBizLogicProvider l_logicProvider = 
                    (WEB3EquityBizLogicProvider)l_finApp.getTradingModule(
                        ProductTypeEnum.EQUITY).getBizLogicProvider();
                TaxTypeEnum l_swapTaxType = l_swapMarginOrderSpec.getSwapTaxType();
                l_dblCapitaGain = l_logicProvider.calcCapitaGain(
                    l_dblEstimatedSwapPrice,
                    l_dblTotalQuantity,
                    l_rsvEqOrderUnitRow.getProductId(),
                    l_subAccount,
                    l_swapTaxType);
                    
                //get取引銘柄()(株式予約注文単位Impl::get取引銘柄)
                WEB3EquityTradedProduct l_tradedProduct =
                    (WEB3EquityTradedProduct)l_rsvEqOrderUnit.getTradedProduct();

                //get受渡日税区分(受渡日 : Date)
                //引数は以下の通りにセットする。  
                //           取引銘柄.getDailyDeliveryDate( )（＝受渡日）
                WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
                TaxTypeEnum l_deliveryDateTaxType = l_mainAccount.getDeliveryDateTaxType(
                    l_tradedProduct.getDailyDeliveryDate());

                //calc譲渡益税(補助口座 : 補助口座, 税区分 : TaxTypeEnum, 金額 : double, 
                //  基準日 : Timestamp, 顧客税区分 : TaxTypeEnum)
                //引数は以下の通りに設定する。  
                //  補助口座：　@予約注文単位.口座ID、補助口座IDに該当する補助口座オブジェクト 
                //  税区分：　@信用現引現渡注文内容.get税区分（現引現渡）()  
                //  金額：　@株式計算サービス.calc譲渡損益()  
                //  基準日：　@予約注文単位.get取引銘柄()で取得した  
                //            取引銘柄.getDailyDeliveryDate()（＝受渡日）  
                //  顧客税区分：　@顧客.get受渡日税区分()
                l_dblCapitalGainTax = l_logicProvider.calcCapitalGainTax(
                    (SubAccount)l_subAccount,
                    l_swapTaxType,
                    l_dblCapitaGain,
                    new Timestamp(l_tradedProduct.getDailyDeliveryDate().getTime()),
                    l_deliveryDateTaxType);
            }

            //get（部店市場弁済別）取扱条件()
            WEB3GentradeBranchMarketRepayDealtCond l_branchMarketRepayDealtCond = 
                l_equityContract.getBranchMarketRepayDealtCond();

            //信用現引現渡更新インタセプタ(信用現引現渡注文内容 : 信用現引現渡注文内容, 
            //  弁済区分（SONAR） : String, 概算受渡代金 : double, 弁済区分 : String, 弁済期限値 : double, 
            //  譲渡益金額 : double, 譲渡益税額 : double, 初回注文の注文チャネル : String, 注文経路区分 : String)
            //引数は以下の通りに設定する。  
            //※建株オブジェクトは、株式ポジションマネージャ.get建株()の戻り値を使用。 
            //  信用現引現渡注文内容：　@作成した信用現引現渡注文内容オブジェクト  
            //  弁済区分（SONAR）：　@建株.get（部店市場弁済別）取扱条件( )で取得した  
            //    （部店市場弁済別）取扱条件オブジェクト.弁済区分（SONAR）  
            //  概算受渡代金：　@calc概算受渡代金（現引現渡）()の戻り値 
            //  弁済区分：　@予約注文単位.弁済区分  
            //  弁済期限値：　@予約注文単位.弁済期限値  
            //  譲渡益金額：　@建株.isShort()==true（現渡）の場合は、株式計算サービス.calc譲渡損益()の戻り値。 
            //　@　@　@            以外、0。 
            //  譲渡益税額：　@建株.isShort()==true（現渡）の場合は、株式計算サービス.calc譲渡益税()の戻り値。 
            //　@　@　@            以外、0。 
            //  初回注文の注文チャネル：　@予約注文単位.初回注文の注文チャネル 
            //  注文経路区分：　@予約注文単位.注文経路区分
            BranchMarketRepayDealtCondRow l_BranchMarketRepayDealtCondRow =
                (BranchMarketRepayDealtCondRow)l_branchMarketRepayDealtCond.getDataSourceObject();
            WEB3MarginSwapMarginUpdateInterceptor l_swapMarginUpdateInterceptor =
                new WEB3MarginSwapMarginUpdateInterceptor(
                    l_swapMarginOrderSpec,
                    l_BranchMarketRepayDealtCondRow.getSonarRepaymentType(),
                    l_dblEstimatedSwapPrice,
                    l_rsvEqOrderUnitRow.getRepaymentType(),
                    l_rsvEqOrderUnitRow.getRepaymentNum(),
                    l_dblCapitaGain,
                    l_dblCapitalGainTax,
                    l_rsvEqOrderUnitRow.getOrderChanel(),
                    l_rsvEqOrderUnitRow.getOrderRootDiv());

            //set受注日時(受注日時 : Date)
            //引数は以下の通りにセットする。 
            //  受注日時：　@予約注文単位.受注日時
            l_swapMarginUpdateInterceptor.setReceivedDateTime(l_rsvEqOrderUnitRow.getReceivedDateTime());

            //validate取引余力(補助口座 : 補助口座, 注文内容インタセプタ : Object[], 注文内容 : Object[], 
            //  注文種別 : OrderTypeEnum, 余力更新フラグ : boolean)
            //引数は以下の通りに設定する。  
            //  補助口座：　@予約注文単位.口座ID、補助口座IDに該当する補助口座オブジェクト 
            //  注文内容インタセプタ：　@生成した信用現引現渡更新インタセプタ  
            //  注文内容：　@作成した信用現引現渡注文内容オブジェクト  
            //  注文種別：　@予約注文単位.get注文種別() 
            //  余力更新フラグ：　@true（完了時）
            WEB3TPTradingPowerService l_tradingPowerService =
                (WEB3TPTradingPowerService)Services.getService(
                    WEB3TPTradingPowerService.class);
            Object[] l_objSwapMarginUpdateInterceptor = new Object[]{l_swapMarginUpdateInterceptor};
            Object[] l_objSwapMarginOrderSpec = new Object[]{l_swapMarginOrderSpec};
            WEB3TPTradingPowerResult l_tpResult =
                l_tradingPowerService.validateTradingPower(
                    l_subAccount,
                    l_objSwapMarginUpdateInterceptor,
                    l_objSwapMarginOrderSpec, 
                    l_rsvEqOrderUnitRow.getOrderType(),
                    true);

            //is判定フラグ( )
            boolean l_blnIsResultFlg = l_tpResult.isResultFlg();

            //取引余力結果.is判定フラグ( )==trueの場合のみ
            EqTypeOrderSubmissionResult l_orderSubmissionResult = null;
            if (l_blnIsResultFlg)
            {
                //setThreadLocalPersistenceEventInterceptor(信用現引現渡更新インタセプタ : EqTypeOrderManagerPersistenceEventInterceptor)
                l_orderManager.setThreadLocalPersistenceEventInterceptor(l_swapMarginUpdateInterceptor);

                //[submitSwapContractOrder( )：引数設定仕様]
                //  arg0（補助口座）：　@取得した補助口座オブジェクト
                //  arg1（現引現渡注文内容）：　@create現引現渡注文内容()の戻り値
                //  arg2（注文ＩＤ）：　@予約注文単位.get注文ID()
                //  arg3（取引パスワード）：　@顧客.getTradingPassword()の戻り値をdecryptした値
                //  arg4（isSkip発注審査）：　@true
                MainAccount l_mainAccount = l_subAccount.getMainAccount();
                WEB3Crypt l_crypt = new WEB3Crypt();
                l_orderSubmissionResult =
                    l_orderManager.submitSwapContractOrder(
                        l_subAccount,
                        l_swapMarginOrderSpec,
                        l_rsvEqOrderUnit.getOrderId(),
                        l_crypt.decrypt(l_mainAccount.getTradingPassword()),
                        true);
            }
            else
            {
                log.debug("取引余力チェックエラー。");
                l_orderManager.throwTpErrorInfo(l_tpResult, l_rsvEqOrderUnitRow.getOrderType());
            }

            //実行結果に応じ、予約注文単位をupdateする
            if (l_orderSubmissionResult.getProcessingResult().isSuccessfulResult())
            {
                //set発注済To予約注文単位(ProductTypeEnum, long)(連続注文マネージャImpl::set発注済To予約注文単位)
                //引数は以下の通りにセットする。 
                //  銘柄タイプ：　@引数の予約注文単位.銘柄タイプ 
                //  注文ID：　@引数の予約注文単位.注文ID
                WEB3ToSuccOrderManagerImpl l_orderManagerImpl = 
                    (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
                l_orderManagerImpl.setOrderedToOrderUnit(
                    l_rsvEqOrderUnit.getProductType(),
                    l_rsvEqOrderUnit.getOrderId());
            }
            else
            {
                log.debug("{submitSwapContractOrder()}がエラーの場合。");

                throw new WEB3BaseException(
                    l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            if (l_ex instanceof WEB3BaseException)
            {
                l_strErrorCode = ((WEB3BaseException)l_ex).getErrorInfo().getErrorCode();
            }
            else if (l_ex instanceof WEB3BaseRuntimeException)
            {
                l_strErrorCode = ((WEB3BaseRuntimeException)l_ex).getErrorInfo().getErrorCode();
            }
            else if (l_ex instanceof NotFoundException)
            {
                l_strErrorCode = WEB3ErrorCatalog.SYSTEM_ERROR_80005.getErrorCode();
            }
            else
            {
                l_strErrorCode = WEB3ErrorCatalog.SYSTEM_ERROR_80002.getErrorCode();
            }

            //invalidate予約注文単位(株式予約注文単位行 : 株式予約注文単位Row, 発注エラーコード : String)
            //引数は以下の通りにセットする。 
            //株式予約注文単位行：　@引数の予約注文単位の行オブジェクト 
            //発生エラーコード：　@発生した例外オブジェクトのErrorInfo.error_code  
            //　@　@　@　@　@　@            （エラーの原因の特定が可能なBusinessError／SystemErrorのエラーコード）
            l_orderUpdateService.invalidateOrderUnit(
                l_rsvEqOrderUnitRow,
                l_strErrorCode);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (create決済指定エントリ)<BR>
     * 引数の株式予約建株返済指定情報一覧より、<BR>
     * 決済注文（返済／現引現渡）の各建への<BR>
     * 決済指定エントリの配列を作成し返却する。<BR>
     * <BR>
     * 引数の予約返済指定情報一覧の要素（index）数分、<BR>
     * 以下の処理を繰り返す。<BR>
     * LOOP終了後、作成したインスタンスの配列を返却する。<BR>
     * <BR>
     * ↓↓↓↓　@START LOOP　@↓↓↓↓<BR>
     * <BR>
     * １）　@返済指定対象の建株オブジェクトを取得する。<BR>
     * <BR>
     * 　@　@　@EQTYPEの株式ポジションマネージャ.get建株<BR>
     *       (予約返済指定情報一覧[index].建株ID)を<BR>
     * 　@　@　@コールする。<BR>
     * <BR>
     * ２）　@返済数量を取得する。<BR>
     * <BR>
     * 　@　@　@返済数量(*1) = 予約返済指定情報一覧[index].返済注文数量<BR>
     * <BR>
     * 　@　@　@返済数量(*1) == 0の場合は、次の要素の処理を行う（continue）。<BR>
     * <BR>
     * ３）　@返済可能建株残高を計算する。<BR>
     * <BR>
     * 　@　@　@返済可能建株残高(*2) = １）で取得した<BR>
     *       建株.建株数 － 建株.getLockedQuantity()<BR>
     * <BR>
     * ４）　@返済数量をチェックする。<BR>
     * <BR>
     * 　@　@　@（返済数量(*1) ＞ 返済可能建株残高(*2)） の場合、<BR>
     * 　@　@　@「建株残高不足エラー」の例外をthrowする。<BR>
     * <BR>
     * ５）　@EqTypeSettleContractOrderEntryのインスタンスを生成し、<BR>
     * 　@　@　@戻り値にappendする。<BR>
     * <BR>
     * 　@　@　@[コンストラクタ引数設定仕様]<BR>
     * 　@　@　@建株ID：　@予約返済指定情報一覧[index].建株ID<BR>
     * 　@　@　@返済数量：　@返済数量(*1)<BR>
     * <BR>
     * ↑↑↑↑　@ENDLOOP　@↑↑↑↑<BR>
     * @@param l_rsvEqClosingContractSpec - (予約返済指定情報一覧)<BR>
     * 株式予約建株返済指定情報行オブジェクトの配列。<BR>
     * @@return EqTypeSettleContractOrderEntry[]<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 434C7376006C
     */
    protected EqTypeSettleContractOrderEntry[] createSettleContractEntries(RsvEqClosingContractSpecRow[] l_rsvEqClosingContractSpec) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSettleContractEntries(RsvEqClosingContractSpecRow[])";
        log.entering(STR_METHOD_NAME);
        
        if (l_rsvEqClosingContractSpec == null)
        {
            log.debug("パラメータ値が不正です。");            
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME); 
        }
        
        //決済指定エントリリストが定義
        List l_lisContractOrderEntry = new ArrayList();

        try
        {
            //決済指定エントリ配列の長度
            int l_intContractSpecLth = l_rsvEqClosingContractSpec.length;

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class); 
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_finApp.getTradingModule(
                    ProductTypeEnum.EQUITY).getPositionManager();

            //↓↓↓↓　@START LOOP　@↓↓↓↓
            for (int i = 0; i < l_intContractSpecLth; i++)
            {
                //１）　@返済指定対象の建株オブジェクトを取得する。
                //　@　@　@EQTYPEの株式ポジションマネージャ.get建株
                //      (予約返済指定情報一覧[index].建株ID)をコールする。
                long l_lngContractId = l_rsvEqClosingContractSpec[i].getContractId();
                Contract l_contract = l_positionManager.getContract(l_lngContractId);

                //２）　@返済数量を取得する。<BR>
                //　@　@　@返済数量(*1) = 予約返済指定情報一覧[index].返済注文数量<BR>
                //　@　@　@返済数量(*1) == 0の場合は、次の要素の処理を行う（continue）。<BR>
                double l_dblQuantity = l_rsvEqClosingContractSpec[i].getQuantity();
                if (l_dblQuantity == 0)
                {
                    continue;
                }

                //３）　@返済可能建株残高を計算する。<BR>
                //　@　@　@返済可能建株残高(*2) = １）で取得した<BR>
                //      建株.建株数 － 建株.getLockedQuantity()<BR>
                double l_dblCloseContractQuantity = l_contract.getQuantity() - l_contract.getLockedQuantity();

                //４）　@返済数量をチェックする。<BR>
                // 　@　@（返済数量(*1) ＞ 返済可能建株残高(*2)） の場合、<BR>
                // 　@　@「建株残高不足エラー」の例外をthrowする。<BR>
                if (l_dblQuantity > l_dblCloseContractQuantity)
                {
                    log.debug("「建株残高不足エラー」");

                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00808,
                        this.getClass().getName() + STR_METHOD_NAME);
                }

                //５）　@EqTypeSettleContractOrderEntryのインスタンスを生成し、<BR>
                //　@　@　@戻り値にappendする。<BR>
                //　@　@　@[コンストラクタ引数設定仕様]<BR>
                //　@　@　@建株ID：　@予約返済指定情報一覧[index].建株ID<BR>
                //　@　@　@返済数量：　@返済数量(*1)<BR>
                EqTypeSettleContractOrderEntry l_contractOrderEntry = 
                    new EqTypeSettleContractOrderEntry(l_lngContractId, l_dblQuantity);
                    
                l_lisContractOrderEntry.add(l_contractOrderEntry);
            }
            //↑↑↑↑　@ENDLOOP　@↑↑↑↑
        }
        catch (NotFoundException l_nfe)
        {
            log.debug(l_nfe.getMessage(), l_nfe);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        //決済指定エントリリストがnull場合
        if (l_lisContractOrderEntry == null || l_lisContractOrderEntry.size() == 0)
        {
            return null;
        }

        EqTypeSettleContractOrderEntry[] l_arrContractOrderEntry =
            new EqTypeSettleContractOrderEntry[l_lisContractOrderEntry.size()];
        l_lisContractOrderEntry.toArray(l_arrContractOrderEntry);

        log.exiting(STR_METHOD_NAME);
        return l_arrContractOrderEntry;
    }
}
@
