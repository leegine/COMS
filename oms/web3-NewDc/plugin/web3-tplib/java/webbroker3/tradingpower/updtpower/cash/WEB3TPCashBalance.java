head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.35.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPCashBalance.java;


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
Revision History : 2004/08/02 堀野 和美(FLJ)  新規作成
                   2006/09/25 車進　@   (中訊) モデルNo.059
                   2006/09/25 車進　@   (中訊) モデルNo.060
                   2006/09/25 車進　@   (中訊) モデルNo.061  
Revision History : 2009/12/15 張騰宇 モデルNo.409 410 429
*/

package webbroker3.tradingpower.updtpower.cash;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow;

import webbroker3.common.define.WEB3DealTypeDef;
import webbroker3.tradingpower.data.TpCashBalanceRow;
import webbroker3.tradingpower.updtpower.WEB3TPAccountInfo;
import webbroker3.tradingpower.updtpower.WEB3TPAssetValuation;
import webbroker3.tradingpower.updtpower.WEB3TPPersistentDataManager;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (預り金)<BR>
 * 預り金残高の推移を表現する。<BR>
 */
public class WEB3TPCashBalance extends WEB3TPAssetValuation
{

    /** ログ　@ユーティリティ　@*/
    private static final WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3TPCashBalance.class);

    /**
     * (預り金一覧<確定>)<BR>
     */
    private List fixedCashBalances;

    /**
     * (過日約定取消情報一覧)<BR>
     * <BR>
     * 過日約定取消情報オブジェクトのリスト<BR>
     */
    private List elapsedDayExecuteCancelInfoList;

    /**
     * @@roseuid 4104B09E0337
     */
    public WEB3TPCashBalance()
    {
        fixedCashBalances = new ArrayList();
        elapsedDayExecuteCancelInfoList = new ArrayList();
    }

    /**
     * (create預り金)<BR>
     * 預り金インスタンスを生成し、引数.総資金から各値を変数にセットする。<BR>
     * <BR>
     * １）　@インスタンスを生成<BR>
     * ２）　@値を設定<BR>
     * 　@顧客情報＝引数.総資金.get顧客情報()<BR>
     * 　@計算条件＝引数.総資金.get計算条件()<BR>
     * 　@現注文内容＝引数.総資金.get現注文内容()<BR> 
     * ３）　@インスタンスを返却<BR>
     *
     * @@param l_valuation (総資金)
     * @@return WEB3TPCashBalance
     */
    public static WEB3TPCashBalance create(WEB3TPCashValuation l_valuation)
    {
        WEB3TPCashBalance l_instance = new WEB3TPCashBalance();
        l_instance.setAccountInfo(l_valuation.getAccountInfo());
        l_instance.setCalcCondition(l_valuation.getCalcCondition());
        l_instance.setNewOrderSpecs(l_valuation.getNewOrderSpecs());
        return l_instance;
    }

    /**
     * (calc預り金残高)<BR>
     * <BR>
     * 引数.指定日の預り金残高を返却する。<BR>
     * <BR>
     * １）引数.指定日の確定預り金を取得する。<BR>
     * <BR>
     * 　@"確定預り金" = this.calc確定預り金(:Date = 引数.指定日)<BR>
     * <BR>
     * ２）引数.指定日の過日約定取消代金を取得する。<BR>
     * <BR>
     * 　@"過日約定取消代金" = this.calc過日約定取消代金(:Date = 引数.指定日)<BR>
     * <BR>
     * ３）"預り金"を計算する。<BR>
     * <BR>
     * 　@"預り金" = "確定預り金"　@+　@"過日約定取消代金"<BR>
     * <BR>
     * ４）計算した"預り金"を返却する。<BR>
     * <BR>
     * 　@返却値："預り金"<BR>
     * <BR>
     * @@param l_datDate (指定日)
     * @@return double
     */
    public double calcCashBalance(Date l_datDate)
    {
        //"確定預り金" = this.calc確定預り金(:Date = 引数.指定日)
        double l_dblFixedBalance = calcFixedCashBalance(l_datDate);
        
        //引数.指定日の過日約定取消代金を取得する。
        //"過日約定取消代金" = this.calc過日約定取消代金(:Date = 引数.指定日)
        double l_dblElapsedDayExecuteCancelAmount = calcElapsedDayExecuteCancelPrice(l_datDate);
        
        //"預り金" = "確定預り金"　@+　@"過日約定取消代金" 
        double l_dblBanlance = l_dblFixedBalance + l_dblElapsedDayExecuteCancelAmount;
        
        //計算した"預り金"を返却する。 
        return l_dblBanlance;
    }

    /**
     * (calc確定預り金)<BR>
     * 引数にて指定日(T+0～T+5)を受け取り、指定日に対応した<BR>
     * 預り金残高を集計し返却する。<BR>
     * <BR>
     * １）　@確定預り金一覧を取得する。<BR>
     * <BR>
     * ２）　@預り金残高を集計する。<BR>
     * 預り金残高(n) = Σ(*)確定預り金.get預り金()<BR>
     * <BR>
     * (*)集計条件：<BR>
     * 確定預り金一覧にある確定預り金<BR>
     * 且つ　@確定預り金.is変動期間中(n)=true<BR>
     * <BR>
     * ３）　@預り金残高(n)を返却する。<BR>
     * <BR>
     * @@param l_datDate - 指定日
     * @@return double
     * @@roseuid 40CD31CD03A3
     */
    public double calcFixedCashBalance(Date l_datDate)
    {
        double l_dblTotal = 0.0d;

        for (Iterator l_iter = fixedCashBalances.iterator(); l_iter.hasNext(); )
        {
            WEB3TPCashBalanceReflector l_ref = (WEB3TPCashBalanceReflector) l_iter.next();
            if (l_ref.isDuringReflectTime(l_datDate))
            {
                l_dblTotal += l_ref.getAmount();
            }

        }
        return l_dblTotal;
    }

    /**    
     * (calc過日約定取消代金)<BR>
     * <BR>
     * 引数.指定日の過日約定取消代金を集計して返却する。<BR> 
     * <BR>
     * １）過日約定取消代金を集計する。<BR> 
     * <BR>
     * 　@＜LOOP処理：this.過日約定取消情報一覧の要素数回＞<BR> 
     * <BR>
     * 　@　@①@過日約定取消情報一覧より、要素オブジェクト(="過日約定取消情報")を取得する。<BR> 
     * <BR>
     * 　@　@　@"過日約定取消情報" = this.過日約定取消情報一覧.get(:int = LOOP中のIndex項番)<BR> 
     * <BR>
     * 　@　@②"過日約定取消代金"を集計する。<BR> 
     * <BR>
     * 　@　@　@[a."過日約定取消情報".is変動反映期間中(:Date = 引数.指定日) == true]<BR> 
     * <BR>
     * 　@　@　@　@"過日約定取消代金" += "過日約定取消代金".get取消代金()<BR> 
     * <BR>
     * ２）集計した"過日約定取消代金を返却する。<BR> 
     * <BR>
     * 　@返却値："過日約定取消代金<BR>
     * <BR>
     * @@param l_datDate - 指定日
     * @@return double
     */
    public double calcElapsedDayExecuteCancelPrice(Date l_datDate)
    {
        double l_dblCancelAmount = 0.0d;

        for (Iterator l_iter = elapsedDayExecuteCancelInfoList.iterator(); l_iter.hasNext(); )
        {
            //①@過日約定取消情報一覧より、要素オブジェクト(="過日約定取消情報")を取得する。 
            //"過日約定取消情報" = this.過日約定取消情報一覧.get(:int = LOOP中のIndex項番)
            WEB3TPElapsedDayExecuteCancelReflector l_elapsedDayExcuteCancelInfo = 
                (WEB3TPElapsedDayExecuteCancelReflector)l_iter.next();

            //②"過日約定取消代金"を集計する。
            //[a."過日約定取消情報".is変動反映期間中(:Date = 引数.指定日) == true]
            //"過日約定取消代金" += "過日約定取消代金".get取消代金()
            if (l_elapsedDayExcuteCancelInfo.isDuringReflectTime(l_datDate))
            {
                l_dblCancelAmount += l_elapsedDayExcuteCancelInfo.getCancelAmount();
            }

        }

        //２）集計した"過日約定取消代金を返却する。
        return l_dblCancelAmount;
    }

    /**
     * (get確定預り金一覧)<BR>
     * 確定預り金一覧を返す。<BR>
     * <BR>
     * @@return List
     * @@roseuid 40ED041B03E7
     */
    public List getFixedCashBalances()
    {
        return fixedCashBalances;
    }

    /**
     * (get過日約定取消情報一覧)<BR>
     * <BR>
     * this.過日約定取消情報一覧を返却する。<BR>
     * <BR>
     * @@return List
     */
    public List getElapsedDayExecuteCancelInfoList()
    {
        //this.過日約定取消情報一覧を返却する。
        return elapsedDayExecuteCancelInfoList;
    }
    
    /**
     * (do預り金ロード)<BR> 
     * <BR>
     * 預り金関連データをロードする。<BR> 
     * <BR>
     * ※シーケンス図「(預り金)do預り金ロード」参照<BR> 
     * <BR>
     */
    public void load()
    {
        //1.1do預り金<確定>ロード()
        loadFixedCashBalances();
        
        //1.2do債券注文過日約定取消情報ロード()
        loadBondOrderElapsedDayExecuteCancelInfo();
    }

    /**
     * (do確定預り金ロード)<BR>
     * <BR>
     * 確定預り金関連データをロードする。<BR>
     * <BR>
     * ※シーケンス図「(預り金)do預り金<確定>ロード」参照<BR>
     * <BR>
     * @@roseuid 40BC5E6F0132
     */
    private void loadFixedCashBalances()
    {
        List l_tpCashBalanceRows = WEB3TPPersistentDataManager.getInstance().getTpCashBalances(this);

        for (Iterator l_iter = l_tpCashBalanceRows.iterator(); l_iter.hasNext(); )
        {
            TpCashBalanceRow l_row = (TpCashBalanceRow) l_iter.next();
            //get顧客属性( )
            WEB3TPAccountInfo l_accountInfo = getAccountInfo();
            //get補助口座ID(補助口座タイプ : SubAccountTypeEnum)
            //補助口座タイプ = 1:株式取引口座（預り金）
            long l_lngSubAccountId = 0;
            if (l_accountInfo != null)
            {
                l_lngSubAccountId = l_accountInfo.getSubAccountId(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            }
            boolean l_blnIsDeposit = false;
            if (l_lngSubAccountId == l_row.getSubAccountId())
            {
                l_blnIsDeposit = false;
            }
            else
            {
                l_blnIsDeposit = true;
            }
            WEB3TPCashBalanceReflector l_cashBalanceT0 = WEB3TPCashBalanceReflector.
                create(getCalcCondition(), l_row.getCashBalance0(), false,
                       getCalcCondition().getBizDate(0), l_blnIsDeposit);
            addFixedCashBalance(l_cashBalanceT0);
            WEB3TPCashBalanceReflector l_cashBalanceT1 = WEB3TPCashBalanceReflector.
                create(getCalcCondition(), l_row.getCashBalance1(), false,
                       getCalcCondition().getBizDate(1), l_blnIsDeposit);
            addFixedCashBalance(l_cashBalanceT1);
            WEB3TPCashBalanceReflector l_cashBalanceT2 = WEB3TPCashBalanceReflector.
                create(getCalcCondition(), l_row.getCashBalance2(), false,
                       getCalcCondition().getBizDate(2), l_blnIsDeposit);
            addFixedCashBalance(l_cashBalanceT2);
            WEB3TPCashBalanceReflector l_cashBalanceT3 = WEB3TPCashBalanceReflector.
                create(getCalcCondition(), l_row.getCashBalance3(), false,
                       getCalcCondition().getBizDate(3), l_blnIsDeposit);
            addFixedCashBalance(l_cashBalanceT3);
            WEB3TPCashBalanceReflector l_cashBalanceT4 = WEB3TPCashBalanceReflector.
                create(getCalcCondition(), l_row.getCashBalance4(), false,
                       getCalcCondition().getBizDate(4), l_blnIsDeposit);
            addFixedCashBalance(l_cashBalanceT4);
            WEB3TPCashBalanceReflector l_cashBalanceT5 = WEB3TPCashBalanceReflector.
                create(getCalcCondition(), l_row.getCashBalance5(), false,
                       getCalcCondition().getBizDate(5), l_blnIsDeposit);
            addFixedCashBalance(l_cashBalanceT5);
        }

    }

    /**
     * (do債券注文過日約定取消情報ロード)<BR>
     * <BR>
     * 債券注文の過日約定取消情報をロードする。<BR> 
     * <BR>
     * ※シーケンス図「(預り金)do債券注文過日約定取消情報ロード」参照<BR> 
     * <BR>
     */
    private void loadBondOrderElapsedDayExecuteCancelInfo()
    {
        //ロード対象となる債券注文単位テーブル行オブジェクトのリストを取得する。
        List l_bondOrderUnitRows = WEB3TPPersistentDataManager.getInstance().getBondOrdersCash(this);
        
        //get債券注文<預り金>(資産評価)の戻り値　@!= nullの場合
        if (l_bondOrderUnitRows != null)
        {
           // LOOP処理：get債券注文<預り金>()の戻り値の要素数回  
            for (Iterator l_iter = l_bondOrderUnitRows.iterator(); l_iter.hasNext(); )
            {
                //トランザクションタイプ別の損益方向を取得する。                
                BondOrderUnitRow l_row = (BondOrderUnitRow) l_iter.next();
                int l_intCashDir = WEB3TPTransactionReflector.getCashDir(
                    l_row.getOrderType().toFinTransactionType());
                
                //取消数量"、"取消代金"を計算する
                //"取消数量" = 引数.債券注文.get約定数量()
                double l_dblCancelQuantity = l_row.getExecutedQuantity();
                
                //"取消代金" = "約定代金"(*1) × -1
                //(*1)"約定代金" = 引数.債券注文.get受渡代金(円貨)() × get損益方向()の戻り値
                //（約定取消の場合、約定の反対仕訳となる。よって、約定代金に-1を乗じる。）
                double l_dbExecutedAmount = l_row.getEstimatedPrice() * l_intCashDir;
                double l_dbCancelAmount = l_dbExecutedAmount * -1;
                Date l_datDeliveryDate = null;
                
                //受渡日"を計算する。
                //募集取引の場合
                //（引数.債券注文.get注文種別() == 401：債券買い注文&&
                //引数.債券注文.get取引() == 35:募集取引）
                //"受渡日" = 引数.債券注文.get入金日()
                if (OrderTypeEnum.BOND_BUY.equals(l_row.getOrderType()) && 
                    WEB3DealTypeDef.RECRUIT_TRADING.equals(l_row.getDealType()))
                {
                    l_datDeliveryDate = l_row.getPaymentDate();
                }
                //[a.以外の場合]
                //"受渡日" = 引数.債券注文.get受渡日()
                else
                {
                    l_datDeliveryDate = l_row.getDeliveryDate();
                }

                //過日約定取消情報オブジェクトを生成する。
                //余力計算条件 = this.get余力計算条件()
                //ProductTypeEnum = 引数.債券注文.get銘柄タイプ() 
                //long = 引数.債券注文.get銘柄ID() 
                //FinTransactionType = 引数.債券注文.get注文種別().toFinTransactionType() 
                //Date = 引数.債券注文.get発注日() 
                //double = "取消数量" 
                //double = "取消代金" 
                //Date = "受渡日" 
                //TaxTypeEnum = 引数.債券注文.get税区分()
                WEB3TPElapsedDayExecuteCancelReflector l_ElapsedDayExecuteCancelInfo = 
                    WEB3TPElapsedDayExecuteCancelReflector.createWEB3TPElapsedDayExecuteCancelReflector(
                        getCalcCondition(), 
                        l_row.getProductType(), 
                        l_row.getProductId(), 
                        l_row.getOrderType().toFinTransactionType(), 
                        WEB3DateUtility.getDate(l_row.getBizDate(), "yyyyMMdd"), 
                        l_dblCancelQuantity, 
                        l_dbCancelAmount, 
                        l_datDeliveryDate, 
                        l_row.getTaxType());

                //取得した過日約定取消情報オブジェクトを、 this.過日約定取消情報一覧に追加する。 
                addElapsedDayExecuteCancelReflector(l_ElapsedDayExecuteCancelInfo);
            }
        }
    }

    /**
     * (add確定預り金)<BR>
     * 確定預り金一覧に引数を追加する。<BR>
     * @@param WEB3TPCashBalance - (確定預り金)
     * @@roseuid 40E113FB031E
     */
    protected void addFixedCashBalance(WEB3TPCashBalanceReflector l_cashBalance)
    {
        if (l_cashBalance instanceof WEB3TPCashBalanceReflector)
        {
            fixedCashBalances.add(l_cashBalance);
        }
    }

    /**
     * (add過日約定取消情報)<BR>
     * <BR>
     * 引数.過日約定取消情報を、this過日約定取消情報一覧に追加する。<BR> 
     * -this.過日約定取消情報一覧.add(:Object = 引数.過日約定取消情報)をコール<BR>
     * <BR>
     * @@param l_cancelInfo - (過日約定取消情報)
     * @@roseuid 40E113FB031E
     */
    protected void addElapsedDayExecuteCancelReflector(WEB3TPElapsedDayExecuteCancelReflector l_cancelInfo)
    {
        if (l_cancelInfo instanceof WEB3TPElapsedDayExecuteCancelReflector)
        {
            elapsedDayExecuteCancelInfoList.add(l_cancelInfo);
        }
    }

    /**
     * (calc確定保証金)<BR>
     * (calc確定保証金) <BR>
     * 
     * 引数.指定日の確定保証金を集計して返却する。 <BR>
     * <BR>
     * １）確定保証金を集計する。 <BR>
     * 　@ <BR>
     * 　@＜LOOP処理：this.確定預り金一覧の要素数回＞ <BR>
     * <BR>
     * 　@　@①@確定預り金一覧より、要素オブジェクト(="確定預り金")を取得する。 <BR>
     * <BR>
     * 　@　@　@"確定預り金" = this.確定預り金一覧.get(:int = LOOP中のIndex項番) <BR>
     * <BR>
     * 　@　@②"確定保証金"を集計する。 <BR>
     * <BR>
     * 　@　@　@[a."確定預り金".is変動反映期間中(:Date = 引数.指定日) == true 且つ　@"確定預り金".is保証金() == true] <BR>
     * <BR>
     * 　@　@　@　@"確定保証金" += "確定預り金".get預り金残高() <BR>
     * <BR>
     * ２）集計した"確定保証金"を返却する。 <BR>
     * <BR>
     * 　@返却値："確定保証金" <BR>
     * @@param l_datDate -   (指定日)<BR>
     * 指定日<BR>
     * @@return double
     */
    public double calcFixedDeposit(Date l_datDate)
    {
        final String STR_METHOD_NAME = "calcFixedDeposit(Date)";
        log.entering(STR_METHOD_NAME);

        double l_dblFixedDeposit = 0;
        //１）確定保証金を集計する。
        //＜LOOP処理：this.確定預り金一覧の要素数回＞
        List l_lisFixedCashBalances = this.getFixedCashBalances();
        for (Iterator l_iterFixedCashBalances = l_lisFixedCashBalances.iterator(); l_iterFixedCashBalances.hasNext();)
        {
            //①@確定預り金一覧より、要素オブジェクト(="確定預り金")を取得する。
            //"確定預り金" = this.確定預り金一覧.get(:int = LOOP中のIndex項番) 
            WEB3TPCashBalanceReflector l_cashBalanceReflector =
                (WEB3TPCashBalanceReflector)l_iterFixedCashBalances.next();
            //②"確定保証金"を集計する。
            //[a."確定預り金".is変動反映期間中(:Date = 引数.指定日) == true 且つ　@"確定預り金".is保証金() == true]
            if (l_cashBalanceReflector.isDuringReflectTime(l_datDate) && l_cashBalanceReflector.isDeposit())
            {
                l_dblFixedDeposit = l_dblFixedDeposit + l_cashBalanceReflector.getAmount();
            }
        }
        //２）集計した"確定保証金"を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_dblFixedDeposit;
    }
    

    /**
     * このオブジェクトの文字列表現を返す。
     * 
     * @@return String
     */
    public String toString()
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .append("fixedCashBalances", this.getFixedCashBalances())
            .append("ElapsedDayExecuteCancelInfoList", this.getElapsedDayExecuteCancelInfoList())
            .appendSuper(super.toString())
            .toString();

    }

}
@
