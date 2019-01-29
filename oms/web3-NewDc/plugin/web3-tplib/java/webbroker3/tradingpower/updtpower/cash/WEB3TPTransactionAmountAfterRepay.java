head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.35.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTransactionAmountAfterRepay.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 返済後取引代金(WEB3TPTransactionAmountAfterRepay.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/11 nakazato(ACT) 新規作成
                   2006/09/15 車進　@  (中訊)モデルNo.30
*/

package webbroker3.tradingpower.updtpower.cash;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;

import webbroker3.tradingpower.updtpower.WEB3TPNewOrderSpec;
import webbroker3.tradingpower.updtpower.WEB3TPPersistentDataManager;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (返済後取引代金)
 */
public class WEB3TPTransactionAmountAfterRepay extends WEB3TPTransactionAmount
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPTransactionAmountAfterRepay.class);

    /**
     * @@roseuid 41E383D600D4
     */
    public WEB3TPTransactionAmountAfterRepay()
    {
    }

    /**
     * (create返済後取引代金)<BR>
     * <BR>
     * 返済後取引代金を生成し、返却する。<BR>
     * <BR>
     * １）　@インスタンスを生成<BR>
     * ２）　@値を設定<BR>
     * 　@顧客情報＝引数.返済後総資金.get顧客情報()<BR>
     * 　@計算条件＝引数.返済後総資金.get計算条件()<BR>
     * 　@現注文内容＝引数.返済後総資金.get現注文内容()<BR> 
     * ３）　@インスタンスを返却<BR>
     *
     * @@param l_valuation (総資金)
     * @@return WEB3TPTransactionAmount
     */
    public static WEB3TPTransactionAmountAfterRepay createWEB3TPTransactionAmountAfterRepay(WEB3TPCashValuationAfterRepay l_valuation)
    {
        final String STR_METHOD_NAME =
            "createWEB3TPTransactionAmountAfterRepay(WEB3TPCashValuationAfterRepay)";
        log.entering(STR_METHOD_NAME);

        WEB3TPTransactionAmountAfterRepay l_instance = new WEB3TPTransactionAmountAfterRepay();
        l_instance.setAccountInfo(l_valuation.getAccountInfo());
        l_instance.setCalcCondition(l_valuation.getCalcCondition());
        l_instance.setNewOrderSpecs(l_valuation.getNewOrderSpecs());

        log.exiting(STR_METHOD_NAME);
        return l_instance;
    }

    /**
     * (calc発注分返済決済損益)<BR>
     * <BR>
     * 今回返済分　@＋　@未約定返済注文分<BR>
     * <BR>
     * シーケンス図「(返済後余力更新)get発注分返済決済損益」参照<BR>
     * <BR>
     * @@param l_datDate - (指定日)
     * @@return double
     * @@roseuid 41CB7E3D02E9
     */
    public double calcOrderRepaySettleProfitLoss(Date l_datDate)
    {
        final String STR_METHOD_NAME = "calcOrderRepaySettleProfitLoss(Date)";
        log.entering(STR_METHOD_NAME);

        //発注分返済決済損益(n)
        double l_dblOrderRepaySettleProfitLoss = 0;

        //取引情報一覧<当日>を取得
        List l_trans = this.getTodaysTransactions();

        //取引情報一覧<当日>の要素数回LOOP
        for (Iterator l_iter = l_trans.iterator(); l_iter.hasNext();)
        {
            //リストより、オブジェクトを取得
            Object l_element = (Object)l_iter.next();

            //返済後取引情報オブジェクトの場合
            if (l_element instanceof WEB3TPTransactionReflectorAfterRepay)
            {
                WEB3TPTransactionReflectorAfterRepay l_tpReflectorAfterRepay =
                    (WEB3TPTransactionReflectorAfterRepay)l_element;

                //引数.指定日が、変動反映日だった場合
                //(is変動期間内() = true)
                if (l_tpReflectorAfterRepay.isDuringReflectTime(l_datDate) == true)
                {
                    //未約定返済決済損益を取得
                    double l_dblUnexecuted =
                        l_tpReflectorAfterRepay.getUnexecutedRepaySettleProfitLoss();

                    //今回発注分返済決済損益を取得
                    double l_dblCurrOrder =
                        l_tpReflectorAfterRepay.getCurrOrderRepaySettleProfitLoss();

                    /*
                     * 発注分返済決済損益(n) = SUM(未約定返済決済損益 + 今回発注分返済決済損益)
                     */
                    l_dblOrderRepaySettleProfitLoss =
                        l_dblOrderRepaySettleProfitLoss + l_dblUnexecuted + l_dblCurrOrder;
                }
            }
        }

        //計算した発注分返済決済損益(n)を返却
        log.exiting(STR_METHOD_NAME);
        return l_dblOrderRepaySettleProfitLoss;
    }

    /**
     * (calc今回返済分返済決済損益)
     * 
     * 今回返済分のみの決済損益
     * @@param l_datDate - (指定日)
     * @@return double
     * @@roseuid 41CB7E3D0308
     */
    public double calcCurrOrderRepaySettleProfitLoss(Date l_datDate)
    {
        final String STR_METHOD_NAME = "calcCurrOrderRepaySettleProfitLoss(Date)";
        log.entering(STR_METHOD_NAME);

        //今回返済分返済決済損益(n)
        double l_dblCurrOrderRepaySettleProfitLoss = 0;

        //取引情報一覧<当日>を取得
        List l_trans = this.getTodaysTransactions();

        //取引情報一覧<当日>の要素数回LOOP
        for (Iterator l_iter = l_trans.iterator(); l_iter.hasNext();)
        {
            //リストより、オブジェクトを取得
            Object l_element = (Object)l_iter.next();

            //返済後取引情報オブジェクトの場合
            if (l_element instanceof WEB3TPTransactionReflectorAfterRepay)
            {
                WEB3TPTransactionReflectorAfterRepay l_tpReflectorAfterRepay =
                    (WEB3TPTransactionReflectorAfterRepay)l_element;

                //引数.指定日が、変動反映日だった場合
                //(is変動期間内() = true)
                if (l_tpReflectorAfterRepay.isDuringReflectTime(l_datDate) == true)
                {
                    //今回発注分返済決済損益を取得
                    double l_dblCurrOrder =
                        l_tpReflectorAfterRepay.getCurrOrderRepaySettleProfitLoss();

                    /*
                     * 今回返済分返済決済損益(n) = SUM(今回発注分返済決済損益)
                     */
                    l_dblCurrOrderRepaySettleProfitLoss =
                        l_dblCurrOrderRepaySettleProfitLoss + l_dblCurrOrder;
                }
            }
        }

        //計算した今回返済分返済決済損益(n)を返却
        log.exiting(STR_METHOD_NAME);
        return l_dblCurrOrderRepaySettleProfitLoss;
    }

    /**
     * (オーバーライド)(do信用返済取引情報ロード<当日>)<BR>
     * <BR>
     * 信用返済取引情報<当日>をロードする。<BR>
     * <BR>
     * シーケンス図「(返済後取引代金)do信用返済取引情報ロード<当日>」参照<BR>
     * <BR>
     * @@roseuid 41E37ECE00D4
     */
    protected void loadTodaysCloseMarginTransactions()
    {
        final String STR_METHOD_NAME = "loadTodaysCloseMarginTransactions()";
        log.entering(STR_METHOD_NAME);

        List l_rows =
            WEB3TPPersistentDataManager.getInstance().getTodaysCloseMarginOrdersAfterRepay(this);
        loadTodaysEqtypeTransactions(l_rows);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (オーバーライド)(do株式取引情報ロード<当日>)<BR>
     * <BR>
     * 株式注文の取引情報<当日>をロードする。<BR>
     * <BR>
     * シーケンス図「(返済後取引代金)do信用返済取引情報ロード<当日>」参照<BR>
     * <BR>
     * @@param l_eqtypeOrderUnitRows (株式注文単位のリスト)
     */
    protected void loadTodaysEqtypeTransactions(List l_rows)
    {
        final String STR_METHOD_NAME = "loadTodaysEqtypeTransactions(List)";
        log.entering(STR_METHOD_NAME);

        if (l_rows != null)
        {
            for (Iterator l_iter = l_rows.iterator(); l_iter.hasNext();)
            {
                //株式注文単位ROWを取得
                EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_iter.next();
                //注文カテゴリを取得
                OrderTypeEnum l_orderTypeEnum = l_orderUnitRow.getOrderType();

                //取引情報
                WEB3TPTransactionReflector[] l_tranRefs;

                //注文タイプ ＝ 信用買返済　@または　@信用売返済の場合
                if (OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_orderTypeEnum)
                    || OrderTypeEnum.CLOSE_MARGIN_SHORT.equals(l_orderTypeEnum))
                {
                    l_tranRefs = createWEB3TPTransactionReflectorAfterRepay(l_orderUnitRow);
                }
                //それ以外
                else
                {
                    l_tranRefs = createWEB3TPTransactionReflector(l_orderUnitRow);
                }

                for (int i = 0; i < l_tranRefs.length; i++)
                {
                    addTodaysTransaction(l_tranRefs[i]);
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (オーバーライド)(add取引情報<当日>)<BR>
     * <BR>
     * 引数を取引情報一覧<当日>に追加する。<BR>
     * <BR>
     * シーケンス図「(返済後取引代金)do信用返済取引情報ロード<当日>」参照<BR>
     * <BR>
     * @@param transaction - (取引情報)
     */
    protected void addTodaysTransaction(WEB3TPTransactionReflector l_transaction)
    {
        final String STR_METHOD_NAME = "addTodaysTransaction(WEB3TPTransactionReflector)";
        log.entering(STR_METHOD_NAME);

        if (l_transaction instanceof WEB3TPTransactionReflector
            || l_transaction instanceof WEB3TPTransactionReflectorAfterRepay)
        {
            this.getTodaysTransactions().add(l_transaction);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create返済後取引情報)<BR>
     * <BR>
     * 引数.株式注文より<BR> 
     * 返済後取引情報を作成し返却する。<BR> 
     * <BR>
     * シーケンス図「(返済後取引代金)create返済後取引情報」参照<BR>
     * <BR>
     * @@param l_row - (株式注文)
     * @@return webbroker3.tradingpower.updtpower.cash.WEB3TPTransactionReflectorAfterRepay[]
     */
    protected WEB3TPTransactionReflectorAfterRepay[] createWEB3TPTransactionReflectorAfterRepay(EqtypeOrderUnitRow l_row)
    {
        final String STR_METHOD_NAME =
            "createWEB3TPTransactionReflectorAfterRepay(EqtypeOrderUnitRow)";
        log.entering(STR_METHOD_NAME);

        //未約定数量
        double l_dblUnexecutedQuantity = 0;
        //約定済数量
        double l_dblExecutedQuantity = 0;
        //未約定代金
        double l_dblUnexecutedAmount = 0;
        //約定済代金
        double l_dblExecutedAmount = 0;

        //今回返済分返済決済損益
        double l_dblCurrOrderRepaySettleProfitLoss = 0;
        //未約定分返済決済損益
        double l_dblUnexecutedRepaySettleProfitLoss = 0;

        //発注日を取得
        Date l_tranDate = WEB3DateUtility.getDate(l_row.getBizDate(), "yyyyMMdd");
        //受渡日を取得
        Date l_deliveryDate = l_row.getDeliveryDate();
        //トランザクションタイプを取得
        FinTransactionType l_tranType = l_row.getOrderType().toFinTransactionType();
        //トランザクションタイプ別の損益方向を取得
        int l_intCashDir = WEB3TPTransactionReflector.getCashDir(l_tranType);

        //全約定の場合
        if (OrderOpenStatusEnum.CLOSED.equals(l_row.getOrderOpenStatus()))
        {
            //未約定数量 = 0
            l_dblUnexecutedQuantity = 0;
            //未約定代金 = 0
            l_dblUnexecutedAmount = 0;

            //約定済数量 = 注文単位テーブル.約定数量
            l_dblExecutedQuantity = l_row.getExecutedQuantity();
            //約定済代金 = SUM(株式トランザクションテーブル.受渡代金)
            l_dblExecutedAmount = getNetAmountTotal(l_row);
        }
        //未約定あるいは一部約定の場合
        else
        {
            //概算受渡代金を取得
            double l_dblEstimatedPrice = l_row.getEstimatedPrice();

            /*
             * 信用返済の場合[株式注文テーブル.注文種別＝信用買返済、信用売返済の場合]
             * 　@・未約定数量 ＝ 0　@
             * 　@・未約定代金 ＝ 0
             * 　@・約定済数量 ＝ 株式注文テーブル.注文数量
             * 　@・約定済代金 ＝ 株式注文テーブル.概算受渡金額
             * 
             * 　@（※）今回返済注文の場合(株式注文テーブル.注文単位ID == -1)
             * 　@　@・今回返済分返済決済損益 ＝ 株式注文テーブル.概算受渡金額
             * 　@　@・未約定分返済決済損益 ＝ 0
             * 　@（※）既存未約定返済注文の場合(株式注文テーブル.注文単位ID ≠ -1)
             * 　@　@・今回返済分返済決済損益 ＝ 0
             * 　@　@・未約定分返済決済損益 ＝ 株式注文テーブル.概算受渡金額 － 受渡代金
             * 
             */
            //未約定数量 = 0
            l_dblUnexecutedQuantity = 0;
            //未約定代金 = 0
            l_dblUnexecutedAmount = 0;
            //約定済数量 = 注文単位テーブル.注文数量
            l_dblExecutedQuantity =
                l_row.getConfirmedQuantityIsNull()
                    ? l_row.getQuantity()
                    : l_row.getConfirmedQuantity();
            //約定済代金 = 概算受渡代金
            l_dblExecutedAmount = l_dblEstimatedPrice * l_intCashDir;

            //今回返済注文の場合(株式注文テーブル.注文単位ID == -1)
            if (l_row.getOrderUnitId() == WEB3TPNewOrderSpec.DEFAULT_NEW_ID)
            {
                //今回返済分返済決済損益 ＝ 株式注文テーブル.概算受渡金額
                l_dblCurrOrderRepaySettleProfitLoss = l_dblEstimatedPrice * l_intCashDir;
                //未約定分返済決済損益 ＝ 0
                l_dblUnexecutedRepaySettleProfitLoss = 0;
            }
            //既存未約定返済注文の場合(株式注文テーブル.注文単位ID ≠ -1)
            else
            {
                //今回返済分返済決済損益 ＝ 0
                l_dblCurrOrderRepaySettleProfitLoss = 0;
                //未約定分返済決済損益 ＝ 株式注文テーブル.概算受渡金額 － 受渡代金
                l_dblUnexecutedRepaySettleProfitLoss =
                    (l_row.getExecutedQuantity() == 0)
                        ? l_dblEstimatedPrice * l_intCashDir
                        : (l_dblEstimatedPrice - getNetAmountTotal(l_row)) * l_intCashDir;
            }
        }

        /*
         * 返済後取引情報オブジェクトを生成し返却する。
         */
        log.exiting(STR_METHOD_NAME);
        return new WEB3TPTransactionReflectorAfterRepay[] {
             WEB3TPTransactionReflectorAfterRepay.createWEB3TPTransactionReflectorAftreRepay(
                getCalcCondition(),
                l_row.getProductType(),
                l_row.getProductId(),
                l_tranType,
                l_tranDate,
                l_dblUnexecutedQuantity,
                l_dblUnexecutedAmount,
                l_dblExecutedQuantity,
                l_dblExecutedAmount,
                l_deliveryDate,
                l_row.getTaxType(),
                l_dblUnexecutedRepaySettleProfitLoss,
                l_dblCurrOrderRepaySettleProfitLoss)};
    }
}
@
