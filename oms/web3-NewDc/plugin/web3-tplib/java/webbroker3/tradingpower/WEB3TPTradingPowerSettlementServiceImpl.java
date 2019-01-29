head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.55.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerSettlementServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 差金決済取引余力取得サービスImpl(WEB3TPTradingPowerSettlementServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/22 nakazato(ACT) 新規作成
Revesion History : 2007/09/28 トウ鋒鋼（中訊）モデルNo.189
Revesion History : 2007/10/10 トウ鋒鋼（中訊）モデルNo.205
Revesion History : 2007/10/22 孟亞南（中訊）実装の問題No.005
Revesion History : 2008/03/14 崔遠鵬 (中訊) モデルNo.263、No.264
*/
package webbroker3.tradingpower;

import java.util.Date;
import java.util.List;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpower.define.WEB3TPExcludeExceptSettlementBuyAmountCheckDef;
import webbroker3.tradingpower.define.WEB3TPMarkToMarketDivDef;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.updtpower.WEB3TPNewOrderSpec;
import webbroker3.tradingpower.updtpower.settlement.WEB3TPSettlementReflector;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;

/**
 * (差金決済取引余力サービスImpl)
 */
public class WEB3TPTradingPowerSettlementServiceImpl implements WEB3TPTradingPowerSettlementService
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPTradingPowerSettlementServiceImpl.class);

    /**
     * (コンストラクタ)
     */
    public WEB3TPTradingPowerSettlementServiceImpl()
    {
    }

    /**
     * （get差金決済買付可能額）<BR>
     * <BR>
     * シーケンス図「(差金決済取引余力サービス)get差金決済買付可能額」参照
     * <BR>
     * @@param l_subAccount - （補助口座）
     * @@param l_datSpecifiedDate - （指定日）
     * @@param l_lngOrderFundId - （注文銘柄ID）
     * @@param l_blnTodayRepFund - （即日入金対象銘柄フラグ）
     * 　@・指定銘柄が即日入金対象銘柄の場合⇒true
     * 　@・指定銘柄が即日入金対象銘柄でない場合⇒false
     * @@return double
     */
    public double getBuyOrderPossibleAmount(
        WEB3GentradeSubAccount l_subAccount,
        Date l_datSpecifiedDate,
        long l_lngOrderFundId,
        boolean l_blnTodayRepFund)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "getBuyOrderPossibleAmount(WEB3GentradeSubAccount, Date, long, boolean)";
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
            //口座IDを取得
            long l_lngAccountId = l_subAccount.getAccountId();

            //顧客オブジェクトを取得
            WEB3GentradeMainAccount l_mainAccount =
                (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

            //信用口座開設区分を取得
            boolean l_blnMargin =
                l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

            //余力計算条件オブジェクトを生成
            WEB3TPCalcCondition l_calcCond =
                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            //余力更新オブジェクトを生成
            WEB3TPTradingPowerUpd l_tpUpd =
                new WEB3TPTradingPowerUpd(l_lngAccountId, l_blnMargin, l_calcCond, null);

            //使用可能現金情報
            WEB3TPActualAccountBalanceInfo l_actualBalInfo = null;

            //引数.指定日≠nullの場合
            if(l_datSpecifiedDate != null)
            {
                //差金決済買付可能額を計算
                double l_dblSettlementBuyAmt =
                    l_tpUpd.settlement.getBuyOrderPossibleAmount(l_datSpecifiedDate, l_lngOrderFundId);

                //引数.指定日をint型に変換する。
                int l_intSpecifiedDate = l_calcCond.calcSpecifiedPoint(l_datSpecifiedDate);
                //int型に変換した指定日を、余力計算基準日<株式買付/信用現引>にセット
                l_calcCond.setEquityBasePoint(l_intSpecifiedDate);

                //差金決済買付可能額≠-1の場合
                if(l_dblSettlementBuyAmt != -1)
                {
                    /*
                     * 使用可能現金情報オブジェクトを生成
                     */
                    l_actualBalInfo = new WEB3TPActualAccountBalanceInfo();
                    l_actualBalInfo.settlementBuyAmount = l_dblSettlementBuyAmt;
                    l_actualBalInfo.specifiedPoint = l_intSpecifiedDate;
                }
            }

            //余力計算結果
            WEB3TPCalcResult l_calcResult = null;

            //現物顧客の場合
            if (l_blnMargin == false)
            {
                /*
                 * 余力計算結果(List)を取得
                 */
                List l_updResults = l_tpUpd.calcTradingpowerUpdResultEquity();

                //資産余力情報オブジェクトを生成
                WEB3TPTradingPowerCalcEquity l_calcEquity =
                    new WEB3TPTradingPowerCalcEquity(l_updResults, l_actualBalInfo, l_calcCond);

                //引数.即日入金対象銘柄フラグ==trueの場合
                if (l_blnTodayRepFund == true)
                {
                    //余力計算結果オブジェクトを取得
                    l_calcResult = l_calcEquity.calcAppliedEquityTradingPowerTodayDepFund();
                }
                //以外(引数.即日入金対象銘柄フラグ==false)の場合
                else
                {
                    //余力計算結果オブジェクトを取得
                    l_calcResult = l_calcEquity.calcAppliedEquityTradingPower();
                }
            }
            //以外(信用顧客)の場合
            else
            {
                /*
                 * 余力計算結果(List)を取得
                 */
                List l_updResults =
                    l_tpUpd.calcTradingpowerUpdResultMargin(WEB3TPMarkToMarketDivDef.NORMAL);

                //資産余力情報オブジェクトを生成
                WEB3TPTradingPowerCalcMargin l_calcMargin =
                    new WEB3TPTradingPowerCalcMargin(l_updResults, l_actualBalInfo, l_calcCond);

                //引数.即日入金対象銘柄フラグ==trueの場合
                if (l_blnTodayRepFund == true)
                {
                    //余力計算結果オブジェクトを取得
                    l_calcResult = l_calcMargin.calcAppliedEquityTradingPowerTodayDepFund();
                }
                //以外(引数.即日入金対象銘柄フラグ==false)の場合
                else
                {
                    //余力計算結果オブジェクトを取得
                    l_calcResult = l_calcMargin.calcAppliedEquityTradingPower();
                }
            }

            //0補正
            double l_dblTradingPower = Math.max(0, l_calcResult.tradingPower);
            log.debug("差金決済取引可能額 = " + Double.toString(l_dblTradingPower));

            //差金決済買付可能額(=余力計算結果.取引可能額)を返却
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
     * （get差金決済売付可能数量）<BR>
     * <BR>
     * シーケンス図「(差金決済取引余力サービス)get差金決済売付可能数量」参照<BR>
     * <BR>
     * @@param l_subAccount - （補助口座）
     * @@param l_datSpecifiedDate - （指定日）
     * @@param l_lngOrderFundId - （注文銘柄ID）
     * @@param l_lngMarketId - （市場ID）
     * @@param l_dblLimitPrice - （指値）
     * @@param l_dblLotSize - （売買単位）
     * @@return double
     */
    public double getSellOrderPossibleQuantity(
        WEB3GentradeSubAccount l_subAccount,
        Date l_datSpecifiedDate,
        long l_lngOrderFundId,
        long l_lngMarketId,
        double l_dblLimitPrice,
        double l_dblLotSize) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getSellOrderPossibleQuantity(WEB3GentradeSubAccount, Date, long, long, double, double)";
        log.entering(STR_METHOD_NAME);

        //引数.補助口座または、引数.指定日がnullの場合
        if(l_subAccount == null || l_datSpecifiedDate == null)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass()
                .getName()
                + "."
                + STR_METHOD_NAME);
        }

        try
        {
            //口座ID
            long l_lngAccountId = l_subAccount.getAccountId();
            //顧客オブジェクト
            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
            //信用口座開設区分
            boolean l_blnMargin = l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

            //余力計算条件
            WEB3TPCalcCondition l_calcCond = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);
            //指定日(:int型)
            int l_intSpecifiedDate = l_intSpecifiedDate = l_calcCond.calcSpecifiedPoint(l_datSpecifiedDate);

            /*
             * (注文前)引出可能現金を取得
             */
            //(注文前)売付時余力更新
            WEB3TPTradingPowerUpdAfterSell l_tpUpdBefore = new WEB3TPTradingPowerUpdAfterSell(
                l_lngAccountId,
                l_blnMargin,
                l_calcCond,
                null,
                l_lngOrderFundId);

            //(注文前)引出可能現金
            double l_dblActPayBalBefore;

            //現物顧客の場合
            WEB3TPTradingPowerCalcEquity l_calcEquityBefore = null;
            if(l_blnMargin == false)
            {
                l_calcEquityBefore = new WEB3TPTradingPowerCalcEquity(
                    l_tpUpdBefore.calcTradingpowerUpdResultEquityIncUnexecSellOrder(),
                    l_calcCond);
                l_dblActPayBalBefore = l_calcEquityBefore.calcActualPaymentBalance(l_intSpecifiedDate);
            }
            //信用顧客の場合
            else
            {
                WEB3TPTradingPowerCalcMargin l_calcMarginBefore = new WEB3TPTradingPowerCalcMargin(
                    l_tpUpdBefore.calcTradingpowerUpdResultMarginIncUnexecSellOrder(),
                    l_calcCond);
                l_dblActPayBalBefore = l_calcMarginBefore.calcActualPaymentBalance(l_intSpecifiedDate);
            }

            /*
             * 注文単価を取得
             */
            //注文単価
            double l_dblPrice;

            //引数.指値 = 0の場合
            if(l_dblLimitPrice == 0)
            {
                //時価を取得
                l_dblPrice = l_calcCond.getEqtypeQuote(l_lngOrderFundId, l_lngMarketId);

                //時価 = 0の場合
                if(l_dblPrice == 0)
                {
                    //T+0を取得
                    Date l_datT0 = l_calcCond.getBizDate(WEB3TPSpecifiedPointDef.T_0);

                    //終値を取得
                    l_dblPrice = l_calcCond.getClosingPrice(
                        l_lngOrderFundId,
                        l_datT0,
                        l_lngMarketId,
                        false);

                    //終値 = 0 の場合
                    if(l_dblPrice == 0)
                    {
                        /*
                         * 前日終値を取得
                         */
                        EqtypeTradedProductRow l_row = EqtypeTradedProductDao.findRowByInstitutionCodeProductIdMarketIdValidUntilBizDate(
                            l_mainAccount.getInstitution().getInstitutionCode(),
                            l_lngOrderFundId,
                            l_lngMarketId,
                            WEB3DateUtility.formatDate(l_datT0, "yyyyMMdd"));

                        if(l_row != null)
                        {
                            l_dblPrice = l_row.getLastClosingPrice();
                        }
                    }
                }
            }
            //以外の場合
            else
            {
                l_dblPrice = l_dblLimitPrice;
            }

            /*
             * (最大/最小)注文数量を取得
             *              
             * 注）売買単位で丸める
             */
            //銘柄ごと取引情報
            WEB3TPSettlementReflector l_orderReflector = l_tpUpdBefore.getSettlement()
                .getSettlementReflector(l_datSpecifiedDate, l_lngOrderFundId);

            //注文数量 = 最大売付可能数量( = 指定日前日保有数量 + 買付数量　@- 売付数量　@- 未約定売付数量)
            double l_dblOrderQty = l_orderReflector.getExistQuantity()
                + l_orderReflector.getBuyQuantity()
                - l_orderReflector.getSellQuantity()
                - l_orderReflector.getUnexecutedSellQuantity();
            //売買単位で丸める
            l_dblOrderQty = Math.floor(l_dblOrderQty / l_dblLotSize) * l_dblLotSize;
            
            //最小売付可能数量 = MAX( (指定日前日保有数量 - 売付数量　@- 未約定売付数量), 0)
            double l_dblMinOrderQty = l_orderReflector.getExistQuantity()
                - l_orderReflector.getSellQuantity()
                - l_orderReflector.getUnexecutedSellQuantity();
            l_dblMinOrderQty = Math.max(l_dblMinOrderQty, 0);
            //売買単位で丸める
            l_dblMinOrderQty = Math.floor(l_dblMinOrderQty / l_dblLotSize) * l_dblLotSize;
            
            /*
             * 現注文内容を生成
             */
            WEB3TPNewOrderSpec l_newOrderSpec = new WEB3TPNewOrderSpec();
            //補助口座ID
            l_newOrderSpec.setSubAccountId(l_subAccount.getSubAccountId());
            //補助口座タイプ
            l_newOrderSpec.setSubAccountType(l_subAccount.getSubAccountType());
            //注文ID
            l_newOrderSpec.setOrderId(WEB3TPNewOrderSpec.DEFAULT_NEW_ID);
            //注文単位ID
            l_newOrderSpec.setOrderUnitId(WEB3TPNewOrderSpec.DEFAULT_NEW_ID);
            //銘柄ID
            l_newOrderSpec.setProductId(l_lngOrderFundId);
            //銘柄タイプ
            l_newOrderSpec.setProductType(ProductTypeEnum.EQUITY);
            //市場ID
            l_newOrderSpec.setMarketId(l_lngMarketId);
            //注文カテゴリ
            l_newOrderSpec.setOrderCategory(OrderCategEnum.ASSET);
            //注文タイプ
            l_newOrderSpec.setOrderType(OrderTypeEnum.EQUITY_SELL);
            //数量
            l_newOrderSpec.setQuantity(l_dblOrderQty);
            //単価
            l_newOrderSpec.setPrice(l_dblPrice);
            //指値
            l_newOrderSpec.setLimitPrice(l_dblLimitPrice);
            //概算代金
            l_newOrderSpec.setEstimatedPrice(l_dblPrice * l_dblOrderQty);
            //発注日
            l_newOrderSpec.setOrderBizDate(l_calcCond.getBizDate(WEB3TPSpecifiedPointDef.T_0));
            //受渡日
            l_newOrderSpec.setDeliveryDate(l_datSpecifiedDate);
            //返済指定情報
            l_newOrderSpec.setContractSettleSpecify(null);
            //譲渡損益
            l_newOrderSpec.setCapitaGain(0);
            //税区分
            l_newOrderSpec.setTaxType(TaxTypeEnum.NORMAL);
            //出金申込区分
            l_newOrderSpec.setPaymentApplicationDiv(null);
            //受付日時
            l_newOrderSpec.setReceivedDateTime(null);
            WEB3TPNewOrderSpec[] l_newOrderSpecs = {l_newOrderSpec};

            /*
             * (注文後)売付時余力更新
             */
            WEB3TPTradingPowerUpdAfterSell l_tpUpdAfter = new WEB3TPTradingPowerUpdAfterSell(
                l_lngAccountId,
                l_blnMargin,
                l_calcCond,
                l_newOrderSpecs,
                l_lngOrderFundId);

            /*
             * 最大LOOP回数を取得
             */
            String l_strMaxLoopCount = GtlUtils.getTradingSystem().getPreference(
                "system.tradingpower.posssellorder.loopcount");
            int l_intMaxLoopCount = Integer.MAX_VALUE;
            if(l_strMaxLoopCount != null)
            {
                l_intMaxLoopCount = Integer.parseInt(l_strMaxLoopCount);
            }

            //(注文後)引出可能現金
            double l_dblActPayBalAfter = 0;

            for(int l_intLoopCnt = 0; l_intLoopCnt < l_intMaxLoopCount; l_intLoopCnt++)
            {
                //LOOP処理回数が0以外の場合
                if(l_intLoopCnt != 0)
                {
                    //注文数量 = 注文数量 - 売買単位
                    l_dblOrderQty = l_dblOrderQty - l_dblLotSize;

                    /*
                     * (*)分岐フロー：注文数量が最小売付可能数量以下である場合
                     */
                    if(l_dblOrderQty <= l_dblMinOrderQty)
                    {
                        log.exiting(STR_METHOD_NAME);
                        return l_dblMinOrderQty;
                    }

                    //今回売付注文分取引情報の内容をリセットする。
                    l_tpUpdAfter.reSetWEB3TPTransactionReflectorNewSellOrder(l_dblOrderQty);
                }

                /*
                 * (注文後)引出可能現金を取得
                 */
                //現物顧客の場合
                WEB3TPTradingPowerCalcEquity l_calcEquityAfter = null;
                if(l_blnMargin == false)
                {
                    l_calcEquityAfter = new WEB3TPTradingPowerCalcEquity(
                        l_tpUpdAfter.calcTradingpowerUpdResultEquityIncUnexecSellOrder(),
                        l_calcCond);
                    l_dblActPayBalAfter = l_calcEquityAfter.calcActualPaymentBalance(l_intSpecifiedDate);
                }
                //信用顧客の場合
                else
                {
                    WEB3TPTradingPowerCalcMargin l_calcMarginAfter = new WEB3TPTradingPowerCalcMargin(
                        l_tpUpdAfter.calcTradingpowerUpdResultMarginIncUnexecSellOrder(),
                        l_calcCond);
                    l_dblActPayBalAfter = l_calcMarginAfter.calcActualPaymentBalance(l_intSpecifiedDate);
                }

                //差金決済相当外買付代金非考慮の(注文後)引出可能現金
                double l_dblActualPaymentBalance = 0;

                //(*)分岐フロー
                //現物顧客（顧客.is信用口座開設() == false）、かつ、
                // (注文後)引出可能現金 < 0、かつ、
                // (注文前)引出可能現金 <= (注文後)引出可能現金の場合
                if (!l_blnMargin && l_dblActPayBalAfter < 0 && l_dblActPayBalBefore <= l_dblActPayBalAfter)
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
                    double l_dblDayTradeRestraintBefore = l_calcEquityBefore.getDayTradeRestraint(l_intSpecifiedDate);

                    //get日計り拘束金(int)
                    //（注文後）日計り拘束金を取得する。
                    //[引数]
                    //受渡日:余力計算条件.calc指定日()の戻り値
                    double l_dblDayTradeRestraintAfter = l_calcEquityAfter.getDayTradeRestraint(l_intSpecifiedDate);

                    // (*)分岐フロー
                    // 差金決済相当外買付代金非考慮の差金決済チェックする(get会社部店別余力計算条件()の戻り値 == 1：実施する)かつ、
                    // is預り証券評価制()の戻り値 == true かつ、
                    // (注文前)get日計り拘束金(T+受渡日) < (注文後)get日計り拘束金(T+受渡日) の場合
                    if (WEB3TPExcludeExceptSettlementBuyAmountCheckDef.EXECUTE.equals(
                        l_strExcludeExceptSettlementBuyAmountCheck)
                        && l_blnIsAssetEvalDiv && l_dblDayTradeRestraintBefore < l_dblDayTradeRestraintAfter)
                    {
                        //calc余力更新内容<現物顧客>～未約定売付注文考慮～(boolean)
                        List l_lisOrders = l_tpUpdAfter.calcTradingpowerUpdResultEquityIncUnexecSellOrder(true);

                        //資産余力情報<現物顧客>(List, 余力計算条件)
                        l_calcEquityAfter =
                            new WEB3TPTradingPowerCalcEquity(l_lisOrders, l_calcCond);

                        //calc引出可能現金(int)
                        l_dblActualPaymentBalance =
                            l_calcEquityAfter.calcActualPaymentBalance(l_intSpecifiedDate);
                    }
                }

                // (*)分岐フロー：注文数量が“発注可”である場合
                // 差金決済相当外買付代金非考慮の(注文後)引出可能現金 >= 0 かつ、
                // (「(注文後)引出可能現金 >= 0」 または
                // 「(注文後)引出可能現金 >= (注文前)引出可能現金」)の場合
                if(l_dblActualPaymentBalance >= 0 && (l_dblActPayBalAfter >= 0
                    || l_dblActPayBalAfter >= l_dblActPayBalBefore))
                {
                    log.exiting(STR_METHOD_NAME);
                    return l_dblOrderQty;
                }
            }

            /*
             * 売付可能数量を計算する。
             * [計算式]
             * 　@売付可能数量 = MAX( 注文数量 - ABS( (注文後)引出可能現金 / 平均単価(*) ), 最小売付可能数量))
             * 
             * (*)平均単価 = 買付代金 / 買付数量
             * 
             * 注）売買単位で丸める
             * 
             */
            //平均単価
            double l_dblAve = l_orderReflector.getBuyAmount() / l_orderReflector.getBuyQuantity();

            //売付可能数量
            double l_dblPossSell = Math.max(l_dblOrderQty
                - Math.abs(l_dblActPayBalAfter / l_dblAve), l_dblMinOrderQty);
            //売買単位で丸める
            l_dblPossSell = Math.floor(l_dblPossSell / l_dblLotSize) * l_dblLotSize;
            
            //売付可能数量を返却
            log.exiting(STR_METHOD_NAME);
            return Math.max(l_dblPossSell, 0);
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
}
@
