head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondInterestPaymentDay.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������(InterestPaymentDay.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/08 �юu�� (���u) �V�K�쐬
*/
package webbroker3.bd;

import java.math.BigDecimal;
import java.util.Date;

/**
 * �������N���X
 * 
 * @@author �юu��(���u)
 * @@version 1.0
 */
public class WEB3BondInterestPaymentDay
{
    /**
     * (�O�񗘕���)<BR>
     * �O�񗘕���<BR>
     */
    private Date preInterestPaymentDay;
    
    /**
     * (���񗘕���)<BR>
     * ���񗘕���<BR>
     */
    private Date nextInterestPaymentDay;
    
    /**
     * (����)<BR>
     * ����<BR>
     */
    private BigDecimal coupon;
    
    /**
     * (���񗘕���)<BR>
     * ���񗘕���<BR>
     */
    private Date firstInterestPaymentDay;
    
    /**
     * (�ŏI������)<BR>
     * �ŏI������<BR>
     */
    private Date finalInterestPaymentDay;

    /**
     * (set�O�񗘕���)<BR>
     * �O�񗘕������Z�b�g����B<BR>
     * @@param l_datPreInterestPaymentDay - (�O�񗘕���)<BR>
     * �O�񗘕���<BR>
     */
    public void setPreInterestPaymentDay(Date l_datPreInterestPaymentDay)
    {
        this.preInterestPaymentDay = l_datPreInterestPaymentDay;
    }
    
    /**
     * (set���񗘕���)<BR>
     * ���񗘕������Z�b�g����B<BR>
     * @@param l_datNextInterestPaymentDay - (���񗘕���)<BR>
     * ���񗘕���<BR>
     */
    public void setNextInterestPaymentDay(Date l_datNextInterestPaymentDay)
    {
        this.nextInterestPaymentDay = l_datNextInterestPaymentDay;
    }
    
    /**
     * (set���񗘕���)<BR>
     * ���񗘕������Z�b�g����B<BR>
     * @@param l_datFirstInterestPaymentDay - (���񗘕���)<BR>
     * ���񗘕���<BR>
     */
    public void setFirstInterestPaymentDay(Date l_datFirstInterestPaymentDay)
    {
        this.firstInterestPaymentDay = l_datFirstInterestPaymentDay;
    }
    
    /**
     * (set�ŏI������)<BR>
     * �ŏI���������Z�b�g����B<BR>
     * @@param l_datFinalInterestPaymentDay - (�ŏI������)<BR>
     * �ŏI������<BR>
     */
    public void setFinalInterestPaymentDay(Date l_datFinalInterestPaymentDay)
    {
        this.finalInterestPaymentDay = l_datFinalInterestPaymentDay;
    }
   
    /**
     * (set����)<BR>
     * �������Z�b�g����B<BR>
     * @@param l_bdCoupon - (����)<BR>
     * ����<BR>
     */
    public void setCoupon(BigDecimal l_bdCoupon)
    {
        this.coupon = l_bdCoupon;
    }

    /**
     * (get�O�񗘕���)<BR>
     * �O�񗘕������擾����B<BR>
     * @@return Date
     */
    public Date getPreInterestPaymentDay()
    {
        return preInterestPaymentDay;
    }
    
    /**
     * (get���񗘕���)<BR>
     * ���񗘕������擾����B<BR>
     * @@return Date
     */
    public Date getNextInterestPaymentDay()
    {
        return nextInterestPaymentDay;
    }
    
    /**
     * (get���񗘕���)<BR>
     * ���񗘕������擾����B<BR>
     * @@return Date
     */
    public Date getFirstInterestPaymentDay()
    {
        return firstInterestPaymentDay;
    }

    /**
     * (get�ŏI������)<BR>
     * �ŏI���������擾����B<BR>
     * @@return Date
     */
    public Date getFinalInterestPaymentDay()
    {
        return finalInterestPaymentDay;
    }

    /**
     * (get����)<BR>
     * �������擾����B<BR>
     * @@return BigDecimal
     */
    public BigDecimal getCoupon()
    {
        return coupon;
    }
    
}
@
