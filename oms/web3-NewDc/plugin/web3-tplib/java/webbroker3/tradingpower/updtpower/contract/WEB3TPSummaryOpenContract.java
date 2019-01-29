head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.04.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSummaryOpenContract.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 未決済建玉の集計(WEB3TPSummaryOpenContract.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/02 齋藤　@栄三 (FLJ) 新規作成
*/
package webbroker3.tradingpower.updtpower.contract;

import webbroker3.tradingpower.util.ToStringUtils;


/**
 * (未決済建玉の集計)
 */
public class WEB3TPSummaryOpenContract extends WEB3TPContractBase 
{
    
    /**
     * (発注分建玉代金)
     */
    private double unExecContractAmount;
    
    /**
     * (発注分必要保証金)
     */
    private double unExecMarginDeposit;
    
    /**
     * (発注分現金必要保証金)
     */
    private double unExecCashMarginDeposit;
    
    /**
     * (未決済建玉評価損)
     */
    private double assetLoss;
    
    /**
     * (未決済建玉評価益)
     */
    private double assetProfit;
    
    /**
     * (建手数料)
     */
    private double setupFee;
    
    /**
     * (日歩・逆日歩損)
     */
    private double interestLoss;
    
    /**
     * (日歩・逆日歩益)
     */
    private double interestProfit;
    
    /**
     * (その他建玉諸経費)
     */
    private double otherCost;
    
    /**
     * @@roseuid 4104AE3E034B
     */
    public WEB3TPSummaryOpenContract() 
    {
     
    }
    
    /**
     * (create未決済建玉の集計)
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPSummaryOpenContract
     * @@roseuid 4100F0E100EA
     */
    public static WEB3TPSummaryOpenContract create() 
    {
        return new WEB3TPSummaryOpenContract();
    }
    
    /**
     * (get発注分建玉代金)<BR>
     * 発注分建玉代金を取得する。<BR>
     * @@return double
     */
    public double getUnExecContractAmount() 
    {
        return unExecContractAmount;
    }
    
    /**
     * (set発注分建玉代金)<BR>
     * 引数の発注分建玉代金をセットする。<BR>
     * @@param l_dblUnExecContractAmount - (発注分建玉代金)
     */
    public void setUnExecContractAmount(double l_dblUnExecContractAmount) 
    {
        unExecContractAmount = l_dblUnExecContractAmount;
    }
    
    /**
     * (get発注分必要保証金)<BR>
     * 発注分必要保証金を取得する。<BR>
     * @@return double
     */
    public double getUnExecMarginDeposit() 
    {
        return unExecMarginDeposit;
    }
    
    /**
     * (set発注分必要保証金)<BR>
     * 引数の発注分必要保証金をセットする。<BR>
     * @@param l_dblUnExecMarginDeposit - (発注分必要保証金)
     */
    public void setUnExecMarginDeposit(double l_dblUnExecMarginDeposit) 
    {
        unExecMarginDeposit = l_dblUnExecMarginDeposit;
    }
    
    /**
     * (get発注分現金必要保証金)<BR>
     * 発注分現金必要保証金を取得する。<BR>
     * @@return double
     */
    public double getUnExecCashMarginDeposit() 
    {
        return unExecCashMarginDeposit;
    }
    
    /**
     * (set発注分現金必要保証金)<BR>
     * 引数の発注分現金必要保証金をセットする。<BR>
     * @@param l_dblUnExecCashMarginDeposit - (発注分現金必要保証金)
     */
    public void setUnExecCashMarginDeposit(double l_dblUnExecCashMarginDeposit) 
    {
        unExecCashMarginDeposit = l_dblUnExecCashMarginDeposit;
    }
    
    /**
     * (get未決済建玉評価損)<BR>
     * 未決済建玉評価損を取得する。<BR>
     * @@return double
     */
    public double getAssetLoss() 
    {
        return assetLoss;
    }
    
    /**
     * (set未決済建玉評価損)<BR>
     * 引数の未決済建玉評価損をセットする。<BR>
     * @@param l_dblAssetLoss - (未決済建玉評価損)
     */
    public void setAssetLoss(double l_dblAssetLoss) 
    {
        assetLoss = l_dblAssetLoss;
    }
    
    /**
     * (get未決済建玉評価益)<BR>
     * 未決済建玉評価益を取得する。<BR>
     * @@return double
     */
    public double getAssetProfit() 
    {
        return assetProfit;
    }
    
    /**
     * (set未決済建玉評価益)<BR>
     * 引数の未決済建玉評価益をセットする。<BR>
     * @@param l_dblAssetProfit - (未決済建玉評価益)
     */
    public void setAssetProfit(double l_dblAssetProfit) 
    {
        assetProfit = l_dblAssetProfit;
    }
    
    /**
     * (get建手数料)<BR>
     * 建手数料を取得する。<BR>
     * @@return double
     */
    public double getSetupFee() 
    {
        return setupFee;
    }
    
    /**
     * (set建手数料)<BR>
     * 引数の建手数料をセットする。<BR>
     * @@param l_dblSetupFee - (建手数料)
     */
    public void setSetupFee(double l_dblSetupFee) 
    {
        setupFee = l_dblSetupFee;
    }
    
    /**
     * (get日歩・逆日歩損)<BR>
     * 日歩・逆日歩損を取得する。<BR>
     * @@return double
     */
    public double getInterestLoss() 
    {
        return interestLoss;
    }
    
    /**
     * (set日歩・逆日歩損)<BR>
     * 引数の日歩・逆日歩損をセットする。<BR>
     * @@param l_dblInterestLoss - (日歩・逆日歩損)
     */
    public void setInterestLoss(double l_dblInterestLoss) 
    {
        interestLoss = l_dblInterestLoss;
    }
    
    /**
     * (get日歩・逆日歩益)<BR>
     * 日歩・逆日歩益を取得する。<BR>
     * @@return double
     */
    public double getInterestProfit() 
    {
        return interestProfit;
    }
    
    /**
     * (set日歩・逆日歩益)<BR>
     * 引数の日歩・逆日歩益をセットする。<BR>
     * @@param l_dblInterestProfit - (日歩・逆日歩益)
     */
    public void setInterestProfit(double l_dblInterestProfit) 
    {
        interestProfit = l_dblInterestProfit;
    }
    
    /**
     * (getその他建玉諸経費)<BR>
     * その他建玉諸経費を取得する。<BR>
     * @@return double
     */
    public double getOtherCost() 
    {
        return otherCost;
    }
    
    /**
     * (setその他建玉諸経費)<BR>
     * 引数のその他建玉諸経費をセットする。<BR>
     * @@param l_dblOtherCost - (その他建玉諸経費)
     */
    public void setOtherCost(double l_dblOtherCost) 
    {
        otherCost = l_dblOtherCost;
    }
    
    /**
     * このオブジェクトの文字列表現を返す。
     */
    public String toString() 
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .appendSuper(super.toString())
            .append("unExecContractAmount", getUnExecContractAmount())
            .append("unExecMarginDeposit", getUnExecMarginDeposit())
            .append("unExecCashMarginDeposit", getUnExecCashMarginDeposit())
            .append("assetLoss", getAssetLoss())
            .append("assetProfit", getAssetProfit())
            .append("setupFee", getSetupFee())
            .append("interestLoss", getInterestLoss())
            .append("interestProfit", getInterestProfit())
            .append("otherCost", getOtherCost())
            .toString();
    }
}
@
