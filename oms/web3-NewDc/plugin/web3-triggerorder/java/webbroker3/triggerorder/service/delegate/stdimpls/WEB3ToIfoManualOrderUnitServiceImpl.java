head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.52.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToIfoManualOrderUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP手動発注UnitServiceImpl(WEB3ToIfoManualOrderUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/17 譚漢江(中訊) 新規作成
                 : 2006/08/24 唐性峰(中訊) モデルNo.158
                 : 2006/11/13 肖志偉(中訊) モデルNo.190
                   2006/11/30 徐大方(中訊) モデルNo.199
                   2006/12/04 徐大方(中訊) モデルNo.204
                   2006/12/19 徐大方(中訊) モデルNo.211
Revesion History : 2007/06/29 金傑(中訊) モデルNo.237
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3TriggerOrderTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoBizLogicProvider;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.WEB3IfoQuoteData;
import webbroker3.quoteadaptor.WEB3QuoteDataSupplierService;
import webbroker3.rlsgateway.data.RlsConOrderHitNotifyParams;
import webbroker3.rlsgateway.service.WEB3RlsRequestSenderService;
import webbroker3.triggerorder.WEB3ToRlsCoopDataManager;
import webbroker3.triggerorder.define.WEB3ToManualOrderErrorCodeDef;
import webbroker3.triggerorder.define.WEB3ToManualTaxTypeDef;
import webbroker3.triggerorder.define.WEB3ToSuccOpProductTypeDef;
import webbroker3.triggerorder.message.WEB3FuturesOptionsManualUnit;
import webbroker3.triggerorder.message.WEB3ManualCommissionInfoUnit;
import webbroker3.triggerorder.service.delegate.WEB3ToIfoManualOrderUnitService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (先物OP手動発注UnitServiceImpl)<BR>
 * 先物OP手動発注１件サービス実装クラス<BR>
 * <BR>
 * １件ごとの手動発注処理を実施する。<BR> 
 * <BR>
 * Plugin時に自動トランザクション<BR>
 * TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW)を指定する。<BR>
 *   
 * @@author 譚漢江
 * @@version 1.0
 */
public class WEB3ToIfoManualOrderUnitServiceImpl implements WEB3ToIfoManualOrderUnitService 
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToIfoManualOrderUnitServiceImpl.class);

    /**
     * @@roseuid 43F4933F0290
     */
    public WEB3ToIfoManualOrderUnitServiceImpl() 
    {
     
    }
    
    /**
     * (exec手動発注)<BR>
     * 注文1件ごとの手動発注を行う。<BR>
     * @@param l_strProductType - (銘柄タイプ)<BR>
     * 銘柄タイプ<BR>
     * @@param l_strTriggerOrderType - (条件注文種別)<BR>
     * 条件注文種別<BR>
     * @@param l_strOrderId - (注文ID)<BR>
     * 注文ID<BR>
     * @@param l_blnIsUpdated - (is更新)<BR>
     * is更新<BR>
     * <BR>
     * true：　@ルールエンジンからの通知テーブルにレコードをinsertする<BR>
     * false：　@ルールエンジンからの通知テーブルにレコードをinsertしない<BR>
     * @@param l_lngSubmitterLoginId - (発注者ログインID)<BR>
     * 発注者ログインID<BR>
     * @@param l_strSubmitnotifyType - (通知経路)<BR>
     * 通知経路<BR>
     * @@return WEB3FuturesOptionsManualUnit
     * @@throws WEB3BaseException
     * @@roseuid 43EB2E0403AD
     */
    public WEB3FuturesOptionsManualUnit execManualOrder(
        String l_strProductType, 
        String l_strTriggerOrderType, 
        String l_strOrderId, 
        boolean l_blnIsUpdated,
        Long l_lngSubmitterLoginId,
        String l_strSubmitnotifyType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execManualOrder(String, String, String, boolean, Long, String)";
        log.entering(STR_METHOD_NAME);
        
        long l_lngOrderId = Long.parseLong(l_strOrderId);
        
        WEB3FuturesOptionsManualUnit l_manualUnit = null;
        try
        {
            try
            {
                //1.1 getOrderUnits(arg0 : long)
                //[引数]
                //注文ID：　@パラメータ.注文ID
                //以降の処理では、取得した注文単位オブジェクトの0番目の要素を使用する。
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
                WEB3OptionOrderManagerImpl l_orderManager = 
                    (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();
                WEB3GentradeAccountManager l_accountManager = 
                    (WEB3GentradeAccountManager) l_finApp.getAccountManager();
                
                OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
                
                //1.2 （分岐フロー： getOrderUnits()の戻り値の要素数  == 0の場合）
                if (l_orderUnits.length == 0)
                {
                    //1.2.1 先物OP手動発注Unit( )
                    //先物OP手動発注Unitインスタンスを生成する。
                    l_manualUnit = new WEB3FuturesOptionsManualUnit();
                    
                    //1.2.2 (*)プロパティセット
                    //手動発注エラーコード = "該当注文なし"
                    //※それ以外の項目    = null
                    l_manualUnit.manualOrderErrorCode = WEB3ToManualOrderErrorCodeDef.NOT_AVAILABLE;
                    
                    log.exiting(STR_METHOD_NAME);
                    return l_manualUnit;
                }

                //1.3 getProduct()
                IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit) l_orderUnits[0];
                IfoProduct l_ifoProduct = (IfoProduct) l_ifoOrderUnit.getProduct();

                //1.4 get原資産銘柄コード()
                String l_strUnderlyingProductCode = l_ifoProduct.getUnderlyingProductCode();

                //1.5 getBranchId( )
                long l_lngBranchId = l_ifoOrderUnit.getBranchId();
                
                //1.6 getBranch(arg0 : long)
                WEB3GentradeBranch l_branch = (WEB3GentradeBranch) l_accountManager.getBranch(l_lngBranchId);

                //取引カレンダコンテキスト.注文受付商品に商品区分をセットする。
                IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow) l_ifoOrderUnit.getDataSourceObject();
                String l_strOrderAccProduct = "";
                //注文単位.先物/オプション区分：先物の場合
                if (WEB3FuturesOptionDivDef.FUTURES.equals(l_ifoOrderUnitRow.getFutureOptionDiv()))
                {
                    l_strOrderAccProduct = WEB3OrderAccProductDef.FUTURE;
                }
                //注文単位.先物/オプション区分：オプションの場合
                else if (WEB3FuturesOptionDivDef.OPTION.equals(l_ifoOrderUnitRow.getFutureOptionDiv()))
                {
                    l_strOrderAccProduct = WEB3OrderAccProductDef.OPTION;
                }

                String l_strInstitutionCode = l_branch.getInstitution().getInstitutionCode();
                String l_strBranchCode = l_branch.getBranchCode();

                //注文受付トランザクション
                String l_strOrderAcceptTransaction = null;

                //パラメータ.条件注文種別 == "逆指値"の場合
                if (WEB3TriggerOrderTypeDef.STOP.equals(l_strTriggerOrderType))
                {
                    //"買付(新規建)"
                    l_strOrderAcceptTransaction = WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN;
                }

                //パラメータ.条件注文種別 == "W指値"の場合
                else if (WEB3TriggerOrderTypeDef.W_LlIMIT.equals(l_strTriggerOrderType))
                {
                    //"訂正"
                    l_strOrderAcceptTransaction = WEB3OrderAccTransactionDef.CHANGE;
                }

                //1.7 set取引カレンダコンテキスト()
                this.setTradingCalendarContext(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strUnderlyingProductCode,
                    l_strOrderAccProduct,
                    l_strOrderAcceptTransaction);

                //1.8 validate注文受付可能()
                WEB3GentradeTradingTimeManagement.validateOrderAccept();

                //1.9 is立会時間帯()
                boolean l_blnIsSessionTimeZone =
                    WEB3GentradeTradingTimeManagement.isSessionTimeZone();

                //1.10 ※分岐フロー：is立会時間帯がfalseを返却した場合
                if (!l_blnIsSessionTimeZone)
                {
                    log.debug(STR_METHOD_NAME + "：受付可能時間外");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00013,
                        WEB3GentradeTradingTimeManagement.class.getName()
                            + "." + STR_METHOD_NAME);
                }

                //1.13 getAccountId( )
                long l_lngAccountId = l_ifoOrderUnit.getAccountId();
                
                //1.14 getMainAccount(arg0 : long)
                WEB3GentradeMainAccount l_mainAccount =
                    (WEB3GentradeMainAccount) l_accountManager.getMainAccount(l_lngAccountId); 
                
                //1.15 getOP取引口座タイプ( )
                SubAccountTypeEnum l_opSubAccountType = l_mainAccount.getOpSubAccountType();
                
                //1.16 get表示顧客コード( )
                String l_strDisplayAccountCode = l_mainAccount.getDisplayAccountCode();

                //1.17 get商品区分(IfoOrderUnit)
                //[引数]
                //注文単位：　@注文単位
                String l_strCommodity = WEB3IfoDataAdapter.getCommodityDiv(l_ifoOrderUnit);
                
                //1.18 get取引区分(注文種別 : OrderTypeEnum)
                //[引数]  
                //注文種別：　@注文単位.注文種別
                String l_strTradingType = WEB3IfoDataAdapter.getTradingType(l_ifoOrderUnit.getOrderType());
                
                //1.19 get注文状態区分(注文単位 : IfoOrderUnit)
                //[引数]  
                //注文単位：　@注文単位
                String l_strOrderState = WEB3IfoDataAdapter.getOrderStatusType(l_ifoOrderUnit);
                
                //1.20 get執行条件(PR層)(執行条件 : IfoOrderExecutionConditionType)
                //[引数]  
                //執行条件：　@注文単位.執行条件
                String l_strPRExecCond = WEB3IfoDataAdapter.getExecutionCondByPr(l_ifoOrderUnit.getExecutionConditionType());
                
                //1.21 get約定状態区分(注文単位 : IfoOrderUnit)
                //[引数]  
                //注文単位：　@注文単位
                String l_strExecType = WEB3IfoDataAdapter.getExecStatusType(l_ifoOrderUnit);

                //1.22 get発注状況区分(注文単位 : IfoOrderUnit, 条件注文種別 : String)
                //[引数] 
                //注文単位：　@注文単位 
                //条件注文種別：　@パラメータ.条件注文種別
                String l_strTriggerOrderState = WEB3IfoDataAdapter.getTriggerOrderStatusType(l_ifoOrderUnit, l_strTriggerOrderType);

                //1.23 get処理状況区分(IfoOrderUnit)
                String l_strTransStatusType = WEB3IfoDataAdapter.getTransactionStatusType(l_ifoOrderUnit);

                //1.24 isMarketOrder( )
                boolean l_blnIsMarketOrder = l_ifoOrderUnit.isMarketOrder();
                
                //1.25 getTradedProduct( )
                TradedProduct l_tradedProduct = l_ifoOrderUnit.getTradedProduct();
                
                //1.26 isリアル顧客( )
                boolean l_blnIsRealCustomer = l_mainAccount.isRealCustomer();
                
                //1.27 getQuote(tradedProduct : TradedProduct, realType : RealType)
                //取引銘柄：　@getTradedProduct( )の戻り値
                //realType：　@顧客.isリアル顧客( )==trueの場合は”リアル”、 
                //    　@　@　@　@falseの場合は”20分ディレイ”をセット。
                RealType l_realType = null;
                if (l_blnIsRealCustomer)
                {
                    l_realType = RealType.REAL;
                }
                else
                {
                    l_realType = RealType.DELAY;
                }
                
                WEB3QuoteDataSupplierService l_supplierService = 
                    (WEB3QuoteDataSupplierService) l_tradingModule.getQuoteDataSupplierService();
                WEB3IfoQuoteData l_quoteData
                    = (WEB3IfoQuoteData) l_supplierService.getQuote(l_tradedProduct, l_realType);
                
                if (l_quoteData == null)
                {
                    log.debug("テーブルに該当するデータがありません。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "テーブルに該当するデータがありません。");
                }
                
                //1.28 getCurrentPrice( )
                double l_dblCurrentPrice = l_quoteData.getCurrentPrice();
                
                //1.29 getChange( )
                double l_dblChange = l_quoteData.getChange();
                
                //1.30 getCurrentPriceTime( )
                Timestamp l_tsCurrentPriceTime = l_quoteData.getCurrentPriceTime();
                
                //1.31 create建玉明細ByOrder(注文ＩＤ : long)
                //[引数] 
                //注文ＩＤ：　@パラメータ.リクエストデータ.注文ID
                WEB3FuturesOptionsContractUnit[] l_contractUnits = 
                    l_orderManager.createContractUnitByOrder(l_lngOrderId);
                
                //1.32 get市場閉局警告指数(部店 : 部店, 先物／オプション区分 : String)
                //[get市場閉局警告指数()に指定する引数] 
                //部店：　@.getBranch() の戻り値
                //先物／オプション区分：　@”オプション” 
                String[] l_strTradeCloseSuspensions =
                    WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(l_branch, WEB3FuturesOptionDivDef.OPTION);

                //1.33 validate手動発注注文(注文単位 : OrderUnit, 条件注文種別 : String)
                //[引数]
                //注文単位：　@注文単位
                //条件注文種別：　@パラメータ.条件注文種別
                String l_strManualOrder =
                    WEB3ToRlsCoopDataManager.validateManualOrder(l_ifoOrderUnit, l_strTriggerOrderType);
                
                //1.34 getSubAccount(arg0 : long, arg1 : SubAccountTypeEnum)
                //[引数]
                //口座ID：　@getAccountId()の戻り値
                //補助口座タイプ ：　@getOP取引口座タイプの戻り値
                SubAccount l_subAccount = l_accountManager.getSubAccount(l_lngAccountId, l_opSubAccountType);

                //W指値注文（注文単位.発注条件 == "W指値"）の場合
                WEB3ManualCommissionInfoUnit l_commissionInfoUnit = null;
                WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult = null;
                if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_ifoOrderUnitRow.getOrderConditionType()))
                {
                    //calcストップ注文切替後概算代金(IfoOrderUnit, 補助口座)
                    //ストップ注文切替後の概算代金を算出する。
                    //[引数]
                    //　@注文単位：　@注文単位
                    //　@補助口座：　@getSubAccount()の戻り値
                    l_estimateDeliveryAmountCalcResult =
                        this.calcStopOrderSwitchOverEstimatedPrice(
                            l_ifoOrderUnit,
                            (WEB3GentradeSubAccount)l_subAccount);

                    //create手動発注手数料情報(先物OP概算受渡代金計算結果)
                    //手動発注手数料情報を生成する。 
                    //[引数] 
                    //　@概算代金計算結果：　@calcストップ注文切替後概算代金()の戻り値
                    l_commissionInfoUnit = this.createManualCommissionInfoUnit(l_estimateDeliveryAmountCalcResult);
                }
                //上記以外の場合
                else
                {
                    //1.35 create手動発注手数料情報(IfoOrderUnit, SubAccount)
                    l_commissionInfoUnit =
                        this.createManualCommissionInfoUnit(l_ifoOrderUnit, l_subAccount);
                }

                //1.36 getルールエンジンからの通知データ(注文単位：OrderUnit, 条件注文種別：String, 銘柄タイプ：ProductTypeEnum)
                RlsConOrderHitNotifyParams l_rlsConOrderHitNotifyParams =
                    WEB3ToRlsCoopDataManager.getRLSConOrderHitNotifyData(
                        l_orderUnits[0],
                        l_strTriggerOrderType,
                        new ProductTypeEnum(ProductTypeEnum.IntValues.IFO, l_strProductType)); 
                
                //1.37 （分岐フロー： パラメータ.is更新 == trueの場合(submit手動発注からcallされた場合)
                //       かつ、validate手動発注注文()の戻り値 == "正常"の場合）
                if (l_blnIsUpdated && l_strManualOrder.equals(WEB3ToManualOrderErrorCodeDef.NORMAL))
                {
                    //1.37.1 sendManualSubmitConOrder(
                    //　@　@補助口座 : SubAccount, 
                    //　@　@条件付注文タイプ : int,
                    //　@　@注文の銘柄タイプ : ProductTypeEnum,
                    //　@　@注文の注文ID : Long,
                    //　@　@親注文の銘柄タイプ : ProductTypeEnum,
                    //　@　@親注文の注文ID : Long,
                    //　@　@発注順番 : int)
                    WEB3RlsRequestSenderService l_requestSenderService = 
                        (WEB3RlsRequestSenderService) Services.getService(WEB3RlsRequestSenderService.class);
                    
                    ProductTypeEnum l_productType =
                        new ProductTypeEnum(ProductTypeEnum.IntValues.IFO, l_strProductType);
                    l_requestSenderService.sendManualSubmitConOrder(
                        l_lngSubmitterLoginId,
                        l_strSubmitnotifyType,
                        l_subAccount,
                        Integer.parseInt(l_strTriggerOrderType),
                        l_productType,
                        new Long(l_lngOrderId),
                        null,
                        null,
                        0);
                }
                
                //1.39 先物OP手動発注Unit()
                l_manualUnit = new WEB3FuturesOptionsManualUnit();
                //1.40 (*)プロパティセット
                //---------- 商品共通プロパティ --------------------
                //ID      ＝ パラメータ.注文ID
                l_manualUnit.id = l_strOrderId;
                //条件注文種別    ＝ パラメータ.条件注文種別
                l_manualUnit.triggerOrderType = l_strTriggerOrderType;
                //(*1)注文単位.発注条件＝("逆指値","W指値")の場合セット
                if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_ifoOrderUnitRow.getOrderConditionType())
                    || WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_ifoOrderUnitRow.getOrderConditionType()))
                {
                    //発注条件演算子  ＝ (*1)注文単位.発注条件演算子
                    l_manualUnit.condOperator = l_ifoOrderUnitRow.getOrderCondOperator();
                    //発注条件単価    ＝ (*1)注文単位.逆指値基準値
                    if (!l_ifoOrderUnitRow.getStopOrderPriceIsNull())
                    {
                        l_manualUnit.orderCondPrice =
                            WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getStopOrderPrice());
                    }
                    //発注条件単価区分    ＝ (*1)注文単位.逆指値基準値タイプ
                    l_manualUnit.orderCondPriceDiv = l_ifoOrderUnitRow.getStopPriceType();
                }
                
                //(*2)注文単位.発注条件 == ("W指値")の場合セット
                if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_ifoOrderUnitRow.getOrderConditionType()))
                {
	                //1.11 getＷ指値用注文単価区分(注文単位 : IfoOrderUnit)
	                String l_strWLimitOrderPriceDiv = 
	                    WEB3IfoDataAdapter.getWLimitOrderPriceDiv(l_ifoOrderUnit);
	                
	                //1.12 get執行条件（PR層）(執行条件 : IfoOrderExecutionConditionType)
	                String l_strExecutionCondByPr = WEB3IfoDataAdapter.getExecutionCondByPr(
	                        l_ifoOrderUnitRow.getWLimitExecCondType());
	                        
                    //W指値用注文単価区分    ＝ (*2)getＷ指値用注文単価区分()の戻り値
                    l_manualUnit.wLimitOrderPriceDiv = l_strWLimitOrderPriceDiv;
                    
                    //W指値用注文単価        ＝ (*2)W指値用注文単価区分が"指値"の場合、注文単位.（W指値）訂正指値をセット
                    if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strWLimitOrderPriceDiv))
                    {
                        if (!l_ifoOrderUnitRow.getWLimitPriceIsNull())
                        {
                            l_manualUnit.wLimitPrice = 
                                WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getWLimitPrice());
                        }
                        else 
                        {
                            l_manualUnit.wLimitPrice = null;
                        }
                        
                    }
                    
                    //W指値用注文執行条件    ＝ (*2)get執行条件(PR層)()の戻り値
                    l_manualUnit.wlimitExecCondType = l_strExecutionCondByPr;
                        
                }
                               
                //部店コード      ＝ getBranch()の戻り値.部店コード
                l_manualUnit.branchCode = l_strBranchCode; 
                //顧客コード      ＝ getMainAccount()の戻り値.get表示顧客コード()
                l_manualUnit.accountCode = l_strDisplayAccountCode;
                //市場コード      ＝ 注文単位.市場IDに該当する市場.市場コード
                WEB3GentradeFinObjectManager l_gentradeFinObjectManager =
                    (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
                l_manualUnit.marketCode =
                    l_gentradeFinObjectManager.getMarket(l_ifoOrderUnitRow.getMarketId()).getMarketCode();
                
                IfoProductRow l_ifoProductRow = (IfoProductRow) l_ifoProduct.getDataSourceObject();
                //銘柄コード ＝ getProduct()の戻り値.getProductCode()
                l_manualUnit.productCode = l_ifoProductRow.getProductCode();
                //銘柄名      ＝ getProduct()の戻り値.銘柄名
                l_manualUnit.productName = l_ifoProductRow.getStandardName();
                //商品区分        ＝ get商品区分()の戻り値
                l_manualUnit.productDiv = l_strCommodity;
                //取引区分        ＝ get取引区分(PR層)()の戻り値
                l_manualUnit.tradingType = l_strTradingType;
                //執行条件        ＝ getPR層執行条件()の戻り値
                l_manualUnit.execCondType = l_strPRExecCond;
                //注文期限区分        ＝ 先物OPデータアダプタ.get注文期限区分(注文単位)の戻り値
                String l_strExpirationDateType = WEB3IfoDataAdapter.getExpirationDateType(l_ifoOrderUnit);
                l_manualUnit.expirationDateType = l_strExpirationDateType;
                //注文有効期限        ＝ 先物OPデータアダプタ.get注文期限区分(注文単位)の戻り値が
                //"出来るまで注文"の場合のみ、注文単位.注文失効日をセット。以外の場合、nullをセット。
                if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_strExpirationDateType))
                {
                    l_manualUnit.expirationDate = WEB3DateUtility.toDay(l_ifoOrderUnitRow.getExpirationDate());
                }
                else
                {
                    l_manualUnit.expirationDate = null;
                }
                //注文数量        ＝ 注文単位.注文数量
                l_manualUnit.orderQuantity = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnit.getQuantity());
                //注文単価区分    ＝ isMarketOrder()の戻り値がtrueの場合、"成行"をセット。
                //　@　@　@　@　@　@　@　@　@　@falseの場合、"指値"をセット。
                if (l_blnIsMarketOrder)
                {
                    l_manualUnit.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                }
                else
                {
                    l_manualUnit.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                    //注文単価        ＝ 注文単価区分が"指値"の場合、注文単位.指値をセット。
                    l_manualUnit.limitPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnit.getLimitPrice());
                }
                //注文状態区分    ＝ get注文状態区分(PR層)()の戻り値
                l_manualUnit.orderState = l_strOrderState;
                //約定状態区分    ＝ get約定状態区分(PR層)()の戻り値
                l_manualUnit.execType = l_strExecType;
                //訂正取消区分    ＝ 注文単位.注文訂正・取消区分
                l_manualUnit.changeCancelDiv = l_ifoOrderUnitRow.getModifyCancelType();
                //注文時間        ＝ 注文単位.受注日時
                l_manualUnit.orderDate = l_ifoOrderUnitRow.getReceivedDateTime();
                //発注日      ＝ 注文単位.発注日
                l_manualUnit.orderBizDate = WEB3DateUtility.getDate(l_ifoOrderUnitRow.getBizDate(), "yyyyMMdd");
                //受渡日      ＝ 注文単位.受渡日
                l_manualUnit.deliveryDate = l_ifoOrderUnit.getDeliveryDate();
                //発注状況区分    ＝ get発注状況区分(PR層)()の戻り値
                l_manualUnit.triggerOrderState = l_strTriggerOrderState;
                //概算受渡代金    ＝ 逆指値注文の場合、注文単位.概算受渡代金。
                //W指値注文の場合、calcストップ注文切替後概算代金()の戻り値.概算受渡代金。
                if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_ifoOrderUnitRow.getOrderConditionType()))
                {
                    l_manualUnit.estimatedPrice =
                        WEB3StringTypeUtility.formatNumber(
                            l_estimateDeliveryAmountCalcResult.getEstimateDeliveryAmount());
                }
                else
                {
                    if (!l_ifoOrderUnitRow.getEstimatedPriceIsNull())
                    {
                        l_manualUnit.estimatedPrice =
                            WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getEstimatedPrice());
                    }
                }
                //決済順序        ＝ 注文単位.決済順序
                l_manualUnit.closingOrder = l_ifoOrderUnitRow.getClosingOrder();
                //時価区分        ＝ null
                l_manualUnit.currentPriceDiv = null;
                //時価(現在値)    ＝ getCurrentPrice()の戻り値
                l_manualUnit.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice);
                //前日比      ＝ getChange()の戻り値
                l_manualUnit.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblChange);
                //取引時間(時価発表時間)  ＝ getCurrentPriceTime()の戻り値
                l_manualUnit.currentPriceTime = l_tsCurrentPriceTime;
                //手動発注エラーコード    ＝ validate手動発注注文()の戻り値
                l_manualUnit.manualOrderErrorCode = l_strManualOrder;
                //手動発注手数料情報＝ create手動発注手数料情報()の戻り値
                l_manualUnit.commissionInfo = l_commissionInfoUnit;

                //(*4)getルールエンジンからの通知データ()の戻り値≠nullの場合、以下のプロパティをセット
                if (l_rlsConOrderHitNotifyParams != null)
                {
                    //時価情報受信時間＝ルールエンジンからの通知Params.tickヒットタイムスタンプ
                    l_manualUnit.currentPriceInfoAcceptTime = l_rlsConOrderHitNotifyParams.getHitTickTimestamp();
                    //トリガー起動時間＝ルールエンジンからの通知Params.ルールエンジンファ@イアタイムスタンプ
                    l_manualUnit.triggerStartTime = l_rlsConOrderHitNotifyParams.getRlsHitTimestamp();
                    //発注完了時間＝ルールエンジンからの通知Params.発注完了タイムスタンプ
                    l_manualUnit.orderCompleteTime = l_rlsConOrderHitNotifyParams.getOrderSubmitTimestamp();
                }
                //処理状況区分 ＝ get処理状況区分()の戻り値
                l_manualUnit.transactionStateType = l_strTransStatusType;
                //単価調整値＝null
                //(*5)連続注文対応時に処理を追加する。
                l_manualUnit.priceAdjustmentValue = null;
                //立会区分 ＝ 注文単位.立会区分
                l_manualUnit.sessionType = l_ifoOrderUnitRow.getSessionType();

                //---------- 先物オプション特化プロパティ --------------------
                //getOP取引口座タイプ()の戻り値がSubAccountTypeEnum.株式取引口座の場合
                //口座区分        ＝ "オプション買建口座"
                if (SubAccountTypeEnum.EQUITY_SUB_ACCOUNT.equals(l_opSubAccountType))
                {
                    l_manualUnit.taxType = WEB3ToManualTaxTypeDef.OPTION_BUY_TAX;
                }
                //getOP取引口座タイプ()の戻り値がSubAccountTypeEnum.株式オプション取引口座(先物証拠金)の場合
                //口座区分        ＝ "先物オプション口座"
                else if (SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_opSubAccountType))
                {
                    l_manualUnit.taxType = WEB3ToManualTaxTypeDef.FUTURE_OPTION_TAX;
                }
                //指数種別        ＝ getProduct()の戻り値.原資産銘柄コード
                l_manualUnit.targetProductCode = l_ifoProduct.getUnderlyingProductCode();
                //限月        ＝ getProduct()の戻り値.限月
                l_manualUnit.delivaryMonth = l_ifoProduct.getMonthOfDelivery();
                //行使価格        ＝ getProduct()の戻り値.行使価格
                l_manualUnit.strikePrice = WEB3StringTypeUtility.formatNumber(l_ifoProduct.getStrikePrice());
                //オプション商品区分  ＝ 
                //　@　@　@getProduct()の戻り値.先物オプション商品＝
                //　@　@　@　@　@　@　@"コールオプション"の場合："コールオプション"
                //　@　@　@getProduct()の戻り値.先物オプション商品＝
                //　@　@　@　@　@　@　@"プットオプション"の場合："プットオプション"
                //　@　@　@上記以外の場合はnull
                if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_ifoProductRow.getDerivativeType()))
                {
                    l_manualUnit.opProductType = WEB3ToSuccOpProductTypeDef.PUT_OPTIONS;
                }
                else if ((IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_ifoProductRow.getDerivativeType())))
                {
                    l_manualUnit.opProductType = WEB3ToSuccOpProductTypeDef.CALL_OPTIONS;
                }
                else
                {
                    l_manualUnit.opProductType = null;
                }
                //取引終了警告文言    ＝ get市場閉局警告指数()の戻り値
                l_manualUnit.messageSuspension = l_strTradeCloseSuspensions;
                //建玉明細        ＝ create建玉明細ByOrder()の戻り値
                l_manualUnit.contractUnits = l_contractUnits;
            }
            catch (NotFoundException l_nfe)
            {
                log.error("テーブルに該当するデータがありません。", l_nfe);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "テーブルに該当するデータがありません。",
                    l_nfe);
            }
        }
        //1.38 (*)処理中に例外が発生した場合
        //　@・業務エラー
        catch (WEB3BusinessLayerException l_be)
        {
            log.debug("業務エラー");
            //1.38.1 先物OP手動発注Unit()
            l_manualUnit = new WEB3FuturesOptionsManualUnit();
            //1.38.2 (*)プロパティセット
            //手動発注エラーコード = "その他エラー"
            l_manualUnit.manualOrderErrorCode = WEB3ToManualOrderErrorCodeDef.OTHER;
        }
        //　@・システムエラー
        catch (WEB3SystemLayerException l_se)
        {
            log.debug("システムエラー");
            //1.38.1 先物OP手動発注Unit()
            l_manualUnit = new WEB3FuturesOptionsManualUnit();
            //1.38.2 (*)プロパティセット
            //手動発注エラーコード = "その他エラー"
            l_manualUnit.manualOrderErrorCode = WEB3ToManualOrderErrorCodeDef.OTHER;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_manualUnit;
    }

    /**
     * (create手動発注手数料情報)<BR>
     * レスポンスにセットする手動発注手数料情報を作成する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@return WEB3ManualCommissionInfoUnit
     * @@throws WEB3BaseException
     * @@roseuid 43F185940346
     */
    public WEB3ManualCommissionInfoUnit createManualCommissionInfoUnit(
        IfoOrderUnit l_orderUnit, 
        SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createManualCommissionInfoUnit(IfoOrderUnit, SubAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        
        WEB3IfoBizLogicProvider l_bizLogicProvider =
            (WEB3IfoBizLogicProvider) l_finApp.getTradingModule(ProductTypeEnum.IFO).getBizLogicProvider();
        //1.1 create手数料(注文単位ID : long)
        WEB3GentradeCommission l_commission = l_bizLogicProvider.createCommission(l_orderUnit.getOrderUnitId());
        
        //1.2 calc諸経費計算用代金(IfoOrderUnit)
        double l_dblExpensesCalcAmount = this.calcExpensesCalcAmount(l_orderUnit);
        
        //1.3 set諸経費計算用代金(諸経費計算用代金 : double)
        l_commission.setExpensesCalcAmount(l_dblExpensesCalcAmount);
        
        //1.4 setIs指値(is指値 : boolean)
        l_commission.setIsLimitPrice(false);  
        
        //1.5 calc委託手数料(手数料 : 手数料, 補助口座 : SubAccount)
        l_bizLogicProvider.calcCommission(l_commission, l_subAccount);
        
        //1.6 calc消費税(金額 : double, 基準日 : Timestamp, 補助口座 : 補助口座)
        double l_dblCommission = l_commission.getCommission();
        double l_dblSalesTax = l_bizLogicProvider.calcSalesTax(
            l_dblCommission,
            l_commission.getOrderBizDate(),
            l_subAccount);
        
        //1.7 手動発注手数料情報( )
        WEB3ManualCommissionInfoUnit l_commissionInfoUnit = new WEB3ManualCommissionInfoUnit();
        
        //1.8 (*)プロパティセット
        //手数料コース    ＝ 手数料.get手数料コースコード()の戻り値
        l_commissionInfoUnit.commissionCourse = l_commission.getCommissionCourseDiv();
        
        //手数料       ＝ 手数料.get手数料金額()の戻り値
        l_commissionInfoUnit.commission =
            WEB3StringTypeUtility.formatNumber(l_dblCommission);
        
        //手数料消費税    ＝ calc消費税()の戻り値
        l_commissionInfoUnit.commissionConsumptionTax =
            WEB3StringTypeUtility.formatNumber(l_dblSalesTax);
        
        log.exiting(STR_METHOD_NAME);
        return l_commissionInfoUnit;
    }
    
    /**
     * (calc諸経費計算用代金)<BR>
     * 手数料計算の為の諸経費計算用代金を取得する。<BR>
     * <BR>
     * １）　@以下の条件により分岐し、対応するメソッドをcallする。<BR>
     * <BR>
     * 　@[パラメータ.注文単位.注文カテゴリ ==<BR>
     * 　@　@"先物新規建注文" or "OP新規建注文"の場合]<BR>
     * 　@先物OP計算サービス.calc拘束売買代金()をcallする。<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@数量：　@引数.注文数量<BR>
     * 　@　@　@計算単価：　@引数.注文単価<BR>
     * 　@　@　@部店ID：　@引数.getBranchId( )の戻り値<BR>
     * 　@　@　@手数料商品コード：　@引数.手数料商品コード<BR>
     * 　@　@　@is指値：　@引数.isMarketOrder() == falseの場合はtrue<BR>
     * 　@　@　@　@　@　@　@　@　@　@引数.isMarketOrder() == trueの場合はfalse<BR>
     * 　@　@　@先物OP取引銘柄：　@引数.getTradedProduct( )の戻り値<BR>
     * <BR>
     * 　@[パラメータ.注文単位.注文カテゴリ ==<BR>
     * 　@　@"先物返済注文" or "OP返済注文の場合]<BR>
     * 　@先物OP計算サービス.calc売買代金()をcallする。<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@数量：　@引数.注文数量<BR>
     * 　@　@　@計算単価：　@引数.注文単価<BR>
     * 　@　@　@先物OP取引銘柄：　@引数.getTradedProduct( )の戻り値<BR>
     * <BR>
     * ２）　@１）の戻り値を返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@return double
     * throws WEB3BaseException
     * @@roseuid 43F1AFCD0302
     */
    public double calcExpensesCalcAmount(IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " calcExpensesCalcAmount(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        
        WEB3IfoBizLogicProvider l_bizLogicProvider =
            (WEB3IfoBizLogicProvider)l_finApp.getTradingModule(ProductTypeEnum.IFO).getBizLogicProvider();
        
        IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();
        double l_dblExpensesCalcAmount = 0;
        
        //１）　@以下の条件により分岐し、対応するメソッドをcallする。
        //[パラメータ.注文単位.注文カテゴリ ==
        //　@"先物新規建注文" or "OP新規建注文"の場合]
        //先物OP計算サービス.calc拘束売買代金()をcallする。
        if (OrderCategEnum.IDX_FUTURES_OPEN.equals(l_orderUnit.getOrderCateg())
            || OrderCategEnum.IDX_OPTIONS_OPEN.equals(l_orderUnit.getOrderCateg()))
        {
            boolean l_blnIsLimitPrice = false;
            if (l_orderUnit.isMarketOrder())
            {
                //is指値：　@引数.isMarketOrder() == trueの場合はfalse
                l_blnIsLimitPrice = false;
            }
            else
            {
                //is指値：　@引数.isMarketOrder() == falseの場合はtrue
                l_blnIsLimitPrice = true;
            }
            l_dblExpensesCalcAmount = l_bizLogicProvider.calcRestraintTurnOver(
                l_orderUnit.getQuantity(),
                l_orderUnitRow.getPrice(),
                l_orderUnit.getBranchId(),
                l_orderUnitRow.getCommProductCode(),
                l_blnIsLimitPrice,
                (WEB3IfoTradedProductImpl) l_orderUnit.getTradedProduct());
        }
        
        //[パラメータ.注文単位.注文カテゴリ ==
        //　@"先物返済注文" or "OP返済注文の場合]
        //先物OP計算サービス.calc売買代金()をcallする。
        else if (OrderCategEnum.IDX_FUTURES_CLOSE.equals(l_orderUnit.getOrderCateg())
            || OrderCategEnum.IDX_OPTIONS_CLOSE.equals(l_orderUnit.getOrderCateg()))
        {
            l_dblExpensesCalcAmount = l_bizLogicProvider.calcTurnOver(
                l_orderUnit.getQuantity(),
                l_orderUnitRow.getPrice(),
                (WEB3IfoTradedProductImpl) l_orderUnit.getTradedProduct());
        }
        
        //２）　@１）の戻り値を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_dblExpensesCalcAmount;
    }

    /**
     * (set取引カレンダコンテキスト)<BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR> 
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。<BR> 
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = パラメータ.証券会社コード<BR>
     * 　@取引カレンダコンテキスト.部店コード = パラメータ.部店コード<BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”11：株価指数先物OP”<BR> 
     * 　@取引カレンダコンテキスト.銘柄コード = パラメータ.銘柄コード<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = パラメータ.注文受付商品<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”01：買付(新規建)” <BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて <BR>
     * 　@　@　@取引時間コンテキストをセットする。 <BR>
     * 設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。 <BR>
     * 　@－取引時間管理.setTimestamp()をコールする。 <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード<BR>
     * @@param l_strOrderAccProduct - (注文受付商品)<BR>
     * 注文受付商品<BR>
     * @@param l_strOrderAcceptTransaction - (注文受付トランザクション)<BR>
     * 注文受付トランザクション<BR>
     * throws WEB3BaseException
     */
    public void setTradingCalendarContext(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strProductCode,
        String l_strOrderAccProduct,
        String l_strOrderAcceptTransaction) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "setTradingCalendarContext(" +
            "String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@取引カレンダコンテキストに内容をセットする。

        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
        //取引カレンダコンテキスト.証券会社コード = パラメータ.証券会社コード
        l_context.setInstitutionCode(l_strInstitutionCode);
        //取引カレンダコンテキスト.部店コード = パラメータ.部店コード
        l_context.setBranchCode(l_strBranchCode);
        //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
        l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
        //取引カレンダコンテキスト.受付時間区分 = ”11：株価指数先物OP”
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);
        //取引カレンダコンテキスト.銘柄コード = パラメータ.銘柄コード
        l_context.setProductCode(l_strProductCode);
        //取引カレンダコンテキスト.注文受付商品 = パラメータ.注文受付商品
        l_context.setOrderAcceptProduct(l_strOrderAccProduct);
        //取引カレンダコンテキスト.注文受付トランザクション = パラメータ.注文受付トランザクション
        l_context.setOrderAcceptTransaction(l_strOrderAcceptTransaction);

        //－ThreadLocalSystemAttributesRegistry.setAttribute( )にて
        //　@取引時間コンテキストをセットする。
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);

        //２）　@受付日時、日付ロールをセットする。
        //　@－取引時間管理.setTimestamp()をコールする。
        WEB3GentradeTradingTimeManagement.setTimestamp();

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create手動発注手数料情報)<BR>
     * （create手動発注手数料情報()のオーバーロード）<BR>
     * 引数の概算代金計算結果より手動発注手数料情報を作成する。<BR>
     * <BR>
     * １）　@手動発注手数料情報インスタンスを生成する。<BR>
     * <BR>
     * ２）　@生成したインスタンスに以下のプロパティをセットする。<BR>
     * 　@手数料コース：　@パラメータ.概算代金計算結果.手数料コース<BR>
     * 　@手数料：　@パラメータ.概算代金計算結果.手数料<BR>
     * 　@手数料消費税：　@パラメータ.概算代金計算結果.手数料消費税<BR>
     * <BR>
     * ３）　@プロパティセットしたインスタンスを返却する。<BR>
     * @@param l_estimateDeliveryAmountCalcResult - (先物OP概算受渡代金計算結果 概算代金計算結果)<BR>
     * 概算受渡代金計算結果オブジェクト<BR>
     * @@return WEB3ManualCommissionInfoUnit
     */
    public WEB3ManualCommissionInfoUnit createManualCommissionInfoUnit(
        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult)
    {
        //手動発注手数料情報インスタンスを生成する。
        WEB3ManualCommissionInfoUnit l_manualCommissionInfoUnit =
            new WEB3ManualCommissionInfoUnit();

        //生成したインスタンスに以下のプロパティをセットする。
        l_manualCommissionInfoUnit.commissionCourse = l_estimateDeliveryAmountCalcResult.getCommissionCourse();
        l_manualCommissionInfoUnit.commission =
            WEB3StringTypeUtility.formatNumber(l_estimateDeliveryAmountCalcResult.getCommission());
        l_manualCommissionInfoUnit.commissionConsumptionTax =
            WEB3StringTypeUtility.formatNumber(l_estimateDeliveryAmountCalcResult.getCommissionTax());

        return l_manualCommissionInfoUnit;
    }

    /**
     * (calcストップ注文切替後概算代金)<BR>
     * ストップ注文切替後の概算受渡代金を算出する。 <BR>
     * <BR>
     * １）　@手数料オブジェクトを作成する。 <BR>
     * 　@先物OP計算サービス.create手数料()をコールする。 <BR>
     * <BR>
     * 　@[create手数料()に指定する引数] <BR>
     * 　@　@注文単位ID：　@パラメータ.注文単位.注文単位ID <BR>
     * 　@　@数量：　@パラメータ.注文単位.注文数量 <BR>
     * <BR>
     * ２）　@作成した手数料.setIs指値()メソッドをコールする。 <BR>
     * <BR>
     * 　@[setIs指値()に指定する引数] <BR>
     * 　@　@is指値：　@パラメータ.注文単位.（W指値）訂正指値 == 0の場合、false。 <BR>
     * 　@　@　@以外、trueをセット。 <BR>
     * <BR>
     * ３）　@注文単位.先物／オプション区分 == "オプション"の場合 <BR>
     * 　@OP注文マネージャ.calc訂正時概算受渡代金()メソッドを <BR>
     * 　@コールする。 <BR>
     * <BR>
     * 　@[calc訂正時概算受渡代金()に指定する引数] <BR>
     * 　@　@手数料：　@作成した手数料オブジェクト <BR>
     * 　@　@指値：　@　@パラメータ.注文単位.（W指値）訂正指値 <BR>
     * 　@　@補助口座：　@パラメータ.補助口座 <BR>
     * 　@　@先物OP取引銘柄：　@パラメータ.注文単位.getTradedProduct() <BR>
     * 　@　@数量：　@パラメータ.注文単位.注文数量 <BR>
     * 　@　@売買：　@パラメータ.注文単位.getSide()  <BR>
     * 　@　@is返済注文： <BR>
     * 　@　@　@[パラメータ.注文単位.注文カテゴリ == "OP新規建注文"の場合] <BR>
     * 　@　@　@　@falseをセット。 <BR>
     * 　@　@　@[上記以外の場合] <BR>
     * 　@　@　@　@trueをセット。 <BR>
     * 　@　@約定数量：　@パラメータ.注文単位.約定数量 <BR>
     * 　@　@合計約定金額：　@パラメータ.注文単位.合計約定金額 <BR>
     * 　@　@isSkip金額チェック：　@false（スキップしない）固定 <BR>
     * <BR>
     * ４）　@上記以外（先物注文）の場合、 <BR>
     * 　@パラメータ.注文単位.注文カテゴリによってコールするメソッドを呼び分ける。 <BR>
     * 　@[パラメータ.注文単位.注文カテゴリ == "先物新規建注文"の場合] <BR>
     * 　@　@先物注文マネージャ.calc訂正時概算建代金()メソッドをコールする。 <BR>
     * <BR>
     * 　@　@[calc訂正時概算建代金()に指定する引数] <BR>
     * 　@　@　@手数料：　@作成した手数料オブジェクト <BR>
     * 　@　@　@指値：　@　@パラメータ.注文単位.（W指値）訂正指値 <BR>
     * 　@　@　@補助口座：　@パラメータ.補助口座 <BR>
     * 　@　@　@先物OP取引銘柄：　@パラメータ.注文単位.getTradedProduct() <BR>
     * 　@　@　@数量：　@パラメータ.注文単位.注文数量 <BR>
     * 　@　@　@約定数量：　@パラメータ.注文単位.約定数量 <BR>
     * 　@　@　@合計約定金額：　@パラメータ.注文単位.合計約定金額 <BR>
     * 　@　@　@isSkip金額チェック：　@false（スキップしない）固定 <BR>
     * <BR>
     * 　@[上記以外（返済注文）の場合] <BR>
     * 　@　@先物注文マネージャ.calc訂正時概算決済損益()メソッドをコールする。 <BR>
     * <BR>
     * 　@　@[calc訂正時概算決済損益()に指定する引数] <BR>
     * 　@　@　@手数料：　@作成した手数料オブジェクト <BR>
     * 　@　@　@指値：　@　@パラメータ.注文単位.（W指値）訂正指値 <BR>
     * 　@　@　@補助口座：　@パラメータ.補助口座 <BR>
     * 　@　@　@先物OP取引銘柄：　@パラメータ.注文単位.getTradedProduct() <BR>
     * 　@　@　@返済建玉エントリ： <BR>
     * 　@　@　@　@先物OPポジションマネージャ.create返済建玉エントリ(パラメータ.注文単位.注文単位ID) <BR>
     * 　@　@　@数量：　@パラメータ.注文単位.注文数量 <BR>
     * 　@　@　@約定数量：　@パラメータ.注文単位.約定数量 <BR>
     * 　@　@　@注文単位ID：　@パラメータ.注文単位.注文単位ID <BR>
     * 　@　@　@isSkip金額チェック：　@false（スキップしない）固定 <BR>
     * 　@　@　@売買： <BR>
     * 　@　@　@　@パラメータ.注文単位.getSide()＝SideEnum.BUY(買)（=売建買返済）の場合、”売”をセット。 <BR>
     * 　@　@　@　@パラメータ.注文単位.getSide()＝SideEnum.SELL(売)（=買建売返済）の場合、”買”をセット。 <BR>
     * <BR>
     * ５）　@各概算代金計算メソッドの戻り値を返却する。<BR>
     * @@param l_infoOrderUnit - (注文単位)<BR>
     * 注文単位オブジェクト
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@return WEB3IfoEstimateDeliveryAmountCalcResult
     * @@throws WEB3BaseException
     */
    public WEB3IfoEstimateDeliveryAmountCalcResult calcStopOrderSwitchOverEstimatedPrice(
        IfoOrderUnit l_infoOrderUnit,
        WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "calcStopOrderSwitchOverEstimatedPrice(" +
            "IfoOrderUnit, WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3IfoBizLogicProvider l_bizLogicProvider =
            (WEB3IfoBizLogicProvider)l_tradingModule.getBizLogicProvider();
        WEB3OptionOrderManagerImpl l_optionOrderManager =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
        WEB3FuturesOrderManagerImpl l_futuresOrderManager =
            (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();
        WEB3IfoPositionManagerImpl l_ifoPositionManagerImpl =
            (WEB3IfoPositionManagerImpl)l_tradingModule.getPositionManager();

        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_infoOrderUnit.getDataSourceObject();

        //１）　@手数料オブジェクトを作成する。
        //　@先物OP計算サービス.create手数料()をコールする。
        //　@[create手数料()に指定する引数]
        //　@　@注文単位ID：　@パラメータ.注文単位.注文単位ID
        //　@　@数量：　@パラメータ.注文単位.注文数量
        WEB3GentradeCommission l_commission =
            l_bizLogicProvider.createCommission(
                l_infoOrderUnit.getOrderUnitId(), l_infoOrderUnit.getQuantity());

        //２）　@作成した手数料.setIs指値()メソッドをコールする。
        //　@[setIs指値()に指定する引数]
        //　@　@is指値：　@パラメータ.注文単位.（W指値）訂正指値 == 0の場合、false。
        //　@　@　@以外、trueをセット。
        boolean l_blnIsLimitPrice = true;
        if (l_ifoOrderUnitRow.getWLimitPrice() == 0)
        {
            l_blnIsLimitPrice = false;
        }

        l_commission.setIsLimitPrice(l_blnIsLimitPrice);

        //３）　@注文単位.先物／オプション区分 == "オプション"の場合
        //　@OP注文マネージャ.calc訂正時概算受渡代金()メソッドを
        //　@コールする。
        //　@[calc訂正時概算受渡代金()に指定する引数]
        //　@　@手数料：　@作成した手数料オブジェクト
        //　@　@指値：　@　@パラメータ.注文単位.（W指値）訂正指値
        //　@　@補助口座：　@パラメータ.補助口座
        //　@　@先物OP取引銘柄：　@パラメータ.注文単位.getTradedProduct()
        //　@　@数量：　@パラメータ.注文単位.注文数量
        //　@　@売買：　@パラメータ.注文単位.getSide()
        //　@　@is返済注文：
        //　@　@　@[パラメータ.注文単位.注文カテゴリ == "OP新規建注文"の場合]
        //　@　@　@　@falseをセット。
        //　@　@　@[上記以外の場合]
        //　@　@　@　@trueをセット。
        //　@　@約定数量：　@パラメータ.注文単位.約定数量
        //　@　@合計約定金額：　@パラメータ.注文単位.合計約定金額
        //　@　@isSkip金額チェック：　@false（スキップしない）固定
        boolean l_blnIsClosingContractOrder = true;
        WEB3IfoEstimateDeliveryAmountCalcResult l_calcResult = null;
        if (OrderCategEnum.IDX_OPTIONS_OPEN.equals(l_ifoOrderUnitRow.getOrderCateg()))
        {
            l_blnIsClosingContractOrder = false;
        }
        if (WEB3FuturesOptionDivDef.OPTION.equals(l_ifoOrderUnitRow.getFutureOptionDiv()))
        {
            l_calcResult =
                l_optionOrderManager.calcChangeEstimateDeliveryAmount(
                    l_commission,
                    l_ifoOrderUnitRow.getWLimitPrice(),
                    l_subAccount,
                    (WEB3IfoTradedProductImpl)l_infoOrderUnit.getTradedProduct(),
                    l_infoOrderUnit.getQuantity(),
                    l_infoOrderUnit.getSide(),
                    l_blnIsClosingContractOrder,
                    l_ifoOrderUnitRow.getExecutedQuantity(),
                    l_ifoOrderUnitRow.getExecutedAmount(),
                    false);
        }

        //４）　@上記以外（先物注文）の場合、
        //　@パラメータ.注文単位.注文カテゴリによってコールするメソッドを呼び分ける。
        //　@[パラメータ.注文単位.注文カテゴリ == "先物新規建注文"の場合]
        //　@　@先物注文マネージャ.calc訂正時概算建代金()メソッドをコールする。
        //　@　@[calc訂正時概算建代金()に指定する引数]
        //　@　@　@手数料：　@作成した手数料オブジェクト
        //　@　@　@指値：　@　@パラメータ.注文単位.（W指値）訂正指値
        //　@　@　@補助口座：　@パラメータ.補助口座
        //　@　@　@先物OP取引銘柄：　@パラメータ.注文単位.getTradedProduct()
        //　@　@　@数量：　@パラメータ.注文単位.注文数量
        //　@　@　@約定数量：　@パラメータ.注文単位.約定数量
        //　@　@　@合計約定金額：　@パラメータ.注文単位.合計約定金額
        //　@　@　@isSkip金額チェック：　@false（スキップしない）固定
        else
        {
            if (OrderCategEnum.IDX_FUTURES_OPEN.equals(l_ifoOrderUnitRow.getOrderCateg()))
            {
                l_calcResult =
                    l_futuresOrderManager.calcChangeEstimatePrice(
                        l_commission,
                        l_ifoOrderUnitRow.getWLimitPrice(),
                        l_subAccount,
                        (WEB3IfoTradedProductImpl)l_infoOrderUnit.getTradedProduct(),
                        l_infoOrderUnit.getQuantity(),
                        l_ifoOrderUnitRow.getExecutedQuantity(),
                        l_ifoOrderUnitRow.getExecutedAmount(),
                        false);
            }
            //　@[上記以外（返済注文）の場合]
            //　@　@先物注文マネージャ.calc訂正時概算決済損益()メソッドをコールする。
            //　@　@[calc訂正時概算決済損益()に指定する引数]
            //　@　@　@手数料：　@作成した手数料オブジェクト
            //　@　@　@指値：　@　@パラメータ.注文単位.（W指値）訂正指値
            //　@　@　@補助口座：　@パラメータ.補助口座
            //　@　@　@先物OP取引銘柄：　@パラメータ.注文単位.getTradedProduct()
            //　@　@　@返済建玉エントリ：
            //　@　@　@　@先物OPポジションマネージャ.create返済建玉エントリ(パラメータ.注文単位.注文単位ID)
            //　@　@　@数量：　@パラメータ.注文単位.注文数量
            //　@　@　@約定数量：　@パラメータ.注文単位.約定数量
            //　@　@　@注文単位ID：　@パラメータ.注文単位.注文単位ID
            //　@　@　@isSkip金額チェック：　@false（スキップしない）固定
            //　@　@　@売買：
            //　@　@　@　@パラメータ.注文単位.getSide()＝SideEnum.BUY(買)（=売建買返済）の場合、”売”をセット。
            //　@　@　@　@パラメータ.注文単位.getSide()＝SideEnum.SELL(売)（=買建売返済）の場合、”買”をセット。
            else
            {
                SideEnum l_dealing = null;
                if (SideEnum.BUY.equals(l_infoOrderUnit.getSide()))
                {
                    l_dealing = SideEnum.SELL;
                }
                else if (SideEnum.SELL.equals(l_infoOrderUnit.getSide()))
                {
                    l_dealing = SideEnum.BUY;
                }

                SettleContractEntry[] l_settleContractEntry =
                    l_ifoPositionManagerImpl.createSettleContractEntry(l_ifoOrderUnitRow.getOrderUnitId());
                l_calcResult =
                    l_futuresOrderManager.calcChangeEstimateSettlementIncome(
                        l_commission,
                        l_ifoOrderUnitRow.getWLimitPrice(),
                        l_subAccount,
                        (WEB3IfoTradedProductImpl)l_infoOrderUnit.getTradedProduct(),
                        l_settleContractEntry,
                        l_infoOrderUnit.getQuantity(),
                        l_dealing,
                        l_ifoOrderUnitRow.getExecutedQuantity(),
                        l_ifoOrderUnitRow.getOrderUnitId(),
                        false);
            }
        }

        //５）　@各概算代金計算メソッドの戻り値を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_calcResult;
    }
}
@
