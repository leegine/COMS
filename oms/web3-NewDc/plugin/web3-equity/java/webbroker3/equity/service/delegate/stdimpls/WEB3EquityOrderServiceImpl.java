head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文サービスImpl(WEB3EquityOrderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/17 李綱 (中訊) 新規作成
Revesion History : 2004/07/12 鄒政 (中訊) 修正
Revesion History : 2004/12/20 中尾寿彦(SRA) 残案件対応による修正
Revesion History : 2005/01/06 岡村和明(SRA) JavaDoc修正
Revesion History : 2006/11/02 唐性峰　@(中訊)モデルNo.988,991,992
Revesion History : 2006/11/14 唐性峰　@(中訊)モデルNo.1026
Revesion History : 2006/12/25 柴双紅(中訊) モデルNo.1091,No.1097
Revesion History : 2007/01/17 唐性峰　@(中訊)モデルNo.1107
Revesion History : 2007/06/14 何文敏　@(中訊)モデルNo.1173
Revesion History : 2007/08/07 周墨洋　@(中訊)モデルNo.1192
Revesion History : 2007/12/04 趙林鵬　@(中訊)モデルNo.1227
Revesion History : 2007/12/10 趙林鵬  (中訊)モデルNo.1240
Revesion History : 2008/10/06 劉剣 (中訊) モデルNo.1323
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewCashBasedOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityClientRequestService;
import webbroker3.equity.WEB3EquityEstimatedDeliveryPrice;
import webbroker3.equity.WEB3EquityNewCashBasedOrderSpec;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityOrderManagerPersistenceEventInterceptor;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.message.WEB3EquityBuyCompleteRequest;
import webbroker3.equity.message.WEB3EquityBuyCompleteResponse;
import webbroker3.equity.message.WEB3EquityBuyConfirmRequest;
import webbroker3.equity.message.WEB3EquityBuyConfirmResponse;
import webbroker3.equity.message.WEB3EquityCommissionInfoUnit;
import webbroker3.equity.message.WEB3EquityCommonRequest;
import webbroker3.equity.message.WEB3EquitySellCompleteRequest;
import webbroker3.equity.message.WEB3EquitySellCompleteResponse;
import webbroker3.equity.message.WEB3EquitySellConfirmRequest;
import webbroker3.equity.message.WEB3EquitySellConfirmResponse;
import webbroker3.equity.service.delegate.WEB3EquityOrderRequestAdapter;
import webbroker3.equity.service.delegate.WEB3EquityOrderService;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.tradingpower.define.WEB3TPTradingPowerErrorDivDef;
import webbroker3.tradingpower.define.WEB3TPResultAttentionObjectionTypeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3TradingTypeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;


/**
 * （株式注文サービスImpl）。<BR>
 * <BR>
 * 国内株式注文ユースケースのエントリポイント
 * @@version 1.0
 */
public class WEB3EquityOrderServiceImpl
    extends WEB3EquityClientRequestService
    implements WEB3EquityOrderService
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderServiceImpl.class);
    
    /**
     * @@roseuid 40A02D070251
     */
    public WEB3EquityOrderServiceImpl()
    {
    }

    /**
     * (validate注文) <BR>
     * <BR>
     * 現物株式注文確認処理を実施する。<BR>
     * シーケンス図「（株式注文サービス）注文入力確認」参照。<BR>
     * <BR>
     * @@param l_request - (入力データ) <BR>
     * クライアントからのリクエストメッセージを指定する。 <BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 3FFD338F00FA
     */
    protected WEB3GenResponse validateOrder(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityCommonRequest l_commonRequest = (WEB3EquityCommonRequest)l_request;
        
        //1.1. validate()
        l_commonRequest.validate();
        
        //1.2. get補助口座()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //1.3. get代理入力者()
        WEB3GentradeTrader l_trader = (WEB3GentradeTrader)this.getTrader();
        
        //1.4. getログインチャネル()
        String l_loginChannel = this.getLoginChannel();
        
        //1.5. createリクエストアダプタ()
        WEB3EquityOrderRequestAdapter l_adapter =
            this.createRequestAdapter(l_request);
        
        //1.6. get銘柄コード()
        String l_strProductCode = l_adapter.getProductCode();

        //1.8. get執行条件()
        EqTypeExecutionConditionType l_execCondType = l_adapter.getExecCondType();
        
        //1.9. get税区分()
        TaxTypeEnum l_taxType = l_adapter.getTaxDivision();
        
        //1.10. is売注文()
        boolean l_blnIsSellOrder = l_adapter.isSellOrder();
        
        // get（Ｗ指値）執行条件( )
        //（Ｗ指値）執行条件を取得する。
        EqTypeExecutionConditionType l_wLimitExecCondType = l_adapter.getWLimitExecCondType();

        //get市場コード( )
        String l_strRequestMarketCode = l_adapter.getMarketCode();
        if (l_request instanceof WEB3EquityBuyConfirmRequest)
        {
            ((WEB3EquityBuyConfirmRequest)l_request).marketCode = l_strRequestMarketCode;
        }
        else
        {
            ((WEB3EquitySellConfirmRequest)l_request).marketCode = l_strRequestMarketCode;
        }
        //reset市場コード(市場コード : String)
        //引数は以下の通りに設定する。
        //市場コード：　@リクエスト.市場コード
        WEB3GentradeTradingTimeManagement.resetMarketCode(l_strRequestMarketCode);

        //get単価()
        double l_dblPrice = l_adapter.getPrice();

        //1.11. create注文内容()
        String l_strOrderCondOperator = null;
        double l_dblStopOrderBasePrice = 0.0D;
        double l_dblWLimitOrderChange = 0.0D;
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_commonRequest.orderCondType))
        {
            //発注条件演算子
            l_strOrderCondOperator = null;
            //逆指値基準値
            l_dblStopOrderBasePrice = 0.0D;
            //（W指値）訂正指値
            l_dblWLimitOrderChange = 0.0D;
            //（Ｗ指値）執行条件：
            //リクエスト.発注条件区分＝（”指定なし”、”逆指値”）の場合は、nullをセット。
            l_wLimitExecCondType = null;
        }
        else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_commonRequest.orderCondType))
        {
            //発注条件演算子
            l_strOrderCondOperator = l_commonRequest.stopOrderCondOperator;
            //逆指値基準値
            l_dblStopOrderBasePrice = Double.parseDouble(l_commonRequest.stopOrderCondPrice);
            //（W指値）訂正指値
            l_dblWLimitOrderChange = 0.0D;;
            //（Ｗ指値）執行条件：
            //リクエスト.発注条件区分＝（”指定なし”、”逆指値”）の場合は、nullをセット。
            l_wLimitExecCondType = null;
        }
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_commonRequest.orderCondType))
        {
            //発注条件演算子
            l_strOrderCondOperator = l_commonRequest.wlimitOrderCondOperator;
            //逆指値基準値
            l_dblStopOrderBasePrice = Double.parseDouble(l_commonRequest.wlimitOrderCondPrice);
            //（W指値）訂正指値
            if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_commonRequest.wLimitOrderPriceDiv))
            {
                l_dblWLimitOrderChange = Double.parseDouble(l_commonRequest.wLimitPrice);
            }
            else
            {
                l_dblWLimitOrderChange = 0.0D;
            }
        }

        Timestamp l_tsExpirationDate = null;
        Long l_firstOrderUnitId = null;
        if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_commonRequest.expirationDateType))
        {
            l_tsExpirationDate = new Timestamp(WEB3GentradeTradingTimeManagement.getOrderBizDate().getTime());
            l_firstOrderUnitId = null;
        }
        else if (l_commonRequest.expirationDateType.equals(WEB3OrderExpirationDateTypeDef.CARRIED_ORDER))
        {
            l_tsExpirationDate = new Timestamp(l_adapter.getExpirationDate().getTime());
            l_firstOrderUnitId = new Long(0);
        }

        String l_strMarketCode;
        String l_strPriceConditionType;
        if (l_request instanceof WEB3EquityBuyConfirmRequest)
        {
            l_strMarketCode = ((WEB3EquityBuyConfirmRequest)l_request).marketCode;
            l_strPriceConditionType = ((WEB3EquityBuyConfirmRequest)l_request).priceCondType;
        }
        else
        {
            l_strMarketCode = ((WEB3EquitySellConfirmRequest)l_request).marketCode;
            l_strPriceConditionType = ((WEB3EquitySellConfirmRequest)l_request).priceCondType;
        }
        WEB3EquityNewCashBasedOrderSpec l_orderSpec =
            WEB3EquityNewCashBasedOrderSpec.createOrderSpec(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_trader,
                l_strMarketCode,
                l_strProductCode,
                Double.parseDouble(l_commonRequest.orderQuantity),
                l_dblPrice,
                l_execCondType,
                l_taxType,
                l_tsExpirationDate,
                l_blnIsSellOrder,
                l_loginChannel,
                l_strPriceConditionType,
                l_commonRequest.orderCondType,
                l_strOrderCondOperator,
                l_dblStopOrderBasePrice,
                l_dblWLimitOrderChange,
                l_firstOrderUnitId,
                l_wLimitExecCondType);

        //1.12. set手数料商品コード()
        l_orderSpec.setCommissionProductCode(WEB3CommisionProductCodeDef.LISTING_STOCK);

        //1.13. create手数料()
        String l_strSonarTradedCode;
        String l_strTradingType;
        if (l_request instanceof WEB3EquityBuyConfirmRequest)
        {
            l_strTradingType = ((WEB3EquityBuyConfirmRequest)l_request).tradingType;
            if (WEB3TradingTypeDef.BUY_ORDER.equals(l_strTradingType))
            {
                l_strSonarTradedCode = WEB3TransactionTypeSONARDef.MARKET_TRADING;
            }
            else
            {
                l_strSonarTradedCode = WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET;
            }
        }
        else
        {
            l_strSonarTradedCode = WEB3TransactionTypeSONARDef.MARKET_TRADING;
        }
        WEB3GentradeCommission l_commission =
            l_orderSpec.createCommission(
                this.getMainAccount().getBranch(),
                l_strSonarTradedCode);
        l_orderSpec.setCommissionProductCode(l_commission.getCommissionProductCode());
        
        //1.14. validate現物株式注文()
        EqTypeNewOrderValidationResult l_eqNewOrderValidationResult =
            this.validateNewCashBasedOrder(l_orderSpec, l_adapter);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        WEB3EquityTradedProduct l_tradedProduct = null;

        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        //throw発注審査結果エラー情報(OrderValidationResult, 証券会社, String)
        //発注審査結果：　@validate現物株式注文()の戻り値
        //証券会社：　@補助口座.getInstitution()
        //銘柄コード：　@リクエストアダプタ.get銘柄コード()
        l_orderManager.throwOrderValidationResultErrorInfo(
            l_eqNewOrderValidationResult,
            l_subAccount.getInstitution(),
            l_strProductCode);

        //1.15. validate売付可能数量()
        this.validateSellableAssetQuantity(l_adapter);

        //validate機@構預託同意(補助口座)
        //補助口座：　@get補助口座()の戻り値
        l_orderManager.validateMechanismDepositAgree(l_subAccount);

        //getTradedProduct()
        try
        {
            l_tradedProduct =
                (WEB3EquityTradedProduct)l_productManager.getTradedProduct(
                    l_subAccount.getInstitution(),
                    l_orderSpec.getProductCode(),
                    l_orderSpec.getMarketCode());
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);                
        }

        //1.18. getQuantity()
        double l_dblQuantity = l_orderSpec.getQuantity();
        
        //1.22. calc概算受渡代金()
        //概算受渡代金を計算する。
        //引数は以下の通りに設定する。

        //手数料 : 株式注文内容.get手数料( )
        //指値 : 株式注文内容.getLimitPrice()
        //（W指値）訂正指値 : 株式注文内容.get（W指値）訂正指値()
        //逆指値基準値 : 株式注文内容.get逆指値基準値()
        //執行条件 : 株式注文内容.getExecConditionType()
        //（W指値）執行条件 : 株式注文内容.get（W指値）執行条件()
        //値段条件 : 株式注文内容.get値段条件()
        //発注条件 : 株式注文内容.get発注条件()
        //確認時取得時価 : null
        //isストップ注文有効 : false（固定）
        //補助口座 : this.get補助口座()
        //取引銘柄 : 拡張プロダクトマネージャ.getTradedProduct()
        //株数 : 株式注文内容.getQuantity( )
        //is売注文 : 株式注文内容.isSellOrder( )
        //約定数量 : 0
        //合計約定金額 : 0
        //isSkip金額チェック : false（固定）
        WEB3EquityEstimatedDeliveryPrice l_estimatedDeliveryPrice =
            l_orderManager.calcEstimateDeliveryAmount(
                l_commission,
                l_orderSpec.getLimitPrice(),
                l_orderSpec.getWLimitPriceChange(),
                l_orderSpec.getStopLimitPriceBasePrice(),
                l_orderSpec.getExecConditionType(),
                l_orderSpec.getWlimitExecCondType(),
                l_orderSpec.getPriceConditionType(),
                l_orderSpec.getOrderCond(),
                null,
                false,
                l_subAccount,
                l_tradedProduct,
                l_dblQuantity,
                l_blnIsSellOrder,
                0.0D,
                0.0D,
                false);

        //set注文単価(double)
        //注文単価をセットする。
        //概算受渡代金計算結果.get計算単価()をセット。
        l_orderSpec.setOrderUnitPrice(l_estimatedDeliveryPrice.getCalcUnitPrice());

        //1.23. set概算受渡代金()
        l_orderSpec.setEstimateDeliveryAmount(l_estimatedDeliveryPrice.getEstimateDeliveryAmount());
        
        //1.24. validate取引余力()
        WEB3TPTradingPowerResult l_tpResult =
            this.validateTradingPower(
                l_subAccount,
                l_orderSpec,
                false,
                l_tradedProduct);
        
        //1.25. createNewOrderId()
        long l_lngOrderId = l_orderManager.createNewOrderId();
        
        //1.26. createResponse()
        WEB3GenResponse l_response = l_request.createResponse();

        //1.27. get市場閉局警告市場()
        String[] l_strMessageSuspensions =
            WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
                (WEB3GentradeBranch)l_subAccount.getMainAccount().getBranch(),
                ProductTypeEnum.EQUITY,
                WEB3MarginTradingDivDef.DEFAULT);
        
        //1.28. isインサイダー警告表示()
        boolean l_blnIsInsiderMessageSuspension =
            l_orderManager.isInsiderMessageSuspension(
                l_subAccount,
                l_tradedProduct.getProduct().getProductId());
        
        //1.29. 売注文の場合のみ実行
        String l_strEstimatedBookPrice = null;
        if (l_blnIsSellOrder)
        {   
            l_strEstimatedBookPrice = this.getEstimatedBookPrice(l_adapter);
        }

        //set単価(株式注文リクエストアダプタ, WEB3GenResponse)
        //引数は以下の通りに設定する。
        //株式注文リクエストアダプタ：　@生成した同名オブジェクト
        //レスポンス：生成したレスポンス
        setPrice(l_adapter, l_response);

        //1.30. プロパティセット
        if (l_response instanceof WEB3EquityBuyConfirmResponse)
        {
            WEB3EquityBuyConfirmResponse l_buyConfirmResponse =
                (WEB3EquityBuyConfirmResponse)l_response;
            //レスポンス.確認時発注日
            Date l_bizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            l_buyConfirmResponse.checkDate = WEB3DateUtility.toDay(l_bizDate);
            //レスポンス.概算受渡代金
            l_buyConfirmResponse.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_orderSpec.getEstimateDeliveryAmount());
            //レスポンス.取引終了警告市場コード一覧
            l_buyConfirmResponse.messageSuspension = l_strMessageSuspensions;
            //レスポンス.確認時単価
            //calc概算受渡代金( )の戻り値.get確認時取得時価( )の戻り値
            l_buyConfirmResponse.checkPrice = l_estimatedDeliveryPrice.getCheckGetCurrentPrice();
            WEB3EquityCommissionInfoUnit l_commissionInfo =
                new WEB3EquityCommissionInfoUnit();
            // 手数料コース
            l_commissionInfo.commissionCourse = l_commission.getCommissionCourseDiv();
            // 手数料
            l_commissionInfo.commission =
                WEB3StringTypeUtility.formatNumber(l_estimatedDeliveryPrice.getCommissionFee());
            // 手数料消費税
            l_commissionInfo.commissionConsumptionTax =
                WEB3StringTypeUtility.formatNumber(l_estimatedDeliveryPrice.getCommissionFeeTax());
            //レスポンス.手数料情報
            l_buyConfirmResponse.commissionInfo = l_commissionInfo;
            //レスポンス.注文ID
            l_buyConfirmResponse.orderId = String.valueOf(l_lngOrderId);
            //レスポンス.インサイダー警告表示フラグ
            l_buyConfirmResponse.insiderWarningFlag = l_blnIsInsiderMessageSuspension;
            //レスポンス.注意文言表示区分
            //レスポンス.預り金不足額
            //取引余力結果.get注意文言表示区分 == "3：預り金不足注意文言表示" または
            //取引余力結果.get注意文言表示区分 == "1：現金不足注意文言表示"の場合
            //取引余力結果クラスの同項目をセットする
            if (l_tpResult != null)
            {
                l_buyConfirmResponse.attentionObjectionType = l_tpResult.getAttentionObjectionType();
                if (WEB3TPResultAttentionObjectionTypeDef.LACK_ACC_BAL_ATTENTION.equals(
                        l_tpResult.getAttentionObjectionType())
                    || WEB3TPResultAttentionObjectionTypeDef.LACK_CASH_AMT_ATTENTION.equals(
                        l_tpResult.getAttentionObjectionType()))
                {
                    l_buyConfirmResponse.accountBalanceInsufficiency =
                        WEB3StringTypeUtility.formatNumber(l_tpResult.getLackAccountBalance());
                }
            }
            //市場コード
            l_buyConfirmResponse.marketCode = l_strRequestMarketCode;
            //レスポンス.銘柄名
			EqtypeProductRow l_productRow = (EqtypeProductRow)l_tradedProduct.getProduct().getDataSourceObject();
            l_buyConfirmResponse.productName = l_productRow.getStandardName();
            // レスポンス.注文有効期限
            l_buyConfirmResponse.expirationDate = l_adapter.getExpirationDate();
        }
        else
        {
            WEB3EquitySellConfirmResponse l_sellConfirmResponse =
                (WEB3EquitySellConfirmResponse)l_response;
            //レスポンス.確認時発注日
            Date l_bizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            l_sellConfirmResponse.checkDate = WEB3DateUtility.toDay(l_bizDate);
            //レスポンス.概算受渡代金
            l_sellConfirmResponse.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_orderSpec.getEstimateDeliveryAmount());
            //レスポンス.取引終了警告市場コード一覧
            l_sellConfirmResponse.messageSuspension = l_strMessageSuspensions;
            //レスポンス.確認時単価
            //calc概算受渡代金( )の戻り値.get確認時取得時価( )の戻り値
            l_sellConfirmResponse.checkPrice = l_estimatedDeliveryPrice.getCheckGetCurrentPrice();
            WEB3EquityCommissionInfoUnit l_commissionInfo =
                new WEB3EquityCommissionInfoUnit();
            // 手数料コース
            l_commissionInfo.commissionCourse = l_commission.getCommissionCourseDiv();
            // 手数料
            l_commissionInfo.commission =
                WEB3StringTypeUtility.formatNumber(l_estimatedDeliveryPrice.getCommissionFee());
            // 手数料消費税
            l_commissionInfo.commissionConsumptionTax =
                WEB3StringTypeUtility.formatNumber(l_estimatedDeliveryPrice.getCommissionFeeTax());
            //レスポンス.手数料情報
            l_sellConfirmResponse.commissionInfo = l_commissionInfo;
            //レスポンス.注文ID
            l_sellConfirmResponse.orderId = String.valueOf(l_lngOrderId);
            //レスポンス.インサイダー警告表示フラグ
            l_sellConfirmResponse.insiderWarningFlag = l_blnIsInsiderMessageSuspension;
            //レスポンス.注意文言表示区分
            //レスポンス.預り金不足額
            //取引余力結果.get注意文言表示区分 == "3：預り金不足注意文言表示" または
            //取引余力結果.get注意文言表示区分 == "1：現金不足注意文言表示"の場合
            //取引余力結果クラスの同項目をセットする
            if (l_tpResult != null)
            {
                l_sellConfirmResponse.attentionObjectionType = l_tpResult.getAttentionObjectionType();
                if (WEB3TPResultAttentionObjectionTypeDef.LACK_ACC_BAL_ATTENTION.equals(
                        l_tpResult.getAttentionObjectionType())
                    || WEB3TPResultAttentionObjectionTypeDef.LACK_CASH_AMT_ATTENTION.equals(
                        l_tpResult.getAttentionObjectionType()))
                {
                    l_sellConfirmResponse.accountBalanceInsufficiency =
                        WEB3StringTypeUtility.formatNumber(l_tpResult.getLackAccountBalance());
                }
            }
            //市場コード
            l_sellConfirmResponse.marketCode = l_strRequestMarketCode;
            //レスポンス.概算簿価単価
            l_sellConfirmResponse.estimatedBookPrice = l_strEstimatedBookPrice;
            // レスポンス.注文有効期限
            l_sellConfirmResponse.expirationDate = l_adapter.getExpirationDate();
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit注文)<BR>
     * <BR>
     * 現物株式注文を登録する。<BR>
     * シーケンス図「（株式注文サービス）注文登録更新」参照。<BR>
     * <BR>
     * @@param l_request - (入力データ) <BR>
     * クライアントからのリクエストメッセージを指定する。 <BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 3FFD343B0271
     */
    protected WEB3GenResponse submitOrder(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOrder(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityCommonRequest l_commonRequest = (WEB3EquityCommonRequest)l_request;
        
        //1.1. validate()
        l_commonRequest.validate();

        //1.3. get補助口座()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //1.4. get代理入力者()
        WEB3GentradeTrader l_trader = (WEB3GentradeTrader)this.getTrader();
        
        //1.5. getログインチャネル()
        String l_loginChannel = this.getLoginChannel();
        
        //1.6. createリクエストアダプタ()
        WEB3EquityOrderRequestAdapter l_adapter =
            this.createRequestAdapter(l_request);
        
        //1.7. get銘柄コード()
        String l_strProductCode = l_adapter.getProductCode();

        //1.9. get執行条件()
        EqTypeExecutionConditionType l_execCondType = l_adapter.getExecCondType();
        
        //1.10. get税区分()
        TaxTypeEnum l_taxType = l_adapter.getTaxDivision();

        //1.11. is売注文()
        boolean l_blnIsSellOrder = l_adapter.isSellOrder();

        //get（Ｗ指値）執行条件( )
        //（Ｗ指値）執行条件を取得する。
        EqTypeExecutionConditionType l_wLimitExecCondType = l_adapter.getWLimitExecCondType();

        //get市場コード( )
        String l_strRequestMarketCode = l_adapter.getMarketCode();
        if (l_request instanceof WEB3EquityBuyCompleteRequest)
        {
            ((WEB3EquityBuyCompleteRequest)l_request).marketCode = l_strRequestMarketCode;
        }
        else if (l_request instanceof WEB3EquitySellCompleteRequest)
        {
            ((WEB3EquitySellCompleteRequest)l_request).marketCode = l_strRequestMarketCode;
        }
        //reset市場コード(市場コード : String)
        WEB3GentradeTradingTimeManagement.resetMarketCode(l_strRequestMarketCode);

        //get単価()
        double l_dblPrice = l_adapter.getPrice();

        //get発注日()
        Date l_datCheckDate;
        if (l_request instanceof WEB3EquityBuyCompleteRequest)
        {
            if (((WEB3EquityBuyCompleteRequest)l_request).checkDate == null)
            {
                ((WEB3EquityBuyCompleteRequest)l_request).checkDate =
                    WEB3GentradeTradingTimeManagement.getOrderBizDate();
            }
            l_datCheckDate = ((WEB3EquityBuyCompleteRequest)l_request).checkDate;
        }
        else
        {
            if (((WEB3EquitySellCompleteRequest)l_request).checkDate == null)
            {
                ((WEB3EquitySellCompleteRequest)l_request).checkDate =
                    WEB3GentradeTradingTimeManagement.getOrderBizDate();
            }
            l_datCheckDate = ((WEB3EquitySellCompleteRequest)l_request).checkDate;
        }
        WEB3GentradeTradingTimeManagement.getOrderBizDate(l_datCheckDate);

        //1.12. create注文内容()
        String l_strOrderCondOperator = null;
        double l_dblStopOrderBasePrice = 0.0D;
        double l_dblWLimitOrderChange = 0.0D;
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_commonRequest.orderCondType))
        {
            //発注条件演算子
            l_strOrderCondOperator = null;
            //逆指値基準値
            l_dblStopOrderBasePrice = 0.0D;
            //（W指値）訂正指値
            l_dblWLimitOrderChange = 0.0D;
            //（Ｗ指値）執行条件：
            //リクエスト.発注条件区分＝（”指定なし”、”逆指値”）の場合は、nullをセット。
            l_wLimitExecCondType = null;
        }
        else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_commonRequest.orderCondType))
        {
            //発注条件演算子
            l_strOrderCondOperator = l_commonRequest.stopOrderCondOperator;
            //逆指値基準値
            l_dblStopOrderBasePrice = Double.parseDouble(l_commonRequest.stopOrderCondPrice);
            //（W指値）訂正指値
            l_dblWLimitOrderChange = 0.0D;;
            //（Ｗ指値）執行条件：
            //リクエスト.発注条件区分＝（”指定なし”、”逆指値”）の場合は、nullをセット。
            l_wLimitExecCondType = null;
        }
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_commonRequest.orderCondType))
        {
            //発注条件演算子
            l_strOrderCondOperator = l_commonRequest.wlimitOrderCondOperator;
            //逆指値基準値
            l_dblStopOrderBasePrice = Double.parseDouble(l_commonRequest.wlimitOrderCondPrice);
            //（W指値）訂正指値
            if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_commonRequest.wLimitOrderPriceDiv))
            {
                l_dblWLimitOrderChange = Double.parseDouble(l_commonRequest.wLimitPrice);
            }
            else
            {
                l_dblWLimitOrderChange = 0.0D;
            }
        }

        Timestamp l_tsExpirationDate = null;
        Long l_firstOrderUnitId = null;
        if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_commonRequest.expirationDateType))
        {
            l_tsExpirationDate = new Timestamp(WEB3GentradeTradingTimeManagement.getOrderBizDate().getTime());
            l_firstOrderUnitId = null;
        }
        else if (l_commonRequest.expirationDateType.equals(WEB3OrderExpirationDateTypeDef.CARRIED_ORDER))
        {
            l_tsExpirationDate = new Timestamp(l_adapter.getExpirationDate().getTime());
            l_firstOrderUnitId = new Long(0);
        }

        String l_strMarketCode;
        String l_strPriceConditionType;
        if (l_request instanceof WEB3EquityBuyCompleteRequest)
        {
            l_strMarketCode = ((WEB3EquityBuyCompleteRequest)l_request).marketCode;
            l_strPriceConditionType = ((WEB3EquityBuyCompleteRequest)l_request).priceCondType;
        }
        else
        {
            l_strMarketCode = ((WEB3EquitySellCompleteRequest)l_request).marketCode;
            l_strPriceConditionType = ((WEB3EquitySellCompleteRequest)l_request).priceCondType;
        }
        WEB3EquityNewCashBasedOrderSpec l_orderSpec =
            WEB3EquityNewCashBasedOrderSpec.createOrderSpec(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_trader,
                l_strMarketCode,
                l_strProductCode,
                Double.parseDouble(l_commonRequest.orderQuantity),
                l_dblPrice,
                l_execCondType,
                l_taxType,
                l_tsExpirationDate,
                l_blnIsSellOrder,
                l_loginChannel,
                l_strPriceConditionType,
                l_commonRequest.orderCondType,
                l_strOrderCondOperator,
                l_dblStopOrderBasePrice,
                l_dblWLimitOrderChange,
                l_firstOrderUnitId,
                l_wLimitExecCondType);

        //1.13. set手数料商品コード()
        l_orderSpec.setCommissionProductCode(WEB3CommisionProductCodeDef.LISTING_STOCK);

        //1.14. create手数料()
        String l_strSonarTradedCode;
        String l_strTradingType;
        if (l_request instanceof WEB3EquityBuyCompleteRequest)
        {
            l_strTradingType = ((WEB3EquityBuyCompleteRequest)l_request).tradingType;
            if (WEB3TradingTypeDef.BUY_ORDER.equals(l_strTradingType))
            {
                l_strSonarTradedCode = WEB3TransactionTypeSONARDef.MARKET_TRADING;
            }
            else
            {
                l_strSonarTradedCode = WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET;
            }
        }
        else
        {
            l_strSonarTradedCode = WEB3TransactionTypeSONARDef.MARKET_TRADING;
        }
        WEB3GentradeCommission l_commission =
            l_orderSpec.createCommission(
                this.getMainAccount().getBranch(),
                l_strSonarTradedCode);
        
        //1.15. validate現物株式注文()
        EqTypeNewOrderValidationResult l_eqNewOrderValidationResult =
            this.validateNewCashBasedOrder(l_orderSpec, l_adapter);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        //throw発注審査結果エラー情報(OrderValidationResult, 証券会社, String)
        //発注審査結果：　@validate現物株式注文()の戻り値
        //証券会社：　@補助口座.getInstitution()
        //銘柄コード：　@リクエストアダプタ.get銘柄コード()
        l_orderManager.throwOrderValidationResultErrorInfo(
            l_eqNewOrderValidationResult,
            l_subAccount.getInstitution(),
            l_strProductCode);

        //1.16. validate売付可能数量()
        this.validateSellableAssetQuantity(l_adapter);

        //validate機@構預託同意(補助口座)
        //補助口座：　@get補助口座()の戻り値
        l_orderManager.validateMechanismDepositAgree(l_subAccount);

        //getTradedProduct()
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        WEB3EquityTradedProduct l_tradedProduct = null;
        try
        {
            l_tradedProduct =
                (WEB3EquityTradedProduct)l_productManager.getTradedProduct(
                    l_subAccount.getInstitution(),
                    l_orderSpec.getProductCode(),
                    l_orderSpec.getMarketCode());
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);                
        }

        //1.18. getQuantity()
        double l_dblQuantity = l_orderSpec.getQuantity();
        
        String l_strCheckPrice;
        if (l_request instanceof WEB3EquityBuyCompleteRequest)
        {
            l_strCheckPrice = ((WEB3EquityBuyCompleteRequest)l_request).checkPrice;
        }
        else
        {
            l_strCheckPrice = ((WEB3EquitySellCompleteRequest)l_request).checkPrice;
        }

        //1.21. calc概算受渡代金()
        //引数は以下の通りに設定する。

        //手数料 : 作成した手数料オブジェクト
        //指値 : 株式注文内容.getLimitPrice()
        //（W指値）訂正指値 : 株式注文内容.get（W指値）訂正指値()
        //逆指値基準値 : 株式注文内容.get逆指値基準値()
        //執行条件 : 株式注文内容.getExecConditionType()
        //（W指値）執行条件 : 株式注文内容.get（Ｗ指値）執行条件( )
        //値段条件 : 株式注文内容.get値段条件()
        //発注条件 : 株式注文内容.get発注条件()
        //確認時取得時価 : リクエスト.確認時単価
        //isストップ注文有効 : false（固定）
        //補助口座 : this.get補助口座()
        //取引銘柄 : 拡張プロダクトマネージャ.getTradedProduct()
        //株数 : 株式注文内容.getQuantity( )
        //is売注文 : 株式注文内容.isSellOrder( )
        //約定数量 : 0
        //合計約定金額 : 0
        //isSkip金額チェック : false（固定）

        WEB3EquityEstimatedDeliveryPrice l_estimatedDeliveryPrice =
            l_orderManager.calcEstimateDeliveryAmount(
                l_commission,
                l_orderSpec.getLimitPrice(),
                l_orderSpec.getWLimitPriceChange(),
                l_orderSpec.getStopLimitPriceBasePrice(),
                l_orderSpec.getExecConditionType(),
                l_orderSpec.getWlimitExecCondType(),
                l_orderSpec.getPriceConditionType(),
                l_orderSpec.getOrderCond(),
                l_strCheckPrice,
                false,
                l_subAccount,
                l_tradedProduct,
                l_dblQuantity,
                l_blnIsSellOrder,
                0.0D,
                0.0D,
                false);

        //set注文単価()
        //注文単価をセットする。
        //注文単価：　@概算受渡代金計算結果.get計算単価()をセットする。
        l_orderSpec.setOrderUnitPrice(l_estimatedDeliveryPrice.getCalcUnitPrice());

        //1.22. set概算受渡代金()
        l_orderSpec.setEstimateDeliveryAmount(l_estimatedDeliveryPrice.getEstimateDeliveryAmount());
        
        //1.23. validate取引余力()
        this.validateTradingPower(
            l_subAccount,
            l_orderSpec,
            true,
            l_tradedProduct);
        
        //1.24. submit現物株式注文()
        long l_lngOrderId;
        String l_strPassward;
        if (l_commonRequest instanceof WEB3EquityBuyCompleteRequest)
        {
            if (((WEB3EquityBuyCompleteRequest)l_commonRequest).orderId == null)
            {
                ((WEB3EquityBuyCompleteRequest)l_commonRequest).orderId =
                    String.valueOf(l_orderManager.createNewOrderId());
            }
            l_lngOrderId = Long.parseLong(((WEB3EquityBuyCompleteRequest)l_commonRequest).orderId);
            l_strPassward = ((WEB3EquityBuyCompleteRequest)l_commonRequest).password;
        }
        else
        {
            if (((WEB3EquitySellCompleteRequest)l_commonRequest).orderId == null)
            {
                ((WEB3EquitySellCompleteRequest)l_commonRequest).orderId =
                    String.valueOf(l_orderManager.createNewOrderId());
            }
            l_lngOrderId = Long.parseLong(((WEB3EquitySellCompleteRequest)l_commonRequest).orderId);
            l_strPassward = ((WEB3EquitySellCompleteRequest)l_commonRequest).password;
        }
        this.submitNewCashBasedOrder(
            l_subAccount,
            l_orderSpec,
            l_lngOrderId,
            l_strPassward,
            l_adapter);
        
        //1.25. createResponse()
        WEB3GenResponse l_response = l_commonRequest.createResponse();
        
        //1.26. isインサイダー警告表示()
        WEB3EquityProduct l_product = null;
        try
        {
            l_product =
                (WEB3EquityProduct)l_productManager.getProduct(
                    l_subAccount.getInstitution(),
                    l_strProductCode);
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        boolean l_blnIsInsiderMessageSuspension =
            l_orderManager.isInsiderMessageSuspension(
                l_subAccount,
                l_product.getProductId());
        
        //1.27. プロパティセット
        if (l_response instanceof WEB3EquityBuyCompleteResponse)
        {
            WEB3EquityBuyCompleteResponse l_buyCompleteResponse =
                (WEB3EquityBuyCompleteResponse)l_response;
            //レスポンス.更新時間
            l_buyCompleteResponse.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
            //レスポンス.識別番号
            l_buyCompleteResponse.orderActionId = ((WEB3EquityBuyCompleteRequest)l_request).orderId; 
            //レスポンス.インサイダー警告表示フラグ
            l_buyCompleteResponse.insiderWarningFlag = l_blnIsInsiderMessageSuspension;
            //市場コード
            l_buyCompleteResponse.marketCode = l_strRequestMarketCode;
            // レスポンス.注文有効期限
            l_buyCompleteResponse.expirationDate = l_adapter.getExpirationDate();
        }
        else
        {
            WEB3EquitySellCompleteResponse l_sellCompleteResponse =
                (WEB3EquitySellCompleteResponse)l_response;
            //レスポンス.更新時間
            l_sellCompleteResponse.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
            //レスポンス.識別番号
            l_sellCompleteResponse.orderActionId = ((WEB3EquitySellCompleteRequest)l_request).orderId; 
            //レスポンス.インサイダー警告表示フラグ
            l_sellCompleteResponse.insiderWarningFlag = l_blnIsInsiderMessageSuspension;
            //市場コード
            l_sellCompleteResponse.marketCode = l_strRequestMarketCode;
            // レスポンス.注文有効期限
            l_sellCompleteResponse.expirationDate = l_adapter.getExpirationDate();
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * 株式注文処理を実行する。 <BR>
     * <BR>
     * （システム実装方針ガイド 4.4.業務ロジック　@参照） <BR>
     * <BR>
     * １）　@実施メソッド判定 <BR>
     * 引数の株式注文リクエストのオブジェクト型より、 <BR>
     * サービスメソッドを判定しコールする。 <BR>
     * <BR>
     * ２）　@サービスメソッドの戻り値を返却する。 <BR>
     * @@param l_request
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 400E3ED800CA
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3EquityBuyConfirmRequest ||
            l_request instanceof WEB3EquitySellConfirmRequest)
        {
            l_response = this.validateOrder(l_request);
        }
        else if (l_request instanceof WEB3EquityBuyCompleteRequest ||
                  l_request instanceof WEB3EquitySellCompleteRequest)
        {
            l_response = this.submitOrder(l_request);
        }
        else
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストタイプ ： " + l_request);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (createリクエストアダプタ)<BR>
     * リクエストアダプタのインスタンスを生成する。<BR>
     * <BR>
     * 株式注文リクエストアダプタ.create(引数のリクエスト)をコールする。<BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエストデータ。
     * @@return WEB3EquityOrderRequestAdapter
     */
    protected WEB3EquityOrderRequestAdapter createRequestAdapter(WEB3GenRequest l_request)
    {
        final String STR_METHOD_NAME = "createRequestAdapter(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityOrderRequestAdapter l_requestAdaptor =
            WEB3EquityOrderRequestAdapter.create(l_request);
        
        log.exiting(STR_METHOD_NAME);
        return l_requestAdaptor;
    }
    
    /**
     * (validate現物株式注文)<BR>
     * 現物株式発注審査メソッドの呼び出しを行う。<BR>
     * <BR>
     * EQTYPEの拡張株式注文マネージャ.validate現物株式注文(<BR>
     * 補助口座, 株式注文内容)にdelegateする。<BR>
     * <BR>
     * ------------------------------------------------------<BR>
     * ＜validate現物株式注文()：引数設定仕様＞<BR>
     * <BR>
     * 補助口座：　@this.get補助口座()の戻り値<BR>
     * 株式注文内容：　@引数の株式注文内容オブジェクト<BR>
     * ------------------------------------------------------<BR>
     * @@param l_orderSpec - (株式注文内容)<BR>
     * 株式注文内容オブジェクト。
     * @@param l_requestAdaptor - (株式注文リクエストアダプタ)<BR>
     * 株式注文リクエストアダプタ。
     * @@return EqTypeNewOrderValidationResult
     * @@throws WEB3BaseException
     */
    protected EqTypeNewOrderValidationResult validateNewCashBasedOrder(
        EqTypeNewCashBasedOrderSpec l_orderSpec,
        WEB3EquityOrderRequestAdapter l_requestAdaptor)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateNewCashBasedOrder(EqTypeNewCashBasedOrderSpec, WEB3EquityOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        EqTypeNewOrderValidationResult l_result =
            l_orderManager.validateNewCashBasedOrder(
                this.getSubAccount(),
                l_orderSpec);
        
        log.exiting(STR_METHOD_NAME);
        return l_result;
    }
    
    /**
     * (validate取引余力)<BR>
     * 取引余力をチェックし、取引余力結果オブジェクトを返却する。<BR>
     * シーケンス図「（株式注文サービス）validate取引余力」を参照。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。
     * @@param l_orderSpec - (株式注文内容)<BR>
     * 株式注文内容オブジェクト。
     * @@param l_blnUpdateFlg - (余力更新フラグ)<BR>
     * 余力更新フラグ。<BR>
     * （false：　@確認時、true：　@完了時）
     * @@param l_tradedProduct - (取引銘柄)<BR>
     * 取引銘柄オブジェクト。
     * @@return WEB3TPTradingPowerResult
     * @@throws WEB3BaseException
     */
    protected WEB3TPTradingPowerResult validateTradingPower(
        WEB3GentradeSubAccount l_subAccount,
        WEB3EquityNewCashBasedOrderSpec l_orderSpec,
        boolean l_blnUpdateFlg,
        WEB3EquityTradedProduct l_tradedProduct)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateTradingPower(WEB3GentradeSubAccount, WEB3EquityNewCashBasedOrderSpec, boolean, WEB3EquityTradedProduct)";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityOrderManagerPersistenceEventInterceptor l_interceptor =
            new WEB3EquityOrderManagerPersistenceEventInterceptor();
                   
        l_interceptor.setEquityOrderSpec(l_orderSpec);
        
        WEB3TPTradingPowerService l_tradingpowerService =
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        Object[] l_orderSpecIntercepters = { l_interceptor };
        Object[] l_orderSpecs = { l_orderSpec };
        OrderTypeEnum l_orderType;
        if (l_orderSpec.isSellOrder())
        {
            l_orderType = OrderTypeEnum.EQUITY_SELL;
        }
        else
        {
            l_orderType = OrderTypeEnum.EQUITY_BUY;
        }
        WEB3TPTradingPowerResult l_tpResult =
            l_tradingpowerService.validateTradingPower(
                l_subAccount,
                l_orderSpecIntercepters,
                l_orderSpecs,
                l_orderType,
                l_blnUpdateFlg);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        if (l_tpResult.isResultFlg() == false)
        {
            if (WEB3TPTradingPowerErrorDivDef.MARGIN_SEC_ERROR.equals(l_tpResult.getTpErrorInfo().tradinPowerErrorDiv))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01928,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            else
            {
                if (l_orderSpec.isBuyOrder())
                {
                    String l_strLackAccountBalanceInfo =
                        l_orderManager.getLackAccountBalanceInfoBuy(l_tpResult);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01929,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_strLackAccountBalanceInfo);
                }
                else
                {
                    String l_strLackAccountBalanceInfo =
                        l_orderManager.getLackAccountBalanceInfoSell(
                            l_tpResult,
                            l_orderSpec.getQuantity());
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01930,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_strLackAccountBalanceInfo);
                }
            }
        }
        
        if (l_blnUpdateFlg)
        {
            l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_tpResult;
    }
    
    /**
     * (get概算簿価単価)<BR>
     * 概算簿価単価を取得し返す。<BR>
     * <BR>
     * １）　@保有資産オブジェクトを取得する。<BR>
     * <BR>
     * 　@株式ポジションマネージャ.getAsset(引数のリクエストアダプタ.リクエスト.ID)をコールする。<BR>
     * <BR>
     * ２）　@株式計算サービス.calc概算簿価単価(保有資産.銘柄ID, get補助口座(), 保有資産.税区分)をコールし、<BR>
     * 　@　@　@戻り値を返却する。<BR>
     * @@param l_requestAdaptor - (株式注文リクエストアダプタ)<BR>
     * 株式注文リクエストアダプタ。
     * @@return String
     * @@throws WEB3BaseException
     */
    protected String getEstimatedBookPrice(
        WEB3EquityOrderRequestAdapter l_requestAdaptor)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getEstimatedBookPrice(WEB3EquityOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
        WEB3GenRequest l_request = l_requestAdaptor.request;
        long l_lngAssetId = 0L;
        String l_strId = null;
        if (l_request instanceof WEB3EquitySellConfirmRequest)
        {
            l_strId = ((WEB3EquitySellConfirmRequest)l_request).id;
        }
        else if (l_request instanceof WEB3EquitySellCompleteRequest)
        {
            l_strId = ((WEB3EquitySellCompleteRequest)l_request).id;
        }
        if (l_strId != null)
        {
            l_lngAssetId = Long.parseLong(l_strId);
        }
        Asset l_asset = null;
        try
        {
            l_asset = l_positionManager.getAsset(l_lngAssetId);
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        WEB3EquityBizLogicProvider l_bizLogicProvider =
            (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
        double l_dblEstimatedBookPrice =
            l_bizLogicProvider.calcEstimatedBookPrice(
                l_asset.getProduct().getProductId(),
                this.getSubAccount(),
                l_asset.getTaxType());
        String l_strEstimatedBookPrice =
            WEB3StringTypeUtility.formatNumber(l_dblEstimatedBookPrice);
        
        log.exiting(STR_METHOD_NAME);
        return l_strEstimatedBookPrice;
    }
    
    /**
     * (validate売付可能数量)<BR>
     * 売付可能数量チェックを行う。<BR>
     * <BR>
     * 何もせずにreturnする。<BR>
     * （売付可能数量チェックはvalidate現物株式注文()内で行っているため）<BR>
     * @@param l_requestAdaptor - (株式注文リクエストアダプタ)<BR>
     * 株式注文リクエストアダプタ。
     * @@throws WEB3BaseException
     */
    protected void validateSellableAssetQuantity(
        WEB3EquityOrderRequestAdapter l_requestAdaptor)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateSellableAssetQuantity(WEB3EquityOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return;
    }
    
    /**
     * (submit現物株式注文)<BR>
     * 現物株式注文を登録する。<BR>
     * <BR>
     * 拡張株式注文マネージャ.submitNewCashBasedOrder(<BR>
     * 補助口座, 株式注文内容, 注文ID, 取引パスワード, true（＝発注審査をスキップする）)をコールする。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。
     * @@param l_orderSpec - (株式注文内容)<BR>
     * 株式注文内容オブジェクト。
     * @@param l_lngOrderId - (注文ID)<BR>
     * 予約注文の注文ID。
     * @@param l_strTradingPassword - (取引パスワード)<BR>
     * 取引パスワード。
     * @@param l_requestAdaptor - (株式注文リクエストアダプタ)<BR>
     * 株式注文リクエストアダプタ。
     * @@throws WEB3BaseException
     */
    protected void submitNewCashBasedOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3EquityNewCashBasedOrderSpec l_orderSpec,
        long l_lngOrderId,
        String l_strTradingPassword,
        WEB3EquityOrderRequestAdapter l_requestAdaptor)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitNewCashBasedOrder(WEB3GentradeSubAccount, WEB3EquityNewCashBasedOrderSpec, long, String, WEB3EquityOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        EqTypeOrderSubmissionResult l_result =
            l_orderManager.submitNewCashBasedOrder(
                l_subAccount,
                l_orderSpec,
                l_lngOrderId,
                l_strTradingPassword,
                true);
        if (l_result.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_result.getProcessingResult());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseException(
                l_result.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set単価)<BR>
     * 何もせずにリターンする。<BR>
     * <BR>
     * @@param l_requestAdapter - (株式注文リクエストアダプタ)<BR>
     * 株式注文リクエストアダプタ。<BR>
     * @@param l_response - (レスポンス)<BR>
     * レスポンス<BR>
     * @@throws WEB3BaseException
     */
    protected void setPrice(
        WEB3EquityOrderRequestAdapter l_requestAdapter,
        WEB3GenResponse l_response)
        throws WEB3BaseException
    {
        
    }
}
@
