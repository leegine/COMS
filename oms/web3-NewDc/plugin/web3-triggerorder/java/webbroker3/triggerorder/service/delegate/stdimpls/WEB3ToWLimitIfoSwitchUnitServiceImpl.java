head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.49.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToWLimitIfoSwitchUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : W指値注文先物OP切替一件サービスImpl(WEB3ToWLimitIfoSwitchUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/23　@肖志偉(中訊) 新規作成
Revesion History : 2006/11/10  唐性峰(中訊)　@モデルNo.186
Revesion History : 2006/11/29  徐大方(中訊)　@モデルNo.200,DB更新仕様 No.034
Revesion History : 2007/01/31  吉麗ナ(中訊)　@仕様変更　@モデルNo.213
Revesion History : 2007/06/30  孟亜南(中訊)　@仕様変更　@モデルNo.240
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoClosingContractSpec;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoContractSettleOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoBizLogicProvider;
import webbroker3.ifo.WEB3IfoChangeSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoOpenContractChangeSpec;
import webbroker3.ifo.WEB3IfoOpenContractChangeUpdateInterceptor;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.define.WEB3IfoWLimitEnableStatusDivDef;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.WEB3ToWLimitIfoSwitchUpdateInterceptor;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitIfoSwitchUnitService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (W指値注文先物OP切替一件サービスImpl)<BR>
 * W指値注文先物OP切替一件サービス実装クラス<BR>
 *
 * @@author 肖志偉
 * @@version 1.0
 */
public class WEB3ToWLimitIfoSwitchUnitServiceImpl implements WEB3ToWLimitIfoSwitchUnitService
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3ToWLimitIfoSwitchUnitServiceImpl.class);

    /**
     * @@roseuid 44E9077B0203
     */
    public WEB3ToWLimitIfoSwitchUnitServiceImpl()
    {

    }

    /**
     * (submit先物新規建W指値注文)<BR>
     * 先物新規建W指値注文切替処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（W指値注文先物OP切替一件サービス）submit先物新規建W指値注文」参照。<BR>
     * @@param l_orderUnit - (先物OP注文単位)<BR>
     * 先物オプション注文単位オブジェクト。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44923E370100
     */
    public void submitFuturesOpenContractWLimitOrder(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitFuturesOpenContractWLimitOrder(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("先物OP注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "先物OP注文単位 = null。");
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3FuturesOrderManagerImpl l_futuresOrderManagerImpl =
            (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();

        //引数で渡された注文単位は更新されている可能性があるので、再取得を行うこと。
        try
        {
            l_orderUnit =
                (IfoOrderUnit)l_futuresOrderManagerImpl.getOrderUnit(
                    l_orderUnit.getOrderUnitId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("注文単位テーブルに該当するデータがありません。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

        //1.1)getSubAccount()
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeSubAccount l_subAccount = null;
        try
        {
            l_subAccount = (WEB3GentradeSubAccount)l_gentradeAccountManager.getSubAccount(
                l_orderUnit.getAccountId(), l_orderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.2)is処理対象()
        boolean l_blnIsProcessObject = this.isProcessObject(l_orderUnit);

        //1.3)（分岐フロー：　@is処理対象＝false（処理対象外注文）の場合）
        if (!l_blnIsProcessObject)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //1.4)validate切替処理可能()
        this.validateSwitchPossible(l_orderUnit);

        //1.5) (*)validate切替処理可能()にて例外がスローされた場合
        try
        {
            //1.6) get発注日(確認時発注日 : Date, 立会区分 : String)
            Date l_datBizDate =
                WEB3DateUtility.getDate(l_ifoOrderUnitRow.getBizDate(), "yyyyMMdd");
            WEB3GentradeTradingTimeManagement.getOrderBizDate(l_datBizDate, l_ifoOrderUnitRow.getSessionType());

            //1.7)（分岐フロー：注文単位.取引者ID≠nullの場合のみ）
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            WEB3IfoProductManagerImpl l_ifoProductManager =
                (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();

            Trader l_trader = null;
            WEB3GentradeMarket l_market = null;
            WEB3IfoProductImpl l_productImpl = null;

            try
            {
                if (!l_ifoOrderUnitRow.getTraderIdIsNull())
                {
                    l_trader =
                        l_finObjectManager.getTrader(l_ifoOrderUnitRow.getTraderId());
                }
                l_market =
                    (WEB3GentradeMarket)l_finObjectManager.getMarket(l_ifoOrderUnitRow.getMarketId());
                l_productImpl =
                    (WEB3IfoProductImpl)l_ifoProductManager.getProduct(l_ifoOrderUnitRow.getProductId());

            }
            catch (NotFoundException l_nfe)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //注文期限区分 = 先物OPデータアダプタ.get注文期限区分(注文単位)
            String l_strExpirationDateType = WEB3IfoDataAdapter.getExpirationDateType(l_orderUnit);

            //夕場前繰越対象フラグ = 先物OPデータアダプタ.get夕場前繰越対象フラグ（PR層）(注文単位)
            boolean l_blnEveningSessionCarryoverFlag =
                WEB3IfoDataAdapter.getEveningSessionCarryOverFlagPr(l_orderUnit);

            //1.8)create新規建訂正内容
            double l_dblQuantity = l_ifoOrderUnitRow.getQuantity();
            double l_dblWLimitPrice = l_ifoOrderUnitRow.getWLimitPrice();
            IfoOrderExecutionConditionType l_wLimitExecCondType =
                l_ifoOrderUnitRow.getWLimitExecCondType();
            Timestamp l_expirationDate = l_ifoOrderUnitRow.getExpirationDate();

            //発注日
            Date l_datBiz = l_datBizDate;

            WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec =
                WEB3IfoOpenContractChangeSpec.createIfoOpenContractChangeSpec(
                    l_ifoOrderUnitRow.getOrderId(),
                    l_ifoOrderUnitRow.getOrderUnitId(),
                    l_dblQuantity,
                    l_dblWLimitPrice,
                    l_wLimitExecCondType,
                    l_expirationDate,
                    l_datBiz,
                    l_ifoOrderUnitRow.getOrderConditionType(),
                    l_ifoOrderUnitRow.getOrderCondOperator(),
                    l_ifoOrderUnitRow.getStopPriceType(),
                    l_ifoOrderUnitRow.getStopOrderPrice(),
                    l_dblWLimitPrice,
                    l_wLimitExecCondType,
                    WEB3IfoWLimitEnableStatusDivDef.STOP_ENABLE,
                    l_strExpirationDateType,
                    l_blnEveningSessionCarryoverFlag);

            //isSkip遅延状況チェック
            boolean l_blnIsSkipDelayStatusCheck = true;
            //1.9) validate先物新規建訂正注文()
            OrderValidationResult l_orderValidationResult =
                l_futuresOrderManagerImpl.validateFuturesChangeOrder(
                    l_subAccount,
                    l_ifoOpenContractChangeSpec,
                    l_blnIsSkipDelayStatusCheck);

            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("ProcessingResult() = " + l_orderValidationResult.getProcessingResult());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                        l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //1.10)create手数料()
            WEB3IfoBizLogicProvider l_bizLogicProvider =
                (WEB3IfoBizLogicProvider)l_tradingModule.getBizLogicProvider();

            WEB3GentradeCommission l_commission =
                l_bizLogicProvider.createCommission(l_ifoOrderUnitRow.getOrderUnitId());

            //1.11)is指値（切替後）()
            l_commission.setIsLimitPrice(this.isLimitPriceAfterSwitch(l_orderUnit));

            //1.11)get取引銘柄()
            WEB3IfoTradedProductImpl l_ifoTradedProduct = null;
            try
            {
                l_ifoTradedProduct =
                    l_ifoProductManager.getIfoTradedProduct(
                        l_subAccount.getInstitution(),
                        l_productImpl.getProductCode(),
                        l_market.getMarketCode());
            }
            catch (NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //1.13)calc訂正時概算建代金()
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult =
                l_futuresOrderManagerImpl.calcChangeEstimatePrice(
                    l_commission,
                    l_ifoOpenContractChangeSpec.getAfterChangePrice(),
                    l_subAccount,
                    l_ifoTradedProduct,
                    l_ifoOpenContractChangeSpec.getAfterChangeOriginalQuantity(),
                    l_ifoOrderUnitRow.getExecutedQuantity(),
                    l_ifoOrderUnitRow.getExecutedAmount(),
                    false);
            //1.14)先物OP新規建訂正更新インタセプタ()
            WEB3IfoOpenContractChangeUpdateInterceptor l_interceptor =
                new WEB3IfoOpenContractChangeUpdateInterceptor(l_ifoOpenContractChangeSpec);

            //1.15)(*)プロパティセット
            //手数料           ＝ 作成した手数料オブジェクト
            l_interceptor.setCommision(l_commission);

            //概算受渡代金計算結果    ＝ calc訂正時概算受渡代金()の戻り値
            l_interceptor.setEstimateDeliveryAmountCalcResult(l_ifoEstimateDeliveryAmountCalcResult);

            //発注条件          ＝ 注文単位の同名項目
            l_interceptor.setOrderCond(l_ifoOrderUnitRow.getOrderConditionType());

            //発注条件演算子       ＝ 注文単位の同名項目
            l_interceptor.setOrderCondOperator(l_ifoOrderUnitRow.getOrderCondOperator());

            //逆指値基準値タイプ     ＝ 注文単位の同名項目
            l_interceptor.setStopOrderBasePriceType(l_ifoOrderUnitRow.getStopPriceType());

            //逆指値基準値        ＝ 注文単位の同名項目
            l_interceptor.setStopOrderBasePrice(l_ifoOrderUnitRow.getStopOrderPrice());

            //（W指値）訂正指値     ＝ 注文単位の同名項目
            l_interceptor.setWLimitPriceChange(l_ifoOrderUnitRow.getWLimitPrice());

            //取引者ID         ＝ 注文単位.取引者ID＝nullの場合、0（固定）。以外、注文単位.取引者ID
            if (l_ifoOrderUnitRow.getTraderIdIsNull())
            {
                l_interceptor.setTraderId(0);
            }
            else
            {
                l_interceptor.setTraderId(l_ifoOrderUnitRow.getTraderId());
            }

            //注文経路区分        ＝ 注文単位の同名項目
            l_interceptor.setOrderRootDiv(l_ifoOrderUnitRow.getOrderRootDiv());

            //1.16)validate取引余力()
            WEB3TPTradingPowerService l_tpTradingPowerService =
                (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            WEB3TPTradingPowerResult l_tpResult =
                l_tpTradingPowerService.validateTradingPower(
                    l_subAccount,
                    new Object[]{l_interceptor},
                    new Object[]{l_ifoOpenContractChangeSpec},
                    l_ifoOrderUnitRow.getOrderType(),
                    true);

            //1.17)throw余力エラー情報()
            if (!l_tpResult.isResultFlg())
            {
                log.debug("取引余力チェックエラー。");
                l_futuresOrderManagerImpl.throwTpErrorInfo(l_tpResult, l_subAccount);
            }

            //1.18) (実行結果に応じて注文系データをUPDATEする)
            //1.18.1(*)正常終了した場合
            //1.18.1.1)W指値注文先物OP切替更新インタセプタ
            WEB3GentradeTrader l_gentradeTrader = null;
            if (l_trader != null)
            {
                l_gentradeTrader = (WEB3GentradeTrader)l_trader;
            }
            WEB3ToWLimitIfoSwitchUpdateInterceptor l_wLimitIfoSwitchUpdateInterceptor =
                new WEB3ToWLimitIfoSwitchUpdateInterceptor(
                    l_gentradeTrader,
                    l_ifoEstimateDeliveryAmountCalcResult,
                    l_ifoOpenContractChangeSpec);

            //1.18.2)setThreadLocalPersistenceEventInterceptor()
            l_futuresOrderManagerImpl.setThreadLocalPersistenceEventInterceptor(
                l_wLimitIfoSwitchUpdateInterceptor);

            //1.18.3)submitChangeOrder()
            String l_strTradingPasswood =
                l_subAccount.getMainAccount().getTradingPassword();
            WEB3Crypt l_crypt = new WEB3Crypt();
            String l_strDecryptPassword = l_crypt.decrypt(l_strTradingPasswood);
            OrderSubmissionResult l_orderSubmissionResult =
                l_futuresOrderManagerImpl.submitChangeOrder(
                    l_subAccount,
                    l_ifoOpenContractChangeSpec,
                    l_strDecryptPassword,
                    true);

            if (l_orderSubmissionResult.getProcessingResult().isFailedResult())
            {
                log.debug("ProcessingResult() = " + l_orderSubmissionResult.getProcessingResult());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }

        //1.18.2)(*)処理中に例外がスローされた場合
        catch (Exception l_ex)
        {
            //1.18.2.1)get注文エラー理由コード(ErrorInfo)
            log.error(l_ex.getMessage(), l_ex);
            ErrorInfo l_errorInfo = null;
            if (l_ex instanceof WEB3BaseException)
            {
                l_errorInfo = ((WEB3BaseException)l_ex).getErrorInfo();
            }
            else if (l_ex instanceof WEB3BaseRuntimeException)
            {
                l_errorInfo = ((WEB3BaseRuntimeException)l_ex).getErrorInfo();
            }
            String l_strErrorReasonCode = null;
            if (l_errorInfo != null)
            {
                l_strErrorReasonCode =
                    l_futuresOrderManagerImpl.getErrorReasonCode(l_errorInfo.getErrorCode());
            }   

            //1.18.2.2) update切替失敗()
            this.updateSwitchFail(l_orderUnit, l_strErrorReasonCode);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (submit先物返済W指値注文)<BR>
     * 先物返済W指値注文切替処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（W指値注文先物OP切替一件サービス）submit先物返済W指値注文」参照。<BR>
     *  ========================================================== <BR>
     * 1.8.1)(*)返済可能数量チェック　@<BR>
     * ３）返済数量(*1) ＞ 返済可能建玉残高(*2)　@の場合、<BR>
     * 　@「返済可能残高数量超過エラー」の例外をthrowする。 <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00299<BR>
     *  ========================================================== <BR>
     * @@param l_orderUnit - (先物OP注文単位)<BR>
     * 先物オプション注文単位オブジェクト。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44923E370110
     */
    public void submitFuturesSettleContractWLimitOrder(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitFuturesSettleContractWLimitOrder(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("先物OP注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "先物OP注文単位 = null。");
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3FuturesOrderManagerImpl l_futuresOrderManagerImpl =
            (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();

        //引数で渡された注文単位は更新されている可能性があるので、再取得を行うこと。
        try
        {
            l_orderUnit =
                (IfoOrderUnit)l_futuresOrderManagerImpl.getOrderUnit(
                    l_orderUnit.getOrderUnitId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("注文単位テーブルに該当するデータがありません。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

        //1.1)getSubAccount()
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeSubAccount l_subAccount = null;
        try
        {
            l_subAccount = (WEB3GentradeSubAccount)l_gentradeAccountManager.getSubAccount(
                l_orderUnit.getAccountId(), l_orderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.2)is処理対象()
        boolean l_blnIsProcessObject = this.isProcessObject(l_orderUnit);

        //1.2.1)（分岐フロー：　@is処理対象＝false（処理対象外注文）の場合）
        if (!l_blnIsProcessObject)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //1.3)validate切替処理可能()
        this.validateSwitchPossible(l_orderUnit);

        //1.4) (*)validate切替処理可能()にて例外がスローされた場合
        try
        {
            //1.5) get発注日(確認時発注日 : Date, 立会区分 : String)
            Date l_datBizDate =
                WEB3DateUtility.getDate(l_ifoOrderUnitRow.getBizDate(), "yyyyMMdd");
            WEB3GentradeTradingTimeManagement.getOrderBizDate(l_datBizDate, l_ifoOrderUnitRow.getSessionType());

            IfoContractSettleOrderUnit l_contractSettleOrderUnit =
                (IfoContractSettleOrderUnit)l_orderUnit;

            //1.6)getContractsToClose()
            IfoClosingContractSpec[] l_ifoClosingContractSpecList =
                l_contractSettleOrderUnit.getContractsToClose();

            //1.7)ArrayList()
            List l_lisSettleContractEntry = new ArrayList();

            //1.8)（getContractsToClose( )の戻り値（＝建玉返済指定情報）要素数(index)分、Loop）
            int l_intLength = 0;
            if (l_ifoClosingContractSpecList != null)
            {
                l_intLength = l_ifoClosingContractSpecList.length;
            }
            for (int i = 0; i < l_intLength; i++)
            {
                //1.8.1)(*)返済可能数量チェック
                //建玉返済指定情報[index].返済注文数量
                double l_dblContractsQuantity = l_ifoClosingContractSpecList[i].getQuantity();
                BigDecimal l_bdContractsQuantity = new BigDecimal(l_dblContractsQuantity);

                //建玉返済指定情報[index].返済約定数量
                double l_dblExecutedQuantity = l_ifoClosingContractSpecList[i].getExecutedQuantity();
                BigDecimal l_bdExecutedQuantity = new BigDecimal(l_dblExecutedQuantity);

                //　@返済数量 ＝ 建玉返済指定情報[index].返済注文数量 － 建玉返済指定情報[index].返済約定数量
                BigDecimal l_bdCloseQuantity = l_bdContractsQuantity.subtract(l_bdExecutedQuantity);

                //1.8.1.1)先物OP建玉()
                WEB3IfoContractImpl l_ifoContractImpl = null;
                try
                {
                    l_ifoContractImpl = new WEB3IfoContractImpl(l_ifoClosingContractSpecList[i].getContractId());
                }
                catch (DataNetworkException l_dne)
                {
                    log.error(STR_METHOD_NAME, l_dne);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                catch (DataQueryException l_dqe)
                {
                    log.error(STR_METHOD_NAME, l_dqe);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + STR_METHOD_NAME);
                }

                //1.8.1.2)getQuantity( )
                double l_dblQuantity = l_ifoContractImpl.getQuantity();
                BigDecimal l_bdQuantity = new BigDecimal(l_dblQuantity);

                //1.8.1.3)getLockedQuantity( )
                double l_dblLockedQuantity = l_ifoContractImpl.getLockedQuantity();
                BigDecimal l_bdLockedQuantity = new BigDecimal(l_dblLockedQuantity);

                //1.8.1.4)getロック中数量()
                double l_dblLockedQuantityReal = l_ifoContractImpl.getLockedQuantity(l_orderUnit.getOrderUnitId());
                BigDecimal l_bdLockedQuantityReal = new BigDecimal(l_dblLockedQuantityReal);

                //返済可能建玉残高 ＝ 建玉.getQuantity()（＝建玉数量） － 建玉.getLockedQuantity()（＝ロック中数量）
                //＋ 建玉.getロック中数量(注文単位ID)（＝当該注文ロック中数量）
                BigDecimal l_bdContractBalance = l_bdQuantity.subtract(l_bdLockedQuantity).add(l_bdLockedQuantityReal);

                //返済数量(*1) ＞ 返済可能建玉残高(*2)　@の場合、「返済可能残高数量超過エラー」の例外をthrowする。
                if (l_bdCloseQuantity.compareTo(l_bdContractBalance) > 0)
                {
                    log.debug("返済可能残高数量超過エラー");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00299,
                        this.getClass().getName() + STR_METHOD_NAME);
                }

                //1.8.1.5)SettleContractEntry()
                SettleContractEntry l_settleContractEntry =
                    new SettleContractEntry(
                        l_ifoClosingContractSpecList[i].getContractId(),
                        l_dblContractsQuantity);

                //1.8.1.6)add()
                l_lisSettleContractEntry.add(l_settleContractEntry);
            }

            SettleContractEntry[] l_settleContractEntryList =
                new SettleContractEntry[l_lisSettleContractEntry.size()];
            l_lisSettleContractEntry.toArray(l_settleContractEntryList);

            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            WEB3IfoProductManagerImpl l_ifoProductManager =
                (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();

            Trader l_trader = null;
            WEB3GentradeMarket l_market = null;
            WEB3IfoProductImpl l_productImpl = null;

            try
            {
                //1.9)（分岐フロー：注文単位.取引者ID≠nullの場合のみ）
                if (!l_ifoOrderUnitRow.getTraderIdIsNull())
                {
                    //1.9.1)getTrader()
                    l_trader =
                        l_finObjectManager.getTrader(l_ifoOrderUnitRow.getTraderId());
                }
                l_market =
                    (WEB3GentradeMarket)l_finObjectManager.getMarket(l_ifoOrderUnitRow.getMarketId());
                l_productImpl =
                    (WEB3IfoProductImpl)l_ifoProductManager.getProduct(l_ifoOrderUnitRow.getProductId());

            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //注文期限区分 = 先物OPデータアダプタ.get注文期限区分(注文単位)
            String l_strExpirationDateType = WEB3IfoDataAdapter.getExpirationDateType(l_orderUnit);

            //夕場前繰越対象フラグ = 先物OPデータアダプタ.get夕場前繰越対象フラグ（PR層）(注文単位)
            boolean l_blnEveningSessionCarryoverFlag =
                WEB3IfoDataAdapter.getEveningSessionCarryOverFlagPr(l_orderUnit);

            //1.10)create返済注文内容
            double l_dblWLimitPrice = l_ifoOrderUnitRow.getWLimitPrice();
            IfoOrderExecutionConditionType l_wLimitExecCondType =
                l_ifoOrderUnitRow.getWLimitExecCondType();
            Timestamp l_expirationDate = l_ifoOrderUnitRow.getExpirationDate();

            //発注日
            Date l_datBiz = l_datBizDate;

            WEB3IfoChangeSettleContractOrderSpec l_ifoChangeSettleContractOrderSpec =
                WEB3IfoChangeSettleContractOrderSpec.createIfoChangeSettleContractOrderSpec(
                    l_ifoOrderUnitRow.getOrderId(),
                    l_ifoOrderUnitRow.getOrderUnitId(),
                    l_dblWLimitPrice,
                    l_settleContractEntryList,
                    l_wLimitExecCondType,
                    l_expirationDate,
                    l_datBiz,
                    l_ifoOrderUnitRow.getOrderConditionType(),
                    l_ifoOrderUnitRow.getOrderCondOperator(),
                    l_ifoOrderUnitRow.getStopPriceType(),
                    l_ifoOrderUnitRow.getStopOrderPrice(),
                    l_dblWLimitPrice,
                    l_wLimitExecCondType,
                    WEB3IfoWLimitEnableStatusDivDef.STOP_ENABLE,
                    l_strExpirationDateType,
                    l_blnEveningSessionCarryoverFlag);

            //isSkip遅延状況チェック
            boolean l_blnIsSkipDelayStatusCheck = true;
            //1.11)validate先物返済訂正注文()
            OrderValidationResult l_orderValidationResult =
                l_futuresOrderManagerImpl.validateFuturesChangeSettleContractOrder(
                    l_subAccount,
                    l_ifoChangeSettleContractOrderSpec,
                    l_blnIsSkipDelayStatusCheck);

            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("ProcessingResult() = " + l_orderValidationResult.getProcessingResult());
                throw new WEB3BusinessLayerException(
                        l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            //1.12)create手数料()
            WEB3IfoBizLogicProvider l_bizLogicProvider =
                (WEB3IfoBizLogicProvider)l_tradingModule.getBizLogicProvider();

            WEB3GentradeCommission l_commission =
                l_bizLogicProvider.createCommission(l_ifoOrderUnitRow.getOrderUnitId());

            //1.13)is指値（切替後）()
            l_commission.setIsLimitPrice(this.isLimitPriceAfterSwitch(l_orderUnit));

            //1.13)get取引銘柄()
            WEB3IfoTradedProductImpl l_ifoTradedProduct = null;
            try
            {
                l_ifoTradedProduct =
                    l_ifoProductManager.getIfoTradedProduct(
                        l_subAccount.getInstitution(),
                        l_productImpl.getProductCode(),
                        l_market.getMarketCode());
            }
            catch (NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //買売:        
            //注文単位.getSide()＝SideEnum.BUY(買)（=売建買返済）の場合、”売”をセット。
            //注文単位.getSide()＝SideEnum.SELL(売)（=買建売返済）の場合、”買”をセット。
            SideEnum l_side = null;        
            if (SideEnum.BUY.equals(l_orderUnit.getSide()))
            {
                l_side = SideEnum.SELL;
            }
            else if (SideEnum.SELL.equals(l_orderUnit.getSide()))
            {
                l_side = SideEnum.BUY;
            }

            //1.15) calc訂正時概算決済損益()
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult =
                l_futuresOrderManagerImpl.calcChangeEstimateSettlementIncome(
                    l_commission,
                    l_ifoChangeSettleContractOrderSpec.getAfterChangePrice(),
                    l_subAccount,
                    l_ifoTradedProduct,
                    l_settleContractEntryList,
                    l_ifoChangeSettleContractOrderSpec.getAfterChangeTotalQuantity(),
                    l_side,
                    l_ifoOrderUnitRow.getExecutedQuantity(),
                    l_ifoOrderUnitRow.getOrderUnitId(),
                    false);

            //1.16.1.1)W指値注文先物OP切替更新インタセプタ()
            WEB3ToWLimitIfoSwitchUpdateInterceptor l_interceptor =
                new WEB3ToWLimitIfoSwitchUpdateInterceptor(
                    (WEB3GentradeTrader)l_trader,
                    l_ifoEstimateDeliveryAmountCalcResult,
                    l_ifoChangeSettleContractOrderSpec);

            //1.15.1.3)setThreadLocalPersistenceEventInterceptor()
            l_futuresOrderManagerImpl.setThreadLocalPersistenceEventInterceptor(l_interceptor);

            //1.15.1.4)submitChangeSettleContractOrder()
            String l_strTradingPasswood =
                l_subAccount.getMainAccount().getTradingPassword();
            WEB3Crypt l_crypt = new WEB3Crypt();
            String l_strDecryptPassword = l_crypt.decrypt(l_strTradingPasswood);
            OrderSubmissionResult l_orderSubmissionResult =
                l_futuresOrderManagerImpl.submitChangeSettleContractOrder(
                    l_subAccount,
                    l_ifoChangeSettleContractOrderSpec,
                    l_strDecryptPassword,
                    true);

            if (l_orderSubmissionResult.getProcessingResult().isFailedResult())
            {
                log.debug("ProcessingResult() = "
                    + l_orderSubmissionResult.getProcessingResult());
                throw new WEB3BusinessLayerException(
                    l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }
        catch (Exception l_ex)
        {
            //1.15.2.1)get注文エラー理由コード(ErrorInfo)
            log.error(l_ex.getMessage(), l_ex);
            ErrorInfo l_errorInfo = null;
            if (l_ex instanceof WEB3BaseException)
            {
                l_errorInfo = ((WEB3BaseException)l_ex).getErrorInfo();
            }
            else if (l_ex instanceof WEB3BaseRuntimeException)
            {
                l_errorInfo = ((WEB3BaseRuntimeException)l_ex).getErrorInfo();
            }
            String l_strErrorReasonCode = null;
            if (l_errorInfo != null)
            {
                l_strErrorReasonCode =
                    l_futuresOrderManagerImpl.getErrorReasonCode(l_errorInfo.getErrorCode());
            } 

            //1.15.3) update切替失敗()
            this.updateSwitchFail(l_orderUnit, l_strErrorReasonCode);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (submitOP新規建W指値注文)<BR>
     * オプション新規建W指値注文切替処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（W指値注文先物OP切替一件サービス）submitOP新規建W指値注文」参照。<BR>
     * @@param l_orderUnit - (先物OP注文単位)<BR>
     * 先物オプション注文単位オブジェクト。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44923E37012F
     */
    public void submitOptionOpenContractWLimitOrder(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitOptionOpenContractWLimitOrder(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("先物OP注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "先物OP注文単位 = null。");
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

        //引数で渡された注文単位は更新されている可能性があるので、再取得を行うこと。
        try
        {
            l_orderUnit =
                (IfoOrderUnit)l_optionOrderManagerImpl.getOrderUnit(
                    l_orderUnit.getOrderUnitId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("注文単位テーブルに該当するデータがありません。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

        //1.1)getSubAccount()
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeSubAccount l_subAccount = null;
        try
        {
            l_subAccount = (WEB3GentradeSubAccount)l_gentradeAccountManager.getSubAccount(
                l_orderUnit.getAccountId(), l_orderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.2)is処理対象()
        boolean l_blnIsProcessObject = this.isProcessObject(l_orderUnit);

        //1.3)（分岐フロー：　@is処理対象＝false（処理対象外注文）の場合）
        if (!l_blnIsProcessObject)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //1.4)validate切替処理可能()
        this.validateSwitchPossible(l_orderUnit);

        //1.5) (*)validate切替処理可能()にて例外がスローされた場合
        try
        {
            //1.6) get発注日(確認時発注日 : Date, 立会区分 : String)
            Date l_datBizDate =
                WEB3DateUtility.getDate(l_ifoOrderUnitRow.getBizDate(), "yyyyMMdd");
            WEB3GentradeTradingTimeManagement.getOrderBizDate(l_datBizDate, l_ifoOrderUnitRow.getSessionType());

            //1.7)（分岐フロー：注文単位.取引者ID≠nullの場合のみ）
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            WEB3IfoProductManagerImpl l_ifoProductManager =
                (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();

            Trader l_trader = null;
            WEB3GentradeMarket l_market = null;
            WEB3IfoProductImpl l_productImpl = null;

            try
            {
                if (!l_ifoOrderUnitRow.getTraderIdIsNull())
                {
                    l_trader =
                        l_finObjectManager.getTrader(l_ifoOrderUnitRow.getTraderId());
                }
                l_market =
                    (WEB3GentradeMarket)l_finObjectManager.getMarket(l_ifoOrderUnitRow.getMarketId());
                l_productImpl =
                    (WEB3IfoProductImpl)l_ifoProductManager.getProduct(l_ifoOrderUnitRow.getProductId());

            }
            catch (NotFoundException l_nfe)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //注文期限区分 = 先物OPデータアダプタ.get注文期限区分(注文単位)
            String l_strExpirationDateType = WEB3IfoDataAdapter.getExpirationDateType(l_orderUnit);

            //夕場前繰越対象フラグ = 先物OPデータアダプタ.get夕場前繰越対象フラグ（PR層）(注文単位)
            boolean l_blnEveningSessionCarryoverFlag =
                WEB3IfoDataAdapter.getEveningSessionCarryOverFlagPr(l_orderUnit);

            //1.8)create新規建注文内容()
            double l_dblQuantity = l_ifoOrderUnitRow.getQuantity();
            double l_dblWLimitPrice = l_ifoOrderUnitRow.getWLimitPrice();
            IfoOrderExecutionConditionType l_wLimitExecCondType =
                l_ifoOrderUnitRow.getWLimitExecCondType();
            Timestamp l_expirationDate = l_ifoOrderUnitRow.getExpirationDate();

            //発注日
            Date l_datBiz = l_datBizDate;

            WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec =
                WEB3IfoOpenContractChangeSpec.createIfoOpenContractChangeSpec(
                    l_ifoOrderUnitRow.getOrderId(),
                    l_ifoOrderUnitRow.getOrderUnitId(),
                    l_dblQuantity,
                    l_dblWLimitPrice,
                    l_wLimitExecCondType,
                    l_expirationDate,
                    l_datBiz,
                    l_ifoOrderUnitRow.getOrderConditionType(),
                    l_ifoOrderUnitRow.getOrderCondOperator(),
                    l_ifoOrderUnitRow.getStopPriceType(),
                    l_ifoOrderUnitRow.getStopOrderPrice(),
                    l_dblWLimitPrice,
                    l_wLimitExecCondType,
                    WEB3IfoWLimitEnableStatusDivDef.STOP_ENABLE,
                    l_strExpirationDateType,
                    l_blnEveningSessionCarryoverFlag);

            //isSkip遅延状況チェック
            boolean l_blnIsSkipDelayStatusCheck = true;
            //1.9) validate新規建訂正注文()
            OrderValidationResult l_orderValidationResult =
                l_optionOrderManagerImpl.validateChangeOrder(
                    l_subAccount,
                    l_ifoOpenContractChangeSpec,
                    l_blnIsSkipDelayStatusCheck);

            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("ProcessingResult() = " + l_orderValidationResult.getProcessingResult());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                        l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //1.10)create手数料()
            WEB3IfoBizLogicProvider l_bizLogicProvider =
                (WEB3IfoBizLogicProvider)l_tradingModule.getBizLogicProvider();

            WEB3GentradeCommission l_commission =
                l_bizLogicProvider.createCommission(l_ifoOrderUnitRow.getOrderUnitId());

            //1.11)is指値()
            l_commission.setIsLimitPrice(this.isLimitPriceAfterSwitch(l_orderUnit));

            //1.11)get取引銘柄()
            WEB3IfoTradedProductImpl l_ifoTradedProduct = null;
            try
            {
                l_ifoTradedProduct =
                    l_ifoProductManager.getIfoTradedProduct(
                        l_subAccount.getInstitution(),
                        l_productImpl.getProductCode(),
                        l_market.getMarketCode());
            }
            catch (NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //1.13)calc訂正時概算受渡代金()
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult =
                l_optionOrderManagerImpl.calcChangeEstimateDeliveryAmount(
                    l_commission,
                    l_ifoOpenContractChangeSpec.getAfterChangePrice(),
                    l_subAccount,
                    l_ifoTradedProduct,
                    l_ifoOpenContractChangeSpec.getAfterChangeOriginalQuantity(),
                    l_orderUnit.getSide(),
                    false,
                    l_ifoOrderUnitRow.getExecutedQuantity(),
                    l_ifoOrderUnitRow.getExecutedAmount(),
                    false);

            //1.14)先物OP新規建訂正更新インタセプタ()
            WEB3IfoOpenContractChangeUpdateInterceptor l_interceptor =
                new WEB3IfoOpenContractChangeUpdateInterceptor(l_ifoOpenContractChangeSpec);

            //1.15)(*)プロパティセット
            //手数料           ＝ 作成した手数料オブジェクト
            l_interceptor.setCommision(l_commission);

            //概算受渡代金計算結果    ＝ calc訂正時概算受渡代金()の戻り値
            l_interceptor.setEstimateDeliveryAmountCalcResult(l_ifoEstimateDeliveryAmountCalcResult);

            //発注条件          ＝ 注文単位の同名項目
            l_interceptor.setOrderCond(l_ifoOrderUnitRow.getOrderConditionType());

            //発注条件演算子       ＝ 注文単位の同名項目
            l_interceptor.setOrderCondOperator(l_ifoOrderUnitRow.getOrderCondOperator());

            //逆指値基準値タイプ     ＝ 注文単位の同名項目
            l_interceptor.setStopOrderBasePriceType(l_ifoOrderUnitRow.getStopPriceType());

            //逆指値基準値        ＝ 注文単位の同名項目
            l_interceptor.setStopOrderBasePrice(l_ifoOrderUnitRow.getStopOrderPrice());

            //（W指値）訂正指値     ＝ 注文単位の同名項目
            l_interceptor.setWLimitPriceChange(l_ifoOrderUnitRow.getWLimitPrice());

            //取引者ID         ＝ 注文単位.取引者ID＝nullの場合、0（固定）。以外、注文単位.取引者ID
            if (l_ifoOrderUnitRow.getTraderIdIsNull())
            {
                l_interceptor.setTraderId(0);
            }
            else
            {
                l_interceptor.setTraderId(l_ifoOrderUnitRow.getTraderId());
            }

            //注文経路区分        ＝ 注文単位の同名項目
            l_interceptor.setOrderRootDiv(l_ifoOrderUnitRow.getOrderRootDiv());

            //1.16)validate取引余力()
            WEB3TPTradingPowerService l_tpTradingPowerService =
                (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            WEB3TPTradingPowerResult l_tpResult =
                l_tpTradingPowerService.validateTradingPower(
                    l_subAccount,
                    new Object[]{l_interceptor},
                    new Object[]{l_ifoOpenContractChangeSpec},
                    l_ifoOrderUnitRow.getOrderType(),
                    true);

            //1.17)throw余力エラー情報()
            if (!l_tpResult.isResultFlg())
            {
                log.debug("取引余力チェックエラー。");
                l_optionOrderManagerImpl.throwTpErrorInfo(l_tpResult, l_subAccount);
            }

            //1.18) (実行結果に応じて注文系データをUPDATEする)
            //1.18.1(*)正常終了した場合
            //1.18.1.1)W指値注文先物OP切替更新インタセプタ
            WEB3ToWLimitIfoSwitchUpdateInterceptor l_wLimitIfoSwitchUpdateInterceptor =
                new WEB3ToWLimitIfoSwitchUpdateInterceptor(
                    (WEB3GentradeTrader)l_trader,
                    l_ifoEstimateDeliveryAmountCalcResult,
                    l_ifoOpenContractChangeSpec);

            //1.18.2)setThreadLocalPersistenceEventInterceptor()
            l_optionOrderManagerImpl.setThreadLocalPersistenceEventInterceptor(
                l_wLimitIfoSwitchUpdateInterceptor);

            //1.18.3)submitChangeOrder()
            String l_strTradingPasswood =
                l_subAccount.getMainAccount().getTradingPassword();
            WEB3Crypt l_crypt = new WEB3Crypt();
            String l_strDecryptPassword = l_crypt.decrypt(l_strTradingPasswood);
            OrderSubmissionResult l_orderSubmissionResult =
                l_optionOrderManagerImpl.submitChangeOrder(
                    l_subAccount,
                    l_ifoOpenContractChangeSpec,
                    l_strDecryptPassword,
                    true);

            if (l_orderSubmissionResult.getProcessingResult().isFailedResult())
            {
                log.debug("ProcessingResult() = " + l_orderSubmissionResult.getProcessingResult());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }
        catch (Exception l_ex)
        {
            //1.18.2.1)get注文エラー理由コード(ErrorInfo)
            log.error(l_ex.getMessage(), l_ex);
            ErrorInfo l_errorInfo = null;
            if (l_ex instanceof WEB3BaseException)
            {
                l_errorInfo = ((WEB3BaseException)l_ex).getErrorInfo();
            }
            else if (l_ex instanceof WEB3BaseRuntimeException)
            {
                l_errorInfo = ((WEB3BaseRuntimeException)l_ex).getErrorInfo();
            }
            String l_strErrorReasonCode = null;
            if (l_errorInfo != null)
            {
                l_strErrorReasonCode =
                    l_optionOrderManagerImpl.getErrorReasonCode(l_errorInfo.getErrorCode());
            }

            //1.18.2.2) update切替失敗()
            this.updateSwitchFail(l_orderUnit, l_strErrorReasonCode);

            //1.18.2.3) (*)オプション買建口座（補助口座.補助口座タイプ≠"株式オプション取引口座（先物証拠金））の場合
            if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()))
            {
                //1.18.2.3.1)余力再計算(補助口座 : 補助口座)(取引余力サービス::余力再計算)
                WEB3TPTradingPowerService l_tpTradingPowerService =
                    (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
                l_tpTradingPowerService.reCalcTradingPower(l_subAccount);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (submitOP返済W指値注文)<BR>
     * オプション返済W指値注文切替処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（W指値注文先物OP切替一件サービス）submitOP返済W指値注文」参照。<BR>
     *  ========================================================== <BR>
     * 1.8.1)(*)返済可能数量チェック　@<BR>
     * ３）返済数量(*1) ＞ 返済可能建玉残高(*2)　@の場合、<BR>
     * 　@「返済可能残高数量超過エラー」の例外をthrowする。 <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00299<BR>
     *  ========================================================== <BR>
     * @@param l_orderUnit - (先物OP注文単位)<BR>
     * 先物オプション注文単位オブジェクト。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44923E37014F
     */
    public void submitOptionSettleContractWLimitOrder(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitOptionSettleContractWLimitOrder(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("先物OP注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "先物OP注文単位 = null。");
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

        //引数で渡された注文単位は更新されている可能性があるので、再取得を行うこと。
        try
        {
            l_orderUnit =
                (IfoOrderUnit)l_optionOrderManagerImpl.getOrderUnit(
                    l_orderUnit.getOrderUnitId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("注文単位テーブルに該当するデータがありません。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

        //1.1)getSubAccount()
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeSubAccount l_subAccount = null;
        try
        {
            l_subAccount = (WEB3GentradeSubAccount)l_gentradeAccountManager.getSubAccount(
                l_orderUnit.getAccountId(), l_orderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.2)is処理対象()
        boolean l_blnIsProcessObject = this.isProcessObject(l_orderUnit);

        //1.2.1)（分岐フロー：　@is処理対象＝false（処理対象外注文）の場合）
        if (!l_blnIsProcessObject)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //1.3)validate切替処理可能()
        this.validateSwitchPossible(l_orderUnit);

        //1.4) (*)validate切替処理可能()にて例外がスローされた場合
        try
        {
            //1.5) get発注日(確認時発注日 : Date, 立会区分 : String)
            Date l_datBizDate =
                WEB3DateUtility.getDate(l_ifoOrderUnitRow.getBizDate(), "yyyyMMdd");
            WEB3GentradeTradingTimeManagement.getOrderBizDate(l_datBizDate, l_ifoOrderUnitRow.getSessionType());

            IfoContractSettleOrderUnit l_contractSettleOrderUnit =
                (IfoContractSettleOrderUnit)l_orderUnit;

            //1.6)getContractsToClose()
            IfoClosingContractSpec[] l_ifoClosingContractSpecList =
                l_contractSettleOrderUnit.getContractsToClose();

            //1.7)ArrayList()
            List l_lisSettleContractEntry = new ArrayList();

            //1.8)（getContractsToClose( )の戻り値（＝建玉返済指定情報）要素数(index)分、Loop）
            int l_intLength = l_ifoClosingContractSpecList.length;
            for (int i = 0; i < l_intLength; i++)
            {
                //1.8.1)(*)返済可能数量チェック
                //建玉返済指定情報[index].返済注文数量
                double l_dblContractsQuantity = l_ifoClosingContractSpecList[i].getQuantity();
                BigDecimal l_bdContractsQuantity = new BigDecimal(l_dblContractsQuantity);

                //建玉返済指定情報[index].返済約定数量
                double l_dblExecutedQuantity = l_ifoClosingContractSpecList[i].getExecutedQuantity();
                BigDecimal l_bdExecutedQuantity = new BigDecimal(l_dblExecutedQuantity);

                //　@返済数量 ＝ 建玉返済指定情報[index].返済注文数量 － 建玉返済指定情報[index].返済約定数量
                BigDecimal l_bdCloseQuantity = l_bdContractsQuantity.subtract(l_bdExecutedQuantity);

                //1.8.1.1)先物OP建玉()
                WEB3IfoContractImpl l_ifoContractImpl = null;
                try
                {
                    l_ifoContractImpl = new WEB3IfoContractImpl(l_ifoClosingContractSpecList[i].getContractId());
                }
                catch (DataNetworkException l_dne)
                {
                    log.error(STR_METHOD_NAME, l_dne);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                catch (DataQueryException l_dqe)
                {
                    log.error(STR_METHOD_NAME, l_dqe);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + STR_METHOD_NAME);
                }

                //1.8.1.2)getQuantity( )
                double l_dblQuantity = l_ifoContractImpl.getQuantity();
                BigDecimal l_bdQuantity = new BigDecimal(l_dblQuantity);

                //1.8.1.3)getLockedQuantity( )
                double l_dblLockedQuantity = l_ifoContractImpl.getLockedQuantity();
                BigDecimal l_bdLockedQuantity = new BigDecimal(l_dblLockedQuantity);

                //1.8.1.4)getロック中数量()
                double l_dblLockedQuantityReal = l_ifoContractImpl.getLockedQuantity(l_orderUnit.getOrderUnitId());
                BigDecimal l_bdLockedQuantityReal = new BigDecimal(l_dblLockedQuantityReal);

                //返済可能建玉残高 ＝ 建玉.getQuantity()（＝建玉数量） － 建玉.getLockedQuantity()（＝ロック中数量）
                //＋ 建玉.getロック中数量(注文単位ID)（＝当該注文ロック中数量
                BigDecimal l_bdContractBalance = l_bdQuantity.subtract(l_bdLockedQuantity).add(l_bdLockedQuantityReal);

                //返済数量(*1) ＞ 返済可能建玉残高(*2)　@の場合、「返済可能残高数量超過エラー」の例外をthrowする。
                if (l_bdCloseQuantity.compareTo(l_bdContractBalance) > 0)
                {
                    log.debug("返済可能残高数量超過エラー");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00299,
                        this.getClass().getName() + STR_METHOD_NAME);
                }

                //1.8.1.5)SettleContractEntry()
                SettleContractEntry l_settleContractEntry =
                    new SettleContractEntry(
                        l_ifoClosingContractSpecList[i].getContractId(),
                        l_dblContractsQuantity);

                //1.8.1.6)add()
                l_lisSettleContractEntry.add(l_settleContractEntry);
            }

            SettleContractEntry[] l_settleContractEntryList =
                new SettleContractEntry[l_lisSettleContractEntry.size()];
            l_lisSettleContractEntry.toArray(l_settleContractEntryList);

            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            WEB3IfoProductManagerImpl l_ifoProductManager =
                (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();

            Trader l_trader = null;
            WEB3GentradeMarket l_market = null;
            WEB3IfoProductImpl l_productImpl = null;

            try
            {
                //1.9)（分岐フロー：注文単位.取引者ID≠nullの場合のみ）
                if (!l_ifoOrderUnitRow.getTraderIdIsNull())
                {
                    //1.9.1)getTrader()
                    l_trader =
                        l_finObjectManager.getTrader(l_ifoOrderUnitRow.getTraderId());
                }
                l_market =
                    (WEB3GentradeMarket)l_finObjectManager.getMarket(l_ifoOrderUnitRow.getMarketId());
                l_productImpl =
                    (WEB3IfoProductImpl)l_ifoProductManager.getProduct(l_ifoOrderUnitRow.getProductId());

            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //注文期限区分 = 先物OPデータアダプタ.get注文期限区分(注文単位)
            String l_strExpirationDateType = WEB3IfoDataAdapter.getExpirationDateType(l_orderUnit);

            //夕場前繰越対象フラグ = 先物OPデータアダプタ.get夕場前繰越対象フラグ（PR層）(注文単位)
            boolean l_blnEveningSessionCarryoverFlag =
                WEB3IfoDataAdapter.getEveningSessionCarryOverFlagPr(l_orderUnit);

            //1.10)create返済注文内容()
            double l_dblWLimitPrice = l_ifoOrderUnitRow.getWLimitPrice();
            IfoOrderExecutionConditionType l_wLimitExecCondType =
                l_ifoOrderUnitRow.getWLimitExecCondType();
            Timestamp l_expirationDate = l_ifoOrderUnitRow.getExpirationDate();

            //発注日
            Date l_datBiz = l_datBizDate;

            WEB3IfoChangeSettleContractOrderSpec l_ifoChangeSettleContractOrderSpec =
                WEB3IfoChangeSettleContractOrderSpec.createIfoChangeSettleContractOrderSpec(
                    l_ifoOrderUnitRow.getOrderId(),
                    l_ifoOrderUnitRow.getOrderUnitId(),
                    l_dblWLimitPrice,
                    l_settleContractEntryList,
                    l_wLimitExecCondType,
                    l_expirationDate,
                    l_datBiz,
                    l_ifoOrderUnitRow.getOrderConditionType(),
                    l_ifoOrderUnitRow.getOrderCondOperator(),
                    l_ifoOrderUnitRow.getStopPriceType(),
                    l_ifoOrderUnitRow.getStopOrderPrice(),
                    l_dblWLimitPrice,
                    l_wLimitExecCondType,
                    WEB3IfoWLimitEnableStatusDivDef.STOP_ENABLE,
                    l_strExpirationDateType,
                    l_blnEveningSessionCarryoverFlag);

            //isSkip遅延状況チェック
            boolean l_blnIsSkipDelayStatusCheck = true;
            //1.11)validate返済訂正注文()
            OrderValidationResult l_orderValidationResult =
                l_optionOrderManagerImpl.validateChangeSettleContractOrder(
                    l_subAccount,
                    l_ifoChangeSettleContractOrderSpec,
                    l_blnIsSkipDelayStatusCheck);

            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("ProcessingResult() = " + l_orderValidationResult.getProcessingResult());
                throw new WEB3BusinessLayerException(
                        l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //1.12)create手数料()
            WEB3IfoBizLogicProvider l_bizLogicProvider =
                (WEB3IfoBizLogicProvider)l_tradingModule.getBizLogicProvider();

            WEB3GentradeCommission l_commission =
                l_bizLogicProvider.createCommission(l_ifoOrderUnitRow.getOrderUnitId());

            //1.13)is指値()
            l_commission.setIsLimitPrice(this.isLimitPriceAfterSwitch(l_orderUnit));

            //1.13)get取引銘柄()
            WEB3IfoTradedProductImpl l_ifoTradedProduct = null;
            try
            {
                l_ifoTradedProduct =
                    l_ifoProductManager.getIfoTradedProduct(
                        l_subAccount.getInstitution(),
                        l_productImpl.getProductCode(),
                        l_market.getMarketCode());
            }
            catch (NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //1.15)calc訂正時概算受渡代金()
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult =
                l_optionOrderManagerImpl.calcChangeEstimateDeliveryAmount(
                    l_commission,
                    l_ifoChangeSettleContractOrderSpec.getAfterChangePrice(),
                    l_subAccount,
                    l_ifoTradedProduct,
                    l_ifoChangeSettleContractOrderSpec.getAfterChangeTotalQuantity(),
                    l_orderUnit.getSide(),
                    true,
                    l_ifoOrderUnitRow.getExecutedQuantity(),
                    l_ifoOrderUnitRow.getExecutedAmount(),
                    false);

            //1.16.1.1)W指値注文先物OP切替更新インタセプタ()
            WEB3ToWLimitIfoSwitchUpdateInterceptor l_interceptor =
                new WEB3ToWLimitIfoSwitchUpdateInterceptor(
                    (WEB3GentradeTrader)l_trader,
                    l_ifoEstimateDeliveryAmountCalcResult,
                    l_ifoChangeSettleContractOrderSpec);

            //1.15.1.3)setThreadLocalPersistenceEventInterceptor()
            l_optionOrderManagerImpl.setThreadLocalPersistenceEventInterceptor(l_interceptor);

            //1.18.3)submitChangeSettleContractOrder()
            String l_strTradingPasswood =
                l_subAccount.getMainAccount().getTradingPassword();
            WEB3Crypt l_crypt = new WEB3Crypt();
            String l_strDecryptPassword = l_crypt.decrypt(l_strTradingPasswood);
            OrderSubmissionResult l_orderSubmissionResult =
                l_optionOrderManagerImpl.submitChangeSettleContractOrder(
                    l_subAccount,
                    l_ifoChangeSettleContractOrderSpec,
                    l_strDecryptPassword,
                    true);

            if (l_orderSubmissionResult.getProcessingResult().isFailedResult())
            {
                log.debug("ProcessingResult() = " + l_orderSubmissionResult.getProcessingResult());
                throw new WEB3BusinessLayerException(
                    l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }
        catch (Exception l_ex)
        {
            //1.18.2.1)get注文エラー理由コード(ErrorInfo)
            log.error(l_ex.getMessage(), l_ex);
            ErrorInfo l_errorInfo = null;
            if (l_ex instanceof WEB3BaseException)
            {
                l_errorInfo = ((WEB3BaseException)l_ex).getErrorInfo();
            }
            else if (l_ex instanceof WEB3BaseRuntimeException)
            {
                l_errorInfo = ((WEB3BaseRuntimeException)l_ex).getErrorInfo();
            }
            String l_strErrorReasonCode = null;
            if (l_errorInfo != null)
            {
                l_strErrorReasonCode =
                    l_optionOrderManagerImpl.getErrorReasonCode(l_errorInfo.getErrorCode());
            }

            //1.18.2.2) update切替失敗()
            this.updateSwitchFail(l_orderUnit, l_strErrorReasonCode);
        }

        //1.16) (*)オプション買建口座（補助口座.補助口座タイプ≠"株式オプション取引口座（先物証拠金））の場合
        if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()))
        {
            //1.16.1)余力再計算(補助口座 : 補助口座)(取引余力サービス::余力再計算)
            WEB3TPTradingPowerService l_tpTradingPowerService =
                (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            l_tpTradingPowerService.reCalcTradingPower(l_subAccount);
        }

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (is処理対象)<BR>
     * 指定の注文がW指値切替の処理対象であるかを判定する。<BR>
     * 処理対象の場合、trueを、処理対象外の場合、falseを返却する。<BR>
     * <BR>
     * 以下の条件のいずれかに該当する場合、<BR>
     * 処理対象外とし、falseを返却する。以外、trueを返却する。<BR>
     * <BR>
     * ・パラメータ.注文単位.注文有効状態 != "オープン"<BR>
     * ・パラメータ.注文単位.発注条件 != "W指値"<BR>
     * ・パラメータ.注文単位.リクエストタイプ == "時価サーバ"<BR>
     * 　@ or "切替完了" or "失効"<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 44924963027C
     */
    protected boolean isProcessObject(IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isProcessObject(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("先物OP注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "先物OP注文単位 = null。");
        }

        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        if (!OrderOpenStatusEnum.OPEN.equals(l_ifoOrderUnitRow.getOrderOpenStatus())
            || !WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_ifoOrderUnitRow.getOrderConditionType())
            || WEB3RequestTypeDef.QUOTE_SERVER.equals(l_ifoOrderUnitRow.getRequestType())
            || WEB3RequestTypeDef.TRANSFERED.equals(l_ifoOrderUnitRow.getRequestType())
            || WEB3RequestTypeDef.INVALIDATE.equals(l_ifoOrderUnitRow.getRequestType()))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (validate切替処理可能)<BR>
     * 指定の注文がW指値切替の処理可能であるかどうかチェックする。<BR>
     * <BR>
     * １）　@注文状態チェック<BR>
     * 　@注文が市場未送信（*）の場合、または、<BR>
     * 　@パラメータ.注文単位.注文状態が以下のいずれかに<BR>
     * 　@該当する場合、「受付中／訂正中／取消中の注文は切替処理不可」の<BR>
     * 　@例外をスローする。<BR>
     * 　@　@・"受付済（変更注文）"<BR>
     * 　@　@・"発注中（変更注文）"<BR>
     * 　@　@・"受付済（取消注文）"<BR>
     * 　@　@・"発注中（取消注文）"<BR>
     * <BR>
     * （*）注文単位.市場から確認済みの数量＝NaNの場合、<BR>
     * 　@市場未送信の注文と判定する。<BR>
     * class:WEB3BusinessLayerException<BR>
     * tag  :BUSINESS_ERROR_02521<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。
     * @@throws WEB3BaseException
     * @@roseuid 449249EE00E5
     */
    protected void validateSwitchPossible(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateSwitchPossible(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("先物OP注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "先物OP注文単位 = null。");
        }
        OrderStatusEnum l_orderStatus = l_orderUnit.getOrderStatus();
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        if (l_ifoOrderUnitRow.getConfirmedQuantityIsNull()
            || OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatus)
            || OrderStatusEnum.MODIFYING.equals(l_orderStatus)
            || OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderStatus)
            || OrderStatusEnum.CANCELLING.equals(l_orderStatus))
        {
            log.debug("受付中／訂正中／取消中の注文は切替処理不可。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02521,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (update切替失敗)<BR>
     * 引数の注文単位を切替失敗状態に更新する。<BR>
     * <BR>
     * OP注文マネージャ.update注文データ()をコールする。<BR>
     * <BR>
     * [update注文データ()に指定する引数] <BR>
     * 　@注文単位：　@更新値をセットした注文単位 <BR>
     * 　@is履歴作成：　@true（作成する）<BR>
     * <BR>
     * ※更新値の設定仕様については、<BR>
     * DB更新仕様 <BR>
     * 「W指値注文切替（NG）_注文単位テーブル仕様」参照。<BR>
     * <BR>
     * ※パラメータ.注文単位.注文種別 == "OP新規買建注文"の場合、<BR>
     * 　@以下の手順にて概算受渡代金の再計算を行う。 <BR>
     * 　@（ストップ注文の注文単価で余力を拘束されている可能性がある為、<BR>
     * 　@　@リミット注文の注文単価で再計算を行う。） <BR>
     * <BR>
     * 　@OP注文マネージャ.getストップ注文失効時概算代金計算結果()をコールする。<BR>
     * <BR>
     * 　@[getストップ注文失効時概算代金計算結果()に指定する引数]<BR>
     * 　@　@注文単位：　@引数.注文単位<BR>
     * 　@　@補助口座：　@引数.注文単位.補助口座IDに該当する補助口座<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@param l_strErrorReasonCode - (注文エラー理由コード)<BR>
     * 注文エラー理由コード<BR>
     * <BR>
     * DBレイアウト<BR>
     * 注文単位テーブル仕様.xls<BR>
     * 「（注文単位テーブル補足）注文エラー理由コード」シート参照。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 449634E80241
     */
    protected void updateSwitchFail(IfoOrderUnit l_orderUnit, String l_strErrorReasonCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateSwitchFail(IfoOrderUnit, String)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("先物OP注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "先物OP注文単位 = null。");
        }
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

        IfoOrderUnitParams l_ifoOrderUnitParams = new IfoOrderUnitParams(l_ifoOrderUnitRow);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

        //注文履歴最終通番
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(
            l_ifoOrderUnitParams.getLastOrderActionSerialNo() + 1);

        //元発注条件
        l_ifoOrderUnitParams.setOrgOrderConditionType(
            l_ifoOrderUnitParams.getOrderConditionType());

        //元発注条件演算子
        l_ifoOrderUnitParams.setOrgOrderCondOperator(
            l_ifoOrderUnitParams.getOrderCondOperator());

        //元逆指値基準値タイプ
        l_ifoOrderUnitParams.setOrgStopPriceType(
            l_ifoOrderUnitParams.getStopPriceType());

        //元逆指値基準値
        if (l_ifoOrderUnitParams.getStopOrderPriceIsNull())
        {
            l_ifoOrderUnitParams.setOrgStopOrderPrice(null);
        }
        else
        {
            l_ifoOrderUnitParams.setOrgStopOrderPrice(
                l_ifoOrderUnitParams.getStopOrderPrice());
        }

        //元（W指値）訂正指値
        if (l_ifoOrderUnitParams.getWLimitPriceIsNull())
        {
            l_ifoOrderUnitParams.setOrgWLimitPrice(null);
        }
        else
        {
            l_ifoOrderUnitParams.setOrgWLimitPrice(
                l_ifoOrderUnitParams.getWLimitPrice());
        }

        //元（W指値）執行条件
        l_ifoOrderUnitParams.setOrgWLimitExecCondType(
            l_ifoOrderUnitParams.getWLimitExecCondType());

        //発注条件
        l_ifoOrderUnitParams.setOrderConditionType(
            WEB3OrderingConditionDef.DEFAULT);

        //注文状態 11：発注失敗（変更注文）
        l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_MODIFIED);

        //発注条件演算子
        l_ifoOrderUnitParams.setOrderCondOperator(null);

        //逆指値基準値タイプ
        l_ifoOrderUnitParams.setStopPriceType(null);

        //逆指値基準値
        l_ifoOrderUnitParams.setStopOrderPrice(null);

        //（W指値）訂正指値
        l_ifoOrderUnitParams.setWLimitPrice(null);

        //this.注文種別 == "OP新規買建注文"の場合
        if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_ifoOrderUnitParams.getOrderType()))
        {
            //OP注文マネージャ.getストップ注文失効時概算代金計算結果()をコールする。
            //[getストップ注文失効時概算代金計算結果()に指定する引数]
            //注文単位：　@引数.注文単位
            //補助口座：　@引数.注文単位.補助口座IDに該当する補助口座
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                    l_orderUnit.getAccountId(), l_orderUnit.getSubAccountId());
            }
            catch (NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult =
                l_optionOrderManagerImpl.getStopOrderExpireEstimatedPrice(l_orderUnit, l_subAccount);

            if (l_ifoOrderUnitParams.getLimitPriceIsNull())
            {
                //注文単価
                l_ifoOrderUnitParams.setPrice(null);

                //市場から確認済みの注文単価
                l_ifoOrderUnitParams.setConfirmedOrderPrice(null);
            }
            else
            {
                //注文単価
                l_ifoOrderUnitParams.setPrice(l_ifoOrderUnitParams.getLimitPrice());

                //市場から確認済みの注文単価
                l_ifoOrderUnitParams.setConfirmedOrderPrice(
                    l_ifoOrderUnitParams.getLimitPrice());
            }

            //概算受渡代金
            double l_dblEstimateDeliveryAmount =
                l_ifoEstimateDeliveryAmountCalcResult.getEstimateDeliveryAmount();
            l_ifoOrderUnitParams.setEstimatedPrice(l_dblEstimateDeliveryAmount);

            //市場から確認済みの概算受渡代金
            l_ifoOrderUnitParams.setConfirmedEstimatedPrice(l_dblEstimateDeliveryAmount);
        }

        //注文訂正・取消区分
        l_ifoOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFER_ERROR);

        //注文エラー理由コード
        l_ifoOrderUnitParams.setErrorReasonCode(l_strErrorReasonCode);

        //リクエストタイプ
        l_ifoOrderUnitParams.setRequestType(WEB3RequestTypeDef.INVALIDATE);

        //（W指値）執行条件
        l_ifoOrderUnitParams.setWLimitExecCondType(null);
        //更新日付
        l_ifoOrderUnitParams.setLastUpdatedTimestamp(
            GtlUtils.getSystemTimestamp());

        //OP注文マネージャ.update注文データ()をコールする。
        IfoOrderUnit l_orderUnitUpdate =
            (IfoOrderUnit)l_optionOrderManagerImpl.toOrderUnit(l_ifoOrderUnitParams);
        l_optionOrderManagerImpl.updateOrderData(l_orderUnitUpdate, true);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (is指値(切替後))<BR>
     * ストップ注文へ切替後のＷ指値注文が指値注文かどうか判別する。<BR>
     * <BR>
     * パラメータ.注文単位.(Ｗ指値)訂正指値 != 0の場合、<BR>
     * true(指値)を返却する。以外、falseを返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isLimitPriceAfterSwitch(IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isLimitPriceAfterSwitch(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("先物OP注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "先物OP注文単位 = null。");
        }

        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

        if (l_ifoOrderUnitRow.getWLimitPrice() > 0D)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }
}
@
