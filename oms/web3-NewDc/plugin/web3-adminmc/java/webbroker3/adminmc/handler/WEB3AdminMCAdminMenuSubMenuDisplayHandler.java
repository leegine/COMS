head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.41.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminMenuSubMenuDisplayHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��j���[����T�u���j���[�\���n���h��(WEB3AdminMCAdminMenuSubMenuDisplayHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/23  �� �� �@@ (���u) �V�K�쐬
*/

package webbroker3.adminmc.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminMenuSubMenuDisplayService;
import webbroker3.adminmc.message.WEB3AdminMCAdminMenuSubMenuDisplayResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminMenuSubMenuDisplayRequest;


/**
 * (�Ǘ��҃��j���[����T�u���j���[�\���n���h��)<BR>
 * �Ǘ��҃��j���[����T�u���j���[�\���n���h��<BR>
 * @@author �����@@
 * @@version 1.0
 */

public class WEB3AdminMCAdminMenuSubMenuDisplayHandler implements MessageHandler 
{
    
    /**
     *���O�o�̓��[�e�B���e�B�B
     */
    private WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminMenuSubMenuDisplayHandler.class);
    
    /**
     * @@roseuid 4198642E01B5
     */
    public WEB3AdminMCAdminMenuSubMenuDisplayHandler() 
    {
     
    }
    
    /**
     * (�T�u���j���[�\��)<BR>
     * �T�u���j���[�ŗ��p�ł���@@�\�J�e�S���R�[�h�ꗗ��ԋp����B<BR>
     * <BR>
     * �Ǘ��҃��j���[����T�u���j���[�\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����T�u���j���[�\�����N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminMenuSubMenuDisplayResponse
     * @@roseuid 417769C70242
     */
    public WEB3AdminMCAdminMenuSubMenuDisplayResponse subMenuDisplay(WEB3AdminMCAdminMenuSubMenuDisplayRequest l_request) 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + ".subMenuDisplay(WEB3AdminMCAdminMenuSubMenuDisplayRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminMCAdminMenuSubMenuDisplayResponse l_response = null;
        WEB3AdminMCAdminMenuSubMenuDisplayService l_service = null;     
        try
        {
            l_service= (WEB3AdminMCAdminMenuSubMenuDisplayService)Services.getService(WEB3AdminMCAdminMenuSubMenuDisplayService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCAdminMenuSubMenuDisplayResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�Ǘ��҃��j���[����T�u���j���[�\���T�[�r�XImpl�̎擾�Ɏ��s���܂���.", l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminMCAdminMenuSubMenuDisplayResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCAdminMenuSubMenuDisplayResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�T�u���j���[�ŗ��p�ł���@@�\�J�e�S���R�[�h�ꗗ�Ɏ��s���܂���.",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        
        return l_response; 
    }

}
@
