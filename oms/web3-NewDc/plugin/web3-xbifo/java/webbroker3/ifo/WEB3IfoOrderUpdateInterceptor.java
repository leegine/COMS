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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�����X�V�C���^�Z�v�^(WEB3IfoOrderUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/15 䈋� (���u) �V�K�쐬
*/
package webbroker3.ifo;

import webbroker3.gentrade.WEB3GentradeCommission;

/**
 * (�敨OP�����X�V�C���^�Z�v�^)<BR>
 * �敨OP�����X�V�C���^�Z�v�^�N���X<BR>
 * @@author  䈋�
 * @@version 1.0
 */
public abstract class WEB3IfoOrderUpdateInterceptor extends WEB3IfoUpdateInterceptor 
{    
    /**
     * (�萔��)<BR>
     * �萔���I�u�W�F�N�g<BR>
     */
    protected WEB3GentradeCommission commRevMstId;
    
    /**
     * (�T�Z��n����v�Z����)<BR>
     * �T�Z��n����v�Z���ʃI�u�W�F�N�g<BR>
     */
    protected WEB3IfoEstimateDeliveryAmountCalcResult estimateDeliveryAmounCalcResult;
    
    /**
     * ��������<BR>
     * �@@0�F DEFAULT�i�����Ȃ��j<BR>
     * �@@1�F �t�w�l<BR>
     * �@@2�F W�w�l<BR>
     */
    protected String orderCond;
    
    /**
     * �����������Z�q<BR>
     * �@@0�F Equal<BR>
     * �@@1�F ��l�ȏ�<BR>
     * �@@2�F ��l�ȉ�<BR>
     */
    protected String orderCondOperator;
    
    /**
     * �t�w�l��l�^�C�v<BR>
     * �@@0�F DEFAULT�i�����Y�����j<BR>
     * �@@1�F �v���~�A��<BR>
     */
    protected String stopOrderBasePriceType;
    
    /**
     * �t�w�l��l<BR>
     */
    protected double stopOrderBasePrice;
    
    /**
     * �iW�w�l�j�����w�l<BR>
     */
    protected double wLimitPriceChange;
    
    /**
     * @@roseuid 40C07514032C
     */
    public WEB3IfoOrderUpdateInterceptor() 
    {
     
    }
    
    /**
     * (set�萔��)<BR>
     * �萔���I�u�W�F�N�g���Z�b�g����B<BR>
     * @@param l_commision - �萔��<BR>
     * �萔���I�u�W�F�N�g<BR>
     * @@roseuid 405E8E110172
     */
    public void setCommision(WEB3GentradeCommission l_commision) 
    {
        this.commRevMstId = l_commision;      
    }
    
    /**
     * (set�T�Z��n����v�Z����)<BR>
     * �T�Z��n����v�Z���ʂ��Z�b�g����B<BR>
     * @@param l_estimateDeliveryAmounCalcResult - �T�Z��n����v�Z���ʃI�u�W�F�N�g
     * @@roseuid 407A62C8002E
     */
    public void setEstimateDeliveryAmountCalcResult(WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmounCalcResult) 
    {
        this.estimateDeliveryAmounCalcResult = l_estimateDeliveryAmounCalcResult;      
    }
    
    /**
     * (set��������)<BR>
     * �����������Z�b�g����B<BR>
     * @@param l_strOrderCond - ��������
     * @@roseuid 405E8E4E0327
     */
    public void setOrderCond(String l_strOrderCond) 
    {
        this.orderCond = l_strOrderCond;
    }
    
    /**
     * (set�����������Z�q)<BR>
     * �����������Z�q���Z�b�g����B<BR>
     * @@param l_strOrderCondOperator - �����������Z�q
     * @@roseuid 405E8E7C02AA
     */
    public void setOrderCondOperator(String l_strOrderCondOperator) 
    {
        this.orderCondOperator = l_strOrderCondOperator;     
    }
    
    /**
     * (set�t�w�l��l�^�C�v)<BR>
     * �t�w�l��l�^�C�v���Z�b�g����B<BR>
     * @@param l_strStopOrderBasePriceType - �t�w�l��l�^�C�v
     * @@roseuid 405E8EA70068
     */
    public void setStopOrderBasePriceType(String l_strStopOrderBasePriceType) 
    {
        this.stopOrderBasePriceType = l_strStopOrderBasePriceType;      
    }
    
    /**
     * (set�t�w�l��l)<BR>
     * �t�w�l��l���Z�b�g����B<BR>
     * @@param l_dblStopOrderBasePrice - �t�w�l��l
     * @@roseuid 405E8ECC0039
     */
    public void setStopOrderBasePrice(double l_dblStopOrderBasePrice) 
    {
        this.stopOrderBasePrice = l_dblStopOrderBasePrice;     
    }
    
    /**
     * (set�iW�w�l�j�����w�l)<BR>
     * �iW�w�l�j�����w�l���Z�b�g����B<BR>
     * @@param l_dblWLimitPriceChange - �iW�w�l�j�������s����
     * @@roseuid 405E8F3F0181
     */
    public void setWLimitPriceChange(double l_dblWLimitPriceChange) 
    {
        this.wLimitPriceChange = l_dblWLimitPriceChange;    
    }
}
@
