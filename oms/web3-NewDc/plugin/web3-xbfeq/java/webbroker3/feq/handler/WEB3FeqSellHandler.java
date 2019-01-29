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
filename	WEB3FeqSellHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���������t�n���h��(WEB3FeqSellHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/15 ���� (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[   
*/

package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3FeqSellCompleteRequest;
import webbroker3.feq.message.WEB3FeqSellCompleteResponse;
import webbroker3.feq.message.WEB3FeqSellConfirmRequest;
import webbroker3.feq.message.WEB3FeqSellConfirmResponse;
import webbroker3.feq.message.WEB3FeqSellInputRequest;
import webbroker3.feq.message.WEB3FeqSellInputResponse;
import webbroker3.feq.service.delegate.WEB3FeqSellService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O���������t�n���h��)<BR>
 * �O���������t�n���h���N���X
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FeqSellHandler implements MessageHandler 
{
   /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqSellHandler.class); 
    
    /**
     * @@roseuid 42D0DA1A008C
     */
    public WEB3FeqSellHandler() 
    {
     
    }
     
    /**
     * (get���͉��)<BR>
     * ���͉�ʂ̕\���ɕK�v�ȃf�[�^���擾����B<BR>
     * <BR>
     * �O���������t�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return webbroker3.feq.message.WEB3FeqSellInputResponse
     * @@roseuid 428AFD8E01BE
     */
    public WEB3FeqSellInputResponse getInputScreen(WEB3FeqSellInputRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "getInputScreen(WEB3FeqSellInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //�O���������t�T�[�r�X�C���^�[�t�F�C�X
        WEB3FeqSellService l_service = null;
         
        //�O���������t���̓��X�|���X
        WEB3FeqSellInputResponse l_response = null;
         
        try
        {
            l_service = 
                (WEB3FeqSellService) Services.getService(
                    WEB3FeqSellService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3FeqSellInputResponse) l_request.createResponse();
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
                (WEB3FeqSellInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqSellInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�O���������t���͏����Ɏ��s���܂����B", 
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
     * @@return webbroker3.feq.message.WEB3FeqSellConfirmResponse
     * @@roseuid 428AFD8E01CD
     */
    public WEB3FeqSellConfirmResponse validateOrder(WEB3FeqSellConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "validateOrder(WEB3FeqSellConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //�O���������t�T�[�r�X�C���^�[�t�F�C�X
        WEB3FeqSellService l_service = null;
         
        //�O���������t�m�F���X�|���X
        WEB3FeqSellConfirmResponse l_response = null;
         
        try
        {
            l_service = 
                (WEB3FeqSellService) Services.getService(
                    WEB3FeqSellService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3FeqSellConfirmResponse) l_request.createResponse();
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
                (WEB3FeqSellConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqSellConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�O���������t�m�F�����Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit����)<BR>
     * ���t�����̓o�^�������s���B<BR>
     * <BR>
     * �O���������t�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * 
     * @@return webbroker3.feq.message.WEB3FeqSellCompleteResponse
     * @@roseuid 428AFD8E01ED
     */
    public WEB3FeqSellCompleteResponse submitOrder(WEB3FeqSellCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "submitOrder(WEB3FeqSellCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //�O���������t�T�[�r�X�C���^�[�t�F�C�X
        WEB3FeqSellService l_service = null;
         
        //�O���������t�������X�|���X
        WEB3FeqSellCompleteResponse l_response = null;
         
        try
        {
            l_service = 
                (WEB3FeqSellService) Services.getService(
                    WEB3FeqSellService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3FeqSellCompleteResponse) l_request.createResponse();
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
                (WEB3FeqSellCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqSellCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�O���������t���������Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
