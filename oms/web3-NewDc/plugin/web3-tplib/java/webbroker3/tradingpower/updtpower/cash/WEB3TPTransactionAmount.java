head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.35.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTransactionAmount.java;


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
                    2006/09/12 徐宏偉 (中訊) モデルNo.021～028
                    2006/09/25 徐宏偉 (中訊) モデルNo.054～057
                    2007/04/10 宮本篤東(SCS) モデルNo.101
 Revision History : 2007/07/25 孟亜南(中訊) モデルNo.133 No.135
 Revision History : 2009/12/15 張騰宇 モデルNo.412 No.413 No.416 No.431 No.434
 Revision History : 2010/01/11 武波　@  (中訊)モデルNo.418,446
 */

package webbroker3.tradingpower.updtpower.cash;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.common.define.WEB3DealTypeDef;
import webbroker3.common.define.WEB3IpoOfferTradingpowerCheckDef;
import webbroker3.common.define.WEB3LotResultDef;
import webbroker3.common.define.WEB3OfferingDivDef;
import webbroker3.ipo.data.IpoOrderRow;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.data.FixedFinTransactionRow;
import webbroker3.tradingpower.define.WEB3TPIPOHostAcceptStatusDef;
import webbroker3.tradingpower.define.WEB3TPPaymentApplicationDivDef;
import webbroker3.tradingpower.define.WEB3TPServiceChargeRestraintDef;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.updtpower.WEB3TPAssetValuation;
import webbroker3.tradingpower.updtpower.WEB3TPPersistentDataManager;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (取引代金)<BR>
 * 取引代金の推移を表現する。
 */
public class WEB3TPTransactionAmount extends WEB3TPAssetValuation
{
    
    /**
     * (取引情報一覧<当日>)<BR>
     */
    private List todaysTransactions;
    
    /**
     * (取引情報一覧<確定>)<BR>
     */
    private List fixedTransactions;
    
    /**
     * (投資信託注文一覧)<BR>
     */
    private List mutualFundTransactions;

    /**
     * (出金注文一覧<余力計算結果詳細用>)<BR>
     */
    private List displayCashOutTransactions;
    
    /**
     * (株式対象トランザクションタイプ)<BR>
     */
    private static final FinTransactionType[] EQUITY_TRANSACTION_TYPES =
    {
            FinTransactionType.EQTYPE_EQUITY_BUY,
            FinTransactionType.EQTYPE_EQUITY_SELL,
            FinTransactionType.EQTYPE_CLOSE_MARGIN_LONG,
            FinTransactionType.EQTYPE_CLOSE_MARGIN_SHORT,
            FinTransactionType.EQTYPE_SWAP_MARGIN_LONG,
            FinTransactionType.EQTYPE_SWAP_MARGIN_SHORT,
    };
    
    /** 
     * ログ　@ユーティリティ　@
     */
    private static final WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3TPTransactionAmount.class);
    
    /**
     * @@roseuid 4104B2440114
     */
    public WEB3TPTransactionAmount()
    {
        todaysTransactions = new ArrayList();
        fixedTransactions = new ArrayList();
        mutualFundTransactions = new ArrayList();
        displayCashOutTransactions = new ArrayList();
    }
    
    /**
     * (create取引代金)<BR>
     * 取引代金を生成し、返却する。<BR>
     * <BR>
     * １）　@インスタンスを生成<BR>
     * ２）　@値を設定<BR>
     * 　@顧客情報＝引数.総資金.get顧客情報()<BR>
     * 　@計算条件＝引数.総資金.get計算条件()<BR>
     * 　@現注文内容＝引数.総資金.get現注文内容()<BR> 
     * ３）　@インスタンスを返却<BR>
     *
     * @@param l_valuation (総資金)
     * @@return WEB3TPTransactionAmount
     */
    public static WEB3TPTransactionAmount create(WEB3TPCashValuation l_valuation)
    {
        WEB3TPTransactionAmount l_instance = new WEB3TPTransactionAmount();
        l_instance.setAccountInfo(l_valuation.getAccountInfo());
        l_instance.setCalcCondition(l_valuation.getCalcCondition());
        l_instance.setNewOrderSpecs(l_valuation.getNewOrderSpecs());
        return l_instance;
    }
    
    /**
     * (get取引情報一覧<当日>)<BR>
     * 取引情報一覧<当日>を返す。<BR>
     * @@return List
     * @@roseuid 40EDF0F203C7
     */
    public List getTodaysTransactions()
    {
        return todaysTransactions;
    }
    
    /**
     * (get取引情報一覧<確定>)<BR>
     * 取引情報一覧<確定>を返す。<BR>
     * @@return List
     * @@roseuid 40F7AFEF02C8
     */
    public List getFixedTransactions()
    {
        return fixedTransactions;
    }
    
    /**
     * (get投資信託注文一覧)<BR>
     * 投資信託注文一覧を返す。<BR>
     * @@return List
     */
    public List getMutualFundTransactions()
    {
        return mutualFundTransactions;
    }
    
    /**
     * (get出金注文一覧<余力計算結果詳細用>)<BR>
     * 出金注文一覧<余力計算結果詳細用>を返す。<BR>
     * @@return List
     */
    public List getDisplayCashOutTransactions()
    {
        return displayCashOutTransactions;
    }
    
    /**
     * (calc当日未約定代金)<BR>
     * 指定日における未約定代金を集計し結果を返却する。<BR>
     * <BR>
     * １）　@取引情報一覧<当日>を取得する。<BR>
     * 　@取引情報一覧<当日>・・・取引評価. get取引情報一覧<当日>()<BR>
     * <BR>
     * ２）　@未約定代金を集計する。<BR>
     * 　@未約定代金(n) = Σ(*)取引情報.get未約定代金()<BR>
     * <BR>
     * (*)集計条件：<BR>
     * 取引情報一覧<当日>にある取引情報<BR>
     * 且つ　@取引情報.is変動期間中(n)=true<BR>
     * <BR>
     * ３）　@未約定代金(n)を返却する。<BR>
     * <BR>
     * @@param l_datDate - (指定日)
     * @@return double
     * @@roseuid 40E107E1004F
     */
    public double calcTodaysUnexecutedTotal(Date l_datDate)
    {
        double l_dblTotal = 0.0d;
        
        for (Iterator l_iter = todaysTransactions.iterator(); l_iter.hasNext(); )
        {
            WEB3TPTransactionReflector l_tranRef = (WEB3TPTransactionReflector) l_iter.
            next();
            if (l_tranRef.isDuringReflectTime(l_datDate))
            {
                l_dblTotal += l_tranRef.getUnexecutedAmount();
            }
        }
        
        //注意
        //買付可能額計算時に値をマイナスするので、＋-を反転させて返す。
        return (l_dblTotal == 0.0d) ? l_dblTotal : (l_dblTotal * -1); 
        
    }
    
    /**
     * (calc当日約定済代金)<BR>
     * <BR>
     * 指定日における約定済代金を集計し結果を返却する。<BR>
     * <BR>
     * １）　@取引情報一覧<当日>を取得する。<BR>
     * 　@取引情報一覧<当日>・・・取引評価. get取引情報一覧<当日>()<BR>
     * <BR>
     * ２）　@約定済代金を集計する。<BR>
     * 　@約定済代金(n) = Σ(*)取引情報.get約定済代金()<BR>
     * <BR>
     * (*)集計条件：<BR>
     * 取引情報一覧<当日>にある取引情報<BR>
     * 且つ　@取引情報.is変動期間中(n)=true<BR>
     * <BR>
     * ３）　@約定済代金(n)を返却する。<BR>
     * <BR>
     * @@param l_datDate - (指定日)
     * @@return double
     * @@roseuid 40E107EB0149
     */
    public double calcTodaysExecutedTotal(Date l_datDate)
    {
        double l_dblTotal = 0.0d;
        
        for (Iterator l_iter = todaysTransactions.iterator(); l_iter.hasNext(); )
        {
            WEB3TPTransactionReflector l_tranRef = (WEB3TPTransactionReflector) l_iter.
            next();
            if (l_tranRef.isDuringReflectTime(l_datDate))
            {
                l_dblTotal += l_tranRef.getExecutedAmount();
            }
        }
        
        //注意
        //買付可能額計算時に値をマイナスするので、＋-を反転させて返す。       
        return (l_dblTotal == 0.0d) ? l_dblTotal : (l_dblTotal * -1); 
    }
    
    /**
     * (calc出金額)<BR>
     * <BR>
     * 指定日における出金額を集計し結果を返却する。<BR>
     * <BR>
     * １）　@取引情報一覧<当日>を取得する。<BR>
     * 　@取引情報一覧<当日>・・・取引評価.get取引情報一覧<当日>()<BR>
     * <BR>
     * ２）　@約定済代金を集計する。<BR>
     * 　@約定済代金(n) = Σ(*)取引情報.get約定済代金()<BR>
     * <BR>
     * (*)集計条件：<BR>
     * 取引情報一覧<当日>にある取引情報<BR>
     * 且つ　@取引情報.getトランザクションタイプ=出金<BR>
     * 且つ　@取引情報.get受渡日=引数.指定日<BR>
     * <BR>
     * ３）　@約定済代金(n)を絶対値で返却する。<BR>
     * <BR>
     * @@param l_datDate - (指定日)
     * @@return double
     */
    public double calcCashOut(Date l_datDate)
    {
        double l_dblTotal = 0.0d;
        
        Date l_datDate0 = this.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0);        
        
        for (Iterator l_iter = displayCashOutTransactions.iterator(); l_iter.hasNext(); )
        {
            WEB3TPTransactionReflector l_tranRef = (WEB3TPTransactionReflector) l_iter.
            next();
            //トランザクションタイプ=出金
            if(FinTransactionType.DEBIT.equals(l_tranRef.getFinTransactionType()))
            {
                //T+5取得
                Date l_datDate5 = this.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5);
                
                //指定日と比較する受渡日
                Date l_datTmpDelivery = null;
                
                //受渡日がT+6以降の場合、T+5とみなす
                if(WEB3DateUtility.compareToDay(l_tranRef.getDeliveryDate(), l_datDate5) > 0)
                {
                    l_datTmpDelivery = l_datDate5;
                }
                else
                {
                    l_datTmpDelivery = l_tranRef.getDeliveryDate();
                }
                
                //指定日=受渡日
                if (WEB3DateUtility.compareToDay(l_datTmpDelivery, l_datDate) == 0)
                {
                    l_dblTotal += l_tranRef.getExecutedAmount(); 
                }
            }
        }
        return Math.abs(l_dblTotal); 
    }
    
    /**
     * (get入金要素集計)<BR>
     * 指定日における入金要素を集計し結果を返却する。<BR>
     * <BR>
     * get入金要素集計<当日>(指定日) ＋ get入金要素集計<確定>(指定日)　@を返却。<BR>
     * <BR>
     * @@param l_datDate - (指定日)
     * @@return double
     * @@roseuid 40F4E6360196
     */
    public double getPlusTotal(Date l_datDate)
    {
        return getTodaysPlusTotal(l_datDate) + getFixedPlusTotal(l_datDate);
    }
    
    /**
     * (get入金要素集計<当日>)<BR>
     * 指定日における入金要素<当日>を集計し結果を返却する。<BR>
     * <BR>
     * １）　@取引情報一覧<当日>を取得。<BR>
     * <BR>
     * ２）　@取引情報一覧<当日>のうち、取引情報.get受渡金額() > 0のものを集計。<BR>
     * <BR>
     * ３）　@集計結果を返却。<BR>
     * <BR>
     * @@param l_datDeliveryDate - (受渡日)
     * @@return double
     * @@roseuid 40F7A1150151
     */
    protected double getTodaysPlusTotal(Date l_datDeliveryDate)
    {
        double l_dblTotal = 0.0d;
        
        for (Iterator l_iter = todaysTransactions.iterator(); l_iter.hasNext(); )
        {
            WEB3TPTransactionReflector l_tranRef = (WEB3TPTransactionReflector) l_iter.
            next();
            
            //保証金から預り金への取引情報は計上しない。
            if ((WEB3DateUtility.compareToDay(l_tranRef.getDeliveryDate(), l_datDeliveryDate) == 0) && (l_tranRef.getAmount() > 0) &&
                   (!(FinTransactionType.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_tranRef.getFinTransactionType()) ||  
                      FinTransactionType.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE.equals(l_tranRef.getFinTransactionType()))))
            {
                l_dblTotal += l_tranRef.getAmount();
            }
        }
        return l_dblTotal;
    }
    
    /**
     * (get入金要素集計<確定>)<BR>
     * 指定日における入金要素<確定>を集計し結果を返却する。<BR>
     * <BR>
     * １）　@取引情報一覧<確定>を取得。<BR>
     * <BR>
     * ２）　@取引情報一覧<確定>のうち、取引情報.get受渡金額() > 0のものを集計。<BR>
     * <BR>
     * ３）　@集計結果を返却。<BR>
     * <BR>
     * @@param l_datDeliveryDate - (受渡日)
     * @@return double
     * @@roseuid 40F7A13C0141
     */
    public double getFixedPlusTotal(Date l_datDeliveryDate)
    {
        double l_dblTotal = 0.0d;
        
        for (Iterator l_iter = fixedTransactions.iterator(); l_iter.hasNext(); )
        {
            WEB3TPTransactionReflector l_tranRef = (WEB3TPTransactionReflector) l_iter.
            next();
            
            //保証金から預り金への取引情報は計上しない。
            if ((WEB3DateUtility.compareToDay(l_tranRef.getDeliveryDate(), l_datDeliveryDate) == 0) && (l_tranRef.getAmount() > 0) &&
                    (!(FinTransactionType.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_tranRef.getFinTransactionType()) ||  
                            FinTransactionType.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE.equals(l_tranRef.getFinTransactionType()))))
            {
                l_dblTotal += l_tranRef.getAmount();
            }
        }
        return l_dblTotal;
    }
    
    /**
     * (get出金要素集計)<BR>
     * 指定日における出金要素を集計し結果を返却する。<BR>
     * <BR>
     * get出金要素集計<当日>(指定日) ＋ get出金要素集計<確定>(指定日)　@を返却。<BR>
     * @@param l_datDate - (指定日)
     * @@return double
     * @@roseuid 40F4E6480138
     */
    public double getMinusTotal(Date l_datDate)
    {
        return getTodaysMinusTotal(l_datDate) + getFixedMinusTotal(l_datDate);
    }
    
    /**
     * (get出金要素集計<当日>)<BR>
     * 指定日における出金要素<当日>を集計し結果を返却する。<BR>
     * <BR>
     * １）　@取引情報一覧<当日>を取得。<BR>
     * <BR>
     * ２）　@取引情報一覧<当日>のうち、取引情報.get受渡金額() ＜ 0のものを集計。<BR>
     * <BR>
     * ３）　@集計結果を返却。<BR>
     * <BR>
     * @@param l_datDeliveryDate - (受渡日)
     * @@return double
     * @@roseuid 40F7A14603A2
     */
    protected double getTodaysMinusTotal(Date l_datDeliveryDate)
    {
        double l_dblTotal = 0.0d;
        
        for (Iterator l_iter = todaysTransactions.iterator(); l_iter.hasNext(); )
        {
            WEB3TPTransactionReflector l_tranRef = (WEB3TPTransactionReflector) l_iter.
            next();
            //預り金から信用保証金への取引情報は計上しない。
            if ((WEB3DateUtility.compareToDay(l_tranRef.getDeliveryDate(), l_datDeliveryDate) == 0) && (l_tranRef.getAmount() < 0) &&
                    (!(FinTransactionType.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_tranRef.getFinTransactionType()) ||  
                            FinTransactionType.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE.equals(l_tranRef.getFinTransactionType()))))
            {
                l_dblTotal += l_tranRef.getAmount();
            }
        }
        return Math.abs(l_dblTotal);
    }
    
    /**
     * (get出金要素集計<確定>)<BR>
     * 指定日における出金要素<確定>を集計し結果を返却する。<BR>
     * <BR>
     * １）　@取引情報一覧<確定>を取得。<BR>
     * <BR>
     * ２）　@取引情報一覧<確定>のうち、取引情報.get受渡金額()　@＜ 0のものを集計。<BR>
     * <BR>
     * ３）　@集計結果を返却。<BR>
     * @@param l_datDeliveryDate - (受渡日)
     * @@roseuid 40F7A15503E1
     */
    public double getFixedMinusTotal(Date l_datDeliveryDate)
    {
        double l_dblTotal = 0.0d;
        
        for (Iterator l_iter = fixedTransactions.iterator(); l_iter.hasNext(); )
        {
            WEB3TPTransactionReflector l_tranRef = (WEB3TPTransactionReflector) l_iter.
            next();
            //預り金から信用保証金への取引情報は計上しない。
            if ((WEB3DateUtility.compareToDay(l_tranRef.getDeliveryDate(), l_datDeliveryDate) == 0) && (l_tranRef.getAmount() < 0) &&
                    (!(FinTransactionType.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_tranRef.getFinTransactionType()) ||  
                            FinTransactionType.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE.equals(l_tranRef.getFinTransactionType()))))
            {
                l_dblTotal += l_tranRef.getAmount();
            }
        }
        return Math.abs(l_dblTotal);
    }
    
    /**
     * (get株式取引情報)<BR>
     * 指定日が受渡日である株式取引情報の配列を返却する。<BR>
     * <BR>
     * １）　@取引情報一覧<当日>、取引情報一覧<確定>から<BR>
     *　@
     * 以下の条件に合う取引情報が存在する場合ローカル変数（ArrayList）に保存する<BR>
     * <BR>
     *     ・取引情報.get銘柄タイプ()＝株 OR 外株<BR>
     *     ・取引情報.getトランザクションタイプ()＝トランザクションタイプ (*)<BR>
     *     ・取引情報.get受渡日＝引数.受渡日<BR>
     * <BR>
     *     (*)引数の配列要素<BR>
     *     トランザクションタイプ＝nullの場合<BR>
     *     ｛70：現物買取引　@80：現物売取引<BR>
     *     110：買建返済取引（売返済）　@120：売建返済取引（買返済）<BR>
     *     130：現引取引　@140：現渡取引｝<BR>
     *     を条件とする。<BR>
     * <BR>
     * ２）　@抽出し保存した取引情報を配列として返却。<BR>
     * <BR>
     * @@param transactionType - (トランザクションタイプ)
     * @@param l_datDate - (受渡日)
     * @@return webbroker3.tradingpower.updtpower.cash.WEB3TPTransactionReflector[]
     * @@roseuid 40DBCFC3029F
     */
    public WEB3TPTransactionReflector[] getEquityTransactions(FinTransactionType[]
                                                                                 l_transactionTypes, Date l_datDeliveryDate)
    {
        
        if (l_transactionTypes == null)
        {
            l_transactionTypes = EQUITY_TRANSACTION_TYPES;
        }
        
        List l_allTransactions = new ArrayList();
        l_allTransactions.addAll(todaysTransactions);
        l_allTransactions.addAll(fixedTransactions);
        
        WEB3TPTransactionReflector[] l_transactionReflectorsEquity =
            searchTransactions(
                l_allTransactions, ProductTypeEnum.EQUITY,
                l_transactionTypes, l_datDeliveryDate);

        WEB3TPTransactionReflector[] l_transactionReflectorsFeq =
            searchTransactions(
                l_allTransactions, ProductTypeEnum.FOREIGN_EQUITY,
                l_transactionTypes, l_datDeliveryDate);

        List l_lisTransactionReflectors = new ArrayList();
        int l_intEquityLength = 0;
        if (l_transactionReflectorsEquity != null)
        {
            l_intEquityLength = l_transactionReflectorsEquity.length;
        }
        for (int i = 0; i < l_intEquityLength; i++)
        {
            l_lisTransactionReflectors.add(l_transactionReflectorsEquity[i]);
        }

        int l_intFeqLength = 0;
        if (l_transactionReflectorsFeq != null)
        {
            l_intFeqLength = l_transactionReflectorsFeq.length;
        }
        for (int i = 0; i < l_intFeqLength; i++)
        {
            l_lisTransactionReflectors.add(l_transactionReflectorsFeq[i]);
        }

        WEB3TPTransactionReflector[] l_transactionReflectors =
            new WEB3TPTransactionReflector[l_lisTransactionReflectors.size()];
        l_lisTransactionReflectors.toArray(l_transactionReflectors);
        return l_transactionReflectors;
    }
    
    /**
     * (get株式取引情報件数<当日>)<BR>
     * 指定日が受渡日である当日以降株式取引情報件数を返却する。<BR>
     * ただし現物売の未約定を含まない件数とする。<BR>
     * <BR>
     * １）　@取引情報一覧<当日>から、<BR>
     *     以下の条件で取引情報の件数をカウントする。<BR>
     *     ・取引情報.get銘柄タイプ＝株<BR>
     *     ・取引情報.getトランザクションタイプ()　@＝　@トランザクションタイプ (*1)<BR>
     *     ・取引情報.is変動反映期間中(指定日)<BR>
     * <BR>
     *     (*1)引数の配列要素<BR>
     *     トランザクションタイプ＝nullの場合<BR>
     *     ｛70：現物買取引　@80：現物売取引<BR>
     *     110：買建返済取引（売返済）　@120：売建返済取引（買返済）<BR>
     *     130：現引取引　@140：現渡取引｝<BR>
     *     を条件とする。<BR>
     * <BR>
     * ２）　@カウントした件数を返却<BR>
     * @@param l_transactionTypes トランザクションタイプ
     * @@param l_datDeliveryDate - (受渡日)
     * @@return int
     * @@roseuid 40DBC71A0349
     */
    public int getTodaysEquityTransactionsCount(FinTransactionType[] l_transactionTypes,
            Date l_datDeliveryDate)
    {
        if (l_transactionTypes == null)
        {
            l_transactionTypes = EQUITY_TRANSACTION_TYPES;
        }
        
        int l_intCount = 0;
        for (Iterator l_iter = todaysTransactions.iterator(); l_iter.hasNext(); )
        {
            WEB3TPTransactionReflector l_tranRef = (WEB3TPTransactionReflector) l_iter.
            next();          
            
            if ((WEB3DateUtility.compareToDay(l_tranRef.getDeliveryDate(), l_datDeliveryDate) == 0) &&
                    (ProductTypeEnum.EQUITY.equals(l_tranRef.getProductType())))
            {
                for (int j = 0; j < l_transactionTypes.length; j++)
                {
                    FinTransactionType l_tranType = l_tranRef.getFinTransactionType();
                    if (l_transactionTypes[j].equals(l_tranType))
                    {
                        //現物売りの場合は未約定が含まれているため約定しているものだけをカウントする
                        if (FinTransactionType.EQTYPE_EQUITY_SELL.equals(l_tranType))
                        {
                            if (l_tranRef.getExecutedQuantity() > 0)
                            {
                                l_intCount++;
                            }
                        }
                        else
                        {
                            l_intCount++;
                        }
                    }
                }
            }
        }
        return l_intCount;
        
    }
    
    /**
     * (getミニ株取引情報件数<当日>)<BR>
     * 指定日が受渡日である当日以降株式取引情報件数を返却する。<BR>
     * <BR>
     * １）　@取引情報一覧<当日>から、<BR>
     *     以下の条件を取引情報の件数をカウントする。<BR>
     *     ・取引情報.get銘柄タイプ()＝株<BR>
     *     ・取引情報.getトランザクションタイプ()＝ミニ株<BR>
     *     ・取引情報.is変動反映期間中(指定日)<BR>
     * <BR>
     * ２）　@カウントした件数を返却<BR>
     * @@param l_datDate - (指定日)
     * @@return int
     * @@roseuid 40DBC6D202CD
     */
    public int getTodaysMiniStockTransactionsCount(Date l_datDate)
    {
        return searchTransactions(todaysTransactions, ProductTypeEnum.EQUITY,
                new FinTransactionType[]
                                       {FinTransactionType.EQTYPE_MINI_STOCK_BUY, FinTransactionType.EQTYPE_MINI_STOCK_SELL,}
        , l_datDate).length;
    }
    
    /**
     * (getオプション取引情報件数<当日>)<BR>
     * 指定日が受渡日である当日以降株式取引情報件数を返却する。<BR>
     * <BR>
     * １）　@取引情報一覧<当日>から、<BR>
     *     以下の条件で取引情報の件数をカウントする。<BR>
     *     ・取引情報.get銘柄タイプ＝オプション<BR>
     *     ・取引情報.is変動反映期間中(指定日)<BR>
     * <BR>
     * ２）　@カウントした件数を返却<BR>
     * <BR>
     * @@param l_datDate - (指定日)
     * @@return int
     * @@roseuid 40DBC49600DC
     */
    public int getTodaysOptionTransactionsCount(Date l_datDate)
    {
        return searchTransactions(todaysTransactions, ProductTypeEnum.IFO, null,
                l_datDate).length;
    }
    
    /**
     * (get投信取引情報件数<当日>)<BR>
     * 指定日が受渡日である当日以降株式取引情報件数を返却する。<BR>
     * <BR>
     * １）　@取引情報一覧<当日>から、<BR>
     *     以下の条件を取引情報の件数をカウントする。<BR>
     *     ・取引情報.get銘柄タイプ＝投信<BR>
     *     ・取引情報.is変動反映期間中(指定日)<BR>
     * <BR>
     * ２）　@カウントした件数を返却<BR>
     * @@param l_datDate - (指定日)
     * @@return int
     * @@roseuid 40DBCD0B0301
     */
    public int getTodaysMutualFundTransactionsCount(Date l_datDate)
    {
        return searchTransactions(todaysTransactions, ProductTypeEnum.MUTUAL_FUND, null,
                l_datDate).length;
    }
    
    /**
     * (get外株取引情報件数<当日>)<BR>
     * 指定日が受渡日である当日以降株式取引情報件数を返却する。<BR>
     * ただし外株売の未約定を含まない件数とする。<BR>
     * <BR>
     * １）　@取引情報一覧<当日>から、<BR>
     *     以下の条件を取引情報の件数をカウントする。<BR>
     *     ・取引情報.get銘柄タイプ＝外株<BR>
     *     ・取引情報.is変動反映期間中(指定日)<BR>
     *     ・（(取引情報.トランザクションタイプ=外株売 且つ<BR>
     *     取引情報. get約定済数量() > 0 )<BR>
     *     或は<BR>
     *     (取引情報.トランザクションタイプ=外株買)）<BR>
     * <BR>
     * ２）　@カウントした件数を返却<BR>
     * <
     * @@param l_datDate - (指定日)
     * @@return int
     * @@roseuid 40DBCDF50179
     */
    public int getTodaysForeignEquityTransactionsCount(Date l_datDate)
    {
        WEB3TPTransactionReflector[] l_transactionReflector =
            searchTransactions(todaysTransactions, ProductTypeEnum.FOREIGN_EQUITY, null,
                l_datDate);
        int l_intCount = 0;
        for (int i = 0;i < l_transactionReflector.length; i++)
        {
            WEB3TPTransactionReflector l_tranRef = l_transactionReflector[i];

            if ((WEB3DateUtility.compareToDay(l_tranRef.getDeliveryDate(), l_datDate) == 0)
                && (ProductTypeEnum.FOREIGN_EQUITY.equals(l_tranRef.getProductType())))
            {

                FinTransactionType l_tranType = l_tranRef.getFinTransactionType();
                //取引情報.トランザクションタイプ=外株売
                if (FinTransactionType.EQTYPE_FEQ_SELL.equals(l_tranType))
                {
                    //取引情報. get約定済数量() > 0
                    if (l_tranRef.getExecutedQuantity() > 0)
                    {
                        l_intCount++;
                    }
                }
                else
                {
                    l_intCount++;
                }
            }
        }
        return l_intCount;
    }
    
    /**
     *　@引数.リストに含まれる取引情報のうち、以下のように条件が合うかを判断し<BR>
     *　@マッチした取引情報を配列で返す。<BR>
     *　@1件もマッチしない場合0個の配列を返す。<BR>
     *　@１）　@引数.受渡日と受渡日が一致<BR>
     *　@２）　@引数で与えられた銘柄タイプが一致<BR>
     *　@３）　@（引数で指定されている場合、）トランザクションタイプ（配列の要素）が一致<BR>
     *
     * @@param l_transactions 取引情報の集約
     * @@param l_productType　@銘柄タイプ
     * @@param l_transactionTypes　@トランザクションタイプ
     * @@param l_datDeliveryDate　@(受渡日)
     * @@return WEB3TPTransactionReflector[]
     */
    protected WEB3TPTransactionReflector[] searchTransactions(
            List l_transactions,
            ProductTypeEnum l_productType,
            FinTransactionType[] l_transactionTypes,
            Date l_datDeliveryDate)
    {
        List l_targetTrans = new ArrayList();
        
        //受渡日T+6以降対応
        Date l_datT5 = getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5);
        
        for (Iterator l_iter = l_transactions.iterator(); l_iter.hasNext(); )
        {
            WEB3TPTransactionReflector l_tranRef = (WEB3TPTransactionReflector) l_iter.
            next();
            
            Date l_tranDeliveryDate = l_tranRef.getDeliveryDate();            
            
            //受渡日T+6以降対応
            if(WEB3DateUtility.compareToDay(l_datT5, l_tranRef.getDeliveryDate()) < 0)
            {
                l_tranDeliveryDate = l_datT5;
            }
            
            if ((WEB3DateUtility.compareToDay(l_tranDeliveryDate, l_datDeliveryDate) == 0) &&
                    (l_productType.equals(l_tranRef.getProductType())))
            {
                //指定なければKeyとしてチェックしない。
                if (l_transactionTypes == null)
                {
                    l_targetTrans.add(l_tranRef);
                }
                //指定あればKeyとしてチェックする。
                else
                {
                    for (int j = 0; j < l_transactionTypes.length; j++)
                    {
                        if (l_transactionTypes[j].equals(l_tranRef.getFinTransactionType()))
                        {
                            l_targetTrans.add(l_tranRef);
                        }
                    }
                }
            }
        }
        
        return (WEB3TPTransactionReflector[]) l_targetTrans.toArray(new
                WEB3TPTransactionReflector[l_targetTrans.size()]);
        
    }
    
    /**
     * (do取引情報ロード)<BR>
     * do確定取引情報ロード()<BR>
     * do当日以降取引情報ロード()<BR>
     * を呼ぶ。<BR>
     * @@roseuid 40DAC4190180
     */
    public void load()
    {
        mutualFundTransactions = 
            WEB3TPPersistentDataManager.getInstance().getMutualFundOrders(this);
        this.loadTodaysTransactions();
        this.loadFixedTransactions();
    }
    
    /**
     * (do取引情報ロード<確定>)<BR>
     * 確定取引明細を取引情報オブジェクトにロードする。<BR>
     * <BR>
     * １）　@確定取引明細を以下のメソッドを呼び取得する。<BR>
     * <BR>
     * 　@資金関連データソースアクセス管理.get確定取引明細()<BR>
     * <BR>
     * <BR>
     * 以降の処理は、検索結果の各行に対して実施する。<BR>
     * <BR>
     * ２）　@取引情報を生成する。<BR>
     * <BR>
     * －設定項目<BR>
     * ・銘柄タイプ        ＝  確定取引明細.銘柄タイプ<BR>
     * ・銘柄ID            ＝  確定取引明細.銘柄ID<BR>
     * ・トランザクションタイプ        ＝  確定取引明細.トランザクションタイプ<BR>
     * ・トランザクション発生日    ＝  確定取引明細.トランザクション発生日<BR>
     * ・未約定数量        ＝  無指定（0）<BR>
     * ・未約定代金        ＝  無指定（0）<BR>
     * ・約定済数量        ＝  確定取引明細.約定数量<BR>
     * ・約定済代金        ＝  確定取引明細.受渡金額<BR>
     * ・変動反映開始日、変動反映終了日設定：calc変動反映日（確定取引明細.受渡日）<BR>
     * <BR>
     * ３）　@取引情報一覧<確定>に生成した取引情報を追加する。<BR>
     * <BR>
     * @@roseuid 40D9700E0323
     */
    protected void loadFixedTransactions()
    {
        List l_fixedFinTranRows = WEB3TPPersistentDataManager.getInstance().getFixedFinTransactions(
                this);
        
        for (Iterator l_iter = l_fixedFinTranRows.iterator(); l_iter.hasNext(); )
        {
            WEB3TPTransactionReflector l_tranRef = createWEB3TPTransactionReflector( (
                    FixedFinTransactionRow) l_iter.next());
            addFixedTransaction(l_tranRef);
            
        }
    }
    
    /**
     * (do取引情報ロード<当日>)<BR>
     * <BR>
     * 当日取引情報関連データをロードする。<BR>
     * <BR>
     * ※シーケンス図「(取引代金)do取引情報ロード<当日>」参照<BR>
     * <BR>
     */
    protected void loadTodaysTransactions()
    {
        loadTodaysCashBasedEquityTransactions();
        loadTodaysCloseMarginTransactions();
        loadTodaysSwapMarginTransactions();
        loadTodaysMiniStorckTransactions();
        loadTodaysOptionTransactions();
        loadMutualFundTransactions();
        loadTodaysGPTransactions();
        loadTodaysForeignEquityTransactions();
        loadTodaysCashInOutTransactions();
        loadTranferCashTransactions();
        loadBondTransactions();
        loadTodaysIpoTransactions();
    }
    
    /**
     * (do現物株取引情報ロード<当日>)<BR>
     * 株式注文単位<現物株>を取引情報オブジェクトにロードする。<BR>
     * <BR>
     * １）　@株式注文単位<現物株>を以下のメソッドを呼び取得する。<BR>
     *     資金関連データソースアクセス管理.get当日以降現物株注文()<BR>
     * <BR>
     * 以降の処理は、loadTodaysEqtypeTransactions()を呼んで<BR>
     * 検索結果の各行に対して実施する。<BR>
     * <BR>
     * ２）　@取引情報を生成する。<BR>
     * <BR>
     * －設定項目<BR>
     * ・銘柄タイプ        ＝  株式注文.銘柄タイプ<BR>
     * ・銘柄ID            ＝  株式注文.銘柄ID<BR>
     * ・トランザクションタイプ        ＝ 株式注文.注文種別.toFinTransactionType()<BR>
     * ・トランザクション発生日    ＝  株式注文.発注日<BR>
     * ・未約定数量        ＝  株式注文.数量　@－　@株式注文.約定済数量<BR>
     * ・未約定代金        ＝　@株式注文.概算受渡代金<BR>
     * ・約定済数量        ＝  株式注文.約定済数量<BR>
     * ・約定済代金        ＝　@株式トランザクション.受渡代金(*)<BR>
     * ・変動反映開始日、変動反映終了日設定：calc変動反映日（株式注文.受渡日）<BR>
     * ・変動反映開始日        ＝  引数.受渡日<BR>
     * ・変動反映終了日        ＝  引数.受渡日<BR>
     * <BR>
     * (*)
     * 株式注文が約定している場合、株式トランザクションを以下のメソッドを呼び取得する。
     * <BR>
     *     資金関連データソースアクセス管理.get株式トランザクション()<BR>
     * <BR>
     * ３）　@取引情報一覧<当日>に生成した取引情報を追加する。<BR>
     * <BR>
     * @@roseuid 40BFDF2602CE
     */
    protected void loadTodaysCashBasedEquityTransactions()
    {
        List l_rows = WEB3TPPersistentDataManager.getInstance().getTodaysCashBasedEquityOrders(this);
        loadTodaysEqtypeTransactions(l_rows);
    }
    
    /**
     * (do信用返済取引情報ロード<当日>)<BR>
     * 株式注文単位<信用返済>を取引情報オブジェクトにロードする。<BR>
     * <BR>
     * １）　@株式注文単位<信用返済>を以下のメソッドを呼び取得する。<BR>
     * <BR>
     *  資金関連データソースアクセス管理.get当日以降信用返済注文()<BR>
     * <BR>
     * 以降の処理は、loadTodaysEqtypeTransactions()を呼んで<BR>
     * 検索結果の各行に対して実施する。<BR>
     * <BR>
     * ２）　@取引情報を生成する。<BR>
     * 　@do当日以降現物株取引情報ロード()の２）参照。<BR>
     * <BR>
     * ３）　@取引情報一覧<当日>に生成した取引情報を追加する。<BR>
     * <BR>
     * @@roseuid 40DF8D390384
     */
    protected void loadTodaysCloseMarginTransactions()
    {
        List l_rows = WEB3TPPersistentDataManager.getInstance().getTodaysCloseMarginOrders(this);
        loadTodaysEqtypeTransactions(l_rows);
    }
    
    /**
     * (do現引現渡取引情報ロード<当日>)<BR>
     * 株式注文単位<現引現渡>を取引情報オブジェクトにロードする。<BR>
     * <BR>
     * １）　@株式注文単位<現引現渡>を以下のメソッドを呼び取得する。<BR>
     * <BR>
     *  資金関連データソースアクセス管理.get当日以降現引現渡注文()<BR>
     * <BR>
     * 以降の処理は、loadTodaysEqtypeTransactions()を呼んで<BR>
     * 検索結果の各行に対して実施する。<BR>
     * <BR>
     * ２）　@取引情報を生成する。<BR>
     * 　@do当日以降現物株取引情報ロード()の２）参照。<BR>
     * <BR>
     * ３）　@取引情報一覧<当日>に生成した取引情報を追加する。<BR>
     * <BR>
     * @@roseuid 40DF8D4C0375
     */
    protected void loadTodaysSwapMarginTransactions()
    {
        List l_rows = WEB3TPPersistentDataManager.getInstance().getTodaysSwapMarginOrders(this);
        loadTodaysEqtypeTransactions(l_rows);
    }
    
    /**
     * (doミニ株取引情報ロード<当日>)<BR>
     * ミニ株注文を取引情報オブジェクトにロードする。<BR>
     * <BR>
     * １）　@ミニ株注文を以下のメソッドを呼び取得する。<BR>
     * <BR>
     *     資金関連データソースアクセス管理.get当日以降ミニ株注文()<BR>
     * <BR>
     * 以降の処理は、loadTodaysEqtypeTransactions()を呼んで<BR>
     * 検索結果の各行に対して実施する。<BR>
     * <BR>
     * ２）　@取引情報を生成する。<BR>
     * 　@do当日以降現物株取引情報ロード()の２）参照。<BR>
     * <BR>
     * ３）　@取引情報一覧<当日>に生成した取引情報を追加する。<BR>
     * <BR>
     * @@roseuid 40BFDF5703C8
     */
    protected void loadTodaysMiniStorckTransactions()
    {
        List l_rows = WEB3TPPersistentDataManager.getInstance().getTodaysMiniStockOrders(this);
        loadTodaysEqtypeTransactions(l_rows);
        
    }
    
    /**
     * (do株式取引情報ロード)<BR>
     * 株式注文(EqtypeOrderUnit)を取引情報にロードする。<BR>
     *
     * @@param l_eqtypeOrderUnitRows (株式注文単位のリスト)
     */
    protected void loadTodaysEqtypeTransactions(List l_rows)
    {
        if (l_rows != null)
        {
            for (Iterator l_iter = l_rows.iterator(); l_iter.hasNext(); )
            {
                
                WEB3TPTransactionReflector[] l_tranRefs = createWEB3TPTransactionReflector( (
                        EqtypeOrderUnitRow) l_iter.next());               
                
                for(int i = 0; i < l_tranRefs.length; i++)
                {    
                    log.debug("追加したTodaysTransaction=" + l_tranRefs[i]);
                    addTodaysTransaction(l_tranRefs[i]);
                }
                
            }
        }
    }
    
    /**
     * (doオプション取引情報ロード<当日>)<BR>
     * オプション注文を取引情報オブジェクトにロードする。<BR>
     * <BR>
     * １）　@オプション注文を以下のメソッドを呼び取得する。<BR>
     * <BR>
     *     資金関連データソースアクセス管理.get当日以降オプション注文()<BR>
     * <BR>
     * 以降の処理は、検索結果の各行に対して実施する。<BR>
     * <BR>
     * ２）　@取引情報を生成する。<BR>
     * <BR>
     * －設定項目<BR>
     * ・銘柄タイプ        ＝  オプション注文.銘柄タイプ<BR>
     * ・銘柄ID            ＝  オプション注文.銘柄ID<BR>
     * ・トランザクションタイプ        ＝ オプション注文.注文種別.toFinTransactionType()<BR>
     * ・トランザクション発生日    ＝  オプション注文.発注日<BR>
     * ・未約定数量        ＝  オプション注文.数量　@－　@オプション注文.約定済数量<BR>
     * ・約定済数量        ＝  オプション注文.約定済数量<BR>
     * ・未約定代金        ＝オプション注文.概算受渡代金<BR>
     * ・約定代金          ＝オプショントランザクション.受渡代金<BR>
     * ・変動反映開始日、変動反映終了日設定：calc変動反映日（オプション注文.受渡日）<BR>
     * ・変動反映開始日        ＝  引数.受渡日<BR>
     * ・変動反映終了日        ＝  引数.受渡日<BR>
     * <BR>
     * (*)
     * オプション注文が約定している場合、オプショントランザクションを以下のメソッドを呼
     * び取得する。<BR>
     *     資金関連データソースアクセス管理.getオプショントランザクション()<BR>
     * <BR>
     * ３）　@取引情報一覧<当日>に生成した取引情報を追加する。<BR>
     * <BR>
     * @@roseuid 40BFDF55028F
     */
    protected void loadTodaysOptionTransactions()
    {
        List l_rows = WEB3TPPersistentDataManager.getInstance().getTodaysOptionOrders(this);
        
        if (l_rows != null)
        {
            for (Iterator l_iter = l_rows.iterator(); l_iter.hasNext(); )
            {
                WEB3TPTransactionReflector[] l_tranRefs = createWEB3TPTransactionReflector( (
                        IfoOrderUnitRow) l_iter.next());
                
                for(int i = 0; i < l_tranRefs.length; i++)
                {    
                    addTodaysTransaction(l_tranRefs[i]);
                }                
            }
        }
        
    }
    
    /**
     * (do投信取引情報ロード)<BR>
     * 投信注文を取引情報オブジェクトにロードする。<BR>
     * <BR>
     * １）　@投信注文を以下のメソッドを呼び取得する。<BR>
     * <BR>
     *     資金関連データソースアクセス管理.get投資信託注文<取引代金><BR>
     * <BR>
     * 以降の処理は、検索結果の各行に対して実施する。<BR>
     * <BR>
     * ２）　@取引情報を生成する。<BR>
     * <BR>
     * <BR>
     * ３）　@取引情報一覧<当日>に生成した取引情報を追加する。<BR>
     * <BR>
     * @@roseuid 40BFDF5601B4
     */
    protected void loadMutualFundTransactions()
    {
        List l_rows = WEB3TPPersistentDataManager.getInstance().getMutualFundOrdersAmount(this.mutualFundTransactions, this);
        
        if (l_rows != null)
        {
            for (Iterator l_iter = l_rows.iterator(); l_iter.hasNext(); )
            {
                WEB3TPTransactionReflector l_tranRef = createWEB3TPTransactionReflector( (
                        MutualFundOrderUnitRow) l_iter.next());
                addTodaysTransaction(l_tranRef);
            }
        }
        
    }
    
    /**
     * (do入出金取引情報ロード<当日>)<BR>
     * 入出金注文を取引情報オブジェクトにロードする。<BR>
     * <BR>
     * １）　@入出金注文を以下のメソッドを呼び取得する。<BR>
     * <BR>
     *     資金関連データソースアクセス管理.get当日以降入出金注文()<BR>
     * <BR>
     * 以降の処理は、検索結果の各行に対して実施する。<BR>
     * <BR>
     * ２）　@取引情報を生成する。<BR>
     * <BR>
     * －設定項目<BR>
     * ・銘柄タイプ        ＝  入出金注文.銘柄タイプ<BR>
     * ・銘柄ID            ＝  入出金注文.銘柄ID<BR>
     * ・トランザクションタイプ        ＝ 入出金注文.注文種別.toFinTransactionType()<BR>
     * ・トランザクション発生日    ＝  入出金注文.発注日<BR>
     * ・未約定数量        ＝  ０<BR>
     * ・約定済数量        ＝  入出金注文.数量<BR>
     * ・未約定代金        ＝  ０<BR>
     * ・約定済代金        ＝  入出金注文.数量<BR>
     * ・変動反映開始日、変動反映終了日設定：calc変動反映日（入出金注文.受渡日）<BR>
     * ・変動反映開始日        ＝  引数.受渡日<BR>
     * ・変動反映終了日        ＝  引数.受渡日<BR>
     * <BR>
     * ３）　@取引情報一覧<当日>に生成した取引情報を追加する。<BR>
     * <BR>
     * @@roseuid 40CEE0430055
     */
    protected void loadTodaysCashInOutTransactions()
    {
        List l_rows = WEB3TPPersistentDataManager.getInstance().getTodaysCashInOutOrders(this);
        Date l_datT5 = this.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5);
        
        //計算条件よりサービス利用拘束かどうか取得
        //値が設定されていない場合は拘束とみなす
        String l_strVal = this.getCalcCondition().getInstBranCalcCondition(WEB3TPCalcCondition.SERVICE_CHARGE_RESTRAINT);       
        boolean isServiceChargeCheckBranch =
            (WEB3TPServiceChargeRestraintDef.EXCEPT.equals(l_strVal)) ?
                    false : true;
        
        log.debug("** サービス利用拘束有無=" + isServiceChargeCheckBranch);	                    
        
        if (l_rows != null)
        {
            for (Iterator l_iter = l_rows.iterator(); l_iter.hasNext(); )
            {    
                AioOrderUnitRow l_row = (AioOrderUnitRow)l_iter.next();
                
                //サービス利用による出金、かつ、受渡日がT+5より先であった場合は
                //サービス利用拘束する部店の場合は                
                //当日約定代金として計上しない。
                //(サービス利用拘束しない部店の場合、当日約定済代金に計上)
                
                if(!((OrderTypeEnum.CASH_OUT.equals(l_row.getOrderType())) &&
                        (l_row.getPaymentApplicationDiv() != null) && 
                        (! WEB3TPPaymentApplicationDivDef.MF_SELL_WITH_CASH_OUT.equals(l_row.getPaymentApplicationDiv())) && 
                        (WEB3DateUtility.compareToDay(l_row.getDeliveryDate(), l_datT5) > 0) &&
                        isServiceChargeCheckBranch))
                {
                    WEB3TPTransactionReflector l_tranRef = createWEB3TPTransactionReflector(l_row);
                    addTodaysTransaction(l_tranRef);	                
                    log.debug("追加した入出金トランザクション：" + l_tranRef + " ===>aio_order_unit_id=" + l_row.getOrderUnitId());                                        
                }                
                
                //有料情報以外の出金注文は
                //calc出金額(calcCashOut)の対象注文とする。（余力計算結果詳細に反映）        
                if(OrderTypeEnum.CASH_OUT.equals(l_row.getOrderType()) &&
                        ((l_row.getPaymentApplicationDiv() == null) ||
                        (WEB3TPPaymentApplicationDivDef.MF_SELL_WITH_CASH_OUT.equals(l_row.getPaymentApplicationDiv()))))
                {
                    WEB3TPTransactionReflector l_cashOutTranRef = createWEB3TPTransactionReflector(l_row);
                    addDisplayCashOutTransaction(l_cashOutTranRef);	                
                    log.debug("追加した余力計算結果詳細用出金トランザクション：" + l_cashOutTranRef + " ===>aio_order_unit_id=" + l_row.getOrderUnitId());                                                            
                }                
            }
        }
        
        /*                if((OrderTypeEnum.CASH_OUT.equals(l_row.getOrderType())) &&
         (l_row.getPaymentApplicationDiv() != null) && 
         (WEB3DateUtility.compareToDay(l_row.getDeliveryDate(), l_datT5) > 0))
         {
         if(!isServiceChargeCheckBranch)
         {
         WEB3TPTransactionReflector l_tranRef = createWEB3TPTransactionReflector(l_row);
         addTodaysTransaction(l_tranRef);
         log.debug("** サービス利用拘束無し::受渡日がT+5より先の有料情報 **");
         log.debug("追加した入出金トランザクション：" + l_tranRef + " 入出金注文単位=" + l_row);
         
         }
         
         //サービス利用拘束する部店の場合は当日約定済代金に計上しない。
          } 
          
          //上記以外の入出金注文
           else
           {
           WEB3TPTransactionReflector l_tranRef = createWEB3TPTransactionReflector(l_row);
           addTodaysTransaction(l_tranRef);
           log.debug("追加した入出金トランザクション：" + l_tranRef + " 入出金注文単位=" + l_row);
           
           }                
           }
           */        
        
    }        
    
    /**
     * (do振替取引情報ロード<当日>)<BR> 
     *<BR>
     * 振替取引情報関連データをロードする。<BR> 
     * <BR>
     * ※シーケンス図「(取引代金)do振替取引情報ロード<当日>」参照<BR> 
     */
    protected void loadTranferCashTransactions()
    {
        final String STR_METHOD_NAME = "loadTranferCashTransactions";
        log.entering(STR_METHOD_NAME);
        
        //1.1.get当日以降振替注文(資産評価)
        //ロード対象となるAIO注文単位Rowオブジェクトのリストを取得する。
        //[引数]
        //資産評価 = this
        List l_transferOrderUnitRows = WEB3TPPersistentDataManager.getInstance().
        getTodaysCashTransferOrders(this);       
        
        //1.2.(*)＜LOOP処理：get当日以降振替注文()の戻り値の要素数回＞
        for (Iterator l_iter = l_transferOrderUnitRows.iterator(); l_iter.hasNext(); )
        {
            //1.2.1.get損益方向(AssetTransferTypeEnum)
            //1.2.2.(*)"約定済代金"を計算する。
            //"約定済代金" = (*)AIO注文単位Row.注文数量 * get損益方向()
            //(*)
            //AIO注文単位Row = get当日以降振替注文()の戻り値の要素オブジェクト
            AioOrderUnitRow l_row = (AioOrderUnitRow) l_iter.next();

            double l_dblTransferAmount = l_row.getQuantity() * WEB3TPTransactionReflector.getCashDir(
                l_row.getTransferType());
            
            //1.2.3.create取引情報(余力計算条件, ProductTypeEnum, long, FinTransactionType, Date, 
            //double, double, double, double, Date, TaxTypeEnum)
            //取引情報オブジェクトを生成する。
            //[引数]
            //余力計算条件 = this.get余力計算条件()
            //ProductTypeEnum = (*)AIO注文単位Row.銘柄タイプ
            //long = (*)AIO注文単位Row.銘柄ID
            //FinTransactionType = (*)AIO注文単位Row.注文種別.toFinTransactionType()
            //Date = (*)AIO注文単位Row.発注日
            //double = 0
            //double = 0
            //double = 0
            //double = "約定済代金"
            //Date = (*)AIO注文単位Row.受渡日
            //TaxTypeEnum = (*)AIO注文単位Row.税区分
            //(*)AIO注文単位Row = get当日以降振替注文()の戻り値の要素オブジェクト
            WEB3TPTransactionReflector l_tranRef = WEB3TPTransactionReflector.create(
                getCalcCondition(), 
                l_row.getProductType(), 
                l_row.getProductId(),
                l_row.getOrderType().toFinTransactionType(), 
                WEB3DateUtility.getDate(l_row.getBizDate(), "yyyyMMdd"), 0, 0, 0, 
                l_dblTransferAmount,
                l_row.getDeliveryDate(),
                l_row.getTaxType());
            
            //1.2.4.add取引情報<当日>(取引情報)
            //取得した取引情報オブジェクトを、this.取引情報一覧<当日>に追加する。
            //[引数]
            //取引情報 = (*)create取引情報()の戻り値
            addTodaysTransaction(l_tranRef);    
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (doIPO取引情報ロード<当日>)<BR>
     * IPO取引情報関連データをロードする。<BR>
     * <BR>
     * シーケンス図「(取引代金)doIPO取引情報ロード<当日>」参照<BR>
     * <BR>
     * @@roseuid 40BFDF55028F
     */
    protected void loadTodaysIpoTransactions()
    {
        List l_rows = WEB3TPPersistentDataManager.getInstance().getTodaysIPOOrders(this);
        Date l_datT0 = getCalcCondition().getBizDate(0);
        
        if (l_rows != null)
        {
            for (Iterator l_iter = l_rows.iterator(); l_iter.hasNext(); )
            {
            	IpoOrderRow l_orderRow = (IpoOrderRow)l_iter.next();
            	
            	//IPO銘柄情報の取得
            	IpoProductRow l_productRow = WEB3TPPersistentDataManager.getInstance().getIPOProduct(
            			l_orderRow.getIpoProductId());

                //get余力計算条件
                WEB3TPCalcCondition l_calcCondition = getCalcCondition();

                //get会社部店別余力計算条件
                String l_strValue = l_calcCondition.getInstBranCalcCondition(
                    WEB3TPCalcCondition.IPO_OFFER_TRADINGPOWER_CHECK);

            	//(*)分岐フロー
                if ((WEB3IpoOfferTradingpowerCheckDef.CHECK.equals(l_strValue) &&
                    WEB3LotResultDef.SUPPLEMENT.equals(l_orderRow.getLotResult()) &&
                    !WEB3OfferingDivDef.PURCHASE_APPLICATION.equals(l_orderRow.getOfferingDiv())) ||
                    WEB3TPIPOHostAcceptStatusDef.PROCESSED.equals(l_orderRow.getAcceptStatus()) ||
                    l_productRow.getOfferEndDateProspec() == null ||
                    l_datT0.compareTo(l_productRow.getOfferEndDateProspec()) > 0 ||
                    BooleanEnum.TRUE.equals(l_productRow.getDeleteFlag()))
                {
                    continue;
                }

            	WEB3TPTransactionReflector l_tranRefs = createWEB3TPTransactionReflector(l_orderRow,l_productRow);
                
                addTodaysTransaction(l_tranRefs);
            }
        }
        
    }
    
    /**
     * (add取引情報<確定>)<BR>
     *　@引数を取引情報一覧<確定>に追加する。<BR>
     * @@param transaction - (取引情報)
     * @@roseuid 40F7B01601CE
     */
    protected void addFixedTransaction(WEB3TPTransactionReflector l_transaction)
    {
        if (l_transaction instanceof WEB3TPTransactionReflector)
        {
            fixedTransactions.add(l_transaction);
        }
        
    }
    
    /**
     * (add取引情報<当日>)<BR>
     *　@引数を取引情報一覧<当日>に追加する。<BR>
     * @@param transaction - (取引情報)
     * @@roseuid 40DFC17103B3
     */
    protected void addTodaysTransaction(WEB3TPTransactionReflector l_transaction)
    {
        if (l_transaction instanceof WEB3TPTransactionReflector)
        {
            todaysTransactions.add(l_transaction);
        }
    }

    /**
     * (add出金注文一覧<余力計算結果詳細用>)<BR>
     *　@引数を出金注文一覧<余力計算結果詳細用>に追加する。<BR>
     * @@param transaction - (取引情報)
     * @@roseuid 40DFC17103B3
     */
    protected void addDisplayCashOutTransaction(WEB3TPTransactionReflector l_transaction)
    {
        if (l_transaction instanceof WEB3TPTransactionReflector && 
                FinTransactionType.DEBIT.equals(l_transaction.getFinTransactionType()))
        {
            displayCashOutTransactions.add(l_transaction);
        }
    }
    
    /**
     * (create取引情報) <BR>
     *<BR>
     * 引数.株式注文より、取引情報オブジェクトを作成し返却する。<BR> 
     *<BR>
     * ※シーケンス図「(取引代金)create取引情報(EqtypeOrderUnitRow)」参照 <BR>
     * <BR>
     * @@param l_orderUnitRow - (株式注文)
     * @@return WEB3TPTransactionReflector[]
     * @@roseuid 40DBEE37031E
     */
    protected WEB3TPTransactionReflector[] createWEB3TPTransactionReflector(
        EqtypeOrderUnitRow l_orderUnitRow)
    {    	
        final String STR_METHOD_NAME = "createWEB3TPTransactionReflector(EqtypeOrderUnitRow)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.(*)"未約定数量"、"約定済数量"を計算する。
        //未約定数量, 約定済数量
        double l_dblUnexecutedQuantity, l_dblExecutedQuantity;
        //未約定代金,約定済代金
        double l_dblUnexecutedAmount, l_dblExecutedAmount;
        
        //"未約定数量" = (*1)
        //"約定済数量" = 引数.株式注文.get約定数量()
        //(*1)
        //[a.引数.株式注文.get注文有効状態() == 2:クローズ の場合]
        //　@"未約定数量" = 0
        if(OrderOpenStatusEnum.CLOSED.equals(l_orderUnitRow.getOrderOpenStatus()))
        {
            l_dblUnexecutedQuantity = 0.0d;
        }
        //[a.以外の場合]
        //　@"未約定数量" =　@引数.株式注文.get注文数量() - 引数.株式注文.get約
        else
        {
            //市場確認済値対応
            l_dblUnexecutedQuantity = l_orderUnitRow.getQuantity() - l_orderUnitRow.getExecutedQuantity();
        }
        
        l_dblExecutedQuantity = l_orderUnitRow.getExecutedQuantity();
        
        //代金の取得
        
        //1.2.get損益方向(FinTransactionType)
        //トランザクションタイプ別の損益方向を取得する。
        //[引数]
        //FinTransactionType = 引数.株式注文.get注文種別().toFinTransactionType()の戻り値
        Date l_tranDate = WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), "yyyyMMdd");
        Date l_deliveryDate = l_orderUnitRow.getDeliveryDate();
        FinTransactionType l_tranType = l_orderUnitRow.getOrderType().toFinTransactionType();
        int l_intCashDir = WEB3TPTransactionReflector.getCashDir(l_tranType);
        
        //1.3.get約定済受渡代金合計(EqtypeOrderUnitRow)
        //株式注文単位に紐付く受渡代金を集計する。
        //[引数]
        //EqtypeOrderUnitRow = 引数.株式注文
        
        //1.4.(*)分岐フロー
        //全部約定の場合
        //("未約定数量 == 0")
        if(l_dblUnexecutedQuantity == 0)
        {
            //1.4.1(*)"未約定代金"、"約定済代金"を計算する。
            //"未約定代金" = 0
            //"約定済代金" = get約定済受渡代金合計()の戻り値
            l_dblUnexecutedAmount = 0;
            l_dblExecutedAmount = getNetAmountTotal(l_orderUnitRow);                        
        }
        
        //1.5.(*)分岐フロー
        //以外（一部約定・未約定）の場合
        else
        {
            double l_dblEstimatedPrice = l_orderUnitRow.getEstimatedPrice();           
            
            //[a.現引・現渡の場合]
            // （引数.株式注文.get注文種別().toFinTransactionType() == 130：現引 or 140：現渡）
            // 　@"約定済代金" = 引数.株式注文.get概算受渡代金() * get損益方向()の戻り値
            // 　@"未約定代金" = 0      
            if (FinTransactionType.EQTYPE_SWAP_MARGIN_LONG.equals(l_tranType) ||
                    FinTransactionType.EQTYPE_SWAP_MARGIN_SHORT.equals(l_tranType))
            {
                l_dblUnexecutedAmount = 0;
                l_dblExecutedAmount = l_dblEstimatedPrice * l_intCashDir;  
            }
            
            //
            // [a.ミニ株買の場合]
            // （引数.株式注文.get注文種別().toFinTransactionType() == 201：ミニ株買）
            // 　@"約定済代金" = 0
            // 　@"未約定代金" = 引数.株式注文.get概算受渡代金() * get損益方向()の戻り値
            else if(FinTransactionType.EQTYPE_MINI_STOCK_BUY.equals(l_tranType))
            {
                l_dblUnexecutedAmount = l_dblEstimatedPrice * l_intCashDir; 
                l_dblExecutedAmount = 0;
            }
            
            // [a.以外（現物買・現物売・信用返済・ミニ株売）の場合]
            // 　@"約定済代金" = get約定済受渡代金合計()の戻り値
            else 
            {    
                // 　@[b.現物買の場合]
                // 　@（引数.株式注文.get注文種別().toFinTransactionType() == 70：現物買）
                // 　@　@"未約定代金" = (引数.株式注文.get概算受渡代金() - ABS("約定済代金")) * get損益方向()の戻り値
                l_dblExecutedAmount = getNetAmountTotal(l_orderUnitRow);
                if(FinTransactionType.EQTYPE_EQUITY_BUY.equals(l_tranType))
                {
                    log.debug("l_dblEstimatedPrice=" + l_dblEstimatedPrice);
                    
                    l_dblUnexecutedAmount = 
                        (l_dblExecutedQuantity == 0) ? l_dblEstimatedPrice * l_intCashDir :
                            (l_dblEstimatedPrice - Math.abs(l_dblExecutedAmount)) * l_intCashDir;
                    
                    log.debug("l_dblUnexecutedAmount=" + l_dblUnexecutedAmount);
                    
                    //2次対応保留中案件
                    /*
                     //出来終了後の場合 
                      if(getCalcCondition().isEquityExecutionDiv())
                      {
                      Date l_datT0 = getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0);                                                       
                      
                      //出来終了後注文繰越処理中の繰越注文未約定分の受渡日を繰越後と同一にする対応        
                       
                       //注文繰越処理中の繰越注文の場合
                        //（発注日＝　@T+0　@かつ　@注文失効日　@＞　@発注日）
                         //注文繰越処理済時と同様の拘束を行う
                          
                          List l_tranRefs = new ArrayList();
                          
                          if((WEB3DateUtility.compareToDay(l_datT0, l_tranDate) == 0) && 
                          (WEB3DateUtility.compareToDay(l_row.getExpirationDate(), l_tranDate) > 0))
                          {
                          //一部約定の場合
                           //当日約定分の取引代金オブジェクトを生成する
                            if(l_dblExecutedQuantity > 0) 
                            {                                                  
                            WEB3TPTransactionReflector l_todayExecTranRef = 
                            WEB3TPTransactionReflector.create
                            (
                            getCalcCondition(),
                            l_row.getProductType(),
                            l_row.getProductId(),
                            l_tranType,
                            l_tranDate,            
                            0, 0,//未約定代金は繰越注文分として計上するので0とする,
                            l_dblExecutedQuantity, l_dblExecutedAmount,//当日約定済代金を反映
                            l_deliveryDate//受渡日
                            );
                            
                            l_tranRefs.add(l_todayExecTranRef);
                            }
                            
                            
                            //繰越される未約定分の取引代金オブジェクトを仮想的に生成する
                             //受渡日の取得===余力更新で統一的に受渡日計算のUtilityをコールするように修正予定
                              //☆☆☆☆☆☆
                               
                               WEB3TPTransactionReflector l_carryOverTranRef =  
                               WEB3TPTransactionReflector.create
                               (
                               getCalcCondition(),
                               l_row.getProductType(),
                               l_row.getProductId(),
                               l_tranType,
                               l_tranDate,            
                               l_dblUnexecutedQuantity, l_dblUnexecutedAmount,//未約定代金のみ反映
                               0, 0,//約定済代金は0
                               getCalcCondition().rollBizDate(l_deliveryDate, 1)//受渡日は一日先へシフト
                               );
                               
                               l_tranRefs.add(l_carryOverTranRef);
                               
                               return (WEB3TPTransactionReflector[])l_tranRefs.toArray(new WEB3TPTransactionReflector[l_tranRefs.size()]);
                               
                               }
                               
                               }
                               
                               */
                }
                
                // 　@[b.以外（現物売・信用返済・ミニ株売）の場合]
                // 　@（引数.株式注文.get注文種別().toFinTransactionType() == 70：現物買）
                // 　@　@"未約定代金" = 0
                else
                {
                    l_dblUnexecutedAmount = 0.0d;
                }
            }            
        }
        
        //1.6.create取引情報(余力計算条件, ProductTypeEnum, long, FinTransactionType, 
        //Date, double, double, double, double, Date, TaxTypeEnum)
        //取引情報オブジェクトを生成する。
        //
        //[引数]
        //余力計算条件 = this.get余力計算条件()
        //ProductTypeEnum = 引数.株式注文.get銘柄タイプ()
        //long = 引数.株式注文.get銘柄ID()
        //FinTransactionType = 引数.株式注文.get注文種別().toFinTransactionType()
        //Date = 引数.株式注文.get発注日()
        //double = 計算した"未約定数量"
        //double = 計算した"未約定代金"
        //double = 計算した"約定済数量"
        //double = 計算した"約定済代金"
        //Date = 引数.株式注文.get受渡日()
        //TaxTypeEnum = 引数.株式注文.get税区分()
        //1.7.(*)返却値：取引情報[]{create取引情報()の戻り値}
        log.exiting(STR_METHOD_NAME);
        return new WEB3TPTransactionReflector[] {
                WEB3TPTransactionReflector.create
                (
                        getCalcCondition(),
                        l_orderUnitRow.getProductType(),
                        l_orderUnitRow.getProductId(),
                        l_tranType,
                        l_tranDate,            
                        l_dblUnexecutedQuantity, l_dblUnexecutedAmount,
                        l_dblExecutedQuantity, l_dblExecutedAmount,
                        l_deliveryDate,
                        l_orderUnitRow.getTaxType()
                )
        };
    }
    
    /**
     * (doGP取引情報ロード<当日>)<BR>
     * GP注文を取引情報オブジェクトにロードする。<BR>
     * <BR>
     * １）　@GP注文を以下のメソッドを呼び取得する。<BR>
     * <BR>
     *     資金関連データソースアクセス管理.get当日以降GP注文()<BR>
     * <BR>
     * 以降の処理は、検索結果の各行に対して実施する。<BR>
     * <BR>
     * ２）　@取引情報を生成する。<BR>
     * <BR>
     * －設定項目<BR>
     * ・銘柄タイプ        ＝  GP注文.銘柄タイプ<BR>
     * ・銘柄ID            ＝  GP注文.銘柄ID<BR>
     * ・トランザクションタイプ        ＝ GP注文.注文種別.toFinTransactionType()<BR>
     * ・トランザクション発生日    ＝  GP注文.発注日<BR>
     * ・未約定数量        ＝  GP注文.数量　@－　@GP注文.約定済数量<BR>
     * ・約定済数量        ＝  GP注文.約定済数量<BR>
     * ・未約定代金        ＝  0<BR>
     * ・約定済代金        ＝  GP注文.概算受渡金額<BR>
     * ・変動反映日、変動開始日設定：calc変動反映日（GP注文.受渡日）<BR>
     * ・変動反映開始日        ＝  引数.受渡日<BR>
     * ・変動反映終了日        ＝  引数.受渡日<BR>
     * <BR>
     * ３）　@取引情報一覧<当日>に生成した取引情報を追加する。<BR>
     * <BR>
     * @@roseuid 40BFDF5700CA
     */
    protected void loadTodaysGPTransactions()
    {
        List l_rows = WEB3TPPersistentDataManager.getInstance().getTodaysGPOrders(this);
        
        if (l_rows != null)
        {
            for (Iterator l_iter = l_rows.iterator(); l_iter.hasNext(); )
            {
                WEB3TPTransactionReflector l_tranRef = createWEB3TPTransactionReflector( (
                        RuitoOrderUnitRow) l_iter.next());
                addTodaysTransaction(l_tranRef);
                
            }
        }
        
        
    }
    
    /**
     * (do外株取引情報ロード<当日>)<BR>
     * 外株注文を取引情報オブジェクトにロードする。<BR>
     * <BR>
     * １）　@外株注文を以下のメソッドを呼び取得する。<BR>
     * <BR>
     *     資金関連データソースアクセス管理.get当日以降外株注文()<BR>
     * <BR>
     * 以降の処理は、検索結果の各行に対して実施する。<BR>
     * <BR>
     * ２）　@取引情報を生成する。<BR>
     * <BR>
     * <BR>
     * －設定項目<BR>
     * ・銘柄タイプ        ＝  外株注文.銘柄タイプ<BR>
     * ・銘柄ID            ＝  外株注文.銘柄ID<BR>
     * ・トランザクションタイプ        ＝ 外株注文.注文種別.toFinTransactionType()<BR>
     * ・トランザクション発生日    ＝  外株注文.発注日<BR>
     * ・未約定数量        ＝  外株注文.数量　@－　@外株注文.約定済数量<BR>
     * ・約定済数量        ＝  外株注文.約定済数量<BR>
     * ・未約定代金        ＝　@外株注文.概算受渡代金<BR>
     * ・約定済代金        ＝　@外株トランザクション.受渡代金<BR>
     * ・変動反映開始日、変動反映終了日設定：calc変動反映日（外株注文.受渡日）<BR>
     * ・変動反映開始日        ＝  引数.受渡日<BR>
     * ・変動反映終了日        ＝  引数.受渡日<BR>
     * <BR>
     * (*)
     * 外株注文が約定している場合、外株トランザクションを以下のメソッドを呼び取得する。
     * <BR>
     *     資金関連データソースアクセス管理.get外株トランザクション()<BR>
     * <BR>
     * ３）　@取引情報一覧<当日>に生成した取引情報を追加する。<BR>
     * <BR>
     * @@roseuid 40BFDF590389
     */
    protected void loadTodaysForeignEquityTransactions()
    {
        List l_rows = WEB3TPPersistentDataManager.getInstance().getTodaysFeqOrders(this);
        
        for(int i = 0; i < l_rows.size(); i++)
        {
            log.debug("選択された行[" + i + "]=" + (FeqOrderUnitRow)l_rows.get(i));                
        }
        
        
        if (l_rows != null)
        {
            for (Iterator l_iter = l_rows.iterator(); l_iter.hasNext(); )
            {
                WEB3TPTransactionReflector l_tranRef = createWEB3TPTransactionReflector( (
                        FeqOrderUnitRow) l_iter.next());
                addTodaysTransaction(l_tranRef);
                
                log.debug("追加された取引情報=" + l_tranRef);
                
            }
        }
        
    }
    
    /**
     * (do債券取引情報ロード) <BR>
     * <BR>
     * 債券取引情報関連データをロードする。 <BR>
     * <BR>
     * ※シーケンス「(取引代金)do債券取引情報ロード」参照<BR>
     */
    protected void loadBondTransactions()
    {
        final String STR_METHOD_NAME = "loadBondTransactions()";
        log.entering(STR_METHOD_NAME);
        
        //1.1.get債券注文<取引代金>(資産評価)
        //ロード対象となる債券注文単位テーブル行オブジェクトのリストを取得する。 
        //[引数] 
        //資産評価 = this
        List l_lisBondOrdersAmountRows = 
            WEB3TPPersistentDataManager.getInstance().getBondOrdersAmount(this);    
        
        //1.2.(*)分岐フロー
        //get債券注文<取引代金>( )の戻り値 != null　@の場合
        if (l_lisBondOrdersAmountRows != null)
        {
            //1.2.1.(*)＜LOOP処理：get債券注文<取引代金>()の戻り値の要素数回＞
            int l_intSize = l_lisBondOrdersAmountRows.size();
            for (int i = 0 ; i < l_intSize; i ++)
            {
                //1.2.1.1.create取引情報(BondOrderUnitRow)
                //取引情報オブジェクトを取得する。 
                //[引数] 
                //BondOrderUnitRow = (*)get債券注文<取引代金>()の戻り値の要素オブジェクト 
                WEB3TPTransactionReflector l_tpTransactionReflector = 
                    createWEB3TPTransactionReflector(
                        (BondOrderUnitRow)l_lisBondOrdersAmountRows.get(i));
                
                //1.2.1.2.add取引情報<当日>(取引情報)
                //取得した取引情報オブジェクトを、this.取引情報一覧<当日>に追加する。 
                //[引数] 
                //取引情報 = (*)create取引情報()の戻り値 
                addTodaysTransaction(l_tpTransactionReflector);
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (create取引情報) <BR>
     * <BR>
     * 引数.確定取引明細より、取引情報オブジェクトを作成し返却する。 <BR>
     * <BR>
     * ※シーケンス図「(取引代金)create取引情報(FixedFinTransactioRow)」参照 <BR>
     * <BR>
     * @@param l_row - (確定取引明細)
     * @@return WEB3TPTransactionReflector
     * @@roseuid 40D91576037D
     */
    protected WEB3TPTransactionReflector createWEB3TPTransactionReflector(
        FixedFinTransactionRow l_row)
    {
        final String STR_METHOD_NAME = "createWEB3TPTransactionReflector(FixedFinTransactionRow)";
        log.entering(STR_METHOD_NAME);
        
        FinTransactionType l_tranType = l_row.getFinTransactionType();
        
        //1.1.(*)"未約定数量"、"約定済数量"、"未約定代金"、"約定済代金"を計算する。
        //"未約定数量" = 0
        //"未約定代金" = 0
        //"約定済数量" = 引数.確定取引明細.約定数量
        //"約定済代金" = 引数.確定取引明細.受
        //未約定数量（確定なので0）
        double l_dblUnexecutedQuantity = 0;
        //約定数量
        double l_dblExecutedQuantity = l_row.getQuantity();
        //未約定代金（確定なので0）
        double l_dblUnexecutedAmount = 0;
        //約定済代金
        double l_dblExecutedAmount = l_row.getNetAmount();
        
        Date l_tranDate = l_row.getFinTransactionTimestamp();
        
        //1.2.create取引情報(余力計算条件, ProductTypeEnum, long, FinTransactionType, 
        //Date, double, double, double, double, Date, TaxTypeEnum)
        //取引情報オブジェクトを生成する。
        //[引数]
        //余力計算条件 = this.get余力計算条件()
        //ProductTypeEnum = 引数.確定取引明細.get銘柄タイプ()
        //long = 引数.確定取引明細.get銘柄ID()
        //FinTransactionType = 引数.確定取引明細.getトランザクションタイプ()
        //Date = 引数.確定取引明細.getトランザクション発生日時()
        //double = "未約定数量"
        //double = "未約定代金"
        //double = "約定済数量"
        //double = "約定済代金"
        //Date = 引数.確定取引明細.get受渡日()
        //TaxTypeEnum = 引数.確定取引明細.get税区分()
        log.exiting(STR_METHOD_NAME);
        return WEB3TPTransactionReflector.create
        (
                getCalcCondition(),
                l_row.getProductType(),
                l_row.getProductId(),
                l_tranType,
                l_tranDate,
                l_dblUnexecutedQuantity, l_dblUnexecutedAmount,
                l_dblExecutedQuantity, l_dblExecutedAmount,
                l_row.getDeliveryDate(),
                l_row.getTaxType()
        );
    }
    
    /**
     * (create取引情報)<BR>
     *<BR>
     * 引数.オプション注文より、取引情報オブジェクトを作成し返却する。<BR> 
     *<BR>
     * ※シーケンス図「(取引代金)create取引情報(IfoOrderUnitRow)」参照<BR> 
     * <BR>
     * @@param l_row - (オプション注文)
     * @@return WEB3TPTransactionReflector[]
     * @@roseuid 40DFB7F601AF
     */
    protected WEB3TPTransactionReflector[] createWEB3TPTransactionReflector(
        IfoOrderUnitRow l_row)
    {
        final String STR_METHOD_NAME = "createWEB3TPTransactionReflector(IfoOrderUnitRow)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.(*)"未約定数量"、"約定済数量"を計算する。
        double l_dblUnexecutedQuantity, l_dblExecutedQuantity;
        //未約定代金,約定済代金
        double l_dblUnexecutedAmount, l_dblExecutedAmount;
        
        //数量の取得
        
        //"未約定数量" = (*1)
        //"約定済数量" = 引数.オプション注文.get約定数量()
        //(*1)
        //[a.引数.オプション注文.get注文有効状態() == 2:クローズ の場合]
        //　@"未約定数量" = 0
        //[a.以外の場合]
        //　@"未約定数量" =　@引数.オプション注文.get注文数量() - 引数.オプ
        if(OrderOpenStatusEnum.CLOSED.equals(l_row.getOrderOpenStatus()))
        {
            l_dblUnexecutedQuantity = 0.0d;
        }
        else
        {
            //市場確認済値対応
            l_dblUnexecutedQuantity = l_row.getQuantity() - l_row.getExecutedQuantity();
        }
        
        l_dblExecutedQuantity = l_row.getExecutedQuantity();
        
        //1.2.get損益方向(FinTransactionType)
        //トランザクションタイプ別の損益方向を取得する。
        //[引数]
        //FinTransactionType = 引数.オプション注文.get注文種別().toFinTransactionType()の戻り値
        //代金の取得
        Date l_tranDate = WEB3DateUtility.getDate(l_row.getBizDate(), "yyyyMMdd");
        Date l_deliveryDate = l_row.getDeliveryDate();
        FinTransactionType l_tranType = l_row.getOrderType().toFinTransactionType();
        int l_intCashDir = WEB3TPTransactionReflector.getCashDir(l_tranType);
        
        //1.3.get約定済受渡代金合計(IfoOrderUnitRow)
        //オプション注文単位に紐付く受渡代金を集計する。
        //[引数]
        //IfoOrderUnitRow = 引数.オプション注文
        //全約定の場合
        //1.4.(*)分岐フロー
        //全部約定の場合
        //("未約定数量 == 0")
        if(l_dblUnexecutedQuantity == 0)
        {
            //1.4.1.(*)"未約定代金"、"約定済代金"を計算する。
            //"未約定代金" = 0
            //"約定済代金" = get約定済受渡代金合計()の戻り値
            l_dblUnexecutedAmount = 0;
            l_dblExecutedAmount = getNetAmountTotal(l_row);                        
        }
        
        //1.5.(*)分岐フロー
        //(*)分岐フロー
        //以外（一部約定・未約定）の場合
        //未約定あるいは一部約定の場合
        else
        {
            double l_dblEstimatedPrice = l_row.getEstimatedPrice();           
            
            //約定分（オプショントランザクション.受渡代金）＝＞約定済代金　@
            l_dblExecutedAmount = getNetAmountTotal(l_row);

            //1.5.1.(*)"未約定代金"、"約定済代金"を計算する。
            //"約定済代金" = get約定済受渡代金合計()の戻り値
            //[a.オプション新規買建の場合]
            //（引数.オプション注文.get注文種別().toFinTransactionType() == 605：OP新規買建注文）
            //　@"未約定代金" = (引数.オプション注文.get概算受渡代金() - ABS("約定済代金")) * get損益方向()の戻り値
            if(FinTransactionType.EQTYPE_IDX_OPTIONS_BUY_TO_OPEN.equals(l_tranType))
            {
                
                l_dblUnexecutedAmount = 
                    (l_dblExecutedQuantity == 0) ? l_dblEstimatedPrice * l_intCashDir :
                        (l_dblEstimatedPrice - Math.abs(l_dblExecutedAmount)) * l_intCashDir;
                
                
                //余力2次対応保留中案件
                /*                //出来終了後の場合 
                 if(getCalcCondition().isOptionExecutionDiv())
                 {
                 Date l_datT0 = getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0);                                                       
                 
                 //注文繰越処理中の繰越注文の場合
                  //（発注日＝　@T+0　@かつ　@注文失効日　@＞　@発注日）
                   //注文繰越処理済時と同様の拘束を行う
                    
                    List l_tranRefs = new ArrayList();
                    
                    if((WEB3DateUtility.compareToDay(l_datT0, l_tranDate) == 0) && 
                    (WEB3DateUtility.compareToDay(l_row.getExpirationDate(), l_tranDate) > 0))
                    {
                    //一部約定の場合
                     //当日約定分の取引代金オブジェクトを生成する
                      if(l_dblExecutedQuantity > 0) 
                      {                                                  
                      WEB3TPTransactionReflector l_todayExecTranRef = 
                      WEB3TPTransactionReflector.create
                      (
                      getCalcCondition(),
                      l_row.getProductType(),
                      l_row.getProductId(),
                      l_tranType,
                      l_tranDate,            
                      0, 0,//未約定代金は繰越注文分として計上するので0とする,
                      l_dblExecutedQuantity, l_dblExecutedAmount,//当日約定済代金を反映,
                      l_deliveryDate//受渡日
                      );
                      
                      l_tranRefs.add(l_todayExecTranRef);
                      }
                      
                      
                      //繰越される未約定分の取引代金オブジェクトを仮想的に生成する
                       //受渡日の取得===余力更新で統一的に受渡日計算のUtilityをコールするように修正予定
                        //ただしオプションは他のところで呼ばれないのであればこのままの実装でOK
                         //☆☆☆☆☆☆
                          
                          WEB3TPTransactionReflector l_carryOverTranRef =  
                          WEB3TPTransactionReflector.create
                          (
                          getCalcCondition(),
                          l_row.getProductType(),
                          l_row.getProductId(),
                          l_tranType,
                          l_tranDate,            
                          l_dblUnexecutedQuantity, l_dblUnexecutedAmount,//未約定代金のみ反映
                          0, 0,//約定済代金は0,
                          getCalcCondition().rollBizDate(l_deliveryDate, 1)//受渡日は一日先へシフト
                          );
                          
                          l_tranRefs.add(l_carryOverTranRef);
                          
                          return (WEB3TPTransactionReflector[])l_tranRefs.toArray(new WEB3TPTransactionReflector[l_tranRefs.size()]);
                          
                          }
                          
                          }
                          */            
            }
            //[a.以外の場合]
            //　@"未約定代金" = 0
            else
            {
                l_dblUnexecutedAmount = 0.0d;
            } 
            
        }          
        //1.6.create取引情報(余力計算条件, ProductTypeEnum, long, FinTransactionType, 
        //Date, double, double, double, double, Date, TaxTypeEnum)
        //取引情報オブジェクトを生成する。
        //[引数]
        //余力計算条件 = this.get余力計算条件()
        //ProductTypeEnum = 引数.オプション注文.get銘柄タイプ()
        //long = 引数.株式注文.get銘柄ID()
        //FinTransactionType = 引数.オプション注文.get注文種別().toFinTransactionType()
        //Date = 引数.オプション注文.get発注日()
        //double = 計算した"未約定数量"
        //double = 計算した"未約定代金"
        //double = 計算した"約定済数量"
        //double = 計算した"約定済代金"
        //Date = 引数.オプション注文.get受渡日()
        //TaxTypeEnum = 引数.オプション注文.get税区分()
        log.exiting(STR_METHOD_NAME);
        return new WEB3TPTransactionReflector[]{
                WEB3TPTransactionReflector.create
                (
                        getCalcCondition(),
                        l_row.getProductType(),
                        l_row.getProductId(),
                        l_tranType,
                        l_tranDate,            
                        l_dblUnexecutedQuantity, l_dblUnexecutedAmount,
                        l_dblExecutedQuantity, l_dblExecutedAmount,
                        l_deliveryDate,
                        l_row.getTaxType()
                )};
    }
    
    /**
     * (create取引情報)<BR>
     *<BR>
     * 引数.投信注文（MutualFundOrderUnitRow）から取引情報を作成する。<BR> 
     *<BR>
     * ※シーケンス図「(取引代金)create取引情報(MutualFundOrderUnitRow)」参照<BR> 
     * <BR>
     * @@param l_row - (投信注文)
     * @@return WEB3TPTransactionReflector
     * @@roseuid 40DFB7F700A5
     */
    protected WEB3TPTransactionReflector createWEB3TPTransactionReflector(
        MutualFundOrderUnitRow l_row)
    {
        final String STR_METHOD_NAME = "createWEB3TPTransactionReflector(MutualFundOrderUnitRow)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.get損益方向(FinTransactionType)
        //トランザクションタイプ別の損益方向を取得する。
        //[引数]
        //FinTransactionType = 引数.投信注文.get注文種別().toFinTransactionType()の戻り値
        FinTransactionType l_tranType = l_row.getOrderType().toFinTransactionType();
        int l_intCashDir = WEB3TPTransactionReflector.getCashDir(l_tranType);
        
        
        //1.2.(*)"未約定数量"、"約定済数量"、"未約定代金"、"約定済代金"を計算する。
        //未約定数量
        double l_dblUnexecutedQuantity = 0;
        
        //未約定代金
        double l_dblUnexecutedAmount = 0;
        
        //約定数量
        double l_dblExecutedQuantity = 0;
        
        //約定済代金
        double l_dblExecutedAmount = 0;
        
        //[a.投信売付の場合]
        //    （引数.投信注文.get注文種別().toFinTransactionType() == 302：投信売付）
        //
        //   　@"未約定数量" = 0
        //   　@"未約定代金" = 0
        //   　@"約定済数量" = "数量"(*1) - 引数.投信注文.約定済数量
        //   　@"約定済代金" = (引数.投信注文.概算受渡金額 - 引数.投信注文.合計約定代金) * get損益方向()の戻り値
        if(FinTransactionType.EQTYPE_MF_SELL.equals(l_tranType))
        {
            //未約定数量
            l_dblUnexecutedQuantity = 0;
            
            //未約定代金
            l_dblUnexecutedAmount = 0;
            
            //約定数量
            l_dblExecutedQuantity =
                l_row.getConfirmedQuantityIsNull() ? 
                    (l_row.getQuantity() - l_row.getExecutedQuantity()):
                        (l_row.getConfirmedQuantity() - l_row.getExecutedQuantity());
                        
            //約定済代金
            l_dblExecutedAmount = (l_row.getEstimatedPrice() - l_row.getExecutedAmount()) * l_intCashDir;
        }
        //投信買付 OR 投信募集の場合
        //[a.以外（投信買付or投信募集）の場合]
        //　@"未約定数量" = "数量"(*1) - 引数.投信注文.約定済数量
        //　@"未約定代金" = (引数.投信注文.概算受渡金額 - 引数.投信注文.合計約定代金) * get損益方向()の戻り値
        //　@"約定済数量" = 引数.投信注文.約定済数量（値は0）
        //　@"約定済代金" = 引数.投信注文.合計約定代金（値は0）
        //(*1)
        //[引数.投信注文.市場から確認済みの数量 != null の場合]
        //　@"数量" = 引数.投信注文.市場から確認済みの数量
        //[以外 の場合]
        //　@"数量" = 引数.注文数量
        else
        {
            //未約定数量
            l_dblUnexecutedQuantity =
                l_row.getConfirmedQuantityIsNull() ? 
                    (l_row.getQuantity() - l_row.getExecutedQuantity()):
                        (l_row.getConfirmedQuantity() - l_row.getExecutedQuantity());
                    
            //未約定代金
            l_dblUnexecutedAmount = (l_row.getEstimatedPrice() - l_row.getExecutedAmount()) * l_intCashDir;
            
            //約定数量（0）
            l_dblExecutedQuantity = l_row.getExecutedQuantity();
            
            //約定済代金（0）
            l_dblExecutedAmount = l_row.getExecutedAmount();
        }
        
        Date l_tranDate = WEB3DateUtility.getDate(l_row.getBizDate(), "yyyyMMdd");
        
        Date l_deliveryDate = null;
        
        //1.3.(*)"受渡日"を計算する。
        //投信募集の場合、入金日を受渡日とみなす
        //[a.投信募集の場合]
        //（引数.投信注文.get注文種別().toFinTransactionType() == 303：投信募集）
        //　@[b.引数.投信注文.入金日 == null の場合]
        //　@　@"受渡日" = T+0(*1)
        //　@[b.以外 の場合]
        //　@　@"受渡日" = 引数.投信注文.入金日
        if(FinTransactionType.EQTYPE_MF_RECRUIT.equals(l_tranType))
        {
            //入金日を取得
            Date l_datPayment = l_row.getPaymentDate();
            
            //入金日＝nullの場合
            if(l_datPayment == null)
            {
                //営業日(T+0)を受渡日とする
                l_deliveryDate = getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0);
            }
            else
            {
                l_deliveryDate = l_datPayment;
            }
        }
        //[a.以外の場合]
        //　@　@"受渡日" = 引数.投信注文.受渡日
        //(*1)
        //T+0 = this.get余力計算条件().get営業日(:int = 0)
        else
        {
            l_deliveryDate = l_row.getDeliveryDate();
        }
        
        //1.4.create取引情報(余力計算条件, ProductTypeEnum, long, FinTransactionType, 
        //Date, double, double, double, double, Date, TaxTypeEnum)
        //取引情報オブジェクトを生成する。
        //[引数]
        //余力計算条件 = this.get余力計算条件()
        //ProductTypeEnum = 引数.投信注文.get銘柄タイプ()
        //long = 引数.投信注文.get銘柄ID()
        //FinTransactionType = 引数.投信注文.get注文種別().toFinTransactionType()
        //Date = 引数.投信注文.get発注日()
        //double = "未約定数量"
        //double = "未約定代金"
        //double = "約定済数量"
        //double = "約定済代金"
        //Date = "受渡日"
        //TaxTypeEnum = 引数.投信注文.get税区分()
        //1.5.(*)返却値：create取引情報()の戻り値
        log.exiting(STR_METHOD_NAME);
        return WEB3TPTransactionReflector.create
        (
                getCalcCondition(),
                l_row.getProductType(),
                l_row.getProductId(),
                l_tranType,
                l_tranDate,
                l_dblUnexecutedQuantity, l_dblUnexecutedAmount,
                l_dblExecutedQuantity, l_dblExecutedAmount,
                l_deliveryDate,
                l_row.getTaxType()
        );
    }
    
    /**
     * (create取引情報)<BR>
     *<BR>
     * 引数.GP注文より、取引情報オブジェクトを作成し返却する。<BR> 
     *<BR>
     * ※シーケンス図「(取引代金)create取引情報(RuitoOrderUnitRow)」参照<BR> 
     *<BR>
     * @@param l_row GP注文
     * @@return WEB3TPTransactionReflector
     */
    protected WEB3TPTransactionReflector createWEB3TPTransactionReflector(
        RuitoOrderUnitRow l_row)
    {
        final String STR_METHOD_NAME = "createWEB3TPTransactionReflector(RuitoOrderUnitRow)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.get損益方向(FinTransactionType)
        //トランザクションタイプ別の損益方向を取得する。
        //[引数]
        //FinTransactionType = 引数.GP注文.get注文種別().toFinTransactionType()の戻り値
        FinTransactionType l_tranType = l_row.getOrderType().toFinTransactionType();
        int l_intCashDir = WEB3TPTransactionReflector.getCashDir(l_tranType);
        
        //1.2.(*)"未約定数量"、"約定済数量"、"未約定代金"、"約定済代金"を計算する。
        //未約定数量
        //"未約定数量" = (*1)
        //"未約定代金" = 0
        //"約定済数量" = 引数.GP注文.約定数量
        //"約定済代金" = "未約定数量" * get損益方向()の戻り値
        //(*1)
        //[引数.GP注文.市場から確認済みの数量 == null]
        //　@"未約定数量" = 引数.GP注文.注文数量
        //[以外の場合]
        //　@"未約定数量" = 引数.GP注文.市場から確認済みの数量 - 引数.GP注文.約定数量
        double l_dblUnexecutedQuantity =
            l_row.getConfirmedQuantityIsNull() ? l_row.getQuantity() :
                (l_row.getConfirmedQuantity() - l_row.getExecutedQuantity());
            
            //約定数量(0のはず)
            double l_dblExecutedQuantity = l_row.getExecutedQuantity();
            
            //未約定代金を0とする
            double l_dblUnexecutedAmount = 0;
            
            //未約定数量(=金額)を約定済代金として計上
            double l_dblExecutedAmount = l_dblUnexecutedQuantity * l_intCashDir;
            
            Date l_tranDate = WEB3DateUtility.getDate(l_row.getBizDate(), "yyyyMMdd");
            
            //1.3.create取引情報(余力計算条件, ProductTypeEnum, long, 
            //FinTransactionType, Date, double, double, double, double, Date, TaxTypeEnum)
            //取引情報オブジェクトを生成する。
            //[引数]
            //余力計算条件 = this.get余力計算条件()
            //ProductTypeEnum = 引数.GP注文.get銘柄タイプ()
            //long = 引数.GP注文.get銘柄ID()
            //FinTransactionType = 引数.GP注文.get注文種別().toFinTransactionType()
            //Date = 引数.GP注文.get発注日()
            //double = "未約定数量"
            //double = "未約定代金"
            //double = "約定済数量"
            //double = "約定済代金"
            //Date = 引数.GP注文.get受渡日()
            //TaxTypeEnum = 引数.GP注文.get税区分()
            
            log.exiting(STR_METHOD_NAME);
            return WEB3TPTransactionReflector.create
            (
                    getCalcCondition(),
                    l_row.getProductType(),
                    l_row.getProductId(),
                    l_tranType,
                    l_tranDate,
                    l_dblUnexecutedQuantity, 
                    l_dblUnexecutedAmount,
                    l_dblExecutedQuantity, 
                    l_dblExecutedAmount,
                    l_row.getDeliveryDate(),
                    l_row.getTaxType()
            );
    }
    
    /**
     * (create取引情報)<BR>
     *<BR>
     * 引数.外株注文より、取引情報オブジェクトを作成し返却する。<BR>
     *<BR>
     * ※シーケンス図「(取引代金)create取引情報(FeqOrderUnitRow)」参照 <BR>
     * <BR>
     * @@param l_row - (外株注文)
     * @@return WEB3TPTransactionReflector
     * @@roseuid 40DFB7F702D8
     */
    protected WEB3TPTransactionReflector createWEB3TPTransactionReflector(
        FeqOrderUnitRow l_row)
    {   
        final String STR_METHOD_NAME = "createWEB3TPTransactionReflector(FeqOrderUnitRow)";
        log.entering(STR_METHOD_NAME);
        
        log.debug("外株注文単位=" + l_row);
        
        //1.1.get損益方向(FinTransactionType)
        FinTransactionType l_tranType = l_row.getOrderType().toFinTransactionType();
        int l_intCashDir = WEB3TPTransactionReflector.getCashDir(l_tranType);
        
        //1.2.(*)"未約定数量"、"約定済数量"を計算する。
        //未約定数量
        double l_dblUnexecutedQuantity;
        
        //(*1)
        //[a.引数.外株注文.get注文有効状態() == 2:クローズ の場合]
        //　@"未約定数量" = 0
        if(OrderOpenStatusEnum.CLOSED.equals(l_row.getOrderOpenStatus()))
        {
            l_dblUnexecutedQuantity = 0.0d;
        }
        else
        {
            //[a.以外の場合]
            //　@[b.引数.外株注文.市場から確認済みの数量 == null の場合]
            //　@　@"未約定数量" =　@引数.外株注文.get注文数量()
            //　@[b.以外の場合]
            //　@　@"未約定数量" =　@引数.外株注文.get市場から確認済みの数量() 
            l_dblUnexecutedQuantity = l_row.getConfirmedQuantityIsNull() ? l_row.getQuantity() :
                (l_row.getConfirmedQuantity() - l_row.getExecutedQuantity());
        }               
        
        //約定数量
        double l_dblExecutedQuantity = l_row.getExecutedQuantity();        
        
        log.debug("l_dblUnexecutedQuantity" + l_dblUnexecutedQuantity);
        log.debug("l_dblExecutedQuantity" + l_dblUnexecutedQuantity);
        
        
        //1.3.get約定済受渡代金合計(FeqOrderUnitRow)
        //外株注文単位に紐付く受渡代金を集計する。
        //[引数]
        //FeqOrderUnitRow = 引数.外株注文

        double l_dblExecutedAmount = this.getNetAmountTotal(l_row);        
        //未約定代金
        double l_dblUnexecutedAmount = 0; 
        
        //(*)分岐フロー
        //以外（一部約定・未約定）の場合

        //1.4.1.(*)"未約定代金"、"約定済代金"を計算する。
        //[a.外株買付の場合]
        //（引数.外株注文.get注文種別() == 701：外株買付）
        if(OrderTypeEnum.FEQ_BUY.equals(l_row.getOrderType()))
        {
            //  [b.一部約定または未約定の場合]
            //   （"未約定数量" > 0）
            //  "未約定代金" = (引数.外株注文.get概算受渡代金() 
            //      - ABS("約定済代金")) * get損益方向()の戻り値
            //　@[b.以外の場合]
            //　@　@"未約定代金" = 0
            //[a.以外の場合]
            //　@"未約定代金" = 0
            if(l_dblUnexecutedQuantity > 0)
            {
                double l_dblEstimatedPrice = l_row.getEstimatedPrice();
                
                l_dblUnexecutedAmount = 
                    (l_dblExecutedQuantity == 0) ? l_dblEstimatedPrice * l_intCashDir :
                        (l_dblEstimatedPrice - Math.abs(l_dblExecutedAmount)) * l_intCashDir;                
                
                log.debug("l_dblEstimatedPrice=" + l_dblEstimatedPrice);
            }            
        }
        
        log.debug("l_dblUnexecutedAmount=" + l_dblUnexecutedAmount);
        log.debug("l_dblExecutedAmount=" + l_dblExecutedAmount);                
        
        Date l_tranDate = WEB3DateUtility.getDate(l_row.getBizDate(), "yyyyMMdd");
        
        //1.5.create取引情報(余力計算条件, ProductTypeEnum, long, FinTransactionType,
        //Date, double, double, double, double, Date, TaxTypeEnum)
        //取引情報オブジェクトを生成する。
        //
        //[引数]
        //余力計算条件 = this.get余力計算条件()
        //ProductTypeEnum = 引数.外株注文.get銘柄タイプ()
        //long = 引数.外株注文.get銘柄ID()
        //FinTransactionType = 引数.外株注文.get注文種別().toFinTransactionType()
        //Date = 引数.外株注文.get発注日()
        //double = 計算した"未約定数量"
        //double = 計算した"未約定代金"
        //double = 計算した"約定済数量"
        //double = 計算した"約定済代金"
        //Date = 引数.外株注文.get受渡日()
        //TaxTypeEnum = 引数.外株注文.get税区分()
        log.exiting(STR_METHOD_NAME);
        return  WEB3TPTransactionReflector.create
        (
                getCalcCondition(),
                l_row.getProductType(),
                l_row.getProductId(),
                l_tranType,
                l_tranDate,            
                l_dblUnexecutedQuantity, l_dblUnexecutedAmount,
                l_dblExecutedQuantity, l_dblExecutedAmount,
                l_row.getDeliveryDate(),
                l_row.getTaxType()
        );
    }
    
    /**
     * (create取引情報) <BR>
     *<BR>
     * 引数.入出金注文より、取引情報を作成し返却する。 <BR>
     *<BR>
     * シーケンス図「(取引代金)create取引情報(AioOrderUnitRow)」参照 <BR>
     * <BR>
     * @@param l_orderUnitRow - (入出金注文)
     * @@return WEB3TPTransactionReflector
     * @@roseuid 40DFB7F800C5
     */
    protected WEB3TPTransactionReflector createWEB3TPTransactionReflector(
        AioOrderUnitRow l_orderUnitRow)
    {
        final String STR_METHOD_NAME = "createWEB3TPTransactionReflector(AioOrderUnitRow)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.get損益方向(FinTransactionType)
        FinTransactionType l_tranType = l_orderUnitRow.getOrderType().toFinTransactionType();
        int l_intCashDir = WEB3TPTransactionReflector.getCashDir(l_tranType);
        
        //1.2.(*)"未約定数量"、"約定済数量"を計算する
        //未約定数量
        double l_dblUnexecutedQuantity = 0;
        //約定数量
        double l_dblExecutedQuantity = 0;
        
        //[a.引数.入出金注文.get注文有効状態() == "1：オープン"の場合]
        //　@"未約定数量" = 引数.入出金注文.get注文数量()
        //　@"約定済数量" = 0　@　@

        if (OrderOpenStatusEnum.OPEN.equals(l_orderUnitRow.getOrderOpenStatus()))
        {
            l_dblUnexecutedQuantity = l_orderUnitRow.getQuantity();
            l_dblExecutedQuantity = 0;
        }
        //[a.以外の場合]
        //　@"未約定数量" = 0
        //　@"約定済数量" = 入出金注文.get注文数量()　@
        else
        {
            l_dblUnexecutedQuantity = 0;
            l_dblExecutedQuantity = l_orderUnitRow.getQuantity();
            
        }
        
        
        //1.3.(*)"未約定代金"、"約定済代金"を計算する。
        //"未約定代金" = 0 
        //"約定済代金" = 引数.入出金注文.get注文数量() * get損益方向()
        double l_dblUnexecutedAmount = 0;
        double l_dblExecutedAmount = l_orderUnitRow.getQuantity() * l_intCashDir;
        Date l_tranDate = WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), "yyyyMMdd");
        
        //1.4.create取引情報(余力計算条件, ProductTypeEnum, long, FinTransactionType, 
        //Date, double, double, double, double, Date, TaxTypeEnum)
        //取引情報オブジェクトを生成する。
        //[引数]
        //余力計算条件 = this.get余力計算条件()
        //ProductTypeEnum = 引数.入出金注文.get銘柄タイプ()
        //long = 引数.入出金注文.get銘柄ID()
        //FinTransactionType = 引数.入出金注文.get注文種別().toFinTransactionType()
        //Date = 引数.入出金注文.get発注日()
        //double = "未約定数量"
        //double = "未約定代金"
        //double = "約定済数量"
        //double = "約定済代金"
        //Date = 引数.入出金注文.get受渡日()
        //TaxTypeEnum = 引数.入出金注文.get税区分()
        //1.5.(*)返却値：create取引情報()の戻り値
        log.exiting(STR_METHOD_NAME);
        return WEB3TPTransactionReflector.create
        (
                getCalcCondition(),
                l_orderUnitRow.getProductType(),
                l_orderUnitRow.getProductId(),
                l_tranType,
                l_tranDate,
                l_dblUnexecutedQuantity, l_dblUnexecutedAmount,
                l_dblExecutedQuantity, l_dblExecutedAmount,
                l_orderUnitRow.getDeliveryDate(),
                l_orderUnitRow.getTaxType()
        );
    }
    
    /**
     * (create取引情報)<BR> 
     * <BR>
     * 引数.債券注文（BondOrderUnitRow）から取引情報を作成する。<BR>
     * <BR>
     * ※シーケンス図「(取引代金)create取引情報(BondOrderUnitRow)」参照 <BR>
     * <BR>
     * @@param l_bondOrderUnitRow - (債券注文)
     * @@return WEB3TPTransactionReflector
     */
    protected WEB3TPTransactionReflector createWEB3TPTransactionReflector(
        BondOrderUnitRow l_bondOrderUnitRow)
    {
        final String STR_METHOD_NAME = "createWEB3TPTransactionReflector(BondOrderUnitRow)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.get損益方向(FinTransactionType)
        //トランザクションタイプ別の損益方向を取得する。
        //[引数]
        //FinTransactionType = 引数.債券注文.get注文種別().toFinTransactionType()の戻り値
        FinTransactionType l_tranType = l_bondOrderUnitRow.getOrderType().toFinTransactionType();
        int l_intCashDir = WEB3TPTransactionReflector.getCashDir(l_tranType);
        
        //未約定数量
        double l_dblUnexecutedQuantity = 0.0d;
        
        //約定数量
        double l_dblExecutedQuantity = 0.0d;
        
        //未約定代金 = 0
        double l_dblUnexecutedAmount = 0.0d;
        
        // 　@"約定済代金" = 0
        double l_dblExecutedAmount = 0.0d;
        
        //1.2.(*)分岐フロー
        //未約定の場合
        //（引数.債券注文.get注文約定区分() == 0：未約定）
        if (WEB3BondOrderExecStatusDef.UNEXECUTED.equals(l_bondOrderUnitRow.getOrderExecStatus()))
        {
            //1.2.1.(*)"未約定数量"、"約定済数量"、"未約定代金"、"約定済代金"を計算する。
            //[a.債券買い注文の場合]
            // （引数.債券注文.get注文種別() == 401：債券買い注文）
            // 　@"未約定数量" = 引数.債券注文.get注文数量()
            // 　@"未約定代金" = 引数.債券注文.get受渡代金(円貨)() * get損益方向()の戻り値
            // 　@"約定済数量" = 0
            // 　@"約定済代金" = 0
            if (OrderTypeEnum.BOND_BUY.equals(l_bondOrderUnitRow.getOrderType()))
            {
                l_dblUnexecutedQuantity = l_bondOrderUnitRow.getQuantity();
                l_dblUnexecutedAmount = l_bondOrderUnitRow.getEstimatedPrice() * l_intCashDir;
            }
            // [a.以外（債券売り注文）の場合]
            // 　@"未約定数量" = 0
            // 　@"未約定代金" = 0
            // 　@"約定済数量" = 0
            // 　@"約定済代金" = 0
        }
        
        //1.3.(*)分岐フロー
        //約定済の場合
        //（引数.債券注文.get注文約定区分() == 1：約定済）
        if (WEB3BondOrderExecStatusDef.EXECUTED.equals(l_bondOrderUnitRow.getOrderExecStatus()))
        {
            //1.3.1.(*)"未約定数量"、"約定済数量"、"未約定代金"、"約定済代金"を計算する。
            //"未約定数量" = 0
            //"未約定代金" = 0
            //"約定済数量" = 引数.債券注文.get約定数量()
            //"約定済代金" = 引数.債券注文.get受渡代金(円貨)() * get損益方向()の戻り値
            l_dblExecutedQuantity = l_bondOrderUnitRow.getExecutedQuantity();
            l_dblExecutedAmount = l_bondOrderUnitRow.getEstimatedPrice() * l_intCashDir;
        }
        
        //1.4.(*)"受渡日"を計算する。
        //[a.募集取引の場合]
        // （引数.債券注文.get注文種別() == 401：債券買い注文
        // 　@　@&&
        // 　@　@　@引数.債券注文.get取引() == 35:募集取引）
        Date l_deliveryDate = null;
        if (OrderTypeEnum.BOND_BUY.equals(l_bondOrderUnitRow.getOrderType()) 
            && WEB3DealTypeDef.RECRUIT_TRADING.equals(l_bondOrderUnitRow.getDealType()))
        {
            // 　@"受渡日" = 引数.債券注文.get入金日()
            l_deliveryDate = l_bondOrderUnitRow.getPaymentDate();
        }
        
        // [a.以外の場合]
        // 　@"受渡日" = 引数.債券注文.get受渡日()
        else
        {
            l_deliveryDate = l_bondOrderUnitRow.getDeliveryDate();
        }
        
        Date l_tranDate = WEB3DateUtility.getDate(l_bondOrderUnitRow.getBizDate(), "yyyyMMdd");
        
        //1.5.create取引情報(余力計算条件, ProductTypeEnum, long, FinTransactionType, 
        //Date, double, double, double, double, Date, TaxTypeEnum)
        //取引情報オブジェクトを生成する。
        //[引数]
        //余力計算条件 = this.get余力計算条件()
        //ProductTypeEnum = 引数.債券注文.get銘柄タイプ()
        //long = 引数.債券注文.get銘柄ID()
        //FinTransactionType = 引数.債券注文.get注文種別().toFinTransactionType()
        //Date = 引数.債券注文.get発注日()
        //double = "未約定数量"
        //double = "未約定代金"
        //double = "約定済数量"
        //double = "約定済代金"
        //Date = "受渡日"
        //TaxTypeEnum = 引数.債券注文.get税区分()
        WEB3TPTransactionReflector l_tpTransaction =  WEB3TPTransactionReflector.create
        (
            getCalcCondition(),
            l_bondOrderUnitRow.getProductType(),
            l_bondOrderUnitRow.getProductId(),
            l_tranType,
            l_tranDate,
            l_dblUnexecutedQuantity, 
            l_dblUnexecutedAmount,
            l_dblExecutedQuantity, 
            l_dblExecutedAmount,
            l_deliveryDate,
            l_bondOrderUnitRow.getTaxType()
        );
        
        //1.6.(*)返却値：create取引情報()の戻り値
        log.exiting(STR_METHOD_NAME);
        return l_tpTransaction;
    }

    /**
     * (create取引情報)<BR> 
     * <BR>
     * 引数.IPO申告（IpoOrderRow）から取引情報を作成する。<BR>
     * <BR>
     * ※シーケンス図「(取引代金)create取引情報(IpoOrderRow,IpoProductRow)」参照 <BR>
     * <BR>
     * @@param l_ipoOrderRow - (IPO申告)
     * @@param l_ipoProductRow - (IPO銘柄情報)
     * @@return WEB3TPTransactionReflector
     */
    protected WEB3TPTransactionReflector createWEB3TPTransactionReflector(
            IpoOrderRow l_ipoOrderRow,IpoProductRow l_ipoProductRow)
    {
        final String STR_METHOD_NAME = "createWEB3TPTransactionReflector(IpoOrderRow,IpoProductRow)";
        log.entering(STR_METHOD_NAME);
        
        //当日の取得
        Date l_datT0 = getCalcCondition().getBizDate(0);
        //T+5の取得
        Date l_datT5 = getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5);

        int l_intCashDir = WEB3TPTransactionReflector.getCashDir(FinTransactionType.OTHER);

        //1.1 (*)"未約定数量","約定済数量"を計算する。
        //未約定数量
        double l_dblUnexecutedQuantity = 0.0d;
        
        //約定数量
        double l_dblExecutedQuantity = 0.0d;
        
        //未約定代金 = 0
        double l_dblUnexecutedAmount = 0.0d;
        
        //約定済代金 = 0
        double l_dblExecutedAmount = 0.0d;
    	
        //未処理の場合
        //（引数.IPO注文.get受付状態() == 0：未処理）
        // 　@"未約定数量" = 引数.IPO注文.get購入申込数量()
        // 　@"未約定代金" = 引数.IPO注文.get購入申込数量() * 引数.IPO注文.get購入申込代金()
        // 　@"約定済数量" = 0
        // 　@"約定済代金" = 0
        if (WEB3TPIPOHostAcceptStatusDef.DEFAULT.equals(l_ipoOrderRow.getAcceptStatus()))
        {
        	l_dblUnexecutedQuantity = l_ipoOrderRow.getApplicationQuantity();
        	l_dblUnexecutedAmount = l_ipoOrderRow.getPayAmount() * l_intCashDir;
        	l_dblExecutedQuantity = 0.0d;
        	l_dblExecutedAmount = 0.0d;
        }
        //SONAR受付済の場合
        //（引数.IPO注文.get受付状態() == 0：未処理）
        // 　@"未約定数量" = 0
        // 　@"未約定代金" = 0
        // 　@"約定済数量" = 引数.IPO注文.get購入申込数量()
        // 　@"約定済代金" = 引数.IPO注文.get購入申込数量() * 引数.IPO注文.get購入申込代金()
        else if(WEB3TPIPOHostAcceptStatusDef.ACCEPTED.equals(l_ipoOrderRow.getAcceptStatus()))
        {
        	l_dblUnexecutedAmount = 0.0d;
        	l_dblUnexecutedQuantity = 0.0d;
        	l_dblExecutedQuantity = l_ipoOrderRow.getApplicationQuantity();
        	l_dblExecutedAmount = l_ipoOrderRow.getPayAmount() * l_intCashDir;
        }
        
        //受渡日の算出
        Date l_dblOfferEndDateProspec = l_ipoProductRow.getOfferEndDateProspec();

        //受渡日がT+5以降の場合は受渡日をT+5に設定する。
        if(WEB3DateUtility.compareToDay(l_datT5, l_dblOfferEndDateProspec) < 0)
        {
        	l_dblOfferEndDateProspec = l_datT5;
        }

    	WEB3TPTransactionReflector l_tpTransaction =  WEB3TPTransactionReflector.create
        (
                getCalcCondition(),
                l_ipoProductRow.getProductType(),
                l_ipoOrderRow.getIpoProductId(),
                FinTransactionType.OTHER,
                l_datT0,
                l_dblUnexecutedQuantity, 
                l_dblUnexecutedAmount,
                l_dblExecutedQuantity, 
                l_dblExecutedAmount,
                l_dblOfferEndDateProspec,
                l_ipoOrderRow.getTaxType()
        );
        
        //1.6.(*)返却値：create取引情報()の戻り値
        log.exiting(STR_METHOD_NAME);
        return l_tpTransaction;    		
    }
    
    /**
     * (get約定済受渡代金合計)<BR>
     * 引数.株式注文に対する株式トランザクションの受渡代金を合計し返却する。<BR>
     * @@param l_row　@株式注文
     * @@return double
     */
    protected double getNetAmountTotal(EqtypeOrderUnitRow l_row)
    {   
        double l_dblExecutedAmount = 0.0d;
        
        //未約定の場合DB検索しない。
        if(l_row.getExecutedQuantity() == 0)
        {
            return l_dblExecutedAmount;
        }
        
        List l_finTranRows = WEB3TPPersistentDataManager.getInstance().getEqtypeFinTransactions(l_row);
        if (l_finTranRows != null)
        {
            for (Iterator l_iter = l_finTranRows.iterator(); l_iter.hasNext(); )
            {
                EqtypeFinTransactionRow l_finTranRow = (EqtypeFinTransactionRow) l_iter.
                next();
                l_dblExecutedAmount += l_finTranRow.getNetAmount();
                
            }
        }
        
        return l_dblExecutedAmount;
        
    }
    
    /**
     * (get約定済受渡代金合計)<BR>
     * 引数.オプション注文に対するオプショントランザクションの受渡代金を合計し返却する。<BR>
     * @@param l_row　@オプション注文
     * @@return double
     */
    protected double getNetAmountTotal(IfoOrderUnitRow l_row)
    {
        double l_dblExecutedAmount = 0.0d;
        
        //未約定の場合DB検索しない。
        if(l_row.getExecutedQuantity() == 0)
        {
            return l_dblExecutedAmount;
        }
        
        List l_finTranRows = WEB3TPPersistentDataManager.getInstance().getIfoFinTransactions(l_row);
        if (l_finTranRows != null)
        {
            for (Iterator l_iter = l_finTranRows.iterator(); l_iter.hasNext(); )
            {
                IfoFinTransactionRow l_finTranRow = (IfoFinTransactionRow) l_iter.next();
                l_dblExecutedAmount += l_finTranRow.getNetAmount();
            }
        }
        return l_dblExecutedAmount;
        
    }
    
    /**
     * (get約定済受渡代金合計)<BR>
     * 引数.外株注文に対する外株トランザクションの受渡代金を合計し返却する。<BR>
     * @@param l_row　@外株注文
     * @@return double
     */
    protected double getNetAmountTotal(FeqOrderUnitRow l_row)
    {
        double l_dblExecutedAmount = 0.0d;
        
        //未約定の場合DB検索しない。
        if(l_row.getExecutedQuantity() == 0)
        {
            return l_dblExecutedAmount;
        }
        
        List l_finTranRows = WEB3TPPersistentDataManager.getInstance().getFeqFinTransactions(l_row);
        if (l_finTranRows != null)
        {
            for (Iterator l_iter = l_finTranRows.iterator(); l_iter.hasNext(); )
            {
                FeqFinTransactionRow l_finTranRow = (FeqFinTransactionRow) l_iter.next();
                l_dblExecutedAmount += l_finTranRow.getNetAmount();
            }
        }
        return l_dblExecutedAmount;
        
    }

    /**
     * (calc翌日出金額)<BR>
     * 翌日の出金額を集計し返却する <BR>
     * <BR>
     * １）出金注文一覧<余力計算結果詳細用>より、約定済代金を集計する。 <BR>
     * <BR>
     * 　@<LOOP処理：this.出金注文一覧<余力計算結果詳細用>の要素数回> <BR>
     * <BR>
     * 　@　@①@出金注文一覧<余力計算結果詳細用>より、取引情報オブジェクトを取得する。 <BR>
     * 　@　@　@-this.出金注文一覧<余力計算結果詳細用>.get(index)をコール <BR>
     * 　@　@ <BR>
     * 　@　@②取得した取引情報オブジェクトより約定済代金を集計する。 <BR>
     * <BR>
     * 　@　@　@[取引情報オブジェクト.getトランザクションタイプ() = "20：出金" かつ <BR>
     * 　@　@　@取引情報オブジェクト.get受渡日() = T+1　@ かつ <BR>
     * 　@　@　@取引情報オブジェクト.getトランザクション発生日()　@= T+0 <BR>
     * 　@　@　@の場合] <BR>
     * 　@　@　@　@ <BR>
     * 　@　@　@　@約定済代金集計 += 取引情報オブジェクト.get約定済代金() <BR>
     * <BR>
     * <BR>
     * ２）集計した約定済代金を返却する。 <BR>
     * @@return double
     */
    public double clacNextBizDateCashoutAmount()
    {
        final String STR_METHOD_NAME = "clacNextBizDateCashoutAmount()";
        log.entering(STR_METHOD_NAME);
        double l_dblExecutedAmount = 0;
        //１）出金注文一覧<余力計算結果詳細用>より、約定済代金を集計する。
        List l_lisDisplayCashOutTransactions = getDisplayCashOutTransactions();
        for (Iterator l_iter = l_lisDisplayCashOutTransactions.iterator(); l_iter.hasNext();)
        {
            //<LOOP処理：this.出金注文一覧<余力計算結果詳細用>の要素数回>
            //-this.出金注文一覧<余力計算結果詳細用>.get(index)をコール
            WEB3TPTransactionReflector l_transactionReflector = (WEB3TPTransactionReflector)l_iter.next();

            Date l_datBizDate1 = this.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_1);
            Date l_datBizDate0 = this.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0);
            //②取得した取引情報オブジェクトより約定済代金を集計する。
            //[取引情報オブジェクト.getトランザクションタイプ() = "20：出金" かつ
            //　@　@　@取引情報オブジェクト.get受渡日() = T+1　@ かつ
            //　@　@　@取引情報オブジェクト.getトランザクション発生日()　@= T+0 の場合]
            //　@　@　@　@約定済代金集計 += 取引情報オブジェクト.get約定済代金()
            if (FinTransactionType.DEBIT.equals(l_transactionReflector.getFinTransactionType())
                && WEB3DateUtility.compareToDay(
                    l_transactionReflector.getDeliveryDate(), l_datBizDate1) == 0
                && WEB3DateUtility.compareToDay(
                    l_transactionReflector.getFinTransactionDate(), l_datBizDate0) == 0)
            {
                l_dblExecutedAmount = l_dblExecutedAmount + l_transactionReflector.getExecutedAmount();
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_dblExecutedAmount;
    }
    
    /**
     * (calc当日以降預り金保証金間の振替額)<BR>
     * アイテムの定義<BR>
     * 預り金保証金間の振替額を集計し結果を返却する。 <BR>
     * １）　@当日以降振替注文を取得する。 <BR>
     * 　@当日以降振替注文・・・余力データソースアクセス管理.get当日以降振替注文(資産評価) <BR>
     * <BR>
     * [引数] <BR>
     * 資産評価 = this <BR>
     * <BR>
     * ２）　@約定済代金を集計する。 <BR>
     *  集計条件：<BR>
     *  AIO注文単位Row.補助口座ID = this.get顧客属性.get補助口座ID(補助口座タイプ：1:株式取引口座（預り金）) 且つ <BR>
     *  AIO注文単位Row.受渡日 <= システム日付（翌営業日） 且つ <BR>
     *  AIO注文単位Row.注文種別.toFinTransactionType() in ( ( <BR>
     *  1005：振替注文(預り金から信用保証金)　@, <BR>
     *  1006：振替注文(信用保証金から預り金)　@) <BR>
     *  約定済代金 = 約定済代金 + AIO注文単位Row.注文数量 * 損益方向<BR>
     * <BR>
     *      (*)<BR>
     *      損益方向　@= 取引情報.get損益方向(振替タイプ：AIO注文単位Row.振替タイプ)<BR>
     *      AIO注文単位Row　@= get当日以降振替注文()の戻り値の要素オブジェクト<BR>
     * <BR>
     * ３）　@約定済代金(n)を返却する。<BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    public double calcTodayMarginDepositTransferAmount() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcTodayMarginDepositTransferAmount()";
        log.entering(STR_METHOD_NAME);
        double l_dblTodayMarginDepositTransferAmount = 0;
        //１）　@当日以降振替注文を取得する。
        //　@当日以降振替注文・・・余力データソースアクセス管理.get当日以降振替注文(資産評価)
        List l_lisTodaysTransactions =
            WEB3TPPersistentDataManager.getInstance().getTodaysCashTransferOrders(this);
        for (Iterator l_iter = l_lisTodaysTransactions.iterator(); l_iter.hasNext();)
        {
            AioOrderUnitRow l_aioOrderUnitRow = (AioOrderUnitRow)l_iter.next();
            //２）　@約定済代金を集計する。
            //約定済代金(n) = Σ(*)取引情報.get約定済代金()
            //集計条件：
            //AIO注文単位Row.補助口座ID = this.get顧客属性.get補助口座ID(補助口座タイプ：1:株式取引口座（預り金）) 且つ
            //AIO注文単位Row.受渡日 <= システム日付（翌営業日） 且つ
            //AIO注文単位Row.注文種別.toFinTransactionType() in (
            //1005：振替注文(預り金から信用保証金)　@,
            //1006：振替注文(信用保証金から預り金)　@)
            //約定済代金 = 約定済代金 + AIO注文単位Row.注文数量 * 損益方向
            //損益方向　@= 取引情報.get損益方向(振替タイプ：AIO注文単位Row.振替タイプ)
            //AIO注文単位Row　@= get当日以降振替注文()の戻り値の要素オブジェクト
            Date l_datOrderDate =
                this.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_1);
            FinTransactionType l_finTransactionType =
                l_aioOrderUnitRow.getOrderType().toFinTransactionType();
            long l_lngSubAccountId =
                this.getAccountInfo().getSubAccountId(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            if(l_lngSubAccountId == l_aioOrderUnitRow.getSubAccountId()
                && (FinTransactionType.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE.equals(l_finTransactionType)
                || FinTransactionType.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_finTransactionType))
                && WEB3DateUtility.compareToDay(l_aioOrderUnitRow.getDeliveryDate(), l_datOrderDate) <= 0)
            {
                l_dblTodayMarginDepositTransferAmount =
                    l_dblTodayMarginDepositTransferAmount
                    + l_aioOrderUnitRow.getQuantity() * WEB3TPTransactionReflector.getCashDir(
                        l_aioOrderUnitRow.getTransferType());
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_dblTodayMarginDepositTransferAmount;
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
        .append("todaysTransactions", getTodaysTransactions())
        .append("fixedTransactions", getFixedTransactions())
        .append("mutualFundTransactions", getMutualFundTransactions())
        .append("displayCashOutTransactions", getDisplayCashOutTransactions())
        .appendSuper(super.toString())
        .toString();
    }
    
    
    public boolean isCashOutOverT5Loaded()
    {
        
        return true;        
    }
    
}@
