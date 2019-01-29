head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.45.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiServiceBidPriceUpdateInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V���̓n���h��(WEB3AdminSrvRegiServiceBidPriceUpdateInputHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 ���w�� �V�K�쐬
*/
package webbroker3.srvregi.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.srvregi.message.WEB3AdminSrvRegiSuccBidInputRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiSuccBidInputResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiServiceBidPriceUpdateInputService;
import webbroker3.util.WEB3LogUtility;


/**
 * (�T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V���̓n���h��)<BR>
 * �T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V���̓n���h���N���X<BR> 
 *                                                               
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AdminSrvRegiServiceBidPriceUpdateInputHandler implements MessageHandler 
{
    /**
     * Logger
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiServiceBidPriceUpdateInputHandler.class);
    
    /**
     * @@roseuid 416F415D01B5
     */
    public WEB3AdminSrvRegiServiceBidPriceUpdateInputHandler() 
    {
     
    }
    
    /**
     * (���D�z�X�V���̓��N�G�X�g)<BR>
     * �T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V���͏Ɖ�����s���B<BR>
     * <BR>
     * �T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V���̓T�[�r�X���擾���A<BR>
     * execute( )���\�b�h���R�[������B<BR>
     * @@roseuid 40F5EBC30291
     */
    public WEB3AdminSrvRegiSuccBidInputResponse bidPriceUpdateInputRequest(WEB3AdminSrvRegiSuccBidInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " bidPriceUpdateInputRequest(WEB3AdminSrvRegiSuccBidInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminSrvRegiSuccBidInputResponse l_response = null;
        WEB3AdminSrvRegiServiceBidPriceUpdateInputService l_service = null;
        
        try
        {
            l_service = (WEB3AdminSrvRegiServiceBidPriceUpdateInputService)
                Services.getService(WEB3AdminSrvRegiServiceBidPriceUpdateInputService.class);//Exception
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminSrvRegiSuccBidInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " �T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V���͏Ɖ�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            l_response = (WEB3AdminSrvRegiSuccBidInputResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminSrvRegiSuccBidInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " �T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V���͂Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
     
    }
}
@
