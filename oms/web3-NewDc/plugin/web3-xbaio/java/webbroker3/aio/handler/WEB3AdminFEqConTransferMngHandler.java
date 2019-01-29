head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.25.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFEqConTransferMngHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���U�֊Ǘ��n���h��(WEB3AdminFEqConTransferMngHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/22 ���E (���u) �V�K�쐬
*/
package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import webbroker3.aio.message.WEB3AdminFEqConTransferListInputRequest;
import webbroker3.aio.message.WEB3AdminFEqConTransferListInputResponse;
import webbroker3.aio.message.WEB3AdminFEqConTransferListRequest;
import webbroker3.aio.message.WEB3AdminFEqConTransferListResponse;
import webbroker3.aio.service.delegate.WEB3AdminFEqConTransferMngService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O���U�֊Ǘ��n���h��)<BR>
 * �O���U�֊Ǘ��n���h���N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AdminFEqConTransferMngHandler implements MessageHandler 
{
    
    /**
     * @@roseuid 4235651700CB
     */
    public WEB3AdminFEqConTransferMngHandler() 
    {
     
    }
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFEqConTransferMngHandler.class);
    
    /**
     * (���͉�ʕ\��)<BR>
     * ���͉�ʕ\�����s���B<BR>
     * <BR>
     * �O���U�֊Ǘ��T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3AdminFEqConTransferListInputResponse
     * @@roseuid 41E3991703E3
     */
    public WEB3AdminFEqConTransferListInputResponse displayInputScreen(
        WEB3AdminFEqConTransferListInputRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "displayInputScreen(" +
            "WEB3AdminFEqConTransferListInputRequest l_request)"; 
        log.entering(STR_METHOD_NAME);
        
        //�O���U�֊Ǘ��T�[�r�X
        WEB3AdminFEqConTransferMngService l_service = null;          
         
        //�O���U�ֈꗗ�������̓��X�|���X
        WEB3AdminFEqConTransferListInputResponse l_response = null;     
         
        try
        {
            l_service = 
                (WEB3AdminFEqConTransferMngService) Services.getService(
                    WEB3AdminFEqConTransferMngService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3AdminFEqConTransferListInputResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�O���U�֊Ǘ��T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminFEqConTransferListInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFEqConTransferListInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�O���U�ֈꗗ�������͂Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;

    }
    
    /**
     * (�ꗗ��ʕ\��)<BR>
     * �ꗗ��ʕ\�����s���B<BR>
     * <BR>
     * �O���U�֊Ǘ��T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3AdminFEqConTransferListResponse
     * @@roseuid 41E3995900B6
     */
    public WEB3AdminFEqConTransferListResponse displayListScreen(
        WEB3AdminFEqConTransferListRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "displayListScreen(" +
            "WEB3AdminFEqConTransferListRequest l_request)"; 
        log.entering(STR_METHOD_NAME);
        
        //�O���U�֊Ǘ��T�[�r�X
        WEB3AdminFEqConTransferMngService l_service = null;          
         
        //�O���U�ֈꗗ���X�|���X
        WEB3AdminFEqConTransferListResponse l_response = null;     
         
        try
        {
            l_service = 
                (WEB3AdminFEqConTransferMngService) Services.getService(
                    WEB3AdminFEqConTransferMngService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3AdminFEqConTransferListResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�O���U�֊Ǘ��T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminFEqConTransferListResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFEqConTransferListResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�O���U�ֈꗗ�Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
