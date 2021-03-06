head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.04.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSummaryDayTradeSwapContract.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 日計り返済・現引現渡建玉の集計(WEB3TPSummaryDayTradeSwapContract.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/02 齋藤　@栄三 (FLJ) 新規作成
Revesion History : 2008/01/31 トウ鋒鋼　@(中訊)　@仕様変更　@モデルNo.257
*/
package webbroker3.tradingpower.updtpower.contract;

import webbroker3.tradingpower.util.ToStringUtils;


/**
 * (日計り返済・現引現渡建玉の集計)
 */
public class WEB3TPSummaryDayTradeSwapContract extends WEB3TPContractBase 
{
    
    /**
     * (現引現渡建玉評価損)
     */
    private double swapContractAssetLoss;
    
    /**
     * (現引現渡建玉評価益)
     */
    private double swapContractAssetProfit;

    /**
     * (現引現渡建玉決済損)
     */
    private double swapContractSettleLoss;

    /**
     * @@roseuid 4104AE400177
     */
    public WEB3TPSummaryDayTradeSwapContract() 
    {
     
    }
    
    /**
     * (create日計り返済・現引現渡建玉の集計)
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPSummaryDayTradeSwapContract
     * @@roseuid 4100F15A0157
     */
    public static WEB3TPSummaryDayTradeSwapContract create() 
    {
        return new WEB3TPSummaryDayTradeSwapContract();
    }
    
    /**
     * (get現引現渡建玉評価損) <BR>
     * <BR>
     * this.現引現渡建玉評価損を返却する。 <BR>
     * <BR>
     * @@return double
     */
    public double getSwapContractAssetLoss()
    {
        return swapContractAssetLoss;
    }

    /**
     * (set現引現渡建玉評価損) <BR>
     * <BR>
     * 引数.現引現渡建玉評価損を、 <BR>
     * this.現引現渡建玉評価損にセットする。 <BR>
     * <BR>
     * @@param l_dblSwapContractAssetLoss - (現引現渡建玉評価損)
     */
    public void setSwapContractAssetLoss(double l_dblSwapContractAssetLoss)
    {
        this.swapContractAssetLoss = l_dblSwapContractAssetLoss;
    }

    /**
     * (get現引現渡建玉評価益) <BR>
     * <BR>
     * this.現引現渡建玉評価益を返却する。 <BR>
     * <BR>
     * @@return double
     */
    public double getSwapContractAssetProfit()
    {
        return swapContractAssetProfit;
    }

    /**
     * (set現引現渡建玉評価益) <BR>
     * <BR>
     * 引数.現引現渡建玉評価益を、<BR> 
     * this.現引現渡建玉評価益にセットする。 <BR>
     * <BR>
     * @@param l_dblSwapContractAssetProfit - (現引現渡建玉評価益)
     */
    public void setSwapContractAssetProfit(double l_dblSwapContractAssetProfit)
    {
        this.swapContractAssetProfit = l_dblSwapContractAssetProfit;
    }

    /**
     * (get現引現渡建玉決済損)<BR>
     * this.現引現渡建玉決済損を返却する。<BR>
     * @@return double
     */
    public double getSwapContractSettleLoss()
    {
        return swapContractSettleLoss;
    }

    /**
     * (set現引現渡建玉決済損)<BR>
     * 引数.現引現渡建玉決済損を、this.現引現渡建玉決済損にセットする。<BR>
     * @@param l_dblTradeSwapContractLoss - (現引現渡建玉決済損)
     */
    public void setSwapContractSettleLoss(double l_dblSwapContractSettleLoss)
    {
        this.swapContractSettleLoss = l_dblSwapContractSettleLoss;
    }

    /**
     * このオブジェクトの文字列表現を返す。
     */
    public String toString() 
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .appendSuper(super.toString())
            .append("swapContractAssetLoss", getSwapContractAssetLoss())
            .append("swapContractAssetProfit", getSwapContractAssetProfit())
            .toString();
    }
}
@
