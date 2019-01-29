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
filename	WEB3MutualCancelAcceptHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�������t�n���h���N���X(WEB3MutualCancelAcceptHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 ������ (���u) �V�K�쐬
                   2004/08/23 ��O�� (���u) ���r���[    
*/

package webbroker3.mf.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mf.message.WEB3MutualCancelAcceptRequest;
import webbroker3.mf.message.WEB3MutualCancelAcceptResponse;
import webbroker3.mf.service.delegate.WEB3MutualCancelAcceptService;
import webbroker3.util.WEB3LogUtility;

/**
 * �����M�������t�n���h���N���X
 * 
 * @@author ������(���u)
 * @@version 1.0
 */

public class WEB3MutualCancelAcceptHandler implements MessageHandler
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualCancelAcceptHandler.class);

    /**
     * (�����t���N�G�X�g)<BR>
     * �����M�������t�������s���B<BR>
     * <BR>
     * �����M�������t�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.mf.message.WEB3MutualCancelAcceptResponse
     * @@roseuid 4056604300DC
     */
    public WEB3MutualCancelAcceptResponse cancelAcceptRequest(WEB3MutualCancelAcceptRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "cancelAcceptRequest(WEB3MutualCancelAcceptRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3MutualCancelAcceptResponse l_response = null;
        WEB3MutualCancelAcceptService l_mutualCancelAcceptService = null;
        
        try
        {
            l_mutualCancelAcceptService =
                (WEB3MutualCancelAcceptService) Services.getService(
                    WEB3MutualCancelAcceptService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MutualCancelAcceptResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����M�������t�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //�����M�������t�T�[�r�X���擾���Aexecute()���\�b�h���R�[������
            l_response =
                (WEB3MutualCancelAcceptResponse) l_mutualCancelAcceptService.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MutualCancelAcceptResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����M�������t���������s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
