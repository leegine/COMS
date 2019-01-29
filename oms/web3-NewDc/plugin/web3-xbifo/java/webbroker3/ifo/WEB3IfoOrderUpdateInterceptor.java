head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.44.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoOrderUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP注文更新インタセプタ(WEB3IfoOrderUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/15 艾興 (中訊) 新規作成
*/
package webbroker3.ifo;

import webbroker3.gentrade.WEB3GentradeCommission;

/**
 * (先物OP注文更新インタセプタ)<BR>
 * 先物OP注文更新インタセプタクラス<BR>
 * @@author  艾興
 * @@version 1.0
 */
public abstract class WEB3IfoOrderUpdateInterceptor extends WEB3IfoUpdateInterceptor 
{    
    /**
     * (手数料)<BR>
     * 手数料オブジェクト<BR>
     */
    protected WEB3GentradeCommission commRevMstId;
    
    /**
     * (概算受渡代金計算結果)<BR>
     * 概算受渡代金計算結果オブジェクト<BR>
     */
    protected WEB3IfoEstimateDeliveryAmountCalcResult estimateDeliveryAmounCalcResult;
    
    /**
     * 発注条件<BR>
     * 　@0： DEFAULT（条件なし）<BR>
     * 　@1： 逆指値<BR>
     * 　@2： W指値<BR>
     */
    protected String orderCond;
    
    /**
     * 発注条件演算子<BR>
     * 　@0： Equal<BR>
     * 　@1： 基準値以上<BR>
     * 　@2： 基準値以下<BR>
     */
    protected String orderCondOperator;
    
    /**
     * 逆指値基準値タイプ<BR>
     * 　@0： DEFAULT（原資産時価）<BR>
     * 　@1： プレミアム<BR>
     */
    protected String stopOrderBasePriceType;
    
    /**
     * 逆指値基準値<BR>
     */
    protected double stopOrderBasePrice;
    
    /**
     * （W指値）訂正指値<BR>
     */
    protected double wLimitPriceChange;
    
    /**
     * @@roseuid 40C07514032C
     */
    public WEB3IfoOrderUpdateInterceptor() 
    {
     
    }
    
    /**
     * (set手数料)<BR>
     * 手数料オブジェクトをセットする。<BR>
     * @@param l_commision - 手数料<BR>
     * 手数料オブジェクト<BR>
     * @@roseuid 405E8E110172
     */
    public void setCommision(WEB3GentradeCommission l_commision) 
    {
        this.commRevMstId = l_commision;      
    }
    
    /**
     * (set概算受渡代金計算結果)<BR>
     * 概算受渡代金計算結果をセットする。<BR>
     * @@param l_estimateDeliveryAmounCalcResult - 概算受渡代金計算結果オブジェクト
     * @@roseuid 407A62C8002E
     */
    public void setEstimateDeliveryAmountCalcResult(WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmounCalcResult) 
    {
        this.estimateDeliveryAmounCalcResult = l_estimateDeliveryAmounCalcResult;      
    }
    
    /**
     * (set発注条件)<BR>
     * 発注条件をセットする。<BR>
     * @@param l_strOrderCond - 発注条件
     * @@roseuid 405E8E4E0327
     */
    public void setOrderCond(String l_strOrderCond) 
    {
        this.orderCond = l_strOrderCond;
    }
    
    /**
     * (set発注条件演算子)<BR>
     * 発注条件演算子をセットする。<BR>
     * @@param l_strOrderCondOperator - 発注条件演算子
     * @@roseuid 405E8E7C02AA
     */
    public void setOrderCondOperator(String l_strOrderCondOperator) 
    {
        this.orderCondOperator = l_strOrderCondOperator;     
    }
    
    /**
     * (set逆指値基準値タイプ)<BR>
     * 逆指値基準値タイプをセットする。<BR>
     * @@param l_strStopOrderBasePriceType - 逆指値基準値タイプ
     * @@roseuid 405E8EA70068
     */
    public void setStopOrderBasePriceType(String l_strStopOrderBasePriceType) 
    {
        this.stopOrderBasePriceType = l_strStopOrderBasePriceType;      
    }
    
    /**
     * (set逆指値基準値)<BR>
     * 逆指値基準値をセットする。<BR>
     * @@param l_dblStopOrderBasePrice - 逆指値基準値
     * @@roseuid 405E8ECC0039
     */
    public void setStopOrderBasePrice(double l_dblStopOrderBasePrice) 
    {
        this.stopOrderBasePrice = l_dblStopOrderBasePrice;     
    }
    
    /**
     * (set（W指値）訂正指値)<BR>
     * （W指値）訂正指値をセットする。<BR>
     * @@param l_dblWLimitPriceChange - （W指値）訂正執行条件
     * @@roseuid 405E8F3F0181
     */
    public void setWLimitPriceChange(double l_dblWLimitPriceChange) 
    {
        this.wLimitPriceChange = l_dblWLimitPriceChange;    
    }
}
@
