head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeOrderValidator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����`�F�b�N(WEB3GentradeOrderValidator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/14 羐� (���u) �V�K�쐬
Revesion History : 2007/02/28 �h�C (���u) �d�l�ύX�E���f��No.222��Ή�
Revesion History : 2007/11/12 �h�C (���u) �d�l�ύX�E���f��No.283��Ή�
Revesion History : 2007/11/23 �h�C (���u) �d�l�ύX�E���f��No.292��Ή�
Revesion History : 2008/01/23 �h�C (���u) �d�l�ύX�E���f��No.304��Ή�
*/
package webbroker3.gentrade;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.IllegalSessionStateException;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.CommonOrderValidatorImpl;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchLockDef;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3MngLockDef;
import webbroker3.common.define.WEB3OrderPermissionDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3ThreadLocalSystemAttributePathDef;
import webbroker3.common.define.WEB3TradingPwdEnvDef;
import webbroker3.common.define.WEB3YellowCustomerDef;
import webbroker3.gentrade.define.WEB3GentradeProcessFlagDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * �����`�F�b�N<BR>
 * �iCommonOrderValidatorImpl�̊g���N���X�j<BR>
 * WEB3EquityOrderValidator��WEB3OrderValidator <BR>
 * <BR> 
 * @@author �����@@���F(SRA)
 * @@version 1.0
 */
public class WEB3GentradeOrderValidator extends CommonOrderValidatorImpl
{

    /** 
     * ���O���[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeOrderValidator.class);

    /**
     * �R���X�g���N�^�B<BR>
     * @@roseuid 409B412D0290
     */
    public WEB3GentradeOrderValidator()
    {

    }

    /**
     * (validate����p�X���[�h) <BR>
     * ����p�X���[�h�����������̃`�F�b�N���s���B<BR>
     * �ivalidateTradingPassword�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@�����ΏۊO���� <BR>
     * �@@���O�C����񂪎擾�ł��Ȃ��ꍇ�i�����菈������̃R�[���̏ꍇ�j�A���������ɏ������I������B <BR>
     * <BR>
     * �Q�j�@@�Z�b�V�������擾���������o�H�敪==�hIVR�h�̏ꍇ�A���������ɏ������I������B <BR>
     *  <BR>
     * �R�j�@@�㗝���͎҂̎擾 <BR>
     * �p�����[�^.�㗝���͎҂�null�̏ꍇ�A�㗝���͎҂̎擾���s���B<BR>
     *  <BR>
     * �V�[�P���X�} <BR>
     * �u�ivalidate����p�X���[�h�j�㗝���͎Ҏ擾�v�Q�ƁB <BR>
     * �S�j�@@�㗝���͂̏ꍇ�̃`�F�b�N <BR>
     * �i�p�����[�^.�㗝���͎� == null�j�̏ꍇ�A���O�C�������<BR>
     * ���҃I�u�W�F�N�g�̎擾���s���B���҃I�u�W�F�N�g���擾�ł����ꍇ�A<BR>
     * �㗝���͂ł���Ɣ��f���A�������I������B<BR>
     * �i�㗝���͂̏ꍇ�́A����p�X���[�h����͂��Ȃ��j<BR>
     *  <BR>
     * �T�j�@@�ڋq���͂̏ꍇ�̃`�F�b�N <BR>
     * this.validate����p�X���[�h(�ڋq, String)���R�[�����A<BR>
     * �������ʂ�ԋp����B<BR>
     * <BR> 
     * @@param l_proxyInputPerson - (�㗝���͎�)<BR>
     *    �g���[�_�̑㗝���͂̏ꍇ�w�肷��B<BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_strTradingPassword - (���̓p�X���[�h)
     * @@return �`�F�b�N����
     * @@roseuid 4042EC7B016D
     */
    public OrderValidationResult validateTradingPassword(
        Trader l_proxyInputPerson,
        SubAccount l_subAccount,
        String l_strTradingPassword)
    {
        final String STR_METHOD_NAME =
            "validateTradingPassword(Trader, SubAccount, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@���O�C����񂪎擾�ł��Ȃ��ꍇ�i�����菈������̃R�[���̏ꍇ�j�A
        // ���������ɏ������I������B
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        long l_loginId = 0L;
        try
        {
            l_loginId = l_opLoginSec.getLoginId();
        }
        catch (IllegalSessionStateException Isse)
        {
            log.info("���O�C����񂪎擾�ł��Ȃ��ꍇ" +
                "�i�����菈������̃R�[���̏ꍇ�j�A���������ɏ������I������B");
            return OrderValidationResult.VALIDATION_OK_RESULT;          
        }

        //�Q�j�@@�Z�b�V�������擾���������o�H�敪==�hIVR�h�̏ꍇ�A���������ɏ������I������B
        if (WEB3OrderRootDivDef.IVR.equals(
            l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV)))
        {
            return OrderValidationResult.VALIDATION_OK_RESULT;
        }

        //�R�j�@@�㗝���͎҂̎擾
        //�@@�p�����[�^.�㗝���͎҂�null�̏ꍇ�A�㗝���͎҂̎擾���s���B
        if (l_proxyInputPerson == null)
        {
            try
            {
                l_proxyInputPerson =
                    l_finApp.getFinObjectManager().getTraderByLoginId(l_loginId);
            }
            catch (NotFoundException e)
            {
                //catch����NotFoundException�𖳎�����B
            }

        }
                
        //�S�j�@@�㗝���͂̏ꍇ�̃`�F�b�N
        //�㗝���͂̏ꍇ�́A����p�X���[�h����͂��Ȃ�
        if(l_proxyInputPerson != null)
        {
            return OrderValidationResult.VALIDATION_OK_RESULT;
        }  
        else
        {
            //�T�j�@@�ڋq���͂̏ꍇ�̃`�F�b�N
            //this.validate����p�X���[�h(�ڋq, String)���R�[�����A 
            //�������ʂ�ԋp����B
            WEB3GentradeMainAccount l_genMainAccount;
            try
            {
                l_genMainAccount =
                    new WEB3GentradeMainAccount(l_subAccount.getAccountId());
            }
            catch (DataException de)
            {
                log.debug(STR_METHOD_NAME, de);
                return new OrderValidationResult(
                    ProcessingResult.newFailedResultInstance(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003));
            }
            return this.validateTradingPassword(
                l_genMainAccount,
                l_strTradingPassword);
        }

    }
    
    /**
     * (validate����p�X���[�h) <BR>
     * ����p�X���[�h�����������̃`�F�b�N���s���B<BR>
     * �ivalidateTradingPassword�̃I�[�o�[���C�h�j<BR>
     * <BR> 
     * �@@OpLoginSecurityService���A���O�C���^�C�v�������擾����B<BR>
     *  <BR>
     * �|���O�C���^�C�v����.������ == ����p�X���[�h�ݒ� <BR>
     *    �i�FTRADING_PWD_ENV�j�̑����l���h0�FDEFAULT<BR>
     *    �i����p�X���[�h���ڂ��g�p���Ȃ��j�h�̏ꍇ�A<BR>
     *     ���O�C���p�X���[�h�̏ƍ���1����B<BR>
     *  <BR>
     * �|���O�C���^�C�v����.������ == ����p�X���[�h�ݒ� <BR>
     *    �i�FTRADING_PWD_ENV�j�̑����l���h1�F����p�X���[�h�g�p�h<BR>
     *     �̏ꍇ�A����p�X���[�h�̏ƍ���2����B<BR>
     *  <BR>
     * ��1 ���O�C���p�X���[�h�̏ƍ� <BR>
     * �iOpLoginSecurityService.checkPassword() == false�j�̏ꍇ�A<BR>
     * �G���[���ʂ�ԋp����B�ȊO�A����I����ԋp����B<BR>
     *  <BR>
     * ��2 ����p�X���[�h�̏ƍ� <BR>
     * �ڋq.����p�X���[�h�𕜍����iWEB3Crypt.decrypt()���g�p�j����B<BR>
     * �����������p�X���[�h�ƈ����̃p�X���[�h����v���Ȃ��ꍇ�A<BR>
     * �G���[���ʂ�ԋp����B�ȊO�A����I����ԋp����B<BR> 
     *  <BR>
     * @@param l_genMainAccount - (�ڋq)
     * @@param l_strTradingPassword - (���̓p�X���[�h)
     * @@return �`�F�b�N����
     * @@roseuid 4042EC7B016D
     */
    public OrderValidationResult validateTradingPassword(
        WEB3GentradeMainAccount l_genMainAccount,
        String l_strTradingPassword)
    {
        final String STR_METHOD_NAME = "validateTradingPassword(WEB3GentradeMainAccount, String)";
        log.entering(STR_METHOD_NAME);
        
        OrderValidationResult l_orderValidationResult;
        
        //1) OpLoginSecurityService���A���O�C���^�C�v�������擾����
        
        //�T�[�r�X���擾
        OpLoginSecurityService l_securityService = (OpLoginSecurityService)
            Services.getService(OpLoginSecurityService.class);
        OpLoginAdminService l_admService = (OpLoginAdminService) Services.getService(
            OpLoginAdminService.class);
        
        //LoginInfo->LoginType->LoginTypeAttribute 
        LoginInfo l_loginInfo = l_securityService.getLoginInfo();
        Map l_mapAttributes = l_admService.getLoginTypeAttributes(l_loginInfo.getLoginTypeId());
        
        //���O�C���^�C�v�������擾����
        String l_strAttribute =
            (String) l_mapAttributes.get(
                WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV);
        
        //2) ���O�C���p�X���[�h�̏ƍ�
        if(WEB3TradingPwdEnvDef.DEFAULT.equals(l_strAttribute))
        {
            //�iOpLoginSecurityService.checkPassword() == false�j�̏ꍇ�A
            //�G���[���ʂ�ԋp����B�ȊO�A����I����ԋp����B
            if(l_securityService.checkPassword(l_strTradingPassword) == false)
            {
                l_orderValidationResult = new OrderValidationResult(
                    ProcessingResult.newFailedResultInstance(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00009));
            }
            else
            {
                l_orderValidationResult = OrderValidationResult.VALIDATION_OK_RESULT;
            }

        }
        //2) ����p�X���[�h�̏ƍ�
        else if(WEB3TradingPwdEnvDef.USE_TRADING_PWD.equals(l_strAttribute))
        {
            // �ڋq.����p�X���[�h�𕜍����iWEB3Crypt.decrypt()���g�p�j����B
            //�����������p�X���[�h�ƈ����̃p�X���[�h����v���Ȃ��ꍇ�A
            //�G���[���ʂ�ԋp����B�ȊO�A����I����ԋp����B
            WEB3Crypt l_crypt = new WEB3Crypt();
            String l_strMainAccountPassword =
                l_crypt.decrypt(l_genMainAccount.getTradingPassword());
            if (l_strMainAccountPassword.equals(l_strTradingPassword))
            {
                l_orderValidationResult = OrderValidationResult.VALIDATION_OK_RESULT;
            }
            else
            {
                l_orderValidationResult = new OrderValidationResult(
                    ProcessingResult.newFailedResultInstance(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00009));
            }

        }
        else
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����p�X���[�h�ݒ�(���O�C���^�C�v����) = " + l_strAttribute);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_orderValidationResult;
        
    }

    /**
     * (validate����\�ڋq) <BR>
     * <BR>
     * �P�j�ڋq�������~���ł��邩���肷��B <BR>
     *  �擾�����ڋq�I�u�W�F�N�g�̓��e���A�ȉ�(1)�`(3)�̉��ꂩ�ł���ꍇ�A<BR>
     *  ��O���X���[����B 
     *  <BR>
     *  [�����~���̃G���[����]  <BR>
     *  (1) �ڋq.�x�q�敪 == �hY�q�h <BR> 
     *  (2) �ڋq.�l�����b�N == �h���b�N�h And �ڋq.�����F�� == �h��F�h <BR>
     *  (3) �ڋq.�x�X���b�N == �h���b�N�h And �ڋq.�����F�� == �h��F�h <BR>
     * <BR>
     * �Q�j�ڋq���Ǘ����b�N�����𔻒肷��B  <BR>
     * �i������������ł���ꍇ(*2)���A�������ϒ����łȂ��ꍇ(*3)�̂݃`�F�b�N����B�j <BR>
     *   �擾�����ڋq�I�u�W�F�N�g�̓��e���A�Ǘ����b�N���Ŋ��A<BR>
     *   ���b�N�������ԊO�̏ꍇ�A��O���X���[����B <BR>
     *  <BR>
     *  [�Ǘ����b�N���̃G���[����] <BR>
     *  �ڋq.�Ǘ����b�N == �h���b�N�h And <BR>
     *  �ڋq.�����F�� == �h��F�h And <BR>
     *  { �ڋq.�Ǘ����b�N�����J�n�� �� ������(*1) Or <BR>
     *    �ڋq.�Ǘ����b�N�����I���� �� ������(*1) }�@@<BR>
     *  �������������b�N�������ԊO <BR>
     * <BR>
     * (*1) ������ <BR>
     * ������ԊǗ�.get������()�ɂĎ擾����B<BR> 
     * (*2) ������������ł���ꍇ <BR>
     * ������ԊǗ�.get������()�Ő���ɔ��������擾�ł���<BR>
     * �i��O���X���[����Ȃ��j�ꍇ�B <BR>
     * (*3) �������ϒ����łȂ��ꍇ <BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute(  <BR>
     * WEB3ThreadLocalSystemAttributePathDef.FORCED_SETTLE_ORDER_VALIDATION_SKIP) <BR>
     * �Ŏ擾�����l��BooleanEnum.TRUE�łȂ��ꍇ�B <BR>
     *  <BR>
     * @@param l_genMainAccount - (�ڋq) <BR>
     * @@return 
     * com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult
     * @@roseuid 409B3FC102CE
     */
    public OrderValidationResult validateAccountForTrading(WEB3GentradeMainAccount l_genMainAccount)
    {
        final String STR_METHOD_NAME = "validateAccountForTrading(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        MainAccountRow l_mainAccountRow =
            (MainAccountRow)l_genMainAccount.getDataSourceObject();
        //get �ڋq.�x�q�敪
        String l_strYellowCustomer = l_mainAccountRow.getYellowCustomer();
        //get �ڋq.�l�����b�N
        String l_strExaminLockFlag = l_mainAccountRow.getExaminLockFlag();
        //get �ڋq.�x�X���b�N
        String l_strBranchLock = l_mainAccountRow.getBranchLock();
        //get �ڋq.�����F��
        String l_strOrderPermission = l_mainAccountRow.getOrderPermission();
        //get �ڋq.�Ǘ����b�N
        String l_strMngLockFlag = l_mainAccountRow.getMngLockFlag();

        // �P�j�ڋq�������~���ł��邩���肷��B   
        //[�����~���̃G���[����]  
        //(1) �ڋq.�x�q�敪 == �hY�q�h 
        //(2) �ڋq.�l�����b�N == �h���b�N�h And �ڋq.�����F�� == �h��F�h 
        //(3) �ڋq.�x�X���b�N == �h���b�N�h And �ڋq.�����F�� == �h��F�h 
        if (WEB3YellowCustomerDef.YELLOW_CUSTOMER.equals(l_strYellowCustomer)
            || (WEB3MngLockDef.LOCK.equals(l_strExaminLockFlag) 
                && WEB3OrderPermissionDef.NO_PERMISSION.equals(l_strOrderPermission))
            || (WEB3BranchLockDef.BRANCH_LOCK.equals(l_strBranchLock) 
                && WEB3OrderPermissionDef.NO_PERMISSION.equals(l_strOrderPermission)))
        {
            log.debug("[�x�q�敪] �F " + l_strYellowCustomer);
            log.debug("[�l�����b�N] �F " + l_strExaminLockFlag);
            log.debug("[�x�X���b�N] �F " + l_strBranchLock);
            log.debug("[�����F��] �F " + l_strOrderPermission);
            log.debug("�����~�ڋq�G���[");
            log.exiting(STR_METHOD_NAME);
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00275));
        }

        //������ԊǗ�.get������()�ɂĎ擾
        Date l_datBizDate = null;
        try
        {
            l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        }
        catch (WEB3SystemLayerException e)
        {
            log.debug("�Ǘ����b�N�ɂ��ẮA������ԊǗ�.get������()�ŗ�O��throw���ꂽ�ꍇ�̓`�F�b�N���Ȃ�");
            log.exiting(STR_METHOD_NAME);
            return OrderValidationResult.VALIDATION_OK_RESULT;
        }
        if(l_datBizDate == null)
        {
            return OrderValidationResult.VALIDATION_OK_RESULT;
        }

        //�������ϒ����̏ꍇ
        if (BooleanEnum.TRUE.equals(
            ThreadLocalSystemAttributesRegistry.getAttribute(  
                WEB3ThreadLocalSystemAttributePathDef.FORCED_SETTLE_ORDER_VALIDATION_SKIP)))
        {
            return OrderValidationResult.VALIDATION_OK_RESULT;
        }
        //get �ڋq.�Ǘ����b�N�����J�n��
        Date l_datStartDate = l_mainAccountRow.getMngLockOffStartDate();
        //get �ڋq.�Ǘ����b�N�����I���� 
        Date l_datEndDate = l_mainAccountRow.getMngLockOffEndDate();

        //�Q�j�ڋq���Ǘ����b�N�����𔻒肷��B
        //[�Ǘ����b�N���̃G���[����] 
        // �ڋq.�Ǘ����b�N == �h���b�N�h And 
        // �ڋq.�����F�� == �h��F�h And 
        // { �ڋq.�Ǘ����b�N�����J�n�� �� ������(*1) Or 
        //   �ڋq.�Ǘ����b�N�����I���� �� ������(*1) }
        if (WEB3MngLockDef.LOCK.equals(l_strMngLockFlag)
           && WEB3OrderPermissionDef.NO_PERMISSION.equals(l_strOrderPermission))
        {
            if ((l_datStartDate != null) && (l_datEndDate != null))
            {
                if ((WEB3DateUtility.compareToDay(l_datStartDate, l_datBizDate) > 0)
                    || (WEB3DateUtility.compareToDay(l_datEndDate, l_datBizDate) < 0))
                {
                    log.debug("[�Ǘ����b�N] �F " + l_strMngLockFlag);
                    log.debug("[�����F��] �F " + l_strOrderPermission);
                    log.debug("[�Ǘ����b�N�����J�n��] �F " + l_datStartDate);
                    log.debug("[�Ǘ����b�N�����I����] �F " + l_datEndDate);
                    log.debug("[������] �F " + l_datBizDate);
                    log.debug("�Ǘ����b�N�ڋq�G���[");
                    log.exiting(STR_METHOD_NAME);
                    return new OrderValidationResult(
                        ProcessingResult.newFailedResultInstance(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00276));
                }
            }
            else
            {
                log.debug("[�Ǘ����b�N] �F " + l_strMngLockFlag);
                log.debug("[�����F��] �F " + l_strOrderPermission);
                log.debug("[�Ǘ����b�N�����J�n���^�I����] �F null");
                log.debug("�Ǘ����b�N�ڋq�G���[");
                log.exiting(STR_METHOD_NAME);
                return new OrderValidationResult(
                    ProcessingResult.newFailedResultInstance(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00276));
            }
        }

        log.exiting(STR_METHOD_NAME);
        return OrderValidationResult.VALIDATION_OK_RESULT;
    }
    
    /**
     * (validate����\�ڋq) <BR>
     *�ivalidateSubAccountForTrading�̃I�[�o�[���C�h�j<BR>
     *  <BR>
     * �P�j�@@�X�[�p�[�N���X�̏��������{����B<BR>
     *  <BR>
     * �Q�j�@@����\�q���𔻒肷��B<BR>
     * this.validate����\�ڋq�i�ڋq�j�@@���R�[�����A�������ʂ�ԋp����B<BR>
     *  <BR>
     * [validate����\�ڋq()�Ɏw�肷�����] <BR>
     *  �ڋq�F�@@�⏕����.getMainAccount() <BR>
     * @@param l_subAccount - (�⏕����) <BR>
     * @@return 
     * com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult
     */
    public OrderValidationResult validateSubAccountForTrading(SubAccount l_subAccount)
    {
        final String STR_METHOD_NAME = "validateSubAccountForTrading(SubAccount)";
        log.entering(STR_METHOD_NAME);

        //�P�j�X�[�p�[�N���X�̏��������{����
        OrderValidationResult l_result =
            super.validateSubAccountForTrading(l_subAccount);
        if (l_result.getProcessingResult().isFailedResult())
        {
            log.exiting(STR_METHOD_NAME);
            return l_result;
        }
        
        //get�ڋq
        WEB3GentradeMainAccount l_genMainAccount;
        try
        {
            l_genMainAccount = 
                new WEB3GentradeMainAccount(l_subAccount.getAccountId());
        }
        catch(DataException de)
        {
            log.debug(STR_METHOD_NAME,de);
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003));
        }
        
        //�Q�j �@@����\�q���𔻒肷��
        l_result =  this.validateAccountForTrading(l_genMainAccount);
        log.exiting(STR_METHOD_NAME);
        return l_result;
    }
    
    /**
     * (validate����\�ڋq) <BR>
     *�ivalidateAccountForTrading�j<BR>
     *  <BR>
     * �P�j�@@�ڋq�������~���ł��邩���肷��B<BR>
     * �����̌ڋq�I�u�W�F�N�g�̓��e���A�ȉ�(1)�`(3)�̉��ꂩ�ł���ꍇ�A<BR>
     * ��O���X���[����B<BR>
     *  <BR>
     * [�����~���̃G���[����] <BR>
     * (1) �ڋq.�x�q�敪 == �hY�q�h <BR>
     * (2) �ڋq.�l�����b�N == �h���b�N�h And �ڋq.�����F�� == �h��F�h <BR>
     * (3) �ڋq.�x�X���b�N == �h���b�N�h And �ڋq.�����F�� == �h��F�h <BR>
     *  <BR>
     * �Q�j�@@�ڋq���Ǘ����b�N�����𔻒肷��B<BR>
     * �擾�����ڋq�I�u�W�F�N�g�̓��e���A<BR>
     * �Ǘ����b�N���Ŋ��A���b�N�������ԊO�̏ꍇ�A��O���X���[����B<BR>
     *  <BR>
     * [�Ǘ����b�N���̃G���[����] <BR>
     * �ڋq.�Ǘ����b�N == �h���b�N�h And <BR>  
     * �ڋq.�����F�� == �h��F�h And <BR>
     * { �ڋq.�Ǘ����b�N�����J�n�� �� ����.������ Or <BR> 
     *   �ڋq.�Ǘ����b�N�����I���� �� ����.������ }�@@<BR>
     * �������������b�N�������ԊO<BR>
     *  <BR>
     * @@param l_genMainAccount - (�ڋq) <BR>
     * @@param l_tsBizDate - (������)<BR>
     * @@return 
     * com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult
     */
    public OrderValidationResult validateAccountForTrading(
        WEB3GentradeMainAccount l_genMainAccount,
        Timestamp l_tsBizDate)
    {
        final String STR_METHOD_NAME = 
            "validateSubAccountForTrading(WEB3GentradeMainAccount, Timestamp)";
        log.entering(STR_METHOD_NAME);
        
        MainAccountRow l_mainAccountRow =
            (MainAccountRow)l_genMainAccount.getDataSourceObject();
        //get �ڋq.�x�q�敪
        String l_strYellowCustomer = l_mainAccountRow.getYellowCustomer();
        //get �ڋq.�l�����b�N
        String l_strExaminLockFlag = l_mainAccountRow.getExaminLockFlag();
        //get �ڋq.�x�X���b�N
        String l_strBranchLock = l_mainAccountRow.getBranchLock();
        //get �ڋq.�����F��
        String l_strOrderPermission = l_mainAccountRow.getOrderPermission();
        //get �ڋq.�Ǘ����b�N
        String l_strMngLockFlag = l_mainAccountRow.getMngLockFlag();
        //get �ڋq.�Ǘ����b�N�����J�n��
        Date l_datStartDate = l_mainAccountRow.getMngLockOffStartDate();
        //get �ڋq.�Ǘ����b�N�����I���� 
        Date l_datEndDate = l_mainAccountRow.getMngLockOffEndDate();
        
        //�P�j�@@�ڋq�������~���ł��邩���肷��B
        //�����̌ڋq�I�u�W�F�N�g�̓��e���A�ȉ�(1)�`(3)�̉��ꂩ�ł���ꍇ�A��O���X���[����B
        //   [�����~���̃G���[����] 
        //   (1) �ڋq.�x�q�敪 == �hY�q�h 
        //   (2) �ڋq.�l�����b�N == �h���b�N�h And �ڋq.�����F�� == �h��F�h 
        //   (3) �ڋq.�x�X���b�N == �h���b�N�h And �ڋq.�����F�� == �h��F�h
        if (WEB3YellowCustomerDef.YELLOW_CUSTOMER.equals(l_strYellowCustomer)
            || (WEB3MngLockDef.LOCK.equals(l_strExaminLockFlag) 
                && WEB3OrderPermissionDef.NO_PERMISSION.equals(l_strOrderPermission))
            || (WEB3BranchLockDef.BRANCH_LOCK.equals(l_strBranchLock) 
                && WEB3OrderPermissionDef.NO_PERMISSION.equals(l_strOrderPermission)))
        {
            log.debug("[�x�q�敪] �F " + l_strYellowCustomer);
            log.debug("[�l�����b�N] �F " + l_strExaminLockFlag);
            log.debug("[�x�X���b�N] �F " + l_strBranchLock);
            log.debug("[�����F��] �F " + l_strOrderPermission);
            log.debug("�����~�ڋq�G���[");
            log.exiting(STR_METHOD_NAME);
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00275));
        }
        
        //�Q�j�@@�ڋq���Ǘ����b�N�����𔻒肷��B
        //�擾�����ڋq�I�u�W�F�N�g�̓��e���A
        //�Ǘ����b�N���Ŋ��A���b�N�������ԊO�̏ꍇ�A��O���X���[����B
        //   [�Ǘ����b�N���̃G���[����] 
        //    �ڋq.�Ǘ����b�N == �h���b�N�h And 
        //    �ڋq.�����F�� == �h��F�h And 
        //    { �ڋq.�Ǘ����b�N�����J�n�� �� ����.������ Or 
        //      �ڋq.�Ǘ����b�N�����I���� �� ����.������ }�@@
        if (WEB3MngLockDef.LOCK.equals(l_strMngLockFlag)
           && WEB3OrderPermissionDef.NO_PERMISSION.equals(l_strOrderPermission))
        {
            if ((l_datStartDate != null) && (l_datEndDate != null))
            {
                if ((WEB3DateUtility.compareToDay(l_datStartDate, l_tsBizDate) > 0)
                    || (WEB3DateUtility.compareToDay(l_datEndDate, l_tsBizDate) < 0))
                {
                    log.debug("[�Ǘ����b�N] �F " + l_strMngLockFlag);
                    log.debug("[�����F��] �F " + l_strOrderPermission);
                    log.debug("[�Ǘ����b�N�����J�n��] �F " + l_datStartDate);
                    log.debug("[�Ǘ����b�N�����I����] �F " + l_datEndDate);
                    log.debug("[������] �F " + l_tsBizDate);
                    log.debug("�Ǘ����b�N�ڋq�G���[");
                    log.exiting(STR_METHOD_NAME);
                    return new OrderValidationResult(
                        ProcessingResult.newFailedResultInstance(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00276));
                }
            }
            else
            {
                log.debug("[�Ǘ����b�N] �F " + l_strMngLockFlag);
                log.debug("[�����F��] �F " + l_strOrderPermission);
                log.debug("[�Ǘ����b�N�����J�n���^�I����] �F null");
                log.debug("�Ǘ����b�N�ڋq�G���[");
                log.exiting(STR_METHOD_NAME);
                return new OrderValidationResult(
                    ProcessingResult.newFailedResultInstance(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00276));
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return OrderValidationResult.VALIDATION_OK_RESULT;
    }

    /**
     * (validate����\�ڋq) <BR>
     *  <BR>
     * �P�j�@@�ڋq�������~���ł��邩���肷��B <BR>
     * <BR>
     * �@@�����̌ڋq�I�u�W�F�N�g�̓��e���A�ȉ���[�����~���̃G���[����]�ɓK������ꍇ�A��O���X���[����B <BR>
     * <BR>
     * �y�G���[�`�F�b�N�����z <BR>
     * ������.�����t���O = 1 �̏ꍇ�A(3),(4)�̃`�F�b�N���s���B <BR>
     * �@@����.�����t���O = 1 �ȊO�̏ꍇ�A�ȉ��̃`�F�b�N�͍s��Ȃ��B <BR>
     * <BR>
     * <BR>
     * �@@[�����~���̃G���[����] <BR>
     * �@@(1) �ڋq.�x�q�敪 == �hY�q�h <BR>
     * �@@(2) �ڋq.�l�����b�N == �h���b�N�h And �ڋq.�����F�� == �h��F�h <BR>
     * �@@(3) �ڋq.�x�X���b�N == �h���b�N�h And �ڋq.�����F�� == �h��F�h <BR>
     * �@@(4) �ڋq���Ǘ����b�N�����𔻒肷��B <BR>
     * �@@�@@�@@�擾�����ڋq�I�u�W�F�N�g�̓��e���A <BR>
     * �@@�@@�@@�Ǘ����b�N���Ŋ��A���b�N�������ԊO�̏ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@[�Ǘ����b�N���̃G���[����] <BR>
     * �@@�@@�@@�ڋq.�Ǘ����b�N == �h���b�N�h And   <BR>
     * �@@�@@�@@�ڋq.�����F�� == �h��F�h And <BR>
     * �@@�@@�@@{ �ڋq.�Ǘ����b�N�����J�n�� > ����.������ Or  <BR>
     * �@@�@@�@@  �ڋq.�Ǘ����b�N�����I���� < ����.������ }�@@�������������b�N�������ԊO <BR>
     * <BR>
     * <BR>
     * �������I�Ȏ����~�̃`�F�b�N���s���ۂɂ��̃��\�b�h���g�p���܂��B <BR>
     * �@@ �`�F�b�N���������b�N�������t���O�ɂ���ăJ�X�^�}�C�Y���Ă��������B <BR>
     * <BR>
     * ������.�����t���O = 1�@@�̏ꍇ�͈ȉ��̃G���[���b�Z�[�W���o�͂���B <BR>
     * �@@(3)�ŗ�O���X���[�ꍇ�A�x�X���b�N�G���[�B�i�V�K�G���[���b�Z�[�W�j <BR>
     *   (4)�ŗ�O���X���[�ꍇ�A�Ǘ����b�N�G���[�B�i�V�K�G���[���b�Z�[�W�j<BR>
     *  <BR>
     * @@param l_genMainAccount - (�ڋq) <BR>
     * @@param l_tsBizDate - (������)<BR>
     * @@param l_strProcessFlag - (�����t���O)<BR>
     * @@return
     * com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult
     */
    public OrderValidationResult validateAccountForTrading(
        WEB3GentradeMainAccount l_genMainAccount,
        Timestamp l_tsBizDate,
        String l_strProcessFlag)
    {
        final String STR_METHOD_NAME =
            "validateSubAccountForTrading(WEB3GentradeMainAccount, Timestamp, String)";
        log.entering(STR_METHOD_NAME);

        MainAccountRow l_mainAccountRow =
            (MainAccountRow)l_genMainAccount.getDataSourceObject();
        //get �ڋq.�x�X���b�N
        String l_strBranchLock = l_mainAccountRow.getBranchLock();
        //get �ڋq.�����F��
        String l_strOrderPermission = l_mainAccountRow.getOrderPermission();
        //get �ڋq.�Ǘ����b�N
        String l_strMngLockFlag = l_mainAccountRow.getMngLockFlag();
        //get �ڋq.�Ǘ����b�N�����J�n��
        Date l_datStartDate = l_mainAccountRow.getMngLockOffStartDate();
        //get �ڋq.�Ǘ����b�N�����I����
        Date l_datEndDate = l_mainAccountRow.getMngLockOffEndDate();

        //�P�j�@@�ڋq�������~���ł��邩���肷��B
        //�����̌ڋq�I�u�W�F�N�g�̓��e���A�ȉ���[�����~���̃G���[����]�ɓK������ꍇ�A
        //��O���X���[����B
        //�y�G���[�`�F�b�N�����z
        //����.�����t���O = 1 �̏ꍇ�A(3),(4)�̃`�F�b�N���s���B
        //����.�����t���O = 1 �ȊO�̏ꍇ�A�ȉ��̃`�F�b�N�͍s��Ȃ��B
        if (WEB3GentradeProcessFlagDef.PROCESS_FLAG_1.equals(l_strProcessFlag))
        {
            //(3) �ڋq.�x�X���b�N == �h���b�N�h And �ڋq.�����F�� == �h��F�h
            if (WEB3BranchLockDef.BRANCH_LOCK.equals(l_strBranchLock)
                && WEB3OrderPermissionDef.NO_PERMISSION.equals(l_strOrderPermission))
            {
                log.debug("[�x�X���b�N] �F " + l_strBranchLock);
                log.debug("[�����F��] �F " + l_strOrderPermission);
                log.debug("�x�X���b�N�G���[");
                log.exiting(STR_METHOD_NAME);
                return new OrderValidationResult(
                    ProcessingResult.newFailedResultInstance(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02957));
            }
            //(4) �ڋq���Ǘ����b�N�����𔻒肷��B
            //�擾�����ڋq�I�u�W�F�N�g�̓��e���A
            //�Ǘ����b�N���Ŋ��A���b�N�������ԊO�̏ꍇ�A��O���X���[����B
            //[�Ǘ����b�N���̃G���[����]
            //�ڋq.�Ǘ����b�N == �h���b�N�h And
            //�ڋq.�����F�� == �h��F�h And
            //{ �ڋq.�Ǘ����b�N�����J�n�� > ����.������ Or
            //�ڋq.�Ǘ����b�N�����I���� < ����.������ }�@@�������������b�N�������ԊO
            if (WEB3MngLockDef.LOCK.equals(l_strMngLockFlag)
                && WEB3OrderPermissionDef.NO_PERMISSION.equals(l_strOrderPermission))
            {
                if ((l_datStartDate != null) && (l_datEndDate != null))
                {
                    if ((WEB3DateUtility.compareToDay(l_datStartDate, l_tsBizDate) > 0)
                        || (WEB3DateUtility.compareToDay(l_datEndDate, l_tsBizDate) < 0))
                    {
                        log.debug("[�Ǘ����b�N] �F " + l_strMngLockFlag);
                        log.debug("[�����F��] �F " + l_strOrderPermission);
                        log.debug("[�Ǘ����b�N�����J�n��] �F " + l_datStartDate);
                        log.debug("[�Ǘ����b�N�����I����] �F " + l_datEndDate);
                        log.debug("[������] �F " + l_tsBizDate);
                        log.debug("�Ǘ����b�N�G���[");
                        log.exiting(STR_METHOD_NAME);
                        return new OrderValidationResult(
                            ProcessingResult.newFailedResultInstance(
                                WEB3ErrorCatalog.BUSINESS_ERROR_02958));
                    }
                }
                else
                {
                    log.debug("[�Ǘ����b�N] �F " + l_strMngLockFlag);
                    log.debug("[�����F��] �F " + l_strOrderPermission);
                    log.debug("[�Ǘ����b�N�����J�n���^�I����] �F null");
                    log.debug("�Ǘ����b�N�G���[");
                    log.exiting(STR_METHOD_NAME);
                    return new OrderValidationResult(
                        ProcessingResult.newFailedResultInstance(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02958));
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
        return OrderValidationResult.VALIDATION_OK_RESULT;
    }
}
@
