head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualTPAdjustConfirmInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�Ǘ��җ]�͒����m��C���^�Z�v�^(WEB3AdminMutualTPAdjustConfirmInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/19 ��O�� (���u) �V�K�쐬      
*/

package webbroker3.mf;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ChannelDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * ���M�Ǘ��җ]�͒����m��C���^�Z�v�^<BR>
 * 
 * @@author ��O��(���u)
 * @@version 1.0  
 */
public class WEB3AdminMutualTPAdjustConfirmInterceptor
    extends WEB3DefaultMutualFundOrderConfirmInterceptor 
{        
    /**
     *  ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminMutualTPAdjustConfirmInterceptor.class);
        
    /**
     * ������<BR>
     */
    protected Timestamp orderBizDate;
        
    /**
     * ����<BR>
     */
    protected Timestamp executionDate;
    
    /**
     * ��n��<BR>
     */
    protected Timestamp deliveryDate;
    
    /**
     * �T�Z��n���<BR>
     */
    protected double estimatedPrice;
    
    /**
     * �v�Z����z<BR>
     */
    protected double constantValue;
    
    /**
     * ����z�K�p��<BR>
     */
    protected Timestamp constantValueAppDate;
    
    /**
     * ���M�Ǘ��җ]�͒����m��C���^�Z�v�^<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 40AD8E0E03B4
     */
    public WEB3AdminMutualTPAdjustConfirmInterceptor() 
    {
    }
    
    /**
     * (�X�V�l�ݒ�)
     * �imutate�̎����j <BR>
     * <BR>
     * �����E��������̒��ŁA���M�����P�ʃf�[�^�̍쐬�E�X�V���ɌĂ΂��B <BR> 
     * �����ŗ^����ꂽ���M�����P��Params�ɒl��ݒ肵�A���M�����P��Params��Ԃ��B<BR> 
     * <BR>
     * �P�j �����P��Params�̍��ڂɒl��ݒ肵�A�ԋp����B <BR>
     * �@@���ڐݒ���e�́A
     *  DB�X�V�d�l�u�����M���Ǘ��җ]�͒���_���M�����P�ʃe�[�u���d�l.xls�v���Q�ƁB<BR>
     * <BR>
     * @@param l_orderManagerPersistenceType - (�i�����^�C�v)<BR>
     * �f�[�^�̍X�V�܂��͑}�����ɌĂԂ��ǂ����w��<BR>
     * @@param l_orderManagerPersistenceContext - (�i�����R���e�L�X�g)<BR>
     * �Ăяo�����̃R���e�L�X�g���w��B�Ⴆ�Ό����������ȂǁB<BR>
     * @@param l_mutualFundOrderUnitParams - (���M�����P��Params)<BR>
     * �i�����O�̓��M�����P��Params<BR>
     * @@return MutualFundOrderUnitParams
     * @@roseuid 40AD8E93025C
     */
    public MutualFundOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        MutualFundOrderUnitParams l_mutualFundOrderUnitParams)  
    {
        final String STR_METHOD_NAME = "mutate(" +
            "OrderManagerPersistenceType l_orderManagerPersistenceType," +
            "OrderManagerPersistenceContext l_orderManagerPersistenceContext, " +
            "MutualFundOrderUnitParams l_mutualFundOrderUnitParams))";
        log.entering(STR_METHOD_NAME);
        
        if  (l_mutualFundOrderUnitParams == null)
        {
            log.debug(" �p�����[�^Null�o���Ȃ��BWith " +
                "(�i�����O�̓��M�����P��Params)l_mutualFundOrderUnitParams" + 
                l_mutualFundOrderUnitParams);        
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^��Null�ł���");
        }        
        
        //�P�j �����P��Params�̍��ڂɒl��ݒ肵�A�ԋp����B 
        //���ڐݒ���e�́ADB�X�V�d�l�u�����M���Ǘ��җ]�͒���_���M�����P�ʃe�[�u���d�l.xls�v���Q�ƁB
        
        //���M�����P��Params.set��n��(���M�Ǘ��җ]�͒����m��C���^�Z�v�^.��n��)
        l_mutualFundOrderUnitParams.setDeliveryDate(this.deliveryDate);
        
        //���M�����P��Params.set������(���M�Ǘ��җ]�͒����m��C���^�Z�v�^.������) 
        l_mutualFundOrderUnitParams.setBizDate(
            WEB3DateUtility.formatDate(this.orderBizDate, "yyyyMMdd"));

        //���M�����P��Params.set���񒍕��̒����`���l��(�h0�F�c�ƓX�h) 
        l_mutualFundOrderUnitParams.setOrderChanel(WEB3ChannelDef.BRANCH);
        
        //���M�����P��Params.set�󒍓���(
        //  �T�[�o���ŃT�[�r�X���N�����ꂽ���ԁi�v�Z�����i���ʁj(*2) �������t�@@���Q�Ɓj) 
        Timestamp l_tsOrderAcceptDate =
            (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        
        l_mutualFundOrderUnitParams.setReceivedDateTime(l_tsOrderAcceptDate);
        
        //���M�����P��Params.set���҃R�[�h�iSONAR�j(null) 
        l_mutualFundOrderUnitParams.setSonarTraderCode(null);
        
        //���M�����P��Params.set���ʃR�[�h(null) 
        l_mutualFundOrderUnitParams.setOrderRequestNumber(null);
        
        //���M�����P��Params.set�v�Z����z(���M�Ǘ��җ]�͒����m��C���^�Z�v�^.�v�Z����z
        //�i���g�����M����.get���t����z()�j) 
        l_mutualFundOrderUnitParams.setCalcConstantValue(this.constantValue);
        
        //���M�����P��Params.set�v�Z����z�i�抷��j(null) 
        l_mutualFundOrderUnitParams.setSwtCalcConstantValue(null);
        
        //���M�����P��Params.set����z�K�p��(���M�Ǘ��җ]�͒����m��C���^�Z�v�^.����z�K�p��
        //�i���g�����M����.get����z�K�p��()�j) 
        l_mutualFundOrderUnitParams.setConstantValueAppDate(
            this.constantValueAppDate);
        
        //���M�����P��Params.set�T�Z��n���(���M�Ǘ��җ]�͒����m��C���^�Z�v�^.�T�Z��n���) 
        l_mutualFundOrderUnitParams.setEstimatedPrice(this.estimatedPrice);
        
        //���M�����P��Params.set�T�Z��������(null) 
        l_mutualFundOrderUnitParams.setEstimateDealingQty(null);
        
        //���M�����P��Params.set�T�Z���t�����i�抷��j(null) 
        l_mutualFundOrderUnitParams.setSwtEstimateDealingQty(null);
        
        //���M�����P��Params.set�ŋ敪�i�抷��j(null) 
        l_mutualFundOrderUnitParams.setSwtTaxType(null);
        
        //���M�����P��Params.set�����R�[�h�i�抷��j(null) 
        l_mutualFundOrderUnitParams.setSwtProductCode(null);
        
        //���M�����P��Params.set��n���@@(null) 
        l_mutualFundOrderUnitParams.setPaymentMethod(null);
        
        //���M�����P��Params.set���M�^�C�v(�h2�F���O�h) 
        l_mutualFundOrderUnitParams.setFundType(MutualFundTypeEnum.FOREIGN);
        
        //���M�����P��Params.set���M���敪(null) 
        l_mutualFundOrderUnitParams.setFundSellDiv(null);
        
        //���M�����P��Params.set����(���M�Ǘ��җ]�͒����m��C���^�Z�v�^.����) 
        l_mutualFundOrderUnitParams.setExecDate(this.executionDate);
        
        //���M�����P��Params.set�����(null) 
        l_mutualFundOrderUnitParams.setExecStatus(null);
        
        //���M�����P��Params.set���ϋ敪(1�F�~��) 
        l_mutualFundOrderUnitParams.setSettlementDiv(
            WEB3SettlementDivDef.JAPANESE_CURRENCY);
        
        //���M�����P��Params.set���萔���敪(null) 
        l_mutualFundOrderUnitParams.setNoContractCommissionDiv(null);
        
        //���M�����P��Params.set�����敪(null) 
        l_mutualFundOrderUnitParams.setRequestDiv(null);
        
        //���M�����P��Params.set�����o�H�敪(9�FHOST) 
        l_mutualFundOrderUnitParams.setOrderRootDiv(WEB3OrderRootDivDef.HOST);
        
        //���M�����P��Params.set�����G���[���R�R�[�h(NULL) 
        l_mutualFundOrderUnitParams.setErrorReasonCode(null);
        
        //���M�����P��Params.set������(null) 
        l_mutualFundOrderUnitParams.setPaymentDate(null);
        
        //���M�����P��Params.set���򒥎��S����(NULL) 
        l_mutualFundOrderUnitParams.setWithholdingTaxRestriction(null);
        
        //���M�����P��Params.set�o���������ʃR�[�h(NULL) 
        l_mutualFundOrderUnitParams.setPaymentOrderReqNumber(null);
        
        //���M�����P��Params.setCPU No.(null) 
        l_mutualFundOrderUnitParams.setCpuNo(null);
        
        //���M�����P��Params��Ԃ��B
        log.exiting(STR_METHOD_NAME); 
        return l_mutualFundOrderUnitParams;
    }
    
    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate�̎����j<BR>
     * <BR>
     * �����E��������̒��ŁA���M���������f�[�^�̍쐬�E�X�V���ɌĂ΂��B  <BR>
     * �����ŗ^����ꂽ���M��������Params�ɒl��ݒ肵�A���M��������Params��Ԃ��B<BR> 
     * <BR>
     * �P�j�@@�����G���[���R�R�[�h�̐ݒ���s���B   <BR>
     * �@@�|���M��������Params.set�����G���[���R�R�[�h()���R�[�����A<BR>
     *          �����G���[���R�R�[�h�̐ݒ���s���B  <BR> 
     * �@@�@@�mset�����G���[���R�R�[�h�ɓn���p�����^�n <BR>
     * �@@�@@�@@�����G���[���R�R�[�h�F null <BR>
     * <BR>
     * �Q�j�@@�����ŗ^����ꂽ���M��������Params��Ԃ��B<BR>
     * <BR>
     * @@param l_orderManagerPersistenceType - (�i�����^�C�v)<BR>
     * �f�[�^�̍X�V�܂��͑}�����ɌĂԂ��ǂ����w��<BR>
     * @@param l_orderManagerPersistenceContext - (�i�����R���e�L�X�g)<BR>
     * �Ăяo�����̃R���e�L�X�g���w��B�Ⴆ�Ό����������ȂǁB<BR>
     * @@param l_mutualFundOrderActionParams - (���M��������Params)<BR>
     * �i�����O�̓��M��������Params<BR>
     * @@return MutualFundOrderActionParams
     * @@roseuid 40AAF0FF00F9
     */
    public MutualFundOrderActionParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        MutualFundOrderActionParams l_mutualFundOrderActionParams)
    {
        final String STR_METHOD_NAME ="mutate(" +
            "OrderManagerPersistenceType l_orderManagerPersistenceType," +
            "OrderManagerPersistenceContext l_orderManagerPersistenceContext," +
            "MutualFundOrderActionParams l_mutualFundOrderActionParams)";
        log.entering(STR_METHOD_NAME);    
        if (l_mutualFundOrderActionParams == null)
        {
            log.debug("the parameter of method is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
        
        //�P�j�@@�����G���[���R�R�[�h�̐ݒ���s���B   
        //�|���M��������Params.set�����G���[���R�R�[�h()���R�[�����A
        //  �����G���[���R�R�[�h�̐ݒ���s���B   
        //�@@�mset�����G���[���R�R�[�h�ɓn���p�����^�n   
        //�@@�@@�����G���[���R�R�[�h�F null   
        l_mutualFundOrderActionParams.setErrorReasonCode(null);                

        //�Q�j�@@�����ŗ^����ꂽ���M��������Params��Ԃ��B
        log.exiting(STR_METHOD_NAME);
        return l_mutualFundOrderActionParams;
    }
    
    /**
     * (set������)
     * this.��������ԋp����B<BR>
     * @@param l_orderBizDate - ������<BR>
     * @@roseuid 40AD92050133
     */
    public void setOrderBizDate(Timestamp l_orderBizDate) 
    {
        this.orderBizDate = l_orderBizDate;
    }
    
    /**
     * (get������)
     * this.��������ԋp����B<BR>
     * @@return Timestamp
     * @@roseuid 40AD91EC00E5
     */
    public Timestamp getOrderBizDate() 
    {
        return orderBizDate;
    }
    
    /**
     * (set����)
     * this.������ԋp����B<BR>
     * @@param l_executionDate - ����<BR>
     * @@roseuid 40AD92050133
     */
    public void setExecutionDate(Timestamp l_executionDate) 
    {
        this.executionDate = l_executionDate;
    }
    
    /**
     * (get����)
     * this.������ԋp����B<BR>
     * @@return Timestamp
     * @@roseuid 40AD91EC00E5
     */
    public Timestamp getExecutionDate() 
    {
        return executionDate;
    }
    
    /**
     * (set��n��)
     * this.��n����ԋp����B<BR>
     * @@param l_deliveryDate - ��n��<BR>
     * @@roseuid 40AD92050133
     */
    public void setDeliveryDate(Timestamp l_deliveryDate) 
    {
        this.deliveryDate = l_deliveryDate;
    }
    
    /**
     * (get��n��)
     * this.��n����ԋp����B<BR>
     * @@return Timestamp
     * @@roseuid 40AD91EC00E5
     */
    public Timestamp getDeliveryDate() 
    {
        return deliveryDate;
    }
    
    /**
     * (set�T�Z��n���)
     * this.�T�Z��n�����ԋp����B<BR>
     * @@param l_estimatedPrice - �T�Z��n���<BR>
     * @@roseuid 40AD92050133
     */
    public void setEstimatedPrice(double l_estimatedPrice) 
    {
        this.estimatedPrice = l_estimatedPrice;
    }
    
    /**
     * (get�T�Z��n���)
     * this.�T�Z��n�����ԋp����B<BR>
     * @@return Timestamp
     * @@roseuid 40AD91EC00E5
     */
    public double getEstimatedPrice() 
    {
        return estimatedPrice;
    }
    
    /**
     * (set�v�Z����z)
     * this.�v�Z����z��ԋp����B<BR>
     * @@param l_constantValue - �v�Z����z<BR>
     * @@roseuid 40AD92050133
     */
    public void setConstantValue(double l_constantValue) 
    {
        this.constantValue = l_constantValue;
    }
    
    /**
     * (get�v�Z����z)
     * this.�v�Z����z��ԋp����B<BR>
     * @@return Timestamp
     * @@roseuid 40AD91EC00E5
     */
    public double getConstantValue() 
    {
        return constantValue;
    }
    
    /**
     * (set����z�K�p��)
     * this.����z�K�p����ԋp����B<BR>
     * @@param l_constantValueAppDate - ����z�K�p��<BR>
     * @@roseuid 40AD92050133
     */
    public void setConstantValueAppDate(Timestamp l_constantValueAppDate) 
    {
        this.constantValueAppDate = l_constantValueAppDate;
    }
    
    /**
     * (get����z�K�p��)
     * this.����z�K�p����ԋp����B<BR>
     * @@return Timestamp
     * @@roseuid 40AD91EC00E5
     */
    public Timestamp getConstantValueAppDate() 
    {
        return constantValueAppDate;
    }

}
@
