head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.45.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoEstimateDeliveryAmountCalcResult.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP概算受渡代金計算結果(WEB3IfoEstimateDeliveryAmountCalcResult .java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/10 艾興 (中訊) 新規作成
*/
package webbroker3.ifo;


/**
 * (先物OP概算受渡代金計算結果)<BR>
 * 先物OP概算受渡代金計算結果クラス<BR>
 * @@author  艾興
 * @@version 1.0
 */
public class WEB3IfoEstimateDeliveryAmountCalcResult 
{
    
    /**
     * 計算単価<BR>
     * 　@・指値注文の場合は指値。<BR>
     * 　@・成行注文の場合は時価。<BR>
     */
    private double calcUnitPrice;
    
    /**
     * 拘束売買代金<BR>
     */
    private double restraintTurnover;
    
    /**
     * 概算受渡代金<BR>
     */
    private double estimateDeliveryAmount;
    
    /**
     * 手数料コース<BR>
     */    
    private String  commissionCourse;
    
    /**
     * 手数料<BR>
     */    
    private double commission;
    
    /**
     * 手数料消費税<BR>
     */  
    private double commissionTax;
    
    /**
     * (先物OP概算受渡代金計算結果)<BR>
     * 概算受渡代金計算結果クラスコンストラクタ。<BR>
     * @@return webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult
     * @@roseuid 409F5E7F00F5
     */
    public WEB3IfoEstimateDeliveryAmountCalcResult() 
    {
        
    }
    
    /**
     * (set計算単価)<BR>
     * 計算単価をセットする。<BR>
     * @@param l_dblCalcUnitPrice - 計算単価<BR>
     * @@roseuid 409F5E7F00F6
     */
    public void setCalcUnitPrice(double l_dblCalcUnitPrice) 
    {
        this.calcUnitPrice = l_dblCalcUnitPrice;    
    }
    
    /**
     * (get計算単価)<BR>
     * 計算単価を取得する。<BR>
     * @@return double
     * @@roseuid 409F5E7F0104
     */
    public double getCalcUnitPrice() 
    {
        return this.calcUnitPrice;
    }
    
    /**
     * (set拘束売買代金)<BR>
     * 拘束売買代金をセットする。<BR>
     * @@param l_dblRestraintTurnover - 拘束売買代金<BR>
     * @@roseuid 409F5E7F0105
     */
    public void setRestraintTurnover(double l_dblRestraintTurnover) 
    {
        this.restraintTurnover = l_dblRestraintTurnover;
    }
    
    /**
     * (get拘束売買代金)<BR>
     * 拘束売買代金を取得する。<BR>
     * @@return double
     * @@roseuid 409F5E7F0107
     */
    public double getRestraintTurnover() 
    {
        return this.restraintTurnover;
    }
    
    /**
     * (set概算受渡代金)<BR>
     * 概算受渡代金をセットする。<BR>
     * @@param l_dblEstimateDeliveryAmount - 概算受渡代金<BR>
     * @@roseuid 409F5E7F0108
     */
    public void setEstimateDeliveryAmount(double l_dblEstimateDeliveryAmount) 
    {
        this.estimateDeliveryAmount = l_dblEstimateDeliveryAmount; 
    }
    
    /**
     * (get概算受渡代金)<BR>
     * 概算受渡代金を取得する。<BR>
     * @@return double
     * @@roseuid 409F5E7F010A
     */
    public double getEstimateDeliveryAmount() 
    {
        return this.estimateDeliveryAmount;
    }
    
    /**
     * (set手数料コース)<BR>
     * 手数料コースをセットする。<BR>
     * @@param l_strcommissionCourse - 手数料コース<BR>
     */
    public void setCommissionCourse(String l_strCommissionCourse)
    {
        this.commissionCourse = l_strCommissionCourse;
    }
    
    /**
     * (get手数料コース)<BR>
     * 手数料コースをセットする。<BR>
     * @@return String<BR>
     */
   public String getCommissionCourse()
   {
        return this.commissionCourse;
   }
   
   /**
    * (set手数料)<BR>
    * 手数料をセットする。<BR>
    * @@param l_strcommissionCourse - 手数料<BR>
    */
   public void setCommission(double l_dblCommission)
   {
       this.commission = l_dblCommission;
   }
   
   /**
    * (get手数料)<BR>
    * 手数料をセットする。<BR>
    * @@return double<BR>
    */
  public double getCommission()
  {
       return this.commission;
  }
  
  /**
   * (set手数料消費税)<BR>
   * 手数料消費税をセットする。<BR>
   * @@param l_strcommissionCourse - 手数料消費税<BR>
   */
  public void setCommissionTax(double l_dblCommissionTax)
  {
      this.commissionTax = l_dblCommissionTax;
  }
   
  /**
   * (get手数料消費税)<BR>
   * 手数料消費税をセットする。<BR>
   * @@return double<BR>
   */
 public double getCommissionTax()
 {
      return this.commissionTax;
 }
}
@
