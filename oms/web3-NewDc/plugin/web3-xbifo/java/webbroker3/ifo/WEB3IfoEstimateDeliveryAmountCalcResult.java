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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�T�Z��n����v�Z����(WEB3IfoEstimateDeliveryAmountCalcResult .java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/10 䈋� (���u) �V�K�쐬
*/
package webbroker3.ifo;


/**
 * (�敨OP�T�Z��n����v�Z����)<BR>
 * �敨OP�T�Z��n����v�Z���ʃN���X<BR>
 * @@author  䈋�
 * @@version 1.0
 */
public class WEB3IfoEstimateDeliveryAmountCalcResult 
{
    
    /**
     * �v�Z�P��<BR>
     * �@@�E�w�l�����̏ꍇ�͎w�l�B<BR>
     * �@@�E���s�����̏ꍇ�͎����B<BR>
     */
    private double calcUnitPrice;
    
    /**
     * �S���������<BR>
     */
    private double restraintTurnover;
    
    /**
     * �T�Z��n���<BR>
     */
    private double estimateDeliveryAmount;
    
    /**
     * �萔���R�[�X<BR>
     */    
    private String  commissionCourse;
    
    /**
     * �萔��<BR>
     */    
    private double commission;
    
    /**
     * �萔�������<BR>
     */  
    private double commissionTax;
    
    /**
     * (�敨OP�T�Z��n����v�Z����)<BR>
     * �T�Z��n����v�Z���ʃN���X�R���X�g���N�^�B<BR>
     * @@return webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult
     * @@roseuid 409F5E7F00F5
     */
    public WEB3IfoEstimateDeliveryAmountCalcResult() 
    {
        
    }
    
    /**
     * (set�v�Z�P��)<BR>
     * �v�Z�P�����Z�b�g����B<BR>
     * @@param l_dblCalcUnitPrice - �v�Z�P��<BR>
     * @@roseuid 409F5E7F00F6
     */
    public void setCalcUnitPrice(double l_dblCalcUnitPrice) 
    {
        this.calcUnitPrice = l_dblCalcUnitPrice;    
    }
    
    /**
     * (get�v�Z�P��)<BR>
     * �v�Z�P�����擾����B<BR>
     * @@return double
     * @@roseuid 409F5E7F0104
     */
    public double getCalcUnitPrice() 
    {
        return this.calcUnitPrice;
    }
    
    /**
     * (set�S���������)<BR>
     * �S������������Z�b�g����B<BR>
     * @@param l_dblRestraintTurnover - �S���������<BR>
     * @@roseuid 409F5E7F0105
     */
    public void setRestraintTurnover(double l_dblRestraintTurnover) 
    {
        this.restraintTurnover = l_dblRestraintTurnover;
    }
    
    /**
     * (get�S���������)<BR>
     * �S������������擾����B<BR>
     * @@return double
     * @@roseuid 409F5E7F0107
     */
    public double getRestraintTurnover() 
    {
        return this.restraintTurnover;
    }
    
    /**
     * (set�T�Z��n���)<BR>
     * �T�Z��n������Z�b�g����B<BR>
     * @@param l_dblEstimateDeliveryAmount - �T�Z��n���<BR>
     * @@roseuid 409F5E7F0108
     */
    public void setEstimateDeliveryAmount(double l_dblEstimateDeliveryAmount) 
    {
        this.estimateDeliveryAmount = l_dblEstimateDeliveryAmount; 
    }
    
    /**
     * (get�T�Z��n���)<BR>
     * �T�Z��n������擾����B<BR>
     * @@return double
     * @@roseuid 409F5E7F010A
     */
    public double getEstimateDeliveryAmount() 
    {
        return this.estimateDeliveryAmount;
    }
    
    /**
     * (set�萔���R�[�X)<BR>
     * �萔���R�[�X���Z�b�g����B<BR>
     * @@param l_strcommissionCourse - �萔���R�[�X<BR>
     */
    public void setCommissionCourse(String l_strCommissionCourse)
    {
        this.commissionCourse = l_strCommissionCourse;
    }
    
    /**
     * (get�萔���R�[�X)<BR>
     * �萔���R�[�X���Z�b�g����B<BR>
     * @@return String<BR>
     */
   public String getCommissionCourse()
   {
        return this.commissionCourse;
   }
   
   /**
    * (set�萔��)<BR>
    * �萔�����Z�b�g����B<BR>
    * @@param l_strcommissionCourse - �萔��<BR>
    */
   public void setCommission(double l_dblCommission)
   {
       this.commission = l_dblCommission;
   }
   
   /**
    * (get�萔��)<BR>
    * �萔�����Z�b�g����B<BR>
    * @@return double<BR>
    */
  public double getCommission()
  {
       return this.commission;
  }
  
  /**
   * (set�萔�������)<BR>
   * �萔������ł��Z�b�g����B<BR>
   * @@param l_strcommissionCourse - �萔�������<BR>
   */
  public void setCommissionTax(double l_dblCommissionTax)
  {
      this.commissionTax = l_dblCommissionTax;
  }
   
  /**
   * (get�萔�������)<BR>
   * �萔������ł��Z�b�g����B<BR>
   * @@return double<BR>
   */
 public double getCommissionTax()
 {
      return this.commissionTax;
 }
}
@
