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
filename	WEB3EquityExecuteReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式注文約定照会サービスImpl(WEB3EquityExecuteReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/18 中尾寿彦(SRA) 新規作成
Revesion History : 2006/07/05 肖志偉 (中訊) 仕様変更モデル942
Revesion History : 2006/07/15 栄イ 【株式】仕様変更管理台帳・モデル952を対応
Revesion History : 2006/07/20 栄イ 【株式】仕様変更管理台帳・モデル957を対応
Revesion History : 2006/08/02 栄イ (中訊) 仕様変更 モデルNo.959を対応
Revesion History : 2006/08/29 柴雙紅(中訊) 仕様変更モデル970
Revesion History : 2006/11/21 唐性峰(中訊)　@モデルNo.989,No.997,No.1077
Revesion History : 2007/10/16 金傑(中訊) 仕様変更モデル1196
Revesion History : 2008/02/15 趙林鵬(中訊) 仕様変更モデルNo.1300
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderAction;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityClientRequestService;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityFinTransactionManager;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.equity.define.WEB3EquityReferenceTypeDef;
import webbroker3.equity.define.WEB3EquityWlimitOrderPriceDivDef;
import webbroker3.equity.message.WEB3EquityChangeCancelHistoryGroup;
import webbroker3.equity.message.WEB3EquityCommissionInfoUnit;
import webbroker3.equity.message.WEB3EquityExecuteDetailsRequest;
import webbroker3.equity.message.WEB3EquityExecuteDetailsResponse;
import webbroker3.equity.message.WEB3EquityExecuteGroup;
import webbroker3.equity.message.WEB3EquityExecuteReferenceRequest;
import webbroker3.equity.message.WEB3EquityExecuteReferenceResponse;
import webbroker3.equity.message.WEB3EquityExecuteUnit;
import webbroker3.equity.message.WEB3EquityOrderHistoryRequest;
import webbroker3.equity.message.WEB3EquityOrderHistoryResponse;
import webbroker3.equity.message.WEB3EquitySortKey;
import webbroker3.equity.service.delegate.WEB3EquityExecuteReferenceService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3EnforcementDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderStatusDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3TradingTypeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeBranchMarketPTSDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;


/**
 * （現物株式注文約定照会サービスImpl）。<BR>
 * <BR>
 * 物株式注文約定照会サービス実装クラス
 * @@version 1.0
 */
public class WEB3EquityExecuteReferenceServiceImpl
    extends WEB3EquityClientRequestService
    implements WEB3EquityExecuteReferenceService
{

    /**
     * (ログ出力ユーティリティ。)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityExecuteReferenceServiceImpl.class);
   

    /**
     * @@roseuid 40A299DF003E
     */
    public WEB3EquityExecuteReferenceServiceImpl()
    {

    }

    /**
     * 現物株式注文約定照会サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型により、「search注文約定照会」<BR>
     * 「search注文約定詳細」「search注文約定履歴」の<BR>
     * いずれかのメソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4060DEB5012D
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3EquityExecuteReferenceRequest)
        {
            //リクエストデータの具象データ型が
            //「現物株式注文約定照会リクエスト」の場合
            l_response = this.searchExecuteReference(
                (WEB3EquityExecuteReferenceRequest) l_request);
        }
        else if (l_request instanceof WEB3EquityExecuteDetailsRequest)
        {
            //リクエストデータの具象データ型が
            //「現物株式注文約定詳細リクエスト」の場合
            l_response = this.searchExecuteDetails(
                (WEB3EquityExecuteDetailsRequest) l_request);
        }
        else if (l_request instanceof WEB3EquityOrderHistoryRequest)
        {
            //リクエストデータの具象データ型が
            //「現物株式注文約定履歴リクエスト」の場合
            l_response = this.searchOrderHistory(
                (WEB3EquityOrderHistoryRequest) l_request);
        }
        else
        {
            log.error(" __Error[入力値が不正です]__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (search注文約定照会）<BR>
     * 現物株式注文約定照会、現物株式訂正取消一覧<BR>
     * の処理を行う。<BR>
     * <BR>
     * シーケンス図「（注文約定照会）search注文約定照会」参照。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 現物株式注文約定照会リクエストオブジェクト<BR>
     * @@return webbroker3.equity.message.WEB3EquityExecuteReferenceResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 406A8FF7033B
     */
    public WEB3EquityExecuteReferenceResponse searchExecuteReference(
        WEB3EquityExecuteReferenceRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "searchExecuteReference(WEB3EquityExecuteReferenceRequest)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        
        //1.1. validate()
        l_request.validate();
        
        //1.2. get補助口座()
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)this.getSubAccount();
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
        BranchRow l_branchRow = (BranchRow)l_branch.getDataSourceObject();
        boolean l_blnOffFloorBranch = false;
        if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchRow.getOffFloorDiv()))
        {
            l_blnOffFloorBranch = true;
        }
        
        boolean l_isChangeOrderAccept = true;
        boolean l_isCancelOrderAccept = true;
        boolean l_isCancelOrderSalesOutsideMarketAccept = true;
        // 注文受付ステイタス
        boolean l_isReferenceAcceptBatch = false;
        boolean l_isReferenceAcceptScram = false;
        boolean l_isReferenceSalesOutsideMarketAcceptBatch = false;
        boolean l_isReferenceSalesOutsideMarketAcceptScram = false;
        String l_strErrorMassage = null;
        //1.3. リクエスト.照会区分＝"注文約定照会"の場合のみ実行
        if (WEB3EquityReferenceTypeDef.REFERENCE_TYPE_VIEW.equals(l_request.referenceType))
        {
            //1.3.1. validate注文受付可能()
            try
            {
                WEB3GentradeTradingTimeManagement.validateOrderAccept();
            }
            catch (WEB3BaseException l_exp)
            {
                if (l_exp instanceof WEB3BusinessLayerException)
                {
                    WEB3BusinessLayerException l_ble = (WEB3BusinessLayerException) l_exp;
                    // 注文受付ステイタス＝バッチ中
                    if (l_ble.getErrorInfo() == WEB3ErrorCatalog.BUSINESS_ERROR_00011)
                    {
                        l_isReferenceAcceptBatch = true;
                    }
                    // 注文受付ステイタス＝緊急停止中
                    else if (l_ble.getErrorInfo() == WEB3ErrorCatalog.BUSINESS_ERROR_00012)
                    {
                        l_isReferenceAcceptScram = true;
                    }                    
                }
            }
            if (l_blnOffFloorBranch == true)
            {
                //1.3.2. reset注文受付商品()
                WEB3GentradeTradingTimeManagement.resetOrderAcceptProduct(WEB3OrderAccProductDef.SALES_OUTSIDE_MARKET);
                //1.3.3. validate注文受付可能()
                try
                {
                    WEB3GentradeTradingTimeManagement.validateOrderAccept();
                }
                catch (WEB3BaseException l_exp)
                {
                    if (l_exp instanceof WEB3BusinessLayerException)
                    {
                        WEB3BusinessLayerException l_ble = (WEB3BusinessLayerException) l_exp;
                        // 注文受付ステイタス＝バッチ中
                        if (l_ble.getErrorInfo() == WEB3ErrorCatalog.BUSINESS_ERROR_00011)
                        {
                            l_isReferenceSalesOutsideMarketAcceptBatch = true;
                        }
                        // 注文受付ステイタス＝緊急停止中
                        else if (l_ble.getErrorInfo() == WEB3ErrorCatalog.BUSINESS_ERROR_00012)
                        {
                            l_isReferenceSalesOutsideMarketAcceptScram = true;
                        }                    
                    }
                }
            }

            // （立会外分売・実施会社の場合のみ）現物、立会外分売共に、注文受付ステイタス＝バッチ処理中
            if (l_isReferenceAcceptBatch == true &&
                l_isReferenceSalesOutsideMarketAcceptBatch == true)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00011,
                    WEB3GentradeTradingTimeManagement.class.getName()
                        + "." + STR_METHOD_NAME);
            }
            // （立会外分売・実施会社の場合のみ）現物、立会外分売共に、注文受付ステイタス＝緊急停止中
            else if (l_isReferenceAcceptScram == true &&
                     l_isReferenceSalesOutsideMarketAcceptScram == true)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00012,
                    WEB3GentradeTradingTimeManagement.class.getName()
                        + "." + STR_METHOD_NAME);
            }
            if (l_blnOffFloorBranch == true)
            {
                // （立会外分売・実施会社の場合のみ）
                // 現物、立会外分売どちらかが、注文受付ステイタス＝緊急停止中 or バッチ処理中
                if ((l_isReferenceAcceptBatch == true && 
                          l_isReferenceSalesOutsideMarketAcceptScram == true) ||
                         (l_isReferenceAcceptScram == true &&
                          l_isReferenceSalesOutsideMarketAcceptBatch == true))
                {
                    if (l_isReferenceAcceptScram)
                    {
                        l_strErrorMassage = "株式は[　@緊急停止中　@]、立会外分売は[　@バッチ処理中　@]となっております。";
                    }
                    else if (l_isReferenceSalesOutsideMarketAcceptScram)
                    {
                        l_strErrorMassage = "株式は［　@バッチ処理中　@]、立会外分売は[　@緊急停止中　@]となっております。";
                    }
                    // 緊急停止中
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00012,
                        WEB3GentradeTradingTimeManagement.class.getName()
                            + "." + STR_METHOD_NAME,
                            l_strErrorMassage);
                }
            }
            else
            {
                //（立会外分売・非実施会社の場合のみ）現物が注文受付ステイタス＝バッチ処理中
                if (l_isReferenceAcceptBatch == true)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00011,
                        WEB3GentradeTradingTimeManagement.class.getName()
                            + "." + STR_METHOD_NAME);
                }
                //（立会外分売・非実施会社の場合のみ）現物が注文受付ステイタス＝緊急停止中
                else if (l_isReferenceAcceptScram == true)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00012,
                        WEB3GentradeTradingTimeManagement.class.getName()
                            + "." + STR_METHOD_NAME);
                }
            }

            if (l_request.productCode != null)
            {
                try
                {
                    EqTypeProduct l_product =
                        l_productManager.getProduct(l_subAccount.getInstitution(), l_request.productCode);
                }
                catch (NotFoundException l_nfe)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                        this.getClass().getName() + "." +STR_METHOD_NAME,
                        l_nfe.getMessage(), null);
                }
            }
        }
        //1.4. リクエスト.照会区分＝"訂正取消一覧"の場合のみ実行
        else
        {
            //1.4.1. reset注文受付商品()
            WEB3GentradeTradingTimeManagement.resetOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);
            //1.4.2. reset注文受付トランザクション()
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.CHANGE);
            //1.4.3. validate注文受付可能()
            try
            {
                WEB3GentradeTradingTimeManagement.validateOrderAccept();
            }
            catch (WEB3BaseException l_exp)
            {
                l_isChangeOrderAccept = false;
            }
            //1.4.4. reset注文受付トランザクション()
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.CANCEL);
            //1.4.5. validate注文受付可能()
            try
            {
                WEB3GentradeTradingTimeManagement.validateOrderAccept();
            }
            catch (WEB3BaseException l_exp)
            {
                l_isCancelOrderAccept = false;
            }

            if (l_blnOffFloorBranch == true)
            {
                //1.4.6. reset受付時間区分
                WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.SALES_OUTSIDE_MARKET);
                //1.4.7. reset注文受付商品()
                WEB3GentradeTradingTimeManagement.resetOrderAcceptProduct(WEB3OrderAccProductDef.SALES_OUTSIDE_MARKET);
                //1.4.8. validate注文受付可能()
                try
                {
                    WEB3GentradeTradingTimeManagement.validateOrderAccept();
                    l_isCancelOrderSalesOutsideMarketAccept = true;
                }
                catch (WEB3BaseException l_exp)
                {
                    l_isCancelOrderSalesOutsideMarketAccept = false;
                }
            }
            else
            {
                l_isCancelOrderSalesOutsideMarketAccept = false;
            }

            if (!l_isChangeOrderAccept &&
                !l_isCancelOrderAccept &&
                !l_isCancelOrderSalesOutsideMarketAccept)
            {
                throw new WEB3BaseException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00146,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            //1.4.9. getOrderValidator()
            WEB3GentradeOrderValidator l_orderValidator =
                (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
            //1.4.10. validate取引可能顧客()
            OrderValidationResult l_orderValidationResult =
                l_orderValidator.validateSubAccountForTrading(l_subAccount);
            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.error("__Error[validate取引可能顧客をチェック]__");
                throw new WEB3SystemLayerException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            //1.4.11. リクエスト.銘柄コード指定時のみ実行
            if (l_request.productCode != null)
            {
                WEB3EquityProduct l_product = null;
                try
                {
                    l_product =
                        (WEB3EquityProduct)l_productManager.getProduct(
                            l_subAccount.getInstitution(),
                            l_request.productCode);
                }
                catch (NotFoundException l_nfe)
                {
					throw new WEB3BusinessLayerException(
						WEB3ErrorCatalog.BUSINESS_ERROR_00301, 
						STR_METHOD_NAME,
						l_nfe.getMessage(), 
						null);
                }
                //1.4.12. リクエスト.銘柄コード及び市場コード指定時のみ実行
                if (l_request.marketCode != null)
                {
                    //1.4.12.1. get市場()
                    Market l_market = null;
                    try
                    {
                        l_market = l_finObjectManager.getMarket(
                            l_subAccount.getInstitution().getInstitutionCode(),
                            l_request.marketCode);
                    }
                    catch (NotFoundException l_nfe)
                    {
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                            this.getClass().getName() + "." +STR_METHOD_NAME,
                            l_nfe.getMessage(),
                            l_nfe);
                    }
                    //1.4.12.2. validate取引銘柄()
                    WEB3EquityTradedProduct l_tradedProduct =
                        (WEB3EquityTradedProduct)l_orderManager.validateTradedProduct(
                            l_product,
                            l_market);
                    //1.4.12.3. validate取扱可能市場()
                    l_orderManager.validateHandlingMarket(
                        l_branch,
                        l_tradedProduct);
                }
            }
        }
        
        //1.5. createResponse()
        WEB3EquityExecuteReferenceResponse l_response =
            (WEB3EquityExecuteReferenceResponse)l_request.createResponse();
                
        //1.6. 銘柄コード・市場コード・発注日プルダウン作成
        // ＜手続き概要＞ ２）部分
        //1.6.2. get取扱可能市場()
        String[] l_strHandlingPossibleMarkets = null;
        try
        {
            l_strHandlingPossibleMarkets =
                WEB3GentradeBranchMarketDealtCond.getHandlingPossibleMarket(
                    l_branch,
                    ProductTypeEnum.EQUITY);
        }
        catch (WEB3BaseException l_exp)
        {
            log.debug(this.getClass().getName() + "." + STR_METHOD_NAME, l_exp);
        }
        
        // ＜手続き概要＞ ４）部分
        //1.6.25. ２）で作成した市場コード一覧を、レスポンス.市場コード一覧にセットする。
        l_response.marketList = l_strHandlingPossibleMarkets;
        //1.6.26. 発注日一覧を取得し、レスポンス.発注日一覧にセットする。
        TreeMap l_orderBizDateMap = new TreeMap();
        //1.6.26.1. システム日付の前営業日、及びシステム日付を、レスポンス.発注日一覧にセットする。
        Date l_datBizdate = GtlUtils.getTradingSystem().getBizDate();
        Timestamp l_tsBizdate = new Timestamp(l_datBizdate.getTime());
        Date l_datSysdate = WEB3DateUtility.toDay(l_tsBizdate);
        l_orderBizDateMap.put(l_datSysdate, l_datSysdate);
        WEB3GentradeBizDate l_genBizDate =
            new WEB3GentradeBizDate(l_tsBizdate);
        Date l_datBizDate = WEB3DateUtility.toDay(l_genBizDate.roll(-1));
        l_orderBizDateMap.put(l_datBizDate, l_datBizDate);
        //1.6.26.2. ２）で取得した市場コード一覧数分、LOOP
        int l_intMarketSize = 0;
        if (l_strHandlingPossibleMarkets != null)
        {
            l_intMarketSize = l_strHandlingPossibleMarkets.length;
        }
        
        //reset受付時間区分()
        WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        for (int i = 0;i < l_intMarketSize;i++)
        {
            //1.6.26.2.1. reset市場コード()
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_strHandlingPossibleMarkets[i]);
            //1.6.26.2.2. get発注日()
            l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            if (!l_orderBizDateMap.containsKey(l_datBizDate))
            {
                l_orderBizDateMap.put(l_datBizDate, l_datBizDate);
            }
        }
        //1.6.26.3. 取得した市場毎の発注日に、レスポンス.発注日一覧に含まれていない日付がある場合は、
        // 　@　@　@　@　@レスポンス.発注日一覧に追加する。
        int l_intListSize = l_orderBizDateMap.size();
        Date[] l_orderBizDateList = new Date[l_intListSize];
        Collection l_collection = l_orderBizDateMap.values();
        l_collection.toArray(l_orderBizDateList);
        l_response.orderBizDateList = l_orderBizDateList;

        //1.7. データ取得及びレスポンス設定
        // ＜手続き概要＞ １）部分
        //1.7.1. get注文単位一覧Fromリクエスト()
        List l_lisOrderUnit = null;
        List l_lisOrderUnitFinal = null;
        boolean l_isMarketTrading = true;
        boolean l_isSalesOutsideMarket = true;
        if (WEB3EquityReferenceTypeDef.REFERENCE_TYPE_VIEW.equals(l_request.referenceType))
        {
            if (l_isReferenceAcceptBatch || l_isReferenceAcceptScram)
            {   
                l_isMarketTrading = false;
            }
            if (l_isReferenceSalesOutsideMarketAcceptBatch || 
                l_isReferenceSalesOutsideMarketAcceptScram)
            {
                l_isSalesOutsideMarket = false;
            }
        }
        l_lisOrderUnit =
            this.getOrderUnitsFromRequest(
                l_subAccount,
                l_request,
                l_isMarketTrading,
                l_isSalesOutsideMarket);
        
        // ＜手続き概要＞ ２）部分
        List l_lisOrderUnitTmp = null;
        int l_intOrderUnitsFromRequestSize = 0;
        if (l_lisOrderUnit != null)
        {
            l_lisOrderUnitTmp = new ArrayList();
            l_intOrderUnitsFromRequestSize = l_lisOrderUnit.size();
        }
        long l_lngProductId = 0L;
        HashMap l_orderChangeImpossibleMap = new HashMap();
        HashMap l_orderCancelImpossibleMap = new HashMap();
        boolean l_validateTradedProduct = true;
        for (int i = 0;i < l_intOrderUnitsFromRequestSize;i++)
        {
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_lisOrderUnit.get(i);
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            if (WEB3EquityReferenceTypeDef.REFERENCE_TYPE_VIEW.equals(l_request.referenceType))
            {
                l_lisOrderUnitTmp.add(l_orderUnit);
            }
            // ２－１）
            String l_strTradingTimeType;
            String l_strOrderAcceptProduct;
            if (!WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(l_orderUnitRow.getSonarTradedCode()))
            {
                l_strTradingTimeType = WEB3TradingTimeTypeDef.EQUITY;
                l_strOrderAcceptProduct = WEB3OrderAccProductDef.STOCK;
            }
            else
            {
                l_strTradingTimeType = WEB3TradingTimeTypeDef.SALES_OUTSIDE_MARKET;
                l_strOrderAcceptProduct = WEB3OrderAccProductDef.SALES_OUTSIDE_MARKET;
            }
            // reset注文受付トランザクション()
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.CANCEL);
            //1.7.2. reset受付時間区分()
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(l_strTradingTimeType);
            //1.7.3. reset注文受付商品()
            WEB3GentradeTradingTimeManagement.resetOrderAcceptProduct(l_strOrderAcceptProduct);
            //1.7.4. reset市場コード()
            Market l_market = null;
            try
            {
                l_market = l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." +STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_market.getMarketCode());
            //1.7.5. validate注文受付可能()
            try
            {
                WEB3GentradeTradingTimeManagement.validateOrderAccept();
            }
            catch (WEB3BaseException l_exp)
            {
                l_orderChangeImpossibleMap.put(Long.toString(l_orderUnit.getOrderUnitId()), l_orderUnit);
                l_orderCancelImpossibleMap.put(Long.toString(l_orderUnit.getOrderUnitId()), l_orderUnit);
                continue;
            }
            
            // ２－２）
            //1.7.6. getProduct()
            WEB3EquityProduct l_product = (WEB3EquityProduct)l_orderUnit.getProduct();
            if (l_product.getProductId() != l_lngProductId)
            {
                l_lngProductId = l_product.getProductId();
                try
                {
                    //1.7.7. validate取引銘柄()
                    WEB3EquityTradedProduct l_tradedProduct =
                        (WEB3EquityTradedProduct)l_orderManager.validateTradedProduct(l_product, l_market);
                    //1.7.8. validate取扱可能市場()
                    l_orderManager.validateHandlingMarket(l_branch, l_tradedProduct);
                    l_validateTradedProduct = true;
                }
                catch (WEB3BaseException l_exp)
                {
                    l_validateTradedProduct = false;
                    l_orderChangeImpossibleMap.put(Long.toString(l_orderUnit.getOrderUnitId()), l_orderUnit);
                    l_orderCancelImpossibleMap.put(Long.toString(l_orderUnit.getOrderUnitId()), l_orderUnit);
                    continue;
                }
            }
            else
            {
                if (!l_validateTradedProduct)
                {
                    l_orderChangeImpossibleMap.put(Long.toString(l_orderUnit.getOrderUnitId()), l_orderUnit);
                    l_orderCancelImpossibleMap.put(Long.toString(l_orderUnit.getOrderUnitId()), l_orderUnit);
                    continue;
                }
            }
            
            // ２－３）①@
            //1.7.9. getCommonOrderValidator()
            WEB3GentradeOrderValidator l_orderValidator =
                (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
            //1.7.10. validate取引可能顧客()
            OrderValidationResult l_result = l_orderValidator.validateSubAccountForTrading(l_subAccount);
            if (l_result.getProcessingResult().isFailedResult())
            {
                l_orderChangeImpossibleMap.put(Long.toString(l_orderUnit.getOrderUnitId()), l_orderUnit);
                l_orderCancelImpossibleMap.put(Long.toString(l_orderUnit.getOrderUnitId()), l_orderUnit);
                continue;
            }
            
            // ２－３）④
            //1.7.13. getOrder()
            Order l_order = l_orderUnit.getOrder();
            //1.7.14. validate注文訂正可能状態()
            boolean l_blnIsOrderChangePossibleStatus = true;
            WEB3EquityTradedProduct l_tradedProduct = null;
            try
            {
                l_tradedProduct = (WEB3EquityTradedProduct) l_orderUnit.getTradedProduct();
                l_orderManager.validateOrderForChangeability(l_order);
            }
            catch (WEB3BaseException l_exp)
            {
                l_orderChangeImpossibleMap.put(Long.toString(l_orderUnit.getOrderUnitId()), l_orderUnit);
                l_blnIsOrderChangePossibleStatus = false;
            }
            catch (RuntimeSystemException l_rse)
            {
                l_orderChangeImpossibleMap.put(Long.toString(l_orderUnit.getOrderUnitId()), l_orderUnit);
                l_blnIsOrderChangePossibleStatus = false;
            }
            
            //1.7.15. 訂正可能かどうかのチェック
            if (l_blnIsOrderChangePossibleStatus)
            {
                try
                {
                    //1.7.15.2. validate取引銘柄()
                    l_orderManager.validateTradedProduct(
                        l_product,
                        l_market);
                    //1.7.15.3. validateインサイダー()
                    l_orderManager.validateInsider(l_subAccount, l_product);
                    //1.7.15.4. validate顧客銘柄別取引停止()
                    l_orderManager.validateAccountProductOrderStop(
                        l_subAccount,
                        l_orderUnitRow.getProductId(),
                        l_orderUnit.getOrderType());
                }
                catch (WEB3BaseException l_exp)
                {
                    l_orderChangeImpossibleMap.put(Long.toString(l_orderUnit.getOrderUnitId()), l_orderUnit);
                    l_blnIsOrderChangePossibleStatus = false;
                }
            }
            //1.7.16. validate注文取消可能状態()
            boolean l_blnIsOrderCancelPossibleStatus = true;
            try
            {
                if (l_tradedProduct != null)
                {
                    l_orderManager.validateOrderForCancellation(l_order);
                }
                else
                {
                    l_orderCancelImpossibleMap.put(Long.toString(l_orderUnit.getOrderUnitId()), l_orderUnit);
                    l_blnIsOrderCancelPossibleStatus = false;
                }
            }
            catch (WEB3BaseException l_exp)
            {
                l_orderCancelImpossibleMap.put(Long.toString(l_orderUnit.getOrderUnitId()), l_orderUnit);
                l_blnIsOrderCancelPossibleStatus = false;
            }
            if (!l_blnIsOrderChangePossibleStatus &&
                !l_blnIsOrderCancelPossibleStatus)
            {
                continue;
            }
            
			// ２－３）⑦
			//1.7.19. 取引時間管理.get発注日 ＞ 注文単位.発注日の場合のみ実施。
			Date l_bizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            
			Date l_orderBizDate =
				WEB3DateUtility.getDate(
					l_orderUnitRow.getBizDate(),
					WEB3GentradeTimeDef.DATE_FORMAT_YMD);
            
			if (WEB3DateUtility.compare(l_bizDate, l_orderBizDate) > 0)
			{
				//1.7.19.1. validate閉局後訂正取消受付可能()
				try
				{
					WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(ProductTypeEnum.EQUITY);
				}
				catch (WEB3BaseException l_exp)
				{
					l_orderChangeImpossibleMap.put(Long.toString(l_orderUnit.getOrderUnitId()), l_orderUnit);
					l_orderCancelImpossibleMap.put(Long.toString(l_orderUnit.getOrderUnitId()), l_orderUnit);
					continue;
				}
			}
            
            // ２－３）⑧及び⑨
            //1.7.20. 立会外分売注文の場合（＝注文単位.取引コード（SONAR）=="立会外分売"の場合）のみ実行
            if (WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(l_orderUnitRow.getSonarTradedCode()))
            {
                //1.7.20.1. get注文経路区分()
                if (WEB3OrderRootDivDef.HOST.equals(l_orderUnitRow.getOrderRootDiv()))
                {
                    l_orderChangeImpossibleMap.put(Long.toString(l_orderUnit.getOrderUnitId()), l_orderUnit);
                    l_orderCancelImpossibleMap.put(Long.toString(l_orderUnit.getOrderUnitId()), l_orderUnit);
                    continue;
                }
                //1.7.20.2. validate立会外分売受付可能()
                try
                {
                    l_orderManager.validateOffFloorOrderPossible(
                        l_orderUnitRow.getProductId(),
                        l_orderUnitRow.getMarketId(),
                        l_subAccount);
                }
                catch (WEB3BaseException l_exp)
                {
                    l_orderChangeImpossibleMap.put(Long.toString(l_orderUnit.getOrderUnitId()), l_orderUnit);
                    l_orderCancelImpossibleMap.put(Long.toString(l_orderUnit.getOrderUnitId()), l_orderUnit);
                    continue;
                }
            }
            if (WEB3EquityReferenceTypeDef.REFERENCE_TYPE_UPDATE.equals(l_request.referenceType))
            {
                l_lisOrderUnitTmp.add(l_orderUnit);
            }
        }
        
        // ＜手続き概要＞ ３）部分
        //1.7.21. リクエストデータ.約定状態区分！＝null　@の場合
        int l_intSize;
        if (l_request.execType != null)
        {
            l_lisOrderUnitFinal = new ArrayList();
            l_intSize = 0;
            if (l_lisOrderUnitFinal != null)
            {
                l_intSize = l_lisOrderUnitTmp.size();
            }
            for (int i = 0;i < l_intSize;i++)
            {
                EqTypeOrderUnit l_orderUnit =
                    (EqTypeOrderUnit)l_lisOrderUnitTmp.get(i);
                //1.7.21.1. is指定約定状態()
                if (this.isExecType(l_request.execType, l_orderUnit))
                {
                    l_lisOrderUnitFinal.add(l_orderUnit);
                }
            }
        }
        else
        {
            l_lisOrderUnitFinal = l_lisOrderUnitTmp;
        }
        
        // ＜手続き概要＞ ４）部分
        //1.7.22. remove繰越元注文単位()
        if (l_lisOrderUnitFinal != null)
        {
            l_intSize = l_lisOrderUnitFinal.size();
        }
        else
        {
            l_intSize = 0;
        }
        EqTypeOrderUnit[] l_orderUnits = new EqTypeOrderUnit[l_intSize];
        for (int i = 0;i < l_intSize;i++)
        {
            l_orderUnits[i] = (EqTypeOrderUnit)l_lisOrderUnitFinal.get(i);
        }
        l_orderUnits = l_orderManager.removeCarryOverOriginalOrderUnit(l_orderUnits);
        
        // ＜手続き概要＞ ５）部分
        if (l_orderUnits != null)
        {
            l_intSize = l_orderUnits.length;
        }
        else
        {
            l_intSize = 0;
        }
        int l_intPageSize = Integer.parseInt(l_request.pageSize);
        int l_intTotalPages = l_intSize / l_intPageSize;
        if (l_intSize % l_intPageSize > 0)
        {
            l_intTotalPages++;
        }
        if (l_intTotalPages > 0)
        {
            //レスポンス.総ページ数
            l_response.totalPages = Integer.toString(l_intTotalPages);
            //レスポンス.総レコード数
            l_response.totalRecords = Integer.toString(l_intSize);
            int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
            if (l_intSize <= (l_intPageSize * (l_intPageIndex - 1)))
            {
                l_intPageIndex = l_intTotalPages;
            }
            //レスポンス.表示ページ番号
            l_response.pageIndex = Integer.toString(l_intPageIndex);
            
            //レスポンス.ID一覧
            l_intSize = l_orderUnits.length;
            l_response.idList = new String[l_intSize];
            for (int i = 0;i < l_intSize;i++)
            {
                l_response.idList[i] = Long.toString(l_orderUnits[i].getOrderId());
            }
            // ＜手続き概要＞ ５）部分
            int l_intStart = l_intPageSize * (l_intPageIndex - 1);
            l_intSize = l_orderUnits.length;
            int l_intRecordCount = l_intPageSize;
            if ((l_intStart + l_intPageSize) > l_intSize)
            {
                l_intRecordCount -= l_intStart + l_intPageSize - l_intSize;
            }
            WEB3EquityExecuteGroup[] l_executeGroups =
                new WEB3EquityExecuteGroup[l_intRecordCount];
            for (int i = l_intStart, j = 0;j < l_intRecordCount;i++,j++)
            {
                EqtypeOrderUnitRow l_orderUnitRow =
                    (EqtypeOrderUnitRow)l_orderUnits[i].getDataSourceObject();
                    
                Market l_market = null;
                try
                {
                    l_market = l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
                }
                catch (NotFoundException l_nfe)
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." +STR_METHOD_NAME,
                        l_nfe.getMessage(),
                        l_nfe);
                }
                // reset市場コード()
                WEB3GentradeTradingTimeManagement.resetMarketCode(l_market.getMarketCode());

                // reset受付時間区分()
                // 受付時間区分：注文単位.取引コード（SONAR）=="立会外分売"の場合は、"立会外分売"
                //              以外、"株式・信用"
                String l_tradingTimeType = null;
                if(WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(l_orderUnitRow.getSonarTradedCode()))
                {
                    l_tradingTimeType = WEB3TradingTimeTypeDef.SALES_OUTSIDE_MARKET;
                }
                else
                {
                    l_tradingTimeType = WEB3TradingTimeTypeDef.EQUITY;
                }
                WEB3GentradeTradingTimeManagement.resetTradingTimeType(l_tradingTimeType);
                
                //1.7.23. get執行条件（SONAR）()
                String l_strExecCondType =
                    l_orderManager.getExecutionConditionTypeSonar(l_orderUnitRow.getExecutionConditionType());

                //get執行条件（SONAR）(EqTypeExecutionConditionType)
                //PR層に返す執行条件を取得する。
                //引数は以下の通りに設定する。
                //執行条件：　@注文単位.(W指値)執行条件
                String l_strExecutionConditionTypeSonar = null;
                if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
                {
                    l_strExecutionConditionTypeSonar =
                        l_orderManager.getExecutionConditionTypeSonar(
                            l_orderUnitRow.getWLimitExecCondType());
                }

                //getＷ指値用有効状態区分(EqTypeOrderUnit)
                //引数は以下の通りに設定する。
                //注文単位：　@レスポンスに設定する注文単位
                String l_strWLimitEnableStatusDiv =
                    WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_orderUnits[i]);

                // getＷ指値用切替前注文単価(EqTypeOrderUnit)
                //引数は以下の通りに設定する。
                //注文単位：　@レスポンスに設定する注文単位
                String l_strWLimitBefSwitchPrice =
                    WEB3EquityDataAdapter.getWLimitBefSwitchPrice(l_orderUnits[i]);

                //getＷ指値用切替前執行条件(EqTypeOrderUnit)
                //引数は以下の通りに設定する。
                //注文単位：　@レスポンスに設定する注文単位
                String l_strWLimitBefSwitchExecCondType =
                    WEB3EquityDataAdapter.getWLimitBefSwitchExecCondType(l_orderUnits[i]);

                // get元Ｗ指値用注文単価区分(EqTypeOrderUnit)
                //引数は以下の通りに設定する。
                //注文単位：　@レスポンスに設定する注文単位
                String l_strOrgWLimitOrderPriceDiv =
                    WEB3EquityDataAdapter.getOrgWLimitOrderPriceDiv(l_orderUnits[i]);

                // get元Ｗ指値用注文単価(EqTypeOrderUnit)
                //引数は以下の通りに設定する。
                //注文単位：　@レスポンスに設定する注文単位
                String l_strOrgWLimitOrderPrice =
                    WEB3EquityDataAdapter.getOrgWLimitOrderPrice(l_orderUnits[i]);

                //get元Ｗ指値用執行条件(EqTypeOrderUnit)
                //引数は以下の通りに設定する。
                //注文単位：　@レスポンスに設定する注文単位
                String l_strOrgWLimitExecCondType =
                    WEB3EquityDataAdapter.getOrgWLimitExecCondType(l_orderUnits[i]);

                //1.7.24. get処理状況区分()
                String l_strTransactionStateType = WEB3EquityDataAdapter.getTransactionStatusType(l_orderUnits[i]);

                // get遅延区分(EqTypeOrderUnit)
                //引数は以下の通りに設定する。
                //注文単位：　@レスポンスに設定する注文単位
                String l_strDelayDiv =
                    WEB3EquityDataAdapter.getDelayDiv(l_orderUnits[i]);

                // is手動発注可能(EqTypeOrderUnit)
                //引数は以下の通りに設定する。
                //注文単位：　@レスポンスに設定する注文単位
                boolean l_blnIsManualOrderPossible =
                    l_orderManager.isManualOrderPossible(l_orderUnits[i]);

                //1.7.25. getExecutions()
                OrderExecution[] l_orderExecutions = null;
                if (!l_orderUnits[i].isUnexecuted())
                {
                    l_orderExecutions = l_orderUnits[i].getExecutions();
                }
                //レスポンス.注文情報一覧.ID
                l_executeGroups[j] = new WEB3EquityExecuteGroup();
                l_executeGroups[j].id = Long.toString(l_orderUnits[i].getOrderId());
                //レスポンス.注文情報一覧.銘柄コード
                WEB3EquityProduct l_product = (WEB3EquityProduct)l_orderUnits[i].getProduct();
                EqtypeProductRow l_productRow = (EqtypeProductRow)l_product.getDataSourceObject();
                l_executeGroups[j].productCode = l_product.getProductCode();
                //レスポンス.注文情報一覧.銘柄名
                l_executeGroups[j].productName = l_productRow.getStandardName();
                //レスポンス.注文情報一覧.市場コード
                l_executeGroups[j].marketCode = l_market.getMarketCode();
                //レスポンス.注文情報一覧.口座区分
                l_executeGroups[j].taxType = WEB3EquityDataAdapter.getTaxType(l_orderUnitRow.getTaxType());
                //レスポンス.注文情報一覧.取引区分
                if (OrderTypeEnum.EQUITY_BUY.equals(l_orderUnitRow.getOrderType()))
                {
                    if (!WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(l_orderUnitRow.getSonarTradedCode()))
                    {
                        l_executeGroups[j].tradingType = WEB3TradingTypeDef.BUY_ORDER;
                    }
                    else
                    {
                        l_executeGroups[j].tradingType = WEB3TradingTypeDef.PRESENCE_ORDER;
                    }
                }
                else
                {
                    l_executeGroups[j].tradingType = WEB3TradingTypeDef.SELL_ORDER;
                }
                //レスポンス.注文情報一覧.値段条件
                l_executeGroups[j].priceCondType = l_orderUnitRow.getPriceConditionType();
                //レスポンス.注文情報一覧.執行条件
                l_executeGroups[j].execCondType = l_strExecCondType;
                //レスポンス.注文情報一覧.発注条件区分
                l_executeGroups[j].orderCondType = l_orderUnitRow.getOrderConditionType();
                if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
                {
					//レスポンス.注文情報一覧.逆指値用発注条件単価
					l_executeGroups[j].stopOrderCondPrice = 
						WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
					//レスポンス.注文情報一覧.逆指値用発注条件演算子
					l_executeGroups[j].stopOrderCondOperator = l_orderUnitRow.getOrderCondOperator();
                }
                else if(WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
                {
                	//レスポンス.注文情報一覧.Ｗ指値用発注条件単価
                	l_executeGroups[j].wlimitOrderCondPrice = 
                		WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
                	//レスポンス.注文情報一覧.Ｗ指値用発注条件演算子
                	l_executeGroups[j].wlimitOrderCondOperator = l_orderUnitRow.getOrderCondOperator();
                	//レスポンス.注文情報一覧.W指値用注文単価区分
                	if (l_orderUnitRow.getWLimitPrice() == 0)
                	{
						l_executeGroups[j].wLimitOrderPriceDiv = 
							WEB3EquityWlimitOrderPriceDivDef.WLIMIT_ORDER_PRICE_DIV_MARKET_PRICE;	
                	}
                	else
                	{
						l_executeGroups[j].wLimitOrderPriceDiv = 
							WEB3EquityWlimitOrderPriceDivDef.WLIMIT_ORDER_PRICE_DIV_LIMIT_PRICE;
						//レスポンス.注文情報一覧.W指値用注文単価
						l_executeGroups[j].wLimitPrice = 
							WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getWLimitPrice());
                	}
                    //レスポンス.注文情報一覧.W指値用執行条件：
                    //注文単位.発注条件＝2（W指値）の場合のみ、
                    //拡張株式注文マネージャ.get執行条件（SONAR）(注文単位.(W指値)執行条件)の戻り値をセット。
                    l_executeGroups[j].wlimitExecCondType = l_strExecutionConditionTypeSonar;
                }
                //レスポンス.注文情報一覧.W指値用有効状態区分：　@
                //株式データアダプタ.getW指値用有効状態区分（注文単位）の戻り値をセット。
                l_executeGroups[j].wlimitEnableStatusDiv = l_strWLimitEnableStatusDiv;

                //レスポンス.注文情報一覧.W指値用切替前注文単価：　@
                //株式データアダプタ.getW指値用切替前注文単価（注文単位）の戻り値をセット。
                l_executeGroups[j].wlimitBefChgLimitPrice = l_strWLimitBefSwitchPrice;

                //レスポンス.注文情報一覧.W指値用切替前執行条件：　@
                //株式データアダプタ.getW指値用切替前執行条件（注文単位）の戻り値をセット。
                l_executeGroups[j].wlimitBefChgExecCondType = l_strWLimitBefSwitchExecCondType;

                //レスポンス.注文情報一覧.元発注条件区分
                l_executeGroups[j].orgOrderCondType = l_orderUnitRow.getOrgOrderConditionType();
                //レスポンス.注文情報一覧.元発注条件単価
                if (l_orderUnitRow.getOrgStopOrderPriceIsNull())
                {
                    l_executeGroups[j].orgOrderCondPrice = null;
                }
                else
                {
                    l_executeGroups[j].orgOrderCondPrice = 
                        WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getOrgStopOrderPrice());
                }

                //レスポンス.注文情報一覧.元発注条件演算子
                l_executeGroups[j].orgOrderCondOperator = l_orderUnitRow.getOrgOrderCondOperator();

                //レスポンス.注文情報一覧.元W指値用注文単価区分：
                //株式データアダプタ.get元W指値用注文単価区分（注文単位）の戻り値をセット。
                l_executeGroups[j].orgWlimitOrderPriceDiv = l_strOrgWLimitOrderPriceDiv;

                //レスポンス.注文情報一覧.元W指値用注文単価：　@
                //レスポンス.元W指値用注文単価区分が"指値"の場合、株式データアダプタ.get元W指値用注文単価（注文単位）の戻り値をセット。
                if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrgWLimitOrderPriceDiv))
                {
                    l_executeGroups[j].orgWlimitPrice = l_strOrgWLimitOrderPrice;
                }

                //レスポンス.注文情報一覧.元W指値用執行条件：　@
                //株式データアダプタ.get元W指値用執行条件（注文単位）の戻り値をセット。
                l_executeGroups[j].orgWlimitExecCondType = l_strOrgWLimitExecCondType;
                
                //レスポンス.注文情報一覧.注文株数
                l_executeGroups[j].orderQuantity =
                    WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getQuantity());
                //レスポンス.注文情報一覧.注文単価区分
                if (l_orderUnits[i].isMarketOrder())
                {
                    l_executeGroups[j].orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                }
                else
                {
                    l_executeGroups[j].orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                }
                //レスポンス.注文情報一覧.注文単価
                if (!l_orderUnits[i].isMarketOrder())
                {
                    l_executeGroups[j].limitPrice =
                        WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getLimitPrice());
                }
                if (!l_orderUnits[i].isUnexecuted())
                {
                    //レスポンス.注文情報一覧.約定株数
                    l_executeGroups[j].execQuantity =
                        WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getExecutedQuantity());
                    //レスポンス.注文情報一覧.約定単価
                    double l_dblExecPrice =
                        Math.round(l_orderUnitRow.getExecutedAmount() / l_orderUnitRow.getExecutedQuantity());
                    l_executeGroups[j].execPrice =
                        WEB3StringTypeUtility.formatNumber(l_dblExecPrice);

                    WEB3EquityFinTransactionManager l_finTransactionManager =
                        (WEB3EquityFinTransactionManager)l_tradingModule.getFinTransactionManager();
                    //getトランザクション
                    List l_lisTransactions = l_finTransactionManager.getTransactions(l_orderUnits[i]);
                    //レスポンス.受渡代金
                    //get失効時受渡代金
                    l_executeGroups[j].deliveryPrice =
                        WEB3StringTypeUtility.formatNumber(
                            l_orderManager.getEstimateDeliveryAmountForClose(l_orderUnits[i],l_lisTransactions));
                    //レスポンス.注文情報一覧.概算損益
                    l_executeGroups[j].estimatedProfitLoss = l_finTransactionManager.getEstimatedProfitLoss(l_lisTransactions);
                }
                //レスポンス.注文情報一覧.概算受渡代金
                l_executeGroups[j].estimatedPrice =
                WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getEstimatedPrice());
                //レスポンス.注文情報一覧.注文時間
                l_executeGroups[j].orderDate = l_orderUnitRow.getCreatedTimestamp();
                //レスポンス.注文情報一覧.発注日
                l_executeGroups[j].orderBizDate =
                    WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), "yyyyMMdd");
                //レスポンス.注文情報一覧.注文有効期限
                if (l_orderManager.isCarriedOrderUnit(l_orderUnits[i]))
                {
                    l_executeGroups[j].expirationDate = l_orderUnitRow.getExpirationDate();
                }
                //レスポンス.注文情報一覧.処理状況
                l_executeGroups[j].transactionStateType = l_strTransactionStateType;
                //レスポンス.注文情報一覧.訂正可能フラグ
                String l_strOrderUnitId = Long.toString(l_orderUnitRow.getOrderUnitId());
                if (l_orderChangeImpossibleMap.containsKey(l_strOrderUnitId))
                {
                    l_executeGroups[j].changeFlag = false;
                }
                else
                {
                    l_executeGroups[j].changeFlag = true;
                }
                //レスポンス.注文情報一覧.取消可能フラグ
                if (l_orderCancelImpossibleMap.containsKey(l_strOrderUnitId))
                {
                    l_executeGroups[j].cancelFlag = false;
                }
                else
                {
                    l_executeGroups[j].cancelFlag = true;
                }
                if (this.getTrader() != null)
                {
                    //レスポンス.注文情報一覧.注文チャネル
                    l_executeGroups[j].orderChannel = l_orderUnitRow.getOrderChanel();
                    //レスポンス.注文情報一覧.注文経路区分
                    l_executeGroups[j].orderRootDiv = l_orderUnitRow.getOrderRootDiv();
                    //レスポンス.注文情報一覧.オペレータコード
                    if (l_orderUnitRow.getTraderIdIsNull() == false)
                    {
                        try
                        {
                            l_executeGroups[j].operatorCode =
                                l_finObjectManager.getTrader(l_orderUnitRow.getTraderId()).getTraderCode();
                        }
                        catch (NotFoundException l_nfe)
                        {
                            l_executeGroups[j].operatorCode = null;
                        }
                    }
                }

                //レスポンス.注文情報一覧.遅延区分：　@株式データアダプタ.get遅延区分（注文単位）の戻り値をセット。
                l_executeGroups[j].delayDiv = l_strDelayDiv;

                //レスポンス.注文情報一覧.手動発注可能フラグ：　@
                //拡張株式注文マネージャ.is手動発注可能（注文単位）の戻り値をセット。
                l_executeGroups[j].manualFlag = l_blnIsManualOrderPossible;

                if (l_orderExecutions != null)
                {
                    WEB3EquityExecuteUnit[] l_execUnit =
                        new WEB3EquityExecuteUnit[l_orderExecutions.length];
                    for (int k = 0;k < l_orderExecutions.length;k++)
                    {
                        //レスポンス.注文情報一覧.分割約定一覧.約定日時
                        l_execUnit[k] = new WEB3EquityExecuteUnit();
                        l_execUnit[k].executionTimestamp = l_orderExecutions[k].getExecutionTimestamp();
                        //レスポンス.注文情報一覧.分割約定一覧.約定数量
                        l_execUnit[k].execQuantity =
                            WEB3StringTypeUtility.formatNumber(l_orderExecutions[k].getExecutionQuantity());
                        //レスポンス.注文情報一覧.分割約定一覧.約定単価
                        l_execUnit[k].execPrice =
                             WEB3StringTypeUtility.formatNumber(l_orderExecutions[k].getExecutionPrice());

                    }
                    l_executeGroups[j].executeUnits = l_execUnit;
                }
            }
            l_response.executeGroups = l_executeGroups;
        }
        
        //1.9. get市場閉局警告市場()
        WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        String[] l_strCloseMarkets =
            WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
                l_branch,
                ProductTypeEnum.EQUITY,
                WEB3MarginTradingDivDef.DEFAULT);
        l_response.messageSuspension = l_strCloseMarkets;
        
        if (l_response.executeGroups == null ||
            l_response.marketList == null)
        {
            //レスポンス.総ページ数
            l_response.totalPages = "0";
            //レスポンス.総レコード数
            l_response.totalRecords = "0";
            //レスポンス.表示ページ番号
            l_response.pageIndex = "0";
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (search注文約定詳細)<BR>
     * 指定された注文単位IDを持つ注文単位オブジェクトの内容を画面表示用に編集し、<BR>
     * レスポンスに設定して返す。<BR>
     * <BR>
     * シーケンス図「（注文約定照会）search注文約定詳細」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 現物株式注文約定詳細リクエストオブジェクト<BR>
     * @@return webbroker3.equity.message.WEB3EquityExecuteDetailsResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 406A8FFA00BA
     */
    public WEB3EquityExecuteDetailsResponse searchExecuteDetails(WEB3EquityExecuteDetailsRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "searchExecuteDetails(WEB3EquityExecuteDetailsRequest)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager = 
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            
        //1.1. validate()
        l_request.validate();
        
        //1.2. getOrderUnits()
        OrderUnit[] l_orderUnits =
            l_orderManager.getOrderUnits(Long.parseLong(l_request.id));
        if (l_orderUnits.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "指定した注文IDに該当するデータが存在しません。");
        }
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        EqtypeOrderUnitRow l_orderUnitRow =
        (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        //reset受付時間区分(受付時間区分 : String)
        //注文単位.取引コード（SONAR）≠"立会外分売"の場合、"株式・信用"。
        //注文単位.取引コード（SONAR）＝"立会外分売"の場合、"立会外分売"
        //
        //1.3. reset注文受付商品()
        if (!WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(l_orderUnitRow.getSonarTradedCode()))
        {
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
            WEB3GentradeTradingTimeManagement.resetOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);
        }
        else
        {
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3OrderAccProductDef.SALES_OUTSIDE_MARKET);
            WEB3GentradeTradingTimeManagement.resetOrderAcceptProduct(WEB3OrderAccProductDef.SALES_OUTSIDE_MARKET);
        }
        
        //1.4. validate注文受付可能()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.5. get補助口座()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //1.6. get手数料コースコード()
        WEB3EquityBizLogicProvider l_bizLogicProvider =
            (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
        Date l_datBizDate = WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), "yyyyMMdd");
        String l_strCommissionCourseDiv = null;
        try
        {
            l_strCommissionCourseDiv =
                l_bizLogicProvider.getCommissionCourseDiv(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_orderUnitRow.getCommProductCode(),
                    l_orderUnitRow.getCommTblNo(),
                    l_orderUnitRow.getCommTblSubNo(),
                    l_datBizDate);
        }
        //前日注文について手数料マスタがなくなってしまう可能性もあるため、
        //「該当データ無しについては」エラー終了しなようにする
        catch (WEB3SystemLayerException l_wse)
        {
            if (l_wse.getErrorInfo() != WEB3ErrorCatalog.SYSTEM_ERROR_80005)
            {
                throw l_wse;
            }
        }
        
        //1.7. createResponse()
        WEB3EquityExecuteDetailsResponse l_response =
            (WEB3EquityExecuteDetailsResponse)l_request.createResponse();
        
        //1.8. get執行条件（SONAR）()
        String l_strExecutionConditionType =
            l_orderManager.getExecutionConditionTypeSonar(l_orderUnitRow.getExecutionConditionType());
        
        //1.9. get注文状態区分()
        String l_orderStatus = WEB3EquityDataAdapter.getOrderState(l_orderUnit);
        
        //1.10. get約定状態区分()
        String l_execType = WEB3EquityDataAdapter.getExecType(l_orderUnit);

        //W指値の場合
        //get執行条件（SONAR）(EqTypeExecutionConditionType)
        //PR層に返す執行条件を取得する。
        //引数は以下の通りに設定する。
        //執行条件：　@注文単位.(Ｗ指値用)執行条件
        String l_strExecutionConditionTypeSonar = null;
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            l_strExecutionConditionTypeSonar =
                l_orderManager.getExecutionConditionTypeSonar(l_orderUnitRow.getWLimitExecCondType());
        }

        //getＷ指値用有効状態区分(EqTypeOrderUnit)
        //引数は以下の通りに設定する。
        //注文単位：　@レスポンスに設定する注文単位
        String l_strWLimitEnableStatusDiv =
            WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_orderUnit);

        //getＷ指値用切替前注文単価(EqTypeOrderUnit)
        //引数は以下の通りに設定する。
        //注文単位：　@レスポンスに設定する注文単位
        String l_strWLimitBefSwitchPrice =
            WEB3EquityDataAdapter.getWLimitBefSwitchPrice(l_orderUnit);

        // getＷ指値用切替前執行条件(EqTypeOrderUnit)
        //引数は以下の通りに設定する。
        //注文単位：　@レスポンスに設定する注文単位
        String l_strWLimitBefSwitchExecCondType =
            WEB3EquityDataAdapter.getWLimitBefSwitchExecCondType(l_orderUnit);

        // get元Ｗ指値用注文単価区分(EqTypeOrderUnit)
        //引数は以下の通りに設定する。
        //注文単位：　@レスポンスに設定する注文単位
        String l_strOrgWLimitOrderPriceDiv =
            WEB3EquityDataAdapter.getOrgWLimitOrderPriceDiv(l_orderUnit);

        //get元Ｗ指値用注文単価(EqTypeOrderUnit)
        //引数は以下の通りに設定する。
        //注文単位：　@レスポンスに設定する注文単位
        String l_strOrgWLimitOrderPrice =
            WEB3EquityDataAdapter.getOrgWLimitOrderPrice(l_orderUnit);

        //get元Ｗ指値用執行条件(EqTypeOrderUnit)
        //引数は以下の通りに設定する。
        //注文単位：　@レスポンスに設定する注文単位
        String l_strOrgWLimitExecCondType =
            WEB3EquityDataAdapter.getOrgWLimitExecCondType(l_orderUnit);

        //1.11. get処理状況区分()
        String l_transactionStateType = WEB3EquityDataAdapter.getTransactionStatusType(l_orderUnit);

        //get遅延区分(EqTypeOrderUnit)
        //引数は以下の通りに設定する。
        //注文単位：　@レスポンスに設定する注文単位
        String l_strDelayDiv = WEB3EquityDataAdapter.getDelayDiv(l_orderUnit);

        //is手動発注可能(EqTypeOrderUnit)
        //引数は以下の通りに設定する。
        //注文単位：　@レスポンスに設定する注文単位
        boolean l_blnIsManualOrderPossible =
            l_orderManager.isManualOrderPossible(l_orderUnit);

        //1.12. getトランザクション
        WEB3EquityFinTransactionManager l_finTransactionManager =
            (WEB3EquityFinTransactionManager)l_tradingModule.getFinTransactionManager();
        List l_lisTransactions = l_finTransactionManager.getTransactions(l_orderUnit);

        //1.13. get失効時受渡代金()　@　@※出来分の受渡代金を取得
        double l_dblNetAmountTotal = l_orderManager.getEstimateDeliveryAmountForClose(l_orderUnit,l_lisTransactions);

        //1.14. get委託手数料合計()
        double l_dblCommTotal = l_finTransactionManager.getCommTotal(l_orderUnit);
        
        //1.15. get委託手数料消費税合計()
        double l_dblCommConsumptionTaxTotal =
            l_finTransactionManager.getCommConsumptionTaxTotal(l_orderUnit);
        
        //1.16. getExecutions()
        OrderExecution[] l_orderExecutions = l_orderUnit.getExecutions();

        //1.17. get概算損益
        String l_estimatedProfitLoss = l_finTransactionManager.getEstimatedProfitLoss(l_lisTransactions);

        //1.18. プロパティセット
        //レスポンス.ID
        l_response.id = Long.toString(l_orderUnit.getOrderId());
        //レスポンス.銘柄コード
        WEB3EquityProduct l_product = (WEB3EquityProduct)l_orderUnit.getProduct();
        EqtypeProductRow l_productRow = (EqtypeProductRow)l_product.getDataSourceObject();
        l_response.productCode = l_product.getProductCode();
        //レスポンス.銘柄名
        l_response.productName = l_productRow.getStandardName();
        //レスポンス.市場コード
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        Market l_market = null;
        try
        {
            l_market = l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        l_response.marketCode = l_market.getMarketCode();
        //レスポンス.口座区分
        l_response.taxType = WEB3EquityDataAdapter.getTaxType(l_orderUnitRow.getTaxType());

        //レスポンス.取引区分
        if (OrderTypeEnum.EQUITY_BUY.equals(l_orderUnitRow.getOrderType()))
        {
            if (!WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(l_orderUnitRow.getSonarTradedCode()))
            {
                l_response.tradingType = WEB3TradingTypeDef.BUY_ORDER;
            }
            else
            {
                l_response.tradingType = WEB3TradingTypeDef.PRESENCE_ORDER;
            }
        }
        else
        {
            l_response.tradingType = WEB3TradingTypeDef.SELL_ORDER;
        }
        //レスポンス.値段条件
        l_response.priceCondType = l_orderUnitRow.getPriceConditionType();
        //レスポンス.執行条件
        l_response.execCondType = l_strExecutionConditionType;
        //レスポンス.発注条件区分
        l_response.orderCondType = l_orderUnitRow.getOrderConditionType();
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            //レスポンス.逆指値用発注条件単価
            l_response.stopOrderCondPrice =
                WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
            //レスポンス.逆指値用発注条件演算子
            l_response.stopOrderCondOperator =
                l_orderUnitRow.getOrderCondOperator();
        }
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            //レスポンス.W指値用発注条件単価
            l_response.wlimitOrderCondPrice =
                WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
            //レスポンス.W指値用発注条件演算子
            l_response.wlimitOrderCondOperator =
                l_orderUnitRow.getOrderCondOperator();
            //レスポンス.W指値用注文単価区分
            if ( l_orderUnitRow.getWLimitPrice() == 0)
            {
                l_response.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            }
            else
            {
                l_response.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                //レスポンス.W指値用注文単価
                l_response.wLimitPrice =
                    WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getWLimitPrice());
            }
            //W指値用執行条件：　@注文単位.発注条件＝2（W指値）の場合のみ、
            //拡張株式注文マネージャ.get執行条件（SONAR）(注文単位.(W指値)執行条件)の戻り値をセット。
            l_response.wlimitExecCondType = l_strExecutionConditionTypeSonar;
        }
        //W指値用有効状態区分：　@株式データアダプタ.getW指値用有効状態区分（注文単位）の戻り値をセット。
        l_response.wlimitEnableStatusDiv = l_strWLimitEnableStatusDiv;

        //W指値用切替前注文単価：　@株式データアダプタ.getW指値用切替前注文単価（注文単位）の戻り値をセット。
        l_response.wlimitBefChgLimitPrice = l_strWLimitBefSwitchPrice;

        //W指値用切替前執行条件：　@株式データアダプタ.getW指値用切替前執行条件（注文単位）の戻り値をセット。
        l_response.wlimitBefChgExecCondType = l_strWLimitBefSwitchExecCondType;

        //レスポンス.元発注条件区分
        l_response.orgOrderCondType = l_orderUnitRow.getOrgOrderConditionType();
        
        //レスポンス.元発注条件単価
        if (l_orderUnitRow.getOrgStopOrderPriceIsNull())
        {
            l_response.orgOrderCondPrice = null;
        }
        else
        {
            l_response.orgOrderCondPrice = 
                WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getOrgStopOrderPrice());
        }
        
        //レスポンス.元発注条件演算子
        l_response.orgOrderCondOperator = l_orderUnitRow.getOrgOrderCondOperator();

        //元W指値用注文単価区分：　@株式データアダプタ.get元W指値用注文単価区分（注文単位）の戻り値をセット。
        l_response.orgWlimitOrderPriceDiv = l_strOrgWLimitOrderPriceDiv;

        //元W指値用注文単価：　@
        //　@　@　@レスポンス.元W指値用注文単価区分が"指値"の場合、株式データアダプタ.get元W指値用注文単価（注文単位）の戻り値をセット。
        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_response.orgWlimitOrderPriceDiv))
        {
            l_response.orgWlimitPrice = l_strOrgWLimitOrderPrice;
        }

        //元W指値用執行条件：　@株式データアダプタ.get元W指値用執行条件（注文単位）の戻り値をセット。
        l_response.orgWlimitExecCondType = l_strOrgWLimitExecCondType;

        //レスポンス.注文株数
        l_response.orderQuantity =
            WEB3StringTypeUtility.formatNumber(l_orderUnit.getQuantity());
        //レスポンス.注文単価区分
        if (l_orderUnit.isMarketOrder())
        {
            l_response.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
        }
        else
        {
            l_response.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
        }
        //レスポンス.注文単価
        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_response.orderPriceDiv))
        {
            l_response.limitPrice =
                WEB3StringTypeUtility.formatNumber(l_orderUnit.getLimitPrice());
        }
        //レスポンス.概算受渡代金
        l_response.estimatedPrice =
            WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getEstimatedPrice());
        //レスポンス.注文有効期限
        if (l_orderManager.isCarriedOrderUnit(l_orderUnit))
        {
            l_response.expirationDate = l_orderUnit.getExpirationTimestamp();
        }
        //レスポンス.注文受付日
        l_response.orderDate = l_orderUnitRow.getReceivedDateTime();
        //レスポンス.注文状態区分
        l_response.orderState = l_orderStatus;
        //レスポンス.繰越エラーコード
        if (WEB3OrderStatusDef.NOT_TRANSFERED.equals(l_orderStatus))
        {
            l_response.transferErrCode = l_orderUnitRow.getErrorReasonCode();
        }
        //レスポンス.発注日
        l_response.orderBizDate =
            WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), "yyyyMMdd");
        //レスポンス.約定状態区分
        l_response.execType = l_execType;
        if (!l_orderUnit.isUnexecuted())
        {
            //レスポンス.受渡日
            Date l_deliveryDate = l_orderUnitRow.getDeliveryDate();
            l_response.deliveryDate = WEB3DateUtility.toDay(l_deliveryDate);
            //レスポンス.約定株数
            double l_dblExecQuantity = l_orderUnitRow.getExecutedQuantity();
            l_response.execQuantity =
                WEB3StringTypeUtility.formatNumber(l_dblExecQuantity);
            //レスポンス.約定単価
            double l_dblExecutedAmount = l_orderUnitRow.getExecutedAmount();
            double l_dblExecPrice = Math.round(l_dblExecutedAmount / l_dblExecQuantity);
            l_response.execPrice = WEB3StringTypeUtility.formatNumber(l_dblExecPrice);
            //レスポンス.約定代金
            l_response.execTotalPrice = WEB3StringTypeUtility.formatNumber(l_dblExecutedAmount);
            //レスポンス.受渡代金
            l_response.deliveryPrice = WEB3StringTypeUtility.formatNumber(l_dblNetAmountTotal);
            //レスポンス.概算損益
            l_response.estimatedProfitLoss = l_estimatedProfitLoss;
            //レスポンス.手数料情報.手数料コースコード
            l_response.commissionInfo = new WEB3EquityCommissionInfoUnit();
            l_response.commissionInfo.commissionCourse = l_strCommissionCourseDiv;
            //レスポンス.手数料情報.手数料
            l_response.commissionInfo.commission = WEB3StringTypeUtility.formatNumber(l_dblCommTotal); 
            //レスポンス.手数料情報.消費税
            l_response.commissionInfo.commissionConsumptionTax =
                WEB3StringTypeUtility.formatNumber(l_dblCommConsumptionTaxTotal);
    
            if (l_orderExecutions != null)
            {
                WEB3EquityExecuteUnit[] l_executeUnits =
                    new WEB3EquityExecuteUnit[l_orderExecutions.length];
                for (int i = 0; i < l_orderExecutions.length; i++)
                { 
                    l_executeUnits[i] = new WEB3EquityExecuteUnit();
                    //分割約定一覧.約定日時
                    l_executeUnits[i].executionTimestamp =
                        l_orderExecutions[i].getExecutionTimestamp();
                    //分割約定一覧.約定株数
                    l_executeUnits[i].execQuantity =
                        WEB3StringTypeUtility.formatNumber(l_orderExecutions[i].getExecutionQuantity());
                    //分割約定一覧.約定単価
                    l_executeUnits[i].execPrice =
                        WEB3StringTypeUtility.formatNumber(l_orderExecutions[i].getExecutionPrice());
                }
                l_response.executeUnits = l_executeUnits;
            }
        }
        //レスポンス.部店コード
        WEB3GentradeMainAccount l_account =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        l_response.branchCode = l_account.getBranch().getBranchCode();
        //レスポンス.顧客コード
        l_response.accountCode = l_account.getDisplayAccountCode();
        //レスポンス.顧客名
        l_response.accountName = l_account.getDisplayAccountName();
        //レスポンス.訂正取消区分
        l_response.changeCancelDiv = l_orderUnitRow.getModifyCancelType();
        //レスポンス.注文経路区分
        l_response.orderRootDiv = l_orderUnitRow.getOrderRootDiv();
        //レスポンス.処理状況
        l_response.transactionStateType = l_transactionStateType;
        
        //遅延区分：　@株式データアダプタ.get遅延区分（）の戻り値をセット
        l_response.delayDiv = l_strDelayDiv;

        //手動発注可能フラグ：　@拡張株式注文マネージャ.is手動発注可能（）の戻り値をセット
        l_response.manualFlag = l_blnIsManualOrderPossible;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (search注文約定履歴)<BR>
     * 指定された注文単位IDを持つ注文単位オブジェクトの注文履歴を取得して<BR>
     * 画面表示用に編集し、レスポンスに設定して返す。<BR>
     * 出来るまで注文の場合は、原注文～最新の注文までの注文履歴を対象とする。<BR>
     * <BR>
     * シーケンス図「（注文約定照会）search注文約定履歴」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 現物株式注文約定履歴リクエストオブジェクト<BR>
     * @@return webbroker3.equity.message.WEB3EquityOrderHistoryResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4079FDE100B5
     */
    public WEB3EquityOrderHistoryResponse searchOrderHistory(
        WEB3EquityOrderHistoryRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "searchOrderHistory(WEB3EquityOrderHistoryRequest)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        
        //1.1. validate()
        l_request.validate();
        
        //1.2. getOrderUnits()
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(Long.parseLong(l_request.id));
        if (l_orderUnits.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "指定した注文IDに該当するデータが存在しません。");
        }
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        
        EqtypeOrderUnitRow l_orderUnitRow =
        (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        //1.3. reset注文受付商品()
        if (!WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(l_orderUnitRow.getSonarTradedCode()))
        {
            WEB3GentradeTradingTimeManagement.resetOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);
        }
        else
        {
            WEB3GentradeTradingTimeManagement.resetOrderAcceptProduct(WEB3OrderAccProductDef.SALES_OUTSIDE_MARKET);
        }
        
        //1.4. validate注文受付可能()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.5. is出来るまで注文単位()
        boolean l_isOrderUntilDeadLineOrderUnit =
            l_orderManager.isCarriedOrderUnit(l_orderUnit);
        
        //1.6. is出来るまで注文単位( )==trueの場合のみ実行
        EqTypeOrderUnit[] l_eqtypeOrderUnits = null;
        if (l_isOrderUntilDeadLineOrderUnit)
        {
            //1.6.1. get出来るまで注文単位FromFirstToLast
            l_eqtypeOrderUnits = this.getExecutedOrderUnitFromFirstToLast(l_orderUnit);
        }
        else
        {
            l_eqtypeOrderUnits = new EqTypeOrderUnit[1];
            l_eqtypeOrderUnits[0] = l_orderUnit;
        }
        
        //1.7. 取得した注文単位オブジェクト数分、Loop
        WEB3EquityChangeCancelHistoryGroup[] l_changeCancelHistoryGroups = null;
        if (l_eqtypeOrderUnits != null && l_eqtypeOrderUnits.length > 0)
        {
            List l_lisChangeCancelHistoryGroup = new ArrayList();
            for (int i = 0;i < l_eqtypeOrderUnits.length;i++)
            {
                l_orderUnit = l_eqtypeOrderUnits[i];
                //1.7.1. getOrderActions()
                OrderAction[] l_orderActions = l_orderUnit.getOrderActions();
                
                //1.7.2. 取得した注文履歴オブジェクト数分、Loop
                for (int j = 0;j < l_orderActions.length;j++)
                {
                    EqTypeOrderAction l_orderAction = (EqTypeOrderAction)l_orderActions[j];
                    EqtypeOrderActionRow l_orderActionRow =
                        (EqtypeOrderActionRow)l_orderAction.getDataSourceObject();
                    //1.7.2.1. get執行条件（SONAR）()
                    String l_strExecutionConditionType =
                        l_orderManager.getExecutionConditionTypeSonar(l_orderAction.getExecutionConditionType());
                    //1.7.2.2. get注文内容区分()
                    String l_strOrderType = WEB3EquityDataAdapter.getOrderType(l_orderAction, l_orderUnit);

                    //get執行条件（SONAR）(EqTypeExecutionConditionType)
                    //PR層に返す執行条件を取得する。
                    //引数は以下の通りに設定する。
                    //執行条件：　@注文履歴.(W指値)執行条件
                    String l_strExecutionConditionTypeSonar = null;
                    if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderActionRow.getOrderConditionType()))
                    {
                        l_strExecutionConditionTypeSonar =
                            l_orderManager.getExecutionConditionTypeSonar(
                                l_orderActionRow.getWLimitExecCondType());
                    }
                    //getＷ指値用有効状態区分(EqTypeOrderAction)
                    //引数は以下の通りに設定する。
                    //注文履歴：　@レスポンスに設定する注文履歴
                    String l_strWLimitEnableStatusDiv =
                        WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_orderAction);

                    //getＷ指値用切替前注文単価(EqTypeOrderAction)
                    //引数は以下の通りに設定する。
                    //注文履歴：　@レスポンスに設定する注文履歴
                    String l_strWLimitBefSwitchPrice =
                        WEB3EquityDataAdapter.getWLimitBefSwitchPrice(l_orderAction);

                    //getＷ指値用切替前執行条件(EqTypeOrderAction)
                    //引数は以下の通りに設定する。
                    //注文履歴：　@レスポンスに設定する注文履歴
                    String l_strWLimitBefSwitchExecCondType =
                        WEB3EquityDataAdapter.getWLimitBefSwitchExecCondType(l_orderAction);

                    //get元Ｗ指値用注文単価区分(EqTypeOrderAction)
                    //引数は以下の通りに設定する。
                    //注文履歴：　@レスポンスに設定する注文履歴
                    String l_strOrgWLimitOrderPriceDiv =
                        WEB3EquityDataAdapter.getOrgWLimitOrderPriceDiv(l_orderAction);

                    //get元Ｗ指値用注文単価(EqTypeOrderAction)
                    //引数は以下の通りに設定する。
                    //注文履歴：　@レスポンスに設定する注文履歴
                    String l_strOrgWLimitOrderPrice =
                        WEB3EquityDataAdapter.getOrgWLimitOrderPrice(l_orderAction);

                    // get元Ｗ指値用執行条件(EqTypeOrderAction)
                    //引数は以下の通りに設定する。
                    //注文履歴：　@レスポンスに設定する注文履歴
                    String l_strOrgWLimitExecCondType =
                        WEB3EquityDataAdapter.getOrgWLimitExecCondType(l_orderAction);

                    //1.7.2.3. get受付結果区分()
                    String l_strAcceptedResultDiv = WEB3EquityDataAdapter.getAcceptedResultDiv(l_orderAction);
                    
                    WEB3EquityChangeCancelHistoryGroup l_changeCancelHistoryGroup =
                        new WEB3EquityChangeCancelHistoryGroup();
                    //注文NO
                    l_changeCancelHistoryGroup.orderActionId =
                        Long.toString(l_orderAction.getOrderActionId());
                    //受付日時
                    l_changeCancelHistoryGroup.orderDate =
                        l_orderActionRow.getCreatedTimestamp();
                    //注文内容区分
                    l_changeCancelHistoryGroup.orderType = l_strOrderType;
                    
                    if (l_orderAction.isUnexecuted())
                    {
                        //値段条件
                        l_changeCancelHistoryGroup.priceCondType =
                            l_orderActionRow.getPriceConditionType();
                        //執行条件
                        l_changeCancelHistoryGroup.execCondType =
                            l_strExecutionConditionType;
                        //発注条件区分
                        l_changeCancelHistoryGroup.orderCondType =
                            l_orderActionRow.getOrderConditionType();
                        //注文株数
                        l_changeCancelHistoryGroup.orderQuantity =
                            WEB3StringTypeUtility.formatNumber(l_orderAction.getQuantity());
                        //注文単価区分
                        if (l_orderAction.isMarketOrder())
                        {
                            l_changeCancelHistoryGroup.orderPriceDiv =
                                WEB3OrderPriceDivDef.MARKET_PRICE;
                        }
                        else
                        {
                            l_changeCancelHistoryGroup.orderPriceDiv =
                                WEB3OrderPriceDivDef.LIMIT_PRICE;
                        }
                        //注文単価
                        if (!l_orderAction.isMarketOrder())
                        {
                            l_changeCancelHistoryGroup.limitPrice =
                                WEB3StringTypeUtility.formatNumber(l_orderAction.getPrice());
                        }
                        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderActionRow.getOrderConditionType()))
                        {
                            //逆指値用発注条件単価
                            l_changeCancelHistoryGroup.stopOrderCondPrice =
                                WEB3StringTypeUtility.formatNumber(l_orderActionRow.getStopOrderPrice());
                            //逆指値用発注条件演算子
                            l_changeCancelHistoryGroup.stopOrderCondOperator =
                                l_orderActionRow.getOrderCondOperator();
                        }
                        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderActionRow.getOrderConditionType()))
                        {
                            //W指値用発注条件単価
                            l_changeCancelHistoryGroup.wlimitOrderCondPrice =
                                WEB3StringTypeUtility.formatNumber(l_orderActionRow.getStopOrderPrice());
                            //W指値用発注条件演算子
                            l_changeCancelHistoryGroup.wlimitOrderCondOperator =
                                l_orderActionRow.getOrderCondOperator();
                            //W指値用注文単価区分
                            if ( l_orderActionRow.getWLimitPrice() == 0)
                            {
                                l_changeCancelHistoryGroup.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                            }
                            else
                            {
                                l_changeCancelHistoryGroup.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                                //W指値用注文単価
                                l_changeCancelHistoryGroup.wLimitPrice =
                                    WEB3StringTypeUtility.formatNumber(l_orderActionRow.getWLimitPrice());
                            }

                            //W指値用執行条件：　@(*1)注文履歴.発注条件＝2（W指値）の場合のみ、
                            //拡張株式注文マネージャ.get執行条件（SONAR）(注文履歴.(W指値)執行条件)の戻り値をセット。
                            l_changeCancelHistoryGroup.wlimitExecCondType = l_strExecutionConditionTypeSonar;
                        }
                        //W指値用有効状態区分：　@(*1)株式データアダプタ.getW指値用有効状態区分（注文履歴）の戻り値をセット。
                        l_changeCancelHistoryGroup.wlimitEnableStatusDiv = l_strWLimitEnableStatusDiv;

                        //W指値用切替前注文単価：　@(*1)株式データアダプタ.getW指値用切替前注文単価（注文履歴）の戻り値をセット。
                        l_changeCancelHistoryGroup.wlimitBefChgLimitPrice = l_strWLimitBefSwitchPrice;

                        //W指値用切替前執行条件：　@(*1)株式データアダプタ.getW指値用切替前執行条件（注文履歴）の戻り値をセット。
                        l_changeCancelHistoryGroup.wlimitBefChgExecCondType = l_strWLimitBefSwitchExecCondType;

                        //元発注条件区分：　@(*1)注文履歴.元発注条件
                        l_changeCancelHistoryGroup.orgOrderCondType =
                            l_orderActionRow.getOrgOrderConditionType();

                        //元発注条件単価：　@(*1)注文履歴.元逆指値基準値
                        if (l_orderActionRow.getOrgStopOrderPriceIsNull())
                        {
                            l_changeCancelHistoryGroup.orgOrderCondPrice = null;
                        }
                        else
                        {
                            l_changeCancelHistoryGroup.orgOrderCondPrice =
                                WEB3StringTypeUtility.formatNumber(l_orderActionRow.getOrgStopOrderPrice());
                        }

                        //元発注条件演算子：　@(*1)注文履歴.元発注条件演算子
                        l_changeCancelHistoryGroup.orgOrderCondOperator =
                            l_orderActionRow.getOrgOrderCondOperator();

                        //元W指値用注文単価区分：　@(*1)株式データアダプタ.get元W指値用注文単価区分（注文履歴）の戻り値をセット。
                        l_changeCancelHistoryGroup.orgWlimitOrderPriceDiv = l_strOrgWLimitOrderPriceDiv;

                        //元W指値用注文単価：　@(*1)
                        //レスポンス.元W指値用注文単価区分が"指値"の場合、株式データアダプタ.get元W指値用注文単価（注文履歴）の戻り値をセット。
                        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrgWLimitOrderPriceDiv))
                        {
                            l_changeCancelHistoryGroup.orgWlimitPrice = l_strOrgWLimitOrderPrice;
                        }

                        //元W指値用執行条件：　@(*1)株式データアダプタ.get元W指値用執行条件（注文履歴）の戻り値をセット。
                        l_changeCancelHistoryGroup.orgWlimitExecCondType = l_strOrgWLimitExecCondType;

                        //注文有効期限

                        if (l_orderManager.isCarriedOrderUnit(l_orderAction.getOrderUnitId()))
                        {
                            l_changeCancelHistoryGroup.expirationDate = l_orderActionRow.getExpirationDate();
                        }
                    }
                    else
                    {
                        //約定株数
                        l_changeCancelHistoryGroup.execQuantity =
                            WEB3StringTypeUtility.formatNumber(l_orderAction.getExecutionQuantity());
                        //約定単価
                        l_changeCancelHistoryGroup.execPrice =
                            WEB3StringTypeUtility.formatNumber(l_orderAction.getExecutionPrice());
                    }
                    
                    //受付結果区分
                    l_changeCancelHistoryGroup.acceptedResultDiv = l_strAcceptedResultDiv;
                    
                    l_lisChangeCancelHistoryGroup.add(l_changeCancelHistoryGroup);
                }
            }
            l_changeCancelHistoryGroups =
                new WEB3EquityChangeCancelHistoryGroup[l_lisChangeCancelHistoryGroup.size()];
            l_lisChangeCancelHistoryGroup.toArray(l_changeCancelHistoryGroups);
        }
        
        //1.8. createResponse()
        WEB3EquityOrderHistoryResponse l_response =
            (WEB3EquityOrderHistoryResponse)l_request.createResponse();
        //レスポンス.注文履歴一覧
        l_response.changeCancelHistoryGroups = l_changeCancelHistoryGroups;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (getAll注文単位一覧)<BR>
     * 表示対象の発注日を持つ注文単位オブジェクトを全てを取得する。<BR>
     * <BR>
     * １）　@this.create検索条件文字列(null、null、null、引数のis現物取得、引数のis立会外分売取得、null)<BR>
     * 　@　@　@により、検索条件文字列を作成する。<BR>
     * <BR>
     * ２）　@this.create検索条件データコンテナ(null、null、null、null)<BR>
     * 　@　@　@により、検索条件データコンテナを作成する。<BR>
     * <BR>
     * ３）　@拡張株式注文マネージャ.get注文単位一覧( )により、<BR>
     * 　@　@　@検索条件に合致する注文単位オブジェクトのListを取得する。<BR>
     * 　@　@　@※取得の際、銘柄ID昇順指定を行う。<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------<BR>
     * 　@　@　@＜get注文単位一覧( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@補助口座：　@引数の補助口座<BR>
     * 　@　@　@銘柄タイプ：　@"株式"<BR>
     * 　@　@　@検索条件文字列：　@create検索条件文字列( )の戻り値<BR>
     * 　@　@　@検索条件データコンテナ：　@create検索条件データコンテナ( )の戻り値<BR>
     * 　@　@　@ソート条件：　@銘柄ID 昇順<BR>
     * 　@　@　@----------------------------------------------------<BR>
     * <BR>
     * ４）　@取得したListを返す。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_isMarketTrading - (is現物取得)<BR>
     * @@param l_isSalesOutsideMarket - (is立会外分売取得)<BR>
     * @@return List
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 406D559000BB
     */
    protected List getAllOrderUnits(
        WEB3GentradeSubAccount l_subAccount,
        boolean l_isMarketTrading,
        boolean l_isSalesOutsideMarket)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAllOrderUnits(WEB3GentradeSubAccount, boolean, boolean)";
        log.entering(STR_METHOD_NAME);
        
        List l_lisReturn = null;
        
        // １）　@this.create検索条件文字列(null、null、null、引数のis現物取得、引数のis立会外分売取得、null)
        // 　@　@　@により、検索条件文字列を作成する。
        String l_strCond = this.createQueryCond(null, null, null, l_isMarketTrading, l_isSalesOutsideMarket, null);
        
        // ２）　@this.create検索条件データコンテナ(null、null、null、null)
        // 　@　@　@により、検索条件データコンテナを作成する。
        String[] l_strQueryCondDataContainer = this.createQueryCondDataContainer(null, null, null, null);
        
        // ３）　@拡張株式注文マネージャ.get注文単位一覧( )により、
        // 　@　@　@検索条件に合致する注文単位オブジェクトのListを取得する。
        // 　@　@　@※取得の際、銘柄ID昇順指定を行う。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        String l_strSort = "product_id ASC";
        l_lisReturn =
            l_orderManager.getOrderUnits(
                l_subAccount,
                ProductTypeEnum.EQUITY,
                l_strCond,
                l_strQueryCondDataContainer,
                l_strSort);
        
        log.exiting(STR_METHOD_NAME);
        return l_lisReturn;
    }

    /**
     * (create検索条件文字列)<BR>
     * 検索条件（where以下指定の文字列）を作成する。<BR>
     * <BR>
     * (1)戻り値となる文字列のインスタンスを生成<BR>
     * <BR>
     * (2)発注日指定を文字列インスタンスに設定<BR>
     * 　@(2-1)パラメータ.発注日≠NULL（発注日指定）の場合<BR>
     * <BR>
     * 　@　@　@"biz_date = ?"<BR>
     * <BR>
     * 　@(2-2)パラメータ.発注日＝NULL（発注日指定なし）の場合<BR>
     * <BR>
     * 　@　@　@"biz_date >= ?"<BR>
     * <BR>
     * (3)注文種別指定を追加<BR>
     * <BR>
     * 　@　@　@" and order_type in (*注１, *注２)"<BR>
     * <BR>
     * 　@-----------------------------------------------------------------<BR>
     * 　@*注１：　@OrderTypeEnum.EQUITY_BUY（現物買注文）　@を文字列に変換した値<BR>
     * 　@*注２：　@OrderTypeEnum.EQUITY_SELL（現物売注文）　@を文字列に変換した値<BR>
     * 　@-----------------------------------------------------------------<BR>
     * <BR>
     * (4)パラメータ.銘柄コード≠NULL（銘柄コード指定）の場合、銘柄ID指定を追加<BR>
     * 　@　@（銘柄コードに対応する銘柄IDで検索を行う)<BR>
     * <BR>
     * 　@　@　@" and product_id = ?"<BR>
     * <BR>
     * (5)市場指定条件を追加する<BR>
     * 　@(5-1)パラメータ.市場コード≠NULL（市場コード指定）の場合、<BR>
     * 　@　@　@　@市場ID指定を追加する。<BR>
     * 　@　@（市場コードに対応する市場IDで検索を行う)<BR>
     * <BR>
     * 　@　@　@" and market_id = ?"<BR>
     * <BR>
     * 　@(5-2)パラメータ.市場コード＝NULL（市場コード指定なし）の場合、<BR>
     * 　@　@　@PTS注文を検索対象外とする。<BR>
     * <BR>
     * 　@　@　@(5-2-1)　@（部店市場別・PTS）取扱条件.get取扱可能市場()をコールする。<BR>
     * <BR>
     * 　@　@　@　@[引数]<BR>
     * 　@　@　@　@部店：　@補助口座.get取引店()の戻り値 （*1）<BR>
     * 　@　@　@　@銘柄タイプ：　@"株式"<BR>
     * <BR>
     * 　@　@　@　@　@　@（*1）補助口座はthis.get補助口座()で取得<BR>
     * <BR>
     * 　@　@　@(5-2-2）　@(5-2-1)で取得した市場コードの配列の要素数 > 0 の場合、<BR>
     * 　@　@　@　@　@以下の文字列を１）の文字列に追加する。<BR>
     * <BR>
     * 　@　@　@　@　@"and market_id not in (?, ?, ・・・・・・) "（*2）<BR>
     * <BR>
     * 　@　@　@　@　@　@（*2）”?”の数は、取得した配列の要素数分設定する。<BR>
     * <BR>
     * (6)パラメータ.is現物取得==falseの場合、以下の指定を追加<BR>
     * <BR>
     * 　@　@　@" and sonar_traded_code = "16：立会外分売""<BR>
     * <BR>
     * (7)パラメータ.is立会外分売取得==falseの場合、以下の指定を追加<BR>
     * <BR>
     * 　@　@　@" and sonar_traded_code != "16：立会外分売""<BR>
     * <BR>
     * (8)パラメータ.発注条件区分≠NULL（発注条件区分指定）の場合、<BR>
     * 発注条件区分指定を追加<BR>
     *  （株式注文単位テーブル.元発注条件に値が設定されている場合は、<BR>
     * 　@元発注条件をもとに検索する。<BR>
     * 　@元発注条件に値が設定されていない場合は、<BR>
     * 　@株式注文単位テーブル.発注条件をもとに検索する。) <BR>
     * <BR>
     * 　@　@" and nvl(org_order_condition_type,order_condition_type) = ?"<BR>
     * (9)文字列インスタンスを返却<BR>
     * <BR>
     * ※(6)または(7)は、片方が実行される／どちらも実行されない　@のいずれか。<BR>
     * <BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * @@param l_datOrderBizDate - (発注日)<BR>
     * @@param l_isMarketTrading - (is現物取得)<BR>
     * @@param l_isSalesOutsideMarket - (is立会外分売取得)<BR>
     * @@param l_strOrderConditionDiv - (発注条件区分)<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 406403B2027F
     */
    protected String createQueryCond(
        String l_strProductCode,
        String l_strMarketCode,
        Date l_datOrderBizDate,
        boolean l_isMarketTrading,
        boolean l_isSalesOutsideMarket,
        String l_strOrderConditionDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createQueryCond(String, String, Date, boolean, boolean, String)";
        log.entering(STR_METHOD_NAME);
        
        // (1)戻り値となる文字列のインスタンスを生成
        StringBuffer l_sbSearchCond = new StringBuffer();
        
        // (2)発注日指定を文字列インスタンスに設定
        // 　@(2-1)パラメータ.発注日≠NULL（発注日指定）の場合
        if (l_datOrderBizDate != null)
        {
            l_sbSearchCond.append("biz_date = ?");
        }
        //　@(2-2)パラメータ.発注日＝NULL（発注日指定なし）の場合
        else
        {
            l_sbSearchCond.append("biz_date >= ?");
        }
        
        // (3)注文種別指定を追加
        l_sbSearchCond.append(" and order_type in (1, 2)");

        // (4)パラメータ.銘柄コード≠NULL（銘柄コード指定）の場合、銘柄ID指定を追加
        if (l_strProductCode != null)
        {
            l_sbSearchCond.append(" and product_id = ?");
        }

        //(5)市場指定条件を追加する
        //(5-1)パラメータ.市場コード≠NULL（市場コード指定）の場合、市場ID指定を追加する。
        if (l_strMarketCode != null)
        {
            l_sbSearchCond.append(" and market_id = ?");
        }
        else
        {
            //(5-2)パラメータ.市場コード＝NULL（市場コード指定なし）の場合、
            //PTS注文を検索対象外とする。
            //(5-2-1)　@（部店市場別・PTS）取扱条件.get取扱可能市場()をコールする。
            //部店：　@補助口座.get取引店()の戻り値 （*1）
            //銘柄タイプ：　@"株式"
            String[] l_handlingPossibleMarkets =
                WEB3GentradeBranchMarketPTSDealtCond.getHandlingPossibleMarket(
                    this.getSubAccount().getWeb3GenBranch(),
                    ProductTypeEnum.EQUITY);

            //(5-2-2）　@(5-2-1)で取得した市場コードの配列の要素数 > 0 の場合、
            //以下の文字列を１）の文字列に追加する。
            //"and market_id not in (?, ?, ・・・・・・) "
            if (l_handlingPossibleMarkets.length > 0)
            {
                int l_inthandlingPossibleMarketsLength = l_handlingPossibleMarkets.length;

                l_sbSearchCond.append(" and market_id not in (?");
                for (int i = 1; i < l_inthandlingPossibleMarketsLength; i++)
                {
                    l_sbSearchCond.append(", ?");
                }
                l_sbSearchCond.append(")");
            }
        }

        // (6)パラメータ.is現物取得==falseの場合、以下の指定を追加
        if (!l_isMarketTrading)
        {

            l_sbSearchCond.append(" and sonar_traded_code = '16'");
        }
        
        // (7)パラメータ.is立会外分売取得==falseの場合、以下の指定を追加
        if (!l_isSalesOutsideMarket)
        {

            l_sbSearchCond.append(" and sonar_traded_code != '16'");
        }

        // (8)パラメータ.発注条件区分≠NULL（発注条件区分指定）の場合
        if (l_strOrderConditionDiv != null)
        {
            l_sbSearchCond.append(" and nvl(org_order_condition_type,order_condition_type) = ?");
        }

        log.exiting(STR_METHOD_NAME);
        return l_sbSearchCond.toString();
    }

    /**
     * (create検索条件データコンテナ)<BR>
     * 検索条件（where以下指定の文字列）のパラメータの文字列配列を作成する。<BR>
     * <BR>
     * (1)文字列配列を生成<BR>
     * <BR>
     * (2)発注日指定値を文字列配列にセット<BR>
     * 　@(2-1)パラメータ.発注日≠NULL（発注日指定）の場合<BR>
     * 　@　@　@発注日指定値 ＝ パラメータ.発注日<BR>
     * <BR>
     * 　@(2-2)パラメータ.発注日＝NULL（発注日指定なし）の場合<BR>
     * 　@　@　@発注日指定値 ＝ 業務日付(*1)<BR>
     * <BR>
     * (3)パラメータ.銘柄コード≠NULL（銘柄コード指定）の場合、<BR>
     * 　@　@銘柄IDを文字列配列にセット（銘柄コードに対応する銘柄IDで検索を行う)<BR>
     * <BR>
     * 　@　@　@銘柄ID ＝ 拡張プロダクトマネージャ.get銘柄(証券会社オブジェクト(*2), パラメータ.銘柄コード).銘柄ID<BR>
     * <BR>
     * (4)市場指定条件を追加する。<BR>
     * 　@(4-1)パラメータ.市場コード≠NULL（市場コード指定）の場合、<BR>
     * 　@　@　@市場IDを文字列配列にセット<BR>
     * 　@　@（市場コードに対応する市場IDで検索を行う)<BR>
     * <BR>
     * 　@　@(4-1-1)金融オブジェクトマネージャ.getMarket().getMarketId()の戻り値<BR>
     * 　@　@　@をリストに追加する。<BR>
     * <BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@　@証券会社： 証券会社オブジェクト(*2)<BR>
     * 　@　@　@　@市場コード： パラメータ.市場コード<BR>
     * <BR>
     * <BR>
     * 　@(4-2)パラメータ.市場コード＝NULL（市場コード指定なし）の場合、<BR>
     * 　@　@　@　@PTS注文を検索対象外とする条件を追加する。<BR>
     * <BR>
     * 　@　@　@(4-2-1)　@（部店市場別・PTS）取扱条件.get取扱可能市場()をコールする。<BR>
     * <BR>
     * 　@　@　@　@[引数]<BR>
     * 　@　@　@　@部店：　@補助口座.get取引店()の戻り値 (*3)<BR>
     * 　@　@　@　@銘柄タイプ：　@"株式"<BR>
     * <BR>
     * 　@　@　@(4-2-2)　@　@(4-2-1)　@で取得した市場コード配列の要素数 > 0 の場合、<BR>
     * 　@　@　@　@配列要素数分、以下を繰り返す。<BR>
     * <BR>
     * 　@　@　@　@拡張金融オブジェクトマネージャ.getMarket().getMarketId()の戻り値<BR>
     * 　@　@　@　@をリストに追加する。<BR>
     * <BR>
     * 　@　@　@　@[引数]<BR>
     * 　@　@　@　@証券会社：　@証券会社オブジェクト(*2)<BR>
     * 　@　@　@　@市場コード：　@(4-2-1)で取得した市場コード配列の要素<BR>
     * <BR>
     * (5)パラメータ.発注条件区分≠NULL（発注条件区分指定）の場合、<BR>
     * パラメータ.発注条件区分を文字列配列にセット<BR>
     * <BR>
     * 　@　@　@発注条件区分 ＝ パラメータ.発注条件区分<BR>
     * <BR>
     * (6)(2)、(3)、(4)、(5)にてパラメータをセットした文字列配列を返却<BR>
     * <BR>
     * (*1)GtlUtils.getTradingSystem( ).getBizDate( )<BR>
     * (*2)証券会社オブジェクトは、補助口座.getInstitution( )で取得し設定<BR>
     * (*3)補助口座はthis.get補助口座()で取得<BR>
     * <BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * @@param l_datOrderBizDate - (発注日)<BR>
     * @@param l_strOrderConditionDiv - (発注条件区分)<BR>
     * @@return String[ ]
     * @@throws WEB3BaseException
     * @@roseuid 40640D2A008C
     */
    protected String[] createQueryCondDataContainer(
        String l_strProductCode,
        String l_strMarketCode,
        Date l_datOrderBizDate,
        String l_strOrderConditionDiv)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "createQueryCondDataContainer(String, String, Date, String)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        
        // (1)戻り値となる文字列のインスタンスを生成
        List l_lisParams = new ArrayList();
        
        // (2)発注日指定値を文字列配列にセット
        // 　@(2-1)パラメータ.発注日≠NULL（発注日指定）の場合
        // 　@　@　@発注日指定値 ＝ パラメータ.発注日
        String l_strBizDate;
        if (l_datOrderBizDate != null)
        {
            l_strBizDate =
                GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(l_datOrderBizDate);
        }
        // 　@(2-2)パラメータ.発注日＝NULL（発注日指定なし）の場合
        // 　@　@　@発注日指定値 ＝ 業務日付(*1)
        else
        {
            WEB3GentradeBizDate l_dateCalc =
                new WEB3GentradeBizDate(GtlUtils.getSystemTimestamp());
            Timestamp l_tsbizDate = new Timestamp(GtlUtils.getTradingSystem().getBizDate().getTime());
            l_strBizDate = GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(l_tsbizDate);
        }
        l_lisParams.add(l_strBizDate);
        
        try
        {
            // (3)パラメータ.銘柄コード≠NULL（銘柄コード指定）の場合
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)this.getSubAccount();
            if (l_strProductCode != null)
            {
                WEB3EquityProduct l_product =
                    (WEB3EquityProduct)l_productManager.getProduct(
                            l_subAccount.getInstitution(),
                            l_strProductCode);
                l_lisParams.add(Long.toString(l_product.getProductId()));
            }

            //(4)市場指定条件を追加する。
            //4-1)パラメータ.市場コード≠NULL（市場コード指定）の場合、市場IDを文字列配列にセット
            if (l_strMarketCode != null)
            {
                Market l_market =
                    (WEB3GentradeMarket)l_finObjectManager.getMarket(
                        l_subAccount.getInstitution(),
                        l_strMarketCode);
                l_lisParams.add(Long.toString(l_market.getMarketId()));
            }
            else
            {
                //(4-2)パラメータ.市場コード＝NULL（市場コード指定なし）の場合、
                //PTS注文を検索対象外とする条件を追加する。
                //(4-2-1)　@（部店市場別・PTS）取扱条件.get取扱可能市場()をコールする。
                //部店：　@補助口座.get取引店()の戻り値
                //銘柄タイプ：　@"株式"
                String[] l_handlingPossibleMarkets =
                    WEB3GentradeBranchMarketPTSDealtCond.getHandlingPossibleMarket(
                        this.getSubAccount().getWeb3GenBranch(),
                        ProductTypeEnum.EQUITY);

                //(4-2-2)　@　@(4-2-1)　@で取得した市場コード配列の要素数 > 0 の場合、
                //配列要素数分、以下を繰り返す。
                //拡張金融オブジェクトマネージャ.getMarket().getMarketId()の戻り値をリストに追加する。
                //証券会社：　@証券会社オブジェクト
                //市場コード：　@(4-2-1)で取得した市場コード配列の要素
                if (l_handlingPossibleMarkets.length > 0)
                {
                    int l_inthandlingPossibleMarketsLength = l_handlingPossibleMarkets.length;

                    for (int i = 0; i < l_inthandlingPossibleMarketsLength; i++)
                    {
                        Market l_market =
                            (WEB3GentradeMarket)l_finObjectManager.getMarket(
                                l_subAccount.getInstitution(),
                                l_handlingPossibleMarkets[i]);

                        l_lisParams.add(Long.toString(l_market.getMarketId()));
                    }
                }
            }
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

        // (5)パラメータ.発注条件区分≠NULL（発注条件区分指定）の場合
        if (l_strOrderConditionDiv != null)
        {
            // パラメータ.発注条件区分を文字列配列にセット 
            l_lisParams.add(l_strOrderConditionDiv);
        }
        
        // (6)(2)、(3)、(4)、(5)にてパラメータをセットした文字列配列を返却
        String[] l_strParams = new String[l_lisParams.size()];
        l_lisParams.toArray(l_strParams);
        log.exiting(STR_METHOD_NAME);
        return l_strParams;
    }

    /**
     * (createソート条件)<BR>
     * <BR>
     * １）　@引数のソートキー.キー項目の数分、対応するテーブルの列物理名を昇順／降順指定を付加しセットする。<BR>
     * <BR>
     * 　@　@・キー項目とテーブルの列名称との対応は以下の通り。<BR>
     * 　@　@　@※キー項目文字列（シンボル名）は、メッセージ定義書を参照。<BR>
     * 　@　@　@※テーブルの列物理名は、テーブルレイアウトを参照。<BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@・（銘柄）コード　@　@　@　@：注文単位テーブル．銘柄ID<BR>
     * 　@　@　@　@　@　@　@　@・口座　@　@　@　@　@　@　@　@　@：注文単位テーブル．税区分<BR>
     * 　@　@　@　@　@　@　@　@・市場　@　@　@　@　@　@　@　@　@：注文単位テーブル．市場ID<BR>
     * 　@　@　@　@　@　@　@　@・取引区分　@　@　@　@　@　@：注文単位テーブル．注文種別、取引コード（SONAR）(*1)<BR>
     * 　@　@　@　@　@　@　@　@・値段条件　@　@　@　@　@　@：注文単位テーブル．値段条件<BR>
     * 　@　@　@　@　@　@　@　@・執行条件　@　@　@　@　@　@：注文単位テーブル．執行条件<BR>
     * 　@　@　@　@　@　@　@　@・発注条件　@　@　@　@　@　@：注文単位テーブル．発注条件<BR>
     * 　@　@　@　@　@　@　@　@・注文時間　@　@　@　@　@　@：注文単位テーブル．作成日時<BR>
     * 　@　@　@　@　@　@　@　@・発注日   　@　@　@　@　@　@：注文単位テーブル．発注日<BR>
     * 　@　@　@　@　@　@　@　@・注文期限　@　@　@　@　@　@：注文単位テーブル．注文失効日付<BR>
     * <BR>
     * 　@　@・昇順／降順指定は、ソートキー.昇順／降順 指定に従い設定する。<BR>
     * <BR>
     * 　@　@(*1)昇順指定の場合：　@以下の①@→③の順となる。<BR>
     * 　@　@　@　@　@①@注文種別=="現物買注文"＋取引コード（SONAR）=="普通株式"<BR>
     * 　@　@　@　@　@②注文種別=="現物買注文"＋取引コード（SONAR）=="立会外分売"<BR>
     * 　@　@　@　@　@③注文種別=="現物売注文"（売注文は、取引コード（SONAR）=="普通株式"固定）<BR>
     * 　@　@　@　@降順指定の場合：　@上記の③→①@の順となる。<BR>
     * <BR>
     * ２）　@ソート条件末尾に、注文単位テーブル.更新日付を昇順指定で付加する。<BR>
     * <BR>
     * ３）　@作成したソート条件文字列を返す。<BR>
     * <BR>
     * @@param l_sortKey - (ソートキー)<BR>
     * リクエスト.ソートキー
     * @@return java.lang.String
     * @@roseuid 40752DE40150
     */
    protected String createSortCond(WEB3EquitySortKey[] l_sortKey)
    {
        final String STR_METHOD_NAME = "createSortCond(WEB3EquitySortKey[])";
        log.entering(STR_METHOD_NAME);
        
        StringBuffer l_sbReturn = new StringBuffer();
        // １）　@引数のソートキー.キー項目の数分、対応するテーブルの列物理名を昇順／降順指定を付加しセットする。
        for (int i = 0; i < l_sortKey.length; i++)
        {
            if(WEB3EquityKeyItemDef.PRODUCTCODE.equals(l_sortKey[i].keyItem))
            {
                //（銘柄）コード ：注文単位テーブル．銘柄ID
                l_sbReturn.append("eqtype_order_unit.product_id");
            }
            else if(WEB3EquityKeyItemDef.ACCOUNTTYPE.equals(l_sortKey[i].keyItem))
            {
                //口座 ：注文単位テーブル．税区分
                l_sbReturn.append("eqtype_order_unit.tax_type");
            }
            else if(WEB3EquityKeyItemDef.TRADEMARKET.equals(l_sortKey[i].keyItem))
            {
                //市場 ：注文単位テーブル．市場ID
                l_sbReturn.append("eqtype_order_unit.market_id");
            }
            else if(WEB3EquityKeyItemDef.TRADETYPE.equals(l_sortKey[i].keyItem))
            {
                //取引区分　@：注文単位テーブル．注文種別、取引コード（SONAR）
                if (WEB3AscDescDef.ASC.equals(l_sortKey[i].ascDesc))
                {
                    l_sbReturn.append("eqtype_order_unit.order_type ASC , eqtype_order_unit.sonar_traded_code　@ASC , ");
                }
                else
                {
                    l_sbReturn.append("eqtype_order_unit.order_type DESC , eqtype_order_unit.sonar_traded_code　@DESC , ");
                }
                continue;
            }
            else if(WEB3EquityKeyItemDef.PRICE_COND.equals(l_sortKey[i].keyItem))
            {
                //値段条件　@：注文単位テーブル．値段条件
                l_sbReturn.append("eqtype_order_unit.price_condition_type");
            }
            else if(WEB3EquityKeyItemDef.EXECUTE_COND.equals(l_sortKey[i].keyItem))
            {
                //執行条件　@：注文単位テーブル．執行条件
                l_sbReturn.append("eqtype_order_unit.execution_condition_type");
            }
            else if(WEB3EquityKeyItemDef.SEND_COND.equals(l_sortKey[i].keyItem))
            {
                //発注条件　@：注文単位テーブル．発注条件
                l_sbReturn.append("eqtype_order_unit.order_condition_type");
            }
            else if(WEB3EquityKeyItemDef.ORDER_TIME.equals(l_sortKey[i].keyItem))
            {
                //注文時間　@：注文単位テーブル．作成日時
                l_sbReturn.append("eqtype_order_unit.created_timestamp");
            }
            else if(WEB3EquityKeyItemDef.SEND_DATE.equals(l_sortKey[i].keyItem))
            {
                //発注日　@：注文単位テーブル．発注日
                l_sbReturn.append("eqtype_order_unit.biz_date");
            }
            else if(WEB3EquityKeyItemDef.ORDER_TIMELIMIT.equals(l_sortKey[i].keyItem))
            {
                //注文期限　@：注文単位テーブル．注文失効日付
                l_sbReturn.append("eqtype_order_unit.expiration_date");
            }
            else
            {
                continue;
            }
            if (WEB3AscDescDef.ASC.equals(l_sortKey[i].ascDesc))
            {
                l_sbReturn.append("　@ASC , ");
            }
            else
            {
                l_sbReturn.append("　@DESC , ");
            }
        }
        
        // ２）　@ソート条件末尾に、注文単位テーブル.更新日付を昇順指定で付加する。
        l_sbReturn.append("eqtype_order_unit.last_updated_timestamp ASC");
        
        // ３）　@作成したソート条件文字列を返す。
        log.debug("ソート条件 = " + l_sbReturn.toString());
        log.exiting(STR_METHOD_NAME);
        return l_sbReturn.toString();
    }

    /**
     * (is指定約定状態)<BR>
     * 指定された約定状態に合致しているかどうかを判定し、<BR>
     * 合致している場合はtrueを、合致していない場合はfalseを、それぞれ返す。<BR>
     * <BR>
     * this.get約定状態区分(パラメータ.注文単位)をコールする。<BR>
     * 　@取得した約定状態区分 == パラメータ.約定状態区分の場合は、trueを返す。<BR>
     * 　@以外、falseを返す。<BR>
     * <BR>
     * @@param l_strExecType (約定状態区分)<BR>
     * リクエスト.約定状態区分。<BR>
     * null:指定なし　@0:未約定　@1:一部成立　@2:全部成立<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。<BR>
     * @@return boolean
     * @@roseuid 407D3956016D
     */
    protected boolean isExecType(String l_strExecType, EqTypeOrderUnit l_orderUnit)
    {
        final String STR_METHOD_NAME = "isExecType(String, EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        boolean l_bReturn;
        if (l_strExecType == null)
        {
            //引数.約定状態区分＝nullの場合
            log.debug("引数.約定状態区分＝nullの場合");
            l_bReturn = true;
        }
        else
        {
            //引数.約定状態区分≠nullの場合
            log.debug("引数.約定状態区分≠nullの場合");
            String l_execType = WEB3EquityDataAdapter.getExecType(l_orderUnit);
            if (l_execType.equals(l_strExecType))
            {
                //取得した約定状態区分＝引数.約定状態区分の場合
                log.debug("取得した約定状態区分＝引数.約定状態区分の場合");
                l_bReturn = true;
            }
            else
            {
                //取得した約定状態区分≠引数.約定状態区分の場合
                log.debug("取得した約定状態区分≠引数.約定状態区分の場合");
                l_bReturn = false;
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_bReturn;
    }

    /**
     * (get出来るまで注文単位FromFirstToLast)<BR>
     * 指定された「出来るまで注文」の注文単位オブジェクトに対する、<BR>
     * 原注文～最新の注文までの注文単位オブジェクトの一覧を取得する。<BR>
     * <BR>
     * 原注文～最新の注文までの注文単位オブジェクトを下記抽出条件にて取得する。<BR>
     * <BR>
     * 　@　@＜抽出条件＞<BR>
     * <BR>
     * 　@　@　@[パラメータ.注文単位.初回注文の注文単位ID == 0（＝出来るまで注文の原注文）の場合]<BR>
     * 　@　@　@　@パラメータ.注文単位（原注文自身）、<BR>
     * 　@　@　@　@及び　@初回注文の注文単位ID == パラメータ.注文単位.注文単位ID<BR>
     * <BR>
     * 　@　@　@[上記以外の場合]<BR>
     * 　@　@　@　@初回注文の注文単位ID == パラメータ.注文単位.初回注文の注文単位ID　@または<BR>
     * 　@　@　@　@注文単位ID == パラメータ.注文単位.初回注文の注文単位ID<BR>
     * <BR>
     * 　@　@　@　@※原注文.初回注文の注文単位IDには、0がセットされている為。<BR>
     * <BR>
     * 　@　@　@　@取得した注文単位オブジェクトを作成日時順に昇順でソートし、配列にして返却する。<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.equity.EqTypeOrderUnit[]
     * @@roseuid 407F49D000B6
     */
    protected EqTypeOrderUnit[] getExecutedOrderUnitFromFirstToLast(EqTypeOrderUnit l_orderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExecutedOrderUnitFromFirstToLast(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        EqTypeOrderUnit[] l_orderUnits = null;
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        List l_lisRecords = null;
        String l_strWhere = "order_unit_id=? or first_order_unit_id=?";
        Object l_objWhere[];
        if (l_orderUnitRow.getFirstOrderUnitId() == 0L)
        {
            l_objWhere =
                new Object[] {
                    new Long(l_orderUnitRow.getOrderUnitId()),
                    new Long(l_orderUnitRow.getOrderUnitId())};
        }
        else
        {
            l_objWhere =
                new Object[] {
                    new Long(l_orderUnitRow.getFirstOrderUnitId()),
                    new Long(l_orderUnitRow.getFirstOrderUnitId())};
        }
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords =
                l_queryProcessor.doFindAllQuery(
                    EqtypeOrderUnitRow.TYPE,
                    l_strWhere,
                    "created_timestamp asc",
                    null,
                    l_objWhere);

        }
        catch (DataException l_de)
        {
            log.error(getClass().getName() + "." + STR_METHOD_NAME, l_de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);            
        }

        int l_intSize = l_lisRecords.size();
        l_orderUnits = new EqTypeOrderUnit[l_intSize];
        for (int i = 0;i < l_intSize;i++)
        {
            EqtypeOrderUnitRow l_orderUnitRowNew =
                (EqtypeOrderUnitRow)l_lisRecords.get(i);
            l_orderUnits[i] =
                (EqTypeOrderUnit)l_orderManager.toOrderUnit(l_orderUnitRowNew);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_orderUnits;
    }

    /**
     * (get注文単位一覧Fromリクエスト)<BR>
     * 注文単位オブジェクトを、リクエスト中の検索条件を指定し取得する。<BR>
     * <BR>
     * １）　@this.create検索条件文字列(引数のリクエスト.銘柄コード, 引数のリクエスト.市場コード,<BR>
     * 　@　@　@引数のリクエスト.発注日, 引数のis現物取得, 引数のis立会外分売取得, 引数のリクエスト.発注条件区分)により、<BR>
     * 　@　@　@検索条件文字列を作成する。<BR>
     * <BR>
     * ２）　@this.create検索条件データコンテナ(引数のリクエスト.銘柄コード, 引数のリクエスト.市場コード,<BR>
     * 　@　@　@引数のリクエスト.発注日, 引数のリクエスト.発注条件区分)により、<BR>
     * 　@　@　@検索条件データコンテナを作成する。<BR>
     * <BR>
     * ３）　@this.createソート条件(引数のリクエスト.ソートキー)により、ソート条件文字列を作成する。<BR>
     * <BR>
     * ４）　@拡張株式注文マネージャ.get注文単位一覧( )により、<BR>
     * 　@　@　@検索条件に合致する注文単位オブジェクトのListを取得する。<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------<BR>
     * 　@　@　@＜get注文単位一覧( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@補助口座：　@引数の補助口座<BR>
     * 　@　@　@銘柄タイプ：　@"株式"<BR>
     * 　@　@　@検索条件文字列：　@create検索条件文字列( )の戻り値<BR>
     * 　@　@　@検索条件データコンテナ：　@create検索条件データコンテナ( )の戻り値<BR>
     * 　@　@　@ソート条件：　@createソート条件( )の戻り値<BR>
     * 　@　@　@----------------------------------------------------<BR>
     * <BR>
     * ５）　@取得したListを返す。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@param l_isMarketTrading - (is現物取得)<BR>
     * @@param l_isSalesOutsideMarket - (is立会外分売取得)<BR>
     * @@return java.util.List
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 407FAFBC022D
     */
    protected List getOrderUnitsFromRequest(
        WEB3GentradeSubAccount l_subAccount,
        WEB3EquityExecuteReferenceRequest l_request,
        boolean l_isMarketTrading,
        boolean l_isSalesOutsideMarket)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getOrderUnitsFromRequest(WEB3GentradeSubAccount, WEB3EquityExecuteReferenceRequest, boolean, boolean)";
        log.entering(STR_METHOD_NAME);
        
        List l_lisReturn = null;
        
        // １）　@this.create検索条件文字列(引数のリクエスト.銘柄コード, 引数のリクエスト.市場コード,
        // 　@　@　@引数のリクエスト.発注日, 引数のis現物取得, 引数のis立会外分売取得, 引数のリクエスト.発注条件区分)により、
        // 　@　@　@検索条件文字列を作成する。
        String l_strFindWhere =
            this.createQueryCond(
                l_request.productCode,
                l_request.marketCode,
                l_request.orderBizDate,
                l_isMarketTrading,
                l_isSalesOutsideMarket,
                l_request.orderCondType);
        log.debug("検索条件文字列 l_strFindWhere = " + l_strFindWhere);
        
        // ２）　@this.create検索条件データコンテナ(引数のリクエスト.銘柄コード, 引数のリクエスト.市場コード,
        // 　@　@　@引数のリクエスト.発注日, 引数のリクエスト.発注条件区分)により、
        // 　@　@　@検索条件データコンテナを作成する。
        String[] l_strArrCond =
            this.createQueryCondDataContainer(
                l_request.productCode,
                l_request.marketCode,
                l_request.orderBizDate,
                l_request.orderCondType);
        log.debug("検索条件データコンテナ l_strArrCond = " + l_strArrCond);
        
        // ３）　@this.createソート条件(引数のリクエスト.ソートキー)により、ソート条件文字列を作成する。
        String l_strOrder = null;
        if (l_request.sortKeys != null) {
            l_strOrder = this.createSortCond(l_request.sortKeys);
        }
        
        // ４）　@拡張株式注文マネージャ.get注文単位一覧( )により、
        // 　@　@　@検索条件に合致する注文単位オブジェクトのListを取得する。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        l_lisReturn =
            l_orderManager.getOrderUnits(
                (WEB3GentradeSubAccount)l_subAccount,
                ProductTypeEnum.EQUITY,
                l_strFindWhere,
                l_strArrCond,
                l_strOrder);
        
        log.exiting(STR_METHOD_NAME);
        return l_lisReturn;
    }
}
@
