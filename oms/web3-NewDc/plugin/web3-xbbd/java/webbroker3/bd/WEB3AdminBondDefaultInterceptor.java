head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondDefaultInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Ǘ��҃f�t�H���g�C���^�Z�v�^(WEB3AdminBondDefaultInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ����� (���u) �V�K�쐬
Revesion History : 2007/07/25 ������(���u) �d�l�ύX���f��NO.240
*/

package webbroker3.bd;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.BatchedQuery;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow;

/**
 * (���Ǘ��҃f�t�H���g�C���^�Z�v�^)<BR>
 * ���Ǘ��҃f�t�H���g�C���^�Z�v�^�N���X<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0 
 */
public class WEB3AdminBondDefaultInterceptor implements BondOrderManagerPersistenceEventInterceptor
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondDefaultInterceptor.class);
    
    /**
     * (�Ǘ���)<BR>
     * �Ǘ���<BR>
     */
    private WEB3Administrator administrator;
    
    /**
     * (���������)<BR>
     * ���������<BR>
     */
    private WEB3BondExecuteDateInfo bondExecuteDateInfo;
    
    /**
     * (����n����v�Z����)<BR>
     * ����n����v�Z����<BR>
     */
    private WEB3BondEstimatedPriceCalcResult estimatedPriceCalcResult;
    
    /**
     * (�J�X�g�f�B�A���R�[�h)<BR>
     * �J�X�g�f�B�A���R�[�h<BR>
     */
    private String custodianCode;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    private WEB3BondProduct bondProduct;
    
    /**
     * (���Ǘ��҃f�t�H���g�C���^�Z�v�^)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 44D878160295
     */
    public WEB3AdminBondDefaultInterceptor() 
    {
     
    }
    
    /**
     * (�i�����P�ʁj�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j <BR>
     * <BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B <BR>
     * <BR>
     * �P�j�@@�����P��Params��ԋp����<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB <BR>
     * @@param l_persistenceType - (arg0)<BR>
     * @@param l_context - (arg1)<BR>
     * @@param l_params - (arg2)<BR>
     * @@return BondOrderUnitParams
     * @@roseuid 44D966030119
     */
    public BondOrderUnitParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_context, 
        BondOrderUnitParams l_params) 
    {
        //�P�j�@@�����P��Params��ԋp����
        return l_params;
    }
    
    /**
     * (�i���������j�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j<BR>
     * <BR>
     * ��������Params�Ɋg������(*)�ݒ肵�ԋp����B <BR>
     * <BR>
     * �P�j�@@�g�����ڃZ�b�g <BR>
     *   �p�����[�^.��������Params�̊g�����ڂɒl���Z�b�g���A�ԋp����B <BR>
     * �@@���ڐݒ���e��<BR>
     * �@@�u���V�K����_�����������e�[�u��DB�X�V�d�l.xls�v<BR>
     * �@@�u��������t_�����������e�[�u��DB�X�V�d�l.xls�v<BR>
     * �@@�u���V�K���_�����������e�[�u��DB�X�V�d�l.xls�v<BR>
     * �@@�u�������_�����������e�[�u��DB�X�V�d�l.xls�v<BR>
     * �@@�u����������i�����_�O�����j_�����������e�[�u��DB�X�V�d�l.xls�v<BR>
     * �@@�u����������i����_�O�����j_�����������e�[�u��DB�X�V�d�l.xls�v<BR>
     * �@@�u�������t_�����������e�[�u��DB�X�V�d�l.xls�v<BR>
     * �@@�u����������i�����_�������j_�����������e�[�u��DB�X�V�d�l.xls�v<BR>
     * �@@�u����������i����_�������j_�����������e�[�u��DB�X�V�d�l.xls�v<BR>
     * �@@�Q�� <BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB <BR>
     * @@param l_persistenceType - (arg0)<BR>
     * @@param l_context - (arg1)<BR>
     * @@param l_params - (arg2)<BR>
     * @@return BondOrderActionParams
     * @@roseuid 44D8787D0018
     */
    public BondOrderActionParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_context,
        BondOrderActionParams l_params) 
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, BondOrderActionParams)";
        log.entering(STR_METHOD_NAME);
       
        if (l_params == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j�g�����ڃZ�b�g 
        long l_lngOrderUnitId = l_params.getOrderUnitId();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondOrderManager l_orderManager = (WEB3BondOrderManager) l_tradingMod.getOrderManager();
        BondOrderUnit l_bondOrderUnit = null;
        
        // �����P�ʃI�u�W�F�N�g�擾
        l_bondOrderUnit = (BondOrderUnit) l_orderManager.getOrderUnit(l_lngOrderUnitId);

        BondOrderUnitRow l_bondOrderUnitRow = (BondOrderUnitRow) l_bondOrderUnit.getDataSourceObject();
        
        //�������P�ʃe�[�u��.�����ID
        if (!l_bondOrderUnitRow.getTraderIdIsNull())
        {
            l_params.setTraderId(l_bondOrderUnitRow.getTraderId());
        }
        
        //�������P�ʃe�[�u��.���
        l_params.setDealType(l_bondOrderUnitRow.getDealType());
        
        //�������P�ʃe�[�u��.�����P��
        if (!l_bondOrderUnitRow.getPriceIsNull())
        {
            l_params.setPrice(l_bondOrderUnitRow.getPrice());
        }
       
        //�������P�ʃe�[�u��.�w�l
        if (!l_bondOrderUnitRow.getLimitPriceIsNull())
        {
            l_params.setLimitPrice(l_bondOrderUnitRow.getLimitPrice());
        }
        
        //�������P�ʃe�[�u��.����
        l_params.setExecDate(l_bondOrderUnitRow.getExecDate());
        
        //�������P�ʃe�[�u��.���n����
        l_params.setForeignExecDate(l_bondOrderUnitRow.getForeignExecDate());
        
        //�������P�ʃe�[�u��.��n��
        l_params.setDeliveryDate(l_bondOrderUnitRow.getDeliveryDate());
        
        //�������P�ʃe�[�u��.���n��n��
        l_params.setForeignDeliveryDate(l_bondOrderUnitRow.getForeignDeliveryDate());
        
        //�������P�ʃe�[�u��.������
        if (l_bondOrderUnitRow.getPaymentDateIsSet())
        {
            l_params.setPaymentDate(l_bondOrderUnitRow.getPaymentDate());
        }
        
        //�������P�ʃe�[�u��.��בփ��[�g
        if (!l_bondOrderUnitRow.getBaseFxRateIsNull())
        {
            l_params.setBaseFxRate(l_bondOrderUnitRow.getBaseFxRate());
        }
        
        //�������P�ʃe�[�u��.���בփ��[�g
        if (!l_bondOrderUnitRow.getExecFxRateIsNull())
        {
            l_params.setExecFxRate(l_bondOrderUnitRow.getExecFxRate());
        }
        
        //�������P�ʃe�[�u��.��������i�~�݁j
        if (!l_bondOrderUnitRow.getTradingPriceIsNull())
        {
            l_params.setTradingPrice(l_bondOrderUnitRow.getTradingPrice());
        }
        
        //�������P�ʃe�[�u��.��������i�O�݁j
        if (!l_bondOrderUnitRow.getForeignTradingPriceIsNull())
        {
            l_params.setForeignTradingPrice(l_bondOrderUnitRow.getForeignTradingPrice());
        }
        
        //�������P�ʃe�[�u��.�o�ߗ��q�i�~�݁j
        if (!l_bondOrderUnitRow.getAccruedInterestIsNull())
        {
            l_params.setAccruedInterest(l_bondOrderUnitRow.getAccruedInterest());
        }
        
        //�������P�ʃe�[�u��.�o�ߗ��q�i�O�݁j
        if (!l_bondOrderUnitRow.getForeignAccruedInterestIsNull())
        {
            l_params.setForeignAccruedInterest(l_bondOrderUnitRow.getForeignAccruedInterest());
        }
        
        //�������P�ʃe�[�u��.��n����i�~�݁j
        if (!l_bondOrderUnitRow.getEstimatedPriceIsNull())
        {
            l_params.setEstimatedPrice(l_bondOrderUnitRow.getEstimatedPrice());
        }
        //�������P�ʃe�[�u��.��n����i�O�݁j
        if (!l_bondOrderUnitRow.getForeignEstimatedPriceIsNull())
        {
            l_params.setForeignEstimatedPrice(l_bondOrderUnitRow.getForeignEstimatedPrice());
        }
        
        //�������P�ʃe�[�u��.���r���������z
        if (!l_bondOrderUnitRow.getAdjustmentBeforeMaturityIsNull())
        {
            l_params.setAdjustmentBeforeMaturity(l_bondOrderUnitRow.getAdjustmentBeforeMaturity());
        }
        
        //�������P�ʃe�[�u��.�o�ߓ���
        if (!l_bondOrderUnitRow.getElapsedDaysIsNull())
        {
            l_params.setElapsedDays(l_bondOrderUnitRow.getElapsedDays());
        }
        
        //�������P�ʃe�[�u��.�����
        if (!l_bondOrderUnitRow.getCalcBaseDaysIsNull())
        {
            l_params.setCalcBaseDays(l_bondOrderUnitRow.getCalcBaseDays());
        }
        
        //�������P�ʃe�[�u��.�J�X�g�f�B�A���R�[�h
        l_params.setCustodianCode(l_bondOrderUnitRow.getCustodianCode());
        
        //�������P�ʃe�[�u��.�����o�H�敪
        l_params.setOrderRootDiv(l_bondOrderUnitRow.getOrderRootDiv());
        
        //�������P�ʃe�[�u��.�Ǘ��҃R�[�h
        l_params.setAdministratorCode(l_bondOrderUnitRow.getAdministratorCode());
        
        //�������P�ʃe�[�u��.�����G���[���R�R�[�h
        l_params.setErrorReasonCode(l_bondOrderUnitRow.getErrorReasonCode());
        
        //�b�蛔��F2006/09/27 ����� �������敪
        l_params.setOrderExecStatus(l_bondOrderUnitRow.getOrderExecStatus());
   
        log.exiting(STR_METHOD_NAME);
        return l_params;
    }
    
    /**
     * (�i���j�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j<BR>
     * <BR>
     * �P�jBondOrderExecutionParams��Ԃ�<BR>
     * @@param l_persistenceType - (arg0)<BR>
     * @@param l_context - (arg1)<BR>
     * @@param l_params - (arg2)<BR>
     * @@return BondOrderExecutionParams
     * @@roseuid 44D9601C002E
     */
    public BondOrderExecutionParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_context, 
        BondOrderExecutionParams l_params) 
    {
        //�P�jBondOrderExecutionParams��Ԃ�
        return l_params;
    }
    
    /**
     * null��Ԃ��B<BR>
     * @@param l_persistenceType - (arg0)<BR>
     * @@param l_class - (arg1)<BR>
     * @@roseuid 44D8790D031A
     */
    public BatchedQuery getQueryToExecute(OrderManagerPersistenceType l_persistenceType, Class l_class)
    {
        return null;
    }
    
    /**
     * (get�Ǘ���)<BR>
     * �Ǘ��҂�ԋp����<BR>
     * @@return WEB3Administrator
     * @@roseuid 44D8780A00CF
     */
    public WEB3Administrator getAdministrator() 
    {
        return this.administrator;
    }
    
    /**
     * (set�Ǘ���)<BR>
     * �Ǘ��҂��Z�b�g����<BR>
     * @@param l_administrator - (�Ǘ���)<BR>
     * �Ǘ���<BR>
     * @@roseuid 44D8780A00C0
     */
    public void setAdministrator(WEB3Administrator l_administrator) 
    {
        this.administrator = l_administrator;
    }
    
    /**
     * (get���������)<BR>
     * ����������ԋp����<BR>
     * @@return WEB3BondExecuteDateInfo
     * @@roseuid 44D965650000
     */
    public WEB3BondExecuteDateInfo getBondExecuteDateInfo() 
    {
        return this.bondExecuteDateInfo;
    }
    
    /**
     * (set���������)<BR>
     * ����������ݒ肷��<BR>
     * @@param l_bondExecuteDateInfo - (���������)<BR>
     * ���������<BR>
     * @@roseuid 44D96565002E
     */
    public void setBondExecuteDateInfo(WEB3BondExecuteDateInfo l_bondExecuteDateInfo) 
    {
        this.bondExecuteDateInfo = l_bondExecuteDateInfo;
    }
    
    /**
     * (set����n����v�Z����)<BR>
     * ����n����v�Z���ʂ�ݒ肷��<BR>
     * @@param l_estimatedPriceCalcResult - (����n����v�Z����)<BR>
     * ����n����v�Z���ʃI�u�W�F�N�g<BR>
     * @@roseuid 44D96565005D
     */
    public void setBondEstimatedPriceCalcResult(WEB3BondEstimatedPriceCalcResult l_estimatedPriceCalcResult) 
    {
        this.estimatedPriceCalcResult = l_estimatedPriceCalcResult;
    }
    
    /**
     * (get����n����v�Z����)<BR>
     * ����n����v�Z���ʂ�ԋp����<BR>
     * @@return WEB3BondEstimatedPriceCalcResult
     * @@roseuid 44D96565007D
     */
    public WEB3BondEstimatedPriceCalcResult getBondEstimatedPriceCalcResult() 
    {
        return this.estimatedPriceCalcResult;
    }
    
    /**
     * (get�J�X�g�f�B�A���R�[�h)<BR>
     * �J�X�g�f�B�A���R�[�h��ԋp����<BR>
     * @@return String
     * @@roseuid 44D9656500BB
     */
    public String getCustodianCode() 
    {
        return this.custodianCode;
    }
    
    /**
     * (set�J�X�g�f�B�A���R�[�h)<BR>
     * �J�X�g�f�B�A���R�[�h��ݒ肷��<BR>
     * @@param l_strCustodianCode - (�J�X�g�f�B�A���R�[�h)<BR>
     * �J�X�g�f�B�A���R�[�h<BR>
     * @@roseuid 44D9656500DA
     */
    public void setCustodianCode(String l_strCustodianCode) 
    {
        this.custodianCode = l_strCustodianCode;
    }
    
    /**
     * (get������)<BR>
     * ��������ԋp����<BR>
     * @@return String
     * @@roseuid 44D965970399
     */
    public WEB3BondProduct getBondProduct() 
    {
        return this.bondProduct;
    }
    
    /**
     * (set������)<BR>
     * ��������ݒ肷��<BR>
     * @@param l_bondProduct - (������)<BR>
     * ������<BR>
     * @@roseuid 44D9659703C8
     */
    public void setBondProduct(WEB3BondProduct l_bondProduct) 
    {
        this.bondProduct = l_bondProduct;
    }
}
@
