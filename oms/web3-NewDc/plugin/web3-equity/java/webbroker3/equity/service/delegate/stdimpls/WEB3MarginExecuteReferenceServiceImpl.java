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
filename	WEB3MarginExecuteReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引注文約定照会サービスImpl(WEB3MarginExecuteReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/21 中尾寿彦(SRA) 新規作成
Revesion History : 2006/07/05 肖志偉 (中訊) 仕様変更モデル942
Revesion History : 2006/11/27 張騰宇(中訊) （モデル）No.998 No.1084
Revesion History : 2007/06/05 何文敏 (中訊) 仕様変更・モデル1164
Revesion History : 2007/10/16 金傑(中訊) 仕様変更モデル1198、仕様変更モデル1199
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeClosingContractSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeContractSettleOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeContractSwapOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderAction;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3MarginAccountOpenDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderStatusDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3TaxTypeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityFinTransactionManager;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3MarginClientRequestService;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.equity.define.WEB3EquityReferenceTypeDef;
import webbroker3.equity.define.WEB3EquityWlimitOrderPriceDivDef;
import webbroker3.equity.message.WEB3MarginChangeCancelHistoryGroup;
import webbroker3.equity.message.WEB3MarginCloseMarginContractGroup;
import webbroker3.equity.message.WEB3MarginCloseMarginContractListRequest;
import webbroker3.equity.message.WEB3MarginCloseMarginContractListResponse;
import webbroker3.equity.message.WEB3MarginCommissionInfoUnit;
import webbroker3.equity.message.WEB3MarginExecuteDetailsRequest;
import webbroker3.equity.message.WEB3MarginExecuteDetailsResponse;
import webbroker3.equity.message.WEB3MarginExecuteGroup;
import webbroker3.equity.message.WEB3MarginExecuteReferenceRequest;
import webbroker3.equity.message.WEB3MarginExecuteReferenceResponse;
import webbroker3.equity.message.WEB3MarginExecuteUnit;
import webbroker3.equity.message.WEB3MarginOrderHistoryRequest;
import webbroker3.equity.message.WEB3MarginOrderHistoryResponse;
import webbroker3.equity.message.WEB3MarginRepaymentUnit;
import webbroker3.equity.message.WEB3MarginSortKey;
import webbroker3.equity.service.delegate.WEB3MarginExecuteReferenceService;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchMarketRepayDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * （信用取引注文約定照会サービスImpl）。<BR>
 * <BR>
 * 信用取引注文約定照会サービス実装クラス
 * @@author 中尾寿彦
 * @@version 1.0
 */
public class WEB3MarginExecuteReferenceServiceImpl
    extends WEB3MarginClientRequestService
    implements WEB3MarginExecuteReferenceService
{

    /**
     * (ログ出力ユーティリティ。)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginExecuteReferenceServiceImpl.class);

    /**
     * 信用取引注文約定照会サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型により、以下の処理を呼び分ける。<BR>
     * <BR>
     * ・search注文約定照会<BR>
     * ・search注文約定詳細<BR>
     * ・search注文履歴照会<BR>
     * ・search決済建株一覧<BR>
     * @@param l_request - (リクエスト)<BR>
     * @@return WEB3GenResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4058302000E7
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.error("リクエスト==null");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3GenResponse l_response = null;

        //search注文約定照会
        if (l_request instanceof WEB3MarginExecuteReferenceRequest)
        {
            l_response =
                searchOrderExecuteReference(
                    (WEB3MarginExecuteReferenceRequest)l_request);
        }

        //search注文約定詳細
        else if (l_request instanceof WEB3MarginExecuteDetailsRequest)
        {
            l_response =
                searchOrderExecuteDetails(
                    (WEB3MarginExecuteDetailsRequest)l_request);
        }

        //search注文履歴照会        
        else if (l_request instanceof WEB3MarginOrderHistoryRequest)
        {
            l_response =
                searchOrderHistoryReference(
                    (WEB3MarginOrderHistoryRequest)l_request);
        }

        //決済建株一覧
        else if (l_request instanceof WEB3MarginCloseMarginContractListRequest)
        {
            l_response =
                searchCloseMarginContractList(
                    (WEB3MarginCloseMarginContractListRequest)l_request);
        }
        else
        {
            log.error(" __Error[入力値が不正です]");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (search注文約定照会)<BR>
     * 信用取引注文約定照会処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(信用取引注文約定照会サービス)search注文約定照会」参照。<BR>
     * <BR>
     *  ========================================================<BR>
     *   シーケンス図(「(信用取引注文約定照会サービス)search注文約定照会」) : <BR>   
     *   getProduct(証券会社 : Institution, 銘柄コード : 論理ビュー::java::lang::String)<BR>   
     *   取得できなかった場合は、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00301<BR>
     *   ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     *   シーケンス図(「(信用取引注文約定照会サービス)search注文約定照会」) : <BR>   
     *   (17*)get取引銘柄(WEB3EquityProduct, 市場)<BR>   
     *   今の段階では指定された銘柄コードの銘柄/取引銘柄が存在するかどうかのチェックのみを実施する。<BR>
     *   取得できなかった場合は、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00638<BR>
     *   ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3MarginExecuteReferenceResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40FC85DC01F0
     */
    protected WEB3MarginExecuteReferenceResponse searchOrderExecuteReference(
        WEB3MarginExecuteReferenceRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "searchOrderExecuteReference(WEB3MarginExecuteReferenceRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MarginExecuteReferenceResponse l_response = null;
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityProductManager l_productManager =
                (WEB3EquityProductManager)l_tradingModule.getProductManager();
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        l_request.validate();
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)this.getMainAccount();
        WEB3GentradeBranch l_branch =
            (WEB3GentradeBranch)l_mainAccount.getBranch();
        if (l_branch.isMarginTradeEnforcement(WEB3GentradeRepaymentDivDef.DEFAULT) == false)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00746,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT) == false)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00747,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //注文約定照会の場合
        if (WEB3EquityReferenceTypeDef.REFERENCE_TYPE_VIEW.equals(l_request.referenceType))
        {
            //validate注文受付可能()
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
            
            //銘柄コード指定時
            if (l_request.productCode != null && l_request.productCode.length() > 0)
            {
                Institution l_institution = l_subAccount.getInstitution();
                EqTypeProduct l_eqtypeProduct;
                try
                {
                    l_eqtypeProduct = l_productManager.getProduct(
                            l_institution,
                            l_request.productCode);
                }
                catch (NotFoundException l_nfe)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00301, STR_METHOD_NAME,
                        l_nfe.getMessage(), null);
                }
            }
        }
        //訂正取消一覧の場合
        else if (WEB3EquityReferenceTypeDef.REFERENCE_TYPE_UPDATE.equals(l_request.referenceType))
        {
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.CHANGE);
            try
            {
                WEB3GentradeTradingTimeManagement.validateOrderAccept();
            }
            catch (WEB3BusinessLayerException e1)
            {
                WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(
                    WEB3OrderAccTransactionDef.CANCEL);
                try
                {
                    WEB3GentradeTradingTimeManagement.validateOrderAccept();
                }
                catch (WEB3BusinessLayerException e2)
                {
                    WEB3GentradeTradingTimeManagement.resetTradingTimeType(
                        WEB3TradingTimeTypeDef.SWAP);
                    WEB3GentradeTradingTimeManagement.validateOrderAccept();
                }

            }

            WEB3GentradeOrderValidator l_orderValidator =
                (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
            OrderValidationResult l_validationResult =
                l_orderValidator.validateSubAccountForTrading(l_subAccount);

            if (l_validationResult.getProcessingResult().isFailedResult())
            {
                log.error("!OrderValidationResult.VALIDATION_OK_RESULT");
                throw new WEB3BusinessLayerException(
                    l_validationResult.getProcessingResult().getErrorInfo(),
                    l_validationResult
                        .getProcessingResult()
                        .getErrorInfo()
                        .getErrorMessage());
            }

            //銘柄コード指定時
            if (l_request.productCode != null && l_request.productCode.length() > 0)
            {
                Institution l_institution = l_subAccount.getInstitution();
                EqTypeProduct l_eqtypeProduct;
                try
                {
                    l_eqtypeProduct = l_productManager.getProduct(
                            l_institution,
                            l_request.productCode);
                }
                catch (NotFoundException l_nfe)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00301, STR_METHOD_NAME,
                        l_nfe.getMessage(), null);
                }

                //市場コード指定時
                if (l_request.marketCode != null && l_request.marketCode.length() > 0)
                {
                    Market l_market;
                    try
                    {
                        l_market = l_finObjectManager.getMarket(
                                l_institution.getInstitutionCode(),
                                l_request.marketCode);
                    }
                    catch (NotFoundException l_nfe)
                    {
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00003, STR_METHOD_NAME,
                            l_nfe.getMessage(), l_nfe);
                    }
                    try
                    {
                        l_productManager.getTradedProduct(
                            l_eqtypeProduct,
                            l_market);
                    }
                    catch (NotFoundException l_nfe)
                    {
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00638, STR_METHOD_NAME,
                            l_nfe.getMessage(), null);
                    }
                }
            }
        }
        
        l_response = (WEB3MarginExecuteReferenceResponse)l_request.createResponse();
        
        WEB3MarginExecuteGroup[] l_group = null;
        //create注文約定照会()
        l_group = this.createExecuteReference(l_subAccount, l_request, l_response);
        
        MainAccountRow l_mainAccountRow =
            (MainAccountRow)l_mainAccount.getDataSourceObject();
        
        //get市場閉局警告市場()
        String l_marginAccOpenDiv = null;
        if (WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN.equals(l_mainAccountRow.getMarginSysAccOpenDiv())
            && WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_NOT_OPEN.equals(l_mainAccountRow.getMarginGenAccOpenDiv()))
        {
            l_marginAccOpenDiv = WEB3MarginTradingDivDef.MARKET_MARGIN;
        }
        if (WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_NOT_OPEN.equals(l_mainAccountRow.getMarginSysAccOpenDiv()) 
            && WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN.equals(l_mainAccountRow.getMarginGenAccOpenDiv()))
        {
            l_marginAccOpenDiv = WEB3MarginTradingDivDef.INSTITUTION_MARGIN;
        }
        if (WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN.equals(l_mainAccountRow.getMarginSysAccOpenDiv())
            && WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN.equals(l_mainAccountRow.getMarginGenAccOpenDiv()))
        {
            l_marginAccOpenDiv = WEB3MarginTradingDivDef.MARKET_MARGIN_OR_INSTITUTION_MARGIN;
        }
        WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        String[] l_closeMarket =
            WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
                l_subAccount.getWeb3GenBranch(),
                ProductTypeEnum.EQUITY,
                l_marginAccOpenDiv);
        
        //get取扱可能市場()
        String l_marginAccOpenDivMarket = null;
        if (WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN.equals(l_mainAccountRow.getMarginSysAccOpenDiv())
           && WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_NOT_OPEN.equals(l_mainAccountRow.getMarginGenAccOpenDiv()))
        {
            l_marginAccOpenDivMarket = WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS;
        }
        if (WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_NOT_OPEN.equals(l_mainAccountRow.getMarginSysAccOpenDiv()) 
            && WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN.equals(l_mainAccountRow.getMarginGenAccOpenDiv()))
        {
            l_marginAccOpenDivMarket = WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN;
        }
        if (WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN.equals(l_mainAccountRow.getMarginSysAccOpenDiv())
            && WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN.equals(l_mainAccountRow.getMarginGenAccOpenDiv()))
        {
            l_marginAccOpenDivMarket = WEB3GentradeRepaymentDivDef.DEFAULT;
        }
        String[] l_possibleMarkets =
            WEB3GentradeBranchMarketRepayDealtCond.getHandlingPossibleMarket(
                l_subAccount.getWeb3GenBranch(),
                l_marginAccOpenDivMarket,
                0.0D);
        
        //レスポンスデータにプロパティをセットする
        TreeMap l_orderBizDateMap = new TreeMap();
        Date l_datBizdate = GtlUtils.getTradingSystem().getBizDate();
        Timestamp l_tsBizdate = new Timestamp(l_datBizdate.getTime());
        
        WEB3GentradeBizDate l_genBizDate =
            new WEB3GentradeBizDate(l_tsBizdate);
        Date l_datBizDate = WEB3DateUtility.toDay(l_genBizDate.roll(-1));
        l_orderBizDateMap.put(l_datBizDate, l_datBizDate);
        
        Date l_datSysdate = WEB3DateUtility.toDay(l_tsBizdate);
        l_orderBizDateMap.put(l_datSysdate, l_datSysdate);
        
        int l_intMarketSize = 0;
        if (l_possibleMarkets != null)
        {
            l_intMarketSize = l_possibleMarkets.length;
        }
        for (int i = 0;i < l_intMarketSize;i++)
        {
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_possibleMarkets[i]);
            l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            if (!l_orderBizDateMap.containsKey(l_datBizDate))
            {
                l_orderBizDateMap.put(l_datBizDate, l_datBizDate);
            }
        }
        int l_intListSize = l_orderBizDateMap.size();
        Date[] l_orderBizDateList = new Date[l_intListSize];
        Collection l_collection = l_orderBizDateMap.values();
        l_collection.toArray(l_orderBizDateList);
        l_response.orderBizDateList = l_orderBizDateList;
        
        if (l_group == null)
        {
            l_response.productCodeNames = null;
            l_response.marketList = l_possibleMarkets;
            l_response.executeGroups = null;
            l_response.totalPages = "0";
            l_response.totalRecords = "0";
            l_response.pageIndex = "0";
            l_response.idList = null;
            l_response.messageSuspension = l_closeMarket;
            log.debug("****** レスポンス．注文情報一覧の要素数：[0]");
            log.debug("****** レスポンス．ID一覧の要素数：[0]");
        }
        else
        {
            l_response.productCodeNames = null;
            l_response.marketList = l_possibleMarkets;
            l_response.messageSuspension = l_closeMarket;
            log.debug("****** レスポンス．注文情報一覧の要素数：[" + l_response.executeGroups.length + "]");
            log.debug("****** レスポンス．ID一覧の要素数：[" + l_response.idList.length + "]");
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (search注文約定詳細)<BR>
     * 信用取引注文約定詳細処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(信用取引注文約定照会サービス)search注文約定詳細」参照。<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return WEB3MarginExecuteDetailsResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40FC86B8029C
     */
    protected WEB3MarginExecuteDetailsResponse searchOrderExecuteDetails(
        WEB3MarginExecuteDetailsRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "searchOrderExecuteDetails(WEB3MarginExecuteDetailsRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MarginExecuteDetailsResponse l_response = null;
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        l_request.validate();

        long l_lngOrderId = Long.parseLong(l_request.id);
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
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
        
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //get手数料コースコード()
        WEB3EquityBizLogicProvider l_bizLogicProvider =
        (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();

        String l_strCommissionCourseDiv = null;
        try
        {
            l_strCommissionCourseDiv = l_bizLogicProvider.getCommissionCourseDiv(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_orderUnitRow.getCommProductCode(),
                l_orderUnitRow.getCommTblNo(),
                l_orderUnitRow.getCommTblSubNo(),
                WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), "yyyyMMdd"));
        }
        catch (WEB3SystemLayerException l_wse)
        {
            if (l_wse.getErrorInfo() != WEB3ErrorCatalog.SYSTEM_ERROR_80005)
            {
                throw l_wse;
            }
        }
        
        String l_strOrderState = WEB3EquityDataAdapter.getOrderState(l_orderUnit);
        String l_strExecType = WEB3EquityDataAdapter.getExecType(l_orderUnit);
        //get処理状況区分()
        String l_transactionStateType =
            WEB3EquityDataAdapter.getTransactionStatusType(l_orderUnit);
        String l_strExcCondTypeSonar =
            l_orderManager.getExecutionConditionTypeSonar(
                l_orderUnitRow.getExecutionConditionType());

        //get執行条件（SONAR））(EqTypeExecutionConditionType)
        //執行条件：　@取得した注文単位オブジェクトの0番目の要素.（W指値）執行条件
        String l_strWlimitExecCondType = null;
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            l_strWlimitExecCondType =
                l_orderManager.getExecutionConditionTypeSonar(
                    l_orderUnitRow.getWLimitExecCondType());
        }

        //getＷ指値用有効状態区分(EqTypeOrderUnit)
        //注文単位：　@取得した注文単位オブジェクトの0番目の要素
        String l_strWlimitEnableStatusDiv =
            WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_orderUnit);

        // getＷ指値用切替前注文単価(EqTypeOrderUnit)
        //注文単位：　@取得した注文単位オブジェクトの0番目の要素
        String l_strWlimitBefChgLimitPrice =
            WEB3EquityDataAdapter.getWLimitBefSwitchPrice(l_orderUnit);

        // getＷ指値用切替前執行条件(EqTypeOrderUnit)
        //注文単位：　@取得した注文単位オブジェクトの0番目の要素
        String l_strWLimitBefSwitchExecCondType =
            WEB3EquityDataAdapter.getWLimitBefSwitchExecCondType(l_orderUnit);

        //get元Ｗ指値用注文単価区分(EqTypeOrderUnit)
        //注文単位：　@取得した注文単位オブジェクトの0番目の要素
        String l_strOrgWlimitOrderPriceDiv =
            WEB3EquityDataAdapter.getOrgWLimitOrderPriceDiv(l_orderUnit);

        //get元Ｗ指値用注文単価(EqTypeOrderUnit)
        //注文単位：　@取得した注文単位オブジェクトの0番目の要素
        String l_strOrgWlimitPrice =
            WEB3EquityDataAdapter.getOrgWLimitOrderPrice(l_orderUnit);

        //get元Ｗ指値用執行条件(EqTypeOrderUnit)
        //注文単位：　@取得した注文単位オブジェクトの0番目の要素
        String l_strOrgWlimitExecCondType =
            WEB3EquityDataAdapter.getOrgWLimitExecCondType(l_orderUnit);

        //get遅延区分(EqTypeOrderUnit)
        //注文単位：　@取得した注文単位オブジェクトの0番目の要素
        String l_strDelayDiv =
            WEB3EquityDataAdapter.getDelayDiv(l_orderUnit);

        //is手動発注可能(EqTypeOrderUnit)
        //注文単位：　@取得した注文単位オブジェクトの0番目の要素
        boolean l_blnIsManualOrderPossible =
            l_orderManager.isManualOrderPossible(l_orderUnit);

        OrderExecution[] l_executions = l_orderUnit.getExecutions();
        WEB3EquityFinTransactionManager l_finManager =
            (WEB3EquityFinTransactionManager)l_tradingModule.getFinTransactionManager();
        List l_transactionList =
            l_finManager.getTransactions(l_orderUnit);

        //トランザクション(株式顧客勘定明細)ごとのLoop処理
        int l_intSize = 0;
        if(l_transactionList != null)
        {
            l_intSize = l_transactionList.size();
        }
        //受渡代金
        double l_dblDeliveryAmt = 0.0D;
        if (OrderCategEnum.OPEN_MARGIN.equals(l_orderUnit.getOrderCateg()))
        {
            l_dblDeliveryAmt = l_orderManager.getEstimateDeliveryAmountForClose(l_orderUnit);
        }
        //委託手数料
        double l_dblCommissionFee = 0.0D;
        //委託手数料消費税
        double l_dblCommissionFeeTax = 0.0D;
        for (int i = 0;i < l_intSize;i++)
        {
            EqtypeFinTransactionRow l_transactionRow =
                (EqtypeFinTransactionRow)l_transactionList.get(i);
            //返済注文、現引現渡注文（≠新規建注文）の場合、以下の処理を実施する。
            if (!OrderCategEnum.OPEN_MARGIN.equals(l_orderUnit.getOrderCateg()))
            {
                double l_dblNetAmount = l_transactionRow.getNetAmount();
                l_dblDeliveryAmt += l_dblNetAmount;
            }
            double l_dblCommissionFeeUnit =
                l_transactionRow.getCommissionFee();
            l_dblCommissionFee += l_dblCommissionFeeUnit;

            double l_dblCommissionFeeTaxUnit =
                l_transactionRow.getCommissionFeeTax();
            l_dblCommissionFeeTax += l_dblCommissionFeeTaxUnit;
        }
        
        l_response =
            (WEB3MarginExecuteDetailsResponse)l_request.createResponse();
        
        //プロパティセット
        //<注文系情報>
        l_response.orderActionId =
            Long.toString(l_orderUnitRow.getOrderId());
            
        WEB3EquityProduct l_product =
            (WEB3EquityProduct)l_orderUnit.getProduct();
        l_response.productCode = l_product.getProductCode();
        EqtypeProductRow l_productRow = (EqtypeProductRow) l_product.getDataSourceObject();
        l_response.productName = l_productRow.getStandardName();
        
        Market l_market;
        try
        {
            l_market = l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00003, STR_METHOD_NAME,
                l_nfe.getMessage(), l_nfe);
        }
        l_response.marketCode = l_market.getMarketCode();

        TaxTypeEnum l_taxType = l_orderUnit.getTaxType();
        if (TaxTypeEnum.NORMAL.equals(l_taxType))
        {
            l_response.taxType = WEB3TaxTypeDef.NORMAL;
        }
        else if (TaxTypeEnum.SPECIAL.equals(l_taxType) ||
            TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxType))
        {
            l_response.taxType = WEB3TaxTypeDef.SPECIAL;
        }
        
        l_response.tradingType =
            Integer.toString(l_orderUnitRow.getOrderType().intValue());
            
        WEB3MarginRepaymentUnit l_repaymentUnit = new WEB3MarginRepaymentUnit();
        l_repaymentUnit.repaymentDiv = l_orderUnitRow.getRepaymentType();
        l_repaymentUnit.repaymentTimeLimit = Integer.toString(l_orderUnitRow.getRepaymentNum());
        l_response.repayment = l_repaymentUnit;
        
        l_response.priceCondType = l_orderUnitRow.getPriceConditionType();
        l_response.execCondType = l_strExcCondTypeSonar;
        
        l_response.orderCondType = l_orderUnitRow.getOrderConditionType();
        String l_strOrderConditionType = l_orderUnitRow.getOrderConditionType();
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            l_response.stopOrderCondPrice =
                WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
            l_response.stopOrderCondOperator =
                l_orderUnitRow.getOrderCondOperator();
        }
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            l_response.wlimitOrderCondPrice =
                WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
            l_response.wlimitOrderCondOperator =
                l_orderUnitRow.getOrderCondOperator();
           if (l_orderUnitRow.getWLimitPrice() == 0)
           {
               l_response.wLimitOrderPriceDiv =
                   WEB3EquityWlimitOrderPriceDivDef
                       .WLIMIT_ORDER_PRICE_DIV_MARKET_PRICE;
           }
           else
           {
               l_response.wLimitOrderPriceDiv =
                   WEB3EquityWlimitOrderPriceDivDef
                       .WLIMIT_ORDER_PRICE_DIV_LIMIT_PRICE;    
               l_response.wLimitPrice =
                   WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getWLimitPrice());
           }

           //Ｗ指値用執行条件
           l_response.wlimitExecCondType = l_strWlimitExecCondType;
        }

        //Ｗ指値用有効状態区分
        l_response.wlimitEnableStatusDiv = l_strWlimitEnableStatusDiv;

        //Ｗ指値用切替前注文単価
        l_response.wlimitBefChgLimitPrice = l_strWlimitBefChgLimitPrice;

        //Ｗ指値用切替前執行条件
        l_response.wlimitBefChgExecCondType = l_strWLimitBefSwitchExecCondType;

        //元発注条件区分
        l_response.orgOrderCondType = l_orderUnitRow.getOrgOrderConditionType();

        //元発注条件単価
        if (l_orderUnitRow.getOrgStopOrderPriceIsNull())
        {
            l_response.orgOrderCondPrice = null;
        }
        else
        {
            l_response.orgOrderCondPrice =
                WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getOrgStopOrderPrice());
        }

        //元発注条件演算子
        l_response.orgOrderCondOperator = l_orderUnitRow.getOrgOrderCondOperator();

        //元Ｗ指値用注文単価区分
        l_response.orgWlimitOrderPriceDiv = l_strOrgWlimitOrderPriceDiv;

        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrgWlimitOrderPriceDiv))
        {
            //元Ｗ指値用注文単価
            l_response.orgWlimitPrice = l_strOrgWlimitPrice;
        }

        //元Ｗ指値用執行条件
        l_response.orgWlimitExecCondType = l_strOrgWlimitExecCondType;

        l_response.orderQuantity =
            WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getQuantity());
        if (l_orderUnit.isMarketOrder())
        {
            l_response.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
        }
        else
        {
            l_response.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
            l_response.limitPrice =
                WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getLimitPrice());
        }
        l_response.estimatedPrice =
            WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getEstimatedPrice());

        if (l_orderManager.isCarriedOrderUnit(l_orderUnit))
        {
            l_response.expirationDate = l_orderUnitRow.getExpirationDate();
        }

        l_response.orderDate = l_orderUnitRow.getReceivedDateTime();
        l_response.orderState = l_strOrderState;
        if (WEB3OrderStatusDef.NOT_TRANSFERED.equals(l_strOrderState))
        {
            l_response.transferErrCode =
                l_orderUnitRow.getErrorReasonCode();
        }
        l_response.orderBizDate = WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), "yyyyMMdd");
        l_response.execType = l_strExecType;
        
        //<約定系情報>
        if (l_orderUnit.isUnexecuted() == false)
        {
            l_response.deliveryDate = l_orderUnit.getDeliveryDate();
            l_response.execQuantity =
                WEB3StringTypeUtility.formatNumber(l_orderUnit.getExecutedQuantity());
            l_response.execPrice =
                WEB3StringTypeUtility.formatNumber(
                    Math.round(
                        l_orderUnitRow.getExecutedAmount()
                            / l_orderUnit.getExecutedQuantity()));   
            l_response.execTotalPrice =
                WEB3StringTypeUtility.formatNumber(
                    l_orderUnitRow.getExecutedAmount());
            l_response.deliveryPrice = WEB3StringTypeUtility.formatNumber(l_dblDeliveryAmt);

            WEB3MarginCommissionInfoUnit l_commissionInfoUnit =
                new WEB3MarginCommissionInfoUnit();
            l_commissionInfoUnit.commissionCourse = l_strCommissionCourseDiv;
            l_commissionInfoUnit.commission =
                WEB3StringTypeUtility.formatNumber(l_dblCommissionFee);
            l_commissionInfoUnit.commissionConsumptionTax =
                WEB3StringTypeUtility.formatNumber(l_dblCommissionFeeTax);
            l_response.commissionInfo = l_commissionInfoUnit;

            int l_intExeLength = 0;
            if (l_executions != null)
            {
                l_intExeLength = l_executions.length;
            }
            l_response.executeUnits = new WEB3MarginExecuteUnit[l_intExeLength];
            for (int i = 0;i < l_intExeLength;i++)
            {
                WEB3MarginExecuteUnit l_executeUnit = new WEB3MarginExecuteUnit();
                l_executeUnit.executionTimestamp =
                    l_executions[i].getExecutionTimestamp();
                l_executeUnit.execQuantity =
                    WEB3StringTypeUtility.formatNumber(
                        l_executions[i].getExecutionQuantity());
                l_executeUnit.execPrice =
                    WEB3StringTypeUtility.formatNumber(
                        l_executions[i].getExecutionPrice());
                l_response.executeUnits[i] = l_executeUnit;
            }
        }
        WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        l_response.branchCode = l_account.getBranch().getBranchCode();
        l_response.accountCode = l_account.getDisplayAccountCode();
        l_response.accountName = l_account.getDisplayAccountName();
        l_response.changeCancelDiv = l_orderUnitRow.getModifyCancelType();
        l_response.orderRootDiv = l_orderUnitRow.getOrderRootDiv();
        l_response.transactionStateType = l_transactionStateType;
        //遅延区分
        l_response.delayDiv = l_strDelayDiv;
        //手動発注可能フラグ
        l_response.manualFlag = l_blnIsManualOrderPossible;
        //強制決済理由
        l_response.forcedSettleReason = l_orderUnitRow.getForcedSettleReasonType();
        //強制失効区分
        l_response.forcedLapseDiv = l_orderUnitRow.getForcedExpireType();

        return l_response;
    }

    /**
     * (search注文履歴照会)<BR>
     * 信用取引注文履歴照会処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(信用取引注文約定照会サービス)search注文履歴照会」参照。<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return WEB3MarginOrderHistoryResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40FC86DC0387
     */
    protected WEB3MarginOrderHistoryResponse searchOrderHistoryReference(
        WEB3MarginOrderHistoryRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "searchOrderHistoryReference(WEB3MarginOrderHistoryRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MarginOrderHistoryResponse l_response = null;

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        l_request.validate();
        
        long l_lngOrderId = Long.parseLong(l_request.id);
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
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
        EqTypeOrderUnit[] l_firstOrderUnits = null;
        
        //出来るまで注文の場合のみ
        if (l_orderManager.isCarriedOrderUnit(l_orderUnit) == true)
        {
            l_firstOrderUnits =
                this.getCarriedOrderUnitFromFirstToLast(l_orderUnit);
        }
        // ※出来るまで注文でない場合は取得した注文単位オブジェクトの0番目の要素のみ
        else
        {
            l_firstOrderUnits = new EqTypeOrderUnit[1];
            l_firstOrderUnits[0] = l_orderUnit;
        }
        
        ArrayList l_lisOrderHistory = new ArrayList();
        for (int i = 0; i < l_firstOrderUnits.length;i++)
        {
            EqTypeOrderUnit l_orderUnitNew = l_firstOrderUnits[i];
            EqtypeOrderUnitRow l_orderUnitRowNew =
                (EqtypeOrderUnitRow)l_orderUnitNew.getDataSourceObject();
            //1.5.1. getOrderActions()
            OrderAction[] l_orderActions = l_orderUnitNew.getOrderActions();               
            int l_intOrderActionLen = 0;
            if (l_orderActions != null)
            {
                l_intOrderActionLen = l_orderActions.length;
            }
            //注文履歴要素ごとのLoop処理
            for (int j = 0; j < l_intOrderActionLen; j++)
            {
                WEB3MarginChangeCancelHistoryGroup l_historyGroup =
                    new WEB3MarginChangeCancelHistoryGroup();
                EqTypeOrderAction l_orderActionUnit =
                    (EqTypeOrderAction)l_orderActions[j];
                EqtypeOrderActionRow l_orderActionRow =
                    (EqtypeOrderActionRow)l_orderActionUnit.getDataSourceObject();
                    
                String l_strExecCondType =
                    l_orderManager.getExecutionConditionTypeSonar(
                    l_orderActionUnit.getExecutionConditionType());
                String l_orderSpecType =
					WEB3EquityDataAdapter.getOrderType(l_orderActionUnit, l_orderUnitNew);

                // get執行条件（SONAR）(EqTypeExecutionConditionType)
                String l_strWlimitExecCondType = null;
                if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderActionRow.getOrderConditionType()))
                {
                    l_strWlimitExecCondType =
                        l_orderManager.getExecutionConditionTypeSonar(
                            l_orderActionRow.getWLimitExecCondType());
                }

                // getＷ指値用有効状態区分(EqTypeOrderAction)
                String l_strWlimitEnableStatusDiv =
                    WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_orderActionUnit);

                // getＷ指値用切替前注文単価(EqTypeOrderAction)
                String l_strWlimitBefChgLimitPrice =
                    WEB3EquityDataAdapter.getWLimitBefSwitchPrice(l_orderActionUnit);

                //getＷ指値用切替前執行条件(EqTypeOrderAction)
                String l_strWLimitBefSwitchExecCondType =
                    WEB3EquityDataAdapter.getWLimitBefSwitchExecCondType(l_orderActionUnit);

                //get元Ｗ指値用注文単価区分(EqTypeOrderAction)
                String l_strOrgWlimitOrderPriceDiv =
                    WEB3EquityDataAdapter.getOrgWLimitOrderPriceDiv(l_orderActionUnit);

                //get元Ｗ指値用注文単価(EqTypeOrderAction)
                String l_strOrgWlimitPrice =
                    WEB3EquityDataAdapter.getOrgWLimitOrderPrice(l_orderActionUnit);

                //get元Ｗ指値用執行条件(EqTypeOrderAction)
                String l_strOrgWlimitExecCondType =
                    WEB3EquityDataAdapter.getOrgWLimitExecCondType(l_orderActionUnit);

                String l_acceptedResult =
					WEB3EquityDataAdapter.getAcceptedResultDiv(l_orderActionUnit);
                l_historyGroup.orderActionId =
                    Long.toString(l_orderActionUnit.getOrderActionId());
                l_historyGroup.orderDate =
                    l_orderActionRow.getCreatedTimestamp();
                l_historyGroup.orderType = l_orderSpecType;
                l_historyGroup.acceptedResultDiv = l_acceptedResult;
                //約定時に作成された履歴でない場合(注文履歴.isUnexecuted() == true)のみセット
                if (l_orderActionUnit.isUnexecuted() == true)
                {
                    l_historyGroup.priceCondType = l_orderActionRow.getPriceConditionType();
                    l_historyGroup.execCondType = l_strExecCondType;
                    l_historyGroup.orderCondType =
                        l_orderActionRow.getOrderConditionType();
                    String l_strOrderConditionType = l_orderActionRow.getOrderConditionType();
                    if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType))
                    {
                        l_historyGroup.stopOrderCondPrice =
                            WEB3StringTypeUtility.formatNumber(l_orderActionRow.getStopOrderPrice());
                        l_historyGroup.stopOrderCondOperator =
                            l_orderActionRow.getOrderCondOperator();
                    }
                    else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
                    {
                        l_historyGroup.wlimitOrderCondPrice =
                            WEB3StringTypeUtility.formatNumber(l_orderActionRow.getStopOrderPrice());
                        l_historyGroup.wlimitOrderCondOperator =
                            l_orderActionRow.getOrderCondOperator();
                        if (l_orderActionRow.getWLimitPrice() == 0)
                        {
                            l_historyGroup.wLimitOrderPriceDiv =
                                WEB3EquityWlimitOrderPriceDivDef.WLIMIT_ORDER_PRICE_DIV_MARKET_PRICE;
                        }
                        else
                        {
                            l_historyGroup.wLimitOrderPriceDiv =
                                WEB3EquityWlimitOrderPriceDivDef.WLIMIT_ORDER_PRICE_DIV_LIMIT_PRICE;
                            l_historyGroup.wLimitPrice =
                                WEB3StringTypeUtility.formatNumber(l_orderActionRow.getWLimitPrice());
                        }
                        //Ｗ指値用執行条件
                        l_historyGroup.wlimitExecCondType = l_strWlimitExecCondType;
                    }

                    //Ｗ指値用有効状態区分
                    l_historyGroup.wlimitEnableStatusDiv = l_strWlimitEnableStatusDiv;

                    //Ｗ指値用切替前注文単価
                    l_historyGroup.wlimitBefChgLimitPrice = l_strWlimitBefChgLimitPrice;

                    //Ｗ指値用切替前執行条件
                    l_historyGroup.wlimitBefChgExecCondType = l_strWLimitBefSwitchExecCondType;

                    //元発注条件区分
                    l_historyGroup.orgOrderCondType = l_orderActionRow.getOrgOrderConditionType();

                    if (!l_orderActionRow.getOrgStopOrderPriceIsNull())
                    {
                        //元発注条件単価
                        l_historyGroup.orgOrderCondPrice =
                            WEB3StringTypeUtility.formatNumber(l_orderActionRow.getOrgStopOrderPrice());
                    }

                    //元発注条件演算子
                    l_historyGroup.orgOrderCondOperator = l_orderActionRow.getOrgOrderCondOperator();

                    //元Ｗ指値用注文単価区分
                    l_historyGroup.orgWlimitOrderPriceDiv = l_strOrgWlimitOrderPriceDiv;

                    if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrgWlimitOrderPriceDiv))
                    {
                        //元Ｗ指値用注文単価
                        l_historyGroup.orgWlimitPrice = l_strOrgWlimitPrice;
                    }

                    //元Ｗ指値用執行条件
                    l_historyGroup.orgWlimitExecCondType = l_strOrgWlimitExecCondType;

                    l_historyGroup.orderQuantity =
                        WEB3StringTypeUtility.formatNumber(l_orderActionRow.getQuantity());
                    if (l_orderActionUnit.isMarketOrder() == true)
                    {
                        l_historyGroup.orderPriceDiv =
                            WEB3OrderPriceDivDef.MARKET_PRICE;
                    }
                    else
                    {
                        l_historyGroup.orderPriceDiv =
                            WEB3OrderPriceDivDef.LIMIT_PRICE;
                        l_historyGroup.limitPrice =
                            WEB3StringTypeUtility.formatNumber(l_orderActionRow.getPrice());
                    }
                    if (l_orderManager.isCarriedOrderUnit(l_orderUnit) == true)
                    {
                        l_historyGroup.expirationDate =
                            WEB3DateUtility.toDay(l_orderActionRow.getExpirationDate());
                    }
                }
                else
                {
                    l_historyGroup.execQuantity =
                        WEB3StringTypeUtility.formatNumber(
                            l_orderActionUnit.getExecutionQuantity());
                    l_historyGroup.execPrice =
                        WEB3StringTypeUtility.formatNumber(
                            l_orderActionUnit.getExecutionPrice());
                }
                l_lisOrderHistory.add(l_historyGroup);
            }
        }
        
        WEB3MarginChangeCancelHistoryGroup[] l_historyGroups =
            new WEB3MarginChangeCancelHistoryGroup[l_lisOrderHistory.size()];
        l_lisOrderHistory.toArray(l_historyGroups);
        
        l_response =
            (WEB3MarginOrderHistoryResponse)l_request.createResponse();
        l_response.changeCancelHistoryGroups = l_historyGroups;

        return l_response;
    }

    /**
     * (search決済建株一覧)<BR>
     * 信用取引決済建株一覧処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(信用取引注文約定照会サービス)search決済建株一覧」参照。<BR>
     * <BR>
     *  ========================================================<BR>
     *  シーケンス図(「(信用取引注文約定照会サービス)search決済建株一覧」) : <BR>   
     *  (4*) (*)注文カテゴリチェック<BR>   
     *  (*)注文カテゴリチェック<BR>
     *  返済注文、現引現渡注文 ,(注文単位.注文カテゴリ != ”返済注文”　@または　@”現引現渡注文”)<BR>
     *  以外は、「処理対象外」の例外をスローする。<BR>
     *  class: WEB3BusinessLayerException<BR>
     *  tag:   BUSINESS_ERROR_00653<BR>
     *  ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「(信用取引注文約定照会サービス)search決済建株一覧」) : <BR>   
     * (5*)getContractsToClose( )<BR>   
     * 注文単位IDに該当する建株返済指定情報オブジェクトを取得する。<BR>
     * 取得できなかった場合は、「該当データなし」の例外をスローする。<BR>
     * ※取得した注文単位の配列の0番目の要素を以下の条件で,　@それぞれの型にキャストしてコールする。<BR>
     * [注文単位.注文カテゴリ == ”返済注文”の場合　@　@EqTypeContractSettleOrderUnitにキャスト。<BR>
     * [注文単位.注文カテゴリ == ”現引現渡注文”の場合]　@　@EqTypeContractSwapOrderUnitにキャスト。<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag:   BUSINESS_ERROR_00659<BR>
     * ==========================================================<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return WEB3MarginCloseMarginContractListResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40FC870C02EA
     */
    protected WEB3MarginCloseMarginContractListResponse searchCloseMarginContractList(
        WEB3MarginCloseMarginContractListRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "searchCloseMarginContractList(WEB3MarginCloseMarginContractListRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MarginCloseMarginContractListResponse l_response = null;
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        try
        {
            l_request.validate();
        
            long l_lngOrderId = Long.parseLong(l_request.id);
            OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
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
        
            OrderCategEnum l_orderCateg = l_orderUnit.getOrderCateg();
            if (!OrderCategEnum.CLOSE_MARGIN.equals(l_orderCateg) &&
                !OrderCategEnum.SWAP_MARGIN.equals(l_orderCateg))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00653,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            EqTypeClosingContractSpec[] l_closingContractSpecs = null;
            if (OrderCategEnum.CLOSE_MARGIN.equals(l_orderCateg))
            {
                EqTypeContractSettleOrderUnit l_closingOrderUnit =
                    (EqTypeContractSettleOrderUnit)l_orderUnit;
                l_closingContractSpecs =
                    l_closingOrderUnit.getContractsToClose();
            }
            else if (OrderCategEnum.SWAP_MARGIN.equals(l_orderCateg))
            {
                EqTypeContractSwapOrderUnit l_swapOrderUnit =
                    (EqTypeContractSwapOrderUnit)l_orderUnit;
                l_closingContractSpecs =
                    l_swapOrderUnit.getContractsToClose();
            }
            if (l_closingContractSpecs.length == 0)
            {
                log.error("該当データなし");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00659,
                    getClass().getName() + "." + STR_METHOD_NAME);
            }
        
            ArrayList l_lisClosingContract = new ArrayList();

            int l_intClosingContractSpecsLen = l_closingContractSpecs.length;
            int l_intCnt = 0;
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            for (int i = 0;i < l_intClosingContractSpecsLen; i++)
            {
                EqTypeClosingContractSpec l_closeContractSpec =
                    l_closingContractSpecs[i];
                double l_dblQuantity = l_closeContractSpec.getQuantity();
                if (l_dblQuantity == 0.0D)
                {
                    continue;
                }
                long l_lngOrderUnitId = l_closeContractSpec.getOrderUnitId();
                long l_lngContractId = l_closeContractSpec.getContractId();
                WEB3EquityContract l_contract =
                    (WEB3EquityContract) l_positionManager.getContract(l_lngContractId);
                Date l_contractOpenDate = l_contract.getOpenDate();
                double l_dblContractPrice = l_contract.getContractPrice();
                double l_dblSetupFee = l_contract.getSetupFee(l_dblQuantity, l_lngOrderUnitId);
                double l_dblSetupFeeTax = l_contract.getSetupFeeTax(l_dblQuantity, l_lngOrderUnitId);
                double l_dblInterestFee = l_contract.getInterestFee(l_dblQuantity, l_lngOrderUnitId);
                double l_dblPayInterestFee = l_contract.getPayInterestFee(l_dblQuantity, l_lngOrderUnitId);
                double l_dblLoanEquityFee = l_contract.getLoanEquityFee(l_dblQuantity, l_lngOrderUnitId);
                double l_dblNameTransferFee = l_contract.getNameTransferFee(l_dblQuantity, l_lngOrderUnitId);
                double l_dblNameTransferFeeTax = l_contract.getNameTransferFeeTax(l_dblQuantity, l_lngOrderUnitId);
                double l_dblManagementFee = l_contract.getManagementFee(l_dblQuantity, l_lngOrderUnitId);
                double l_dblManagementFeeTax = l_contract.getManagementFeeTax(l_dblQuantity, l_lngOrderUnitId);
                double l_dblOther = l_contract.getOther(l_dblQuantity, l_lngOrderUnitId);
                double l_dblContractQuantity =
                    l_closeContractSpec.getQuantity();
                double l_dblContractExecutedQuantity =
                    l_closeContractSpec.getExecutedQuantity();
                
                List l_finTransactionList =
                    l_positionManager.getFinTransactionListByOrderUnitPlusContract(
                        l_lngOrderUnitId,
                        l_lngContractId);
                //取得したトランザクション(株式顧客勘定明細)ごとのLoop処理
                int l_intSize = l_finTransactionList.size();
                double l_dblNetAmount = 0.0D;
                double l_dblExecutedAmount = 0.0D;
                for (int j = 0;j < l_intSize;j++)
                {
                    EqtypeFinTransactionRow l_transactionRow =
                        (EqtypeFinTransactionRow)l_finTransactionList.get(j);
                    l_dblNetAmount += l_transactionRow.getNetAmount();
                    l_dblExecutedAmount += l_transactionRow.getPrice() * l_transactionRow.getQuantity();
                }
            
                WEB3MarginCloseMarginContractGroup l_group =
                    new WEB3MarginCloseMarginContractGroup();

                //プロパティセット
                l_group.openDate = WEB3DateUtility.toDay(l_contractOpenDate);
                l_group.contractPrice = WEB3StringTypeUtility.formatNumber(l_dblContractPrice);
                l_group.orderQuantity =
                    WEB3StringTypeUtility.formatNumber(l_dblContractQuantity);
                if (l_orderUnit.isMarketOrder())
                {
                    l_group.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                }
                else
                {
                    l_group.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                    l_group.limitPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getLimitPrice());
                }

                if (l_closeContractSpec.isUnexecuted() == false)
                {
                    l_group.execQuantity = WEB3StringTypeUtility.formatNumber(l_dblContractExecutedQuantity);    
                    l_group.execPrice =
                        WEB3StringTypeUtility.formatNumber(
                            Math.round(l_dblExecutedAmount / l_dblContractExecutedQuantity));
                }

                l_group.contractCommission =
                    WEB3StringTypeUtility.formatNumber(l_dblSetupFee + l_dblSetupFeeTax);
                l_group.interestFee =
                    WEB3StringTypeUtility.formatNumber(l_dblInterestFee);
                l_group.payInterestFee =
                    WEB3StringTypeUtility.formatNumber(l_dblPayInterestFee);
                l_group.loanEquityFee =
                    WEB3StringTypeUtility.formatNumber(l_dblLoanEquityFee);
                l_group.setupFee =
                    WEB3StringTypeUtility.formatNumber(l_dblNameTransferFee + l_dblNameTransferFeeTax);
                l_group.managementFee =
                    WEB3StringTypeUtility.formatNumber(l_dblManagementFee + l_dblManagementFeeTax);
                l_group.otherwise =
                    WEB3StringTypeUtility.formatNumber(l_dblOther);
                if (l_closeContractSpec.isUnexecuted() == false)
                {
                    l_group.settleProfitLoss =
                        WEB3StringTypeUtility.formatNumber(l_dblNetAmount);
                }
                if (l_orderUnitRow.getClosingOrderType() != null)
                {
                    l_group.settlePriority =
                        WEB3StringTypeUtility.formatNumber(++l_intCnt);
                }
                l_lisClosingContract.add(l_group);
            }
        
            WEB3MarginCloseMarginContractGroup[] l_closeMaginGroup =
                new WEB3MarginCloseMarginContractGroup[l_lisClosingContract.size()];
            l_lisClosingContract.toArray(l_closeMaginGroup);
        
            l_response =
                (WEB3MarginCloseMarginContractListResponse)l_request.createResponse();

            //プロパティセット
            WEB3EquityContract l_contract1 =
                (WEB3EquityContract)l_positionManager.getContract(
                    l_closingContractSpecs[0].getContractId());
            EqtypeContractRow l_eqtypeContractRow =
                (EqtypeContractRow)l_contract1.getDataSourceObject();
            WEB3EquityProduct l_product =
                (WEB3EquityProduct)l_contract1.getProduct();
            EqtypeProductRow l_productRow =
                (EqtypeProductRow)l_product.getDataSourceObject();
            l_response.productCode = l_productRow.getProductCode();
            l_response.productName = l_productRow.getStandardName();

            Market l_market;
            try
            {
                l_market =
                    l_finObjectManager.getMarket(
                        l_eqtypeContractRow.getMarketId());
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00003, STR_METHOD_NAME,
                    l_nfe.getMessage(), l_nfe);
            }
            l_response.marketCode = l_market.getMarketCode();
            if (TaxTypeEnum.NORMAL.equals(l_eqtypeContractRow.getTaxType()))
            {
                l_response.taxType = WEB3TaxTypeDef.NORMAL;
            }
            else if (TaxTypeEnum.SPECIAL.equals(l_eqtypeContractRow.getTaxType()) ||
                      TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_eqtypeContractRow.getTaxType()))
            {
                l_response.taxType = WEB3TaxTypeDef.SPECIAL;
            }

            l_response.contractType =
                Integer.toString(l_eqtypeContractRow.getContractType().intValue());
            WEB3MarginRepaymentUnit l_repaymentUnit = new WEB3MarginRepaymentUnit();
            l_repaymentUnit.repaymentDiv =
                l_eqtypeContractRow.getRepaymentType();
            l_repaymentUnit.repaymentTimeLimit =
                Integer.toString(l_eqtypeContractRow.getRepaymentNum());
            l_response.repayment = l_repaymentUnit;
            l_response.closingOrder = l_orderUnitRow.getClosingOrderType();
            l_response.closeMarginContractGroups = l_closeMaginGroup;
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create注文約定照会)<BR>
     * 注文約定照会画面／訂正取消一覧画面に表示する<BR>
     * 注文約定照会注文単位の一覧を作成し、レスポンスに設定する。<BR>
     * <BR>
     * 該当データが存在しない場合にはnullを返却する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（信用取引注文約定照会サービス）create注文約定照会」参照。<BR>
     * @@param L_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 信用取引注文約定照会リクエストオブジェクト<BR>
     * @@param l_response - (レスポンスデータ)<BR>
     * 信用取引注文約定照会レスポンスオブジェクト<BR>
     * @@return WEB3MarginExecuteGroup[]
     * @@roseuid 40FCA49A0193
     */
    protected WEB3MarginExecuteGroup[] createExecuteReference(
        WEB3GentradeSubAccount l_subAccount,
        WEB3MarginExecuteReferenceRequest l_request,
        WEB3MarginExecuteReferenceResponse l_response)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createExecuteReference(WEB3GentradeSubAccount, WEB3MarginExecuteReferenceRequest, "
            + "WEB3MarginExecuteReferenceResponse)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MarginExecuteGroup[] l_group = null;
        Long[] l_orderUnitIdList = null;
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
        
        try
        {
            String l_strSearchCondCharacter =
                createSearchCondCharacter(
                    l_request.productCode,
                    l_request.marketCode,
                    l_request.orderBizDate,
                    l_request.orderCondType);
            
            String[] l_searchCondDataContains =
                createSearchCondDataContainers(
                    l_request.productCode,
                    l_request.marketCode,
                    l_request.orderBizDate,
                    l_request.orderCondType);
            
            String l_strSortCond = createSortCond(l_request.sortKeys);
            
            List l_orderUnitsList =
                l_orderManager.getOrderUnits(
                    l_subAccount,
                    ProductTypeEnum.EQUITY,
                    l_strSearchCondCharacter,
                    l_searchCondDataContains,
                    l_strSortCond);
            
            //検索条件に約定状態が指定された場合
            List l_orderUnitTmpList;
            if (l_request.execType != null)
            {
                l_orderUnitTmpList = new ArrayList();
                int l_intSize = 0;
                if (l_orderUnitsList != null)
                {
                    l_intSize = l_orderUnitsList.size();
                }
                for (int i = 0;i < l_intSize;i++)
                {
                    EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnitsList.get(i);
                    boolean l_blnIsDesignateExecutedStatus =
                        this.isDesignateExecutedStatus(l_request.execType, l_orderUnit);
                    if (l_blnIsDesignateExecutedStatus)
                    {
                        l_orderUnitTmpList.add(l_orderUnit);
                    }
                }
            }
            else
            {
                l_orderUnitTmpList = l_orderUnitsList;
            }
            if (l_orderUnitTmpList == null || l_orderUnitTmpList.size() == 0)
            {
                return null;
            }
            
            //remove繰越元注文単位()
            EqTypeOrderUnit[] l_orderUnits = null;
            int l_intSize = l_orderUnitTmpList.size();
            l_orderUnits = new EqTypeOrderUnit[l_intSize];
            for (int i = 0;i < l_intSize;i++)
            {
                l_orderUnits[i] = (EqTypeOrderUnit)l_orderUnitTmpList.get(i);
            }
			EqTypeOrderUnit[] l_leftOrderUnits = l_orderManager.removeCarryOverOriginalOrderUnit(l_orderUnits);
            
            List l_orderUnitLastList = new ArrayList();
            List l_selectedOrderUnitList = new ArrayList();
            //表示対象注文単位の要素数分のLoop処理
            for (int i = 0;i < l_leftOrderUnits.length;i++)
            {
                EqTypeOrderUnit l_orderUnit = l_leftOrderUnits[i];
                EqtypeOrderUnitRow l_orderUnitRow =
                    (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
                WEB3GentradeMarket l_market =
                    (WEB3GentradeMarket)l_finObjectManager.getMarket(
                        l_orderUnitRow.getMarketId());
                WEB3GentradeTradingTimeManagement.resetMarketCode(
                    l_market.getMarketCode());
                WEB3EquityProduct l_product =
                    (WEB3EquityProduct)l_orderUnit.getProduct();
                boolean l_isChangeCancelEnableOrderUnit =
                    this.isChangeCancelEnableOrderUnit(
                        l_subAccount,
                        l_orderUnit,
                        l_product,
                        l_market);
                if (WEB3EquityReferenceTypeDef.REFERENCE_TYPE_UPDATE.equals(l_request.referenceType))
                {
                    if (l_isChangeCancelEnableOrderUnit == false)
                    {
                        continue;
                    }
                }
                Order l_order = l_orderManager.getOrder(l_orderUnit.getOrderId());
                //validate注文訂正可能状態()
                boolean l_isOrderChangePossibleStatus = true;
                WEB3EquityTradedProduct l_tradedProduct = null;
                try
                {
                    l_tradedProduct = (WEB3EquityTradedProduct) l_orderUnit.getTradedProduct();
                    l_orderManager.validateOrderForChangeability(l_order);
                }
                catch (WEB3BaseException l_wbe)
                {
                    l_isOrderChangePossibleStatus = false;
                }
                catch (RuntimeSystemException l_rse)
                {
                    l_isOrderChangePossibleStatus = false;
                }
                //訂正可能かどうかのチェック
                if (l_isOrderChangePossibleStatus)
                {
                    try
                    {
                        //validate取引銘柄（信用）()
                        boolean l_blnIsShort = true;
                        OrderCategEnum l_orderCateg = l_orderUnit.getOrderCateg();
                        SideEnum l_side = l_orderUnit.getSide();
                        if (OrderCategEnum.OPEN_MARGIN.equals(l_orderCateg))
                        {
                            if (SideEnum.BUY.equals(l_side))
                            {
                                l_blnIsShort = false;
                            }
                            else
                            {
                                l_blnIsShort = true;
                            }
                        }
                        else
                        {
                            if (SideEnum.BUY.equals(l_side))
                            {
                                l_blnIsShort = true;
                            }
                            else
                            {
                                l_blnIsShort = false;
                            }
                        }
                        l_orderManager.validateTradedProductForMarginTrading(
                            l_subAccount,
                            l_product,
                            l_market,
                            l_subAccount.getWeb3GenBranch(),
                            l_orderUnitRow.getRepaymentType(),
                            l_orderCateg,
                            l_blnIsShort,
                            false);
                        //validateインサイダー()
                        l_orderManager.validateInsider(l_subAccount, l_product);
                        //validate顧客銘柄別取引停止()
                        l_orderManager.validateAccountProductOrderStop(
                            l_subAccount,
                            l_orderUnitRow.getProductId(),
                            l_orderUnit.getOrderType());
                    }
                    catch (WEB3BaseException l_exp)
                    {
                        l_isOrderChangePossibleStatus = false;
                    }
                }
                //validate注文取消可能状態()
                boolean l_isOrderCancelPossibleStatus = true;
                try
                {
                    if (l_tradedProduct != null)
                    {
                        l_orderManager.validateOrderForCancellation(l_order);    
                    }
                    else
                    {
                        l_isOrderCancelPossibleStatus = false;
                    }
                }
                catch (WEB3BaseException l_exp)
                {
                    l_isOrderCancelPossibleStatus = false;
                }
                if (WEB3EquityReferenceTypeDef.REFERENCE_TYPE_UPDATE.equals(l_request.referenceType))
                {
                    if (!l_isOrderChangePossibleStatus &&
                        !l_isOrderCancelPossibleStatus)
                    {
                        continue;
                    }
                }

                WEB3MarginExecuteGroup l_marginExecuteGroup =
                    new WEB3MarginExecuteGroup();
                    
                //プロパティセット
                l_marginExecuteGroup.id = Long.toString(l_orderUnitRow.getOrderId());
                l_marginExecuteGroup.productCode = l_product.getProductCode();
                EqtypeProductRow l_productRow = (EqtypeProductRow) l_product.getDataSourceObject();
                l_marginExecuteGroup.productName = l_productRow.getStandardName();
                l_marginExecuteGroup.marketCode = l_market.getMarketCode();

                //訂正可能フラグ
                if (l_isChangeCancelEnableOrderUnit == false ||
                    l_isOrderChangePossibleStatus == false)
                {
                    l_marginExecuteGroup.changeFlag = false;
                }
                else
                {
                    l_marginExecuteGroup.changeFlag = true;                     
                }
                //取消可能フラグ
                if (l_isChangeCancelEnableOrderUnit == false || 
                    l_isOrderCancelPossibleStatus == false)
                {
                    l_marginExecuteGroup.cancelFlag = false;
                }
                else
                {
                    l_marginExecuteGroup.cancelFlag = true;
                }

                l_orderUnitLastList.add(l_marginExecuteGroup);
                l_selectedOrderUnitList.add(l_orderUnit);
            }

            if (l_orderUnitLastList.size() == 0)
            {
                return null;
            }
            l_group = new WEB3MarginExecuteGroup[l_orderUnitLastList.size()];
            l_orderUnitLastList.toArray(l_group);
            
            //レスポンスのページ関連項目設定
            //レスポンス.ID一覧：　@検索条件に該当する注文IDを全てセット
            // （要素数は、総レコード数に等しい）
            if (l_group != null)
            {
                int l_intOrderSize = l_group.length;
                l_response.idList = new String[l_intOrderSize];
                for (int i = 0;i < l_intOrderSize; i++)
                {
                    l_response.idList[i] = l_group[i].id;
                }
            }
            //レスポンス.総ページ数
            //レスポンス.総レコード数
            //レスポンス.表示ページ番号
            int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
            int l_intPageSize = Integer.parseInt(l_request.pageSize);
            WEB3PageIndexInfo l_pageIndexInfo =
                new WEB3PageIndexInfo(l_group, l_intPageIndex, l_intPageSize);
            l_response.executeGroups
                = (WEB3MarginExecuteGroup[])l_pageIndexInfo.getArrayReturned(WEB3MarginExecuteGroup.class);
            l_response.pageIndex = String.valueOf(l_pageIndexInfo.getPageIndex());
            l_response.totalPages = String.valueOf(l_pageIndexInfo.getTotalPages());
            l_response.totalRecords = String.valueOf(l_pageIndexInfo.getTotalRecords());

            WEB3PageIndexInfo l_ordersAtPage =
                new WEB3PageIndexInfo(l_selectedOrderUnitList, l_intPageIndex, l_intPageSize);
            Object[] l_objReturned = l_ordersAtPage.getArrayReturned(EqTypeOrderUnit.class);

            for (int i = 0; i < l_response.executeGroups.length; i++)
            {
                WEB3MarginExecuteGroup l_marginExecuteGroup = l_response.executeGroups[i];
                EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_objReturned[i];
                EqtypeOrderUnitRow l_orderUnitRow =
                    (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
                Market l_market = l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
                WEB3GentradeTradingTimeManagement.resetMarketCode(l_market.getMarketCode());

                // reset受付時間区分()
                // 受付時間区分：注文単位.注文カテゴリ=="現引現渡"の場合は、"現引・現渡"
                //              以外、"株式・信用"
                String l_tradingTimeType = null;
                if(OrderCategEnum.SWAP_MARGIN.equals(l_orderUnitRow.getOrderCateg()))
                {
                    l_tradingTimeType = WEB3TradingTimeTypeDef.SWAP;
                }
                else
                {
                    l_tradingTimeType = WEB3TradingTimeTypeDef.EQUITY;
                }
                WEB3GentradeTradingTimeManagement.resetTradingTimeType(l_tradingTimeType);

                //get執行条件（SONAR）(EqTypeExecutionConditionType)
                //執行条件：　@注文単位.(W指値)執行条件
                String l_strWlimitExecCondType = null;
                if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
                {
                    l_strWlimitExecCondType =
                        l_orderManager.getExecutionConditionTypeSonar(
                            l_orderUnitRow.getWLimitExecCondType());
                }

                //getＷ指値用有効状態区分(EqTypeOrderUnit)
                String l_strWlimitEnableStatusDiv =
                    WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_orderUnit);

                // getＷ指値用切替前注文単価(EqTypeOrderUnit)
                //注文単位：　@取得した注文単位オブジェクトの0番目の要素
                String l_strWlimitBefChgLimitPrice =
                    WEB3EquityDataAdapter.getWLimitBefSwitchPrice(l_orderUnit);

                // getＷ指値用切替前執行条件(EqTypeOrderUnit)
                //注文単位：　@取得した注文単位オブジェクトの0番目の要素
                String l_strWLimitBefSwitchExecCondType =
                    WEB3EquityDataAdapter.getWLimitBefSwitchExecCondType(l_orderUnit);

                //get元Ｗ指値用注文単価区分(EqTypeOrderUnit)
                //注文単位：　@取得した注文単位オブジェクトの0番目の要素
                String l_strOrgWlimitOrderPriceDiv =
                    WEB3EquityDataAdapter.getOrgWLimitOrderPriceDiv(l_orderUnit);

                //get元Ｗ指値用注文単価(EqTypeOrderUnit)
                //注文単位：　@取得した注文単位オブジェクトの0番目の要素
                String l_strOrgWlimitPrice =
                    WEB3EquityDataAdapter.getOrgWLimitOrderPrice(l_orderUnit);

                //get元Ｗ指値用執行条件(EqTypeOrderUnit)
                //注文単位：　@取得した注文単位オブジェクトの0番目の要素
                String l_strOrgWlimitExecCondType =
                    WEB3EquityDataAdapter.getOrgWLimitExecCondType(l_orderUnit);

                //get遅延区分(EqTypeOrderUnit)
                //注文単位：　@取得した注文単位オブジェクトの0番目の要素
                String l_strDelayDiv =
                    WEB3EquityDataAdapter.getDelayDiv(l_orderUnit);

                //is手動発注可能(EqTypeOrderUnit)
                //注文単位：　@取得した注文単位オブジェクトの0番目の要素
                boolean l_blnIsManualOrderPossible =
                    l_orderManager.isManualOrderPossible(l_orderUnit);

                if (TaxTypeEnum.NORMAL.equals(l_orderUnitRow.getTaxType()))
                {
                    l_marginExecuteGroup.taxType = WEB3TaxTypeDef.NORMAL;
                }
                if (TaxTypeEnum.SPECIAL.equals(l_orderUnitRow.getTaxType()) ||
                    TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_orderUnitRow.getTaxType()))
                {
                    l_marginExecuteGroup.taxType = WEB3TaxTypeDef.SPECIAL;
                }

                l_marginExecuteGroup.tradingType =
                    Integer.toString(l_orderUnitRow.getOrderType().intValue());

                WEB3MarginRepaymentUnit l_repaymentUnit = new WEB3MarginRepaymentUnit();
                l_repaymentUnit.repaymentDiv = l_orderUnitRow.getRepaymentType();
                l_repaymentUnit.repaymentTimeLimit = Integer.toString(l_orderUnitRow.getRepaymentNum());
                l_marginExecuteGroup.repayment = l_repaymentUnit;

                EqTypeClosingContractSpec[] l_contractSpec = null;
                if (OrderCategEnum.CLOSE_MARGIN.equals(l_orderUnit.getOrderCateg()))
                {
                    EqTypeContractSettleOrderUnit l_orderUnitClose =
                        (EqTypeContractSettleOrderUnit)l_orderUnit;
                    l_contractSpec = l_orderUnitClose.getContractsToClose();
                }
                if (OrderCategEnum.SWAP_MARGIN.equals(l_orderUnit.getOrderCateg()))
                {
                    EqTypeContractSwapOrderUnit l_orderSwap =
                        (EqTypeContractSwapOrderUnit)l_orderUnit;
                    l_contractSpec = l_orderSwap.getContractsToClose();
                }

                if (OrderCategEnum.OPEN_MARGIN.equals(l_orderUnitRow.getOrderCateg()))
                {
                    l_marginExecuteGroup.openDate = null;
                    l_marginExecuteGroup.contractPrice = null;
                }
                else
                {
                    WEB3EquityContract l_contract;
                    if (l_orderUnitRow.getClosingOrderType() == null)
                    {
                        if (l_contractSpec.length > 0)
                        {
                            l_contract =
                                (WEB3EquityContract)l_positionManager.getContract(l_contractSpec[0].getContractId());
                            l_marginExecuteGroup.openDate =
                                WEB3DateUtility.toDay(l_contract.getOpenDate());
                        }
                    }
                    double l_dblTotalContractAmount = 0.0D;
                    double l_dblTotalQuantity = 0.0D;
                    for (int j = 0;j < l_contractSpec.length;j++)
                    {
                        l_contract =
                            (WEB3EquityContract)l_positionManager.getContract(l_contractSpec[j].getContractId());
                        double l_dblQuantity = l_contractSpec[j].getQuantity();
                        l_dblTotalContractAmount += l_contract.getContractAmount(l_dblQuantity);
                        l_dblTotalQuantity += l_dblQuantity;
                    }
                    l_marginExecuteGroup.contractPrice =
                        WEB3StringTypeUtility.formatNumber(
                            Math.round(l_dblTotalContractAmount / l_dblTotalQuantity));                          
                }
                l_marginExecuteGroup.priceCondType = l_orderUnitRow.getPriceConditionType();
                l_marginExecuteGroup.execCondType = 
                    l_orderManager.getExecutionConditionTypeSonar(
                    l_orderUnitRow.getExecutionConditionType());
                l_marginExecuteGroup.orderCondType = l_orderUnitRow.getOrderConditionType();
                if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(
                	l_orderUnitRow.getOrderConditionType()))
                {
					l_marginExecuteGroup.stopOrderCondPrice = 
						WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
					l_marginExecuteGroup.stopOrderCondOperator = l_orderUnitRow.getOrderCondOperator();
                }
				else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(
					l_orderUnitRow.getOrderConditionType()))
				{
					l_marginExecuteGroup.wlimitOrderCondPrice = 
						WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
					l_marginExecuteGroup.wlimitOrderCondOperator = l_orderUnitRow.getOrderCondOperator();
					if (l_orderUnitRow.getWLimitPrice() == 0)
					{
						l_marginExecuteGroup.wLimitOrderPriceDiv = 
							WEB3EquityWlimitOrderPriceDivDef.WLIMIT_ORDER_PRICE_DIV_MARKET_PRICE;
					}
					else
					{
						l_marginExecuteGroup.wLimitOrderPriceDiv = 
							WEB3EquityWlimitOrderPriceDivDef.WLIMIT_ORDER_PRICE_DIV_LIMIT_PRICE;
						l_marginExecuteGroup.wLimitPrice = 
							WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getWLimitPrice());
					}
                    //Ｗ指値用執行条件
                    l_marginExecuteGroup.wlimitExecCondType = l_strWlimitExecCondType;
				}

                //Ｗ指値用有効状態区分
                l_marginExecuteGroup.wlimitEnableStatusDiv = l_strWlimitEnableStatusDiv;

                //Ｗ指値用切替前注文単価
                l_marginExecuteGroup.wlimitBefChgLimitPrice = l_strWlimitBefChgLimitPrice;

                //Ｗ指値用切替前執行条件
                l_marginExecuteGroup.wlimitBefChgExecCondType = l_strWLimitBefSwitchExecCondType;

                //元発注条件区分
                l_marginExecuteGroup.orgOrderCondType = l_orderUnitRow.getOrgOrderConditionType();
                
                //元発注条件単価
                if (l_orderUnitRow.getOrgStopOrderPriceIsNull())
                {
                    l_marginExecuteGroup.orgOrderCondPrice = null;
                }
                else
                {
                    l_marginExecuteGroup.orgOrderCondPrice = 
                        WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getOrgStopOrderPrice());
                }

                //元発注条件演算子
                l_marginExecuteGroup.orgOrderCondOperator = l_orderUnitRow.getOrgOrderCondOperator();

                //元Ｗ指値用注文単価区分
                l_marginExecuteGroup.orgWlimitOrderPriceDiv = l_strOrgWlimitOrderPriceDiv;

                if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrgWlimitOrderPriceDiv))
                {
                    //元Ｗ指値用注文単価
                    l_marginExecuteGroup.orgWlimitPrice = l_strOrgWlimitPrice;
                }

                //元Ｗ指値用執行条件
                l_marginExecuteGroup.orgWlimitExecCondType = l_strOrgWlimitExecCondType;

                l_marginExecuteGroup.orderQuantity =
                    WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getQuantity());
                if (l_orderUnit.isMarketOrder())
                {
                    l_marginExecuteGroup.orderPriceDiv =
                        WEB3OrderPriceDivDef.MARKET_PRICE;
                }
                else
                {
                    l_marginExecuteGroup.orderPriceDiv =
                        WEB3OrderPriceDivDef.LIMIT_PRICE;
                    l_marginExecuteGroup.limitPrice =
                        WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getLimitPrice());
                }

                if (l_orderUnit.isUnexecuted() == false)
                {
                    l_marginExecuteGroup.execQuantity =
                        WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getExecutedQuantity());
                    l_marginExecuteGroup.execPrice =
                        WEB3StringTypeUtility.formatNumber(
                            Math.round(l_orderUnitRow.getExecutedAmount() / l_orderUnitRow.getExecutedQuantity()));
                    OrderExecution[] l_orderExecutions = l_orderUnit.getExecutions();
                    int l_intExeLength = l_orderExecutions.length;
                    l_marginExecuteGroup.executeUnits = new WEB3MarginExecuteUnit[l_intExeLength];
                    for (int j = 0;j < l_intExeLength;j++)
                    {
                        WEB3MarginExecuteUnit l_executeUnit = new WEB3MarginExecuteUnit();
                        l_executeUnit.executionTimestamp = l_orderExecutions[j].getExecutionTimestamp();
                        l_executeUnit.execQuantity =
                            WEB3StringTypeUtility.formatNumber(l_orderExecutions[j].getExecutionQuantity());
                        l_executeUnit.execPrice =
                            WEB3StringTypeUtility.formatNumber(l_orderExecutions[j].getExecutionPrice());                             
                        l_marginExecuteGroup.executeUnits[j] = l_executeUnit;
                    }
                    double l_dblDeliveryAmt = l_orderManager.getEstimateDeliveryAmountForClose(l_orderUnit);
                    l_marginExecuteGroup.deliveryPrice =
                        WEB3StringTypeUtility.formatNumber(l_dblDeliveryAmt);
                }
                l_marginExecuteGroup.estimatedPrice =
                    WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getEstimatedPrice());
                l_marginExecuteGroup.orderDate =
                    l_orderUnitRow.getCreatedTimestamp();
                l_marginExecuteGroup.orderBizDate =
                    WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), "yyyyMMdd");
                boolean l_isCarriedOrderUnit =
                    l_orderManager.isCarriedOrderUnit(l_orderUnit);
                if (l_isCarriedOrderUnit)
                {
                    l_marginExecuteGroup.expirationDate =
                        WEB3DateUtility.toDay(l_orderUnitRow.getExpirationDate());
                }
                l_marginExecuteGroup.transactionStateType =
					WEB3EquityDataAdapter.getTransactionStatusType(l_orderUnit);
                    
                l_marginExecuteGroup.orderChannel = l_orderUnitRow.getOrderChanel();
                l_marginExecuteGroup.orderRootDiv = l_orderUnitRow.getOrderRootDiv();
                if (l_orderUnitRow.getTraderIdIsNull() == false)
                {
                    Trader l_trader;
                    if ((l_trader = l_finObjectManager.getTrader(l_orderUnitRow.getTraderId())) != null)
                    {
                        l_marginExecuteGroup.operatorCode = l_trader.getTraderCode();
                    }
                }

                //遅延区分
                l_marginExecuteGroup.delayDiv = l_strDelayDiv;
                //手動発注可能フラグ
                l_marginExecuteGroup.manualFlag = l_blnIsManualOrderPossible;
                //強制決済理由
                l_marginExecuteGroup.forcedSettleReason = l_orderUnitRow.getForcedSettleReasonType();
                //強制失効区分
                l_marginExecuteGroup.forcedLapseDiv = l_orderUnitRow.getForcedExpireType();
            }
        }
        catch (NotFoundException l_nfe)
        {
            log.error(getClass().getName() + "." + STR_METHOD_NAME,l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        return l_group;
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
     * (3)注文カテゴリ指定を追加<BR>
     * <BR>
     * 　@　@　@" and order_categ IN (?, ?, ?)"<BR>
     * <BR>
     * (4)パラメータ.銘柄コード≠NULL（銘柄コード指定）の場合、銘柄ID指定を追加<BR>
     * 　@　@（銘柄コードに対応する銘柄IDで検索を行う)<BR>
     * <BR>
     * 　@　@　@" and product_id = ?"<BR>
     * <BR>
     * (5)パラメータ.市場コード≠NULL（市場コード指定）の場合、市場ID指定を追加<BR>
     * 　@　@（市場コードに対応する市場IDで検索を行う)<BR>
     * <BR>
     * 　@　@　@" and market_id = ?"<BR>
     * <BR>
     * (6)パラメータ.発注条件区分≠NULL（発注条件区分指定）の場合、発注条件区分指定を追加<BR>
     *　@（株式注文単位テーブル.元発注条件に値が設定されている場合は、<BR>
     * 　@元発注条件をもとに検索する。<BR>
     * 　@元発注条件に値が設定されていない場合は、<BR>
     * 　@株式注文単位テーブル.発注条件をもとに検索する。) <BR>
     * <BR>
     * 　@" and nvl(org_order_condition_type,order_condition_type) = ?" <BR>
     * <BR>
     * (7)文字列インスタンスを返却<BR>
     * <BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * @@param l_datOrderBizDate - (発注日)<BR>
     * @@param l_strOrderConditionDiv - (発注条件区分)<BR>
     * @@return String<BR>
     * @@roseuid 40FCA49A0196
     */
    protected String createSearchCondCharacter(
        String l_strProductCode,
        String l_strMarketCode,
        Date l_datOrderBizDate,
        String l_strOrderConditionDiv)
    {
        final String STR_METHOD_NAME =
            "createSearchCondCharacter(String, String, Date, String)";
        log.entering(STR_METHOD_NAME);
        
        StringBuffer l_sbSearchCond = new StringBuffer();

        if (l_datOrderBizDate != null)
        {
            l_sbSearchCond.append("biz_date = ?");
        }
        else
        {
            l_sbSearchCond.append("biz_date >= ?");
        }
        
        l_sbSearchCond.append(" and order_categ in (?, ?, ?)");

        if (l_strProductCode != null)
        {
            l_sbSearchCond.append(" and product_id = ?");
        }

        if (l_strMarketCode != null)
        {

            l_sbSearchCond.append(" and market_id = ?");
        }
        
        // パラメータ.発注条件区分≠NULL（発注条件区分指定）の場合
        if (l_strOrderConditionDiv != null)
        {
            l_sbSearchCond.append(" and nvl(org_order_condition_type,order_condition_type) = ?");
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_sbSearchCond.toString();
    }

    /**
     * (create検索条件データコンテナ)<BR>
     * 索条件（where以下指定の文字列）のパラメータの文字列配列を作成する。<BR>
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
     * (3)以下の注文カテゴリを文字列配列にセット<BR>
     * 　@　@・”新規建注文”<BR>
     * 　@　@・”返済注文”<BR>
     * 　@　@・”現引現渡注文”<BR>
     * 　@　@(OrderCategEnumにて定義)<BR>
     * <BR>
     * (4)パラメータ.銘柄コード≠NULL（銘柄コード指定）の場合、<BR>
     * 　@　@銘柄IDを文字列配列にセット（銘柄コードに対応する銘柄IDで検索を行う)<BR>
     * <BR>
     * 　@　@　@銘柄ID ＝ 拡張プロダクトマネージャ.get銘柄(証券会社オブジェクト(*2), パラメータ.銘柄コード).銘柄ID<BR>
     * <BR>
     * (5)パラメータ.市場コード≠NULL（市場コード指定）の場合、市場IDを文字列配列にセット<BR>
     * 　@　@（市場コードに対応する市場IDで検索を行う)<BR>
     * <BR>
     * 　@　@　@市場ID ＝ 金融オブジェクトマネージャ.get市場(証券会社オブジェクト.証券会社コード, パラメータ.市場コード).市場ID<BR>
     * <BR>
     * (6)パラメータ.発注条件区分≠NULL（発注条件区分指定）の場合、<BR>
     * パラメータ.発注条件区分を文字列配列にセット <BR>
     * <BR>
     * 　@発注条件区分 ＝ パラメータ.発注条件区分<BR>
     * <BR>
     * (7)(2)、(3)、(4)、(5)、(6)にてパラメータをセットした文字列配列を返却<BR>
     * <BR>
     * (*1)GtlUtils.getTradingSystem( ).getBizDate( ) <BR>
     * (*2)証券会社オブジェクトは、補助口座.getInstitution( )で取得し設定<BR>
     * <BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * @@param l_datOrderBizDate - (発注日)<BR>
     * @@param l_strOrderConditionDiv - (発注条件区分)<BR>
     * @@return String[]<BR>
     * @@roseuid 40FCA49A01A2
     */
    protected String[] createSearchCondDataContainers(
        String l_strProductCode,
        String l_strMarketCode,
        Date l_datOrderBizDate,
        String l_strOrderConditionDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createSearchCondDataContainers(String ,String ,Date, String)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        List l_lisParams = new ArrayList();

        if (l_datOrderBizDate != null)
        {
            l_lisParams.add(GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(l_datOrderBizDate));
        }
        else
        {
            Timestamp l_tsbizDate = new Timestamp(GtlUtils.getTradingSystem().getBizDate().getTime());
            l_lisParams.add(GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(l_tsbizDate));
        }
        l_lisParams.add(Integer.toString(OrderCategEnum.OPEN_MARGIN.intValue()));
        l_lisParams.add(Integer.toString(OrderCategEnum.CLOSE_MARGIN.intValue()));
        l_lisParams.add(Integer.toString(OrderCategEnum.SWAP_MARGIN.intValue()));

        try
        {
            // (4)（銘柄コード指定）の場合
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
            
            // (5)（市場コード指定）の場合、市場IDを文字列配列にセット
            if (l_strMarketCode != null)
            {
                Market l_market =
                    (WEB3GentradeMarket)l_finObjectManager.getMarket(
                        l_subAccount.getInstitution(),
                        l_strMarketCode);
                l_lisParams.add(Long.toString(l_market.getMarketId()));
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
        
        // 6)パラメータ.発注条件区分≠NULL（発注条件区分指定）の場合
        if (l_strOrderConditionDiv != null)
        {
            // パラメータ.発注条件区分を文字列配列にセット 
            l_lisParams.add(l_strOrderConditionDiv);
        }
        
        String[] l_strParams = new String[l_lisParams.size()];
        l_lisParams.toArray(l_strParams);
        log.exiting(STR_METHOD_NAME);
        return l_strParams;
    }

    /**
     * (createソート条件)<BR>
     * ソート条件文字列を作成し返す。<BR>
     * <BR>
     * (1)パラメータ.信用取引ソートキー ＝ nullの場合は、<BR>
     * 　@"銘柄ID 昇順"のソート条件文字列を返却する。<BR>
     * <BR>
     * (2)パラメータ.信用取引ソートキー.キー項目の数分、対応するテーブルの列物理名を<BR>
     * 　@　@昇順／降順指定を付加しセット<BR>
     * <BR>
     * 　@　@・キー項目とテーブルの列名称との対応は以下の通り<BR>
     * 　@　@　@※テーブル名：注文単位テーブル(eqtype_order_unit)<BR>
     * 　@　@　@※キー項目文字列（シンボル名）は、メッセージ定義書を参照<BR>
     * 　@　@　@※テーブルの列物理名は、テーブルレイアウトを参照<BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@変換前　@　@　@　@　@　@　@　@　@変換後<BR>
     *              -------------　@　@　@　@-----------------------------<BR>
     * 　@　@　@　@　@　@　@　@・銘柄コード  　@　@　@　@　@：注文単位テーブル．銘柄ID<BR>
     * 　@　@　@　@　@　@　@　@・口座区分 　@　@　@　@　@　@：注文単位テーブル．税区分<BR>
     *                ・市場コード  　@　@　@　@　@：注文単位テーブル．市場ID<BR>
     * 　@　@　@　@　@　@　@　@・取引区分 　@　@　@　@　@　@：注文単位テーブル．注文種別<BR>
     * 　@　@　@　@　@　@　@　@・値段条件 　@　@　@　@　@　@：注文単位テーブル．値段条件<BR>
     * 　@　@　@　@　@　@　@　@・執行条件 　@　@　@　@　@　@：注文単位テーブル．執行条件<BR>
     * 　@　@　@　@　@　@　@　@・発注条件 　@　@　@　@　@　@：注文単位テーブル．発注条件<BR>
     * 　@　@　@　@　@　@　@　@・注文時間 　@　@　@　@　@　@：注文単位テーブル．作成日付<BR>
     * 　@　@　@　@　@　@　@　@・発注日    　@　@　@　@　@　@：注文単位テーブル．発注日<BR>
     * 　@　@　@　@　@　@　@　@・注文期限 　@　@　@　@　@　@：注文単位テーブル．注文失効日付<BR>
     * 　@　@　@　@　@　@　@　@・弁済区分 　@　@　@　@　@　@：注文単位テーブル．弁済区分<BR>
     * 　@　@　@　@　@　@　@　@・弁済期限 　@　@　@　@　@　@：注文単位テーブル．弁済期限<BR>
     * <BR>
     * 　@　@・昇順／降順指定は、信用取引ソートキー.昇順／降順 指定に従い設定<BR>
     * <BR>
     * (3)ソート条件末尾に、注文単位テーブル.更新日付を昇順指定で付加<BR>
     * <BR>
     * (4)作成したソート条件文字列を返却<BR>
     * <BR>
     * @@param l_marginSortKey - 信用取引ソートキー<BR>
     * リクエストデータ.信用取引ソートキー<BR>
     * @@return String
     * @@roseuid 40FCA49A01F0
     */
    protected String createSortCond(WEB3MarginSortKey[] l_marginSortKey)
    {
        final String STR_METHOD_NAME = "createSortCond(WEB3MarginSortKey[])";
        log.entering(STR_METHOD_NAME);
        
        if (l_marginSortKey == null)
        {
            return "product_id ASC";
        }
        
        StringBuffer l_sbReturn = new StringBuffer();
        for (int i = 0;i < l_marginSortKey.length;i++)
        {      
            if (l_marginSortKey[i].keyItem.equals(WEB3EquityKeyItemDef.PRODUCTCODE))
            {
                l_sbReturn.append("eqtype_order_unit.product_id");
            }                
            else if (l_marginSortKey[i].keyItem.equals(WEB3EquityKeyItemDef.ACCOUNTTYPE))
            {
                l_sbReturn.append("eqtype_order_unit.tax_type");
            }                       
            else if (l_marginSortKey[i].keyItem.equals(WEB3EquityKeyItemDef.TRADEMARKET))
            {
                l_sbReturn.append("eqtype_order_unit.market_id");
            }                                    
            else if (l_marginSortKey[i].keyItem.equals(WEB3EquityKeyItemDef.TRADETYPE))
            {
                l_sbReturn.append("eqtype_order_unit.order_type");
            }                                             
            else if (l_marginSortKey[i].keyItem.equals(WEB3EquityKeyItemDef.PRICE_COND))
            {
                l_sbReturn.append("eqtype_order_unit.price_condition_type");
            }
            else if (l_marginSortKey[i].keyItem.equals(WEB3EquityKeyItemDef.EXECUTE_COND))
            {
                l_sbReturn.append("eqtype_order_unit.execution_condition_type");
            }
            else if (l_marginSortKey[i].keyItem.equals(WEB3EquityKeyItemDef.SEND_COND))
            {
                l_sbReturn.append("eqtype_order_unit.order_condition_type");
            }
            else if (l_marginSortKey[i].keyItem.equals(WEB3EquityKeyItemDef.ORDER_TIME))
            {
                l_sbReturn.append("eqtype_order_unit.created_timestamp");
            }
            else if (l_marginSortKey[i].keyItem.equals(WEB3EquityKeyItemDef.SEND_DATE))
            {
                l_sbReturn.append("eqtype_order_unit.biz_date");
            }
            else if (l_marginSortKey[i].keyItem.equals(WEB3EquityKeyItemDef.ORDER_TIMELIMIT))
            {
                l_sbReturn.append("eqtype_order_unit.expiration_date");
            }
            else if (l_marginSortKey[i].keyItem.equals(WEB3EquityKeyItemDef.REPAYMENT_DIV))
            {
                l_sbReturn.append("eqtype_order_unit.repayment_type");
            }
            else if (l_marginSortKey[i].keyItem.equals(WEB3EquityKeyItemDef.REPAYMENTNUM))
            {
                l_sbReturn.append("eqtype_order_unit.repayment_num");
            }
            else
            {
                continue;
            }
            if (WEB3AscDescDef.ASC.equals(l_marginSortKey[i].ascDesc))
            {
                l_sbReturn.append("　@ASC , ");
            }
            else
            {
                l_sbReturn.append("　@DESC , ");
            }
        }
        
        l_sbReturn.append("eqtype_order_unit.last_updated_timestamp ASC");
        log.debug("ソート条件 = " + l_sbReturn.toString());
        
        log.exiting(STR_METHOD_NAME);
        return l_sbReturn.toString();
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
     * @@return EqTypeOrderUnit[]
     * @@roseuid 40FCA49A023F
     */
    protected EqTypeOrderUnit[] getCarriedOrderUnitFromFirstToLast(
        EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCarriedOrderUnitFromFirstToLast(EqTypeOrderUnit)";
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
     * (is訂正取消可能注文単位)<BR>
     * 指定された注文単位オブジェクトが、訂正取消可能であるかどうか判定する。<BR>
     * 訂正取消可能の場合はtrueを、以外はfalseを返す。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(信用取引注文約定照会サービス)is訂正取消可能注文単位」参照。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * @@param l_eqtypeProduct - (株式銘柄)<BR>
     * @@param l_market - (市場)<BR>
     * @@return boolean
     * @@roseuid 40FCF9D00200
     */
    protected boolean isChangeCancelEnableOrderUnit(
        WEB3GentradeSubAccount l_subAccount,
        EqTypeOrderUnit l_orderUnit,
        WEB3EquityProduct l_eqtypeProduct,
        WEB3GentradeMarket l_market)
    {
        final String STR_METHOD_NAME =
            "isChangeCancelEnableOrderUnit(WEB3GentradeSubAccount, EqTypeOrderUnit, WEB3EquityProduct, WEB3GentradeMarket)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
        
        if (!l_branch.isMarginTradeEnforcement(l_orderUnitRow.getRepaymentType()))
        {
            return false;
        }
        
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        
        if (!l_mainAccount.isMarginAccountEstablished(l_orderUnitRow.getRepaymentType()))
        {
            return false;
        }
        
        WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.CANCEL);
        try
        {
            OrderCategEnum l_orderCateg = l_orderUnit.getOrderCateg();
            if (OrderCategEnum.OPEN_MARGIN.equals(l_orderCateg) ||
                OrderCategEnum.CLOSE_MARGIN.equals(l_orderCateg))
            {
                WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
                WEB3GentradeTradingTimeManagement.validateOrderAccept();
            }
            else if (OrderCategEnum.SWAP_MARGIN.equals(l_orderCateg))
            {
                WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.SWAP);
                WEB3GentradeTradingTimeManagement.validateOrderAccept();
            }
            
            //validate取引銘柄()
            WEB3EquityProduct l_product = (WEB3EquityProduct)l_orderUnit.getProduct();
            WEB3EquityTradedProduct l_tradedProduct =
                (WEB3EquityTradedProduct)l_orderManager.validateTradedProduct(
                    l_product,
                    l_market);

            //validate取扱可能市場（信用）()
            l_orderManager.validateHandlingMarket(
                l_branch,
                l_tradedProduct,
                l_market.getMarketCode(),
                l_orderUnitRow.getRepaymentType(),
                l_orderUnitRow.getRepaymentNum());

            WEB3GentradeOrderValidator l_orderValidator =
                (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
            
            //validate取引可能顧客()
            OrderValidationResult l_validationResult =
                l_orderValidator.validateSubAccountForTrading(l_subAccount);
            if (l_validationResult.getProcessingResult().isFailedResult())
            {
                return false;
            }
            
			//注文単位の発注日が現在日時より算出した発注日より前の場合
			Date l_bizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            
			Date l_orderBizDate = 
				WEB3DateUtility.getDate(
					l_orderUnitRow.getBizDate(), 
					WEB3GentradeTimeDef.DATE_FORMAT_YMD);
                    
			if (WEB3DateUtility.compare(l_bizDate, l_orderBizDate) > 0)
			{
				//validate閉局後訂正取消受付可能()
				WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
					ProductTypeEnum.EQUITY);
			}
        }
        catch (WEB3BaseException l_exp)
        {
            return false;
        }

        log.exiting(STR_METHOD_NAME);
        return true;
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
     * @@param l_strExecType - (約定状態区分)<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * @@return boolean
     * @@roseuid 40FCA49A0221
     */
    protected boolean isDesignateExecutedStatus(
        String l_strExecType,
        EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isDesignateExecutedStatus(String, EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        boolean l_result = false;

        if (l_strExecType.equals(WEB3EquityDataAdapter.getExecType(l_orderUnit)))
        {
            l_result = true;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_result;
    }
}
@
