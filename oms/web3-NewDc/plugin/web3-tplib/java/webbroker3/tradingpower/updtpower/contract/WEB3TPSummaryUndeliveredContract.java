head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.05.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSummaryUndeliveredContract.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 未受渡建玉の集計(WEB3TPSummaryUndeliveredContract.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/02 齋藤　@栄三 (FLJ) 新規作成
*/
package webbroker3.tradingpower.updtpower.contract;

import webbroker3.tradingpower.util.ToStringUtils;


/**
 * (未受渡建玉の集計)
 */
public class WEB3TPSummaryUndeliveredContract extends WEB3TPContractBase 
{
    
    /**
     * (決済益)
     */
    private double contractProfit;
    
    /**
     * (決済損)
     */
    private double contractLoss;
    
    /**
     * (決済益<当日>)
     */
    private double todayRepayContractProfit;
    
    /**
     * (決済損<当日>)
     */
    private double todayRepayContractLoss;
    
    /**
     * (決済建玉前日価格評価<当日>)
     */
    private double todayRepayContractPrevAsset;
    
    /**
     * (決済益<指定日>)
     */
    private double designateDateContractProfit;
    
    /**
     * (決済損<指定日>)
     */
    private double designateDateContractLoss;
    
    /**
     * @@roseuid 4104AE3F0261
     */
    public WEB3TPSummaryUndeliveredContract() 
    {
     
    }
    
    /**
     * (create未受渡建玉の集計)
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPSummaryUndeliveredContract
     * @@roseuid 4100F12701F3
     */
    public static WEB3TPSummaryUndeliveredContract create() 
    {
        return new WEB3TPSummaryUndeliveredContract();
    }
    
    /**
     * (get決済益)<BR>
     * 決済益を取得する。<BR>
     * @@return double
     * @@roseuid 40BABB6C0334
     */
    public double getContractProfit() 
    {
        return contractProfit;
    }
    
    /**
     * (set決済益)<BR>
     * 引数の決済益をセットする。<BR>
     * @@param l_dblContractProfit - (決済益)
     * @@roseuid 40BABB6D00F1
     */
    public void setContractProfit(double l_dblContractProfit) 
    {
        contractProfit = l_dblContractProfit;
    }
    
    /**
     * (get決済損)<BR>
     * 決済損を取得する。<BR>
     * @@return double
     * @@roseuid 40BABB6C0372
     */
    public double getContractLoss() 
    {
         return contractLoss;
    }
    
    /**
     * (set決済損)<BR>
     * 引数の決済損をセットする。<BR>
     * @@param l_dblContractLoss - (決済損)
     * @@roseuid 40BABB6D0140
     */
    public void setContractLoss(double l_dblContractLoss) 
    {
        contractLoss = l_dblContractLoss;
    }
    
    /**
     * (get決済益<当日>)<BR>
     * 決済益<当日>を取得する。<BR>
     * @@return double
     */
    public double getTodayRepayContractProfit() 
    {
         return todayRepayContractProfit;
    }
    
    /**
     * (set決済益<当日>)<BR>
     * 引数の決済益<当日>をセットする。<BR>
     * @@param l_dblTodayRepayContractProfit - (決済益<当日>)
     */
    public void setTodayRepayContractProfit(double l_dblTodayRepayContractProfit) 
    {
        todayRepayContractProfit = l_dblTodayRepayContractProfit;
    }
    
    /**
     * (get決済損<当日>)<BR>
     * 決済損<当日>を取得する。<BR>
     * @@return double
     */
    public double getTodayRepayContractLoss() 
    {
         return todayRepayContractLoss;
    }
    
    /**
     * (set決済損<当日>)<BR>
     * 引数の決済損<当日>をセットする。<BR>
     * @@param l_dblTodayRepayContractLoss - (決済損<当日>)
     */
    public void setTodayRepayContractLoss(double l_dblTodayRepayContractLoss) 
    {
        todayRepayContractLoss = l_dblTodayRepayContractLoss;
    }
    
    /**
     * (get決済建玉前日価格評価<当日>)<BR>
     * 決済建玉前日価格評価<当日>を取得する。<BR>
     * @@return double
     */
    public double getTodayRepayContractPrevAsset() 
    {
         return todayRepayContractPrevAsset;
    }
    
    /**
     * (set決済建玉前日価格評価<当日>)<BR>
     * 引数の決済建玉前日価格評価<当日>をセットする。<BR>
     * @@param l_dblTodayRepayContractPrevAsset - (決済建玉前日価格評価<当日>)
     */
    public void setTodayRepayContractPrevAsset(double l_dblTodayRepayContractPrevAsset) 
    {
        todayRepayContractPrevAsset = l_dblTodayRepayContractPrevAsset;
    }
    
    /**
     * (get決済益<指定日>)<BR>
     * 決済益<指定日>を取得する。<BR>
     * @@return double
     */
    public double getDesignateDateContractProfit() 
    {
         return designateDateContractProfit;
    }
    
    /**
     * (set決済益<指定日>)<BR>
     * 引数の決済益<指定日>をセットする。<BR>
     * @@param l_dblDesignateDateContractProfit - (決済益<指定日>)
     */
    public void setDesignateDateContractProfit(double l_dblDesignateDateContractProfit) 
    {
        designateDateContractProfit = l_dblDesignateDateContractProfit;
    }
    
    /**
     * (get決済損<指定日>)<BR>
     * 決済損<指定日>を取得する。<BR>
     * @@return double
     */
    public double getDesignateDateContractLoss() 
    {
         return designateDateContractLoss;
    }
    
    /**
     * (set決済損<指定日>)<BR>
     * 引数の決済損<指定日>をセットする。<BR>
     * @@param l_dblDesignateDateContractLoss - (決済損<指定日>)
     */
    public void setDesignateDateContractLoss(double l_dblDesignateDateContractLoss) 
    {
        designateDateContractLoss = l_dblDesignateDateContractLoss;
    }
    
    
    /**
     * このオブジェクトの文字列表現を返す。
     */
    public String toString() 
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .appendSuper(super.toString())
            .append("contractProfit", getContractProfit())
            .append("contractLoss", getContractLoss())
            .append("todayRepayContractProfit", getTodayRepayContractProfit())
            .append("todayRepayContractLoss", getTodayRepayContractLoss())
            .append("todayRepayContractPreviousAsset", getTodayRepayContractPrevAsset())
            .append("designateDateContractProfit", getDesignateDateContractProfit())
            .append("designateDateContractLoss", getDesignateDateContractLoss())
            .toString();
    }
}
@
