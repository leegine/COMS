head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.45.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiServiceBidPriceUpdateHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V�n���h��(WEB3AdminSrvRegiServiceBidPriceUpdateHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 ���w�� �V�K�쐬
*/
package webbroker3.srvregi.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.srvregi.message.WEB3AdminSrvRegiSuccBidCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiSuccBidCompleteResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiSuccBidConfirmRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiSuccBidConfirmResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiServiceBidPriceUpdateService;
import webbroker3.util.WEB3LogUtility;


/**
 * (�T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V�n���h��)<BR>
 * �T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V�n���h���N���X<BR> 
 *                                                               
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AdminSrvRegiServiceBidPriceUpdateHandler implements MessageHandler 
{
    /**
     * Logger
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminSrvRegiServiceBidPriceUpdateHandler.class);
    
    /**
     * @@roseuid 416F415D0119
     */
    public WEB3AdminSrvRegiServiceBidPriceUpdateHandler() 
    {
     
    }
    
    /**
     * (confirm���D�z�X�V)<BR>
     * �T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V�R���������s���B<BR>
     * <BR>
     * �T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V�T�[�r�X���擾���A<BR>
     * execute( )���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V���N�G�X�g�@@�I�u�W�F�N�g<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiSuccBidConfirmResponse
     * @@roseuid 40F5E98A01E5
     */
    public WEB3AdminSrvRegiSuccBidConfirmResponse confirmBidPriceUpdate(WEB3AdminSrvRegiSuccBidConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " confirmBidPriceUpdate(WEB3AdminSrvRegiSuccBidConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminSrvRegiSuccBidConfirmResponse l_response = null;
        WEB3AdminSrvRegiServiceBidPriceUpdateService l_service = null;
        
        try
        {
            l_service = (WEB3AdminSrvRegiServiceBidPriceUpdateService)Services.getService(WEB3AdminSrvRegiServiceBidPriceUpdateService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminSrvRegiSuccBidConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " �T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            l_response = (WEB3AdminSrvRegiSuccBidConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminSrvRegiSuccBidConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " �T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (complete���D�z�X�V)<BR>
     * �T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V�������s���B<BR>
     * <BR>
     * �T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V�T�[�r�X���擾���A<BR>
     * execute( )���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V���N�G�X�g�@@�I�u�W�F�N�g<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiSuccBidCompleteResponse
     * @@roseuid 40F5E9C5039B
     */
    public WEB3AdminSrvRegiSuccBidCompleteResponse completeBidPriceUpdate(WEB3AdminSrvRegiSuccBidCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " completeBidPriceUpdate(WEB3AdminSrvRegiSuccBidCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminSrvRegiSuccBidCompleteResponse l_response = null;
        WEB3AdminSrvRegiServiceBidPriceUpdateService l_service = null;
        
        try
        {
            l_service = (WEB3AdminSrvRegiServiceBidPriceUpdateService)Services.getService(WEB3AdminSrvRegiServiceBidPriceUpdateService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminSrvRegiSuccBidCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " �T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            l_response = (WEB3AdminSrvRegiSuccBidCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminSrvRegiSuccBidCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " �T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V�T�[�r�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
             
        return l_response;
    }
}
@
