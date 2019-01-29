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
filename	WEB3FeqBuyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���������t�n���h��(WEB3FeqBuyHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/15 ���� (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[   
*/

package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3FeqBuyCompleteRequest;
import webbroker3.feq.message.WEB3FeqBuyCompleteResponse;
import webbroker3.feq.message.WEB3FeqBuyConfirmRequest;
import webbroker3.feq.message.WEB3FeqBuyConfirmResponse;
import webbroker3.feq.message.WEB3FeqBuyInputRequest;
import webbroker3.feq.message.WEB3FeqBuyInputResponse;
import webbroker3.feq.message.WEB3FeqBuyProductSelectRequest;
import webbroker3.feq.message.WEB3FeqBuyProductSelectResponse;
import webbroker3.feq.service.delegate.WEB3FeqBuyService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O���������t�n���h��)<BR>
 * �O���������t�n���h���N���X
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FeqBuyHandler implements MessageHandler 
{
   
   /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqBuyHandler.class); 
    
    /**
     * @@roseuid 42D0DA1903B9
     */
    public WEB3FeqBuyHandler() 
    {
     
    }
           
    /**
     * (get���͉��)<BR>
     * ���͉�ʂ̕\���ɕK�v�ȃf�[�^���擾����B<BR>
     * <BR>
     * �O���������t�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return webbroker3.feq.message.WEB3FeqBuyInputResponse
     * @@roseuid 428AF36E0170
     */
    public WEB3FeqBuyInputResponse getInputScreen(WEB3FeqBuyInputRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "getInputScreen(WEB3FeqBuyInputRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        //�O���������t�T�[�r�X�C���^�[�t�F�C�X
        WEB3FeqBuyService l_service = null;
         
        //�O���������t���̓��X�|���X
        WEB3FeqBuyInputResponse l_response = null;
         
        try
        {
            l_service = 
                (WEB3FeqBuyService) Services.getService(
                    WEB3FeqBuyService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3FeqBuyInputResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�O���������t�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FeqBuyInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqBuyInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�O���������t���͂Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate����)<BR>
     * ���t�����̊m�F�������s���B<BR>
     * <BR>
     * �O���������t�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return webbroker3.feq.message.WEB3FeqBuyConfirmResponse
     * @@roseuid 428AF45F021B
     */
    public WEB3FeqBuyConfirmResponse validateOrder(WEB3FeqBuyConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "validateOrder(WEB3FeqBuyConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //�O���������t�T�[�r�X�C���^�[�t�F�C�X
        WEB3FeqBuyService l_service = null;
         
        //�O���������t���̓��X�|���X
        WEB3FeqBuyConfirmResponse l_response = null;
         
        try
        {
            l_service = 
                (WEB3FeqBuyService) Services.getService(
                    WEB3FeqBuyService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3FeqBuyConfirmResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�O���������t�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FeqBuyConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqBuyConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�O���������t�m�F�Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit����)<BR>
     * ���t�����̓o�^�������s���B
     * <BR>
     * �O���������t�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return webbroker3.feq.message.WEB3FeqBuyCompleteResponse
     * @@roseuid 428AF4910008
     */
    public WEB3FeqBuyCompleteResponse submitOrder(WEB3FeqBuyCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "submitOrder(WEB3FeqBuyCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //�O���������t�T�[�r�X�C���^�[�t�F�C�X
        WEB3FeqBuyService l_service = null;
         
        //�O���������t���̓��X�|���X
        WEB3FeqBuyCompleteResponse l_response = null;
         
        try
        {
            l_service = 
                (WEB3FeqBuyService) Services.getService(
                    WEB3FeqBuyService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3FeqBuyCompleteResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�O���������t�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FeqBuyCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqBuyCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�O���������t�����Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�����I�����)<BR>
     * �����I����ʂ̕\���ɕK�v�ȃf�[�^���擾����B<BR>
     * <BR>
     * �O���������t�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3FeqBuyProductSelectResponse
     */
    public WEB3FeqBuyProductSelectResponse getProductSelectScreen(
        WEB3FeqBuyProductSelectRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "getProductSelectScreen(WEB3FeqBuyProductSelectRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3FeqBuyService l_service = null;
        
        WEB3FeqBuyProductSelectResponse l_response = null;
        
        try
        {
            l_service = 
                (WEB3FeqBuyService)Services.getService(
                    WEB3FeqBuyService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3FeqBuyProductSelectResponse)l_request.createResponse();
            l_response.errorInfo =
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�O���������t�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FeqBuyProductSelectResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqBuyProductSelectResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�O���������t�����I���Ɏ��s���܂����B",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
