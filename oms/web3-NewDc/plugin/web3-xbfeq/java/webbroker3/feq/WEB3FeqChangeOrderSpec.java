head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqChangeOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�����������������e(WEB3FeqChangeOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14  ����(���u) �V�K�쐬
                 : 2005/07/28 ������(���u) ���r���[
*/


package webbroker3.feq;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.ordersubmitter.io.FeqChangeOrderSpec;


/**
 * (�O�����������������e)
 * �O�����������������e�N���X
 * 
 * @@ author ���� 
 * @@ version 1.0 
 */
public class WEB3FeqChangeOrderSpec extends FeqChangeOrderSpec 
{
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    private Date bizDate;
    
    /**
     * (�������s����)<BR>
     * ������̎��s����<BR>
     */
    private FeqExecutionConditionType changeExecutionCondition;
    
    /**
     * (���������L������)<BR>
     * ������̒����L������<BR>
     */
    private Date changeOrderExpirationDate;
    
    /**
     * (��������)<BR>
     * ��������<BR>
     * <BR>
     * 0�FDEFAULT�i�����w��Ȃ��j<BR>
     * 1�F�t�w�l<BR>
     * 2�FW�w�l<BR>
     */
    private String orderConditionType;
    
    /**
     * (���������������Z�q)<BR>
     * ������̔����������Z�q<BR>
     */
    private String changeOrderCondOperator;
    
    /**
     * (�������������P��)<BR>
     * ������̔��������P��<BR>
     */
    private double changeOrderCondPrice;
    
    /**
     * (�����iW�w�l�j�����w�l)<BR>
     * ������́iW�w�l�j�����w�l<BR>
     */
    private double changeWLimitPrice;
    
    /**
     * (�������������敪)<BR>
     * ������̒��������敪<BR>
     */        
    private String changeOrderExpirationDiv;
    
    /**
     * (�O�����������������e)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �����̒l���e�v���p�e�B�ɃZ�b�g����B<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID
     * 
     * @@param l_lngOrderUnitId - (�����P��ID)<BR>
     * �����P��ID
     * 
     * @@param l_dblChangeQuantity - (��������)<BR>
     * ��������
     * 
     * @@param l_dblChangePrice - (�����P��)<BR>
     * �����P��
     * @@roseuid 42973C070148
     */
    public WEB3FeqChangeOrderSpec(
        long l_lngOrderId, 
        long l_lngOrderUnitId, 
        double l_dblChangeQuantity, 
        double l_dblChangePrice) 
    {        
        super(l_lngOrderId,l_lngOrderUnitId, l_dblChangeQuantity, l_dblChangePrice);       
    }
    
    /**
     * (set������)<BR>
     * ���������Z�b�g����B<BR>
     * @@param l_datBizDate - (������)<BR>
     * ������
     * @@roseuid 429737B6005E
     */
    public void setBizDate(Date l_datBizDate) 
    {
        this.bizDate = l_datBizDate; 
    }
    
    /**
     * (get������)<BR>
     * ���������擾����B<BR>
     * <BR>
     * this.��������ԋp����B<BR>
     * @@return Date
     * @@roseuid 429737B6006D
     */
    public Date getBizDate() 
    {
        return this.bizDate;
    }
    
    /**
     * (set�������s����)<BR>
     * ������̎��s�������Z�b�g����B<BR>
     * @@param l_strChangeExecutionType - (���s����)<BR>
     * ������̎��s����
     * @@roseuid 4297367002EE
     */
    public void setChangeExecutionCondition(FeqExecutionConditionType l_strChangeExecutionType) 
    {
        this.changeExecutionCondition = l_strChangeExecutionType; 
    }
    
    /**
     * (get�������s����)<BR>
     * ������̎��s�������擾����B<BR>
     * <BR>
     * this.�������s������ԋp����B<BR>
     * @@return FeqExecutionConditionType
     * @@roseuid 4297367002FD
     */
    public FeqExecutionConditionType getChangeExecutionCondition() 
    {
        return this.changeExecutionCondition;
    }
    
    /**
     * (set���������L������)<BR>
     * ������̒����L���������Z�b�g����B<BR>
     * @@param l_datChangeOrderExpirationDate - (�����L������)<BR>
     * ������̒����L������
     * @@roseuid 42973670031D
     */
    public void setChangeOrderExpirationDate(Date l_datChangeOrderExpirationDate) 
    {
        this.changeOrderExpirationDate =  l_datChangeOrderExpirationDate;
    }
    
    /**
     * (get���������L������)<BR>
     * ������̒����L���������擾����B<BR>
     * <BR>
     * this.���������L��������ԋp����B<BR>
     * @@return Date
     * @@roseuid 42973670032C
     */
    public Date getChangeOrderExpirationDate() 
    {
        return this.changeOrderExpirationDate;
    }
    
    /**
     * (set��������)<BR>
     * �����������Z�b�g����B<BR>
     * @@param l_strOrderConditionType - (��������)<BR>
     * ��������
     * @@roseuid 42973670033C
     */
    public void setOrderConditionType(String l_strOrderConditionType) 
    {
        this.orderConditionType = l_strOrderConditionType;
    }
    
    /**
     * (get��������)<BR>
     * �����������擾����B<BR>
     * <BR>
     * this.����������ԋp����B<BR>
     * @@return String
     * @@roseuid 42973670034C
     */
    public String getOrderConditionType() 
    {
        return this.orderConditionType;
    }
    
    /**
     * (set���������������Z�q)<BR>
     * ������̔����������Z�q���Z�b�g����B<BR>
     * @@param l_strChangeOrderCondOperator - (�����������Z�q)<BR>
     * ������̔����������Z�q
     * @@roseuid 4297380A03D8
     */
    public void setChangeOrderCondOperator(String l_strChangeOrderCondOperator) 
    {
        this.changeOrderCondOperator =  l_strChangeOrderCondOperator;
    }
    
    /**
     * (get���������������Z�q)<BR>
     * ������̔����������Z�q���擾����B<BR>
     * <BR>
     * this.���������������Z�q��ԋp����B<BR>
     * @@return String
     * @@roseuid 4297380B0000
     */
    public String getChangeOrderCondOperator() 
    {
        return this.changeOrderCondOperator;
    }
    
    /**
     * (set�������������P��)<BR>
     * ������̔��������P�����Z�b�g����B<BR>
     * @@param l_dblChangeOrderCondPrice - (���������P��)<BR>
     * ������̔��������P��
     * @@roseuid 429738BB037A
     */
    public void setChangeOrderCondPrice(double l_dblChangeOrderCondPrice) 
    {
        this.changeOrderCondPrice =  l_dblChangeOrderCondPrice;
    }
    
    /**
     * (get�������������P��)<BR>
     * ������̔��������P�����擾����B<BR>
     * <BR>
     * this.�������������P����ԋp����B<BR>
     * @@return double
     * @@roseuid 429738BB03A9
     */
    public double getChangeOrderCondPrice() 
    {
        return this.changeOrderCondPrice;
    }
    
    /**
     * (set�����iW�w�l�j�����w�l)<BR>
     * ������́iW�w�l�j�����w�l���Z�b�g����B<BR>
     * @@param l_dblChangeWLimitPrice - (�iW�w�l�j�����w�l)<BR>
     * ������́iW�w�l�j�����w�l
     * @@roseuid 42973670036B
     */
    public void setChangeWLimitPrice(double l_dblChangeWLimitPrice) 
    {
        this.changeWLimitPrice = l_dblChangeWLimitPrice;
    }
    
    /**
     * (get�����iW�w�l�j�����w�l)<BR>
     * ������́iW�w�l�j�����w�l���擾����B<BR>
     * <BR>
     * this.�����iW�w�l�j�����w�l��ԋp����B<BR>
     * @@return double
     * @@roseuid 42973670039A
     */
    public double getChangeWLimitPrice() 
    {
        return this.changeWLimitPrice;
    }
    
    /**
     * (set�������������敪)<BR>
     * ������̒��������敪���擾����B<BR>
     * <BR>
     * @@param l_strChangeOrderExpirationDiv - (������̒��������敪)<BR>
     * ������̒��������敪
     */
    public void setChangeOrderExpirationDiv(String l_strChangeOrderExpirationDiv)
    {
        this.changeOrderExpirationDiv = l_strChangeOrderExpirationDiv;
    }
    
    /**
     * (get�������������敪)<BR>
     * ������̒��������敪���擾����B<BR>
     * <BR>
     * this.������̒��������敪��ԋp����B<BR>
     */
    public String getChangeOrderExpirationDiv()
    {
        return this.changeOrderExpirationDiv;
    }
}
@
