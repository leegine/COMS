head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.53.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerCalcMarginAfterRepay.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 返済後資産余力情報(WEB3TPTradingPowerCalcMarginAfterRepay.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/11 nakazato(ACT) 新規作成
*/

package webbroker3.tradingpower;

import java.util.List;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3LogUtility;

/**
 * (返済後資産余力情報)
 */
public class WEB3TPTradingPowerCalcMarginAfterRepay extends WEB3TPTradingPowerCalcMargin
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPTradingPowerCalcMarginAfterRepay.class);

    /**
     * (発注分返済決済損益)
     */
    protected double[] orderRepaySettleProfitLoss;

    /**
     * (今回発注分返済決済損益)
     */
    protected double[] currOrderRepaySettleProfitLoss;

    /**
     * @@roseuid 41E383F20299
     */
    public WEB3TPTradingPowerCalcMarginAfterRepay()
    {

    }

    /**
     * (コンストラクタ)<BR>
     * <BR>
     * 引数を各パラメータにセットする。<BR>
     * <BR>
     * @@param l_calcResult - （余力計算結果）
     * @@param l_calcCondition - (余力計算条件)
     * @@param l_orderRepaySettleProfitLoss - (発注分返済決済損益)
     * @@param l_currOrderRepaySettleProfitLoss - (今回発注分返済決済損益)
     * @@roseuid 41D0B26F019A
     */
    public WEB3TPTradingPowerCalcMarginAfterRepay(
        List l_calcResult,
        WEB3TPCalcCondition l_calcCondition,
        double[] l_orderRepaySettleProfitLoss,
        double[] l_currOrderRepaySettleProfitLoss)
    {
        super(l_calcResult, l_calcCondition);

        final String STR_METHOD_NAME =
            "WEB3TPTradingPowerCalcMarginAfterRepay(List, WEB3TPCalcCondition, double[], double[])";
        log.entering(STR_METHOD_NAME);
        
        this.orderRepaySettleProfitLoss = l_orderRepaySettleProfitLoss;
        this.currOrderRepaySettleProfitLoss = l_currOrderRepaySettleProfitLoss;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get発注分返済決済損益)<BR>
     * <BR>
     * 引数で指定された指定日(=n)の、「発注分返済決済損益」を返却する。<BR>
     * <BR>
     * １）　@引数チェックを行う。<BR>
     * nが0以上5以下でない時、0を返却する。<BR>
     * <BR>
     * ２）　@指定日の「発注分返済決済損益」を返却する。<BR>
     * <BR>
     * ［返却値］<BR>
     * this.発注分返済決済損益[T+n]<BR>
     * <BR>
     * @@param l_intSpecifiedPoint - (指定日)
     * @@return double
     * @@roseuid 41D0B1F201F8
     */
    public double getOrderRepaySettleProfitLoss(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "getOrderRepaySettleProfitLoss(int)";

        /*
         * 引数チェックを行う。
         */
        //パラメータ.指定日がT+0より小さい　@または　@T+5より大きい時
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //指定日の「発注分返済決済損益」を返却する
        log.exiting(STR_METHOD_NAME);
        return this.orderRepaySettleProfitLoss[l_intSpecifiedPoint];
    }

    /**
     * (get今回発注分返済決済損益)<BR>
     * <BR>
     * 引数で指定された指定日(=n)の、「今回発注分返済決済損益」を返却する。<BR>
     * <BR>
     * １）　@引数チェックを行う。<BR>
     * nが0以上5以下でない時、0を返却する。<BR>
     * <BR>
     * ２）　@指定日の「今回発注分返済決済損益」を返却する。<BR>
     * <BR>
     * ［返却値］<BR>
     * this.今回発注分返済決済損益[T+n]<BR>
     * <BR>
     * @@param l_intSpecifiedPoint - (指定日)
     * @@return double
     * @@roseuid 41D0B1FA03DC
     */
    public double getCurrOrderRepaySettleProfitLoss(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "getCurrOrderRepaySettleProfitLoss(int)";

        /*
         * 引数チェックを行う。
         */
        //パラメータ.指定日がT+0より小さい　@または　@T+5より大きい時
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //指定日の「今回発注分返済決済損益」を返却する
        log.exiting(STR_METHOD_NAME);
        return this.currOrderRepaySettleProfitLoss[l_intSpecifiedPoint];
    }

    /**
     * このオブジェクトの文字列表現を返す。<BR>
     * 
     * @@return String
     */
    public String toString()
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .append("calcResultMargin", this.getCalcResultMargin().toString())
            .append("calcResultDetailMargin", this.getCalcResultDetailMargin().toString())
            .append("orderRepaySettleProfitLoss[0]", this.getOrderRepaySettleProfitLoss(0))
            .append("orderRepaySettleProfitLoss[1]", this.getOrderRepaySettleProfitLoss(1))
            .append("orderRepaySettleProfitLoss[2]", this.getOrderRepaySettleProfitLoss(2))
            .append("orderRepaySettleProfitLoss[3]", this.getOrderRepaySettleProfitLoss(3))
            .append("orderRepaySettleProfitLoss[4]", this.getOrderRepaySettleProfitLoss(4))
            .append("orderRepaySettleProfitLoss[5]", this.getOrderRepaySettleProfitLoss(5))
            .append("currOrderRepaySettleProfitLoss[0]", this.getCurrOrderRepaySettleProfitLoss(0))
            .append("currOrderRepaySettleProfitLoss[1]", this.getCurrOrderRepaySettleProfitLoss(1))
            .append("currOrderRepaySettleProfitLoss[2]", this.getCurrOrderRepaySettleProfitLoss(2))
            .append("currOrderRepaySettleProfitLoss[3]", this.getCurrOrderRepaySettleProfitLoss(3))
            .append("currOrderRepaySettleProfitLoss[4]", this.getCurrOrderRepaySettleProfitLoss(4))
            .append("currOrderRepaySettleProfitLoss[5]", this.getCurrOrderRepaySettleProfitLoss(5))
            .toString();
    }
}
@
