head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�����������n���h��(WEB3FeqChangeHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ��O�� (���u) �V�K�쐬       
                 : 2005/08/03 �A�C��(���u) ���r���[       
*/

package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3FeqChangeCompleteRequest;
import webbroker3.feq.message.WEB3FeqChangeCompleteResponse;
import webbroker3.feq.message.WEB3FeqChangeConfirmRequest;
import webbroker3.feq.message.WEB3FeqChangeConfirmResponse;
import webbroker3.feq.message.WEB3FeqChangeInputRequest;
import webbroker3.feq.message.WEB3FeqChangeInputResponse;
import webbroker3.feq.service.delegate.WEB3FeqChangeService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O�����������n���h��)<BR>
 * �O�����������n���h���N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3FeqChangeHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqChangeHandler.class);
    
    /**
     * @@roseuid 42D0DA1902FD
     */
    public WEB3FeqChangeHandler() 
    {
     
    }
    
    /**
     * (get���͉��)<BR>
     * ���͉�ʂ̕\���ɕK�v�ȃf�[�^���擾����B<BR>
     * <BR>
     * �O�����������T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3FeqChangeInputResponse
     * @@roseuid 4295F0AF01F3
     */
    public WEB3FeqChangeInputResponse getInputScreen(
            WEB3FeqChangeInputRequest l_request)
    {
        final String STR_METHOD_NAME = 
            "getInputScreen(WEB3FeqChangeInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //�O�����������T�[�r�X�C���^�[�t�F�C�X
        WEB3FeqChangeService l_service = null;
         
        //�O�������������̓��X�|���X
        WEB3FeqChangeInputResponse l_response = null;
         
        try
        {
            l_service = 
                (WEB3FeqChangeService) Services.getService(
                        WEB3FeqChangeService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3FeqChangeInputResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�O�����������T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FeqChangeInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqChangeInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "���͉�ʂ̕\�������Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate����)<BR>
     * ���������̊m�F�������s���B<BR>
     * <BR>
     * �O�����������T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3FeqChangeConfirmResponse
     * @@roseuid 4295F0AF01F5
     */
    public WEB3FeqChangeConfirmResponse validateOrder(
            WEB3FeqChangeConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = 
            "validateOrder(WEB3FeqChangeConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //�O�����������T�[�r�X�C���^�[�t�F�C�X
        WEB3FeqChangeService l_service = null;
         
        //�O�����������m�F���X�|���X
        WEB3FeqChangeConfirmResponse l_response = null;
         
        try
        {
            l_service = 
                (WEB3FeqChangeService) Services.getService(
                        WEB3FeqChangeService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3FeqChangeConfirmResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�O�����������T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FeqChangeConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqChangeConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "���������̊m�F�����Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit����)<BR>
     * ���������̍X�V�������s���B<BR>
     * <BR>
     * �O�����������T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3FeqChangeCompleteResponse
     * @@roseuid 4295F0AF01F7
     */
    public WEB3FeqChangeCompleteResponse submitOrder(
            WEB3FeqChangeCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = 
            "submitOrder(WEB3FeqChangeCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //�O�����������T�[�r�X�C���^�[�t�F�C�X
        WEB3FeqChangeService l_service = null;
         
        //�O�����������������X�|���X
        WEB3FeqChangeCompleteResponse l_response = null;
         
        try
        {
            l_service = 
                (WEB3FeqChangeService) Services.getService(
                        WEB3FeqChangeService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3FeqChangeCompleteResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�O�����������T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FeqChangeCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqChangeCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "���������̍X�V�����Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
