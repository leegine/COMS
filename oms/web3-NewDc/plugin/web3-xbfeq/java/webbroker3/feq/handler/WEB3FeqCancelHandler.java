head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqCancelHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O����������n���h��(WEB3FeqCancelHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ��O�� (���u) �V�K�쐬       
                 : 2005/08/03 �A�C��(���u) ���r���[       
*/

package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3FeqCancelCompleteRequest;
import webbroker3.feq.message.WEB3FeqCancelCompleteResponse;
import webbroker3.feq.message.WEB3FeqCancelConfirmRequest;
import webbroker3.feq.message.WEB3FeqCancelConfirmResponse;
import webbroker3.feq.service.delegate.WEB3FeqCancelService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O����������n���h��)<BR>
 * �O����������n���h���N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3FeqCancelHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqCancelHandler.class);
    
    /**
     * @@roseuid 42D0DA190109
     */
    public WEB3FeqCancelHandler() 
    {
     
    }
    
    /**
     * (validate����)<BR>
     * ��������̊m�F�������s���B<BR>
     * <BR>
     * �O����������T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * 
     * @@return WEB3FeqCancelConfirmResponse
     * @@roseuid 429ADDB40239
     */
    public WEB3FeqCancelConfirmResponse validateOrder(
            WEB3FeqCancelConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "validateOrder(WEB3FeqCancelConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //�O����������T�[�r�X�C���^�[�t�F�C�X
        WEB3FeqCancelService l_service = null;
         
        //�O����������m�F���X�|���X
        WEB3FeqCancelConfirmResponse l_response = null;
         
        try
        {
            l_service = 
                (WEB3FeqCancelService) Services.getService(
                        WEB3FeqCancelService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3FeqCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�O����������T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FeqCancelConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "��������̊m�F�����Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit����)<BR>
     * ��������̍X�V�������s���B<BR>
     * <BR>
     * �O����������T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * 
     * @@return WEB3FeqCancelCompleteResponse
     * @@roseuid 429ADDB40258
     */
    public WEB3FeqCancelCompleteResponse submitOrder(
            WEB3FeqCancelCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = 
            "submitOrder(WEB3FeqCancelCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //�O����������T�[�r�X�C���^�[�t�F�C�X
        WEB3FeqCancelService l_service = null;
         
        //�O����������������X�|���X
        WEB3FeqCancelCompleteResponse l_response = null;
         
        try
        {
            l_service = 
                (WEB3FeqCancelService) Services.getService(
                        WEB3FeqCancelService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3FeqCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�O����������T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FeqCancelCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "��������̍X�V�����Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
