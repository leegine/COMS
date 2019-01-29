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
filename	WEB3MutualRecruitOrderInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M��W�������̓n���h���N���X(WEB3MutualRecruitOrderInputHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/29 ���� (���u) �V�K�쐬
*/

package webbroker3.mf.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mf.message.WEB3MutualApplyInputRequest;
import webbroker3.mf.message.WEB3MutualApplyInputResponse;
import webbroker3.mf.service.delegate.WEB3MutualRecruitOrderInputService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

/**
 * ���M��W�������̓n���h���N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3MutualRecruitOrderInputHandler implements MessageHandler
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualRecruitOrderInputHandler.class);
    
    /**
     * (��W��������)<BR>
     * �����M���̕�W�������͉�ʕ\���������s���B<BR>
     * ���M��W�������̓T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���M��W�������̓��N�G�X�g
     * @@return webbroker3.mf.message.WEB3MutualApplyInputResponse
     * @@roseuid 40555E0E00E9
     */
    public WEB3MutualApplyInputResponse RecruitOrderInput(
        WEB3MutualApplyInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " RecruitOrderInput(WEB3MutualApplyInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("the parametor method in null ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3MutualRecruitOrderInputService l_service = null;
        WEB3MutualApplyInputResponse l_response = null;
        try
        {
            l_service = 
                (WEB3MutualRecruitOrderInputService) Services.getService(
                    WEB3MutualRecruitOrderInputService.class);
        }
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3MutualApplyInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���M��W�������̓T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response = 
                (WEB3MutualApplyInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = 
                (WEB3MutualApplyInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                " ���M��W�������͂̎擾�Ɏ��s���܂���",
                l_ex.getErrorInfo(),
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }   
}
@
