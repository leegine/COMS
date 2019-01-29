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
filename	WEB3MarginChangeOpenMarginServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引訂正新規建サービスImpl(WEB3MarginChangeOpenMarginServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/08 盧法@旭(中訊) 新規作成
Revesion History : 2004/12/15 中尾寿彦(SRA) 残案件対応による修正
Revesion History : 2005/01/06 岡村和明(SRA) JavaDoc修正
Revesion History : 2006/11/28 趙林鵬 (中訊) モデル No.1009
Revesion History : 2007/06/14 何文敏 (中訊) モデル No.1170
Revesion History : 2007/08/08 韓斌 (中訊)   モデル No.1192
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeOrderUnitEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityEstimatedContractPrice;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityOrderValidationResult;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3MarginChangeOpenMarginUpdateInterceptor;
import webbroker3.equity.WEB3MarginChangeOrderSpec;
import webbroker3.equity.WEB3MarginClientRequestService;
import webbroker3.equity.message.WEB3MarginCommissionInfoUnit;
import webbroker3.equity.message.WEB3MarginOpenMarginChangeCompleteRequest;
import webbroker3.equity.message.WEB3MarginOpenMarginChangeCompleteResponse;
import webbroker3.equity.message.WEB3MarginOpenMarginChangeConfirmRequest;
import webbroker3.equity.message.WEB3MarginOpenMarginChangeConfirmResponse;
import webbroker3.equity.service.delegate.WEB3MarginChangeOpenMarginRequestAdapter;
import webbroker3.equity.service.delegate.WEB3MarginChangeOpenMarginService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationEqTypeOrderUpdateService;

/**
 * （信用取引訂正新規建サービスImpl）。<BR>
 * <BR>
 * 信用取引訂正新規建サービス実装クラス
 * @@author 法@旭
 * @@version 1.0
 */
public class WEB3MarginChangeOpenMarginServiceImpl extends WEB3MarginClientRequestService implements WEB3MarginChangeOpenMarginService 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        WEB3MarginChangeOpenMarginServiceImpl.class);
    /**
     * @@roseuid 4140067003AC
     */
    public WEB3MarginChangeOpenMarginServiceImpl() 
    {
     
    }
    
    /**
     * 信用取引訂正新規建サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型により、validate注文()メソッド、<BR>
     * submit注文()メソッドのいずれかをコールする。
     * @@param l_request - リクエスト
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40581E0B0145
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "EXECUTE";
        if (l_request == null)
        {
             throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (l_request instanceof WEB3MarginOpenMarginChangeConfirmRequest)
        {
            return this.validateOrder((WEB3MarginOpenMarginChangeConfirmRequest)l_request);   
        }
        else if (l_request instanceof WEB3MarginOpenMarginChangeCompleteRequest)
        {
            return this.submitOrder((WEB3MarginOpenMarginChangeCompleteRequest)l_request);    
        }
        throw new WEB3BaseRuntimeException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80018,
            this.getClass().getName() + "." + STR_METHOD_NAME);
    }
    
    /**
     * (validate注文)<BR>
     * 信用取引の訂正新規建発注審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（信用取引訂正新規建サービス）validate注文１」及び<BR>
     * 「（信用取引訂正新規建サービス）validate注文２」参照。<BR>
     * @@param l_request - 信用取引訂正新規建注文確認リクエストデータオブジェクト
     * @@return webbroker3.margin.message.WEB3MarginOpenMarginChangeConfirmResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40581E0B0165
     */
    protected WEB3MarginOpenMarginChangeConfirmResponse validateOrder(
        WEB3MarginOpenMarginChangeConfirmRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateOrder(WEB3MarginOpenMarginChangeConfirmRequest)";
        if (l_request == null)
        {
             throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //1.1. validate()
        l_request.validate();
        
        //1.2. get補助口座()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //1.3. get代理入力者()
        Trader l_trader = this.getTrader();
        
        //1.4. getOrderUnits()
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tm = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);     
        WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager)l_tm.getOrderManager();
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(Long.parseLong(l_request.id)); 
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        //1.7. get発注日()
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        //1.8. 信用新規建注文訂正内容作成
        //1.8.1. 信用取引訂正新規建リクエストアダプタオブジェクトを生成する。
        WEB3MarginChangeOpenMarginRequestAdapter l_requestAdapter = WEB3MarginChangeOpenMarginRequestAdapter.create(l_request);
        //1.8.2. get訂正後注文株数()
        double l_dblQuantity = l_requestAdapter.getModifiedOrderQuantity();
        //1.8.3. get訂正後執行条件()
        EqTypeExecutionConditionType l_type = l_requestAdapter.getModifiedExecutionConditionType();
        //1.8.4. get訂正後注文失効日()
        Date l_datExpirationDate = WEB3DateUtility.toDay(l_requestAdapter.getModifiedExpirationDate());
        //1.8.5. get訂正後発注条件演算子()
        String l_strOrderCondOperator = l_requestAdapter.getModifiedOrderCondOperator();
        //1.8.6. get訂正後逆指値基準値()
        double l_dblStopOrderPrice = l_requestAdapter.getModifiedStopOrderPrice();
        //1.8.7. is出来るまで注文()
        boolean l_blnIsCarriedOrder = l_requestAdapter.isCarriedOrder();

        //get訂正後（W指値）執行条件
        EqTypeExecutionConditionType l_eqTypeExecutionConditionType =
            l_requestAdapter.getModifiedWLimitExecCondType();

        //1.8.8. create新規建注文訂正内容
        String l_strWLimitPrice = l_request.wLimitPrice;
        if (l_strWLimitPrice == null)
        {
            l_strWLimitPrice = "0.0";
        }        
        long l_lngOrderId = l_orderUnitRow.getOrderId();
        String l_strLimitPrice = l_request.limitPrice;
        if (l_strLimitPrice == null)
        {
            l_strLimitPrice = "0.0";            
        }
        WEB3MarginChangeOrderSpec l_spec =
            WEB3MarginChangeOrderSpec.createOpenMarginChangeOrderSpec(
                l_lngOrderId,
                l_orderUnitRow.getOrderUnitId(),
                l_dblQuantity,
                Double.parseDouble(l_strLimitPrice),
                l_type,            
                l_datExpirationDate,
                l_request.priceCondType,
                l_request.orderCondType,
                l_strOrderCondOperator,
                l_dblStopOrderPrice,
                Double.parseDouble(l_strWLimitPrice),
                l_eqTypeExecutionConditionType,
                l_blnIsCarriedOrder,
                l_trader,
                l_request.wlimitEnableStatusDiv
            );
        
        //1.8.9. create手数料()
        WEB3EquityBizLogicProvider l_provider = (WEB3EquityBizLogicProvider) l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getBizLogicProvider();          
        WEB3GentradeCommission l_commission = l_provider.createCommission(l_lngOrderId);
        
        //1.8.11.1. getTradeProduct()
        WEB3EquityTradedProduct l_tradeProduct = null;
        try
        {
            l_tradeProduct =(WEB3EquityTradedProduct)l_orderUnit.getTradedProduct();
        }
        catch (RuntimeSystemException l_rse)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01966,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文単位ID=[" + Long.toString(l_orderUnit.getOrderUnitId()) + "]の注文単位に紐付く取引銘柄無し",
                l_rse);
        }
         
        //1.8.11.2. get新規建注文訂正内容詳細()                 
        EqTypeChangeOrderUnitEntry l_entry =
            (EqTypeChangeOrderUnitEntry)l_spec.getChangeOrderUnitEntry();
        //1.8.11.5. getAfterChangePrice()
        double l_dblChangePrice = l_entry.getAfterChangePrice();

        //is売注文(EqTypeOrderUnit)
        boolean l_blnIsSellOrder = l_orderManager.isSellOrder(l_orderUnit);

        //1.8.15. get訂正後注文株数()
        double l_dblOrderQuantity = l_spec.getModifiedOrderQuantity();
        //1.8.16. getExecutedQuantity()
        double l_dblExecutedQuantity = l_orderUnit.getExecutedQuantity();
        //1.8.17. getExecutedAmmount()
        double l_dblExecutedAmmount = l_orderUnit.getExecutedAmount();
        //calc注文時建代金
        //手数料：　@create手数料()の戻り値
        //指値：　@getAfterChangePrice()の戻り値
        //（W指値）訂正指値：　@信用新規建注文訂正内容.get訂正後（W指値）訂正指値()の戻り値
        //逆指値基準値：　@信用新規建注文訂正内容.get訂正後逆指値基準値()の戻り値
        //執行条件：　@信用新規建注文訂正内容.get訂正後執行条件()の戻り値
        //（W指値）執行条件：　@信用新規建注文訂正内容.get訂正後（W指値）執行条件()の戻り値
        //値段条件：　@信用新規建注文訂正内容.get訂正後値段条件()の戻り値
        //発注条件：　@信用新規建注文訂正内容.get訂正後発注条件()の戻り値
        //確認時取得時価：
        //　@確認処理の場合はnull
        //　@完了処理の場合はリクエストデータ.確認時単価
        //isストップ注文有効：　@信用新規建注文訂正内容.isストップ注文有効()の戻り値
        //is売建：　@is売注文()の戻り値
        //補助口座：　@get補助口座()の戻り値
        //取引銘柄：　@getTradedProduct()の戻り値
        //株数：　@get訂正後注文株数()の戻り値
        //約定数量：　@getExecutedQuantity()戻り値
        //合計約定金額：　@getExecutedAmount()の戻り値
        //isSkip金額チェック：　@false
        WEB3EquityEstimatedContractPrice l_equityEstimatedContractPrice =
            l_orderManager.calcContractAmountAtOrder(
                l_commission,
                l_dblChangePrice,
                l_spec.getModifiedWLimitPrice(),
                l_spec.getModifiedStopOrderPrice(),
                l_spec.getModifiedExecutionType(),
                l_spec.getModifiedWlimitExecCondType(),
                l_spec.getModifiedPriceConditionType(),
                l_spec.getModifiedOrderConditionType(),
                null,
                l_spec.isStopOrderEnable(),
                l_blnIsSellOrder,
                l_subAccount,
                l_tradeProduct,
                l_dblOrderQuantity,
                l_dblExecutedQuantity,
                l_dblExecutedAmmount,
                false);

        //set訂正後計算単価()
        l_spec.setModifiedCalcUnitPrice(l_equityEstimatedContractPrice.getCalcUnitPrice());
        
        //set訂正後建代金()
        l_spec.setModifiedContractAmount(l_equityEstimatedContractPrice.getEstimatedContractPrice());
        
        //1.9. validate現物株式訂正注文()
        WEB3EquityOrderValidationResult l_result =
            (WEB3EquityOrderValidationResult)l_orderManager.validateChangeOrder(
                l_subAccount,
                l_spec);

        //throw発注審査結果エラー情報(OrderValidationResult, 証券会社, String)
        //発注審査結果：　@validate現物株式訂正注文()の戻り値
        //証券会社：　@補助口座.getInstitution()
        //銘柄コード：　@注文単位.銘柄IDに該当する株式銘柄.銘柄コード
        long l_lngProductId = l_orderUnitRow.getProductId();

        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tm.getProductManager();
        WEB3EquityProduct l_product;
        try
        {
            l_product = (WEB3EquityProduct)l_productManager.getProduct(l_lngProductId);
        }
        catch (NotFoundException l_exNFE)
        {
            log.error("テーブルに該当するデータがありません。", l_exNFE);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_exNFE.getMessage(),
                l_exNFE);
        }

        String l_strProductCode = l_product.getProductCode();

        l_orderManager.throwOrderValidationResultErrorInfo(
            l_result,
            l_subAccount.getInstitution(),
            l_strProductCode);

        //validate取引余力(補助口座, 信用新規建注文訂正内容, boolean, 株式発注審査結果)
        //補助口座：　@get補助口座()の戻り値 
        //信用新規建注文訂正内容：　@作成した 信用新規建注文訂正内容オブジェクト
        //余力更新フラグ：　@false（確認時)
        //発注審査結果：　@validate現物株式訂正注文()の戻り値
        WEB3TPTradingPowerResult l_tpResult =
            this.validateTradingPower(
                l_subAccount,
                l_spec,
                false,
                l_result);
        
        //1.13. calc委託手数料
        l_provider.calcCommission(l_commission,l_subAccount);
        
        //1.14. create response
        WEB3MarginOpenMarginChangeConfirmResponse l_response =
            (WEB3MarginOpenMarginChangeConfirmResponse)l_request.createResponse();
        
        //1.15. 信用取引手数料情報()
        WEB3MarginCommissionInfoUnit l_commissionInfo = new WEB3MarginCommissionInfoUnit();
        
        //1.16. calc消費税
        double l_dblCommision = l_commission.getCommission();
        double l_dblSaleTax = l_provider.calcSalesTax(
            l_dblCommision,
            l_commission.getOrderBizDate(),
            l_subAccount);
        
        //1.17. プロパティセット
        // 手数料コース
        l_commissionInfo.commissionCourse = l_commission.getCommissionCourseDiv();
        // 手数料
        l_commissionInfo.commission = WEB3StringTypeUtility.formatNumber(l_dblCommision);
        // 手数料消費税
        l_commissionInfo.commissionConsumptionTax = WEB3StringTypeUtility.formatNumber(l_dblSaleTax);
        
        //1.18. get市場閉局警告市場()
        String[] l_strMarkets = WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
            (WEB3GentradeBranch)l_subAccount.getMainAccount().getBranch(),
            ProductTypeEnum.EQUITY,
            l_orderUnitRow.getRepaymentType());
        
        //1.20. get訂正後建代金()
        double l_dblContractAmmount2 = l_spec.getModifiedContractAmount();
        
        //1.21. isインサイダー警告表示()
        boolean l_isInsiderMessageSuspension = l_orderManager.isInsiderMessageSuspension(
            l_subAccount,
            l_orderUnitRow.getProductId());
        
        //1.22. レスポンスデータに以下の通りプロパティを設定する。
        //レスポンス.確認時発注日
        l_response.checkDate = WEB3DateUtility.toDay(l_datBizDate);
        //レスポンス.概算受渡代金
        l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_dblContractAmmount2);
        //レスポンス.取引終了警告市場コード一覧
        l_response.messageSuspension = l_strMarkets;
        //レスポンス.内出来株数
        l_response.partContQuantity = WEB3StringTypeUtility.formatNumber(l_dblExecutedQuantity);
        //レスポンス.手数料情報
        l_response.commissionInfo = l_commissionInfo;
        // レスポンス.確認時単価
        l_response.checkPrice = l_equityEstimatedContractPrice.getCheckGetCurrentPrice();
        // レスポンス.インサイダー警告表示フラグ
        l_response.insiderWarningFlag = l_isInsiderMessageSuspension;
        if (l_tpResult != null)
        {
            l_response.attentionObjectionType =
                l_tpResult.getAttentionObjectionType();
        }
        // レスポンス.注文有効期限
        l_response.expirationDate = l_requestAdapter.getExpirationDate();
        
        return l_response;
    }
    
    /**
     * (submit注文)<BR>
     * 信用取引訂正新規建注文を登録する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（信用取引訂正新規建サービス）submit注文１」及び<BR>
     * 「（信用取引訂正新規建サービス）submit注文２」参照。
     * @@param l_request - (リクエストデータ)<BR>
     * 信用取引訂正新規建注文完了リクエストデータオブジェクト
     * @@return WEB3MarginOpenMarginChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 40581E0B0184
     */
    protected WEB3MarginOpenMarginChangeCompleteResponse submitOrder(
        WEB3MarginOpenMarginChangeCompleteRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateOrder";
        if (l_request == null)
        {
             throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //1.1. validate
        l_request.validate();
        
        //1.2. get補助口座( )
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //1.3 get代理入力者( )
        Trader l_trader = this.getTrader();
        
        //1.4. getOrderUnits
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tm = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);     
        WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager) l_tm.getOrderManager();
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(Long.parseLong(l_request.id)); 
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
        
        //1.7. get発注日( )
        if (l_request.checkDate == null)
        {
            l_request.checkDate =
                WEB3GentradeTradingTimeManagement.getOrderBizDate();
        }
        WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
        
        //1.8. 信用新規建注文訂正内容作成
        //1.8.1. 信用取引訂正新規建リクエストアダプタオブジェクトを生成する。
        WEB3MarginChangeOpenMarginRequestAdapter l_requestAdapter = WEB3MarginChangeOpenMarginRequestAdapter.create(l_request);
        //1.8.2. get訂正後注文株数()
        double l_dblQuantity = l_requestAdapter.getModifiedOrderQuantity();
        //1.8.3. get訂正後執行条件()
        EqTypeExecutionConditionType l_type = l_requestAdapter.getModifiedExecutionConditionType();
        //1.8.4. get訂正後注文失効日()
        Date l_datExpirationDate = WEB3DateUtility.toDay(l_requestAdapter.getModifiedExpirationDate());
        //1.8.5. get訂正後発注条件演算子()
        String l_strOrderCondOperator = l_requestAdapter.getModifiedOrderCondOperator();
        //1.8.6. get訂正後逆指値基準値()
        double l_dblStopOrderPrice = l_requestAdapter.getModifiedStopOrderPrice();
        //1.8.7. is出来るまで注文()
        boolean l_blnIsCarriedOrder = l_requestAdapter.isCarriedOrder();
        //get訂正後（W指値）執行条件
        EqTypeExecutionConditionType l_eqTypeExecutionConditionType =
            l_requestAdapter.getModifiedWLimitExecCondType();

        //1.8.8. create新規建注文訂正内容
        String l_strWLimitPrice = l_request.wLimitPrice;
        if (l_strWLimitPrice == null)
        {
            l_strWLimitPrice = "0.0";
        }        
        long l_lngOrderId = l_orderUnitRow.getOrderId();
        String l_strLimitPrice = l_request.limitPrice;
        if (l_strLimitPrice == null)
        {
            l_strLimitPrice = "0.0";            
        }
        WEB3MarginChangeOrderSpec l_spec =
            WEB3MarginChangeOrderSpec.createOpenMarginChangeOrderSpec(
                l_lngOrderId,
                l_orderUnitRow.getOrderUnitId(),
                l_dblQuantity,
                Double.parseDouble(l_strLimitPrice),
                l_type,            
                l_datExpirationDate,
                l_request.priceCondType,
                l_request.orderCondType,
                l_strOrderCondOperator,
                l_dblStopOrderPrice,
                Double.parseDouble(l_strWLimitPrice),
                l_eqTypeExecutionConditionType,
                l_blnIsCarriedOrder,
                l_trader,
                l_request.wlimitEnableStatusDiv
            );
        
        //1.8.9. create手数料(注文ID : long)
        WEB3EquityBizLogicProvider l_provider = (WEB3EquityBizLogicProvider) l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getBizLogicProvider();          
        WEB3GentradeCommission l_commission = l_provider.createCommission(l_lngOrderId);
        
        WEB3EquityTradedProduct l_tradeProduct = null;
        try
        {
	        l_tradeProduct =
	        (WEB3EquityTradedProduct)l_orderUnit.getTradedProduct();
	    }
        catch (RuntimeSystemException l_rse)
        {
	        throw new WEB3BusinessLayerException(
	        WEB3ErrorCatalog.BUSINESS_ERROR_01966,
	        this.getClass().getName() + "." + STR_METHOD_NAME,
	        "注文単位ID=[" + Long.toString(l_orderUnit.getOrderUnitId()) + "]の注文単位に紐付く取引銘柄無し",
	        l_rse);
	    }
                         
        EqTypeChangeOrderUnitEntry l_entry =
        (EqTypeChangeOrderUnitEntry)l_spec.getChangeOrderUnitEntry();
        double l_dblChangePrice = l_entry.getAfterChangePrice();

        //is売注文(EqTypeOrderUnit)
        boolean l_blnIsSellOrder = l_orderManager.isSellOrder(l_orderUnit);

        //1.8.15. get訂正後注文株数()
        double l_dblOrderQuantity = l_spec.getModifiedOrderQuantity();
        //1.8.16. getExecutedQuantity()
        double l_dblExecutedQuantity = l_orderUnit.getExecutedQuantity();
        //1.8.17. getExecutedAmmount()
        double l_dblExecutedAmmount = l_orderUnit.getExecutedAmount();

        //calc注文時建代金
        //手数料：　@create手数料()の戻り値
        //指値：　@getAfterChangePrice()の戻り値
        //（W指値）訂正指値：　@信用新規建注文訂正内容.get訂正後（W指値）訂正指値()の戻り値
        //逆指値基準値：　@信用新規建注文訂正内容.get訂正後逆指値基準値()の戻り値
        //執行条件：　@信用新規建注文訂正内容.get訂正後執行条件()の戻り値
        //（W指値）執行条件：　@信用新規建注文訂正内容.get訂正後（W指値）執行条件()の戻り値
        //値段条件：　@信用新規建注文訂正内容.get訂正後値段条件()の戻り値
        //発注条件：　@信用新規建注文訂正内容.get訂正後発注条件()の戻り値
        //確認時取得時価：
        //　@確認処理の場合はnull
        //　@完了処理の場合はリクエストデータ.確認時単価
        //isストップ注文有効：　@信用新規建注文訂正内容.isストップ注文有効()の戻り値
        //is売建：　@is売注文()の戻り値
        //補助口座：　@get補助口座()の戻り値
        //取引銘柄：　@getTradedProduct()の戻り値
        //株数：　@get訂正後注文株数()の戻り値
        //約定数量：　@getExecutedQuantity()戻り値
        //合計約定金額：　@getExecutedAmount()の戻り値
        //isSkip金額チェック：　@false
        WEB3EquityEstimatedContractPrice l_equityEstimatedContractPrice =
            l_orderManager.calcContractAmountAtOrder(
                l_commission,
                l_dblChangePrice,
                l_spec.getModifiedWLimitPrice(),
                l_spec.getModifiedStopOrderPrice(),
                l_spec.getModifiedExecutionType(),
                l_spec.getModifiedWlimitExecCondType(),
                l_spec.getModifiedPriceConditionType(),
                l_spec.getModifiedOrderConditionType(),
                l_request.checkPrice,
                l_spec.isStopOrderEnable(),
                l_blnIsSellOrder,
                l_subAccount,
                l_tradeProduct,
                l_dblOrderQuantity,
                l_dblExecutedQuantity,
                l_dblExecutedAmmount,
                false);
        
        //set訂正後計算単価()
        l_spec.setModifiedCalcUnitPrice(l_equityEstimatedContractPrice.getCalcUnitPrice());
        
        //set訂正後建代金()
        l_spec.setModifiedContractAmount(l_equityEstimatedContractPrice.getEstimatedContractPrice());
        
        //1.9. validate現物株式訂正注文
        WEB3EquityOrderValidationResult l_result =
            (WEB3EquityOrderValidationResult)l_orderManager.validateChangeOrder(
                l_subAccount,
                l_spec);

        //throw発注審査結果エラー情報(OrderValidationResult, 証券会社, String)
        //発注審査結果：　@validate現物株式訂正注文()の戻り値
        //証券会社：　@補助口座.getInstitution()
        //銘柄コード：　@注文単位.銘柄IDに該当する株式銘柄.銘柄コード
        long l_lngProductId = l_orderUnitRow.getProductId();

        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tm.getProductManager();
        WEB3EquityProduct l_product;
        try
        {
            l_product = (WEB3EquityProduct)l_productManager.getProduct(l_lngProductId);
        }
        catch (NotFoundException l_exNFE)
        {
            log.error("テーブルに該当するデータがありません。", l_exNFE);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_exNFE.getMessage(),
                l_exNFE);
        }

        String l_strProductCode = l_product.getProductCode();

        l_orderManager.throwOrderValidationResultErrorInfo(
            l_result,
            l_subAccount.getInstitution(),
            l_strProductCode);

        this.validateTradingPower(
            l_subAccount,
            l_spec,
            true,
            l_result);
        
        //1.14. submit現物株式注文訂正
        String l_strTradingPassword;
        if (l_trader == null)
        {
            l_strTradingPassword = l_request.password;
        }
        else
        {
            WEB3Crypt l_crypt = new WEB3Crypt();
            l_strTradingPassword =
                l_crypt.decrypt(l_subAccount.getMainAccount().getTradingPassword());
        }
        OrderSubmissionResult l_orderSubmissionResult =
            l_orderManager.submitChangeOrder(
                l_subAccount,
                l_spec,
                l_strTradingPassword,
                true);
        if (l_orderSubmissionResult.getProcessingResult().isFailedResult())
        {
            throw new WEB3BusinessLayerException(
                l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
		l_orderUnits = l_orderManager.getOrderUnits(Long.parseLong(l_request.id)); 
		l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
		l_orderUnitRow = (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
        
        //1.15. isインサイダー警告表示(補助口座 : 補助口座, 銘柄ID : long)
        boolean l_isInsiderMessageSuspension = l_orderManager.isInsiderMessageSuspension(
            l_subAccount,
            l_orderUnitRow.getProductId());
        
        //1.16. create response
        WEB3MarginOpenMarginChangeCompleteResponse l_response =
            (WEB3MarginOpenMarginChangeCompleteResponse)l_request.createResponse();

        List l_lisOrderUnits = null;
        if (l_orderManager.isReserveOrderConfirmRequire(l_orderUnit))
        {
            WEB3ToSuccReservationEqTypeOrderUpdateService l_updateService =
                (WEB3ToSuccReservationEqTypeOrderUpdateService)Services.getService(
                    WEB3ToSuccReservationEqTypeOrderUpdateService.class);
            l_lisOrderUnits =
                l_updateService.getOpenReserveEqtypeOrderUnits(l_orderUnit.getOrderId());
        }
        
        //1.17. プロパティセット
        // レスポンス.更新時間
        l_response.lastUpdatedTimestamp = l_orderUnitRow.getLastUpdatedTimestamp();
        // レスポンス.識別番号
        l_response.orderActionId = String.valueOf(l_orderUnitRow.getOrderId()); 
        // レスポンス.インサイダー警告表示フラグ
        l_response.insiderWarningFlag = l_isInsiderMessageSuspension;
        if (l_lisOrderUnits != null)
        {
            l_response.succSettingFlag = true;
        }
        else
        {
            l_response.succSettingFlag = false;
        }
        // レスポンス.注文有効期限
        l_response.expirationDate = l_requestAdapter.getExpirationDate();

        return l_response;
    }
    
    /**
     * (validate取引余力)<BR>
     * 取引余力をチェックする。<BR>
     * シーケンス図「（信用取引訂正新規建サービス）validate取引余力」を参照。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。
     * @@param l_orderSpec - (信用新規建注文訂正内容)<BR>
     * 信用新規建注文訂正内容オブジェクト。
     * @@param l_blnUpdateFlg - (余力更新フラグ)<BR>
     * 余力更新フラグ。<BR>
     * （false：　@確認時、true：　@完了時）
     * @@param l_validationResult - (発注審査結果)<BR>
     * 株式発注審査結果オブジェクト
     * @@return WEB3TPTradingPowerResult
     * @@throws WEB3BaseException
     */
    protected WEB3TPTradingPowerResult validateTradingPower(
        WEB3GentradeSubAccount l_subAccount,
        WEB3MarginChangeOrderSpec l_orderSpec,
        boolean l_blnUpdateFlg,
        WEB3EquityOrderValidationResult l_validationResult)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateTradingPower(WEB3GentradeSubAccount, WEB3MarginChangeOrderSpec, boolean, WEB3EquityOrderValidationResult)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        
        boolean l_bIsShortSellRegulationTarget =
            l_validationResult.getShortSellingRestraint();
        
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        String l_strOrderRootDiv =
            l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV);     
        WEB3MarginChangeOpenMarginUpdateInterceptor l_interceptor =
            new WEB3MarginChangeOpenMarginUpdateInterceptor(
                l_orderSpec,
                l_bIsShortSellRegulationTarget,
                l_strOrderRootDiv,
                (WEB3GentradeTrader)this.getTrader());
        
        Object[] l_orderSpecIntercepters = { l_interceptor };
        Object[] l_orderSpecs = { l_orderSpec };
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_orderSpec.getOrderId());
        OrderTypeEnum l_orderType = l_orderUnits[0].getOrderType();
        WEB3TPTradingPowerService l_tradingPowerService
            = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        WEB3TPTradingPowerResult l_tpResult =
            l_tradingPowerService.validateTradingPower(
                l_subAccount,
                l_orderSpecIntercepters,
                l_orderSpecs,
                l_orderType,
                l_blnUpdateFlg);
        if (l_tpResult.isResultFlg() == false)
        {
            l_orderManager.throwTpErrorInfo(l_tpResult, l_orderType);
        }
        
        if (l_blnUpdateFlg)
        {
            l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_tpResult;
    }     
}
@
