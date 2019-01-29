head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.23.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioOrderManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���o�������}�l�[�W��(WEB3AioOrderManager)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 ��O�� (���u) �V�K�쐬
                   2004/10/25 ������ (���u) ���r���[ 
                   2004/12/06 ���E (���u) �c�Ή�
                   2005/02/16 ���� (���u) �c�Ή�
                   2007/1/2  �����q (���u) �d�l�ύX�E���f��668 
Revesion History : 2007/10/16  �����q (���u) �d�l�ύX�E���f��807
Revesion History : 2007/10/26  �����q (���u) �d�l�ύX�E���f��817
Revesion History : 2008/09/22  ���u�� (���u) �d�l�ύX�E���f��992
Revesion History : 2009/03/12  �đo�g (���u) �d�l�ύX�E���f��1109
Revesion History : 2009/03/16  �Ԑi (���u) �d�l�ύX�E���f��1139
Revesion History : 2009/03/18  �Ԑi (���u) �d�l�ύX�E���f��1121
*/

package webbroker3.aio;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.stdimpls.AioOrderManagerImpl;
import com.fitechlabs.xtrade.plugin.tc.xbaio.stdimpls.AioProductTypeOrderManagerReusableValidations;

import webbroker3.aio.data.BankCashTransferStatusRow;
import webbroker3.aio.data.CompBankConditionRow;
import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.UwgTransferStatusParams;
import webbroker3.aio.data.UwgTransferStatusRow;
import webbroker3.aio.define.WEB3AioProcessManagementIdDivDef;
import webbroker3.aio.define.WEB3OrderCancelPossibleDef;
import webbroker3.aio.define.WEB3OrderSendPossibleDef;
import webbroker3.aio.message.WEB3AioFinancialInstitutionUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FeqFirstTransferFlagDef;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.common.define.WEB3OtherOrgCodeDef;
import webbroker3.common.define.WEB3ProcDivDef;
import webbroker3.common.define.WEB3ResidentDef;
import webbroker3.common.define.WEB3ResultStatusFlagDef;
import webbroker3.common.define.WEB3SendRcvDivDef;
import webbroker3.common.define.WEB3ServiceDateDivDef;
import webbroker3.common.define.WEB3ServiceDivDef;
import webbroker3.common.define.WEB3TheDayTransferDef;
import webbroker3.common.define.WEB3TradingPwdEnvDef;
import webbroker3.common.define.WEB3TransactionStatusDef;
import webbroker3.common.define.WEB3TransferStatusDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeFinInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.FinInstitutionAvailableRow;
import webbroker3.gentrade.data.OtherOrgOutOfSrvDateRow;
import webbroker3.gentrade.data.OtherOrgOutOfSrvWeekRow;
import webbroker3.gentrade.data.OtherOrgSrvTimeRow;
import webbroker3.gentrade.data.ProcessManagementRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (���o�������}�l�[�W��)<BR>
 * ���o�������}�l�[�W���N���X<BR>
 * �iAioOrderManager�̊g���N���X�j
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */ 

public class WEB3AioOrderManager extends AioOrderManagerImpl 
{    
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioOrderManager.class);
    /**
     * (validate����)<BR>
     * �������ʃ`�F�b�N�����{����B <BR>
     * <BR>
     * �ȉ��̃`�F�b�N���s���B <BR>
     * �@@�|��t���ԃ`�F�b�N <BR>
     * �@@�|�V�X�e����~���`�F�b�N <BR>
     * �@@�|�ڋq�̃`�F�b�N�i�x�q�A�Ǘ����b�N���j <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i���o�������jvalidate�����v�Q�ƁB<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@throws OrderValidationException, WEB3BaseException
     * @@roseuid 40EBF6590242
     */
    public void validateOrder(SubAccount l_subAccount) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateOrder(SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 ���o�������R���ʃ`�F�b�N�̃I�u�W�F�N�g���擾����B
        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations = 
            (WEB3AioProductTypeOrderManagerReusableValidations)
            AioProductTypeOrderManagerReusableValidations.getInstance();
                  
        //1.2 ��t���ԃ`�F�b�N/�V�X�e��������~�`�F�b�N���s���B 
        //������ԊǗ�.validate������t�\( )
        log.debug("������ԊǗ�.validate������t�\( ).");
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
              
        //1.3 ���ʃ`�F�b�N�������s���B 
        try
        {        
            log.debug("���ʃ`�F�b�N�������s���B");
            l_reusableValidations.commonFirstValidationsForAllOperations(l_subAccount);
        }
        catch (OrderValidationException l_ex)
        {
            log.error("Error in validate����", l_ex);
            throw new WEB3SystemLayerException(
                l_ex.getValidationResult().getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //�����`�F�b�N�I�u�W�F�N�g���擾����
        FinApp l_finApp = GtlUtils.getFinApp();
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator(); 

        //1.3.1 �ڋq���b�N�̃`�F�b�N���s���B 
        //�|�����`�F�b�N.validate����\�ڋq()���R�[������B
        OrderValidationResult l_validationResult =
            l_orderValidator.validateSubAccountForTrading(l_subAccount);
            
        log.debug("�����`�F�b�N.validate����\�ڋq(): isFailedResult = " + 
                l_validationResult.getProcessingResult().isFailedResult());
        
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("�����~�ڋq�G���[");
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�V�K����)<BR>
     * �ivalidateNewOrder�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �V�K�����̔����R�����s���B<BR>
     * <BR>
     * �P�j�������ʃ`�F�b�N�����{����B <BR>
     *    this.validate����()���\�b�h���R�[������B<BR>
     * <BR>
     *    �ȉ��̃`�F�b�N���s���B <BR>
     * �@@   �|��t���ԃ`�F�b�N <BR>
     * �@@   �|�V�X�e����~���`�F�b�N <BR>
     * �@@   �|�ڋq�̃`�F�b�N�i�x�q�A�Ǘ����b�N���j <BR>
     * <BR>
     *    [����]<BR>
     *    �⏕�����F ����.�⏕����<BR>
     * <BR>
     * �Q�j���o���������e.������ʂɂ���āA�ȉ��̃��\�b�h���R�[������B<BR>
     * <BR>
     *    1001�i�o�������j�F validate�o������()<BR>
     *    1002�i���������j�F validate��������()<BR>
     * <BR>
     *    [����]<BR>
     *    �⏕�����F ����.�⏕����<BR>
     *    ���o���������e�F ����.���o���������e<BR>
     * <BR>
     * �R�j�V�K�̒���ID���擾����B<BR>
     *   this.createNewOrderId()<BR>
     * <BR>
     * �S�j�����R������(NewOrderValidationResult)�C���X�^���X�𐶐�����B<BR>
     * <BR>
     *   [�C���X�^���X�����̈���]<BR>
     *   �P�DProcessingResult.SUCCESS_RESULT<BR>
     *   �Q�D�Q�j�̒���ID<BR>
     * <BR>
     * �T�j�����R�����ʂ�Ԃ��B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���o�������jvalidate�V�K�����v �Q��<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_productType - (���i�^�C�v)
     * @@param l_cashTransOrderSpec - (���o���������e)
     * @@return NewOrderValidationResult
     * @@roseuid 40EE07120029
     */
    public NewOrderValidationResult validateNewOrder(
        SubAccount l_subAccount, 
        ProductTypeEnum l_productType, 
        NewOrderSpec l_newOrderSpec)
    {
        String STR_METHOD_NAME = "validateNewOrder(SubAccount l_subAccount," + 
            "ProductTypeEnum l_productType, NewOrderSpec l_newOrderSpec)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_newOrderSpec == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3AioNewOrderSpec l_web3AioNewOrderSpec = (WEB3AioNewOrderSpec) l_newOrderSpec;
        
        //�����R������
        NewOrderValidationResult l_newOrderValidationResult = null;
        
        try
        {      
            //�P�j�������ʃ`�F�b�N���s���B 
            //�ȉ��̃`�F�b�N���s���B 
            //   �|��t���ԃ`�F�b�N 
            //   �|�V�X�e����~���`�F�b�N 
            //   �|�ڋq�̃`�F�b�N�i�x�q�A�Ǘ����b�N���j 
            //[����] 
            //�⏕�����F ����.�⏕���� 
      
            this.validateOrder(l_subAccount);
            
            log.debug("���o���������e.�������=" + 
                    l_web3AioNewOrderSpec.getOrderTypeEnum().intValue());
            
            //1.2 ���o���������e.�������=1001�i�o���j�̏ꍇ           
            if (OrderTypeEnum.CASH_OUT.equals(l_web3AioNewOrderSpec.getOrderTypeEnum()))
            {
                log.debug("���o���������e.�������=1001�i�o���j�̏ꍇ");               
                //1.2.1 �o�������̃`�F�b�N���s���B 
                //[����] 
                //�⏕�����F ����.�⏕���� 
                //���o���������e�F ����.���o���������e 

                this.validateCashoutOrder(l_subAccount, l_web3AioNewOrderSpec);
            }
            //1.3 ���o���������e.�������=1002�i�����j�̏ꍇ
            else if (OrderTypeEnum.CASH_IN.equals(l_web3AioNewOrderSpec.getOrderTypeEnum()))
            {
                log.debug("���o���������e.�������=1002�i�����j�̏ꍇ");               
                //1.3.1 ���������̃`�F�b�N���s���B
                //[����]
                //�⏕�����F ����.�⏕���� 
                //���o���������e�F ����.���o���������e
                this.validateCashinOrder(l_subAccount, l_web3AioNewOrderSpec);
            }
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in validate�V�K����", l_ex);
            log.exiting(STR_METHOD_NAME);
            return new NewOrderValidationResult(
                ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo()));
        }
        
        long l_lngOrderId = 0L;
        //���o���������e.����ID = null �̏ꍇ�AcreateNewOrderId()�̖߂�l 
        if (l_web3AioNewOrderSpec.getOrderId() == null)
        {
            log.debug("���o���������e.����ID = null �̏ꍇ.");
            
            //�R�j�V�K�̒���ID���擾����B
            l_lngOrderId = this.createNewOrderId();
            log.debug("�V�K�̒���ID: " + l_lngOrderId);
        }
        //���o���������e.����ID != null �̏ꍇ�A���o���������e.����ID 
        else
        {
            log.debug("���o���������e.����ID != null �̏ꍇ.");
            
            l_lngOrderId = l_web3AioNewOrderSpec.getOrderId().longValue();
            
            log.debug("���o���������e.����ID: " + l_lngOrderId);
        }
        
        //�S�j�����R������(NewOrderValidationResult)�C���X�^���X�𐶐�����B
        l_newOrderValidationResult = 
            new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT, l_lngOrderId);
                
        log.debug("�����R������ = " + 
                l_newOrderValidationResult.getProcessingResult().isSuccessfulResult());
        //�T�j�����R�����ʂ�Ԃ��B
        log.exiting(STR_METHOD_NAME);
        return l_newOrderValidationResult;
    }

    /**
     * �ivalidateCancelOrder�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * ��������̔����R�����s���B<BR>
     * <BR>
     * �P�j�������ʃ`�F�b�N�����{����B <BR>
     *    this.validate����()���\�b�h���R�[������B<BR>
     * <BR>
     *    �ȉ��̃`�F�b�N���s���B <BR>
     * �@@   �|��t���ԃ`�F�b�N <BR>
     * �@@   �|�V�X�e����~���`�F�b�N <BR>
     * �@@   �|�ڋq�̃`�F�b�N�i�x�q�A�Ǘ����b�N���j <BR>
     * <BR>
     *    [����]<BR>
     *    �⏕�����F ����.�⏕����<BR>
     * <BR>
     * �Q�j����������e�Ó����`�F�b�N<BR>
     *    �Y��������SONAR���M���ςł��邩���`�F�b�N����B<BR>
     * <BR>
     *    �Q�|�P�j����������e�������Ώۂ̒����I�u�W�F�N�g���擾����B<BR>
     *       ����������e.getOrderID()<BR>
     * <BR>
     *    �Q�|�Q�j�����I�u�W�F�N�g����A�����P�ʃI�u�W�F�N�g���擾����B<BR>
     *       ����.getOrderUnits()��1�Ԗڂ̗v�f<BR>
     * <BR>
     *    �Q�|�R�j������Ԃ̃`�F�b�N <BR>
     * <BR>
     *    ������� != 1�i��t�ρj or ��������敪 != 0�i�����l�j<BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01938<BR>
     * <BR>
     * <BR>
     * �Q�|�S�jSONAR���M�󋵂̃`�F�b�N <BR>
     *    �����P��.MQ�X�e�[�^�X != "0"�i�������j �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00716<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_cancelOrderSpec - (����������e)<BR>
     * ����������e�I�u�W�F�N�g
     * @@return OrderValidationResult
     * @@roseuid 40EE079B0336
     */
    public OrderValidationResult validateCancelOrder(
        SubAccount l_subAccount, 
        CancelOrderSpec l_cancelOrderSpec)                  
    {
        String STR_METHOD_NAME = "validateCancelOrder(" + 
            "SubAccount l_subAccount, CancelOrderSpec l_cancelOrderSpec)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_cancelOrderSpec == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //�����R������
        OrderValidationResult l_orderValidationResult = null;
        
        //�P�j�������ʃ`�F�b�N�����{����B 
        try
        {      
            log.debug("�������ʃ`�F�b�N�����{����B");
            this.validateOrder(l_subAccount);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in validate����()", l_ex);
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo()));
        }

        //�Q�j����������e�Ó����`�F�b�N
        //�Q�|�P�j����������e�������Ώۂ̒����I�u�W�F�N�g���擾����B
        //�Q�|�Q�j�����I�u�W�F�N�g����A�����P�ʃI�u�W�F�N�g���擾����B
        //����.getOrderUnits()��1�Ԗڂ̗v�f
        AioOrderUnit l_aioOrderUnit = (AioOrderUnit) this.getOrderUnits(l_cancelOrderSpec.getOrderId())[0];
        AioOrderUnitRow l_orderUnitRow = (AioOrderUnitRow) l_aioOrderUnit.getDataSourceObject();
        
        //�Q�|�R�j������Ԃ̃`�F�b�N
        // ������� != 1�i��t�ρj or ��������敪 != 0�i�����l�j�̏ꍇ�A��O���X���[����B

        if (!OrderStatusEnum.ACCEPTED.equals(l_orderUnitRow.getOrderStatus())
            || !WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(l_orderUnitRow.getCancelType()))
        {
            log.debug("Error in ������� != 1�i��t�ρj or ��������敪 != 0�i�����l�j�̏ꍇ");
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01938));      
        }            
        
        //�Q�|�S�jSONAR���M�󋵂̃`�F�b�N <BR>
        //  �����P��.MQ�X�e�[�^�X != "0"�i�������j �̏ꍇ�A��O���X���[����B
        log.debug("�����P��.MQ�X�e�[�^�X = " + l_orderUnitRow.getMqStatus());
        if (!WEB3MqStatusDef.NOT_SEND_MAIL.equals(l_orderUnitRow.getMqStatus()))
        {
            log.debug("Error in �����P��.MQ�X�e�[�^�X != '0'�i�������j");
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00716));      
        }            
        //NewOrderValidationResult�I�u�W�F�N�g�𐶐����ĕԂ�
        l_orderValidationResult =
            new OrderValidationResult(ProcessingResult.SUCCESS_RESULT);

        log.debug("�����R��result = " + 
            l_orderValidationResult.getProcessingResult().isSuccessfulResult());
        
        log.exiting(STR_METHOD_NAME);
        return l_orderValidationResult;

    }
    
    /**
     * (validate�������z)<BR>
     * �������z�̃`�F�b�N���s���B<BR>
     * <BR>
     * - 1�񓖂���̏�����z�`�F�b�N<BR>
     * - 1�񓖂���̉������z�`�F�b�N<BR>
     * - �������z�P�ʃ`�F�b�N<BR>
     * - �������z�`�F�b�N<BR>
     * - �����񐔃`�F�b�N<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���o�������jvalidate�������z�v �Q��<BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_strPaySchemeId - (���ϋ@@��ID)
     * @@param l_dblAmount - (�����z)
     * @@throws WEB3BaseException
     * @@roseuid 40F2751D0076
     */
    public void validateCreditAmount(
        SubAccount l_subAccount, 
        String l_strPaySchemeId, 
        double l_dblAmount) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateCreditAmount(SubAccount l_subAccount, " +
            "String l_strPaySchemeId, double l_dblAmount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.1. ���o�������R���ʃ`�F�b�N�̃I�u�W�F�N�g���擾����B
        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations = 
            (WEB3AioProductTypeOrderManagerReusableValidations)
            AioProductTypeOrderManagerReusableValidations.getInstance();
            
        log.debug("�،���ЃR�[�h: " + l_subAccount.getInstitution().getInstitutionCode());
        log.debug("���X�R�[�h: " + l_subAccount.getMainAccount().getBranch().getBranchCode());
        log.debug("���ϋ@@��ID: " + l_strPaySchemeId);
        
        //1.2. ��Еʌ��ϋ@@�֏����C���X�^���X�𐶐�����B 
        WEB3AioCompanySettleInstitutionConditions l_aioSettleCondition = 
            new WEB3AioCompanySettleInstitutionConditions(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_strPaySchemeId);
                
        //---1�񓖂���̏�����z�`�F�b�N 
        
        //1.3. get����������z(): 1�񓖂���̏���������z���擾����B        
        double l_dblMaxAmountOnce = l_aioSettleCondition.getMaxAmountOnce();
        
        log.debug("get����������z():" + l_dblMaxAmountOnce);
        
        //1.4. validate������z(): 1�񓖂���̏�����z�̃`�F�b�N���s���B
        //[����] 
        //�����z�F ����.�����z 
        //������z�F get����������z�i1�񓖂���j()�̖߂�l
        l_reusableValidations.validateMaxAmount(l_dblAmount, l_dblMaxAmountOnce);
        
        //--- 1�񓖂���̉������z�`�F�b�N 
        
        //1.5. get�����������z(): 1�񓖂���̉����������z���擾����B 
        double l_dblMinAmountOnce = l_aioSettleCondition.getMinAmountOnce();
        
        log.debug("get�����������z():" + l_dblMinAmountOnce);
        
        //1.6. validate�������z(): 1�񓖂���̉������z�̃`�F�b�N���s���B
        //[����] 
        //�����z�F ����.�����z 
        //�������z�F get�����������z�i1�񓖂���j()�̖߂�l
        l_reusableValidations.validateMinAmount(l_dblAmount, l_dblMinAmountOnce);
        
        //--- �������z�P�ʃ`�F�b�N

        //1.7. get�P�ʓ������z(): �P�ʓ������z���擾����B
        double l_dblAmountUnit = l_aioSettleCondition.getAmountUnit();
        
        log.debug("get�P�ʓ������z():" + l_dblAmountUnit);
        
        //1.8. validate�ŏ��P��(): �����z���ŏ��P�ʂŊ���؂�邩���`�F�b�N����B
        //[����] 
        //�����z�F ����.�����z 
        //�ŏ��P�ʁF get�P�ʓ������z()�̖߂�l
        l_reusableValidations.validateSmallestUnit(l_dblAmount, l_dblAmountUnit);
        
        //--- �������z�`�F�b�N 

        //1.9. get�I�����C������������(): �Y�����錈�ϋ@@�ւł̔��������擾����B
        Date l_datBizDate = this.getOnlineCashinBizDate(l_strPaySchemeId);
        
        log.debug("get�I�����C������������():" + l_datBizDate);
        
        //1.10. get����������z(): 1��������̏���������z���擾����B 
        double l_dblMaxAmountDaily = l_aioSettleCondition.getMaxAmountDaily();
        
        log.debug("get����������z():" + l_dblMaxAmountDaily);
        
        //1.11. get�������z(): �����_�̑������z���擾����B
        //[����] 
        //�⏕�����F ����.�⏕���� 
        //���ϋ@@��ID�F ����.���ϋ@@��ID 
        //�������F get�I�����C������������()�̖߂�l 

        double l_dblTotalCreditAmout = this.getTotalCreditAmount(
            l_subAccount, 
            l_strPaySchemeId, 
            l_datBizDate);
        
        //1.12. validate����������z(): 1���̓������z������𒴂��ĂȂ����`�F�b�N����B
        //[����] 
        //�����_�������z�F get�������z()�̖߂�l 
        //�����z�F ����.�����z 
        //������z�F get����������z�i1��������j()�̖߂�l 
        
        log.debug("�����_�������z�F" + l_dblTotalCreditAmout);
        log.debug("����.�����z�F" + l_dblAmount);
        log.debug("������z�F" + l_dblMaxAmountDaily);
        
        l_reusableValidations.validateTotalCreditMaxAmount(
            l_dblTotalCreditAmout, l_dblAmount, l_dblMaxAmountDaily);
        
        //--- �����񐔃`�F�b�N 
        
        //1.13.  get�����(): 1��������̓�������񐔂��擾����B
        long l_lngMaxCount = l_aioSettleCondition.getMaxCount();
        
        log.debug("get�����():" + l_lngMaxCount);
        
        //1.14. get����������(): �����_�̑������񐔂��擾����B
        long l_lngOrderCount = this.getCashinOrderCount(l_subAccount, l_strPaySchemeId, l_datBizDate);
        
        log.debug("get����������():" + l_lngOrderCount);
        
        //1.15. validate�����(): 1���̓����񐔂�����𒴂��ĂȂ����`�F�b�N����B
        //[����] 
        //�����_�����񐔁F get����������()�̖߂�l 
        //����񐔁F get�����()�̖߂�l
        l_reusableValidations.validateMaxCount(l_lngOrderCount, l_lngMaxCount);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get����������)<BR>
     * 1���̓����񐔂��擾����B<BR>
     * <BR>
     * �P�j���Z�@@�֘A�g���o���󋵃e�[�u������ȉ���<BR>
     * �����ƈ�v���郌�R�[�h���擾����B<BR>
     * <BR>
     *    [��������]<BR>
     *    �،���ЃR�[�h = <BR>
     * �⏕����.getInstitution().getInstitutionCode()�̖߂�l<BR>
     *    ���X�R�[�h = �⏕����.get����X().getBranchCode()�̖߂�l<BR>
     *    �ڋq�R�[�h = <BR>
     * �⏕����.getMainAccount().getAccountCode()�̖߂�l<BR>
     *    ���ϋ@@��ID = ����.���ϋ@@��ID<BR>
     *    �W�v��� = ����.������<BR>
     *   �i�����敪 = '1' or ����FLAG�i���ό��ʁj in ('1','2','5')�j<BR> 
     * <BR>
     * �Q�j�P�j�Ŏ擾�������R�[�h����Ԃ��B<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_strPaySchemeId - (���ϋ@@��ID)
     * @@param l_datBizDate - (������)
     * @@throws WEB3BaseException
     * @@roseuid 40F3693C000F
     */
    public long getCashinOrderCount(
        SubAccount l_subAccount, 
        String l_strPaySchemeId, 
        Date l_datBizDate) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getCashinOrderCount(" + 
            "SubAccount l_subAccount, String l_strPaySchemeId, Date l_datBizDate)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j���Z�@@�֘A�g���o���󋵃e�[�u������ȉ��̏����ƈ�v���郌�R�[�h���擾����B
        List l_lisRows = null;
        try
        {           
            //�،���ЃR�[�h = �⏕����.getInstitution().getInstitutionCode()�̖߂�l 
            String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
            
            //���X�R�[�h = �⏕����.get����X().getBranchCode()�̖߂�l
            String l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
            
            //�ڋq�R�[�h = �⏕����.getMainAccount().getAccountCode()�̖߂�l
            String l_strAccountCode = l_subAccount.getMainAccount().getAccountCode();
            
            log.debug("�،���ЃR�[�h = " + l_strInstitutionCode);
            log.debug("���X�R�[�h = " + l_strBranchCode);
            log.debug("�ڋq�R�[�h = " + l_strAccountCode);
            
            String l_strWhereClause = 
                "institution_code = ? and branch_code = ?" +
                " and account_code = ? and pay_scheme_id = ?" +
                " and to_char(base_date, 'YYMMDD') = to_char(?, 'YYMMDD')" +
                " and transaction_status = ? and result_status_flag in ( ? , ? , ? )";                 
            
            Object l_bindVars[] = {
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strPaySchemeId, 
                    l_datBizDate, 
                    WEB3TransactionStatusDef.OK, 
                    WEB3ResultStatusFlagDef.NOTIFY_RECEIPT, 
                    WEB3ResultStatusFlagDef.RESPONSE_SEND, 
                    WEB3ResultStatusFlagDef.RESPONSE_ERROR_TELEGRAM
                    };
            
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    BankCashTransferStatusRow.TYPE,
                    l_strWhereClause,
                    null,
                    l_bindVars);
            
            long l_lngReturnSize = l_lisRows.size();
            log.debug("���R�[�h�� = " + l_lngReturnSize);
            
            //�Q�j�P�j�Ŏ擾�������R�[�h����Ԃ��B
            log.exiting(STR_METHOD_NAME);
            return l_lngReturnSize;
            
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (get�������z)<BR>
     * 1��������̓������z���擾����B<BR>
     * <BR>
     * �P�j���Z�@@�֘A�g���o���󋵃e�[�u������ȉ��̏����ƈ�v���郌�R�[�h���擾����B<BR>
     * <BR>
     *    [��������]<BR>
     *    �،���ЃR�[�h = �⏕����.getInstitution().getInstitutionCode()�̖߂�l<BR>
     *    ���X�R�[�h = �⏕����.get����X().getBranchCode()�̖߂�l<BR>
     *    �ڋq�R�[�h = �⏕����.getMainAccount().getAccountCode()�̖߂�l<BR>
     *    ���ϋ@@��ID = ����.���ϋ@@��ID<BR>
     *    �W�v��� = ����.������<BR>
     *    �����敪 = '1'�iOK�j <BR>
     * <BR>
     * �Q�j�擾�������R�[�h����A���z���ڂ̍��v���Z�o����B<BR>
     * <BR>
     * �R�j�Q�j�̎Z�o���ʂ�Ԃ��B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_strPaySchemeId - (���ϋ@@��ID)
     * @@param l_datBizDate - (������)
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 40F3693C001F
     */
    public double getTotalCreditAmount(
        SubAccount l_subAccount, 
        String l_strPaySchemeId, 
        Date l_datBizDate)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getTotalCreditAmount(" + 
            "SubAccount l_subAccount, String l_strPaySchemeId, Date l_datBizDate)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //�P�j���Z�@@�֘A�g���o���󋵃e�[�u������ȉ��̏����ƈ�v���郌�R�[�h���擾����B
        List l_lisRows = null;
       
        try
        {            
            //�،���ЃR�[�h = �⏕����.getInstitution().getInstitutionCode()�̖߂�l 
            String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
            
            //���X�R�[�h = �⏕����.get����X().getBranchCode()�̖߂�l
            String l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
            
            //�ڋq�R�[�h = �⏕����.getMainAccount().getAccountCode()�̖߂�l
            String l_strAccountCode = l_subAccount.getMainAccount().getAccountCode();
            
            log.debug("�،���ЃR�[�h = " + l_strInstitutionCode);
            log.debug("���X�R�[�h = " + l_strBranchCode);
            log.debug("�ڋq�R�[�h = " + l_strAccountCode);
            
            String l_strWhereClause = 
                "institution_code = ? and branch_code = ?" +
                " and account_code = ? and pay_scheme_id = ?" +
                " and to_char(base_date, 'YYMMDD') = to_char(?, 'YYMMDD')" +
                " and transaction_status = ? ";                
            
            Object l_bindVars[] = {
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strPaySchemeId, 
                    l_datBizDate, 
                    WEB3TransactionStatusDef.OK };          
            
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    BankCashTransferStatusRow.TYPE,
                    l_strWhereClause,
                    null,
                    l_bindVars);
            
            //�Q�j�擾�������R�[�h����A���z���ڂ̍��v���Z�o����B
            double l_dblAmountCount = 0;
            for (int i = 0; i < l_lisRows.size(); i++)
            {
                BankCashTransferStatusRow l_bankCashTransferStatusRow = 
                    (BankCashTransferStatusRow) l_lisRows.get(i);
                log.debug("���Z�@@�֘A�g���o���󋵃e�[�u��Row : " + l_bankCashTransferStatusRow);
                
                l_dblAmountCount += l_bankCashTransferStatusRow.getAmount();
            }
            log.debug("���z���ڂ̍��v = " + l_dblAmountCount);
            
            //�R�j�Q�j�̎Z�o���ʂ�Ԃ��B
            log.exiting(STR_METHOD_NAME);
            return l_dblAmountCount;
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }        
    }
    
    /**
     * (validate�o������)<BR>
     * �o�������̔����R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���o�������jvalidate�o�������v �Q��<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_cashTransOrderSpec - (���o���������e)
     * @@throws WEB3BaseException
     * @@roseuid 40F4FD3801E9
     */
    protected void validateCashoutOrder(
        SubAccount l_subAccount, 
        WEB3AioNewOrderSpec l_cashTransOrderSpec)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateCashoutOrder(" + 
            "SubAccount l_subAccount, WEB3AioNewOrderSpec l_cashTransOrderSpec)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_cashTransOrderSpec == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //1.1. �U���\������擾����B 
        Date l_datEstimatedDate = l_cashTransOrderSpec.getEstimatedTransferDate();        
        
        log.debug("�U���\��� = " + l_datEstimatedDate);
        
        //���o�������R���ʃ`�F�b�N�̃I�u�W�F�N�g���擾����B
        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations = 
            (WEB3AioProductTypeOrderManagerReusableValidations)
            AioProductTypeOrderManagerReusableValidations.getInstance();
            
        //1.2. validate�o���d������(): �����n���̏o�������̃`�F�b�N���s���B
        //[����] 
        //�⏕�����F ����.�⏕���� 
        //��n���F ���o���������e.getEstimatedTransferDate()�̖߂�l        
        l_reusableValidations.validateCashoutDuplicateOrder(
            l_subAccount, l_datEstimatedDate);
            
        log.debug("call validate�o���d������()...");
        //1.3.  getQuantity(): ���z���擾����B
        double l_dblQuantity = l_cashTransOrderSpec.getQuantity();
        
        log.debug("���z = " + l_dblQuantity);
        
        //1.4. validate�o�����z(): �o�����z�͈̔͂̃`�F�b�N���s���B
        //[����] 
        //�⏕�����F ����.�⏕���� 
        //���z�F ���o���������e.getQuantity()�̖߂�l 
        //��n���F�@@���o���������e.getEstimatedTransferDate()�̖߂�l
        l_reusableValidations.validatePaymentAmount(
            l_subAccount, 
            l_dblQuantity, 
            l_cashTransOrderSpec.getEstimatedTransferDate());
               
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate��������)<BR>
     * ���������̔����R�����s���B<BR>
     * <BR>
     * �P�j���z�`�F�b�N<BR>
     *   this.validate�������z()���\�b�h���R�[������B<BR>
     * <BR>
     *   [����]<BR>
     *   �⏕�����F ����.�⏕����<BR>
     *   ���ϋ@@��ID�F ���o���������e.get���ϋ@@��ID()�̖߂�l<BR>
     *   �������z�F ���o���������e.getQuantity()�̖߂�l<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���o�������jvalidate���������v �Q��<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_cashTransOrderSpec - (���o���������e)
     * @@throws WEB3BaseException
     * @@roseuid 40F4FD4F00C0
     */
    protected void validateCashinOrder(
        SubAccount l_subAccount, 
        WEB3AioNewOrderSpec l_cashTransOrderSpec)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateCashinOrder(" + 
            "SubAccount l_subAccount, WEB3AioNewOrderSpec l_cashTransOrderSpec)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_cashTransOrderSpec == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //���ϋ@@��ID�F ���o���������e.get���ϋ@@��ID()�̖߂�l
        String l_strPaySchemeId = "";
        l_strPaySchemeId = l_cashTransOrderSpec.getPaySchemeId();
        
        log.debug("���o���������e.get���ϋ@@��ID() = " + l_strPaySchemeId);
        
        //�������z�F ���o���������e.getQuantity()�̖߂�l
        double l_dblQuantity = 0;
        l_dblQuantity = l_cashTransOrderSpec.getQuantity();
        
        log.debug("���o���������e.getQuantity() = " + l_dblQuantity);
        
        //�P�j���z�`�F�b�N
        this.validateCreditAmount(l_subAccount, l_strPaySchemeId, l_dblQuantity);
   
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get���iID)<BR>
     * ���o���p�̏��iID���擾����B<BR>
     * <BR>
     * �P�jProduct�e�[�u�����烌�R�[�h�i�s�I�u�W�F�N�g�j���擾����B<BR>
     *   ProductDao.findRowsByInstitutionCodeProductType(�،���ЃR�[�h, ���i�^�C�v)<BR>
     * <BR>
     *   [����]<BR>
     *   �،���ЃR�[�h�F ����.�،����.getInstitutionCode()�̖߂�l<BR>
     *   ���i�^�C�v�F 5�i�����j<BR>
     * <BR>
     * �Q�j�s�I�u�W�F�N�g.getProductId()�̖߂�l��ԋp����B<BR>
     * <BR>
     * @@param l_institution - (�،����)<BR>
     * �،���ЃI�u�W�F�N�g
     * @@return long
     * @@throws WEB3BaseException
     * @@roseuid 40F52F9C0142
     */
    public long getProductId(Institution l_institution) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getProductId(Institution l_institution)";
        log.entering(STR_METHOD_NAME);
        
        if (l_institution == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�،���ЃR�[�h�F ����.�،����.getInstitutionCode()�̖߂�l
        String l_strInstitutionCode = l_institution.getInstitutionCode();
         
        List l_lisProductRow = null;
        try
        {
            //[����] 
            //�،���ЃR�[�h�F ����.�،����.getInstitutionCode()�̖߂�l 
            //���i�^�C�v�F 5�i�����j 
            log.debug("ProductDao.findRowsBy...");
            l_lisProductRow = ProductDao.findRowsByInstitutionCodeProductType(
                l_strInstitutionCode, ProductTypeEnum.CASH);               
            
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //�P�jProduct�e�[�u�����烌�R�[�h�i�s�I�u�W�F�N�g�j���擾����B
        if (l_lisProductRow != null && l_lisProductRow.size() != 0)
        {
            ProductRow l_productRow = (ProductRow) l_lisProductRow.get(0);        
            log.debug("ProductRow : " + l_productRow);
            
            //�Q�j�s�I�u�W�F�N�g.getProductId()�̖߂�l��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return l_productRow.getProductId();
        }
        
        throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80006,
            getClass().getName() + STR_METHOD_NAME); 
    }
    
    /**
     * (get�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �P�j�ȉ��̃I�u�W�F�N�g���擾����B<BR>
     * 
     * �،���ЃI�u�W�F�N�g = �g���A�J�E���g�}�l�[�W��.getInstitutiion(����.�،���ЃR�[�h)<BR>
     * ���X�I�u�W�F�N�g = �g���A�J�E���g�}�l�[�W��.get���X(����.�،���ЃR�[�h, ����.���X�R�[�h)<BR>
     * �����i�ڋq�j�I�u�W�F�N�g = �g���A�J�E���g�}�l�[�W��.getMainAccount(�،���ЃI�u�W�F�N�g, ���X�I�u�W�F�N�g, 
     * ����.�ڋq�R�[�h)<BR>
     * �⏕�����I�u�W�F�N�g = �g���A�J�E���g�}�l�[�W��.getSubAccount(����.����ID, ����.�⏕�����^�C�v)<BR>
     * <BR>
     * �Q�j�����P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * [��������]<BR>
     * ����ID = ����.����ID<BR>
     * �⏕����ID = �⏕����.�⏕����ID<BR>
     * ���XID = ���X.���XID<BR>
     * �����^�C�v = ProductTypeEnum.CASH<BR>
     * ���ʃR�[�h = ����.���ʃR�[�h<BR>
     * <BR>
     * �Y���s�����݂����ꍇ�A�����s��v�����ꍇ�͗�O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00739<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)
     * @@param l_strBranchCode - (���X�R�[�h)
     * @@param l_strAccountCode - (�ڋq�R�[�h)
     * @@param l_strOrderRequestNumber - (���ʃR�[�h)
     * @@param l_subAccountType - (�⏕�����^�C�v)
     * @@return AioOrderUnit
     * @@throws NotFoundException, WEB3BaseException
     * @@roseuid 40FCB8370387
     */
    public AioOrderUnit getOrderUnit(
        String l_strInstitutionCode, 
        String l_strBranchCode, 
        String l_strAccountCode, 
        String l_strOrderRequestNumber, 
        SubAccountTypeEnum l_subAccountType)        
        throws WEB3BaseException, NotFoundException
    {
        String STR_METHOD_NAME = "getOrderUnit(" + 
            "String l_strBranchCode, String l_strAccountCode, " + 
            "String l_strOrderRequestNumber, SubAccountTypeEnum l_subAccountType)";
            
        log.entering(STR_METHOD_NAME);        
        
        //�P�j�ȉ��̃I�u�W�F�N�g���擾����B
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        
        //�g���A�J�E���g�}�l�[�W���擾����    
        WEB3GentradeAccountManager l_web3GentradeAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        MainAccount l_mainAccount = null;
        SubAccount l_subAccount = null;
        Branch l_branch = null;
        
        try
        {        
            //Throw NotFoundException
            //�،���ЃI�u�W�F�N�g = �g���A�J�E���g�}�l�[�W��.getInstitutiion(����.�،���ЃR�[�h) 
            Institution l_institution = l_web3GentradeAccountManager.getInstitution(
                l_strInstitutionCode);
            log.debug("�،����.get�،����Code() = " + l_institution.getInstitutionCode());
            
            //���X�I�u�W�F�N�g = �g���A�J�E���g�}�l�[�W��.get���X(�،���ЃI�u�W�F�N�g, ����.���X�R�[�h) 
            l_branch = l_web3GentradeAccountManager.getBranch(
                l_institution, l_strBranchCode);
            log.debug("���X.get���XCode() = " + l_branch.getBranchCode());
            
            //�����i�ڋq�j�I�u�W�F�N�g = �g���A�J�E���g�}�l�[�W��.getMainAccount
            //(�،���ЃI�u�W�F�N�g, ���X�I�u�W�F�N�g, ����.�ڋq�R�[�h) 
            l_mainAccount = l_web3GentradeAccountManager.getMainAccount(
                l_institution, l_branch, l_strAccountCode);
                
            log.debug("�����i�ڋq�j�I�u�W�F�N�g.get����Code = " + l_mainAccount.getAccountId());
            
            //�⏕�����I�u�W�F�N�g = �g���A�J�E���g�}�l�[�W��.getSubAccount(����.����ID, ����.�⏕�����^�C�v) 
            l_subAccount = l_web3GentradeAccountManager.getSubAccount(
                l_mainAccount.getAccountId(), l_subAccountType);
            
            log.debug("�⏕����.get�⏕����Id() = " + l_subAccount.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        try
        {
            //�Q�j�����P�ʃI�u�W�F�N�g���擾����B
            List l_lisRows = null;
            String l_strWhereClause = "account_id = ? and sub_account_id = ? " +
                "and branch_id = ? and product_type = ? and order_request_number = ?";
            
            //����ID = ����.����ID
            long l_lngAccountId = l_mainAccount.getAccountId();
            
            //�⏕����ID = �⏕����.�⏕����ID
            long l_lngSubAccountId = l_subAccount.getSubAccountId();
            
            //���XID = ���X.���XID
            long l_lngBranchId = l_branch.getBranchId();           
            
            log.debug("����ID  = " + l_lngAccountId);
            log.debug("�⏕����ID  = " + l_lngSubAccountId);
            log.debug("���XID  = " + l_lngBranchId);
            log.debug("�����^�C�v  = " + ProductTypeEnum.CASH.intValue());
            log.debug("���ʃR�[�h  = " + l_strOrderRequestNumber);
            
            Object l_bindVars[] = {
                new Long(l_lngAccountId),
                new Long(l_lngSubAccountId),
                new Long(l_lngBranchId),
                ProductTypeEnum.CASH, 
                l_strOrderRequestNumber };
            
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    AioOrderUnitRow.TYPE,
                    l_strWhereClause,
                    null,
                    l_bindVars);
            
            AioOrderUnit l_aioOrderUnit = null;
            //�Y���s�����݂����ꍇ�A�����s��v�����ꍇ�͗�O���X���[����B
            if (l_lisRows.size() > 1)
            {                
                log.debug("�Y�����钍����񂪕���������܂����B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00739,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�Y�����钍����񂪕���������܂����Bsize = " + l_lisRows.size());
            }
            else if (l_lisRows.size() == 1)
            {
                //�����P�ʃI�u�W�F�N�g���擾����B
                AioOrderUnitRow l_aioOrderUnitRow = (AioOrderUnitRow)l_lisRows.get(0);            
                log.debug("�����P��Row : " + l_aioOrderUnitRow);
                //this.getOrderUnit()���R�[�����A�����P�ʃI�u�W�F�N�g���擾����
                l_aioOrderUnit = (AioOrderUnit) this.toOrderUnit(
                        l_aioOrderUnitRow);
    
                log.exiting(STR_METHOD_NAME);
            }
            else 
            {
                log.debug("�f�[�^�s�����G���[");
                //��O���X���[����
                throw new NotFoundException("�����P�ʃI�u�W�F�N�g���擾�ł��Ȃ�");
            }
            return l_aioOrderUnit;
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (create�U������Z�@@�֖���)<BR>
     * �U������Z�@@�֖��ׂ̔z��𐶐�����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���o�������jcreate�U������Z�@@�֖��ׁv  �Q��<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)
     * @@param l_strBranchCode - (���X�R�[�h)
     * @@return WEB3AioFinancialInstitutionUnit
     * @@throws WEB3BaseException
     * @@roseuid 41084E1D02C5
     */
    public WEB3AioFinancialInstitutionUnit[] createFinancialInstitutionDetails(
        String l_strInstitutionCode, String l_strBranchCode)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "createFinancialInstitutionDetails(" + 
            "String l_strInstitutionCode, String l_strBranchCode)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 1.2 �Y�����镔�X�ŗ��p�\�ȋ��Z�@@�ւ����Z�@@�֗��p�e�[�u������擾����B 
        String l_strWhereClause = "";
        String l_strOrderBy = "";
        
        List l_lisRows = null;
        Object l_bindVars[] = null;
        try
        {        
            //Where�����F����.���X�R�[�h=null�̏ꍇ�F �hinstitution_code=?�h 
            //orderBy�����F����.���X�R�[�h=null�̏ꍇ�F �hfin_institution_code�h 
            if (l_strBranchCode == null)
            {
                log.debug("����.���X�R�[�h = null�̏ꍇ");
                l_strWhereClause = "institution_code = ?";
                l_strOrderBy = "fin_institution_code";
                l_bindVars = new Object[1];
                l_bindVars[0] = l_strInstitutionCode;

            }
            //Where�����F����.���X�R�[�h!=null�̏ꍇ�F �hinstitution_code=? and branch_code=?" 
            //orderBy�����F����.���X�R�[�h!=null�̏ꍇ�F �hdisplay_order"  
            else
            {
                log.debug("����.���X�R�[�h != null�̏ꍇ");
                l_strWhereClause = "institution_code = ? and branch_code = ?";
                l_strOrderBy = "display_order";

                l_bindVars = new Object[2];              
                l_bindVars[0] = l_strInstitutionCode;
                l_bindVars[1] = l_strBranchCode;

            }
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    FinInstitutionAvailableRow.TYPE, 
                    l_strWhereClause,
                    l_strOrderBy,
                    null,                    
                    l_bindVars);
            
            log.debug("search ���Z�@@�֗��p�e�[�u��.size: = " + l_lisRows.size());
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.3 ArrayList�̃C���X�^���X�𐶐�����B
        List l_lisFinancialInstitutionUnit = new ArrayList();
        
        //1.4 �擾�����v�f����Loop����         
        for (int i = 0; i < l_lisRows.size(); i++)
        {
            FinInstitutionAvailableRow l_finInstAvailableRow = 
                (FinInstitutionAvailableRow) l_lisRows.get(i);            
            
            log.debug("���Z�@@�֗��p�e�[�u��Row : " + l_finInstAvailableRow);
            
            //1.4.1 �U������Z�@@�֖���
            WEB3AioFinancialInstitutionUnit l_financialInstirutionUnit =
                new WEB3AioFinancialInstitutionUnit();
                
            //1.4.2 ���Z�@@�փC���X�^���X�𐶐�����B
            WEB3GentradeFinInstitution l_finInstitution = 
                new WEB3GentradeFinInstitution(
                    l_strInstitutionCode, 
                    l_finInstAvailableRow.getFinInstitutionCode());
                
            //1.4.3 �v���p�e�B�Z�b�g
            //(*1)�ȉ��̂Ƃ���Ƀv���p�e�B���Z�b�g����B

            //�U������Z�@@�֖���.�R�[�h �� ���Z�@@�֗��p�s�I�u�W�F�N�g.get���Z�@@�փR�[�h()�̖߂�l
            l_financialInstirutionUnit.finInstitutionCode = 
                l_finInstAvailableRow.getFinInstitutionCode();

            //�U������Z�@@�֖���.���� �� ���Z�@@��.get���Z�@@�֖��i�����j()�̖߂�l
            l_financialInstirutionUnit.finInstitutionName = 
                l_finInstitution.getFinInstitutionNameKanji();
            
            log.debug("�U������Z�@@�֖���.�R�[�h �� " + l_financialInstirutionUnit.finInstitutionCode);
            log.debug("�U������Z�@@�֖���.���� �� " + l_financialInstirutionUnit.finInstitutionName);            
            
            //�d��������e�̃I�u�W�F�N�g������ꍇ�́A�ǉ����Ȃ��B
            if (!WEB3Toolkit.contain(l_lisFinancialInstitutionUnit, l_financialInstirutionUnit))
            {
                log.debug("�U������Z�@@�֖��׃I�u�W�F�N�g��ArrayList�ɒǉ�����B");
                //1.4.4 �U������Z�@@�֖��׃I�u�W�F�N�g��ArrayList�ɒǉ�����B 
                l_lisFinancialInstitutionUnit.add(l_financialInstirutionUnit);   
            }
        }
        
        WEB3AioFinancialInstitutionUnit[] l_financialInstirutionUnits = 
            new WEB3AioFinancialInstitutionUnit[l_lisFinancialInstitutionUnit.size()];
                    
        l_lisFinancialInstitutionUnit.toArray(l_financialInstirutionUnits);
                
        log.exiting(STR_METHOD_NAME);
        return l_financialInstirutionUnits;
    }

    /**
     * (create�U������Z�@@�֖���)<BR>
     * �i�I�[�o�[���[�h���\�b�h�j<BR>
     * �U������Z�@@�֖��ׂ̔z��𐶐�����B<BR>
     * <BR>
     * �P�j�،���ЃR�[�h�A���X�R�[�h���擾����B<BR>
     * <BR>
     *    �،���ЃR�[�h�F ����.�⏕����.getInstitution().getInstitutionCode()�̖߂�l<BR>
     *    ���X�R�[�h�F ����.�⏕����.get����X().getBranchCode()�̖߂�l<BR>
     * <BR>
     * �Q�jcreate�U������Z�@@�֖��ׁi�،���ЃR�[�h, ���X�R�[�h�j���\�b�h���R�[������B<BR>
     * <BR>
     *   �m�����n<BR>
     *   �،���ЃR�[�h�F �P�j�Ŏ擾�����،���ЃR�[�h<BR>
     *   ���X�R�[�h�F �P�j�Ŏ擾�������X�R�[�h<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@return WEB3AioFinancialInstitutionUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 4108628E0267
     */
    public WEB3AioFinancialInstitutionUnit[] createFinancialInstitutionDetails(
        SubAccount l_subAccount) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "createFinancialInstitutionDetails(SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j�،���ЃR�[�h�A���X�R�[�h���擾����B       
        //�،���ЃR�[�h�F ����.�⏕����.getInstitution().getInstitutionCode()�̖߂�l
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        //���X�R�[�h�F ����.�⏕����.get����X().getBranchCode()�̖߂�l
        String l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
        
        //�Q�jcreate�U������Z�@@�֖��ׁi�،���ЃR�[�h, ���X�R�[�h�j���\�b�h���R�[������B
        WEB3AioFinancialInstitutionUnit[] l_web3AioFinancialInstitutionUnits = 
            this.createFinancialInstitutionDetails(l_strInstitutionCode, l_strBranchCode);

        log.exiting(STR_METHOD_NAME);
        return l_web3AioFinancialInstitutionUnits;
    }
    
    /**
     * (validate�������M�\)<BR>
     * �o�����������M�\���ǂ����̃`�F�b�N���s���B<BR>
     * <BR>
     * �P�j������Ԃ̃`�F�b�N<BR>
     * <BR>
     *    ������� != 1�i��t�ρj and ��������敪 != 0�i�����l�j<BR>
     * <BR>
     *    �̏ꍇ�A'1'�iNG�j��Ԃ��B<BR>
     * <BR>
     * �Q�j���M��Ԃ̃`�F�b�N<BR>
     * <BR>
     *    �����P��.MQ�X�e�[�^�X != '0'�i�������j<BR>
     * <BR>
     *    �̏ꍇ�A'1'�iNG�j��Ԃ��B<BR>
     * <BR>
     * �R�j�������̃`�F�b�N<BR>
     * <BR>
     *    �����P��.������ > �������t<BR>
     * <BR>
     *    �̏ꍇ�A'1'�iNG�j��Ԃ��B<BR>
     * <BR>
     * �S�j'0'�iOK�j��Ԃ��B<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g
     * @@return String
     * @@roseuid 4129D45B006D
     */
    public String validateOrderSendPossible(AioOrderUnit l_orderUnit) 
    {
        String STR_METHOD_NAME = "validateOrderSendPossible(AioOrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
        
        AioOrderUnitRow l_aioOrderUnitRow = (AioOrderUnitRow) l_orderUnit.getDataSourceObject();
        //�P�j������Ԃ̃`�F�b�N
        OrderStatusEnum l_orderStatus = l_aioOrderUnitRow.getOrderStatus();        
        log.debug("������� = " + l_orderStatus.intValue());
        
        String l_strCancelType = l_aioOrderUnitRow.getCancelType();
        log.debug("����敪 = " + l_strCancelType);
        
        //������� != 1�i��t�ρj and ��������敪 != 0�i�����l�j�̏ꍇ�A'1'�iNG�j��Ԃ��B
        if (!OrderStatusEnum.ACCEPTED.equals(l_orderStatus) && 
            !WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(l_strCancelType))
        {
            log.debug("������� != 1�i��t�ρjand ��������敪 != 0�i�����l�j�̏ꍇ�A'1'�iNG�j��Ԃ��B");
            log.exiting(STR_METHOD_NAME);
            return WEB3OrderSendPossibleDef.SEND_NG;        
        }
        
        //�Q�j���M��Ԃ̃`�F�b�N
        String l_strMqStatus = l_aioOrderUnitRow.getMqStatus();
        log.debug("���M��� = " + l_strMqStatus);
        
        //�����P��.MQ�X�e�[�^�X != '0'�i�������j�̏ꍇ�A'1'�iNG�j��Ԃ��B
        if (!WEB3MqStatusDef.NOT_SEND_MAIL.equals(l_strMqStatus))
        {
            log.debug("�����P��.MQ�X�e�[�^�X != '0'�i�������j�̏ꍇ�A'1'�iNG�j��Ԃ��B");
            log.exiting(STR_METHOD_NAME);
            return WEB3OrderSendPossibleDef.SEND_NG;
        }
        
        //�R�j�������̃`�F�b�N    
        //�����P��.������    
        String l_strBizDate = l_aioOrderUnitRow.getBizDate();
        Date l_datBizDate = WEB3DateUtility.getDate(l_strBizDate, "yyyyMMdd");
        log.debug("�����P��.������ = " + 
                WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd")); 
        
        //�������t
        Date l_datToday = GtlUtils.getSystemTimestamp();
        log.debug("�������t = " + l_datToday);
       
        //�����P��.������ > �������t�̏ꍇ�A'1'�iNG�j��Ԃ��B
        if (WEB3DateUtility.compareToDay(l_datBizDate, l_datToday) > 0)
        {
            log.debug("�����P��.������ > �������t�̏ꍇ�A'1'�iNG�j��Ԃ��B");
            log.exiting(STR_METHOD_NAME);
            return WEB3OrderSendPossibleDef.SEND_NG;
        }
        
        //�S�j'0'�iOK�j��Ԃ��B
        log.debug("'0'�iOK�j��Ԃ��B");
        log.exiting(STR_METHOD_NAME);
        return WEB3OrderSendPossibleDef.SEND_OK;        
    }
    
    /**
     * (validate��������\)<BR>
     * �o������������\���ǂ����̃`�F�b�N���s���B<BR>
     * <BR>
     * �P�j������Ԃ̃`�F�b�N<BR>
     * <BR>
     *    ������� != 1�i��t�ρj and ��������敪 != 0�i�����l�j<BR>
     * <BR>
     *    �̏ꍇ�A'1'�iNG�j��Ԃ��B<BR>
     * <BR>
     * �Q�j���M��Ԃ̃`�F�b�N<BR>
     * <BR>
     *    �����P��.MQ�X�e�[�^�X != '0'�i�������j<BR>
     * <BR>
     *    �̏ꍇ�A'1'�iNG�j��Ԃ��B<BR>
     * <BR>
     * �R�j'0'�iOK�j��Ԃ��B<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g
     * @@return String
     * @@roseuid 412D80A7000D
     */
    public String validateOrderCancelPossible(AioOrderUnit l_orderUnit) 
    {
        String STR_METHOD_NAME = "validateOrderCancelPossible(AioOrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
        
        AioOrderUnitRow l_aioOrderUnitRow = (AioOrderUnitRow)l_orderUnit.getDataSourceObject();
        //�P�j������Ԃ̃`�F�b�N
        //�������
        OrderStatusEnum l_orderStatus = l_aioOrderUnitRow.getOrderStatus();
        
        //��������敪
        String l_strCancelType = l_aioOrderUnitRow.getCancelType();
        
        //������� != 1�i��t�ρj and ��������敪 != 0�i�����l�j�̏ꍇ�A'1'�iNG�j��Ԃ��B
        if (!OrderStatusEnum.ACCEPTED.equals(l_orderStatus) && 
            !WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(l_strCancelType))
        {
            log.debug("������� != 1�i��t�ρj and ��������敪 != 0�i�����l�j�̏ꍇ");
            log.exiting(STR_METHOD_NAME);
            return WEB3OrderCancelPossibleDef.CANCEL_NG;
        }
        //�Q�j���M��Ԃ̃`�F�b�N
        String l_strMqStatus = l_aioOrderUnitRow.getMqStatus();
        
        //�����P��.MQ�X�e�[�^�X != '0'�i�������j�̏ꍇ�A'1'�iNG�j��Ԃ��B
        if (!WEB3MqStatusDef.NOT_SEND_MAIL.equals(l_strMqStatus))
        {
            log.debug("�����P��.MQ�X�e�[�^�X != '0'�i�������j�̏ꍇ");
            log.exiting(STR_METHOD_NAME);
            return WEB3OrderCancelPossibleDef.CANCEL_NG;
        }      
                
        //�R�j'0'�iOK�j��Ԃ��B
        log.exiting(STR_METHOD_NAME);
        return WEB3OrderCancelPossibleDef.CANCEL_OK;     
    }
    
    /**
     * (get���ߐU����)<BR>
     * ���߂̐U�������擾����B<BR>
     * <BR>
     * �P�j�،���ЃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     *    �⏕����.getInstitution()<BR>
     * <BR>
     * �Q�j���ߐU�������Z�o����B<BR>
     * <BR>
     * �Q�|�P�j�،����Params.�����o���U�����{ = '0'�i�����{�j�̏ꍇ<BR>
     * <BR>
     *    ����.�������̗��c�Ɠ���ԋp����B<BR>
     * <BR>
     * �Q�|�Q�j�،����Params.�����o���U�����{ = '1'�i���{�j�̏ꍇ<BR>
     * <BR>
     *    ����.��������ԋp����B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * 
     * @@param l_datBizDate - (������)
     * @@throws WEB3BaseException
     * @@return Date
     * @@roseuid 412ADEF900FD
     */
    public Date getClosestTransferDate(SubAccount l_subAccount, Date l_datBizDate) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getClosestTransferDate(" +
            "SubAccount l_subAccount, Date l_datBizDate)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
        
        //�P�j�،���ЃI�u�W�F�N�g���擾����B
        Institution l_institution = l_subAccount.getInstitution();
        
        log.debug("�،����.�،����Code = " + l_institution.getInstitutionCode());
        
        //�Q�j���ߐU�������Z�o����B
        InstitutionRow l_institutionRow = (InstitutionRow)l_institution.getDataSourceObject();
        String l_strTheDayTransfer = l_institutionRow.getTheDayTransfer();
        
        Date l_datTransferDate = null;
        log.debug("�،����Params.�����o���U�����{ = " + l_strTheDayTransfer);
        
        //�Q�|�P�j�،����Params.�����o���U�����{ = '0'�i�����{�j�̏ꍇ
        if (WEB3TheDayTransferDef.NOT_ENFORCEMENT.equals(l_strTheDayTransfer))
        {
            log.debug("�،����Params.�����o���U�����{ = '0'�i�����{�j�̏ꍇ");
            //����.�������̗��c�Ɠ���ԋp����B             
            l_datTransferDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);        
        }
        
        //�Q�|�Q�j�،����Params.�����o���U�����{ = '1'�i���{�j�̏ꍇ
        else if (WEB3TheDayTransferDef.ENFORCEMENT.equals(l_strTheDayTransfer))
        {
            log.debug("�،����Params.�����o���U�����{ = '1'�i���{�j�̏ꍇ");
            //����.��������ԋp����B
            l_datTransferDate = l_datBizDate;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_datTransferDate;
    }
    
    /**
     * (validate�敨��������J��)<BR>
     * �ڋq���敨����������J�݂��Ă��邩���`�F�b�N����B <BR>
     * <BR>
     * �i���o�������R���ʃ`�F�b�N.validate�敨��������J��()���\�b�h�ɈϏ�����B�j<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@throws WEB3BaseException
     * @@roseuid 41344AB90026
     */
    public void validateOpenFuturesTradedAccount(SubAccount l_subAccount) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateOpenFuturesTradedAccount(SubAccount l_subAccount) ";
        log.entering(STR_METHOD_NAME);   
        
        //���o�������R���ʃ`�F�b�N�̃I�u�W�F�N�g���擾����B
        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations = 
            (WEB3AioProductTypeOrderManagerReusableValidations)
            AioProductTypeOrderManagerReusableValidations.getInstance();
            
        l_reusableValidations.validateOpenFuturesTradedAccount(l_subAccount);
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�U�։�)<BR>
     * �����_�ł̐U�։񐔂��擾����B<BR>
     * <BR>
     * �P�j�ȉ��̏����Œ����P�ʂ���������B<BR> 
     * <BR>
     *     [��������] <BR>
     *     ����ID�F ����.�⏕����.getMainAccount().����ID <BR>
     *     �⏕����ID�F ����.�⏕����.�⏕����ID <BR>
     *     ���XID�F ����.�⏕����.get����X().���XID <BR>
     *     �������F ����.������ <BR>
     *     �����J�e�S���F ����.�����J�e�S�� <BR>
     *     ������� �F�@@(*) <BR>
     *<BR> 
     * �@@�@@(*)������Ԃ̐ݒ� <BR>
     * �@@�@@ - ����.�����J�e�S�� �� 15�i�ב֕ۏ؋��U�ցj�̏ꍇ�F <BR>
     * �@@�@@�@@�@@�@@�@@������� != ( 6�i�������s�i�V�K�����j�j or 14�i�����ρi��������j�j <BR>
     * �@@�@@ - ����.�����J�e�S�� != 15�i�ב֕ۏ؋��U�ցj�̏ꍇ�F <BR>
     * �@@�@@�@@�@@�@@�@@������� != 6�i�������s�i�V�K�����j�j <BR>
     * <BR>
     * �@@�@@���ב֕ۏ؋��U�ւ̏ꍇ�AFX�V�X�e���̌��ʒʒm�G���[�ɂ���Ď�� <BR>
     *       ���ꂽ�������J�E���g���Ȃ� <BR>
     *<BR> 
     * �Q�j�擾�������X�g�̌�����ԋp����<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_datBizDate - (������)
     * @@param l_orderCategEnum - (�����J�e�S��)
     * @@return int
     * @@throws WEB3BaseException
     * @@roseuid 413537F001F5
     */
    public int getTransferCount(SubAccount l_subAccount, Date l_datBizDate, 
        OrderCategEnum l_orderCategEnum) throws WEB3BaseException
    {       
        String STR_METHOD_NAME = "getTransferCount(" +
                "SubAccount l_subAccount, Date l_datBizDate, " +
                "OrderCategEnum l_orderCategEnum)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.error(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        List l_lisRows = null;
        String l_strWhereClause = "";   
        
        //����ID�F ����.�⏕����.getMainAccount().����ID        
        long l_lngAccountId  = l_subAccount.getMainAccount().getAccountId();
            
        //�⏕����ID�F ����.�⏕����.�⏕����ID
        long l_lngSubAccountId = l_subAccount.getSubAccountId();
            
        //���XID�F ����.�⏕����.get����X().���XID 
        long l_lngBranchId = 
            l_subAccount.getMainAccount().getBranch().getBranchId();
        
        String l_strBizDate = 
            WEB3DateUtility.formatDate(l_datBizDate,"yyyyMMdd");
        
        try
        {
            //(*)������Ԃ̐ݒ� 
            //- ����.�����J�e�S�� �� 15�i�ב֕ۏ؋��U�ցj�̏ꍇ�F 
            //  ������� != ( 6�i�������s�i�V�K�����j�j or 14�i�����ρi��������j�j 
            //- ����.�����J�e�S�� != 15�i�ב֕ۏ؋��U�ցj�̏ꍇ�F 
            //  ������� != 6�i�������s�i�V�K�����j�j
            if (OrderCategEnum.FX.equals(l_orderCategEnum))
            { 
                log.debug("����.�����J�e�S�� �� 15�i�ב֕ۏ؋��U�ցj�̏ꍇ");
                
                l_strWhereClause = "account_id = ? and sub_account_id = ? " +
                    "and branch_id = ? and biz_date = ? and order_categ = ? " +
                    "and (order_status <> ? and order_status <> ?)";
                            
        
                Object[] l_bindVars = {
                        new Long(l_lngAccountId), 
                        new Long(l_lngSubAccountId), 
                        new Long(l_lngBranchId), 
                        l_strBizDate, 
                        l_orderCategEnum, 
                        OrderStatusEnum.NOT_ORDERED,    //6 �i�������s�i�V�K�����j�j
                        OrderStatusEnum.CANCELLED       //14�i�����ρi��������j�j
                        };
                l_lisRows =
                    Processors.getDefaultProcessor().doFindAllQuery(
                        AioOrderUnitRow.TYPE,
                        l_strWhereClause,
                        null,
                        l_bindVars);
            }
            else
            {
                log.debug("����.�����J�e�S�� !�� 15�i�ב֕ۏ؋��U�ցj�̏ꍇ");
                l_strWhereClause = "account_id = ? and sub_account_id = ? " +
                "and branch_id = ? and biz_date = ? and order_categ = ? " +
                "and order_status <> ?";
    
                Object[] l_bindVars = {
                        new Long(l_lngAccountId), 
                        new Long(l_lngSubAccountId), 
                        new Long(l_lngBranchId), 
                        l_strBizDate, 
                        l_orderCategEnum, 
                        OrderStatusEnum.NOT_ORDERED    //6 �i�������s�i�V�K�����j�j                    
                        };
                l_lisRows =
                    Processors.getDefaultProcessor().doFindAllQuery(
                        AioOrderUnitRow.TYPE,
                        l_strWhereClause,
                        null,
                        l_bindVars);
            }
            
            log.exiting(STR_METHOD_NAME);
            
            log.debug("�擾�������X�g�̌��� = " + l_lisRows.size());
            
            //�Q�j�擾�������X�g�̌�����ԋp����B 
            return l_lisRows.size();
            
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (validate�U�։\��)<BR>
     * �����_�ł̐U�։񐔂��A�U�։\�񐔂𒴂��ĂȂ����`�F�b�N����B<BR>
     * <BR>
     * �P�j�����_�ł̐U�։񐔂��擾����B <BR>
     *<BR> 
     *  this.get�U�։�() <BR>
     * <BR>
     *  [����]<BR> 
     *      �⏕�����F ����.�⏕���� <BR>
     *      �������F ����.������ <BR>
     *      �����J�e�S���F ����.�����J�e�S�� <BR>
     * <BR>
     * �Q�j�ݒ肳��Ă���U�։\�񐔂��擾����B <BR>
     *<BR> 
     *  �U�։\�� = �⏕����.getMainAccount().�U�։\�� <BR>
     *<BR> 
     * �R�j�ȉ��̏����ɓ��Ă͂܂�ꍇ�A��O���X���[����B <BR>
     *<BR> 
     *  �P�j�Ŏ擾�����U�։� + 1 > �Q�j�Ŏ擾�����U�։\�� <BR>
     *<BR> 
     * �S�j�Q�j�Ŏ擾�����U�։\�񐔂�ԋp����B<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_datBizDate - (������)<BR>
     * @@param l_orderCategEnum - (�����J�e�S��)<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41355F81012F
     */
    public int validateTransferPossibleCount(
        SubAccount l_subAccount, Date l_datBizDate, OrderCategEnum l_orderCategEnum)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateTransferPossibleCount(" +
            "SubAccount l_subAccount, Date l_datBizDate)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //�P�j�����_�ł̐U�։񐔂��擾����B
        int l_intTransferCount1 = this.getTransferCount(l_subAccount, l_datBizDate, l_orderCategEnum);
        log.debug("�����_�ł̐U�։� = " + l_intTransferCount1);
                
        //�Q�j�ݒ肳��Ă���U�։\�񐔂��擾����B
        //�U�։\�� = �⏕����.getMainAccount().�U�։\�� 
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_subAccount.getMainAccount().getDataSourceObject();
        int l_intTransferCount2 = l_mainAccountRow.getTransferCount();
        
        log.debug("�⏕����.getMainAccount().�U�։\�� = " + l_intTransferCount2);
        
        //�R�j�ȉ��̏����ɓ��Ă͂܂�ꍇ�A��O���X���[����B
        //�P�j�Ŏ擾�����U�։� + 1 > �Q�j�Ŏ擾�����U�։\��
        if (l_intTransferCount1 + 1 > l_intTransferCount2)
        {
            log.debug("�U�։񐔂�����񐔂𒴂��Ă��܂��B ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00740,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�擾�����U�։�[" + l_intTransferCount1 + "] + 1 > �擾�����U�։\��[" + 
                l_intTransferCount2 + "]");
        }       
        log.exiting(STR_METHOD_NAME);
        
        // =========remain zhuo-yong NO.3 begin ============
        
        //�S�j�Q�j�Ŏ擾�����U�։\�񐔂�ԋp����B
        return l_intTransferCount2;
        
        // =========remain zhuo-yong NO.3 end ============
    }
    
    /**
     * (validate�،��U�։\��)<BR>
     * �����_�ł̏،��U�։񐔂��A�U�։\�񐔂𒴂��ĂȂ����`�F�b�N����B <BR>
     * <BR>
     * �P�j�����_�ł̐U�։񐔂��擾����B<BR>
     * <BR>
     *    �P�|�P�j�ȉ��̏����Œ����P�ʂ���������B<BR>
     * <BR>
     *    [��������] <BR>
     *    ����ID�F ����.�⏕����.getMainAccount().����ID <BR>
     *    �⏕����ID�F ����.�⏕����.�⏕����ID <BR>
     *    ���XID�F ����.�⏕����.get����X().���XID <BR>
     *    �������F ����.������ <BR>
     *    �����J�e�S���F 14�i�،��U�ցj<BR>
     *    ������� != 6�i�������s�i�V�K�����j�j <BR>
     * <BR>
     *    �P�|�Q�j�擾�������X�g�̌�����U�։񐔂Ƃ���B<BR>
     * �Q�j�ݒ肳��Ă���U�։\�񐔂��擾����B <BR>
     *    �U�։\�� = �⏕����.get����X().�،��U�֏����<BR>
     * <BR>
     * �R�j�ȉ��̏����ɓ��Ă͂܂�ꍇ�A��O���X���[����B<BR>
     *   �P�j�Ŏ擾�����U�։� + 1 > �Q�j�Ŏ擾�����U�։\�� 
     *         class: WEB3BusinessLayerException <BR>
     *         tag:   BUSINESS_ERROR_01033 <BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_datBizDate - (������)
     * @@throws WEB3BaseException
     * @@roseuid 41355F81012F
     */
    public void validateInstitutionTransferPossibleCount(
        SubAccount l_subAccount, Date l_datBizDate)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateInstitutionTransferPossibleCount(" +  
            "SubAccount l_subAccount, Date l_datBizDate)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //�P�j�����_�ł̐U�։񐔂��擾����B        

        //�P�|�P�j�ȉ��̏����Œ����P�ʂ���������B
        //[��������] 
        //����ID�F ����.�⏕����.getMainAccount().����ID 
        //�⏕����ID�F ����.�⏕����.�⏕����ID 
        //���XID�F ����.�⏕����.get����X().���XID 
        //�������F ����.������ 
        //�����J�e�S���F 14�i�،��U�ցj 
        //������� != 6�i�������s�i�V�K�����j�j 
        
        String l_strWhereClause = "account_id = ? and sub_account_id = ? " +
            "and branch_id = ? and biz_date = ? and order_categ = ? " +
            "and order_status <> ?";    
                    
        //����ID�F ����.�⏕����.getMainAccount().����ID        
        long l_lngAccountId  = l_subAccount.getMainAccount().getAccountId();
            
        //�⏕����ID�F ����.�⏕����.�⏕����ID
        long l_lngSubAccountId = l_subAccount.getSubAccountId();
            
        //���XID�F ����.�⏕����.get����X().���XID 
        long l_lngBranchId = l_subAccount.getMainAccount().getBranch().getBranchId();
        
        String l_strBizDate = WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd");
        
        List l_lisRows = null;

        try
        {
            Object l_bindVars[] = {
                new Long(l_lngAccountId), 
                new Long(l_lngSubAccountId), 
                new Long(l_lngBranchId), 
                l_strBizDate, 
                OrderCategEnum.ASSET_TRANSFER, //14�i�،��U�ցj
                OrderStatusEnum.NOT_ORDERED };      //6�i�������s�i�V�K�����j�j
            
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    AioOrderUnitRow.TYPE,
                    l_strWhereClause,
                    null,
                    l_bindVars);
                    
            //�P�|�Q�j�擾�������X�g�̌�����U�։񐔂Ƃ���B           
            int l_intTransferCount = l_lisRows.size();
            
            //�Q�j�ݒ肳��Ă���U�։\�񐔂��擾����B
            //�U�։\�� = �⏕����.get����X().�،��U�֏����
            BranchRow branchRow =
                (BranchRow) l_subAccount.getMainAccount().getBranch().getDataSourceObject(); 
            
            int l_intTransferPossibleCount = branchRow.getMarginSecTransferMaxCount();
            
            //�R�j�ȉ��̏����ɓ��Ă͂܂�ꍇ�A��O���X���[����B
            //�P�j�Ŏ擾�����U�։� + 1 > �Q�j�Ŏ擾�����U�։\�� 

            if (l_intTransferCount + 1 > l_intTransferPossibleCount)
            {
                log.debug("�U�։񐔂�����񐔂𒴂��Ă��܂��B");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00740,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�擾�����U�։�[" + l_intTransferCount + 
                    "] + 1 > �擾�����U�։\��[" + l_intTransferPossibleCount + "]");
            }
            
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (submit�U�֒���)<BR>
     * �U�֒����̓o�^���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���o�������jsubmit�U�֒����v �Q��<BR>
     * @@param l_subAccount (�⏕����)
     * @@param l_productTypeEnum (�����^�C�v)
     * @@param l_orderTypeEnum (�������)
     * @@param l_newOrderSpec (�������e)
     * @@param l_aioOrderManagerPersistenceEventInterceptor (�C���^�Z�v�^)
     * @@param l_lngOrder (����ID)
     * @@param l_strPassword (�p�X���[�h)
     * @@throws WEB3BaseException
     * @@roseuid 41358DBB0006
     */
    public void submitTransferOrder(
            SubAccount l_subAccount,
            ProductTypeEnum l_productTypeEnem,
            OrderTypeEnum l_orderTypeEnum,
            NewOrderSpec l_newOrderSpec,
            AioOrderManagerPersistenceEventInterceptor l_aioOrderManagerPersistenceEventInterceptor, 
            long l_lngOrderId, 
            String l_strPassword)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "submitTransferOrder()";
        log.entering(STR_METHOD_NAME);

        //=========remain zhou-yong NO.2 begin ===========
        
        //1.1) setThreadLocalPersistenceEventInterceptor(AioOrderManagerPersistenceEventInterceptor)
        //(AIO�����}�l�[�W��::setThreadLocalPersistenceEventInterceptor)
        //�A�C�e���̒�`
        //�C���^�Z�v�^���X���b�h�ɃZ�b�g����B
        //[����] 
        //arg0�F ����.�C���^�Z�v�^ 
        this.setThreadLocalPersistenceEventInterceptor(
            l_aioOrderManagerPersistenceEventInterceptor);
        
        //1.2) submitNewOrder(SubAccount, ProductTypeEnum, NewOrderSpec, long,
        //     �_���r���[::java::lang::String, boolean)
        //�A�C�e���̒�`
        //�U�֒����̓o�^���s���B
        //[����] 
        //�⏕�����F�@@����.�⏕���� 
        //�����^�C�v�F�@@����.�����^�C�v 
        //�������e�F�@@����.�������e 
        //�����h�c�F�@@����.����ID 
        //�p�X���[�h�F�@@����.�p�X���[�h 
        //isSkip�����R���F�@@true 
        OrderSubmissionResult l_orderSubmitResult = 
            this.submitNewOrder(
                l_subAccount, 
                l_productTypeEnem,
                l_newOrderSpec,
                l_lngOrderId,
                l_strPassword,
                true);
        
        if (l_orderSubmitResult.getProcessingResult().isFailedResult())
        {
            log.debug("�U�֒����̓o�^�G���[" + l_orderSubmitResult.getProcessingResult().getErrorInfo());
            throw new WEB3SystemLayerException(
                l_orderSubmitResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //=========remain zhou-yong NO.2 end ===========
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate���ϋ@@�֎�t�\)<BR>
     * �Y�����錈�ϋ@@�ւ���t���ԓ����ǂ������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@ThreadLocalSystemAttributesRegistry���A<BR>
     * ��t�������擾����B<BR>
     * <BR>�@@ThreadLocalSystemAttributesRegistry.getAttribute<BR>
     * ("xblocks.gtl.attributes.systemtimestamp")<BR>
     * 
     * �Q�j��t�����̃J�����_���擾����B<BR>
     *    Calendar.getInstance()<BR>
     *    Calendar.setTime()<BR>
     * <BR>
     * �R�j�O���@@�֎�t���ԃe�[�u���`�F�b�N <BR>
     * <BR>
     * �R�|�P�j�O���@@�֎�t���ԃe�[�u������ȉ��̏����̃��R�[�h���擾����B<BR>
     * <BR>
     *      [��������]<BR>
     *      �O���@@�փR�[�h = this.���ϋ@@��ID and<BR>
     *      �j���敪 = Calendar.get("DAY_OF_WEEK")�̖߂�l-1 and<BR>
     *      ��t���ԁiFROM) <= �J�����_�̎����iHHMMSS�j���� and<BR>
     *      ��t���ԁiTO) >= �J�����_�̎����iHHMMSS�j����<BR>
     * <BR>
     *   �擾�ł��Ȃ������ꍇ�Afalse��Ԃ��B<BR>
     * <BR>
     * �R�|�Q�j�擾�������R�[�h�̃T�[�r�X�敪��0�i��~���j�̏ꍇ�Afalse��Ԃ��B<BR>
     * <BR>
     * �S�j�O���@@�֎�t���ԊO�i�j���j�e�[�u���`�F�b�N <BR>
     *   �O���@@�֎�t���ԊO�i�j���j�e�[�u������ȉ��̏����̃��R�[�h���擾����<BR>
     * <BR>
     *      [��������]<BR>
     *      �O���@@�փR�[�h = this.���ϋ@@��ID and<BR>
     *      (�� = Calendar.get("MONTH")�̖߂�l+1 or <BR>
     *       �� = "0" ) and<BR>
     *      �j�� = Calendar.get("DAY_OF_WEEK")�̖߂�l-1 and<BR>
     *      (�j���ԍ� = Calendar.get("DAY_OF_WEEK_IN_MONTH")�̖߂�l or<BR>
     *       �j���ԍ� = "0") and<BR>
     *      ��~���ԁiFROM) <= �J�����_�̎����iHHMMSS�j���� and<BR>
     *      ��~���ԁiTO) >= �J�����_�̎����iHHMMSS�j����<BR>
     * <BR>
     *   �擾�ł����ꍇ�Afalse��Ԃ��B<BR>
     * <BR>
     * �T�j�O���@@�֎�t���ԊO�i���t�j�e�[�u���`�F�b�N <BR>
     *   �O���@@�֎�t���ԊO�i���t�j�e�[�u������ȉ��̏����̃��R�[�h���擾����<BR>
     * <BR>
     *      [��������]<BR>
     *      �O���@@�փR�[�h = this.���ϋ@@��ID and<BR>
     *      ���t = �J�����_�̓��t(MMDD)���� and<BR>
     *      ��~���ԁiFROM) <= �J�����_�̎����iHHMMSS�j���� and<BR>
     *      ��~���ԁiTO) >= �J�����_�̎����iHHMMSS�j����<BR>
     * <BR>
     *   �擾�ł����ꍇ�Afalse��Ԃ��B<BR>
     * <BR>
     * �U�j�c�Ɠ��`�F�b�N<BR>
     *   ��t�����̓��t����c�Ɠ��������ꍇ�́A�ȍ~�̏������s�킸��true��ԋp����B<BR>
     * <BR>
     * �V�j��Еʌ��ϋ@@�֏����e�[�u���`�F�b�N<BR>
     *   ��Еʌ��ϋ@@�֏����e�[�u������ȉ��̏����̃��R�[�h���擾����<BR>
     * <BR>
     *      [��������]<BR>
     *      �،���ЃR�[�h = this.�،���ЃR�[�h and
     *      ���X�R�[�h = this.���X�R�[�h and
     *      �O���@@�փR�[�h = this.���ϋ@@��ID and<BR>
     *      ��t��~���ԁi��) <= �J�����_�̎����iHHMMSS�j���� and<BR>
     *      ��t��~���ԁi��) >= �J�����_�̎����iHHMMSS�j����<BR>
     * <BR>
     *   �擾�ł����ꍇ�Afalse��Ԃ��B<BR>
     * <BR>
     * �W�jtrue��Ԃ��B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)
     * @@param l_strBranchCode - (���X�R�[�h)
     * @@param l_strPaySchemeId - (���ϋ@@��ID)<BR>
     * �}���`�o���N���ς��s�����ϋ@@�ւ�ID
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 413BCA700233
     */
    public boolean validatePaySchemeAcceptPossible(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strPaySchemeId) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validatePaySchemeAcceptPossible(String l_strPaySchemeId)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@ThreadLocalSystemAttributesRegistry���A��t�������擾����B
        Date l_datServiceDate = GtlUtils.getSystemTimestamp();
        
        //�Q�j��t�����̃J�����_���擾����B
        Calendar l_calendar = Calendar.getInstance();
        l_calendar.setTime(l_datServiceDate);
        Date l_datServiceTime = l_calendar.getTime();
        
        String l_strServiceTime = 
            WEB3DateUtility.formatDate(l_datServiceTime, "HHmmss");
        
        log.debug("��t���� = " + l_strServiceTime);
        
        //�R�j�O���@@�֎�t���ԃe�[�u���`�F�b�N
        //�R�|�P�j�O���@@�֎�t���ԃe�[�u������ȉ��̏����̃��R�[�h���擾����B
        //[��������] 
        //�O���@@�փR�[�h = this.���ϋ@@��ID and 
        //�j���敪 = Calendar.get("DAY_OF_WEEK")�̖߂�l-1 and 
        //��t���ԁiFROM) <= �J�����_�̎����iHHMMSS�j���� and 
        //��t���ԁiTO) >= �J�����_�̎����iHHMMSS�j���� 

        List l_lisOtherOrgSrvTimeRows = null;
        List l_lisOtherOrgOutOfSrvWeekRows = null;
        List l_lisOtherOrgOutOfSrvDateRows = null;
        List l_lisCompBnakCondithinRows = null;

        //�J�����_�̎����iHHMMSS�j����
        String l_strWhereClause = "other_org_code = ? and week_div = ? " +
            "and service_start_time <= ? and service_end_time >= ?";
        
        //�j���敪 = Calendar.get("DAY_OF_WEEK")�̖߂�l-1
        String l_strWeekDiv = (l_calendar.get(Calendar.DAY_OF_WEEK) - 1) + "";

        log.debug("���ϋ@@��ID = " + l_strPaySchemeId);
        log.debug("�j���敪 = " + l_strWeekDiv);
        
        Object l_bindServiceTimeVars[] = {
            l_strPaySchemeId, 
            l_strWeekDiv, 
            l_strServiceTime, 
            l_strServiceTime };
        
        //=======remain zhou-yong NO.3 �䒠.123  being =========
        
        try
        {               
            l_lisOtherOrgSrvTimeRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    OtherOrgSrvTimeRow.TYPE,
                    l_strWhereClause,
                    null,
                    l_bindServiceTimeVars);
            
            log.debug("�O���@@�֎�t���ԃe�[�u���擾.size() = " + l_lisOtherOrgSrvTimeRows.size());
            //�擾�ł��Ȃ������ꍇ�Afalse��Ԃ��B 
            if (l_lisOtherOrgSrvTimeRows == null || l_lisOtherOrgSrvTimeRows.size() == 0)
            {
                log.debug("�O���@@�֎�t���ԃe�[�u���擾�ł��Ȃ������ꍇ");
                log.exiting(STR_METHOD_NAME); 
                return false;
            }
            
            //�擾�������R�[�h�̃T�[�r�X�敪��0�i��~���j�̏ꍇ�Afalse��Ԃ��B            
            OtherOrgSrvTimeRow l_otherOrgSrvTimeRow = (OtherOrgSrvTimeRow) 
                l_lisOtherOrgSrvTimeRows.get(0);
            
            log.debug("search result : = " + l_otherOrgSrvTimeRow);
            log.debug("�擾�������R�[�h�̃T�[�r�X�敪: = " + l_otherOrgSrvTimeRow.getServiceDiv());
            
            if (WEB3ServiceDivDef.STOPPING.equals(l_otherOrgSrvTimeRow.getServiceDiv()))
            {
                log.debug("�擾�������R�[�h�̃T�[�r�X�敪��0�i��~���j�̏ꍇ�Afalse��Ԃ��B");
                log.exiting(STR_METHOD_NAME);
                return false;
            }
            
            //�S�j�O���@@�֎�t���ԊO�i�j���j�e�[�u���`�F�b�N 
            //���ϋ@@�֎�t���ԊO�i�j���j�e�[�u������ȉ��̏����̃��R�[�h���擾����B 

            //[��������] 
            // �O���@@�փR�[�h = this.���ϋ@@��ID and 
            // (�� = Calendar.get("MONTH")�̖߂�l+1 or 
            // �� = "0" ) and 
            // �j�� = Calendar.get("DAY_OF_WEEK")�̖߂�l-1 and 
            // (�j���ԍ� = Calendar.get("DAY_OF_WEEK_IN_MONTH")�̖߂�l or 
            // �j���ԍ� = "0") and 
            // ��~���ԁiFROM) <= �J�����_�̎����iHHMMSS�j���� and 
            // ��~���ԁiTO) >= �J�����_�̎����iHHMMSS�j���� 

            l_strWhereClause = "other_org_code = ? and (month = ? or month = ?)" +
                " and week_div = ? and (week_no = ? or week_no = ?)" + 
                " and suspension_start_time <= ? and suspension_end_time >= ?";  
                      
            //�� = Calendar.get("MONTH")�̖߂�l+1
            String l_strMonth = l_calendar.get(Calendar.MONTH) + 1 + "";
            
            //�j�� = Calendar.get("DAY_OF_WEEK")�̖߂�l-1
            String l_strDayOfWeek = l_calendar.get(Calendar.DAY_OF_WEEK) - 1 + "";
            
            //�j���ԍ� = Calendar.get("DAY_OF_WEEK_IN_MONTH")�̖߂�l
            String l_strWeekOfMonth = l_calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH) + "";
            
            Object l_bindServiceWeekVars[] = {
                l_strPaySchemeId, 
                l_strMonth, 
                "0", 
                l_strDayOfWeek, 
                l_strWeekOfMonth, 
                "0", 
                l_strServiceTime, 
                l_strServiceTime };
            
            l_lisOtherOrgOutOfSrvWeekRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    OtherOrgOutOfSrvWeekRow.TYPE,
                    l_strWhereClause,
                    null,
                    l_bindServiceWeekVars);
            
            //�擾�ł����ꍇ�Afalse��Ԃ��B
            if (l_lisOtherOrgOutOfSrvWeekRows != null && l_lisOtherOrgOutOfSrvWeekRows.size() > 0)
            {
                log.debug("�O���@@�֎�t���ԊO�i�j���j�e�[�u���擾�ł����ꍇ�Afalse��Ԃ��B");
                log.exiting(STR_METHOD_NAME); 
                return false;
            }
            
            // �T�j�O���@@�֎�t���ԊO�i���t�j�e�[�u���`�F�b�N 
            // �O���@@�֎�t���ԊO�i���t�j�e�[�u������ȉ��̏����̃��R�[�h���擾����B 
            // 
            // [��������] 
            // �O���@@�փR�[�h = this.���ϋ@@��ID and 
            // ���t = �J�����_�̓��t(MMDD)���� and 
            // ��~���ԁiFROM) <= �J�����_�̎����iHHMMSS�j���� and 
            // ��~���ԁiTO) >= �J�����_�̎����iHHMMSS�j����  

            l_strWhereClause = "other_org_code = ? and suspension_date = ? " +                
            "and suspension_start_time <= ? and suspension_end_time >= ?";  
                      
            //���t = �J�����_�̓��t(MMDD)����
            String l_strSuspensionDate = WEB3DateUtility.formatDate(
                GtlUtils.getSystemTimestamp(),"MMdd");

            Object l_bindServiceDateVars[] = {
                l_strPaySchemeId, 
                l_strSuspensionDate, 
                l_strServiceTime, 
                l_strServiceTime };
            
            l_lisOtherOrgOutOfSrvDateRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    OtherOrgOutOfSrvDateRow.TYPE,
                    l_strWhereClause,
                    null,
                    l_bindServiceDateVars);            
            
            //�擾�ł����ꍇ�Afalse��Ԃ��B
            if (l_lisOtherOrgOutOfSrvDateRows != null && l_lisOtherOrgOutOfSrvDateRows.size() > 0)
            {                
                log.debug("�O���@@�֎�t���ԊO�i���t�j�e�[�u���擾�ł����ꍇ�Afalse��Ԃ��B");
                log.exiting(STR_METHOD_NAME); 
                return false;
            }

            //=======remain zhou-yong NO.3 �䒠.123  end =========

			//�U�j�c�Ɠ��`�F�b�N
			//  ��t�����̓��t����c�Ɠ��������ꍇ�́A�ȍ~�̏������s�킸��true��ԋp����B
			WEB3GentradeBizDate l_genBizDate = new WEB3GentradeBizDate((Timestamp)l_datServiceDate);
			try {
				l_genBizDate.roll(0);
			} catch (WEB3SystemLayerException e) {
				return true;
			}
						
            //�V�j��Еʌ��ϋ@@�֏����e�[�u���`�F�b�N
            //  ��Еʌ��ϋ@@�֏����e�[�u������ȉ��̏����̃��R�[�h���擾����
            //
            //     [��������]
            //     �،���ЃR�[�h = this.�،���ЃR�[�h and
            //     ���X�R�[�h = this.���X�R�[�h and
            //     �O���@@�փR�[�h = this.���ϋ@@��ID and
            //     ��t��~���ԁi��) <= �J�����_�̎����iHHMMSS�j���� and
            //     ��t��~���ԁi��) >= �J�����_�̎����iHHMMSS�j����

            l_strWhereClause = "institution_code = ? " +
            "and branch_code = ? and pay_scheme_id = ? " +
            "and suspension_start_time <= ? and suspension_end_time >= ?";  

            Object l_bindCompBankVars[] = {
                l_strInstitutionCode,
                l_strBranchCode,
                l_strPaySchemeId,
                l_strServiceTime,
                l_strServiceTime };

            l_lisCompBnakCondithinRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    CompBankConditionRow.TYPE,
                    l_strWhereClause,
                    null,
                    l_bindCompBankVars);

            //  �擾�ł����ꍇ�Afalse��Ԃ��B
            if (l_lisCompBnakCondithinRows != null && l_lisCompBnakCondithinRows.size() > 0)
            {                
                log.debug("��Еʌ��ϋ@@�֏����e�[�u���̃f�[�^�擾�ł����ꍇ�Afalse��Ԃ��B");
                log.exiting(STR_METHOD_NAME); 
                return false;
            }

            log.exiting(STR_METHOD_NAME); 
            //�W�jtrue��Ԃ��B
            return true;
                
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }     
    }

    /**
     * (get�I�����C������������)<BR>
     * ��������������ۂ̔��������擾����B<BR>
     * <BR>
     * �P�j�@@ThreadLocalSystemAttributesRegistry���A��t�������擾����B<BR>
     * �@@ThreadLocalSystemAttributesRegistry.getAttribute<BR>
     * ("xblocks.gtl.attributes.systemtimestamp")<BR>
     * <BR>
     * �Q�j��t�����̃J�����_���擾����B<BR>
     *    Calendar.getInstance()<BR>
     *    Calendar.setTime()<BR>
     * <BR>
     * �R�j�O���@@�֎�t���ԃe�[�u������ȉ��̏����̃��R�[�h���擾����B<BR>
     * <BR>
     *      [��������]<BR>
     *      �O���@@�փR�[�h  = ����.���ϋ@@��ID and<BR>
     *      �j���敪 = Calendar.get("DAY_OF_WEEK")�̖߂�l-1 and<BR>
     *      ��t���ԁiFROM) <= �J�����_�̎����iHHMMSS�j���� and<BR>
     *      ��t���ԁiTO) >= �J�����_�̎����iHHMMSS�j����<BR>
     * <BR>
     *   �擾�ł��Ȃ������ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00751<BR>
     * <BR>
     * <BR>
     * �S�j�擾�������R�[�h�̎�t���敪�ɏ]���A��������Ԃ��B<BR>
     * <BR>
     *    - ��t���敪��1�i�����j�̏ꍇ�A�J�����_�̓������t��Ԃ��B<BR>
     *    - ��t���敪��2�i�����j�̏ꍇ�A�J�����_�̗��c�Ɠ����t��Ԃ��B<BR>
     * @@param l_strPaySchemeId - (���ϋ@@��ID)
     * @@return Date
     * @@throws WEB3BaseException
     * @@roseuid 413BCA700253
     */
    public Date getOnlineCashinBizDate(String l_strPaySchemeId) 
        throws WEB3BaseException
    {        
        String STR_METHOD_NAME = "getOnlineCashinBizDate(String l_strPaySchemeId)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@ThreadLocalSystemAttributesRegistry���A��t�������擾����B
        Date l_datServiceDate = GtlUtils.getSystemTimestamp();
        log.debug("��t���� = " + l_datServiceDate);

        //�Q�j��t�����̃J�����_���擾����B
        Calendar l_calendar = Calendar.getInstance();
        l_calendar.setTime(l_datServiceDate);
        
        //========remain zhou-yong NO.4 begin ========
        
        //�R�j�O���@@�֎�t���ԃe�[�u������ȉ��̏����̃��R�[�h���擾����B
        //[��������] 
        //�O���@@�փR�[�h = ����.���ϋ@@��ID and 
        //�j���敪 = Calendar.get("DAY_OF_WEEK")�̖߂�l-1 and 
        //��t���ԁiFROM) <= �J�����_�̎����iHHMMSS�j���� and 
        //��t���ԁiTO) >= �J�����_�̎����iHHMMSS�j���� 

        List l_lisRows = null;
        String l_strWhereClause = "other_org_code = ? and week_div = ?" +
            " and service_start_time <= ? and service_end_time >= ?";
        
        //�j���敪 = Calendar.get("DAY_OF_WEEK")�̖߂�l-1
        String l_strWeekDiv = l_calendar.get(Calendar.DAY_OF_WEEK) - 1 + "";
        
        //�J�����_�̎����iHHMMSS�j����
        Date l_datServiceTime = l_calendar.getTime();
        String l_strServiceTime = 
            WEB3DateUtility.formatDate(l_datServiceTime, "HHmmss");
        
        log.debug("���ϋ@@��ID = " + l_strPaySchemeId);
        log.debug("�j���敪 = " + l_strWeekDiv);
        log.debug("�J�����_�̎����iHHMMSS�j���� = " + l_strServiceTime);
        
        Object l_bindVars[] = {
            l_strPaySchemeId, 
            l_strWeekDiv, 
            l_strServiceTime, 
            l_strServiceTime};
        try
        {            
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    OtherOrgSrvTimeRow.TYPE,
                    l_strWhereClause,
                    null,
                    l_bindVars);
                    
            //�擾�ł��Ȃ������ꍇ�A��O���X���[����B 
            if (l_lisRows == null || l_lisRows.size() == 0)
            {
                log.debug("�w�肵���O���@@�ւ̎�t���ԂɊւ����񂪎擾�ł��܂���ł����B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00751,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�w�肵���O���@@�ւ̎�t���ԂɊւ����񂪎擾�ł��܂���ł����B");
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //�S�j�擾�������R�[�h�̎�t���敪�ɏ]���A��������Ԃ��B            
        
        OtherOrgSrvTimeRow l_otherOrgSrvTimeRow = (OtherOrgSrvTimeRow) l_lisRows.get(0);
        
        //- ��t���敪��1�i�����j�̏ꍇ�A�J�����_�̓������t��Ԃ��B
        Date l_datOnlineCashinBizDate = null;
        if (WEB3ServiceDateDivDef.DAY_BIZ_DATE.equals(
            l_otherOrgSrvTimeRow.getServiceDateDiv()))
        {
            log.debug("��t���敪��1�i�����j�̏ꍇ�A�J�����_�̓������t��Ԃ��B"); 
            l_datOnlineCashinBizDate = l_datServiceDate;
        }
        //- ��t���敪��2�i�����j�̏ꍇ�A�J�����_�̗��c�Ɠ����t��Ԃ��B
        else
        {
            log.debug("��t���敪��2�i�����j�̏ꍇ�A�J�����_�̗��c�Ɠ����t��Ԃ��B"); 
            
            Date l_datBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datServiceDate.getTime())).roll(1);                

            l_datOnlineCashinBizDate = l_datBizDate;
        }     
        
        //========remain zhou-yong NO.4 end ========
        
        log.exiting(STR_METHOD_NAME); 
        return WEB3DateUtility.toDay(l_datOnlineCashinBizDate);
     
    }
    
    /**
     * (get�U�֒����P��)<BR>
     * �U�֒����̒����P�ʃI�u�W�F�N�g�̔z����擾����B<BR>
     * <BR>
     * �P�j�ȉ��̃I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �،���ЃI�u�W�F�N�g = <BR>
     * �g���A�J�E���g�}�l�[�W��.getInstitutiion(����.�،���ЃR�[�h)<BR>
     * ���X�I�u�W�F�N�g = <BR>
     * �g���A�J�E���g�}�l�[�W��.get���X(����.�،���ЃR�[�h, ����.���X�R�[�h)<BR>
     * �����i�ڋq�j�I�u�W�F�N�g = <BR>
     * �g���A�J�E���g�}�l�[�W��.getMainAccount(�،���ЃI�u�W�F�N�g, <BR>
     *       ���X�I�u�W�F�N�g, ����.�ڋq�R�[�h)<BR>
     * <BR>
     * �Q�j�����P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * [��������]<BR>
     * ����ID = ����.����ID<BR>
     * ���XID = ���X.���XID<BR>
     * �����^�C�v = ProductTypeEnum.CASH<BR>
     * ���ʃR�[�h = ����.���ʃR�[�h<BR>
     * <BR>
     * �擾���������P��.�����J�e�S�� != 15(�ב֕ۏ؋��U��)�̏ꍇ�A<BR>
     * �擾���������P�ʐ� != 2�̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     *         class: WEB3SystemLayerException <BR>
     *         tag:   SYSTEM_ERROR_80006 <BR>
     * ���ב֕ۏ؋��U�ւ̏ꍇ�͍쐬����钍���P�ʐ� == 1 <BR>
     * <BR>
     * �R�j�擾���������P�ʂ�z��ŕԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)
     * @@param l_strBranchCode - (���X�R�[�h)
     * @@param l_strAccountCode - (�ڋq�R�[�h)
     * @@param l_strOrderRequestNumber - (���ʃR�[�h)
     * @@return AioOrderUnit[]
     * @@throws NotFoundException, WEB3BaseException
     * @@roseuid 413C4E3300AC
     */
    public AioOrderUnit[] getTransferOrderUnit(
        String l_strInstitutionCode, 
        String l_strBranchCode, 
        String l_strAccountCode, 
        String l_strOrderRequestNumber)
        throws NotFoundException, WEB3BaseException
    {
        String STR_METHOD_NAME = "getTransferOrderUnit()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�ȉ��̃I�u�W�F�N�g���擾����B
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        
        //�g���A�J�E���g�}�l�[�W���擾����    
        WEB3GentradeAccountManager l_web3GentradeAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        //�،���ЃI�u�W�F�N�g = �g���A�J�E���g�}�l�[�W��.getInstitutiion(����.�،���ЃR�[�h) 
        Institution l_institution = l_web3GentradeAccountManager.getInstitution(
            l_strInstitutionCode);

        //���X�I�u�W�F�N�g = �g���A�J�E���g�}�l�[�W��.get���X(�،���ЃI�u�W�F�N�g, ����.���X�R�[�h)
        Branch l_branch = l_web3GentradeAccountManager.getBranch(
            l_institution, l_strBranchCode);

        //�����i�ڋq�j�I�u�W�F�N�g = �g���A�J�E���g�}�l�[�W��.getMainAccount
        //(�،���ЃI�u�W�F�N�g, ���X�I�u�W�F�N�g, ����.�ڋq�R�[�h)
        MainAccount l_mainAccount = l_web3GentradeAccountManager.getMainAccount(
            l_institution, l_branch, l_strAccountCode);

        //�Q�j�����P�ʃI�u�W�F�N�g���擾����B
        List l_lisRows = null;
        
        try
        {            
            String l_strWhereClause = "account_id = ? and branch_id = ?" +
                " and order_request_number = ?";
            
            //����ID = ����.����ID
            long l_lngAccountId = l_mainAccount.getAccountId();     
            log.debug("����.����ID = " + l_lngAccountId);
                        
            //���XID = ���X.���XID
            long l_lngBranchId = l_branch.getBranchId();
            log.debug("���X.���XID = " + l_lngBranchId);
            log.debug("���ʃR�[�h = " + l_strOrderRequestNumber);
            
            //[��������]
            //����ID = ����.����ID
            //���XID = ���X.���XID
            //���ʃR�[�h = ����.���ʃR�[�h
            
            Object l_bindVars[] = {
                new Long(l_lngAccountId),                 
                new Long(l_lngBranchId), 
                l_strOrderRequestNumber };  //���ʃR�[�h = ����.���ʃR�[�h 
            
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    AioOrderUnitRow.TYPE,
                    l_strWhereClause,
                    null,
                    l_bindVars);

            List l_lisAioOrderUnit = new ArrayList();
            int l_intNotFxCount = 0;
            boolean l_blnIsNotFxRecord = false;
            
            log.debug("l_lisRows.size() = " + l_lisRows.size());
            
            for (int i = 0; i < l_lisRows.size(); i++)
            {
                AioOrderUnitRow l_aioOrderUnitRow = (AioOrderUnitRow) l_lisRows.get(i);
                log.debug("l_aioOrderUnitRow = " + l_aioOrderUnitRow);
                log.debug("�����P��.�����J�e�S�� = " + l_aioOrderUnitRow.getOrderCateg());
                if (!OrderCategEnum.FX.equals(l_aioOrderUnitRow.getOrderCateg()))
                {
                    l_intNotFxCount = l_intNotFxCount + 1;
                    l_blnIsNotFxRecord = true;
                }
                OrderUnit l_orderUnit = 
                    this.getOrderUnit(l_aioOrderUnitRow.getOrderUnitId());
                AioOrderUnit l_aioOrderUnit = (AioOrderUnit) l_orderUnit;
                
                l_lisAioOrderUnit.add(l_aioOrderUnit);
            }
            log.debug("l_blnIsNotFxRecord = " + l_blnIsNotFxRecord);
            log.debug("l_intNotFxCount = " + l_intNotFxCount);
            if (l_blnIsNotFxRecord && l_intNotFxCount != 2)
            {
                log.debug("�ב֕ۏ؋��U�ֈȊO�̏ꍇ�́A�擾�����U�֒����P�ʂ�2�ł͂���܂���B");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�ב֕ۏ؋��U�ֈȊO�̏ꍇ�́A�擾�����U�֒����P�ʂ�2�ł͂���܂���B");
            }
            AioOrderUnit[] l_aioOrderUnits = new AioOrderUnit[l_lisRows.size()];
            l_lisAioOrderUnit.toArray(l_aioOrderUnits);
            
            log.exiting(STR_METHOD_NAME);
            return l_aioOrderUnits;
        }
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (validate�O���V�X�e����t�\)
     * <BR>
     * �Y������O���V�X�e������t���ԓ����ǂ������`�F�b�N����B <BR>
     * <BR>
     * �P�j�@@ThreadLocalSystemAttributesRegistry���A��t�������擾����B <BR>
     *     �@@ThreadLocalSystemAttributesRegistry.getAttribute( <BR>
     *          "xblocks.gtl.attributes.systemtimestamp") <BR>
     * <BR>
     * �Q�j��t�����̃J�����_���擾����B <BR>
     *     Calendar.getInstance() <BR>
     *     Calendar.setTime() <BR>
     * <BR>
     * �R�j�O���@@�֎�t���ԊO�i�j���j�e�[�u���`�F�b�N <BR>
     *    �O���@@�֎�t���ԊO�i�j���j�e�[�u������ȉ��̏����̃��R�[�h���擾����B <BR>
     * <BR>
     *  [��������] <BR>
     *    �O���@@�փR�[�h = ����.�V�X�e���R�[�h and <BR>
     *    (�� = Calendar.get("MONTH")�̖߂�l+1 or �� = "0" ) and <BR>
     *    �j�� = Calendar.get("DAY_OF_WEEK")�̖߂�l-1 and <BR>
     *    (�T�ԍ� = Calendar.get("DAY_OF_WEEK_IN_MONTH")�̖߂�l or �j���ԍ� = "0") and <BR>
     *    ��~���ԁiFROM) <= �J�����_�̎����iHHMMSS�j���� and <BR>
     *    ��~���ԁiTO) >= �J�����_�̎����iHHMMSS�j���� <BR>
     * <BR>
     *    �擾�ł����ꍇ�A��O��throw����B <BR>
     * <BR>
     * �S�j�O���@@�֎�t���ԊO�i���t�j�e�[�u���`�F�b�N <BR>
     *    �O���@@�֎�t���ԊO�i���t�j�e�[�u������ȉ��̏����̃��R�[�h���擾����B <BR>
     * <BR>
     *  [��������] <BR>
     *    �O���@@�փR�[�h = ����.�V�X�e���R�[�h and <BR>
     *    ���t = �J�����_�̓��t(MMDD)���� and <BR>
     *    ��~���ԁiFROM) <= �J�����_�̎����iHHMMSS�j���� and <BR>
     *    ��~���ԁiTO) >= �J�����_�̎����iHHMMSS�j���� <BR>
     * <BR>
     *    �擾�ł����ꍇ�A��O��throw����B <BR>
     * <BR>
     * @@param l_strSystemCode - (�V�X�e���R�[�h)
     * @@throws WEB3BaseException
     */
    public void validateOtherSystemAcceptPossible(String l_strSystemCode) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateFXSystemAcceptPossible(" +
                "String l_strSystemCode)";
        log.entering(STR_METHOD_NAME);       
        
        //�P�j�@@ThreadLocalSystemAttributesRegistry���A��t�������擾����B
        Timestamp l_dteAcceptDate = GtlUtils.getSystemTimestamp();
        
        //�Q�j��t�����̃J�����_���擾����B
        Calendar l_calendar = Calendar.getInstance();
        l_calendar.setTime(l_dteAcceptDate);
        
        //�J�����_�̎����iHHMMSS�j����
        Date l_datServiceTime = l_calendar.getTime();        
        String l_strServiceTime = 
            WEB3DateUtility.formatDate(l_datServiceTime, "HHmmss");
        
        List l_lisOtherOrgOutOfSrvWeekRows = null;
        List l_lisOtherOrgOutOfSrvDateRows = null;        
        
        try
        {
            //�R�j�O���@@�֎�t���ԊO�i�j���j�e�[�u���`�F�b�N 
            //�O���@@�֎�t���ԊO�i�j���j�e�[�u������ȉ��̏����̃��R�[�h���擾����B 
    
            //[��������] 
            //�O���@@�փR�[�h = ����.�V�X�e���R�[�h and 
            //(�� = Calendar.get("MONTH")�̖߂�l+1 or �� = "0" ) and 
            //�j�� = Calendar.get("DAY_OF_WEEK")�̖߂�l-1 and 
            //(�T�ԍ� = Calendar.get("DAY_OF_WEEK_IN_MONTH")�̖߂�l or �j���ԍ� = "0") and 
            //��~���ԁiFROM) <= �J�����_�̎����iHHMMSS�j���� and 
            //��~���ԁiTO) >= �J�����_�̎����iHHMMSS�j���� 
            
            String l_strWhereClause = "other_org_code = ? and (month = ? or month = ?)" +
            " and week_div = ? and (week_no = ? or week_no = ?)" + 
            " and suspension_start_time <= ? and suspension_end_time >= ?";  
                  
            //�� = Calendar.get("MONTH")�̖߂�l+1
            String l_strMonth = l_calendar.get(Calendar.MONTH) + 1 + "";
            
            //�j�� = Calendar.get("DAY_OF_WEEK")�̖߂�l-1
            String l_strDayOfWeek = l_calendar.get(Calendar.DAY_OF_WEEK) - 1 + "";
            
            //�j���ԍ� = Calendar.get("DAY_OF_WEEK_IN_MONTH")�̖߂�l
            String l_strWeekOfMonth = l_calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH) + "";
            
            log.debug("week_div = " + l_strDayOfWeek);
            log.debug("�J�����_�̎����iHHMMSS�j���� = " + l_strServiceTime);
            
            Object l_bindServiceWeekVars[] = {
                l_strSystemCode, 
                l_strMonth, 
                "0", 
                l_strDayOfWeek, 
                l_strWeekOfMonth, 
                "0", 
                l_strServiceTime, 
                l_strServiceTime };
            
            l_lisOtherOrgOutOfSrvWeekRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    OtherOrgOutOfSrvWeekRow.TYPE,
                    l_strWhereClause,
                    null,
                    l_bindServiceWeekVars);
            
            //�擾�ł����ꍇ�A��O��throw����B
            if (l_lisOtherOrgOutOfSrvWeekRows != null && 
                    l_lisOtherOrgOutOfSrvWeekRows.size() > 0)
            {
                log.debug("�O���V�X�e������t�\���ԊO�ł��B");
                log.exiting(STR_METHOD_NAME); 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01863,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�O���V�X�e������t�\���ԊO�ł��B");
                
            }
            
            //�S�j�O���@@�֎�t���ԊO�i���t�j�e�[�u���`�F�b�N
            //�O���@@�֎�t���ԊO�i���t�j�e�[�u������ȉ��̏����̃��R�[�h���擾����B 
            //[��������] 
            //�O���@@�փR�[�h = ����.�V�X�e���R�[�h and 
            //���t = �J�����_�̓��t(MMDD)���� and 
            //��~���ԁiFROM) <= �J�����_�̎����iHHMMSS�j���� and 
            //��~���ԁiTO) >= �J�����_�̎����iHHMMSS�j����
            
            l_strWhereClause = "other_org_code = ? and suspension_date = ? " +                
            "and suspension_start_time <= ? and suspension_end_time >= ?";  
            
            //���t = �J�����_�̓��t(MMDD)����
            String l_strSuspensionDate = WEB3DateUtility.formatDate(
                    GtlUtils.getSystemTimestamp(),"MMdd");
        
            log.debug("�O���@@�փR�[�h = " + l_strSystemCode);
            log.debug("���tMMdd = " + l_strSuspensionDate);
            
            Object l_bindServiceDateVars[] = {
                l_strSystemCode, 
                l_strSuspensionDate, 
                l_strServiceTime, 
                l_strServiceTime };
          
            l_lisOtherOrgOutOfSrvDateRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    OtherOrgOutOfSrvDateRow.TYPE,
                    l_strWhereClause,
                    null,
                    l_bindServiceDateVars);
          
            //�擾�ł����ꍇ�A��O��throw����B 
            if (l_lisOtherOrgOutOfSrvDateRows != null && 
                    l_lisOtherOrgOutOfSrvDateRows.size() > 0)
            {                
                log.debug("�O���V�X�e������t�\���ԊO�ł��B");
                log.exiting(STR_METHOD_NAME); 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01863,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�O���V�X�e������t�\���ԊO�ł��B");
            }
        }
        catch (DataQueryException l_ex)
        {
          log.error("__DataQueryException__", l_ex);
          throw new WEB3SystemLayerException(
              WEB3ErrorCatalog.SYSTEM_ERROR_80003,
              this.getClass().getName() + "." + STR_METHOD_NAME,
              l_ex.getMessage(),
              l_ex);
        }
        catch (DataNetworkException l_ex)
        {
          log.error("__DataNetworkException__", l_ex);
          throw new WEB3SystemLayerException(
              WEB3ErrorCatalog.SYSTEM_ERROR_80003,
              this.getClass().getName() + "." + STR_METHOD_NAME,
              l_ex.getMessage(),
              l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     *(validateFX�����J�݉\)
     * <BR>
     * �ڋq��FX��������J�݉\�ł��邩�`�F�b�N���s���B <BR>
     * <BR>
     * �i���o�������R���ʃ`�F�b�N.validateFX�����J�݉\()���\�b�h�ɈϏ�����B�j <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_compFxConditionParams - (��Е�FX�V�X�e������Params)
     * @@throws WEB3BaseException
     */
    public void validateFXAccOpenPossible(
        SubAccount l_subAccount,
        CompFxConditionParams l_compFxConditionParams)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateFXAccOpenPossible(" +
            "SubAccount, CompFxConditionParams)";
        log.entering(STR_METHOD_NAME);        
        
        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations = 
            (WEB3AioProductTypeOrderManagerReusableValidations)
                AioProductTypeOrderManagerReusableValidations.getInstance();
        l_reusableValidations.validateFXAccOpenPossible(
            l_subAccount, l_compFxConditionParams);

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validateFX�U�։\)
     * <BR>
     * �ڋq��FX�U�֎���\�ł��邩�`�F�b�N���s���B <BR>
     * <BR>
     * �i���o�������R���ʃ`�F�b�N.validateFX�U�։\()���\�b�h�ɈϏ�����B�j <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_strFXSystemCode - (FX�V�X�e���R�[�h)
     * @@throws WEB3BaseException
     */
    public void validateFXTransferPossible(
            SubAccount l_subAccount, 
            String l_strFXSystemCode) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateFXTransferPossible(" +
                "SubAccount l_subAccount, String l_strFXSystemCode)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations = 
            (WEB3AioProductTypeOrderManagerReusableValidations)
                AioProductTypeOrderManagerReusableValidations.getInstance();
        l_reusableValidations.validateFXTransferPossible(
                l_subAccount, l_strFXSystemCode);
        
        log.exiting(STR_METHOD_NAME);
    }
    
	/**
	 * (�U�֒������)
	 * <BR>
	 * �U�֒����̎���������s���B <BR>
	 * <BR>
	 * �V�[�P���X�} <BR>
	 * �u�i�U�֒����j�U�֒�������v �Q�� <BR>
	 * @@param l_strInstitutionCode - (�،���ЃR�[�h)
	 * @@param l_strBranchCode - (���X�R�[�h)
	 * @@param l_strAccountCode - (�ڋq�R�[�h)
	 * @@param l_strRequestNumber - (���ʃR�[�h)
	 * @@param l_strMrgTrnRequestNumber - (�M�p�U�֗p���ʃR�[�h)
	 * @@throws WEB3BaseException
	 */
	public void transferOrderCancel(
			String l_strInstitutionCode, 
			String l_strBranchCode, 
			String l_strAccountCode, 
			String l_strRequestNumber, 
			String l_strMrgTrnRequestNumber) 
		throws WEB3BaseException
	{
		String STR_METHOD_NAME = "transferOrderCancel(String,String,String,String,String)";
		log.entering(STR_METHOD_NAME);

		//�U�֒������(�،���ЃR�[�h, ���X�R�[�h, �ڋq�R�[�h, ���ʃR�[�h, �M�p�U�֗p���ʃR�[�h, �p�X���[�h)�ɈϏ�����B
		//���p�X���[�h = null �Ƃ���B
		transferOrderCancel(l_strInstitutionCode,l_strBranchCode,l_strAccountCode,l_strRequestNumber,l_strMrgTrnRequestNumber,null);

		log.exiting(STR_METHOD_NAME);
	}

    /**
     * (�U�֒������)
     * <BR>
     * �U�֒����̎���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�U�֒����j�U�֒�������v �Q�� <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)
     * @@param l_strBranchCode - (���X�R�[�h)
     * @@param l_strAccountCode - (�ڋq�R�[�h)
     * @@param l_strRequestNumber - (���ʃR�[�h)
     * @@param l_strMrgTrnRequestNumber - (�M�p�U�֗p���ʃR�[�h)
     * @@throws WEB3BaseException
     */
    public void transferOrderCancel(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strAccountCode, 
            String l_strRequestNumber, 
            String l_strMrgTrnRequestNumber,
            String l_strAdminPassword) 
        throws WEB3BaseException
    {
		String STR_METHOD_NAME = "transferOrderCancel(String,String,String,String,String,String)";
        log.entering(STR_METHOD_NAME);
        
        //1.1�ڋq�C���X�^���X���擾����B 
        //[����] 
        //�،���ЃR�[�h�F ����.�،���ЃR�[�h 
        //���X�R�[�h�F ����.���X�R�[�h 
        //�����R�[�h�F ����.�ڋq�R�[�h 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accMgr = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        //�ڋq�I�u�W�F�N�g���擾����    
        MainAccount l_mainAccount = 
            l_accMgr.getMainAccount(
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_strAccountCode);      
        
        //1.2 �����P�ʂ��擾����B 
        //[����] 
        //�،���ЃR�[�h�F ����.�،���ЃR�[�h 
        //���X�R�[�h�F ����.���X�R�[�h 
        //�ڋq�R�[�h�F ����.�ڋq�R�[�h 
        //���ʃR�[�h�F ����.���ʃR�[�h 
        //�⏕�����^�C�v�F 1�i�a��������j 
        
        log.debug("�،���ЃR�[�h = " + l_strInstitutionCode);
        log.debug("���X�R�[�h = " + l_strBranchCode);
        log.debug("�ڋq�R�[�h = " + l_strAccountCode);
        log.debug("���ʃR�[�h = " + l_strRequestNumber);
        
        AioOrderUnit l_aioOrderUnit = null;
        try
        {
            l_aioOrderUnit = this.getOrderUnit(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strRequestNumber,
                    SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
            //1.3 ����������e(1)�𐶐�����B 
            //[����] 
            //����ID�F �����P�ʁi�����P��(1)�j.����ID 
            CancelOrderSpec l_cancelOrderSpec1 = 
                new CancelOrderSpec(l_aioOrderUnit.getOrderId());
            
            //1.4 �o������X�V�C���^�Z�v�^�𐶐�����B 
            WEB3AioCashoutCancelUpdateInterceptor l_aioCashoutCancelUpdateInterceptor = 
                new WEB3AioCashoutCancelUpdateInterceptor();
            
            //1.5 �C���^�Z�v�^���Z�b�g����B
            this.setThreadLocalPersistenceEventInterceptor(
                    l_aioCashoutCancelUpdateInterceptor);

			//1.6 �⏕����(1)���擾����B 
			//[����] 
			//����ID�F �����P�ʁi�����P��(1)�j.����ID 
			//�⏕����ID�F �����P�ʁi�����P��(1)�j.�⏕����ID 
			log.debug("����ID = " + l_aioOrderUnit.getAccountId());
			log.debug("�⏕����ID = " + l_aioOrderUnit.getSubAccountId());
	            
			SubAccount l_subAccount1 = l_accMgr.getSubAccount(
					l_aioOrderUnit.getAccountId(),
					l_aioOrderUnit.getSubAccountId());

			//1.7 ���O�C���^�C�v��������A����p�X���[�h�ݒ�𑮐��l���擾����B
			//�T�[�r�X���擾
			OpLoginSecurityService l_securityService = (OpLoginSecurityService)
				Services.getService(OpLoginSecurityService.class);
			OpLoginAdminService l_admService = (OpLoginAdminService) Services.getService(
				OpLoginAdminService.class);
        
			//LoginInfo->LoginType->LoginTypeAttribute 
			LoginInfo l_loginInfo = l_securityService.getLoginInfo();
			Map l_mapAttributes = l_admService.getLoginTypeAttributes(l_loginInfo.getLoginTypeId());
        
			//����p�X���[�h�ݒ���擾����
			String l_strAttribute =
				(String) l_mapAttributes.get(
					WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV);

			String l_strPassword = null;
			// ���O�C���p�X���[�h�̏ꍇ
			if(WEB3TradingPwdEnvDef.DEFAULT.equals(l_strAttribute))
			{
				l_strPassword = l_strAdminPassword;
			}
			// ����p�X���[�h�̏ꍇ
			else if(WEB3TradingPwdEnvDef.USE_TRADING_PWD.equals(l_strAttribute))
			{
				WEB3Crypt l_web3Crypt = new WEB3Crypt();
				l_strPassword = l_web3Crypt.decrypt(l_mainAccount.getTradingPassword());
			}
			log.debug("����p�X���[�h�ݒ� = " + l_strAttribute);
            
            //1.8 ��������s����B 
            //[����] 
            //�⏕�����F�@@get�⏕����()�̖߂�l�i�⏕����(1)�j 
            //����������e�F�@@����������e�I�u�W�F�N�g�i����������e(1)�j 
			//�p�X���[�h�F �i�ȉ��̂Ƃ���j
			//  ����p�X���[�h�ݒ� == �hDEFAULT�h �̏ꍇ�A����.�p�X���[�h
			//  ����p�X���[�h�ݒ� == �h����p�X���[�h�g�p�h �̏ꍇ�A�ڋq.getTradingPassword()�̖߂�l��WEB3Crypt.decrypt()�ŕ�����������
            //isSkip�����R���F�@@true 
            this.submitCancelOrder(
                l_subAccount1, 
                l_cancelOrderSpec1, 
				l_strPassword,
                true);
            
            log.debug("��������s����(1) end");
            
            log.debug("����.�M�p�U�֗p���ʃR�[�h = " + l_strMrgTrnRequestNumber);
            //1.9 ����.�M�p�U�֗p���ʃR�[�h != null
            if (!WEB3StringTypeUtility.isEmpty(l_strMrgTrnRequestNumber))
            {
                log.debug("����.�M�p�U�֗p���ʃR�[�h != null");
                //1.9.1 �����P�ʂ��擾����B 
                //[����] 
                //�،���ЃR�[�h�F ����.�،���ЃR�[�h 
                //���X�R�[�h�F ����.���X�R�[�h 
                //�ڋq�R�[�h�F ����.�ڋq�R�[�h 
                //���ʃR�[�h�F ����.�M�p�U�֗p���ʃR�[�h 
                AioOrderUnit[] l_aioOrderUnits = 
                    this.getTransferOrderUnit(
                            l_strInstitutionCode,
                            l_strBranchCode,
                            l_strAccountCode,
                            l_strMrgTrnRequestNumber);
                
                //1.9.2 �擾���������P�ʂ̗v�f����Loop����
                for (int i = 0; i < l_aioOrderUnits.length; i++)
                {                    
                    long l_lngOrderId = l_aioOrderUnits[i].getOrderId();
                    long l_lngAccountId = l_aioOrderUnits[i].getAccountId();
                    long l_lngSubAccountId = l_aioOrderUnits[i].getSubAccountId();
                    
                    //1.9.2.1 ����������e(2)�𐶐�����B 
                    //[����] 
                    //����ID�F �����P�ʁiLoop�����̗v�f�j.����ID 
                    CancelOrderSpec l_cancelOrderSpec2 = new CancelOrderSpec(l_lngOrderId);
                    
                    //1.9.2.2 �⏕����(2)���擾����B 
                    //[����] 
                    //����ID�F �����P�ʁi�����P��(2)�j.����ID 
                    //�⏕����ID�F �����P�ʁi�����P��(2)�j.�⏕����ID 
                    SubAccount l_subAccount2 = l_accMgr.getSubAccount(
                            l_lngAccountId,
                            l_lngSubAccountId);

                    //1.9.2.3 �C���^�Z�v�^���Z�b�g����B
                    //[����] 
                    //arg0�F �o������X�V�C���^�Z�v�^ 
                    this.setThreadLocalPersistenceEventInterceptor(
                            l_aioCashoutCancelUpdateInterceptor);

                    //1.9.2.4 ��������s����B 
                    //[����] 
                    //�⏕�����F�@@get�⏕����()�̖߂�l�i�⏕����(2)�j 
                    //����������e�F�@@����������e�I�u�W�F�N�g�i����������e(2)�j 
					//�p�X���[�h�F �i�ȉ��̂Ƃ���j
					//  ����p�X���[�h�ݒ� == �hDEFAULT�h �̏ꍇ�A����.�p�X���[�h
					//  ����p�X���[�h�ݒ� == �h����p�X���[�h�g�p�h �̏ꍇ�A�ڋq.getTradingPassword()�̖߂�l��WEB3Crypt.decrypt()�ŕ�����������
                    //isSkip�����R���F�@@true 
                    this.submitCancelOrder(
                            l_subAccount2, 
                            l_cancelOrderSpec2, 
							l_strPassword,
                            true);
                    log.debug("��������s����(2) end");
                }
            }            
        }
        catch (NotFoundException l_ex)
        {
            log.debug("__NotFoundException__", l_ex);
            //��O���X���[����
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     *(validate�O�������J�݉\)
     * <BR>
     * �ڋq���O����������J�݉\�ł��邩�`�F�b�N���s���B <BR>
     * <BR>
     * �i���o�������R���ʃ`�F�b�N.validate�O�������J�݉\()���\�b�h�ɈϏ�����B�j<BR>
     * @@param l_subAccount - (�⏕����)
     * @@throws WEB3BaseException
     */
    public void validateFeqConAccOpenPossible(
            SubAccount l_subAccount)
        throws WEB3BaseException
    {
        
        String STR_METHOD_NAME = "validateFeqConAccountOpenPossible(" +
                "SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations = 
            (WEB3AioProductTypeOrderManagerReusableValidations)
                AioProductTypeOrderManagerReusableValidations.getInstance();
        
        l_reusableValidations.validateFeqConAccOpenPossible(l_subAccount);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�O���U�։\)
     * <BR>
     * �ڋq���O���U�֎���\�ł��邩�`�F�b�N���s���B <BR>
     * <BR>
     *�i���o�������R���ʃ`�F�b�N.validate�O���U�։\()���\�b�h�ɈϏ�����B�j<BR> 
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@throws WEB3BaseException
     */
    public void validateFeqConTransferPossible(
            SubAccount l_subAccount) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateFeqConTransferPossible(" +
                "SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations = 
            (WEB3AioProductTypeOrderManagerReusableValidations)
                AioProductTypeOrderManagerReusableValidations.getInstance();
        
        l_reusableValidations.validateFeqConTransferPossible(l_subAccount);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�O���U�֔�����)
     * <BR>
     * �O���U�֒���������ۂ̔��������擾����B<BR>
     * �P�j������ԊǗ�.get������()���R�[�������������擾���A�����(A)�Ƃ���B <BR>
     * <BR>
     * �Q�j(A)�̃J�����_���擾����B <BR>
     * <BR>
     * �R�j�ȉ���2�̃e�[�u������������B <BR>
     * <BR>
     * (1)�O���@@�֎�t���ԊO�i�j���j�e�[�u���`�F�b�N <BR>
     * �O���@@�֎�t���ԊO�i�j���j�e�[�u������ȉ��̏����̃��R�[�h���擾����B<BR> 
     * <BR>
     * [��������] <BR>
     * �O���@@�փR�[�h = �O���@@�փR�[�h.�hUWG�h and <BR>
     * (�� = Calendar.get("MONTH")�̖߂�l+1 or �� = "0" ) and <BR>
     * �j�� = Calendar.get("DAY_OF_WEEK")�̖߂�l-1 and <BR>
     * (�T�ԍ� = Calendar.get("DAY_OF_WEEK_IN_MONTH")�̖߂�l or �j���ԍ� = "0") <BR>
     * <BR>
     * (2)�O���@@�֎�t���ԊO�i���t�j�e�[�u���`�F�b�N <BR>
     * �O���@@�֎�t���ԊO�i���t�j�e�[�u������ȉ��̏����̃��R�[�h���擾����B <BR>
     * <BR>
     * [��������] <BR>
     * �O���@@�փR�[�h = �O���@@�փR�[�h.�hUWG�h and <BR>
     * ���t = �J�����_�̓��t(MMDD)���� <BR>
     * <BR>
     * �S�j�@@�ƇA�̃e�[�u���Ń��R�[�h���擾���ꂽ�ꍇ��(A)�̗��c�Ɠ����擾���A <BR>
     * �R�j����̏������s���B <BR>
     * <BR>
     * �T�j���R�[�h���擾����Ȃ������ꍇ�́A(A)�̓��t��ԋp����B <BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public Date getFeqConTransferBizDate() 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getFEqConTransferBizDate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j������ԊǗ�.get������()���R�[�������������擾���A�����(A)�Ƃ���B 
        Date l_datBizDateA = 
            WEB3GentradeTradingTimeManagement.getOrderBizDate();

        log.debug("������ԊǗ�.get������() = " + 
                WEB3DateUtility.formatDate(l_datBizDateA,"yyyyMMdd"));
        
        //�Q�j(A)�̃J�����_���擾����B
        Calendar l_calendar = Calendar.getInstance();
        l_calendar.setTime(l_datBizDateA);
        Date l_datBizDateTimeA = l_calendar.getTime();
        
        log.debug("(A)�̃J�����_���擾 = " + 
                WEB3DateUtility.formatDate(l_datBizDateTimeA,"yyyyMMdd"));
        
        Date l_datTransferDate = null;
        List l_lisOtherOrgOutOfSrvWeekRows = null;
        List l_lisOtherOrgOutOfSrvDateRows = null;
        
        //�R�j�ȉ���2�̃e�[�u������������B 
        try
        {
            //(1)�O���@@�֎�t���ԊO�i�j���j�e�[�u���`�F�b�N 
            //�O���@@�֎�t���ԊO�i�j���j�e�[�u������ȉ��̏����̃��R�[�h���擾����B 
            //[��������] 
            //�O���@@�փR�[�h = �O���@@�փR�[�h.�hUWG�h and 
            //(�� = Calendar.get("MONTH")�̖߂�l+1 or �� = "0" ) and 
            //�j�� = Calendar.get("DAY_OF_WEEK")�̖߂�l-1 and 
            //(�T�ԍ� = Calendar.get("DAY_OF_WEEK_IN_MONTH")�̖߂�l or �j���ԍ� = "0") 
            String l_strWhereClause = "other_org_code = ? and (month = ? or month = ?)" +
            " and week_div = ? and (week_no = ? or week_no = ?)" ;
                  
            //�� = Calendar.get("MONTH")�̖߂�l+1
            String l_strMonth = l_calendar.get(Calendar.MONTH) + 1 + "";
            log.debug("�� = " + l_strMonth);
            
            //�j�� = Calendar.get("DAY_OF_WEEK")�̖߂�l-1
            String l_strDayOfWeek = l_calendar.get(Calendar.DAY_OF_WEEK) - 1 + "";
            log.debug("�j�� = " + l_strDayOfWeek);
            
            //�j���ԍ� = Calendar.get("DAY_OF_WEEK_IN_MONTH")�̖߂�l
            String l_strWeekOfMonth = l_calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH) + "";
            log.debug("�j���ԍ� = " + l_strWeekOfMonth);
                       
            Object l_bindServiceWeekVars[] = {
                WEB3OtherOrgCodeDef.UWG, 
                l_strMonth, 
                "0", 
                l_strDayOfWeek, 
                l_strWeekOfMonth, 
                "0" };
            
            l_lisOtherOrgOutOfSrvWeekRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    OtherOrgOutOfSrvWeekRow.TYPE,
                    l_strWhereClause,
                    null,
                    l_bindServiceWeekVars);
            
            log.debug("l_lisOtherOrgOutOfSrvWeekRows.size() = " + 
                    l_lisOtherOrgOutOfSrvWeekRows.size());
            
            //(2)�O���@@�֎�t���ԊO�i���t�j�e�[�u���`�F�b�N 
            //�O���@@�֎�t���ԊO�i���t�j�e�[�u������ȉ��̏����̃��R�[�h���擾����B 
            //[��������] 
            //�O���@@�փR�[�h = �O���@@�փR�[�h.�hUWG�h and 
            //���t = �J�����_�̓��t(MMDD)���� 
            l_strWhereClause = "other_org_code = ? and suspension_date = ?";  
            
            //���t = �J�����_�̓��t(MMDD)����
            String l_strSuspensionDate = WEB3DateUtility.formatDate(
                    l_datBizDateTimeA,"MMdd");
            
            log.debug("���t = " + l_strSuspensionDate);
            
            Object l_bindServiceDateVars[] = {
                WEB3OtherOrgCodeDef.UWG, 
                l_strSuspensionDate };
          
            l_lisOtherOrgOutOfSrvDateRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    OtherOrgOutOfSrvDateRow.TYPE, 
                    l_strWhereClause, 
                    null, 
                    l_bindServiceDateVars);
            
            log.debug("l_lisOtherOrgOutOfSrvDateRows.size() = " + 
                    l_lisOtherOrgOutOfSrvDateRows.size());
            
            //�S�j(1)��(2)�̃e�[�u���Ń��R�[�h���擾���ꂽ�ꍇ��(A)�̗��c�Ɠ����擾���A 
            //    �R�j����̏������s���B 
            if ((l_lisOtherOrgOutOfSrvWeekRows != null && 
                l_lisOtherOrgOutOfSrvWeekRows.size() > 0) && 
                (l_lisOtherOrgOutOfSrvDateRows != null && 
                l_lisOtherOrgOutOfSrvDateRows.size() > 0))
            {
                log.debug("(1)��(2)�̃e�[�u���Ń��R�[�h���擾���ꂽ�ꍇ");
                l_datTransferDate = new WEB3GentradeBizDate(
                        new Timestamp(l_datBizDateA.getTime())).roll(1);                
            }
            //�T�j���R�[�h���擾����Ȃ������ꍇ�́A(A)�̓��t��ԋp����B 
            else
            {
                log.debug("���R�[�h���擾����Ȃ������ꍇ");
                l_datTransferDate = l_datBizDateA;
            }
        }
        catch (DataQueryException l_ex)
        {
          log.error("__DataQueryException__", l_ex);
          throw new WEB3SystemLayerException(
              WEB3ErrorCatalog.SYSTEM_ERROR_80003,
              this.getClass().getName() + "." + STR_METHOD_NAME,
              l_ex.getMessage(),
              l_ex);
        }
        catch (DataNetworkException l_ex)
        {
          log.error("__DataNetworkException__", l_ex);
          throw new WEB3SystemLayerException(
              WEB3ErrorCatalog.SYSTEM_ERROR_80003,
              this.getClass().getName() + "." + STR_METHOD_NAME,
              l_ex.getMessage(),
              l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        log.debug("return : " + 
                WEB3DateUtility.formatDate(l_datTransferDate,"yyyyMMdd"));
        return l_datTransferDate;
    }
    
    /**
     * (validate�O������U��)
     * <BR>
     * �O���U�ւ�����̏ꍇ�̃`�F�b�N���s���B <BR>
     * <BR>
     * �i���o�������R���ʃ`�F�b�N.validate�O������U��()���\�b�h�ɈϏ�����B�j <BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_subAccount - (�U�֋��z)
     * @@throws WEB3BaseException
     */
    public void validateFeqConFirstTransfer(
            SubAccount l_subAccount, double l_dblTransferAmount) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateFeqConFirstTransfer(" +
                "SubAccount l_subAccount, double l_dblTransferAmount)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations = 
            (WEB3AioProductTypeOrderManagerReusableValidations)
                AioProductTypeOrderManagerReusableValidations.getInstance();
        l_reusableValidations.validateFeqConFirstTransfer(
                l_subAccount, l_dblTransferAmount);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�O���U�֎��)
     * <BR>
     * ����ΏۂƂȂ��Ă���O���U�֒���������\���ǂ����̃`�F�b�N���s���B <BR>
     * <BR>
     * �P�jUWG�U�֏󋵂̎擾 <BR>
     * �O���U�֘A�g�f�[�^����T�[�r�XImpl.getUWG�U�֏�()���R�[������B <BR>
     * <BR>
     * [����] <BR>
     * �،���ЃR�[�h�F ����.�⏕����.�،���ЃR�[�h <BR>
     * ���X�R�[�h�F ����.�⏕����.get����X.getBranchCode() <BR>
     * ���ʃR�[�h�F ����.���ʃR�[�h <BR>
     * <BR>
     * �Q�jUWG�U�֏�Params.����U�փt���O==�h����U�ցh �̏ꍇ�A�ȉ��̏������s���B <BR>
     * <BR>
     * �Q�|�P�j�ȉ��̏�����UWG�U�֏󋵃e�[�u������������B <BR>
     * <BR>
     * [��������] <BR>
     * �،���ЃR�[�h�F ����.�⏕����.�،���ЃR�[�h <BR>
     * ���X�R�[�h�F ����.�⏕����.get����X.getBranchCode() <BR>
     * �ڋq�R�[�h�F�@@����.�⏕����.getMainAccount().getAccountCode() <BR>
     * �U�֏󋵋敪�F �h���ϒ��h <BR>
     * ����M�敪�F �h�����M�h <BR>
     * ����U�փt���O�F �h���̑��h <BR>
     * <BR>
     * �Q�|�Q�j�����̌��ʂ�0���łȂ������ꍇ�́A����s�Ƃ������Ƃŗ�O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01940<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_strRequestNumber - ���ʃR�[�h
     * @@throws WEB3BaseException
     */
    public void validateFeqConTransferCancel(
            SubAccount l_subAccount, String l_strRequestNumber) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateFeqConTransferCancel(" +
                "SubAccount l_subAccount, String l_strRequestNumber)";
        log.entering(STR_METHOD_NAME);       
        
        if (l_subAccount == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�jUWG�U�֏󋵂̎擾 
        //�O���U�֘A�g�f�[�^����T�[�r�XImpl.getUWG�U�֏�()���R�[������B 
        //[����] 
        //�،���ЃR�[�h�F ����.�⏕����.�،���ЃR�[�h 
        //���X�R�[�h�F ����.�⏕����.get����X.getBranchCode() 
        //���ʃR�[�h�F ����.���ʃR�[�h 
        WEB3FEqConTransferDataControlService l_feqConTransferDataControlService = 
            (WEB3FEqConTransferDataControlService) Services.getService(
                WEB3FEqConTransferDataControlService.class);
        
        UwgTransferStatusParams l_uwgTransferStatusParams = 
            l_feqConTransferDataControlService.getUwgTransferStatus(
                l_subAccount.getInstitution().getInstitutionCode(), 
                l_subAccount.getMainAccount().getBranch().getBranchCode(), 
                l_strRequestNumber);
        
        //�Q�jUWG�U�֏�Params.����U�փt���O==�h����U�ցh �̏ꍇ�A�ȉ��̏������s���B 
        //�Q�|�P�j�ȉ��̏�����UWG�U�֏󋵃e�[�u������������B 
        //[��������] 
        //�،���ЃR�[�h�F ����.�⏕����.�،���ЃR�[�h 
        //���X�R�[�h�F ����.�⏕����.get����X.getBranchCode() 
        //�ڋq�R�[�h�F�@@����.�⏕����.getMainAccount().getAccountCode() 
        //�U�֏󋵋敪�F �h���ϒ��h 
        //����M�敪�F �h�����M�h 
        //����U�փt���O�F �h���̑��h 
        if (WEB3FeqFirstTransferFlagDef.TRANSFERRED.equals(
                l_uwgTransferStatusParams.getFirstTransferDiv()))
        {
            List l_lisRows = null;
            
            try
            {            
                String l_strWhereClause = 
                    " institution_code = ? and branch_code = ?" +
                    " and account_code = ? and transfer_status_div = ?" +
                    " and send_rcv_div = ? and first_transfer_div = ?";
                      
                Object l_bindVars[] = {
                    l_subAccount.getInstitution().getInstitutionCode(),                 
                    l_subAccount.getMainAccount().getBranch().getBranchCode(), 
                    l_subAccount.getMainAccount().getAccountCode(), 
                    WEB3TransferStatusDivDef.PROCESSING, 
                    WEB3SendRcvDivDef.NOT_SEND, 
                    WEB3FeqFirstTransferFlagDef.NOT_TRANSFER }; 
                
                l_lisRows =
                    Processors.getDefaultProcessor().doFindAllQuery(
                        UwgTransferStatusRow.TYPE,
                        l_strWhereClause,
                        null,
                        l_bindVars);
                
                //�Q�|�Q�j�����̌��ʂ�0���łȂ������ꍇ�́A����s�Ƃ������Ƃŗ�O���X���[����B 
                if (l_lisRows.size() > 0)
                {
                    log.debug("��������UWG�U�֏󋵂����݂��Ă��܂��B");
                    log.exiting(STR_METHOD_NAME); 
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01940,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "��������UWG�U�֏󋵂����݂��Ă��܂��B");
                }
                
            }
            catch (DataException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     *(validateFX�����J��)<BR>
     * <BR>
     * �ڋq��FX��������J�ݍςł��邩�`�F�b�N���s���B  
     * 
     * �i���o�������R���ʃ`�F�b�N.validateFX�����J��()���\�b�h�ɈϏ�����B�j 
     * @@param l_subAccount - (�⏕����)
     * @@param l_strFXSystemCode - (FX�V�X�e���R�[�h)
     * @@throws WEB3BaseException
     */
    public void validateFXAccOpen(
            SubAccount l_subAccount, 
            String l_strFXSystemCode)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateFXAccOpen(" +
                "SubAccount, String)";
        log.entering(STR_METHOD_NAME);        
        
        //�i���o�������R���ʃ`�F�b�N.validateFX�����J��()���\�b�h�ɈϏ�����B�j
        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations = 
            (WEB3AioProductTypeOrderManagerReusableValidations)
                AioProductTypeOrderManagerReusableValidations.getInstance();
        
        l_reusableValidations.validateFXAccOpen(
            l_subAccount, l_strFXSystemCode);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (is�ۏ؋��U��)<BR>
     * �ۏ؋��U�ւɂ����āA�U�֒������쐬����K�v��<BR> 
     * ���邩���肷��B<BR> 
     * <BR>
     * �쐬����K�v������ꍇ�́A�htrue�h���A<BR> 
     * �쐬����K�v���Ȃ��ꍇ�́A�hfalse�h��ԋp����B<BR> 
     * <BR>
     * �P�j�������̃`�F�b�N<BR> 
     * �����������c�Ɠ��̎��ԑт��ǂ����̔�����s���B<BR> 
     * ����.�Ɩ����t �� ����.�������̏ꍇ�A<BR>
     * �ȉ��̏������s���B<BR>
     * ����.�Ɩ����t �� ����.�������łȂ��ꍇ�A<BR>
     * "true"��ԋp����B<BR>�@@ 
     * <BR>
     * �Q�j�v���Z�X�Ǘ�Params�̎擾<BR> 
     * �v���Z�X�Ǘ��e�[�u�����ȉ��̏����Ō������s���B<BR>�@@ 
     * <BR>
     * [�����̐ݒ�]<BR> 
     * �v���Z�XID�F�@@�h0005�h(�ۏ؋������U�֏I��)<BR> 
     * �،���ЃR�[�h�F�@@����.�⏕����.�،���ЃR�[�h<BR>
     * ���X�R�[�h�F�@@����.�⏕����.get����X.���X�R�[�h<BR>
     * �����敪�F�h1�h�i�����ρj<BR>
     * <BR>
     * �R�j�v���Z�X�Ǘ�Params���擾�ł��Ȃ��i�Y���f�[�^�Ȃ��j�ꍇ�A<BR> 
     * "false"��ԋp����B<BR>
     * <BR>
     * �S�j�v���Z�X�Ǘ�Params���擾�ł����i�Y���f�[�^����j�ꍇ�A<BR> 
     * "true"��ԋp����B<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_datBizDate - (�Ɩ����t)
     * @@param l_datOrderBizDate - (������)
     * @@return boolean
     * @@throws webbroker3.common.WEB3BaseException
     */
    public boolean isDepositTransfer(
        SubAccount l_subAccount, 
        Date l_datBizDate, 
        Date l_datOrderBizdate)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "isDepositTransfer(" + 
            "SubAccount l_subAccount, Date l_datBizDate, Date l_datOrderBizDate)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_datBizDate == null || l_datOrderBizdate == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j�������̃`�F�b�N
        //����.������ > ����.�Ɩ����t�̏ꍇ�A�ȉ��̏������s���B
        if (WEB3DateUtility.compareToDay(l_datOrderBizdate, l_datBizDate) > 0)
        {
            log.debug("�����P��.������ > �Ɩ����t�̏ꍇ");
            
            //�Q�j�v���Z�X�Ǘ��e�[�u���̃��R�[�h�擾
            //   �v���Z�X�Ǘ��e�[�u�����ȉ��̏����Ō������s���B�@@
            // [�����̐ݒ�] 
            // �v���Z�XID�F�@@�h0005�h(�ۏ؋������U�֏I��) 
            // �،���ЃR�[�h�F�@@����.�⏕����.�،���ЃR�[�h 
            // ���X�R�[�h�F�@@����.�⏕����.get����X.���X�R�[�h
            // �����敪�F�h1�h�i�����ρj 

            List l_lisRows = null;
            try
            {
                String l_strWhereClause = 
                    " process_id = ? and institution_code = ?" +
                    " and branch_code = ? and status = ?";
            
                Object l_bindVars[] = {
                    WEB3AioProcessManagementIdDivDef.DEPOSIT_TRANSFER_END, 
                    l_subAccount.getInstitution().getInstitutionCode(),                 
                    l_subAccount.getMainAccount().getBranch().getBranchCode(), 
                    WEB3ProcDivDef.PROCESSED};
             
                l_lisRows =
                    Processors.getDefaultProcessor().doFindAllQuery(
                        ProcessManagementRow.TYPE,
                        l_strWhereClause,
                        null,
                        l_bindVars);
            
            
                //�R�j�v���Z�X�Ǘ��e�[�u���̃��R�[�h���擾�ł��Ȃ��i�Y���f�[�^�Ȃ��j�ꍇ�A
                //  "false"��ԋp����B 
                if (l_lisRows.size() == 0)
                {
                    log.debug("�v���Z�X�Ǘ��e�[�u���ɊY���f�[�^������܂���B");
                    log.exiting(STR_METHOD_NAME); 
                    return false;
                }
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�T�[�o�Ƃ̒ʐM�Ɏ��s����", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
            
        //�S�j����.������ > ����.�Ɩ����t�łȂ��ꍇ�A
        //    �v���Z�X�Ǘ��e�[�u���̃��R�[�h���擾�ł����i�Y���f�[�^����j�ꍇ�A
        //    "true"��ԋp����B 
        log.exiting(STR_METHOD_NAME); 
        return true;
    }

    /**
     * (validate���Z��)<BR>
     * <BR>
     * ���Z�҃`�F�b�N���s���B<BR>
     * �P�j�p�����[�^.�⏕��������擾�����ڋq�I�u�W�F�N�g.���Z�^�񋏏Z�敪<BR>
     * �@@�@@���u1�F���ʔ񋏏Z�ҁv or �u2�F�񋏏Z�ҁv�̏ꍇ�́A��O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_02344<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@throws WEB3BaseException
     */
    public void validateResident(SubAccount l_subAccount) throws WEB3BaseException
    {
        String STR_METHOD_NAME = " validateResident(WEB3GentradeSubAccount l_subAccount)";
        log.debug(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug("�⏕���� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�⏕���� = null�B");
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountMgr = (WEB3GentradeAccountManager) l_finApp.getAccountManager();

        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            l_mainAccount = (WEB3GentradeMainAccount)
                l_accountMgr.getMainAccount(l_subAccount.getAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�ڋq�e�[�u���ɊY������f�[�^������܂���B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        MainAccountRow l_row = (MainAccountRow) l_mainAccount.getDataSourceObject();

        //�P�j�p�����[�^.�⏕��������擾�����ڋq�I�u�W�F�N�g.���Z�^�񋏏Z�敪
        //���u1�F���ʔ񋏏Z�ҁv or �u2�F�񋏏Z�ҁv�̏ꍇ�́A��O���X���[����B
        if (WEB3ResidentDef.SPE_NON_RESIDENT.equals(l_row.getResident()) ||
            WEB3ResidentDef.NON_RESIDENT.equals(l_row.getResident()))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02708,
                this.getClass().getName() + STR_METHOD_NAME,
                "�񋏏Z�҂͐\���ނ��Ƃ��ł��܂���B");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validateSL�ԍϏd������)<BR>
     * �����̎�n���Ɠ������ɂ��łɏ،��S�ۃ��[���ԍϒ������o�ĂȂ����ǂ�����<BR>
     * �`�F�b�N����B<BR>
     * <BR>
     * ���d����������Ȃ��̂́A�ʏ�̏،��S�ۃ��[���ԍϒ����̂݁B<BR>
     * <BR>
     * �P�j�ȉ��̏����ɂĒ����P�ʃe�[�u���̃��R�[�h���擾����B<BR>
     * <BR>
     * [��������]<BR>
     * ����ID = ����.�⏕����.getAccountId()�̖߂�l<BR>
     * �⏕����ID = ����.�⏕����.getSubAccountId()�̖߂�l<BR>
     * ������� = 1020�i�U�֒����i�a���������I���b�N�X�N���W�b�g�j�j<BR>
     * ������� = 1�i��t�ρj or 2�i�������j or 3�i�����ρj<BR>
     * �����L����� = 1�i�I�[�v���j<BR>
     * ��n�� = ����.��n��<BR>
     * �o���\���敪 = null<BR>
     * <BR>
     * �Q�j�擾�������R�[�h���� > 0 �̏ꍇ�́A��O���X���[����B<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_datDeliveryDate - (��n��)<BR>
     * �����̎�n��<BR>
     * @@throws WEB3BaseException
     */
    public void validateSLRepayDuplicateOrder(SubAccount l_subAccount, Date l_datDeliveryDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateSLRepayDuplicateOrder(SubAccount, Date)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_datDeliveryDate == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        try
        {
            //�P�j�ȉ��̏����ɂĒ����P�ʃe�[�u���̃��R�[�h���擾����
            long l_lngAccountId = l_subAccount.getAccountId();
            long l_lngSubAccountId = l_subAccount.getSubAccountId();

            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" account_id = ? ");
            l_sbWhere.append(" and sub_account_id = ? ");
            l_sbWhere.append(" and order_type = ? ");
            l_sbWhere.append(" and (order_status = ? or order_status = ? or order_status = ? ) ");
            l_sbWhere.append(" and order_open_status = ? ");
            l_sbWhere.append(" and delivery_date = ? ");
            l_sbWhere.append(" and payment_application_div is null");

            Object[] l_sqlValues = new Object[]{
                new Long(l_lngAccountId),
                new Long(l_lngSubAccountId),
                OrderTypeEnum.TO_ORIX_CREDIT.intValue() + "",
                OrderStatusEnum.ACCEPTED.intValue() + "",
                OrderStatusEnum.ORDERING.intValue() + "",
                OrderStatusEnum.ORDERED.intValue() + "",
                OrderOpenStatusEnum.OPEN.intValue() + "",
                l_datDeliveryDate};

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_listFindAllQuerys = l_queryProcessor.doFindAllQuery(
                AioOrderUnitRow.TYPE,
                l_sbWhere.toString(),
                l_sqlValues);

            //�Q�j�擾�������R�[�h���� > 0 �̏ꍇ�́A��O���X���[����
            if(l_listFindAllQuerys.size() > 0)
            {
                log.debug("�w�肵�����U���\����Ɠ����U�����̏o���\�������łɓo�^����Ă��܂��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00757,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�w�肵�����U���\����Ɠ����U�����̏o���\�������łɓo�^����Ă��܂��B" );                
            }
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validateCFD�U�։\)<BR>
     * �ڋq��FX�U�֎���\�ł��邩�`�F�b�N���s���B<BR>
     * <BR>
     * �i���o�������R���ʃ`�F�b�N.validateCFD�U�։\()���\�b�h�ɈϏ�����B�j<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_strFxSystemCode - (FX�V�X�e���R�[�h)<BR>
     * FX�V�X�e���R�[�h<BR>
     * @@throws WEB3BaseException
     */
    public void validateCFDChangePoss(
        SubAccount l_subAccount, String l_strFxSystemCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateCFDChangePoss(SubAccount, String)";
        log.entering(STR_METHOD_NAME);

        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations =
            (WEB3AioProductTypeOrderManagerReusableValidations)
                AioProductTypeOrderManagerReusableValidations.getInstance();

        //���o�������R���ʃ`�F�b�N.validateCFD�U�։\()���\�b�h�ɈϏ�����B
        l_reusableValidations.validateCFDChangePoss(
            l_subAccount, l_strFxSystemCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�U�֎���\)<BR>
     * �ڋq���U�֎���\�ł��邩�`�F�b�N���s���B<BR>
     * <BR>
     * �i���o�������R���ʃ`�F�b�N.validate�U�֎���\()���\�b�h�ɈϏ�����B�j<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_compFxConditionParams - (��Е�FX�V�X�e������Params)<BR>
     * ��Е�FX�V�X�e������Params<BR>
     * @@throws WEB3BaseException
     */
    public void validateTransferTradePossible(
        SubAccount l_subAccount,
        CompFxConditionParams l_compFxConditionParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateTransferTradePossible(SubAccount, CompFxConditionParams)";
        log.entering(STR_METHOD_NAME);

        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations =
            (WEB3AioProductTypeOrderManagerReusableValidations)
                AioProductTypeOrderManagerReusableValidations.getInstance();

        //���o�������R���ʃ`�F�b�N.validate�U�֎���\()���\�b�h�ɈϏ�����
        l_reusableValidations.validateTransferTradePossible(
            l_subAccount,
            l_compFxConditionParams);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�؋����U�֎w���)<BR>
     * �؋����U�ւ̎w������擾����B<BR>
     * <BR>
     * �P�j�؋����U�ւ̎w������擾����B<BR>
     * <BR>
     * �P�|�P�j�Ɩ����t(*1)�ƈ���.����������v����ꍇ<BR>
     * <BR>
     * �@@0��ԋp����B<BR>
     * <BR>
     * �P�|�Q�j���̑��i���������j�̏ꍇ<BR>
     * <BR>
     * �@@1��ԋp����B<BR>
     * <BR>
     * (*1)GtlUtils.getTradingSystem().getBizDate()<BR>
     * @@param l_datBizDate - (������)<BR>
     * ������<BR>
     * @@return int
     */
    public int getMarginTransferDesignatedDate(Date l_datBizDate)
    {
        final String STR_METHOD_NAME = "getMarginTransferDesignatedDate(Date)";
        log.entering(STR_METHOD_NAME);

        //�Ɩ����t(*1)�ƈ���.����������v����ꍇ
        //0��ԋp����B
        int l_intDesignatedDate = 0;

        //���̑��i���������j�̏ꍇ
        //1��ԋp����B
        if (WEB3DateUtility.compare(GtlUtils.getTradingSystem().getBizDate(),
            l_datBizDate) != 0)
        {
        	l_intDesignatedDate = 1;
        }

        log.exiting(STR_METHOD_NAME);
        return l_intDesignatedDate;
    }
}
@
