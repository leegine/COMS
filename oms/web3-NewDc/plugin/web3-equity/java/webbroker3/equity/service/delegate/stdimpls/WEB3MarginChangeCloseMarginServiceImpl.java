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
filename	WEB3MarginChangeCloseMarginServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引訂正返済サービスImpl(WEB3MarginChangeCloseMarginServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 李松峰(中訊) 新規作成
Revesion History : 2004/12/15 中尾寿彦(SRA) 残案件対応による修正
Revesion History : 2005/01/06 岡村和明(SRA) JavaDoc修正
Revesion History : 2006/11/28 趙林鵬 (中訊) モデル No.1021
Revesion History : 2007/06/14 何文敏 (中訊) モデル 1172
Revesion History : 2007/08/08 周墨洋 (中訊) モデル 1192
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeContractSettleChangeOrderUnitEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityRealizedProfitAndLossPrice;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3MarginChangeCloseMarginUpdateInterceptor;
import webbroker3.equity.WEB3MarginChangeSettleContractOrderSpec;
import webbroker3.equity.WEB3MarginClientRequestService;
import webbroker3.equity.message.WEB3MarginCloseMarginChangeCompleteRequest;
import webbroker3.equity.message.WEB3MarginCloseMarginChangeCompleteResponse;
import webbroker3.equity.message.WEB3MarginCloseMarginChangeConfirmRequest;
import webbroker3.equity.message.WEB3MarginCloseMarginChangeConfirmResponse;
import webbroker3.equity.message.WEB3MarginCommissionInfoUnit;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.equity.service.delegate.WEB3MarginChangeCloseMarginRequestAdapter;
import webbroker3.equity.service.delegate.WEB3MarginChangeCloseMarginService;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationEqTypeOrderUpdateService;


/**
 * （信用取引訂正返済サービスImpl）。<BR>
 * <BR>
 * 信用取引訂正返済サービス実装クラス
 * @@version 1.0
 */
public class WEB3MarginChangeCloseMarginServiceImpl extends WEB3MarginClientRequestService implements WEB3MarginChangeCloseMarginService 
{ 
   /**
    * ログ出力ユーティリティ。<BR>
    */
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3MarginChangeCloseMarginServiceImpl.class);
    /**
    
    /**
     * @@roseuid 414006710168
     */
    public WEB3MarginChangeCloseMarginServiceImpl() 
    {
     
    }
    
    /**
     * 信用取引訂正返済サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型により、validate注文()メソッド、<BR>
     * submit注文()メソッドのいずれかをコールする。
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4058288B024F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";

        log.entering(STR_METHOD_NAME);

        if (l_request instanceof WEB3MarginCloseMarginChangeConfirmRequest)
        {
            return this.validateOrder((WEB3MarginCloseMarginChangeConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3MarginCloseMarginChangeCompleteRequest)
        {
            return this.submitOrder((WEB3MarginCloseMarginChangeCompleteRequest)l_request);
        }
        else
        {
            log.error(STR_METHOD_NAME + " __Error[入力値が不正です]__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    }
    
    /**
     * (validate注文)<BR>
     * 信用取引の訂正返済発注審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（信用取引訂正返済サービス）validate注文１」及び<BR>
     * 「（信用取引訂正返済サービス）validate注文２」参照。<BR>
     * 「信用取引訂正返済 / （信用取引訂正返済サービス）信用返済注文訂正内容作成」<BR>
     *    (注文株数チェック)<BR>
     *     訂正元注文がランダム以外(*)で、かつ、信用取引訂正返済リクエストアダプタ.get注文株数( )＝0の場合、<BR>
     *     例外をスローする。<BR>
     *     class: WEB3BusinessLayerException <BR>
     *     tag:   BUSINESS_ERROR_00650 <BR>
     * <BR>
     * @@param l_request - 信用取引訂正返済確認リクエストデータオブジェクト
     * @@return WEB3MarginCloseMarginChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4058288B026E
     */
    protected WEB3MarginCloseMarginChangeConfirmResponse validateOrder(
        WEB3MarginCloseMarginChangeConfirmRequest l_request)
        throws WEB3BaseException 
    { 
        final String STR_METHOD_NAME = "validateOrder(WEB3MarginCloseMarginChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate(); 

        //1.2. get補助口座()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //1.4. get建株()
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tm = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tm.getPositionManager();
        long l_lngContractId = Long.parseLong(l_request.closeMarginContractUnits[0].id);
        WEB3EquityContract l_equityContract = null;
        try
        {
            l_equityContract =
                (WEB3EquityContract)l_positionManager.getContract(l_lngContractId);
        }
        catch (NotFoundException l_ne)
        {
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
        
        //1.7. get発注日()
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        //1.8. 信用返済注文訂正内容作成
        //1.8.1. create()
        WEB3MarginChangeCloseMarginRequestAdapter l_adapter = 
            WEB3MarginChangeCloseMarginRequestAdapter.create(l_request);
        //1.8.2. get注文株数()
        double l_dblOrderQuantity = l_adapter.getOrderQuantity();
        //1.8.3. getOrderUnits()
        WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager)
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        long l_lngOrderId = Long.parseLong(l_request.id);
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderManager.getOrderUnits(l_lngOrderId)[0];
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        //1.8.4. 注文株数チェック
        String l_strclosingOrderType = l_orderUnitRow.getClosingOrderType(); 
        if (l_strclosingOrderType == null || 
            WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(l_strclosingOrderType) ||
            WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(l_strclosingOrderType) ||
            WEB3ClosingOrderDef.OPEN_DATE.equals(l_strclosingOrderType)) 
        {
            if (l_adapter.getOrderQuantity() == 0.0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00650,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        //1.8.5. create決済建株エントリ
        if (l_request.orderQuantity == null)
        {
            l_dblOrderQuantity = 0.0D; 
        }
        EqTypeSettleContractOrderEntry[] l_contractOrderEntry = 
            l_orderManager.createClosingContractEntry(
                l_orderUnit.getOrderUnitId(),
                l_dblOrderQuantity,
                l_request.closeMarginContractUnits);
        //1.8.6. EqTypeContractSettleChangeOrderUnitEntry()
        double l_dblAfterChangePrice;
        if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.orderPriceDiv))
        {
            l_dblAfterChangePrice = 0.0D;
        }
        else
        {
            l_dblAfterChangePrice = Double.parseDouble(l_request.limitPrice);
        }
        EqTypeContractSettleChangeOrderUnitEntry l_orderUnitEntry =
            new EqTypeContractSettleChangeOrderUnitEntry(
                l_orderUnit.getOrderUnitId(),
                l_dblAfterChangePrice,
                l_contractOrderEntry);
        //1.8.7. get執行条件()
        EqTypeExecutionConditionType l_executeConType =
            l_adapter.getExecutionCondition();
        //1.8.8. is出来るまで注文()
        boolean l_isCarryOrder = l_adapter.isCarriedOrder();
        //1.8.9. create返済注文訂正内容()
        String l_strModifiedOrderCondOperator;  // 訂正後発注条件演算子
        double l_dblModifiedStopOrderPrice;     // 訂正後逆指値基準値
        double l_dblModifiedWLimitPrice;        // 訂正後（W指値）訂正指値
        // リクエスト.発注条件区分＝”指定なし”の場合
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_request.orderCondType))
        {
            l_strModifiedOrderCondOperator = "0";
            l_dblModifiedStopOrderPrice = 0.0D;
            l_dblModifiedWLimitPrice = 0.0D;
        }
        // リクエスト.発注条件区分＝”逆指値”の場合
        else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_strModifiedOrderCondOperator = l_request.stopOrderCondOperator;               
            l_dblModifiedStopOrderPrice = Double.parseDouble(l_request.stopOrderCondPrice);
            l_dblModifiedWLimitPrice = 0.0D;
        }
        // リクエスト.発注条件区分＝”W指値”の場合は、
        else
        {
            l_strModifiedOrderCondOperator = l_request.wlimitOrderCondOperator;
            l_dblModifiedStopOrderPrice = Double.parseDouble(l_request.wlimitOrderCondPrice);               
              
            if (l_request.wLimitPrice != null)
            {
                l_dblModifiedWLimitPrice =Double.parseDouble(l_request.wLimitPrice); 
            }
            else
            {
                l_dblModifiedWLimitPrice = 0.0D;
            }
        }
        // 訂正後注文失効日
        // 信用取引訂正返済リクエストアダプタ.get訂正後注文失効日( )の戻り値。
        Date l_datModifiedExpirationDate = l_adapter.getModifiedExpirationDate();

        WEB3MarginChangeSettleContractOrderSpec l_changeSettleContractOrderSpec = 
            WEB3MarginChangeSettleContractOrderSpec.createCloseMarginChangeOrderSpec(
                l_lngOrderId,
                l_orderUnitEntry,
                l_request.priceCondType,
                l_request.orderCondType,
                l_strModifiedOrderCondOperator,
                l_dblModifiedStopOrderPrice,
                l_dblModifiedWLimitPrice,
                l_executeConType,
                l_datModifiedExpirationDate,
                l_isCarryOrder,
                l_adapter.getModifiedWLimitExecCondType(),
                l_request.wlimitEnableStatusDiv);

        //1.9. validate返済注文訂正()
		EqTypeOrderValidationResult l_result = l_orderManager.validateChangeSettleContractOrder(
		l_subAccount,
		l_changeSettleContractOrderSpec);

        //throw発注審査結果エラー情報(OrderValidationResult, 証券会社, String)
        //発注審査結果：　@validate返済注文訂正()の戻り値
        //証券会社：　@補助口座.getInstitution()
        //銘柄コード：　@建株.getProduct().getProductCode()
        l_orderManager.throwOrderValidationResultErrorInfo(
            l_result,
            l_subAccount.getInstitution(),
            ((WEB3EquityProduct)(l_equityContract.getProduct())).getProductCode());

        //1.10. create手数料()
        WEB3EquityBizLogicProvider l_bizLogicProvider = (WEB3EquityBizLogicProvider)
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getBizLogicProvider();
        WEB3GentradeCommission l_commission =
            l_bizLogicProvider.createCommission(l_lngOrderId);
        
        //1.11. setIs指値()
        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_request.orderPriceDiv))
        {
            l_commission.setIsLimitPrice(true);
        }
        else
        {
            l_commission.setIsLimitPrice(false);
        }
        
        //1.12. getTradedProduct()
        WEB3EquityTradedProduct l_tradedProduct =
            (WEB3EquityTradedProduct)l_equityContract.getTradedProduct();
        
        //1.13. get注文訂正詳細()
        EqTypeContractSettleChangeOrderUnitEntry l_contractSettleChangeOrderUnitEntry = 
            l_changeSettleContractOrderSpec.getChangeOrderUnitEntry();
        
        //1.14. getAfterChangeTotalQuantity()
        double l_dblTotalQuantity =
            l_contractSettleChangeOrderUnitEntry.getAfterChangeTotalQuantity();
        
        //1.15. calc概算決済損益代金()
        WEB3EquityRealizedProfitAndLossPrice l_realizedProfitAndLossPrice = 
            l_orderManager.calcEstimatedRealizedProfitAndLossAmount(
                l_commission,
                l_dblAfterChangePrice,
                l_subAccount,
                l_tradedProduct,
                l_contractOrderEntry,
                l_dblTotalQuantity,
                l_orderUnit,
                0D,
                0D,
                false);
        
        //1.16. createResponse()
        WEB3MarginCloseMarginChangeConfirmResponse l_response =
            (WEB3MarginCloseMarginChangeConfirmResponse)l_request.createResponse();
        
        //1.17. 決済建株エントリ（EqtypeSettleContractOrderEntry[]）要素毎のLoop処理
        WEB3MarginContractUnit l_contractUnit;
        long l_lngContractOrderId;
        double l_dblCloseQuantity;
        List l_arrayList = new ArrayList();
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tm.getProductManager();
        for (int i = 0;i < l_contractOrderEntry.length;i++)
        {
            //1.17.1. 信用取引建株明細()
            l_contractUnit = new WEB3MarginContractUnit();
            //1.17.2. getContractId()
            l_lngContractOrderId = l_contractOrderEntry[i].getContractId();
            //1.17.3. getQuantity()
            l_dblCloseQuantity = l_contractOrderEntry[i].getQuantity();
            //1.17.4. get建株()
            WEB3EquityContract l_contract = null;
            try
            {
                l_contract = 
                    (WEB3EquityContract)l_positionManager.getContract(l_lngContractOrderId);
            }
            catch (NotFoundException l_ne)
            {
                throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            //1.17.5. getOpenDate()
            Date l_openDate = l_contract.getOpenDate();
            //1.17.6. getQuantity()
            double l_dblQuantity = l_contract.getQuantity();
            //1.17.7. getContractPrice()
            double l_dblcontractPrice = l_contract.getContractPrice();
            //1.17.8. get建代金()
            double l_dblcontractAmount = l_contract.getContractAmount(l_dblCloseQuantity);
            //1.17.9. get評価損益（建株諸経費考慮）()
            double l_dblCalcUnitPrice = 0.0D;
            if (!l_orderUnitEntry.isAfterChangePriceMarket())
            {
                l_dblCalcUnitPrice =
                    l_productManager.getCurrentPrice(
                        (EqTypeTradedProduct)l_contract.getTradedProduct());
            }
            else
            {
                l_dblCalcUnitPrice = l_realizedProfitAndLossPrice.getCalcUnitPrice();
            }
            double l_appraisalProfitOrLoss =
                l_contract.getAppraisalProfitOrLossExpenses(
                    l_dblCalcUnitPrice,
                    l_dblCloseQuantity,
                    l_orderUnit.getOrderUnitId());
            //1.17.10. get返済約定済数量()
            double l_dblClosingExecutedQuantity =
                l_contract.getClosingExecutedQuantity(l_orderUnit.getOrderUnitId());
            
            //1.17.11. プロパティセット
            // 信用取引建株明細.ID
            l_contractUnit.id = String.valueOf(l_lngContractOrderId);
            // 信用取引建株明細.建日
            l_contractUnit.openDate = WEB3DateUtility.toDay(l_openDate);
            // 信用取引建株明細.建単価
            l_contractUnit.contractPrice = WEB3StringTypeUtility.formatNumber(l_dblcontractPrice);
            // 信用取引建株明細.建株数
            l_contractUnit.contractQuantity =WEB3StringTypeUtility.formatNumber(l_dblQuantity);
            // 信用取引建株明細.建代金
            l_contractUnit.contractExecPrice = WEB3StringTypeUtility.formatNumber(l_dblcontractAmount);
            // 信用取引建株明細.評価損益
            l_contractUnit.appraisalProfitLoss = WEB3StringTypeUtility.formatNumber(l_appraisalProfitOrLoss);
            // 信用取引建株明細.注文株数
            l_contractUnit.orderQuantity = WEB3StringTypeUtility.formatNumber(l_dblCloseQuantity);
            // 信用取引建株明細.内出来株数
            l_contractUnit.partContQuantity = WEB3StringTypeUtility.formatNumber(l_dblClosingExecutedQuantity);
            // 信用取引建株明細.決済順位
            l_contractUnit.settlePriority = WEB3StringTypeUtility.formatNumber(i + 1);
            l_arrayList.add(l_contractUnit);
        }
        WEB3MarginContractUnit[] l_Contractunits = new WEB3MarginContractUnit[l_arrayList.size()]; 
        l_arrayList.toArray(l_Contractunits);
        
        //1.18. 信用取引手数料情報
        WEB3MarginCommissionInfoUnit l_commissionInfoUnit = new WEB3MarginCommissionInfoUnit();
        
        //1.19. calc消費税
        double l_dblcalSalesTax =
            l_bizLogicProvider.calcSalesTax(
                l_commission.getCommission(),
                l_commission.getOrderBizDate(),
                l_subAccount);
        
        //1.20. プロパティセット
        //信用取引手数料情報.手数料コース
        l_commissionInfoUnit.commissionCourse = l_commission.getCommissionCourseDiv();
        // 信用取引手数料情報.手数料
        l_commissionInfoUnit.commission = WEB3StringTypeUtility.formatNumber(l_commission.getCommission());
        // 信用取引手数料情報.手数料消費税
        l_commissionInfoUnit.commissionConsumptionTax = WEB3StringTypeUtility.formatNumber(l_dblcalSalesTax);
        
        //1.21. get市場閉局警告市場()
        EqtypeContractRow l_contractRow =
            (EqtypeContractRow)l_equityContract.getDataSourceObject();
        String[] l_strmessageSuspensions =
            WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
                l_subAccount.getWeb3GenBranch(),
                ProductTypeEnum.EQUITY,
                l_contractRow.getRepaymentType());

        //1.22. get概算決済損益代金()
        double l_dblEstimatedRealizedProfitAndLossAmount = 
                l_realizedProfitAndLossPrice.getEstimatedRealizedProfitAndLossAmount();
        
        //1.23. get計算単価()
        double l_dblCalcUnitPrice = l_realizedProfitAndLossPrice.getCalcUnitPrice();
        
        //1.24. isインサイダー警告表示(補助口座 : 補助口座, 銘柄ID : long)
        boolean l_isInsiderMessageSuspension = l_orderManager.isInsiderMessageSuspension(
            l_subAccount,
            l_orderUnitRow.getProductId());
        
        //1.25. プロパティセット
        //レスポンス.確認時発注日
        l_response.checkDate =  l_datBizDate;
        // レスポンス.概算受渡代金
        l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_dblEstimatedRealizedProfitAndLossAmount);
        // レスポンス.取引終了警告市場コード一覧
        l_response.messageSuspension = l_strmessageSuspensions;
        // レスポンス.建株明細一覧
        l_response.contractUnits = l_Contractunits;
        // レスポンス.手数料情報
        l_response.commissionInfo = l_commissionInfoUnit;
        // レスポンス.確認時単価
        l_response.checkPrice = WEB3StringTypeUtility.formatNumber(l_dblCalcUnitPrice);
        // レスポンス.インサイダー警告表示フラグ
        l_response.insiderWarningFlag = l_isInsiderMessageSuspension;
        // レスポンス.注文有効期限
        l_response.expirationDate = l_adapter.getExpirationDate();
            
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit注文)<BR>
     * 信用取引訂正返済注文を登録する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（信用取引訂正返済サービス）submit注文１」及び<BR>
     * 「（信用取引訂正返済サービス）submit注文２」参照。<BR>
     * 「信用取引訂正返済 / （信用取引訂正返済サービス）信用返済注文訂正内容作成」<BR>
     *    (注文株数チェック)<BR>
     *     訂正元注文がランダム以外(*)で、かつ、信用取引訂正返済リクエストアダプタ.get注文株数( )＝0の場合、<BR>
     *     例外をスローする。<BR>
     *     class: WEB3BusinessLayerException <BR>
     *     tag:   BUSINESS_ERROR_00650 <BR>
     * <BR>
     * @@param l_request - 信用取引訂正返済完了リクエストデータオブジェクト
     * @@return WEB3MarginCloseMarginChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4058288B027E
     */
    protected WEB3MarginCloseMarginChangeCompleteResponse submitOrder(
        WEB3MarginCloseMarginChangeCompleteRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "submitOrder(WEB3MarginCloseMarginChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate(); 

        //1.2. get補助口座()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //1.3. get代理入力者()
        Trader l_trader = this.getTrader();
        
        //1.4. get建株()
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tm = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tm.getPositionManager();
        long l_lngContractId = Long.parseLong(l_request.closeMarginContractUnits[0].id);
        WEB3EquityContract l_equityContract = null;
        try
        {
            l_equityContract =
                (WEB3EquityContract)l_positionManager.getContract(l_lngContractId);
        }
        catch (NotFoundException l_ne)
        {
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.7. get発注日()
        if (l_request.checkDate == null)
        {
            l_request.checkDate =
                WEB3GentradeTradingTimeManagement.getOrderBizDate();
        }
        WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
        
        //1.8. 信用返済注文訂正内容作成
        //1.8.1. create()
        WEB3MarginChangeCloseMarginRequestAdapter l_adapter = 
            WEB3MarginChangeCloseMarginRequestAdapter.create(l_request);
        //1.8.2. get注文株数()
        double l_dblOrderQuantity = l_adapter.getOrderQuantity();
        //1.8.3. getOrderUnits()
        WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager)
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        long l_lngOrderId = Long.parseLong(l_request.id);
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderManager.getOrderUnits(l_lngOrderId)[0];
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        //1.8.4. 注文株数チェック
        String l_strclosingOrderType = l_orderUnitRow.getClosingOrderType(); 
        if (l_strclosingOrderType == null || 
            WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(l_strclosingOrderType) ||
            WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(l_strclosingOrderType) ||
            WEB3ClosingOrderDef.OPEN_DATE.equals(l_strclosingOrderType)) 
        {
            if (l_adapter.getOrderQuantity() == 0.0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00650,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        //1.8.5. create決済建株エントリ
        if (l_request.orderQuantity == null)
        {
            l_dblOrderQuantity = 0.0D; 
        }
        EqTypeSettleContractOrderEntry[] l_contractOrderEntry = 
            l_orderManager.createClosingContractEntry(
                l_orderUnit.getOrderUnitId(),
                l_dblOrderQuantity,
                l_request.closeMarginContractUnits);
        //1.8.6. EqTypeContractSettleChangeOrderUnitEntry()
        double l_dblAfterChangePrice;
        if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.orderPriceDiv))
        {
            l_dblAfterChangePrice = 0.0D;
        }
        else
        {
            l_dblAfterChangePrice = Double.parseDouble(l_request.limitPrice);
        }
        EqTypeContractSettleChangeOrderUnitEntry l_orderUnitEntry =
            new EqTypeContractSettleChangeOrderUnitEntry(
                l_orderUnit.getOrderUnitId(),
                l_dblAfterChangePrice,
                l_contractOrderEntry);
        //1.8.7. get執行条件()
        EqTypeExecutionConditionType l_executeConType =
            l_adapter.getExecutionCondition();
        //1.8.8. is出来るまで注文()
        boolean l_isCarryOrder = l_adapter.isCarriedOrder();
        //1.8.9. create返済注文訂正内容()
        String l_strModifiedOrderCondOperator;  // 訂正後発注条件演算子
        double l_dblModifiedStopOrderPrice;     // 訂正後逆指値基準値
        double l_dblModifiedWLimitPrice;        // 訂正後（W指値）訂正指値
        // リクエスト.発注条件区分＝”指定なし”の場合
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_request.orderCondType))
        {
            l_strModifiedOrderCondOperator = "0";
            l_dblModifiedStopOrderPrice = 0.0D;
            l_dblModifiedWLimitPrice = 0.0D;
        }
        // リクエスト.発注条件区分＝”逆指値”の場合
        else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_strModifiedOrderCondOperator = l_request.stopOrderCondOperator;               
            l_dblModifiedStopOrderPrice = Double.parseDouble(l_request.stopOrderCondPrice);
            l_dblModifiedWLimitPrice = 0.0D;
        }
        // リクエスト.発注条件区分＝”W指値”の場合は、
        else
        {
            l_strModifiedOrderCondOperator = l_request.wlimitOrderCondOperator;
            l_dblModifiedStopOrderPrice = Double.parseDouble(l_request.wlimitOrderCondPrice);               
              
            if (l_request.wLimitPrice != null)
            {
                l_dblModifiedWLimitPrice =Double.parseDouble(l_request.wLimitPrice); 
            }
            else
            {
                l_dblModifiedWLimitPrice = 0.0D;
            }
        }
        // 訂正後注文失効日
        // 信用取引訂正返済リクエストアダプタ.get訂正後注文失効日( )の戻り値。
        Date l_datModifiedExpirationDate = l_adapter.getModifiedExpirationDate();

        WEB3MarginChangeSettleContractOrderSpec l_changeSettleContractOrderSpec = 
            WEB3MarginChangeSettleContractOrderSpec.createCloseMarginChangeOrderSpec(
                l_lngOrderId,
                l_orderUnitEntry,
                l_request.priceCondType,
                l_request.orderCondType,
                l_strModifiedOrderCondOperator,
                l_dblModifiedStopOrderPrice,
                l_dblModifiedWLimitPrice,
                l_executeConType,
                l_datModifiedExpirationDate,
                l_isCarryOrder,
                l_adapter.getModifiedWLimitExecCondType(),
                l_request.wlimitEnableStatusDiv);

        //1.9. validate返済注文訂正()
        
		EqTypeOrderValidationResult l_result = l_orderManager.validateChangeSettleContractOrder(
		l_subAccount,
		l_changeSettleContractOrderSpec);

        //throw発注審査結果エラー情報(OrderValidationResult, 証券会社, String)
        //発注審査結果：　@validate返済注文訂正()の戻り値
        //証券会社：　@補助口座.getInstitution()
        //銘柄コード：　@建株.getProduct().getProductCode()
        l_orderManager.throwOrderValidationResultErrorInfo(
            l_result,
            l_subAccount.getInstitution(),
            ((WEB3EquityProduct)(l_equityContract.getProduct())).getProductCode());

        //1.10. create手数料()
        WEB3EquityBizLogicProvider l_bizLogicProvider = (WEB3EquityBizLogicProvider)
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getBizLogicProvider();
        WEB3GentradeCommission l_commission =
            l_bizLogicProvider.createCommission(l_lngOrderId);
        
        //1.11. setIs指値()
        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_request.orderPriceDiv))
        {
            l_commission.setIsLimitPrice(true);
        }
        else
        {
            l_commission.setIsLimitPrice(false);
        }
        
        //1.12. getTradedProduct()
        WEB3EquityTradedProduct l_tradedProduct =
            (WEB3EquityTradedProduct)l_equityContract.getTradedProduct();
        
        //1.13. get注文訂正詳細()
        EqTypeContractSettleChangeOrderUnitEntry l_contractSettleChangeOrderUnitEntry = 
            l_changeSettleContractOrderSpec.getChangeOrderUnitEntry();
        
        //1.14. getAfterChangeTotalQuantity()
        double l_dblTotalQuantity =
            l_contractSettleChangeOrderUnitEntry.getAfterChangeTotalQuantity();
        
        //1.15. calc概算決済損益代金()
        if (l_request.checkPrice == null)
        {
            if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.orderPriceDiv))
            {
                l_request.checkPrice = "0";
            }
            else
            {
                l_request.checkPrice = l_request.limitPrice;
            }
        }
        WEB3EquityRealizedProfitAndLossPrice l_realizedProfitAndLossPrice = 
            l_orderManager.calcEstimatedRealizedProfitAndLossAmount(
                l_commission,
                Double.parseDouble(l_request.checkPrice),
                l_subAccount,
                l_tradedProduct,
                l_contractOrderEntry,
                l_dblTotalQuantity,
                l_orderUnit,
                0D,
                0D,
                false);
        
        //セキュリティサービスを取得
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(
                OpLoginSecurityService.class); 
        // 注文経路区分取得
        String l_strOrderRootDiv =
            l_opLoginSec.getSessionProperty(
                WEB3SessionAttributeDef.ORDER_ROOT_DIV);
                
        //1.16. 信用返済訂正更新インタセプタ()
        WEB3MarginChangeCloseMarginUpdateInterceptor l_updateInterceptor =
            new WEB3MarginChangeCloseMarginUpdateInterceptor(
                l_changeSettleContractOrderSpec,
                l_realizedProfitAndLossPrice,
                l_strOrderRootDiv,
                (WEB3GentradeTrader)this.getTrader());

        //1.17. setThreadLocalPersistenceEventInterceptor()
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);
        
        //1.19. submitChangeSettleContractOrder()
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
        EqTypeOrderSubmissionResult l_orderSubmissionResult =
            l_orderManager.submitChangeSettleContractOrder(
                l_subAccount,
                l_changeSettleContractOrderSpec,
                l_strTradingPassword,
                true);
        if (l_orderSubmissionResult.getProcessingResult().isFailedResult())
        {
            throw new WEB3BusinessLayerException(
                l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        List l_lisOrderUnits = null;
        if (l_orderManager.isReserveOrderConfirmRequire(l_orderUnit))
        {
            WEB3ToSuccReservationEqTypeOrderUpdateService l_updateService =
                (WEB3ToSuccReservationEqTypeOrderUpdateService)Services.getService(
                    WEB3ToSuccReservationEqTypeOrderUpdateService.class);
            l_lisOrderUnits =
                l_updateService.getOpenReserveEqtypeOrderUnits(l_orderUnit.getOrderId());
        }
        
        //1.20. 余力再計算
        WEB3TPTradingPowerService l_tradingpowerService =
        (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        l_tradingpowerService.reCalcTradingPower(l_subAccount);

        //1.21. createResponse()
        WEB3MarginCloseMarginChangeCompleteResponse l_response = 
            (WEB3MarginCloseMarginChangeCompleteResponse)l_request.createResponse();
        
        //1.22. isインサイダー警告表示()
		l_orderUnit = (EqTypeOrderUnit)l_orderManager.getOrderUnits(l_lngOrderId)[0];
        l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        boolean l_isInsiderMessageSuspension =
            l_orderManager.isInsiderMessageSuspension(
            l_subAccount,
            l_orderUnitRow.getProductId());
        
        //1.23. プロパティセット
        //l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
              
        // レスポンス.更新時間
        l_response.lastUpdatedTimestamp = l_orderUnitRow.getLastUpdatedTimestamp();

        // レスポンス.識別番号
        l_response.orderActionId = String.valueOf(l_orderUnitRow.getOrderId()); 
        // レスポンス.インサイダー警告表示フラグ
        l_response.insiderWarningFlag = l_isInsiderMessageSuspension;
        // レスポンス.注文有効期限
        l_response.expirationDate = l_adapter.getExpirationDate();
        if (l_lisOrderUnits != null)
        {
            l_response.succSettingFlag = true;
        }
        else
        {
            l_response.succSettingFlag = false;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
