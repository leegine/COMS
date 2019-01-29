head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.53.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerUpdAfterRepay.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 返済後余力更新(WEB3TPTradingPowerUpdAfterRepay.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/11 nakazato(ACT) 新規作成
*/

package webbroker3.tradingpower;

import java.util.Date;
import java.util.List;

import webbroker3.tradingpower.updtpower.WEB3TPAccountInfo;
import webbroker3.tradingpower.updtpower.WEB3TPNewOrderSpec;
import webbroker3.tradingpower.updtpower.WEB3TPPersistentDataManager;
import webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityValuation;
import webbroker3.tradingpower.updtpower.cash.WEB3TPCashValuationAfterRepay;
import webbroker3.tradingpower.updtpower.contract.WEB3TPContractInfoAfterRepay;
import webbroker3.tradingpower.updtpower.settlement.WEB3TPSettlement;
import webbroker3.util.WEB3LogUtility;

/**
 * (返済後余力更新)
 */
public class WEB3TPTradingPowerUpdAfterRepay extends WEB3TPTradingPowerUpd
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPTradingPowerUpdAfterRepay.class);

    /**
     * @@roseuid 41E3842B00D4
     */
    public WEB3TPTradingPowerUpdAfterRepay()
    {

    }

    /**
     * (コンストラクタ)<BR>
     * <BR>
     * シーケンス図「(返済後余力更新)コンストラクタ」参照<BR>
     * <BR>
     * @@param l_lngAccountId - (口座ID)
     * @@param l_blnMarginFlag - (信用顧客フラグ)
     * @@param l_calcCondition - (余力計算条件)
     * @@param l_newOrderSpecs - (現注文内容)
     * @@roseuid 41BFE12803B9
     */
    public WEB3TPTradingPowerUpdAfterRepay(
        long l_lngAccountId,
        boolean l_blnMarginFlag,
        WEB3TPCalcCondition l_calcCondition,
        WEB3TPNewOrderSpec[] l_newOrderSpecs)
    {
        final String STR_METHOD_NAME =
            "WEB3TPTradingPowerUpdAfterRepay(long, boolean, WEB3TPCalcCondition, WEB3TPNewOrderSpec[])";
        log.entering(STR_METHOD_NAME);

        /*
         * 顧客属性オブジェクト生成       
         */
        WEB3TPAccountInfo l_accountInfo = WEB3TPAccountInfo.create(l_lngAccountId, l_blnMarginFlag);

        this.accountInfo = l_accountInfo;
        this.calcCondition = l_calcCondition;
        this.newOrderSpecs = l_newOrderSpecs;

        List l_todaysEquityOrders = WEB3TPPersistentDataManager.getInstance().getTodaysEquityOrders(
            l_accountInfo, l_calcCondition);
        /*
         * 返済後建玉情報オブジェクト作成
         */
        WEB3TPContractInfoAfterRepay l_contractInfo =
            WEB3TPContractInfoAfterRepay.createWEB3TPContractInfoAfterRepay(
                this.accountInfo,
                this.calcCondition,
                this.newOrderSpecs);
        l_contractInfo.setTodaysEquityOrders(l_todaysEquityOrders);

        //返済後建玉情報ロード
        l_contractInfo.loadContractInfoAfterRepay();
        this.contractInfo = l_contractInfo;

        /*
         * 証券評価オブジェクト作成
         */
        WEB3TPSecurityValuation l_securityValuation =
            WEB3TPSecurityValuation.create(
                this.accountInfo,
                this.calcCondition,
                this.newOrderSpecs,
                l_contractInfo);
        l_securityValuation.setTodaysEquityOrders(l_todaysEquityOrders);

        //銘柄ごと証券評価額ロード
        l_securityValuation.doSecurityValuationPerProductLoad();
        this.securityValuation = l_securityValuation;

        /*
         * 返済後総資金オブジェクト作成
         */
        WEB3TPCashValuationAfterRepay l_cashValuation =
            WEB3TPCashValuationAfterRepay.createWEB3TPCashValuationAfterRepay(
                this.accountInfo,
                this.calcCondition,
                this.newOrderSpecs);
        l_cashValuation.setTodaysEquityOrders(l_todaysEquityOrders);

        //返済後総資金ロード
        l_cashValuation.loadAllAfterRepay();
        this.cashValuation = l_cashValuation;

        /*
         * 差金決済オブジェクトを生成
         */
        this.settlement =
            new WEB3TPSettlement(this.cashValuation, this.securityValuation, this.calcCondition);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get発注分返済決済損益)<BR>
     * <BR>
     * シーケンス図「(返済後余力更新)get発注分返済決済損益」参照<BR>
     * <BR>
     * @@param l_intSpecifiedPoint - (指定日)
     * @@return double
     * @@roseuid 41C7AB8F02C8
     */
    public double getOrderRepaySettleProfitLoss(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "getOrderRepaySettleProfitLoss(int)";
        log.entering(STR_METHOD_NAME);

        //指定日に対応する営業日を取得
        Date l_datDate = this.calcCondition.getBizDate(l_intSpecifiedPoint);

        //this.総資金を返済後総資金にキャスト
        WEB3TPCashValuationAfterRepay l_cashValuationAfterRepay =
            (WEB3TPCashValuationAfterRepay)this.cashValuation;

        //指定日の発注分返済決済損益を返却
        log.exiting(STR_METHOD_NAME);
        return l_cashValuationAfterRepay.calcOrderRepaySettleProfitLoss(l_datDate);
    }

    /**
     * (get今回返済分返済決済損益)<BR>
     * <BR>
     * シーケンス図「(返済後余力更新)get今回返済分決済損益」参照<BR>
     * <BR>
     * @@param l_intSpecifiedPoint - (指定日)
     * @@return double
     * @@roseuid 41C7AB9D00D4
     */
    public double getCurrOrderRepaySettleProfitLoss(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "getCurrOrderRepaySettleProfitLoss(int)";
        log.entering(STR_METHOD_NAME);

        //指定日に対応する営業日を取得
        Date l_datDate = this.calcCondition.getBizDate(l_intSpecifiedPoint);

        //this.総資金を返済後総資金にキャスト
        WEB3TPCashValuationAfterRepay l_cashValuationAfterRepay =
            (WEB3TPCashValuationAfterRepay)this.cashValuation;

        //指定日の今回発注分返済決済損益を返却
        log.exiting(STR_METHOD_NAME);
        return l_cashValuationAfterRepay.calcCurrOrderRepaySettleProfitLoss(l_datDate);
    }
}
@
