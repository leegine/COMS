head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.55.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerAfterRepayServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 返済後余力サービスImpl(WEB3TPTradingPowerAfterRepayServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/11 nakazato(ACT) 新規作成
*/

package webbroker3.tradingpower;

import java.util.List;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpower.define.WEB3TPAttentionObjectionTypeDef;
import webbroker3.tradingpower.define.WEB3TPMarkToMarketDivDef;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.updtpower.WEB3TPNewOrderSpec;
import webbroker3.util.WEB3LogUtility;

/**
 * (返済後余力サービスImpl)
 */
public class WEB3TPTradingPowerAfterRepayServiceImpl implements WEB3TPTradingPowerAfterRepayService
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPTradingPowerAfterRepayServiceImpl.class);

    /**
     * @@roseuid 41E384280038
     */
    public WEB3TPTradingPowerAfterRepayServiceImpl()
    {

    }

    /**
     * (create返済後資産余力情報<信用顧客>)<BR>
     * <BR>
     * 返済後余力オブジェクトを作成する。<BR>
     * <BR>
     * シーケンス図「(返済後余力サービスImpl)create返済後資産余力情報」参照<BR>
     * <BR>
     * @@param l_subAccount
     * @@param l_orderSpecIntercepter
     * @@param l_orderSpec
     * @@return webbroker3.tradingpower.WEB3TPTradingPowerCalcMarginAfterRepay
     * @@roseuid 41E384280076
     */
    public WEB3TPTradingPowerCalcMarginAfterRepay createWEB3TPTradingPowerCalcAfterRepay(
        WEB3GentradeSubAccount l_subAccount,
        Object l_orderSpecIntercepter,
        Object l_orderSpec)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "WEB3TPTradingPowerAfterRepayServiceImpl.createWEB3TPTradingPowerCalcAfterRepay(WEB3GentradeSubAccount, Object, Object)";
        log.entering(STR_METHOD_NAME);

        //引数.補助口座がnullの場合
        if (l_subAccount == null)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                STR_METHOD_NAME);
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
                STR_METHOD_NAME);
        }

        try
        {
            //余力計算条件オブジェクトを生成
            WEB3TPCalcCondition l_calcCondition =
                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            //現注文内容の配列
            WEB3TPNewOrderSpec[] l_newOrderSpecs = null;

            //引数.注文内容≠nullの場合
            if (l_orderSpec != null)
            {
                //現注文内容オブジェクトを生成
                WEB3TPNewOrderSpec l_newOrderSpec =
                    WEB3TPNewOrderSpec.create(l_subAccount, l_orderSpecIntercepter, l_orderSpec);
                //現注文内容の配列を作成する
                l_newOrderSpecs = new WEB3TPNewOrderSpec[] { l_newOrderSpec };
            }

            //返済後余力更新オブジェクトを生成
            WEB3TPTradingPowerUpdAfterRepay l_tpUpdAfterRepay =
                new WEB3TPTradingPowerUpdAfterRepay(
                    l_lngAccountId,
                    l_blnMargin,
                    l_calcCondition,
                    l_newOrderSpecs);

            /*
             * 余力計算結果Paramsオブジェクトを取得
             */
            List l_updResults =
                l_tpUpdAfterRepay.calcTradingpowerUpdResultMargin(WEB3TPMarkToMarketDivDef.NORMAL);

            //発注分返済決済損益
            double[] l_orderProfitLoss = new double[6];
            double[] l_currProfitLoss = new double[6];

            //指定日(T+0～T+5)の範囲でLOOP処理
            for (int index = WEB3TPSpecifiedPointDef.T_0;
                index <= WEB3TPSpecifiedPointDef.T_5;
                index++)
            {
                //指定日の発注分返済決済損益を取得
                l_orderProfitLoss[index] = l_tpUpdAfterRepay.getOrderRepaySettleProfitLoss(index);
                //指定日の今回返済分決済損益を取得
                l_currProfitLoss[index] =
                    l_tpUpdAfterRepay.getCurrOrderRepaySettleProfitLoss(index);
            }

            //返済後資産余力情報<信用顧客>オブジェクトを生成する。
            WEB3TPTradingPowerCalcMarginAfterRepay l_tpCalcMarginAfterRepay =
                new WEB3TPTradingPowerCalcMarginAfterRepay(
                    l_updResults,
                    l_calcCondition,
                    l_orderProfitLoss,
                    l_currProfitLoss);

            //生成した返済後資産余力情報<信用顧客>オブジェクトを返却する
            log.exiting(STR_METHOD_NAME);
            return l_tpCalcMarginAfterRepay;
        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            //エラーをスロー
            log.error(baseRunEx.getErrorMessage());
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            //エラーをスロー
            log.error(e.getMessage());
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (create返済時注意文言)<BR>
     * <BR>
     * 返済時注意文言オブジェクトを生成する。<BR>
     * <BR>
     * シーケンス図「(返済後余力サービスImpl)create返済時注意文言」参照<BR>
     * <BR>
     * @@param l_subAccount
     * @@param l_orderSpecIntercepter
     * @@param l_orderSpec
     * @@return webbroker3.tradingpower.WEB3TPAttentionObjection
     * @@roseuid 41E3842801ED
     */
    public WEB3TPAttentionObjection createWEB3TPAttentionObjection(
        WEB3GentradeSubAccount l_subAccount,
        Object l_orderSpecIntercepter,
        Object l_orderSpec)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "WEB3TPTradingPowerAfterRepayServiceImpl.createWEB3TPAttentionObjection(WEB3GentradeSubAccount, Object, Object)";
        log.entering(STR_METHOD_NAME);

        //引数.補助口座がnullの場合
        if (l_subAccount == null)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                STR_METHOD_NAME);
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
                STR_METHOD_NAME);
        }

        try
        {
            //余力計算条件オブジェクトを生成
            WEB3TPCalcCondition l_calcCondition =
                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            //余力計算結果(List)を取得
            List l_resultMargin =
                WEB3TPTradingPowerCalcMargin.findCalcResultMarginParams(l_lngAccountId);

            //返済前の資産余力情報<信用顧客>オブジェクトを生成
            WEB3TPTradingPowerCalcMargin l_tpCalcMargin =
                new WEB3TPTradingPowerCalcMargin(l_resultMargin, l_calcCondition);

            //返済前入金請求フラグ
            boolean l_blnDemandBeforeRepay = false;
            //指定日(T+0～T+5)の範囲でLOOP処理
            for (int index = WEB3TPSpecifiedPointDef.T_0;
                index <= WEB3TPSpecifiedPointDef.T_5;
                index++)
            {
                //入金請求が発生している(入金請求額(n) > 0) の場合
                if (l_tpCalcMargin.calcDemandamount(index) > 0)
                {
                    l_blnDemandBeforeRepay = true;
                }
            }

            //現注文内容の配列
            WEB3TPNewOrderSpec[] l_newOrderSpecs = null;

            //引数.注文内容≠nullの場合
            if (l_orderSpec != null)
            {
                //現注文内容オブジェクトを生成
                WEB3TPNewOrderSpec l_newOrderSpec =
                    WEB3TPNewOrderSpec.create(l_subAccount, l_orderSpecIntercepter, l_orderSpec);
                //現注文内容の配列を作成する
                l_newOrderSpecs = new WEB3TPNewOrderSpec[] { l_newOrderSpec };
            }

            //返済後余力更新オブジェクトを生成
            WEB3TPTradingPowerUpdAfterRepay l_tpUpdAfterRepay =
                new WEB3TPTradingPowerUpdAfterRepay(
                    l_lngAccountId,
                    l_blnMargin,
                    l_calcCondition,
                    l_newOrderSpecs);

            /*
             * 余力計算結果Paramsオブジェクトを取得
             */
            List l_updResults =
                l_tpUpdAfterRepay.calcTradingpowerUpdResultMargin(WEB3TPMarkToMarketDivDef.NORMAL);

            //返済後の資産余力情報<信用顧客>オブジェクトを生成
            l_tpCalcMargin = new WEB3TPTradingPowerCalcMargin(l_updResults, l_calcCondition);

            //返済後入金請求フラグ
            boolean l_blnDemandAfterRepay = false;
            //(直近の)返済後入金請求額
            double l_dblDemandamount = 0;
            //指定日(T+0～T+5)の範囲でLOOP処理
            for (int index = WEB3TPSpecifiedPointDef.T_5;
                index >= WEB3TPSpecifiedPointDef.T_0;
                index--)
            {
                //入金請求(n)
                double l_dblBufDemandamount = l_tpCalcMargin.calcDemandamount(index);

                //入金請求が発生している(入金請求額(n) > 0) の場合
                if (l_dblBufDemandamount > 0)
                {
                    l_blnDemandAfterRepay = true;
                    l_dblDemandamount = l_dblBufDemandamount;
                }
            }

            //返済時注意文言オブジェクトを生成
            WEB3TPAttentionObjection l_attentionObjection = new WEB3TPAttentionObjection();

            //[返済前入金請求：○ or ×、返済後入金請求：×]
            if (l_blnDemandAfterRepay == false)
            {
                //注意文言表示区分   ＝ 注意文言表示区分Def.注意文言表示無し
                l_attentionObjection.attentionObjectionType =
                    WEB3TPAttentionObjectionTypeDef.NO_ATTENTION;
                //入金請求額      ＝ 0
                l_attentionObjection.demandAmount = 0;
            }
            //[返済前入金請求：○、返済後入金請求：○]
            else if (l_blnDemandBeforeRepay == true && l_blnDemandAfterRepay == true)
            {
                //注意文言表示区分   ＝ 注意文言表示区分Def.注意文言表示有り(返済前入金請求有り)
                l_attentionObjection.attentionObjectionType =
                    WEB3TPAttentionObjectionTypeDef.ATTENTION_BEFORE_REPAY;
                //入金請求額      ＝ (直近の)返済後入金請求額
                l_attentionObjection.demandAmount = l_dblDemandamount;
            }
            //[返済前入金請求：×、返済後入金請求：○]
            else if (l_blnDemandBeforeRepay == false && l_blnDemandAfterRepay == true)
            {
                //注意文言表示区分   ＝ 注意文言表示区分Def.注意文言表示有り(返済前入金請求無し)
                l_attentionObjection.attentionObjectionType =
                    WEB3TPAttentionObjectionTypeDef.ATTENTION_AFTER_REPAY;
                //入金請求額      ＝ (直近の)返済後入金請求額
                l_attentionObjection.demandAmount = l_dblDemandamount;
            }

            //生成した返済時注意文言オブジェクトを返却する。
            log.exiting(STR_METHOD_NAME);
            return l_attentionObjection;
        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            //エラーをスロー
            log.error(baseRunEx.getErrorMessage());
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            //エラーをスロー
            log.error(e.getMessage());
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }
}
@
