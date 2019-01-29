head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualCategoryRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�Ǘ��҃J�e�S���[�o�^�n���h��(WEB3AdminMutualCategoryRegistHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/03 ���� (���u) �V�K�쐬 
*/

package webbroker3.mf.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistChangeRequest;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistChangeResponse;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistCompleteResponse;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistConfirmRequest;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistConfirmResponse;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistInputRequest;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistInputResponse;
import webbroker3.mf.service.delegate.WEB3AdminMutualCategoryRegistService;
import webbroker3.util.WEB3LogUtility;

/**
 * (���M�Ǘ��҃J�e�S���[�o�^�n���h��)<BR>
 * �����M���Ǘ��҃J�e�S���[�o�^�n���h���N���X
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3AdminMutualCategoryRegistHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMutualCategoryRegistHandler.class);
    
    /**
     * (get�J�e�S���[�o�^���͉��)<BR>
     * �����M���Ǘ��҃J�e�S���[�o�^���͉�ʎ擾�������s���B<BR>
     * <BR>
     * ���M�Ǘ��҃J�e�S���[�o�^�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3AdminMutualCategoryRegistInputResponse
     * @@roseuid 4153E9BB016F
     */
    public WEB3AdminMutualCategoryRegistInputResponse getCategoryRegistrInput(
        WEB3AdminMutualCategoryRegistInputRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getCategoryRegistrInput(WEB3AdminMutualCategoryRegistInputRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        //�Ǘ��҃J�e�S���[�o�^�T�[�r�X
        WEB3AdminMutualCategoryRegistService l_service = null;
        //���M�Ǘ��҃J�e�S���[�o�^���͉�ʃ��X�|���X
        WEB3AdminMutualCategoryRegistInputResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminMutualCategoryRegistService) Services.getService(
                    WEB3AdminMutualCategoryRegistService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3AdminMutualCategoryRegistInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҃J�e�S���[�o�^�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminMutualCategoryRegistInputResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMutualCategoryRegistInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "���M�Ǘ��҃J�e�S���[�o�^���͉�ʃ��X�|���X�̏����Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
   
    /**
     * (get�J�e�S���[�ύX���͉��)<BR>
     * �����M���Ǘ��҃J�e�S���[�ύX���͉�ʎ擾�������s���B<BR>
     * <BR>
     * ���M�Ǘ��҃J�e�S���[�o�^�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3AdminMutualCategoryRegistChangeResponse
     * @@roseuid 4153ED7B01BD
     */
    public WEB3AdminMutualCategoryRegistChangeResponse getCategoryRegistChangeInput(
        WEB3AdminMutualCategoryRegistChangeRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getCategoryRegistChangeInput(WEB3AdminMutualCategoryRegistChangeRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //�Ǘ��҃J�e�S���[�o�^�T�[�r�X
        WEB3AdminMutualCategoryRegistService l_service = null;
        //���M�Ǘ��҃J�e�S���[�ύX���͉�ʃ��X�|���X
        WEB3AdminMutualCategoryRegistChangeResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminMutualCategoryRegistService) Services.getService(
                    WEB3AdminMutualCategoryRegistService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3AdminMutualCategoryRegistChangeResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҃J�e�S���[�o�^�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminMutualCategoryRegistChangeResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMutualCategoryRegistChangeResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "���M�Ǘ��҃J�e�S���[�ύX���͉�ʃ��X�|���X�̏����Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
   
    /**
     * (confirm�J�e�S���[�o�^)<BR>
     * �����M���Ǘ��҃J�e�S���[�o�^�m�F�������s���B<BR>
     * <BR>
     * ���M�Ǘ��҃J�e�S���[�o�^�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3AdminMutualCategoryRegistConfirmResponse
     * @@roseuid 4153ECDC0334
     */
    public WEB3AdminMutualCategoryRegistConfirmResponse confirmCategoryRegistRequest(
        WEB3AdminMutualCategoryRegistConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =
            "confirmCategoryRegistRequest(WEB3AdminMutualCategoryRegistConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //�Ǘ��҃J�e�S���[�o�^�T�[�r�X
        WEB3AdminMutualCategoryRegistService l_service = null;
        //���M�Ǘ��҃J�e�S���[�o�^�m�F���X�|���X
        WEB3AdminMutualCategoryRegistConfirmResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminMutualCategoryRegistService) Services.getService(
                    WEB3AdminMutualCategoryRegistService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3AdminMutualCategoryRegistConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҃J�e�S���[�o�^�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminMutualCategoryRegistConfirmResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMutualCategoryRegistConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "���M�Ǘ��҃J�e�S���[�o�^�m�F���X�|���X�̏����Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
   
    /**
     * (complete�J�e�S���[�o�^)<BR>
     * �����M���Ǘ��҃J�e�S���[�o�^�������s���B<BR>
     * <BR>
     * ���M�Ǘ��҃J�e�S���[�o�^�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3AdminMutualCategoryRegistCompleteResponse
     * @@roseuid 4153ED4003A2
     */
    public WEB3AdminMutualCategoryRegistCompleteResponse completeCategoryRegistRequest(
        WEB3AdminMutualCategoryRegistCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "completeCategoryRegistRequest(WEB3AdminMutualCategoryRegistCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //�Ǘ��҃J�e�S���[�o�^�T�[�r�X
        WEB3AdminMutualCategoryRegistService l_service = null;
        //���M�Ǘ��҃J�e�S���[�o�^�������X�|���X
        WEB3AdminMutualCategoryRegistCompleteResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminMutualCategoryRegistService) Services.getService(
                    WEB3AdminMutualCategoryRegistService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3AdminMutualCategoryRegistCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҃J�e�S���[�o�^�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminMutualCategoryRegistCompleteResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMutualCategoryRegistCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "���M�Ǘ��҃J�e�S���[�o�^�������X�|���X�̏����Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
