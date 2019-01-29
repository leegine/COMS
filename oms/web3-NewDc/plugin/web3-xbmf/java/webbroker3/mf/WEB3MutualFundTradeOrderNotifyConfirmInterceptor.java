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
filename	WEB3MutualFundTradeOrderNotifyConfirmInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�����������ʒm�m��C���^�Z�v�^(WEB3MutualFundTradeOrderNotifyConfirmInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/06 ���E (���u) �V�K�쐬
                   2004/08/20 ���� (���u) ���r���[   
                   2004/12/06 ������ (���u) �c�Ή�
                   2005/10/21 ��O�� (���u) �t�B�f���e�B�Ή�
*/
package webbroker3.mf;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ChannelDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * �����M�����������ʒm�m��C���^�Z�v�^<BR>
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3MutualFundTradeOrderNotifyConfirmInterceptor
    extends WEB3DefaultMutualFundOrderConfirmInterceptor
{
    /**
     * ��n��<BR>
     */
    protected Timestamp deliveryDate;
    
    /**
     * ������<BR>
     */
    protected Timestamp BizDate;
    
    /**
     * �󒍓���<BR>
     */
    protected Timestamp acceptDate;
    
    /**
     * ���ʃR�[�h<BR>
     */
    protected String discriminationCode;
    
    /**
     * �v�Z����z<BR>
     */
    protected double constantValue;
    
    /**
     * �v�Z����z�i�抷��j<BR>
     */
    protected double switchingConstantValue;
    
    /**
     * �T�Z��n���<BR>
     */
    protected double estimatedPrice;
    
    /**
     * �T�Z��������<BR>
     */
    protected double estimatedQty;
    
    /**
     * ��n���@@<BR>
     */
    protected String deliveryDiv;
    
    /**
     * ���M�^�C�v<BR>
     * <BR>
     * 0�F���̑��@@1�F�����@@2�F���O<BR>
     */
    protected String mutualFundType;
    
    /**
     * ����<BR>
     */
    protected Timestamp executionTimestamp;
    
    /**
     * ���ϋ敪<BR>
     * <BR>
     * 1�F�~�݁@@2�F�O��<BR>
     */
    protected String settlementType;
    
    /**
     * ���萔���敪<BR>
     * <BR>
     * �󔒁F���֌W�@@5�F�抷�D���@@9�F���萔��<BR>
     */
    protected String noCommissionDivision;
    
    /**
     * �����R�[�h�i�抷��j<BR>
     */
    protected String switchingSubjectMutualProductCode;
    
    /**
     * �����敪<BR>
     * <BR>
     * 0�F���@@1�F����<BR>
     */
    protected String requestDivision;
    
    /**
     * ���҃R�[�h�iSONAR�j<BR>
     */
    protected String sonarTraderCode;
    
    /**
     * ���M���敪<BR>
     */
    protected String mutualFundSellDiv;
    
    /**
     * ����z�K�p��<BR>
     */
    protected Timestamp constantValueAppDate;
    
    /**
     * �T�Z���t�����i�抷��j<BR>
     */
    protected double switchingEstimatedQty;
    
    /**
     * �ŋ敪�i�抷��j<BR>
     */
    protected TaxTypeEnum switchingSubjectTaxDivision;
    
    /**
     * �������<BR>
     */
    protected OrderTypeEnum orderType;
    
    /**
     * CPU No<BR>
     *�iSONAR�ł̒����̊Ǘ��ԍ��j 
     */
    protected String CPUNo;
    
        
    /**
     * ������<BR>
     */
    protected Timestamp paymentDate;

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MutualFundTradeOrderNotifyConfirmInterceptor.class);
            
    /**
     * (���M���������ʒm�m��C���^�Z�v�^)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40AD8E1C0327
     */
    public WEB3MutualFundTradeOrderNotifyConfirmInterceptor()
    {
    }
    
    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate�̎����j<BR>
     * <BR>
     * �����E��������̒��ŁA���M���������f�[�^�̍쐬�E�X�V���ɌĂ΂��B<BR>
     * �����ŗ^����ꂽ���M��������Params�ɒl��ݒ肵�A���M��������Params��Ԃ��B <BR>
     * <BR>
     * �P�j�@@�����G���[���R�R�[�h�̐ݒ���s���B <BR>
     * �@@�|���M��������Params.set�����G���[���R�R�[�h()���R�[�����A<BR>
     * �����G���[���R�R�[�h�̐ݒ���s���B <BR>
     * �@@�@@�mset�����G���[���R�R�[�h�ɓn���p�����^�n <BR>
     * �@@�@@�@@�����G���[���R�R�[�h�F null <BR>
     * <BR>
     * �Q�j�@@�����ŗ^����ꂽ���M��������Params��Ԃ��B <BR>
     * <BR>
     * @@param l_orderManagerPersistenceType - (�i�����^�C�v)<BR>
     * �f�[�^�̍X�V�܂��͑}�����ɌĂԂ��ǂ����w��<BR>
     * @@param l_orderManagerPersistenceContext - (�i�����R���e�L�X�g)<BR>
     * �Ăяo�����̃R���e�L�X�g���w��B�Ⴆ�Ό����������ȂǁB<BR>
     * @@param l_mutualFundOrderActionParams - (���M��������Params)<BR>
     * �i�����O�̓��M��������Params<BR>
     * @@return MutualFundOrderActionParams
     * @@roseuid 40AD90DC0097
     */
    public MutualFundOrderActionParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        MutualFundOrderActionParams l_mutualFundOrderActionParams)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType l_orderManagerPersistenceType," +
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
        
        //�P�j�@@�����G���[���R�R�[�h�̐ݒ���s��
        l_mutualFundOrderActionParams.setErrorReasonCode(null);
        
        log.exiting(STR_METHOD_NAME);
        //�Q�j�@@�����ŗ^����ꂽ���M��������Params��Ԃ�
        return l_mutualFundOrderActionParams;
    }
    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate�̎����j<BR>
     * <BR>
     * �����E��������̒��ŁA���M�����P�ʃf�[�^�̍쐬�E�X�V���ɌĂ΂��B<BR>
     * �����ŗ^����ꂽ���M�����P��Params�ɒl��ݒ肵�A���M�����P��Params��Ԃ��B <BR>
     * <BR>
     * �P�j�@@���I�u�W�F�N�g�ɐݒ肳��Ă���l�𓊐M�����P��Params�ɐݒ肷��B <BR>
     * <BR>
     *     ���M�����P��Params.set�������(this.get�������( )) <BR>
     * �@@�@@���M�����P��Params.set������(this.get������( ))  <BR>
     * �@@�@@���M�����P��Params.set��n��(this.get��n��( ))<BR>
     * �@@�@@���M�����P��Params.set�󒍓���(this.get�󒍓���( ))<BR>
     * �@@�@@���M�����P��Params.set���҃R�[�h�iSONAR�j(this.get���҃R�[�h�iSONAR�j( ))<BR>
     * �@@�@@���M�����P��Params.set���ʃR�[�h(this.get���ʃR�[�h( ))<BR>
     * �@@�@@���M�����P��Params.set�v�Z����z(this.get�v�Z����z( ))<BR>
     * �@@�@@���M�����P��Params.set����z�K�p��(this.get����z�K�p��( ))<BR>
     * �@@�@@���M�����P��Params.set�T�Z��n���(this.get�T�Z��n���( ))<BR>
     * �@@�@@���M�����P��Params.set�T�Z��������(this.get�T�Z��������( ))<BR>
     * �@@�@@���M�����P��Params.set�ŋ敪�i�抷��j(this.get�ŋ敪�i�抷��j( ))<BR>
     * �@@�@@���M�����P��Params.set��n���@@(this.get��n���@@( ))<BR>
     * �@@�@@���M�����P��Params.set���M�^�C�v(this.get���M�^�C�v( ))<BR>
     * �@@�@@���M�����P��Params.set����(this.get����( ))<BR>
     * �@@�@@���M�����P��Params.set���ϋ敪(this.get���ϋ敪( ))<BR>
     * �@@�@@���M�����P��Params.set���萔���敪(this.get���萔���敪( ))<BR>
     * �@@�@@���M�����P��Params.set�����R�[�h�i�抷��j(this.get�����R�[�h�i�抷��j( ))<BR>
     * �@@�@@���M�����P��Params.set�����敪(this.get�����敪( ))<BR>
     * �@@�@@���M�����P��Params.set���M���敪(this.get���M���敪( )) <BR>
     *     ���M�����P��Params.set������(this.get������( )) <BR>
     * <BR>
     * �Q�j�@@���񒍕��̒����`���l���̐ݒ���s��<BR>
     * �@@�|���M�����P��Params.set���񒍕��̒����`���l��()���R�[�����A<BR>
     * ���񒍕��̒����`���l���̐ݒ���s���B <BR>
     * �@@�@@�mset���񒍕��̒����`���l���ɓn���p�����^�n <BR>
     * �@@�@@�@@���񒍕��̒����`���l���F �h0�F�c�ƓX�h<BR>
     * <BR>
     * �R�j�@@�����o�H�敪�̐ݒ���s��<BR>
     * �@@�|���M�����P��Params.set�����o�H�敪()���R�[�����A�����o�H�敪�̐ݒ���s���B <BR>
     * �@@�@@�mset�����o�H�敪�ɓn���p�����^�n <BR>
     * �@@�@@�@@�����o�H�敪�F �h9�FHOST�h<BR>
     * <BR>
     * �S�j�@@���M�����P��Params�I�u�W�F�N�g�ɁA�ȉ��̐ݒ���s���B<BR>
     * �@@�@@���M�����P��Params.set�����(null)<BR>
     * �@@�@@���M�����P��Params.set�����G���[���R�R�[�h(null)<BR>
     * <BR>
     * �T�j�@@�v�Z����z�i�抷��j�̐ݒ���s���B<BR>
     * �@@�|���M�����P��Params.set�v�Z����z�i�抷��j()���R�[�����A<BR>
     * �v�Z����z�i�抷��j�̐ݒ���s���B<BR>
     * �@@�@@�mset�v�Z����z�i�抷��j�ɓn���p�����^�n<BR>
     * �@@�@@�@@�@@(*) Double.isNan(this.get�v�Z����z�i�抷��j())��true��Ԃ��ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�@@(Double)null��ݒ肷��B<BR>
     * �@@�@@�@@�@@(*) Double.isNan(this.get�v�Z����z�i�抷��j())��false��Ԃ��ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�@@this.get�v�Z����z�i�抷��j()�̖߂�l��ݒ肷��B<BR>
     * <BR>
     * �U�j�@@�T�Z���t�����i�抷��j�̐ݒ���s���B<BR>
     * �@@�|���M�����P��Params.set�T�Z���t�����i�抷��j()���R�[�����A<BR>
     * �T�Z���t�����i�抷��j�̐ݒ���s���B<BR>
     * �@@�@@�mset�T�Z���t�����i�抷��j�ɓn���p�����^�n<BR>
     * �@@�@@�@@�@@(*) Double.isNan(this.get�T�Z���t�����i�抷��j())��true��Ԃ��ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�@@(Double)null��ݒ肷��B<BR>
     * �@@�@@�@@�@@(*) Double.isNan(this.get�T�Z���t�����i�抷��j())��false��Ԃ��ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�@@this.get�T�Z���t�����i�抷��j()�̖߂�l��ݒ肷��B<BR>
     * <BR>
     * �V�j�@@�����ŗ^����ꂽ���M�����P��Params��Ԃ��B <BR>
     * @@param l_orderManagerPersistenceType - (�i�����^�C�v)<BR>
     * �f�[�^�̍X�V�܂��͑}�����ɌĂԂ��ǂ����w��<BR>
     * @@param l_orderManagerPersistenceContext - (�i�����R���e�L�X�g)<BR>
     * �Ăяo�����̃R���e�L�X�g���w��B�Ⴆ�Ό����������ȂǁB<BR>
     * @@param l_mutualFundOrderUnitParams - (���M�����P��Params)<BR>
     * �i�����O�̓��M�����P��Params<BR>
     * @@return MutualFundOrderUnitParams
     * @@roseuid 40AD90DC00B6
     */
    public MutualFundOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        MutualFundOrderUnitParams l_mutualFundOrderUnitParams)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType l_orderManagerPersistenceType," +
            "OrderManagerPersistenceContext l_orderManagerPersistenceContext," +
            " MutualFundOrderUnitParams l_mutualFundOrderUnitParams)";
        log.entering(STR_METHOD_NAME);
        if (l_mutualFundOrderUnitParams == null)
        {
            log.debug("the parameter of method is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }         
        
        //�P�j�@@���I�u�W�F�N�g�ɐݒ肳��Ă���l�𓊐M�����P��Params�ɐݒ肷�� 
        // ���M�����P��Params.set������(this.get������( )) 
        String l_strBizDate = 
            WEB3DateUtility.formatDate(
                    this.getBizDate(),
                    "yyyyMMdd");
        l_mutualFundOrderUnitParams.setBizDate(l_strBizDate);  
        
        //���M�����P��Params.set�������(this.get�������( )) 
        l_mutualFundOrderUnitParams.setOrderType(this.getOrderType());
        
        //���M�����P��Params.set��n��(this.get��n��( ))
        l_mutualFundOrderUnitParams.setDeliveryDate(this.deliveryDate);
        
        //���M�����P��Params.set�󒍓���(this.get�󒍓���( ))
        l_mutualFundOrderUnitParams.setReceivedDateTime(this.acceptDate);

        //���M�����P��Params.set���҃R�[�h�iSONAR�j(this.get���҃R�[�h�iSONAR�j( )) 
        l_mutualFundOrderUnitParams.setSonarTraderCode(this.sonarTraderCode);
        
        //���M�����P��Params.set���ʃR�[�h(this.get���ʃR�[�h( )) 
        l_mutualFundOrderUnitParams.setOrderRequestNumber(
            this.discriminationCode);
        
        //���M�����P��Params.set�v�Z����z(this.get�v�Z����z( ))
        l_mutualFundOrderUnitParams.setCalcConstantValue(this.constantValue);
        
        //���M�����P��Params.set����z�K�p��(this.get����z�K�p��( ))
        l_mutualFundOrderUnitParams.setConstantValueAppDate(
            this.constantValueAppDate);
        
        //���M�����P��Params.set�T�Z��n���(this.get�T�Z��n���( ))
        l_mutualFundOrderUnitParams.setEstimatedPrice(
            this.estimatedPrice);
        
        //���M�����P��Params.set�T�Z��������(this.get�T�Z��������( ))
        l_mutualFundOrderUnitParams.setEstimateDealingQty(
            this.estimatedQty);
        
        //���M�����P��Params.set�ŋ敪�i�抷��j(this.get�ŋ敪�i�抷��j( ))
        l_mutualFundOrderUnitParams.setSwtTaxType(
            this.switchingSubjectTaxDivision);
        
        //���M�����P��Params.set��n���@@(this.get��n���@@( ))
        l_mutualFundOrderUnitParams.setPaymentMethod(this.deliveryDiv);
        
        //���M�����P��Params.set���M�^�C�v(this.get���M�^�C�v( ))
        if (MutualFundTypeEnum.DOMESTIC.intValue() == Integer.parseInt(this.mutualFundType))
        {
            l_mutualFundOrderUnitParams.setFundType(
                MutualFundTypeEnum.DOMESTIC);
        }
        if (MutualFundTypeEnum.FOREIGN.intValue() == Integer.parseInt(this.mutualFundType))
        {
            l_mutualFundOrderUnitParams.setFundType(MutualFundTypeEnum.FOREIGN);
        }
        if (MutualFundTypeEnum.OTHER.intValue() == Integer.parseInt(this.mutualFundType))
        {
            l_mutualFundOrderUnitParams.setFundType(MutualFundTypeEnum.OTHER);
        }
        
        //���M�����P��Params.set����(this.get����( ))
        l_mutualFundOrderUnitParams.setExecDate(this.executionTimestamp);
        
        //���M�����P��Params.set���ϋ敪(this.get���ϋ敪( ))
        l_mutualFundOrderUnitParams.setSettlementDiv(this.settlementType);
        
        //���M�����P��Params.set���萔���敪(this.get���萔���敪( ))
        l_mutualFundOrderUnitParams.setNoContractCommissionDiv(
            this.noCommissionDivision);
        
        //���M�����P��Params.set�����R�[�h�i�抷��j(this.get�����R�[�h�i�抷��j( ))
        l_mutualFundOrderUnitParams.setSwtProductCode(
            this.switchingSubjectMutualProductCode);
        
        //���M�����P��Params.set�����敪(this.get�����敪( ))
        l_mutualFundOrderUnitParams.setRequestDiv(this.requestDivision);
        
        //���M�����P��Params.set���M���敪(this.get���M���敪( ))
        l_mutualFundOrderUnitParams.setFundSellDiv(this.mutualFundSellDiv);
        
		//���M�����P��Params.set������(this.get������( )) 
        l_mutualFundOrderUnitParams.setPaymentDate(this.getPaymentDate());
        
        l_mutualFundOrderUnitParams.setCpuNo(this.getCPUNo());
        
        //�Q�j�@@���񒍕��̒����`���l���̐ݒ���s��
        //  �|���M�����P��Params.set���񒍕��̒����`���l��()���R�[�����A���񒍕��̒����`���l���̐ݒ���s��
        l_mutualFundOrderUnitParams.setOrderChanel(WEB3ChannelDef.BRANCH);
        
        //�S�j�@@�����o�H�敪�̐ݒ���s��
        //  �|���M�����P��Params.set�����o�H�敪()���R�[�����A�����o�H�敪�̐ݒ���s���B
        l_mutualFundOrderUnitParams.setOrderRootDiv(WEB3OrderRootDivDef.HOST);
        
        //�T�j�@@���M�����P��Params�I�u�W�F�N�g�ɁA�ȉ��̐ݒ���s��
        //���M�����P��Params.set�����(null)
        //���M�����P��Params.set�����G���[���R�R�[�h(null)
        l_mutualFundOrderUnitParams.setExecStatus(null);
        l_mutualFundOrderUnitParams.setErrorReasonCode(null);
        
        //�U�j�@@�v�Z����z�i�抷��j�̐ݒ���s���B
        if (Double.isNaN(this.getSwitchingConstantValue()) == true)
        {
            l_mutualFundOrderUnitParams.setSwtCalcConstantValue(null);
        }
        else if (Double.isNaN(this.getSwitchingConstantValue()) == false)
        {
            l_mutualFundOrderUnitParams.setSwtCalcConstantValue(
                this.getSwitchingConstantValue());
        }
       
        //�V�j�@@�T�Z���t�����i�抷��j�̐ݒ���s���B
        if (Double.isNaN(this.getSwitchingEstimatedQty()) == true)
        {
            l_mutualFundOrderUnitParams.setSwtEstimateDealingQty(null);
        }
        else if (Double.isNaN(this.getSwitchingEstimatedQty()) == false)
        {
            l_mutualFundOrderUnitParams.setSwtEstimateDealingQty(
                this.getSwitchingEstimatedQty());
        }
        log.exiting(STR_METHOD_NAME);
    
        //�W�j�@@�����ŗ^����ꂽ���M�����P��Params��Ԃ�
        return l_mutualFundOrderUnitParams;
    }
    
    /**
     * ��n���̐ݒ���s���B<BR>
     * @@param l_tsDeliveryDate - ��n��<BR>
     * @@roseuid 40AD9C050385
     */
    public void setDeliveryDate(Timestamp l_tsDeliveryDate)
    {
        this.deliveryDate = l_tsDeliveryDate;
    }
    
    /**
     * this.��n����Ԃ��B<BR>
     * @@return Timestamp
     * @@roseuid 40AD9C0C03B4
     */
    public Timestamp getDeliveryDate()
    {
        return this.deliveryDate;
    }
    
    /**
     * �������̐ݒ���s���B<BR>
     * @@param l_tsDeliveryDate - ������<BR>
     * @@roseuid 40AD9C050385
     */
    public void setBizDate(Timestamp l_tsDeliveryDate)
    {
        this.BizDate = l_tsDeliveryDate;
    }
    
    /**
     * this.��������Ԃ��B<BR>
     * @@return Timestamp
     * @@roseuid 40AD9C0C03B4
     */
    public Timestamp getBizDate()
    {
        return this.BizDate;
    }
   
    /**
     * �󒍓����̐ݒ���s���B<BR>
     * @@param l_tsAcceptDate - �󒍓���<BR>
     * @@roseuid 40AD9BD90308
     */
    public void setAcceptDate(Timestamp l_tsAcceptDate)
    {
        this.acceptDate = l_tsAcceptDate;
    }
   
    /**
     * this.�󒍓�����Ԃ��B<BR>
     * @@return Timestamp
     * @@roseuid 40AD9BD90317
     */
    public Timestamp getAcceptDate()
    {
        return this.acceptDate;
    }
   
    /**
     * ���ʃR�[�h�̐ݒ���s���B<BR>
     * @@param l_strDiscriminationCode - ���ʃR�[�h<BR>
     * @@roseuid 40AD9BD90356
     */
    public void setDiscriminationCode(String l_strDiscriminationCode)
    {
        this.discriminationCode = l_strDiscriminationCode;
    }
   
    /**
     * this.���ʃR�[�h��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40AD9BD90375
     */
    public String getDiscriminationCode()
    {
        return this.discriminationCode;
    }
   
    /**
     * �v�Z����z�̐ݒ���s���B<BR>
     * @@param l_dblConstantValue - ����z<BR>
     * @@roseuid 40AD9BD90394
     */
    public void setConstantValue(double l_dblConstantValue)
    {
        this.constantValue = l_dblConstantValue;
    }
   
    /**
     * this.�v�Z����z��Ԃ��B<BR>
     * @@return double
     * @@roseuid 40AD9BD903A4
     */
    public double getConstantValue()
    {
        return this.constantValue;
    }
   
    /**
     * �v�Z����z�i�抷��j�̐ݒ���s���B<BR>
     * @@param l_dblSwitchingConstantValue - ����z�i�抷��j<BR>
     * @@roseuid 40D7D33600D8
     */
    public void setSwitchingConstantValue(double l_dblSwitchingConstantValue)
    {
        this.switchingConstantValue = l_dblSwitchingConstantValue;
    }
   
    /**
     * this.�v�Z����z�i�抷��j��Ԃ��B<BR>
     * @@return double
     * @@roseuid 40D7D33600E8
     */
    public double getSwitchingConstantValue()
    {
        return this.switchingConstantValue;
    }
   
    /**
     * �T�Z��n����̐ݒ���s���B<BR>
     * @@param l_dblEstimatedPrice - �T�Z��n���<BR>
     * @@roseuid 40AD9BD903C3
     */
    public void setEstimatedPrice(double l_dblEstimatedPrice)
    {
        this.estimatedPrice = l_dblEstimatedPrice;
    }
   
    /**
     * this.�T�Z��n�����Ԃ��B<BR>
     * @@return double
     * @@roseuid 40AD9BD903E3
     */
    public double getEstimatedPrice()
    {
        return this.estimatedPrice;
    }
   
    /**
     * �T�Z���������̐ݒ���s���B<BR>
     * @@param l_dblEstimatedQty - �T�Z��������<BR>
     * @@roseuid 40AD9BDA001A
     */
    public void setEstimatedQty(double l_dblEstimatedQty)
    {
        this.estimatedQty = l_dblEstimatedQty;
    }
   
    /**
     * this.�T�Z����������Ԃ��B<BR>
     * @@return double
     * @@roseuid 40AD9BDA0029
     */
    public double getEstimatedQty()
    {
        return this.estimatedQty;
    }
   
    /**
     * ��n���@@�̐ݒ���s���B<BR>
     * @@param l_strDeliveryDiv - ��n���@@<BR>
     * @@roseuid 40AD9BDA0049
     */
    public void setDeliveryDiv(String l_strDeliveryDiv)
    {
        this.deliveryDiv = l_strDeliveryDiv;
    }
   
    /**
     * this.��n���@@��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40AD9BDA0068
     */
    public String getDeliveryDiv()
    {
        return this.deliveryDiv;
    }
   
    /**
     * ���M�^�C�v�̐ݒ���s���B<BR>
     * @@param l_strMutualFundType - ���M�^�C�v<BR>
     * @@roseuid 40AD9BDA0087
     */
    public void setMutualFundType(String l_strMutualFundType)
    {
        this.mutualFundType = l_strMutualFundType;
    }
   
    /**
     * this.���M�^�C�v��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40AD9BDA0097
     */
    public String getMutualFundType()
    {
        return this.mutualFundType;
    }
   
    /**
     * �����̐ݒ���s���B<BR>
     * @@param l_tsExecutionTimestamp - ����<BR>
     * @@roseuid 40AD9BDA00B6
     */
    public void setExecutionTimestamp(Timestamp l_tsExecutionTimestamp)
    {
        this.executionTimestamp = l_tsExecutionTimestamp;
    }
   
    /**
     * this.������Ԃ��B<BR>
     * @@return Timestamp
     * @@roseuid 40AD9BDA00D5
     */
    public Timestamp getExecutionTimestamp()
    {
        return this.executionTimestamp;
    }
   
    /**
     * ���ϋ敪�̐ݒ���s���B<BR>
     * @@param l_strSettlementType - ���ϋ敪<BR>
     * @@roseuid 40AD9BDA0133
     */
    public void setSettlementType(String l_strSettlementType)
    {
        this.settlementType = l_strSettlementType;
    }
   
    /**
     * this.���ϋ敪��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40AD9BDA0152
     */
    public String getSettlementType()
    {
        return this.settlementType;
    }
   
    /**
     * ���萔���敪�̐ݒ���s���B<BR>
     * @@param l_strNoCommissionDivision - ���萔���敪<BR>
     * @@roseuid 40AD9BDA0172
     */
    public void setNoCommissionDivision(String l_strNoCommissionDivision)
    {
        this.noCommissionDivision = l_strNoCommissionDivision;
    }
   
    /**
     * this.���萔���敪��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40AD9BDA0191
     */
    public String getNoCommissionDivision()
    {
        return this.noCommissionDivision;
    }
   
    /**
     * �����R�[�h�i�抷��j�̐ݒ���s���B<BR>
     * @@param l_strSwitchingSubjectMutualProductCode - �����R�[�h�i�抷��j<BR>
     * @@roseuid 40AD9BDA01FE
     */
    public void setSwitchingSubjectMutualProductCode(String l_strSwitchingSubjectMutualProductCode)
    {
        this.switchingSubjectMutualProductCode =
            l_strSwitchingSubjectMutualProductCode;
    }
   
    /**
     * this.�����R�[�h�i�抷��j��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40AD9BDA021D
     */
    public String getSwitchingSubjectMutualProductCode()
    {
        return this.switchingSubjectMutualProductCode;
    }
   
    /**
     * �����敪�̐ݒ���s���B<BR>
     * @@param l_strRequestDivision - �����敪<BR>
     * @@roseuid 40AD9BDA023D
     */
    public void setRequestDivision(String l_strRequestDivision)
    {
        this.requestDivision = l_strRequestDivision;
    }
   
    /**
     * this.�����敪��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40AD9BDA025C
     */
    public String getRequestDivision()
    {
        return this.requestDivision;
    }
   
    /**
     * ���҃R�[�h�iSONAR�j�̐ݒ���s���B<BR>
     * @@param l_strSonarTraderCode - ���҃R�[�h�iSONAR�j<BR>
     * @@roseuid 40AD9BDA027B
     */
    public void setSonarTraderCode(String l_strSonarTraderCode)
    {
        this.sonarTraderCode = l_strSonarTraderCode;
    }
   
    /**
     * this.���҃R�[�h�iSONAR�j��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40AD9BDA029A
     */
    public String getSonarTraderCode()
    {
        return this.sonarTraderCode;
    }
   
    /**
     * ���M���敪�̐ݒ���s���B<BR>
     * @@param l_strMutualFundSellDiv - ���M���敪<BR>
     * @@roseuid 40AD9BDA02BA
     */
    public void setMutualFundSellDiv(String l_strMutualFundSellDiv)
    {
        this.mutualFundSellDiv = l_strMutualFundSellDiv;
    }
   
    /**
     * this.���M���敪��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40AD9BDA02D9
     */
    public String getMutualFundSellDiv()
    {
        return this.mutualFundSellDiv;
    }
   
    /**
     * ����z�K�p���̐ݒ���s���B<BR>
     * @@param l_tsConstantValueAppDate - ����z�K�p��<BR>
     * @@roseuid 40CFE62D02FD
     */
    public void setConstantValueAppDate(Timestamp l_tsConstantValueAppDate)
    {
        this.constantValueAppDate = l_tsConstantValueAppDate;
    }
   
    /**
     * this.����z�K�p����Ԃ��B<BR>
     * @@return Timestamp
     * @@roseuid 40CFE62D030C
     */
    public Timestamp getConstantValueAppDate()
    {
        return this.constantValueAppDate;
    }
   
    /**
     * �T�Z���t�����i�抷��j�̐ݒ���s���B<BR>
     * @@param l_dblSwitchingEstimatedQty - �抷��T�Z���t����<BR>
     * @@roseuid 40D2BCFF0159
     */
    public void setSwitchingEstimatedQty(double l_dblSwitchingEstimatedQty)
    {
        this.switchingEstimatedQty = l_dblSwitchingEstimatedQty;
    }
   
    /**
     * this.�T�Z���t�����i�抷��j��Ԃ��B<BR>
     * @@return double
     * @@roseuid 40D2BCFF0169
     */
    public double getSwitchingEstimatedQty()
    {
        return this.switchingEstimatedQty;
    }
   
    /**
     * �ŋ敪�i�抷��j�̐ݒ���s���B<BR>
     * @@param l_switchingSubjectTaxDivision - �ŋ敪�i�抷��j<BR>
     * @@roseuid 40D2BCFF016A
     */
    public void setSwitchingSubjectTaxDivision(TaxTypeEnum l_switchingSubjectTaxDivision)
    {
        this.switchingSubjectTaxDivision = l_switchingSubjectTaxDivision;
    }
   
    /**
     * this.�ŋ敪�i�抷��j��Ԃ��B<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum
     * @@roseuid 40D2BCFF0178
     */
    public TaxTypeEnum getSwitchingSubjectTaxDivision()
    {
        return this.switchingSubjectTaxDivision;
    }
    
    /**
     * (set�������)
     * ������ʂ̐ݒ���s���B<BR>
     * @@param l_orderType - �������<BR>
     * @@roseuid 40AD92050133
     */
    public void setOrderType(OrderTypeEnum l_orderType) 
    {
        this.orderType = l_orderType;
    }
    
    /**
     * (get�������)
     * this.������ʂ�Ԃ��B<BR>
     * @@return OrderTypeEnum
     * @@roseuid 40AD91EC00E5
     */
    public OrderTypeEnum getOrderType() 
    {
        return orderType;
    }
    
    /**
     * (set������)
     * �������̐ݒ���s���B<BR>
     * @@param l_paymentDate - ������<BR>
     * @@roseuid 40AD92050133
     */
    public void setPaymentDate(Timestamp l_paymentDate) 
    {
        this.paymentDate = l_paymentDate;
    }
    
    /**
     * (get������)
     * this.��������Ԃ��B<BR>
     * @@return Timestamp
     * @@roseuid 40AD91EC00E5
     */
    public Timestamp getPaymentDate() 
    {
        return paymentDate;
    }  
    
    /**
     * (setCPUNo)
     * CPUNo�̐ݒ���s���B<BR>
     * @@param CPUNo - String <BR>
     * @@roseuid 40AD92050133
     */
    public void setCPUNo(String l_strCpuNo) 
    {
        this.CPUNo = l_strCpuNo;
    }
    
    /**
     * (getCPUNo)
     * this.CPUNo��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40AD91EC00E5
     */
    public String getCPUNo() 
    {
        return CPUNo;
    }
}
@
