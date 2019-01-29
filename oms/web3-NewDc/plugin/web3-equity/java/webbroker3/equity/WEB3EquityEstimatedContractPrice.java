head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityEstimatedContractPrice.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 概算建代金計算結果(WEB3EquityEstimatedContractPrice.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/11/01 唐性峰　@新規作成 （モデル）No.946,No.1026
*/

package webbroker3.equity;

import webbroker3.util.WEB3StringTypeUtility;

/**
 * (概算建代金計算結果)<BR>
 * 拡張株式注文マネージャ.calc注文時建代金()の戻り値を表現するデータクラス。<BR>
 * 
 * @@author 唐性峰
 * @@version 1.0
 */
public class WEB3EquityEstimatedContractPrice implements WEB3EquityEstimatedPrice
{

    /**
     * (計算単価) <BR>
     * 計算単価 <BR>
     * (拘束金額計算に使用した単価）<BR>
     */
    private double calculationUnitPrice;

    /**
     * (概算建代金)<BR>
     * 概算建代金<BR>
     */
    private double estimatedContractPrice;

    /**
     * (確認時取得時価)<BR>
     * 確認レスポンス.確認時単価 にセットする値（＝完了リクエストでもらう値）をセット。<BR>
     * ・時価を取得した場合は、取得した時価。<BR>
     * ・時価を取得していない場合は、0（初期値）。<BR>
     */
    private double checkGetCurrentPrice;

    /**
     * (概算建代金計算結果) <BR>
     * 概算建代金計算結果クラスコンストラクタ。<BR>
     */
    public WEB3EquityEstimatedContractPrice()
    {

    }

    /**
     * (set計算単価) <BR>
     * 計算単価をセットする。<BR>
     * @@param l_dblCalculationUnitPrice - 計算単価 <BR>
     */
    public void setCalcUnitPrice(double l_dblCalculationUnitPrice)
    {
        this.calculationUnitPrice = l_dblCalculationUnitPrice;
    }

    /**
     * (get計算単価) <BR>
     * 計算単価を取得する。<BR>
     * @@return double
     */
    public double getCalcUnitPrice()
    {
        return this.calculationUnitPrice;
    }

    /**
     * (set概算建代金) <BR>
     * 概算建代金をセットする。<BR>
     * @@param l_dblEstimatedContractPrice -  概算建代金 <BR>
     */
    public void setEstimatedContractPrice(double l_dblEstimatedContractPrice)
    {
        this.estimatedContractPrice = l_dblEstimatedContractPrice;
    }

    /**
     * (get概算建代金) <BR>
     * 概算建代金を取得する。<BR>
     * @@return double
     */
    public double getEstimatedContractPrice()
    {
        return this.estimatedContractPrice;
    }

    /**
     * (set確認時取得時価 ) <BR>
     * 確認時取得時価をセットする。<BR>
     * <BR>
     * 引数の確認時取得時価==（0, NaN）の場合は、0（初期値）をセットする。<BR>
     * 以外、引数の確認時取得時価の値をそのままセットする。<BR>
     * @@param l_dblCheckGetCurrentPrice -  確認時取得時価 <BR>
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
     * @@return String
     */
    public String getCheckGetCurrentPrice()
    {
        return WEB3StringTypeUtility.formatNumber(this.checkGetCurrentPrice);
    }

    /**
     * (get概算受渡代金) <BR>
     * this.get概算建代金()に処理を委譲する。<BR>
     * @@return double
     */
    public double getEstimateDeliveryAmount()
    {
        return this.getEstimatedContractPrice();
    }
}
@
