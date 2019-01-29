head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityRealizedProfitAndLossPrice.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 概算決済損益代金計算結果(WEB3EquityRealizedProfitAndLossPrice.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/27 王暁傑 新規作成
Revesion History : 2005/01/05 岡村和明(SRA) JavaDoc修正
Revesion History : 2006/11/01 唐性峰　@(中訊)モデルNo.1026
*/
package webbroker3.equity;


/**
 * （概算決済損益代金計算結果）。<BR>
 * <BR>
 * 拡張株式注文マネージャ.calc概算決済損益代金()の戻り値を表現するデータクラス。
 * @@version 1.0
 */
public class WEB3EquityRealizedProfitAndLossPrice implements WEB3EquityEstimatedPrice
{
    
    /**
     * (計算単価)<BR>
     * 　@・指値注文の場合は指値。<BR>
     * 　@・成行注文の場合は時価。<BR>
     */
    private double calcUnitPrice;
    
    /**
     * (売買代金)
     */
    private double turnover;
    
    /**
     * (概算決済損益代金)
     */
    private double estimatedRealizedProfitAndLossAmount;
    
    /**
     * (概算決済損益代金計算結果)<BR>
     * 概算決済損益代金計算結果クラスコンストラクタ。
     * @@return webbroker3.margin.WEB3EquityEstimatedCloseIncomeAmountDeliveryPrice
     * @@roseuid 40B473B30399
     */
    public WEB3EquityRealizedProfitAndLossPrice() 
    {
     
    }
    
    /**
     * (set計算単価)<BR>
     * 計算単価をセットする。
     * @@param l_dblCalcUnitPrice - 計算単価
     * @@roseuid 40B473B3039A
     */
    public void setCalcUnitPrice(double l_dblCalcUnitPrice) 
    {
        this.calcUnitPrice = l_dblCalcUnitPrice;
    }
    
    /**
     * (get計算単価)<BR>
     * 計算単価を取得する。
     * @@return double
     * @@roseuid 40B473B3039C
     */
    public double getCalcUnitPrice() 
    {
        return this.calcUnitPrice;
    }
    
    /**
     * (set売買代金)<BR>
     * 売買代金をセットする。
     * @@param l_dblRestraintTurnover - 拘束売買代金
     * @@roseuid 40B6C39802ED
     */
    public void setTurnover(double l_dblRestraintTurnover) 
    {
        this.turnover = l_dblRestraintTurnover;
    }
    
    /**
     * (get売買代金)<BR>
     * 売買代金を取得する。
     * @@return double
     * @@roseuid 40B6C39802EF
     */
    public double getTurnover() 
    {
        return this.turnover;
    }
    
    /**
     * ( set概算決済損益代金)<BR>
     * 概算決済損益代金をセットする。
     * @@param l_dblEstimatedCloseIncomeAmount - 概算決済損益代金
     * @@roseuid 40B473B303A8
     */
    public void setEstimatedRealizedProfitAndLossAmount(double l_dblEstimatedCloseIncomeAmount) 
    {
        this.estimatedRealizedProfitAndLossAmount = l_dblEstimatedCloseIncomeAmount;
    }
    
    /**
     * (get概算決済損益代金)<BR>
     * 概算決済損益代金を取得する。
     * @@return double
     * @@roseuid 40B473B303AA
     */
    public double getEstimatedRealizedProfitAndLossAmount() 
    {
        return this.estimatedRealizedProfitAndLossAmount;
    }
    
    /**
     * (get概算受渡代金)<BR>
     * this.get概算決済損益代金()に処理を委譲する。<BR>
     * @@return double
     */
    public double getEstimateDeliveryAmount()
    {
        return this.getEstimatedRealizedProfitAndLossAmount();
    }
}
@
