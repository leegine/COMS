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
filename	WEB3EquityOrderAcceptPersistenceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����������t�C���^�Z�v�^(WEB3EquityOrderAcceptPersistenceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/19 �^�� (���u) �V�K�쐬
Revesion History : 2004/09/20 羐� (���u) �C��
Revesion History : 2004/09/24 �@@�� (���u) �C��
Revesion History : 2004/12/10 ���� (SRA) �C��
Revesion History : 2005/01/05 ���� (SRA) JavaDoc�C��
Revesion History : 2006/07/12 �юu�� (���u) �c�a�X�V�d�l155
Revesion History : 2006/08/01 ���r (���u) �c�a�X�V�d�l162
Revesion History : 2006/11/01 �đo�g (���u) �c�a�X�V�d�lNo.172
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3HostOrderAcceptStatusDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.equity.data.HostEqtypeOrderAcceptParams;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.util.WEB3LogUtility;

/**
 * �i����������t�C���^�Z�v�^�j�B<BR>
 * <BR>
 * ���������P�ʁA�������������e�[�u���̃J�X�^�}�C�Y���ڂɊm���̒l���Z�b�g����B
 * @@author lufaxu
 * @@version 1.0
 */
public class WEB3EquityOrderAcceptPersistenceInterceptor
    extends WEB3EquityOrderManagerPersistenceEventInterceptor
{

    /**
     * (���O�o�̓��[�e�B���e�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityOrderAcceptPersistenceInterceptor.class);

    /**
     * (����������t�L���[Params)<BR>
     *�y����������t�L���[�e�[�u���z�̂P���R�[�h�B<BR>
     */
    private HostEqtypeOrderAcceptParams hostEqtypeOrderAcceptParams;

    /**
     * �i����������t�C���^�Z�v�^�j<BR>
     * <BR>
     * �R���X�g���N�^�B<BR>
     */
    public WEB3EquityOrderAcceptPersistenceInterceptor()
    {
        super();
    }

    /**
     * (����������t�C���^�Z�v�^) <BR>
     * �R���X�g���N�^�B <BR>
     * �������A�N���X�̃v���p�e�B�ɃZ�b�g����B <BR>
     * @@param l_hostEqtypeOrderAcceptParams - (�����������͎�t�L���[Params) <BR>
     * �y����������t�L���[�e�[�u���z�̂P���R�[�h�B <BR>
     * @@return webbroker3.equity.WEB3EquityOrderNotifyIntercepter
     * @@roseuid 403EF16602DD
     */
    public WEB3EquityOrderAcceptPersistenceInterceptor(
        HostEqtypeOrderAcceptParams l_hostEqtypeOrderAcceptParams)
    {
        this.hostEqtypeOrderAcceptParams = l_hostEqtypeOrderAcceptParams;
    }

    /**
     * �i�X�V�l�ݒ�j<BR>
     * �imutate���\�b�h�̎����j<BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB<BR>
     * <BR>
     * �P�j �g�����ڃZ�b�g<BR>
     * �i������Ԃ��h�����ρi�V�K�����j�FORDERED�h�܂��́h�������s�i�V�K�����j�F<BR>
     * NOT_ORDERED�h�̏ꍇ�̂�<BR>
     * �@@���������{����j<BR>
     * <BR>
     * ������Ԃ��h�����ρi�V�K�����j�FORDERED�h�̏ꍇ�̍X�V���e�́A<BR>
     * �u����������t_���������P�ʃe�[�u��.xls�v<BR>
     * �́uDB�X�V�d�l[��tOK]�i���������P�ʃe�[�u���j�v�V�[�g���Q�ƁB<BR>
     * ������Ԃ��h�������s�i�V�K�����j�FNOT_ORDERED�h�̏ꍇ�̍X�V���e�́A<BR>
     * �u����������t_���������P�ʃe�[�u��.xls�v<BR>
     * �́uDB�X�V�d�l[��t�G���[]�i���������P�ʃe�[�u���j�v�V�[�g���Q�ƁB<BR>
     * <BR>
     * @@param l_orderManagerPersistenceType - �i�X�V�^�C�v�j<BR>
     * INSERT�܂��́AUPDATE�B<BR>
     * OrderManagerPersistenceType�ɂĒ萔��`�B<BR>
     * <BR>
     * @@param l_orderManagerPersistenceContext - �i�����j<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j<BR>
     * <BR>
     * @@param l_orderUnitParams - �i�����P��Params�j<BR>
     * ���������P�ʂ��ێ����鍀�ڂ̃p�����[�^�N���X�B<BR>
     * @@return EqtypeOrderUnitParams
     * @@roseuid 4143CE5F01B6
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
        
        // ������Ԃ��h�����ρi�V�K�����j�FORDERED�h�̏ꍇ�̍X�V���e�́A
        //�u����������t_���������P�ʃe�[�u��.xls�v��
        //�uDB�X�V�d�l[��tOK]�i���������P�ʃe�[�u���j�v�V�[�g���Q��
        if (OrderStatusEnum.ORDERED.equals(l_orderUnitParams.getOrderStatus()))
        {
            //�s�ꂩ��m�F�ς݂̒����P��
            if (l_orderUnitParams.getPriceIsNull())
            {
                l_orderUnitParams.setConfirmedOrderPrice(null);
            }
            else
            {
                l_orderUnitParams.setConfirmedOrderPrice(
                    l_orderUnitParams.getPrice());
            }
            if (l_orderUnitParams.getEstimatedPriceIsNull())
            {
                //�s�ꂩ��m�F�ς݂̊T�Z��n���
                l_orderUnitParams.setConfirmedEstimatedPrice(null);
            }
            else
            {
                //�s�ꂩ��m�F�ς݂̊T�Z��n���
                l_orderUnitParams.setConfirmedEstimatedPrice(
                    l_orderUnitParams.getEstimatedPrice());                
            }

            //�s�ꂩ��m�F�ς݂̎��s����
            l_orderUnitParams.setConfirmedExecConditionType(
                l_orderUnitParams.getExecutionConditionType());
                
            //�s�ꂩ��m�F�ς݂̒l�i����
            l_orderUnitParams.setConfirmedPriceConditionType(
                l_orderUnitParams.getPriceConditionType());
            
            //�����o�H�敪
            if (hostEqtypeOrderAcceptParams != null)
            {
                //������t[��tOK]�̏ꍇ
                l_orderUnitParams.setSubmitOrderRouteDiv(
                    hostEqtypeOrderAcceptParams.getSubmitOrderRouteDiv());
            }
            
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitParams.getOrderConditionType()))
            {
                //����������
	            l_orderUnitParams.setOrgOrderConditionType(
	                l_orderUnitParams.getOrderConditionType());
	            
	            //�������������Z�q
	            l_orderUnitParams.setOrgOrderCondOperator(
	                l_orderUnitParams.getOrderCondOperator());
	            
	            //���t�w�l��l
	            if (l_orderUnitParams.getStopOrderPriceIsNull())
	            {
	                l_orderUnitParams.setOrgStopOrderPrice(null);
	            }
	            else 
	            {
	                l_orderUnitParams.setOrgStopOrderPrice(
	                    l_orderUnitParams.getStopOrderPrice());
	            }
	            
	            //��������
	            l_orderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);
	            
	            //�����������Z�q
	            l_orderUnitParams.setOrderCondOperator(null);
	            
	            //�t�w�l��l
	            l_orderUnitParams.setStopOrderPrice(null);
	            
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                WEB3GentradeAccountManager l_accManager =
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                Branch l_branch = null;
                try
                {
                    l_branch = l_accManager.getBranch(l_orderUnitParams.getBranchId());
                }
                catch(NotFoundException l_nfe)
                {
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                
                WEB3GentradeInstitution l_institution =
                    (WEB3GentradeInstitution)l_branch.getInstitution();
                boolean l_blnIsSubmitOrderDelayDisregard =
                    l_institution.isSubmitOrderDelayDisregard();

            }
        }
        //������Ԃ��h�������s�i�V�K�����j�FNOT_ORDERED�h�̏ꍇ�̍X�V���e�́A
        //�u����������t_���������P�ʃe�[�u��.xls�v��
        //�uDB�X�V�d�l[��t�G���[]�i���������P�ʃe�[�u���j�v�V�[�g���Q�ƁB
        else if (OrderStatusEnum.NOT_ORDERED.equals(l_orderUnitParams.getOrderStatus()))
        {
            if (hostEqtypeOrderAcceptParams != null)
            {
                //������t[��t�G���[]�̏ꍇ
                
                //�����o�H�敪
                l_orderUnitParams.setSubmitOrderRouteDiv(
                    hostEqtypeOrderAcceptParams.getSubmitOrderRouteDiv());
                
                //�����G���[���R�R�[�h
                l_orderUnitParams.setErrorReasonCode(
                    hostEqtypeOrderAcceptParams.getErrorMessage());
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
    
    /**
     * (�X�V�l�ݒ�)<BR>
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
     * �@@�@@[this.����������t�L���[Params == null �̏ꍇ<BR>
     * �i�������ʒm����̃R�[�����j]�A�܂���<BR>
     * �@@�@@[this.����������t�L���[Params.������t���� == <BR>
     * �h������t�����h�̏ꍇ]<BR>
     * �@@�@@�@@�p�����[�^.��������Params.�����G���[���R�R�[�h�ɁA<BR>
     * �h0000:����h���Z�b�g����B<BR>
     * �@@�@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@�@@�p�����[�^.��������Params.�����G���[���R�R�[�h�ɁA<BR>
     * �����P�ʃI�u�W�F�N�g�̓������ڂ���<BR>
     * �@@�@@�@@�l���Z�b�g����B<BR>
     * �@@<BR>
     * �@@�����̑��̍��ځ@@<BR>
     * �@@�p�����[�^.��������Params�̊g�����ڂɁA<BR>
     * �����P�ʃI�u�W�F�N�g�̓������ڂ���l���Z�b�g���A<BR>
     * �@@�ԋp����B<BR>
     * <BR>
     * @@param l_orderManagerPersistenceType - (�X�V�^�C�v)<BR>
     * INSERT�܂��́AUPDATE�B<BR>
     * OrderManagerPersistenceType�ɂĒ萔��`�B<BR>
     * <BR>
     * @@param l_orderManagerPersistenceContext - (����)<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j<BR>
     * <BR>
     * @@param l_orderActionParams - (������������Params)<BR>
     * ���������������ێ����鍀�ڂ̃p�����[�^�N���X�B
     * @@return EqtypeOrderActionParams
     * @@roseuid 4110B75A0173
     */
    public EqtypeOrderActionParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        EqtypeOrderActionParams l_orderActionParams) 
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, EqtypeOrderActionParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderActionParams == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        OrderManager l_orderMgr = l_tradingMod.getOrderManager();
        EqTypeOrderUnit l_orderUnit = null;
        try
        {
            l_orderUnit = (EqTypeOrderUnit)l_orderMgr.getOrderUnit(l_orderActionParams.order_unit_id);
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        l_orderActionParams =
            super.mutate(
                l_orderManagerPersistenceType,
                l_orderManagerPersistenceContext,
                l_orderActionParams);
        
        // �����G���[���R�R�[�h
        if (this.hostEqtypeOrderAcceptParams == null)
        {
            //�����ʒm�̏ꍇ
            l_orderActionParams.setErrorReasonCode(
                WEB3ErrorReasonCodeDef.NORMAL);
        }
        else
        {
            if (this.hostEqtypeOrderAcceptParams.getAcceptStatus().equals(WEB3HostOrderAcceptStatusDef.ORDER_ACCEPT_COMPLETE))
            {
                //������t[��tOK]�̏ꍇ
                l_orderActionParams.setErrorReasonCode(
                    WEB3ErrorReasonCodeDef.NORMAL);
            }
            else
            {
                //������t[��t�G���[]�̏ꍇ
                l_orderActionParams.setErrorReasonCode(
                    l_orderUnitRow.getErrorReasonCode());
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_orderActionParams;
    }
}
@
