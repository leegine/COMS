head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.53.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引余力サービスImpl(WEB3TPTradingPowerServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 nakazato(ACT) 新規作成
                   2006/09/11 徐宏偉  (中訊) モデルNo.006
                   2006/09/11 徐宏偉  (中訊) モデルNo.007
                   2006/09/11 徐宏偉  (中訊) モデルNo.009
                   2006/09/25 車進　@  (中訊) モデルNo.047
                   2006/09/25 車進　@  (中訊) モデルNo.048
                   2006/09/25 車進　@  (中訊) モデルNo.049
                   2006/11/13 徐大方  (中訊) モデルNo.071-072
                   2006/11/27 謝旋  (中訊) モデルNo.089、No.090、No.091
                   2007/03/19 宮本 篤東 (SCS) モデルNo.100
Revesion History : 2007/07/12 金傑 (中訊) モデルNo.107
Revesion History : 2007/07/19 孟亜南 (中訊) モデルNo.109-111、モデルNo.118
Revesion History : 2007/08/06 金傑 (中訊) モデルNo.123
Revesion History : 2007/08/17 金傑 (中訊) モデルNo.166
Revesion History : 2007/09/20 孟亜南 (中訊) モデルNo.169
Revesion History : 2007/09/29 トウ鋒鋼 (中訊) モデルNo.190
Revesion History : 2007/10/10 トウ鋒鋼 (中訊) モデルNo.206
Revesion History : 2007/10/12 金傑 (中訊) モデルNo.194
Revesion History : 2007/10/12 金傑 (中訊) モデルNo.209
Revesion History : 2007/10/12 金傑 (中訊) 計算式書No.013
Revesion History : 2007/10/16 孟亞南（中訊）モデルNo.210
Revesion History : 2007/10/15 孟亞南 (中訊) モデルNo.211
Revesion History : 2007/10/18 崔遠鵬（中訊）モデルNo.212
Revesion History : 2007/10/22 孟亞南 (中訊) モデルNo.213 実装の問題No.004
                   2007/11/08 inomata (SCS) モデルNo.229
Revesion History : 2007/12/19 徐宏偉 (中訊) モデルNo.243
Revesion History : 2008/01/18 トウ鋒鋼 (中訊) モデルNo.247
Revesion History : 2008/03/14 崔遠鵬 (中訊) モデルNo.261、No.262
Revesion History : 2008/09/10 張少傑 (中訊) モデルNo.291-295
Revesion History : 2008/10/20 安陽 (中訊) モデルNo.319、320、323、333、334、335、343
Revesion History : 2008/10/31 孟亞南 (中訊) モデルNo.355
Revesion History : 2008/11/21 三島淳一郎 (SCS) モデルNo.372
Revesion History : 2008/12/02 李キョウ モデルNo.377
Revesion History : 2008/12/10 劉剣 (中訊) モデルNo.378、モデルNo.380、モデルNo.381
Revesion History : 2008/12/16 劉剣 (中訊) モデルNo.383、モデルNo.384
                   2008/12/24 三島淳一郎 (SCS) モデルNo.385-387
Revesion History : 2009/01/07 劉剣 (中訊) モデルNo.388
Revesion History : 2009/01/19 李俊 (中訊) モデルNo.389
Revesion History : 2009/12/11 張騰宇 (中訊) モデルNo.404 405 407 433 435 436 437 440 計算式書No.022 No.023
Revesion History : 2010/01/28 武波　@ (中訊)モデルNo.448,449,450,451,452,454
Revesion History : 2010/01/11 武波　@ (中訊)モデルNo.424,No.425,No.428,No.438,No.439
*/
package webbroker3.tradingpower;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.BatchedQuery;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProductManager;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeCancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewCashBasedOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.TradingModuleImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BranchTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.ordersubmitter.io.BondChangeOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.ordersubmitter.io.FeqNewOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitParams;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3CashoutTodayDepositTransferDivDef;
import webbroker3.common.define.WEB3CashoutTradingpowerCheckDef;
import webbroker3.common.define.WEB3DbCurrentPriceCheckDivDef;
import webbroker3.common.define.WEB3DealTypeDef;
import webbroker3.common.define.WEB3SecondDepositMarginOpenTpStopDef;
import webbroker3.gentrade.WEB3GentradeBranchListmarketDealtCond;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.ifodeposit.WEB3IfoDepositCalc;
import webbroker3.ifodeposit.WEB3IfoDepositCalcCondition;
import webbroker3.ifodeposit.WEB3IfoDepositCalcService;
import webbroker3.ifodeposit.WEB3IfoNewOrderSpec;
import webbroker3.tradingpower.data.TpCalcResultEquityDetailRow;
import webbroker3.tradingpower.data.TpCalcResultEquityParams;
import webbroker3.tradingpower.data.TpCalcResultEquityRow;
import webbroker3.tradingpower.data.TpCalcResultMarginDetailParams;
import webbroker3.tradingpower.data.TpCalcResultMarginDetailRow;
import webbroker3.tradingpower.data.TpCalcResultMarginParams;
import webbroker3.tradingpower.data.TpCalcResultMarginRow;
import webbroker3.tradingpower.data.TpCashBalanceParams;
import webbroker3.tradingpower.data.TpCashBalanceRow;
import webbroker3.tradingpower.define.WEB3TPCashoutTodayDepositTransferDivDef;
import webbroker3.tradingpower.define.WEB3TPDepositRealTransferEnforceDivDef;
import webbroker3.tradingpower.define.WEB3TPDoublepositionCheckDef;
import webbroker3.tradingpower.define.WEB3TPEquityBuyTradingPowerCheckTypeDef;
import webbroker3.tradingpower.define.WEB3TPExcludeExceptSettlementBuyAmountCheckDef;
import webbroker3.tradingpower.define.WEB3TPForcedSettleReasonDef;
import webbroker3.tradingpower.define.WEB3TPMarginSwapLongTradingPowerCheckTypeDef;
import webbroker3.tradingpower.define.WEB3TPMarkToMarketDivDef;
import webbroker3.tradingpower.define.WEB3TPMutualFundBuyApplyDateDef;
import webbroker3.tradingpower.define.WEB3TPOrixSecuredLoanLockDef;
import webbroker3.tradingpower.define.WEB3TPResultAttentionObjectionTypeDef;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.define.WEB3TPTradingPowerErrorDivDef;
import webbroker3.tradingpower.updtpower.WEB3TPNewOrderSpec;
import webbroker3.tradingpower.updtpower.WEB3TPPersistentDataManager;
import webbroker3.tradingpower.updtpower.contract.WEB3TPContractInfo;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (取引余力サービスImpl)<BR>
 * 取引余力サービスインターフェイスの実装クラス<BR>
 */
public class WEB3TPTradingPowerServiceImpl implements WEB3TPTradingPowerService
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPTradingPowerServiceImpl.class);

    /**
     * (コンストラクタ)<BR>
     * @@roseuid 418F3DD101B4
     */
    public WEB3TPTradingPowerServiceImpl()
    {

    }

    /**
     * (validate取引余力)<BR>
     * <BR>
     * 引数.注文種別において指定された注文の取引余力チェックを実施する。<BR>
     * <BR>
     * シーケンス図「(取引余力サービス)validate取引余力」参照<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_orderSpecIntercepters - (注文内容インタセプタ)
     * @@param l_orderSpecs - (注文内容)
     * @@param l_orderTypeEnum - (注文種別)
     * @@param l_blnUpdateFlg - (余力更新フラグ)<BR>
     * trueの時、余力再計算処理を実施する<BR>
     * falseの時、余力再計算処理を実施しない<BR>
     * @@throws WEB3SystemLayerException
     * @@return WEB3TPTradingPowerResult
     * @@roseuid 4158CBB901C5
     */
    public WEB3TPTradingPowerResult validateTradingPower(
        WEB3GentradeSubAccount l_subAccount,
        Object[] l_orderSpecIntercepters,
        Object[] l_orderSpecs,
        OrderTypeEnum l_orderTypeEnum,
        boolean l_blnUpdateFlg)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "validateTradingPower(WEB3GentradeSubAccount, Object[], Object[], OrderTypeEnum, boolean)";
        log.entering(STR_METHOD_NAME);

        //引数.補助口座、引数.注文内容インタセプタ、引数.注文内容、引数.注文種別の何れかがnullの場合
        if (l_subAccount == null
            || l_orderSpecIntercepters == null
            || l_orderSpecs == null
            || l_orderTypeEnum == null)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            //取引余力結果
            WEB3TPTradingPowerResult l_tradingPowerResult = null;

            /*
             * 引数.注文内容インセプタ、注文内容より、現注文内容を生成
             */
            //引数.注文内容の要素数を取得する。
            int l_intSize = l_orderSpecs.length;

            //現注文内容[]
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[l_intSize];
            //先物OP現注文内容
            WEB3IfoNewOrderSpec l_ifoNewOrderSpec = null;

            //補助口座タイプを取得
            SubAccountTypeEnum l_subAccountType = l_subAccount.getSubAccountType();

            //引数.補助口座.補助口座タイプ == ’株式オプション取引口座(先物証拠金)の場合
            if (SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccountType))
            {
                //先物OP現注文内容を生成する
                l_ifoNewOrderSpec =
                    WEB3IfoNewOrderSpec.createWEB3IfoNewOrderSpec(
                        l_subAccount,
                        l_orderSpecIntercepters[0],
                        l_orderSpecs[0],
                        l_orderTypeEnum);
            }
            //以外(引数.補助口座.補助口座タイプ==1：株式取引口座(預り金)または、2：株式信用取引口座(保証金)の場合)
            else
            {
                //引数.注文内容の要素数回LOOP処理
                for (int index = 0; index < l_intSize; index++)
                {
                    //現注文内容を生成する。
                    WEB3TPNewOrderSpec l_spec =
                        WEB3TPNewOrderSpec.create(
                            l_subAccount,
                            l_orderSpecIntercepters[index],
                            l_orderSpecs[index]);

                    log.debug(l_spec.toString());
                    l_newOrderSpecs[index] = l_spec;
                }
            }

            //引数.注文種別＝現物買付注文の場合
            if (OrderTypeEnum.EQUITY_BUY.equals(l_orderTypeEnum))
            {
                l_tradingPowerResult =
                    this.validateTradingPowerEquityBuy(
                        l_subAccount,
                        l_newOrderSpecs,
                        l_blnUpdateFlg);
            }
            //引数.注文種別＝現物売付注文の場合
            else if (OrderTypeEnum.EQUITY_SELL.equals(l_orderTypeEnum))
            {
                //新規注文の場合
                if(l_orderSpecs[0] instanceof EqTypeNewCashBasedOrderSpec)
                {
                    l_tradingPowerResult =
                        this.validateTradingPowerEquitySell(
                            l_subAccount,
                            l_newOrderSpecs,
                            l_blnUpdateFlg);
                    
                }
                //以外（訂正注文）の場合
                else
                {
                    l_tradingPowerResult =
                        this.validateTradingPowerReCalc(
                            l_subAccount,
                            l_newOrderSpecs,
                            l_orderTypeEnum,
                            l_blnUpdateFlg);
                }
            }
            //引数.注文種別＝信用現引注文の場合
            else if (OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_orderTypeEnum))
            {
                l_tradingPowerResult =
                    this.validateTradingPowerActualReceipt(
                        l_subAccount,
                        l_newOrderSpecs,
                        l_blnUpdateFlg);
            }
            //引数.注文種別＝信用現渡注文の場合
            else if (OrderTypeEnum.SWAP_MARGIN_SHORT.equals(l_orderTypeEnum))
            {
                //信用現渡取消注文の場合
                if (l_orderSpecs[0] instanceof EqTypeCancelOrderSpec)
                {
                    l_tradingPowerResult =
                        this.validateTradingPowerActualDeliveryCancel(
                            l_subAccount,
                            l_newOrderSpecs,
                            l_blnUpdateFlg);
                }
                //以外（新規注文）の場合
                else
                {
                    l_tradingPowerResult =
                        this.validateTradingPowerActualDelivery(
                            l_subAccount,
                            l_newOrderSpecs,
                            l_blnUpdateFlg);
                }
            }
            //引数.注文種別＝信用新規建注文の場合
            else if (
                OrderTypeEnum.MARGIN_LONG.equals(l_orderTypeEnum)
                    || OrderTypeEnum.MARGIN_SHORT.equals(l_orderTypeEnum))
            {
                l_tradingPowerResult =
                    this.validateTradingPowerMargin(l_subAccount, l_newOrderSpecs, l_blnUpdateFlg);
            }
            //オプション買建の場合
            else if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_orderTypeEnum))
            {
                //証拠金口座開設済みの場合
                if (SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccountType))
                {
                    l_tradingPowerResult =
                        this.validateTradingPowerOptionBuy(l_subAccount, l_ifoNewOrderSpec);
                }
                //以外(証拠金口座開設未済)の場合
                else
                {
                    l_tradingPowerResult =
                        this.validateTradingPowerOther(
                                l_subAccount,
                                l_newOrderSpecs,
                                l_orderTypeEnum,
                                l_blnUpdateFlg);
                }
            }
            //先物取引、オプション売建の場合
            else if (
                l_orderTypeEnum.intValue() == OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.intValue()
                    || l_orderTypeEnum.intValue() == OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.intValue()
                    || l_orderTypeEnum.intValue()
                        == OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.intValue())
            {
                l_tradingPowerResult =
                    this.validateTradingPowerFuturesOption(l_subAccount, l_ifoNewOrderSpec);
            }
            //投信買付、投信募集、投信乗換、累投買付、債券買い注文、ミニ株買付、出金の場合
            else if (
                l_orderTypeEnum.intValue() == OrderTypeEnum.MF_BUY.intValue()
                    || l_orderTypeEnum.intValue() == OrderTypeEnum.MF_RECRUIT.intValue()
                    || l_orderTypeEnum.intValue() == OrderTypeEnum.MF_SWITCHING.intValue()
                    || l_orderTypeEnum.intValue() == OrderTypeEnum.RUITO_BUY.intValue()
                    || l_orderTypeEnum.intValue() == OrderTypeEnum.BOND_BUY.intValue()
                    || l_orderTypeEnum.intValue() == OrderTypeEnum.MINI_STOCK_BUY.intValue()
                    || l_orderTypeEnum.intValue() == OrderTypeEnum.CASH_OUT.intValue()
                    ) 
            {
                l_tradingPowerResult =
                    this.validateTradingPowerOther(
                        l_subAccount,
                        l_newOrderSpecs,
                        l_orderTypeEnum,
                        l_blnUpdateFlg);
            }
            //(預り金⇒証拠金)振替注文、(預り金⇒為替保証金)振替注文、(預り金⇒外国株式口座)振替注文、
            //(預り金⇒オリックスクレジット)振替注文、(預り金⇒CFD口座)振替注文の場合
            else if (
                l_orderTypeEnum.intValue() == OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.intValue()
                	|| l_orderTypeEnum.intValue() == OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.intValue()
                	|| l_orderTypeEnum.intValue() == OrderTypeEnum.TRANSFER_TO_FEQ.intValue()
                	|| l_orderTypeEnum.intValue() == OrderTypeEnum.TO_ORIX_CREDIT.intValue()
                    || l_orderTypeEnum.intValue() == OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT.intValue())
            {
                /*
                 * 現注文内容[]のうち、補助口座タイプ＝1：株式取引口座（預り金）の現注文内容のみ余力更新に引き渡す
                 */
                WEB3TPNewOrderSpec[] l_specs = null;

                for (int index = 0; index < l_newOrderSpecs.length; index++)
                {
                    WEB3TPNewOrderSpec l_spec = l_newOrderSpecs[index];

                    //現注文内容.補助口座タイプ＝1：株式取引口座（預り金）の場合
                    if (SubAccountTypeEnum.EQUITY_SUB_ACCOUNT.equals(l_spec.getSubAccountType()))
                    {
                        l_specs = new WEB3TPNewOrderSpec[1];
                        l_specs[0] = l_spec;
                        break;
                    }
                }

                l_tradingPowerResult =
                    this.validateTradingPowerOther(
                        l_subAccount,
                        l_specs,
                        l_orderTypeEnum,
                        l_blnUpdateFlg);
            }
            //累投解約　@取消注文の場合
            else if (
                l_orderTypeEnum.intValue() == OrderTypeEnum.RUITO_SELL.intValue()
                    && l_orderSpecs[0] instanceof CancelOrderSpec)
            {
                l_tradingPowerResult =
                    this.validateTradingPowerOther(
                        l_subAccount,
                        l_newOrderSpecs,
                        l_orderTypeEnum,
                        l_blnUpdateFlg);
            }
            //証券振替(保護預り⇒代用)の場合
            else if (
                l_orderTypeEnum.intValue()
                    == OrderTypeEnum.FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES.intValue())
            {
                l_tradingPowerResult =
                    this.validateTradingPowerReCalc(
                        l_subAccount,
                        l_newOrderSpecs,
                        l_orderTypeEnum,
                        l_blnUpdateFlg);
            }
            //債券売付、約定変更の場合
            else if(l_orderTypeEnum.intValue() == OrderTypeEnum.BOND_SELL.intValue()
                    && l_orderSpecs[0] instanceof BondChangeOrderSpec)
            {
                l_tradingPowerResult =
                    this.validateTradingPowerOther(
                        l_subAccount,
                        l_newOrderSpecs,
                        l_orderTypeEnum,
                        l_blnUpdateFlg);
            }
            else if(l_orderTypeEnum.intValue() == OrderTypeEnum.FEQ_BUY.intValue())
            {
                //validate取引余力<外国株式買付>(補助口座, 現注文内容[]
                //(論理ビュー::余力／証拠金(web3-tradingpower)::余力::余力更新::余力更新エンティティ::現注文内容), boolean)
                l_tradingPowerResult =
                    this.validateTradingPowerFeqBuy(
                        l_subAccount,
                        l_newOrderSpecs,
                        l_blnUpdateFlg);
            }
            else if(l_orderTypeEnum.intValue() == OrderTypeEnum.FEQ_SELL.intValue())
            {
                if (l_orderSpecs[0] instanceof FeqNewOrderSpec)
                {
                    //validate取引余力<外国株式売付>(補助口座, 現注文内容[]
                    //(論理ビュー::余力／証拠金(web3-tradingpower)::余力::余力更新::余力更新エンティティ::現注文内容), boolean)
                    l_tradingPowerResult =
                        this.validateTradingPowerFeqSell(
                            l_subAccount,
                            l_newOrderSpecs,
                            l_blnUpdateFlg);
                }
                else
                {
                    //validate取引余力<余力再計算>
                    l_tradingPowerResult =
                        this.validateTradingPowerReCalc(
                            l_subAccount,
                            l_newOrderSpecs,
                            l_orderTypeEnum,
                            l_blnUpdateFlg);
                }
            }
            //以外の場合
            else
            {
                //エラーをスロー
                log.error("illegal Argument");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //取引余力結果を返却する
            log.exiting(STR_METHOD_NAME);
            log.debug(l_tradingPowerResult.toString());
            return l_tradingPowerResult;
        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (get株式買付可能額)<BR>
     * 現物株式買付可能額を取得する。<BR>
     * <BR>
     * シーケンス図「(取引余力サービス)get株式買付可能額」参照<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@return double
     */
    public double getEquityTradingPower(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getEquityTradingPower(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        //引数.補助口座がnullの場合
        if (l_subAccount == null)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //口座IDを取得
        long l_lngAccountId = l_subAccount.getAccountId();

        //顧客オブジェクトを取得
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //信用口座開設区分を取得
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        try
        {
            //余力計算条件
            WEB3TPCalcCondition l_calcCond =
                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            //現物顧客の場合
            if (l_blnMargin == false)
            {
                //余力計算結果(List)を取得
                List l_lisCalcResult =
                    WEB3TPTradingPowerCalcEquity.findCalcResultEquityParams(l_lngAccountId);

                //資産余力情報
                WEB3TPTradingPowerCalcEquity l_calcEquity =
                    new WEB3TPTradingPowerCalcEquity(l_lisCalcResult, l_calcCond);
                //余力計算結果を取得
                WEB3TPCalcResult l_result = l_calcEquity.calcAppliedEquityTradingPower();
                log.debug(l_result.toString());

                //0補正
                double l_dblTradingPower = Math.max(0, l_result.tradingPower);
                log.debug("取引可能額 = " + Double.toString(l_dblTradingPower));

                //可能額を返却
                log.exiting(STR_METHOD_NAME);
                return l_dblTradingPower;
            }
            //信用顧客の場合
            else
            {
                //余力計算結果(List)を取得
                List l_lisCalcResult =
                    WEB3TPTradingPowerCalcMargin.findCalcResultMarginParams(l_lngAccountId);

                //資産余力情報
                WEB3TPTradingPowerCalcMargin l_calcMargin =
                    new WEB3TPTradingPowerCalcMargin(l_lisCalcResult, l_calcCond);
                //余力計算結果を取得
                WEB3TPCalcResult l_result = l_calcMargin.calcAppliedEquityTradingPower();
                log.debug(l_result.toString());

                //0補正
                double l_dblTradingPower = Math.max(0, l_result.tradingPower);
                log.debug("取引可能額 = " + Double.toString(l_dblTradingPower));

                //可能額を返却
                log.exiting(STR_METHOD_NAME);
                return l_dblTradingPower;
            }
        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (get信用現引可能額)<BR>
     * 信用現引可能額を取得する。<BR>
     * <BR>
     * シーケンス図「(取引余力サービス)get信用現引可能額」参照<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@return double
     */
    public double getActualReceiptTradingPower(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getActualReceiptTradingPower(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        //引数.補助口座がnullの場合
        if (l_subAccount == null)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //口座IDを取得
        long l_lngAccountId = l_subAccount.getAccountId();

        //顧客オブジェクトを取得
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //信用口座開設区分を取得
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //現物顧客の場合
        if (l_blnMargin == false)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            //余力計算条件
            WEB3TPCalcCondition l_calcCond =
                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            //余力計算結果(List)を取得
            List l_lisCalcResult =
                WEB3TPTradingPowerCalcMargin.findCalcResultMarginParams(l_lngAccountId);

            //資産余力情報
            WEB3TPTradingPowerCalcMargin l_calcMargin =
                new WEB3TPTradingPowerCalcMargin(l_lisCalcResult, l_calcCond);
            //余力計算結果を取得
            WEB3TPCalcResult l_result = l_calcMargin.calcAppliedActualReceiptTradingPower();
            log.debug(l_result.toString());

            //0補正
            double l_dblTradingPower = Math.max(0, l_result.tradingPower);
            log.debug("取引可能額 = " + Double.toString(l_dblTradingPower));

            //可能額を返却
            log.exiting(STR_METHOD_NAME);
            return l_dblTradingPower;
        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (get信用新規建可能額)<BR>
     * 信用新規建可能額を取得する。<BR>
     * <BR>
     * シーケンス図「(取引余力サービス)get信用新規建可能額」参照<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@return double
     * @@roseuid 415A0BAB03AA
     */
    public double getMarginTradingPower(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getMarginTradingPower(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        //引数.補助口座がnullの場合
        if (l_subAccount == null)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //口座IDを取得
        long l_lngAccountId = l_subAccount.getAccountId();

        //顧客オブジェクトを取得
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //信用口座開設区分を取得
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);


        //現物顧客の場合
        if (l_blnMargin == false)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            //余力計算条件
            WEB3TPCalcCondition l_calcCond =
                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            //余力計算結果(List)を取得
            List l_lisCalcResult =
                WEB3TPTradingPowerCalcMargin.findCalcResultMarginParams(l_lngAccountId);

            //資産余力情報
            WEB3TPTradingPowerCalcMargin l_calcMargin =
                new WEB3TPTradingPowerCalcMargin(l_lisCalcResult, l_calcCond);
            //余力計算結果を取得
            WEB3TPCalcResult l_result = l_calcMargin.calcAppliedMarginTradingPower();
            log.debug(l_result.toString());

            //0補正
            double l_dblTradingPower = Math.max(0, l_result.tradingPower);
            log.debug("取引可能額 = " + Double.toString(l_dblTradingPower));

            //可能額を返却
            log.exiting(STR_METHOD_NAME);
            return l_dblTradingPower;
        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (getオプション新規買建可能額)<BR>
     * オプションの新規買建可能額を返却する。<BR>
     * <BR>
     * ・証拠金口座開設済の場合 <BR>
     * 　@- 銘柄が指定されていない場合、nullを返却する。 <BR>
     * 　@(別サービスで新規建可能額を表示するため) <BR>
     * 　@- 銘柄が指定されている場合、証拠金計算の証拠金余力額を返却する。<BR>
     * <BR>
     * ・証拠金口座未開設の場合、余力計算のオプション新規買建可能額を返却する。 <BR>
     * <BR>
     * ○証拠金口座開設済の場合 <BR>
     * (引数.補助口座.補助口座タイプ == ”株式オプション取引口座(先物証拠金)”) <BR>
     * <BR>
     * １）　@銘柄が指定されていない場合(引数.銘柄 == null) <BR>
     * <BR>
     * 　@nullを返却する。 <BR>
     * <BR>
     * ２）　@銘柄が指定されている場合(引数.銘柄 != null) <BR>
     * 　@２－１）証拠金計算の取得 <BR>
     * 　@　@証拠金計算サービス.get証拠金計算(補助口座)をコールする。 <BR>
     * 　@　@<BR>
     * 　@　@[引数の設定]  <BR>
     * 　@　@　@補助口座：　@引数.補助口座  <BR>
     * <BR>
     * 　@２－２）　@新規建余力可能チェック<BR>
     *   　@以下のいずれかに当てはまる場合、0を返却する（新規建余力不可)<BR>
     *   　@・証拠金計算.get証拠金計算条件().is新規建余力可能() == false<BR>
     *   　@・証拠金計算.calc受入証拠金残高() < 証拠金計算.get証拠金計算条件().get必要最低証拠金()<BR>
     *   　@・証拠金計算.calc未入金額() > 0<BR>
     * <BR>
     * 　@２－３）オプション新規建可能額の取得  <BR>
     * 　@証拠金計算.calc証拠金余力額( )をコールし、結果を返却する。  <BR>
     * <BR>
     * ○証拠金口座未開設の場合<BR>
     * (引数.補助口座.補助口座タイプ != ”株式オプション取引口座(先物証拠金)”)<BR>
     * 　@１）信用口座開設区分を取得<BR>
     * 　@　@・引数.補助口座.getMainAccount()をコール<BR>
     * 　@　@・顧客.is信用口座開設()をコール<BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@　@弁済区分：”0：DEFAULT”<BR>
     * <BR>
     * 　@２）余力計算条件オブジェクトを生成<BR>
     * 　@　@・余力計算条件.create余力計算条件()をコール<BR>
     * 　@　@　@［引数］<BR>
     * 　@　@　@　@補助口座：引数.補助口座<BR>
     * <BR>
     * 　@３a)現物顧客の場合(*顧客.is信用口座開設()==false)<BR>
     * 　@　@３a－１）余力計算結果Params<現物顧客>を取得<BR>
     * 　@　@　@・資産余力情報<現物顧客>.find余力計算結果<現物顧客>Params()をコール<BR>
     * 　@　@　@　@［引数］<BR>
     * 　@　@　@　@　@long：引数.補助口座.getAccountId()<BR>
     * 　@　@３a－２)資産余力情報<現物顧客>を生成<BR>
     * 　@　@　@・コンストラクタをコール<BR>
     * 　@　@　@　@［引数］<BR>
     * 　@　@　@　@　@余力計算結果Params<現物顧客>：取得した余力計算結果Params<現物顧客><BR>
     * 　@　@　@　@　@余力計算条件：生成した余力計算条件オブジェクト<BR>
     * <BR>
     * 　@　@３a－３）オプション買建可能額を取得する。<BR>
     * 　@　@　@・資産余力情報<現物顧客>.calcオプション買建可能額()をコール<BR>
     * <BR>
     * 　@　@３a－４）取引可能額を返却する　@<BR>
     * 　@　@　@［返却値］<BR>
     * 　@　@　@　@余力計算結果.取引可能額<BR>
     *<BR>
     * 　@３b)信用顧客の場合(*顧客.is信用口座開設()==true)<BR>
     * 　@　@３b－１）余力計算結果Params<信用顧客>を取得<BR>
     * 　@　@　@・資産余力情報<信用顧客>.find余力計算結果<信用顧客>Params()をコール<BR>
     * 　@　@　@　@［引数］<BR>
     * 　@　@　@　@　@long：引数.補助口座.getAccountId()<BR>
     * <BR>
     * 　@　@３b－２)資産余力情報<信用顧客>を生成<BR>
     * 　@　@　@・コンストラクタをコール<BR>
     *　@　@　@　@ ［引数］<BR>
     * 　@　@　@　@　@余力計算結果Params<信用顧客>：取得した余力計算結果Params<信用顧客><BR>
     *　@ 　@　@　@　@余力計算条件：生成した余力計算条件オブジェクト<BR>
     * <BR>
     * 　@　@３b－３）オプション買建可能額を取得する。<BR>
     * 　@　@　@・資産余力情報<信用顧客>.calcオプション買建可能額()をコール<BR>
     * <BR>
     * 　@　@３b－４）取引可能額を返却する<BR>
     * 　@　@　@［返却値］<BR>
     * 　@　@　@　@余力計算結果.取引可能額<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@return Double
     * @@roseuid 416F5BC901E9
     */
    public Double getOptionBuyTradingPower(
        WEB3GentradeSubAccount l_subAccount,
        IfoProduct l_ifoProduct)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getOptionBuyTradingPower(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        //引数.補助口座がnullの場合
        if (l_subAccount == null)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            //補助口座タイプを取得
            SubAccountTypeEnum l_subAccountType = l_subAccount.getSubAccountType();

            //引数.補助口座.補助口座タイプ == ’株式オプション取引口座(先物証拠金)の場合
            if (SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccountType))
            {
                //銘柄が指定されていない場合(引数.銘柄 == null)
                if(l_ifoProduct == null)
                {
                    //nullを返却する
                    log.exiting(STR_METHOD_NAME);
                    return null;
                }

                //証拠金計算サービスを取得
                WEB3IfoDepositCalcService l_service =
                    (WEB3IfoDepositCalcService)Services.getService(WEB3IfoDepositCalcService.class);
                //証拠金計算オブジェクトを取得
                WEB3IfoDepositCalc l_ifoDepCalc = l_service.getIfoDepositCalc(l_subAccount);
                //証拠金計算条件を取得
                WEB3IfoDepositCalcCondition l_ifoCalcCond = l_ifoDepCalc.getIfoDepositCalcCondition();
            
                //新規建余力可能フラグ
                boolean l_newOpenFlg = l_ifoCalcCond.isNewOpenTradingPowerAvailable();
                //必要最低保証金
                double l_dblMinIfoDep = l_ifoCalcCond.getMinIfoDeposit();
                //受入証拠金残高
                double l_dblRecIfoDepBal = l_ifoDepCalc.calcReceiptIfoDepositBalance();
                //未入金額
                double l_dblNonPayAmt = l_ifoDepCalc.calcNonPayAmount();

                //新規建余力不可の場合
                if (l_newOpenFlg == false
                    || l_dblRecIfoDepBal < l_dblMinIfoDep
                    || l_dblNonPayAmt > 0)
                {
                    return new Double(0);
                }

                //オプション新規建可能額を取得
                double l_dblTradingPower = l_ifoDepCalc.calcIfoDepositTradingPowerAmount();

                //オプション新規建可能額を返却
                log.exiting(STR_METHOD_NAME);
                return new Double(l_dblTradingPower);
            }
            //以外(引数.補助口座.補助口座タイプ==1：株式取引口座(預り金)または、2：株式信用取引口座(保証金)の場合)
            else
            {
                //口座IDを取得
                long l_lngAccountId = l_subAccount.getAccountId();

                //顧客オブジェクトを取得
                WEB3GentradeMainAccount l_mainAccount =
                    (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
                //信用口座開設区分を取得
                boolean l_blnMargin =
                    l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

                //余力計算条件
                WEB3TPCalcCondition l_calcCond =
                    WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

                //現物顧客の場合
                if (l_blnMargin == false)
                {
                    //余力計算結果(List)を取得
                    List l_lisCalcResult =
                        WEB3TPTradingPowerCalcEquity.findCalcResultEquityParams(l_lngAccountId);

                    //資産余力情報
                    WEB3TPTradingPowerCalcEquity l_calcEquity =
                        new WEB3TPTradingPowerCalcEquity(l_lisCalcResult, l_calcCond);
                    //余力計算結果を取得
                    WEB3TPCalcResult l_result = l_calcEquity.calcOptionTradingPower();
                    log.debug(l_result.toString());

                    //0補正
                    double l_dblTradingPower = Math.max(0, l_result.tradingPower);
                    log.debug("取引可能額 = " + Double.toString(l_dblTradingPower));

                    //可能額を返却
                    log.exiting(STR_METHOD_NAME);
                    return new Double(l_dblTradingPower);
                }
                //信用顧客の場合
                else
                {
                    //余力計算結果(List)を取得
                    List l_lisCalcResult =
                        WEB3TPTradingPowerCalcMargin.findCalcResultMarginParams(l_lngAccountId);

                    //資産余力情報
                    WEB3TPTradingPowerCalcMargin l_calcMargin =
                        new WEB3TPTradingPowerCalcMargin(l_lisCalcResult, l_calcCond);
                    //余力計算結果を取得
                    WEB3TPCalcResult l_result = l_calcMargin.calcOptionTradingPower();
                    log.debug(l_result.toString());

                    //0補正
                    double l_dblTradingPower = Math.max(0, l_result.tradingPower);
                    log.debug("取引可能額 = " + Double.toString(l_dblTradingPower));

                    //可能額を返却
                    log.exiting(STR_METHOD_NAME);
                    return new Double(l_dblTradingPower);
                }
            }
        }
        catch(WEB3SystemLayerException ex)
        {
            throw ex;
        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (get先物オプション新規建可能額)
     * 先物オプションの新規建可能額(数量)を返却する。<BR>
     * ※オプションは、売建のみ。買建の場合は、this.getOP新規買建可能額()を使用。<BR>
     * <BR>
     * ・銘柄が指定されていない場合、nullを返却する。<BR>
     * 　@(別サービスで新規建可能額を表示するため)<BR>
     * <BR>
     * ・銘柄が指定されている場合、<BR>
     * 　@　@SPAN採用会社ならば、証拠金計算の証拠金余力額を返却する。<BR>
     * 　@　@SPAN非採用会社(またはSPNAトラブル時)ならば、証拠金計算の買/売ポジション可能量を返却する。<BR>
     * <BR>
     * １）銘柄が指定されていない場合(引数.銘柄 == null)<BR>
     * 　@nullを返却する。<BR>
     * <BR>
     * ２）銘柄が指定されている場合(引数.銘柄 != null)<BR>
     * ２－１）証拠金計算の取得<BR>
     * 　@証拠金計算サービス.get証拠金計算(補助口座)をコールする。<BR>
     * 　@[引数の設定] <BR>
     * 　@　@補助口座：　@引数.補助口座 <BR>
     * <BR>
     * ２－２）　@新規建余力可能チェック<BR>
     *   以下のいずれかに当てはまる場合、0を返却する（新規建余力不可)<BR>
     *   ・証拠金計算.get証拠金計算条件().is新規建余力可能() == false<BR>
     *   ・証拠金計算.calc受入証拠金残高() < 証拠金計算.get証拠金計算条件().get必要最低証拠金()<BR>
     *   ・証拠金計算.calc未入金額() > 0<BR>
     * <BR>
     * ２－３）新規建可能額/数量の取得<BR>
     * 　@○SPAN採用会社の場合<BR>
     * 　@(証拠金計算.get証拠金計算条件( ).isSPAN使用可能( ) == true)<BR>
     * 　@　@証拠金計算.calc証拠金余力額( )をコールし、結果を返却する。<BR>
     * <BR>
     * 　@○SPAN非採用会社の場合<BR>
     * 　@(証拠金計算.get証拠金計算条件( ).isSPAN使用可能( ) == false)<BR>
     * 　@　@・買ポジションの場合<BR>
     * 　@　@(引数.銘柄.先物オプション商品 == ”先物” && 引数.is買建 == true、または、<BR>
     * 　@　@ 引数.銘柄.先物オプション商品 == ”プットオプション”)<BR>
     * <BR>
     * 　@　@　@　@証拠金計算.calc買ポジション可能数量(引数.銘柄.原資産銘柄コード)をコールし、結果を返却する。<BR>
     * <BR>
     * 　@・売ポジションの場合<BR>
     * 　@　@(引数.銘柄.先物オプション商品 == ”先物” && 引数.is買建 == false、または、<BR>
     * 　@　@ 引数.銘柄.先物オプション商品 == ”コールオプション”)<BR>
     * <BR>
     * 　@　@　@　@証拠金計算.calc売ポジション可能数量(引数.銘柄.原資産銘柄コード)をコールし、結果を返却する。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_blnLongFlg - (is買建)
     * true：買建　@false：売建
     * @@param l_ifoProduct - (銘柄)
     * 銘柄指定時のみ設定。以外、null。
     * @@return Double
     * @@roseuid 416F5BC90227
     */
    public Double getFuturesOptionTradingPower(
        WEB3GentradeSubAccount l_subAccount,
        boolean l_blnLongFlg,
        IfoProduct l_ifoProduct)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "getFuturesOptionTradingPower(WEB3GentradeSubAccount, boolean, IfoProduct)";
        log.entering(STR_METHOD_NAME);

        //銘柄が指定されていない場合
        if (l_ifoProduct == null)
        {
            //-1を返却する
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        //銘柄が指定されている場合
        else
        {
            //証拠金計算サービスを取得
            WEB3IfoDepositCalcService l_service =
                (WEB3IfoDepositCalcService)Services.getService(WEB3IfoDepositCalcService.class);
            //証拠金計算オブジェクトを取得
            WEB3IfoDepositCalc l_ifoDepCalc = l_service.getIfoDepositCalc(l_subAccount);
            //証拠金計算条件を取得
            WEB3IfoDepositCalcCondition l_ifoCalcCond = l_ifoDepCalc.getIfoDepositCalcCondition();
            
            //新規建余力可能フラグ
            boolean l_newOpenFlg = l_ifoCalcCond.isNewOpenTradingPowerAvailable();
            //必要最低保証金
            double l_dblMinIfoDep = l_ifoCalcCond.getMinIfoDeposit();
            //受入証拠金残高
            double l_dblRecIfoDepBal = l_ifoDepCalc.calcReceiptIfoDepositBalance();
            //未入金額
            double l_dblNonPayAmt = l_ifoDepCalc.calcNonPayAmount();
            
            //新規建余力不可の場合
            if (l_newOpenFlg == false
                || l_dblRecIfoDepBal < l_dblMinIfoDep
                || l_dblNonPayAmt > 0)
            {
                return new Double(0);
            }

            //SPAN採用会社の場合
            if (l_ifoDepCalc.getIfoDepositCalcCondition().isSPANUsable() == true)
            {
                //証拠金余力額を取得する。
                double l_dblTradingPower = l_ifoDepCalc.calcIfoDepositTradingPowerAmount();

                //証拠金余力額を返却する。
                log.exiting(STR_METHOD_NAME);
                return new Double(l_dblTradingPower);
            }
            //SPAN非採用会社の場合
            else
            {
                //先物OP商品区分
                IfoDerivativeTypeEnum l_deriEnum = l_ifoProduct.getDerivativeType();
                //原資産銘柄コード
                String l_strProductCode = l_ifoProduct.getUnderlyingProductCode();

                //買ポジションの場合
                if ((l_deriEnum.intValue() == IfoDerivativeTypeEnum.FUTURES.intValue()
                    && l_blnLongFlg == true)
                    || l_deriEnum.intValue() == IfoDerivativeTypeEnum.PUT_OPTIONS.intValue())
                {
                    //買ポジション可能数量を取得
                    double l_dblPossQty = l_ifoDepCalc.calcPossibleBuyQty(l_strProductCode);

                    //買ポジション可能数量を返却
                    log.exiting(STR_METHOD_NAME);
                    return new Double(l_dblPossQty);
                }
                //売ポジションの場合
                else if (
                    (l_deriEnum.intValue() == IfoDerivativeTypeEnum.FUTURES.intValue()
                        && l_blnLongFlg == false)
                        || l_deriEnum.intValue() == IfoDerivativeTypeEnum.CALL_OPTIONS.intValue())
                {
                    //売ポジション可能数量を取得
                    double l_dblPossQty = l_ifoDepCalc.calcPossibleSellQty(l_strProductCode);

                    //売ポジション可能数量を返却
                    log.exiting(STR_METHOD_NAME);
                    return new Double(l_dblPossQty);
                }
                //以外
                else
                {
                    //エラーをスロー
                    log.error("illegal Argument");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
        }
    }

    /**
     * (getその他商品買付可能額)<BR>
     * その他商品買付可能額（0補正有り）を取得する。<BR>
     * <BR>
     * １）getその他商品買付可能額～0補正無し～()をコールし、<BR>
     * その他商品買付可能額（0補正無し）を取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@補助口座：引数.補助口座<BR>
     * 　@受渡日：引数.受渡日<BR>
     * <BR>
     * ２）１）の戻り値を、0補正して返却する。<BR>
     * <BR>
     * 　@返却値 = Math.max(0, １）の戻り値)<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_datDeliveryDate - (受渡日)
     * @@return double
     * @@roseuid 4158CBE10251
     */
    public double getOtherTradingPower(WEB3GentradeSubAccount l_subAccount, Date l_datDeliveryDate)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getOtherTradingPower(WEB3GentradeSubAccount, Date)";
        log.entering(STR_METHOD_NAME);

        //getその他商品買付可能額～0補正無し～()をコールし、その他商品買付可能額（0補正無し）を取得する。
        double l_dblTradingPower = this.getOtherTradingPowerForCheck(l_subAccount, l_datDeliveryDate);

        //返却値 = Math.max(0, １）の戻り値)
        double l_dblPayment = Math.max(0, l_dblTradingPower);

        log.exiting(STR_METHOD_NAME);
        return l_dblPayment;
    }

    /**
     * (get出金可能額～0補正有り～)<BR>
     * 出金可能額を取得する。<BR>
     * <BR><
     * １）出金可能額を取得する。<BR>
     * 　@取引余力サービスImpl.get出金可能額～0補正無し()をコール<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@補助口座：引数.補助口座<BR>
     * 　@　@受渡日：引数.受渡日<BR>
     *     呼び出し元区分：1<BR>
     * <BR>
     * ２）出金可能額を返却する。<BR>
     * 　@○１）の戻り値 < 0の場合<BR>
     * 　@　@返却値：0<BR>
     * <BR>
     * 　@○以外（ １）の戻り値 >= 0）<BR>
     * 　@　@返却値："１）の戻り値"<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_datDeliveryDate - (受渡日)
     * @@return double
     */
    public double getPaymentTradingPower(WEB3GentradeSubAccount l_subAccount, Date l_datDeliveryDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getPaymentTradingPower(WEB3GentradeSubAccount, Date)";
        log.entering(STR_METHOD_NAME);

        //get出金可能額～0補正無し～をコール
        double l_tradingPower = this.getPaymentTradingPowerForCheck(
                l_subAccount, l_datDeliveryDate, 1);
        //0補正を行う
        l_tradingPower = Math.max(0, l_tradingPower);

        log.debug("取引可能額 = " + Double.toString(l_tradingPower));
        log.exiting(STR_METHOD_NAME);
        return l_tradingPower;
    }

    /**
     * (get出金可能額～0補正無し～) （※）出金余力チェック時に使用<BR>
     * 出金可能額を取得する。<BR>
     * <BR>
     * １）出金可能額を取得する。<BR>
     * 　@取引余力サービスImpl.get出金可能額～0補正無し()をコール<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@補助口座：引数.補助口座<BR>
     * 　@　@受渡日：引数.受渡日<BR>
     *     呼び出し元区分：2<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_datDeliveryDate - (受渡日)
     * @@return double
     */
    public double getPaymentTradingPowerForCheck(WEB3GentradeSubAccount l_subAccount, Date l_datDeliveryDate)
        throws WEB3BaseException
    {
        //get出金可能額～0補正無し～をコール
        return this.getPaymentTradingPowerForCheck(
                l_subAccount, l_datDeliveryDate, 2);
    }

    /**
     * (get投資信託買付可能額)<BR>
     * 投信買付可能額を取得する。<BR>
     * <BR>
     * シーケンス図「(取引余力サービス)get投資信託買付可能額」参照<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_datDeliveryDate - (受渡日)
     * @@param l_orderTypeEnum - (注文種別)
     * @@return double
     */
    public double getMutualFundBuyTradingPower(WEB3GentradeSubAccount l_subAccount, Date l_datDeliveryDate, OrderTypeEnum l_orderTypeEnum)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getMutualFundBuyTradingPower(WEB3GentradeSubAccount, Date, OrderTypeEnum)";
        log.entering(STR_METHOD_NAME);

        //引数.補助口座、引数.受渡日がnullの場合
        if (l_subAccount == null || l_datDeliveryDate == null)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //注文種別＝nullの場合
        if(l_orderTypeEnum == null)
        {
            //投信買付をデフォルトとしてセットする
            l_orderTypeEnum = OrderTypeEnum.MF_BUY;
        }

        //口座IDを取得
        long l_lngAccountId = l_subAccount.getAccountId();

        //顧客オブジェクトを取得
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        
        //信用口座開設区分を取得
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        try
        {
            /*
             * 余力計算条件オブジェクトを生成する
             */
            WEB3TPCalcCondition l_calcCond = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            //投資信託買付可能額適用日の設定値を取得
            String l_strMFBuyApplyDate = l_calcCond.getInstBranCalcCondition(WEB3TPCalcCondition.MFBUY_APPLY_DATE);

            //基準日
            int l_intFundBasePoint = 0;
            
            //適用日＝発注日以降の場合
            if(WEB3TPMutualFundBuyApplyDateDef.FROM_BIZ_DATE_UNTIL_T5.equals(l_strMFBuyApplyDate))
            {
                //基準日＝計算条件の基準日<投信>
                l_intFundBasePoint = l_calcCond.getFundBasePoint();
            }
            else
            {
                //受渡日が当日(T+0)以前だった場合
                if (WEB3DateUtility
                    .compareToDay(l_datDeliveryDate, l_calcCond.getBizDate(WEB3TPSpecifiedPointDef.T_0))
                    < 0)
                {
                    //エラーをスロー
                    log.error("illegal Argument");
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                //受渡日がT+5以降だった場合
                else if (
                    WEB3DateUtility.compareToDay(
                            l_datDeliveryDate,
                        l_calcCond.getBizDate(WEB3TPSpecifiedPointDef.T_5))
                        > 0)
                {
                    //基準日＝営業日(T+5)
                    l_intFundBasePoint = WEB3TPSpecifiedPointDef.T_5;
                }
                //以外(受渡日が、T+0～T+5の間)の場合
                else
                {
                    //基準日＝引数の受渡日
                    l_intFundBasePoint = l_calcCond.calcSpecifiedPoint(l_datDeliveryDate);
                }
            }

            //現物顧客の場合
            if (l_blnMargin == false)
            {
                //余力計算結果(List)を取得
                List l_lisCalcResult =
                    WEB3TPTradingPowerCalcEquity.findCalcResultEquityParams(l_lngAccountId);

                //資産余力情報
                WEB3TPTradingPowerCalcEquity l_calcEquity =
                    new WEB3TPTradingPowerCalcEquity(l_lisCalcResult, l_calcCond);

                //余力計算結果オブジェクトを取得
                WEB3TPCalcResult l_result = l_calcEquity.calcAppliedOtherTradingPower(l_orderTypeEnum, l_intFundBasePoint);

                log.debug(l_result.toString());

                //0補正
                double l_dblTradingPower = Math.max(0, l_result.tradingPower);
                log.debug("取引可能額 = " + Double.toString(l_dblTradingPower));

                //可能額を返却
                log.exiting(STR_METHOD_NAME);
                return l_dblTradingPower;
            }
            //信用顧客の場合
            else
            {
                //余力計算結果(List)を取得
                List l_lisCalcResult =
                    WEB3TPTradingPowerCalcMargin.findCalcResultMarginParams(l_lngAccountId);

                //資産余力情報
                WEB3TPTradingPowerCalcMargin l_calcMargin =
                    new WEB3TPTradingPowerCalcMargin(l_lisCalcResult, l_calcCond);

                //余力計算結果オブジェクトを取得
                WEB3TPCalcResult l_result = l_calcMargin.calcAppliedOtherTradingPower(l_orderTypeEnum, l_intFundBasePoint);

                log.debug(l_result.toString());

                //0補正
                double l_dblTradingPower = Math.max(0, l_result.tradingPower);
                log.debug("取引可能額 = " + Double.toString(l_dblTradingPower));

                //可能額を返却
                log.exiting(STR_METHOD_NAME);
                return l_dblTradingPower;
            }
        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (get預り金への振替額)<BR>
     * <BR>
     * シーケンス図「(取引余力サービス)get預り金への振替額」参照<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_dblNecessaryCash - (当日必要現金)
     * @@param l_datDeliveryDate - (受渡日)
     * @@return double
     * @@roseuid 416E4E9002A6
     */
    public double getTransferAmountToEquitySubAcount(
        WEB3GentradeSubAccount l_subAccount,
        double l_dblNecessaryCash,
        Date l_datDeliveryDate)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "getTransferAmountToEquitySubAcount(WEB3GentradeSubAccount, double, Date)";
        log.entering(STR_METHOD_NAME);

        //引数.補助口座、引数.受渡日がnullの場合
        if (l_subAccount == null || l_datDeliveryDate == null)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //顧客オブジェクトを取得
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //信用口座開設区分を取得
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        try
        {
            //現物顧客の場合
            if (l_blnMargin == false)
            {
                //資産余力情報<現物顧客>を取得
                WEB3TPTradingPowerCalcEquity l_calcEquity =
                    this.getTradingPowerCalcEquity(l_subAccount);

                //実質顧客勘定残高を取得
                double l_dblRealBalance =
                    this.calcRealBalanceEquity(l_subAccount, l_calcEquity, l_datDeliveryDate);

                //預り金への振り替え額を計算
                double l_dblTranAmt = l_dblNecessaryCash - l_dblRealBalance;
                //0補正
                l_dblTranAmt = Math.max(0, l_dblTranAmt);
                log.debug("預り金への振替額 = " + Double.toString(l_dblTranAmt));

                //預り金への振り替え額を返却する
                log.exiting(STR_METHOD_NAME);
                return l_dblTranAmt;
            }
            //信用顧客の場合
            else
            {
                //資産余力情報<信用顧客>を取得
                WEB3TPTradingPowerCalcMargin l_calcMargin =
                    this.getTradingPowerCalcMargin(l_subAccount);

                //実質顧客勘定残高を取得
                double l_dblRealBalance =
                    this.calcRealBalanceMargin(l_subAccount, l_calcMargin, l_datDeliveryDate);

                //預り金への振り替え額を計算
                double l_dblTranAmt = l_dblNecessaryCash - l_dblRealBalance;
                //0補正
                l_dblTranAmt = Math.max(0, l_dblTranAmt);
                log.debug("預り金への振替額 = " + Double.toString(l_dblTranAmt));

                //預り金への振り替え額を返却する
                log.exiting(STR_METHOD_NAME);
                return l_dblTranAmt;
            }
        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (余力再計算)<BR>
     * 余力再計算を実施し、引数で指定された顧客の余力状態を最新にする。<BR>
     * <BR>
     * ※シーケンス図「(取引余力サービス)余力再計算」参照<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@roseuid 41774399003E
     */
    public void reCalcTradingPower(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "reCalcTradingPower(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        //引数.補助口座がnullの場合
        if (l_subAccount == null)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //口座IDを取得
        long l_lngAccountId = l_subAccount.getAccountId();

        //顧客オブジェクトを取得
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //信用口座開設区分を取得
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        try
        {
            //余力計算条件
            WEB3TPCalcCondition l_calcCond = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);
            //余力更新
            WEB3TPTradingPowerUpd l_tpUpd =
                new WEB3TPTradingPowerUpd(l_lngAccountId, l_blnMargin, l_calcCond, null);

            //現物顧客の場合
            if (l_blnMargin == false)
            {
                //余力更新内容を取得
                List l_updResults = l_tpUpd.calcTradingpowerUpdResultEquity();
                //余力更新内容をテーブルに挿入
                l_tpUpd.saveTradingpowerUpdResultEquity(l_updResults);
            }
            //信用顧客の場合
            else
            {
                //余力更新内容を取得
                List l_updResults =
                    l_tpUpd.calcTradingpowerUpdResultMargin(WEB3TPMarkToMarketDivDef.NORMAL);
                //余力更新内容をテーブルに挿入
                l_tpUpd.saveTradingpowerUpdResultMargin(l_updResults);
            }
        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get資産余力情報<現物顧客>)<BR>
     * <BR>
     * シーケンス図「(取引余力サービス)get資産余力情報<現物顧客>」参照
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@return WEB3TPTradingPowerCalcEquity
     */
    public WEB3TPTradingPowerCalcEquity getTradingPowerCalcEquity(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getTradingPowerCalcEquity(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        //引数.補助口座がnullの場合
        if (l_subAccount == null)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //口座IDを取得
        long l_lngAccountId = l_subAccount.getAccountId();

        //顧客オブジェクトを取得
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //信用口座開設区分を取得
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //信用顧客の場合
        if (l_blnMargin == true)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            //余力計算条件
            WEB3TPCalcCondition l_calcCond =
                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            //余力計算結果(List)を取得
            List l_lisCalcResult =
                WEB3TPTradingPowerCalcEquity.findCalcResultEquityParams(l_lngAccountId);

            //資産余力情報を生成
            WEB3TPTradingPowerCalcEquity l_calcEquity =
                new WEB3TPTradingPowerCalcEquity(l_lisCalcResult, l_calcCond);

            //資産余力情報を返却
            log.exiting(STR_METHOD_NAME);
            return l_calcEquity;

        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (get資産余力情報<信用顧客>)<BR>
     * <BR>
     * シーケンス図「(取引余力サービス)get資産余力情報<信用顧客>」参照 <BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@return WEB3TPTradingPowerCalcMargin
     */
    public WEB3TPTradingPowerCalcMargin getTradingPowerCalcMargin(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getTradingPowerCalcMargin(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        //引数.補助口座がnullの場合
        if (l_subAccount == null)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //口座IDを取得
        long l_lngAccountId = l_subAccount.getAccountId();

        //顧客オブジェクトを取得
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //信用口座開設区分を取得
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //現物顧客の場合
        if (l_blnMargin == false)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            //余力計算条件
            WEB3TPCalcCondition l_calcCond =
                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            //余力計算結果(List)を取得
            List l_lisCalcResult =
                WEB3TPTradingPowerCalcMargin.findCalcResultMarginParams(l_lngAccountId);

            //資産余力情報を生成
            WEB3TPTradingPowerCalcMargin l_calcMargin =
                new WEB3TPTradingPowerCalcMargin(l_lisCalcResult, l_calcCond);

            //資産余力情報を返却
            log.exiting(STR_METHOD_NAME);
            return l_calcMargin;

        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (get資産余力情報<現物顧客>～時価評価～)<BR>
     * <BR>
     * シーケンス図「(取引余力サービス)get資産余力情報<現物顧客>～時価評価～」参照<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@return WEB3TPTradingPowerCalcEquity
     */
    public WEB3TPTradingPowerCalcEquity getTradingPowerCalcEquityQuote(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getTradingPowerCalcEquityQuote(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        //引数.補助口座がnullの場合
        if (l_subAccount == null)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //口座IDを取得
        long l_lngAccountId = l_subAccount.getAccountId();

        //顧客オブジェクトを取得
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //信用口座開設区分を取得
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //信用顧客の場合
        if (l_blnMargin == true)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            //余力計算条件
            WEB3TPCalcCondition l_calcCond =
                WEB3TPCalcCondition.createCalcConditionQuote(l_subAccount);

            //余力更新オブジェクトを生成
            WEB3TPTradingPowerUpd l_tpUpd = new WEB3TPTradingPowerUpd(l_lngAccountId, l_blnMargin, l_calcCond, null);
        
            //余力計算結果(List)を取得
            List l_lisCalcResult = l_tpUpd.calcTradingpowerUpdResultEquity();

            //資産余力情報を生成
            WEB3TPTradingPowerCalcEquity l_calcEquity =
                new WEB3TPTradingPowerCalcEquity(l_lisCalcResult, l_calcCond);

            //資産余力情報を返却
            log.exiting(STR_METHOD_NAME);
            return l_calcEquity;

        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (get資産余力情報<信用顧客>～時価評価～)<BR>
     * <BR>
     * シーケンス図「(取引余力サービス)get資産余力情報<信用顧客>～時価評価～」参照<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@return WEB3TPTradingPowerCalcMargin
     */
    public WEB3TPTradingPowerCalcMargin getTradingPowerCalcMarginQuote(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getTradingPowerCalcMarginQuote(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        //引数.補助口座がnullの場合
        if (l_subAccount == null)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //口座IDを取得
        long l_lngAccountId = l_subAccount.getAccountId();

        //顧客オブジェクトを取得
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //信用口座開設区分を取得
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //現物顧客の場合
        if (l_blnMargin == false)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            //余力計算条件
            WEB3TPCalcCondition l_calcCond =
                WEB3TPCalcCondition.createCalcConditionQuote(l_subAccount);

            //余力更新オブジェクトを生成
            WEB3TPTradingPowerUpd l_tpUpd = new WEB3TPTradingPowerUpd(l_lngAccountId, l_blnMargin, l_calcCond, null);
        
            //余力計算結果(List)を取得
            List l_lisCalcResult = l_tpUpd.calcTradingpowerUpdResultMargin(WEB3TPMarkToMarketDivDef.NORMAL);

            //資産余力情報を生成
            WEB3TPTradingPowerCalcMargin l_calcMargin =
                new WEB3TPTradingPowerCalcMargin(l_lisCalcResult, l_calcCond);

            //資産余力情報を返却
            log.exiting(STR_METHOD_NAME);
            return l_calcMargin;

        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (get資産余力情報<現物顧客>)<BR>
     * <BR>
     * シーケンス図「(取引余力サービス)get資産余力情報<現物顧客>～余力計算結果ＩＤ指定～」参照<BR>
     * <BR>
     * @@param l_lngCalcResultId - (余力計算結果ID)
     * @@return WEB3TPTradingPowerCalcEquity
     */
    public WEB3TPTradingPowerCalcEquity getTradingPowerCalcEquity(long l_lngCalcResultId)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getTradingPowerCalcEquity(long)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //余力計算結果(List)を取得
            List l_lisCalcResult =
                WEB3TPTradingPowerCalcEquity.findCalcResultEquityParamsSpecifiedCalcResultId(
                    l_lngCalcResultId);

            /*
             * List内の余力計算結果<現物顧客>Paramsオブジェクトより口座IDを取得する。
             */
            //口座ID
            long l_lngAccountId;
            //余力計算結果<現物顧客>Params
            TpCalcResultEquityParams l_params = null;

            for (Iterator l_iter = l_lisCalcResult.iterator(); l_iter.hasNext();)
            {
                Object l_element = (Object)l_iter.next();

                if (l_element instanceof TpCalcResultEquityParams)
                {
                    l_params = (TpCalcResultEquityParams)l_element;
                }
            }
            l_lngAccountId = l_params.getAccountId();

            //補助口座オブジェクトを取得
            WEB3GentradeSubAccount l_subAccount = this.getSubAccount(l_lngAccountId);

            //顧客オブジェクトを取得
            WEB3GentradeMainAccount l_mainAccount =
                (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
            //信用口座開設区分を取得
            boolean l_blnMargin =
                l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

            //信用顧客の場合
            if (l_blnMargin == true)
            {
                //エラーをスロー
                log.error("illegal Argument");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //余力計算条件
            WEB3TPCalcCondition l_calcCond =
                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            //資産余力情報を生成
            WEB3TPTradingPowerCalcEquity l_calcEquity =
                new WEB3TPTradingPowerCalcEquity(l_lisCalcResult, l_calcCond);

            //資産余力情報を返却
            log.exiting(STR_METHOD_NAME);
            return l_calcEquity;
        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (get資産余力情報<信用顧客>)<BR>
     * <BR>
     * シーケンス図「(取引余力サービス)get資産余力情報<信用顧客>～余力計算結果ＩＤ指定～」参照<BR>
     * <BR>
     * @@param l_lngCalcResultId - (余力計算結果ID)
     * @@return WEB3TPTradingPowerCalcMargin
     */
    public WEB3TPTradingPowerCalcMargin getTradingPowerCalcMargin(long l_lngCalcResultId)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getTradingPowerCalcMargin(long)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //余力計算結果(List)を取得
            List l_lisCalcResult =
                WEB3TPTradingPowerCalcMargin.findCalcResultMarginParamsSpecifiedCalcResultId(
                    l_lngCalcResultId);

            /*
             * List内の余力計算結果<現物顧客>Paramsオブジェクトより口座IDを取得する。
             */
            //口座ID
            long l_lngAccountId;
            //余力計算結果<信用顧客>Params
            TpCalcResultMarginParams l_params = null;

            for (Iterator l_iter = l_lisCalcResult.iterator(); l_iter.hasNext();)
            {
                Object l_element = (Object)l_iter.next();

                if (l_element instanceof TpCalcResultMarginParams)
                {
                    l_params = (TpCalcResultMarginParams)l_element;
                }
            }
            l_lngAccountId = l_params.getAccountId();

            //補助口座オブジェクトを取得
            WEB3GentradeSubAccount l_subAccount = this.getSubAccount(l_lngAccountId);

            //顧客オブジェクトを取得
            WEB3GentradeMainAccount l_mainAccount =
                (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
            //信用口座開設区分を取得
            boolean l_blnMargin =
                l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

            //現物顧客の場合
            if (l_blnMargin == false)
            {
                //エラーをスロー
                log.error("illegal Argument");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //余力計算条件
            WEB3TPCalcCondition l_calcCond =
                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            //資産余力情報を生成
            WEB3TPTradingPowerCalcMargin l_calcMargin =
                new WEB3TPTradingPowerCalcMargin(l_lisCalcResult, l_calcCond);

            //資産余力情報を返却
            log.exiting(STR_METHOD_NAME);
            return l_calcMargin;
        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (get資産余力情報<現物顧客>)<BR>
     * <BR>
     * シーケンス図「(取引余力サービス)get資産余力情報<現物顧客>～計算結果事前取得済～」参照
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_resultRow - (余力計算結果<現物顧客>)
     * @@param l_resultDetailRow - (余力計算結果詳細<現物顧客>)
     * @@return WEB3TPTradingPowerCalcEquity
     */
    public WEB3TPTradingPowerCalcEquity getTradingPowerCalcEquity(
        WEB3GentradeSubAccount l_subAccount,
        TpCalcResultEquityRow l_resultRow,
        TpCalcResultEquityDetailRow l_resultDetailRow)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getTradingPowerCalcEquity(WEB3GentradeSubAccount, TpCalcResultEquityRow, TpCalcResultEquityDetailRow)";
        log.entering(STR_METHOD_NAME);

        //引数.補助口座がnullの場合
        if (l_subAccount == null)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //口座IDを取得
        long l_lngAccountId = l_subAccount.getAccountId();

        //顧客オブジェクトを取得
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //信用口座開設区分を取得
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //信用顧客の場合
        if (l_blnMargin == true)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            //余力計算条件
            WEB3TPCalcCondition l_calcCond =
                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            //余力計算結果(List)を作成
            List l_lisCalcResult = new ArrayList();
            l_lisCalcResult.add(l_resultRow);
            l_lisCalcResult.add(l_resultDetailRow);

            //資産余力情報を生成
            WEB3TPTradingPowerCalcEquity l_calcEquity =
                new WEB3TPTradingPowerCalcEquity(l_lisCalcResult, l_calcCond);

            //資産余力情報を返却
            log.exiting(STR_METHOD_NAME);
            return l_calcEquity;

        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (get資産余力情報<信用顧客>)<BR>
     * <BR>
     * シーケンス図「(取引余力サービス)get資産余力情報<信用顧客>～計算結果事前取得済～」参照 <BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_resultRow - (余力計算結果<信用顧客>)
     * @@param l_resultDetailRow - (余力計算結果詳細<信用顧客>)
     * @@return WEB3TPTradingPowerCalcMargin
     */
    public WEB3TPTradingPowerCalcMargin getTradingPowerCalcMargin(
        WEB3GentradeSubAccount l_subAccount,
        TpCalcResultMarginRow l_resultRow,
        TpCalcResultMarginDetailRow l_resultDetailRow)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getTradingPowerCalcMargin(WEB3GentradeSubAccount, TpCalcResultMarginRow, TpCalcResultMarginDetailRow)";
        log.entering(STR_METHOD_NAME);

        //引数.補助口座がnullの場合
        if (l_subAccount == null)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //口座IDを取得
        long l_lngAccountId = l_subAccount.getAccountId();

        //顧客オブジェクトを取得
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //信用口座開設区分を取得
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //現物顧客の場合
        if (l_blnMargin == false)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            //余力計算条件
            WEB3TPCalcCondition l_calcCond =
                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            //余力計算結果(List)を作成
            List l_lisCalcResult = new ArrayList();
            l_lisCalcResult.add(l_resultRow);
            l_lisCalcResult.add(l_resultDetailRow);

            //資産余力情報を生成
            WEB3TPTradingPowerCalcMargin l_calcMargin =
                new WEB3TPTradingPowerCalcMargin(l_lisCalcResult, l_calcCond);

            //資産余力情報を返却
            log.exiting(STR_METHOD_NAME);
            return l_calcMargin;

        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (get二階建銘柄一覧)<BR>
     * <BR>
     * ※シーケンス図「(取引余力サービス)get二階建銘柄情報一覧」参照<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@return WEB3TPMarginSecurityInfo[]
     */
    public WEB3TPMarginSecurityInfo[] getMarginSecurityInfo(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getMarginSecurityInfo(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        //引数.補助口座がnullの場合
        if (l_subAccount == null)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //口座IDを取得
        long l_lngAccountId = l_subAccount.getAccountId();

        //顧客オブジェクトを取得
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //信用口座開設区分を取得
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //現物顧客の場合
        if (l_blnMargin == false)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            //余力計算条件
            WEB3TPCalcCondition l_calcCond =
                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            //余力更新オブジェクトを生成
            WEB3TPTradingPowerUpd l_calcUpd =
                new WEB3TPTradingPowerUpd(l_lngAccountId, l_blnMargin, l_calcCond, null);

            //余力更新内容(List)を取得
            List l_lisCalcResult =
                l_calcUpd.calcTradingpowerUpdResultMargin(WEB3TPMarkToMarketDivDef.NORMAL);

            //資産余力情報<信用>オブジェクトを生成
            WEB3TPTradingPowerCalcMargin l_calcMargin =
                new WEB3TPTradingPowerCalcMargin(l_lisCalcResult, l_calcCond);

            //二階建銘柄情報一覧を取得
            WEB3TPMarginSecurityInfo[] l_marginSecInfo =
                this.calcMarginSecurity(l_subAccount, -1, l_calcMargin, l_calcUpd, null, -1, null);

            log.exiting(STR_METHOD_NAME);
            return l_marginSecInfo;

        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (getその他商品買付可能額～余力計算結果ROW指定～)
     * <BR>
     * 余力計算結果Rowならびに余力計算結果詳細Rowを指定して<BR>
     * その他商品買付可能額を取得する。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_datDeliveryDate - (受渡日)
     * @@param l_tpCalcResultEquityRow - (余力計算結果<現物顧客>Row)
     * @@param l_tpCalcResultEquityDetailRow - (余力計算結果詳細<現物顧客>Row)
     * @@param l_tpCalcResultMarginRow - (余力計算結果<信用顧客>Row)
     * @@param l_tpCalcResultMarginDetailRow - (余力計算結果詳細<信用顧客>Row)l_subAccount - (補助口座)
     * @@return double
     */
    public double getOtherTradingPower(
            WEB3GentradeSubAccount l_subAccount, Date l_datDeliveryDate,
            TpCalcResultEquityRow l_tpCalcResultEquityRow,
            TpCalcResultEquityDetailRow l_tpCalcResultEquityDetailRow,
            TpCalcResultMarginRow l_tpCalcResultMarginRow,
            TpCalcResultMarginDetailRow l_tpCalcResultMarginDetailRow)
            throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getOtherTradingPower(WEB3GentradeSubAccount, Date, TpCalcResultEquityRow, TpCalcResultEquityDetailRow, TpCalcResultMarginRow, TpCalcResultMarginDetailRow)";
        log.entering(STR_METHOD_NAME);

        //引数.補助口座、引数.受渡日がnullの場合
        if (l_subAccount == null || l_datDeliveryDate == null)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //口座IDを取得
        long l_lngAccountId = l_subAccount.getAccountId();

        //顧客オブジェクトを取得
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //信用口座開設区分を取得
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        try
        {
            /*
             * 余力計算条件オブジェクトを生成する
             */
            WEB3TPCalcCondition l_calcCond = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            //受渡日が当日(T+0)以前だった場合
            if (WEB3DateUtility
                .compareToDay(l_datDeliveryDate, l_calcCond.getBizDate(WEB3TPSpecifiedPointDef.T_0))
                < 0)
            {
                //エラーをスロー
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            //受渡日がT+5以降だった場合
            else if (
                WEB3DateUtility.compareToDay(
                    l_datDeliveryDate,
                    l_calcCond.getBizDate(WEB3TPSpecifiedPointDef.T_5))
                    > 0)
            {
                //余力計算基準日<その他買付>=T+5をセット
                l_calcCond.setOtherBasePoint(WEB3TPSpecifiedPointDef.T_5);
            }
            //以外(受渡日が、T+0～T+5の間)の場合
            else
            {
                //受渡日に対応する指定日を取得
                int l_intDeliDate = l_calcCond.calcSpecifiedPoint(l_datDeliveryDate);

                //余力計算基準日<その他買付>=指定日をセット
                l_calcCond.setOtherBasePoint(l_intDeliDate);
            }

            //現物顧客の場合
            if (l_blnMargin == false)
            {
                List l_lisCalcResult = null;

                if(l_tpCalcResultEquityRow != null)
                {
                    //余力計算結果(List)を取得
                    l_lisCalcResult = new ArrayList();
                    l_lisCalcResult.add(l_tpCalcResultEquityRow);
                    l_lisCalcResult.add(l_tpCalcResultEquityDetailRow);
                }
                else
                {
                    //余力計算結果(List)を取得
                    l_lisCalcResult = WEB3TPTradingPowerCalcEquity
                        .findCalcResultEquityParams(l_lngAccountId);
                }

                //資産余力情報
                WEB3TPTradingPowerCalcEquity l_calcEquity =
                    new WEB3TPTradingPowerCalcEquity(l_lisCalcResult, l_calcCond);

                //余力計算結果を取得
                WEB3TPCalcResult l_result = l_calcEquity.calcAppliedOtherTradingPower(null);
                log.debug(l_result.toString());

                //0補正
                double l_dblTradingPower = Math.max(0, l_result.tradingPower);
                log.debug("取引可能額 = " + Double.toString(l_dblTradingPower));

                //可能額を返却
                log.exiting(STR_METHOD_NAME);
                return l_dblTradingPower;
            }
            //信用顧客の場合
            else
            {
                List l_lisCalcResult = null;

                if(l_tpCalcResultMarginRow != null)
                {
                    //余力計算結果(List)を取得
                    l_lisCalcResult = new ArrayList();
                    l_lisCalcResult.add(l_tpCalcResultMarginRow);
                    l_lisCalcResult.add(l_tpCalcResultMarginDetailRow);
                }
                else
                {
                    //余力計算結果(List)を取得
                    l_lisCalcResult = WEB3TPTradingPowerCalcMargin
                        .findCalcResultMarginParams(l_lngAccountId);
                }

                //資産余力情報
                WEB3TPTradingPowerCalcMargin l_calcMargin =
                    new WEB3TPTradingPowerCalcMargin(l_lisCalcResult, l_calcCond);

                //余力計算結果を取得
                WEB3TPCalcResult l_result = l_calcMargin.calcAppliedOtherTradingPower(null);
                log.debug(l_result.toString());

                //0補正
                double l_dblTradingPower = Math.max(0, l_result.tradingPower);
                log.debug("取引可能額 = " + Double.toString(l_dblTradingPower));

                //可能額を返却
                log.exiting(STR_METHOD_NAME);
                return l_dblTradingPower;
            }
        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (get株式買付可能額～連続注文～) <BR>
     * <BR>
     * 連続注文用の株式買付可能額を計算し返却する。 <BR>
     * <BR>
     * ※シーケンス図「(取引余力サービス)get株式買付可能額～連続注文～」参照 <BR>
     * <BR>
     * 「返却値 >= 概算受渡代金」⇒取引OK <BR>
     * 「返却値 <  概算受渡代金」⇒取引NG <BR>
     * <BR>
     * 注）返却値 >= 0 とする。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_datDeliveryDate - (受渡日)
     * @@param l_estimatedPrice - (訂正前概算受渡代金)
     * @@return double
     */
    public double getSuccEquityTradingPower(
            WEB3GentradeSubAccount l_subAccount,
            Date l_datDeliveryDate,
            Double l_estimatedPrice) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getSuccEquityTradingPower(WEB3GentradeSubAccount, Date, Double)";
        log.entering(STR_METHOD_NAME);

        //引数.補助口座が、nullの場合
        if(l_subAccount == null)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //口座IDを取得
        long l_lngAccountId = l_subAccount.getAccountId();

        //顧客オブジェクトを取得
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //信用口座開設区分を取得
        boolean l_blnMargin = l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        try
        {
            //余力計算条件
            WEB3TPCalcCondition l_calcCond = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            /*
             * 基準日を取得
             */
            int l_intBasePoint;
            //引数.受渡日 == nullの場合
            if(l_datDeliveryDate == null)
            {
                //余力計算基準日<株式買付/信用現引>を取得
                l_intBasePoint = l_calcCond.getEquityBasePoint();
            }
            //以外の場合
            else 
            {
                //引数.受渡日に対応する指定日を取得
                l_intBasePoint = l_calcCond.calcSpecifiedPoint(l_datDeliveryDate);
            }
            
            //営業日(T+0)
            Date l_datT0 = l_calcCond.getBizDate(WEB3TPSpecifiedPointDef.T_0);

            //当日株式予約注文単位一覧
            List l_lisTodaysRsvEqOrderUnits = this.getTodaysRsvEqOrderUnits(l_lngAccountId, l_datT0);

            //株式買付可能額～連続注文～
            double l_result;

            //現物顧客の場合
            if(l_blnMargin == false)
            {
                //余力計算結果(List)を取得
                List l_lisCalcResult = WEB3TPTradingPowerCalcEquity.findCalcResultEquityParams(l_lngAccountId);

                //資産余力情報
                WEB3TPTradingPowerCalcEquity l_calcEquity = new WEB3TPTradingPowerCalcEquity(
                        l_lisCalcResult,
                        l_calcCond);

                //当日株式予約注文単位一覧をセット
                l_calcEquity.setTodaysRsvEqOrderUnits(l_lisTodaysRsvEqOrderUnits);

                //株式買付可能額～連続注文～を取得
                l_result = l_calcEquity.calcSuccEquityTradingPower(l_intBasePoint);
            }
            //信用顧客の場合
            else
            {
                //余力計算結果(List)を取得
                List l_lisCalcResult = WEB3TPTradingPowerCalcMargin.findCalcResultMarginParams(l_lngAccountId);

                //資産余力情報
                WEB3TPTradingPowerCalcMargin l_calcMargin = new WEB3TPTradingPowerCalcMargin(
                        l_lisCalcResult,
                        l_calcCond);

                //当日株式予約注文単位一覧をセット
                l_calcMargin.setTodaysRsvEqOrderUnits(l_lisTodaysRsvEqOrderUnits);

                //株式買付可能額～連続注文～を取得
                l_result = l_calcMargin.calcSuccEquityTradingPower(l_intBasePoint);
            }

            //訂正注文の場合
            if(l_estimatedPrice != null)
            {
                //株式買付可能額～連続注文～ = 株式買付可能額～連続注文～ - 訂正前概算受渡代金
                l_result = l_result + l_estimatedPrice.doubleValue();
            }

            //0補正
            l_result = Math.max(0, l_result);
            log.debug("取引可能額 = " + Double.toString(l_result));

            //可能額を返却
            log.exiting(STR_METHOD_NAME);
            return l_result;

        }
        catch(WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                    baseRunEx.getErrorInfo(),
                    baseRunEx.getErrorMethod(),
                    baseRunEx.getErrorMessage(),
                    baseRunEx.getException());
        }
        catch(Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    STR_METHOD_NAME,
                    e.getMessage(),
                    e);
        }
    }

    /**
     * (get信用新規建可能額～連続注文～) <BR>
     * <BR>
     * 連続注文用の信用新規建可能額を計算し返却する。 <BR>
     * <BR>
     * ※シーケンス図「(取引余力サービス)get信用新規建可能額～連続注文～」参照 <BR>
     * <BR>
     * 「返却値 >= 概算受渡代金」⇒取引OK 
     * 「返却値 <  概算受渡代金」⇒取引NG <BR><BR>
     * <BR>
     * 注）返却値 >= 0 とする。 <BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_estimatedPrice - (訂正前概算受渡代金)
     * @@return double
     */
    public double getSuccMarginTradingPower(
            WEB3GentradeSubAccount l_subAccount,
            Double l_estimatedPrice) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getSuccMarginTradingPower(WEB3GentradeSubAccount, Double)";
        log.entering(STR_METHOD_NAME);

        //引数.補助口座がnullの場合
        if(l_subAccount == null)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //口座IDを取得
        long l_lngAccountId = l_subAccount.getAccountId();

        //顧客オブジェクトを取得
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //信用口座開設区分を取得
        boolean l_blnMargin = l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        try
        {
            //余力計算条件
            WEB3TPCalcCondition l_calcCond = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            //営業日(T+0)
            Date l_datT0 = l_calcCond.getBizDate(WEB3TPSpecifiedPointDef.T_0);

            //当日株式予約注文単位一覧
            List l_lisTodaysRsvEqOrderUnits = this.getTodaysRsvEqOrderUnits(l_lngAccountId, l_datT0);

            //信用新規建可能額～連続注文～
            double l_result;

            //現物顧客の場合
            if(l_blnMargin == false)
            {
                //エラーをスロー
                log.error("illegal Argument");
                throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            //信用顧客の場合
            else
            {
                //余力計算結果(List)を取得
                List l_lisCalcResult = WEB3TPTradingPowerCalcMargin.findCalcResultMarginParams(l_lngAccountId);

                //資産余力情報
                WEB3TPTradingPowerCalcMargin l_calcMargin = new WEB3TPTradingPowerCalcMargin(
                        l_lisCalcResult,
                        l_calcCond);

                //当日株式予約注文単位一覧をセット
                l_calcMargin.setTodaysRsvEqOrderUnits(l_lisTodaysRsvEqOrderUnits);

                //信用新規建可能額～連続注文～を取得
                l_result = l_calcMargin.calcSuccMarginTradingPower();
            }

            //訂正注文の場合
            if(l_estimatedPrice != null)
            {
                //信用新規建可能額～連続注文～ = 信用新規建可能額～連続注文～ - 訂正前概算受渡代金
                l_result = l_result + l_estimatedPrice.doubleValue();
            }

            //0補正
            l_result = Math.max(0, l_result);
            log.debug("取引可能額 = " + Double.toString(l_result));

            //可能額を返却
            log.exiting(STR_METHOD_NAME);
            return l_result;

        }
        catch(WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                    baseRunEx.getErrorInfo(),
                    baseRunEx.getErrorMethod(),
                    baseRunEx.getErrorMessage(),
                    baseRunEx.getException());
        }
        catch(Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    STR_METHOD_NAME,
                    e.getMessage(),
                    e);
        }
    }

    /**
     * (get信用現引可能額～連続注文～) <BR>
     * <BR>
     * 連続注文用の信用現引可能額を計算し返却する。 <BR>
     * <BR>
     * ※シーケンス図「(取引余力サービス)get信用現引可能額～連続注文～」参照 <BR>
     * <BR>
     * 「返却値 >= 概算受渡代金」⇒取引OK <BR>
     * 「返却値 <  概算受渡代金」⇒取引NG <BR>
     * <BR>
     * 注）返却値 >= 0 とする。 <BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_datDeliveryDate - (受渡日)
     * @@return double
     */
    public double getSuccActualReceiptTradingPower(
            WEB3GentradeSubAccount l_subAccount,
            Date l_datDeliveryDate) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getSuccActualReceiptTradingPower(WEB3GentradeSubAccount, Date)";
        log.entering(STR_METHOD_NAME);

        //引数.補助口座がnull の場合
        if(l_subAccount == null)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //口座IDを取得
        long l_lngAccountId = l_subAccount.getAccountId();

        //顧客オブジェクトを取得
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //信用口座開設区分を取得
        boolean l_blnMargin = l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        try
        {
            //余力計算条件
            WEB3TPCalcCondition l_calcCond = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            /*
             * 基準日を取得
             */
            int l_intBasePoint;
            //引数.受渡日 == nullの場合
            if(l_datDeliveryDate == null)
            {
                //余力計算基準日<株式買付/信用現引>を取得
                l_intBasePoint = l_calcCond.getEquityBasePoint();
            }
            //以外の場合
            else 
            {
                //引数.受渡日に対応する指定日を取得
                l_intBasePoint = l_calcCond.calcSpecifiedPoint(l_datDeliveryDate);
            }

            //営業日(T+0)
            Date l_datT0 = l_calcCond.getBizDate(WEB3TPSpecifiedPointDef.T_0);

            //当日株式予約注文単位一覧
            List l_lisTodaysRsvEqOrderUnits = this.getTodaysRsvEqOrderUnits(l_lngAccountId, l_datT0);

            //信用現引可能額～連続注文～
            double l_result;

            //現物顧客の場合
            if(l_blnMargin == false)
            {
                //エラーをスロー
                log.error("illegal Argument");
                throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            //信用顧客の場合
            else
            {
                //余力計算結果(List)を取得
                List l_lisCalcResult = WEB3TPTradingPowerCalcMargin.findCalcResultMarginParams(l_lngAccountId);

                //資産余力情報
                WEB3TPTradingPowerCalcMargin l_calcMargin = new WEB3TPTradingPowerCalcMargin(
                        l_lisCalcResult,
                        l_calcCond);

                //当日株式予約注文単位一覧をセット
                l_calcMargin.setTodaysRsvEqOrderUnits(l_lisTodaysRsvEqOrderUnits);

                //信用現引可能額～連続注文～を取得
                l_result = l_calcMargin.calcSuccActualReceiptTradingPower(l_intBasePoint);
            }

            //0補正
            l_result = Math.max(0, l_result);
            log.debug("取引可能額 = " + Double.toString(l_result));

            //可能額を返却
            log.exiting(STR_METHOD_NAME);
            return l_result;

        }
        catch(WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                    baseRunEx.getErrorInfo(),
                    baseRunEx.getErrorMethod(),
                    baseRunEx.getErrorMessage(),
                    baseRunEx.getException());
        }
        catch(Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    STR_METHOD_NAME,
                    e.getMessage(),
                    e);
        }
    }

    /**
     * (validate取引余力<現物株式買付>) <BR>
     * <BR>
     * 現物株式買付において、今回注文分を加味した取引余力チェックを実施する。 <BR>
     * <BR>
     * シーケンス図「(取引余力サービス)validate取引余力<現物株式買付>」参照<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_newOrderSpecs - (現注文内容)
     * @@param l_blnUpdateFlg - (余力更新フラグ)
     * trueの時、余力再計算処理を実施する
     * falseの時、余力再計算処理を実施しない
     * @@return webbroker3.tradingpower.WEB3TPTradingPowerResult
     */
    protected WEB3TPTradingPowerResult validateTradingPowerEquityBuy(
        WEB3GentradeSubAccount l_subAccount,
        WEB3TPNewOrderSpec[] l_newOrderSpecs,
        boolean l_blnUpdateFlg)
    {
        final String STR_METHOD_NAME =
            "validateTradingPowerEquityBuy(WEB3GentradeSubAccount, WEB3TPNewOrderSpec[], boolean)";
        log.entering(STR_METHOD_NAME);

        //口座IDを取得
        long l_lngAccountId = l_subAccount.getAccountId();

        //顧客オブジェクトを取得
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //信用口座開設区分を取得
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //余力計算条件
        WEB3TPCalcCondition l_calcCond = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

        /*
         * 取引停止区分チェック
         */
        //取引停止中の場合
        if (l_calcCond.isTradingStop() == true)
        {
            //取引余力結果オブジェクトを生成
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();

            //判定フラグをセット
            l_tpResult.setResultFlg(false);
            //取引可能額をセット
            l_tpResult.setTradingPower(0);

            /*
             * 取引余力エラー情報を生成
             */
            WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
            l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.TRADING_STOP_ERROR;
            l_tpErrorInfo.lackAccountBalance = 0;
            l_tpErrorInfo.buyOrderPossibleAmount = 0;
            l_tpErrorInfo.sellOrderPossibleQuantity = 0;
            l_tpErrorInfo.marginSecInfo = null;

            //取引余力エラー情報をセット
            l_tpResult.setTpErrorInfo(l_tpErrorInfo);

            //取引余力結果を返却する
            log.exiting(STR_METHOD_NAME);
            return l_tpResult;
        }

        //その他商品買付余力停止中の場合
        if (l_calcCond.isOtherTradingStop() == true)
        {
            //取引余力結果オブジェクトを生成
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();

            //判定フラグをセット
            l_tpResult.setResultFlg(false);
            //取引可能額をセット
            l_tpResult.setTradingPower(0);

            /*
             * 取引余力エラー情報を生成
             */
            WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
            l_tpErrorInfo.tradinPowerErrorDiv =
                WEB3TPTradingPowerErrorDivDef.OTHER_TRADING_STOP_ERROR;
            l_tpErrorInfo.lackAccountBalance = 0;
            l_tpErrorInfo.buyOrderPossibleAmount = 0;
            l_tpErrorInfo.sellOrderPossibleQuantity = 0;
            l_tpErrorInfo.marginSecInfo = null;

            //取引余力エラー情報をセット
            l_tpResult.setTpErrorInfo(l_tpErrorInfo);

            //取引余力結果を返却する
            log.exiting(STR_METHOD_NAME);
            return l_tpResult;
        }

        //余力更新オブジェクトを生成
        WEB3TPTradingPowerUpd l_tpUpd =
            new WEB3TPTradingPowerUpd(l_lngAccountId, l_blnMargin, l_calcCond, l_newOrderSpecs);

        /*
         * 即日入金銘柄フラグを取得
         */
        //銘柄ID
        long l_lngProductId = l_newOrderSpecs[0].getProductId();
        //市場ID
        long l_lngMarketId = l_newOrderSpecs[0].getMarketId();
        //即日入金銘柄フラグ
        boolean l_isTodayDepFundReg = this.isTodayDepFundReg(l_lngProductId, l_lngMarketId);
        
        //現物顧客の場合
        if (l_blnMargin == false)
        {
            //余力計算結果(List)～未約定売付注文考慮～を取得
            List l_updResults = l_tpUpd.calcTradingpowerUpdResultEquityIncUnexecSellOrder();

            //資産余力情報オブジェクトを生成
            WEB3TPTradingPowerCalcEquity l_calcEquity =
                new WEB3TPTradingPowerCalcEquity(l_updResults, l_calcCond);

            /*
             * 預り金不足額、現金不足額を計算する。
             * 
             * [預り証券評価制顧客の場合]
             * 　@預り金不足額 = MIN(引出可能現金(T+0..発注日), 株式買付可能額<日計り拘束金考慮>(T+受渡日..5))
             * 　@現金不足額 = MIN(引出可能現金(T+0..発注日), 引出可能現金(T+受渡日..5))
             * 
             * 　@※注文銘柄が即日入金対象銘柄の場合、以下の計算する。
             * 　@　@ 預り金不足額 = MIN(預り金不足額, 使用可能現金(T+0..受渡日))　@　@
             * 
             * [前金制顧客の場合] ※非預り証券評価制顧客
             * 　@預り金不足額 = MIN( 株式買付可能額<日計り拘束金考>(T+0..5) ))
             *   現金不足額 = 0
             */
            //預り金不足額
            double l_dblLackAmt = 0;
            //現金不足額
            double l_dblLackCashAmt = 0;

            //[預り証券評価制顧客の場合]
            if(l_calcCond.isAssetEvalDiv() == true)
            {
                //発注日(：int型)
                int l_intBizDate = l_calcCond
                    .calcSpecifiedPoint(l_newOrderSpecs[0].getOrderBizDate());
                //受渡日(：int型)
                int l_intDeliDate = l_calcCond
                    .calcSpecifiedPoint(l_newOrderSpecs[0].getDeliveryDate());
                
                /*
                 * 預り金不足額　@=　@MIN(引出可能現金(T+0..発注日), 株式買付可能額<日計り拘束金考慮>(T+受渡日..5))
                 * 現金不足額　@=　@MIN(引出可能現金(T+0..発注日), 引出可能現金(T+受渡日..5))
                 */

                //LOOP処理(受渡日～T+5の間)
                for (int index = l_intDeliDate; index <= WEB3TPSpecifiedPointDef.T_5; index++)
                {
                    l_dblLackAmt =
                        Math.min(l_calcEquity.calcEquityTradingPowerIncDayTrade(index), l_dblLackAmt);
                    l_dblLackCashAmt =
                        Math.min(l_calcEquity.calcActualPaymentBalance(index), l_dblLackCashAmt);
                }

                //LOOP処理(T+0～発注日の間)
                for (int index = WEB3TPSpecifiedPointDef.T_0; index <= l_intBizDate; index++)
                {
                    l_dblLackAmt =
                        Math.min(l_calcEquity.calcActualPaymentBalance(index), l_dblLackAmt);
                    l_dblLackCashAmt =
                        Math.min(l_calcEquity.calcActualPaymentBalance(index), l_dblLackCashAmt);
                }

                //即日入金対象銘柄フラグ==trueの場合
                if(l_isTodayDepFundReg == true)
                {
                    /*
                     * 預り金不足額 = MIN(預り金不足額, 使用可能現金(T+発注日..受渡日-1))
                     */

                    //LOOP処理(T+発注日～受渡日-1の間)
                    for (int index = l_intBizDate; index < l_intDeliDate; index++)
                    {
                        l_dblLackAmt =
                            Math.min(l_calcEquity.calcActualAccountBalance(index), l_dblLackAmt);
                    }
                }
            }
            //[前金制顧客の場合] ※非預り証券評価制顧客
            else
            {
                //預り金不足額 = MIN( 株式買付可能額<日計り拘束金考>(T+0..5) ))
                for (int index = 0; index <= WEB3TPSpecifiedPointDef.T_5; index++)
                {
                    l_dblLackAmt =
                        Math.min(l_calcEquity.calcEquityTradingPowerIncDayTrade(index), l_dblLackAmt);
                }
            }
            
            //部店タイプを取得する。
            BranchTypeEnum l_branchType = l_calcCond.getBranchType();
            
            //取引余力結果オブジェクトを生成
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();

            /*
             * 取引余力チェックOK（預り金不足エラーチェックOK または、営業部店）の場合
             * (0 ＜＝ 預り金不足額 || get部店タイプ()の戻り値 == 4:一般国内部店）
             */
            if(l_dblLackAmt >= 0
                    || BranchTypeEnum.REGULAR_LOCAL_BRANCH.equals(l_branchType) == true)
            {
                //余力計算結果(List)を取得
                l_updResults = l_tpUpd.calcTradingpowerUpdResultEquity();

                //資産余力情報オブジェクトを生成
                l_calcEquity = new WEB3TPTradingPowerCalcEquity(
                        l_updResults, l_calcCond);

                //余力計算結果オブジェクトを取得
                WEB3TPCalcResult l_calcResult = l_calcEquity.calcAppliedEquityTradingPower();

                //trueをセット
                l_tpResult.setResultFlg(true);
                //(今回注文後)取引可能額をセット
                l_tpResult.setTradingPower(l_calcResult.tradingPower);
                //取引余力エラー情報をセット
                l_tpResult.setTpErrorInfo(null);
                
                //預り証券評価制顧客かつ現金不足の場合
                if(l_calcCond.isAssetEvalDiv() == true && l_dblLackCashAmt < 0)
                {
                    //注意文言表示区分をセット
                    l_tpResult
                        .setAttentionObjectionType(WEB3TPResultAttentionObjectionTypeDef.LACK_CASH_AMT_ATTENTION);

                    //set預り金不足額(double)
                    //預り金不足額をセットする。
                    //[引数]
                    //double = ABS(現金不足額)
                    l_tpResult.setLackAccountBalance(Math.abs(l_dblLackCashAmt));
                }
                /*
                 * 営業部店　@かつ　@預り金不足の場合
                 * （get部店タイプ()の戻り値 == 4:一般国内部店 && 預り金不足額  < 0）
                 */
                else if(BranchTypeEnum.REGULAR_LOCAL_BRANCH.equals(l_branchType) == true
                        && l_dblLackAmt < 0)
                {
                    //注意文言表示区分をセット
                    l_tpResult.setAttentionObjectionType(WEB3TPResultAttentionObjectionTypeDef.LACK_ACC_BAL_ATTENTION);
                    //預り金不足額をセット
                    l_tpResult.setLackAccountBalance(Math.abs(l_dblLackAmt));
                }
                //以外の場合
                else
                {
                    //注意文言表示区分をセット
                    l_tpResult.setAttentionObjectionType(null);
                }
                
                //余力更新フラグ==trueの場合
                if (l_blnUpdateFlg == true)
                {
                    //余力更新を実施
                    l_tpUpd.saveTradingpowerUpdResultEquity(l_updResults);
                }
            }
            /*
             * 以外（取引余力チェックNG）の場合
             */
            else
            {
                //falseをセット
                l_tpResult.setResultFlg(false);
                //(今回注文後)取引可能額(=0)をセット
                l_tpResult.setTradingPower(0);

                /*
                 * 取引余力エラー情報を生成
                 */
                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
                l_tpErrorInfo.tradinPowerErrorDiv =
                    WEB3TPTradingPowerErrorDivDef.LACK_ACCOUNT_BALANCE;
                l_tpErrorInfo.lackAccountBalance = Math.abs(l_dblLackAmt);
                l_tpErrorInfo.buyOrderPossibleAmount =
                    this.getBuyOrderPossibleAmount(l_subAccount, l_newOrderSpecs[0]);
                l_tpErrorInfo.sellOrderPossibleQuantity = 0;
                l_tpErrorInfo.marginSecInfo = null;

                //取引余力エラー情報をセット
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);
            }

            //取引余力結果を返却する
            log.exiting(STR_METHOD_NAME);
            return l_tpResult;
        }
        //信用顧客の場合
        else
        {
            /*
             * 追証余力チェック
             */
            //注文前余力計算結果を取得
            List l_curUpdResults = WEB3TPTradingPowerCalcMargin
                .findCalcResultMarginParams(l_lngAccountId);

            //資産余力情報オブジェクトを生成
            WEB3TPTradingPowerCalcMargin l_curCalcMargin = new WEB3TPTradingPowerCalcMargin(
                    l_curUpdResults, l_calcCond);
            
            //発注日
            Date l_datBizDate = l_newOrderSpecs[0].getOrderBizDate();
            int l_intBizDate = l_calcCond.calcSpecifiedPoint(l_datBizDate);
            
            //受渡日
            Date l_datDeliDate = l_newOrderSpecs[0].getDeliveryDate();
            int l_intDeliDate = l_calcCond.calcSpecifiedPoint(l_datDeliDate);

            //追証余力<適用可能額チェック用>を取得
            WEB3TPCalcResult l_marginCallPower = l_curCalcMargin
                .calcMarginCallPowerForCheck(
                        OrderTypeEnum.EQUITY_BUY, l_intBizDate, l_intDeliDate);
            
            //追証チェックNGの場合(余力計算結果.取引可能額 < 0)
            if(l_marginCallPower != null && l_marginCallPower.tradingPower < 0)
            {
                //取引余力結果オブジェクトを生成
                WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();

                //判定フラグをセット
                l_tpResult.setResultFlg(false);
                //取引可能額をセット
                l_tpResult.setTradingPower(0);

                /*
                 * 取引余力エラー情報を生成
                 */
                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
                l_tpErrorInfo.tradinPowerErrorDiv =
                    WEB3TPTradingPowerErrorDivDef.LACK_ACCOUNT_BALANCE;
                l_tpErrorInfo.lackAccountBalance = l_marginCallPower.tradingPower;
                l_tpErrorInfo.buyOrderPossibleAmount = 0;
                l_tpErrorInfo.sellOrderPossibleQuantity = 0;
                l_tpErrorInfo.marginSecInfo = null;

                //取引余力エラー情報をセット
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);

                //取引余力結果を返却する
                log.exiting(STR_METHOD_NAME);
                return l_tpResult;
            }
            
            /*
             * “取引余力チェック方法@-現物株式買付注文時”を取得する。
             */
            //取引余力チェック方法@-現物株式買付注文時
            String l_strInstBranCalcCond = l_calcCond.getInstBranCalcCondition(WEB3TPCalcCondition.EQUITYBUY_TRADINGPOWER_CHECK);
            
            /*
             * 余力計算結果(List)～未約定売付注文考慮～を取得
             */
            //余力計算結果(List)～未約定売付注文考慮～
            List l_updResults = null;
            
            //“取引余力チェック方法@-現物株式買付注文時”== 1:BRANCH_SUBRATE の場合
            if(WEB3TPEquityBuyTradingPowerCheckTypeDef.BRANCH_SUBRATE.equals(l_strInstBranCalcCond) == true)
            {
                //買付後余力更新オブジェクトを生成
                WEB3TPTradingPowerUpdAfterBuy l_tpUpdAfterBuy =
                    new WEB3TPTradingPowerUpdAfterBuy(l_lngAccountId, l_blnMargin, l_calcCond, l_newOrderSpecs);
                
                //余力計算結果(List)～未約定売付注文考慮～を取得
                l_updResults =
                    l_tpUpdAfterBuy.calcTradingpowerUpdResultMarginIncUnexecSellOrder();
            }
            //以外(“取引余力チェック方法@-現物株式買付注文時”== 0:DEFAULT or null)の場合
            else
            {
                //余力計算結果(List)～未約定売付注文考慮～を取得
                l_updResults =
                    l_tpUpd.calcTradingpowerUpdResultMarginIncUnexecSellOrder();
            }

            //資産余力情報オブジェクトを生成
            WEB3TPTradingPowerCalcMargin l_calcMargin =
                new WEB3TPTradingPowerCalcMargin(l_updResults, l_calcCond);

            /*
             * 預り金不足額を計算する
             * 
             *  　@預り金不足額 = MIN( 預り金請求余力(T+0..T+受渡日-1), 
             *                       引出可能現金(T+受渡日..5), 
             *                       保証金引出余力(T+受渡日..5) )
             */
            double l_dblLackAmt = 0;

            //T+0..T+受渡日-1
            for(int index = 0; index < l_intDeliDate; index++)
            {
                //預り金請求余力(T+0..T+受渡日-1)
	            l_dblLackAmt = Math.min(l_calcMargin
	                .calcAccountBalanceDemandPower(index), l_dblLackAmt);
            }
            //T+受渡日..T+5
            for(int index = l_intDeliDate; index <= WEB3TPSpecifiedPointDef.T_5; index++)
            {
                //引出可能現金(T+受渡日..5)
                l_dblLackAmt = Math.min(l_calcMargin
                    .calcActualPaymentBalance(index), l_dblLackAmt);

                //保証金引出余力(T+受渡日..5)
                l_dblLackAmt = Math.min(l_calcMargin
                    .calcMarginDrawPower(index), l_dblLackAmt);
            }

            //即日入金対象銘柄フラグ==trueの場合
            if(l_isTodayDepFundReg == true)
            {
                /*
                 * 預り金不足額 = MIN(預り金不足額, 
                 *                   引出可能現金(T+発注日..受渡日-1),
                 *                   保証金引出余力(T+発注日..受渡日-1))
                 */
                //LOOP処理(T+発注日～受渡日-1の間)
                for (int index = l_intBizDate; index < l_intDeliDate; index++)
                {
                    //引出可能現金(T+発注日..受渡日-1)
                    l_dblLackAmt =
                        Math.min(l_calcMargin.calcActualPaymentBalance(index), l_dblLackAmt);

                    //保証金引出余力(T+発注日..受渡日-1)
                    l_dblLackAmt =
                        Math.min(l_calcMargin.calcMarginDrawPower(index), l_dblLackAmt);
                }
            }
            
            /*
             * 注文銘柄の二階建チェックを実施
             */
            //余力計算結果(List)～未約定売付注文考慮～を取得
            l_updResults =
                l_tpUpd.calcTradingpowerUpdResultMarginIncUnexecSellOrder();

            //資産余力情報オブジェクトを生成
            l_calcMargin = new WEB3TPTradingPowerCalcMargin(
                    l_updResults, l_calcCond);

            //二階建チェックエラー銘柄情報の配列を取得
            WEB3TPMarginSecurityInfo[] l_marginSecs = this.calcMarginSecurity(
                    l_subAccount, l_lngProductId, l_calcMargin, l_tpUpd,
                    OrderTypeEnum.EQUITY_BUY, l_lngMarketId, l_datBizDate);
            
            //部店タイプを取得する。
            BranchTypeEnum l_branchType = l_calcCond.getBranchType();
            
            /*
             * 取引余力結果オブジェクトを生成
             */
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();

            /*
             * 取引余力チェックOK（二階建チェックOK　@かつ　@（預り金不足エラーチェックOKまたは、営業部店））の場合
             * (二階建銘柄情報一覧[] == null && (0 ＜＝ 預り金不足額　@または get部店タイプ()の戻り値 == 4:一般国内部店)
             * 
             * ※二階建銘柄情報一覧[] = 余力計算.calc二階建()の戻り値
             */
            if(l_marginSecs == null
                    && (l_dblLackAmt >= 0 || BranchTypeEnum.REGULAR_LOCAL_BRANCH.equals(l_branchType) == true))
            {
                //余力計算結果(List)を取得
                l_updResults = l_tpUpd
                    .calcTradingpowerUpdResultMargin(WEB3TPMarkToMarketDivDef.NORMAL);

                //資産余力情報オブジェクトを生成
                l_calcMargin = new WEB3TPTradingPowerCalcMargin(
                        l_updResults, l_calcCond);

                //余力計算結果オブジェクトを取得
                WEB3TPCalcResult l_calcResult = l_calcMargin.calcAppliedEquityTradingPower();

                //trueをセット
                l_tpResult.setResultFlg(true);
                //(今回注文後)取引可能額をセット
                l_tpResult.setTradingPower(l_calcResult.tradingPower);
                //取引余力エラー情報をセット
                l_tpResult.setTpErrorInfo(null);

                /*
                 * 営業部店　@かつ　@預り金不足の場合
                 * （get部店タイプ()の戻り値 == 4:一般国内部店 && 預り金不足額  < 0）
                 */
                if(BranchTypeEnum.REGULAR_LOCAL_BRANCH.equals(l_branchType) == true
                        && l_dblLackAmt < 0)
                {
                    //注意文言表示区分をセット
                    l_tpResult.setAttentionObjectionType(WEB3TPResultAttentionObjectionTypeDef.LACK_ACC_BAL_ATTENTION);
                    //預り金不足額をセット
                    l_tpResult.setLackAccountBalance(Math.abs(l_dblLackAmt));
                }
                //以外の場合
                else
                {
                    //注意文言表示区分をセット
                    l_tpResult.setAttentionObjectionType(null);
                }
                
                /*
                 * 余力更新を実施
                 */
                //余力更新フラグ==trueの場合
                if (l_blnUpdateFlg == true)
                {
                    //余力更新を実施
                    l_tpUpd.saveTradingpowerUpdResultMargin(l_updResults);
                }
            }
            /*
             * 以外（取引余力チェックNG）の場合
             */
            else
            {
                //falseをセット
                l_tpResult.setResultFlg(false);

                /*
                 * 取引余力エラー情報を生成
                 */
                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();

                //0 ＞ 預り金不足額 かつ get部店タイプ()の戻り値 ≠ 4:一般国内部店の場合
                if(l_dblLackAmt < 0
                        && BranchTypeEnum.REGULAR_LOCAL_BRANCH.equals(l_branchType) == false)
                {
                    l_tpErrorInfo.tradinPowerErrorDiv =
                        WEB3TPTradingPowerErrorDivDef.LACK_ACCOUNT_BALANCE;
                    l_tpErrorInfo.lackAccountBalance = Math.abs(l_dblLackAmt);
                }
                //それ以外(二階建エラーの場合)
                else
                {
                    l_tpErrorInfo.tradinPowerErrorDiv =
                        WEB3TPTradingPowerErrorDivDef.MARGIN_SEC_ERROR;
                    l_tpErrorInfo.lackAccountBalance = 0;
                }

                l_tpErrorInfo.buyOrderPossibleAmount =
                    this.getBuyOrderPossibleAmount(l_subAccount, l_newOrderSpecs[0]);
                l_tpErrorInfo.sellOrderPossibleQuantity = 0;
                l_tpErrorInfo.marginSecInfo = l_marginSecs;

                //取引余力エラー情報をセット
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);
            }

            //取引余力結果を返却する
            log.exiting(STR_METHOD_NAME);
            return l_tpResult;
        }
    }

    /**
     * (validate取引余力<現物株式売付>)<BR>
     * <BR>
     * 現物株式売付において、今回注文分を加味した取引余力チェックを実施する。<BR>
     * <BR>
     * シーケンス図「(取引余力サービス)validate取引余力<現物株式売付>」参照<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_newOrderSpecs - (現注文内容)
     * @@param l_blnUpdateFlg - (余力更新フラグ)
     * trueの時、余力再計算処理を実施する
     * falseの時、余力再計算処理を実施しない
     * @@return webbroker3.tradingpower.WEB3TPTradingPowerResult
     */
    protected WEB3TPTradingPowerResult validateTradingPowerEquitySell(
        WEB3GentradeSubAccount l_subAccount,
        WEB3TPNewOrderSpec[] l_newOrderSpecs,
        boolean l_blnUpdateFlg)
    {
        final String STR_METHOD_NAME = "validateTradingPowerEquitySell(WEB3GentradeSubAccount, WEB3TPNewOrderSpec[], boolean)";
        log.entering(STR_METHOD_NAME);

        //口座IDを取得
        long l_lngAccountId = l_subAccount.getAccountId();

        //顧客オブジェクトを取得
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_subAccount.getMainAccount();

        //信用口座開設区分を取得
        boolean l_blnMargin = l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //余力計算条件
        WEB3TPCalcCondition l_calcCond = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

        //銘柄ID
        long l_lngProductId = l_newOrderSpecs[0].getProductId();

        //(注文後)売付時余力更新
        WEB3TPTradingPowerUpdAfterSell l_tpUpdAfter = new WEB3TPTradingPowerUpdAfterSell(
            l_lngAccountId,
            l_blnMargin,
            l_calcCond,
            l_newOrderSpecs,
            l_lngProductId);

        //取引余力結果
        WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
        //受渡日(:int)
        int l_intDeliDate = l_calcCond.calcSpecifiedPoint(l_newOrderSpecs[0].getDeliveryDate());
        //取引判定フラグ
        boolean l_blnTradingPower = false;
        //部店タイプ
        BranchTypeEnum l_branchType = l_calcCond.getBranchType();

        //現物顧客の場合
        if(l_blnMargin == false)
        {
            //(注文後)引出可能現金(受渡日)
            WEB3TPTradingPowerCalcEquity l_calcEquityAfter = new WEB3TPTradingPowerCalcEquity(
                l_tpUpdAfter.calcTradingpowerUpdResultEquityIncUnexecSellOrder(),
                l_calcCond);
            double l_dblActualPayBalAfter = l_calcEquityAfter.calcActualPaymentBalance(l_intDeliDate);

            //(注文前)売付時余力更新
            WEB3TPTradingPowerUpdAfterSell l_tpUpdBefore = new WEB3TPTradingPowerUpdAfterSell(
                l_lngAccountId,
                l_blnMargin,
                l_calcCond,
                null,
                l_lngProductId);

            //(注文前)引出可能現金(受渡日)
            WEB3TPTradingPowerCalcEquity l_calcEquityBefore = new WEB3TPTradingPowerCalcEquity(
                l_tpUpdBefore.calcTradingpowerUpdResultEquityIncUnexecSellOrder(),
                l_calcCond);
            double l_dblActualPayBalBefore = l_calcEquityBefore.calcActualPaymentBalance(l_intDeliDate);

            //差金決済相当外買付代金非考慮の(注文後)引出可能現金(T+受渡日)
            double l_dblActualPaymentBalance = 0;

            // (*)分岐フロー
            // (注文後)引出可能現金(T+受渡日) < 0、かつ、
            // (注文前)引出可能現金(T+受渡日) <= (注文後)引出可能現金(T+受渡日)の場合
            if (l_dblActualPayBalAfter < 0 && l_dblActualPayBalBefore <= l_dblActualPayBalAfter)
            {
                //get会社部店別余力計算条件(String)
                String l_strExcludeExceptSettlementBuyAmountCheck =
                    l_calcCond.getInstBranCalcCondition(
                        WEB3TPCalcCondition.EXCLUDE_EXCEPT_SETTLEMENT_BUY_AMOUNT_CHECK);
 
                //is預り証券評価制( )
                boolean l_blnIsAssetEvalDiv = l_calcCond.isAssetEvalDiv();

                //get日計り拘束金(int)
                //（注文前）日計り拘束金を取得する。
                //[引数]
                //受渡日:余力計算条件.calc指定日()の戻り値
                double l_dblDayTradeRestraintBefore = l_calcEquityBefore.getDayTradeRestraint(l_intDeliDate);

                //get日計り拘束金(int)
                //（注文後）日計り拘束金を取得する。
                //[引数]
                //受渡日:余力計算条件.calc指定日()の戻り値
                double l_dblDayTradeRestraintAfter = l_calcEquityAfter.getDayTradeRestraint(l_intDeliDate);

                // (*)分岐フロー
                // 差金決済相当外買付代金非考慮の差金決済チェックする(
                // get会社部店別余力計算条件()の戻り値 == 1：実施する)かつ、
                // is預り証券評価制()の戻り値 == true かつ
                // (注文前)get日計り拘束金(T+受渡日) < (注文後)get日計り拘束金(T+受渡日)の場合
                if (WEB3TPExcludeExceptSettlementBuyAmountCheckDef.EXECUTE.equals(
                    l_strExcludeExceptSettlementBuyAmountCheck)
                    && l_blnIsAssetEvalDiv && l_dblDayTradeRestraintBefore < l_dblDayTradeRestraintAfter)
                {
                    //calc余力更新内容<現物顧客>～未約定売付注文考慮～(boolean)
                    List l_lisOrders = l_tpUpdAfter.calcTradingpowerUpdResultEquityIncUnexecSellOrder(true);

                    //資産余力情報<現物顧客>(List, 余力計算条件)
                    WEB3TPTradingPowerCalcEquity l_tradingPowerCalcEquity =
                        new WEB3TPTradingPowerCalcEquity(l_lisOrders, l_calcCond);

                    //calc引出可能現金(int)
                    l_dblActualPaymentBalance =
                        l_tradingPowerCalcEquity.calcActualPaymentBalance(l_intDeliDate);
                }
            }

            //「営業部店」の場合
            if(BranchTypeEnum.REGULAR_LOCAL_BRANCH.equals(l_branchType) == true)
            {
                l_blnTradingPower = true;
            }
            //以外の場合
            else
            {
                // 差金決済相当外買付代金非考慮の(注文後)引出可能現金(T+受渡日) >= 0 かつ、
                // (「（注文後）引出可能現金(T+受渡日) >= 0」 または
                //「（注文後）引出可能現金(T+受渡日) >= （注文前）引出可能現金(T+受渡日)」)の場合
                if(l_dblActualPaymentBalance >= 0
                    && (l_dblActualPayBalAfter >= 0
                    || l_dblActualPayBalAfter >= l_dblActualPayBalBefore))
                {
                    l_blnTradingPower = true;
                }
                //以外の場合
                else
                {
                    l_blnTradingPower = false;
                }
            }

            //分岐フロー：取引OKの場合
            if(l_blnTradingPower == true)
            {
                //判定フラグ
                l_tpResult.setResultFlg(true);
                //取引可能額
                l_tpResult.setTradingPower(0);
                //取引エラー情報
                l_tpResult.setTpErrorInfo(null);

                /*
                 * [「営業部店」 かつ 「(注文後)引出可能現金(T+受渡日) < 0」 かつ 
                 *  「（注文後）引出可能現金(T+受渡日) < （注文前）引出可能現金(T+受渡日)」の場合]
                 */
                if(BranchTypeEnum.REGULAR_LOCAL_BRANCH.equals(l_branchType) == true
                        && l_dblActualPayBalAfter < 0
                        && l_dblActualPayBalAfter < l_dblActualPayBalBefore)
                {
                    //注意文言表示区分をセット
                    l_tpResult.setAttentionObjectionType(WEB3TPResultAttentionObjectionTypeDef.LACK_ACC_BAL_ATTENTION);
                    //預り金不足額をセット
                    l_tpResult.setLackAccountBalance(Math.abs(l_dblActualPayBalAfter));
                }
                //以外の場合
                else
                {
                    //注意文言表示区分
                    l_tpResult.setAttentionObjectionType(null);
                }

                //submit処理の場合
                if(l_blnUpdateFlg == true)
                {
                    //余力更新
                    WEB3TPTradingPowerUpd l_tpUpd = new WEB3TPTradingPowerUpd(
                        l_lngAccountId,
                        l_blnMargin,
                        l_calcCond,
                        l_newOrderSpecs);

                    //余力更新を実施
                    l_tpUpd.saveTradingpowerUpdResultEquity(l_tpUpd.calcTradingpowerUpdResultEquity());
                }
            }
            //分岐フロー：取引NGの場合
            else
            {
                //判定フラグ
                l_tpResult.setResultFlg(false);
                //取引可能額
                l_tpResult.setTradingPower(0);
                //注意文言表示区分
                l_tpResult.setAttentionObjectionType(null);

                /*
                 * 取引余力エラー情報
                 */
                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
                l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.LACK_ACCOUNT_BALANCE;
                //差金決済相当外買付代金非考慮の(注文後)引出可能現金(T+受渡日) < 0 の場合
                if (l_dblActualPaymentBalance < 0)
                {
                    //預り金不足額 = ABS(差金決済相当外買付代金非考慮の(注文後)引出可能現金(T+受渡日))
                    l_tpErrorInfo.lackAccountBalance = Math.abs(l_dblActualPaymentBalance);
                }
                //それ以外
                else
                {
                    //預り金不足額 = ABS((注文後)引出可能現金(T+受渡日))
                    l_tpErrorInfo.lackAccountBalance = Math.abs(l_dblActualPayBalAfter);
                }

                l_tpErrorInfo.buyOrderPossibleAmount = 0;
                l_tpErrorInfo.sellOrderPossibleQuantity = this.getSellOrderPossibleQuantity(
                    l_subAccount,
                    l_newOrderSpecs[0]);
                l_tpErrorInfo.marginSecInfo = null;

                //取引エラー情報
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);
            }
        }
        //信用顧客の場合
        else
        {
            //(注文後)引出可能現金(受渡日)
            WEB3TPTradingPowerCalcMargin l_calcMarginAfter = new WEB3TPTradingPowerCalcMargin(
                l_tpUpdAfter.calcTradingpowerUpdResultMarginIncUnexecSellOrder(),
                l_calcCond);
            double l_dblActualPayBalAfter = l_calcMarginAfter.calcActualPaymentBalance(l_intDeliDate);

            //(注文前)売付時余力更新
            WEB3TPTradingPowerUpdAfterSell l_tpUpdBefore = new WEB3TPTradingPowerUpdAfterSell(
                l_lngAccountId,
                l_blnMargin,
                l_calcCond,
                null,
                l_lngProductId);

            //(注文前)引出可能現金(受渡日)
            WEB3TPTradingPowerCalcMargin l_calcMarginBefore = new WEB3TPTradingPowerCalcMargin(
                l_tpUpdBefore.calcTradingpowerUpdResultMarginIncUnexecSellOrder(),
                l_calcCond);
            double l_dblActualPayBalBefore = l_calcMarginBefore.calcActualPaymentBalance(l_intDeliDate);

            //「営業部店」または「（注文後）引出可能現金(T+受渡日) >= 0」 の場合
            if(BranchTypeEnum.REGULAR_LOCAL_BRANCH.equals(l_branchType) == true
                    || l_dblActualPayBalAfter >= 0)
            {
                l_blnTradingPower = true;
            }
            //以外の場合
            else
            {
                //「（注文後）引出可能現金(T+受渡日) >= （注文前）引出可能現金(T+受渡日)」　@の場合
                if(l_dblActualPayBalAfter >= l_dblActualPayBalBefore)
                {
                    l_blnTradingPower = true;
                }
                //以外の場合
                else
                {
                    l_blnTradingPower = false;
                }
            }

            //分岐フロー：取引OKの場合
            if(l_blnTradingPower == true)
            {
                //判定フラグ
                l_tpResult.setResultFlg(true);
                //取引可能額
                l_tpResult.setTradingPower(0);
                //取引エラー情報
                l_tpResult.setTpErrorInfo(null);
                //注意文言表示区分
                l_tpResult.setAttentionObjectionType(null);

                /*
                 * [「営業部店」 かつ 「(注文後)引出可能現金(T+受渡日) < 0」 かつ 
                 *  「（注文後）引出可能現金(T+受渡日) < （注文前）引出可能現金(T+受渡日)」の場合]
                 */
                if(BranchTypeEnum.REGULAR_LOCAL_BRANCH.equals(l_branchType) == true
                        && l_dblActualPayBalAfter < 0
                        && l_dblActualPayBalAfter < l_dblActualPayBalBefore)
                {
                    //注意文言表示区分をセット
                    l_tpResult.setAttentionObjectionType(WEB3TPResultAttentionObjectionTypeDef.LACK_ACC_BAL_ATTENTION);
                    //預り金不足額をセット
                    l_tpResult.setLackAccountBalance(Math.abs(l_dblActualPayBalAfter));
                }
                //以外の場合
                else
                {
                    //注意文言表示区分
                    l_tpResult.setAttentionObjectionType(null);
                }

                //submit処理の場合
                if(l_blnUpdateFlg == true)
                {
                    //余力更新
                    WEB3TPTradingPowerUpd l_tpUpd = new WEB3TPTradingPowerUpd(
                        l_lngAccountId,
                        l_blnMargin,
                        l_calcCond,
                        l_newOrderSpecs);

                    //余力更新を実施
                    l_tpUpd.saveTradingpowerUpdResultMargin(l_tpUpd.calcTradingpowerUpdResultMargin(WEB3TPMarkToMarketDivDef.NORMAL));
                }
            }
            //分岐フロー：取引NGの場合
            else
            {
                //判定フラグ
                l_tpResult.setResultFlg(false);
                //取引可能額
                l_tpResult.setTradingPower(0);
                //注意文言表示区分
                l_tpResult.setAttentionObjectionType(null);

                /*
                 * 取引余力エラー情報
                 */
                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
                l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.LACK_ACCOUNT_BALANCE;
                l_tpErrorInfo.lackAccountBalance = Math.abs(l_dblActualPayBalAfter);
                l_tpErrorInfo.buyOrderPossibleAmount = 0;
                l_tpErrorInfo.sellOrderPossibleQuantity = this.getSellOrderPossibleQuantity(
                    l_subAccount,
                    l_newOrderSpecs[0]);
                l_tpErrorInfo.marginSecInfo = null;

                //取引エラー情報
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);
            }
        }

        //取引余力結果を返却する
        log.exiting(STR_METHOD_NAME);
        return l_tpResult;
    }

    /**
     * (validate取引余力<信用取引現引>) <BR>
     * <BR>
     * 信用取引現引において、今回注文分を加味した取引余力チェックを実施する。 <BR>
     * <BR>
     * シーケンス図「(取引余力サービス)validate取引余力<信用取引現引>」参照<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_newOrderSpecs - (現注文内容)
     * @@param l_blnUpdateFlg - (余力更新フラグ)
     * trueの時、余力再計算処理を実施する
     * falseの時、余力再計算処理を実施しない
     * @@return webbroker3.tradingpower.WEB3TPTradingPowerResult
     */
    protected WEB3TPTradingPowerResult validateTradingPowerActualReceipt(
        WEB3GentradeSubAccount l_subAccount,
        WEB3TPNewOrderSpec[] l_newOrderSpecs,
        boolean l_blnUpdateFlg)
    {
        final String STR_METHOD_NAME =
            "validateTradingPowerActualReceipt(WEB3GentradeSubAccount, WEB3TPNewOrderSpec[], boolean)";
        log.entering(STR_METHOD_NAME);

        //口座IDを取得
        long l_lngAccountId = l_subAccount.getAccountId();

        //顧客オブジェクトを取得
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //信用口座開設区分を取得
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //余力計算条件
        WEB3TPCalcCondition l_calcCond = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

        /*
         * 取引停止区分チェック
         */
        //取引停止中の場合
        if (l_calcCond.isTradingStop() == true)
        {
            //取引余力結果オブジェクトを生成
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();

            //判定フラグをセット
            l_tpResult.setResultFlg(false);
            //取引可能額をセット
            l_tpResult.setTradingPower(0);

            /*
             * 取引余力エラー情報を生成
             */
            WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
            l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.TRADING_STOP_ERROR;
            l_tpErrorInfo.lackAccountBalance = 0;
            l_tpErrorInfo.buyOrderPossibleAmount = 0;
            l_tpErrorInfo.sellOrderPossibleQuantity = 0;
            l_tpErrorInfo.marginSecInfo = null;

            //取引余力エラー情報をセット
            l_tpResult.setTpErrorInfo(l_tpErrorInfo);

            //取引余力結果を返却する
            log.exiting(STR_METHOD_NAME);
            return l_tpResult;
        }

        //その他商品買付余力停止中の場合
        if (l_calcCond.isOtherTradingStop() == true)
        {
            //取引余力結果オブジェクトを生成
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();

            //判定フラグをセット
            l_tpResult.setResultFlg(false);
            //取引可能額をセット
            l_tpResult.setTradingPower(0);

            /*
             * 取引余力エラー情報を生成
             */
            WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
            l_tpErrorInfo.tradinPowerErrorDiv =
                WEB3TPTradingPowerErrorDivDef.OTHER_TRADING_STOP_ERROR;
            l_tpErrorInfo.lackAccountBalance = 0;
            l_tpErrorInfo.buyOrderPossibleAmount = 0;
            l_tpErrorInfo.sellOrderPossibleQuantity = 0;
            l_tpErrorInfo.marginSecInfo = null;

            //取引余力エラー情報をセット
            l_tpResult.setTpErrorInfo(l_tpErrorInfo);

            //取引余力結果を返却する
            log.exiting(STR_METHOD_NAME);
            return l_tpResult;
        }

        //余力更新オブジェクトを生成
        WEB3TPTradingPowerUpd l_tpUpd =
            new WEB3TPTradingPowerUpd(l_lngAccountId, l_blnMargin, l_calcCond, l_newOrderSpecs);

        //現物顧客の場合
        if (l_blnMargin == false)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //信用顧客の場合
        else
        {
            /*
             * 追証余力チェック
             */
            //注文前余力計算結果を取得
            List l_curUpdResults = WEB3TPTradingPowerCalcMargin
                .findCalcResultMarginParams(l_lngAccountId);

            //資産余力情報オブジェクトを生成
            WEB3TPTradingPowerCalcMargin l_curCalcMargin = new WEB3TPTradingPowerCalcMargin(
                    l_curUpdResults, l_calcCond);

            //発注日
            Date l_datBizDate = l_newOrderSpecs[0].getOrderBizDate();
            int l_intBizDate = l_calcCond.calcSpecifiedPoint(l_datBizDate);

            //受渡日
            Date l_datDeliDate = l_newOrderSpecs[0].getDeliveryDate();
            int l_intDeliDate = l_calcCond.calcSpecifiedPoint(l_datDeliDate);

            //追証余力<適用可能額チェック用>を取得
            WEB3TPCalcResult l_marginCallPower = l_curCalcMargin
                .calcMarginCallPowerForCheck(
                        OrderTypeEnum.SWAP_MARGIN_LONG, l_intBizDate,
                        l_intDeliDate);

            //追証チェックNGの場合(余力計算結果.取引可能額 < 0)
            if(l_marginCallPower != null && l_marginCallPower.tradingPower < 0)
            {
                //取引余力結果オブジェクトを生成
                WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();

                //判定フラグをセット
                l_tpResult.setResultFlg(false);
                //取引可能額をセット
                l_tpResult.setTradingPower(0);

                /*
                 * 取引余力エラー情報を生成
                 */
                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
                l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.LACK_ACCOUNT_BALANCE;
                l_tpErrorInfo.lackAccountBalance = l_marginCallPower.tradingPower;
                l_tpErrorInfo.buyOrderPossibleAmount = 0;
                l_tpErrorInfo.sellOrderPossibleQuantity = 0;
                l_tpErrorInfo.marginSecInfo = null;

                //取引余力エラー情報をセット
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);

                //取引余力結果を返却する
                log.exiting(STR_METHOD_NAME);
                return l_tpResult;
            }

            /*
             * 余力計算結果(List)～未約定売付注文考慮～を取得
             */
            List l_updResults =
                l_tpUpd.calcTradingpowerUpdResultMarginIncUnexecSellOrder();

            //資産余力情報オブジェクトを生成
            WEB3TPTradingPowerCalcMargin l_calcMargin =
                new WEB3TPTradingPowerCalcMargin(l_updResults, l_calcCond);

            /*
             * 注文銘柄の二階建チェックを実施
             */
            //現注文内容より注文銘柄IDを取得
            long l_lngProductId = l_newOrderSpecs[0].getProductId();
            //現注文内容より市場IDを取得
            long l_lngMarketId = l_newOrderSpecs[0].getMarketId();

            //二階建チェックエラー銘柄情報の配列を取得
            WEB3TPMarginSecurityInfo[] l_marginSecs = this.calcMarginSecurity(l_subAccount,
                    l_lngProductId, l_calcMargin, l_tpUpd, OrderTypeEnum.SWAP_MARGIN_LONG,
                    l_lngMarketId, l_datBizDate);

            /*
             * 預り金不足額を取得
             */
            //会社部店別余力計算条件."取引余力チェック方法@-信用現引注文時" を取得
            String l_strTradingPowerCheck = l_calcCond
                .getInstBranCalcCondition(WEB3TPCalcCondition.MARGINSWAPLONG_TRADINGPOWER_CHECK);

            //今回注文の受渡日に対応する指定日を取得
            int l_intSpecifiedPoint = l_calcCond
                .calcSpecifiedPoint(l_newOrderSpecs[0].getDeliveryDate());

            //預り金不足額
            double l_dblLackAmt = 0;

            //T+0..T+受渡日-1
            for(int index = 0; index < l_intSpecifiedPoint; index++)
            {
                //預り金請求余力(T+0..T+受渡日-1)
	            l_dblLackAmt = Math.min(l_calcMargin
	                .calcAccountBalanceDemandPower(index), l_dblLackAmt);
            }
            //T+受渡日..T+5
            for(int index = l_intSpecifiedPoint; index <= WEB3TPSpecifiedPointDef.T_5; index++)
            {
                //引出可能現金(T+受渡日..5)
                l_dblLackAmt = Math.min(l_calcMargin
                    .calcActualPaymentBalance(index), l_dblLackAmt);

                //"取引余力チェック方法@-信用現引注文時" = 1:ADDDEPOSIT の場合
                if(WEB3TPMarginSwapLongTradingPowerCheckTypeDef.ADDDEPOSIT
                    .equals(l_strTradingPowerCheck) == true)
                {
                    //追証余力(index)を取得
                    l_dblLackAmt = Math.min(l_calcMargin
                        .calcMarginCallPower(index), l_dblLackAmt);
                }
                //以外 の場合
                else
                {
                    //保証金余力(index)を取得
                    l_dblLackAmt = Math.min(l_calcMargin
                        .calcMarginPower(index), l_dblLackAmt);
                }
            }

            //部店タイプを取得する。
            BranchTypeEnum l_branchType = l_calcCond.getBranchType();
            
            /*
             * 取引余力結果オブジェクトを生成
             */
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();

            /*
             * 取引余力チェックOK（二階建チェックOK　@かつ　@（預り金不足エラーチェックOKまたは、営業部店））の場合
             * (二階建銘柄情報一覧[] == null && (0 ＜＝ 預り金不足額　@または get部店タイプ()の戻り値 == 4:一般国内部店)
             */
            if(l_marginSecs == null
                    && (l_dblLackAmt >= 0 || BranchTypeEnum.REGULAR_LOCAL_BRANCH.equals(l_branchType) == true))
            {
                /*
                 * 余力計算結果(List)を取得
                 */
                l_updResults =
                    l_tpUpd.calcTradingpowerUpdResultMargin(WEB3TPMarkToMarketDivDef.NORMAL);

                //資産余力情報オブジェクトを生成
                l_calcMargin =
                    new WEB3TPTradingPowerCalcMargin(l_updResults, l_calcCond);

                //余力計算結果オブジェクトを取得
                WEB3TPCalcResult l_calcResult = l_calcMargin.calcAppliedActualReceiptTradingPower();

                //trueをセット
                l_tpResult.setResultFlg(true);
                //(今回注文後)取引可能額をセット
                l_tpResult.setTradingPower(l_calcResult.tradingPower);
                //取引余力エラー情報をセット
                l_tpResult.setTpErrorInfo(null);

                /*
                 * 営業部店　@かつ　@預り金不足の場合
                 * （get部店タイプ()の戻り値 == 4:一般国内部店 && 預り金不足額  < 0）
                 */
                if(BranchTypeEnum.REGULAR_LOCAL_BRANCH.equals(l_branchType) == true
                        && l_dblLackAmt < 0)
                {
                    //注意文言表示区分をセット
                    l_tpResult.setAttentionObjectionType(WEB3TPResultAttentionObjectionTypeDef.LACK_ACC_BAL_ATTENTION);
                    //預り金不足額をセット
                    l_tpResult.setLackAccountBalance(Math.abs(l_dblLackAmt));
                }
                //以外の場合
                else
                {
                    //注意文言表示区分をセット
                    l_tpResult.setAttentionObjectionType(null);
                }
                
                /*
                 * 余力更新を実施
                 */
                //余力更新フラグ==trueの場合
                if (l_blnUpdateFlg == true)
                {
                    //余力更新を実施
                    l_tpUpd.saveTradingpowerUpdResultMargin(l_updResults);
                }
            }
            //取引余力チェックNG（以外）の場合
            else
            {
                //falseをセット
                l_tpResult.setResultFlg(false);

                /*
                 * 取引余力エラー情報を生成
                 */
                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();

                //0 ＞ 預り金不足額 かつ get部店タイプ()の戻り値 ≠ 4:一般国内部店の場合
                if(l_dblLackAmt < 0
                        && BranchTypeEnum.REGULAR_LOCAL_BRANCH.equals(l_branchType) == false)
                {
                    l_tpErrorInfo.tradinPowerErrorDiv =
                        WEB3TPTradingPowerErrorDivDef.LACK_ACCOUNT_BALANCE;
                    l_tpErrorInfo.lackAccountBalance = Math.abs(l_dblLackAmt);
                }
                //それ以外(二階建エラーの場合)
                else
                {
                    l_tpErrorInfo.tradinPowerErrorDiv =
                        WEB3TPTradingPowerErrorDivDef.MARGIN_SEC_ERROR;
                    l_tpErrorInfo.lackAccountBalance = 0;
                }

                l_tpErrorInfo.buyOrderPossibleAmount =
                    this.getBuyOrderPossibleAmount(l_subAccount, l_newOrderSpecs[0]);
                l_tpErrorInfo.sellOrderPossibleQuantity = 0;
                l_tpErrorInfo.marginSecInfo = l_marginSecs;

                //取引余力エラー情報をセット
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);
            }

            //取引余力結果を返却する
            log.exiting(STR_METHOD_NAME);
            return l_tpResult;
        }
    }

    /**
     * (validate取引余力<信用取引現渡>)<BR>
     * 信用取引現渡において、今回注文分を加味した取引余力チェックを実施する。<BR>
     * <BR>
     * シーケンス図「(取引余力サービス)validate取引余力<信用取引現渡>」参照<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_newOrderSpecs - (現注文内容)
     * @@param l_blnUpdateFlg - (余力更新フラグ)
     * trueの時、余力再計算処理を実施する
     * falseの時、余力再計算処理を実施しない
     * @@return webbroker3.tradingpower.WEB3TPTradingPowerResult
     * @@roseuid 41593E170359
     */
    protected WEB3TPTradingPowerResult validateTradingPowerActualDelivery(
        WEB3GentradeSubAccount l_subAccount,
        WEB3TPNewOrderSpec[] l_newOrderSpecs,
        boolean l_blnUpdateFlg)
    {
        final String STR_METHOD_NAME = "validateTradingPowerActualDelivery(WEB3GentradeSubAccount, WEB3TPNewOrderSpec[], boolean)";
        log.entering(STR_METHOD_NAME);

        //口座IDを取得
        long l_lngAccountId = l_subAccount.getAccountId();

        //顧客オブジェクトを取得
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_subAccount.getMainAccount();

        //信用口座開設区分を取得
        boolean l_blnMargin = l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //現物顧客の場合
        if(l_blnMargin == false)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass()
                .getName()
                + "."
                + STR_METHOD_NAME);
        }

        //余力計算条件
        WEB3TPCalcCondition l_calcCond = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

        //銘柄ID
        long l_lngProductId = l_newOrderSpecs[0].getProductId();

        //(注文後)売付時余力更新
        WEB3TPTradingPowerUpdAfterSell l_tpUpdAfter = new WEB3TPTradingPowerUpdAfterSell(
            l_lngAccountId,
            l_blnMargin,
            l_calcCond,
            l_newOrderSpecs,
            l_lngProductId);

        //取引余力結果
        WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
        //受渡日(:int)
        int l_intDeliDate = l_calcCond.calcSpecifiedPoint(l_newOrderSpecs[0].getDeliveryDate());
        //取引判定フラグ
        boolean l_blnTradingPower = false;
        //部店タイプ
        BranchTypeEnum l_branchType = l_calcCond.getBranchType();
        
        //(注文後)引出可能現金(受渡日)
        WEB3TPTradingPowerCalcMargin l_calcMarginAfter = new WEB3TPTradingPowerCalcMargin(
            l_tpUpdAfter.calcTradingpowerUpdResultMarginIncUnexecSellOrder(),
            l_calcCond);
        double l_dblActualPayBalAfter = l_calcMarginAfter.calcActualPaymentBalance(l_intDeliDate);

        //(注文前)売付時余力更新
        WEB3TPTradingPowerUpdAfterSell l_tpUpdBefore = new WEB3TPTradingPowerUpdAfterSell(
            l_lngAccountId,
            l_blnMargin,
            l_calcCond,
            null,
            l_lngProductId);

        //(注文前)引出可能現金(受渡日)
        WEB3TPTradingPowerCalcMargin l_calcMarginBefore = new WEB3TPTradingPowerCalcMargin(
            l_tpUpdBefore.calcTradingpowerUpdResultMarginIncUnexecSellOrder(),
            l_calcCond);
        double l_dblActualPayBalBefore = l_calcMarginBefore.calcActualPaymentBalance(l_intDeliDate);

        //「営業部店」または「（注文後）引出可能現金(T+受渡日) >= 0」 の場合
        if(BranchTypeEnum.REGULAR_LOCAL_BRANCH.equals(l_branchType) == true
                || l_dblActualPayBalAfter >= 0)
        {
            l_blnTradingPower = true;
        }
        //以外の場合
        else
        {
            //「（注文後）引出可能現金(T+受渡日) >= （注文前）引出可能現金(T+受渡日)」　@の場合
            if(l_dblActualPayBalAfter >= l_dblActualPayBalBefore)
            {
                l_blnTradingPower = true;
            }
            //以外の場合
            else
            {
                l_blnTradingPower = false;
            }
        }

        //分岐フロー：取引OKの場合
        if(l_blnTradingPower == true)
        {
            //判定フラグ
            l_tpResult.setResultFlg(true);
            //取引可能額
            l_tpResult.setTradingPower(0);
            //取引エラー情報
            l_tpResult.setTpErrorInfo(null);
            //注意文言表示区分
            l_tpResult.setAttentionObjectionType(null);

            /*
             * [「営業部店」 かつ 「(注文後)引出可能現金(T+受渡日) < 0」 かつ 
             *  「（注文後）引出可能現金(T+受渡日) < （注文前）引出可能現金(T+受渡日)」の場合]
             */
            if(BranchTypeEnum.REGULAR_LOCAL_BRANCH.equals(l_branchType) == true
                    && l_dblActualPayBalAfter < 0
                    && l_dblActualPayBalAfter < l_dblActualPayBalBefore)
            {
                //注意文言表示区分をセット
                l_tpResult.setAttentionObjectionType(WEB3TPResultAttentionObjectionTypeDef.LACK_ACC_BAL_ATTENTION);
                //預り金不足額をセット
                l_tpResult.setLackAccountBalance(Math.abs(l_dblActualPayBalAfter));
            }
            //以外の場合
            else
            {
                //注意文言表示区分
                l_tpResult.setAttentionObjectionType(null);
            }

            //submit処理の場合
            if(l_blnUpdateFlg == true)
            {
                //余力更新
                WEB3TPTradingPowerUpd l_tpUpd = new WEB3TPTradingPowerUpd(
                    l_lngAccountId,
                    l_blnMargin,
                    l_calcCond,
                    l_newOrderSpecs);

                //余力更新を実施
                l_tpUpd.saveTradingpowerUpdResultMargin(l_tpUpd.calcTradingpowerUpdResultMargin(WEB3TPMarkToMarketDivDef.NORMAL));
            }
        }
        //分岐フロー：取引NGの場合
        else
        {
            //判定フラグ
            l_tpResult.setResultFlg(false);
            //取引可能額
            l_tpResult.setTradingPower(0);
            //注意文言表示区分
            l_tpResult.setAttentionObjectionType(null);

            /*
             * 取引余力エラー情報
             */
            WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
            l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.LACK_ACCOUNT_BALANCE;
            l_tpErrorInfo.lackAccountBalance = Math.abs(l_dblActualPayBalAfter);
            l_tpErrorInfo.buyOrderPossibleAmount = 0;
            l_tpErrorInfo.sellOrderPossibleQuantity = this.getSellOrderPossibleQuantity(
                l_subAccount,
                l_newOrderSpecs[0]);
            l_tpErrorInfo.marginSecInfo = null;

            //取引エラー情報
            l_tpResult.setTpErrorInfo(l_tpErrorInfo);
        }

        //取引余力結果を返却する
        log.exiting(STR_METHOD_NAME);
        return l_tpResult;
    }

    /**
     * (validate取引余力<信用取引現渡取消>)<BR>
     * 信用取引現渡取消注文において、今回注文分を加味した取引余力チェックを実施する。<BR>
     * <BR>
     * シーケンス図「(取引余力サービス)validate取引余力<信用取引現渡取消>」参照<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_newOrderSpecs - (現注文内容)
     * @@param l_blnUpdateFlg - (余力更新フラグ)
     * trueの時、余力再計算処理を実施する
     * falseの時、余力再計算処理を実施しない
     * @@return webbroker3.tradingpower.WEB3TPTradingPowerResult
     */
    protected WEB3TPTradingPowerResult validateTradingPowerActualDeliveryCancel(
        WEB3GentradeSubAccount l_subAccount,
        WEB3TPNewOrderSpec[] l_newOrderSpecs,
        boolean l_blnUpdateFlg)
    {
        final String STR_METHOD_NAME =
            "validateTradingPowerActualDeliveryCancel(WEB3GentradeSubAccount, WEB3TPNewOrderSpec[], boolean)";
        log.entering(STR_METHOD_NAME);

        //口座IDを取得
        long l_lngAccountId = l_subAccount.getAccountId();

        //顧客オブジェクトを取得
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //信用口座開設区分を取得
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //余力計算条件
        WEB3TPCalcCondition l_calcCond = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

        //余力更新オブジェクトを生成
        WEB3TPTradingPowerUpd l_tpUpd =
            new WEB3TPTradingPowerUpd(l_lngAccountId, l_blnMargin, l_calcCond, l_newOrderSpecs);

        //現物顧客の場合
        if (l_blnMargin == false)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //信用顧客の場合
        else
        {
            /*
             * 余力計算結果(List)～未約定売付注文考慮～を取得
             */
            List l_updResults =
                l_tpUpd.calcTradingpowerUpdResultMarginIncUnexecSellOrder();

            //資産余力情報オブジェクトを生成
            WEB3TPTradingPowerCalcMargin l_calcMargin =
                new WEB3TPTradingPowerCalcMargin(l_updResults, l_calcCond);

            /*
             * 取引余力結果オブジェクトを生成
             */
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();

            /*
             * 預り金不足額を計算する
             * 
             *  　@預り金不足額 = MIN( 預り金請求余力(T+0..T+受渡日-1), 
             *                       引出可能現金(T+受渡日..5), 
             *                       保証金引出余力(T+受渡日..5) )
             */
            //今回注文の受渡日に対応する指定日を取得
            int l_intSpecifiedPoint = l_calcCond
                .calcSpecifiedPoint(l_newOrderSpecs[0].getDeliveryDate());

            double l_dblLackAmt = 0;

            //T+0..T+受渡日-1
            for(int index = 0; index < l_intSpecifiedPoint; index++)
            {
                //預り金請求余力(T+0..T+受渡日-1)
	            l_dblLackAmt = Math.min(l_calcMargin
	                .calcAccountBalanceDemandPower(index), l_dblLackAmt);
            }
            //T+受渡日..T+5
            for(int index = l_intSpecifiedPoint; index <= WEB3TPSpecifiedPointDef.T_5; index++)
            {
                //引出可能現金(T+受渡日..5)
                l_dblLackAmt = Math.min(l_calcMargin
                    .calcActualPaymentBalance(index), l_dblLackAmt);

                //保証金引出余力(T+受渡日..5)
                l_dblLackAmt = Math.min(l_calcMargin
                    .calcMarginDrawPower(index), l_dblLackAmt);
            }
            
            //部店タイプを取得
            BranchTypeEnum l_branchType = l_calcCond.getBranchType();
            
            /*
             * 取引余力チェックOK（預り金不足エラーチェックOKまたは、営業部店）の場合
             * ((0 ＜＝ 預り金不足額　@または get部店タイプ()の戻り値 == 4:一般国内部店)
             */
            if(l_dblLackAmt >= 0
                    || BranchTypeEnum.REGULAR_LOCAL_BRANCH.equals(l_branchType) == true)
            {
                //trueをセット
                l_tpResult.setResultFlg(true);
                //(今回注文後)取引可能額をセット
                l_tpResult.setTradingPower(0);
                //取引余力エラー情報をセット
                l_tpResult.setTpErrorInfo(null);

                /*
                 * 営業部店　@かつ　@預り金不足の場合
                 * （get部店タイプ()の戻り値 == 4:一般国内部店 && 預り金不足額  < 0）
                 */
                if(BranchTypeEnum.REGULAR_LOCAL_BRANCH.equals(l_branchType) == true
                        && l_dblLackAmt < 0)
                {
                    //注意文言表示区分をセット
                    l_tpResult.setAttentionObjectionType(WEB3TPResultAttentionObjectionTypeDef.LACK_ACC_BAL_ATTENTION);
                    //預り金不足額をセット
                    l_tpResult.setLackAccountBalance(Math.abs(l_dblLackAmt));
                }
                //以外の場合
                else
                {
                    //注意文言表示区分をセット
                    l_tpResult.setAttentionObjectionType(null);
                }

                /*
                 * 余力更新を実施
                 */
                //余力更新フラグ==trueの場合
                if (l_blnUpdateFlg == true)
                {
                    /*
                     * 余力計算結果(List)を取得
                     */
                    l_updResults = l_tpUpd
                        .calcTradingpowerUpdResultMargin(WEB3TPMarkToMarketDivDef.NORMAL);
                    
                    //余力更新を実施
                    l_tpUpd.saveTradingpowerUpdResultMargin(l_updResults);
                }
            }
            //取引余力チェックNG（以外）の場合
            else
            {
                //falseをセット
                l_tpResult.setResultFlg(false);

                /*
                 * 取引余力エラー情報を生成
                 */
                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();

                l_tpErrorInfo.tradinPowerErrorDiv =
                    WEB3TPTradingPowerErrorDivDef.LACK_ACCOUNT_BALANCE;

                l_tpErrorInfo.lackAccountBalance = Math.abs(l_dblLackAmt);
                l_tpErrorInfo.buyOrderPossibleAmount = 0;
                l_tpErrorInfo.sellOrderPossibleQuantity = 0;
                l_tpErrorInfo.marginSecInfo = null;

                //取引余力エラー情報をセット
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);
            }

            //取引余力結果を返却する
            log.exiting(STR_METHOD_NAME);
            return l_tpResult;
        }
    }

    /**
     * (validate取引余力<信用取引新規建>)<BR>
     * 信用取引新規建において、今回注文分を加味した取引余力チェックを実施する。<BR>
     * <BR>
     * シーケンス図「(取引余力サービス)validate取引余力<信用取引新規建>」参照<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_newOrderSpecs - (現注文内容)
     * @@param l_blnUpdateFlg - (余力更新フラグ)
     * trueの時、余力再計算処理を実施する
     * falseの時、余力再計算処理を実施しない
     * @@return webbroker3.tradingpower.WEB3TPTradingPowerResult
     * @@throws WEB3BaseException
     */
    protected WEB3TPTradingPowerResult validateTradingPowerMargin(
        WEB3GentradeSubAccount l_subAccount,
        WEB3TPNewOrderSpec[] l_newOrderSpecs,
        boolean l_blnUpdateFlg) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateTradingPowerMargin(WEB3GentradeSubAccount, WEB3TPNewOrderSpec[], boolean)";
        log.entering(STR_METHOD_NAME);

        //口座IDを取得
        long l_lngAccountId = l_subAccount.getAccountId();

        //顧客オブジェクトを取得
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //信用口座開設区分を取得
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //余力計算条件
        WEB3TPCalcCondition l_calcCond = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

        /*
         * 取引停止区分チェック
         */
        //取引停止中の場合
        if (l_calcCond.isTradingStop() == true)
        {
            //取引余力結果オブジェクトを生成
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();

            //判定フラグをセット
            l_tpResult.setResultFlg(false);
            //取引可能額をセット
            l_tpResult.setTradingPower(0);

            /*
             * 取引余力エラー情報を生成
             */
            WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
            l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.TRADING_STOP_ERROR;
            l_tpErrorInfo.lackAccountBalance = 0;
            l_tpErrorInfo.buyOrderPossibleAmount = 0;
            l_tpErrorInfo.sellOrderPossibleQuantity = 0;
            l_tpErrorInfo.marginSecInfo = null;

            //取引余力エラー情報をセット
            l_tpResult.setTpErrorInfo(l_tpErrorInfo);

            //取引余力結果を返却する
            log.exiting(STR_METHOD_NAME);
            return l_tpResult;
        }

        //信用新規建余力停止中の場合
        if (l_calcCond.isMarginOpenPositionStop() == true)
        {
            //取引余力結果オブジェクトを生成
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();

            //判定フラグをセット
            l_tpResult.setResultFlg(false);
            //取引可能額をセット
            l_tpResult.setTradingPower(0);

            /*
             * 取引余力エラー情報を生成
             */
            WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
            l_tpErrorInfo.tradinPowerErrorDiv =
                WEB3TPTradingPowerErrorDivDef.MARGIN_OPEN_POSITION_STOP_ERROR;
            l_tpErrorInfo.lackAccountBalance = 0;
            l_tpErrorInfo.buyOrderPossibleAmount = 0;
            l_tpErrorInfo.sellOrderPossibleQuantity = 0;
            l_tpErrorInfo.marginSecInfo = null;

            //取引余力エラー情報をセット
            l_tpResult.setTpErrorInfo(l_tpErrorInfo);

            //取引余力結果を返却する
            log.exiting(STR_METHOD_NAME);
            return l_tpResult;
        }

        //現物顧客の場合
        if (l_blnMargin == false)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //get会社部店別余力計算条件(String)
        //部店用プリファ@レンステーブルより、引数.プリファ@レンス名に対応した
        //プリファ@レンスの値を取得する。
        String  l_strSecondDepositMarginopenTpstop =
            l_calcCond.getInstBranCalcCondition(WEB3BranchPreferencesNameDef.SECOND_DEPOSIT_MARGINOPEN_TPSTOP);

        //(*)分岐フロー
        //get会社部店別余力計算条件()の戻り値が 1：実施する場合、
        //以下の処理を実施する。
        if (WEB3SecondDepositMarginOpenTpStopDef.EXECUTE.equals(
                l_strSecondDepositMarginopenTpstop))
        {
            //create入金請求管理(顧客)
            //入金請求管理を生成する。
            //[引数]
            //顧客：getMainAccount()の戻り値
            WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
                WEB3TPPaymentRequisitionManagement.createPaymentRequisitionManagement(l_mainAccount);

            //is第二水準追証発生( )
            //第二水準追証が発生しているか判定する。
            boolean l_blnIsSecondAdddeposit = l_paymentRequisitionManagement.isSecondAdddeposit();

            if (l_blnIsSecondAdddeposit)
            {
                //取引余力結果オブジェクトを生成
                WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
                //判定フラグをセット
                l_tpResult.setResultFlg(false);
                //取引可能額をセット
                l_tpResult.setTradingPower(0);

                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
                l_tpErrorInfo.tradinPowerErrorDiv =
                    WEB3TPTradingPowerErrorDivDef.MARGIN_OPEN_POSITION_STOP_ERROR;
                l_tpErrorInfo.lackAccountBalance = 0;
                l_tpErrorInfo.buyOrderPossibleAmount = 0;
                l_tpErrorInfo.sellOrderPossibleQuantity = 0;
                l_tpErrorInfo.marginSecInfo = null;

                //取引余力エラー情報をセット
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);

                //取引余力結果を返却する
                log.exiting(STR_METHOD_NAME);
                return l_tpResult;
            }
        }
        //余力更新オブジェクトを生成
        WEB3TPTradingPowerUpd l_tpUpd =
            new WEB3TPTradingPowerUpd(l_lngAccountId, l_blnMargin, l_calcCond, l_newOrderSpecs);
        
        /*
         * (注文後)余力計算結果(List)を取得
         */
        List l_updResults =
            l_tpUpd.calcTradingpowerUpdResultMargin(WEB3TPMarkToMarketDivDef.NORMAL);
        
        //(注文後)資産余力情報を生成
        WEB3TPTradingPowerCalcMargin l_calcMargin =
            new WEB3TPTradingPowerCalcMargin(l_updResults, l_calcCond);
        //(注文後)余力計算結果を取得
        WEB3TPCalcResult l_calcResult = l_calcMargin.calcAppliedMarginTradingPower();
        
        /*
         * 注文銘柄の保証金率/現金保証金率を取得する。
         */
        //現注文内容より注文種別を取得
        OrderTypeEnum l_orderType = l_newOrderSpecs[0].getOrderType();
        //現注文内容より注文銘柄IDを取得
        long l_lngProductId = l_newOrderSpecs[0].getProductId();
        //現注文内容より市場IDを取得
        long l_lngMarketId = l_newOrderSpecs[0].getMarketId();
        //現注文内容より発注日を取得
        Date l_datBizDate = l_newOrderSpecs[0].getOrderBizDate();

        //(注文銘柄)保証金率
        int l_intOrderMarginDepRate = 0;
        //(注文銘柄)現金保証金率
        int l_intOrderCashMarginDepRate = 0;

        try
        {
            //株式取引銘柄Rowを取得。
            EqtypeTradedProductRow l_row = WEB3TPTradingPowerServiceImpl.getEqtypeTradedProductRow(
                l_lngProductId,
                l_lngMarketId);

            //新規買建の場合
            if(OrderTypeEnum.MARGIN_LONG.equals(l_orderType) == true)
            {
                l_intOrderMarginDepRate = (int) l_row.getLongMarginDepositRate();
                l_intOrderCashMarginDepRate = (int) l_row.getLongCashMarginDepositRate();
            }
            //新規売建の場合
            else if(OrderTypeEnum.MARGIN_SHORT.equals(l_orderType) == true)
            {
                l_intOrderMarginDepRate = (int) l_row.getShortMarginDepositRate();
                l_intOrderCashMarginDepRate = (int) l_row.getShortCashMarginDepositRate();
            }
        }
        catch(NotFoundException de)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        /*
         * 預り金不足額を計算する。
         */
        //預り金不足額
        double l_dblLackAmt = 0;

        //(部店)保証金率
        int l_intMarginDepRate = l_calcCond.getMarginDepositRate();
        //(部店)現金保証金率
        int l_intCashMarginDepRate = l_calcCond.getCashMarginDepositRate();

        //増担保規制銘柄の場合
        //[(部店)現金保証金率 < (注文銘柄)現金保証金率]
        if(l_intCashMarginDepRate < l_intOrderCashMarginDepRate)
        {
            /*
             * [計算式]
             * 　@預り金不足額　@=　@MIN(預り金請求余力(T+信用新規建基準日),・・・,預り金請求余力(T+5))
             * 
             * ※計算項目の取得方法@
             * 　@・引出可能現(T+n) = 
             * 　@　@　@(注文後)資産余力情報<信用顧客>.calc預り金請求余力(T+n)
             * 　@・信用新規建基準日 = 余力計算条件.get余力計算基準日<信用新規建>()
             */
            //LOOP処理(受渡日～T+5の間)
            for(int index = l_calcCond.getMarginBasePoint(); index <= WEB3TPSpecifiedPointDef.T_5; index++)
            {
                //預り金請求余力(T+n)
                double l_dblActPayBal = l_calcMargin.calcAccountBalanceDemandPower(index);

                l_dblLackAmt = Math.min(l_dblActPayBal, l_dblLackAmt);
            }
        }
        
        /*
         * 注文銘柄の二階建チェックを実施
         */
        //二階建銘柄情報[]
        WEB3TPMarginSecurityInfo[] l_marginSecs = null;
        
        //注文種別 == 信用新規買建の場合
        if(OrderTypeEnum.MARGIN_LONG.equals(l_orderType) == true)
        {
            //二階建銘柄情報[]を取得
            l_marginSecs = this.calcMarginSecurity(l_subAccount, l_lngProductId, l_calcMargin,
                    l_tpUpd, l_orderType, l_lngMarketId, l_datBizDate);
        }

        //is受入保証金占有率超過(long, 余力更新, 資産余力情報<信用顧客>)
        boolean l_blnIsReceiptDepositRateOver = this.isReceiptDepositRateOver(l_lngProductId, l_tpUpd, l_calcMargin);

        /*
         * 取引余力結果オブジェクトを生成
         */        
        WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
        
        //取引余力チェックOKの場合
        //(0 <= (注文後)新規建可能額 かつ 0<= 預り金不足額 かつ 二階建銘柄情報[] == null
        //かつ is受入保証金占有率超過()の戻り値 == false)
        if (l_calcResult.tradingPower >= 0 && l_dblLackAmt >= 0 && l_marginSecs == null
            && !l_blnIsReceiptDepositRateOver)
        {
            //trueをセット
            l_tpResult.setResultFlg(true);
            //(今回注文後)取引可能額をセット
            l_tpResult.setTradingPower(l_calcResult.tradingPower);
            //取引余力エラー情報をセット
            l_tpResult.setTpErrorInfo(null);
            
            /*
             * 増担保規制銘柄の場合
             */
            if(l_intMarginDepRate < l_intOrderMarginDepRate
                || l_intCashMarginDepRate < l_intOrderCashMarginDepRate)
            {
                //注意文言表示区分をセット
                l_tpResult.setAttentionObjectionType(WEB3TPResultAttentionObjectionTypeDef.INC_DEPOSIT_REG_ATTENTION);
            }
            
            /*
             * 余力更新を実施
             */
            //余力更新フラグ==trueの場合
            if (l_blnUpdateFlg == true)
            {
                //余力更新を実施
                l_tpUpd.saveTradingpowerUpdResultMargin(l_updResults);
            }
        }
        //以外（取引余力チェックNG）の場合
        else
        {
            //falseをセット
            l_tpResult.setResultFlg(false);
            
            /*
             * 取引余力エラー情報を生成
             */
            WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
            
            //増担保規制銘柄の場合
            if(l_intMarginDepRate < l_intOrderMarginDepRate
                || l_intCashMarginDepRate < l_intOrderCashMarginDepRate)
            {
                /*
                 * 増担保規制銘柄新規建可能額を取得
                 */
                //(注文前)余力計算結果(List)を取得
                List l_updResultsBeFore = WEB3TPTradingPowerCalcMargin.findCalcResultMarginParams(l_lngAccountId);

                //(注文前)資産余力情報を生成
                WEB3TPTradingPowerCalcMargin l_calcMarginBefore = new WEB3TPTradingPowerCalcMargin(
                    l_updResultsBeFore,
                    l_calcCond);

                //(注文前)余力計算結果を取得
                WEB3TPCalcResult l_calcResultBefore = l_calcMarginBefore.calcAppliedMarginTradingPowerIncDeposit(
                    Math.max(l_intMarginDepRate, l_intOrderMarginDepRate),
                    Math.max(l_intCashMarginDepRate, l_intOrderCashMarginDepRate));

                //(増担保)預り金不足エラーの場合
                //(預り金不足額 < 0)
                if(l_dblLackAmt < 0)
                {
                    l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.INC_DEPOSIT_LACK_ACCOUNT_BALANCE;
                    l_tpErrorInfo.lackAccountBalance = Math.abs(l_dblLackAmt);
                    l_tpErrorInfo.marginTradingPowerIncDeposit = Math.max(
                        l_calcResultBefore.tradingPower,
                        0);
                    l_tpErrorInfo.marginSecInfo = l_marginSecs;
                }
                //(増担保)保証金不足エラーの場合
                //(注文後)新規建可能額 < 0)
                else if(l_calcResult.tradingPower < 0)
                {
                    l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.INC_DEPOSIT_LACK_MARGIN_POWER;
                    l_tpErrorInfo.marginTradingPowerIncDeposit = Math.max(
                        l_calcResultBefore.tradingPower,
                        0);
                    l_tpErrorInfo.marginSecInfo = l_marginSecs;
                }
                //二階建エラーの場合
                else if (l_marginSecs != null)
                {
                    l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.MARGIN_SEC_ERROR;
                    l_tpErrorInfo.marginSecInfo = l_marginSecs;
                }
                //受入保証金占有率超過エラーの場合
                else if (l_blnIsReceiptDepositRateOver)
                {
                    l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.RECEIPT_DEPOSIT_RATE_OVER_ERROR;
                }
            }
            //以外（通常銘柄）の場合
            else
            {
                //保証金不足エラーの場合
                //(注文後)新規建可能額 < 0)
                if(l_calcResult.tradingPower < 0)
                {
                    l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.LACK_MARGIN_POWER;
                    l_tpErrorInfo.marginSecInfo = l_marginSecs;
                }
                //二階建エラーの場合
                else if (l_marginSecs != null)
                {
                    l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.MARGIN_SEC_ERROR;
                    l_tpErrorInfo.marginSecInfo = l_marginSecs;
                }
                //受入保証金占有率超過エラーの場合
                else if (l_blnIsReceiptDepositRateOver)
                {
                    l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.RECEIPT_DEPOSIT_RATE_OVER_ERROR;
                }
            }
            
            //取引余力エラー情報をセット
            l_tpResult.setTpErrorInfo(l_tpErrorInfo);
        }
        
        //取引余力結果を返却する
        log.exiting(STR_METHOD_NAME);
        return l_tpResult;
    }

    /**
     * (validate取引余力<オプション新規買建>)<BR>
     * オプション新規買建において、取引余力チェックを実施する。<BR>
     * <BR>
     * シーケンス図「(取引余力サービス)validate取引余力<オプション新規買建>～先物証拠金～」参照<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_newOrderSpec - (先物OP現注文内容)
     * @@return webbroker3.tradingpower.WEB3TPTradingPowerResult
     * @@roseuid 416FA7EE0285
     */
    protected WEB3TPTradingPowerResult validateTradingPowerOptionBuy(
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoNewOrderSpec l_newOrderSpec)
    {
        final String STR_METHOD_NAME =
            "validateTradingPowerOptionBuy(WEB3GentradeSubAccount, WEB3IfoNewOrderSpec, boolean)";
        log.entering(STR_METHOD_NAME);

        //証拠金計算サービスを取得
        WEB3IfoDepositCalcService l_service =
            (WEB3IfoDepositCalcService)Services.getService(WEB3IfoDepositCalcService.class);

        try
        {
            //取引余力結果を生成        
            WEB3TPTradingPowerResult l_tradingPowerResult = new WEB3TPTradingPowerResult();

            //証拠金計算オブジェクトを取得
            WEB3IfoDepositCalc l_ifoDepCalc = l_service.getIfoDepositCalc(l_subAccount, l_newOrderSpec);
            
            //新規建余力可能フラグ
            boolean l_newOpenFlg = l_ifoDepCalc.getIfoDepositCalcCondition().isNewOpenTradingPowerAvailable();
            //必要最低保証金
            double l_dblMinIfoDep = l_ifoDepCalc.getIfoDepositCalcCondition().getMinIfoDeposit();
            //受入証拠金残高
            double l_dblRecIfoDepBal = l_ifoDepCalc.calcReceiptIfoDepositBalance();
            //未入金額
            double l_dblNonPayAmt = l_ifoDepCalc.calcNonPayAmount();

            //新規建余力不可の場合
            if (l_newOpenFlg == false)
            {
                //判定フラグにfalseを代入
                l_tradingPowerResult.setResultFlg(false);
                //取引余力結果を返却する
                log.exiting(STR_METHOD_NAME);
                return l_tradingPowerResult;
            }
            //新規建余力可能で受入証拠金残高＜必要最低保証金の場合
            if (l_dblRecIfoDepBal < l_dblMinIfoDep)
            {
                //判定フラグにfalseを代入
                l_tradingPowerResult.setResultFlg(false);
                //取引余力結果を返却する
                log.exiting(STR_METHOD_NAME);
                return l_tradingPowerResult;
            }
            //新規建余力可能で未入金額＞0の場合
            if (l_dblNonPayAmt > 0)
            {
                //判定フラグにfalseを代入
                l_tradingPowerResult.setResultFlg(false);
                //取引余力結果を返却する
                log.exiting(STR_METHOD_NAME);
                return l_tradingPowerResult;
            }

            //オプション新規建可能額
            double l_dblTradingPower = l_ifoDepCalc.calcIfoDepositTradingPowerAmount();

            //証拠金計算.calc証拠金余力額() >= 0の場合
            if (l_dblTradingPower >= 0)
            {
                //判定フラグにtrueを代入
                l_tradingPowerResult.setResultFlg(true);
                //取引余力結果を返却する
                log.exiting(STR_METHOD_NAME);
                return l_tradingPowerResult;
            }
            //以外の時
            else
            {
                //判定フラグにfalseを代入
                l_tradingPowerResult.setResultFlg(false);
                //取引余力結果を返却する
                log.exiting(STR_METHOD_NAME);
                return l_tradingPowerResult;
            }
        }
        catch (WEB3SystemLayerException e)
        {
            throw new WEB3BaseRuntimeException(
                e.getErrorInfo(),
                e.getErrorMethod(),
                e.getErrorMessage(),
                e.getException());
        }
    }

    /**
     * (validate取引余力<先物オプション新規建>)<BR>
     * <BR>
     * シーケンス図「(取引余力サービス)validate取引余力<先物オプション新規建>」参照<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_newOrderSpec - (先物OP現注文内容)
     * @@return webbroker3.tradingpower.WEB3TPTradingPowerResult
     * @@roseuid 416FA7EE0295
     */
    protected WEB3TPTradingPowerResult validateTradingPowerFuturesOption(
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoNewOrderSpec l_newOrderSpec)
    {
        final String STR_METHOD_NAME =
            "validateTradingPowerFuturesOption(WEB3GentradeSubAccount, WEB3IfoNewOrderSpec)";
        log.entering(STR_METHOD_NAME);

        //取引余力結果を生成        
        WEB3TPTradingPowerResult l_tradingPowerResult = new WEB3TPTradingPowerResult();

        //証拠金計算サービスを取得
        WEB3IfoDepositCalcService l_service =
            (WEB3IfoDepositCalcService)Services.getService(WEB3IfoDepositCalcService.class);

        //証拠金計算オブジェクトを取得
        WEB3IfoDepositCalc l_ifoDepCalc = null;
        try
        {
            l_ifoDepCalc = l_service.getIfoDepositCalc(l_subAccount, l_newOrderSpec);
            //証拠金計算条件を取得
            WEB3IfoDepositCalcCondition l_ifoCalcCond =
                l_service.createIfoDepositCalcCondition(l_subAccount);
        }
        catch (WEB3SystemLayerException e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3BaseRuntimeException(
                e.getErrorInfo(),
                e.getErrorMethod(),
                e.getErrorMessage(),
                e.getException());
        }

        //証拠金計算条件を取得
        WEB3IfoDepositCalcCondition l_ifoCalcCond = l_ifoDepCalc.getIfoDepositCalcCondition();

        //SPAN採用実施会社の場合
        if (l_ifoCalcCond.isSPANUsable() == true)
        {
            //新規建余力可能の場合
            if (l_ifoCalcCond.isNewOpenTradingPowerAvailable() == true)
            {
                //受入証拠金残高
                double l_dblRecIfoDepBal = l_ifoDepCalc.calcReceiptIfoDepositBalance();
                //証拠金所要額
                double l_dblIfoDepReqAmt = l_ifoDepCalc.calcIfoDepositRequiredAmount();
                //必要最低証拠金
                double l_dblMinIfoDep = l_ifoCalcCond.getMinIfoDeposit();

                //受入証拠金残高＜必要最低証拠金の場合
                if (l_dblRecIfoDepBal < l_dblMinIfoDep)
                {
                    //判定フラグにfalseを代入
                    l_tradingPowerResult.setResultFlg(false);
                    //取引余力結果を返却する
                    log.exiting(STR_METHOD_NAME);
                    return l_tradingPowerResult;
                }
                //受入証拠金残高＜証拠金所要額の場合
                else if (l_dblRecIfoDepBal < l_dblIfoDepReqAmt)
                {
                    //判定フラグにfalseを代入
                    l_tradingPowerResult.setResultFlg(false);
                    //取引余力結果を返却する
                    log.exiting(STR_METHOD_NAME);
                    return l_tradingPowerResult;
                }
                //以外の場合
                else
                {
                    //判定フラグにtrueを代入
                    l_tradingPowerResult.setResultFlg(true);
                    //取引余力結果を返却する
                    log.exiting(STR_METHOD_NAME);
                    return l_tradingPowerResult;
                }
            }
            //新規建余力不可能の場合
            else
            {
                //判定フラグにfalseを代入
                l_tradingPowerResult.setResultFlg(false);
                //取引余力結果を返却する
                log.exiting(STR_METHOD_NAME);
                return l_tradingPowerResult;
            }
        }
        //SPAN非採用会社の場合
        else
        {
            //新規建余力額
            boolean l_newOpenFlg = l_ifoCalcCond.isNewOpenTradingPowerAvailable();
            //必要最低保証金
            double l_dblMinIfoDep = l_ifoCalcCond.getMinIfoDeposit();
            //受入証拠金残高
            double l_dblRecIfoDepBal = l_ifoDepCalc.calcReceiptIfoDepositBalance();
            //未入金額
            double l_dblNonPayAmt = l_ifoDepCalc.calcNonPayAmount();

            //新規建余力不可の場合
            if (l_newOpenFlg == false)
            {
                //判定フラグにfalseを代入
                l_tradingPowerResult.setResultFlg(false);
                //取引余力結果を返却する
                log.exiting(STR_METHOD_NAME);
                return l_tradingPowerResult;
            }
            //新規建余力可能で受入証拠金残高＜必要最低保証金の場合
            if (l_dblRecIfoDepBal < l_dblMinIfoDep)
            {
                //判定フラグにfalseを代入
                l_tradingPowerResult.setResultFlg(false);
                //取引余力結果を返却する
                log.exiting(STR_METHOD_NAME);
                return l_tradingPowerResult;
            }
            //新規建余力可能で未入金額＞0の場合
            if (l_dblNonPayAmt > 0)
            {
                //判定フラグにfalseを代入
                l_tradingPowerResult.setResultFlg(false);
                //取引余力結果を返却する
                log.exiting(STR_METHOD_NAME);
                return l_tradingPowerResult;
            }

            //先物OP商品区分
            IfoDerivativeTypeEnum l_deriEnum = l_newOrderSpec.ifoDerivativeType;
            //建区分
            ContractTypeEnum l_contEnum = l_newOrderSpec.contractType;

            //買ポジションの場合
            if ((l_deriEnum.intValue() == IfoDerivativeTypeEnum.FUTURES.intValue()
                && l_contEnum.intValue() == ContractTypeEnum.LONG.intValue())
                || l_deriEnum.intValue() == IfoDerivativeTypeEnum.PUT_OPTIONS.intValue())
            {
                //買ポジション可能数量
                double l_dblPossBuyQty =
                    l_ifoDepCalc.calcPossibleBuyQty(l_newOrderSpec.underlyingProductCode);

                //新規建可能数量 >= 0の場合
                if (l_dblPossBuyQty >= 0)
                {
                    //判定フラグにtrueを代入
                    l_tradingPowerResult.setResultFlg(true);
                    //取引余力結果を返却する
                    log.exiting(STR_METHOD_NAME);
                    return l_tradingPowerResult;
                }
                //以外の場合
                else
                {
                    //判定フラグにfalseを代入
                    l_tradingPowerResult.setResultFlg(false);
                    //取引余力結果を返却する
                    log.exiting(STR_METHOD_NAME);
                    return l_tradingPowerResult;
                }
            }
            //売ポジションの場合
            else
            {
                //売ポジション可能数量
                double l_dblPossSellQty =
                    l_ifoDepCalc.calcPossibleSellQty(l_newOrderSpec.underlyingProductCode);

                //新規建可能数量 >= 0の場合
                if (l_dblPossSellQty >= 0)
                {
                    //判定フラグにtrueを代入
                    l_tradingPowerResult.setResultFlg(true);
                    //取引余力結果を返却する
                    log.exiting(STR_METHOD_NAME);
                    return l_tradingPowerResult;
                }
                //以外の場合
                else
                {
                    //判定フラグにfalseを代入
                    l_tradingPowerResult.setResultFlg(false);
                    //取引余力結果を返却する
                    log.exiting(STR_METHOD_NAME);
                    return l_tradingPowerResult;
                }
            }
        }
    }

    /**
     * (validate取引余力<余力再計算>)<BR>
     * 現注文内容を含んだ余力再計算処理を実施する。<BR>
     * <BR>
     * シーケンス図「(取引余力サービス)validate取引余力<余力再計算>」参照<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_newOrderSpecs - (現注文内容)
     * @@param l_orderTypeEnum - (注文種別)
     * @@param l_blnUpdateFlg - (余力更新フラグ)
     * trueの時、余力再計算処理を実施する
     * falseの時、余力再計算処理を実施しない
     * @@return webbroker3.tradingpower.WEB3TPCalcResult
     */
    protected WEB3TPTradingPowerResult validateTradingPowerReCalc(
        WEB3GentradeSubAccount l_subAccount,
        WEB3TPNewOrderSpec[] l_newOrderSpecs,
        OrderTypeEnum l_orderTypeEnum,
        boolean l_blnUpdateFlg)
    {
        final String STR_METHOD_NAME =
            "validateTradingPowerReCalc(WEB3GentradeSubAccount, WEB3TPNewOrderSpec, boolean)";
        log.entering(STR_METHOD_NAME);

        //口座IDを取得
        long l_lngAccountId = l_subAccount.getAccountId();

        //顧客オブジェクトを取得
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //信用口座開設区分を取得
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //余力計算条件
        WEB3TPCalcCondition l_calcCond =
            WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

        /*
         * 取引余力結果オブジェクトを生成
         */
        WEB3TPTradingPowerResult l_tradingPowerResult = new WEB3TPTradingPowerResult();

        //現物顧客の場合
        if (l_blnMargin == false)
        {
            /*
             * 取引余力結果オブジェクトに値をセット
             */
            //判定フラグ
            l_tradingPowerResult.setResultFlg(true);
            //取引可能額
            l_tradingPowerResult.setTradingPower(0);
            //取引余力エラー情報
            l_tradingPowerResult.setTpErrorInfo(null);

            //余力更新フラグ==trueの場合
            if(l_blnUpdateFlg == true)
            {
                /*
                 * 余力更新オブジェクトを生成
                 */
                WEB3TPTradingPowerUpd l_tpUpd = new WEB3TPTradingPowerUpd(
                        l_lngAccountId, l_blnMargin, l_calcCond,
                        l_newOrderSpecs);

                /*
                 * 余力計算結果(List)を取得
                 */
                List l_updResults = l_tpUpd.calcTradingpowerUpdResultEquity();

                //余力更新を実施
                l_tpUpd.saveTradingpowerUpdResultEquity(l_updResults);
            }
        }
        //信用顧客の場合
        else
        {
            /*
             * 余力更新オブジェクトを生成
             */
            WEB3TPTradingPowerUpd l_tpUpd = new WEB3TPTradingPowerUpd(
                    l_lngAccountId, l_blnMargin, l_calcCond,
                    l_newOrderSpecs);

            /*
             * 余力計算結果(List)を取得
             */
            List l_updResults =
                l_tpUpd.calcTradingpowerUpdResultMargin(WEB3TPMarkToMarketDivDef.NORMAL);

            //資産余力情報を生成
            WEB3TPTradingPowerCalcMargin l_calcMargin =
                new WEB3TPTradingPowerCalcMargin(l_updResults, l_calcCond);

            /*
             * 注文銘柄の二階建チェックを実施
             */
            //現注文内容より注文種別を取得
            OrderTypeEnum l_orderType = l_newOrderSpecs[0].getOrderType();
            //現注文内容より注文銘柄IDを取得
            long l_lngProductId = l_newOrderSpecs[0].getProductId();

            //二階建チェックエラー銘柄情報の配列
            WEB3TPMarginSecurityInfo[] l_marginSecs = null;

            //注文種別 == (保護⇒代用)証券振替の場合
            if(OrderTypeEnum.FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES
                .equals(l_orderType) == true)
            {
                //二階建チェックエラー銘柄情報の配列を取得
                l_marginSecs = this.calcMarginSecurity(l_subAccount, l_lngProductId, l_calcMargin,
                        l_tpUpd, l_orderType, -1, null);
            }

            //二階建チェックOK場合
            if(l_marginSecs == null)
            {
                /*
                 * 取引余力結果オブジェクトに値をセット
                 */
                //判定フラグ
                l_tradingPowerResult.setResultFlg(true);
                //取引可能額
                l_tradingPowerResult.setTradingPower(0);
                //取引余力エラー情報
                l_tradingPowerResult.setTpErrorInfo(null);

                /*
                 * 余力更新を実施
                 */
                //余力更新フラグ==trueの場合
                if (l_blnUpdateFlg == true)
                {
                    //余力更新を実施
                    l_tpUpd.saveTradingpowerUpdResultMargin(l_updResults);
                }
            }
            //二階建チェックNG場合
            else
            {
                /*
                 * 取引余力結果オブジェクトに値をセット
                 */
                //判定フラグ
                l_tradingPowerResult.setResultFlg(false);
                //取引可能額
                l_tradingPowerResult.setTradingPower(0);

                /*
                 * 取引余力エラー情報を生成
                 */
                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
                
                l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.MARGIN_SEC_ERROR;
                l_tpErrorInfo.lackAccountBalance = 0;
                l_tpErrorInfo.buyOrderPossibleAmount = 0;
                l_tpErrorInfo.sellOrderPossibleQuantity = 0;
                l_tpErrorInfo.marginSecInfo = l_marginSecs;

                //取引余力エラー情報
                l_tradingPowerResult.setTpErrorInfo(l_tpErrorInfo);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_tradingPowerResult;
    }

    /**
     * (validate取引余力<その他商品>)<BR>
     * <BR>
     * その他商品(現物株式、信用取引以外)において、 <BR>
     * 現注文内容を取り込み預り金チェックを実施する。 <BR>
     * <BR>
     * シーケンス図「(取引余力サービス)validate取引余力<その他商品>」参照 <BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_newOrderSpecs - (現注文内容)
     * @@param l_orderTypeEnum - (注文種別)
     * @@param l_blnUpdateFlg - (余力更新フラグ)<BR>
     * trueの時、余力再計算処理を実施する<BR>
     * falseの時、余力再計算処理を実施しない<BR>
     * @@return WEB3TPCalcResult
     * @@throws WEB3BaseException
     */
    protected WEB3TPTradingPowerResult validateTradingPowerOther(
        WEB3GentradeSubAccount l_subAccount,
        WEB3TPNewOrderSpec[] l_newOrderSpecs,
        OrderTypeEnum l_orderTypeEnum,
        boolean l_blnUpdateFlg) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateTradingPowerOther(WEB3GentradeSubAccount, WEB3TPNewOrderSpec, boolean)";
        log.entering(STR_METHOD_NAME);

        //余力計算条件
        WEB3TPCalcCondition l_calcCond =
            WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

        //引数.注文種別 ≠ 累投解約 かつ　@引数.注文種別 ≠ 債券売付　@の場合
        if(OrderTypeEnum.RUITO_SELL.equals(l_orderTypeEnum) == false
                && OrderTypeEnum.BOND_SELL.equals(l_orderTypeEnum) == false)
        {
            /*
             * 取引停止区分チェック
             */
            //取引停止中の場合
            if (l_calcCond.isTradingStop() == true)
            {
                //取引余力結果オブジェクトを生成
                WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();

                //判定フラグをセット
                l_tpResult.setResultFlg(false);
                //取引可能額をセット
                l_tpResult.setTradingPower(0);

                /*
                 * 取引余力エラー情報を生成
                 */
                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
                l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.TRADING_STOP_ERROR;
                l_tpErrorInfo.lackAccountBalance = 0;
                l_tpErrorInfo.buyOrderPossibleAmount = 0;
                l_tpErrorInfo.sellOrderPossibleQuantity = 0;
                l_tpErrorInfo.marginSecInfo = null;

                //取引余力エラー情報をセット
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);

                //取引余力結果を返却する
                log.exiting(STR_METHOD_NAME);
                return l_tpResult;
            }

            //その他商品買付余力停止区分 == true かつ 引数.注文種別 not in (出金、証拠金振替、為替保証金振替、中国株式振替、
            //ｵﾘｯｸｽｸﾚｼﾞｯﾄ、CFD口座への振替)の場合
            if (l_calcCond.isOtherTradingStop() == true
                && !(OrderTypeEnum.CASH_OUT.equals(l_orderTypeEnum)
                        ||OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(l_orderTypeEnum)
                        ||OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum)
                        ||OrderTypeEnum.TRANSFER_TO_FEQ.equals(l_orderTypeEnum)
                        ||OrderTypeEnum.TO_ORIX_CREDIT.equals(l_orderTypeEnum)
                        ||OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum)))
            {
                //取引余力結果オブジェクトを生成
                WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();

                //判定フラグをセット
                l_tpResult.setResultFlg(false);
                //取引可能額をセット
                l_tpResult.setTradingPower(0);

                /*
                 * 取引余力エラー情報を生成
                 */
                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
                l_tpErrorInfo.tradinPowerErrorDiv =
                    WEB3TPTradingPowerErrorDivDef.OTHER_TRADING_STOP_ERROR;
                l_tpErrorInfo.lackAccountBalance = 0;
                l_tpErrorInfo.buyOrderPossibleAmount = 0;
                l_tpErrorInfo.sellOrderPossibleQuantity = 0;
                l_tpErrorInfo.marginSecInfo = null;

                //取引余力エラー情報をセット
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);

                //取引余力結果を返却する
                log.exiting(STR_METHOD_NAME);
                return l_tpResult;
            }
            
            //1.5.3.(*3)分岐フロー
            //出金注文、証拠金への振替、為替保証金への振替、中国株式への振替　@ｵﾘｯｸｽｸﾚｼﾞｯﾄへの振替、
            //CFD口座への振替 かつ 預り金担保出金余力停止中の場合
            //（
            //　@引数.注文種別 IN(
            //　@　@1001：出金注文,
            //　@　@1007：振替注文(預り金から株先証拠金),
            //　@　@1011：為替保証金振替注文（預り金から為替保証金）,
            //　@　@1013：外国株式振替注文（預り金から外国株式口座）
            //    1020：振替注文（預り金からオリックスクレジット）
            //    1021：CFD振替注文（預り金からCFD口座）
            //　@&& 
            //　@余力計算条件.is預り金担保出金停止区分==true
            //）
            if (l_calcCond.isCashDepositStopDiv() == true                
                && (OrderTypeEnum.CASH_OUT.equals(l_orderTypeEnum)
                ||OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(l_orderTypeEnum)
                ||OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum)
                ||OrderTypeEnum.TRANSFER_TO_FEQ.equals(l_orderTypeEnum)
                ||OrderTypeEnum.TO_ORIX_CREDIT.equals(l_orderTypeEnum)
                ||OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum)))
            {
                //取引余力結果オブジェクトを生成
                WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
                
                //[取引余力結果の設定値]
                //判定フラグ：false
                l_tpResult.setResultFlg(false);

                //取引可能額：0
                l_tpResult.setTradingPower(0);
                
                // 取引余力エラー情報を生成
                 
                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
                
                //取引エラー情報：（注）参照
                //（注）[取引余力エラー情報の設定値]
                //・取引余力エラー区分：取引余力エラー区分Def.預り金担保出金余力停止エラー
                l_tpErrorInfo.tradinPowerErrorDiv = 
                    WEB3TPTradingPowerErrorDivDef.CASH_DEPOSIT_PAYMENT_STOP_ERROR;
               
                //・預り金不足額：0
                l_tpErrorInfo.lackAccountBalance = 0;   
                
                //・差金決済買付可能額：0
                l_tpErrorInfo.buyOrderPossibleAmount = 0;
                
                //・差金決済売付可能数量：0
                l_tpErrorInfo.sellOrderPossibleQuantity = 0;
                
                //・二階建銘柄情報一覧[]：null
                l_tpErrorInfo.marginSecInfo = null;
            
                //取引余力エラー情報をセット
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);

                //1.5.3.1.(*)取引余力結果を返却する。 
                log.exiting(STR_METHOD_NAME);
                return l_tpResult;
            }

            //出金余力停止中 かつ 引数.注文種別in (出金、証拠金振替、為替保証金振替、中国株式振替、
            //ｵﾘｯｸｽｸﾚｼﾞｯ、CFD口座への振替ﾄ)の場合
            if (l_calcCond.isPaymentStop() == true
                && (OrderTypeEnum.CASH_OUT.equals(l_orderTypeEnum)
                ||OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(l_orderTypeEnum)
                ||OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum)
                ||OrderTypeEnum.TRANSFER_TO_FEQ.equals(l_orderTypeEnum)
                ||OrderTypeEnum.TO_ORIX_CREDIT.equals(l_orderTypeEnum)
                ||OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum)))
            {
                //取引余力結果オブジェクトを生成
                WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();

                //判定フラグをセット
                l_tpResult.setResultFlg(false);
                //取引可能額をセット
                l_tpResult.setTradingPower(0);

                /*
                 * 取引余力エラー情報を生成
                 */
                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
                l_tpErrorInfo.tradinPowerErrorDiv =
                    WEB3TPTradingPowerErrorDivDef.PAYMENT_STOP_ERROR;
                l_tpErrorInfo.lackAccountBalance = 0;
                l_tpErrorInfo.buyOrderPossibleAmount = 0;
                l_tpErrorInfo.sellOrderPossibleQuantity = 0;
                l_tpErrorInfo.marginSecInfo = null;

                //取引余力エラー情報をセット
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);

                //取引余力結果を返却する
                log.exiting(STR_METHOD_NAME);
                return l_tpResult;
            }

            //(*5)分岐フロー
            //証券担保ローン口座開設済顧客（出金拘束金レコード有） かつ
            //出金注文、証拠金への振替、為替保証金への振替、中国株式への振替、
            //CFD口座への振替 かつ 証券担保ローン金額ロック中の場合
            //
            //（
            //  余力計算条件.isオリックス_担保ローン口座開設区分==true
            //  &&
            //　@引数.注文種別 IN(
            //　@　@1001：出金注文,
            //　@　@1007：振替注文(預り金から株先証拠金),
            //　@　@1011：為替保証金振替注文（預り金から為替保証金）,
            //　@　@1013：外国株式振替注文（預り金から外国株式口座）
            //    1021：CFD振替注文（預り金からCFD口座）
            //　@&&
            //　@余力計算条件.getオリックス_担保ローン金額ロック==1
            //）
            if (l_calcCond.isOrixSecuredLoanAccOpenDiv() == true
            	&& (OrderTypeEnum.CASH_OUT.equals(l_orderTypeEnum)
                || OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(l_orderTypeEnum)
                || OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum)
                || OrderTypeEnum.TRANSFER_TO_FEQ.equals(l_orderTypeEnum)
                || OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum))
                && WEB3TPOrixSecuredLoanLockDef.ORIX_SECURED_LOAN.equals(l_calcCond.getOrixSecuredLoanLockFlag()))
            {
                //取引余力結果オブジェクトを生成
                WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();

                //[取引余力結果の設定値]
                //判定フラグ：false
                l_tpResult.setResultFlg(false);

                //取引可能額：0
                l_tpResult.setTradingPower(0);

                // 取引余力エラー情報を生成

                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();

                //取引エラー情報：（注）参照
                //（注）[取引余力エラー情報の設定値]
                //・取引余力エラー区分：取引余力エラー区分Def.証券担保ローン金額ロックエラー
                l_tpErrorInfo.tradinPowerErrorDiv =
                    WEB3TPTradingPowerErrorDivDef.SECURITY_DEPOSIT_LOAN_LOCK_ERROR;

                //・預り金不足額：0
                l_tpErrorInfo.lackAccountBalance = 0;

                //・差金決済買付可能額：0
                l_tpErrorInfo.buyOrderPossibleAmount = 0;

                //・差金決済売付可能数量：0
                l_tpErrorInfo.sellOrderPossibleQuantity = 0;

                //・二階建銘柄情報一覧[]：null
                l_tpErrorInfo.marginSecInfo = null;

                //取引余力エラー情報をセット
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);

                //取引余力結果を返却する。
                log.exiting(STR_METHOD_NAME);
                return l_tpResult;
            }
        }

        // 取引を取得
        String l_strDealType = l_newOrderSpecs[0].getDealType();
        
        /*
         * 余力計算条件オブジェクトに、現注文内容[0].受渡日に対応する余力計算基準日<その他>をセット
         */
        //受渡日
        Date l_datDeliDate = l_newOrderSpecs[0].getDeliveryDate();
        
        //債券募集取引の場合
        //引数.注文種別 =401：債券買い注文 && get取引() ==  35：募集取引
        if (OrderTypeEnum.BOND_BUY.equals(l_orderTypeEnum) && 
            WEB3DealTypeDef.RECRUIT_TRADING.equals(l_strDealType))
        {
            // "受渡日" = get入金日()
            l_datDeliDate = l_newOrderSpecs[0].getPaymentDate(); 
        }
        
        //受渡日<int>
        int l_intDeliDate = 0;
        
        //投信買付、投信募集、投信乗換の場合
        if(OrderTypeEnum.MF_BUY.equals(l_orderTypeEnum)
           || OrderTypeEnum.MF_RECRUIT.equals(l_orderTypeEnum)
           || OrderTypeEnum.MF_SWITCHING.equals(l_orderTypeEnum))
        {
            //投信募集の場合、受渡日ではなく入金日を採用
            if(OrderTypeEnum.MF_RECRUIT.equals(l_orderTypeEnum))
            {
                //受渡日＝入金日にする
                l_datDeliDate = l_newOrderSpecs[0].getPaymentDate();                
            }
            
            //投資信託買付可能額適用日の設定値を取得
            String l_strMFBuyApplyDate = l_calcCond.getInstBranCalcCondition(WEB3TPCalcCondition.MFBUY_APPLY_DATE);
            
            //適用日＝発注日以降の場合
            if(WEB3TPMutualFundBuyApplyDateDef.FROM_BIZ_DATE_UNTIL_T5.equals(l_strMFBuyApplyDate))
            {
                //受渡日＝当日にする
                l_datDeliDate = l_calcCond.getBizDate(WEB3TPSpecifiedPointDef.T_0);
            }
        }
        
        //受渡日が当日(T+0)以前だった場合
        if (WEB3DateUtility
            .compareToDay(l_datDeliDate, l_calcCond.getBizDate(WEB3TPSpecifiedPointDef.T_0))
            < 0)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //受渡日がT+5以降だった場合
        else if (
            WEB3DateUtility.compareToDay(
                l_datDeliDate,
                l_calcCond.getBizDate(WEB3TPSpecifiedPointDef.T_5))
                > 0)
        {
            //受渡日<int> = T+5
            l_intDeliDate = WEB3TPSpecifiedPointDef.T_5;
        }
        //以外(受渡日が、T+0～T+5の間)の場合
        else
        {
            //受渡日<int> = 受渡日に対応する指定日を取得
            l_intDeliDate = l_calcCond.calcSpecifiedPoint(l_datDeliDate);
        }

        //口座IDを取得
        long l_lngAccountId = l_subAccount.getAccountId();

        //顧客オブジェクトを取得
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //信用口座開設区分を取得
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //余力更新オブジェクトを生成
        WEB3TPTradingPowerUpd l_tpUpd =
            new WEB3TPTradingPowerUpd(l_lngAccountId, l_blnMargin, l_calcCond, l_newOrderSpecs);

        //営業店取引余力チェック実施区分を、取得する。
        //OrderTypeEnum = 引数.注文種別
        boolean l_blnBrachTradingPowerCheckDiv = l_calcCond.isSalesOfficeTPCheckDiv(l_orderTypeEnum);
        
        //現物顧客の場合
        if (l_blnMargin == false)
        {
            //取引余力結果オブジェクトを生成
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();

            /*
             * 余力計算結果(List)を取得
             */
            List l_updResults = l_tpUpd.calcTradingpowerUpdResultEquity();

            //資産余力情報オブジェクトを生成
            WEB3TPTradingPowerCalcEquity l_calcEquity =
                new WEB3TPTradingPowerCalcEquity(l_updResults, l_calcCond, l_newOrderSpecs[0].getEstimatedPrice());

            //余力計算結果オブジェクトを取得
            WEB3TPCalcResult l_calcResult = l_calcEquity.calcAppliedOtherTradingPower(l_orderTypeEnum, l_intDeliDate);

            //余力計算結果.取引可能額 >= 0 || is営業店取引余力チェック実施区分() == trueの場合
            if (l_calcResult.tradingPower >= 0 || l_blnBrachTradingPowerCheckDiv)
            {
                //trueをセット
                l_tpResult.setResultFlg(true);
                //(今回注文後)取引可能額をセット
                l_tpResult.setTradingPower(l_calcResult.tradingPower);
                //取引余力エラー情報をセット
                l_tpResult.setTpErrorInfo(null);

                //余力更新フラグ==trueの場合
                if (l_blnUpdateFlg == true)
                {
                    //余力更新を実施
                    l_tpUpd.saveTradingpowerUpdResultEquity(l_updResults);
                }
            }
            //余力計算結果.取引可能額 < 0の場合
            else
            {
                //falseをセット
                l_tpResult.setResultFlg(false);
                //(今回注文後)取引可能額(=0)をセット
                l_tpResult.setTradingPower(0);

                /*
                 * 取引余力エラー情報を生成
                 */
                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
                l_tpErrorInfo.tradinPowerErrorDiv =
                    WEB3TPTradingPowerErrorDivDef.LACK_ACCOUNT_BALANCE;
                l_tpErrorInfo.lackAccountBalance = 0;
                l_tpErrorInfo.buyOrderPossibleAmount = 0;
                l_tpErrorInfo.sellOrderPossibleQuantity = 0;
                l_tpErrorInfo.marginSecInfo = null;

                //取引余力エラー情報をセット
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);
            }

            //取引余力結果を返却する
            log.exiting(STR_METHOD_NAME);
            return l_tpResult;
        }
        //信用顧客の場合
        else
        {
            /*
             * 余力計算結果(List)を取得
             */
            List l_updResults =
                l_tpUpd.calcTradingpowerUpdResultMargin(WEB3TPMarkToMarketDivDef.NORMAL);

            //資産余力情報オブジェクトを生成
            WEB3TPTradingPowerCalcMargin l_calcMargin =
                new WEB3TPTradingPowerCalcMargin(l_updResults, l_calcCond);

            //余力計算結果オブジェクトを取得
            WEB3TPCalcResult l_calcResult = l_calcMargin.calcAppliedOtherTradingPower(l_orderTypeEnum, l_intDeliDate);

            /*
             * 注文銘柄の二階建チェックを実施
             */
            //現注文内容より注文種別を取得
            OrderTypeEnum l_orderType = l_newOrderSpecs[0].getOrderType();

            //二階建チェックエラー銘柄情報の配列
            WEB3TPMarginSecurityInfo[] l_marginSecs = null;

            //引数.注文種別 ≠ 累投解約 かつ 引数.注文種別 ≠ 債券売付 の場合
            if(OrderTypeEnum.RUITO_SELL.equals(l_orderTypeEnum) == false
                    && OrderTypeEnum.BOND_SELL.equals(l_orderTypeEnum) == false)
            {
                //二階建チェックエラー銘柄情報の配列を取得
                l_marginSecs = this.calcMarginSecurity(l_subAccount, -1, l_calcMargin, l_tpUpd,
                        l_orderType, -1, null);
            }

            //(*)分岐フロー
            //下記条件を満たす場合、以下の処理を実施
            //①@出金に伴う当日保証金振替実施区分　@=　@１：EXECUTE
            //②引数.注文種別 = 1001:出金
            //③引数.現注文内容.get発注日() = T+0
            //④引数.現注文内容.get受渡日() = T+1
            //⑤引数.現注文内容.get出金申込区分() = null
            //⑥即日入金対象銘柄拘束金( T + 0 ) > 0
            //※出金に伴う当日保証金振替実施区分 =
            //資産余力情報<信用顧客>.get余力計算条件().get会社部店別余力計算条件(
            //     :String = "cashout.today.deposit.transfer.div")
            String l_strInstBranCalcCondition = l_calcMargin.getCalcCondition().getInstBranCalcCondition(
                WEB3BranchPreferencesNameDef.CASHOUT_TODAY_DEPOSIT_TRANSFER_DIV);
            Date l_datBizDate0 = l_calcMargin.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0);
            Date l_datBizDate1 = l_calcMargin.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_1);
            double l_dblTodayDepFundRestraint0 = l_calcMargin.getCalcResultDetailMargin().getTodayDepFundRestraint0();           

            double l_dblTradingPower = 0;
            if (WEB3TPCashoutTodayDepositTransferDivDef.EXECUTE.equals(l_strInstBranCalcCondition)
                && OrderTypeEnum.CASH_OUT.equals(l_orderTypeEnum)
                && WEB3DateUtility.compareToDay(l_newOrderSpecs[0].getOrderBizDate(), l_datBizDate0) == 0
                && WEB3DateUtility.compareToDay(l_newOrderSpecs[0].getDeliveryDate(), l_datBizDate1) == 0
                && l_newOrderSpecs[0].getPaymentApplicationDiv() == null
                && l_dblTodayDepFundRestraint0 > 0)
            {
                //出金可能額を計算する。
                //出金可能額　@=　@保証金引出余力(Ｔ＋０)
                //※保証金引出余力(Ｔ＋０) = calc保証金引出余力(int)の戻り値
                l_dblTradingPower =
                    l_calcMargin.calcMarginDrawPower(WEB3TPSpecifiedPointDef.T_0);
            }

            /*
             * 取引余力結果オブジェクトを生成
             */        
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();

            //二階建チェックエラーが存在しない　@かつ　@ (取引可能額 >=0 || is営業店取引余力チェック実施区分() == true場合)
            //&& 出金可能額 >= 0
            if (l_marginSecs == null && (l_calcResult.tradingPower >= 0 || l_blnBrachTradingPowerCheckDiv)
                && l_dblTradingPower >= 0)
            {
                //trueをセット
                l_tpResult.setResultFlg(true);
                //(今回注文後)取引可能額をセット
                l_tpResult.setTradingPower(l_calcResult.tradingPower);
                //取引余力エラー情報をセット
                l_tpResult.setTpErrorInfo(null);

                /*
                 * 余力更新を実施
                 */
                //余力更新フラグ==trueの場合
                if (l_blnUpdateFlg == true)
                {
                    //余力更新を実施
                    l_tpUpd.saveTradingpowerUpdResultMargin(l_updResults);
                }
            }
            //以外
            else
            {
                //falseをセット
                l_tpResult.setResultFlg(false);
                //(今回注文後)取引可能額をセット
                l_tpResult.setTradingPower(0);

                /*
                 * 取引余力エラー情報を生成
                 */
                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
                
                //預り金が不足している場合
                if(l_calcResult.tradingPower < 0 || l_dblTradingPower < 0)
                {
                    l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.LACK_ACCOUNT_BALANCE;
                }
                //それ以外(二階建エラーの場合)
                else
                {
                    l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.MARGIN_SEC_ERROR;
                }
                l_tpErrorInfo.lackAccountBalance = 0;
                l_tpErrorInfo.buyOrderPossibleAmount = 0;
                l_tpErrorInfo.sellOrderPossibleQuantity = 0;
                l_tpErrorInfo.marginSecInfo = l_marginSecs;

                //取引余力エラー情報をセット
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);
            }
            
            //取引余力結果を返却する
            log.exiting(STR_METHOD_NAME);
            return l_tpResult;
        }
    }

    /**
     * （calc二階建）<BR>
     * 二階建チェックを実施する。 <BR>
     * 二階建銘柄ごとに二階建率を算出し制限二階建率を超えているかどうかチェックする。 <BR>
     * 制限二階建率を超えていた二階建チェックエラー銘柄情報の配列を作成し返却する。 <BR>
     * 
     * シーケンス図「(取引余力サービス)calc二階建」参照<BR>
     * 
     * @@param l_subAccount -(補助口座)
     * @@param l_lngProductId -(銘柄ID)
     * @@param l_tpCalcMargin -(資産余力情報<信用顧客>)
     * @@param l_tpUpd -(余力更新)
     * @@param l_orderTypeEnum - (注文種別)
     * @@param l_lngMarketId - (市場ID)
     * @@param l_datDeliDate - (発注日)
     * @@return WEB3TPMarginSecurityInfo[]
     */
    protected WEB3TPMarginSecurityInfo[] calcMarginSecurity(
        WEB3GentradeSubAccount l_subAccount,
        long l_lngProductId,
        WEB3TPTradingPowerCalcMargin l_tpCalcMargin,
        WEB3TPTradingPowerUpd l_tpUpd,
        OrderTypeEnum l_orderTypeEnum,
        long l_lngMarketId,
        Date l_datBizDate)
    {
        final String STR_METHOD_NAME =
            "calcMarginSecurity(WEB3GentradeSubAccount, long, WEB3TPTradingPowerCalcMargin, WEB3TPTradingPowerUpd, OrderTypeEnum, long, Date)";
        log.entering(STR_METHOD_NAME);

        //銘柄ID
        long l_lngCheckProductId;
        //会社部店別余力計算条件."二階建チェック方法@"
        String l_strDoublepositionCheck = null;
        
        //引数.注文種別 == null の場合
        if(l_orderTypeEnum == null)
        {
            //銘柄ID = 引数.銘柄ID
            l_lngCheckProductId = l_lngProductId;
        }
        //以外(引数.注文種別 != null)の場合
        else
        {
            //余力計算条件
            WEB3TPCalcCondition l_calcCond = l_tpCalcMargin.getCalcCondition();

            /*
             * 会社部店別余力計算条件を取得
             */
            //引数.注文種別 == 現物買注文の場合
            if(OrderTypeEnum.EQUITY_BUY.equals(l_orderTypeEnum) == true)
            {
                l_strDoublepositionCheck = l_calcCond
                    .getInstBranCalcCondition(WEB3TPCalcCondition.EQUITYBUY_DOUBLEPOSITION_CHECK);
            }
            //引数.注文種別 == 信用新規買建注文の場合
            else if(OrderTypeEnum.MARGIN_LONG.equals(l_orderTypeEnum) == true)
            {
                l_strDoublepositionCheck = l_calcCond
                    .getInstBranCalcCondition(WEB3TPCalcCondition.MARGINOPENLONG_DOUBLEPOSITION_CHECK);
            }
            //引数.注文種別 == 信用現引注文の場合
            else if(OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_orderTypeEnum) == true)
            {
                l_strDoublepositionCheck = l_calcCond
                    .getInstBranCalcCondition(WEB3TPCalcCondition.MARGINSWAPLONG_DOUBLEPOSITION_CHECK);
            }
            //引数.注文種別 == 証券振替注文の場合
            else if(OrderTypeEnum.FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES
                .equals(l_orderTypeEnum) == true)
            {
                l_strDoublepositionCheck = l_calcCond
                    .getInstBranCalcCondition(WEB3TPCalcCondition.MARGINSUBSECURITY_DOUBLEPOSITION_CHECK);
            }
            //引数.注文種別 == 以外の場合
            else
            {
                l_strDoublepositionCheck = l_calcCond
                    .getInstBranCalcCondition(WEB3TPCalcCondition.OTHERBUY_DOUBLEPOSITION_CHECK);
            }

        
            /*
             * 銘柄IDを設定する。
             */
            //"二階建チェック方法@" = 1:ORDER_PRODUCT 
            //または　@
            //"二階建チェック方法@" = 3:LISTMARKET_ORDER_PRODUCTの場合
            if(WEB3TPDoublepositionCheckDef.ORDER_PRODUCT.equals(l_strDoublepositionCheck) == true
                    || WEB3TPDoublepositionCheckDef.LISTMARKET_ORDER_PRODUCT
                            .equals(l_strDoublepositionCheck) == true)
            {
                // 銘柄ID = 引数.銘柄ID
                l_lngCheckProductId = l_lngProductId;
            }
            // "二階建チェック方法@" = 2:ALL_PRODUCT の場合
            else if(WEB3TPDoublepositionCheckDef.ALL_PRODUCT
                .equals(l_strDoublepositionCheck) == true)
            {
                //銘柄ID = -1
                l_lngCheckProductId = -1;
            }
            //以外 の場合
            else
            {
                //nullを返却
                return null;
            }
        }
        
        /*
         * 二階建率を取得する。
         */
        //補助口座より取引店を取得する
        BranchRow l_branchRow = (BranchRow) l_subAccount.getWeb3GenBranch().getDataSourceObject();

        //制限二階建率(全体設定)
        double l_dbLimitRate = l_branchRow.getMarginSecCheckRate();
        //部店ID
        long l_lngBranchId = l_branchRow.getBranchId();
        
       
        //二階建てチェック方法@==3:LISTMARKET_ORDER_PRODUCTかつ
        //銘柄ID ≠ -1 かつ 引数.市場ID ≠ -1 かつ 引数.発注日 !=null
        //の場合
        if(WEB3TPDoublepositionCheckDef.LISTMARKET_ORDER_PRODUCT.equals(l_strDoublepositionCheck) == true
                && l_lngCheckProductId != -1 && l_lngMarketId != -1 && l_datBizDate != null)
        {
            //会社コード
            String l_strInstCode = l_subAccount.getInstitution().getInstitutionCode();
            //発注日
            String l_strBizdate = GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(
                    l_datBizDate);

            //上場区分
            String l_strListType = null;
            //新上場区分
            String l_strNewListType = null;
            //店頭公開区分
            String l_strOpenOtcDiv = null;

            //株式取引銘柄マスタRowを取得する。
            EqtypeTradedProductRow l_eqTradedProductRow = WEB3TPPersistentDataManager.getInstance()
                    .getEqtypeTradedProduct(l_strInstCode, l_lngProductId, l_lngMarketId,
                            l_strBizdate);

            //株式取引銘柄マスタRowを取得できた場合
            if(l_eqTradedProductRow != null)
            {
                l_strListType = l_eqTradedProductRow.getListType();
                l_strNewListType = l_eqTradedProductRow.getNewListType();
                l_strOpenOtcDiv = l_eqTradedProductRow.getOpenOtcDiv();
            }
            //株式取引銘柄マスタRowを取得できなかった場合
            else
            {
                //株式取引銘柄UpdqRowを取得
                EqtypeTradedProductUpdqRow l_eqTradedProductUpdqRow = WEB3TPPersistentDataManager
                        .getInstance().getEqtypeTradedProductUpdq(l_lngProductId, l_lngMarketId,
                                l_strBizdate);

                //株式取引銘柄UpdqRowを取得できた場合
                if(l_eqTradedProductUpdqRow != null)
                {
                    l_strListType = l_eqTradedProductUpdqRow.getListType();
                    l_strNewListType = l_eqTradedProductUpdqRow.getNewListType();
                    l_strOpenOtcDiv = l_eqTradedProductUpdqRow.getOpenOtcDiv();
                }
                //株式取引銘柄UpdqRowを取得できなかった場合
                else
                {
                    StringBuffer l_strErrorBuf = new StringBuffer("株式取引銘柄マスター該当レコード無し:");
                    l_strErrorBuf.append("product_id=");
                    l_strErrorBuf.append(l_lngProductId);
                    l_strErrorBuf.append(" market_id=");
                    l_strErrorBuf.append(l_lngMarketId);
                    l_strErrorBuf.append(" valid_until_biz_date=");
                    l_strErrorBuf.append(l_strBizdate);
                    log.error(l_strErrorBuf.toString());
                }
            }

            try
            {
                //(部店市場上場区分別)取扱条件クラスのインスタンスを生成する。
                WEB3GentradeBranchListmarketDealtCond l_dealtCond = new WEB3GentradeBranchListmarketDealtCond(
                                                                                                              l_lngBranchId,
                                                                                                              l_lngMarketId,
                                                                                                              l_strListType,
                                                                                                              l_strNewListType,
                                                                                                              l_strOpenOtcDiv);

                //部店・市場・上場区分別の制限二階建率を取得する。
                Double l_MarginSecCheckRate = l_dealtCond.getMarginSecCheckRate();

                if(l_MarginSecCheckRate != null)
                {
                    l_dbLimitRate = l_MarginSecCheckRate.doubleValue();
                }
            }
            catch(WEB3SystemLayerException e)
            {
                StringBuffer l_strErrorBuf = new StringBuffer("(部店市場上場区分別)取扱条件テーブル　@該当レコード無し:");
                l_strErrorBuf.append("branch_id=");
                l_strErrorBuf.append(l_lngBranchId);
                l_strErrorBuf.append(" market_id=");
                l_strErrorBuf.append(l_lngMarketId);
                l_strErrorBuf.append(" list_type=");
                l_strErrorBuf.append(l_strListType);
                l_strErrorBuf.append(" new_list_type=");
                l_strErrorBuf.append(l_strNewListType);
                l_strErrorBuf.append(" open_otc_div=");
                l_strErrorBuf.append(l_strOpenOtcDiv);
                log.error(l_strErrorBuf.toString());
            }
        }

        
        // 差入保証金(T+5)を取得
        double l_dbPaidMarginDeposit =
            l_tpCalcMargin.calcPaidMarginDeposit(WEB3TPSpecifiedPointDef.T_5);
        BigDecimal l_bdPaidMarginDeposit = new BigDecimal(Double.toString(l_dbPaidMarginDeposit));

        //差入保証金(T+5) <= 0 場合
        if(l_dbPaidMarginDeposit <= 0) 
        {
            //nullを返却
            return null;
        }

        //余力更新サービスより建玉銘柄一覧を取得する
        long[] l_lngContractProductIds = l_tpUpd.getContractProducts(
                WEB3TPSpecifiedPointDef.T_5, ContractTypeEnum.LONG);
        if(l_lngContractProductIds == null)
        {
            //nullを返却
            return null;
        }
        
        //銘柄IDが-1でない場合
        if (l_lngCheckProductId != -1)
        {
            /*
             * 建玉銘柄一覧より引数.銘柄IDを検索
             */
            boolean l_flg = false;

            //建玉銘柄一覧の要素数回、処理を実行する
            int l_intCnt = l_lngContractProductIds.length;
            for (int index = 0; index < l_intCnt; index++)
            {
                if(l_lngContractProductIds[index] == l_lngCheckProductId)
                {
                    l_flg = true;
                }
            }

            //建玉銘柄一覧より引数.銘柄IDを検索できた場合
            if(l_flg == true)
            {
                //指定された銘柄IDのみを二階建銘柄IDの配列にセットする
                l_lngContractProductIds = new long[1];
                l_lngContractProductIds[0] = l_lngCheckProductId;
            }
            //以外の場合
            else
            {
                //nullを返却する。
                return null;                
            }
        }
        
        /*
         * 二階建チェックを実施
         */
        //二階建チェックエラー銘柄情報一覧
        ArrayList l_errorMargins = new ArrayList();

        //二階建率の上限値
        BigDecimal l_bdLimitRate = new BigDecimal(Double.toString(l_dbLimitRate));
        //二階建占有率
        BigDecimal l_bdMarginRate = null;
        //銘柄ごと代用証券評価額
        BigDecimal l_bdValuationPrice = null;

        //二階建銘柄IDの配列の要素数回、処理を実行する
        int l_marginProductIdsCnt = l_lngContractProductIds.length;
        for (int i = 0; i < l_marginProductIdsCnt; i++)
        {
            //銘柄ごと代用証券評価額を取得する
            double l_dbValuationPrice =
                l_tpUpd.getSubstituteValuationPriceParProduct(
                        l_lngContractProductIds[i],
                    WEB3TPSpecifiedPointDef.T_5);
            l_bdValuationPrice = new BigDecimal(Double.toString(l_dbValuationPrice));

            /*
             * 二階建占有率率を計算する
             * 
             * 二階建占有率 = (銘柄毎)代用証券評価額(T+5) / 差入保証金(T+5) * 100
             * ※少数第２位まで求める。
             */
            l_bdMarginRate =
                l_bdValuationPrice.multiply(new BigDecimal("100")).divide(
                    l_bdPaidMarginDeposit,
                    2,
                    BigDecimal.ROUND_HALF_UP);

            //（二階建率の上限値　@＜　@二階建占有率）の場合
            if (l_bdLimitRate.compareTo(l_bdMarginRate) < 0)
            {
                /*
                 * 二階建チェックエラー銘柄情報オブジェクトを生成する
                 */
                WEB3TPMarginSecurityInfo l_marginSecInfo = new WEB3TPMarginSecurityInfo();
                l_marginSecInfo.marginSecProductId = l_lngContractProductIds[i];
                l_marginSecInfo.marginSecRate = l_bdMarginRate.doubleValue();

                //二階建チェックエラー銘柄情報一覧に追加する
                l_errorMargins.add(l_marginSecInfo);
            }
        }

        //二階建チェックエラー銘柄が存在しない時
        if (l_errorMargins.isEmpty() == true)
        {
            //nullを返却する。
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            //リストを配列に変換する
            WEB3TPMarginSecurityInfo[] l_errorMarginSec =
                new WEB3TPMarginSecurityInfo[l_errorMargins.size()];
            l_errorMarginSec =
                (WEB3TPMarginSecurityInfo[])l_errorMargins.toArray(l_errorMarginSec);

            //二階建チェックエラー銘柄情報の配列を返却する
            log.exiting(STR_METHOD_NAME);
            return l_errorMarginSec;
        }
    }

    /**
     * （calc実質顧客勘定残高<現物顧客><BR>
     * 引数.補助口座の、実質顧客勘定残高(*)」を返却する。<BR>
     * (*)朝時点の確定顧客勘定残高に、当日取引分を加味した値<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。<BR>
     * <BR>
     * @@param l_subAccount -(補助口座)
     * @@param l_tpCalcEquity -(資産余力情報<現物顧客>)
     * @@param l_datDeliveryDate - (受渡日)
     * @@return double
     */
    protected double calcRealBalanceEquity(
        WEB3GentradeSubAccount l_subAccount,
        WEB3TPTradingPowerCalcEquity l_tpCalcEquity,
        Date l_datDeliveryDate)
    {
        final String STR_METHOD_NAME = "calcRealBalanceEquity()";

        /*
         * 指定日を取得する。
         */
        //指定日を取得する。
        int l_intSpecifiedPoint;

        //受渡日が当日(T+0)以前だった場合
        if (WEB3DateUtility
            .compareToDay(l_datDeliveryDate, l_tpCalcEquity.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0))
            < 0)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //受渡日がT+5以降だった場合
        else if (
            WEB3DateUtility.compareToDay(
                l_datDeliveryDate,
        l_tpCalcEquity.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5))
                > 0)
        {
            //指定日=T+5をセット
            l_intSpecifiedPoint = WEB3TPSpecifiedPointDef.T_5;
        }
        //以外(受渡日が、T+0～T+5の間)の場合
        else
        {
            //受渡日に対応する指定日を取得
            l_intSpecifiedPoint = l_tpCalcEquity.getCalcCondition().calcSpecifiedPoint(l_datDeliveryDate);
        }

        //実質預り金残高
        double l_dblRealAccBal =
            l_tpCalcEquity.getAccountBalance(l_intSpecifiedPoint)
                - l_tpCalcEquity.getTodayExecutedAmount(l_intSpecifiedPoint)
                - l_tpCalcEquity.getTodayUnexecutedAmount(l_intSpecifiedPoint)
                - l_tpCalcEquity.getOtherRestraint(l_intSpecifiedPoint);

        /*
         * 朝時点の確定MRF残高、顧客勘定残高からMRF残高への振替額を取得
         */
        //口座ID
        long l_lngAccountId = l_subAccount.getAccountId();
        //補助口座ID(預り金)
        long l_lngSubAccountId;
        try
        {
            l_lngSubAccountId =
                l_subAccount
                    .getMainAccount()
                    .getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT)
                    .getSubAccountId();
        }
        catch (NotFoundException e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //当日(T+0)
        Date l_datCurDate =
            l_tpCalcEquity.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0);
        String l_strCurDate = WEB3DateUtility.formatDate(l_datCurDate, "yyyyMMdd");

        //顧客勘定残高(マスタ情報)テーブル検索条件
        String l_strTpWhere = "account_id=? and sub_account_id=?";
        Object[] l_objTpBindVars = { new Long(l_lngAccountId), new Long(l_lngSubAccountId)};

        log.debug(
            "Finding TpCashBalanceParams where="
                + l_strTpWhere
                + ", bindVars="
                + objectsToString(l_objTpBindVars));

        //累投注文単位テーブルの検索条件
        String l_strRuitoWhere =
            "account_id=? and sub_account_id=? and order_type=? and biz_date>=? and delivery_date<=? and order_status not in (?,?)";
        Object[] l_objRuitoBindVars =
            {
                new Long(l_lngAccountId),
                new Long(l_lngSubAccountId),
                OrderTypeEnum.RUITO_SELL,
                l_strCurDate,
                l_datDeliveryDate,
                OrderStatusEnum.NOT_ORDERED,
                OrderStatusEnum.CANCELLED };

        log.debug(
            "Finding RuitoOrderUnitParams where="
                + l_strRuitoWhere
                + ", bindVars="
                + objectsToString(l_objRuitoBindVars));

        BatchedQuery[] l_queries =
            new BatchedQuery[] {
                BatchedQuery.createFindAllQuery(
                    TpCashBalanceParams.TYPE,
                    l_strTpWhere,
                    l_objTpBindVars),
                BatchedQuery.createFindAllQuery(
                    RuitoOrderUnitParams.TYPE,
                    l_strRuitoWhere,
                    l_objRuitoBindVars)};

        //MRF残高
        double l_dblMrfBal = 0;
        //振替額
        double l_dblQuantity = 0;

        try
        {
            Object[] l_results = Processors.getDefaultProcessor().doQueries(l_queries);
            List l_tpList = (List)l_results[0];
            List l_ruitoList = (List)l_results[1];

            TpCashBalanceParams l_tpParmas = null;
            RuitoOrderUnitParams l_ruitoParams = null;

            /*
             * 顧客勘定残高(マスタ)情報テーブルの検索結果よりMRF残高を取得する
             */
            if (l_tpList != null && l_tpList.size() == 1)
            {
                l_tpParmas = (TpCashBalanceParams)l_tpList.get(0);
                l_dblMrfBal = l_tpParmas.getMrfBalance();
            }
            else
            {
                //Y00127:預り金レコードが存在しなかった場合の対応
                l_dblMrfBal = 0;
            }

            /*
             * 累投注文単位テーブルの検索結果より振替額を集計
             */
            if (l_ruitoList != null && l_ruitoList.size() > 0)
            {
                for (int index = 0; index < l_ruitoList.size(); index++)
                {
                    l_ruitoParams = (RuitoOrderUnitParams)l_ruitoList.get(index);
                    l_dblQuantity = l_dblQuantity + l_ruitoParams.getQuantity();
                }
            }
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                de.getMessage(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de);
        }

        //実質顧客勘定残高 = 実質預り金残高　@－ (MRF残高 － 振替額)
        double l_dblRealBal = l_dblRealAccBal - l_dblMrfBal + l_dblQuantity;

        //実質顧客勘定残高を返却する。
        return l_dblRealBal;
    }

    /**
     * （calc実質顧客勘定残高<信用顧客>）<BR>
     * 引数.補助口座の、実質顧客勘定残高(*)」を返却する。<BR>
     * (*)朝時点の確定顧客勘定残高に、当日取引分を加味した値<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。 <BR>
     * <BR>
     * @@param l_subAccount -(補助口座)
     * @@param l_tpCalcMargin -(資産余力情報<信用顧客>)
     * @@param l_datDeliveryDate - (受渡日)
     * @@return double
     */
    protected double calcRealBalanceMargin(
        WEB3GentradeSubAccount l_subAccount,
        WEB3TPTradingPowerCalcMargin l_tpCalcMargin,
        Date l_datDeliveryDate)
    {
        final String STR_METHOD_NAME = "calcRealBalanceMargin()";

        /*
         * 指定日を取得する。
         */
        //指定日を取得する。
        int l_intSpecifiedPoint;

        //受渡日が当日(T+0)以前だった場合
        if (WEB3DateUtility
            .compareToDay(l_datDeliveryDate, l_tpCalcMargin.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0))
            < 0)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //受渡日がT+5以降だった場合
        else if (
            WEB3DateUtility.compareToDay(
                l_datDeliveryDate,
        l_tpCalcMargin.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5))
                > 0)
        {
            //指定日=T+5をセット
            l_intSpecifiedPoint = WEB3TPSpecifiedPointDef.T_5;
        }
        //以外(受渡日が、T+0～T+5の間)の場合
        else
        {
            //受渡日に対応する指定日を取得
            l_intSpecifiedPoint = l_tpCalcMargin.getCalcCondition().calcSpecifiedPoint(l_datDeliveryDate);
        }

        //実質預り金残高
        double l_dblRealAccBal =
            l_tpCalcMargin.getAccountBalance(l_intSpecifiedPoint)
                - l_tpCalcMargin.getTodayExecutedAmount(l_intSpecifiedPoint)
                - l_tpCalcMargin.getTodayUnexecutedAmount(l_intSpecifiedPoint)
                - l_tpCalcMargin.getOtherRestraint(l_intSpecifiedPoint);

        /*
         * 朝時点の確定信用保証金残高、顧客勘定残高から信用保証金残高への振替額を取得
         */
        //口座ID
        long l_lngAccountId = l_subAccount.getAccountId();
        //補助口座ID(信用保証金)
        long l_lngSubAccountId;
        try
        {
            l_lngSubAccountId =
                l_subAccount
                    .getMainAccount()
                    .getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT)
                    .getSubAccountId();
        }
        catch (NotFoundException e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }

        //当日(T+0)
        Date l_datCurDate =
            l_tpCalcMargin.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0);
        String l_strCurDate = WEB3DateUtility.formatDate(l_datCurDate, "yyyyMMdd");

        //顧客勘定残高(マスタ情報)テーブル検索条件
        String l_strTpWhere = "account_id=? and sub_account_id=?";
        Object[] l_objTpBindVars = { new Long(l_lngAccountId), new Long(l_lngSubAccountId)};

        log.debug(
            "Finding TpCashBalanceParams where="
                + l_strTpWhere
                + ", bindVars="
                + objectsToString(l_objTpBindVars));

        //入出金注文単位テーブルの検索条件
        String l_strAioWhere =
            "account_id=? and sub_account_id=? and order_type in (?,?) and biz_date>=? and delivery_date<=? and order_status not in (?,?)";
        Object[] l_objAioBindVars =
            {
                new Long(l_lngAccountId),
                new Long(l_lngSubAccountId),
                OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE,
                OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT,
                l_strCurDate,
                l_datDeliveryDate,
                OrderStatusEnum.NOT_ORDERED,
                OrderStatusEnum.CANCELLED };

        log.debug(
            "Finding AioOrderUnitParams where="
                + l_strAioWhere
                + ", bindVars="
                + objectsToString(l_objAioBindVars));

        BatchedQuery[] l_queries =
            new BatchedQuery[] {
                BatchedQuery.createFindAllQuery(
                    TpCashBalanceParams.TYPE,
                    l_strTpWhere,
                    l_objTpBindVars),
                BatchedQuery.createFindAllQuery(
                    AioOrderUnitParams.TYPE,
                    l_strAioWhere,
                    l_objAioBindVars)};

        //保証金残高
        double l_dblCashBal = 0;
        //振替額
        double l_dblQuantity = 0;

        try
        {
            Object[] l_results = Processors.getDefaultProcessor().doQueries(l_queries);
            List l_tpList = (List)l_results[0];
            List l_aioList = (List)l_results[1];

            TpCashBalanceParams l_tpParmas = null;
            AioOrderUnitParams l_aioParams = null;

            /*
             * 顧客勘定残高(マスタ)情報テーブルの検索結果より保証金残高を取得する
             */
            if (l_tpList != null && l_tpList.size() == 1)
            {
                l_tpParmas = (TpCashBalanceParams)l_tpList.get(0);

                //受渡日＝T+0の場合
                if (l_intSpecifiedPoint == WEB3TPSpecifiedPointDef.T_0)
                {
                    l_dblCashBal = l_tpParmas.getCashBalance0();
                }
                //受渡日＝T+1の場合
                else if (l_intSpecifiedPoint == WEB3TPSpecifiedPointDef.T_1)
                {
                    l_dblCashBal = l_tpParmas.getCashBalance1();
                }
                //受渡日＝T+2の場合
                else if (l_intSpecifiedPoint == WEB3TPSpecifiedPointDef.T_2)
                {
                    l_dblCashBal = l_tpParmas.getCashBalance2();
                }
                //受渡日＝T+3の場合
                else if (l_intSpecifiedPoint == WEB3TPSpecifiedPointDef.T_3)
                {
                    l_dblCashBal = l_tpParmas.getCashBalance3();
                }
                //受渡日＝T+4の場合
                else if (l_intSpecifiedPoint == WEB3TPSpecifiedPointDef.T_4)
                {
                    l_dblCashBal = l_tpParmas.getCashBalance4();
                }
                //受渡日＝T+5の場合
                else if (l_intSpecifiedPoint == WEB3TPSpecifiedPointDef.T_5)
                {
                    l_dblCashBal = l_tpParmas.getCashBalance5();
                }
            }
            else
            {
                //Y00127:保証金残高レコードが存在しなかった場合の対応
                l_dblCashBal = 0;
            }

            /*
             * 入出金注文単位テーブルの検索結果より当日振替額を集計
             */
            if (l_aioList != null && l_aioList.size() > 0)
            {
                for (int index = 0; index < l_aioList.size(); index++)
                {
                    l_aioParams = (AioOrderUnitParams)l_aioList.get(index);

                    //注文単位テーブル.注文種別 = “1005:預り金から信用保証金”の場合
                    if (l_aioParams.getOrderType().intValue()
                        == OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE.intValue())
                    {
                        //振替額 = 振替額 + 注文単位テーブル.注文数量
                        l_dblQuantity = l_dblQuantity + l_aioParams.getQuantity();
                    }
                    //注文単位テーブル.注文種別 = “1006:信用保証金から預り金”の場合
                    else if (
                        l_aioParams.getOrderType().intValue()
                            == OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT.intValue())
                    {
                        //振替額 = 振替額 - 注文単位テーブル.注文数量
                        l_dblQuantity = l_dblQuantity - l_aioParams.getQuantity();
                    }
                }
            }
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

        //実質顧客勘定残高 = 実質預り金残高 － (確定信用保証金残高 + 振替額)
        double l_dblRealBal = l_dblRealAccBal - l_dblCashBal - l_dblQuantity;

        //実質顧客勘定残高を返却する。
        return l_dblRealBal;
    }

    /**
     * （get補助口座）<BR>
     * <BR>
     * １）顧客オブジェクトを生成する。 <BR>
     * 　@[引数]<BR>
     * 　@　@口座ID：引数.口座ID<BR>
     * <BR>
     * ２）顧客.is信用口座開設()の判定<BR>
     * 　@[引数]<BR>
     *   　@弁済区分：”指定なし”<BR>
     * <BR>
     * 　@○未開設の場合(顧客.is信用口座開設()==false)<BR>
     * 　@　@　@補助口座<株式取引口座(預り金)>オブジェクトを取得し、リターンする。<BR>
     * <BR>
     * 　@○開設の場合(顧客.is信用口座開設()==true)<BR>
     * 　@　@　@補助口座<株式信用取引口座(保証金)>オブジェクトを取得し、リターンする。<BR>
     * <BR>
     * @@param l_lngAccountId - (口座ID)
     * @@return webbroker3.gentrade.WEB3GentradeSubAccount
     */
    protected WEB3GentradeSubAccount getSubAccount(long l_lngAccountId)
    {
        final String STR_METHOD_NAME = "getSubAccount(long)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //顧客
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_lngAccountId);
            //補助口座
            SubAccount l_subAccount = null;

            //信用口座開設の判定
            if (!l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
            {
                //現物顧客　@銘柄タイプ：株式取引口座(預り金)
                l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            }
            else
            {
                //信用顧客　@銘柄タイプ：株式信用取引口座(保証金)>
                l_subAccount =
                    l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            }

            //補助口座を返却
            log.exiting(STR_METHOD_NAME);
            return new WEB3GentradeSubAccount((SubAccountRow)l_subAccount.getDataSourceObject());

        }
        catch (NotFoundException nfe)
        {
            log.error(nfe.getMessage(), nfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                nfe.getMessage(),
                nfe);
        }
        catch (DataQueryException dqe)
        {
            log.error(dqe.getMessage(), dqe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                dqe.getMessage(),
                dqe);
        }
        catch (DataNetworkException dne)
        {
            log.error(dne.getMessage(), dne);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                dne.getMessage(),
                dne);
        }
    }

    /**
     * (get差金決済買付可能額)<BR>
     * <BR>
     * (get差金決済買付可能額)<BR>
     * <BR>
     * １）引数.現注文内容より、銘柄ID、市場ID、受渡日を取得する。<BR>
     * 　@　@銘柄ID=引数.現注文内容.get銘柄ID()<BR>
     * 　@　@市場ID=引数.現注文内容.get市場ID()<BR>
     * 　@　@受渡日=引数.現注文内容.get受渡日ID()<BR>
     * <BR>
     * ２）即日入金対象銘柄フラグを取得<BR>
     * 　@－銘柄ID、市場IDに対応する株式取引銘柄Rowオブジェクトを取得<BR>
     * 　@－株式取引銘柄Rowオブジェクト.即日入金対象銘柄フラグを取得<BR>
     * <BR>
     * ３）差金決済買付可能額を取得<BR>
     * 　@－差金決済取引余力サービスを取得<BR>
     * 　@－差金決済取引余力サービス.get差金決済買付可能額()をコール<BR>
     * <BR>
     * 　@［引数］<BR>
     * 　@　@補助口座：引数.補助口座<BR>
     * 　@　@指定日：１）で取得した、受渡日<BR>
     * 　@　@注文銘柄ID：１）で取得した、銘柄ID<BR>
     * 　@　@即日入金対象銘柄フラグ：２）で取得した即日入金対象銘柄フラグ<BR>
     * <BR>
     * ４）取得した差金決済買付可能額を返却<BR>
     * <BR>
     * @@param l_subAccount　@補助口座
     * @@param l_newOrderSpec  現注文内容
     * @@return double  
     */
    private double getBuyOrderPossibleAmount(
        WEB3GentradeSubAccount l_subAccount,
        WEB3TPNewOrderSpec l_newOrderSpec)
    {
        final String STR_METHOD_NAME =
            "WEB3TPTradingPowerServiceImpl.getBuyOrderPossibleAmount(WEB3GentradeSubAccount, WEB3TPNewOrderSpec)";

        try
        {
            /*
             * 差金決済買付可能額を取得
             */
            //銘柄ID
            long l_lngProductId = l_newOrderSpec.getProductId();
            //市場ID
            long l_lngMarketId = l_newOrderSpec.getMarketId();
            //受渡日
            Date l_datDeliveryDate = l_newOrderSpec.getDeliveryDate();
            //即日入金銘柄フラグ
            boolean l_isTodayDepFundReg = this.isTodayDepFundReg(
                    l_lngProductId, l_lngMarketId);

            log.debug("CALL get差金決済買付可能額");
            log.debug("　@受渡日 = " + l_datDeliveryDate);
            log.debug("　@銘柄ID = " + l_lngProductId);
            log.debug("　@即日入金銘柄フラグ = " + l_isTodayDepFundReg);

            //差金決済取引余力サービスを取得
            WEB3TPTradingPowerSettlementService l_service =
                (WEB3TPTradingPowerSettlementService)Services.getService(
                    WEB3TPTradingPowerSettlementService.class);

            //差金決済買付可能額を取得
            double l_dblDayTradeEquityTradingPower =
                l_service.getBuyOrderPossibleAmount(
                    l_subAccount,
                    l_datDeliveryDate,
                    l_lngProductId,
                    l_isTodayDepFundReg);

            return l_dblDayTradeEquityTradingPower;

        }
        catch (WEB3SystemLayerException e)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (get差金決済売付可能数量)<BR>
     * <BR>
     * １）引数.現注文内容より、銘柄ID、市場ID、受渡日を取得する。
     * 　@銘柄ID = 引数.現注文内容.get銘柄ID()
     * 　@市場ID = 引数.現注文内容.get市場ID()
     * 　@受渡日 = 引数.現注文内容.get受渡日()
     * 
     * ２）売買単位を取得
     * 　@－銘柄ID、市場IDに対応する株式取引銘柄Rowオブジェクトを取得
     * 　@－株式取引銘柄Rowオブジェクト.売買単位を取得
     * 
     * ３）指値を取得する。
     * 　@[a.信用現渡注文の場合]
     * 　@（引数.現注文内容.get注文タイプ == 8:現渡注文）
     * 
     * 　@　@指値 = 引数.現注文内容.get概算受渡代金() / 引数.現注文内容.get数量()(*)
     * 　@　@(*)小数点以下切捨て
     * 
     * 　@[a.以外（現物売付注文）の場合]
     * 
     * 　@　@指値 = 引数.現注文内容.get単価()
     * 
     * ４）差金決済売付可能数量を取得
     * 　@－差金決済取引余力サービスを取得
     * 　@－差金決済取引余力サービス.get差金決済売付可能数量()をコール
     * 
     * 　@　@［引数］
     * 　@　@　@補助口座：引数.補助口座
     * 　@　@　@指定日：１）で取得した、受渡日
     * 　@　@　@注文銘柄ID：１）で取得した、銘柄ID
     * 　@　@　@市場ID：１）で取得した市場ID
     * 　@　@　@指値：３）で取得した、指値
     * 　@　@　@売買単位：２）で取得した、売買単位
     * 
     * ４）取得した差金決済売付可能数量を返却
     * 
     * @@param l_subAccount　@補助口座
     * @@param l_newOrderSpec  現注文内容
     * @@return double
     */
    private double getSellOrderPossibleQuantity(
        WEB3GentradeSubAccount l_subAccount,
        WEB3TPNewOrderSpec l_newOrderSpec)
    {
        final String STR_METHOD_NAME = "WEB3TPTradingPowerServiceImpl.getSellOrderPossibleQuantity(WEB3GentradeSubAccount, WEB3TPNewOrderSpec)";
        log.entering(STR_METHOD_NAME);

        try
        {
            /*
             * 引数.現注文内容より、銘柄ID、市場ID、受渡日を取得する。
             * 
             */
            //銘柄ID
            long l_lngProductId = l_newOrderSpec.getProductId();
            //市場ID
            long l_lngMarketId = l_newOrderSpec.getMarketId();
            //受渡日
            Date l_datDeliveryDate = l_newOrderSpec.getDeliveryDate();

            /*
             * 売買単位を取得
             */
            //株式取引銘柄Rowを取得
            EqtypeTradedProductRow l_row = WEB3TPTradingPowerServiceImpl.getEqtypeTradedProductRow(
                l_lngProductId,
                l_lngMarketId);

            //売買単位
            double l_dblLotSize = l_row.getLotSize();

            /*
             * 指値を取得する
             */
            //指値
            double l_dblLimitPrice = 0;

            //[a.信用現渡注文の場合]
            if(OrderTypeEnum.SWAP_MARGIN_SHORT.equals(l_newOrderSpec.getOrderType()) == true)
            {
                //指値 = 引数.現注文内容.get概算受渡代金() / 引数.現注文内容.get数量()(*)
                //(*)小数点以下切捨て
                l_dblLimitPrice = Math.floor(l_newOrderSpec.getEstimatedPrice()
                    / l_newOrderSpec.getQuantity());

            }
            //[a.以外（現物売付注文）の場合]
            else
            {
                //指値 = 引数.現注文内容.get単価()
                l_dblLimitPrice = l_newOrderSpec.getPrice();
            }

            log.debug("CALL get差金決済売付可能数量");
            log.debug("　@指定日 = " + l_datDeliveryDate);
            log.debug("　@銘柄ID = " + l_lngProductId);
            log.debug("　@市場ID = " + l_lngMarketId);
            log.debug("　@指値   = " + l_dblLimitPrice);
            log.debug("　@売買単位 = " + l_dblLotSize);

            //差金決済取引余力サービスを取得
            WEB3TPTradingPowerSettlementService l_service = (WEB3TPTradingPowerSettlementService) Services.getService(WEB3TPTradingPowerSettlementService.class);

            //差金決済売付可能数量を取得
            double l_dblsellOrderPossQty = l_service.getSellOrderPossibleQuantity(
                l_subAccount,
                l_datDeliveryDate,
                l_lngProductId,
                l_lngMarketId,
                l_dblLimitPrice,
                l_dblLotSize);

            return l_dblsellOrderPossQty;
        }
        catch (WEB3SystemLayerException e)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
        catch (NotFoundException de)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }

    /**
     * (is即日入金対象銘柄フラグ)<BR>
     * 
     * 引数.銘柄ID／引数.市場IDより即日入金銘柄フラグを取得する。
     * 
     * <BR>
     * １）即日入金対象銘柄フラグを取得<BR>
     * 　@－銘柄ID、市場IDに対応する株式取引銘柄Rowオブジェクトを取得<BR>
     * 　@－株式取引銘柄Rowオブジェクト.即日入金対象銘柄フラグを取得<BR>
     * 
     * ２）取得した即日入金対象銘柄フラグを返却<BR>
     * <BR>
     * @@param l_lngProductId  銘柄ID
     * @@param l_lngMarketId  市場ID
     * @@return boolean  
     */
    private boolean isTodayDepFundReg(long l_lngProductId, long l_lngMarketId)
    {
        final String STR_METHOD_NAME =
            "WEB3TPTradingPowerServiceImpl.isTodayDepFundReg(WEB3TPNewOrderSpec)";

        try
        {
            /*
             * 即日入金対象銘柄フラグを取得
             */
            //即日入金銘柄フラグ
            boolean l_isTodayDepFundReg = false;

            //株式取引銘柄Rowを取得。
            EqtypeTradedProductRow l_row =
                WEB3TPTradingPowerServiceImpl.getEqtypeTradedProductRow(
                    l_lngProductId,
                    l_lngMarketId);

            // 即日入金銘柄フラグをセット
            if (BooleanEnum.TRUE.equals(l_row.getTodayDepFundReg()))
            {
                l_isTodayDepFundReg = true;
            }
            else
            {
                l_isTodayDepFundReg = false;
            }

            return l_isTodayDepFundReg;

        }
        catch (NotFoundException de)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }

    /**
     * (get当日株式予約注文単位一覧) <BR>
     * 当日以降の株式株式予約注文単位のリストを取得する。 <BR>
     * <BR>
     * 1)株式予約注文単位テーブルを以下の条件で検索する。 <BR>
     * <BR>
     * 　@[検索条件] <BR>
     * 　@　@口座ID = 引数.口座ID <BR>
     * 　@　@発注日 >= 引数.当日(T+0) <BR>
     * <BR>
     * 2)検索されたRowオブジェクトのリストを返却する。 
     * @@param l_lngAccountId - (口座ID)<BR>
     * @@param l_datT0 - (当日(T+0))
     * @@return List  
     */
    private List getTodaysRsvEqOrderUnits(long l_lngAccountId, Date l_datT0)
    {
        final String STR_METHOD_NAME = "getTodaysRsvEqOrderUnits(long, Date)";
        log.entering(STR_METHOD_NAME);

        try
        {
            StringBuffer l_strWhereBuf = new StringBuffer(" ");
            l_strWhereBuf.append("account_id = ? and ");
            l_strWhereBuf.append("TO_DATE(biz_Date,'YYYYMMDD') >= ?");
            String l_strWhere = l_strWhereBuf.toString();

            Object[] l_bindVars =
            {new Long(l_lngAccountId), l_datT0};

            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRows = l_qp.doFindAllQuery(
                    RsvEqOrderUnitRow.TYPE,
                    l_strWhere,
                    null,
                    l_bindVars);

            log.exiting(STR_METHOD_NAME);
            return l_lisRows;
        }
        catch(DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    de.getMessage(),
                    de);
        }
    }
    
    /**
     * (get出金可能額～0補正無し～) （※）出金余力チェック時に使用<BR>
     * <BR>
     * シーケンス図「(取引余力サービス)get出金可能額～0補正無し～」参照<BR>
     * ==================================================================== <BR>
     * シーケンス図 ：((取引余力サービス)get出金可能額～0補正無し～) <BR>
     * 具体位置：(受渡日 < 余力計算条件.get営業日（0）の場合)<BR>
     * class: WEB3BusinessLayerException <BR>
     * 　@tag: BUSINESS_ERROR_02336 <BR>
     * ==================================================================== <BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_datDeliveryDate - (受渡日)
     * @@param l_intSummonsDiv - (呼び出し元区分)
     * @@return double
     */
    private double getPaymentTradingPowerForCheck(
            WEB3GentradeSubAccount l_subAccount, 
            Date l_datDeliveryDate, 
            int l_intSummonsDiv
            )
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getPaymentTradingPowerForCheck(WEB3GentradeSubAccount, Date, boolean)";
        log.entering(STR_METHOD_NAME);

        //指定日
        int l_intDeliDate = 0;

        //引数.補助口座、引数.受渡日がnullの場合
        if (l_subAccount == null || l_datDeliveryDate == null)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    
        //口座IDを取得
        long l_lngAccountId = l_subAccount.getAccountId();
    
        //顧客オブジェクトを取得
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //信用口座開設区分を取得
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        /*
         * 余力計算条件オブジェクトを生成する
         */
        WEB3TPCalcCondition l_calcCond = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

        //受渡日 < 余力計算条件.get営業日（0）の場合
        if (WEB3DateUtility.compareToDay(l_datDeliveryDate,
            l_calcCond.getBizDate(WEB3TPSpecifiedPointDef.T_0)) < 0)
        {
            //例外をスローする
            log.debug("受渡日が非営業日です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02336,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "受渡日が非営業日です。");
        }
        //受渡日 > 余力計算条件.get営業日（5）の場合
        else if (WEB3DateUtility.compareToDay(l_datDeliveryDate,
            l_calcCond.getBizDate(WEB3TPSpecifiedPointDef.T_5)) > 0)
        {
            //set余力計算基準日<出金>(int)
            //余力計算基準日をセットする。
            //[引数] 
            //余力計算基準日 = 5
            l_calcCond.setPaymentBasePoint(WEB3TPSpecifiedPointDef.T_5);
        }
        //受渡日が上記以外の場
        else
        {
            //calc指定日(Date)
            //引数.受渡日に対応する指定日を取得する。
            //[引数]
            //Date：　@引数.受渡日
            l_intDeliDate = l_calcCond.calcSpecifiedPoint(l_datDeliveryDate);

            //set余力計算基準日<出金>(int)
            //余力計算基準日をセットする。
            //[引数]
            //余力計算基準日 = calc指定日の戻り値
            l_calcCond.setPaymentBasePoint(l_intDeliDate);
        }

        //現物顧客の場合
        if (l_blnMargin == false)
        {
            //余力計算結果(List)を取得
            List l_lisCalcResult =
                WEB3TPTradingPowerCalcEquity.findCalcResultEquityParams(l_lngAccountId);

            //資産余力情報
            WEB3TPTradingPowerCalcEquity l_calcEquity =
                new WEB3TPTradingPowerCalcEquity(l_lisCalcResult, l_calcCond);

            //calc適用その他商品買付可能額(OrderTypeEnum)
            //最小のその他商品買付可能額を計算する。
            //[引数]
            //OrderTypeEnum：　@1001:出金
            WEB3TPCalcResult l_result = l_calcEquity.calcAppliedOtherTradingPower(OrderTypeEnum.CASH_OUT);
            log.debug(l_result.toString());

            log.debug("取引可能額 = " + Double.toString(l_result.tradingPower));
            log.exiting(STR_METHOD_NAME);
            return l_result.tradingPower;
        }
        //信用顧客の場合
        else
        {
            //余力計算結果(List)を取得
            List l_lisCalcResult =
                WEB3TPTradingPowerCalcMargin.findCalcResultMarginParams(l_lngAccountId);

            //資産余力情報
            WEB3TPTradingPowerCalcMargin l_calcMargin =
                new WEB3TPTradingPowerCalcMargin(l_lisCalcResult, l_calcCond);

            //get会社部店別余力計算条件("cashout.tradingpower.check") == 1:ON_BIZ_DATEの戻り値の場合
            //(出金日の出金可能額を取得する。場合)
            //且つ、引数.呼び出し元区分が、2（：get出金可能額～0補正無し～()が、呼び出し元）の場合
            if (WEB3CashoutTradingpowerCheckDef.ON_BIZ_DATE.equals(
                l_calcCond.getInstBranCalcCondition(
                    WEB3BranchPreferencesNameDef.CASHOUT_TRADINGPOWER_CHECK))
                    && l_intSummonsDiv == 2)
            {
                //is取引停止区分( )
                //取引停止区分を取得する。
                boolean l_blnIsTradingStop = l_calcCond.isTradingStop();

                //is出金余力区分( )
                //出金余力区分を取得する。
                boolean l_blnIsPaymentStop = l_calcCond.isPaymentStop();

                //is取引停止区分()、is出金余力区分()の戻り値のいずれかがtrueの場合
                if (l_blnIsTradingStop || l_blnIsPaymentStop)
                {
                    //-1を返却する。
                    log.exiting(STR_METHOD_NAME);
                    return -1D;
                }

                // calcその他商品買付可能額(int)
                //その他商品買付可能額を計算する。 
                //[引数] 
                //int： 余力計算条件.calc指定日()の戻り値
                double l_dblOtherTradingPower =
                    l_calcMargin.calcOtherTradingPower(l_intDeliDate);

                //calcその他商品買付可能額()の戻り値 < 0 の場合
                if (l_dblOtherTradingPower < 0)
                {
                    //-1を返却する。
                    log.exiting(STR_METHOD_NAME);
                    return -1D;
                }
                else
                {
                    //返却値=calcその他商品買付可能額()の戻り値
                    log.exiting(STR_METHOD_NAME);
                    return l_dblOtherTradingPower;
                }
            }

            //calc適用その他商品買付可能額(OrderTypeEnum)
            //最小のその他商品買付可能額を計算する。
            //[引数]
            //OrderTypeEnum：　@1001:出金
            WEB3TPCalcResult l_result = l_calcMargin.calcAppliedOtherTradingPower(OrderTypeEnum.CASH_OUT);
            log.debug(l_result.toString());   

            if (WEB3CashoutTodayDepositTransferDivDef.EXECUTE.equals(
                l_calcCond.getInstBranCalcCondition(
                    WEB3BranchPreferencesNameDef.CASHOUT_TODAY_DEPOSIT_TRANSFER_DIV))
                && l_intSummonsDiv == 3
                && WEB3DateUtility.compareToDay(l_datDeliveryDate,
                    l_calcCond.getBizDate(WEB3TPSpecifiedPointDef.T_1)) == 0)
            {
                //calc預り金請求余力(指定日 : int)
                //int： 0
                double l_dblCalcAccountBalanceDemandPower =
                    l_calcMargin.calcAccountBalanceDemandPower(WEB3TPSpecifiedPointDef.T_0);

                //calc保証金引出余力(指定日 : int)
                //int： 0
                double l_dblCalcMarginDrawPower =
                    l_calcMargin.calcMarginDrawPower(WEB3TPSpecifiedPointDef.T_0);

                //資産余力情報<信用顧客>Wrapper
                //余力計算結果Params：find余力計算結果<信用顧客>Params()の戻り値
                //余力計算条件：　@create余力計算条件()の戻り値
                WEB3TPTradingPowerCalcMarginWrapper l_tradingPowerCalcMarginWrapper =
                    new WEB3TPTradingPowerCalcMarginWrapper(l_lisCalcResult, l_calcCond);

                //calc実質顧客勘定残高<信用顧客>
                //補助口座：引数.補助口座
                //資産余力情報<信用顧客>：資産余力情報<信用顧客>Wrapperオブジェクト
                //Date：引数.受渡日
                double l_dblCalcRealBalanceMargin = this.calcRealBalanceMargin(
                    l_subAccount, l_tradingPowerCalcMarginWrapper, l_datDeliveryDate);

                //Max( Min(預り金請求余力(T+0), 保証金引出余力(T+0)), 0 ) ＋ Max( 実質顧客勘定残高(T+1), 0 )
                double l_dblAmount = Math.max(
                        Math.min(
                            l_dblCalcAccountBalanceDemandPower,
                            l_dblCalcMarginDrawPower), 0);
                l_dblAmount =
                    new BigDecimal(Double.toString(l_dblAmount)).add(
                        new BigDecimal(
                            Double.toString(Math.max(
                                l_dblCalcRealBalanceMargin, 0)))).doubleValue();
                log.exiting(STR_METHOD_NAME);
                return Math.min(l_result.tradingPower, l_dblAmount);
            }
            log.debug("取引可能額 = " + Double.toString(l_result.tradingPower));
            log.exiting(STR_METHOD_NAME);
            return l_result.tradingPower;
        }
    }
    
    /**
     * (get株式取引銘柄())<BR>
     * <BR>
     * 株式取引銘柄を取得する。<BR>
     * @@param l_lngProductId　@銘柄ID  
     * @@param l_lngMarketId  市場ID
     * @@return EqtypeTradedProductRow  - 株式取引銘柄Row
     */
    private static EqtypeTradedProductRow getEqtypeTradedProductRow(
        long l_lngProductId,
        long l_lngMarketId)
        throws NotFoundException
    {
        String l_tmName = TradingModuleImpl.TRADING_MODULE_NAME;
        EqTypeProductManager l_pm =
            (EqTypeProductManager) (GtlUtils.getTradingModule(l_tmName).getProductManager());
        TradedProduct tradedProduct = l_pm.getTradedProduct(l_lngProductId, l_lngMarketId);
        EqtypeTradedProductRow l_row = (EqtypeTradedProductRow)tradedProduct.getDataSourceObject();
        return l_row;
    }

    private static String objectsToString(Object[] l_objBindVars)
    {

        StringBuffer l_sb = new StringBuffer();
        if (l_objBindVars != null)
        {
            for (int i = 0; i < l_objBindVars.length; i++)
            {
                if (i > 0)
                {
                    l_sb.append(",");
                }
                l_sb.append("[").append(i).append("]=");
                l_sb.append(String.valueOf(l_objBindVars[i]));
            }
        }
        return l_sb.toString();
    }
   
    /**
     * (get大証金への振替可能額) <BR>
     * <BR>
     * 証券担保ローン実施顧客について、預り金返済額を返却する。 <BR>
     * <BR>
     * シーケンス図「(取引余力サービス)get大証金への振替可能額」参照 <BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_datDeliveryDate - (受渡日)
     * @@return double
     * @@throws WEB3SystemLayerException
     */
    public double getOsakaTransferableTradingPower(
        WEB3GentradeSubAccount l_subAccount,
        Date l_datDeliveryDate) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getOsakaTransferableTradingPower(WEB3GentradeSubAccount, Date)";
        log.entering(STR_METHOD_NAME);
        
        //引数.補助口座がnull の場合
        if(l_subAccount == null)
        {
            //エラーをスロー
            log.error("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1.getMainAccount()
        //引数.補助口座より、顧客オブジェクトを取得する。
        WEB3GentradeMainAccount l_mainAccount = 
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        
        //1.2. is信用口座開設(String)
        //信用口座を開設しているかどうかの判別を行う。 
        //true：　@開設済 
        //false：　@未開設 
        //[引数] 
        //弁済区分：　@0(指定なし)
        boolean l_blnMarginAccountEstablished = 
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
        
        //1.3.(*)分岐フロー
        //(*)分岐フロー
        //is信用口座開設()の戻り値 = true(信用顧客)
        //の場合は以下の処理を実施する。
        if (l_blnMarginAccountEstablished)
        {
            //1.3.1.(*)例外をスローする。
            //エラーをスロー
            log.error("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.4.create余力計算条件<標準>(補助口座)
        //余力計算条件を生成する。 
        //[引数] 
        //補助口座：引数.補助口座
        //余力計算条件オブジェクトを生成する。
        WEB3TPCalcCondition l_calcCond =
            WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

        //1.5.calc指定日(Date)
        //引数.受渡日に対応する指定日を取得する。 
        //[引数] 
        //Date：　@引数.受渡日
        int l_intSpecifiedPoint = l_calcCond.calcSpecifiedPoint(l_datDeliveryDate);
        
        //1.6.set余力計算基準日<その他買付>(int)
        //余力計算基準日<その他買付>の値を引数.受渡日の基準日にセットし直す。 
        //[引数] 
        //int：　@calc指定日()の戻り値
        l_calcCond.setOtherBasePoint(l_intSpecifiedPoint);
        
        //1.7.find余力計算結果<現物顧客>～口座ＩＤ指定～(long)
        //余力計算結果<現物顧客>Paramsを取得する。 
        //[引数] 
        //long:引数.補助口座.getMainAccountId()
        List l_lisCalcResult = WEB3TPTradingPowerCalcEquity.findCalcResultEquityParams(
            l_subAccount.getMainAccount().getAccountId());
        
        //1.8.資産余力情報<現物顧客>(List, 余力計算条件)
        //資産余力情報<現物顧客>オブジェクトを生成する。 
        //[引数] 
        //余力計算結果Params：find余力計算結果<現物顧客>Params()の戻り値 
        //余力計算条件：　@create余力計算条件()の戻り値
        WEB3TPTradingPowerCalcEquity l_tpTradingPowerCalcEquity = 
            new WEB3TPTradingPowerCalcEquity(l_lisCalcResult, l_calcCond);

        //1.9.calc適用その他商品買付可能額(OrderTypeEnum)
        //最小のその他商品買付可能額を計算する。 
        //[引数] 
        //OrderTypeEnum = 1019：振替注文（預り金から大証金)
        WEB3TPCalcResult l_tpCalcResult = 
            l_tpTradingPowerCalcEquity.calcAppliedOtherTradingPower(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_DSK);
        
        //1.10.余力計算結果.取引可能額を0補正して返却する。
        //返却値=MAX(余力計算結果.取引可能額, 0)
        //※余力計算結果 = calc適用その他商品買付可能額()の戻り値
        double l_dblMax = Math.max(l_tpCalcResult.tradingPower, 0);
        
        log.exiting(STR_METHOD_NAME);
        return l_dblMax;
    }
    
    /**
     * (get出金可能額&lt;DB時価&gt;～0補正無し～)<BR>
     * (get出金可能額&lt;DB時価&gt;～0補正無し～)（※）出金余力チェック時に使用<BR>
     * <BR>
     * シーケンス図「(取引余力サービス)get出金可能額&lt;DB時価&gt;～0補正無し～」参照<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * @@param l_datDeliveryDate - (受渡日)<BR>
     * @@return double
     * @@throws WEB3SystemLayerException
     */
    public double getPaymentTradingPowerDBQuote(WEB3GentradeSubAccount l_subAccount, Date l_datDeliveryDate)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getPaymentTradingPowerDBQuote(WEB3GentradeSubAccount, Date)";
        log.entering(STR_METHOD_NAME);

        //引数.補助口座、引数.受渡日がnullの場合
        if (l_subAccount == null || l_datDeliveryDate == null)
        {
            //エラーをスロー
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        double l_dblReturn = 0;

        //getMainAccount( )
        //引数.補助口座より、顧客オブジェクトを取得する。 
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //is信用口座開設(String)
        //信用口座を開設しているかどうかの判別を行う。
        //true：　@開設済
        //false：　@未開設
        //[引数]
        //弁済区分：　@0（指定なし）
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //分岐フロー
        //is信用口座開設(String) == false
        if (!l_blnMargin)
        {
            //create余力計算条件<標準>(補助口座)
            //余力計算条件を生成する。
            //[引数]
            //補助口座：　@引数.補助口座
            WEB3TPCalcCondition l_calcCond = 
                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            //calc指定日(Date)
            //引数.受渡日に対応する指定日を取得する。 
            //[引数]
            //Date：　@引数.受渡日
            int l_intSpecifiedPoint = l_calcCond.calcSpecifiedPoint(l_datDeliveryDate);

            //find余力計算結果<現物顧客>～口座ＩＤ指定～(long)
            //余力計算結果<現物顧客>Paramsを取得する。
            //[引数]
            //long：　@引数.補助口座.getMainAccountId()
            List l_lisCalcResult = WEB3TPTradingPowerCalcEquity.findCalcResultEquityParams(
                l_mainAccount.getAccountId());

            //資産余力情報<現物顧客>(List, 使用可能現金情報, 余力計算条件)
            //資産余力情報<現物顧客>オブジェクトを生成する。  
            //[引数]  
            //余力計算結果Params：　@find余力計算結果<現物顧客>()の戻り値  
            //余力計算条件：　@create余力計算条件<標準>()の戻り値 
            WEB3TPTradingPowerCalcEquity l_tpTradingPowerCalcEquity = 
                new WEB3TPTradingPowerCalcEquity(l_lisCalcResult, l_calcCond);

            //calc適用その他商品買付可能額(OrderTypeEnum, int)
            //最小のその他商品買付可能額を計算する。
            //[引数]
            //OrderTypeEnum：　@1001:出金
            //int：　@calc指定日()の戻り値
            WEB3TPCalcResult l_tpCalcResult =
                l_tpTradingPowerCalcEquity.calcAppliedOtherTradingPower(
                    OrderTypeEnum.CASH_OUT,
                    l_intSpecifiedPoint);

            //返却値 = 余力計算結果.取引可能額
            l_dblReturn = l_tpCalcResult.tradingPower;
        }
        //分岐フロー
        //is信用口座開設(String) == true
        else
        {
            //create余力計算条件<DB時価>(補助口座)
            //余力計算条件を生成する。
            //[引数]
            //補助口座：　@引数.補助口座
            WEB3TPCalcCondition l_calcCond = 
                WEB3TPCalcCondition.createCalcConditionDBQuote(l_subAccount);

            //calc指定日(Date)
            //引数.受渡日に対応する指定日を取得する。 
            //[引数]
            //Date：　@引数.受渡日
            int l_intSpecifiedPoint = l_calcCond.calcSpecifiedPoint(l_datDeliveryDate);

            //余力更新(long, boolean, 余力計算条件, 現注文内容[])
            //余力更新サービスオブジェクトを生成する。
            //[引数]
            //long：　@引数.補助口座.getAccountId()
            //boolean：　@is信用口座開設()の返り値
            //余力計算条件：　@create余力計算条件<DB時価>()の戻り値
            //現注文内容[]：　@null
            WEB3TPTradingPowerUpd l_tpUpd = new WEB3TPTradingPowerUpd(
                l_mainAccount.getAccountId(),
                l_blnMargin,
                l_calcCond,
                null);

            //calc余力更新内容<信用顧客>(String)
            //余力計算結果のListを取得
            //[引数]
            //String：　@2：DB時価値洗い
            List l_lisCalcResult =
                l_tpUpd.calcTradingpowerUpdResultMargin(WEB3TPMarkToMarketDivDef.DB_QUOTE);

            //資産余力情報<信用顧客>(List, 余力計算条件)
            //資産余力情報<信用顧客>オブジェクトを生成
            //[引数]
            //余力計算結果Params<信用顧客>：　@calc余力更新内容<信用顧客>()の戻り値
            //余力計算条件：　@create余力計算条件<DB時価>()の戻り値
            WEB3TPTradingPowerCalcMargin l_tpTradingPowerCalcMargin = 
                new WEB3TPTradingPowerCalcMargin(l_lisCalcResult, l_calcCond);

            //calc適用その他商品買付可能額(OrderTypeEnum, int)
            //最小のその他商品買付可能額を計算する。
            //[引数]
            //OrderTypeEnum：　@1001:出金
            //int：　@calc指定日()の戻り値
            WEB3TPCalcResult l_tpCalcResult =
                l_tpTradingPowerCalcMargin.calcAppliedOtherTradingPower(
                    OrderTypeEnum.CASH_OUT,
                    l_intSpecifiedPoint);

            //save余力更新内容<信用顧客>(List)
            //余力更新処理を実行する。
            //[引数]
            //List：　@calc余力更新内容<信用顧客>()の戻り値
            l_tpUpd.saveTradingpowerUpdResultMargin(l_lisCalcResult);

            //返却値 = 余力計算結果.取引可能額
            l_dblReturn = l_tpCalcResult.tradingPower;
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblReturn;
    }

    /**
     * (get保証金への振替額) <BR>
     * <BR>
     * 保証金への振替額を取得する。<BR>
     * <BR>
     * シーケンス図「(取引余力サービス)get保証金への振替額」参照 <BR>
     * <BR>
     * ※指定された顧客が信用口座未開設の時、保証金への振替額(=0)を返却する。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_datDeliveryDate - (受渡日)
     * @@param l_dblPayAmount - (入金額)
     * @@return double
     * @@throws WEB3BaseException
     */
    public double getTransferAmountToDeposit(
        WEB3GentradeSubAccount l_subAccount,
        Date l_datDeliveryDate,
        double l_dblPayAmount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTransferAmountToDeposit(WEB3GentradeSubAccount, Date, double)";
        log.entering(STR_METHOD_NAME);
        // 引数.補助口座がnullの場合
        if (l_subAccount == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        BigDecimal l_bdPayAmount = new BigDecimal(l_dblPayAmount + "");

        double l_dblTransferAmount = 0;

        BigDecimal l_bdAdddepositNotClearAmt = new BigDecimal("0");

        double l_dblRealBalanceMargin = 0;

        // 顧客オブジェクトを取得する
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        
        //  is信用口座開設
        // 信用口座を開設しているかどうかの判別を行う。
        // true：　@開設済
        // false：　@未開設
        // [引数]
        // 弁済区分：　@0(指定なし)
        boolean l_blnIsMarginAccountEstablished =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        // 信用顧客の場合
        if (l_blnIsMarginAccountEstablished)
        {
            // create余力計算条件<標準>
            // 余力計算条件を生成する
            // [引数]
            // 補助口座：引数.補助口座
            // 余力計算条件オブジェクトを生成する。
            WEB3TPCalcCondition l_calcCond =
                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            // find余力計算結果<信用顧客>～口座ＩＤ指定～
            List l_lisCalcResults =
                WEB3TPTradingPowerCalcMargin.findCalcResultMarginParams(l_subAccount.getAccountId());

            // 資産余力情報<信用顧客>オブジェクトを生成する
            WEB3TPTradingPowerCalcMargin l_tpTradingPowerCalcMargin =
                new WEB3TPTradingPowerCalcMargin(l_lisCalcResults, l_calcCond);

            //get会社部店別余力計算条件(String)
            String l_strDepositRealTransferEnforceDiv =
                l_calcCond.getInstBranCalcCondition(
                    WEB3TPCalcCondition.DEPOSIT_REAL_TRANSFER_ENFORCE_DIV);

            // get会社部店別余力計算条件()の戻り値  =  null or
            // (get会社部店別余力計算条件()の戻り値  !=  "1" and
            // get会社部店別余力計算条件()の戻り値  !=  "2")
            // の場合は以下の計算を実施して、保証金への振替額を返却する
            if (l_strDepositRealTransferEnforceDiv == null
                || (!WEB3TPDepositRealTransferEnforceDivDef.EXECUTE_T0.equals(l_strDepositRealTransferEnforceDiv)
                    && !WEB3TPDepositRealTransferEnforceDivDef.EXECUTE_T1.equals(l_strDepositRealTransferEnforceDiv)))
            {
                log.exiting(STR_METHOD_NAME);
                return l_dblTransferAmount;
            }

            // 分岐プロー
            // get会社部店別余力計算条件()の戻り値 = "2" の場合は以下の処理を実施する
            if (WEB3TPDepositRealTransferEnforceDivDef.EXECUTE_T1.equals(l_strDepositRealTransferEnforceDiv))
            {
            	// calc指定日(Date)
            	int l_intSpecifiedPoint = l_calcCond.calcSpecifiedPoint(l_datDeliveryDate);

            	// get営業日(int)
            	Date l_datBizDate = l_calcCond.getBizDate(l_intSpecifiedPoint + 1);

            	// calc実質顧客勘定残高<信用顧客>(補助口座, 資産余力情報<信用顧客>, Date)
            	l_dblRealBalanceMargin = this.calcRealBalanceMargin(
            	    l_subAccount, l_tpTradingPowerCalcMargin, l_datBizDate);

                //create入金請求管理(顧客)
                WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
                    WEB3TPPaymentRequisitionManagement.createPaymentRequisitionManagement(l_mainAccount);

                // create第二水準追証未解消情報( )
                //第二水準追証未解消情報を生成する。
                WEB3TPSecondAdddepositNotClearInfo l_secondAdddepositNotClearInfo =
                    l_paymentRequisitionManagement.createSecondAdddepositNotClearInfo();

                BigDecimal l_bdSecondDepositNonPay = new BigDecimal(
                    l_secondAdddepositNotClearInfo.secondDepositNonPay + "");
                BigDecimal l_bdSecondDeposit2 = new BigDecimal(
                    l_secondAdddepositNotClearInfo.secondDeposit2 + "");
                BigDecimal l_bdSecondDeposit1 = new BigDecimal(
                    l_secondAdddepositNotClearInfo.secondDeposit1 + "");
                BigDecimal l_bdSecondDepositExpect = new BigDecimal(
                    l_secondAdddepositNotClearInfo.secondDepositExpect + "");

                //第二水準追証未解消情報を計算する
                //(*1)受渡日の指定日 = calc指定日()の戻り値
                //(*1)受渡日の指定日 = 0の場合
                if (l_intSpecifiedPoint == WEB3TPSpecifiedPointDef.T_0)
                {
                    // 追証未解消金額　@＝　@第二水準追証未解消情報.追証金額(未入金) 
                    //　@　@　@　@　@　@　@　@　@　@+第二水準追証未解消情報.追証金額(請求2) 
                    l_bdAdddepositNotClearAmt = l_bdSecondDepositNonPay.add(l_bdSecondDeposit2);
                }
                //(*1)受渡日の指定日 = 1の場合
                else if (l_intSpecifiedPoint == WEB3TPSpecifiedPointDef.T_1)
                {
                    //追証未解消金額　@＝　@第二水準追証未解消情報.追証金額(未入金) 
                    //                　@+第二水準追証未解消情報.追証金額(請求2) 
                    //                　@+第二水準追証未解消情報.追証金額(請求1)
                    l_bdAdddepositNotClearAmt =
                        l_bdSecondDepositNonPay.add(l_bdSecondDeposit2).add(l_bdSecondDeposit1);
                }
                //(*1)受渡日の指定日 = 上記以外の場合
                else
                {
                    //追証未解消金額　@＝　@第二水準追証未解消情報.追証金額(未入金) 
                    //                　@+第二水準追証未解消情報.追証金額(請求2) 
                    //                　@+第二水準追証未解消情報.追証金額(請求1)
                    //                　@+第二水準追証未解消情報.追証金額(見込金額)
                    l_bdAdddepositNotClearAmt = l_bdSecondDepositNonPay.add(
                        l_bdSecondDeposit2).add(l_bdSecondDeposit1).add(l_bdSecondDepositExpect);
                }

                //追証未解消金額 == 0 の場合以下の処理を実施する。
                if (GtlUtils.Double.isZero(l_bdAdddepositNotClearAmt.doubleValue()))
                {
                    //get第一水準追証未解消金額( )
                    //第一水準追証未解消金額を取得する。
                    double l_dblFirstAdddepositUncancelAmt =
                        l_paymentRequisitionManagement.getFirstAdddepositUncancelAmt();

                    //get第一水準追証経過日数( )
                    //第一水準追証経過日数を取得する。
                    int l_intFirstAdddepositPassDay =
                        l_paymentRequisitionManagement.getFirstAdddepositPassDay();

                    //get第一水準追証有効経過日数( )
                    //第一水準追証有効経過日数を取得する。
                    int l_intFirstAdddepositPassDayValid =
                        l_paymentRequisitionManagement.getFirstAdddepositPassDayValid();

                    //第一水準追証未解消情報を計算する
                    //(*1)有効経過日数 - (*2)経過日数 <= (*3)受渡日の指定日の場合
                    //(*1)get第一水準追証有効経過日数()の戻り値
                    //(*2)get第一水準追証経過日数()の戻り値
                    //(*3)calc指定日()の戻り値
                    if (l_intFirstAdddepositPassDayValid - l_intFirstAdddepositPassDay <= l_intSpecifiedPoint)
                    {
                        //追証未解消金額 = get第一水準追証未解消金額()の戻り値
                        l_bdAdddepositNotClearAmt = new BigDecimal(l_dblFirstAdddepositUncancelAmt + "");
                    }
                }
            }

            // calc実質顧客勘定残高<信用顧客>
            double l_dblRealBal = this.calcRealBalanceMargin(l_subAccount, l_tpTradingPowerCalcMargin, l_datDeliveryDate);

            BigDecimal l_bdRealBal = new BigDecimal(String.valueOf(l_dblRealBal));

            //　@・get会社部店別余力計算条件()の戻り値 = "1"　@の場合
            if (WEB3TPDepositRealTransferEnforceDivDef.EXECUTE_T0.equals(l_strDepositRealTransferEnforceDiv))
            {
                //　@保証金への振替額
                //　@= 
                //　@MIN(MAX((*1)実質顧客勘定残高+引数.保証金振替額, 0), 引数.保証金振替額))
                //(*1) 1.3.8. calc実質顧客勘定残高<信用顧客>の戻り値
                double l_dblAmount = l_bdRealBal.add(l_bdPayAmount).doubleValue();
                l_dblAmount = Math.max(l_dblAmount, 0);
                l_dblTransferAmount = Math.min(l_dblAmount, l_dblPayAmount);
            }
            //　@・get会社部店別余力計算条件()の戻り値 = "2"　@の場合
            else if (WEB3TPDepositRealTransferEnforceDivDef.EXECUTE_T1.equals(l_strDepositRealTransferEnforceDiv))
            {
                //不足(T+0) = MIN((*1)実質顧客勘定残高, 0)
                double l_dblNon0 = Math.min(l_dblRealBal, 0);
                BigDecimal l_bdNon0 = new BigDecimal(l_dblNon0 + "");

                //不足(T+1) = MIN((*2)実質顧客勘定残高, 0)
                double l_dblNon1 = Math.min(l_dblRealBalanceMargin, 0);
                BigDecimal l_bdNon1 = new BigDecimal(l_dblNon1 + "");

                //１、不足(T+0) <= 不足(T+1)の場合
                if ((l_dblNon0 < l_dblNon1) || GtlUtils.Double.isEqual(l_dblNon0, l_dblNon1))
                {
                    //・保証金振替額 = MAX(引数.保証金振替額 + 不足(T+0), 0)
                    double l_dblAmount = l_bdPayAmount.add(l_bdNon0).doubleValue();
                    l_dblTransferAmount = Math.max(l_dblAmount, 0);
                }
                //２、不足(T+0) > 不足(T+1)の場合
                else
                {
                    //a）引数.保証金振替額 + 不足(T+0) - 追証未解消金額 <= 0
                    if (l_bdPayAmount.add(l_bdNon0).subtract(l_bdAdddepositNotClearAmt).doubleValue() < 0
                        || GtlUtils.Double.isZero(
                        l_bdPayAmount.add(l_bdNon0).subtract(l_bdAdddepositNotClearAmt).doubleValue()))
                    {
                        //・保証金振替額 = MAX(引数.保証金振替額 + 不足(T+0), 0)
                        double l_dblAmount = l_bdPayAmount.add(l_bdNon0).doubleValue();
                        l_dblTransferAmount = Math.max(l_dblAmount, 0);
                    }
                    //b）引数.保証金振替額 + 不足(T+0) - 追証未解消金額 > 0
                    else
                    {
                        //・保証金振替額 = MAX(引数.保証金振替額 + 不足(T+1), 追証未解消金額, 0)
                        double l_dblAmount = l_bdPayAmount.add(l_bdNon1).doubleValue();
                        l_dblAmount = Math.max(l_dblAmount, l_bdAdddepositNotClearAmt.doubleValue());
                        l_dblTransferAmount = Math.max(l_dblAmount, 0);
                    }
                }
            }

            log.exiting(STR_METHOD_NAME);
            return l_dblTransferAmount;
        }
        // 現物顧客の場合
        else
        {
            log.exiting(STR_METHOD_NAME);
            return l_dblTransferAmount;
        }
    }

    /**
     * (getその他商品買付可能額～0補正無し～)<BR>
     * (getその他商品買付可能額～0補正無し～)<BR>
     * <BR>
     * その他商品買付可能額（0補正無し）を取得する。<BR>
     * <BR>
     * シーケンス図「(取引余力サービス)getその他商品買付可能額～0補正無し～」参照<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座
     * @@param l_datDeliveryDate - (受渡日)<BR>
     * 受渡日
     * @@return double
     * @@throws WEB3SystemLayerException
     */
    public double getOtherTradingPowerForCheck(WEB3GentradeSubAccount l_subAccount, Date l_datDeliveryDate)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getOtherTradingPowerForCheck(WEB3GentradeSubAccount, Date)";
        log.entering(STR_METHOD_NAME);
    
        //引数.補助口座、引数.受渡日がnullの場合
        if (l_subAccount == null || l_datDeliveryDate == null)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    
        //口座IDを取得
        long l_lngAccountId = l_subAccount.getAccountId();
    
        //顧客オブジェクトを取得
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //信用口座開設区分を取得
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
    
        try
        {
            /*
             * 余力計算条件オブジェクトを生成する
             */
            WEB3TPCalcCondition l_calcCond = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);
    
            //受渡日が当日(T+0)以前だった場合
            if (WEB3DateUtility
                .compareToDay(l_datDeliveryDate, l_calcCond.getBizDate(WEB3TPSpecifiedPointDef.T_0))
                < 0)
            {
                //エラーをスロー
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            //受渡日がT+5以降だった場合
            else if (
                WEB3DateUtility.compareToDay(
                    l_datDeliveryDate,
                    l_calcCond.getBizDate(WEB3TPSpecifiedPointDef.T_5))
                    > 0)
            {
                //余力計算基準日<その他買付>=T+5をセット
                l_calcCond.setOtherBasePoint(WEB3TPSpecifiedPointDef.T_5);
            }
            //以外(受渡日が、T+0～T+5の間)の場合
            else
            {
                //受渡日に対応する指定日を取得
                int l_intDeliDate = l_calcCond.calcSpecifiedPoint(l_datDeliveryDate);
    
                //余力計算基準日<その他買付>=指定日をセット
                l_calcCond.setOtherBasePoint(l_intDeliDate);
            }
    
            //現物顧客の場合
            if (l_blnMargin == false)
            {
                //余力計算結果(List)を取得
                List l_lisCalcResult =
                    WEB3TPTradingPowerCalcEquity.findCalcResultEquityParams(l_lngAccountId);
    
                //資産余力情報
                WEB3TPTradingPowerCalcEquity l_calcEquity =
                    new WEB3TPTradingPowerCalcEquity(l_lisCalcResult, l_calcCond);
                //余力計算結果を取得
                WEB3TPCalcResult l_result = l_calcEquity.calcAppliedOtherTradingPower(null);
                log.debug(l_result.toString());
    
                //可能額を返却
                log.exiting(STR_METHOD_NAME);
                return l_result.tradingPower;
            }
            //信用顧客の場合
            else
            {
                //余力計算結果(List)を取得
                List l_lisCalcResult =
                    WEB3TPTradingPowerCalcMargin.findCalcResultMarginParams(l_lngAccountId);
    
                //資産余力情報
                WEB3TPTradingPowerCalcMargin l_calcMargin =
                    new WEB3TPTradingPowerCalcMargin(l_lisCalcResult, l_calcCond);
                //余力計算結果を取得
                WEB3TPCalcResult l_result = l_calcMargin.calcAppliedOtherTradingPower(null);
                log.debug(l_result.toString());
    
                //可能額を返却
                log.exiting(STR_METHOD_NAME);
                return l_result.tradingPower;
            }
        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (get外貨残高情報) <BR>
     * <BR>
     * シーケンス図「(取引余力サービス)get外貨残高情報」参照 <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_strCurrencyCode - (通貨コード)<BR>
     * 通貨コード<BR>
     * @@return WEB3ForeignPositionContract
     * @@throws WEB3SystemLayerException
     */
    public WEB3ForeignPositionContract getForeignPositionContract(
        WEB3GentradeSubAccount l_subAccount, String l_strCurrencyCode)
            throws WEB3SystemLayerException
    {

        final String STR_METHOD_NAME = "getForeignPositionContract(WEB3GentradeSubAccount, String)";
        log.entering(STR_METHOD_NAME);

        //引数.補助口座がnullの場合
        if (l_subAccount == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        // 外貨残高情報を取得する
        // [引数]
        //  口座ID：引数.補助口座.getMainAccountId()
        //  通貨コード：引数.通貨コード

        WEB3ForeignPositionContract l_foreignPositionContract =
            WEB3ForeignPositionContract.createForeignPositionContract(
            l_subAccount.getMainAccount().getAccountId(),
            l_strCurrencyCode);

        log.exiting(STR_METHOD_NAME);
        return l_foreignPositionContract;
    }
    
    /**
     * (get担保ローン振替可能額)<BR>
     * <BR>
     * 証券担保ローン実施顧客について、預り金返済額（オリックスクレジット）を返却する。<BR>
     * <BR>
     * シーケンス図「(取引余力サービス)get担保ローン振替可能額」参照 <BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座
     * @@param l_datDeliveryDate - (受渡日)<BR>
     * 受渡日
     * @@return double
     * @@throws WEB3SystemLayerException
     */
    public double getSLChangePossAmt(WEB3GentradeSubAccount l_subAccount, Date l_datDeliveryDate)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getSLChangePossAmt(WEB3GentradeSubAccount, Date)";
        log.entering(STR_METHOD_NAME);

        //引数.補助口座がnull の場合
        if (l_subAccount == null)
        {
            //エラーをスロー
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //getMainAccount()
        //引数.補助口座より、顧客オブジェクトを取得する。
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //is信用口座開設(String)
        //信用口座を開設しているかどうかの判別を行う。
        //true：　@開設済
        //false：　@未開設
        //[引数]
        //弁済区分：　@0(指定なし)
        boolean l_blnIsMarginAccountEstablished =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //(*)分岐フロー
        //is信用口座開設()の戻り値 = true(信用顧客)
        //の場合は以下の処理を実施する。
        if (l_blnIsMarginAccountEstablished)
        {
            //エラーをスロー
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //create余力計算条件<標準>(補助口座)
        //余力計算条件を生成する。
        //[引数]
        //補助口座：引数.補助口座
        //余力計算条件オブジェクトを生成する。
        WEB3TPCalcCondition l_calcCond =
            WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

        //calc指定日(Date)
        //引数.受渡日に対応する指定日を取得する。
        //[引数]
        //Date：　@引数.受渡日
        int l_intSpecifiedPoint = l_calcCond.calcSpecifiedPoint(l_datDeliveryDate);

        //set余力計算基準日<その他買付>(int)
        //余力計算基準日<その他買付>の値を引数.受渡日の基準日にセットし直す。
        //[引数]
        //int：　@calc指定日()の戻り値
        l_calcCond.setOtherBasePoint(l_intSpecifiedPoint);

        //find余力計算結果<現物顧客>～口座ＩＤ指定～(long)
        //余力計算結果<現物顧客>Paramsを取得する。
        //[引数]
        //long:引数.補助口座.getMainAccountId()
        List l_lisCalcResults = WEB3TPTradingPowerCalcEquity.findCalcResultEquityParams(
            l_subAccount.getMainAccount().getAccountId());

        //資産余力情報<現物顧客>(List, 余力計算条件)
        //資産余力情報<現物顧客>オブジェクトを生成する。
        //[引数]
        //余力計算結果Params：find余力計算結果<現物顧客>Params()の戻り値
        //余力計算条件：　@create余力計算条件()の戻り値
        WEB3TPTradingPowerCalcEquity l_tpTradingPowerCalcEquity =
            new WEB3TPTradingPowerCalcEquity(l_lisCalcResults, l_calcCond);

        //calc適用その他商品買付可能額(OrderTypeEnum)
        //最小のその他商品買付可能額を計算する。
        //[引数]
        //OrderTypeEnum = 1020：振替注文（預り金からオリックスクレジット）
        WEB3TPCalcResult l_tpCalcResult =
            l_tpTradingPowerCalcEquity.calcAppliedOtherTradingPower(OrderTypeEnum.TO_ORIX_CREDIT);

        //余力計算結果.取引可能額を0補正して返却する。
        //返却値=MAX(余力計算結果.取引可能額, 0)
        //※余力計算結果 = calc適用その他商品買付可能額()の戻り値
        double l_dblMax = Math.max(l_tpCalcResult.tradingPower, 0);

        log.exiting(STR_METHOD_NAME);
        return l_dblMax;
    }

    /**
     * (validate建玉強制決済～オンライン開始前～) <BR>
     * <BR>
     * 強制決済の対象顧客か判定を行い、<BR>
     * 結果を「建玉強制決済結果」オブジェクトに設定し、返却する。 <BR>
     * <BR>
     * ※シーケンス図「(取引余力サービス)validate建玉強制決済～オンライン開始前～」参照<BR>
     * ==================================================================== <BR>
     * シーケンス図 ：((取引余力サービス)validate建玉強制決済～オンライン開始前～) <BR>
     * 具体位置：(is信用口座開設()の戻り値　@=　@false(現物顧客)の場合)<BR>
     * class: WEB3BusinessLayerException <BR>
     * 　@tag: BUSINESS_ERROR_02887 <BR>
     * ==================================================================== <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@return WEB3TPContractForcedSettleResult
     * @@throws WEB3BaseException
     */
    public WEB3TPContractForcedSettleResult validateContractForcedSettleBefOnline(
        WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateContractForcedSettleBefOnline(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        //引数.補助口座がnullの場合
        if (l_subAccount == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //getMainAccount( )
        //引数.補助口座より、顧客オブジェクトを取得する。
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //is信用口座開設(弁済区分 : String)
        //信用口座を開設しているかどうかの判別を行う。
        //true：　@開設済
        //false：　@未開設
        //[引数]
        //弁済区分：　@0(指定なし)
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //is信用口座開設()の戻り値　@=　@false(現物顧客)の場合
        if (l_blnMargin == false)
        {
            //例外をスロ－
            log.debug("信用口座開設なし。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02887,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "信用口座開設なし。");
        }

        //create入金請求管理(顧客)
        WEB3TPPaymentRequisitionManagement l_management =
            WEB3TPPaymentRequisitionManagement.createPaymentRequisitionManagement(l_mainAccount);

        //建玉強制決済結果
        WEB3TPContractForcedSettleResult l_settleResult = new WEB3TPContractForcedSettleResult();

        //get第一水準追証経過日数( )
        //第一水準追証経過日数を取得する。
        int l_intFirstAdddepositPassDay = l_management.getFirstAdddepositPassDay();

        //get第一水準追証有効経過日数( )
        //第一水準追証有効経過日数を取得する。
        int l_intFirstAdddepositPassDayValid = l_management.getFirstAdddepositPassDayValid();

        //(*1)第一水準追証経過日数　@>　@(*2)第一水準追証有効経過日数
        //(*1)get第一水準追証経過日数の戻り値
        //(*2)get第一水準追証有効経過日数の戻り値
        if (l_intFirstAdddepositPassDay > l_intFirstAdddepositPassDayValid)
        {
            //建玉強制決済結果を返却
            //建玉強制決済結果オブジェクトの属性にセットする。
            //・判定フラグ　@=　@true
            l_settleResult.resultFlg = true;
            //・強制決済理由　@=　@"2"：オンライン開始前(軽度)
            l_settleResult.forcedSettleReason = WEB3TPForcedSettleReasonDef.BEFORE_ONLINE_SLIGHTNESS;
            //・追証発生日　@=　@入金請求管理.get第一水準追証発生日
            l_settleResult.additionalMarginDate = l_management.getFirstAdddepositOccurredDate();
            //・追証発生日からの経過日数　@=　@入金請求管理.get第一水準追証経過日数
            l_settleResult.additionalMarginAccruedDays = new Integer(l_management.getFirstAdddepositPassDay());
            //・保証金預託率　@=　@入金請求管理.get第一水準追証保証金率
            l_settleResult.marginMaintenanceRate =
                new Double(l_management.getFirstAdddepositMarginDepositRate());

            log.exiting(STR_METHOD_NAME);
            //建玉強制決済結果を返却
            return l_settleResult;
        }

        //get第二水準追証金額（未入金）( )
        //第二水準追証金額（未入金）を取得する。
        double l_dblSecondAdddepositDepositNonPay = l_management.getSecondAdddepositDepositNonPay();

        //(*1)第二水準追証金額(未入金)　@>　@0　@の場合
        //(*1)get第二水準追証金額(未入金)の戻り値
        if (l_dblSecondAdddepositDepositNonPay > 0)
        {
            //建玉強制決済結果を返却
            //建玉強制決済結果オブジェクトの属性にセットする。 
            //・判定フラグ　@=　@true
            l_settleResult.resultFlg = true;
            //・強制決済理由　@=　@"4"：オンライン開始前(法@定)
            l_settleResult.forcedSettleReason = WEB3TPForcedSettleReasonDef.BEFORE_ONLINE_LEGAL;
            //・追証発生日　@=　@null
            l_settleResult.additionalMarginDate = null;
            //・追証発生日からの経過日数　@=　@null
            l_settleResult.additionalMarginAccruedDays = null;
            //・保証金預託率　@=　@null
            l_settleResult.marginMaintenanceRate = null;

            log.exiting(STR_METHOD_NAME);
            //建玉強制決済結果を返却
            return l_settleResult;
        }

        //建玉強制決済結果を返却
        //建玉強制決済結果オブジェクトの属性にセットする。
        //・判定フラグ　@=　@false
        l_settleResult.resultFlg = false;
        //・強制決済理由　@=　@null
        l_settleResult.forcedSettleReason = null;
        //・追証発生日　@=　@null
        l_settleResult.additionalMarginDate = null;
        //・追証発生日からの経過日数　@=　@null
        l_settleResult.additionalMarginAccruedDays = null;
        //・保証金預託率　@=　@null
        l_settleResult.marginMaintenanceRate = null;

        log.exiting(STR_METHOD_NAME);
        //建玉強制決済結果を返却
        return l_settleResult;
    }

    /**
     * (validate建玉強制決済～場間～) <BR>
     * validate建玉強制決済～場間～<BR>
     * <BR>
     * 強制決済の対象顧客か判定を行い、結果を「建玉強制決済結果」オブジェクトに設定し、<BR>
     * 　@返却する。<BR>
     * <BR>
     * ※シーケンス図「(取引余力サービス)validate建玉強制決済～場間～」参照<BR>
     * ================================================================= <BR>
     * シーケンス図 ：((取引余力サービス)validate建玉強制決済～場間～) <BR>
     * 具体位置：(is信用口座開設()の戻り値　@=　@false(現物顧客)の場合)<BR>
     * class: WEB3BusinessLayerException <BR>
     * 　@tag: BUSINESS_ERROR_02887 <BR>
     * ================================================================= <BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@return WEB3TPContractForcedSettleResult
     * @@throws WEB3BaseException
     */
    public WEB3TPContractForcedSettleResult validateContractForcedSettleIntermission(
        WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateContractForcedSettleIntermission(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        //引数.補助口座がnullの場合
        if (l_subAccount == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //getMainAccount( )
        //引数.補助口座より、顧客オブジェクトを取得する。
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //is信用口座開設(弁済区分 : String)
        //信用口座を開設しているかどうかの判別を行う。
        //true：　@開設済
        //false：　@未開設
        //[引数]
        //弁済区分：　@0(指定なし)
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //is信用口座開設()の戻り値　@=　@false(現物顧客)の場合
        if (l_blnMargin == false)
        {
            //例外をスロ－
            log.debug("信用口座開設なし。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02887,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "信用口座開設なし。");
        }

        //create入金請求管理(顧客)
        //入金請求管理オブジェクトを生成する。
        //[引数]
        //顧客：　@getMainAccount()の戻り値
        WEB3TPPaymentRequisitionManagement l_management =
            WEB3TPPaymentRequisitionManagement.createPaymentRequisitionManagement(l_mainAccount);

        //建玉強制決済結果()
        //建玉強制決済結果オブジェクトを生成する。
        WEB3TPContractForcedSettleResult l_settleResult = new WEB3TPContractForcedSettleResult();

        //create第二水準追証未解消情報( )
        //第二水準追証未解消情報を生成する。
        WEB3TPSecondAdddepositNotClearInfo l_secondAdddepositNotClearInfo =
            l_management.createSecondAdddepositNotClearInfo();

        //(*1)第二水準追証金額(請求2)　@>　@0の場合
        //(*1)第二水準追証未解消情報.追証金額(請求2)
        if (l_secondAdddepositNotClearInfo.secondDeposit2 > 0)
        {
            //建玉強制決済結果オブジェクトの属性にセットする。
            //・判定フラグ　@=　@true
            l_settleResult.resultFlg = true;
            //・強制決済理由　@=　@"3"：場間
            l_settleResult.forcedSettleReason = WEB3TPForcedSettleReasonDef.TRADING_TIME_ZONE;
            //・追証発生日　@=　@入金請求管理.get第二水準追証発生日(請求2)
            l_settleResult.additionalMarginDate = l_management.getSecondAdddepositDepositOccurredDate2();
            //・追証発生日からの経過日数　@=　@"3"：固定値
            l_settleResult.additionalMarginAccruedDays = new Integer(3);
            //・保証金預託率　@=　@入金請求管理.get第二水準追証保証金率(請求2)
            l_settleResult.marginMaintenanceRate =
                new Double(l_management.getSecondAdddepositMarginDepositRate2());

            log.exiting(STR_METHOD_NAME);
            //建玉強制決済結果オブジェクトを返却する。
            return l_settleResult;
        }

        //建玉強制決済結果オブジェクトの属性にセットする。
        //判定フラグ　@=　@false
        l_settleResult.resultFlg = false;
        //強制決済理由　@=　@null
        l_settleResult.forcedSettleReason = null;
        //追証発生日　@=　@null
        l_settleResult.additionalMarginDate = null;
        //追証発生日からの経過日数　@=　@null
        l_settleResult.additionalMarginAccruedDays = null;
        //保証金預託率　@=　@null
        l_settleResult.marginMaintenanceRate = null;

        log.exiting(STR_METHOD_NAME);
        //建玉強制決済結果オブジェクトを返却する。
        return l_settleResult;
    }

    /**
     * (is受入保証金占有率超過)<BR>
     * <BR>
     * is受入保証金占有率超過を実施する。<BR>
     * <BR>
     * シーケンス図「(取引余力サービス)is受入保証金占有率超過」参照<BR>
     * <BR>
     * @@param l_lngProductId - (銘柄ID)<BR>
     * 銘柄ID<BR>
     * @@param l_tpUpd - (余力更新)<BR>
     * 余力更新<BR>
     * @@param l_tpCalcMargin - (資産余力情報<信用顧客>)<BR>
     * 資産余力情報<信用顧客><BR>
     * @@return boolean
     */
    protected boolean isReceiptDepositRateOver(
        long l_lngProductId,
        WEB3TPTradingPowerUpd l_tpUpd,
        WEB3TPTradingPowerCalcMargin l_tpCalcMargin)
    {
        final String STR_METHOD_NAME =
            "isReceiptDepositRateOver(long, WEB3TPTradingPowerUpd, WEB3TPTradingPowerCalcMargin)";
        log.entering(STR_METHOD_NAME);

        //get余力計算条件()
        WEB3TPCalcCondition l_tpCalcCondition = l_tpCalcMargin.getCalcCondition();

        //get会社部店別余力計算条件(String)
        String l_strReceiptMarginDepositHighlimitRate =
            l_tpCalcCondition.getInstBranCalcCondition(
                WEB3BranchPreferencesNameDef.RECEIPT_MARGIN_DEPOSIT_HIGHLIMIT_RATE);

        //(*)分岐フロー
        if (l_strReceiptMarginDepositHighlimitRate == null)
        {
            log.debug("get会社部店別余力計算条件()の戻り値 = null");
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //受入保証金占有率の上限値
        double l_dblReceiptDepositRateMax = Double.parseDouble(l_strReceiptMarginDepositHighlimitRate);
        log.debug("受入保証金占有率の上限値 = " + l_dblReceiptDepositRateMax);

        if (GtlUtils.Double.isZero(l_dblReceiptDepositRateMax))
        {
            log.debug("get会社部店別余力計算条件()の戻り値 = 0");
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //(*)分岐フロー
        if (l_dblReceiptDepositRateMax > 0)
        {
            //get建玉情報()
            WEB3TPContractInfo l_tpContractInfo = l_tpUpd.getContractInfo();

            //get営業日(int)
            Date l_datBizDate = l_tpCalcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_5);

            //calc銘柄ごと必要保証金(Date, long)
            double l_dblProductMarginDeposit = l_tpContractInfo.calcProductMarginDeposit(l_datBizDate, l_lngProductId);
            BigDecimal l_bdProductMarginDeposit = new BigDecimal(l_dblProductMarginDeposit + "");

            //calc受入保証金(int)
            double l_dblReceiptMarginDeposit = l_tpCalcMargin.calcReceiptMarginDeposit(WEB3TPSpecifiedPointDef.T_5);
            BigDecimal l_bdReceiptMarginDeposit = new BigDecimal(l_dblReceiptMarginDeposit + "");
            log.debug("受入保証金(T+5) = " + l_dblReceiptMarginDeposit);

            //(*)分岐フロー
            //calc受入保証金() <= 0 の場合
            if (l_dblReceiptMarginDeposit < 0 || GtlUtils.Double.isZero(l_dblReceiptMarginDeposit))
            {
                //calc銘柄ごと必要保証金() > 0 の場合
                if (l_dblProductMarginDeposit > 0)
                {
                    log.exiting(STR_METHOD_NAME);
                    return true;
                }
                //calc銘柄ごと必要保証金() <= 0 の場合
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    return false;
                }
            }

            //（*）受入保証金占有率と受入保証金占有率の上限値を計算する。
            //受入保証金占有率
            BigDecimal l_bdReceiptDepositRate = l_bdProductMarginDeposit.multiply(
                new BigDecimal("100")).divide(
                    l_bdReceiptMarginDeposit,
                    2,
                    BigDecimal.ROUND_UP);
            double l_dblReceiptDepositRate = l_bdReceiptDepositRate.doubleValue();
            log.debug(l_lngProductId + "の受入保証金占有率 = " + l_dblReceiptDepositRate);

            //(*)分岐フロー
            if (l_dblReceiptDepositRate < l_dblReceiptDepositRateMax
                || GtlUtils.Double.isEqual(l_dblReceiptDepositRate, l_dblReceiptDepositRateMax))
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (get翌日不足金)<BR>
     * (get翌日不足金) <BR>
     * <BR>
     * シーケンス図「(取引余力サービス)get翌日不足金」参照 <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    public double getNextBizDateShortfall(WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getNextBizDateShortfall(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        double l_dblNextBizDateShortfall = 0;
        //getMainAccount( )
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //is信用口座開設(String)
        //[引数]
        //弁済区分：　@0(指定なし)
        boolean l_blnIsMarginAccountOpen =
            l_mainAccount.isMarginAccountEstablished( WEB3GentradeRepaymentDivDef.DEFAULT);

        if (l_blnIsMarginAccountOpen)
        {
            //create余力計算条件<標準>(補助口座 : 補助口座)
            WEB3TPCalcCondition l_tpCalcCondition =
                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);
            //find余力計算結果<信用顧客>～口座ＩＤ指定～(口座ID : long)
            List l_lisTpCalcResultMarginParams =
                WEB3TPTradingPowerCalcMargin.findCalcResultMarginParams(l_subAccount.getAccountId());
            //資産余力情報<信用顧客>(余力計算結果<信用顧客> : List, 余力計算条件 : 余力計算条件)
            //[引数]
            //余力計算結果Params：find余力計算結果<現物顧客>Params()の戻り値
            //余力計算条件：　@create余力計算条件()の戻り値
            WEB3TPTradingPowerCalcMargin l_tradingPowerCalcMargin =
                new WEB3TPTradingPowerCalcMargin(l_lisTpCalcResultMarginParams, l_tpCalcCondition);
            //calc翌日不足金(補助口座 : 補助口座, 資産余力情報<信用顧客>)
            l_dblNextBizDateShortfall = this.calcNextBizDateShortfall(l_subAccount, l_tradingPowerCalcMargin);
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblNextBizDateShortfall;
    }

    /**
     * （calc翌日不足金）<BR>
     * （calc翌日不足金）<BR>
     * <BR>
     * 顧客の「翌日不足金」を返却する。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_tradingPowerCalcMargin - （資産余力情報<信用顧客>）<BR>
     * 資産余力情報<信用顧客><BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    public double calcNextBizDateShortfall(
        WEB3GentradeSubAccount l_subAccount,
        WEB3TPTradingPowerCalcMargin l_tradingPowerCalcMargin)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcNextBizDateShortfall(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        //システム日付を取得する。
        //システム日付（当日）
        //システム日付（翌営業日）
        Date l_datSystemDay = l_tradingPowerCalcMargin.getCalcCondition().getBizDate(0);
        Date l_datNextBizDate = l_tradingPowerCalcMargin.getCalcCondition().getBizDate(1);

        //顧客マスタを取得する。
        //客マスタ = 引数.補助口座.getMainAccount()
        MainAccount l_mainAccount = l_subAccount.getMainAccount();

        //余力計算結果に間している計算項目を取得する。
        //余力計算結果を取得する。
        //余力計算結果テーブル（信用顧客）  = 資産余力情報<信用顧客>.get余力計算結果Params<信用顧客>
        TpCalcResultMarginParams l_tpCalcResultMarginParams = l_tradingPowerCalcMargin.getCalcResultMargin();

        //余力計算結果詳細テーブル（信用顧客）    = 資産余力情報<信用顧客>.get余力計算結果詳細Params<信用顧客>
        TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = l_tradingPowerCalcMargin.getCalcResultDetailMargin();
        
        //余力計算結果に間している計算項目
        //預り金（T+0）      = 余力計算結果テーブル（信用顧客）.預り金残高（T+0）
        double l_dblAccountBalance0 = l_tpCalcResultMarginParams.getAccountBalance0();

        //預り金（T+1）      = 余力計算結果テーブル（信用顧客）.預り金残高（T+1）
        double l_dblAccountBalance1 = l_tpCalcResultMarginParams.getAccountBalance1();

        //当日約定済代金（T+0）  = 余力計算結果テーブル（信用顧客）.当日約定済代金（T+0）
        double l_dblTodayExecutedAmount0 = l_tpCalcResultMarginParams.getTodayExecutedAmount0();

        //当日約定済代金（T+1）  = 余力計算結果テーブル（信用顧客）.当日約定済代金（T+1）
        double l_dblTodayExecutedAmount1 = l_tpCalcResultMarginParams.getTodayExecutedAmount1();

        //当日未約定代金（T+0）  = 余力計算結果テーブル（信用顧客）.当日未約定代金（T+0）
        double l_dblTodayUnexecutedAmount0 = l_tpCalcResultMarginParams.getTodayUnexecutedAmount0();

        //当日未約定代金（T+1）    = 余力計算結果テーブル（信用顧客）.当日未約定代金（T+1）
        double l_dblTodayUnexecutedAmount1 = l_tpCalcResultMarginParams.getTodayUnexecutedAmount1();
        
        //日計り拘束金（T+1）       = 余力計算結果テーブル（信用顧客）.日計り拘束金（T+1）
        double l_dblDayTradeRestraint1 = l_tpCalcResultMarginParams.getDayTradeRestraint1();

        //代用証券評価額（T+1）  = 余力計算結果テーブル（信用顧客）.代用証券評価額（T+1）
        double l_dblSubstituteSecurityAsset1= l_tpCalcResultMarginParams.getSubstituteSecurityAsset1();

        //未決済建玉代金（T+1）  = 余力計算結果テーブル（信用顧客）.未決済建玉代金（T+1）
        double l_dblContractAmount1= l_tpCalcResultMarginParams.getContractAmount1();

        //未受渡建玉代金（T+1）  = 余力計算結果テーブル（信用顧客）.未受渡建玉代金（T+1）
        double l_dblUndeliContractAmount1 = l_tpCalcResultMarginParams.getUndeliContractAmount1();

        //現金必要保証金（T+1）  = 余力計算結果テーブル（信用顧客）.現金必要保証金（T+1）
        double l_dblCashMarginDeposit1 = l_tpCalcResultMarginParams.getCashMarginDeposit1();

        //未決済建玉評価損益（T+1）    = 余力計算結果テーブル（信用顧客）.未決済建玉評価損益（T+1）
        double l_dblContractAssetProfitLoss1 = l_tpCalcResultMarginParams.getContractAssetProfitLoss1();

        //建玉諸経費（T+1）        = 余力計算結果テーブル（信用顧客）.建玉諸経費（T+1）
        double l_dblContractTotalCost1 = l_tpCalcResultMarginParams.getContractTotalCost1();

        //未受渡建玉決済損（T+1） = 余力計算結果テーブル（信用顧客）.未受渡建玉決済損（T+1）
        double l_dblUndeliContractLoss1 = l_tpCalcResultMarginParams.getUndeliContractLoss1();

        //即日入金対象銘柄拘束金(T+0)　@= 余力計算結果詳細テーブル（信用顧客）.即日入金対象銘柄拘束金(T+0)
        double l_dbTodayDepFundRestraint0 = l_tpCalcResultMarginDetailParams.getTodayDepFundRestraint0();
        
        //即日入金対象銘柄拘束金(T+1)　@= 余力計算結果詳細テーブル（信用顧客）.即日入金対象銘柄拘束金(T+1)
        double l_dbTodayDepFundRestraint1 = l_tpCalcResultMarginDetailParams.getTodayDepFundRestraint1();

        SubAccount l_eqtypeMarSubAccount = null;
        try
        {
            //顧客勘定残高に間している計算項目を取得する。
            //補助口座 = 顧客マスタ.getSubAccount(“2:株式信用取引口座（保証金）”)
            l_eqtypeMarSubAccount =
                l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //顧客勘定残高(マスタ情報)=余力データソースアクセス管理.get顧客勘定残高(マスタ情報) (口座ID, 補助口座ID)
        TpCashBalanceRow l_tpCashBalanceRow =
            WEB3TPPersistentDataManager.getInstance().getTpCashBalanceRow(
                l_mainAccount.getAccountId(), l_eqtypeMarSubAccount.getSubAccountId());

        //顧客勘定残高(マスタ情報)に間している計算項目
        //[a. 顧客勘定残高（マスタ情報）.残高（当日+　@０日） == nullの場合]
        //残高（T+0） = 0
        // 以外　@の場合
        //残高（T+0） = 顧客勘定残高（マスタ情報）.（当日+　@０日）
        double l_dblTpCashBalance0 = 0.0;
        if (l_tpCashBalanceRow != null)
        {
            l_dblTpCashBalance0 = l_tpCashBalanceRow.getCashBalance0();
        }

        //顧客勘定残高（マスタ情報）.残高（当日+　@１日） == nullの場合
        //残高（T+1） = 0
        //以外の場合:残高（T+1） = 顧客勘定残高（マスタ情報）.残高（当日+　@１日）
        double l_dblTpCashBalance1 = 0.0;
        if (l_tpCashBalanceRow != null)
        {
            l_dblTpCashBalance1 = l_tpCashBalanceRow.getCashBalance1();
        }

        double l_dblMarginMaintenanceRate =
            l_tradingPowerCalcMargin.getCalcCondition().getMarginMentenanceRate() / 100.0;

        //最低必要保証金   = 部店.最低必要保証金
        double l_dblMinMarginDeposit =
            l_tradingPowerCalcMargin.getCalcCondition().getMinMarginDeposit();

        //保証金振替に間している計算項目を取得する。
        //入出金注文単位テーブルを以下の条件で検索する。
        //口座ID = 補助口座.getAccountId()
        //補助口座ID = 補助口座.getSubAccountId()
        //注文種別 IN(“1005:預り金から信用保証金”,“1006:信用保証金から預り金”)
        //発注日 >= システム日付（当日）
        //注文状態 NOT IN(“6:発注失敗(新規注文)”,“14:発注済(取消注文)”)
        List l_lisAioOrderUnitDeposits = null;
        QueryProcessor l_queryProcessor = null;
        try
        {
            l_queryProcessor = Processors.getDefaultProcessor();

            StringBuffer l_sbWhereDeposit = new StringBuffer();
            l_sbWhereDeposit.append(" account_id = ? ");
            l_sbWhereDeposit.append(" and sub_account_id = ? ");
            l_sbWhereDeposit.append(" and order_type in (?, ?) ");
            l_sbWhereDeposit.append(" and biz_date >= ? ");
            l_sbWhereDeposit.append(" and order_status not in(?, ?) ");
            Object[] l_aioWhereDeposits =
                {new Long(l_mainAccount.getAccountId()),
                new Long(l_eqtypeMarSubAccount.getSubAccountId()),
                OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE,
                OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT,
                WEB3DateUtility.formatDate(l_datSystemDay,"yyyyMMdd"),
                OrderStatusEnum.NOT_ORDERED,
                OrderStatusEnum.CANCELLED};

            l_lisAioOrderUnitDeposits = l_queryProcessor.doFindAllQuery(
                AioOrderUnitRow.TYPE,
                l_sbWhereDeposit.toString(),
                l_aioWhereDeposits);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3TPPaymentRequisitionManagement." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3TPPaymentRequisitionManagement." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //振替金(T+0以前)
        double l_dblTransferAmountT0 = 0.0;
        BigDecimal l_bdTransferAmountT0 = new BigDecimal(Double.toString(0.0));
        //振替金(T+1)
        double l_dblTransferAmountT1 = 0.0;
        BigDecimal l_bdTransferAmountT1 = new BigDecimal(Double.toString(0.0));

        //検索された入出金注文単位テーブルの行より振替金を集計する
        //項番 (=i)の範囲(0～“検索された行数“-1)で以下の処理を繰り返す。
        int l_intCount = l_lisAioOrderUnitDeposits.size();
        for (int i = 0 ; i < l_intCount; i++)
        {
            AioOrderUnitRow l_aioOrderUnitRow = (AioOrderUnitRow)l_lisAioOrderUnitDeposits.get(i);
            //入出金注文単位テーブル.受渡日 <= システム日付（当日） の場合
            if (WEB3DateUtility.compareToDay(l_aioOrderUnitRow.getDeliveryDate(), l_datSystemDay) <= 0)
            {
                //注文単位テーブル.注文種別 = “1005:預り金から信用保証金”の場合
                if (OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE.equals(
                    l_aioOrderUnitRow.getOrderType()))
                {
                    //振替金(T+0以前) = 振替金(T+0以前) + 注文単位テーブル.注文数量
                    l_bdTransferAmountT0 = l_bdTransferAmountT0.add(new BigDecimal(
                        Double.toString(l_aioOrderUnitRow.getQuantity())));
                }
                //注文単位テーブル.注文種別 = “1006:信用保証金から預り金”の場合
                else
                {
                    //振替金(T+0以前) = 振替金(T+0以前) - 注文単位テーブル.注文数量
                    l_bdTransferAmountT0 = l_bdTransferAmountT0.subtract(new BigDecimal(
                        Double.toString(l_aioOrderUnitRow.getQuantity())));
                }
            }

            //入出金注文単位テーブル.受渡日 = システム日付（翌営業日） の場合
            if (WEB3DateUtility.compareToDay(l_aioOrderUnitRow.getDeliveryDate(), l_datNextBizDate) == 0)
            {
                //注文単位テーブル.注文種別 = “1005:預り金から信用保証金”の場合
                if (OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE.equals(
                    l_aioOrderUnitRow.getOrderType()))
                {
                    //振替金(T+1) = 振替金(T+1) + 注文単位テーブル.注文数量
                    l_bdTransferAmountT1 = l_bdTransferAmountT1.add(new BigDecimal(
                        Double.toString(l_aioOrderUnitRow.getQuantity())));
                }
                //注文単位テーブル.注文種別 = “1006:信用保証金から預り金”の場合
                else
                {
                    //振替金(T+1) = 振替金(T+1) - 注文単位テーブル.注文数量
                    l_bdTransferAmountT1 = l_bdTransferAmountT1.subtract(new BigDecimal(
                        Double.toString(l_aioOrderUnitRow.getQuantity())));
                }
            }
        }
        l_dblTransferAmountT0 = l_bdTransferAmountT0.doubleValue();
        l_dblTransferAmountT1 = l_bdTransferAmountT1.doubleValue();

        //SONAR側で、預り金の不足する額を計算する。(T+0)
        //SONAR預り金を計算する。
        //WEB現金保証金（T+0）   = 預り金（T+0） - 当日約定済代金（T+0） 
        //- 当日未約定代金（T+0） - 即日入金対象銘柄拘束金(T+0)
        double l_dblWebMarginAccountBalance = 
            new BigDecimal(Double.toString(l_dblAccountBalance0)).subtract(
                new BigDecimal(Double.toString(l_dblTodayExecutedAmount0))).subtract(
                    new BigDecimal(Double.toString(l_dblTodayUnexecutedAmount0))).subtract(
                        new BigDecimal(Double.toString(l_dbTodayDepFundRestraint0))).doubleValue();

        //SONAR現金保証金（T+0）   = 残高（T+0） + 振替金(T+0以前)
        double l_dblSonaMarginAccountBalance0 = new BigDecimal(
            Double.toString(l_dblTpCashBalance0)).add(
                new BigDecimal(Double.toString(l_dblTransferAmountT0))).doubleValue();

        //SONAR預り金（T+0）     = WEB現金保証金（T+0） - SONAR現金保証金（T+0）
        double l_dblSonaAccountBalance0 = 
            new BigDecimal(Double.toString(l_dblWebMarginAccountBalance)).subtract(
                new BigDecimal(Double.toString(l_dblSonaMarginAccountBalance0))).doubleValue();

        //SONAR預り金不足額を計算する。
        double l_dblUnlessSonaAccountBalance0 = Math.abs(Math.min(l_dblSonaAccountBalance0, 0));

        //保証金→預り金への振替額を計算する。(T+0)
        //SONAR預り金不足額(T+0) > 0 かつ、即日入金対象銘柄拘束金(T+0) > 0 の場合
        //預り金への振替額(T+0) = SONAR預り金不足額(T+0)
        //上記以外 の場合
        //預り金への振替額(T+0) = 0
        double l_dblToAccBalTraAmount0 = 0.0;
        if (l_dblUnlessSonaAccountBalance0 > 0 && l_dbTodayDepFundRestraint0 >0)
        {
            l_dblToAccBalTraAmount0 = l_dblUnlessSonaAccountBalance0;
        }

        //保証金維持余力を計算する。(T+1)
        //SONAR差入保証金(T+1)を計算する。
        //SONAR差入保証金(T+1) = ( 残高(T+1) + 振替金(T+1) + 振替金(T+0以前) ) 
        //+ 代用証券評価額(T+1) - 預り金への振替額(T+0)
        double l_dblPaidMarginDeposit1 = new BigDecimal(
            Double.toString(l_dblTpCashBalance1)).add(
                new BigDecimal(Double.toString(l_dblTransferAmountT1))).add(
                    new BigDecimal(Double.toString( l_dblTransferAmountT0))).add(
                        new BigDecimal(Double.toString( l_dblSubstituteSecurityAsset1))).subtract(
                            new BigDecimal(Double.toString( l_dblToAccBalTraAmount0))).doubleValue();

        //SONAR受入保証金(T+1)を計算する。
        //SONAR受入保証金(T+1)   = SONAR差入保証金(T+1) + MIN(未決済建玉評価損益(T+1), 0) 
        //- 建玉諸経費(T+1) - 未受渡建玉決済損(T+1)
        double l_dblSonaReceiptMarginDeposit1 = new BigDecimal(Double.toString(l_dblPaidMarginDeposit1)).add(
            new BigDecimal(Double.toString(Math.min(l_dblContractAssetProfitLoss1, 0)))).subtract(
                new BigDecimal(Double.toString(l_dblContractTotalCost1))).subtract(
                    new BigDecimal(Double.toString(l_dblUndeliContractLoss1))).doubleValue();

        //SONAR保証金維持余力(T+1)を計算する。
        //未決済建玉代金(T+1) + 未受渡建玉代金(T+1) = 0の場合
        //SONAR保証金維持余力(T+1) = SONAR受入保証金(T+1)
        double l_dblMarginMaintenancePower1 = 0.0;
        if (GtlUtils.Double.isZero(l_dblContractAmount1 + l_dblUndeliContractAmount1))
        {
            l_dblMarginMaintenancePower1 = l_dblSonaReceiptMarginDeposit1;
        }
        //未決済建玉代金(T+1) + 未受渡建玉代金(T+1) > 0の場合
        else if (l_dblContractAmount1 + l_dblUndeliContractAmount1 > 0)
        {
            //SONAR保証金維持余力(T+1) = Min(SONAR差入保証金(T+1) - 最低必要保証金,
            //SONAR受入保証金(T+1) - MAX(未決済建玉代金(T+1) 
            //       * 保証金維持率, 法@定最低必要保証金))
            l_dblMarginMaintenancePower1 = Math.min(
                new BigDecimal(Double.toString(l_dblPaidMarginDeposit1)).subtract(
                    new BigDecimal(Double.toString(l_dblMinMarginDeposit))).doubleValue() ,
                new BigDecimal(Double.toString(l_dblSonaReceiptMarginDeposit1)).subtract(
                    new BigDecimal(Double.toString(Math.max(
                        new BigDecimal(Double.toString(l_dblContractAmount1)).multiply(
                            new BigDecimal(Double.toString(l_dblMarginMaintenanceRate))).doubleValue(), 
                        l_tradingPowerCalcMargin.getCalcCondition().getLegalMinMarginDeposit())))).doubleValue());
        }

        //SONAR側で、預り金の不足する額を計算する。(T+1)
        //SONAR預り金を計算する。
        //WEB現金保証金（T+1）    = 預り金（T+1） - 当日約定済代金（T+1） 
        //- 当日未約定代金（T+1） - 即日入金対象銘柄拘束金(T+1)
        double l_dblWebMarginAccountBalance1 = 
            new BigDecimal(Double.toString(l_dblAccountBalance1)).subtract(
                new BigDecimal(Double.toString(l_dblTodayExecutedAmount1))).subtract(
                    new BigDecimal(Double.toString(l_dblTodayUnexecutedAmount1))).subtract(
                        new BigDecimal(Double.toString(l_dbTodayDepFundRestraint1))).doubleValue();

        //SONAR現金保証金（T+1）  = 残高（T+1） + 振替金(T+1) + 振替金(T+0以前) 
        //- 預り金への振替額(T+0)
        double l_dblSonaMarginAccountBalance1 = 
            new BigDecimal(Double.toString(l_dblTpCashBalance1)).add(
                new BigDecimal(Double.toString(l_dblTransferAmountT1))).add(
                    new BigDecimal(Double.toString(l_dblTransferAmountT0))).subtract(
                        new BigDecimal(Double.toString(l_dblToAccBalTraAmount0))).doubleValue();

        //SONAR預り金（T+1）= WEB現金保証金（T+1） - SONAR現金保証金（T+1）
        double l_dblSonaAccountBalance1 = 
            new BigDecimal(Double.toString(l_dblWebMarginAccountBalance1)).subtract(
                new BigDecimal(Double.toString(l_dblSonaMarginAccountBalance1))).doubleValue();

        //SONAR預り金不足額を計算する。
        //SONAR預り金不足額（T+1）= ABS( MIN(SONAR預り金（T+1） - 日計り拘束金（T+1）, 0) )
        double l_dblUnlessSonaAccountBalance1 = Math.abs(Math.min(
            new BigDecimal(Double.toString(l_dblSonaAccountBalance1)).subtract(
                new BigDecimal(Double.toString(l_dblDayTradeRestraint1))).doubleValue(), 0));

        //保証金→預り金への振替額を計算する。(T+1)
        //SONAR預り金不足額(T+1) <= 0の場合
        double l_dblToAccBalTraAmount1 = 0.0;
        if (l_dblUnlessSonaAccountBalance1 <= 0)
        {
            //（SONAR預り金に十分お金があり、振り替えなくても立替金・特別立替金は発生しない）
            //0を返却する
            log.exiting(STR_METHOD_NAME);
            return 0D;
        }
        //SONAR預り金不足額(T+1) > 0の場合
        else
        {
            //SONAR現金保証金(T+1) - SONAR預り金不足額(T+1) - 現金必要保証金(T+1) >= 0 
            //and SONAR保証金維持余力(T+1) + SONAR 預り金(T+1) >= 0 の場合
            double l_dblSubAmount =  
                new BigDecimal(Double.toString(l_dblSonaMarginAccountBalance1)).subtract(
                    new BigDecimal(Double.toString(l_dblUnlessSonaAccountBalance1))).subtract(
                        new BigDecimal(Double.toString(l_dblCashMarginDeposit1))).doubleValue();

            double l_dblAddAmount = 
                new BigDecimal(Double.toString(l_dblMarginMaintenancePower1)).add(
                    new BigDecimal(Double.toString(l_dblSonaAccountBalance1))).doubleValue();
            if (l_dblSubAmount >= 0 && l_dblAddAmount >= 0)
            {
                //0を返却する
                log.exiting(STR_METHOD_NAME);
                return 0D;
            }
            //上記以外の場合
            else
            {
                //預り金への振替額(T+1) = MIN(ABS(MIN(SONAR 預り金(T+1), 0)),
                //MAX(MIN(SONAR保証金維持余力(T+1), SONAR現金保証金(T+1) 
                //        - 現金必要保証金(T+1)), 0))
                l_dblToAccBalTraAmount1 = Math.min(Math.abs(Math.min(l_dblSonaAccountBalance1, 0)),
                    Math.max(Math.min(l_dblMarginMaintenancePower1,
                        new BigDecimal(Double.toString(l_dblSonaMarginAccountBalance1)).subtract(
                            new BigDecimal(Double.toString(l_dblCashMarginDeposit1))).doubleValue()), 0));
            }
        }

        //翌日不足金を計算する。
        //翌日不足金 = ABS(MIN(SONAR 預り金(T+1) ＋ 預り金への振替額(T+1), 0)) 
        double l_dblNextBizDateShortfall = Math.abs(Math.min(
            new BigDecimal(Double.toString(l_dblSonaAccountBalance1)).add(
                new BigDecimal(Double.toString(l_dblToAccBalTraAmount1))).doubleValue(), 0D));

        BigDecimal l_bdNextBizDateShortfall = new BigDecimal(Double.toString(l_dblNextBizDateShortfall));

        l_dblNextBizDateShortfall =
            l_bdNextBizDateShortfall.setScale(0, BigDecimal.ROUND_DOWN).doubleValue();

        log.exiting(STR_METHOD_NAME);
        return l_dblNextBizDateShortfall;
    }

    /**
     * (get当日保証金引出余力)<BR>
     * <BR>
     * シーケンス図「(取引余力サービス)get当日保証金引出余力」参照<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_strDbCurrentPriceCheckDiv - (DB時価余力チェック区分)<BR>
     * 補助口座<BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    public double getTodayDepositWithdrawTradingPower(
        WEB3GentradeSubAccount l_subAccount, String l_strDbCurrentPriceCheckDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTodayDepositWithdrawTradingPower("
            + "WEB3GentradeSubAccount, String)";
        log.entering(STR_METHOD_NAME);

        //getMainAccount
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //is信用口座開設(弁済区分 : String)
        //弁済区分：　@0(指定なし)
        boolean l_blnIsMarginAccountOpen =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //is信用口座開設の戻り値＝FALSE（現物顧客）の場合
        if (!l_blnIsMarginAccountOpen)
        {
            log.exiting(STR_METHOD_NAME);
            return 0D;
        }
        //is信用口座開設の戻り値＝TRUE（信用顧客）の場合
        else
        {
            WEB3TPCalcCondition l_tpCalcCondition = null;
            //DB時価余力チェック区分=0（未実施）の場合
            if (WEB3DbCurrentPriceCheckDivDef.NOT_ENFORCEMENT.equals(l_strDbCurrentPriceCheckDiv))
            {
                //create余力計算条件<標準>(補助口座)
                l_tpCalcCondition =
                    WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);
            }
            //DB時価余力チェック区分=1（実施）の場合
            else if (WEB3DbCurrentPriceCheckDivDef.ENFORCEMENT.equals(l_strDbCurrentPriceCheckDiv))
            {
                //create余力計算条件<DB時価>(補助口座)
                l_tpCalcCondition =
                    WEB3TPCalcCondition.createCalcConditionDBQuote(l_subAccount);
            }

            //find余力計算結果<信用顧客>～口座ＩＤ指定～
            List l_lisFindCalcResultMarginParams =
                WEB3TPTradingPowerCalcMargin.findCalcResultMarginParams(
                    l_subAccount.getAccountId());

            WEB3TPTradingPowerCalcMargin l_tpTradingPowerCalcMargin =
                new WEB3TPTradingPowerCalcMargin(
                    l_lisFindCalcResultMarginParams, l_tpCalcCondition);


            double l_dblCalcMarginDrawPower = 0;

            //即日入金対象銘柄拘束金( T + 0 ) > 0 の場合
            if (l_tpTradingPowerCalcMargin.getCalcResultDetailMargin().getTodayDepFundRestraint0() > 0)
            {
                l_dblCalcMarginDrawPower =
                    l_tpTradingPowerCalcMargin.calcMarginDrawPower(WEB3TPSpecifiedPointDef.T_0);
            }         

            log.exiting(STR_METHOD_NAME);
            return l_dblCalcMarginDrawPower;
        }
    }

    /**
     * (validate取引余力<外国株式買付>)<BR>
     * <BR>
     * 外国株式買付において、現注文内容を取り込み預り金チェックを実施する。<BR> 
     * <BR>
     * シーケンス図「(取引余力サービス)validate取引余力<外国株式買付>」参照<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_newOrderSpecs - (現注文内容)<BR>
     * 現注文内容<BR>
     * @@param l_blnUpdateFlg - (余力更新フラグ)<BR>
     * 余力更新フラグ<BR>
     * trueの時、余力再計算処理を実施する<BR>
     * falseの時、余力再計算処理を実施しない<BR>
     * @@return WEB3TPTradingPowerResult
     * @@throws WEB3BaseException
     */
    protected WEB3TPTradingPowerResult validateTradingPowerFeqBuy(
        WEB3GentradeSubAccount l_subAccount,
        WEB3TPNewOrderSpec[] l_newOrderSpecs,
        boolean l_blnUpdateFlg)  throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateTradingPowerFeqBuy("
            + "WEB3GentradeSubAccount, WEB3TPNewOrderSpec[], boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null
            || l_newOrderSpecs == null
            || l_newOrderSpecs.length == 0)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        //getAccountId
        long l_lngAccountId = l_subAccount.getAccountId();

        //getMainAccount
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //is信用口座開設(弁済区分 : String)
        //弁済区分：　@0(指定なし)
        boolean l_blnIsMarginAccountOpen =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //create余力計算条件<標準>(補助口座)
        WEB3TPCalcCondition l_tpCalcCondition =
            WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

        //分岐フロー
        if (l_tpCalcCondition.isTradingStop())
        {
            //取引余力結果オブジェクトを生成
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();

            //判定フラグをセット
            l_tpResult.setResultFlg(false);
            //取引可能額をセット
            l_tpResult.setTradingPower(0);

            /*
             * 取引余力エラー情報を生成
             */
            WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
            l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.TRADING_STOP_ERROR;
            l_tpErrorInfo.lackAccountBalance = 0;
            l_tpErrorInfo.buyOrderPossibleAmount = 0;
            l_tpErrorInfo.sellOrderPossibleQuantity = 0;
            l_tpErrorInfo.marginSecInfo = null;

            //取引余力エラー情報をセット
            l_tpResult.setTpErrorInfo(l_tpErrorInfo);

            //取引余力結果を返却する
            log.exiting(STR_METHOD_NAME);
            return l_tpResult;
        }

        //分岐フロー
        if (l_tpCalcCondition.isOtherTradingStop())
        {
            //取引余力結果オブジェクトを生成
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();

            //判定フラグをセット
            l_tpResult.setResultFlg(false);
            //取引可能額をセット
            l_tpResult.setTradingPower(0);

            /*
             * 取引余力エラー情報を生成
             */
            WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
            l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.OTHER_TRADING_STOP_ERROR;
            l_tpErrorInfo.lackAccountBalance = 0;
            l_tpErrorInfo.buyOrderPossibleAmount = 0;
            l_tpErrorInfo.sellOrderPossibleQuantity = 0;
            l_tpErrorInfo.marginSecInfo = null;

            //取引余力エラー情報をセット
            l_tpResult.setTpErrorInfo(l_tpErrorInfo);

            //取引余力結果を返却する
            log.exiting(STR_METHOD_NAME);
            return l_tpResult;
        }

        //get受渡日
        Date l_datDeliveryDate = l_newOrderSpecs[0].getDeliveryDate();

        //calc指定日(Date)
        //Date = ''受渡日"
        int l_intSpecifiedPoint = 0;

        if (WEB3DateUtility.compareToDay(
            l_datDeliveryDate,
            l_tpCalcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_5))
            > 0)
        {
            //[a."受渡日" > T+5 の場合]
            //calc指定日()の戻り値を、T+5(=5)とする。
            l_intSpecifiedPoint = WEB3TPSpecifiedPointDef.T_5;
        }
        else if (WEB3DateUtility.compareToDay(
            l_tpCalcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0),
            l_datDeliveryDate)
            > 0)
        {
            //[a."受渡日" < T+0 の場合]
            //例外ををスローする。
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        else
        {
            l_intSpecifiedPoint = l_tpCalcCondition.calcSpecifiedPoint(l_datDeliveryDate);
        }

        //余力更新(long, boolean, 余力計算条件, 現注文内容[])
        //long = 補助口座.getAccountId()
        //boolean = 顧客.is信用口座開設()
        //余力計算条件 = create余力計算条件<標準>()
        //現注文内容[0] = 引数.現注文内容
        WEB3TPTradingPowerUpd l_tradingPowerUpd = new WEB3TPTradingPowerUpd(
            l_lngAccountId,
            l_blnIsMarginAccountOpen,
            l_tpCalcCondition,
            l_newOrderSpecs);

        //取引余力結果
        WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
        //分岐フロー
        if (!l_blnIsMarginAccountOpen)
        {
            //calc余力更新内容<現物顧客>～未約定売付注文考慮～
            //現物顧客の余力更新内容を計算する
            //未約定売付注文を考慮した日計り拘束金を取得する。
            //（出金を伴う注文の取引余力チェック時にコールされる。）
            //１）this.calc余力更新内容<現物顧客>～未約定売付注文考慮～()をコールする。
            //[引数]
            //差金決済相当外買付代金非考慮フラグ：false
            List l_lisTradingpowerUpdResultEquityIncUnexecSellOrders =
                l_tradingPowerUpd.calcTradingpowerUpdResultEquityIncUnexecSellOrder();

            //資産余力情報<現物顧客>(List, 余力計算条件, double)
            // calc余力更新内容<現物顧客>～未約定売付注文考慮～()の戻り値
            //余力計算条件 = create余力計算条件<標準>()の戻り値
            //今回注文出金額 = 引数.現注文内容[0].get概算代金
            WEB3TPTradingPowerCalcEquity l_tpTradingPowerCalcEquity =
                new WEB3TPTradingPowerCalcEquity(
                    l_lisTradingpowerUpdResultEquityIncUnexecSellOrders,
                    l_tpCalcCondition,
                    l_newOrderSpecs[0].getEstimatedPrice());

            //預り金不足額を計算する。
            double l_dblActualPaymentBalance = 0.0D;
            for(int index = 0; index <= WEB3TPSpecifiedPointDef.T_5; index++)
            {
                //引出可能現金(T+0..5)
                l_dblActualPaymentBalance = Math.min(
                    l_tpTradingPowerCalcEquity.calcActualPaymentBalance(index),
                    l_dblActualPaymentBalance);
            }

            //分岐フロー
            if (l_dblActualPaymentBalance > 0
                || GtlUtils.Double.isZero(l_dblActualPaymentBalance))
            {
                //calc余力更新内容<現物顧客>( )
                List l_lisTradingpowerUpdResultEquitys =
                    l_tradingPowerUpd.calcTradingpowerUpdResultEquity();

                //資産余力情報<現物顧客>(List, 余力計算条件)
                //余力計算結果Params<現物顧客>：calc余力更新内容<現物顧客>()の戻り値
                //余力計算条件：create余力計算条件<標準>()の戻り値
                WEB3TPTradingPowerCalcEquity l_tradingPowerCalcEquity =
                    new WEB3TPTradingPowerCalcEquity(
                        l_lisTradingpowerUpdResultEquitys,
                        l_tpCalcCondition);

                //calc適用その他商品買付可能額(OrderTypeEnum, int)
                //注文種別　@=　@701：外株買い
                //基準日 = calc指定日()の戻り値
                WEB3TPCalcResult l_calcResult =
                    l_tradingPowerCalcEquity.calcAppliedOtherTradingPower(
                        OrderTypeEnum.FEQ_BUY, l_intSpecifiedPoint);

                //set判定フラグ(boolean)
                //boolean： true
                l_tpResult.setResultFlg(true);

                //set次回取引可能額(double)
                //取引可能額： 余力計算結果.取引可能額
                //※　@余力計算結果：calc適用その他商品買付可能額()の戻り値
                l_tpResult.setTradingPower(l_calcResult.tradingPower);

                //set取引余力エラー情報(取引余力エラー情報)
                //取引余力エラー情報：null
                l_tpResult.setTpErrorInfo(null);

                //分岐フロー
                if (l_blnUpdateFlg)
                {
                    //save余力更新内容<現物顧客>
                    //List：余力更新.calc余力更新内容<現物顧客>()
                    l_tradingPowerUpd.saveTradingpowerUpdResultEquity(l_lisTradingpowerUpdResultEquitys);
                }
            }
            else
            {
                //判定フラグをセット
                l_tpResult.setResultFlg(false);
                //取引可能額をセット
                l_tpResult.setTradingPower(0);

                /*
                 * 取引余力エラー情報を生成
                 */
                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
                l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.LACK_ACCOUNT_BALANCE;
                l_tpErrorInfo.lackAccountBalance = 0;
                l_tpErrorInfo.buyOrderPossibleAmount = 0;
                l_tpErrorInfo.sellOrderPossibleQuantity = 0;
                l_tpErrorInfo.marginSecInfo = null;

                //取引余力エラー情報をセット
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);
            }
        }
        else
        {
            //find余力計算結果<信用顧客>～口座ＩＤ指定～(口座ID : long)
            List l_lisCalcResult =
                WEB3TPTradingPowerCalcMargin.findCalcResultMarginParams(l_lngAccountId);

            //資産余力情報<信用顧客>(List, 使用可能現金情報, 余力計算条件)
            //余力計算結果Params：find余力計算結果<信用顧客>()の戻り値
            //余力計算条件 = create余力計算条件<標準>()の戻り値
            WEB3TPTradingPowerCalcMargin l_tradingPowerCalcMargin =
                new WEB3TPTradingPowerCalcMargin(l_lisCalcResult, l_tpCalcCondition);

            //get発注日
            Date l_datOrderBizDate = l_newOrderSpecs[0].getOrderBizDate();
            int l_intOrderBizDate = l_tpCalcCondition.calcSpecifiedPoint(l_datOrderBizDate);

            //get受渡日
            int l_intDeliveryDate = l_tpCalcCondition.calcSpecifiedPoint(l_datDeliveryDate);

            //calc追証余力<適用可能額チェック用>(OrderCategEnum, int, int)
            //注文種別 = 701：外株買い
            //発注日 = get発注日の戻り値
            //受渡日 = get受渡日の戻り値
            WEB3TPCalcResult l_tpCalcResult =
                l_tradingPowerCalcMargin.calcMarginCallPowerForCheck(
                    OrderTypeEnum.FEQ_BUY, l_intOrderBizDate, l_intDeliveryDate);

            //分岐フロー
            if (l_tpCalcResult !=null && l_tpCalcResult.tradingPower < 0)
            {
                //判定フラグをセット
                l_tpResult.setResultFlg(false);
                //取引可能額をセット
                l_tpResult.setTradingPower(0);

                /*
                 * 取引余力エラー情報を生成
                 */
                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
                l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.LACK_ACCOUNT_BALANCE;
                l_tpErrorInfo.lackAccountBalance = 0;
                l_tpErrorInfo.buyOrderPossibleAmount = 0;
                l_tpErrorInfo.sellOrderPossibleQuantity = 0;
                l_tpErrorInfo.marginSecInfo = null;

                //取引余力エラー情報をセット
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);

                log.exiting(STR_METHOD_NAME);
                return l_tpResult;
            }

            //calc余力更新内容<信用顧客>～未約定売付注文考慮～
            List l_lisTradingpowerUpdResultMarginIncUnexecSellOrders =
                l_tradingPowerUpd.calcTradingpowerUpdResultMarginIncUnexecSellOrder();

            //資産余力情報<信用顧客>(List, 余力計算条件)
            //余力計算結果Params<信用顧客>：calc余力更新内容<信用顧客>～未約定売付注文考慮～()
            //余力計算条件：create余力計算条件<標準>()
            WEB3TPTradingPowerCalcMargin l_tpTradingPowerCalcMargin =
                new WEB3TPTradingPowerCalcMargin(
                    l_lisTradingpowerUpdResultMarginIncUnexecSellOrders, l_tpCalcCondition);

            //預り金不足額を計算する。
            double l_dblActualPaymentBalance = 0.0D;
            /*
             * 預り金不足額を計算する
             * 
             *  　@預り金不足額 = MIN( 預り金請求余力(T+0..T+受渡日-1), 
             *                       引出可能現金(T+受渡日..5), 
             *                       保証金引出余力(T+受渡日..5) )
             */
            //T+0..T+受渡日-1
            for(int index = 0; index < l_intDeliveryDate; index++)
            {
                //預り金請求余力(T+0..T+受渡日-1)
                l_dblActualPaymentBalance = Math.min(
                    l_tpTradingPowerCalcMargin.calcAccountBalanceDemandPower(index),
                    l_dblActualPaymentBalance);
            }
            //T+受渡日..T+5
            for(int index = l_intDeliveryDate; index <= WEB3TPSpecifiedPointDef.T_5; index++)
            {
                //引出可能現金(T+受渡日..5)
                l_dblActualPaymentBalance = Math.min(
                    l_tpTradingPowerCalcMargin.calcActualPaymentBalance(index),
                    l_dblActualPaymentBalance);

                //保証金引出余力(T+受渡日..5)
                l_dblActualPaymentBalance = Math.min(
                    l_tpTradingPowerCalcMargin.calcMarginDrawPower(index),
                    l_dblActualPaymentBalance);
            }

            //calc二階建(補助口座, long, 資産余力情報<信用顧客>, 余力更新, OrderTypeEnum, long, Date)
            //補助口座：引数.補助口座
            //long：-1
            //資産余力情報<信用顧客>：生成した資産余力情報<信用顧客>オブジェクト
            //余力更新：生成した余力更新オブジェクト
            //OrderTypeEnum：外株買い
            //long：-1
            //Date：null
            WEB3TPMarginSecurityInfo[] l_marginSecurityInfos =
                this.calcMarginSecurity(
                    l_subAccount,
                    -1,
                    l_tpTradingPowerCalcMargin,
                    l_tradingPowerUpd,
                    OrderTypeEnum.FEQ_BUY,
                    -1,
                    null);

            //分岐フロー
            if (l_marginSecurityInfos == null
                && (l_dblActualPaymentBalance > 0
                    || GtlUtils.Double.isZero(l_dblActualPaymentBalance)))
            {
                //calc余力更新内容<信用顧客>
                List l_lisTradingpowerUpdResultMargin =
                    l_tradingPowerUpd.calcTradingpowerUpdResultMargin(WEB3TPMarkToMarketDivDef.NORMAL);

                //資産余力情報<信用顧客>(List, 余力計算条件)
                //余力計算結果Params<信用顧客>：calc余力更新内容<信用顧客>()
                //余力計算条件：create余力計算条件<標準>()
                WEB3TPTradingPowerCalcMargin l_calcMargin =
                    new WEB3TPTradingPowerCalcMargin(
                        l_lisTradingpowerUpdResultMargin, l_tpCalcCondition);

                //calc適用その他商品買付可能額(OrderTypeEnum, int)
                //注文種別　@=　@701：外株買い
                //基準日 = calc指定日()の戻り値
                WEB3TPCalcResult l_calcResult = l_calcResult =
                    l_calcMargin.calcAppliedOtherTradingPower(
                        OrderTypeEnum.FEQ_BUY, l_intSpecifiedPoint);

                //set判定フラグ(boolean)
                //boolean： true
                l_tpResult.setResultFlg(true);

                //set次回取引可能額(double)
                //取引可能額： 余力計算結果.取引可能額
                //※　@余力計算結果：calc適用その他商品買付可能額()の戻り値
                l_tpResult.setTradingPower(l_calcResult.tradingPower);

                //set取引余力エラー情報(取引余力エラー情報)
                //取引余力エラー情報：null
                l_tpResult.setTpErrorInfo(null);

                //分岐フロー
                if (l_blnUpdateFlg)
                {
                    //save余力更新内容<信用顧客>
                    //List：余力更新.calc余力更新内容<信用顧客>()
                    l_tradingPowerUpd.saveTradingpowerUpdResultMargin(l_lisTradingpowerUpdResultMargin);
                }
            }
            else
            {
                //判定フラグをセット
                l_tpResult.setResultFlg(false);
                //取引可能額をセット
                l_tpResult.setTradingPower(0);

                /*
                 * 取引余力エラー情報を生成
                 */
                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
                if (l_dblActualPaymentBalance < 0)
                {
                    l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.LACK_ACCOUNT_BALANCE;                    
                }
                else
                {
                    l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.MARGIN_SEC_ERROR;
                }
                l_tpErrorInfo.lackAccountBalance = 0;
                l_tpErrorInfo.buyOrderPossibleAmount = 0;
                l_tpErrorInfo.sellOrderPossibleQuantity = 0;
                l_tpErrorInfo.marginSecInfo = l_marginSecurityInfos;

                //取引余力エラー情報をセット
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_tpResult;
    }

    /**
     * (validate取引余力<外国株式売付>)<BR>
     * <BR>
     * 外国株式買付において、現注文内容を取り込み預り金チェックを実施する。<BR>
     * <BR>
     * シーケンス図「(取引余力サービス)validate取引余力<外国株式売付>」参照<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_newOrderSpecs - (現注文内容)<BR>
     * 現注文内容<BR>
     * @@param l_blnUpdateFlg - (余力更新フラグ)<BR>
     * 余力更新フラグ<BR>
     * trueの時、余力再計算処理を実施する<BR>
     * falseの時、余力再計算処理を実施しない<BR>
     * @@return WEB3TPTradingPowerResult
     * @@throws WEB3BaseException
     */
    protected WEB3TPTradingPowerResult validateTradingPowerFeqSell(
        WEB3GentradeSubAccount l_subAccount,
        WEB3TPNewOrderSpec[] l_newOrderSpecs,
        boolean l_blnUpdateFlg) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateTradingPowerFeqSell("
            + "WEB3GentradeSubAccount, WEB3TPNewOrderSpec[], boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null
            || l_newOrderSpecs == null
            || l_newOrderSpecs.length == 0)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        //getAccountId
        long l_lngAccountId = l_subAccount.getAccountId();

        //getMainAccount
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //is信用口座開設(弁済区分 : String)
        //弁済区分：　@0(指定なし)
        boolean l_blnIsMarginAccountOpen =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //create余力計算条件<標準>(補助口座)
        WEB3TPCalcCondition l_tpCalcCondition =
            WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

        //get銘柄ID
        long l_lngProductId = l_newOrderSpecs[0].getProductId();

        //売付後余力更新(long, boolean, 余力計算条件, 現注文内容[], long)
        //long：補助口座.getAccountId() の戻り値
        //boolean：顧客.is信用口座開設() の戻り値
        //余力計算条件：create余力計算条件<標準>()の戻り値
        //現注文内容[]：null
        //銘柄ID：現注文内容.get銘柄ID()の戻り値
        WEB3TPTradingPowerUpdAfterSell l_tradingPowerUpdAfterSellOrderFront =
            new WEB3TPTradingPowerUpdAfterSell(
                l_lngAccountId,
                l_blnIsMarginAccountOpen,
                l_tpCalcCondition,
                null,
                l_lngProductId);

        //売付後余力更新(long, boolean, 余力計算条件, 現注文内容[], long)
        //long：補助口座.getAccountId() の戻り値
        //boolean：顧客.is信用口座開設() の戻り値
        //余力計算条件：create余力計算条件<標準>()の戻り値
        //現注文内容[]：引数.現注文内容
        //銘柄ID：現注文内容.get銘柄ID()の戻り値
        WEB3TPTradingPowerUpdAfterSell l_tradingPowerUpdAfterSellOrderBack =
            new WEB3TPTradingPowerUpdAfterSell(
                l_lngAccountId,
                l_blnIsMarginAccountOpen,
                l_tpCalcCondition,
                l_newOrderSpecs,
                l_lngProductId);

        //取引余力結果
        WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
        //(*分岐フロー：現物顧客（顧客.is信用口座開設() == false）の場合
        if (!l_blnIsMarginAccountOpen)
        {
            //get受渡日
            Date l_datDeliveryDate = l_newOrderSpecs[0].getDeliveryDate();

            //calc指定日(Date)
            //受渡日：引数.現注文内容.get受渡日()の戻り値
            int l_intSpecifiedPoint = l_tpCalcCondition.calcSpecifiedPoint(l_datDeliveryDate);

            //calc余力更新内容<現物顧客>～未約定売付注文考慮～
            List l_lisTradingpowerUpdResultEquityIncUnexecSellOrderFronts =
                l_tradingPowerUpdAfterSellOrderFront.calcTradingpowerUpdResultEquityIncUnexecSellOrder();

            WEB3TPTradingPowerCalcEquity l_tpTradingPowerCalcEquityFront =
                new WEB3TPTradingPowerCalcEquity(
                    l_lisTradingpowerUpdResultEquityIncUnexecSellOrderFronts,
                    l_tpCalcCondition);
            //calc余力更新内容<現物顧客>～未約定売付注文考慮～
            List l_lisTradingpowerUpdResultEquityIncUnexecSellOrderBacks =
                l_tradingPowerUpdAfterSellOrderBack.calcTradingpowerUpdResultEquityIncUnexecSellOrder();

            WEB3TPTradingPowerCalcEquity l_tpTradingPowerCalcEquityBack =
                new WEB3TPTradingPowerCalcEquity(
                    l_lisTradingpowerUpdResultEquityIncUnexecSellOrderBacks,
                    l_tpCalcCondition);
            //分岐フロー：取引OKの場合
            // (「（注文後）引出可能現金(T+受渡日) >= 0」 または
            //「（注文後）引出可能現金(T+受渡日) >= （注文前）引出可能現金(T+受渡日)」)の場合
            double l_dblActualPayBalFront =
                l_tpTradingPowerCalcEquityFront.calcActualPaymentBalance(l_intSpecifiedPoint);
            double l_dblActualPayBalBack =
                l_tpTradingPowerCalcEquityBack.calcActualPaymentBalance(l_intSpecifiedPoint);
            if (l_dblActualPayBalBack > 0
                || GtlUtils.Double.isZero(l_dblActualPayBalBack)
                || l_dblActualPayBalBack > l_dblActualPayBalFront
                || GtlUtils.Double.isEqual(l_dblActualPayBalBack, l_dblActualPayBalFront))
            {
                //判定フラグ
                l_tpResult.setResultFlg(true);
                //取引可能額
                l_tpResult.setTradingPower(0);
                //取引エラー情報
                l_tpResult.setTpErrorInfo(null);
                //注意文言表示区分
                l_tpResult.setAttentionObjectionType(null);

                //分岐フロー：submit処理（引数.更新フラグ == true）の場合
                if (l_blnUpdateFlg)
                {
                    //余力更新(long, boolean, 余力計算条件, 現注文内容[])
                    //long：補助口座.getAccountId() の戻り値
                    //boolean：顧客.is信用口座開設() の戻り値
                    //余力計算条件：create余力計算条件<標準>()の戻り値
                    //現注文内容[]：引数.現注文内容
                    WEB3TPTradingPowerUpd l_tradingPowerUpd = new WEB3TPTradingPowerUpd(
                        l_lngAccountId,
                        l_blnIsMarginAccountOpen,
                        l_tpCalcCondition,
                        l_newOrderSpecs);

                    //calc余力更新内容<現物顧客>
                    List l_lisTradingpowerUpdResultEquitys =
                        l_tradingPowerUpd.calcTradingpowerUpdResultEquity();

                    // save余力更新内容<現物顧客>(List)
                    //余力更新内容：余力更新.calc余力更新内容<現物顧客>()の戻り値
                    l_tradingPowerUpd.saveTradingpowerUpdResultEquity(
                        l_lisTradingpowerUpdResultEquitys);
                }
            }
            else
            {
                //判定フラグをセット
                l_tpResult.setResultFlg(false);
                //取引可能額をセット
                l_tpResult.setTradingPower(0);
                //注意文言表示区分
                l_tpResult.setAttentionObjectionType(null);
                /*
                 * 取引余力エラー情報を生成
                 */
                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
                l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.LACK_ACCOUNT_BALANCE;
                l_tpErrorInfo.lackAccountBalance = 0;
                l_tpErrorInfo.buyOrderPossibleAmount = 0;
                l_tpErrorInfo.sellOrderPossibleQuantity = 0;
                l_tpErrorInfo.marginSecInfo = null;

                //取引余力エラー情報をセット
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);
            }
        }
        else
        {
            //get受渡日
            Date l_datDeliveryDate = l_newOrderSpecs[0].getDeliveryDate();

            //calc指定日(Date)
            //受渡日：引数.現注文内容.get受渡日()の戻り値
            int l_intSpecifiedPoint = l_tpCalcCondition.calcSpecifiedPoint(l_datDeliveryDate);

            //calc余力更新内容<信用顧客>～未約定売付注文考慮～
            List l_lisTradingpowerUpdResultMarginIncUnexecSellOrderFronts =
                l_tradingPowerUpdAfterSellOrderFront.calcTradingpowerUpdResultMarginIncUnexecSellOrder();

            WEB3TPTradingPowerCalcMargin l_tpTradingPowerCalcMarginFront =
                new WEB3TPTradingPowerCalcMargin(
                    l_lisTradingpowerUpdResultMarginIncUnexecSellOrderFronts,
                    l_tpCalcCondition);
            //calc余力更新内容<信用顧客>～未約定売付注文考慮～
            List l_lisTradingpowerUpdResultMarginIncUnexecSellOrderBacks =
                l_tradingPowerUpdAfterSellOrderBack.calcTradingpowerUpdResultMarginIncUnexecSellOrder();

            WEB3TPTradingPowerCalcMargin l_tpTradingPowerCalcMarginBack =
                new WEB3TPTradingPowerCalcMargin(
                    l_lisTradingpowerUpdResultMarginIncUnexecSellOrderBacks,
                    l_tpCalcCondition);

            //分岐フロー：取引OKの場合
            // (「（注文後）引出可能現金(T+受渡日) >= 0」 または
            //「（注文後）引出可能現金(T+受渡日) >= （注文前）引出可能現金(T+受渡日)」)の場合
            double l_dblActualPayBalFront =
                l_tpTradingPowerCalcMarginFront.calcActualPaymentBalance(l_intSpecifiedPoint);
            double l_dblActualPayBalBack =
                l_tpTradingPowerCalcMarginBack.calcActualPaymentBalance(l_intSpecifiedPoint);
            if (l_dblActualPayBalBack > 0
                || GtlUtils.Double.isZero(l_dblActualPayBalBack)
                || l_dblActualPayBalBack > l_dblActualPayBalFront
                || GtlUtils.Double.isEqual(l_dblActualPayBalBack, l_dblActualPayBalFront))
            {
                //判定フラグ
                l_tpResult.setResultFlg(true);
                //取引可能額
                l_tpResult.setTradingPower(0);
                //取引エラー情報
                l_tpResult.setTpErrorInfo(null);
                //注意文言表示区分
                l_tpResult.setAttentionObjectionType(null);

                //分岐フロー：submit処理（引数.更新フラグ == true）の場合
                if (l_blnUpdateFlg)
                {
                    //余力更新(long, boolean, 余力計算条件, 現注文内容[])
                    //long：補助口座.getAccountId() の戻り値
                    //boolean：顧客.is信用口座開設() の戻り値
                    //余力計算条件：create余力計算条件<標準>()の戻り値
                    //現注文内容[]：引数.現注文内容
                    WEB3TPTradingPowerUpd l_tradingPowerUpd = new WEB3TPTradingPowerUpd(
                        l_lngAccountId,
                        l_blnIsMarginAccountOpen,
                        l_tpCalcCondition,
                        l_newOrderSpecs);

                    //calc余力更新内容<信用顧客>(String)
                    //String：0:通常
                    List l_lisTradingpowerUpdResultMargins =
                        l_tradingPowerUpd.calcTradingpowerUpdResultMargin(
                            WEB3TPMarkToMarketDivDef.NORMAL);

                    // save余力更新内容<信用顧客>(List)
                    //余力更新内容：余力更新.calc余力更新内容<信用顧客>()の戻り値
                    l_tradingPowerUpd.saveTradingpowerUpdResultMargin(
                        l_lisTradingpowerUpdResultMargins);
                }
            }
            else
            {
                //判定フラグをセット
                l_tpResult.setResultFlg(false);
                //取引可能額をセット
                l_tpResult.setTradingPower(0);
                //注意文言表示区分
                l_tpResult.setAttentionObjectionType(null);
                /*
                 * 取引余力エラー情報を生成
                 */
                WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
                l_tpErrorInfo.tradinPowerErrorDiv = WEB3TPTradingPowerErrorDivDef.LACK_ACCOUNT_BALANCE;
                l_tpErrorInfo.lackAccountBalance = 0;
                l_tpErrorInfo.buyOrderPossibleAmount = 0;
                l_tpErrorInfo.sellOrderPossibleQuantity = 0;
                l_tpErrorInfo.marginSecInfo = null;

                //取引余力エラー情報をセット
                l_tpResult.setTpErrorInfo(l_tpErrorInfo);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_tpResult;
    }

    /**
     * (get出金可能額～出金入力画面表示用～)<BR>
     * <BR>
     * 出金可能額を取得する。<BR>
     * <BR>
     * １）出金可能額を取得する。<BR>
     * 　@取引余力サービスImpl.get出金可能額～0補正無し～()をコール<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@補助口座：引数.補助口座<BR>
     * 　@　@受渡日：引数.受渡日<BR>
     * 　@　@呼び出し元区分：3<BR>
     * <BR>
     * ２）出金可能額を返却する。<BR>
     * 　@○１）の戻り値 < 0の場合<BR>
     * 　@　@返却値：0<BR>
     * <BR>
     * 　@○以外（ １）の戻り値 >= 0）<BR>
     * 　@　@返却値："１）の戻り値"<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_datDeliveryDate - (受渡日)<BR>
     * 受渡日<BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    public double getPaymentTradingPowerAioCashoutInput(
        WEB3GentradeSubAccount l_subAccount,
        Date l_datDeliveryDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getPaymentTradingPowerAioCashoutInput("
            + "WEB3GentradeSubAccount, Date)";
        log.entering(STR_METHOD_NAME);

        //１）出金可能額を取得する。
        double l_tradingPower = this.getPaymentTradingPowerForCheck(
            l_subAccount, l_datDeliveryDate, 3);

        if (l_tradingPower < 0)
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return l_tradingPower;
        }
    }
}
@
