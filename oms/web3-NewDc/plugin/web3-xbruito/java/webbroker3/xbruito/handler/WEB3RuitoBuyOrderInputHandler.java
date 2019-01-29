head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoBuyOrderInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݐϓ������t�������̓n���h��(WEB3RuitoBuyOrderInputHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/07 �m�X (���u) �V�K�쐬
*/
package webbroker3.xbruito.handler;

import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.xbruito.message.WEB3RuitoBuyInputRequest;
import webbroker3.xbruito.message.WEB3RuitoBuyInputResponse;
import webbroker3.xbruito.service.delegate.WEB3RuitoBuyOrderInputService;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.util.WEB3LogUtility;


/**
 * �ݐϓ������t�������̓n���h��<BR>
 */
public class WEB3RuitoBuyOrderInputHandler implements MessageHandler
{

    /**
         * ���O�o�̓��[�e�B���e�B�B
         */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoBuyOrderInputHandler.class);

    /**
     * �ݐϓ����̔��t�������͉�ʕ\���������s���B<BR>
     * <BR>
     * �ݓ����t�������̓T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param ���N�G�X�g�f�[�^
     * @@param l_request
     * @@roseuid 4069307001B5
     */
    public WEB3RuitoBuyInputResponse ruitoBuyInputRequest(
            WEB3RuitoBuyInputRequest l_request)
    {
        final String STR_METHOD_NAME =
            "WEB3RuitoBuyOrderInputRequest(WEB3RuitoBuyInputRequest)";

        log.entering(STR_METHOD_NAME);
        if (l_request == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");            
        }
        WEB3RuitoBuyInputResponse l_response = null;
        WEB3RuitoBuyOrderInputService l_service = null;

        // �ݓ����t�������̓T�[�r�X�I�u�W�F�N�g���擾����
        // �ݓ����t�������̓T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        // �������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_service =
                (WEB3RuitoBuyOrderInputService) Services.getService(
                    WEB3RuitoBuyOrderInputService.class);
            l_response = 
                (WEB3RuitoBuyInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3RuitoBuyInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�ݓ����t�����Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        // ���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;

    }
}
@
