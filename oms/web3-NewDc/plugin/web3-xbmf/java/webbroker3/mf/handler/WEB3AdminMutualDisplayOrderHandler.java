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
filename	WEB3AdminMutualDisplayOrderHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�Ǘ��Җ����\�������o�^�n���h��(WEB3AdminMutualDisplayOrderHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/03 ���� (���u) �V�K�쐬 
*/

package webbroker3.mf.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mf.message.WEB3AdminMutualDisplayOrderCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualDisplayOrderCompleteResponse;
import webbroker3.mf.message.WEB3AdminMutualDisplayOrderInputRequest;
import webbroker3.mf.message.WEB3AdminMutualDisplayOrderInputResponse;
import webbroker3.mf.service.delegate.WEB3AdminMutualDisplayOrderService;
import webbroker3.util.WEB3LogUtility;

/**
 * (���M�Ǘ��Җ����\�������o�^�n���h��)<BR>
 * ���M�Ǘ��Җ����\�������o�^�n���h���N���X
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3AdminMutualDisplayOrderHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMutualDisplayOrderHandler.class);
    
    /**
     * (get�����\�������o�^���͉��)<BR>
     * �����M���Ǘ��Җ����\�������o�^���͉�ʎ擾�������s���B<BR>
     * <BR>
     * ���M�Ǘ��Җ����\�������o�^�T�[�r�X���擾���A<BR>
     * execute( )���\�b�h���R�[������B
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���M�Ǘ��Җ����\�������o�^���͉�ʃ��N�G�X�g�I�u�W�F�N�g
     * @@return WEB3AdminMutualDisplayOrderInputResponse
     * @@roseuid 4153F74A02F6
     */
    public WEB3AdminMutualDisplayOrderInputResponse getProductDisplayOrderInput(
        WEB3AdminMutualDisplayOrderInputRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getProductDisplayOrderInput(WEB3AdminMutualDisplayOrderInputRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        //���M�Ǘ��Җ����\�������o�^�T�[�r�X
        WEB3AdminMutualDisplayOrderService l_service = null;
        //���M�Ǘ��Җ����\�������o�^���͉�ʃ��X�|���X
        WEB3AdminMutualDisplayOrderInputResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminMutualDisplayOrderService) Services.getService(
                    WEB3AdminMutualDisplayOrderService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3AdminMutualDisplayOrderInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���M�Ǘ��Җ����\�������o�^�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminMutualDisplayOrderInputResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMutualDisplayOrderInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "���M�Ǘ��Җ����\�������o�^���͉�ʃ��X�|���X�̏����Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (complete�Ǘ��Җ����\�������o�^)<BR>
     * �����M���Ǘ��Җ����\�������o�^�������s���B<BR>
     * <BR>
     * ���M�Ǘ��Җ����\�������o�^�T�[�r�X���擾���A<BR>
     * execute( )���\�b�h���R�[������B
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���M�Ǘ��Җ����\�������o�^�������N�G�X�g�I�u�W�F�N�g
     * @@return WEB3AdminMutualDisplayOrderCompleteResponse
     * @@roseuid 4153F7C701EC
     */
    public WEB3AdminMutualDisplayOrderCompleteResponse completeAdminMutualDisplayOrderRegistr(
        WEB3AdminMutualDisplayOrderCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "completeAdminMutualDisplayOrderRegistr(WEB3AdminMutualDisplayOrderCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //���M�Ǘ��Җ����\�������o�^�T�[�r�X
        WEB3AdminMutualDisplayOrderService l_service = null;
        //���M�Ǘ��Җ����\�������o�^�������X�|���X
        WEB3AdminMutualDisplayOrderCompleteResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminMutualDisplayOrderService) Services.getService(
                    WEB3AdminMutualDisplayOrderService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3AdminMutualDisplayOrderCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���M�Ǘ��Җ����\�������o�^�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminMutualDisplayOrderCompleteResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMutualDisplayOrderCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "���M�Ǘ��Җ����\�������o�^�������X�|���X�̏����Ɏ��s���܂����B", 
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
