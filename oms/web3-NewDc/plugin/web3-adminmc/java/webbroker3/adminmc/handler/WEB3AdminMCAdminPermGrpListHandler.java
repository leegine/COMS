head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.41.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPermGrpListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ꗗ�n���h��(WEB3AdminMCAdminPermGrpListHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/25 Tongwei(���u) �V�K�쐬
*/

package webbroker3.adminmc.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminPermGrpListService;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpListResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpListRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDetailsResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDetailsRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ꗗ�n���h��)<BR>
 * �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ꗗ�n���h��<BR>
 * 
 * @@author Tongwei
 * @@version 1.0  
 */
public class WEB3AdminMCAdminPermGrpListHandler implements MessageHandler 
{
    /**
     *���O�o�̓��[�e�B���e�B�B
     */
    private WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminPermGrpListHandler.class);
    
    /**
     * @@roseuid 4198642F00AB
     */
    public WEB3AdminMCAdminPermGrpListHandler() 
    {
     
    }
    
    /**
     * (�����O���[�v�ꗗ�\��)<BR>
     * �����O���[�v�ꗗ�\�����������{����B<BR>
     * <BR>
     * �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ꗗ�T�[�r�X���擾���Aexecute()���\�b�h���R�E
     * ������B<BR>
     * <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ꗗ���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpListResponse
     * @@roseuid 41774B2D02CE
     */
    public WEB3AdminMCAdminPermGrpListResponse permGrpListDisplay(WEB3AdminMCAdminPermGrpListRequest l_request) 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + ".permGrpListDisplay(WEB3AdminMCAdminPermGrpListRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminMCAdminPermGrpListResponse l_response = null;
        WEB3AdminMCAdminPermGrpListService l_service = null;
             
        try
        {
            l_service= (WEB3AdminMCAdminPermGrpListService)Services.getService(WEB3AdminMCAdminPermGrpListService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCAdminPermGrpListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ꗗ�T�[�r�XImpl�̎擾�Ɏ��s���܂���", 
                l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminMCAdminPermGrpListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCAdminPermGrpListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�����O���[�v�ꗗ�\�������Ɏ��s���܂���",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);           
        return l_response;
    }
    
    /**
     * (�����O���[�v�ڍו\��)<BR>
     * �����O���[�v�ڍו\�����������{����B<BR>
     * <BR>
     * �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ꗗ�T�[�r�X���擾���Aexecute()���\�b�h���R�E
     * ������B<BR>
     * <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ڍ׃��N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDetailsResponse
     * @@roseuid 41774B2D02D0
     */
    public WEB3AdminMCAdminPermGrpDetailsResponse permGrpDetailDisplay(WEB3AdminMCAdminPermGrpDetailsRequest l_request) 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + ".permGrpDetailDisplay(WEB3AdminMCAdminPermGrpDetailsRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminMCAdminPermGrpDetailsResponse l_response = null;
        WEB3AdminMCAdminPermGrpListService l_service = null;
             
        try
        {
            l_service= (WEB3AdminMCAdminPermGrpListService)Services.getService(WEB3AdminMCAdminPermGrpListService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCAdminPermGrpDetailsResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ꗗ�T�[�r�XImpl�̎擾�Ɏ��s���܂���", 
                l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminMCAdminPermGrpDetailsResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCAdminPermGrpDetailsResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�����O���[�v�ڍו\�������Ɏ��s���܂���",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);           
        return l_response;
    }
}
@
