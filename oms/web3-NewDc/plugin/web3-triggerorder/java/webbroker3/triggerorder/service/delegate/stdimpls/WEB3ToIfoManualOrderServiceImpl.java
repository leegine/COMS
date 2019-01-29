head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.51.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToIfoManualOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�蓮�����T�[�r�XImpl(WEB3ToIfoManualOrderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/17 杊��](���u) �V�K�쐬
                 : 2006/10/30 ������(���u)�@@���f��No.159
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.rlsgateway.define.WEB3RlsNotifyTypeDef;
import webbroker3.triggerorder.define.WEB3ToManualOrderErrorCodeDef;
import webbroker3.triggerorder.message.WEB3FuturesOptionsManualCompleteRequest;
import webbroker3.triggerorder.message.WEB3FuturesOptionsManualCompleteResponse;
import webbroker3.triggerorder.message.WEB3FuturesOptionsManualConfirmRequest;
import webbroker3.triggerorder.message.WEB3FuturesOptionsManualConfirmResponse;
import webbroker3.triggerorder.message.WEB3FuturesOptionsManualUnit;
import webbroker3.triggerorder.service.delegate.WEB3ToIfoManualOrderService;
import webbroker3.triggerorder.service.delegate.WEB3ToIfoManualOrderUnitService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�敨OP�蓮�����T�[�r�XImpl)<BR>
 * �敨OP�蓮�����T�[�r�X�����N���X<BR>
 *   
 * @@author 杊��]
 * @@version 1.0
 */
public class WEB3ToIfoManualOrderServiceImpl 
    extends WEB3ClientRequestService 
    implements WEB3ToIfoManualOrderService 
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToIfoManualOrderServiceImpl.class);

    /**
     * @@roseuid 43F4933F007D
     */
    public WEB3ToIfoManualOrderServiceImpl() 
    {
     
    }
    
    /**
     * �敨OP�蓮�����T�[�r�X���������{����B<BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Avalidate�蓮����()�܂��́A<BR>
     * submit�蓮����()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 43E9AFEB014B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l�s���B");
        }

        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3FuturesOptionsManualConfirmRequest)
        {
            l_response = this.validateManualOrder((WEB3FuturesOptionsManualConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3FuturesOptionsManualCompleteRequest)
        {
            l_response = this.submitManualOrder((WEB3FuturesOptionsManualCompleteRequest) l_request);
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�蓮����)<BR>
     * �V�[�P���X�}<BR>
     * �u(�敨OP�蓮�����T�[�r�X)validate�蓮�����v<BR>
     * �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �敨OP�蓮�����m�F���N�G�X�g<BR>
     * @@return WEB3FuturesOptionsManualConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 43EAD39A00A5
     */
    public WEB3FuturesOptionsManualConfirmResponse validateManualOrder(
        WEB3FuturesOptionsManualConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateManualOrder(WEB3FuturesOptionsManualConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();
        
        //1.2 �i����t���[�F�Ǘ��҂̏ꍇ�j
        //���Ǘ��҂̏ꍇ(OpLoginSecurityService.getAccountId��
        //�߂�l == 0)�̏ꍇ
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
        if (l_opLoginSec.getAccountId() == 0)
        {
            //1.2.1 getInstanceFrom���O�C�����( )
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
            
            //1.2.2 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
            //[����]  
            //�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�敨OP(�g���K�[�����蓮����)  
            //is�X�V�F�@@true(�X�V����)  
            l_admin.validateAuthority(WEB3TransactionCategoryDef.TRIGGER_ORDER_HANDLER_SEND, true);
            
            //1.2.3 validate���X����(���X�R�[�h : String[])
            //[����] 
            //���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h
            l_admin.validateBranchPermission(l_request.branchCode);
            
        }

        //1.3 ArrayList( )
        List l_lisManuals = new ArrayList();
        
        //1.4 (*)���N�G�X�g.����ID[]�̗v�f����loop����
        int l_intCount = 0;
        int l_intOrderIdLength = l_request.orderId.length;
        for (int i = 0; i < l_intOrderIdLength; i++)
        {
            //1.4.1 exec�蓮����(String, String, String, boolean)
            //[����]
            //�����^�C�v�F�@@���N�G�X�g�f�[�^.�����^�C�v
            //����������ʁF�@@���N�G�X�g�f�[�^.�����������
            //�����h�c�F�@@�����h�c[n�Ԗڂ̗v�f]
            //is�X�V�F�@@false
            //�����҃��O�C��ID�F�@@null
            //�ʒm�o�H�F�@@null
            WEB3ToIfoManualOrderUnitService l_unitService =
                (WEB3ToIfoManualOrderUnitService) Services.getService(WEB3ToIfoManualOrderUnitService.class);
            
            WEB3FuturesOptionsManualUnit l_manualUnit = l_unitService.execManualOrder(
                l_request.productType,
                l_request.triggerOrderType,
                l_request.orderId[i],
                false,
                null,
                null);
            //exec�蓮�����̖߂�l.�蓮�����G���[�R�[�h��"����"�̖߂�l
            if (WEB3ToManualOrderErrorCodeDef.NORMAL.equals(l_manualUnit.manualOrderErrorCode))
            {
                l_intCount++;
            }
            
            //1.4.2 add(arg0 : Object)
            l_lisManuals.add(l_manualUnit);
        }
        
        //exec�蓮�����̖߂�l.�蓮�����G���[�R�[�h��
        //"����"�̖߂�l��1�������������ꍇ�A
        //�u�蓮�����Ώے����Ȃ��v�̗�O��throw����B        
        if (l_intCount < 1)
        {
            log.debug("�蓮�����Ώے����Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02393, 
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�蓮�����Ώے����Ȃ��B");
        }

        //1.5 toArray( )
        //�敨OP�蓮����Unit�̔z��𐶐�����B
        WEB3FuturesOptionsManualUnit[] l_manualUnits = new WEB3FuturesOptionsManualUnit[l_lisManuals.size()]; 
        l_lisManuals.toArray(l_manualUnits);
        
        //1.6 createResponse( )
        WEB3FuturesOptionsManualConfirmResponse l_response =
            (WEB3FuturesOptionsManualConfirmResponse) l_request.createResponse();
        
        //1.7 (*)�v���p�e�B�Z�b�g
        //�敨OP�蓮����Unit���敨OP�蓮����Unit�̔z��
        l_response.manualUnits = l_manualUnits;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit�蓮����)<BR>
     * �V�[�P���X�}<BR>
     * �u(�敨OP�蓮�����T�[�r�X)submit�蓮�����v<BR>
     * �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3FuturesOptionsManualCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 43EC92C20284
     */
    public WEB3FuturesOptionsManualCompleteResponse submitManualOrder(WEB3FuturesOptionsManualCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateManualOrder(WEB3FuturesOptionsManualCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();
        
        //�ʒm�o�H
        String l_strSubmitnotifyType = "";
        
        //1.2 �i����t���[�F �Ǘ��҂̏ꍇ�j
        //���Ǘ��҂̏ꍇ(OpLoginSecurityService.getAccountId�̖߂�l == 0)�̏ꍇ
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
        if (l_opLoginSec.getAccountId() == 0)
        {
            //1.2.1 getInstanceFrom���O�C�����( )
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
            
            //1.2.2 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
            //[����]  
            //�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�敨OP(�g���K�[�����蓮����)  
            //is�X�V�F�@@true(�X�V����)  
            l_admin.validateAuthority(WEB3TransactionCategoryDef.TRIGGER_ORDER_HANDLER_SEND, true);
            
            //1.2.3 validate���X����(���X�R�[�h : String[])
            //[����] 
            //���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h
            l_admin.validateBranchPermission(l_request.branchCode);
            
            //1.2.4 validate����p�X���[�h(�p�X���[�h : String)
            l_admin.validateTradingPassword(l_request.password);
            //�ʒm�o�H�F�@@�Ǘ���
            l_strSubmitnotifyType = WEB3RlsNotifyTypeDef.MANUAL_ADMIN;
            
        }
        
        //1.3 get�㗝���͎�( )
        Trader l_trader = this.getTrader();
        //�㗝���͂̏ꍇ
        if (l_trader != null)
        {
            //�ʒm�o�H�F�@@�I�y���[�^
            l_strSubmitnotifyType = WEB3RlsNotifyTypeDef.MANUAL_OPERATOR;
        }
        
        //1.4 �i����t���[�F �ڋq�̏ꍇ�j
        //�i����t���[�F �ڋq�̏ꍇ�i�Ǘ��҂łȂ� 
        //&& �㗝���͎҃I�u�W�F�N�g���擾�ł��Ȃ��ꍇ�j�j
        if (l_opLoginSec.getAccountId() != 0 && l_trader == null)
        {
            //1.4.1 get����()
            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)this.getMainAccount();

            //1.4.2 validate����p�X���[�h(�ڋq : �ڋq, �p�X���[�h : String)
            //[����]
            //�ڋq�F�@@getMainAccount()�̖߂�l
            //�p�X���[�h�F�@@���N�G�X�g.�Ïؔԍ�
            WEB3GentradeOrderValidator l_orderValidator = 
                (WEB3GentradeOrderValidator) GtlUtils.getCommonOrderValidator();
            OrderValidationResult l_orderValidationResult =
                l_orderValidator.validateTradingPassword(l_mainAccount, l_request.password);
            
            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq���O�C���F����p�X���[�h�`�F�b�N�G���[");
            }
            //�ʒm�o�H�F�@@�ڋq
            l_strSubmitnotifyType = WEB3RlsNotifyTypeDef.MANUAL_USER;
        }
        
        //1.5 ArrayList( )
        List l_lisManuals = new ArrayList();
        
        //1.6 (*)���N�G�X�g.����ID[]�̗v�f����loop����
        //�����N�G�X�g.����ID[]�̗v�f����loop���A
        //�@@exec�蓮�����̖߂�l.�蓮�����G���[�R�[�h��
        //�@@"����"�̖߂�l��1�������������ꍇ�A
        //�@@�u�蓮�����Ώے����Ȃ��v�̗�O��throw����B
        int l_intCount = 0;
        int l_intOrderIdLength = l_request.orderId.length;

        //�����҃��O�C��ID���擾����B
        Long l_lngSubmitterLoginId = new Long(l_opLoginSec.getLoginId());
        
        for (int i = 0; i < l_intOrderIdLength; i++)
        {
            //1.6.1 exec�蓮����(String, String, String, boolean)
            //[����]
            //�����^�C�v�F�@@���N�G�X�g�f�[�^.�����^�C�v
            //����������ʁF�@@���N�G�X�g�f�[�^.�����������
            //�����h�c�F�@@�����h�c[n�Ԗڂ̗v�f]
            //is�X�V�F�@@true
            //�����҃��O�C��ID�F�@@OploginSecurityService.getLoginId()�̖߂�l
            //�ʒm�o�H�F�@@�Ǘ��� or �I�y���[�^ or �ڋq
            WEB3ToIfoManualOrderUnitService l_unitService =
                (WEB3ToIfoManualOrderUnitService) Services.getService(WEB3ToIfoManualOrderUnitService.class);
            
            WEB3FuturesOptionsManualUnit l_manualUnit = l_unitService.execManualOrder(
                l_request.productType,
                l_request.triggerOrderType,
                l_request.orderId[i],
                true,
                l_lngSubmitterLoginId,
                l_strSubmitnotifyType);
            
            //exec�蓮�����̖߂�l.�蓮�����G���[�R�[�h��"����"�̖߂�l
            if (WEB3ToManualOrderErrorCodeDef.NORMAL.equals(l_manualUnit.manualOrderErrorCode))
            {
                l_intCount++;
            }
            
            //1.6.2 add(arg0 : Object)
            l_lisManuals.add(l_manualUnit);
            
        }
        
        //exec�蓮�����̖߂�l.�蓮�����G���[�R�[�h��
        //"����"�̖߂�l��1�������������ꍇ�A
        //�u�蓮�����Ώے����Ȃ��v�̗�O��throw����B        
        if (l_intCount < 1)
        {
            log.debug("�蓮�����Ώے����Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02393, 
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�蓮�����Ώے����Ȃ��B");
        }

        //1.7 toArray( )
        //�敨OP�蓮����Unit�̔z��𐶐�����B
        WEB3FuturesOptionsManualUnit[] l_manualUnits = new WEB3FuturesOptionsManualUnit[l_lisManuals.size()]; 
        l_lisManuals.toArray(l_manualUnits);
        
        //1.8 createResponse( )
        WEB3FuturesOptionsManualCompleteResponse l_response =
            (WEB3FuturesOptionsManualCompleteResponse) l_request.createResponse();
        
        //1.9 (*)�v���p�e�B�Z�b�g
        //�X�V����      �@@ �� GtlUtils.getSystemTimestamp()
        //�敨OP�蓮����Unit���敨OP�蓮����Unit�̔z��
        l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
        l_response.manualUnits = l_manualUnits;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
