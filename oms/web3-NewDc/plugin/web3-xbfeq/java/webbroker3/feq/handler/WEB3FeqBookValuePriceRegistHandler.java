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
filename	WEB3FeqBookValuePriceRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������뉿�P���o�^�n���h��(WEB3FeqBookValuePriceRegistHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/15 ���� (���u) �V�K�쐬   
                 : 2005/08/03 �A�C��(���u) ���r���[       
Revesion History : 2008/01/17 �đo�g(���u) ���f��No.377
*/

package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3FeqBookPriceConfirmRequest;
import webbroker3.feq.message.WEB3FeqBookPriceConfirmResponse;
import webbroker3.feq.message.WEB3FeqBookPriceInputRequest;
import webbroker3.feq.message.WEB3FeqBookPriceInputResponse;
import webbroker3.feq.message.WEB3FeqBookPriceRegistRequest;
import webbroker3.feq.message.WEB3FeqBookPriceRegistResponse;
import webbroker3.feq.service.delegate.WEB3FeqBookValuePriceRegistService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O�������뉿�P���o�^�n���h��)<BR>
 * �O�������뉿�P���o�^�n���h���N���X
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FeqBookValuePriceRegistHandler implements MessageHandler 
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqBookValuePriceRegistHandler.class);
    
    /**
     * @@roseuid 42D0DA1A0138
     */
    public WEB3FeqBookValuePriceRegistHandler() 
    {
     
    }
    
    /**
     * (get���͉��)<BR>
     * �O�������뉿�P���o�^���͉�ʕ\���������s���B<BR>
     * <BR>
     * �O�������뉿�P���o�^�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �O�������뉿�P���o�^���̓��N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.feq.message.WEB3FeqBookPriceInputResponse
     * @@roseuid 42A9353502FA
     */
    public WEB3FeqBookPriceInputResponse getInputScreen(WEB3FeqBookPriceInputRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "getInputScreen(WEB3FeqBookPriceInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //�O�������뉿�P���o�^�T�[�r�X�C���^�[�t�F�C�X
        WEB3FeqBookValuePriceRegistService l_service = null;
         
        //�O�����������m�F���X�|���X
        WEB3FeqBookPriceInputResponse l_response = null;
         
        try
        {
            l_service = 
                (WEB3FeqBookValuePriceRegistService) Services.getService(
                    WEB3FeqBookValuePriceRegistService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3FeqBookPriceInputResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�O�������뉿�P���o�^�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FeqBookPriceInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqBookPriceInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�O�������뉿�P���o�^���͏����Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit�뉿�P��)<BR>
     * �O�������뉿�P���o�^�������s���B<BR>
     * <BR>
     * �O�������뉿�P���o�^�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �O�������뉿�P���o�^���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.feq.message.WEB3FeqBookPriceRegistResponse
     * @@roseuid 42A9353B03C5
     */
    public WEB3FeqBookPriceRegistResponse submitBookValuePrice(WEB3FeqBookPriceRegistRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "submitBookValuePrice(WEB3FeqBookPriceRegistRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //�O�������뉿�P���o�^�T�[�r�X�C���^�[�t�F�C�X
        WEB3FeqBookValuePriceRegistService l_service = null;
         
        //�O�������뉿�P���o�^���X�|���X
        WEB3FeqBookPriceRegistResponse l_response = null;
         
        try
        {
            l_service = 
                (WEB3FeqBookValuePriceRegistService) Services.getService(
                    WEB3FeqBookValuePriceRegistService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3FeqBookPriceRegistResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�O�������뉿�P���o�^�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FeqBookPriceRegistResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqBookPriceRegistResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�O�������뉿�P���o�^�����Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�뉿�P��)<BR>
     * �O�������뉿�P���m�F�������s���B<BR>
     * <BR>
     * �O�������뉿�P���o�^�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �O�������뉿�P���o�^�m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3FeqBookPriceConfirmResponse
     */
    public WEB3FeqBookPriceConfirmResponse validateBookValuePrice(
        WEB3FeqBookPriceConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =
            "validateBookValuePrice(WEB3FeqBookPriceConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3FeqBookPriceConfirmResponse l_response = null;
        WEB3FeqBookValuePriceRegistService l_service = null;
        try
        {
            l_service =
                (WEB3FeqBookValuePriceRegistService)Services.getService(
                    WEB3FeqBookValuePriceRegistService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3FeqBookPriceConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�O�������뉿�P���o�^�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3FeqBookPriceConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqBookPriceConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�O�������뉿�P���m�F�����Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3FeqBookPriceConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�O�������뉿�P���m�F�����Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
