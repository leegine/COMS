head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.39.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqUploadErrCancelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO��������������۰�޴װ�����T�[�r�XImpl(WEB3AdminFeqUploadErrCancelServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/24 �s�p (���u) �V�K�쐬
                 : 2005/08/01 ��O��(���u) ���r���[   
*/

package webbroker3.feq.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqExecuteResultInputCSV;
import webbroker3.feq.WEB3FeqOrderAcceptResultUploadCSV;
import webbroker3.feq.define.WEB3FeqErrorCancelTargetDivDef;
import webbroker3.feq.message.WEB3AdminFeqUploadErrCancelCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqUploadErrCancelCompleteResponse;
import webbroker3.feq.message.WEB3AdminFeqUploadErrCancelConfirmRequest;
import webbroker3.feq.message.WEB3AdminFeqUploadErrCancelConfirmResponse;
import webbroker3.feq.message.WEB3AdminFeqUploadErrCancelInputRequest;
import webbroker3.feq.message.WEB3AdminFeqUploadErrCancelInputResponse;
import webbroker3.feq.service.delegate.WEB3AdminFeqUploadErrCancelService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҊO��������������۰�޴װ�����T�[�r�XImpl)<BR>
 * �Ǘ��ҊO��������������۰�މ����T�[�r�X�����N���X<BR>
 *    
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3AdminFeqUploadErrCancelServiceImpl implements WEB3AdminFeqUploadErrCancelService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqUploadErrCancelServiceImpl.class);
        
    /**
     * @@roseuid 42CE39F202DE
     */
    public WEB3AdminFeqUploadErrCancelServiceImpl() 
    {
     
    }
    
    /**
     * �O�����������A�b�v���[�h�G���[�������������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɑΉ����郁�\�b�h���R�[������B<BR>
     * <BR>
     * �|get���͉��()<BR>
     * �|validate����()<BR>
     * �|submit����()<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 42BBB75F032B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest) ";
        log.entering(STR_METHOD_NAME);
        
        
        if (l_request == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminFeqUploadErrCancelInputRequest)
        {
            //get���͉��()
            l_response = 
                this.getInputScreen((WEB3AdminFeqUploadErrCancelInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqUploadErrCancelConfirmRequest)
        {
            //validate����()
            l_response = 
                this.validateRelease((WEB3AdminFeqUploadErrCancelConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqUploadErrCancelCompleteRequest)
        {
            //submit����()
            l_response = 
                this.submitRelease((WEB3AdminFeqUploadErrCancelCompleteRequest)l_request);
        }
        else
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get���͉��)<BR>
     * ���͉�ʕ\�����������{����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i(��)�װ�����jget���͉�ʁv �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҊO��������������۰�޴װ�������̓��N�G�X�g<BR>
     * @@return webbroker3.feq.message.WEB3AdminFeqUploadErrCancelInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 42BBB6760108
     */
    protected WEB3AdminFeqUploadErrCancelInputResponse getInputScreen(
        WEB3AdminFeqUploadErrCancelInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminFeqUploadErrCancelInputRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1:getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();        
        if (l_admin == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�Ǘ��҂̃��O�C����񂪑��݂��Ȃ��B");
        }
        
        //1.2:validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);
        
        //1.3:createResponse( )
        WEB3AdminFeqUploadErrCancelInputResponse l_response = 
            (WEB3AdminFeqUploadErrCancelInputResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (validate����)<BR>
     * �m�F��ʕ\�����������{����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i(��)�װ�����jvalidate�����v �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҊO��������������۰�޴װ�����m�F���N�G�X�g<BR>
     * @@return webbroker3.feq.message.WEB3AdminFeqUploadErrCancelConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 42BBB676010A
     */
    protected WEB3AdminFeqUploadErrCancelConfirmResponse validateRelease(
        WEB3AdminFeqUploadErrCancelConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateRelease(WEB3AdminFeqUploadErrCancelConfirmRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1:validate()
        l_request.validate();
        
        //1.2:getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();        
        if (l_admin == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�Ǘ��҂̃��O�C����񂪑��݂��Ȃ��B");
        }
        
        //1.3:validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);
        
        //1.4:createResponse( )
        WEB3AdminFeqUploadErrCancelConfirmResponse l_response = 
            (WEB3AdminFeqUploadErrCancelConfirmResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (submit����)<BR>
     * ��������۰�މ������������{����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i(��)�װ�����jsubmit�����v �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҊO��������������۰�޴װ�����������N�G�X�g<BR>
     * @@return webbroker3.feq.message.WEB3AdminFeqUploadErrCancelCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 42BBB676010C
     */
    protected WEB3AdminFeqUploadErrCancelCompleteResponse submitRelease(
        WEB3AdminFeqUploadErrCancelCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitRelease(WEB3AdminFeqUploadErrCancelCompleteRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1:validate()
        l_request.validate();
        
        //1.2:getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();        
        if (l_admin == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�Ǘ��҂̃��O�C����񂪑��݂��Ȃ��B");
        }
        
        //1.3:validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);
        
        //1.4:validate����p�X���[�h(String)
        l_admin.validateTradingPassword(l_request.password);
        
        //1.5:(*) ������t�i���N�G�X�g�f�[�^.�G���[�����Ώۋ@@�\�敪 == �h������t�h�j�̏ꍇ
        if (WEB3FeqErrorCancelTargetDivDef.ORDER_ACCEPT.equals(l_request.errorCancelTargetDiv))
        {
            //1.5.1:saveAll�A�b�v���[�h���~( )
            WEB3FeqOrderAcceptResultUploadCSV l_csv = new WEB3FeqOrderAcceptResultUploadCSV();
            l_csv.saveAllUploadStop();
        }
        
        //1.6:(*) ���i���N�G�X�g�f�[�^.�G���[�����Ώۋ@@�\�敪 == �h���h�j�̏ꍇ
        if (WEB3FeqErrorCancelTargetDivDef.EXECUTED.equals(l_request.errorCancelTargetDiv))
        {
            //1.6.1:saveAll�A�b�v���[�h���~( )
            WEB3FeqExecuteResultInputCSV l_csv = new WEB3FeqExecuteResultInputCSV();
            l_csv.saveAllUploadStop();
        }
        
        //1.7:createResponse( )
        WEB3AdminFeqUploadErrCancelCompleteResponse l_response = 
            (WEB3AdminFeqUploadErrCancelCompleteResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
}
@
