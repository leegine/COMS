head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.04.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPContractInfoAfterRepay.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 返済後建玉情報(WEB3TPContractInfoAfterRepay.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/11 nakazato(ACT) 新規作成
Revesion History : 2008/02/01 崔遠鵬(中訊) モデルNo.260
*/

package webbroker3.tradingpower.updtpower.contract;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;

import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPEqtypeSwapMarginCostUndeliveredContractLossDivDef;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.updtpower.WEB3TPAccountInfo;
import webbroker3.tradingpower.updtpower.WEB3TPNewOrderSpec;
import webbroker3.tradingpower.updtpower.WEB3TPPersistentDataManager;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (返済後建玉情報)
 */
public class WEB3TPContractInfoAfterRepay extends WEB3TPContractInfo
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPContractInfoAfterRepay.class);

    /**
     * @@roseuid 41E383A20076
     */
    public WEB3TPContractInfoAfterRepay()
    {

    }

    /**
     * (staticメソッド)(create返済後建玉情報)<BR>
     * <BR>
     * 返済後建玉情報を生成する<BR>
     * <BR>
     * @@param l_accountInfo - (顧客属性)
     * @@param l_calcCondition - (余力計算条件)
     * @@param l_newOrderSpecs - (現注文内容)
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPContractInfoAfterRepay
     * @@roseuid 41C7E6380112
     */
    public static WEB3TPContractInfoAfterRepay createWEB3TPContractInfoAfterRepay(
        WEB3TPAccountInfo l_accountInfo,
        WEB3TPCalcCondition l_calcCondition,
        WEB3TPNewOrderSpec[] l_newOrderSpecs)
    {
        final String STR_METHOD_NAME =
            "createWEB3TPContractInfoAfterRepay(WEB3TPAccountInfo, WEB3TPCalcCondition, WEB3TPNewOrderSpec[])";
        log.entering(STR_METHOD_NAME);

        WEB3TPContractInfoAfterRepay l_instance = new WEB3TPContractInfoAfterRepay();
        l_instance.setAccountInfo(l_accountInfo);
        l_instance.setCalcCondition(l_calcCondition);
        l_instance.setNewOrderSpecs(l_newOrderSpecs);

        log.exiting(STR_METHOD_NAME);
        return l_instance;
    }

    /**
     * (do返済後建玉情報ロード)<BR>
     * <BR>
     * 返済後建玉情報をロードする
     * <BR>
     * シーケンス図「返済後建玉情報)do返済後建玉情報ロード」参照<BR>
     * <BR>
     * @@roseuid 41C7CDEE021C
     */
    public void loadContractInfoAfterRepay()
    {
        final String STR_METHOD_NAME = "loadContractInfoAfterRepay()";
        log.entering(STR_METHOD_NAME);

        //顧客属性を取得
        WEB3TPAccountInfo l_accountInfo = getAccountInfo();
        //信用顧客フラグを取得
        boolean l_isEquityMargin = l_accountInfo.isMarginCustomer();

        //信用顧客でない場合、ロード処理終了
        if (l_isEquityMargin == false)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //確定の建玉情報をロード
        loadFixedContracts();
        //当日の建玉情報をロード
        loadTodaysContracts();
        //建玉返済指定情報をロード
        loadClosingContractSpecs();
        //確定の変動情報をロード
        loadFixedHistories();
        //当日の変動情報をロード
        loadTodaysHistories();
        //未約定変動情報をロード
        loadUnexecutedOrderSpecs();
        //未約定返済変動情報をロード
        loadUnexecutedRepayOrderSpecs();

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (オーバーライド)(do建玉変動情報<当日>ロード)<BR>
     * <BR>
     * 建玉変動情報<当日>をロードする<BR>
     * <BR>
     * シーケンス図「(返済後建玉情報)do建玉変動情報<当日>ロード」参照<BR>
     * <BR>
     * @@roseuid 41C7CCA301AF
     */
    protected void loadTodaysHistories()
    {
        final String STR_METHOD_NAME = "loadTodaysHistories()";
        log.entering(STR_METHOD_NAME);

        //当日の建玉変動レコードセットを取得
        List l_rows = WEB3TPPersistentDataManager.getInstance().getTodaysHistories(this);

        //取得行のサイズでループ
        int l_intSize = l_rows.size();
        for (int i = 0; i < l_intSize; i++)
        {
            //レコードを取得
            EqtypeFinTransactionRow l_row = (EqtypeFinTransactionRow)l_rows.get(i);

            //対象建玉を取得
            WEB3TPTargetContract l_targetContract = getTargetContract(true, l_row.getContractId());

            //建玉ごと変動情報を取得
            WEB3TPHistoryPerContract l_historyPerContract = getHistoryPerContract(l_targetContract);

            //対象建玉詳細を取得
            WEB3TPTargetContractDetail l_targetContractDetail =  l_targetContract.getTargetContractDetail();
            
            //トランザクションテーブル.トランザクションカテゴリ = 信用返済の場合
            if (FinTransactionCateg.EQTYPE_CLOSE_MARGIN.equals(l_row.getFinTransactionCateg())
                == true)
            {
                //create返済後建玉変動
                WEB3TPHistoryAfterRepay l_history =
                    WEB3TPHistoryAfterRepay.createWEB3TPHistoryAfterRepay(
                        l_targetContract,
                        getCalcCondition());
                //注文単位ID
                l_history.setOrderUnitId(l_row.getOrderUnitId());
                //トランザクションカテゴリ
                l_history.setTransactionCateg(l_row.getFinTransactionCateg());
                //約定済フラグ
                l_history.setExecuted(true);
                //トランザクション発生日
                l_history.setTransactionDate(l_row.getFinTransactionTimestamp());
                //単価
                l_history.setPrice(l_targetContractDetail.getContractPrice());
                //株数
                l_history.setQuantity(l_row.getQuantity());
                //受渡日
                l_history.setDeliveryDate(l_row.getDeliveryDate());
                //calc変動反映日
                l_history.calcReflectDay(l_row.getDeliveryDate());
                //受渡代金
                l_history.setNetAmount(l_row.getNetAmount());

                //返済後建玉変動を建玉ごと変動情報に追加
                l_historyPerContract.addHistory(l_history);
            }
            //以外の場合(トランザクションテーブル.トランザクションカテゴリ = 現引現渡)の場合
            else
            {
                //create建玉変動
                WEB3TPHistory l_history =
                    WEB3TPHistory.create(l_targetContract, getCalcCondition());
                //トランザクションカテゴリ
                l_history.setTransactionCateg(l_row.getFinTransactionCateg());
                //約定済フラグ
                l_history.setExecuted(true);
                //トランザクション発生日
                l_history.setTransactionDate(l_row.getFinTransactionTimestamp());
                //単価
                l_history.setPrice(l_targetContractDetail.getContractPrice());
                //株数
                l_history.setQuantity(l_row.getQuantity());
                //受渡日
                l_history.setDeliveryDate(l_row.getDeliveryDate());

                String l_strInstBranCalaCondition = this.getCalcCondition().getInstBranCalcCondition(
                    WEB3TPCalcCondition.EQTYPE_SWAP_MARGIN_COST_UNDELIVERED_CONTRACT_LOSS_DIV);
                if (WEB3TPEqtypeSwapMarginCostUndeliveredContractLossDivDef.EXECUTE.equals(l_strInstBranCalaCondition))
                {
                    //get会社部店別余力計算条件("eqtype.swap.margin.cost.undelivered.contract.loss.div") = "1"の場合
                    //建手数料
                    BigDecimal l_bdImportedSetupFee = new BigDecimal(Double.toString(l_row.getImportedSetupFee()));

                    //建手数料消費税
                    BigDecimal l_bdImportedSetupFeeTax = new BigDecimal(Double.toString(l_row.getImportedSetupFeeTax()));

                    //名義書換料
                    BigDecimal l_bdImportedNameTransferFee = new BigDecimal(Double.toString(l_row.getImportedNameTransferFee()));

                    //名義書換料消費税
                    BigDecimal l_bdImportedNameTransferFeeTax =
                        new BigDecimal(Double.toString(l_row.getImportedNameTransferFeeTax()));

                    //管理費
                    BigDecimal l_bdImportedManagementFee = new BigDecimal(Double.toString(l_row.getImportedManagementFee()));

                    //管理費消費税
                    BigDecimal l_bdImportedManagementFeeTax =
                        new BigDecimal(Double.toString(l_row.getImportedManagementFeeTax()));

                    if (ContractTypeEnum.LONG.equals(l_targetContractDetail.getContractType()))
                    {
                        //対象建玉詳細.建区分 = "買建"の場合
                        //順日歩
                        BigDecimal l_bdImportedInterestFee = new BigDecimal(Double.toString(l_row.getImportedInterestFee()));

                        //計算した建玉諸経費を設定する。
                        BigDecimal l_bdContractTotalCost = l_bdImportedSetupFee.add(
                            l_bdImportedSetupFeeTax).add(
                            l_bdImportedNameTransferFee).add(
                            l_bdImportedNameTransferFeeTax).add(
                            l_bdImportedManagementFee).add(
                            l_bdImportedManagementFeeTax).add(
                            l_bdImportedInterestFee);
                        l_history.setContractTotalCost(l_bdContractTotalCost.doubleValue());
                    }
                    else if (ContractTypeEnum.SHORT.equals(l_targetContractDetail.getContractType()))
                    {
                        //対象建玉詳細.建区分 = "売建"の場合
                        //逆日歩
                        BigDecimal l_bdImportedPayInterestFee =
                            new BigDecimal(Double.toString(l_row.getImportedPayInterestFee()));

                        //貸株料
                        BigDecimal l_bdImportedLoanEquityFee =
                            new BigDecimal(Double.toString(l_row.getImportedLoanEquityFee()));

                        //計算した建玉諸経費を設定する。
                        BigDecimal l_bdContractTotalCost = l_bdImportedSetupFee.add(
                            l_bdImportedSetupFeeTax).add(
                            l_bdImportedNameTransferFee).add(
                            l_bdImportedNameTransferFeeTax).add(
                            l_bdImportedManagementFee).add(
                            l_bdImportedManagementFeeTax).add(
                            l_bdImportedPayInterestFee).add(
                            l_bdImportedLoanEquityFee);
                        l_history.setContractTotalCost(l_bdContractTotalCost.doubleValue());
                    }
                }
                else
                {
                    //get会社部店別余力計算条件("eqtype.swap.margin.cost.undelivered.contract.loss.div") != "1"の場合
                    l_history.setContractTotalCost(0.0D);
                }

                //calc変動反映日
                l_history.calcReflectDay(l_row.getDeliveryDate());

                //建玉変動を建玉ごと変動情報に追加
                l_historyPerContract.addHistory(l_history);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (do未約定返済変動情報ロード)
     * <BR>
     * シーケンス図「(返済後建玉情報)do未約定返済変動情報ロード」参照<BR>
     * <BR>
     * @@roseuid 41C7D9A40299
     */
    protected void loadUnexecutedRepayOrderSpecs()
    {
        final String STR_METHOD_NAME = "loadUnexecutedRepayOrderSpecs()";
        log.entering(STR_METHOD_NAME);

        final String l_strYYYYMMDDFormat = "yyyyMMdd";
        
        //余力計算条件を取得
        WEB3TPCalcCondition l_calcCondition = this.getCalcCondition();
    
        //営業日(T+0)を取得
        Date l_datBizDate0 = l_calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0);

        //未約定返済変動情報レコードセットを取得
        List l_rows = WEB3TPPersistentDataManager.getInstance().getUnExecutedOrdersAfterRepay(this);

        //取得行のサイズでループ
        int l_intSize = l_rows.size();
        for (int i = 0; i < l_intSize; i++)
        {
            //レコードを取得
            EqtypeOrderUnitRow l_row = (EqtypeOrderUnitRow)l_rows.get(i);

            //建玉返済指定情報一覧を取得
            List l_closingContractSpecs = getClosingContractSpecs(l_row.getOrderUnitId());

            //注文ごと決済損益を取得
            double l_dblProfitLoss =
                getRepaySettleProfitLossPerOrder(l_row.getOrderUnitId(), l_closingContractSpecs);

            //建玉返済指定情報一覧のサイズでループ
            int l_intClosingSize = l_closingContractSpecs.size();
            for (int j = 0; j < l_intClosingSize; j++)
            {
                //建玉返済指定情報を取得
                WEB3TPClosingContractSpec l_closingContractSpec =
                    (WEB3TPClosingContractSpec)l_closingContractSpecs.get(j);

                //対象建玉を取得
                WEB3TPTargetContract l_targetContract =
                    getTargetContract(true, l_closingContractSpec.getContractId());

                //対象建玉詳細を取得
                WEB3TPTargetContractDetail l_targetContractDetail = l_targetContract.getTargetContractDetail();
            
                //建日を取得
                Date l_datOpenDate = l_targetContractDetail.getOpenDate();

                //建日=営業日(T+0)の場合
                if(WEB3DateUtility.compareToDay(l_datOpenDate, l_datBizDate0) == 0)
                {
                    //建株数を取得
                    double l_dblQuantity = l_targetContractDetail.getQuantity();
                    
                    //返済後の建株数を算出
                    double l_dblAfterQuantity = l_dblQuantity - (l_closingContractSpec.getQuantity() - l_closingContractSpec.getExecutedQuantity());
                    
                    //按分率を算出
                    double l_dblRate = l_dblAfterQuantity / l_dblQuantity;
                    
                    //建手数料
                    double l_dblSetupFee = Math.floor(l_targetContractDetail.getSetupFee() * l_dblRate);
                    
                    //建手数料消費税
                    double l_dblSetupFeeTax = Math.floor(l_targetContractDetail.getSetupFeeTax() * l_dblRate);
                    
                    //対象建玉詳細に返済後の建株数を反映
                    l_targetContractDetail.setQuantity(l_dblAfterQuantity);
                    
                    //対象建玉詳細に返済後の建手数料を反映
                    l_targetContractDetail.setSetupFee(l_dblSetupFee);
                    
                    //対象建玉詳細に返済後の建手数料消費税を反映
                    l_targetContractDetail.setSetupFeeTax(l_dblSetupFeeTax);
                }
                                
                //建玉ごと変動情報を取得
                WEB3TPHistoryPerContract l_historyPerContract =
                    getHistoryPerContract(l_targetContract);

                //create返済後建玉変動
                WEB3TPHistoryAfterRepay l_history =
                    WEB3TPHistoryAfterRepay.createWEB3TPHistoryAfterRepay(
                        l_targetContract,
                        getCalcCondition());

                //注文単位ID
                l_history.setOrderUnitId(l_row.getOrderUnitId());
                //トランザクションカテゴリ
                l_history.setTransactionCateg(FinTransactionCateg.EQTYPE_CLOSE_MARGIN);
                //約定済フラグ
                l_history.setExecuted(true);
                //トランザクション発生日
                l_history.setTransactionDate(
                    WEB3DateUtility.getDate(l_row.getBizDate(), l_strYYYYMMDDFormat));
                //単価
                l_history.setPrice(l_targetContract.getTargetContractDetail().getContractPrice());
                //株数
                l_history.setQuantity(
                    l_closingContractSpec.getQuantity()
                        - l_closingContractSpec.getExecutedQuantity());
                //受渡日
                l_history.setDeliveryDate(l_row.getDeliveryDate());

                /*
                 * 未出来分受渡代金を、建玉返済指定情報一覧の先頭要素に寄せる。
                 */
                //INDEXカーソル==0の場合
                if (j == 0)
                {
                    //受渡代金
                    l_history.setNetAmount(l_row.getEstimatedPrice() - l_dblProfitLoss);
                }
                //以外の場合
                else
                {
                    //受渡代金
                    l_history.setNetAmount(0);
                }

                //calc変動反映日
                l_history.calcReflectDay(l_row.getDeliveryDate());

                //建玉変動を建玉ごと変動情報に追加
                l_historyPerContract.addHistory(l_history);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get注文ごと決済損益)<BR>
     * <BR>
     * シーケンス図「(返済後建玉情報)get注文ごと決済損益」参照<BR>
     * <BR>
     * @@param l_lngOrderUnitId - (注文単位ID)
     * @@param l_closingContractSpecs - (建玉返済指定情報一覧)
     * @@return double
     * @@roseuid 41C7E32E02F7
     */
    protected double getRepaySettleProfitLossPerOrder(
        long l_lngOrderUnitId,
        List l_closingContractSpecs)
    {
        final String STR_METHOD_NAME = "getRepaySettleProfitLossPerOrder(long, List)";
        log.entering(STR_METHOD_NAME);

        //引数.建玉返済指定情報一覧==nullの場合
        if(l_closingContractSpecs == null || l_closingContractSpecs.size() == 0)
        {
            //0を返却            
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //受渡代金合計
        double l_dblNetAmount = 0;

        //引数.建玉返済指定情報一覧の要素数回LOOP処理
        for (Iterator l_iterClose = l_closingContractSpecs.iterator(); l_iterClose.hasNext();)
        {
            //建玉返済指定情報を取得
            WEB3TPClosingContractSpec l_closingContractSpec =
                (WEB3TPClosingContractSpec)l_iterClose.next();

            //対象建玉を取得
            WEB3TPTargetContract l_targetContract =
                getTargetContract(true, l_closingContractSpec.getContractId());

            //建玉ごと変動情報を取得
            WEB3TPHistoryPerContract l_historyPerContract = getHistoryPerContract(l_targetContract);

            //建玉変動一覧を取得
            List l_lisHistorys = l_historyPerContract.getHistories();

            //引数.建玉変動の要素数回LOOP処理
            for (Iterator l_iter = l_lisHistorys.iterator(); l_iter.hasNext();)
            {
                //リストより要素を取得
                Object l_element = (Object)l_iter.next();

                //要素のインスタンスが、返済後建玉変動だった場合
                if (l_element instanceof WEB3TPHistoryAfterRepay)
                {
                    WEB3TPHistoryAfterRepay l_historyAftreRepay =
                        (WEB3TPHistoryAfterRepay)l_element;

                    //引数.注文単位ID = 返済後建玉変動.get注文単位ID()の場合
                    if (l_lngOrderUnitId == l_historyAftreRepay.getOrderUnitId())
                    {

                        /*
                         * 受渡代金の集計
                         * 受渡代金合計 = SUM(返済後建玉変動.get受渡代金())
                         */
                        l_dblNetAmount = l_dblNetAmount + l_historyAftreRepay.getNetAmount();
                    }
                }
            }
        }

        //受渡代金合計を返却
        log.exiting(STR_METHOD_NAME);
        return l_dblNetAmount;
    }
}
@
