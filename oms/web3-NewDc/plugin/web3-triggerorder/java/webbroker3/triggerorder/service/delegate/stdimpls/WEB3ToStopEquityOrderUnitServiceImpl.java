head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.54.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToStopEquityOrderUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 逆指値注文株式発注一件サービスImpl(WEB3ToStopEquityOrderUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08 呉艶飛(中訊) 新規作成
                   2006/01/23 呉艶飛(北京中訊) 仕様変更・モデル077
                   2006/11/22 齊珂  (中訊)     仕様変更・モデル177   
Revesion History : 2007/08/29 柴双紅(中訊) 仕様変更・モデル243,モデル244
Revesion History : 2009/11/19 車進    (中訊) 【トリガー注文・株管理者】逆指値注文発注障害対応
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeClosingContractSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeContractSettleOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultOrderInvalidatedMarketResponseMessage;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.common.define.WEB3ShortSellOrderFlagDef;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityChangeOrderSpec;
import webbroker3.equity.WEB3EquityChangeOrderUnitEntry;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityEstimatedDeliveryPrice;
import webbroker3.equity.WEB3EquityNewCashBasedOrderSpec;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityOrderManagerChangeOrderEventInterceptor;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityRealizedProfitAndLossPrice;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3EquityTradingModule;
import webbroker3.equity.WEB3EquityTypeOrderManagerReusableValidations;
import webbroker3.equity.WEB3MarginChangeOpenMarginUpdateInterceptor;
import webbroker3.equity.WEB3MarginChangeOrderSpec;
import webbroker3.equity.WEB3MarginNewOrderValidationResult;
import webbroker3.equity.WEB3MarginOpenContractOrderSpec;
import webbroker3.equity.WEB3MarginSettleContractOrderSpec;
import webbroker3.equity.message.WEB3EquityChangeCompleteRequest;
import webbroker3.equity.service.delegate.WEB3EquityChangeOrderRequestAdapter;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderSwitching;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.mqgateway.WEB3MQGatewayService;
import webbroker3.mqgateway.WEB3MQMessageSpec;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.WEB3ToStopEquityOrderServiceInterceptor;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationEqTypeOrderUpdateService;
import webbroker3.triggerorder.service.delegate.WEB3ToStopEquityOrderUnitService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (逆指値注文株式発注一件サービスImpl)<BR>
 * 逆指値注文株式発注一件サービス実装クラス<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3ToStopEquityOrderUnitServiceImpl implements WEB3ToStopEquityOrderUnitService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToStopEquityOrderUnitServiceImpl.class);
    
    /**
     * @@roseuid 436ACF6F0167
     */
    public WEB3ToStopEquityOrderUnitServiceImpl() 
    {
     
    }
    
    /**
     * (submit現物株式逆指値注文)<BR>
     * 現物株式逆指値注文を発注する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（連続注文株式発注一件サービス）submit現物株式逆指値注文」参照。<BR>
     *  ========================================================== <BR>
     * 1.17.1（分岐フロー：　@<BR>
     * 二階建エラー（取引余力結果.取引余力エラー情報.取引余力エラー区分 <BR>
     * == "二階建エラー"）の場合「二階建チェックエラー」の例外をスローする。<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01928<BR>
     *  ========================================================== <BR>
     *  ========================================================== <BR>
     * 1.17.2.1.1get預り金不足情報（買付）(取引余力結果 : 取引余力結果)<BR>
     * メソッドコール後、「買付預り金不足」の例外をスローする。<BR>
     * ※取得した文字列は上記例外オブジェクトに設定すること。<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01929<BR>
     *  ========================================================== <BR>
     *  ========================================================== <BR>
     * 1.17.2.2.1get預り金不足情報<BR>
     * （売付）(取引余力結果 : 取引余力結果, 注文株数 : double)<BR>
     * メソッドコール後、「売付預り金不足」の例外をスローする。<BR>
     * ※取得した文字列は上記例外オブジェクト<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01930<BR>
     *  ========================================================== <BR>
     * @@param l_orderUnit - (株式注文単位)<BR>
     * 株式注文単位オブジェクト。<BR>
     * @@roseuid 434C7B0003A0
     */
    public void submitEquityStopOrder(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitEquityStopOrder(EqTypeOrderUnit l_orderUnit) ";        
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

		WEB3TPTradingPowerService l_tpTradingPowerService =
			(WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager = 
            (WEB3EquityOrderManager) l_tradingModule.getOrderManager();        
        SubAccount l_subAccount = null;
        // 注文単位の再取得
        try
        {
	        l_orderUnit =
	            (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
        }
        catch (NotFoundException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        try
        { 
            EqtypeOrderUnitRow l_row = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            WEB3GentradeAccountManager l_accountMananger = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            //補助口座：　@引数の注文単位.口座ID、補助口座IDに該当する補助口座オブジェク
            l_subAccount = l_accountMananger.getSubAccount(l_row.getAccountId(), l_row.getSubAccountId());
            
            //1.1指定された注文が逆指値発注の対象であるかを判定する。
            boolean l_blnProcessObject = this.isProcessObject(l_orderUnit);
            
            //1.2（分岐フロー：　@is処理対象＝false（処理対象外注文）の場合）
            if (!l_blnProcessObject)
            {
                return;
            }
            //1.3発注日チェックを行う。 
            //（注文登録時の発注日と、現在日時から求めた発注日が異なる場合は発注エラーとする）
            Date l_datBizDate = WEB3DateUtility.getDate(l_row.getBizDate(), "yyyyMMdd");
            WEB3GentradeTradingTimeManagement.getOrderBizDate(l_datBizDate);
    
            //1.4（分岐フロー：　@注文単位.取引者ID≠nullの場合のみ）

            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

            WEB3GentradeTrader l_trader = null;
            WEB3GentradeMarket l_market = null;
            EqTypeProduct l_eqtypeProduct = null;
            WEB3GentradeBranch l_branch = null;
            try
            {
                if (!l_row.getTraderIdIsNull())
                {

                    //1.4.1扱者オブジェクトを取得する。 
                    l_trader = 
                        (WEB3GentradeTrader)l_finObjectManager.getTrader(l_row.getTraderId());
                }
                
                // 1.5create新規建注文内容
                l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(l_row.getMarketId());
                
                // 拡張プロダクトマネージャ
                WEB3EquityProductManager l_productManager =
                    (WEB3EquityProductManager) l_tradingModule.getProductManager();
                l_eqtypeProduct =
                    (EqTypeProduct)l_productManager.getProduct(l_row.getProductId());
                
                l_branch = (WEB3GentradeBranch)l_accountMananger.getBranch(l_row.getBranchId());
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3BaseException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            
            //is売注文：　@注文単位.注文種別より判定する。（”現物買注文”の場合はfalse、”現物売注文”の場合はtrue）
            boolean l_blnIsSellOrder = false;
            if (OrderTypeEnum.EQUITY_BUY.equals(l_orderUnit.getOrderType()))
            {
                l_blnIsSellOrder = false;
            }
            else if (OrderTypeEnum.EQUITY_SELL.equals(l_orderUnit.getOrderType()))
            {
                l_blnIsSellOrder = true;
            }

            Long l_firstOrderUnitId = null;
            if (!l_row.getFirstOrderUnitIdIsNull())
            {
                l_firstOrderUnitId = new Long(l_row.getFirstOrderUnitId());
            }
            WEB3EquityNewCashBasedOrderSpec l_orderSpec = WEB3EquityNewCashBasedOrderSpec.createOrderSpec(
                l_branch.getInstitution().getInstitutionCode(),  //証券会社コード：　@注文単位.部店ID の部店オブジェクト.証券会社コード
                l_trader,  //扱者：　@注文単位.取引者ID==nullの場合は、null注文単位.取引者ID≠nullの場合は、取引者IDに該当する扱者オブジェクト
                l_market.getMarketCode(),  //市場コード：　@注文単位.市場IDに該当する市場コード
                l_eqtypeProduct.getProductCode(),  //銘柄コード：　@注文単位.銘柄IDに該当する銘柄コード
                l_row.getQuantity(),  //株数：　@注文単位.注文数量
                l_row.getLimitPrice(),  //指値：　@注文単位.指値
                l_row.getExecutionConditionType(),  //執行条件：　@注文単位.執行条件
                l_row.getTaxType(),  //税区分：　@注文単位.税区分
                l_row.getExpirationDate(),  //注文失効日：　@注文単位.注文失効日
                l_blnIsSellOrder,  //is売注文 :：　@注文単位.注文種別より判定する。（”現物買注文”の場合はfalse、”現物売注文”の場合はtrue）
                l_row.getOrderChanel(),  //注文チャネル：　@注文単位.初回注文の注文チャネル
                l_row.getPriceConditionType(),  //値段条件：　@注文単位.値段条件
                l_row.getOrderConditionType(),  //発注条件：　@注文単位.発注条件
                l_row.getOrderCondOperator(),  //発注条件演算子：　@注文単位.発注条件演算子
                l_row.getStopOrderPrice(),  //逆指値基準値：　@注文単位.逆指値基準値
                0,  //（W指値）訂正指値：　@0（固定）
                l_firstOrderUnitId);  //初回注文の注文単位ID：　@注文単位.初回注文の注文単位
            
            //1.6set手数料商品コード(手数料商品コード : String)
            l_orderSpec.setCommissionProductCode(WEB3CommisionProductCodeDef.LISTING_STOCK);
            
            //1.7手数料オブジェクトを作成する。
            //引数は以下の通りに設定する。
            //部店：　@注文単位.部店ID の部店オブジェクト
            //取引コード（SONAR）：　@注文単位.取引コード（SONAR）
            WEB3GentradeCommission l_commission = l_orderSpec.createCommission(l_branch, l_row.getSonarTradedCode());
            
            //1.8validate現物株式注文(補助口座 : SubAccount, 株式注文内容 : EqTypeNewCashBasedOrderSpec, is連続反対売買 : boolean)
            EqTypeNewOrderValidationResult l_result = l_orderManager.validateNewCashBasedOrder(l_subAccount, l_orderSpec, true);
            if (l_result.getProcessingResult().isFailedResult())
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    l_result.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            //1.10取引銘柄を取得する。
            WEB3EquityTradedProduct l_tradeProduct = (WEB3EquityTradedProduct)l_orderUnit.getTradedProduct();
            
            // 1.11売り注文の場合
            if (l_blnIsSellOrder)
            {
                WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
                    (WEB3EquityTypeOrderManagerReusableValidations)WEB3EquityTypeOrderManagerReusableValidations.getInstance();
                // 1.11.1売付可能数量チェック
                l_orderMgrResVal.validateSellableAssetQuantity(
                    l_subAccount,
                    l_tradeProduct,
                    0D,
                    l_orderSpec.getTaxType());
            }
            
            EqtypeOrderUnitRow l_orderUnitRow =
                (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            // calc拘束金額計算単価
            double l_dblCalcPrice = l_orderManager.calcPriceForRestraintAmount(
                l_commission,
                l_orderUnitRow.getOrderType(),
                l_orderUnitRow.getLimitPrice(),
                l_orderUnitRow.getWLimitPrice(),
                l_orderUnitRow.getOrderConditionType(),
                l_orderUnitRow.getExecutionConditionType(),
                l_orderUnitRow.getPriceConditionType(),
                l_tradeProduct,
                (WEB3GentradeSubAccount)l_subAccount,
                null);
                
            //1.14計算単価をセットする。
            l_orderSpec.setOrderUnitPrice(l_dblCalcPrice);
            
            //1.15calc概算受渡代金(手数料 : 手数料, 計算単価 : double, 補助口座 : SubAccount, 
            //取引銘柄 : 取引銘柄, 株数 : double, is売注文 : boolean, 約定数量 : double,
            //合計約定金額 : double, isSkip金額チェック : boolean, is拘束考慮 : boolean)
            WEB3EquityEstimatedDeliveryPrice l_deliveryPrice = l_orderManager.calcEstimateDeliveryAmount(
                l_orderSpec.getCommission(),  //手数料：　@株式注文内容.get手数料( )
                l_orderSpec.getOrderUnitPrice(),  //計算単価：　@株式注文内容.get注文単価( )
                l_subAccount,  //補助口座：　@引数の注文単位.口座ID、補助口座IDに該当する補助口座オブジェクト
                l_tradeProduct,  //取引銘柄：　@取得した取引銘柄オブジェクト
                l_orderSpec.getQuantity(), //株数：　@株式注文内容.getQuantity( )（未出来分の数量）
                l_orderSpec.isSellOrder(),  //is売注文：　@株式注文内容.isSellOrder( )
                0D,  //約定数量：　@0
                0D,  //合計約定金額：　@0
                false,  //isSkip金額チェック：　@false（スキップしない）
                true);  //is拘束考慮：　@true（考慮する）
            
            //1.16引数は以下の通りに設定する。
            //概算金額：　@calc概算受渡代金戻り値オブジェクト.get概算受渡代金( )
            l_orderSpec.setEstimateDeliveryAmount(l_deliveryPrice.getEstimateDeliveryAmount());

            // create株式注文訂正値詳細(注文単位)
            // 株式注文訂正値詳細オブジェクトを作成する。
            // 注文単位　@：　@パラメータ.注文単位
            WEB3EquityChangeOrderUnitEntry l_entry = this.createEquityChangeOrderUnitEntry(l_orderUnit);
			
			//1.18株式注文訂正内容(注文ＩＤ : long, 株式注文訂正値詳細 : 株式注文訂正値詳細, 証券会社コード : String, 注文チャネル : String, 扱者 : 扱者)
			WEB3EquityChangeOrderSpec l_changeOrderSpec = 
				new WEB3EquityChangeOrderSpec(
					l_row.getOrderId(),
					l_entry,
					l_orderSpec.getInstitutionCode(),
					l_orderSpec.getOrderChannel(),
					l_orderSpec.getTrader());
			
			//1.19株式注文訂正インタセプタ(注文経路区分 : String, 代理入力者 : 扱者)
			WEB3EquityOrderManagerChangeOrderEventInterceptor l_intercepter =
				new WEB3EquityOrderManagerChangeOrderEventInterceptor(
					l_row.getOrderRootDiv(),
					(WEB3GentradeTrader)l_orderSpec.getTrader());
			
			//1.20create株式注文内容( )
            l_changeOrderSpec.createOrderSpec();
            
			WEB3EquityNewCashBasedOrderSpec l_cashBasedOrderSpec = 
				l_changeOrderSpec.getNewCachBasedOrderSpec();
			
			//1.21set注文単価(注文単価 : double)
			l_cashBasedOrderSpec.setOrderUnitPrice(l_orderSpec.getOrderUnitPrice());
			
			//1.22set概算受渡代金(概算金額 : double)
			l_cashBasedOrderSpec.setEstimateDeliveryAmount(l_orderSpec.getEstimateDeliveryAmount());
			
			//1.23set株式注文内容(株式注文内容 : 株式注文内容)
			l_intercepter.setEquityOrderSpec(l_cashBasedOrderSpec);
			
			//1.24validate取引余力(補助口座 : 補助口座, 注文内容インタセプタ : Object[], 注文内容 : Object[], 注文種別 : OrderTypeEnum, 余力更新フラグ : boolean)
			Object[] l_intercepters = { l_intercepter };
			Object[] l_cashBasedOrderSpecs = { l_changeOrderSpec };
			WEB3TPTradingPowerResult l_tradingPowerResult =
				l_tpTradingPowerService.validateTradingPower(
					(WEB3GentradeSubAccount) l_subAccount,
					l_intercepters,
					l_cashBasedOrderSpecs,
					l_row.getOrderType(),
					true);
			
			//1.25throw余力エラー詳細情報(取引余力結果 : 取引余力結果, 注文種別 : OrderTypeEnum)
			l_orderManager.throwTpErrorInfo(l_tradingPowerResult, l_row.getOrderType());
            
            WEB3EquityFrontOrderService l_frontOrderService = (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            String l_strRouteDiv = 
                l_frontOrderService.getSubmitOrderRouteDiv(l_tradeProduct, l_subAccount.getInstitution().getInstitutionCode(), l_row.getSonarTradedCode());
            //1.26（実行結果に応じ、注文単位テーブルをupdateする）
            //1.26.1(*)正常終了した場合
            //1.26.1.1注文系データをupdateする。
            this.updateOrderData(l_orderUnit, l_orderSpec.getOrderUnitPrice(), l_orderSpec.getEstimateDeliveryAmount(), l_strRouteDiv, null);
            
            //1.26.1.2insert現物株式注文キュー(注文ID : long)
            l_orderManager.insertEquityHostOrder(l_orderUnit.getOrderId());
            
            EqTypeOrderUnit l_eqOrderUnit = null;
            try
            {
                l_eqOrderUnit = (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3BaseException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            //1.26.1.3sendMQTrigger(注文単位)
            this.sendMQTrigger(l_eqOrderUnit);
            log.exiting(STR_METHOD_NAME);
            
        }
        //1.26.2(*)処理中に例外がスローされた場合
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            ErrorInfo l_errorInfo = null;
            if (l_ex instanceof WEB3BaseException)
            {
                l_errorInfo = ((WEB3BaseException)l_ex).getErrorInfo();
            }
            else if (l_ex instanceof WEB3BaseRuntimeException)
            {
                l_errorInfo = ((WEB3BaseRuntimeException)l_ex).getErrorInfo();
            }
            //1.26.2.1get注文エラー理由コード(エラーコード : String)
            String l_strReasonCode = l_orderManager.getErrorReasonCode(l_errorInfo.getErrorCode());
            //1.26.2.2逆指値注文株式発注更新インタセプタ(注文エラー理由コード : String)
			WEB3ToStopEquityOrderServiceInterceptor l_interceptor = new WEB3ToStopEquityOrderServiceInterceptor(l_strReasonCode);
            //1.26.2.3注文マネージャに生成した更新イベントインタセプタをセットする。
            l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);
            //1.26.2.4失効メッセージオブジェクトを生成する。
            DefaultOrderInvalidatedMarketResponseMessage l_responseMessage = new DefaultOrderInvalidatedMarketResponseMessage(l_orderUnit.getOrderId());
            //1.26.2.5注文失効処理を行う。
            MarketAdapter l_marketAdapter = l_tradingModule.getMarketAdapter();
            EqTypeMarketResponseReceiverCallbackService l_callbackService = 
                (EqTypeMarketResponseReceiverCallbackService)l_marketAdapter.getMarketResponseReceiverCallbackService();            
            try
            {
	            ProcessingResult l_result = l_callbackService.process(l_responseMessage);
	            if (l_result.isFailedResult())
	            {
	                log.exiting(STR_METHOD_NAME);
	                throw new WEB3SystemLayerException(
	                    l_result.getErrorInfo(),
	                    this.getClass().getName() + "." + STR_METHOD_NAME);
	            }
            }
            catch (Exception l_ex2)
            {
                String l_strErrMsg = "発注審査エラーとなった逆指値注文の失効処理に失敗しました。注文ID：[" + l_orderUnit.getOrderId() + "]";
                log.error(l_strErrMsg, l_ex2);
                if (l_errorInfo != null)
                {
                    if (l_errorInfo.error_tag.startsWith("SYSTEM_"))
                    {
                        throw new WEB3SystemLayerException(
                            l_errorInfo,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_strErrMsg);
                    }
                    else
                    {
                        throw new WEB3BusinessLayerException(
                            l_errorInfo,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_strErrMsg);
                    }
                }
                else
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_strErrMsg);
                }                
            }

            // is予約注文確認要
            // 注文単位：　@注文単位
            boolean l_blnIsReserveOrderConfirmRequire =
                l_orderManager.isReserveOrderConfirmRequire(l_orderUnit);

            //invalidateAll予約注文単位(long)
            //該当の注文に紐付く予約注文がある場合、それらの失効も行う。
            //親注文の注文ID：　@注文単位.注文ID
            if (l_blnIsReserveOrderConfirmRequire)
            {
                WEB3ToSuccReservationEqTypeOrderUpdateService l_updateService =
                    (WEB3ToSuccReservationEqTypeOrderUpdateService)Services.getService(
                        WEB3ToSuccReservationEqTypeOrderUpdateService.class);
                l_updateService.invalidateAllOrderUnit(l_orderUnit.getOrderId());
            }

			//1.26.2.6余力再計算を行う。
			l_tpTradingPowerService.reCalcTradingPower((WEB3GentradeSubAccount)l_subAccount);
        }        
            
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (submit信用新規建逆指値注文)<BR>
     * 信用新規建逆指値注文を発注する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（連続注文株式発注一件サービス）submit信用新規建逆指値注文」参照。<BR>
     *  ========================================================== <BR>
     * 1.19.1（分岐フロー：　@二階建エラー<BR>
     * （取引余力結果.取引余力エラー情報.取引余力エラー区分 ==<BR>
     *  "二階建エラー"）の場合「二階建エラー」の例外を<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01928<BR>
     *  ========================================================== <BR>
     *  ========================================================== <BR>
     * 1.19.2（分岐フロー：　@預り金不足エラー<BR>
     * （取引余力結果.取引余力エラー情報.取引余力エラー区分 !=<BR>
     *  "二階建エラー"）の場合「新規建預り金不足」の例外を<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01935<BR>
     *  ========================================================== <BR>
     * @@param l_orderUnit - (株式注文単位)<BR>
     * 株式注文単位オブジェクト。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 434C7B010006
     */
    public void submitMarginOpenContractStopOrder(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "submitMarginOpenContractStopOrder(EqTypeOrderUnit l_orderUnit) ";        
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

		WEB3TPTradingPowerService l_tpTradingPowerService =
			(WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager = 
            (WEB3EquityOrderManager) l_tradingModule.getOrderManager();      
        WEB3EquityBizLogicProvider l_bizLogicProvider =
            (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
        SubAccount l_subAccount = null;
        // 注文単位の再取得
        try
        {
            l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
        }
        catch (NotFoundException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        try
        { 
	        EqtypeOrderUnitRow l_row = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
		    WEB3GentradeAccountManager l_accountMananger = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
		    //補助口座：　@引数の注文単位.口座ID、補助口座IDに該当する補助口座オブジェク
		    l_subAccount = l_accountMananger.getSubAccount(l_row.getAccountId(), l_row.getSubAccountId());
		    
            //1.1指定された注文が逆指値発注の対象であるかを判定する。
            boolean l_blnProcessObject = this.isProcessObject(l_orderUnit);
            
            //1.2（分岐フロー：　@is処理対象＝false（処理対象外注文）の場合）
            if (!l_blnProcessObject)
            {
                return;
            }
            
            //1.3発注日チェックを行う。 
            //（注文登録時の発注日と、現在日時から求めた発注日が異なる場合は発注エラーとする）
            Date l_datBizDate = WEB3DateUtility.getDate(l_row.getBizDate(), "yyyyMMdd");
            Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(l_datBizDate);
    
            //1.4（分岐フロー：　@注文単位.取引者ID≠nullの場合のみ）

            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

            WEB3GentradeTrader l_trader = null;
            WEB3GentradeMarket l_market = null;
            EqTypeProduct l_eqtypeProduct = null;
            try
            {
                if (!l_row.getTraderIdIsNull())
                {

                    //1.4.1扱者オブジェクトを取得する。 
                    l_trader = 
                        (WEB3GentradeTrader)l_finObjectManager.getTrader(l_row.getTraderId());
                }
                
                // 1.5create新規建注文内容
                l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(l_row.getMarketId());
                
                // 拡張プロダクトマネージャ
                WEB3EquityProductManager l_productManager =
                    (WEB3EquityProductManager) l_tradingModule.getProductManager();
                l_eqtypeProduct =
                    (EqTypeProduct)l_productManager.getProduct(l_row.getProductId());
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3BaseException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }

            
            //is買建：　@[注文単位.getSide() == SideEnum.BUY(買)の場合]trueをセット。
            //[注文単位.getSide() == SideEnum.SELL(売)の場合]falseをセット。
            boolean l_blnIsBuy = false;
            if (SideEnum.BUY.equals(l_orderUnit.getSide()))
            {
                l_blnIsBuy = true;
            }
            else 
            {
                l_blnIsBuy = false;
            }

            Long l_firstOrderUnitId = null;
            if (!l_row.getFirstOrderUnitIdIsNull())
            {
                l_firstOrderUnitId =  new Long(l_row.getFirstOrderUnitId());
            }
            WEB3MarginOpenContractOrderSpec l_orderSpec = WEB3MarginOpenContractOrderSpec.createOpenMarginOrderSpec(
                l_trader,  //扱者：　@注文単位.取引者ID==nullの場合は、null注文単位.取引者ID≠nullの場合は、取引者IDに該当する扱者オブジェクト
                l_blnIsBuy,  //is買建：[注文単位.getSide() == SideEnum.BUY(買)の場合]trueをセット。[注文単位.getSide() == SideEnum.SELL(売)の場合]falseをセット。
                l_eqtypeProduct.getProductCode(),  //銘柄コード：　@注文単位.銘柄IDに該当する銘柄コード
                l_market.getMarketCode(),  //市場コード：　@注文単位.市場IDに該当する市場コード
                l_row.getQuantity(),  //株数：　@注文単位.注文数量
                l_row.getLimitPrice(),  //指値：　@注文単位.指値
                l_row.getExecutionConditionType(),  //執行条件：　@注文単位.執行条件
                l_row.getExpirationDate(),  //注文失効日：　@注文単位.注文失効日
                l_row.getTaxType(),  //税区分：　@注文単位.税区分
                l_row.getPriceConditionType(),  //値段条件：　@注文単位.値段条件
                l_row.getOrderConditionType(),  //発注条件：　@注文単位.発注条件
                l_row.getOrderCondOperator(),  //発注条件演算子：　@注文単位.発注条件演算子
                l_row.getStopOrderPrice(),  //逆指値基準値：　@注文単位.逆指値基準値
                0,  //（W指値）訂正指値：　@0（固定）
                l_row.getRepaymentType(),  //弁済区分：　@注文単位.弁済区分
                l_row.getRepaymentNum(),  //弁済期限値：　@注文単位.弁済期
                l_firstOrderUnitId);  //初回注文の注文単位ID：　@注文単位.初回注文の注文単位
            
            //1.6create手数料(注文ID : long)
            WEB3GentradeCommission l_commission = l_bizLogicProvider.createCommission(l_orderUnit.getOrderId());
            
            //1.7set発注日(発注日 : Timestamp)
            l_commission.setOrderBizDate(new Timestamp(l_datOrderBizDate.getTime()));
            
            // 1.11validate取引銘柄（信用）
            WEB3EquityTypeOrderManagerReusableValidations l_typeOrderMgrReusValidations =
                (WEB3EquityTypeOrderManagerReusableValidations)WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            WEB3EquityTradedProduct l_tradeProduct =
                (WEB3EquityTradedProduct)l_typeOrderMgrReusValidations.validateTradedProductForMarginTrading(
                    l_subAccount,
                    (WEB3EquityProduct)l_eqtypeProduct,
                    l_market,
                    ((WEB3GentradeSubAccount)l_subAccount).getWeb3GenBranch(),
                    l_orderSpec.getRepaymentType(),
                    OrderCategEnum.OPEN_MARGIN,
                    l_orderSpec.isShortOrder());
            
            // calc拘束金額計算単価
            EqtypeOrderUnitRow l_orderUnitRow =
                (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            double l_dblCalcPrice = l_orderManager.calcPriceForRestraintAmount(
                l_commission,
                l_orderUnitRow.getOrderType(),
                l_orderUnitRow.getLimitPrice(),
                l_orderUnitRow.getWLimitPrice(),
                l_orderUnitRow.getOrderConditionType(),
                l_orderUnitRow.getExecutionConditionType(),
                l_orderUnitRow.getPriceConditionType(),
                l_tradeProduct,
                (WEB3GentradeSubAccount)l_subAccount,
                null);
            
            //1.13set計算単価(計算単価 : double)
            l_orderSpec.setCalcUnitPrice(l_dblCalcPrice);
            
            //1.14calc注文時建代金(手数料 : 手数料, 計算単価 : double, 補助口座 : SubAccount, 
            //取引銘柄 : EqTypeTradedProduct, 株数 : double, 約定数量 : double, 
            //合計約定金額 : double, isSkip金額チェック : boolean)
            double l_dblAmountAtOrder = l_orderManager.calcContractAmountAtOrder(
                l_commission,  //手数料：　@作成した手数料オブジェクト
                l_orderSpec.getCalcUnitPrice(),  //計算単価：　@信用新規建注文内容.get計算単価( )
                (WEB3GentradeSubAccount)l_subAccount,  //補助口座：　@取得した補助口座オブジェクト
                l_tradeProduct,  //取引銘柄：　@取得した取引銘柄オブジェクト
                l_orderSpec.getQuantity(),  //株数：　@　@信用新規建注文内容.getQuantity( )
                0,  //約定数量：　@0
                0,  //合計約定金額：　@0
                false);  //isSkip金額チェック：　@false（スキップしない）固定
            
            //1.15set建代金(建代金 : double)
            l_orderSpec.setContractAmount(l_dblAmountAtOrder);
            
            //1.16validate新規建注文(補助口座 : SubAccount, 信用新規建注文内容 : EqTypeOpenContractOrderSpec, 注文単位 : 注文単位)
            WEB3MarginNewOrderValidationResult l_validationResult = 
                (WEB3MarginNewOrderValidationResult)l_orderManager.validateOpenContractOrder(l_subAccount, l_orderSpec, l_orderUnit);
            
            if (l_validationResult.getProcessingResult().isFailedResult())
            {
                throw new WEB3BusinessLayerException(l_validationResult.getProcessingResult().getErrorInfo(), STR_METHOD_NAME);
            }
            
            //1.17calc委託手数料(手数料 : 手数料, 補助口座 : SubAccount)
            l_bizLogicProvider.calcCommission(l_commission, l_subAccount);
            
            //1.18空売り規制対象フラグを取得する。
            boolean l_blnRestraint = l_validationResult.getShortSellingRestraint();
            
            //1.19create新規建注文訂正内容(注文ID : long, 注文単位ID : long, 訂正後注文株数 : double, 訂正後指値 : double, 
            //　@　@訂正後執行条件 : EqTypeExecutionConditionType, 訂正後注文失効日 : Date, 訂正後値段条件 : String, 発注条件 : String, 
            //　@　@訂正後発注条件演算子 : String, 訂正後逆指値基準値 : double, 訂正後（W指値）訂正指値 : double, 訂正後is出来るまで注文 : boolean,
            //　@　@代理入力者 : 扱者)
			WEB3MarginChangeOrderSpec l_changeOrderSpec =
				WEB3MarginChangeOrderSpec.createOpenMarginChangeOrderSpec(
					l_row.getOrderId(),
					l_row.getOrderUnitId(),
					l_orderSpec.getQuantity(),
					l_orderSpec.getLimitPrice(),
					l_orderSpec.getExecConditionType(),
					l_orderSpec.getOrderExpDate(),
					l_orderSpec.getPriceConditionType(),
					l_orderSpec.getOrderConditionType(),
					l_orderSpec.getOrderCondOperator(),
					l_orderSpec.getStopOrderPrice(),
					0,
					l_orderManager.isCarriedOrderUnit(l_orderUnit),
					l_orderSpec.getTrader());
			
			//1.20set訂正後計算単価(訂正後計算単価 : double)
			l_changeOrderSpec.setModifiedCalcUnitPrice(l_orderSpec.getCalcUnitPrice());
			
			//1.21set訂正後建代金(建代金 : double)
			l_changeOrderSpec.setModifiedContractAmount(l_orderSpec.getContractAmount());
			
			//1.22信用新規建訂正更新インタセプタ(信用新規建注文訂正内容 : 信用新規建注文訂正内容, 空売り規制対象フラグ : boolean, 注文経路区分 : String, 代理入力者 : 扱者)
			WEB3MarginChangeOpenMarginUpdateInterceptor l_intercepter =
				new WEB3MarginChangeOpenMarginUpdateInterceptor(
					l_changeOrderSpec,
					l_blnRestraint,
					l_row.getOrderRootDiv(),
					l_trader);
			
			//1.23validate取引余力(補助口座 : 補助口座, 注文内容インタセプタ : Object[], 注文内容 : Object[], 注文種別 : OrderTypeEnum, 余力更新フラグ : boolean)
			Object[] l_intercepters = { l_intercepter };
			Object[] l_changeOrderSpecs = { l_changeOrderSpec };
			WEB3TPTradingPowerResult l_tradingPowerResult =
				l_tpTradingPowerService.validateTradingPower(
					(WEB3GentradeSubAccount) l_subAccount,
					l_intercepters,
					l_changeOrderSpecs,
					l_row.getOrderType(),
					true);
			
			//1.24throw余力エラー詳細情報(取引余力結果 : 取引余力結果, 注文種別 : OrderTypeEnum)
			l_orderManager.throwTpErrorInfo(l_tradingPowerResult, l_row.getOrderType());
            
            WEB3EquityFrontOrderService l_frontOrderService = (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            String l_strRouteDiv = 
                l_frontOrderService.getSubmitOrderRouteDiv(l_tradeProduct, l_subAccount.getInstitution().getInstitutionCode(), l_row.getSonarTradedCode());
            //1.25（実行結果に応じ、注文単位テーブルをupdateする）
            //1.25.1(*)正常終了した場合
            //1.25.1.1注文系データをupdateする。
            this.updateOrderData(l_orderUnit, l_orderSpec.getCalcUnitPrice(), l_orderSpec.getContractAmount(), l_strRouteDiv, new Boolean(l_blnRestraint));
            
            //1.25.1.2insert信用新規建注文キュー(注文ID : long)
            l_orderManager.insertMarginOpenHostOrder(l_orderUnit.getOrderId());
            
            EqTypeOrderUnit l_eqOrderUnit = null;
            try
            {
                l_eqOrderUnit = (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3BaseException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            
            //1.25.1.3sendMQTrigger(注文単位)
            this.sendMQTrigger(l_eqOrderUnit);
            log.exiting(STR_METHOD_NAME);
            
        }
        //1.25.2(*)処理中に例外がスローされた場合
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            ErrorInfo l_errorInfo = null;
            if (l_ex instanceof WEB3BaseException)
            {
                l_errorInfo = ((WEB3BaseException)l_ex).getErrorInfo();
            }
            else if (l_ex instanceof WEB3BaseRuntimeException)
            {
                l_errorInfo = ((WEB3BaseRuntimeException)l_ex).getErrorInfo();
            }
            
            //1.25.2.1get注文エラー理由コード(エラーコード : String)
			String l_strReasonCode = l_orderManager.getErrorReasonCode(l_errorInfo.getErrorCode());
            //1.25.2.2逆指値注文株式発注更新インタセプタ(注文エラー理由コード : String)
			WEB3ToStopEquityOrderServiceInterceptor l_interceptor = new WEB3ToStopEquityOrderServiceInterceptor(l_strReasonCode);
            //1.25.2.3注文マネージャに生成した更新イベントインタセプタをセットする。
            l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);
            //1.25.2.4失効メッセージオブジェクトを生成する。
            DefaultOrderInvalidatedMarketResponseMessage l_responseMessage = new DefaultOrderInvalidatedMarketResponseMessage(l_orderUnit.getOrderId());
            //1.25.2.5注文失効処理を行う。
            MarketAdapter l_marketAdapter = l_tradingModule.getMarketAdapter();
            EqTypeMarketResponseReceiverCallbackService l_callbackService = 
                (EqTypeMarketResponseReceiverCallbackService)l_marketAdapter.getMarketResponseReceiverCallbackService();            
            try
            {
	            ProcessingResult l_result = l_callbackService.process(l_responseMessage);
	            if (l_result.isFailedResult())
	            {
	                log.exiting(STR_METHOD_NAME);
	                throw new WEB3SystemLayerException(
	                    l_result.getErrorInfo(),
	                    this.getClass().getName() + "." + STR_METHOD_NAME);
	            }
	            
            }
            catch (Exception l_ex2)
            {
                String l_strErrMsg = "発注審査エラーとなった逆指値注文の失効処理に失敗しました。注文ID：[" + l_orderUnit.getOrderId() + "]";
                log.error(l_strErrMsg, l_ex2);
                if (l_errorInfo != null)
                {
                    if (l_errorInfo.error_tag.startsWith("SYSTEM_"))
                    {
                        throw new WEB3SystemLayerException(
                            l_errorInfo,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_strErrMsg);
                    }
                    else
                    {
                        throw new WEB3BusinessLayerException(
                            l_errorInfo,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_strErrMsg);
                    }
                }
                else
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_strErrMsg);
                }
            }

            // is予約注文確認要
            // 注文単位：　@注文単位
            boolean l_blnIsReserveOrderConfirmRequire =
                l_orderManager.isReserveOrderConfirmRequire(l_orderUnit);

            //invalidateAll予約注文単位(long)
            //該当の注文に紐付く予約注文がある場合、それらの失効も行う。
            //親注文の注文ID：　@注文単位.注文ID
            if (l_blnIsReserveOrderConfirmRequire)
            {
                WEB3ToSuccReservationEqTypeOrderUpdateService l_updateService =
                    (WEB3ToSuccReservationEqTypeOrderUpdateService)Services.getService(
                        WEB3ToSuccReservationEqTypeOrderUpdateService.class);
                l_updateService.invalidateAllOrderUnit(l_orderUnit.getOrderId());
            }

			//1.25.2.6余力再計算を行う。
			l_tpTradingPowerService.reCalcTradingPower((WEB3GentradeSubAccount)l_subAccount);
        }        
            
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (submit信用返済逆指値注文)<BR>
     * 信用返済逆指値注文を発注する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（連続注文株式発注一件サービス）submit信用返済逆指値注文」参照。<BR>
     *  ========================================================== <BR>
     * 1.4.1（* 建株残高チェック）<BR>
     * （３） 返済数量(*1) ＞ 返済可能建株残高(*2)　@の場合、<BR>
     * 「建株残高不足エラー」の例外をthrowする。<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00808<BR>
     *  ========================================================== <BR>
     * @@param l_orderUnit - (株式注文単位)<BR>
     * 株式注文単位オブジェクト。<BR>
     * @@throws WEB3SystemLayerException
     * @@roseuid 434C7B010045
     */
    public void submitMarginSettleContractStopOrder(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "submitMarginSettleContractStopOrder(EqTypeOrderUnit l_orderUnit) ";        
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        WEB3GentradeAccountManager l_accountMananger = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager = 
            (WEB3EquityOrderManager) l_tradingModule.getOrderManager(); 
        
        WEB3EquityBizLogicProvider l_bizLogicProvider =
            (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
        
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
        SubAccount l_subAccount = null;
        // 注文単位の再取得
        try
        {
            l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
        }
        catch (NotFoundException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        EqtypeOrderUnitRow l_row = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        try
        {
            //補助口座を取得する。
            l_subAccount = l_accountMananger.getSubAccount(l_row.getAccountId(), l_row.getSubAccountId());
        }
        catch (NotFoundException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
                
        try
        {
            //1.1指定された注文が逆指値発注の対象であるかを判定する。
            boolean l_blnProcessObject = this.isProcessObject(l_orderUnit);
            
            //1.2（分岐フロー：　@is処理対象＝false（処理対象外注文）の場合）
            if (!l_blnProcessObject)
            {
                return;
            }
            
            //1.3発注日チェックを行う。 
            //（注文登録時の発注日と、現在日時から求めた発注日が異なる場合は発注エラーとする）
            Date l_datBizDate = WEB3DateUtility.getDate(l_row.getBizDate(), "yyyyMMdd");
            Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(l_datBizDate);
            
            //1.4建株返済指定情報を取得する。
            EqTypeClosingContractSpec[] l_contractSpec = 
                ((EqTypeContractSettleOrderUnit)l_orderUnit).getContractsToClose();
            
            //1.5ArrayListを生成する。
            List l_lisContractOrderEntry = new ArrayList();
            if (l_contractSpec != null)
            {
                //1.6（getContractsToClose( )の戻り値（＝建株返済指定情報）要素数(index)分、Loop）
                for (int i = 0; i < l_contractSpec.length; i++)
                {
                    //1.6.1（* 建株残高チェック）
                    //1.6.1.1建株を取得する。
                    long l_lngContratId = l_contractSpec[i].getContractId(); 
                    WEB3EquityContract l_contract = null;
                    try
                    {
                        l_contract = (WEB3EquityContract)l_positionManager.getContract(l_lngContratId);
                    }
                    catch (NotFoundException l_ex)
                    {
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                            this.getClass().getName() + STR_METHOD_NAME, 
                            l_ex.getMessage(), l_ex);
                    }
                    
                    //（１）返済数量(*1)を取得する。返済数量 ＝ 建株返済指定情報[index].返済注文数量
                    double l_dblSettleQuantity = l_contractSpec[i].getQuantity();
                    if (Double.isNaN(l_dblSettleQuantity))
                    {
                        l_dblSettleQuantity = 0.0D;
                    }
                    
                    //1.6.1.2建株数を取得する。
                    double l_dblQuantity = l_contract.getQuantity();                   
                    if (Double.isNaN(l_dblQuantity))
                    {
                        l_dblQuantity = 0.0D;
                    }
                    
                    //1.6.1.3建株に対する決済注文中数量を取得する。
                    double l_dblLockedQuantity = l_contract.getLockedQuantity();
                    if (Double.isNaN(l_dblQuantity))
                    {
                        l_dblLockedQuantity = 0.0D;
                    }
                    
                    //1.6.1.4建株に対する決済注文中数量のうち、当該返済注文の分の注文中数量を取得する。
                    double l_dblOrderLockedQuantity = l_contract.getLockedQuantity(l_orderUnit.getOrderUnitId());
                                        
                    //（２）返済可能建株残高(*2)を算出する。建株は、建株返済指定情報[index].建株IDに該当する建株オブジェクトを取得して使用。
                    //返済可能建株残高 ＝ 建株.建株数 － 建株.getLockedQuantity( )
                    //（＝ロック中数量） ＋ 建株.getロック中数量( )（＝当該注文ロック中数量）
                    double l_dblContractAmount = l_dblQuantity - l_dblLockedQuantity + l_dblOrderLockedQuantity;
                    
                    //（３） 返済数量(*1) ＞ 返済可能建株残高(*2)　@の場合、「建株残高不足エラー」の例外をthrowする。
                    if (l_dblSettleQuantity > l_dblContractAmount)
                    {
                        throw new WEB3BusinessLayerException(
                           WEB3ErrorCatalog.BUSINESS_ERROR_00808,
                           this.getClass().getName() + "." + STR_METHOD_NAME);
                    }
                    
                    //1.6.2EqTypeSettleContractOrderEntryのインスタンスを生成する。
                    if (l_dblSettleQuantity > 0)
                    {
                        EqTypeSettleContractOrderEntry l_orderEntry = new EqTypeSettleContractOrderEntry(l_lngContratId, l_dblSettleQuantity);
                        //1.6.3生成したEqTypeSettleContractOrderEntryをArrayListに追加する。
                        l_lisContractOrderEntry.add(l_orderEntry);
                        
                    }
                }
                //1.7EqTypeSettleContractOrderEntryの配列を生成する。
                EqTypeSettleContractOrderEntry[] l_orderEntries = new EqTypeSettleContractOrderEntry[l_lisContractOrderEntry.size()];
                l_lisContractOrderEntry.toArray(l_orderEntries);
                WEB3GentradeTrader l_trader = null;

                //1.8（分岐フロー：　@注文単位.取引者ID≠nullの場合のみ）
                if (!l_row.getTraderIdIsNull())
                {

                    //1.8.1扱者オブジェクトを取得する。 
                    try
                    {
                        l_trader = 
                            (WEB3GentradeTrader)l_finObjectManager.getTrader(l_row.getTraderId());

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

                Long l_firstOrderUnitId = null;
                if (!l_row.getFirstOrderUnitIdIsNull())
                {
                    l_firstOrderUnitId = new Long(l_row.getFirstOrderUnitId());
                }
                //1.9create返済注文内容
                WEB3MarginSettleContractOrderSpec l_orderSpec = 
                    WEB3MarginSettleContractOrderSpec.createCloseMarginOrderSpec(
                        l_trader,  //扱者：　@注文単位.取引者ID==nullの場合は、null注文単位.取引者ID≠nullの場合は、取引者IDに該当する扱者オブジェクト
                        l_orderEntries,  //決済建株エントリ：　@作成したEqTypeSettleContractOrderEntryの配列
                        l_row.getLimitPrice(),  //指値：　@注文単位.指値
                        l_row.getExecutionConditionType(),  //執行条件：　@注文単位.執行条件
                        l_row.getExpirationDate(),  //注文失効日：　@注文単位.注文失効日
                        l_row.getTaxType(),  //税区分：　@注文単位.税区分
                        l_row.getPriceConditionType(),  //値段条件：　@注文単位.値段条件
                        l_row.getOrderConditionType(),  //発注条件：　@注文単位.発注条件
                        l_row.getOrderCondOperator(),  //発注条件演算子：　@注文単位.発注条件演算子
                        l_row.getStopOrderPrice(),  //逆指値基準値：　@注文単位.逆指値基準値
                        0,  //（W指値）訂正指値：　@0（固定）
                        l_row.getClosingOrderType(),  // 決済順序区分
                        l_firstOrderUnitId);  //初回注文の注文単位ID：　@注文単位.初回注文の注文単位) 

                // 1.10建株を取得する。
                WEB3EquityContract l_equityContract = null;
                try
                {
                    l_equityContract = (WEB3EquityContract)l_positionManager.getContract(l_orderEntries[0].getContractId());
                }
                catch (NotFoundException l_nfe)
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_nfe.getMessage(),
                        l_nfe);
                }
                
                //1.12validate返済注文(補助口座 : SubAccount, 信用返済注文内容 : EqTypeSettleContractOrderSpec, 建株 : WEB3EquityContract)
                EqTypeNewOrderValidationResult l_result = 
                    l_orderManager.validateSettleContractOrder(l_subAccount, l_orderSpec, l_equityContract);
                
                if (l_result.getProcessingResult().isFailedResult())
                {
                    throw new WEB3BusinessLayerException(l_result.getProcessingResult().getErrorInfo(), STR_METHOD_NAME);
                }
                
                //1.13 取引銘柄を取得する。
                WEB3EquityTradedProduct l_tradeProduct = (WEB3EquityTradedProduct)l_equityContract.getTradedProduct();
                
                // 1.14決済総建株数チェック
                EqtypeContractRow l_equityContractRow =
                    (EqtypeContractRow)l_equityContract.getDataSourceObject();
                WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
                    (WEB3EquityTypeOrderManagerReusableValidations)WEB3EquityTypeOrderManagerReusableValidations.getInstance();
                l_orderMgrResVal.validateSettleContractTotalQuantity(
                    (WEB3GentradeSubAccount)l_subAccount,
                    l_orderUnit.getOrderUnitId(),
                    l_tradeProduct,
                    l_orderSpec.getTaxType(),
                    l_equityContractRow.getRepaymentType(),
                    l_equityContractRow.getRepaymentNum(),
                    l_orderSpec.getTotalQuantity(),
                    l_equityContractRow.getContractType());
                
                //1.15create手数料(注文ID : long)
                WEB3GentradeCommission l_commission = l_bizLogicProvider.createCommission(l_orderUnit.getOrderId());
                
                //1.16set発注日(発注日 : Timestamp)
                l_commission.setOrderBizDate(new Timestamp(l_datOrderBizDate.getTime()));
                
                //1.17概算決済損益代金を取得する。
                WEB3EquityRealizedProfitAndLossPrice l_profitAndLossAmount = l_orderManager.calcEstimatedRealizedProfitAndLossAmount(
                    l_commission,  //手数料：　@作成した手数料オブジェクト
                    l_row.getLimitPrice(),  //指値：　@注文単位.指値
                    (WEB3GentradeSubAccount)l_subAccount,  //補助口座：　@取得した補助口座オブジェクト
                    l_tradeProduct,  //取引銘柄：　@取得した取引銘柄オブジェクト
                    l_orderEntries,  //決済建株エントリ：　@作成したEqTypeSettleContractOrderEntryの配列
                    l_orderSpec.getTotalQuantity(),  //　@返済注文内容.getTotalQuantity()
                    null,  //注文単位：　@null（
                    0,  //今回約定数量：　@0
                    0,  //今回約定単価：　@0
                    false);  //isSkip金額チェック：　@false（スキップしない）固定
                
                //1.18（実行結果に応じ、注文単位テーブルをupdateする）
                //1.18.1(*)正常終了した場合
                //1.18.1.1注文系データをupdateする。
                WEB3EquityFrontOrderService l_frontOrderService = 
                    (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
                String l_strRouteDiv = 
                    l_frontOrderService.getSubmitOrderRouteDiv(
                        l_tradeProduct, 
                        l_subAccount.getInstitution().getInstitutionCode(), 
                        l_row.getSonarTradedCode());
                //1.18.1.1注文系データをupdateする。               
                this.updateOrderData(
                    l_orderUnit, 
                    l_profitAndLossAmount.getCalcUnitPrice(), 
                    l_profitAndLossAmount.getEstimatedRealizedProfitAndLossAmount(), 
                    l_strRouteDiv, 
                    null);
                
                //1.18.1.2insert信用返済注文キュー(注文ID : long)
                l_orderManager.insertMarginCloseHostOrder(l_orderUnit.getOrderId());
                
                EqTypeOrderUnit l_eqOrderUnit = null;
                try
                {
                    l_eqOrderUnit = (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
                }
                catch (NotFoundException l_nfe)
                {
                    throw new WEB3BaseException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_nfe.getMessage(),
                        l_nfe);
                }
                
                //1.18.1.3sendMQTrigger(注文単位)
                this.sendMQTrigger(l_eqOrderUnit);
            }

        }
        //1.18.2(*)処理中に例外がスローされた場合
        catch (Exception l_ex)
        { 
            log.error(l_ex.getMessage(), l_ex);
            ErrorInfo l_errorInfo = null;
            if (l_ex instanceof WEB3BaseException)
            {
                l_errorInfo = ((WEB3BaseException)l_ex).getErrorInfo();
            }
            else if (l_ex instanceof WEB3BaseRuntimeException)
            {
                l_errorInfo = ((WEB3BaseRuntimeException)l_ex).getErrorInfo();
            }
            
            //1.18.2.1スローされた例外に対応する注文エラー理由コードを取得する。
			String l_strReasonCode = l_orderManager.getErrorReasonCode(l_errorInfo.getErrorCode());
            
            //1.18.2.2逆指値注文株式発注更新インタセプタ(注文エラー理由コード : String)
			WEB3ToStopEquityOrderServiceInterceptor l_interceptor = new WEB3ToStopEquityOrderServiceInterceptor(l_strReasonCode);
            
            //1.18.2.3注文マネージャに生成した更新イベントインタセプタをセットする。
            l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);
            
            //1.18.2.4失効メッセージオブジェクトを生成する。
            DefaultOrderInvalidatedMarketResponseMessage l_responseMessage = new DefaultOrderInvalidatedMarketResponseMessage(l_orderUnit.getOrderId());
            MarketAdapter l_marketAdapter = l_tradingModule.getMarketAdapter();
            EqTypeMarketResponseReceiverCallbackService l_callbackService = 
                (EqTypeMarketResponseReceiverCallbackService)l_marketAdapter.getMarketResponseReceiverCallbackService(); 
            
            //1.18.2.5注文失効処理を行う。
            try
            {
	            ProcessingResult l_result = l_callbackService.process(l_responseMessage);
	            if (l_result.isFailedResult())
	            {
	                log.exiting(STR_METHOD_NAME);
	                throw new WEB3SystemLayerException(
	                    l_result.getErrorInfo(),
	                    this.getClass().getName() + "." + STR_METHOD_NAME);
	            }
            }
            catch (Exception l_ex2)
            {
                String l_strErrMsg = "発注審査エラーとなった逆指値注文の失効処理に失敗しました。注文ID：[" + l_orderUnit.getOrderId() + "]";
                log.error(l_strErrMsg, l_ex2);
                if (l_errorInfo != null)
                {
                    if (l_errorInfo.error_tag.startsWith("SYSTEM_"))
                    {
                        throw new WEB3SystemLayerException(
                            l_errorInfo,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_strErrMsg);
                    }
                    else
                    {
                        throw new WEB3BusinessLayerException(
                            l_errorInfo,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_strErrMsg);
                    }
                }
                else
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_strErrMsg);
                }
            }

            // is予約注文確認要
            // 注文単位：　@注文単位
            boolean l_blnIsReserveOrderConfirmRequire =
                l_orderManager.isReserveOrderConfirmRequire(l_orderUnit);

            //invalidateAll予約注文単位(long)
            //該当の注文に紐付く予約注文がある場合、それらの失効も行う。
            //親注文の注文ID：　@注文単位.注文ID
            if (l_blnIsReserveOrderConfirmRequire)
            {
                WEB3ToSuccReservationEqTypeOrderUpdateService l_updateService =
                    (WEB3ToSuccReservationEqTypeOrderUpdateService)Services.getService(
                        WEB3ToSuccReservationEqTypeOrderUpdateService.class);
                l_updateService.invalidateAllOrderUnit(l_orderUnit.getOrderId());
            }
        }

        //1.19余力残高を更新する。
        WEB3TPTradingPowerService l_tpTradingPowerService =
            (WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);
        l_tpTradingPowerService.reCalcTradingPower((WEB3GentradeSubAccount)l_subAccount);
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (update注文データ)<BR>
     * 注文系データのupdateを行う。<BR>
     * <BR>
     * １）　@パラメータ.注文単位のcloneを作成する。<BR>
     * <BR>
     * ２）　@１）にて作成したcloneに対し、更新値をセットする。<BR>
     * 　@DB更新仕様<BR>
     * 　@「逆指値注文発注（OK）_株式注文単位テーブル.xls」参照。<BR>
     * <BR>
     * ３）　@注文データをupdateする。<BR>
     * 　@拡張株式注文マネージャ.update注文データ()をコールする。<BR>
     * <BR>
     * 　@[update注文データ()に指定する引数]<BR>
     * 　@　@注文単位：　@２）にて作成した注文単位<BR>
     * 　@　@is履歴作成：　@true（作成する）<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@param l_dblOrderPrice - (注文単価)<BR>
     * 概算受渡代金算出に使用した注文単価。<BR>
     * @@param l_dblEstimatedPrice - (概算受渡代金)<BR>
     * 概算受渡代金。<BR>
     * @@param l_strOrderRootDiv - (発注経路区分)<BR>
     * 発注経路区分<BR>
     * @@param l_blnShortSellingRestraint - (is空売り規制対象)<BR>
     * 空売り規制対象かどうかのフラグ<BR>
     * <BR>
     * true：　@空売り規制対象<BR>
     * false：　@空売り規制対象でない<BR>
     * <BR>
     * ※新規建注文の場合のみセット。<BR>
     * 　@以外、null。<BR>
     * @@roseuid 434F72FD0270
     */
    protected void updateOrderData(
        EqTypeOrderUnit l_orderUnit, 
        double l_dblOrderPrice, 
        double l_dblEstimatedPrice, 
        String l_strOrderRootDiv, 
        Boolean l_blnShortSellingRestraint) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "updateOrderData(EqTypeOrderUnit, double, double, String, Boolean ) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）　@パラメータ.注文単位のcloneを作成する。
        EqtypeOrderUnitRow l_row = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        EqtypeOrderUnitParams l_params = new EqtypeOrderUnitParams(l_row);
        
        // ２）　@１）にて作成したcloneに対し、更新値をセットする。
        //DB更新仕様
        // 　@「逆指値注文発注（OK）_株式注文単位テーブル.xls」参照。
        
        //注文履歴最終通番 = （既存値） + 1
        l_params.setLastOrderActionSerialNo(l_params.getLastOrderActionSerialNo() + 1);
        //注文状態 = 2：発注中（新規注文）
        l_params.setOrderStatus(OrderStatusEnum.ORDERING);
        //引数の注文単価
        //（* 概算受渡代金算出に使用した単価）
        l_params.setPrice(l_dblOrderPrice);
        //引数の概算受渡代金
        //（* 概算受渡代金計算結果）
        l_params.setEstimatedPrice(l_dblEstimatedPrice);
        
        //新規建注文（注文カテゴリ == "新規建注文"）の場合のみセット。以外、（既存値）
        //引数のis空売り規制対象==trueの場合は、5（"価格規制対象"）。
        //引数のis空売り規制対象==falseの場合は、ブランク（"対象外"）。
        if (OrderCategEnum.OPEN_MARGIN.equals(l_orderUnit.getOrderCateg()))
        {
            if (l_blnShortSellingRestraint.booleanValue())
            {
                l_params.setShortSellOrderFlag(WEB3ShortSellOrderFlagDef.PRICE_RESTRAINT);
            }
            else
            {
                l_params.setShortSellOrderFlag(WEB3ShortSellOrderFlagDef.EXCEPT_OBJECT);
            }
        }
        
        //0:初期値
        l_params.setModifyCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
        //引数の発注経路区分
        l_params.setSubmitOrderRouteDiv(l_strOrderRootDiv);
        //1：時価サーバ
        l_params.setRequestType(WEB3RequestTypeDef.QUOTE_SERVER);
        //現在時刻
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        //逆指値注文発注日時
        l_params.setStopOrderOrderedTimestamp(GtlUtils.getSystemTimestamp());
        
        //３）　@注文データをupdateする。
        //拡張株式注文マネージャ.update注文データ()をコールする。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);        
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager = 
            (WEB3EquityOrderManager) l_tradingModule.getOrderManager(); 
        EqTypeOrderUnit l_eqTypeOrderUnit = (EqTypeOrderUnit)l_orderManager.toOrderUnit(l_params);
        l_orderManager.updateOrderData(l_eqTypeOrderUnit, true);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (sendMQトリガ)<BR>
     * MQトリガを発行する必要がある場合は、<BR>
     * MQトリガを発行する。<BR>
     * <BR>
     * １）　@MQトリガを発行するかどうかを判定する。<BR>
     * 　@取引時間管理.isトリガ発行()をコールする。<BR>
     * <BR>
     * 　@[isトリガ発行()に指定する引数]<BR>
     * 　@　@発注条件：　@null（固定）<BR>
     * 　@　@※注文単位.発注条件を指定すると、<BR>
     * 　@　@　@　@逆指値時はfalseが返される為。<BR>
     * <BR>
     * ２）　@MQトリガ発行要否を取得する。<BR>
     * 　@発注先切替.isMQトリガ発行経路()をコールする。<BR>
     * <BR>
     * 　@[isMQトリガ発行経路()に指定する引数]<BR>
     * 　@　@証券会社コード：<BR>
     * 　@　@　@注文単位.証券会社IDに該当する証券会社コード<BR>
     * 　@　@銘柄タイプ：　@注文単位.銘柄タイプ<BR>
     * 　@　@市場コード：　@注文単位.市場IDに該当する市場コード<BR>
     * 　@　@発注経路区分：　@注文単位の同項目<BR>
     * 　@　@フロント発注システム区分：<BR>
     * 　@　@　@株式発注サービス.getフロント発注システム区分(<BR>
     * 　@　@　@　@　@注文単位.市場IDに該当する市場コード, <BR>
     * 　@　@　@　@　@注文単位.getTradedProduct()の取引銘柄.店頭公開区分)<BR>
     * <BR>
     * ３）　@１）、２）の戻り値が両方ともtrueの場合、<BR>
     * 　@以降の処理を実施する。<BR>
     * <BR>
     * 　@３－１）　@発注時に設定するデータコードを取得する。<BR>
     * 　@　@株式発注サービス.get発注時MQデータコード()をコールする。<BR>
     * <BR>
     * 　@　@[get発注時MQデータコード()に指定する引数]<BR>
     * 　@　@　@発注経路区分：　@注文単位の同項目<BR>
     * <BR>
     * 　@　@nullが返却された場合、処理を終了（return）する。　@<BR>
     * <BR>
     * 　@３－２）　@WEB3MQMessageSpecを生成する。<BR>
     * <BR>
     * 　@　@[コンストラクタに指定する引数]<BR>
     * 　@　@　@証券会社コード：　@<BR>
     * 　@　@　@　@注文単位.部店IDに該当する部店.証券会社コード<BR>
     * 　@　@　@データコード：　@<BR>
     * 　@　@　@　@株式発注サービス.get発注時MQデータコード()の戻り値<BR>
     * <BR>
     * 　@３－３）　@MQトリガを発行する。<BR>
     * 　@　@WEB3MQGatewayService.send()メソッドをコールする。<BR>
     * <BR>
     * 　@　@[send()に指定する引数]<BR>
     * 　@　@　@MQメッセージ内容：　@３－２）にて生成したインスタンス<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。<BR>
     * @@roseuid 434F69C801B4
     */
    protected void sendMQTrigger(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "sendMQTrigger(EqTypeOrderUnit l_orderUnit)  ";
        log.entering(STR_METHOD_NAME);
        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        try
        {
            //１）　@MQトリガを発行するかどうかを判定する。
            // 　@取引時間管理.isトリガ発行()をコールする。
            boolean l_blnIsTrigger = WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(null);
            
            //２）　@MQトリガ発行要否を取得する。
            // 　@発注先切替.isMQトリガ発行経路()をコールする。
            EqtypeOrderUnitRow l_row = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountMananger = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            
            WEB3GentradeBranch l_branch = (WEB3GentradeBranch)l_accountMananger.getBranch(l_row.getBranchId());
            WEB3GentradeMarket l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(l_row.getMarketId());
            WEB3EquityTradedProduct l_tradeProduct = (WEB3EquityTradedProduct)l_orderUnit.getTradedProduct();
            
            EqtypeTradedProductRow l_productRow = (EqtypeTradedProductRow)l_tradeProduct.getDataSourceObject();
            
            WEB3EquityFrontOrderService l_frontOrderService = 
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            
            String l_strSystemCode = 
                l_frontOrderService.getFrontOrderSystemCode(
                    l_market.getMarketCode(), 
                    l_productRow.getOpenOtcDiv());
            
            boolean l_blnIsEnable = WEB3GentradeOrderSwitching.isSubmitMQTriggerEnable(
                l_branch.getInstitution().getInstitutionCode(),
                l_row.getProductType(),
                l_market.getMarketCode(),
                l_row.getSubmitOrderRouteDiv(),
                l_strSystemCode);
            
            //３）　@１）、２）の戻り値が両方ともtrueの場合、<BR>
            // 　@以降の処理を実施する。
            if (l_blnIsTrigger && l_blnIsEnable)
            {
                //３－１）　@発注時に設定するデータコードを取得する。<BR>
                //　@　@株式発注サービス.get発注時MQデータコード()をコールする。
                String l_strDataCode = l_frontOrderService.getOrderMQDataCode(l_row.getSubmitOrderRouteDiv());
                //nullが返却された場合、処理を終了（return）する。
                if (l_strDataCode == null)
                {
                    return;
                }
                
                //３－２）　@WEB3MQMessageSpecを生成する。
                WEB3MQMessageSpec l_spec = new WEB3MQMessageSpec(l_branch.getInstitution().getInstitutionCode(), l_strDataCode);
                
                //３－３）　@MQトリガを発行する。<BR>
               // 　@　@WEB3MQGatewayService.send()メソッドをコールする。
                WEB3MQGatewayService l_gatewayService = (WEB3MQGatewayService)Services.getService(WEB3MQGatewayService.class);
                l_gatewayService.send(l_spec);
                
                log.exiting(STR_METHOD_NAME);
            }            
        }
        catch (NotFoundException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        
    }
    
    /**
     * (is処理対象)<BR>
     * 指定の注文が逆指値発注の処理対象であるかを判定する。<BR>
     * <BR>
     * 処理対象の場合、trueを、処理対象外の場合、falseを返却する。 <BR>
     * 以下の条件のいずれかに該当する場合、<BR>
     * 処理対象外とし、falseを返却する。以外、trueを返却する。<BR>
     * <BR>
     * ・パラメータ.注文単位.注文有効状態 != "オープン"<BR>
     * ・パラメータ.注文単位.発注条件 != "逆指値"<BR>
     * ・パラメータ.注文単位.リクエストタイプ == "時価サーバ"<BR>
     * @@param l_orderUnit - (株式注文単位)
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isProcessObject (EqTypeOrderUnit l_orderUnit)
    {
        final String STR_METHOD_NAME =
            "isProcessObject (OrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);
        
        //指定の注文が逆指値発注の処理対象であるかを判定する。
        EqtypeOrderUnitRow l_row = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderingCondition = l_row.getOrderConditionType();
        String l_strRequestType = l_row.getRequestType();
        
        //以下の条件のいずれかに該当する場合、
        //処理対象外とし、falseを返却する。以外、trueを返却する
        if (!OrderOpenStatusEnum.OPEN.equals(l_orderUnit.getOrderOpenStatus())
            || !WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderingCondition)
            || WEB3RequestTypeDef.QUOTE_SERVER.equals(l_strRequestType))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
    }
    
    /**
     * （create株式注文訂正リクエストアダプタ）<BR>
     * 株式注文訂正リクエストアダプタを作成する。<BR>
     * <BR>
     * １）　@株式注文訂正完了リクエストインスタンスを生成し、<BR>
     * 　@以下の通りプロパティをセットする。<BR>
     * 　@※未記述の項目にはnullをセットする。<BR>
     * <BR>
     * 　@注文株数：　@株式注文内容.注文株数<BR>
     * 　@注文単価区分：　@注文単位.isMarketOrder() == trueの場合、<BR>
     * 　@　@"成行"。以外、"指値"<BR>
     * 　@注文単価：　@注文単位.指値 <BR>
     * 　@値段条件：　@注文単位.値段条件<BR>
     * 　@執行条件：　@株式注文マネージャ.get執行条件（SONAR）(注文単位.執行条件)<BR>
     * 　@注文期限区分：　@株式注文マネージャ.is出来るまで注文単位(注文単位) == trueの場合、<BR>
     * 　@　@"出来るまで注文"。以外、"当日限り" <BR>
     * 　@注文有効期限：　@注文期限区分 == "出来るまで注文"の場合、<BR>
     * 　@　@注文単位.注文失効日付。以外、null。<BR>
     * 　@発注条件区分：　@注文単位.発注条件 <BR>
     * 　@逆指値用発注条件単価：　@注文単位.逆指値基準値<BR>
     * 　@逆指値用発注条件演算子：　@注文単位.発注条件演算子<BR>
     * 　@ID：　@注文単位.注文ID<BR>
     * 　@確認時単価：　@株式注文内容.注文単価<BR>
     * 　@確認時発注日：　@株式注文内容.発注日<BR>
     * <BR>
     * ２）　@株式注文訂正リクエストアダプタ.create()メソッドを<BR>
     * 　@コールし、株式注文訂正リクエストアダプタを生成する。 <BR>
     * <BR>
     * 　@[create()に指定する引数]<BR> 
     * 　@　@リクエスト：　@１）にて作成したリクエストデータ<BR>
     * <BR>
     * ３）　@生成した株式注文訂正リクエストアダプタを返却する。<BR>
     * @@param l_orderUnit - (株式注文単位)
     * @@param l_orderSpec - (株式注文内容)
     * @@return WEB3EquityChangeOrderRequestAdapter
     * @@throws WEB3BaseException
     */
    protected WEB3EquityChangeOrderRequestAdapter createEquityChangeOrderRequestAdapter(
    	EqTypeOrderUnit l_orderUnit,
		WEB3EquityNewCashBasedOrderSpec l_orderSpec) 
		throws WEB3BaseException
    {
    	//１）　@株式注文訂正完了リクエストインスタンスを生成し、
    	//　@以下の通りプロパティをセットする。
    	//　@※未記述の項目にはnullをセットする。
		WEB3EquityChangeCompleteRequest l_request = 
			new WEB3EquityChangeCompleteRequest();
		EqtypeOrderUnitRow l_row = 
			(EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
		FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
		WEB3EquityTradingModule l_tradingModule
			= (WEB3EquityTradingModule) l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
		WEB3EquityOrderManager l_eqOrderManager
			= (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
		
		//注文株数：　@株式注文内容.注文株数
		l_request.orderQuantity = WEB3StringTypeUtility.formatNumber(l_orderSpec.getQuantity());
		//注文単価区分：　@注文単位.isMarketOrder() == trueの場合、
		//　@　@"成行"。以外、"指値"
		l_request.orderPriceDiv = 
			l_orderUnit.isMarketOrder()? WEB3OrderPriceDivDef.MARKET_PRICE : WEB3OrderPriceDivDef.LIMIT_PRICE;
		//注文単価：　@注文単位.指値 
        if ( !l_row.getLimitPriceIsNull() )
        {
            l_request.limitPrice = WEB3StringTypeUtility.formatNumber(l_row.getLimitPrice());
        }
		//値段条件：　@注文単位.値段条件
		l_request.priceCondType = l_row.getPriceConditionType();
		//執行条件：　@株式注文マネージャ.get執行条件（SONAR）(注文単位.執行条件)
		l_request.execCondType = 
			l_eqOrderManager.getExecutionConditionTypeSonar(l_row.getExecutionConditionType());
		//注文期限区分：　@株式注文マネージャ.is出来るまで注文単位(注文単位) == trueの場合、
		//　@　@"出来るまで注文"。以外、"当日限り"
		l_request.expirationDateType = 
			l_eqOrderManager.isCarriedOrderUnit(l_orderUnit)? 
				WEB3OrderExpirationDateTypeDef.CARRIED_ORDER : WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
		//注文有効期限：　@注文期限区分 == "出来るまで注文"の場合、
		//　@　@注文単位.注文失効日付。以外、null。
		if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_request.expirationDateType))
		{
			l_request.expirationDate = l_row.getExpirationDate();	 
		}
		//発注条件区分：　@注文単位.発注条件 
		l_request.orderCondType = l_row.getOrderConditionType();
		//逆指値用発注条件単価：　@注文単位.逆指値基準値
		l_request.stopOrderCondPrice = 
			WEB3StringTypeUtility.formatNumber(l_row.getStopOrderPrice());
		//逆指値用発注条件演算子：　@注文単位.発注条件演算子
		l_request.stopOrderCondOperator = l_row.getOrderCondOperator();
		//Ｗ指値用発注条件単価
		l_request.wlimitOrderCondPrice = null;
		//Ｗ指値用発注条件演算子
		l_request.wlimitOrderCondOperator = null;
		//Ｗ指値用注文単価区分
		l_request.wLimitOrderPriceDiv = null;
		//W指値用注文単価
		l_request.wlimitOrderCondPrice = null;
		//ID：　@注文単位.注文ID
		l_request.id = Long.toString(l_row.getOrderId());
		//確認時単価：　@株式注文内容.注文単価
		l_request.checkPrice = 
			WEB3StringTypeUtility.formatNumber(l_orderSpec.getOrderUnitPrice());
		//確認時発注日：　@株式注文内容.発注日
		l_request.checkDate = l_orderSpec.getOrderBizDate();
		
		//２）　@株式注文訂正リクエストアダプタ.create()メソッドを
		//　@コールし、株式注文訂正リクエストアダプタを生成する。 
		WEB3EquityChangeOrderRequestAdapter l_requestAdapter = 
			WEB3EquityChangeOrderRequestAdapter.create(l_request);
		
		//３）　@生成した株式注文訂正リクエストアダプタを返却する。
		return l_requestAdapter;
    }

    /**
     * (create株式注文訂正値詳細)<BR>
     * 注文訂正値詳細オブジェクトを生成し、<BR>
     * パラメータ.注文単位の内容よりプロパティをセットする。<BR>
     * <BR>
     * １）　@注文単位.getDataSourceObject()をコールする。<BR>
     * <BR>
     * ２）　@拡張株式注文マネージャ.is出来るまで注文単位()をコールする。<BR>
     * <BR>
     * 　@　@　@[is出来るまで注文単位()の引数]<BR>
     * 　@　@　@注文単位：　@パラメータ.注文単位<BR>
     * <BR>
     * ３）　@株式注文訂正値詳細オブジェクトを生成する。<BR>
     * <BR>
     * 　@　@　@[株式注文訂正値詳細()の引数]<BR>
     * 　@　@　@訂正後執行条件：　@注文単位Row.執行条件<BR>
     * 　@　@　@注文単位：　@パラメータ.注文単位<BR>
     * 　@　@　@訂正後is出来るまで注文：　@is出来るまで注文単位()の戻り値<BR>
     * 　@　@　@訂正後値段条件：　@注文単位Row.値段条件<BR>
     * 　@　@　@訂正後発注条件：　@注文単位Row.発注条件<BR>
     * 　@　@　@訂正後発注条件演算子：　@注文単位Row.発注条件演算子<BR>
     * 　@　@　@訂正後逆指値基準値：　@注文単位Row.逆指値基準値<BR>
     * 　@　@　@訂正後（W指値）訂正指値：　@0<BR>
     * 　@　@　@訂正後注文失効日：　@注文単位Row.注文失効日付<BR>
     * 　@　@　@訂正後（W指値）執行条件：　@null<BR>
     * 　@　@　@（W指値）有効状態区分：　@null<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@return WEB3EquityChangeOrderUnitEntry
     * @@throws WEB3BaseException
     */
    public WEB3EquityChangeOrderUnitEntry createEquityChangeOrderUnitEntry(
        EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createEquityChangeOrderUnitEntry(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //注文単位.getDataSourceObject()をコールする
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        //拡張株式注文マネージャ.is出来るまで注文単位()をコールする。
        //　@　@　@[is出来るまで注文単位()の引数]
        //　@　@　@注文単位：　@パラメータ.注文単位
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager  =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        boolean l_blnIsOrderUntilDeadLine = l_orderManager.isCarriedOrderUnit(l_orderUnit);

        //株式注文訂正値詳細オブジェクトを生成する。
        WEB3EquityChangeOrderUnitEntry l_orderUnitEntry =
            new WEB3EquityChangeOrderUnitEntry(
                l_orderUnitRow.getExecutionConditionType(),
                l_orderUnit,
                l_blnIsOrderUntilDeadLine,
                l_orderUnitRow.getPriceConditionType(),
                l_orderUnitRow.getOrderConditionType(),
                l_orderUnitRow.getOrderCondOperator(),
                l_orderUnitRow.getStopOrderPrice(),
                0,
                l_orderUnitRow.getExpirationDate(),
                null,
                null);

        log.exiting(STR_METHOD_NAME);
        return l_orderUnitEntry;
    }
}@
