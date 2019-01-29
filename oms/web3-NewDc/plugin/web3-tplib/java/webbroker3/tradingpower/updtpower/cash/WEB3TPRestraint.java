head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.36.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPRestraint.java;


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
                   2006/09/11 徐宏偉 (中訊) モデルNo.013
                   2006/09/11 徐宏偉 (中訊) モデルNo.015
                   2006/09/11 徐宏偉 (中訊) モデルNo.016  
                   2006/09/12 徐宏偉 (中訊) モデルNo.034 、No.035
                   2007/01/22 李木子 (中訊) モデルNo.093
                   2007/03/19 宮本 篤東 (SCS) モデルNo.098
                   2007/04/10 宮本篤東(SCS) モデルNo.102
Revision History : 2007/07/25 孟亜南(中訊) モデルNo.134、No.136
Revision History : 2007/11/05 趙林鵬(中訊) モデルNo.221、No.222、No.223、No.225、No.226、No.228
Revision History : 2008/05/27 孟亞南(中訊) モデルNo.285、No.286
*/

package webbroker3.tradingpower.updtpower.cash;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3DutyTypeDef;
import webbroker3.common.define.WEB3IpoOfferTradingpowerCheckDef;
import webbroker3.common.define.WEB3LotResultDef;
import webbroker3.common.define.WEB3OfferingDivDef;
import webbroker3.common.define.WEB3PaymentStopDivDef;
import webbroker3.common.define.WEB3RestraintDivDef;
import webbroker3.gentrade.data.SecurityShortageAccountDao;
import webbroker3.gentrade.data.SecurityShortageAccountRow;
import webbroker3.ipo.data.IpoOrderRow;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.data.FixedFinTransactionRow;
import webbroker3.tradingpower.data.TpCashBalanceRow;
import webbroker3.tradingpower.data.TpOtherTempRestraintRow;
import webbroker3.tradingpower.define.WEB3TPAccountTaxTypeKeyDef;
import webbroker3.tradingpower.define.WEB3TPMutualFundAdvanceRestraintDef;
import webbroker3.tradingpower.define.WEB3TPPaymentApplicationDivDef;
import webbroker3.tradingpower.define.WEB3TPServiceChargeRestraintDef;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.updtpower.WEB3TPAccountInfo;
import webbroker3.tradingpower.updtpower.WEB3TPAssetValuation;
import webbroker3.tradingpower.updtpower.WEB3TPPersistentDataManager;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (拘束金)<BR>
 * その他拘束金を表現する。
 *
 */
public class WEB3TPRestraint extends WEB3TPAssetValuation
{
    /**
     * (即日入金銘柄拘束金一覧)<BR>
     */
    private List todayDepFundRestraints;

    /**
     * (有料サービス利用拘束金一覧)<BR>
     */
    private List serviceChargeRestraints;

    /**
     * (IPO拘束金一覧)<BR>
     */
    private List ipoRestraints;

    /**
     * (日歩拘束金一覧)<BR>
     */
    private List dayIntAdjustRestraints;

    /**
     * (手数料一口処理拘束金一覧)<BR>
     */
    private List conCommAdjustRestraints;

    /**
     * (譲渡益税拘束金一覧)<BR>
     */
    private List capitalGainTaxRestraints;

    /**
     * (投資信託譲渡益税拘束金一覧)<BR>
     */
    private List mutualFundCapitalGainTaxRestraints;

    /**
     * (投資信託前受拘束金一覧)<BR>
     */
    private List mutualFundAdvanceRestraints;
    
    /**
     * (ストックオプション売付代金拘束金一覧)
     */
    private List stockOptionSellAmountRestraints;
    
    /**
     * (預り金担保出金拘束金一覧)<BR>
     */
    private List cashDepositRestraints;

    /** 
     * (取引代金)<BR>
     */
    WEB3TPTransactionAmount transactionAmount;

    /**
     * (仮拘束金一覧)<BR>
     */
    private List tempRestraints;

    /**
     * 日付フォーマット
     */
    private static String BIZDATE_FORMAT = "yyyyMMdd";

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPRestraint.class);

    /**
     * (拘束金)<BR>
     * (デフォルトコンストラクタ)<BR>
     * <BR>
     * １）以下のメンバーをArrayListで初期化<BR>
     * <BR>
     * 　@・IPO拘束金一覧<BR>
     * 　@・サービス利用拘束金一覧<BR>
     * 　@・ストックオプション売却代金拘束金一覧<BR>
     * 　@・手数料一口処理拘束金一覧<BR>
     * 　@・譲渡益税拘束金一覧<BR>
     * 　@・即日入金銘柄拘束金一覧<BR>
     * 　@・投資信託譲渡益税拘束金一覧<BR>
     * 　@・投資信託前受拘束金一覧<BR>
     * 　@・日歩拘束金一覧<BR>
     * 　@・預り金担保出金拘束金一覧<BR>
     * 　@・仮拘束金一覧<BR>
     * @@roseuid 4104CB1F0049
     */
    public WEB3TPRestraint()
    {
        this.todayDepFundRestraints = new ArrayList();
        this.serviceChargeRestraints = new ArrayList();
        this.ipoRestraints = new ArrayList();
        this.dayIntAdjustRestraints = new ArrayList();
        this.conCommAdjustRestraints = new ArrayList();
        this.capitalGainTaxRestraints = new ArrayList();
        this.mutualFundCapitalGainTaxRestraints = new ArrayList();
        this.mutualFundAdvanceRestraints = new ArrayList();
        this.stockOptionSellAmountRestraints = new ArrayList();
        this.cashDepositRestraints = new ArrayList();
        this.tempRestraints = new ArrayList();
    }

    /**
     * (create拘束金)<BR>
     * 拘束金を生成し、返却する。<BR>
     * <BR>
     * １）　@インスタンスを生成<BR>
     * ２）　@値を設定<BR>
     * 　@顧客情報＝引数.総資金.get顧客情報()<BR>
     * 　@計算条件＝引数.総資金.get計算条件()<BR>
     * 　@現注文内容＝引数.総資金.get現注文内容()<BR> 
     * ３）　@インスタンスを返却<BR>
     *
     * @@param l_this 総資金
     * @@return WEB3TPRestraint
     */
    public static WEB3TPRestraint create(WEB3TPCashValuation l_valuation)
    {
        WEB3TPRestraint l_instance = new WEB3TPRestraint();
        l_instance.setAccountInfo(l_valuation.getAccountInfo());
        l_instance.setCalcCondition(l_valuation.getCalcCondition());
        l_instance.setNewOrderSpecs(l_valuation.getNewOrderSpecs());
        l_instance.setTransactionAmount(l_valuation.getTransactionAmount());
        return l_instance;

    }

    /**
     * (calcその他拘束金) <BR>
     *<BR>
     * 引数.指定日におけるその他拘束金を集計し、結果を返却する。<BR> 
     *<BR>
     * １）引数.指定日におけるその他拘束金を集計する。 <BR>
     * <BR>
     *  [計算式] <BR>
     *　@　@”その他拘束金” = 　@this.calc即日入金銘柄拘束金(:Date = 引数.指定日) <BR>
     *　@　@　@　@　@　@　@　@　@　@　@　@　@+　@this.calcサービス利用拘束金(:Date = 引数.指定日)<BR> 
     *　@　@　@　@　@　@　@　@　@　@　@　@　@+  this.calcIPO拘束金(:Date = 引数.指定日)　@ <BR>
     *　@　@　@　@　@　@　@　@　@　@　@　@　@+  this.calc日歩拘束金(:Date = 引数.指定日) <BR>
     *　@　@　@　@　@　@　@　@　@　@　@　@　@+  this.calc手数料一口処理拘束金(:Date = 引数.指定日) <BR>
     *　@　@　@　@　@　@　@　@　@　@　@　@　@+  this.calc譲渡益税拘束金(:Date = 引数.指定日) <BR>
     *　@　@　@　@　@　@　@　@　@　@　@　@　@+  this.calc投資信託譲渡益税拘束金(:Date = 引数.指定日) <BR>
     *　@　@　@　@　@　@　@　@　@　@　@　@　@+  this.calc投資信託前受拘束金(:Date = 引数.指定日) <BR>
     *　@　@　@　@　@　@　@　@　@　@　@　@　@+  this.calcストックオプション売付代金拘束金(:Date = 引数.指定日)<BR> 
     *　@　@　@　@　@　@　@　@　@　@　@　@　@+  this.calc仮拘束金(:Date = 引数.指定日)<BR>
     *<BR>
     * ２）集計したその他拘束金を返却する。 <BR>
     *<BR>
     *　@返却値：”その他拘束金” <BR>
     * @@param l_datDate (指定日)
     * @@return double
     * @@roseuid 40DAABF203CE
     */
    public double calcOtherRestraints(Date l_datDate)
    {
        double l_dblOtherRest = 
            calcTodayDepFundRestraint(l_datDate) +
            calcServiceChargeRestraint(l_datDate) +
            calcIPORestraint(l_datDate) +
            calcDayInterestAjdustmentRestraint(l_datDate) +
            calcConsolidatedCommissionAdjustmentRestraint(l_datDate) +
            calcCapitalGainTaxRestraint(l_datDate) +
            calcMutualFundCapitalGainTaxRestraint(l_datDate) +
            calcMutualFundAdvanceRestraint(l_datDate) +
            calcStockOptionSellAmountRestraint(l_datDate) +
            calcTempRestraint(l_datDate);

        return l_dblOtherRest;

    }
    
    /**
     * (calc預り金担保出金拘束金)<BR> 
     * <BR>
     * 引数.指定日における預り金担保出金拘束金を集計し、結果を返却する。<BR> 
     * <BR>
     * １）引数.指定日における預り金担保出金拘束金を集計する。<BR> 
     * <BR>
     * 　@”預り金担保出金拘束金” = this.calc拘束金合計() <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@List = this.預り金担保出金拘束金一覧 <BR>
     * 　@　@Date = 引数.指定日 <BR>
     * <BR>
     * ２）集計した預り金担保出金拘束金を返却する。 <BR>
     * <BR>
     * 　@返却値：”預り金担保出金拘束金”<BR> 
     * @@param l_datDate (指定日)
     * @@return double 
     */
    public double calcCashDepositRestraint(Date l_datDate)
    {
        return this.calcRestraintsTotal(this.cashDepositRestraints, l_datDate);
    }

    /**
     * (calc即日入金銘柄拘束金)<BR>
     * 引数.指定日における即日入金銘柄拘束金を集計し、結果を返却する。<BR>
     * <BR>
     * １）　@即日入金銘柄拘束金一覧を取得する。<BR>
     * <BR>
     * ２）　@即日入金銘柄拘束金を集計する。<BR>
     * <BR>
     * 　@即日入金銘柄拘束金(n)  ＝ Σ(*)即日入金銘柄拘束金.get拘束金()<BR>
     * <BR>
     *  (*)集計条件：<BR>
     * 即日入金銘柄拘束金一覧にある即日入金銘柄拘束金<BR>
     * 且つ　@即日入金銘柄拘束金.is変動期間中(n)＝true<BR>
     * <BR>
     * ３）　@即日入金銘柄拘束金(n)を返却する。<BR>
     * @@param l_datDate (指定日)
     * @@return double 
     * @@roseuid 40E9FD6E00F7
     */
    public double calcTodayDepFundRestraint(Date l_datDate)
    {
        return calcRestraintsTotal(todayDepFundRestraints, l_datDate);
    }

    /**
     * (calc有料サービス利用拘束金)<BR>
     * 引数.指定日における即日入金銘柄拘束金を集計し、結果を返却する。<BR>
     * <BR>
     * １）　@有料サービス利用拘束金一覧を取得する。<BR>
     * <BR>
     * ２）　@有料サービス利用拘束金を集計する。<BR>
     * <BR>
     * 　@有料サービス利用拘束金(n)  ＝ Σ(*)有料サービス利用拘束金.get拘束金()<BR>
     * <BR>
     *  (*)集計条件：<BR>
     * 有料サービス利用拘束金一覧にある有料サービス利用拘束金<BR>
     * 且つ　@即有料サービス利用拘束金.is変動期間中(n)＝true<BR>
     * <BR>
     * ３）　@有料サービス利用拘束金(n)を返却する。<BR>
     * @@param l_datDate (指定日)
     * @@return double
     */
    public double calcServiceChargeRestraint(Date l_datDate)
    {
        return calcRestraintsTotal(serviceChargeRestraints, l_datDate);
    }
    
    /**
     * (calcIPO拘束金)<BR>
     * 引数.指定日におけるIPO拘束金を集計し、値を返却する。<BR>
     * <BR>
     * １）　@IPO拘束金一覧を取得する。<BR>
     * <BR>
     * ２）　@IPO拘束金を集計する。<BR>
     * <BR>
     * 　@IPO拘束金(n)  = Σ(*)IPO拘束金.get拘束金()<BR>
     * <BR>
     * (*)集計条件：<BR>
     * IPO拘束金一覧にあるIPO拘束金<BR>
     * 且つ　@IPO拘束金.is変動期間中(n)=true<BR>
     * <BR>
     * ３）　@IPO拘束金(n)を返却する。<BR>
     * 
     * @@param l_datDate (指定日)
     * @@return double
     * @@roseuid 40E9FD87002C
     */
    public double calcIPORestraint(Date l_datDate)
    {
        return calcRestraintsTotal(ipoRestraints, l_datDate);
    }

    /**
     * (calc日歩拘束金)<BR>
     * 引数.指定日における日歩拘束金を集計し、結果を返却する。<BR>
     * <BR>
     * １） 日歩拘束金一覧を取得する。<BR>
     * <BR>
     * ２）　@日歩拘束金を集計する。<BR>
     * 　@日歩拘束金(n)  = Σ(*)日歩拘束金.get拘束金()<BR>
     * <BR>
     * (*)集計条件：<BR>
     * 日歩拘束金一覧にある日歩拘束金<BR>
     * 且つ　@日歩拘束金.is変動期間中(n)=true<BR>
     * <BR>
     * ３）　@日歩拘束金(n)を返却する。<BR>
     * 
     * @@param l_datDate (指定日)
     * @@return double
     * @@roseuid 40E9FD8D0397
     */
    private double calcDayInterestAjdustmentRestraint(Date l_datDate)
    {
        return calcRestraintsTotal(dayIntAdjustRestraints, l_datDate);
    }

    /**
     * (calc手数料一口処理拘束金)<BR>
     * 引数.指定日における手数料一口処理拘束金を集計し、結果を返却する。<BR>
     * <BR>
     * １）　@手数料一口処理拘束金一覧を取得する。<BR>
     * <BR>
     * ２）　@手数料一口処理拘束金を集計する。<BR>
     * 　@手数料一口処理拘束金(n) 　@= Σ(*)手数料一口処理拘束金.get拘束金()<BR>
     * <BR>
     * (*)集計条件：<BR>
     * 手数料一口処理拘束金一覧にある手数料一口処理拘束金<BR>
     * 且つ　@手数料一口処理拘束金.is変動期間中(n)=true<BR>
     * <BR>
     * ３）　@手数料一口処理拘束金(n)を返却する。<BR>
     * 
     * @@param l_datDate (指定日)
     * @@return double
     * @@roseuid 40E9FD9500C8
     */
    private double calcConsolidatedCommissionAdjustmentRestraint(Date l_datDate)
    {
        return calcRestraintsTotal(conCommAdjustRestraints, l_datDate);
    }

    /**
     * (calc譲渡益税拘束金)<BR>
     * 引数.指定日における譲渡益税拘束金を集計し、結果を返却する。<BR>
     * <BR>
     * １）　@譲渡益税拘束金一覧を取得する。<BR>
     * <BR>
     * ２）　@譲渡益税拘束金を集計する。<BR>
     * 　@譲渡益税拘束金(n)  = Σ(*)譲渡益税拘束金.get拘束金()<BR>
     * <BR>
     * (*)集計条件：<BR>
     * 譲渡益税拘束金一覧にある譲渡益拘束金<BR>
     * 且つ　@譲渡益税拘束金.is変動期間中(n)=true<BR>
     * <BR>
     * ３）　@譲渡益税拘束金(n)を返却する。<BR>
     * 
     * @@param l_datDate (指定日)
     * @@return double
     * @@roseuid 40E9FD9C02FB
     */
    private double calcCapitalGainTaxRestraint(Date l_datDate)
    {
        return calcRestraintsTotal(capitalGainTaxRestraints, l_datDate);
    }
    
    /**
     * (calc投資信託譲渡益税拘束金)<BR>
     * 引数.指定日における投資信託譲渡益税拘束金を集計し、値を返却する。<BR>
     * <BR>
     * １）　@投資信託譲渡益税拘束金一覧を取得する。<BR>
     * <BR>
     * ２）　@投資信託譲渡益税拘束金を集計する。<BR>
     * <BR>
     * 　@投資信託譲渡益税拘束金(n)  = Σ(*)投資信託譲渡益税拘束金.get拘束金()<BR>
     * <BR>
     * (*)集計条件：<BR>
     * 投資信託譲渡益税拘束金一覧にある投資信託譲渡益税拘束金<BR>
     * 且つ　@投資信託譲渡益税拘束金.is変動期間中(n)=true<BR>
     * <BR>
     * ３）　@投資信託譲渡益税拘束金(n)を返却する。<BR>
     * 
     * @@param l_datDate (指定日)
     * @@return double
     */
    public double calcMutualFundCapitalGainTaxRestraint(Date l_datDate)
    {
        return calcRestraintsTotal(mutualFundCapitalGainTaxRestraints, l_datDate);
    }
    
    /**
     * (calc投資信託前受拘束金)<BR>
     * 引数.指定日における投資信託前受拘束金を集計し、値を返却する。<BR>
     * <BR>
     * １）　@投資信託前受拘束金一覧を取得する。<BR>
     * <BR>
     * ２）　@投資信託前受拘束金を集計する。<BR>
     * <BR>
     * 　@投資信託前受拘束金(n)  = Σ(*)投資信託前受拘束金.get拘束金()<BR>
     * <BR>
     * (*)集計条件：<BR>
     * 投資信託前受拘束金一覧にある投資信託前受拘束金<BR>
     * 且つ　@投資信託前受拘束金.is変動期間中(n)=true<BR>
     * <BR>
     * ３）　@投資信託前受拘束金(n)を返却する。<BR>
     * 
     * @@param l_datDate (指定日)
     * @@return double
     */
    public double calcMutualFundAdvanceRestraint(Date l_datDate)
    {
        return calcRestraintsTotal(mutualFundAdvanceRestraints, l_datDate);
    }
    
    /**
     * (calcストックオプション売付代金拘束金) <BR>
     * <BR>
     * 引数.指定日におけるストックオプション売付代金拘束金を集計し、結果を返却する。<BR> 
     * <BR>
     * １）引数.指定日におけるストックオプション売付代金拘束金を集計する。 <BR>
     * <BR>
     * 　@”ストックオプション売付代金拘束金” = this.calc拘束金合計() <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@List = this.ストックオプション売付代金拘束金一覧 <BR>
     * 　@　@Date = 引数.指定日 <BR>
     * <BR>
     * ２）集計したストックオプション売付代金拘束金を返却する。 <BR>
     * <BR>
     * 　@返却値：”ストックオプション売付代金拘束金” <BR>
     * 
     * @@param l_datDate (指定日)
     * @@return double
     */
    public double calcStockOptionSellAmountRestraint(Date l_datDate)
    {
        return calcRestraintsTotal(stockOptionSellAmountRestraints, l_datDate);
    }

    /**
     * (calc拘束金合計)<BR>
     * 引数.リストに含まれる拘束金変動を集計し<BR>
     * 値を返却する。
     * <BR>
     * @@param l_restraints　@拘束金変動の集約
     * @@param l_datDate　@(指定日)
     * @@return double
     */
    private double calcRestraintsTotal(List l_restraints, Date l_datDate)
    {
        double l_dblTotal = 0.0d;

        for (int i = 0; i < l_restraints.size(); i++)
        {
            Object l_object = l_restraints.get(i);

            WEB3TPRestraintReflector l_reflector = (WEB3TPRestraintReflector) l_object;

            if (l_reflector.isDuringReflectTime(l_datDate))
            {
                l_dblTotal += l_reflector.getAmount();
            }
        }
        return l_dblTotal;
    }

    /**
     * (calc前受拘束金集計<日計り拘束金計上分>)<BR>
     * 引数.トランザクション発生日と、同一発生日の前受拘束金の内 <BR>
     * 日計り拘束金と相殺可能な拘束金を集計する。 <BR>
     * <BR>
     * ※前受拘束金<BR> 
     * ・即日入金銘柄拘束金<BR> 
     * ・サービス利用拘束金 <BR>
     * ・IPO拘束金 <BR>
     * ・投資信託前受拘束金<BR> 
     * <BR>
     * 1)IPO拘束金のうち、引数.トランザクション発生日と同一発生日の <BR>
     * 　@IPO拘束金を集計する。 <BR>
     * <BR>
     * 　@＜LOOP処理:this.IPO拘束金一覧の要素数回＞ <BR>
     * 　@　@2-1)IPO拘束金一覧より、IPO拘束金オブジェクトを取得する。<BR> 
     * 　@　@　@-this.IPO拘束金一覧.get(index)をコール <BR>
     * <BR>
     * 　@　@2-2)引数.トランザクション発生日と同一発生日のIPO拘束金を集計する。 <BR>
     * 　@　@　@[a.IPO拘束金.getトランザクション発生日() == 引数.トランザクション発生日 の場合]<BR> 
     * <BR>
     * 　@　@　@　@前受拘束金集計<日計り拘束金計上分> += IPO拘束金.get拘束金() <BR>
     * <BR>
     * 2)即日入金銘柄拘束金のうち、引数.トランザクション発生日と同一発生日の <BR>
     * 　@即日入金銘柄拘束金を集計する。 <BR>
     * <BR>
     * 　@＜LOOP処理:this.即日入金銘柄拘束金一覧の要素数回＞ <BR>
     * 　@　@2-1)即日入金銘柄拘束金一覧より、即日入金銘柄拘束金オブジェクトを取得する。 <BR>
     * 　@　@　@-this.即日入金銘柄拘束金一覧.get(index)をコール <BR>
     * <BR>
     * 　@　@2-2)引数.トランザクション発生日と同一発生日の即日入金銘柄拘束金を集計する。 <BR>
     * <BR>
     * 　@　@　@[a.即日入金銘柄拘束金.getトランザクション発生日() == 引数.トランザクション発生日 の場合] <BR>
     * <BR>
     * 　@　@　@　@前受拘束金集計<日計り拘束金計上分> += 即日入金銘柄拘束金.get拘束金() <BR>
     * <BR>
     * 3)集計した前受拘束金集計<日計り拘束金計上分>を返却する。 <BR>
     * <BR>
     * @@param l_finTransactionDate(トランザクション発生日)
     * @@return double
     */
    public double calcAdvanceRestraintOffset(Date l_finTransactionDate)
    {
        //前受拘束金集計<日計り拘束金計上分>
        double l_dblAdRestOffset = 0.0d;

        //LOOP処理:this.IPO拘束金一覧の要素数回
        for(int i = 0; i < ipoRestraints.size(); i++)
        {
            //IPO拘束金一覧より、IPO拘束金オブジェクトを取得する。
            WEB3TPIPORestraintReflector l_ipoRestRef = (WEB3TPIPORestraintReflector)ipoRestraints.get(i);

            /*
             * 引数.トランザクション発生日と同一発生日のIPO拘束金を集計する。
             */
            //[a.IPO拘束金.getトランザクション発生日() == 引数.トランザクション発生日 の場合]
            if(WEB3DateUtility.compareToDay(
                    l_finTransactionDate,
                    l_ipoRestRef.getFinTransactionDate()) == 0)
            {
                //前受拘束金集計<日計り拘束金計上分> += IPO拘束金.get拘束金()
                l_dblAdRestOffset += l_ipoRestRef.getAmount();
            }
        }

        //LOOP処理:this.即日入金銘柄拘束金一覧の要素数回
        for(int i = 0; i < todayDepFundRestraints.size(); i++)
        {
            //即日入金銘柄拘束金一覧より、即日入金銘柄拘束金オブジェクトを取得する。
            WEB3TPTodayDepFundRestraintReflector l_todayDepFundRestRef = (WEB3TPTodayDepFundRestraintReflector)todayDepFundRestraints.get(i);

            /*
             * 引数.トランザクション発生日と同一発生日の即日入金銘柄拘束金を集計する。
             */
            //[a.即日入金銘柄拘束金.getトランザクション発生日() == 引数.トランザクション発生日 の場合]
            if(WEB3DateUtility.compareToDay(
                    l_finTransactionDate,
                    l_todayDepFundRestRef.getFinTransactionDate()) == 0)
            {
                //前受拘束金集計<日計り拘束金計上分> += 即日入金銘柄拘束金.get拘束金()
                l_dblAdRestOffset += l_todayDepFundRestRef.getAmount();
            }
        }

        //集計した前受拘束金集計<日計り拘束金計上分>を返却する。
        return l_dblAdRestOffset;
    }

    /**
     * (calc仮拘束金)<BR>
     * calc仮拘束金<BR>
     * <BR>
     * 引数.指定日における仮拘束金を集計し、結果を返却する。<BR>
     * <BR>
     * １）引数.指定日における仮拘束金を集計する。<BR>
     * <BR>
     * 　@”仮拘束金” = this.calc拘束金合計()<BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@Date = 引数.指定日<BR>
     * 　@　@String[] = null<BR>
     * <BR>
     * ２）集計した仮拘束金を返却する。<BR>
     * <BR>
     * 　@返却値：仮拘束金<BR>
     * @@param l_datDate - (指定日)<BR>
     * 指定日<BR>
     * @@return double
     */
    public double calcTempRestraint(Date l_datDate)
    {
        return this.calcTempRestraint(l_datDate, null);
    }

    /**
     * (get即日入金銘柄拘束金一覧)<BR>
     * 即日入金銘柄拘束金一覧を返す。<BR>
     * @@return List
     * @@roseuid 40EB52AB0388
     */
    public List getTodayDepFundRestraints()
    {
        return todayDepFundRestraints;
    }

    /**
     * (get有料サービス利用拘束金一覧)
     * 有料サービス利用拘束金一覧を返却する。
     * @@return List
     */
    public List getServiceChargeRestraints() {
        return serviceChargeRestraints;
    }
    
    /**
     * (getIPO拘束金一覧)<BR>
     * IPO拘束金一覧を返す。<BR>
     * @@return List
     * @@roseuid 40EB529E001D
     */
    public List getIPORestraints()
    {
        return ipoRestraints;
    }

    /**
     * (get日歩拘束金一覧)<BR>
     * 日歩拘束金一覧を返す。<BR>
     * @@return List
     * @@roseuid 40ECABB203D7
     */
    public List getDayInterestAjdustmentRestraints()
    {
        return dayIntAdjustRestraints;
    }

    /**
     * (get手数料一口処理拘束金一覧)<BR>
     * 手数料一口処理拘束金一覧を返す。<BR>
     * @@return List
     * @@roseuid 40ECABA80128
     */
    public List getConsolidatedCommissionAdjustmentRestraints()
    {
        return conCommAdjustRestraints;
    }

    /**
     * (get譲渡益税拘束金一覧)<BR>
     * 譲渡益税拘束金一覧を返す。<BR>
     * @@return List
     * @@roseuid 40EBAB5C0175
     */
    public List getCapitalGainTaxRestraints()
    {
        return capitalGainTaxRestraints;
    }

    /**
     * (get投資信託譲渡益税拘束金一覧)<BR>
     * 投資信託譲渡益税拘束金一覧を返す。<BR>
     * @@return List
     */
    public List getMutualFundCapitalGainTaxRestraints()
    {
        return mutualFundCapitalGainTaxRestraints;
    }

    /**
     * (get投資信託前受拘束金一覧)<BR>
     * 投資信託前受拘束金一覧を返す。<BR>
     * @@return List
     */
    public List getMutualFundAdvanceRestraints()
    {
        return mutualFundAdvanceRestraints;
    }
    
    /**
     * (get預り金担保出金拘束金一覧)<BR>
     * <BR>
     * this.get預り金担保出金拘束金一覧を返却する。<BR> 
     * @@return List
     */
    public List getCashDepositRestraints()
    {
        return this.cashDepositRestraints;
    }
    
    /**
     * (getストックオプション売付金拘束金一覧)<BR> 
     *<BR>
     * this.ストックオプション売付金拘束金一覧を返却する。<BR> 
     * @@return List
     */
    public List getStockOptionSellAmountRestraints()
    {
        return this.stockOptionSellAmountRestraints;
    }

    /**
     * (get仮拘束金一覧)<BR>
     * get仮拘束金一覧<BR>
     * <BR>
     * this.仮拘束金一覧を返却する。<BR>
     * @@return List
     */
    public List getTempRestraints()
    {
        return this.tempRestraints;
    }

    /**
     * (doその他拘束金ロード)<BR>
     *<BR>
     * その他拘束金に関連するデータをロードする。<BR> 
     *<BR>
     * ※シーケンス図「(拘束金)doその他拘束金ロード」参照 <BR>
     */
    public void load()
    {
        loadTodayDepFundRestraints();
        loadServiceChargeRestraints();
        loadIPORestraints();
        loadDayInterestAdjustmentRestraints();
        loadConsolidatedCommissionAdjustmentRestraints();
        loadCapitalGainTaxRestraints();
        loadMutualFundCapitalGainTaxRestraints();
        loadMutualFundAdvanceRestraints();
        loadStockOptionSellAmountRestraints();
        loadCashDepositRestraints();
        loadTempRestraints();
    }

    /**
     * (do即日入金銘柄拘束金ロード)<BR>
     * 即日入金銘柄拘束金をロードする。<BR>
     * <BR>
     * 計算条件.営業日（T+1～T+5）に対して、以下の処理を繰り返し行う。<BR>
     * <BR>
     * １）　@取引情報の配列を取得する。<BR>
     * <BR>
     *     取引情報＝取引情報取引評価.get株式取引情報()<BR>
     * <BR>
     *     引数：<BR>
     *     指定日＝営業日[n]<BR>
     *     トランザクションタイプ＝｛70：現物買取引、 130：現引取引｝<BR>
     * <BR>
     * 取得した取引情報件数分以下の処理を行う。<BR>
     * <BR>
     * ２）　@即日入金銘柄取引かどうかチェックする。<BR>
     * <BR>
     * 　@即日入金銘柄取引であった場合、以下の処理を行う。<BR>
     * <BR>
     * ３）　@即日入金銘柄拘束金を生成する。<BR>
     * <BR>
     * －設定項目<BR><BR>
     * ・拘束金      ＝  取引情報.get受渡代金()<BR>
     * ・変動反映開始日、変動反映終了日設定：calc変動反映日（）<BR>
     * ・変動反映開始日＝営業日[0]<BR>
     * ・変動反映終了日＝引数.受渡日-1<BR>
     * <BR>
     * ４）　@即日入金銘柄拘束金一覧に追加する。<BR>
     * @@roseuid 40BD65DB0306
     */
    private void loadTodayDepFundRestraints()
    {
        FinTransactionType[] l_transactionType =
            {
            FinTransactionType.EQTYPE_EQUITY_BUY
        };
                        
        Map l_todayDepFundMap = new HashMap();
        
        int l_intLastBizDateIndex = getCalcCondition().getSpecifiedPointRange();
                
        //T+1～T+5まで
        for (int i = WEB3TPSpecifiedPointDef.T_1; i <= l_intLastBizDateIndex; i++)
        {
            Date l_datBizDate = getCalcCondition().getBizDate(i);
            
            //受渡日がT+1～T+5の現物買あるいは現引の株式取引を取得する
            WEB3TPTransactionReflector[] l_tranRefs = getTransactionAmount().
                getEquityTransactions(l_transactionType, l_datBizDate);
            
            //取得した取引分ループする
            for (int j = 0; j < l_tranRefs.length; j++)
            {               
                Date l_tranDate = GtlUtils.truncateDate(l_tranRefs[j].getFinTransactionDate());
                long l_lngProductId = l_tranRefs[j].getProductId();                                                
                
                //銘柄IDで、該当取引執行日の検索結果がキャッシュされているか存在チェック
                Map l_execDateMap = (Map)l_todayDepFundMap.get(new Long(l_lngProductId));
                
                //銘柄IDでヒットした場合                
                if(l_execDateMap != null)
                {
                    //検索結果がキャッシュされている場合＝即日入金
                    if(l_execDateMap.get(l_tranDate) != null)
                    {
                        //即日入金銘柄拘束金オブジェクト作成・追加
                        WEB3TPTodayDepFundRestraintReflector l_ref =
                            WEB3TPTodayDepFundRestraintReflector.create(getCalcCondition(),
                            Math.abs(l_tranRefs[j].getAmount()), l_tranDate, l_datBizDate);
                        
                        log.debug("already cashed.");
                        log.debug("addTodayFundDepRestraint ===> source = " + l_tranRefs[j]);

                        addTodayFundDepRestraint(l_ref);
                    }

                    //検索結果がキャッシュされていない場合
                    else
                    {
                        //DBから検索
                        List l_rows = WEB3TPPersistentDataManager.getInstance().getTodayDepFundProducts(l_lngProductId, l_tranDate, this);                    
                        
                        //検索された場合
                        if((l_rows != null) && (l_rows.size() > 0))
                        {
                            //即日入金銘柄拘束金オブジェクト作成・追加
                            WEB3TPTodayDepFundRestraintReflector l_ref =
                                WEB3TPTodayDepFundRestraintReflector.create(getCalcCondition(),
                                Math.abs(l_tranRefs[j].getAmount()), l_tranDate, l_datBizDate);

                            log.debug("A. find by db.");
                            log.debug("addTodayFundDepRestraint ===> source = " + l_tranRefs[j]);
    
                            addTodayFundDepRestraint(l_ref);
                            
                            //キャッシュに取引執行日をKey,検索結果をValueとして追加
                            l_execDateMap.put(l_tranDate, l_rows);
                        }
                    }
                }

                //銘柄IDでヒットしなかった場合                
                else
                {
                    //DBから検索
                    List l_rows = WEB3TPPersistentDataManager.getInstance().getTodayDepFundProducts(l_lngProductId, l_tranDate, this);                    
                    
                    //検索された場合
                    if((l_rows != null) && (l_rows.size() > 0))
                    {
                        //即日入金銘柄拘束金オブジェクト作成・追加
                        WEB3TPTodayDepFundRestraintReflector l_ref =
                            WEB3TPTodayDepFundRestraintReflector.create(getCalcCondition(),
                            Math.abs(l_tranRefs[j].getAmount()), l_tranDate, l_datBizDate);

                        log.debug("B. find by db.");
                        log.debug("addTodayFundDepRestraint ===> source = " + l_tranRefs[j]);

                        addTodayFundDepRestraint(l_ref);                    
                        
                        //新しいMapを作成
                        //取引執行日をKey,検索結果をValueとして追加
                        l_execDateMap = new HashMap();
                        l_execDateMap.put(l_tranDate, l_rows);
                        //キャッシュに銘柄IDをKey、作成した取引執行日-検索結果のMapをValueとして追加                       
                        l_todayDepFundMap.put(new Long(l_lngProductId), l_execDateMap);

                    }
                }
            }
                
        }

    }

    /**
     * (do有料サービス利用拘束金ロード)<BR>
     * 有料サービス利用拘束金をロードする。<BR>
     * <BR>
     *  １）対象となる当日以降出金注文を取得する。<BR>
     * 　@出金注文＝資金関連データソースアクセス管理.get当日以降入出金注文()<BR>
     * 以降の処理はget当日以降入出金注文()の戻り値の要素数分LOOPする。<BR>
     * ２）以下の条件にマッチした場合、入出金注文単位より<BR>
     * 有料サービス利用拘束金を作成する。<BR>
     * 条件：<BR>
     * 入出金注文単位.注文種別　@= 出金<BR>
     * 入出金注文単位.出金申込区分 != null<BR>
     * 入出金注文単位.出金申込区分 != mf<BR>
     * 入出金注文単位.受渡日 > T+0<BR>
     * ３）有料サービス利用拘束金.create有料サービス利用拘束金()の引数に以下を渡す。<BR>
     * 計算条件 = this.get計算条件()<BR>
     * 受渡代金 = 入出金注文単位.注文数量<BR>
     * トランザクション発生日 = 入出金注文単位.発注日<BR>
     * 受渡日 = 入出金注文単位.受渡日<BR>
     * ４）有料サービス利用拘束金一覧に追加する。<BR>
     */
    private void loadServiceChargeRestraints()
    {
        //対象となる顧客の部店がサービス利用拘束金を計上する場合のみ
        //ロードする。
       String l_strVal = this.getCalcCondition().getInstBranCalcCondition(WEB3TPCalcCondition.SERVICE_CHARGE_RESTRAINT);       
       if(WEB3TPServiceChargeRestraintDef.EXCEPT.equals(l_strVal))
               return;
       
        
        //１）対象となる当日以降出金注文を取得する。<BR>
        List l_rows = WEB3TPPersistentDataManager.getInstance().getTodaysCashInOutOrders(this);
        
        // ２）以下の条件にマッチした場合、入出金注文単位より<BR>
        //有料サービス利用拘束金を作成する。<BR>
        //条件：<BR>
        //入出金注文単位.注文種別　@= 出金<BR>
        //入出金注文単位.出金申込区分 = 有料情報<BR>
        //入出金注文単位.受渡日 > T+0<BR>
        
        Date l_datT0 = getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0);
        Date l_datT5 = getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5);
        
        int i = 0;
        for(Iterator l_iter = l_rows.iterator(); l_iter.hasNext(); )
        {
            AioOrderUnitRow l_row = (AioOrderUnitRow)l_iter.next();
            Date l_datDeliveryDate = l_row.getDeliveryDate();
                                    
            //有料の判断：有料情報区分がnullかつ投信解約による出金予約レコードでない場合に修正
            if((OrderTypeEnum.CASH_OUT.equals(l_row.getOrderType())) &&
                    (l_row.getPaymentApplicationDiv() != null) &&
                    (! WEB3TPPaymentApplicationDivDef.MF_SELL_WITH_CASH_OUT.equals(l_row.getPaymentApplicationDiv())))
            {
                //バイキング手数料の場合、発注日から拘束
                //それ以外通常のサービス利用の場合T+0から拘束
                Date l_datAcceptedDate = WEB3TPPaymentApplicationDivDef.BUYKING.equals(l_row.getPaymentApplicationDiv()) ?
                        WEB3DateUtility.getDate(l_row.getBizDate(), "yyyyMMdd") : l_datT0;
                
                //受渡日がT+5以前の場合
                if(WEB3DateUtility.compareToDay(l_datT5, l_datDeliveryDate) >= 0)
                {
                    //受渡日がT+0より先のものをロード
                    if((WEB3DateUtility.compareToDay(l_datDeliveryDate, l_datT0) > 0) &&
                            (WEB3DateUtility.compareToDay(l_datDeliveryDate, l_datAcceptedDate) > 0))
                    {
                        //３）サービス利用拘束金.create有料サービス利用拘束金()の引数に以下を渡す。
                        WEB3TPServiceChargeRestraintReflector l_ref = WEB3TPServiceChargeRestraintReflector.create(getCalcCondition(), l_row.getQuantity() , l_datDeliveryDate, l_datAcceptedDate);                     
                        //４）サービス利用拘束金一覧に追加する。
                        addServiceChargeRestraint(l_ref);
                        log.debug("サービス利用拘束金追加：" + l_ref + " 入出金注文単位=[" + i + "]=" + l_row);
                    }
                    //そうでない場合はロードしない
                }
                //受渡日がT+5より先の場合
                else
                {
                    //発注日と受渡日が同日の場合もサービス利用拘束金として計上するためロードする。
                    //３）サービス利用拘束金.create有料サービス利用拘束金()の引数に以下を渡す。
                    WEB3TPServiceChargeRestraintReflector l_ref = WEB3TPServiceChargeRestraintReflector.create(getCalcCondition(), l_row.getQuantity(), l_datDeliveryDate, l_datAcceptedDate);
                    //４）サービス利用拘束金一覧に追加する。
                    addServiceChargeRestraint(l_ref);
                    log.debug("サービス利用拘束金追加：" + l_ref + " 入出金注文単位=[" + i + "]=" + l_row);
                }                
            }
            i++;
        }
        
    }
    
    /**
     * (doIPO拘束金ロード)<BR>
     * IPO拘束金に関連するデータをロードする。<BR>
     * <BR>
     * ※シーケンス図「(拘束金)doIPO拘束金ロード」参照<BR>
     * @@roseuid 40BEF66F014F
     */
    private void loadIPORestraints()
    {
        List l_orderRows = WEB3TPPersistentDataManager.getInstance().getTodaysIPOOrders(this);

        Date l_datT0 = getCalcCondition().getBizDate(0);

        for(Iterator l_iter = l_orderRows.iterator(); l_iter.hasNext();)
        {
            IpoOrderRow l_orderRow = (IpoOrderRow)l_iter.next();

            IpoProductRow l_productRow = WEB3TPPersistentDataManager.getInstance().getIPOProduct(
                    l_orderRow.getIpoProductId());

            //抽選日
            Date l_lotDate = l_productRow.getLotDate();

            //客勘反映日
            //客勘反映日は購入申込締切日となる
            Date l_accountReflectDate = l_productRow.getOfferEndDateProspec();

            //get余力計算条件
            WEB3TPCalcCondition l_calcCondition = getCalcCondition();

            //get会社部店別余力計算条件
            String l_strValue = l_calcCondition.getInstBranCalcCondition(
                WEB3TPCalcCondition.IPO_OFFER_TRADINGPOWER_CHECK);

            log.debug("l_orderRow = " + l_orderRow);
            log.debug("l_productRow getIpoProductId = " + l_productRow.getIpoProductId());
            log.debug("l_lotDate = " + l_lotDate);
            log.debug("l_accountReflectDate = " + l_accountReflectDate);

            //客勘反映日（購入申込締切日）がT+0の場合は余力対象としない
            //DeleteFlagがたっている銘柄への注文は余力対象としない (by渡辺さん)
            if ((WEB3IpoOfferTradingpowerCheckDef.CHECK.equals(l_strValue) &&
                WEB3LotResultDef.SUPPLEMENT.equals(l_orderRow.getLotResult()) &&
                !WEB3OfferingDivDef.PURCHASE_APPLICATION.equals(l_orderRow.getOfferingDiv())) ||
                (l_accountReflectDate != null && l_datT0.compareTo(l_accountReflectDate) >= 0)
                || BooleanEnum.TRUE.equals(l_productRow.getDeleteFlag()))
            {
                continue;
            }

            WEB3TPIPORestraintReflector l_ref = WEB3TPIPORestraintReflector.create(
                    getCalcCondition(),
                    l_orderRow.getPayAmount(),
                    l_lotDate,
                    l_accountReflectDate);

            addIPORestraint(l_ref);
        }
    }

    /**
     * (do日歩拘束金ロード)<BR>
     * 日歩拘束金をロードする。<BR>
     * <BR>
     * 計算条件.営業日の配列件数分以下の処理をループする。<BR>
     * <BR>
     * １）　@受渡日が営業日[n]の返済注文の件数を以下のメソッドを呼び、取得する。<BR>
     *     取引情報取引評価.get当日以降株式取引情報件数()<BR>
     * <BR>
     *     引数：<BR>
     *     トランザクションタイプ      ＝
     * ｛信用買返済、信用売返済、信用現引、信用現渡｝<BR>
     *     指定日＝営業日[n]<BR>
     * <BR>
     * ２）　@日歩調整額を取得する。<BR>
     * <BR>
     * 資産関連データソースアクセス管理.get日歩調整額()<BR>
     * <BR>
     * ３）　@日歩拘束金を生成する。<BR>
     * <BR>
     *   －設定項目<BR>
     *    ・注文件数        ＝  １）で取得した当日返済注文件数<BR>
     *    ・日歩調整額        ＝　@２）で取得した日歩調整額<BR>
     *    ・拘束金             ＝calc日歩拘束金()<BR>
     *    ・受渡日      ＝  営業日[n]<BR>
     *    ・変動反映開始日、変動反映終了日設定：calc変動反映日()<BR>
     * 　@　@変動反映開始日  ＝   受渡日<BR>
     * 　@　@変動反映終了日  ＝   営業日[5]<BR>
     * <BR>
     * ４）　@日歩拘束金一覧に生成した日歩拘束金を追加する。<BR>
     * <BR>
     * @@roseuid 40BF1999023A
     */
    private void loadDayInterestAdjustmentRestraints()
    {
        FinTransactionType[] l_transactionType =
            {
            FinTransactionType.EQTYPE_CLOSE_MARGIN_LONG,
            FinTransactionType.EQTYPE_CLOSE_MARGIN_SHORT,
            FinTransactionType.EQTYPE_SWAP_MARGIN_LONG,
            FinTransactionType.EQTYPE_SWAP_MARGIN_SHORT
        };

        double l_dblAdjustment =
            WEB3TPPersistentDataManager.getInstance().getDayInterestAdjustment(this);
        
        int l_intLastBizDateIndex = getCalcCondition().getSpecifiedPointRange();

        for (int i = WEB3TPSpecifiedPointDef.T_0; i <= l_intLastBizDateIndex; i++)
        {
            Date l_datBizDate = getCalcCondition().getBizDate(i);
            int l_intCount = getTransactionAmount().getTodaysEquityTransactionsCount(
                l_transactionType, l_datBizDate);
            
            log.debug("当日株式注文件数(getTodaysEquityTransactionsCount())=" + l_intCount);

            if (l_intCount > 0)
            {
                WEB3TPDayInterestAdjustmentRestraintReflector l_ref =
                    WEB3TPDayInterestAdjustmentRestraintReflector.create(getCalcCondition(),
                    l_intCount, l_dblAdjustment, l_datBizDate);

                log.debug("追加した日歩拘束金=" +l_ref);
                addDayInterestAdjustmentRestraint(l_ref);
            }
        }

    }

    /**
     * (do手数料一口処理拘束金ロード)<BR>
     * 手数料一口処理拘束金をロードする。<BR>
     * <BR>
     * 計算条件.営業日の配列件数分以下の処理をループする。<BR>
     * <BR>
     * １）　@商品別に対象となる注文の件数を取得する。<BR>
     *     A.注文件数<株>＝取引情報取引評価.get株式取引情報件数<当日>() (*)<BR>
     *     B.注文件数<ミニ株>＝取引情報取引評価.getミニ株取引情報件数<当日>()<BR>
     *     C.注文件数<オプション>＝取引情報取引評価.getオプション取引情報件数<当日>()<BR>
     *     D.注文件数<投信>＝取引情報取引評価.get投信株取引情報件数<当日>()<BR>
     *     E.注文件数<外株>＝取引情報取引評価.get外株取引情報件数<当日>()<BR>
     * <BR>
     *     引数：<BR>
     *     (*)トランザクションタイプ＝null<BR>
     *         指定日＝営業日[n]<BR>
     * <BR>
     * 商品毎に注文件数>0の場合、以降の処理を行う。<BR>
     * <BR>
     * ２）　@手数料一口処理調整額<商品毎>を取得する。<BR>
     *  資産関連データソースアクセス管理.get手数料一口処理調整額(手数料商品コード)<BR>
     * <BR>
     * ３）　@手数料一口処理拘束金を生成する。<BR>
     * <BR>
     *     －設定項目<BR>
     *     ・手数料商品コード＝手数料商品コード<BR>
     *     （  A---10:上場株式<BR>
     *         B---12:ミニ株式<BR>
     *         C---51:株価指数OP<BR>
     *         D---20:投資信託<BR>
     *         E---40:外国株式 ）<BR>
     * <BR>
     *     ・手数料一口処理調整額＝２）で取得した手数料一口処理調整額<BR>
     *     ・受渡日      ＝  営業日[n]<BR>
     *    ・変動反映開始日、変動反映終了日設定：calc変動反映日()<BR>
     * 　@　@変動反映開始日  ＝   受渡日<BR>
     * 　@　@変動反映終了日  ＝   営業日[5]<BR>
     * <BR>
     *   ４）手数料一口処理拘束金一覧に生成した日手数料一口処理拘束金を追加する。<BR>
     * <BR>
     * @@roseuid 40BEF62F01AD
     */
    private void loadConsolidatedCommissionAdjustmentRestraints()
    {
        //株式手数料一口処理対象となるトランザクションタイプ（現引、現渡は含まない）
        final FinTransactionType[] l_equityTransactionTypes =
            {
                FinTransactionType.EQTYPE_EQUITY_BUY,
                FinTransactionType.EQTYPE_EQUITY_SELL,
                FinTransactionType.EQTYPE_CLOSE_MARGIN_LONG,
                FinTransactionType.EQTYPE_CLOSE_MARGIN_SHORT                
            };

        double l_dblEquityAdjust = WEB3TPPersistentDataManager.getInstance().
            getConsolidatedCommissionAdjustment(WEB3CommisionProductCodeDef.LISTING_STOCK,
                                                this);
        double l_dblMiniStockAdjust = WEB3TPPersistentDataManager.getInstance().
            getConsolidatedCommissionAdjustment(WEB3CommisionProductCodeDef.MINI_STOCK,
                                                this);

        double l_dblMfAdjust = WEB3TPPersistentDataManager.getInstance().
            getConsolidatedCommissionAdjustment(WEB3CommisionProductCodeDef.MUTUAL_FUND,
                                                this);
        double l_dblOptAdjust = WEB3TPPersistentDataManager.getInstance().
            getConsolidatedCommissionAdjustment(WEB3CommisionProductCodeDef.INDEX_OP,
                                                this);
        double l_dblFeqAdjust = WEB3TPPersistentDataManager.getInstance().
            getConsolidatedCommissionAdjustment(WEB3CommisionProductCodeDef.FOREIGN_EQITY,
                                                this);

        int l_intLastBizDateIndex = getCalcCondition().getSpecifiedPointRange();

        for (int i =  WEB3TPSpecifiedPointDef.T_0; i <= l_intLastBizDateIndex; i++)
        {
            Date l_datBizDate = getCalcCondition().getBizDate(i);
            
            //株
            int l_intEquityCount = getTransactionAmount().
                getTodaysEquityTransactionsCount(l_equityTransactionTypes, l_datBizDate);

            log.debug("l_datBizDate[" + i + "]" + l_datBizDate +
                      " = getTodaysEquityTransactionsCount" + l_intEquityCount);


            if (l_intEquityCount > 0)
            {
                WEB3TPConsolidatedCommissionAdjustmentRestraintReflector l_restRef =
                    WEB3TPConsolidatedCommissionAdjustmentRestraintReflector.create(
                    getCalcCondition(), WEB3CommisionProductCodeDef.LISTING_STOCK,
                    l_dblEquityAdjust, l_datBizDate);

                addConsolidatedCommissionAdjustmentRestraint(l_restRef);

            }

            //ミニ株
            int l_intMiniStockCount = getTransactionAmount().
                getTodaysMiniStockTransactionsCount(l_datBizDate);

            log.debug("l_datBizDate[" + i + "]" + l_datBizDate +
                      " = getTodaysMiniStockTransactionsCount" + l_intMiniStockCount);

            if (l_intMiniStockCount > 0)
            {
                WEB3TPConsolidatedCommissionAdjustmentRestraintReflector l_restRef =
                    WEB3TPConsolidatedCommissionAdjustmentRestraintReflector.create(
                    getCalcCondition(), WEB3CommisionProductCodeDef.MINI_STOCK,
                    l_dblMiniStockAdjust, l_datBizDate);

                addConsolidatedCommissionAdjustmentRestraint(l_restRef);
            }

            int l_intMfCount = getTransactionAmount().
                getTodaysMutualFundTransactionsCount(l_datBizDate);

            log.debug("l_datBizDate[" + i + "]" + l_datBizDate +
                      " = getTodaysMutualFundTransactionsCount" + l_intMfCount);

            if (l_intMfCount > 0)
            {
                WEB3TPConsolidatedCommissionAdjustmentRestraintReflector l_restRef =
                    WEB3TPConsolidatedCommissionAdjustmentRestraintReflector.create(
                    getCalcCondition(), WEB3CommisionProductCodeDef.MUTUAL_FUND,
                    l_dblMfAdjust, l_datBizDate);

                addConsolidatedCommissionAdjustmentRestraint(l_restRef);
            }

            //オプション
            int l_intOptCount = getTransactionAmount().getTodaysOptionTransactionsCount(
                l_datBizDate);

            if (l_intOptCount > 0)
            {
                WEB3TPConsolidatedCommissionAdjustmentRestraintReflector l_restRef =
                    WEB3TPConsolidatedCommissionAdjustmentRestraintReflector.create(
                    getCalcCondition(), WEB3CommisionProductCodeDef.INDEX_OP,
                    l_dblOptAdjust, l_datBizDate);

                addConsolidatedCommissionAdjustmentRestraint(l_restRef);
            }

            //外株
            int l_intFeqCount = getTransactionAmount().
                getTodaysForeignEquityTransactionsCount(l_datBizDate);

            if (l_intFeqCount > 0)
            {
                WEB3TPConsolidatedCommissionAdjustmentRestraintReflector l_restRef =
                    WEB3TPConsolidatedCommissionAdjustmentRestraintReflector.create(
                    getCalcCondition(), WEB3CommisionProductCodeDef.FOREIGN_EQITY,
                    l_dblFeqAdjust, l_datBizDate);

                addConsolidatedCommissionAdjustmentRestraint(l_restRef);
            }

        }
        
        
        for(int i = 0; i < conCommAdjustRestraints.size(); i++)
        {
            log.debug("conCommAdjustRestraints[" + i + "]" + conCommAdjustRestraints.get(i));
        }

    }

    /**
     * (do譲渡益税拘束金ロード)<BR>
     * <BR>
     * 譲渡益税拘束金をロードする。<BR>
     * <BR>
     * シーケンス図「(拘束金)do譲渡益税拘束金ロード」参照<BR>
     * <BR>
     */
    private void loadCapitalGainTaxRestraints()
    {
        //すべて特定口座源泉ありでなければロードしない
        if((!getAccountInfo().isTaxSpecialWithhold(WEB3TPAccountTaxTypeKeyDef.TAX_TYPE)) &&
                (!getAccountInfo().isTaxSpecialWithhold(WEB3TPAccountTaxTypeKeyDef.TAX_TYPE_NEXT)) &&
                (!getAccountInfo().isTaxSpecialWithhold(WEB3TPAccountTaxTypeKeyDef.MARGIN_TAX_TYPE)) &&
                (!getAccountInfo().isTaxSpecialWithhold(WEB3TPAccountTaxTypeKeyDef.MARGIN_TAX_TYPE_NEXT)))
        {
            return;         
        }       
        
        //年をまたぐのがいつか取得する。
        int l_intNextYearDay = getNextYearBizDateIndex();       
        log.debug("翌年になる営業日のインデックス=" + l_intNextYearDay);

        WEB3TPPersistentDataManager l_dataMgr = WEB3TPPersistentDataManager.getInstance();

        //累積譲渡損益をロードする。
        List l_tpCashBalanceRows = l_dataMgr.getTpCashBalances(this);

        double l_thisTermCapGain = 0.0d;
        double l_nextMonthCapGain = 0.0d;

        for (Iterator l_iter = l_tpCashBalanceRows.iterator(); l_iter.hasNext(); )
        {
            TpCashBalanceRow l_row = (TpCashBalanceRow) l_iter.next();
            l_thisTermCapGain += l_row.getCurrentTermCapitalGain();
            l_nextMonthCapGain += l_row.getNextMonthCapitalGain();
        }

        WEB3TPAccumulatedCapitalGain l_accCapGain = WEB3TPAccumulatedCapitalGain.create(
            l_thisTermCapGain, l_nextMonthCapGain);

        //当期譲渡損益をロードする。
        int l_intLastBizDateIndex = getCalcCondition().getSpecifiedPointRange();
        
        WEB3TPCapitalGain[] l_capitalGains = new WEB3TPCapitalGain[l_intLastBizDateIndex + 1];
        
        for (int i = WEB3TPSpecifiedPointDef.T_0; i <= l_intLastBizDateIndex; i++)
        {
            Date l_datBizDate = getCalcCondition().getBizDate(i);            
            l_capitalGains[i] = WEB3TPCapitalGain.create(l_datBizDate);           
        }

        //株式注文より受渡日毎に当日以降譲渡損益を計上
        //
        Map l_eqOrderUnitRowMap = new HashMap();
        List l_eqOrderUnitRows = l_dataMgr.getTodaysSpecialWithHoldEquityOrders(this);
        for (Iterator l_iter = l_eqOrderUnitRows.iterator(); l_iter.hasNext(); )
        {
            EqtypeOrderUnitRow l_row = (EqtypeOrderUnitRow) l_iter.next();
            //現渡約定取消に備えて注文単位IDと注文単位のMapを作成
            l_eqOrderUnitRowMap.put(new Long(l_row.getOrderUnitId()), l_row);
            
            //未約定注文の場合
            if(OrderOpenStatusEnum.OPEN.equals(l_row.getOrderOpenStatus()) &&
                    (l_row.getExecutedQuantity() == 0))
            {
                for (int i = WEB3TPSpecifiedPointDef.T_0; i <= l_intLastBizDateIndex; i++)
                {
                    if(WEB3DateUtility.compareToDay(l_capitalGains[i].getDeliveryDate(), l_row.getDeliveryDate()) == 0)
                    {
                        //T+0と同年の場合
                        if(i < l_intNextYearDay)
                        {   
                            if(getAccountInfo().isTaxSpecialWithhold(WEB3TPAccountTaxTypeKeyDef.TAX_TYPE))
                            {
                                l_capitalGains[i].addAmount(l_row.getCapitalGain());                            
                                log.debug("当期譲渡損益に計上<現渡>=" + l_row);
                            }
                        }
                        //翌年の場合
                        else
                        {
                            if(getAccountInfo().isTaxSpecialWithhold(WEB3TPAccountTaxTypeKeyDef.TAX_TYPE_NEXT))
                            {
                                l_capitalGains[i].addAmount(l_row.getCapitalGain());              
                                log.debug("翌月譲渡損益に計上<現渡>=" + l_row);
                                
                            }                           
                        }
                    }
                }
                
            }

        }
        
        
        //株トランザクションより受渡日毎に当日以降譲渡損益を計上
        List l_eqTranRows = l_dataMgr.getTodaysSpecialWithHoldEquityFinTransactions(
            this);

        for (Iterator l_iter = l_eqTranRows.iterator(); l_iter.hasNext(); )
        {
            EqtypeFinTransactionRow l_row = (EqtypeFinTransactionRow) l_iter.next();            
            FinTransactionType l_finTransactionType = l_row.getFinTransactionType();            
                        
            for (int i = 0; i <= l_intLastBizDateIndex; i++)
            {
                if(WEB3DateUtility.compareToDay(l_capitalGains[i].getDeliveryDate(), l_row.getDeliveryDate()) == 0)
                {
                    //税区分チェック
                    //現物売約定分
                    //現渡約定分（約定取消されたものは注文のマップに登録されていないので計上しない）
                    if(FinTransactionType.EQTYPE_EQUITY_SELL.equals( l_finTransactionType) 
                            || (FinTransactionType.EQTYPE_SWAP_MARGIN_SHORT.equals(l_finTransactionType) &&
                                    (l_eqOrderUnitRowMap.get(new Long(l_row.getOrderUnitId())) != null)))
                    {
                        //T+0と同年の場合
                        if(i < l_intNextYearDay)
                        {   
                            if(getAccountInfo().isTaxSpecialWithhold(WEB3TPAccountTaxTypeKeyDef.TAX_TYPE))
                            {
                                l_capitalGains[i].addAmount(l_row.getCapitalGain());           
                                log.debug("当期譲渡損益に計上<現物株>=" + l_row);
                            }
                        }
                        //翌年の場合
                        else
                        {
                            if(getAccountInfo().isTaxSpecialWithhold(WEB3TPAccountTaxTypeKeyDef.TAX_TYPE_NEXT))
                            {
                                l_capitalGains[i].addAmount(l_row.getCapitalGain());                            
                                log.debug("翌月譲渡損益に計上<現物株>=" + l_row);
                            }                           
                        }
                    }
                    
                    //信用税区分チェック
                    else if(FinTransactionType.EQTYPE_CLOSE_MARGIN_LONG.equals( l_finTransactionType) 
                            || FinTransactionType.EQTYPE_CLOSE_MARGIN_SHORT.equals(l_finTransactionType))
                    {
                        //T+0と同年の場合
                        if(i < l_intNextYearDay)
                        {   
                            if(getAccountInfo().isTaxSpecialWithhold(WEB3TPAccountTaxTypeKeyDef.MARGIN_TAX_TYPE))
                            {
                                l_capitalGains[i].addAmount(l_row.getCapitalGain());                            
                                log.debug("当期譲渡損益に計上<現渡>=" + l_row);
                            }
                        }
                        //翌年の場合
                        else
                        {
                            if(getAccountInfo().isTaxSpecialWithhold(WEB3TPAccountTaxTypeKeyDef.MARGIN_TAX_TYPE_NEXT))
                            {
                                l_capitalGains[i].addAmount(l_row.getCapitalGain());                            
                                log.debug("翌月譲渡損益に計上<現渡>=" + l_row);
                            }                           
                        }
                    }
                    
                }
            }
        }

        //外株トランザクションより受渡日毎に当日以降譲渡損益を計上
        List l_feqTranRows = l_dataMgr.getTodaysSpecialWithHoldFeqFinTranasctions(
                this);
        
        Map l_l_feqOrderUnitMap = new HashMap();
        if(l_feqTranRows.size() > 0)
        {
            List l_feqOrderUnitRows = l_dataMgr.getTodaysFeqOrders(this); 
            for(int i = 0; i < l_feqOrderUnitRows.size(); i++)
            {
                FeqOrderUnitRow l_orderUnitRow = (FeqOrderUnitRow)l_feqOrderUnitRows.get(i);
                l_l_feqOrderUnitMap.put(new Long(l_orderUnitRow.getOrderUnitId()), l_orderUnitRow);
            }
            
        }
        for (Iterator l_iter = l_feqTranRows.iterator(); l_iter.hasNext(); )
        {
            FeqFinTransactionRow l_row = (FeqFinTransactionRow) l_iter.next();
            //外株トランザクションから注文単位ＩＤで紐付く外株注文単位を取得
            FeqOrderUnitRow l_orderUnitRow = (FeqOrderUnitRow)l_l_feqOrderUnitMap.get(new Long(l_row.getOrderUnitId()));            
            //注文が存在していれば計上
            if(l_orderUnitRow != null)
            {
                log.debug("セレクトされた外株トランザクション=" + l_row);
                
                for (int i = WEB3TPSpecifiedPointDef.T_0; i <= l_intLastBizDateIndex; i++)
                {
                    if(WEB3DateUtility.compareToDay(l_capitalGains[i].getDeliveryDate(), l_row.getDeliveryDate()) == 0)
                    {
                        //T+0と同年の場合
                        if(i < l_intNextYearDay)
                        {   
                            if(getAccountInfo().isTaxSpecialWithhold(WEB3TPAccountTaxTypeKeyDef.TAX_TYPE))
                            {
                                l_capitalGains[i].addAmount(l_row.getCapitalGain());         
                                log.debug("当期譲渡損益に計上<外株>=" + l_row);
                            }
                        }
                        //翌年の場合
                        else
                        {
                            if(getAccountInfo().isTaxSpecialWithhold(WEB3TPAccountTaxTypeKeyDef.TAX_TYPE_NEXT))
                            {
                                l_capitalGains[i].addAmount(l_row.getCapitalGain());                            
                                log.debug("翌年譲渡損益に計上<外株>=" + l_row);
                            }                           
                        }                   
                    }
                }                
            }
                
        }
        //譲渡損益拘束金を生成する。
        for (int i = WEB3TPSpecifiedPointDef.T_0; i <= l_intLastBizDateIndex; i++)
        {
            Date l_datBizDate = getCalcCondition().getBizDate(i);
            
            //譲渡益税率を取得する。
            double l_dblRate = l_dataMgr.getTaxRate(this,
                WEB3DutyTypeDef.CPITAL_GAIN_TAX, l_datBizDate);

            WEB3TPCapitalGainTaxRestraintReflector l_restRef =
                WEB3TPCapitalGainTaxRestraintReflector.create(getCalcCondition(),
                l_dblRate, l_datBizDate, l_accCapGain, l_capitalGains);
            addCapitalGainTaxRestraint(l_restRef);
            
            log.debug("追加された譲渡益税拘束金=" + l_restRef);
        }

    }
    
    /**
     * (do投資信託譲渡益税拘束金ロード)<BR>
     * 投資信託譲渡益税拘束金をロードする。<BR>
     * <BR>
     * １）投資信託注文一覧から対象となる注文を取得する。<BR>
     * 　@投資信託注文＝資金関連データソースアクセス管理.get投資信託注文<譲渡益税><BR>
     * <BR>
     * 　@以降の処理は、検索結果の各行に対して実施する。<BR><BR>
     * <BR>
     * ２）投資信託譲渡益税拘束金を生成する。<BR>
     * <BR>
     * －設定項目<BR><BR>
     * ・拘束金      ＝  投信注文.源泉徴収拘束金<BR>
     * ・変動反映開始日、変動反映終了日設定：calc変動反映日(投信注文.受渡日)<BR>
     *  変動反映開始日＝受渡日<BR>
     *  変動反映終了日＝営業日(T+5)<BR>
     * <BR>
     * ３）投資信託譲渡益税拘束金一覧に生成した投資信託譲渡益税拘束金を追加する。<BR>
     */
    private void loadMutualFundCapitalGainTaxRestraints()
    {
        //投資信託注文一覧を取得
        List l_mutualFundOrders = this.getTransactionAmount().getMutualFundTransactions();
        
        //譲渡益税拘束金を含む注文を取得
        List l_rows = WEB3TPPersistentDataManager.getInstance().getMutualFundOrdersCapitalGainTax(l_mutualFundOrders, this);

        for (Iterator l_iter = l_rows.iterator(); l_iter.hasNext(); )
        {
            MutualFundOrderUnitRow l_row = (MutualFundOrderUnitRow) l_iter.next();

            WEB3TPMutualFundCapitalGainTaxRestraintReflector l_ref = WEB3TPMutualFundCapitalGainTaxRestraintReflector.create(
                getCalcCondition(), l_row.getWithholdingTaxRestriction(), l_row.getDeliveryDate());

            addMutualFundCapitalGainTaxRestraint(l_ref);
        }
    }
    
    /**
     * (do投資信託前受拘束金ロード)<BR>
     * 投資信託前受拘束金をロードする。<BR>
     * <BR>
     * １）投資信託前受拘束なしの場合、メソッドを終了する。拘束ありの場合、以降の処理に進む。<BR>
     * <BR>
     * ２）投資信託注文一覧から対象となる注文を取得する。<BR>
     * 　@投資信託注文＝資金関連データソースアクセス管理.get投資信託前受拘束<未約定><BR>
     * <BR>
     * 　@下記 ３）、４）の処理は、検索結果の各行に対して実施する。<BR><BR>
     * <BR>
     * ３）投資信託前受拘束金を生成する。<BR>
     * <BR>
     * ４）投資信託前受拘束金一覧に生成した投資信託前受拘束金を追加する。<BR>
     * <BR>
     * －設定項目<BR>
     * 注文種別＝投信乗換の場合<BR>
     * ・拘束金      ＝  投信注文.源泉徴収拘束金<BR>
     * 注文種別＝投信乗換以外の場合<BR>
     * ・拘束金      ＝  投信注文.概算受渡代金<BR>
     * 注文種別＝投信募集の場合<BR>
     * ・受渡日      ＝  投信注文.入金日<BR>
     * 注文種別＝投信募集以外の場合<BR>
     * ・受渡日      ＝  投信注文.受渡日<BR>
     * ・変動反映開始日、変動反映終了日設定：calc変動反映日(受渡日)<BR>
     *  変動反映開始日＝発注日<BR>
     *  変動反映終了日＝受渡日前日<BR>
     * <BR>
     * ５）確定トランザクションテーブルから対象となる注文を取得する。<BR>
     * 　@投資信託注文＝資金関連データソースアクセス管理.get投資信託前受拘束<約定済><BR>
     * <BR>
     * 　@下記 ６）、７）の処理は、検索結果の各行に対して実施する。<BR><BR>
     * <BR>
     * ６）投資信託前受拘束金を生成する。<BR>
     * <BR>
     * －設定項目<BR>
     * ・拘束金      ＝  ABS(確定トランザクション.受渡代金)<BR>
     * ・変動反映開始日、変動反映終了日設定：calc変動反映日(受渡日)<BR>
     *  変動反映開始日＝営業日(T+0)<BR>
     *  変動反映終了日＝受渡日前日<BR>
     * <BR>
     * ７）投資信託前受拘束金一覧に生成した投資信託前受拘束金を追加する。<BR>
     */
    private void loadMutualFundAdvanceRestraints()
    {
        //余力計算条件を取得
        WEB3TPCalcCondition l_calcCondition = this.getCalcCondition();

        //投資信託前受拘束区分を取得
        String l_strMutualFundAdvanceRestraintDiv = l_calcCondition.getInstBranCalcCondition(WEB3TPCalcCondition.MUTUAL_FUND_ADVANCE_RESTRAINT);
        
        //拘束なしの場合、メソッド終了
        if(! WEB3TPMutualFundAdvanceRestraintDef.FROM_BIZ_DATE_UNTIL_PRE_DELIVERY_DATE.equals(l_strMutualFundAdvanceRestraintDiv))
        {
            return;
        }

        //投資信託注文一覧を取得
        List l_mutualFundOrders = this.getTransactionAmount().getMutualFundTransactions();
        
        //投資信託前受拘束金を含む注文を取得
        List l_unExecutedRows = WEB3TPPersistentDataManager.getInstance().getMutualFundAdvanceRestraintUnExecuted(l_mutualFundOrders, this);

        for (Iterator l_iter = l_unExecutedRows.iterator(); l_iter.hasNext(); )
        {
            MutualFundOrderUnitRow l_row = (MutualFundOrderUnitRow) l_iter.next();
            
            //拘束金
            double l_dblAmount = 0;
            
            //乗換の場合
            if(OrderTypeEnum.MF_SWITCHING.equals(l_row.getOrderType()))
            {
                l_dblAmount = l_row.getWithholdingTaxRestriction();
            }
            //乗換以外の場合
            else
            {
                l_dblAmount = l_row.getEstimatedPrice();
            }
            
            //受渡日
            Date l_datDeliveryDate = null;
            
            //募集の場合
            if(OrderTypeEnum.MF_RECRUIT.equals(l_row.getOrderType()))
            {
                l_datDeliveryDate = l_row.getPaymentDate();
            }
            //募集以外の場合
            else
            {
                l_datDeliveryDate = l_row.getDeliveryDate();
            }
            
            //発注日
            Date l_datBizDate = l_calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0);
            
            WEB3TPMutualFundAdvanceRestraintReflector l_ref = WEB3TPMutualFundAdvanceRestraintReflector.create(
                getCalcCondition(), l_dblAmount, l_datBizDate, l_datDeliveryDate);

            addMutualFundAdvanceRestraint(l_ref);
        }
        
        
        //約定済データから投資信託前受拘束金を含む注文を取得
        List l_executedRows = WEB3TPPersistentDataManager.getInstance().getMutualFundAdvanceRestraintFixed(this);
        
        for (Iterator l_iter = l_executedRows.iterator(); l_iter.hasNext(); )
        {
            FixedFinTransactionRow l_row = (FixedFinTransactionRow) l_iter.next();
            
            //拘束金
            double l_dblAmount = l_row.getNetAmount() * -1;
            
            //約定済なので、発注日＝営業日(T+0)とする
            Date l_datBizDate = l_calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0);

            WEB3TPMutualFundAdvanceRestraintReflector l_ref = WEB3TPMutualFundAdvanceRestraintReflector.create(
                getCalcCondition(), l_dblAmount, l_datBizDate, l_row.getDeliveryDate());

            addMutualFundAdvanceRestraint(l_ref);
        }
    }
    
    /**
     * (do預り金担保出金拘束金ロード)<BR> 
     * <BR>
     * 預り金担保出金拘束金に関連するデータをロードする。<BR> 
     * <BR>
     * ※シーケンス図「(拘束金)do預り金担保出金拘束金ロード」参照<BR> 
     */
    private void loadCashDepositRestraints()
    {
        final String STR_METHOD_NAME = "loadCashDepositRestraints()";
        log.entering(STR_METHOD_NAME);
        
        //1.1.get顧客属性()
        WEB3TPAccountInfo l_accountInfo = getAccountInfo();
        
        //1.2.get口座ID()
        long l_lngAccountId = l_accountInfo.getAccountId();
        
        try
        {
            //1.3.(*)担保不足顧客データテーブルより、当該顧客のレコードを取得する。
            //[対象テーブル]
            // 担保不足顧客データテーブル
            //[検索条件]
            // 口座ID = 顧客属性.get口座()
            SecurityShortageAccountRow l_securityShortageAccountRow = 
                (SecurityShortageAccountRow)SecurityShortageAccountDao.findRowByAccountId(l_lngAccountId);
       
            //1.4.(*)分岐フロー
            //担保不足顧客データテーブルの行オブジェクトを取得できたかつ一部出金停止の場合
            //(担保不足顧客データRow != null 
            // &&
            //　@担保不足顧客データRow.出金停止区分 == 2:一部)
            //※担保不足顧客データRow = 担保不足顧客データテーブルの検索結果行オブジェクト
            if (l_securityShortageAccountRow != null && WEB3PaymentStopDivDef.PART.equals(
                l_securityShortageAccountRow.getPaymentStopDiv()))
            {
                //1.4.1.create預り金担保出金拘束金(余力計算条件, double, Date)
                //ストックオプション売付代金拘束金オブジェクトを生成する。 
                //[引数] 
                //余力計算条件 = this.get余力計算条件() 
                //double = 担保不足顧客データRow.出金停止額 
                //Date = 担保不足顧客データRow.処理日 (*)Date型に変換する。 
                String l_strPaymentStopAmount = null;
                if (l_securityShortageAccountRow.getPaymentStopAmount() == null)
                {
                    l_strPaymentStopAmount = "0";
                }
                else
                {
                    l_strPaymentStopAmount = 
                        l_securityShortageAccountRow.getPaymentStopAmount();
                }
                WEB3TPCashDepositRestraintReflector l_reflector = 
                    WEB3TPCashDepositRestraintReflector.createCashDepositRestraint(
                        this.getCalcCondition(), 
                        Double.parseDouble(l_strPaymentStopAmount),
                        WEB3DateUtility.getDate(
                            l_securityShortageAccountRow.getProcDate(), "yyyyMMdd"));
                
                //1.4.2.add預り金担保出金拘束金(預り金担保出金拘束金)
                //生成した預り金担保出金拘束金オブジェクトを 
                //預り金担保出金拘束金一覧に追加する。 
                //[引数] 
                //預り金担保出金拘束金 = create預り金担保出金拘束金()の戻り値
                this.addCashDepositRestraint(l_reflector);
            }
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (doストックオプション売付代金拘束金ロード)<BR> 
     * <BR>
     * ストックオプション売付代金拘束金に関連するデータをロードする。 <BR>
     * <BR>
     * ※シーケンス図「(拘束金)doストックオプション売付代金拘束金ロード」参照 <BR>
     */
    private void loadStockOptionSellAmountRestraints()
    {
        final String STR_METHOD_NAME = "loadStockOptionSellAmountRestraints()";
        log.entering(STR_METHOD_NAME);
        
        List l_lisAllTransactions = new ArrayList();
        // 1.1.get取引情報一覧<当日>( )
        //取引情報一覧<当日>を取得
        List l_lisTodaysTransactions = this.transactionAmount.getTodaysTransactions();
        
        // 1.2.addAll(Collection)
        // 取得した取引情報一覧<当日>をリストに追加する。 
        // [引数] 
        // Collection = get取引情報一覧<当日>()の戻り値
        l_lisAllTransactions.addAll(l_lisTodaysTransactions);
        
        // 1.3.get取引情報一覧<確定>()
        // 取引情報一覧<確定>を取得する。 
        List l_lisFixedTransactions = this.transactionAmount.getFixedTransactions();
        
        // 1.4.addAll(Collection)
        // 取得した取引情報一覧<確定>をリストに追加する。 
        // [引数] 
        // Collection = get取引情報一覧<確定>()の戻り値
        l_lisAllTransactions.addAll(l_lisFixedTransactions);
        
        // 1.5.get営業日(int)
        // 営業日(T-1)を取得する。 
        // [引数] 
        // int = -1
        Date l_datBizDate = this.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_MINUS1);
        
        // 1.6.＜LOOP処理："取引情報一覧"の要素数回＞
        for (Iterator l_iter = l_lisAllTransactions.iterator(); l_iter.hasNext();)
        {
            //1.6.1(*)"取引情報一覧"より要素オブジェクト(=取引情報)を取得する
            WEB3TPTransactionReflector l_tranRef = (WEB3TPTransactionReflector)l_iter.next();
            
            // 1.6.2. getトランザクションタイプ()
            FinTransactionType l_finTransactionType = l_tranRef.getFinTransactionType();
            
            // 1.6.3.get税区分()
            TaxTypeEnum l_taxTypeEnum = l_tranRef.getTaxType();
            
            // 1.6.4.getトランザクション発生日()
            Date l_datFinTransactionDate = l_tranRef.getFinTransactionDate();
            
            // 1.6.5.(*)分岐フロー
            // ストックオプション残高の現物売付　@かつ トランザクション発生日がT-1以降の場合
            // ("トランザクションタイプ" = 80:現物売取引 && 
            //"税区分" = 5:ストックオプション && "トランザクション発生日" >= "T-1")
            // ※"トランザクションタイプ" = getトランザクションタイプ()の戻り値
            // ※"税区分" = get税区分()の戻り値
            // ※"トランザクション発生日" = getトランザクション発生日()の戻り値
            // ※"T-1" = get営業日()の戻り値
            if (FinTransactionType.EQTYPE_EQUITY_SELL.equals(l_finTransactionType) 
                && TaxTypeEnum.STOCK_OPTION.equals(l_taxTypeEnum) 
                && WEB3DateUtility.compareToDay(l_datFinTransactionDate, l_datBizDate) >= 0)
            {
                // 1.6.5.1.createストックオプション売付代金拘束金
                // ストックオプション売付代金拘束金オブジェクトを生成する。 
                // [引数] 
                // 余力計算条件 = this.get余力計算条件() 
                // double = 取引情報.get受渡代金() 
                // Date = 取引情報.get受渡日() 
                WEB3TPStockOptionSellAmountRestraintReflector l_reflector = 
                    WEB3TPStockOptionSellAmountRestraintReflector.createStockOptionSellAmountRestraintReflector(
                        this.getCalcCondition(), 
                        l_tranRef.getAmount(),
                        l_tranRef.getDeliveryDate());
               
                // 1.6.5.2. addストックオプション売付代金拘束金
                // 生成したストックオプション売付代金拘束金オブジェクトを 
                // ストックオプション売付代金拘束金一覧に追加する。
                // [引数] 
                // ストックオプション売付代金拘束金 = createストックオプション売付代金拘束金()の戻り値
                this.addStockOptionSellAmountRestraint(l_reflector);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (do仮拘束金ロード)<BR>
     * do仮拘束金ロード<BR>
     * <BR>
     * 仮拘束金に関連するデータをロードする。<BR>
     * <BR>
     * ※シーケンス図「(拘束金)do仮拘束金ロード」参照<BR>
     */
    private void loadTempRestraints()
    {
        final String STR_METHOD_NAME = "loadTempRestraints()";
        log.entering(STR_METHOD_NAME);

        //get顧客属性()
        WEB3TPAccountInfo l_accountInfo = this.getAccountInfo();

        //get口座ID()
        long l_lngAccountId = l_accountInfo.getAccountId();

        WEB3TPPersistentDataManager l_persistentDataManager =
            WEB3TPPersistentDataManager.getInstance();
        //getその他拘束金（仮拘束）(Long)
        //[引数]
        //口座ID = 顧客属性.get口座ID()の戻り値
        List l_lisTpOtherTempRestraints =
            l_persistentDataManager.getTempRestraint(new Long(l_lngAccountId));

        //get余力計算条件
        WEB3TPCalcCondition l_calcCondition = this.getCalcCondition();

        //LOOP処理：取得したその他拘束金(仮拘束)Rowの件数回
        int l_intTpOtherTempRestraint = l_lisTpOtherTempRestraints.size();

        for (int i = 0; i < l_intTpOtherTempRestraint; i++)
        {
            //その他拘束金(仮拘束)Row
            TpOtherTempRestraintRow l_tpOtherTempRestraintRow =
                (TpOtherTempRestraintRow)l_lisTpOtherTempRestraints.get(i);

            //create仮拘束金(余力計算条件, double, Date, Date, String)
            //[引数]
            // 余力計算条件 = get余力計算条件()の戻り値
            // 拘束金= その他拘束金(仮拘束)Row.拘束金
            // トランザクション発生日 = その他拘束金(仮拘束)Row.トランザクション発生日
            // 受渡日 = その他拘束金(仮拘束)Row.受渡日
            // 拘束金種別 = その他拘束金(仮拘束)Row.拘束金種別
            WEB3TPTempRestraint l_tempRestraint = WEB3TPTempRestraint.createTempRestraint(
                l_calcCondition,
                l_tpOtherTempRestraintRow.getAmount(),
                l_tpOtherTempRestraintRow.getTransactionDate(),
                l_tpOtherTempRestraintRow.getDeliveryDate(),
                l_tpOtherTempRestraintRow.getRestraintDiv());

            //add仮拘束金(仮拘束金)
            //this.仮拘束金一覧に、引数.仮拘束金を追加する。
            //仮拘束金 = create仮拘束金()の戻り値
            this.addTempRestraint(l_tempRestraint);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (add即日入金銘柄拘束金)<BR>
     * 即日入金銘柄拘束金一覧に引数を追加する。<BR>
     * @@param todayFundDepRestraint -
     * (即日入金銘柄拘束金)
     * @@roseuid 40ECBA3B002E
     */
    protected void addTodayFundDepRestraint(WEB3TPTodayDepFundRestraintReflector
                                            l_todayFundDepRestraint)
    {        
        if (l_todayFundDepRestraint instanceof WEB3TPTodayDepFundRestraintReflector)
        {
            todayDepFundRestraints.add(l_todayFundDepRestraint);
        }
    }

    /**
     * (add有料サービス利用拘束金)<BR>
     * 有料サービス利用拘束金一覧に引数を追加する。<BR>
     * @@param todayFundDepRestraint -
     * (有料サービス利用拘束金)
     */
    protected void addServiceChargeRestraint(WEB3TPServiceChargeRestraintReflector
                                            l_serviceChargeRestraint)
    {        
        if (l_serviceChargeRestraint instanceof WEB3TPServiceChargeRestraintReflector)
        {
            serviceChargeRestraints.add(l_serviceChargeRestraint);
        }
    }
    
    /**
     * (addIPO拘束金)<BR>
     * IPO拘束金一覧に引数を追加する。<BR>
     * @@param ipoRestraint IPO拘束金
     * @@roseuid 40ECBA0901F3
     */
    protected void addIPORestraint(WEB3TPIPORestraintReflector l_ipoRestraint)
    {
        if (l_ipoRestraint instanceof WEB3TPIPORestraintReflector)
        {
            ipoRestraints.add(l_ipoRestraint);
        }
    }

    /**
     * (add日歩拘束金)<BR>
     * 日歩拘束金一覧に引数を追加する。<BR>
     * @@param dayInterestRestraint - (日歩拘束金)
     * @@roseuid 40ECBA2A008C
     */
    protected void addDayInterestAdjustmentRestraint(
        WEB3TPDayInterestAdjustmentRestraintReflector l_dayInterestRestraint)
    {
        if (l_dayInterestRestraint instanceof
            WEB3TPDayInterestAdjustmentRestraintReflector)
        {
            dayIntAdjustRestraints.add(l_dayInterestRestraint);
        }
    }

    /**
     * (add手数料一口処理拘束金)<BR>
     * 手数料一口処理拘束金一覧に引数を追加する。<BR>
     * @@param consolidatedCommissionAdjustmentRestraint - (手数料一口処理拘束金)
     * @@roseuid 40ECBA21030C
     */
    protected void addConsolidatedCommissionAdjustmentRestraint(
        WEB3TPConsolidatedCommissionAdjustmentRestraintReflector
        l_consolidatedCommissionAdjustmentRestraint)
    {
        if (l_consolidatedCommissionAdjustmentRestraint instanceof
            WEB3TPConsolidatedCommissionAdjustmentRestraintReflector)
        {
            conCommAdjustRestraints.add(l_consolidatedCommissionAdjustmentRestraint);
        }

    }

    /**
     * (add譲渡益税拘束金)<BR>
     * 譲渡益税拘束金一覧に引数を追加する。<BR>
     * @@param capitalGainTaxRestraint - (譲渡益税拘束金)
     * @@roseuid 40ECBA3101E3
     */
    protected void addCapitalGainTaxRestraint(WEB3TPCapitalGainTaxRestraintReflector
                                              l_capitalGainTaxRestraint)
    {
        if (l_capitalGainTaxRestraint instanceof WEB3TPCapitalGainTaxRestraintReflector)
        {
            capitalGainTaxRestraints.add(l_capitalGainTaxRestraint);
        }

    }

    /**
     * (add投資信託譲渡益税拘束金)<BR>
     * 投資信託譲渡益税拘束金一覧に引数を追加する。<BR>
     * @@param l_mutualFundCapitalGainTaxRestraint - (投資信託譲渡益税拘束金)
     */
    protected void addMutualFundCapitalGainTaxRestraint(WEB3TPMutualFundCapitalGainTaxRestraintReflector
                                              l_mutualFundCapitalGainTaxRestraint)
    {
        mutualFundCapitalGainTaxRestraints.add(l_mutualFundCapitalGainTaxRestraint);
    }

    /**
     * (add投資信託前受拘束金)<BR>
     * 投資信託前受拘束金一覧に引数を追加する。<BR>
     * @@param l_mutualFundAdvanceRestraint - (投資信託前受拘束金)
     */
    protected void addMutualFundAdvanceRestraint(WEB3TPMutualFundAdvanceRestraintReflector
                                              l_mutualFundAdvanceRestraint)
    {
        mutualFundAdvanceRestraints.add(l_mutualFundAdvanceRestraint);
    }
    
    /**
     * (add預り金担保出金拘束金) <BR>
     * <BR>
     * this.預り金担保出金拘束金一覧に、引数.預り金担保出金拘束金を追加する。<BR> 
     * <BR>
     * －this.預り金担保出金拘束金一覧.add(:Object = 引数.預り金担保出金拘束金)をコール <BR>
     * @@param l_cashDepositRestraint - (預り金担保出金拘束金)
     */
    protected void addCashDepositRestraint(
        WEB3TPCashDepositRestraintReflector 
        l_cashDepositRestraint)
    {
        cashDepositRestraints.add(l_cashDepositRestraint);
    }
    
    /**
     * (addストックオプション売付代金拘束金) <BR>
     * <BR>
     * this.ストックオプション売付代金拘束金一覧に、<BR>
     * 引数.ストックオプション売付代金拘束金を追加する。<BR> 
     * <BR>
     * －this.ストックオプション売付代金拘束金一覧.add<BR>
     * (:Object = 引数.ストックオプション売付代金拘束金)をコール <BR>
     * <BR>
     * @@param l_stockOptionSellAmountRestraint - (ストックオプション売付代金拘束金)
     */
    protected void addStockOptionSellAmountRestraint(
        WEB3TPStockOptionSellAmountRestraintReflector l_stockOptionSellAmountRestraint)
    {
        this.stockOptionSellAmountRestraints.add(l_stockOptionSellAmountRestraint);
    }

    /**
     * (add仮拘束金)<BR>
     * add仮拘束金<BR>
     * <BR>
     * this.仮拘束金一覧に、引数.仮拘束金を追加する。<BR>
     * －this.仮拘束金一覧.add(:Object = 引数.仮拘束金)をコール<BR>
     * @@param l_tempRestraint - (仮拘束金)<BR>
     * 仮拘束金<BR>
     */
    protected void addTempRestraint(WEB3TPTempRestraint l_tempRestraint)
    {
        this.tempRestraints.add(l_tempRestraint);
    }

    /**
     * (get取引代金)<BR>
     * 取引代金を取得する<BR>
     *
     * @@return WEB3TPTransactionAmount
     */
    public WEB3TPTransactionAmount getTransactionAmount()
    {
        return transactionAmount;
    }
    
    /**
     * (set取引代金)<BR>
     * 引数を取引代金にセットする<BR>
     *
     * @@param WEB3TPTransactionAmount 取引代金
     */
    public void setTransactionAmount(WEB3TPTransactionAmount l_transactionAmount)
    {
        transactionAmount = l_transactionAmount;
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
            .append("todayDepFundRestraints", getTodayDepFundRestraints())
            .append("ipoRestraints", getIPORestraints())
            .append("dayIntAdjustRestraints", getDayInterestAjdustmentRestraints())
            .append("conCommAdjustRestraints", getConsolidatedCommissionAdjustmentRestraints())
            .append("capitalGainTaxRestraints", getCapitalGainTaxRestraints())
            .append("mutualFundCapitalGainTaxRestraints", getMutualFundCapitalGainTaxRestraints())
            .append("mutualFundAdvanceRestraints", getMutualFundAdvanceRestraints())
            .append("stockOptionSellAmountRestraints", getStockOptionSellAmountRestraints())
            .append("cashDepositRestraints", getCashDepositRestraints())
            .appendSuper(super.toString())
            .toString();
    }        
    
    /**
     * (get年変更日)<BR>
     * <BR>
     * 余力計算範囲内で年をまたぐ場合、またぐ営業日のインデックスを返す。<BR>
     * またがなければ、6を返す。<BR>
     * <BR>
     * 1)営業日(T+0)、営業日(T+5)を取得する。<BR>
     * <BR>
     * 　@営業日(T+0) = this.get計算条件().get営業日(指定日:int = 0:T+0)<BR>
     * 　@営業日(T+5) = this.get計算条件().get営業日(指定日:int = 5:T+5)<BR>
     * <BR>
     * 2)年変更日を計算する。<BR>
     * <BR>
     * 　@[a.余力計算範囲内(T+0～T+5)で年をまたぐ 場合]<BR>
     * 　@(営業日(T+0)の年部分 <  営業日(T+5)の年部分)<BR>
     * <BR>
     * 　@　@＜LOOP処理：余力計算範囲内(T+1～T+5) の間＞<BR>
     * 　@　@　@営業日(index) =  this.get計算条件().get営業日(指定日:int = index)<BR>
     * <BR>
     * 　@　@　@[b.営業日(index)が年をまたぐ 場合]<BR>
     * 　@　@　@(営業日(T+0)の年部分 <  営業日(index)の年部分)<BR>
     * <BR>
     * 　@　@　@　@年変更日 = index<BR>
     * <BR>
     * 　@[a.以外（余力計算範囲内(T+0～T+5)で年をまたがない 場合]<BR>
     * <BR>
     * 　@　@年変更日 = 6<BR>
     * <BR>
     * 3)計算した年変更日を返却する。<BR>
     * <BR>
     * @@return int 
     */
    private int getNextYearBizDateIndex()
    {
        int nextYearBizDateIndex = WEB3TPSpecifiedPointDef.T_5 + 1;
        Date l_datT0 = this.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0);
        int l_intLastBizDateIndex = WEB3TPSpecifiedPointDef.T_0 + WEB3TPSpecifiedPointDef.TP_CALC_RANGE;        

        //余力計算範囲内で年をまたぐ場合
        if(WEB3DateUtility.compareToYear(l_datT0, getCalcCondition().getBizDate(l_intLastBizDateIndex)) < 0)
        {
            //余力計算範囲内で年をまたぐ場合、どこでまたぐかチェック
            for(int i = WEB3TPSpecifiedPointDef.T_0 + 1; i <= WEB3TPSpecifiedPointDef.T_0 + WEB3TPSpecifiedPointDef.TP_CALC_RANGE; i++)
            {   
                Date l_compBizDate = getCalcCondition().getBizDate(i);              
                if(WEB3DateUtility.compareToYear(l_datT0, l_compBizDate) < 0)
                {           
                    return i;
                }
            }
            
        }
        
        //余力計算範囲内で同年の場合
        //ほとんどの場合このケース
        return nextYearBizDateIndex;

    }
    
    /**
     * (calc仮拘束金)<BR>
     * calc仮拘束金<BR>
     * <BR>
     * 引数.指定日における仮拘束金を集計し、結果を返却する。<BR>
     * <BR>
     * １）仮拘束一覧を取得する。<BR>
     * <BR>
     * 　@１-１）引数.拘束金種別一覧 !=null の場合、<BR>
     * 　@　@　@　@引数.仮拘束金一覧から仮拘束金オブジェクトを抽出する。<BR>
     * <BR>
     * 　@　@　@仮拘束金一覧 = this.get仮拘束金一覧()<BR>
     * <BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@　@List = this.仮拘束金一覧<BR>
     * 　@　@　@　@String[] = 引数.拘束金種別一覧<BR>
     * <BR>
     * 　@１-２）その他の場合<BR>
     * <BR>
     * 　@　@　@仮拘束金一覧 = this.仮拘束金一覧<BR>
     * <BR>
     * ２）引数.指定日における仮拘束金を集計する。<BR>
     * <BR>
     * 　@仮拘束金 = this.calc拘束金合計()<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@List = １）で取得した仮拘束金一覧<BR>
     * 　@　@Date = 引数.指定日<BR>
     * <BR>
     * ３）集計した仮拘束金を返却する。<BR>
     * <BR>
     * 　@返却値：仮拘束金<BR>
     * <BR>
     * @@param l_datDate - (指定日)<BR>
     * 指定日<BR>
     * @@param l_restraintDivList - (拘束金種別一覧)<BR>
     * 拘束金種別一覧<BR>
     * @@return double
     */
    public double calcTempRestraint(Date l_datDate, String[] l_restraintDivList)
    {
        final String STR_METHOD_NAME = "calcTempRestraint(Date, String[])";
        log.entering(STR_METHOD_NAME);

        //仮拘束一覧を取得する
        List l_lisTempRestraints = null;

        //引数.拘束金種別一覧 !=null の場合
        if (l_restraintDivList != null)
        {
            //仮拘束金一覧 = this.get仮拘束金一覧()
            l_lisTempRestraints = this.getTempRestraintList(this.tempRestraints, l_restraintDivList);
        }
        //その他の場合
        else
        {
            //仮拘束金一覧 = this.仮拘束金一覧
            l_lisTempRestraints = this.tempRestraints;
        }

        //引数.指定日における仮拘束金を集計する
        //仮拘束金 = this.calc拘束金合計()
        //集計した仮拘束金を返却する。
        log.exiting(STR_METHOD_NAME);
        return this.calcRestraintsTotal(l_lisTempRestraints, l_datDate);
    }

    /**
     * (get顧客勘定残高反映対象外拘束金種別一覧)<BR>
     * get顧客勘定残高反映対象外拘束金種別一覧<BR>
     * <BR>
     * 顧客勘定残高反映対象外の拘束金種別一覧の配列を作成し返却する。<BR>
     * <BR>
     * 以下の値で、String配列を初期化し返却する。<BR>
     * <BR>
     * 　@1：投信乗換売却代金<BR>
     * <BR>
     * @@return String[]
     */
    public String[] getAccountCashBalanceReflectObjectRestraintDivList()
    {
        return new String[]{WEB3RestraintDivDef.MF_SWITCHING_SELL_AMOUNT};
    }

    /**
     * (get仮拘束金一覧)<BR>
     * get仮拘束金一覧<BR>
     * <BR>
     * 引数.仮拘束金一覧から仮拘束金オブジェクトを抽出し返却する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@引数.拘束金種別一覧に含まれる拘束金種別 == 仮拘束金.get拘束金種別() <BR>
     * <BR>
     * １）戻り値の仮拘束一覧(List)を生成する。<BR>
     * <BR>
     * ２）仮拘束金一覧を作成する。<BR>
     * <BR>
     * 　@＜LOOP処理：引数.仮拘束金一覧の要素数回＞ <BR>
     * <BR>
     * 　@　@仮拘束金オブジェクト = 引数.仮拘束金一覧.get(:int = LOOPのIndex項番) <BR>
     * <BR>
     * 　@　@[a.引数.拘束金種別一覧に含まれる拘束金種別と、<BR>
     * 　@　@　@仮拘束金オブジェクトの拘束金種別が一致する場合]<BR>
     * 　@　@　@(引数.拘束金種別一覧に含まれる拘束金種別 == 仮拘束金.get拘束金種別())<BR>
     * <BR>
     * 　@　@　@仮拘束金一覧に仮拘束金オブジェクトを追加する。<BR>
     * <BR>
     * ３）作成した仮拘束金一覧を返却する。<BR>
     * <BR>
     * 　@返却値：仮拘束金一覧<BR>
     * <BR>
     * @@param l_lisTempRestraints - (仮拘束金一覧)<BR>
     * 仮拘束金一覧<BR>
     * @@param l_restraintDivs - (拘束金種別一覧)<BR>
     * 拘束金種別一覧<BR>
     * @@return List
     */
    public List getTempRestraintList(List l_lisTempRestraints, String[] l_restraintDivs)
    {
        final String STR_METHOD_NAME = "getTempRestraintList(List, String[])";
        log.entering(STR_METHOD_NAME);

        //戻り値の仮拘束一覧(List)を生成する。
        List l_lisTempRestraintList = new ArrayList();
        int l_intTempRestraintSize = l_lisTempRestraints.size();

        //仮拘束金一覧を作成する。
        for (int i = 0; i < l_intTempRestraintSize; i++)
        {
            //仮拘束金オブジェクト = 引数.仮拘束金一覧.get(:int = LOOPのIndex項番)
            WEB3TPTempRestraint l_tempRestraint = (WEB3TPTempRestraint)l_lisTempRestraints.get(i);
            //引数.拘束金種別一覧に含まれる拘束金種別 == 仮拘束金.get拘束金種別()
            boolean l_blnContain =
                WEB3Toolkit.contain(l_restraintDivs, l_tempRestraint.getRestraintDiv());
            //仮拘束金一覧に仮拘束金オブジェクトを追加する。
            if (l_blnContain)
            {
                l_lisTempRestraintList.add(l_tempRestraint);
            }
        }

        //作成した仮拘束金一覧を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_lisTempRestraintList;
    }
}
@
