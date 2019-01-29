head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOrderManagerImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物注文マネージャクラス(WEB3FuturesOrderManagerImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/8/05 呉艶飛 (中訊) 新規作成
Revesion History : 2006/7/27 肖志偉 (中訊) 仕様変更 モデルNo.483,486,489,492
Revesion History : 2006/8/16 肖志偉 (中訊) 仕様変更 モデルNo.546
Revesion History : 2006/9/19 郭英 (中訊) 仕様変更 モデルNo.554
Revesion History : 2006/10/06 郭英 (中訊) 仕様変更 モデルNo.563
Revesion History : 2007/01/25 趙林鵬 (中訊) 仕様変更 モデルNo.619,No.620.No.621,No.622,No,624
Revesion History : 2007/06/11 趙林鵬 (中訊) モデルNo.699，732
Revesion History : 2007/06/21 趙林鵬 (中訊) モデルNo.678，707
Revesion History : 2008/03/12 金傑(中訊) モデル 836,847
Revesion History : 2008/08/18 安陽(中訊) IFO小数点対応
*/
package webbroker3.ifo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoContractImpl;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoContractOpenOrderUnitImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (先物注文マネージャ)<BR>
 * 先物注文マネージャクラス<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3FuturesOrderManagerImpl extends WEB3OptionOrderManagerImpl
{
    /**
    * ログユーティリティ<BR>
    */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesOrderManagerImpl.class);

    /**
     * @@roseuid 40F7B156037A
     */
    public WEB3FuturesOrderManagerImpl()
    {

    }

    /**
     * (calc概算決済損益)<BR>
     * 概算決済損益を算出して返却する。<BR>
     * <BR>
     * this.calc概算決済損益()に処理を委譲（delegate）する。<BR>
     * <BR>
     * [引数] 手数料：　@パラメータ.手数料<BR>
     * 指値：　@パラメータ.指値<BR>
     * 補助口座：　@パラメータ.補助口座<BR>
     * 先物OP取引銘柄：　@パラメータ.先物OP取引銘柄<BR>
     * 返済建玉エントリ[]：　@パラメータ.返済建玉エントリ[]<BR>
     * 数量：　@パラメータ.数量<BR>
     * 売買：　@パラメータ.売買<BR>
     * isSkip金額チェック：　@パラメータ.isSkip金額チェック<BR>
     * 先物OP建玉：　@null <BR>
     * <BR>
     * @@param l_commission - (手数料)<BR>
     * @@param l_dblLimitPrice - 指値<BR>
     * <BR>
     * 0が指定された場合は、時価を計算単価として利用する。<BR>
     *
     * @@param l_subAccount - (補助口座)<BR>
     * @@param l_futuresOptionTradedProduct - 先物OP取引銘柄<BR>
     * @@param l_settleContractEntry[] - (返済建玉エントリ[])<BR>
     * @@param l_dblQuantity - 数量<BR>
     * @@param l_dealing - 売買<BR>
     * 　@SideEnum.BUY（買）<BR>
     * 　@SideEnum.SELL（売）<BR>
     *
     * @@param l_blnIsSkipPriceCheck - (isSkip金額チェック)<BR>
     * 計算結果の代金について、妥当性チェックをスキップするかのフラグ。<BR>
     * <BR>
     * チェックを行う場合はfalse、チェックを行わない（スキップする）場合はtrueを指定する。<BR>
     *
     * @@return webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult
     * @@throws WEB3BaseException
     * @@roseuid 40A31F10000C
     */
    public WEB3IfoEstimateDeliveryAmountCalcResult calcEstimateSettlementIncome(
        WEB3GentradeCommission l_commission,
        double l_dblLimitPrice,
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoTradedProductImpl l_futuresOptionTradedProduct,
        SettleContractEntry[] l_settleContractEntry,
        double l_dblQuantity,
        SideEnum l_dealing,
        boolean l_blnIsSkipPriceCheck)throws WEB3BaseException
    {
        return this.calcEstimateSettlementIncome(
            l_commission,
            l_dblLimitPrice,
            l_subAccount,
            l_futuresOptionTradedProduct,
            l_settleContractEntry,
            l_dblQuantity,
            l_dealing,
            l_blnIsSkipPriceCheck,
            null);
    }

    /**
     * (calc概算決済損益)<BR>
     * 概算決済損益を算出して返却する。<BR>
     * <BR>
     * シーケンス図「（先物注文）calc概算決済損益」参照。<BR>
     * <BR>
     * @@param l_commission - (手数料)<BR>
     * @@param l_dblLimitPrice - 指値<BR>
     * <BR>
     * 0が指定された場合は、時価を計算単価として利用する。<BR>
     *
     * @@param l_subAccount - (補助口座)<BR>
     * @@param l_futuresOptionTradedProduct - 先物OP取引銘柄<BR>
     * @@param l_settleContractEntry[] - (返済建玉エントリ[])<BR>
     * @@param l_dblQuantity - 数量<BR>
     * @@param l_dealing - 売買<BR>
     * 　@SideEnum.BUY（買）<BR>
     * 　@SideEnum.SELL（売）<BR>
     *
     * @@param l_blnIsSkipPriceCheck - (isSkip金額チェック)<BR>
     * 計算結果の代金について、妥当性チェックをスキップするかのフラグ。<BR>
     * <BR>
     * チェックを行う場合はfalse、チェックを行わない（スキップする）場合はtrueを指定する。<BR>
     * @@param l_ifoContractImpl - 先物OP建玉<BR>
     *
     * @@return webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult
     * @@roseuid 40A31F10000C
     */
    public WEB3IfoEstimateDeliveryAmountCalcResult calcEstimateSettlementIncome(
        WEB3GentradeCommission l_commission,
        double l_dblLimitPrice,
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoTradedProductImpl l_futuresOptionTradedProduct,
        SettleContractEntry[] l_settleContractEntry,
        double l_dblQuantity,
        SideEnum l_dealing,
        boolean l_blnIsSkipPriceCheck,
        WEB3IfoContractImpl l_ifoContractImpl)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "calcEstimateSettlementIncome(WEB3GentradeCommission, double, WEB3GentradeSubAccount, " 
          + "WEB3IfoTradedProductImpl, SettleContractEntry[], double, SideEnum, boolean, WEB3IfoContractImpl)";
        log.entering(STR_METHOD_NAME);
        
        //戻り値の概算受渡代金計算結果オブジェクトを生成する。
        WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult = new WEB3IfoEstimateDeliveryAmountCalcResult();
        
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

            //先物OP発注審査個別チェックオブジェクトを作成する
            WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = (WEB3IfoOrderManagerReusableValidations) WEB3IfoOrderManagerReusableValidations.getInstance();

            //成行時計算単価
            double l_dblmakeOrderCalcUnitPrice = 0D;
            
            WEB3IfoProductManagerImpl l_productManager = (WEB3IfoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();
            
            if (l_dblLimitPrice == 0D)
            {
                l_dblmakeOrderCalcUnitPrice = l_productManager.getCurrentPrice(l_futuresOptionTradedProduct);
                //計算単価に時価を設定
                l_ifoEstimateDeliveryAmountCalcResult.setCalcUnitPrice(l_dblmakeOrderCalcUnitPrice);
            }
            else
            {
                l_dblmakeOrderCalcUnitPrice = l_dblLimitPrice;
                //計算単価に指値を設定
                l_ifoEstimateDeliveryAmountCalcResult.setCalcUnitPrice(l_dblLimitPrice);
            }

            //計算サービスオブジェクトを作成する
            WEB3IfoBizLogicProvider l_ifoBizLogicProvider = null;
            l_ifoBizLogicProvider = (WEB3IfoBizLogicProvider) l_finApp.getTradingModule(ProductTypeEnum.IFO).getBizLogicProvider();

            //売買代金を計算する               
            double l_dblCalcTurnOver = 0D;
            l_dblCalcTurnOver =
                l_ifoBizLogicProvider.calcTurnOver(
                    l_dblQuantity,
                    l_ifoEstimateDeliveryAmountCalcResult.getCalcUnitPrice(),
                    l_futuresOptionTradedProduct);
            l_ifoEstimateDeliveryAmountCalcResult.setRestraintTurnover(l_dblCalcTurnOver);
            
            //手数料オブジェクトに諸経費計算用代金をセットする。
            l_commission.setExpensesCalcAmount(l_dblCalcTurnOver);
            
            //isSkip金額チェック == falseの場合のみ、取引可能上限金額チェックを行う
            if (!l_blnIsSkipPriceCheck)
            {
                MainAccountRow l_mainAccountRow = (MainAccountRow) l_subAccount.getMainAccount().getDataSourceObject();
                l_ifoOrderManagerReusableValidations.validateOrderMaxAmount(
                    l_subAccount.getWeb3GenBranch(),
                    l_commission.getExpensesCalcAmount(),                
                    l_mainAccountRow.getAccountType(),
                    WEB3FuturesOptionDivDef.FUTURES);
            }
            
            //発注日を取得する。
            Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

            long l_lngContractId = 0L;
            double l_dblContractQuantity = 0D;
            double l_dblContractExecutedAmount = 0D;
            BigDecimal l_bdContractExecutedAmount = new BigDecimal("0");

            double l_dblCommission = 0D;
            BigDecimal l_bdCommission = new BigDecimal("0");

            double l_dblCommissionConsumptionTax = 0D;
            BigDecimal l_bdCommissionConsumptionTax = new BigDecimal("0");

            WEB3IfoContractImpl l_ifoContract = l_ifoContractImpl;
            
            //返済建玉エントリの要素数分のLOOP処理
            for (int i = 0; i < l_settleContractEntry.length; i++)
            {
                //返済数量の取得
                l_dblContractQuantity = l_settleContractEntry[i].getQuantity();
                
                // 反対取引以外（パラメータ.建玉 == null）の場合
                if (l_ifoContractImpl == null)
                {
                    //建玉IDの取得
                    l_lngContractId = l_settleContractEntry[i].getContractId();
                    //建玉オブジェクトの取得
                    l_ifoContract = new WEB3IfoContractImpl(l_lngContractId);
                }

                //建約定代金の取得
                l_bdContractExecutedAmount =
                    l_bdContractExecutedAmount.add(
                        new BigDecimal(l_ifoContract.getContractExecutedAmount(l_dblContractQuantity) + ""));
                l_dblContractExecutedAmount = l_bdContractExecutedAmount.doubleValue();

                //建手数料の取得
                BigDecimal l_bdContractCommission =
                    new BigDecimal(l_ifoContract.getContractCommission(l_dblContractQuantity) + "");
                l_bdCommission = l_bdCommission.add(l_bdContractCommission);
                l_dblCommission = l_bdCommission.doubleValue();

                //建手数料消費税の取得
                BigDecimal l_bdContractCommissionConsumptionTax =
                    new BigDecimal(l_ifoContract.getContractCommissionConsumptionTax(l_dblContractQuantity) + "");
                l_bdCommissionConsumptionTax = l_bdCommissionConsumptionTax.add(l_bdContractCommissionConsumptionTax);
                l_dblCommissionConsumptionTax = l_bdCommissionConsumptionTax.doubleValue();
            }      
            log.debug("建約定代金 = " + l_dblContractExecutedAmount);
            log.debug("建手数料 = " + l_dblCommission);
            log.debug("建手数料消費税 = " + l_dblCommissionConsumptionTax);
            
            //今回の取引分の概算受渡代金を算出する
            double l_dblDeliveryAmountNow = 0D;
            double l_dblCommissionFeeTaxNow = 0D;
            
            SideEnum l_sideEnum = SideEnum.SELL.equals(l_dealing) ? SideEnum.BUY : SideEnum.SELL;
            
            //今回取引分の委託手数料を算出する。
			l_ifoBizLogicProvider.calcCommission(l_commission, l_subAccount);
            //今回取引分の委託手数料にかかる消費税を算出する
            l_dblCommissionFeeTaxNow = l_ifoBizLogicProvider.calcSalesTax(l_commission.getCommission(), l_commission.getOrderBizDate(), l_subAccount);
            
            //今回取引分の概算受渡代金を算出する
            l_dblDeliveryAmountNow = l_ifoBizLogicProvider.calcDeliveryAmount(l_sideEnum, l_dblCalcTurnOver, l_commission.getCommission(), l_dblCommissionFeeTaxNow);
            log.debug("今回の取引分の概算受渡代金 = " + l_dblDeliveryAmountNow);
            BigDecimal l_bdDeliveryAmountNow = new BigDecimal(l_dblDeliveryAmountNow + "");
            
            //建分の受渡代金を算出する            
            double l_dblContractDeliveryAmount = 0D;
            l_dblContractDeliveryAmount = l_ifoBizLogicProvider.calcDeliveryAmount(l_dealing, l_dblContractExecutedAmount, l_dblCommission, l_dblCommissionConsumptionTax);
            log.debug("建分の受渡代金 = " + l_dblContractDeliveryAmount);
            BigDecimal l_bdContractDeliveryAmount = new BigDecimal(l_dblContractDeliveryAmount + "");
            
            //買建売返済の場合 
            if (SideEnum.BUY.equals(l_dealing))
            {
                l_ifoEstimateDeliveryAmountCalcResult.setEstimateDeliveryAmount(
                    l_bdDeliveryAmountNow.subtract(l_bdContractDeliveryAmount).doubleValue());
            }
            //売建買返済の場合
            else
            {
                l_ifoEstimateDeliveryAmountCalcResult.setEstimateDeliveryAmount(
                    l_bdContractDeliveryAmount.subtract(l_bdDeliveryAmountNow).doubleValue());
            }
            
            //手数料コースをセットする      
            l_ifoEstimateDeliveryAmountCalcResult.setCommissionCourse(l_commission.getCommissionCourseDiv());
            
            //手数料をセットする
            l_ifoEstimateDeliveryAmountCalcResult.setCommission(l_commission.getCommission());
            
            //手数料消費税をセットする
            l_ifoEstimateDeliveryAmountCalcResult.setCommissionTax(l_dblCommissionFeeTaxNow);
            
            log.exiting(STR_METHOD_NAME);
            return l_ifoEstimateDeliveryAmountCalcResult;
        }
        catch (DataQueryException l_dqe)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_dne)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, this.getClass().getName() + STR_METHOD_NAME);
        }

    }

    /**
     * (calc訂正時概算決済損益)<BR>
     *注文訂正時の概算決済損益を算出して返却する。<BR>
     *シーケンス図「（先物注文）calc訂正時概算決済損益」参照。<BR>
     * @@param l_commission - (手数料)<BR>
     * @@param l_dblLimitPrice - 指値<BR>
     * <BR>
     * 0が指定された場合は、時価を計算単価として利用する。<BR>
     * 
     * @@param l_subAccount - (補助口座)<BR>
     * @@param l_futuresOptionTradedProduct - 先物OP取引銘柄<BR>
     * @@param l_settleContractEntry[] - (返済建玉エントリ[])<BR>
     * @@param l_dblQuantity - (数量)<BR>
     * 訂正注文数量<BR>
     * 
     * @@param l_dealing - 売買<BR>
     * 　@SideEnum.BUY（買）<BR>
     * 　@SideEnum.SELL（売）<BR>
     * 
     * @@param l_dblExecQuantity - 注文単位.約定数量<BR>
     * 
     * @@param l_lngOrderUnitId - 注文単位.注文単位ID<BR>
     * 
     * @@param l_blnIsSkipPriceCheck - (isSkip金額チェック)<BR>
     * 計算結果の代金について、妥当性チェックをスキップするかのフラグ。<BR>
     * <BR>
     * チェックを行う場合はfalse、チェックを行わない（スキップする）場合はtrueを指定する。<BR>
     * 
     * @@return webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult
     * @@roseuid 40A31F3800B8
     */
    public WEB3IfoEstimateDeliveryAmountCalcResult calcChangeEstimateSettlementIncome(
        WEB3GentradeCommission l_commission,
        double l_dblLimitPrice,
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoTradedProductImpl l_futuresOptionTradedProduct,
        SettleContractEntry[] l_settleContractEntry,
        double l_dblQuantity,
        SideEnum l_dealing,
        double l_dblExecQuantity,
        long l_lngOrderUnitId,
        boolean l_blnIsSkipPriceCheck)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcChangeEstimateSettlementIncome(WEB3GentradeCommission, double, WEB3GentradeSubAccount, " 
            + "WEB3IfoTradedProductImpl, SettleContractEntry[], double, SideEnum, double, long, boolean";
        log.entering(STR_METHOD_NAME);

        //戻り値の概算受渡代金計算結果オブジェクトを生成する。
        WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult = new WEB3IfoEstimateDeliveryAmountCalcResult();
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3IfoProductManagerImpl l_productMgr = (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager();
        
        try{
            //計算サービスオブジェクトを作成する
            WEB3IfoBizLogicProvider l_ifoBizLogicProvider = null;
            l_ifoBizLogicProvider = (WEB3IfoBizLogicProvider) l_finApp.getTradingModule(ProductTypeEnum.IFO).getBizLogicProvider();
        
            //先物OP発注審査個別チェックオブジェクトを作成する
            WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = null;
            l_ifoOrderManagerReusableValidations =
                 (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();

        
            double l_dblCurrentPrice = 0;
            //（手数料.is指値() == false）の場合のみ実施する
            if (l_dblLimitPrice == 0D)
            {
                l_dblCurrentPrice = l_productMgr.getCurrentPrice(l_futuresOptionTradedProduct);
                l_ifoEstimateDeliveryAmountCalcResult.setCalcUnitPrice(l_dblCurrentPrice);
            }
            else
            {
                l_ifoEstimateDeliveryAmountCalcResult.setCalcUnitPrice(l_dblLimitPrice);
            }
        
            //売買代金を計算する。
            double l_dblTurnOver = 0;
            l_dblTurnOver = l_ifoBizLogicProvider.calcTurnOver((l_dblQuantity - l_dblExecQuantity),
                    l_ifoEstimateDeliveryAmountCalcResult.getCalcUnitPrice(),
                    l_futuresOptionTradedProduct);
                    
            log.debug("売買代金 = " + l_dblTurnOver); 
            BigDecimal l_bdTurnOver = new BigDecimal(l_dblTurnOver + "");
 
            //注文単位を取得する。
            IfoOrderUnit l_orderUnit = null;
            l_orderUnit = (IfoOrderUnit)this.getOrderUnit(l_lngOrderUnitId);
            IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        
            //諸経費計算用代金：　@売買代金 + 注文単位.合計約定金額
            BigDecimal l_bdExecutedAmount = new BigDecimal(l_orderUnitRow.getExecutedAmount() + "");
            double l_dblsetExpensesCalcAmount = l_bdTurnOver.add(l_bdExecutedAmount).doubleValue();
            log.debug("諸経費計算用代金 = " + l_dblsetExpensesCalcAmount);
            l_commission.setExpensesCalcAmount(l_dblsetExpensesCalcAmount);
        
            //isSkip金額チェック == falseの場合のみ、取引可能上限金額チェックを行う
            if (!l_blnIsSkipPriceCheck)
            {
                MainAccountRow l_mainAccountRow = (MainAccountRow) l_subAccount.getMainAccount().getDataSourceObject();
                l_ifoOrderManagerReusableValidations.validateOrderMaxAmount(
                        l_subAccount.getWeb3GenBranch(),
                        l_commission.getExpensesCalcAmount(),
                        l_mainAccountRow.getAccountType(),
                        WEB3FuturesOptionDivDef.FUTURES);
            } 
        
            //発注日を取得する。
            Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

            WEB3IfoContractImpl l_contractImpl = null;
            long l_lngContractId = 0L;
            double l_dblContractQuantity = 0D;
            double l_dblContractExecutedAmount = 0D;
            BigDecimal l_bdContractExecutedAmount = new BigDecimal("0");
            double l_dblCommission = 0D;
            BigDecimal l_bdCommission = new BigDecimal("0");
            double l_dblCommissionConsumptionTax = 0D;
            BigDecimal l_bdCommissionConsumptionTax = new BigDecimal("0");
            
            //返済建玉エントリの要素数分のLOOP処理
            for (int i = 0; i < l_settleContractEntry.length; i++)
            {
                //返済数量の取得
                l_dblContractQuantity = l_settleContractEntry[i].getQuantity();
                
                //建玉IDの取得
                l_lngContractId = l_settleContractEntry[i].getContractId();
                
                //建玉オブジェクトの取得
                l_contractImpl = new WEB3IfoContractImpl(l_lngContractId);

                //建約定代金の取得
                l_bdContractExecutedAmount =
                    l_bdContractExecutedAmount.add(
                        new BigDecimal(l_contractImpl.getContractExecutedAmount(l_dblContractQuantity) + ""));
                l_dblContractExecutedAmount = l_bdContractExecutedAmount.doubleValue();

                //建手数料の取得
                BigDecimal l_bdContractCommission =
                    new BigDecimal(l_contractImpl.getContractCommission(l_dblContractQuantity, l_lngOrderUnitId) + "");
                l_bdCommission = l_bdCommission.add(l_bdContractCommission);
                l_dblCommission = l_bdCommission.doubleValue();

                //建手数料消費税の取得
                BigDecimal l_bdContractCommissionConsumptionTax =
                    new BigDecimal(l_contractImpl.getContractCommissionConsumptionTax(l_dblContractQuantity, l_lngOrderUnitId) + "");
                l_bdCommissionConsumptionTax = l_bdCommissionConsumptionTax.add(l_bdContractCommissionConsumptionTax);
                l_dblCommissionConsumptionTax = l_bdCommissionConsumptionTax.doubleValue();
            }
            log.debug("建約定代金 = " + l_dblContractExecutedAmount);
            log.debug("建手数料 = " + l_dblCommission);
            log.debug("建手数料消費税 = " + l_dblCommissionConsumptionTax);
            
            //今回の取引分の概算受渡代金を算出する
            double l_dblDeliveryAmountNow = 0D;
            double l_dblCommissionFeeTaxNow = 0D;
            
            SideEnum l_sideEnum = SideEnum.SELL.equals(l_dealing) ? SideEnum.BUY : SideEnum.SELL;
            
            //今回取引分の委託手数料を算出する
            l_ifoBizLogicProvider.calcCommission(l_commission, l_subAccount);
            //今回取引分の委託手数料にかかる消費税を算出する
            l_dblCommissionFeeTaxNow = l_ifoBizLogicProvider.calcSalesTax(l_commission.getCommission(), l_commission.getOrderBizDate(), l_subAccount);
            
            //今回取引分の概算受渡代金を算出する
            l_dblDeliveryAmountNow = l_ifoBizLogicProvider.calcDeliveryAmount(l_sideEnum, l_dblsetExpensesCalcAmount, l_commission.getCommission(), l_dblCommissionFeeTaxNow);            
            BigDecimal l_bdDeliveryAmountNow = new BigDecimal(l_dblDeliveryAmountNow + "");
            log.debug("今回の取引分の概算受渡代金 = " + l_dblDeliveryAmountNow);
            
            //建分の受渡代金を算出する            
            double l_dblContractDeliveryAmount = 0D;
            l_dblContractDeliveryAmount = l_ifoBizLogicProvider.calcDeliveryAmount(l_dealing, l_dblContractExecutedAmount, l_dblCommission, l_dblCommissionConsumptionTax);
            BigDecimal l_bdContractDeliveryAmount = new BigDecimal(l_dblContractDeliveryAmount + "");
            log.debug("建分の受渡代金 = " + l_dblContractDeliveryAmount);
        
            //買建売返済の場合
            if (SideEnum.BUY.equals(l_dealing))
            {
                //概算決済損益 = 今回取引分の概算受渡代金(**1) － 建分の受渡代金(**2)
                l_ifoEstimateDeliveryAmountCalcResult.setEstimateDeliveryAmount(
                    l_bdDeliveryAmountNow.subtract(l_bdContractDeliveryAmount).doubleValue());
            }
            //売建買返済の場合
            else
            {
				//概算決済損益 = 建分の受渡代金(**2) － 今回取引分の概算受渡代金(**1)
                l_ifoEstimateDeliveryAmountCalcResult.setEstimateDeliveryAmount(
                    l_bdContractDeliveryAmount.subtract(l_bdDeliveryAmountNow).doubleValue());
            }

            //手数料コースをセットする
            l_ifoEstimateDeliveryAmountCalcResult.setCommissionCourse(l_commission.getCommissionCourseDiv());
            
            //手数料をセットする
            l_ifoEstimateDeliveryAmountCalcResult.setCommission(l_commission.getCommission());
            
            //手数料消費税をセットする
            l_ifoEstimateDeliveryAmountCalcResult.setCommissionTax(l_dblCommissionFeeTaxNow);
            
            log.exiting(STR_METHOD_NAME);
            return l_ifoEstimateDeliveryAmountCalcResult;
        }
        catch (NotFoundException l_nfex)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_dne)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_dqe)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, this.getClass().getName() + STR_METHOD_NAME);
        }
    }

    /**
     * (validate先物新規建注文)<BR>
     * 物新規建注文発注審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物注文）validate新規建注文」参照。<BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@param l_openContractOrderSpec - 新規建注文内容<BR>
     * @@param l_ifoOrderUnit - 注文単位<BR>
     * @@return NewOrderValidationResult
     * @@roseuid 40AB13340083
     */
    public NewOrderValidationResult validateFuturesOpenContractOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoOpenContractOrderSpec l_openContractOrderSpec,
        IfoOrderUnit l_ifoOrderUnit)
    {
        final String STR_METHOD_NAME = "validateFuturesOpenContractOrder(WEB3GentradeSubAccount, WEB3IfoOpenContractOrderSpec, IfoOrderUnit)";

        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_openContractOrderSpec == null)
        {
            //例外をスローする
            log.error(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            //throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80017));     
        }
        NewOrderValidationResult l_newOrderValidationResult = null;
        try
        {
        //先物OP発注審査個別チェックオブジェクトを作成する
        WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = (WEB3IfoOrderManagerReusableValidations) WEB3IfoOrderManagerReusableValidations.getInstance();

        //注文共通チェックを実施する。
        this.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.FUTURES);

        //入力市場コードの取得
        String l_strMarketCode = l_openContractOrderSpec.getMarketCode();

        String l_strInstitutionCode = l_openContractOrderSpec.getInstitutionCode();

        //市場コードのチェックを実施する。 
        WEB3GentradeMarket l_market = (WEB3GentradeMarket) l_ifoOrderManagerReusableValidations.validateMarket(l_strMarketCode, l_strInstitutionCode);

        //銘柄コードを取得する。
        String l_strProductCode = l_openContractOrderSpec.getProductCode();

        //銘柄のチェックを行い、銘柄オブジェクトを返却する
        WEB3IfoProductImpl l_ifoProductImpl = l_ifoOrderManagerReusableValidations.validateProductCode(l_strProductCode, l_strInstitutionCode);

        //買建かを判定する。
        boolean l_isBuyToOpenOrder = l_openContractOrderSpec.isBuyToOpenOrder();

        //取引銘柄のチェックを行い、先物OP取引銘柄オブジェクトを返却する。    
        WEB3IfoTradedProductImpl l_ifoTradedProductImpl = l_ifoOrderManagerReusableValidations.validateTradedProduct(l_ifoProductImpl, l_market, l_isBuyToOpenOrder, true);

        //指定顧客の取引店である部店オブジェクトを取得する。 
        //部店オブジェクトを取得する
        WEB3GentradeBranch l_branch = null;
        l_branch = (WEB3GentradeBranch) l_subAccount.getMainAccount().getBranch();

        //入力指数が取扱可能かを判定する。 
        l_ifoOrderManagerReusableValidations.validateHandlingIndex(l_branch.getBranchCode(), l_ifoTradedProductImpl);

        //成行かどうかを判定する。
        boolean l_blnMarketOrder = l_openContractOrderSpec.isMarketOrder();

        //注文失効日を取得する。
        Date l_datOrderExpDate = l_openContractOrderSpec.getOrderExpDate();

        //発注条件を取得する。
        String l_strOrderCond = l_openContractOrderSpec.getOrderCond();

        //執行条件を取得する。
        IfoOrderExecutionConditionType l_conditionType = l_openContractOrderSpec.getExecutionConditionType();

		//注文期限区分を取得する。
		String l_strExpirationDateType = l_openContractOrderSpec.getExpirationDateType();
		
		//注文条件のチェックを行う
		Date l_datFirstOrderBizDate = null;
		//注文繰越の場合
		if((l_openContractOrderSpec.getFirstOrderUnitId() != null) &&
			(l_openContractOrderSpec.getFirstOrderUnitId().longValue() > 0))
		{
			IfoOrderUnit l_orderUnit = null;
			try
			{
				l_orderUnit = (IfoOrderUnit)getOrderUnit(
				l_openContractOrderSpec.getFirstOrderUnitId().longValue());
			}
			catch (NotFoundException l_nfe)
			{
				return new NewOrderValidationResult(
					ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));     
			}
			IfoOrderUnitRow l_orderUnitRow =
				(IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
			l_datFirstOrderBizDate = WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(),"yyyyMMdd");
		}

		//validate注文条件		
        l_ifoOrderManagerReusableValidations.validateOrderCond(
            l_subAccount,
            0,
            l_blnMarketOrder,
            l_ifoTradedProductImpl,
            true,
            l_isBuyToOpenOrder,
            l_datFirstOrderBizDate,
            l_datOrderExpDate,
            l_strOrderCond,
            l_conditionType,
			l_strExpirationDateType,
            l_openContractOrderSpec.getFirstOrderUnitId());

        //数量のチェックを行う。 
        if (l_ifoOrderUnit == null)
        {
            double l_dblQuantity = l_openContractOrderSpec.getQuantity();
            l_ifoOrderManagerReusableValidations.validateQuantity(l_subAccount, l_ifoTradedProductImpl, l_dblQuantity, l_isBuyToOpenOrder, true);
        }

        //指値を取得する。
        double l_dblLimitPrice = l_openContractOrderSpec.getLimitPrice();
        //指値のチェックを行う。
        l_ifoOrderManagerReusableValidations.validateOrderUnitPrice(l_dblLimitPrice, l_ifoTradedProductImpl, l_subAccount);

        //W指値の訂正指値を取得する。
        double l_dblWLimitPriceChange = l_openContractOrderSpec.getWLimitPriceChange();

        //W指値の執行条件を取得する。
        IfoOrderExecutionConditionType l_wLimitExecCondType = l_openContractOrderSpec.getWLimitExecCondType();
        
        //逆指値基準値を取得する。
        double l_dblStopOrderPrice = l_openContractOrderSpec.getStopOrderPrice();
        
        String l_strWLimitPriceChange = 
            WEB3StringTypeUtility.formatNumber(l_dblWLimitPriceChange);
        
        //validateW指値注文()
        l_ifoOrderManagerReusableValidations.validateWLimitPriceOrder(
            l_subAccount,
            0,
            l_dblLimitPrice,
            l_strOrderCond,
            l_dblStopOrderPrice,
            l_strWLimitPriceChange,
            l_wLimitExecCondType,
            null,
            l_ifoTradedProductImpl,
            true,
            l_isBuyToOpenOrder);
        
        ProcessingResult l_processingResult = null;
        l_processingResult = ProcessingResult.newSuccessResultInstance();
        l_newOrderValidationResult = new NewOrderValidationResult(l_processingResult);
               
        }
        catch (WEB3BaseException l_webx)
        {
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(l_webx.getErrorInfo()));
        }
        
		log.exiting(STR_METHOD_NAME);
        
        return l_newOrderValidationResult;
    }

    /**
     * (validate先物返済注文)<BR>
     * 先物返済注文発注審査を行う。<BR>
     * （validateFuturesSettleContractOrderのオーバーライド）<BR>
     * <BR>
     * this.validate先物返済注文()に処理を委譲（delegate）する。<BR>
     * <BR>
     * [引数] 補助口座：　@パラメータ.補助口座<BR>
     * 返済注文内容：　@パラメータ.返済注文内容<BR>
     * 建玉：　@null <BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@param l_settleContractOrderSpec - 返済注文内容オブジェクト<BR>
     * @@return NewOrderValidationResult
     */
    public NewOrderValidationResult validateFuturesSettleContractOrder(
        WEB3GentradeSubAccount l_subAccount, WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec)
    {
        return this.validateFuturesSettleContractOrder(l_subAccount, l_settleContractOrderSpec, null);
    }

    /**
     * (validate先物返済注文)<BR>
     * 先物返済注文発注審査<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物注文）validate返済注文」参照。<BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@param l_settleContractOrderSpec - 返済注文内容オブジェクト<BR>
     * @@param l_ifoContractImpl - 先物OP建玉<BR>
     * @@return NewOrderValidationResult
     * @@roseuid 40AB133400A2
     */
    public NewOrderValidationResult validateFuturesSettleContractOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec,
        WEB3IfoContractImpl l_ifoContractImpl)
    {
        final String STR_METHOD_NAME = "validateFuturesSettleContractOrder(" +
            "WEB3GentradeSubAccount, WEB3IfoSettleContractOrderSpec, WEB3IfoContractImpl)";

        log.entering(STR_METHOD_NAME);
        if (l_subAccount == null || l_settleContractOrderSpec == null)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80017));     
        }

        ProcessingResult l_processingResult = null;

        //新規建発注審査結果オブジェクト
        NewOrderValidationResult l_newOrderValidationResult = null;

        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

            //先物OP発注審査個別チェックオブジェクトを作成する
            WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = null;
            l_ifoOrderManagerReusableValidations = (WEB3IfoOrderManagerReusableValidations) WEB3IfoOrderManagerReusableValidations.getInstance();

            //注文共通チェックを実施する
            this.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.FUTURES);

            // 反対取引以外（パラメータ.建玉 == null）の場合
            if (l_ifoContractImpl == null)
            {
                //返済注文内容に関連する返済建玉エントリの配列を取得する
                SettleContractEntry l_settleContractEntry = null;
                l_settleContractEntry = l_settleContractOrderSpec.getSettleContractEntries()[0];

                //建玉IDを取得
                long l_lngContractID = 0L;
                l_lngContractID = l_settleContractEntry.getContractId();

                //建玉IDに該当する建玉オブジェクトを取得する
                l_ifoContractImpl = new WEB3IfoContractImpl(l_lngContractID);
            }

            //市場IDを取得
            long l_lngMarketId = 0L;
            l_lngMarketId = l_ifoContractImpl.getMarketId();

            //市場オブジェクトを取得
            Market l_market = null;
            l_market = l_finApp.getFinObjectManager().getMarket(l_lngMarketId); //throw NotFoundException

            //証券会社コードを取得
            String l_strInstitutionCode = null;
            l_strInstitutionCode = l_settleContractOrderSpec.getInstitutionCode();

            //市場コードのチェックを実施する
            l_market = l_ifoOrderManagerReusableValidations.validateMarket(l_market.getMarketCode(), l_strInstitutionCode);

            //先物OP銘柄オブジェクトを取得する
            WEB3IfoProductImpl l_ifoProductImpl = null;
            l_ifoProductImpl = (WEB3IfoProductImpl) l_ifoContractImpl.getProduct();

            //銘柄コードを取得する
            String l_strProductCode = null;
            l_strProductCode = l_ifoProductImpl.getProductCode();

            //銘柄のチェックを行い、銘柄オブジェクトを返却する            
            l_ifoProductImpl = l_ifoOrderManagerReusableValidations.validateProductCode(l_strProductCode, l_strInstitutionCode);

            //買建かを判定する
            boolean l_blnIsBuyContract = false;
            l_blnIsBuyContract = l_ifoContractImpl.isLong();

            //取引銘柄のチェックを行い、先物OP取引銘柄オブジェクトを返却する
            WEB3IfoTradedProductImpl l_ifoTradedProductImpl = null;
            l_ifoTradedProductImpl = l_ifoOrderManagerReusableValidations.validateTradedProduct(l_ifoProductImpl, (WEB3GentradeMarket) l_market, l_blnIsBuyContract, false);

            //部店コードを取得
            String l_strBranchCode = null;
            l_strBranchCode = l_subAccount.getWeb3GenBranch().getBranchCode();

            //入力指数が取扱可能かを判定する
            l_ifoOrderManagerReusableValidations.validateHandlingIndex(l_strBranchCode, l_ifoTradedProductImpl);

            //成行かどうかを判定する
            boolean l_blnIsMatketOrder = false;
            l_blnIsMatketOrder = l_settleContractOrderSpec.isMarketOrder();

            //注文失効日を取得する
            Date l_datOrderExpDate = null;
            l_datOrderExpDate = l_settleContractOrderSpec.getOrderExpDate();

            //発注条件を取得する
            String l_strOrderCond = null;
            l_strOrderCond = l_settleContractOrderSpec.getOrderCond();

            //執行条件を取得する
            IfoOrderExecutionConditionType l_executionConditionType = null;
            l_executionConditionType = l_settleContractOrderSpec.getExecutionConditionType();

			//注文期限区分を取得する
			String l_strExpirationDateType = null;
			l_strExpirationDateType = l_settleContractOrderSpec.getExpirationDateType();
			
			Date l_datFirstOrderBizDate = null;
			//注文繰越の場合
			if((l_settleContractOrderSpec.getFirstOrderUnitId() != null) &&
				(l_settleContractOrderSpec.getFirstOrderUnitId().longValue() > 0))
			{
				IfoOrderUnit l_orderUnit = null;
				l_orderUnit = (IfoOrderUnit)getOrderUnit(
					l_settleContractOrderSpec.getFirstOrderUnitId().longValue());
				IfoOrderUnitRow l_orderUnitRow =
					(IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
				l_datFirstOrderBizDate = WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(),"yyyyMMdd");
			}	
			
            //注文条件のチェックを行う
            l_ifoOrderManagerReusableValidations.validateOrderCond(
                l_subAccount,
                0,
                l_blnIsMatketOrder,
                l_ifoTradedProductImpl,
                false,
                l_blnIsBuyContract,
                l_datFirstOrderBizDate,
                l_datOrderExpDate,
                l_strOrderCond,
                l_executionConditionType,
				l_strExpirationDateType,
                l_settleContractOrderSpec.getFirstOrderUnitId());

            //数量を取得
            double l_dblTotalQuantity = 0D;
            l_dblTotalQuantity = l_settleContractOrderSpec.getTotalQuantity();

            //数量のチェックを行う
            l_ifoOrderManagerReusableValidations.validateQuantity(l_subAccount, l_ifoTradedProductImpl, l_dblTotalQuantity, l_blnIsBuyContract, false);

            //指値を取得する
            double l_dblLimitPrice = 0D;
            l_dblLimitPrice = l_settleContractOrderSpec.getLimitPrice();

            //指値のチェックを行う
            l_ifoOrderManagerReusableValidations.validateOrderUnitPrice(l_dblLimitPrice, l_ifoTradedProductImpl, l_subAccount);

            //W指値の訂正指値を取得する
            double l_dblWLimitPriceChange = 0D;
            l_dblWLimitPriceChange = l_settleContractOrderSpec.getWLimitPriceChange();

            //逆指値基準値を取得する。
            double l_dblStopOrderPrice = l_settleContractOrderSpec.getStopOrderPrice();
            
            //W指値の執行条件を取得する。
            IfoOrderExecutionConditionType l_wLimitExecCondType = l_settleContractOrderSpec.getWLimitExecCondType();
            
            String l_strWLimitPriceChange = 
                WEB3StringTypeUtility.formatNumber(l_dblWLimitPriceChange);
            
            //validateW指値注文()
            l_ifoOrderManagerReusableValidations.validateWLimitPriceOrder(
                l_subAccount,
                0,
                l_dblLimitPrice,
                l_strOrderCond,
                l_dblStopOrderPrice,
                l_strWLimitPriceChange,
                l_wLimitExecCondType,
                null,
                l_ifoTradedProductImpl,
                false,
                l_ifoContractImpl.isShort());
            
            l_processingResult = ProcessingResult.newSuccessResultInstance();

            l_newOrderValidationResult = new NewOrderValidationResult(l_processingResult);
        }
        catch (DataQueryException l_dqex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_dqex);
            return new NewOrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003));  
        }
        catch (DataNetworkException l_dnex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_dnex);
            return new NewOrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003));  
        }
        catch (NotFoundException l_nfex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_nfex);
            return new NewOrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005));  
        }
        catch (WEB3BaseException l_webx)
        {
            return new NewOrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    l_webx.getErrorInfo()));
        }
        log.exiting(STR_METHOD_NAME);

        return l_newOrderValidationResult;
    }

    /**
     * (validate先物返済訂正注文)<BR>
     * this.validate先物返済訂正注文()に処理を委譲（delegate）する。<BR>
     * <BR>
     * [validate先物返済訂正注文()に指定する引数]<BR>
     * 補助口座：　@パラメータ.補助口座<BR>
     * 先物返済訂正内容：　@パラメータ.返済訂正内容<BR>
     * isSkip遅延状況チェック：　@false（固定）<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_changeSettleContractOrderSpec - (返済訂正内容)<BR>
     * 返済訂正内容オブジェクト<BR>
     * @@return OrderValidationResult
     */
    public OrderValidationResult validateFuturesChangeSettleContractOrder(
        WEB3GentradeSubAccount l_subAccount, 
        WEB3IfoChangeSettleContractOrderSpec l_changeSettleContractOrderSpec)
    {
        final String STR_METHOD_NAME =
            "validateFuturesChangeSettleContractOrder(l_subAccount,l_changeSettleContractOrderSpec)";
        log.entering(STR_METHOD_NAME);

        //this.validate先物返済訂正注文()に処理を委譲（delegate）する。
        OrderValidationResult l_orderValidationResult =
            this.validateFuturesChangeSettleContractOrder(
                l_subAccount,
                l_changeSettleContractOrderSpec,
                false);

        log.exiting(STR_METHOD_NAME);
        return l_orderValidationResult;
    }

    /**
     * (validate先物返済訂正注文)<BR>
     * 先物返済訂正注文発注審査<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物注文）validate返済訂正注文」参照。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_changeSettleContractOrderSpec - (返済訂正内容)<BR>
     * 返済訂正内容オブジェクト<BR>
     * @@param l_blnIsSkipDelayStatusCheck - (isSkip遅延状況チェック)<BR>
     * isSkip遅延状況チェック<BR>
     * @@return OrderValidationResult
     * @@roseuid 40AB133400C2
     */
    public OrderValidationResult validateFuturesChangeSettleContractOrder(
        WEB3GentradeSubAccount l_subAccount, 
        WEB3IfoChangeSettleContractOrderSpec l_changeSettleContractOrderSpec,
        boolean l_blnIsSkipDelayStatusCheck)
    {
        final String STR_METHOD_NAME = "validateFuturesChangeSettleContractOrder" +
            "(l_subAccount, l_changeSettleContractOrderSpec, l_blnIsSkipDelayStatusCheck)";

        log.entering(STR_METHOD_NAME);
        if (l_subAccount == null || l_changeSettleContractOrderSpec == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //例外をスローする
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80017)); 
        }

        //注文チェック結果オブジェクト
        OrderValidationResult l_orderValidationResult = null;

        //先物OP発注審査個別チェックオブジェクトを作成する
        WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = null;
        l_ifoOrderManagerReusableValidations = (WEB3IfoOrderManagerReusableValidations) WEB3IfoOrderManagerReusableValidations.getInstance();

        try
        {
            //注文共通チェックを実施する
            this.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.FUTURES);

            //注文IDを取得
            long l_lngOrderID = 0L;
            l_lngOrderID = l_changeSettleContractOrderSpec.getOrderId();
            log.debug("注文ＩＤを取得 = " + l_lngOrderID);
            //注文オブジェクトを取得
            Order l_order = null;
            l_order = this.getOrder(l_lngOrderID); //throw NotFoundException

            //原注文のステイタスが訂正可能な状態かを判定する
            //[validate注文訂正可能状態()に指定する引数] 
            // 注文：　@（注文ＩＤに該当する注文オブジェクト） 
            // isSkip遅延状況チェック：引数のisSkip遅延状況チェック
            l_ifoOrderManagerReusableValidations.validateOrderForChangeability(
                l_order,
                l_blnIsSkipDelayStatusCheck);

            //注文単位IDを取得
            long l_lngOrderUnitID = 0L;
            l_lngOrderUnitID = l_changeSettleContractOrderSpec.getOrderUnitId();

            //注文単位オブジェクトを取得
            OrderUnit l_orderUnit = null;
            l_orderUnit = this.getOrderUnit(l_lngOrderUnitID); //throw NotFoundException

            //注文単位Rowオブジェクトを取得
            IfoOrderUnitRow l_ifoOrderUnitRow = null;
            l_ifoOrderUnitRow = (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();

            //先物OP銘柄オブジェクト
            WEB3IfoProductImpl l_ifoProductImpl = null;

            //銘柄のチェックを行い、銘柄オブジェクトを返却する
            l_ifoProductImpl = l_ifoOrderManagerReusableValidations.validateProductID(l_ifoOrderUnitRow.getProductId());

            //市場オブジェクト
            WEB3GentradeMarket l_market = null;
            //市場のチェック
            l_market = (WEB3GentradeMarket) l_ifoOrderManagerReusableValidations.validateMarketID(l_ifoOrderUnitRow.getMarketId());

            //返済注文訂正個別エントリに関連する返済建玉エントリの配列を取得する
            SettleContractEntry l_settleContractEntry = null;
            l_settleContractEntry = l_changeSettleContractOrderSpec.getAfterChangeSettleContractEntries()[0];

            //建玉IDを取得
            long l_lngContractID = 0L;
            l_lngContractID = l_settleContractEntry.getContractId();

            //建玉ＩＤに該当する建玉オブジェクトを取得する
            IfoContractImpl l_ifoContractImpl = null;
            //throw DataQueryException,DataNetworkException
            l_ifoContractImpl = new IfoContractImpl(l_lngContractID);

            //売買建の区分を取得
            boolean l_blnIsBuyContract = false;
            l_blnIsBuyContract = l_ifoContractImpl.isLong();

            //取引銘柄のチェックを行い、先物OP取引銘柄オブジェクトを返却する
            WEB3IfoTradedProductImpl l_ifoTradedProductImpl = null;
            l_ifoTradedProductImpl = l_ifoOrderManagerReusableValidations.validateTradedProduct(l_ifoProductImpl, l_market, l_blnIsBuyContract, false);

            //入力指数が取扱可能かを判定する
            l_ifoOrderManagerReusableValidations.validateHandlingIndex(l_subAccount.getWeb3GenBranch().getBranchCode(), l_ifoTradedProductImpl);

            //is成行を取得する
            boolean l_blnIsAfterChangePriceMarket = false;
            l_blnIsAfterChangePriceMarket = l_changeSettleContractOrderSpec.isAfterChangePriceMarket();

            //訂正失効日を取得する
            Date l_datChangeExpirationDate = null;
            log.debug( "訂正失効日 =" + l_datChangeExpirationDate);
            l_datChangeExpirationDate = l_changeSettleContractOrderSpec.getChangeExpirationDate();

            //発注条件を取得する
            String l_strOrderCond = null;
            l_strOrderCond = l_changeSettleContractOrderSpec.getOrderCond();

            //訂正執行条件を取得する
            IfoOrderExecutionConditionType l_changeExecCondType = null;
            l_changeExecCondType = l_changeSettleContractOrderSpec.getChangeExecCondType();

            //注文期限区分を取得する。
            String l_strExpirationDateType=null;
			l_strExpirationDateType = l_changeSettleContractOrderSpec.getExpirationDateType();
			
            //先物注文マネージャ.get初回注文の注文単位(注文単位).発注日を取得する
            IfoOrderUnitRow l_firstOrderUnitRow =
                (IfoOrderUnitRow)this.getFirstOrderUnit((IfoOrderUnit)l_orderUnit).getDataSourceObject();
            Date l_bizDate = WEB3DateUtility.getDate(l_firstOrderUnitRow.getBizDate(),"yyyyMMdd");
	
            //注文条件のチェックを行う
            l_ifoOrderManagerReusableValidations.validateOrderCond(
                l_subAccount,
                l_lngOrderUnitID,
                l_blnIsAfterChangePriceMarket,
                l_ifoTradedProductImpl,
                false,
                l_blnIsBuyContract,
				l_bizDate,
                l_datChangeExpirationDate,
                l_strOrderCond,
                l_changeExecCondType,
				l_strExpirationDateType,
                new Long(l_firstOrderUnitRow.getOrderUnitId()));

            //訂正後の数量を取得する
            double l_dblAfterChangeTotalQuantity = 0D;
            l_dblAfterChangeTotalQuantity = l_changeSettleContractOrderSpec.getAfterChangeTotalQuantity();

            //数量が0または、マイナス値でないことのチェックを行う
            //throw OrderValidationException
            l_ifoOrderManagerReusableValidations.validateQuantity(l_dblAfterChangeTotalQuantity);

            //訂正後指値を取得する
            double l_dblAfterChangePrice = 0D;
            l_dblAfterChangePrice = l_changeSettleContractOrderSpec.getAfterChangePrice();

            //指値のチェックを行う
            l_ifoOrderManagerReusableValidations.validateOrderUnitPrice(l_dblAfterChangePrice, l_ifoTradedProductImpl, l_subAccount);

            //validate閉局後訂正取消受付可能
            //銘柄タイプ：　@注文単位.銘柄タイプ
            //先物／オプション区分：　@注文単位.先物／オプション区分
            //部店：　@補助口座.get取扱店()
            //立会区分：　@注文単位.立会区分
            //発注日：　@注文単位.発注日
            Date l_datBizDate = WEB3DateUtility.getDate(
                l_ifoOrderUnitRow.getBizDate(), "yyyyMMdd");

            WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                l_ifoOrderUnitRow.getProductType(),
                l_ifoOrderUnitRow.getFutureOptionDiv(),
                l_subAccount.getWeb3GenBranch(),
                l_ifoOrderUnitRow.getSessionType(),
                l_datBizDate);

            //訂正後の発注条件演算子を取得する
            String l_strOrderOperatorAfterChange = null;
            l_strOrderOperatorAfterChange = l_changeSettleContractOrderSpec.getOrderCondOperator();

            //訂正後の逆指値基準値タイプを取得する
            String l_strStopOrderBasePriceTypeAfterChange = null;
            l_strStopOrderBasePriceTypeAfterChange = l_changeSettleContractOrderSpec.getStopOrderBasePriceType();

            //訂正後の逆指値基準値を取得する
            double l_dblStopOrderBasePriceAfterChange = 0D;

            l_dblStopOrderBasePriceAfterChange = l_changeSettleContractOrderSpec.getStopOrderBasePrice();
            
            //訂正後の（W指値）訂正指値を取得する。
            double l_dblWStopPriceAfterChange = l_changeSettleContractOrderSpec.getWLimitPriceChange();
            
            //訂正後の失効日を取得する
            Date l_datExpirationDateAfterChange = null;
            l_datExpirationDateAfterChange = l_changeSettleContractOrderSpec.getChangeExpirationDate();

            //訂正後の（W指値）執行条件を取得する。
            IfoOrderExecutionConditionType l_wLimitExecCondTypeAfterChange = 
                l_changeSettleContractOrderSpec.getWLimitExecCondType();            
            
            String l_strWLimitPriceChange = 
                WEB3StringTypeUtility.formatNumber(l_dblWStopPriceAfterChange);
            
            //validateW指値注文()
            l_ifoOrderManagerReusableValidations.validateWLimitPriceOrder(
                l_subAccount,
                l_lngOrderUnitID,
                l_dblAfterChangePrice,
                l_strOrderCond,
                l_dblStopOrderBasePriceAfterChange,
                l_strWLimitPriceChange,
                l_wLimitExecCondTypeAfterChange,
                l_changeSettleContractOrderSpec.getWLimitEnableStatusDiv(),
                l_ifoTradedProductImpl,
                false,
                l_ifoContractImpl.isShort());
            
            //訂正後の注文期限区分を取得する
            String l_strAfterExpirationDateType = l_changeSettleContractOrderSpec.getExpirationDateType();
			
			//訂正後の内訳数量を取得する。
			SettleContractEntry[] l_modifiedSettleContractEntries=l_changeSettleContractOrderSpec.getAfterChangeSettleContractEntries();	
            
            //訂正入力値が妥当であるかチェックする
            l_ifoOrderManagerReusableValidations.validateOrderChangeSpec(
                l_orderUnit,
                l_dblAfterChangeTotalQuantity,
                l_dblAfterChangePrice,
                l_changeExecCondType,
                l_strOrderCond,
                l_strOrderOperatorAfterChange,
                l_strStopOrderBasePriceTypeAfterChange,
                l_dblStopOrderBasePriceAfterChange,
                l_dblWStopPriceAfterChange,
                l_wLimitExecCondTypeAfterChange,
                l_datExpirationDateAfterChange,
                l_strAfterExpirationDateType,
				l_modifiedSettleContractEntries);

            //validate訂正時注文Rev上限(IfoOrderUnit, double, double, IfoOrderExecutionConditionType)  
            //[validate訂正時注文Rev上限()に指定する引数] 
            //注文単位：　@原注文（訂正元）の注文単位オブジェクト 
            //訂正数量：　@getAfterChangeTotalQuantiy() 
            //訂正指値：　@getAfterChangePrice() 
            //訂正執行条件：　@get訂正執行条件()
            l_ifoOrderManagerReusableValidations.validateChangeOrderRevLimit(
                (IfoOrderUnit)l_orderUnit,
                l_dblAfterChangeTotalQuantity,
                l_dblAfterChangePrice,
                l_changeExecCondType);

            l_orderValidationResult = new OrderValidationResult(
                ProcessingResult.newSuccessResultInstance());
        }
        catch (NotFoundException l_nfex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_nfex);
            //例外をスローする
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80003));
        }
        catch (DataQueryException l_dqex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_dqex);
            //例外をスローする
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80003));
        }
        catch (DataNetworkException l_dnex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_dnex);
            //例外をスローする
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80003));
        }
        catch (OrderValidationException l_ovex)
        {
            log.error(STR_METHOD_NAME, l_ovex);
            return l_ovex.getValidationResult();      
        }
        catch (WEB3BaseException l_webx)
        {
            //例外をスローする
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(l_webx.getErrorInfo()));
        }
        log.exiting(STR_METHOD_NAME);

        return l_orderValidationResult;
    }

    /**
     * (validate先物新規建訂正注文)<BR>
     * this.validate先物新規建訂正注文()に処理を委譲（delegate）する。<BR>
     * <BR>
     * [validate先物新規建訂正注文()に指定する引数]<BR>
     * 補助口座：　@パラメータ.補助口座<BR>
     * 先物注文訂正内容：　@パラメータ.新規建訂正内容<BR>
     * isSkip遅延状況チェック：　@false（固定）<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_ifoOpenContractChangeSpec - (新規建訂正内容)<BR>
     * 新規建訂正内容オブジェクト<BR>
     * @@return OrderValidationResult
     */
    public OrderValidationResult validateFuturesChangeOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec)
    {
        final String STR_METHOD_NAME = 
            "validateFuturesChangeOrder(l_subAccount, l_ifoOpenContractChangeSpec)";
        log.entering(STR_METHOD_NAME);

        //this.validate先物新規建訂正注文()に処理を委譲（delegate）する。
        OrderValidationResult l_orderValidationResult =
            this.validateFuturesChangeOrder(
                l_subAccount,
                l_ifoOpenContractChangeSpec,
                false);

        log.exiting(STR_METHOD_NAME);
        return l_orderValidationResult;
    }

    /**
     * (validate先物新規建訂正注文)<BR>
     * 先物新規建訂正注文発注審査<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物注文）validate新規建訂正注文」参照。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_ifoOpenContractChangeSpec - (新規建訂正内容)<BR>
     * 新規建訂正内容オブジェクト<BR>
     * @@param l_blnIsSkipDelayStatusCheck - (isSkip遅延状況チェック)<BR>
     * isSkip遅延状況チェック<BR>
     * @@return OrderValidationResult
     * @@roseuid 40AB133400E1
     */
    public OrderValidationResult validateFuturesChangeOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec,
        boolean l_blnIsSkipDelayStatusCheck)
    {
        final String STR_METHOD_NAME =
            "validateFuturesChangeOrder(l_subAccount, l_ifoOpenContractChangeSpec, l_blnIsSkipDelayStatusCheck)";
        log.entering(STR_METHOD_NAME);
        if (l_subAccount == null || l_ifoOpenContractChangeSpec == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //例外をスローする
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80017)); 
        }

        //注文チェック結果オブジェクト
        OrderValidationResult l_orderValidationResult = null;

        //先物OP発注審査個別チェックオブジェクトを生成
        WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = null;

        l_ifoOrderManagerReusableValidations = (WEB3IfoOrderManagerReusableValidations) WEB3IfoOrderManagerReusableValidations.getInstance();

        try
        {
            //注文チェックを行う
            this.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.FUTURES);

            //注文IDを取得
            long l_lngOrderID = 0L;
            l_lngOrderID = l_ifoOpenContractChangeSpec.getOrderId();

            //注文オブジェクトを取得
            Order l_order = null;
            l_order = this.getOrder(l_lngOrderID); //Throw NotFoundException

            //原注文のステイタスが訂正可能な状態かを判定する。
            //[validate注文訂正可能状態()に指定する引数] 
            // 注文：　@（注文ＩＤに該当する注文オブジェクト） 
            // isSkip遅延状況チェック：引数のisSkip遅延状況チェック
            l_ifoOrderManagerReusableValidations.validateOrderForChangeability(
                l_order,
                l_blnIsSkipDelayStatusCheck);

            //注文単位IDを取得
            long l_lngOrderUnitID = 0L;
            l_lngOrderUnitID = l_ifoOpenContractChangeSpec.getOrderUnitId();

            //注文単位オブジェクトを取得
            OrderUnit l_orderUnit = null;
            l_orderUnit = this.getOrderUnit(l_lngOrderUnitID);

            //注文単位Rowオブジェクトを取得
            IfoOrderUnitRow l_ifoOrderUnitRow = null;
            l_ifoOrderUnitRow = (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();

            //先物OP銘柄オブジェクト
            WEB3IfoProductImpl l_ifoProductImpl = null;
            //銘柄のチェック
            l_ifoProductImpl = l_ifoOrderManagerReusableValidations.validateProductID(l_ifoOrderUnitRow.getProductId());

            //市場オブジェクト
            WEB3GentradeMarket l_market = null;
            //市場のチェック
            l_market = (WEB3GentradeMarket) l_ifoOrderManagerReusableValidations.validateMarketID(l_ifoOrderUnitRow.getMarketId());

            IfoContractOpenOrderUnitImpl l_ifoContractOpenOrderUnitImpl = null;
            l_ifoContractOpenOrderUnitImpl = new IfoContractOpenOrderUnitImpl(l_lngOrderUnitID);

            //売買区分を取得
            boolean l_blnIsBuyContract = false;
            if (SideEnum.BUY.equals(l_ifoContractOpenOrderUnitImpl.getSide()))
            {
                l_blnIsBuyContract = true;
            }
            else
            {
                l_blnIsBuyContract = false;
            }

            //取引銘柄オブジェクトを取得
            WEB3IfoTradedProductImpl l_ifoTradedProductImpl = null;

            //取引銘柄のチェック
            l_ifoTradedProductImpl = l_ifoOrderManagerReusableValidations.validateTradedProduct(l_ifoProductImpl, l_market, l_blnIsBuyContract, true);

            //入力指数が取扱可能かを判定する
            l_ifoOrderManagerReusableValidations.validateHandlingIndex(l_subAccount.getWeb3GenBranch().getBranchCode(), l_ifoTradedProductImpl);

            //is成行を取得する
            boolean l_blnIsAfterChangePriceMarket = false;
            l_blnIsAfterChangePriceMarket = l_ifoOpenContractChangeSpec.isAfterChangePriceMarket();

            //訂正失効日を取得する
            Date l_datChangeExpirationDate = null;
            l_datChangeExpirationDate = l_ifoOpenContractChangeSpec.getChangeExpirationDate();

            //発注条件を取得する
            String l_strOrderCond = null;
            l_strOrderCond = l_ifoOpenContractChangeSpec.getOrderCond();

            //訂正執行条件を取得する
            IfoOrderExecutionConditionType l_changeExecCondType = null;
            l_changeExecCondType = l_ifoOpenContractChangeSpec.getChangeExecCondType();

            //注文期限区分を取得する
			String l_strExpirationDateType = null;
			l_strExpirationDateType = l_ifoOpenContractChangeSpec.getExpirationDateType();
			
            //先物注文マネージャ.get初回注文の注文単位(注文単位).発注日を取得する
            IfoOrderUnitRow l_firstOrderUnitRow =
                (IfoOrderUnitRow)this.getFirstOrderUnit((IfoOrderUnit)l_orderUnit).getDataSourceObject();
            Date l_bizDate = WEB3DateUtility.getDate(l_firstOrderUnitRow.getBizDate(),"yyyyMMdd");
	
            //注文条件のチェックを行う
            l_ifoOrderManagerReusableValidations.validateOrderCond(
                l_subAccount,
                l_lngOrderUnitID,
                l_blnIsAfterChangePriceMarket,
                l_ifoTradedProductImpl,
                true,
                l_blnIsBuyContract,
                l_bizDate,
                l_datChangeExpirationDate,
                l_strOrderCond,
                l_changeExecCondType,
				l_strExpirationDateType,
                new Long(l_firstOrderUnitRow.getOrderUnitId()));

            //訂正後数量を取得する
            double l_dblAfterChangeOriginalQuantity = 0D;
            l_dblAfterChangeOriginalQuantity = l_ifoOpenContractChangeSpec.getAfterChangeOriginalQuantity();

            //数量が0または、マイナス値でないことのチェックを行う
            //Throw OrderValidationException
            l_ifoOrderManagerReusableValidations.validateQuantity(l_dblAfterChangeOriginalQuantity);

            //訂正後指値を取得する
            double l_dblAfterChangePrice = 0D;
            l_dblAfterChangePrice = l_ifoOpenContractChangeSpec.getAfterChangePrice();

            //指値のチェックを行う
            l_ifoOrderManagerReusableValidations.validateOrderUnitPrice(l_dblAfterChangePrice, l_ifoTradedProductImpl, l_subAccount);

            //validate閉局後訂正取消受付可能
            //銘柄タイプ：　@注文単位.銘柄タイプ
            //先物／オプション区分：　@注文単位.先物／オプション区分
            //部店：　@補助口座.get取扱店()
            //立会区分：　@注文単位.立会区分
            //発注日：　@注文単位.発注日
            Date l_datBizDate = WEB3DateUtility.getDate(
                l_ifoOrderUnitRow.getBizDate(), "yyyyMMdd");

            WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                l_ifoOrderUnitRow.getProductType(),
                l_ifoOrderUnitRow.getFutureOptionDiv(),
                l_subAccount.getWeb3GenBranch(),
                l_ifoOrderUnitRow.getSessionType(),
                l_datBizDate);

            //訂正後の発注条件演算子を取得する
            String l_strOrderOperatorAfterChange = null;
            l_strOrderOperatorAfterChange = l_ifoOpenContractChangeSpec.getOrderCondOperator();

            //訂正後の逆指値基準値タイプを取得する
            String l_strStopOrderBasePriceTypeAfterChange = null;
            l_strStopOrderBasePriceTypeAfterChange = l_ifoOpenContractChangeSpec.getStopOrderBasePriceType();

            //訂正後の逆指値基準値を取得する
            double l_dblStopOrderBasePriceAfterChange = 0D;
            l_dblStopOrderBasePriceAfterChange = l_ifoOpenContractChangeSpec.getStopOrderBasePrice();
            
            //訂正後の（W指値）訂正指値を取得する。
            double l_dblWStopPriceAfterChange = l_ifoOpenContractChangeSpec.getWLimitPriceChange();

            //訂正後の失効日を取得する
            Date l_datExpirationDateAfterChange = null;
            l_datExpirationDateAfterChange = l_ifoOpenContractChangeSpec.getChangeExpirationDate();

            //W指値の執行条件を取得する。
            IfoOrderExecutionConditionType l_wLimitExecCondType = l_ifoOpenContractChangeSpec.getWLimitExecCondType();            
            
            //is買建
            boolean l_blnIsBuyToOpenOrder = false;
            if (SideEnum.BUY.equals(l_orderUnit.getSide()))
            {
                l_blnIsBuyToOpenOrder = true;
            }
            
            String l_strWLimitPriceChange = 
                WEB3StringTypeUtility.formatNumber(l_dblWStopPriceAfterChange);
            
            //validateW指値注文()
            l_ifoOrderManagerReusableValidations.validateWLimitPriceOrder(
                l_subAccount,
                l_lngOrderUnitID,
                l_dblAfterChangePrice,
                l_strOrderCond,
                l_dblStopOrderBasePriceAfterChange,
                l_strWLimitPriceChange,
                l_wLimitExecCondType,
                l_ifoOpenContractChangeSpec.getWLimitEnableStatusDiv(),
                l_ifoTradedProductImpl,
                true,
                l_blnIsBuyToOpenOrder);
            
            //訂正後の注文期限区分を取得する
            String l_strAfterExpirationDateType = l_ifoOpenContractChangeSpec.getExpirationDateType();

			//訂正後の訂正返済建玉エントリを取得する。
			SettleContractEntry[] l_modifiedSettleContractEntries = null;

            //訂正入力値が妥当であるかチェックする
            l_ifoOrderManagerReusableValidations.validateOrderChangeSpec(
                l_orderUnit,
                l_dblAfterChangeOriginalQuantity,
                l_dblAfterChangePrice,
                l_changeExecCondType,
                l_strOrderCond,
                l_strOrderOperatorAfterChange,
                l_strStopOrderBasePriceTypeAfterChange,
                l_dblStopOrderBasePriceAfterChange,
                l_dblWStopPriceAfterChange,
                l_wLimitExecCondType,
                l_datExpirationDateAfterChange,
                l_strAfterExpirationDateType,
				l_modifiedSettleContractEntries);

            //validate訂正時注文Rev上限(IfoOrderUnit, double, double, IfoOrderExecutionConditionType)
            //[validate訂正時注文Rev上限()に指定する引数] 
            // 注文単位：　@原注文（訂正元）の注文単位オブジェクト 
            // 訂正数量：　@getAfterChangeOriginalQuantity() 
            // 訂正指値：　@getAfterChangePrice() 
            // 訂正執行条件：　@get訂正執行条件()
            l_ifoOrderManagerReusableValidations.validateChangeOrderRevLimit(
                (IfoOrderUnit)l_orderUnit,
                l_dblAfterChangeOriginalQuantity,
                l_dblAfterChangePrice,
                l_changeExecCondType);

            l_orderValidationResult = new OrderValidationResult(
                ProcessingResult.newSuccessResultInstance());
        }
        catch (DataNetworkException l_dnex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_dnex);
            //例外をスローする
            //throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME);
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80003)); 
        }
        catch (DataQueryException l_dqex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_dqex);
            //例外をスローする
            //throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME);
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80003)); 
        }
        catch (NotFoundException l_nfex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_nfex);
            //例外をスローする
            //throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME);
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80003)); 
        }
        catch (WEB3BaseException l_webx)
        {
            //例外をスローする
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(l_webx.getErrorInfo()));
        }

        catch (OrderValidationException  l_ovx)
        {
            log.error(STR_METHOD_NAME, l_ovx);            
            return l_ovx.getValidationResult();
        }
        log.exiting(STR_METHOD_NAME);
        return l_orderValidationResult;
    }

    /**
     * (validate取消注文)<BR>
     * （validateCancelOrderのオーバーライド）<BR>
     * <BR>
     * 取消発注審査<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物注文）validate取消注文」参照。<BR>
     * @@param l_subAccount - 補助口座オブジェクト<BR>
     * @@param l_cancelOrderSpec - 取消注文内容<BR>
     * @@return OrderValidationResult
     * @@roseuid 40AB13340100
     */
    public OrderValidationResult validateFuturesCancelOrder(WEB3GentradeSubAccount l_subAccount, CancelOrderSpec l_cancelOrderSpec) 
    {
        final String STR_METHOD_NAME = "validateFuturesCancelOrder(l_subAccount,l_cancelOrderSpec)";
        log.entering(STR_METHOD_NAME);
        if (l_subAccount == null || l_cancelOrderSpec == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //例外をスローする
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80017)); 
        }
        //注文チェック結果オブジェクト
        OrderValidationResult l_orderValidationResult = null;

        //先物OP発注審査個別チェックオブジェクトを生成
        WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = null;

        l_ifoOrderManagerReusableValidations = (WEB3IfoOrderManagerReusableValidations) WEB3IfoOrderManagerReusableValidations.getInstance();

        try
        {
            //注文チェックを行う
            this.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.FUTURES);

            //注文IDを取得
            long l_lngOrderID = 0L;
            l_lngOrderID = l_cancelOrderSpec.getOrderId();

            //注文オブジェクトを取得
            Order l_order = null;
            l_order = this.getOrder(l_lngOrderID); //Throw NotFoundException

            //注文取消可能状態をチェック
            //Throw OrderValidationException
            l_ifoOrderManagerReusableValidations.validateOrderForCancellation(l_order);

            //注文単位オブジェクト
            OrderUnit l_orderUnit = null;
            l_orderUnit = l_order.getOrderUnits()[0];

            //注文単位ROWオブジェクト
            IfoOrderUnitRow l_ifoOrderUnitRow = null;
            l_ifoOrderUnitRow = (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();
            
            //市場のチェックを行い、市場オブジェクトを返却する。
            l_ifoOrderManagerReusableValidations.validateMarketID(l_ifoOrderUnitRow.getMarketId());

            //validate閉局後訂正取消受付可能
            //銘柄タイプ：　@注文単位.銘柄タイプ
            //先物／オプション区分：　@注文単位.先物／オプション区分
            //部店：　@補助口座.get取扱店()
            //立会区分：　@注文単位.立会区分
            //発注日：　@注文単位.発注日
            Date l_datBizDate = WEB3DateUtility.getDate(
                l_ifoOrderUnitRow.getBizDate(), "yyyyMMdd");

            WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                l_ifoOrderUnitRow.getProductType(),
                l_ifoOrderUnitRow.getFutureOptionDiv(),
                l_subAccount.getWeb3GenBranch(),
                l_ifoOrderUnitRow.getSessionType(),
                l_datBizDate);

            l_orderValidationResult = new OrderValidationResult(ProcessingResult.newSuccessResultInstance());

        }
        catch (NotFoundException l_nfex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_nfex);
            //例外をスローする
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80003)); 
        }
        catch (OrderValidationException l_ovex)
        {
            log.error(STR_METHOD_NAME, l_ovex);
            return l_ovex.getValidationResult(); 
        }
        catch (WEB3BaseException l_webx)
        {
            //例外をスローする
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(l_webx.getErrorInfo()));
        }
        log.exiting(STR_METHOD_NAME);

        return l_orderValidationResult;
    }

    /**
     * (calc概算建代金)<BR>
     * 概算建代金(手数料を含まない)を計算し結果を返却する。<BR>
     * <BR>
     * シーケンス図「(先物注文)calc概算建代金」参照<BR>
     * <BR>
     * @@param l_commission - (手数料)<BR>
     * @@param l_dblLimitPrice - 指値<BR>
     * <BR>
     * 0が指定された場合は、時価を計算単価として利用する。<BR>
     * 
     * @@param l_subAccount - (補助口座)<BR>
     * @@param l_futuresOptionTradedProduct - 先物OP取引銘柄<BR>
     * @@param l_dblQuantity - 数量<BR>
     * @@param l_blnIsSkipPriceCheck - (isSkip金額チェック)<BR>
     * 計算結果の代金について、妥当性チェックをスキップするかのフラグ。<BR>
     * <BR>
     * チェックを行う場合はfalse、チェックを行わない（スキップする）場合はtrueを指定する。<BR>
     * 
     * @@return webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult
     * @@roseuid 40B1B1BB0085
     */
    public WEB3IfoEstimateDeliveryAmountCalcResult calcEstimatePrice(
        WEB3GentradeCommission l_commission,
        double l_dblLimitPrice,
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoTradedProductImpl l_futuresOptionTradedProduct,
        double l_dblQuantity,
        boolean l_blnIsSkipPriceCheck)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "calcEstimatePrice(WEB3GentradeCommission, double, WEB3GentradeSubAccount, " 
        + "WEB3IfoTradedProductImpl, double, boolean)";
        log.entering(STR_METHOD_NAME);
        
        //戻り値の概算受渡代金計算結果オブジェクトを生成する。
        WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult = new WEB3IfoEstimateDeliveryAmountCalcResult();
        
    try {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
            //部店IDを取得する
            long l_lngBranchID = l_commission.getBranchId();

            //部店オブジェクトを取得する
            WEB3GentradeBranch l_branch = (WEB3GentradeBranch) l_finApp.getAccountManager().getBranch(l_lngBranchID);

            //計算サービスオブジェクトを作成する
            WEB3IfoBizLogicProvider l_ifoBizLogicProvider = null;
            l_ifoBizLogicProvider = (WEB3IfoBizLogicProvider) l_finApp.getTradingModule(ProductTypeEnum.IFO).getBizLogicProvider();

            //先物OP発注審査個別チェックオブジェクトを作成する
            WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = (WEB3IfoOrderManagerReusableValidations) WEB3IfoOrderManagerReusableValidations.getInstance();
            
            double l_dblCalcUnitPrice = 0D;
            String l_strProductCode = l_commission.getCommissionProductCode();
            WEB3IfoProductManagerImpl l_productManager = (WEB3IfoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();
            double l_dblcalcRestraintTurnover = 0D;
            
            if (l_dblLimitPrice == 0)
            {
                l_dblCalcUnitPrice = l_productManager.getCurrentPrice(l_futuresOptionTradedProduct);
                l_ifoEstimateDeliveryAmountCalcResult.setCalcUnitPrice(l_dblCalcUnitPrice);
            }
            else
            {
                l_ifoEstimateDeliveryAmountCalcResult.setCalcUnitPrice(l_dblLimitPrice);
            }

			l_dblcalcRestraintTurnover =
			l_ifoBizLogicProvider.calcRestraintTurnOver(l_dblQuantity,
																	l_ifoEstimateDeliveryAmountCalcResult.getCalcUnitPrice(),
																	l_lngBranchID,
																	l_strProductCode,
																	l_commission.isLimitPrice(),
																	l_futuresOptionTradedProduct);

            l_ifoEstimateDeliveryAmountCalcResult.setRestraintTurnover(l_dblcalcRestraintTurnover);
            MainAccountRow l_mainAccountRow = (MainAccountRow) l_subAccount.getMainAccount().getDataSourceObject();
            
            //isSkip金額チェック == falseの場合のみ、取引可能上限金額チェックを行う
            if (!l_blnIsSkipPriceCheck)
            {
                l_ifoOrderManagerReusableValidations.validateOrderMaxAmount(
                    l_branch,
                    l_dblcalcRestraintTurnover,
                    l_mainAccountRow.getAccountType(),
                    WEB3FuturesOptionDivDef.FUTURES);
            }
            
            //概算受渡代金をセットする 
            l_ifoEstimateDeliveryAmountCalcResult.setEstimateDeliveryAmount(l_dblcalcRestraintTurnover);
            l_commission.setExpensesCalcAmount(l_dblcalcRestraintTurnover);
            
            //委託手数料を算出する
            l_ifoBizLogicProvider.calcCommission(l_commission,l_subAccount);
            
            //委託手数料にかかる消費税を算出する
            double l_dblSalesTax = l_ifoBizLogicProvider.calcSalesTax(l_commission.getCommission(),l_commission.getOrderBizDate(),l_subAccount);
            
            //手数料コースをセットする
            l_ifoEstimateDeliveryAmountCalcResult.setCommissionCourse(l_commission.getCommissionCourseDiv());
            
            //手数料をセットする
            l_ifoEstimateDeliveryAmountCalcResult.setCommission(l_commission.getCommission());
            
            //手数料消費税をセットする
            l_ifoEstimateDeliveryAmountCalcResult.setCommissionTax(l_dblSalesTax);
            
            return l_ifoEstimateDeliveryAmountCalcResult;
        }
        catch (NotFoundException l_nfex)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME);
        }
    }

    /**
     * (calc訂正時概算建代金)<BR>
     *注文訂正時の概算建代金(手数料を含まない)を算出して返却する。<BR>
     *シーケンス図「(先物注文)calc訂正時概算建代金」参照。<BR>
     * @@param l_commission - (手数料)<BR>
     * @@param l_dblLimitPrice - 指値<BR>
     * <BR>
     * 0が指定された場合は、時価を計算単価として利用する。<BR>
     * 
     * @@param l_subAccount - (補助口座)<BR>
     * @@param l_futuresOptionTradedProduct - 先物OP取引銘柄<BR>
     * @@param l_dblQuantity - (数量)<BR>
     * 訂正注文数量<BR>
     * 
     * @@param l_dblExecQuantity -約定数量
     *  注文単位.約定数量<BR>
     * @@param l_dblSumTransferredAssetBookValue -合計約定金額 
     * 注文単位.合計約定金額<BR>
     * @@param l_blnIsSkipPriceCheck - (isSkip金額チェック)<BR>
     * 計算結果の代金について、妥当性チェックをスキップするかのフラグ。<BR>
     * <BR>
     * チェックを行う場合はfalse、チェックを行わない（スキップする）場合はtrueを指定する。<BR>
     * 
     * @@return webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult
     * @@roseuid 40C5AF5C0010
     */
    public WEB3IfoEstimateDeliveryAmountCalcResult calcChangeEstimatePrice(
        WEB3GentradeCommission l_commission,
        double l_dblLimitPrice,
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoTradedProductImpl l_futuresOptionTradedProduct,
        double l_dblQuantity,
        double l_dblExecQuantity,
        double l_dblSumTransferredAssetBookValue,
        boolean l_blnIsSkipPriceCheck)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "calcChangeEstimatePrice(WEB3GentradeCommission, double, WEB3GentradeSubAccount, " 
        + "WEB3IfoTradedProductImpl, double, double, double, boolean)";
        log.entering(STR_METHOD_NAME);

        BigDecimal l_bdSumTransferredAssetBookValue = new BigDecimal(l_dblSumTransferredAssetBookValue + "");

        //戻り値の概算受渡代金計算結果オブジェクトを生成する。
        WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult = new WEB3IfoEstimateDeliveryAmountCalcResult();
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3IfoProductManagerImpl l_productMgr = (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager();
        
        //計算サービスオブジェクトを作成する
        WEB3IfoBizLogicProvider l_ifoBizLogicProvider = null;
        l_ifoBizLogicProvider = (WEB3IfoBizLogicProvider) l_finApp.getTradingModule(ProductTypeEnum.IFO).getBizLogicProvider();
        
        //先物OP発注審査個別チェックオブジェクトを作成する
        WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = null;
        l_ifoOrderManagerReusableValidations =
             (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();

        //部店ＩＤを取得する。
        long l_lngBranchId = l_commission.getBranchId();
        
        if (l_dblLimitPrice == 0)
        {
			double l_dblCurrentPrice = l_productMgr.getCurrentPrice(l_futuresOptionTradedProduct);
			l_ifoEstimateDeliveryAmountCalcResult.setCalcUnitPrice(l_dblCurrentPrice);
        }
        else
        {
			l_ifoEstimateDeliveryAmountCalcResult.setCalcUnitPrice(l_dblLimitPrice);
        }
        
                
        //手数料商品コードを取得する。
        String l_strCommissionProductCode = l_commission.getCommissionProductCode();
        
        //拘束売買代金
        double l_dblRestraintTurnOver = 0;
        //拘束売買代金を計算する。
        l_dblRestraintTurnOver = l_ifoBizLogicProvider.calcRestraintTurnOver((l_dblQuantity - l_dblExecQuantity),
                    l_ifoEstimateDeliveryAmountCalcResult.getCalcUnitPrice(),
                    l_lngBranchId,
                    l_strCommissionProductCode,
                    l_commission.isLimitPrice(),
                    l_futuresOptionTradedProduct);
        
        BigDecimal l_bdRestraintTurnOver = new BigDecimal(l_dblRestraintTurnOver + "");

        //戻り値オブジェクトに拘束売買代金をセットする。
        log.debug("拘束売買代金 = " + l_dblRestraintTurnOver);
        l_ifoEstimateDeliveryAmountCalcResult.setRestraintTurnover(l_dblRestraintTurnOver);
        
        //手数料オブジェクトに諸経費計算用代金をセットする。   
        //諸経費計算用代金：拘束売買代金 + 引数.合計約定金額
        log.debug("諸経費計算用代金 = " + (l_bdRestraintTurnOver.add(l_bdSumTransferredAssetBookValue).doubleValue()));
        l_commission.setExpensesCalcAmount(l_bdRestraintTurnOver.add(l_bdSumTransferredAssetBookValue).doubleValue());  
        
        //戻り値オブジェクトに概算受渡代金をセットする。
        l_ifoEstimateDeliveryAmountCalcResult.setEstimateDeliveryAmount(l_commission.getExpensesCalcAmount());
         
        //isSkip金額チェック == falseの場合のみ、取引可能上限金額チェックを行う
        if (!l_blnIsSkipPriceCheck)
        {
            MainAccountRow l_mainAccountRow = (MainAccountRow) l_subAccount.getMainAccount().getDataSourceObject();
            l_ifoOrderManagerReusableValidations.validateOrderMaxAmount(
                    l_subAccount.getWeb3GenBranch(),
                    l_commission.getExpensesCalcAmount(),
                    l_mainAccountRow.getAccountType(),
                    WEB3FuturesOptionDivDef.FUTURES);
        }
        
        //委託手数料を算出する。
        l_ifoBizLogicProvider.calcCommission(l_commission, l_subAccount);
        
        //委託手数料にかかる消費税を算出する。
        double l_dblSalesTax = l_ifoBizLogicProvider.calcSalesTax(l_commission.getCommission(),l_commission.getOrderBizDate(),l_subAccount);
        
        //手数料コースをセットする。
        l_ifoEstimateDeliveryAmountCalcResult.setCommissionCourse(l_commission.getCommissionCourseDiv());
        
        //手数料をセットする。
        log.debug("手数料金額 = " + l_commission.getCommission());
        l_ifoEstimateDeliveryAmountCalcResult.setCommission(l_commission.getCommission());
        
        //手数料消費税をセットする。
        log.debug("手数料消費税 = " + l_dblSalesTax);
        l_ifoEstimateDeliveryAmountCalcResult.setCommissionTax(l_dblSalesTax);
        
        //概算受渡代金計算結果オブジェクトを返却する。
        return l_ifoEstimateDeliveryAmountCalcResult;
    }
    
    /**
     * (update先物概算受渡代金)<BR>
     * 先物の概算受渡代金を更新する。<BR>
     * １）　@注文単位テーブルの下記項目を更新する。<BR>
     *  ・概算受渡代金<BR>
     *  ・注文単価<BR>
     *  ・市場から確認済みの概算受渡代金<BR>
     *  ・市場から確認済みの注文単価<BR>
     * ２）　@注文履歴テーブルの概算受渡代金を更新する。<BR>
     *  シーケンス図「（先物注文）update概算受渡代金」参照。<BR>
     * @@param l_orderUnit - 注文単位オブジェクト
     * @@return void
     * @@throws WEB3BaseException
     * @@roseuid 40A459EC0365
     */
    public void updateFuturesEstimateDeliveryAmount(OrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateFuturesEstimateDeliveryAmount(l_orderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }

        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
            // 概算受渡代金
            double l_dblEstimateDeliveryAmount = 0D;
            // 注文単価
            double l_dblPrice = 0D;
            
            log.debug("注文単位.getOrderOpenStatus() = " + l_orderUnit.getOrderOpenStatus());
            log.debug("注文単位.isFullyExecuted() = " + l_orderUnit.isFullyExecuted());
            log.debug("注文単位.失効区分 = " + l_orderUnit.getExpirationStatus());
            
            //(*1)全部出来、または、失効済の場合(引数.注文単位.isFullyExecuted() == true || 引数.注文単位.失効区分 == "マーケット拒否")
            if ((l_orderUnit.isFullyExecuted()) || 
                OrderExpirationStatusEnum.INVALIDATED_BY_MKT.equals(l_orderUnit.getExpirationStatus()))
            {
                log.debug("全部出来、または、失効済の場合");
                //1.1.1 get受渡金額合計(注文単位 : IfoOrderUnit)
                // 更新値の設定
                //  概算受渡代金 = get受渡金額合計()
                //  注文単価 = 注文単位.注文単価（既存値のまま）
                l_dblEstimateDeliveryAmount = super.getNetAmount((IfoOrderUnit)l_orderUnit);
                l_dblPrice = l_orderUnitRow.getPrice();
                log.debug("l_dblEstimateDeliveryAmount = " + l_dblEstimateDeliveryAmount);
                log.debug("l_dblPrice = " + l_dblPrice);
            }            
            //(*2)全部出来、または、失効済以外の場合((*1)以外)
            else
            {
                log.debug("全部出来、または、失効済以外の場合");
                //1.2.1 create手数料(注文単位ID : long)
                // 手数料オブジェクトを生成する。
                // [引数の設定]
                // 注文単位ID：　@引数.注文単位.注文単位ID
                WEB3IfoBizLogicProvider l_bizLogicProvider = (WEB3IfoBizLogicProvider)l_tradingMod.getBizLogicProvider();
                WEB3GentradeCommission l_commission = l_bizLogicProvider.createCommission(l_orderUnit.getOrderUnitId());
    
                //1.2.2 (*)新規建の場合(引数.注文単位.注文カテゴリ == "先物新規建注文")
                WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(
                            l_orderUnit.getAccountId(),
                            l_orderUnit.getSubAccountId());
                if (OrderCategEnum.IDX_FUTURES_OPEN.equals(l_orderUnit.getOrderCateg()))
                {
                    //1.2.2.1 calc訂正時概算建代金()
                    //[引数]
                    //  手数料： create手数料()の戻り値
                    //  指値： 注文単位.指値
                    //  補助口座： 注文単位.補助口座IDから取得される補助口座オブジェクト
                    //  先物OP取引銘柄： 注文単位.getTradedProduct()の戻り値
                    //  数量： 注文単位.数量
                    //  約定数量： 注文単位.約定数量
                    //  合計約定金額： 注文単位.合計約定金額
                    //  isSkip金額チェック： true(スキップする)
                    WEB3IfoEstimateDeliveryAmountCalcResult l_result = calcChangeEstimatePrice(
                        l_commission,
                        l_orderUnitRow.getLimitPrice(),
                        l_subAccount,
                        (WEB3IfoTradedProductImpl)l_orderUnit.getTradedProduct(),
                        l_orderUnitRow.getQuantity(),
                        l_orderUnitRow.getExecutedQuantity(),
                        l_orderUnitRow.getExecutedAmount(),
                        true);
                         
                    //更新値の設定
                    //概算受渡代金 = calc訂正時概算建代金()の戻り値の概算受渡代金計算結果.概算受渡代金
                    //注文単価 = calc訂正時概算建代金()の戻り値の概算受渡代金計算結果.計算単価
                    l_dblEstimateDeliveryAmount = l_result.getEstimateDeliveryAmount();
                    l_dblPrice = l_result.getCalcUnitPrice();
                }
                //1.2.3 (*)返済の場合(引数.注文単位.注文カテゴリ == "先物返済注文")
                else if (OrderCategEnum.IDX_FUTURES_CLOSE.equals(l_orderUnit.getOrderCateg()))
                {
                    //1.2.3.1 create返済建玉エントリ(注文単位ID : long)
                    WEB3IfoPositionManagerImpl l_positionManager = 
                        (WEB3IfoPositionManagerImpl)l_finApp.getTradingModule(
                            ProductTypeEnum.IFO).getPositionManager();
                    SettleContractEntry[] l_contractEntry = l_positionManager.createSettleContractEntry(l_orderUnit.getOrderUnitId());
                     
                    //1.2.3.2 calc訂正時概算決済損益(手数料, double, SubAccount, 先物OP取引銘柄, SettleContractEntry[], double, doubl
                    //[引数]
                    //  手数料： create手数料()の戻り値
                    //  指値： 注文単位.指値
                    //  補助口座： 注文単位.補助口座IDから取得される補助口座オブジェクト
                    //  先物OP取引銘柄： 注文単位.getTradedProduct()の戻り値
                    //  返済建玉エントリ：　@create返済建玉エントリ()の戻り値
                    //  数量： 注文単位.数量
                    //  約定数量： 注文単位.約定数量
                    //  注文単位ID：　@注文単位.注文単位ID
                    //  isSkip金額チェック： true（スキップする）
                    //  売買：
                    //    注文単位.getSide() = ”買”の場合、”売”
                    //    注文単位.getSide() = ”売”の場合、”買”
                    SideEnum l_dealing = null;
                    if (SideEnum.BUY.equals(l_orderUnit.getSide()))
                    {
                        l_dealing = SideEnum.SELL;
                    }
                    else if (SideEnum.SELL.equals(l_orderUnit.getSide()))
                    {
                        l_dealing = SideEnum.BUY;
                    }
                    WEB3IfoEstimateDeliveryAmountCalcResult l_result = calcChangeEstimateSettlementIncome(
                        l_commission,
                        l_orderUnitRow.getLimitPrice(),
                        l_subAccount,
                        (WEB3IfoTradedProductImpl)l_orderUnit.getTradedProduct(),
                        l_contractEntry,
                        l_orderUnitRow.getQuantity(),
                        l_dealing,
                        l_orderUnitRow.getExecutedQuantity(),
                        l_orderUnitRow.getOrderUnitId(),
                        true);

                    //更新値の設定
                    //概算受渡代金 = calc訂正時概算決済損益()の戻り値の概算受渡代金計算結果.概算受渡代金
                    //注文単価          = calc訂正時概算決済損益()の戻り値の概算受渡代金計算結果.計算単価                     
                    l_dblEstimateDeliveryAmount = l_result.getEstimateDeliveryAmount();
                    l_dblPrice = l_result.getCalcUnitPrice();
                }
                
                log.debug("l_dblEstimateDeliveryAmount = " + l_dblEstimateDeliveryAmount);
                log.debug("l_dblPrice = " + l_dblPrice);
            }
            
            //1.3 (*)注文単位テーブルの更新
            //以下の条件で注文単位テーブルを検索し、取得した行を下記更新内容で更新する。
            //[条件]
            //注文単位テーブル.注文単位ID =　@注文単位.注文単位ID 
            //[更新内容]
            //注文単位テーブル.注文単価 = 上記で設定した注文単価
            //注文単位テーブル.市場から確認済みの注文単価 = 上記で設定した注文単価
            //注文単位テーブル.概算受渡代金 = 上記で設定した概算受渡代金
            //注文単位テーブル.市場から確認済みの概算受渡代金 = 上記で設定した概算受渡代金
            OrderUnit l_orderUnitSch = getOrderUnit(l_orderUnit.getOrderUnitId());
            IfoOrderUnitRow l_orderUnitRowSch = (IfoOrderUnitRow)l_orderUnitSch.getDataSourceObject();
            IfoOrderUnitParams l_orderUnitParam = new IfoOrderUnitParams(l_orderUnitRowSch);

            l_orderUnitParam.setPrice(l_dblPrice);
            l_orderUnitParam.setConfirmedOrderPrice(l_dblPrice);
            l_orderUnitParam.setEstimatedPrice(l_dblEstimateDeliveryAmount);
            l_orderUnitParam.setConfirmedEstimatedPrice(l_dblEstimateDeliveryAmount);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();    
            l_queryProcessor.doUpdateQuery(l_orderUnitParam);
            log.debug("注文単位テーブルの更新 " + l_orderUnit.getOrderUnitId());
            
            //1.4 (*)注文履歴テーブルの更新
            //以下の条件で注文履歴テーブルを検索し、取得した行を下記更新内容で更新する。
            //[条件]
            //注文履歴テーブル.注文単位ID　@    =　@注文単位.注文単位ID and 
            //注文履歴テーブル.注文履歴番号　@=　@注文単位.注文履歴最終通番 
            //[更新内容]
            //注文履歴テーブル.概算受渡代金　@= 上記で設定した概算受渡代金
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" order_unit_id = ? ");            
            l_sbWhere.append(" and order_action_serial_no = ? ");
    
            Object[] l_objWhere = { 
                String.valueOf(l_orderUnit.getOrderUnitId()),
                String.valueOf(l_orderUnitRow.getLastOrderActionSerialNo())};

            List l_lisRecords = l_queryProcessor.doFindAllQuery(
                IfoOrderActionRow.TYPE,
                l_sbWhere.toString(),
                null,
                "FOR UPDATE",
                l_objWhere);
            if (l_lisRecords.size() > 0)
            {
                IfoOrderActionRow l_actionRow = (IfoOrderActionRow)l_lisRecords.get(0);
                IfoOrderActionParams l_actionParams = new IfoOrderActionParams(l_actionRow);
                l_actionParams.setEstimatedPrice(l_dblEstimateDeliveryAmount);

                l_queryProcessor.doUpdateQuery(l_actionParams);
                log.debug("注文単位テーブルの更新 " + l_actionRow.getOrderActionId());
            }
        }
        catch (DataQueryException l_dqe)
        {
            log.error("DBへのアクセスに失敗しました。", l_dqe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error("DBへのアクセスに失敗しました。", l_dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        catch (NotFoundException l_nfe)
        {
            log.error("DBへのアクセスに失敗しました。", l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate先物新規建注文)<BR>
     * 先物新規建注文発注審査を行う。<BR>
     * <BR>
     * this.validate先物新規建注文()に処理を委譲（delegate）する。<BR>
     * <BR>
     * [validate先物新規建注文()に指定する引数]<BR>
     * 　@補助口座：　@パラメータ.補助口座<BR>
     * 　@新規建注文内容：　@パラメータ.新規建注文内容<BR>
     * 　@注文単位：　@null<BR>
     * @@param l_subAccount - 補助口座
     * @@param l_openContractOrderSpec - 新規建注文内容
     * @@return NewOrderValidationResult
     */
    public NewOrderValidationResult validateFuturesOpenContractOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoOpenContractOrderSpec l_openContractOrderSpec)
    {
        final String STR_METHOD_NAME =
            "validateFuturesOpenContractOrder(WEB3GentradeSubAccount, WEB3IfoOpenContractOrderSpec)";
        log.entering(STR_METHOD_NAME);
        
        NewOrderValidationResult l_result =
            this.validateFuturesOpenContractOrder(
                l_subAccount,
                l_openContractOrderSpec,
                null);
        
        log.exiting(STR_METHOD_NAME);
        return l_result;
    }
}
@
