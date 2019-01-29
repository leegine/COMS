head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.34.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPCashValuationAfterRepay.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 返済後総資金(WEB3TPCashValuationAfterRepay.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/11 nakazato(ACT) 新規作成
*/

package webbroker3.tradingpower.updtpower.cash;

import java.util.Date;

import webbroker3.tradingpower.updtpower.WEB3TPAccountInfo;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.updtpower.WEB3TPNewOrderSpec;
import webbroker3.util.WEB3LogUtility;

/**
 * (返済後総資金)
 */
public class WEB3TPCashValuationAfterRepay extends WEB3TPCashValuation
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPCashValuationAfterRepay.class);

    /**
     * @@roseuid 41E383D50326
     */
    public WEB3TPCashValuationAfterRepay()
    {
    }

    /**
     * (staticメソッド)(create返済後総資金)<BR>
     * <BR>
     * 返済後総資金インスタンスを生成し返却する。<BR>
     * <BR>
     * １）　@引数を変数にセットする。<BR>
     * ・顧客属性＝引数.顧客属性<BR>
     * ・計算条件＝引数.計算条件<BR>
     * ・現注文内容=引数.現注文内容<BR>
     * ２）　@預り金生成し変数にセットする。
     * <BR>
     * ３）　@返済後取引代金生成し変数にセットする。
     * <BR>
     * ４）　@拘束金生成し変数にセットする。
     * <BR>
     * @@param l_accountInfo - (顧客属性)
     * @@param l_calcCondition - (余力計算条件)
     * @@param l_newOrderSpecs - (現注文内容)
     * @@return webbroker3.tradingpower.updtpower.cash.WEB3TPCashValuationAfterRepay
     * @@roseuid 41C91CB0003E
     */
    public static WEB3TPCashValuationAfterRepay createWEB3TPCashValuationAfterRepay(
        WEB3TPAccountInfo l_accountInfo,
        WEB3TPCalcCondition l_calcCondition,
        WEB3TPNewOrderSpec[] l_newOrderSpecs)
    {
        final String STR_METHOD_NAME =
            "createWEB3TPCashValuationAfterRepay(WEB3TPAccountInfo, WEB3TPCalcCondition, WEB3TPNewOrderSpec[])";
        log.entering(STR_METHOD_NAME);

        WEB3TPCashValuationAfterRepay l_instance = new WEB3TPCashValuationAfterRepay();
        l_instance.setAccountInfo(l_accountInfo);
        l_instance.setCalcCondition(l_calcCondition);
        l_instance.setNewOrderSpecs(l_newOrderSpecs);

        l_instance.setTransactionAmount(
            WEB3TPTransactionAmountAfterRepay.createWEB3TPTransactionAmountAfterRepay(l_instance));
        l_instance.setCashBalance(WEB3TPCashBalance.create(l_instance));
        l_instance.setRestraint(WEB3TPRestraint.create(l_instance));

        log.exiting(STR_METHOD_NAME);
        return l_instance;
    }

    /**
     * (do返済後総資金ロード)<BR>
     * <BR>
     * 総資金ロード()をコール<BR>
     * <BR>
     * シーケンス図「(返済後総資金)do返済後総資金ロード」参照<BR>
     * <BR>
     * @@roseuid 41C91C7302FD
     */
    public void loadAllAfterRepay()
    {
        final String STR_METHOD_NAME = "loadAllAfterRepay()";
        log.entering(STR_METHOD_NAME);

        this.loadAll();

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (calc発注分返済決済損益)<BR>
     * <BR>
     * 今回返済分　@＋　@未約定返済注文分
     * <BR>
     * シーケンス図「(返済後余力更新)get発注分返済決済損益」参照<BR>
     * <BR>
     * @@param l_datDate - (指定日)
     * @@return double
     * @@roseuid 41C91ED80167
     */
    public double calcOrderRepaySettleProfitLoss(Date l_datDate)
    {
        final String STR_METHOD_NAME = "calcOrderRepaySettleProfitLoss(Date)";
        log.entering(STR_METHOD_NAME);

        //返済後取引代金オブジェクトを取得
        WEB3TPTransactionAmountAfterRepay l_transactionAmountAfterRepay =
            (WEB3TPTransactionAmountAfterRepay)this.getTransactionAmount();

        //発注分返済決済損益(n)を取得
        double l_dblProfitLoss =
            l_transactionAmountAfterRepay.calcOrderRepaySettleProfitLoss(l_datDate);

        //発注分返済決済損益(n)を返却
        log.exiting(STR_METHOD_NAME);
        return l_dblProfitLoss;
    }

    /**
     * (calc今回返済分返済決済損益)<BR>
     * <BR>
     * 今回返済分のみの決済損益<BR>
     * <BR>
     * シーケンス図「(返済後余力更新)get今回返済分決済損益」参照<BR>
     * <BR>
     * @@param l_datDate - (指定日)
     * @@return Double
     * @@roseuid 41C91F360177
     */
    public double calcCurrOrderRepaySettleProfitLoss(Date l_datDate)
    {
        final String STR_METHOD_NAME = "calcCurrOrderRepaySettleProfitLoss(Date)";
        log.entering(STR_METHOD_NAME);

        //返済後取引代金オブジェクトを取得
        WEB3TPTransactionAmountAfterRepay l_transactionAmountAfterRepay =
            (WEB3TPTransactionAmountAfterRepay)this.getTransactionAmount();

        //今回返済分返済決済損益(n)を取得
        double l_dblProfitLoss =
            l_transactionAmountAfterRepay.calcCurrOrderRepaySettleProfitLoss(l_datDate);

        //今回返済分返済決済損益(n)を返却
        log.exiting(STR_METHOD_NAME);
        return l_dblProfitLoss;
    }
}
@
