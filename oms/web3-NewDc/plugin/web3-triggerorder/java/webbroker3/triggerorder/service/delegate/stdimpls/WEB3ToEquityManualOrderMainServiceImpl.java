head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.51.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToEquityManualOrderMainServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����蓮�������C���T�[�r�XImpl(WEB3ToEquityManualOrderMainServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/3/6 鰐V(���u) �V�K�쐬
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.rlsgateway.define.WEB3RlsNotifyTypeDef;
import webbroker3.triggerorder.define.WEB3ToManualOrderErrorCodeDef;
import webbroker3.triggerorder.message.WEB3EquityManualCompleteRequest;
import webbroker3.triggerorder.message.WEB3EquityManualCompleteResponse;
import webbroker3.triggerorder.message.WEB3EquityManualConfirmRequest;
import webbroker3.triggerorder.message.WEB3EquityManualConfirmResponse;
import webbroker3.triggerorder.message.WEB3EquityManualUnit;
import webbroker3.triggerorder.service.delegate.WEB3ToEquityManualOrderMainService;
import webbroker3.triggerorder.service.delegate.WEB3ToEquityManualOrderUnitService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�����蓮�������C���T�[�r�XImpl)<BR>
 * ���ۃN���X�iabstract�j<BR>
 * 
 * @@author 鰐V <BR>
 * @@version 1.0<BR>
 */
public abstract class WEB3ToEquityManualOrderMainServiceImpl 
    extends WEB3ClientRequestService implements WEB3ToEquityManualOrderMainService 
{
    
    /**
     * ���O�I�u�W�F�N�g
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
    	WEB3ToEquityManualOrderMainServiceImpl.class);

    /**
     * @@roseuid 4349DEE70222
     */
    public WEB3ToEquityManualOrderMainServiceImpl() 
    {
     
    }
    
    /**
     * ���������X�V���C���T�[�r�X���������{����B<BR>
     * <BR>
     * �P�j�@@instanceof(�����蓮�����������N�G�X�g)==true�̏ꍇ <BR>
     * �@@�@@�@@�@@�@@this.submit()���R�[������B <BR>
     * �@@�@@�@@�����łȂ��ꍇ�A <BR>
     * �@@�@@�@@�@@�@@this.validate()���R�[������B <BR>
     * <BR>
     * �Q�j�@@�P�j�̖߂�l��Ԃ��B <BR>
     * <BR>
     * @@param l_request - (WEB3GenRequest) <BR>
     * @@return WEB3GenResponse <BR>
     * @@throws WEB3BaseException <BR>
     * @@roseuid 432175DD01A0
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^��Null�ł�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3GenResponse l_response = null;
        //�P�j�@@instanceof(�����蓮�����������N�G�X�g)==true�̏ꍇ
        if(l_request instanceof WEB3EquityManualCompleteRequest)
        {
            l_response = this.submit((WEB3EquityManualCompleteRequest) l_request);
        }
        else if (l_request instanceof WEB3EquityManualConfirmRequest)
        {
            l_response = this.validate((WEB3EquityManualConfirmRequest) l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }        

        log.exiting(STR_METHOD_NAME);
        //�Q�j�@@�P�j�̖߂�l��Ԃ��B
        return l_response;
    }
    
    /**
     * �V�[�P���X�}  <BR>
     * �u(�����蓮�������C���T�[�r�X)validate�v�Q�ƁB <BR>
     * <BR>
     *  ���X�|���X.�蓮�����G���[�R�[�h��"����"�̊����蓮����Unit��1�������݂��Ȃ��ꍇ�A<BR>
     *        �u�蓮�����Ώے����Ȃ��v�̗�O��throw����B<BR>
     * <BR>
     *          class :WEB3BusinessLayerException<BR>
     *          tag   :BUSINESS_ERROR_02393<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3EquityManualConfirmResponse
     * @@throws WEB3BaseException<BR>
     * @@roseuid 432175DD01A0
     */
    protected WEB3EquityManualConfirmResponse validate(
        WEB3EquityManualConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate(WEB3EquityManualConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();
        
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(
                OpLoginSecurityService.class);
        
        if (l_opLoginSec.getAccountId() == 0)
        {
            //1.2 validate�Ǘ��Ҍ���(String[])
            this.validateAdminPermission(l_request.branchCode);
        }
        //1.3 ArrayList�𐶐�����B
        List l_lisEquityManualUnit = new ArrayList();

        //1.4 getUnitService( )
        WEB3ToEquityManualOrderUnitService l_toEquityManualOrderUnitService = 
            this.getUnitService();
            
        //1.5 ���N�G�X�g�f�[�^.����ID[]�̗v�f����loop����
        String[] l_strOrderIds = l_request.orderId;
        boolean l_blnManualOrderError = true;
        for (int i = 0; i < l_strOrderIds.length; i++)
        {
            //1.5.1 validate(String)
            WEB3EquityManualUnit l_equityManualUnit
                = l_toEquityManualOrderUnitService.validate(l_strOrderIds[i]);
            if(WEB3ToManualOrderErrorCodeDef.NORMAL.equals(
                l_equityManualUnit.manualOrderErrorCode))
            {
                l_blnManualOrderError = false;
            }            
            //1.5.2 add(arg0 : Object)
            l_lisEquityManualUnit.add(l_equityManualUnit);
        }
        //submit( )�̖߂�l.�蓮�����G���[�R�[�h��"����"�̖߂�l��1�������������ꍇ�A
        //�u�蓮�����Ώے����Ȃ��v�̗�O��throw����B
        if (l_blnManualOrderError)
        {
            log.debug("�蓮�����Ώے����Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02393,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�蓮�����Ώے����Ȃ��B");  
        }
        
        WEB3EquityManualUnit[] l_equityManualUnits = new WEB3EquityManualUnit[l_lisEquityManualUnit.size()]; 
        //1.6 toArray( )
        l_lisEquityManualUnit.toArray(l_equityManualUnits);
        
        //1.7 createResponse( )
        WEB3EquityManualConfirmResponse l_response = (WEB3EquityManualConfirmResponse) l_request.createResponse();
        
        //1.8 set�m�F���X�|���X�v���p�e�B(�����蓮�����m�F���X�|���X, �����蓮����Unit[])
        this.setConfirmResponseProperty(l_response, l_equityManualUnits);

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * �V�[�P���X�} <BR>
     * �u(�����蓮�������C���T�[�r�X)submit�v�Q�ƁB<BR>
     * <BR>
     *  ���X�|���X.�蓮�����G���[�R�[�h��"����"�̊����蓮����Unit��1�������݂��Ȃ��ꍇ�A<BR>
     * �u�蓮�����Ώے����Ȃ��v�̗�O��throw����B<BR>
     * <BR>
     *          class :WEB3BusinessLayerException<BR>
     *          tag   :BUSINESS_ERROR_02393<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3EquityManualCompleteResponse
     * @@throws WEB3BaseException<BR>
     * @@roseuid 432175DD01A0
     */
    protected WEB3EquityManualCompleteResponse submit(
        WEB3EquityManualCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submit(WEB3EquityManualCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        String l_strRlsNotifyType = null;
        //1.1 validate( )
        l_request.validate();
        
        //1.2 �i����t���[�F�Ǘ��҂̏ꍇ�j
        //���Ǘ��҂̏ꍇ(OpLoginSecurityService.getAccountId��
        //�߂�l == 0)�̏ꍇ
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
                    
        if (l_opLoginSec.getAccountId() == 0)
        {
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

            //1.2.1 validate�Ǘ��Ҍ���(String[])
            this.validateAdminPermission(l_request.branchCode);
            
            //1.2.2 validate����p�X���[�h
            l_admin.validateTradingPassword(l_request.password);            
            l_strRlsNotifyType = WEB3RlsNotifyTypeDef.MANUAL_ADMIN;
        }
        else
        {
            // 1.3 get�㗝���͎�( ) 
            WEB3GentradeTrader l_trader = (WEB3GentradeTrader)this.getTrader();
            if(l_trader == null)
            {
                //�ڋq�̏ꍇ�i�Ǘ��҂łȂ�
                //&&�㗝���͎҃I�u�W�F�N�g���擾�ł��Ȃ��ꍇ�j�j�̂ݎ��{����B
                
                //1.4 validate����p�X���[�h
                this.validateTradingPassword(l_request.password);
                l_strRlsNotifyType = WEB3RlsNotifyTypeDef.MANUAL_USER;
            }
            else
            {
                l_strRlsNotifyType = WEB3RlsNotifyTypeDef.MANUAL_OPERATOR;
            }
        }
        //1.5 ArrayList�𐶐�����B
        List l_lisEquityManualUnit = new ArrayList();

        //1.6 getUnitService( )
        WEB3ToEquityManualOrderUnitService l_toEquityManualOrderUnitService = this.getUnitService();
        
        //1.7 ���N�G�X�g�f�[�^.����ID[]�̗v�f����loop����
        String[] l_strOrderIds = l_request.orderId;
        boolean l_blnManualOrderError = true;
        for (int i = 0; i < l_strOrderIds.length; i++)
        {
            //1.7.1 submit(String, Long, String)
            WEB3EquityManualUnit l_equityManualUnit = 
                l_toEquityManualOrderUnitService.submit(
                    l_strOrderIds[i],
                    new Long(l_opLoginSec.getLoginId()),
                    l_strRlsNotifyType);
                
            if(WEB3ToManualOrderErrorCodeDef.NORMAL.equals(l_equityManualUnit.manualOrderErrorCode))
            {
                l_blnManualOrderError = false;
            }            
            //1.7.2 add(arg0 : Object)
            l_lisEquityManualUnit.add(l_equityManualUnit);
        }
        //submit( )�̖߂�l.�蓮�����G���[�R�[�h��"����"�̖߂�l��1�������������ꍇ�A
        //�u�蓮�����Ώے����Ȃ��v�̗�O��throw����B
        if (l_blnManualOrderError)
        {
            log.debug("�蓮�����Ώے����Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02393,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�蓮�����Ώے����Ȃ��B");  
        }
        
        WEB3EquityManualUnit[] l_equityManualUnits = new WEB3EquityManualUnit[l_lisEquityManualUnit.size()];
        //1.8 toArray( )
        l_lisEquityManualUnit.toArray(l_equityManualUnits);
        
        //1.9 createResponse( )
        WEB3EquityManualCompleteResponse l_response = (WEB3EquityManualCompleteResponse) l_request.createResponse();
        
        //1.10 set�������X�|���X�v���p�e�B(�����蓮�����������X�|���X, �����蓮����Unit[])
        this.setCompleteResponseProperty(l_response, l_equityManualUnits);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�Ǘ��Ҍ���)
     * <BR>
     * �P�j�@@���O�C�������Ǘ��҃C���X�^���X���擾����B  <BR>
     * �@@�@@�@@�Ǘ���.getInstanceFrom���O�C�����()���R�[������B <BR>
     * <BR>
     * <BR>
     * �Q�j�@@�Ǘ��Ҍ����`�F�b�N�B <BR>
     * �@@�@@�@@�Ǘ���.validate����()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@[validate����()�Ɏw�肷�����] <BR>
     * �@@�@@�@@�@@�\�J�e�S���R�[�h�F<BR>
     * �@@�@@�@@�@@WEB3TransactionCategoryDef.��������(�g���K�[�����蓮����) <BR>
     * �@@�@@�@@is�X�V�F�@@true <BR>
     * <BR>
     * <BR>
     * �R�j�@@ ���X�����`�F�b�N�B <BR>
     * �@@�@@�@@ �Ǘ���.validate���X����()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@[validate����()�Ɏw�肷�����] <BR>
     * �@@�@@�@@���X�R�[�h�F�@@�p�����[�^.���X�R�[�h�ꗗ <BR>
     * <BR>
     * @@param l_strBranchCodes - (���X�R�[�h�ꗗ) <BR>
     * @@throws WEB3BaseException <BR>
     * @@roseuid 432175DD01A0
     */
    protected void validateAdminPermission(
        String[] l_strBranchCodes) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateAdminPermission(String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@���O�C�������Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_web3Administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        //�Q�j�@@�Ǘ��Ҍ����`�F�b�N�B
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.DOMESTIC_EQUITY_TRIGGER_ORDER_MANUAL, 
            true);
        //�R�j�@@ ���X�����`�F�b�N�B 
        l_web3Administrator.validateBranchPermission(l_strBranchCodes);

        log.exiting(STR_METHOD_NAME);        
    }
    
    /**
     * (validate����p�X���[�h)
     * <BR>
     * �ڋq�̎���p�X���[�h���`�F�b�N����B <BR>
     * <BR>
     * �P�j�@@�ڋq�I�u�W�F�N�g���擾����B <BR>
     * �@@�@@�@@this.get����()���R�[������B <BR>
     * <BR>
     * �Q�j�@@����p�X���[�h�`�F�b�N�B <BR>
     * �@@�@@�@@�����`�F�b�N.validate����p�X���[�h()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@[validate����p�X���[�h()�Ɏw�肷�����] <BR>
     * �@@�@@�@@�ڋq�F�@@get����()�̖߂�l <BR>
     * �@@�@@�@@�p�X���[�h�F�@@�p�����[�^.�p�X���[�h <BR>
     * <BR>
     * @@param l_strPassword - (�p�X���[�h)<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 432175DD01A0
     */
    protected void validateTradingPassword(
        String l_strPassword) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateTradingPassword(String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�ڋq�I�u�W�F�N�g���擾����B
        WEB3GentradeMainAccount l_mainAccount= 
            (WEB3GentradeMainAccount) this.getMainAccount();
        
        //�Q�j�@@����p�X���[�h�`�F�b�N�B
        FinApp l_finApp = GtlUtils.getFinApp();
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator(); 
        
        OrderValidationResult l_orderValidationResult = 
            l_orderValidator.validateTradingPassword(l_mainAccount, l_strPassword);
        
        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("�p�X���[�h�̃`�F�b�N���s��");            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_orderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);        
    }
    
    /**
     * ���ۃ��\�b�h�iabstract�j<BR>
     * @@return WEB3EquityManualOrderUnitService
     * @@roseuid 432175DD01A0
     */
    protected abstract WEB3ToEquityManualOrderUnitService getUnitService();
    
    /**
     * (set�m�F���X�|���X�v���p�e�B)
     * <BR>
     * �P�j�@@���L�����̑S�ĂɊY������ꍇ�݈̂ȉ������{����B <BR>
     * �@@�@@�@@�@@�E�ڋq�ł���B <BR>
     * �@@�@@�@@�@@�E�p�����[�^.�����蓮����Unit[0].�蓮�����G���[�R�[�h��"����"�ł���B <BR>
     * <BR>
     * �@@�@@�P�|�P�j�@@�ڋq�I�u�W�F�N�g���擾����B <BR>
     * �@@�@@�@@�@@�@@�@@�@@this.get�ڋq()���R�[������B <BR>
     * <BR>
     * �@@�@@�P�|�Q�j�@@���Y�q���M�p�q���ǂ������肷��B  <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�ڋq�Dis�M�p�����J��("0�F�w��Ȃ�")���A"true"�̏ꍇ�͐M�p�q�B <BR>
     * �@@�@@�@@�@@�@@�@@�@@�ȊO�A��M�p�q�B <BR>
     * <BR>
     * �@@�@@�P�|�R�j�@@this.get�⏕����( )�ɂāA�Y���ڋq�̕⏕�����I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@[get�⏕����()�ɐݒ肷�����]  <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�M�p�q�̏ꍇ�F�@@SubAccountTypeEnum.�M�p����������Z�b�g <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��M�p�q�̏ꍇ�F�@@SubAccountTypeEnum.��������������Z�b�g�B <BR>
     * <BR>
     * �@@�@@�P�|�S�j�@@this.set����J�����_�R���e�L�X�g( )���R�[������B  <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@[set����J�����_�R���e�L�X�g()�ɐݒ肷�����]  <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����蓮����Unit�F�@@�����蓮����Unit��0�Ԗڂ̗v�f<BR>
     * �@@�@@�P�|�T�j�@@�s��I���x���s��R�[�h���擾����B <BR>
     * �@@�@@�@@�@@�@@�@@�@@������ԊǗ�.get�s��ǌx���s��()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@[get�s��ǌx���s��()�ɐݒ肷�����] <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���X�F�@@get�⏕����()�̖߂�l.get����X()  <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����^�C�v�F�@@"����"  <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�M�p����敪�F�@@(*1) <BR>
     * <BR>
     * (*1)�M�p����敪 <BR>
     * �@@�@@�@@��M�p�q�̏ꍇ�A"DEFAULT"���Z�b�g�B <BR>
     * �@@�@@�A�M�p�q�̏ꍇ�͈ȉ��̒ʂ�B <BR>
     * �@@�@@�@@�@@�E�ȉ������S�ĂɊY������ꍇ�A�h���x�^��ʐM�p(����)�h���Z�b�g�B <BR>
     * �@@�@@�@@�@@�@@�@@�@@�ڋq.is�M�p�����J��(�h���x�M�p�h)��true <BR>
     * �@@�@@�@@�@@�@@�@@�@@�ڋq.is�M�p�����J��(�h��ʐM�p�h)��true <BR>
     * �@@�@@�@@�@@�E�ȉ������S�ĂɊY������ꍇ�A�h���x�M�p�h���Z�b�g�B <BR>
     * �@@�@@�@@�@@�@@�@@�@@�ڋq.is�M�p�����J��(�h���x�M�p�h)��true <BR>
     * �@@�@@�@@�@@�@@�@@�@@�ڋq.is�M�p�����J��(�h��ʐM�p�h)��false <BR>
     * �@@�@@�@@�@@�E�ȉ������S�ĂɊY������ꍇ�A�h��ʐM�p�h���Z�b�g�B <BR>
     * �@@�@@�@@�@@�@@�@@�@@�ڋq.is�M�p�����J��(�h���x�M�p�h)��false <BR>
     * �@@�@@�@@�@@�@@�@@�@@�ڋq.is�M�p�����J��(�h��ʐM�p�h)��true  <BR>
     * <BR>
     * �Q�j�@@�m�F���X�|���X�Ƀv���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@�����蓮����Unit�F�@@�p�����[�^.�����蓮�����ꗗ <BR>
     * �@@�s��I���x���s��R�[�h�F�@@get�s��ǌx���s��()�̖߂�l(*2) <BR>
     * <BR>
     * �@@(*2)�ڋq�̂݃Z�b�g����B <BR>
     * �@@�@@�@@�@@�Ǘ��҂̏ꍇ��null���Z�b�g�B <BR>
     * <BR>
     * �R�j�@@�m�F���X�|���X��Ԃ��B <BR>
     * <BR>
     * @@param l_confirmResponse - (�m�F���X�|���X)<BR>
     * @@param l_equityManualUnits - (�����蓮����Unit[])<BR>
     * @@return WEB3EquityManualConfirmResponse
     * @@throws WEB3BaseException<BR>
     * @@roseuid 432175DD01A0
     */
    protected WEB3EquityManualConfirmResponse setConfirmResponseProperty(
        WEB3EquityManualConfirmResponse l_confirmResponse, 
        WEB3EquityManualUnit[] l_equityManualUnits) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "setConfirmResponseProperty(" +
            "WEB3EquityManualConfirmResponse, WEB3EquityManualUnit[])";
        log.entering(STR_METHOD_NAME);
        
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
            
        WEB3GentradeTrader l_trader = (WEB3GentradeTrader)this.getTrader();
        SubAccountTypeEnum l_subAccountTypeEnum = null;
        String l_strMarginTradingDiv = null;
        
        //�P�j�@@���L�����̑S�ĂɊY������ꍇ�݈̂ȉ������{����B 
        //�@@�@@�E�ڋq�ł���B 
        //�@@�@@�E�p�����[�^.�����蓮����Unit[0].�蓮�����G���[�R�[�h��"����"�ł���B 

        if (l_opLoginSec.getAccountId() != 0 && l_trader == null &&
                WEB3ToManualOrderErrorCodeDef.NORMAL.equals(
                    l_equityManualUnits[0].manualOrderErrorCode))
        {
            //�P�|�P�j�@@�ڋq�I�u�W�F�N�g���擾����B 
            WEB3GentradeMainAccount l_mainAccount= (
                WEB3GentradeMainAccount) this.getMainAccount();
            
            WEB3GentradeSubAccount l_subAccount = null;
            //�P�|�Q�j�@@���Y�q���M�p�q���ǂ������肷��B 
            if(l_mainAccount.isMarginAccountEstablished(
                WEB3GentradeRepaymentDivDef.DEFAULT))
            {
                // �M�p�q�̏ꍇ�F�@@SubAccountTypeEnum.�M�p����������Z�b�g 
                l_subAccountTypeEnum = SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT;
                
                //�A�M�p�q�̏ꍇ�͈ȉ��̒ʂ�B
                boolean l_blnMarginSys =
                    l_mainAccount.isMarginAccountEstablished(
                        WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS);
                
                boolean l_blnMarginGen =
                    l_mainAccount.isMarginAccountEstablished(
                        WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN);
                
                //�E�ȉ������S�ĂɊY������ꍇ�A�h���x�^��ʐM�p(����)�h���Z�b�g�B 
                //       �ڋq.is�M�p�����J��(�h���x�M�p�h)��true 
                //       �ڋq.is�M�p�����J��(�h��ʐM�p�h)��true                     
                if(l_blnMarginSys && l_blnMarginGen)
                {
                    l_strMarginTradingDiv = 
                        WEB3MarginTradingDivDef.MARKET_MARGIN_OR_INSTITUTION_MARGIN;
                }
                else if(l_blnMarginSys && !l_blnMarginGen)
                {
                    l_strMarginTradingDiv = WEB3MarginTradingDivDef.MARKET_MARGIN;
                }
                else if(!l_blnMarginSys && l_blnMarginGen)
                {
                    l_strMarginTradingDiv = WEB3MarginTradingDivDef.INSTITUTION_MARGIN;
                }
            }
            else
            {
                //��M�p�q�̏ꍇ�F�@@SubAccountTypeEnum.��������������Z�b�g
                l_subAccountTypeEnum = SubAccountTypeEnum.EQUITY_SUB_ACCOUNT;
                // �@@��M�p�q�̏ꍇ�A"DEFAULT"���Z�b�g�B
                l_strMarginTradingDiv =WEB3MarginTradingDivDef.DEFAULT;                  
            }
            // �P�|�R�j�@@this.get�⏕����( )�ɂāA�Y���ڋq�̕⏕�����I�u�W�F�N�g���擾����B
            l_subAccount = 
                (WEB3GentradeSubAccount) this.getSubAccount(l_subAccountTypeEnum);
            
            // �P�|�S�j�@@this.set����J�����_�R���e�L�X�g( )���R�[������B  
            //�@@�@@[set����J�����_�R���e�L�X�g()�ɐݒ肷�����]  
            //�@@�@@�����蓮����Unit�F�@@�����蓮����Unit��0�Ԗڂ̗v�f
            this.setTradingClendarContext(l_equityManualUnits[0]);
            
            //�P�|�T�j�@@�s��I���x���s��R�[�h���擾����B
            //������ԊǗ�.get�s��ǌx���s��()
            String[] l_strTradeCloseSuspensions =
                WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
                    l_subAccount.getWeb3GenBranch(),
                    ProductTypeEnum.EQUITY,
                    l_strMarginTradingDiv
                    );
            
            //�Q�j�@@�m�F���X�|���X�Ƀv���p�e�B���Z�b�g����B 
            l_confirmResponse.messageSuspension = l_strTradeCloseSuspensions;
        }
        // �Ǘ��҂̏ꍇ��null���Z�b�g
        else if(l_opLoginSec.getAccountId() == 0)
        {
            l_confirmResponse.messageSuspension = null;
        }
        l_confirmResponse.manualUnits = l_equityManualUnits;
        
        log.exiting(STR_METHOD_NAME);
        return l_confirmResponse;
    }

    /**
     * (set�������X�|���X�v���p�e�B)
     * <BR>
     * �����蓮�����������X�|���X�ɒl���Z�b�g���A�Ԃ��B <BR>
     * <BR>
     * �P�j�@@�p�����[�^�D�������X�|���X�Ƀv���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@�����蓮����Unit�F�@@�p�����[�^.�����蓮�����ꗗ <BR>
     * �@@�X�V���ԁF�@@GtlUtils.getSystemTimestamp() <BR>
     * <BR>
     * �Q�j�@@�������X�|���X��Ԃ��B<BR>
     * <BR>
     * @@param l_completeResponse - (�������X�|���X)<BR>
     * @@param l_equityManualUnits - (�����蓮����Unit[])<BR>
     * @@return WEB3EquityManualCompleteResponse
     * @@roseuid 432175DD01A0
     */
    protected WEB3EquityManualCompleteResponse setCompleteResponseProperty(
        WEB3EquityManualCompleteResponse l_completeResponse, 
        WEB3EquityManualUnit[] l_equityManualUnits)
    {
        final String STR_METHOD_NAME = "setCompleteResponseProperty(" +
            "WEB3EquityManualCompleteResponse, WEB3EquityManualUnit[])";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�p�����[�^�D�������X�|���X�Ƀv���p�e�B���Z�b�g����B
        l_completeResponse.manualUnits = l_equityManualUnits;
        l_completeResponse.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
        
        log.exiting(STR_METHOD_NAME);
        //�Q�j�@@�������X�|���X��Ԃ�
        return l_completeResponse;
    }
    /**
     * (set����J�����_�R���e�L�X�g)
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     *<BR>
     *�P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     *�@@�|�p�����[�^.�����f�[�^�̓��e��������ԃR���e�L�X�g�� <BR>
     *�@@�@@�@@�v���p�e�B���Z�b�g����B <BR>
     *<BR>
     *�@@����J�����_�R���e�L�X�g.�،���ЃR�[�h =  OpLoginSecurityService���ҏW<BR>
     *�@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW <BR>
     *�@@����J�����_�R���e�L�X�g.�s��R�[�h = �p�����[�^.�����蓮����Unit.�s��R�[�h<BR>
     *�@@����J�����_�R���e�L�X�g.��t���ԋ敪 = (*1) <BR>
     *�@@����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h <BR>
     *�@@����J�����_�R���e�L�X�g.������t���i = null<BR>
     *�@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = null<BR>
     *<BR>
     *�@@(*1)��t���ԋ敪<BR>
     *�@@�@@�@@�p�����[�^.�����蓮����Unit.����敪���A<BR>
     *�@@�@@�@@�@@�E"��������"�܂���"���n����"�̏ꍇ�A<BR>
     *�@@�@@�@@�@@�@@�@@"�����E���n"���Z�b�g����B<BR>
     *�@@�@@�@@�@@�E��L�ȊO�̏ꍇ�A<BR>
     *�@@�@@�@@�@@�@@�@@"�����E�M�p"���Z�b�g����B<BR>
     *<BR>
     *�@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ� <BR>
     *�@@�@@�@@������ԃR���e�L�X�g���Z�b�g����B <BR>
     *�ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B    <BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * @@param l_equityManualUnit - (�����蓮����Unit)<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 432175DD01A0
     */
   protected void setTradingClendarContext(WEB3EquityManualUnit l_equityManualUnit) 
       throws WEB3BaseException
   {
       final String STR_METHOD_NAME = "setTradingClendarContext(WEB3EquityManualUnit)";
       log.entering(STR_METHOD_NAME);
       
       if (l_equityManualUnit == null)
       {
           log.debug("�p�����[�^�l�s���B");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
       }

       FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
       
       WEB3GentradeAccountManager l_accountManager = 
           (WEB3GentradeAccountManager) l_finApp.getAccountManager();
       
       OpLoginSecurityService l_opLoginSecurityService =
           (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
       
       MainAccount l_mainAccount = null;
       
       try
       {
           long l_lngAccountId = l_opLoginSecurityService.getAccountId();
           l_mainAccount = 
               l_accountManager.getMainAccount(l_lngAccountId);
       }       
       catch (NotFoundException l_ex)
       {
           log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80005,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               l_ex.getMessage(),
               l_ex);
       }
           
       String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
       String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();       
   
       //�P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B      
       
       WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
       
       //����J�����_�R���e�L�X�g.�،���ЃR�[�h =  OpLoginSecurityService���ҏW
       l_context.setInstitutionCode(l_strInstitutionCode);
   
       //����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW 
       l_context.setBranchCode(l_strBranchCode);
   
       //�@@����J�����_�R���e�L�X�g.�s��R�[�h = �p�����[�^.�����蓮����Unit.�s��R�[�h 
       l_context.setMarketCode(l_equityManualUnit.marketCode);
   
       //�@@����J�����_�R���e�L�X�g.��t���ԋ敪 = (*1) 
       //�@@(*1)��t���ԋ敪
       //�@@�p�����[�^.�����蓮����Unit.����敪���A
       //�@@�@@�E"��������"�܂���"���n����"�̏ꍇ�A
       //�@@�@@�@@�@@"�����E���n"���Z�b�g����B
       //�@@�@@�E��L�ȊO�̏ꍇ�A
       //�@@�@@�@@�@@"�����E�M�p"���Z�b�g����B
       
       if (WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.SWAP_MARGIN_LONG).equals(
               l_equityManualUnit.tradingType) ||
           WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.SWAP_MARGIN_SHORT).equals(
               l_equityManualUnit.tradingType))
       {
           l_context.setTradingTimeType(WEB3TradingTimeTypeDef.SWAP);
       }
       //  �E�����J�e�S������L�ȊO�̏ꍇ�A 
       //�@@�@@�@@�h�����E�M�p�h���Z�b�g����B
       else
       {
           l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
       }      
       
       //�@@����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h 
       l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
       
       //�@@����J�����_�R���e�L�X�g.������t���i = null
       l_context.setOrderAcceptProduct(null);       
       
       //�@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = null              
       l_context.setOrderAcceptTransaction(null);
       
       //�@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ� 
       //�@@�@@������ԃR���e�L�X�g���Z�b�g����B 
       //    �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
       
       ThreadLocalSystemAttributesRegistry.setAttribute(
           WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
           l_context);
       
       // �Q�j�@@��t�����A���t���[�����Z�b�g����B  
       //�@@�|������ԊǗ�.setTimestamp()���R�[������B
       WEB3GentradeTradingTimeManagement.setTimestamp();//WEB3BaseException
     
       log.exiting(STR_METHOD_NAME);
    }
   
}
@
