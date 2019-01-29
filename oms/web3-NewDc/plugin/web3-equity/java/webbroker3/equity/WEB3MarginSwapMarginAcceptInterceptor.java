head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginSwapMarginAcceptInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p�������n��t�C���^�Z�v�^(WEB3MarginSwapMarginAcceptInterceptor.java)
Author Name      : 2004/9/2�S Ḗ@@��(���u) �V�K�쐬
                 : 2004/11/2 �@@���@@�C��   
                 : 2004/12/09 �����iSRA�j�@@�c�Č��Ή�
                 : 2004/12/28 �����iSRA�j�@@JavaDoc�C��
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderManager;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AcceptStatusDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.equity.data.HostEqtypeSwapAcceptParams;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p�������n��t�C���^�Z�v�^�j�B<BR>
 * <BR>
 * �������n������t���́ADB�X�V�d�l�J�X�^�}�C�Y�p�̃N���X�B<BR>
 *�iEqTypeOrderManagerPersistenceEventInterceptor�̎����j
 * @@author �@@��
 * @@version 1.0
 */
public class WEB3MarginSwapMarginAcceptInterceptor extends WEB3MarginUpdateEventInterceptor 
{
    
    /**
     * (���O�o�̓��[�e�B���e�B�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginSwapMarginAcceptInterceptor.class);   
    /**
     * �i�������n��t�L���[Params�j�B<BR>
     * <BR>
     * �y�������n��t�L���[�e�[�u���z�̂P���R�[�h�B<BR>
     * �M�p����������n�����ʒm�T�[�r�X����R�[������ꍇ�́A<BR>
     * null���Z�b�g�����B
     */
    private HostEqtypeSwapAcceptParams hostEqtypeSwapAcceptParams ;
    
    /**
     * @@roseuid 4142BBF50106
     */
    public WEB3MarginSwapMarginAcceptInterceptor() 
    {
     
    }
    
    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j<BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB<BR>
     * <BR>
     * �P�j �g�����ڃZ�b�g<BR>
     * ��������Ԃ�<BR>
     * �@@�h�����ρi�V�K�����j�FORDERED�h �܂���<BR>
     * �@@�h�������s�i�V�K�����j�FNOT_ORDERED�h �܂���<BR>
     * �@@�h�������s�i��������j�FNOT_CANCELLED�h �̏ꍇ�̂ݏ��������{����B<BR>
     * �@@�ȊO�́A���������̂܂ܕԋp���������I������B<BR>
     * <BR>
     * ��������Ԃ��h�����ρi�V�K�����j�FORDERED�h�̏ꍇ�̍X�V���e�́A<BR>
     * �@@�u�i������t�j�������n��t_���������P�ʃe�[�u��.xls�v��<BR>
     * �@@�u�i�������n������t�j[��tOK]���������P�ʃe�[�u���v�V�[�g���Q�ƁB<BR>
     * <BR>
     * ��������Ԃ��h�������s�i�V�K�����j�FNOT_ORDERED�h�̏ꍇ�̍X�V���e�́A<BR>
     * �@@�u�i������t�j�������n��t_���������P�ʃe�[�u��.xls�v��<BR>
     * �@@�u�i�������n������t�j[��t�G���[]���������P�ʃe�[�u���v���Q�ƁB<BR>
     * <BR>
     * ��������Ԃ��h�������s�i��������j�FNOT_CANCELLED�h�̏ꍇ�̍X�V���e�́A<BR>
     * �@@�u�i�����t�j�������n��t_���������P�ʃe�[�u��.xls�v��<BR>
     * �@@�u�i�������n�����t�j[�����t�G���[]���������P�ʃe�[�u���v�V�[�g�Q�ƁB<BR>
     * <BR>
     * @@param l_orderManagerPersistenceType - (�X�V�^�C�v)<BR>
     * INSERT�܂��́AUPDATE�B<BR>
     * OrderManagerPersistenceType�ɂĒ萔��`�B
     * @@param l_process - (����)<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j
     * @@param l_orderUnitParams - (�����P��Params)<BR>
     * ���������P�ʂ��ێ����鍀�ڂ̃p�����[�^�N���X�B
     * @@return EqtypeOrderUnitParams
     * @@roseuid 410DA99F0023
     */
    public EqtypeOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        EqtypeOrderUnitParams l_orderUnitParams) 
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, EqtypeOrderUnitParams)";        
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnitParams == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        OrderStatusEnum l_orderStatus = l_orderUnitParams.getOrderStatus();
        //�i�������n������t�j[��tOK]���������P�ʃe�[�u���v�V�[�g
        if (OrderStatusEnum.ORDERED.equals(l_orderStatus))
        {
            //�s�ꂩ��m�F�ς݂̒����P��
            double l_dblPrice = l_orderUnitParams.getPrice();
            if (l_orderUnitParams.getPriceIsNull())
            {
                l_orderUnitParams.setConfirmedOrderPrice(null);
            }
            else
            {
                l_orderUnitParams.setConfirmedOrderPrice(l_dblPrice);
            }

            double l_dblEstimatePrice = l_orderUnitParams.getEstimatedPrice();
            //�s�ꂩ��m�F�ς݂̊T�Z��n���
            if (l_orderUnitParams.getEstimatedPriceIsNull())
            {
                l_orderUnitParams.setConfirmedEstimatedPrice(null);
            }
            else
            {
                l_orderUnitParams.setConfirmedEstimatedPrice(l_dblEstimatePrice);
            }

            //�s�ꂩ��m�F�ς݂̎��s����
            EqTypeExecutionConditionType l_type = l_orderUnitParams.getExecutionConditionType();
            if (l_type == null)
            {
                l_orderUnitParams.setConfirmedExecConditionType(null);
            }
            else
            {
                l_orderUnitParams.setConfirmedExecConditionType(l_type);
            }
            
            //�s�ꂩ��m�F�ς݂̒l�i����            
            l_orderUnitParams.setConfirmedPriceConditionType(
                l_orderUnitParams.getPriceConditionType());
        }
        //�������n������t�j[��t�G���[]���������P�ʃe�[�u��
        else if (OrderStatusEnum.NOT_ORDERED.equals(l_orderStatus))
        {
            l_orderUnitParams.setErrorReasonCode(
                this.hostEqtypeSwapAcceptParams.getErrorMessage());
        }
        //������Ԃ��h�������s�i��������j�FNOT_CANCELLED�h�̏ꍇ
        else if (OrderStatusEnum.NOT_CANCELLED.equals(l_orderStatus))
        {
            // �T�Z��n���
            if (l_orderUnitParams.getConfirmedEstimatedPriceIsNull())
            {
                l_orderUnitParams.setEstimatedPrice(null);    
            }
            else
            {
                l_orderUnitParams.setEstimatedPrice(
                    l_orderUnitParams.getConfirmedEstimatedPrice());
            }
            
            //���������E����敪
            l_orderUnitParams.setModifyCancelType(
                WEB3ModifyCancelTypeDef.CANCEL_ERROR);
            
            //�����G���[���R�R�[�h
            l_orderUnitParams.setErrorReasonCode(
                this.hostEqtypeSwapAcceptParams.getErrorMessage());
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
    
    /**
     * �i�M�p�������n��t�C���^�Z�v�^�j�B<BR>
     * <BR>
     * �R���X�g���N�^�B<BR>
     * �����Ɏw�肳�ꂽ�I�u�W�F�N�g���A�����̃v���p�e�B�ɐݒ肷��B<BR>
     * @@param l_swapMarginAcceptCueParams - �������n��t�L���[Params<BR>
     * �@@�@@�@@�y�������n��t�L���[�e�[�u���z�̂P���R�[�h�B<BR>
     * @@return webbroker3.margin.WEB3MarginSwapMarginAcceptInterceptor
     * @@roseuid 410DA99F0032
     */
    public WEB3MarginSwapMarginAcceptInterceptor(HostEqtypeSwapAcceptParams l_hostEqtypeSwapAcceptParams) 
    {
        this.hostEqtypeSwapAcceptParams = l_hostEqtypeSwapAcceptParams;
    }
    
    /**
     * �i�X�V�l�ݒ�j�B<BR>
     * <BR>
     * �imutate���\�b�h�̎����j<BR>
     * ��������Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * <BR>
     * �P�j�@@�����P�ʃI�u�W�F�N�g�擾<BR>
     * <BR>
     * �@@�����̒����P��Params.�����P�ʂh�c�ɊY�����钍���P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j�@@�g�����ڃZ�b�g<BR>
     * �@@�������G���[���R�R�[�h<BR>
     * �@@�@@[this.�������n��t�L���[Params == null �̏ꍇ�i�������ʒm����̃R�[�����j]�A�܂���<BR>
     * �@@�@@[this.�������n��t�L���[Params.������t���� == �h������t�����h�̏ꍇ]<BR>
     * �@@�@@�@@�p�����[�^.��������Params.�����G���[���R�R�[�h�ɁA�h0000:����h���Z�b�g����B<BR>
     * �@@�@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@�@@�p�����[�^.��������Params.�����G���[���R�R�[�h�ɁA�����P�ʃI�u�W�F�N�g�̓������ڂ���<BR>
     * �@@�@@�@@�l���Z�b�g����B<BR>
     * <BR>
     * �@@�����̑��̍���<BR>�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@
     * �@@�p�����[�^.��������Params�̊g�����ڂɁA�����P�ʃI�u�W�F�N�g�̓������ڂ���l���Z�b�g���A<BR>
     * �@@�ԋp����B
     * @@param l_orderManagerPersistenceType - (�X�V�^�C�v)<BR>
     * INSERT�܂��́AUPDATE�B<BR>
     * EqTypeOrderManagerPersistenceType�ɂĒ萔��`�B<BR>
     * <BR>
     * @@param l_orderManagerPersistenceContext - (����)<BR>
     * �iEqTypeOrderManagerPersistenceContext�ɂĒ萔��`�j<BR>
     * <BR>
     * @@param l_orderActionParams - (������������Params)<BR>
     * ���������������ێ����鍀�ڂ̃p�����[�^�N���X�B<BR>
     * @@return EqtypeOrderActionParams
     * @@roseuid 4110C514004A
     */
    public EqtypeOrderActionParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        EqtypeOrderActionParams l_orderActionParams)
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, EqtypeOrderActionParams)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        EqTypeOrderManager l_orderMgr = (EqTypeOrderManager) l_tradingMod.getOrderManager();
        EqTypeOrderUnit l_orderUnit = null;
        try 
        {
            l_orderUnit =
                (EqTypeOrderUnit)l_orderMgr.getOrderUnit(
                    l_orderActionParams.getOrderUnitId());
        } 
        catch (NotFoundException l_ex) 
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        l_orderActionParams =
            super.mutate(
                l_orderManagerPersistenceType,
                l_orderManagerPersistenceContext,
                l_orderActionParams);
        
        if (this.hostEqtypeSwapAcceptParams == null ||
            WEB3AcceptStatusDef.OVER.equals(this.hostEqtypeSwapAcceptParams.getAcceptStatus()))
        {
            l_orderActionParams.setErrorReasonCode(
                WEB3ErrorReasonCodeDef.NORMAL);
        }
        else
        {
            l_orderActionParams.setErrorReasonCode(
                l_orderUnitRow.getErrorReasonCode());
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_orderActionParams;
    }
}
@
