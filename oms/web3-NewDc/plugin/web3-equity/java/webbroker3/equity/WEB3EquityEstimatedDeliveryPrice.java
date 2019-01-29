head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityEstimatedDeliveryPrice.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 概算受渡代金計算結果(WEB3EquityEstimatedDeliveryPrice.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/19 趙林 (中訊) 新規作成
                   2005/01/06 岡村和明(SRA) JavaDoc修正
                   2006/11/01 唐性峰　@(中訊)モデルNo.945, No.1026
*/
package webbroker3.equity;

import webbroker3.util.WEB3StringTypeUtility;

/**
 * （概算受渡代金計算結果）。<BR>
 * <BR>
 * 拡張株式注文マネージャ.calc概算受渡代金()<BR>
 * の戻り値を表現するデータクラス。
 * @@version 1.0
 */
public class WEB3EquityEstimatedDeliveryPrice implements WEB3EquityEstimatedPrice
{

    /**
     * (計算単価) <BR>
     * 　@・指値注文の場合は指値。 <BR>
     * 　@・成行注文の場合は時価。 <BR>
     * <BR>
     */
    private double calculationUnitPrice;

    /**
     * (拘束売買代金) <BR>
     */
    private double restraintTurnover;

    /**
     * (概算受渡代金) <BR>
     */
    private double estimateDeliveryAmount;
    
    /**
     * (委託手数料) <BR>
     */
    private double commissionFee;
    
    /**
     * (委託手数料消費税) <BR>
     */
    private double commissionFeeTax;
    
    /**
     * (確認時取得時価) <BR>
     * 確認レスポンス.確認時単価 にセットする値（＝完了リクエストでもらう値）をセット。<BR>
     * ・時価を取得した場合は、取得した時価。<BR>
     * ・時価を取得していない場合は、0（初期値<BR>
     */
    private double checkGetCurrentPrice;

    /**
     * (概算受渡代金計算結果クラスコンストラクタ。) <BR>
     * @@roseuid 40591BDF02CE
     */
    public WEB3EquityEstimatedDeliveryPrice()
    {

    }

    /**
     * (計算単価をセットする。) <BR>
     * @@param l_dblCalculationUnitPrice - 計算単価 <BR>
     * @@roseuid 40591C87007C
     */
    public void setCalcUnitPrice(double l_dblCalculationUnitPrice)
    {
        this.calculationUnitPrice = l_dblCalculationUnitPrice;
    }

    /**
     * (計算単価を取得する。) <BR>
     * @@return double
     * @@roseuid 40591CB0004D
     */
    public double getCalcUnitPrice()
    {
        return this.calculationUnitPrice;
    }

    /**
     * (拘束売買代金をセットする。) <BR>
     * @@param l_dblRestraintTurnover - 拘束売買代金 <BR>
     * @@roseuid 40591CCB02BE
     */
    public void setRestraintTurnover(double l_dblRestraintTurnover)
    {
        this.restraintTurnover = l_dblRestraintTurnover;
    }

    /**
     * (拘束売買代金を取得する。)<BR>
     * @@return double
     * @@roseuid 40591CCB02DE
     */
    public double getRestraintTurnover()
    {
        return this.restraintTurnover;
    }

    /**
     * (概算受渡代金をセットする。) <BR>
     * @@param l_dblEstimateDeliveryAmount - 概算受渡代金 <BR>
     * @@roseuid 40591CCC03B8
     */
    public void setEstimateDeliveryAmount(double l_dblEstimateDeliveryAmount)
    {
        this.estimateDeliveryAmount = l_dblEstimateDeliveryAmount;
    }

    /**
     * (概算受渡代金を取得する。) <BR>
     * @@return double
     * @@roseuid 40591CCC03C8
     */
    public double getEstimateDeliveryAmount()
    {
        return this.estimateDeliveryAmount;
    }
    /**
     * (set委託手数料) <BR>
     * @@param l_dblCommissionFee - 委託手数料 <BR>
     */
    public void setCommissionFee(double l_dblCommissionFee)
    {
        this.commissionFee = l_dblCommissionFee;
    }
    
    /**
     * (get委託手数料) <BR>
     * 委託手数料を取得する
     * @@return double
     */
    public double getCommissionFee()
    {
        return this.commissionFee;
    }
    /**
     * (set委託手数料消費税) <BR>
     * 委託手数料消費税をセットする
     * @@param l_dblCommissionFeeTax - 委託手数料消費税 <BR>
     */
    public void setCommissionFeeTax(double l_dblCommissionFeeTax)
    {
        this.commissionFeeTax = l_dblCommissionFeeTax;
    }
    
    /**
     * (get委託手数料消費税) <BR>
     * 委託手数料消費税を取得する
     * @@return double
     */
    public double getCommissionFeeTax()
    {
        return this.commissionFeeTax;
    }

    /**
     * (set確認時取得時価) <BR>
     * 確認時取得時価をセットする。<BR>
     * <BR>
     * 引数の確認時取得時価==（0, NaN）の場合は、0（初期値）をセットする。<BR>
     * 以外、引数の確認時取得時価の値をそのままセットする。<BR>
     * @@param l_dblCheckGetCurrentPrice - 確認時取得時価 <BR>
     */
    public void setCheckGetCurrentPrice(double l_dblCheckGetCurrentPrice)
    {
        if (l_dblCheckGetCurrentPrice == 0 || Double.isNaN(l_dblCheckGetCurrentPrice))
        {
            this.checkGetCurrentPrice = 0;
        }
        else
        {
            this.checkGetCurrentPrice = l_dblCheckGetCurrentPrice;
        }
    }

    /**
     * (get確認時取得時価) <BR>
     * 確認時取得時価を取得する。（確認レスポンス.確認時単価設定用）<BR>
     * @@return double
     */
    public String getCheckGetCurrentPrice()
    {
        return WEB3StringTypeUtility.formatNumber(this.checkGetCurrentPrice);
    }
}
@
