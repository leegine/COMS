head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.44.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiAccountDataUlStateInquiryHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��Ҍڋq�f�[�^UL�󋵏Ɖ�n���h��(WEB3AdminSrvRegiAccountDataUlStateInquiryHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/27 �A�C��(���u) �V�K�쐬
*/

package webbroker3.srvregi.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadStateRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadStateResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountDataUlStateInquiryService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^UL�󋵏Ɖ�n���h��)<BR>
 * �T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�A�b�v���[�h�󋵏Ɖ�n���h���N���X<BR>
 *  
 * @@author �A�C��
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiAccountDataUlStateInquiryHandler implements MessageHandler 
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiAccountDataUlStateInquiryHandler.class);
    
    /**
     * @@roseuid 416F415C0148
     */
    public WEB3AdminSrvRegiAccountDataUlStateInquiryHandler() 
    {
     
    }
    
    /**
     * (�ڋq�f�[�^UL�󋵏Ɖ�N�G�X�g)<BR>
     * �ڋq�f�[�^�A�b�v���[�h�󋵏Ɖ�����s���B<BR>
     * <BR>
     * �ڋq�f�[�^UL�󋵏Ɖ�T�[�r�X���擾���Aexecute( )���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p�Ǘ��Ҍڋq�f�[�^UL�󋵏Ɖ�N�G�X�g<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiUploadStateResponse
     * @@roseuid 411AC737028E
     */
    public WEB3AdminSrvRegiUploadStateResponse accountDataUploadStateInqueryRequest(WEB3AdminSrvRegiUploadStateRequest l_request) 
    {
        final String STR_METHOD_NAME = " accountUploadScreenIndication(WEB3AdminSrvRegiUploadInputRequest )";    
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminSrvRegiUploadStateResponse l_response = null;
        WEB3AdminSrvRegiAccountDataUlStateInquiryService l_service = null;        
        try
        {
            //get service
            l_service = 
                (WEB3AdminSrvRegiAccountDataUlStateInquiryService)Services.getService(
                    WEB3AdminSrvRegiAccountDataUlStateInquiryService.class);
        }
        catch (Exception l_e)
        {
            
            l_response = (WEB3AdminSrvRegiUploadStateResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^UL�󋵏Ɖ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_e);
            return l_response;                  
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminSrvRegiUploadStateResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_e)
        {
            l_response = (WEB3AdminSrvRegiUploadStateResponse)l_request.createResponse();
            l_response.errorInfo = l_e.getErrorInfo();
            log.error(l_request, "�ڋq�f�[�^UL�󋵏Ɖ�N�G�X�g�Ɏ��s���܂����B", l_e);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
