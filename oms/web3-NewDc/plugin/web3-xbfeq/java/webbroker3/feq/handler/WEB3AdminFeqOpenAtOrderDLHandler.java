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
filename	WEB3AdminFeqOpenAtOrderDLHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O��������t����DL�n���h��(WEB3AdminFeqOpenAtOrderDLHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/20 �Ջ`�g (���u) �V�K�쐬
                   2005/08/03 ����(���u) ���r���[     
*/

package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3AdminFeqOpenAtOrderDownloadInputRequest;
import webbroker3.feq.message.WEB3AdminFeqOpenAtOrderDownloadInputResponse;
import webbroker3.feq.message.WEB3AdminFeqOpenAtOrderDownloadRequest;
import webbroker3.feq.message.WEB3AdminFeqOpenAtOrderDownloadResponse;
import webbroker3.feq.service.delegate.WEB3AdminFeqOpenAtOrderDLService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O��������t����DL�n���h��)<BR>
 * �O��������t����DL�n���h���N���X<BR>
 *   
 * @@author �Ջ`�g
 * @@version 1.0
 */
public class WEB3AdminFeqOpenAtOrderDLHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminFeqOpenAtOrderDLHandler.class);
    
    /**
     * @@roseuid 42D0DA1600BB
     */
    public WEB3AdminFeqOpenAtOrderDLHandler() 
    {
     
    }
    
    /**
     * (get���͉��)<BR>
     * �O��������t����DL�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOpenAtOrderDownloadInputResponse
     * @@roseuid 421494D4010B
     */
    public WEB3AdminFeqOpenAtOrderDownloadInputResponse getInputScreen(WEB3AdminFeqOpenAtOrderDownloadInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminFeqOpenAtOrderDownloadInputRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "���N�G�X�g��null�ł��B");
        }

        WEB3AdminFeqOpenAtOrderDownloadInputResponse l_response = null;
        WEB3AdminFeqOpenAtOrderDLService l_service = null;

        try
        {            
            //get�O��������t����DL�T�[�r�X
            l_service = (WEB3AdminFeqOpenAtOrderDLService)
                Services.getService(WEB3AdminFeqOpenAtOrderDLService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�O��������t����DL�T�[�r�X���擾�Ɏ��s���܂����B");
            }
            //execute
            l_response = (WEB3AdminFeqOpenAtOrderDownloadInputResponse)l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqOpenAtOrderDownloadInputResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "���͉�ʕ\�����������{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqOpenAtOrderDownloadInputResponse) l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                "���͉�ʕ\�����������{�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�_�E�����[�h�t�@@�C��)<BR>
     * �O��������t����DL�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOpenAtOrderDownloadResponse
     * @@roseuid 4214951C0291
     */
    public WEB3AdminFeqOpenAtOrderDownloadResponse getDownloadFile(WEB3AdminFeqOpenAtOrderDownloadRequest l_request) 
    {
        final String STR_METHOD_NAME = " getDownloadFile(WEB3AdminFeqOpenAtOrderDownloadRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "���N�G�X�g��null�ł��B");
        }

        WEB3AdminFeqOpenAtOrderDownloadResponse l_response = null;
        WEB3AdminFeqOpenAtOrderDLService l_service = null;

        try
        {
            //get�O��������t����DL�T�[�r�X
            l_service = (WEB3AdminFeqOpenAtOrderDLService)
                Services.getService(WEB3AdminFeqOpenAtOrderDLService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�O��������t����DL�T�[�r�X���擾�Ɏ��s���܂����B");
            }
            //execute
            l_response = (WEB3AdminFeqOpenAtOrderDownloadResponse)l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqOpenAtOrderDownloadResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "get�_�E�����[�h�t�@@�C�����������{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqOpenAtOrderDownloadResponse) l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                "get�_�E�����[�h�t�@@�C�����������{�Ɏ��s���܂����B",
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
