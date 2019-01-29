head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionSettleContractOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP返済注文サービスImpl(WEB3OptionSettleContractOrderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/24 張宝楠 (中訊) 新規作成
              001: 2004/07/22 王暁傑 (中訊) WEB3OrderPriceDivDefでWEB3IfoOrderPriceDivDefを差し替える
              002: 2004/07/22 王暁傑 (中訊) WEB3ExecutionConditionDefでWEB3IfoExecCondTypeDefを差し替える
              003: 2004/07/22 王暁傑 (中訊) WEB3OrderingConditionDefでWEB3OrderingConditionDefを差し替える
              004: 2004/08/09 王暁傑 (Sinocom) 対応名称:【WEB3-XBIFO-A-CD-0082】
              005: 2004/08/13 呉艶飛　@(中訊) STBUG(IFO_ST-000079)を対応
              006: 2004/08/13 呉艶飛　@(中訊) STBUG(IFO_ST-000114)を対応
              007: 2004/08/15 呉艶飛　@(中訊) STBUG(IFO_ST-000083)を対応
              008: 2006/07/13 魏　@(中訊) 仕様変更 モデル NO.460を対応
Revesion History : 2007/06/08 孫洪江 (中訊) 仕様変更モデルNo.655
Revesion History : 2007/06/21 孟亜南 (中訊) 仕様変更モデルNo.739
Revesion History : 2007/11/20 何文敏 (中訊) 仕様変更モデルNo.806,815,819
Revesion History : 2008/04/10 張騰宇　@(中訊) モデル 849 873 876 878 880
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PayTypeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoSettleContractUpdateInterceptor;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionClientRequestService;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.WEB3IfoProductQuote;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.message.WEB3OptionsCloseMarginCompleteRequest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginCompleteResponse;
import webbroker3.ifo.message.WEB3OptionsCloseMarginConfirmRequest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginConfirmResponse;
import webbroker3.ifo.service.delegate.WEB3OptionSettleContractOrderRequestAdapter;
import webbroker3.ifo.service.delegate.WEB3OptionSettleContractOrderService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (OP返済注文サービスImpl)<BR>
 * <BR>
 * 株価指数オプション返済注文サービス実装クラス<BR>
 *
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3OptionSettleContractOrderServiceImpl extends WEB3OptionClientRequestService implements WEB3OptionSettleContractOrderService
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionSettleContractOrderServiceImpl.class);

    /**
     * 株価指数オプション返済注文サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型により、validate注文()または、submit注文()メソッドをコールする。<BR>
     * @@param l_inRequest - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40551369036A
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3OptionsCloseMarginConfirmRequest)  //validate注文
        {
            l_response = validateOrder((WEB3OptionsCloseMarginConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3OptionsCloseMarginCompleteRequest)    //submit注文
        {
            l_response = submitOrder((WEB3OptionsCloseMarginCompleteRequest)l_request);
        }
        else
        {
            log.error(STR_METHOD_NAME + "パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (validate注文)<BR>
     * <BR>
     * 株価指数オプションの返済発注審査を行う。<BR>
     * <BR>
     * 「（OP返済サービス）validate注文」参照。<BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.ifo.message.WEB3OptionsCloseMarginConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40551384003D
     */
    protected WEB3OptionsCloseMarginConfirmResponse validateOrder(WEB3OptionsCloseMarginConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateOrder(WEB3OptionsCloseMarginConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //リクエストデータの整合性をチェックする。
        l_request.validate();

        WEB3OptionsCloseMarginConfirmResponse l_response = (WEB3OptionsCloseMarginConfirmResponse)l_request.createResponse();

        //補助口座を取得する。
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)this.getSubAccount();
        log.debug(l_subAccount.getInstitution().getInstitutionCode());
        //代理入力者の扱者オブジェクトを取得する。
        Trader l_trader = this.getTrader();

        //createリクエストアダプタ(WEB3GenRequest)
        WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter = this.createRequestAdapter(l_request);

        //create返済建玉エントリ(OP返済注文リクエストアダプタ, 返済建玉[])
        SettleContractEntry[] l_settleContractOrderEntries =
            this.createSettleContractEntry(l_requestAdapter, l_request.closeMarginContractUnits);

        //get建玉( )
        WEB3IfoContractImpl l_contractImpl = l_requestAdapter.getContract();

        //getProduct( )
        IfoProduct l_product = (IfoProduct)l_contractImpl.getProduct();

        //reset銘柄コード(銘柄コード : String)
        WEB3GentradeTradingTimeManagement.resetProductCode(l_product.getUnderlyingProductCode());

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3OptionOrderManagerImpl l_orderMgr = (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

        //先物OP取引銘柄オブジェクトを取得する。
        TradedProduct l_tradecProduct = l_contractImpl.getTradedProduct();

        //執行条件: 先物OPデータアダプタ.get執行条件(リクエストデータ.執行条件)
        IfoOrderExecutionConditionType l_execCondType = WEB3IfoDataAdapter.getExecutionConditionType(l_request.execCondType);
        //set limitPrice
        double l_dblLimitPrice = l_requestAdapter.getPrice();
        double l_dblwLimitPrice = 0D;
        
        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_request.wLimitOrderPriceDiv) 
            && WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_dblwLimitPrice = Double.parseDouble(l_request.wLimitPrice);
        }

        //初回注文の注文単位ID：　@
        //先物OPデータアダプタ.get初回注文の注文単位ID(リクエストデータ.注文期限区分)

        Long l_lngFirstOrderUnitId = WEB3IfoDataAdapter.getFirstOrderUnitId(l_request.expirationDateType);
        // 注文失効日：　@OP注文マネージャ.get注文有効期限(リクエストデータ.注文有効期限,
        // 先物OP銘柄.銘柄コード,市場.getMarketCode(),”オプション”)の戻り値
        // 銘柄オブジェクトを取得する。
        WEB3IfoProductImpl l_ifoProductImpl = (WEB3IfoProductImpl)l_contractImpl.getProduct();
        String l_strProductCode = l_ifoProductImpl.getProductCode();
        Market l_market = l_ifoProductImpl.getPrimaryMarket();
        String l_strMarketCode = l_market.getMarketCode();
        Date l_datExpirationDate = l_orderMgr.getExpirationDate(
            l_request.expirationDate,
            l_strProductCode,
            l_strMarketCode,
            WEB3FuturesOptionDivDef.OPTION);

        //返済注文内容オブジェクトを生成する。
        double l_dblStopOrderPrice = 0D;
        //リクエストデータ.発注条件区分 == ”逆指値”の場合
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_dblStopOrderPrice = Double.parseDouble(l_request.stopOrderCondPrice);
        }
        //リクエストデータ.発注条件区分 == ”W指値”の場合
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_dblStopOrderPrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
        }
        // 1.6 create返済注文内容
        IfoOrderExecutionConditionType l_wLimitExecCondType = WEB3IfoDataAdapter.getExecutionConditionType(l_request.wlimitExecCondType);

        //夕場前繰越対象フラグ：先物OPデータアダプタ.get夕場前繰越対象フラグ(
        //      リクエストデータ.注文期限区分,
        //      補助口座に該当する部店.部店ID)
        boolean l_blnEveningSessionCarryoverFlag = WEB3IfoDataAdapter.getEveningSessionCarryOverFlag(
            l_request.expirationDateType,
            l_subAccount.getWeb3GenBranch().getBranchId());

        WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec =
            WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
                l_subAccount.getInstitution().getInstitutionCode(), //証券会社コード
                l_trader,                                           //扱者
                l_dblLimitPrice,                                    //指値
                l_execCondType,                                     //執行条件
                l_datExpirationDate,                                //注文失効日
                l_settleContractOrderEntries,                       //返済建玉エントリ
                l_request.orderCondType,                            //発注条件
                l_dblStopOrderPrice,                                //逆指値基準値
                l_dblwLimitPrice,                                   //W指値用注文単価
                l_wLimitExecCondType,                               //(W指値)執行条件
                l_request.expirationDateType,                       //注文期限区分
                l_lngFirstOrderUnitId,                              //初回注文の注文単位ID
                l_blnEveningSessionCarryoverFlag);                  //夕場前繰越対象フラグ

        //先物OP返済注文の発注審査を実施する。
        NewOrderValidationResult l_result = null;
        l_result = this.validateOptionsSettleContractOrder(
            l_subAccount, l_settleContractOrderSpec, l_requestAdapter);

        if (l_result.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_result.getProcessingResult());
            throw new WEB3BusinessLayerException(
                l_result.getProcessingResult().getErrorInfo(),
                STR_METHOD_NAME);
        }

        //建玉の合計数量を取得する。
        double l_dblTotalQuantity = l_settleContractOrderSpec.getTotalQuantity();
        if (Double.isNaN(l_dblTotalQuantity))
        {
            l_dblTotalQuantity = 0D;
        }

        //t発注日
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        Timestamp l_tsOrderBizDate = new Timestamp(l_datOrderBizDate.getTime());


        //手数料オブジェクトを生成する。
        WEB3GentradeCommission l_commission = new WEB3GentradeCommission();

        //手数料.注文チャネル = this.getログインチャネル()
        l_commission.setOrderChannel(this.getLoginChannel());

        //手数料.証券会社ID = 補助口座.get証券会社()
        l_commission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());

        //手数料.部店ID = 補助口座.get取引店()
        l_commission.setBranchId(l_subAccount.getWeb3GenBranch().getBranchId());

        //手数料.発注日 = 取引時間管理.get発注日()
        l_commission.setOrderBizDate(l_tsOrderBizDate);

        //手数料.取引コード(SONAR) = ”52：返済”
        l_commission.setSonarTradedCode(WEB3TransactionTypeSONARDef.SETTLE_CONTRACT);

        //手数料.手数料商品コード = ”51：株価指数OP”
        l_commission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_OP);

        //弁済区分をセットする。
        l_commission.setPayType(WEB3PayTypeDef.OTHER);
        //手数料.is指値をセットする。
        l_commission.setIsLimitPrice(l_settleContractOrderSpec.isLimitOrder());

        //手数料.原資産銘柄コード = 建玉.get銘柄().get原資産銘柄コード(*2)
        l_commission.setUnderlyingProductCode(
            ((WEB3IfoProductImpl)l_contractImpl.getProduct()).getUnderlyingProductCode());

        //手数料.日計り区分 = this.get日計り区分()の戻り値
        l_commission.setDayTradeType(this.getDayTradeType(l_settleContractOrderEntries, l_requestAdapter));

        //手数料.数量 = 返済注文内容.getTotalQuantity()
        l_commission.setQuantity(l_dblTotalQuantity);

        //売買
        SideEnum l_sellToBuy = null;
        if (l_contractImpl.isLong())
        {
            l_sellToBuy = SideEnum.SELL;
        }
        else
        {
            l_sellToBuy = SideEnum.BUY;
        }

        //概算受渡代金を計算する。

        WEB3IfoEstimateDeliveryAmountCalcResult l_EstimateDeliveryAmount =
            l_orderMgr.calcEstimateDeliveryAmount(
                l_commission,                               //手数料
                l_dblLimitPrice,   //指値
                (WEB3GentradeSubAccount)l_subAccount,       //補助口座
                (WEB3IfoTradedProductImpl)l_tradecProduct,  //先物OP取引銘柄
                l_dblTotalQuantity,                         //数量
                l_sellToBuy,                                //売買
                true,                                       //is返済注文
                false                                       //isSkip金額チェック
                );

        //[引数]
        //返済建玉エントリ：　@create返済建玉エントリ()の戻り値
        //リクエストアダプタ：　@createリクエストアダプタ()の戻り値
        //先物OP取引銘柄：　@先物OP取引銘柄オブジェクト
        WEB3FuturesOptionsContractUnit[] l_contractUnits = this.createContractUnit(
            l_settleContractOrderEntries, l_requestAdapter, (WEB3IfoTradedProductImpl)l_tradecProduct);

        //新規注文番号を採番する。
        long l_lngOrderId = l_orderMgr.createNewOrderId();

        //set単価(OP返済注文リクエストアダプタ, WEB3GenResponse)
        this.setPrice(l_requestAdapter, l_response);

        //レスポンス.注文ID = OP注文マネージャ.createNewOrderId()の戻り値 
        l_response.orderId = "" + l_lngOrderId;
        //レスポンス.建玉明細 = （*上記で編集した建玉明細オブジェクトの配列）
        l_response.contractUnits = l_contractUnits;

        //レスポンス.概算受渡代金 = 概算受渡代金計算結果.概算受渡代金
        double l_dblEstimateDeliveryAmount = l_EstimateDeliveryAmount.getEstimateDeliveryAmount();
        if (Double.isNaN(l_dblEstimateDeliveryAmount))
        {
            l_dblEstimateDeliveryAmount = 0D;
        }
        l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_dblEstimateDeliveryAmount);

        //レスポンス.手数料コース = 概算受渡代金計算結果.手数料コース
        l_response.commissionCourse = l_EstimateDeliveryAmount.getCommissionCourse();

        //レスポンス.手数料 = 概算受渡代金計算結果.手数料
        l_response.commission = WEB3StringTypeUtility.formatNumber(l_EstimateDeliveryAmount.getCommission());

        //レスポンス.手数料消費税 = 概算受渡代金計算結果.手数料消費税
        l_response.commissionConsumptionTax = WEB3StringTypeUtility.formatNumber(l_EstimateDeliveryAmount.getCommissionTax());           

        //レスポンス.取引終了警告文言 = 取引時間管理.get市場閉局警告指数()の戻り値
        l_response.messageSuspension = WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(l_subAccount.getWeb3GenBranch(), WEB3FuturesOptionDivDef.OPTION);

        //レスポンス.確認時単価 = 概算受渡代金計算結果.計算単価
        double l_dblCalcUnitPrice = l_EstimateDeliveryAmount.getCalcUnitPrice();
        if (Double.isNaN(l_dblCalcUnitPrice))
        {
            l_dblCalcUnitPrice = 0D;
        }
        l_response.checkPrice = WEB3StringTypeUtility.formatNumber(l_dblCalcUnitPrice);

        //レスポンス.確認時発注日 = 取引時間管理.get発注日()の戻り値
        l_response.checkDate = WEB3DateUtility.toDay(l_datOrderBizDate);

        //レスポンス.注文有効期限 = 新規建注文内容.返済注文内容
        l_response.expirationDate = l_settleContractOrderSpec.getOrderExpDate();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit注文)<BR>
     * <BR>
     * 株価指数オプションの返済注文を登録する。<BR>
     * <BR>
     * 「（OP返済サービス）submit注文」参照。<BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.ifo.message.WEB3OptionsCloseMarginCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 405513960166
     */
    protected WEB3OptionsCloseMarginCompleteResponse submitOrder(WEB3OptionsCloseMarginCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitOrder(WEB3OptionsCloseMarginCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //リクエストデータの整合性をチェックする。
        l_request.validate();

        WEB3OptionsCloseMarginCompleteResponse l_response = (WEB3OptionsCloseMarginCompleteResponse)l_request.createResponse();

        //補助口座を取得する。
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)this.getSubAccount();

        //代理入力者の扱者オブジェクトを取得する。
        Trader l_trader = this.getTrader();

        //createリクエストアダプタ(WEB3GenRequest)
        WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter = this.createRequestAdapter(l_request);

        //create返済建玉エントリ(OP返済注文リクエストアダプタ, 返済建玉[])
        SettleContractEntry[] l_settleContractOrderEntries =
            this.createSettleContractEntry(l_requestAdapter, l_request.closeMarginContractUnits);

        //get建玉( )
        WEB3IfoContractImpl l_contractImpl = l_requestAdapter.getContract();

        //getProduct( )
        IfoProduct l_product = (IfoProduct)l_contractImpl.getProduct();

        //reset銘柄コード(銘柄コード : String)
        WEB3GentradeTradingTimeManagement.resetProductCode(l_product.getUnderlyingProductCode());

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3OptionOrderManagerImpl l_orderMgr = (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

        //執行条件: 先物OPデータアダプタ.get執行条件(リクエストデータ.執行条件)
        IfoOrderExecutionConditionType l_execCondType = WEB3IfoDataAdapter.getExecutionConditionType(l_request.execCondType);

        //先物OP取引銘柄オブジェクトを取得する。
        TradedProduct l_tradecProduct = l_contractImpl.getTradedProduct();

        //発注日
        Date l_datBizDate = null;
        if (l_request.checkDate != null)
        {
            l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
        }

        double l_dblLimitPrice = l_requestAdapter.getPrice();
        double l_dblwLimitPrice = 0D;
        
        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_request.wLimitOrderPriceDiv)
            && WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_dblwLimitPrice = Double.parseDouble(l_request.wLimitPrice);
        }

        //初回注文の注文単位ID： 
        //先物OPデータアダプタ.get初回注文の注文単位ID(リクエストデータ.注文期限区分)
        Long l_lngFirstOrderUnitId = WEB3IfoDataAdapter.getFirstOrderUnitId(l_request.expirationDateType);
        // 注文失効日：　@OP注文マネージャ.get注文有効期限(リクエストデータ.注文有効期限,
        // 先物OP銘柄.銘柄コード,リクエストデータ.取引市場,”オプション”)の戻り値
        // 銘柄オブジェクトを取得する。
        WEB3IfoProductImpl l_ifoProductImpl = (WEB3IfoProductImpl)l_contractImpl.getProduct();
        String l_strProductCode = l_ifoProductImpl.getProductCode();
        Market l_market = l_ifoProductImpl.getPrimaryMarket();
        String l_strMarketCode = l_market.getMarketCode();
        Date l_datExpirationDate = l_orderMgr.getExpirationDate(
            l_request.expirationDate,
            l_strProductCode,
            l_strMarketCode,
            WEB3FuturesOptionDivDef.OPTION);

        //返済注文内容オブジェクトを生成する。
        double l_dblStopOrderPrice = 0D;
        //リクエストデータ.発注条件区分 == ”逆指値”の場合
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_dblStopOrderPrice = Double.parseDouble(l_request.stopOrderCondPrice);
        }
        //リクエストデータ.発注条件区分 == ”W指値”の場合
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_dblStopOrderPrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
        }

        IfoOrderExecutionConditionType l_wLimitExecCondType = WEB3IfoDataAdapter.getExecutionConditionType(l_request.wlimitExecCondType);

        //夕場前繰越対象フラグ：先物OPデータアダプタ.get夕場前繰越対象フラグ(
        //      リクエストデータ.注文期限区分,
        //      補助口座に該当する部店.部店ID)
        boolean l_blnEveningSessionCarryoverFlag = WEB3IfoDataAdapter.getEveningSessionCarryOverFlag(
            l_request.expirationDateType,
            l_subAccount.getWeb3GenBranch().getBranchId());

        WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec =
            WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
                l_subAccount.getInstitution().getInstitutionCode(), //証券会社コード
                l_trader,                                           //扱者
                l_dblLimitPrice,                                    //指値
                l_execCondType,                                     //執行条件
                l_datExpirationDate,                                //注文失効日
                l_settleContractOrderEntries,                       //返済建玉エントリ
                l_request.orderCondType,                            //発注条件
                l_dblStopOrderPrice,                                //逆指値基準値
                l_dblwLimitPrice,                                   //W指値用注文単価
                l_wLimitExecCondType,                               //(W指値)執行条件
                l_request.expirationDateType,                       //注文期限区分
                l_lngFirstOrderUnitId,                              //初回注文の注文単位ID
                l_blnEveningSessionCarryoverFlag);                  //夕場前繰越対象フラグ

        //先物OP返済注文の発注審査を実施する。
        //[引数]
        //補助口座：　@get補助口座()の戻り値
        //返済注文内容：　@create返済注文内容()の戻り値
        //リクエストアダプタ：　@createリクエストアダプタ()の戻り値
        NewOrderValidationResult l_newOrderValidationResult =
            this.validateOptionsSettleContractOrder(l_subAccount, l_settleContractOrderSpec, l_requestAdapter);

        if (l_newOrderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_newOrderValidationResult.getProcessingResult());
            throw new WEB3BaseException(
                l_newOrderValidationResult.getProcessingResult().getErrorInfo(),
                STR_METHOD_NAME);
        }

        //建玉の合計数量を取得する。
        double l_dblTotalQuantity = l_settleContractOrderSpec.getTotalQuantity();
        //START 2004/08/13 呉艶飛  対応バッグ BUG79
        if (Double.isNaN(l_dblTotalQuantity))
        {
            l_dblTotalQuantity = 0D;
        }

        //手数料オブジェクトを生成する。
        WEB3GentradeCommission l_commission = new WEB3GentradeCommission();

        //手数料.注文チャネル = this.getログインチャネル()
        l_commission.setOrderChannel(this.getLoginChannel());

        //手数料.証券会社ID = 補助口座.get証券会社()
        l_commission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());

        //手数料.部店ID = 補助口座.get取引店()
        l_commission.setBranchId(l_subAccount.getWeb3GenBranch().getBranchId());

        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        //手数料.発注日 = 取引時間管理.get発注日()
        l_commission.setOrderBizDate(new Timestamp(l_datOrderBizDate.getTime()));

        //手数料.取引コード(SONAR) = ”52：返済”
        l_commission.setSonarTradedCode(WEB3TransactionTypeSONARDef.SETTLE_CONTRACT);

        //2004/07/19 王暁傑  対応変更  【先物オプション】仕様変更管理台帳.xls  No.046
        //弁済区分をセットする。
        //l_commission.setPayType("00");
        l_commission.setPayType(WEB3PayTypeDef.OTHER);

        //手数料.手数料商品コード = ”51：株価指数OP”
        l_commission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_OP);
        //手数料.is指値をセットする。
        l_commission.setIsLimitPrice(l_settleContractOrderSpec.isLimitOrder());

        //手数料.原資産銘柄コード = 建玉.get銘柄().get原資産銘柄コード(*2)
        l_commission.setUnderlyingProductCode(
            ((WEB3IfoProductImpl)l_contractImpl.getProduct()).getUnderlyingProductCode());

        //手数料.日計り区分 = this.get日計り区分()の戻り値
        l_commission.setDayTradeType(this.getDayTradeType(l_settleContractOrderEntries, l_requestAdapter));

        //手数料.数量 = 返済注文内容.getTotalQuantity()
        l_commission.setQuantity(l_dblTotalQuantity);

        //売買
        SideEnum l_sellToBuy = null;
        if (l_contractImpl.isLong())
        {
            l_sellToBuy = SideEnum.SELL;
        }
        else
        {
            l_sellToBuy = SideEnum.BUY;
        }

        double l_dblPrice = 0D;
        if (l_request.checkPrice != null)
        {
            l_dblPrice = Double.parseDouble(l_request.checkPrice); 
        }
        else
        {

            l_dblPrice = l_requestAdapter.getPrice(); 

        }

        //概算受渡代金を計算する。
        //[calc概算受渡代金()に指定する引数]
        //手数料：　@手数料オブジェクト
        //指値：
        //　@リクエストデータ.確認時単価!=nullの場合、リクエストデータ.確認時単価を設定。
        //　@リクエストデータ.確認時単価==nullの場合、createリクエストアダプタ()の戻り値.get単価()を設定
        //補助口座：　@補助口座オブジェクト
        //先物OP取引銘柄：　@先物OP取引銘柄オブジェクト
        //数量： 返済注文内容.getTotalQuantity()
        //売買：
        //　@パラメータ.リクエストアダプタ.get建玉().isLong() == trueの場合SideEnum.SELL
        //　@パラメータ.リクエストアダプタ.get建玉().isLong() == falseの場合SideEnum.BUY 
        //is返済注文：　@true
        //isSkip金額チェック：　@false
        WEB3IfoEstimateDeliveryAmountCalcResult l_dblEstimateDeliveryAmount =
            l_orderMgr.calcEstimateDeliveryAmount(
                l_commission,
                l_dblPrice,
                (WEB3GentradeSubAccount)l_subAccount,
                (WEB3IfoTradedProductImpl)l_tradecProduct,
                l_dblTotalQuantity,
                l_sellToBuy,
                true,
                false);

        long l_lngOrderId = 0;
        if (l_request.orderId != null)
        {
            l_lngOrderId = Long.parseLong(l_request.orderId);
        }
        else
        {
            l_lngOrderId = l_orderMgr.createNewOrderId();
        }

        //[引数]
        //リクエストアダプタ：　@createリクエストアダプタ()の戻り値
        //補助口座：　@get補助口座()の戻り値
        //返済注文内容：　@create返済注文内容()で生成した注文内容。
        //注文ID：　@リクエストデータ.注文ID　@(*1)
        //手数料：　@calc概算受渡代金()引数の手数料オブジェクト
        //概算受渡代金計算結果：　@calc概算受渡代金()の戻り値
        //(*1)　@リクエストデータ.注文ID == nullの場合、
        //　@　@　@　@createNewOrderId()の戻り値
        this.submitSettleContractOrder(
            l_requestAdapter,
            l_subAccount,
            l_settleContractOrderSpec,
            l_lngOrderId,
            l_commission,
            l_dblEstimateDeliveryAmount);

        this.execReCalcTradingPower(l_subAccount);

        //レスポンス.更新時間 = 現在日時(GtlUtils.getSystemTimestamp())
        l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
        //リクエストデータ.注文ID!=nullの場合、リクエストデータ.注文IDを設定。 
        //リクエストデータ.注文ID==nullの場合、OP注文マネージャ.createNewOrderId()の戻り値を設定。 
        l_response.orderActionId = "" + l_lngOrderId;

        log.debug("l_response.lastUpdateTimestamp = " + l_response.lastUpdatedTimestamp);
        log.debug("l_response.orderActionId = "+ l_response.orderActionId);

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (createリクエストアダプタ)<BR>
     * リクエストアダプタのインスタンスを生成する。 <BR>
     * <BR>
     * ※処理の詳細はシーケンス図「（OP返済）validate注文１」<BR>
     * 「（OP返済）submit注文１」参照<BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエストデータ。<BR>
     * @@return WEB3OptionSettleContractOrderRequestAdapter
     * @@throws WEB3BaseException
     */
    protected WEB3OptionSettleContractOrderRequestAdapter createRequestAdapter(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createRequestAdapter(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        //リクエストアダプタを作成する。
        //リクエスト ： 引数.リクエストデータ
        WEB3OptionSettleContractOrderRequestAdapter l_adapter =
            WEB3OptionSettleContractOrderRequestAdapter.create(l_request);

        log.exiting(STR_METHOD_NAME);
        return l_adapter;
    }

    /**
     * (create返済建玉エントリ)<BR>
     * 返済建玉エントリを作成する。 <BR>
     * <BR>
     * ※処理の詳細はシーケンス図「（OP返済）validate注文１」<BR>
     * 「（OP返済）submit注文１」参照<BR>
     * @@param l_requestAdapter - (リクエストアダプタ)<BR>
     * リクエストデータ。<BR>
     * @@param l_closeMarginContractUnits - (返済建玉オブジェクトの配列)<BR>
     * リクエストデータ<BR>
     * @@return SettleContractEntry[]
     * @@throws WEB3BaseException
     */
    protected SettleContractEntry[] createSettleContractEntry(
        WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter,
        WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSettleContractEntry(" +
            "WEB3OptionSettleContractOrderRequestAdapter, WEB3FuturesOptionsCloseMarginContractUnit[])";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3OptionOrderManagerImpl l_orderMgr = (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

        //決済順位でソートし、返済建玉エントリの配列を作成する。
        //[引数]
        //注文単位ID = 0
        //注文数量：　@パラメータ.リクエストアダプタ.get注文数量()戻り値
        //返済建玉[] = パラメータ.返済建玉
        SettleContractEntry[] l_settleContractOrderEntries = l_orderMgr.createSettleContractEntry(
            0, l_requestAdapter.getOrderQuantity(), l_closeMarginContractUnits);

        log.exiting(STR_METHOD_NAME);
        return l_settleContractOrderEntries;
    }

    /**
     * (validateOP返済注文)<BR>
     * OP返済注文の発注審査を実施する。 <BR>
     * <BR>
     * ※処理の詳細はシーケンス図「（OP返済）validate注文１」<BR>
     * 「（OP返済）submit注文１」参照 <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_settleContractOrderSpec - (返済注文内容)<BR>
     * 返済注文内容オブジェクト<BR>
     * @@param l_requestAdapter - (リクエストアダプタ)<BR>
     * リクエストアダプタ<BR>
     * @@return NewOrderValidationResult
     * @@throws WEB3BaseException
     */
    protected NewOrderValidationResult validateOptionsSettleContractOrder(
        SubAccount l_subAccount,
        WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec,
        WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOptionsSettleContractOrder(" +
            "SubAccount, IfoSettleContractOrderSpec, WEB3OptionSettleContractOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3OptionOrderManagerImpl l_orderMgr = (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

        NewOrderValidationResult l_result = null;
        //[引数]
        //arg0（補助口座）：　@パラメータ.補助口座
        //arg1（返済注文内容）：　@パラメータ.返済注文内容
        l_result = l_orderMgr.validateSettleContractOrder(
            (WEB3GentradeSubAccount)l_subAccount, l_settleContractOrderSpec);

        log.exiting(STR_METHOD_NAME);
        return l_result;
    }

    /**
     * (create建玉明細)<BR>
     * 返済建玉エントリより建玉明細の一覧を作成する。 <BR>
     * <BR>
     * ※処理の詳細はシーケンス図「（OP返済サービス）validate注文２」参照<BR>
     * @@param l_settleContractEntries - (返済建玉エントリ)<BR>
     * 返済建玉エントリ<BR>
     * @@param l_requestAdapter - (リクエストアダプタ)<BR>
     * リクエストアダプタ<BR>
     * @@param l_ifoTradedProduct - (先物OP取引銘柄)<BR>
     * 先物OP取引銘柄<BR>
     * @@return WEB3FuturesOptionsContractUnit[]
     * @@throws WEB3BaseException
     */
    protected WEB3FuturesOptionsContractUnit[] createContractUnit(
        SettleContractEntry[] l_settleContractEntries,
        WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter,
        WEB3IfoTradedProductImpl l_ifoTradedProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createContractUnit(" +
            "SettleContractEntry[], WEB3OptionSettleContractOrderRequestAdapter, WEB3IfoTradedProductImpl)";

        List l_lisContractUnits = new ArrayList();

        for (int i = 0; i < l_settleContractEntries.length; i++)
        {
            //時価情報を取得する｡
            WEB3IfoProductQuote l_currentInfo =
                ((WEB3IfoTradedProductImpl)l_ifoTradedProduct).getCurrentInfo(null);

            log.debug("i = " + i);
            SettleContractEntry l_settleContractEntry = l_settleContractEntries[i];

            //建玉明細オブジェクトを生成する。
            WEB3FuturesOptionsContractUnit l_contractUnit = new WEB3FuturesOptionsContractUnit();

            //返済数量
            double l_dblSettleContractQuantity = l_settleContractEntry.getQuantity();

            if (Double.isNaN(l_dblSettleContractQuantity))
            {
                l_dblSettleContractQuantity = 0D;
            }

            //建玉オブジェクトを取得する。
            WEB3IfoContractImpl l_contract = null;
            try
            {
                l_contract = new WEB3IfoContractImpl(l_settleContractEntry.getContractId());
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //建玉明細.ID = 建玉ＩＤ
            l_contractUnit.id = "" + l_contract.getContractId();

            //建玉明細.建年月日 = 建玉.getOpenDate()
            l_contractUnit.openDate = l_contract.getOpenDate();

            //建玉明細.建単価 = 建玉.getContractPrice()
            l_contractUnit.contractPrice = WEB3StringTypeUtility.formatNumber(l_contract.getContractPrice());

            //建玉明細.建玉数 = 建玉.getQuantity() - 建玉.getLockedQuantity()
            BigDecimal l_bdContractQuantity = new BigDecimal(l_contract.getQuantity() + "");
            BigDecimal l_bdLockedQuantity = new BigDecimal(l_contract.getLockedQuantity() + "");
            BigDecimal l_bdQuantity = l_bdContractQuantity.subtract(l_bdLockedQuantity);
            double l_dblQuantity = l_bdQuantity.doubleValue();
            l_contractUnit.contractQuantity = WEB3StringTypeUtility.formatNumber(l_dblQuantity);                

            //建玉明細.建約定金額 = 建玉.get建約定金額(返済数量)
            l_contractUnit.contractExecPrice =
                WEB3StringTypeUtility.formatNumber(l_contract.getContractExecutedAmount(
                    l_dblSettleContractQuantity));

            //建玉明細.建手数料
            BigDecimal l_bdCommission =
                new BigDecimal(l_contract.getContractCommission(l_dblSettleContractQuantity) + "");
            BigDecimal l_bdCommissionConsumptionTax =
                new BigDecimal(l_contract.getContractCommissionConsumptionTax(l_dblSettleContractQuantity) + "");
            double l_dblContractCommission =
                l_bdCommission.add(l_bdCommissionConsumptionTax).doubleValue();
            l_contractUnit.contractCommission = WEB3StringTypeUtility.formatNumber(l_dblContractCommission);

            //時価を取得する。
            double l_dblPrice = 0D;
            if (l_currentInfo != null)
            {
                l_dblPrice = l_currentInfo.getCurrentPrice();
            }

            //建玉詳細.損益 = 建玉.get評価損益()
            double l_dblIncome = l_contract.getEvaluateIncome(
                l_dblPrice,
                l_dblSettleContractQuantity);
            l_contractUnit.income = WEB3StringTypeUtility.formatNumber(l_dblIncome);

            //建玉詳細.損益（諸経費込）
            BigDecimal l_bdIncome = new BigDecimal(l_dblIncome + "");
            BigDecimal l_bdContractCommission = new BigDecimal(l_dblContractCommission + "");
            l_contractUnit.incomeCost = WEB3StringTypeUtility.formatNumber(
                l_bdIncome.subtract(l_bdContractCommission).doubleValue());

            //建玉明細.返済数量 = SettleContractEntry.getQuantity()（*返済数量）
            l_contractUnit.contractOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblSettleContractQuantity);

            //建玉明細.決済順位 = （SettleContractEntryのindex+1）
            l_contractUnit.settlePriority = "" + (i + 1);                

            //建玉明細.立会区分 = 建玉.立会区分
            l_contractUnit.sessionType =
                ((IfoContractRow)l_contract.getDataSourceObject()).getSessionType();

            l_lisContractUnits.add(l_contractUnit);
        }

        WEB3FuturesOptionsContractUnit[] l_contractUnits =
            new WEB3FuturesOptionsContractUnit[l_lisContractUnits.size()];
        l_lisContractUnits.toArray(l_contractUnits);

        log.exiting(STR_METHOD_NAME);
        return l_contractUnits;
    }

    /**
     * (submit返済注文)<BR>
     * OP返済注文を登録する。 <BR>
     * <BR>
     * ※処理の詳細はシーケンス図「（OP返済）submit注文２」参照<BR>
     * @@param l_requestAdapter - (リクエストアダプタ)<BR>
     * リクエストアダプタ<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_settleContractOrderSpec - (返済注文内容)<BR>
     * 返済注文内容<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID<BR>
     * @@param l_commission - (手数料)<BR>
     * 手数料<BR>
     * @@param l_amountCalcResult - (概算受渡代金計算結果)<BR>
     * 概算受渡代金計算結果<BR>
     * @@throws WEB3BaseException
     */
    protected void submitSettleContractOrder(
        WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter,
        SubAccount l_subAccount,
        WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec,
        long l_lngOrderId,
        WEB3GentradeCommission l_commission,
        WEB3IfoEstimateDeliveryAmountCalcResult l_amountCalcResult) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitSettleContractOrder(" +
            "WEB3OptionSettleContractOrderRequestAdapter, " +
            "SubAccount, " +
            "WEB3IfoSettleContractOrderSpec, " +
            "long" +
            "WEB3GentradeCommission, " +
            "WEB3IfoEstimateDeliveryAmountCalcResult)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3OptionOrderManagerImpl l_orderMgr = (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

        WEB3OptionsCloseMarginCompleteRequest l_request =
            (WEB3OptionsCloseMarginCompleteRequest)l_requestAdapter.request;

        //インタセプタを生成する。
        WEB3IfoSettleContractUpdateInterceptor l_settleContractUpdateInterceptor =
            new WEB3IfoSettleContractUpdateInterceptor(l_settleContractOrderSpec);

        //インタセプタ.手数料
        l_settleContractUpdateInterceptor.setCommision(l_commission);

        //インタセプタ.概算受渡代金計算結果
        l_settleContractUpdateInterceptor.setEstimateDeliveryAmountCalcResult(l_amountCalcResult);

        //インタセプタ.発注条件
        l_settleContractUpdateInterceptor.setOrderCond(l_request.orderCondType);

        double l_dblstopOrderCondPrice = 0D;
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_dblstopOrderCondPrice = Double.parseDouble(l_request.stopOrderCondPrice);
        }
        //（*発注条件区分で逆指値/W指値を判定）
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            //インタセプタ.発注条件演算子 = リクエストデータ.発注条件演算子
            l_settleContractUpdateInterceptor.setOrderCondOperator(l_request.stopOrderCondOperator);

            //インタセプタ.逆指値基準値タイプ = リクエストデータ.プレミアム／原資産価格
            l_settleContractUpdateInterceptor.setStopOrderBasePriceType(l_request.stopPremium_underlyingAssets);

            //インタセプタ.逆指値基準値 = リクエストデータ.発注条件単価
            l_settleContractUpdateInterceptor.setStopOrderBasePrice(l_dblstopOrderCondPrice);
        }
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            //インタセプタ.発注条件演算子 = リクエストデータ.発注条件演算子
            l_settleContractUpdateInterceptor.setOrderCondOperator(l_request.wlimitOrderCondOperator);
            
            //インタセプタ.逆指値基準値タイプ = リクエストデータ.プレミアム／原資産価格
            l_settleContractUpdateInterceptor.setStopOrderBasePriceType(l_request.wlimitPremium_underlyingAssets);

            double l_dblWLimitOrderCondPrice = 0D;

            if (WEB3StringTypeUtility.isNumber(l_request.wlimitOrderCondPrice))
            {
                l_dblWLimitOrderCondPrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
            }

            //インタセプタ.逆指値基準値 = リクエストデータ.発注条件単価
            l_settleContractUpdateInterceptor.setStopOrderBasePrice(l_dblWLimitOrderCondPrice);

            //インタセプタ.(W指値)訂正指値 = 返済注文内容.(W指値)訂正指値
            log.debug("setWLimitPriceChange");
            l_settleContractUpdateInterceptor.setWLimitPriceChange(l_settleContractOrderSpec.getWLimitPriceChange());
        }

        //インタセプタ.決済順序 = リクエストデータ.決済順序
        l_settleContractUpdateInterceptor.setSettleSequence(l_request.closingOrder);

        //インタセプタ.立会区分 = 取引時間管理.get立会区分()の戻り値
        l_settleContractUpdateInterceptor.setSessionType(WEB3GentradeTradingTimeManagement.getSessionType());

        //インタセプタを注文マネージャにセットする。
        l_orderMgr.setThreadLocalPersistenceEventInterceptor(l_settleContractUpdateInterceptor);

        //返済注文登録処理を行う。
        l_settleContractOrderSpec.getTaxType();

        OrderSubmissionResult l_result = l_orderMgr.submitSettleContractOrder(
            l_subAccount,
            l_settleContractOrderSpec,
            l_lngOrderId,
            l_request.password,
            true);

        if (l_result.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_result.getProcessingResult());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_result.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set単価)<BR>
     * 何もせずにreturnする。（カラ実装）<BR>
     * @@param l_requestAdapter - (OP返済注文リクエストアダプタ)<BR>
     * OP返済注文リクエストアダプタ。<BR>
     * @@param l_response - (レスポンス)<BR>
     * レスポンス。<BR>
     * @@throws WEB3BaseException
     */
    protected void setPrice(
        WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter,
        WEB3GenResponse l_response) throws WEB3BaseException
    {
        return;
    }

    /**
     * (exec余力再計算)<BR>
     * 余力再計算を行う。 <BR>
     * <BR>
     * ※処理の詳細はシーケンス図「（OP返済）submit注文２」参照<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。<BR>
     * @@throws WEB3BaseException
     */
    protected void execReCalcTradingPower(SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execReCalcTradingPower(SubAccount)";
        log.entering(STR_METHOD_NAME);

        if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()))
        {
            WEB3TPTradingPowerService l_tradingPowerService =
                (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

            //余力再計算を行う。
            l_tradingPowerService.reCalcTradingPower((WEB3GentradeSubAccount)l_subAccount);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get日計り区分)<BR>
     * 日計り区分を取得する。 <BR>
     * <BR>
     * OP注文マネージャ.get日計り区分()をcallする。 <BR>
     * <BR>
     * [引数] <BR>
     * 返済建玉エントリ：　@パラメータ.返済建玉エントリ <BR>
     * @@param l_settleContractOrderEntries - (返済建玉エントリ)<BR>
     * 返済建玉エントリ<BR>
     * @@param l_requestAdapter - (リクエストアダプタ)<BR>
     * リクエストアダプタ<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    protected String getDayTradeType(
        SettleContractEntry[] l_settleContractOrderEntries,
        WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getDayTradeType(SettleContractEntry[], WEB3OptionSettleContractOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3OptionOrderManagerImpl l_orderMgr =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
        //OP注文マネージャ.get日計り区分()をcallする。
        String l_strDayTradeType = l_orderMgr.getDayTradeType(l_settleContractOrderEntries);

        log.exiting(STR_METHOD_NAME);
        return l_strDayTradeType;
    }
}
@
