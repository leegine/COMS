head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesSettleContractOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物返済注文サービス実装(WEB3FuturesSettleContractOrderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/21 鄒鋭 (中訊) 新規作成
                 : 2006/07/28 肖志偉 (中訊) 仕様変更　@モデル486
Revesion History : 2007/06/21 張騰宇(中訊) モデル726 743
Revesion History : 2007/11/20 趙林鵬 (中訊)仕様変更 モデル810
Revesion History : 2007/11/28 趙林鵬 (中訊)Javaソース（基本設計と合っていない実装）011
Revesion History : 2008/03/13 金傑 (中訊)仕様変更 モデル840,841,842
Revesion History : 2008/03/14 金傑 (中訊)仕様変更 モデル853
Revesion History : 2008/03/17 金傑 (中訊)仕様変更 モデル854
Revesion History : 2008/04/14 張騰宇 (中訊)仕様変更 モデル873 876 878
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PayTypeDef;
import webbroker3.common.define.WEB3StopBasePriceTypeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3FuturesClientRequestService;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductQuote;
import webbroker3.ifo.WEB3IfoSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoSettleContractUpdateInterceptor;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.message.WEB3FuturesCloseMarginCompleteRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginCompleteResponse;
import webbroker3.ifo.message.WEB3FuturesCloseMarginConfirmRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginConfirmResponse;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.service.delegate.WEB3FuturesSettleContractOrderRequestAdapter;
import webbroker3.ifo.service.delegate.WEB3FuturesSettleContractOrderService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (先物返済注文サービスImpl)<BR>
 * 株価指数先物返済注文サービス実装クラス
 * @@author 鄒鋭
 * @@version 1.0
 */
public class WEB3FuturesSettleContractOrderServiceImpl extends WEB3FuturesClientRequestService implements WEB3FuturesSettleContractOrderService
{

    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesSettleContractOrderServiceImpl.class);

    /**
     * @@roseuid 40F7A2CE0213
     */
    public WEB3FuturesSettleContractOrderServiceImpl()
    {

    }

    /**
     * 株価指数先物返済注文サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型により、validate注文()または、submit注文()<BR>
     * メソッドをコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A8C21D03D4
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3FuturesCloseMarginConfirmRequest) //validate注文
        {
            l_response = validateOrder((WEB3FuturesCloseMarginConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3FuturesCloseMarginCompleteRequest) //submit注文
        {
            l_response = submitOrder((WEB3FuturesCloseMarginCompleteRequest) l_request);
        }
        else
        {
            log.error(STR_METHOD_NAME + "パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, STR_METHOD_NAME);
        }
        //log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (validate注文)<BR>
     * 株価指数先物の返済発注審査を行う。<BR>
     * <BR>
     * 「（先物返済サービス）validate注文」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3FuturesCloseMarginConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A8C21E000C
     */
    protected WEB3FuturesCloseMarginConfirmResponse validateOrder(WEB3FuturesCloseMarginConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateOrder(WEB3FuturesCloseMarginConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //リクエストデータの整合性をチェックする。
        l_request.validate();

        WEB3FuturesCloseMarginConfirmResponse l_response = (WEB3FuturesCloseMarginConfirmResponse) l_request.createResponse();

        //補助口座を取得する。
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) this.getSubAccount();
        //代理入力者の扱者オブジェクトを取得する。
        Trader l_trader = this.getTrader();

        // createリクエストアダプタ
        WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter = createRequestAdapter(l_request);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3FuturesOrderManagerImpl l_orderMgr = (WEB3FuturesOrderManagerImpl) l_tradingModule.getOrderManager();

        // create返済建玉エントリ
        SettleContractEntry[] l_settleContractEntries = createSettleContractEntry(
            l_requestAdapter, l_request.closeMarginContractUnits);
        // 建玉オブジェクトを取得する。
        WEB3IfoContractImpl l_contractImpl = l_requestAdapter.getContract();

        //先物OP銘柄を取得する
        WEB3IfoProductImpl l_ifoProductImpl = (WEB3IfoProductImpl)l_contractImpl.getProduct();

        // reset銘柄コード
        WEB3GentradeTradingTimeManagement.resetProductCode(l_ifoProductImpl.getUnderlyingProductCode());

        //先物OP取引銘柄オブジェクトを取得する。
        TradedProduct l_tradecProduct = l_contractImpl.getTradedProduct();

        //執行条件：　@先物OPデータアダプタ.get執行条件(リクエストデータ.執行条件)の戻り値
        IfoOrderExecutionConditionType l_executionConditionType = 
            WEB3IfoDataAdapter.getExecutionConditionType(l_request.execCondType);
        
        //set limitPrice
        double l_dblLimitPrice = l_requestAdapter.getPrice();
        double l_dblwLimitPrice = 0D;

        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_request.wLimitOrderPriceDiv)
            && WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            //指値
            l_dblwLimitPrice = Double.parseDouble(l_request.wLimitPrice);
        }
        else
        {
            l_dblwLimitPrice = 0D;
        }
        
        log.debug("l_dblLimitPrice = " + l_dblLimitPrice);
        log.debug("l_dblwLimitPrice = " + l_dblwLimitPrice);
        
        //逆指値基準値：
        double l_dblStopOrderBasePrice = 0D;
        
        //[リクエストデータ.発注条件区分 == ”逆指値”の場合
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            //リクエストデータ.逆指値用発注条件単価
            l_dblStopOrderBasePrice = Double.parseDouble(l_request.stopOrderCondPrice);
        }
        
        //　@[リクエストデータ.発注条件区分 == ”W指値”の場合]
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            //リクエストデータ.W指値用発注条件単価
            l_dblStopOrderBasePrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
        }
        
        //（W指値）執行条件：　@
        //  先物OPデータアダプタ.get執行条件(リクエストデータ.W指値用執行条件)の戻り値
        IfoOrderExecutionConditionType l_wLimitExecutionConditionType = 
            WEB3IfoDataAdapter.getExecutionConditionType(l_request.wlimitExecCondType);

        //初回注文の注文単位ID：　@先物OPデータアダプタ.get初回注文の
        //  注文単位ID(リクエストデータ.注文期限区分)
        Long l_firstOrderUnitId = WEB3IfoDataAdapter.getFirstOrderUnitId(l_request.expirationDateType);

        //夕場前繰越対象フラグ：　@先物OPデータアダプタ.get夕場前繰越対象
        //   フラグ(リクエストデータ.注文期限区分, 補助口座に該当する部店.部店ID)
        boolean l_blnEveningSessionCarryOverFlag =
            WEB3IfoDataAdapter.getEveningSessionCarryOverFlag(
                l_request.expirationDateType,
                l_subAccount.getWeb3GenBranch().getBranchId());

        //OP注文マネージャ
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

        //市場
        Market l_market = l_ifoProductImpl.getPrimaryMarket();

        //注文失効日：　@OP注文マネージャ.get注文有効期限(
        //リクエストデータ.注文有効期限,先物OP銘柄.銘柄コード,市場.getMarketCode(),”先物”)の戻り値
        Date l_datExpirationDate = l_orderManager.getExpirationDate(
            l_request.expirationDate,
            l_ifoProductImpl.getProductCode(),
            l_market.getMarketCode(),
            WEB3FuturesOptionDivDef.FUTURES);

        //返済注文内容オブジェクトを生成する。
        log.debug("createSettleContractOrderSpec");
        WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec =
            WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_trader,
                l_dblLimitPrice,
                l_executionConditionType,
                l_datExpirationDate,
                l_settleContractEntries,
                l_request.orderCondType,
                l_dblStopOrderBasePrice,
                l_dblwLimitPrice,
                l_wLimitExecutionConditionType,
                l_request.expirationDateType,
                l_firstOrderUnitId,
                l_blnEveningSessionCarryOverFlag);

        //先物OP返済注文の発注審査を実施する。
        // validate先物返済注文
        // [引数]
        // 補助口座：　@get補助口座()の戻り値
        // 返済注文内容：　@create返済注文内容()の戻り値
        // リクエストアダプタ：　@createリクエストアダプタ()の戻り値
        NewOrderValidationResult l_newOrderValidationResult = validateFuturesSettleContractOrder(
            l_subAccount, l_settleContractOrderSpec, l_requestAdapter);
        if (l_newOrderValidationResult.getProcessingResult().isFailedResult())
        {
            //例外をスローする
            log.debug("ProcessingResult() = " + l_newOrderValidationResult.getProcessingResult());
            throw new WEB3BusinessLayerException(
                l_newOrderValidationResult.getProcessingResult().getErrorInfo(), 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        //建玉の合計数量を取得する。
        double l_dblTotalQuantity = l_settleContractOrderSpec.getTotalQuantity();
        log.debug("l_dblTotalQuantity = " + l_dblTotalQuantity);

        //手数料オブジェクトを生成する。
        WEB3GentradeCommission l_commission = new WEB3GentradeCommission();

        //手数料.注文チャネル = this.getログインチャネル()
        l_commission.setOrderChannel(this.getLoginChannel());

        //手数料.証券会社ID = 補助口座.get証券会社()
        log.debug("setInstitutionCode: " + l_subAccount.getInstitution().getInstitutionCode());
        l_commission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());

        //手数料.部店ID = 補助口座.get取引店()
        log.debug("setBranchId: " + l_subAccount.getWeb3GenBranch().getBranchId());
        l_commission.setBranchId(l_subAccount.getWeb3GenBranch().getBranchId());

        //発注日
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        Timestamp l_tsOrderBizDate = new Timestamp(l_datOrderBizDate.getTime());
        log.debug("l_tsOrderBizDate = " + l_tsOrderBizDate);

        //手数料.発注日 = 取引時間管理.get発注日()
        l_commission.setOrderBizDate(l_tsOrderBizDate);

        //手数料.取引コード(SONAR) = ”52：返済”
        l_commission.setSonarTradedCode(WEB3TransactionTypeSONARDef.SETTLE_CONTRACT);

        //手数料.手数料商品コード = ”50：株価指数先物”
        l_commission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_FUTURES);

        //弁済区分をセットする。
        l_commission.setPayType(WEB3PayTypeDef.OTHER);
        
        //is指値をセットする。
        l_commission.setIsLimitPrice(l_settleContractOrderSpec.isLimitOrder());
        
        //手数料.原資産銘柄コード = 建玉.get銘柄().get原資産銘柄コード(*2)
        l_commission.setUnderlyingProductCode(
            ((WEB3IfoProductImpl)l_contractImpl.getProduct()).getUnderlyingProductCode());

        //手数料.日計り区分 = this.get日計り区分の戻り値
        l_commission.setDayTradeType(this.getDayTradeType(l_settleContractEntries, l_requestAdapter));

        //手数料.数量 = 返済注文内容.getTotalQuantity()
        l_commission.setQuantity(l_dblTotalQuantity);

        //概算決済損益を計算する。
        // [引数] 
        // 手数料：　@手数料オブジェクト
        // 指値：　@createリクエストアダプタ()の戻り値.get単価()
        // 補助口座：　@補助口座オブジェクト
        // 先物OP取引銘柄：　@先物OP取引銘柄オブジェクト
        // 返済建玉エントリ[]：  create返済建玉エントリの戻り値
        // 数量： 返済注文内容.getTotalQuantity()
        // リクエストアダプタ：　@createリクエストアダプタ()の戻り値
        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmount = getEstimateSettlementIncome(
            l_commission,
            l_requestAdapter.getPrice(),
            l_subAccount,
            (WEB3IfoTradedProductImpl)l_tradecProduct,
            l_settleContractEntries,
            l_settleContractOrderSpec.getTotalQuantity(),
            l_requestAdapter);

        // create建玉明細
        //[引数]
        // 返済建玉エントリ：　@create返済建玉エントリ()の戻り値
        // リクエストアダプタ：　@createリクエストアダプタ()の戻り値
        // 先物OP取引銘柄：　@先物OP取引銘柄オブジェクト
        WEB3FuturesOptionsContractUnit[] l_contractUnits = createContractUnit(
            l_settleContractEntries, l_requestAdapter, (WEB3IfoTradedProductImpl)l_tradecProduct);

        //新規注文番号を採番する。
        long l_lngOrderId = l_orderMgr.createNewOrderId();

        //set単価(先物返済注文リクエストアダプタ, WEB3GenResponse)
        setPrice(l_requestAdapter, l_response);

        //レスポンス.注文ID = OP注文マネージャ.createNewOrderId()の戻り値
        l_response.orderId = "" + l_lngOrderId;

        //レスポンス.建玉明細 = create建玉明細の戻り値
        l_response.contractUnits = l_contractUnits;
        log.debug("l_response.contractUnits = " + l_contractUnits[0].contractExecPrice);
        //レスポンス.概算決済損益 = 概算決済損益計算結果.概算受渡代金
        l_response.estimatedSettleIncome = WEB3StringTypeUtility.formatNumber(l_estimateDeliveryAmount.getEstimateDeliveryAmount());
        log.debug("l_response.estimatedSettleIncome = " + l_estimateDeliveryAmount.getEstimateDeliveryAmount());

        //レスポンス.手数料コース = 概算受渡代金計算結果.手数料コース
        l_response.commissionCourse = l_estimateDeliveryAmount.getCommissionCourse();
        
        //レスポンス.手数料 = 概算受渡代金計算結果.手数料
        l_response.commission = WEB3StringTypeUtility.formatNumber(l_estimateDeliveryAmount.getCommission());
        
        //レスポンス.手数料消費税 = 概算受渡代金計算結果.手数料消費税
        l_response.commissionConsumptionTax = WEB3StringTypeUtility.formatNumber(l_estimateDeliveryAmount.getCommissionTax());
        
        //レスポンス.取引終了警告文言 = 取引時間管理.get市場閉局警告指数()の戻り値
        l_response.messageSuspension = WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(l_subAccount.getWeb3GenBranch(), WEB3FuturesOptionDivDef.FUTURES);
        log.debug("l_response.messageSuspension = " + WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(l_subAccount.getWeb3GenBranch(), WEB3FuturesOptionDivDef.FUTURES));
        //レスポンス.確認時単価 = 概算受渡代金計算結果.計算単価
        double l_dblCalcUnitPrice = l_estimateDeliveryAmount.getCalcUnitPrice();
        if (Double.isNaN(l_dblCalcUnitPrice))
        {
            l_dblCalcUnitPrice = 0D;
        }
        l_response.checkPrice = WEB3StringTypeUtility.formatNumber(l_dblCalcUnitPrice);
        log.debug("l_response.checkPrice = " + l_estimateDeliveryAmount.getEstimateDeliveryAmount());
        //レスポンス.確認時発注日 = 取引時間管理.get発注日()の戻り値
        l_response.checkDate = WEB3DateUtility.toDay(l_datOrderBizDate);
        log.debug("l_response.checkDate = " + l_datOrderBizDate);

        //レスポンス.注文有効期限 = 返済注文内容.注文失効日
        l_response.expirationDate = l_settleContractOrderSpec.getOrderExpDate();

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (submit注文)<BR>
     * 株価指数先物の返済注文を登録する。<BR>
     * <BR>
     * 「（先物返済サービス）submit注文」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3FuturesCloseMarginCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A8C21E002B
     */
    protected WEB3FuturesCloseMarginCompleteResponse submitOrder(WEB3FuturesCloseMarginCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitOrder(WEB3FuturesCloseMarginCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //リクエストデータの整合性をチェックする。
        l_request.validate();
        WEB3FuturesCloseMarginCompleteResponse l_response;

        //補助口座を取得する。
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) this.getSubAccount();

        //代理入力者の扱者オブジェクトを取得する。
        Trader l_trader = this.getTrader();

        // createリクエストアダプタ
        WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter = createRequestAdapter(l_request);

        // create返済建玉エントリ
        SettleContractEntry[] l_settleContractOrderEntries = createSettleContractEntry(
            l_requestAdapter, l_request.closeMarginContractUnits);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3FuturesOrderManagerImpl l_orderMgr = (WEB3FuturesOrderManagerImpl) l_tradingModule.getOrderManager();

        //建玉オブジェクトを取得する。
        WEB3IfoContractImpl l_contractImp = l_requestAdapter.getContract();

        //先物OP銘柄を取得する
        WEB3IfoProductImpl l_ifoProductImpl = (WEB3IfoProductImpl)l_contractImp.getProduct();

        // reset銘柄コード
        WEB3GentradeTradingTimeManagement.resetProductCode(l_ifoProductImpl.getUnderlyingProductCode());

        //先物取引銘柄オブジェクトを取得する。
        TradedProduct l_tradecProduct = l_contractImp.getTradedProduct();

        //get発注日
        //リクエストデータ.確認時発注日!=nullの場合、コール。
        Date l_datOrderBizDate = null;
        if (l_request.checkDate != null)
        {
            l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
        }

        //執行条件：　@先物OPデータアダプタ.get執行条件(リクエストデータ.執行条件)の戻り値
        IfoOrderExecutionConditionType l_executionConditionType = 
            WEB3IfoDataAdapter.getExecutionConditionType(l_request.execCondType);
        
        //set limitPrice
        double l_dblLimitPrice = l_requestAdapter.getPrice();
        double l_dblwLimitPrice;

        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_request.wLimitOrderPriceDiv)
            && WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {                
            l_dblwLimitPrice = Double.parseDouble(l_request.wLimitPrice);
        }
        else
        {
            l_dblwLimitPrice = 0D;
        }            
        
        log.debug("l_dblLimitPrice = " + l_dblLimitPrice);
        log.debug("l_dblwLimitPrice = " + l_dblwLimitPrice);

        //逆指値基準値：
        double l_dblStopOrderBasePrice = 0D;
        
        //[リクエストデータ.発注条件区分 == ”逆指値”の場合
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            //リクエストデータ.逆指値用発注条件単価
            l_dblStopOrderBasePrice = Double.parseDouble(l_request.stopOrderCondPrice);
        }
        
        //　@[リクエストデータ.発注条件区分 == ”W指値”の場合]
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            //リクエストデータ.W指値用発注条件単価
            l_dblStopOrderBasePrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
        }
        
        //（W指値）執行条件：　@
        //  先物OPデータアダプタ.get執行条件(リクエストデータ.W指値用執行条件)の戻り値
        IfoOrderExecutionConditionType l_wLimitExecutionConditionType = 
            WEB3IfoDataAdapter.getExecutionConditionType(l_request.wlimitExecCondType);
        
        //初回注文の注文単位ID：　@先物OPデータアダプタ.get初回注文の
        //  注文単位ID(リクエストデータ.注文期限区分)
        Long l_firstOrderUnitId = WEB3IfoDataAdapter.getFirstOrderUnitId(l_request.expirationDateType);

        //夕場前繰越対象フラグ：　@先物OPデータアダプタ.get夕場前繰越対象
        //   フラグ(リクエストデータ.注文期限区分, 補助口座に該当する部店.部店ID)
        boolean l_blnEveningSessionCarryOverFlag =
            WEB3IfoDataAdapter.getEveningSessionCarryOverFlag(
                l_request.expirationDateType,
                l_subAccount.getWeb3GenBranch().getBranchId());

        //OP注文マネージャ
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

        //市場
        Market l_market = l_ifoProductImpl.getPrimaryMarket();

        //注文失効日：　@OP注文マネージャ.get注文有効期限(
        //リクエストデータ.注文有効期限,先物OP銘柄.銘柄コード,市場.getMarketCode(),”先物”)の戻り値
        Date l_datExpirationDate = l_orderManager.getExpirationDate(
            l_request.expirationDate,
            l_ifoProductImpl.getProductCode(),
            l_market.getMarketCode(),
            WEB3FuturesOptionDivDef.FUTURES);

        //返済注文内容オブジェクトを生成する。
        WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec =
            WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_trader,
                l_dblLimitPrice,
                l_executionConditionType,
                l_datExpirationDate,
                (SettleContractEntry[]) l_settleContractOrderEntries,
                l_request.orderCondType,
                l_dblStopOrderBasePrice,
                l_dblwLimitPrice,
                l_wLimitExecutionConditionType,
                l_request.expirationDateType,
                l_firstOrderUnitId,
                l_blnEveningSessionCarryOverFlag);

        //先物返済注文の発注審査を実施する。
        // validate先物返済注文
        // [引数]
        // 補助口座：　@get補助口座()の戻り値
        // 返済注文内容：　@create返済注文内容()の戻り値
        // リクエストアダプタ：　@createリクエストアダプタ()の戻り値
        NewOrderValidationResult l_newOrderValidationResult = validateFuturesSettleContractOrder(
            l_subAccount, l_settleContractOrderSpec, l_requestAdapter);
        if (l_newOrderValidationResult.getProcessingResult().isFailedResult())
        {
            //例外をスローする
            log.debug("ProcessingResult() = " + l_newOrderValidationResult.getProcessingResult());
            throw new WEB3BusinessLayerException(
                l_newOrderValidationResult.getProcessingResult().getErrorInfo(), 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        //建玉の合計数量を取得する。
        double l_dblTotalQuantity = l_settleContractOrderSpec.getTotalQuantity();

        //手数料オブジェクトを生成する。
        WEB3GentradeCommission l_commission = new WEB3GentradeCommission();

        //手数料.注文チャネル = this.getログインチャネル()
        l_commission.setOrderChannel(this.getLoginChannel());

        //手数料.証券会社ID = 補助口座.get証券会社()
        l_commission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());

        //手数料.部店ID = 補助口座.get取引店()
        l_commission.setBranchId(l_subAccount.getWeb3GenBranch().getBranchId());

        //手数料.発注日 = 取引時間管理.get発注日()
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        l_commission.setOrderBizDate(new Timestamp(l_datBizDate.getTime()));

        //手数料.取引コード(SONAR) = ”52：返済”
        l_commission.setSonarTradedCode(WEB3TransactionTypeSONARDef.SETTLE_CONTRACT);

        //手数料.手数料商品コード = ”50：先物”
        l_commission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_FUTURES);

        //弁済区分をセットする。
        l_commission.setPayType(WEB3PayTypeDef.OTHER);

        //is指値をセットする。
        l_commission.setIsLimitPrice(l_settleContractOrderSpec.isLimitOrder());
        
        //手数料.原資産銘柄コード = 建玉.get銘柄().get原資産銘柄コード(*2)
        l_commission.setUnderlyingProductCode(
            ((WEB3IfoProductImpl)l_contractImp.getProduct()).getUnderlyingProductCode());

        //手数料.日計り区分 = this.get日計り区分の戻り値
        l_commission.setDayTradeType(this.getDayTradeType(l_settleContractOrderEntries, l_requestAdapter));

        //手数料.数量 = 返済注文内容.getTotalQuantity()
        l_commission.setQuantity(l_dblTotalQuantity);

        // get概算決済損益
        // [引数]
        // 手数料：　@手数料オブジェクト
        // 指値：　@リクエストデータ.確認時単価　@(*1)
        // 補助口座：　@補助口座オブジェクト
        // 先物OP取引銘柄：　@先物OP取引銘柄オブジェクト
        // 返済建玉エントリ[]：   create返済建玉エントリの戻り値
        // 数量： 返済注文内容.getTotalQuantity()
        // リクエストアダプタ：　@createリクエストアダプタ()の戻り値
        // (*1)リクエスト.確認時単価==nullの場合、先に以下の処理を行う。
        // リクエスト.確認時単価：createリクエストアダプタ()の戻り値.get単価()
        double l_dblPrice = 0;
        if (l_request.checkPrice == null)
        {
            l_dblPrice = l_requestAdapter.getPrice();
        }
        else
        {
            l_dblPrice = Double.parseDouble(l_request.checkPrice);
        }
        WEB3IfoEstimateDeliveryAmountCalcResult l_dblEstimateDeliveryAmount =
            getEstimateSettlementIncome(
                l_commission,
                l_dblPrice,
                l_subAccount,
                (WEB3IfoTradedProductImpl)l_tradecProduct,
                l_settleContractOrderEntries,
                l_settleContractOrderSpec.getTotalQuantity(),
                l_requestAdapter);

        // submit返済注文
        //[引数]
        // リクエストアダプタ：　@createリクエストアダプタ()の戻り値
        // 補助口座：　@get補助口座()の戻り値
        // 返済注文内容：　@create返済注文内容()で生成した注文内容。
        // 注文ID：　@リクエストデータ.注文ID　@(*1)
        // 手数料：　@calc概算決済損益()引数の手数料オブジェクト
        // 概算受渡代金計算結果：　@calc概算決済損益()の戻り値
        // (*1)　@リクエストデータ.注文ID == nullの場合
        // 　@　@　@　@createNewOrderId()の戻り値
        long l_lngOrderId = 0;
        if (l_request.orderId != null)
        {
            l_lngOrderId = Long.parseLong(l_request.orderId);
        }
        else
        {
            l_lngOrderId = l_orderMgr.createNewOrderId();
        }
        submitSettleContractOrder(
            l_requestAdapter,
            l_subAccount,
            l_settleContractOrderSpec,
            l_lngOrderId,
            l_commission,
            l_dblEstimateDeliveryAmount);

        l_response = (WEB3FuturesCloseMarginCompleteResponse) l_request.createResponse();
        //レスポンス.更新時間 = 現在日時(GtlUtils.getSystemTimestamp())
        l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();

        //レスポンス.識別番号 = 
        //　@リクエストデータ.注文ID==nullの場合、先物注文マネージャ.createNewOrderID()の戻り値。
        //  リクエストデータ.注文ID!=nullの場合、リクエストデータ.注文ID
        l_response.orderActionId = "" + l_lngOrderId;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (createリクエストアダプタ)<BR>
     * リクエストアダプタのインスタンスを生成する。<BR>
     * <BR>
     * ※処理の詳細はシーケンス図「（先物返済）validate注文１」<BR>
     * 「（先物返済）submit注文１」参照<BR>
     * <BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエストデータ。<BR>
     * @@return WEB3FuturesSettleContractOrderRequestAdapter
     * @@throws WEB3BaseException
     */
    protected WEB3FuturesSettleContractOrderRequestAdapter createRequestAdapter(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        return WEB3FuturesSettleContractOrderRequestAdapter.create(l_request);
    }

    /**
     * (create返済建玉エントリ)<BR>
     * 返済建玉エントリを作成する。<BR>
     * <BR>
     * ※処理の詳細はシーケンス図「（先物返済）validate注文１」<BR>
     * 「（先物返済）submit注文１」参照<BR>
     * <BR>
     * @@param l_requestAdapter - (リクエストアダプタ)<BR>
     * リクエストデータ。<BR>
     * @@param l_closeMarginContractUnits - (返済建玉オブジェクトの配列)<BR>
     * リクエストデータ<BR>
     * @@return WEB3FuturesSettleContractOrderRequestAdapter
     * @@throws WEB3BaseException
     */
    protected SettleContractEntry[] createSettleContractEntry(
        WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter,
        WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSettleContractEntry(" +
            "WEB3FuturesSettleContractOrderRequestAdapter, WEB3FuturesOptionsCloseMarginContractUnit[])";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3FuturesOrderManagerImpl l_orderMgr = (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();

        // [引数]
        // 注文単位ID = 0
        // 注文数量：　@パラメータ.リクエストアダプタ.get注文数量()戻り値
        // 返済建玉[] = パラメータ.返済建玉
        SettleContractEntry[] l_settleContractOrderEntries = l_orderMgr.createSettleContractEntry(
            0, l_requestAdapter.getOrderQuantity(), l_closeMarginContractUnits);

        log.exiting(STR_METHOD_NAME);
        return l_settleContractOrderEntries;
    }

    /**
     * (validate先物返済注文)<BR>
     * 先物返済注文の発注審査を実施する。<BR>
     * <BR>
     * ※処理の詳細はシーケンス図「（先物返済）validate注文１」<BR>
     * 「（先物返済）submit注文１」参照<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_settleContractOrderSpec - (返済注文内容)<BR>
     * 返済注文内容オブジェクト<BR>
     * @@param l_requestAdapter - (リクエストアダプタ)<BR>
     * リクエストアダプタ<BR>
     * @@return NewOrderValidationResult
     * @@throws WEB3BaseException
     */
    protected NewOrderValidationResult validateFuturesSettleContractOrder(
        SubAccount l_subAccount,
        WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec,
        WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateFuturesSettleContractOrder(" +
            "SubAccount, WEB3IfoSettleContractOrderSpec, WEB3FuturesSettleContractOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3FuturesOrderManagerImpl l_orderMgr = (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();

        // [引数]
        // 補助口座：　@get補助口座()の戻り値
        // 返済注文内容：　@create返済注文内容()の戻り値
        NewOrderValidationResult l_validateResult = l_orderMgr.validateFuturesSettleContractOrder(
            (WEB3GentradeSubAccount)l_subAccount, l_settleContractOrderSpec);

        log.exiting(STR_METHOD_NAME);
        return l_validateResult;
    }

    /**
     * (get概算決済損益)<BR>
     * 概算決済損益を取得する。<BR>
     * ※処理の詳細はシーケンス図「（先物返済）validate注文１」<BR>
     *「（先物返済）submit注文１」参照<BR>
     * <BR>
     * @@param l_commision - (手数料)<BR>
     * 手数料<BR>
     * @@param l_dblLimitPrice - (指値)<BR>
     * 指値<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_ifoTradedProduct - (先物OP取引銘柄)<BR>
     * 先物OP取引銘柄<BR>
     * @@param l_settleContractEntry - (返済建玉エントリの配列)<BR>
     * 返済建玉エントリの配列<BR>
     * @@param l_dblQuantity - (数量)<BR>
     * 数量<BR>
     * @@param l_requestAdapter - (リクエストアダプタ)<BR>
     * リクエストアダプタ<BR>
     * @@return WEB3IfoEstimateDeliveryAmountCalcResult
     * @@throws WEB3BaseException
     */
    protected WEB3IfoEstimateDeliveryAmountCalcResult getEstimateSettlementIncome(
        WEB3GentradeCommission l_commision,
        double l_dblLimitPrice,
        SubAccount l_subAccount,
        WEB3IfoTradedProductImpl l_ifoTradedProduct,
        SettleContractEntry[] l_settleContractEntry,
        double l_dblQuantity,
        WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getEstimateSettlementIncome(" +
            "WEB3GentradeCommission, double, SubAccount, WEB3IfoTradedProductImpl," +
            "SettleContractEntry[], double, WEB3FuturesSettleContractOrderRequestAdapter)";

        log.entering(STR_METHOD_NAME);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3FuturesOrderManagerImpl l_orderMgr = (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();

        // [引数]
        // 手数料：　@パラメータ.手数料
        // 指値：　@パラメータ.指値
        // 補助口座：　@パラメータ.補助口座
        // 先物OP取引銘柄：　@パラメータ.先物OP取引銘柄
        // 返済建玉エントリ[]：　@パラメータ.返済建玉エントリ[]
        // 数量： パラメータ.数量
        // 売買：
        // 　@[リクエストアダプタ.get建玉()の戻り値.isLong() == trueの場合]
        // 　@　@SideEnum.BUY
        // 　@[以外]
        // 　@　@SideEnum.SELL
        // isSkip金額チェック：  false
        SideEnum l_dealing = null;
        if (l_requestAdapter.getContract().isLong())
        {
            l_dealing = SideEnum.BUY;
        }
        else
        {
            l_dealing = SideEnum.SELL;
        }

        WEB3IfoEstimateDeliveryAmountCalcResult l_calcResult = l_orderMgr.calcEstimateSettlementIncome(
            l_commision,
            l_dblLimitPrice,
            (WEB3GentradeSubAccount)l_subAccount,
            l_ifoTradedProduct,
            l_settleContractEntry,
            l_dblQuantity,
            l_dealing,
            false);

        log.exiting(STR_METHOD_NAME);
        return l_calcResult;
    }

    /**
     * (create建玉明細)<BR>
     * 返済建玉エントリより建玉明細の一覧を作成する。<BR>
     * <BR>
     * １）　@ArrayListを生成する。<BR>
     * ２）　@時価を取得する。<BR>
     * 　@２－１）　@先物OP時価情報を取得する。<BR>
     * 　@　@パラメータ.先物OP取引銘柄.get時価情報(null)をcallする。<BR>
     * 　@２－２）　@時価を取得する。<BR>
     * 　@　@２－１）の戻り値≠nullの場合、get時価()をcallする。<BR>
     * 　@　@　@※nullの場合、時価を0とする。<BR>
     * <BR>
     * ３）　@パラメータ.返済建玉エントリの要素数分、<BR>
     * 　@以下の処理を実施する。<BR>
     * 　@３－１）　@処理対象の要素.getContractId()の戻り値により、<BR>
     * 　@　@　@　@　@　@先物OP建玉インスタンスを生成する。<BR>
     * <BR>
     * 　@３－２）　@建玉明細インスタンスを生成する。<BR>
     * ３－３）　@生成したインスタンスに以下のプロパティをセットする。<BR>
     * 　@ID = 建玉ＩＤ<BR>
     * 　@建年月日 = 建玉.getOpenDate()<BR>
     * 　@建単価 = 建玉.getContractPrice()<BR>
     * 　@建玉数 = 建玉.getQuantity() - 建玉.getLockedQuantity()<BR>
     * 　@建約定金額 = 建玉.get建約定金額(数量(*1))<BR>
     * 　@建手数料 = 建玉.get建手数料(数量(*1)) + 建玉.get建手数料消費税(数量(*1))<BR>
     * 　@損益 = 建玉.get評価損益(返済単価(*2), 数量(*1))<BR>
     * 　@損益（諸経費込） = 建玉.get評価損益() - (建玉.get建手数料() + 建玉.get建手数料消費税())<BR>
     * 　@返済数量 = SettleContractEntry.getQuantity()（*返済数量）<BR>
     * 　@返済約定数量 = NULL<BR>
     * 　@決済順位 = （SettleContractEntryのindex+1）<BR>
     * 　@立会区分 = 建玉.立会区分 <BR>
     * <BR>
     * @@param l_settleContractEntry - (返済建玉エントリ)<BR>
     * 返済建玉エントリ<BR>
     * @@param l_requestAdapter - (リクエストアダプタ)<BR>
     * リクエストアダプタ<BR>
     * @@param l_ifoTradedProduct - (先物OP取引銘柄)<BR>
     * 先物OP取引銘柄<BR>
     * @@return WEB3FuturesOptionsContractUnit[]
     * @@throws WEB3BaseException
     */
    protected WEB3FuturesOptionsContractUnit[] createContractUnit(
        SettleContractEntry[] l_settleContractEntry,
        WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter,
        WEB3IfoTradedProductImpl l_ifoTradedProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createContractUnit(" +
            "SettleContractEntry[], WEB3FuturesSettleContractOrderRequestAdapter, WEB3IfoTradedProductImpl)";

        log.entering(STR_METHOD_NAME);

        // １）　@ArrayListを生成する。
        List l_lisContractUnits = new ArrayList();

        // ２）　@時価を取得する。
        //　@２－１）　@先物OP時価情報を取得する。
        //　@パラメータ.先物OP取引銘柄.get時価情報(null)をcallする。
        WEB3IfoProductQuote l_productQuote = l_ifoTradedProduct.getCurrentInfo(null);
        double l_dblCurrentPrice = 0;
        // ２－２）　@時価を取得する。
        // 　@２－１）の戻り値≠nullの場合、get時価()をcallする。
        // 　@　@※nullの場合、時価を0とする。
        if (l_productQuote != null)
        {
            l_dblCurrentPrice = l_productQuote.getCurrentPrice();
        }

        // ３）　@パラメータ.返済建玉エントリの要素数分、
        int l_intSettleContractEntry = l_settleContractEntry.length;
        for (int i = 0; i < l_intSettleContractEntry; i++)
        {
            try
            {
                // ３－１）　@処理対象の要素.getContractId()の戻り値により、
                //  　@　@　@　@　@先物OP建玉インスタンスを生成する。
                WEB3IfoContractImpl l_ifoContract = new WEB3IfoContractImpl(
                    l_settleContractEntry[i].getContractId());

                // ３－２）　@建玉明細インスタンスを生成する。
                //建玉明細オブジェクトを生成する。
                WEB3FuturesOptionsContractUnit l_contractUnit = new WEB3FuturesOptionsContractUnit();

                // ３－３）　@生成したインスタンスに以下のプロパティをセットする。
                // ID = 建玉ＩＤ
                l_contractUnit.id = WEB3StringTypeUtility.formatNumber(l_ifoContract.getContractId());

                // 建年月日 = 建玉.getOpenDate()
                l_contractUnit.openDate = l_ifoContract.getOpenDate();

                // 建単価 = 建玉.getContractPrice()
                l_contractUnit.contractPrice = WEB3StringTypeUtility.formatNumber(
                    l_ifoContract.getContractPrice());

                // 建玉数 = 建玉.getQuantity() - 建玉.getLockedQuantity()
                l_contractUnit.contractQuantity = WEB3StringTypeUtility.formatNumber(
                    l_ifoContract.getQuantity() - l_ifoContract.getLockedQuantity());

                // 建約定金額 = 建玉.get建約定金額(数量(*1))
                double l_dblQuantity = l_settleContractEntry[i].getQuantity();
                l_contractUnit.contractExecPrice = WEB3StringTypeUtility.formatNumber(
                    l_ifoContract.getContractExecutedAmount(l_dblQuantity));

                // 建手数料 = 建玉.get建手数料(数量(*1)) + 建玉.get建手数料消費税(数量(*1))
                double l_dblContractCommission = l_ifoContract.getContractCommission(l_dblQuantity);
                double l_dblContractCommissionTax = l_ifoContract.getContractCommissionConsumptionTax(
                    l_dblQuantity);
                BigDecimal l_bdContractCommission = new BigDecimal(l_dblContractCommission + "");
                BigDecimal l_bdContractCommissionTax = new BigDecimal(l_dblContractCommissionTax + "");
                l_contractUnit.contractCommission = WEB3StringTypeUtility.formatNumber(
                    l_bdContractCommission.add(l_bdContractCommissionTax).doubleValue());

                // 損益 = 建玉.get評価損益(返済単価(*2), 数量(*1))
                double l_dblEvaluateIncome = l_ifoContract.getEvaluateIncome(l_dblCurrentPrice, l_dblQuantity);
                l_contractUnit.income = WEB3StringTypeUtility.formatNumber(l_dblEvaluateIncome);

                // 損益（諸経費込） = 建玉.get評価損益() - (建玉.get建手数料() + 建玉.get建手数料消費税())
                BigDecimal l_bdEvaluateIncome = new BigDecimal(l_dblEvaluateIncome + "");
                l_contractUnit.incomeCost = WEB3StringTypeUtility.formatNumber(
                    l_bdEvaluateIncome.subtract(l_bdContractCommission.add(l_bdContractCommissionTax)).doubleValue());

                // 返済数量 = SettleContractEntry.getQuantity()（*返済数量）
                l_contractUnit.contractOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblQuantity);

                // 返済約定数量 = NULL
                l_contractUnit.contractExecQuantity = null;

                // 決済順位 = （SettleContractEntryのindex+1）
                l_contractUnit.settlePriority = String.valueOf(i + 1);

                // 立会区分 = 建玉.立会区分
                IfoContractRow l_ifoContractRow = (IfoContractRow)l_ifoContract.getDataSourceObject();
                l_contractUnit.sessionType = l_ifoContractRow.getSessionType();

                // ３－４）　@ArrayListにプロパティセットしたインスタンスを追加する。
                l_lisContractUnits.add(l_contractUnit);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
        log.exiting(STR_METHOD_NAME);

        return (WEB3FuturesOptionsContractUnit[])l_lisContractUnits.toArray(
            new WEB3FuturesOptionsContractUnit[l_lisContractUnits.size()]);
    }

    /**
     * (submit返済注文)<BR>
     * 先物返済注文を登録する。<BR>
     * <BR>
     * ※処理の詳細はシーケンス図「（先物返済）submit注文２」参照<BR>
     * <BR>
     * @@param l_requestAdapter - (リクエストアダプタ)<BR>
     * リクエストアダプタ<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_orderSpec - (返済注文内容)<BR>
     * 返済注文内容<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID<BR>
     * @@param l_commision - (手数料)<BR>
     * 手数料<BR>
     * @@param l_amountCalcResult - (概算受渡代金計算結果)<BR>
     * 概算受渡代金計算結果<BR>
     * @@throws WEB3BaseException
     */
    protected void submitSettleContractOrder(
        WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter,
        SubAccount l_subAccount,
        WEB3IfoSettleContractOrderSpec l_orderSpec,
        long l_lngOrderId,
        WEB3GentradeCommission l_commision,
        WEB3IfoEstimateDeliveryAmountCalcResult l_amountCalcResult) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitSettleContractOrder(" +
            "WEB3FuturesSettleContractOrderRequestAdapter, " +
            "SubAccount, " +
            "WEB3IfoSettleContractOrderSpec, " +
            "long, " +
            "WEB3GentradeCommission, " +
            "WEB3IfoEstimateDeliveryAmountCalcResult)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3FuturesOrderManagerImpl l_orderMgr = (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();
        //インタセプタを生成する。
        WEB3IfoSettleContractUpdateInterceptor l_settleContractUpdateInterceptor =
            new WEB3IfoSettleContractUpdateInterceptor(l_orderSpec);

        //インタセプタ.手数料 = （calc概算決済損益()引数の通常手数料オブジェクト）
        l_settleContractUpdateInterceptor.setCommision(l_commision);

        //インタセプタ.概算決済損益計算結果 = （calc概算決済損益()の戻り値）
        l_settleContractUpdateInterceptor.setEstimateDeliveryAmountCalcResult(l_amountCalcResult);

        WEB3FuturesCloseMarginCompleteRequest l_request = (WEB3FuturesCloseMarginCompleteRequest)
            l_requestAdapter.request;
        //インタセプタ.発注条件 = リクエストデータ.発注条件区分
        l_settleContractUpdateInterceptor.setOrderCond(l_request.orderCondType);

        //（*発注条件区分で逆指値/W指値を判定）
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderSpec.getOrderCond()))
        {
            //インタセプタ.発注条件演算子 = リクエストデータ.発注条件演算子　@
            l_settleContractUpdateInterceptor.setOrderCondOperator(l_request.stopOrderCondOperator);

            //インタセプタ.逆指値基準値タイプ = 0
            //l_settleContractUpdateInterceptor.setStopOrderBasePriceType("0");
            l_settleContractUpdateInterceptor.setStopOrderBasePriceType(WEB3StopBasePriceTypeDef.DEFAULT);

            //インタセプタ.逆指値基準値 = リクエストデータ.発注条件単価
            l_settleContractUpdateInterceptor.setStopOrderBasePrice(Double.parseDouble(l_request.stopOrderCondPrice));
        }
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderSpec.getOrderCond()))
        {
            //インタセプタ.発注条件演算子 = リクエストデータ.発注条件演算子

            l_settleContractUpdateInterceptor.setOrderCondOperator(l_request.wlimitOrderCondOperator);

            l_settleContractUpdateInterceptor.setStopOrderBasePriceType(WEB3StopBasePriceTypeDef.DEFAULT);

            l_settleContractUpdateInterceptor.setStopOrderBasePrice(Double.parseDouble(l_request.wlimitOrderCondPrice));
            //インタセプタ.(W指値)訂正指値 = 返済注文内容.(W指値)訂正指値
            l_settleContractUpdateInterceptor.setWLimitPriceChange(l_orderSpec.getWLimitPriceChange());
        }

        l_settleContractUpdateInterceptor.setSettleSequence(l_request.closingOrder);

        //インタセプタ.立会区分 = 取引時間管理.get立会区分の戻り値
        l_settleContractUpdateInterceptor.setSessionType(WEB3GentradeTradingTimeManagement.getSessionType());

        l_orderMgr.setThreadLocalPersistenceEventInterceptor(l_settleContractUpdateInterceptor);

        // [引数]
        // 補助口座：　@パラメータ.補助口座
        // 返済注文内容：　@パラメータ.返済注文内容
        // 注文ＩＤ：　@パラメータ.注文ID
        // 取引パスワード：　@パラメータ.リクエストアダプタ.リクエストデータ.暗証番号
        // isSkip発注審査：　@true
        OrderSubmissionResult l_result = l_orderMgr.submitSettleContractOrder(
            l_subAccount, //補助口座
            l_orderSpec, //返済注文内容
            l_lngOrderId, //注文ＩＤ
            l_request.password, //取引パスワード
            true);

        if (l_result.getProcessingResult().isFailedResult())
        {
            //例外をスローする
            log.debug("ProcessingResult() = " + l_result.getProcessingResult());
            throw new WEB3BusinessLayerException(
                l_result.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set単価)<BR>
     * 何もせずにreturnする。（カラ実装）<BR>
     *
     * @@param l_requestAdapter - (先物返済注文リクエストアダプタ)<BR>
     * 先物返済注文リクエストアダプタ。<BR>
     * @@param l_response - (レスポンス)<BR>
     * レスポンス。<BR>
     * @@throws WEB3BaseException
     */
    protected void setPrice(
        WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter,
        WEB3GenResponse l_response) throws WEB3BaseException
    {
        return;
    }

    /**
     * (get日計り区分)<BR>
     * 日計り区分を取得する。 <BR>
     * <BR>
     * 先物注文マネージャ.get日計り区分()をcallする。<BR>
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
        WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getDayTradeType(SettleContractEntry[], WEB3FuturesSettleContractOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3FuturesOrderManagerImpl l_orderMgr =
            (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();
        //先物注文マネージャ.get日計り区分()をcallする。
        String l_strDayTradeType = l_orderMgr.getDayTradeType(l_settleContractOrderEntries);

        log.exiting(STR_METHOD_NAME);
        return l_strDayTradeType;
    }
}
@
