head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.04.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPHistory.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 建玉変動(WEB3TPHistory.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/02 齋藤　@栄三 (FLJ) 新規作成
Revesion History : 2008/01/31 トウ鋒鋼　@(中訊)　@仕様変更　@モデルNo.253
Revesion History : 2008/10/21 安陽　@(中訊)　@仕様変更　@モデルNo325
Revesion History : 2008/10/31 張少傑　@(中訊)　@仕様変更　@モデルNo352
Revesion History : 2009/12/03 張騰宇　@(中訊)　@仕様変更　@モデルNo400
*/
package webbroker3.tradingpower.updtpower.contract;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPContractAmountApplyDateDef;
import webbroker3.tradingpower.define.WEB3TPRestraintDivDef;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.define.WEB3TPSwapContractDepositRestraintDef;
import webbroker3.tradingpower.define.WEB3TPSwapContractProfitlossRestraintDef;
import webbroker3.tradingpower.updtpower.WEB3TPAssetReflector;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3DateUtility;

/**
 * (建玉変動)
 */
public class WEB3TPHistory extends WEB3TPAssetReflector 
{
    
    /**
     * (トランザクションカテゴリ)
     */
    private FinTransactionCateg transactionCateg;
    
    /**
     * (約定済フラグ)
     */
    private boolean executedFlag;
    
    /**
     * (トランザクション発生日)
     */
    private Date transactionDate;
    
    /**
     * (単価)
     */
    private double price;
    
    /**
     * (株数)
     */
    private double quantity;
    
    /**
     * (受渡代金)
     */
    private double netAmount;
    
    /**
     * (受渡日)
     */
    private Date deliveryDate;
    
    /**
     * (対象建玉)
     */
    private WEB3TPTargetContract targetContract;
  
    /**
     * (注文単位ID)
     */
    private long orderUnitId;

    /**
     * (建玉諸経費)
     */
    private double contractTotalCost = 0;

    /**
     * (保証金率の分母)
     */
    private static final double depositDenominator = 100;
    
    /**
     * @@roseuid 4104AB46030D
     */
    public WEB3TPHistory() 
    {
     
    }
    
    /**
     * (create建玉変動)<BR>
     * 建玉変動を生成する。<BR>
     * @@param l_targetContract - (対象建玉)
     * @@param l_calcCondition - (余力計算条件)
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPHistory
     * @@roseuid 40DC0C290151
     */
    public static WEB3TPHistory create(WEB3TPTargetContract l_targetContract, WEB3TPCalcCondition l_calcCondition) 
    {
        WEB3TPHistory l_thisInstance = new WEB3TPHistory();
        l_thisInstance.setTargetContract(l_targetContract);
        l_thisInstance.setCalcCondition(l_calcCondition);
        return l_thisInstance;
    }
    
    /**
     * (getトランザクションカテゴリ)<BR>
     * トランザクションカテゴリを取得する。<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg
     * @@roseuid 40DBE42D030D
     */
    public FinTransactionCateg getTransactionCateg() 
    {
        return transactionCateg;
    }
    
    /**
     * (setトランザクションカテゴリ)<BR>
     * 引数のトランザクションカテゴリをセットする。<BR>
     * @@param l_transactionCateg - (トランザクションカテゴリ)
     * @@roseuid 40DBE4300271
     */
    public void setTransactionCateg(FinTransactionCateg l_transactionCateg) 
    {
        transactionCateg = l_transactionCateg;
    }
    
    /**
     * (is約定済)<BR>
     * 約定済か判定する。<BR>
     * @@return boolean
     * @@roseuid 40DBE43E00EA
     */
    public boolean isExecuted() 
    {
        return executedFlag;
    }
    
    /**
     * (set約定済フラグ)<BR>
     * 引数の約定済フラグをセットする。<BR>
     * @@param l_isExecuted - (約定済フラグ)
     * @@roseuid 40DBE44002DE
     */
    public void setExecuted(boolean l_isExecuted) 
    {
        executedFlag = l_isExecuted;
    }
    
    /**
     * (getトランザクション発生日)<BR>
     * トランザクション発生日を取得する。<BR>
     * @@return java.util.Date
     * @@roseuid 40DBE44B008C
     */
    public Date getTransactionDate() 
    {
        return transactionDate;
    }
    
    /**
     * (setトランザクション発生日)<BR>
     * 引数のトランザクション発生日をセットする。<BR>
     * @@param l_datTransaction - (トランザクション発生日)
     * @@roseuid 40DBE44E005D
     */
    public void setTransactionDate(Date l_datTransaction) 
    {
        transactionDate = l_datTransaction;
    }
    
    /**
     * (get単価)<BR>
     * 単価を取得する。<BR>
     * @@return double
     * @@roseuid 40DBE45402FD
     */
    public double getPrice() 
    {
        return price;
    }
    
    /**
     * (set単価)<BR>
     * 引数の単価をセットする。<BR>
     * @@param l_dblPrice - (単価)
     * @@roseuid 40DBE456031C
     */
    public void setPrice(double l_dblPrice) 
    {
        price = l_dblPrice;
    }
    
    /**
     * (get株数)<BR>
     * 株数を取得する。<BR>
     * @@return double
     * @@roseuid 40DC0DF402F7
     */
    public double getQuantity() 
    {
        return quantity;
    }
    
    /**
     * (set株数)<BR>
     * 引数の株数をセットする。<BR>
     * @@param l_dblQuantity - (株数)
     * @@roseuid 40DC0DF70316
     */
    public void setQuantity(double l_dblQuantity) 
    {
        quantity = l_dblQuantity;
    }
    
    /**
     * (get受渡代金)<BR>
     * 受渡代金を取得する。<BR>
     * @@return double
     * @@roseuid 40DBF1B202DE
     */
    public double getNetAmount() 
    {
        return netAmount;
    }
    
    /**
     * (set受渡代金)<BR>
     * 引数の受渡代金をセットする。<BR>
     * @@param l_dblNetAmount - (受渡代金)
     * @@roseuid 40DBF21100BB
     */
    public void setNetAmount(double l_dblNetAmount) 
    {
        netAmount = l_dblNetAmount;
    }
    
    /**
     * (get受渡日)<BR>
     * 受渡日を取得する。<BR>
     * @@return java.util.Date
     * @@roseuid 40E0237002FF
     */
    public Date getDeliveryDate() 
    {
        return deliveryDate;
    }
    
    /**
     * (set受渡日)<BR>
     * 引数の受渡日をセットする。<BR>
     * @@param l_datDelivery - (受渡日)
     * @@roseuid 40E02370032E
     */
    public void setDeliveryDate(Date l_datDelivery) 
    {
        deliveryDate = l_datDelivery;
    }

    /**
     * (get対象建玉)<BR>
     * 対象建玉を取得する。<BR>
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPTargetContract
     */
    public WEB3TPTargetContract getTargetContract() 
    {
        return targetContract;
    }
    
    /**
     * (set対象建玉)<BR>
     * 引数の対象建玉をセットする。<BR>
     * @@param l_targetContract - (対象建玉)
     */
    public void setTargetContract(WEB3TPTargetContract l_targetContract) 
    {
        targetContract = l_targetContract;
    }

    /**
     * (get注文単位ID)
     * 注文単位IDを取得する。<BR>
     * @@return long
     */
    public long getOrderUnitId()
    {
        return orderUnitId;
    }

    /**
     * (set注文単位ID)
     * 引数の注文単位IDをセットする。<BR>
     * @@param l_lngOrderUnitId - (注文単位ID)
     */
    public void setOrderUnitId(long l_lngOrderUnitId)
    {
        orderUnitId = l_lngOrderUnitId;
    }

    /**
     * (get建玉諸経費)
     * 「建玉諸経費」を返却する。<BR>
     * @@return double
     */
    public double getContractTotalCost()
    {
        return contractTotalCost;
    }

    /**
     * (set建玉諸経費)
     * 引数.建玉諸経費をthis.建玉諸経費にセットする。<BR>
     * @@param l_dblContractTotalCost - (建玉諸経費)
     */
    public void setContractTotalCost(double l_dblContractTotalCost)
    {
        contractTotalCost = l_dblContractTotalCost;
    }

    /**
     * (calc建玉代金)<BR>
     * 建玉代金を計算し、計算結果を返す。<BR>
     * <BR>
     * １）建玉代金を計算する。<BR>
     * 　@　@建玉代金＝建玉変動.単価<BR>
     * 　@　@　@　@　@　@　@×　@建玉変動.株数<BR>
     * ２）計算した建玉代金を返す。<BR>
     * <BR>
     * ※計算式に使用する各値の取得方法@<BR>
     * ・建玉変動.単価・・・建玉変動.get単価()<BR>
     * ・建玉変動.株数・・・建玉変動.get株数()<BR>
     * @@return double
     * @@roseuid 40DBF2210271
     */
    public double calcContractAmount() 
    {
        return getPrice() * getQuantity();
    }
    
    /**
     * (calc必要保証金)<BR>
     * 必要保証金を計算し、計算結果を返す。<BR>
     * <BR>
     * １）必要保証金を計算する。<BR>
     * 　@　@必要保証金＝建玉変動.単価<BR>
     * 　@　@　@　@　@　@　@　@×　@建玉変動.株数<BR>
     * 　@　@　@　@　@　@　@　@×　@引数.保証金率 / 100<BR>
     * <BR>
     * ２）計算した必要保証金を返す。<BR>
     * <BR>
     * ※計算式に使用する各値の取得方法@<BR>
     * ・建玉変動.単価・・・建玉変動.get単価()<BR>
     * ・建玉変動.株数・・・建玉変動.get株数()<BR>
     * @@param l_dblMarginDepositRate - (保証金率)
     * @@return double
     * @@roseuid 40DBF22C0109
     */
    public double calcMarginDeposit(double l_dblMarginDepositRate) 
    {
        return calcContractAmount() * l_dblMarginDepositRate / depositDenominator;
    }
    
    /**
     * (calc現金必要保証金)<BR>
     * 現金必要保証金を計算し、計算結果を返す。<BR>
     * <BR>
     * １）現金必要保証金を計算する。<BR>
     * 　@　@現金必要保証金＝建玉変動.単価<BR>
     * 　@　@　@　@　@　@　@　@　@　@×　@建玉変動.株数<BR>
     * 　@　@　@　@　@　@　@　@　@　@×　@引数.現金保証金率 / 100<BR>
     * <BR>
     * ２）計算した現金必要保証金を返す。<BR>
     * <BR>
     * ※計算式に使用する各値の取得方法@<BR>
     * ・建玉変動.単価・・・建玉変動.get単価()<BR>
     * ・建玉変動.株数・・・建玉変動.get株数()<BR>
     * @@param l_dblCashMarginDepositRate - (現金保証金率)
     * @@return double
     * @@roseuid 40DBF23502CE
     */
    public double calcCashMarginDeposit(double l_dblCashMarginDepositRate) 
    {
        return calcContractAmount() * l_dblCashMarginDepositRate / depositDenominator;
    }
    
    /**
     * (calc決済益)<BR>
     * 決済益を計算し、計算結果を返す。<BR>
     * <BR>
     * １）決済益を計算する。<BR>
     * 　@－建玉変動.受渡代金＞0 の場合<BR>
     * 　@　@・決済益＝建玉変動.受渡代金<BR>
     * 　@－建玉変動.受渡代金＝＜0 の場合<BR>
     * 　@　@・決済益＝0<BR>
     * <BR>
     * ２）計算した決済益を返す。<BR>
     * <BR>
     * ※計算式に使用する各値の取得方法@<BR>
     * ・建玉変動.受渡代金・・・建玉変動.get受渡代金()<BR>
     * @@return double
     * @@roseuid 40E134A100AD
     */
    public double calcContractProfit() 
    {
        double l_dblContractProfit;
        if(getNetAmount() > 0)
        {
            l_dblContractProfit = getNetAmount();
        }
        else
        {
            l_dblContractProfit = 0;
        }
        
        return l_dblContractProfit;
    }
    
    /**
     * (calc決済損) <BR>
     * 決済損を計算し、計算結果を返す。<BR>
     * <BR>
     * １）決済損を計算する。<BR>
     * 　@－建玉変動.受渡代金＜0 の場合<BR>
     * 　@　@・決済損＝建玉変動.受渡代金 × -1<BR>
     * 　@－建玉変動.受渡代金＞＝0 の場合<BR>
     * 　@　@・決済損＝0<BR>
     * <BR>
     * ２）計算した決済損を返す。<BR>
     * <BR>
     * ※計算式に使用する各値の取得方法@<BR>
     * ・建玉変動.受渡代金・・・建玉変動.get受渡代金()<BR>
     * @@return double
     * @@roseuid 40E134AB02DF
     */
    public double calcContractLoss() 
    {
        double l_dblContractLoss;
        if(getNetAmount() < 0)
        {
            l_dblContractLoss = getNetAmount() * -1;
        }
        else
        {
            l_dblContractLoss = 0;
        }
        
        return l_dblContractLoss;
    }
    
    /**
     * (is未受渡建玉<非日計り返済・現引現渡>)<BR>
     * 未受渡建玉<非日計り返済・現引現渡>か判定する。<BR>
     * <BR>
     * １）以下の条件に該当する場合はtrueを返す。<BR>
     * 　@　@該当しない場合はfalseを返す。<BR>
     * 　@－条件１・・・非日計り返済<BR>
     * 　@　@・トランザクションカテゴリ＝返済<BR>
     * 　@　@　@且つ<BR>
     * 　@　@・約定済フラグ＝済<BR>
     * 　@　@　@且つ<BR>
     * 　@　@・受渡日＞余力計算条件.営業日(T+0)<BR>
     * 　@　@　@且つ<BR>
     * 　@　@・!( 余力計算条件.翌日注文受付開始区分<現物株式>＝未<BR>
     * 　@　@　@　@　@且つ<BR>
     * 　@　@　@　@対象建玉情報.建日＝余力計算条件.営業日(T+0)<BR>
     * 　@　@　@　@　@且つ<BR>
     * 　@　@　@　@トランザクション発生日＝余力計算条件.営業日(T+0)<BR>
     * 　@　@　@　@ )<BR>
     * <BR>
     * 　@－条件２・・・非当日決済現引現渡<BR>
     * 　@　@・トランザクションカテゴリ＝現引現渡<BR>
     * 　@　@　@且つ<BR>
     * 　@　@・約定済フラグ＝済<BR>
     * 　@　@　@且つ<BR>
     * 　@　@・受渡日＞余力計算条件.営業日(T+0)<BR>
     * 　@　@　@且つ<BR>
     * 　@　@・(<BR>
     * 　@　@　@　@( トランザクション発生日＝余力計算条件.営業日(T+0)<BR>
     * 　@　@　@　@　@　@且つ<BR>
     * 　@　@　@　@　@余力計算条件.翌日注文受付開始区分<現物株式>＝終<BR>
     * 　@　@　@　@ )<BR>
     * 　@　@　@　@又は<BR>
     * 　@　@　@　@トランザクション発生日≠余力計算条件.営業日(T+0)<BR>
     * 　@　@　@ )<BR>
     * 　@　@且つ<BR>
     * 　@　@・（<BR>
     * 　@　@　@　@引数.拘束区分＝「0：DEFAULT」<BR>
     * 　@　@　@　@又は<BR>
     * 　@　@　@　@（引数.拘束区分＝「1：必要保証金」<BR>
     * 　@　@　@　@　@且つ<BR>
     * 　@　@　@　@　@余力計算条件．get会社部店別余力計算条件（<BR>
     * 　@　@　@　@　@　@:String = 「現引現渡建玉必要保証金拘束区分」）<BR>
     * 　@　@　@　@　@が「1 ： 現引現渡の約定日から、受渡日前日まで拘束する。」以外<BR>
     * 　@　@　@　@）<BR>
     * 　@　@　@）<BR>
     * @@param l_strRestraintDiv - (拘束区分)<BR>
     * 「0：DEFAULT」<BR>
     * 「1：必要保証金」<BR>
     * @@return boolean
     */
    public boolean isUndeliveredContractNotDayTradeSwap(String l_strRestraintDiv) 
    {
        WEB3TPCalcCondition l_calcCondition = getCalcCondition();
        WEB3TPTargetContract l_targetContract = getTargetContract();

        //対象建玉詳細を取得
        WEB3TPTargetContractDetail l_targetContractDetail = l_targetContract.getTargetContractDetail();
        
        //営業日(T+0)を取得
        Date l_datBizDate0 = l_calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0);
        
        //非日計り返済
        //カテゴリが返済
        if(FinTransactionCateg.EQTYPE_CLOSE_MARGIN.equals(getTransactionCateg()))
        {
            //約定済
            if(isExecuted())
            {
                //営業日(T+0) < 受渡日
                if(WEB3DateUtility.compareToDay(l_datBizDate0, getDeliveryDate()) < 0)
                {
                    //! (翌日注文受付時間帯=未 且つ 営業日(T+0)=建日 且つ 営業日(T+0)=トランザクション発生日)
                    if(! (
                        l_calcCondition.isEquityNextDayOrderStartDiv() == false
                        && WEB3DateUtility.compareToDay(l_datBizDate0, l_targetContractDetail.getOpenDate()) == 0
                        && WEB3DateUtility.compareToDay(l_datBizDate0,getTransactionDate()) == 0))
                    {
                        return true;
                    }
                }
            }
        }
        
        //非当日決済現引現渡
        //カテゴリが現引現渡
        if(FinTransactionCateg.EQTYPE_SWAP_MARGIN.equals(getTransactionCateg()))
        {
            //約定済
            if(isExecuted())
            {
                //営業日(T+0) < 受渡日
                if(WEB3DateUtility.compareToDay(l_datBizDate0, getDeliveryDate()) < 0)
                {
                    //営業日(T+0)=トランザクション発生日且つ余力計算条件.翌日注文受付開始区分<現物株式>＝終
                    //又はトランザクション発生日≠余力計算条件.営業日(T+0)
                    if (((WEB3DateUtility.compareToDay(l_datBizDate0, getTransactionDate()) == 0)
                            && l_calcCondition.isEquityNextDayOrderStartDiv())
                        || (WEB3DateUtility.compareToDay(l_datBizDate0, getTransactionDate()) != 0))
                    {
                        //引数.拘束区分＝「0：DEFAULT」
                        //又は
                        //（引数.拘束区分＝「1：必要保証金」 且つ 余力計算条件．get会社部店別余力計算条件（
                        //    :String = 「現引現渡建玉必要保証金拘束区分」）が「1 ： 現引現渡の約定日から、受渡日前日まで拘束する。」以外
                        String l_strSwapContractDepositRestraint = l_calcCondition.getInstBranCalcCondition(
                                WEB3BranchPreferencesNameDef.SWAP_CONTRACT_DEPOSIT_RESTRAINT);
                        if (WEB3TPRestraintDivDef.DEFAULT.equals(l_strRestraintDiv)
                            || (WEB3TPRestraintDivDef.MARGIN_DEPOSIT.equals(l_strRestraintDiv)
                                && !WEB3TPSwapContractDepositRestraintDef.THREE_DAYS.equals(l_strSwapContractDepositRestraint)))
                        {
                            return true;
                        }
                    }
                }
            }
        }
        
        return false;
    }
    
    /**
     * (is未受渡建玉<日計り返済>)<BR>
     * 日計り返済か判定する。<BR>
     * <BR>
     * １）以下の条件に該当する場合はtrueを返す。<BR>
     * 　@　@該当しない場合はfalseを返す。<BR>
     * 　@－条件・・・日計り返済<BR>
     * 　@　@・トランザクションカテゴリ＝返済<BR>
     * 　@　@　@且つ<BR>
     * 　@　@・約定済フラグ＝済<BR>
     * 　@　@　@且つ<BR>
     * 　@　@・余力計算条件.注文受付開始区分<現物株式>＝未<BR>
     * 　@　@　@且つ<BR>
     * 　@　@・対象建玉情報.建日＝余力計算条件.営業日(T+0)<BR>
     * 　@　@　@且つ<BR>
     * 　@　@・トランザクション発生日＝余力計算条件.営業日(T+0)<BR>
     * @@return boolean
     */
    public boolean isUndeliveredContractDayTrade() 
    {
        WEB3TPCalcCondition l_calcCondition = getCalcCondition();
        WEB3TPTargetContract l_targetContract = getTargetContract();

        //対象建玉詳細を取得
        WEB3TPTargetContractDetail l_targetContractDetail = l_targetContract.getTargetContractDetail();
        
        //営業日(T+0)を取得
        Date l_datBizDate0 = l_calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0);
        
        //日計り返済
        //カテゴリが返済
        if(FinTransactionCateg.EQTYPE_CLOSE_MARGIN.equals(getTransactionCateg()))
        {
            //約定済
            if(isExecuted())
            {
                //翌日注文受付時間帯=未
                if(! l_calcCondition.isEquityNextDayOrderStartDiv())
                {
                    //営業日(T+0)=建日
                    if(WEB3DateUtility.compareToDay(l_datBizDate0, l_targetContractDetail.getOpenDate()) == 0)
                    {
                        //営業日(T+0)=トランザクション発生日
                        if(WEB3DateUtility.compareToDay(l_datBizDate0,getTransactionDate()) == 0)
                        {
                            return true;
                        }
                    }
                }
            }
        }
        
        return false;
    }
    
    /**
     * (is日計り返済・現引現渡建玉)<BR>
     * 日計り返済・現引現渡建玉か判定する。<BR>
     * <BR>
     * １）以下の条件に該当する場合はtrueを返す。<BR>
     * 　@　@該当しない場合はfalseを返す。<BR>
     * 　@－条件１・・・日計り返済<BR>
     * 　@　@・is未受渡建玉<日計り返済>をコールする。<BR>
     * <BR>
     * 　@－条件２・・・未約定現引現渡<BR>
     * 　@　@・トランザクションカテゴリ＝現引現渡<BR>
     * 　@　@　@且つ<BR>
     * 　@　@・約定済フラグ＝未<BR>
     * <BR>
     * 　@－条件３・・・当日決済現引現渡<BR>
     * 　@　@・トランザクションカテゴリ＝現引現渡<BR>
     * 　@　@　@且つ<BR>
     * 　@　@・約定済フラグ＝済<BR>
     * 　@　@　@且つ<BR>
     * 　@　@・余力計算条件.翌日注文受付開始区分<現物株式>＝未<BR>
     * 　@　@　@且つ<BR>
     * 　@　@・トランザクション発生日＝余力計算条件.営業日(T+0)<BR>
     * <BR>
     * 　@－条件４・・・非当日決済現引現渡<BR>
     * 　@　@・トランザクションカテゴリ＝現引現渡<BR>
     * 　@　@　@且つ<BR>
     * 　@　@・約定済フラグ＝済<BR>
     * 　@　@　@且つ<BR>
     * 　@　@・受渡日＞余力計算条件.営業日(T+0)<BR>
     * 　@　@　@且つ<BR>
     * 　@　@・(<BR>
     * 　@　@　@( トランザクション発生日＝余力計算条件.営業日(T+0)<BR>
     * 　@　@　@　@　@　@且つ<BR>
     * 　@　@　@　@　@余力計算条件.翌日注文受付開始区分<現物株式>＝終<BR>
     * 　@　@　@　@ )<BR>
     * 　@　@　@　@又は<BR>
     * 　@　@　@　@トランザクション発生日≠余力計算条件.営業日(T+0)<BR>
     * 　@　@　@)<BR>
     * 　@　@　@且つ<BR>
     * 　@　@　@（<BR>
     * 　@　@　@　@（引数．拘束区分＝「1：必要保証金」<BR>
     * 　@　@　@　@　@且つ<BR>
     * 　@　@　@　@　@余力計算条件．get会社部店別余力計算条件（<BR>
     * 　@　@　@　@　@:String = 「現引現渡建玉必要保証金拘束区分」）<BR>
     * 　@　@　@　@　@＝　@「1 ： 現引現渡の約定日から、受渡日前日まで拘束する。」）<BR>
     * 　@　@　@　@又は<BR>
     * 　@　@　@　@（引数．拘束区分＝「2：評価損益」<BR>
     * 　@　@　@　@　@且つ<BR>
     * 　@　@　@　@　@余力計算条件．get会社部店別余力計算条件（<BR>
     * 　@　@　@　@　@ :String = 「現引現渡建玉評価損益拘束区分」）<BR>
     * 　@　@　@　@　@＝　@「1 ： 現引現渡の約定日から、受渡日前日まで拘束する。」）<BR>
     * 　@　@　@)<BR>
     * @@param l_strRestraintDiv - (拘束区分)<BR>
     * 「0：DEFAULT」<BR>
     * 「1：必要保証金」<BR>
     * 「2：評価損益」<BR>
     * @@return boolean
     */
    public boolean isDayTradeSwap(String l_strRestraintDiv) 
    {
        WEB3TPCalcCondition l_calcCondition = getCalcCondition();

        //日計り返済
        if(isUndeliveredContractDayTrade())
        {
            return true;
        }
        
        //営業日(T+0)を取得
        Date l_datBizDate0 = l_calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0);
        
        //現引現渡
        //カテゴリが現引現渡
        if(FinTransactionCateg.EQTYPE_SWAP_MARGIN.equals(getTransactionCateg()))
        {
            //約定済
            if(isExecuted())
            {
                //翌日注文受付時間帯=未
                if(! l_calcCondition.isEquityNextDayOrderStartDiv())
                {
                    //営業日(T+0)=トランザクション発生日
                    if(WEB3DateUtility.compareToDay(l_datBizDate0,getTransactionDate()) == 0)
                    {
                        return true;
                    }
                }
                //非当日決済現引現渡
                //受渡日＞余力計算条件.営業日(T+0)
                if (WEB3DateUtility.compareToDay(getDeliveryDate(), l_datBizDate0) > 0)
                {
                    //営業日(T+0)=トランザクション発生日 且つ 余力計算条件.翌日注文受付開始区分<現物株式>＝終
                    //又はトランザクション発生日≠余力計算条件.営業日(T+0)
                    if (((WEB3DateUtility.compareToDay(l_datBizDate0, getTransactionDate()) == 0)
                            && l_calcCondition.isEquityNextDayOrderStartDiv())
                        || (WEB3DateUtility.compareToDay(l_datBizDate0, getTransactionDate()) != 0))
                    {
                        //現引現渡建玉必要保証金拘束区分
                        String l_strSwapContractDepositRestraint = l_calcCondition.getInstBranCalcCondition(
                                WEB3BranchPreferencesNameDef.SWAP_CONTRACT_DEPOSIT_RESTRAINT);
                        //現引現渡建玉評価損益拘束区分
                        String l_strSwapContractProfitlossRestraint = l_calcCondition.getInstBranCalcCondition(
                                WEB3BranchPreferencesNameDef.SWAP_CONTRACT_PROFITLOSS_RESTRAINT);

                        //引数.拘束区分＝「1：必要保証金」 且つ 余力計算条件．get会社部店別余力計算条件（
                        //    :String = 「現引現渡建玉必要保証金拘束区分」）＝　@「1 ： 現引現渡の約定日から、受渡日前日まで拘束する。」）
                        //又は
                        //（引数.拘束区分＝「2：評価損益」 且つ 余力計算条件．get会社部店別余力計算条件（
                        //    :String = 「現引現渡建玉評価損益拘束区分」）＝　@「1 ： 現引現渡の約定日から、受渡日前日まで拘束する。」）
                        if ((WEB3TPRestraintDivDef.MARGIN_DEPOSIT.equals(l_strRestraintDiv)
                                &&  WEB3TPSwapContractDepositRestraintDef.THREE_DAYS.equals(l_strSwapContractDepositRestraint))
                            || (WEB3TPRestraintDivDef.PROFITLOSS.equals(l_strRestraintDiv)
                                && WEB3TPSwapContractProfitlossRestraintDef.THREE_DAYS.equals(l_strSwapContractProfitlossRestraint)))
                        {
                            return true;
                        }
                    }
                }
            }
            //未約定
            else
            {
                return true;
            }
        } 

        return false;
    }

    /**
     * (calc変動反映日)<BR>
     * 変動開始日と変動終了日を設定する。<BR>
     * <BR>
     * １）当日建玉代金計上開始日モードにしたがって、<BR>
     * 　@　@当日建玉代金計上開始基準日に0 or 1 or 2 をセットする。<BR>
     * <BR>
     * ２）トランザクションカテゴリ＝新規建の場合<BR>
     * <BR>
     * 　@a）対象建玉詳細.建日＞＝余力計算条件.営業日(T+0) - 当日建玉代金計上開始基準日の場合<BR>
     * 　@　@・変動反映開始日に建日 + 当日建玉代金計上開始基準日をセットする。<BR>
     * <BR>
     * 　@a）対象建玉詳細.建日＜余力計算条件.営業日(T+0) - 当日建玉代金計上開始基準日の場合<BR>
     * 　@　@・変動反映開始日に営業日(T+0)をセットする。<BR>
     * <BR>
     * 　@－変動反映終了日に余力計算条件.営業日(T+5)をセットする。<BR>
     * <BR>
     * <BR>
     * ３）トランザクションカテゴリ＝返済 or 現引現渡の場合<BR>
     * <BR>
     * 　@a）対象建玉詳細.建日＞＝余力計算条件.営業日(T+0) - 当日建玉代金計上開始基準日の場合<BR>
     * 　@　@・変動反映開始日に建日 + 当日建玉代金計上開始基準日をセットする。<BR>
     * <BR>
     * 　@a）対象建玉詳細.建日＜余力計算条件.営業日(T+0) - 当日建玉代金計上開始基準日の場合<BR>
     * 　@　@・変動反映開始日に営業日(T+0)をセットする。<BR>
     * <BR>
     * 　@－変動反映終了日に受渡日-1をセットする。<BR>
     * @@param l_datDate - (受渡日)<BR>
     */
    public void calcReflectDay(Date l_datDate) 
    {
        final String STR_METHOD_NAME = "calcReflectDay(Date l_datDate)";

        //引数がnullの時は、自オブジェクトに設定されてる受渡日を使う
        if(l_datDate == null)
        {
            l_datDate = getDeliveryDate();
        }

        WEB3TPCalcCondition l_calcCondition = getCalcCondition();
        WEB3TPTargetContract l_targetContract = getTargetContract();
        Date l_datBizDate0 = l_calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0);
        Date l_datBizDate5 = l_calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_5);
        Date l_datOpenDate = l_targetContract.getTargetContractDetail().getOpenDate();
        String l_strContractApplyDate = l_calcCondition.getInstBranCalcCondition(WEB3TPCalcCondition.CONTRACTAMOUNT_APPLY_DATE);

        int l_intContractApplyDate = 1;
        //１）当日建玉代金計上開始日モードにしたがって、
        //　@当日建玉代金計上開始基準日に0 or 1 or 2 をセットする。
        if (WEB3TPContractAmountApplyDateDef.DEFAULT.equals(l_strContractApplyDate))
        {
            l_intContractApplyDate = 1;
        }
        else if (WEB3TPContractAmountApplyDateDef.FROM_BIZ_DATE.equals(l_strContractApplyDate))
        {
            l_intContractApplyDate = 0;
        }
        else if (WEB3TPContractAmountApplyDateDef.FROM_T2.equals(l_strContractApplyDate))
        {
            l_intContractApplyDate = 2;
        }

        //２）トランザクションカテゴリ＝新規建の場合
        if (FinTransactionCateg.EQTYPE_OPEN_MARGIN.equals(getTransactionCateg()))
        {
            //a）対象建玉詳細.建日＞＝余力計算条件.営業日(T+0) - 当日建玉代金計上開始基準日の場合
            if (WEB3DateUtility.compareToDay(l_datOpenDate,
                l_calcCondition.rollBizDate(l_datBizDate0, -l_intContractApplyDate)) >= 0)
            {
                //・変動反映開始日に建日 + 当日建玉代金計上開始基準日をセットする。
                setReflectStartDay(l_calcCondition.rollBizDate(l_datOpenDate, l_intContractApplyDate));
            }
            //a）対象建玉詳細.建日＜余力計算条件.営業日(T+0) - 当日建玉代金計上開始基準日の場合
            else
            {
                //・変動反映開始日に営業日(T+0)をセットする。
                setReflectStartDay(l_datBizDate0);
            }
            //－変動反映終了日に余力計算条件.営業日(T+5)をセットする。
            setReflectEndDay(l_datBizDate5);
        }
        //３）トランザクションカテゴリ＝返済 or 現引現渡の場合
        else if (FinTransactionCateg.EQTYPE_CLOSE_MARGIN.equals(getTransactionCateg())
            || FinTransactionCateg.EQTYPE_SWAP_MARGIN.equals(getTransactionCateg()))
        {
            //a）対象建玉詳細.建日＞＝余力計算条件.営業日(T+0) - 当日建玉代金計上開始基準日の場合
            if (WEB3DateUtility.compareToDay(l_datOpenDate,
                l_calcCondition.rollBizDate(l_datBizDate0, -l_intContractApplyDate)) >= 0)
            {
                //・変動反映開始日に建日 + 当日建玉代金計上開始基準日をセットする。
                setReflectStartDay(l_calcCondition.rollBizDate(l_datOpenDate, l_intContractApplyDate));
            }
            //a）対象建玉詳細.建日＜余力計算条件.営業日(T+0) - 当日建玉代金計上開始基準日の場合
            else
            {
                //・変動反映開始日に営業日(T+0)をセットする。
                setReflectStartDay(l_datBizDate0);
            }
            //－変動反映終了日に受渡日-1をセットする。
            setReflectEndDay(l_calcCondition.rollBizDate(l_datDate, -1));
        }
        else
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80025, this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    }

    /**
     * このオブジェクトの文字列表現を返す。
     */
    public String toString()
    {
        String l_datTransactionDate = WEB3DateUtility.formatDate(getTransactionDate(), "yyyy/MM/dd HH:mm:ss");
        String l_datDeliveryDate = WEB3DateUtility.formatDate(getDeliveryDate(), "yyyy/MM/dd");
        
        return ToStringUtils
            .newToStringBuilder(this)
            .appendSuper(super.toString())
            .append("transactionCateg", getTransactionCateg())
            .append("executedFlag", isExecuted())
            .append("transactionDate", l_datTransactionDate)
            .append("price", getPrice())
            .append("quantity", getQuantity())
            .append("netAmount", getNetAmount())
            .append("deliveryDate", l_datDeliveryDate)
            .append("orderUnitId", getOrderUnitId())
            .toString();
    }
}
@
