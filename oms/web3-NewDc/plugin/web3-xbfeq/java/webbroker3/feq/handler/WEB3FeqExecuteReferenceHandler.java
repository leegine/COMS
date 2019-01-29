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
filename	WEB3FeqExecuteReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������������Ɖ�n���h��(WEB3FeqExecuteReferenceHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ��O�� (���u) �V�K�쐬       
                 : 2005/08/03 �A�C��(���u) ���r���[       
*/

package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3FeqExecuteDetailsRequest;
import webbroker3.feq.message.WEB3FeqExecuteDetailsResponse;
import webbroker3.feq.message.WEB3FeqExecuteReferenceRequest;
import webbroker3.feq.message.WEB3FeqExecuteReferenceResponse;
import webbroker3.feq.message.WEB3FeqOrderHistoryRequest;
import webbroker3.feq.message.WEB3FeqOrderHistoryResponse;
import webbroker3.feq.service.delegate.WEB3FeqExecuteReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O�������������Ɖ�n���h��)<BR>
 * �O�������������Ɖ�n���h��
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3FeqExecuteReferenceHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqExecuteReferenceHandler.class);
    
    /**
     * @@roseuid 42D0DA190251
     */
    public WEB3FeqExecuteReferenceHandler() 
    {
     
    }
    
    /**
     * (get�������Ɖ�)<BR>
     * �O�������������Ɖ�����s�� <BR>
     * <BR>
     * �O�������������Ɖ�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �O�������������Ɖ�N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.feq.message.WEB3FeqExecuteReferenceResponse
     * @@roseuid 429EA81801B1
     */
    public WEB3FeqExecuteReferenceResponse getOrderExecuteReference(
            WEB3FeqExecuteReferenceRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "getOrderExecuteReference(WEB3FeqExecuteReferenceRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //�O�������������Ɖ�T�[�r�X�C���^�[�t�F�C�X
        WEB3FeqExecuteReferenceService l_service = null;
         
        //�O�������������Ɖ�X�|���X
        WEB3FeqExecuteReferenceResponse l_response = null;
         
        try
        {
            l_service = 
                (WEB3FeqExecuteReferenceService) Services.getService(
                    WEB3FeqExecuteReferenceService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3FeqExecuteReferenceResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�O�������������Ɖ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FeqExecuteReferenceResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqExecuteReferenceResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�O�������������Ɖ���Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�������ڍ�)<BR>
     * �O�������������ڍ׏Ɖ�����s�� <BR>
     * <BR>
     * �O�������������Ɖ�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �O�������������ڍ׃��N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.feq.message.WEB3FeqExecuteDetailsResponse
     * @@roseuid 429EA90E03D4
     */
    public WEB3FeqExecuteDetailsResponse getOrderExecuteDetails(
            WEB3FeqExecuteDetailsRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "getOrderExecuteDetails(WEB3FeqExecuteDetailsRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //�O�������������Ɖ�T�[�r�X�C���^�[�t�F�C�X
        WEB3FeqExecuteReferenceService l_service = null;
         
        //�O�������������ڍ׃��X�|���X
        WEB3FeqExecuteDetailsResponse l_response = null;
         
        try
        {
            l_service = 
                (WEB3FeqExecuteReferenceService) Services.getService(
                    WEB3FeqExecuteReferenceService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3FeqExecuteDetailsResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�O�������������Ɖ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FeqExecuteDetailsResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqExecuteDetailsResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�O�������������ڍ׏Ɖ���Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get������藚��)<BR>
     * �O������������藚���Ɖ�����s�� <BR>
     * <BR>
     * �O�������������Ɖ�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �O������������藚�����N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.feq.message.WEB3FeqOrderHistoryResponse
     * @@roseuid 429EA8B9020F
     */
    public WEB3FeqOrderHistoryResponse getOrderExecuteAction(
            WEB3FeqOrderHistoryRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "getOrderExecuteAction(WEB3FeqOrderHistoryRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //�O�������������Ɖ�T�[�r�X�C���^�[�t�F�C�X
        WEB3FeqExecuteReferenceService l_service = null;
         
        //�O������������藚�����X�|���X
        WEB3FeqOrderHistoryResponse l_response = null;
         
        try
        {
            l_service = 
                (WEB3FeqExecuteReferenceService) Services.getService(
                    WEB3FeqExecuteReferenceService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3FeqOrderHistoryResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�O�������������Ɖ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FeqOrderHistoryResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqOrderHistoryResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�O������������藚���Ɖ���Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
