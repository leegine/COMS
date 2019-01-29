head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.36.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPCashValuation.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3TPAccumulatedCapitalGainTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2004/08/02 堀野 和美(FLJ) 新規作成
                   2006/09/11 徐宏偉 (中訊) モデルNo.012
Revision History : 2009/12/15 張騰宇 モデルNo.414 417
Revision History : 2010/01/15 武波 モデルNo.442
*/

package webbroker3.tradingpower.updtpower.cash;

import java.math.BigDecimal;
import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.updtpower.WEB3TPAccountInfo;
import webbroker3.tradingpower.updtpower.WEB3TPAssetValuation;
import webbroker3.tradingpower.updtpower.WEB3TPNewOrderSpec;
import webbroker3.tradingpower.util.ToStringUtils;

/**
 * (総資金)<BR>
 * 総資金の推移を表現します。
 */
public class WEB3TPCashValuation
    extends WEB3TPAssetValuation
{
    /**
     * (預り金)
     */
    private WEB3TPCashBalance cashBalance;
    
    /**
     * (取引代金)
     */
    private WEB3TPTransactionAmount transactionAmount;
    
    /**
     * (拘束金)
     */
    private WEB3TPRestraint restraint;

    /**
     * @@roseuid 4104B4E600A6
     */
    public WEB3TPCashValuation()
    {
    }

    /**
     * (create総資金)<BR>
     * 総資金インスタンスを生成し返却する。<BR>
     * <BR>
     * １）　@引数を変数にセットする。<BR>
     * ・顧客属性＝引数.顧客属性<BR>
     * ・計算条件＝引数.計算条件<BR>
     * ・現注文内容=引数.現注文内容<BR>
     * ２）　@預り金生成し変数にセットする。
     * <BR>
     * ３）　@取引代金生成し変数にセットする。
     * <BR>
     * ４）　@拘束金生成し変数にセットする。
     * <BR>
     * @@param l_accountInfo - (顧客属性)
     * @@param l_calcCondition - (計算条件)
     * @@param l_newOrderSpecs - (現注文内容)
     * @@return WEB3TPCashValuation
     * @@roseuid 40F3CCE20030
     */
    public static WEB3TPCashValuation create(WEB3TPAccountInfo l_accountInfo,
                                             WEB3TPCalcCondition l_calcCondition,
                                             WEB3TPNewOrderSpec[] l_newOrderSpecs)
    {
        WEB3TPCashValuation l_instance = new WEB3TPCashValuation();
        l_instance.setAccountInfo(l_accountInfo);
        l_instance.setCalcCondition(l_calcCondition);
        l_instance.setNewOrderSpecs(l_newOrderSpecs);

        l_instance.setTransactionAmount(WEB3TPTransactionAmount.create(l_instance));
        l_instance.setCashBalance(WEB3TPCashBalance.create(l_instance));
        l_instance.setRestraint(WEB3TPRestraint.create(l_instance));
        return l_instance;
    }

    /**
     * (calc預り金残高)<BR>
     * 預り金.calc預り金残高()を呼ぶ。<BR>
     * @@param l_datDate - (指定日)
     * @@return double
     * @@roseuid 40B3E7F700CD
     */
    public double calcCashBalance(Date l_datDate)
    {
        return cashBalance.calcCashBalance(l_datDate);
    }

    /**
     * (calc当日未約定代金)<BR>
     * 取引代金.calc未約定代金()を呼ぶ。<BR>
     * @@param l_datDate - (指定日)
     * @@return double
     * @@roseuid 40B4035F036D
     */
    public double calcTodaysUnexecutedTotal(Date l_datDate)
    {
        return transactionAmount.calcTodaysUnexecutedTotal(l_datDate);
    }

    /**
     * (calc当日約定済代金)<BR>
     * 取引代金.calc約定済代金()を呼ぶ。<BR>
     * @@param l_datDate - (指定日)
     * @@return double
     * @@roseuid 40C81E3B0349
     */
    public double calcTodaysExecutedTotal(Date l_datDate)
    {
        return transactionAmount.calcTodaysExecutedTotal(l_datDate);
    }

    /**
     * (calcその他拘束金)<BR>
     * 拘束金.calcその他拘束金()を呼ぶ。<BR>
     * @@param l_datDate - (指定日)
     * @@return double
     * @@roseuid 40B403890050
     */
    public double calcOtherRestraintsTotal(Date l_datDate)
    {
        return restraint.calcOtherRestraints(l_datDate);
    }

    /**
     * (calc預り金担保出金拘束金)<BR> 
     * <BR>
     * 引数.指定日の預り金担保出金拘束金を返却する。<BR> 
     * <BR>
     * １）引数.指定日の預り金担保出金拘束金を取得する。<BR> 
     * <BR>
     * 　@[a.証券担保ローン実施顧客の場合] <BR>
     * 　@(this.get余力計算条件().is証券担保ローン区分 == true)<BR> 
     * 　@　@－"預り金担保出金拘束金" = this.拘束金.calc預り金担保出金拘束金(:Date = 引数.指定日)<BR> 
     * <BR>
     * 　@[a.以外(証券担保ローン未実施）の場合] <BR>
     * 　@　@－"預り金担保出金拘束金" = 0 <BR>
     * <BR>
     * ２）取得した預り金担保出金拘束金を返却する。 <BR>
     * <BR>
     * 　@返却値："預り金担保出金拘束金" <BR>
     * <BR>
     * @@param l_datDate - (指定日)
     * @@return double
     */
    public double caleCashDepositRestraint(Date l_datDate)
    {
        //[a.証券担保ローン実施顧客の場合]
        //(this.get余力計算条件().is証券担保ローン区分 == true)
        //－"預り金担保出金拘束金" = this.拘束金.calc預り金担保出金拘束金(:Date = 引数.指定日)
        boolean l_blnSecuredLoanSecAccOpenDiv = 
            this.getCalcCondition().isSecuredLoanSecAccOpenDiv();
        
        double l_dblCashDepositRestraint = 0;
        if (l_blnSecuredLoanSecAccOpenDiv)
        {
            l_dblCashDepositRestraint= 
                this.restraint.calcCashDepositRestraint(l_datDate);
        }
        //[a.以外(証券担保ローン未実施）の場合]
        //－"預り金担保出金拘束金" = 0 
        //取得した預り金担保出金拘束金を返却する。
        return l_dblCashDepositRestraint;
    }
    
    /**
     * (calc出金額)<BR>
     * 取引代金.calc出金額()を呼ぶ。<BR>
     * @@param l_datDate - (指定日)
     * @@return double
     */
    public double calcCashOut(Date l_datDate)
    {
        return transactionAmount.calcCashOut(l_datDate);
    }
    
    /**
     * (get預り金)<BR>
     * 預り金を返す。<BR>
     * @@return webbroker3.tradingpower.updtpower.cash.WEB3TPCashBalance
     * @@roseuid 4100E5740059
     */
    public WEB3TPCashBalance getCashBalance()
    {
        return cashBalance;
    }

    /**
     * (set預り金)<BR>
     * 引数を預り金にセットする。<BR>
     * @@param l_cashBalance - (預り金)
     * @@roseuid 4100E57900F5
     */
    public void setCashBalance(WEB3TPCashBalance l_cashBalance)
    {
        cashBalance = l_cashBalance;
    }

    /**
     * (get取引代金)<BR>
     * 取引代金を返す。<BR>
     * @@return webbroker3.tradingpower.updtpower.cash.WEB3TPTransactionAmount
     * @@roseuid 4100E58F0105
     */
    public WEB3TPTransactionAmount getTransactionAmount()
    {
        return transactionAmount;
    }

    /**
     * (set取引代金)<BR>
     * 引数を取引代金にセットする。<BR>
     * @@param l_transactionAmount - （取引代金）
     * @@roseuid 4100E59E0105
     */
    public void setTransactionAmount(WEB3TPTransactionAmount l_transactionAmount)
    {
        transactionAmount = l_transactionAmount;
    }

    /**
     * (get拘束金)<BR>
     * 拘束金を返す。<BR>
     * @@return webbroker3.tradingpower.updtpower.cash.WEB3TPRestraint
     * @@roseuid 4100E5A60124
     */
    public WEB3TPRestraint getRestraint()
    {
        return restraint;
    }

    /**
     * (set拘束金)<BR>
     * 引数を拘束金にセットする。<BR>
     * @@param l_restraint - （拘束金）
     * @@roseuid 4100E58F01FF
     */
    public void setRestraint(WEB3TPRestraint l_restraint)
    {
        restraint = l_restraint;
    }

    /**
     * (do総資金ロード)<BR>
     * 資金関連のデータをすべてロードする。<BR>
     * <BR>
     * 以下の順でロードメソッドを呼ぶ。<BR>
     * 取引代金.do取引情報ロード()<BR>
     * 預り金.do預り金ロード()<BR>
     * 拘束金.doその他拘束金ロード()<BR>
     * @@roseuid 40C7E97300E8
     */
    public void loadAll()
    {
        transactionAmount.setTodaysEquityOrders(this.getTodaysEquityOrders());
        transactionAmount.load();
        cashBalance.setTodaysEquityOrders(this.getTodaysEquityOrders());
        cashBalance.load();
        restraint.setTodaysEquityOrders(this.getTodaysEquityOrders());
        restraint.load();
    }

    /**
     * (calc出金拘束金)<BR>
     * ①@翌日出金額を取得する。　@ <BR>
     * 出金額 = ABS(this.取引代金.calc翌日出金額（）)<BR>
     * <BR>
     * ②実質預り金残高（T+1）(*)を計算する。 <BR>
     * (*)T+1朝時点の確定預り金(顧客勘定残高+保証金残高)に、当日取引代金を加味した値 <BR>
     * <BR>
     * [計算式] <BR>
     * 実質預り金残高(T+1) <BR>
     * = 引数．預り金残高(T+1) - 引数．当日約定済代金(T+1) - <BR>
     * 　@引数．当日未約定代金(T+1) - 引数．即日入金銘柄拘束金(T+1) <BR>
     * <BR>
     * <BR>
     * ③T+1の朝時点の確定信用保証金残高を取得する。 <BR>
     * <BR>
     * 確定信用保証金残高(T+1) =this.預り金.calc確定保証金(:Date = T+1) <BR>
     * <BR>
     * <BR>
     * ④顧客勘定残高から信用保証金残高への当日(T+0)から受渡日までの振替額の合計(以下、振替額)を取得する。 <BR>
     * <BR>
     * 振替額　@= this.取引代金. calc当日以降預り金保証金間の振替額() <BR>
     * <BR>
     * ⑤実質顧客勘定残高を計算する。 <BR>
     * ［計算式］ <BR>
     * 実質顧客勘定残高(T+1) = 実質預り金残高(T+1) + 振替額 - 確定信用保証金残高(T+1) <BR>
     * <BR>
     * ⑥＜実質顧客勘定残高(T+1)が0 以上の場合＞ <BR>
     * 出金拘束金 ＝ 0<BR>
     * ＜実質顧客勘定残高(T+1)が0 より小さい以下の場合＞ <BR>
     * 出金拘束金 ＝ MIN(①@で計算した出金額、ABS(実質顧客勘定残高(T+1)))<BR>
     * @@param l_dblCashBalance - (預り金残高(T+1))<BR>
     * @@param l_dblUnexecutedAmount - (未約定代金(T+1))<BR>
     * @@param l_dblExecutedAmount - (約定済代金(T+1))<BR>
     * @@param l_dblTodayDepFundRestraint - (即日入金対象銘柄拘束金(T+1))<BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    public double calcCashoutRestraint(
        double l_dblCashBalance, double l_dblUnexecutedAmount, double l_dblExecutedAmount, double l_dblTodayDepFundRestraint)
        throws WEB3BaseException
    {
        double l_dblCashoutRestraint = 0;

        //①@翌日出金額を取得する。
        //出金額 = ABS(this.取引代金.calc翌日出金額（）)
        BigDecimal l_bdCashoutAmount = new BigDecimal(Math.abs(transactionAmount.clacNextBizDateCashoutAmount()) + "");

        //②実質預り金残高（T+1）(*)を計算する。
        //(*)T+1朝時点の確定預り金(顧客勘定残高+保証金残高)に、当日取引代金を加味した値
        //[計算式]
        //実質預り金残高(T+1)
        //= 引数．預り金残高(T+1) - 引数．当日約定済代金(T+1) -
        //引数．当日未約定代金(T+1) - 引数．即日入金銘柄拘束金(T+1)
        BigDecimal l_bdCashBalance = new BigDecimal(l_dblCashBalance + "");
        BigDecimal l_bdUnexecutedAmount = new BigDecimal(l_dblUnexecutedAmount + "");
        BigDecimal l_bdExecutedAmount = new BigDecimal(l_dblExecutedAmount + "");
        BigDecimal l_bdOtherRestraint = new BigDecimal(l_dblTodayDepFundRestraint + "");
        BigDecimal l_bdActualAccountBalance =
            l_bdCashBalance.subtract(l_bdUnexecutedAmount).subtract(
                l_bdExecutedAmount).subtract(l_bdOtherRestraint);

        //③T+1の朝時点の確定信用保証金残高を取得する。
        //確定信用保証金残高(T+1) =this.預り金.calc確定保証金(:Date = T+1)
        Date l_datBizDate = this.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_1);
        BigDecimal l_bdMarginCashBalance = new BigDecimal(this.cashBalance.calcFixedDeposit(l_datBizDate) + "");

        //④顧客勘定残高から信用保証金残高への当日(T+0)から受渡日までの振替額の合計(以下、振替額)を取得する。
        //振替額　@= this.取引代金. calc当日以降預り金保証金間の振替額()
        BigDecimal l_bdTransferBalance = new BigDecimal(this.transactionAmount.calcTodayMarginDepositTransferAmount() + "");

        //⑤実質顧客勘定残高を計算する。
        //実質顧客勘定残高(T+1) = 実質預り金残高(T+1) + 振替額 - 確定信用保証金残高(T+1)
        BigDecimal l_bdActualCashBalance = l_bdActualAccountBalance.add(l_bdTransferBalance).subtract(l_bdMarginCashBalance);

        //⑥＜実質顧客勘定残高(T+1)が0 以上の場合＞
        //出金拘束金 ＝ 0
        //＜実質顧客勘定残高(T+1)が0 より小さい以下の場合＞
        //出金拘束金 ＝ MIN(①@で計算した出金額、ABS(実質顧客勘定残高(T+1)))
        if (l_bdActualCashBalance.doubleValue() >= 0)
        {
            l_dblCashoutRestraint = 0;
        }
        else
        {
            l_dblCashoutRestraint =
                Math.min(
                    l_bdCashoutAmount.doubleValue(),
                    Math.abs(l_bdActualCashBalance.doubleValue()));
        }
        //⑩「⑨で計算した出金拘束金」を返却する。
        return l_dblCashoutRestraint;
    }

    /**
     * このオブジェクトの文字列表現を返す。<BR>
     * 
     * @@return String
     */
    public String toString()
    {
        return ToStringUtils.newToStringBuilder(this)
            .append("cashBalance", getCashBalance())
            .append("transactionAmount", getTransactionAmount())
            .append("restraint", getRestraint())
            .toString();
            
    }            
            


}
@
