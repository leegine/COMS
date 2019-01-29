head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.45.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiAccountAppliStateListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��Ҍڋq�\���󋵈ꗗ�n���h��(WEB3AdminSrvRegiAccountAppliStateListHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/01 �A�C��(���u) �V�K�쐬
*/

package webbroker3.srvregi.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.srvregi.message.WEB3AdminSrvRegiStateRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiStateResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountAppliStateListService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�T�[�r�X���p�Ǘ��Ҍڋq�\���󋵈ꗗ�n���h��)<BR>
 * �T�[�r�X���p�Ǘ��Ҍڋq�\���󋵈ꗗ�n���h���N���X<BR>
 *  
 * @@author �A�C��
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiAccountAppliStateListHandler implements MessageHandler 
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiAccountAppliStateListHandler.class);
    
    /**
     * @@roseuid 416F415C0290
     */
    public WEB3AdminSrvRegiAccountAppliStateListHandler() 
    {
     
    }
    
    /**
     * (search�ڋq�\����)<BR>
     * �T�[�r�X���p�Ǘ��Ҍڋq�\���󋵈ꗗ�������s���B<BR>
     * <BR>
     * �T�[�r�X���p�Ǘ��Ҍڋq�\���󋵈ꗗ�T�[�r�X���擾���Aexecute( )���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p�Ǘ��Ҍڋq�\���󋵈ꗗ���N�G�X�g�@@�I�u�W�F�N�g<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiStateResponse
     * @@roseuid 40F51FD50112
     */
    public WEB3AdminSrvRegiStateResponse searchAccountAppliState(WEB3AdminSrvRegiStateRequest l_request) 
    {
        final String STR_METHOD_NAME = " searchAccountAppliState(WEB3AdminSrvRegiStateRequest )";    
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminSrvRegiStateResponse l_response = null;
        WEB3AdminSrvRegiAccountAppliStateListService l_service = null;        
        try
        {
            //get service
            l_service = 
                (WEB3AdminSrvRegiAccountAppliStateListService)Services.getService(
                    WEB3AdminSrvRegiAccountAppliStateListService.class);
        }
        catch (Exception l_ex)
        {
        
            l_response = (WEB3AdminSrvRegiStateResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�T�[�r�X���p�Ǘ��Ҍڋq�\���󋵈ꗗ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;                  
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminSrvRegiStateResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminSrvRegiStateResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "search�ڋq�\���󋵂Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
