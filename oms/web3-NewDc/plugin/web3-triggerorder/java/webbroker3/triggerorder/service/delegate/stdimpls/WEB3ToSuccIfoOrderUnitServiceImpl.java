head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.53.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccIfoOrderUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連続注文先物OP発注一件サービスImpl(WEB3ToSuccIfoOrderUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/05/06 于瀟(中訊) 新規作成モデル311,339,344,347,348,349
Revision History : 2008/05/29 トウ鋒鋼(中訊) 仕様変更モデルNo.354
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PayTypeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoOpenContractOrderSpec;
import webbroker3.ifo.WEB3IfoOpenContractUpdateInterceptor;
import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoSettleContractUpdateInterceptor;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvIfoClosingContractSpecRow;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationIfoOrderUpdateService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccIfoOrderUnitService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (連続注文先物OP発注一件サービスImpl)<BR>
 * 連続注文先物OP発注一件サービス実装クラス。<BR>
 * <BR>
 * @@author 于瀟
 * @@version 1.0
 */
public class WEB3ToSuccIfoOrderUnitServiceImpl implements WEB3ToSuccIfoOrderUnitService
{
    /**
     * ログオブジェクト
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3ToSuccIfoOrderUnitServiceImpl.class);

    /**
     * @@roseuid 481EA53F0017
     */
    public WEB3ToSuccIfoOrderUnitServiceImpl()
    {

    }

    /**
     * (submit先物新規建注文)<BR>
     * 先物新規建注文を発注する。<BR>
     * シーケンス図「（連続注文先物OP発注一件サービス）submit先物新規建注文」を参照。<BR>
     * @@param l_rsvIfoOrderUnit - (先物OP予約注文単位)<BR>
     * 先物OP予約注文単位オブジェクト。<BR>
     * @@roseuid 47DF5498018A
     */
    public void submitFuturesOpenContractOrder(WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitFuturesOpenContractOrder(WEB3ToSuccIfoOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        //エラーコード
        String l_strErrorCode = null;

        //先物OP予約注文更新サービス
        WEB3ToSuccReservationIfoOrderUpdateService l_orderUpdateService =
            (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                WEB3ToSuccReservationIfoOrderUpdateService.class);

        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = null;
        WEB3GentradeSubAccount l_subAccount = null;
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3FuturesOrderManagerImpl l_orderManager =
            (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();
        try
        {
            //get発注日(確認時発注日 : Date, 立会区分 : String)
            //発注日／立会時間帯のチェックを行う。
            //（予約注文登録時の発注日／立会時間帯と、
            //現在日時から求めた発注日／立会時間帯が異なる場合は発注エラーとする）
            //[引数]
            //　@確認時発注日 ： 引数の先物OP予約注文単位.発注日
            //　@立会区分 ： 引数の先物OP予約注文単位.立会区分
            l_rsvIfoOrderUnitRow = (RsvIfoOrderUnitRow)l_rsvIfoOrderUnit.getDataSourceObject();
            WEB3GentradeTradingTimeManagement.getOrderBizDate(
                WEB3DateUtility.getDate(l_rsvIfoOrderUnitRow.getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD),
                l_rsvIfoOrderUnitRow.getSessionType());

            //分岐フロー：　@予約注文単位.取引者ID≠nullの場合のみ
            WEB3GentradeTrader l_trader = null;
            if (!l_rsvIfoOrderUnitRow.getTraderIdIsNull())
            {
                //扱者（代理入力者）オブジェクトを取得する。
                //引数は以下の通りにセットする。
                //取引者ID：　@予約注文単位.取引者ID
                FinObjectManager l_finMng = GtlUtils.getFinObjectManager();
                l_trader = (WEB3GentradeTrader)l_finMng.getTrader(l_rsvIfoOrderUnitRow.getTraderId());
            }

            //補助口座を取得する
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            l_subAccount = (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                l_rsvIfoOrderUnit.getAccountId(),
                l_rsvIfoOrderUnitRow.getSubAccountId());

            //[create新規建注文内容()に指定する引数]
            //証券会社コ ード ： 予約注文単位.部店IDから取得される証券会社コード
            //扱者 ： 予約注文単位.取引者ID==nullの場合、null
            //          予約注文単位.取引者ID<>nullの場合、取得した扱者オブジェクト
            //is買建 ： 予約注文単位.is買注文()
            //市場コード ： 予約注文単位.get市場().市場コード
            //銘柄 ： 予約注文単位.get銘柄()
            //数量 ： 予約注文単位.注文数量
            //指値 ： 予約注文単位.get予約注文執行単価()
            //執行条件   ： "条件なし"
            //注文失効日：予約注文単位.注文失効日
            //発注条件   ："条件なし"
            //逆指値基準値       ： 0
            //（W指値）訂正指値 ： 0
            //（W指値）執行条件 ： null
            //注文期限区分             ： 予約注文単位.注文期限区分
            //初回注文の注文単位ID ： 予約注文単位.初回注文の注文単位ID
            Long l_firstOrderUnitId = null;
            if (!l_rsvIfoOrderUnitRow.getFirstOrderUnitIdIsNull())
            {
                l_firstOrderUnitId = new Long(l_rsvIfoOrderUnitRow.getFirstOrderUnitId());
            }
            //夕場前繰越対象フラグ   ： 予約注文単位.夕場前繰越対象フラグ
            boolean l_blnEveningSessionCarryoverFlag = false;
            if (BooleanEnum.TRUE.equals(l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag()))
            {
                l_blnEveningSessionCarryoverFlag = true;
            }

            String l_strInstitutionCode =
                l_accountManager.getBranch(l_rsvIfoOrderUnitRow.getBranchId()).getInstitution().getInstitutionCode();

            //create新規建注文内容
            WEB3IfoOpenContractOrderSpec l_openContractOrderSpec =
                WEB3IfoOpenContractOrderSpec.createOpenContractOrderSpec(
                    l_strInstitutionCode,
                    l_trader,
                    l_rsvIfoOrderUnit.isBuyOrder(),
                    l_rsvIfoOrderUnit.getMarket().getMarketCode(),
                    (WEB3IfoProductImpl)l_rsvIfoOrderUnit.getProduct(),
                    l_rsvIfoOrderUnit.getQuantity(),
                    l_rsvIfoOrderUnit.getRsvOrderExecPrice(),
                    IfoOrderExecutionConditionType.NONE,
                    l_rsvIfoOrderUnit.getExpirationTimestamp(),
                    WEB3OrderingConditionDef.DEFAULT,
                    0,
                    0,
                    null,
                    l_rsvIfoOrderUnit.getExpirationDateType(),
                    l_firstOrderUnitId,
                    l_blnEveningSessionCarryoverFlag);

            //validate先物新規建注文
            //引数は以下の通りに設定する。
            //補助口座：　@取得した補助口座オブジェクト
            //新規建注文内容：　@作成した新規建注文内容オブジェクト
            //注文単位：null
            NewOrderValidationResult l_orderValidationResult =
                l_orderManager.validateFuturesOpenContractOrder(
                    l_subAccount,
                    l_openContractOrderSpec,
                    null);

            //{validate先物新規建注文()}が失敗の場合。
            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("{validate先物新規建注文()}が失敗の場合。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "{validate先物新規建注文()}が失敗の場合。");
            }

            //手数料オブジェクトを生成する。
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            //(*)手数料オブジェクトに以下の通りプロパティをセットする。
            //手数料.注文チャネル = 予約注文単位.初回注文の注文チャネル
            //手数料.証券会社コード = 予約注文単位.部店IDから取得される証券会社コード
            //手数料.部店ID = 予約注文単位.部店ID
            //手数料.発注日 = 予約注文単位.発注日
            //手数料.取引コード(SONAR) = ”51：建”
            //手数料.手数料商品コード = ”50：先物”
            //手数料.弁済区分 = ”00：その他”
            //手数料.is指値=新規建注文内容.isLimitOrder()
            //手数料.原資産銘柄コード = 予約注文単位.get銘柄().get原資産銘柄コード()
            //手数料.数量=予約注文単位.注文数量
            Timestamp l_tsOrderBizDate = new Timestamp(
                WEB3DateUtility.getDate(l_rsvIfoOrderUnitRow.getBizDate(),
                WEB3GentradeTimeDef.DATE_FORMAT_YMD).getTime());
            String l_strOpenContract = WEB3TransactionTypeSONARDef.OPEN_CONTRACT;
            String l_strCommissionProductCode = WEB3CommisionProductCodeDef.INDEX_FUTURES;
            l_commission.setOrderChannel(l_rsvIfoOrderUnitRow.getOrderChanel());
            l_commission.setInstitutionCode(l_strInstitutionCode);
            l_commission.setBranchId(l_rsvIfoOrderUnit.getBranchId());
            l_commission.setOrderBizDate(l_tsOrderBizDate);
            l_commission.setSonarTradedCode(l_strOpenContract);
            l_commission.setCommissionProductCode(l_strCommissionProductCode);
            l_commission.setPayType(WEB3PayTypeDef.OTHER);
            l_commission.setIsLimitPrice(l_openContractOrderSpec.isLimitOrder());
            l_commission.setUnderlyingProductCode(
                ((WEB3IfoProductImpl)l_rsvIfoOrderUnit.getProduct()).getUnderlyingProductCode());
            l_commission.setQuantity(l_rsvIfoOrderUnit.getQuantity());

            //calc概算建代金
            //[calc概算建代金()に指定する引数]
            //手数料    ： 作成した手数料オブジェクト
            //計算単価 ： 予約注文単位.get予約注文執行単価()
            //補助口座 ： 取得した補助口座オブジェクト
            //先物OP取引銘柄 ： 予約注文単位.get取引銘柄()
            //数量 ： 予約注文単位.注文数量
            //isSkip金額チェック ：　@false
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult =
                l_orderManager.calcEstimatePrice(
                    l_commission,
                    l_rsvIfoOrderUnit.getRsvOrderExecPrice(),
                    l_subAccount,
                    (WEB3IfoTradedProductImpl)l_rsvIfoOrderUnit.getTradedProduct(),
                    l_rsvIfoOrderUnit.getQuantity(),
                    false);

            //先物OP新規建更新インタセプタを作成する。
            //[引数]
            //先物OP新規建注文内容 ： 作成した新規建注文内容オブジェクト
            WEB3IfoOpenContractUpdateInterceptor l_ifoOpenContractUpdateInterceptor =
                new WEB3IfoOpenContractUpdateInterceptor(l_openContractOrderSpec);

            //(*)インタセプタオブジェクトのプロパティに以下の値をセットする。
            //インタセプタ.手数料 = 作成した手数料オブジェクト
            l_ifoOpenContractUpdateInterceptor.setCommision(l_commission);
            //インタセプタ.概算受渡代金計算結果 = calc概算建代金()の戻り値
            l_ifoOpenContractUpdateInterceptor.setEstimateDeliveryAmountCalcResult(
                l_ifoEstimateDeliveryAmountCalcResult);
            //インタセプタ.発注条件 = "条件なし"
            l_ifoOpenContractUpdateInterceptor.setOrderCond(WEB3OrderingConditionDef.DEFAULT);
            //インタセプタ.立会区分 = 予約注文単位.立会区分
            l_ifoOpenContractUpdateInterceptor.setSessionType(l_rsvIfoOrderUnitRow.getSessionType());
            //インタセプタ.扱者コード（SONAR） =　@予約注文単位.扱者コード（SONAR）
            l_ifoOpenContractUpdateInterceptor.setSonarTraderCode(l_rsvIfoOrderUnitRow.getSonarTraderCode());
            //インタセプタ.初回注文の注文チャネル = 予約注文単位.初回注文の注文チャネル
            l_ifoOpenContractUpdateInterceptor.setOrderChanel(l_rsvIfoOrderUnitRow.getOrderChanel());
            //インタセプタ.注文経路区分 = 予約注文単位.注文経路区分
            l_ifoOpenContractUpdateInterceptor.setOrderRootDiv(l_rsvIfoOrderUnitRow.getOrderRootDiv());

            //validate取引余力
            //証拠金のチェックを行う。
            //[引数]
            //補助口座 ： 取得した補助口座オブジェクト
            //注文内容インタセプタ[] ： 先物OP新規建更新インタセプタを要素とした配列
            //注文内容[] ： 新規建注文内容を要素とした配列
            //注文種別 ：
            //　@引数.注文内容[0].isBuyToOpenOrder() == trueの場合、"先物新規買建"
            //　@以外の場合、"先物新規売建"
            //余力更新フラグ ： false
            WEB3TPTradingPowerService l_tradingPowerService =
                (WEB3TPTradingPowerService)Services.getService(
                    WEB3TPTradingPowerService.class);

            WEB3IfoOpenContractUpdateInterceptor[] l_ifoOpenContractUpdateInterceptors =
                {l_ifoOpenContractUpdateInterceptor};
            WEB3IfoOpenContractOrderSpec[] l_orderSpecs = {l_openContractOrderSpec};

            OrderTypeEnum l_orderTypeEnum = null;
            if (l_orderSpecs[0].isBuyToOpenOrder())
            {
                l_orderTypeEnum = OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN;
            }
            else
            {
                l_orderTypeEnum = OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN;
            }
            WEB3TPTradingPowerResult l_tradingPowerResult = l_tradingPowerService.validateTradingPower(
                l_subAccount,
                l_ifoOpenContractUpdateInterceptors,
                l_orderSpecs,
                l_orderTypeEnum,
                false);

            OrderSubmissionResult l_orderSubmissionResult = null;
            //取引余力結果.is判定フラグ( )==trueの場合
            if (l_tradingPowerResult.isResultFlg())
            {
                //setThreadLocalPersistenceEventInterceptor(arg0 : IfoOrderManagerPersistenceEventInterceptor)
                //注文マネージャのThreadLocalに、先物OP新規建更新インタセプタをセットする。
                //[引数]
                //arg0（先物OP新規建更新インタセプタ）：　@生成した先物OP新規建更新インタセプタ
                l_orderManager.setThreadLocalPersistenceEventInterceptor(
                    l_ifoOpenContractUpdateInterceptor);

                //submitOpenContractOrder
                //新規建注文を登録する。
                String l_strTradingPasswood =
                    l_subAccount.getMainAccount().getTradingPassword();
                WEB3Crypt l_crypt = new WEB3Crypt();
                String l_strDecryptPassword = l_crypt.decrypt(l_strTradingPasswood);

                l_orderSubmissionResult = l_orderManager.submitOpenContractOrder(
                    l_subAccount,
                    l_openContractOrderSpec,
                    l_rsvIfoOrderUnit.getOrderId(),
                    l_strDecryptPassword,
                    true);
            }
            //取引余力結果.is判定フラグ( )==falseの場合
            //余力エラーの情報から、予約注文単位に記録するエラーコードを決定する。
            //引数は以下の通りにセットする。
            //取引余力結果：　@validate取引余力()の戻り値
            //補助口座：　@取得した補助口座オブジェクト
            else
            {
                log.debug("取引余力チェックエラー。");
                l_orderManager.throwTpErrorInfo(l_tradingPowerResult, l_subAccount);
            }

            //実行結果に応じ、予約注文単位をupdateする。
            if (l_orderSubmissionResult.getProcessingResult().isSuccessfulResult())
            {
                //set発注済To予約注文単位(ProductTypeEnum, long)(連続注文マネージャImpl::set発注済To予約注文単位)
                //引数は以下の通りにセットする。
                //銘柄タイプ ： 引数の予約注文単位.銘柄タイプ
                //注文ID ： 引数の予約注文単位.注文ID
                WEB3ToSuccOrderManagerImpl l_orderManagerImpl =
                    (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
                l_orderManagerImpl.setOrderedToOrderUnit(
                    l_rsvIfoOrderUnit.getProductType(),
                    l_rsvIfoOrderUnit.getOrderId());
            }
            //実行結果エラー。
            else
            {
                log.debug("{submitFuturesOpenContractOrder()}がエラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "{submitFuturesOpenContractOrder()}がエラー。");
            }
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            if (l_ex instanceof WEB3BaseException)
            {
                l_strErrorCode = ((WEB3BaseException)l_ex).getErrorInfo().getErrorCode();
            }
            else if (l_ex instanceof WEB3BaseRuntimeException)
            {
                l_strErrorCode = ((WEB3BaseRuntimeException)l_ex).getErrorInfo().getErrorCode();
            }
            else if (l_ex instanceof NotFoundException)
            {
                l_strErrorCode = WEB3ErrorCatalog.SYSTEM_ERROR_80005.getErrorCode();
            }
            else
            {
                l_strErrorCode = WEB3ErrorCatalog.SYSTEM_ERROR_80002.getErrorCode();
            }

            //invalidate予約注文単位(先物OP予約注文単位Row, String)
            //[引数]
            //　@先物OP予約注文単位行 ： 引数の予約注文単位の行オブジェクト
            //　@発生エラーコード ： 発生した例外オブジェクトのErrorInfo.error_code
            l_orderUpdateService.invalidateOrderUnit(
                l_rsvIfoOrderUnitRow,
                l_strErrorCode);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (submit先物返済注文)<BR>
     * 先物返済注文を発注する。<BR>
     * シーケンス図「（連続注文先物OP発注一件サービス）submit先物返済注文」を参照。<BR>
     * ==========================================================<BR>
     * シーケンス図 ：(（連続注文先物OP発注一件サービス）submit先物返済注文)<BR>
     * 具体位置：(get先物OP予約建玉返済指定情報一覧)<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag: BUSINESS_ERROR_02339<BR>
     * ==========================================================<BR>
     * @@param l_rsvIfoOrderUnit - (先物OP予約注文単位)<BR>
     * 先物OP予約注文単位オブジェクト。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47DF5498019A
     */
    public void submitFuturesSettleContractOrder(WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitFuturesSettleContractOrder(WEB3ToSuccIfoOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        //エラーコード
        String l_strErrorCode = null;

        //先物予約注文単位
        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = null;

        //先物予約注文更新サービス
        WEB3ToSuccReservationIfoOrderUpdateService l_orderUpdateService =
            (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                WEB3ToSuccReservationIfoOrderUpdateService.class);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeSubAccount l_subAccount = null;
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3FuturesOrderManagerImpl l_orderManager =
            (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();

        try
        {
            //get発注日(確認時発注日 : Date, 立会区分 : String)
            //確認時発注日 ： 引数の先物OP予約注文単位.発注日
            //立会区分 ： 引数の先物OP予約注文単位.立会区分
            l_rsvIfoOrderUnitRow = (RsvIfoOrderUnitRow)l_rsvIfoOrderUnit.getDataSourceObject();
            WEB3GentradeTradingTimeManagement.getOrderBizDate(
                WEB3DateUtility.getDate(l_rsvIfoOrderUnitRow.getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD),
                l_rsvIfoOrderUnitRow.getSessionType());

            //（*）補助口座を取得する
            WEB3GentradeAccountManager l_accountMananger = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            l_subAccount = (WEB3GentradeSubAccount)l_accountMananger.getSubAccount(
                l_rsvIfoOrderUnit.getAccountId(),
                l_rsvIfoOrderUnit.getSubAccountId());

            //get先物OP予約建玉返済指定情報一覧( )
            RsvIfoClosingContractSpecRow[] l_rsvIfoClosingContractSpecs = l_rsvIfoOrderUnit.getContractsToClose();
            if (l_rsvIfoClosingContractSpecs == null)
            {
                log.debug("前提注文による約定建の情報、または返済指定データが存在しません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02339,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "前提注文による約定建の情報、または返済指定データが存在しません。");
            }

            //create決済指定エントリ(先物OP予約建玉返済指定情報行[])
            //予約返済指定情報一覧 ： get先物OP予約建玉返済指定情報一覧()の戻り値
            SettleContractEntry[] l_settleContractEntries =
                this.createSettleContractEntries(l_rsvIfoClosingContractSpecs);

            //get予約注文執行単価( )
            double l_dblRsvOrderExecPrice = l_rsvIfoOrderUnit.getRsvOrderExecPrice();

            //分岐フロー：　@予約注文単位.取引者ID≠nullの場合のみ
            WEB3GentradeTrader l_trader = null;
            if (!l_rsvIfoOrderUnitRow.getTraderIdIsNull())
            {
                //getTrader(取引者ID : long)
                //取引者ID ： 予約注文単位.取引者ID
                FinObjectManager l_finMng = GtlUtils.getFinObjectManager();
                l_trader = (WEB3GentradeTrader)l_finMng.getTrader(l_rsvIfoOrderUnit.getTraderId());
            }

            //[create返済注文内容( )：引数設定仕様]
            //証券会社コード ： 予約注文単位.部店IDから取得される証券会社コード
            //扱者 ： 予約注文単位.取引者ID == nullの場合、null
            //          予約注文単位.取引者ID <> nullの場合、取得した扱者オブジェクト
            //指値 ： 予約注文単位.get予約注文執行単価()の戻り値
            //執行条件 ： "条件なし"
            //注文失効日 ： 予約注文単位.注文失効日付
            //返済建玉エントリ ： create決済指定エントリ()の戻り値
            //発注条件 ： "条件なし"
            //逆指値基準値 ： 0
            //（W指値）訂正指値 ： 0
            //（W指値）執行条件 ： null
            //注文期限区分 ： 予約注文単位.注文期限区分
            //初回注文の注文単位ID ： 予約注文単位.初回注文の注文単位ID
            //夕場前繰越対象フラグ ： 予約注文単位.夕場前繰越対象フラグ
            Long l_firstOrderUnitId = null;
            if (!l_rsvIfoOrderUnitRow.getFirstOrderUnitIdIsNull())
            {
                l_firstOrderUnitId = new Long(l_rsvIfoOrderUnitRow.getFirstOrderUnitId());
            }

            //証券会社コード
            String l_strInstitutionCode =
                l_accountMananger.getBranch(l_rsvIfoOrderUnitRow.getBranchId()).getInstitution().getInstitutionCode();

            //夕場前繰越対象フラグ
            boolean l_blnEveningSessionCarryoverFlag = false;
            if (BooleanEnum.TRUE.equals(l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag()))
            {
                l_blnEveningSessionCarryoverFlag = true;
            }

            //create返済注文内容
            WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec =
                WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
                    l_strInstitutionCode,
                    l_trader,
                    l_dblRsvOrderExecPrice,
                    IfoOrderExecutionConditionType.NONE,
                    l_rsvIfoOrderUnit.getExpirationTimestamp(),
                    l_settleContractEntries,
                    WEB3OrderingConditionDef.DEFAULT,
                    0,
                    0,
                    null,
                    l_rsvIfoOrderUnit.getExpirationDateType(),
                    l_firstOrderUnitId,
                    l_blnEveningSessionCarryoverFlag);

            //validate返済注文(補助口座 : SubAccount, 返済注文内容 : IfoSettleContractOrderSpec)
            //補助口座 ： 取得した補助口座オブジェクト
            //先物OP返済注文内容 ： 作成した先物OP返済注文内容オブジェクト
            NewOrderValidationResult l_orderValidationResult =
                l_orderManager.validateSettleContractOrder(
                    l_subAccount,
                    l_settleContractOrderSpec);
            //{validate返済注文()}が失敗の場合。
            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("{validate返済注文()}が失敗の場合。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "{validate返済注文()}が失敗の場合。");
            }

            //手数料オブジェクトを生成する。
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();

            // (*)手数料オブジェクトに以下のプロパティを設定する。
            //注文チャネル = 予約注文単位.初回注文の注文チャネル
            //証券会社コード = 予約注文単位.部店IDから取得される証券会社コード
            //部店ID = 予約注文単位.部店ID
            //発注日 = 予約注文単位.発注日
            //取引コード(SONAR) = ”52：返済”
            //手数料商品コード = ”50：先物”
            //弁済区分 = ”00：その他”
            //is指値 = 返済注文内容.isLimitOrder()
            //原資産銘柄コード = 予約注文単位.get銘柄().get原資産銘柄コード()
            //日計り区分 = 先物注文マネージャ.get日計り区分()(*1)
            //数量 = 返済注文内容.getTotalQuantity()
            //(*1) [引数設定仕様]
            //create決済指定エントリ()の戻り値
            Timestamp l_tsOrderBizDate = new Timestamp(
                WEB3DateUtility.getDate(l_rsvIfoOrderUnitRow.getBizDate(),
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD).getTime());
            String l_strOpenContract = WEB3TransactionTypeSONARDef.SETTLE_CONTRACT;
            String l_strCommissionProductCode = WEB3CommisionProductCodeDef.INDEX_FUTURES;
            l_commission.setOrderChannel(l_rsvIfoOrderUnitRow.getOrderChanel());
            l_commission.setInstitutionCode(l_strInstitutionCode);
            l_commission.setBranchId(l_rsvIfoOrderUnit.getBranchId());
            l_commission.setOrderBizDate(l_tsOrderBizDate);
            l_commission.setSonarTradedCode(l_strOpenContract);
            l_commission.setCommissionProductCode(l_strCommissionProductCode);
            l_commission.setPayType(WEB3PayTypeDef.OTHER);
            l_commission.setIsLimitPrice(l_settleContractOrderSpec.isLimitOrder());
            l_commission.setUnderlyingProductCode(
                ((WEB3IfoProductImpl)l_rsvIfoOrderUnit.getProduct()).getUnderlyingProductCode());
            l_commission.setDayTradeType(l_orderManager.getDayTradeType(l_settleContractEntries));
            l_commission.setQuantity(l_settleContractOrderSpec.getTotalQuantity());

            WEB3IfoPositionManagerImpl l_positionManager =
                (WEB3IfoPositionManagerImpl)l_tradingModule.getPositionManager();
            long l_lngContractId = l_settleContractEntries[0].getContractId();
            //建玉は返済建玉エントリ[0].建玉IDから生成した建玉オブジェクト
            WEB3IfoContractImpl l_contract = (WEB3IfoContractImpl)l_positionManager.getContract(l_lngContractId);

            //[calc概算決済損益( )：引数設定仕様]
            //手数料 ： 作成した手数料オブジェクト
            //指値 ： 返済注文内容.getLimitPrice()
            //補助口座 ： 取得した補助口座オブジェクト
            //先物OP取引銘柄 ： 予約注文単位.get取引銘柄()
            //返済建玉エントリ[] ： create決済指定エントリ()の戻り値
            //数量 ： 返済注文内容.getTotalQuantity()
            //売買 ： (*)
            // 建玉.isLong() == trueの場合、SideEnum.BUY
            // 建玉.isLong() == falseの場合、SideEnum.SELL
            //isSkip金額チェック： false（スキップしない）
            //(*)建玉は返済建玉エントリ[0].建玉IDから生成した建玉オブジェクト
            SideEnum l_side = null;
            if (l_contract.isLong())
            {
            	l_side = SideEnum.BUY;
            }
            else
            {
            	l_side = SideEnum.SELL;
            }
            WEB3IfoEstimateDeliveryAmountCalcResult l_amountCalcResult =
                l_orderManager.calcEstimateSettlementIncome(
                    l_commission,
                    l_settleContractOrderSpec.getLimitPrice(),
                    l_subAccount,
                    (WEB3IfoTradedProductImpl)l_rsvIfoOrderUnit.getTradedProduct(),
                    l_settleContractEntries,
                    l_settleContractOrderSpec.getTotalQuantity(),
                    l_side,
                    false);

            //先物OP返済更新インタセプタ(先物OP返済注文内容 : 返済注文内容)
            //先物OP返済注文内容 ： 作成した先物OP返済注文内容オブジェクト
            WEB3IfoSettleContractUpdateInterceptor l_settleContractUpdateInterceptor =
                new WEB3IfoSettleContractUpdateInterceptor(l_settleContractOrderSpec);

            //(*)インタセプタにプロパティをセットする。
            //[設定する各プロパティ]
            //手数料 ： 作成した手数料オブジェクト
            l_settleContractUpdateInterceptor.setCommision(l_commission);
            //概算受渡代金計算結果 ： calc概算決済損益()の戻り値
            l_settleContractUpdateInterceptor.setEstimateDeliveryAmountCalcResult(l_amountCalcResult);
            //発注条件 ： "条件なし"
            l_settleContractUpdateInterceptor.setOrderCond(WEB3OrderingConditionDef.DEFAULT);
            //決済順序 ： 予約注文単位.決済順序
            l_settleContractUpdateInterceptor.setSettleSequence(l_rsvIfoOrderUnitRow.getClosingOrder());
            //立会区分 ： 予約注文単位.立会区分
            l_settleContractUpdateInterceptor.setSessionType(l_rsvIfoOrderUnitRow.getSessionType());
            //扱者コード（SONAR） ： 予約注文単位.扱者コード（SONAR）
            l_settleContractUpdateInterceptor.setSonarTraderCode(l_rsvIfoOrderUnitRow.getSonarTraderCode());
            //初回注文の注文チャネル = 予約注文単位.初回注文の注文チャネル
            l_settleContractUpdateInterceptor.setOrderChanel(l_rsvIfoOrderUnitRow.getOrderChanel());
            //注文経路区分 = 予約注文単位.注文経路区分
            l_settleContractUpdateInterceptor.setOrderRootDiv(l_rsvIfoOrderUnitRow.getOrderRootDiv());

            //setThreadLocalPersistenceEventInterceptor(arg0 : IfoOrderManagerPersistenceEventInterceptor)
            //arg0（先物OP返済更新インタセプタ） ： 生成した先物OP返済更新インタセプタ
            l_orderManager.setThreadLocalPersistenceEventInterceptor(l_settleContractUpdateInterceptor);

            //[submitSettleContractOrder()：引数設定仕様]
            //arg0（補助口座） ： 取得した補助口座オブジェクト
            //arg1（返済注文内容） ： create返済注文内容()の戻り値
            //arg2（注文ＩＤ） ： 予約注文単位.注文ID
            //arg3（取引パスワード） ： 顧客.getTradingPassword()の戻り値をdecryptした値
            //arg4（isSkip発注審査） ： true
            MainAccount l_mainAccount = l_subAccount.getMainAccount();
            WEB3Crypt l_crypt = new WEB3Crypt();

            OrderSubmissionResult l_orderSubmissionResult =
                l_orderManager.submitSettleContractOrder(
                l_subAccount,
                l_settleContractOrderSpec,
                l_rsvIfoOrderUnit.getOrderId(),
                l_crypt.decrypt(l_mainAccount.getTradingPassword()),
                true);

            //実行結果に応じ、予約注文単位をupdateする。
            if (l_orderSubmissionResult.getProcessingResult().isSuccessfulResult())
            {
                //set発注済To予約注文単位(ProductTypeEnum, long)(連続注文マネージャImpl::set発注済To予約注文単位)
                //引数は以下の通りにセットする。
                //銘柄タイプ ： 引数の予約注文単位.銘柄タイプ
                //注文ID ： 引数の予約注文単位.注文ID
                WEB3ToSuccOrderManagerImpl l_orderManagerImpl =
                    (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
                l_orderManagerImpl.setOrderedToOrderUnit(
                    l_rsvIfoOrderUnit.getProductType(),
                    l_rsvIfoOrderUnit.getOrderId());
            }
            //実行結果エラー。
            else
            {
                log.debug("{submitFuturesSettleContractOrder()}がエラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "{submitFuturesSettleContractOrder()}がエラー。");
            }

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            if (l_ex instanceof WEB3BaseException)
            {
                l_strErrorCode = ((WEB3BaseException)l_ex).getErrorInfo().getErrorCode();
            }
            else if (l_ex instanceof WEB3BaseRuntimeException)
            {
                l_strErrorCode = ((WEB3BaseRuntimeException)l_ex).getErrorInfo().getErrorCode();
            }
            else if (l_ex instanceof NotFoundException)
            {
                l_strErrorCode = WEB3ErrorCatalog.SYSTEM_ERROR_80005.getErrorCode();
            }
            else
            {
                l_strErrorCode = WEB3ErrorCatalog.SYSTEM_ERROR_80002.getErrorCode();
            }

            //invalidate予約注文単位(先物OP予約注文単位Row, String)
            //[引数]
            //　@先物OP予約注文単位行 ： 引数の予約注文単位の行オブジェクト
            //　@発生エラーコード ： 発生した例外オブジェクトのErrorInfo.error_code
            l_orderUpdateService.invalidateOrderUnit(
                l_rsvIfoOrderUnitRow,
                l_strErrorCode);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (submitOP新規建注文)<BR>
     * オプション新規建注文を発注する。<BR>
     * シーケンス図「（連続注文先物OP発注一件サービス）submitOP新規建注文」を参照。<BR>
     * @@param l_rsvIfoOrderUnit - (先物OP予約注文単位)<BR>
     * 先物OP予約注文単位オブジェクト。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47DF5498019C
     */
    public void submitOptionsOpenContractOrder(WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOptionsOpenContractOrder(WEB3ToSuccIfoOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        //エラーコード
        String l_strErrorCode = null;

        //先物OP予約注文更新サービス
        WEB3ToSuccReservationIfoOrderUpdateService l_orderUpdateService =
            (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                WEB3ToSuccReservationIfoOrderUpdateService.class);

        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = null;
        WEB3GentradeSubAccount l_subAccount = null;
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
        try
        {
            //発注日／立会時間帯のチェックを行う。
            //（予約注文登録時の発注日／立会時間帯と、
            //現在日時から求めた発注日／立会時間帯が異なる場合は発注エラーとする）
            //[引数]
            //　@確認時発注日 ： 引数の先物OP予約注文単位.発注日
            //　@立会区分 ： 引数の先物OP予約注文単位.立会区分
            l_rsvIfoOrderUnitRow = (RsvIfoOrderUnitRow)l_rsvIfoOrderUnit.getDataSourceObject();
            WEB3GentradeTradingTimeManagement.getOrderBizDate(
                WEB3DateUtility.getDate(l_rsvIfoOrderUnitRow.getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD),
                l_rsvIfoOrderUnitRow.getSessionType());

            //分岐フロー：　@予約注文単位.取引者ID≠nullの場合のみ
            WEB3GentradeTrader l_trader = null;
            if (!l_rsvIfoOrderUnitRow.getTraderIdIsNull())
            {
                //扱者（代理入力者）オブジェクトを取得する。
                //引数は以下の通りにセットする。
                //取引者ID：　@予約注文単位.取引者ID
                FinObjectManager l_finMng = GtlUtils.getFinObjectManager();
                l_trader = (WEB3GentradeTrader)l_finMng.getTrader(l_rsvIfoOrderUnitRow.getTraderId());
            }

            //補助口座を取得する
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            l_subAccount = (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                l_rsvIfoOrderUnit.getAccountId(),
                l_rsvIfoOrderUnitRow.getSubAccountId());

            //[create新規建注文内容()に指定する引数]
            //証券会社コ ード ： 予約注文単位.部店IDから取得される証券会社コード
            //扱者 ： 予約注文単位.取引者ID==nullの場合、null
            //          予約注文単位.取引者ID<>nullの場合、取得した扱者オブジェクト
            //is買建 ： 予約注文単位.is買注文()
            //市場コード ： 予約注文単位.get市場().市場コード
            //銘柄 ： 予約注文単位.get銘柄()
            //数量 ： 予約注文単位.注文数量
            //指値 ： 予約注文単位.get予約注文執行単価()
            //執行条件   ： "条件なし"
            //注文失効日：予約注文単位.注文失効日
            //発注条件   ："条件なし"
            //逆指値基準値       ： 0
            //（W指値）訂正指値 ： 0
            //（W指値）執行条件 ： null
            //注文期限区分             ： 予約注文単位.注文期限区分
            //初回注文の注文単位ID ： 予約注文単位.初回注文の注文単位ID
            Long l_firstOrderUnitId = null;
            if (!l_rsvIfoOrderUnitRow.getFirstOrderUnitIdIsNull())
            {
                l_firstOrderUnitId = new Long(l_rsvIfoOrderUnitRow.getFirstOrderUnitId());
            }
            //夕場前繰越対象フラグ   ： 予約注文単位.夕場前繰越対象フラグ
            boolean l_blnEveningSessionCarryoverFlag = false;
            if (BooleanEnum.TRUE.equals(l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag()))
            {
                l_blnEveningSessionCarryoverFlag = true;
            }

            String l_strInstitutionCode =
                l_accountManager.getBranch(l_rsvIfoOrderUnitRow.getBranchId()).getInstitution().getInstitutionCode();

            //create新規建注文内容
            WEB3IfoOpenContractOrderSpec l_openContractOrderSpec =
                WEB3IfoOpenContractOrderSpec.createOpenContractOrderSpec(
                    l_strInstitutionCode,
                    l_trader,
                    l_rsvIfoOrderUnit.isBuyOrder(),
                    l_rsvIfoOrderUnit.getMarket().getMarketCode(),
                    (WEB3IfoProductImpl)l_rsvIfoOrderUnit.getProduct(),
                    l_rsvIfoOrderUnit.getQuantity(),
                    l_rsvIfoOrderUnit.getRsvOrderExecPrice(),
                    IfoOrderExecutionConditionType.NONE,
                    l_rsvIfoOrderUnit.getExpirationTimestamp(),
                    WEB3OrderingConditionDef.DEFAULT,
                    0,
                    0,
                    null,
                    l_rsvIfoOrderUnit.getExpirationDateType(),
                    l_firstOrderUnitId,
                    l_blnEveningSessionCarryoverFlag);

            //validate新規建注文(補助口座 : SubAccount, 先物OP新規建注文内容 : IfoOpenContractOrderSpec, 注文単位 : IfoOrderUnit)
            //引数は以下の通りに設定する。
            //補助口座：　@取得した補助口座オブジェクト
            //新規建注文内容：　@作成した新規建注文内容オブジェクト
            //注文単位：null
            NewOrderValidationResult l_orderValidationResult =
                l_orderManager.validateOpenContractOrder(
                    l_subAccount,
                    l_openContractOrderSpec,
                    null);

            //{validate新規建注文()}が失敗の場合。
            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("{validate新規建注文()}が失敗の場合。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "{validate新規建注文()}が失敗の場合。");
            }

            //手数料オブジェクトを生成する。
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            //(*)手数料オブジェクトに以下の通りプロパティをセットする。
            //手数料.注文チャネル = 予約注文単位.初回注文の注文チャネル
            //手数料.証券会社コード = 予約注文単位.部店IDから取得される証券会社コード
            //手数料.部店ID = 予約注文単位.部店ID
            //手数料.発注日 = 予約注文単位.発注日
            //手数料.取引コード(SONAR) = ”51：建”
            //手数料.手数料商品コード = ”51：株価指数OP”
            //手数料.弁済区分 = ”00：その他”
            //手数料.is指値=新規建注文内容.isLimitOrder()
            //手数料.原資産銘柄コード = 予約注文単位.get銘柄().get原資産銘柄コード()
            //手数料.数量=予約注文単位.注文数量
            Timestamp l_tsOrderBizDate = new Timestamp(
                WEB3DateUtility.getDate(l_rsvIfoOrderUnitRow.getBizDate(),
                WEB3GentradeTimeDef.DATE_FORMAT_YMD).getTime());
            String l_strOpenContract = WEB3TransactionTypeSONARDef.OPEN_CONTRACT;
            String l_strCommissionProductCode = WEB3CommisionProductCodeDef.INDEX_OP;
            l_commission.setOrderChannel(l_rsvIfoOrderUnitRow.getOrderChanel());
            l_commission.setInstitutionCode(l_strInstitutionCode);
            l_commission.setBranchId(l_rsvIfoOrderUnit.getBranchId());
            l_commission.setOrderBizDate(l_tsOrderBizDate);
            l_commission.setSonarTradedCode(l_strOpenContract);
            l_commission.setCommissionProductCode(l_strCommissionProductCode);
            l_commission.setPayType(WEB3PayTypeDef.OTHER);
            l_commission.setIsLimitPrice(l_openContractOrderSpec.isLimitOrder());
            l_commission.setUnderlyingProductCode(
                ((WEB3IfoProductImpl)l_rsvIfoOrderUnit.getProduct()).getUnderlyingProductCode());
            l_commission.setQuantity(l_rsvIfoOrderUnit.getQuantity());

            //calc概算受渡代金
            //[calc概算受渡代金()に指定する引数]
            //手数料    ： 作成した手数料オブジェクト
            //指値 ： 予約注文単位.get予約注文執行単価()
            //補助口座 ： 取得した補助口座オブジェクト
            //先物OP取引銘柄 ： 予約注文単位.get取引銘柄()
            //数量 ： 予約注文単位.注文数量
            //売買：
            //　@  ・予約注文単位.is買注文() == trueの場合
            // 　@   　@→SideEnum.BUY
            // 　@・上記以外の場合
            //   　@　@ →SideEnum.SELL
            //is返済注文：　@false
            //isSkip金額チェック：　@false
            SideEnum l_sideEnum = null;
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult = null;
            if (l_rsvIfoOrderUnit.isBuyOrder())
            {
                l_sideEnum = SideEnum.BUY;
            }
            else
            {
                l_sideEnum = SideEnum.SELL;
            }
            l_ifoEstimateDeliveryAmountCalcResult = l_orderManager.calcEstimateDeliveryAmount(
                l_commission,
                l_rsvIfoOrderUnit.getRsvOrderExecPrice(),
                l_subAccount,
                (WEB3IfoTradedProductImpl)l_rsvIfoOrderUnit.getTradedProduct(),
                l_rsvIfoOrderUnit.getQuantity(),
                l_sideEnum,
                false,
                false);

            //先物OP新規建更新インタセプタを作成する。
            //[引数]
            //先物OP新規建注文内容 ： 作成した新規建注文内容オブジェクト
            WEB3IfoOpenContractUpdateInterceptor l_ifoOpenContractUpdateInterceptor =
                new WEB3IfoOpenContractUpdateInterceptor(l_openContractOrderSpec);

            //(*)インタセプタオブジェクトのプロパティに以下の値をセットする。
            //インタセプタ.手数料 = 作成した手数料オブジェクト
            l_ifoOpenContractUpdateInterceptor.setCommision(l_commission);
            //インタセプタ.概算受渡代金計算結果 = calc概算受渡代金()の戻り値
            l_ifoOpenContractUpdateInterceptor.setEstimateDeliveryAmountCalcResult(
                l_ifoEstimateDeliveryAmountCalcResult);
            //インタセプタ.発注条件 = "条件なし"
            l_ifoOpenContractUpdateInterceptor.setOrderCond(WEB3OrderingConditionDef.DEFAULT);
            //インタセプタ.立会区分 = 予約注文単位.立会区分
            l_ifoOpenContractUpdateInterceptor.setSessionType(l_rsvIfoOrderUnitRow.getSessionType());
            //インタセプタ.扱者コード（SONAR） =　@予約注文単位.扱者コード（SONAR）
            l_ifoOpenContractUpdateInterceptor.setSonarTraderCode(l_rsvIfoOrderUnitRow.getSonarTraderCode());
            //インタセプタ.初回注文の注文チャネル = 予約注文単位.初回注文の注文チャネル
            l_ifoOpenContractUpdateInterceptor.setOrderChanel(l_rsvIfoOrderUnitRow.getOrderChanel());
            //インタセプタ.注文経路区分 = 予約注文単位.注文経路区分
            l_ifoOpenContractUpdateInterceptor.setOrderRootDiv(l_rsvIfoOrderUnitRow.getOrderRootDiv());

            //validate取引余力
            //証拠金のチェックを行う。
            //[引数]
            //補助口座 ： 取得した補助口座オブジェクト
            //注文内容インタセプタ[] ： 先物OP新規建更新インタセプタを要素とした配列
            //注文内容[] ： 新規建注文内容を要素とした配列
            //注文種別 ：
            // 引数.注文内容[0].isBuyToOpenOrder() == trueの場合、"OP新規買建"
            // 以外の場合、"OP新規売建"
            //余力更新フラグ ： true
            WEB3TPTradingPowerService l_tradingPowerService =
                (WEB3TPTradingPowerService)Services.getService(
                    WEB3TPTradingPowerService.class);

            WEB3IfoOpenContractUpdateInterceptor[] l_ifoOpenContractUpdateInterceptors =
                {l_ifoOpenContractUpdateInterceptor};
            WEB3IfoOpenContractOrderSpec[] l_orderSpecs = {l_openContractOrderSpec};
            OrderTypeEnum l_orderTypeEnum = null;
            if (l_orderSpecs[0].isBuyToOpenOrder())
            {
                l_orderTypeEnum = OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN;
            }
            else
            {
                l_orderTypeEnum = OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN;
            }
            WEB3TPTradingPowerResult l_tradingPowerResult = l_tradingPowerService.validateTradingPower(
                l_subAccount,
                l_ifoOpenContractUpdateInterceptors,
                l_orderSpecs,
                l_orderTypeEnum,
                true);

            OrderSubmissionResult l_orderSubmissionResult = null;
            //取引余力結果.is判定フラグ( )==trueの場合
            if (l_tradingPowerResult.isResultFlg())
            {
                //setThreadLocalPersistenceEventInterceptor(arg0 : IfoOrderManagerPersistenceEventInterceptor)
                //注文マネージャのThreadLocalに、先物OP新規建更新インタセプタをセットする。
                //[引数]
                //arg0（先物OP新規建更新インタセプタ）：　@生成した先物OP新規建更新インタセプタ
                l_orderManager.setThreadLocalPersistenceEventInterceptor(
                    l_ifoOpenContractUpdateInterceptor);

                //submitOpenContractOrder
                //新規建注文を登録する。
                String l_strTradingPasswood =
                    l_subAccount.getMainAccount().getTradingPassword();
                WEB3Crypt l_crypt = new WEB3Crypt();
                String l_strDecryptPassword = l_crypt.decrypt(l_strTradingPasswood);

                l_orderSubmissionResult = l_orderManager.submitOpenContractOrder(
                    l_subAccount,
                    l_openContractOrderSpec,
                    l_rsvIfoOrderUnit.getOrderId(),
                    l_strDecryptPassword,
                    true);
            }
            //取引余力結果.is判定フラグ( )==falseの場合
            //余力エラーの情報から、予約注文単位に記録するエラーコードを決定する。
            //引数は以下の通りにセットする。
            //取引余力結果：　@validate取引余力()の戻り値
            //補助口座：　@取得した補助口座オブジェクト
            else
            {
                log.debug("取引余力チェックエラー。");
                l_orderManager.throwTpErrorInfo(l_tradingPowerResult, l_subAccount);
            }

            //実行結果に応じ、予約注文単位をupdateする。
            if (l_orderSubmissionResult.getProcessingResult().isSuccessfulResult())
            {
                //set発注済To予約注文単位(ProductTypeEnum, long)(連続注文マネージャImpl::set発注済To予約注文単位)
                //引数は以下の通りにセットする。
                //銘柄タイプ ： 引数の予約注文単位.銘柄タイプ
                //注文ID ： 引数の予約注文単位.注文ID
                WEB3ToSuccOrderManagerImpl l_orderManagerImpl =
                    (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
                l_orderManagerImpl.setOrderedToOrderUnit(
                    l_rsvIfoOrderUnit.getProductType(),
                    l_rsvIfoOrderUnit.getOrderId());
            }
            //実行結果エラー。
            else
            {
                log.debug("{submitOptionsOpenContractOrder()}がエラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "{submitOptionsOpenContractOrder()}がエラー。");
            }
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            if (l_ex instanceof WEB3BaseException)
            {
                l_strErrorCode = ((WEB3BaseException)l_ex).getErrorInfo().getErrorCode();
            }
            else if (l_ex instanceof WEB3BaseRuntimeException)
            {
                l_strErrorCode = ((WEB3BaseRuntimeException)l_ex).getErrorInfo().getErrorCode();
            }
            else if (l_ex instanceof NotFoundException)
            {
                l_strErrorCode = WEB3ErrorCatalog.SYSTEM_ERROR_80005.getErrorCode();
            }
            else
            {
                l_strErrorCode = WEB3ErrorCatalog.SYSTEM_ERROR_80002.getErrorCode();
            }

            //invalidate予約注文単位(先物OP予約注文単位Row, String)
            //[引数]
            //　@先物OP予約注文単位行 ： 引数の予約注文単位の行オブジェクト
            //　@発生エラーコード ： 発生した例外オブジェクトのErrorInfo.error_code
            l_orderUpdateService.invalidateOrderUnit(
                l_rsvIfoOrderUnitRow,
                l_strErrorCode);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (submitOP返済注文)<BR>
     * オプション返済注文を発注する。<BR>
     * シーケンス図「（連続注文先物OP発注一件サービス）submitOP返済注文」を参照。<BR>
     * ==========================================================<BR>
     * シーケンス図 ：(（連続注文先物OP発注一件サービス）submit先物返済注文)<BR>
     * 具体位置：(get先物OP予約建玉返済指定情報一覧)<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag: BUSINESS_ERROR_02339<BR>
     * ==========================================================<BR>
     * @@param l_rsvIfoOrderUnit - (先物OP予約注文単位)<BR>
     * 先物OP予約注文単位オブジェクト。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47DF5498019E
     */
    public void submitOptionsSettleContractOrder(WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOptionsSettleContractOrder(WEB3ToSuccIfoOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        //先物OP予約注文単位
        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = null;

        //エラーコード
        String l_strErrorCode = null;

        //先物OP予約注文更新サービス
        WEB3ToSuccReservationIfoOrderUpdateService l_orderUpdateService =
            (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                WEB3ToSuccReservationIfoOrderUpdateService.class);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeSubAccount l_subAccount = null;

        try
        {
            //get発注日(確認時発注日 : Date)
            //引数は以下の通りにセットする。
            //確認時発注日：　@引数の先物OP予約注文単位.発注日
            //立会区分 ： 引数の先物OP予約注文単位.立会区分
            l_rsvIfoOrderUnitRow = (RsvIfoOrderUnitRow)l_rsvIfoOrderUnit.getDataSourceObject();
            WEB3GentradeTradingTimeManagement.getOrderBizDate(
                WEB3DateUtility.getDate(l_rsvIfoOrderUnitRow.getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD),
                l_rsvIfoOrderUnitRow.getSessionType());

            //補助口座を取得する
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            l_subAccount = (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                l_rsvIfoOrderUnit.getAccountId(),
                l_rsvIfoOrderUnitRow.getSubAccountId());

            //get先物OP予約建株返済指定情報一覧( )(先物OP予約注文単位Impl::get先物OP予約建株返済指定情報一覧)
            RsvIfoClosingContractSpecRow[] l_rsvIfoClosingContractSpecRows =
            	l_rsvIfoOrderUnit.getContractsToClose();

            if (l_rsvIfoClosingContractSpecRows == null)
            {
            	log.debug("前提注文による約定建の情報、または返済指定データが存在しません。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02339,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "前提注文による約定建の情報、または返済指定データが存在しません。");
            }

            //create決済指定エントリ(先物OP予約建株返済指定情報行[])(連続注文先物OP発注一件サービスImpl::create決済指定エントリ)
            //引数は以下の通りにセットする。
            //予約返済指定情報一覧 ： get先物OP予約建玉返済指定情報一覧()の戻り値
            SettleContractEntry[] l_settleContractOrderEntries =
                this.createSettleContractEntries(l_rsvIfoClosingContractSpecRows);

            //get予約注文執行単価( )(先物OP予約注文単位Impl::get予約注文執行単価)
            double l_dblRsvOrderExecPrice = l_rsvIfoOrderUnit.getRsvOrderExecPrice();

            //分岐フロー：　@予約注文単位.取引者ID≠nullの場合のみ
            WEB3GentradeTrader l_trader = null;
            if (!l_rsvIfoOrderUnitRow.getTraderIdIsNull())
            {
                //getTrader(取引者ID : long)
                //引数は以下の通りにセットする
                //  取引者ID：　@予約注文単位.取引者
                FinObjectManager l_finMng = GtlUtils.getFinObjectManager();
                l_trader = (WEB3GentradeTrader)l_finMng.getTrader(l_rsvIfoOrderUnit.getTraderId());
            }

            //[create返済注文内容( )：引数設定仕様]
            //証券会社コード ： 予約注文単位.部店IDから取得される証券会社コード
            //扱者 ： 予約注文単位.取引者ID == nullの場合、null
            //        予約注文単位.取引者ID <> nullの場合、取得した扱者オブジェクト
            //指値 ： 予約注文単位.get予約注文執行単価()の戻り値
            //執行条件 ： "条件なし"
            //注文失効日 ： 予約注文単位.注文失効日付
            //返済建玉エントリ ： create決済指定エントリ()の戻り値
            //発注条件 ： "条件なし"
            //逆指値基準値 ： 0
            //（W指値）訂正指値 ： 0
            //（W指値）執行条件 ： null
            //注文期限区分 ： 予約注文単位.注文期限区分
            //初回注文の注文単位ID ： 予約注文単位.初回注文の注文単位ID
            //夕場前繰越対象フラグ ： 予約注文単位.夕場前繰越対象フラグ
            Long l_firstOrderUnitId = null;
            if (!l_rsvIfoOrderUnitRow.getFirstOrderUnitIdIsNull())
            {
            	l_firstOrderUnitId = new Long(l_rsvIfoOrderUnitRow.getFirstOrderUnitId());
            }
            String l_strInstitutionCode =
                l_accountManager.getBranch(l_rsvIfoOrderUnitRow.getBranchId()).getInstitution().getInstitutionCode();

            boolean l_blnEveningSessionCarryoverFlag = false;
            if (BooleanEnum.TRUE.equals(l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag()))
            {
                l_blnEveningSessionCarryoverFlag = true;
            }

            WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec =
            	WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
            		l_strInstitutionCode,
                    l_trader,
                    l_dblRsvOrderExecPrice,
                    IfoOrderExecutionConditionType.NONE,
                    l_rsvIfoOrderUnitRow.getExpirationDate(),
                    l_settleContractOrderEntries,
                    WEB3OrderingConditionDef.DEFAULT,
                    0,
                    0,
                    null,
                    l_rsvIfoOrderUnitRow.getExpirationDateType(),
                    l_firstOrderUnitId,
                    l_blnEveningSessionCarryoverFlag);

            //validate返済注文(補助口座 : SubAccount, 信用返済注文内容 : EqTypeSettleContractOrderSpec)
            //引数は以下の通りに設定する。
            //  補助口座：　@予約注文単位.口座ID、補助口座IDに該当する補助口座オブジェクト
            //  信用返済注文内容：　@作成した信用返済注文内容オブジェクト
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(
                    ProductTypeEnum.IFO).getOrderManager();
            NewOrderValidationResult l_orderValidationResult =
                l_orderManager.validateSettleContractOrder(
                    l_subAccount,
                    l_settleContractOrderSpec);

            //{validate返済注文()}が失敗の場合。
            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("{validate返済注文()}が失敗の場合。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "{validate返済注文()}が失敗の場合。");
            }

            //手数料オブジェクトを生成する。
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            //(*)手数料オブジェクトに以下の通りプロパティをセットする。
            //注文チャネル = 予約注文単位.初回注文の注文チャネル
            //証券会社コード = 予約注文単位.部店IDから取得される証券会社コード
            //部店ID = 予約注文単位.部店ID
            //発注日 = 予約注文単位.発注日
            //取引コード(SONAR) =  ”52：返済”
            //手数料商品コード =  ”51：株価指数ＯＰ”
            //弁済区分 = ”00：その他”
            //is指値=新規建注文内容.isLimitOrder()
            //原資産銘柄コード = 予約注文単位.get銘柄().get原資産銘柄コード()
            //日計り区分 = OP注文マネージャ.get日計り区分()(*1)
            //     (*1) [引数設定仕様]
            //     create決済指定エントリ()の戻り値
            //数量 = 返済注文内容.getTotalQuantity()
            Timestamp l_tsOrderBizDate = new Timestamp(
                WEB3DateUtility.getDate(l_rsvIfoOrderUnitRow.getBizDate(),
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD).getTime());
            String l_strOpenContract = WEB3TransactionTypeSONARDef.SETTLE_CONTRACT;
            String l_strCommisionProductCodeDef = WEB3CommisionProductCodeDef.INDEX_OP;
            l_commission.setOrderChannel(l_rsvIfoOrderUnitRow.getOrderChanel());
            l_commission.setInstitutionCode(l_strInstitutionCode);
            l_commission.setBranchId(l_rsvIfoOrderUnit.getBranchId());
            l_commission.setOrderBizDate(l_tsOrderBizDate);
            l_commission.setSonarTradedCode(l_strOpenContract);
            l_commission.setCommissionProductCode(l_strCommisionProductCodeDef);
            l_commission.setPayType(WEB3PayTypeDef.OTHER);
            l_commission.setIsLimitPrice(l_rsvIfoOrderUnit.isLimitOrder());
            l_commission.setUnderlyingProductCode(
                ((WEB3IfoProductImpl)l_rsvIfoOrderUnit.getProduct()).getUnderlyingProductCode());
            l_commission.setDayTradeType(l_orderManager.getDayTradeType(l_settleContractOrderEntries));
            l_commission.setQuantity(l_settleContractOrderSpec.getTotalQuantity());

            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3IfoPositionManagerImpl l_positionManager =
                (WEB3IfoPositionManagerImpl)l_tradingModule.getPositionManager();
            long l_lngContractId = l_settleContractOrderEntries[0].getContractId();
            //建玉は返済建玉エントリ[0].建玉IDから生成した建玉オブジェクト
            WEB3IfoContractImpl l_contract = (WEB3IfoContractImpl)l_positionManager.getContract(l_lngContractId);

            //[calc概算受渡代金()：引数設定仕様]
            //  手数料：　@作成した手数料オブジェクト
            //  指値：　@ 返済注文内容.getLimitPrice()
            //  補助口座：　@予約注文単位.口座ID、補助口座IDに該当する補助口座オブジェクト
            //  先物OP取引銘柄 ： 予約注文単位.get取引銘柄()
            //  数量 ： 返済注文内容.getTotalQuantity()
            //  売買 ： (*)
            //    建玉.isLong() == trueの場合、SideEnum.BUY
            //    建玉.isLong() == falseの場合、SideEnum.SELL
            //    (*)建玉は返済建玉エントリ[0].建玉IDから生成した建玉オブジェクト
            //  is返済注文 ： true
            //  isSkip金額チェック ： false（スキップしない）
            SideEnum l_sideEnum = null;
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult = null;
            if (l_contract.isLong())
            {
                l_sideEnum = SideEnum.BUY;
            }
            else
            {
                l_sideEnum = SideEnum.SELL;
            }
            l_ifoEstimateDeliveryAmountCalcResult =
                l_orderManager.calcEstimateDeliveryAmount(
                    l_commission,
                    l_settleContractOrderSpec.getLimitPrice(),
                    l_subAccount,
                    (WEB3IfoTradedProductImpl)l_rsvIfoOrderUnit.getTradedProduct(),
                    l_settleContractOrderSpec.getTotalQuantity(),
                    l_sideEnum,
                    true,
                    false);
            //先物OP返済更新インタセプタを作成する。
            //先物OP返済注文内容 ： 作成した先物OP返済注文内容オブジェクト
            WEB3IfoSettleContractUpdateInterceptor l_closeIfoUpdateInterceptor =
                new WEB3IfoSettleContractUpdateInterceptor(
                    l_settleContractOrderSpec);

            //(*)インタセプタにプロパティをセットする。
            //[設定する各プロパティ]
            //手数料 ： 作成した手数料オブジェクト
            //概算受渡代金計算結果 ： calc概算受渡代金()の戻り値
            //発注条件 ： "条件なし"
            //決済順序 ： 予約注文単位.決済順序
            //立会区分 ： 予約注文単位.立会区分
            //扱者コード（SONAR） ： 予約注文単位.扱者コード（SONAR）
            //初回注文の注文チャネル = 予約注文単位.初回注文の注文チャネル
            //注文経路区分 = 予約注文単位.注文経路区分
            l_closeIfoUpdateInterceptor.setCommision(l_commission);
            l_closeIfoUpdateInterceptor.setEstimateDeliveryAmountCalcResult(
                l_ifoEstimateDeliveryAmountCalcResult);
            l_closeIfoUpdateInterceptor.setOrderCond(WEB3OrderingConditionDef.DEFAULT);
            l_closeIfoUpdateInterceptor.setSettleSequence(l_rsvIfoOrderUnitRow.getClosingOrder());
            l_closeIfoUpdateInterceptor.setSessionType(l_rsvIfoOrderUnitRow.getSessionType());
            l_closeIfoUpdateInterceptor.setSonarTraderCode(l_rsvIfoOrderUnitRow.getSonarTraderCode());
            l_closeIfoUpdateInterceptor.setOrderChanel(l_rsvIfoOrderUnitRow.getOrderChanel());
            l_closeIfoUpdateInterceptor.setOrderRootDiv(l_rsvIfoOrderUnitRow.getOrderRootDiv());

            //注文マネージャのThreadLocalに、先物OP返済更新インタセプタをセットする。
            //[引数]
            //arg0（先物OP返済更新インタセプタ） ： 生成した先物OP返済更新インタセプタ
            l_orderManager.setThreadLocalPersistenceEventInterceptor(l_closeIfoUpdateInterceptor);

            //[submitSettleContractOrder( )：引数設定仕様]
            //  arg0（補助口座）：　@取得した補助口座オブジェクト
            //  arg1（返済注文内容）：　@create返済注文内容()の戻り値
            //  arg2（注文ＩＤ）：　@予約注文単位.get注文ID()
            //  arg3（取引パスワード）：　@顧客.getTradingPassword()の戻り値をdecryptした値
            //  arg4（isSkip発注審査）：　@true
            MainAccount l_mainAccount = l_subAccount.getMainAccount();
            WEB3Crypt l_crypt = new WEB3Crypt();

            OrderSubmissionResult l_orderSubmissionResult =
                l_orderManager.submitSettleContractOrder(
                    l_subAccount,
                    l_settleContractOrderSpec,
                    l_rsvIfoOrderUnit.getOrderId(),
                    l_crypt.decrypt(l_mainAccount.getTradingPassword()),
                    true);

            //オプション買建口座（補助口座.補助口座タイプ≠"株式オプション取引口座（先物証拠金））の場合
            if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()))
            {
                //余力再計算(補助口座 : 補助口座)
                //引数は以下の通りにセットする。
                //  補助口座：　@予約注文単位.口座ID、補助口座IDに該当する補助口座オブジェクト
                WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
                    (WEB3TPTradingPowerReCalcService)Services.getService(
                        WEB3TPTradingPowerReCalcService.class);
                l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
            }

            //実行結果に応じ、予約注文単位をupdateする
            if (l_orderSubmissionResult.getProcessingResult().isSuccessfulResult())
            {
                //set発注済To予約注文単位(ProductTypeEnum, long)(連続注文マネージャImpl::set発注済To予約注文単位)
                //引数は以下の通りにセットする。
                //  銘柄タイプ：　@引数の予約注文単位.銘柄タイプ
                //  注文ID：　@引数の予約注文単位.注文ID
                WEB3ToSuccOrderManagerImpl l_orderManagerImpl =
                    (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
                l_orderManagerImpl.setOrderedToOrderUnit(
                    l_rsvIfoOrderUnit.getProductType(),
                    l_rsvIfoOrderUnit.getOrderId());
            }
            else
            {
                log.debug("{submitOptionsSettleContractOrder()}がエラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "{submitOptionsSettleContractOrder()}がエラー。");
            }

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            if (l_ex instanceof WEB3BaseException)
            {
                l_strErrorCode = ((WEB3BaseException)l_ex).getErrorInfo().getErrorCode();
            }
            else if (l_ex instanceof WEB3BaseRuntimeException)
            {
                l_strErrorCode = ((WEB3BaseRuntimeException)l_ex).getErrorInfo().getErrorCode();
            }
            else if (l_ex instanceof NotFoundException)
            {
                l_strErrorCode = WEB3ErrorCatalog.SYSTEM_ERROR_80005.getErrorCode();
            }
            else
            {
                l_strErrorCode = WEB3ErrorCatalog.SYSTEM_ERROR_80002.getErrorCode();
            }

            //invalidate予約注文単位(先物OP予約注文単位行 : 先物OP予約注文単位Row, 発注エラーコード : String)
            //引数は以下の通りにセットする。
            //先物OP予約注文単位行 ： 引数の予約注文単位の行オブジェクト
            //発生エラーコード ： 発生した例外オブジェクトのErrorInfo.error_code
            //発生エラーコードには、エラー原因の特定が可能な
            //BusinessError／SystemErrorのエラーコードをセットする。
            l_orderUpdateService.invalidateOrderUnit(
                l_rsvIfoOrderUnitRow,
                l_strErrorCode);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create決済指定エントリ)<BR>
     * 引数の先物OP予約建玉返済指定情報一覧より、<BR>
     * 返済注文への決済指定エントリ（配列）を作成し返却する。<BR>
     * <BR>
     * 引数の予約返済指定情報一覧の要素（index）数分、以下の処理を繰り返す。<BR>
     * LOOP終了後、作成したインスタンスの配列を返却する。<BR>
     * <BR>
     * ↓↓↓↓　@START LOOP　@↓↓↓↓<BR>
     * <BR>
     * １）　@返済指定対象の建玉オブジェクトを取得する。<BR>
     * <BR>
     * 　@　@　@先物OP建玉のインスタンスを生成する。<BR>
     * <BR>
     * 　@　@　@[引数設定仕様]<BR>
     * 　@　@　@　@建玉ID ： 予約返済指定情報一覧[index].建玉ID<BR>
     * <BR>
     * ２）　@返済数量を取得する。<BR>
     * <BR>
     * 　@　@　@返済数量 = 予約返済指定情報一覧[index].返済注文数量<BR>
     * <BR>
     * 　@　@　@※返済数量 == 0の場合は、次の要素の処理を行う（continue）<BR>
     * <BR>
     * ３）　@返済可能建玉残高を計算する。<BR>
     * <BR>
     * 　@　@　@返済可能建玉残高 = 建玉.建玉数 － 建玉.getLockedQuantity()<BR>
     * <BR>
     * 　@　@　@※建玉は１）で取得した建玉<BR>
     * <BR>
     * ４）　@返済数量をチェックする。<BR>
     * <BR>
     * 　@　@　@（２）で取得した返済数量 ＞ ３）で計算した返済可能建玉残高）の場合、<BR>
     * 　@　@　@「建玉残高不足エラー」の例外をthrowする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag: BUSINESS_ERROR_03082<BR>
     * <BR>
     * ５）　@SettleContractEntryのインスタンスを生成し、戻り値にappendする。<BR>
     * <BR>
     * 　@　@　@[引数設定仕様]<BR>
     * 　@　@　@　@建玉ID ： 予約返済指定情報一覧[index].建玉ID<BR>
     * 　@　@　@　@返済数量 ： ２）で取得した返済数量<BR>
     * <BR>
     * ↑↑↑↑　@END LOOP　@↑↑↑↑<BR>
     * @@param l_rsvIfoClosingContractSpecs - (予約返済指定情報一覧)<BR>
     * 先物OP予約建玉返済指定情報行オブジェクトの配列。<BR>
     * @@return SettleContractEntry[]
     * @@throws WEB3BaseException
     * @@roseuid 47DF549801A0
     */
    protected SettleContractEntry[] createSettleContractEntries(
        RsvIfoClosingContractSpecRow[] l_rsvIfoClosingContractSpecs) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSettleContractEntries(RsvIfoClosingContractSpecRow[])";
        log.entering(STR_METHOD_NAME);

        if (l_rsvIfoClosingContractSpecs == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //決済指定エントリリストが定義
        List l_lisContractOrderEntries = new ArrayList();

        try
        {
            //決済指定エントリ配列の長度
            int l_intContractSpecLength = l_rsvIfoClosingContractSpecs.length;

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3IfoPositionManagerImpl l_positionManager =
                (WEB3IfoPositionManagerImpl)l_finApp.getTradingModule(
                    ProductTypeEnum.IFO).getPositionManager();

            //↓↓↓↓　@START LOOP　@↓↓↓↓
            for (int i = 0; i < l_intContractSpecLength; i++)
            {
                //１）　@返済指定対象の建玉オブジェクトを取得する。
                //先物OP建玉のインスタンスを生成する。
                //[引数設定仕様]
                //建玉ID ： 予約返済指定情報一覧[index].建玉ID
                long l_lngContractId = l_rsvIfoClosingContractSpecs[i].getContractId();
                WEB3IfoContractImpl l_contract = (WEB3IfoContractImpl)l_positionManager.getContract(l_lngContractId);

                //２）　@返済数量を取得する。
                //　@　@　@返済数量 = 予約返済指定情報一覧[index].返済注文数量
                //　@　@　@返済数量 == 0の場合は、次の要素の処理を行う（continue）
                double l_dblClosingQuantity = l_rsvIfoClosingContractSpecs[i].getQuantity();
                BigDecimal l_bdClosingQuantity = new BigDecimal(String.valueOf(l_dblClosingQuantity));
                if (GtlUtils.Double.isZero(l_dblClosingQuantity))
                {
                    continue;
                }

                double l_dblQuantity = l_contract.getQuantity();
                BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_dblQuantity));

                double l_dblLockedQuantity = l_contract.getLockedQuantity();
                BigDecimal l_bdLockedQuantity = new BigDecimal(String.valueOf(l_dblLockedQuantity));

                //３）　@返済可能建玉残高を計算する。
                //　@　@　@返済可能建玉残高 = 建玉.建玉数 － 建玉.getLockedQuantity()
                //      ※建玉は１）で取得した建玉
                BigDecimal l_bdCloseContractQuantity = l_bdQuantity.subtract(l_bdLockedQuantity);
                //４）　@返済数量をチェックする。
                // 　@　@（２）で取得した返済数量 ＞ ３）で計算した返済可能建玉残高）の場合、
                // 　@　@「建玉残高不足エラー」の例外をthrowする。
                if (l_bdClosingQuantity.compareTo(l_bdCloseContractQuantity) > 0)
                {
                    log.debug("建玉残高不足エラー。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03082,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "建玉残高不足エラー。");
                }

                //５）　@SettleContractEntryのインスタンスを生成し、戻り値にappendする。
                //　@　@　@[引数設定仕様]
                //　@　@　@[コンストラクタ引数設定仕様]
                //　@　@　@建玉ID ： 予約返済指定情報一覧[index].建玉ID
                //　@　@　@返済数量 ： ２）で取得した返済数量
                SettleContractEntry l_settlecontractEntry =
                    new SettleContractEntry(l_lngContractId, l_dblClosingQuantity);

                l_lisContractOrderEntries.add(l_settlecontractEntry);
            }
            //↑↑↑↑　@ENDLOOP　@↑↑↑↑
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに重複する該当データが存在します。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //決済指定エントリリストがnull場合
        if (l_lisContractOrderEntries == null || l_lisContractOrderEntries.size() == 0)
        {
        	log.exiting(STR_METHOD_NAME);
            return null;
        }

        SettleContractEntry[] l_settleContractEntries =
            new SettleContractEntry[l_lisContractOrderEntries.size()];
        l_lisContractOrderEntries.toArray(l_settleContractEntries);

        log.exiting(STR_METHOD_NAME);
        return l_settleContractEntries;
    }
}
@
