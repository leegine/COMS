head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.41.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqCommonMessageCreatedServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式共通メッセージ作成サービスImpl(WEB3FeqCommonMessageCreatedServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/13  艾興(中訊) 新規作成
Revesion History : 2008/01/23 柴双紅(中訊) モデルNo.372
*/
package webbroker3.feq.service.delegate.stdimpls;


import java.math.BigDecimal;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3BuySellTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.feq.WEB3FeqFinTransactionManager;
import webbroker3.feq.WEB3FeqOrderExecution;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.define.WEB3FeqAccountDivDef;
import webbroker3.feq.message.WEB3AdminFeqExecuteGroup;
import webbroker3.feq.message.WEB3FeqExecDetailInfoUnit;
import webbroker3.feq.message.WEB3FeqOrderAndExecutionUnit;
import webbroker3.feq.message.WEB3FeqOrderCommonUnit;
import webbroker3.feq.service.delegate.WEB3FeqCommonMessageCreatedService;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (外国株式共通メッセージ作成サービスImpl) <BR>
 * 外国株式共通メッセージ作成サービス実装クラス
 * 
 * @@author 艾興
 * @@version 1.0 
 */
public class WEB3FeqCommonMessageCreatedServiceImpl implements WEB3FeqCommonMessageCreatedService 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqCommonMessageCreatedServiceImpl.class);
    
    /**
     * @@roseuid 42CE39F4033C
     */
    public WEB3FeqCommonMessageCreatedServiceImpl() 
    {
     
    }
    
    /**
     * (create外国株式注文共通明細) <BR>
     * 注文単位の内容で、外国株式注文共通明細メッセージ <BR>
     * オブジェクトプロパティに値をセットする。 <BR>
     *  <BR>
     * 　@外国株式注文共通明細の各プロパティに以下の通り値をセットする。 <BR>
     *  <BR>
     * 　@注文ＩＤ：　@注文単位.注文ＩＤ <BR>
     * 　@運用コード：　@注文単位.運用コード <BR>
     * 　@識別コード：　@注文単位.識別コード <BR>
     * 　@伝票番号：　@注文単位.伝票No <BR>
     * 　@部店コード：　@注文単位.get部店コード() <BR>
     * 　@顧客コード：　@注文単位.get顧客().get表示顧客コード() <BR>
     * 　@顧客名：　@注文単位.get顧客().get顧客表示名() <BR>
     * 　@銘柄コード：　@注文単位.getProduct().getProductCode() <BR>
     * 　@現地銘柄コード：　@注文単位.getProduct().getOffshoreProductCode() <BR>
     * 　@銘柄名：　@注文単位.getProduct().get表示銘柄名() <BR>
     * 　@市場コード：　@注文単位.get市場().市場コード  <BR>
     * 　@特定口座区分：　@注文単位.税区分に該当する区分 <BR>
     * 　@売買区分：　@注文単位.getSide()に該当する区分 <BR>
     * 　@決済区分：　@注文単位.決済区分 <BR>
     * 　@執行条件：　@注文単位.getExecutionConditionType()に該当する区分 <BR>
     * 　@注文有効期限：　@注文単位.注文失効日付 <BR>
     * 　@発注条件：　@注文単位.発注条件 <BR>
     * 　@発注条件単価：　@注文単位.逆指値基準値 <BR>
     * 　@発注条件演算子：　@注文単位.発注条件演算子 <BR>
     * 　@Ｗ指値用注文単価区分：　@ <BR>
     * 　@　@※（発注条件 == Ｗ指値）の場合のみセット <BR>
     * 　@　@注文単位.（W指値）訂正指値 == 0の場合、”成行”。以外”指値”。  <BR>
     * 　@Ｗ指値用注文単価：　@注文単位.（W指値）訂正指値<BR> 
     * 　@注文数量：　@注文単位.注文数量 <BR>
     * 　@注文単価区分：　@（注文単位.is指値() == true）の場合”指値”、 <BR>
     * 　@　@　@　@以外”成行”。 <BR>
     * 　@注文単価：　@注文単位.指値 <BR>
     * 　@通貨コード：　@注文単位.通貨コード <BR>
     * 　@概算受渡代金：　@注文単位.概算受渡代金 <BR>
     * 　@概算受渡代金（外貨）：　@注文単位.概算受渡代金（外貨） <BR>
     * 　@注文時間：　@注文単位.受注日時 <BR>
     * 　@発注日：　@注文単位.発注日 <BR>
     *   顧客区分：　@注文単位.顧客区分<BR>
     * @@param l_feqOrderCommonUnit - (外国株式注文共通明細) <BR>
     * 外国株式注文共通明細メッセージオブジェクト
     * 
     * @@param l_feqOrderUnit - (注文単位)
     * @@throws WEB3BaseException
     * @@roseuid 42943F200046
     */
    public void createFeqOrderCommonUnit(WEB3FeqOrderCommonUnit l_feqOrderCommonUnit, 
        WEB3FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createFeqOrderCommonUnit";
        log.entering(STR_METHOD_NAME);

        FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow)l_feqOrderUnit.getDataSourceObject();
        // 　@注文ＩＤ：　@注文単位.注文ＩＤ 
        l_feqOrderCommonUnit.orderId = "" + l_feqOrderUnitRow.getOrderId();
        // 　@運用コード：　@注文単位.運用コード 
        l_feqOrderCommonUnit.managementCode = l_feqOrderUnitRow.getOrderEmpCode();
        // 　@識別コード：　@注文単位.識別コード 
        l_feqOrderCommonUnit.requestNumber = l_feqOrderUnitRow.getOrderRequestNumber();
        // 　@伝票番号：　@注文単位.伝票No 
        l_feqOrderCommonUnit.orderNumber = l_feqOrderUnitRow.getVoucherNo();
        // 　@部店コード：　@注文単位.get部店コード() 
        l_feqOrderCommonUnit.branchCode = l_feqOrderUnit.getBranchCode();
        // 　@顧客コード：　@注文単位.get顧客().get表示顧客コード() 
        WEB3GentradeMainAccount l_mainAccount = 
            (WEB3GentradeMainAccount)l_feqOrderUnit.getMainAccount();
        l_feqOrderCommonUnit.accountCode = l_mainAccount.getDisplayAccountCode();
        // 　@顧客名：　@注文単位.get顧客().get顧客表示名() 
        l_feqOrderCommonUnit.accountName = 
            l_mainAccount.getDisplayAccountName();
        // 　@銘柄コード：　@注文単位.getProduct().getProductCode() 
        WEB3FeqProduct l_product = (WEB3FeqProduct)l_feqOrderUnit.getProduct();
        l_feqOrderCommonUnit.productCode = l_product.getProductCode();
        // 　@現地銘柄コード：　@注文単位.getProduct().getOffshoreProductCode() 
        l_feqOrderCommonUnit.localProductCode = l_product.getOffshoreProductCode();
        // 　@銘柄名：　@注文単位.getProduct().get表示銘柄名() 
        l_feqOrderCommonUnit.productName = l_product.getDisplayProductName();
        // 　@市場コード：　@注文単位.get市場().市場コード 
        l_feqOrderCommonUnit.marketCode= l_feqOrderUnit.getMarket().getMarketCode();
        // 　@特定口座区分：　@注文単位.税区分に該当する区分 
        if (TaxTypeEnum.NORMAL.equals(l_feqOrderUnitRow.getTaxType()))
        {
            l_feqOrderCommonUnit.taxType = WEB3TaxTypeSpecialDef.NORMAL;
        }
        else if (TaxTypeEnum.SPECIAL.equals(l_feqOrderUnitRow.getTaxType()) ||
            TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_feqOrderUnitRow.getTaxType()))
        {
            l_feqOrderCommonUnit.taxType = WEB3TaxTypeSpecialDef.SPECIAL;
        }
        
        // 　@売買区分：　@注文単位.getSide()に該当する区分 
        if (SideEnum.BUY.equals(l_feqOrderUnit.getSide()))
        {   
            l_feqOrderCommonUnit.dealingType = WEB3BuySellTypeDef.BUY;
        }
        else if (SideEnum.SELL.equals(l_feqOrderUnit.getSide()))
        {
            l_feqOrderCommonUnit.dealingType = WEB3BuySellTypeDef.SELL;
        }

        // 　@決済区分：　@注文単位.決済区分 
        l_feqOrderCommonUnit.settleDiv = l_feqOrderUnitRow.getSettleDiv();
        // 　@執行条件：　@注文単位.getExecutionConditionType()に該当する区分 
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqOrderManager l_orderManager = (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        String l_strExeCondType = "" + l_feqOrderUnit.getExecutionConditionType().intValue();
        l_feqOrderCommonUnit.execCondType = l_orderManager.getExecutionConditionTypeSonar(l_strExeCondType);
        // 　@注文有効期限：　@注文単位.注文失効日付 
        l_feqOrderCommonUnit.expirationDate = l_feqOrderUnitRow.getExpirationDate();
        // 　@発注条件：　@注文単位.発注条件 
        l_feqOrderCommonUnit.orderCondType = l_feqOrderUnitRow.getOrderConditionType();
        // 　@発注条件単価：　@注文単位.逆指値基準値 
        if (!l_feqOrderUnitRow.getStopOrderPriceIsNull())
        {
            l_feqOrderCommonUnit.orderCondPrice = 
                WEB3StringTypeUtility.formatNumber(l_feqOrderUnitRow.getStopOrderPrice());
        }

        // 　@発注条件演算子：　@注文単位.発注条件演算子 
        l_feqOrderCommonUnit.condOperator = l_feqOrderUnitRow.getOrderCondOperator();
        // 　@Ｗ指値用注文単価区分：　@ 
        // 　@　@※（発注条件 == Ｗ指値）の場合のみセット 
        //    注文単位.（W指値）訂正指値 == 0の場合、”成行”。以外”指値”。 
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_feqOrderUnitRow.getOrderConditionType()))
        {
            if (l_feqOrderUnitRow.getWLimitPrice() == 0)
            {
                l_feqOrderCommonUnit.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            }
            else
            {
                //Ｗ指値用注文単価：　@注文単位.（W指値）訂正指値 
                l_feqOrderCommonUnit.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                l_feqOrderCommonUnit.wLimitPrice = WEB3StringTypeUtility.formatNumber(l_feqOrderUnitRow.getWLimitPrice());
            }
        }

        // 　@注文数量：　@注文単位.注文数量 
        l_feqOrderCommonUnit.orderQuantity = 
            WEB3StringTypeUtility.formatNumber(l_feqOrderUnit.getQuantity());
        // 　@注文単価区分：　@（注文単位.is指値() == true）の場合”指値”、 
        // 　@　@　@　@以外”成行”。 
        if (l_feqOrderUnit.isLimitOrder())
        {
            l_feqOrderCommonUnit.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
        }
        else
        {
            l_feqOrderCommonUnit.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
        }

        // 　@注文単価：　@注文単位.指値 
        l_feqOrderCommonUnit.orderPrice = 
            WEB3StringTypeUtility.formatNumber(l_feqOrderUnit.getLimitPrice());
        // 　@通貨コード：　@注文単位.通貨コード 
        l_feqOrderCommonUnit.currencyCode = l_feqOrderUnitRow.getCurrencyCode();
        // 　@概算受渡代金：　@注文単位.概算受渡代金 
        l_feqOrderCommonUnit.estimatedPrice = 
            WEB3StringTypeUtility.formatNumber(l_feqOrderUnitRow.getEstimatedPrice());
        // 　@概算受渡代金（外貨）：　@注文単位.概算受渡代金（外貨） 
        l_feqOrderCommonUnit.foreignEstimatedPrice = 
            WEB3StringTypeUtility.formatNumber(l_feqOrderUnitRow.getFEstimatedPrice());
        // 　@注文時間：　@注文単位.受注日時 
        l_feqOrderCommonUnit.orderDate = 
            l_feqOrderUnitRow.getReceivedDateTime();
        // 　@発注日：　@注文単位.発注日 
        l_feqOrderCommonUnit.orderBizDate = 
            WEB3DateUtility.getDate(l_feqOrderUnitRow.getBizDate(), "yyyyMMdd");
        //  顧客区分：　@注文単位.顧客区分
        l_feqOrderCommonUnit.accountDiv = l_feqOrderUnitRow.getAccountDiv();
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (create外国株式約定詳細（管理者）) <BR>
     * 約定，トランザクション（取引勘定明細）の内容で、 <BR>
     * 外国株式約定詳細（管理者）メッセージオブジェクトプロパティに値をセットする。 <BR>
     *  <BR>
     * 　@外国株式約定詳細（管理者）の各プロパティに以下の通り値をセットする。 <BR>
     *  <BR>
     * 　@約定単価：　@約定.約定単価 <BR>
     * 　@約定数量：　@約定.約定数量 <BR>
     * 　@約定日時：　@約定.約定日時 <BR>
     * 　@約定ＩＤ：　@約定.約定ＩＤ <BR>
     * 　@約定番号：　@約定.約定通番を、pattern="000"でフォーマットした文字列  <BR>
     * 　@売買代金：　@約定.約定数量×約定.約定単価 <BR>
     * 　@現地手数料：　@トランザクション（取引勘定明細）.現地手数料 <BR>
     * 　@現地取引税：　@トランザクション（取引勘定明細）.現地取引税 <BR>
     * 　@その他コスト１：　@トランザクション（取引勘定明細）.その他コスト１ <BR>
     * 　@その他コスト２：　@トランザクション（取引勘定明細）.その他コスト２ <BR>
     * 　@清算代金（円貨）：　@トランザクション（取引勘定明細）.現地清算代金（円貨） <BR>
     * 　@清算代金（外貨）：　@トランザクション（取引勘定明細）.現地清算代金 <BR>
     * 　@国内手数料（円貨）：　@トランザクション（取引勘定明細）.委託手数料 <BR>
     * 　@国内手数料（外貨）：　@トランザクション（取引勘定明細）.委託手数料（外貨） <BR>
     * 　@消費税（円貨）：　@トランザクション（取引勘定明細）.委託手数料消費税 <BR>
     * 　@消費税（外貨）：　@トランザクション（取引勘定明細）.委託手数料消費税（外貨） <BR>
     * 　@受渡金額（円貨）：　@トランザクション（取引勘定明細）.受渡代金 <BR>
     * 　@受渡金額（外貨）：　@トランザクション（取引勘定明細）.受渡代金（外貨） <BR>
     * 　@国内受渡日：　@約定.受渡日 <BR>
     * @@param l_adminFeqExecDetailInfoUnit - (外国株式約定詳細（管理者）) <BR>
     * 外国株式約定詳細（管理者）メッセージオブジェクト
     * @@param l_feqExecute - (約定)
     * @@param l_feqFinTransactionParams - (トランザクション（取引勘定明細）行) <BR>
     * トランザクション（取引勘定明細）行オブジェクト
     * @@throws WEB3BaseException
     * @@roseuid 42A0458302FC
     */
    public void createAdminFeqExecDetailInfoUnit
        (WEB3FeqExecDetailInfoUnit l_adminFeqExecDetailInfoUnit, 
        WEB3FeqOrderExecution l_feqExecute, 
        FeqFinTransactionParams l_feqFinTransactionParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createAdminFeqExecDetailInfoUnit";
        log.entering(STR_METHOD_NAME);
        FeqOrderExecutionRow l_feqOrderExeRow = 
            (FeqOrderExecutionRow)l_feqExecute.getDataSourceObject();
        // 　@約定単価：　@約定.約定単価 
        l_adminFeqExecDetailInfoUnit.execPrice = WEB3StringTypeUtility.formatNumber(l_feqOrderExeRow.getExecPrice());
        // 　@約定数量：　@約定.約定数量 
        l_adminFeqExecDetailInfoUnit.execQuantity = WEB3StringTypeUtility.formatNumber(l_feqOrderExeRow.getExecQuantity());
        // 　@約定日時：　@約定.約定日時 
        l_adminFeqExecDetailInfoUnit.executionTimestamp = l_feqExecute.getExecutionTimestamp();
        // 　@約定ＩＤ：　@約定.約定ＩＤ 
        l_adminFeqExecDetailInfoUnit.execId = "" + l_feqOrderExeRow.getOrderExecutionId();
        // 　@約定番号：　@約定.約定通番を、pattern="000"でフォーマットした文字列 
        l_adminFeqExecDetailInfoUnit.execNo = 
            WEB3StringTypeUtility.formatNumber(l_feqExecute.getExecutionSerialNo(), 3);
        // 　@売買代金：　@約定.約定数量×約定.約定単価
        l_adminFeqExecDetailInfoUnit.foreignTradePrice = 
            WEB3StringTypeUtility.formatNumber(l_feqOrderExeRow.getExecPrice() * l_feqOrderExeRow.getExecQuantity());
            
        WEB3FeqProduct l_product = (WEB3FeqProduct)l_feqExecute.getProduct();
        WEB3GentradeCurrency l_currency = l_product.getCurrency();
        int l_intDecimalPlace = l_currency.getScale();
        BigDecimal l_bdforeignTradePrice = new BigDecimal(l_adminFeqExecDetailInfoUnit.foreignTradePrice);
        l_bdforeignTradePrice =
            l_bdforeignTradePrice.setScale(l_intDecimalPlace, BigDecimal.ROUND_HALF_EVEN);
        l_adminFeqExecDetailInfoUnit.foreignTradePrice = l_bdforeignTradePrice.toString();

        // 　@現地手数料：　@トランザクション（取引勘定明細）.現地手数料
        l_adminFeqExecDetailInfoUnit.localCommission = 
            WEB3StringTypeUtility.formatNumber(l_feqFinTransactionParams.getForeignCommissionFee());
        // 　@現地取引税：　@トランザクション（取引勘定明細）.現地取引税
        l_adminFeqExecDetailInfoUnit.localTradingTax = 
            WEB3StringTypeUtility.formatNumber(l_feqFinTransactionParams.getForeignTax());
        // 　@その他コスト１：　@トランザクション（取引勘定明細）.その他コスト１
        l_adminFeqExecDetailInfoUnit.otherCost1 = 
            WEB3StringTypeUtility.formatNumber(l_feqFinTransactionParams.getForeignFeeExt1());
        // 　@その他コスト２：　@トランザクション（取引勘定明細）.その他コスト２
        l_adminFeqExecDetailInfoUnit.otherCost2 = 
            WEB3StringTypeUtility.formatNumber(l_feqFinTransactionParams.getForeignFeeExt2());
        // 　@清算代金（円貨）：　@トランザクション（取引勘定明細）.現地清算代金（円貨）
        l_adminFeqExecDetailInfoUnit.clearUpPrice = 
            WEB3StringTypeUtility.formatNumber(l_feqFinTransactionParams.getBalanceAmount());
        // 　@清算代金（外貨）：　@トランザクション（取引勘定明細）.現地清算代金
        l_adminFeqExecDetailInfoUnit.foreignClearUpPrice = 
            WEB3StringTypeUtility.formatNumber(l_feqFinTransactionParams.getBalanceAmountFc());
        // 　@国内手数料（円貨）：　@トランザクション（取引勘定明細）.委託手数料
        l_adminFeqExecDetailInfoUnit.commission = 
            WEB3StringTypeUtility.formatNumber(l_feqFinTransactionParams.getCommissionFee());
        // 　@国内手数料（外貨）：　@トランザクション（取引勘定明細）.委託手数料（外貨）
        l_adminFeqExecDetailInfoUnit.foreignCommission =
            WEB3StringTypeUtility.formatNumber(l_feqFinTransactionParams.getCommissionFeeFc());
        // 　@消費税（円貨）：　@トランザクション（取引勘定明細）.委託手数料消費税
        l_adminFeqExecDetailInfoUnit.consumptionTax = 
            WEB3StringTypeUtility.formatNumber(l_feqFinTransactionParams.getCommissionFeeTax());
        // 　@消費税（外貨）：　@トランザクション（取引勘定明細）.委託手数料消費税（外貨）
        l_adminFeqExecDetailInfoUnit.foreignConsumptionTax = 
            WEB3StringTypeUtility.formatNumber(l_feqFinTransactionParams.getCommissionFeeTaxFc());

        //受渡代金 (円貨)
        if(l_feqFinTransactionParams.getFinTransactionType().equals(FinTransactionType.EQTYPE_FEQ_BUY))
        {   
            //受渡金額（円貨）：　@トランザクション（取引勘定明細）.受渡代金 ×（-1） 
            l_adminFeqExecDetailInfoUnit.deliveryPrice =
                WEB3StringTypeUtility.formatNumber(l_feqFinTransactionParams.getNetAmount() * (-1));
        }
        else if (l_feqFinTransactionParams.getFinTransactionType().equals(FinTransactionType.EQTYPE_FEQ_SELL))
        {
            //受渡金額（円貨）：　@トランザクション（取引勘定明細）.受渡代金
            l_adminFeqExecDetailInfoUnit.deliveryPrice =
                WEB3StringTypeUtility.formatNumber(l_feqFinTransactionParams.getNetAmount());
        }
        
        
        //受渡代金 (外貨)        
        if(l_feqFinTransactionParams.getFinTransactionType().equals(FinTransactionType.EQTYPE_FEQ_BUY))
        {   
            //受渡金額（外貨）：　@トランザクション（取引勘定明細）.受渡代金（外貨）×（-1） 
            l_adminFeqExecDetailInfoUnit.foreignDeliveryPrice =
                WEB3StringTypeUtility.formatNumber(l_feqFinTransactionParams.getNetAmountFc() * (-1));
        }
        else if (l_feqFinTransactionParams.getFinTransactionType().equals(FinTransactionType.EQTYPE_FEQ_SELL))
        {
            //受渡金額（外貨）：　@トランザクション（取引勘定明細）.受渡代金（外貨）            
            l_adminFeqExecDetailInfoUnit.foreignDeliveryPrice =
                WEB3StringTypeUtility.formatNumber(l_feqFinTransactionParams.getNetAmountFc());
        }  
                
        // 　@国内受渡日：　@約定.受渡日 
        l_adminFeqExecDetailInfoUnit.deliveryDate = l_feqExecute.getDeliveryDate();
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (create外国株式注文明細（管理者） <BR>
     * 約定，トランザクション（取引勘定明細）の内容で、 <BR>
     * 外国株式注文明細（管理者）メッセージオブジェクトプロパティに値をセットする。<BR>
     *  <BR>
     * １）　@superクラスの情報をセット <BR>
     * 　@this.create外国株式注文共通明細()をコールする。 <BR>
     *  <BR>
     * 　@[create外国株式注文共通明細()に指定する引数] <BR>
     * 　@外国株式注文共通明細：　@外国株式注文明細（管理者） <BR>
     * 　@注文単位：　@注文単位 <BR>
     *  <BR>
     * ２）　@個別プロパティセット <BR>
     * 　@外国株式注文明細（管理者）の各プロパティに以下の通り値をセットする。 <BR>
     *  <BR>
     * 　@注文履歴番号：　@注文単位.注文履歴最終通番 <BR>
     * 　@約定数量：　@注文単位.getExecutedQuantity() <BR>
     * 　@受渡代金（外貨）：　@ <BR>
     * 　@　@　@　@外国株式トランザクションマネージャ.get受渡代金合計（外貨）(注文単位)  <BR>
     * 　@処理状況： 注文単位.get処理状況() <BR>
     * @@param l_adminFeqExecuteGroup - (外国株式注文明細（管理者）) <BR>
     * 外国株式注文明細（管理者）メッセージオブジェクト
     * @@param l_feqOrderUnit - (注文単位)
     * @@throws WEB3BaseException
     * @@roseuid 4294416701EC
     */
    public void createAdminFeqExecuteGroup(WEB3AdminFeqExecuteGroup l_adminFeqExecuteGroup, 
        WEB3FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createAdminFeqExecuteGroup";
        log.entering(STR_METHOD_NAME);

        FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow)l_feqOrderUnit.getDataSourceObject();
        // １）　@superクラスの情報をセット 
        // 　@this.create外国株式注文共通明細()をコールする。 
        //  
        // 　@[create外国株式注文共通明細()に指定する引数] 
        // 　@外国株式注文共通明細：　@外国株式注文明細（管理者） 
        // 　@注文単位：　@注文単位 
        //
        this.createFeqOrderCommonUnit(l_adminFeqExecuteGroup, l_feqOrderUnit);
        // ２）　@個別プロパティセット 
        // 　@外国株式注文明細（管理者）の各プロパティに以下の通り値をセットする。 
        //  
        // 　@注文履歴番号：　@注文単位.注文履歴最終通番
        l_adminFeqExecuteGroup.orderActionId = "" + l_feqOrderUnitRow.getLastOrderActionSerialNo();
        // 　@約定数量：　@注文単位.getExecutedQuantity() 
        l_adminFeqExecuteGroup.execQuantity = 
            WEB3StringTypeUtility.formatNumber(l_feqOrderUnit.getExecutedQuantity());
        
        // 　@受渡代金：　@ 
        // 　@　@　@　@外国株式トランザクションマネージャ.get受渡代金合計(注文単位) 
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqFinTransactionManager l_finTransaction = 
            (WEB3FeqFinTransactionManager)l_tradingModule.getFinTransactionManager();
        double l_dblNetAmount = l_finTransaction.getNetAmount(l_feqOrderUnit);
        if (l_dblNetAmount == 0)
        {
            l_adminFeqExecuteGroup.deliveryPrice = null;
        }
        else
        {
            if(l_feqOrderUnit.isBuy())
            {
                //受渡金額（円貨） ×（-1）
                l_adminFeqExecuteGroup.deliveryPrice = 
                    WEB3StringTypeUtility.formatNumber(l_dblNetAmount * (-1));
            }
            else
            {
                l_adminFeqExecuteGroup.deliveryPrice = 
                    WEB3StringTypeUtility.formatNumber(l_dblNetAmount);
            }
        }

        //　@受渡代金（外貨）：　@外国株式トランザクションマネージャ.get受渡代金合計（外貨）(注文単位)
        double l_dblNetAmountFc = l_finTransaction.getNetAmountFc(l_feqOrderUnit);
        if (l_dblNetAmountFc == 0)
        {
            l_adminFeqExecuteGroup.foreignDeliveryPrice = null;
        }
        else
        {
            if(l_feqOrderUnit.isBuy())
            {
                //受渡金額（外貨） ×（-1） 
                l_adminFeqExecuteGroup.foreignDeliveryPrice = 
                    WEB3StringTypeUtility.formatNumber(l_dblNetAmountFc * (-1));
            }
            else
            {
                l_adminFeqExecuteGroup.foreignDeliveryPrice = 
                    WEB3StringTypeUtility.formatNumber(l_dblNetAmountFc);
            }
            
        }
        
        // 　@処理状況： 注文単位.get処理状況() 
        l_adminFeqExecuteGroup.transactionStateType = l_feqOrderUnit.getTransactionStateType();
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (create外国株式約定入力情報) <BR>
     * 注文単位，約定，トランザクション（取引勘定明細）の内容で、 <BR>
     * 外国株式注文明細（管理者）メッセージオブジェクトプロパティに値をセットする。 <BR>
     *  <BR> 
     * １）　@superクラスの情報をセット <BR>
     * 　@this.create外国株式注文共通明細()をコールする。 <BR>
     *  <BR>
     * 　@[create外国株式注文共通明細()に指定する引数] <BR>
     * 　@外国株式注文共通明細：　@外国株式約定入力情報 <BR>
     * 　@注文単位：　@注文単位 <BR>
     *  <BR>
     * ２）　@約定詳細作成 <BR>
     * 　@※約定情報が指定された場合 <BR>
     *   （トランザクション（取引勘定明細）行 != null && 約定 != null）のみ <BR>
     *  <BR>
     * 　@外国株式約定詳細（管理者）を生成し、 <BR>
     * 　@this.create外国株式約定詳細（管理者）()をコールし値をセットする。 <BR>
     *  <BR>
     * 　@[create外国株式約定詳細（管理者）()に指定する引数] <BR>
     * 　@外国株式約定詳細（管理者）：　@（生成したオブジェクト） <BR>
     * 　@約定：　@約定 <BR>
     * 　@トランザクション（取引勘定明細）行：　@トランザクション（取引勘定明細）行 <BR>
     *  <BR>
     * ３）　@個別プロパティセット <BR>
     * 　@外国株式注文明細（管理者）の各プロパティに以下の通り値をセットする。 <BR>
     *  <BR>
     * 　@顧客区分：　@ <BR>
     * 　@　@（外国株式約定入力情報.部店コードの右2byte == ”92”）の場合、 <BR>
     * 　@　@　@　@　@　@”2：自己”。 <BR>
     * 　@　@（外国株式約定入力情報.部店コードの右2byte != ”92”） &&  <BR>
     *     （外国株式約定入力情報.顧客コード 左2byte == ”80”）の場合、 <BR>
     * 　@　@　@　@　@　@”1：同業者”。 <BR>
     * 　@　@以外、”0：一般”。 <BR>
     * 　@扱者コード：　@注文単位.扱者コード（SONAR） <BR>
     *  <BR>
     * 　@○ 約定情報が指定された場合 <BR>
     *    （トランザクション（取引勘定明細）行 != null && 約定 != null） <BR>
     * 　@　@譲渡益：　@トランザクション（取引勘定明細行）.譲渡損益 <BR>
     * 　@　@譲渡益税：　@トランザクション（取引勘定明細行）.譲渡益税 <BR>
     * 　@　@約定為替レート：　@約定.為替レート <BR>
     * 　@　@現地受渡日：　@約定.現地受渡日 <BR>
     * 　@　@約定詳細：　@（２）で作成したオブジェクト） <BR>
     *  <BR>
     * 　@○ 約定情報が指定されていない場合 <BR>
     *    （トランザクション（取引勘定明細）行 == null || 約定 == null） <BR>
     * 　@　@約定為替レート：　@ <BR>
     * 　@　@　@　@　@注文単位.getProduct().get通貨().get為替レート()の戻り値 <BR>
     *  <BR>
     * 　@　@　@　@[get為替レート()に指定する引数] <BR>
     * 　@　@　@　@is買付：　@注文単位.getSide() == SideEnum.買の場合true、以外false。 <BR>
     * 　@　@　@　@is約定計算：　@true <BR>
     * 　@　@　@　@入力為替レート：　@0 <BR>
     *  <BR>
     * @@param l_feqOrderAndExecutionUnit - (外国株式約定入力情報) <BR>
     * 外国株式約定入力情報メッセージ
     * @@param l_feqOrderUnit - (注文単位)
     * @@param l_feqExecute - (約定) <BR>
     *  <BR>
     * ※ null指定可 <BR>
     * 
     * @@param l_feqFinTransactionParams - (トランザクション（取引勘定明細）行) <BR>
     * トランザクション（取引勘定明細）行オブジェクト <BR>
     *  <BR>
     * ※ null指定可 <BR>
     * @@throws WEB3BaseException
     * @@roseuid 429442B70102
     */
    public void createFeqOrderAndExecutionUnit(WEB3FeqOrderAndExecutionUnit l_feqOrderAndExecutionUnit, 
        WEB3FeqOrderUnit l_feqOrderUnit, WEB3FeqOrderExecution l_feqExecute, 
        FeqFinTransactionParams l_feqFinTransactionParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createFeqOrderAndExecutionUnit";
        log.entering(STR_METHOD_NAME);
        FeqOrderUnitRow l_feqOrderUnitRow = 
            (FeqOrderUnitRow)l_feqOrderUnit.getDataSourceObject();

        WEB3FeqExecDetailInfoUnit l_feqExecDetailInfoUnit = new WEB3FeqExecDetailInfoUnit();
        // １）　@superクラスの情報をセット 
        // 　@this.create外国株式注文共通明細()をコールする。 
        //  
        // 　@[create外国株式注文共通明細()に指定する引数] 
        // 　@外国株式注文共通明細：　@外国株式約定入力情報 
        // 　@注文単位：　@注文単位 
        //  
        this.createFeqOrderCommonUnit(l_feqOrderAndExecutionUnit, l_feqOrderUnit);
        // ２）　@約定詳細作成 
        // 　@※約定情報が指定された場合 
        //   （トランザクション（取引勘定明細）行 != null && 約定 != null）のみ 
        //  
        // 　@外国株式約定詳細（管理者）を生成し、 
        // 　@this.create外国株式約定詳細（管理者）()をコールし値をセットする。 
        //  
        // 　@[create外国株式約定詳細（管理者）()に指定する引数] 
        // 　@外国株式約定詳細（管理者）：　@（生成したオブジェクト） 
        // 　@約定：　@約定 
        // 　@トランザクション（取引勘定明細）行：　@トランザクション（取引勘定明細）行 
        //  
        if ((l_feqExecute != null) && (l_feqFinTransactionParams != null))
        {
            
            this.createAdminFeqExecDetailInfoUnit
                (l_feqExecDetailInfoUnit, l_feqExecute, l_feqFinTransactionParams);

        }
        // ３）　@個別プロパティセット 
        // 　@外国株式注文明細（管理者）の各プロパティに以下の通り値をセットする。 
        //  
        // 　@顧客区分：　@ 
        // 　@　@（外国株式約定入力情報.部店コードの右2byte == ”92”）の場合、 
        // 　@　@　@　@　@　@”2：自己”。 
        // 　@　@（外国株式約定入力情報.部店コードの右2byte != ”92”） &&  
        //     （外国株式約定入力情報.顧客コード 左2byte == ”80”）の場合、 
        // 　@　@　@　@　@　@”1：同業者”。 
        // 　@　@以外、”0：一般”。 
        if (l_feqOrderAndExecutionUnit.branchCode != null)
        {
            int l_length = l_feqOrderAndExecutionUnit.branchCode.length();
            if ("92".equals(l_feqOrderAndExecutionUnit.branchCode.substring(l_length-2)))
            {
                l_feqOrderAndExecutionUnit.accountDiv = WEB3FeqAccountDivDef.SELF;
            }
            else
            {
                if ("80".equals(l_feqOrderAndExecutionUnit.accountCode.substring(0, 2)))
                {
                    l_feqOrderAndExecutionUnit.accountDiv = WEB3FeqAccountDivDef.FELLOW_TRADER;
                }
                else
                {
                    l_feqOrderAndExecutionUnit.accountDiv = WEB3FeqAccountDivDef.NORMAL;
                }
            }
        }

        // 　@扱者コード：　@注文単位.扱者コード（SONAR） 
        //  
        l_feqOrderAndExecutionUnit.traderCode = l_feqOrderUnitRow.getSonarTraderCode();
        // 　@○ 約定情報が指定された場合 
        //    （トランザクション（取引勘定明細）行 != null && 約定 != null） 
        // 　@　@譲渡益：　@トランザクション（取引勘定明細行）.譲渡損益 
        // 　@　@譲渡益税：　@トランザクション（取引勘定明細行）.譲渡益税 
        // 　@　@約定為替レート：　@約定.為替レート 
        // 　@　@現地受渡日：　@約定.現地受渡日 
        // 　@　@約定詳細：　@（２）で作成したオブジェクト） 
        // 
        if ((l_feqFinTransactionParams != null) && (l_feqExecute != null))
        {
            FeqOrderExecutionRow l_feqOrderExeRow = 
                (FeqOrderExecutionRow)l_feqExecute.getDataSourceObject();
            l_feqOrderAndExecutionUnit.passProfit = 
                WEB3StringTypeUtility.formatNumber(l_feqFinTransactionParams.getCapitalGain());
            l_feqOrderAndExecutionUnit.passProfitTax = 
                WEB3StringTypeUtility.formatNumber(l_feqFinTransactionParams.getCapitalGainTax());
            l_feqOrderAndExecutionUnit.execExchangeRate = 
                WEB3StringTypeUtility.formatNumber(l_feqOrderExeRow.getFxRate());
            l_feqOrderAndExecutionUnit.localDeliveryDate = l_feqOrderExeRow.getFDeliveryDate();
            l_feqOrderAndExecutionUnit.execDetailInfoUnit = l_feqExecDetailInfoUnit;
        }
        // 　@○ 約定情報が指定されていない場合 
        //    （トランザクション（取引勘定明細）行 == null || 約定 == null） 
        // 　@　@約定為替レート：　@ 
        // 　@　@　@　@　@注文単位.getProduct().get通貨().get為替レート()の戻り値 
        //  
        // 　@　@　@　@[get為替レート()に指定する引数] 
        // 　@　@　@　@is買付：　@注文単位.getSide() == SideEnum.買の場合true、以外false。 
        // 　@　@　@　@is約定計算：　@true 
        // 　@　@　@　@入力為替レート：　@0 
        //
        else
        {
            WEB3FeqProduct l_feqProduct = (WEB3FeqProduct)l_feqOrderUnit.getProduct();
            boolean l_isBuy = false;
            if (SideEnum.BUY.equals(l_feqOrderUnit.getSide()))
            {
                l_isBuy = true;
            }
            double l_dblFxRate = l_feqProduct.getCurrency().getExchangeRate(l_isBuy, true, 0);
            l_feqOrderAndExecutionUnit.execExchangeRate = WEB3StringTypeUtility.formatNumber(l_dblFxRate);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
